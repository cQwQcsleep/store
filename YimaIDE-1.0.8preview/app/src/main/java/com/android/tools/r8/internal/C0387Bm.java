package com.android.tools.r8.internal;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0387Bm extends H0 {
    public final C0955Xj b;
    public final C1856jk[] d;
    public C0520Gp c = new C0520Gp();
    public C2712tk0 e = C2712tk0.c;

    public C0387Bm(C0955Xj c0955Xj) {
        this.b = c0955Xj;
        this.d = new C1856jk[c0955Xj.b.l.size()];
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        List arrayList;
        if (c1856jk.i != this.b) {
            w01.a("FieldDescriptor does not match message type.");
            return null;
        }
        C0520Gp c0520Gp = this.c;
        if (c0520Gp.b) {
            this.c = c0520Gp.m11clone();
        }
        C0520Gp c0520Gp2 = this.c;
        c0520Gp2.getClass();
        if (!c1856jk.m()) {
            w01.a("addRepeatedField() can only be called on repeated fields.");
            return null;
        }
        C0520Gp.c(c1856jk, obj);
        Object objA = c0520Gp2.a((InterfaceC0468Ep) c1856jk);
        if (objA == null) {
            arrayList = new ArrayList();
            c0520Gp2.a.a(c1856jk, arrayList);
        } else {
            arrayList = (List) objA;
        }
        arrayList.add(obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        if (c1856jk.i != this.b) {
            w01.a("FieldDescriptor does not match message type.");
            return null;
        }
        C0520Gp c0520Gp = this.c;
        if (c0520Gp.b) {
            this.c = c0520Gp.m11clone();
        }
        if (c1856jk.h == EnumC1771ik.g) {
            if (c1856jk.m()) {
                for (Object obj2 : (List) obj) {
                    Charset charset = AbstractC1556gB.a;
                    obj2.getClass();
                    if (!(obj2 instanceof C1515fk)) {
                        w01.a("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
                        return null;
                    }
                }
            } else {
                Charset charset2 = AbstractC1556gB.a;
                obj.getClass();
                if (!(obj instanceof C1515fk)) {
                    w01.a("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
                    return null;
                }
            }
        }
        C2198nk c2198nk = c1856jk.k;
        if (c2198nk != null) {
            int i = c2198nk.b;
            C1856jk c1856jk2 = this.d[i];
            if (c1856jk2 != null && c1856jk2 != c1856jk) {
                C0520Gp c0520Gp2 = this.c;
                c0520Gp2.a.remove(c1856jk2);
                if (c0520Gp2.a.isEmpty()) {
                    c0520Gp2.c = false;
                }
            }
            this.d[i] = c1856jk;
        } else if (c1856jk.e.f() == 3 && !c1856jk.m() && c1856jk.h.b != EnumC1686hk.k && obj.equals(c1856jk.f())) {
            C0520Gp c0520Gp3 = this.c;
            c0520Gp3.a.remove(c1856jk);
            if (c0520Gp3.a.isEmpty()) {
                c0520Gp3.c = false;
            }
            return this;
        }
        this.c.b(c1856jk, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        if (C0413Cm.a(this.b, this.c)) {
            return i();
        }
        C0955Xj c0955Xj = this.b;
        C0520Gp c0520Gp = this.c;
        C1856jk[] c1856jkArr = this.d;
        throw H0.c(new C0413Cm(c0955Xj, c0520Gp, (C1856jk[]) Arrays.copyOf(c1856jkArr, c1856jkArr.length), this.e));
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 c(C1856jk c1856jk) {
        if (c1856jk.i != this.b) {
            w01.a("FieldDescriptor does not match message type.");
            return null;
        }
        if (c1856jk.h.b == EnumC1686hk.k) {
            return new C0387Bm(c1856jk.i());
        }
        w01.a("newBuilderForField is only valid for fields with message type.");
        return null;
    }

    public final Object clone() {
        C0387Bm c0387Bm = new C0387Bm(this.b);
        c0387Bm.c.a(this.c);
        C2712tk0 c2712tk0 = this.e;
        C2712tk0 c2712tk1 = c0387Bm.e;
        C2712tk0 c2712tk2 = C2712tk0.c;
        c0387Bm.e = new C2285ok0().a(c2712tk1).a(c2712tk0).build();
        C1856jk[] c1856jkArr = this.d;
        System.arraycopy(c1856jkArr, 0, c0387Bm.d, 0, c1856jkArr.length);
        return c0387Bm;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final C0387Bm b(J0 j0) {
        if (!(j0 instanceof C0413Cm)) {
            return (C0387Bm) super.b(j0);
        }
        C0413Cm c0413Cm = (C0413Cm) j0;
        if (c0413Cm.d != this.b) {
            w01.a("mergeFrom(Message) can only merge messages of the same type.");
            return null;
        }
        C0520Gp c0520Gp = this.c;
        if (c0520Gp.b) {
            this.c = c0520Gp.m11clone();
        }
        this.c.a(c0413Cm.e);
        C2712tk0 c2712tk0 = c0413Cm.g;
        C2712tk0 c2712tk1 = this.e;
        C2712tk0 c2712tk2 = C2712tk0.c;
        this.e = new C2285ok0().a(c2712tk1).a(c2712tk0).build();
        int i = 0;
        while (true) {
            C1856jk[] c1856jkArr = this.d;
            if (i >= c1856jkArr.length) {
                return this;
            }
            C1856jk c1856jk = c1856jkArr[i];
            C1856jk[] c1856jkArr2 = c0413Cm.f;
            if (c1856jk == null) {
                c1856jkArr[i] = c1856jkArr2[i];
            } else {
                C1856jk c1856jk2 = c1856jkArr2[i];
                if (c1856jk2 != null && c1856jk != c1856jk2) {
                    C0520Gp c0520Gp2 = this.c;
                    c0520Gp2.a.remove(c1856jk);
                    if (c0520Gp2.a.isEmpty()) {
                        c0520Gp2.c = false;
                    }
                    this.d[i] = c0413Cm.f[i];
                }
            }
            i++;
        }
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.WN
    public final Map f() {
        return this.c.a();
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        if (C0413Cm.a(this.b, this.c)) {
            return i();
        }
        C0955Xj c0955Xj = this.b;
        C0520Gp c0520Gp = this.c;
        C1856jk[] c1856jkArr = this.d;
        throw H0.c(new C0413Cm(c0955Xj, c0520Gp, (C1856jk[]) Arrays.copyOf(c1856jkArr, c1856jkArr.length), this.e));
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C0413Cm i() {
        char c;
        if (this.b.b.l().j) {
            for (C1856jk c1856jk : Collections.unmodifiableList(Arrays.asList(this.b.g))) {
                int i = c1856jk.c.h;
                if (i != 1) {
                    c = 2;
                    if (i != 2) {
                        c = 3;
                        if (i != 3) {
                            c = 0;
                        }
                    }
                } else {
                    c = 1;
                }
                if (c == 0) {
                    c = 1;
                }
                if (c == 1 && !this.c.b(c1856jk)) {
                    EnumC1686hk enumC1686hk = c1856jk.h.b;
                    EnumC1686hk enumC1686hk2 = EnumC1686hk.k;
                    C0520Gp c0520Gp = this.c;
                    if (enumC1686hk == enumC1686hk2) {
                        c0520Gp.b(c1856jk, C0413Cm.a(c1856jk.i()));
                    } else {
                        c0520Gp.b(c1856jk, c1856jk.f());
                    }
                }
            }
        }
        this.c.d();
        C0955Xj c0955Xj = this.b;
        C0520Gp c0520Gp2 = this.c;
        C1856jk[] c1856jkArr = this.d;
        return new C0413Cm(c0955Xj, c0520Gp2, (C1856jk[]) Arrays.copyOf(c1856jkArr, c1856jkArr.length), this.e);
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        return C0413Cm.a(this.b, this.c);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        C2712tk0 c2712tk1 = this.e;
        C2712tk0 c2712tk2 = C2712tk0.c;
        this.e = new C2285ok0().a(c2712tk1).a(c2712tk0).build();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final Object a(C1856jk c1856jk) {
        if (c1856jk.i == this.b) {
            Object objA = this.c.a((InterfaceC0468Ep) c1856jk);
            if (objA != null) {
                return objA;
            }
            if (c1856jk.m()) {
                return Collections.EMPTY_LIST;
            }
            if (c1856jk.h.b == EnumC1686hk.k) {
                return C0413Cm.a(c1856jk.i());
            }
            return c1856jk.f();
        }
        w01.a("FieldDescriptor does not match message type.");
        return null;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final boolean b(C1856jk c1856jk) {
        if (c1856jk.i == this.b) {
            return this.c.b(c1856jk);
        }
        w01.a("FieldDescriptor does not match message type.");
        return false;
    }
}
