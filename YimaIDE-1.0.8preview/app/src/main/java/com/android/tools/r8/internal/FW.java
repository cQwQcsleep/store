package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FW {
    public final ArrayList a;
    public final DW[] b;
    public final boolean c;

    public FW(boolean z, DW... dwArr) {
        this.a = new ArrayList(dwArr.length);
        for (int i = 0; i < dwArr.length; i++) {
            this.a.add(new ArrayList());
            dwArr[i].a(i);
        }
        this.c = z;
        this.b = dwArr;
    }

    public final C2165nN a(Supplier supplier, Supplier supplier2, Runnable runnable) {
        if (!((Boolean) supplier.get()).booleanValue()) {
            return null;
        }
        AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) supplier2.get();
        int i = 0;
        boolean z = true;
        while (true) {
            DW[] dwArr = this.b;
            if (i >= dwArr.length) {
                break;
            }
            DW dw = dwArr[i];
            List list = (List) this.a.get(i);
            list.clear();
            if (abstractC0890Uw != null) {
                for (int i2 = 0; i2 < dw.c() && dw.b().test(abstractC0890Uw); i2++) {
                    list.add(abstractC0890Uw);
                    if (!((Boolean) supplier.get()).booleanValue()) {
                        abstractC0890Uw = null;
                        break;
                    }
                    abstractC0890Uw = (AbstractC0890Uw) supplier2.get();
                }
            }
            z &= list.size() >= dw.a() && list.size() <= dw.c();
            if (!z) {
                break;
            }
            i++;
        }
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < ((List) this.a.get(i3)).size(); i4++) {
                runnable.run();
            }
        }
        if (abstractC0890Uw != null) {
            runnable.run();
        }
        if (z) {
            return new C2165nN(this.a);
        }
        return null;
    }

    public final C2165nN a(final C1650hK c1650hK) {
        if (this.c) {
            return a(new Supplier() { // from class: wl4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Boolean.valueOf(c1650hK.hasPrevious());
                }
            }, new Supplier() { // from class: xl4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return c1650hK.previous();
                }
            }, new Runnable() { // from class: yl4
                @Override // java.lang.Runnable
                public final void run() {
                    c1650hK.next();
                }
            });
        }
        return a(new Supplier() { // from class: zl4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(c1650hK.hasNext());
            }
        }, new Supplier() { // from class: am4
            @Override // java.util.function.Supplier
            public final Object get() {
                return c1650hK.next();
            }
        }, new Runnable() { // from class: bm4
            @Override // java.lang.Runnable
            public final void run() {
                c1650hK.previous();
            }
        });
    }
}
