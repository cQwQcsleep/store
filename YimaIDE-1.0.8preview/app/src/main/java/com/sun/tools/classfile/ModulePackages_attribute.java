package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ModulePackages_attribute extends Attribute {
    public final int packages_count;
    public final int[] packages_index;

    public ModulePackages_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.packages_count = unsignedShort;
        this.packages_index = new int[unsignedShort];
        for (int i3 = 0; i3 < this.packages_count; i3++) {
            this.packages_index[i3] = classReader.readUnsignedShort();
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitModulePackages(this, d);
    }

    public String getPackage(int i, ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getPackageInfo(this.packages_index[i]).getName();
    }

    public ModulePackages_attribute(ConstantPool constantPool, int[] iArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.ModulePackages), iArr);
    }

    public ModulePackages_attribute(int i, int[] iArr) {
        super(i, (iArr.length * 2) + 2);
        this.packages_count = iArr.length;
        this.packages_index = iArr;
    }
}
