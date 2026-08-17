package com.android.tools.r8.internal;

import defpackage.g3c;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ns, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2209ns extends J0 implements Serializable {
    public C2712tk0 d;

    public AbstractC2209ns() {
        this.d = C2712tk0.c;
    }

    public static J0 a(AbstractC1537g1 abstractC1537g1, InputStream inputStream) throws IOException {
        AbstractC0663Md c0612Kd;
        try {
            abstractC1537g1.getClass();
            C0415Co c0415Co = AbstractC1537g1.a;
            if (inputStream == null) {
                byte[] bArr = AbstractC1556gB.d;
                int length = bArr.length;
                c0612Kd = new C0586Jd(bArr, 0, length, false);
                try {
                    c0612Kd.c(length);
                } catch (RB e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                c0612Kd = new C0612Kd(inputStream);
            }
            TN tn = (TN) abstractC1537g1.a(c0612Kd, c0415Co);
            try {
                c0612Kd.a(0);
                return (J0) AbstractC1537g1.a(tn);
            } catch (RB e2) {
                e2.b = tn;
                throw e2;
            }
        } catch (RB e3) {
            throw e3.a();
        }
    }

    public abstract H0 a(C0859Tr c0859Tr);

    public boolean b(C1856jk c1856jk) {
        return C2123ms.a(j(), c1856jk).a(this);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return j().a;
    }

    public Map f() {
        return Collections.unmodifiableMap(i());
    }

    /* JADX WARN: Code duplicated, block: B:31:0x009f A[EDGE_INSN: B:31:0x009f->B:40:0x00be BREAK  A[LOOP:1: B:24:0x0087->B:53:0x0087]] */
    public final TreeMap i() {
        boolean zB;
        TreeMap treeMap = new TreeMap();
        List listUnmodifiableList = Collections.unmodifiableList(Arrays.asList(j().a.g));
        int i = 0;
        while (i < listUnmodifiableList.size()) {
            C1856jk c1856jk = (C1856jk) listUnmodifiableList.get(i);
            C2198nk c2198nk = c1856jk.k;
            if (c2198nk != null) {
                i += c2198nk.g - 1;
                C1271cs c1271csA = C2123ms.a(j(), c2198nk);
                C1856jk c1856jk2 = c1271csA.d;
                if (c1856jk2 != null) {
                    zB = b(c1856jk2);
                } else {
                    zB = ((InterfaceC1046aB) a(c1271csA.b, this, new Object[0])).a() != 0;
                }
                if (zB) {
                    C1271cs c1271csA2 = C2123ms.a(j(), c2198nk);
                    C1856jk c1856jk3 = c1271csA2.d;
                    if (c1856jk3 == null) {
                        int iA = ((InterfaceC1046aB) a(c1271csA2.b, this, new Object[0])).a();
                        if (iA <= 0) {
                            c1856jk = null;
                            break;
                        }
                        C1856jk[] c1856jkArr = c1271csA2.a.h;
                        int length = c1856jkArr.length;
                        C1600gk c1600gk = C1856jk.n;
                        Logger logger = AbstractC2370pk.a;
                        C1600gk c1600gk2 = C1856jk.n;
                        int i2 = length - 1;
                        int i3 = 0;
                        while (true) {
                            if (i3 <= i2) {
                                int i4 = (i3 + i2) / 2;
                                C1856jk c1856jk4 = c1856jkArr[i4];
                                int iA2 = c1600gk2.a(c1856jk4);
                                if (iA >= iA2) {
                                    if (iA <= iA2) {
                                        c1856jk = c1856jk4;
                                        break;
                                    }
                                    i3 = i4 + 1;
                                } else {
                                    i2 = i4 - 1;
                                }
                            } else {
                                c1856jk = null;
                                break;
                            }
                        }
                    } else {
                        if (!b(c1856jk3)) {
                            c1856jk = null;
                            break;
                        }
                        c1856jk = c1271csA2.d;
                    }
                    treeMap.put(c1856jk, a(c1856jk));
                }
            } else if (c1856jk.m()) {
                List list = (List) a(c1856jk);
                if (!list.isEmpty()) {
                    treeMap.put(c1856jk, list);
                }
            } else if (b(c1856jk)) {
                treeMap.put(c1856jk, a(c1856jk));
            }
            i++;
        }
        return treeMap;
    }

    public abstract C2123ms j();

    public AbstractC2209ns(AbstractC0911Vr abstractC0911Vr) {
        this.d = abstractC0911Vr.e;
    }

    public Object a(C1856jk c1856jk) {
        return C2123ms.a(j(), c1856jk).b(this);
    }

    public static C0945Wz a(InterfaceC1216cB interfaceC1216cB) {
        C0945Wz c0945Wz = (C0945Wz) interfaceC1216cB;
        int i = c0945Wz.d;
        return c0945Wz.l(i == 0 ? 10 : i * 2);
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 a(I0 i0) {
        return a(new C0859Tr(i0));
    }

    public static Method a(Class cls, String str, Class[] clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e);
        }
    }

    public static Object a(Method method, Object obj, Object[] objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            g3c.a("Couldn't use Java reflection to implement protocol message reflection.", e);
            return null;
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (!(cause instanceof RuntimeException)) {
                if (!(cause instanceof Error)) {
                    g3c.a("Unexpected exception thrown by generated accessor method.", cause);
                    return null;
                }
                throw ((Error) cause);
            }
            throw ((RuntimeException) cause);
        }
    }

    public static boolean a(Object obj) {
        if (obj instanceof String) {
            return ((String) obj).isEmpty();
        }
        return ((U7) obj).size() == 0;
    }

    public static int a(int i, Object obj) {
        int iB;
        int iA;
        if (obj instanceof String) {
            iB = AbstractC0793Rd.b(i);
            iA = AbstractC0793Rd.a((String) obj);
        } else {
            iB = AbstractC0793Rd.b(i);
            iA = AbstractC0793Rd.a((U7) obj);
        }
        return iA + iB;
    }

    public static void a(AbstractC0793Rd abstractC0793Rd, int i, Object obj) {
        if (obj instanceof String) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(i, 2);
            c0689Nd.b((String) obj);
        } else {
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(i, 2);
            c0689Nd2.b((U7) obj);
        }
    }
}
