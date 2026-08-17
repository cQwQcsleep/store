package org.jetbrains.kotlin.fir.builder;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/FirSyntaxErrors;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "()V", "SYNTAX", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", Argument.Delimiters.none, "getSYNTAX", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSyntaxErrors extends KtDiagnosticsContainer {
    public static final FirSyntaxErrors INSTANCE;
    private static final KtDiagnosticFactory1<String> SYNTAX;

    static {
        FirSyntaxErrors firSyntaxErrors = new FirSyntaxErrors();
        INSTANCE = firSyntaxErrors;
        SYNTAX = new KtDiagnosticFactory1<>("SYNTAX", Severity.ERROR, SourceElementPositioningStrategies.INSTANCE.getSYNTAX_ERROR(), Reflection.getOrCreateKotlinClass(PsiElement.class), firSyntaxErrors.getRendererFactory());
    }

    private FirSyntaxErrors() {
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
    public BaseDiagnosticRendererFactory getRendererFactory() {
        return FirSyntaxErrorsDefaultMessages.INSTANCE;
    }

    public final KtDiagnosticFactory1<String> getSYNTAX() {
        return SYNTAX;
    }
}
