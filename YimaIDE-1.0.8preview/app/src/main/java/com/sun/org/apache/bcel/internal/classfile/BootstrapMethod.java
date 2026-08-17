package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BootstrapMethod implements Cloneable {
    private int[] bootstrapArguments;
    private int bootstrapMethodRef;

    public BootstrapMethod(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort(), dataInput.readUnsignedShort());
        int i = 0;
        while (true) {
            int[] iArr = this.bootstrapArguments;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = dataInput.readUnsignedShort();
            i++;
        }
    }

    public BootstrapMethod copy() {
        try {
            return (BootstrapMethod) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public final void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.bootstrapMethodRef);
        dataOutputStream.writeShort(this.bootstrapArguments.length);
        for (int i : this.bootstrapArguments) {
            dataOutputStream.writeShort(i);
        }
    }

    public int[] getBootstrapArguments() {
        return this.bootstrapArguments;
    }

    public int getBootstrapMethodRef() {
        return this.bootstrapMethodRef;
    }

    public int getNumBootstrapArguments() {
        return this.bootstrapArguments.length;
    }

    public void setBootstrapArguments(int[] iArr) {
        this.bootstrapArguments = iArr;
    }

    public void setBootstrapMethodRef(int i) {
        this.bootstrapMethodRef = i;
    }

    public final String toString(ConstantPool constantPool) {
        StringBuilder sb = new StringBuilder();
        sb.append(Utility.compactClassName(constantPool.constantToString(this.bootstrapMethodRef, (byte) 15), false));
        int length = this.bootstrapArguments.length;
        if (length > 0) {
            sb.append("\nMethod Arguments:");
            for (int i = 0; i < length; i++) {
                sb.append("\n  ");
                sb.append(i);
                sb.append(": ");
                sb.append(constantPool.constantToString(constantPool.getConstant(this.bootstrapArguments[i])));
            }
        }
        return sb.toString();
    }

    public BootstrapMethod(BootstrapMethod bootstrapMethod) {
        this(bootstrapMethod.getBootstrapMethodRef(), bootstrapMethod.getBootstrapArguments());
    }

    private BootstrapMethod(int i, int i2) {
        this(i, new int[i2]);
    }

    public BootstrapMethod(int i, int[] iArr) {
        this.bootstrapMethodRef = i;
        this.bootstrapArguments = iArr;
    }

    public final String toString() {
        return "BootstrapMethod(" + this.bootstrapMethodRef + ", " + this.bootstrapArguments.length + ", " + Arrays.toString(this.bootstrapArguments) + ")";
    }
}
