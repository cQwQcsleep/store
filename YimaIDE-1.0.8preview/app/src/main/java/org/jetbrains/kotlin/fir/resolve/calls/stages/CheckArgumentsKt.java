package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.FirSamResolver;
import org.jetbrains.kotlin.fir.resolve.ImplicitIntegerCoercionKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeLambdaWithTypeVariableAsExpectedTypeAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.LowerPriorityToPreserveCompatibilityDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckArgumentsKt;
import org.jetbrains.kotlin.fir.resolve.inference.ConstraintSystemCompleterKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilderKt;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeSystemCommonSuperTypesContextContextualKt;
import org.jetbrains.kotlin.types.model.TypeSystemContextHelpersKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a9\u0010\u0002\u001a\u0004\u0018\u00010\u0003*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\r\u001a/\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0003H\u0002R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0010\u001a-\u0010\u0011\u001a\u00020\u0012*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0003H\u0002R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0014\u001a\u001d\u0010\u0015\u001a\u00020\u0012*\u00020\nH\u0002R\u00020\u0016j\u0006\u0010\u0005\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017\u001a*\u0010\u0018\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0003H\u0002\u001a\u000e\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\nH\u0002\u001a\u001d\u0010\u001d\u001a\u00020\u001e*\u00020\u001fH\u0002R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010 \"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"SAM_LOOKUP_NAME", "Lorg/jetbrains/kotlin/name/Name;", "prepareExpectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getExpectedTypeWithSAMConversion", "candidateExpectedType", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "shouldUseSamConversion", Argument.Delimiters.none, "candidate", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "isCallWithGenericReturnTypeAndMatchingLambda", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Z", "getExpectedTypeWithImplicitIntegerCoercion", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "namedReferenceWithCandidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirNamedReferenceWithCandidate;", "markCandidateForCompatibilityResolve", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;)V", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckArgumentsKt {
    private static final Name SAM_LOOKUP_NAME;

    static {
        Name nameSpecial = Name.special("<SAM-CONSTRUCTOR>");
        nameSpecial.getClass();
        SAM_LOOKUP_NAME = nameSpecial;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:26:0x0053  */
    private static final ConeKotlinType getExpectedTypeWithImplicitIntegerCoercion(FirSession firSession, FirExpression firExpression, FirValueParameter firValueParameter, ConeKotlinType coneKotlinType) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedReturnType;
        FirCallableSymbol resolvedCallableSymbol$default;
        if (!FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).supportsFeature(LanguageFeature.ImplicitSignedToUnsignedIntegerConversion) || !ImplicitIntegerCoercionKt.isMarkedWithImplicitIntegerCoercion(firValueParameter) || !ConeBuiltinTypeUtilsKt.isUnsignedTypeOrNullableUnsignedType(TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null))) {
            return null;
        }
        if (ResolveUtilsKt.isIntegerLiteralOrOperatorCall(firExpression)) {
            resolvedReturnType = FirTypeUtilsKt.getResolvedType(firExpression);
        } else {
            FirReference reference = ReferenceUtilsKt.toReference(firExpression, firSession);
            if (reference == null || (resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(reference, false, 1, null)) == null) {
                resolvedReturnType = null;
            } else {
                if (!resolvedCallableSymbol$default.getRawStatus().isConst() || !ImplicitIntegerCoercionKt.isMarkedWithImplicitIntegerCoercion((FirCallableSymbol<?>) resolvedCallableSymbol$default)) {
                    resolvedCallableSymbol$default = null;
                }
                if (resolvedCallableSymbol$default != null) {
                    resolvedReturnType = resolvedCallableSymbol$default.getResolvedReturnType();
                } else {
                    resolvedReturnType = null;
                }
            }
        }
        if (resolvedReturnType != null) {
            return TypeUtilsKt.withNullabilityOf(resolvedReturnType, coneKotlinType, TypeComponentsKt.getTypeContext(firSession));
        }
        return null;
    }

    private static final ConeKotlinType getExpectedTypeWithSAMConversion(ResolutionContext resolutionContext, Candidate candidate, FirExpression firExpression, ConeKotlinType coneKotlinType) {
        FirSamResolver.SamConversionInfo samInfoForPossibleSamType;
        if (FunctionalTypeUtilsKt.isSomeFunctionType(coneKotlinType, resolutionContext.getSession()) || (samInfoForPossibleSamType = resolutionContext.getBodyResolveComponents().getSamResolver().getSamInfoForPossibleSamType(coneKotlinType)) == null || !shouldUseSamConversion(resolutionContext, firExpression, candidate, coneKotlinType)) {
            return null;
        }
        candidate.setSamConversionOfArgument(FirExpressionUtilKt.unwrapArgument(firExpression), samInfoForPossibleSamType);
        return samInfoForPossibleSamType.getFunctionalType();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final boolean isCallWithGenericReturnTypeAndMatchingLambda(SessionHolder sessionHolder, FirExpression firExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        Candidate candidate;
        List<ConePostponedResolvedAtom> postponedAtoms;
        FirSession session = sessionHolder.getSession();
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidateNamedReferenceWithCandidate = namedReferenceWithCandidate(firExpression);
        if (firNamedReferenceWithCandidateNamedReferenceWithCandidate != null && (candidate = firNamedReferenceWithCandidateNamedReferenceWithCandidate.getCandidate()) != null && (postponedAtoms = candidate.getPostponedAtoms()) != null) {
            List<ConePostponedResolvedAtom> list = postponedAtoms;
            if ((list instanceof Collection) && list.isEmpty()) {
                return false;
            }
            for (ConePostponedResolvedAtom conePostponedResolvedAtom : list) {
                if ((conePostponedResolvedAtom instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) && Intrinsics.areEqual(TypeSystemContextHelpersKt.typeConstructor(((ConeLambdaWithTypeVariableAsExpectedTypeAtom) conePostponedResolvedAtom).mo581getExpectedType(), TypeComponentsKt.getTypeContext(session)), TypeSystemContextHelpersKt.typeConstructor(resolvedType, TypeComponentsKt.getTypeContext(session)))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void markCandidateForCompatibilityResolve(ResolutionContext resolutionContext, CheckerSink checkerSink) {
        if (LanguageVersionUtilsKt.disableCompatibilityModeForNewInference(resolutionContext)) {
            return;
        }
        checkerSink.reportDiagnostic(LowerPriorityToPreserveCompatibilityDiagnostic.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FirNamedReferenceWithCandidate namedReferenceWithCandidate(FirExpression firExpression) {
        if (firExpression instanceof FirResolvable) {
            FirReference calleeReference = ((FirResolvable) firExpression).getCalleeReference();
            if (calleeReference instanceof FirNamedReferenceWithCandidate) {
                return (FirNamedReferenceWithCandidate) calleeReference;
            }
            return null;
        }
        if (firExpression instanceof FirSafeCallExpression) {
            FirStatement selector = ((FirSafeCallExpression) firExpression).getSelector();
            FirExpression firExpression2 = selector instanceof FirExpression ? (FirExpression) selector : null;
            if (firExpression2 != null) {
                return namedReferenceWithCandidate(firExpression2);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeKotlinType prepareExpectedType(ResolutionContext resolutionContext, Candidate candidate, CallInfo callInfo, FirExpression firExpression, FirValueParameter firValueParameter) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeClassLikeLookupTag classLikeLookupTagIfAny;
        ConeClassLikeLookupTag coneClassLikeLookupTag = null;
        if (firValueParameter == null) {
            return null;
        }
        ConeKotlinType expectedType = ArgumentUtilsKt.getExpectedType(firExpression, resolutionContext.getSession(), firValueParameter);
        ConeKotlinType expectedTypeWithSAMConversion = getExpectedTypeWithSAMConversion(resolutionContext, candidate, firExpression, expectedType);
        if (expectedTypeWithSAMConversion != null) {
            FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(resolutionContext.getSession());
            if (lookupTracker != null && (classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()))) != null) {
                FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) resolutionContext, classLikeLookupTagIfAny);
                if (symbol != null && !((FirClassLikeDeclaration) symbol.getFir()).getIsLocal()) {
                    coneClassLikeLookupTag = classLikeLookupTagIfAny;
                }
                if (coneClassLikeLookupTag != null) {
                    ClassId classId = coneClassLikeLookupTag.getClassId();
                    String strAsString = SAM_LOOKUP_NAME.asString();
                    strAsString.getClass();
                    FirLookupTrackerComponentKt.recordClassMemberLookup(lookupTracker, strAsString, classId, callInfo.getCallSite().getSource(), callInfo.getContainingFile().getSource());
                    FirLookupTrackerComponentKt.recordClassLikeLookup(lookupTracker, classId, callInfo.getCallSite().getSource(), callInfo.getContainingFile().getSource());
                }
            }
            expectedType = expectedTypeWithSAMConversion;
        } else {
            ConeKotlinType expectedTypeWithImplicitIntegerCoercion = getExpectedTypeWithImplicitIntegerCoercion(resolutionContext.getSession(), firExpression, firValueParameter, expectedType);
            if (expectedTypeWithImplicitIntegerCoercion != null) {
                expectedType = expectedTypeWithImplicitIntegerCoercion;
            }
        }
        return candidate.getSubstitutor().substituteOrSelf(expectedType);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final boolean shouldUseSamConversion(final ResolutionContext resolutionContext, FirExpression firExpression, Candidate candidate, ConeKotlinType coneKotlinType) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirExpression firExpressionUnwrapArgument = FirExpressionUtilKt.unwrapArgument(firExpression);
        if ((firExpressionUnwrapArgument instanceof FirAnonymousFunctionExpression) || (firExpressionUnwrapArgument instanceof FirCallableReferenceAccess) || isCallWithGenericReturnTypeAndMatchingLambda(resolutionContext, firExpressionUnwrapArgument)) {
            return true;
        }
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
        if (ConstraintSystemBuilderKt.isSubtypeConstraintCompatible(ConstraintSystemCompleterKt.getCsBuilder(candidate), resolvedType, candidate.getSubstitutor().substituteOrSelf(coneKotlinType))) {
            return false;
        }
        return TypeSystemCommonSuperTypesContextContextualKt.anySuperTypeConstructor(resolutionContext.getTypeContext(), resolvedType, new Function1() { // from class: ej1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CheckArgumentsKt.shouldUseSamConversion$lambda$0$0(resolutionContext, (RigidTypeMarker) obj));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldUseSamConversion$lambda$0$0(ResolutionContext resolutionContext, RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        if (rigidTypeMarker instanceof ConeKotlinType) {
            return FunctionalTypeUtilsKt.isSomeFunctionType((ConeKotlinType) rigidTypeMarker, resolutionContext.getSession());
        }
        w01.a("Failed requirement.");
        return false;
    }
}
