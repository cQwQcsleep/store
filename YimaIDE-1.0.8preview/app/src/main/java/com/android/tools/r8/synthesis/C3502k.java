package com.android.tools.r8.synthesis;

import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.graph.AbstractC0175b1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0202f0;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.synthesis.C3502k;
import java.util.Comparator;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3502k implements Comparable {
    public static final /* synthetic */ boolean f = true;
    public final I2 b;
    public final I2 c;
    public final Origin d;
    public final FeatureSplit e;

    public C3502k(I2 i2, I2 i3, Origin origin, FeatureSplit featureSplit) {
        this.b = i2;
        this.c = i3;
        this.d = origin;
        this.e = featureSplit;
    }

    public final void a(C0333y c0333y, I2 i2) {
        if (c0333y.M().V()) {
            if (!f && !i2.m0().startsWith(this.b.m0())) {
                x1f.a();
                return;
            }
            I2 i2A = c0333y.z.a(this.b);
            if (i2A == null) {
                return;
            }
            String strF = C0929Wj.f(this.b.Z0());
            String strF2 = C0929Wj.f(i2A.Z0());
            String strSubstring = C0929Wj.f(i2.Z0()).substring(strF.length());
            c0333y.z.a(i2, c0333y.a().e(C0929Wj.l(strF2 + strSubstring)));
        }
    }

    public final String toString() {
        String str;
        I2 i2 = this.b;
        if (this.e.isBase()) {
            str = XmlPullParser.NO_NAMESPACE;
        } else {
            str = ", feature:" + this.e;
        }
        return "SynthesizingContext{" + i2 + str + "}";
    }

    public final I2 b() {
        return this.b;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(C3502k c3502k) {
        return Comparator.comparing(new Function() { // from class: reh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C3502k) obj).b();
            }
        }).thenComparing(new Function() { // from class: bfh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C3502k) obj).c;
            }
        }).compare(this, c3502k);
    }

    public final FeatureSplit a() {
        return this.e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static C3502k a(InterfaceC0202f0 interfaceC0202f0) {
        AbstractC0175b1 abstractC0175b1 = (AbstractC0175b1) interfaceC0202f0;
        return new C3502k(abstractC0175b1.z(), abstractC0175b1.z(), ((E0) interfaceC0202f0).d, FeatureSplit.BASE);
    }
}
