package com.sun.tools.classfile;

import com.sun.tools.classfile.NestMembers_attribute;
import java.io.IOException;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NestMembers_attribute extends Attribute {
    public final int[] members_indexes;

    public NestMembers_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.members_indexes = new int[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.members_indexes[i3] = classReader.readUnsignedShort();
        }
    }

    public static /* synthetic */ ConstantPool.CONSTANT_Class_info[] a(int i) {
        return new ConstantPool.CONSTANT_Class_info[i];
    }

    public static /* synthetic */ ConstantPool.CONSTANT_Class_info b(ConstantPool constantPool, int i) {
        try {
            return constantPool.getClassInfo(i);
        } catch (ConstantPoolException e) {
            x01.a(e);
            return null;
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitNestMembers(this, d);
    }

    public ConstantPool.CONSTANT_Class_info[] getChildren(final ConstantPool constantPool) throws ConstantPoolException {
        return (ConstantPool.CONSTANT_Class_info[]) IntStream.of(this.members_indexes).mapToObj(new IntFunction() { // from class: uga
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return NestMembers_attribute.b(constantPool, i);
            }
        }).toArray(new IntFunction() { // from class: vga
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return NestMembers_attribute.a(i);
            }
        });
    }

    public NestMembers_attribute(int i, int[] iArr) {
        super(i, 2);
        this.members_indexes = iArr;
    }
}
