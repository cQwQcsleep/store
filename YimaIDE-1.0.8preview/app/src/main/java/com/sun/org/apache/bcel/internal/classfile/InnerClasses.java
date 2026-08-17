package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class InnerClasses extends Attribute implements Iterable<InnerClass> {
    private static final InnerClass[] EMPTY_INNER_CLASSE_ARRAY = new InnerClass[0];
    private InnerClass[] innerClasses;

    public InnerClasses(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (InnerClass[]) null, constantPool);
        int unsignedShort = dataInput.readUnsignedShort();
        this.innerClasses = new InnerClass[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.innerClasses[i3] = new InnerClass(dataInput);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitInnerClasses(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        InnerClasses innerClasses = (InnerClasses) clone();
        InnerClass[] innerClassArr = new InnerClass[this.innerClasses.length];
        innerClasses.innerClasses = innerClassArr;
        Arrays.setAll(innerClassArr, new IntFunction() { // from class: cq6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.innerClasses[i].copy();
            }
        });
        innerClasses.setConstantPool(constantPool);
        return innerClasses;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.innerClasses.length);
        for (InnerClass innerClass : this.innerClasses) {
            innerClass.dump(dataOutputStream);
        }
    }

    public InnerClass[] getInnerClasses() {
        return this.innerClasses;
    }

    @Override // java.lang.Iterable
    public Iterator<InnerClass> iterator() {
        return Stream.of((Object[]) this.innerClasses).iterator();
    }

    public void setInnerClasses(InnerClass[] innerClassArr) {
        if (innerClassArr == null) {
            innerClassArr = EMPTY_INNER_CLASSE_ARRAY;
        }
        this.innerClasses = innerClassArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InnerClasses(");
        sb.append(this.innerClasses.length);
        sb.append("):\n");
        for (InnerClass innerClass : this.innerClasses) {
            sb.append(innerClass.toString(super.getConstantPool()));
            sb.append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public InnerClasses(InnerClasses innerClasses) {
        this(innerClasses.getNameIndex(), innerClasses.getLength(), innerClasses.getInnerClasses(), innerClasses.getConstantPool());
    }

    public InnerClasses(int i, int i2, InnerClass[] innerClassArr, ConstantPool constantPool) {
        super((byte) 6, i, i2, constantPool);
        innerClassArr = innerClassArr == null ? EMPTY_INNER_CLASSE_ARRAY : innerClassArr;
        this.innerClasses = innerClassArr;
        Args.requireU2(innerClassArr.length, "innerClasses.length");
    }
}
