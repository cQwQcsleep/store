package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BootstrapMethods extends Attribute implements Iterable<BootstrapMethod> {
    private BootstrapMethod[] bootstrapMethods;

    public BootstrapMethods(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (BootstrapMethod[]) null, constantPool);
        int unsignedShort = dataInput.readUnsignedShort();
        this.bootstrapMethods = new BootstrapMethod[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.bootstrapMethods[i3] = new BootstrapMethod(dataInput);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitBootstrapMethods(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public BootstrapMethods copy(ConstantPool constantPool) {
        BootstrapMethods bootstrapMethods = (BootstrapMethods) clone();
        bootstrapMethods.bootstrapMethods = new BootstrapMethod[this.bootstrapMethods.length];
        int i = 0;
        while (true) {
            BootstrapMethod[] bootstrapMethodArr = this.bootstrapMethods;
            if (i >= bootstrapMethodArr.length) {
                bootstrapMethods.setConstantPool(constantPool);
                return bootstrapMethods;
            }
            bootstrapMethods.bootstrapMethods[i] = bootstrapMethodArr[i].copy();
            i++;
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public final void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.bootstrapMethods.length);
        for (BootstrapMethod bootstrapMethod : this.bootstrapMethods) {
            bootstrapMethod.dump(dataOutputStream);
        }
    }

    public final BootstrapMethod[] getBootstrapMethods() {
        return this.bootstrapMethods;
    }

    @Override // java.lang.Iterable
    public Iterator<BootstrapMethod> iterator() {
        return Stream.of((Object[]) this.bootstrapMethods).iterator();
    }

    public final void setBootstrapMethods(BootstrapMethod[] bootstrapMethodArr) {
        this.bootstrapMethods = bootstrapMethodArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BootstrapMethods(");
        sb.append(this.bootstrapMethods.length);
        sb.append("):");
        for (int i = 0; i < this.bootstrapMethods.length; i++) {
            sb.append("\n");
            int length = sb.length();
            sb.append("  ");
            sb.append(i);
            sb.append(": ");
            int length2 = sb.length() - length;
            String[] strArrSplit = this.bootstrapMethods[i].toString(super.getConstantPool()).split("\\r?\\n");
            sb.append(strArrSplit[0]);
            for (int i2 = 1; i2 < strArrSplit.length; i2++) {
                sb.append("\n");
                sb.append((CharSequence) "          ", 0, length2);
                sb.append(strArrSplit[i2]);
            }
        }
        return sb.toString();
    }

    public BootstrapMethods(int i, int i2, BootstrapMethod[] bootstrapMethodArr, ConstantPool constantPool) {
        super((byte) 20, i, i2, constantPool);
        this.bootstrapMethods = bootstrapMethodArr;
    }

    public BootstrapMethods(BootstrapMethods bootstrapMethods) {
        this(bootstrapMethods.getNameIndex(), bootstrapMethods.getLength(), bootstrapMethods.getBootstrapMethods(), bootstrapMethods.getConstantPool());
    }
}
