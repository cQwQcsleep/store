package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.xpath.internal.XPath;
import defpackage.yr2;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SimpleElementValue extends ElementValue {
    private int index;

    public SimpleElementValue(int i, int i2, ConstantPool constantPool) {
        super(i, constantPool);
        this.index = i2;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        int type = super.getType();
        dataOutputStream.writeByte(type);
        if (type != 70 && type != 83 && type != 90 && type != 115 && type != 73 && type != 74) {
            switch (type) {
                case 66:
                case 67:
                case 68:
                    break;
                default:
                    yr2.a("SimpleElementValue doesnt know how to write out type ", type);
                    break;
            }
            return;
        }
        dataOutputStream.writeShort(getIndex());
    }

    public int getIndex() {
        return this.index;
    }

    public boolean getValueBoolean() {
        if (super.getType() == 90) {
            return ((ConstantInteger) super.getConstantPool().getConstant(getIndex())).getBytes() != 0;
        }
        k2d.a("Dont call getValueBoolean() on a non BOOLEAN ElementValue");
        return false;
    }

    public byte getValueByte() {
        if (super.getType() == 66) {
            return (byte) super.getConstantPool().getConstantInteger(getIndex()).getBytes();
        }
        k2d.a("Dont call getValueByte() on a non BYTE ElementValue");
        return (byte) 0;
    }

    public char getValueChar() {
        if (super.getType() == 67) {
            return (char) super.getConstantPool().getConstantInteger(getIndex()).getBytes();
        }
        k2d.a("Dont call getValueChar() on a non CHAR ElementValue");
        return (char) 0;
    }

    public double getValueDouble() {
        if (super.getType() == 68) {
            return ((ConstantDouble) super.getConstantPool().getConstant(getIndex())).getBytes();
        }
        k2d.a("Dont call getValueDouble() on a non DOUBLE ElementValue");
        return XPath.MATCH_SCORE_QNAME;
    }

    public float getValueFloat() {
        if (super.getType() == 70) {
            return ((ConstantFloat) super.getConstantPool().getConstant(getIndex())).getBytes();
        }
        k2d.a("Dont call getValueFloat() on a non FLOAT ElementValue");
        return 0.0f;
    }

    public int getValueInt() {
        if (super.getType() == 73) {
            return super.getConstantPool().getConstantInteger(getIndex()).getBytes();
        }
        k2d.a("Dont call getValueInt() on a non INT ElementValue");
        return 0;
    }

    public long getValueLong() {
        if (super.getType() == 74) {
            return ((ConstantLong) super.getConstantPool().getConstant(getIndex())).getBytes();
        }
        k2d.a("Dont call getValueLong() on a non LONG ElementValue");
        return 0L;
    }

    public short getValueShort() {
        if (super.getType() == 83) {
            return (short) ((ConstantInteger) super.getConstantPool().getConstant(getIndex())).getBytes();
        }
        k2d.a("Dont call getValueShort() on a non SHORT ElementValue");
        return (short) 0;
    }

    public String getValueString() {
        if (super.getType() == 115) {
            return super.getConstantPool().getConstantUtf8(getIndex()).getBytes();
        }
        k2d.a("Dont call getValueString() on a non STRING ElementValue");
        return null;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public String stringifyValue() {
        ConstantPool constantPool = super.getConstantPool();
        int type = super.getType();
        if (type == 70) {
            return Float.toString(((ConstantFloat) constantPool.getConstant(getIndex(), (byte) 4, ConstantFloat.class)).getBytes());
        }
        if (type == 83) {
            return Integer.toString(constantPool.getConstantInteger(getIndex()).getBytes());
        }
        if (type == 90) {
            return constantPool.getConstantInteger(getIndex()).getBytes() == 0 ? "false" : "true";
        }
        if (type == 115) {
            return constantPool.getConstantUtf8(getIndex()).getBytes();
        }
        if (type == 73) {
            return Integer.toString(constantPool.getConstantInteger(getIndex()).getBytes());
        }
        if (type == 74) {
            return Long.toString(((ConstantLong) constantPool.getConstant(getIndex(), (byte) 5, ConstantLong.class)).getBytes());
        }
        switch (type) {
            case 66:
                return Integer.toString(constantPool.getConstantInteger(getIndex()).getBytes());
            case 67:
                return String.valueOf((char) constantPool.getConstantInteger(getIndex()).getBytes());
            case 68:
                return Double.toString(((ConstantDouble) constantPool.getConstant(getIndex(), (byte) 6, ConstantDouble.class)).getBytes());
            default:
                pu7.a("SimpleElementValue class does not know how to stringify type ", type);
                return null;
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public String toString() {
        return stringifyValue();
    }
}
