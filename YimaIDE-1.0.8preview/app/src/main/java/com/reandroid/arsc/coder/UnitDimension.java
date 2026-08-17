package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class UnitDimension extends ComplexUnit {
    public static final UnitDimension DIP;
    public static final UnitDimension DP;
    public static final UnitDimension IN;
    public static final UnitDimension MM;
    public static final UnitDimension PT;
    public static final UnitDimension PX;
    public static final UnitDimension SP;
    private static final UnitDimension[] VALUES;

    static {
        UnitDimension unitDimension = new UnitDimension(0, "px");
        PX = unitDimension;
        UnitDimension unitDimension2 = new UnitDimension(1, "dp");
        DP = unitDimension2;
        UnitDimension unitDimension3 = new UnitDimension(1, "dip");
        DIP = unitDimension3;
        UnitDimension unitDimension4 = new UnitDimension(2, "sp");
        SP = unitDimension4;
        UnitDimension unitDimension5 = new UnitDimension(3, "pt");
        PT = unitDimension5;
        UnitDimension unitDimension6 = new UnitDimension(4, "in");
        IN = unitDimension6;
        UnitDimension unitDimension7 = new UnitDimension(5, "mm");
        MM = unitDimension7;
        VALUES = new UnitDimension[]{unitDimension, unitDimension2, unitDimension3, unitDimension4, unitDimension5, unitDimension6, unitDimension7};
    }

    private UnitDimension(int i, String str) {
        super(i, str);
    }

    public static UnitDimension fromPostfix(String str) {
        for (UnitDimension unitDimension : VALUES) {
            if (unitDimension.hasPostfix(str)) {
                return unitDimension;
            }
        }
        return null;
    }

    public static UnitDimension valueOf(String str) {
        if (str == null) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        for (UnitDimension unitDimension : VALUES) {
            if (lowerCase.equals(unitDimension.getSymbol())) {
                return unitDimension;
            }
        }
        return null;
    }

    public static UnitDimension[] values() {
        return (UnitDimension[]) VALUES.clone();
    }

    @Override // com.reandroid.arsc.coder.ComplexUnit
    public ValueType getValueType() {
        return ValueType.DIMENSION;
    }

    public static UnitDimension valueOf(int i) {
        for (UnitDimension unitDimension : VALUES) {
            if (i == unitDimension.getFlag()) {
                return unitDimension;
            }
        }
        return null;
    }
}
