package com.reandroid.archive;

import com.reandroid.apk.APKLogger;
import com.reandroid.archive.ArchiveEntry;
import com.reandroid.archive.block.ApkSignatureBlock;
import com.reandroid.archive.block.EndRecord;
import com.reandroid.archive.io.ZipInput;
import com.reandroid.archive.model.CentralFileDirectory;
import com.reandroid.archive.model.LocalFileDirectory;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayIterator;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.io.FilePermissions;
import com.reandroid.utils.io.FileUtil;
import com.reandroid.utils.io.IOUtil;
import defpackage.ee0;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class Archive<T extends ZipInput> implements Closeable {
    private static final long LOG_LARGE_FILE_SIZE = 20480000;
    private final ApkSignatureBlock apkSignatureBlock;
    private final EndRecord endRecord;
    private final ArchiveEntry[] entryList;
    private final T zipInput;
    public static final int STORED = ObjectsUtil.of(0);
    public static final int DEFLATED = ObjectsUtil.of(8);

    public Archive(T t) throws IOException {
        this.zipInput = t;
        CentralFileDirectory centralFileDirectory = new CentralFileDirectory();
        centralFileDirectory.visit(t);
        this.endRecord = centralFileDirectory.getEndRecord();
        LocalFileDirectory localFileDirectory = new LocalFileDirectory(centralFileDirectory);
        localFileDirectory.visit(t);
        this.entryList = localFileDirectory.buildArchiveEntryList();
        this.apkSignatureBlock = localFileDirectory.getApkSigBlock();
    }

    private void applyAttributes(ArchiveEntry archiveEntry, File file) {
        FilePermissions filePermissions = archiveEntry.getFilePermissions();
        if (filePermissions.get() != 0) {
            filePermissions.apply(file);
        }
        file.setLastModified(dosToJavaDate(archiveEntry.getDosTime()).getTime());
    }

    public static <T1 extends InputSource> PathTree<T1> buildPathTree(T1[] t1Arr) {
        PathTree<T1> pathTreeNewRoot = PathTree.newRoot();
        for (T1 t1 : t1Arr) {
            pathTreeNewRoot.add(t1.getAlias(), t1);
        }
        return pathTreeNewRoot;
    }

    public static Date dosToJavaDate(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, ((int) ((j >> 25) & 127)) + 1980);
        calendar.set(2, ((int) ((j >> 21) & 15)) - 1);
        calendar.set(5, ((int) (j >> 16)) & 31);
        calendar.set(11, ((int) (j >> 11)) & 31);
        calendar.set(12, ((int) (j >> 5)) & 63);
        calendar.set(13, ((int) (j << 1)) & 62);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    private void extractCompressed(File file, ArchiveEntry archiveEntry) throws IOException {
        IOUtil.writeAll(openInputStream(archiveEntry), new FileOutputStream(file));
    }

    public static long javaToDosTime(Date date) {
        if (date == null || date.getTime() == 0) {
            return 0L;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        if (gregorianCalendar.get(1) < 1980) {
            return 0L;
        }
        return (((long) ((gregorianCalendar.get(5) | ((gregorianCalendar.get(2) + 1) << 5)) | ((gregorianCalendar.get(1) - 1980) << 9))) << 16) | ((long) ((gregorianCalendar.get(13) >> 1) | (gregorianCalendar.get(12) << 5) | (gregorianCalendar.get(11) << 11)));
    }

    private File toFile(File file, ArchiveEntry archiveEntry) {
        return new File(file, archiveEntry.getName().replace('/', File.separatorChar));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.zipInput.close();
    }

    public abstract InputSource createInputSource(ArchiveEntry archiveEntry);

    public ZipEntryMap createZipEntryMap() {
        return new ZipEntryMap(mapEntrySource());
    }

    public void extract(File file, ArchiveEntry archiveEntry, APKLogger aPKLogger) throws IOException {
        if (archiveEntry.isDirectory()) {
            return;
        }
        FileUtil.ensureParentDirectory(file);
        if (aPKLogger != null) {
            long dataSize = archiveEntry.getDataSize();
            if (dataSize > LOG_LARGE_FILE_SIZE) {
                aPKLogger.logVerbose("Extracting [" + FileUtil.toReadableFileSize(dataSize) + "] " + archiveEntry.getName());
            }
        }
        if (archiveEntry.getMethod() != STORED) {
            extractCompressed(file, archiveEntry);
        } else {
            extractStored(file, archiveEntry);
        }
        applyAttributes(archiveEntry, file);
    }

    public int extractAll(File file, Predicate<ArchiveEntry> predicate, APKLogger aPKLogger) throws IOException {
        Iterator<ArchiveEntry> it = iterator(predicate);
        int i = 0;
        while (it.hasNext()) {
            ArchiveEntry next = it.next();
            extract(toFile(file, next), next, aPKLogger);
            i++;
        }
        return i;
    }

    public abstract void extractStored(File file, ArchiveEntry archiveEntry) throws IOException;

    public ApkSignatureBlock getApkSignatureBlock() {
        return this.apkSignatureBlock;
    }

    public EndRecord getEndRecord() {
        return this.endRecord;
    }

    public InputSource getEntrySource(String str) {
        if (str == null) {
            return null;
        }
        for (ArchiveEntry archiveEntry : this.entryList) {
            if (!archiveEntry.isDirectory() && str.equals(archiveEntry.getName())) {
                return createInputSource(archiveEntry);
            }
        }
        return null;
    }

    public Iterator<ArchiveEntry> getFiles() {
        return iterator(new ee0());
    }

    public InputSource[] getInputSources(Predicate<? super ArchiveEntry> predicate) {
        List list = CollectionUtil.toList(ComputeIterator.of(iterator(predicate), new Function() { // from class: fe0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.createInputSource((ArchiveEntry) obj);
            }
        }));
        return (InputSource[]) list.toArray(new InputSource[list.size()]);
    }

    public PathTree<InputSource> getPathTree() {
        PathTree<InputSource> pathTreeNewRoot = PathTree.newRoot();
        Iterator<ArchiveEntry> files = getFiles();
        while (files.hasNext()) {
            InputSource inputSourceCreateInputSource = createInputSource(files.next());
            pathTreeNewRoot.add(inputSourceCreateInputSource.getAlias(), inputSourceCreateInputSource);
        }
        return pathTreeNewRoot;
    }

    public T getZipInput() {
        return this.zipInput;
    }

    public Iterator<ArchiveEntry> iterator() {
        return new ArrayIterator(this.entryList);
    }

    public LinkedHashMap<String, InputSource> mapEntrySource() {
        LinkedHashMap<String, InputSource> linkedHashMap = new LinkedHashMap<>(size());
        Iterator<ArchiveEntry> files = getFiles();
        while (files.hasNext()) {
            InputSource inputSourceCreateInputSource = createInputSource(files.next());
            linkedHashMap.put(inputSourceCreateInputSource.getAlias(), inputSourceCreateInputSource);
        }
        return linkedHashMap;
    }

    public InputStream openInputStream(ArchiveEntry archiveEntry) throws IOException {
        InputStream inputStreamOpenRawInputStream = openRawInputStream(archiveEntry);
        return !archiveEntry.isCompressed() ? inputStreamOpenRawInputStream : new InflaterInputStream(inputStreamOpenRawInputStream, new Inflater(true), 1024000);
    }

    public InputStream openRawInputStream(ArchiveEntry archiveEntry) throws IOException {
        return this.zipInput.getInputStream(archiveEntry.getFileOffset(), archiveEntry.getDataSize());
    }

    public int size() {
        return this.entryList.length;
    }

    public Iterator<ArchiveEntry> iterator(Predicate<? super ArchiveEntry> predicate) {
        return new ArrayIterator(this.entryList, predicate);
    }

    public int extractAll(File file, APKLogger aPKLogger) throws IOException {
        return extractAll(file, null, aPKLogger);
    }

    public int extractAll(File file, Predicate<ArchiveEntry> predicate) throws IOException {
        return extractAll(file, predicate, null);
    }

    public int extractAll(File file) throws IOException {
        return extractAll(file, null, null);
    }

    public InputSource[] getInputSources() {
        return getInputSources(new ee0());
    }

    public void extract(File file, ArchiveEntry archiveEntry) throws IOException {
        extract(file, archiveEntry, null);
    }

    public static long javaToDosTime(long j) {
        return javaToDosTime(new Date(j));
    }
}
