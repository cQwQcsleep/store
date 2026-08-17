package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC3114yW;
import com.android.tools.r8.internal.E2;
import com.android.tools.r8.internal.G2;
import com.reandroid.arsc.chunk.TypeBlock;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E2 extends AbstractC0927Wh {
    public final C0688Nc a;
    public final M3 b;
    public final AbstractC0551Hu c;

    public E2(C2516rW c2516rW) {
        C0688Nc c0688Nc = new C0688Nc(c2516rW);
        this.a = c0688Nc;
        c0688Nc.a(TypeBlock.NAME_name, EnumC0662Mc.c);
        c0688Nc.a("constant", EnumC0662Mc.d);
        c0688Nc.a("namePattern", EnumC0662Mc.b);
        M3 m3 = new M3(c2516rW, new Function() { // from class: w24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new G2((AbstractC3114yW) obj);
            }
        });
        this.b = m3;
        m3.a("retention", H2.b);
        m3.e = new BiConsumer() { // from class: y24
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                E2.a((List) obj, (AbstractC3114yW) obj2);
            }
        };
        this.c = AbstractC0551Hu.a(c0688Nc, m3);
    }

    public static void a(List list, AbstractC3114yW abstractC3114yW) {
        if (list.isEmpty()) {
            abstractC3114yW.getClass();
            throw new C3096yE(abstractC3114yW, "Expected non-empty array of retention policies");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh
    public final AbstractC0551Hu b() {
        return this.c;
    }

    public final BE c() {
        if (!a()) {
            return null;
        }
        BE be = BE.c;
        final AE ae = new AE();
        if (this.b.a()) {
            ((List) this.b.getValue()).forEach(new Consumer() { // from class: a34
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ae.a((RetentionPolicy) obj);
                }
            });
        } else {
            ae.a(RetentionPolicy.RUNTIME);
        }
        ae.a = (C1476fH) this.a.a((Object) C1476fH.a());
        return ae.a();
    }
}
