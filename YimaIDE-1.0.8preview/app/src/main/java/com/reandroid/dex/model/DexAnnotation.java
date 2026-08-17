package com.reandroid.dex.model;

import com.reandroid.common.ArraySupplier;
import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.ArraySupplierIterator;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexAnnotation extends Dex implements Iterable<DexAnnotationElement> {
    private final AnnotatedProgram annotatedProgram;
    private final Dex declaring;
    private AnnotationItemKey mRemoved;
    private TypeKey typeKey;

    public DexAnnotation(Dex dex, AnnotatedProgram annotatedProgram, TypeKey typeKey) {
        this.declaring = dex;
        this.annotatedProgram = annotatedProgram;
        this.typeKey = typeKey;
    }

    private void applyRemoveSelf() {
        if (this.mRemoved != null) {
            this.typeKey = null;
            return;
        }
        AnnotationSetKey annotationSet = getAnnotationSet();
        AnnotationItemKey key = getKey();
        TypeKey type = this.typeKey;
        this.typeKey = null;
        if (key != null) {
            this.mRemoved = key;
            type = key.getType();
        }
        setAnnotation(annotationSet.remove(type));
    }

    private void checkRemoved() {
        AnnotationItemKey annotationItemKey = this.mRemoved;
        if (annotationItemKey != null) {
            AnnotationSetKey annotationSet = getAnnotationSet();
            TypeKey type = annotationItemKey.getType();
            if (annotationSet != null) {
                if (!annotationSet.contains(type)) {
                    applyRemoveSelf();
                } else {
                    this.typeKey = type;
                    this.mRemoved = null;
                }
            }
        }
    }

    public static DexAnnotation create(Dex dex, AnnotatedProgram annotatedProgram, TypeKey typeKey) {
        if (dex == null || annotatedProgram == null || !annotatedProgram.hasAnnotation(typeKey)) {
            return null;
        }
        return new DexAnnotation(dex, annotatedProgram, typeKey);
    }

    private AnnotatedProgram getAnnotatedItem() {
        return this.annotatedProgram;
    }

    private AnnotationSetKey getAnnotationSet() {
        return getAnnotatedItem().getAnnotation();
    }

    private void setAnnotation(AnnotationSetKey annotationSetKey) {
        getAnnotatedItem().setAnnotation(annotationSetKey);
    }

    @Override // com.reandroid.dex.model.Dex, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        if (isRemoved()) {
            return;
        }
        getKey().append(smaliWriter);
    }

    public boolean contains(String str) {
        return getKey().containsElement(str);
    }

    public DexAnnotationElement get(int i) {
        return DexAnnotationElement.create(this, getKey().get(i));
    }

    public ElementType getAnnotationType() {
        Dex declaring = getDeclaring();
        if (declaring instanceof DexMethodParameter) {
            return ElementType.PARAMETER;
        }
        if (declaring instanceof DexMethod) {
            return ElementType.METHOD;
        }
        return declaring instanceof DexField ? ElementType.FIELD : ElementType.TYPE;
    }

    @Override // com.reandroid.dex.model.Dex
    public DexClassRepository getClassRepository() {
        return getDeclaring().getClassRepository();
    }

    public Dex getDeclaring() {
        return this.declaring;
    }

    public AnnotationItemKey getKey() {
        AnnotationItemKey annotationItemKey = this.mRemoved;
        return annotationItemKey == null ? getAnnotationSet().get(getType()) : annotationItemKey;
    }

    public DexAnnotationElement getOrCreate(String str) {
        if (!contains(str)) {
            setKey(getKey().getOrCreate(str));
        }
        return DexAnnotationElement.create(this, str);
    }

    public TypeKey getType() {
        return this.typeKey;
    }

    public AnnotationVisibility getVisibility() {
        return getKey().getVisibility();
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean isRemoved() {
        checkRemoved();
        return this.typeKey == null;
    }

    @Override // java.lang.Iterable
    public Iterator<DexAnnotationElement> iterator() {
        return ArraySupplierIterator.of(new ArraySupplier<DexAnnotationElement>() { // from class: com.reandroid.dex.model.DexAnnotation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.reandroid.common.ArraySupplier
            public DexAnnotationElement get(int i) {
                return DexAnnotation.this.get(i);
            }

            @Override // com.reandroid.common.CountSupplier
            public int getCount() {
                return DexAnnotation.this.size();
            }
        });
    }

    public void remove(String str) {
        setKey(getKey().remove(str));
    }

    @Override // com.reandroid.dex.model.Dex
    public void removeSelf() {
        if (isRemoved()) {
            return;
        }
        applyRemoveSelf();
    }

    public void setKey(Key key) {
        AnnotationItemKey annotationItemKey = (AnnotationItemKey) key;
        setAnnotation(getAnnotationSet().remove(getType()).add(annotationItemKey));
        this.typeKey = annotationItemKey.getType();
    }

    public void setType(TypeKey typeKey) {
        setKey(getKey().changeType(typeKey));
    }

    public void setVisibility(AnnotationVisibility annotationVisibility) {
        setKey(getKey().changeVisibility(annotationVisibility));
    }

    public int size() {
        return getKey().size();
    }

    @Override // com.reandroid.dex.model.Dex
    public String toString() {
        AnnotationItemKey key = getKey();
        if (!isRemoved()) {
            return String.valueOf(key);
        }
        return "#REMOVED " + key;
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean uses(Key key) {
        return getKey().uses(key);
    }

    public DexAnnotationElement get(String str) {
        return DexAnnotationElement.create(this, str);
    }

    public static DexAnnotation create(Dex dex, AnnotatedProgram annotatedProgram, AnnotationItemKey annotationItemKey) {
        if (dex == null || annotatedProgram == null || annotationItemKey == null) {
            return null;
        }
        return create(dex, annotatedProgram, annotationItemKey.getType());
    }
}
