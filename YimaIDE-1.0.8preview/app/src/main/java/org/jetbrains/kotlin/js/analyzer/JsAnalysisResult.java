package org.jetbrains.kotlin.js.analyzer;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.analyzer.AnalysisResult;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.library.components.KlibMetadataConstants;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingTrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0016\u0018\u0000 \f2\u00020\u0001:\u0002\f\rB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/js/analyzer/JsAnalysisResult;", "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "bindingTrace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "shouldGenerateCode", "", "<init>", "(Lorg/jetbrains/kotlin/resolve/BindingTrace;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Z)V", "getBindingTrace", "()Lorg/jetbrains/kotlin/resolve/BindingTrace;", "Companion", "RetryWithAdditionalRoots", "org.jetbrains.kotlin:js.frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class JsAnalysisResult extends AnalysisResult {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BindingTrace bindingTrace;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/js/analyzer/JsAnalysisResult$RetryWithAdditionalRoots;", "Lorg/jetbrains/kotlin/js/analyzer/JsAnalysisResult;", "bindingTrace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "additionalKotlinRoots", "", "Ljava/io/File;", "<init>", "(Lorg/jetbrains/kotlin/resolve/BindingTrace;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Ljava/util/List;)V", "getAdditionalKotlinRoots", "()Ljava/util/List;", "org.jetbrains.kotlin:js.frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class RetryWithAdditionalRoots extends JsAnalysisResult {
        private final List<File> additionalKotlinRoots;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public RetryWithAdditionalRoots(BindingTrace bindingTrace, ModuleDescriptor moduleDescriptor, List<? extends File> list) {
            super(bindingTrace, moduleDescriptor, false);
            bindingTrace.getClass();
            moduleDescriptor.getClass();
            list.getClass();
            this.additionalKotlinRoots = list;
        }

        public final List<File> getAdditionalKotlinRoots() {
            return this.additionalKotlinRoots;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public JsAnalysisResult(BindingTrace bindingTrace, ModuleDescriptor moduleDescriptor, boolean z) {
        bindingTrace.getClass();
        moduleDescriptor.getClass();
        BindingContext bindingContext = bindingTrace.getBindingContext();
        bindingContext.getClass();
        super(bindingContext, moduleDescriptor, z);
        this.bindingTrace = bindingTrace;
    }

    @JvmStatic
    public static final JsAnalysisResult success(BindingTrace bindingTrace, ModuleDescriptor moduleDescriptor, boolean z) {
        return INSTANCE.success(bindingTrace, moduleDescriptor, z);
    }

    public final BindingTrace getBindingTrace() {
        return this.bindingTrace;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007b\u0002\b\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/js/analyzer/JsAnalysisResult$Companion;", "", "<init>", "()V", "success", "Lorg/jetbrains/kotlin/js/analyzer/JsAnalysisResult;", "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "shouldGenerateCode", "", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:js.frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final JsAnalysisResult success(BindingTrace trace, ModuleDescriptor module, boolean shouldGenerateCode) {
            trace.getClass();
            module.getClass();
            return new JsAnalysisResult(trace, module, shouldGenerateCode);
        }

        private Companion() {
        }
    }
}
