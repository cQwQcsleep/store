package org.jetbrains.kotlin.contracts.model.functors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.AbstractFunctor;
import org.jetbrains.kotlin.contracts.model.Computation;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESTypeSubstitution;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.SimpleEffect;
import org.jetbrains.kotlin.contracts.model.structure.ESCalls;
import org.jetbrains.kotlin.contracts.model.structure.ESReturns;
import org.jetbrains.kotlin.contracts.model.structure.ESVariable;
import org.jetbrains.kotlin.contracts.model.structure.ValuesKt;
import org.jetbrains.kotlin.contracts.model.visitors.Reducer;
import org.jetbrains.kotlin.contracts.model.visitors.Substitutor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.resolve.scopes.receivers.ImplicitClassReceiver;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ,\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\u0002J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\f\u0010\u0016\u001a\u00020\u0011*\u00020\u0017H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/functors/SubstitutingFunctor;", "Lorg/jetbrains/kotlin/contracts/model/AbstractFunctor;", "basicEffects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "ownerFunction", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;)V", "doInvocation", "arguments", "Lorg/jetbrains/kotlin/contracts/model/Computation;", "typeSubstitution", "Lorg/jetbrains/kotlin/contracts/model/ESTypeSubstitution;", "reducer", "Lorg/jetbrains/kotlin/contracts/model/visitors/Reducer;", "computeParameters", "Lorg/jetbrains/kotlin/contracts/model/structure/ESVariable;", "combine", "effect", "Lorg/jetbrains/kotlin/contracts/model/SimpleEffect;", "substitutedCondition", "toESVariable", "Lorg/jetbrains/kotlin/descriptors/ValueDescriptor;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SubstitutingFunctor extends AbstractFunctor {
    private final List<ESEffect> basicEffects;
    private final FunctionDescriptor ownerFunction;

    /* JADX WARN: Multi-variable type inference failed */
    public SubstitutingFunctor(List<? extends ESEffect> list, FunctionDescriptor functionDescriptor) {
        list.getClass();
        functionDescriptor.getClass();
        this.basicEffects = list;
        this.ownerFunction = functionDescriptor;
    }

    private final ESEffect combine(SimpleEffect effect, ESEffect substitutedCondition) {
        if (!(substitutedCondition instanceof ConditionalEffect)) {
            return null;
        }
        ConditionalEffect conditionalEffect = (ConditionalEffect) substitutedCondition;
        SimpleEffect simpleEffect = conditionalEffect.getSimpleEffect();
        if (!(simpleEffect instanceof ESReturns)) {
            return substitutedCondition;
        }
        ESReturns eSReturns = (ESReturns) simpleEffect;
        if (ValuesKt.isWildcard(eSReturns.getValue())) {
            return substitutedCondition;
        }
        if (ValuesKt.isTrue(eSReturns.getValue())) {
            return new ConditionalEffect(conditionalEffect.getCondition(), effect);
        }
        return null;
    }

    private final List<ESVariable> computeParameters(List<? extends Computation> arguments) {
        ValueDescriptor descriptor;
        ClassDescriptor classDescriptor;
        ParameterDescriptor dispatchReceiverParameter = this.ownerFunction.getDispatchReceiverParameter();
        ESVariable eSVariable = dispatchReceiverParameter != null ? toESVariable(dispatchReceiverParameter) : null;
        ParameterDescriptor extensionReceiverParameter = this.ownerFunction.getExtensionReceiverParameter();
        ESVariable eSVariable2 = extensionReceiverParameter != null ? toESVariable(extensionReceiverParameter) : null;
        List listListOfNotNull = CollectionsKt.listOfNotNull(new ESVariable[]{eSVariable, eSVariable2});
        List<ValueParameterDescriptor> valueParameters = this.ownerFunction.getValueParameters();
        valueParameters.getClass();
        List<ValueParameterDescriptor> list = valueParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ValueDescriptor valueDescriptor = (ValueParameterDescriptor) it.next();
            valueDescriptor.getClass();
            arrayList.add(toESVariable(valueDescriptor));
        }
        List<ESVariable> listPlus = CollectionsKt.plus(listListOfNotNull, arrayList);
        if (listPlus.size() == arguments.size()) {
            return listPlus;
        }
        if (eSVariable == null || (descriptor = eSVariable.getDescriptor()) == null) {
            computeParameters$fail(arguments, listPlus);
            wq6.a();
            return null;
        }
        if (descriptor instanceof ReceiverParameterDescriptor) {
            ImplicitClassReceiver value = ((ReceiverParameterDescriptor) descriptor).getValue();
            ImplicitClassReceiver implicitClassReceiver = value instanceof ImplicitClassReceiver ? value : null;
            if (implicitClassReceiver == null || (classDescriptor = implicitClassReceiver.getClassDescriptor()) == null) {
                computeParameters$fail(arguments, listPlus);
                wq6.a();
                return null;
            }
            if (classDescriptor.getKind() == ClassKind.OBJECT) {
                List listCreateListBuilder = CollectionsKt.createListBuilder();
                if (eSVariable2 != null) {
                    listCreateListBuilder.add(eSVariable2);
                }
                listCreateListBuilder.addAll(arrayList);
                List<ESVariable> listBuild = CollectionsKt.build(listCreateListBuilder);
                if (listBuild.size() == arguments.size()) {
                    return listBuild;
                }
                computeParameters$fail(arguments, listPlus);
                wq6.a();
                return null;
            }
        }
        computeParameters$fail(arguments, listPlus);
        wq6.a();
        return null;
    }

    private static final Void computeParameters$fail(List<? extends Computation> list, List<? extends ESVariable> list2) {
        throw new IllegalStateException(("Arguments and parameters size mismatch: arguments.size = " + list.size() + ", parameters.size = " + list2.size()).toString());
    }

    private final ESVariable toESVariable(ValueDescriptor valueDescriptor) {
        return new ESVariable(valueDescriptor);
    }

    @Override // org.jetbrains.kotlin.contracts.model.AbstractFunctor
    public List<ESEffect> doInvocation(List<? extends Computation> arguments, ESTypeSubstitution typeSubstitution, Reducer reducer) {
        List<ESEffect> effects;
        arguments.getClass();
        typeSubstitution.getClass();
        reducer.getClass();
        if (this.basicEffects.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        Map map = MapsKt.toMap(CollectionsKt.zip(computeParameters(arguments), arguments));
        Substitutor substitutor = new Substitutor(map, typeSubstitution, reducer);
        ArrayList arrayList = new ArrayList();
        for (ESEffect eSEffect : this.basicEffects) {
            if (eSEffect instanceof ConditionalEffect) {
                ConditionalEffect conditionalEffect = (ConditionalEffect) eSEffect;
                Computation computation = (Computation) conditionalEffect.getCondition().accept(substitutor);
                if (computation != null && (effects = computation.getEffects()) != null) {
                    Iterator<T> it = effects.iterator();
                    while (it.hasNext()) {
                        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(arrayList, combine(conditionalEffect.getSimpleEffect(), (ESEffect) it.next()));
                    }
                }
            } else if (eSEffect instanceof ESCalls) {
                ESCalls eSCalls = (ESCalls) eSEffect;
                Object obj = map.get(eSCalls.getCallable());
                ESValue eSValue = obj instanceof ESValue ? (ESValue) obj : null;
                if (eSValue != null) {
                    arrayList.add(new ESCalls(eSValue, eSCalls.getKind()));
                }
            } else {
                arrayList.add(eSEffect);
            }
        }
        return arrayList;
    }
}
