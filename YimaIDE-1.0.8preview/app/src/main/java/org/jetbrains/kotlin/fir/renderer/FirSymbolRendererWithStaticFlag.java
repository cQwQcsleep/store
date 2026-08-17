package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0014¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirSymbolRendererWithStaticFlag;", "Lorg/jetbrains/kotlin/fir/renderer/FirSymbolRenderer;", "<init>", "()V", "renderReference", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSymbolRendererWithStaticFlag extends FirSymbolRenderer {
    @Override // org.jetbrains.kotlin.fir.renderer.FirSymbolRenderer
    public String renderReference(FirBasedSymbol<?> symbol) {
        symbol.getClass();
        String strRenderReference = super.renderReference(symbol);
        if (!(symbol instanceof FirCallableSymbol) || (symbol instanceof FirEnumEntrySymbol) || !((FirCallableSymbol) symbol).getRawStatus().isStatic()) {
            return strRenderReference;
        }
        return strRenderReference + "*s";
    }
}
