package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferReceiverParameterType;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferValueParameterType;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.resolve.FirRegularTowerDataContexts;
import org.jetbrains.kotlin.fir.resolve.FirTowerDataMode;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeAtomWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeSimpleLeafResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticFunctionSymbol;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FirAnonymousFunctionReturnExpressionInfo;
import org.jetbrains.kotlin.fir.resolve.inference.CollectionLiteralBounds;
import org.jetbrains.kotlin.fir.resolve.inference.FirCallCompleter;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeArgumentConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeExpectedTypeConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.FirCallCompletionResultsWriterTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.LambdaArgumentEffectsTransformerKt;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.SyntheticCallableId;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilderKt;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionMode;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintStorage;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.calls.inference.model.VariableWithConstraints;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.model.MarkerExtensionsKt;
import org.jetbrains.kotlin.types.model.TypeSubstitutorMarker;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001HB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J3\u0010\u0014\u001a\u0002H\u0015\"\f\b\u0000\u0010\u0015*\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u0002H\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c¢\u0006\u0002\u0010\u001dJ/\u0010\u001e\u001a\u0002H\u0015\"\f\b\u0000\u0010\u0015*\u00020\u0016*\u00020\u0017*\u0002H\u00152\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002¢\u0006\u0002\u0010#J\f\u0010$\u001a\u00020\u001c*\u00020 H\u0002J\f\u0010%\u001a\u00020\u001c*\u00020 H\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0002J \u0010*\u001a\u00020'2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010+\u001a\u00020,2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0014\u0010-\u001a\u00020\u001c*\u00020 2\u0006\u0010.\u001a\u00020,H\u0002J\f\u0010/\u001a\u00020\u001c*\u00020,H\u0002J\u0010\u00100\u001a\u00020\u001c*\u0006\u0012\u0002\b\u000301H\u0002J2\u00102\u001a\u00020'2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,2\n\b\u0002\u00103\u001a\u0004\u0018\u000104J\u0016\u00105\u001a\u00020'2\u0006\u00106\u001a\u0002072\u0006\u0010\u001f\u001a\u00020 J\u0018\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\b\b\u0002\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u0002042\u0006\u0010?\u001a\u00020@J&\u0010A\u001a\u00020,*\u00020,2\b\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010D\u001a\u00020\u001c2\u0006\u0010E\u001a\u00020 H\u0002J\u001c\u0010F\u001a\u00020\u001c*\u00020,2\u0006\u0010G\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020\u001cH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006I"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "transformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "components", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "inferenceSession", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;", "getInferenceSession", "()Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;", "completer", "Lorg/jetbrains/kotlin/fir/resolve/inference/ConstraintSystemCompleter;", "getCompleter", "()Lorg/jetbrains/kotlin/fir/resolve/inference/ConstraintSystemCompleter;", "completeCall", "T", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", K2JsArgumentConstants.CALL, "resolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "skipEvenPartialCompletion", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;Z)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "runningCompletionResultsWriterInNonFullModeIfNeeded", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "completionMode", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "isSyntheticCallForTopLevelLambda", "isSyntheticCallForTopLevelCollectionLiteral", "checkStorageConstraintsAfterFullCompletion", Argument.Delimiters.none, "storage", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", "addConstraintFromExpectedType", "initialType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isSyntheticFunctionCallThatShouldUseEqualityConstraint", "expectedType", "isUnitOrAnyWithArbitraryNullability", "isSyntheticElvisFunction", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "runCompletionForCall", "analyzer", "Lorg/jetbrains/kotlin/fir/resolve/inference/PostponedArgumentsAnalyzer;", "prepareLambdaAtomForFactoryPattern", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "createCompletionResultsWriter", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "mode", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer$Mode;", "createPostponedArgumentsAnalyzer", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "approximateLambdaInputType", "valueParameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "isRootLambdaForPCLASession", "containingCandidate", "useErrorTypeInsteadOfTypeVariableForParameterType", "isReceiver", "LambdaAnalyzerImpl", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallCompleter implements SessionHolder {
    private final ConstraintSystemCompleter completer;
    private final FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components;
    private final FirSession session;
    private final FirAbstractBodyResolveTransformerDispatcher transformer;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JX\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J*\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter$LambdaAnalyzerImpl;", "Lorg/jetbrains/kotlin/fir/resolve/inference/LambdaAnalyzer;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;)V", "analyzeAndGetLambdaReturnArguments", "Lorg/jetbrains/kotlin/fir/resolve/inference/ReturnArgumentsAnalysisResult;", "lambdaAtom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "receiverType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "contextParameters", Argument.Delimiters.none, "parameters", "expectedReturnType", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "withPCLASession", Argument.Delimiters.none, "forOverloadByLambdaReturnType", "setContextParametersConfiguration", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "givenContextParameterTypes", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class LambdaAnalyzerImpl implements LambdaAnalyzer {
        public LambdaAnalyzerImpl() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit analyzeAndGetLambdaReturnArguments$lambda$5$2(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirAnonymousFunctionExpression firAnonymousFunctionExpression, FirResolvedTypeRef firResolvedTypeRef) {
            firDeclarationsResolveTransformer.doTransformAnonymousFunctionBodyFromCallCompletion$org_jetbrains_kotlin_resolve(firAnonymousFunctionExpression, firResolvedTypeRef);
            return Unit.INSTANCE;
        }

        private final void setContextParametersConfiguration(FirAnonymousFunction firAnonymousFunction, List<? extends ConeKotlinType> list, boolean z, Candidate candidate) {
            if (list.isEmpty()) {
                return;
            }
            KtSourceElement source = firAnonymousFunction.getSource();
            if (firAnonymousFunction.getIsLambda()) {
                List<? extends ConeKotlinType> list2 = list;
                FirCallCompleter firCallCompleter = FirCallCompleter.this;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (ConeKotlinType coneKotlinType : list2) {
                    FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
                    firValueParameterBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
                    firValueParameterBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.LambdaContextParameter.INSTANCE, null, 2, null) : null);
                    firValueParameterBuilder.setContainingDeclarationSymbol(firAnonymousFunction.getSymbol());
                    firValueParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(firCallCompleter.getSession()));
                    firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                    firValueParameterBuilder.setName(SpecialNames.UNDERSCORE_FOR_UNUSED_VAR);
                    firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                    firValueParameterBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default(firCallCompleter.approximateLambdaInputType(coneKotlinType, firValueParameterBuilder.getSymbol(), z, candidate), source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.LambdaContextParameter.INSTANCE, null, 2, null) : null, null, 2, null));
                    firValueParameterBuilder.setValueParameterKind(FirValueParameterKind.ContextParameter);
                    arrayList.add(firValueParameterBuilder.mo288build());
                }
                firAnonymousFunction.replaceContextParameters(arrayList);
                return;
            }
            if (list.size() != firAnonymousFunction.getContextParameters().size()) {
                k2d.a("Check failed.");
                return;
            }
            List<FirValueParameter> contextParameters = firAnonymousFunction.getContextParameters();
            FirCallCompleter firCallCompleter2 = FirCallCompleter.this;
            int i = 0;
            for (Object obj : contextParameters) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                FirValueParameter firValueParameter = (FirValueParameter) obj;
                ConeKotlinType coneKotlinTypeApproximateLambdaInputType = firCallCompleter2.approximateLambdaInputType(list.get(i), firValueParameter.getSymbol(), z, candidate);
                KtSourceElement source2 = firValueParameter.getReturnTypeRef().getSource();
                if (source2 == null) {
                    KtSourceElement source3 = firValueParameter.getSource();
                    source2 = source3 != null ? KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.ImplicitReturnTypeOfLambdaValueParameter.INSTANCE, null, 2, null) : null;
                }
                firValueParameter.replaceReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneKotlinTypeApproximateLambdaInputType, source2, null, 2, null));
                i = i2;
            }
        }

        /* JADX WARN: Code duplicated, block: B:7:0x002f  */
        @Override // org.jetbrains.kotlin.fir.resolve.inference.LambdaAnalyzer
        public ReturnArgumentsAnalysisResult analyzeAndGetLambdaReturnArguments(ConeResolvedLambdaAtom lambdaAtom, ConeKotlinType receiverType, List<? extends ConeKotlinType> contextParameters, List<? extends ConeKotlinType> parameters, ConeKotlinType expectedReturnType, Candidate candidate, boolean withPCLASession, boolean forOverloadByLambdaReturnType) {
            boolean z;
            FirValueParameter firValueParameterBuild;
            final FirResolvedTypeRef firResolvedTypeRefResolvedTypeFromPrototype;
            KtSourceElement ktSourceElementFakeElement$default;
            List<? extends ConeKotlinType> listPlus;
            FirResolvedTypeRef noExpectedType;
            ConstraintStorage firPCLAInferenceSession;
            ConstraintStorage constraintStorageRunLambdaCompletion;
            ConstraintStorage constraintStorageRunLambdaCompletion2;
            ConstraintStorage firPCLAInferenceSession2;
            ConstraintStorage firPCLAInferenceSession3;
            List<? extends ConeKotlinType> list;
            int i;
            ConstraintStorage constraintStorage;
            KtSourceElement ktSourceElementFakeElement$default2;
            boolean z2 = withPCLASession;
            lambdaAtom.getClass();
            contextParameters.getClass();
            parameters.getClass();
            candidate.getClass();
            FirAnonymousFunction anonymousFunction = lambdaAtom.getAnonymousFunction();
            int i2 = 0;
            if (anonymousFunction.getValueParameters().isEmpty()) {
                z = parameters.size() == 1;
            }
            ClassMembersKt.setMatchingParameterFunctionType(anonymousFunction, lambdaAtom.mo581getExpectedType());
            ConstraintStorage constraintStorage2 = null;
            if (z) {
                Name name = StandardNames.IMPLICIT_LAMBDA_PARAMETER_NAME;
                ConeKotlinType coneKotlinType = (ConeKotlinType) CollectionsKt.single(parameters);
                FirCallCompleter firCallCompleter = FirCallCompleter.this;
                FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
                firValueParameterBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
                KtSourceElement source = lambdaAtom.getAnonymousFunction().getSource();
                firValueParameterBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ItLambdaParameter.INSTANCE, null, 2, null) : null);
                firValueParameterBuilder.setContainingDeclarationSymbol(anonymousFunction.getSymbol());
                firValueParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(firCallCompleter.getSession()));
                firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                firValueParameterBuilder.setName(name);
                firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                ConeKotlinType coneKotlinTypeApproximateLambdaInputType = firCallCompleter.approximateLambdaInputType(coneKotlinType, firValueParameterBuilder.getSymbol(), z2, candidate);
                KtSourceElement source2 = lambdaAtom.getAnonymousFunction().getSource();
                firValueParameterBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneKotlinTypeApproximateLambdaInputType, source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.ImplicitReturnTypeOfLambdaValueParameter.INSTANCE, null, 2, null) : null, null, 2, null));
                firValueParameterBuilder.setDefaultValue(null);
                firValueParameterBuilder.setCrossinline(false);
                firValueParameterBuilder.setNoinline(false);
                firValueParameterBuilder.setVararg(false);
                firValueParameterBuild = firValueParameterBuilder.mo288build();
            } else {
                firValueParameterBuild = null;
            }
            if (expectedReturnType != null) {
                FirTypeRef returnTypeRef = anonymousFunction.getReturnTypeRef();
                KtSourceElement source3 = anonymousFunction.getSource();
                firResolvedTypeRefResolvedTypeFromPrototype = CopyUtilsKt.resolvedTypeFromPrototype(returnTypeRef, expectedReturnType, source3 != null ? KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE, null, 2, null) : null);
            } else {
                firResolvedTypeRefResolvedTypeFromPrototype = null;
            }
            if (receiverType == null || lambdaAtom.getCoerceFirstParameterToExtensionReceiver()) {
                anonymousFunction.replaceReceiverParameter(null);
                Unit unit = Unit.INSTANCE;
            } else {
                FirReceiverParameter receiverParameter = anonymousFunction.getReceiverParameter();
                if (receiverParameter != null) {
                    ConeKotlinType coneKotlinTypeApproximateLambdaInputType2 = FirCallCompleter.this.approximateLambdaInputType(receiverType, null, z2, candidate);
                    KtSourceElement source4 = receiverParameter.getSource();
                    if (source4 == null || (ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source4, KtFakeSourceElementKind.LambdaReceiver.INSTANCE, null, 2, null)) == null) {
                        KtSourceElement source5 = anonymousFunction.getSource();
                        ktSourceElementFakeElement$default = source5 != null ? KtSourceElementKt.fakeElement$default(source5, KtFakeSourceElementKind.LambdaReceiver.INSTANCE, null, 2, null) : null;
                    }
                    receiverParameter.replaceTypeRef(CopyUtilsKt.resolvedTypeFromPrototype(receiverParameter.getTypeRef(), coneKotlinTypeApproximateLambdaInputType2, ktSourceElementFakeElement$default));
                }
            }
            setContextParametersConfiguration(anonymousFunction, contextParameters, z2, candidate);
            FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(FirCallCompleter.this.getSession());
            KtSourceElement source6 = FirCallCompleter.this.components.getFile().getSource();
            if (!lambdaAtom.getCoerceFirstParameterToExtensionReceiver()) {
                listPlus = parameters;
            } else {
                if (receiverType == null) {
                    k2d.a("Coercion to extension receiver while no receiver present");
                    return null;
                }
                listPlus = CollectionsKt.plus(CollectionsKt.listOf(receiverType), parameters);
            }
            List<FirValueParameter> valueParameters = anonymousFunction.getValueParameters();
            FirCallCompleter firCallCompleter2 = FirCallCompleter.this;
            for (Object obj : valueParameters) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                FirValueParameter firValueParameter = (FirValueParameter) obj;
                if (i2 >= listPlus.size()) {
                    FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                    firErrorTypeRefBuilder.setDiagnostic(new ConeCannotInferValueParameterType(firValueParameter.getSymbol(), "Lambda or anonymous function has more parameters than expected", false, 4, null));
                    firErrorTypeRefBuilder.setSource(firValueParameter.getSource());
                    firValueParameter.replaceReturnTypeRef(firErrorTypeRefBuilder.build());
                    list = listPlus;
                    i = 2;
                    constraintStorage = null;
                } else {
                    ConeKotlinType coneKotlinTypeApproximateLambdaInputType3 = firCallCompleter2.approximateLambdaInputType(listPlus.get(i2), firValueParameter.getSymbol(), z2, candidate);
                    KtSourceElement source7 = firValueParameter.getSource();
                    list = listPlus;
                    if (source7 != null) {
                        i = 2;
                        constraintStorage = null;
                        ktSourceElementFakeElement$default2 = KtSourceElementKt.fakeElement$default(source7, KtFakeSourceElementKind.ImplicitReturnTypeOfLambdaValueParameter.INSTANCE, null, 2, null);
                    } else {
                        i = 2;
                        constraintStorage = null;
                        ktSourceElementFakeElement$default2 = null;
                    }
                    FirTypeRef firResolvedTypeRef$default = firValueParameter.getReturnTypeRef() instanceof FirImplicitTypeRef ? UtilsKt.toFirResolvedTypeRef$default(coneKotlinTypeApproximateLambdaInputType3, ktSourceElementFakeElement$default2, constraintStorage, i, constraintStorage) : CopyUtilsKt.resolvedTypeFromPrototype(firValueParameter.getReturnTypeRef(), coneKotlinTypeApproximateLambdaInputType3, ktSourceElementFakeElement$default2);
                    firValueParameter.replaceReturnTypeRef(firResolvedTypeRef$default);
                    if (lookupTracker != null) {
                        FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, firResolvedTypeRef$default, firValueParameter.getSource(), source6);
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
                listPlus = list;
                constraintStorage2 = constraintStorage;
                i2 = i3;
                z2 = withPCLASession;
            }
            ConstraintStorage constraintStorage3 = constraintStorage2;
            anonymousFunction.replaceValueParameters(CollectionsKt.plus(anonymousFunction.getValueParameters(), CollectionsKt.listOfNotNull(firValueParameterBuild)));
            if (firResolvedTypeRefResolvedTypeFromPrototype != null) {
                if (lookupTracker != null) {
                    FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, (FirTypeRef) firResolvedTypeRefResolvedTypeFromPrototype, anonymousFunction.getSource(), source6);
                    Unit unit3 = Unit.INSTANCE;
                }
                noExpectedType = firResolvedTypeRefResolvedTypeFromPrototype;
            } else {
                noExpectedType = FirCallCompleter.this.components.getNoExpectedType();
            }
            anonymousFunction.replaceReturnTypeRef(noExpectedType);
            BodyResolveContext context = FirCallCompleter.this.transformer.getContext();
            FirAnonymousFunctionSymbol symbol = anonymousFunction.getSymbol();
            FirCallCompleter firCallCompleter3 = FirCallCompleter.this;
            Pair<FirTowerDataContext, FirInferenceSession> anonymousFunctionContext = context.getSpecialTowerDataContexts().getAnonymousFunctionContext(symbol);
            if (anonymousFunctionContext == null) {
                if (withPCLASession) {
                    candidate.getLambdasAnalyzedWithPCLA().add(anonymousFunction);
                    firPCLAInferenceSession3 = new FirPCLAInferenceSession(candidate, InferenceComponentsKt.getInferenceComponents(firCallCompleter3.getSession()));
                } else {
                    firPCLAInferenceSession3 = constraintStorage3;
                }
                final FirAnonymousFunctionExpression expression = lambdaAtom.getExpression();
                final FirDeclarationsResolveTransformer declarationsTransformer = firCallCompleter3.transformer.getDeclarationsTransformer();
                declarationsTransformer.getClass();
                if (firPCLAInferenceSession3 != null) {
                    BodyResolveContext context2 = firCallCompleter3.transformer.getContext();
                    FirInferenceSession inferenceSession = context2.getInferenceSession();
                    context2.setInferenceSession(firPCLAInferenceSession3);
                    try {
                        declarationsTransformer.doTransformAnonymousFunctionBodyFromCallCompletion$org_jetbrains_kotlin_resolve(expression, firResolvedTypeRefResolvedTypeFromPrototype);
                        firPCLAInferenceSession3.applyResultsToMainCandidate();
                        Unit unit4 = Unit.INSTANCE;
                        context2.setInferenceSession(inferenceSession);
                        constraintStorageRunLambdaCompletion2 = constraintStorage3;
                    } catch (Throwable th) {
                        context2.setInferenceSession(inferenceSession);
                        throw th;
                    }
                } else {
                    constraintStorageRunLambdaCompletion2 = firCallCompleter3.transformer.getContext().getInferenceSession().runLambdaCompletion(candidate, forOverloadByLambdaReturnType, new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.inference.d
                        public final Object invoke() {
                            return FirCallCompleter.LambdaAnalyzerImpl.analyzeAndGetLambdaReturnArguments$lambda$5$2(declarationsTransformer, expression, firResolvedTypeRefResolvedTypeFromPrototype);
                        }
                    });
                }
                Unit unit5 = Unit.INSTANCE;
            } else {
                FirTowerDataContext firTowerDataContext = (FirTowerDataContext) anonymousFunctionContext.component1();
                FirInferenceSession firInferenceSession = (FirInferenceSession) anonymousFunctionContext.component2();
                FirTowerDataMode towerDataMode = context.getTowerDataMode();
                try {
                    FirRegularTowerDataContexts firRegularTowerDataContextsReplaceAndSetActiveRegularContext = context.getRegularTowerDataContexts().replaceAndSetActiveRegularContext(firTowerDataContext);
                    FirRegularTowerDataContexts regularTowerDataContexts = context.getRegularTowerDataContexts();
                    context.setRegularTowerDataContexts(firRegularTowerDataContextsReplaceAndSetActiveRegularContext);
                    try {
                        if (firInferenceSession != context.getInferenceSession()) {
                            FirInferenceSession inferenceSession2 = context.getInferenceSession();
                            context.setInferenceSession(firInferenceSession);
                            if (withPCLASession) {
                                try {
                                    candidate.getLambdasAnalyzedWithPCLA().add(anonymousFunction);
                                    firPCLAInferenceSession2 = new FirPCLAInferenceSession(candidate, InferenceComponentsKt.getInferenceComponents(firCallCompleter3.getSession()));
                                } catch (Throwable th2) {
                                    context.setInferenceSession(inferenceSession2);
                                    throw th2;
                                }
                            } else {
                                firPCLAInferenceSession2 = constraintStorage3;
                            }
                            final FirAnonymousFunctionExpression expression2 = lambdaAtom.getExpression();
                            final FirDeclarationsResolveTransformer declarationsTransformer2 = firCallCompleter3.transformer.getDeclarationsTransformer();
                            declarationsTransformer2.getClass();
                            if (firPCLAInferenceSession2 != null) {
                                BodyResolveContext context3 = firCallCompleter3.transformer.getContext();
                                FirInferenceSession inferenceSession3 = context3.getInferenceSession();
                                context3.setInferenceSession(firPCLAInferenceSession2);
                                try {
                                    declarationsTransformer2.doTransformAnonymousFunctionBodyFromCallCompletion$org_jetbrains_kotlin_resolve(expression2, firResolvedTypeRefResolvedTypeFromPrototype);
                                    firPCLAInferenceSession2.applyResultsToMainCandidate();
                                    Unit unit6 = Unit.INSTANCE;
                                    context3.setInferenceSession(inferenceSession3);
                                    constraintStorageRunLambdaCompletion = constraintStorage3;
                                } catch (Throwable th3) {
                                    context3.setInferenceSession(inferenceSession3);
                                    throw th3;
                                }
                            } else {
                                constraintStorageRunLambdaCompletion = firCallCompleter3.transformer.getContext().getInferenceSession().runLambdaCompletion(candidate, forOverloadByLambdaReturnType, new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.inference.d
                                    public final Object invoke() {
                                        return FirCallCompleter.LambdaAnalyzerImpl.analyzeAndGetLambdaReturnArguments$lambda$5$2(declarationsTransformer2, expression2, firResolvedTypeRefResolvedTypeFromPrototype);
                                    }
                                });
                            }
                            Unit unit7 = Unit.INSTANCE;
                            context.setInferenceSession(inferenceSession2);
                        } else {
                            if (withPCLASession) {
                                candidate.getLambdasAnalyzedWithPCLA().add(anonymousFunction);
                                firPCLAInferenceSession = new FirPCLAInferenceSession(candidate, InferenceComponentsKt.getInferenceComponents(firCallCompleter3.getSession()));
                            } else {
                                firPCLAInferenceSession = constraintStorage3;
                            }
                            final FirAnonymousFunctionExpression expression3 = lambdaAtom.getExpression();
                            final FirDeclarationsResolveTransformer declarationsTransformer3 = firCallCompleter3.transformer.getDeclarationsTransformer();
                            declarationsTransformer3.getClass();
                            if (firPCLAInferenceSession != null) {
                                BodyResolveContext context4 = firCallCompleter3.transformer.getContext();
                                FirInferenceSession inferenceSession4 = context4.getInferenceSession();
                                context4.setInferenceSession(firPCLAInferenceSession);
                                try {
                                    declarationsTransformer3.doTransformAnonymousFunctionBodyFromCallCompletion$org_jetbrains_kotlin_resolve(expression3, firResolvedTypeRefResolvedTypeFromPrototype);
                                    firPCLAInferenceSession.applyResultsToMainCandidate();
                                    Unit unit8 = Unit.INSTANCE;
                                    context4.setInferenceSession(inferenceSession4);
                                    constraintStorageRunLambdaCompletion = constraintStorage3;
                                } catch (Throwable th4) {
                                    context4.setInferenceSession(inferenceSession4);
                                    throw th4;
                                }
                            } else {
                                constraintStorageRunLambdaCompletion = firCallCompleter3.transformer.getContext().getInferenceSession().runLambdaCompletion(candidate, forOverloadByLambdaReturnType, new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.inference.d
                                    public final Object invoke() {
                                        return FirCallCompleter.LambdaAnalyzerImpl.analyzeAndGetLambdaReturnArguments$lambda$5$2(declarationsTransformer3, expression3, firResolvedTypeRefResolvedTypeFromPrototype);
                                    }
                                });
                            }
                            Unit unit9 = Unit.INSTANCE;
                        }
                        context.setRegularTowerDataContexts(regularTowerDataContexts);
                        context.setTowerDataMode(towerDataMode);
                        constraintStorageRunLambdaCompletion2 = constraintStorageRunLambdaCompletion;
                    } catch (Throwable th5) {
                        context.setRegularTowerDataContexts(regularTowerDataContexts);
                        throw th5;
                    }
                } catch (Throwable th6) {
                    context.setTowerDataMode(towerDataMode);
                    throw th6;
                }
            }
            FirCallCompleter.this.transformer.getContext().dropContextForAnonymousFunction(anonymousFunction);
            Collection<FirAnonymousFunctionReturnExpressionInfo> collectionReturnExpressionsOfAnonymousFunction = FirCallCompleter.this.components.getDataFlowAnalyzer().returnExpressionsOfAnonymousFunction(anonymousFunction);
            FirCallCompleter firCallCompleter4 = FirCallCompleter.this;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionReturnExpressionsOfAnonymousFunction, 10));
            for (FirAnonymousFunctionReturnExpressionInfo firAnonymousFunctionReturnExpressionInfo : collectionReturnExpressionsOfAnonymousFunction) {
                ConeResolutionAtom coneResolutionAtomCreateRawAtom = ConeResolutionAtom.INSTANCE.createRawAtom(firAnonymousFunctionReturnExpressionInfo.getExpression());
                if (expectedReturnType != null && LanguageVersionUtilsKt.isEnabled(firCallCompleter4, LanguageFeature.PCLAEnhancementsIn21) && !(coneResolutionAtomCreateRawAtom instanceof ConeAtomWithCandidate)) {
                    coneResolutionAtomCreateRawAtom = new ConeSimpleLeafResolutionAtom(firAnonymousFunctionReturnExpressionInfo.getExpression(), false);
                }
                arrayList.add(coneResolutionAtomCreateRawAtom);
            }
            return new ReturnArgumentsAnalysisResult(arrayList, constraintStorageRunLambdaCompletion2);
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConstraintSystemCompletionMode.values().length];
            try {
                iArr[ConstraintSystemCompletionMode.FULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConstraintSystemCompletionMode.PARTIAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConstraintSystemCompletionMode.PCLA_POSTPONED_CALL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ConstraintSystemCompletionMode.UNTIL_FIRST_LAMBDA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FirCallCompleter(FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher, FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents) {
        firAbstractBodyResolveTransformerDispatcher.getClass();
        bodyResolveTransformerComponents.getClass();
        this.transformer = firAbstractBodyResolveTransformerDispatcher;
        this.components = bodyResolveTransformerComponents;
        this.session = bodyResolveTransformerComponents.getSession();
        this.completer = new ConstraintSystemCompleter(bodyResolveTransformerComponents);
    }

    private final void addConstraintFromExpectedType(Candidate candidate, ConeKotlinType initialType, ResolutionMode resolutionMode) {
        if (resolutionMode instanceof ResolutionMode.WithExpectedType) {
            ResolutionMode.WithExpectedType withExpectedType = (ResolutionMode.WithExpectedType) resolutionMode;
            if (withExpectedType.getArrayLiteralPosition() == ResolutionMode.ArrayLiteralPosition.AnnotationArgument) {
                return;
            }
            ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(this, withExpectedType.getExpectedType());
            NewConstraintSystemImpl system = candidate.getSystem();
            if (resolutionMode.getForceFullCompletion() && isSyntheticFunctionCallThatShouldUseEqualityConstraint(candidate, coneKotlinTypeFullyExpandedType)) {
                ConstraintSystemBuilderKt.addEqualityConstraintIfCompatible(system, initialType, coneKotlinTypeFullyExpandedType, ConeExpectedTypeConstraintPosition.INSTANCE);
                candidate.markWasExpectedTypeAddedAsEqualityForSyntheticCall();
                return;
            }
            if (withExpectedType.getFromCast()) {
                if (FirCallCompleterKt.isFunctionForExpectTypeFromCastFeature(candidate)) {
                    system.addSubtypeConstraint(initialType, coneKotlinTypeFullyExpandedType, ConeExpectedTypeConstraintPosition.INSTANCE);
                }
            } else if (!TypeUtilsKt.isUnitOrFlexibleUnit(coneKotlinTypeFullyExpandedType) || !withExpectedType.getLastStatementInBlock()) {
                ConstraintSystemBuilderKt.addSubtypeConstraintIfCompatible(system, initialType, coneKotlinTypeFullyExpandedType, ConeExpectedTypeConstraintPosition.INSTANCE);
            } else {
                if (system.getNotFixedTypeVariables().isEmpty()) {
                    return;
                }
                if (ConeBuiltinTypeUtilsKt.isUnit(coneKotlinTypeFullyExpandedType)) {
                    ConstraintSystemBuilderKt.addEqualityConstraintIfCompatible(system, initialType, coneKotlinTypeFullyExpandedType, ConeExpectedTypeConstraintPosition.INSTANCE);
                } else {
                    ConstraintSystemBuilderKt.addSubtypeConstraintIfCompatible(system, initialType, coneKotlinTypeFullyExpandedType, ConeExpectedTypeConstraintPosition.INSTANCE);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConeKotlinType approximateLambdaInputType(ConeKotlinType coneKotlinType, FirValueParameterSymbol firValueParameterSymbol, boolean z, Candidate candidate) {
        if (useErrorTypeInsteadOfTypeVariableForParameterType(coneKotlinType, firValueParameterSymbol == null, z)) {
            return new ConeErrorType(firValueParameterSymbol != null ? new ConeCannotInferValueParameterType(firValueParameterSymbol, null, isSyntheticCallForTopLevelLambda(candidate), 2, null) : new ConeCannotInferReceiverParameterType(null, 1, null), false, null, null, null, null, null, 126, null);
        }
        ConeKotlinType coneKotlinTypeApproximateToSuperType = TypeComponentsKt.getTypeApproximator(getSession()).approximateToSuperType(coneKotlinType, TypeApproximatorConfiguration.IntermediateApproximationToSupertypeAfterCompletionInK2.INSTANCE);
        return coneKotlinTypeApproximateToSuperType == null ? coneKotlinType : coneKotlinTypeApproximateToSuperType;
    }

    public static void b(PostponedArgumentsAnalyzer postponedArgumentsAnalyzer, Candidate candidate, ConePostponedResolvedAtom conePostponedResolvedAtom, boolean z, CollectionLiteralBounds collectionLiteralBounds) {
        conePostponedResolvedAtom.getClass();
        postponedArgumentsAnalyzer.analyze(candidate.getSystem(), conePostponedResolvedAtom, candidate, z, collectionLiteralBounds);
    }

    private final void checkStorageConstraintsAfterFullCompletion(ConstraintStorage storage) {
        if (!storage.getNotFixedTypeVariables().isEmpty() && LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.PCLAEnhancementsIn21)) {
            Map notFixedTypeVariables = storage.getNotFixedTypeVariables();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : notFixedTypeVariables.entrySet()) {
                if (((VariableWithConstraints) entry.getValue()).getTypeVariable() instanceof ConeTypeParameterBasedTypeVariable) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            if (linkedHashMap.isEmpty()) {
                return;
            }
            wec.a("All variables should be fixed to something, but {", CollectionsKt.joinToString$default(linkedHashMap.keySet(), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), "} are found");
        }
    }

    public static /* synthetic */ FirExpression completeCall$default(FirCallCompleter firCallCompleter, FirExpression firExpression, ResolutionMode resolutionMode, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return firCallCompleter.completeCall(firExpression, resolutionMode, z);
    }

    public static /* synthetic */ FirCallCompletionResultsWriterTransformer createCompletionResultsWriter$default(FirCallCompleter firCallCompleter, ConeSubstitutor coneSubstitutor, FirCallCompletionResultsWriterTransformer.Mode mode, int i, Object obj) {
        if ((i & 2) != 0) {
            mode = FirCallCompletionResultsWriterTransformer.Mode.Normal;
        }
        return firCallCompleter.createCompletionResultsWriter(coneSubstitutor, mode);
    }

    private final FirInferenceSession getInferenceSession() {
        return this.transformer.getContext().getInferenceSession();
    }

    private final boolean isSyntheticCallForTopLevelCollectionLiteral(Candidate candidate) {
        return (candidate.getSymbol() instanceof FirSyntheticFunctionSymbol) && (candidate.getCallInfo().getCallSite() instanceof FirCollectionLiteral);
    }

    private final boolean isSyntheticCallForTopLevelLambda(Candidate candidate) {
        return candidate.getCallInfo().getCallSite() instanceof FirAnonymousFunctionExpression;
    }

    private final boolean isSyntheticElvisFunction(FirBasedSymbol<?> firBasedSymbol) {
        if (!Intrinsics.areEqual(firBasedSymbol.getOrigin(), FirDeclarationOrigin.Synthetic.FakeFunction.INSTANCE)) {
            return false;
        }
        FirCallableSymbol firCallableSymbol = firBasedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) firBasedSymbol : null;
        return Intrinsics.areEqual(firCallableSymbol != null ? firCallableSymbol.getCallableId() : null, SyntheticCallableId.INSTANCE.getELVIS());
    }

    private final boolean isSyntheticFunctionCallThatShouldUseEqualityConstraint(Candidate candidate, ConeKotlinType coneKotlinType) {
        if (!LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.EqualityConstraintForOperatorsUnderAssignments) && this.components.getContext().getIsInsideAssignmentRhs()) {
            return false;
        }
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
        if (firCallableSymbol == null || !Intrinsics.areEqual(firCallableSymbol.getOrigin(), FirDeclarationOrigin.Synthetic.FakeFunction.INSTANCE) || isUnitOrAnyWithArbitraryNullability(coneKotlinType) || (Intrinsics.areEqual(firCallableSymbol.getCallableId(), SyntheticCallableId.INSTANCE.getCHECK_NOT_NULL()) && TypeUtilsKt.canBeNull$default(coneKotlinType, getSession(), false, null, 6, null))) {
            return false;
        }
        Collection<TypeVariableMarker> collectionValues = candidate.getSystem().getAllTypeVariables().values();
        if ((collectionValues instanceof Collection) && collectionValues.isEmpty()) {
            return true;
        }
        for (TypeVariableMarker typeVariableMarker : collectionValues) {
            if ((typeVariableMarker instanceof ConeTypeParameterBasedTypeVariable) && isSyntheticElvisFunction(((ConeTypeParameterBasedTypeVariable) typeVariableMarker).getTypeParameterSymbol().getContainingDeclarationSymbol())) {
                return false;
            }
        }
        return true;
    }

    private final boolean isUnitOrAnyWithArbitraryNullability(ConeKotlinType coneKotlinType) {
        if (coneKotlinType instanceof ConeDynamicType) {
            return false;
        }
        ConeRigidType coneRigidTypeUpperBoundIfFlexible = ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinType);
        return ConeBuiltinTypeUtilsKt.isUnitOrNullableUnit(coneRigidTypeUpperBoundIfFlexible) || ConeBuiltinTypeUtilsKt.isAnyOrNullableAny(coneRigidTypeUpperBoundIfFlexible);
    }

    public static /* synthetic */ void runCompletionForCall$default(FirCallCompleter firCallCompleter, Candidate candidate, ConstraintSystemCompletionMode constraintSystemCompletionMode, FirExpression firExpression, ConeKotlinType coneKotlinType, PostponedArgumentsAnalyzer postponedArgumentsAnalyzer, int i, Object obj) {
        if ((i & 16) != 0) {
            postponedArgumentsAnalyzer = null;
        }
        firCallCompleter.runCompletionForCall(candidate, constraintSystemCompletionMode, firExpression, coneKotlinType, postponedArgumentsAnalyzer);
    }

    private final <T extends FirExpression & FirResolvable> T runningCompletionResultsWriterInNonFullModeIfNeeded(T t, Candidate candidate, ConstraintSystemCompletionMode constraintSystemCompletionMode) {
        if (!isSyntheticCallForTopLevelLambda(candidate) && !isSyntheticCallForTopLevelCollectionLiteral(candidate)) {
            return t;
        }
        ConstraintSystemCompletionMode constraintSystemCompletionMode2 = ConstraintSystemCompletionMode.FULL;
        return (T) ((FirExpression) FirTransformerUtilKt.transformSingle(t, createCompletionResultsWriter((ConeSubstitutor) org.jetbrains.kotlin.resolve.calls.inference.InferenceUtilsKt.buildCurrentSubstitutor(candidate.getSystem().currentStorage(), TypeComponentsKt.getTypeContext(getSession()), MapsKt.emptyMap()), isSyntheticCallForTopLevelCollectionLiteral(candidate) ? FirCallCompletionResultsWriterTransformer.Mode.TopLevelSyntheticCallInPclaCompletion : FirCallCompletionResultsWriterTransformer.Mode.Normal), null));
    }

    private final boolean useErrorTypeInsteadOfTypeVariableForParameterType(ConeKotlinType coneKotlinType, boolean z, boolean z2) {
        if (!(coneKotlinType instanceof ConeTypeVariableType)) {
            return false;
        }
        if (z) {
            return true;
        }
        return !(z2 || (getInferenceSession() instanceof FirPCLAInferenceSession)) || ((ConeTypeVariableType) coneKotlinType).getTypeConstructor().getOriginalTypeParameter() == null;
    }

    public final <T extends FirExpression & FirResolvable> T completeCall(T call, ResolutionMode resolutionMode, boolean skipEvenPartialCompletion) {
        call.getClass();
        resolutionMode.getClass();
        T t = call;
        ConeKotlinType coneKotlinTypeTypeFromCallee = ResolveUtilsKt.typeFromCallee(this.components, t);
        FirReference calleeReference = t.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        if (firNamedReferenceWithCandidate != null) {
            Candidate candidate = firNamedReferenceWithCandidate.getCandidate();
            ConeKotlinType coneKotlinTypeInitialTypeOfCandidate = ResolveUtilsKt.initialTypeOfCandidate(coneKotlinTypeTypeFromCallee, candidate);
            if (!(call instanceof FirAnnotationCall) && !(call instanceof FirDelegatedConstructorCall)) {
                call.replaceConeTypeOrNull(coneKotlinTypeInitialTypeOfCandidate);
            }
            FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
            if (lookupTracker != null) {
                FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, coneKotlinTypeInitialTypeOfCandidate, t.getSource(), this.components.getContext().getFile().getSource());
            }
            addConstraintFromExpectedType(candidate, coneKotlinTypeInitialTypeOfCandidate, resolutionMode);
            if (!skipEvenPartialCompletion) {
                Enum enumComputeCompletionMode = CompletionModeCalculatorKt.computeCompletionMode(candidate, InferenceComponentsKt.getInferenceComponents(getSession()), resolutionMode, coneKotlinTypeInitialTypeOfCandidate);
                Enum r0 = ConstraintSystemCompletionMode.FULL;
                Enum r7 = (enumComputeCompletionMode == r0 && (enumComputeCompletionMode = getInferenceSession().customCompletionModeInsteadOfFull(t)) == null) ? r0 : enumComputeCompletionMode;
                PostponedArgumentsAnalyzer postponedArgumentsAnalyzerCreatePostponedArgumentsAnalyzer = createPostponedArgumentsAnalyzer(this.transformer.getResolutionContext());
                if (call instanceof FirFunctionCall) {
                    LambdaArgumentEffectsTransformerKt.replaceLambdaArgumentEffects((FirFunctionCall) call, getSession());
                }
                int i = WhenMappings.$EnumSwitchMapping$0[r7.ordinal()];
                if (i == 1) {
                    runCompletionForCall(candidate, r7, call, coneKotlinTypeInitialTypeOfCandidate, postponedArgumentsAnalyzerCreatePostponedArgumentsAnalyzer);
                    ConstraintStorage constraintStorageAsReadOnlyStorage = candidate.getSystem().asReadOnlyStorage();
                    checkStorageConstraintsAfterFullCompletion(constraintStorageAsReadOnlyStorage);
                    return (T) ((FirExpression) FirTransformerUtilKt.transformSingle(call, createCompletionResultsWriter$default(this, (ConeSubstitutor) org.jetbrains.kotlin.resolve.calls.inference.InferenceUtilsKt.buildAbstractResultingSubstitutor$default(constraintStorageAsReadOnlyStorage, TypeComponentsKt.getTypeContext(getSession()), false, 2, (Object) null), null, 2, null), null));
                }
                if (i == 2 || i == 3) {
                    runCompletionForCall(candidate, r7, call, coneKotlinTypeInitialTypeOfCandidate, postponedArgumentsAnalyzerCreatePostponedArgumentsAnalyzer);
                    getInferenceSession().processPartiallyResolvedCall(call, resolutionMode, r7);
                    return (T) runningCompletionResultsWriterInNonFullModeIfNeeded(call, candidate, r7);
                }
                if (i != 4) {
                    bu8.a();
                    return null;
                }
                g33.a();
                return null;
            }
        }
        return call;
    }

    public final FirCallCompletionResultsWriterTransformer createCompletionResultsWriter(ConeSubstitutor substitutor, FirCallCompletionResultsWriterTransformer.Mode mode) {
        substitutor.getClass();
        mode.getClass();
        return new FirCallCompletionResultsWriterTransformer(getSession(), this.components.getScopeSession(), substitutor, this.components.getReturnTypeCalculator(), TypeComponentsKt.getTypeApproximator(getSession()), this.components.getDataFlowAnalyzer(), this.components.getIntegerLiteralAndOperatorApproximationTransformer(), this.components.getSamResolver(), this.components.getContext(), mode, this.components.getContext().getIsInsideAnnotationContext());
    }

    public final PostponedArgumentsAnalyzer createPostponedArgumentsAnalyzer(ResolutionContext context) {
        context.getClass();
        return new PostponedArgumentsAnalyzer(context, new LambdaAnalyzerImpl(), InferenceComponentsKt.getInferenceComponents(getSession()), this.transformer.getComponents().getCallResolver());
    }

    public final ConstraintSystemCompleter getCompleter() {
        return this.completer;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    public final void prepareLambdaAtomForFactoryPattern(ConeResolvedLambdaAtom atom, Candidate candidate) {
        atom.getClass();
        candidate.getClass();
        ConeTypeVariableForLambdaReturnType coneTypeVariableForLambdaReturnType = new ConeTypeVariableForLambdaReturnType(atom.getAnonymousFunction(), "_R");
        NewConstraintSystemImpl builder = candidate.getSystem().getBuilder();
        builder.registerVariable(coneTypeVariableForLambdaReturnType);
        TypeSubstitutorMarker typeSubstitutorMarkerBuildCurrentSubstitutor = builder.buildCurrentSubstitutor();
        ConeKotlinType coneKotlinTypeMo581getExpectedType = atom.mo581getExpectedType();
        coneKotlinTypeMo581getExpectedType.getClass();
        ConeClassLikeType coneClassLikeTypeSafeSubstitute = MarkerExtensionsKt.safeSubstitute(typeSubstitutorMarkerBuildCurrentSubstitutor, builder, coneKotlinTypeMo581getExpectedType);
        coneClassLikeTypeSafeSubstitute.getClass();
        ConeClassLikeType coneClassLikeType = coneClassLikeTypeSafeSubstitute;
        int length = coneClassLikeType.getTypeArguments().length;
        ConeClassLikeLookupTag lookupTag = coneClassLikeType.getLookupTag();
        ConeTypeProjection[] coneTypeProjectionArr = new ConeTypeProjection[length];
        int i = 0;
        while (i < length) {
            coneTypeProjectionArr[i] = i != length + (-1) ? coneClassLikeType.getTypeArguments()[i] : coneTypeVariableForLambdaReturnType.getDefaultType();
            i++;
        }
        ConeClassLikeTypeImpl coneClassLikeTypeImpl = new ConeClassLikeTypeImpl(lookupTag, coneTypeProjectionArr, coneClassLikeType.getIsMarkedNullable(), coneClassLikeType.getAttributes());
        builder.addSubtypeConstraint(coneClassLikeTypeImpl, coneClassLikeType, new ConeArgumentConstraintPosition(atom.getAnonymousFunction()));
        atom.replaceExpectedType(coneClassLikeTypeImpl, coneTypeVariableForLambdaReturnType.getDefaultType());
        atom.replaceTypeVariableForLambdaReturnType(coneTypeVariableForLambdaReturnType);
    }

    public final void runCompletionForCall(final Candidate candidate, ConstraintSystemCompletionMode completionMode, FirExpression call, ConeKotlinType initialType, final PostponedArgumentsAnalyzer analyzer) {
        candidate.getClass();
        completionMode.getClass();
        call.getClass();
        initialType.getClass();
        if (analyzer == null) {
            analyzer = createPostponedArgumentsAnalyzer(this.transformer.getResolutionContext());
        }
        this.completer.complete(candidate.getSystem().asConstraintSystemCompleterContext(), completionMode, CollectionsKt.listOf(new ConeAtomWithCandidate(call, candidate)), initialType, this.transformer.getResolutionContext(), new ConstraintSystemCompleter.PostponedAtomAnalyzer() { // from class: ty4
            @Override // org.jetbrains.kotlin.fir.resolve.inference.ConstraintSystemCompleter.PostponedAtomAnalyzer
            public final void analyzeInternal(ConePostponedResolvedAtom conePostponedResolvedAtom, boolean z, CollectionLiteralBounds collectionLiteralBounds) {
                FirCallCompleter.b(analyzer, candidate, conePostponedResolvedAtom, z, collectionLiteralBounds);
            }
        });
    }
}
