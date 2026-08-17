package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationKt;
import androidx.compose.foundation.text.input.internal.PartialGapBuffer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.ThreadLocalCachedByteArray;
import com.intellij.openapi.util.io.BufferExposingByteArrayOutputStream;
import com.intellij.openapi.util.io.ByteArraySequence;
import com.intellij.util.MathUtil;
import com.intellij.util.SystemProperties;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PersistentHashMapValueStorage {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BLOCK_SIZE_TO_WRITE_WHEN_SOFT_MAX_RETAINED_LIMIT_IS_HIT = 1024;
    private static final int CACHE_PROBATIONAL_QUEUE_SIZE = 20;
    private static final int CACHE_PROTECTED_QUEUE_SIZE = 10;
    public static final boolean COMPRESSION_ENABLED;
    private static final long MAX_RETAINED_LIMIT_WHEN_COMPACTING = 104857600;
    public static final long SOFT_MAX_RETAINED_LIMIT = 10485760;
    private static final ThreadLocalCachedByteArray myBuffer;
    private static final FileAccessorCache<Path, SyncAbleBufferedOutputStreamOverCachedFileChannel> ourAppendersCache;
    private static final int ourBufferLength = 1024;
    private static final boolean ourDumpChunkRemovalTime;
    private static final FileAccessorCache<Path, FileChannelWithSizeTracking> ourFileChannelCache;
    private static final FileAccessorCache<Path, RAReader> ourReadersCache;
    private int myChunks;
    private long myChunksBytesAfterRemoval;
    private long myChunksOriginalBytes;
    private long myChunksReadingTime;
    private long myChunksRemovalTime;
    private boolean myCompactionMode;
    private RAReader myCompactionModeReader;
    private final CompressedAppendableFile myCompressedAppendableFile;
    private int myLastReportedChunksCount;
    private final CreationTimeOptions myOptions;
    private final Path myPath;
    private volatile long mySize;

    public static final class CreationTimeOptions {
        private final boolean myCompactChunksWithValueDeserialization;
        private final boolean myHasNoChunks;
        private final boolean myReadOnly;
        private final boolean myUseCompression;
        public static final ThreadLocal<Boolean> READONLY = new ThreadLocal<>();
        public static final ThreadLocal<Boolean> COMPACT_CHUNKS_WITH_VALUE_DESERIALIZATION = new ThreadLocal<>();
        public static final ThreadLocal<Boolean> HAS_NO_CHUNKS = new ThreadLocal<>();
        public static final ThreadLocal<Boolean> DO_COMPRESSION = new ThreadLocal<Boolean>() { // from class: com.intellij.util.io.PersistentHashMapValueStorage.CreationTimeOptions.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            public Boolean initialValue() {
                return Boolean.valueOf(PersistentHashMapValueStorage.COMPRESSION_ENABLED);
            }
        };

        public CreationTimeOptions(boolean z, boolean z2, boolean z3, boolean z4) {
            this.myReadOnly = z;
            this.myCompactChunksWithValueDeserialization = z2;
            this.myHasNoChunks = z3;
            this.myUseCompression = z4;
        }

        public static CreationTimeOptions threadLocalOptions() {
            Boolean bool = READONLY.get();
            Boolean bool2 = Boolean.TRUE;
            return new CreationTimeOptions(bool == bool2, COMPACT_CHUNKS_WITH_VALUE_DESERIALIZATION.get() == bool2, HAS_NO_CHUNKS.get() == bool2, DO_COMPRESSION.get() == bool2);
        }

        public int getVersion() {
            return ((this.myHasNoChunks ? 10 : 0) * 31) + (this.myUseCompression ? 19 : 0);
        }

        public boolean isReadOnly() {
            return this.myReadOnly;
        }

        public CreationTimeOptions setReadOnly() {
            return new CreationTimeOptions(true, this.myCompactChunksWithValueDeserialization, this.myHasNoChunks, this.myUseCompression);
        }

        public boolean useCompression() {
            return this.myUseCompression;
        }
    }

    public static final class FileReader implements RAReader {
        private final ResilientFileChannel fileChannel;

        private FileReader(Path path) throws IOException {
            this.fileChannel = new ResilientFileChannel(path, StandardOpenOption.READ);
        }

        @Override // com.intellij.util.io.PersistentHashMapValueStorage.RAReader
        public void dispose() throws IOException {
            this.fileChannel.close();
        }

        @Override // com.intellij.util.io.PersistentHashMapValueStorage.RAReader
        public void get(long j, byte[] bArr, int i, int i2) throws IOException {
            this.fileChannel.read(ByteBuffer.wrap(bArr, i, i2), j);
        }
    }

    public final class MyCompressedAppendableFile extends CompressedAppendableFile {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[2];
            objArr[0] = "com/intellij/util/io/PersistentHashMapValueStorage$MyCompressedAppendableFile";
            if (i == 1) {
                objArr[1] = "getChunkAppendStream";
            } else if (i == 2) {
                objArr[1] = "getChunkLengthAppendStream";
            } else if (i != 3) {
                objArr[1] = "getChunkInputStream";
            } else {
                objArr[1] = "getChunksFile";
            }
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", objArr));
        }

        public MyCompressedAppendableFile() throws IOException {
            super(PersistentHashMapValueStorage.this.myPath);
        }

        @Override // com.intellij.util.io.CompressedAppendableFile
        public synchronized void dispose() {
            super.dispose();
            PersistentHashMapValueStorage.ourAppendersCache.remove(getChunkLengthFile());
            PersistentHashMapValueStorage.ourFileChannelCache.remove(getChunkLengthFile());
        }

        @Override // com.intellij.util.io.CompressedAppendableFile
        public synchronized void force() {
            super.force();
            PersistentHashMapValueStorage.forceAppender(getChunkLengthFile());
        }

        @Override // com.intellij.util.io.CompressedAppendableFile
        public DataOutputStream getChunkAppendStream() {
            DataOutputStream dataOutputStream = PersistentHashMapValueStorage.toDataOutputStream(PersistentHashMapValueStorage.ourAppendersCache.get(PersistentHashMapValueStorage.this.myPath));
            if (dataOutputStream == null) {
                $$$reportNull$$$0(1);
            }
            return dataOutputStream;
        }

        @Override // com.intellij.util.io.CompressedAppendableFile
        public InputStream getChunkInputStream(long j, int i) throws Exception {
            PersistentHashMapValueStorage.forceAppender(PersistentHashMapValueStorage.this.myPath);
            FileAccessorCache.Handle handle = PersistentHashMapValueStorage.ourReadersCache.get(PersistentHashMapValueStorage.this.myPath);
            try {
                byte[] bArr = new byte[i];
                ((RAReader) handle.get()).get(j, bArr, 0, i);
                return new ByteArrayInputStream(bArr);
            } finally {
                handle.release();
            }
        }

        @Override // com.intellij.util.io.CompressedAppendableFile
        public DataOutputStream getChunkLengthAppendStream() {
            DataOutputStream dataOutputStream = PersistentHashMapValueStorage.toDataOutputStream(PersistentHashMapValueStorage.ourAppendersCache.get(getChunkLengthFile()));
            if (dataOutputStream == null) {
                $$$reportNull$$$0(2);
            }
            return dataOutputStream;
        }

        @Override // com.intellij.util.io.CompressedAppendableFile
        public Path getChunksFile() {
            Path path = PersistentHashMapValueStorage.this.myPath;
            if (path == null) {
                $$$reportNull$$$0(3);
            }
            return path;
        }
    }

    public interface RAReader {
        void dispose() throws IOException;

        void get(long j, byte[] bArr, int i, int i2) throws IOException;
    }

    public static final class ReadResult {
        final byte[] buffer;
        final int chunksCount;

        public ReadResult(byte[] bArr, int i) {
            this.buffer = bArr;
            this.chunksCount = i;
        }
    }

    public static final class ReaderOverCompressedFile implements RAReader {
        private final CompressedAppendableFile myCompressedAppendableFile;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compressedAppendableFile", "com/intellij/util/io/PersistentHashMapValueStorage$ReaderOverCompressedFile", "<init>"));
        }

        public ReaderOverCompressedFile(CompressedAppendableFile compressedAppendableFile) {
            if (compressedAppendableFile == null) {
                $$$reportNull$$$0(0);
            }
            this.myCompressedAppendableFile = compressedAppendableFile;
        }

        @Override // com.intellij.util.io.PersistentHashMapValueStorage.RAReader
        public void dispose() {
        }

        @Override // com.intellij.util.io.PersistentHashMapValueStorage.RAReader
        public void get(long j, byte[] bArr, int i, int i2) throws Throwable {
            DataInputStream stream = this.myCompressedAppendableFile.getStream(j);
            try {
                stream.readFully(bArr, i, i2);
                stream.close();
            } catch (Throwable th) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }

    public static final class ReaderOverFileChannelCache implements RAReader {
        private final Path myPath;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/intellij/util/io/PersistentHashMapValueStorage$ReaderOverFileChannelCache", "<init>"));
        }

        private ReaderOverFileChannelCache(Path path) {
            if (path == null) {
                $$$reportNull$$$0(0);
            }
            this.myPath = path;
        }

        @Override // com.intellij.util.io.PersistentHashMapValueStorage.RAReader
        public void dispose() {
        }

        @Override // com.intellij.util.io.PersistentHashMapValueStorage.RAReader
        public void get(long j, byte[] bArr, int i, int i2) throws Exception {
            FileAccessorCache.Handle handle = PersistentHashMapValueStorage.ourFileChannelCache.get(this.myPath);
            try {
                ((FileChannelWithSizeTracking) handle.get()).read(j, bArr, i, i2);
                handle.close();
            } catch (Throwable th) {
                if (handle == null) {
                    throw th;
                }
                try {
                    handle.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            default:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                i2 = 3;
                break;
            default:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 14:
                objArr[0] = "path";
                break;
            case 2:
            case 15:
                objArr[0] = "options";
                break;
            case 3:
                objArr[0] = "dataOutputStream";
                break;
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "infos";
                break;
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "storage";
                break;
            case 8:
                objArr[0] = "appender";
                break;
            case 9:
                objArr[0] = "result";
                break;
            case 10:
            case 11:
                objArr[0] = "in";
                break;
            case 12:
                objArr[0] = "buffer";
                break;
            case 13:
                objArr[0] = "handle";
                break;
            default:
                objArr[0] = "com/intellij/util/io/PersistentHashMapValueStorage";
                break;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                objArr[1] = "com/intellij/util/io/PersistentHashMapValueStorage";
                break;
            default:
                objArr[1] = "getOptions";
                break;
        }
        switch (i) {
            case 1:
            case 2:
                objArr[2] = "<init>";
                break;
            case 3:
                objArr[2] = "saveHeader";
                break;
            case 4:
            case 5:
                objArr[2] = "compactValuesWithoutChunks";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "compactValues";
                break;
            case 8:
            case 9:
                objArr[2] = "compactChunks";
                break;
            case 10:
                objArr[2] = "readChunkSize";
                break;
            case 11:
                objArr[2] = "readPrevChunkAddress";
                break;
            case 12:
                objArr[2] = "toDataInputStream";
                break;
            case 13:
                objArr[2] = "toDataOutputStream";
                break;
            case 14:
            case 15:
                objArr[2] = "create";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                throw new IllegalArgumentException(str2);
            default:
                throw new IllegalStateException(str2);
        }
    }

    static {
        int i = CACHE_PROBATIONAL_QUEUE_SIZE;
        ourFileChannelCache = new FileAccessorCache<Path, FileChannelWithSizeTracking>(i, 40) { // from class: com.intellij.util.io.PersistentHashMapValueStorage.1
            private static /* synthetic */ void $$$reportNull$$$0(int i2) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileAccessor", "com/intellij/util/io/PersistentHashMapValueStorage$1", "disposeAccessor"));
            }

            @Override // com.intellij.util.io.FileAccessorCache
            public void disposeAccessor(FileChannelWithSizeTracking fileChannelWithSizeTracking) throws IOException {
                if (fileChannelWithSizeTracking == null) {
                    $$$reportNull$$$0(0);
                }
                fileChannelWithSizeTracking.close();
            }

            @Override // com.intellij.util.io.FileAccessorCache
            public FileChannelWithSizeTracking createAccessor(Path path) throws IOException {
                return new FileChannelWithSizeTracking(path);
            }
        };
        int i2 = 10;
        ourAppendersCache = new FileAccessorCache<Path, SyncAbleBufferedOutputStreamOverCachedFileChannel>(i2, i) { // from class: com.intellij.util.io.PersistentHashMapValueStorage.2
            private static /* synthetic */ void $$$reportNull$$$0(int i3) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stream", "com/intellij/util/io/PersistentHashMapValueStorage$2", "disposeAccessor"));
            }

            @Override // com.intellij.util.io.FileAccessorCache
            public void disposeAccessor(SyncAbleBufferedOutputStreamOverCachedFileChannel syncAbleBufferedOutputStreamOverCachedFileChannel) throws IOException {
                if (syncAbleBufferedOutputStreamOverCachedFileChannel == null) {
                    $$$reportNull$$$0(0);
                }
                syncAbleBufferedOutputStreamOverCachedFileChannel.close();
            }

            @Override // com.intellij.util.io.FileAccessorCache
            public SyncAbleBufferedOutputStreamOverCachedFileChannel createAccessor(Path path) {
                return new SyncAbleBufferedOutputStreamOverCachedFileChannel(path);
            }
        };
        ourReadersCache = new FileAccessorCache<Path, RAReader>(i2, i) { // from class: com.intellij.util.io.PersistentHashMapValueStorage.3
            private static /* synthetic */ void $$$reportNull$$$0(int i3) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileAccessor", "com/intellij/util/io/PersistentHashMapValueStorage$3", "disposeAccessor"));
            }

            @Override // com.intellij.util.io.FileAccessorCache
            public void disposeAccessor(RAReader rAReader) throws IOException {
                if (rAReader == null) {
                    $$$reportNull$$$0(0);
                }
                rAReader.dispose();
            }

            @Override // com.intellij.util.io.FileAccessorCache
            public RAReader createAccessor(Path path) {
                return new ReaderOverFileChannelCache(path);
            }
        };
        COMPRESSION_ENABLED = SystemProperties.getBooleanProperty("idea.compression.enabled", true);
        myBuffer = new ThreadLocalCachedByteArray();
        ourDumpChunkRemovalTime = SystemProperties.getBooleanProperty("idea.phmp.dump.chunk.removal.time", false);
    }

    public PersistentHashMapValueStorage(Path path, CreationTimeOptions creationTimeOptions) throws IOException {
        if (path == null) {
            $$$reportNull$$$0(1);
        }
        if (creationTimeOptions == null) {
            $$$reportNull$$$0(2);
        }
        this.myPath = path;
        this.myOptions = creationTimeOptions;
        if (!creationTimeOptions.useCompression()) {
            this.myCompressedAppendableFile = null;
            this.mySize = Files.exists(path, new LinkOption[0]) ? Files.size(path) : 0L;
        } else {
            MyCompressedAppendableFile myCompressedAppendableFile = new MyCompressedAppendableFile();
            this.myCompressedAppendableFile = myCompressedAppendableFile;
            this.mySize = myCompressedAppendableFile.length();
        }
    }

    private boolean allowedToCompactChunks() {
        return (this.myCompactionMode || this.myOptions.myReadOnly) ? false : true;
    }

    private static void checkCancellation() {
        IOCancellationCallbackHolder.checkCancelled();
    }

    private static void checkPreconditions(byte[] bArr, int i) throws IOException {
        if (i < 0) {
            a16.a("Value storage corrupted: negative chunk size");
        } else {
            if (i <= bArr.length) {
                return;
            }
            a16.a("Value storage corrupted");
        }
    }

    private long compactValuesWithoutChunks(List<PersistentMapImpl.CompactionRecordInfo> list, PersistentHashMapValueStorage persistentHashMapValueStorage) throws IOException {
        PersistentMapImpl.CompactionRecordInfo compactionRecordInfo;
        int i;
        if (list == null) {
            $$$reportNull$$$0(4);
        }
        if (persistentHashMapValueStorage == null) {
            $$$reportNull$$$0(5);
        }
        list.sort(Comparator.comparingLong(new ToLongFunction() { // from class: com.intellij.util.io.i
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((PersistentMapImpl.CompactionRecordInfo) obj).valueAddress;
            }
        }));
        int i2 = 262144;
        byte[] bArr = new byte[262144];
        int i3 = -1;
        byte[] bArr2 = new byte[4096];
        long j = -1;
        int i4 = 0;
        int i5 = 0;
        for (PersistentMapImpl.CompactionRecordInfo compactionRecordInfo2 : list) {
            int i6 = i3;
            long j2 = compactionRecordInfo2.valueAddress;
            int i7 = (int) (j2 - j);
            if (i7 + 5 > i2 || j == -1) {
                long j3 = this.mySize;
                if (j2 != -1) {
                    j3 -= j2;
                }
                int i8 = j3 < 262144 ? (int) j3 : i2;
                compactionRecordInfo = compactionRecordInfo2;
                this.myCompactionModeReader.get(j2, bArr, 0, i8);
                i = (int) (compactionRecordInfo.valueAddress - j2);
                i3 = i8;
                j = j2;
            } else {
                i = i7;
                i3 = i6;
                compactionRecordInfo = compactionRecordInfo2;
            }
            DataInputStream dataInputStream = toDataInputStream(bArr, i, i2);
            int iAvailable = dataInputStream.available();
            int chunkSize = readChunkSize(dataInputStream);
            int i9 = i2;
            byte[] bArr3 = bArr2;
            readPrevChunkAddress(compactionRecordInfo.valueAddress, dataInputStream);
            int iAvailable2 = (iAvailable - dataInputStream.available()) + i;
            byte[] bArr4 = chunkSize >= bArr3.length ? new byte[((chunkSize / 4096) + 1) * 4096] : bArr3;
            int iMin = Math.min(chunkSize, i9 - iAvailable2);
            System.arraycopy(bArr, iAvailable2, bArr4, 0, iMin);
            int i10 = iMin;
            while (i10 != chunkSize) {
                long j4 = j + ((long) i3);
                long j5 = this.mySize - j4;
                int i11 = j5 < 262144 ? (int) j5 : i9;
                this.myCompactionModeReader.get(j4, bArr, 0, i11);
                int iMin2 = Math.min(chunkSize - i10, i9);
                System.arraycopy(bArr, 0, bArr4, i10, iMin2);
                i10 += iMin2;
                i3 = i11;
                j = j4;
            }
            byte[] bArr5 = bArr4;
            compactionRecordInfo.newValueAddress = persistentHashMapValueStorage.appendBytes(bArr5, 0, chunkSize, 0L);
            i4++;
            i5++;
            i2 = i9;
            bArr2 = bArr5;
        }
        return ((long) i4) | (((long) i5) << 32);
    }

    public static PersistentHashMapValueStorage create(Path path, CreationTimeOptions creationTimeOptions) throws IOException {
        if (path == null) {
            $$$reportNull$$$0(14);
        }
        if (creationTimeOptions == null) {
            $$$reportNull$$$0(15);
        }
        return new PersistentHashMapValueStorage(path, creationTimeOptions);
    }

    private long doAppendBytes(byte[] bArr, int i, int i2, long j) throws Throwable {
        DataOutputStream dataOutputStream;
        if (!allowedToCompactChunks()) {
            x1f.a();
            return 0L;
        }
        if (j != 0 && this.myOptions.myHasNoChunks) {
            x1f.a();
            return 0L;
        }
        long j2 = this.mySize;
        DataOutputStream dataOutputStream2 = this.myCompressedAppendableFile != null ? new DataOutputStream(new OutputStream() { // from class: com.intellij.util.io.PersistentHashMapValueStorage.4
            private static /* synthetic */ void $$$reportNull$$$0(int i3) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "b", "com/intellij/util/io/PersistentHashMapValueStorage$4", "write"));
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr2, int i3, int i4) throws IOException {
                if (bArr2 == null) {
                    $$$reportNull$$$0(0);
                }
                PersistentHashMapValueStorage.this.myCompressedAppendableFile.append(bArr2, i3, i4);
            }

            @Override // java.io.OutputStream
            public void write(int i3) throws IOException {
                write(new byte[]{(byte) (i3 & PartialGapBuffer.BUF_SIZE)});
            }
        }) : toDataOutputStream(ourAppendersCache.get(this.myPath));
        try {
            saveHeader(i2, j, j2, dataOutputStream2);
            dataOutputStream = dataOutputStream2;
            try {
                dataOutputStream.write(bArr, i, i2);
                this.mySize += (long) dataOutputStream.resetWrittenBytesCount();
                dataOutputStream.close();
                return j2;
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                dataOutputStream.close();
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = dataOutputStream2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void forceAppender(Path path) {
        FileAccessorCache.Handle<SyncAbleBufferedOutputStreamOverCachedFileChannel> ifCached = ourAppendersCache.getIfCached(path);
        if (ifCached != null) {
            try {
                try {
                    ifCached.get().flush();
                    ifCached.release();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (Throwable th) {
                ifCached.release();
                throw th;
            }
        }
    }

    private static int readChunkSize(DataInputStream dataInputStream) throws IOException {
        if (dataInputStream == null) {
            $$$reportNull$$$0(10);
        }
        int i = DataInputOutputUtil.readINT(dataInputStream);
        if (i >= 0) {
            return i;
        }
        t8g.a("Value storage corrupted: negative chunk size: ", i);
        return 0;
    }

    private long readPrevChunkAddress(long j, DataInputStream dataInputStream) throws IOException {
        if (dataInputStream == null) {
            $$$reportNull$$$0(11);
        }
        if (this.myOptions.myHasNoChunks) {
            return 0L;
        }
        long j2 = DataInputOutputUtil.readLONG(dataInputStream);
        if (j2 < j) {
            if (j2 != 0) {
                return j - j2;
            }
            return 0L;
        }
        throw new IOException("readPrevChunkAddress:" + j + "," + j2 + "," + this.mySize + "," + this.myPath);
    }

    private int saveAccumulatedDataOnDiskPreservingWriteOrder(PersistentHashMapValueStorage persistentHashMapValueStorage, PersistentMapImpl.CompactionRecordInfo compactionRecordInfo, long j, byte[] bArr, int i) throws Throwable {
        byte[] bArr2 = readBytes(j).buffer;
        long jAppendBytes = persistentHashMapValueStorage.appendBytes(bArr2, 0, bArr2.length, compactionRecordInfo.newValueAddress);
        compactionRecordInfo.newValueAddress = jAppendBytes;
        compactionRecordInfo.newValueAddress = persistentHashMapValueStorage.appendBytes(bArr, 0, i, jAppendBytes);
        compactionRecordInfo.value = null;
        compactionRecordInfo.valueAddress = 0L;
        return 2;
    }

    private void saveHeader(int i, long j, long j2, DataOutputStream dataOutputStream) throws IOException {
        if (dataOutputStream == null) {
            $$$reportNull$$$0(3);
        }
        DataInputOutputUtil.writeINT(dataOutputStream, i);
        if (this.myOptions.myHasNoChunks) {
            return;
        }
        if (j2 >= j) {
            long j3 = j2 - j;
            if (j == 0) {
                j3 = 0;
            }
            DataInputOutputUtil.writeLONG(dataOutputStream, j3);
            return;
        }
        throw new IOException("writePrevChunkAddress:" + j2 + "," + j + "," + this.myPath);
    }

    private static DataInputStream toDataInputStream(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            $$$reportNull$$$0(12);
        }
        return new DataInputStream(new UnsyncByteArrayInputStream(bArr, i, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DataOutputStream toDataOutputStream(final FileAccessorCache.Handle<SyncAbleBufferedOutputStreamOverCachedFileChannel> handle) {
        if (handle == null) {
            $$$reportNull$$$0(13);
        }
        return new DataOutputStream(handle.get()) { // from class: com.intellij.util.io.PersistentHashMapValueStorage.5
            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                super.close();
                handle.close();
            }
        };
    }

    public long appendBytes(byte[] bArr, int i, int i2, long j) throws Throwable {
        if (this.mySize == 0) {
            byte[] bytes = "Header Record For PersistentHashMapValueStorage".getBytes(StandardCharsets.UTF_8);
            doAppendBytes(bytes, 0, bytes.length, 0L);
            FileAccessorCache.Handle<SyncAbleBufferedOutputStreamOverCachedFileChannel> ifCached = ourAppendersCache.getIfCached(this.myPath);
            try {
                if (ifCached != null) {
                    try {
                        SyncAbleBufferedOutputStreamOverCachedFileChannel syncAbleBufferedOutputStreamOverCachedFileChannel = ifCached.get();
                        syncAbleBufferedOutputStreamOverCachedFileChannel.flush();
                        syncAbleBufferedOutputStreamOverCachedFileChannel.sync();
                        ifCached.release();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                long size = Files.exists(this.myPath, new LinkOption[0]) ? Files.size(this.myPath) : 0L;
                if (size > this.mySize) {
                    Logger.getInstance(PersistentHashMapValueStorage.class.getName()).info("Avoided PSHM corruption due to write failure:" + this.myPath);
                    this.mySize = size;
                }
            } catch (Throwable th) {
                ifCached.release();
                throw th;
            }
        }
        return doAppendBytes(bArr, i, i2, j);
    }

    public void checkAppendsAllowed(int i) {
        if (i == 0 || !this.myOptions.myHasNoChunks) {
            return;
        }
        x1f.a();
    }

    public long compactChunks(AppendablePersistentMap.ValueDataAppender valueDataAppender, ReadResult readResult) throws IOException {
        long jAppendBytes;
        String str;
        if (valueDataAppender == null) {
            $$$reportNull$$$0(8);
        }
        if (readResult == null) {
            $$$reportNull$$$0(9);
        }
        checkCancellation();
        boolean z = ourDumpChunkRemovalTime;
        long jNanoTime = z ? System.nanoTime() : 0L;
        if (this.myOptions.myCompactChunksWithValueDeserialization) {
            BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream = new BufferExposingByteArrayOutputStream(readResult.buffer.length);
            valueDataAppender.append(new DataOutputStream(bufferExposingByteArrayOutputStream));
            jAppendBytes = appendBytes(bufferExposingByteArrayOutputStream.toByteArraySequence(), 0L);
            this.myChunksBytesAfterRemoval += (long) bufferExposingByteArrayOutputStream.size();
        } else {
            long jAppendBytes2 = appendBytes(ByteArraySequence.create(readResult.buffer), 0L);
            this.myChunksBytesAfterRemoval += (long) readResult.buffer.length;
            jAppendBytes = jAppendBytes2;
        }
        if (z) {
            this.myChunksRemovalTime += System.nanoTime() - jNanoTime;
            int i = this.myChunks;
            if (i - this.myLastReportedChunksCount > 1000) {
                this.myLastReportedChunksCount = i;
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append(this.myChunks);
                sb.append(" chunks were read ");
                sb.append(this.myChunksReadingTime / AnimationKt.MillisToNanos);
                sb.append("ms, bytes: ");
                sb.append(this.myChunksOriginalBytes);
                if (this.myChunksOriginalBytes != this.myChunksBytesAfterRemoval) {
                    str = "->" + this.myChunksBytesAfterRemoval;
                } else {
                    str = "";
                }
                sb.append(str);
                sb.append(" compaction:");
                sb.append(this.myChunksRemovalTime / AnimationKt.MillisToNanos);
                sb.append("ms in ");
                sb.append(this.myPath);
                printStream.println(sb.toString());
            }
        }
        return jAppendBytes;
    }

    public long compactValues(List<PersistentMapImpl.CompactionRecordInfo> list, PersistentHashMapValueStorage persistentHashMapValueStorage) throws IOException {
        byte[] bArr;
        int i;
        int i2;
        byte[] bArr2;
        int i3;
        PersistentMapImpl.CompactionRecordInfo compactionRecordInfo;
        byte[] bArr3;
        DataInputStream dataInputStream;
        int i4;
        byte[] bArr4;
        int i5;
        byte[] bArr5;
        byte[] bArr6;
        PersistentHashMapValueStorage persistentHashMapValueStorage2 = this;
        if (list == null) {
            $$$reportNull$$$0(6);
        }
        if (persistentHashMapValueStorage == null) {
            $$$reportNull$$$0(7);
        }
        if (persistentHashMapValueStorage2.myOptions.myHasNoChunks) {
            return compactValuesWithoutChunks(list, persistentHashMapValueStorage);
        }
        PriorityQueue priorityQueue = new PriorityQueue(list.size(), new Comparator() { // from class: com.intellij.util.io.h
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Long.compare(((PersistentMapImpl.CompactionRecordInfo) obj2).valueAddress, ((PersistentMapImpl.CompactionRecordInfo) obj).valueAddress);
            }
        });
        priorityQueue.addAll(list);
        int i6 = 262159;
        byte[] bArr7 = new byte[262159];
        int i7 = 0;
        long j = persistentHashMapValueStorage2.mySize;
        int i8 = (int) (persistentHashMapValueStorage2.mySize - ((persistentHashMapValueStorage2.mySize / 262144) * 262144));
        long j2 = 0;
        byte[] bArr8 = new byte[0];
        long j3 = j;
        long j4 = j3;
        int i9 = 0;
        int i10 = 0;
        long j5 = 0;
        long length = 0;
        byte[] bArr9 = null;
        while (j3 != j2) {
            long j6 = j2;
            int i11 = i8;
            int iSaveAccumulatedDataOnDiskPreservingWriteOrder = i10;
            int i12 = i9;
            long j7 = j3 - ((long) i8);
            persistentHashMapValueStorage2.myCompactionModeReader.get(j7, bArr7, 0, i11);
            long j8 = j5;
            while (true) {
                if (!priorityQueue.isEmpty()) {
                    PersistentMapImpl.CompactionRecordInfo compactionRecordInfo2 = (PersistentMapImpl.CompactionRecordInfo) priorityQueue.peek();
                    int i13 = i11;
                    i2 = 262144;
                    long j9 = compactionRecordInfo2.valueAddress;
                    if (j9 < j7) {
                        j3 = j7;
                        bArr = bArr7;
                        byte[] bArr10 = bArr9;
                        if (bArr10 != null) {
                            i = 0;
                            bArr9 = bArr10;
                            j8 = j3;
                            break;
                        }
                        int i14 = (int) (j4 - j3);
                        byte[] bArr11 = new byte[i14];
                        i = 0;
                        System.arraycopy(bArr, 0, bArr11, 0, i14);
                        bArr9 = bArr11;
                        break;
                    }
                    if (j9 >= j3) {
                        long j10 = j7;
                        throw new IOException("Value storage is corrupted: value file size:" + persistentHashMapValueStorage2.mySize + ", readStartOffset:" + j10 + ", record address:" + compactionRecordInfo2.valueAddress + "; file: " + persistentHashMapValueStorage2.myPath);
                    }
                    int i15 = (int) (j9 - j7);
                    DataInputStream dataInputStream2 = toDataInputStream(bArr7, i15, i6);
                    if (bArr9 == null || 262144 - i15 >= 15) {
                        bArr2 = bArr9;
                        i3 = i13;
                        compactionRecordInfo = compactionRecordInfo2;
                        bArr3 = bArr7;
                        dataInputStream = dataInputStream2;
                    } else if (j8 != j6) {
                        dataInputStream = dataInputStream2;
                        bArr2 = bArr9;
                        persistentHashMapValueStorage2.myCompactionModeReader.get(j8, bArr7, i13, 15);
                        bArr3 = bArr7;
                        i3 = i13;
                        compactionRecordInfo = compactionRecordInfo2;
                    } else {
                        bArr2 = bArr9;
                        i3 = i13;
                        compactionRecordInfo = compactionRecordInfo2;
                        bArr3 = bArr7;
                        dataInputStream = dataInputStream2;
                        System.arraycopy(bArr2, i7, bArr3, i3, Math.min(bArr2.length, 15));
                    }
                    int iAvailable = dataInputStream.available();
                    int chunkSize = readChunkSize(dataInputStream);
                    DataInputStream dataInputStream3 = dataInputStream;
                    long prevChunkAddress = persistentHashMapValueStorage2.readPrevChunkAddress(compactionRecordInfo.valueAddress, dataInputStream3);
                    int iAvailable2 = iAvailable - dataInputStream3.available();
                    byte[] bArr12 = compactionRecordInfo.value;
                    if (bArr12 != null) {
                        i4 = i3;
                        int length2 = bArr12.length + chunkSize;
                        if (prevChunkAddress == j6) {
                            if (length2 >= bArr8.length) {
                                bArr8 = new byte[length2];
                            }
                            bArr6 = bArr8;
                        } else {
                            byte[] bArr13 = new byte[length2];
                            length += (long) length2;
                            bArr6 = bArr8;
                            bArr8 = bArr13;
                        }
                        System.arraycopy(bArr12, 0, bArr8, chunkSize, bArr12.length);
                        bArr4 = bArr8;
                        bArr8 = bArr6;
                    } else {
                        i4 = i3;
                        prevChunkAddress = prevChunkAddress;
                        if (prevChunkAddress == j6) {
                            if (chunkSize >= bArr8.length) {
                                bArr8 = new byte[chunkSize];
                            }
                            bArr4 = bArr8;
                        } else {
                            length += (long) chunkSize;
                            bArr4 = new byte[chunkSize];
                        }
                    }
                    long j11 = iAvailable2;
                    int iClamp = MathUtil.clamp((int) (((compactionRecordInfo.valueAddress + j11) + ((long) chunkSize)) - j3), 0, chunkSize);
                    if (iClamp <= 0) {
                        i5 = iClamp;
                        bArr5 = bArr4;
                    } else if (j8 != j6) {
                        byte[] bArr14 = bArr4;
                        persistentHashMapValueStorage2.myCompactionModeReader.get(j8, bArr14, chunkSize - iClamp, iClamp);
                        bArr5 = bArr14;
                        i5 = iClamp;
                    } else {
                        i5 = iClamp;
                        bArr5 = bArr4;
                        System.arraycopy(bArr2, Math.max((int) ((compactionRecordInfo.valueAddress + j11) - j3), 0), bArr5, chunkSize - i5, i5);
                    }
                    long j12 = compactionRecordInfo.valueAddress;
                    checkPreconditions(bArr5, chunkSize);
                    System.arraycopy(bArr3, i15 + iAvailable2, bArr5, 0, chunkSize - i5);
                    i12++;
                    priorityQueue.remove(compactionRecordInfo);
                    byte[] bArr15 = compactionRecordInfo.value;
                    if (bArr15 != null) {
                        chunkSize += bArr15.length;
                        length -= (long) bArr15.length;
                        compactionRecordInfo.value = null;
                    }
                    int i16 = chunkSize;
                    if (prevChunkAddress == j6) {
                        compactionRecordInfo.newValueAddress = persistentHashMapValueStorage.appendBytes(bArr5, 0, i16, compactionRecordInfo.newValueAddress);
                        iSaveAccumulatedDataOnDiskPreservingWriteOrder++;
                        persistentHashMapValueStorage2 = this;
                    } else if ((length <= SOFT_MAX_RETAINED_LIMIT || bArr5.length <= 1024) && length <= MAX_RETAINED_LIMIT_WHEN_COMPACTING) {
                        persistentHashMapValueStorage2 = this;
                        byte[] bArr16 = bArr5;
                        PersistentMapImpl.CompactionRecordInfo compactionRecordInfo3 = compactionRecordInfo;
                        compactionRecordInfo3.value = bArr16;
                        compactionRecordInfo3.valueAddress = prevChunkAddress;
                        priorityQueue.add(compactionRecordInfo3);
                    } else {
                        persistentHashMapValueStorage2 = this;
                        byte[] bArr17 = bArr5;
                        iSaveAccumulatedDataOnDiskPreservingWriteOrder += persistentHashMapValueStorage2.saveAccumulatedDataOnDiskPreservingWriteOrder(persistentHashMapValueStorage, compactionRecordInfo, prevChunkAddress, bArr17, i16);
                        length -= (long) bArr17.length;
                    }
                    j4 = j12;
                    bArr7 = bArr3;
                    i11 = i4;
                    j8 = j6;
                    j7 = j7;
                    i6 = 262159;
                    i7 = 0;
                    bArr9 = null;
                } else {
                    j3 = j7;
                    bArr = bArr7;
                    i = i7;
                    i2 = 262144;
                    break;
                }
            }
            i7 = i;
            bArr7 = bArr;
            i9 = i12;
            i10 = iSaveAccumulatedDataOnDiskPreservingWriteOrder;
            i8 = i2;
            j2 = j6;
            j5 = j8;
            i6 = 262159;
        }
        return ((long) i9) | (((long) i10) << 32);
    }

    public void dispose() throws Exception {
        try {
            try {
                CompressedAppendableFile compressedAppendableFile = this.myCompressedAppendableFile;
                if (compressedAppendableFile != null) {
                    compressedAppendableFile.dispose();
                }
            } finally {
                ourReadersCache.remove(this.myPath);
                ourAppendersCache.remove(this.myPath);
                ourFileChannelCache.remove(this.myPath);
                RAReader rAReader = this.myCompactionModeReader;
                if (rAReader != null) {
                    rAReader.dispose();
                    this.myCompactionModeReader = null;
                }
            }
        } catch (IOException e) {
            rc6.a(e);
        }
    }

    public void force() {
        if (this.myOptions.myReadOnly) {
            return;
        }
        CompressedAppendableFile compressedAppendableFile = this.myCompressedAppendableFile;
        if (compressedAppendableFile != null) {
            compressedAppendableFile.force();
        }
        forceAppender(this.myPath);
    }

    public CreationTimeOptions getOptions() {
        CreationTimeOptions creationTimeOptions = this.myOptions;
        if (creationTimeOptions == null) {
            $$$reportNull$$$0(0);
        }
        return creationTimeOptions;
    }

    public long getSize() {
        return this.mySize;
    }

    public boolean isReadOnly() {
        return this.myOptions.myReadOnly;
    }

    public boolean performChunksCompaction(int i) {
        return i > 1 && allowedToCompactChunks();
    }

    public ReadResult readBytes(long j) throws Exception {
        RAReader rAReader;
        FileAccessorCache.Handle<RAReader> handle;
        FileAccessorCache.Handle<RAReader> handle2;
        byte[] bArr;
        forceAppender(this.myPath);
        checkCancellation();
        long j2 = 0;
        long jNanoTime = ourDumpChunkRemovalTime ? System.nanoTime() : 0L;
        RAReader readerOverCompressedFile = this.myCompactionModeReader;
        CompressedAppendableFile compressedAppendableFile = this.myCompressedAppendableFile;
        if (compressedAppendableFile != null) {
            readerOverCompressedFile = new ReaderOverCompressedFile(compressedAppendableFile);
        }
        byte[] bArr2 = null;
        if (readerOverCompressedFile == null) {
            handle = ourReadersCache.get(this.myPath);
            rAReader = handle.get();
        } else {
            rAReader = readerOverCompressedFile;
            handle = null;
        }
        int i = 0;
        long j3 = j;
        int i2 = 0;
        while (j3 != j2) {
            if (j3 >= j2) {
                try {
                    if (j3 <= this.mySize) {
                        byte[] buffer = myBuffer.getBuffer(1024);
                        long j4 = j2;
                        int iMin = (int) Math.min(1024L, this.mySize - j3);
                        rAReader.get(j3, buffer, 0, iMin);
                        long j5 = j3;
                        DataInputStream dataInputStream = toDataInputStream(buffer, i, iMin);
                        int chunkSize = readChunkSize(dataInputStream);
                        long prevChunkAddress = readPrevChunkAddress(j5, dataInputStream);
                        int iAvailable = iMin - dataInputStream.available();
                        int length = (bArr2 != null ? bArr2.length : i) + chunkSize;
                        byte[] bArr3 = new byte[length];
                        if (bArr2 != null) {
                            handle2 = handle;
                            try {
                                try {
                                    System.arraycopy(bArr2, i, bArr3, length - bArr2.length, bArr2.length);
                                } catch (Throwable th) {
                                    th = th;
                                    if (handle2 != null) {
                                        handle2.release();
                                    }
                                    throw th;
                                }
                            } catch (OutOfMemoryError unused) {
                                throw new CorruptedException(this.myPath);
                            }
                        } else {
                            handle2 = handle;
                        }
                        checkPreconditions(bArr3, chunkSize);
                        if (chunkSize < 1024 - iAvailable) {
                            System.arraycopy(buffer, iAvailable, bArr3, i, chunkSize);
                            bArr = bArr3;
                        } else {
                            rAReader = rAReader;
                            bArr = bArr3;
                            rAReader.get(j5 + ((long) iAvailable), bArr, 0, chunkSize);
                        }
                        if (prevChunkAddress >= j5) {
                            throw new CorruptedException(this.myPath);
                        }
                        i2++;
                        if (prevChunkAddress != j4) {
                            checkCancellation();
                        }
                        if (length > this.mySize && this.myCompressedAppendableFile == null) {
                            throw new CorruptedException(this.myPath);
                        }
                        j2 = j4;
                        bArr2 = bArr;
                        handle = handle2;
                        j3 = prevChunkAddress;
                        i = 0;
                    }
                } catch (OutOfMemoryError unused2) {
                } catch (Throwable th2) {
                    th = th2;
                    handle2 = handle;
                }
            }
            throw new CorruptedException(this.myPath);
        }
        FileAccessorCache.Handle<RAReader> handle3 = handle;
        long j6 = j2;
        if (handle3 != null) {
            handle3.release();
        }
        if (i2 > 1) {
            checkCancellation();
            this.myChunksReadingTime += (ourDumpChunkRemovalTime ? System.nanoTime() : j6) - jNanoTime;
            this.myChunks += i2;
            this.myChunksOriginalBytes += (long) bArr2.length;
        }
        return new ReadResult(bArr2, i2);
    }

    public void switchToCompactionMode() throws Exception {
        ourReadersCache.remove(this.myPath);
        ourFileChannelCache.remove(this.myPath);
        CompressedAppendableFile compressedAppendableFile = this.myCompressedAppendableFile;
        if (compressedAppendableFile != null) {
            this.myCompactionModeReader = new ReaderOverCompressedFile(compressedAppendableFile);
        } else {
            this.myCompactionModeReader = new FileReader(this.myPath);
        }
        this.myCompactionMode = true;
    }

    public static final class SyncAbleBufferedOutputStreamOverCachedFileChannel extends BufferedOutputStream {
        private final Path myPath;

        public SyncAbleBufferedOutputStreamOverCachedFileChannel(Path path) {
            super(new OutputStreamOverRandomAccessFileCache(path));
            this.myPath = path;
        }

        public void sync() throws IOException {
            ((FileChannelWithSizeTracking) PersistentHashMapValueStorage.ourFileChannelCache.get(this.myPath).get()).force();
        }

        public static final class OutputStreamOverRandomAccessFileCache extends OutputStream {
            private final Path myPath;

            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "b", "com/intellij/util/io/PersistentHashMapValueStorage$SyncAbleBufferedOutputStreamOverCachedFileChannel$OutputStreamOverRandomAccessFileCache", "write"));
            }

            public OutputStreamOverRandomAccessFileCache(Path path) {
                this.myPath = path;
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws Exception {
                if (bArr == null) {
                    $$$reportNull$$$0(0);
                }
                FileAccessorCache.Handle handle = PersistentHashMapValueStorage.ourFileChannelCache.get(this.myPath);
                FileChannelWithSizeTracking fileChannelWithSizeTracking = (FileChannelWithSizeTracking) handle.get();
                try {
                    fileChannelWithSizeTracking.write(fileChannelWithSizeTracking.length(), bArr, i, i2);
                } finally {
                    handle.release();
                }
            }

            @Override // java.io.OutputStream
            public void write(int i) throws IOException {
                write(new byte[]{(byte) (i & PartialGapBuffer.BUF_SIZE)});
            }
        }
    }

    public long appendBytes(ByteArraySequence byteArraySequence, long j) throws IOException {
        return appendBytes(byteArraySequence.getInternalBuffer(), byteArraySequence.getOffset(), byteArraySequence.getLength(), j);
    }
}
