package com.android.tools.r8.dex;

import com.android.tools.r8.graph.AbstractC0259n1;
import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.AbstractC0315v2;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B {
    public int a = 0;
    public final AbstractC0315v2[] b;
    public final Supplier c;

    public B(AbstractC0315v2[] abstractC0315v2Arr, Supplier supplier) {
        this.b = abstractC0315v2Arr;
        this.c = supplier;
    }

    public final AbstractC0259n1 a(AbstractC0287r2 abstractC0287r2) {
        while (true) {
            int i = this.a;
            AbstractC0315v2[] abstractC0315v2Arr = this.b;
            if (i >= abstractC0315v2Arr.length || abstractC0315v2Arr[i].b.compareTo(abstractC0287r2) >= 0) {
                break;
            }
            this.a++;
        }
        int i2 = this.a;
        AbstractC0315v2[] abstractC0315v2Arr2 = this.b;
        return (i2 >= abstractC0315v2Arr2.length || !abstractC0315v2Arr2[i2].b.equals(abstractC0287r2)) ? (AbstractC0259n1) this.c.get() : this.b[this.a].c;
    }
}
