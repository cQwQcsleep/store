package com.reandroid.dex.dalvik;

import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.ArrayValueKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import com.reandroid.dex.program.ClassProgram;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DalvikMemberClass extends DalvikAnnotation {
    private DalvikMemberClass(AnnotatedProgram annotatedProgram) {
        super(annotatedProgram, TypeKey.DALVIK_MemberClass);
    }

    public static DalvikMemberClass getOrCreate(AnnotatedProgram annotatedProgram) {
        TypeKey typeKey = TypeKey.DALVIK_MemberClass;
        if (!annotatedProgram.hasAnnotation(typeKey)) {
            annotatedProgram.addAnnotation(AnnotationItemKey.create(AnnotationVisibility.SYSTEM, typeKey, AnnotationElementKey.create(Key.DALVIK_value, ArrayValueKey.empty())));
        }
        return of(annotatedProgram);
    }

    public static DalvikMemberClass of(AnnotatedProgram annotatedProgram) {
        if (annotatedProgram.hasAnnotation(TypeKey.DALVIK_MemberClass)) {
            return new DalvikMemberClass(annotatedProgram);
        }
        return null;
    }

    public void add(TypeKey typeKey) {
        setArray(getArray().remove((Key) typeKey).add((Key) typeKey).sort(CompareUtil.getComparableComparator()));
    }

    public void addSimpleName(TypeKey typeKey, String str) {
        if (typeKey != null) {
            add(typeKey.createInnerClass(str));
        } else {
            x0e.a("Null enclosing type");
        }
    }

    public boolean contains(TypeKey typeKey) {
        return getArray().contains(typeKey);
    }

    public TypeKey findEnclosing() {
        if (getAnnotatedProgram() instanceof ClassProgram) {
            return ((ClassProgram) getAnnotatedProgram()).getKey();
        }
        TypeKey typeKey = (TypeKey) CollectionUtil.getFirst(getMembers());
        return typeKey != null ? typeKey.getEnclosingClass() : typeKey;
    }

    public ArrayValueKey getArray() {
        ArrayValueKey arrayValueKey = (ArrayValueKey) readValue(Key.DALVIK_value);
        return arrayValueKey == null ? ArrayValueKey.empty() : arrayValueKey;
    }

    public Iterator<TypeKey> getMembers() {
        return getArray().iterator(TypeKey.class);
    }

    public boolean isEmpty() {
        return getArray().isEmpty();
    }

    public void remove(TypeKey typeKey) {
        setArray(getArray().remove((Key) typeKey).add((Key) typeKey));
    }

    public void removeIf(Predicate<? super TypeKey> predicate) {
        setArray(getArray().removeIf((Predicate<? super Key>) ObjectsUtil.cast(predicate)));
    }

    public void setArray(ArrayValueKey arrayValueKey) {
        if (arrayValueKey == null) {
            arrayValueKey = ArrayValueKey.empty();
        }
        writeValue(Key.DALVIK_value, arrayValueKey);
    }

    public int size() {
        return getArray().size();
    }

    @Override // com.reandroid.dex.dalvik.DalvikAnnotation
    public String toString() {
        return StringsUtil.join(getMembers(), "\n");
    }

    public void addSimpleName(String str) {
        addSimpleName(findEnclosing(), str);
    }
}
