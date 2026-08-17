package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u0000*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u00042\u00020\u0005B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\b\u001a\u00028\u00002\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ)\u0010\r\u001a\u00028\u00002\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u000f2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0010J)\u0010\u0011\u001a\u00028\u00002\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00132\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0014J)\u0010\u0015\u001a\u00028\u00002\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00162\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0017J)\u0010\u0018\u001a\u00028\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u001a2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001bJ)\u0010\u001c\u001a\u00028\u00002\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u001e2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001fJ)\u0010 \u001a\u00028\u00002\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\"2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010#J)\u0010$\u001a\u00028\u00002\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030&2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010'J)\u0010(\u001a\u00028\u00002\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030)2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010*J)\u0010+\u001a\u00028\u00002\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030-2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010.J)\u0010/\u001a\u00028\u00002\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003012\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00102J)\u00103\u001a\u00028\u00002\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003052\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00106J)\u00107\u001a\u00028\u00002\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003092\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010:J)\u0010;\u001a\u00028\u00002\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030<2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010=J)\u0010>\u001a\u00028\u00002\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030@2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010AJ)\u0010B\u001a\u00028\u00002\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030D2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010EJ)\u0010F\u001a\u00028\u00002\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030H2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010IJ)\u0010J\u001a\u00028\u00002\u0012\u0010K\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030L2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010MJ)\u0010N\u001a\u00028\u00002\u0012\u0010O\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030P2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010QJ)\u0010R\u001a\u00028\u00002\u0012\u0010S\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030T2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010UJ)\u0010V\u001a\u00028\u00002\u0012\u0010W\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030X2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010YJ)\u0010Z\u001a\u00028\u00002\u0012\u0010S\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030[2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\\J)\u0010]\u001a\u00028\u00002\u0012\u0010^\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030_2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010`¨\u0006a"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;", "R", "D", "Type", "Diagnostic", Argument.Delimiters.none, "<init>", "()V", "visitContractDescriptionElement", "contractDescriptionElement", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "data", "(Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;Ljava/lang/Object;)Ljava/lang/Object;", "visitEffectDeclaration", "effectDeclaration", "Lorg/jetbrains/kotlin/contracts/description/KtEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/KtEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitConditionalEffectDeclaration", "conditionalEffect", "Lorg/jetbrains/kotlin/contracts/description/KtConditionalEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/KtConditionalEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitConditionalReturnsDeclaration", "Lorg/jetbrains/kotlin/contracts/description/KtConditionalReturnsDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/KtConditionalReturnsDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitReturnsEffectDeclaration", "returnsEffect", "Lorg/jetbrains/kotlin/contracts/description/KtReturnsEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/KtReturnsEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallsEffectDeclaration", "callsEffect", "Lorg/jetbrains/kotlin/contracts/description/KtCallsEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/KtCallsEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitHoldsInEffectDeclaration", "holdsInEffect", "Lorg/jetbrains/kotlin/contracts/description/KtHoldsInEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/KtHoldsInEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitReturnsResultOfEffectDeclaration", "returnsResultOfEffect", "Lorg/jetbrains/kotlin/contracts/description/KtReturnsResultOfDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/KtReturnsResultOfDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitErroneousCallsEffectDeclaration", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousCallsEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/KtErroneousCallsEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanExpression", "booleanExpression", "Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;", "(Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitLogicalBinaryOperationContractExpression", "binaryLogicExpression", "Lorg/jetbrains/kotlin/contracts/description/KtBinaryLogicExpression;", "(Lorg/jetbrains/kotlin/contracts/description/KtBinaryLogicExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitLogicalNot", "logicalNot", "Lorg/jetbrains/kotlin/contracts/description/KtLogicalNot;", "(Lorg/jetbrains/kotlin/contracts/description/KtLogicalNot;Ljava/lang/Object;)Ljava/lang/Object;", "visitIsInstancePredicate", "isInstancePredicate", "Lorg/jetbrains/kotlin/contracts/description/KtIsInstancePredicate;", "(Lorg/jetbrains/kotlin/contracts/description/KtIsInstancePredicate;Ljava/lang/Object;)Ljava/lang/Object;", "visitErroneousIsInstancePredicate", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousIsInstancePredicate;", "(Lorg/jetbrains/kotlin/contracts/description/KtErroneousIsInstancePredicate;Ljava/lang/Object;)Ljava/lang/Object;", "visitIsNullPredicate", "isNullPredicate", "Lorg/jetbrains/kotlin/contracts/description/KtIsNullPredicate;", "(Lorg/jetbrains/kotlin/contracts/description/KtIsNullPredicate;Ljava/lang/Object;)Ljava/lang/Object;", "visitValue", "value", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionValue;", "(Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitConstantDescriptor", "constantReference", "Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "(Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanConstantDescriptor", "booleanConstantDescriptor", "Lorg/jetbrains/kotlin/contracts/description/KtBooleanConstantReference;", "(Lorg/jetbrains/kotlin/contracts/description/KtBooleanConstantReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitErroneousConstantReference", "erroneousConstantReference", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousConstantReference;", "(Lorg/jetbrains/kotlin/contracts/description/KtErroneousConstantReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitValueParameterReference", "valueParameterReference", "Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", "(Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanValueParameterReference", "booleanValueParameterReference", "Lorg/jetbrains/kotlin/contracts/description/KtBooleanValueParameterReference;", "(Lorg/jetbrains/kotlin/contracts/description/KtBooleanValueParameterReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitErroneousValueParameterReference", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousValueParameterReference;", "(Lorg/jetbrains/kotlin/contracts/description/KtErroneousValueParameterReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitErroneousElement", "element", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousContractElement;", "(Lorg/jetbrains/kotlin/contracts/description/KtErroneousContractElement;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KtContractDescriptionVisitor<R, D, Type, Diagnostic> {
    public R visitBooleanConstantDescriptor(KtBooleanConstantReference<Type, Diagnostic> booleanConstantDescriptor, D data) {
        booleanConstantDescriptor.getClass();
        return visitConstantDescriptor(booleanConstantDescriptor, data);
    }

    public R visitBooleanExpression(KtBooleanExpression<Type, Diagnostic> booleanExpression, D data) {
        booleanExpression.getClass();
        return visitContractDescriptionElement(booleanExpression, data);
    }

    public R visitBooleanValueParameterReference(KtBooleanValueParameterReference<Type, Diagnostic> booleanValueParameterReference, D data) {
        booleanValueParameterReference.getClass();
        return visitValueParameterReference(booleanValueParameterReference, data);
    }

    public R visitCallsEffectDeclaration(KtCallsEffectDeclaration<Type, Diagnostic> callsEffect, D data) {
        callsEffect.getClass();
        return visitEffectDeclaration(callsEffect, data);
    }

    public R visitConditionalEffectDeclaration(KtConditionalEffectDeclaration<Type, Diagnostic> conditionalEffect, D data) {
        conditionalEffect.getClass();
        return visitEffectDeclaration(conditionalEffect, data);
    }

    public R visitConditionalReturnsDeclaration(KtConditionalReturnsDeclaration<Type, Diagnostic> conditionalEffect, D data) {
        conditionalEffect.getClass();
        return visitEffectDeclaration(conditionalEffect, data);
    }

    public R visitConstantDescriptor(KtConstantReference<Type, Diagnostic> constantReference, D data) {
        constantReference.getClass();
        return visitValue(constantReference, data);
    }

    public R visitContractDescriptionElement(KtContractDescriptionElement<Type, Diagnostic> contractDescriptionElement, D data) {
        contractDescriptionElement.getClass();
        throw new IllegalStateException("Top of hierarchy reached, no overloads were found for element: " + contractDescriptionElement);
    }

    public R visitEffectDeclaration(KtEffectDeclaration<Type, Diagnostic> effectDeclaration, D data) {
        effectDeclaration.getClass();
        return visitContractDescriptionElement(effectDeclaration, data);
    }

    public R visitErroneousCallsEffectDeclaration(KtErroneousCallsEffectDeclaration<Type, Diagnostic> callsEffect, D data) {
        callsEffect.getClass();
        return visitCallsEffectDeclaration(callsEffect, data);
    }

    public R visitErroneousConstantReference(KtErroneousConstantReference<Type, Diagnostic> erroneousConstantReference, D data) {
        erroneousConstantReference.getClass();
        return visitConstantDescriptor(erroneousConstantReference, data);
    }

    public R visitErroneousElement(KtErroneousContractElement<Type, Diagnostic> element, D data) {
        element.getClass();
        return visitContractDescriptionElement(element, data);
    }

    public R visitErroneousIsInstancePredicate(KtErroneousIsInstancePredicate<Type, Diagnostic> isInstancePredicate, D data) {
        isInstancePredicate.getClass();
        return visitIsInstancePredicate(isInstancePredicate, data);
    }

    public R visitErroneousValueParameterReference(KtErroneousValueParameterReference<Type, Diagnostic> valueParameterReference, D data) {
        valueParameterReference.getClass();
        return visitValueParameterReference(valueParameterReference, data);
    }

    public R visitHoldsInEffectDeclaration(KtHoldsInEffectDeclaration<Type, Diagnostic> holdsInEffect, D data) {
        holdsInEffect.getClass();
        return visitEffectDeclaration(holdsInEffect, data);
    }

    public R visitIsInstancePredicate(KtIsInstancePredicate<Type, Diagnostic> isInstancePredicate, D data) {
        isInstancePredicate.getClass();
        return visitBooleanExpression(isInstancePredicate, data);
    }

    public R visitIsNullPredicate(KtIsNullPredicate<Type, Diagnostic> isNullPredicate, D data) {
        isNullPredicate.getClass();
        return visitBooleanExpression(isNullPredicate, data);
    }

    public R visitLogicalBinaryOperationContractExpression(KtBinaryLogicExpression<Type, Diagnostic> binaryLogicExpression, D data) {
        binaryLogicExpression.getClass();
        return visitBooleanExpression(binaryLogicExpression, data);
    }

    public R visitLogicalNot(KtLogicalNot<Type, Diagnostic> logicalNot, D data) {
        logicalNot.getClass();
        return visitBooleanExpression(logicalNot, data);
    }

    public R visitReturnsEffectDeclaration(KtReturnsEffectDeclaration<Type, Diagnostic> returnsEffect, D data) {
        returnsEffect.getClass();
        return visitEffectDeclaration(returnsEffect, data);
    }

    public R visitReturnsResultOfEffectDeclaration(KtReturnsResultOfDeclaration<Type, Diagnostic> returnsResultOfEffect, D data) {
        returnsResultOfEffect.getClass();
        return visitEffectDeclaration(returnsResultOfEffect, data);
    }

    public R visitValue(KtContractDescriptionValue<Type, Diagnostic> value, D data) {
        value.getClass();
        return visitContractDescriptionElement(value, data);
    }

    public R visitValueParameterReference(KtValueParameterReference<Type, Diagnostic> valueParameterReference, D data) {
        valueParameterReference.getClass();
        return visitValue(valueParameterReference, data);
    }
}
