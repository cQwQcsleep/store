package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.LanguageVersionSettings;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"supportsComposeFeature", "", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "feature", "Landroidx/compose/compiler/plugins/kotlin/ComposeLanguageFeature;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ComposeLanguageFeaturesKt {
    public static final boolean supportsComposeFeature(LanguageVersionSettings languageVersionSettings, ComposeLanguageFeature composeLanguageFeature) {
        languageVersionSettings.getClass();
        composeLanguageFeature.getClass();
        return languageVersionSettings.getLanguageVersion().compareTo(composeLanguageFeature.getSince()) >= 0;
    }
}
