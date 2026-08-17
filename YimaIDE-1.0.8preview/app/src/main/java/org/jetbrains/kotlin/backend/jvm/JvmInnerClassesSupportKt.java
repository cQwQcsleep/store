package org.jetbrains.kotlin.backend.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrField;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"3\u0010\u000b\u001a\u0004\u0018\u00010\n*\u00020\n2\b\u0010\u0000\u001a\u0004\u0018\u00010\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\t\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\"3\u0010\u0011\u001a\u0004\u0018\u00010\n*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\t\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "innerClassOuterThisField", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getInnerClassOuterThisField", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrField;", "setInnerClassOuterThisField", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/kotlin/ir/declarations/IrField;)V", "innerClassOuterThisField$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "innerClassConstructorWithOuterThisParameter", "getInnerClassConstructorWithOuterThisParameter", "(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "setInnerClassConstructorWithOuterThisParameter", "(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)V", "innerClassConstructorWithOuterThisParameter$delegate", "innerClassOriginalPrimaryConstructor", "getInnerClassOriginalPrimaryConstructor", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "setInnerClassOriginalPrimaryConstructor", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)V", "innerClassOriginalPrimaryConstructor$delegate", "org.jetbrains.kotlin:backend.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JvmInnerClassesSupportKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute innerClassConstructorWithOuterThisParameter$delegate;
    private static final IrAttribute innerClassOriginalPrimaryConstructor$delegate;
    private static final IrAttribute innerClassOuterThisField$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(JvmInnerClassesSupportKt.class, "innerClassOuterThisField", "getInnerClassOuterThisField(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrField;", 1), new MutablePropertyReference1Impl<>(JvmInnerClassesSupportKt.class, "innerClassConstructorWithOuterThisParameter", "getInnerClassConstructorWithOuterThisParameter(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", 1), new MutablePropertyReference1Impl<>(JvmInnerClassesSupportKt.class, "innerClassOriginalPrimaryConstructor", "getInnerClassOriginalPrimaryConstructor(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", 1)};
        $$delegatedProperties = kPropertyArr;
        innerClassOuterThisField$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[0]);
        innerClassConstructorWithOuterThisParameter$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[1]);
        innerClassOriginalPrimaryConstructor$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrConstructor getInnerClassConstructorWithOuterThisParameter(IrConstructor irConstructor) {
        return (IrConstructor) IrAttributeKt.get(irConstructor, innerClassConstructorWithOuterThisParameter$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrConstructor getInnerClassOriginalPrimaryConstructor(IrClass irClass) {
        return (IrConstructor) IrAttributeKt.get(irClass, innerClassOriginalPrimaryConstructor$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrField getInnerClassOuterThisField(IrClass irClass) {
        return (IrField) IrAttributeKt.get(irClass, innerClassOuterThisField$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setInnerClassConstructorWithOuterThisParameter(IrConstructor irConstructor, IrConstructor irConstructor2) {
        IrAttributeKt.set(irConstructor, innerClassConstructorWithOuterThisParameter$delegate, irConstructor2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setInnerClassOriginalPrimaryConstructor(IrClass irClass, IrConstructor irConstructor) {
        IrAttributeKt.set(irClass, innerClassOriginalPrimaryConstructor$delegate, irConstructor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setInnerClassOuterThisField(IrClass irClass, IrField irField) {
        IrAttributeKt.set(irClass, innerClassOuterThisField$delegate, irField);
    }
}
