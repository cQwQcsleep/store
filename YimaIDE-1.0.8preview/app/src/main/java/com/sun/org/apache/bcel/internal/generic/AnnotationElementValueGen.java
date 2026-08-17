package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.AnnotationElementValue;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationElementValueGen extends ElementValueGen {
    private final AnnotationEntryGen a;

    public AnnotationElementValueGen(AnnotationElementValue annotationElementValue, ConstantPoolGen constantPoolGen, boolean z) {
        super(64, constantPoolGen);
        this.a = new AnnotationEntryGen(annotationElementValue.getAnnotationEntry(), constantPoolGen, z);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getElementValueType());
        this.a.dump(dataOutputStream);
    }

    public AnnotationEntryGen getAnnotation() {
        return this.a;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public ElementValue getElementValue() {
        return new AnnotationElementValue(super.getElementValueType(), this.a.getAnnotation(), getConstantPool().getConstantPool());
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public String stringifyValue() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public AnnotationElementValueGen(AnnotationEntryGen annotationEntryGen, ConstantPoolGen constantPoolGen) {
        super(64, constantPoolGen);
        this.a = annotationEntryGen;
    }

    public AnnotationElementValueGen(int i, AnnotationEntryGen annotationEntryGen, ConstantPoolGen constantPoolGen) {
        super(i, constantPoolGen);
        if (i == 64) {
            this.a = annotationEntryGen;
        } else {
            qf1.a("Only element values of type annotation can be built with this ctor - type specified: ", i);
            throw null;
        }
    }
}
