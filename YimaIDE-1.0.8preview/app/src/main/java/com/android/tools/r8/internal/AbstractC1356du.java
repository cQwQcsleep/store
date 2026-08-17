package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C0585Jc;
import com.android.tools.r8.internal.C0697Nl;
import com.android.tools.r8.internal.C0957Xl;
import com.android.tools.r8.internal.C1075ac;
import com.android.tools.r8.internal.Wj0;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.du, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1356du {
    public static final /* synthetic */ boolean b = true;
    public final com.android.tools.r8.graph.F2 a;

    public AbstractC1356du(com.android.tools.r8.graph.F2 f2) {
        if (b || f2 != null) {
            this.a = f2;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static C1075ac a(com.android.tools.r8.graph.I2 i2) {
        return (C1075ac) C0470Er.a((Object) i2, new Function() { // from class: yng
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C1075ac((I2) obj);
            }
        });
    }

    public static C0585Jc b(com.android.tools.r8.graph.I2 i2) {
        return (C0585Jc) C0470Er.a((Object) i2, new Function() { // from class: wng
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C0585Jc((I2) obj);
            }
        });
    }

    public static C0957Xl c(com.android.tools.r8.graph.I2 i2) {
        return (C0957Xl) C0470Er.a((Object) i2, new Function() { // from class: zng
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C0957Xl((I2) obj);
            }
        });
    }

    public AbstractC1528fu a() {
        return null;
    }

    public boolean b() {
        return false;
    }

    public static C0697Nl a(AbstractC0287r2 abstractC0287r2) {
        return (C0697Nl) C0470Er.a((Object) abstractC0287r2, new Function() { // from class: xng
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C0697Nl((AbstractC0287r2) obj);
            }
        });
    }

    public static Wj0 b(AbstractC0287r2 abstractC0287r2) {
        return (Wj0) C0470Er.a((Object) abstractC0287r2, new Function() { // from class: aog
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new Wj0((AbstractC0287r2) obj);
            }
        });
    }
}
