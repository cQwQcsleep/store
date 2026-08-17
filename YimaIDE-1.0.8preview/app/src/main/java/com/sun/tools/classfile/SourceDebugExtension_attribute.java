package com.sun.tools.classfile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SourceDebugExtension_attribute extends Attribute {
    public final byte[] debug_extension;

    public SourceDebugExtension_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        byte[] bArr = new byte[this.attribute_length];
        this.debug_extension = bArr;
        classReader.readFully(bArr);
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitSourceDebugExtension(this, d);
    }

    public String getValue() {
        return new String(this.debug_extension, StandardCharsets.UTF_8);
    }

    public SourceDebugExtension_attribute(ConstantPool constantPool, byte[] bArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.SourceDebugExtension), bArr);
    }

    public SourceDebugExtension_attribute(int i, byte[] bArr) {
        super(i, bArr.length);
        this.debug_extension = bArr;
    }
}
