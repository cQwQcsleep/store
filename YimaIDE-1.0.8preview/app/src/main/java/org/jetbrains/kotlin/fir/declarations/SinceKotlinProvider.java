package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/SinceKotlinProvider;", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationInfoProvider;", "sinceVersion", "Lorg/jetbrains/kotlin/config/ApiVersion;", "<init>", "(Lorg/jetbrains/kotlin/config/ApiVersion;)V", "computeDeprecationInfo", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SinceKotlinProvider extends DeprecationInfoProvider {
    private final ApiVersion sinceVersion;

    public SinceKotlinProvider(ApiVersion apiVersion) {
        apiVersion.getClass();
        this.sinceVersion = apiVersion;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.DeprecationInfoProvider
    public FirDeprecationInfo computeDeprecationInfo(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        if (this.sinceVersion.compareTo(languageVersionSettings.getApiVersion()) <= 0) {
            return null;
        }
        return new FutureApiDeprecationInfo(DeprecationLevelValue.HIDDEN, true, this.sinceVersion);
    }
}
