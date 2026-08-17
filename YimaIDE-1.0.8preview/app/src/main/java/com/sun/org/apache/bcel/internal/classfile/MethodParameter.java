package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodParameter implements Cloneable, Node {
    private int accessFlags;
    private int nameIndex;

    public MethodParameter(DataInput dataInput) throws IOException {
        this.nameIndex = dataInput.readUnsignedShort();
        this.accessFlags = dataInput.readUnsignedShort();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitMethodParameter(this);
    }

    public MethodParameter copy() {
        try {
            return (MethodParameter) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public final void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.nameIndex);
        dataOutputStream.writeShort(this.accessFlags);
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public String getParameterName(ConstantPool constantPool) {
        int i = this.nameIndex;
        if (i == 0) {
            return null;
        }
        return constantPool.getConstantUtf8(i).getBytes();
    }

    public boolean isFinal() {
        return (this.accessFlags & 16) != 0;
    }

    public boolean isMandated() {
        return (this.accessFlags & (-32768)) != 0;
    }

    public boolean isSynthetic() {
        return (this.accessFlags & 4096) != 0;
    }

    public void setAccessFlags(int i) {
        this.accessFlags = i;
    }

    public void setNameIndex(int i) {
        this.nameIndex = i;
    }

    public MethodParameter() {
    }
}
