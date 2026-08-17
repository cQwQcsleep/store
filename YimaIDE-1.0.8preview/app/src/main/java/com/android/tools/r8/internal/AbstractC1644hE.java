package com.android.tools.r8.internal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1644hE {
    public static final C0389Bo a;

    static {
        C0389Bo c0389Bo = new C0389Bo();
        c0389Bo.a(AbstractC1559gE.a);
        c0389Bo.a(AbstractC1559gE.b);
        c0389Bo.a(AbstractC1559gE.c);
        c0389Bo.a(AbstractC1559gE.d);
        c0389Bo.a(AbstractC1559gE.e);
        c0389Bo.a(AbstractC1559gE.f);
        c0389Bo.a(AbstractC1559gE.g);
        c0389Bo.a(AbstractC1559gE.h);
        c0389Bo.a(AbstractC1559gE.i);
        c0389Bo.a(AbstractC1559gE.j);
        c0389Bo.a(AbstractC1559gE.k);
        c0389Bo.a(AbstractC1559gE.l);
        c0389Bo.a(AbstractC1559gE.m);
        c0389Bo.a(AbstractC1559gE.n);
        a = c0389Bo;
    }

    public static ND a(ByteArrayInputStream byteArrayInputStream, String[] strArr) {
        L0 l0A;
        C0389Bo c0389Bo = a;
        ZD zd = C1473fE.i;
        zd.getClass();
        try {
            int i = byteArrayInputStream.read();
            if (i == -1) {
                l0A = null;
            } else {
                if ((i & 128) != 0) {
                    i &= 127;
                    int i2 = 7;
                    while (true) {
                        if (i2 < 32) {
                            int i3 = byteArrayInputStream.read();
                            if (i3 == -1) {
                                throw new QB("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
                            }
                            i |= (i3 & 127) << i2;
                            if ((i3 & 128) == 0) {
                                break;
                            }
                            i2 += 7;
                        } else {
                            while (true) {
                                if (i2 >= 64) {
                                    throw new QB("CodedInputStream encountered a malformed varint.");
                                }
                                int i4 = byteArrayInputStream.read();
                                if (i4 == -1) {
                                    throw new QB("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
                                }
                                if ((i4 & 128) == 0) {
                                    break;
                                }
                                i2 += 7;
                            }
                        }
                    }
                }
                l0A = zd.a((InputStream) new K0(byteArrayInputStream, i), c0389Bo);
            }
            C1473fE c1473fE = (C1473fE) AbstractC1451f1.a(l0A);
            KB.b(c1473fE, "parseDelimitedFrom(...)");
            return new ND(c1473fE, strArr);
        } catch (IOException e) {
            throw new QB(e.getMessage());
        }
    }

    public static String a(C2903w00 c2903w00, InterfaceC2425qQ interfaceC2425qQ) {
        if ((c2903w00.d & 16) != 16) {
            return null;
        }
        String strA = ((ND) interfaceC2425qQ).a(c2903w00.j);
        String str = (String) yc.b.get(strA);
        if (str != null) {
            return str;
        }
        return "L" + AbstractC1679hg0.a(strA, '.', '$') + ';';
    }
}
