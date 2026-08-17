package org.jetbrains.kotlin.contracts.parsing;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.psi.KtElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0010J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u000fH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;", Argument.Delimiters.none, "unsupportedFeature", Argument.Delimiters.none, "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "contractNotAllowed", "message", Argument.Delimiters.none, "badDescription", "reportOn", "Lorg/jetbrains/kotlin/psi/KtElement;", "addFallbackErrorIfNecessary", "flushDiagnostics", "hasErrors", Argument.Delimiters.none, "EMPTY", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ContractParsingDiagnosticsCollector {

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0005H\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector$EMPTY;", "Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;", "<init>", "()V", "contractNotAllowed", Argument.Delimiters.none, "message", Argument.Delimiters.none, "badDescription", "reportOn", "Lorg/jetbrains/kotlin/psi/KtElement;", "unsupportedFeature", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "addFallbackErrorIfNecessary", "flushDiagnostics", "hasErrors", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class EMPTY implements ContractParsingDiagnosticsCollector {
        public static final EMPTY INSTANCE = new EMPTY();

        private EMPTY() {
        }

        @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
        public void addFallbackErrorIfNecessary() {
        }

        @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
        public void badDescription(String message, KtElement reportOn) {
            message.getClass();
            reportOn.getClass();
        }

        @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
        public void contractNotAllowed(String message) {
            message.getClass();
        }

        @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
        public void flushDiagnostics() {
        }

        @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
        public boolean hasErrors() {
            return false;
        }

        @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
        public void unsupportedFeature(LanguageVersionSettings languageVersionSettings) {
            languageVersionSettings.getClass();
        }
    }

    void addFallbackErrorIfNecessary();

    void badDescription(String message, KtElement reportOn);

    void contractNotAllowed(String message);

    void flushDiagnostics();

    boolean hasErrors();

    void unsupportedFeature(LanguageVersionSettings languageVersionSettings);
}
