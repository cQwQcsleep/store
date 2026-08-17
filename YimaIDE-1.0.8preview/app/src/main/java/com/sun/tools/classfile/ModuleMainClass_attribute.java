package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ModuleMainClass_attribute extends Attribute {
    public final int main_class_index;

    public ModuleMainClass_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.main_class_index = classReader.readUnsignedShort();
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitModuleMainClass(this, d);
    }

    public String getMainClassName(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getClassInfo(this.main_class_index).getName();
    }

    public ModuleMainClass_attribute(ConstantPool constantPool, int i) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.ModuleMainClass), i);
    }

    public ModuleMainClass_attribute(int i, int i2) {
        super(i, 2);
        this.main_class_index = i2;
    }
}
