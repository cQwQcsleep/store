package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1312dO;
import com.android.tools.r8.internal.EnumC1229cO;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1312dO extends C1397eO {
    public final byte[] c;
    public final ConcurrentHashMap d;

    public C1312dO(byte[] bArr) {
        super(null, null);
        this.d = new ConcurrentHashMap();
        this.c = bArr;
    }

    public final C1397eO a(final EnumC1229cO enumC1229cO) {
        return (C1397eO) this.d.computeIfAbsent(Integer.valueOf(enumC1229cO.b), new Function() { // from class: lmg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(enumC1229cO, (Integer) obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.C1397eO
    public final Collection b() {
        C1397eO c1397eOA = a(EnumC1229cO.d);
        if (c1397eOA == null) {
            return null;
        }
        return c1397eOA.b();
    }

    @Override // com.android.tools.r8.internal.C1397eO
    public final boolean c() {
        C1397eO c1397eOA = a(EnumC1229cO.e);
        return c1397eOA != null && c1397eOA.c();
    }

    @Override // com.android.tools.r8.internal.C1397eO
    public final boolean d() {
        C1397eO c1397eOA = a(EnumC1229cO.d);
        return c1397eOA != null && c1397eOA.d();
    }

    @Override // com.android.tools.r8.internal.C1397eO
    public final Set a() {
        C1397eO c1397eOA = a(EnumC1229cO.e);
        if (c1397eOA == null) {
            return null;
        }
        return c1397eOA.a();
    }

    public static /* synthetic */ boolean a(EnumC1229cO enumC1229cO, EnumC1229cO enumC1229cO2) {
        return enumC1229cO2 == enumC1229cO;
    }

    public final /* synthetic */ C1397eO a(final EnumC1229cO enumC1229cO, Integer num) {
        return C1397eO.a(this.c, new Predicate() { // from class: mmg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C1312dO.a(enumC1229cO, (EnumC1229cO) obj);
            }
        });
    }
}
