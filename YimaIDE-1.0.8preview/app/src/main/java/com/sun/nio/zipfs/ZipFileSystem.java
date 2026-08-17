package com.sun.nio.zipfs;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import defpackage.b9g;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.AccessMode;
import java.nio.file.ClosedFileSystemException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.ReadOnlyFileSystemException;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.WatchService;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipError;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ZipFileSystem extends FileSystem {
    private static final String GLOB_SYNTAX = "glob";
    private static final String REGEX_SYNTAX = "regex";
    final byte[] cen;
    private final SeekableByteChannel ch;
    private final boolean createNew;
    private final String defaultDir;
    private final ZipPath defaultdir;
    private END end;
    private LinkedHashMap<IndexNode, IndexNode> inodes;
    private long locpos;
    private final String nameEncoding;
    private final ZipFileSystemProvider provider;
    private boolean readOnly;
    private IndexNode root;
    private final boolean useTempFile;
    final ZipCoder zc;
    private final Path zfpath;
    private static final boolean isWindows = System.getProperty("os.name").startsWith("Windows");
    private static final Set<String> supportedFileAttributeViews = Collections.unmodifiableSet(new HashSet(Arrays.asList("basic", "zip")));
    private static byte[] ROOTPATH = new byte[0];
    private final int tempFileCreationThreshold = 10485760;
    private Set<InputStream> streams = Collections.synchronizedSet(new HashSet());
    private Set<ExChannelCloser> exChClosers = new HashSet();
    private Set<Path> tmppaths = Collections.synchronizedSet(new HashSet());
    private volatile boolean isOpen = true;
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private boolean hasUpdate = false;
    private final IndexNode LOOKUPKEY = IndexNode.keyOf(null);
    private final int MAX_FLATER = 20;
    private final List<Inflater> inflaters = new ArrayList();
    private final List<Deflater> deflaters = new ArrayList();

    public static class END {
        long cenlen;
        long cenoff;
        int centot;
        int comlen;
        byte[] comment;
        int diskNum;
        int disknum;
        int disktot;
        long endpos;
        int endsub;
        int sdisknum;

        public void write(OutputStream outputStream, long j) throws IOException {
            boolean z;
            long j2 = this.cenlen;
            long j3 = this.cenoff;
            boolean z2 = true;
            if (j2 >= 4294967295L) {
                j2 = 4294967295L;
                z = true;
            } else {
                z = false;
            }
            if (j3 >= 4294967295L) {
                j3 = 4294967295L;
                z = true;
            }
            int i = this.centot;
            if (i >= 65535) {
                i = 65535;
            } else {
                z2 = z;
            }
            if (z2) {
                ZipUtils.writeInt(outputStream, 101075792L);
                ZipUtils.writeLong(outputStream, 44L);
                ZipUtils.writeShort(outputStream, 45);
                ZipUtils.writeShort(outputStream, 45);
                ZipUtils.writeInt(outputStream, 0L);
                ZipUtils.writeInt(outputStream, 0L);
                ZipUtils.writeLong(outputStream, this.centot);
                ZipUtils.writeLong(outputStream, this.centot);
                ZipUtils.writeLong(outputStream, this.cenlen);
                ZipUtils.writeLong(outputStream, this.cenoff);
                ZipUtils.writeInt(outputStream, 117853008L);
                ZipUtils.writeInt(outputStream, 0L);
                ZipUtils.writeLong(outputStream, j);
                ZipUtils.writeInt(outputStream, 1L);
            }
            ZipUtils.writeInt(outputStream, ZipConstants.ENDSIG);
            ZipUtils.writeShort(outputStream, 0);
            ZipUtils.writeShort(outputStream, 0);
            ZipUtils.writeShort(outputStream, i);
            ZipUtils.writeShort(outputStream, i);
            ZipUtils.writeInt(outputStream, j2);
            ZipUtils.writeInt(outputStream, j3);
            byte[] bArr = this.comment;
            if (bArr == null) {
                ZipUtils.writeShort(outputStream, 0);
            } else {
                ZipUtils.writeShort(outputStream, bArr.length);
                ZipUtils.writeBytes(outputStream, this.comment);
            }
        }
    }

    public class EntryOutputStream extends DeflaterOutputStream {
        private CRC32 crc;
        private Entry e;
        private boolean isClosed;
        private long written;

        public EntryOutputStream(Entry entry, OutputStream outputStream) throws IOException {
            super(outputStream, ZipFileSystem.this.getDeflater());
            this.isClosed = false;
            if (entry == null) {
                x0e.a("Zip entry is null");
                throw null;
            }
            this.e = entry;
            this.crc = new CRC32();
        }

        @Override // java.util.zip.DeflaterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            try {
                if (this.isClosed) {
                    return;
                }
                this.isClosed = true;
                Entry entry = this.e;
                int i = entry.method;
                if (i == 0) {
                    long j = this.written;
                    entry.csize = j;
                    entry.size = j;
                    entry.crc = this.crc.getValue();
                } else {
                    if (i != 8) {
                        throw new ZipException("invalid compression method");
                    }
                    finish();
                    this.e.size = ((DeflaterOutputStream) this).def.getBytesRead();
                    this.e.csize = ((DeflaterOutputStream) this).def.getBytesWritten();
                    this.e.crc = this.crc.getValue();
                }
                OutputStream outputStream = ((DeflaterOutputStream) this).out;
                if (outputStream instanceof FileRolloverOutputStream) {
                    FileRolloverOutputStream fileRolloverOutputStream = (FileRolloverOutputStream) outputStream;
                    if (fileRolloverOutputStream.tmpFileOS == null) {
                        this.e.bytes = fileRolloverOutputStream.toByteArray();
                    }
                }
                if (this.e.type == 3) {
                    ZipFileSystem.this.releaseDeflater(((DeflaterOutputStream) this).def);
                    return;
                }
                super.close();
                ZipFileSystem.this.releaseDeflater(((DeflaterOutputStream) this).def);
                ZipFileSystem.this.update(this.e);
            } catch (Throwable th) {
                throw th;
            }
        }

        @Override // java.util.zip.DeflaterOutputStream, java.io.FilterOutputStream, java.io.OutputStream
        public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                if (this.e.type != 3) {
                    ZipFileSystem.this.ensureOpen();
                }
                if (this.isClosed) {
                    throw new IOException("Stream closed");
                }
                if (i < 0 || i2 < 0 || i > bArr.length - i2) {
                    throw new IndexOutOfBoundsException();
                }
                if (i2 == 0) {
                    return;
                }
                int i3 = this.e.method;
                if (i3 == 0) {
                    this.written += (long) i2;
                    ((DeflaterOutputStream) this).out.write(bArr, i, i2);
                } else {
                    if (i3 != 8) {
                        throw new ZipException("invalid compression method");
                    }
                    super.write(bArr, i, i2);
                }
                this.crc.update(bArr, i, i2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static class ExChannelCloser {
        SeekableByteChannel ch;
        Path path;
        Set<InputStream> streams;

        public ExChannelCloser(Path path, SeekableByteChannel seekableByteChannel, Set<InputStream> set) {
            this.path = path;
            this.ch = seekableByteChannel;
            this.streams = set;
        }
    }

    public ZipFileSystem(ZipFileSystemProvider zipFileSystemProvider, Path path, Map<String, ?> map) throws IOException {
        this.readOnly = false;
        boolean zEquals = "true".equals(map.get("create"));
        this.createNew = zEquals;
        String str = map.containsKey("encoding") ? (String) map.get("encoding") : "UTF-8";
        this.nameEncoding = str;
        this.useTempFile = Boolean.TRUE.equals(map.get("useTempFile"));
        String str2 = map.containsKey("default.dir") ? (String) map.get("default.dir") : PsuedoNames.PSEUDONAME_ROOT;
        this.defaultDir = str2;
        if (str2.charAt(0) != '/') {
            w01.a("default dir should be absolute");
            throw null;
        }
        this.provider = zipFileSystemProvider;
        this.zfpath = path;
        if (Files.notExists(path, new LinkOption[0])) {
            if (!zEquals) {
                throw new FileSystemNotFoundException(path.toString());
            }
            OutputStream outputStreamNewOutputStream = Files.newOutputStream(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
            try {
                new END().write(outputStreamNewOutputStream, 0L);
                if (outputStreamNewOutputStream != null) {
                    outputStreamNewOutputStream.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (outputStreamNewOutputStream != null) {
                        try {
                            outputStreamNewOutputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        path.getFileSystem().provider().checkAccess(path, AccessMode.READ);
        if (!Files.isWritable(path)) {
            this.readOnly = true;
        }
        this.zc = ZipCoder.get(str);
        this.defaultdir = new ZipPath(this, getBytes(str2));
        this.ch = Files.newByteChannel(path, StandardOpenOption.READ);
        this.cen = initCEN();
    }

    private void addToTree(IndexNode indexNode, HashSet<IndexNode> hashSet) {
        IndexNode indexNode2;
        if (hashSet.contains(indexNode)) {
            return;
        }
        byte[] bArr = indexNode.name;
        byte[] parent = getParent(bArr);
        if (this.inodes.containsKey(this.LOOKUPKEY.as(parent))) {
            indexNode2 = this.inodes.get(this.LOOKUPKEY);
        } else {
            IndexNode indexNode3 = new IndexNode(parent, -1);
            this.inodes.put(indexNode3, indexNode3);
            indexNode2 = indexNode3;
        }
        addToTree(indexNode2, hashSet);
        indexNode.sibling = indexNode2.child;
        indexNode2.child = indexNode;
        if (bArr[bArr.length - 1] == 47) {
            hashSet.add(indexNode);
        }
    }

    private final void beginRead() {
        this.rwlock.readLock().lock();
    }

    private final void beginWrite() {
        this.rwlock.writeLock().lock();
    }

    private void buildNodeTree() throws IOException {
        beginWrite();
        try {
            HashSet<IndexNode> hashSet = new HashSet<>();
            IndexNode indexNode = new IndexNode(ROOTPATH, -1);
            this.inodes.put(indexNode, indexNode);
            hashSet.add(indexNode);
            for (IndexNode indexNode2 : (IndexNode[]) this.inodes.keySet().toArray(new IndexNode[0])) {
                addToTree(indexNode2, hashSet);
            }
            endWrite();
        } catch (Throwable th) {
            endWrite();
            throw th;
        }
    }

    private void checkOptions(Set<? extends OpenOption> set) {
        for (OpenOption openOption : set) {
            openOption.getClass();
            if (!(openOption instanceof StandardOpenOption)) {
                j2d.a();
                return;
            }
        }
        if (set.contains(StandardOpenOption.APPEND) && set.contains(StandardOpenOption.TRUNCATE_EXISTING)) {
            w01.a("APPEND + TRUNCATE_EXISTING not allowed");
        }
    }

    private void checkParents(byte[] bArr) throws IOException {
        beginRead();
        do {
            try {
                bArr = getParent(bArr);
                if (bArr == null || bArr.length == 0) {
                    endRead();
                    return;
                }
            } catch (Throwable th) {
                endRead();
                throw th;
            }
        } while (this.inodes.containsKey(IndexNode.keyOf(bArr)));
        throw new NoSuchFileException(getString(bArr));
    }

    private void checkWritable() throws IOException {
        if (this.readOnly) {
            throw new ReadOnlyFileSystemException();
        }
    }

    private long copyLOCEntry(Entry entry, boolean z, OutputStream outputStream, long j, byte[] bArr) throws IOException {
        long j2;
        long jLOCNAM;
        long j3;
        long jLOCNAM2;
        long jWriteLOC;
        int fullyAt;
        long j4 = entry.locoff;
        entry.locoff = j;
        if ((entry.flag & 8) != 0) {
            j2 = (entry.size >= 4294967295L || entry.csize >= 4294967295L) ? 24L : 16L;
        } else {
            j2 = 0;
        }
        if (readFullyAt(bArr, 0, 30L, j4) != 30) {
            throw new ZipException("loc: reading failed");
        }
        if (z) {
            jLOCNAM = j4 + ((long) (ZipConstants.LOCNAM(bArr) + 30 + ZipConstants.LOCEXT(bArr)));
            jLOCNAM2 = j2 + entry.csize;
            jWriteLOC = ((long) entry.writeLOC(outputStream)) + jLOCNAM2;
            j3 = 0;
        } else {
            outputStream.write(bArr, 0, 30);
            jLOCNAM = j4 + 30;
            j3 = 0;
            jLOCNAM2 = j2 + ((long) (ZipConstants.LOCNAM(bArr) + ZipConstants.LOCEXT(bArr))) + entry.csize;
            jWriteLOC = jLOCNAM2 + 30;
        }
        while (jLOCNAM2 > j3 && (fullyAt = (int) readFullyAt(bArr, 0, bArr.length, jLOCNAM)) != -1) {
            if (jLOCNAM2 < fullyAt) {
                fullyAt = (int) jLOCNAM2;
            }
            outputStream.write(bArr, 0, fullyAt);
            long j5 = fullyAt;
            jLOCNAM2 -= j5;
            jLOCNAM += j5;
        }
        return jWriteLOC;
    }

    private static void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    private Path createTempFileInSameDirectoryAs(Path path) throws IOException {
        Path parent = path.toAbsolutePath().getParent();
        if (parent == null) {
            parent = path.getFileSystem().getPath(Constants.ATTRVAL_THIS, new String[0]);
        }
        Path pathCreateTempFile = Files.createTempFile(parent, "zipfstmp", null, new FileAttribute[0]);
        this.tmppaths.add(pathCreateTempFile);
        return pathCreateTempFile;
    }

    private final void endRead() {
        this.rwlock.readLock().unlock();
    }

    private final void endWrite() {
        this.rwlock.writeLock().unlock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureOpen() throws IOException {
        if (!this.isOpen) {
            throw new ClosedFileSystemException();
        }
    }

    private END findEND() throws IOException {
        byte[] bArr = new byte[128];
        ZipFileSystem zipFileSystem = this;
        long size = zipFileSystem.ch.size();
        long j = size - 65557;
        long j2 = 0;
        if (j <= 0) {
            j = 0;
        }
        long j3 = 106;
        long j4 = j - 106;
        long j5 = size - 128;
        while (j5 >= j4) {
            int i = 0;
            if (j5 < j2) {
                int i2 = (int) (-j5);
                Arrays.fill(bArr, 0, i2, (byte) 0);
                i = i2;
            }
            long j6 = 128 - i;
            long j7 = j5;
            if (zipFileSystem.readFullyAt(bArr, i, j6, ((long) i) + j5) != j6) {
                zerror("zip END header not found");
            }
            int i3 = 106;
            while (i3 >= 0) {
                if (bArr[i3] == 80 && bArr[i3 + 1] == 75 && bArr[i3 + 2] == 5 && bArr[i3 + 3] == 6) {
                    long j8 = ((long) i3) + j7;
                    if (22 + j8 + ((long) ZipConstants.ENDCOM(bArr, i3)) == size) {
                        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i3, i3 + 22);
                        END end = new END();
                        end.endsub = ZipConstants.ENDSUB(bArrCopyOfRange);
                        end.centot = ZipConstants.ENDTOT(bArrCopyOfRange);
                        end.cenlen = ZipConstants.ENDSIZ(bArrCopyOfRange);
                        end.cenoff = ZipConstants.ENDOFF(bArrCopyOfRange);
                        end.comlen = ZipConstants.ENDCOM(bArrCopyOfRange);
                        end.endpos = j8;
                        if (end.cenlen == 4294967295L || end.cenoff == 4294967295L || end.centot == 65535) {
                            byte[] bArr2 = new byte[20];
                            if (readFullyAt(bArr2, 0, 20L, j8 - 20) == 20) {
                                long jZIP64_LOCOFF = ZipConstants.ZIP64_LOCOFF(bArr2);
                                byte[] bArr3 = new byte[56];
                                if (readFullyAt(bArr3, 0, 56L, jZIP64_LOCOFF) == 56) {
                                    end.cenlen = ZipConstants.ZIP64_ENDSIZ(bArr3);
                                    end.cenoff = ZipConstants.ZIP64_ENDOFF(bArr3);
                                    end.centot = (int) ZipConstants.ZIP64_ENDTOT(bArr3);
                                    end.endpos = jZIP64_LOCOFF;
                                    return end;
                                }
                            }
                        }
                        return end;
                    }
                }
                i3--;
                j3 = j3;
            }
            j5 = j7 - j3;
            zipFileSystem = this;
            j2 = 0;
        }
        zerror("zip END header not found");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getDataPos(Entry entry) throws IOException {
        if (entry.locoff == -1) {
            Entry entry0 = getEntry0(entry.name);
            if (entry0 == null) {
                throw new ZipException("invalid loc for entry <" + entry.name + ">");
            }
            entry.locoff = entry0.locoff;
        }
        byte[] bArr = new byte[30];
        if (readFullyAt(bArr, 0, 30L, entry.locoff) == 30) {
            return this.locpos + entry.locoff + 30 + ((long) ZipConstants.LOCNAM(bArr)) + ((long) ZipConstants.LOCEXT(bArr));
        }
        throw new ZipException("invalid loc for entry <" + entry.name + ">");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Deflater getDeflater() {
        synchronized (this.deflaters) {
            try {
                int size = this.deflaters.size();
                if (size > 0) {
                    return this.deflaters.remove(size - 1);
                }
                return new Deflater(-1, true);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Entry getEntry0(byte[] bArr) throws IOException {
        int i;
        IndexNode inode = getInode(bArr);
        if (inode instanceof Entry) {
            return (Entry) inode;
        }
        if (inode == null || (i = inode.pos) == -1) {
            return null;
        }
        return Entry.readCEN(this, i);
    }

    private Inflater getInflater() {
        synchronized (this.inflaters) {
            try {
                int size = this.inflaters.size();
                if (size > 0) {
                    return this.inflaters.remove(size - 1);
                }
                return new Inflater(true);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private IndexNode getInode(byte[] bArr) {
        if (bArr == null) {
            x0e.a("path");
            return null;
        }
        IndexNode indexNodeKeyOf = IndexNode.keyOf(bArr);
        IndexNode indexNode = this.inodes.get(indexNodeKeyOf);
        if (indexNode != null || (bArr.length != 0 && bArr[bArr.length - 1] == 47)) {
            return indexNode;
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length + 1);
        bArrCopyOf[bArrCopyOf.length - 1] = 47;
        return this.inodes.get(indexNodeKeyOf.as(bArrCopyOf));
    }

    private InputStream getInputStream(Entry entry) throws IOException {
        InputStream entryInputStream;
        ZipFileSystem zipFileSystem;
        int i = entry.type;
        if (i == 2) {
            if (entry.bytes != null) {
                entryInputStream = new ByteArrayInputStream(entry.bytes);
            } else {
                Path path = entry.file;
                if (path == null) {
                    throw new ZipException("update entry data is missing");
                }
                entryInputStream = Files.newInputStream(path, new OpenOption[0]);
            }
        } else {
            if (i == 3) {
                return Files.newInputStream(entry.file, new OpenOption[0]);
            }
            entryInputStream = new EntryInputStream(entry, this.ch);
        }
        InputStream inputStream = entryInputStream;
        int i2 = entry.method;
        if (i2 == 8) {
            final long j = entry.size;
            long j2 = 2 + j;
            if (j2 > 65536) {
                j2 = 8192;
            }
            zipFileSystem = this;
            inputStream = new InflaterInputStream(inputStream, getInflater(), (int) j2) { // from class: com.sun.nio.zipfs.ZipFileSystem.5
                private boolean eof;
                private boolean isClosed = false;

                @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream
                public int available() throws IOException {
                    if (this.isClosed) {
                        return 0;
                    }
                    long bytesWritten = j - ((InflaterInputStream) this).inf.getBytesWritten();
                    if (bytesWritten > 2147483647L) {
                        return Integer.MAX_VALUE;
                    }
                    return (int) bytesWritten;
                }

                @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    if (this.isClosed) {
                        return;
                    }
                    ZipFileSystem.this.releaseInflater(((InflaterInputStream) this).inf);
                    ((InflaterInputStream) this).in.close();
                    this.isClosed = true;
                    ZipFileSystem.this.streams.remove(this);
                }

                @Override // java.util.zip.InflaterInputStream
                public void fill() throws IOException {
                    if (this.eof) {
                        vo6.a("Unexpected end of ZLIB input stream");
                        return;
                    }
                    InputStream inputStream2 = ((InflaterInputStream) this).in;
                    byte[] bArr = ((InflaterInputStream) this).buf;
                    int i3 = inputStream2.read(bArr, 0, bArr.length);
                    ((InflaterInputStream) this).len = i3;
                    if (i3 == -1) {
                        ((InflaterInputStream) this).buf[0] = 0;
                        ((InflaterInputStream) this).len = 1;
                        this.eof = true;
                    }
                    ((InflaterInputStream) this).inf.setInput(((InflaterInputStream) this).buf, 0, ((InflaterInputStream) this).len);
                }
            };
        } else {
            zipFileSystem = this;
            if (i2 != 0) {
                throw new ZipException("invalid compression method");
            }
        }
        zipFileSystem.streams.add(inputStream);
        return inputStream;
    }

    private OutputStream getOutputStream(Entry entry) throws IOException {
        OutputStream outputStreamNewOutputStream;
        if (entry.mtime == -1) {
            entry.mtime = System.currentTimeMillis();
        }
        if (entry.method == -1) {
            entry.method = 8;
        }
        entry.flag = 0;
        if (this.zc.isUTF8()) {
            entry.flag |= 2048;
        }
        if (this.useTempFile || entry.size >= 10485760) {
            Path tempPathForEntry = getTempPathForEntry(null);
            entry.file = tempPathForEntry;
            outputStreamNewOutputStream = Files.newOutputStream(tempPathForEntry, StandardOpenOption.WRITE);
        } else {
            outputStreamNewOutputStream = new FileRolloverOutputStream(entry);
        }
        return new EntryOutputStream(entry, outputStreamNewOutputStream);
    }

    private static byte[] getParent(byte[] bArr) {
        int length = bArr.length;
        int i = length - 1;
        if (i > 0 && bArr[i] == 47) {
            i = length - 2;
        }
        while (i > 0 && bArr[i] != 47) {
            i--;
        }
        return i <= 0 ? ROOTPATH : Arrays.copyOf(bArr, i + 1);
    }

    private PosixFileAttributes getPosixAttributes(Path path) throws IOException {
        try {
            PosixFileAttributeView posixFileAttributeView = (PosixFileAttributeView) Files.getFileAttributeView(path, PosixFileAttributeView.class, new LinkOption[0]);
            if (posixFileAttributeView == null) {
                return null;
            }
            return posixFileAttributeView.readAttributes();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Path getTempPathForEntry(byte[] bArr) throws IOException {
        Path pathCreateTempFileInSameDirectoryAs = createTempFileInSameDirectoryAs(this.zfpath);
        if (bArr != null && getEntry0(bArr) != null) {
            InputStream inputStreamNewInputStream = newInputStream(bArr);
            try {
                Files.copy(inputStreamNewInputStream, pathCreateTempFileInSameDirectoryAs, StandardCopyOption.REPLACE_EXISTING);
                if (inputStreamNewInputStream != null) {
                    inputStreamNewInputStream.close();
                    return pathCreateTempFileInSameDirectoryAs;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (inputStreamNewInputStream != null) {
                        try {
                            inputStreamNewInputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        return pathCreateTempFileInSameDirectoryAs;
    }

    private byte[] initCEN() throws IOException {
        END endFindEND = findEND();
        this.end = endFindEND;
        long j = endFindEND.endpos;
        if (j == 0) {
            this.inodes = new LinkedHashMap<>(10);
            this.locpos = 0L;
            buildNodeTree();
            return null;
        }
        if (endFindEND.cenlen > j) {
            zerror("invalid END header (bad central directory size)");
        }
        END end = this.end;
        long j2 = end.endpos - end.cenlen;
        long j3 = j2 - end.cenoff;
        this.locpos = j3;
        if (j3 < 0) {
            zerror("invalid END header (bad central directory offset)");
        }
        int i = (int) (this.end.cenlen + 22);
        byte[] bArr = new byte[i];
        if (readFullyAt(bArr, 0, i, j2) != this.end.cenlen + 22) {
            zerror("read CEN tables failed");
        }
        this.inodes = new LinkedHashMap<>(this.end.centot + 1);
        int i2 = i - 22;
        int i3 = 0;
        while (i3 < i2) {
            if (!ZipConstants.cenSigAt(bArr, i3)) {
                zerror("invalid CEN header (bad signature)");
            }
            int iCENHOW = ZipConstants.CENHOW(bArr, i3);
            int iCENNAM = ZipConstants.CENNAM(bArr, i3);
            int iCENEXT = ZipConstants.CENEXT(bArr, i3);
            int iCENCOM = ZipConstants.CENCOM(bArr, i3);
            if ((ZipConstants.CENFLG(bArr, i3) & 1) != 0) {
                zerror("invalid CEN header (encrypted entry)");
            }
            if (iCENHOW != 0 && iCENHOW != 8) {
                zerror("invalid CEN header (unsupported compression method: " + iCENHOW + ")");
            }
            int i4 = i3 + 46;
            int i5 = i4 + iCENNAM;
            if (i5 > i2) {
                zerror("invalid CEN header (bad header size)");
            }
            IndexNode indexNode = new IndexNode(Arrays.copyOfRange(bArr, i4, i5), i3);
            this.inodes.put(indexNode, indexNode);
            i3 += iCENNAM + 46 + iCENEXT + iCENCOM;
        }
        if (i3 + 22 != i) {
            zerror("invalid CEN header (bad header size)");
        }
        buildNodeTree();
        return bArr;
    }

    private final long readFullyAt(ByteBuffer byteBuffer, long j) throws IOException {
        long j2;
        synchronized (this.ch) {
            j2 = this.ch.position(j).read(byteBuffer);
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseDeflater(Deflater deflater) {
        synchronized (this.deflaters) {
            try {
                if (this.deflaters.size() < 20) {
                    deflater.reset();
                    this.deflaters.add(deflater);
                } else {
                    deflater.end();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseInflater(Inflater inflater) {
        synchronized (this.inflaters) {
            try {
                if (this.inflaters.size() < 20) {
                    inflater.reset();
                    this.inflaters.add(inflater);
                } else {
                    inflater.end();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void removeFromTree(IndexNode indexNode) {
        IndexNode indexNode2 = this.inodes.get(this.LOOKUPKEY.as(getParent(indexNode.name)));
        IndexNode indexNode3 = indexNode2.child;
        if (indexNode3.equals(indexNode)) {
            indexNode2.child = indexNode3.sibling;
            return;
        }
        while (true) {
            IndexNode indexNode4 = indexNode3.sibling;
            if (indexNode4 == null) {
                return;
            }
            if (indexNode4.equals(indexNode)) {
                indexNode3.sibling = indexNode4.sibling;
                return;
            }
            indexNode3 = indexNode4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeTempPathForEntry(Path path) throws IOException {
        Files.delete(path);
        this.tmppaths.remove(path);
    }

    private void sync() throws IOException {
        long length;
        if (!this.exChClosers.isEmpty()) {
            for (ExChannelCloser exChannelCloser : this.exChClosers) {
                if (exChannelCloser.streams.isEmpty()) {
                    exChannelCloser.ch.close();
                    Files.delete(exChannelCloser.path);
                    this.exChClosers.remove(exChannelCloser);
                }
            }
        }
        if (this.hasUpdate) {
            PosixFileAttributes posixAttributes = getPosixAttributes(this.zfpath);
            Path pathCreateTempFileInSameDirectoryAs = createTempFileInSameDirectoryAs(this.zfpath);
            int i = 0;
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(pathCreateTempFileInSameDirectoryAs, StandardOpenOption.WRITE));
            try {
                ArrayList arrayList = new ArrayList(this.inodes.size());
                byte[] bArr = new byte[8192];
                long jWriteCEN = 0;
                for (IndexNode indexNode : this.inodes.values()) {
                    if (indexNode instanceof Entry) {
                        Entry entry = (Entry) indexNode;
                        try {
                            if (entry.type == 4) {
                                length = copyLOCEntry(entry, true, bufferedOutputStream, jWriteCEN, bArr);
                            } else {
                                entry.locoff = jWriteCEN;
                                jWriteCEN += (long) entry.writeLOC(bufferedOutputStream);
                                byte[] bArr2 = entry.bytes;
                                if (bArr2 != null) {
                                    bufferedOutputStream.write(bArr2);
                                    length = entry.bytes.length;
                                } else {
                                    Path path = entry.file;
                                    if (path != null) {
                                        InputStream inputStreamNewInputStream = Files.newInputStream(path, new OpenOption[i]);
                                        try {
                                            int i2 = entry.type;
                                            if (i2 == 2) {
                                                while (true) {
                                                    int i3 = inputStreamNewInputStream.read(bArr);
                                                    if (i3 == -1) {
                                                        break;
                                                    }
                                                    bufferedOutputStream.write(bArr, i, i3);
                                                    jWriteCEN += (long) i3;
                                                    i = 0;
                                                }
                                            } else if (i2 == 3) {
                                                EntryOutputStream entryOutputStream = new EntryOutputStream(entry, bufferedOutputStream);
                                                while (true) {
                                                    try {
                                                        int i4 = inputStreamNewInputStream.read(bArr);
                                                        if (i4 == -1) {
                                                            break;
                                                        } else {
                                                            entryOutputStream.write(bArr, 0, i4);
                                                        }
                                                    } catch (Throwable th) {
                                                        try {
                                                            throw th;
                                                        } catch (Throwable th2) {
                                                            try {
                                                                entryOutputStream.close();
                                                            } catch (Throwable th3) {
                                                                th.addSuppressed(th3);
                                                            }
                                                            throw th2;
                                                        }
                                                    }
                                                    try {
                                                        throw th;
                                                    } catch (Throwable th4) {
                                                        if (inputStreamNewInputStream != null) {
                                                            try {
                                                                inputStreamNewInputStream.close();
                                                            } catch (Throwable th5) {
                                                                th.addSuppressed(th5);
                                                            }
                                                        }
                                                        throw th4;
                                                    }
                                                }
                                                entryOutputStream.close();
                                                jWriteCEN += entry.csize;
                                                if ((entry.flag & 8) != 0) {
                                                    jWriteCEN += (long) entry.writeEXT(bufferedOutputStream);
                                                }
                                            }
                                            if (inputStreamNewInputStream != null) {
                                                inputStreamNewInputStream.close();
                                            }
                                            Files.delete(entry.file);
                                            this.tmppaths.remove(entry.file);
                                        } catch (Throwable th6) {
                                            throw th6;
                                        }
                                    }
                                }
                                arrayList.add(entry);
                            }
                            jWriteCEN += length;
                            arrayList.add(entry);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        int i5 = indexNode.pos;
                        if (i5 != -1) {
                            Entry cen = Entry.readCEN(this, i5);
                            try {
                                jWriteCEN += copyLOCEntry(cen, false, bufferedOutputStream, jWriteCEN, bArr);
                                arrayList.add(cen);
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    i = 0;
                }
                this.end.cenoff = jWriteCEN;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    jWriteCEN += (long) ((Entry) it.next()).writeCEN(bufferedOutputStream);
                }
                this.end.centot = arrayList.size();
                END end = this.end;
                end.cenlen = jWriteCEN - end.cenoff;
                end.write(bufferedOutputStream, jWriteCEN);
                bufferedOutputStream.close();
                if (this.streams.isEmpty()) {
                    this.ch.close();
                    Files.delete(this.zfpath);
                } else {
                    ExChannelCloser exChannelCloser2 = new ExChannelCloser(createTempFileInSameDirectoryAs(this.zfpath), this.ch, this.streams);
                    Files.move(this.zfpath, exChannelCloser2.path, StandardCopyOption.REPLACE_EXISTING);
                    this.exChClosers.add(exChannelCloser2);
                    this.streams = Collections.synchronizedSet(new HashSet());
                }
                if (posixAttributes != null) {
                    Files.setPosixFilePermissions(pathCreateTempFileInSameDirectoryAs, posixAttributes.permissions());
                }
                Files.move(pathCreateTempFileInSameDirectoryAs, this.zfpath, StandardCopyOption.REPLACE_EXISTING);
                this.hasUpdate = false;
            } catch (Throwable th7) {
                try {
                    throw th7;
                } catch (Throwable th8) {
                    try {
                        bufferedOutputStream.close();
                        throw th8;
                    } catch (Throwable th9) {
                        th7.addSuppressed(th9);
                        throw th8;
                    }
                }
            }
        }
    }

    private ZipPath toZipPath(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 1];
        bArr2[0] = 47;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        return new ZipPath(this, bArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update(Entry entry) {
        beginWrite();
        try {
            IndexNode indexNodePut = this.inodes.put(entry, entry);
            if (indexNodePut != null) {
                removeFromTree(indexNodePut);
            }
            int i = entry.type;
            if (i == 2 || i == 3 || i == 4) {
                IndexNode indexNode = this.inodes.get(this.LOOKUPKEY.as(getParent(entry.name)));
                entry.sibling = indexNode.child;
                indexNode.child = entry;
            }
            this.hasUpdate = true;
        } finally {
            endWrite();
        }
    }

    private void updateDelete(IndexNode indexNode) {
        beginWrite();
        try {
            removeFromTree(indexNode);
            this.inodes.remove(indexNode);
            this.hasUpdate = true;
        } finally {
            endWrite();
        }
    }

    public static void zerror(String str) {
        throw new ZipError(str);
    }

    @Override // java.nio.file.FileSystem, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        beginWrite();
        try {
            if (!this.isOpen) {
                endWrite();
                return;
            }
            this.isOpen = false;
            endWrite();
            if (!this.streams.isEmpty()) {
                Iterator it = new HashSet(this.streams).iterator();
                while (it.hasNext()) {
                    ((InputStream) it.next()).close();
                }
            }
            beginWrite();
            try {
                sync();
                this.ch.close();
                endWrite();
                synchronized (this.inflaters) {
                    try {
                        Iterator<Inflater> it2 = this.inflaters.iterator();
                        while (it2.hasNext()) {
                            it2.next().end();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                synchronized (this.deflaters) {
                    try {
                        Iterator<Deflater> it3 = this.deflaters.iterator();
                        while (it3.hasNext()) {
                            it3.next().end();
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
                beginWrite();
                IOException iOException = null;
                try {
                    this.inodes = null;
                    endWrite();
                    synchronized (this.tmppaths) {
                        Iterator<Path> it4 = this.tmppaths.iterator();
                        while (it4.hasNext()) {
                            try {
                                Files.deleteIfExists(it4.next());
                            } catch (IOException e) {
                                if (iOException == null) {
                                    iOException = e;
                                } else {
                                    iOException.addSuppressed(e);
                                }
                            }
                        }
                    }
                    this.provider.removeFileSystem(this.zfpath, this);
                    if (iOException != null) {
                        throw iOException;
                    }
                } catch (Throwable th3) {
                    endWrite();
                    throw th3;
                }
            } catch (Throwable th4) {
                endWrite();
                throw th4;
            }
        } catch (Throwable th5) {
            endWrite();
            throw th5;
        }
    }

    public void copyFile(boolean z, byte[] bArr, byte[] bArr2, CopyOption... copyOptionArr) throws IOException {
        checkWritable();
        if (Arrays.equals(bArr, bArr2)) {
            return;
        }
        beginWrite();
        try {
            ensureOpen();
            Entry entry0 = getEntry0(bArr);
            if (entry0 == null) {
                throw new NoSuchFileException(getString(bArr));
            }
            if (entry0.isDir()) {
                createDirectory(bArr2, new FileAttribute[0]);
                endWrite();
                return;
            }
            boolean z2 = false;
            boolean z3 = false;
            for (CopyOption copyOption : copyOptionArr) {
                if (copyOption == StandardCopyOption.REPLACE_EXISTING) {
                    z2 = true;
                } else if (copyOption == StandardCopyOption.COPY_ATTRIBUTES) {
                    z3 = true;
                }
            }
            if (getEntry0(bArr2) == null) {
                checkParents(bArr2);
            } else if (!z2) {
                throw new FileAlreadyExistsException(getString(bArr2));
            }
            Entry entry = new Entry(entry0, 4);
            entry.name(bArr2);
            int i = entry0.type;
            if (i == 2 || i == 3) {
                entry.type = i;
                byte[] bArr3 = entry0.bytes;
                if (z) {
                    entry.bytes = bArr3;
                    entry.file = entry0.file;
                } else if (bArr3 != null) {
                    entry.bytes = Arrays.copyOf(bArr3, bArr3.length);
                } else if (entry0.file != null) {
                    Path tempPathForEntry = getTempPathForEntry(null);
                    entry.file = tempPathForEntry;
                    Files.copy(entry0.file, tempPathForEntry, StandardCopyOption.REPLACE_EXISTING);
                }
            }
            if (!z3) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                entry.ctime = jCurrentTimeMillis;
                entry.atime = jCurrentTimeMillis;
                entry.mtime = jCurrentTimeMillis;
            }
            update(entry);
            if (z) {
                updateDelete(entry0);
            }
            endWrite();
        } catch (Throwable th) {
            endWrite();
            throw th;
        }
    }

    public void createDirectory(byte[] bArr, FileAttribute<?>... fileAttributeArr) throws IOException {
        checkWritable();
        byte[] directoryPath = ZipUtils.toDirectoryPath(bArr);
        beginWrite();
        try {
            ensureOpen();
            if (directoryPath.length == 0 || exists(directoryPath)) {
                throw new FileAlreadyExistsException(getString(directoryPath));
            }
            checkParents(directoryPath);
            Entry entry = new Entry(directoryPath, 2);
            entry.method = 0;
            update(entry);
            endWrite();
        } catch (Throwable th) {
            endWrite();
            throw th;
        }
    }

    public void deleteFile(byte[] bArr, boolean z) throws IOException {
        checkWritable();
        IndexNode inode = getInode(bArr);
        if (inode != null) {
            if (inode.isDir() && inode.child != null) {
                throw new DirectoryNotEmptyException(getString(bArr));
            }
            updateDelete(inode);
            return;
        }
        if (bArr != null && bArr.length == 0) {
            throw new ZipException("root directory </> can't not be delete");
        }
        if (z) {
            throw new NoSuchFileException(getString(bArr));
        }
    }

    public boolean exists(byte[] bArr) throws IOException {
        beginRead();
        try {
            ensureOpen();
            return getInode(bArr) != null;
        } finally {
            endRead();
        }
    }

    public void finalize() throws IOException {
        close();
    }

    public final byte[] getBytes(String str) {
        return this.zc.getBytes(str);
    }

    public ZipPath getDefaultDir() {
        return this.defaultdir;
    }

    public ZipFileAttributes getFileAttributes(byte[] bArr) throws IOException {
        beginRead();
        try {
            ensureOpen();
            Entry entry0 = getEntry0(bArr);
            if (entry0 == null) {
                IndexNode inode = getInode(bArr);
                if (inode == null) {
                    return null;
                }
                entry0 = new Entry(inode.name);
                entry0.method = 0;
                entry0.ctime = -1L;
                entry0.atime = -1L;
                entry0.mtime = -1L;
            }
            return new ZipFileAttributes(entry0);
        } finally {
            endRead();
        }
    }

    public FileStore getFileStore(ZipPath zipPath) {
        return new ZipFileStore(zipPath);
    }

    @Override // java.nio.file.FileSystem
    public Iterable<FileStore> getFileStores() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new ZipFileStore(new ZipPath(this, new byte[]{47})));
        return arrayList;
    }

    @Override // java.nio.file.FileSystem
    public ZipPath getPath(String str, String... strArr) {
        if (strArr.length != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            for (String str2 : strArr) {
                if (str2.length() > 0) {
                    if (sb.length() > 0) {
                        sb.append('/');
                    }
                    sb.append(str2);
                }
            }
            str = sb.toString();
        }
        return new ZipPath(this, str);
    }

    @Override // java.nio.file.FileSystem
    public PathMatcher getPathMatcher(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf <= 0 || iIndexOf == str.length()) {
            j2d.a();
            return null;
        }
        String strSubstring = str.substring(0, iIndexOf);
        String strSubstring2 = str.substring(iIndexOf + 1);
        if (strSubstring.equals(GLOB_SYNTAX)) {
            strSubstring2 = ZipUtils.toRegexPattern(strSubstring2);
        } else if (!strSubstring.equals(REGEX_SYNTAX)) {
            b9g.a("Syntax '", strSubstring, "' not recognized");
            return null;
        }
        final Pattern patternCompile = Pattern.compile(strSubstring2);
        return new PathMatcher() { // from class: com.sun.nio.zipfs.ZipFileSystem.1
            @Override // java.nio.file.PathMatcher
            public boolean matches(Path path) {
                return patternCompile.matcher(path.toString()).matches();
            }
        };
    }

    @Override // java.nio.file.FileSystem
    public Iterable<Path> getRootDirectories() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ZipPath(this, new byte[]{47}));
        return arrayList;
    }

    @Override // java.nio.file.FileSystem
    public String getSeparator() {
        return PsuedoNames.PSEUDONAME_ROOT;
    }

    public final String getString(byte[] bArr) {
        return this.zc.toString(bArr);
    }

    @Override // java.nio.file.FileSystem
    public UserPrincipalLookupService getUserPrincipalLookupService() {
        throw new UnsupportedOperationException();
    }

    public Path getZipFile() {
        return this.zfpath;
    }

    public boolean isDirectory(byte[] bArr) throws IOException {
        beginRead();
        try {
            IndexNode inode = getInode(bArr);
            return inode != null && inode.isDir();
        } finally {
            endRead();
        }
    }

    @Override // java.nio.file.FileSystem
    public boolean isOpen() {
        return this.isOpen;
    }

    @Override // java.nio.file.FileSystem
    public boolean isReadOnly() {
        return this.readOnly;
    }

    public Iterator<Path> iteratorOf(byte[] bArr, DirectoryStream.Filter<? super Path> filter) throws IOException {
        beginWrite();
        try {
            ensureOpen();
            IndexNode inode = getInode(bArr);
            if (inode == null) {
                throw new NotDirectoryException(getString(bArr));
            }
            ArrayList arrayList = new ArrayList();
            for (IndexNode indexNode = inode.child; indexNode != null; indexNode = indexNode.sibling) {
                ZipPath zipPath = toZipPath(indexNode.name);
                if (filter == null || filter.accept(zipPath)) {
                    arrayList.add(zipPath);
                }
            }
            Iterator<Path> it = arrayList.iterator();
            endWrite();
            return it;
        } catch (Throwable th) {
            endWrite();
            throw th;
        }
    }

    public SeekableByteChannel newByteChannel(byte[] bArr, Set<? extends OpenOption> set, FileAttribute<?>... fileAttributeArr) throws IOException {
        Entry entry0;
        checkOptions(set);
        if (set.contains(StandardOpenOption.WRITE) || set.contains(StandardOpenOption.APPEND)) {
            checkWritable();
            beginRead();
            try {
                WritableByteChannel writableByteChannelNewChannel = Channels.newChannel(newOutputStream(bArr, (OpenOption[]) set.toArray(new OpenOption[0])));
                long j = 0;
                if (set.contains(StandardOpenOption.APPEND) && (entry0 = getEntry0(bArr)) != null) {
                    long j2 = entry0.size;
                    if (j2 >= 0) {
                        j = j2;
                    }
                }
                return new SeekableByteChannel(j, writableByteChannelNewChannel) { // from class: com.sun.nio.zipfs.ZipFileSystem.2
                    final /* synthetic */ long val$offset;
                    final /* synthetic */ WritableByteChannel val$wbc;
                    long written;

                    {
                        this.val$offset = j;
                        this.val$wbc = writableByteChannelNewChannel;
                        this.written = j;
                    }

                    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
                    public void close() throws IOException {
                        this.val$wbc.close();
                    }

                    @Override // java.nio.channels.Channel
                    public boolean isOpen() {
                        return this.val$wbc.isOpen();
                    }

                    @Override // java.nio.channels.SeekableByteChannel
                    public SeekableByteChannel position(long j3) throws IOException {
                        throw new UnsupportedOperationException();
                    }

                    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
                    public int read(ByteBuffer byteBuffer) throws IOException {
                        throw new UnsupportedOperationException();
                    }

                    @Override // java.nio.channels.SeekableByteChannel
                    public long size() throws IOException {
                        return this.written;
                    }

                    @Override // java.nio.channels.SeekableByteChannel
                    public SeekableByteChannel truncate(long j3) throws IOException {
                        throw new UnsupportedOperationException();
                    }

                    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
                    public int write(ByteBuffer byteBuffer) throws IOException {
                        int iWrite = this.val$wbc.write(byteBuffer);
                        this.written += (long) iWrite;
                        return iWrite;
                    }

                    @Override // java.nio.channels.SeekableByteChannel
                    public long position() throws IOException {
                        return this.written;
                    }
                };
            } finally {
                endRead();
            }
        }
        beginRead();
        try {
            ensureOpen();
            Entry entry1 = getEntry0(bArr);
            if (entry1 == null || entry1.isDir()) {
                throw new NoSuchFileException(getString(bArr));
            }
            final ReadableByteChannel readableByteChannelNewChannel = Channels.newChannel(getInputStream(entry1));
            final long j3 = entry1.size;
            SeekableByteChannel seekableByteChannel = new SeekableByteChannel() { // from class: com.sun.nio.zipfs.ZipFileSystem.3
                long read = 0;

                @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    readableByteChannelNewChannel.close();
                }

                @Override // java.nio.channels.Channel
                public boolean isOpen() {
                    return readableByteChannelNewChannel.isOpen();
                }

                @Override // java.nio.channels.SeekableByteChannel
                public SeekableByteChannel position(long j4) throws IOException {
                    throw new UnsupportedOperationException();
                }

                @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
                public int read(ByteBuffer byteBuffer) throws IOException {
                    int i = readableByteChannelNewChannel.read(byteBuffer);
                    if (i > 0) {
                        this.read += (long) i;
                    }
                    return i;
                }

                @Override // java.nio.channels.SeekableByteChannel
                public long size() throws IOException {
                    return j3;
                }

                @Override // java.nio.channels.SeekableByteChannel
                public SeekableByteChannel truncate(long j4) throws IOException {
                    throw new NonWritableChannelException();
                }

                @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
                public int write(ByteBuffer byteBuffer) throws IOException {
                    throw new NonWritableChannelException();
                }

                @Override // java.nio.channels.SeekableByteChannel
                public long position() throws IOException {
                    return this.read;
                }
            };
            endRead();
            return seekableByteChannel;
        } catch (Throwable th) {
            endRead();
            throw th;
        }
    }

    public FileChannel newFileChannel(byte[] bArr, Set<? extends OpenOption> set, FileAttribute<?>... fileAttributeArr) throws Throwable {
        Throwable th;
        checkOptions(set);
        boolean z = set.contains(StandardOpenOption.WRITE) || set.contains(StandardOpenOption.APPEND);
        beginRead();
        try {
            ensureOpen();
            Entry entry0 = getEntry0(bArr);
            try {
                if (z) {
                    try {
                        checkWritable();
                        if (entry0 == null) {
                            if (!set.contains(StandardOpenOption.CREATE) && !set.contains(StandardOpenOption.CREATE_NEW)) {
                                throw new NoSuchFileException(getString(bArr));
                            }
                        } else {
                            if (set.contains(StandardOpenOption.CREATE_NEW)) {
                                throw new FileAlreadyExistsException(getString(bArr));
                            }
                            if (entry0.isDir()) {
                                throw new FileAlreadyExistsException("directory <" + getString(bArr) + "> exists");
                            }
                        }
                        HashSet hashSet = new HashSet(set);
                        hashSet.remove(StandardOpenOption.CREATE_NEW);
                        set = hashSet;
                    } catch (Throwable th2) {
                        th = th2;
                        endRead();
                        throw th;
                    }
                } else if (entry0 == null || entry0.isDir()) {
                    throw new NoSuchFileException(getString(bArr));
                }
                boolean z2 = entry0 != null && entry0.type == 3;
                Path tempPathForEntry = z2 ? entry0.file : getTempPathForEntry(bArr);
                FileChannel fileChannelNewFileChannel = tempPathForEntry.getFileSystem().provider().newFileChannel(tempPathForEntry, set, fileAttributeArr);
                if (!z2) {
                    entry0 = new Entry(bArr, tempPathForEntry, 3);
                }
                Entry entry = entry0;
                if (z) {
                    entry.flag = 8;
                    entry.method = 8;
                }
                4 r4 = new 4(this, fileChannelNewFileChannel, z, entry, z2, tempPathForEntry);
                endRead();
                return r4;
            } catch (Throwable th3) {
                th = th3;
                th = th;
                endRead();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public InputStream newInputStream(byte[] bArr) throws IOException {
        beginRead();
        try {
            ensureOpen();
            Entry entry0 = getEntry0(bArr);
            if (entry0 == null) {
                throw new NoSuchFileException(getString(bArr));
            }
            if (entry0.isDir()) {
                throw new FileSystemException(getString(bArr), "is a directory", null);
            }
            InputStream inputStream = getInputStream(entry0);
            endRead();
            return inputStream;
        } catch (Throwable th) {
            endRead();
            throw th;
        }
    }

    public OutputStream newOutputStream(byte[] bArr, OpenOption... openOptionArr) throws IOException {
        checkWritable();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        for (OpenOption openOption : openOptionArr) {
            if (openOption == StandardOpenOption.READ) {
                w01.a("READ not allowed");
                return null;
            }
            if (openOption == StandardOpenOption.CREATE_NEW) {
                z4 = true;
            }
            if (openOption == StandardOpenOption.CREATE) {
                z3 = true;
            }
            if (openOption == StandardOpenOption.APPEND) {
                z = true;
            }
            if (openOption == StandardOpenOption.TRUNCATE_EXISTING) {
                z2 = true;
            }
        }
        if (z && z2) {
            w01.a("APPEND + TRUNCATE_EXISTING not allowed");
            return null;
        }
        beginRead();
        try {
            ensureOpen();
            Entry entry0 = getEntry0(bArr);
            if (entry0 == null) {
                if (!z3 && !z4) {
                    throw new NoSuchFileException(getString(bArr));
                }
                checkParents(bArr);
                OutputStream outputStream = getOutputStream(new Entry(bArr, 2));
                endRead();
                return outputStream;
            }
            if (entry0.isDir() || z4) {
                throw new FileAlreadyExistsException(getString(bArr));
            }
            if (!z) {
                OutputStream outputStream2 = getOutputStream(new Entry(entry0, 2));
                endRead();
                return outputStream2;
            }
            InputStream inputStream = getInputStream(entry0);
            OutputStream outputStream3 = getOutputStream(new Entry(entry0, 2));
            copyStream(inputStream, outputStream3);
            inputStream.close();
            endRead();
            return outputStream3;
        } catch (Throwable th) {
            endRead();
            throw th;
        }
    }

    @Override // java.nio.file.FileSystem
    public WatchService newWatchService() {
        throw new UnsupportedOperationException();
    }

    @Override // java.nio.file.FileSystem
    public FileSystemProvider provider() {
        return this.provider;
    }

    public void setTimes(byte[] bArr, FileTime fileTime, FileTime fileTime2, FileTime fileTime3) throws IOException {
        checkWritable();
        beginWrite();
        try {
            ensureOpen();
            Entry entry0 = getEntry0(bArr);
            if (entry0 == null) {
                throw new NoSuchFileException(getString(bArr));
            }
            if (entry0.type == 1) {
                entry0.type = 4;
            }
            if (fileTime != null) {
                entry0.mtime = fileTime.toMillis();
            }
            if (fileTime2 != null) {
                entry0.atime = fileTime2.toMillis();
            }
            if (fileTime3 != null) {
                entry0.ctime = fileTime3.toMillis();
            }
            update(entry0);
            endWrite();
        } catch (Throwable th) {
            endWrite();
            throw th;
        }
    }

    @Override // java.nio.file.FileSystem
    public Set<String> supportedFileAttributeViews() {
        return supportedFileAttributeViews;
    }

    public String toString() {
        return this.zfpath.toString();
    }

    public static class Entry extends IndexNode {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final int CEN = 1;
        static final int COPY = 4;
        static final int FILECH = 3;
        static final int NEW = 2;
        long atime;
        int attrs;
        long attrsEx;
        byte[] bytes;
        byte[] comment;
        long crc;
        long csize;
        long ctime;
        int disk;
        byte[] extra;
        Path file;
        int flag;
        long locoff;
        int method;
        long mtime;
        long size;
        int type;
        int version;
        int versionMade;

        public Entry(Entry entry, int i) {
            this.type = 1;
            this.method = -1;
            this.mtime = -1L;
            this.atime = -1L;
            this.ctime = -1L;
            this.crc = -1L;
            this.csize = -1L;
            this.size = -1L;
            name(entry.name);
            this.version = entry.version;
            this.ctime = entry.ctime;
            this.atime = entry.atime;
            this.mtime = entry.mtime;
            this.crc = entry.crc;
            this.size = entry.size;
            this.csize = entry.csize;
            this.method = entry.method;
            this.extra = entry.extra;
            this.versionMade = entry.versionMade;
            this.disk = entry.disk;
            this.attrs = entry.attrs;
            this.attrsEx = entry.attrsEx;
            this.locoff = entry.locoff;
            this.comment = entry.comment;
            this.type = i;
        }

        private Entry cen(ZipFileSystem zipFileSystem, int i) throws IOException {
            byte[] bArr = zipFileSystem.cen;
            if (!ZipConstants.cenSigAt(bArr, i)) {
                ZipFileSystem.zerror("invalid CEN header (bad signature)");
            }
            this.versionMade = ZipConstants.CENVEM(bArr, i);
            this.version = ZipConstants.CENVER(bArr, i);
            this.flag = ZipConstants.CENFLG(bArr, i);
            this.method = ZipConstants.CENHOW(bArr, i);
            this.mtime = ZipUtils.dosToJavaTime(ZipConstants.CENTIM(bArr, i));
            this.crc = ZipConstants.CENCRC(bArr, i);
            this.csize = ZipConstants.CENSIZ(bArr, i);
            this.size = ZipConstants.CENLEN(bArr, i);
            int iCENNAM = ZipConstants.CENNAM(bArr, i);
            int iCENEXT = ZipConstants.CENEXT(bArr, i);
            int iCENCOM = ZipConstants.CENCOM(bArr, i);
            this.disk = ZipConstants.CENDSK(bArr, i);
            this.attrs = ZipConstants.CENATT(bArr, i);
            this.attrsEx = ZipConstants.CENATX(bArr, i);
            this.locoff = ZipConstants.CENOFF(bArr, i);
            int i2 = i + 46;
            int i3 = iCENNAM + i2;
            name(Arrays.copyOfRange(bArr, i2, i3));
            if (iCENEXT > 0) {
                int i4 = iCENEXT + i3;
                this.extra = Arrays.copyOfRange(bArr, i3, i4);
                readExtra(zipFileSystem);
                i3 = i4;
            }
            if (iCENCOM > 0) {
                this.comment = Arrays.copyOfRange(bArr, i3, iCENCOM + i3);
            }
            return this;
        }

        public static Entry readCEN(ZipFileSystem zipFileSystem, int i) throws IOException {
            return new Entry().cen(zipFileSystem, i);
        }

        public static Entry readLOC(ZipFileSystem zipFileSystem, long j, byte[] bArr) throws IOException {
            return new Entry().loc(zipFileSystem, j, bArr);
        }

        public Entry loc(ZipFileSystem zipFileSystem, long j, byte[] bArr) throws IOException {
            if (zipFileSystem.readFullyAt(bArr, 0, 30L, j) != 30) {
                throw new ZipException("loc: reading failed");
            }
            int i = 0;
            if (!ZipConstants.locSigAt(bArr, 0)) {
                throw new ZipException("loc: wrong sig ->" + Long.toString(ZipConstants.getSig(bArr, 0), 16));
            }
            this.version = ZipConstants.LOCVER(bArr);
            this.flag = ZipConstants.LOCFLG(bArr);
            this.method = ZipConstants.LOCHOW(bArr);
            this.mtime = ZipUtils.dosToJavaTime(ZipConstants.LOCTIM(bArr));
            this.crc = ZipConstants.LOCCRC(bArr);
            this.csize = ZipConstants.LOCSIZ(bArr);
            this.size = ZipConstants.LOCLEN(bArr);
            int iLOCNAM = ZipConstants.LOCNAM(bArr);
            int iLOCEXT = ZipConstants.LOCEXT(bArr);
            byte[] bArr2 = new byte[iLOCNAM];
            this.name = bArr2;
            long j2 = iLOCNAM;
            long j3 = j + 30;
            if (zipFileSystem.readFullyAt(bArr2, 0, j2, j3) != j2) {
                throw new ZipException("loc: name reading failed");
            }
            if (iLOCEXT > 0) {
                byte[] bArr3 = new byte[iLOCEXT];
                this.extra = bArr3;
                long j4 = iLOCEXT;
                if (zipFileSystem.readFullyAt(bArr3, 0, j4, j3 + j2) != j4) {
                    throw new ZipException("loc: ext reading failed");
                }
            }
            if ((this.flag & 8) != 0) {
                Entry entry0 = zipFileSystem.getEntry0(this.name);
                if (entry0 == null) {
                    throw new ZipException("loc: name not found in cen");
                }
                this.size = entry0.size;
                this.csize = entry0.csize;
                return this;
            }
            if (this.extra != null && (this.size == 4294967295L || this.csize == 4294967295L)) {
                while (i + 20 < iLOCEXT) {
                    int iSH = ZipConstants.SH(this.extra, i + 2);
                    if (ZipConstants.SH(this.extra, i) == 1 && iSH == 16) {
                        this.size = ZipConstants.LL(this.extra, i + 4);
                        this.csize = ZipConstants.LL(this.extra, i + 12);
                        break;
                    }
                    i += iSH + 4;
                }
            }
            return this;
        }

        /* JADX WARN: Code duplicated, block: B:105:0x0188 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:69:0x0167  */
        /* JADX WARN: Code duplicated, block: B:72:0x016c  */
        /* JADX WARN: Code duplicated, block: B:73:0x0175 A[PHI: r5
          0x0175: PHI (r5v2 int) = (r5v1 int), (r5v4 int) binds: [B:68:0x0165, B:72:0x016c] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:75:0x017b  */
        /* JADX WARN: Code duplicated, block: B:99:0x0188 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        public void readExtra(ZipFileSystem zipFileSystem) throws IOException {
            int i;
            byte[] bArr = this.extra;
            if (bArr == null) {
                return;
            }
            int length = bArr.length;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int i4 = i2 + 4;
                if (i4 >= length) {
                    break;
                }
                int iSH = ZipConstants.SH(this.extra, i2);
                int iSH2 = ZipConstants.SH(this.extra, i2 + 2);
                if (i4 + iSH2 > length) {
                    break;
                }
                if (iSH != 1) {
                    if (iSH != 10) {
                        if (iSH != 21589) {
                            byte[] bArr2 = this.extra;
                            int i5 = iSH2 + 4;
                            System.arraycopy(bArr2, i2, bArr2, i3, i5);
                            i3 += i5;
                        } else {
                            byte[] bArr3 = new byte[30];
                            if (zipFileSystem.readFullyAt(bArr3, 0, 30L, this.locoff) != 30) {
                                throw new ZipException("loc: reading failed");
                            }
                            if (!ZipConstants.locSigAt(bArr3, 0)) {
                                throw new ZipException("loc: wrong sig ->" + Long.toString(ZipConstants.getSig(bArr3, 0), 16));
                            }
                            int iLOCEXT = ZipConstants.LOCEXT(bArr3);
                            if (iLOCEXT < 9) {
                                continue;
                            } else {
                                int iLOCNAM = ZipConstants.LOCNAM(bArr3);
                                byte[] bArr4 = new byte[iLOCEXT];
                                long j = iLOCEXT;
                                if (zipFileSystem.readFullyAt(bArr4, 0, j, this.locoff + 30 + ((long) iLOCNAM)) != j) {
                                    throw new ZipException("loc extra: reading failed");
                                }
                                int i6 = 0;
                                while (true) {
                                    int i7 = i6 + 4;
                                    if (i7 >= iLOCEXT) {
                                        break;
                                    }
                                    int iSH3 = ZipConstants.SH(bArr4, i6);
                                    int iSH4 = ZipConstants.SH(bArr4, i6 + 2);
                                    if (iSH3 == 21589) {
                                        int i8 = (iSH4 + i7) - 4;
                                        int i9 = i6 + 5;
                                        int iCH = ZipConstants.CH(bArr4, i7);
                                        if ((iCH & 1) != 0 && i9 <= i8) {
                                            this.mtime = ZipUtils.unixToJavaTime(ZipConstants.LG(bArr4, i9));
                                            i9 = i6 + 9;
                                        }
                                        if ((iCH & 2) != 0 && i9 <= i8) {
                                            this.atime = ZipUtils.unixToJavaTime(ZipConstants.LG(bArr4, i9));
                                            i9 += 4;
                                        }
                                        if ((iCH & 4) != 0 && i9 <= i8) {
                                            this.ctime = ZipUtils.unixToJavaTime(ZipConstants.LG(bArr4, i9));
                                            break;
                                        }
                                        break;
                                    }
                                    i6 = i7 + iSH4;
                                }
                            }
                        }
                    } else if (iSH2 >= 32) {
                        if (ZipConstants.SH(this.extra, i2 + 8) == 1 && ZipConstants.SH(this.extra, i2 + 10) == 24) {
                            this.mtime = ZipUtils.winToJavaTime(ZipConstants.LL(this.extra, i2 + 12));
                            this.atime = ZipUtils.winToJavaTime(ZipConstants.LL(this.extra, i2 + 20));
                            this.ctime = ZipUtils.winToJavaTime(ZipConstants.LL(this.extra, i2 + 28));
                        }
                    }
                } else if (this.size == 4294967295L) {
                    int i10 = i2 + 12;
                    if (i10 <= length) {
                        this.size = ZipConstants.LL(this.extra, i4);
                        i4 = i10;
                        if (this.csize != 4294967295L) {
                            i = i4 + 8;
                            if (i > length) {
                                this.csize = ZipConstants.LL(this.extra, i4);
                                i4 = i;
                                if (this.locoff != 4294967295L) {
                                }
                            }
                        } else if (this.locoff != 4294967295L) {
                        }
                    }
                } else if (this.csize != 4294967295L) {
                    i = i4 + 8;
                    if (i > length) {
                        this.csize = ZipConstants.LL(this.extra, i4);
                        i4 = i;
                        if (this.locoff != 4294967295L) {
                        }
                    }
                } else if (this.locoff != 4294967295L && i4 + 8 <= length) {
                    this.locoff = ZipConstants.LL(this.extra, i4);
                }
                i2 += iSH2 + 4;
            }
            if (i3 != 0) {
                byte[] bArr5 = this.extra;
                if (i3 != bArr5.length) {
                    this.extra = Arrays.copyOf(bArr5, i3);
                    return;
                }
            }
            this.extra = null;
        }

        public int version() throws ZipException {
            int i = this.method;
            if (i == 8) {
                return 20;
            }
            if (i == 0) {
                return 10;
            }
            throw new ZipException("unsupported compression method");
        }

        /* JADX WARN: Code duplicated, block: B:45:0x008d  */
        /* JADX WARN: Code duplicated, block: B:46:0x0096  */
        /* JADX WARN: Code duplicated, block: B:49:0x00cd  */
        /* JADX WARN: Code duplicated, block: B:50:0x00d9  */
        /* JADX WARN: Code duplicated, block: B:53:0x00f2  */
        /* JADX WARN: Code duplicated, block: B:55:0x00ff  */
        /* JADX WARN: Code duplicated, block: B:58:0x0108  */
        /* JADX WARN: Code duplicated, block: B:61:0x0111  */
        /* JADX WARN: Code duplicated, block: B:63:0x0118  */
        /* JADX WARN: Code duplicated, block: B:65:0x014b  */
        /* JADX WARN: Code duplicated, block: B:67:0x015d  */
        /* JADX WARN: Code duplicated, block: B:68:0x0162  */
        /* JADX WARN: Code duplicated, block: B:72:0x0173  */
        /* JADX WARN: Code duplicated, block: B:75:0x017a  */
        public int writeCEN(OutputStream outputStream) throws IOException {
            int i;
            long j;
            int i2;
            int i3;
            int i4;
            byte[] bArr;
            byte[] bArr2;
            int iVersion = version();
            long j2 = this.csize;
            long j3 = this.size;
            long j4 = this.locoff;
            byte[] bArr3 = this.name;
            int length = bArr3 != null ? bArr3.length : 0;
            byte[] bArr4 = this.extra;
            int length2 = bArr4 != null ? bArr4.length : 0;
            byte[] bArr5 = this.comment;
            int length3 = bArr5 != null ? bArr5.length : 0;
            long j5 = 4294967295L;
            if (j2 >= 4294967295L) {
                i = 8;
            } else {
                j5 = j2;
                i = 0;
            }
            if (j3 >= j5) {
                i += 8;
                j3 = 4294967295L;
            }
            if (j4 >= j5) {
                i += 8;
                j4 = j5;
            }
            if (i != 0) {
                i += 4;
            }
            int i5 = 0;
            boolean z = false;
            while (true) {
                j = j5;
                if (i5 + 4 >= length2) {
                    break;
                }
                int iSH = ZipConstants.SH(this.extra, i5);
                int i6 = i;
                int iSH2 = ZipConstants.SH(this.extra, i5 + 2);
                if (iSH == 21589 || iSH == 10) {
                    z = true;
                }
                i5 += iSH2 + 4;
                j5 = j;
                i = i6;
            }
            int i7 = i;
            if (!z) {
                if (ZipFileSystem.isWindows) {
                    i2 = 36;
                } else {
                    i3 = 9;
                    i2 = 0;
                }
                ZipUtils.writeInt(outputStream, ZipConstants.CENSIG);
                if (i7 != 0) {
                    ZipUtils.writeShort(outputStream, 45);
                    ZipUtils.writeShort(outputStream, 45);
                } else {
                    ZipUtils.writeShort(outputStream, iVersion);
                    ZipUtils.writeShort(outputStream, iVersion);
                }
                ZipUtils.writeShort(outputStream, this.flag);
                ZipUtils.writeShort(outputStream, this.method);
                ZipUtils.writeInt(outputStream, (int) ZipUtils.javaToDosTime(this.mtime));
                ZipUtils.writeInt(outputStream, this.crc);
                ZipUtils.writeInt(outputStream, j5);
                ZipUtils.writeInt(outputStream, j3);
                ZipUtils.writeShort(outputStream, this.name.length);
                ZipUtils.writeShort(outputStream, length2 + i7 + i2 + i3);
                if (this.comment != null) {
                    ZipUtils.writeShort(outputStream, Math.min(length3, 65535));
                    i4 = 0;
                } else {
                    i4 = 0;
                    ZipUtils.writeShort(outputStream, 0);
                }
                ZipUtils.writeShort(outputStream, i4);
                ZipUtils.writeShort(outputStream, i4);
                ZipUtils.writeInt(outputStream, 0L);
                ZipUtils.writeInt(outputStream, j4);
                ZipUtils.writeBytes(outputStream, this.name);
                if (i7 != 0) {
                    ZipUtils.writeShort(outputStream, 1);
                    ZipUtils.writeShort(outputStream, i7 - 4);
                    if (j3 == j) {
                        ZipUtils.writeLong(outputStream, this.size);
                    }
                    if (j5 == j) {
                        ZipUtils.writeLong(outputStream, this.csize);
                    }
                    if (j4 == j) {
                        ZipUtils.writeLong(outputStream, this.locoff);
                    }
                }
                if (i2 != 0) {
                    ZipUtils.writeShort(outputStream, 10);
                    ZipUtils.writeShort(outputStream, i2 - 4);
                    ZipUtils.writeInt(outputStream, 0L);
                    ZipUtils.writeShort(outputStream, 1);
                    ZipUtils.writeShort(outputStream, 24);
                    ZipUtils.writeLong(outputStream, ZipUtils.javaToWinTime(this.mtime));
                    ZipUtils.writeLong(outputStream, ZipUtils.javaToWinTime(this.atime));
                    ZipUtils.writeLong(outputStream, ZipUtils.javaToWinTime(this.ctime));
                }
                if (i3 != 0) {
                    ZipUtils.writeShort(outputStream, 21589);
                    ZipUtils.writeShort(outputStream, i3 - 4);
                    if (this.ctime == -1) {
                        outputStream.write(3);
                    } else {
                        outputStream.write(7);
                    }
                    ZipUtils.writeInt(outputStream, ZipUtils.javaToUnixTime(this.mtime));
                }
                bArr = this.extra;
                if (bArr != null) {
                    ZipUtils.writeBytes(outputStream, bArr);
                }
                bArr2 = this.comment;
                if (bArr2 != null) {
                    ZipUtils.writeBytes(outputStream, bArr2);
                }
                return length + 46 + length2 + length3 + i7 + i2 + i3;
            }
            i2 = 0;
            i3 = 0;
            ZipUtils.writeInt(outputStream, ZipConstants.CENSIG);
            if (i7 != 0) {
                ZipUtils.writeShort(outputStream, 45);
                ZipUtils.writeShort(outputStream, 45);
            } else {
                ZipUtils.writeShort(outputStream, iVersion);
                ZipUtils.writeShort(outputStream, iVersion);
            }
            ZipUtils.writeShort(outputStream, this.flag);
            ZipUtils.writeShort(outputStream, this.method);
            ZipUtils.writeInt(outputStream, (int) ZipUtils.javaToDosTime(this.mtime));
            ZipUtils.writeInt(outputStream, this.crc);
            ZipUtils.writeInt(outputStream, j5);
            ZipUtils.writeInt(outputStream, j3);
            ZipUtils.writeShort(outputStream, this.name.length);
            ZipUtils.writeShort(outputStream, length2 + i7 + i2 + i3);
            if (this.comment != null) {
                ZipUtils.writeShort(outputStream, Math.min(length3, 65535));
                i4 = 0;
            } else {
                i4 = 0;
                ZipUtils.writeShort(outputStream, 0);
            }
            ZipUtils.writeShort(outputStream, i4);
            ZipUtils.writeShort(outputStream, i4);
            ZipUtils.writeInt(outputStream, 0L);
            ZipUtils.writeInt(outputStream, j4);
            ZipUtils.writeBytes(outputStream, this.name);
            if (i7 != 0) {
                ZipUtils.writeShort(outputStream, 1);
                ZipUtils.writeShort(outputStream, i7 - 4);
                if (j3 == j) {
                    ZipUtils.writeLong(outputStream, this.size);
                }
                if (j5 == j) {
                    ZipUtils.writeLong(outputStream, this.csize);
                }
                if (j4 == j) {
                    ZipUtils.writeLong(outputStream, this.locoff);
                }
            }
            if (i2 != 0) {
                ZipUtils.writeShort(outputStream, 10);
                ZipUtils.writeShort(outputStream, i2 - 4);
                ZipUtils.writeInt(outputStream, 0L);
                ZipUtils.writeShort(outputStream, 1);
                ZipUtils.writeShort(outputStream, 24);
                ZipUtils.writeLong(outputStream, ZipUtils.javaToWinTime(this.mtime));
                ZipUtils.writeLong(outputStream, ZipUtils.javaToWinTime(this.atime));
                ZipUtils.writeLong(outputStream, ZipUtils.javaToWinTime(this.ctime));
            }
            if (i3 != 0) {
                ZipUtils.writeShort(outputStream, 21589);
                ZipUtils.writeShort(outputStream, i3 - 4);
                if (this.ctime == -1) {
                    outputStream.write(3);
                } else {
                    outputStream.write(7);
                }
                ZipUtils.writeInt(outputStream, ZipUtils.javaToUnixTime(this.mtime));
            }
            bArr = this.extra;
            if (bArr != null) {
                ZipUtils.writeBytes(outputStream, bArr);
            }
            bArr2 = this.comment;
            if (bArr2 != null) {
                ZipUtils.writeBytes(outputStream, bArr2);
            }
            return length + 46 + length2 + length3 + i7 + i2 + i3;
        }

        public int writeEXT(OutputStream outputStream) throws IOException {
            ZipUtils.writeInt(outputStream, ZipConstants.EXTSIG);
            ZipUtils.writeInt(outputStream, this.crc);
            long j = this.csize;
            if (j >= 4294967295L || this.size >= 4294967295L) {
                ZipUtils.writeLong(outputStream, j);
                ZipUtils.writeLong(outputStream, this.size);
                return 24;
            }
            ZipUtils.writeInt(outputStream, j);
            ZipUtils.writeInt(outputStream, this.size);
            return 16;
        }

        public int writeLOC(OutputStream outputStream) throws IOException {
            int i;
            int i2;
            int i3;
            ZipUtils.writeInt(outputStream, ZipConstants.LOCSIG);
            version();
            byte[] bArr = this.name;
            if (bArr != null) {
                int length = bArr.length;
            }
            byte[] bArr2 = this.extra;
            int i4 = 0;
            int length2 = bArr2 != null ? bArr2.length : 0;
            if ((this.flag & 8) != 0) {
                ZipUtils.writeShort(outputStream, version());
                ZipUtils.writeShort(outputStream, this.flag);
                ZipUtils.writeShort(outputStream, this.method);
                ZipUtils.writeInt(outputStream, (int) ZipUtils.javaToDosTime(this.mtime));
                ZipUtils.writeInt(outputStream, 0L);
                ZipUtils.writeInt(outputStream, 0L);
                ZipUtils.writeInt(outputStream, 0L);
                i = 0;
                i2 = 0;
            } else {
                if (this.csize >= 4294967295L || this.size >= 4294967295L) {
                    ZipUtils.writeShort(outputStream, 45);
                    i = 20;
                } else {
                    ZipUtils.writeShort(outputStream, version());
                    i = 0;
                }
                ZipUtils.writeShort(outputStream, this.flag);
                ZipUtils.writeShort(outputStream, this.method);
                ZipUtils.writeInt(outputStream, (int) ZipUtils.javaToDosTime(this.mtime));
                ZipUtils.writeInt(outputStream, this.crc);
                if (i != 0) {
                    ZipUtils.writeInt(outputStream, 4294967295L);
                    ZipUtils.writeInt(outputStream, 4294967295L);
                } else {
                    ZipUtils.writeInt(outputStream, this.csize);
                    ZipUtils.writeInt(outputStream, this.size);
                }
                i2 = 0;
            }
            int i5 = i2;
            while (true) {
                if (i2 + 4 >= length2) {
                    break;
                }
                int iSH = ZipConstants.SH(this.extra, i2);
                int iSH2 = ZipConstants.SH(this.extra, i2 + 2);
                if (iSH == 21589 || iSH == 10) {
                    i5 = 1;
                }
                i2 += iSH2 + 4;
            }
            if (i5 != 0) {
                i3 = 0;
            } else if (ZipFileSystem.isWindows) {
                i3 = 0;
                i4 = 36;
            } else {
                i3 = this.atime != -1 ? 13 : 9;
                if (this.ctime != -1) {
                    i3 += 4;
                }
            }
            ZipUtils.writeShort(outputStream, this.name.length);
            ZipUtils.writeShort(outputStream, length2 + i + i4 + i3);
            ZipUtils.writeBytes(outputStream, this.name);
            if (i != 0) {
                ZipUtils.writeShort(outputStream, 1);
                ZipUtils.writeShort(outputStream, 16);
                ZipUtils.writeLong(outputStream, this.size);
                ZipUtils.writeLong(outputStream, this.csize);
            }
            if (i4 != 0) {
                ZipUtils.writeShort(outputStream, 10);
                ZipUtils.writeShort(outputStream, i4 - 4);
                ZipUtils.writeInt(outputStream, 0L);
                ZipUtils.writeShort(outputStream, 1);
                ZipUtils.writeShort(outputStream, 24);
                ZipUtils.writeLong(outputStream, ZipUtils.javaToWinTime(this.mtime));
                ZipUtils.writeLong(outputStream, ZipUtils.javaToWinTime(this.atime));
                ZipUtils.writeLong(outputStream, ZipUtils.javaToWinTime(this.ctime));
            }
            if (i3 != 0) {
                ZipUtils.writeShort(outputStream, 21589);
                ZipUtils.writeShort(outputStream, i3 - 4);
                int i6 = this.atime != -1 ? 3 : 1;
                if (this.ctime != -1) {
                    i6 |= 4;
                }
                outputStream.write(i6);
                ZipUtils.writeInt(outputStream, ZipUtils.javaToUnixTime(this.mtime));
                long j = this.atime;
                if (j != -1) {
                    ZipUtils.writeInt(outputStream, ZipUtils.javaToUnixTime(j));
                }
                long j2 = this.ctime;
                if (j2 != -1) {
                    ZipUtils.writeInt(outputStream, ZipUtils.javaToUnixTime(j2));
                }
            }
            byte[] bArr3 = this.extra;
            if (bArr3 != null) {
                ZipUtils.writeBytes(outputStream, bArr3);
            }
            return this.name.length + 30 + length2 + i + i4 + i3;
        }

        public static Entry readLOC(ZipFileSystem zipFileSystem, long j) throws IOException {
            return readLOC(zipFileSystem, j, new byte[1024]);
        }

        public Entry(byte[] bArr) {
            this.type = 1;
            this.method = -1;
            this.mtime = -1L;
            this.atime = -1L;
            this.ctime = -1L;
            this.crc = -1L;
            this.csize = -1L;
            this.size = -1L;
            name(bArr);
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.atime = jCurrentTimeMillis;
            this.ctime = jCurrentTimeMillis;
            this.mtime = jCurrentTimeMillis;
            this.crc = 0L;
            this.size = 0L;
            this.csize = 0L;
            this.method = 8;
        }

        public Entry(byte[] bArr, int i) {
            this(bArr);
            this.type = i;
        }

        public Entry() {
            this.type = 1;
            this.method = -1;
            this.mtime = -1L;
            this.atime = -1L;
            this.ctime = -1L;
            this.crc = -1L;
            this.csize = -1L;
            this.size = -1L;
        }

        public Entry(byte[] bArr, Path path, int i) {
            this(bArr, i);
            this.file = path;
            this.method = 0;
        }
    }

    public static class IndexNode {
        IndexNode child;
        int hashcode;
        byte[] name;
        int pos;
        IndexNode sibling;

        public IndexNode(byte[] bArr, int i) {
            this.pos = -1;
            name(bArr);
            this.pos = i;
        }

        public static final IndexNode keyOf(byte[] bArr) {
            return new IndexNode(bArr, -1);
        }

        public final IndexNode as(byte[] bArr) {
            name(bArr);
            return this;
        }

        public boolean equals(Object obj) {
            if (obj instanceof IndexNode) {
                return Arrays.equals(this.name, ((IndexNode) obj).name);
            }
            return false;
        }

        public int hashCode() {
            return this.hashcode;
        }

        public boolean isDir() {
            byte[] bArr = this.name;
            if (bArr != null) {
                return bArr.length == 0 || bArr[bArr.length - 1] == 47;
            }
            return false;
        }

        public final void name(byte[] bArr) {
            this.name = bArr;
            this.hashcode = Arrays.hashCode(bArr);
        }

        public IndexNode() {
            this.pos = -1;
        }
    }

    public class FileRolloverOutputStream extends OutputStream {
        private ByteArrayOutputStream baos;
        private final Entry entry;
        private OutputStream tmpFileOS;
        private long totalWritten;

        private FileRolloverOutputStream(Entry entry) {
            this.baos = new ByteArrayOutputStream(8192);
            this.totalWritten = 0L;
            this.entry = entry;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] toByteArray() {
            ByteArrayOutputStream byteArrayOutputStream = this.baos;
            if (byteArrayOutputStream == null) {
                return null;
            }
            return byteArrayOutputStream.toByteArray();
        }

        private void transferToFile() throws IOException {
            this.entry.file = ZipFileSystem.this.getTempPathForEntry(null);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(this.entry.file, new OpenOption[0]));
            this.tmpFileOS = bufferedOutputStream;
            this.baos.writeTo(bufferedOutputStream);
            this.baos = null;
        }

        private void writeToFile(int i) throws IOException {
            this.tmpFileOS.write(i);
            this.totalWritten++;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.baos = null;
            OutputStream outputStream = this.tmpFileOS;
            if (outputStream != null) {
                outputStream.close();
            }
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            OutputStream outputStream = this.tmpFileOS;
            if (outputStream != null) {
                outputStream.flush();
            }
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            if (this.tmpFileOS != null) {
                writeToFile(i);
            } else if (this.totalWritten + 1 < 10485760) {
                this.baos.write(i);
                this.totalWritten++;
            } else {
                transferToFile();
                writeToFile(i);
            }
        }

        private void writeToFile(byte[] bArr, int i, int i2) throws IOException {
            this.tmpFileOS.write(bArr, i, i2);
            this.totalWritten += (long) i2;
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.tmpFileOS != null) {
                writeToFile(bArr, i, i2);
                return;
            }
            long j = i2;
            if (this.totalWritten + j < 10485760) {
                this.baos.write(bArr, i, i2);
                this.totalWritten += j;
            } else {
                transferToFile();
                writeToFile(bArr, i, i2);
            }
        }
    }

    public final long readFullyAt(byte[] bArr, int i, long j, long j2) throws IOException {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.position(i);
        byteBufferWrap.limit((int) (((long) i) + j));
        return readFullyAt(byteBufferWrap, j2);
    }

    public class EntryInputStream extends InputStream {
        private long pos;
        protected long rem;
        protected final long size;
        private final SeekableByteChannel zfch;

        public EntryInputStream(Entry entry, SeekableByteChannel seekableByteChannel) throws IOException {
            this.zfch = seekableByteChannel;
            this.rem = entry.csize;
            this.size = entry.size;
            this.pos = ZipFileSystem.this.getDataPos(entry);
        }

        @Override // java.io.InputStream
        public int available() {
            long j = this.rem;
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.rem = 0L;
            ZipFileSystem.this.streams.remove(this);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            long j;
            ZipFileSystem.this.ensureOpen();
            long j2 = this.rem;
            if (j2 == 0) {
                return -1;
            }
            if (i2 <= 0) {
                return 0;
            }
            if (i2 > j2) {
                i2 = (int) j2;
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            byteBufferWrap.position(i);
            byteBufferWrap.limit(i + i2);
            synchronized (this.zfch) {
                j = this.zfch.position(this.pos).read(byteBufferWrap);
            }
            if (j > 0) {
                this.pos += j;
                this.rem -= j;
            }
            if (this.rem == 0) {
                close();
            }
            return (int) j;
        }

        public long size() {
            return this.size;
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            ZipFileSystem.this.ensureOpen();
            long j2 = this.rem;
            if (j > j2) {
                j = j2;
            }
            this.pos += j;
            long j3 = j2 - j;
            this.rem = j3;
            if (j3 == 0) {
                close();
            }
            return j;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            byte[] bArr = new byte[1];
            if (read(bArr, 0, 1) == 1) {
                return bArr[0] & 255;
            }
            return -1;
        }
    }
}
