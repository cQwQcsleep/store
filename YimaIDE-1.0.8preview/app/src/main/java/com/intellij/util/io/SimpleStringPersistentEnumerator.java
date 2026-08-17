package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ArrayUtilRt;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SimpleStringPersistentEnumerator implements ScannableDataEnumeratorEx, Closeable {
    private static final Logger LOG = Logger.getInstance(SimpleStringPersistentEnumerator.class);
    private final Charset charset;
    private volatile boolean closed;
    private final Path file;
    private volatile String[] idToValue;
    private volatile Object2IntMap<String> valueToId;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 5 || i == 9) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 5 || i == 9) ? 2 : 3];
        switch (i) {
            case 2:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 11:
                objArr[0] = "charset";
                break;
            case 3:
            case 5:
            case 9:
                objArr[0] = "com/intellij/util/io/SimpleStringPersistentEnumerator";
                break;
            case 4:
                objArr[0] = "reader";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 10:
            default:
                objArr[0] = "file";
                break;
            case 8:
                objArr[0] = "charsetToFallback";
                break;
        }
        if (i == 3) {
            objArr[1] = "getFile";
        } else if (i == 5) {
            objArr[1] = "dumpToString";
        } else if (i != 9) {
            objArr[1] = "com/intellij/util/io/SimpleStringPersistentEnumerator";
        } else {
            objArr[1] = "readStorageFromDisk";
        }
        switch (i) {
            case 3:
            case 5:
            case 9:
                break;
            case 4:
                objArr[2] = "forEach";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "readStorageFromDisk";
                break;
            case 10:
            case 11:
                objArr[2] = "writeStorageToDisk";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 5 && i != 9) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public SimpleStringPersistentEnumerator(Path path, Charset charset) {
        Pair<Object2IntMap<String>, String[]> storageFromDisk;
        if (path == null) {
            $$$reportNull$$$0(1);
        }
        if (charset == null) {
            $$$reportNull$$$0(2);
        }
        this.closed = false;
        this.file = path;
        this.charset = charset;
        try {
            if (Files.notExists(path, new LinkOption[0])) {
                Files.createDirectories(path.getParent(), new FileAttribute[0]);
                Files.createFile(path, new FileAttribute[0]);
            }
            try {
                storageFromDisk = readStorageFromDisk(path, charset, Charset.defaultCharset());
            } catch (IOException e) {
                LOG.warnWithDebug("Can't read [" + path.toAbsolutePath() + "] content", e);
                Files.write(path, ArrayUtilRt.EMPTY_BYTE_ARRAY, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
                storageFromDisk = readStorageFromDisk(path, charset, Charset.defaultCharset());
            }
            synchronized (this) {
                this.valueToId = (Object2IntMap) storageFromDisk.getFirst();
                this.idToValue = (String[]) storageFromDisk.getSecond();
            }
        } catch (IOException e2) {
            throw new UncheckedIOException("Can't create file [" + path + "]", e2);
        }
    }

    private void checkNotClosed() {
        if (this.closed) {
            k2d.a("Storage already closed");
        }
    }

    private synchronized int insertNewValue(String str) {
        if (str != null) {
            try {
                if (StringUtil.containsLineBreak(str)) {
                    throw new IllegalArgumentException("SimpleStringPersistentEnumerator doesn't support multi-line strings: [" + str + "]");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        int i = this.valueToId.getInt(str);
        if (i != this.valueToId.defaultReturnValue()) {
            return i;
        }
        int length = this.idToValue.length;
        int i2 = length + 1;
        String[] strArr = (String[]) Arrays.copyOf(this.idToValue, this.idToValue.length + 1);
        strArr[length] = str;
        this.idToValue = strArr;
        Object2IntOpenHashMap object2IntOpenHashMap = new Object2IntOpenHashMap(this.valueToId);
        object2IntOpenHashMap.put(str, i2);
        this.valueToId = object2IntOpenHashMap;
        forceDiskSync();
        return i2;
    }

    private static Pair<Object2IntMap<String>, String[]> readStorageFromDisk(Path path, Charset charset, Charset charset2) throws IOException {
        List<String> allLines;
        if (path == null) {
            $$$reportNull$$$0(6);
        }
        if (charset == null) {
            $$$reportNull$$$0(7);
        }
        if (charset2 == null) {
            $$$reportNull$$$0(8);
        }
        try {
            allLines = Files.readAllLines(path, charset);
        } catch (IOException e) {
            try {
                allLines = Files.readAllLines(path, charset2);
            } catch (IOException e2) {
                e2.addSuppressed(e);
                throw e2;
            }
        }
        Object2IntOpenHashMap object2IntOpenHashMap = new Object2IntOpenHashMap(allLines.size());
        String[] strArr = allLines.isEmpty() ? ArrayUtilRt.EMPTY_STRING_ARRAY : new String[allLines.size()];
        int i = 0;
        while (i < allLines.size()) {
            String str = allLines.get(i);
            int i2 = i + 1;
            object2IntOpenHashMap.put(str, i2);
            strArr[i] = str;
            i = i2;
        }
        Pair<Object2IntMap<String>, String[]> pairCreate = Pair.create(object2IntOpenHashMap, strArr);
        if (pairCreate == null) {
            $$$reportNull$$$0(9);
        }
        return pairCreate;
    }

    private static void writeStorageToDisk(String[] strArr, Path path, Charset charset) {
        if (path == null) {
            $$$reportNull$$$0(10);
        }
        if (charset == null) {
            $$$reportNull$$$0(11);
        }
        try {
            Files.write(path, Arrays.asList(strArr), charset, StandardOpenOption.WRITE);
        } catch (IOException e) {
            if (!Files.notExists(path, new LinkOption[0])) {
                throw new UncheckedIOException("Can't store enumerator to " + path, e);
            }
            throw new UncheckedIOException("Can't store enumerator to " + path + " -- file is removed?", e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.closed = true;
    }

    @Override // com.intellij.util.io.DataEnumerator
    public int enumerate(String str) {
        checkNotClosed();
        Object2IntMap<String> object2IntMap = this.valueToId;
        int i = object2IntMap.getInt(str);
        return i != object2IntMap.defaultReturnValue() ? i : insertNewValue(str);
    }

    public synchronized void forceDiskSync() {
        checkNotClosed();
        writeStorageToDisk(this.idToValue, this.file, this.charset);
    }

    public Path getFile() {
        Path path = this.file;
        if (path == null) {
            $$$reportNull$$$0(3);
        }
        return path;
    }

    public Map<String, Integer> getInvertedState() {
        checkNotClosed();
        return new HashMap((Map) this.valueToId);
    }

    @Override // com.intellij.util.io.DataEnumerator
    public String valueOf(int i) {
        checkNotClosed();
        String[] strArr = this.idToValue;
        if (i <= 0 || i > strArr.length) {
            return null;
        }
        return strArr[i - 1];
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SimpleStringPersistentEnumerator(Path path) {
        this(path, StandardCharsets.UTF_8);
        if (path == null) {
            $$$reportNull$$$0(0);
        }
    }
}
