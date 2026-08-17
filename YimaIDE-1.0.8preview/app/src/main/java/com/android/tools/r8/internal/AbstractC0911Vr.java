package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Vr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0911Vr extends H0 {
    public final InterfaceC0937Wr b;
    public C0885Ur c;
    public boolean d;
    public C2712tk0 e = C2712tk0.c;

    public AbstractC0911Vr(InterfaceC0937Wr interfaceC0937Wr) {
        this.b = interfaceC0937Wr;
    }

    @Override // com.android.tools.r8.internal.WN
    public Object a(C1856jk c1856jk) {
        Object objB = C2123ms.a(n(), c1856jk).b(this);
        return c1856jk.m() ? Collections.unmodifiableList((List) objB) : objB;
    }

    @Override // com.android.tools.r8.internal.WN
    public boolean b(C1856jk c1856jk) {
        return C2123ms.a(n(), c1856jk).a(this);
    }

    public final AbstractC0911Vr c(C2712tk0 c2712tk0) {
        C2712tk0 c2712tk1 = this.e;
        C2712tk0 c2712tk2 = C2712tk0.c;
        return d(new C2285ok0().a(c2712tk1).a(c2712tk0).build());
    }

    public abstract AbstractC0911Vr d(C2712tk0 c2712tk0);

    @Override // com.android.tools.r8.internal.WN
    public Map f() {
        return Collections.unmodifiableMap(l());
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.H0
    public final void j() {
        this.d = true;
    }

    public final AbstractC0911Vr k() {
        AbstractC0911Vr abstractC0911Vr = (AbstractC0911Vr) b().h();
        abstractC0911Vr.a(i());
        return abstractC0911Vr;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x009f A[EDGE_INSN: B:31:0x009f->B:40:0x00be BREAK  A[LOOP:1: B:24:0x0087->B:53:0x0087]] */
    public final TreeMap l() {
        boolean zB;
        TreeMap treeMap = new TreeMap();
        List listUnmodifiableList = Collections.unmodifiableList(Arrays.asList(n().a.g));
        int i = 0;
        while (i < listUnmodifiableList.size()) {
            C1856jk c1856jk = (C1856jk) listUnmodifiableList.get(i);
            C2198nk c2198nk = c1856jk.k;
            if (c2198nk != null) {
                i += c2198nk.g - 1;
                C1271cs c1271csA = C2123ms.a(n(), c2198nk);
                C1856jk c1856jk2 = c1271csA.d;
                if (c1856jk2 != null) {
                    zB = b(c1856jk2);
                } else {
                    zB = ((InterfaceC1046aB) AbstractC2209ns.a(c1271csA.c, this, new Object[0])).a() != 0;
                }
                if (zB) {
                    C1271cs c1271csA2 = C2123ms.a(n(), c2198nk);
                    C1856jk c1856jk3 = c1271csA2.d;
                    if (c1856jk3 == null) {
                        int iA = ((InterfaceC1046aB) AbstractC2209ns.a(c1271csA2.c, this, new Object[0])).a();
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

    public final C0885Ur m() {
        if (this.c == null) {
            this.c = new C0885Ur(this);
        }
        return this.c;
    }

    public abstract C2123ms n();

    public final void o() {
        if (this.b != null) {
            this.d = true;
        }
    }

    public final void p() {
        InterfaceC0937Wr interfaceC0937Wr;
        if (!this.d || (interfaceC0937Wr = this.b) == null) {
            return;
        }
        interfaceC0937Wr.a();
        this.d = false;
    }

    @Override // com.android.tools.r8.internal.H0
    public H0 c(C1856jk c1856jk) {
        return C2123ms.a(n(), c1856jk).a();
    }
}
