package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2587sH;
import com.android.tools.r8.internal.OO;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class OO extends AbstractC1751iZ {
    public final C2967wj0 g;

    public OO(C2773uW c2773uW) {
        super(c2773uW);
        this.g = new C2967wj0(c2773uW);
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final boolean a(Object obj, String str, Object obj2, Consumer consumer) {
        EnumC2881vj0 enumC2881vj0 = (EnumC2881vj0) obj;
        if (enumC2881vj0 == EnumC2881vj0.c && "void".equals(obj2)) {
            consumer.accept(PG.a);
            return true;
        }
        if (enumC2881vj0 != EnumC2881vj0.d || !C3050xi0.a(0, 1, "V").equals(obj2)) {
            return this.g.a(enumC2881vj0, str, obj2, a(consumer));
        }
        consumer.accept(PG.a);
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final boolean b(Object obj, String str, String str2, Consumer consumer) {
        C2967wj0 c2967wj0 = this.g;
        a(consumer);
        c2967wj0.getClass();
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final J2 c(String str, Consumer consumer, Object obj) {
        C2967wj0 c2967wj0 = this.g;
        a(consumer);
        c2967wj0.getClass();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ, com.android.tools.r8.internal.InterfaceC1665hZ
    public final Object getValue() {
        return (QG) super.getValue();
    }

    public final QG b() {
        return (QG) super.getValue();
    }

    public static void a(Consumer consumer, AbstractC2587sH abstractC2587sH) {
        OG og;
        abstractC2587sH.getClass();
        if (abstractC2587sH instanceof C2245oH) {
            og = OG.b;
        } else {
            og = new OG(abstractC2587sH);
        }
        consumer.accept(og);
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final J2 a(Object obj, String str, String str2, Consumer consumer) {
        return this.g.a((EnumC2881vj0) obj, str, str2, a(consumer));
    }

    public static Consumer a(final Consumer consumer) {
        return new Consumer() { // from class: hma
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                OO.a(consumer, (AbstractC2587sH) obj);
            }
        };
    }
}
