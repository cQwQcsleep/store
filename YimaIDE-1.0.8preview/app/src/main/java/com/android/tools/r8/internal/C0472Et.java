package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Et, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0472Et {
    public final H5 a = new H5();
    public C0997Yz b = new C0997Yz();
    public InterfaceC3177zA c = new C0997Yz();
    public final C0997Yz d = new C0997Yz();
    public C0997Yz e = new C0997Yz();

    public final String toString() {
        StringBuilder sb = new StringBuilder("block ");
        sb.append(this.a.p());
        sb.append(" predecessors: ");
        C0997Yz c0997Yz = this.b;
        c0997Yz.getClass();
        C0971Xz c0971Xz = new C0971Xz(c0997Yz);
        String str = XmlPullParser.NO_NAMESPACE;
        String str2 = XmlPullParser.NO_NAMESPACE;
        while (c0971Xz.hasNext()) {
            int iQ = c0971Xz.q();
            sb.append(str2);
            sb.append(iQ);
            str2 = ", ";
        }
        C0997Yz c0997Yz2 = this.d;
        c0997Yz2.getClass();
        C0971Xz c0971Xz2 = new C0971Xz(c0997Yz2);
        while (c0971Xz2.hasNext()) {
            int iQ2 = c0971Xz2.q();
            sb.append(str2);
            sb.append('*');
            sb.append(iQ2);
            str2 = ", ";
        }
        sb.append(" successors: ");
        InterfaceC1640hA it = this.c.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            sb.append(str);
            sb.append(iIntValue);
            str = ", ";
        }
        C0997Yz c0997Yz3 = this.e;
        c0997Yz3.getClass();
        C0971Xz c0971Xz3 = new C0971Xz(c0997Yz3);
        while (c0971Xz3.hasNext()) {
            int iQ3 = c0971Xz3.q();
            sb.append(str);
            sb.append('*');
            sb.append(iQ3);
            str = ", ";
        }
        return sb.toString();
    }
}
