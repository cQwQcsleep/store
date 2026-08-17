package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ModuleResolution_attribute extends Attribute {
    public static final int DO_NOT_RESOLVE_BY_DEFAULT = 1;
    public static final int WARN_DEPRECATED = 2;
    public static final int WARN_DEPRECATED_FOR_REMOVAL = 4;
    public static final int WARN_INCUBATING = 8;
    public final int resolution_flags;

    public ModuleResolution_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.resolution_flags = classReader.readUnsignedShort();
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitModuleResolution(this, d);
    }

    public ModuleResolution_attribute(ConstantPool constantPool, int i) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.ModuleResolution), i);
    }

    public ModuleResolution_attribute(int i, int i2) {
        super(i, 2);
        this.resolution_flags = i2;
    }
}
