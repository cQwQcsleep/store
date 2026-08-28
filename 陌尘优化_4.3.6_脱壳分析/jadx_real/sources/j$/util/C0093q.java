package j$.util;

import java.util.NoSuchElementException;

/* renamed from: j$.util.q, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final class C0093q {
    private static final C0093q c = new C0093q();
    private final boolean a;
    private final long b;

    private C0093q() {
        this.a = false;
        this.b = 0L;
    }

    public static C0093q a() {
        return c;
    }

    private C0093q(long j) {
        this.a = true;
        this.b = j;
    }

    public static C0093q d(long j) {
        return new C0093q(j);
    }

    public final long b() {
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
        if (!(obj instanceof C0093q)) {
            return false;
        }
        C0093q c0093q = (C0093q) obj;
        boolean z = this.a;
        if (z && c0093q.a) {
            if (this.b == c0093q.b) {
                return true;
            }
        } else if (z == c0093q.a) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (!this.a) {
            return 0;
        }
        long j = this.b;
        return (int) (j ^ (j >>> 32));
    }

    public final String toString() {
        if (this.a) {
            return "OptionalLong[" + this.b + "]";
        }
        return "OptionalLong.empty";
    }
}
