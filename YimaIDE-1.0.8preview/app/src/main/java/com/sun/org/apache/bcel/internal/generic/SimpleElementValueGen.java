package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.ConstantDouble;
import com.sun.org.apache.bcel.internal.classfile.ConstantFloat;
import com.sun.org.apache.bcel.internal.classfile.ConstantInteger;
import com.sun.org.apache.bcel.internal.classfile.ConstantLong;
import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import com.sun.org.apache.bcel.internal.classfile.SimpleElementValue;
import defpackage.ena;
import defpackage.jt6;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SimpleElementValueGen extends ElementValueGen {
    private final int idx;

    public SimpleElementValueGen(SimpleElementValue simpleElementValue, ConstantPoolGen constantPoolGen, boolean z) {
        super(simpleElementValue.getElementValueType(), constantPoolGen);
        if (!z) {
            this.idx = simpleElementValue.getIndex();
            return;
        }
        int elementValueType = simpleElementValue.getElementValueType();
        if (elementValueType == 70) {
            this.idx = constantPoolGen.addFloat(simpleElementValue.getValueFloat());
            return;
        }
        if (elementValueType == 83) {
            this.idx = constantPoolGen.addInteger(simpleElementValue.getValueShort());
            return;
        }
        if (elementValueType == 90) {
            if (simpleElementValue.getValueBoolean()) {
                this.idx = constantPoolGen.addInteger(1);
                return;
            } else {
                this.idx = constantPoolGen.addInteger(0);
                return;
            }
        }
        if (elementValueType == 115) {
            this.idx = constantPoolGen.addUtf8(simpleElementValue.getValueString());
            return;
        }
        if (elementValueType == 73) {
            this.idx = constantPoolGen.addInteger(simpleElementValue.getValueInt());
            return;
        }
        if (elementValueType == 74) {
            this.idx = constantPoolGen.addLong(simpleElementValue.getValueLong());
            return;
        }
        switch (elementValueType) {
            case 66:
                this.idx = constantPoolGen.addInteger(simpleElementValue.getValueByte());
                return;
            case 67:
                this.idx = constantPoolGen.addInteger(simpleElementValue.getValueChar());
                return;
            case 68:
                this.idx = constantPoolGen.addDouble(simpleElementValue.getValueDouble());
                return;
            default:
                jt6.a("SimpleElementValueGen class does not know how to copy this type ", super.getElementValueType());
                throw null;
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getElementValueType());
        int elementValueType = super.getElementValueType();
        if (elementValueType != 70 && elementValueType != 83 && elementValueType != 90 && elementValueType != 115 && elementValueType != 73 && elementValueType != 74) {
            switch (elementValueType) {
                case 66:
                case 67:
                case 68:
                    break;
                default:
                    ena.a("SimpleElementValueGen doesnt know how to write out type ", super.getElementValueType());
                    break;
            }
            return;
        }
        dataOutputStream.writeShort(this.idx);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public ElementValue getElementValue() {
        return new SimpleElementValue(super.getElementValueType(), this.idx, getConstantPool().getConstantPool());
    }

    public int getIndex() {
        return this.idx;
    }

    public int getValueInt() {
        if (super.getElementValueType() == 73) {
            return ((ConstantInteger) getConstantPool().getConstant(this.idx)).getBytes();
        }
        k2d.a("Dont call getValueString() on a non STRING ElementValue");
        return 0;
    }

    public String getValueString() {
        if (super.getElementValueType() == 115) {
            return ((ConstantUtf8) getConstantPool().getConstant(this.idx)).getBytes();
        }
        k2d.a("Dont call getValueString() on a non STRING ElementValue");
        return null;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public String stringifyValue() {
        int elementValueType = super.getElementValueType();
        if (elementValueType == 70) {
            return Float.toString(((ConstantFloat) getConstantPool().getConstant(this.idx)).getBytes());
        }
        if (elementValueType == 83) {
            return Integer.toString(((ConstantInteger) getConstantPool().getConstant(this.idx)).getBytes());
        }
        if (elementValueType == 90) {
            return ((ConstantInteger) getConstantPool().getConstant(this.idx)).getBytes() == 0 ? "false" : "true";
        }
        if (elementValueType == 115) {
            return ((ConstantUtf8) getConstantPool().getConstant(this.idx)).getBytes();
        }
        if (elementValueType == 73) {
            return Integer.toString(((ConstantInteger) getConstantPool().getConstant(this.idx)).getBytes());
        }
        if (elementValueType == 74) {
            return Long.toString(((ConstantLong) getConstantPool().getConstant(this.idx)).getBytes());
        }
        switch (elementValueType) {
            case 66:
                return Integer.toString(((ConstantInteger) getConstantPool().getConstant(this.idx)).getBytes());
            case 67:
                return Integer.toString(((ConstantInteger) getConstantPool().getConstant(this.idx)).getBytes());
            case 68:
                return Double.toString(((ConstantDouble) getConstantPool().getConstant(this.idx)).getBytes());
            default:
                ena.a("SimpleElementValueGen class does not know how to stringify type ", super.getElementValueType());
                return null;
        }
    }

    public SimpleElementValueGen(int i, ConstantPoolGen constantPoolGen, byte b) {
        super(i, constantPoolGen);
        this.idx = getConstantPool().addInteger(b);
    }

    public SimpleElementValueGen(int i, ConstantPoolGen constantPoolGen, char c) {
        super(i, constantPoolGen);
        this.idx = getConstantPool().addInteger(c);
    }

    public SimpleElementValueGen(int i, ConstantPoolGen constantPoolGen, double d) {
        super(i, constantPoolGen);
        this.idx = getConstantPool().addDouble(d);
    }

    public SimpleElementValueGen(int i, ConstantPoolGen constantPoolGen, float f) {
        super(i, constantPoolGen);
        this.idx = getConstantPool().addFloat(f);
    }

    public SimpleElementValueGen(int i, ConstantPoolGen constantPoolGen, int i2) {
        super(i, constantPoolGen);
        this.idx = getConstantPool().addInteger(i2);
    }

    public SimpleElementValueGen(int i, ConstantPoolGen constantPoolGen, long j) {
        super(i, constantPoolGen);
        this.idx = getConstantPool().addLong(j);
    }

    public SimpleElementValueGen(int i, ConstantPoolGen constantPoolGen, short s) {
        super(i, constantPoolGen);
        this.idx = getConstantPool().addInteger(s);
    }

    public SimpleElementValueGen(int i, ConstantPoolGen constantPoolGen, String str) {
        super(i, constantPoolGen);
        this.idx = getConstantPool().addUtf8(str);
    }

    public SimpleElementValueGen(int i, int i2, ConstantPoolGen constantPoolGen) {
        super(i, constantPoolGen);
        this.idx = i2;
    }

    public SimpleElementValueGen(int i, ConstantPoolGen constantPoolGen, boolean z) {
        super(i, constantPoolGen);
        if (z) {
            this.idx = getConstantPool().addInteger(1);
        } else {
            this.idx = getConstantPool().addInteger(0);
        }
    }
}
