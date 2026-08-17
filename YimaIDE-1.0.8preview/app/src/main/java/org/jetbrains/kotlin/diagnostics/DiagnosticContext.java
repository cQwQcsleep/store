package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0012J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "containingFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "getContainingFile", "()Lorg/jetbrains/kotlin/KtSourceFile;", "containingFilePath", Argument.Delimiters.none, "getContainingFilePath", "()Ljava/lang/String;", "isDiagnosticSuppressed", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "Default", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface DiagnosticContext extends DiagnosticBaseContext {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext$Default;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "<init>", "()V", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "containingFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "getContainingFile", "()Lorg/jetbrains/kotlin/KtSourceFile;", "isDiagnosticSuppressed", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default implements DiagnosticContext {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext
        public KtSourceFile getContainingFile() {
            return null;
        }

        @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext, org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext
        public LanguageVersionSettings getLanguageVersionSettings() {
            return LanguageVersionSettingsImpl.DEFAULT;
        }

        @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext
        public boolean isDiagnosticSuppressed(KtDiagnostic diagnostic) {
            diagnostic.getClass();
            return false;
        }
    }

    KtSourceFile getContainingFile();

    default String getContainingFilePath() {
        KtSourceFile containingFile = getContainingFile();
        if (containingFile != null) {
            return containingFile.getPath();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext
    LanguageVersionSettings getLanguageVersionSettings();

    boolean isDiagnosticSuppressed(KtDiagnostic diagnostic);
}
