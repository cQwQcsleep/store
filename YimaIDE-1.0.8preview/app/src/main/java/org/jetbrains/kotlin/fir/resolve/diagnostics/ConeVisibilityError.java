package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0016\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeVisibilityError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeDiagnosticWithSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "symbol", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ConeVisibilityError implements ConeDiagnosticWithSymbol<FirBasedSymbol<?>> {
    private final FirBasedSymbol<?> symbol;

    public ConeVisibilityError(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        this.symbol = firBasedSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "HIDDEN: " + ConeDiagnosticsKt.describeSymbol(getSymbol()) + " is invisible";
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithSymbol
    public FirBasedSymbol<?> getSymbol() {
        return this.symbol;
    }
}
