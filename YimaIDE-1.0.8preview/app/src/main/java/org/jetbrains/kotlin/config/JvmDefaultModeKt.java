package org.jetbrains.kotlin.config;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"jvmDefaultMode", "Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getJvmDefaultMode", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "org.jetbrains.kotlin:config.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmDefaultModeKt {
    public static final JvmDefaultMode getJvmDefaultMode(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        JvmDefaultMode jvmDefaultMode = (JvmDefaultMode) languageVersionSettings.getFlag(JvmAnalysisFlags.getJvmDefaultMode());
        if (jvmDefaultMode == null) {
            return languageVersionSettings.supportsFeature(LanguageFeature.JvmDefaultEnableByDefault) ? JvmDefaultMode.ENABLE : JvmDefaultMode.DISABLE;
        }
        return jvmDefaultMode;
    }
}
