package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CharacterRangeTable_attribute extends Attribute {
    public static final int CRT_ASSIGNMENT = 4;
    public static final int CRT_BLOCK = 2;
    public static final int CRT_BRANCH_FALSE = 256;
    public static final int CRT_BRANCH_TRUE = 128;
    public static final int CRT_CREATE = 64;
    public static final int CRT_FLOW_CONTROLLER = 8;
    public static final int CRT_FLOW_TARGET = 16;
    public static final int CRT_INVOKE = 32;
    public static final int CRT_STATEMENT = 1;
    public final Entry[] character_range_table;

    public static class Entry {
        public final int character_range_end;
        public final int character_range_start;
        public final int end_pc;
        public final int flags;
        public final int start_pc;

        public Entry(ClassReader classReader) throws IOException {
            this.start_pc = classReader.readUnsignedShort();
            this.end_pc = classReader.readUnsignedShort();
            this.character_range_start = classReader.readInt();
            this.character_range_end = classReader.readInt();
            this.flags = classReader.readUnsignedShort();
        }

        public static int length() {
            return 14;
        }
    }

    public CharacterRangeTable_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.character_range_table = new Entry[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.character_range_table[i3] = new Entry(classReader);
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitCharacterRangeTable(this, d);
    }

    public CharacterRangeTable_attribute(ConstantPool constantPool, Entry[] entryArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.CharacterRangeTable), entryArr);
    }

    public CharacterRangeTable_attribute(int i, Entry[] entryArr) {
        super(i, (entryArr.length * Entry.length()) + 2);
        this.character_range_table = entryArr;
    }
}
