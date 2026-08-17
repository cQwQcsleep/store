package defpackage;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ew {
    public static final ew a = new ew();

    public final boolean a(String str, String str2) {
        Object objM38constructorimpl;
        str.getClass();
        str2.getClass();
        String strReplace$default = StringsKt.replace$default(StringsKt.trim(str2).toString(), '\\', '/', false, 4, (Object) null);
        if (strReplace$default.length() == 0) {
            return true;
        }
        if (strReplace$default.length() > 120) {
            return false;
        }
        String strTrim = StringsKt.trim(StringsKt.replace$default(StringsKt.trim(str).toString(), '\\', '/', false, 4, (Object) null), new char[]{'/'});
        try {
            Result.Companion companion = Result.INSTANCE;
            objM38constructorimpl = Result.m38constructorimpl(b(strReplace$default));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM38constructorimpl = Result.m38constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m44isFailureimpl(objM38constructorimpl)) {
            objM38constructorimpl = null;
        }
        Regex regex = (Regex) objM38constructorimpl;
        if (regex == null) {
            return false;
        }
        return regex.matches(strTrim);
    }

    public final Regex b(String str) {
        str.getClass();
        String strTrim = StringsKt.trim(StringsKt.replace$default(StringsKt.trim(str).toString(), '\\', '/', false, 4, (Object) null), new char[]{'/'});
        StringBuilder sb = new StringBuilder("^");
        int i = 0;
        while (i < strTrim.length()) {
            char cCharAt = strTrim.charAt(i);
            if (cCharAt != '*') {
                if (cCharAt != '?') {
                    if (StringsKt.contains$default("\\.[]{}()+-^$|", cCharAt, false, 2, (Object) null)) {
                        sb.append('\\');
                    }
                    sb.append(cCharAt);
                } else {
                    sb.append("[^/]");
                }
                i++;
            } else {
                int i2 = i + 1;
                if (i2 >= strTrim.length() || strTrim.charAt(i2) != '*') {
                    sb.append("[^/]*");
                    i = i2;
                } else {
                    int i3 = i + 2;
                    i = (i3 >= strTrim.length() || strTrim.charAt(i3) != '/') ? i3 : i + 3;
                    sb.append(".*");
                }
            }
        }
        sb.append(Typography.dollar);
        return new Regex(sb.toString(), RegexOption.IGNORE_CASE);
    }
}
