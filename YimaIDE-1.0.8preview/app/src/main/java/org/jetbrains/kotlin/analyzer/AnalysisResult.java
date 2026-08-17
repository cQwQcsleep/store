package org.jetbrains.kotlin.analyzer;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.types.error.ErrorUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 !2\u00020\u0001:\u0004\u001e\u001f !B#\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\u0010\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J\t\u0010\u0014\u001a\u00020\u0003H\u0086\u0002J\t\u0010\u0015\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0016\u001a\u00020\u0007H\u0086\u0002J\u0006\u0010\u001b\u001a\u00020\u0007J\u0006\u0010\u001c\u001a\u00020\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "shouldGenerateCode", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/resolve/BindingContext;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Z)V", "getBindingContext", "()Lorg/jetbrains/kotlin/resolve/BindingContext;", "getModuleDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getShouldGenerateCode", "()Z", "equals", "other", "hashCode", "", "component1", "component2", "component3", CompilerOptions.ERROR, "", "getError", "()Ljava/lang/Throwable;", "isError", "throwIfError", "", "CompilationError", "InternalError", "RetryWithAdditionalRoots", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class AnalysisResult {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final AnalysisResult EMPTY;
    private final BindingContext bindingContext;
    private final ModuleDescriptor moduleDescriptor;
    private final boolean shouldGenerateCode;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/AnalysisResult$CompilationError;", "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/resolve/BindingContext;)V", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class CompilationError extends AnalysisResult {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CompilationError(BindingContext bindingContext) {
            super(bindingContext, ErrorUtils.INSTANCE.getErrorModule(), false, 4, null);
            bindingContext.getClass();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/AnalysisResult$InternalError;", "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "exception", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/resolve/BindingContext;Ljava/lang/Throwable;)V", "getException", "()Ljava/lang/Throwable;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class InternalError extends AnalysisResult {
        private final Throwable exception;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InternalError(BindingContext bindingContext, Throwable th) {
            super(bindingContext, ErrorUtils.INSTANCE.getErrorModule(), false, 4, null);
            bindingContext.getClass();
            th.getClass();
            this.exception = th;
        }

        public final Throwable getException() {
            return this.exception;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        BindingContext bindingContext = BindingContext.EMPTY;
        bindingContext.getClass();
        EMPTY = companion.success(bindingContext, ErrorUtils.INSTANCE.getErrorModule());
    }

    public AnalysisResult(BindingContext bindingContext, ModuleDescriptor moduleDescriptor, boolean z) {
        bindingContext.getClass();
        moduleDescriptor.getClass();
        this.bindingContext = bindingContext;
        this.moduleDescriptor = moduleDescriptor;
        this.shouldGenerateCode = z;
    }

    @JvmStatic
    public static final AnalysisResult compilationError(BindingContext bindingContext) {
        return INSTANCE.compilationError(bindingContext);
    }

    @JvmStatic
    public static final AnalysisResult internalError(BindingContext bindingContext, Throwable th) {
        return INSTANCE.internalError(bindingContext, th);
    }

    @JvmStatic
    public static final AnalysisResult success(BindingContext bindingContext, ModuleDescriptor moduleDescriptor) {
        return INSTANCE.success(bindingContext, moduleDescriptor);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final BindingContext getBindingContext() {
        return this.bindingContext;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getShouldGenerateCode() {
        return this.shouldGenerateCode;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnalysisResult)) {
            return false;
        }
        AnalysisResult analysisResult = (AnalysisResult) other;
        return Intrinsics.areEqual(this.bindingContext, analysisResult.bindingContext) && Intrinsics.areEqual(this.moduleDescriptor, analysisResult.moduleDescriptor) && this.shouldGenerateCode == analysisResult.shouldGenerateCode;
    }

    public final BindingContext getBindingContext() {
        return this.bindingContext;
    }

    public final Throwable getError() {
        if (this instanceof InternalError) {
            return ((InternalError) this).getException();
        }
        k2d.a("Should only be called for error analysis result");
        return null;
    }

    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    public final boolean getShouldGenerateCode() {
        return this.shouldGenerateCode;
    }

    public int hashCode() {
        return ((((493 + this.bindingContext.hashCode()) * 29) + this.moduleDescriptor.hashCode()) * 29) + Boolean.hashCode(this.shouldGenerateCode);
    }

    public final boolean isError() {
        return (this instanceof InternalError) || (this instanceof CompilationError);
    }

    public final void throwIfError() {
        if (!(this instanceof InternalError)) {
            if (this instanceof CompilationError) {
                throw new CompilationErrorException();
            }
        } else {
            mg9.a("failed to analyze: " + getError(), getError());
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007b\u0002\b\rJ$\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007b\u0002\b\rJ\u001c\u0010\u0010\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007b\u0002\b\rJ\u0014\u0010\u0013\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0007b\u0002\b\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/AnalysisResult$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "EMPTY", "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "getEMPTY", "()Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "success", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "module", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "Lkotlin/jvm/JvmStatic;", "shouldGenerateCode", "", "internalError", CompilerOptions.ERROR, "", "compilationError", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final AnalysisResult compilationError(BindingContext bindingContext) {
            bindingContext.getClass();
            return new CompilationError(bindingContext);
        }

        public final AnalysisResult getEMPTY() {
            return AnalysisResult.EMPTY;
        }

        @JvmStatic
        public final AnalysisResult internalError(BindingContext bindingContext, Throwable error) {
            bindingContext.getClass();
            error.getClass();
            return new InternalError(bindingContext, error);
        }

        @JvmStatic
        public final AnalysisResult success(BindingContext bindingContext, ModuleDescriptor module) {
            bindingContext.getClass();
            module.getClass();
            return new AnalysisResult(bindingContext, module, true);
        }

        private Companion() {
        }

        @JvmStatic
        public final AnalysisResult success(BindingContext bindingContext, ModuleDescriptor module, boolean shouldGenerateCode) {
            bindingContext.getClass();
            module.getClass();
            return new AnalysisResult(bindingContext, module, shouldGenerateCode);
        }
    }

    @JvmStatic
    public static final AnalysisResult success(BindingContext bindingContext, ModuleDescriptor moduleDescriptor, boolean z) {
        return INSTANCE.success(bindingContext, moduleDescriptor, z);
    }

    public /* synthetic */ AnalysisResult(BindingContext bindingContext, ModuleDescriptor moduleDescriptor, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bindingContext, moduleDescriptor, (i & 4) != 0 ? true : z);
    }
}
