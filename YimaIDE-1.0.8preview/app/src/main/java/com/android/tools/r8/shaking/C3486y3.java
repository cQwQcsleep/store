package com.android.tools.r8.shaking;

import com.android.tools.r8.shaking.S3;
import defpackage.lua;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.y3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3486y3 extends A3 {
    public final String c;
    public final List d;

    public C3486y3(T2.a aVar) {
        this.c = aVar.a;
        this.d = aVar.b;
    }

    @Override // com.android.tools.r8.shaking.A3
    public final boolean a(String str) {
        boolean zA = A3.a(this.c, 0, str, 0, this.d, 0);
        if (!zA) {
            this.d.forEach(new lua());
        }
        return zA;
    }

    @Override // com.android.tools.r8.shaking.A3
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C3486y3 b() {
        return new C3486y3(new T2.a(this.c, (List) this.d.stream().map(new Function() { // from class: lui
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((S3) obj).f();
            }
        }).collect(Collectors.toList())));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C3486y3) && this.c.equals(((C3486y3) obj).c);
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return this.c;
    }

    @Override // com.android.tools.r8.shaking.A3
    public final Iterable a() {
        return this.d;
    }
}
