package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002¨\u0006\u0004"}, d2 = {"describeSymbol", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeDiagnosticsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String describeSymbol(FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            return ((FirClassLikeSymbol) firBasedSymbol).getClassId().asString();
        }
        return firBasedSymbol instanceof FirCallableSymbol ? ((FirCallableSymbol) firBasedSymbol).callableIdAsString() : String.valueOf(firBasedSymbol);
    }
}
