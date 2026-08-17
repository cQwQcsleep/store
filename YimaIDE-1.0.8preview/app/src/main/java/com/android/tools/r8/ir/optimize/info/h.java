package com.android.tools.r8.ir.optimize.info;

import com.android.tools.r8.internal.AbstractC0423Cw;
import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.AbstractC0570In;
import com.android.tools.r8.internal.AbstractC1047aC;
import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.B1;
import com.android.tools.r8.internal.B7;
import com.android.tools.r8.internal.Gb0;
import com.android.tools.r8.internal.InterfaceC2182nc;
import com.android.tools.r8.internal.VB;
import java.util.BitSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class h implements g {
    public static final /* synthetic */ boolean a = true;

    public abstract boolean A();

    public abstract AbstractC2173nV B();

    public abstract boolean C();

    public abstract boolean D();

    public abstract boolean E();

    public abstract boolean F();

    public abstract boolean G();

    public abstract AbstractC0423Cw a(VB vb);

    public abstract boolean a(AbstractC1047aC abstractC1047aC);

    public abstract boolean e();

    public abstract boolean f();

    public abstract boolean g();

    public abstract com.android.tools.r8.internal.r h();

    public abstract B1 i();

    public abstract AbstractC3260a j();

    public abstract B7 k();

    public abstract InterfaceC2182nc l();

    public abstract AbstractC0423Cw m();

    public abstract AbstractC0439Dm n();

    public abstract AbstractC0570In o();

    public abstract Set p();

    public abstract int q();

    public abstract BitSet r();

    public abstract BitSet s();

    public abstract int t();

    public abstract Gb0 u();

    public abstract BitSet v();

    public abstract boolean w();

    public final boolean x() {
        if (a || v() == null || !v().isEmpty()) {
            return v() != null;
        }
        x1f.a();
        return false;
    }

    public abstract boolean y();

    public abstract boolean z();
}
