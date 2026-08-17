package com.android.tools.r8.internal;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m7, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2061m7<T> {
    public Object a;

    public AbstractC2061m7(Object obj) {
        a(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean a(Predicate predicate) {
        return b() && predicate.test(a());
    }

    public boolean b() {
        return this.a != null;
    }

    public final boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.a, ((AbstractC2061m7) obj).a);
    }

    public final int hashCode() {
        return Objects.hashCode(this.a);
    }

    public AbstractC2061m7() {
    }

    public Object a(Supplier supplier) {
        if (!b()) {
            a(supplier.get());
        }
        return this.a;
    }

    public Object a() {
        return this.a;
    }

    public void a(Object obj) {
        this.a = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(Consumer consumer) {
        if (b()) {
            consumer.accept(a());
        }
    }
}
