package org.jetbrains.kotlin.backend.common;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006\"?\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t*\u00020\f2\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\b\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\"3\u0010\u0013\u001a\u0004\u0018\u00010\u0012*\u00020\f2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00128F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\b\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\"3\u0010\u001a\u001a\u0004\u0018\u00010\u0019*\u00020\u00192\b\u0010\u0000\u001a\u0004\u0018\u00010\u00198F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\b\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e\"3\u0010 \u001a\u0004\u0018\u00010\u0019*\u00020\u00192\b\u0010\u0000\u001a\u0004\u0018\u00010\u00198F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\b\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001e\"3\u0010$\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\b\u001a\u0004\b%\u0010\u0004\"\u0004\b&\u0010\u0006\"3\u0010)\u001a\u0004\u0018\u00010(*\u00020(2\b\u0010\u0000\u001a\u0004\u0018\u00010(8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010\b\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-\"3\u00100\u001a\u0004\u0018\u00010/*\u00020\u00192\b\u0010\u0000\u001a\u0004\u0018\u00010/8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010\b\u001a\u0004\b1\u00102\"\u0004\b3\u00104\"/\u00107\u001a\u000206*\u0002082\u0006\u0010\u0000\u001a\u0002068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<\"/\u0010?\u001a\u000206*\u00020@2\u0006\u0010\u0000\u001a\u0002068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010>\u001a\u0004\bA\u0010B\"\u0004\bC\u0010D¨\u0006F"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "defaultArgumentsDispatchFunction", "getDefaultArgumentsDispatchFunction", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "setDefaultArgumentsDispatchFunction", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)V", "defaultArgumentsDispatchFunction$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "capturedFields", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getCapturedFields", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/Collection;", "setCapturedFields", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Ljava/util/Collection;)V", "capturedFields$delegate", "", "functionReferenceReflectedName", "getFunctionReferenceReflectedName", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/lang/String;", "setFunctionReferenceReflectedName", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Ljava/lang/String;)V", "functionReferenceReflectedName$delegate", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "functionWithContinuations", "getFunctionWithContinuations", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "setFunctionWithContinuations", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "functionWithContinuations$delegate", "suspendFunction", "getSuspendFunction", "setSuspendFunction", "suspendFunction$delegate", "defaultArgumentsOriginalFunction", "getDefaultArgumentsOriginalFunction", "setDefaultArgumentsOriginalFunction", "defaultArgumentsOriginalFunction$delegate", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "capturedConstructor", "getCapturedConstructor", "(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "setCapturedConstructor", "(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)V", "capturedConstructor$delegate", "Lorg/jetbrains/kotlin/name/Name;", "customNameInReflection", "getCustomNameInReflection", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/name/Name;", "setCustomNameInReflection", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/name/Name;)V", "customNameInReflection$delegate", "", "implicitInvoke", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "getImplicitInvoke", "(Lorg/jetbrains/kotlin/ir/expressions/IrCall;)Z", "setImplicitInvoke", "(Lorg/jetbrains/kotlin/ir/expressions/IrCall;Z)V", "implicitInvoke$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute$Flag;", "fileForTopLevelPluginDeclarations", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "getFileForTopLevelPluginDeclarations", "(Lorg/jetbrains/kotlin/ir/declarations/IrFile;)Z", "setFileForTopLevelPluginDeclarations", "(Lorg/jetbrains/kotlin/ir/declarations/IrFile;Z)V", "fileForTopLevelPluginDeclarations$delegate", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class CommonIrAttributesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute capturedConstructor$delegate;
    private static final IrAttribute capturedFields$delegate;
    private static final IrAttribute customNameInReflection$delegate;
    private static final IrAttribute defaultArgumentsDispatchFunction$delegate;
    private static final IrAttribute defaultArgumentsOriginalFunction$delegate;
    private static final IrAttribute.Flag fileForTopLevelPluginDeclarations$delegate;
    private static final IrAttribute functionReferenceReflectedName$delegate;
    private static final IrAttribute functionWithContinuations$delegate;
    private static final IrAttribute.Flag implicitInvoke$delegate;
    private static final IrAttribute suspendFunction$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(CommonIrAttributesKt.class, "defaultArgumentsDispatchFunction", "getDefaultArgumentsDispatchFunction(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", 1), new MutablePropertyReference1Impl<>(CommonIrAttributesKt.class, "capturedFields", "getCapturedFields(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/Collection;", 1), new MutablePropertyReference1Impl<>(CommonIrAttributesKt.class, "functionReferenceReflectedName", "getFunctionReferenceReflectedName(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/lang/String;", 1), new MutablePropertyReference1Impl<>(CommonIrAttributesKt.class, "functionWithContinuations", "getFunctionWithContinuations(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(CommonIrAttributesKt.class, "suspendFunction", "getSuspendFunction(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(CommonIrAttributesKt.class, "defaultArgumentsOriginalFunction", "getDefaultArgumentsOriginalFunction(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", 1), new MutablePropertyReference1Impl<>(CommonIrAttributesKt.class, "capturedConstructor", "getCapturedConstructor(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", 1), new MutablePropertyReference1Impl<>(CommonIrAttributesKt.class, "customNameInReflection", "getCustomNameInReflection(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/name/Name;", 1), new MutablePropertyReference1Impl<>(CommonIrAttributesKt.class, "implicitInvoke", "getImplicitInvoke(Lorg/jetbrains/kotlin/ir/expressions/IrCall;)Z", 1), new MutablePropertyReference1Impl<>(CommonIrAttributesKt.class, "fileForTopLevelPluginDeclarations", "getFileForTopLevelPluginDeclarations(Lorg/jetbrains/kotlin/ir/declarations/IrFile;)Z", 1)};
        $$delegatedProperties = kPropertyArr;
        defaultArgumentsDispatchFunction$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[0]);
        capturedFields$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[1]);
        functionReferenceReflectedName$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[2]);
        functionWithContinuations$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[3]);
        suspendFunction$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[4]);
        defaultArgumentsOriginalFunction$delegate = IrAttributeKt.irAttribute(true).provideDelegate((Object) null, kPropertyArr[5]);
        capturedConstructor$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[6]);
        customNameInReflection$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[7]);
        implicitInvoke$delegate = IrAttributeKt.irFlag(false).provideDelegate((Object) null, kPropertyArr[8]);
        fileForTopLevelPluginDeclarations$delegate = IrAttributeKt.irFlag(false).provideDelegate((Object) null, kPropertyArr[9]);
    }

    public static final IrConstructor getCapturedConstructor(IrConstructor irConstructor) {
        irConstructor.getClass();
        return (IrConstructor) IrAttributeKt.get(irConstructor, capturedConstructor$delegate);
    }

    public static final Collection<IrField> getCapturedFields(IrClass irClass) {
        irClass.getClass();
        return (Collection) IrAttributeKt.get(irClass, capturedFields$delegate);
    }

    public static final Name getCustomNameInReflection(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        return (Name) IrAttributeKt.get(irSimpleFunction, customNameInReflection$delegate);
    }

    public static final IrFunction getDefaultArgumentsDispatchFunction(IrFunction irFunction) {
        irFunction.getClass();
        return (IrFunction) IrAttributeKt.get(irFunction, defaultArgumentsDispatchFunction$delegate);
    }

    public static final IrFunction getDefaultArgumentsOriginalFunction(IrFunction irFunction) {
        irFunction.getClass();
        return (IrFunction) IrAttributeKt.get(irFunction, defaultArgumentsOriginalFunction$delegate);
    }

    public static final boolean getFileForTopLevelPluginDeclarations(IrFile irFile) {
        irFile.getClass();
        return fileForTopLevelPluginDeclarations$delegate.get(irFile);
    }

    public static final String getFunctionReferenceReflectedName(IrClass irClass) {
        irClass.getClass();
        return (String) IrAttributeKt.get(irClass, functionReferenceReflectedName$delegate);
    }

    public static final IrSimpleFunction getFunctionWithContinuations(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irSimpleFunction, functionWithContinuations$delegate);
    }

    public static final boolean getImplicitInvoke(IrCall irCall) {
        irCall.getClass();
        return implicitInvoke$delegate.get(irCall);
    }

    public static final IrSimpleFunction getSuspendFunction(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irSimpleFunction, suspendFunction$delegate);
    }

    public static final void setCapturedConstructor(IrConstructor irConstructor, IrConstructor irConstructor2) {
        irConstructor.getClass();
        IrAttributeKt.set(irConstructor, capturedConstructor$delegate, irConstructor2);
    }

    public static final void setCapturedFields(IrClass irClass, Collection<? extends IrField> collection) {
        irClass.getClass();
        IrAttributeKt.set(irClass, capturedFields$delegate, collection);
    }

    public static final void setCustomNameInReflection(IrSimpleFunction irSimpleFunction, Name name) {
        irSimpleFunction.getClass();
        IrAttributeKt.set(irSimpleFunction, customNameInReflection$delegate, name);
    }

    public static final void setDefaultArgumentsDispatchFunction(IrFunction irFunction, IrFunction irFunction2) {
        irFunction.getClass();
        IrAttributeKt.set(irFunction, defaultArgumentsDispatchFunction$delegate, irFunction2);
    }

    public static final void setDefaultArgumentsOriginalFunction(IrFunction irFunction, IrFunction irFunction2) {
        irFunction.getClass();
        IrAttributeKt.set(irFunction, defaultArgumentsOriginalFunction$delegate, irFunction2);
    }

    public static final void setFileForTopLevelPluginDeclarations(IrFile irFile, boolean z) {
        irFile.getClass();
        fileForTopLevelPluginDeclarations$delegate.set(irFile, z);
    }

    public static final void setFunctionReferenceReflectedName(IrClass irClass, String str) {
        irClass.getClass();
        IrAttributeKt.set(irClass, functionReferenceReflectedName$delegate, str);
    }

    public static final void setFunctionWithContinuations(IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2) {
        irSimpleFunction.getClass();
        IrAttributeKt.set(irSimpleFunction, functionWithContinuations$delegate, irSimpleFunction2);
    }

    public static final void setImplicitInvoke(IrCall irCall, boolean z) {
        irCall.getClass();
        implicitInvoke$delegate.set(irCall, z);
    }

    public static final void setSuspendFunction(IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2) {
        irSimpleFunction.getClass();
        IrAttributeKt.set(irSimpleFunction, suspendFunction$delegate, irSimpleFunction2);
    }
}
