package io.github.rosemoe.sora.lang.styling.util;

import io.github.rosemoe.sora.lang.styling.util.PointAnchoredObject;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\t\b\u0016\u0018\u0000 \u001c*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0002\u001c\u001dB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0002J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0002J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0002J\u0013\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\u0006\u0010\u0013\u001a\u00020\tJ\u0006\u0010\u0014\u001a\u00020\u0015J&\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tJ&\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/util/PointAnchoredContainer;", "T", "Lio/github/rosemoe/sora/lang/styling/util/PointAnchoredObject;", "", "<init>", "()V", "objects", "", "getInsertionPoint", "", "e", "getInsertionPointLast", "getInsertionPointFirst", "add", "", "(Lio/github/rosemoe/sora/lang/styling/util/PointAnchoredObject;)V", "remove", "getForLine", "", "line", "getLineNumbers", "", "updateOnInsertion", "startLine", "startColumn", "endLine", "endColumn", "updateOnDeletion", "Companion", "Anchor", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public class PointAnchoredContainer<T extends PointAnchoredObject> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Comparator<PointAnchoredObject> comparator = new Comparator<PointAnchoredObject>() { // from class: io.github.rosemoe.sora.lang.styling.util.PointAnchoredContainer$Companion$comparator$1
        @Override // java.util.Comparator
        public int compare(PointAnchoredObject o1, PointAnchoredObject o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            int iCompare = Intrinsics.compare(o1.getLine(), o2.getLine());
            return iCompare != 0 ? iCompare : Intrinsics.compare(o1.getColumn(), o2.getColumn());
        }
    };
    private final List<T> objects = new ArrayList();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/util/PointAnchoredContainer$Anchor;", "Lio/github/rosemoe/sora/lang/styling/util/PointAnchoredObject;", "line", "", "column", "<init>", "(II)V", "getLine", "()I", "setLine", "(I)V", "getColumn", "setColumn", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public static final class Anchor implements PointAnchoredObject {
        private int column;
        private int line;

        public Anchor(int i, int i2) {
            this.line = i;
            this.column = i2;
        }

        @Override // io.github.rosemoe.sora.lang.styling.util.PointAnchoredObject
        public int getColumn() {
            return this.column;
        }

        @Override // io.github.rosemoe.sora.lang.styling.util.PointAnchoredObject
        public int getLine() {
            return this.line;
        }

        @Override // io.github.rosemoe.sora.lang.styling.util.PointAnchoredObject
        public void setColumn(int i) {
            this.column = i;
        }

        @Override // io.github.rosemoe.sora.lang.styling.util.PointAnchoredObject
        public void setLine(int i) {
            this.line = i;
        }
    }

    private final int getInsertionPoint(PointAnchoredObject e) {
        int iBinarySearch$default = CollectionsKt.binarySearch$default(this.objects, e, comparator, 0, 0, 12, null);
        return iBinarySearch$default < 0 ? -(iBinarySearch$default + 1) : iBinarySearch$default;
    }

    private final int getInsertionPointFirst(PointAnchoredObject e) {
        int insertionPoint = getInsertionPoint(e);
        if (insertionPoint == this.objects.size()) {
            return insertionPoint;
        }
        while (true) {
            int i = insertionPoint - 1;
            if (i <= 0) {
                break;
            }
            if (comparator.compare(this.objects.get(insertionPoint), this.objects.get(i)) != 0) {
                break;
            }
            insertionPoint--;
        }
        return insertionPoint;
    }

    private final int getInsertionPointLast(PointAnchoredObject e) {
        int insertionPoint = getInsertionPoint(e);
        while (true) {
            int i = insertionPoint + 1;
            if (i >= this.objects.size()) {
                break;
            }
            if (comparator.compare(this.objects.get(insertionPoint), this.objects.get(i)) != 0) {
                break;
            }
            insertionPoint = i;
        }
        return insertionPoint;
    }

    public final void add(T e) {
        e.getClass();
        this.objects.add(getInsertionPointLast(e), e);
    }

    public final List<T> getForLine(int line) {
        int insertionPointFirst = getInsertionPointFirst(new Anchor(line, 0));
        if (insertionPointFirst >= this.objects.size() || this.objects.get(insertionPointFirst).getLine() > line) {
            return CollectionsKt.emptyList();
        }
        int i = insertionPointFirst;
        while (i < this.objects.size() && this.objects.get(i).getLine() == line) {
            i++;
        }
        return new ArrayList(this.objects.subList(insertionPointFirst, i));
    }

    public final int[] getLineNumbers() {
        List<T> list = this.objects;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(Integer.valueOf(((PointAnchoredObject) it2.next()).getLine()));
        }
        return CollectionsKt.toIntArray(CollectionsKt.distinct(arrayList));
    }

    public final void remove(T e) {
        e.getClass();
        int insertionPointFirst = getInsertionPointFirst(e);
        int insertionPointLast = getInsertionPointLast(e);
        if (insertionPointFirst > insertionPointLast) {
            return;
        }
        while (true) {
            if (insertionPointFirst < this.objects.size() && Intrinsics.areEqual(this.objects.get(insertionPointFirst), e)) {
                this.objects.remove(insertionPointFirst);
                return;
            } else if (insertionPointFirst == insertionPointLast) {
                return;
            } else {
                insertionPointFirst++;
            }
        }
    }

    public final void updateOnDeletion(int startLine, int startColumn, int endLine, int endColumn) {
        int insertionPointFirst = getInsertionPointFirst(new Anchor(startLine, startColumn));
        int i = endLine - startLine;
        int i2 = -1;
        for (int i3 = insertionPointFirst; i3 < this.objects.size(); i3++) {
            T t = this.objects.get(i3);
            if (t.getLine() < endLine || (t.getLine() == endLine && t.getColumn() < endColumn)) {
                i2 = i3;
            } else if (t.getLine() == endLine) {
                t.setColumn(t.getColumn() - (startLine == endLine ? endColumn - startColumn : endColumn));
            } else if (i == 0) {
                break;
            } else {
                t.setLine(t.getLine() - i);
            }
        }
        if (i2 != -1) {
            this.objects.subList(insertionPointFirst, i2 + 1).clear();
        }
    }

    public final void updateOnInsertion(int startLine, int startColumn, int endLine, int endColumn) {
        int i = endLine - startLine;
        for (int insertionPointFirst = getInsertionPointFirst(new Anchor(startLine, startColumn)); insertionPointFirst < this.objects.size(); insertionPointFirst++) {
            T t = this.objects.get(insertionPointFirst);
            if (t.getLine() == startLine) {
                t.setLine(endLine);
                t.setColumn((t.getColumn() - startColumn) + endColumn);
            } else if (i == 0) {
                return;
            } else {
                t.setLine(t.getLine() + i);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/util/PointAnchoredContainer$Companion;", "", "<init>", "()V", "comparator", "Ljava/util/Comparator;", "Lio/github/rosemoe/sora/lang/styling/util/PointAnchoredObject;", "Lkotlin/Comparator;", "getComparator", "()Ljava/util/Comparator;", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Comparator<PointAnchoredObject> getComparator() {
            return PointAnchoredContainer.comparator;
        }

        private Companion() {
        }
    }
}
