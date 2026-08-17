package org.jetbrains.kotlin.fir.scopes;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirLookupDefaultStarImportsInSourcesSettingHolder;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "value", Argument.Delimiters.none, "<init>", "(Z)V", "getValue$org_jetbrains_kotlin_providers", "()Z", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLookupDefaultStarImportsInSourcesSettingHolder implements FirSessionComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean value;

    public FirLookupDefaultStarImportsInSourcesSettingHolder(boolean z) {
        this.value = z;
    }

    /* JADX INFO: renamed from: getValue$org_jetbrains_kotlin_providers, reason: from getter */
    public final boolean getValue() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirLookupDefaultStarImportsInSourcesSettingHolder$Companion;", Argument.Delimiters.none, "<init>", "()V", "defaultSetting", Argument.Delimiters.none, "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "createDefault", "Lorg/jetbrains/kotlin/fir/scopes/FirLookupDefaultStarImportsInSourcesSettingHolder;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirLookupDefaultStarImportsInSourcesSettingHolder createDefault(LanguageVersionSettings languageVersionSettings) {
            languageVersionSettings.getClass();
            return new FirLookupDefaultStarImportsInSourcesSettingHolder(defaultSetting(languageVersionSettings));
        }

        public final boolean defaultSetting(LanguageVersionSettings languageVersionSettings) {
            languageVersionSettings.getClass();
            return ((Boolean) languageVersionSettings.getFlag(AnalysisFlags.getAllowKotlinPackage())).booleanValue();
        }

        private Companion() {
        }
    }
}
