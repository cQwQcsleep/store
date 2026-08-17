package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"isAnyOfDelegateOperators", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegatedPropertyInferenceSessionKt {
    public static final boolean isAnyOfDelegateOperators(FirElement firElement) {
        CallInfo callInfo;
        firElement.getClass();
        if (firElement instanceof FirPropertyAccessExpression) {
            Candidate candidate = CandidateFactoryKt.candidate((FirResolvable) firElement);
            FirElement callSite = (candidate == null || (callInfo = candidate.getCallInfo()) == null) ? null : callInfo.getCallSite();
            FirFunctionCall firFunctionCall = callSite instanceof FirFunctionCall ? (FirFunctionCall) callSite : null;
            if (firFunctionCall == null) {
                return false;
            }
            return isAnyOfDelegateOperators(firFunctionCall);
        }
        if (firElement instanceof FirFunctionCall) {
            FirFunctionCall firFunctionCall2 = (FirFunctionCall) firElement;
            if (firFunctionCall2.getOrigin() == FirFunctionCallOrigin.Operator) {
                Name name = firFunctionCall2.getCalleeReference().getName();
                return Intrinsics.areEqual(name, OperatorNameConventions.PROVIDE_DELEGATE) || Intrinsics.areEqual(name, OperatorNameConventions.GET_VALUE) || Intrinsics.areEqual(name, OperatorNameConventions.SET_VALUE);
            }
        }
        return false;
    }
}
