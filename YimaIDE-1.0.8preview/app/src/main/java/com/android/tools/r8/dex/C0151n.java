package com.android.tools.r8.dex;

import com.android.tools.r8.dex.C0151n;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.internal.AT;
import com.android.tools.r8.internal.AbstractC1643hD;
import com.android.tools.r8.internal.C1898kD;
import com.android.tools.r8.internal.ET;
import defpackage.g3c;
import java.io.UTFDataFormatException;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.dex.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0151n {
    public static final /* synthetic */ boolean b = true;
    public final ET a = new ET();

    public C0151n() {
        boolean z = b;
        if (!z && "~~~".charAt(0) != '~') {
            x1f.a();
            throw null;
        }
        if (!z && "~~~".charAt(1) != '~') {
            x1f.a();
            throw null;
        }
        if (z || "~~~".charAt(2) == '~') {
            return;
        }
        x1f.a();
        throw null;
    }

    public final String a() {
        final C1898kD c1898kD = new C1898kD();
        this.a.e().stream().sorted(Comparator.comparing(new Function() { // from class: hnh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (String) ((AT) obj).getKey();
            }
        })).forEach(new Consumer() { // from class: lnh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C0151n.a(c1898kD, (AT) obj);
            }
        });
        return "~~~" + c1898kD;
    }

    public final /* synthetic */ void a(Map.Entry entry) {
        this.a.a((String) entry.getKey(), Long.parseLong(((AbstractC1643hD) entry.getValue()).g(), 16));
    }

    public static boolean a(H2 h2) {
        try {
            char[] cArr = new char[3];
            int iA = h2.a(cArr);
            if (iA == 0) {
                return true;
            }
            char c = cArr[0];
            if (c != '~') {
                return c < '~';
            }
            if (iA == 1) {
                return true;
            }
            char c2 = cArr[1];
            if (c2 != '~') {
                return c2 < '~';
            }
            return iA == 2 || cArr[2] < '~';
        } catch (UTFDataFormatException e) {
            g3c.a("Bad format", e);
            return false;
        }
    }

    public final void a(C1898kD c1898kD) {
        c1898kD.b.entrySet().forEach(new Consumer() { // from class: pnh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((Map.Entry) obj);
            }
        });
    }

    public static void a(C1898kD c1898kD, AT at) {
        ET et = at.c;
        Object[] objArr = et.b;
        int i = at.b;
        c1898kD.a((String) objArr[i], Long.toString(et.c[i], 16));
    }
}
