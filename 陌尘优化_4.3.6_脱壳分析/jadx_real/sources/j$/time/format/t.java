package j$.time.format;

import j$.util.concurrent.ConcurrentHashMap;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class t {
    public static final t a = new t();

    public final int hashCode() {
        return 182;
    }

    static {
        new ConcurrentHashMap(16, 2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof t)) {
            return false;
        }
        ((t) obj).getClass();
        return true;
    }

    public final String toString() {
        return "DecimalStyle[0+-.]";
    }
}
