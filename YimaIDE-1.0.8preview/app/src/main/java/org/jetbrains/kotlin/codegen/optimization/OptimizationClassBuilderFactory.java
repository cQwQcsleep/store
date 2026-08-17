package org.jetbrains.kotlin.codegen.optimization;

import org.jetbrains.kotlin.codegen.ClassBuilderFactory;
import org.jetbrains.kotlin.codegen.DelegatingClassBuilderFactory;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class OptimizationClassBuilderFactory extends DelegatingClassBuilderFactory {
    private final GenerationState generationState;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "generationState";
        } else {
            objArr[0] = "origin";
        }
        objArr[1] = "org/jetbrains/kotlin/codegen/optimization/OptimizationClassBuilderFactory";
        if (i != 1) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "newClassBuilder";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OptimizationClassBuilderFactory(ClassBuilderFactory classBuilderFactory, GenerationState generationState) {
        super(classBuilderFactory);
        if (generationState == null) {
            $$$reportNull$$$0(0);
        }
        this.generationState = generationState;
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilderFactory, org.jetbrains.kotlin.codegen.ClassBuilderFactory
    public OptimizationClassBuilder newClassBuilder(JvmDeclarationOrigin jvmDeclarationOrigin) {
        if (jvmDeclarationOrigin == null) {
            $$$reportNull$$$0(1);
        }
        return new OptimizationClassBuilder(getDelegate().newClassBuilder(jvmDeclarationOrigin), this.generationState);
    }
}
