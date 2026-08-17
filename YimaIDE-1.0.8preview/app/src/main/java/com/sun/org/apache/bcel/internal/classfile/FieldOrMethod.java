package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class FieldOrMethod extends AccessFlags implements Cloneable, Node {
    private AnnotationEntry[] annotationEntries;

    @java.lang.Deprecated
    protected Attribute[] attributes;

    @java.lang.Deprecated
    protected int attributes_count;

    @java.lang.Deprecated
    protected ConstantPool constant_pool;

    @java.lang.Deprecated
    protected int name_index;
    private boolean searchedForSignatureAttribute;
    private String signatureAttributeString;

    @java.lang.Deprecated
    protected int signature_index;

    public FieldOrMethod(DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), null, constantPool);
        int unsignedShort = dataInput.readUnsignedShort();
        this.attributes = new Attribute[unsignedShort];
        for (int i = 0; i < unsignedShort; i++) {
            this.attributes[i] = Attribute.readAttribute(dataInput, constantPool);
        }
        this.attributes_count = unsignedShort;
    }

    public FieldOrMethod copy_(final ConstantPool constantPool) {
        try {
            FieldOrMethod fieldOrMethod = (FieldOrMethod) clone();
            fieldOrMethod.constant_pool = constantPool;
            Attribute[] attributeArr = new Attribute[this.attributes.length];
            fieldOrMethod.attributes = attributeArr;
            fieldOrMethod.attributes_count = this.attributes_count;
            Arrays.setAll(attributeArr, new IntFunction() { // from class: po4
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return this.b.attributes[i].copy(constantPool);
                }
            });
            return fieldOrMethod;
        } catch (CloneNotSupportedException e) {
            e7f.a(e);
            return null;
        }
    }

    public final void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(super.getAccessFlags());
        dataOutputStream.writeShort(this.name_index);
        dataOutputStream.writeShort(this.signature_index);
        dataOutputStream.writeShort(this.attributes_count);
        Attribute[] attributeArr = this.attributes;
        if (attributeArr != null) {
            for (Attribute attribute : attributeArr) {
                attribute.dump(dataOutputStream);
            }
        }
    }

    public AnnotationEntry[] getAnnotationEntries() {
        if (this.annotationEntries == null) {
            this.annotationEntries = AnnotationEntry.createAnnotationEntries(getAttributes());
        }
        return this.annotationEntries;
    }

    public final Attribute[] getAttributes() {
        return this.attributes;
    }

    public final ConstantPool getConstantPool() {
        return this.constant_pool;
    }

    public final String getGenericSignature() {
        if (!this.searchedForSignatureAttribute) {
            boolean z = false;
            int i = 0;
            while (!z) {
                Attribute[] attributeArr = this.attributes;
                if (i >= attributeArr.length) {
                    break;
                }
                Attribute attribute = attributeArr[i];
                if (attribute instanceof Signature) {
                    this.signatureAttributeString = ((Signature) attribute).getSignature();
                    z = true;
                }
                i++;
            }
            this.searchedForSignatureAttribute = true;
        }
        return this.signatureAttributeString;
    }

    public final String getName() {
        return this.constant_pool.getConstantUtf8(this.name_index).getBytes();
    }

    public final int getNameIndex() {
        return this.name_index;
    }

    public final String getSignature() {
        return this.constant_pool.getConstantUtf8(this.signature_index).getBytes();
    }

    public final int getSignatureIndex() {
        return this.signature_index;
    }

    public final void setAttributes(Attribute[] attributeArr) {
        this.attributes = attributeArr;
        this.attributes_count = attributeArr != null ? attributeArr.length : 0;
    }

    public final void setConstantPool(ConstantPool constantPool) {
        this.constant_pool = constantPool;
    }

    public final void setNameIndex(int i) {
        this.name_index = i;
    }

    public final void setSignatureIndex(int i) {
        this.signature_index = i;
    }

    public FieldOrMethod() {
    }

    @java.lang.Deprecated
    public FieldOrMethod(DataInputStream dataInputStream, ConstantPool constantPool) throws IOException {
        this((DataInput) dataInputStream, constantPool);
    }

    public FieldOrMethod(FieldOrMethod fieldOrMethod) {
        this(fieldOrMethod.getAccessFlags(), fieldOrMethod.getNameIndex(), fieldOrMethod.getSignatureIndex(), fieldOrMethod.getAttributes(), fieldOrMethod.getConstantPool());
    }

    public FieldOrMethod(int i, int i2, int i3, Attribute[] attributeArr, ConstantPool constantPool) {
        super(i);
        this.name_index = i2;
        this.signature_index = i3;
        this.constant_pool = constantPool;
        setAttributes(attributeArr);
    }
}
