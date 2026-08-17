package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ModuleHashes_attribute extends Attribute {
    public final int algorithm_index;
    public final Entry[] hashes_table;
    public final int hashes_table_length;

    public static class Entry {
        public final byte[] hash;
        public final int module_name_index;

        public Entry(ClassReader classReader) throws IOException {
            this.module_name_index = classReader.readUnsignedShort();
            int unsignedShort = classReader.readUnsignedShort();
            this.hash = new byte[unsignedShort];
            for (int i = 0; i < unsignedShort; i++) {
                this.hash[i] = (byte) classReader.readUnsignedByte();
            }
        }

        public int length() {
            return this.hash.length + 4;
        }
    }

    public ModuleHashes_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.algorithm_index = classReader.readUnsignedShort();
        int unsignedShort = classReader.readUnsignedShort();
        this.hashes_table_length = unsignedShort;
        this.hashes_table = new Entry[unsignedShort];
        for (int i3 = 0; i3 < this.hashes_table_length; i3++) {
            this.hashes_table[i3] = new Entry(classReader);
        }
    }

    private static int length(Entry[] entryArr) {
        int length = 0;
        for (Entry entry : entryArr) {
            length += entry.length();
        }
        return length;
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitModuleHashes(this, d);
    }

    public ModuleHashes_attribute(ConstantPool constantPool, int i, Entry[] entryArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.ModuleHashes), i, entryArr);
    }

    public ModuleHashes_attribute(int i, int i2, Entry[] entryArr) {
        super(i, length(entryArr) + 4);
        this.algorithm_index = i2;
        this.hashes_table_length = entryArr.length;
        this.hashes_table = entryArr;
    }
}
