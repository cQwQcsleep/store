package org.jetbrains.kotlin.fir.resolve.providers;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¨\u0006\u0005"}, d2 = {"getContainingFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirProvider;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirProviderUtilsKt {
    public static final FirFile getContainingFile(FirProvider firProvider, FirBasedSymbol<?> firBasedSymbol) {
        firProvider.getClass();
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirSyntheticFunctionSymbol) {
            FirSyntheticFunctionSymbol firSyntheticFunctionSymbol = (FirSyntheticFunctionSymbol) firBasedSymbol;
            return firProvider.getFirClassifierContainerFileIfAny(new ClassId(firSyntheticFunctionSymbol.getCallableId().getPackageName(), firSyntheticFunctionSymbol.getCallableId().getCallableName()));
        }
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            return firProvider.getFirClassifierContainerFileIfAny((FirClassLikeSymbol<?>) firBasedSymbol);
        }
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return firProvider.getFirCallableContainerFile((FirCallableSymbol) firBasedSymbol);
        }
        return null;
    }
}
