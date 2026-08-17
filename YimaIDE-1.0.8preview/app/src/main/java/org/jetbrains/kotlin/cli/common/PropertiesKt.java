package org.jetbrains.kotlin.cli.common;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005\"\u0011\u0010\u0000\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0002¨\u0006\u0006"}, d2 = {"isWindows", Argument.Delimiters.none, "()Z", "toBooleanLenient", Argument.Delimiters.none, "(Ljava/lang/String;)Ljava/lang/Boolean;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PropertiesKt {
    public static final boolean isWindows() {
        String value = CompilerSystemProperties.OS_NAME.getValue();
        value.getClass();
        String lowerCase = value.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        return StringsKt.startsWith$default(lowerCase, "windows", false, 2, (Object) null);
    }

    public static final Boolean toBooleanLenient(String str) {
        String lowerCase;
        if (str != null) {
            lowerCase = str.toLowerCase(Locale.ROOT);
            lowerCase.getClass();
        } else {
            lowerCase = null;
        }
        if (lowerCase == null) {
            return Boolean.FALSE;
        }
        if (CollectionsKt.listOf(new String[]{Argument.Delimiters.none, "yes", "true", "on", "y"}).contains(lowerCase)) {
            return Boolean.TRUE;
        }
        if (CollectionsKt.listOf(new String[]{K2JsArgumentConstants.SOURCE_MAP_NAMES_POLICY_NO, "false", "off", "n"}).contains(lowerCase)) {
            return Boolean.FALSE;
        }
        return null;
    }
}
