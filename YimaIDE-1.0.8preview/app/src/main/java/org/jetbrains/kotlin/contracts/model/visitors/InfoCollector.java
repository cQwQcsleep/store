package org.jetbrains.kotlin.contracts.model.visitors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESExpressionVisitor;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.MutableContextInfo;
import org.jetbrains.kotlin.contracts.model.structure.ESAnd;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.contracts.model.structure.ESConstants;
import org.jetbrains.kotlin.contracts.model.structure.ESEqual;
import org.jetbrains.kotlin.contracts.model.structure.ESIs;
import org.jetbrains.kotlin.contracts.model.structure.ESNot;
import org.jetbrains.kotlin.contracts.model.structure.ESOr;
import org.jetbrains.kotlin.contracts.model.structure.ESReceiver;
import org.jetbrains.kotlin.contracts.model.structure.ESVariable;
import org.jetbrains.kotlin.contracts.model.structure.TypesKt;
import org.jetbrains.kotlin.contracts.model.visitors.InfoCollector;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u000b\u001a\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020\u00022\u0006\u0010)\u001a\u00020*H\u0016J!\u0010+\u001a\u0002H,\"\u0004\b\u0000\u0010,2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0.H\u0002¢\u0006\u0002\u0010/R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/visitors/InfoCollector;", "Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;", "Lorg/jetbrains/kotlin/contracts/model/MutableContextInfo;", "observedEffect", "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "<init>", "(Lorg/jetbrains/kotlin/contracts/model/ESEffect;Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;)V", "isInverted", Argument.Delimiters.none, "collectFromSchema", "schema", Argument.Delimiters.none, "collectFromEffect", "effect", "visitIs", "isOperator", "Lorg/jetbrains/kotlin/contracts/model/structure/ESIs;", "visitEqual", "equal", "Lorg/jetbrains/kotlin/contracts/model/structure/ESEqual;", "visitAnd", "and", "Lorg/jetbrains/kotlin/contracts/model/structure/ESAnd;", "visitNot", "not", "Lorg/jetbrains/kotlin/contracts/model/structure/ESNot;", "visitOr", "or", "Lorg/jetbrains/kotlin/contracts/model/structure/ESOr;", "visitVariable", "esVariable", "Lorg/jetbrains/kotlin/contracts/model/structure/ESVariable;", "visitConstant", "esConstant", "Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "visitReceiver", "esReceiver", "Lorg/jetbrains/kotlin/contracts/model/structure/ESReceiver;", "visitLambda", "lambda", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", "inverted", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InfoCollector implements ESExpressionVisitor<MutableContextInfo> {
    private final KotlinBuiltIns builtIns;
    private boolean isInverted;
    private final ESEffect observedEffect;

    public InfoCollector(ESEffect eSEffect, KotlinBuiltIns kotlinBuiltIns) {
        eSEffect.getClass();
        kotlinBuiltIns.getClass();
        this.observedEffect = eSEffect;
        this.builtIns = kotlinBuiltIns;
    }

    public static MutableContextInfo a(ESNot eSNot, InfoCollector infoCollector) {
        return (MutableContextInfo) eSNot.getArg().accept(infoCollector);
    }

    private final MutableContextInfo collectFromEffect(ESEffect effect) {
        if (!(effect instanceof ConditionalEffect)) {
            return MutableContextInfo.INSTANCE.getEMPTY().fire(effect);
        }
        ConditionalEffect conditionalEffect = (ConditionalEffect) effect;
        Boolean boolIsImplies = this.observedEffect.isImplies(conditionalEffect.getSimpleEffect());
        if (Intrinsics.areEqual(boolIsImplies, Boolean.TRUE)) {
            return (MutableContextInfo) conditionalEffect.getCondition().accept(this);
        }
        if (boolIsImplies != null && !Intrinsics.areEqual(boolIsImplies, Boolean.FALSE)) {
            bu8.a();
        }
        return null;
    }

    private final <R> R inverted(Function0<? extends R> block) {
        this.isInverted = !this.isInverted;
        R r = (R) block.invoke();
        this.isInverted = !this.isInverted;
        return r;
    }

    public final MutableContextInfo collectFromSchema(List<? extends ESEffect> schema) {
        schema.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = schema.iterator();
        while (it.hasNext()) {
            MutableContextInfo mutableContextInfoCollectFromEffect = collectFromEffect((ESEffect) it.next());
            if (mutableContextInfoCollectFromEffect != null) {
                arrayList.add(mutableContextInfoCollectFromEffect);
            }
        }
        MutableContextInfo empty = MutableContextInfo.INSTANCE.getEMPTY();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            empty = empty.and((MutableContextInfo) it2.next());
        }
        return empty;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public MutableContextInfo visitAnd(ESAnd and) {
        and.getClass();
        MutableContextInfo mutableContextInfo = (MutableContextInfo) and.getLeft().accept(this);
        MutableContextInfo mutableContextInfo2 = (MutableContextInfo) and.getRight().accept(this);
        return this.isInverted ? mutableContextInfo.or(mutableContextInfo2) : mutableContextInfo.and(mutableContextInfo2);
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public MutableContextInfo visitConstant(ESConstant esConstant) {
        esConstant.getClass();
        return MutableContextInfo.INSTANCE.getEMPTY();
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public MutableContextInfo visitEqual(ESEqual equal) {
        equal.getClass();
        return equal.getFunctor().getIsNegated() != this.isInverted ? MutableContextInfo.INSTANCE.getEMPTY().notEqual(equal.getLeft(), equal.getRight()) : MutableContextInfo.INSTANCE.getEMPTY().equal(equal.getLeft(), equal.getRight());
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public MutableContextInfo visitIs(ESIs isOperator) {
        isOperator.getClass();
        KotlinType kotlinType = isOperator.getType().toKotlinType(this.builtIns);
        return isOperator.getFunctor().getIsNegated() != this.isInverted ? MutableContextInfo.INSTANCE.getEMPTY().notSubtype(isOperator.getLeft(), kotlinType) : MutableContextInfo.INSTANCE.getEMPTY().subtype(isOperator.getLeft(), kotlinType);
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public MutableContextInfo visitLambda(ESValue lambda) {
        lambda.getClass();
        return MutableContextInfo.INSTANCE.getEMPTY();
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public MutableContextInfo visitNot(final ESNot not) {
        not.getClass();
        return (MutableContextInfo) inverted(new Function0() { // from class: ap6
            public final Object invoke() {
                return InfoCollector.a(not, this);
            }
        });
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public MutableContextInfo visitOr(ESOr or) {
        or.getClass();
        MutableContextInfo mutableContextInfo = (MutableContextInfo) or.getLeft().accept(this);
        MutableContextInfo mutableContextInfo2 = (MutableContextInfo) or.getRight().accept(this);
        return this.isInverted ? mutableContextInfo.and(mutableContextInfo2) : mutableContextInfo.or(mutableContextInfo2);
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public MutableContextInfo visitReceiver(ESReceiver esReceiver) {
        esReceiver.getClass();
        return MutableContextInfo.INSTANCE.getEMPTY();
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public MutableContextInfo visitVariable(ESVariable esVariable) {
        esVariable.getClass();
        return !TypesKt.isBoolean(esVariable.getType()) ? MutableContextInfo.INSTANCE.getEMPTY() : MutableContextInfo.INSTANCE.getEMPTY().equal(esVariable, ESConstants.INSTANCE.booleanValue(!this.isInverted));
    }
}
