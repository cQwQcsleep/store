package com.reandroid.dex.model;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.sections.DexContainerBlock;
import com.reandroid.dex.sections.MergeOptions;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.io.FileUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexFile implements Closeable, DexClassRepository, Iterable<DexLayout> {
    private boolean closed;
    private final DexContainerBlock containerBlock;
    private DexDirectory dexDirectory;
    private final DexFileLayoutController layoutController;

    public DexFile(DexContainerBlock dexContainerBlock) {
        this.containerBlock = dexContainerBlock;
        DexFileLayoutController dexFileLayoutController = new DexFileLayoutController(this);
        this.layoutController = dexFileLayoutController;
        dexContainerBlock.setLayoutBlockChangedListener(dexFileLayoutController);
    }

    public static DexFile createDefault() {
        return new DexFile(DexContainerBlock.createDefault());
    }

    public static DexFile createNew() {
        return new DexFile(new DexContainerBlock());
    }

    public static int getDexFileNumber(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            iLastIndexOf = str.lastIndexOf(92);
        }
        if (iLastIndexOf >= 0) {
            str = str.substring(iLastIndexOf + 1);
        }
        if (str.equals("classes.dex")) {
            return 0;
        }
        if (str.startsWith("classes") && str.endsWith(".dex")) {
            try {
                return Integer.parseInt(str.substring(7, str.length() - 4));
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }

    public static String getDexName(int i) {
        if (i == 0) {
            return "classes.dex";
        }
        if (i == 1) {
            i = 2;
        }
        return "classes" + i + ".dex";
    }

    private boolean isLayoutDirectory(File file) {
        if (!file.isDirectory()) {
            return false;
        }
        String name = file.getName();
        String str = DexLayout.DIRECTORY_PREFIX;
        if (!name.startsWith(str)) {
            return false;
        }
        try {
            return Integer.parseInt(name.substring(str.length())) >= 0;
        } catch (NumberFormatException unused) {
        }
    }

    private List<File> listSmaliLayouts(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return null;
        }
        ArrayCollection arrayCollection = new ArrayCollection();
        for (File file2 : fileArrListFiles) {
            if (file2.isFile()) {
                if (!file2.getName().equals(DexFileInfo.FILE_NAME)) {
                    return null;
                }
            } else {
                if (!isLayoutDirectory(file2)) {
                    return null;
                }
                arrayCollection.add(file2);
            }
        }
        if (arrayCollection.isEmpty()) {
            return null;
        }
        arrayCollection.sort(CompareUtil.getToStringComparator());
        return arrayCollection;
    }

    public static DexFile read(BlockReader blockReader, Predicate<SectionType<?>> predicate) throws IOException {
        DexFile dexFile = new DexFile(new DexContainerBlock());
        dexFile.readBytes(blockReader, predicate);
        return dexFile;
    }

    private void requireNotClosed() throws IOException {
        if (isClosed()) {
            a16.a("Closed");
        }
    }

    public String buildSmaliDirectoryName() {
        String simpleName = getSimpleName();
        int i = 0;
        if (simpleName != null && simpleName.endsWith(".dex")) {
            return simpleName.substring(0, simpleName.length() - 4);
        }
        DexDirectory dexDirectory = getDexDirectory();
        if (dexDirectory == null) {
            return "classes";
        }
        Iterator<DexFile> it = dexDirectory.iterator();
        while (it.hasNext() && it.next() != this) {
            i++;
        }
        if (i == 0) {
            return "classes";
        }
        return "classes" + (i + 1);
    }

    public int clearDuplicateData() {
        Iterator<DexLayout> it = iterator();
        int iClearDuplicateData = 0;
        while (it.hasNext()) {
            iClearDuplicateData += it.next().clearDuplicateData();
        }
        return iClearDuplicateData;
    }

    public void clearEmptySections() {
        Iterator<DexLayout> it = iterator();
        while (it.hasNext()) {
            it.next().clearEmptySections();
        }
    }

    public int clearUnused() {
        Iterator<DexLayout> it = iterator();
        int iClearUnused = 0;
        while (it.hasNext()) {
            iClearUnused += it.next().clearUnused();
        }
        return iClearUnused;
    }

    public Iterator<DexLayout> clonedIterator() {
        return this.layoutController.iterator();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
        int size = size();
        for (int i = 0; i < size; i++) {
            DexLayout layout = getLayout(i);
            if (layout != null) {
                layout.close();
            }
        }
        getContainerBlock().clear();
    }

    public void combineFrom(DexLayout dexLayout) {
        getContainerBlock().combineFrom(dexLayout.getDexLayoutBlock());
    }

    public byte[] getBytes() {
        return isEmpty() ? new byte[0] : getContainerBlock().getBytes();
    }

    public DexContainerBlock getContainerBlock() {
        return this.containerBlock;
    }

    public int getDexClassesCountForDebug() {
        int size = size();
        int dexClassesCountForDebug = 0;
        for (int i = 0; i < size; i++) {
            dexClassesCountForDebug += getLayout(i).getDexClassesCountForDebug();
        }
        return dexClassesCountForDebug;
    }

    public DexDirectory getDexDirectory() {
        return this.dexDirectory;
    }

    public Iterator<DexInstruction> getDexInstructions() {
        return new IterableIterator<DexLayout, DexInstruction>(iterator()) { // from class: com.reandroid.dex.model.DexFile.1
            public Iterator<DexInstruction> iterator(DexLayout dexLayout) {
                return dexLayout.getDexInstructions();
            }
        };
    }

    public Iterator<DexInstruction> getDexInstructionsCloned() {
        return new IterableIterator<DexLayout, DexInstruction>(iterator()) { // from class: com.reandroid.dex.model.DexFile.2
            public Iterator<DexInstruction> iterator(DexLayout dexLayout) {
                return dexLayout.getDexInstructionsCloned();
            }
        };
    }

    public String getFileName() {
        String simpleName = getSimpleName();
        if (simpleName != null) {
            return FileUtil.getFileName(simpleName);
        }
        return buildSmaliDirectoryName() + ".dex";
    }

    public DexLayout getFirst() {
        return this.layoutController.get(0);
    }

    public int getIndex() {
        DexDirectory dexDirectory = getDexDirectory();
        if (dexDirectory != null) {
            return dexDirectory.indexOf(this);
        }
        return -1;
    }

    public DexLayout getLayout(int i) {
        return this.layoutController.get(i);
    }

    public DexLayout getOrCreateAt(int i) {
        getContainerBlock().ensureSize(i + 1);
        return getLayout(i);
    }

    public DexLayout getOrCreateFirst() {
        return getOrCreateAt(0);
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    public DexClassRepository getRootRepository() {
        DexDirectory dexDirectory = getDexDirectory();
        return dexDirectory != null ? dexDirectory.getRootRepository() : this;
    }

    public String getSimpleName() {
        return getContainerBlock().getSimpleName();
    }

    public DexSource<DexFile> getSource() {
        DexDirectory dexDirectory = getDexDirectory();
        if (dexDirectory != null) {
            return dexDirectory.getDexSourceSet().getSource(this);
        }
        return null;
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    public int getVersion() {
        return getContainerBlock().getVersion();
    }

    public boolean isClosed() {
        return this.closed;
    }

    public boolean isEmpty() {
        return getContainerBlock().isEmpty();
    }

    public boolean isMultiLayout() {
        return getContainerBlock().isMultiLayout();
    }

    @Override // java.lang.Iterable
    public Iterator<DexLayout> iterator() {
        return this.layoutController.iterator();
    }

    public boolean merge(MergeOptions mergeOptions, DexFile dexFile) {
        DexLayout first;
        if (dexFile == null || (first = dexFile.getFirst()) == null) {
            return false;
        }
        return getOrCreateFirst().merge(mergeOptions, first);
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    public Iterator<DexClassModule> modules() {
        return (Iterator) ObjectsUtil.cast(iterator());
    }

    public void parseSmaliDirectory(File file) throws IOException {
        File file2 = new File(file, DexFileInfo.FILE_NAME);
        if (file2.isFile()) {
            DexFileInfo.readJson(file2).applyTo(this);
        }
        List<File> listListSmaliLayouts = listSmaliLayouts(file);
        if (listListSmaliLayouts == null) {
            getOrCreateFirst().parseSmaliDirectory(file);
            return;
        }
        int size = listListSmaliLayouts.size();
        for (int i = 0; i < size; i++) {
            getOrCreateAt(i).parseSmaliDirectory(listListSmaliLayouts.get(i));
        }
    }

    public String printSectionInfo(boolean z) {
        int size = size();
        if (size == 0) {
            return "no layouts";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append('\n');
            }
            sb.append(getLayout(i).printSectionInfo(z));
        }
        return sb.toString();
    }

    public void readBytes(BlockReader blockReader) throws IOException {
        getContainerBlock().readBytes(blockReader);
    }

    public void refresh() {
        getContainerBlock().refresh();
        this.layoutController.refreshController();
    }

    @Override // com.reandroid.dex.common.FullRefresh
    public void refreshFull() {
        this.layoutController.refreshController();
        getContainerBlock().refreshFull();
        this.layoutController.refreshController();
    }

    public void setDexDirectory(DexDirectory dexDirectory) {
        this.dexDirectory = dexDirectory;
        DexContainerBlock containerBlock = getContainerBlock();
        containerBlock.setTag(this);
        containerBlock.setSimpleName(getSimpleName());
    }

    public void setSimpleName(String str) {
        getContainerBlock().setSimpleName(str);
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    public void setVersion(int i) {
        getContainerBlock().setVersion(i);
    }

    public int size() {
        return getContainerBlock().size();
    }

    public String toString() {
        int size = size();
        if (size == 0) {
            return "EMPTY DEX FILE";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append('\n');
            }
            sb.append(getLayout(i));
        }
        if (isMultiLayout()) {
            sb.append("\ntotal classes = ");
            sb.append(getDexClassesCountForDebug());
        }
        return sb.toString();
    }

    public void write(File file) throws IOException {
        OutputStream outputStream = FileUtil.outputStream(file);
        write(outputStream);
        outputStream.close();
    }

    public void writeSmali(SmaliWriter smaliWriter, File file) throws IOException {
        requireNotClosed();
        File file2 = new File(file, buildSmaliDirectoryName());
        DexFileInfo.fromDex(this).saveToDirectory(file2);
        if (!isMultiLayout()) {
            DexLayout first = getFirst();
            if (first != null) {
                first.writeSmali(smaliWriter, file2);
                return;
            }
            return;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            getLayout(i).writeSmali(smaliWriter, new File(file2, DexLayout.DIRECTORY_PREFIX + i));
        }
    }

    public void readBytes(BlockReader blockReader, Predicate<SectionType<?>> predicate) throws IOException {
        getContainerBlock().readBytes(blockReader, predicate);
    }

    public void write(OutputStream outputStream) throws IOException {
        getContainerBlock().writeBytes(outputStream);
    }

    public void combineFrom(MergeOptions mergeOptions, DexLayout dexLayout) {
        getContainerBlock().combineFrom(mergeOptions, dexLayout.getDexLayoutBlock());
    }

    public void combineFrom(DexFile dexFile) {
        getContainerBlock().combineFrom(dexFile.getContainerBlock());
    }

    public static DexFile read(File file) throws IOException {
        return read(file, (Predicate<SectionType<?>>) null);
    }

    public void combineFrom(MergeOptions mergeOptions, DexFile dexFile) {
        getContainerBlock().combineFrom(mergeOptions, dexFile.getContainerBlock());
    }

    public static DexFile read(InputStream inputStream) throws IOException {
        return read(inputStream, (Predicate<SectionType<?>>) null);
    }

    public static DexFile read(BlockReader blockReader) throws IOException {
        return read(blockReader, (Predicate<SectionType<?>>) null);
    }

    public static DexFile read(byte[] bArr, Predicate<SectionType<?>> predicate) throws IOException {
        return read(new BlockReader(bArr), predicate);
    }

    public static DexFile read(File file, Predicate<SectionType<?>> predicate) throws IOException {
        return read(new BlockReader(file), predicate);
    }

    public static DexFile read(InputStream inputStream, Predicate<SectionType<?>> predicate) throws IOException {
        return read(new BlockReader(inputStream), predicate);
    }

    public static DexFile read(byte[] bArr) throws IOException {
        return read(bArr, (Predicate<SectionType<?>>) null);
    }

    public boolean merge(MergeOptions mergeOptions, DexClass dexClass) {
        return merge(mergeOptions, dexClass.getId());
    }

    public boolean merge(ClassId classId) {
        return merge(new DexMergeOptions(true), classId);
    }

    public boolean merge(MergeOptions mergeOptions, ClassId classId) {
        return getOrCreateFirst().merge(mergeOptions, classId);
    }

    public boolean merge(DexClass dexClass) {
        return merge(new DexMergeOptions(true), dexClass);
    }

    public boolean merge(MergeOptions mergeOptions, DexLayout dexLayout) {
        if (dexLayout == null || dexLayout.isEmpty()) {
            return false;
        }
        return getOrCreateFirst().merge(mergeOptions, dexLayout);
    }
}
