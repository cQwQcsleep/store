package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnosticWithSource;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeNestedClassAccessedViaInstanceReference;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnosticWithSource;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeNestedClassAccessedViaInstanceReference extends ConeDiagnosticWithSource {
    private final FirClassLikeSymbol<?> symbol;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeNestedClassAccessedViaInstanceReference(KtSourceElement ktSourceElement, FirClassLikeSymbol<?> firClassLikeSymbol) {
        super(ktSourceElement);
        ktSourceElement.getClass();
        firClassLikeSymbol.getClass();
        this.symbol = firClassLikeSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Nested " + this.symbol.getClassId() + " accessed via instance reference";
    }

    public final FirClassLikeSymbol<?> getSymbol() {
        return this.symbol;
    }
}
