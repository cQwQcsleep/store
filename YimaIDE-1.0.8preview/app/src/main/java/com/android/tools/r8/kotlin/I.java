package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.C1903kI;
import com.android.tools.r8.internal.C3100yI;
import com.android.tools.r8.shaking.InterfaceC3369b0;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I implements InterfaceC3369b0 {
    public static final I c = new I("kotlin.jvm.PlatformType", null);
    public static final /* synthetic */ boolean d = true;
    public final String a;
    public final r0 b;

    public I(String str, r0 r0Var) {
        this.a = str;
        this.b = r0Var;
        if (d || "kotlin.jvm.PlatformType".equals(str)) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
        if (this == c) {
            return;
        }
        r0 r0Var = this.b;
        if (r0Var != null) {
            r0Var.a(interfaceC0189d1);
        } else {
            if (d) {
                return;
            }
            x1f.a();
        }
    }

    public final boolean b(final Consumer consumer, C0333y c0333y) {
        if (this == c) {
            return false;
        }
        r0 r0Var = this.b;
        if (r0Var != null) {
            return r0Var.b(new Consumer() { // from class: cd6
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(consumer, (C3100yI) obj);
                }
            }, c0333y);
        }
        if (d) {
            return false;
        }
        x1f.a();
        return false;
    }

    public final /* synthetic */ void a(Consumer consumer, C3100yI c3100yI) {
        consumer.accept(new C1903kI(c3100yI, this.a));
    }
}
