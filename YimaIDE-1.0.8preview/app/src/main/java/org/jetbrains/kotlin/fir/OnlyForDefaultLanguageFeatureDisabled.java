package org.jetbrains.kotlin.fir;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Retention(RetentionPolicy.RUNTIME)
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004Ê\u0001\u0002\b\u0006¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/fir/OnlyForDefaultLanguageFeatureDisabled;", Argument.Delimiters.none, "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "org.jetbrains.kotlin:tree", "Lkotlin/RequiresOptIn;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public @interface OnlyForDefaultLanguageFeatureDisabled {
    LanguageFeature languageFeature();
}
