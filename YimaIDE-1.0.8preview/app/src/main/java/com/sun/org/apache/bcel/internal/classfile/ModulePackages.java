package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.ModulePackages;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ModulePackages extends Attribute {
    private int[] packageIndexTable;

    public ModulePackages(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (int[]) null, constantPool);
        int unsignedShort = dataInput.readUnsignedShort();
        this.packageIndexTable = new int[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.packageIndexTable[i3] = dataInput.readUnsignedShort();
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitModulePackages(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        ModulePackages modulePackages = (ModulePackages) clone();
        int[] iArr = this.packageIndexTable;
        if (iArr != null) {
            modulePackages.packageIndexTable = (int[]) iArr.clone();
        }
        modulePackages.setConstantPool(constantPool);
        return modulePackages;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.packageIndexTable.length);
        for (int i : this.packageIndexTable) {
            dataOutputStream.writeShort(i);
        }
    }

    public int getNumberOfPackages() {
        int[] iArr = this.packageIndexTable;
        if (iArr == null) {
            return 0;
        }
        return iArr.length;
    }

    public int[] getPackageIndexTable() {
        return this.packageIndexTable;
    }

    public String[] getPackageNames() {
        String[] strArr = new String[this.packageIndexTable.length];
        Arrays.setAll(strArr, new IntFunction() { // from class: q5a
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                ModulePackages modulePackages = this.b;
                return Utility.pathToPackage(super/*com.sun.org.apache.bcel.internal.classfile.Attribute*/.getConstantPool().getConstantString(modulePackages.packageIndexTable[i], (byte) 20));
            }
        });
        return strArr;
    }

    public void setPackageIndexTable(int[] iArr) {
        if (iArr == null) {
            iArr = Const.EMPTY_INT_ARRAY;
        }
        this.packageIndexTable = iArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ModulePackages(");
        sb.append(this.packageIndexTable.length);
        sb.append("):\n");
        for (int i : this.packageIndexTable) {
            String constantString = super.getConstantPool().getConstantString(i, (byte) 20);
            sb.append("  ");
            sb.append(Utility.compactClassName(constantString, false));
            sb.append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public ModulePackages(int i, int i2, int[] iArr, ConstantPool constantPool) {
        super(Const.ATTR_MODULE_PACKAGES, i, i2, constantPool);
        iArr = iArr == null ? Const.EMPTY_INT_ARRAY : iArr;
        this.packageIndexTable = iArr;
        Args.requireU2(iArr.length, "packageIndexTable.length");
    }

    public ModulePackages(ModulePackages modulePackages) {
        this(modulePackages.getNameIndex(), modulePackages.getLength(), modulePackages.getPackageIndexTable(), modulePackages.getConstantPool());
    }
}
