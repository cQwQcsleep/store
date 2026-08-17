package com.android.tools.r8.shaking;

import com.android.tools.r8.shaking.C3375c1;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.c1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3375c1 extends AbstractC3415k1 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3375c1(C3380d1 c3380d1) {
        super(new C3370b1(c3380d1));
        c3380d1.getClass();
    }

    @Override // com.android.tools.r8.shaking.AbstractC3390f1
    public final C3375c1 a(C3375c1 c3375c1) {
        return (C3375c1) ((C3375c1) ((C3375c1) a((AbstractC3415k1) c3375c1)).a(!((C3370b1) c3375c1.a).k, new Consumer() { // from class: zig
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C3375c1) obj).s();
            }
        })).a(!((C3370b1) c3375c1.a).l, new Consumer() { // from class: ajg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C3375c1) obj).t();
            }
        });
    }

    @Override // com.android.tools.r8.shaking.AbstractC3390f1
    public final C3375c1 b() {
        return this;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3390f1
    public final AbstractC3390f1 o() {
        return this;
    }

    public final C3375c1 s() {
        ((C3370b1) this.a).k = false;
        return this;
    }

    public final C3375c1 t() {
        ((C3370b1) this.a).l = false;
        return this;
    }
}
