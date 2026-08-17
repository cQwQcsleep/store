package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirResolvedReifiedParameterReference;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.FirWrappedExpression;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.calls.ConeAtomWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.fir.resolve.inference.FirPCLAInferenceSession;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeExpectedTypeConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeSemiFixVariableConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableTypeConstructor;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilderKt;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionContext;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionMode;
import org.jetbrains.kotlin.resolve.calls.inference.components.TypeVariableDirectionCalculator;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintStorage;
import org.jetbrains.kotlin.resolve.calls.inference.model.MutableVariableWithConstraints;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J3\u0010\u001c\u001a\u00020\u001d\"\f\b\u0000\u0010\u001e*\u00020\u001b*\u00020\u001f2\u0006\u0010\u001a\u001a\u0002H\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0019H\u0016¢\u0006\u0002\u0010#J(\u0010$\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001d0(H\u0016J)\u0010)\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e2\u0006\u0010*\u001a\u00020\t2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u001e0(H\u0002¢\u0006\u0002\u0010+J\u0006\u0010,\u001a\u00020\u001dJ0\u0010-\u001a\u00020\u001d2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u00020\u00142\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u001d03J\u0018\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u0014\u00107\u001a\u00020\u001d*\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0012\u00108\u001a\u0004\u0018\u0001092\u0006\u0010:\u001a\u000209H\u0016J\u0018\u0010;\u001a\u00020\u001d2\u0006\u0010:\u001a\u0002092\u0006\u0010<\u001a\u00020\tH\u0016J.\u0010=\u001a\u0010\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u000209\u0018\u00010>2\u0006\u0010:\u001a\u0002092\u0006\u0010<\u001a\u00020\t2\b\b\u0002\u0010@\u001a\u00020&J.\u0010A\u001a\u0004\u0018\u000109*\u00020B2\u0006\u0010C\u001a\u00020?2\u0006\u0010@\u001a\u00020&2\u000e\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090(H\u0002J\b\u0010E\u001a\u00020&H\u0002J\u0014\u0010F\u001a\u00020&*\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\f\u0010G\u001a\u00020&*\u00020\u001fH\u0002J\f\u0010H\u001a\u00020&*\u00020\u001fH\u0002J\f\u0010I\u001a\u00020&*\u000209H\u0002J \u0010J\u001a\u00020\u001d2\u0006\u0010K\u001a\u0002092\u0006\u0010L\u001a\u0002092\u0006\u0010M\u001a\u00020NH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006O"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirPCLAInferenceSession;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;", "outerCandidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "inferenceComponents", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;)V", "value", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "currentCommonSystem", "getCurrentCommonSystem", "()Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "semiFixedVariables", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "getSemiFixedVariables", "()Ljava/util/Map;", "baseConstraintStorageForCandidate", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", "candidate", "bodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "customCompletionModeInsteadOfFull", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "processPartiallyResolvedCall", Argument.Delimiters.none, "T", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "resolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "completionMode", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;)V", "runLambdaCompletion", "forOverloadByLambdaReturnType", Argument.Delimiters.none, "block", "Lkotlin/Function0;", "runWithSpecifiedCurrentCommonSystem", "newSystem", "(Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "applyResultsToMainCandidate", "integrateChildSession", "childCalls", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "childStorage", "onCompletionResultsWriting", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "updateExpressionReturnTypeWithCurrentSubstitutorInPCLA", "expression", "updateReturnTypeWithCurrentSubstitutor", "getAndSemiFixCurrentResultIfTypeVariable", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "semiFixTypeVariablesAllowingFixationToOtherOnes", "myCs", "semiFixCurrentResultIfTypeVariableAndReturnBinding", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableTypeConstructor;", "allowFixationToOtherTypeVariables", "prepareContextForTypeVariableForSemiFixation", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionContext;", "coneTypeVariableTypeConstructor", "resultTypeCallback", "is21Mode", "mightBeAnalyzedAndCompletedIndependently", "isTrivialArgument", "isReceiverPostponed", "containsNotFixedTypeVariables", "addSubtypeConstraintIfCompatible", "lowerType", "upperType", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPCLAInferenceSession extends FirInferenceSession {
    private NewConstraintSystemImpl currentCommonSystem;
    private final InferenceComponents inferenceComponents;
    private final Candidate outerCandidate;
    private final Map<TypeConstructorMarker, KotlinTypeMarker> semiFixedVariables;

    public FirPCLAInferenceSession(Candidate candidate, InferenceComponents inferenceComponents) {
        candidate.getClass();
        inferenceComponents.getClass();
        this.outerCandidate = candidate;
        this.inferenceComponents = inferenceComponents;
        this.currentCommonSystem = FirInferenceSession.prepareSharedBaseSystem(candidate.getSystem(), inferenceComponents);
        this.semiFixedVariables = new LinkedHashMap();
    }

    public static boolean b(FirPCLAInferenceSession firPCLAInferenceSession, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return (coneKotlinType instanceof ConeTypeVariableType) && firPCLAInferenceSession.currentCommonSystem.getAllTypeVariables().containsKey(((ConeTypeVariableType) coneKotlinType).getTypeConstructor());
    }

    private final boolean containsNotFixedTypeVariables(ConeKotlinType coneKotlinType) {
        return ConeTypeUtilsKt.contains(coneKotlinType, new Function1() { // from class: ub5
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirPCLAInferenceSession.b(this.b, (ConeKotlinType) obj));
            }
        });
    }

    public static ConeKotlinType d(FirPCLAInferenceSession firPCLAInferenceSession, ConstraintSystemCompletionContext constraintSystemCompletionContext, ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor, Function0 function0) {
        if (firPCLAInferenceSession.inferenceComponents.getVariableFixationFinder().typeVariableHasProperConstraint(constraintSystemCompletionContext, coneTypeVariableTypeConstructor)) {
            return (ConeKotlinType) function0.invoke();
        }
        return null;
    }

    private final boolean is21Mode() {
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.inferenceComponents.getSession()).supportsFeature(LanguageFeature.PCLAEnhancementsIn21);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isReceiverPostponed(FirExpression firExpression) {
        Candidate candidate;
        if (containsNotFixedTypeVariables(FirTypeUtilsKt.getResolvedType(firExpression))) {
            return true;
        }
        FirResolvable firResolvable = firExpression instanceof FirResolvable ? (FirResolvable) firExpression : null;
        return (firResolvable == null || (candidate = CandidateFactoryKt.candidate(firResolvable)) == null || !candidate.getUsedOuterCs()) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isTrivialArgument(FirExpression firExpression) {
        if ((firExpression instanceof FirCallableReferenceAccess) || (firExpression instanceof FirCollectionLiteral)) {
            return false;
        }
        if (firExpression instanceof FirResolvable) {
            Candidate candidate = CandidateFactoryKt.candidate((FirResolvable) firExpression);
            if (candidate == null) {
                return !containsNotFixedTypeVariables(FirTypeUtilsKt.getResolvedType(firExpression));
            }
            return !candidate.getUsedOuterCs();
        }
        if (firExpression instanceof FirWrappedExpression) {
            return isTrivialArgument(((FirWrappedExpression) firExpression).getExpression());
        }
        if (firExpression instanceof FirFunctionTypeConversionExpression) {
            return isTrivialArgument(((FirFunctionTypeConversionExpression) firExpression).getExpression());
        }
        if (firExpression instanceof FirSmartCastExpression) {
            return isTrivialArgument(((FirSmartCastExpression) firExpression).getOriginalExpression());
        }
        if (firExpression instanceof FirCall) {
            List<FirExpression> arguments = ((FirCall) firExpression).getArgumentList().getArguments();
            if ((arguments instanceof Collection) && arguments.isEmpty()) {
                return true;
            }
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                if (!isTrivialArgument((FirExpression) it.next())) {
                    return false;
                }
            }
            return true;
        }
        if (firExpression instanceof FirBooleanOperatorExpression) {
            FirBooleanOperatorExpression firBooleanOperatorExpression = (FirBooleanOperatorExpression) firExpression;
            return isTrivialArgument(firBooleanOperatorExpression.getLeftOperand()) && isTrivialArgument(firBooleanOperatorExpression.getRightOperand());
        }
        if (firExpression instanceof FirComparisonExpression) {
            return isTrivialArgument(((FirComparisonExpression) firExpression).getCompareToCall());
        }
        if (firExpression instanceof FirCheckedSafeCallSubject) {
            return isTrivialArgument(((FirCheckedSafeCallSubject) firExpression).getOriginalReceiverRef().getValue());
        }
        if (firExpression instanceof FirSafeCallExpression) {
            FirSafeCallExpression firSafeCallExpression = (FirSafeCallExpression) firExpression;
            if (isTrivialArgument(firSafeCallExpression.getReceiver())) {
                FirStatement selector = firSafeCallExpression.getSelector();
                FirExpression firExpression2 = selector instanceof FirExpression ? (FirExpression) selector : null;
                if (firExpression2 != null && isTrivialArgument(firExpression2)) {
                    return true;
                }
            }
            return false;
        }
        if (!(firExpression instanceof FirVarargArgumentsExpression)) {
            return (firExpression instanceof FirLiteralExpression) || (firExpression instanceof FirResolvedQualifier) || (firExpression instanceof FirResolvedReifiedParameterReference);
        }
        List<FirExpression> arguments2 = ((FirVarargArgumentsExpression) firExpression).getArguments();
        if ((arguments2 instanceof Collection) && arguments2.isEmpty()) {
            return true;
        }
        Iterator<T> it2 = arguments2.iterator();
        while (it2.hasNext()) {
            if (!isTrivialArgument((FirExpression) it2.next())) {
                return false;
            }
        }
        return true;
    }

    private final boolean mightBeAnalyzedAndCompletedIndependently(Candidate candidate, BodyResolveContext bodyResolveContext) {
        ConeKotlinType coneType;
        FirExpression expression;
        FirExpression expression2;
        ResolutionMode resolutionMode = candidate.getCallInfo().getResolutionMode();
        if (resolutionMode instanceof ResolutionMode.Delegate) {
            return false;
        }
        if (resolutionMode instanceof ResolutionMode.WithExpectedType) {
            if (containsNotFixedTypeVariables(((ResolutionMode.WithExpectedType) candidate.getCallInfo().getResolutionMode()).getExpectedType())) {
                return false;
            }
        } else {
            if ((resolutionMode instanceof ResolutionMode.WithStatus) || (resolutionMode instanceof ResolutionMode.UpdateImplicitTypeRef)) {
                StringBuilder sb = new StringBuilder();
                sb.append(candidate);
                ej7.a(sb, " call should not be analyzed in ", candidate.getCallInfo().getResolutionMode());
                return false;
            }
            if (!(resolutionMode instanceof ResolutionMode.AssignmentLValue) && !(resolutionMode instanceof ResolutionMode.ContextDependent) && !(resolutionMode instanceof ResolutionMode.ContextIndependent) && !(resolutionMode instanceof ResolutionMode.ReceiverResolution)) {
                bu8.a();
                return false;
            }
        }
        FirElement callSite = candidate.getCallInfo().getCallSite();
        if (callSite instanceof FirAnnotationCall) {
            return true;
        }
        if (callSite instanceof FirCollectionLiteral) {
            return !FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.inferenceComponents.getSession()).supportsFeature(LanguageFeature.CollectionLiterals) || bodyResolveContext.getIsInsideAnnotationContext();
        }
        if (!(callSite instanceof FirResolvable) && !(callSite instanceof FirVariableAssignment)) {
            return false;
        }
        ConeResolutionAtom dispatchReceiver = candidate.getDispatchReceiver();
        if (dispatchReceiver != null && (expression2 = dispatchReceiver.getExpression()) != null && isReceiverPostponed(expression2)) {
            return false;
        }
        ConeResolutionAtom givenExtensionReceiver = candidate.getGivenExtensionReceiver();
        if (givenExtensionReceiver != null && (expression = givenExtensionReceiver.getExpression()) != null && isReceiverPostponed(expression)) {
            return false;
        }
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
        if (firCallableSymbol != null && FirCallableSymbolKt.getHasContextParameters(firCallableSymbol)) {
            return false;
        }
        FirBasedSymbol<?> symbol2 = candidate.getSymbol();
        FirCallableSymbol<?> firCallableSymbol2 = symbol2 instanceof FirCallableSymbol ? (FirCallableSymbol) symbol2 : null;
        FirResolvedTypeRef firResolvedTypeRefTryCalculateReturnType = firCallableSymbol2 != null ? bodyResolveContext.getReturnTypeCalculator().tryCalculateReturnType(firCallableSymbol2) : null;
        if (firResolvedTypeRefTryCalculateReturnType != null && (coneType = firResolvedTypeRefTryCalculateReturnType.getConeType()) != null && containsNotFixedTypeVariables(coneType)) {
            return false;
        }
        List<FirExpression> arguments = candidate.getCallInfo().getArguments();
        if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                if (!isTrivialArgument((FirExpression) it.next())) {
                    return false;
                }
            }
        }
        return true;
    }

    private final ConeKotlinType prepareContextForTypeVariableForSemiFixation(final ConstraintSystemCompletionContext constraintSystemCompletionContext, final ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor, boolean z, final Function0<? extends ConeKotlinType> function0) {
        Set outerTypeVariables;
        if (is21Mode()) {
            outerTypeVariables = constraintSystemCompletionContext.getNotFixedTypeVariables().keySet();
        } else {
            outerTypeVariables = constraintSystemCompletionContext.getOuterTypeVariables();
            if (outerTypeVariables == null) {
                outerTypeVariables = SetsKt.emptySet();
            }
        }
        return (ConeKotlinType) constraintSystemCompletionContext.withTypeVariablesThatAreCountedAsProperTypes(outerTypeVariables, z, new Function0() { // from class: rb5
            public final Object invoke() {
                return FirPCLAInferenceSession.d(this.b, constraintSystemCompletionContext, coneTypeVariableTypeConstructor, function0);
            }
        });
    }

    private final <T> T runWithSpecifiedCurrentCommonSystem(NewConstraintSystemImpl newSystem, Function0<? extends T> block) {
        NewConstraintSystemImpl newConstraintSystemImpl = this.currentCommonSystem;
        try {
            this.currentCommonSystem = newSystem;
            return (T) block.invoke();
        } finally {
            this.currentCommonSystem = newConstraintSystemImpl;
        }
    }

    public static /* synthetic */ Pair semiFixCurrentResultIfTypeVariableAndReturnBinding$default(FirPCLAInferenceSession firPCLAInferenceSession, ConeKotlinType coneKotlinType, NewConstraintSystemImpl newConstraintSystemImpl, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return firPCLAInferenceSession.semiFixCurrentResultIfTypeVariableAndReturnBinding(coneKotlinType, newConstraintSystemImpl, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeKotlinType semiFixCurrentResultIfTypeVariableAndReturnBinding$lambda$1$0(FirPCLAInferenceSession firPCLAInferenceSession, NewConstraintSystemImpl newConstraintSystemImpl, MutableVariableWithConstraints mutableVariableWithConstraints) {
        ConeKotlinType coneKotlinTypeFindResultIfThereIsEqualsConstraint = firPCLAInferenceSession.inferenceComponents.getResultTypeResolver().findResultIfThereIsEqualsConstraint(newConstraintSystemImpl, mutableVariableWithConstraints, true);
        if (coneKotlinTypeFindResultIfThereIsEqualsConstraint != null) {
            return coneKotlinTypeFindResultIfThereIsEqualsConstraint;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeKotlinType semiFixCurrentResultIfTypeVariableAndReturnBinding$lambda$1$2(FirPCLAInferenceSession firPCLAInferenceSession, NewConstraintSystemImpl newConstraintSystemImpl, MutableVariableWithConstraints mutableVariableWithConstraints) {
        return firPCLAInferenceSession.inferenceComponents.getResultTypeResolver().findResultType(newConstraintSystemImpl, mutableVariableWithConstraints, TypeVariableDirectionCalculator.ResolveDirection.UNKNOWN);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void updateReturnTypeWithCurrentSubstitutor(FirExpression firExpression, ResolutionMode resolutionMode) {
        NewConstraintSystemImpl system;
        Candidate candidate;
        FirResolvable firResolvable = firExpression instanceof FirResolvable ? (FirResolvable) firExpression : null;
        if (firResolvable == null || (candidate = CandidateFactoryKt.candidate(firResolvable)) == null || (system = candidate.getSystem()) == null) {
            system = this.currentCommonSystem;
        }
        NewConstraintSystemImpl newConstraintSystemImpl = system;
        ConeKotlinType coneKotlinTypeSubstituteOrNull = ((ConeSubstitutor) newConstraintSystemImpl.buildCurrentSubstitutor(resolutionMode instanceof ResolutionMode.ReceiverResolution ? semiFixCurrentResultIfTypeVariableAndReturnBinding$default(this, FirTypeUtilsKt.getResolvedType(firExpression), newConstraintSystemImpl, false, 4, null) : null)).substituteOrNull(FirTypeUtilsKt.getResolvedType(firExpression));
        if (coneKotlinTypeSubstituteOrNull != null) {
            firExpression.replaceConeTypeOrNull(coneKotlinTypeSubstituteOrNull);
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public void addSubtypeConstraintIfCompatible(ConeKotlinType lowerType, ConeKotlinType upperType, FirElement element) {
        lowerType.getClass();
        upperType.getClass();
        element.getClass();
        FirInferenceLogger inferenceLogger = FirInferenceLoggerKt.getInferenceLogger(this.inferenceComponents.getSession());
        if (inferenceLogger != null) {
            inferenceLogger.logStage("Some addSubtypeConstraintIfCompatible() with currentCommonSystem inside PCLA inference session", this.currentCommonSystem);
        }
        ConstraintSystemBuilderKt.addSubtypeConstraintIfCompatible(this.currentCommonSystem, lowerType, upperType, ConeExpectedTypeConstraintPosition.INSTANCE);
    }

    public final void applyResultsToMainCandidate() {
        this.outerCandidate.getSystem().replaceContentWith(this.currentCommonSystem.currentStorage());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public ConstraintStorage baseConstraintStorageForCandidate(Candidate candidate, BodyResolveContext bodyResolveContext) {
        candidate.getClass();
        bodyResolveContext.getClass();
        if (mightBeAnalyzedAndCompletedIndependently(candidate, bodyResolveContext)) {
            return null;
        }
        return this.currentCommonSystem.currentStorage();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public ConstraintSystemCompletionMode customCompletionModeInsteadOfFull(FirResolvable call) {
        call.getClass();
        Candidate candidate = CandidateFactoryKt.candidate(call);
        if (candidate == null || !candidate.getUsedOuterCs()) {
            return null;
        }
        return ConstraintSystemCompletionMode.PCLA_POSTPONED_CALL;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public ConeKotlinType getAndSemiFixCurrentResultIfTypeVariable(ConeKotlinType type) {
        type.getClass();
        FirInferenceLogger inferenceLogger = FirInferenceLoggerKt.getInferenceLogger(this.inferenceComponents.getSession());
        if (inferenceLogger != null) {
            inferenceLogger.logStage("Some getAndSemiFixCurrentResultIfTypeVariable() with currentCommonSystem inside PCLA inference session", this.currentCommonSystem);
        }
        Pair pairSemiFixCurrentResultIfTypeVariableAndReturnBinding$default = semiFixCurrentResultIfTypeVariableAndReturnBinding$default(this, type, this.currentCommonSystem, false, 4, null);
        if (pairSemiFixCurrentResultIfTypeVariableAndReturnBinding$default != null) {
            return (ConeKotlinType) pairSemiFixCurrentResultIfTypeVariableAndReturnBinding$default.getSecond();
        }
        return null;
    }

    public final NewConstraintSystemImpl getCurrentCommonSystem() {
        return this.currentCommonSystem;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public Map<TypeConstructorMarker, KotlinTypeMarker> getSemiFixedVariables() {
        return this.semiFixedVariables;
    }

    public final void integrateChildSession(Collection<? extends ConeResolutionAtom> childCalls, ConstraintStorage childStorage, Function1<? super ConeSubstitutor, Unit> onCompletionResultsWriting) {
        childCalls.getClass();
        childStorage.getClass();
        onCompletionResultsWriting.getClass();
        CollectionsKt.addAll(this.outerCandidate.getPostponedPCLACalls(), childCalls);
        this.currentCommonSystem.replaceContentWith(childStorage);
        this.outerCandidate.getOnPCLACompletionResultsWritingCallbacks().add(onCompletionResultsWriting);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public <T extends FirExpression & FirResolvable> void processPartiallyResolvedCall(T call, ResolutionMode resolutionMode, ConstraintSystemCompletionMode completionMode) {
        call.getClass();
        resolutionMode.getClass();
        completionMode.getClass();
        updateReturnTypeWithCurrentSubstitutor(call, resolutionMode);
        Candidate candidate = CandidateFactoryKt.candidate(call);
        if (candidate == null || !candidate.getUsedOuterCs() || (resolutionMode instanceof ResolutionMode.Delegate)) {
            return;
        }
        this.currentCommonSystem.replaceContentWith(candidate.getSystem().currentStorage());
        if (completionMode == ConstraintSystemCompletionMode.PCLA_POSTPONED_CALL) {
            this.outerCandidate.getPostponedPCLACalls().add(new ConeAtomWithCandidate(call, candidate));
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public ConstraintStorage runLambdaCompletion(Candidate candidate, boolean forOverloadByLambdaReturnType, Function0<Unit> block) {
        candidate.getClass();
        block.getClass();
        if (!forOverloadByLambdaReturnType) {
            runWithSpecifiedCurrentCommonSystem(candidate.getSystem(), block);
            return null;
        }
        NewConstraintSystemImpl newConstraintSystemImplCreateConstraintSystem$default = InferenceComponents.createConstraintSystem$default(this.inferenceComponents, null, 1, null);
        newConstraintSystemImplCreateConstraintSystem$default.setBaseSystem(this.currentCommonSystem.currentStorage());
        runWithSpecifiedCurrentCommonSystem(newConstraintSystemImplCreateConstraintSystem$default, block);
        return newConstraintSystemImplCreateConstraintSystem$default.currentStorage();
    }

    public final Pair<ConeTypeVariableTypeConstructor, ConeKotlinType> semiFixCurrentResultIfTypeVariableAndReturnBinding(ConeKotlinType type, NewConstraintSystemImpl myCs, boolean allowFixationToOtherTypeVariables) {
        ConeTypeVariableTypeConstructor typeConstructor;
        type.getClass();
        myCs.getClass();
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(type);
        ConeTypeVariableType coneTypeVariableType = coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeTypeVariableType ? (ConeTypeVariableType) coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound : null;
        if (coneTypeVariableType != null && (typeConstructor = coneTypeVariableType.getTypeConstructor()) != null) {
            if (myCs.getAllTypeVariables().containsKey(typeConstructor)) {
                final MutableVariableWithConstraints mutableVariableWithConstraints = (MutableVariableWithConstraints) myCs.getNotFixedTypeVariables().get(typeConstructor);
                if (mutableVariableWithConstraints == null) {
                    return null;
                }
                final NewConstraintSystemImpl builder = myCs.getBuilder();
                Set outerTypeVariables = myCs.getOuterTypeVariables();
                if (outerTypeVariables == null) {
                    outerTypeVariables = SetsKt.emptySet();
                }
                if (outerTypeVariables.contains(typeConstructor) && !is21Mode()) {
                    ConeKotlinType coneKotlinTypePrepareContextForTypeVariableForSemiFixation = prepareContextForTypeVariableForSemiFixation(builder, typeConstructor, allowFixationToOtherTypeVariables, new Function0() { // from class: sb5
                        public final Object invoke() {
                            return FirPCLAInferenceSession.semiFixCurrentResultIfTypeVariableAndReturnBinding$lambda$1$0(this.b, builder, mutableVariableWithConstraints);
                        }
                    });
                    if (coneKotlinTypePrepareContextForTypeVariableForSemiFixation != null) {
                        return new Pair<>(typeConstructor, coneKotlinTypePrepareContextForTypeVariableForSemiFixation);
                    }
                    return null;
                }
                ConeKotlinType coneKotlinTypePrepareContextForTypeVariableForSemiFixation2 = prepareContextForTypeVariableForSemiFixation(builder, typeConstructor, allowFixationToOtherTypeVariables, new Function0() { // from class: tb5
                    public final Object invoke() {
                        return FirPCLAInferenceSession.semiFixCurrentResultIfTypeVariableAndReturnBinding$lambda$1$2(this.b, builder, mutableVariableWithConstraints);
                    }
                });
                if (coneKotlinTypePrepareContextForTypeVariableForSemiFixation2 == null) {
                    return null;
                }
                TypeVariableMarker typeVariable = mutableVariableWithConstraints.getTypeVariable();
                builder.addEqualityConstraint(builder.defaultType(typeVariable), coneKotlinTypePrepareContextForTypeVariableForSemiFixation2, new ConeSemiFixVariableConstraintPosition(typeVariable));
                getSemiFixedVariables().put(typeConstructor, coneKotlinTypePrepareContextForTypeVariableForSemiFixation2);
                return new Pair<>(typeConstructor, coneKotlinTypePrepareContextForTypeVariableForSemiFixation2);
            }
            z1f.a(typeConstructor, " not found");
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public void semiFixTypeVariablesAllowingFixationToOtherOnes(ConeKotlinType type, NewConstraintSystemImpl myCs) {
        type.getClass();
        myCs.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{type});
        while (!listMutableListOf.isEmpty()) {
            ConeKotlinType coneKotlinType = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf);
            semiFixCurrentResultIfTypeVariableAndReturnBinding(coneKotlinType, myCs, true);
            if (coneKotlinType instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
                listMutableListOf.add(coneFlexibleType.getLowerBound());
                if (!coneFlexibleType.getIsTrivial()) {
                    listMutableListOf.add(coneFlexibleType.getUpperBound());
                }
            } else if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
            } else if (coneKotlinType instanceof ConeIntersectionType) {
                listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType).getIntersectedTypes());
            } else {
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
                    if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                        listMutableListOf.add(coneKotlinTypeProjection.getType());
                    }
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public void updateExpressionReturnTypeWithCurrentSubstitutorInPCLA(FirExpression expression, ResolutionMode resolutionMode) {
        expression.getClass();
        resolutionMode.getClass();
        updateReturnTypeWithCurrentSubstitutor(expression, resolutionMode);
    }
}
