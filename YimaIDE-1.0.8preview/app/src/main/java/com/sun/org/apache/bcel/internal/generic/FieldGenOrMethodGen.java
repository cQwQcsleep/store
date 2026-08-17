package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.AccessFlags;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import defpackage.aca;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class FieldGenOrMethodGen extends AccessFlags implements NamedAndTyped, Cloneable {
    private final List<AnnotationEntryGen> annotationList;
    private final List<Attribute> attributeList;

    @Deprecated
    protected ConstantPoolGen cp;

    @Deprecated
    protected String name;

    @Deprecated
    protected Type type;

    public FieldGenOrMethodGen() {
        this.attributeList = new ArrayList();
        this.annotationList = new ArrayList();
    }

    public void addAll(Attribute[] attributeArr) {
        Collections.addAll(this.attributeList, attributeArr);
    }

    public void addAnnotationEntry(AnnotationEntryGen annotationEntryGen) {
        this.annotationList.add(annotationEntryGen);
    }

    public void addAttribute(Attribute attribute) {
        this.attributeList.add(attribute);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new Error("Clone Not Supported");
        }
    }

    public AnnotationEntryGen[] getAnnotationEntries() {
        return (AnnotationEntryGen[]) this.annotationList.toArray(AnnotationEntryGen.EMPTY_ARRAY);
    }

    public Attribute[] getAttributes() {
        return (Attribute[]) this.attributeList.toArray(Attribute.EMPTY_ARRAY);
    }

    public ConstantPoolGen getConstantPool() {
        return this.cp;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.NamedAndTyped
    public String getName() {
        return this.name;
    }

    public abstract String getSignature();

    @Override // com.sun.org.apache.bcel.internal.generic.NamedAndTyped
    public Type getType() {
        return this.type;
    }

    public void removeAnnotationEntries() {
        this.annotationList.clear();
    }

    public void removeAnnotationEntry(AnnotationEntryGen annotationEntryGen) {
        this.annotationList.remove(annotationEntryGen);
    }

    public void removeAttribute(Attribute attribute) {
        this.attributeList.remove(attribute);
    }

    public void removeAttributes() {
        this.attributeList.clear();
    }

    public void setConstantPool(ConstantPoolGen constantPoolGen) {
        this.cp = constantPoolGen;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.NamedAndTyped
    public void setName(String str) {
        this.name = str;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.NamedAndTyped
    public void setType(Type type) {
        if (type.getType() != 16) {
            this.type = type;
        } else {
            aca.a("Type can not be ", type);
        }
    }

    public FieldGenOrMethodGen(int i) {
        super(i);
        this.attributeList = new ArrayList();
        this.annotationList = new ArrayList();
    }
}
