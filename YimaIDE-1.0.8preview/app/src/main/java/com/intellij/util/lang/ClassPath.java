package com.intellij.util.lang;

import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationKt;
import com.intellij.util.lang.ClassPath;
import defpackage.eyf;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.jar.Attributes;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ClassPath {
    private static final AtomicLong classDefineTotalTime;
    private static final Measurer classLoading;
    private static final Collection<Map.Entry<String, Path>> loadedClasses;
    static final boolean logLoadingInfo;
    static final boolean recordLoadingInfo;
    static final boolean recordLoadingTime;
    private static final Measurer resourceLoading;
    private volatile boolean allUrlsWereProcessed;
    private final ClasspathCache cache;
    private final CachePoolImpl cachePool;
    private final Predicate<? super Path> cachingCondition;
    private Path[] files;
    final boolean isClassPathIndexEnabled;
    private final AtomicInteger lastLoaderProcessed;
    private final List<Loader> loaders;
    private final Function<Path, ResourceFile> resourceFileFactory;
    private int searchOffset;
    private final boolean useCache;

    public interface ClassDataConsumer {
        Class<?> consumeClassData(String str, byte[] bArr) throws IOException;
    }

    public static final class Measurer {
        private final ThreadLocal<Boolean> doingTiming;
        private final AtomicInteger requestCounter;
        private final AtomicLong timeCounter;

        private Measurer() {
            this.timeCounter = new AtomicLong();
            this.requestCounter = new AtomicInteger();
            this.doingTiming = new ThreadLocal<>();
        }

        public void record(long j, String str) {
            if (j == -1) {
                return;
            }
            this.doingTiming.set(null);
            long jNanoTime = System.nanoTime() - j;
            long jAddAndGet = this.timeCounter.addAndGet(jNanoTime);
            int iIncrementAndGet = this.requestCounter.incrementAndGet();
            if (ClassPath.logLoadingInfo) {
                if (jNanoTime > 3000000) {
                    System.out.println((jNanoTime / AnimationKt.MillisToNanos) + " ms for " + str);
                }
                if (iIncrementAndGet % 10000 == 0) {
                    System.out.println(ClassPath.class.getClassLoader() + ", requests: " + iIncrementAndGet + ", time:" + (jAddAndGet / AnimationKt.MillisToNanos) + "ms");
                }
            }
        }

        public long startTiming() {
            if (!ClassPath.recordLoadingTime || this.doingTiming.get() != null) {
                return -1L;
            }
            this.doingTiming.set(Boolean.TRUE);
            return System.nanoTime();
        }

        public String toString() {
            return "Measurer(time=" + (this.timeCounter.get() / AnimationKt.MillisToNanos) + "ms, requests=" + this.requestCounter + ')';
        }
    }

    public static final class ResourceEnumeration implements Enumeration<URL> {
        private int index;
        private final Loader[] loaders;
        private final String name;
        private Resource resource;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/intellij/util/lang/ClassPath$ResourceEnumeration", "<init>"));
        }

        public ResourceEnumeration(String str, Loader[] loaderArr) {
            if (str == null) {
                $$$reportNull$$$0(0);
            }
            this.name = str;
            this.loaders = loaderArr;
        }

        private boolean next() {
            Resource resource;
            if (this.resource != null) {
                return true;
            }
            long jStartTiming = ClassPath.resourceLoading.startTiming();
            do {
                try {
                    int i = this.index;
                    Loader[] loaderArr = this.loaders;
                    if (i >= loaderArr.length) {
                        return false;
                    }
                    this.index = i + 1;
                    resource = loaderArr[i].getResource(this.name);
                    this.resource = resource;
                } finally {
                    ClassPath.resourceLoading.record(jStartTiming, this.name);
                }
            } while (resource == null);
            return true;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return next();
        }

        @Override // java.util.Enumeration
        public URL nextElement() {
            if (!next()) {
                z0e.a();
                return null;
            }
            Resource resource = this.resource;
            this.resource = null;
            return resource.getURL();
        }
    }

    public static final class UncachedResourceEnumeration implements Enumeration<URL> {
        private final ClassPath classPath;
        private int index;
        private final String name;
        private Resource resource;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "name";
            } else {
                objArr[0] = "classPath";
            }
            objArr[1] = "com/intellij/util/lang/ClassPath$UncachedResourceEnumeration";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public UncachedResourceEnumeration(String str, ClassPath classPath) {
            if (str == null) {
                $$$reportNull$$$0(0);
            }
            if (classPath == null) {
                $$$reportNull$$$0(1);
            }
            this.name = str;
            this.classPath = classPath;
        }

        private boolean next() {
            Resource resource;
            if (this.resource != null) {
                return true;
            }
            long jStartTiming = ClassPath.resourceLoading.startTiming();
            do {
                try {
                    ClassPath classPath = this.classPath;
                    int i = this.index;
                    this.index = i + 1;
                    Loader loader = classPath.getLoader(i);
                    if (loader == null) {
                        return false;
                    }
                    resource = loader.getResource(this.name);
                    this.resource = resource;
                } finally {
                    ClassPath.resourceLoading.record(jStartTiming, this.name);
                }
            } while (resource == null);
            return true;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return next();
        }

        @Override // java.util.Enumeration
        public URL nextElement() {
            if (!next()) {
                z0e.a();
                return null;
            }
            Resource resource = this.resource;
            this.resource = null;
            return resource.getURL();
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 14) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 14) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "configuration";
                break;
            case 2:
            case 14:
                objArr[0] = "com/intellij/util/lang/ClassPath";
                break;
            case 3:
            case 15:
            case 16:
                objArr[0] = "file";
                break;
            case 4:
                objArr[0] = "newList";
                break;
            case 5:
                objArr[0] = "fileName";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "className";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "classConsumer";
                break;
            case 8:
            case 17:
                objArr[0] = "loader";
                break;
            case 9:
                objArr[0] = "resourceName";
                break;
            case 10:
                objArr[0] = "name";
                break;
            case 11:
                objArr[0] = "dir";
                break;
            case 12:
                objArr[0] = "fileNameFilter";
                break;
            case 13:
                objArr[0] = "consumer";
                break;
            case 18:
                objArr[0] = "zipFile";
                break;
            default:
                objArr[0] = "files";
                break;
        }
        if (i == 2) {
            objArr[1] = "getLoadingStats";
        } else if (i != 14) {
            objArr[1] = "com/intellij/util/lang/ClassPath";
        } else {
            objArr[1] = "getBaseUrls";
        }
        switch (i) {
            case 2:
            case 14:
                break;
            case 3:
                objArr[2] = "addFile";
                break;
            case 4:
                objArr[2] = "addFiles";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "findClassInLoader";
                break;
            case 9:
                objArr[2] = "findResource";
                break;
            case 10:
                objArr[2] = "getResources";
                break;
            case 11:
            case 12:
            case 13:
                objArr[2] = "processResources";
                break;
            case 15:
                objArr[2] = "createLoader";
                break;
            case 16:
                objArr[2] = "addFromManifestClassPathIfNeeded";
                break;
            case 17:
            case 18:
                objArr[2] = "loadManifestClasspath";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 14) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        boolean z = Boolean.getBoolean("idea.record.classpath.info");
        recordLoadingInfo = z;
        recordLoadingTime = z || Boolean.getBoolean("idea.record.classloading.stats");
        boolean z2 = Boolean.getBoolean("idea.log.classpath.info");
        logLoadingInfo = z2;
        classLoading = new Measurer();
        resourceLoading = new Measurer();
        classDefineTotalTime = new AtomicLong();
        loadedClasses = z ? new ConcurrentLinkedQueue() : null;
        if (z2) {
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() { // from class: nw1
                @Override // java.lang.Runnable
                public final void run() {
                    System.out.println("Classloading requests: " + ClassPath.class.getClassLoader() + ", class=" + ClassPath.classLoading + ", resource=" + ClassPath.resourceLoading);
                }
            }, "Shutdown hook for tracing classloading information"));
        }
    }

    private void addFromManifestClassPathIfNeeded(Path path, ResourceFile resourceFile, JarLoader jarLoader) {
        if (path == null) {
            $$$reportNull$$$0(16);
        }
        String[] strArrLoadManifestClasspath = loadManifestClasspath(jarLoader, resourceFile);
        if (strArrLoadManifestClasspath != null) {
            long jNanoTime = logLoadingInfo ? System.nanoTime() : 0L;
            ArrayList arrayList = new ArrayList(strArrLoadManifestClasspath.length);
            for (String str : strArrLoadManifestClasspath) {
                try {
                    arrayList.add(Paths.get(UrlClassLoader.urlToFilePath(str), new String[0]));
                } catch (Exception e) {
                    System.err.println("file: " + path + " / " + str + " " + e);
                }
            }
            addFiles(arrayList);
            if (logLoadingInfo) {
                System.out.println("Loaded all " + strArrLoadManifestClasspath.length + " files " + ((System.nanoTime() - jNanoTime) / AnimationKt.MillisToNanos) + "ms");
            }
        }
    }

    private Loader createLoader(Path path) throws IOException {
        Predicate<? super Path> predicate;
        if (path == null) {
            $$$reportNull$$$0(15);
        }
        try {
            BasicFileAttributes attributes = Files.readAttributes(path, (Class<BasicFileAttributes>) BasicFileAttributes.class, new LinkOption[0]);
            if (attributes.isDirectory()) {
                return this.useCache ? FileLoader.createCachingFileLoader(path, this.cachePool, this.cachingCondition, this.isClassPathIndexEnabled, this.cache) : new FileLoader(path);
            }
            if (!attributes.isRegularFile()) {
                return null;
            }
            ResourceFile resourceFileApply = this.resourceFileFactory.apply(path);
            JarLoader jarLoader = new JarLoader(path, this, resourceFileApply);
            if (this.useCache) {
                CachePoolImpl cachePoolImpl = this.cachePool;
                ClasspathCache.IndexRegistrar indexRegistrarBuildClassPathCacheData = cachePoolImpl != null ? cachePoolImpl.loaderIndexCache.get(path) : null;
                if (indexRegistrarBuildClassPathCacheData == null) {
                    indexRegistrarBuildClassPathCacheData = resourceFileApply.buildClassPathCacheData();
                    if (this.cachePool != null && (predicate = this.cachingCondition) != null && predicate.test(path)) {
                        this.cachePool.loaderIndexCache.put(path, indexRegistrarBuildClassPathCacheData);
                    }
                }
                this.cache.applyLoaderData(indexRegistrarBuildClassPathCacheData, jarLoader);
            }
            String string = path.toString();
            if (string.startsWith("classpath", string.lastIndexOf(File.separatorChar) + 1)) {
                addFromManifestClassPathIfNeeded(path, resourceFileApply, jarLoader);
            }
            return jarLoader;
        } catch (RuntimeException e) {
            eyf.a("Failed to read attributes of file from ", path.getFileSystem(), e);
            return null;
        } catch (NoSuchFileException unused) {
            return null;
        }
    }

    private static Class<?> findClassInLoader(String str, String str2, ClassDataConsumer classDataConsumer, Loader loader) throws IOException {
        if (str == null) {
            $$$reportNull$$$0(5);
        }
        if (str2 == null) {
            $$$reportNull$$$0(6);
        }
        if (classDataConsumer == null) {
            $$$reportNull$$$0(7);
        }
        if (loader == null) {
            $$$reportNull$$$0(8);
        }
        Class<?> clsFindClass = loader.findClass(str, str2, classDataConsumer);
        if (clsFindClass == null) {
            return null;
        }
        Collection<Map.Entry<String, Path>> collection = loadedClasses;
        if (collection != null) {
            collection.add(new AbstractMap.SimpleImmutableEntry(str, loader.getPath()));
        }
        return clsFindClass;
    }

    private Class<?> findClassWithoutCache(String str, String str2, int i, ClassDataConsumer classDataConsumer) throws IOException {
        while (true) {
            Loader loaderSlowPath = i < this.lastLoaderProcessed.get() ? this.loaders.get(i) : getLoaderSlowPath(i);
            if (loaderSlowPath == null) {
                return null;
            }
            Class<?> clsFindClassInLoader = findClassInLoader(str2, str, classDataConsumer, loaderSlowPath);
            if (clsFindClassInLoader != null) {
                return clsFindClassInLoader;
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Loader getLoader(int i) {
        return i < this.lastLoaderProcessed.get() ? this.loaders.get(i) : getLoaderSlowPath(i);
    }

    private synchronized Loader getLoaderSlowPath(int i) {
        while (this.loaders.size() < i + 1) {
            try {
                int i2 = this.searchOffset;
                Path[] pathArr = this.files;
                if (i2 == pathArr.length) {
                    if (this.useCache) {
                        this.allUrlsWereProcessed = true;
                    }
                    return null;
                }
                this.searchOffset = i2 + 1;
                try {
                    Loader loaderCreateLoader = createLoader(pathArr[i2].toAbsolutePath());
                    if (loaderCreateLoader != null) {
                        if (this.useCache && this.searchOffset == this.files.length) {
                            this.allUrlsWereProcessed = true;
                        }
                        this.loaders.add(loaderCreateLoader);
                        this.lastLoaderProcessed.incrementAndGet();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return this.loaders.get(i);
    }

    private String[] loadManifestClasspath(JarLoader jarLoader, ResourceFile resourceFile) {
        Predicate<? super Path> predicate;
        CachePoolImpl cachePoolImpl;
        if (jarLoader == null) {
            $$$reportNull$$$0(17);
        }
        if (resourceFile == null) {
            $$$reportNull$$$0(18);
        }
        try {
            Map<JarLoader.Attribute, String> manifestData = (!this.useCache || (cachePoolImpl = this.cachePool) == null) ? null : cachePoolImpl.getManifestData(jarLoader.getPath());
            if (manifestData == null) {
                Attributes attributesLoadManifestAttributes = resourceFile.loadManifestAttributes();
                manifestData = attributesLoadManifestAttributes == null ? Collections.EMPTY_MAP : JarLoader.getAttributes(attributesLoadManifestAttributes);
                if (this.useCache && this.cachePool != null && (predicate = this.cachingCondition) != null && predicate.test(jarLoader.getPath())) {
                    this.cachePool.cacheManifestData(jarLoader.getPath(), manifestData);
                }
            }
            String str = manifestData.get(JarLoader.Attribute.CLASS_PATH);
            if (str != null) {
                String[] strArrSplit = str.split(" ");
                if (strArrSplit.length > 0 && strArrSplit[0].startsWith("file:")) {
                    return strArrSplit;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public synchronized void addFile(Path path) {
        if (path == null) {
            try {
                $$$reportNull$$$0(3);
            } catch (Throwable th) {
                throw th;
            }
        }
        for (Path path2 : this.files) {
            if (path2.equals(path)) {
                return;
            }
        }
        Path[] pathArr = this.files;
        Path[] pathArr2 = (Path[]) Arrays.copyOf(pathArr, pathArr.length + 1);
        pathArr2[pathArr2.length - 1] = path;
        this.files = pathArr2;
        this.allUrlsWereProcessed = false;
    }

    public synchronized void addFiles(Collection<Path> collection) {
        if (collection == null) {
            try {
                $$$reportNull$$$0(4);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (collection.isEmpty()) {
            return;
        }
        if (collection.size() == 1) {
            addFile((Path) (collection instanceof List ? ((List) collection).get(0) : collection.iterator().next()));
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(this.files.length + collection.size());
        Collections.addAll(linkedHashSet, this.files);
        linkedHashSet.addAll(collection);
        if (linkedHashSet.size() == this.files.length) {
            return;
        }
        this.files = (Path[]) linkedHashSet.toArray(new Path[0]);
        this.allUrlsWereProcessed = false;
    }

    public Class<?> findClass(String str, String str2, long j, ClassDataConsumer classDataConsumer) throws IOException {
        long jStartTiming = classLoading.startTiming();
        try {
            int i = 0;
            if (this.useCache) {
                boolean z = this.allUrlsWereProcessed;
                int i2 = this.lastLoaderProcessed.get();
                Loader[] classLoadersByPackageNameHash = this.cache.getClassLoadersByPackageNameHash(j);
                if (classLoadersByPackageNameHash != null) {
                    int length = classLoadersByPackageNameHash.length;
                    while (i < length) {
                        Class<?> clsFindClassInLoader = findClassInLoader(str2, str, classDataConsumer, classLoadersByPackageNameHash[i]);
                        if (clsFindClassInLoader != null) {
                            return clsFindClassInLoader;
                        }
                        i++;
                    }
                }
                if (z) {
                    return null;
                }
                i = i2;
            }
            return findClassWithoutCache(str, str2, i, classDataConsumer);
        } finally {
            classLoading.record(jStartTiming, str);
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x005b A[Catch: all -> 0x003f, TryCatch #0 {all -> 0x003f, blocks: (B:5:0x000d, B:7:0x0013, B:9:0x0023, B:11:0x0026, B:13:0x002e, B:15:0x0032, B:27:0x0055, B:29:0x005b, B:31:0x0061, B:33:0x0065), top: B:39:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:33:0x0065 A[Catch: all -> 0x003f, TRY_LEAVE, TryCatch #0 {all -> 0x003f, blocks: (B:5:0x000d, B:7:0x0013, B:9:0x0023, B:11:0x0026, B:13:0x002e, B:15:0x0032, B:27:0x0055, B:29:0x005b, B:31:0x0061, B:33:0x0065), top: B:39:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:36:0x0077 A[LOOP:1: B:26:0x0053->B:36:0x0077, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:42:0x004c A[EDGE_INSN: B:42:0x004c->B:23:0x004c BREAK  A[LOOP:1: B:26:0x0053->B:36:0x0077], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x0061 A[SYNTHETIC] */
    public Resource findResource(String str) {
        int i;
        Loader loader;
        Resource resource;
        Collection<Map.Entry<String, Path>> collection;
        if (str == null) {
            $$$reportNull$$$0(9);
        }
        long jStartTiming = resourceLoading.startTiming();
        try {
            int i2 = 0;
            if (!this.useCache) {
                while (true) {
                    i = i2 + 1;
                    loader = getLoader(i2);
                    if (loader != null) {
                        break;
                        break;
                    }
                    resource = loader.getResource(str);
                    if (resource != null) {
                        collection = loadedClasses;
                        if (collection != null) {
                            collection.add(new AbstractMap.SimpleImmutableEntry(str, loader.getPath()));
                        }
                        resourceLoading.record(jStartTiming, str);
                        return resource;
                    }
                    i2 = i;
                }
            } else {
                boolean z = this.allUrlsWereProcessed;
                int i3 = this.lastLoaderProcessed.get();
                Loader[] loadersByName = this.cache.getLoadersByName(str);
                if (loadersByName != null) {
                    int length = loadersByName.length;
                    while (i2 < length) {
                        Loader loader2 = loadersByName[i2];
                        Resource resource2 = loader2.getResource(str);
                        if (resource2 != null) {
                            Collection<Map.Entry<String, Path>> collection2 = loadedClasses;
                            if (collection2 != null) {
                                collection2.add(new AbstractMap.SimpleImmutableEntry(str, loader2.getPath()));
                            }
                            resourceLoading.record(jStartTiming, str);
                            return resource2;
                        }
                        i2++;
                    }
                }
                if (!z) {
                    i2 = i3;
                    while (true) {
                        i = i2 + 1;
                        loader = getLoader(i2);
                        if (loader != null) {
                            break;
                        }
                        resource = loader.getResource(str);
                        if (resource != null) {
                            collection = loadedClasses;
                            if (collection != null) {
                                collection.add(new AbstractMap.SimpleImmutableEntry(str, loader.getPath()));
                            }
                            resourceLoading.record(jStartTiming, str);
                            return resource;
                        }
                        i2 = i;
                    }
                }
            }
            resourceLoading.record(jStartTiming, str);
            return null;
        } catch (Throwable th) {
            resourceLoading.record(jStartTiming, str);
            throw th;
        }
    }

    public synchronized List<Path> getFiles() {
        return Arrays.asList(this.files);
    }

    public Enumeration<URL> getResources(String str) {
        if (str == null) {
            $$$reportNull$$$0(10);
        }
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        if (!this.useCache || !this.allUrlsWereProcessed) {
            return new UncachedResourceEnumeration(str, this);
        }
        Loader[] loadersByName = this.cache.getLoadersByName(str);
        return (loadersByName == null || loadersByName.length == 0) ? Collections.emptyEnumeration() : new ResourceEnumeration(str, loadersByName);
    }
}
