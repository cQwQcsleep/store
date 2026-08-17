package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/InaccessibleOuterClassReceiver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InaccessibleOuterClassReceiver extends ResolutionDiagnostic {
    private final FirClassSymbol<?> symbol;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InaccessibleOuterClassReceiver(FirClassSymbol<?> firClassSymbol) {
        super(CandidateApplicability.INAPPLICABLE);
        firClassSymbol.getClass();
        this.symbol = firClassSymbol;
    }

    public final FirClassSymbol<?> getSymbol() {
        return this.symbol;
    }
}
