package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.TypeVariableAsExplicitReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.inference.FirDelegatedPropertyInferenceSessionKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0010H\u0002¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeVariablesInExplicitReceivers;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkOtherCases", "obtainTypeVariable", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeVariablesInExplicitReceivers extends ResolutionStage {
    public static final TypeVariablesInExplicitReceivers INSTANCE = new TypeVariablesInExplicitReceivers();

    private TypeVariablesInExplicitReceivers() {
    }

    private final void checkOtherCases(Candidate candidate) {
        ConeKotlinType resolvedType;
        ConeKotlinType resolvedType2;
        FirExpression firExpressionChosenExtensionReceiverExpression = candidate.chosenExtensionReceiverExpression();
        ConeTypeVariableType coneTypeVariableTypeObtainTypeVariable = null;
        if (((firExpressionChosenExtensionReceiverExpression == null || (resolvedType2 = FirTypeUtilsKt.getResolvedType(firExpressionChosenExtensionReceiverExpression)) == null) ? null : obtainTypeVariable(resolvedType2)) != null) {
            dt1.a("Found TV in extension receiver of ", candidate);
            return;
        }
        FirExpression firExpressionDispatchReceiverExpression = candidate.dispatchReceiverExpression();
        if (firExpressionDispatchReceiverExpression != null && (resolvedType = FirTypeUtilsKt.getResolvedType(firExpressionDispatchReceiverExpression)) != null) {
            coneTypeVariableTypeObtainTypeVariable = obtainTypeVariable(resolvedType);
        }
        if (coneTypeVariableTypeObtainTypeVariable == null) {
            return;
        }
        dt1.a("Found TV in dispatch receiver of ", candidate);
    }

    private final ConeTypeVariableType obtainTypeVariable(ConeKotlinType coneKotlinType) {
        if (coneKotlinType instanceof ConeFlexibleType) {
            return obtainTypeVariable(((ConeFlexibleType) coneKotlinType).getLowerBound());
        }
        if (coneKotlinType instanceof ConeTypeVariableType) {
            return (ConeTypeVariableType) coneKotlinType;
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return obtainTypeVariable(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            Iterator<T> it = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes().iterator();
            while (it.hasNext()) {
                ConeTypeVariableType coneTypeVariableTypeObtainTypeVariable = INSTANCE.obtainTypeVariable((ConeKotlinType) it.next());
                if (coneTypeVariableTypeObtainTypeVariable != null) {
                    return coneTypeVariableTypeObtainTypeVariable;
                }
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) {
        FirTypeParameterSymbol typeParameterSymbol;
        FirTypeParameter firTypeParameter;
        if (FirDelegatedPropertyInferenceSessionKt.isAnyOfDelegateOperators(candidate.getCallInfo().getCallSite())) {
            return Unit.INSTANCE;
        }
        FirExpression explicitReceiver = candidate.getCallInfo().getExplicitReceiver();
        if (explicitReceiver == null) {
            checkOtherCases(candidate);
            return Unit.INSTANCE;
        }
        ConeTypeVariableType coneTypeVariableTypeObtainTypeVariable = obtainTypeVariable(FirTypeUtilsKt.getResolvedType(explicitReceiver));
        if (coneTypeVariableTypeObtainTypeVariable == null) {
            checkOtherCases(candidate);
            return Unit.INSTANCE;
        }
        TypeParameterMarker originalTypeParameter = coneTypeVariableTypeObtainTypeVariable.getTypeConstructor().getOriginalTypeParameter();
        ConeTypeParameterLookupTag coneTypeParameterLookupTag = originalTypeParameter instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) originalTypeParameter : null;
        if (coneTypeParameterLookupTag == null || (typeParameterSymbol = coneTypeParameterLookupTag.getTypeParameterSymbol()) == null || (firTypeParameter = (FirTypeParameter) typeParameterSymbol.getFir()) == null) {
            checkOtherCases(candidate);
            return Unit.INSTANCE;
        }
        checkerSink.reportDiagnostic(new TypeVariableAsExplicitReceiver(explicitReceiver, firTypeParameter));
        return Unit.INSTANCE;
    }
}
