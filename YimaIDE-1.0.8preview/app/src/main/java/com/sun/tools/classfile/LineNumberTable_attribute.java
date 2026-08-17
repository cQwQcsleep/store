package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LineNumberTable_attribute extends Attribute {
    public final Entry[] line_number_table;
    public final int line_number_table_length;

    public static class Entry {
        public final int line_number;
        public final int start_pc;

        public Entry(ClassReader classReader) throws IOException {
            this.start_pc = classReader.readUnsignedShort();
            this.line_number = classReader.readUnsignedShort();
        }

        public static int length() {
            return 4;
        }
    }

    public LineNumberTable_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.line_number_table_length = unsignedShort;
        this.line_number_table = new Entry[unsignedShort];
        for (int i3 = 0; i3 < this.line_number_table_length; i3++) {
            this.line_number_table[i3] = new Entry(classReader);
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitLineNumberTable(this, d);
    }

    public LineNumberTable_attribute(ConstantPool constantPool, Entry[] entryArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.LineNumberTable), entryArr);
    }

    public LineNumberTable_attribute(int i, Entry[] entryArr) {
        super(i, (entryArr.length * Entry.length()) + 2);
        this.line_number_table_length = entryArr.length;
        this.line_number_table = entryArr;
    }
}
