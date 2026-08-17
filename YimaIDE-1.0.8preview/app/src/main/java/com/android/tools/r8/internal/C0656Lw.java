package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C0656Lw;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0656Lw extends AbstractC1751iZ {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0656Lw(AbstractC3114yW abstractC3114yW) {
        super(new C2773uW(abstractC3114yW, "instance-of"));
        abstractC3114yW.getClass();
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final boolean a(Object obj, String str, Object obj2, Consumer consumer) {
        C2416qG c2416qG;
        C2416qG c2416qG2;
        int iOrdinal = ((EnumC0631Kw) obj).ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                C1476fH.a();
                C1476fH c1476fHA = C1476fH.a((String) obj2);
                if (c1476fHA.d()) {
                    defpackage.l0.a("Invalid instance-of pattern matching any class exclusive. This pattern matches nothing.");
                    return false;
                }
                c2416qG = new C2416qG(c1476fHA, false);
            } else if (iOrdinal == 2) {
                C1476fH.a();
                C1476fH c1476fHA2 = C1476fH.a(((C3050xi0) obj2).a());
                if (c1476fHA2.d()) {
                    c2416qG2 = C2416qG.c;
                } else {
                    c2416qG = new C2416qG(c1476fHA2, true);
                }
            } else if (iOrdinal != 3) {
                c2416qG2 = null;
            } else {
                C1476fH.a();
                C1476fH c1476fHA3 = C1476fH.a(((C3050xi0) obj2).a());
                if (c1476fHA3.d()) {
                    defpackage.l0.a("Invalid instance-of pattern matching any class exclusive. This pattern matches nothing.");
                    return false;
                }
                c2416qG = new C2416qG(c1476fHA3, false);
            }
            c2416qG2 = c2416qG;
        } else {
            C1476fH.a();
            C1476fH c1476fHA4 = C1476fH.a((String) obj2);
            if (c1476fHA4.d()) {
                c2416qG2 = C2416qG.c;
            } else {
                c2416qG = new C2416qG(c1476fHA4, true);
                c2416qG2 = c2416qG;
            }
        }
        if (c2416qG2 == null) {
            return false;
        }
        consumer.accept(c2416qG2);
        return true;
    }

    public static void a(Consumer consumer, T6 t6, C0688Nc c0688Nc) {
        C2416qG c2416qG;
        C1476fH.a();
        boolean zBooleanValue = ((Boolean) t6.a(Boolean.TRUE)).booleanValue();
        C1476fH c1476fH = (C1476fH) c0688Nc.a((Object) C1476fH.a());
        if (!c1476fH.d()) {
            c2416qG = new C2416qG(c1476fH, zBooleanValue);
        } else if (zBooleanValue) {
            c2416qG = C2416qG.c;
        } else {
            defpackage.l0.a("Invalid instance-of pattern matching any class exclusive. This pattern matches nothing.");
            return;
        }
        consumer.accept(c2416qG);
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final C2431qW a(EnumC0631Kw enumC0631Kw, String str, String str2, final Consumer consumer) {
        if (!enumC0631Kw.equals(EnumC0631Kw.f)) {
            return null;
        }
        C2516rW c2516rW = new C2516rW(this.a.b(str), str2);
        final T6 t6 = new T6(c2516rW);
        t6.a("inclusive", S6.b);
        final C0688Nc c0688Nc = new C0688Nc(c2516rW);
        c0688Nc.a("classNamePattern", EnumC0662Mc.b);
        return new C2431qW(c2516rW, AbstractC0551Hu.a(t6, c0688Nc), new Runnable() { // from class: uj9
            @Override // java.lang.Runnable
            public final void run() {
                C0656Lw.a(consumer, t6, c0688Nc);
            }
        });
    }
}
