package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocalVariableTypeTable_attribute extends Attribute {
    public final Entry[] local_variable_table;
    public final int local_variable_table_length;

    public static class Entry {
        public final int index;
        public final int length;
        public final int name_index;
        public final int signature_index;
        public final int start_pc;

        public Entry(ClassReader classReader) throws IOException {
            this.start_pc = classReader.readUnsignedShort();
            this.length = classReader.readUnsignedShort();
            this.name_index = classReader.readUnsignedShort();
            this.signature_index = classReader.readUnsignedShort();
            this.index = classReader.readUnsignedShort();
        }

        public static int length() {
            return 10;
        }
    }

    public LocalVariableTypeTable_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.local_variable_table_length = unsignedShort;
        this.local_variable_table = new Entry[unsignedShort];
        for (int i3 = 0; i3 < this.local_variable_table_length; i3++) {
            this.local_variable_table[i3] = new Entry(classReader);
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitLocalVariableTypeTable(this, d);
    }

    public LocalVariableTypeTable_attribute(ConstantPool constantPool, Entry[] entryArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.LocalVariableTypeTable), entryArr);
    }

    public LocalVariableTypeTable_attribute(int i, Entry[] entryArr) {
        super(i, (entryArr.length * Entry.length()) + 2);
        this.local_variable_table_length = entryArr.length;
        this.local_variable_table = entryArr;
    }
}
