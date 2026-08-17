package com.android.tools.r8.internal;

import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2155nD extends AbstractC1643hD {
    public final Object b;

    public C2155nD(Boolean bool) {
        Objects.requireNonNull(bool);
        this.b = bool;
    }

    public static boolean a(C2155nD c2155nD) {
        Object obj = c2155nD.b;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    @Override // com.android.tools.r8.internal.AbstractC1643hD
    public final int b() {
        return this.b instanceof Number ? i().intValue() : Integer.parseInt(g());
    }

    @Override // com.android.tools.r8.internal.AbstractC1643hD
    public final long e() {
        return this.b instanceof Number ? i().longValue() : Long.parseLong(g());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2155nD.class != obj.getClass()) {
            return false;
        }
        C2155nD c2155nD = (C2155nD) obj;
        if (this.b == null) {
            return c2155nD.b == null;
        }
        if (a(this) && a(c2155nD)) {
            return i().longValue() == c2155nD.i().longValue();
        }
        Object obj2 = this.b;
        if (!(obj2 instanceof Number) || !(c2155nD.b instanceof Number)) {
            return obj2.equals(c2155nD.b);
        }
        double dDoubleValue = i().doubleValue();
        double dDoubleValue2 = c2155nD.i().doubleValue();
        return dDoubleValue == dDoubleValue2 || (Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2));
    }

    @Override // com.android.tools.r8.internal.AbstractC1643hD
    public final String g() {
        Object obj = this.b;
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Number) {
            return i().toString();
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).toString();
        }
        pe1.a("Unexpected value type: ", this.b.getClass());
        return null;
    }

    public final int hashCode() {
        long jDoubleToLongBits;
        if (this.b == null) {
            return 31;
        }
        if (a(this)) {
            jDoubleToLongBits = i().longValue();
        } else {
            Object obj = this.b;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(i().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    public final Number i() {
        Object obj = this.b;
        if (obj instanceof Number) {
            return (Number) obj;
        }
        if (obj instanceof String) {
            return new qJ((String) obj);
        }
        c41.a("Primitive is neither a number nor a string");
        return null;
    }

    public C2155nD(Number number) {
        Objects.requireNonNull(number);
        this.b = number;
    }

    public C2155nD(String str) {
        Objects.requireNonNull(str);
        this.b = str;
    }

    @Override // com.android.tools.r8.internal.AbstractC1643hD
    public final boolean a() {
        Object obj = this.b;
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return Boolean.parseBoolean(g());
    }
}
