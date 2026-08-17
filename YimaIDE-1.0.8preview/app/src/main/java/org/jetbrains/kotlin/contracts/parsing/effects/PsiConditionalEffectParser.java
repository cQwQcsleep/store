package org.jetbrains.kotlin.contracts.parsing.effects;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.BooleanExpression;
import org.jetbrains.kotlin.contracts.description.ConditionalEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.EffectDeclaration;
import org.jetbrains.kotlin.contracts.parsing.AbstractPsiEffectParser;
import org.jetbrains.kotlin.contracts.parsing.ContractCallContext;
import org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector;
import org.jetbrains.kotlin.contracts.parsing.PsiContractParserDispatcher;
import org.jetbrains.kotlin.contracts.parsing.PsiContractsUtilsKt;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.calls.util.CallUtilKt;
import org.jetbrains.kotlin.resolve.scopes.receivers.ExpressionReceiver;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/effects/PsiConditionalEffectParser;", "Lorg/jetbrains/kotlin/contracts/parsing/AbstractPsiEffectParser;", "collector", "Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;", "callContext", "Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;", "dispatcher", "Lorg/jetbrains/kotlin/contracts/parsing/PsiContractParserDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;Lorg/jetbrains/kotlin/contracts/parsing/PsiContractParserDispatcher;)V", "tryParseEffect", "Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;", "expression", "Lorg/jetbrains/kotlin/psi/KtExpression;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiConditionalEffectParser extends AbstractPsiEffectParser {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PsiConditionalEffectParser(ContractParsingDiagnosticsCollector contractParsingDiagnosticsCollector, ContractCallContext contractCallContext, PsiContractParserDispatcher psiContractParserDispatcher) {
        super(contractParsingDiagnosticsCollector, contractCallContext, psiContractParserDispatcher);
        contractParsingDiagnosticsCollector.getClass();
        contractCallContext.getClass();
        psiContractParserDispatcher.getClass();
    }

    @Override // org.jetbrains.kotlin.contracts.parsing.PsiEffectParser
    public EffectDeclaration tryParseEffect(KtExpression expression) {
        BooleanExpression condition;
        expression.getClass();
        ResolvedCall resolvedCall = CallUtilKt.getResolvedCall(expression, getCallContext().getBindingContext());
        if (resolvedCall == null) {
            return null;
        }
        CallableDescriptor resultingDescriptor = resolvedCall.getResultingDescriptor();
        resultingDescriptor.getClass();
        if (!PsiContractsUtilsKt.isImpliesCallDescriptor(resultingDescriptor)) {
            return null;
        }
        PsiContractParserDispatcher contractParserDispatcher = getContractParserDispatcher();
        ExpressionReceiver dispatchReceiver = resolvedCall.getDispatchReceiver();
        ExpressionReceiver expressionReceiver = dispatchReceiver instanceof ExpressionReceiver ? dispatchReceiver : null;
        EffectDeclaration effect = contractParserDispatcher.parseEffect(expressionReceiver != null ? expressionReceiver.getExpression() : null);
        if (effect == null || (condition = getContractParserDispatcher().parseCondition(PsiContractsUtilsKt.firstArgumentAsExpressionOrNull(resolvedCall))) == null) {
            return null;
        }
        return new ConditionalEffectDeclaration(effect, condition);
    }
}
