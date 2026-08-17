package org.jetbrains.kotlin.fir.extensions;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/OriginalCallData;", Argument.Delimiters.none, "originalSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "extension", "Lorg/jetbrains/kotlin/fir/extensions/FirFunctionCallRefinementExtension;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lorg/jetbrains/kotlin/fir/extensions/FirFunctionCallRefinementExtension;)V", "getOriginalSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getExtension", "()Lorg/jetbrains/kotlin/fir/extensions/FirFunctionCallRefinementExtension;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OriginalCallData {
    private final FirFunctionCallRefinementExtension extension;
    private final FirNamedFunctionSymbol originalSymbol;

    public OriginalCallData(FirNamedFunctionSymbol firNamedFunctionSymbol, FirFunctionCallRefinementExtension firFunctionCallRefinementExtension) {
        firNamedFunctionSymbol.getClass();
        firFunctionCallRefinementExtension.getClass();
        this.originalSymbol = firNamedFunctionSymbol;
        this.extension = firFunctionCallRefinementExtension;
    }

    public final FirFunctionCallRefinementExtension getExtension() {
        return this.extension;
    }

    public final FirNamedFunctionSymbol getOriginalSymbol() {
        return this.originalSymbol;
    }
}
