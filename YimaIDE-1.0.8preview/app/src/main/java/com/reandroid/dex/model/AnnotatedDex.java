package com.reandroid.dex.model;

import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.ProgramKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.ProgramElement;
import com.reandroid.utils.collection.ComputeIterator;
import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface AnnotatedDex extends ProgramElement {
    @Override // com.reandroid.dex.program.AnnotatedProgram
    default void clearAnnotations() {
        getProgramElement().clearAnnotations();
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    default AnnotationSetKey getAnnotation() {
        return getProgramElement().getAnnotation();
    }

    /* JADX WARN: Multi-variable type inference failed */
    default DexAnnotation getDexAnnotation(TypeKey typeKey) {
        ProgramElement programElement = getProgramElement();
        if (programElement.hasAnnotation(typeKey)) {
            return new DexAnnotation((Dex) this, programElement, typeKey);
        }
        return null;
    }

    default DexAnnotationElement getDexAnnotationElement(TypeKey typeKey, String str) {
        DexAnnotation dexAnnotation = getDexAnnotation(typeKey);
        if (dexAnnotation != null) {
            return dexAnnotation.get(str);
        }
        return null;
    }

    default Iterator<DexAnnotation> getDexAnnotations() {
        return ComputeIterator.of(getProgramElement().getAnnotation().getTypes(), new Function() { // from class: i80
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.getDexAnnotation((TypeKey) obj);
            }
        });
    }

    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    default ProgramKey getKey() {
        return getProgramElement().getKey();
    }

    /* JADX WARN: Multi-variable type inference failed */
    default DexAnnotation getOrCreateDexAnnotation(TypeKey typeKey) {
        ProgramElement programElement = getProgramElement();
        AnnotationSetKey annotation = programElement.getAnnotation();
        if (!annotation.contains(typeKey)) {
            programElement.setAnnotation(annotation.getOrCreate(typeKey));
        }
        return new DexAnnotation((Dex) this, programElement, typeKey);
    }

    default DexAnnotationElement getOrCreateDexAnnotationElement(TypeKey typeKey, String str) {
        return getOrCreateDexAnnotation(typeKey).getOrCreate(str);
    }

    ProgramElement getProgramElement();

    @Override // com.reandroid.dex.program.AnnotatedProgram
    default boolean hasAnnotations() {
        return getProgramElement().hasAnnotations();
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    default void setAnnotation(AnnotationSetKey annotationSetKey) {
        getProgramElement().setAnnotation(annotationSetKey);
    }
}
