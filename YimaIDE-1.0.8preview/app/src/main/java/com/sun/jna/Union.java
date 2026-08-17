package com.sun.jna;

import defpackage.h0f;
import defpackage.i0f;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Union extends Structure {
    private Structure.StructField activeField;

    public Union() {
    }

    private Structure.StructField findField(Class<?> cls) {
        ensureAllocated();
        for (Structure.StructField structField : fields().values()) {
            if (structField.type.isAssignableFrom(cls)) {
                return structField;
            }
        }
        return null;
    }

    @Override // com.sun.jna.Structure
    public List<String> getFieldOrder() {
        List<Field> fieldList = getFieldList();
        ArrayList arrayList = new ArrayList(fieldList.size());
        Iterator<Field> it = fieldList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        return arrayList;
    }

    @Override // com.sun.jna.Structure
    public int getNativeAlignment(Class<?> cls, Object obj, boolean z) {
        return super.getNativeAlignment(cls, obj, true);
    }

    public Object getTypedValue(Class<?> cls) {
        ensureAllocated();
        for (Structure.StructField structField : fields().values()) {
            if (structField.type == cls) {
                this.activeField = structField;
                read();
                return getFieldValue(this.activeField.field);
            }
        }
        h0f.a("No field of type ", cls, " in ", this);
        return null;
    }

    @Override // com.sun.jna.Structure
    public Object readField(Structure.StructField structField) {
        if (structField == this.activeField || !(Structure.class.isAssignableFrom(structField.type) || String.class.isAssignableFrom(structField.type) || WString.class.isAssignableFrom(structField.type))) {
            return super.readField(structField);
        }
        return null;
    }

    public void setType(Class<?> cls) {
        ensureAllocated();
        for (Structure.StructField structField : fields().values()) {
            if (structField.type == cls) {
                this.activeField = structField;
                return;
            }
        }
        h0f.a("No field of type ", cls, " in ", this);
    }

    public Object setTypedValue(Object obj) {
        Structure.StructField structFieldFindField = findField(obj.getClass());
        if (structFieldFindField == null) {
            i0f.a("No field of type ", obj.getClass(), " in ", this);
            return null;
        }
        this.activeField = structFieldFindField;
        setFieldValue(structFieldFindField.field, obj);
        return this;
    }

    @Override // com.sun.jna.Structure
    public void writeField(String str) {
        ensureAllocated();
        setType(str);
        super.writeField(str);
    }

    public Union(Pointer pointer) {
        super(pointer);
    }

    public Union(Pointer pointer, int i) {
        super(pointer, i);
    }

    public Union(TypeMapper typeMapper) {
        super(typeMapper);
    }

    public Union(Pointer pointer, int i, TypeMapper typeMapper) {
        super(pointer, i, typeMapper);
    }

    @Override // com.sun.jna.Structure
    public void writeField(String str, Object obj) {
        ensureAllocated();
        setType(str);
        super.writeField(str, obj);
    }

    @Override // com.sun.jna.Structure
    public void writeField(Structure.StructField structField) {
        if (structField == this.activeField) {
            super.writeField(structField);
        }
    }

    @Override // com.sun.jna.Structure
    public Object readField(String str) {
        ensureAllocated();
        setType(str);
        return super.readField(str);
    }

    public void setType(String str) {
        ensureAllocated();
        Structure.StructField structField = fields().get(str);
        if (structField != null) {
            this.activeField = structField;
        } else {
            h0f.a("No field named ", str, " in ", this);
        }
    }
}
