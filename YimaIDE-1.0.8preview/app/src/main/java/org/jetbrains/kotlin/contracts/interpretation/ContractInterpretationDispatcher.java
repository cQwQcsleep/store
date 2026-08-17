package org.jetbrains.kotlin.contracts.interpretation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.BooleanExpression;
import org.jetbrains.kotlin.contracts.description.ConditionalEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.ContractDescription;
import org.jetbrains.kotlin.contracts.description.EffectDeclaration;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.VariableReference;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESExpression;
import org.jetbrains.kotlin.contracts.model.Functor;
import org.jetbrains.kotlin.contracts.model.functors.SubstitutingFunctor;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.contracts.model.structure.ESVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J\u0017\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001aJ\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0000¢\u0006\u0002\b\u001fJ\u0017\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#H\u0000¢\u0006\u0002\b$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/contracts/interpretation/ContractInterpretationDispatcher;", Argument.Delimiters.none, "<init>", "()V", "constantsInterpreter", "Lorg/jetbrains/kotlin/contracts/interpretation/ConstantValuesInterpreter;", "conditionInterpreter", "Lorg/jetbrains/kotlin/contracts/interpretation/ConditionInterpreter;", "conditionalEffectInterpreter", "Lorg/jetbrains/kotlin/contracts/interpretation/ConditionalEffectInterpreter;", "effectsInterpreters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/interpretation/EffectDeclarationInterpreter;", "convertContractDescriptorToFunctor", "Lorg/jetbrains/kotlin/contracts/model/Functor;", "contractDescription", "Lorg/jetbrains/kotlin/contracts/description/ContractDescription;", "interpretEffect", "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "effectDeclaration", "Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;", "interpretEffect$org_jetbrains_kotlin_resolution", "interpretConstant", "Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "constantReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", "interpretConstant$org_jetbrains_kotlin_resolution", "interpretCondition", "Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "booleanExpression", "Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "interpretCondition$org_jetbrains_kotlin_resolution", "interpretVariable", "Lorg/jetbrains/kotlin/contracts/model/structure/ESVariable;", "variableReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;", "interpretVariable$org_jetbrains_kotlin_resolution", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContractInterpretationDispatcher {
    private final ConstantValuesInterpreter constantsInterpreter = new ConstantValuesInterpreter();
    private final ConditionInterpreter conditionInterpreter = new ConditionInterpreter(this);
    private final ConditionalEffectInterpreter conditionalEffectInterpreter = new ConditionalEffectInterpreter(this);
    private final List<EffectDeclarationInterpreter> effectsInterpreters = CollectionsKt.listOf(new EffectDeclarationInterpreter[]{new ReturnsEffectInterpreter(this), new CallsEffectInterpreter(this)});

    public final Functor convertContractDescriptorToFunctor(ContractDescription contractDescription) {
        contractDescription.getClass();
        ArrayList arrayList = new ArrayList();
        for (EffectDeclaration effectDeclaration : contractDescription.getEffects()) {
            if (effectDeclaration instanceof ConditionalEffectDeclaration) {
                ConditionalEffect conditionalEffectInterpret = this.conditionalEffectInterpreter.interpret((ConditionalEffectDeclaration) effectDeclaration);
                if (conditionalEffectInterpret == null) {
                    return null;
                }
                arrayList.add(conditionalEffectInterpret);
            } else {
                List<EffectDeclarationInterpreter> list = this.effectsInterpreters;
                ArrayList arrayList2 = new ArrayList();
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    ESEffect eSEffectTryInterpret = ((EffectDeclarationInterpreter) it.next()).tryInterpret(effectDeclaration);
                    if (eSEffectTryInterpret != null) {
                        arrayList2.add(eSEffectTryInterpret);
                    }
                }
                int size = arrayList2.size();
                if (size == 0) {
                    continue;
                } else {
                    if (size != 1) {
                        return null;
                    }
                    arrayList.add(arrayList2.get(0));
                }
            }
        }
        return new SubstitutingFunctor(arrayList, contractDescription.getOwnerFunction());
    }

    public final ESExpression interpretCondition$org_jetbrains_kotlin_resolution(BooleanExpression booleanExpression) {
        booleanExpression.getClass();
        return (ESExpression) booleanExpression.accept(this.conditionInterpreter, Unit.INSTANCE);
    }

    public final ESConstant interpretConstant$org_jetbrains_kotlin_resolution(ConstantReference constantReference) {
        constantReference.getClass();
        return this.constantsInterpreter.interpretConstant(constantReference);
    }

    public final ESEffect interpretEffect$org_jetbrains_kotlin_resolution(EffectDeclaration effectDeclaration) {
        effectDeclaration.getClass();
        List<EffectDeclarationInterpreter> list = this.effectsInterpreters;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ESEffect eSEffectTryInterpret = ((EffectDeclarationInterpreter) it.next()).tryInterpret(effectDeclaration);
            if (eSEffectTryInterpret != null) {
                arrayList.add(eSEffectTryInterpret);
            }
        }
        return (ESEffect) CollectionsKt.singleOrNull(arrayList);
    }

    public final ESVariable interpretVariable$org_jetbrains_kotlin_resolution(VariableReference variableReference) {
        variableReference.getClass();
        return new ESVariable(variableReference.getDescriptor());
    }
}
