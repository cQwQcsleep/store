package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.LanguageSettingsParserKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a\u0006\u0010\u0000\u001a\u00020\u0001\"\u001b\u0010\u0002\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"allowTestsOnlyLanguageFeatures", Argument.Delimiters.none, "areTestOnlyLanguageFeaturesAllowed", Argument.Delimiters.none, "getAreTestOnlyLanguageFeaturesAllowed", "()Z", "areTestOnlyLanguageFeaturesAllowed$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LanguageSettingsParserKt {
    private static final Lazy areTestOnlyLanguageFeaturesAllowed$delegate = LazyKt.lazy(new Function0() { // from class: qo8
        public final Object invoke() {
            return Boolean.valueOf(LanguageSettingsParserKt.a());
        }
    });

    public static boolean a() {
        String property = System.getProperty("kotlinc.test.allow.testonly.language.features");
        return property != null && Boolean.parseBoolean(property);
    }

    public static final void allowTestsOnlyLanguageFeatures() {
        System.setProperty("kotlinc.test.allow.testonly.language.features", "true");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getAreTestOnlyLanguageFeaturesAllowed() {
        return ((Boolean) areTestOnlyLanguageFeaturesAllowed$delegate.getValue()).booleanValue();
    }
}
