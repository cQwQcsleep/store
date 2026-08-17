package org.jetbrains.kotlin.analyzer.common;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlag;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0015\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u0007H\u0096\u0001J(\u0010\b\u001a\u0002H\t\"\n\b\u0000\u0010\t*\u0004\u0018\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0096\u0001¢\u0006\u0002\u0010\rJ\t\u0010\u000e\u001a\u00020\u000fH\u0096\u0001J\u0011\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0096\u0001R\u0012\u0010\u0011\u001a\u00020\u0012X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0012\u0010\u0015\u001a\u00020\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"org/jetbrains/kotlin/analyzer/common/CommonResolverForModuleFactory$Companion$analyzeFiles$multiplatformLanguageSettings$1", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getFeatureSupport", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "getCustomizedLanguageFeatures", Argument.Delimiters.none, "getFlag", "T", Argument.Delimiters.none, "flag", "Lorg/jetbrains/kotlin/config/AnalysisFlag;", "(Lorg/jetbrains/kotlin/config/AnalysisFlag;)Ljava/lang/Object;", "isPreRelease", Argument.Delimiters.none, "supportsFeature", "apiVersion", "Lorg/jetbrains/kotlin/config/ApiVersion;", "getApiVersion", "()Lorg/jetbrains/kotlin/config/ApiVersion;", "languageVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "getLanguageVersion", "()Lorg/jetbrains/kotlin/config/LanguageVersion;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonResolverForModuleFactory$Companion$analyzeFiles$multiplatformLanguageSettings$1 implements LanguageVersionSettings {
    private final /* synthetic */ LanguageVersionSettings $$delegate_0;
    final /* synthetic */ LanguageVersionSettings $languageVersionSettings;

    public CommonResolverForModuleFactory$Companion$analyzeFiles$multiplatformLanguageSettings$1(LanguageVersionSettings languageVersionSettings) {
        this.$languageVersionSettings = languageVersionSettings;
        this.$$delegate_0 = languageVersionSettings;
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public ApiVersion getApiVersion() {
        return this.$$delegate_0.getApiVersion();
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public Map<LanguageFeature, LanguageFeature.State> getCustomizedLanguageFeatures() {
        return this.$$delegate_0.getCustomizedLanguageFeatures();
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public LanguageFeature.State getFeatureSupport(LanguageFeature feature) {
        feature.getClass();
        return feature == LanguageFeature.MultiPlatformProjects ? LanguageFeature.State.ENABLED : this.$languageVersionSettings.getFeatureSupport(feature);
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public <T> T getFlag(AnalysisFlag<? extends T> flag) {
        flag.getClass();
        return (T) this.$$delegate_0.getFlag(flag);
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public LanguageVersion getLanguageVersion() {
        return this.$$delegate_0.getLanguageVersion();
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public boolean isPreRelease() {
        return this.$$delegate_0.isPreRelease();
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public boolean supportsFeature(LanguageFeature feature) {
        feature.getClass();
        return this.$$delegate_0.supportsFeature(feature);
    }
}
