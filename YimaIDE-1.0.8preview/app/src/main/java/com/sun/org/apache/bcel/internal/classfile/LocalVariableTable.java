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
public class LocalVariableTable extends Attribute implements Iterable<LocalVariable> {
    private LocalVariable[] localVariableTable;

    public LocalVariableTable(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (LocalVariable[]) null, constantPool);
        int unsignedShort = dataInput.readUnsignedShort();
        this.localVariableTable = new LocalVariable[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.localVariableTable[i3] = new LocalVariable(dataInput, constantPool);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitLocalVariableTable(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        LocalVariableTable localVariableTable = (LocalVariableTable) clone();
        LocalVariable[] localVariableArr = new LocalVariable[this.localVariableTable.length];
        localVariableTable.localVariableTable = localVariableArr;
        Arrays.setAll(localVariableArr, new IntFunction() { // from class: lf9
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.localVariableTable[i].copy();
            }
        });
        localVariableTable.setConstantPool(constantPool);
        return localVariableTable;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public final void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.localVariableTable.length);
        for (LocalVariable localVariable : this.localVariableTable) {
            localVariable.dump(dataOutputStream);
        }
    }

    public final LocalVariable getLocalVariable(int i, int i2) {
        for (LocalVariable localVariable : this.localVariableTable) {
            if (localVariable.getIndex() == i) {
                int startPC = localVariable.getStartPC();
                int length = localVariable.getLength() + startPC;
                if (i2 >= startPC && i2 <= length) {
                    return localVariable;
                }
            }
        }
        return null;
    }

    public final LocalVariable[] getLocalVariableTable() {
        return this.localVariableTable;
    }

    public final int getTableLength() {
        LocalVariable[] localVariableArr = this.localVariableTable;
        if (localVariableArr == null) {
            return 0;
        }
        return localVariableArr.length;
    }

    @Override // java.lang.Iterable
    public Iterator<LocalVariable> iterator() {
        return Stream.of((Object[]) this.localVariableTable).iterator();
    }

    public final void setLocalVariableTable(LocalVariable[] localVariableArr) {
        this.localVariableTable = localVariableArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            LocalVariable[] localVariableArr = this.localVariableTable;
            if (i >= localVariableArr.length) {
                return sb.toString();
            }
            sb.append(localVariableArr[i]);
            if (i < this.localVariableTable.length - 1) {
                sb.append('\n');
            }
            i++;
        }
    }

    public LocalVariableTable(int i, int i2, LocalVariable[] localVariableArr, ConstantPool constantPool) {
        super((byte) 5, i, i2, constantPool);
        localVariableArr = localVariableArr == null ? LocalVariable.EMPTY_ARRAY : localVariableArr;
        this.localVariableTable = localVariableArr;
        Args.requireU2(localVariableArr.length, "localVariableTable.length");
    }

    public LocalVariableTable(LocalVariableTable localVariableTable) {
        this(localVariableTable.getNameIndex(), localVariableTable.getLength(), localVariableTable.getLocalVariableTable(), localVariableTable.getConstantPool());
    }

    @java.lang.Deprecated
    public final LocalVariable getLocalVariable(int i) {
        for (LocalVariable localVariable : this.localVariableTable) {
            if (localVariable.getIndex() == i) {
                return localVariable;
            }
        }
        return null;
    }
}
