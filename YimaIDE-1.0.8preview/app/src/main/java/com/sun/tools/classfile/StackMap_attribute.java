package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StackMap_attribute extends Attribute {
    public final stack_map_frame[] entries;
    public final int number_of_entries;

    public static class stack_map_frame extends StackMapTable_attribute.full_frame {
        public stack_map_frame(ClassReader classReader) throws StackMapTable_attribute.InvalidStackMap, IOException {
            super(255, classReader);
        }
    }

    public StackMap_attribute(ClassReader classReader, int i, int i2) throws StackMapTable_attribute.InvalidStackMap, IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.number_of_entries = unsignedShort;
        this.entries = new stack_map_frame[unsignedShort];
        for (int i3 = 0; i3 < this.number_of_entries; i3++) {
            this.entries[i3] = new stack_map_frame(classReader);
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitStackMap(this, d);
    }

    public StackMap_attribute(ConstantPool constantPool, stack_map_frame[] stack_map_frameVarArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.StackMap), stack_map_frameVarArr);
    }

    public StackMap_attribute(int i, stack_map_frame[] stack_map_frameVarArr) {
        super(i, StackMapTable_attribute.length(stack_map_frameVarArr));
        this.number_of_entries = stack_map_frameVarArr.length;
        this.entries = stack_map_frameVarArr;
    }
}
