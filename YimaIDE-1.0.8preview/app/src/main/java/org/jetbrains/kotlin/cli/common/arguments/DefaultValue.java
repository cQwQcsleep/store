package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0012\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/DefaultValue;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "BOOLEAN_FALSE_DEFAULT", "BOOLEAN_TRUE_DEFAULT", "BOOLEAN_NULL_DEFAULT", "STRING_NULL_DEFAULT", "EMPTY_STRING_LIST_DEFAULT", "EMPTY_STRING_ARRAY_DEFAULT", "LANGUAGE_VERSIONS", "API_VERSIONS", "JVM_TARGET_VERSIONS", "JVM_DEFAULT_MODES", "JS_ECMA_VERSIONS", "JS_MODULE_KINDS", "JS_SOURCE_MAP_CONTENT_MODES", "JS_MAIN", "JS_SOURCE_MAP_NAMES_POLICY", "org.jetbrains.kotlin:arguments.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum DefaultValue {
    BOOLEAN_FALSE_DEFAULT,
    BOOLEAN_TRUE_DEFAULT,
    BOOLEAN_NULL_DEFAULT,
    STRING_NULL_DEFAULT,
    EMPTY_STRING_LIST_DEFAULT,
    EMPTY_STRING_ARRAY_DEFAULT,
    LANGUAGE_VERSIONS,
    API_VERSIONS,
    JVM_TARGET_VERSIONS,
    JVM_DEFAULT_MODES,
    JS_ECMA_VERSIONS,
    JS_MODULE_KINDS,
    JS_SOURCE_MAP_CONTENT_MODES,
    JS_MAIN,
    JS_SOURCE_MAP_NAMES_POLICY;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<DefaultValue> getEntries() {
        return $ENTRIES;
    }
}
