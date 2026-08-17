package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class SourceFile extends Attribute {
    private int sourceFileIndex;

    public SourceFile(int i, int i2, int i3, ConstantPool constantPool) {
        super((byte) 0, i, Args.require(i2, 2, "SourceFile length attribute"), constantPool);
        this.sourceFileIndex = Args.requireU2(i3, 0, constantPool.getLength(), "SourceFile source file index");
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitSourceFile(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        return (Attribute) clone();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.sourceFileIndex);
    }

    public int getSourceFileIndex() {
        return this.sourceFileIndex;
    }

    public String getSourceFileName() {
        return super.getConstantPool().getConstantUtf8(this.sourceFileIndex).getBytes();
    }

    public void setSourceFileIndex(int i) {
        this.sourceFileIndex = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        return "SourceFile: " + getSourceFileName();
    }

    public SourceFile(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, dataInput.readUnsignedShort(), constantPool);
    }

    public SourceFile(SourceFile sourceFile) {
        this(sourceFile.getNameIndex(), sourceFile.getLength(), sourceFile.getSourceFileIndex(), sourceFile.getConstantPool());
    }
}
