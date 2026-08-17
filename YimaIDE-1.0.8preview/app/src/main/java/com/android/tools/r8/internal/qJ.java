package com.android.tools.r8.internal;

import java.math.BigDecimal;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class qJ extends Number {
    public final String b;

    public qJ(String str) {
        this.b = str;
    }

    @Override // java.lang.Number
    public final double doubleValue() {
        return Double.parseDouble(this.b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof qJ)) {
            return false;
        }
        String str = this.b;
        String str2 = ((qJ) obj).b;
        return str == str2 || str.equals(str2);
    }

    @Override // java.lang.Number
    public final float floatValue() {
        return Float.parseFloat(this.b);
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    @Override // java.lang.Number
    public final int intValue() {
        try {
            try {
                return Integer.parseInt(this.b);
            } catch (NumberFormatException unused) {
                return (int) Long.parseLong(this.b);
            }
        } catch (NumberFormatException unused2) {
            return new BigDecimal(this.b).intValue();
        }
    }

    @Override // java.lang.Number
    public final long longValue() {
        try {
            return Long.parseLong(this.b);
        } catch (NumberFormatException unused) {
            return new BigDecimal(this.b).longValue();
        }
    }

    public final String toString() {
        return this.b;
    }
}
