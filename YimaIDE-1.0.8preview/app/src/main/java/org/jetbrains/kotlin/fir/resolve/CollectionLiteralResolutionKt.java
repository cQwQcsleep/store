package org.jetbrains.kotlin.fir.resolve;

import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionKt;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionStrategy;
import org.jetbrains.kotlin.fir.resolve.calls.ConeAtomWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ConeCollectionLiteralAtom;
import org.jetbrains.kotlin.fir.resolve.calls.FirCallResolver;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallKind;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSinkImpl;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.ErrorCandidateUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirErrorReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.ImplicitInvokeMode;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ArgumentCheckingProcessor;
import org.jetbrains.kotlin.fir.resolve.inference.CollectionLiteralBounds;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.CollectionNames$Factories;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\n\u001a-\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u000e\u001a5\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0013\u001a5\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0007H\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0016\u001a!\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u001a\u001a>\u0010\u001b\u001a\u0004\u0018\u0001H\u001c\"\b\b\u0000\u0010\u001c*\u00020\u001d2\u0019\u0010\u001e\u001a\u0015\u0012\u0004\u0012\u00020 \u0012\u0006\u0012\u0004\u0018\u0001H\u001c0\u001f¢\u0006\u0002\b!R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\"¨\u0006#"}, d2 = {"runCollectionLiteralResolution", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralOuterCandidateContext;", "outerCandidateContext", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "precalculatedBounds", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralOuterCandidateContext;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;)V", "resolveCollectionLiteralToPreparedCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "preparedCall", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralOuterCandidateContext;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "resolveCollectionLiteralToErrorCall", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "collectionLiteralAtom", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralOuterCandidateContext;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;)Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "postprocessCollectionLiteralCall", "replacementForCL", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralOuterCandidateContext;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;)V", "prepareFunctionCallForFallback", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;)Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "tryAllCLResolutionStrategies", "T", Argument.Delimiters.none, "attempt", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralResolutionStrategy;", "Lkotlin/ExtensionFunctionType;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CollectionLiteralResolutionKt {
    public static FirFunctionCall a(FirCollectionLiteral firCollectionLiteral, FirRegularClassSymbol firRegularClassSymbol, ResolutionContext resolutionContext, CollectionLiteralOuterCandidateContext collectionLiteralOuterCandidateContext, CollectionLiteralResolutionStrategy collectionLiteralResolutionStrategy) {
        collectionLiteralResolutionStrategy.getClass();
        FirFunctionCall firFunctionCallPrepareRawCall = collectionLiteralResolutionStrategy.prepareRawCall(firCollectionLiteral, firRegularClassSymbol);
        if (firFunctionCallPrepareRawCall == null) {
            return null;
        }
        return resolveCollectionLiteralToPreparedCall(resolutionContext, collectionLiteralOuterCandidateContext, firFunctionCallPrepareRawCall);
    }

    private static final void postprocessCollectionLiteralCall(ResolutionContext resolutionContext, CollectionLiteralOuterCandidateContext collectionLiteralOuterCandidateContext, FirFunctionCall firFunctionCall, ConeCollectionLiteralAtom coneCollectionLiteralAtom) {
        Candidate candidate;
        FirCollectionLiteral expression = coneCollectionLiteralAtom.getExpression();
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        if (firNamedReferenceWithCandidate == null || (candidate = firNamedReferenceWithCandidate.getCandidate()) == null) {
            k2d.a("Collection literal is expected to be resolved to a call with named candidate.");
            return;
        }
        Candidate containingCandidate = collectionLiteralOuterCandidateContext.getContainingCandidate();
        coneCollectionLiteralAtom.setSubAtom(new ConeAtomWithCandidate(firFunctionCall, candidate));
        coneCollectionLiteralAtom.getContainingCallCandidate().setUpdatedCollectionLiteral(expression, firFunctionCall);
        ArgumentCheckingProcessor argumentCheckingProcessor = ArgumentCheckingProcessor.INSTANCE;
        ConeAtomWithCandidate subAtom = coneCollectionLiteralAtom.getSubAtom();
        subAtom.getClass();
        ConeKotlinType coneKotlinTypeMo581getExpectedType = coneCollectionLiteralAtom.mo581getExpectedType();
        CheckerSink checkerSink = collectionLiteralOuterCandidateContext.getCheckerSink();
        if (checkerSink == null) {
            checkerSink = new CheckerSinkImpl(containingCandidate, null, false, 6, null);
        }
        argumentCheckingProcessor.resolveArgumentExpression(candidate, subAtom, coneKotlinTypeMo581getExpectedType, checkerSink, resolutionContext, false, false, (128 & 128) != 0 ? null : null);
        resolutionContext.getBodyResolveComponents().getResolutionStageRunner().processCandidate(candidate, resolutionContext, false, true);
        CollectionLiteralResolutionUtilsKt.updateCalleeReferenceWithNewErrorsIfNeeded(resolutionContext, firFunctionCall, candidate);
        CheckerSink checkerSink2 = collectionLiteralOuterCandidateContext.getCheckerSink();
        if (checkerSink2 != null) {
            CollectionLiteralResolutionUtilsKt.remapResolutionDiagnosticsToOuterCandidate(candidate, checkerSink2);
        }
        containingCandidate.getSystem().replaceContentWith(candidate.getSystem().currentStorage());
    }

    private static final FirFunctionCall prepareFunctionCallForFallback(ResolutionContext resolutionContext, FirCollectionLiteral firCollectionLiteral) {
        return CollectionLiteralResolutionUtilsKt.buildCollectionLiteralCallForStdlibType(resolutionContext.getBodyResolveComponents(), StandardNames.COLLECTIONS_PACKAGE_FQ_NAME, CollectionNames$Factories.INSTANCE.getLIST_OF(), firCollectionLiteral);
    }

    private static final FirFunctionCall resolveCollectionLiteralToErrorCall(ResolutionContext resolutionContext, CollectionLiteralOuterCandidateContext collectionLiteralOuterCandidateContext, ConeDiagnostic coneDiagnostic, ConeCollectionLiteralAtom coneCollectionLiteralAtom) {
        BodyResolveComponents bodyResolveComponents = resolutionContext.getBodyResolveComponents();
        FirCollectionLiteral expression = coneCollectionLiteralAtom.getExpression();
        CallKind.CollectionLiteral collectionLiteral = CallKind.CollectionLiteral.INSTANCE;
        Name name = OperatorNameConventions.OF;
        FirArgumentList argumentList = expression.getArgumentList();
        List listEmptyList = CollectionsKt.emptyList();
        FirSession session = resolutionContext.getSession();
        FirFile file = bodyResolveComponents.getFile();
        List<FirDeclaration> containingDeclarations = bodyResolveComponents.getContainingDeclarations();
        ResolutionMode.ContextDependent.Companion companion = ResolutionMode.ContextDependent.INSTANCE;
        FirFunctionCallOrigin firFunctionCallOrigin = FirFunctionCallOrigin.Operator;
        CallInfo callInfo = new CallInfo(expression, collectionLiteral, name, null, argumentList, false, listEmptyList, session, file, containingDeclarations, null, companion, firFunctionCallOrigin, ImplicitInvokeMode.None, collectionLiteralOuterCandidateContext.getContainingCandidate(), 1024, null);
        KtSourceElement source = coneCollectionLiteralAtom.getExpression().getSource();
        FirErrorReferenceWithCandidate firErrorReferenceWithCandidateCreateErrorReferenceWithErrorCandidate = ErrorCandidateUtilsKt.createErrorReferenceWithErrorCandidate(callInfo, coneDiagnostic, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.CalleeReferenceForOperatorOfCall.INSTANCE, null, 2, null) : null, resolutionContext, bodyResolveComponents.getResolutionStageRunner());
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.getAnnotations().addAll(expression.getAnnotations());
        firFunctionCallBuilder.setSource(expression.getSource());
        firFunctionCallBuilder.setArgumentList(expression.getArgumentList());
        firFunctionCallBuilder.setCalleeReference(firErrorReferenceWithCandidateCreateErrorReferenceWithErrorCandidate);
        firFunctionCallBuilder.setOrigin(firFunctionCallOrigin);
        return (FirFunctionCall) bodyResolveComponents.getCallCompleter().completeCall(firFunctionCallBuilder.mo288build(), companion, true);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private static final FirFunctionCall resolveCollectionLiteralToPreparedCall(ResolutionContext resolutionContext, CollectionLiteralOuterCandidateContext collectionLiteralOuterCandidateContext, FirFunctionCall firFunctionCall) throws UninitializedPropertyAccessException {
        FirCallResolver callResolver = resolutionContext.getBodyResolveComponents().getCallResolver();
        ResolutionMode.ContextDependent.Companion companion = ResolutionMode.ContextDependent.INSTANCE;
        return (FirFunctionCall) resolutionContext.getBodyResolveComponents().getCallCompleter().completeCall(callResolver.resolveCallAndSelectCandidate(firFunctionCall, companion, collectionLiteralOuterCandidateContext), companion, true);
    }

    public static final void runCollectionLiteralResolution(final ResolutionContext resolutionContext, final CollectionLiteralOuterCandidateContext collectionLiteralOuterCandidateContext, ConeCollectionLiteralAtom coneCollectionLiteralAtom, CollectionLiteralBounds collectionLiteralBounds) {
        final FirRegularClassSymbol classRepresentativeForCollectionLiteralResolution;
        ConeKotlinType coneKotlinTypeMo581getExpectedType;
        resolutionContext.getClass();
        collectionLiteralOuterCandidateContext.getClass();
        coneCollectionLiteralAtom.getClass();
        final FirCollectionLiteral expression = coneCollectionLiteralAtom.getExpression();
        if (collectionLiteralBounds instanceof CollectionLiteralBounds.SingleBound) {
            classRepresentativeForCollectionLiteralResolution = ((CollectionLiteralBounds.SingleBound) collectionLiteralBounds).getBound();
        } else if (collectionLiteralBounds instanceof CollectionLiteralBounds.NonTvExpected) {
            classRepresentativeForCollectionLiteralResolution = ((CollectionLiteralBounds.NonTvExpected) collectionLiteralBounds).getBound();
        } else {
            classRepresentativeForCollectionLiteralResolution = null;
            if (collectionLiteralBounds == null && (coneKotlinTypeMo581getExpectedType = coneCollectionLiteralAtom.mo581getExpectedType()) != null) {
                classRepresentativeForCollectionLiteralResolution = CollectionLiteralResolutionUtilsKt.getClassRepresentativeForCollectionLiteralResolution(resolutionContext, coneKotlinTypeMo581getExpectedType);
            }
        }
        FirFunctionCall firFunctionCallResolveCollectionLiteralToErrorCall = (FirFunctionCall) tryAllCLResolutionStrategies(resolutionContext, new Function1() { // from class: r52
            public final Object invoke(Object obj) {
                return CollectionLiteralResolutionKt.a(expression, classRepresentativeForCollectionLiteralResolution, resolutionContext, collectionLiteralOuterCandidateContext, (CollectionLiteralResolutionStrategy) obj);
            }
        });
        if (firFunctionCallResolveCollectionLiteralToErrorCall == null) {
            firFunctionCallResolveCollectionLiteralToErrorCall = collectionLiteralBounds instanceof CollectionLiteralBounds.Ambiguity ? resolveCollectionLiteralToErrorCall(resolutionContext, collectionLiteralOuterCandidateContext, CollectionLiteralResolutionUtilsKt.toConeDiagnostic(collectionLiteralBounds), coneCollectionLiteralAtom) : resolveCollectionLiteralToPreparedCall(resolutionContext, collectionLiteralOuterCandidateContext, prepareFunctionCallForFallback(resolutionContext, expression));
        }
        postprocessCollectionLiteralCall(resolutionContext, collectionLiteralOuterCandidateContext, firFunctionCallResolveCollectionLiteralToErrorCall, coneCollectionLiteralAtom);
    }

    public static final <T> T tryAllCLResolutionStrategies(ResolutionContext resolutionContext, Function1<? super CollectionLiteralResolutionStrategy, ? extends T> function1) {
        resolutionContext.getClass();
        function1.getClass();
        T t = (T) function1.invoke(new CollectionLiteralResolutionStrategyThroughCompanion(resolutionContext));
        return t != null ? t : (T) function1.invoke(new CollectionLiteralResolutionStrategyForStdlibType(resolutionContext));
    }
}
