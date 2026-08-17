package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.C1122b50;
import defpackage.chg;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1122b50 implements N5 {
    public static final /* synthetic */ boolean d = true;
    public final C1154bX a;
    public final C1154bX b;
    public final FW c;

    public C1122b50() {
        C1154bX c1154bX = new C1154bX(new Predicate() { // from class: bhg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AbstractC0890Uw) obj).F1();
            }
        });
        this.a = c1154bX;
        C1154bX c1154bX2 = new C1154bX(new chg());
        this.b = c1154bX2;
        this.c = new FW(false, c1154bX, c1154bX2);
    }

    @Override // com.android.tools.r8.internal.N5
    public final boolean a(C1650hK c1650hK) {
        C2165nN c2165nNA = this.c.a(c1650hK);
        if (c2165nNA == null) {
            return false;
        }
        C1154bX c1154bX = this.a;
        c1154bX.getClass();
        final C0719Oh c0719OhJ = ((AbstractC0890Uw) ((List) c2165nNA.a.get(c1154bX.b)).get(0)).J();
        C1154bX c1154bX2 = this.b;
        c1154bX2.getClass();
        AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) ((List) c2165nNA.a.get(c1154bX2.b)).get(0);
        AbstractC2004lX position = c0719OhJ.getPosition();
        AbstractC2004lX position2 = abstractC0890Uw.getPosition();
        position.getClass();
        if (com.android.tools.r8.utils.structural.k.a(position, position2)) {
            c1650hK.p();
            return true;
        }
        c1650hK.next();
        AbstractC0890Uw next = c1650hK.next();
        if (!d && next != abstractC0890Uw) {
            x1f.a();
            return false;
        }
        boolean z = false;
        while (next.w1() && c1650hK.hasNext()) {
            AbstractC0890Uw next2 = c1650hK.next();
            AbstractC2004lX position3 = next2.getPosition();
            AbstractC2004lX position4 = next.getPosition();
            position3.getClass();
            if (!com.android.tools.r8.utils.structural.k.a(position3, position4)) {
                break;
            }
            next.a(position);
            z = true;
            next = next2;
        }
        c1650hK.c(new Predicate() { // from class: dhg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C1122b50.a(c0719OhJ, (AbstractC0890Uw) obj);
            }
        });
        if (!z) {
            return false;
        }
        c1650hK.next();
        c1650hK.p();
        return true;
    }

    public static /* synthetic */ boolean a(C0719Oh c0719Oh, AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw == c0719Oh;
    }
}
