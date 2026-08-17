package org.jetbrains.kotlin.backend.jvm;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.util.DumpIrTreeKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006\"?\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t*\u00020\f2\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\b\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\"3\u0010\u0013\u001a\u0004\u0018\u00010\u0012*\u00020\u00142\b\u0010\u0000\u001a\u0004\u0018\u00010\u00128F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\b\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\"?\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a*\u00020\u001d2\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\"\u0010\b\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!\"?\u0010#\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a*\u00020\u001d2\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010\b\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!\"8\u0010(\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a*\u00020\u001d2\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b)\u0010\u001f\"\u0004\b*\u0010!¨\u0006+"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "originalConstructorOfThisMfvcConstructorReplacement", "getOriginalConstructorOfThisMfvcConstructorReplacement", "(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "setOriginalConstructorOfThisMfvcConstructorReplacement", "(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)V", "originalConstructorOfThisMfvcConstructorReplacement$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "mfvcFieldsToRemove", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getMfvcFieldsToRemove", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/Set;", "setMfvcFieldsToRemove", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Ljava/util/Set;)V", "mfvcFieldsToRemove$delegate", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "oldMfvcDefaultArgument", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "getOldMfvcDefaultArgument", "(Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;)Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "setOldMfvcDefaultArgument", "(Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)V", "oldMfvcDefaultArgument$delegate", "", "Lorg/jetbrains/kotlin/backend/jvm/MemoizedMultiFieldValueClassReplacements$RemappedParameter;", "parameterTemplateStructureOfThisOldMfvcBidingFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getParameterTemplateStructureOfThisOldMfvcBidingFunction", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Ljava/util/List;", "setParameterTemplateStructureOfThisOldMfvcBidingFunction", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Ljava/util/List;)V", "parameterTemplateStructureOfThisOldMfvcBidingFunction$delegate", "_parameterTemplateStructureOfThisNewMfvcBidingFunction", "get_parameterTemplateStructureOfThisNewMfvcBidingFunction", "set_parameterTemplateStructureOfThisNewMfvcBidingFunction", "_parameterTemplateStructureOfThisNewMfvcBidingFunction$delegate", "value", "parameterTemplateStructureOfThisNewMfvcBidingFunction", "getParameterTemplateStructureOfThisNewMfvcBidingFunction", "setParameterTemplateStructureOfThisNewMfvcBidingFunction", "org.jetbrains.kotlin:backend.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class MemoizedMultiFieldValueClassReplacementsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute _parameterTemplateStructureOfThisNewMfvcBidingFunction$delegate;
    private static final IrAttribute mfvcFieldsToRemove$delegate;
    private static final IrAttribute oldMfvcDefaultArgument$delegate;
    private static final IrAttribute originalConstructorOfThisMfvcConstructorReplacement$delegate;
    private static final IrAttribute parameterTemplateStructureOfThisOldMfvcBidingFunction$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(MemoizedMultiFieldValueClassReplacementsKt.class, "originalConstructorOfThisMfvcConstructorReplacement", "getOriginalConstructorOfThisMfvcConstructorReplacement(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", 1), new MutablePropertyReference1Impl<>(MemoizedMultiFieldValueClassReplacementsKt.class, "mfvcFieldsToRemove", "getMfvcFieldsToRemove(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/Set;", 1), new MutablePropertyReference1Impl<>(MemoizedMultiFieldValueClassReplacementsKt.class, "oldMfvcDefaultArgument", "getOldMfvcDefaultArgument(Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;)Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", 1), new MutablePropertyReference1Impl<>(MemoizedMultiFieldValueClassReplacementsKt.class, "parameterTemplateStructureOfThisOldMfvcBidingFunction", "getParameterTemplateStructureOfThisOldMfvcBidingFunction(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Ljava/util/List;", 1), new MutablePropertyReference1Impl<>(MemoizedMultiFieldValueClassReplacementsKt.class, "_parameterTemplateStructureOfThisNewMfvcBidingFunction", "get_parameterTemplateStructureOfThisNewMfvcBidingFunction(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Ljava/util/List;", 1)};
        $$delegatedProperties = kPropertyArr;
        originalConstructorOfThisMfvcConstructorReplacement$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[0]);
        mfvcFieldsToRemove$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[1]);
        oldMfvcDefaultArgument$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[2]);
        parameterTemplateStructureOfThisOldMfvcBidingFunction$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[3]);
        _parameterTemplateStructureOfThisNewMfvcBidingFunction$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[4]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<IrField> getMfvcFieldsToRemove(IrClass irClass) {
        return (Set) IrAttributeKt.get(irClass, mfvcFieldsToRemove$delegate);
    }

    public static final IrExpression getOldMfvcDefaultArgument(IrValueParameter irValueParameter) {
        irValueParameter.getClass();
        return (IrExpression) IrAttributeKt.get(irValueParameter, oldMfvcDefaultArgument$delegate);
    }

    public static final IrConstructor getOriginalConstructorOfThisMfvcConstructorReplacement(IrConstructor irConstructor) {
        irConstructor.getClass();
        return (IrConstructor) IrAttributeKt.get(irConstructor, originalConstructorOfThisMfvcConstructorReplacement$delegate);
    }

    public static final List<MemoizedMultiFieldValueClassReplacements.RemappedParameter> getParameterTemplateStructureOfThisNewMfvcBidingFunction(IrFunction irFunction) {
        irFunction.getClass();
        return get_parameterTemplateStructureOfThisNewMfvcBidingFunction(irFunction);
    }

    public static final List<MemoizedMultiFieldValueClassReplacements.RemappedParameter> getParameterTemplateStructureOfThisOldMfvcBidingFunction(IrFunction irFunction) {
        irFunction.getClass();
        return (List) IrAttributeKt.get(irFunction, parameterTemplateStructureOfThisOldMfvcBidingFunction$delegate);
    }

    private static final List<MemoizedMultiFieldValueClassReplacements.RemappedParameter> get_parameterTemplateStructureOfThisNewMfvcBidingFunction(IrFunction irFunction) {
        return (List) IrAttributeKt.get(irFunction, _parameterTemplateStructureOfThisNewMfvcBidingFunction$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setMfvcFieldsToRemove(IrClass irClass, Set<IrField> set) {
        IrAttributeKt.set(irClass, mfvcFieldsToRemove$delegate, set);
    }

    public static final void setOldMfvcDefaultArgument(IrValueParameter irValueParameter, IrExpression irExpression) {
        irValueParameter.getClass();
        IrAttributeKt.set(irValueParameter, oldMfvcDefaultArgument$delegate, irExpression);
    }

    public static final void setOriginalConstructorOfThisMfvcConstructorReplacement(IrConstructor irConstructor, IrConstructor irConstructor2) {
        irConstructor.getClass();
        IrAttributeKt.set(irConstructor, originalConstructorOfThisMfvcConstructorReplacement$delegate, irConstructor2);
    }

    public static final void setParameterTemplateStructureOfThisNewMfvcBidingFunction(IrFunction irFunction, List<? extends MemoizedMultiFieldValueClassReplacements.RemappedParameter> list) {
        irFunction.getClass();
        if (list != null) {
            int size = irFunction.getParameters().size();
            Iterator<T> it = list.iterator();
            int size2 = 0;
            while (it.hasNext()) {
                size2 += ((MemoizedMultiFieldValueClassReplacements.RemappedParameter) it.next()).getParameters().size();
            }
            if (size != size2) {
                StringBuilder sb = new StringBuilder("Illegal structure ");
                sb.append(list);
                ywd.a(sb, " for function ", DumpIrTreeKt.dump$default(irFunction, (DumpIrTreeOptions) null, 1, (Object) null));
                return;
            }
        }
        set_parameterTemplateStructureOfThisNewMfvcBidingFunction(irFunction, list);
    }

    public static final void setParameterTemplateStructureOfThisOldMfvcBidingFunction(IrFunction irFunction, List<? extends MemoizedMultiFieldValueClassReplacements.RemappedParameter> list) {
        irFunction.getClass();
        IrAttributeKt.set(irFunction, parameterTemplateStructureOfThisOldMfvcBidingFunction$delegate, list);
    }

    private static final void set_parameterTemplateStructureOfThisNewMfvcBidingFunction(IrFunction irFunction, List<? extends MemoizedMultiFieldValueClassReplacements.RemappedParameter> list) {
        IrAttributeKt.set(irFunction, _parameterTemplateStructureOfThisNewMfvcBidingFunction$delegate, list);
    }
}
