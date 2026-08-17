package org.jetbrains.kotlin.contracts.parsing.effects;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.CallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.EffectDeclaration;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.contracts.description.expressions.VariableReference;
import org.jetbrains.kotlin.contracts.parsing.AbstractPsiEffectParser;
import org.jetbrains.kotlin.contracts.parsing.ContractCallContext;
import org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector;
import org.jetbrains.kotlin.contracts.parsing.PsiContractParserDispatcher;
import org.jetbrains.kotlin.contracts.parsing.PsiContractsUtilsKt;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.ValueArgument;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.ContractsDslNames;
import org.jetbrains.kotlin.resolve.calls.model.DefaultValueArgument;
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedValueArgument;
import org.jetbrains.kotlin.resolve.calls.util.CallUtilKt;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/effects/PsiCallsEffectParser;", "Lorg/jetbrains/kotlin/contracts/parsing/AbstractPsiEffectParser;", "collector", "Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;", "callContext", "Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;", "contractParserDispatcher", "Lorg/jetbrains/kotlin/contracts/parsing/PsiContractParserDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;Lorg/jetbrains/kotlin/contracts/parsing/PsiContractParserDispatcher;)V", "tryParseEffect", "Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;", "expression", "Lorg/jetbrains/kotlin/psi/KtExpression;", "toInvocationKind", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiCallsEffectParser extends AbstractPsiEffectParser {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PsiCallsEffectParser(ContractParsingDiagnosticsCollector contractParsingDiagnosticsCollector, ContractCallContext contractCallContext, PsiContractParserDispatcher psiContractParserDispatcher) {
        super(contractParsingDiagnosticsCollector, contractCallContext, psiContractParserDispatcher);
        contractParsingDiagnosticsCollector.getClass();
        contractCallContext.getClass();
        psiContractParserDispatcher.getClass();
    }

    private final EventOccurrencesRange toInvocationKind(KtExpression ktExpression, BindingContext bindingContext) {
        CallableDescriptor resultingDescriptor;
        ResolvedCall resolvedCall = CallUtilKt.getResolvedCall(ktExpression, bindingContext);
        if (resolvedCall == null || (resultingDescriptor = resolvedCall.getResultingDescriptor()) == null || !PsiContractsUtilsKt.isInvocationKindEnum((DeclarationDescriptor) SequencesKt.first(DescriptorUtilsKt.getParents(resultingDescriptor)))) {
            return null;
        }
        Name nameShortName = DescriptorUtilsKt.getFqNameSafe(resultingDescriptor).shortName();
        ContractsDslNames contractsDslNames = ContractsDslNames.INSTANCE;
        if (Intrinsics.areEqual(nameShortName, contractsDslNames.getAT_MOST_ONCE_KIND().getCallableName())) {
            return EventOccurrencesRange.AT_MOST_ONCE;
        }
        if (Intrinsics.areEqual(nameShortName, contractsDslNames.getEXACTLY_ONCE_KIND().getCallableName())) {
            return EventOccurrencesRange.EXACTLY_ONCE;
        }
        if (Intrinsics.areEqual(nameShortName, contractsDslNames.getAT_LEAST_ONCE_KIND().getCallableName())) {
            return EventOccurrencesRange.AT_LEAST_ONCE;
        }
        if (Intrinsics.areEqual(nameShortName, contractsDslNames.getUNKNOWN_KIND().getCallableName())) {
            return EventOccurrencesRange.UNKNOWN;
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.contracts.parsing.PsiEffectParser
    public EffectDeclaration tryParseEffect(KtExpression expression) {
        VariableReference variable;
        EventOccurrencesRange invocationKind;
        ValueArgument valueArgument;
        KtExpression argumentExpression;
        ValueArgument valueArgument2;
        KtExpression argumentExpression2;
        expression.getClass();
        ResolvedCall resolvedCall = CallUtilKt.getResolvedCall(expression, getCallContext().getBindingContext());
        if (resolvedCall == null) {
            return null;
        }
        CallableDescriptor resultingDescriptor = resolvedCall.getResultingDescriptor();
        resultingDescriptor.getClass();
        if (!PsiContractsUtilsKt.isCallsInPlaceEffectDescriptor(resultingDescriptor) || (variable = getContractParserDispatcher().parseVariable(PsiContractsUtilsKt.firstArgumentAsExpressionOrNull(resolvedCall))) == null) {
            return null;
        }
        List valueArgumentsByIndex = resolvedCall.getValueArgumentsByIndex();
        ResolvedValueArgument resolvedValueArgument = valueArgumentsByIndex != null ? (ResolvedValueArgument) CollectionsKt.getOrNull(valueArgumentsByIndex, 1) : null;
        if (resolvedValueArgument instanceof DefaultValueArgument) {
            invocationKind = EventOccurrencesRange.UNKNOWN;
        } else {
            invocationKind = (!(resolvedValueArgument instanceof ExpressionValueArgument) || (valueArgument = ((ExpressionValueArgument) resolvedValueArgument).getValueArgument()) == null || (argumentExpression = valueArgument.getArgumentExpression()) == null) ? null : toInvocationKind(argumentExpression, getCallContext().getBindingContext());
        }
        if (invocationKind != null) {
            return new CallsEffectDeclaration(variable, invocationKind);
        }
        ExpressionValueArgument expressionValueArgument = resolvedValueArgument instanceof ExpressionValueArgument ? (ExpressionValueArgument) resolvedValueArgument : null;
        if (expressionValueArgument != null && (valueArgument2 = expressionValueArgument.getValueArgument()) != null && (argumentExpression2 = valueArgument2.getArgumentExpression()) != null) {
            expression = argumentExpression2;
        }
        getCollector().badDescription("unrecognized InvocationKind", expression);
        return null;
    }
}
