package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ParameterAnnotations extends Attribute implements Iterable<ParameterAnnotationEntry> {
    private ParameterAnnotationEntry[] parameterAnnotationTable;

    public ParameterAnnotations(byte b, int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(b, i, i2, (ParameterAnnotationEntry[]) null, constantPool);
        int unsignedByte = dataInput.readUnsignedByte();
        this.parameterAnnotationTable = new ParameterAnnotationEntry[unsignedByte];
        for (int i3 = 0; i3 < unsignedByte; i3++) {
            this.parameterAnnotationTable[i3] = new ParameterAnnotationEntry(dataInput, constantPool);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitParameterAnnotation(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        return (Attribute) clone();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeByte(this.parameterAnnotationTable.length);
        for (ParameterAnnotationEntry parameterAnnotationEntry : this.parameterAnnotationTable) {
            parameterAnnotationEntry.dump(dataOutputStream);
        }
    }

    public ParameterAnnotationEntry[] getParameterAnnotationEntries() {
        return this.parameterAnnotationTable;
    }

    public final ParameterAnnotationEntry[] getParameterAnnotationTable() {
        return this.parameterAnnotationTable;
    }

    @Override // java.lang.Iterable
    public Iterator<ParameterAnnotationEntry> iterator() {
        return Stream.of((Object[]) this.parameterAnnotationTable).iterator();
    }

    public final void setParameterAnnotationTable(ParameterAnnotationEntry[] parameterAnnotationEntryArr) {
        this.parameterAnnotationTable = parameterAnnotationEntryArr;
    }

    public ParameterAnnotations(byte b, int i, int i2, ParameterAnnotationEntry[] parameterAnnotationEntryArr, ConstantPool constantPool) {
        super(b, i, i2, constantPool);
        this.parameterAnnotationTable = parameterAnnotationEntryArr;
    }
}
