package org.jetbrains.kotlin.contracts.interpretation;

import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanVariableReference;
import org.jetbrains.kotlin.contracts.description.expressions.IsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.expressions.IsNullPredicate;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalAnd;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalNot;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalOr;
import org.jetbrains.kotlin.contracts.model.ESExpression;
import org.jetbrains.kotlin.contracts.model.functors.IsFunctor;
import org.jetbrains.kotlin.contracts.model.structure.ESAnd;
import org.jetbrains.kotlin.contracts.model.structure.ESConstants;
import org.jetbrains.kotlin.contracts.model.structure.ESEqual;
import org.jetbrains.kotlin.contracts.model.structure.ESIs;
import org.jetbrains.kotlin.contracts.model.structure.ESNot;
import org.jetbrains.kotlin.contracts.model.structure.ESOr;
import org.jetbrains.kotlin.contracts.model.structure.ESVariable;
import org.jetbrains.kotlin.contracts.model.structure.TypesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\fJ\u001f\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0010J\u001f\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0014J\u001f\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0018J\u001f\u0010\u0019\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u001cJ\u001f\u0010\u001d\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010 J\u001f\u0010!\u001a\u0004\u0018\u00010\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/contracts/interpretation/ConditionInterpreter;", "Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;", "Lorg/jetbrains/kotlin/contracts/model/ESExpression;", Argument.Delimiters.none, "dispatcher", "Lorg/jetbrains/kotlin/contracts/interpretation/ContractInterpretationDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/contracts/interpretation/ContractInterpretationDispatcher;)V", "visitLogicalOr", "logicalOr", "Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalOr;", "data", "(Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalOr;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "visitLogicalAnd", "logicalAnd", "Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalAnd;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalAnd;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "visitLogicalNot", "logicalNot", "Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalNot;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalNot;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "visitIsInstancePredicate", "isInstancePredicate", "Lorg/jetbrains/kotlin/contracts/description/expressions/IsInstancePredicate;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/IsInstancePredicate;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "visitIsNullPredicate", "isNullPredicate", "Lorg/jetbrains/kotlin/contracts/description/expressions/IsNullPredicate;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/IsNullPredicate;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "visitBooleanConstantDescriptor", "booleanConstantDescriptor", "Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanConstantReference;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanConstantReference;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "visitBooleanVariableReference", "booleanVariableReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanVariableReference;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanVariableReference;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConditionInterpreter implements ContractDescriptionVisitor<ESExpression, Unit> {
    private final ContractInterpretationDispatcher dispatcher;

    public ConditionInterpreter(ContractInterpretationDispatcher contractInterpretationDispatcher) {
        contractInterpretationDispatcher.getClass();
        this.dispatcher = contractInterpretationDispatcher;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public ESExpression visitBooleanConstantDescriptor(BooleanConstantReference booleanConstantDescriptor, Unit data) {
        booleanConstantDescriptor.getClass();
        data.getClass();
        return this.dispatcher.interpretConstant$org_jetbrains_kotlin_resolution(booleanConstantDescriptor);
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public ESExpression visitBooleanVariableReference(BooleanVariableReference booleanVariableReference, Unit data) {
        booleanVariableReference.getClass();
        data.getClass();
        return this.dispatcher.interpretVariable$org_jetbrains_kotlin_resolution(booleanVariableReference);
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public ESExpression visitIsInstancePredicate(IsInstancePredicate isInstancePredicate, Unit data) {
        isInstancePredicate.getClass();
        data.getClass();
        ESVariable eSVariableInterpretVariable$org_jetbrains_kotlin_resolution = this.dispatcher.interpretVariable$org_jetbrains_kotlin_resolution(isInstancePredicate.getArg());
        if (eSVariableInterpretVariable$org_jetbrains_kotlin_resolution == null) {
            return null;
        }
        return new ESIs(eSVariableInterpretVariable$org_jetbrains_kotlin_resolution, new IsFunctor(TypesKt.toESType(isInstancePredicate.getType()), isInstancePredicate.getIsNegated()));
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public ESExpression visitIsNullPredicate(IsNullPredicate isNullPredicate, Unit data) {
        isNullPredicate.getClass();
        data.getClass();
        ESVariable eSVariableInterpretVariable$org_jetbrains_kotlin_resolution = this.dispatcher.interpretVariable$org_jetbrains_kotlin_resolution(isNullPredicate.getArg());
        if (eSVariableInterpretVariable$org_jetbrains_kotlin_resolution == null) {
            return null;
        }
        return new ESEqual(eSVariableInterpretVariable$org_jetbrains_kotlin_resolution, ESConstants.INSTANCE.getNullValue(), isNullPredicate.getIsNegated());
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public ESExpression visitLogicalAnd(LogicalAnd logicalAnd, Unit data) {
        ESExpression eSExpression;
        logicalAnd.getClass();
        data.getClass();
        ESExpression eSExpression2 = (ESExpression) logicalAnd.getLeft().accept(this, data);
        if (eSExpression2 == null || (eSExpression = (ESExpression) logicalAnd.getRight().accept(this, data)) == null) {
            return null;
        }
        return new ESAnd(eSExpression2, eSExpression);
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public ESExpression visitLogicalNot(LogicalNot logicalNot, Unit data) {
        logicalNot.getClass();
        data.getClass();
        ESExpression eSExpression = (ESExpression) logicalNot.getArg().accept(this, data);
        if (eSExpression == null) {
            return null;
        }
        return new ESNot(eSExpression);
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public ESExpression visitLogicalOr(LogicalOr logicalOr, Unit data) {
        ESExpression eSExpression;
        logicalOr.getClass();
        data.getClass();
        ESExpression eSExpression2 = (ESExpression) logicalOr.getLeft().accept(this, data);
        if (eSExpression2 == null || (eSExpression = (ESExpression) logicalOr.getRight().accept(this, data)) == null) {
            return null;
        }
        return new ESOr(eSExpression2, eSExpression);
    }
}
