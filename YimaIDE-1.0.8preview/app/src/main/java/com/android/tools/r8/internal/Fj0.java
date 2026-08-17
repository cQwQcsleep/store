package com.android.tools.r8.internal;

import java.lang.reflect.Type;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Fj0 {
    public final Class a;
    public final Type b;
    public final int c;

    public Fj0(Type type) {
        Objects.requireNonNull(type);
        Type typeA = AbstractC1278d.a(type);
        this.b = typeA;
        this.a = AbstractC1278d.b(typeA);
        this.c = typeA.hashCode();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Fj0) && AbstractC1278d.a(this.b, ((Fj0) obj).b);
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return AbstractC1278d.c(this.b);
    }
}
