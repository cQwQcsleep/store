package org.jetbrains.kotlin.fir.resolve.calls.stages;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentTypeMismatch;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeAtomWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ConeCollectionLiteralAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeContextSensitiveAlternativeForQualifierAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeLambdaWithTypeVariableAsExpectedTypeAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithPostponedChild;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithSingleChild;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedCallableReferenceAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeSimpleLeafResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeSimpleNameForContextSensitiveResolution;
import org.jetbrains.kotlin.fir.resolve.calls.DynamicReceiverExpectedButWasNonDynamic;
import org.jetbrains.kotlin.fir.resolve.calls.ErrorTypeInArguments;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableNullableReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableWrongReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.NullForNotNullType;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.UnstableSmartCast;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.inference.ConeTypeVariableForLambdaParameterType;
import org.jetbrains.kotlin.fir.resolve.inference.ConeTypeVariableForLambdaReturnType;
import org.jetbrains.kotlin.fir.resolve.inference.ConstraintSystemCompleterKt;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeArgumentConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeExplicitTypeParameterConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeReceiverConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeRegularLambdaArgumentConstraintPosition;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeIntersector;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableTypeConstructor;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilder;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilderKt;
import org.jetbrains.kotlin.resolve.calls.inference.components.TypeVariableDirectionCalculator;
import org.jetbrains.kotlin.resolve.calls.inference.model.ArgumentConstraintPosition;
import org.jetbrains.kotlin.resolve.calls.inference.model.Constraint;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintKind;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintPosition;
import org.jetbrains.kotlin.resolve.calls.inference.model.SimpleConstraintSystemConstraintPosition;
import org.jetbrains.kotlin.resolve.calls.inference.model.VariableWithConstraints;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContextHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001MB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JL\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014JT\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018JF\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\u001d2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0014\u0010\u0004\u001a\u00020\u0005*\u00020 2\u0006\u0010\b\u001a\u00020\tH\u0002J\u001e\u0010!\u001a\u00020\u0005*\u00020 2\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020\u0011H\u0002J\u0018\u0010#\u001a\u0006\u0012\u0002\b\u00030$*\u00020 2\u0006\u0010\b\u001a\u00020\tH\u0002J2\u0010\u0015\u001a\u00020\u0005*\u00020 2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u00112\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\f\u0010%\u001a\u00020\u0011*\u00020 H\u0002J$\u0010&\u001a\u00020\u0005*\u00020 2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020)H\u0002J\u001c\u0010*\u001a\u00020+*\u00020 2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0016\u001a\u00020\u000bH\u0002J\u0014\u0010.\u001a\u00020\u000b*\u00020 2\u0006\u0010/\u001a\u00020\u000bH\u0002J\u0014\u00100\u001a\u00020\u0005*\u00020 2\u0006\u0010\b\u001a\u00020\u001dH\u0002J\u001c\u00101\u001a\u00020\u0005*\u00020 2\u0006\u0010\b\u001a\u00020\u001d2\u0006\u0010,\u001a\u000202H\u0002J\u001c\u00103\u001a\u00020\u0005*\u00020 2\u0006\u0010\b\u001a\u00020\u001d2\u0006\u0010,\u001a\u000204H\u0002J\u0014\u00105\u001a\u00020\u0005*\u00020 2\u0006\u0010\b\u001a\u00020\u001dH\u0002J\u0014\u00106\u001a\u00020\u0005*\u00020 2\u0006\u0010\b\u001a\u00020\u001dH\u0002J\u0014\u00107\u001a\u00020\u0011*\u00020 2\u0006\u0010\b\u001a\u00020\u001dH\u0002J&\u00108\u001a\u00020\u001a*\u00020 2\u0006\u0010\b\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u00112\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\u001e\u0010:\u001a\u00020\u001a*\u00020 2\u0006\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010\u0018H\u0002J&\u0010>\u001a\u0004\u0018\u00010\u000b*\u00020?2\u0006\u0010@\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000bH\u0002R\u0018\u0010B\u001a\u00020<*\u00020\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bC\u0010DR\u0018\u0010E\u001a\u00020F*\u00020\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0018\u0010I\u001a\u00020J*\u00020\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bK\u0010L¨\u0006N"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ArgumentCheckingProcessor;", Argument.Delimiters.none, "<init>", "()V", "resolveArgumentExpression", Argument.Delimiters.none, "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "isReceiver", Argument.Delimiters.none, "isDispatch", "anonymousFunctionIfReturnExpression", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "resolvePlainArgumentType", "argumentType", "sourceForReceiver", "Lorg/jetbrains/kotlin/KtSourceElement;", "createResolvedLambdaAtomDuringCompletion", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "csBuilder", "Lorg/jetbrains/kotlin/resolve/calls/inference/ConstraintSystemBuilder;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtomWithPostponedChild;", "returnTypeVariable", "Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeVariableForLambdaReturnType;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ArgumentCheckingProcessor$ArgumentContext;", "resolvePlainExpressionArgument", "useNullableArgumentType", "createArgumentConstraintPosition", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ArgumentConstraintPosition;", "shouldRunConversion", "checkApplicabilityForArgumentType", "argumentTypeBeforeCapturing", "position", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintPosition;", "subtypeError", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "prepareTypeForArgumentTypeMismatch", ModuleXmlParser.TYPE, "preprocessCallableReference", "preprocessSimpleNameReferenceForContextSensitiveResolution", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "preprocessQualifierWithContextSensitiveAlternative", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifierWithContextSensitiveAlternative;", "preprocessCollectionLiteral", "preprocessLambdaArgument", "createLambdaWithTypeVariableAsExpectedTypeAtomIfNeeded", "createResolvedLambdaAtom", "duringCompletion", "extractLambdaInfo", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "sourceForFunctionExpression", "argumentTypeWithCustomConversion", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "lambdaExpression", "getLambdaExpression", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtomWithPostponedChild;)Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "callableReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "getCallableReferenceExpression", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtomWithPostponedChild;)Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "collectionLiteralExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "getCollectionLiteralExpression", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtomWithPostponedChild;)Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "ArgumentContext", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ArgumentCheckingProcessor {
    public static final ArgumentCheckingProcessor INSTANCE = new ArgumentCheckingProcessor();

    private ArgumentCheckingProcessor() {
    }

    private final ConeKotlinType argumentTypeWithCustomConversion(ConeInferenceContext coneInferenceContext, FirSession firSession, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        ConeClassLikeType coneClassLikeTypeCustomFunctionTypeToSimpleFunctionType;
        ConeKotlinType coneKotlinTypeFindSubtypeOfBasicFunctionType;
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = FunctionalTypeUtilsKt.functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default == null || FunctionTypeKindKt.isBasicFunctionOrKFunction(functionTypeKindFunctionTypeKind$default) || !functionTypeKindFunctionTypeKind$default.getSupportsConversionFromSimpleFunctionType() || (coneKotlinTypeFindSubtypeOfBasicFunctionType = FunctionalTypeUtilsKt.findSubtypeOfBasicFunctionType(coneKotlinType2, firSession, (coneClassLikeTypeCustomFunctionTypeToSimpleFunctionType = FunctionalTypeUtilsKt.customFunctionTypeToSimpleFunctionType(coneKotlinType, firSession)))) == null) {
            return null;
        }
        List<ConeClassLikeType> listFastCorrespondingSupertypes = coneInferenceContext.fastCorrespondingSupertypes(ConeTypeUtilsKt.unwrapLowerBound(coneKotlinTypeFindSubtypeOfBasicFunctionType), coneInferenceContext.m691typeConstructor((RigidTypeMarker) coneClassLikeTypeCustomFunctionTypeToSimpleFunctionType));
        ConeClassLikeType coneClassLikeType = listFastCorrespondingSupertypes != null ? (ConeClassLikeType) CollectionsKt.firstOrNull(listFastCorrespondingSupertypes) : null;
        if (coneClassLikeType == null) {
            coneClassLikeType = null;
        }
        if (coneClassLikeType == null) {
            return null;
        }
        ConeTypeProjection[] typeArguments = coneClassLikeType.getTypeArguments();
        ArrayList arrayList = new ArrayList(typeArguments.length);
        for (ConeTypeProjection coneTypeProjection : typeArguments) {
            ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
            if (type == null) {
                type = firSession.getBuiltinTypes().getNullableAnyType().getConeType();
            }
            arrayList.add(type);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return ResolveUtilsKt.createFunctionType$default(functionTypeKindFunctionTypeKind$default, arrayList.subList(0, CollectionsKt.getLastIndex(arrayList)), null, (ConeKotlinType) CollectionsKt.last(arrayList), null, 16, null);
    }

    private final void checkApplicabilityForArgumentType(ArgumentContext argumentContext, ConeResolutionAtom coneResolutionAtom, ConeKotlinType coneKotlinType, ConstraintPosition constraintPosition) {
        if (argumentContext.getExpectedType() == null) {
            return;
        }
        ConeKotlinType coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded = ArgumentUtilsKt.captureFromTypeParameterUpperBoundIfNeeded(coneKotlinType, argumentContext.getExpectedType(), argumentContext.getSession());
        if (argumentContext.isReceiver() && argumentContext.isDispatch()) {
            if (ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(argumentContext.getExpectedType()) || !ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded)) {
                return;
            }
            argumentContext.reportDiagnostic(new InapplicableWrongReceiver(argumentContext.getExpectedType(), coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded));
            return;
        }
        if (argumentContext.isReceiver() && (argumentContext.getExpectedType() instanceof ConeDynamicType) && !(coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded instanceof ConeDynamicType)) {
            argumentContext.reportDiagnostic(new DynamicReceiverExpectedButWasNonDynamic(coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded));
            return;
        }
        if (ConstraintSystemBuilderKt.addSubtypeConstraintIfCompatible(argumentContext.getCsBuilder(), coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded, argumentContext.getExpectedType(), constraintPosition)) {
            return;
        }
        FirExpression expression = coneResolutionAtom.getExpression();
        FirSmartCastExpression firSmartCastExpression = expression instanceof FirSmartCastExpression ? (FirSmartCastExpression) expression : null;
        if (firSmartCastExpression != null && !firSmartCastExpression.isStable()) {
            if (ConstraintSystemBuilderKt.addSubtypeConstraintIfCompatible(argumentContext.getCsBuilder(), FirTypeUtilsKt.getConeType(firSmartCastExpression.getSmartcastType()), argumentContext.getExpectedType(), constraintPosition)) {
                argumentContext.reportDiagnostic(new UnstableSmartCast(firSmartCastExpression, argumentContext.getExpectedType(), InferenceUtilsKt.isTypeMismatchDueToNullability(TypeComponentsKt.getTypeContext(argumentContext.getSession()), coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded, argumentContext.getExpectedType()), false));
                return;
            }
        }
        if (!argumentContext.isReceiver()) {
            argumentContext.reportDiagnostic(subtypeError(argumentContext, coneResolutionAtom.getExpression(), coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded));
            return;
        }
        if (ConstraintSystemBuilderKt.addSubtypeConstraintIfCompatible(argumentContext.getCsBuilder(), coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded, TypeUtilsKt.withNullability$default(argumentContext.getExpectedType(), true, TypeComponentsKt.getTypeContext(argumentContext.getSession()), null, false, 12, null), constraintPosition)) {
            argumentContext.reportDiagnostic(new InapplicableNullableReceiver(coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded));
        } else {
            argumentContext.getCsBuilder().addSubtypeConstraint(coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded, argumentContext.getExpectedType(), constraintPosition);
            argumentContext.reportDiagnostic(new InapplicableWrongReceiver(argumentContext.getExpectedType(), coneKotlinTypeCaptureFromTypeParameterUpperBoundIfNeeded));
        }
    }

    private final ArgumentConstraintPosition<?> createArgumentConstraintPosition(ArgumentContext argumentContext, ConeResolutionAtom coneResolutionAtom) {
        FirAnonymousFunction anonymousFunctionIfReturnExpression = argumentContext.getAnonymousFunctionIfReturnExpression();
        return anonymousFunctionIfReturnExpression == null ? new ConeArgumentConstraintPosition(coneResolutionAtom.getExpression()) : new ConeRegularLambdaArgumentConstraintPosition(anonymousFunctionIfReturnExpression, coneResolutionAtom.getExpression());
    }

    private final boolean createLambdaWithTypeVariableAsExpectedTypeAtomIfNeeded(ArgumentContext argumentContext, ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild) {
        VariableWithConstraints variableWithConstraints;
        ConeKotlinType coneKotlinType;
        Object next;
        KotlinTypeMarker type;
        if (argumentContext.getExpectedType() == null || !argumentContext.getCsBuilder().isTypeVariable(argumentContext.getExpectedType()) || (variableWithConstraints = (VariableWithConstraints) argumentContext.getCsBuilder().currentStorage().getNotFixedTypeVariables().get(TypeSystemContextHelpersKt.typeConstructor(argumentContext.getExpectedType(), argumentContext.getContext().getTypeContext()))) == null) {
            return false;
        }
        Iterator it = variableWithConstraints.getConstraints().iterator();
        while (true) {
            coneKotlinType = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Constraint constraint = (Constraint) next;
            if (constraint.getKind() == ConstraintKind.EQUALITY && (constraint.getPosition().getFrom() instanceof ConeExplicitTypeParameterConstraintPosition)) {
                break;
            }
        }
        Constraint constraint2 = (Constraint) next;
        if (constraint2 != null && (type = constraint2.getType()) != null) {
            coneKotlinType = (ConeKotlinType) type;
        }
        if (coneKotlinType != null && coneKotlinType.getTypeArguments().length == 0) {
            return false;
        }
        ConeLambdaWithTypeVariableAsExpectedTypeAtom coneLambdaWithTypeVariableAsExpectedTypeAtom = new ConeLambdaWithTypeVariableAsExpectedTypeAtom(getLambdaExpression(coneResolutionAtomWithPostponedChild), argumentContext.getExpectedType(), argumentContext.getCandidate(), argumentContext.getAnonymousFunctionIfReturnExpression());
        argumentContext.getCandidate().addPostponedAtom(coneLambdaWithTypeVariableAsExpectedTypeAtom);
        coneResolutionAtomWithPostponedChild.setPostponedSubAtom(coneLambdaWithTypeVariableAsExpectedTypeAtom);
        return true;
    }

    private final ConeResolvedLambdaAtom createResolvedLambdaAtom(ArgumentContext argumentContext, ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild, boolean z, ConeTypeVariableForLambdaReturnType coneTypeVariableForLambdaReturnType) {
        FirAnonymousFunctionExpression lambdaExpression = getLambdaExpression(coneResolutionAtomWithPostponedChild);
        FirAnonymousFunction anonymousFunction = lambdaExpression.getAnonymousFunction();
        ConeResolvedLambdaAtom coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType = org.jetbrains.kotlin.fir.resolve.inference.InferenceUtilsKt.extractLambdaInfoFromFunctionType(argumentContext.getExpectedType(), lambdaExpression, anonymousFunction, coneTypeVariableForLambdaReturnType, argumentContext.getContext().getBodyResolveComponents(), z, lambdaExpression.getSource());
        if (coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType == null) {
            coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType = extractLambdaInfo(argumentContext, lambdaExpression, lambdaExpression.getSource());
        }
        coneResolutionAtomWithPostponedChild.setPostponedSubAtom(coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType);
        argumentContext.getCandidate().addPostponedAtom(coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType);
        if (argumentContext.getExpectedType() != null) {
            List<ConeKotlinType> parameterTypes$org_jetbrains_kotlin_resolve = coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType.getParameterTypes$org_jetbrains_kotlin_resolve();
            FunctionTypeKind functionTypeKindExtractSingleSpecialKindForFunction = FirFunctionTypeKindServiceKt.getFunctionTypeService(argumentContext.getContext().getSession()).extractSingleSpecialKindForFunction(anonymousFunction.getSymbol());
            if (functionTypeKindExtractSingleSpecialKindForFunction == null) {
                FunctionTypeKind expectedFunctionTypeKind = coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType.getExpectedFunctionTypeKind();
                functionTypeKindExtractSingleSpecialKindForFunction = expectedFunctionTypeKind != null ? expectedFunctionTypeKind.nonReflectKind() : null;
                if (functionTypeKindExtractSingleSpecialKindForFunction == null) {
                    functionTypeKindExtractSingleSpecialKindForFunction = FunctionTypeKind.Function.INSTANCE;
                }
            }
            ConeLookupTagBasedType coneLookupTagBasedTypeCreateFunctionType = ResolveUtilsKt.createFunctionType(functionTypeKindExtractSingleSpecialKindForFunction, parameterTypes$org_jetbrains_kotlin_resolve, coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType.getReceiverType(), coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType.getReturnType(), coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType.getContextParameterTypes$org_jetbrains_kotlin_resolve());
            ArgumentConstraintPosition<?> argumentConstraintPositionCreateArgumentConstraintPosition = createArgumentConstraintPosition(argumentContext, coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType);
            if (z) {
                argumentContext.getCsBuilder().addSubtypeConstraint(coneLookupTagBasedTypeCreateFunctionType, argumentContext.getExpectedType(), argumentConstraintPositionCreateArgumentConstraintPosition);
                return coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType;
            }
            if (!ConstraintSystemBuilderKt.addSubtypeConstraintIfCompatible(argumentContext.getCsBuilder(), coneLookupTagBasedTypeCreateFunctionType, argumentContext.getExpectedType(), argumentConstraintPositionCreateArgumentConstraintPosition)) {
                argumentContext.reportDiagnostic(new ArgumentTypeMismatch(argumentContext.getExpectedType(), coneLookupTagBasedTypeCreateFunctionType, lambdaExpression, InferenceUtilsKt.isTypeMismatchDueToNullability(TypeComponentsKt.getTypeContext(argumentContext.getContext().getSession()), coneLookupTagBasedTypeCreateFunctionType, argumentContext.getExpectedType()), argumentContext.getAnonymousFunctionIfReturnExpression(), false, 32, null));
            }
        }
        return coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType;
    }

    private final ConeResolvedLambdaAtom extractLambdaInfo(ArgumentContext argumentContext, FirAnonymousFunctionExpression firAnonymousFunctionExpression, KtSourceElement ktSourceElement) {
        ConeRigidType coneRigidTypeLowerBoundIfFlexible;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible2;
        ConeKotlinType expectedType = argumentContext.getExpectedType();
        if (((expectedType == null || (coneRigidTypeLowerBoundIfFlexible2 = ConeTypeUtilsKt.lowerBoundIfFlexible(expectedType)) == null) ? null : FunctionalTypeUtilsKt.functionTypeKind$default(coneRigidTypeLowerBoundIfFlexible2, argumentContext.getSession(), false, 2, (Object) null)) != null) {
            dt1.a("Currently, we only extract lambda info from its shape when expected type is not function, but ", argumentContext.getExpectedType());
            return null;
        }
        FirAnonymousFunction anonymousFunction = firAnonymousFunctionExpression.getAnonymousFunction();
        ConeTypeVariableForLambdaReturnType coneTypeVariableForLambdaReturnType = new ConeTypeVariableForLambdaReturnType(anonymousFunction, "_R");
        ConeKotlinType receiverType = InferenceUtilsKt.getReceiverType(anonymousFunction);
        ConeKotlinType returnType = InferenceUtilsKt.getReturnType(anonymousFunction);
        if (returnType == null) {
            returnType = coneTypeVariableForLambdaReturnType.getDefaultType();
        }
        ConeKotlinType coneKotlinType = returnType;
        ConeDynamicType coneDynamicTypeCreate$default = Intrinsics.areEqual(argumentContext.getCandidate().getSymbol().getOrigin(), FirDeclarationOrigin.DynamicScope.INSTANCE) ? TypeUtilsKt.create$default(ConeDynamicType.Companion, argumentContext.getSession(), null, 2, null) : null;
        List<FirValueParameter> valueParameters = anonymousFunction.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        int i = 0;
        for (Object obj : valueParameters) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirResolvedTypeRef returnTypeRef = ((FirValueParameter) obj).getReturnTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
            ConeDynamicType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (coneType == null) {
                coneType = null;
            }
            if (coneType == null) {
                if (coneDynamicTypeCreate$default != null) {
                    coneType = coneDynamicTypeCreate$default;
                } else {
                    ConeTypeVariableForLambdaParameterType coneTypeVariableForLambdaParameterType = new ConeTypeVariableForLambdaParameterType("_RP" + i);
                    argumentContext.getCsBuilder().registerVariable(coneTypeVariableForLambdaParameterType);
                    coneType = coneTypeVariableForLambdaParameterType.getDefaultType();
                }
            }
            arrayList.add(coneType);
            i = i2;
        }
        List<FirValueParameter> contextParameters = anonymousFunction.getContextParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
        int i3 = 0;
        for (Object obj2 : contextParameters) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirResolvedTypeRef returnTypeRef2 = ((FirValueParameter) obj2).getReturnTypeRef();
            FirResolvedTypeRef firResolvedTypeRef2 = returnTypeRef2 instanceof FirResolvedTypeRef ? returnTypeRef2 : null;
            ConeDynamicType coneType2 = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
            if (coneType2 == null) {
                coneType2 = null;
            }
            if (coneType2 == null) {
                if (coneDynamicTypeCreate$default != null) {
                    coneType2 = coneDynamicTypeCreate$default;
                } else {
                    ConeTypeVariableForLambdaParameterType coneTypeVariableForLambdaParameterType2 = new ConeTypeVariableForLambdaParameterType("_C" + i3);
                    argumentContext.getCsBuilder().registerVariable(coneTypeVariableForLambdaParameterType2);
                    coneType2 = coneTypeVariableForLambdaParameterType2.getDefaultType();
                }
            }
            arrayList2.add(coneType2);
            i3 = i4;
        }
        boolean zAreEqual = Intrinsics.areEqual(coneKotlinType, coneTypeVariableForLambdaReturnType.getDefaultType());
        if (zAreEqual) {
            argumentContext.getCsBuilder().registerVariable(coneTypeVariableForLambdaReturnType);
        }
        ConeKotlinType expectedType2 = argumentContext.getExpectedType();
        FirResolvedTypeRef typeRef = anonymousFunction.getTypeRef();
        FirResolvedTypeRef firResolvedTypeRef3 = typeRef instanceof FirResolvedTypeRef ? typeRef : null;
        ConeKotlinType coneType3 = firResolvedTypeRef3 != null ? firResolvedTypeRef3.getConeType() : null;
        if (coneType3 == null) {
            coneType3 = null;
        }
        return new ConeResolvedLambdaAtom(firAnonymousFunctionExpression, expectedType2, (coneType3 == null || (coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneType3)) == null) ? null : FunctionalTypeUtilsKt.functionTypeKind$default(coneRigidTypeLowerBoundIfFlexible, argumentContext.getSession(), false, 2, (Object) null), receiverType, arrayList2, arrayList, coneKotlinType, zAreEqual ? coneTypeVariableForLambdaReturnType : null, false, ktSourceElement);
    }

    private final FirCallableReferenceAccess getCallableReferenceExpression(ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild) {
        FirExpression expression = coneResolutionAtomWithPostponedChild.getExpression();
        FirCallableReferenceAccess firCallableReferenceAccess = expression instanceof FirCallableReferenceAccess ? (FirCallableReferenceAccess) expression : null;
        if (firCallableReferenceAccess != null) {
            return firCallableReferenceAccess;
        }
        k2d.a("Expected callable reference");
        return null;
    }

    private final FirCollectionLiteral getCollectionLiteralExpression(ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild) {
        FirExpression expression = coneResolutionAtomWithPostponedChild.getExpression();
        FirCollectionLiteral firCollectionLiteral = expression instanceof FirCollectionLiteral ? (FirCollectionLiteral) expression : null;
        if (firCollectionLiteral != null) {
            return firCollectionLiteral;
        }
        k2d.a("Expected collection literal expression");
        return null;
    }

    private final FirAnonymousFunctionExpression getLambdaExpression(ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild) {
        FirExpression expression = coneResolutionAtomWithPostponedChild.getExpression();
        FirAnonymousFunctionExpression firAnonymousFunctionExpression = expression instanceof FirAnonymousFunctionExpression ? (FirAnonymousFunctionExpression) expression : null;
        if (firAnonymousFunctionExpression != null) {
            return firAnonymousFunctionExpression;
        }
        k2d.a("Expected anonymous function expression");
        return null;
    }

    private final ConeKotlinType prepareTypeForArgumentTypeMismatch(ArgumentContext argumentContext, ConeKotlinType coneKotlinType) {
        ConeClassLikeType coneClassLikeType;
        ArrayList arrayList;
        List constraints;
        if (coneKotlinType instanceof ConeTypeVariableType) {
            ConeTypeVariableType coneTypeVariableType = (ConeTypeVariableType) coneKotlinType;
            ConeTypeVariableTypeConstructor typeConstructor = coneTypeVariableType.getTypeConstructor();
            VariableWithConstraints variableWithConstraints = (VariableWithConstraints) argumentContext.getCsBuilder().currentStorage().getNotFixedTypeVariables().get(typeConstructor);
            if (variableWithConstraints != null) {
                ConeKotlinType coneKotlinTypeFindResultTypeOrNull = argumentContext.getContext().getInferenceComponents().getResultTypeResolver().findResultTypeOrNull(argumentContext.getCandidate().getSystem(), variableWithConstraints, TypeVariableDirectionCalculator.ResolveDirection.UNKNOWN);
                if (coneKotlinTypeFindResultTypeOrNull != null) {
                    if (coneTypeVariableType.getIsMarkedNullable()) {
                        coneKotlinTypeFindResultTypeOrNull = TypeUtilsKt.withNullability$default(coneKotlinTypeFindResultTypeOrNull, coneTypeVariableType.getIsMarkedNullable(), TypeComponentsKt.getTypeContext(argumentContext.getSession()), null, false, 12, null);
                    }
                    if (coneKotlinTypeFindResultTypeOrNull != null) {
                        return coneKotlinTypeFindResultTypeOrNull;
                    }
                }
            }
            if (variableWithConstraints == null || (constraints = variableWithConstraints.getConstraints()) == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList();
                Iterator it = constraints.iterator();
                while (it.hasNext()) {
                    ConeKotlinType type = ((Constraint) it.next()).getType();
                    ConeKotlinType coneKotlinType2 = type instanceof ConeKotlinType ? type : null;
                    if (coneKotlinType2 != null) {
                        arrayList.add(coneKotlinType2);
                    }
                }
            }
            if (arrayList != null && !arrayList.isEmpty()) {
                ConeKotlinType coneKotlinTypeIntersectTypes = ConeTypeIntersector.INSTANCE.intersectTypes(TypeComponentsKt.getTypeContext(argumentContext.getSession()), arrayList);
                return coneTypeVariableType.getIsMarkedNullable() ? TypeUtilsKt.withNullability$default(coneKotlinTypeIntersectTypes, coneTypeVariableType.getIsMarkedNullable(), TypeComponentsKt.getTypeContext(argumentContext.getSession()), null, false, 12, null) : coneKotlinTypeIntersectTypes;
            }
            TypeParameterMarker originalTypeParameter = typeConstructor.getOriginalTypeParameter();
            ConeTypeParameterLookupTag coneTypeParameterLookupTag = originalTypeParameter instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) originalTypeParameter : null;
            if (coneTypeParameterLookupTag != null) {
                return new ConeTypeParameterTypeImpl(coneTypeParameterLookupTag, coneTypeVariableType.getIsMarkedNullable(), coneTypeVariableType.getAttributes());
            }
        } else if ((coneKotlinType instanceof ConeIntegerLiteralType) && (coneClassLikeType = (ConeClassLikeType) CollectionsKt.firstOrNull(((ConeIntegerLiteralType) coneKotlinType).getPossibleTypes())) != null) {
            return coneClassLikeType;
        }
        return coneKotlinType;
    }

    private final void preprocessCallableReference(ArgumentContext argumentContext, ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild) {
        FirCallableReferenceAccess callableReferenceExpression = getCallableReferenceExpression(coneResolutionAtomWithPostponedChild);
        ConeResolvedCallableReferenceAtom coneResolvedCallableReferenceAtom = new ConeResolvedCallableReferenceAtom(callableReferenceExpression, argumentContext.getExpectedType(), argumentContext.getContext().getBodyResolveComponents().getDoubleColonExpressionResolver().resolveDoubleColonLHS(callableReferenceExpression), argumentContext.getContext().getSession(), argumentContext.getAnonymousFunctionIfReturnExpression());
        coneResolutionAtomWithPostponedChild.setPostponedSubAtom(coneResolvedCallableReferenceAtom);
        argumentContext.getCandidate().addPostponedAtom(coneResolvedCallableReferenceAtom);
    }

    private final void preprocessCollectionLiteral(ArgumentContext argumentContext, ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild) {
        FirCollectionLiteral collectionLiteralExpression = getCollectionLiteralExpression(coneResolutionAtomWithPostponedChild);
        if (!argumentContext.getContext().getBodyResolveContext().getIsInsideAnnotationContext() && LanguageVersionUtilsKt.isEnabled(argumentContext, LanguageFeature.CollectionLiterals)) {
            ConeCollectionLiteralAtom coneCollectionLiteralAtom = new ConeCollectionLiteralAtom(collectionLiteralExpression, argumentContext.getExpectedType(), argumentContext.getCandidate());
            coneResolutionAtomWithPostponedChild.setPostponedSubAtom(coneCollectionLiteralAtom);
            argumentContext.getCandidate().addPostponedAtom(coneCollectionLiteralAtom);
        } else {
            coneResolutionAtomWithPostponedChild.useFallbackForDisabledCollectionLiterals();
            ConeResolutionAtom subAtom = coneResolutionAtomWithPostponedChild.getSubAtom();
            subAtom.getClass();
            resolveArgumentExpression(argumentContext, subAtom);
        }
    }

    private final void preprocessLambdaArgument(ArgumentContext argumentContext, ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild) {
        if (createLambdaWithTypeVariableAsExpectedTypeAtomIfNeeded(argumentContext, coneResolutionAtomWithPostponedChild)) {
            return;
        }
        createResolvedLambdaAtom(argumentContext, coneResolutionAtomWithPostponedChild, false, null);
    }

    private final void preprocessQualifierWithContextSensitiveAlternative(ArgumentContext argumentContext, ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild, FirQualifierWithContextSensitiveAlternative firQualifierWithContextSensitiveAlternative) {
        FirPropertyAccessExpression contextSensitiveAlternative = firQualifierWithContextSensitiveAlternative.getContextSensitiveAlternative();
        if (contextSensitiveAlternative == null) {
            f2f.a("Should not create atom with postponed child for expression without CSR alternative ", UtilsKt.render(firQualifierWithContextSensitiveAlternative));
            return;
        }
        if (argumentContext.getExpectedType() == null) {
            coneResolutionAtomWithPostponedChild.useFallbackSubAtom();
            ConeResolutionAtom subAtom = coneResolutionAtomWithPostponedChild.getSubAtom();
            subAtom.getClass();
            resolveArgumentExpression(argumentContext, subAtom);
            return;
        }
        ConeContextSensitiveAlternativeForQualifierAtom coneContextSensitiveAlternativeForQualifierAtom = new ConeContextSensitiveAlternativeForQualifierAtom(firQualifierWithContextSensitiveAlternative, contextSensitiveAlternative, argumentContext.getExpectedType());
        ConeResolutionAtom fallbackSubAtom = coneResolutionAtomWithPostponedChild.getFallbackSubAtom();
        fallbackSubAtom.getClass();
        resolveArgumentExpression(argumentContext, fallbackSubAtom);
        coneResolutionAtomWithPostponedChild.setPostponedSubAtom(coneContextSensitiveAlternativeForQualifierAtom);
        argumentContext.getCandidate().addPostponedAtom(coneContextSensitiveAlternativeForQualifierAtom);
    }

    private final void preprocessSimpleNameReferenceForContextSensitiveResolution(ArgumentContext argumentContext, ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild, FirPropertyAccessExpression firPropertyAccessExpression) {
        if (argumentContext.getExpectedType() == null || !LanguageVersionUtilsKt.isEnabled(argumentContext, LanguageFeature.ContextSensitiveResolutionUsingExpectedType)) {
            coneResolutionAtomWithPostponedChild.useFallbackSubAtom();
            ConeResolutionAtom subAtom = coneResolutionAtomWithPostponedChild.getSubAtom();
            subAtom.getClass();
            resolveArgumentExpression(argumentContext, subAtom);
            return;
        }
        ConeKotlinType expectedType = argumentContext.getExpectedType();
        Candidate candidate = argumentContext.getCandidate();
        ConeResolutionAtom fallbackSubAtom = coneResolutionAtomWithPostponedChild.getFallbackSubAtom();
        fallbackSubAtom.getClass();
        ConeSimpleNameForContextSensitiveResolution coneSimpleNameForContextSensitiveResolution = new ConeSimpleNameForContextSensitiveResolution(firPropertyAccessExpression, expectedType, candidate, fallbackSubAtom);
        coneResolutionAtomWithPostponedChild.setPostponedSubAtom(coneSimpleNameForContextSensitiveResolution);
        argumentContext.getCandidate().addPostponedAtom(coneSimpleNameForContextSensitiveResolution);
    }

    private final void resolveArgumentExpression(ArgumentContext argumentContext, ConeResolutionAtom coneResolutionAtom) {
        if (coneResolutionAtom instanceof ConeResolutionAtomWithPostponedChild) {
            ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild = (ConeResolutionAtomWithPostponedChild) coneResolutionAtom;
            FirExpression expression = coneResolutionAtomWithPostponedChild.getExpression();
            if (expression instanceof FirAnonymousFunctionExpression) {
                preprocessLambdaArgument(argumentContext, coneResolutionAtomWithPostponedChild);
                return;
            }
            if (expression instanceof FirCallableReferenceAccess) {
                preprocessCallableReference(argumentContext, coneResolutionAtomWithPostponedChild);
                return;
            }
            if (expression instanceof FirPropertyAccessExpression) {
                if (((FirPropertyAccessExpression) coneResolutionAtomWithPostponedChild.getExpression()).getExplicitReceiver() == null) {
                    preprocessSimpleNameReferenceForContextSensitiveResolution(argumentContext, coneResolutionAtomWithPostponedChild, (FirPropertyAccessExpression) coneResolutionAtomWithPostponedChild.getExpression());
                    return;
                } else if (LanguageVersionUtilsKt.isSet(argumentContext, AnalysisFlags.getIdeMode())) {
                    preprocessQualifierWithContextSensitiveAlternative(argumentContext, coneResolutionAtomWithPostponedChild, (FirQualifierWithContextSensitiveAlternative) coneResolutionAtomWithPostponedChild.getExpression());
                    return;
                } else {
                    f2f.a("Unknown kind of atom with postponed child: ", Reflection.getOrCreateKotlinClass(coneResolutionAtomWithPostponedChild.getExpression().getClass()));
                    return;
                }
            }
            if ((expression instanceof FirResolvedQualifier) && LanguageVersionUtilsKt.isSet(argumentContext, AnalysisFlags.getIdeMode())) {
                preprocessQualifierWithContextSensitiveAlternative(argumentContext, coneResolutionAtomWithPostponedChild, (FirQualifierWithContextSensitiveAlternative) coneResolutionAtomWithPostponedChild.getExpression());
                return;
            } else if (expression instanceof FirCollectionLiteral) {
                preprocessCollectionLiteral(argumentContext, coneResolutionAtomWithPostponedChild);
                return;
            } else {
                f2f.a("Unknown kind of atom with postponed child: ", Reflection.getOrCreateKotlinClass(coneResolutionAtomWithPostponedChild.getExpression().getClass()));
                return;
            }
        }
        if ((coneResolutionAtom instanceof ConeSimpleLeafResolutionAtom) || (coneResolutionAtom instanceof ConeAtomWithCandidate)) {
            resolvePlainExpressionArgument$default(this, argumentContext, coneResolutionAtom, false, 2, null);
            return;
        }
        if (coneResolutionAtom instanceof ConePostponedResolvedAtom) {
            f2f.a("Unexpected type of atom: ", coneResolutionAtom.getClass());
            return;
        }
        if (!(coneResolutionAtom instanceof ConeResolutionAtomWithSingleChild)) {
            bu8.a();
            return;
        }
        ConeResolutionAtomWithSingleChild coneResolutionAtomWithSingleChild = (ConeResolutionAtomWithSingleChild) coneResolutionAtom;
        FirExpression expression2 = coneResolutionAtomWithSingleChild.getExpression();
        if (expression2 instanceof FirSafeCallExpression) {
            ConeResolutionAtom subAtom = coneResolutionAtomWithSingleChild.getSubAtom();
            if (subAtom == null) {
                checkApplicabilityForArgumentType(argumentContext, coneResolutionAtom, TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getUnit(), null, false, null, 7, null), SimpleConstraintSystemConstraintPosition.INSTANCE);
                return;
            } else {
                resolvePlainExpressionArgument(argumentContext, subAtom, true);
                return;
            }
        }
        if (expression2 instanceof FirBlock) {
            ConeResolutionAtom subAtom2 = coneResolutionAtomWithSingleChild.getSubAtom();
            if (subAtom2 == null) {
                checkApplicabilityForArgumentType(ArgumentContext.copy$default(argumentContext, null, null, null, null, null, false, false, null, 159, null), coneResolutionAtom, FirTypeUtilsKt.getResolvedType(coneResolutionAtomWithSingleChild.getExpression()), SimpleConstraintSystemConstraintPosition.INSTANCE);
                return;
            } else {
                resolveArgumentExpression(argumentContext, subAtom2);
                return;
            }
        }
        ConeResolutionAtom subAtom3 = coneResolutionAtomWithSingleChild.getSubAtom();
        if (subAtom3 == null) {
            resolvePlainExpressionArgument$default(this, argumentContext, coneResolutionAtom, false, 2, null);
        } else {
            resolveArgumentExpression(argumentContext, subAtom3);
        }
    }

    private final void resolvePlainArgumentType(ArgumentContext argumentContext, ConeResolutionAtom coneResolutionAtom, ConeKotlinType coneKotlinType, boolean z, KtSourceElement ktSourceElement) {
        ConeKotlinType coneKotlinTypeArgumentTypeWithCustomConversion;
        FirExpression expression = coneResolutionAtom.getExpression();
        ConeReceiverConstraintPosition coneReceiverConstraintPosition = argumentContext.isReceiver() ? new ConeReceiverConstraintPosition(expression, ktSourceElement) : createArgumentConstraintPosition(argumentContext, coneResolutionAtom);
        ConeKotlinType coneKotlinTypePrepareCapturedType = ArgumentUtilsKt.prepareCapturedType(coneKotlinType, argumentContext.getContext().getSession());
        if (z) {
            coneKotlinTypePrepareCapturedType = TypeUtilsKt.withNullability$default(coneKotlinTypePrepareCapturedType, true, TypeComponentsKt.getTypeContext(argumentContext.getSession()), null, false, 12, null);
        }
        if (argumentContext.getExpectedType() != null && shouldRunConversion(argumentContext) && (coneKotlinTypeArgumentTypeWithCustomConversion = argumentTypeWithCustomConversion(argumentContext.getContext().getTypeContext(), argumentContext.getSession(), argumentContext.getExpectedType(), coneKotlinTypePrepareCapturedType)) != null) {
            argumentContext.getCandidate().addFunctionKindConversionOfArgument(expression, new Candidate.FunctionConversionDescription(true, argumentContext.getExpectedType()));
            coneKotlinTypePrepareCapturedType = coneKotlinTypeArgumentTypeWithCustomConversion;
        }
        checkApplicabilityForArgumentType(argumentContext, coneResolutionAtom, coneKotlinTypePrepareCapturedType, coneReceiverConstraintPosition);
    }

    private final void resolvePlainExpressionArgument(ArgumentContext argumentContext, ConeResolutionAtom coneResolutionAtom, boolean z) {
        if (argumentContext.getExpectedType() == null) {
            return;
        }
        resolvePlainArgumentType$default(this, argumentContext, coneResolutionAtom, FirTypeUtilsKt.getResolvedType(coneResolutionAtom.getExpression()), z, null, 8, null);
    }

    public static /* synthetic */ void resolvePlainExpressionArgument$default(ArgumentCheckingProcessor argumentCheckingProcessor, ArgumentContext argumentContext, ConeResolutionAtom coneResolutionAtom, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        argumentCheckingProcessor.resolvePlainExpressionArgument(argumentContext, coneResolutionAtom, z);
    }

    private final boolean shouldRunConversion(ArgumentContext argumentContext) {
        if (argumentContext.getAnonymousFunctionIfReturnExpression() != null) {
            return !LanguageVersionUtilsKt.isEnabled(argumentContext, LanguageFeature.DoNotRunSuspendConversionForLambdaReturnStatements);
        }
        return true;
    }

    private final ResolutionDiagnostic subtypeError(ArgumentContext argumentContext, FirExpression firExpression, ConeKotlinType coneKotlinType) {
        if (argumentContext.getExpectedType() == null) {
            w01.a("Expected type mustn't be null");
            return null;
        }
        if (!FirTypeUtilsKt.isNullLiteral(firExpression) || ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(argumentContext.getExpectedType())) {
            return ((coneKotlinType instanceof ConeErrorType) || (argumentContext.getExpectedType() instanceof ConeErrorType)) ? ErrorTypeInArguments.INSTANCE : new ArgumentTypeMismatch(prepareTypeForArgumentTypeMismatch(argumentContext, argumentContext.getExpectedType()), prepareTypeForArgumentTypeMismatch(argumentContext, coneKotlinType), firExpression, InferenceUtilsKt.isTypeMismatchDueToNullability(TypeComponentsKt.getTypeContext(argumentContext.getSession()), coneKotlinType, argumentContext.getExpectedType()), argumentContext.getAnonymousFunctionIfReturnExpression(), argumentContext.getCsBuilder().getHasContradiction());
        }
        return new NullForNotNullType(firExpression, argumentContext.getExpectedType());
    }

    public final ConeResolvedLambdaAtom createResolvedLambdaAtomDuringCompletion(Candidate candidate, ConstraintSystemBuilder csBuilder, ConeResolutionAtomWithPostponedChild atom, ConeKotlinType expectedType, ResolutionContext context, ConeTypeVariableForLambdaReturnType returnTypeVariable, FirAnonymousFunction anonymousFunctionIfReturnExpression) {
        candidate.getClass();
        csBuilder.getClass();
        atom.getClass();
        context.getClass();
        return createResolvedLambdaAtom(new ArgumentContext(candidate, csBuilder, expectedType, null, context, false, false, anonymousFunctionIfReturnExpression), atom, true, returnTypeVariable);
    }

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010,\u001a\u00020\u000bHÆ\u0003J\t\u0010-\u001a\u00020\rHÆ\u0003J\t\u0010.\u001a\u00020\rHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0010HÆ\u0003J_\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001J\u0014\u00101\u001a\u00020\r2\b\u00102\u001a\u0004\u0018\u000103HÖ\u0083\u0004J\n\u00104\u001a\u000205HÖ\u0081\u0004J\n\u00106\u001a\u000207HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001dR\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u001dR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u00068"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ArgumentCheckingProcessor$ArgumentContext;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "csBuilder", "Lorg/jetbrains/kotlin/resolve/calls/inference/ConstraintSystemBuilder;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "isReceiver", Argument.Delimiters.none, "isDispatch", "anonymousFunctionIfReturnExpression", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/resolve/calls/inference/ConstraintSystemBuilder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;ZZLorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "getCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "getCsBuilder", "()Lorg/jetbrains/kotlin/resolve/calls/inference/ConstraintSystemBuilder;", "getExpectedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getSink", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "getContext", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "()Z", "getAnonymousFunctionIfReturnExpression", "()Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "reportDiagnostic", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ArgumentContext implements SessionHolder {
        private final FirAnonymousFunction anonymousFunctionIfReturnExpression;
        private final Candidate candidate;
        private final ResolutionContext context;
        private final ConstraintSystemBuilder csBuilder;
        private final ConeKotlinType expectedType;
        private final boolean isDispatch;
        private final boolean isReceiver;
        private final CheckerSink sink;

        public ArgumentContext(Candidate candidate, ConstraintSystemBuilder constraintSystemBuilder, ConeKotlinType coneKotlinType, CheckerSink checkerSink, ResolutionContext resolutionContext, boolean z, boolean z2, FirAnonymousFunction firAnonymousFunction) {
            candidate.getClass();
            constraintSystemBuilder.getClass();
            resolutionContext.getClass();
            this.candidate = candidate;
            this.csBuilder = constraintSystemBuilder;
            this.expectedType = coneKotlinType;
            this.sink = checkerSink;
            this.context = resolutionContext;
            this.isReceiver = z;
            this.isDispatch = z2;
            this.anonymousFunctionIfReturnExpression = firAnonymousFunction;
        }

        public static /* synthetic */ ArgumentContext copy$default(ArgumentContext argumentContext, Candidate candidate, ConstraintSystemBuilder constraintSystemBuilder, ConeKotlinType coneKotlinType, CheckerSink checkerSink, ResolutionContext resolutionContext, boolean z, boolean z2, FirAnonymousFunction firAnonymousFunction, int i, Object obj) {
            if ((i & 1) != 0) {
                candidate = argumentContext.candidate;
            }
            if ((i & 2) != 0) {
                constraintSystemBuilder = argumentContext.csBuilder;
            }
            if ((i & 4) != 0) {
                coneKotlinType = argumentContext.expectedType;
            }
            if ((i & 8) != 0) {
                checkerSink = argumentContext.sink;
            }
            if ((i & 16) != 0) {
                resolutionContext = argumentContext.context;
            }
            if ((i & 32) != 0) {
                z = argumentContext.isReceiver;
            }
            if ((i & 64) != 0) {
                z2 = argumentContext.isDispatch;
            }
            if ((i & 128) != 0) {
                firAnonymousFunction = argumentContext.anonymousFunctionIfReturnExpression;
            }
            boolean z3 = z2;
            FirAnonymousFunction firAnonymousFunction2 = firAnonymousFunction;
            ResolutionContext resolutionContext2 = resolutionContext;
            boolean z4 = z;
            return argumentContext.copy(candidate, constraintSystemBuilder, coneKotlinType, checkerSink, resolutionContext2, z4, z3, firAnonymousFunction2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Candidate getCandidate() {
            return this.candidate;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ConstraintSystemBuilder getCsBuilder() {
            return this.csBuilder;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final ConeKotlinType getExpectedType() {
            return this.expectedType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final CheckerSink getSink() {
            return this.sink;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final ResolutionContext getContext() {
            return this.context;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsReceiver() {
            return this.isReceiver;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getIsDispatch() {
            return this.isDispatch;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final FirAnonymousFunction getAnonymousFunctionIfReturnExpression() {
            return this.anonymousFunctionIfReturnExpression;
        }

        public final ArgumentContext copy(Candidate candidate, ConstraintSystemBuilder csBuilder, ConeKotlinType expectedType, CheckerSink sink, ResolutionContext context, boolean isReceiver, boolean isDispatch, FirAnonymousFunction anonymousFunctionIfReturnExpression) {
            candidate.getClass();
            csBuilder.getClass();
            context.getClass();
            return new ArgumentContext(candidate, csBuilder, expectedType, sink, context, isReceiver, isDispatch, anonymousFunctionIfReturnExpression);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ArgumentContext)) {
                return false;
            }
            ArgumentContext argumentContext = (ArgumentContext) other;
            return Intrinsics.areEqual(this.candidate, argumentContext.candidate) && Intrinsics.areEqual(this.csBuilder, argumentContext.csBuilder) && Intrinsics.areEqual(this.expectedType, argumentContext.expectedType) && Intrinsics.areEqual(this.sink, argumentContext.sink) && Intrinsics.areEqual(this.context, argumentContext.context) && this.isReceiver == argumentContext.isReceiver && this.isDispatch == argumentContext.isDispatch && Intrinsics.areEqual(this.anonymousFunctionIfReturnExpression, argumentContext.anonymousFunctionIfReturnExpression);
        }

        public final FirAnonymousFunction getAnonymousFunctionIfReturnExpression() {
            return this.anonymousFunctionIfReturnExpression;
        }

        public final Candidate getCandidate() {
            return this.candidate;
        }

        public final ResolutionContext getContext() {
            return this.context;
        }

        public final ConstraintSystemBuilder getCsBuilder() {
            return this.csBuilder;
        }

        public final ConeKotlinType getExpectedType() {
            return this.expectedType;
        }

        @Override // org.jetbrains.kotlin.fir.SessionHolder
        public FirSession getSession() {
            return this.context.getSession();
        }

        public final CheckerSink getSink() {
            return this.sink;
        }

        public int hashCode() {
            int iHashCode = ((this.candidate.hashCode() * 31) + this.csBuilder.hashCode()) * 31;
            ConeKotlinType coneKotlinType = this.expectedType;
            int iHashCode2 = (iHashCode + (coneKotlinType == null ? 0 : coneKotlinType.hashCode())) * 31;
            CheckerSink checkerSink = this.sink;
            int iHashCode3 = (((((((iHashCode2 + (checkerSink == null ? 0 : checkerSink.hashCode())) * 31) + this.context.hashCode()) * 31) + Boolean.hashCode(this.isReceiver)) * 31) + Boolean.hashCode(this.isDispatch)) * 31;
            FirAnonymousFunction firAnonymousFunction = this.anonymousFunctionIfReturnExpression;
            return iHashCode3 + (firAnonymousFunction != null ? firAnonymousFunction.hashCode() : 0);
        }

        public final boolean isDispatch() {
            return this.isDispatch;
        }

        public final boolean isReceiver() {
            return this.isReceiver;
        }

        public final void reportDiagnostic(ResolutionDiagnostic diagnostic) {
            diagnostic.getClass();
            CheckerSink checkerSink = this.sink;
            if (checkerSink != null) {
                checkerSink.reportDiagnostic(diagnostic);
            }
        }

        public String toString() {
            return "ArgumentContext(candidate=" + this.candidate + ", csBuilder=" + this.csBuilder + ", expectedType=" + this.expectedType + ", sink=" + this.sink + ", context=" + this.context + ", isReceiver=" + this.isReceiver + ", isDispatch=" + this.isDispatch + ", anonymousFunctionIfReturnExpression=" + this.anonymousFunctionIfReturnExpression + ')';
        }

        public /* synthetic */ ArgumentContext(Candidate candidate, ConstraintSystemBuilder constraintSystemBuilder, ConeKotlinType coneKotlinType, CheckerSink checkerSink, ResolutionContext resolutionContext, boolean z, boolean z2, FirAnonymousFunction firAnonymousFunction, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(candidate, constraintSystemBuilder, coneKotlinType, checkerSink, resolutionContext, z, z2, (i & 128) != 0 ? null : firAnonymousFunction);
        }
    }

    public static /* synthetic */ void resolvePlainArgumentType$default(ArgumentCheckingProcessor argumentCheckingProcessor, ArgumentContext argumentContext, ConeResolutionAtom coneResolutionAtom, ConeKotlinType coneKotlinType, boolean z, KtSourceElement ktSourceElement, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            ktSourceElement = null;
        }
        argumentCheckingProcessor.resolvePlainArgumentType(argumentContext, coneResolutionAtom, coneKotlinType, z2, ktSourceElement);
    }

    public final void resolvePlainArgumentType(Candidate candidate, ConeResolutionAtom atom, ConeKotlinType argumentType, ConeKotlinType expectedType, CheckerSink sink, ResolutionContext context, boolean isReceiver, boolean isDispatch, KtSourceElement sourceForReceiver) {
        candidate.getClass();
        atom.getClass();
        argumentType.getClass();
        sink.getClass();
        context.getClass();
        resolvePlainArgumentType$default(this, new ArgumentContext(candidate, ConstraintSystemCompleterKt.getCsBuilder(candidate), expectedType, sink, context, isReceiver, isDispatch, null, 128, null), atom, argumentType, false, sourceForReceiver, 4, null);
    }

    public final void resolveArgumentExpression(Candidate candidate, ConeResolutionAtom atom, ConeKotlinType expectedType, CheckerSink sink, ResolutionContext context, boolean isReceiver, boolean isDispatch, FirAnonymousFunction anonymousFunctionIfReturnExpression) {
        candidate.getClass();
        atom.getClass();
        sink.getClass();
        context.getClass();
        resolveArgumentExpression(new ArgumentContext(candidate, ConstraintSystemCompleterKt.getCsBuilder(candidate), expectedType, sink, context, isReceiver, isDispatch, anonymousFunctionIfReturnExpression), atom);
    }
}
