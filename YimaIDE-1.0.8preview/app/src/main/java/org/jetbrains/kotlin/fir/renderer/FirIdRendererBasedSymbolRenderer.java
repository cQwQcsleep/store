package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirIdRendererBasedSymbolRenderer;", "Lorg/jetbrains/kotlin/fir/renderer/FirSymbolRenderer;", "<init>", "()V", "printReference", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirIdRendererBasedSymbolRenderer extends FirSymbolRenderer {
    @Override // org.jetbrains.kotlin.fir.renderer.FirSymbolRenderer
    public void printReference(FirBasedSymbol<?> symbol) {
        symbol.getClass();
        if (symbol instanceof FirCallableSymbol) {
            getComponents$org_jetbrains_kotlin_tree().getIdRenderer().renderCallableId(((FirCallableSymbol) symbol).getCallableIdForRendering());
        } else if (symbol instanceof FirClassLikeSymbol) {
            getComponents$org_jetbrains_kotlin_tree().getIdRenderer().renderClassId(((FirClassLikeSymbol) symbol).getClassId());
        } else {
            super.printReference(symbol);
        }
    }
}
