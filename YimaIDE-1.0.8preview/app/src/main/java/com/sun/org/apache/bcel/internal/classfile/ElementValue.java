package com.sun.org.apache.bcel.internal.classfile;

import defpackage.yr2;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ElementValue {
    public static final byte ANNOTATION = 64;
    public static final byte ARRAY = 91;
    public static final byte CLASS = 99;
    public static final byte ENUM_CONSTANT = 101;
    public static final byte PRIMITIVE_BOOLEAN = 90;
    public static final byte PRIMITIVE_BYTE = 66;
    public static final byte PRIMITIVE_CHAR = 67;
    public static final byte PRIMITIVE_DOUBLE = 68;
    public static final byte PRIMITIVE_FLOAT = 70;
    public static final byte PRIMITIVE_INT = 73;
    public static final byte PRIMITIVE_LONG = 74;
    public static final byte PRIMITIVE_SHORT = 83;
    public static final byte STRING = 115;

    @java.lang.Deprecated
    protected ConstantPool cpool;

    @java.lang.Deprecated
    protected int type;

    public ElementValue(int i, ConstantPool constantPool) {
        this.type = i;
        this.cpool = constantPool;
    }

    public static ElementValue readElementValue(DataInput dataInput, ConstantPool constantPool, int i) throws IOException {
        byte b = dataInput.readByte();
        if (b == 64) {
            return new AnnotationElementValue(64, AnnotationEntry.read(dataInput, constantPool, false), constantPool);
        }
        if (b != 70 && b != 83) {
            if (b == 99) {
                return new ClassElementValue(99, dataInput.readUnsignedShort(), constantPool);
            }
            if (b == 101) {
                return new EnumElementValue(101, dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), constantPool);
            }
            if (b != 115 && b != 73 && b != 74 && b != 90) {
                if (b == 91) {
                    int i2 = i + 1;
                    if (i2 > 255) {
                        throw new ClassFormatException(String.format("Arrays are only valid if they represent %,d or fewer dimensions.", 255));
                    }
                    int unsignedShort = dataInput.readUnsignedShort();
                    ElementValue[] elementValueArr = new ElementValue[unsignedShort];
                    for (int i3 = 0; i3 < unsignedShort; i3++) {
                        elementValueArr[i3] = readElementValue(dataInput, constantPool, i2);
                    }
                    return new ArrayElementValue(91, elementValueArr, constantPool);
                }
                switch (b) {
                    case 66:
                    case 67:
                    case 68:
                        break;
                    default:
                        yr2.a("Unexpected element value tag in annotation: ", b);
                        return null;
                }
            }
        }
        return new SimpleElementValue(b, dataInput.readUnsignedShort(), constantPool);
    }

    public abstract void dump(DataOutputStream dataOutputStream) throws IOException;

    public final ConstantPool getConstantPool() {
        return this.cpool;
    }

    public int getElementValueType() {
        return this.type;
    }

    public final int getType() {
        return this.type;
    }

    public abstract String stringifyValue();

    public String toShortString() {
        return stringifyValue();
    }

    public String toString() {
        return stringifyValue();
    }

    public static ElementValue readElementValue(DataInput dataInput, ConstantPool constantPool) throws IOException {
        return readElementValue(dataInput, constantPool, 0);
    }
}
