package com.reandroid.dex.model;

import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexAnnotationElement extends Dex {
    private final DexAnnotation dexAnnotation;
    private AnnotationElementKey mRemoved;
    private String name;

    public DexAnnotationElement(DexAnnotation dexAnnotation, String str) {
        this.dexAnnotation = dexAnnotation;
        this.name = str;
    }

    private void applyRemoveSelf() {
        if (this.mRemoved != null) {
            this.name = null;
            return;
        }
        AnnotationElementKey key = getKey();
        this.name = null;
        if (key != null) {
            this.mRemoved = key;
            getDexAnnotation().remove(key.getName());
        }
    }

    private void checkRemoved() {
        DexAnnotation dexAnnotation = getDexAnnotation();
        if (dexAnnotation.isRemoved()) {
            applyRemoveSelf();
            return;
        }
        AnnotationElementKey annotationElementKey = this.mRemoved;
        if (annotationElementKey == null || dexAnnotation.isRemoved()) {
            return;
        }
        String name = annotationElementKey.getName();
        if (dexAnnotation.contains(annotationElementKey.getName())) {
            this.name = name;
            this.mRemoved = null;
        }
    }

    public static DexAnnotationElement create(DexAnnotation dexAnnotation, AnnotationElementKey annotationElementKey) {
        if (dexAnnotation == null || annotationElementKey == null) {
            return null;
        }
        return create(dexAnnotation, annotationElementKey.getName());
    }

    @Override // com.reandroid.dex.model.Dex, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        if (isRemoved()) {
            return;
        }
        getKey().append(smaliWriter);
    }

    @Override // com.reandroid.dex.model.Dex
    public DexClassRepository getClassRepository() {
        return getDexAnnotation().getClassRepository();
    }

    public DexAnnotation getDexAnnotation() {
        return this.dexAnnotation;
    }

    public AnnotationElementKey getKey() {
        AnnotationElementKey annotationElementKey = this.mRemoved;
        return annotationElementKey == null ? getDexAnnotation().getKey().get(getName()) : annotationElementKey;
    }

    public String getName() {
        return this.name;
    }

    public TypeKey getType() {
        return getDexAnnotation().getType();
    }

    public Key getValue() {
        return getKey().getValue();
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean isRemoved() {
        checkRemoved();
        return this.name == null;
    }

    @Override // com.reandroid.dex.model.Dex
    public void removeSelf() {
        if (isRemoved()) {
            return;
        }
        applyRemoveSelf();
    }

    public void setKey(Key key) {
        AnnotationElementKey annotationElementKey = (AnnotationElementKey) key;
        DexAnnotation dexAnnotation = getDexAnnotation();
        dexAnnotation.setKey(dexAnnotation.getKey().remove(getName()).add(annotationElementKey));
        this.name = annotationElementKey.getName();
    }

    public void setName(String str) {
        setKey(getKey().changeName(str));
    }

    public void setValue(Key key) {
        setKey(getKey().changeValue(key));
    }

    @Override // com.reandroid.dex.model.Dex
    public String toString() {
        AnnotationElementKey key = getKey();
        if (!isRemoved()) {
            return String.valueOf(key);
        }
        return "#REMOVED " + key;
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean uses(Key key) {
        return getKey().uses(key);
    }

    public static DexAnnotationElement create(DexAnnotation dexAnnotation, String str) {
        if (dexAnnotation == null || str == null) {
            return null;
        }
        return new DexAnnotationElement(dexAnnotation, str);
    }
}
