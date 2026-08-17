package com.sun.org.apache.bcel.internal.classfile;

import defpackage.yr2;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationElementValue extends ElementValue {
    private final AnnotationEntry annotationEntry;

    public AnnotationElementValue(int i, AnnotationEntry annotationEntry, ConstantPool constantPool) {
        super(i, constantPool);
        if (i == 64) {
            this.annotationEntry = annotationEntry;
        } else {
            yr2.a("Only element values of type annotation can be built with this ctor - type specified: ", i);
            throw null;
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getType());
        this.annotationEntry.dump(dataOutputStream);
    }

    public AnnotationEntry getAnnotationEntry() {
        return this.annotationEntry;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public String stringifyValue() {
        return this.annotationEntry.toString();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public String toString() {
        return stringifyValue();
    }
}
