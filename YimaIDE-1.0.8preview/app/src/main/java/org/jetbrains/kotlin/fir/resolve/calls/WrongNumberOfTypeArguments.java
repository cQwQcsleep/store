package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/WrongNumberOfTypeArguments;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "desiredCount", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "<init>", "(ILorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "getDesiredCount", "()I", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WrongNumberOfTypeArguments extends ResolutionDiagnostic {
    private final int desiredCount;
    private final FirBasedSymbol<?> symbol;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WrongNumberOfTypeArguments(int i, FirBasedSymbol<?> firBasedSymbol) {
        super(CandidateApplicability.INAPPLICABLE);
        firBasedSymbol.getClass();
        this.desiredCount = i;
        this.symbol = firBasedSymbol;
    }

    public final int getDesiredCount() {
        return this.desiredCount;
    }

    public final FirBasedSymbol<?> getSymbol() {
        return this.symbol;
    }
}
