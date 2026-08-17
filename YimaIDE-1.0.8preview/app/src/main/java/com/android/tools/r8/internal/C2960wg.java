package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2072mF;
import com.android.tools.r8.internal.C2960wg;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2960wg extends AbstractC1751iZ {
    public C2960wg(AbstractC3114yW abstractC3114yW) {
        super(new C2773uW(abstractC3114yW, "constraints"));
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final C2157nF c(EnumC2874vg enumC2874vg, String str, final Consumer consumer) {
        C3030xW c3030xWB = this.a.b(str);
        int iOrdinal = enumC2874vg.ordinal();
        if (iOrdinal == 0) {
            Objects.requireNonNull(consumer);
            return new C2157nF(c3030xWB, new InterfaceC1221cG() { // from class: api
                @Override // com.android.tools.r8.internal.InterfaceC1221cG
                public final void accept(Object obj) {
                    consumer.accept((AbstractC2072mF) obj);
                }
            });
        }
        if (iOrdinal != 1) {
            return null;
        }
        return new C2157nF(c3030xWB, new InterfaceC1221cG() { // from class: zoi
            @Override // com.android.tools.r8.internal.InterfaceC1221cG
            public final void accept(Object obj) {
                C2960wg.a(consumer, (AbstractC2072mF) obj);
            }
        });
    }

    public static void a(Consumer consumer, AbstractC2072mF abstractC2072mF) {
        if (abstractC2072mF instanceof C1900kF) {
            abstractC2072mF = new C1731iF((C1900kF) abstractC2072mF);
        } else if (!AbstractC2072mF.a && !(abstractC2072mF instanceof C1986lF) && !(abstractC2072mF instanceof C1731iF)) {
            x1f.a();
            return;
        }
        consumer.accept(abstractC2072mF);
    }
}
