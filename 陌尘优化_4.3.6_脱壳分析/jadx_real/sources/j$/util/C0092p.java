package j$.util;

import java.util.NoSuchElementException;

/* renamed from: j$.util.p, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final class C0092p {
    private static final C0092p c = new C0092p();
    private final boolean a;
    private final int b;

    private C0092p() {
        this.a = false;
        this.b = 0;
    }

    public static C0092p a() {
        return c;
    }

    private C0092p(int i) {
        this.a = true;
        this.b = i;
    }

    public static C0092p d(int i) {
        return new C0092p(i);
    }

    public final int b() {
        if (!this.a) {
            throw new NoSuchElementException("No value present");
        }
        return this.b;
    }

    public final boolean c() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0092p)) {
            return false;
        }
        C0092p c0092p = (C0092p) obj;
        boolean z = this.a;
        if (z && c0092p.a) {
            if (this.b == c0092p.b) {
                return true;
            }
        } else if (z == c0092p.a) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (this.a) {
            return this.b;
        }
        return 0;
    }

    public final String toString() {
        if (this.a) {
            return "OptionalInt[" + this.b + "]";
        }
        return "OptionalInt.empty";
    }
}
