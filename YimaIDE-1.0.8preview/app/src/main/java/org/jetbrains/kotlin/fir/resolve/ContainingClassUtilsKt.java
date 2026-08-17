package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\u0014\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004*\u0006\u0012\u0002\b\u00030\u0005\u001a\u001c\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\t\u001a\u0010\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004*\u00020\n¨\u0006\u000b"}, d2 = {"getContainingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getContainingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getContainingSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContainingClassUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final FirRegularClass getContainingClass(FirCallableDeclaration firCallableDeclaration) {
        FirRegularClassSymbol regularClassSymbol;
        firCallableDeclaration.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableDeclaration);
        if (coneClassLikeLookupTagContainingClassLookupTag == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, firCallableDeclaration.getModuleData().getSession())) == null) {
            return null;
        }
        return (FirRegularClass) regularClassSymbol.getFir();
    }

    public static final FirClassLikeSymbol<?> getContainingClassSymbol(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        return FirProviderKt.getFirProvider(firBasedSymbol.getModuleData().getSession()).getContainingClass(firBasedSymbol);
    }

    public static final FirBasedSymbol<?> getContainingSymbol(FirCallableSymbol<?> firCallableSymbol, FirSession firSession) {
        firCallableSymbol.getClass();
        firSession.getClass();
        FirClassLikeSymbol<?> containingClassSymbol = getContainingClassSymbol(firCallableSymbol);
        if (containingClassSymbol != null) {
            return containingClassSymbol;
        }
        FirFile firCallableContainerFile = FirProviderKt.getFirProvider(firSession).getFirCallableContainerFile(firCallableSymbol);
        if (firCallableContainerFile != null) {
            return firCallableContainerFile.getSymbol();
        }
        return null;
    }

    public static final FirClassLikeSymbol<?> getContainingClassSymbol(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return getContainingClassSymbol(firDeclaration.getSymbol());
    }
}
