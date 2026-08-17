package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Annotations extends Attribute implements Iterable<AnnotationEntry> {
    private AnnotationEntry[] annotationTable;
    private final boolean isRuntimeVisible;

    public Annotations(byte b, int i, int i2, DataInput dataInput, ConstantPool constantPool, boolean z) throws IOException {
        this(b, i, i2, (AnnotationEntry[]) null, constantPool, z);
        int unsignedShort = dataInput.readUnsignedShort();
        this.annotationTable = new AnnotationEntry[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.annotationTable[i3] = AnnotationEntry.read(dataInput, constantPool, z);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitAnnotation(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        return null;
    }

    public AnnotationEntry[] getAnnotationEntries() {
        return this.annotationTable;
    }

    public final int getNumAnnotations() {
        AnnotationEntry[] annotationEntryArr = this.annotationTable;
        if (annotationEntryArr == null) {
            return 0;
        }
        return annotationEntryArr.length;
    }

    public boolean isRuntimeVisible() {
        return this.isRuntimeVisible;
    }

    @Override // java.lang.Iterable
    public Iterator<AnnotationEntry> iterator() {
        return Stream.of((Object[]) this.annotationTable).iterator();
    }

    public final void setAnnotationTable(AnnotationEntry[] annotationEntryArr) {
        this.annotationTable = annotationEntryArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public final String toString() {
        StringBuilder sb = new StringBuilder(Const.getAttributeName(getTag()));
        sb.append(":\n");
        for (int i = 0; i < this.annotationTable.length; i++) {
            sb.append("  ");
            sb.append(this.annotationTable[i]);
            if (i < this.annotationTable.length - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    public void writeAnnotations(DataOutputStream dataOutputStream) throws IOException {
        AnnotationEntry[] annotationEntryArr = this.annotationTable;
        if (annotationEntryArr == null) {
            return;
        }
        dataOutputStream.writeShort(annotationEntryArr.length);
        for (AnnotationEntry annotationEntry : this.annotationTable) {
            annotationEntry.dump(dataOutputStream);
        }
    }

    public Annotations(byte b, int i, int i2, AnnotationEntry[] annotationEntryArr, ConstantPool constantPool, boolean z) {
        super(b, i, i2, constantPool);
        this.annotationTable = annotationEntryArr;
        this.isRuntimeVisible = z;
    }
}
