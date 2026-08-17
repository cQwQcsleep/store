package com.reandroid.dex.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCreator;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.ArrayKey;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodHandleKey;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.NullValueKey;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.value.AnnotationValue;
import com.reandroid.dex.value.ArrayValue;
import com.reandroid.dex.value.BooleanValue;
import com.reandroid.dex.value.ByteValue;
import com.reandroid.dex.value.CharValue;
import com.reandroid.dex.value.DexValueBlock;
import com.reandroid.dex.value.DoubleValue;
import com.reandroid.dex.value.EnumValue;
import com.reandroid.dex.value.FieldIdValue;
import com.reandroid.dex.value.FloatValue;
import com.reandroid.dex.value.IntValue;
import com.reandroid.dex.value.LongValue;
import com.reandroid.dex.value.MethodHandleValue;
import com.reandroid.dex.value.MethodIdValue;
import com.reandroid.dex.value.NullValue;
import com.reandroid.dex.value.ProtoValue;
import com.reandroid.dex.value.ShortValue;
import com.reandroid.dex.value.StringValue;
import com.reandroid.dex.value.TypeValue;
import com.reandroid.utils.HexUtil;
import com.sun.org.apache.xpath.internal.XPath;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexValueType<T extends DexValueBlock<?>> implements BlockCreator<T> {
    public static final DexValueType<AnnotationValue> ANNOTATION;
    public static final DexValueType<ArrayValue> ARRAY;
    public static final DexValueType<BooleanValue> BOOLEAN;
    public static final DexValueType<ByteValue> BYTE;
    public static final DexValueType<CharValue> CHAR;
    public static final DexValueType<DoubleValue> DOUBLE;
    public static final DexValueType<EnumValue> ENUM;
    public static final DexValueType<FieldIdValue> FIELD;
    public static final DexValueType<FloatValue> FLOAT;
    public static final DexValueType<IntValue> INT;
    public static final DexValueType<LongValue> LONG;
    public static final DexValueType<MethodIdValue> METHOD;
    public static final DexValueType<MethodHandleValue> METHOD_HANDLE;
    public static final DexValueType<NullValue> NULL;
    public static final DexValueType<ProtoValue> PROTO;
    public static final DexValueType<ShortValue> SHORT;
    public static final DexValueType<StringValue> STRING;
    public static final DexValueType<TypeValue> TYPE;
    private static final DexValueType<?>[] VALUES;
    private static final DexValueType<?>[] VALUES_COPY;
    private final BlockCreator<T> creator;
    private final int flag;
    private final String name;
    private final int size;
    private final int type;

    static {
        DexValueType<?>[] dexValueTypeArr = new DexValueType[32];
        VALUES = dexValueTypeArr;
        DexValueType<ByteValue> dexValueType = new DexValueType<>("BYTE", 0, 0, new BlockCreator() { // from class: rr3
            public final Block newInstance() {
                return new ByteValue();
            }
        });
        BYTE = dexValueType;
        dexValueTypeArr[0] = dexValueType;
        DexValueType<ShortValue> dexValueType2 = new DexValueType<>("SHORT", 2, 1, new BlockCreator() { // from class: is3
            public final Block newInstance() {
                return new ShortValue();
            }
        });
        SHORT = dexValueType2;
        dexValueTypeArr[2] = dexValueType2;
        DexValueType<CharValue> dexValueType3 = new DexValueType<>("CHAR", 3, 1, new BlockCreator() { // from class: sr3
            public final Block newInstance() {
                return new CharValue();
            }
        });
        CHAR = dexValueType3;
        dexValueTypeArr[3] = dexValueType3;
        DexValueType<IntValue> dexValueType4 = new DexValueType<>("INT", 4, 3, new BlockCreator() { // from class: tr3
            public final Block newInstance() {
                return new IntValue();
            }
        });
        INT = dexValueType4;
        dexValueTypeArr[4] = dexValueType4;
        DexValueType<LongValue> dexValueType5 = new DexValueType<>("LONG", 6, 7, new BlockCreator() { // from class: ur3
            public final Block newInstance() {
                return new LongValue();
            }
        });
        LONG = dexValueType5;
        dexValueTypeArr[6] = dexValueType5;
        DexValueType<FloatValue> dexValueType6 = new DexValueType<>("FLOAT", 16, 3, new BlockCreator() { // from class: vr3
            public final Block newInstance() {
                return new FloatValue();
            }
        });
        FLOAT = dexValueType6;
        dexValueTypeArr[16] = dexValueType6;
        DexValueType<DoubleValue> dexValueType7 = new DexValueType<>("DOUBLE", 17, 7, new BlockCreator() { // from class: wr3
            public final Block newInstance() {
                return new DoubleValue();
            }
        });
        DOUBLE = dexValueType7;
        dexValueTypeArr[17] = dexValueType7;
        DexValueType<ProtoValue> dexValueType8 = new DexValueType<>("PROTO", 21, 3, new BlockCreator() { // from class: xr3
            public final Block newInstance() {
                return new ProtoValue();
            }
        });
        PROTO = dexValueType8;
        dexValueTypeArr[21] = dexValueType8;
        DexValueType<MethodHandleValue> dexValueType9 = new DexValueType<>("METHOD_HANDLE", 22, 3, new BlockCreator() { // from class: yr3
            public final Block newInstance() {
                return new MethodHandleValue();
            }
        });
        METHOD_HANDLE = dexValueType9;
        dexValueTypeArr[22] = dexValueType9;
        DexValueType<StringValue> dexValueType10 = new DexValueType<>("STRING", 23, 3, new BlockCreator() { // from class: zr3
            public final Block newInstance() {
                return new StringValue();
            }
        });
        STRING = dexValueType10;
        dexValueTypeArr[23] = dexValueType10;
        DexValueType<TypeValue> dexValueType11 = new DexValueType<>("TYPE", 24, 3, new BlockCreator() { // from class: as3
            public final Block newInstance() {
                return new TypeValue();
            }
        });
        TYPE = dexValueType11;
        dexValueTypeArr[24] = dexValueType11;
        DexValueType<FieldIdValue> dexValueType12 = new DexValueType<>("FIELD", 25, 3, new BlockCreator() { // from class: bs3
            public final Block newInstance() {
                return new FieldIdValue();
            }
        });
        FIELD = dexValueType12;
        dexValueTypeArr[25] = dexValueType12;
        DexValueType<MethodIdValue> dexValueType13 = new DexValueType<>("METHOD", 26, 3, new BlockCreator() { // from class: cs3
            public final Block newInstance() {
                return new MethodIdValue();
            }
        });
        METHOD = dexValueType13;
        dexValueTypeArr[26] = dexValueType13;
        DexValueType<EnumValue> dexValueType14 = new DexValueType<>("ENUM", 27, 3, new BlockCreator() { // from class: ds3
            public final Block newInstance() {
                return new EnumValue();
            }
        });
        ENUM = dexValueType14;
        dexValueTypeArr[27] = dexValueType14;
        DexValueType<ArrayValue> dexValueType15 = new DexValueType<>("ARRAY", 28, 0, new BlockCreator() { // from class: es3
            public final Block newInstance() {
                return new ArrayValue();
            }
        });
        ARRAY = dexValueType15;
        dexValueTypeArr[28] = dexValueType15;
        DexValueType<AnnotationValue> dexValueType16 = new DexValueType<>("ANNOTATION", 29, 0, new BlockCreator() { // from class: fs3
            public final Block newInstance() {
                return new AnnotationValue();
            }
        });
        ANNOTATION = dexValueType16;
        dexValueTypeArr[29] = dexValueType16;
        DexValueType<NullValue> dexValueType17 = new DexValueType<>("NULL", 30, 0, new BlockCreator() { // from class: gs3
            public final Block newInstance() {
                return new NullValue();
            }
        });
        NULL = dexValueType17;
        dexValueTypeArr[30] = dexValueType17;
        DexValueType<BooleanValue> dexValueType18 = new DexValueType<>("BOOLEAN", 31, 1, new BlockCreator() { // from class: hs3
            public final Block newInstance() {
                return new BooleanValue();
            }
        });
        BOOLEAN = dexValueType18;
        dexValueTypeArr[31] = dexValueType18;
        int i = 0;
        for (int i2 = 0; i2 < 32; i2++) {
            if (dexValueTypeArr[i2] != null) {
                i++;
            }
        }
        VALUES_COPY = new DexValueType[i];
        int i3 = 0;
        for (int i4 = 0; i4 < 32; i4++) {
            DexValueType<?> dexValueType19 = dexValueTypeArr[i4];
            if (dexValueType19 != null) {
                VALUES_COPY[i3] = dexValueType19;
                i3++;
            }
        }
    }

    private DexValueType(String str, int i, int i2, BlockCreator<T> blockCreator) {
        this.name = str;
        this.type = i;
        this.size = i2;
        this.creator = blockCreator;
        this.flag = (i2 << 5) | i;
    }

    public static DexValueBlock<?> create(BlockReader blockReader) throws IOException {
        int i = blockReader.read();
        blockReader.offset(-1);
        DexValueType<?> dexValueTypeFromFlag = fromFlag(i);
        if (dexValueTypeFromFlag != null) {
            return dexValueTypeFromFlag.newInstance();
        }
        v8g.a("Invalid value type: ", HexUtil.toHex2((byte) i), ", ", blockReader);
        return null;
    }

    public static Key createDefaultValue(TypeKey typeKey) {
        if (!typeKey.isPrimitive()) {
            return NullValueKey.INSTANCE;
        }
        if (TypeKey.TYPE_B.equals(typeKey)) {
            return PrimitiveKey.of((byte) 0);
        }
        if (TypeKey.TYPE_C.equals(typeKey)) {
            return PrimitiveKey.of((char) 0);
        }
        if (TypeKey.TYPE_D.equals(typeKey)) {
            return PrimitiveKey.of(XPath.MATCH_SCORE_QNAME);
        }
        if (TypeKey.TYPE_F.equals(typeKey)) {
            return PrimitiveKey.of(0.0f);
        }
        if (TypeKey.TYPE_I.equals(typeKey)) {
            return PrimitiveKey.of(0);
        }
        if (TypeKey.TYPE_J.equals(typeKey)) {
            return PrimitiveKey.of(0L);
        }
        if (TypeKey.TYPE_S.equals(typeKey)) {
            return PrimitiveKey.of((short) 0);
        }
        if (TypeKey.TYPE_Z.equals(typeKey)) {
            return PrimitiveKey.of(false);
        }
        throw new DexException("Unknown key type: " + typeKey);
    }

    public static int decodeSize(int i) {
        return i >>> 5;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public static DexValueType<?> forKey(Key key) throws DexException {
        if (key instanceof TypeKey) {
            return TYPE;
        }
        if (key instanceof FieldKey) {
            return ENUM;
        }
        if (key instanceof MethodKey) {
            return METHOD;
        }
        if (key instanceof StringKey) {
            return STRING;
        }
        if (key instanceof AnnotationItemKey) {
            return ANNOTATION;
        }
        if (key instanceof ArrayKey) {
            return ARRAY;
        }
        if (key instanceof NullValueKey) {
            return NULL;
        }
        if (key instanceof PrimitiveKey) {
            PrimitiveKey primitiveKey = (PrimitiveKey) key;
            if (primitiveKey.isBoolean()) {
                return BOOLEAN;
            }
            if (primitiveKey.isByte()) {
                return BYTE;
            }
            if (primitiveKey.isChar()) {
                return CHAR;
            }
            if (primitiveKey.isShort()) {
                return SHORT;
            }
            if (primitiveKey.isInteger()) {
                return INT;
            }
            if (primitiveKey.isFloat()) {
                return FLOAT;
            }
            if (primitiveKey.isDouble()) {
                return DOUBLE;
            }
            if (primitiveKey.isLong()) {
                return LONG;
            }
        }
        if (key instanceof MethodHandleKey) {
            return METHOD_HANDLE;
        }
        if (key instanceof ProtoKey) {
            return PROTO;
        }
        throw new DexException("Unknown value type: " + key.getClass() + ", " + key);
    }

    public static DexValueType<?> fromFlag(int i) {
        return VALUES[i & 31];
    }

    public static <T1 extends IdItem> DexValueType<? extends SectionValue<T1>> get(SectionType<T1> sectionType) {
        DexValueType<? extends SectionValue<T1>> dexValueType = sectionType == SectionType.STRING_ID ? STRING : null;
        if (sectionType == SectionType.TYPE_ID) {
            dexValueType = TYPE;
        }
        if (sectionType == SectionType.FIELD_ID) {
            dexValueType = ENUM;
        }
        if (sectionType == SectionType.METHOD_ID) {
            dexValueType = METHOD;
        }
        return sectionType == SectionType.METHOD_HANDLE ? METHOD_HANDLE : dexValueType;
    }

    public static DexValueType<?>[] values() {
        return VALUES_COPY;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int getFlag(int i) {
        return this.type | (i << 5);
    }

    public int getSize() {
        return this.size;
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return this.flag;
    }

    public T newInstance() {
        return this.creator.newInstance();
    }

    public String toString() {
        return this.name;
    }

    public int getFlag() {
        return this.flag;
    }
}
