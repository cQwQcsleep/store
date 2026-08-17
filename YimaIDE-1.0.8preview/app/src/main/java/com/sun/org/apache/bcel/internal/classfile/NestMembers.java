package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.NestMembers;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class NestMembers extends Attribute {
    private int[] classes;

    public NestMembers(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (int[]) null, constantPool);
        int unsignedShort = dataInput.readUnsignedShort();
        this.classes = new int[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.classes[i3] = dataInput.readUnsignedShort();
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitNestMembers(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        NestMembers nestMembers = (NestMembers) clone();
        int[] iArr = this.classes;
        if (iArr.length > 0) {
            nestMembers.classes = (int[]) iArr.clone();
        }
        nestMembers.setConstantPool(constantPool);
        return nestMembers;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.classes.length);
        for (int i : this.classes) {
            dataOutputStream.writeShort(i);
        }
    }

    public String[] getClassNames() {
        String[] strArr = new String[this.classes.length];
        Arrays.setAll(strArr, new IntFunction() { // from class: tga
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                NestMembers nestMembers = this.b;
                return Utility.pathToPackage(super/*com.sun.org.apache.bcel.internal.classfile.Attribute*/.getConstantPool().getConstantString(nestMembers.classes[i], (byte) 7));
            }
        });
        return strArr;
    }

    public int[] getClasses() {
        return this.classes;
    }

    public int getNumberClasses() {
        return this.classes.length;
    }

    public void setClasses(int[] iArr) {
        if (iArr == null) {
            iArr = Const.EMPTY_INT_ARRAY;
        }
        this.classes = iArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NestMembers(");
        sb.append(this.classes.length);
        sb.append("):\n");
        for (int i : this.classes) {
            String constantString = super.getConstantPool().getConstantString(i, (byte) 7);
            sb.append("  ");
            sb.append(Utility.compactClassName(constantString, false));
            sb.append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public NestMembers(int i, int i2, int[] iArr, ConstantPool constantPool) {
        super((byte) 26, i, i2, constantPool);
        iArr = iArr == null ? Const.EMPTY_INT_ARRAY : iArr;
        this.classes = iArr;
        Args.requireU2(iArr.length, "classes.length");
    }

    public NestMembers(NestMembers nestMembers) {
        this(nestMembers.getNameIndex(), nestMembers.getLength(), nestMembers.getClasses(), nestMembers.getConstantPool());
    }
}
