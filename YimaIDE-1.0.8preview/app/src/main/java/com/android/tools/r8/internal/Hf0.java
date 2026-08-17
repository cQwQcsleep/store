package com.android.tools.r8.internal;

import com.android.tools.r8.internal.Hf0;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Hf0 extends AbstractC1751iZ {
    public Hf0(AbstractC3114yW abstractC3114yW) {
        super(abstractC3114yW);
    }

    public static void a(Hf0 hf0, Ff0 ff0, C2516rW c2516rW, Ff0 ff1, Consumer consumer) {
        if (!hf0.a()) {
            String str = (String) ff0.a((Object) null);
            String str2 = (String) ff1.a((Object) null);
            consumer.accept((str == null && str2 == null) ? C1988lH.d : new C1988lH(null, str, str2));
        } else {
            if (ff0.a()) {
                c2516rW.getClass();
                throw new C3096yE(c2516rW, "Cannot specify both the exact string and a prefix");
            }
            if (ff1.a()) {
                c2516rW.getClass();
                throw new C3096yE(c2516rW, "Cannot specify both the exact string and a suffix");
            }
            consumer.accept((C1988lH) hf0.getValue());
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final C2431qW a(Gf0 gf0, String str, String str2, final Consumer consumer) {
        if (gf0.ordinal() != 1) {
            return null;
        }
        final C2516rW c2516rW = new C2516rW(this.a.b(str), str2);
        final Hf0 hf0 = new Hf0(c2516rW);
        final Ff0 ff0 = new Ff0(c2516rW);
        final Ff0 ff1 = new Ff0(c2516rW);
        hf0.a("exact", Gf0.b);
        Ef0 ef0 = Ef0.b;
        ff0.a("startsWith", ef0);
        ff1.a("endsWith", ef0);
        return new C2431qW(c2516rW, AbstractC0551Hu.a(hf0, ff0, ff1), new Runnable() { // from class: w76
            @Override // java.lang.Runnable
            public final void run() {
                Hf0.a(this.b, ff0, c2516rW, ff1, consumer);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final boolean a(Object obj, String str, Object obj2, Consumer consumer) {
        if (((Gf0) obj).ordinal() != 0) {
            return false;
        }
        consumer.accept(C1988lH.a((String) obj2));
        return true;
    }
}
