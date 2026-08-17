package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2587sH;
import com.android.tools.r8.internal.C0688Nc;
import com.android.tools.r8.internal.C1306dH;
import com.android.tools.r8.internal.C1476fH;
import com.android.tools.r8.internal.CE;
import com.reandroid.arsc.chunk.TypeBlock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Nc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0688Nc extends AbstractC1751iZ {
    public C0688Nc(AbstractC3114yW abstractC3114yW) {
        super(abstractC3114yW);
    }

    public static /* synthetic */ void a(C0688Nc c0688Nc, C1927kd c1927kd, C1067aW c1067aW, C2516rW c2516rW, Consumer consumer) {
        if (!c0688Nc.a()) {
            consumer.accept(C1476fH.b().a((YG) c1067aW.a(UG.a)).a((AbstractC2843vH) c1927kd.a(C2672tH.a)).a());
        } else {
            if (c1927kd.a() || c1067aW.a()) {
                c2516rW.a("Cannot specify both the full class name and its ".concat(c1927kd.a() ? "simple name" : "package"));
                throw null;
            }
            consumer.accept((C1476fH) c0688Nc.getValue());
        }
    }

    public final void b(Consumer consumer, String str, AbstractC2587sH abstractC2587sH) {
        consumer.accept(a(abstractC2587sH, this.a.b(str)));
    }

    public final void a(Consumer consumer, String str, AbstractC2587sH abstractC2587sH) {
        consumer.accept(a(abstractC2587sH, this.a.b(str)));
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final boolean a(EnumC0662Mc enumC0662Mc, final String str, Object obj, final Consumer consumer) {
        int iOrdinal = enumC0662Mc.ordinal();
        if (iOrdinal == 1) {
            return new C2967wj0(this.a).a(EnumC2881vj0.c, str, obj, new Consumer() { // from class: ifa
                @Override // java.util.function.Consumer
                public final void accept(Object obj2) {
                    this.b.a(consumer, str, (AbstractC2587sH) obj2);
                }
            });
        }
        if (iOrdinal != 2) {
            return false;
        }
        return new C2967wj0(this.a).a(EnumC2881vj0.d, str, obj, new Consumer() { // from class: hfa
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                this.b.b(consumer, str, (AbstractC2587sH) obj2);
            }
        });
    }

    public static C1476fH a(AbstractC2587sH abstractC2587sH, final C3030xW c3030xW) {
        return (C1476fH) abstractC2587sH.a(new Supplier() { // from class: kfa
            @Override // java.util.function.Supplier
            public final Object get() {
                return C1476fH.a();
            }
        }, new Function() { // from class: lfa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0688Nc.a(c3030xW, (C1306dH) obj);
            }
        }, new Function() { // from class: mfa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0688Nc.a(c3030xW, (CE) obj);
            }
        }, new Function() { // from class: nfa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0688Nc.a((C1476fH) obj);
            }
        });
    }

    public static C1476fH a(C3030xW c3030xW, C1306dH c1306dH) {
        c3030xW.getClass();
        throw new C3096yE(c3030xW, "Invalid use of primitive type where class type was expected");
    }

    public static C1476fH a(C3030xW c3030xW, CE ce) {
        c3030xW.getClass();
        throw new C3096yE(c3030xW, "Invalid use of array type where class type was expected");
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final C2431qW a(EnumC0662Mc enumC0662Mc, String str, String str2, final Consumer consumer) {
        if (enumC0662Mc.ordinal() != 0) {
            return null;
        }
        final C2516rW c2516rW = new C2516rW(this.a.b(str), str2);
        final C0688Nc c0688Nc = new C0688Nc(c2516rW);
        final C1067aW c1067aW = new C1067aW(c2516rW);
        final C1927kd c1927kd = new C1927kd(c2516rW);
        c0688Nc.a(TypeBlock.NAME_name, EnumC0662Mc.c);
        c0688Nc.a("constant", EnumC0662Mc.d);
        c1067aW.a("packageName", ZV.b);
        c1927kd.a("simpleName", EnumC1842jd.b);
        return new C2431qW(c2516rW, AbstractC0551Hu.a(c0688Nc, c1067aW, c1927kd), new Runnable() { // from class: jfa
            @Override // java.lang.Runnable
            public final void run() {
                C0688Nc.a(this.b, c1927kd, c1067aW, c2516rW, consumer);
            }
        });
    }

    public static /* synthetic */ C1476fH a(C1476fH c1476fH) {
        return c1476fH;
    }
}
