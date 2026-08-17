package org.jetbrains.kotlin.config;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\u0004\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\t\u001a\u0010\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b*\u00020\u0005\u001a\u0010\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000b*\u00020\u0005\u001a\u0012\u0010\r\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"NO_ISSUE_SPECIFIED", Argument.Delimiters.none, "toKotlinVersion", "Lkotlin/KotlinVersion;", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "isPreRelease", Argument.Delimiters.none, "forcesPreReleaseBinariesIfEnabled", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "getCustomizedEffectivelyEnabledLanguageFeatures", Argument.Delimiters.none, "getCustomizedEffectivelyDisabledLanguageFeatures", "isEnabledByDefault", "languageFeature", "org.jetbrains.kotlin:language.version-settings"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LanguageVersionSettingsKt {
    public static final String NO_ISSUE_SPECIFIED = "No YT issue";

    public static final boolean forcesPreReleaseBinariesIfEnabled(LanguageFeature languageFeature) {
        languageFeature.getClass();
        LanguageVersion sinceVersion = languageFeature.getSinceVersion();
        return !(sinceVersion != null && sinceVersion.isStable()) && languageFeature.getForcesPreReleaseBinaries();
    }

    public static final Set<LanguageFeature> getCustomizedEffectivelyDisabledLanguageFeatures(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        Set<Map.Entry<LanguageFeature, LanguageFeature.State>> setEntrySet = languageVersionSettings.getCustomizedLanguageFeatures().entrySet();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            LanguageFeature languageFeature = (LanguageFeature) entry.getKey();
            LanguageFeature.State state = (LanguageFeature.State) entry.getValue();
            if (!isEnabledByDefault(languageVersionSettings, languageFeature) || state != LanguageFeature.State.DISABLED) {
                languageFeature = null;
            }
            if (languageFeature != null) {
                linkedHashSet.add(languageFeature);
            }
        }
        return linkedHashSet;
    }

    public static final Set<LanguageFeature> getCustomizedEffectivelyEnabledLanguageFeatures(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        Set<Map.Entry<LanguageFeature, LanguageFeature.State>> setEntrySet = languageVersionSettings.getCustomizedLanguageFeatures().entrySet();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            LanguageFeature languageFeature = (LanguageFeature) entry.getKey();
            LanguageFeature.State state = (LanguageFeature.State) entry.getValue();
            if (isEnabledByDefault(languageVersionSettings, languageFeature) || state != LanguageFeature.State.ENABLED) {
                languageFeature = null;
            }
            if (languageFeature != null) {
                linkedHashSet.add(languageFeature);
            }
        }
        return linkedHashSet;
    }

    public static final boolean isEnabledByDefault(LanguageVersionSettings languageVersionSettings, LanguageFeature languageFeature) {
        languageVersionSettings.getClass();
        languageFeature.getClass();
        return languageFeature.getSinceVersion() != null && languageVersionSettings.getLanguageVersion().compareTo(languageFeature.getSinceVersion()) >= 0 && languageVersionSettings.getApiVersion().compareTo(languageFeature.getSinceApiVersion()) >= 0;
    }

    public static final boolean isPreRelease(LanguageVersion languageVersion) {
        languageVersion.getClass();
        if (languageVersion.isStable()) {
            return KotlinCompilerVersion.isPreRelease() && languageVersion == LanguageVersion.LATEST_STABLE;
        }
        return true;
    }

    public static final KotlinVersion toKotlinVersion(LanguageVersion languageVersion) {
        languageVersion.getClass();
        return new KotlinVersion(languageVersion.getMajor(), languageVersion.getMinor());
    }

    public static final KotlinVersion toKotlinVersion(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        return toKotlinVersion(languageVersionSettings.getLanguageVersion());
    }
}
