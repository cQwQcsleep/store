package com.intellij.util.lang;

import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationKt;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayDeque;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class FileLoader implements Loader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final AtomicLong tempCounter;
    private final Predicate<? super String> nameFilter;
    private final Path path;
    private static final EnumSet<StandardOpenOption> READ_OPTIONS = EnumSet.of(StandardOpenOption.READ);
    private static final EnumSet<StandardOpenOption> WRITE_OPTIONS = EnumSet.of(StandardOpenOption.WRITE, StandardOpenOption.CREATE);
    private static final AtomicInteger totalLoaders = new AtomicInteger();
    private static final AtomicLong totalScanning = new AtomicLong();
    private static final AtomicLong totalSaving = new AtomicLong();
    private static final AtomicLong totalReading = new AtomicLong();
    private static final Boolean doFsActivityLogging = Boolean.FALSE;

    public static final class FileResource implements Resource {
        private final Path file;
        private URL url;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 1 || i == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[(i == 1 || i == 2) ? 2 : 3];
            if (i == 1 || i == 2) {
                objArr[0] = "com/intellij/util/lang/FileLoader$FileResource";
            } else {
                objArr[0] = "file";
            }
            if (i == 1) {
                objArr[1] = "getURL";
            } else if (i != 2) {
                objArr[1] = "com/intellij/util/lang/FileLoader$FileResource";
            } else {
                objArr[1] = "getBytes";
            }
            if (i != 1 && i != 2) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i != 1 && i != 2) {
                throw new IllegalArgumentException(str2);
            }
            throw new IllegalStateException(str2);
        }

        public FileResource(Path path) {
            if (path == null) {
                $$$reportNull$$$0(0);
            }
            this.file = path;
        }

        @Override // com.intellij.util.lang.Resource
        public InputStream getInputStream() throws IOException {
            return new BufferedInputStream(Files.newInputStream(this.file, new OpenOption[0]), 32000);
        }

        @Override // com.intellij.util.lang.Resource
        public URL getURL() {
            URL url = this.url;
            if (url == null) {
                try {
                    url = this.file.toUri().toURL();
                    this.url = url;
                } catch (MalformedURLException e) {
                    rc6.a(e);
                    return null;
                }
            }
            if (url == null) {
                $$$reportNull$$$0(1);
            }
            return url;
        }

        public String toString() {
            return this.file.toString();
        }
    }

    public static final class LoaderData implements ClasspathCache.IndexRegistrar {
        private final long[] classPackageHashes;
        private final NameFilter nameFilter;
        private final long[] resourcePackageHashes;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i == 1) {
                objArr[0] = "com/intellij/util/lang/FileLoader$LoaderData";
            } else if (i != 2) {
                objArr[0] = "nameFilter";
            } else {
                objArr[0] = "buffer";
            }
            if (i != 1) {
                objArr[1] = "com/intellij/util/lang/FileLoader$LoaderData";
            } else {
                objArr[1] = "getNameFilter";
            }
            if (i != 1) {
                if (i != 2) {
                    objArr[2] = "<init>";
                } else {
                    objArr[2] = "save";
                }
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        public LoaderData(long[] jArr, long[] jArr2, NameFilter nameFilter) {
            if (nameFilter == null) {
                $$$reportNull$$$0(0);
            }
            this.resourcePackageHashes = jArr;
            this.classPackageHashes = jArr2;
            this.nameFilter = nameFilter;
        }

        public int approximateSizeInBytes() {
            return (this.classPackageHashes.length * 8) + 8 + (this.resourcePackageHashes.length * 8) + this.nameFilter.filter.sizeInBytes();
        }

        @Override // com.intellij.util.lang.ClasspathCache.IndexRegistrar
        public int classPackageCount() {
            return this.classPackageHashes.length;
        }

        @Override // com.intellij.util.lang.ClasspathCache.IndexRegistrar
        public long[] classPackages() {
            return this.classPackageHashes;
        }

        @Override // com.intellij.util.lang.ClasspathCache.IndexRegistrar
        public Predicate<String> getNameFilter() {
            NameFilter nameFilter = this.nameFilter;
            if (nameFilter == null) {
                $$$reportNull$$$0(1);
            }
            return nameFilter;
        }

        @Override // com.intellij.util.lang.ClasspathCache.IndexRegistrar
        public int resourcePackageCount() {
            return this.resourcePackageHashes.length;
        }

        @Override // com.intellij.util.lang.ClasspathCache.IndexRegistrar
        public long[] resourcePackages() {
            return this.resourcePackageHashes;
        }

        public void save(ByteBuffer byteBuffer) {
            if (byteBuffer == null) {
                $$$reportNull$$$0(2);
            }
            byteBuffer.putInt(this.classPackageHashes.length);
            byteBuffer.putInt(this.resourcePackageHashes.length);
            LongBuffer longBufferAsLongBuffer = byteBuffer.asLongBuffer();
            longBufferAsLongBuffer.put(this.classPackageHashes);
            longBufferAsLongBuffer.put(this.resourcePackageHashes);
            byteBuffer.position(byteBuffer.position() + (longBufferAsLongBuffer.position() * 8));
            this.nameFilter.filter.write(byteBuffer);
        }
    }

    public static final class NameFilter implements Predicate<String> {
        final Xor16 filter;

        public NameFilter(Xor16 xor16) {
            this.filter = xor16;
        }

        @Override // java.util.function.Predicate
        public boolean test(String str) {
            if (str.isEmpty()) {
                return true;
            }
            int length = str.length() - 1;
            if (str.charAt(length) != '/') {
                length = str.length();
            }
            return this.filter.mightContain(Xxh3Impl.hash(str, CharSequenceAccess.INSTANCE, 0, length * 2, 0L));
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 4 || i == 5 || i == 6 || i == 11 || i == 16) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 4 || i == 5 || i == 6 || i == 11 || i == 16) ? 2 : 3];
        switch (i) {
            case 2:
                objArr[0] = "nameFilter";
                break;
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 11:
            case 16:
                objArr[0] = "com/intellij/util/lang/FileLoader";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "dir";
                break;
            case 8:
                objArr[0] = "fileNameFilter";
                break;
            case 9:
                objArr[0] = "consumer";
                break;
            case 10:
            case 15:
            default:
                objArr[0] = "path";
                break;
            case 12:
                objArr[0] = "name";
                break;
            case 13:
                objArr[0] = "data";
                break;
            case 14:
                objArr[0] = "indexFile";
                break;
        }
        if (i == 3 || i == 4 || i == 5) {
            objArr[1] = "createCachingFileLoader";
        } else if (i == 6) {
            objArr[1] = "getPath";
        } else if (i == 11) {
            objArr[1] = "getRelativeResourcePath";
        } else if (i != 16) {
            objArr[1] = "com/intellij/util/lang/FileLoader";
        } else {
            objArr[1] = "buildData";
        }
        switch (i) {
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 11:
            case 16:
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
                objArr[2] = "processResources";
                break;
            case 10:
                objArr[2] = "getRelativeResourcePath";
                break;
            case 12:
                objArr[2] = "getResource";
                break;
            case 13:
            case 14:
                objArr[2] = "saveIndex";
                break;
            case 15:
                objArr[2] = "buildData";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 4 && i != 5 && i != 6 && i != 11 && i != 16) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    static {
        AtomicLong atomicLong = new AtomicLong();
        tempCounter = atomicLong;
        atomicLong.set(System.currentTimeMillis() - 1707826225241L);
    }

    private FileLoader(Path path, Predicate<? super String> predicate) {
        if (path == null) {
            $$$reportNull$$$0(1);
        }
        if (predicate == null) {
            $$$reportNull$$$0(2);
        }
        this.path = path;
        this.nameFilter = predicate;
    }

    public static /* synthetic */ boolean a(String str) {
        return true;
    }

    private static LoaderData buildData(Path path, boolean z) {
        long jAddAndGet;
        if (path == null) {
            $$$reportNull$$$0(15);
        }
        Path pathResolve = null;
        Path pathResolve2 = z ? path.resolve("classpath.index") : null;
        LoaderData fromIndex = pathResolve2 == null ? null : readFromIndex(pathResolve2);
        int iIncrementAndGet = totalLoaders.incrementAndGet();
        if (fromIndex == null) {
            long jNanoTime = System.nanoTime();
            StrippedLongArrayList strippedLongArrayList = new StrippedLongArrayList();
            ClasspathCache.LoaderDataBuilder loaderDataBuilder = new ClasspathCache.LoaderDataBuilder();
            buildPackageAndNameCache(path, loaderDataBuilder, strippedLongArrayList);
            LoaderData loaderData = new LoaderData(loaderDataBuilder.resourcePackageHashes.toArray(), loaderDataBuilder.classPackageHashes.toArray(), new NameFilter(Xor16.construct(strippedLongArrayList.elements(), 0, strippedLongArrayList.size())));
            long jNanoTime2 = System.nanoTime() - jNanoTime;
            jAddAndGet = totalScanning.addAndGet(jNanoTime2);
            if (doFsActivityLogging.booleanValue()) {
                System.out.println("Scanned: " + path + " for " + (jNanoTime2 / AnimationKt.MillisToNanos) + "ms");
            }
            if (z) {
                try {
                    pathResolve = pathResolve2.getParent().resolve("classpath.index." + Long.toUnsignedString(tempCounter.getAndIncrement()) + ".tmp");
                    saveIndex(loaderData, pathResolve);
                    try {
                        Files.move(pathResolve, pathResolve2, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
                    } catch (AtomicMoveNotSupportedException unused) {
                        Files.move(pathResolve, pathResolve2, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    try {
                        Files.deleteIfExists(pathResolve);
                    } catch (IOException unused2) {
                    }
                    e.printStackTrace();
                }
            }
            fromIndex = loaderData;
        } else {
            jAddAndGet = totalScanning.get();
        }
        if (doFsActivityLogging.booleanValue()) {
            System.out.println("Scanning: " + (jAddAndGet / AnimationKt.MillisToNanos) + "ms, loading: " + (totalReading.get() / AnimationKt.MillisToNanos) + "ms for " + iIncrementAndGet + " loaders");
        }
        return fromIndex;
    }

    private static void buildPackageAndNameCache(Path path, ClasspathCache.LoaderDataBuilder loaderDataBuilder, StrippedLongArrayList strippedLongArrayList) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(path);
        int length = path.toString().length();
        while (true) {
            Path path2 = (Path) arrayDeque.pollFirst();
            if (path2 == null) {
                return;
            }
            try {
                try {
                    DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path2);
                    try {
                        boolean z = false;
                        boolean z2 = false;
                        for (Path path3 : directoryStreamNewDirectoryStream) {
                            String relativeResourcePath = getRelativeResourcePath(path3.toString(), length);
                            long jHash = Xxh3Impl.hash(relativeResourcePath, CharSequenceAccess.INSTANCE, 0, relativeResourcePath.length() * 2, 0L);
                            if (relativeResourcePath.endsWith(".class")) {
                                strippedLongArrayList.add(jHash);
                                z = true;
                            } else {
                                strippedLongArrayList.add(jHash);
                                if (!relativeResourcePath.endsWith(".svg") && !relativeResourcePath.endsWith(".png") && !relativeResourcePath.endsWith(".xml")) {
                                    arrayDeque.addLast(path3);
                                }
                                z2 = true;
                            }
                        }
                        if (z || z2) {
                            String relativeResourcePath2 = getRelativeResourcePath(path2.toString(), length);
                            if (z) {
                                loaderDataBuilder.addClassPackage(relativeResourcePath2);
                            }
                            if (z2) {
                                loaderDataBuilder.addResourcePackage(relativeResourcePath2);
                            }
                        }
                        directoryStreamNewDirectoryStream.close();
                    } catch (Throwable th) {
                        if (directoryStreamNewDirectoryStream != null) {
                            try {
                                directoryStreamNewDirectoryStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (NoSuchFileException | NotDirectoryException unused) {
                }
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public static FileLoader createCachingFileLoader(Path path, CachePoolImpl cachePoolImpl, Predicate<? super Path> predicate, boolean z, ClasspathCache classpathCache) {
        if (cachePoolImpl == null) {
            LoaderData loaderDataBuildData = buildData(path, z);
            FileLoader fileLoader = new FileLoader(path, loaderDataBuildData.nameFilter);
            classpathCache.applyLoaderData(loaderDataBuildData, fileLoader);
            return fileLoader;
        }
        ClasspathCache.IndexRegistrar indexRegistrar = cachePoolImpl.loaderIndexCache.get(path);
        if (indexRegistrar != null) {
            FileLoader fileLoader2 = new FileLoader(path, indexRegistrar.getNameFilter());
            classpathCache.applyLoaderData(indexRegistrar, fileLoader2);
            return fileLoader2;
        }
        LoaderData loaderDataBuildData2 = buildData(path, z);
        FileLoader fileLoader3 = new FileLoader(path, loaderDataBuildData2.nameFilter);
        if (predicate != null && predicate.test(path)) {
            cachePoolImpl.loaderIndexCache.put(path, loaderDataBuildData2);
        }
        classpathCache.applyLoaderData(loaderDataBuildData2, fileLoader3);
        return fileLoader3;
    }

    private static String getRelativeResourcePath(String str, int i) {
        if (str == null) {
            $$$reportNull$$$0(10);
        }
        String strReplace = str.substring(i).replace(File.separatorChar, '/');
        return strReplace.startsWith("/") ? strReplace.substring(1) : strReplace;
    }

    private static LoaderData readFromIndex(Path path) throws IOException {
        long jNanoTime = System.nanoTime();
        try {
            try {
                SeekableByteChannel seekableByteChannelNewByteChannel = Files.newByteChannel(path, READ_OPTIONS, new FileAttribute[0]);
                try {
                    ByteBuffer byteBufferAllocate = DirectByteBufferPool.DEFAULT_POOL.allocate((int) seekableByteChannelNewByteChannel.size());
                    do {
                        try {
                            seekableByteChannelNewByteChannel.read(byteBufferAllocate);
                        } catch (Throwable th) {
                            DirectByteBufferPool.DEFAULT_POOL.release(byteBufferAllocate);
                            throw th;
                        }
                    } while (byteBufferAllocate.hasRemaining());
                    byteBufferAllocate.flip();
                    byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
                    if (byteBufferAllocate.getShort() != 24) {
                        DirectByteBufferPool.DEFAULT_POOL.release(byteBufferAllocate);
                        seekableByteChannelNewByteChannel.close();
                        Files.deleteIfExists(path);
                        totalReading.addAndGet(System.nanoTime() - jNanoTime);
                        return null;
                    }
                    long[] jArr = new long[byteBufferAllocate.getInt()];
                    long[] jArr2 = new long[byteBufferAllocate.getInt()];
                    LongBuffer longBufferAsLongBuffer = byteBufferAllocate.asLongBuffer();
                    longBufferAsLongBuffer.get(jArr);
                    longBufferAsLongBuffer.get(jArr2);
                    byteBufferAllocate.position(byteBufferAllocate.position() + (longBufferAsLongBuffer.position() * 8));
                    LoaderData loaderData = new LoaderData(jArr2, jArr, new NameFilter(new Xor16(byteBufferAllocate)));
                    DirectByteBufferPool.DEFAULT_POOL.release(byteBufferAllocate);
                    seekableByteChannelNewByteChannel.close();
                    totalReading.addAndGet(System.nanoTime() - jNanoTime);
                    return loaderData;
                } catch (Throwable th2) {
                    if (seekableByteChannelNewByteChannel != null) {
                        try {
                            seekableByteChannelNewByteChannel.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                if (0 == 0) {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException unused) {
                    }
                }
                totalReading.addAndGet(System.nanoTime() - jNanoTime);
                throw th4;
            }
        } catch (NoSuchFileException | IOException unused2) {
        } catch (Exception e) {
            System.err.println("Cannot read classpath index (version=-1, module=" + path.getParent().getFileName() + ")");
            e.printStackTrace();
            if (0 == 0) {
            }
            totalReading.addAndGet(System.nanoTime() - jNanoTime);
            return null;
        }
    }

    private static void saveIndex(LoaderData loaderData, Path path) throws IOException {
        if (loaderData == null) {
            $$$reportNull$$$0(13);
        }
        if (path == null) {
            $$$reportNull$$$0(14);
        }
        long jNanoTime = System.nanoTime();
        ByteBuffer byteBufferAllocate = DirectByteBufferPool.DEFAULT_POOL.allocate(loaderData.approximateSizeInBytes() + 2);
        try {
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate.putShort((short) 24);
            loaderData.save(byteBufferAllocate);
            byteBufferAllocate.flip();
            SeekableByteChannel seekableByteChannelNewByteChannel = Files.newByteChannel(path, WRITE_OPTIONS, new FileAttribute[0]);
            do {
                try {
                    seekableByteChannelNewByteChannel.write(byteBufferAllocate);
                } catch (Throwable th) {
                    if (seekableByteChannelNewByteChannel != null) {
                        try {
                            seekableByteChannelNewByteChannel.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } while (byteBufferAllocate.hasRemaining());
            seekableByteChannelNewByteChannel.close();
            DirectByteBufferPool.DEFAULT_POOL.release(byteBufferAllocate);
            totalSaving.addAndGet(System.nanoTime() - jNanoTime);
        } catch (Throwable th3) {
            DirectByteBufferPool.DEFAULT_POOL.release(byteBufferAllocate);
            totalSaving.addAndGet(System.nanoTime() - jNanoTime);
            throw th3;
        }
    }

    @Override // com.intellij.util.lang.Loader
    public Class<?> findClass(String str, String str2, ClassPath.ClassDataConsumer classDataConsumer) throws IOException {
        if (!this.nameFilter.test(str)) {
            return null;
        }
        try {
            return classDataConsumer.consumeClassData(str2, Files.readAllBytes(this.path.resolve(str)));
        } catch (NoSuchFileException unused) {
            return null;
        }
    }

    @Override // com.intellij.util.lang.Loader
    public Path getPath() {
        Path path = this.path;
        if (path == null) {
            $$$reportNull$$$0(6);
        }
        return path;
    }

    @Override // com.intellij.util.lang.Loader
    public Resource getResource(String str) {
        if (str == null) {
            $$$reportNull$$$0(12);
        }
        if (!this.nameFilter.test(str)) {
            return null;
        }
        Path pathResolve = this.path.resolve(str);
        if (Files.exists(pathResolve, new LinkOption[0])) {
            return new FileResource(pathResolve);
        }
        return null;
    }

    public String toString() {
        return "FileLoader(path=" + this.path + ')';
    }

    public FileLoader(Path path) {
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        this.path = path;
        this.nameFilter = new Predicate() { // from class: com.intellij.util.lang.b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return FileLoader.a((String) obj);
            }
        };
    }
}
