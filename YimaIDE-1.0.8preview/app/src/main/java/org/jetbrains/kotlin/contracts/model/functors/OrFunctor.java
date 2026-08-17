package org.jetbrains.kotlin.contracts.model.functors;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.Computation;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESExpression;
import org.jetbrains.kotlin.contracts.model.SimpleEffect;
import org.jetbrains.kotlin.contracts.model.functors.OrFunctor;
import org.jetbrains.kotlin.contracts.model.structure.ESAnd;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.contracts.model.structure.ESConstants;
import org.jetbrains.kotlin.contracts.model.structure.ESOr;
import org.jetbrains.kotlin.contracts.model.structure.ESReturns;
import org.jetbrains.kotlin.contracts.model.structure.ValuesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014J*\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u0005H\u0014¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/functors/OrFunctor;", "Lorg/jetbrains/kotlin/contracts/model/functors/AbstractBinaryFunctor;", "<init>", "()V", "invokeWithConstant", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "computation", "Lorg/jetbrains/kotlin/contracts/model/Computation;", "constant", "Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "invokeWithReturningEffects", "Lorg/jetbrains/kotlin/contracts/model/ConditionalEffect;", "left", "right", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OrFunctor extends AbstractBinaryFunctor {
    public static ESOr a(ESExpression eSExpression, ESExpression eSExpression2) {
        eSExpression.getClass();
        eSExpression2.getClass();
        return new ESOr(eSExpression, eSExpression2);
    }

    public static ESExpression b(ESExpression eSExpression, ESExpression eSExpression2) {
        eSExpression.getClass();
        eSExpression2.getClass();
        return new ESAnd(eSExpression, eSExpression2);
    }

    @Override // org.jetbrains.kotlin.contracts.model.functors.AbstractBinaryFunctor
    public List<ESEffect> invokeWithConstant(Computation computation, ESConstant constant) {
        computation.getClass();
        constant.getClass();
        if (!ValuesKt.isFalse(constant) && ValuesKt.isTrue(constant)) {
            return CollectionsKt.emptyList();
        }
        return computation.getEffects();
    }

    @Override // org.jetbrains.kotlin.contracts.model.functors.AbstractBinaryFunctor
    public List<ConditionalEffect> invokeWithReturningEffects(List<ConditionalEffect> left, List<ConditionalEffect> right) {
        left.getClass();
        right.getClass();
        List<ConditionalEffect> list = left;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            SimpleEffect simpleEffect = ((ConditionalEffect) obj).getSimpleEffect();
            if ((simpleEffect instanceof ESReturns) && ValuesKt.isTrue(((ESReturns) simpleEffect).getValue())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list) {
            SimpleEffect simpleEffect2 = ((ConditionalEffect) obj2).getSimpleEffect();
            if ((simpleEffect2 instanceof ESReturns) && ValuesKt.isFalse(((ESReturns) simpleEffect2).getValue())) {
                arrayList2.add(obj2);
            }
        }
        List<ConditionalEffect> list2 = right;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : list2) {
            SimpleEffect simpleEffect3 = ((ConditionalEffect) obj3).getSimpleEffect();
            if ((simpleEffect3 instanceof ESReturns) && ValuesKt.isTrue(((ESReturns) simpleEffect3).getValue())) {
                arrayList3.add(obj3);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj4 : list2) {
            SimpleEffect simpleEffect4 = ((ConditionalEffect) obj4).getSimpleEffect();
            if ((simpleEffect4 instanceof ESReturns) && ValuesKt.isFalse(((ESReturns) simpleEffect4).getValue())) {
                arrayList4.add(obj4);
            }
        }
        ESExpression eSExpressionFoldConditionsWithOr = foldConditionsWithOr(arrayList);
        ESExpression eSExpressionFoldConditionsWithOr2 = foldConditionsWithOr(arrayList3);
        ESExpression eSExpressionFoldConditionsWithOr3 = foldConditionsWithOr(arrayList2);
        ESExpression eSExpressionFoldConditionsWithOr4 = foldConditionsWithOr(arrayList4);
        ESOr eSOr = (ESOr) FunctorsUtilsKt.applyIfBothNotNull(eSExpressionFoldConditionsWithOr, eSExpressionFoldConditionsWithOr2, new Function2() { // from class: ora
            public final Object invoke(Object obj5, Object obj6) {
                return OrFunctor.a((ESExpression) obj5, (ESExpression) obj6);
            }
        });
        ESExpression eSExpression = (ESExpression) FunctorsUtilsKt.applyWithDefault(eSExpressionFoldConditionsWithOr3, eSExpressionFoldConditionsWithOr4, new Function2() { // from class: pra
            public final Object invoke(Object obj5, Object obj6) {
                return OrFunctor.b((ESExpression) obj5, (ESExpression) obj6);
            }
        });
        ArrayList arrayList5 = new ArrayList();
        if (eSOr != null) {
            arrayList5.add(new ConditionalEffect(eSOr, new ESReturns(ESConstants.INSTANCE.getTrueValue())));
        }
        if (eSExpression != null) {
            arrayList5.add(new ConditionalEffect(eSExpression, new ESReturns(ESConstants.INSTANCE.getFalseValue())));
        }
        return arrayList5;
    }
}
