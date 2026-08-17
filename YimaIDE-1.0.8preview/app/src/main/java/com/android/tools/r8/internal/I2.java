package com.android.tools.r8.internal;

import com.android.tools.r8.internal.I2;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I2 extends AbstractC1751iZ {
    public I2(AbstractC3114yW abstractC3114yW) {
        super(abstractC3114yW);
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final C2431qW a(F2 f2, String str, String str2, final Consumer consumer) {
        if (f2.ordinal() != 0) {
            return null;
        }
        C2516rW c2516rW = new C2516rW(this.a.b(str), str2);
        final E2 e2 = new E2(c2516rW);
        return new C2431qW(c2516rW, e2, new Runnable() { // from class: ne6
            @Override // java.lang.Runnable
            public final void run() {
                I2.a(consumer, e2);
            }
        });
    }

    public static void a(Consumer consumer, E2 e2) {
        BE beC;
        if (e2.a()) {
            beC = e2.c();
        } else {
            beC = BE.d;
        }
        consumer.accept(beC);
    }
}
