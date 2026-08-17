package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import defpackage.yr2;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class StackMapType implements Cloneable {
    public static final StackMapType[] EMPTY_ARRAY = new StackMapType[0];
    private ConstantPool constantPool;
    private int index;
    private byte type;

    public StackMapType(DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(dataInput.readByte(), -1, constantPool);
        if (hasIndex()) {
            this.index = dataInput.readUnsignedShort();
        }
        this.constantPool = constantPool;
    }

    private byte checkType(byte b) {
        if (b >= 0 && b <= 8) {
            return b;
        }
        yr2.a("Illegal type for StackMapType: ", b);
        return (byte) 0;
    }

    private String printIndex() {
        byte b = this.type;
        if (b == 7) {
            if (this.index < 0) {
                return ", class=<unknown>";
            }
            return ", class=" + this.constantPool.constantToString(this.index, (byte) 7);
        }
        if (b != 8) {
            return "";
        }
        return ", offset=" + this.index;
    }

    public StackMapType copy() {
        try {
            return (StackMapType) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(this.type);
        if (hasIndex()) {
            dataOutputStream.writeShort(getIndex());
        }
    }

    public ConstantPool getConstantPool() {
        return this.constantPool;
    }

    public int getIndex() {
        return this.index;
    }

    public byte getType() {
        return this.type;
    }

    public boolean hasIndex() {
        byte b = this.type;
        return b == 7 || b == 8;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setType(byte b) {
        this.type = checkType(b);
    }

    public String toString() {
        return "(type=" + Const.getItemName(this.type) + printIndex() + ")";
    }

    public StackMapType(byte b, int i, ConstantPool constantPool) {
        this.index = -1;
        this.type = checkType(b);
        this.index = i;
        this.constantPool = constantPool;
    }
}
