package com.reandroid.utils.io;

import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArraySort;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.io.FileIterator;
import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FileIterator implements Iterator<File> {
    public static final Comparator<File> NAME_COMPARATOR = new Comparator() { // from class: kp4
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FileIterator.a((File) obj, (File) obj2);
        }
    };
    private final Comparator<File> comparator;
    private File currentFile;
    private FileIterator currentIterator;
    private final File file;
    private final File[] files;
    private final Predicate<File> filter;
    private int index;

    public FileIterator(File file, Predicate<File> predicate, Comparator<File> comparator) {
        File[] fileArrListFiles;
        this.file = file;
        this.filter = predicate;
        this.comparator = comparator;
        if (file.isDirectory()) {
            fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && comparator != null) {
                ArraySort.sort(fileArrListFiles, comparator);
            }
        } else {
            fileArrListFiles = null;
        }
        this.files = fileArrListFiles;
        this.index = -2;
    }

    public static /* synthetic */ int a(File file, File file2) {
        boolean zIsFile = file.isFile();
        boolean zIsFile2 = file2.isFile();
        if (zIsFile && !zIsFile2) {
            return -1;
        }
        if (zIsFile || !zIsFile2) {
            return StringsUtil.toUpperCase(file.getName()).compareTo(StringsUtil.toUpperCase(file2.getName()));
        }
        return 1;
    }

    private File computeNext() {
        if (this.index == -2) {
            this.index = -1;
            File file = this.file;
            if (matchesFile(file)) {
                return file;
            }
        }
        FileIterator currentIterator = getCurrentIterator();
        if (currentIterator == null || !currentIterator.hasNext()) {
            return null;
        }
        return currentIterator.next();
    }

    private File getCurrent() {
        File file = this.currentFile;
        if (file != null) {
            return file;
        }
        File fileComputeNext = computeNext();
        this.currentFile = fileComputeNext;
        return fileComputeNext;
    }

    private FileIterator getCurrentIterator() {
        FileIterator fileIterator = this.currentIterator;
        if (fileIterator != null && fileIterator.hasNext()) {
            return fileIterator;
        }
        int i = this.index + 1;
        this.index = i;
        File[] fileArr = this.files;
        if (fileArr == null || i >= fileArr.length) {
            this.currentIterator = null;
            return null;
        }
        int length = fileArr.length;
        while (i < length) {
            fileIterator = new FileIterator(fileArr[i], this.filter, this.comparator);
            fileArr[i] = null;
            if (fileIterator.hasNext()) {
                break;
            }
            i++;
            fileIterator = null;
        }
        this.index = i;
        this.currentIterator = fileIterator;
        return fileIterator;
    }

    public static Predicate<File> getExtensionFilter(final String str) {
        return str == null ? CollectionUtil.getAcceptAll() : new Predicate() { // from class: jp4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return str.equalsIgnoreCase(FileUtil.getExtension((File) obj));
            }
        };
    }

    private boolean matchesFile(File file) {
        if (!file.isFile()) {
            return false;
        }
        Predicate<File> predicate = this.filter;
        return predicate == null || predicate.test(file);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return getCurrent() != null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public File next() {
        File current = getCurrent();
        this.currentFile = null;
        return current;
    }

    public FileIterator(File file, Predicate<File> predicate) {
        this(file, predicate, null);
    }

    public FileIterator(File file, Comparator<File> comparator) {
        this(file, null, comparator);
    }

    public FileIterator(File file) {
        this(file, null, null);
    }
}
