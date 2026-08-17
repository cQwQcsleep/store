package com.reandroid.arsc.coder;

import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.value.ValueType;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CommonType {
    public static final CommonType ANIM;
    public static final CommonType ANIMATOR;
    public static final CommonType BOOL;
    public static final CommonType COLOR;
    private static final String[] COMPLEX_TYPES;
    public static final CommonType DIMEN;
    public static final CommonType DRAWABLE;
    public static final CommonType FONT;
    public static final CommonType FRACTION;
    public static final CommonType ID;
    public static final CommonType INTEGER;
    public static final CommonType INTERPOLATOR;
    public static final CommonType LAYOUT;
    public static final CommonType MENU;
    public static final CommonType MIPMAP;
    public static final CommonType NAVIGATION;
    public static final CommonType RAW;
    public static final CommonType STRING;
    public static final CommonType TRANSITION;
    private static final Map<String, CommonType> TYPE_MAP;
    private static final CommonType[] VALUES;
    public static final CommonType XML;
    private final int hash;
    private final String name;
    private final ValueType[] valueTypes;

    static {
        HashMap map = new HashMap();
        TYPE_MAP = map;
        ValueType valueType = ValueType.STRING;
        CommonType commonType = new CommonType("anim", new ValueType[]{valueType});
        map.put(commonType.name, commonType);
        ANIM = commonType;
        CommonType commonType2 = new CommonType("animator", new ValueType[]{valueType});
        map.put(commonType2.name, commonType2);
        ANIMATOR = commonType2;
        ValueType valueType2 = ValueType.BOOLEAN;
        CommonType commonType3 = new CommonType("bool", new ValueType[]{valueType2});
        map.put(commonType3.name, commonType3);
        BOOL = commonType3;
        ValueType valueType3 = ValueType.COLOR_ARGB8;
        ValueType valueType4 = ValueType.COLOR_RGB8;
        ValueType valueType5 = ValueType.COLOR_RGB4;
        ValueType valueType6 = ValueType.COLOR_ARGB4;
        CommonType commonType4 = new CommonType("color", new ValueType[]{valueType3, valueType4, valueType5, valueType6});
        map.put(commonType4.name, commonType4);
        COLOR = commonType4;
        ValueType valueType7 = ValueType.DIMENSION;
        ValueType valueType8 = ValueType.FRACTION;
        ValueType valueType9 = ValueType.FLOAT;
        ValueType valueType10 = ValueType.DEC;
        CommonType commonType5 = new CommonType("dimen", new ValueType[]{valueType7, valueType8, valueType9, valueType10});
        map.put(commonType5.name, commonType5);
        DIMEN = commonType5;
        CommonType commonType6 = new CommonType("drawable", new ValueType[]{valueType3, valueType4, valueType5, valueType6, valueType});
        map.put(commonType6.name, commonType6);
        DRAWABLE = commonType6;
        CommonType commonType7 = new CommonType("fraction", new ValueType[]{valueType8});
        map.put(commonType7.name, commonType7);
        FRACTION = commonType7;
        CommonType commonType8 = new CommonType("font", new ValueType[]{valueType});
        map.put(commonType8.name, commonType8);
        FONT = commonType8;
        CommonType commonType9 = new CommonType(TypeBlock.NAME_id, new ValueType[]{valueType2, valueType});
        map.put(commonType9.name, commonType9);
        ID = commonType9;
        CommonType commonType10 = new CommonType("integer", new ValueType[]{valueType10, ValueType.HEX});
        map.put(commonType10.name, commonType10);
        INTEGER = commonType10;
        CommonType commonType11 = new CommonType("interpolator", new ValueType[]{valueType});
        map.put(commonType11.name, commonType11);
        INTERPOLATOR = commonType11;
        CommonType commonType12 = new CommonType("layout", new ValueType[]{valueType});
        map.put(commonType12.name, commonType12);
        LAYOUT = commonType12;
        CommonType commonType13 = new CommonType("menu", new ValueType[]{valueType});
        map.put(commonType13.name, commonType13);
        MENU = commonType13;
        CommonType commonType14 = new CommonType("mipmap", new ValueType[]{valueType3, valueType4, valueType5, valueType6, valueType});
        map.put(commonType14.name, commonType14);
        MIPMAP = commonType14;
        CommonType commonType15 = new CommonType("navigation", new ValueType[]{valueType});
        map.put(commonType15.name, commonType15);
        NAVIGATION = commonType15;
        CommonType commonType16 = new CommonType("raw", new ValueType[]{valueType});
        map.put(commonType16.name, commonType16);
        RAW = commonType16;
        CommonType commonType17 = new CommonType("string", new ValueType[]{valueType});
        map.put(commonType17.name, commonType17);
        STRING = commonType17;
        CommonType commonType18 = new CommonType("transition", new ValueType[]{valueType});
        map.put(commonType18.name, commonType18);
        TRANSITION = commonType18;
        CommonType commonType19 = new CommonType("xml", new ValueType[]{valueType});
        map.put(commonType19.name, commonType19);
        XML = commonType19;
        VALUES = new CommonType[]{commonType, commonType2, commonType3, commonType4, commonType5, commonType6, commonType7, commonType8, commonType9, commonType10, commonType11, commonType12, commonType13, commonType14, commonType15, commonType16, commonType17, commonType18, commonType19};
        COMPLEX_TYPES = new String[]{"attr", "array", "plurals", "style"};
    }

    private CommonType(String str, ValueType[] valueTypeArr) {
        this.name = str;
        this.valueTypes = valueTypeArr;
        int iHashCode = str.hashCode();
        this.hash = (iHashCode * 31) + iHashCode;
    }

    public static ValueType[] getExpectedTypes(String str) {
        CommonType commonTypeValueOf = valueOf(str);
        if (commonTypeValueOf != null) {
            return commonTypeValueOf.valueTypes();
        }
        return null;
    }

    public static CommonType[] getValues() {
        return (CommonType[]) VALUES.clone();
    }

    public static boolean isCommonTypeName(String str) {
        return TYPE_MAP.containsKey(str) || isComplexTypeName(str);
    }

    public static boolean isComplexTypeName(String str) {
        for (String str2 : COMPLEX_TYPES) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static CommonType valueOf(String str) {
        return TYPE_MAP.get(str);
    }

    public boolean contains(ValueType valueType) {
        for (ValueType valueType2 : this.valueTypes) {
            if (valueType2 == valueType) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return this.hash;
    }

    public boolean isDifferent(ValueType valueType) {
        if (valueType == null || valueType == ValueType.NULL || valueType.isReference()) {
            return false;
        }
        return !contains(valueType);
    }

    public String name() {
        return this.name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(", types = [");
        ValueType[] valueTypeArr = this.valueTypes;
        for (int i = 0; i < valueTypeArr.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(valueTypeArr[i]);
        }
        sb.append(']');
        return sb.toString();
    }

    public ValueType[] valueTypes() {
        return this.valueTypes;
    }
}
