package org.jetbrains.kotlin.fir.references.builder;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.references.FirDelegateFieldReference;
import org.jetbrains.kotlin.fir.references.impl.FirDelegateFieldReferenceImpl;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0016\u001a\u00020\u0017R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015Ê\u0001\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/references/builder/FirDelegateFieldReferenceBuilder;", Argument.Delimiters.none, "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "setResolvedSymbolOrigin", "(Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;)V", "resolvedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "getResolvedSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "setResolvedSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;)V", "build", "Lorg/jetbrains/kotlin/fir/references/FirDelegateFieldReference;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegateFieldReferenceBuilder {
    public FirDelegateFieldSymbol resolvedSymbol;
    private FirResolvedSymbolOrigin resolvedSymbolOrigin;
    private KtSourceElement source;

    public final FirDelegateFieldReference build() {
        return new FirDelegateFieldReferenceImpl(this.source, this.resolvedSymbolOrigin, getResolvedSymbol());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirDelegateFieldSymbol getResolvedSymbol() throws UninitializedPropertyAccessException {
        FirDelegateFieldSymbol firDelegateFieldSymbol = this.resolvedSymbol;
        if (firDelegateFieldSymbol != null) {
            return firDelegateFieldSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("resolvedSymbol");
        return null;
    }

    public final FirResolvedSymbolOrigin getResolvedSymbolOrigin() {
        return this.resolvedSymbolOrigin;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final void setResolvedSymbol(FirDelegateFieldSymbol firDelegateFieldSymbol) {
        firDelegateFieldSymbol.getClass();
        this.resolvedSymbol = firDelegateFieldSymbol;
    }

    public final void setResolvedSymbolOrigin(FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        this.resolvedSymbolOrigin = firResolvedSymbolOrigin;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
