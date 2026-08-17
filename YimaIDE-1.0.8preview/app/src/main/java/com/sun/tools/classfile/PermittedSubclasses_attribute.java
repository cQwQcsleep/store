package com.sun.tools.classfile;

import com.sun.tools.classfile.PermittedSubclasses_attribute;
import java.io.IOException;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PermittedSubclasses_attribute extends Attribute {
    public int[] subtypes;

    public PermittedSubclasses_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.subtypes = new int[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.subtypes[i3] = classReader.readUnsignedShort();
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
        return visitor.visitPermittedSubclasses(this, d);
    }

    public ConstantPool.CONSTANT_Class_info[] getSubtypes(final ConstantPool constantPool) throws ConstantPoolException {
        return (ConstantPool.CONSTANT_Class_info[]) IntStream.of(this.subtypes).mapToObj(new IntFunction() { // from class: n0b
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return PermittedSubclasses_attribute.b(constantPool, i);
            }
        }).toArray(new IntFunction() { // from class: o0b
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return PermittedSubclasses_attribute.a(i);
            }
        });
    }

    public PermittedSubclasses_attribute(int i, int[] iArr) {
        super(i, 2);
        this.subtypes = iArr;
    }
}
