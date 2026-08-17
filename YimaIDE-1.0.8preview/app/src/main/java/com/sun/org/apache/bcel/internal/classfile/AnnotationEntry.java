package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.classfile.AnnotationEntry;
import com.sun.org.apache.bcel.internal.classfile.Annotations;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationEntry implements Node {
    public static final AnnotationEntry[] EMPTY_ARRAY = new AnnotationEntry[0];
    private final ConstantPool constantPool;
    private List<ElementValuePair> elementValuePairs;
    private final boolean isRuntimeVisible;
    private final int typeIndex;

    public AnnotationEntry(int i, ConstantPool constantPool, boolean z) {
        this.typeIndex = i;
        this.constantPool = constantPool;
        this.isRuntimeVisible = z;
    }

    public static /* synthetic */ AnnotationEntry[] b(int i) {
        return new AnnotationEntry[i];
    }

    public static AnnotationEntry[] createAnnotationEntries(Attribute[] attributeArr) {
        final Class<Annotations> cls = Annotations.class;
        return (AnnotationEntry[]) Stream.of((Object[]) attributeArr).filter(new Predicate() { // from class: s80
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return cls.isInstance((Attribute) obj);
            }
        }).flatMap(new Function() { // from class: t80
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Stream.of((Object[]) ((Annotations) ((Attribute) obj)).getAnnotationEntries());
            }
        }).toArray(new IntFunction() { // from class: u80
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return AnnotationEntry.b(i);
            }
        });
    }

    public static AnnotationEntry read(DataInput dataInput, ConstantPool constantPool, boolean z) throws IOException {
        AnnotationEntry annotationEntry = new AnnotationEntry(dataInput.readUnsignedShort(), constantPool, z);
        int unsignedShort = dataInput.readUnsignedShort();
        annotationEntry.elementValuePairs = new ArrayList();
        for (int i = 0; i < unsignedShort; i++) {
            annotationEntry.elementValuePairs.add(new ElementValuePair(dataInput.readUnsignedShort(), ElementValue.readElementValue(dataInput, constantPool), constantPool));
        }
        return annotationEntry;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitAnnotationEntry(this);
    }

    public void addElementNameValuePair(ElementValuePair elementValuePair) {
        this.elementValuePairs.add(elementValuePair);
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.typeIndex);
        dataOutputStream.writeShort(this.elementValuePairs.size());
        Iterator<ElementValuePair> it = this.elementValuePairs.iterator();
        while (it.hasNext()) {
            it.next().dump(dataOutputStream);
        }
    }

    public String getAnnotationType() {
        return this.constantPool.getConstantUtf8(this.typeIndex).getBytes();
    }

    public int getAnnotationTypeIndex() {
        return this.typeIndex;
    }

    public ConstantPool getConstantPool() {
        return this.constantPool;
    }

    public ElementValuePair[] getElementValuePairs() {
        return (ElementValuePair[]) this.elementValuePairs.toArray(ElementValuePair.EMPTY_ARRAY);
    }

    public final int getNumElementValuePairs() {
        return this.elementValuePairs.size();
    }

    public int getTypeIndex() {
        return this.typeIndex;
    }

    public boolean isRuntimeVisible() {
        return this.isRuntimeVisible;
    }

    public String toShortString() {
        StringBuilder sb = new StringBuilder();
        sb.append("@");
        sb.append(getAnnotationType());
        ElementValuePair[] elementValuePairs = getElementValuePairs();
        if (elementValuePairs.length > 0) {
            sb.append("(");
            for (ElementValuePair elementValuePair : elementValuePairs) {
                sb.append(elementValuePair.toShortString());
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(")");
        }
        return sb.toString();
    }

    public String toString() {
        return toShortString();
    }
}
