package org.jetbrains.kotlin.load.kotlin;

import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.impl.VirtualFileBoundJavaClass;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinder;", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;", "perfManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "<init>", "(Lorg/jetbrains/kotlin/util/PerformanceManager;)V", "findVirtualFileWithHeader", "Lcom/intellij/openapi/vfs/VirtualFile;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "findSourceOrBinaryVirtualFile", "findKotlinClassOrContent", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder$Result;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "javaClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "classFileName", Argument.Delimiters.none, "jClass", "SERVICE", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class VirtualFileFinder implements KotlinClassFinder {
    public static final SERVICE SERVICE = new SERVICE((DefaultConstructorMarker) null);
    private final PerformanceManager perfManager;

    public /* synthetic */ VirtualFileFinder(PerformanceManager performanceManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : performanceManager);
    }

    private final String classFileName(JavaClass jClass) {
        String strAsString = jClass.getName().asString();
        strAsString.getClass();
        JavaClass outerClass = jClass.getOuterClass();
        if (outerClass == null) {
            return strAsString;
        }
        return classFileName(outerClass) + '$' + strAsString;
    }

    public KotlinClassFinder.Result findKotlinClassOrContent(JavaClass javaClass, MetadataVersion metadataVersion) {
        VirtualFile virtualFile;
        javaClass.getClass();
        metadataVersion.getClass();
        VirtualFileBoundJavaClass virtualFileBoundJavaClass = javaClass instanceof VirtualFileBoundJavaClass ? (VirtualFileBoundJavaClass) javaClass : null;
        if (virtualFileBoundJavaClass == null || (virtualFile = virtualFileBoundJavaClass.getVirtualFile()) == null) {
            return null;
        }
        if (javaClass.getOuterClass() != null) {
            VirtualFile parent = virtualFile.getParent();
            parent.getClass();
            virtualFile = parent.findChild(classFileName(javaClass) + ".class");
            if (virtualFile == null) {
                pe1.a("Virtual file not found for ", javaClass);
                return null;
            }
        }
        return KotlinBinaryClassCache.Companion.getKotlinBinaryClassOrClassFileContent$default(KotlinBinaryClassCache.Companion, virtualFile, metadataVersion, (byte[]) null, this.perfManager, 4, (Object) null);
    }

    public abstract VirtualFile findSourceOrBinaryVirtualFile(ClassId classId);

    public abstract VirtualFile findVirtualFileWithHeader(ClassId classId);

    public VirtualFileFinder(PerformanceManager performanceManager) {
        this.perfManager = performanceManager;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public VirtualFileFinder() {
        PerformanceManager performanceManager = null;
        this(performanceManager, 1, performanceManager);
    }

    public KotlinClassFinder.Result findKotlinClassOrContent(ClassId classId, MetadataVersion metadataVersion) {
        classId.getClass();
        metadataVersion.getClass();
        VirtualFile virtualFileFindVirtualFileWithHeader = findVirtualFileWithHeader(classId);
        if (virtualFileFindVirtualFileWithHeader == null) {
            return null;
        }
        return KotlinBinaryClassCache.Companion.getKotlinBinaryClassOrClassFileContent$default(KotlinBinaryClassCache.Companion, virtualFileFindVirtualFileWithHeader, metadataVersion, (byte[]) null, this.perfManager, 4, (Object) null);
    }
}
