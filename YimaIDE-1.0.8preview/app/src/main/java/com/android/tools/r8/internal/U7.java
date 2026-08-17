package com.android.tools.r8.internal;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class U7 implements Iterable, Serializable {
    public static final Q7 c = new Q7(AbstractC1556gB.d);
    public static final O7 d;
    public int b = 0;

    static {
        d = AbstractC2394q2.b() ? new S7() : new M7();
    }

    public static int a(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            jb9.a(AbstractC1784iv.a(i, "Beginning index: ", " < 0"));
            return 0;
        }
        if (i2 < i) {
            rnd.a("Beginning index larger than ending index: ", i, ", ", i2);
            return 0;
        }
        rnd.a("End index: ", i2, " >= ", i3);
        return 0;
    }

    public abstract boolean a();

    public abstract String b();

    public final String c() {
        Charset charset = AbstractC1556gB.a;
        return size() == 0 ? XmlPullParser.NO_NAMESPACE : b();
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int size = size();
        Q7 q7 = (Q7) this;
        byte[] bArr = q7.e;
        int iD = q7.d();
        int i2 = size;
        for (int i3 = iD; i3 < iD + size; i3++) {
            i2 = (i2 * 31) + bArr[i3];
        }
        if (i2 == 0) {
            i2 = 1;
        }
        this.b = i2;
        return i2;
    }

    public abstract byte j(int i);

    public abstract byte k(int i);

    public abstract int size();

    public final String toString() {
        String string;
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        int size = size();
        if (size() <= 50) {
            string = Wg0.a(new Tg0(this));
        } else {
            StringBuilder sb = new StringBuilder();
            Q7 q7 = (Q7) this;
            int iA = a(0, 47, q7.size());
            sb.append(Wg0.a(new Tg0(iA == 0 ? c : new N7(q7.e, q7.d(), iA))));
            sb.append("...");
            string = sb.toString();
        }
        return "<ByteString@" + hexString + " size=" + size + " contents=\"" + string + "\">";
    }

    public static Q7 a(String str) {
        return new Q7(str.getBytes(AbstractC1556gB.b));
    }
}
