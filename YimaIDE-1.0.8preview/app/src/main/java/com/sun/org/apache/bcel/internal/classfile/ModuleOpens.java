package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ModuleOpens implements Cloneable, Node {
    private final int opensFlags;
    private final int opensIndex;
    private final int opensToCount;
    private final int[] opensToIndex;

    public ModuleOpens(DataInput dataInput) throws IOException {
        this.opensIndex = dataInput.readUnsignedShort();
        this.opensFlags = dataInput.readUnsignedShort();
        int unsignedShort = dataInput.readUnsignedShort();
        this.opensToCount = unsignedShort;
        this.opensToIndex = new int[unsignedShort];
        for (int i = 0; i < this.opensToCount; i++) {
            this.opensToIndex[i] = dataInput.readUnsignedShort();
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitModuleOpens(this);
    }

    public ModuleOpens copy() {
        try {
            return (ModuleOpens) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.opensIndex);
        dataOutputStream.writeShort(this.opensFlags);
        dataOutputStream.writeShort(this.opensToCount);
        for (int i : this.opensToIndex) {
            dataOutputStream.writeShort(i);
        }
    }

    public String toString(ConstantPool constantPool) {
        StringBuilder sb = new StringBuilder();
        sb.append(Utility.compactClassName(constantPool.constantToString(this.opensIndex, (byte) 20), false));
        sb.append(", ");
        sb.append(String.format("%04x", Integer.valueOf(this.opensFlags)));
        sb.append(", to(");
        sb.append(this.opensToCount);
        sb.append("):\n");
        for (int i : this.opensToIndex) {
            String constantString = constantPool.getConstantString(i, (byte) 19);
            sb.append("      ");
            sb.append(Utility.compactClassName(constantString, false));
            sb.append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String toString() {
        return "opens(" + this.opensIndex + ", " + this.opensFlags + ", " + this.opensToCount + ", ...)";
    }
}
