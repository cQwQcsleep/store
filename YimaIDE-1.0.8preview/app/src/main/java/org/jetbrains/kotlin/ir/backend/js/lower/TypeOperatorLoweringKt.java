package org.jetbrains.kotlin.ir.backend.js.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.expressions.IrStatementOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"NOT_NULL_CHECK", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "getNOT_NULL_CHECK", "()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "NOT_NULL_CHECK$delegate", "Lkotlin/properties/ReadOnlyProperty;", "org.jetbrains.kotlin:backend.js"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class TypeOperatorLoweringKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final ReadOnlyProperty NOT_NULL_CHECK$delegate;

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference0Impl<>(TypeOperatorLoweringKt.class, "NOT_NULL_CHECK", "getNOT_NULL_CHECK()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 1)};
        $$delegatedProperties = kPropertyArr;
        NOT_NULL_CHECK$delegate = IrStatementOriginImpl.INSTANCE.m389provideDelegate((Object) null, kPropertyArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrStatementOriginImpl getNOT_NULL_CHECK() {
        return (IrStatementOriginImpl) NOT_NULL_CHECK$delegate.getValue((Object) null, $$delegatedProperties[0]);
    }
}
