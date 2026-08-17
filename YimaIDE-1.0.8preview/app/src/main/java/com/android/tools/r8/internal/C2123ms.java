package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ms, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2123ms {
    public final C0955Xj a;
    public final InterfaceC1188bs[] b;
    public String[] c;
    public final C1271cs[] d;
    public volatile boolean e = false;

    public C2123ms(C0955Xj c0955Xj, String[] strArr) {
        this.a = c0955Xj;
        this.c = strArr;
        this.b = new InterfaceC1188bs[Collections.unmodifiableList(Arrays.asList(c0955Xj.g)).size()];
        this.d = new C1271cs[Collections.unmodifiableList(Arrays.asList(c0955Xj.j)).size()];
    }

    public final C2123ms a(Class cls, Class cls2) {
        if (this.e) {
            return this;
        }
        synchronized (this) {
            try {
                if (this.e) {
                    return this;
                }
                int length = this.b.length;
                for (int i = 0; i < length; i++) {
                    C1856jk c1856jk = (C1856jk) Collections.unmodifiableList(Arrays.asList(this.a.g)).get(i);
                    C2198nk c2198nk = c1856jk.k;
                    String str = c2198nk != null ? this.c[c2198nk.b + length] : null;
                    boolean zM = c1856jk.m();
                    EnumC1771ik enumC1771ik = c1856jk.h;
                    if (zM) {
                        EnumC1686hk enumC1686hk = enumC1771ik.b;
                        if (enumC1686hk != EnumC1686hk.k) {
                            EnumC1686hk enumC1686hk2 = EnumC1686hk.j;
                            InterfaceC1188bs[] interfaceC1188bsArr = this.b;
                            if (enumC1686hk == enumC1686hk2) {
                                interfaceC1188bsArr[i] = new C1354ds(c1856jk, this.c[i], cls, cls2);
                            } else {
                                interfaceC1188bsArr[i] = new C1526fs(this.c[i], cls, cls2);
                            }
                        } else {
                            if (c1856jk.j()) {
                                String str2 = this.c[i];
                                AbstractC2209ns abstractC2209ns = (AbstractC2209ns) AbstractC2209ns.a(AbstractC2209ns.a(cls, "getDefaultInstance", new Class[0]), (Object) null, new Object[0]);
                                int i2 = c1856jk.c.g;
                                abstractC2209ns.getClass();
                                throw new RuntimeException("No map fields found in ".concat(abstractC2209ns.getClass().getName()));
                            }
                            this.b[i] = new C1611gs(this.c[i], cls, cls2);
                        }
                    } else {
                        EnumC1686hk enumC1686hk3 = enumC1771ik.b;
                        if (enumC1686hk3 == EnumC1686hk.k) {
                            this.b[i] = new C1952ks(c1856jk, this.c[i], cls, cls2, str);
                        } else if (enumC1686hk3 == EnumC1686hk.j) {
                            this.b[i] = new C1697hs(c1856jk, this.c[i], cls, cls2, str);
                        } else {
                            EnumC1686hk enumC1686hk4 = EnumC1686hk.h;
                            InterfaceC1188bs[] interfaceC1188bsArr2 = this.b;
                            if (enumC1686hk3 == enumC1686hk4) {
                                interfaceC1188bsArr2[i] = new C2038ls(c1856jk, this.c[i], cls, cls2, str);
                            } else {
                                interfaceC1188bsArr2[i] = new C1867js(c1856jk, this.c[i], cls, cls2, str);
                            }
                        }
                    }
                }
                int length2 = this.d.length;
                for (int i3 = 0; i3 < length2; i3++) {
                    this.d[i3] = new C1271cs(this.a, i3, this.c[i3 + length], cls, cls2);
                }
                this.e = true;
                this.c = null;
                return this;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static InterfaceC1188bs a(C2123ms c2123ms, C1856jk c1856jk) {
        c2123ms.getClass();
        if (c1856jk.i == c2123ms.a) {
            if (!c1856jk.c.q()) {
                return c2123ms.b[c1856jk.b];
            }
            w01.a("This type does not have extensions.");
            return null;
        }
        w01.a("FieldDescriptor does not match message type.");
        return null;
    }

    public static C1271cs a(C2123ms c2123ms, C2198nk c2198nk) {
        c2123ms.getClass();
        if (c2198nk.f == c2123ms.a) {
            return c2123ms.d[c2198nk.b];
        }
        w01.a("OneofDescriptor does not match message type.");
        return null;
    }
}
