package org.jetbrains.kotlin.analysis.decompiler.stub.file;

import com.intellij.ide.highlighter.JavaClassFileType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWithId;
import java.lang.ref.SoftReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.load.kotlin.KotlinBinaryClassCache;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.load.kotlin.header.KotlinClassHeader;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u00020\u0001:\u0003$%&B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tJ\u001c\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tJ3\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tJ\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000bH\u0002J\u0014\u0010\u0018\u001a\u00020\u0012*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010!2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u0016\u0010\u0013\u001a\n \u0015*\u0004\u0018\u00010\u00140\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\u001e\u001a!\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020! \u0015*\n\u0012\u0004\u0012\u00020!\u0018\u00010 0 0\u001f¢\u0006\u0002\b\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/ClsKotlinBinaryClassCache;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "isKotlinJvmCompiledFile", "", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "fileContent", "", "getKotlinBinaryClass", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;", "kotlinJvmBinaryClass", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "isKotlinBinary", "(Lcom/intellij/openapi/vfs/VirtualFile;[BLorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;Ljava/lang/Boolean;)Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;", "getKotlinBinaryClassHeaderData", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/ClsKotlinBinaryClassCache$KotlinBinaryClassHeaderData;", "attributeService", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/FileAttributeService;", "kotlin.jvm.PlatformType", "createHeaderInfo", "kotlinBinaryClass", "toLightHeader", "Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "KOTLIN_IS_COMPILED_FILE_ATTRIBUTE", "", "KOTLIN_BINARY_DATA_KEY", "Lcom/intellij/openapi/util/Key;", "Ljava/lang/ref/SoftReference;", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/ClsKotlinBinaryClassCache$KotlinBinaryData;", "Lorg/jetbrains/annotations/NotNull;", "getKotlinBinaryFromCache", "KotlinBinaryClassHeaderData", "KotlinBinaryData", "Companion", "org.jetbrains.kotlin:decompiler-to-file-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ClsKotlinBinaryClassCache {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Key<SoftReference<KotlinBinaryData>> KOTLIN_BINARY_DATA_KEY;
    private final String KOTLIN_IS_COMPILED_FILE_ATTRIBUTE;
    private final FileAttributeService attributeService;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/ClsKotlinBinaryClassCache$KotlinBinaryClassHeaderData;", "", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "kind", "Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$Kind;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "partNamesIfMultifileFacade", "", "", "packageName", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$Kind;Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;Ljava/util/List;Ljava/lang/String;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getKind", "()Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$Kind;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "getPartNamesIfMultifileFacade", "()Ljava/util/List;", "getPackageName", "()Ljava/lang/String;", "packageNameWithFallback", "Lorg/jetbrains/kotlin/name/FqName;", "getPackageNameWithFallback", "()Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:decompiler-to-file-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class KotlinBinaryClassHeaderData {
        private final ClassId classId;
        private final KotlinClassHeader.Kind kind;
        private final MetadataVersion metadataVersion;
        private final String packageName;
        private final List<String> partNamesIfMultifileFacade;

        public KotlinBinaryClassHeaderData(ClassId classId, KotlinClassHeader.Kind kind, MetadataVersion metadataVersion, List<String> list, String str) {
            classId.getClass();
            kind.getClass();
            metadataVersion.getClass();
            list.getClass();
            this.classId = classId;
            this.kind = kind;
            this.metadataVersion = metadataVersion;
            this.partNamesIfMultifileFacade = list;
            this.packageName = str;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public final KotlinClassHeader.Kind getKind() {
            return this.kind;
        }

        public final MetadataVersion getMetadataVersion() {
            return this.metadataVersion;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final FqName getPackageNameWithFallback() {
            String str = this.packageName;
            return str != null ? new FqName(str) : this.classId.getPackageFqName();
        }

        public final List<String> getPartNamesIfMultifileFacade() {
            return this.partNamesIfMultifileFacade;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/ClsKotlinBinaryClassCache$KotlinBinaryData;", "", "isKotlinBinary", "", "timestamp", "", "headerData", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/ClsKotlinBinaryClassCache$KotlinBinaryClassHeaderData;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(ZJLorg/jetbrains/kotlin/analysis/decompiler/stub/file/ClsKotlinBinaryClassCache$KotlinBinaryClassHeaderData;)V", "()Z", "getTimestamp", "()J", "getHeaderData", "()Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/ClsKotlinBinaryClassCache$KotlinBinaryClassHeaderData;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:decompiler-to-file-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class KotlinBinaryData {
        private final KotlinBinaryClassHeaderData headerData;
        private final boolean isKotlinBinary;
        private final long timestamp;

        public KotlinBinaryData(boolean z, long j, KotlinBinaryClassHeaderData kotlinBinaryClassHeaderData) {
            this.isKotlinBinary = z;
            this.timestamp = j;
            this.headerData = kotlinBinaryClassHeaderData;
        }

        public static /* synthetic */ KotlinBinaryData copy$default(KotlinBinaryData kotlinBinaryData, boolean z, long j, KotlinBinaryClassHeaderData kotlinBinaryClassHeaderData, int i, Object obj) {
            if ((i & 1) != 0) {
                z = kotlinBinaryData.isKotlinBinary;
            }
            if ((i & 2) != 0) {
                j = kotlinBinaryData.timestamp;
            }
            if ((i & 4) != 0) {
                kotlinBinaryClassHeaderData = kotlinBinaryData.headerData;
            }
            return kotlinBinaryData.copy(z, j, kotlinBinaryClassHeaderData);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsKotlinBinary() {
            return this.isKotlinBinary;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTimestamp() {
            return this.timestamp;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final KotlinBinaryClassHeaderData getHeaderData() {
            return this.headerData;
        }

        public final KotlinBinaryData copy(boolean isKotlinBinary, long timestamp, KotlinBinaryClassHeaderData headerData) {
            return new KotlinBinaryData(isKotlinBinary, timestamp, headerData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KotlinBinaryData)) {
                return false;
            }
            KotlinBinaryData kotlinBinaryData = (KotlinBinaryData) other;
            return this.isKotlinBinary == kotlinBinaryData.isKotlinBinary && this.timestamp == kotlinBinaryData.timestamp && Intrinsics.areEqual(this.headerData, kotlinBinaryData.headerData);
        }

        public final KotlinBinaryClassHeaderData getHeaderData() {
            return this.headerData;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public int hashCode() {
            int iHashCode = ((Boolean.hashCode(this.isKotlinBinary) * 31) + Long.hashCode(this.timestamp)) * 31;
            KotlinBinaryClassHeaderData kotlinBinaryClassHeaderData = this.headerData;
            return iHashCode + (kotlinBinaryClassHeaderData == null ? 0 : kotlinBinaryClassHeaderData.hashCode());
        }

        public final boolean isKotlinBinary() {
            return this.isKotlinBinary;
        }

        public String toString() {
            return "KotlinBinaryData(isKotlinBinary=" + this.isKotlinBinary + ", timestamp=" + this.timestamp + ", headerData=" + this.headerData + Util.C_PARAM_END;
        }
    }

    public ClsKotlinBinaryClassCache() {
        FileAttributeService fileAttributeService = (FileAttributeService) ApplicationManager.getApplication().getService(FileAttributeService.class);
        this.attributeService = fileAttributeService;
        fileAttributeService.getClass();
        FileAttributeService.register$default(fileAttributeService, "kotlin-is-binary-compiled", 2, false, 4, null);
        this.KOTLIN_IS_COMPILED_FILE_ATTRIBUTE = "kotlin-is-binary-compiled";
        Key<SoftReference<KotlinBinaryData>> keyCreate = Key.create("kotlin-is-binary-compiled");
        keyCreate.getClass();
        this.KOTLIN_BINARY_DATA_KEY = keyCreate;
    }

    private final KotlinBinaryClassHeaderData createHeaderInfo(KotlinJvmBinaryClass kotlinBinaryClass) {
        return toLightHeader(kotlinBinaryClass.getClassHeader(), kotlinBinaryClass.getClassId());
    }

    public static /* synthetic */ KotlinJvmBinaryClass getKotlinBinaryClass$default(ClsKotlinBinaryClassCache clsKotlinBinaryClassCache, VirtualFile virtualFile, byte[] bArr, int i, Object obj) {
        if ((i & 2) != 0) {
            bArr = null;
        }
        return clsKotlinBinaryClassCache.getKotlinBinaryClass(virtualFile, bArr);
    }

    public static /* synthetic */ KotlinBinaryClassHeaderData getKotlinBinaryClassHeaderData$default(ClsKotlinBinaryClassCache clsKotlinBinaryClassCache, VirtualFile virtualFile, byte[] bArr, int i, Object obj) {
        if ((i & 2) != 0) {
            bArr = null;
        }
        return clsKotlinBinaryClassCache.getKotlinBinaryClassHeaderData(virtualFile, bArr);
    }

    private final KotlinBinaryData getKotlinBinaryFromCache(VirtualFile file) {
        SoftReference softReference = (SoftReference) file.getUserData(this.KOTLIN_BINARY_DATA_KEY);
        KotlinBinaryData kotlinBinaryData = softReference != null ? (KotlinBinaryData) softReference.get() : null;
        if (kotlinBinaryData != null && kotlinBinaryData.getTimestamp() == file.getTimeStamp()) {
            return kotlinBinaryData;
        }
        CachedAttributeData<Boolean> booleanAttribute = file instanceof VirtualFileWithId ? this.attributeService.readBooleanAttribute(this.KOTLIN_IS_COMPILED_FILE_ATTRIBUTE, file) : null;
        if (booleanAttribute == null) {
            return null;
        }
        boolean zBooleanValue = booleanAttribute.getValue().booleanValue();
        KotlinBinaryData kotlinBinaryData2 = new KotlinBinaryData(zBooleanValue, file.getTimeStamp(), null);
        if (zBooleanValue) {
            file.putUserData(this.KOTLIN_BINARY_DATA_KEY, new SoftReference(kotlinBinaryData2));
        }
        return kotlinBinaryData2;
    }

    public static /* synthetic */ boolean isKotlinJvmCompiledFile$default(ClsKotlinBinaryClassCache clsKotlinBinaryClassCache, VirtualFile virtualFile, byte[] bArr, int i, Object obj) {
        if ((i & 2) != 0) {
            bArr = null;
        }
        return clsKotlinBinaryClassCache.isKotlinJvmCompiledFile(virtualFile, bArr);
    }

    private final KotlinJvmBinaryClass kotlinJvmBinaryClass(VirtualFile file, byte[] fileContent, MetadataVersion metadataVersion, Boolean isKotlinBinary) {
        try {
            KotlinClassFinder.Result kotlinBinaryClassOrClassFileContent = KotlinBinaryClassCache.Companion.getKotlinBinaryClassOrClassFileContent(file, metadataVersion, fileContent, (PerformanceManager) null);
            KotlinJvmBinaryClass kotlinJvmBinaryClass = kotlinBinaryClassOrClassFileContent != null ? kotlinBinaryClassOrClassFileContent.toKotlinJvmBinaryClass() : null;
            boolean z = kotlinJvmBinaryClass != null;
            if ((file instanceof VirtualFileWithId) && !Intrinsics.areEqual(Boolean.valueOf(z), isKotlinBinary)) {
                this.attributeService.writeBooleanAttribute(this.KOTLIN_IS_COMPILED_FILE_ATTRIBUTE, file, z);
            }
            file.putUserData(this.KOTLIN_BINARY_DATA_KEY, new SoftReference(new KotlinBinaryData(z, file.getTimeStamp(), z ? createHeaderInfo(kotlinJvmBinaryClass) : null)));
            return kotlinJvmBinaryClass;
        } catch (Exception e) {
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
            return null;
        }
    }

    private final KotlinBinaryClassHeaderData toLightHeader(KotlinClassHeader kotlinClassHeader, ClassId classId) {
        return new KotlinBinaryClassHeaderData(classId, kotlinClassHeader.getKind(), kotlinClassHeader.getMetadataVersion(), kotlinClassHeader.getMultifilePartNames(), kotlinClassHeader.getPackageName());
    }

    public final KotlinJvmBinaryClass getKotlinBinaryClass(VirtualFile file, byte[] fileContent) {
        MetadataVersion metadataVersion;
        KotlinBinaryClassHeaderData headerData;
        file.getClass();
        KotlinBinaryData kotlinBinaryFromCache = getKotlinBinaryFromCache(file);
        if (kotlinBinaryFromCache != null && !kotlinBinaryFromCache.isKotlinBinary()) {
            return null;
        }
        if (kotlinBinaryFromCache == null || (headerData = kotlinBinaryFromCache.getHeaderData()) == null || (metadataVersion = headerData.getMetadataVersion()) == null) {
            metadataVersion = MetadataVersion.INSTANCE;
        }
        return kotlinJvmBinaryClass(file, fileContent, metadataVersion, kotlinBinaryFromCache != null ? Boolean.valueOf(kotlinBinaryFromCache.isKotlinBinary()) : null);
    }

    public final KotlinBinaryClassHeaderData getKotlinBinaryClassHeaderData(VirtualFile file, byte[] fileContent) {
        file.getClass();
        KotlinBinaryData kotlinBinaryFromCache = getKotlinBinaryFromCache(file);
        if (kotlinBinaryFromCache != null) {
            if (!kotlinBinaryFromCache.isKotlinBinary()) {
                return null;
            }
            if (kotlinBinaryFromCache.getHeaderData() != null) {
                return kotlinBinaryFromCache.getHeaderData();
            }
        }
        KotlinJvmBinaryClass kotlinJvmBinaryClass = kotlinJvmBinaryClass(file, fileContent, MetadataVersion.INSTANCE, kotlinBinaryFromCache != null ? Boolean.valueOf(kotlinBinaryFromCache.isKotlinBinary()) : null);
        if (kotlinJvmBinaryClass == null) {
            return null;
        }
        return createHeaderInfo(kotlinJvmBinaryClass);
    }

    public final boolean isKotlinJvmCompiledFile(VirtualFile file, byte[] fileContent) {
        file.getClass();
        String extension = file.getExtension();
        JavaClassFileType javaClassFileType = JavaClassFileType.INSTANCE;
        javaClassFileType.getClass();
        if (!Intrinsics.areEqual(extension, javaClassFileType.getDefaultExtension())) {
            return false;
        }
        KotlinBinaryData kotlinBinaryFromCache = getKotlinBinaryFromCache(file);
        if (kotlinBinaryFromCache != null) {
            return kotlinBinaryFromCache.isKotlinBinary();
        }
        return kotlinJvmBinaryClass(file, fileContent, MetadataVersion.INSTANCE, null) != null;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/ClsKotlinBinaryClassCache$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getInstance", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/ClsKotlinBinaryClassCache;", "org.jetbrains.kotlin:decompiler-to-file-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ClsKotlinBinaryClassCache getInstance() {
            Object service = ApplicationManager.getApplication().getService(ClsKotlinBinaryClassCache.class);
            service.getClass();
            return (ClsKotlinBinaryClassCache) service;
        }

        private Companion() {
        }
    }
}
