package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0003\u001a\f\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"INLINE_SCOPE_NUMBER_SEPARATOR", Argument.Delimiters.none, "dropInlineScopeInfo", Argument.Delimiters.none, "getInlineScopeInfo", "Lorg/jetbrains/kotlin/codegen/inline/InlineScopeInfo;", "org.jetbrains.kotlin:backend.common.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineScopeUtilsKt {
    public static final char INLINE_SCOPE_NUMBER_SEPARATOR = '\\';

    public static final String dropInlineScopeInfo(String str) {
        str.getClass();
        return StringsKt.substringBefore$default(str, INLINE_SCOPE_NUMBER_SEPARATOR, (String) null, 2, (Object) null);
    }

    public static final InlineScopeInfo getInlineScopeInfo(String str) {
        str.getClass();
        String strSubstringAfter$default = StringsKt.substringAfter$default(str, INLINE_SCOPE_NUMBER_SEPARATOR, (String) null, 2, (Object) null);
        StringBuilder[] sbArr = {new StringBuilder(), new StringBuilder(), new StringBuilder()};
        int length = strSubstringAfter$default.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = strSubstringAfter$default.charAt(i2);
            if (cCharAt != '\\') {
                sbArr[i].append(cCharAt);
            } else {
                if (i >= 3) {
                    return null;
                }
                i++;
            }
        }
        Integer intOrNull = StringsKt.toIntOrNull(sbArr[0].toString());
        if (intOrNull != null) {
            return new InlineScopeInfo(intOrNull.intValue(), StringsKt.toIntOrNull(sbArr[1].toString()), StringsKt.toIntOrNull(sbArr[2].toString()));
        }
        return null;
    }
}
