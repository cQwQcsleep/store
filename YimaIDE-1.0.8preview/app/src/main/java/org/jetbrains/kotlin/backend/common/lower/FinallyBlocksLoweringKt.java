package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.expressions.IrStatementOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003\"\u001b\u0010\u0006\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\u0005\u001a\u0004\b\u0007\u0010\u0003¨\u0006\t"}, d2 = {"FINALLY_EXPRESSION", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "getFINALLY_EXPRESSION", "()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "FINALLY_EXPRESSION$delegate", "Lkotlin/properties/ReadOnlyProperty;", "SYNTHETIC_CATCH_FOR_FINALLY_EXPRESSION", "getSYNTHETIC_CATCH_FOR_FINALLY_EXPRESSION", "SYNTHETIC_CATCH_FOR_FINALLY_EXPRESSION$delegate", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class FinallyBlocksLoweringKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final ReadOnlyProperty FINALLY_EXPRESSION$delegate;
    private static final ReadOnlyProperty SYNTHETIC_CATCH_FOR_FINALLY_EXPRESSION$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new PropertyReference0Impl<>(FinallyBlocksLoweringKt.class, "FINALLY_EXPRESSION", "getFINALLY_EXPRESSION()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 1), new PropertyReference0Impl<>(FinallyBlocksLoweringKt.class, "SYNTHETIC_CATCH_FOR_FINALLY_EXPRESSION", "getSYNTHETIC_CATCH_FOR_FINALLY_EXPRESSION()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 1)};
        $$delegatedProperties = kPropertyArr;
        IrStatementOriginImpl.Companion companion = IrStatementOriginImpl.Companion;
        FINALLY_EXPRESSION$delegate = companion.provideDelegate((Object) null, kPropertyArr[0]);
        SYNTHETIC_CATCH_FOR_FINALLY_EXPRESSION$delegate = companion.provideDelegate((Object) null, kPropertyArr[1]);
    }

    public static final IrStatementOriginImpl getFINALLY_EXPRESSION() {
        return (IrStatementOriginImpl) FINALLY_EXPRESSION$delegate.getValue((Object) null, $$delegatedProperties[0]);
    }

    public static final IrStatementOriginImpl getSYNTHETIC_CATCH_FOR_FINALLY_EXPRESSION() {
        return (IrStatementOriginImpl) SYNTHETIC_CATCH_FOR_FINALLY_EXPRESSION$delegate.getValue((Object) null, $$delegatedProperties[1]);
    }
}
