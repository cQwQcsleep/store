package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B%\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeDeprecated;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeDiagnosticWithSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "symbol", "deprecationInfo", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getDeprecationInfo", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeDeprecated implements ConeDiagnosticWithSymbol<FirBasedSymbol<?>> {
    private final FirDeprecationInfo deprecationInfo;
    private final KtSourceElement source;
    private final FirBasedSymbol<?> symbol;

    public ConeDeprecated(KtSourceElement ktSourceElement, FirBasedSymbol<?> firBasedSymbol, FirDeprecationInfo firDeprecationInfo) {
        firBasedSymbol.getClass();
        firDeprecationInfo.getClass();
        this.source = ktSourceElement;
        this.symbol = firBasedSymbol;
        this.deprecationInfo = firDeprecationInfo;
    }

    public final FirDeprecationInfo getDeprecationInfo() {
        return this.deprecationInfo;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Deprecated: " + this.deprecationInfo.getDeprecationLevel();
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithSymbol
    public FirBasedSymbol<?> getSymbol() {
        return this.symbol;
    }
}
