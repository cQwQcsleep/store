package org.jetbrains.kotlin.fir.lightTree;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtOffsetsOnlySourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.builder.FirSyntaxErrors;
import org.jetbrains.kotlin.fir.lightTree.LightTreeParsingErrorListenerKt;
import org.jetbrains.kotlin.parsing.KotlinLightParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"toKotlinParsingErrorListener", "Lorg/jetbrains/kotlin/parsing/KotlinLightParser$LightTreeParsingErrorListener;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "sourceFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LightTreeParsingErrorListenerKt {
    public static void a(DiagnosticReporter diagnosticReporter, LightTreeParsingErrorListenerKt$toKotlinParsingErrorListener$diagnosticContext$1 lightTreeParsingErrorListenerKt$toKotlinParsingErrorListener$diagnosticContext$1, int i, int i2, String str) {
        KtOffsetsOnlySourceElement ktOffsetsOnlySourceElement = new KtOffsetsOnlySourceElement(i, i2);
        KtDiagnosticFactory1<String> syntax = FirSyntaxErrors.INSTANCE.getSYNTAX();
        if (str == null) {
            str = Argument.Delimiters.none;
        }
        KtDiagnosticReportHelpersKt.reportOn$default(diagnosticReporter, ktOffsetsOnlySourceElement, syntax, str, lightTreeParsingErrorListenerKt$toKotlinParsingErrorListener$diagnosticContext$1, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.jetbrains.kotlin.fir.lightTree.LightTreeParsingErrorListenerKt$toKotlinParsingErrorListener$diagnosticContext$1] */
    public static final KotlinLightParser.LightTreeParsingErrorListener toKotlinParsingErrorListener(final DiagnosticReporter diagnosticReporter, final KtSourceFile ktSourceFile, final LanguageVersionSettings languageVersionSettings) {
        diagnosticReporter.getClass();
        ktSourceFile.getClass();
        languageVersionSettings.getClass();
        final ?? r0 = new DiagnosticContext(ktSourceFile, languageVersionSettings) { // from class: org.jetbrains.kotlin.fir.lightTree.LightTreeParsingErrorListenerKt$toKotlinParsingErrorListener$diagnosticContext$1
            final /* synthetic */ LanguageVersionSettings $languageVersionSettings;
            private final KtSourceFile containingFile;

            {
                this.$languageVersionSettings = languageVersionSettings;
                this.containingFile = ktSourceFile;
            }

            @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext
            public KtSourceFile getContainingFile() {
                return this.containingFile;
            }

            @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext, org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext
            /* JADX INFO: renamed from: getLanguageVersionSettings, reason: from getter */
            public LanguageVersionSettings get$languageVersionSettings() {
                return this.$languageVersionSettings;
            }

            @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext
            public boolean isDiagnosticSuppressed(KtDiagnostic diagnostic) {
                diagnostic.getClass();
                return false;
            }
        };
        return new KotlinLightParser.LightTreeParsingErrorListener() { // from class: z09
            public final void onError(int i, int i2, String str) {
                LightTreeParsingErrorListenerKt.a(diagnosticReporter, r0, i, i2, str);
            }
        };
    }
}
