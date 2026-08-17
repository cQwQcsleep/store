package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocalVariableTypeTable extends Attribute implements Iterable<LocalVariable> {
    private LocalVariable[] localVariableTypeTable;

    public LocalVariableTypeTable(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (LocalVariable[]) null, constantPool);
        int unsignedShort = dataInput.readUnsignedShort();
        this.localVariableTypeTable = new LocalVariable[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.localVariableTypeTable[i3] = new LocalVariable(dataInput, constantPool);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitLocalVariableTypeTable(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        LocalVariableTypeTable localVariableTypeTable = (LocalVariableTypeTable) clone();
        LocalVariable[] localVariableArr = new LocalVariable[this.localVariableTypeTable.length];
        localVariableTypeTable.localVariableTypeTable = localVariableArr;
        Arrays.setAll(localVariableArr, new IntFunction() { // from class: mf9
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.localVariableTypeTable[i].copy();
            }
        });
        localVariableTypeTable.setConstantPool(constantPool);
        return localVariableTypeTable;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public final void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.localVariableTypeTable.length);
        for (LocalVariable localVariable : this.localVariableTypeTable) {
            localVariable.dump(dataOutputStream);
        }
    }

    public final LocalVariable getLocalVariable(int i) {
        for (LocalVariable localVariable : this.localVariableTypeTable) {
            if (localVariable.getIndex() == i) {
                return localVariable;
            }
        }
        return null;
    }

    public final LocalVariable[] getLocalVariableTypeTable() {
        return this.localVariableTypeTable;
    }

    public final int getTableLength() {
        LocalVariable[] localVariableArr = this.localVariableTypeTable;
        if (localVariableArr == null) {
            return 0;
        }
        return localVariableArr.length;
    }

    @Override // java.lang.Iterable
    public Iterator<LocalVariable> iterator() {
        return Stream.of((Object[]) this.localVariableTypeTable).iterator();
    }

    public final void setLocalVariableTable(LocalVariable[] localVariableArr) {
        this.localVariableTypeTable = localVariableArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            LocalVariable[] localVariableArr = this.localVariableTypeTable;
            if (i >= localVariableArr.length) {
                return sb.toString();
            }
            sb.append(localVariableArr[i].toStringShared(true));
            if (i < this.localVariableTypeTable.length - 1) {
                sb.append('\n');
            }
            i++;
        }
    }

    public LocalVariableTypeTable(int i, int i2, LocalVariable[] localVariableArr, ConstantPool constantPool) {
        super((byte) 17, i, i2, constantPool);
        localVariableArr = localVariableArr == null ? LocalVariable.EMPTY_ARRAY : localVariableArr;
        this.localVariableTypeTable = localVariableArr;
        Args.requireU2(localVariableArr.length, "localVariableTypeTable.length");
    }

    public LocalVariableTypeTable(LocalVariableTypeTable localVariableTypeTable) {
        this(localVariableTypeTable.getNameIndex(), localVariableTypeTable.getLength(), localVariableTypeTable.getLocalVariableTypeTable(), localVariableTypeTable.getConstantPool());
    }
}
