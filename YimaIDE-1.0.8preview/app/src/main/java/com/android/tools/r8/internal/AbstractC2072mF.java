package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1560gF;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2072mF {
    public static final /* synthetic */ boolean a = true;

    public abstract TG a(TG tg);

    public abstract AbstractC2554rv a();

    public final void a(final C2498rE c2498rE) {
        a().forEach(new Consumer() { // from class: qlh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC1560gF) obj).a(c2498rE);
            }
        });
    }

    public abstract Set b();
}
