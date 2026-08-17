package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.expressions.IrRichFunctionReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/backend/jvm/lower/IndyCallData;", "indyCallData", "Lorg/jetbrains/kotlin/ir/expressions/IrRichFunctionReference;", "getIndyCallData", "(Lorg/jetbrains/kotlin/ir/expressions/IrRichFunctionReference;)Lorg/jetbrains/kotlin/backend/jvm/lower/IndyCallData;", "setIndyCallData", "(Lorg/jetbrains/kotlin/ir/expressions/IrRichFunctionReference;Lorg/jetbrains/kotlin/backend/jvm/lower/IndyCallData;)V", "indyCallData$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class FunctionReferenceLoweringKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute indyCallData$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(FunctionReferenceLoweringKt.class, "indyCallData", "getIndyCallData(Lorg/jetbrains/kotlin/ir/expressions/IrRichFunctionReference;)Lorg/jetbrains/kotlin/backend/jvm/lower/IndyCallData;", 1)};
        $$delegatedProperties = kPropertyArr;
        indyCallData$delegate = IrAttributeKt.irAttribute(true).provideDelegate((Object) null, kPropertyArr[0]);
    }

    public static final IndyCallData getIndyCallData(IrRichFunctionReference irRichFunctionReference) {
        irRichFunctionReference.getClass();
        return (IndyCallData) IrAttributeKt.get(irRichFunctionReference, indyCallData$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setIndyCallData(IrRichFunctionReference irRichFunctionReference, IndyCallData indyCallData) {
        IrAttributeKt.set(irRichFunctionReference, indyCallData$delegate, indyCallData);
    }
}
