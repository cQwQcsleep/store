package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class kb3 {
    public static volatile Context c;
    public static volatile String e;
    public static volatile long f;
    public static volatile String j;
    public static volatile b m;
    public static final kb3 a = new kb3();
    public static final SecureRandom b = new SecureRandom();
    public static volatile String d = "";
    public static volatile long g = -1;
    public static volatile long h = -1;
    public static volatile long i = -1;
    public static final MutableStateFlow k = StateFlowKt.MutableStateFlow(Boolean.FALSE);
    public static final MutableStateFlow l = StateFlowKt.MutableStateFlow((Object) null);
    public static final MutableStateFlow n = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
    public static final int o = 8;

    public static final class a {
        public final String a;
        public final String b;
        public final long c;

        public a(String str, String str2, long j) {
            str.getClass();
            str2.getClass();
            this.a = str;
            this.b = str2;
            this.c = j;
        }

        public final String a() {
            return this.a;
        }

        public final String b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b) && this.c == aVar.c;
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Long.hashCode(this.c);
        }

        public String toString() {
            return "Crash(packageName=" + this.a + ", trace=" + this.b + ", time=" + this.c + ")";
        }
    }

    public static final class b {
        public final List a;
        public final long b;

        public b(List list, long j) {
            list.getClass();
            this.a = list;
            this.b = j;
        }

        public final List a() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return Intrinsics.areEqual(this.a, bVar.a) && this.b == bVar.b;
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + Long.hashCode(this.b);
        }

        public String toString() {
            return "Preflight(issues=" + this.a + ", time=" + this.b + ")";
        }
    }

    public static CharSequence a(byte b2) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Integer.valueOf(b2 & 255)}, 1));
    }

    public final void b(String str) {
        str.getClass();
        List mutableList = CollectionsKt.toMutableList((Collection) n.getValue());
        mutableList.add(str);
        int size = mutableList.size() - 400;
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                mutableList.remove(0);
            }
        }
        n.setValue(mutableList);
    }

    public final void c(Context context) {
        context.getClass();
        Context applicationContext = context.getApplicationContext();
        c = applicationContext;
        if (d.length() == 0) {
            String string = applicationContext.getSharedPreferences("yima_debug_bridge", 0).getString("last_run_id", null);
            if (string == null) {
                string = "";
            }
            if (string.length() > 0) {
                d = string;
            }
        }
    }

    public final void d() {
        l.setValue((Object) null);
    }

    public final void e() {
        n.setValue(CollectionsKt.emptyList());
    }

    public final void f() {
        e = null;
        g = -1L;
        h = -1L;
        i = -1L;
        j = null;
        k.setValue(Boolean.FALSE);
    }

    public final String g() {
        return d;
    }

    public final StateFlow h() {
        return n;
    }

    public final StateFlow i() {
        return l;
    }

    public final b j() {
        return m;
    }

    public final long k() {
        return h;
    }

    public final long l() {
        return g;
    }

    public final String m() {
        return j;
    }

    public final StateFlow n() {
        return k;
    }

    public final String o() {
        return e;
    }

    public final long p() {
        return f;
    }

    public final String q() {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editorEdit;
        SharedPreferences.Editor editorPutString;
        byte[] bArr = new byte[16];
        b.nextBytes(bArr);
        String strJoinToString$default = ArraysKt.joinToString$default(bArr, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: jb3
            public final Object invoke(Object obj) {
                return kb3.a(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
        d = strJoinToString$default;
        Context context = c;
        if (context != null && (sharedPreferences = context.getSharedPreferences("yima_debug_bridge", 0)) != null && (editorEdit = sharedPreferences.edit()) != null && (editorPutString = editorEdit.putString("last_run_id", strJoinToString$default)) != null) {
            editorPutString.apply();
        }
        return strJoinToString$default;
    }

    public final void r() {
        String str = j;
        k.setValue(Boolean.valueOf(str != null && new File(str).isFile()));
    }

    public final void s(String str, String str2) {
        str.getClass();
        str2.getClass();
        l.setValue(new a(str, str2, System.currentTimeMillis()));
    }

    public final void t(List list) {
        list.getClass();
        m = new b(list, System.currentTimeMillis());
    }

    public final void u(String str, long j2, long j3, long j4, File file) {
        str.getClass();
        e = str;
        f = System.currentTimeMillis();
        g = j2;
        h = j3;
        i = j4;
        String absolutePath = null;
        if (file != null) {
            if (!file.isFile()) {
                file = null;
            }
            if (file != null) {
                absolutePath = file.getAbsolutePath();
            }
        }
        j = absolutePath;
        r();
    }

    public final void v(String str) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editorEdit;
        SharedPreferences.Editor editorPutString;
        str.getClass();
        if (str.length() == 0) {
            return;
        }
        d = str;
        Context context = c;
        if (context == null || (sharedPreferences = context.getSharedPreferences("yima_debug_bridge", 0)) == null || (editorEdit = sharedPreferences.edit()) == null || (editorPutString = editorEdit.putString("last_run_id", str)) == null) {
            return;
        }
        editorPutString.apply();
    }
}
