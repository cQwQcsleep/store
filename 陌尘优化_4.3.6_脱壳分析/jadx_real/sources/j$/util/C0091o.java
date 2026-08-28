package j$.util;

import java.util.NoSuchElementException;

/* renamed from: j$.util.o, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final class C0091o {
    private static final C0091o c = new C0091o();
    private final boolean a;
    private final double b;

    private C0091o() {
        this.a = false;
        this.b = Double.NaN;
    }

    public static C0091o a() {
        return c;
    }

    private C0091o(double d) {
        this.a = true;
        this.b = d;
    }

    public static C0091o d(double d) {
        return new C0091o(d);
    }

    public final double b() {
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
        if (!(obj instanceof C0091o)) {
            return false;
        }
        C0091o c0091o = (C0091o) obj;
        boolean z = this.a;
        if (z && c0091o.a) {
            if (Double.compare(this.b, c0091o.b) == 0) {
                return true;
            }
        } else if (z == c0091o.a) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (!this.a) {
            return 0;
        }
        long jDoubleToLongBits = Double.doubleToLongBits(this.b);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    public final String toString() {
        if (this.a) {
            return "OptionalDouble[" + this.b + "]";
        }
        return "OptionalDouble.empty";
    }
}
