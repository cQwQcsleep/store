package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class StackMap extends Attribute {
    private StackMapEntry[] table;

    public StackMap(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (StackMapEntry[]) null, constantPool);
        int unsignedShort = dataInput.readUnsignedShort();
        this.table = new StackMapEntry[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.table[i3] = new StackMapEntry(dataInput, constantPool);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitStackMap(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        StackMap stackMap = (StackMap) clone();
        StackMapEntry[] stackMapEntryArr = new StackMapEntry[this.table.length];
        stackMap.table = stackMapEntryArr;
        Arrays.setAll(stackMapEntryArr, new IntFunction() { // from class: ild
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.table[i].copy();
            }
        });
        stackMap.setConstantPool(constantPool);
        return stackMap;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.table.length);
        for (StackMapEntry stackMapEntry : this.table) {
            stackMapEntry.dump(dataOutputStream);
        }
    }

    public int getMapLength() {
        return this.table.length;
    }

    public StackMapEntry[] getStackMap() {
        return this.table;
    }

    public void setStackMap(StackMapEntry[] stackMapEntryArr) {
        if (stackMapEntryArr == null) {
            stackMapEntryArr = StackMapEntry.EMPTY_ARRAY;
        }
        this.table = stackMapEntryArr;
        int mapEntrySize = 2;
        for (StackMapEntry stackMapEntry : stackMapEntryArr) {
            mapEntrySize += stackMapEntry.getMapEntrySize();
        }
        setLength(mapEntrySize);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        StringBuilder sb = new StringBuilder("StackMap(");
        int byteCodeOffset = -1;
        int i = 0;
        while (true) {
            StackMapEntry[] stackMapEntryArr = this.table;
            if (i >= stackMapEntryArr.length) {
                sb.append(')');
                return sb.toString();
            }
            byteCodeOffset = stackMapEntryArr[i].getByteCodeOffset() + byteCodeOffset + 1;
            sb.append(String.format("%n@%03d %s", Integer.valueOf(byteCodeOffset), this.table[i]));
            if (i < this.table.length - 1) {
                sb.append(", ");
            }
            i++;
        }
    }

    public StackMap(int i, int i2, StackMapEntry[] stackMapEntryArr, ConstantPool constantPool) {
        super((byte) 11, i, i2, constantPool);
        stackMapEntryArr = stackMapEntryArr == null ? StackMapEntry.EMPTY_ARRAY : stackMapEntryArr;
        this.table = stackMapEntryArr;
        Args.requireU2(stackMapEntryArr.length, "table.length");
    }
}
