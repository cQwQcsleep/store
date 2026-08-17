package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Record_attribute extends Attribute {
    public final int component_count;
    public final ComponentInfo[] component_info_arr;

    public Record_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.component_count = unsignedShort;
        this.component_info_arr = new ComponentInfo[unsignedShort];
        for (int i3 = 0; i3 < this.component_count; i3++) {
            this.component_info_arr[i3] = new ComponentInfo(classReader);
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitRecord(this, d);
    }

    public static class ComponentInfo {
        public final Attributes attributes;
        public final Descriptor descriptor;
        public final int name_index;

        public ComponentInfo(ClassReader classReader) throws IOException {
            this.name_index = classReader.readUnsignedShort();
            this.descriptor = new Descriptor(classReader);
            this.attributes = new Attributes(classReader);
        }

        public String getName(ConstantPool constantPool) throws ConstantPoolException {
            return constantPool.getUTF8Value(this.name_index);
        }

        public ComponentInfo(int i, Descriptor descriptor, Attributes attributes) {
            this.name_index = i;
            this.descriptor = descriptor;
            this.attributes = attributes;
        }
    }

    public Record_attribute(int i, ComponentInfo[] componentInfoArr) {
        super(i, 2);
        this.component_count = componentInfoArr.length;
        this.component_info_arr = componentInfoArr;
    }
}
