package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Method {
    public final AccessFlags access_flags;
    public final Attributes attributes;
    public final Descriptor descriptor;
    public final int name_index;

    public Method(ClassReader classReader) throws IOException {
        this.access_flags = new AccessFlags(classReader);
        this.name_index = classReader.readUnsignedShort();
        this.descriptor = new Descriptor(classReader);
        this.attributes = new Attributes(classReader);
    }

    public int byteLength() {
        return this.attributes.byteLength() + 6;
    }

    public String getName(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getUTF8Value(this.name_index);
    }

    public Method(AccessFlags accessFlags, int i, Descriptor descriptor, Attributes attributes) {
        this.access_flags = accessFlags;
        this.name_index = i;
        this.descriptor = descriptor;
        this.attributes = attributes;
    }
}
