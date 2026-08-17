package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class RuntimeParameterAnnotations_attribute extends Attribute {
    public final Annotation[][] parameter_annotations;

    public RuntimeParameterAnnotations_attribute(ClassReader classReader, int i, int i2) throws Annotation.InvalidAnnotation, IOException {
        super(i, i2);
        this.parameter_annotations = new Annotation[classReader.readUnsignedByte()][];
        for (int i3 = 0; i3 < this.parameter_annotations.length; i3++) {
            int unsignedShort = classReader.readUnsignedShort();
            Annotation[] annotationArr = new Annotation[unsignedShort];
            for (int i4 = 0; i4 < unsignedShort; i4++) {
                annotationArr[i4] = new Annotation(classReader);
            }
            this.parameter_annotations[i3] = annotationArr;
        }
    }

    private static int length(Annotation[][] annotationArr) {
        int length = 1;
        for (Annotation[] annotationArr2 : annotationArr) {
            length += 2;
            for (Annotation annotation : annotationArr2) {
                length += annotation.length();
            }
        }
        return length;
    }

    public RuntimeParameterAnnotations_attribute(int i, Annotation[][] annotationArr) {
        super(i, length(annotationArr));
        this.parameter_annotations = annotationArr;
    }
}
