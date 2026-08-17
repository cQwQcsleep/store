package defpackage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class iy9 {
    public static final iy9 a = new iy9();
    public static final DateTimeFormatter b = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final Set c = SetsKt.setOf((Object[]) new String[]{"月度会员", "季度会员", "年度会员"});
    public static final int d = 8;

    public final String a(String str) {
        Object objM38constructorimpl;
        if (str == null) {
            return null;
        }
        if (StringsKt.isBlank(str) || Intrinsics.areEqual(str, "null")) {
            str = null;
        }
        if (str == null) {
            return null;
        }
        Instant instantC = c(str);
        if (instantC != null) {
            return instantC.atZone(ZoneId.systemDefault()).format(b);
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM38constructorimpl = Result.m38constructorimpl(LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).format(b));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM38constructorimpl = Result.m38constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m44isFailureimpl(objM38constructorimpl)) {
            objM38constructorimpl = null;
        }
        String str2 = (String) objM38constructorimpl;
        if (str2 != null) {
            return str2;
        }
        String strTake = StringsKt.take(str, 10);
        if (strTake.length() == 10) {
            return strTake;
        }
        return null;
    }

    public final String b(String str, String str2, String str3) {
        String strA;
        str.getClass();
        str2.getClass();
        if (Intrinsics.areEqual(str, "member") && c.contains(str2) && (strA = a(str3)) != null) {
            return "有效期至 ".concat(strA);
        }
        return null;
    }

    public final Instant c(String str) {
        Object objM38constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM38constructorimpl = Result.m38constructorimpl(Instant.parse(str));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM38constructorimpl = Result.m38constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m44isFailureimpl(objM38constructorimpl)) {
            objM38constructorimpl = null;
        }
        return (Instant) objM38constructorimpl;
    }
}
