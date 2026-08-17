package com.reandroid.arsc.value.plurals;

import com.reandroid.arsc.value.ResValueMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public enum PluralsQuantity {
    OTHER(4),
    ZERO(5),
    ONE(6),
    TWO(7),
    FEW(8),
    MANY(9);

    private final short mId;

    PluralsQuantity(short s) {
        this.mId = s;
    }

    public static PluralsQuantity value(String str) {
        if (str == null) {
            return null;
        }
        String upperCase = str.trim().toUpperCase();
        for (PluralsQuantity pluralsQuantity : values()) {
            if (upperCase.equals(pluralsQuantity.name())) {
                return pluralsQuantity;
            }
        }
        return null;
    }

    public static PluralsQuantity valueOf(short s) {
        for (PluralsQuantity pluralsQuantity : values()) {
            if (s == pluralsQuantity.mId) {
                return pluralsQuantity;
            }
        }
        return null;
    }

    public short getId() {
        return this.mId;
    }

    @Override // java.lang.Enum
    public String toString() {
        return name().toLowerCase();
    }

    public static PluralsQuantity valueOf(ResValueMap resValueMap) {
        if (resValueMap == null) {
            return null;
        }
        return valueOf((short) (resValueMap.getNameId() & 65535));
    }
}
