package org.jetbrains.kotlin.backend.wasm;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.expressions.IrCatch;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"3\u0010\n\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\t\u001a\u0004\b\u000b\u0010\u0005\"\u0004\b\f\u0010\u0007\"3\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u00020\u000f2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\t\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\"3\u0010\u0015\u001a\u0004\u0018\u00010\u0001*\u00020\u000f2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013\"3\u0010\u0019\u001a\u0004\u0018\u00010\u0001*\u00020\u000f2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\t\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013\"/\u0010\u001e\u001a\u00020\u001d*\u00020\u001f2\u0006\u0010\u0000\u001a\u00020\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006&"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "topLevelFunctionForNestedExternal", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getTopLevelFunctionForNestedExternal", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "setTopLevelFunctionForNestedExternal", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "topLevelFunctionForNestedExternal$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "jsFunctionForExternalAdapterFunction", "getJsFunctionForExternalAdapterFunction", "setJsFunctionForExternalAdapterFunction", "jsFunctionForExternalAdapterFunction$delegate", "getInstanceFunctionForExternalObject", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getGetInstanceFunctionForExternalObject", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "setGetInstanceFunctionForExternalObject", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "getInstanceFunctionForExternalObject$delegate", "instanceCheckForExternalClass", "getInstanceCheckForExternalClass", "setInstanceCheckForExternalClass", "instanceCheckForExternalClass$delegate", "getJsClassForExternalClass", "getGetJsClassForExternalClass", "setGetJsClassForExternalClass", "getJsClassForExternalClass$delegate", "", "toCatchThrowableOrJsException", "Lorg/jetbrains/kotlin/ir/expressions/IrCatch;", "getToCatchThrowableOrJsException", "(Lorg/jetbrains/kotlin/ir/expressions/IrCatch;)Z", "setToCatchThrowableOrJsException", "(Lorg/jetbrains/kotlin/ir/expressions/IrCatch;Z)V", "toCatchThrowableOrJsException$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute$Flag;", "org.jetbrains.kotlin:backend.wasm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class WasmIrAttributesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute getInstanceFunctionForExternalObject$delegate;
    private static final IrAttribute getJsClassForExternalClass$delegate;
    private static final IrAttribute instanceCheckForExternalClass$delegate;
    private static final IrAttribute jsFunctionForExternalAdapterFunction$delegate;
    private static final IrAttribute.Flag toCatchThrowableOrJsException$delegate;
    private static final IrAttribute topLevelFunctionForNestedExternal$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(WasmIrAttributesKt.class, "topLevelFunctionForNestedExternal", "getTopLevelFunctionForNestedExternal(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(WasmIrAttributesKt.class, "jsFunctionForExternalAdapterFunction", "getJsFunctionForExternalAdapterFunction(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(WasmIrAttributesKt.class, "getInstanceFunctionForExternalObject", "getGetInstanceFunctionForExternalObject(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(WasmIrAttributesKt.class, "instanceCheckForExternalClass", "getInstanceCheckForExternalClass(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(WasmIrAttributesKt.class, "getJsClassForExternalClass", "getGetJsClassForExternalClass(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(WasmIrAttributesKt.class, "toCatchThrowableOrJsException", "getToCatchThrowableOrJsException(Lorg/jetbrains/kotlin/ir/expressions/IrCatch;)Z", 1)};
        $$delegatedProperties = kPropertyArr;
        topLevelFunctionForNestedExternal$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[0]);
        jsFunctionForExternalAdapterFunction$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[1]);
        getInstanceFunctionForExternalObject$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[2]);
        instanceCheckForExternalClass$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[3]);
        getJsClassForExternalClass$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[4]);
        toCatchThrowableOrJsException$delegate = IrAttributeKt.irFlag(true).provideDelegate((Object) null, kPropertyArr[5]);
    }

    public static final IrSimpleFunction getGetInstanceFunctionForExternalObject(IrClass irClass) {
        irClass.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irClass, getInstanceFunctionForExternalObject$delegate);
    }

    public static final IrSimpleFunction getGetJsClassForExternalClass(IrClass irClass) {
        irClass.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irClass, getJsClassForExternalClass$delegate);
    }

    public static final IrSimpleFunction getInstanceCheckForExternalClass(IrClass irClass) {
        irClass.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irClass, instanceCheckForExternalClass$delegate);
    }

    public static final IrSimpleFunction getJsFunctionForExternalAdapterFunction(IrFunction irFunction) {
        irFunction.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irFunction, jsFunctionForExternalAdapterFunction$delegate);
    }

    public static final boolean getToCatchThrowableOrJsException(IrCatch irCatch) {
        irCatch.getClass();
        return toCatchThrowableOrJsException$delegate.get(irCatch);
    }

    public static final IrSimpleFunction getTopLevelFunctionForNestedExternal(IrFunction irFunction) {
        irFunction.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irFunction, topLevelFunctionForNestedExternal$delegate);
    }

    public static final void setGetInstanceFunctionForExternalObject(IrClass irClass, IrSimpleFunction irSimpleFunction) {
        irClass.getClass();
        IrAttributeKt.set(irClass, getInstanceFunctionForExternalObject$delegate, irSimpleFunction);
    }

    public static final void setGetJsClassForExternalClass(IrClass irClass, IrSimpleFunction irSimpleFunction) {
        irClass.getClass();
        IrAttributeKt.set(irClass, getJsClassForExternalClass$delegate, irSimpleFunction);
    }

    public static final void setInstanceCheckForExternalClass(IrClass irClass, IrSimpleFunction irSimpleFunction) {
        irClass.getClass();
        IrAttributeKt.set(irClass, instanceCheckForExternalClass$delegate, irSimpleFunction);
    }

    public static final void setJsFunctionForExternalAdapterFunction(IrFunction irFunction, IrSimpleFunction irSimpleFunction) {
        irFunction.getClass();
        IrAttributeKt.set(irFunction, jsFunctionForExternalAdapterFunction$delegate, irSimpleFunction);
    }

    public static final void setToCatchThrowableOrJsException(IrCatch irCatch, boolean z) {
        irCatch.getClass();
        toCatchThrowableOrJsException$delegate.set(irCatch, z);
    }

    public static final void setTopLevelFunctionForNestedExternal(IrFunction irFunction, IrSimpleFunction irSimpleFunction) {
        irFunction.getClass();
        IrAttributeKt.set(irFunction, topLevelFunctionForNestedExternal$delegate, irSimpleFunction);
    }
}
