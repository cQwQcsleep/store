package com.android.tools.r8.naming;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.internal.AbstractC1103at;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class y0 implements StringConsumer {
    public static a a(String str) {
        int i = -1;
        while (true) {
            int i2 = i + 1;
            int iIndexOf = str.indexOf(10, i2);
            if (iIndexOf < 0) {
                return a.b("Failure to find map hash");
            }
            String strTrim = str.substring(i2, iIndexOf).trim();
            if (!strTrim.isEmpty()) {
                if (strTrim.charAt(0) != '#') {
                    return a.b("Failure to find map hash in header");
                }
                String strTrim2 = strTrim.substring(1).trim();
                if (strTrim2.startsWith("pg_map_hash")) {
                    int iIndexOf2 = strTrim2.indexOf("SHA-256 ", 11);
                    if (iIndexOf2 < 0) {
                        return a.a("Unknown map hash function: '" + strTrim2 + "'");
                    }
                    String strTrim3 = strTrim2.substring(iIndexOf2 + 7).trim();
                    com.android.tools.r8.internal.E e = (com.android.tools.r8.internal.E) AbstractC1103at.a().a();
                    e.a(str.substring(iIndexOf + 1), StandardCharsets.UTF_8);
                    String string = e.a().toString();
                    if (strTrim3.equals(string)) {
                        return a.a();
                    }
                    return a.a("Mismatching map hash: '" + strTrim3 + "' != '" + string + "'");
                }
            }
            i = iIndexOf;
        }
    }

    @Override // com.android.tools.r8.StringConsumer
    public final void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        throw null;
    }

    @Override // com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        throw null;
    }

    public static class a {
        public static final /* synthetic */ boolean c = true;
        public final boolean a;
        public final String b;

        public a(String str, boolean z) {
            this.a = z;
            this.b = str;
        }

        public static a a() {
            return new a(null, false);
        }

        public boolean b() {
            return !this.a && this.b == null;
        }

        public static a a(String str) {
            return new a(str, true);
        }

        public static a b(String str) {
            return new a(str, false);
        }
    }
}
