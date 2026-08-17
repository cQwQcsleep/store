package org.jetbrains.kotlin.fir.resolve.calls.stages;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableNullableReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableWrongReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.ResolvedWithLowPriority;
import org.jetbrains.kotlin.fir.resolve.calls.UnstableSmartCast;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirUnstableSmartcastTypeScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.types.AbstractNullabilityChecker;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckDispatchReceiver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckDispatchReceiver extends ResolutionStage {
    public static final CheckDispatchReceiver INSTANCE = new CheckDispatchReceiver();

    private CheckDispatchReceiver() {
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirExpression expression;
        ConeKotlinType resolvedType;
        ConeSimpleKotlinType dispatchReceiverType;
        FirAnnotationContainer explicitReceiver = candidate.getCallInfo().getExplicitReceiver();
        ConeKotlinType coneType = null;
        if (explicitReceiver instanceof FirSuperReceiverExpression) {
            FirDeclaration fir = candidate.getSymbol().getFir();
            FirMemberDeclaration firMemberDeclaration = fir instanceof FirMemberDeclaration ? (FirMemberDeclaration) fir : null;
            if ((firMemberDeclaration != null ? firMemberDeclaration.getStatus().getModality() : null) == Modality.ABSTRACT) {
                checkerSink.reportDiagnostic(ResolvedWithLowPriority.INSTANCE);
            }
        }
        ConeResolutionAtom dispatchReceiver = candidate.getDispatchReceiver();
        if (dispatchReceiver == null || (expression = dispatchReceiver.getExpression()) == null || (resolvedType = FirTypeUtilsKt.getResolvedType(expression)) == null) {
            return Unit.INSTANCE;
        }
        boolean zIsSubtypeOfAny = AbstractNullabilityChecker.INSTANCE.isSubtypeOfAny(TypeComponentsKt.getTypeContext(resolutionContext.getSession()), resolvedType);
        FirScope originScope = candidate.getOriginScope();
        FirUnstableSmartcastTypeScope firUnstableSmartcastTypeScope = originScope instanceof FirUnstableSmartcastTypeScope ? (FirUnstableSmartcastTypeScope) originScope : null;
        boolean z = false;
        if (firUnstableSmartcastTypeScope != null && firUnstableSmartcastTypeScope.isSymbolFromUnstableSmartcast(candidate.getSymbol())) {
            z = true;
        }
        if (explicitReceiver instanceof FirCheckNotNullCall) {
            explicitReceiver = (FirExpression) CollectionsKt.first(((FirCall) explicitReceiver).getArgumentList().getArguments());
        }
        FirSmartCastExpression firSmartCastExpression = explicitReceiver instanceof FirSmartCastExpression ? (FirSmartCastExpression) explicitReceiver : null;
        if (firSmartCastExpression == null || firSmartCastExpression.isStable() || (!z && (zIsSubtypeOfAny || TypeUtilsKt.canBeNull$default(FirTypeUtilsKt.getConeType(firSmartCastExpression.getSmartcastType()), candidate.getCallInfo().getSession(), false, null, 6, null)))) {
            if (!zIsSubtypeOfAny) {
                checkerSink.reportDiagnostic(new InapplicableNullableReceiver(resolvedType));
                return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
            }
            if (!z) {
                return Unit.INSTANCE;
            }
            checkerSink.reportDiagnostic(new InapplicableWrongReceiver(null, resolvedType, 1, null));
            return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
        }
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
        if (firCallableSymbol != null && (dispatchReceiverType = firCallableSymbol.getDispatchReceiverType()) != null && (coneType = TypeComponentsKt.getTypeApproximator(resolutionContext.getSession()).approximateToSuperType(dispatchReceiverType, TypeApproximatorConfiguration.FinalApproximationAfterResolutionAndInference.INSTANCE)) == null) {
            coneType = dispatchReceiverType;
        }
        if (coneType == null) {
            coneType = FirTypeUtilsKt.getConeType(firSmartCastExpression.getSmartcastType());
        }
        checkerSink.reportDiagnostic(new UnstableSmartCast(firSmartCastExpression, coneType, InferenceUtilsKt.isTypeMismatchDueToNullability(TypeComponentsKt.getTypeContext(resolutionContext.getSession()), FirTypeUtilsKt.getResolvedType(firSmartCastExpression.getOriginalExpression()), coneType), candidate.getCallInfo().isImplicitInvoke()));
        return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
    }
}
