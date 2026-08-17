package org.jetbrains.kotlin.resolve.jvm.modules;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.load.java.JavaModuleAnnotationsProvider;
import org.jetbrains.kotlin.name.FqName;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \n2\u00020\u0001:\u0002\t\nJ&\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver;", "Lorg/jetbrains/kotlin/load/java/JavaModuleAnnotationsProvider;", "checkAccessibility", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError;", "fileFromOurModule", "Lcom/intellij/openapi/vfs/VirtualFile;", "referencedFile", "referencedPackage", "Lorg/jetbrains/kotlin/name/FqName;", "AccessError", "SERVICE", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface JavaModuleResolver extends JavaModuleAnnotationsProvider {

    /* JADX INFO: renamed from: SERVICE, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleResolver$SERVICE, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$SERVICE;", "", "<init>", "()V", "getInstance", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver;", "project", "Lcom/intellij/openapi/project/Project;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final JavaModuleResolver getInstance(Project project) {
            project.getClass();
            Object service = project.getService(JavaModuleResolver.class);
            service.getClass();
            return (JavaModuleResolver) service;
        }
    }

    AccessError checkAccessibility(VirtualFile fileFromOurModule, VirtualFile referencedFile, FqName referencedPackage);

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError;", "", "<init>", "()V", "ModuleDoesNotReadUnnamedModule", "ModuleDoesNotReadModule", "ModuleDoesNotExportPackage", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError$ModuleDoesNotExportPackage;", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError$ModuleDoesNotReadModule;", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError$ModuleDoesNotReadUnnamedModule;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static abstract class AccessError {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError$ModuleDoesNotExportPackage;", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError;", "dependencyModuleName", "", "<init>", "(Ljava/lang/String;)V", "getDependencyModuleName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final /* data */ class ModuleDoesNotExportPackage extends AccessError {
            private final String dependencyModuleName;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ModuleDoesNotExportPackage(String str) {
                super(null);
                str.getClass();
                this.dependencyModuleName = str;
            }

            public static /* synthetic */ ModuleDoesNotExportPackage copy$default(ModuleDoesNotExportPackage moduleDoesNotExportPackage, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = moduleDoesNotExportPackage.dependencyModuleName;
                }
                return moduleDoesNotExportPackage.copy(str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getDependencyModuleName() {
                return this.dependencyModuleName;
            }

            public final ModuleDoesNotExportPackage copy(String dependencyModuleName) {
                dependencyModuleName.getClass();
                return new ModuleDoesNotExportPackage(dependencyModuleName);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof ModuleDoesNotExportPackage) && Intrinsics.areEqual(this.dependencyModuleName, ((ModuleDoesNotExportPackage) other).dependencyModuleName);
            }

            public final String getDependencyModuleName() {
                return this.dependencyModuleName;
            }

            public int hashCode() {
                return this.dependencyModuleName.hashCode();
            }

            public String toString() {
                return "ModuleDoesNotExportPackage(dependencyModuleName=" + this.dependencyModuleName + ')';
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError$ModuleDoesNotReadModule;", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError;", "dependencyModuleName", "", "<init>", "(Ljava/lang/String;)V", "getDependencyModuleName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final /* data */ class ModuleDoesNotReadModule extends AccessError {
            private final String dependencyModuleName;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ModuleDoesNotReadModule(String str) {
                super(null);
                str.getClass();
                this.dependencyModuleName = str;
            }

            public static /* synthetic */ ModuleDoesNotReadModule copy$default(ModuleDoesNotReadModule moduleDoesNotReadModule, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = moduleDoesNotReadModule.dependencyModuleName;
                }
                return moduleDoesNotReadModule.copy(str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getDependencyModuleName() {
                return this.dependencyModuleName;
            }

            public final ModuleDoesNotReadModule copy(String dependencyModuleName) {
                dependencyModuleName.getClass();
                return new ModuleDoesNotReadModule(dependencyModuleName);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof ModuleDoesNotReadModule) && Intrinsics.areEqual(this.dependencyModuleName, ((ModuleDoesNotReadModule) other).dependencyModuleName);
            }

            public final String getDependencyModuleName() {
                return this.dependencyModuleName;
            }

            public int hashCode() {
                return this.dependencyModuleName.hashCode();
            }

            public String toString() {
                return "ModuleDoesNotReadModule(dependencyModuleName=" + this.dependencyModuleName + ')';
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError$ModuleDoesNotReadUnnamedModule;", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError;", "<init>", "()V", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class ModuleDoesNotReadUnnamedModule extends AccessError {
            public static final ModuleDoesNotReadUnnamedModule INSTANCE = new ModuleDoesNotReadUnnamedModule();

            private ModuleDoesNotReadUnnamedModule() {
                super(null);
            }
        }

        public /* synthetic */ AccessError(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private AccessError() {
        }
    }
}
