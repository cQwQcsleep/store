package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ModuleExports implements Cloneable, Node {
    private final int exportsFlags;
    private final int exportsIndex;
    private final int exportsToCount;
    private final int[] exportsToIndex;

    public ModuleExports(DataInput dataInput) throws IOException {
        this.exportsIndex = dataInput.readUnsignedShort();
        this.exportsFlags = dataInput.readUnsignedShort();
        int unsignedShort = dataInput.readUnsignedShort();
        this.exportsToCount = unsignedShort;
        this.exportsToIndex = new int[unsignedShort];
        for (int i = 0; i < this.exportsToCount; i++) {
            this.exportsToIndex[i] = dataInput.readUnsignedShort();
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitModuleExports(this);
    }

    public ModuleExports copy() {
        try {
            return (ModuleExports) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.exportsIndex);
        dataOutputStream.writeShort(this.exportsFlags);
        dataOutputStream.writeShort(this.exportsToCount);
        for (int i : this.exportsToIndex) {
            dataOutputStream.writeShort(i);
        }
    }

    public String toString(ConstantPool constantPool) {
        StringBuilder sb = new StringBuilder();
        sb.append(Utility.compactClassName(constantPool.constantToString(this.exportsIndex, (byte) 20), false));
        sb.append(", ");
        sb.append(String.format("%04x", Integer.valueOf(this.exportsFlags)));
        sb.append(", to(");
        sb.append(this.exportsToCount);
        sb.append("):\n");
        for (int i : this.exportsToIndex) {
            String constantString = constantPool.getConstantString(i, (byte) 19);
            sb.append("      ");
            sb.append(Utility.compactClassName(constantString, false));
            sb.append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String toString() {
        return "exports(" + this.exportsIndex + ", " + this.exportsFlags + ", " + this.exportsToCount + ", ...)";
    }
}
