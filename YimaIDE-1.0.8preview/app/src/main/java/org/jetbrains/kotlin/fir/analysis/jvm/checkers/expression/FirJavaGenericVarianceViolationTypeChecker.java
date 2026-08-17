package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.StandardTypes;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeConflictingProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionIn;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContextHelpersKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00020\u000f*\u00020\u000fH\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001c\u0010\u0011\u001a\u00020\u0016*\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001c\u0010\u0011\u001a\u00020\u0017*\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001c\u0010\u0011\u001a\u00020\u0018*\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001c\u0010\u0019\u001a\u00020\u0015*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJavaGenericVarianceViolationTypeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "approximate", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "removeOutProjection", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "isCovariant", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "isTypeConstructorEqualOrSubClassOf", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "subType", "superType", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaGenericVarianceViolationTypeChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirJavaGenericVarianceViolationTypeChecker INSTANCE = new FirJavaGenericVarianceViolationTypeChecker();

    private FirJavaGenericVarianceViolationTypeChecker() {
        super(MppCheckerKind.Common);
    }

    private final ConeKotlinType approximate(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        ConeKotlinType coneKotlinTypeApproximateToSuperType = TypeComponentsKt.getTypeApproximator(checkerContext.getSession()).approximateToSuperType(coneKotlinType, TypeApproximatorConfiguration.FinalApproximationAfterResolutionAndInference.INSTANCE);
        return coneKotlinTypeApproximateToSuperType == null ? coneKotlinType : coneKotlinTypeApproximateToSuperType;
    }

    private final boolean isTypeConstructorEqualOrSubClassOf(ConeInferenceContext coneInferenceContext, ConeKotlinType coneKotlinType, ConeRigidType coneRigidType) {
        return AbstractTypeChecker.INSTANCE.isSubtypeOfClass(coneInferenceContext, coneInferenceContext.typeConstructor(coneKotlinType), coneInferenceContext.m691typeConstructor((RigidTypeMarker) coneRigidType));
    }

    private final ConeSimpleKotlinType removeOutProjection(ConeSimpleKotlinType coneSimpleKotlinType, ConeTypeContext coneTypeContext, boolean z) {
        if (coneSimpleKotlinType instanceof ConeIntersectionType) {
            ConeIntersectionType coneIntersectionType = (ConeIntersectionType) coneSimpleKotlinType;
            Collection<ConeKotlinType> intersectedTypes = coneIntersectionType.getIntersectedTypes();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes, 10));
            Iterator<T> it = intersectedTypes.iterator();
            while (it.hasNext()) {
                arrayList.add(INSTANCE.removeOutProjection((ConeKotlinType) it.next(), coneTypeContext, z));
            }
            ConeKotlinType upperBoundForApproximation = coneIntersectionType.getUpperBoundForApproximation();
            return new ConeIntersectionType(arrayList, upperBoundForApproximation != null ? INSTANCE.removeOutProjection(upperBoundForApproximation, coneTypeContext, z) : null);
        }
        if (!(coneSimpleKotlinType instanceof ConeClassLikeTypeImpl)) {
            if (!(coneSimpleKotlinType instanceof ConeCapturedType)) {
                return coneSimpleKotlinType;
            }
            k2d.a("There shouldn't be any captured types here as we call `approximate()`");
            return null;
        }
        ConeClassLikeTypeImpl coneClassLikeTypeImpl = (ConeClassLikeTypeImpl) coneSimpleKotlinType;
        ConeClassLikeLookupTag lookupTag = coneClassLikeTypeImpl.getLookupTag();
        ConeTypeProjection[] typeArguments = coneClassLikeTypeImpl.getTypeArguments();
        ArrayList arrayList2 = new ArrayList(typeArguments.length);
        for (ConeTypeProjection coneTypeProjection : typeArguments) {
            arrayList2.add(INSTANCE.removeOutProjection(coneTypeProjection, coneTypeContext, z));
        }
        return new ConeClassLikeTypeImpl(lookupTag, (ConeTypeProjection[]) arrayList2.toArray(new ConeTypeProjection[0]), coneClassLikeTypeImpl.getIsMarkedNullable(), coneClassLikeTypeImpl.getAttributes());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirTypeRef typeRef;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        CheckerContext checkerContext2 = checkerContext;
        checkerContext2.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        FirFunctionSymbol firFunctionSymbol = resolvedCallableSymbol$default instanceof FirFunctionSymbol ? (FirFunctionSymbol) resolvedCallableSymbol$default : null;
        if (firFunctionSymbol == null) {
            return;
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firFunctionSymbol.getFir();
        while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            } else {
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol<*>");
            return;
        }
        FirFunctionSymbol firFunctionSymbol2 = (FirFunctionSymbol) symbol;
        FirDeclarationOrigin origin = firFunctionSymbol2.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            D fir = firFunctionSymbol2.getFir();
            FirCallableDeclaration firCallableDeclaration2 = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration2 == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration2)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                return;
            }
        }
        FirArgumentList argumentList = firFunctionCall.getArgumentList();
        LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
        if (mapping == null) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int iMin = Math.min(firFunctionCall.getTypeArguments().size(), firFunctionSymbol.getTypeParameterSymbols().size());
        for (int i = 0; i < iMin; i++) {
            FirTypeProjection firTypeProjection = firFunctionCall.getTypeArguments().get(i);
            FirTypeProjectionWithVariance firTypeProjectionWithVariance = firTypeProjection instanceof FirTypeProjectionWithVariance ? (FirTypeProjectionWithVariance) firTypeProjection : null;
            ConeKotlinType coneType = (firTypeProjectionWithVariance == null || (typeRef = firTypeProjectionWithVariance.getTypeRef()) == null) ? null : FirTypeUtilsKt.getConeType(typeRef);
            if (coneType != null) {
                linkedHashMap.put(firFunctionSymbol.getTypeParameterSymbols().get(i), coneType);
            }
        }
        ConeSubstitutor coneSubstitutorSubstitutorByMap$default = ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, checkerContext2.getSession(), false, 4, null);
        for (Map.Entry<FirExpression, FirValueParameter> entry : mapping.entrySet()) {
            FirExpression key = entry.getKey();
            ConeKotlinType coneKotlinTypeSubstituteOrSelf = coneSubstitutorSubstitutorByMap$default.substituteOrSelf(FirTypeUtilsKt.getConeType(entry.getValue().getReturnTypeRef()));
            if (coneKotlinTypeSubstituteOrSelf instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinTypeSubstituteOrSelf;
                if (coneFlexibleType.getTypeArguments().length != 0) {
                    if (!(coneKotlinTypeSubstituteOrSelf instanceof ConeRawType)) {
                        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(key);
                        ConeRigidType lowerBound = coneFlexibleType.getLowerBound();
                        ConeRigidType upperBound = coneFlexibleType.getUpperBound();
                        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(checkerContext2.getSession());
                        if (!Intrinsics.areEqual(TypeSystemContextHelpersKt.typeConstructor(lowerBound, typeContext), TypeSystemContextHelpersKt.typeConstructor(upperBound, typeContext)) && isTypeConstructorEqualOrSubClassOf(typeContext, resolvedType, lowerBound)) {
                            if (!AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeContext, removeOutProjection(approximate(checkerContext2, resolvedType), (ConeTypeContext) TypeComponentsKt.getTypeContext(checkerContext2.getSession()), true), TypeUtilsKt.withNullability$default(approximate(checkerContext2, lowerBound), true, typeContext, null, false, 12, null), false, 8, (Object) null)) {
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) key.getSource(), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getJAVA_TYPE_MISMATCH(), (Object) coneKotlinTypeSubstituteOrSelf, (Object) resolvedType, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                            }
                        }
                    }
                }
            }
            checkerContext2 = checkerContext;
        }
    }

    private final ConeRigidType removeOutProjection(ConeRigidType coneRigidType, ConeTypeContext coneTypeContext, boolean z) {
        if (coneRigidType instanceof ConeSimpleKotlinType) {
            return removeOutProjection((ConeSimpleKotlinType) coneRigidType, coneTypeContext, z);
        }
        if (coneRigidType instanceof ConeDefinitelyNotNullType) {
            return new ConeDefinitelyNotNullType(removeOutProjection(((ConeDefinitelyNotNullType) coneRigidType).getOriginal(), coneTypeContext, z));
        }
        bu8.a();
        return null;
    }

    private final ConeKotlinType removeOutProjection(ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext, boolean z) {
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        ConeKotlinType coneKotlinTypeCreate = null;
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            ConeRigidType lowerBound2 = coneFlexibleType.getLowerBound();
            FirJavaGenericVarianceViolationTypeChecker firJavaGenericVarianceViolationTypeChecker = INSTANCE;
            ConeRigidType coneRigidTypeRemoveOutProjection = firJavaGenericVarianceViolationTypeChecker.removeOutProjection(lowerBound2, coneTypeContext, z);
            if (!coneFlexibleType.getIsTrivial()) {
                ConeRigidType coneRigidTypeRemoveOutProjection2 = firJavaGenericVarianceViolationTypeChecker.removeOutProjection(coneFlexibleType.getUpperBound(), coneTypeContext, z);
                if (coneRigidTypeRemoveOutProjection != null || coneRigidTypeRemoveOutProjection2 != null) {
                    if (!(coneFlexibleType instanceof ConeRawType)) {
                        if (coneRigidTypeRemoveOutProjection == null) {
                            coneRigidTypeRemoveOutProjection = coneFlexibleType.getLowerBound();
                        }
                        if (coneRigidTypeRemoveOutProjection2 == null) {
                            coneRigidTypeRemoveOutProjection2 = coneFlexibleType.getUpperBound();
                        }
                        coneKotlinTypeCreate = TypeUtilsKt.coneFlexibleOrSimpleType(coneTypeContext, coneRigidTypeRemoveOutProjection, coneRigidTypeRemoveOutProjection2, false);
                    } else {
                        ConeRawType.Companion companion = ConeRawType.INSTANCE;
                        if (coneRigidTypeRemoveOutProjection == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(coneRigidTypeRemoveOutProjection)) == null) {
                            lowerBound = coneFlexibleType.getLowerBound();
                        }
                        if (coneRigidTypeRemoveOutProjection2 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(coneRigidTypeRemoveOutProjection2)) == null) {
                            upperBound = coneFlexibleType.getUpperBound();
                        }
                        coneKotlinTypeCreate = companion.create(lowerBound, upperBound);
                    }
                }
            } else if (coneRigidTypeRemoveOutProjection != null) {
                coneKotlinTypeCreate = TypeUtilsKt.coneFlexibleOrSimpleType(coneTypeContext, coneRigidTypeRemoveOutProjection, TypeUtilsKt.withNullability$default(coneRigidTypeRemoveOutProjection, true, coneTypeContext, null, true, 4, null), true);
            }
            return coneKotlinTypeCreate == null ? coneFlexibleType : coneKotlinTypeCreate;
        }
        if (coneKotlinType instanceof ConeRigidType) {
            return removeOutProjection((ConeRigidType) coneKotlinType, coneTypeContext, z);
        }
        bu8.a();
        return null;
    }

    private final ConeTypeProjection removeOutProjection(ConeTypeProjection coneTypeProjection, ConeTypeContext coneTypeContext, boolean z) {
        if (coneTypeProjection instanceof ConeKotlinTypeProjectionOut) {
            return z ? ((ConeKotlinTypeProjectionOut) coneTypeProjection).getType() : (ConeKotlinTypeProjection) coneTypeProjection;
        }
        if (coneTypeProjection instanceof ConeKotlinTypeProjectionIn) {
            return new ConeKotlinTypeProjectionIn(removeOutProjection(((ConeKotlinTypeProjectionIn) coneTypeProjection).getType(), coneTypeContext, !z));
        }
        if (coneTypeProjection instanceof ConeStarProjection) {
            return z ? StandardTypes.INSTANCE.getNullableAny() : coneTypeProjection;
        }
        if ((coneTypeProjection instanceof ConeKotlinTypeConflictingProjection) || (coneTypeProjection instanceof ConeKotlinType)) {
            return coneTypeProjection;
        }
        bu8.a();
        return null;
    }
}
