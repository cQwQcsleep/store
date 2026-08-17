package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeAmbiguouslyResolvedAnnotationArgument;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "symbolFromCompilerPhase", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "symbolFromAnnotationArgumentsPhase", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "getSymbolFromCompilerPhase", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getSymbolFromAnnotationArgumentsPhase", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeAmbiguouslyResolvedAnnotationArgument implements ConeDiagnostic {
    private final FirBasedSymbol<?> symbolFromAnnotationArgumentsPhase;
    private final FirBasedSymbol<?> symbolFromCompilerPhase;

    public ConeAmbiguouslyResolvedAnnotationArgument(FirBasedSymbol<?> firBasedSymbol, FirBasedSymbol<?> firBasedSymbol2) {
        firBasedSymbol.getClass();
        this.symbolFromCompilerPhase = firBasedSymbol;
        this.symbolFromAnnotationArgumentsPhase = firBasedSymbol2;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "\n            Annotation symbol resolved differently on compiler annotation and symbols stages:\n              - compiler annotations: " + this.symbolFromCompilerPhase + "\n              - compiler arguments stage: " + this.symbolFromAnnotationArgumentsPhase + "\n        ";
    }

    public final FirBasedSymbol<?> getSymbolFromAnnotationArgumentsPhase() {
        return this.symbolFromAnnotationArgumentsPhase;
    }

    public final FirBasedSymbol<?> getSymbolFromCompilerPhase() {
        return this.symbolFromCompilerPhase;
    }
}
