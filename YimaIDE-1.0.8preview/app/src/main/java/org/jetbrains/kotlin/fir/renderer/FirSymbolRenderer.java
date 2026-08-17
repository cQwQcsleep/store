package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKindKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0016J\u0014\u0010\u0012\u001a\u00020\u00132\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirSymbolRenderer;", Argument.Delimiters.none, "<init>", "()V", "components", "Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "getComponents$org_jetbrains_kotlin_tree", "()Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "setComponents$org_jetbrains_kotlin_tree", "(Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;)V", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "printReference", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "renderReference", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirSymbolRenderer {
    public FirRendererComponents components;

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirRendererComponents getComponents$org_jetbrains_kotlin_tree() throws UninitializedPropertyAccessException {
        FirRendererComponents firRendererComponents = this.components;
        if (firRendererComponents != null) {
            return firRendererComponents;
        }
        Intrinsics.throwUninitializedPropertyAccessException("components");
        return null;
    }

    public final FirPrinter getPrinter() {
        return getComponents$org_jetbrains_kotlin_tree().getPrinter();
    }

    public void printReference(FirBasedSymbol<?> symbol) {
        symbol.getClass();
        getPrinter().print(renderReference(symbol));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String renderReference(FirBasedSymbol<?> symbol) {
        symbol.getClass();
        if (symbol instanceof FirValueParameterSymbol) {
            FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) symbol;
            if (FirValueParameterKindKt.isLegacyContextReceiver((FirValueParameter) firValueParameterSymbol.getFir())) {
                return "context of " + renderReference(((FirValueParameter) firValueParameterSymbol.getFir()).getContainingDeclarationSymbol());
            }
        }
        if (symbol instanceof FirCallableSymbol) {
            return ((FirCallableSymbol) symbol).callableIdAsString();
        }
        if (symbol instanceof FirClassLikeSymbol) {
            return ((FirClassLikeSymbol) symbol).getClassId().toString();
        }
        return symbol instanceof FirReceiverParameterSymbol ? renderReference(((FirReceiverParameter) ((FirReceiverParameterSymbol) symbol).getFir()).getContainingDeclarationSymbol()) : "?";
    }

    public final void setComponents$org_jetbrains_kotlin_tree(FirRendererComponents firRendererComponents) {
        firRendererComponents.getClass();
        this.components = firRendererComponents;
    }
}
