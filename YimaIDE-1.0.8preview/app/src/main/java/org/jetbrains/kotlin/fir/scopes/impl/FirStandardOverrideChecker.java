package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.PhaseUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J0\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\"\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001d\u001a\u00020!2\u0006\u0010\u001f\u001a\u00020!2\u0006\u0010\"\u001a\u00020\tH\u0016J$\u0010#\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010$\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020%H\u0016J\u001e\u0010$\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020%2\u0006\u0010&\u001a\u00020\tJ\u0018\u0010'\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020!2\u0006\u0010\u001f\u001a\u00020(H\u0016J\u001e\u0010'\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020!2\u0006\u0010\u001f\u001a\u00020(2\u0006\u0010&\u001a\u00020\tJ(\u0010)\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020!2\u0006\u0010\u001f\u001a\u00020!2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\tH\u0002J$\u0010*\u001a\u00020+2\u0010\u0010,\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030.0-2\b\u0010/\u001a\u0004\u0018\u000100H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirStandardOverrideChecker;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractOverrideChecker;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "context", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "isEqualTypes", Argument.Delimiters.none, "candidateType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "baseType", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "candidateTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "baseTypeRef", "maybeEqualErrorTypes", "ref1", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "ref2", "isEqualBound", "overrideBound", "baseBound", "overrideTypeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "baseTypeParameter", "isCompatibleTypeParameters", "overrideCandidate", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "baseDeclaration", "buildTypeParametersSubstitutorIfCompatible", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "checkReifiednessIsSame", "isEqualReceiverTypes", "isOverriddenFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "ignoreVisibility", "isOverriddenProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "commonCallableChecks", "chooseIntersectionVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "overrides", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "dispatchClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirStandardOverrideChecker extends FirAbstractOverrideChecker {
    private final ConeInferenceContext context;
    private final FirSession session;

    public FirStandardOverrideChecker(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.context = TypeComponentsKt.getTypeContext(firSession);
    }

    private final boolean commonCallableChecks(FirCallableDeclaration overrideCandidate, FirCallableDeclaration baseDeclaration, ConeSubstitutor substitutor, boolean ignoreVisibility) {
        if ((!ignoreVisibility && Visibilities.INSTANCE.isPrivate(baseDeclaration.getStatus().getVisibility())) || overrideCandidate.getContextParameters().size() != baseDeclaration.getContextParameters().size()) {
            return false;
        }
        FirResolvePhase firResolvePhase = FirResolvePhase.TYPES;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(overrideCandidate, firResolvePhase);
        FirLazyDeclarationResolverKt.lazyResolveToPhase(baseDeclaration, firResolvePhase);
        FirReceiverParameter receiverParameter = overrideCandidate.getReceiverParameter();
        FirTypeRef typeRef = receiverParameter != null ? receiverParameter.getTypeRef() : null;
        FirReceiverParameter receiverParameter2 = baseDeclaration.getReceiverParameter();
        if (isEqualReceiverTypes(typeRef, receiverParameter2 != null ? receiverParameter2.getTypeRef() : null, substitutor)) {
            List<Pair> listZip = CollectionsKt.zip(overrideCandidate.getContextParameters(), baseDeclaration.getContextParameters());
            if ((listZip instanceof Collection) && listZip.isEmpty()) {
                return true;
            }
            for (Pair pair : listZip) {
                if (!isEqualTypes(((FirValueParameter) pair.component1()).getReturnTypeRef(), ((FirValueParameter) pair.component2()).getReturnTypeRef(), substitutor)) {
                }
            }
            return true;
        }
        return false;
    }

    private final boolean isCompatibleTypeParameters(FirTypeParameterRef overrideCandidate, FirTypeParameterRef baseDeclaration, ConeSubstitutor substitutor) {
        if (Intrinsics.areEqual(overrideCandidate.getSymbol(), baseDeclaration.getSymbol())) {
            return true;
        }
        if (!(overrideCandidate instanceof FirTypeParameter) || !(baseDeclaration instanceof FirTypeParameter)) {
            return false;
        }
        FirTypeParameter firTypeParameter = (FirTypeParameter) overrideCandidate;
        FirTypeParameter firTypeParameter2 = (FirTypeParameter) baseDeclaration;
        if (firTypeParameter.getBounds().size() != firTypeParameter2.getBounds().size()) {
            return false;
        }
        List<Pair> listZip = CollectionsKt.zip(firTypeParameter.getSymbol().getResolvedBounds(), firTypeParameter2.getSymbol().getResolvedBounds());
        if ((listZip instanceof Collection) && listZip.isEmpty()) {
            return true;
        }
        for (Pair pair : listZip) {
            FirStandardOverrideChecker firStandardOverrideChecker = this;
            ConeSubstitutor coneSubstitutor = substitutor;
            if (!firStandardOverrideChecker.isEqualBound((FirResolvedTypeRef) pair.component1(), (FirResolvedTypeRef) pair.component2(), firTypeParameter, firTypeParameter2, coneSubstitutor)) {
                return false;
            }
            this = firStandardOverrideChecker;
            substitutor = coneSubstitutor;
        }
        return true;
    }

    private final boolean isEqualBound(FirTypeRef overrideBound, FirTypeRef baseBound, FirTypeParameter overrideTypeParameter, FirTypeParameter baseTypeParameter, ConeSubstitutor substitutor) {
        ConeKotlinType coneKotlinTypeSubstituteOrSelf = substitutor.substituteOrSelf(FirTypeUtilsKt.getConeType(overrideBound));
        ConeKotlinType coneKotlinTypeSubstituteOrSelf2 = substitutor.substituteOrSelf(FirTypeUtilsKt.getConeType(baseBound));
        if (AbstractTypeChecker.equalTypes$default(AbstractTypeChecker.INSTANCE, this.context, coneKotlinTypeSubstituteOrSelf, coneKotlinTypeSubstituteOrSelf2, false, false, 24, (Object) null)) {
            return true;
        }
        List<FirResolvedTypeRef> resolvedBounds = overrideTypeParameter.getSymbol().getResolvedBounds();
        if ((resolvedBounds instanceof Collection) && resolvedBounds.isEmpty()) {
            return false;
        }
        Iterator<T> it = resolvedBounds.iterator();
        while (it.hasNext()) {
            if (isEqualTypes(((FirResolvedTypeRef) it.next()).getConeType(), coneKotlinTypeSubstituteOrSelf2, substitutor)) {
                List<FirResolvedTypeRef> resolvedBounds2 = baseTypeParameter.getSymbol().getResolvedBounds();
                if ((resolvedBounds2 instanceof Collection) && resolvedBounds2.isEmpty()) {
                    return false;
                }
                Iterator<T> it2 = resolvedBounds2.iterator();
                while (it2.hasNext()) {
                    if (isEqualTypes(((FirResolvedTypeRef) it2.next()).getConeType(), coneKotlinTypeSubstituteOrSelf, substitutor)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private final boolean isEqualReceiverTypes(FirTypeRef candidateTypeRef, FirTypeRef baseTypeRef, ConeSubstitutor substitutor) {
        if (candidateTypeRef == null || baseTypeRef == null) {
            return candidateTypeRef == null && baseTypeRef == null;
        }
        return isEqualTypes(candidateTypeRef, baseTypeRef, substitutor);
    }

    private final boolean maybeEqualErrorTypes(FirErrorTypeRef ref1, FirErrorTypeRef ref2) {
        FirUserTypeRef delegatedTypeRef = ref1.getDelegatedTypeRef();
        FirUserTypeRef firUserTypeRef = delegatedTypeRef instanceof FirUserTypeRef ? delegatedTypeRef : null;
        if (firUserTypeRef == null) {
            return false;
        }
        FirTypeRef delegatedTypeRef2 = ref2.getDelegatedTypeRef();
        FirUserTypeRef firUserTypeRef2 = delegatedTypeRef2 instanceof FirUserTypeRef ? (FirUserTypeRef) delegatedTypeRef2 : null;
        if (firUserTypeRef2 == null || firUserTypeRef.getQualifier().size() != firUserTypeRef2.getQualifier().size()) {
            return false;
        }
        List<Pair> listZip = CollectionsKt.zip(firUserTypeRef.getQualifier(), firUserTypeRef2.getQualifier());
        if ((listZip instanceof Collection) && listZip.isEmpty()) {
            return true;
        }
        for (Pair pair : listZip) {
            if (!Intrinsics.areEqual(((FirQualifierPart) pair.component1()).getName(), ((FirQualifierPart) pair.component2()).getName())) {
                return false;
            }
        }
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractOverrideChecker
    public ConeSubstitutor buildTypeParametersSubstitutorIfCompatible(FirCallableDeclaration overrideCandidate, FirCallableDeclaration baseDeclaration, boolean checkReifiednessIsSame) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        FirResolvePhase firResolvePhase = FirResolvePhase.TYPES;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(overrideCandidate, firResolvePhase);
        FirLazyDeclarationResolverKt.lazyResolveToPhase(baseDeclaration, firResolvePhase);
        ConeSubstitutor coneSubstitutorBuildSubstitutorForOverridesCheck = FirAbstractOverrideCheckerKt.buildSubstitutorForOverridesCheck(overrideCandidate, baseDeclaration, this.session);
        if (coneSubstitutorBuildSubstitutorForOverridesCheck == null) {
            return null;
        }
        if (!overrideCandidate.getTypeParameters().isEmpty()) {
            List<Pair> listZip = CollectionsKt.zip(overrideCandidate.getTypeParameters(), baseDeclaration.getTypeParameters());
            if ((listZip instanceof Collection) && listZip.isEmpty()) {
                return coneSubstitutorBuildSubstitutorForOverridesCheck;
            }
            for (Pair pair : listZip) {
                FirTypeParameterRef firTypeParameterRef = (FirTypeParameterRef) pair.component1();
                FirTypeParameterRef firTypeParameterRef2 = (FirTypeParameterRef) pair.component2();
                if (!isCompatibleTypeParameters(firTypeParameterRef, firTypeParameterRef2, coneSubstitutorBuildSubstitutorForOverridesCheck) || (checkReifiednessIsSame && firTypeParameterRef.getSymbol().isReified() != firTypeParameterRef2.getSymbol().isReified())) {
                    return null;
                }
            }
        }
        return coneSubstitutorBuildSubstitutorForOverridesCheck;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public Visibility chooseIntersectionVisibility(Collection<? extends FirCallableSymbol<?>> overrides, FirRegularClassSymbol dispatchClassSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        Visibility visibility;
        overrides.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : overrides) {
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) obj;
            if (!FirOverrideUtilsKt.isAbstractAccordingToRawStatus(firCallableSymbol) && !Intrinsics.areEqual(firCallableSymbol.getCallableId(), StandardClassIds.Callables.INSTANCE.getClone())) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            visibility = Visibilities.Private.INSTANCE;
            Iterator<? extends FirCallableSymbol<?>> it = overrides.iterator();
            while (it.hasNext()) {
                Object fir = it.next().getFir();
                fir.getClass();
                Visibility visibility2 = ((FirMemberDeclaration) fir).getStatus().getVisibility();
                Integer numCompare = Visibilities.INSTANCE.compare(visibility2, visibility);
                if (numCompare == null) {
                    visibility = null;
                    break;
                }
                if (numCompare.intValue() > 0) {
                    visibility = visibility2;
                }
            }
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                linkedHashSet.add(((FirCallableSymbol) it2.next()).getRawStatus().getVisibility());
            }
            visibility = (Visibility) CollectionsKt.singleOrNull(linkedHashSet);
        }
        return visibility == null ? Visibilities.Unknown.INSTANCE : visibility;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isEqualTypes(FirTypeRef candidateTypeRef, FirTypeRef baseTypeRef, ConeSubstitutor substitutor) {
        candidateTypeRef.getClass();
        baseTypeRef.getClass();
        substitutor.getClass();
        FirSession firSession = this.session;
        FirResolvePhase firResolvePhase = FirResolvePhase.TYPES;
        PhaseUtilsKt.ensureResolvedTypeDeclaration(candidateTypeRef, firSession, firResolvePhase);
        PhaseUtilsKt.ensureResolvedTypeDeclaration(baseTypeRef, this.session, firResolvePhase);
        return ((candidateTypeRef instanceof FirErrorTypeRef) && (baseTypeRef instanceof FirErrorTypeRef)) ? maybeEqualErrorTypes((FirErrorTypeRef) candidateTypeRef, (FirErrorTypeRef) baseTypeRef) : isEqualTypes(FirTypeUtilsKt.getConeType(candidateTypeRef), FirTypeUtilsKt.getConeType(baseTypeRef), substitutor);
    }

    public final boolean isOverriddenFunction(FirNamedFunction overrideCandidate, FirNamedFunction baseDeclaration, boolean ignoreVisibility) {
        ConeSubstitutor coneSubstitutorBuildTypeParametersSubstitutorIfCompatible$default;
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        if (overrideCandidate.getValueParameters().size() != baseDeclaration.getValueParameters().size() || Intrinsics.areEqual(DeprecationUtilsKt.isHiddenToOvercomeSignatureClash(baseDeclaration), Boolean.TRUE) || (coneSubstitutorBuildTypeParametersSubstitutorIfCompatible$default = FirAbstractOverrideChecker.buildTypeParametersSubstitutorIfCompatible$default(this, overrideCandidate, baseDeclaration, false, 4, null)) == null || !commonCallableChecks(overrideCandidate, baseDeclaration, coneSubstitutorBuildTypeParametersSubstitutorIfCompatible$default, ignoreVisibility)) {
            return false;
        }
        List<Pair> listZip = CollectionsKt.zip(overrideCandidate.getValueParameters(), baseDeclaration.getValueParameters());
        if ((listZip instanceof Collection) && listZip.isEmpty()) {
            return true;
        }
        for (Pair pair : listZip) {
            if (!isEqualTypes(((FirValueParameter) pair.component1()).getReturnTypeRef(), ((FirValueParameter) pair.component2()).getReturnTypeRef(), coneSubstitutorBuildTypeParametersSubstitutorIfCompatible$default)) {
                return false;
            }
        }
        return true;
    }

    public final boolean isOverriddenProperty(FirCallableDeclaration overrideCandidate, FirProperty baseDeclaration, boolean ignoreVisibility) {
        ConeSubstitutor coneSubstitutorBuildTypeParametersSubstitutorIfCompatible$default;
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        if ((overrideCandidate instanceof FirProperty) && (coneSubstitutorBuildTypeParametersSubstitutorIfCompatible$default = FirAbstractOverrideChecker.buildTypeParametersSubstitutorIfCompatible$default(this, overrideCandidate, baseDeclaration, false, 4, null)) != null) {
            return commonCallableChecks(overrideCandidate, baseDeclaration, coneSubstitutorBuildTypeParametersSubstitutorIfCompatible$default, ignoreVisibility);
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public boolean isOverriddenProperty(FirCallableDeclaration overrideCandidate, FirProperty baseDeclaration) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        return isOverriddenProperty(overrideCandidate, baseDeclaration, false);
    }

    private final boolean isEqualTypes(ConeKotlinType candidateType, ConeKotlinType baseType, ConeSubstitutor substitutor) {
        return AbstractTypeChecker.equalTypes$default(AbstractTypeChecker.INSTANCE, this.context, substitutor.substituteOrSelf(candidateType), substitutor.substituteOrSelf(baseType), false, FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session).supportsFeature(LanguageFeature.AllowDnnTypeOverridingFlexibleType), 8, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public boolean isOverriddenFunction(FirNamedFunction overrideCandidate, FirNamedFunction baseDeclaration) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        return isOverriddenFunction(overrideCandidate, baseDeclaration, false);
    }
}
