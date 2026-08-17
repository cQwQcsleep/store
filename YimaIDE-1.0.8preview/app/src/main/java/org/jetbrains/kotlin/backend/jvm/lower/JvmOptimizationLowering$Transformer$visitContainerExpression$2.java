package org.jetbrains.kotlin.backend.jvm.lower;

import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.ir.declarations.IrVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class JvmOptimizationLowering$Transformer$visitContainerExpression$2 extends FunctionReferenceImpl implements Function1<IrVariable, Boolean> {
    public JvmOptimizationLowering$Transformer$visitContainerExpression$2(Object obj) {
        super(1, obj, HashSet.class, "contains", "contains(Ljava/lang/Object;)Z", 0);
    }

    public final Boolean invoke(IrVariable irVariable) {
        irVariable.getClass();
        return Boolean.valueOf(((HashSet) ((CallableReference) this).receiver).contains(irVariable));
    }
}
