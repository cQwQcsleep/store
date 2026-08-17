package org.jetbrains.kotlin.resolve.extensions;

import com.intellij.openapi.project.Project;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.analyzer.AnalysisResult;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.container.ComponentProvider;
import org.jetbrains.kotlin.context.ProjectContext;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.extensions.ProjectExtensionDescriptor;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.BindingTrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012J@\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J0\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/extensions/AnalysisHandlerExtension;", "", "doAnalysis", "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "project", "Lcom/intellij/openapi/project/Project;", "module", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "projectContext", "Lorg/jetbrains/kotlin/context/ProjectContext;", "files", "", "Lorg/jetbrains/kotlin/psi/KtFile;", "bindingTrace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "componentProvider", "Lorg/jetbrains/kotlin/container/ComponentProvider;", "analysisCompleted", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface AnalysisHandlerExtension {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/extensions/AnalysisHandlerExtension$Companion;", "Lorg/jetbrains/kotlin/extensions/ProjectExtensionDescriptor;", "Lorg/jetbrains/kotlin/resolve/extensions/AnalysisHandlerExtension;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion extends ProjectExtensionDescriptor<AnalysisHandlerExtension> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("org.jetbrains.kotlin.analyzeCompleteHandlerExtension", AnalysisHandlerExtension.class);
        }
    }

    default AnalysisResult analysisCompleted(Project project, ModuleDescriptor module, BindingTrace bindingTrace, Collection<? extends KtFile> files) {
        project.getClass();
        module.getClass();
        bindingTrace.getClass();
        files.getClass();
        return null;
    }

    default AnalysisResult doAnalysis(Project project, ModuleDescriptor module, ProjectContext projectContext, Collection<? extends KtFile> files, BindingTrace bindingTrace, ComponentProvider componentProvider) {
        project.getClass();
        module.getClass();
        projectContext.getClass();
        files.getClass();
        bindingTrace.getClass();
        componentProvider.getClass();
        return null;
    }
}
