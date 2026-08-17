package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ParameterAnnotationEntry implements Node {
    static final ParameterAnnotationEntry[] EMPTY_ARRAY = new ParameterAnnotationEntry[0];
    private final AnnotationEntry[] annotationTable;

    public ParameterAnnotationEntry(DataInput dataInput, ConstantPool constantPool) throws IOException {
        int unsignedShort = dataInput.readUnsignedShort();
        this.annotationTable = new AnnotationEntry[unsignedShort];
        for (int i = 0; i < unsignedShort; i++) {
            this.annotationTable[i] = AnnotationEntry.read(dataInput, constantPool, false);
        }
    }

    public static ParameterAnnotationEntry[] createParameterAnnotationEntries(Attribute[] attributeArr) {
        ArrayList arrayList = new ArrayList(attributeArr.length);
        for (Attribute attribute : attributeArr) {
            if (attribute instanceof ParameterAnnotations) {
                Collections.addAll(arrayList, ((ParameterAnnotations) attribute).getParameterAnnotationEntries());
            }
        }
        return (ParameterAnnotationEntry[]) arrayList.toArray(EMPTY_ARRAY);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitParameterAnnotationEntry(this);
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.annotationTable.length);
        for (AnnotationEntry annotationEntry : this.annotationTable) {
            annotationEntry.dump(dataOutputStream);
        }
    }

    public AnnotationEntry[] getAnnotationEntries() {
        return this.annotationTable;
    }
}
