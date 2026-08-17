package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class r01 {
    public static final r01 a = new r01();

    public final boolean a(Context context, String str) {
        Object objM38constructorimpl;
        context.getClass();
        str.getClass();
        try {
            Result.Companion companion = Result.INSTANCE;
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(268435456);
            context.startActivity(intent);
            objM38constructorimpl = Result.m38constructorimpl(Boolean.TRUE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM38constructorimpl = Result.m38constructorimpl(ResultKt.createFailure(th));
        }
        Boolean bool = Boolean.FALSE;
        if (Result.m44isFailureimpl(objM38constructorimpl)) {
            objM38constructorimpl = bool;
        }
        return ((Boolean) objM38constructorimpl).booleanValue();
    }
}
