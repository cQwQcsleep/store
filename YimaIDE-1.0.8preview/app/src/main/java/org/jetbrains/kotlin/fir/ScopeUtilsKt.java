package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n\u001a \u0010\u000b\u001a\u0004\u0018\u00010\f*\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\r"}, d2 = {"expandedClassWithConstructorsScope", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "memberRequiredPhaseForRegularClasses", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getPrimaryConstructorSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ScopeUtilsKt {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v3, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static Unit a(Ref.ObjectRef objectRef, FirConstructorSymbol firConstructorSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        ?? fir;
        FirConstructor firConstructor;
        firConstructorSymbol.getClass();
        TypeAliasConstructorInfo typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo((FirFunction) firConstructorSymbol.getFir());
        if (typeAliasConstructorInfo == null || (firConstructor = (FirConstructor) typeAliasConstructorInfo.getOriginalConstructor()) == null) {
            fir = firConstructor;
            fir = firConstructorSymbol.getFir();
        } else {
            while (true) {
                fir = firConstructor;
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride((FirCallableDeclaration) fir) || (fir.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                fir = originalForSubstitutionOverrideAttr;
            }
        }
        if (((FirConstructor) fir).getIsPrimary() && objectRef.element == null) {
            objectRef.element = firConstructorSymbol;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Pair<FirRegularClassSymbol, FirScope> expandedClassWithConstructorsScope(FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase) {
        FirRegularClassSymbol regularClassSymbol;
        firClassLikeSymbol.getClass();
        firSession.getClass();
        scopeSession.getClass();
        if (firClassLikeSymbol instanceof FirRegularClassSymbol) {
            return TuplesKt.to(firClassLikeSymbol, FirKotlinScopeProviderKt.unsubstitutedScope((FirClassSymbol<?>) firClassLikeSymbol, firSession, scopeSession, false, firResolvePhase));
        }
        if (!(firClassLikeSymbol instanceof FirTypeAliasSymbol)) {
            return null;
        }
        FirTypeAliasSymbol firTypeAliasSymbol = (FirTypeAliasSymbol) firClassLikeSymbol;
        ConeKotlinType coneType = firTypeAliasSymbol.getResolvedExpandedTypeRef().getConeType();
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        if (coneClassLikeType == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeType, firSession)) == null) {
            return null;
        }
        return TuplesKt.to(regularClassSymbol, FirKotlinScopeProviderKt.scopeForTypeAlias((FirTypeAlias) firTypeAliasSymbol.getFir(), firSession, scopeSession));
    }

    public static final FirConstructorSymbol getPrimaryConstructorSymbol(FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession, ScopeSession scopeSession) {
        firClassLikeSymbol.getClass();
        firSession.getClass();
        scopeSession.getClass();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Pair<FirRegularClassSymbol, FirScope> pairExpandedClassWithConstructorsScope = expandedClassWithConstructorsScope(firClassLikeSymbol, firSession, scopeSession, null);
        if (pairExpandedClassWithConstructorsScope == null) {
            return null;
        }
        ((FirScope) pairExpandedClassWithConstructorsScope.component2()).processDeclaredConstructors(new Function1() { // from class: jxc
            public final Object invoke(Object obj) {
                return ScopeUtilsKt.a(objectRef, (FirConstructorSymbol) obj);
            }
        });
        return (FirConstructorSymbol) objectRef.element;
    }
}
