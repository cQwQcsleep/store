package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u001a\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001c\u0010\u0005\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007¨\u0006\b"}, d2 = {"shouldWarn", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "getShouldWarn", "(Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;)Z", "isNoArgumentProperty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)Z", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContextSensitiveResolutionAmbiguityCheckerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getShouldWarn(FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        return firResolvedSymbolOrigin != FirResolvedSymbolOrigin.ContextSensitive;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isNoArgumentProperty(FirVariableSymbol<?> firVariableSymbol) {
        return (FirSymbolStatusUtilsKt.isExtension(firVariableSymbol) || FirCallableSymbolKt.getHasContextParameters(firVariableSymbol)) ? false : true;
    }
}
