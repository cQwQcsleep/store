package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class nj0 {
    public static volatile SharedPreferences c;
    public static final nj0 a = new nj0();
    public static final Object b = new Object();
    public static final int d = 8;

    public static final class a extends SuspendLambda implements Function2 {
        public int b;
        public final /* synthetic */ Context c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, Continuation continuation) {
            super(2, continuation);
            this.c = context;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new a(this.c, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.b == 0) {
                ResultKt.throwOnFailure(obj);
                return nj0.a.d(this.c);
            }
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public final void a(Context context) {
        context.getClass();
        try {
            Result.Companion companion = Result.Companion;
            h(context).edit().clear().apply();
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public final String b(Context context) {
        Object obj;
        context.getClass();
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl(h(context).getString("access_token", null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = null;
        }
        String str = (String) obj;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        return str;
    }

    public final String c(Context context) {
        Object obj;
        context.getClass();
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl(h(context).getString("refresh_token", null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = null;
        }
        String str = (String) obj;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        return str;
    }

    public final String d(Context context) {
        context.getClass();
        String strB = b(context);
        String strA = null;
        if (strB == null) {
            return null;
        }
        if (!f(context)) {
            return strB;
        }
        synchronized (b) {
            try {
                nj0 nj0Var = a;
                String strB2 = nj0Var.b(context);
                if (strB2 == null) {
                    return null;
                }
                if (!nj0Var.f(context)) {
                    return strB2;
                }
                String strC = nj0Var.c(context);
                if (strC == null) {
                    return null;
                }
                mj0.a aVarI = mj0.a.i(strC);
                if (aVarI instanceof mj0.a.b) {
                    mj0.b bVar = (mj0.b) ((mj0.a.b) aVarI).a();
                    nj0Var.i(context, bVar.a(), bVar.c(), bVar.b());
                    strA = bVar.a();
                } else {
                    if (!(aVarI instanceof mj0.a.C0010a)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    nj0Var.a(context);
                }
                return strA;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Object e(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new a(context, null), continuation);
    }

    public final boolean f(Context context) {
        Object obj;
        context.getClass();
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl(Long.valueOf(h(context).getLong("access_expire_at", 0L)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = 0L;
        }
        long jLongValue = ((Number) obj).longValue();
        return jLongValue <= 0 || System.currentTimeMillis() >= jLongValue - 60000;
    }

    public final boolean g(Context context) {
        context.getClass();
        if (c(context) != null) {
            return true;
        }
        if (b(context) == null) {
            return false;
        }
        return !f(context);
    }

    public final SharedPreferences h(Context context) {
        SharedPreferences sharedPreferences = c;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        synchronized (this) {
            SharedPreferences sharedPreferences2 = c;
            if (sharedPreferences2 != null) {
                return sharedPreferences2;
            }
            MasterKey masterKeyBuild = new MasterKey.Builder(context.getApplicationContext()).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();
            masterKeyBuild.getClass();
            SharedPreferences sharedPreferencesCreate = EncryptedSharedPreferences.create(context.getApplicationContext(), "yima_auth", masterKeyBuild, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            sharedPreferencesCreate.getClass();
            c = sharedPreferencesCreate;
            return sharedPreferencesCreate;
        }
    }

    public final void i(Context context, String str, String str2, long j) {
        context.getClass();
        str.getClass();
        str2.getClass();
        try {
            Result.Companion companion = Result.Companion;
            Result.constructor-impl(Boolean.valueOf(h(context).edit().putString("access_token", str).putString("refresh_token", str2).putLong("access_expire_at", System.currentTimeMillis() + (j * 1000)).commit()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }
}
