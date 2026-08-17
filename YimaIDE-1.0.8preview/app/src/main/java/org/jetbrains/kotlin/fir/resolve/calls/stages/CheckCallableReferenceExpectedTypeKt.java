package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DelegatedWrapperData;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirNamedArgumentExpressionBuilder;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.CallableReferenceAdaptation;
import org.jetbrains.kotlin.fir.resolve.calls.CallableReferenceConversionStrategy;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomsKt;
import org.jetbrains.kotlin.fir.resolve.calls.InputOutputTypes;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnosticKt;
import org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument;
import org.jetbrains.kotlin.fir.resolve.calls.VisibilityUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallableReferenceInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilderKt;
import org.jetbrains.kotlin.resolve.calls.inference.model.Constraint;
import org.jetbrains.kotlin.resolve.calls.inference.model.MutableVariableWithConstraints;
import org.jetbrains.kotlin.types.AbstractTypeApproximator;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.expressions.CoercionStrategy;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001aI\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00012\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\r\u001a0\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001aG\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00160\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002R\u00020\u0017j\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0002\u0010\u001d\u001a1\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0002H\u0002R\u00020\u0017j\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0002\u0010 \u001a\u001c\u0010!\u001a\u00020\f*\u00020\"2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0002H\u0002\u001a\u0014\u0010#\u001a\u00020\u0014*\u00020\u00112\u0006\u0010$\u001a\u00020\u001bH\u0002\u001a4\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00142\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00020,2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a\u0014\u00100\u001a\u00020\f*\u0002012\u0006\u0010\t\u001a\u00020\nH\u0002\"\u0018\u0010%\u001a\u00020\f*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&\"\u0018\u0010'\u001a\u00020\u0014*\u00020(8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u00062"}, d2 = {"buildResultingTypeAndAdaptation", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceAdaptation;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "receiverType", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "forceReflectionType", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Z)Lkotlin/Pair;", "getCallableReferenceAdaptation", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "expectedType", "unboundReceiverCount", Argument.Delimiters.none, "varargParameterTypeByExpectedParameter", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/VarargMappingState;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "<unused var>", "expectedParameterType", "substitutedParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "varargMappingState", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;Lorg/jetbrains/kotlin/fir/resolve/calls/stages/VarargMappingState;)Lkotlin/Pair;", "isApplicableTypeVariableForMappingAsArray", "arrayType", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "doesNotContradictToArrayArgument", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/Constraint;", "indexOf", "valueParameter", "isBaseTypeForNumberedReferenceTypes", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "index", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getIndex", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)I", "createFakeArgumentsForReference", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "expectedArgumentCount", "inputTypes", "canBeMutableReference", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckCallableReferenceExpectedTypeKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VarargMappingState.values().length];
            try {
                iArr[VarargMappingState.MAPPED_WITH_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[VarargMappingState.MAPPED_WITH_PLAIN_ARGS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[VarargMappingState.UNMAPPED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final Pair<ConeKotlinType, CallableReferenceAdaptation> buildResultingTypeAndAdaptation(ResolutionContext resolutionContext, FirCallableDeclaration firCallableDeclaration, ConeKotlinType coneKotlinType, Candidate candidate, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        FunctionTypeKind functionTypeKindSpecialFunctionTypeKind;
        CallableReferenceConversionStrategy suspendConversionStrategy;
        FirResolvedTypeRef firResolvedTypeRefTryCalculateReturnType = resolutionContext.getBodyResolveComponents().getReturnTypeCalculator().tryCalculateReturnType(firCallableDeclaration);
        if (!(firCallableDeclaration instanceof FirFunction)) {
            if (!(firCallableDeclaration instanceof FirVariable)) {
                bu8.a();
                return null;
            }
            ConeKotlinType coneType = firResolvedTypeRefTryCalculateReturnType.getConeType();
            boolean zCanBeMutableReference = canBeMutableReference((FirVariable) firCallableDeclaration, candidate);
            if (zCanBeMutableReference && ConeTypeUtilsKt.hasCapture(coneType)) {
                KotlinTypeMarker kotlinTypeMarkerApproximateToSuperType$default = AbstractTypeApproximator.approximateToSuperType$default(resolutionContext.getInferenceComponents().getResultTypeResolver().getTypeApproximator(), coneType, TypeApproximatorConfiguration.InternalTypesApproximation.INSTANCE, (Map) null, 4, (Object) null);
                ConeKotlinType coneKotlinType2 = kotlinTypeMarkerApproximateToSuperType$default instanceof ConeKotlinType ? (ConeKotlinType) kotlinTypeMarkerApproximateToSuperType$default : null;
                if (coneKotlinType2 != null) {
                    coneType = coneKotlinType2;
                }
            }
            return TuplesKt.to(ResolveUtilsKt.createKPropertyType(coneKotlinType, coneType, zCanBeMutableReference), null);
        }
        int i = coneKotlinType != null ? 1 : 0;
        CallInfo callInfo = candidate.getCallInfo();
        callInfo.getClass();
        CallableReferenceInfo callableReferenceInfo = (CallableReferenceInfo) callInfo;
        BodyResolveComponents bodyResolveComponents = resolutionContext.getBodyResolveComponents();
        FirFunction firFunction = (FirFunction) firCallableDeclaration;
        ConeKotlinType expectedType = callableReferenceInfo.getExpectedType();
        CallableReferenceAdaptation callableReferenceAdaptation = getCallableReferenceAdaptation(bodyResolveComponents, candidate, firFunction, expectedType != null ? ConeTypeUtilsKt.lowerBoundIfFlexible(expectedType) : null, i);
        ArrayList arrayList = new ArrayList();
        if (firFunction.getReceiverParameter() == null && coneKotlinType != null) {
            arrayList.add(coneKotlinType);
        }
        ConeKotlinType coneType2 = firResolvedTypeRefTryCalculateReturnType.getConeType();
        if (callableReferenceAdaptation == null) {
            for (FirValueParameter firValueParameter : firFunction.getValueParameters()) {
                arrayList.add((((firCallableDeclaration instanceof FirNamedFunction) || (firCallableDeclaration instanceof FirConstructor)) && !Intrinsics.areEqual(firFunction.getOrigin(), FirDeclarationOrigin.SamConstructor.INSTANCE)) ? LookupTagUtilsKt.withParameterNameAnnotation(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()), firValueParameter) : FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()));
            }
        } else {
            CollectionsKt.addAll(arrayList, callableReferenceAdaptation.getArgumentTypes());
            boolean hasSyntheticOuterCall = callableReferenceInfo.getHasSyntheticOuterCall();
            if (callableReferenceAdaptation.getCoercionStrategy() == CoercionStrategy.COERCION_TO_UNIT && (!hasSyntheticOuterCall || !(ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(coneType2) instanceof ConeTypeParameterType))) {
                coneType2 = resolutionContext.getSession().getBuiltinTypes().getUnitType().getConeType();
            }
        }
        if ((callableReferenceAdaptation == null || (suspendConversionStrategy = callableReferenceAdaptation.getSuspendConversionStrategy()) == null || (functionTypeKindSpecialFunctionTypeKind = suspendConversionStrategy.getKind()) == null) && (functionTypeKindSpecialFunctionTypeKind = FunctionalTypeUtilsKt.specialFunctionTypeKind(firFunction, resolutionContext.getSession())) == null) {
            functionTypeKindSpecialFunctionTypeKind = FunctionTypeKind.Function.INSTANCE;
        }
        FunctionTypeKind functionTypeKindReflectKind = (callableReferenceAdaptation == null || z) ? functionTypeKindSpecialFunctionTypeKind.reflectKind() : functionTypeKindSpecialFunctionTypeKind.nonReflectKind();
        if (firFunction.getReceiverParameter() == null) {
            coneKotlinType = null;
        }
        List<FirValueParameter> contextParameters = firFunction.getContextParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
        Iterator<T> it = contextParameters.iterator();
        while (it.hasNext()) {
            arrayList2.add(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef()));
        }
        return TuplesKt.to(ResolveUtilsKt.createFunctionType(functionTypeKindReflectKind, arrayList, coneKotlinType, coneType2, arrayList2), callableReferenceAdaptation);
    }

    private static final boolean canBeMutableReference(FirVariable firVariable, Candidate candidate) {
        FirCallableDeclaration firCallableDeclaration;
        if (!firVariable.getIsVar()) {
            return false;
        }
        if (firVariable instanceof FirField) {
            firCallableDeclaration = firVariable;
            return true;
        }
        while (true) {
            firCallableDeclaration = firVariable;
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                    originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                }
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirVariable firVariable2 = (FirVariable) firCallableDeclaration;
        KtSourceElement source = firVariable2.getSource();
        if (!Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE)) {
            if (firVariable2.getSetter() != null) {
                FirVisibilityChecker visibilityChecker = FirVisibilityCheckerKt.getVisibilityChecker(candidate.getCallInfo().getSession());
                FirPropertyAccessor setter = firVariable2.getSetter();
                setter.getClass();
                if (VisibilityUtilsKt.isVisible$default(visibilityChecker, setter, candidate, false, 4, null)) {
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0052  */
    private static final List<ConeResolutionAtom> createFakeArgumentsForReference(FirFunction firFunction, int i, List<? extends ConeKotlinType> list, int i2) {
        Name name;
        FirExpression firFakeArgumentForCallableReference;
        IntRange intRangeUntil = RangesKt.until(0, i);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        IntIterator it = intRangeUntil.iterator();
        boolean z = false;
        boolean z2 = false;
        ConeKotlinType coneKotlinType = null;
        while (it.hasNext()) {
            int iNextInt = it.nextInt();
            ConeKotlinType coneKotlinType2 = (ConeKotlinType) CollectionsKt.getOrNull(list, iNextInt + i2);
            if (z && !Intrinsics.areEqual(coneKotlinType, coneKotlinType2)) {
                z2 = true;
            }
            FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.getOrNull(firFunction.getValueParameters(), iNextInt);
            if (z2) {
                if ((firValueParameter != null ? firValueParameter.getDefaultValue() : null) != null) {
                    name = firValueParameter.getName();
                } else {
                    name = null;
                }
            } else {
                name = null;
            }
            if (firValueParameter != null && firValueParameter.getIsVararg()) {
                coneKotlinType = coneKotlinType2;
                z = true;
            }
            if (name != null) {
                FirNamedArgumentExpressionBuilder firNamedArgumentExpressionBuilder = new FirNamedArgumentExpressionBuilder();
                firNamedArgumentExpressionBuilder.setExpression(new FirFakeArgumentForCallableReference(iNextInt));
                firNamedArgumentExpressionBuilder.setName(name);
                firNamedArgumentExpressionBuilder.setSpread(false);
                firFakeArgumentForCallableReference = firNamedArgumentExpressionBuilder.mo288build();
            } else {
                firFakeArgumentForCallableReference = new FirFakeArgumentForCallableReference(iNextInt);
            }
            arrayList.add(ConeResolutionAtom.INSTANCE.createRawAtom(firFakeArgumentForCallableReference));
        }
        return arrayList;
    }

    private static final boolean doesNotContradictToArrayArgument(Constraint constraint, Candidate candidate, ConeKotlinType coneKotlinType) {
        return !constraint.getKind().impliesLower() || ConstraintSystemBuilderKt.isSubtypeConstraintCompatible(candidate.getSystem(), constraint.getType(), coneKotlinType);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:89:0x01f5  */
    private static final CallableReferenceAdaptation getCallableReferenceAdaptation(BodyResolveComponents bodyResolveComponents, Candidate candidate, FirFunction firFunction, ConeKotlinType coneKotlinType, int i) throws KotlinIllegalArgumentExceptionWithAttachments {
        InputOutputTypes inputOutputTypesExtractInputOutputTypesFromCallableReferenceExpectedType;
        CoercionStrategy coercionStrategy;
        ConeKotlinType coneKotlinTypeFullyExpandedType;
        List<ConeKotlinType> list;
        VarargMappingState varargMappingState;
        ConeKotlinType coneKotlinType2;
        FirResolvedTypeRef firResolvedTypeRef = null;
        if (coneKotlinType == null || TypeUtilsKt.isKCallableType(coneKotlinType) || (inputOutputTypesExtractInputOutputTypesFromCallableReferenceExpectedType = ConeResolutionAtomsKt.extractInputOutputTypesFromCallableReferenceExpectedType(coneKotlinType, bodyResolveComponents.getSession())) == null) {
            return null;
        }
        List<ConeKotlinType> listComponent1 = inputOutputTypesExtractInputOutputTypesFromCallableReferenceExpectedType.component1();
        ConeKotlinType outputType = inputOutputTypesExtractInputOutputTypesFromCallableReferenceExpectedType.getOutputType();
        int size = listComponent1.size() - i;
        if (size < 0) {
            return null;
        }
        List<ConeResolutionAtom> listCreateFakeArgumentsForReference = createFakeArgumentsForReference(firFunction, size, listComponent1, i);
        ConeSimpleKotlinType dispatchReceiverType = firFunction.getDispatchReceiverType();
        ArgumentMapping argumentMappingMapArguments = FirArgumentsToParametersMapperKt.mapArguments(bodyResolveComponents, listCreateFakeArgumentsForReference, firFunction, dispatchReceiverType != null ? ScopeUtilsKt.scope(bodyResolveComponents, dispatchReceiverType, CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS) : null, false, false);
        if (ResolutionDiagnosticKt.getAnyUnsuccessful(argumentMappingMapArguments.getDiagnostics())) {
            return null;
        }
        VarargMappingState varargMappingState2 = VarargMappingState.UNMAPPED;
        Map linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        int size2 = listCreateFakeArgumentsForReference.size();
        ConeKotlinType[] coneKotlinTypeArr = new ConeKotlinType[size2];
        Iterator<Map.Entry<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>>> it = argumentMappingMapArguments.getParameterToCallArgumentMap().entrySet().iterator();
        int i2 = 0;
        while (true) {
            FirResolvedTypeRef firResolvedTypeRef2 = firResolvedTypeRef;
            if (!it.hasNext()) {
                for (int i3 = 0; i3 < size2; i3++) {
                    if (coneKotlinTypeArr[i3] == null) {
                        return firResolvedTypeRef2;
                    }
                }
                for (Map.Entry entry : linkedHashMap2.entrySet()) {
                    linkedHashMap.put((FirValueParameter) entry.getKey(), new ResolvedCallArgument.VarargArgument((List) entry.getValue()));
                }
                boolean z = !linkedHashMap2.isEmpty();
                for (FirValueParameter firValueParameter : firFunction.getValueParameters()) {
                    if (firValueParameter.getIsVararg() && !linkedHashMap.containsKey(firValueParameter)) {
                        linkedHashMap.put(firValueParameter, new ResolvedCallArgument.VarargArgument(CollectionsKt.emptyList()));
                        z = true;
                    }
                }
                FirResolvedTypeRef returnTypeRef = firFunction.getReturnTypeRef();
                if (TypeUtilsKt.isUnitOrFlexibleUnit(outputType)) {
                    FirResolvedTypeRef firResolvedTypeRef3 = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : firResolvedTypeRef2;
                    FirResolvedTypeRef coneType = firResolvedTypeRef3 != null ? firResolvedTypeRef3.getConeType() : firResolvedTypeRef2;
                    if (coneType == null) {
                        coneType = firResolvedTypeRef2;
                    }
                    if (coneType == null || (coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) bodyResolveComponents, (ConeKotlinType) coneType)) == null || !ConeBuiltinTypeUtilsKt.isUnit(coneKotlinTypeFullyExpandedType)) {
                        coercionStrategy = CoercionStrategy.COERCION_TO_UNIT;
                    } else {
                        coercionStrategy = CoercionStrategy.NO_COERCION;
                    }
                } else {
                    coercionStrategy = CoercionStrategy.NO_COERCION;
                }
                CoercionStrategy coercionStrategy2 = coercionStrategy;
                if (isBaseTypeForNumberedReferenceTypes(coneKotlinType)) {
                    linkedHashMap = MapsKt.emptyMap();
                }
                FunctionTypeKind functionTypeKindFunctionTypeKind$default = FunctionalTypeUtilsKt.functionTypeKind$default(coneKotlinType, bodyResolveComponents.getSession(), false, 2, (Object) firResolvedTypeRef2);
                FunctionTypeKind functionTypeKind = (functionTypeKindFunctionTypeKind$default == null || FunctionTypeKindKt.isBasicFunctionOrKFunction(functionTypeKindFunctionTypeKind$default)) ? null : functionTypeKindFunctionTypeKind$default;
                CallableReferenceConversionStrategy customConversion = (functionTypeKind != null && functionTypeKind.getSupportsConversionFromSimpleFunctionType() && FunctionalTypeUtilsKt.specialFunctionTypeKind(firFunction, bodyResolveComponents.getSession()) == null) ? new CallableReferenceConversionStrategy.CustomConversion(functionTypeKind) : CallableReferenceConversionStrategy.NoConversion.INSTANCE;
                if (i2 == 0 && !z && coercionStrategy2 == CoercionStrategy.NO_COERCION && Intrinsics.areEqual(customConversion, CallableReferenceConversionStrategy.NoConversion.INSTANCE)) {
                    return null;
                }
                return new CallableReferenceAdaptation(coneKotlinTypeArr, coercionStrategy2, i2, linkedHashMap, customConversion);
            }
            Map.Entry<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> next = it.next();
            FirValueParameter key = next.getKey();
            ResolvedCallArgument<ConeResolutionAtom> value = next.getValue();
            for (ConeResolutionAtom coneResolutionAtom : value.getArguments()) {
                int index = getIndex(coneResolutionAtom.getExpression());
                it = it;
                FirValueParameter firValueParameter2 = (FirValueParameter) CollectionsKt.getOrNull(firFunction.getValueParameters(), indexOf(firFunction, key));
                if (firValueParameter2 != null) {
                    if (firValueParameter2.getIsVararg()) {
                        list = listComponent1;
                        Pair<ConeKotlinType, VarargMappingState> pairVarargParameterTypeByExpectedParameter = varargParameterTypeByExpectedParameter(bodyResolveComponents, candidate, listComponent1.get(index + i), firValueParameter2, varargMappingState2);
                        coneKotlinType2 = (ConeKotlinType) pairVarargParameterTypeByExpectedParameter.component1();
                        VarargMappingState varargMappingState3 = (VarargMappingState) pairVarargParameterTypeByExpectedParameter.component2();
                        int i4 = WhenMappings.$EnumSwitchMapping$0[varargMappingState3.ordinal()];
                        varargMappingState = varargMappingState3;
                        if (i4 == 1) {
                            linkedHashMap.put(key, new ResolvedCallArgument.SimpleArgument(coneResolutionAtom));
                        } else if (i4 == 2) {
                            Object arrayList = linkedHashMap2.get(key);
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                linkedHashMap2.put(key, arrayList);
                            }
                            ((List) arrayList).add(coneResolutionAtom);
                        } else if (i4 != 3) {
                            bu8.a();
                            return firResolvedTypeRef2;
                        }
                    } else {
                        list = listComponent1;
                        ConeKotlinType coneType2 = FirTypeUtilsKt.getConeType(firValueParameter2.getReturnTypeRef());
                        linkedHashMap.put(key, value);
                        varargMappingState = varargMappingState2;
                        coneKotlinType2 = coneType2;
                    }
                    coneKotlinTypeArr[index] = coneKotlinType2;
                    listComponent1 = list;
                    varargMappingState2 = varargMappingState;
                }
            }
            Iterator<Map.Entry<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>>> it2 = it;
            List<ConeKotlinType> list2 = listComponent1;
            if (Intrinsics.areEqual(value, ResolvedCallArgument.DefaultArgument.INSTANCE)) {
                i2++;
                linkedHashMap.put(key, value);
            }
            firResolvedTypeRef = firResolvedTypeRef2;
            it = it2;
            listComponent1 = list2;
        }
    }

    private static final int getIndex(FirExpression firExpression) {
        if (firExpression instanceof FirNamedArgumentExpression) {
            return getIndex(((FirNamedArgumentExpression) firExpression).getExpression());
        }
        if (firExpression instanceof FirFakeArgumentForCallableReference) {
            return ((FirFakeArgumentForCallableReference) firExpression).getIndex();
        }
        j2d.a();
        return 0;
    }

    private static final int indexOf(FirFunction firFunction, FirValueParameter firValueParameter) {
        return firFunction.getValueParameters().indexOf(firValueParameter);
    }

    private static final boolean isApplicableTypeVariableForMappingAsArray(SessionHolder sessionHolder, ConeKotlinType coneKotlinType, Candidate candidate, ConeKotlinType coneKotlinType2) {
        if (!(coneKotlinType instanceof ConeTypeVariableType)) {
            return false;
        }
        if (LanguageVersionUtilsKt.isDisabled(sessionHolder, LanguageFeature.RefinedVarargConversionRulesForCallableReferences)) {
            return true;
        }
        MutableVariableWithConstraints mutableVariableWithConstraints = (MutableVariableWithConstraints) candidate.getSystem().getNotFixedTypeVariables().get(((ConeTypeVariableType) coneKotlinType).getTypeConstructor());
        if (mutableVariableWithConstraints == null) {
            w04.a("Not found type variable: ", coneKotlinType);
            return false;
        }
        List constraints = mutableVariableWithConstraints.getConstraints();
        if ((constraints instanceof Collection) && constraints.isEmpty()) {
            return true;
        }
        Iterator it = constraints.iterator();
        while (it.hasNext()) {
            if (!doesNotContradictToArrayArgument((Constraint) it.next(), candidate, coneKotlinType2)) {
                return false;
            }
        }
        return true;
    }

    private static final boolean isBaseTypeForNumberedReferenceTypes(ConeKotlinType coneKotlinType) {
        ClassId classId = ConeTypeUtilsKt.getClassId(ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType));
        if (classId == null) {
            return false;
        }
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        return Intrinsics.areEqual(classId, standardClassIds.getKProperty()) || Intrinsics.areEqual(classId, standardClassIds.getKMutableProperty()) || Intrinsics.areEqual(classId, standardClassIds.getKCallable());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final Pair<ConeKotlinType, VarargMappingState> varargParameterTypeByExpectedParameter(SessionHolder sessionHolder, Candidate candidate, ConeKotlinType coneKotlinType, FirValueParameter firValueParameter, VarargMappingState varargMappingState) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()), false, 1, null);
        if (coneKotlinTypeArrayElementType$default == null) {
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Vararg parameter " + firValueParameter.getClass() + " does not have vararg type", (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withConeTypeEntry(exceptionAttachmentBuilder, "expectedParameterType", coneKotlinType);
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "substitutedParameter", firValueParameter);
            exceptionAttachmentBuilder.withEntry("varargMappingState", varargMappingState.toString());
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[varargMappingState.ordinal()];
        if (i == 1) {
            return TuplesKt.to(null, VarargMappingState.MAPPED_WITH_ARRAY);
        }
        if (i == 2) {
            if (LanguageVersionUtilsKt.isDisabled(sessionHolder, LanguageFeature.RefinedVarargConversionRulesForCallableReferences)) {
                return (ArrayUtilsKt.isArrayOrPrimitiveArray$default(coneKotlinType, false, 1, null) || (coneKotlinType instanceof ConeTypeVariableType)) ? TuplesKt.to(null, VarargMappingState.MAPPED_WITH_PLAIN_ARGS) : TuplesKt.to(coneKotlinTypeArrayElementType$default, VarargMappingState.MAPPED_WITH_PLAIN_ARGS);
            }
            return TuplesKt.to(coneKotlinTypeArrayElementType$default, VarargMappingState.MAPPED_WITH_PLAIN_ARGS);
        }
        if (i == 3) {
            return (ArrayUtilsKt.isArrayOrPrimitiveArray$default(coneKotlinType, false, 1, null) || isApplicableTypeVariableForMappingAsArray(sessionHolder, coneKotlinType, candidate, ArrayUtilsKt.createOutArrayType$default(coneKotlinTypeArrayElementType$default, false, false, 3, null))) ? TuplesKt.to(ArrayUtilsKt.createOutArrayType$default(coneKotlinTypeArrayElementType$default, false, false, 3, null), VarargMappingState.MAPPED_WITH_ARRAY) : TuplesKt.to(coneKotlinTypeArrayElementType$default, VarargMappingState.MAPPED_WITH_PLAIN_ARGS);
        }
        bu8.a();
        return null;
    }
}
