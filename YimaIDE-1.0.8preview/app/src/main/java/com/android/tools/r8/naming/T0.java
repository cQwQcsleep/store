package com.android.tools.r8.naming;

import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.C0629Ku;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.Ck0;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class T0 {
    public final AbstractC0706Nu a;
    public final Set b;
    public final C2742u50 c;

    public T0(AbstractC0706Nu abstractC0706Nu, HashSet hashSet, C2742u50 c2742u50) {
        this.c = c2742u50;
        C0629Ku c0629KuE = AbstractC0706Nu.e();
        for (Map.Entry entry : abstractC0706Nu.entrySet()) {
            String str = (String) entry.getKey();
            C3327i c3327i = (C3327i) entry.getValue();
            c0629KuE.a(str, new C3329j(c3327i.b, c3327i.a, (F0) c3327i.c, c3327i.e, c3327i.f, c3327i.g));
        }
        AbstractC0706Nu abstractC0706NuB = c0629KuE.b();
        this.a = abstractC0706NuB;
        this.b = hashSet;
        HashMap map = new HashMap();
        Ck0 it = abstractC0706NuB.keySet().iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            C3329j c3329j = (C3329j) this.a.get(str2);
            String str3 = (String) map.put(c3329j.b, str2);
            if (str3 != null) {
                C2742u50 c2742u51 = this.c;
                String strB = C0929Wj.b(str2);
                String strB2 = C0929Wj.b(str3);
                String strA = C0929Wj.a(c3329j.b);
                c2742u51.error(new z0("'" + strB + "' and '" + strB2 + "' map to same name: '" + strA + "'", c3329j.c));
            }
        }
        this.c.a();
    }

    public static T0 a(C2742u50 c2742u50, Path path) throws IOException {
        H0 h0 = new H0(new O(new BufferedReader(new InputStreamReader(Files.newInputStream(path, new OpenOption[0]), StandardCharsets.UTF_8))), c2742u50, false, false, MapVersion.MAP_VERSION_NONE);
        try {
            S0 s0 = new S0(c2742u50);
            h0.b(s0);
            h0.a(s0);
            s0.c.a();
            T0 t0 = new T0(AbstractC0706Nu.a(s0.a), s0.b, s0.c);
            h0.a.close();
            return t0;
        } catch (Throwable th) {
            try {
                h0.a.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }
}
