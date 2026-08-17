package org.jetbrains.kotlin.contracts.model.visitors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESExpression;
import org.jetbrains.kotlin.contracts.model.ESExpressionVisitor;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.structure.ESAnd;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.contracts.model.structure.ESConstants;
import org.jetbrains.kotlin.contracts.model.structure.ESEqual;
import org.jetbrains.kotlin.contracts.model.structure.ESIs;
import org.jetbrains.kotlin.contracts.model.structure.ESNot;
import org.jetbrains.kotlin.contracts.model.structure.ESOr;
import org.jetbrains.kotlin.contracts.model.structure.ESReceiver;
import org.jetbrains.kotlin.contracts.model.structure.ESType;
import org.jetbrains.kotlin.contracts.model.structure.ESVariable;
import org.jetbrains.kotlin.contracts.model.structure.ValuesKt;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.typeUtil.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\tH\u0002J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0016J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0016J\u0012\u0010%\u001a\u0004\u0018\u00010\u00022\u0006\u0010&\u001a\u00020'H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/visitors/Reducer;", "Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;", "Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "<init>", "(Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;)V", "reduceEffects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "schema", "reduceEffect", "effect", "visitIs", "isOperator", "Lorg/jetbrains/kotlin/contracts/model/structure/ESIs;", "visitEqual", "equal", "Lorg/jetbrains/kotlin/contracts/model/structure/ESEqual;", "visitAnd", "and", "Lorg/jetbrains/kotlin/contracts/model/structure/ESAnd;", "visitOr", "or", "Lorg/jetbrains/kotlin/contracts/model/structure/ESOr;", "visitNot", "not", "Lorg/jetbrains/kotlin/contracts/model/structure/ESNot;", "visitVariable", "Lorg/jetbrains/kotlin/contracts/model/structure/ESVariable;", "esVariable", "visitConstant", "Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "esConstant", "visitReceiver", "Lorg/jetbrains/kotlin/contracts/model/structure/ESReceiver;", "esReceiver", "visitLambda", "lambda", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Reducer implements ESExpressionVisitor<ESExpression> {
    private final KotlinBuiltIns builtIns;

    public Reducer(KotlinBuiltIns kotlinBuiltIns) {
        kotlinBuiltIns.getClass();
        this.builtIns = kotlinBuiltIns;
    }

    private final ESEffect reduceEffect(ESEffect effect) {
        if (effect instanceof ConditionalEffect) {
            ConditionalEffect conditionalEffect = (ConditionalEffect) effect;
            ESExpression eSExpression = (ESExpression) conditionalEffect.getCondition().accept(this);
            if (eSExpression == null || ValuesKt.isFalse(eSExpression)) {
                return null;
            }
            if (ValuesKt.isTrue(eSExpression)) {
                return conditionalEffect.getSimpleEffect();
            }
        }
        return effect;
    }

    public final List<ESEffect> reduceEffects(List<? extends ESEffect> schema) {
        schema.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = schema.iterator();
        while (it.hasNext()) {
            ESEffect eSEffectReduceEffect = reduceEffect((ESEffect) it.next());
            if (eSEffectReduceEffect != null) {
                arrayList.add(eSEffectReduceEffect);
            }
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public ESExpression visitAnd(ESAnd and) {
        ESExpression eSExpression;
        and.getClass();
        ESExpression eSExpression2 = (ESExpression) and.getLeft().accept(this);
        if (eSExpression2 == null || (eSExpression = (ESExpression) and.getRight().accept(this)) == null) {
            return null;
        }
        if (!ValuesKt.isFalse(eSExpression2) && !ValuesKt.isFalse(eSExpression)) {
            if (ValuesKt.isTrue(eSExpression2)) {
                return eSExpression;
            }
            if (!ValuesKt.isTrue(eSExpression)) {
                return new ESAnd(eSExpression2, eSExpression);
            }
        }
        return eSExpression2;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public ESExpression visitEqual(ESEqual equal) {
        equal.getClass();
        ESValue eSValue = (ESValue) equal.getLeft().accept(this);
        if (eSValue == null) {
            return null;
        }
        ESValue right = equal.getRight();
        return eSValue instanceof ESConstant ? ESConstants.INSTANCE.booleanValue(Intrinsics.areEqual(eSValue, right) ^ equal.getFunctor().getIsNegated()) : new ESEqual(eSValue, right, equal.getFunctor().getIsNegated());
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public ESExpression visitIs(ESIs isOperator) {
        isOperator.getClass();
        Object objAccept = isOperator.getLeft().accept(this);
        objAccept.getClass();
        ESValue eSValue = (ESValue) objAccept;
        ESType type = eSValue.getType();
        Boolean boolValueOf = null;
        KotlinType kotlinType = type != null ? type.toKotlinType(this.builtIns) : null;
        KotlinType kotlinType2 = isOperator.getFunctor().getType().toKotlinType(this.builtIns);
        if (eSValue instanceof ESConstant) {
            kotlinType.getClass();
            boolValueOf = Boolean.valueOf(TypeUtilsKt.isSubtypeOf(kotlinType, kotlinType2));
        } else {
            if (!(eSValue instanceof ESVariable) && !(eSValue instanceof ESReceiver)) {
                qu7.a("Unknown ESValue: ", eSValue);
                return null;
            }
            if (kotlinType != null && TypeUtilsKt.isSubtypeOf(kotlinType, kotlinType2)) {
                boolValueOf = Boolean.TRUE;
            }
        }
        if (boolValueOf != null) {
            return ESConstants.INSTANCE.booleanValue(isOperator.getFunctor().getIsNegated() ^ boolValueOf.booleanValue());
        }
        return new ESIs(eSValue, isOperator.getFunctor());
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public ESExpression visitLambda(ESValue lambda) {
        lambda.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public ESExpression visitNot(ESNot not) {
        not.getClass();
        ESExpression eSExpression = (ESExpression) not.getArg().accept(this);
        if (eSExpression == null) {
            return null;
        }
        if (ValuesKt.isTrue(eSExpression)) {
            return ESConstants.INSTANCE.getFalseValue();
        }
        return ValuesKt.isFalse(eSExpression) ? ESConstants.INSTANCE.getTrueValue() : eSExpression;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public ESExpression visitOr(ESOr or) {
        ESExpression eSExpression;
        or.getClass();
        ESExpression eSExpression2 = (ESExpression) or.getLeft().accept(this);
        if (eSExpression2 == null || (eSExpression = (ESExpression) or.getRight().accept(this)) == null) {
            return null;
        }
        if (!ValuesKt.isTrue(eSExpression2) && !ValuesKt.isTrue(eSExpression)) {
            if (ValuesKt.isFalse(eSExpression2)) {
                return eSExpression;
            }
            if (!ValuesKt.isFalse(eSExpression)) {
                return new ESOr(eSExpression2, eSExpression);
            }
        }
        return eSExpression2;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public ESExpression visitConstant(ESConstant esConstant) {
        esConstant.getClass();
        return esConstant;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public ESExpression visitReceiver(ESReceiver esReceiver) {
        esReceiver.getClass();
        return esReceiver;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public ESExpression visitVariable(ESVariable esVariable) {
        esVariable.getClass();
        return esVariable;
    }
}
