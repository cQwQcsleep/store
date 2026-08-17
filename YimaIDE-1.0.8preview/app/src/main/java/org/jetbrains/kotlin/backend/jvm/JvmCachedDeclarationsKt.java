package org.jetbrains.kotlin.backend.jvm;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0019\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"3\u0010\n\u001a\u0004\u0018\u00010\u0001*\u00020\u000b2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\t\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\"K\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011*\u00020\u00122\u0014\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00118B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\"3\u0010\u0019\u001a\u0004\u0018\u00010\u0012*\u00020\u00122\b\u0010\u0000\u001a\u0004\u0018\u00010\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\t\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\"3\u0010 \u001a\u0004\u0018\u00010\u001f*\u00020\u001f2\b\u0010\u0000\u001a\u0004\u0018\u00010\u001f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010\t\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\"3\u0010'\u001a\u0004\u0018\u00010&*\u00020\u00122\b\u0010\u0000\u001a\u0004\u0018\u00010&8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b,\u0010\t\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+\"3\u0010-\u001a\u0004\u0018\u00010\u0012*\u00020\u00122\b\u0010\u0000\u001a\u0004\u0018\u00010\u00128F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b0\u0010\t\u001a\u0004\b.\u0010\u001b\"\u0004\b/\u0010\u001d\"3\u00101\u001a\u0004\u0018\u00010\u001f*\u00020\u001f2\b\u0010\u0000\u001a\u0004\u0018\u00010\u001f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b4\u0010\t\u001a\u0004\b2\u0010\"\"\u0004\b3\u0010$\"3\u00105\u001a\u0004\u0018\u00010\u0001*\u00020\u001f2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010\t\u001a\u0004\b6\u00107\"\u0004\b8\u00109\"3\u0010;\u001a\u0004\u0018\u00010\u0001*\u00020\u001f2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010\t\u001a\u0004\b<\u00107\"\u0004\b=\u00109¨\u0006?"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "declaringField", "Lorg/jetbrains/kotlin/ir/declarations/IrEnumEntry;", "getDeclaringField", "(Lorg/jetbrains/kotlin/ir/declarations/IrEnumEntry;)Lorg/jetbrains/kotlin/ir/declarations/IrField;", "setDeclaringField", "(Lorg/jetbrains/kotlin/ir/declarations/IrEnumEntry;Lorg/jetbrains/kotlin/ir/declarations/IrField;)V", "declaringField$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "staticBackingFields", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "getStaticBackingFields", "(Lorg/jetbrains/kotlin/ir/declarations/IrProperty;)Lorg/jetbrains/kotlin/ir/declarations/IrField;", "setStaticBackingFields", "(Lorg/jetbrains/kotlin/ir/declarations/IrProperty;Lorg/jetbrains/kotlin/ir/declarations/IrField;)V", "staticBackingFields$delegate", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "staticCompanionDeclarations", "getStaticCompanionDeclarations", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lkotlin/Pair;", "setStaticCompanionDeclarations", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lkotlin/Pair;)V", "staticCompanionDeclarations$delegate", "defaultImplsMethod", "getDefaultImplsMethod", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "setDefaultImplsMethod", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "defaultImplsMethod$delegate", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "defaultImplsClass", "getDefaultImplsClass", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "setDefaultImplsClass", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/kotlin/ir/declarations/IrClass;)V", "defaultImplsClass$delegate", "Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement;", "classFakeOverrideReplacement", "getClassFakeOverrideReplacement", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement;", "setClassFakeOverrideReplacement", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement;)V", "classFakeOverrideReplacement$delegate", "originalFunctionForDefaultImpl", "getOriginalFunctionForDefaultImpl", "setOriginalFunctionForDefaultImpl", "originalFunctionForDefaultImpl$delegate", "repeatedAnnotationSyntheticContainer", "getRepeatedAnnotationSyntheticContainer", "setRepeatedAnnotationSyntheticContainer", "repeatedAnnotationSyntheticContainer$delegate", "fieldForObjectInstance", "getFieldForObjectInstance", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrField;", "setFieldForObjectInstance", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/kotlin/ir/declarations/IrField;)V", "fieldForObjectInstance$delegate", "interfaceCompanionFieldForObjectInstance", "getInterfaceCompanionFieldForObjectInstance", "setInterfaceCompanionFieldForObjectInstance", "interfaceCompanionFieldForObjectInstance$delegate", "org.jetbrains.kotlin:backend.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JvmCachedDeclarationsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute classFakeOverrideReplacement$delegate;
    private static final IrAttribute declaringField$delegate;
    private static final IrAttribute defaultImplsClass$delegate;
    private static final IrAttribute defaultImplsMethod$delegate;
    private static final IrAttribute fieldForObjectInstance$delegate;
    private static final IrAttribute interfaceCompanionFieldForObjectInstance$delegate;
    private static final IrAttribute originalFunctionForDefaultImpl$delegate;
    private static final IrAttribute repeatedAnnotationSyntheticContainer$delegate;
    private static final IrAttribute staticBackingFields$delegate;
    private static final IrAttribute staticCompanionDeclarations$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(JvmCachedDeclarationsKt.class, "declaringField", "getDeclaringField(Lorg/jetbrains/kotlin/ir/declarations/IrEnumEntry;)Lorg/jetbrains/kotlin/ir/declarations/IrField;", 1), new MutablePropertyReference1Impl<>(JvmCachedDeclarationsKt.class, "staticBackingFields", "getStaticBackingFields(Lorg/jetbrains/kotlin/ir/declarations/IrProperty;)Lorg/jetbrains/kotlin/ir/declarations/IrField;", 1), new MutablePropertyReference1Impl<>(JvmCachedDeclarationsKt.class, "staticCompanionDeclarations", "getStaticCompanionDeclarations(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lkotlin/Pair;", 1), new MutablePropertyReference1Impl<>(JvmCachedDeclarationsKt.class, "defaultImplsMethod", "getDefaultImplsMethod(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(JvmCachedDeclarationsKt.class, "defaultImplsClass", "getDefaultImplsClass(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrClass;", 1), new MutablePropertyReference1Impl<>(JvmCachedDeclarationsKt.class, "classFakeOverrideReplacement", "getClassFakeOverrideReplacement(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement;", 1), new MutablePropertyReference1Impl<>(JvmCachedDeclarationsKt.class, "originalFunctionForDefaultImpl", "getOriginalFunctionForDefaultImpl(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(JvmCachedDeclarationsKt.class, "repeatedAnnotationSyntheticContainer", "getRepeatedAnnotationSyntheticContainer(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrClass;", 1), new MutablePropertyReference1Impl<>(JvmCachedDeclarationsKt.class, "fieldForObjectInstance", "getFieldForObjectInstance(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrField;", 1), new MutablePropertyReference1Impl<>(JvmCachedDeclarationsKt.class, "interfaceCompanionFieldForObjectInstance", "getInterfaceCompanionFieldForObjectInstance(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrField;", 1)};
        $$delegatedProperties = kPropertyArr;
        declaringField$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[0]);
        staticBackingFields$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[1]);
        staticCompanionDeclarations$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[2]);
        defaultImplsMethod$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[3]);
        defaultImplsClass$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[4]);
        classFakeOverrideReplacement$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[5]);
        originalFunctionForDefaultImpl$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[6]);
        repeatedAnnotationSyntheticContainer$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[7]);
        fieldForObjectInstance$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[8]);
        interfaceCompanionFieldForObjectInstance$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[9]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClassFakeOverrideReplacement getClassFakeOverrideReplacement(IrSimpleFunction irSimpleFunction) {
        return (ClassFakeOverrideReplacement) IrAttributeKt.get(irSimpleFunction, classFakeOverrideReplacement$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrField getDeclaringField(IrEnumEntry irEnumEntry) {
        return (IrField) IrAttributeKt.get(irEnumEntry, declaringField$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrClass getDefaultImplsClass(IrClass irClass) {
        return (IrClass) IrAttributeKt.get(irClass, defaultImplsClass$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrSimpleFunction getDefaultImplsMethod(IrSimpleFunction irSimpleFunction) {
        return (IrSimpleFunction) IrAttributeKt.get(irSimpleFunction, defaultImplsMethod$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrField getFieldForObjectInstance(IrClass irClass) {
        return (IrField) IrAttributeKt.get(irClass, fieldForObjectInstance$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrField getInterfaceCompanionFieldForObjectInstance(IrClass irClass) {
        return (IrField) IrAttributeKt.get(irClass, interfaceCompanionFieldForObjectInstance$delegate);
    }

    public static final IrSimpleFunction getOriginalFunctionForDefaultImpl(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irSimpleFunction, originalFunctionForDefaultImpl$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrClass getRepeatedAnnotationSyntheticContainer(IrClass irClass) {
        return (IrClass) IrAttributeKt.get(irClass, repeatedAnnotationSyntheticContainer$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrField getStaticBackingFields(IrProperty irProperty) {
        return (IrField) IrAttributeKt.get(irProperty, staticBackingFields$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<IrSimpleFunction, IrSimpleFunction> getStaticCompanionDeclarations(IrSimpleFunction irSimpleFunction) {
        return (Pair) IrAttributeKt.get(irSimpleFunction, staticCompanionDeclarations$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setClassFakeOverrideReplacement(IrSimpleFunction irSimpleFunction, ClassFakeOverrideReplacement classFakeOverrideReplacement) {
        IrAttributeKt.set(irSimpleFunction, classFakeOverrideReplacement$delegate, classFakeOverrideReplacement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDeclaringField(IrEnumEntry irEnumEntry, IrField irField) {
        IrAttributeKt.set(irEnumEntry, declaringField$delegate, irField);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDefaultImplsClass(IrClass irClass, IrClass irClass2) {
        IrAttributeKt.set(irClass, defaultImplsClass$delegate, irClass2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDefaultImplsMethod(IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2) {
        IrAttributeKt.set(irSimpleFunction, defaultImplsMethod$delegate, irSimpleFunction2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setFieldForObjectInstance(IrClass irClass, IrField irField) {
        IrAttributeKt.set(irClass, fieldForObjectInstance$delegate, irField);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setInterfaceCompanionFieldForObjectInstance(IrClass irClass, IrField irField) {
        IrAttributeKt.set(irClass, interfaceCompanionFieldForObjectInstance$delegate, irField);
    }

    public static final void setOriginalFunctionForDefaultImpl(IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2) {
        irSimpleFunction.getClass();
        IrAttributeKt.set(irSimpleFunction, originalFunctionForDefaultImpl$delegate, irSimpleFunction2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRepeatedAnnotationSyntheticContainer(IrClass irClass, IrClass irClass2) {
        IrAttributeKt.set(irClass, repeatedAnnotationSyntheticContainer$delegate, irClass2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setStaticBackingFields(IrProperty irProperty, IrField irField) {
        IrAttributeKt.set(irProperty, staticBackingFields$delegate, irField);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setStaticCompanionDeclarations(IrSimpleFunction irSimpleFunction, Pair<? extends IrSimpleFunction, ? extends IrSimpleFunction> pair) {
        IrAttributeKt.set(irSimpleFunction, staticCompanionDeclarations$delegate, pair);
    }
}
