package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0470Er;
import com.android.tools.r8.internal.C1307dI;
import com.android.tools.r8.internal.C1391eI;
import com.android.tools.r8.internal.EnumC1477fI;
import com.android.tools.r8.internal.EnumC1563gI;
import com.android.tools.r8.kotlin.D;
import com.android.tools.r8.kotlin.E;
import com.android.tools.r8.shaking.InterfaceC3369b0;
import defpackage.q33;
import defpackage.z33;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E implements InterfaceC3369b0 {
    public final EnumC1563gI a;
    public final EnumC1477fI b;
    public final List c;
    public final D d;

    public E(EnumC1563gI enumC1563gI, EnumC1477fI enumC1477fI, AbstractC0551Hu abstractC0551Hu, D d) {
        this.a = enumC1563gI;
        this.b = enumC1477fI;
        this.c = abstractC0551Hu;
        this.d = d;
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
        C0470Er.a((Iterable) this.c, new Function() { // from class: j14
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return E.a((D) obj);
            }
        }, (Object) interfaceC0189d1);
        this.d.a(interfaceC0189d1);
    }

    public final boolean b(Consumer consumer, C0333y c0333y) {
        final C1307dI c1307dI = (C1307dI) d0.a(consumer, new C1307dI(this.a, this.b));
        return d0.a(c0333y, this.c, c1307dI.c, new z33()) | this.d.b(new Consumer() { // from class: m14
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c1307dI.a((C1391eI) obj);
            }
        }, c0333y);
    }

    public static /* synthetic */ Consumer a(D d) {
        Objects.requireNonNull(d);
        return new q33(d);
    }
}
