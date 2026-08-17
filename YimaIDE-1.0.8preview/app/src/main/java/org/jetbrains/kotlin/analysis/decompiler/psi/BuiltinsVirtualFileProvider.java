package org.jetbrains.kotlin.analysis.decompiler.psi;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/psi/BuiltinsVirtualFileProvider;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getBuiltinVirtualFiles", "", "Lcom/intellij/openapi/vfs/VirtualFile;", "createBuiltinsScope", "Lcom/intellij/psi/search/GlobalSearchScope;", "project", "Lcom/intellij/openapi/project/Project;", "Companion", "org.jetbrains.kotlin:decompiler-to-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class BuiltinsVirtualFileProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public abstract GlobalSearchScope createBuiltinsScope(Project project);

    public abstract Set<VirtualFile> getBuiltinVirtualFiles();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/psi/BuiltinsVirtualFileProvider$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getInstance", "Lorg/jetbrains/kotlin/analysis/decompiler/psi/BuiltinsVirtualFileProvider;", "org.jetbrains.kotlin:decompiler-to-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BuiltinsVirtualFileProvider getInstance() {
            Object service = ApplicationManager.getApplication().getService(BuiltinsVirtualFileProvider.class);
            service.getClass();
            return (BuiltinsVirtualFileProvider) service;
        }

        private Companion() {
        }
    }
}
