package com.reandroid.arsc.value;

import com.reandroid.utils.StringsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public enum AttributeType {
    FORMATS(16777216),
    MIN(16777217),
    MAX(16777218),
    L10N(16777219),
    OTHER(16777220),
    ZERO(16777221),
    ONE(16777222),
    TWO(16777223),
    FEW(16777224),
    MANY(16777225);

    private static final AttributeType[] VALUES = values();
    private final int id;

    AttributeType(int i) {
        this.id = i;
    }

    public static AttributeType fromName(String str) {
        if (str == null) {
            return null;
        }
        String upperCase = str.toUpperCase();
        if ("FORMAT".equals(upperCase)) {
            return FORMATS;
        }
        for (AttributeType attributeType : VALUES) {
            if (upperCase.equals(attributeType.name())) {
                return attributeType;
            }
        }
        return null;
    }

    public static AttributeType valueOf(int i) {
        if (i == 0) {
            return null;
        }
        for (AttributeType attributeType : VALUES) {
            if (attributeType.getId() == i) {
                return attributeType;
            }
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return StringsUtil.toLowercase(name());
    }

    public boolean isPlural() {
        int i = this.id & 65535;
        return i >= 4 && i <= 9;
    }

    @Override // java.lang.Enum
    public String toString() {
        return getName();
    }
}
