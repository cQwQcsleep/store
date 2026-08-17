package org.jetbrains.kotlin.contracts.model.functors;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.AbstractFunctor;
import org.jetbrains.kotlin.contracts.model.Computation;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESTypeSubstitution;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.contracts.model.structure.ESConstants;
import org.jetbrains.kotlin.contracts.model.structure.ESEqual;
import org.jetbrains.kotlin.contracts.model.structure.ESReturns;
import org.jetbrains.kotlin.contracts.model.structure.TypesKt;
import org.jetbrains.kotlin.contracts.model.structure.ValuesKt;
import org.jetbrains.kotlin.contracts.model.visitors.Reducer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J,\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000bJ\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J \u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u001cH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/functors/EqualsFunctor;", "Lorg/jetbrains/kotlin/contracts/model/AbstractFunctor;", "isNegated", Argument.Delimiters.none, "<init>", "(Z)V", "()Z", "doInvocation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "arguments", "Lorg/jetbrains/kotlin/contracts/model/Computation;", "typeSubstitution", "Lorg/jetbrains/kotlin/contracts/model/ESTypeSubstitution;", "reducer", "Lorg/jetbrains/kotlin/contracts/model/visitors/Reducer;", "invokeWithArguments", "left", "right", "equateCallAndConstant", K2JsArgumentConstants.CALL, "constant", "Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "isSafeToProduceFalse", "leftCall", "leftConstant", "rightConstant", "equateValues", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EqualsFunctor extends AbstractFunctor {
    private final boolean isNegated;

    public EqualsFunctor(boolean z) {
        this.isNegated = z;
    }

    private final List<ESEffect> equateCallAndConstant(Computation call, ESConstant constant) {
        ArrayList arrayList = new ArrayList();
        for (ESEffect eSEffect : call.getEffects()) {
            if (eSEffect instanceof ConditionalEffect) {
                ConditionalEffect conditionalEffect = (ConditionalEffect) eSEffect;
                if ((conditionalEffect.getSimpleEffect() instanceof ESReturns) && !ValuesKt.isWildcard(((ESReturns) conditionalEffect.getSimpleEffect()).getValue())) {
                    if (Intrinsics.areEqual(((ESReturns) conditionalEffect.getSimpleEffect()).getValue(), constant)) {
                        arrayList.add(new ConditionalEffect(conditionalEffect.getCondition(), new ESReturns(ESConstants.INSTANCE.booleanValue(!this.isNegated))));
                    }
                    if (!Intrinsics.areEqual(((ESReturns) conditionalEffect.getSimpleEffect()).getValue(), constant) && (((ESReturns) conditionalEffect.getSimpleEffect()).getValue() instanceof ESConstant) && isSafeToProduceFalse(call, (ESConstant) ((ESReturns) conditionalEffect.getSimpleEffect()).getValue(), constant)) {
                        arrayList.add(new ConditionalEffect(conditionalEffect.getCondition(), new ESReturns(ESConstants.INSTANCE.booleanValue(this.isNegated))));
                    }
                }
            }
            arrayList.add(eSEffect);
        }
        return arrayList;
    }

    private final List<ESEffect> equateValues(ESValue left, ESValue right) {
        ESEqual eSEqual = new ESEqual(left, right, this.isNegated);
        ESConstants eSConstants = ESConstants.INSTANCE;
        return CollectionsKt.listOf(new ConditionalEffect[]{new ConditionalEffect(eSEqual, new ESReturns(eSConstants.getTrueValue())), new ConditionalEffect(new ESEqual(left, right, !this.isNegated), new ESReturns(eSConstants.getFalseValue()))});
    }

    private final boolean isSafeToProduceFalse(Computation leftCall, ESConstant leftConstant, ESConstant rightConstant) {
        if (TypesKt.isBoolean(rightConstant.getType()) && TypesKt.isBoolean(leftCall.getType())) {
            return true;
        }
        return leftConstant.isNullConstant() && rightConstant.isNullConstant();
    }

    @Override // org.jetbrains.kotlin.contracts.model.AbstractFunctor
    public List<ESEffect> doInvocation(List<? extends Computation> arguments, ESTypeSubstitution typeSubstitution, Reducer reducer) {
        arguments.getClass();
        typeSubstitution.getClass();
        reducer.getClass();
        arguments.size();
        return arguments.size() != 2 ? CollectionsKt.emptyList() : invokeWithArguments(arguments.get(0), arguments.get(1));
    }

    public final List<ESEffect> invokeWithArguments(Computation left, Computation right) {
        left.getClass();
        right.getClass();
        if ((left instanceof ESValue) && (right instanceof ESValue)) {
            return equateValues((ESValue) left, (ESValue) right);
        }
        if (left instanceof ESConstant) {
            return equateCallAndConstant(right, (ESConstant) left);
        }
        return right instanceof ESConstant ? equateCallAndConstant(left, (ESConstant) right) : CollectionsKt.emptyList();
    }

    /* JADX INFO: renamed from: isNegated, reason: from getter */
    public final boolean getIsNegated() {
        return this.isNegated;
    }
}
