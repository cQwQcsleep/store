package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class RuntimeTypeAnnotations_attribute extends Attribute {
    public final TypeAnnotation[] annotations;

    public RuntimeTypeAnnotations_attribute(ClassReader classReader, int i, int i2) throws Annotation.InvalidAnnotation, IOException {
        super(i, i2);
        this.annotations = new TypeAnnotation[classReader.readUnsignedShort()];
        int i3 = 0;
        while (true) {
            TypeAnnotation[] typeAnnotationArr = this.annotations;
            if (i3 >= typeAnnotationArr.length) {
                return;
            }
            typeAnnotationArr[i3] = new TypeAnnotation(classReader);
            i3++;
        }
    }

    private static int length(TypeAnnotation[] typeAnnotationArr) {
        int length = 2;
        for (TypeAnnotation typeAnnotation : typeAnnotationArr) {
            length += typeAnnotation.length();
        }
        return length;
    }

    public RuntimeTypeAnnotations_attribute(int i, TypeAnnotation[] typeAnnotationArr) {
        super(i, length(typeAnnotationArr));
        this.annotations = typeAnnotationArr;
    }
}
