package com.android.tools.r8.shaking;

import com.android.tools.r8.shaking.AbstractC3415k1;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.k1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3415k1 extends AbstractC3390f1 {
    public AbstractC3415k1(AbstractC3410j1 abstractC3410j1) {
        super(abstractC3410j1);
    }

    public final AbstractC3415k1 a(AbstractC3415k1 abstractC3415k1) {
        return (AbstractC3415k1) ((AbstractC3415k1) super.a((AbstractC3390f1) abstractC3415k1)).a(!((AbstractC3410j1) abstractC3415k1.a).j, new Consumer() { // from class: lhh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC3415k1) obj).r();
            }
        });
    }

    @Override // com.android.tools.r8.shaking.AbstractC3390f1
    public final AbstractC3415k1 c() {
        return this;
    }

    public final AbstractC3415k1 r() {
        AbstractC3410j1 abstractC3410j1 = (AbstractC3410j1) this.a;
        abstractC3410j1.j = false;
        return (AbstractC3415k1) o();
    }
}
