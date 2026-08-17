package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanVariableReference;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.ContractDescriptionValue;
import org.jetbrains.kotlin.contracts.description.expressions.IsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.expressions.IsNullPredicate;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalAnd;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalNot;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalOr;
import org.jetbrains.kotlin.contracts.description.expressions.VariableReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u00002\u00020\u0003J\u001d\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\bJ\u001d\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ\u001d\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0014J\u001d\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0018J\u001d\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010 J\u001d\u0010!\u001a\u00028\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010$J\u001d\u0010%\u001a\u00028\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010(J\u001d\u0010)\u001a\u00028\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010,J\u001d\u0010-\u001a\u00028\u00002\u0006\u0010.\u001a\u00020/2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00100J\u001d\u00101\u001a\u00028\u00002\u0006\u00102\u001a\u0002032\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00104J\u001d\u00105\u001a\u00028\u00002\u0006\u00106\u001a\u0002072\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00108J\u001d\u00109\u001a\u00028\u00002\u0006\u0010:\u001a\u00020;2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010<J\u001d\u0010=\u001a\u00028\u00002\u0006\u0010>\u001a\u00020?2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010@J\u001d\u0010A\u001a\u00028\u00002\u0006\u0010B\u001a\u00020C2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010DJ\u001d\u0010E\u001a\u00028\u00002\u0006\u0010F\u001a\u00020G2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010Hø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006IÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;", "R", "D", Argument.Delimiters.none, "visitContractDescriptionElement", "contractDescriptionElement", "Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionElement;", "data", "(Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionElement;Ljava/lang/Object;)Ljava/lang/Object;", "visitEffectDeclaration", "effectDeclaration", "Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitConditionalEffectDeclaration", "conditionalEffect", "Lorg/jetbrains/kotlin/contracts/description/ConditionalEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/ConditionalEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitReturnsEffectDeclaration", "returnsEffect", "Lorg/jetbrains/kotlin/contracts/description/ReturnsEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/ReturnsEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallsEffectDeclaration", "callsEffect", "Lorg/jetbrains/kotlin/contracts/description/CallsEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/CallsEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitReturnsResultOfEffectDeclaration", "returnsResultOfEffect", "Lorg/jetbrains/kotlin/contracts/description/ReturnsResultOfEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/ReturnsResultOfEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanExpression", "booleanExpression", "Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "(Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitLogicalOr", "logicalOr", "Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalOr;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalOr;Ljava/lang/Object;)Ljava/lang/Object;", "visitLogicalAnd", "logicalAnd", "Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalAnd;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalAnd;Ljava/lang/Object;)Ljava/lang/Object;", "visitLogicalNot", "logicalNot", "Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalNot;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalNot;Ljava/lang/Object;)Ljava/lang/Object;", "visitIsInstancePredicate", "isInstancePredicate", "Lorg/jetbrains/kotlin/contracts/description/expressions/IsInstancePredicate;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/IsInstancePredicate;Ljava/lang/Object;)Ljava/lang/Object;", "visitIsNullPredicate", "isNullPredicate", "Lorg/jetbrains/kotlin/contracts/description/expressions/IsNullPredicate;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/IsNullPredicate;Ljava/lang/Object;)Ljava/lang/Object;", "visitValue", "value", "Lorg/jetbrains/kotlin/contracts/description/expressions/ContractDescriptionValue;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/ContractDescriptionValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitConstantDescriptor", "constantReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanConstantDescriptor", "booleanConstantDescriptor", "Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanConstantReference;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanConstantReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitVariableReference", "variableReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanVariableReference", "booleanVariableReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanVariableReference;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanVariableReference;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ContractDescriptionVisitor<R, D> {
    default R visitBooleanConstantDescriptor(BooleanConstantReference booleanConstantDescriptor, D data) {
        booleanConstantDescriptor.getClass();
        return visitConstantDescriptor(booleanConstantDescriptor, data);
    }

    default R visitBooleanExpression(BooleanExpression booleanExpression, D data) {
        booleanExpression.getClass();
        return visitContractDescriptionElement(booleanExpression, data);
    }

    default R visitBooleanVariableReference(BooleanVariableReference booleanVariableReference, D data) {
        booleanVariableReference.getClass();
        return visitVariableReference(booleanVariableReference, data);
    }

    default R visitCallsEffectDeclaration(CallsEffectDeclaration callsEffect, D data) {
        callsEffect.getClass();
        return visitEffectDeclaration(callsEffect, data);
    }

    default R visitConditionalEffectDeclaration(ConditionalEffectDeclaration conditionalEffect, D data) {
        conditionalEffect.getClass();
        return visitEffectDeclaration(conditionalEffect, data);
    }

    default R visitConstantDescriptor(ConstantReference constantReference, D data) {
        constantReference.getClass();
        return visitValue(constantReference, data);
    }

    default R visitContractDescriptionElement(ContractDescriptionElement contractDescriptionElement, D data) {
        contractDescriptionElement.getClass();
        throw new IllegalStateException("Top of hierarchy reached, no overloads were found for element: " + contractDescriptionElement);
    }

    default R visitEffectDeclaration(EffectDeclaration effectDeclaration, D data) {
        effectDeclaration.getClass();
        return visitContractDescriptionElement(effectDeclaration, data);
    }

    default R visitIsInstancePredicate(IsInstancePredicate isInstancePredicate, D data) {
        isInstancePredicate.getClass();
        return visitBooleanExpression(isInstancePredicate, data);
    }

    default R visitIsNullPredicate(IsNullPredicate isNullPredicate, D data) {
        isNullPredicate.getClass();
        return visitBooleanExpression(isNullPredicate, data);
    }

    default R visitLogicalAnd(LogicalAnd logicalAnd, D data) {
        logicalAnd.getClass();
        return visitBooleanExpression(logicalAnd, data);
    }

    default R visitLogicalNot(LogicalNot logicalNot, D data) {
        logicalNot.getClass();
        return visitBooleanExpression(logicalNot, data);
    }

    default R visitLogicalOr(LogicalOr logicalOr, D data) {
        logicalOr.getClass();
        return visitBooleanExpression(logicalOr, data);
    }

    default R visitReturnsEffectDeclaration(ReturnsEffectDeclaration returnsEffect, D data) {
        returnsEffect.getClass();
        return visitEffectDeclaration(returnsEffect, data);
    }

    default R visitReturnsResultOfEffectDeclaration(ReturnsResultOfEffectDeclaration returnsResultOfEffect, D data) {
        returnsResultOfEffect.getClass();
        return visitEffectDeclaration(returnsResultOfEffect, data);
    }

    default R visitValue(ContractDescriptionValue value, D data) {
        value.getClass();
        return visitContractDescriptionElement(value, data);
    }

    default R visitVariableReference(VariableReference variableReference, D data) {
        variableReference.getClass();
        return visitValue(variableReference, data);
    }
}
