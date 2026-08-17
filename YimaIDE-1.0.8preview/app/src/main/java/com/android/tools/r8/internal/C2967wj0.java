package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2587sH;
import com.android.tools.r8.internal.C1476fH;
import com.android.tools.r8.internal.C2245oH;
import com.android.tools.r8.internal.C2967wj0;
import com.reandroid.arsc.chunk.TypeBlock;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wj0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2967wj0 extends AbstractC1751iZ {
    public C2967wj0(AbstractC3114yW abstractC3114yW) {
        super(abstractC3114yW);
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final J2 a(EnumC2881vj0 enumC2881vj0, String str, String str2, final Consumer consumer) {
        int iOrdinal = enumC2881vj0.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 3) {
                return null;
            }
            return new C0688Nc(this.a).a(EnumC0662Mc.b, str, str2, new Consumer() { // from class: opi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    C2967wj0.a(consumer, (C1476fH) obj);
                }
            });
        }
        C2516rW c2516rW = new C2516rW(this.a.b(str), str2);
        final C2967wj0 c2967wj0 = new C2967wj0(c2516rW);
        c2967wj0.a(TypeBlock.NAME_name, EnumC2881vj0.c);
        c2967wj0.a("constant", EnumC2881vj0.d);
        c2967wj0.a("classNamePattern", EnumC2881vj0.e);
        return new C2431qW(c2516rW, c2967wj0, new Runnable() { // from class: ppi
            @Override // java.lang.Runnable
            public final void run() {
                consumer.accept((AbstractC2587sH) c2967wj0.a(C2245oH.a));
            }
        });
    }

    public static void a(Consumer consumer, C1476fH c1476fH) {
        consumer.accept(new C2417qH(c1476fH));
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final boolean a(EnumC2881vj0 enumC2881vj0, String str, Object obj, Consumer consumer) {
        int iOrdinal = enumC2881vj0.ordinal();
        if (iOrdinal == 1) {
            consumer.accept(AbstractC1732iG.a((String) obj, this.a.b(str)));
            return true;
        }
        if (iOrdinal != 2) {
            return false;
        }
        consumer.accept(AbstractC2587sH.a(((C3050xi0) obj).b()));
        return true;
    }
}
