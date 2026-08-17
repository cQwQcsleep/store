package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1306dH {
    public static final C1306dH b = new C1306dH('*');
    public static final AbstractC0706Nu c;
    public final char a;

    static {
        C1306dH[] c1306dHArr = {new C1306dH('Z'), new C1306dH('B'), new C1306dH('C'), new C1306dH('S'), new C1306dH('I'), new C1306dH('J'), new C1306dH('F'), new C1306dH('D')};
        C0629Ku c0629KuE = AbstractC0706Nu.e();
        for (int i = 0; i < 8; i++) {
            C1306dH c1306dH = c1306dHArr[i];
            c0629KuE.a(Character.toString(c1306dH.a()), c1306dH);
        }
        c = c0629KuE.b();
    }

    public C1306dH(char c2) {
        this.a = c2;
    }

    public final char a() {
        if (this != b) {
            return this.a;
        }
        defpackage.l0.a("No descriptor exists for 'any' primitive");
        return (char) 0;
    }

    public static void a(Consumer consumer) {
        c.values().forEach(consumer);
    }
}
