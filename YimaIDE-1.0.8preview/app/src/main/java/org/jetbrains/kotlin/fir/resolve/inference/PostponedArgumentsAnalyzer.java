package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.builder.FirErrorNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralOuterCandidateContext;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionKt;
import org.jetbrains.kotlin.fir.resolve.ContextSensitiveResolutionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeCollectionLiteralAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeContextSensitiveAlternativeForQualifierAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeLambdaWithTypeVariableAsExpectedTypeAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithPostponedChild;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedCallableReferenceAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeSimpleNameForContextSensitiveResolution;
import org.jetbrains.kotlin.fir.resolve.calls.FirCallResolver;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.UnitReturnTypeLambdaContradictsExpectedType;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSinkImpl;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirErrorReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ArgumentCheckingProcessor;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphBuilderKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedReferenceError;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeLambdaArgumentConstraintPositionWithCoercionToUnit;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.resolve.calls.components.PostponedArgumentsAnalyzerContext;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilder;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilderKt;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintStorage;
import org.jetbrains.kotlin.types.model.MarkerExtensionsKt;
import org.jetbrains.kotlin.types.model.TypeSubstitutorMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContextContextualKt;
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContextContextualKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ0\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0017H\u0002J\u0018\u0010\"\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020#2\u0006\u0010!\u001a\u00020\u0017H\u0002J \u0010$\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0002J\"\u0010'\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020(2\u0006\u0010!\u001a\u00020\u00172\b\u0010)\u001a\u0004\u0018\u00010\u001bH\u0002J8\u0010*\u001a\u00020+2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010/\u001a\u00020\u0019J\u0014\u00100\u001a\u00020\u0019*\u00020&2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002JB\u00101\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00102\u001a\u00020+2\u0006\u00103\u001a\u00020\u00192\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&05J<\u00106\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u00107\u001a\u0002082\u0006\u0010,\u001a\u00020-2\u0006\u00109\u001a\u00020:2\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&05H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/PostponedArgumentsAnalyzer;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "resolutionContext", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "lambdaAnalyzer", "Lorg/jetbrains/kotlin/fir/resolve/inference/LambdaAnalyzer;", "components", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "callResolver", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/inference/LambdaAnalyzer;Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "analyze", Argument.Delimiters.none, "c", "Lorg/jetbrains/kotlin/resolve/calls/components/PostponedArgumentsAnalyzerContext;", "argument", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "withPCLASession", Argument.Delimiters.none, "precalculatedBoundsForCL", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "processCallableReference", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedCallableReferenceAtom;", "processSimpleNameForContextSensitiveResolution", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeSimpleNameForContextSensitiveResolution;", "topLevelCandidate", "processSimpleNameForContextSensitiveResolutionIdeAlternative", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeContextSensitiveAlternativeForQualifierAtom;", "runContextSensitiveResolutionAndApplyResultsIfSuccessful", "substitutedExpectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "processCollectionLiteral", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "precalculatedBounds", "analyzeLambda", "Lorg/jetbrains/kotlin/fir/resolve/inference/ReturnArgumentsAnalysisResult;", "lambda", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "forOverloadByLambdaReturnType", "allowFixationToOtherTypeVariables", "willEventuallyBecomeUnit", "applyResultsOfAnalyzedLambdaToCandidateSystem", "results", "forEagerLambdaAnalysis", "substituteAlreadyFixedVariables", "Lkotlin/Function1;", "addLambdaReturnTypeUnitConstraintOrReportError", "builder", "Lorg/jetbrains/kotlin/resolve/calls/inference/ConstraintSystemBuilder;", "checkerSink", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PostponedArgumentsAnalyzer implements SessionHolder {
    private final FirCallResolver callResolver;
    private final InferenceComponents components;
    private final LambdaAnalyzer lambdaAnalyzer;
    private final ResolutionContext resolutionContext;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.inference.PostponedArgumentsAnalyzer$analyzeLambda$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<ConeKotlinType, ConeKotlinType> {
        final /* synthetic */ PostponedArgumentsAnalyzerContext $c;
        final /* synthetic */ TypeSubstitutorMarker $currentSubstitutor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TypeSubstitutorMarker typeSubstitutorMarker, PostponedArgumentsAnalyzerContext postponedArgumentsAnalyzerContext) {
            super(1, Intrinsics.Kotlin.class, "substitute", "analyzeLambda$substitute(Lorg/jetbrains/kotlin/types/model/TypeSubstitutorMarker;Lorg/jetbrains/kotlin/resolve/calls/components/PostponedArgumentsAnalyzerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", 0);
            this.$currentSubstitutor = typeSubstitutorMarker;
            this.$c = postponedArgumentsAnalyzerContext;
        }

        public final ConeKotlinType invoke(ConeKotlinType coneKotlinType) {
            coneKotlinType.getClass();
            return PostponedArgumentsAnalyzer.analyzeLambda$substitute(this.$currentSubstitutor, this.$c, coneKotlinType);
        }
    }

    public PostponedArgumentsAnalyzer(ResolutionContext resolutionContext, LambdaAnalyzer lambdaAnalyzer, InferenceComponents inferenceComponents, FirCallResolver firCallResolver) {
        resolutionContext.getClass();
        lambdaAnalyzer.getClass();
        inferenceComponents.getClass();
        firCallResolver.getClass();
        this.resolutionContext = resolutionContext;
        this.lambdaAnalyzer = lambdaAnalyzer;
        this.components = inferenceComponents;
        this.callResolver = firCallResolver;
    }

    private final void addLambdaReturnTypeUnitConstraintOrReportError(PostponedArgumentsAnalyzerContext c, ConstraintSystemBuilder builder, ConeResolvedLambdaAtom lambda, CheckerSink checkerSink, Function1<? super ConeKotlinType, ? extends ConeKotlinType> substituteAlreadyFixedVariables) {
        ConeKotlinType coneKotlinType = (ConeKotlinType) substituteAlreadyFixedVariables.invoke(lambda.getReturnType());
        if (c.isError(coneKotlinType) || builder.getHasContradiction()) {
            return;
        }
        ConeLambdaArgumentConstraintPositionWithCoercionToUnit coneLambdaArgumentConstraintPositionWithCoercionToUnit = new ConeLambdaArgumentConstraintPositionWithCoercionToUnit(lambda.getAnonymousFunction(), null);
        ConeClassLikeType coneType = this.components.getSession().getBuiltinTypes().getUnitType().getConeType();
        if (ConstraintSystemBuilderKt.addSubtypeConstraintIfCompatible(builder, coneType, coneKotlinType, coneLambdaArgumentConstraintPositionWithCoercionToUnit)) {
            return;
        }
        ConeKotlinType coneKotlinTypeMo581getExpectedType = lambda.mo581getExpectedType();
        ConeKotlinType coneKotlinType2 = coneKotlinTypeMo581getExpectedType != null ? (ConeKotlinType) substituteAlreadyFixedVariables.invoke(coneKotlinTypeMo581getExpectedType) : null;
        if (coneKotlinType2 != null) {
            checkerSink.reportDiagnostic(new UnitReturnTypeLambdaContradictsExpectedType(lambda.getAnonymousFunction(), coneKotlinType2, lambda.getSourceForFunctionExpression()));
        } else {
            builder.addSubtypeConstraint(coneType, coneKotlinType, coneLambdaArgumentConstraintPositionWithCoercionToUnit);
        }
    }

    public static /* synthetic */ ReturnArgumentsAnalysisResult analyzeLambda$default(PostponedArgumentsAnalyzer postponedArgumentsAnalyzer, PostponedArgumentsAnalyzerContext postponedArgumentsAnalyzerContext, ConeResolvedLambdaAtom coneResolvedLambdaAtom, Candidate candidate, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 32) != 0) {
            z3 = false;
        }
        return postponedArgumentsAnalyzer.analyzeLambda(postponedArgumentsAnalyzerContext, coneResolvedLambdaAtom, candidate, z, z2, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeKotlinType analyzeLambda$substitute(TypeSubstitutorMarker typeSubstitutorMarker, PostponedArgumentsAnalyzerContext postponedArgumentsAnalyzerContext, ConeKotlinType coneKotlinType) {
        return MarkerExtensionsKt.safeSubstitute(typeSubstitutorMarker, postponedArgumentsAnalyzerContext, coneKotlinType);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final void processCallableReference(ConeResolvedCallableReferenceAtom atom, Candidate candidate) throws UninitializedPropertyAccessException {
        if (atom.getNeedsResolution()) {
            ConeResolvedCallableReferenceAtom.State state = atom.getState();
            this.callResolver.resolveCallableReference(candidate, atom, false);
            if (atom.isPostponedBecauseOfAmbiguity() && FirLanguageSettingsComponentKt.getLanguageVersionSettings(candidate.getCallInfo().getSession()).supportsFeature(LanguageFeature.CallableReferenceOverloadResolutionInLambda)) {
                if (state == ConeResolvedCallableReferenceAtom.State.NOT_RESOLVED_YET) {
                    return;
                }
                k2d.a("Check failed.");
                return;
            }
        }
        FirCallableReferenceAccess expression = atom.getExpression();
        atom.setAnalyzed(true);
        this.resolutionContext.getBodyResolveContext().dropCallableReferenceContext(expression);
        FirNamedReference resultingReference = atom.getResultingReference();
        if (resultingReference == null) {
            FirErrorNamedReferenceBuilder firErrorNamedReferenceBuilder = new FirErrorNamedReferenceBuilder();
            firErrorNamedReferenceBuilder.setSource(expression.getSource());
            firErrorNamedReferenceBuilder.setDiagnostic(new ConeUnresolvedReferenceError(expression.getCalleeReference().getName()));
            firErrorNamedReferenceBuilder.setName(expression.getCalleeReference().getName());
            resultingReference = firErrorNamedReferenceBuilder.build();
        }
        expression.replaceCalleeReference(resultingReference);
        ConeKotlinType resultingTypeForCallableReference = atom.getResultingTypeForCallableReference();
        if (resultingTypeForCallableReference == null) {
            resultingTypeForCallableReference = resultingReference instanceof FirErrorReferenceWithCandidate ? new ConeErrorType(((FirErrorReferenceWithCandidate) resultingReference).getDiagnostic(), false, null, null, null, null, null, 126, null) : new ConeErrorType(new ConeUnresolvedReferenceError(expression.getCalleeReference().getName()), false, null, null, null, null, null, 126, null);
        }
        expression.replaceConeTypeOrNull(resultingTypeForCallableReference);
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(this.resolutionContext.getSession());
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, resultingTypeForCallableReference, expression.getSource(), this.resolutionContext.getBodyResolveComponents().getFile().getSource());
        }
    }

    private final void processCollectionLiteral(ConeCollectionLiteralAtom atom, Candidate topLevelCandidate, CollectionLiteralBounds precalculatedBounds) {
        atom.setAnalyzed(true);
        CollectionLiteralResolutionKt.runCollectionLiteralResolution(this.resolutionContext, new CollectionLiteralOuterCandidateContext(topLevelCandidate, null, 2, null), atom, precalculatedBounds);
    }

    private final void processSimpleNameForContextSensitiveResolution(ConeSimpleNameForContextSensitiveResolution atom, Candidate topLevelCandidate) {
        atom.setAnalyzed(true);
        ConeKotlinType coneKotlinType = (ConeKotlinType) MarkerExtensionsKt.safeSubstitute((ConeSubstitutor) ConstraintSystemCompleterKt.getCsBuilder(topLevelCandidate).buildCurrentSubstitutor(MapsKt.emptyMap()), ConstraintSystemCompleterKt.getCsBuilder(topLevelCandidate), atom.mo581getExpectedType());
        if (runContextSensitiveResolutionAndApplyResultsIfSuccessful(atom, topLevelCandidate, coneKotlinType)) {
            return;
        }
        ArgumentCheckingProcessor.INSTANCE.resolveArgumentExpression(topLevelCandidate, atom.getFallbackSubAtom(), coneKotlinType, new CheckerSinkImpl(topLevelCandidate, null, false, 6, null), this.resolutionContext, false, false, (128 & 128) != 0 ? null : null);
    }

    private final void processSimpleNameForContextSensitiveResolutionIdeAlternative(ConeContextSensitiveAlternativeForQualifierAtom atom, Candidate topLevelCandidate) {
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        if (atom.getAnalyzed()) {
            k2d.a("Check failed.");
            return;
        }
        if (TypeSystemInferenceExtensionContextContextualKt.isTypeVariable(typeContext, TypeSystemContextContextualKt.typeConstructor(typeContext, atom.mo581getExpectedType()))) {
            atom.markDiscarded();
            return;
        }
        atom.setAnalyzed(true);
        ContextSensitiveResolutionUtilsKt.appendCSRAlternativeDiagnosticIfNeeded(atom.getOriginalExpression(), ContextSensitiveResolutionUtilsKt.runContextSensitiveResolutionForPropertyAccess(this.resolutionContext.getBodyResolveComponents(), atom.getAlternative(), MarkerExtensionsKt.safeSubstitute((ConeSubstitutor) ConstraintSystemCompleterKt.getCsBuilder(topLevelCandidate).buildCurrentSubstitutor(MapsKt.emptyMap()), ConstraintSystemCompleterKt.getCsBuilder(topLevelCandidate), atom.mo581getExpectedType())));
        atom.getOriginalExpression().replaceContextSensitiveAlternative(null);
    }

    private final boolean runContextSensitiveResolutionAndApplyResultsIfSuccessful(ConeSimpleNameForContextSensitiveResolution atom, Candidate topLevelCandidate, ConeKotlinType substitutedExpectedType) {
        FirPropertyAccessExpression expression = atom.getExpression();
        FirExpression firExpressionRunContextSensitiveResolutionForPropertyAccess = ContextSensitiveResolutionUtilsKt.runContextSensitiveResolutionForPropertyAccess(this.resolutionContext.getBodyResolveComponents(), expression, substitutedExpectedType);
        if (firExpressionRunContextSensitiveResolutionForPropertyAccess == null) {
            return false;
        }
        atom.getContainingCallCandidate().setUpdatedArgumentFromContextSensitiveResolution(expression, firExpressionRunContextSensitiveResolutionForPropertyAccess);
        ArgumentCheckingProcessor.INSTANCE.resolveArgumentExpression(topLevelCandidate, ConeResolutionAtom.INSTANCE.createRawAtom(firExpressionRunContextSensitiveResolutionForPropertyAccess), substitutedExpectedType, new CheckerSinkImpl(topLevelCandidate, null, false, 6, null), this.resolutionContext, false, false, (128 & 128) != 0 ? null : null);
        return true;
    }

    private final boolean willEventuallyBecomeUnit(ConeKotlinType coneKotlinType, PostponedArgumentsAnalyzerContext postponedArgumentsAnalyzerContext) {
        return !ConeTypeUtilsKt.isMarkedNullable(coneKotlinType) && postponedArgumentsAnalyzerContext.hasUpperOrEqualUnitConstraint(coneKotlinType);
    }

    public final void analyze(PostponedArgumentsAnalyzerContext c, ConePostponedResolvedAtom argument, Candidate candidate, boolean withPCLASession, CollectionLiteralBounds precalculatedBoundsForCL) {
        c.getClass();
        argument.getClass();
        candidate.getClass();
        if (argument instanceof ConeResolvedLambdaAtom) {
            analyzeLambda$default(this, c, (ConeResolvedLambdaAtom) argument, candidate, false, withPCLASession, false, 32, null);
            return;
        }
        if (argument instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) {
            analyzeLambda$default(this, c, PostponedArgumentsAnalyzerKt.transformToResolvedLambda$default((ConeLambdaWithTypeVariableAsExpectedTypeAtom) argument, c.getBuilder(), this.resolutionContext, null, null, 12, null), candidate, false, withPCLASession, false, 32, null);
            return;
        }
        if (argument instanceof ConeResolvedCallableReferenceAtom) {
            processCallableReference((ConeResolvedCallableReferenceAtom) argument, candidate);
            return;
        }
        if (argument instanceof ConeSimpleNameForContextSensitiveResolution) {
            processSimpleNameForContextSensitiveResolution((ConeSimpleNameForContextSensitiveResolution) argument, candidate);
            return;
        }
        if (argument instanceof ConeContextSensitiveAlternativeForQualifierAtom) {
            processSimpleNameForContextSensitiveResolutionIdeAlternative((ConeContextSensitiveAlternativeForQualifierAtom) argument, candidate);
        } else if (argument instanceof ConeCollectionLiteralAtom) {
            processCollectionLiteral((ConeCollectionLiteralAtom) argument, candidate, precalculatedBoundsForCL);
        } else {
            bu8.a();
        }
    }

    public final ReturnArgumentsAnalysisResult analyzeLambda(PostponedArgumentsAnalyzerContext c, ConeResolvedLambdaAtom lambda, Candidate candidate, boolean forOverloadByLambdaReturnType, boolean withPCLASession, boolean allowFixationToOtherTypeVariables) {
        ConeKotlinType coneKotlinType;
        c.getClass();
        lambda.getClass();
        candidate.getClass();
        ConeKotlinType coneKotlinTypeAnalyzeLambda$substitute = null;
        if (lambda.getAnalyzed()) {
            return new ReturnArgumentsAnalysisResult(lambda.getReturnStatements(), null);
        }
        FirInferenceSession inferenceSession = this.resolutionContext.getBodyResolveContext().getInferenceSession();
        if ((inferenceSession instanceof FirPCLAInferenceSession) && lambda.getReceiverType() != null) {
            ((FirPCLAInferenceSession) inferenceSession).semiFixCurrentResultIfTypeVariableAndReturnBinding(lambda.getReceiverType(), candidate.getSystem(), allowFixationToOtherTypeVariables);
        }
        ConeClassLikeType coneType = this.components.getSession().getBuiltinTypes().getUnitType().getConeType();
        TypeSubstitutorMarker typeSubstitutorMarkerBuildCurrentSubstitutor = c.buildCurrentSubstitutor(inferenceSession.getSemiFixedVariables());
        ConeKotlinType receiverType = lambda.getReceiverType();
        ConeKotlinType coneKotlinTypeAnalyzeLambda$substitute2 = receiverType != null ? analyzeLambda$substitute(typeSubstitutorMarkerBuildCurrentSubstitutor, c, receiverType) : null;
        List<ConeKotlinType> contextParameterTypes$org_jetbrains_kotlin_resolve = lambda.getContextParameterTypes$org_jetbrains_kotlin_resolve();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameterTypes$org_jetbrains_kotlin_resolve, 10));
        Iterator<T> it = contextParameterTypes$org_jetbrains_kotlin_resolve.iterator();
        while (it.hasNext()) {
            arrayList.add(analyzeLambda$substitute(typeSubstitutorMarkerBuildCurrentSubstitutor, c, (ConeKotlinType) it.next()));
        }
        List<ConeKotlinType> parameterTypes$org_jetbrains_kotlin_resolve = lambda.getParameterTypes$org_jetbrains_kotlin_resolve();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameterTypes$org_jetbrains_kotlin_resolve, 10));
        Iterator<T> it2 = parameterTypes$org_jetbrains_kotlin_resolve.iterator();
        while (it2.hasNext()) {
            arrayList2.add(analyzeLambda$substitute(typeSubstitutorMarkerBuildCurrentSubstitutor, c, (ConeKotlinType) it2.next()));
        }
        ConeKotlinType returnType = lambda.getReturnType();
        boolean z = forOverloadByLambdaReturnType && LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.EagerLambdaAnalysis);
        if (z) {
            coneKotlinType = coneKotlinTypeAnalyzeLambda$substitute;
        } else {
            if (c.canBeProper(returnType)) {
                coneKotlinTypeAnalyzeLambda$substitute = analyzeLambda$substitute(typeSubstitutorMarkerBuildCurrentSubstitutor, c, returnType);
            } else if (willEventuallyBecomeUnit(returnType, c)) {
                coneKotlinType = coneType;
            } else if (withPCLASession && LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.PCLAEnhancementsIn21)) {
                coneKotlinTypeAnalyzeLambda$substitute = analyzeLambda$substitute(typeSubstitutorMarkerBuildCurrentSubstitutor, c, returnType);
            }
            coneKotlinType = coneKotlinTypeAnalyzeLambda$substitute;
        }
        ReturnArgumentsAnalysisResult returnArgumentsAnalysisResultAnalyzeAndGetLambdaReturnArguments = this.lambdaAnalyzer.analyzeAndGetLambdaReturnArguments(lambda, coneKotlinTypeAnalyzeLambda$substitute2, arrayList, arrayList2, coneKotlinType, candidate, withPCLASession, forOverloadByLambdaReturnType);
        applyResultsOfAnalyzedLambdaToCandidateSystem(c, lambda, candidate, returnArgumentsAnalysisResultAnalyzeAndGetLambdaReturnArguments, z, new AnonymousClass1(typeSubstitutorMarkerBuildCurrentSubstitutor, c));
        return returnArgumentsAnalysisResultAnalyzeAndGetLambdaReturnArguments;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x0132  */
    public final void applyResultsOfAnalyzedLambdaToCandidateSystem(PostponedArgumentsAnalyzerContext c, ConeResolvedLambdaAtom lambda, Candidate candidate, ReturnArgumentsAnalysisResult results, boolean forEagerLambdaAnalysis, Function1<? super ConeKotlinType, ? extends ConeKotlinType> substituteAlreadyFixedVariables) {
        boolean z;
        boolean z2;
        c.getClass();
        lambda.getClass();
        candidate.getClass();
        results.getClass();
        substituteAlreadyFixedVariables.getClass();
        Collection<ConeResolutionAtom> collectionComponent1 = results.component1();
        ConstraintStorage additionalConstraints = results.getAdditionalConstraints();
        Collection<ConeResolutionAtom> collection = collectionComponent1;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((ConeResolutionAtom) it.next()).getExpression());
        }
        if (additionalConstraints != null) {
            c.mergeOtherSystem(additionalConstraints);
        }
        CheckerSinkImpl checkerSinkImpl = new CheckerSinkImpl(candidate, null, false, 6, null);
        ConstraintSystemBuilder builder = c.getBuilder();
        FirStatement firStatementLastStatement = ControlFlowGraphBuilderKt.lastStatement(lambda.getAnonymousFunction());
        FirExpression firExpression = firStatementLastStatement instanceof FirExpression ? (FirExpression) firStatementLastStatement : null;
        FirResolvedTypeRef returnTypeRef = lambda.getAnonymousFunction().getReturnTypeRef();
        FirResolvedTypeRef firResolvedTypeRefResolvedTypeFromPrototype = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
        if (firResolvedTypeRefResolvedTypeFromPrototype == null) {
            ConeKotlinType coneKotlinType = (ConeKotlinType) substituteAlreadyFixedVariables.invoke(lambda.getReturnType());
            KtSourceElement source = lambda.getAnonymousFunction().getSource();
            firResolvedTypeRefResolvedTypeFromPrototype = CopyUtilsKt.resolvedTypeFromPrototype(returnTypeRef, coneKotlinType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitFunctionReturnType.INSTANCE, null, 2, null) : null);
        }
        FirResolvedTypeRef firResolvedTypeRef = firResolvedTypeRefResolvedTypeFromPrototype;
        boolean z3 = false;
        boolean z4 = TypeUtilsKt.isUnitOrFlexibleUnit(firResolvedTypeRef.getConeType()) || willEventuallyBecomeUnit(firResolvedTypeRef.getConeType(), c) || ResolveUtilsKt.lambdaWithExplicitEmptyReturns(lambda.getAnonymousFunction(), arrayList);
        for (ConeResolutionAtom coneResolutionAtom : collectionComponent1) {
            FirExpression expression = coneResolutionAtom.getExpression();
            if (ResolveUtilsKt.isImplicitUnitForEmptyLambda(expression)) {
                firResolvedTypeRef = firResolvedTypeRef;
            } else {
                boolean zAreEqual = Intrinsics.areEqual(expression, firExpression);
                boolean zAddSubsystemFromAtom = CandidateFactoryKt.addSubsystemFromAtom(c, coneResolutionAtom);
                if (zAreEqual && z4) {
                    if (zAddSubsystemFromAtom) {
                        ConstraintSystemBuilderKt.addSubtypeConstraintIfCompatible(builder, FirTypeUtilsKt.getResolvedType(expression), firResolvedTypeRef.getConeType(), new ConeLambdaArgumentConstraintPositionWithCoercionToUnit(lambda.getAnonymousFunction(), expression));
                    }
                    if (!forEagerLambdaAnalysis) {
                        z2 = true;
                    } else if (!FirTypeUtilsKt.getHasResolvedType(expression) && !(coneResolutionAtom instanceof ConeResolutionAtomWithPostponedChild)) {
                        k2d.a("The only known case lambda return expression is not resolved is when it's a postponed atom, thus it's not Unit");
                        return;
                    } else if (FirTypeUtilsKt.getHasResolvedType(expression) && ConstraintSystemBuilderKt.isSubtypeConstraintCompatible(builder, FirTypeUtilsKt.getResolvedType(expression), getSession().getBuiltinTypes().getUnitType().getConeType())) {
                        z2 = true;
                    } else {
                        z2 = true;
                        candidate.setUsesCoercionToUnitInLambda(true);
                    }
                    firResolvedTypeRef = firResolvedTypeRef;
                } else {
                    if (!builder.getHasContradiction() || (coneResolutionAtom instanceof ConeResolutionAtomWithPostponedChild)) {
                        CheckerSinkImpl checkerSinkImpl2 = checkerSinkImpl;
                        z = true;
                        ArgumentCheckingProcessor.INSTANCE.resolveArgumentExpression(candidate, coneResolutionAtom, (ConeKotlinType) substituteAlreadyFixedVariables.invoke(lambda.getReturnType()), checkerSinkImpl2, this.resolutionContext, false, false, lambda.getAnonymousFunction());
                        checkerSinkImpl = checkerSinkImpl2;
                    } else {
                        z = true;
                    }
                    z3 = z;
                }
            }
            firResolvedTypeRef = firResolvedTypeRef;
        }
        if (!z3) {
            addLambdaReturnTypeUnitConstraintOrReportError(c, builder, lambda, checkerSinkImpl, substituteAlreadyFixedVariables);
        }
        lambda.setAnalyzed(true);
        lambda.setReturnStatements(collectionComponent1);
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.resolutionContext.getSession();
    }
}
