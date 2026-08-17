package com.reandroid.dex.model;

import com.reandroid.archive.ByteInputSource;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.utils.io.FileUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DexSource<T> extends Comparable<DexSource<?>>, Closeable {

    public static abstract class DexSourceImpl<T> implements DexSource<T> {
        private boolean closed;
        private T item;

        @Override // com.reandroid.dex.model.DexSource, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.closed = true;
            T t = this.item;
            this.item = null;
            if (t instanceof Closeable) {
                ((Closeable) t).close();
            }
        }

        @Override // com.reandroid.dex.model.DexSource
        public boolean delete() {
            set(null);
            return onDelete();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DexSource) && getDexFileNumber() == ((DexSource) obj).getDexFileNumber();
        }

        @Override // com.reandroid.dex.model.DexSource
        public T get() {
            return this.item;
        }

        public int hashCode() {
            return getDexFileNumber();
        }

        @Override // com.reandroid.dex.model.DexSource
        public boolean isClosed() {
            return this.closed;
        }

        public abstract boolean onDelete();

        @Override // com.reandroid.dex.model.DexSource
        public void set(T t) {
            if (isClosed()) {
                return;
            }
            this.item = t;
        }

        public String toString() {
            return getSimpleName();
        }
    }

    static <T> DexSource<T> create(ZipEntryMap zipEntryMap, String str) {
        return new ZipDexSource(zipEntryMap, str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    @Override // java.lang.Comparable
    default int compareTo(DexSource<?> dexSource) {
        return Integer.compare(getDexFileNumber(), dexSource.getDexFileNumber());
    }

    default DexSource<T> createNext() {
        throw new RuntimeException("Method not implemented");
    }

    boolean delete();

    T get();

    default int getDexFileNumber() {
        return DexFile.getDexFileNumber(getName());
    }

    String getName();

    default String getSimpleName() {
        String name = getName();
        int iLastIndexOf = name.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            iLastIndexOf = name.lastIndexOf(92);
        }
        return iLastIndexOf >= 0 ? name.substring(iLastIndexOf + 1) : name;
    }

    default DexSource<T> initializeNew() {
        throw new RuntimeException("Method not implemented");
    }

    boolean isClosed();

    InputStream openStream() throws IOException;

    void set(T t);

    void write(byte[] bArr) throws IOException;

    static <T> DexSource<T> create(ZipEntryMap zipEntryMap, String str, T t) {
        return new ZipDexSource(zipEntryMap, str, t);
    }

    public static class ZipDexSource<T> extends DexSourceImpl<T> {
        private final String name;
        private final ZipEntryMap zipEntryMap;

        public ZipDexSource(ZipEntryMap zipEntryMap, String str) {
            this.zipEntryMap = zipEntryMap;
            this.name = str;
        }

        private String getPath(int i) {
            return FileUtil.combineUnixPath(FileUtil.getParent(getName()), DexFile.getDexName(i));
        }

        @Override // com.reandroid.dex.model.DexSource
        public ZipDexSource<T> createNext() {
            if (isClosed()) {
                return null;
            }
            int dexFileNumber = getDexFileNumber() + 1;
            String path = getPath(dexFileNumber);
            ZipEntryMap zipEntryMap = this.zipEntryMap;
            while (zipEntryMap.contains(path)) {
                dexFileNumber++;
                path = getPath(dexFileNumber);
            }
            return new ZipDexSource<>(zipEntryMap, path);
        }

        @Override // com.reandroid.dex.model.DexSource
        public String getName() {
            return this.name;
        }

        @Override // com.reandroid.dex.model.DexSource.DexSourceImpl
        public boolean onDelete() {
            if (isClosed()) {
                return false;
            }
            this.zipEntryMap.remove(getName());
            return true;
        }

        @Override // com.reandroid.dex.model.DexSource
        public InputStream openStream() throws IOException {
            if (isClosed()) {
                u8g.a("Closed: ", getName());
                return null;
            }
            InputSource inputSource = this.zipEntryMap.getInputSource(getName());
            if (inputSource != null) {
                return inputSource.openStream();
            }
            u8g.a("Zip input source not found: ", getName());
            return null;
        }

        @Override // com.reandroid.dex.model.DexSource.DexSourceImpl
        public String toString() {
            return this.zipEntryMap.getModuleName() + ":/" + getSimpleName();
        }

        @Override // com.reandroid.dex.model.DexSource
        public void write(byte[] bArr) throws IOException {
            if (isClosed()) {
                u8g.a("Closed: ", getName());
            } else {
                this.zipEntryMap.add(new ByteInputSource(bArr, getName()));
            }
        }

        public ZipDexSource(ZipEntryMap zipEntryMap, String str, T t) {
            this(zipEntryMap, str);
            set(t);
        }
    }

    static <T> DexSource<T> create(File file) {
        return new FileDexSource(file);
    }

    public static class FileDexSource<T> extends DexSourceImpl<T> {
        private final File file;

        public FileDexSource(File file) {
            this.file = file;
        }

        private File getFile(int i) {
            String dexName = DexFile.getDexName(i);
            File parentFile = getFile().getParentFile();
            return parentFile == null ? new File(dexName) : new File(parentFile, dexName);
        }

        @Override // com.reandroid.dex.model.DexSource
        public FileDexSource<T> createNext() {
            if (isClosed()) {
                return null;
            }
            int dexFileNumber = getDexFileNumber() + 1;
            File file = getFile(dexFileNumber);
            while (file.isFile()) {
                dexFileNumber++;
                file = getFile(dexFileNumber);
            }
            return new FileDexSource<>(file);
        }

        @Override // com.reandroid.dex.model.DexSource
        public String getName() {
            return getFile().getAbsolutePath();
        }

        @Override // com.reandroid.dex.model.DexSource
        public String getSimpleName() {
            return getFile().getName();
        }

        @Override // com.reandroid.dex.model.DexSource
        public DexSource<T> initializeNew() {
            return null;
        }

        @Override // com.reandroid.dex.model.DexSource.DexSourceImpl
        public boolean onDelete() {
            if (isClosed()) {
                return false;
            }
            File file = getFile();
            if (file.isFile()) {
                return file.delete();
            }
            return true;
        }

        @Override // com.reandroid.dex.model.DexSource
        public InputStream openStream() throws IOException {
            if (!isClosed()) {
                return new FileInputStream(getFile());
            }
            u8g.a("Closed: ", getName());
            return null;
        }

        @Override // com.reandroid.dex.model.DexSource
        public void write(byte[] bArr) throws IOException {
            if (isClosed()) {
                u8g.a("Closed: ", getName());
                return;
            }
            OutputStream outputStream = FileUtil.outputStream(getFile());
            outputStream.write(bArr, 0, bArr.length);
            outputStream.close();
        }

        public File getFile() {
            return this.file;
        }
    }
}
