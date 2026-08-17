package org.jetbrains.kotlin.fir.declarations.builder;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.declarations.FirConstructedClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.impl.FirConstructedClassTypeParameterRefImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fÊ\u0001\u0002\b\u0013¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirConstructedClassTypeParameterRefBuilder;", Argument.Delimiters.none, "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;)V", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructedClassTypeParameterRef;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConstructedClassTypeParameterRefBuilder {
    private KtSourceElement source;
    public FirTypeParameterSymbol symbol;

    public final FirConstructedClassTypeParameterRef build() {
        return new FirConstructedClassTypeParameterRefImpl(this.source, getSymbol());
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirTypeParameterSymbol getSymbol() throws UninitializedPropertyAccessException {
        FirTypeParameterSymbol firTypeParameterSymbol = this.symbol;
        if (firTypeParameterSymbol != null) {
            return firTypeParameterSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symbol");
        return null;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    public final void setSymbol(FirTypeParameterSymbol firTypeParameterSymbol) {
        firTypeParameterSymbol.getClass();
        this.symbol = firTypeParameterSymbol;
    }
}
