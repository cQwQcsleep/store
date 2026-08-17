package com.android.tools.r8.internal;

import com.android.tools.r8.internal.HW;
import com.android.tools.r8.internal.X2;
import com.android.tools.r8.shaking.M;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X2 extends W2 {
    public static final /* synthetic */ boolean f = true;
    public final com.android.tools.r8.shaking.V1 a;
    public int b;
    public List c;
    public final ArrayList d;
    public final BiConsumer e;

    public X2(com.android.tools.r8.shaking.V1 v1, List list, BiConsumer biConsumer) {
        this.b = 0;
        this.d = new ArrayList();
        if (!f && v1.a.isEmpty() && list.isEmpty()) {
            x1f.a();
            throw null;
        }
        this.a = v1;
        this.c = list;
        this.e = biConsumer;
    }

    @Override // com.android.tools.r8.internal.W2
    public final void a(com.android.tools.r8.shaking.M m) {
        if (this.c.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.c.size(); i++) {
            HW hw = (HW) this.c.get(i);
            if (hw != null) {
                if (!HW.d && hw.a.isEmpty()) {
                    x1f.a();
                    return;
                }
                int i2 = 0;
                for (Object obj : hw.a) {
                    if (hw.a(obj, m)) {
                        i2++;
                        hw.b.add(obj);
                    }
                }
                if (i2 == 0) {
                    continue;
                } else if (i2 == hw.a.size()) {
                    hw.a = Collections.EMPTY_LIST;
                    this.b++;
                    this.c.set(i, null);
                    m.a(hw.c);
                    ArrayList arrayList = this.d;
                    if (!HW.d && !hw.a.isEmpty()) {
                        x1f.a();
                        return;
                    } else {
                        arrayList.add(new C2251oN(hw.c, hw.a(hw.b)));
                        this.e.accept(hw, m);
                    }
                } else {
                    int size = hw.a.size() - i2;
                    if (!HW.d && size <= 0) {
                        x1f.a();
                        return;
                    }
                    List listA = hw.a(hw.b);
                    List<com.android.tools.r8.graph.F2> listA2 = hw.a(hw.a);
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it = hw.a.iterator();
                    for (com.android.tools.r8.graph.F2 f2 : listA2) {
                        Object next = it.next();
                        if (!listA.contains(f2)) {
                            arrayList2.add(next);
                        }
                    }
                    boolean z = HW.d;
                    if (!z && it.hasNext()) {
                        x1f.a();
                        return;
                    }
                    if (!z && arrayList2.size() != size) {
                        x1f.a();
                        return;
                    }
                    hw.a = arrayList2;
                    if (!z && arrayList2.isEmpty()) {
                        x1f.a();
                        return;
                    }
                }
            }
        }
        if (this.b == this.c.size()) {
            if (!f && !AbstractC3179zC.a(this.c, new EX() { // from class: bwf
                @Override // com.android.tools.r8.internal.EX
                public final boolean apply(Object obj2) {
                    return Objects.isNull((HW) obj2);
                }
            })) {
                x1f.a();
                return;
            } else {
                this.b = 0;
                this.c = Collections.EMPTY_LIST;
                return;
            }
        }
        if (this.b >= Math.max(1, this.c.size() / 10)) {
            int size2 = this.c.size() - this.b;
            ArrayList arrayList3 = new ArrayList(size2);
            for (HW hw2 : this.c) {
                if (hw2 != null) {
                    if (!f && hw2.a.isEmpty()) {
                        x1f.a();
                        return;
                    }
                    arrayList3.add(hw2);
                }
            }
            if (f || arrayList3.size() == size2) {
                this.b = 0;
                this.c = arrayList3;
            } else {
                x1f.a();
            }
        }
    }

    @Override // com.android.tools.r8.internal.W2
    public final void b(com.android.tools.r8.shaking.M m) {
        if (!f && !this.d.isEmpty()) {
            x1f.a();
        } else {
            if (this.a.a.isEmpty()) {
                return;
            }
            m.a(this.a);
        }
    }

    public X2(com.android.tools.r8.shaking.V1 v1, List list) {
        this(v1, list, new BiConsumer() { // from class: cwf
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                X2.a((HW) obj, (M) obj2);
            }
        });
    }

    public static /* synthetic */ void a(HW hw, com.android.tools.r8.shaking.M m) {
    }

    @Override // com.android.tools.r8.internal.W2
    public final C2337pN a() {
        return new C2337pN(this.a, this.d);
    }
}
