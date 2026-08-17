package org.jetbrains.kotlin.analysis.decompiled.light.classes;

import com.intellij.ide.highlighter.JavaClassFileType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.impl.compiled.ClsFileImpl;
import com.intellij.psi.impl.java.stubs.PsiJavaFileStub;
import com.intellij.psi.impl.java.stubs.impl.PsiJavaFileStubImpl;
import com.intellij.util.cls.ClsFormatException;
import com.intellij.util.containers.ContainerUtil;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0002\u0010\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002R3\u0010\u0004\u001a'\u0012\u000e\u0012\f0\u0006¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b\u0012\u000e\u0012\f0\t¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b0\u0005¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiled/light/classes/ClsJavaStubByVirtualFileCache;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "cache", "Ljava/util/concurrent/ConcurrentMap;", "Lcom/intellij/openapi/vfs/VirtualFile;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lorg/jetbrains/kotlin/analysis/decompiled/light/classes/ClsJavaStubByVirtualFileCache$CachedJavaStub;", "get", "Lcom/intellij/psi/impl/java/stubs/impl/PsiJavaFileStubImpl;", "classFile", "createStub", "Lcom/intellij/psi/impl/java/stubs/PsiJavaFileStub;", "file", "CachedJavaStub", "Companion", "org.jetbrains.kotlin:light-classes-for-decompiled"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ClsJavaStubByVirtualFileCache {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG;
    private final ConcurrentMap<VirtualFile, CachedJavaStub> cache;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiled/light/classes/ClsJavaStubByVirtualFileCache$CachedJavaStub;", "", "modificationStamp", "", "javaFileStub", "Lcom/intellij/psi/impl/java/stubs/impl/PsiJavaFileStubImpl;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(JLcom/intellij/psi/impl/java/stubs/impl/PsiJavaFileStubImpl;)V", "getModificationStamp", "()J", "getJavaFileStub", "()Lcom/intellij/psi/impl/java/stubs/impl/PsiJavaFileStubImpl;", "org.jetbrains.kotlin:light-classes-for-decompiled"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class CachedJavaStub {
        private final PsiJavaFileStubImpl javaFileStub;
        private final long modificationStamp;

        public CachedJavaStub(long j, PsiJavaFileStubImpl psiJavaFileStubImpl) {
            psiJavaFileStubImpl.getClass();
            this.modificationStamp = j;
            this.javaFileStub = psiJavaFileStubImpl;
        }

        public final PsiJavaFileStubImpl getJavaFileStub() {
            return this.javaFileStub;
        }

        public final long getModificationStamp() {
            return this.modificationStamp;
        }
    }

    static {
        Logger logger = Logger.getInstance(ClsJavaStubByVirtualFileCache.class);
        logger.getClass();
        LOG = logger;
    }

    public ClsJavaStubByVirtualFileCache() {
        ConcurrentMap<VirtualFile, CachedJavaStub> concurrentMapCreateConcurrentWeakKeySoftValueMap = ContainerUtil.createConcurrentWeakKeySoftValueMap();
        concurrentMapCreateConcurrentWeakKeySoftValueMap.getClass();
        this.cache = concurrentMapCreateConcurrentWeakKeySoftValueMap;
    }

    private final PsiJavaFileStub createStub(VirtualFile file) {
        String extension = file.getExtension();
        FileType fileType = JavaClassFileType.INSTANCE;
        if (!Intrinsics.areEqual(extension, fileType.getDefaultExtension()) && file.getFileType() != fileType) {
            return null;
        }
        try {
            return ClsFileImpl.buildFileStub(file, file.contentsToByteArray(false));
        } catch (ClsFormatException e) {
            Logger logger = LOG;
            StringBuilder sb = new StringBuilder("Failed to build java cls class for ");
            String canonicalPath = file.getCanonicalPath();
            canonicalPath.getClass();
            sb.append(canonicalPath);
            logger.warn(sb.toString(), e);
            return null;
        } catch (IOException e2) {
            Logger logger2 = LOG;
            StringBuilder sb2 = new StringBuilder("Failed to build java cls class for ");
            String canonicalPath2 = file.getCanonicalPath();
            canonicalPath2.getClass();
            sb2.append(canonicalPath2);
            logger2.warn(sb2.toString(), e2);
            return null;
        }
    }

    public final PsiJavaFileStubImpl get(VirtualFile classFile) {
        classFile.getClass();
        CachedJavaStub cachedJavaStub = this.cache.get(classFile);
        long modificationStamp = classFile.getModificationStamp();
        if (cachedJavaStub != null && cachedJavaStub.getModificationStamp() == modificationStamp) {
            return cachedJavaStub.getJavaFileStub();
        }
        PsiJavaFileStubImpl psiJavaFileStubImplCreateStub = createStub(classFile);
        if (psiJavaFileStubImplCreateStub == null) {
            return null;
        }
        this.cache.put(classFile, new CachedJavaStub(modificationStamp, psiJavaFileStubImplCreateStub));
        return psiJavaFileStubImplCreateStub;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0013\u0010\u0004\u001a\u00070\u0005¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiled/light/classes/ClsJavaStubByVirtualFileCache$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "LOG", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "getInstance", "Lorg/jetbrains/kotlin/analysis/decompiled/light/classes/ClsJavaStubByVirtualFileCache;", "project", "Lcom/intellij/openapi/project/Project;", "org.jetbrains.kotlin:light-classes-for-decompiled"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ClsJavaStubByVirtualFileCache getInstance(Project project) {
            project.getClass();
            Object service = project.getService(ClsJavaStubByVirtualFileCache.class);
            service.getClass();
            return (ClsJavaStubByVirtualFileCache) service;
        }

        private Companion() {
        }
    }
}
