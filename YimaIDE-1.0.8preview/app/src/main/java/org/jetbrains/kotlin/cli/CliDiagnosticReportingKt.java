package org.jetbrains.kotlin.cli;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u001e\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u001e\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u001e\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\f"}, d2 = {"report", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "message", Argument.Delimiters.none, "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "reportInfo", "reportLog", "reportOutput", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliDiagnosticReportingKt {
    public static final void report(final CompilerConfiguration compilerConfiguration, KtSourcelessDiagnosticFactory ktSourcelessDiagnosticFactory, String str, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        compilerConfiguration.getClass();
        ktSourcelessDiagnosticFactory.getClass();
        str.getClass();
        KtDiagnosticReportHelpersKt.report(new DiagnosticContext() { // from class: org.jetbrains.kotlin.cli.CliDiagnosticReportingKt$report$context$1
            @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext
            public KtSourceFile getContainingFile() {
                return null;
            }

            @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext, org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext
            public LanguageVersionSettings getLanguageVersionSettings() {
                return CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration);
            }

            @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext
            public boolean isDiagnosticSuppressed(KtDiagnostic diagnostic) {
                diagnostic.getClass();
                return false;
            }
        }, CLIConfigurationKeysKt.getDiagnosticsCollector(compilerConfiguration), ktSourcelessDiagnosticFactory, str, compilerMessageSourceLocation);
    }

    public static /* synthetic */ void report$default(CompilerConfiguration compilerConfiguration, KtSourcelessDiagnosticFactory ktSourcelessDiagnosticFactory, String str, CompilerMessageSourceLocation compilerMessageSourceLocation, int i, Object obj) {
        if ((i & 4) != 0) {
            compilerMessageSourceLocation = null;
        }
        report(compilerConfiguration, ktSourcelessDiagnosticFactory, str, compilerMessageSourceLocation);
    }

    public static final void reportInfo(CompilerConfiguration compilerConfiguration, String str, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        compilerConfiguration.getClass();
        str.getClass();
        CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration).report(CompilerMessageSeverity.INFO, str, compilerMessageSourceLocation);
    }

    public static /* synthetic */ void reportInfo$default(CompilerConfiguration compilerConfiguration, String str, CompilerMessageSourceLocation compilerMessageSourceLocation, int i, Object obj) {
        if ((i & 2) != 0) {
            compilerMessageSourceLocation = null;
        }
        reportInfo(compilerConfiguration, str, compilerMessageSourceLocation);
    }

    public static final void reportLog(CompilerConfiguration compilerConfiguration, String str, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        compilerConfiguration.getClass();
        str.getClass();
        CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration).report(CompilerMessageSeverity.LOGGING, str, compilerMessageSourceLocation);
    }

    public static /* synthetic */ void reportLog$default(CompilerConfiguration compilerConfiguration, String str, CompilerMessageSourceLocation compilerMessageSourceLocation, int i, Object obj) {
        if ((i & 2) != 0) {
            compilerMessageSourceLocation = null;
        }
        reportLog(compilerConfiguration, str, compilerMessageSourceLocation);
    }

    public static final void reportOutput(CompilerConfiguration compilerConfiguration, String str, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        compilerConfiguration.getClass();
        str.getClass();
        CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration).report(CompilerMessageSeverity.OUTPUT, str, compilerMessageSourceLocation);
    }

    public static /* synthetic */ void reportOutput$default(CompilerConfiguration compilerConfiguration, String str, CompilerMessageSourceLocation compilerMessageSourceLocation, int i, Object obj) {
        if ((i & 2) != 0) {
            compilerMessageSourceLocation = null;
        }
        reportOutput(compilerConfiguration, str, compilerMessageSourceLocation);
    }
}
