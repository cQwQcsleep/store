package j$.util;

import java.util.NoSuchElementException;

/* renamed from: j$.util.n, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final class C0090n {
    private static final C0090n b = new C0090n();
    private final Object a;

    private C0090n() {
        this.a = null;
    }

    public static C0090n a() {
        return b;
    }

    private C0090n(Object obj) {
        this.a = Objects.requireNonNull(obj);
    }

    public static C0090n d(Object obj) {
        return new C0090n(obj);
    }

    public final Object b() {
        Object obj = this.a;
        if (obj != null) {
            return obj;
        }
        throw new NoSuchElementException("No value present");
    }

    public final boolean c() {
        return this.a != null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0090n) {
            return Objects.equals(this.a, ((C0090n) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.a);
    }

    public final String toString() {
        Object obj = this.a;
        if (obj != null) {
            return String.format("Optional[%s]", obj);
        }
        return "Optional.empty";
    }
}
