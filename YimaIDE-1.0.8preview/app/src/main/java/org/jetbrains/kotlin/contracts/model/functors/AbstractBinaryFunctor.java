package org.jetbrains.kotlin.contracts.model.functors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.AbstractFunctor;
import org.jetbrains.kotlin.contracts.model.Computation;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESExpression;
import org.jetbrains.kotlin.contracts.model.ESTypeSubstitution;
import org.jetbrains.kotlin.contracts.model.SimpleEffect;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.contracts.model.structure.ESOr;
import org.jetbrains.kotlin.contracts.model.structure.ESReturns;
import org.jetbrains.kotlin.contracts.model.structure.ValuesKt;
import org.jetbrains.kotlin.contracts.model.visitors.Reducer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0005H\u0004J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H$J*\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00130\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00130\u0005H$¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/functors/AbstractBinaryFunctor;", "Lorg/jetbrains/kotlin/contracts/model/AbstractFunctor;", "<init>", "()V", "doInvocation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "arguments", "Lorg/jetbrains/kotlin/contracts/model/Computation;", "typeSubstitution", "Lorg/jetbrains/kotlin/contracts/model/ESTypeSubstitution;", "reducer", "Lorg/jetbrains/kotlin/contracts/model/visitors/Reducer;", "invokeWithArguments", "left", "right", "foldConditionsWithOr", "Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "list", "Lorg/jetbrains/kotlin/contracts/model/ConditionalEffect;", "invokeWithConstant", "computation", "constant", "Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "invokeWithReturningEffects", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractBinaryFunctor extends AbstractFunctor {
    @Override // org.jetbrains.kotlin.contracts.model.AbstractFunctor
    public List<ESEffect> doInvocation(List<? extends Computation> arguments, ESTypeSubstitution typeSubstitution, Reducer reducer) {
        arguments.getClass();
        typeSubstitution.getClass();
        reducer.getClass();
        arguments.size();
        return invokeWithArguments(arguments.get(0), arguments.get(1));
    }

    public final ESExpression foldConditionsWithOr(List<ConditionalEffect> list) {
        list.getClass();
        if (list.isEmpty()) {
            return null;
        }
        List<ConditionalEffect> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((ConditionalEffect) it.next()).getCondition());
        }
        Iterator it2 = arrayList.iterator();
        if (!it2.hasNext()) {
            c41.a("Empty collection can't be reduced.");
            return null;
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            next = new ESOr((ESExpression) next, (ESExpression) it2.next());
        }
        return (ESExpression) next;
    }

    public final List<ESEffect> invokeWithArguments(Computation left, Computation right) {
        left.getClass();
        right.getClass();
        if (left instanceof ESConstant) {
            return invokeWithConstant(right, (ESConstant) left);
        }
        if (right instanceof ESConstant) {
            return invokeWithConstant(left, (ESConstant) right);
        }
        List<ESEffect> effects = left.getEffects();
        ArrayList arrayList = new ArrayList();
        for (Object obj : effects) {
            if (obj instanceof ConditionalEffect) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            SimpleEffect simpleEffect = ((ConditionalEffect) obj2).getSimpleEffect();
            if ((simpleEffect instanceof ESReturns) && !ValuesKt.isWildcard(((ESReturns) simpleEffect).getValue())) {
                arrayList2.add(obj2);
            }
        }
        List<ESEffect> effects2 = right.getEffects();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : effects2) {
            if (obj3 instanceof ConditionalEffect) {
                arrayList3.add(obj3);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj4 : arrayList3) {
            SimpleEffect simpleEffect2 = ((ConditionalEffect) obj4).getSimpleEffect();
            if ((simpleEffect2 instanceof ESReturns) && !ValuesKt.isWildcard(((ESReturns) simpleEffect2).getValue())) {
                arrayList4.add(obj4);
            }
        }
        return CollectionsKt.plus(CollectionsKt.minus(CollectionsKt.plus(CollectionsKt.minus(left.getEffects(), arrayList2), right.getEffects()), arrayList4), invokeWithReturningEffects(arrayList2, arrayList4));
    }

    public abstract List<ESEffect> invokeWithConstant(Computation computation, ESConstant constant);

    public abstract List<ConditionalEffect> invokeWithReturningEffects(List<ConditionalEffect> left, List<ConditionalEffect> right);
}
