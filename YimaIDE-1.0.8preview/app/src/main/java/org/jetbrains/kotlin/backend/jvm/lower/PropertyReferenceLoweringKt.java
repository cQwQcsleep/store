package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.expressions.IrRawFunctionReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"/\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"<set-?>", "", "needsDummySignature", "Lorg/jetbrains/kotlin/ir/expressions/IrRawFunctionReference;", "getNeedsDummySignature", "(Lorg/jetbrains/kotlin/ir/expressions/IrRawFunctionReference;)Z", "setNeedsDummySignature", "(Lorg/jetbrains/kotlin/ir/expressions/IrRawFunctionReference;Z)V", "needsDummySignature$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute$Flag;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class PropertyReferenceLoweringKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute.Flag needsDummySignature$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(PropertyReferenceLoweringKt.class, "needsDummySignature", "getNeedsDummySignature(Lorg/jetbrains/kotlin/ir/expressions/IrRawFunctionReference;)Z", 1)};
        $$delegatedProperties = kPropertyArr;
        needsDummySignature$delegate = IrAttributeKt.irFlag(true).provideDelegate((Object) null, kPropertyArr[0]);
    }

    public static final boolean getNeedsDummySignature(IrRawFunctionReference irRawFunctionReference) {
        irRawFunctionReference.getClass();
        return needsDummySignature$delegate.get(irRawFunctionReference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setNeedsDummySignature(IrRawFunctionReference irRawFunctionReference, boolean z) {
        needsDummySignature$delegate.set(irRawFunctionReference, z);
    }
}
