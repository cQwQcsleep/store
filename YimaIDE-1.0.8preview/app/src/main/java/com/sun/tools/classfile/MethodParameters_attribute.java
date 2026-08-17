package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodParameters_attribute extends Attribute {
    public final Entry[] method_parameter_table;
    public final int method_parameter_table_length;

    public MethodParameters_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        int unsignedByte = classReader.readUnsignedByte();
        this.method_parameter_table_length = unsignedByte;
        this.method_parameter_table = new Entry[unsignedByte];
        for (int i3 = 0; i3 < this.method_parameter_table_length; i3++) {
            this.method_parameter_table[i3] = new Entry(classReader);
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitMethodParameters(this, d);
    }

    public static class Entry {
        public final int flags;
        public final int name_index;

        public Entry(ClassReader classReader) throws IOException {
            this.name_index = classReader.readUnsignedShort();
            this.flags = classReader.readUnsignedShort();
        }

        public static int length() {
            return 6;
        }

        public Entry(int i, int i2) {
            this.name_index = i;
            this.flags = i2;
        }
    }

    public MethodParameters_attribute(ConstantPool constantPool, Entry[] entryArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.MethodParameters), entryArr);
    }

    public MethodParameters_attribute(int i, Entry[] entryArr) {
        super(i, (entryArr.length * Entry.length()) + 1);
        this.method_parameter_table_length = entryArr.length;
        this.method_parameter_table = entryArr;
    }
}
