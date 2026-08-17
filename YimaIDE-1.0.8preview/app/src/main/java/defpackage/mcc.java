package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class mcc {
    public static volatile SharedPreferences b;
    public static final mcc a = new mcc();
    public static final int c = 8;

    public final fcc a(Context context) {
        context.getClass();
        SharedPreferences sharedPreferencesB = b(context);
        String string = sharedPreferencesB.getString("host", "");
        String string2 = string != null ? StringsKt.trim(string).toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        String string3 = sharedPreferencesB.getString("username", "");
        String string4 = string3 != null ? StringsKt.trim(string3).toString() : null;
        if (string4 == null) {
            string4 = "";
        }
        String string5 = sharedPreferencesB.getString("password", "");
        String str = string5 != null ? string5 : "";
        if (StringsKt.isBlank(string2) || StringsKt.isBlank(string4)) {
            return null;
        }
        return new fcc(string2, RangesKt.coerceIn(sharedPreferencesB.getInt("port", 22), 1, 65535), string4, str);
    }

    public final SharedPreferences b(Context context) {
        SharedPreferences sharedPreferences = b;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        synchronized (this) {
            SharedPreferences sharedPreferences2 = b;
            if (sharedPreferences2 != null) {
                return sharedPreferences2;
            }
            MasterKey masterKeyBuild = new MasterKey.Builder(context.getApplicationContext()).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();
            masterKeyBuild.getClass();
            SharedPreferences sharedPreferencesCreate = EncryptedSharedPreferences.create(context.getApplicationContext(), "yima_remote_server", masterKeyBuild, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            sharedPreferencesCreate.getClass();
            b = sharedPreferencesCreate;
            return sharedPreferencesCreate;
        }
    }

    public final void c(Context context, fcc fccVar) {
        context.getClass();
        fccVar.getClass();
        b(context).edit().putString("host", StringsKt.trim(fccVar.a()).toString()).putInt("port", RangesKt.coerceIn(fccVar.c(), 1, 65535)).putString("username", StringsKt.trim(fccVar.d()).toString()).putString("password", fccVar.b()).apply();
    }
}
