package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ModuleProvides implements Cloneable, Node {
    private final int providesIndex;
    private final int providesWithCount;
    private final int[] providesWithIndex;

    public ModuleProvides(DataInput dataInput) throws IOException {
        this.providesIndex = dataInput.readUnsignedShort();
        int unsignedShort = dataInput.readUnsignedShort();
        this.providesWithCount = unsignedShort;
        this.providesWithIndex = new int[unsignedShort];
        for (int i = 0; i < this.providesWithCount; i++) {
            this.providesWithIndex[i] = dataInput.readUnsignedShort();
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitModuleProvides(this);
    }

    public ModuleProvides copy() {
        try {
            return (ModuleProvides) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.providesIndex);
        dataOutputStream.writeShort(this.providesWithCount);
        for (int i : this.providesWithIndex) {
            dataOutputStream.writeShort(i);
        }
    }

    public String toString(ConstantPool constantPool) {
        StringBuilder sb = new StringBuilder();
        sb.append(Utility.compactClassName(constantPool.constantToString(this.providesIndex, (byte) 7), false));
        sb.append(", with(");
        sb.append(this.providesWithCount);
        sb.append("):\n");
        for (int i : this.providesWithIndex) {
            String constantString = constantPool.getConstantString(i, (byte) 7);
            sb.append("      ");
            sb.append(Utility.compactClassName(constantString, false));
            sb.append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String toString() {
        return "provides(" + this.providesIndex + ", " + this.providesWithCount + ", ...)";
    }
}
