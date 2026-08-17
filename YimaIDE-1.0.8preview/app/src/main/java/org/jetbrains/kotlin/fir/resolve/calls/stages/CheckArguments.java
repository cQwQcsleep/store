package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirInaccessibleReceiverExpression;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.transformers.PhaseUtilsKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJK\u0010\r\u001a\u00020\u0005*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckArguments;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveArgument", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "isReceiver", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;Z)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckArguments extends ResolutionStage {
    public static final CheckArguments INSTANCE = new CheckArguments();

    private CheckArguments() {
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void resolveArgument(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, CallInfo callInfo, ConeResolutionAtom coneResolutionAtom, FirValueParameter firValueParameter, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirExpression expression = coneResolutionAtom.getExpression();
        PhaseUtilsKt.ensureResolvedTypeDeclaration$default(expression.getConeTypeOrNull(), resolutionContext.getSession(), (FirResolvePhase) null, 2, (Object) null);
        ArgumentCheckingProcessor.INSTANCE.resolveArgumentExpression(candidate, coneResolutionAtom, CheckArgumentsKt.prepareExpectedType(resolutionContext, candidate, callInfo, expression, firValueParameter), checkerSink, resolutionContext, z, false, (128 & 128) != 0 ? null : null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirRegularClassSymbol regularClassSymbol;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(candidate.getSymbol(), FirResolvePhase.STATUS);
        LinkedHashMap<ConeResolutionAtom, FirValueParameter> argumentMapping = candidate.getArgumentMapping();
        boolean zIsInvokeFromExtensionFunctionType = ResolutionStagesKt.isInvokeFromExtensionFunctionType(candidate);
        Integer expectedContextParameterCountForInvoke = candidate.getExpectedContextParameterCountForInvoke();
        int iIntValue = expectedContextParameterCountForInvoke != null ? expectedContextParameterCountForInvoke.intValue() : 0;
        int i = 0;
        for (ConeResolutionAtom coneResolutionAtom : candidate.getArguments()) {
            int i2 = i + 1;
            if (i >= iIntValue) {
                FirExpression expression = coneResolutionAtom.getExpression();
                if (ArgumentUtilsKt.isInaccessibleAndInapplicable(expression)) {
                    checkerSink.reportDiagnostic(ArgumentUtilsKt.toInaccessibleReceiverDiagnostic((FirInaccessibleReceiverExpression) expression));
                }
                resolveArgument(checkerSink, resolutionContext, candidate, candidate.getCallInfo(), coneResolutionAtom, argumentMapping.get(coneResolutionAtom), i == 0 && zIsInvokeFromExtensionFunctionType);
            }
            i = i2;
        }
        if (candidate.getSystem().getHasContradiction() && !candidate.getCallInfo().getArguments().isEmpty()) {
            checkerSink.reportDiagnostic(InapplicableCandidate.INSTANCE);
            return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
        }
        if (ResolutionStagesKt.shouldHaveLowPriorityDueToSAM(candidate, resolutionContext.getBodyResolveComponents())) {
            Collection<FirValueParameter> collectionValues = argumentMapping.values();
            collectionValues.getClass();
            Collection<FirValueParameter> collection = collectionValues;
            if (!collection.isEmpty()) {
                Iterator<T> it = collection.iterator();
                while (it.hasNext()) {
                    ConeKotlinType coneType = FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef());
                    if (resolutionContext.getBodyResolveComponents().getSamResolver().isSamType(coneType) && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(resolutionContext, coneType)) != null) {
                        FirDeclarationOrigin origin = regularClassSymbol.getOrigin();
                        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
                            E fir = regularClassSymbol.getFir();
                            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
                            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                            }
                        }
                        CheckArgumentsKt.markCandidateForCompatibilityResolve(resolutionContext, checkerSink);
                        break;
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }
}
