package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlag;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u0004R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005\u001a!\u0010\u0006\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0007R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u001b\u0010\t\u001a\u00020\u0001*\u00020\u0004R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005\u001a\u0017\u0010\n\u001a\u00020\u0001R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b\u001a\u0017\u0010\f\u001a\u00020\u0001R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b\u001a\u0017\u0010\r\u001a\u00020\u0001R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b\u001a\u0017\u0010\u000e\u001a\u00020\u0001R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b\u001a\u0017\u0010\u000f\u001a\u00020\u0001R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b¨\u0006\u0010"}, d2 = {"isEnabled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionHolder;", "c", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/config/LanguageFeature;)Z", "isSet", "Lorg/jetbrains/kotlin/config/AnalysisFlag;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/config/AnalysisFlag;)Z", "isDisabled", "enableWarningsForIdentitySensitiveOperationsOnValueClassesAndPrimitives", "(Lorg/jetbrains/kotlin/fir/SessionHolder;)Z", "enableWarningsForValueBasedJavaClasses", "disableWarningsForValueBasedJavaClasses", "enableCompatibilityModeForNewInference", "disableCompatibilityModeForNewInference", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LanguageVersionUtilsKt {
    public static final boolean disableCompatibilityModeForNewInference(SessionHolder sessionHolder) {
        sessionHolder.getClass();
        return isEnabled(sessionHolder, LanguageFeature.DisableCompatibilityModeForNewInference);
    }

    public static final boolean disableWarningsForValueBasedJavaClasses(SessionHolder sessionHolder) {
        sessionHolder.getClass();
        return isEnabled(sessionHolder, LanguageFeature.DisableWarningsForValueBasedJavaClasses);
    }

    public static final boolean enableCompatibilityModeForNewInference(SessionHolder sessionHolder) {
        sessionHolder.getClass();
        return isDisabled(sessionHolder, LanguageFeature.DisableCompatibilityModeForNewInference);
    }

    public static final boolean enableWarningsForIdentitySensitiveOperationsOnValueClassesAndPrimitives(SessionHolder sessionHolder) {
        sessionHolder.getClass();
        return isDisabled(sessionHolder, LanguageFeature.DisableWarningsForIdentitySensitiveOperationsOnValueClassesAndPrimitives);
    }

    public static final boolean enableWarningsForValueBasedJavaClasses(SessionHolder sessionHolder) {
        sessionHolder.getClass();
        return isDisabled(sessionHolder, LanguageFeature.DisableWarningsForValueBasedJavaClasses);
    }

    public static final boolean isDisabled(SessionHolder sessionHolder, LanguageFeature languageFeature) {
        sessionHolder.getClass();
        languageFeature.getClass();
        return !isEnabled(sessionHolder, languageFeature);
    }

    public static final boolean isEnabled(SessionHolder sessionHolder, LanguageFeature languageFeature) {
        sessionHolder.getClass();
        languageFeature.getClass();
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(sessionHolder.getSession()).supportsFeature(languageFeature);
    }

    public static final boolean isSet(SessionHolder sessionHolder, AnalysisFlag<Boolean> analysisFlag) {
        sessionHolder.getClass();
        analysisFlag.getClass();
        return ((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(sessionHolder.getSession()).getFlag(analysisFlag)).booleanValue();
    }
}
