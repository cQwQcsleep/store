package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ModuleTarget_attribute extends Attribute {
    public final int target_platform_index;

    public ModuleTarget_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.target_platform_index = classReader.readUnsignedShort();
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitModuleTarget(this, d);
    }

    public ModuleTarget_attribute(int i, int i2) {
        super(i, 2);
        this.target_platform_index = i2;
    }
}
