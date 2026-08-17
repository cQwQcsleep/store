package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class RuntimeAnnotations_attribute extends Attribute {
    public final Annotation[] annotations;

    public RuntimeAnnotations_attribute(ClassReader classReader, int i, int i2) throws Annotation.InvalidAnnotation, IOException {
        super(i, i2);
        this.annotations = new Annotation[classReader.readUnsignedShort()];
        int i3 = 0;
        while (true) {
            Annotation[] annotationArr = this.annotations;
            if (i3 >= annotationArr.length) {
                return;
            }
            annotationArr[i3] = new Annotation(classReader);
            i3++;
        }
    }

    private static int length(Annotation[] annotationArr) {
        int length = 2;
        for (Annotation annotation : annotationArr) {
            length += annotation.length();
        }
        return length;
    }

    public RuntimeAnnotations_attribute(int i, Annotation[] annotationArr) {
        super(i, length(annotationArr));
        this.annotations = annotationArr;
    }
}
