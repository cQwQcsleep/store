package org.jetbrains.kotlin.fir.resolve.calls.jvm;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolver;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u001a\u0010\n\u001a\u00020\u000b*\u00020\f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u001a\u0010\r\u001a\u00020\u000b*\u00020\u000e2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u0014\u0010\u000f\u001a\u00020\u000b*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/jvm/JvmPlatformOverloadsConflictResolver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "chooseMaximallySpecificCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "candidates", "isShadowedByFieldCandidate", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "isShadowedByPropertyCandidate", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "strictlyDerivedFrom", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "other", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmPlatformOverloadsConflictResolver extends ConeCallConflictResolver {
    private final FirSession session;

    public JvmPlatformOverloadsConflictResolver(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean isShadowedByFieldCandidate(FirProperty firProperty, Set<Candidate> set) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirCallableDeclaration firCallableDeclaration = firProperty;
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(((FirProperty) firCallableDeclaration).getSymbol());
        if (coneClassLikeLookupTagContainingClassLookupTag == null) {
            return false;
        }
        Iterator<Candidate> it = set.iterator();
        while (it.hasNext()) {
            FirDeclaration fir = it.next().getSymbol().getFir();
            FirCallableDeclaration firCallableDeclaration2 = fir instanceof FirField ? (FirField) fir : null;
            if (firCallableDeclaration2 != null) {
                while (true) {
                    FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                    if (originalForSubstitutionOverrideAttr2 == null) {
                        originalForSubstitutionOverrideAttr2 = ClassMembersKt.isIntersectionOverride(firCallableDeclaration2) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration2) : null;
                    }
                    if (originalForSubstitutionOverrideAttr2 == null) {
                        break;
                    }
                    firCallableDeclaration2 = originalForSubstitutionOverrideAttr2;
                }
                ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag2 = ClassMembersKt.containingClassLookupTag(((FirField) firCallableDeclaration2).getSymbol());
                if (coneClassLikeLookupTagContainingClassLookupTag2 != null && !strictlyDerivedFrom(coneClassLikeLookupTagContainingClassLookupTag, coneClassLikeLookupTagContainingClassLookupTag2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean isShadowedByPropertyCandidate(FirField firField, Set<Candidate> set) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirCallableDeclaration firCallableDeclaration = firField;
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(((FirField) firCallableDeclaration).getSymbol());
        if (coneClassLikeLookupTagContainingClassLookupTag == null) {
            return false;
        }
        Iterator<Candidate> it = set.iterator();
        while (it.hasNext()) {
            FirDeclaration fir = it.next().getSymbol().getFir();
            FirCallableDeclaration firCallableDeclaration2 = fir instanceof FirProperty ? (FirProperty) fir : null;
            if (firCallableDeclaration2 != null) {
                while (true) {
                    FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                    if (originalForSubstitutionOverrideAttr2 == null) {
                        break;
                    }
                    firCallableDeclaration2 = originalForSubstitutionOverrideAttr2;
                }
                ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag2 = ClassMembersKt.containingClassLookupTag(((FirProperty) firCallableDeclaration2).getSymbol());
                if (coneClassLikeLookupTagContainingClassLookupTag2 != null && strictlyDerivedFrom(coneClassLikeLookupTagContainingClassLookupTag2, coneClassLikeLookupTagContainingClassLookupTag)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean strictlyDerivedFrom(ConeClassLikeLookupTag coneClassLikeLookupTag, ConeClassLikeLookupTag coneClassLikeLookupTag2) {
        FirClassSymbol<?> classSymbol;
        FirClass firClass;
        if (Intrinsics.areEqual(coneClassLikeLookupTag, coneClassLikeLookupTag2) || (classSymbol = ToSymbolUtilsKt.toClassSymbol(coneClassLikeLookupTag, this.session)) == null || (firClass = (FirClass) classSymbol.getFir()) == null) {
            return false;
        }
        return SupertypeUtilsKt.isSubclassOf$default(firClass, coneClassLikeLookupTag2, this.session, true, null, false, 24, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolver
    public Set<Candidate> chooseMaximallySpecificCandidates(Set<Candidate> candidates) throws KotlinIllegalArgumentExceptionWithAttachments {
        candidates.getClass();
        if (!FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session).supportsFeature(LanguageFeature.PreferJavaFieldOverload)) {
            return candidates;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Candidate candidate : candidates) {
            FirDeclaration fir = candidate.getSymbol().getFir();
            if (fir instanceof FirProperty) {
                if (!isShadowedByFieldCandidate((FirProperty) fir, candidates)) {
                    linkedHashSet.add(candidate);
                }
            } else if (!(fir instanceof FirField)) {
                linkedHashSet.add(candidate);
            } else if (!isShadowedByPropertyCandidate((FirField) fir, candidates)) {
                linkedHashSet.add(candidate);
            }
        }
        return linkedHashSet;
    }
}
