package io.github.rosemoe.sora.text;

import defpackage.ju2;
import io.github.rosemoe.sora.text.bidi.ContentBidi;
import io.github.rosemoe.sora.text.bidi.Directions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Content implements CharSequence {
    public static final int CHECK_TYPE_CURSOR = 1;
    public static final int CHECK_TYPE_INDEX = 2;
    public static final int CHECK_TYPE_READ = 0;
    public static final int DEFAULT_LIST_CAPACITY = 1000;
    public static final int DEFAULT_MAX_UNDO_STACK_SIZE = 500;
    private static int sInitialListCapacity;
    private final ContentBidi bidi;
    private final List<ContentListener> contentListeners;
    private Cursor cursor;
    private final AtomicLong documentVersion;
    private final Indexer indexer;
    private final List<ContentLine> lines;
    private final ReadWriteLock lock;
    private int nestedBatchEdit;
    private int textLength;
    private UndoManager undoManager;

    public interface ContentLineConsumer {
        void accept(int i, ContentLine contentLine, Directions directions);
    }

    public interface ContentLineConsumer2 {

        public static class AbortFlag {
            public boolean set = false;
        }

        void accept(int i, ContentLine contentLine, AbortFlag abortFlag);
    }

    static {
        setInitialLineCapacity(DEFAULT_LIST_CAPACITY);
    }

    public Content(CharSequence charSequence, boolean z) {
        this.documentVersion = new AtomicLong(1L);
        charSequence = charSequence == null ? "" : charSequence;
        if (z) {
            this.lock = new ReentrantReadWriteLock();
        } else {
            this.lock = null;
        }
        this.textLength = 0;
        this.nestedBatchEdit = 0;
        ArrayList arrayList = new ArrayList(getInitialLineCapacity());
        this.lines = arrayList;
        arrayList.add(new ContentLine());
        this.contentListeners = new ArrayList();
        this.bidi = new ContentBidi(this);
        this.undoManager = new UndoManager();
        setMaxUndoStackSize(500);
        this.indexer = new CachedIndexer(this);
        if (charSequence.length() == 0) {
            setUndoEnabled(true);
            return;
        }
        setUndoEnabled(false);
        insert(0, 0, charSequence);
        setUndoEnabled(true);
    }

    private void deleteInternal(int i, int i2, int i3, int i4) {
        int i5;
        checkLineAndColumn(i3, i4, 1);
        checkLineAndColumn(i, i2, 1);
        if (i == i3 && i2 == i4) {
            return;
        }
        if (i4 > this.lines.get(i3).length() && (i5 = i3 + 1) < getLineCount()) {
            deleteInternal(i, i2, i5, 0);
            return;
        }
        ContentLine contentLine = this.lines.get(i);
        if (i2 > contentLine.length()) {
            deleteInternal(i, contentLine.length(), i3, i4);
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (i == i3) {
            ContentLine contentLineMakeLineMutable = makeLineMutable(i);
            int length = contentLineMakeLineMutable.length();
            if (i2 < 0 || i4 > length || i2 > i4) {
                throw new StringIndexOutOfBoundsException("invalid bounds");
            }
            Cursor cursor = this.cursor;
            if (cursor != null) {
                cursor.beforeDelete(i, i2, i3, i4);
            }
            dispatchBeforeModification();
            sb.append((CharSequence) contentLineMakeLineMutable, i2, i4);
            contentLineMakeLineMutable.delete(i2, i4);
            this.textLength -= i4 - i2;
        } else {
            if (i >= i3) {
                w01.a("start line > end line");
                return;
            }
            Cursor cursor2 = this.cursor;
            if (cursor2 != null) {
                cursor2.beforeDelete(i, i2, i3, i4);
            }
            dispatchBeforeModification();
            int i6 = i + 1;
            for (int i7 = i6; i7 <= i3 - 1; i7++) {
                ContentLine contentLine2 = this.lines.get(i7);
                LineSeparator lineSeparator = this.lines.get(i7).getLineSeparator();
                this.textLength -= contentLine2.length() + lineSeparator.getLength();
                contentLine2.appendTo(sb);
                sb.append(lineSeparator.getContent());
                contentLine2.release();
            }
            if (i3 > i6) {
                this.lines.subList(i6, i3).clear();
            }
            ContentLine contentLineMakeLineMutable2 = makeLineMutable(i);
            ContentLine contentLine3 = this.lines.get(i6);
            this.textLength -= contentLineMakeLineMutable2.length() - i2;
            sb.insert(0, contentLineMakeLineMutable2, i2, contentLineMakeLineMutable2.length()).insert(contentLineMakeLineMutable2.length() - i2, contentLineMakeLineMutable2.getLineSeparator().getContent());
            contentLineMakeLineMutable2.delete(i2, contentLineMakeLineMutable2.length());
            this.textLength -= i4;
            sb.append((CharSequence) contentLine3, 0, i4);
            this.textLength -= contentLineMakeLineMutable2.getLineSeparator().getLength();
            this.lines.remove(i6);
            contentLineMakeLineMutable2.append(new TextReference(contentLine3, i4, contentLine3.length()));
            contentLineMakeLineMutable2.setLineSeparator(contentLine3.getLineSeparator());
            contentLine3.release();
        }
        dispatchAfterDelete(i, i2, i3, i4, sb);
    }

    private void dispatchAfterDelete(int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.undoManager.afterDelete(this, i, i2, i3, i4, charSequence);
        Cursor cursor = this.cursor;
        if (cursor != null) {
            cursor.afterDelete(i, i2, i3, i4, charSequence);
        }
        Indexer indexer = this.indexer;
        if (indexer instanceof ContentListener) {
            ((ContentListener) indexer).afterDelete(this, i, i2, i3, i4, charSequence);
        }
        Iterator<ContentListener> it2 = this.contentListeners.iterator();
        while (it2.hasNext()) {
            it2.next().afterDelete(this, i, i2, i3, i4, charSequence);
        }
    }

    private void dispatchAfterInsert(int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.undoManager.afterInsert(this, i, i2, i3, i4, charSequence);
        Cursor cursor = this.cursor;
        if (cursor != null) {
            cursor.afterInsert(i, i2, i3, i4, charSequence);
        }
        Indexer indexer = this.indexer;
        if (indexer instanceof ContentListener) {
            ((ContentListener) indexer).afterInsert(this, i, i2, i3, i4, charSequence);
        }
        Iterator<ContentListener> it2 = this.contentListeners.iterator();
        while (it2.hasNext()) {
            it2.next().afterInsert(this, i, i2, i3, i4, charSequence);
        }
    }

    private void dispatchBeforeModification() {
        this.undoManager.beforeModification(this);
        Iterator<ContentListener> it2 = this.contentListeners.iterator();
        while (it2.hasNext()) {
            it2.next().beforeModification(this);
        }
    }

    private void dispatchBeforeReplace() {
        this.undoManager.beforeReplace(this);
        Cursor cursor = this.cursor;
        if (cursor != null) {
            cursor.beforeReplace();
        }
        Indexer indexer = this.indexer;
        if (indexer instanceof ContentListener) {
            ((ContentListener) indexer).beforeReplace(this);
        }
        Iterator<ContentListener> it2 = this.contentListeners.iterator();
        while (it2.hasNext()) {
            it2.next().beforeReplace(this);
        }
    }

    public static int getInitialLineCapacity() {
        return sInitialListCapacity;
    }

    private void insertInternal(int i, int i2, CharSequence charSequence) {
        checkLineAndColumn(i, i2, 1);
        if (charSequence == null) {
            w01.a("text can not be null");
            return;
        }
        if (i2 > this.lines.get(i).length()) {
            i2 = this.lines.get(i).length();
        }
        int i3 = i2;
        Cursor cursor = this.cursor;
        if (cursor != null) {
            cursor.beforeInsert(i, i3);
        }
        dispatchBeforeModification();
        ContentLine contentLineMakeLineMutable = makeLineMutable(i);
        InsertTextHelper insertTextHelperForInsertion = InsertTextHelper.forInsertion(charSequence);
        int iForward = InsertTextHelper.TYPE_EOF;
        LinkedList linkedList = new LinkedList();
        LineSeparator lineSeparator = contentLineMakeLineMutable.getLineSeparator();
        boolean z = false;
        int i4 = i;
        int indexNext = i3;
        while (true) {
            int iForward2 = z ? iForward : insertTextHelperForInsertion.forward();
            if (iForward2 == InsertTextHelper.TYPE_EOF) {
                contentLineMakeLineMutable.setLineSeparator(lineSeparator);
                this.lines.addAll(i + 1, linkedList);
                insertTextHelperForInsertion.recycle();
                this.textLength += charSequence.length();
                dispatchAfterInsert(i, i3, i4, indexNext, charSequence);
                return;
            }
            if (iForward2 == InsertTextHelper.TYPE_LINE_CONTENT) {
                contentLineMakeLineMutable.insert(indexNext, charSequence, insertTextHelperForInsertion.getIndex(), insertTextHelperForInsertion.getIndexNext());
                indexNext += insertTextHelperForInsertion.getIndexNext() - insertTextHelperForInsertion.getIndex();
                z = false;
            } else {
                contentLineMakeLineMutable.setLineSeparator(LineSeparator.fromSeparatorString(charSequence, insertTextHelperForInsertion.getIndex(), insertTextHelperForInsertion.getIndexNext()));
                iForward = insertTextHelperForInsertion.forward();
                ContentLine contentLine = new ContentLine((((contentLineMakeLineMutable.length() - indexNext) + insertTextHelperForInsertion.getIndexNext()) - insertTextHelperForInsertion.getIndex()) + 10);
                contentLine.insert(0, contentLineMakeLineMutable, indexNext, contentLineMakeLineMutable.length());
                contentLineMakeLineMutable.delete(indexNext, contentLineMakeLineMutable.length());
                linkedList.add(contentLine);
                i4++;
                indexNext = 0;
                contentLineMakeLineMutable = contentLine;
                z = true;
            }
        }
    }

    private ContentLine makeLineMutable(int i) {
        ContentLine contentLine = this.lines.get(i);
        ContentLine mutable = contentLine.toMutable();
        if (mutable != contentLine) {
            this.lines.set(i, mutable);
            contentLine.release();
        }
        return mutable;
    }

    public static void setInitialLineCapacity(int i) {
        if (i > 0) {
            sInitialListCapacity = i;
        } else {
            w01.a("capacity can not be negative or zero");
        }
    }

    private Content subContentInternal(int i, int i2, int i3, int i4, boolean z) {
        List<ContentLine> list;
        Content content = new Content(null, z);
        content.setUndoEnabled(false);
        if (i == i3) {
            ContentLine contentLine = this.lines.get(i);
            if (i4 != contentLine.length() + 1 || contentLine.getLineSeparator() != LineSeparator.CRLF) {
                content.insert(0, 0, contentLine.subSequence(i2, i4));
            } else if (i2 < i4) {
                content.insert(0, 0, contentLine.subSequence(i2, contentLine.length()));
                content.lines.get(0).setLineSeparator(LineSeparator.CR);
                content.textLength++;
                content.lines.add(new ContentLine());
            }
        } else {
            if (i >= i3) {
                throw new StringIndexOutOfBoundsException("start > end");
            }
            ContentLine contentLine2 = this.lines.get(i);
            if (contentLine2.getLineSeparator() != LineSeparator.CRLF || i2 <= contentLine2.length()) {
                content.insert(0, 0, contentLine2.subSequence(i2, contentLine2.length()));
                content.lines.get(0).setLineSeparator(contentLine2.getLineSeparator());
                content.textLength += contentLine2.getLineSeparator().getLength();
            } else {
                if (i2 != contentLine2.length() + 1) {
                    qc6.a();
                    return null;
                }
                ContentLine contentLine3 = content.lines.get(0);
                LineSeparator lineSeparator = LineSeparator.LF;
                contentLine3.setLineSeparator(lineSeparator);
                content.textLength += lineSeparator.getLength();
            }
            int i5 = i + 1;
            while (true) {
                list = this.lines;
                if (i5 >= i3) {
                    break;
                }
                ContentLine contentLine4 = list.get(i5);
                content.lines.add(new ContentLine(contentLine4));
                content.textLength += contentLine4.length() + contentLine4.getLineSeparator().getLength();
                i5++;
            }
            ContentLine contentLine5 = list.get(i3);
            if (i4 == contentLine5.length() + 1 && contentLine5.getLineSeparator() == LineSeparator.CRLF) {
                ContentLine contentLineInsert = new ContentLine().insert(0, contentLine5, 0, i4 - 1);
                content.lines.add(contentLineInsert);
                contentLineInsert.setLineSeparator(LineSeparator.CR);
                content.textLength += i4 + 1;
            } else {
                content.lines.add(new ContentLine().insert(0, contentLine5, 0, i4));
                content.textLength += i4;
            }
        }
        content.setUndoEnabled(true);
        return content;
    }

    private StringBuilder subStringBuilder(int i, int i2, int i3, int i4, int i5) {
        List<ContentLine> list;
        StringBuilder sb = new StringBuilder(i5);
        if (i == i3) {
            ContentLine contentLine = this.lines.get(i);
            if (i4 != contentLine.length() + 1 || contentLine.getLineSeparator() != LineSeparator.CRLF) {
                sb.append((CharSequence) this.lines.get(i), i2, i4);
                return sb;
            }
            if (i2 < i4) {
                sb.append((CharSequence) this.lines.get(i), i2, contentLine.length());
                sb.append(LineSeparator.CR.getContent());
            }
            return sb;
        }
        if (i >= i3) {
            throw new StringIndexOutOfBoundsException("start > end");
        }
        ContentLine contentLine2 = this.lines.get(i);
        if (contentLine2.getLineSeparator() != LineSeparator.CRLF || i2 <= contentLine2.length()) {
            sb.append((CharSequence) contentLine2, i2, contentLine2.length());
            sb.append(contentLine2.getLineSeparator().getContent());
        } else {
            if (i2 != contentLine2.length() + 1) {
                qc6.a();
                return null;
            }
            sb.append(LineSeparator.LF.getContent());
        }
        while (true) {
            i++;
            list = this.lines;
            if (i >= i3) {
                break;
            }
            ContentLine contentLine3 = list.get(i);
            sb.append((CharSequence) contentLine3);
            sb.append(contentLine3.getLineSeparator().getContent());
        }
        ContentLine contentLine4 = list.get(i3);
        if (i4 != contentLine4.length() + 1 || contentLine4.getLineSeparator() != LineSeparator.CRLF) {
            sb.append((CharSequence) contentLine4, 0, i4);
            return sb;
        }
        sb.append((CharSequence) contentLine4, 0, i4);
        sb.append(LineSeparator.CR.getContent());
        return sb;
    }

    private static boolean textEquals(ContentLine contentLine, ContentLine contentLine2) {
        if (contentLine.length() != contentLine2.length()) {
            return false;
        }
        if (contentLine == contentLine2) {
            return true;
        }
        for (int i = 0; i < contentLine.length(); i++) {
            if (contentLine.charAt(i) != contentLine2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public void addContentListener(ContentListener contentListener) {
        if (contentListener == null) {
            w01.a("listener can not be null");
        } else if (contentListener instanceof Indexer) {
            w01.a("Permission denied");
        } else {
            if (this.contentListeners.contains(contentListener)) {
                return;
            }
            this.contentListeners.add(contentListener);
        }
    }

    public void appendToStringBuilder(StringBuilder sb) {
        sb.ensureCapacity(sb.length() + length());
        lock(false);
        try {
            int lineCount = getLineCount();
            for (int i = 0; i < lineCount; i++) {
                ContentLine contentLine = this.lines.get(i);
                contentLine.appendTo(sb);
                sb.append(contentLine.getLineSeparator().getContent());
            }
            unlock(false);
        } catch (Throwable th) {
            unlock(false);
            throw th;
        }
    }

    public boolean beginBatchEdit() {
        this.nestedBatchEdit++;
        return isInBatchEdit();
    }

    public boolean canRedo() {
        return this.undoManager.canRedo();
    }

    public boolean canUndo() {
        return this.undoManager.canUndo();
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        checkIndex(i, 0);
        lock(false);
        try {
            CharPosition charPosition = getIndexer().getCharPosition(i);
            return this.lines.get(charPosition.line).charAt(charPosition.column);
        } finally {
            unlock(false);
        }
    }

    public void checkIndex(int i, int i2) {
        if (i2 != 0 ? i <= length() : i < length()) {
            if (i >= 0) {
                return;
            }
        }
        throw new StringIndexOutOfBoundsException("Index " + i + " out of bounds. length:" + length());
    }

    public void checkLine(int i) {
        if (i >= getLineCount() || i < 0) {
            throw new StringIndexOutOfBoundsException("Line " + i + " out of bounds. line count:" + getLineCount());
        }
    }

    public void checkLineAndColumn(int i, int i2, int i3) {
        checkLine(i);
        ContentLine contentLine = this.lines.get(i);
        if (i3 == 0) {
            int length = contentLine.length() + contentLine.getLineSeparator().getLength();
            if (i2 >= length || i2 < 0) {
                ju2.a(i2, " out of bounds for READ. line: ", i, length, ")");
                return;
            }
            return;
        }
        if (i3 == 1) {
            int length2 = contentLine.length();
            if (i2 > length2 || i2 < 0) {
                ju2.a(i2, " out of bounds for CURSOR. line: ", i, length2, "]");
                return;
            }
            return;
        }
        if (i3 != 2) {
            return;
        }
        int length3 = contentLine.length() + contentLine.getLineSeparator().getLength();
        if (i == getLineCount() - 1) {
            if (i2 > length3 || i2 < 0) {
                ju2.a(i2, " out of bounds for INDEX. line: ", i, length3, "]");
                return;
            }
            return;
        }
        if (i2 >= length3 || i2 < 0) {
            ju2.a(i2, " out of bounds for INDEX. line: ", i, length3, ")");
        }
    }

    public Content copyText(boolean z, boolean z2) {
        lock(false);
        try {
            Content content = new Content(null, z);
            content.lines.remove(0);
            ((ArrayList) content.lines).ensureCapacity(getLineCount());
            List<ContentLine> list = this.lines;
            if (z2) {
                Iterator<ContentLine> it2 = list.iterator();
                while (it2.hasNext()) {
                    it2.next().retain();
                }
                content.lines.addAll(this.lines);
            } else {
                Iterator<ContentLine> it3 = list.iterator();
                while (it3.hasNext()) {
                    content.lines.add(new ContentLine(it3.next()));
                }
            }
            content.textLength = this.textLength;
            return content;
        } finally {
            unlock(false);
        }
    }

    public Content copyTextShallow() {
        return copyTextShallow(false);
    }

    public void delete(int i, int i2) {
        lock(true);
        checkIndex(i, 1);
        checkIndex(i2, 1);
        this.documentVersion.getAndIncrement();
        try {
            CharPosition charPosition = getIndexer().getCharPosition(i);
            CharPosition charPosition2 = getIndexer().getCharPosition(i2);
            if (i != i2) {
                deleteInternal(charPosition.line, charPosition.column, charPosition2.line, charPosition2.column);
            }
        } finally {
            unlock(true);
        }
    }

    public boolean endBatchEdit() {
        int i = this.nestedBatchEdit - 1;
        this.nestedBatchEdit = i;
        if (i == 0) {
            this.undoManager.onExitBatchEdit();
        }
        if (this.nestedBatchEdit < 0) {
            this.nestedBatchEdit = 0;
        }
        return isInBatchEdit();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Content)) {
            return false;
        }
        Content content = (Content) obj;
        if (content.length() != length()) {
            return false;
        }
        for (int i = 0; i < getLineCount(); i++) {
            if (!textEquals(this.lines.get(i), content.lines.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int getCharIndex(int i, int i2) {
        lock(false);
        try {
            return getIndexer().getCharIndex(i, i2);
        } finally {
            unlock(false);
        }
    }

    public int getColumnCount(int i) {
        return getLine(i).length();
    }

    public int getColumnCountUnsafe(int i) {
        return this.lines.get(i).length();
    }

    public Cursor getCursor() {
        if (this.cursor == null) {
            this.cursor = new Cursor(this);
        }
        return this.cursor;
    }

    public long getDocumentVersion() {
        return this.documentVersion.get();
    }

    public Indexer getIndexer() {
        Cursor cursor = this.cursor;
        return cursor != null ? cursor.getIndexer() : this.indexer;
    }

    public ContentLine getLine(int i) {
        lock(false);
        try {
            return this.lines.get(i);
        } finally {
            unlock(false);
        }
    }

    public void getLineChars(int i, char[] cArr) {
        getRegionOnLine(i, 0, getColumnCount(i), cArr, 0);
    }

    public int getLineCount() {
        return this.lines.size();
    }

    public Directions getLineDirections(int i) {
        lock(false);
        try {
            return this.bidi.getLineDirections(this.lines.get(i), i);
        } finally {
            unlock(false);
        }
    }

    public LineSeparator getLineSeparatorUnsafe(int i) {
        return this.lines.get(i).getLineSeparator();
    }

    public String getLineString(int i) {
        lock(false);
        try {
            checkLine(i);
            return this.lines.get(i).toString();
        } finally {
            unlock(false);
        }
    }

    public ContentLine getLineUnsafe(int i) {
        return this.lines.get(i);
    }

    public int getMaxUndoStackSize() {
        return this.undoManager.getMaxUndoStackSize();
    }

    public int getNestedBatchEdit() {
        return this.nestedBatchEdit;
    }

    public void getRegionOnLine(int i, int i2, int i3, char[] cArr, int i4) {
        lock(false);
        try {
            this.lines.get(i).getChars(i2, i3, cArr, i4);
        } finally {
            unlock(false);
        }
    }

    public UndoManager getUndoManager() {
        return this.undoManager;
    }

    public int hashCode() {
        return Objects.hash(this.lines, Integer.valueOf(this.textLength));
    }

    public void insert(int i, int i2, CharSequence charSequence) {
        lock(true);
        this.documentVersion.getAndIncrement();
        try {
            insertInternal(i, i2, charSequence);
        } finally {
            unlock(true);
        }
    }

    public boolean isBidiEnabled() {
        return this.bidi.isEnabled();
    }

    public boolean isCursorCreated() {
        return this.cursor != null;
    }

    public boolean isInBatchEdit() {
        return this.nestedBatchEdit > 0;
    }

    public boolean isRtlAt(int i, int i2) {
        Directions lineDirections = getLineDirections(i);
        for (int i3 = 0; i3 < lineDirections.getRunCount(); i3++) {
            if (i2 >= lineDirections.getRunStart(i3) && i2 < lineDirections.getRunEnd(i3)) {
                return lineDirections.isRunRtl(i3);
            }
        }
        return false;
    }

    public boolean isThreadSafe() {
        return this.lock != null;
    }

    public boolean isUndoEnabled() {
        return this.undoManager.isUndoEnabled();
    }

    public boolean isUndoManagerWorking() {
        return this.undoManager.isModifyingContent();
    }

    public boolean isValidPosition(CharPosition charPosition) {
        if (charPosition == null) {
            return false;
        }
        int i = charPosition.line;
        int i2 = charPosition.column;
        int i3 = charPosition.index;
        lock(false);
        if (i >= 0) {
            try {
                if (i < getLineCount()) {
                    ContentLine line = getLine(i);
                    if (i2 <= line.length() + line.getLineSeparator().getLength() && i2 >= 0) {
                        return getIndexer().getCharIndex(i, i2) == i3;
                    }
                    return false;
                }
            } finally {
                unlock(false);
            }
        }
        return false;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.textLength;
    }

    public void lock(boolean z) {
        ReadWriteLock readWriteLock = this.lock;
        if (readWriteLock == null) {
            return;
        }
        (z ? readWriteLock.writeLock() : readWriteLock.readLock()).lock();
    }

    public void redo() {
        this.undoManager.redo(this);
    }

    public void release() {
        lock(true);
        try {
            Iterator<ContentLine> it2 = this.lines.iterator();
            while (it2.hasNext()) {
                it2.next().release();
            }
            this.lines.clear();
            this.textLength = 0;
            this.cursor = null;
            this.bidi.destroy();
        } finally {
            unlock(true);
        }
    }

    public void removeContentListener(ContentListener contentListener) {
        if (contentListener instanceof Indexer) {
            w01.a("Permission denied");
        } else {
            this.contentListeners.remove(contentListener);
        }
    }

    public void replace(int i, int i2, int i3, int i4, CharSequence charSequence) {
        if (charSequence == null) {
            w01.a("text can not be null");
            return;
        }
        lock(true);
        this.documentVersion.getAndIncrement();
        try {
            dispatchBeforeReplace();
            deleteInternal(i, i2, i3, i4);
            insertInternal(i, i2, charSequence);
        } finally {
            unlock(true);
        }
    }

    public void resetBatchEdit() {
        this.nestedBatchEdit = 0;
    }

    public void runReadActionsOnLines(int i, int i2, ContentLineConsumer2 contentLineConsumer2) {
        lock(false);
        try {
            ContentLineConsumer2.AbortFlag abortFlag = new ContentLineConsumer2.AbortFlag();
            while (i <= i2 && !abortFlag.set) {
                contentLineConsumer2.accept(i, this.lines.get(i), abortFlag);
                i++;
            }
            unlock(false);
        } catch (Throwable th) {
            unlock(false);
            throw th;
        }
    }

    public void setBidiEnabled(boolean z) {
        this.bidi.setEnabled(z);
    }

    public void setMaxUndoStackSize(int i) {
        this.undoManager.setMaxUndoStackSize(i);
    }

    public void setUndoEnabled(boolean z) {
        this.undoManager.setUndoEnabled(z);
    }

    public void setUndoManager(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    public Content subContent(int i, int i2, int i3, int i4, boolean z) {
        lock(false);
        try {
            return subContentInternal(i, i2, i3, i4, z);
        } finally {
            unlock(false);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) throws Throwable {
        Content content;
        if (i > i2) {
            throw new StringIndexOutOfBoundsException("start > end");
        }
        lock(false);
        try {
            CharPosition charPosition = getIndexer().getCharPosition(i);
            CharPosition charPosition2 = getIndexer().getCharPosition(i2);
            content = this;
            try {
                Content contentSubContentInternal = content.subContentInternal(charPosition.getLine(), charPosition.getColumn(), charPosition2.getLine(), charPosition2.getColumn(), true);
                content.unlock(false);
                return contentSubContentInternal;
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                content.unlock(false);
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            content = this;
        }
    }

    public String substring(int i, int i2) throws Throwable {
        Content content;
        if (i > i2) {
            throw new StringIndexOutOfBoundsException("start > end");
        }
        lock(false);
        try {
            CharPosition charPosition = getIndexer().getCharPosition(i);
            CharPosition charPosition2 = getIndexer().getCharPosition(i2);
            content = this;
            try {
                String string = content.subStringBuilder(charPosition.getLine(), charPosition.getColumn(), charPosition2.getLine(), charPosition2.getColumn(), (i2 - i) + 1).toString();
                content.unlock(false);
                return string;
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                content.unlock(false);
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            content = this;
        }
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return toStringBuilder().toString();
    }

    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        appendToStringBuilder(sb);
        return sb;
    }

    public TextRange undo() {
        return this.undoManager.undo(this);
    }

    public void unlock(boolean z) {
        ReadWriteLock readWriteLock = this.lock;
        if (readWriteLock == null) {
            return;
        }
        (z ? readWriteLock.writeLock() : readWriteLock.readLock()).unlock();
    }

    public Content copyTextShallow(boolean z) {
        return copyText(z, true);
    }

    public Content subContent(int i, int i2, int i3, int i4) {
        return subContent(i, i2, i3, i4, true);
    }

    public void replace(int i, int i2, CharSequence charSequence) {
        CharPosition charPosition = getIndexer().getCharPosition(i);
        CharPosition charPosition2 = getIndexer().getCharPosition(i2);
        replace(charPosition.line, charPosition.column, charPosition2.line, charPosition2.column, charSequence);
    }

    public void runReadActionsOnLines(int i, int i2, ContentLineConsumer contentLineConsumer) {
        lock(false);
        while (i <= i2) {
            try {
                ContentLine contentLine = this.lines.get(i);
                contentLineConsumer.accept(i, contentLine, this.bidi.getLineDirections(contentLine, i));
                i++;
            } catch (Throwable th) {
                unlock(false);
                throw th;
            }
        }
        unlock(false);
    }

    public char charAt(int i, int i2) {
        lock(false);
        try {
            checkLineAndColumn(i, i2, 0);
            return this.lines.get(i).charAt(i2);
        } finally {
            unlock(false);
        }
    }

    public void delete(int i, int i2, int i3, int i4) {
        lock(true);
        this.documentVersion.getAndIncrement();
        try {
            deleteInternal(i, i2, i3, i4);
        } finally {
            unlock(true);
        }
    }

    public Content copyText(boolean z) {
        return copyText(z, false);
    }

    public Content copyText() {
        return copyText(true);
    }

    public Content(CharSequence charSequence) {
        this(charSequence, true);
    }

    public Content() {
        this(null);
    }
}
