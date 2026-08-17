package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.AnnotationElementValue;
import com.sun.org.apache.bcel.internal.classfile.AnnotationEntry;
import com.sun.org.apache.bcel.internal.classfile.ArrayElementValue;
import com.sun.org.apache.bcel.internal.classfile.ClassElementValue;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import com.sun.org.apache.bcel.internal.classfile.EnumElementValue;
import com.sun.org.apache.bcel.internal.classfile.SimpleElementValue;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ElementValueGen {
    public static final int ANNOTATION = 64;
    public static final int ARRAY = 91;
    public static final int CLASS = 99;
    public static final int ENUM_CONSTANT = 101;
    public static final int PRIMITIVE_BOOLEAN = 90;
    public static final int PRIMITIVE_BYTE = 66;
    public static final int PRIMITIVE_CHAR = 67;
    public static final int PRIMITIVE_DOUBLE = 68;
    public static final int PRIMITIVE_FLOAT = 70;
    public static final int PRIMITIVE_INT = 73;
    public static final int PRIMITIVE_LONG = 74;
    public static final int PRIMITIVE_SHORT = 83;
    public static final int STRING = 115;

    @Deprecated
    protected ConstantPoolGen cpGen;

    @Deprecated
    protected int type;

    public ElementValueGen(int i, ConstantPoolGen constantPoolGen) {
        this.type = i;
        this.cpGen = constantPoolGen;
    }

    public static ElementValueGen copy(ElementValue elementValue, ConstantPoolGen constantPoolGen, boolean z) {
        int elementValueType = elementValue.getElementValueType();
        if (elementValueType == 64) {
            return new AnnotationElementValueGen((AnnotationElementValue) elementValue, constantPoolGen, z);
        }
        if (elementValueType != 70 && elementValueType != 83) {
            if (elementValueType == 99) {
                return new ClassElementValueGen((ClassElementValue) elementValue, constantPoolGen, z);
            }
            if (elementValueType == 101) {
                return new EnumElementValueGen((EnumElementValue) elementValue, constantPoolGen, z);
            }
            if (elementValueType != 115 && elementValueType != 73 && elementValueType != 74 && elementValueType != 90) {
                if (elementValueType == 91) {
                    return new ArrayElementValueGen((ArrayElementValue) elementValue, constantPoolGen, z);
                }
                switch (elementValueType) {
                    case 66:
                    case 67:
                    case 68:
                        break;
                    default:
                        throw new UnsupportedOperationException("Not implemented yet! (" + elementValue.getElementValueType() + ")");
                }
            }
        }
        return new SimpleElementValueGen((SimpleElementValue) elementValue, constantPoolGen, z);
    }

    public static ElementValueGen readElementValue(DataInput dataInput, ConstantPoolGen constantPoolGen) throws IOException {
        int unsignedByte = dataInput.readUnsignedByte();
        if (unsignedByte == 64) {
            return new AnnotationElementValueGen(64, new AnnotationEntryGen(AnnotationEntry.read(dataInput, constantPoolGen.getConstantPool(), true), constantPoolGen, false), constantPoolGen);
        }
        if (unsignedByte == 70) {
            return new SimpleElementValueGen(70, dataInput.readUnsignedShort(), constantPoolGen);
        }
        if (unsignedByte == 83) {
            return new SimpleElementValueGen(83, dataInput.readUnsignedShort(), constantPoolGen);
        }
        if (unsignedByte == 99) {
            return new ClassElementValueGen(dataInput.readUnsignedShort(), constantPoolGen);
        }
        if (unsignedByte == 101) {
            return new EnumElementValueGen(dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), constantPoolGen);
        }
        if (unsignedByte == 115) {
            return new SimpleElementValueGen(115, dataInput.readUnsignedShort(), constantPoolGen);
        }
        if (unsignedByte == 73) {
            return new SimpleElementValueGen(73, dataInput.readUnsignedShort(), constantPoolGen);
        }
        if (unsignedByte == 74) {
            return new SimpleElementValueGen(74, dataInput.readUnsignedShort(), constantPoolGen);
        }
        if (unsignedByte == 90) {
            return new SimpleElementValueGen(90, dataInput.readUnsignedShort(), constantPoolGen);
        }
        if (unsignedByte != 91) {
            switch (unsignedByte) {
                case 66:
                    return new SimpleElementValueGen(66, dataInput.readUnsignedShort(), constantPoolGen);
                case 67:
                    return new SimpleElementValueGen(67, dataInput.readUnsignedShort(), constantPoolGen);
                case 68:
                    return new SimpleElementValueGen(68, dataInput.readUnsignedShort(), constantPoolGen);
                default:
                    qf1.a("Unexpected element value kind in annotation: ", unsignedByte);
                    return null;
            }
        }
        int unsignedShort = dataInput.readUnsignedShort();
        ElementValue[] elementValueArr = new ElementValue[unsignedShort];
        for (int i = 0; i < unsignedShort; i++) {
            elementValueArr[i] = ElementValue.readElementValue(dataInput, constantPoolGen.getConstantPool());
        }
        return new ArrayElementValueGen(91, elementValueArr, constantPoolGen);
    }

    public abstract void dump(DataOutputStream dataOutputStream) throws IOException;

    public ConstantPoolGen getConstantPool() {
        return this.cpGen;
    }

    public abstract ElementValue getElementValue();

    public int getElementValueType() {
        return this.type;
    }

    public abstract String stringifyValue();
}
