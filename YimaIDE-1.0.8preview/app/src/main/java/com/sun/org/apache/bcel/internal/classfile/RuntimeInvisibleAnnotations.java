package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RuntimeInvisibleAnnotations extends Annotations {
    public RuntimeInvisibleAnnotations(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        super((byte) 13, i, i2, dataInput, constantPool, false);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Annotations, com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        return (Attribute) clone();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public final void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        writeAnnotations(dataOutputStream);
    }
}
