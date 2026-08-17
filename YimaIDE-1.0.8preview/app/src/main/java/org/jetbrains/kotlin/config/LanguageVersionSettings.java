package org.jetbrains.kotlin.config;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0014\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\tH&J\b\u0010\n\u001a\u00020\u0007H&J!\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u000eH&¢\u0006\u0002\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", Argument.Delimiters.none, "getFeatureSupport", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "supportsFeature", Argument.Delimiters.none, "getCustomizedLanguageFeatures", Argument.Delimiters.none, "isPreRelease", "getFlag", "T", "flag", "Lorg/jetbrains/kotlin/config/AnalysisFlag;", "(Lorg/jetbrains/kotlin/config/AnalysisFlag;)Ljava/lang/Object;", "apiVersion", "Lorg/jetbrains/kotlin/config/ApiVersion;", "getApiVersion", "()Lorg/jetbrains/kotlin/config/ApiVersion;", "languageVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "getLanguageVersion", "()Lorg/jetbrains/kotlin/config/LanguageVersion;", "Companion", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface LanguageVersionSettings {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final String RESOURCE_NAME_TO_ALLOW_READING_FROM_ENVIRONMENT = "META-INF/allow-configuring-from-environment";

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageVersionSettings$Companion;", Argument.Delimiters.none, "<init>", "()V", "RESOURCE_NAME_TO_ALLOW_READING_FROM_ENVIRONMENT", Argument.Delimiters.none, "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String RESOURCE_NAME_TO_ALLOW_READING_FROM_ENVIRONMENT = "META-INF/allow-configuring-from-environment";

        private Companion() {
        }
    }

    ApiVersion getApiVersion();

    Map<LanguageFeature, LanguageFeature.State> getCustomizedLanguageFeatures();

    LanguageFeature.State getFeatureSupport(LanguageFeature feature);

    <T> T getFlag(AnalysisFlag<? extends T> flag);

    LanguageVersion getLanguageVersion();

    boolean isPreRelease();

    default boolean supportsFeature(LanguageFeature feature) {
        feature.getClass();
        return getFeatureSupport(feature) == LanguageFeature.State.ENABLED;
    }
}
