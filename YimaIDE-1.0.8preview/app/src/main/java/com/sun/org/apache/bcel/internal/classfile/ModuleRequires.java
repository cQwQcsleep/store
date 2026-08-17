package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ModuleRequires implements Cloneable, Node {
    private final int requiresFlags;
    private final int requiresIndex;
    private final int requiresVersionIndex;

    public ModuleRequires(DataInput dataInput) throws IOException {
        this.requiresIndex = dataInput.readUnsignedShort();
        this.requiresFlags = dataInput.readUnsignedShort();
        this.requiresVersionIndex = dataInput.readUnsignedShort();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitModuleRequires(this);
    }

    public ModuleRequires copy() {
        try {
            return (ModuleRequires) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.requiresIndex);
        dataOutputStream.writeShort(this.requiresFlags);
        dataOutputStream.writeShort(this.requiresVersionIndex);
    }

    public String toString(ConstantPool constantPool) {
        StringBuilder sb = new StringBuilder();
        sb.append(Utility.compactClassName(constantPool.constantToString(this.requiresIndex, (byte) 19), false));
        sb.append(", ");
        sb.append(String.format("%04x", Integer.valueOf(this.requiresFlags)));
        int i = this.requiresVersionIndex;
        String constantString = i == 0 ? "0" : constantPool.getConstantString(i, (byte) 1);
        sb.append(", ");
        sb.append(constantString);
        return sb.toString();
    }

    public String toString() {
        return "requires(" + this.requiresIndex + ", " + String.format("%04x", Integer.valueOf(this.requiresFlags)) + ", " + this.requiresVersionIndex + ")";
    }
}
