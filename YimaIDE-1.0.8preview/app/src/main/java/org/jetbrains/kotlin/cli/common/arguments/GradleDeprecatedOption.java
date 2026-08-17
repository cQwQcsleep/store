package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/GradleDeprecatedOption;", Argument.Delimiters.none, "message", Argument.Delimiters.none, "removeAfter", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "level", "Lkotlin/DeprecationLevel;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/config/LanguageVersion;Lkotlin/DeprecationLevel;)V", "getMessage", "()Ljava/lang/String;", "getRemoveAfter", "()Lorg/jetbrains/kotlin/config/LanguageVersion;", "getLevel", "()Lkotlin/DeprecationLevel;", "org.jetbrains.kotlin:arguments.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class GradleDeprecatedOption {
    private final DeprecationLevel level;
    private final String message;
    private final LanguageVersion removeAfter;

    public GradleDeprecatedOption(String str, LanguageVersion languageVersion, DeprecationLevel deprecationLevel) {
        str.getClass();
        languageVersion.getClass();
        deprecationLevel.getClass();
        this.message = str;
        this.removeAfter = languageVersion;
        this.level = deprecationLevel;
    }

    public final DeprecationLevel getLevel() {
        return this.level;
    }

    public final String getMessage() {
        return this.message;
    }

    public final LanguageVersion getRemoveAfter() {
        return this.removeAfter;
    }

    public /* synthetic */ GradleDeprecatedOption(String str, LanguageVersion languageVersion, DeprecationLevel deprecationLevel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "This option has no effect and will be removed in a future release." : str, languageVersion, deprecationLevel);
    }
}
