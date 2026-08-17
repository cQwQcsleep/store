package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.Bc0;
import com.android.tools.r8.internal.Ck0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class E3 {
    public static final /* synthetic */ boolean c = true;
    public final boolean a;
    public final AbstractC0551Hu b;

    public E3(AbstractC0551Hu abstractC0551Hu, boolean z) {
        this.a = z;
        if (abstractC0551Hu.isEmpty()) {
            F3 f3 = new F3();
            int i = AbstractC0551Hu.c;
            this.b = new Bc0(f3);
        } else if (c || z) {
            this.b = abstractC0551Hu;
        } else {
            x1f.a();
            throw null;
        }
    }

    public boolean a(String str) {
        if (!this.a) {
            return false;
        }
        Ck0 it = this.b.iterator();
        while (it.hasNext()) {
            if (((I3) it.next()).a(str)) {
                return true;
            }
        }
        return false;
    }
}
