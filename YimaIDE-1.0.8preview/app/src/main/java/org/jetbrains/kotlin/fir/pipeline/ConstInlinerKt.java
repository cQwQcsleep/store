package org.jetbrains.kotlin.fir.pipeline;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.expressions.IrConst;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"<set-?>", "", "wasInlined", "Lorg/jetbrains/kotlin/ir/expressions/IrConst;", "getWasInlined", "(Lorg/jetbrains/kotlin/ir/expressions/IrConst;)Ljava/lang/Boolean;", "setWasInlined", "(Lorg/jetbrains/kotlin/ir/expressions/IrConst;Ljava/lang/Boolean;)V", "wasInlined$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "org.jetbrains.kotlin:entrypoint"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ConstInlinerKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute wasInlined$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(ConstInlinerKt.class, "wasInlined", "getWasInlined(Lorg/jetbrains/kotlin/ir/expressions/IrConst;)Ljava/lang/Boolean;", 1)};
        $$delegatedProperties = kPropertyArr;
        wasInlined$delegate = IrAttributeKt.irAttribute(true).provideDelegate((Object) null, kPropertyArr[0]);
    }

    public static final Boolean getWasInlined(IrConst irConst) {
        irConst.getClass();
        return (Boolean) IrAttributeKt.get(irConst, wasInlined$delegate);
    }

    public static final void setWasInlined(IrConst irConst, Boolean bool) {
        irConst.getClass();
        IrAttributeKt.set(irConst, wasInlined$delegate, bool);
    }
}
