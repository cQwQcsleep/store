package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class nd0 {
    public static final nd0 a = new nd0();
    public static volatile String b = "0";
    public static final int c = 8;

    public final String a() {
        return b;
    }

    public final void b(Context context) {
        context.getClass();
        Context applicationContext = context.getApplicationContext();
        applicationContext.getClass();
        b = c(applicationContext);
    }

    public final String c(Context context) {
        Object obj;
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl(String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L)).getLongVersionCode()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = "0";
        }
        return (String) obj;
    }
}
