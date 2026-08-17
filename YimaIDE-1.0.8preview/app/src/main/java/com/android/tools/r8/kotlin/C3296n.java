package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AH;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C3015xH;
import com.android.tools.r8.internal.E6;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.kotlin.AbstractC3295m;
import com.android.tools.r8.kotlin.C3296n;
import com.android.tools.r8.shaking.InterfaceC3369b0;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.kotlin.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3296n implements InterfaceC3369b0 {
    public static final P40 c;
    public final u0 a;
    public final Map b;

    static {
        int i = AbstractC0551Hu.c;
        c = P40.e;
    }

    public C3296n(u0 u0Var, Map map) {
        this.a = u0Var;
        this.b = map;
    }

    public final /* synthetic */ void a(final E6 e6, final C0333y c0333y, Consumer consumer, String str) {
        if (str == null) {
            e6.b(true);
            return;
        }
        String strC = C0929Wj.c(str);
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.b.forEach(new BiConsumer() { // from class: onh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AbstractC3295m abstractC3295m = (AbstractC3295m) obj2;
                e6.a(abstractC3295m.b(new Consumer() { // from class: knh
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj3) {
                        C3296n.a(map, str, (AH) obj3);
                    }
                }, c0333y));
            }
        });
        consumer.accept(new C3015xH(strC, linkedHashMap));
    }

    public final boolean b(final Consumer consumer, final C0333y c0333y) {
        final E6 e6 = new E6(false);
        e6.a(this.a.b(new Consumer() { // from class: fnh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(e6, c0333y, consumer, (String) obj);
            }
        }, c0333y, null));
        return e6.a();
    }

    public static /* synthetic */ void a(Map map, String str, AH ah) {
        if (ah != null) {
            map.put(str, ah);
        }
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(final InterfaceC0189d1 interfaceC0189d1) {
        this.a.a(interfaceC0189d1);
        this.b.forEach(new BiConsumer() { // from class: rnh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((AbstractC3295m) obj2).a(interfaceC0189d1);
            }
        });
    }
}
