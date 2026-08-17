package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0006"}, d2 = {"isLibraryToSourceAnalysisEnabled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)Z", "areExpectActualClassesStable", "getAreExpectActualClassesStable", "org.jetbrains.kotlin:config"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LanguageVersionSettingsUtilsKt {
    public static final boolean getAreExpectActualClassesStable(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        return ((Boolean) languageVersionSettings.getFlag(AnalysisFlags.getMuteExpectActualClassesWarning())).booleanValue() || languageVersionSettings.supportsFeature(LanguageFeature.ExpectActualClasses);
    }

    public static final boolean isLibraryToSourceAnalysisEnabled(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        return ((Boolean) languageVersionSettings.getFlag(AnalysisFlags.getLibraryToSourceAnalysis())).booleanValue();
    }
}
