package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.validation.checkers.IrElementChecker;
import org.jetbrains.kotlin.ir.validation.checkers.context.CheckerContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/NoAnonymousInitializersChecker;", "Lorg/jetbrains/kotlin/ir/validation/checkers/IrElementChecker;", "Lorg/jetbrains/kotlin/ir/declarations/IrAnonymousInitializer;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "check", "", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "context", "Lorg/jetbrains/kotlin/ir/validation/checkers/context/CheckerContext;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class NoAnonymousInitializersChecker extends IrElementChecker<IrAnonymousInitializer> {
    public static final NoAnonymousInitializersChecker INSTANCE = new NoAnonymousInitializersChecker();

    private NoAnonymousInitializersChecker() {
        super(Reflection.getOrCreateKotlinClass(IrAnonymousInitializer.class));
    }

    public void check(IrAnonymousInitializer element, CheckerContext context) {
        element.getClass();
        context.getClass();
        context.error(this, element, "No anonymous initializers should remain at this stage");
    }
}
