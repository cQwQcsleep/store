package com.reandroid.arsc.value;

import com.reandroid.utils.StringsUtil;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'INTEGER' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:422)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:351)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class AttributeDataFormat {
    private static final /* synthetic */ AttributeDataFormat[] $VALUES;
    public static final AttributeDataFormat ANY;
    private static final AttributeDataFormat[] BAG_TYPES;
    public static final AttributeDataFormat BOOL;
    public static final AttributeDataFormat COLOR;
    public static final AttributeDataFormat DIMENSION;
    public static final AttributeDataFormat ENUM;
    public static final AttributeDataFormat FLAG;
    public static final AttributeDataFormat FLOAT;
    public static final AttributeDataFormat FRACTION;
    public static final AttributeDataFormat INTEGER;
    public static final AttributeDataFormat REFERENCE;
    public static final AttributeDataFormat STRING;
    private static final AttributeDataFormat[] VALUE_TYPES;
    private final int mask;
    private final ValueType[] valueTypes;

    static {
        AttributeDataFormat attributeDataFormat = new AttributeDataFormat("REFERENCE", 0, 1, new ValueType[]{ValueType.REFERENCE, ValueType.ATTRIBUTE, ValueType.DYNAMIC_REFERENCE, ValueType.DYNAMIC_ATTRIBUTE, ValueType.NULL});
        REFERENCE = attributeDataFormat;
        ValueType valueType = ValueType.DEC;
        ValueType valueType2 = ValueType.HEX;
        AttributeDataFormat attributeDataFormat2 = new AttributeDataFormat("INTEGER", 1, 4, new ValueType[]{valueType, valueType2});
        INTEGER = attributeDataFormat2;
        AttributeDataFormat attributeDataFormat3 = new AttributeDataFormat("BOOL", 2, 8, new ValueType[]{ValueType.BOOLEAN});
        BOOL = attributeDataFormat3;
        AttributeDataFormat attributeDataFormat4 = new AttributeDataFormat("COLOR", 3, 16, new ValueType[]{ValueType.COLOR_ARGB8, ValueType.COLOR_RGB8, ValueType.COLOR_RGB4, ValueType.COLOR_ARGB4});
        COLOR = attributeDataFormat4;
        AttributeDataFormat attributeDataFormat5 = new AttributeDataFormat("FLOAT", 4, 32, new ValueType[]{ValueType.FLOAT});
        FLOAT = attributeDataFormat5;
        AttributeDataFormat attributeDataFormat6 = new AttributeDataFormat("DIMENSION", 5, 64, new ValueType[]{ValueType.DIMENSION});
        DIMENSION = attributeDataFormat6;
        AttributeDataFormat attributeDataFormat7 = new AttributeDataFormat("FRACTION", 6, 128, new ValueType[]{ValueType.FRACTION});
        FRACTION = attributeDataFormat7;
        AttributeDataFormat attributeDataFormat8 = new AttributeDataFormat("ANY", 7, 65535, (ValueType[]) ValueType.values().clone());
        ANY = attributeDataFormat8;
        AttributeDataFormat attributeDataFormat9 = new AttributeDataFormat("ENUM", 8, 65536, new ValueType[]{valueType, valueType2});
        ENUM = attributeDataFormat9;
        AttributeDataFormat attributeDataFormat10 = new AttributeDataFormat("FLAG", 9, 131072, new ValueType[]{valueType2, valueType});
        FLAG = attributeDataFormat10;
        AttributeDataFormat attributeDataFormat11 = new AttributeDataFormat("STRING", 10, 2, new ValueType[]{ValueType.STRING});
        STRING = attributeDataFormat11;
        $VALUES = new AttributeDataFormat[]{attributeDataFormat, attributeDataFormat2, attributeDataFormat3, attributeDataFormat4, attributeDataFormat5, attributeDataFormat6, attributeDataFormat7, attributeDataFormat8, attributeDataFormat9, attributeDataFormat10, attributeDataFormat11};
        VALUE_TYPES = new AttributeDataFormat[]{attributeDataFormat, attributeDataFormat11, attributeDataFormat2, attributeDataFormat3, attributeDataFormat4, attributeDataFormat5, attributeDataFormat6, attributeDataFormat7, attributeDataFormat8};
        BAG_TYPES = new AttributeDataFormat[]{attributeDataFormat9, attributeDataFormat10};
    }

    private AttributeDataFormat(String str, int i, int i2, ValueType[] valueTypeArr) {
        super(str, i);
        this.mask = i2;
        this.valueTypes = valueTypeArr;
    }

    public static boolean contains(AttributeDataFormat[] attributeDataFormatArr, ValueType valueType) {
        if (attributeDataFormatArr != null && valueType != null) {
            for (AttributeDataFormat attributeDataFormat : attributeDataFormatArr) {
                if (attributeDataFormat != null && attributeDataFormat.contains(valueType)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static AttributeDataFormat[] decodeValueTypes(int i) {
        AttributeDataFormat[] attributeDataFormatArr = VALUE_TYPES;
        AttributeDataFormat[] attributeDataFormatArr2 = new AttributeDataFormat[attributeDataFormatArr.length];
        int i2 = 0;
        for (AttributeDataFormat attributeDataFormat : attributeDataFormatArr) {
            int mask = attributeDataFormat.getMask();
            if (mask == i) {
                return new AttributeDataFormat[]{attributeDataFormat};
            }
            if (attributeDataFormat != ANY && (i & mask) == mask) {
                attributeDataFormatArr2[i2] = attributeDataFormat;
                i2++;
            }
        }
        if (i2 == 0) {
            return null;
        }
        AttributeDataFormat[] attributeDataFormatArr3 = new AttributeDataFormat[i2];
        System.arraycopy(attributeDataFormatArr2, 0, attributeDataFormatArr3, 0, i2);
        return attributeDataFormatArr3;
    }

    public static AttributeDataFormat fromBagTypeName(String str) {
        if (str == null) {
            return null;
        }
        String upperCase = StringsUtil.toUpperCase(str.trim());
        for (AttributeDataFormat attributeDataFormat : BAG_TYPES) {
            if (upperCase.equals(attributeDataFormat.name())) {
                return attributeDataFormat;
            }
        }
        return null;
    }

    public static AttributeDataFormat fromValueTypeName(String str) {
        if (str == null) {
            return null;
        }
        String upperCase = StringsUtil.toUpperCase(str.trim());
        for (AttributeDataFormat attributeDataFormat : VALUE_TYPES) {
            if (upperCase.equals(attributeDataFormat.name())) {
                return attributeDataFormat;
            }
        }
        return null;
    }

    public static ValueType[] getExpectedValueTypes(String str) {
        AttributeDataFormat attributeDataFormatFromValueTypeName = fromValueTypeName(str);
        if (attributeDataFormatFromValueTypeName != null) {
            return attributeDataFormatFromValueTypeName.valueTypes();
        }
        return null;
    }

    public static AttributeDataFormat[] parseValueTypes(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.trim().split("\\s*\\|\\s*");
        AttributeDataFormat[] attributeDataFormatArr = new AttributeDataFormat[VALUE_TYPES.length];
        int i = 0;
        for (String str2 : strArrSplit) {
            AttributeDataFormat attributeDataFormatFromValueTypeName = fromValueTypeName(str2);
            if (attributeDataFormatFromValueTypeName != null) {
                attributeDataFormatArr[i] = attributeDataFormatFromValueTypeName;
                i++;
            }
        }
        if (i == 0) {
            return null;
        }
        AttributeDataFormat[] attributeDataFormatArr2 = new AttributeDataFormat[i];
        System.arraycopy(attributeDataFormatArr, 0, attributeDataFormatArr2, 0, i);
        return attributeDataFormatArr2;
    }

    public static int sum(AttributeDataFormat[] attributeDataFormatArr) {
        if (attributeDataFormatArr == null) {
            return 0;
        }
        int mask = 0;
        for (AttributeDataFormat attributeDataFormat : attributeDataFormatArr) {
            if (attributeDataFormat != null) {
                mask |= attributeDataFormat.getMask();
            }
        }
        return mask;
    }

    public static String toString(AttributeDataFormat[] attributeDataFormatArr) {
        if (attributeDataFormatArr == null || attributeDataFormatArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean z = false;
        for (AttributeDataFormat attributeDataFormat : attributeDataFormatArr) {
            if (attributeDataFormat != ENUM && attributeDataFormat != FLAG) {
                AttributeDataFormat attributeDataFormat2 = ANY;
                if (attributeDataFormat == attributeDataFormat2) {
                    return attributeDataFormat2.getName();
                }
                int mask = attributeDataFormat.getMask();
                if ((i & mask) != mask) {
                    if (z) {
                        sb.append('|');
                    }
                    sb.append(attributeDataFormat.getName());
                    i |= mask;
                    z = true;
                }
            }
        }
        return sb.toString();
    }

    public static String toStringValueTypes(int i) {
        return toString(decodeValueTypes(i));
    }

    public static AttributeDataFormat typeOfBag(int i) {
        for (AttributeDataFormat attributeDataFormat : BAG_TYPES) {
            if (attributeDataFormat.matches(i)) {
                return attributeDataFormat;
            }
        }
        return null;
    }

    public static AttributeDataFormat valueOf(int i) {
        for (AttributeDataFormat attributeDataFormat : VALUE_TYPES) {
            if (attributeDataFormat.getMask() == i) {
                return attributeDataFormat;
            }
        }
        return null;
    }

    public static AttributeDataFormat[] values() {
        return (AttributeDataFormat[]) $VALUES.clone();
    }

    public int getMask() {
        return this.mask;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public ValueType[] getValueTypes() {
        return (ValueType[]) this.valueTypes.clone();
    }

    public boolean matches(int i) {
        int i2 = this.mask;
        return (i & i2) == i2;
    }

    public ValueType[] valueTypes() {
        return this.valueTypes;
    }

    public static AttributeDataFormat valueOf(String str) {
        return (AttributeDataFormat) Enum.valueOf(AttributeDataFormat.class, str);
    }

    public boolean contains(ValueType valueType) {
        for (ValueType valueType2 : this.valueTypes) {
            if (valueType == valueType2) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Enum
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append('{');
        ValueType[] valueTypeArr = this.valueTypes;
        for (int i = 0; i < valueTypeArr.length; i++) {
            if (i != 0) {
                sb.append(',');
            }
            sb.append(valueTypeArr[i]);
        }
        sb.append('}');
        return sb.toString();
    }
}
