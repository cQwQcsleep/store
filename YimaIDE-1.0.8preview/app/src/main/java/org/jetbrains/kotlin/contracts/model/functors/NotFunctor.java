package org.jetbrains.kotlin.contracts.model.functors;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.SimpleEffect;
import org.jetbrains.kotlin.contracts.model.structure.ESConstants;
import org.jetbrains.kotlin.contracts.model.structure.ESReturns;
import org.jetbrains.kotlin.contracts.model.structure.ValuesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0014¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/functors/NotFunctor;", "Lorg/jetbrains/kotlin/contracts/model/functors/AbstractUnaryFunctor;", "<init>", "()V", "invokeWithReturningEffects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ConditionalEffect;", "list", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NotFunctor extends AbstractUnaryFunctor {
    @Override // org.jetbrains.kotlin.contracts.model.functors.AbstractUnaryFunctor
    public List<ConditionalEffect> invokeWithReturningEffects(List<ConditionalEffect> list) {
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (ConditionalEffect conditionalEffect : list) {
            SimpleEffect simpleEffect = conditionalEffect.getSimpleEffect();
            simpleEffect.getClass();
            ESValue value = ((ESReturns) simpleEffect).getValue();
            ConditionalEffect conditionalEffect2 = ValuesKt.isTrue(value) ? new ConditionalEffect(conditionalEffect.getCondition(), new ESReturns(ESConstants.INSTANCE.getFalseValue())) : ValuesKt.isFalse(value) ? new ConditionalEffect(conditionalEffect.getCondition(), new ESReturns(ESConstants.INSTANCE.getTrueValue())) : null;
            if (conditionalEffect2 != null) {
                arrayList.add(conditionalEffect2);
            }
        }
        return arrayList;
    }
}
