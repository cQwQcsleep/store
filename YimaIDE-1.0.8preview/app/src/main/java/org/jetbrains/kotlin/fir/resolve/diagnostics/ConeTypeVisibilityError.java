package org.jetbrains.kotlin.fir.resolve.diagnostics;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeTypeVisibilityError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeVisibilityError;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "smallestUnresolvablePrefix", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Ljava/util/List;)V", "getSmallestUnresolvablePrefix", "()Ljava/util/List;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeVisibilityError extends ConeVisibilityError {
    private final List<FirQualifierPart> smallestUnresolvablePrefix;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ConeTypeVisibilityError(FirBasedSymbol<?> firBasedSymbol, List<? extends FirQualifierPart> list) {
        super(firBasedSymbol);
        firBasedSymbol.getClass();
        list.getClass();
        this.smallestUnresolvablePrefix = list;
    }

    public final List<FirQualifierPart> getSmallestUnresolvablePrefix() {
        return this.smallestUnresolvablePrefix;
    }
}
