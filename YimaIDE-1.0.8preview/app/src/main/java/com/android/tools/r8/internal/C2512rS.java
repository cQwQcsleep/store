package com.android.tools.r8.internal;

import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rS, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2512rS {
    public static final /* synthetic */ boolean e = true;
    public AbstractC1120b40 a;
    public AbstractC1120b40 b;
    public AbstractC1120b40 c;
    public AbstractC1120b40 d;

    public final AbstractC1120b40 a(C2427qS c2427qS, BiFunction biFunction) {
        AbstractC1120b40 abstractC1120b40;
        AbstractC1120b40 abstractC1120b41;
        if (c2427qS == C2427qS.h()) {
            abstractC1120b40 = this.a;
        } else if (c2427qS == C2427qS.c()) {
            abstractC1120b40 = this.b;
        } else if (c2427qS == C2427qS.b()) {
            abstractC1120b40 = this.c;
        } else {
            if (!e && c2427qS != C2427qS.a()) {
                x1f.a();
                return null;
            }
            abstractC1120b40 = this.d;
        }
        if (abstractC1120b40 != null) {
            return abstractC1120b40;
        }
        synchronized (this) {
            try {
                if (c2427qS == C2427qS.h()) {
                    abstractC1120b41 = this.a;
                } else if (c2427qS == C2427qS.c()) {
                    abstractC1120b41 = this.b;
                } else if (c2427qS == C2427qS.b()) {
                    abstractC1120b41 = this.c;
                } else {
                    if (!e && c2427qS != C2427qS.a()) {
                        throw new AssertionError();
                    }
                    abstractC1120b41 = this.d;
                }
                if (abstractC1120b41 != null) {
                    return abstractC1120b41;
                }
                AbstractC1120b40 abstractC1120b42 = (AbstractC1120b40) biFunction.apply(c2427qS, this);
                boolean z = e;
                if (!z && abstractC1120b42 == null) {
                    throw new AssertionError();
                }
                if (c2427qS == C2427qS.h()) {
                    this.a = abstractC1120b42;
                } else if (c2427qS == C2427qS.c()) {
                    this.b = abstractC1120b42;
                } else if (c2427qS == C2427qS.b()) {
                    this.c = abstractC1120b42;
                } else {
                    if (!z && c2427qS != C2427qS.a()) {
                        throw new AssertionError();
                    }
                    this.d = abstractC1120b42;
                }
                return abstractC1120b42;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static AbstractC1120b40 a(C2427qS c2427qS, Function function) {
        C2512rS c2512rS = new C2512rS();
        AbstractC1120b40 abstractC1120b40 = (AbstractC1120b40) function.apply(c2512rS);
        if (c2427qS == C2427qS.h()) {
            c2512rS.a = abstractC1120b40;
            return abstractC1120b40;
        }
        if (c2427qS == C2427qS.c()) {
            c2512rS.b = abstractC1120b40;
            return abstractC1120b40;
        }
        if (c2427qS == C2427qS.b()) {
            c2512rS.c = abstractC1120b40;
            return abstractC1120b40;
        }
        if (e || c2427qS == C2427qS.a()) {
            c2512rS.d = abstractC1120b40;
            return abstractC1120b40;
        }
        x1f.a();
        return null;
    }
}
