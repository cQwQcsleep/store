package org.jetbrains.kotlin.analysis.decompiler.konan;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.ContainerUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.analysis.decompiler.konan.KlibLoadingMetadataCache;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.library.KotlinLibraryVersioningKt;
import org.jetbrains.kotlin.library.metadata.KlibMetadataDeserializationUtilsKt;
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 !2\u00020\u0001:\u0003\u001f !B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u0011J\u001e\u0010\u0014\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00152\u0006\u0010\u0010\u001a\u00020\u0011J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00020\u0011H\u0002J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0011H\u0002J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u0011H\u0002J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001e\u001a\u00020\u0011H\u0002R9\u0010\u0004\u001a-\u0012\u000e\u0012\f0\u0006¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b0\u0005¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R9\u0010\u000b\u001a-\u0012\u000e\u0012\f0\u0006¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b0\u0005¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R9\u0010\r\u001a-\u0012\u000e\u0012\f0\u0006¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000e0\t¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b0\u0005¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/konan/KlibLoadingMetadataCache;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "packageFragmentCache", "Ljava/util/concurrent/ConcurrentMap;", "Lorg/jetbrains/kotlin/analysis/decompiler/konan/KlibLoadingMetadataCache$CacheKey;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lorg/jetbrains/kotlin/analysis/decompiler/konan/KlibLoadingMetadataCache$CacheValue;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "moduleHeaderCache", "Lorg/jetbrains/kotlin/library/metadata/KlibMetadataProtoBuf$Header;", "libraryMetadataVersionCache", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "getCachedPackageFragment", "packageFragmentFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "getCachedModuleHeader", "moduleHeaderFile", "getCachedPackageFragmentWithVersion", "Lkotlin/Pair;", "getCachedMetadataVersion", "libraryRoot", "getKlibLibraryRootForPackageFragment", "isMetadataCompatible", "", "computePackageFragment", "computeModuleHeader", "computeLibraryMetadataVersion", "manifestFile", "CacheKey", "CacheValue", "Companion", "org.jetbrains.kotlin:decompiler-native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KlibLoadingMetadataCache {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ConcurrentMap<CacheKey, CacheValue<MetadataVersion>> libraryMetadataVersionCache;
    private final ConcurrentMap<CacheKey, CacheValue<KlibMetadataProtoBuf.Header>> moduleHeaderCache;
    private final ConcurrentMap<CacheKey, CacheValue<ProtoBuf.PackageFragment>> packageFragmentCache;

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0004\u0010\u0005R\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/konan/KlibLoadingMetadataCache$CacheValue;", "T", "", "value", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "org.jetbrains.kotlin:decompiler-native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class CacheValue<T> {
        private final T value;

        public CacheValue(T t) {
            this.value = t;
        }

        public final T getValue() {
            return this.value;
        }
    }

    public KlibLoadingMetadataCache() {
        ConcurrentMap<CacheKey, CacheValue<ProtoBuf.PackageFragment>> concurrentMapCreateConcurrentWeakValueMap = ContainerUtil.createConcurrentWeakValueMap();
        concurrentMapCreateConcurrentWeakValueMap.getClass();
        this.packageFragmentCache = concurrentMapCreateConcurrentWeakValueMap;
        ConcurrentMap<CacheKey, CacheValue<KlibMetadataProtoBuf.Header>> concurrentMapCreateConcurrentWeakValueMap2 = ContainerUtil.createConcurrentWeakValueMap();
        concurrentMapCreateConcurrentWeakValueMap2.getClass();
        this.moduleHeaderCache = concurrentMapCreateConcurrentWeakValueMap2;
        ConcurrentMap<CacheKey, CacheValue<MetadataVersion>> concurrentMapCreateConcurrentWeakValueMap3 = ContainerUtil.createConcurrentWeakValueMap();
        concurrentMapCreateConcurrentWeakValueMap3.getClass();
        this.libraryMetadataVersionCache = concurrentMapCreateConcurrentWeakValueMap3;
    }

    public static CacheValue a(KlibLoadingMetadataCache klibLoadingMetadataCache, VirtualFile virtualFile, CacheKey cacheKey) {
        return new CacheValue(klibLoadingMetadataCache.computeLibraryMetadataVersion(virtualFile));
    }

    public static CacheValue b(Function1 function1, Object obj) {
        return (CacheValue) function1.invoke(obj);
    }

    public static CacheValue c(Function1 function1, Object obj) {
        return (CacheValue) function1.invoke(obj);
    }

    private final MetadataVersion computeLibraryMetadataVersion(VirtualFile manifestFile) {
        try {
            Properties properties = new Properties();
            InputStream inputStream = manifestFile.getInputStream();
            try {
                properties.load(inputStream);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(inputStream, (Throwable) null);
                return KotlinLibraryVersioningKt.readKonanLibraryVersioning(properties).getMetadataVersion();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(inputStream, th);
                    throw th2;
                }
            }
        } catch (IOException | IllegalArgumentException unused) {
            return null;
        }
    }

    private final KlibMetadataProtoBuf.Header computeModuleHeader(VirtualFile moduleHeaderFile) {
        VirtualFile parent = moduleHeaderFile.getParent().getParent();
        parent.getClass();
        if (!isMetadataCompatible(parent)) {
            return null;
        }
        try {
            byte[] bArrContentsToByteArray = moduleHeaderFile.contentsToByteArray(false);
            bArrContentsToByteArray.getClass();
            return KlibMetadataDeserializationUtilsKt.parseModuleHeader(bArrContentsToByteArray);
        } catch (IOException unused) {
            return null;
        }
    }

    private final ProtoBuf.PackageFragment computePackageFragment(VirtualFile packageFragmentFile) {
        if (!isMetadataCompatible(getKlibLibraryRootForPackageFragment(packageFragmentFile))) {
            return null;
        }
        try {
            byte[] bArrContentsToByteArray = packageFragmentFile.contentsToByteArray(false);
            bArrContentsToByteArray.getClass();
            return KlibMetadataDeserializationUtilsKt.parsePackageFragment(bArrContentsToByteArray);
        } catch (IOException unused) {
            return null;
        }
    }

    public static CacheValue d(Function1 function1, Object obj) {
        return (CacheValue) function1.invoke(obj);
    }

    public static CacheValue e(KlibLoadingMetadataCache klibLoadingMetadataCache, VirtualFile virtualFile, CacheKey cacheKey) {
        return new CacheValue(klibLoadingMetadataCache.computePackageFragment(virtualFile));
    }

    public static CacheValue f(KlibLoadingMetadataCache klibLoadingMetadataCache, VirtualFile virtualFile, CacheKey cacheKey) {
        return new CacheValue(klibLoadingMetadataCache.computeModuleHeader(virtualFile));
    }

    private final MetadataVersion getCachedMetadataVersion(VirtualFile libraryRoot) {
        final VirtualFile virtualFileFindChild = libraryRoot.findChild("manifest");
        if (virtualFileFindChild == null) {
            return null;
        }
        ConcurrentMap<CacheKey, CacheValue<MetadataVersion>> concurrentMap = this.libraryMetadataVersionCache;
        CacheKey cacheKey = new CacheKey(virtualFileFindChild);
        final Function1 function1 = new Function1() { // from class: org.jetbrains.kotlin.analysis.decompiler.konan.c
            public final Object invoke(Object obj) {
                return KlibLoadingMetadataCache.a(this.b, virtualFileFindChild, (KlibLoadingMetadataCache.CacheKey) obj);
            }
        };
        return concurrentMap.computeIfAbsent(cacheKey, new Function() { // from class: u88
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return KlibLoadingMetadataCache.c(function1, obj);
            }
        }).getValue();
    }

    @JvmStatic
    public static final KlibLoadingMetadataCache getInstance() {
        return INSTANCE.getInstance();
    }

    private final VirtualFile getKlibLibraryRootForPackageFragment(VirtualFile packageFragmentFile) {
        VirtualFile parent = packageFragmentFile.getParent().getParent().getParent();
        parent.getClass();
        return parent;
    }

    private final boolean isMetadataCompatible(VirtualFile libraryRoot) {
        MetadataVersion cachedMetadataVersion = getCachedMetadataVersion(libraryRoot);
        if (cachedMetadataVersion == null) {
            return false;
        }
        return cachedMetadataVersion.isCompatibleWithCurrentCompilerVersion();
    }

    public final KlibMetadataProtoBuf.Header getCachedModuleHeader(final VirtualFile moduleHeaderFile) {
        moduleHeaderFile.getClass();
        if (!Intrinsics.areEqual(moduleHeaderFile.getName(), "module")) {
            cpa.a("Not a module header file: ", moduleHeaderFile);
            return null;
        }
        ConcurrentMap<CacheKey, CacheValue<KlibMetadataProtoBuf.Header>> concurrentMap = this.moduleHeaderCache;
        CacheKey cacheKey = new CacheKey(moduleHeaderFile);
        final Function1 function1 = new Function1() { // from class: org.jetbrains.kotlin.analysis.decompiler.konan.a
            public final Object invoke(Object obj) {
                return KlibLoadingMetadataCache.f(this.b, moduleHeaderFile, (KlibLoadingMetadataCache.CacheKey) obj);
            }
        };
        return concurrentMap.computeIfAbsent(cacheKey, new Function() { // from class: s88
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return KlibLoadingMetadataCache.b(function1, obj);
            }
        }).getValue();
    }

    public final ProtoBuf.PackageFragment getCachedPackageFragment(final VirtualFile packageFragmentFile) {
        packageFragmentFile.getClass();
        if (!Intrinsics.areEqual(packageFragmentFile.getExtension(), "knm")) {
            cpa.a("Not a package metadata file: ", packageFragmentFile);
            return null;
        }
        ConcurrentMap<CacheKey, CacheValue<ProtoBuf.PackageFragment>> concurrentMap = this.packageFragmentCache;
        CacheKey cacheKey = new CacheKey(packageFragmentFile);
        final Function1 function1 = new Function1() { // from class: org.jetbrains.kotlin.analysis.decompiler.konan.b
            public final Object invoke(Object obj) {
                return KlibLoadingMetadataCache.e(this.b, packageFragmentFile, (KlibLoadingMetadataCache.CacheKey) obj);
            }
        };
        return concurrentMap.computeIfAbsent(cacheKey, new Function() { // from class: t88
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return KlibLoadingMetadataCache.d(function1, obj);
            }
        }).getValue();
    }

    public final Pair<ProtoBuf.PackageFragment, MetadataVersion> getCachedPackageFragmentWithVersion(VirtualFile packageFragmentFile) {
        packageFragmentFile.getClass();
        ProtoBuf.PackageFragment cachedPackageFragment = getCachedPackageFragment(packageFragmentFile);
        return cachedPackageFragment == null ? TuplesKt.to((Object) null, (Object) null) : TuplesKt.to(cachedPackageFragment, getCachedMetadataVersion(getKlibLibraryRootForPackageFragment(packageFragmentFile)));
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005H\u0007b\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/konan/KlibLoadingMetadataCache$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getInstance", "Lorg/jetbrains/kotlin/analysis/decompiler/konan/KlibLoadingMetadataCache;", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:decompiler-native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final KlibLoadingMetadataCache getInstance() {
            Object service = ApplicationManager.getApplication().getService(KlibLoadingMetadataCache.class);
            service.getClass();
            return (KlibLoadingMetadataCache) service;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/konan/KlibLoadingMetadataCache$CacheKey;", "", "url", "", "modificationStamp", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;J)V", "virtualFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "(Lcom/intellij/openapi/vfs/VirtualFile;)V", "getUrl", "()Ljava/lang/String;", "getModificationStamp", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:decompiler-native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class CacheKey {
        private final long modificationStamp;
        private final String url;

        /* JADX WARN: Illegal instructions before constructor call */
        public CacheKey(VirtualFile virtualFile) {
            virtualFile.getClass();
            String url = virtualFile.getUrl();
            url.getClass();
            this(url, virtualFile.getModificationStamp());
        }

        public static /* synthetic */ CacheKey copy$default(CacheKey cacheKey, String str, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = cacheKey.url;
            }
            if ((i & 2) != 0) {
                j = cacheKey.modificationStamp;
            }
            return cacheKey.copy(str, j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getModificationStamp() {
            return this.modificationStamp;
        }

        public final CacheKey copy(String url, long modificationStamp) {
            url.getClass();
            return new CacheKey(url, modificationStamp);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CacheKey)) {
                return false;
            }
            CacheKey cacheKey = (CacheKey) other;
            return Intrinsics.areEqual(this.url, cacheKey.url) && this.modificationStamp == cacheKey.modificationStamp;
        }

        public final long getModificationStamp() {
            return this.modificationStamp;
        }

        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            return (this.url.hashCode() * 31) + Long.hashCode(this.modificationStamp);
        }

        public String toString() {
            return "CacheKey(url=" + this.url + ", modificationStamp=" + this.modificationStamp + Util.C_PARAM_END;
        }

        public CacheKey(String str, long j) {
            str.getClass();
            this.url = str;
            this.modificationStamp = j;
        }
    }
}
