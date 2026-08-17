package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ExceptionTable extends Attribute {
    private int[] exceptionIndexTable;

    public ExceptionTable(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (int[]) null, constantPool);
        int unsignedShort = dataInput.readUnsignedShort();
        this.exceptionIndexTable = new int[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.exceptionIndexTable[i3] = dataInput.readUnsignedShort();
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitExceptionTable(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        ExceptionTable exceptionTable = (ExceptionTable) clone();
        int[] iArr = this.exceptionIndexTable;
        if (iArr != null) {
            exceptionTable.exceptionIndexTable = (int[]) iArr.clone();
        }
        exceptionTable.setConstantPool(constantPool);
        return exceptionTable;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.exceptionIndexTable.length);
        for (int i : this.exceptionIndexTable) {
            dataOutputStream.writeShort(i);
        }
    }

    public int[] getExceptionIndexTable() {
        return this.exceptionIndexTable;
    }

    public String[] getExceptionNames() {
        String[] strArr = new String[this.exceptionIndexTable.length];
        Arrays.setAll(strArr, new IntFunction() { // from class: kd4
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                ExceptionTable exceptionTable = this.b;
                return Utility.pathToPackage(super/*com.sun.org.apache.bcel.internal.classfile.Attribute*/.getConstantPool().getConstantString(exceptionTable.exceptionIndexTable[i], (byte) 7));
            }
        });
        return strArr;
    }

    public int getNumberOfExceptions() {
        int[] iArr = this.exceptionIndexTable;
        if (iArr == null) {
            return 0;
        }
        return iArr.length;
    }

    public void setExceptionIndexTable(int[] iArr) {
        if (iArr == null) {
            iArr = Const.EMPTY_INT_ARRAY;
        }
        this.exceptionIndexTable = iArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        StringBuilder sb = new StringBuilder("Exceptions: ");
        for (int i = 0; i < this.exceptionIndexTable.length; i++) {
            sb.append(Utility.compactClassName(super.getConstantPool().getConstantString(this.exceptionIndexTable[i], (byte) 7), false));
            if (i < this.exceptionIndexTable.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public ExceptionTable(ExceptionTable exceptionTable) {
        this(exceptionTable.getNameIndex(), exceptionTable.getLength(), exceptionTable.getExceptionIndexTable(), exceptionTable.getConstantPool());
    }

    public ExceptionTable(int i, int i2, int[] iArr, ConstantPool constantPool) {
        super((byte) 3, i, i2, constantPool);
        iArr = iArr == null ? Const.EMPTY_INT_ARRAY : iArr;
        this.exceptionIndexTable = iArr;
        Args.requireU2(iArr.length, "exceptionIndexTable.length");
    }
}
