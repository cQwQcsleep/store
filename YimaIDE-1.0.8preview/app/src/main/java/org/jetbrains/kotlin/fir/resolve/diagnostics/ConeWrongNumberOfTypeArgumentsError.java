package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnosticWithSource;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeWrongNumberOfTypeArgumentsError;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnosticWithSource;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnmatchedTypeArgumentsError;", "desiredCount", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isDeprecationErrorForCallableReferenceLHS", Argument.Delimiters.none, "<init>", "(ILorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/KtSourceElement;Z)V", "getDesiredCount", "()I", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "()Z", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeWrongNumberOfTypeArgumentsError extends ConeDiagnosticWithSource implements ConeUnmatchedTypeArgumentsError {
    private final int desiredCount;
    private final boolean isDeprecationErrorForCallableReferenceLHS;
    private final FirClassLikeSymbol<?> symbol;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeWrongNumberOfTypeArgumentsError(int i, FirClassLikeSymbol<?> firClassLikeSymbol, KtSourceElement ktSourceElement, boolean z) {
        super(ktSourceElement);
        firClassLikeSymbol.getClass();
        ktSourceElement.getClass();
        this.desiredCount = i;
        this.symbol = firClassLikeSymbol;
        this.isDeprecationErrorForCallableReferenceLHS = z;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnmatchedTypeArgumentsError
    public int getDesiredCount() {
        return this.desiredCount;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Wrong number of type arguments";
    }

    /* JADX INFO: renamed from: isDeprecationErrorForCallableReferenceLHS, reason: from getter */
    public final boolean getIsDeprecationErrorForCallableReferenceLHS() {
        return this.isDeprecationErrorForCallableReferenceLHS;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithSymbol
    public FirClassLikeSymbol<?> getSymbol() {
        return this.symbol;
    }

    public /* synthetic */ ConeWrongNumberOfTypeArgumentsError(int i, FirClassLikeSymbol firClassLikeSymbol, KtSourceElement ktSourceElement, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, firClassLikeSymbol, ktSourceElement, (i2 & 8) != 0 ? false : z);
    }
}
