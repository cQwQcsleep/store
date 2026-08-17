package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirLanguageSettingsComponent;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "isMetadataCompilation", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Z)V", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "()Z", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLanguageSettingsComponent implements FirSessionComponent {
    private final boolean isMetadataCompilation;
    private final LanguageVersionSettings languageVersionSettings;

    public FirLanguageSettingsComponent(LanguageVersionSettings languageVersionSettings, boolean z) {
        languageVersionSettings.getClass();
        this.languageVersionSettings = languageVersionSettings;
        this.isMetadataCompilation = z;
    }

    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    /* JADX INFO: renamed from: isMetadataCompilation, reason: from getter */
    public final boolean getIsMetadataCompilation() {
        return this.isMetadataCompilation;
    }
}
