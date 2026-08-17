package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import defpackage.z26;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1328dc {
    public static final C1161bc e = new C1161bc();
    public static final /* synthetic */ boolean f = true;
    public final C0333y a;
    public final C0705Nt b;
    public C2031lm c;
    public int d;

    public C1328dc() {
        this.c = null;
        this.d = -1;
        this.a = null;
        this.b = null;
    }

    public boolean a(com.android.tools.r8.graph.I2 i2, AbstractC1047aC abstractC1047aC) {
        int i;
        AbstractC0890Uw next;
        com.android.tools.r8.graph.B5 b5I = this.b.i();
        H5 h5I = abstractC1047aC.i();
        Iterator<AbstractC0890Uw> it = h5I.k().iterator();
        while (it.hasNext() && (next = it.next()) != abstractC1047aC) {
            com.android.tools.r8.graph.I2 i3 = i2;
            if (next.a(i3, b5I, this.a, 2, 1)) {
                return true;
            }
            i2 = i3;
        }
        com.android.tools.r8.graph.I2 i4 = i2;
        if (this.c == null) {
            this.c = new C2031lm(this.b, 2);
        }
        for (H5 h5 : this.c.a(h5I)) {
            if (!h5.x() || h5.g() == null) {
                i = 1;
                break;
            }
            if (this.d < 0) {
                int iA = this.b.A();
                this.d = iA;
                this.b.a(h5I, iA);
            }
            C2490r8 c2490r8I = h5.i();
            c2490r8I.getClass();
            int i5 = 0;
            while (true) {
                if (i5 >= c2490r8I.size()) {
                    i = 1;
                    break;
                }
                Object obj = c2490r8I.c.get(i5);
                i5++;
                if (((H5) obj).a(this.d)) {
                    i = 2;
                    break;
                }
            }
            J5 j5H = h5.H();
            while (j5H.hasNext()) {
                AbstractC0890Uw next2 = j5H.next();
                if (next2.a(i4, b5I, this.a, 2, i)) {
                    return true;
                }
                if (h5.x() && next2.g()) {
                    if (!f && !StreamSupport.stream(Spliterators.spliteratorUnknownSize(j5H, 0), false).noneMatch(new z26())) {
                        x1f.a();
                        break;
                    }
                    break;
                }
            }
        }
        return false;
    }

    public C1328dc(C0333y c0333y, C0705Nt c0705Nt) {
        this.c = null;
        this.d = -1;
        this.a = c0333y;
        this.b = c0705Nt;
    }
}
