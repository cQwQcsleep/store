package org.jetbrains.kotlin.fir.analysis.js.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformDiagnosticSuppressor;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsPlatformDiagnosticSuppressor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformDiagnosticSuppressor;", "<init>", "()V", "shouldReportNoBody", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Z", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsPlatformDiagnosticSuppressor implements FirPlatformDiagnosticSuppressor {
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformDiagnosticSuppressor
    public boolean shouldReportNoBody(CheckerContext checkerContext, FirCallableDeclaration firCallableDeclaration) {
        checkerContext.getClass();
        firCallableDeclaration.getClass();
        return !FirJsPlatformDiagnosticSuppressorKt.isLexicallyInsideJsNative(checkerContext, firCallableDeclaration);
    }
}
