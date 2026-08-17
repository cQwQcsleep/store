package com.sun.tools.javac.code;

import com.sun.tools.javac.model.AnnotationProxyMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import defpackage.aca;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Array;
import javax.lang.model.AnnotatedConstruct;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class AnnoConstruct implements AnnotatedConstruct {
    private static Class<? extends Annotation> getContainer(Class<? extends Annotation> cls) {
        Repeatable repeatable = (Repeatable) cls.getAnnotation(Repeatable.class);
        if (repeatable == null) {
            return null;
        }
        return repeatable.value();
    }

    private static Attribute[] unpackAttributes(Attribute.Compound compound) {
        return ((Attribute.Array) compound.member(compound.type.tsym.name.table.names.value)).values;
    }

    private Attribute.Compound[] unpackContained(Attribute.Compound compound) {
        Attribute[] attributeArrUnpackAttributes = compound != null ? unpackAttributes(compound) : null;
        ListBuffer listBuffer = new ListBuffer();
        if (attributeArrUnpackAttributes != null) {
            for (Attribute attribute : attributeArrUnpackAttributes) {
                if (attribute instanceof Attribute.Compound) {
                    listBuffer = listBuffer.append((Attribute.Compound) attribute);
                }
            }
        }
        return (Attribute.Compound[]) listBuffer.toArray(new Attribute.Compound[listBuffer.size()]);
    }

    @Override // javax.lang.model.AnnotatedConstruct
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        if (!cls.isAnnotation()) {
            aca.a("Not an annotation type: ", cls);
            return null;
        }
        Attribute.Compound attribute = getAttribute(cls);
        if (attribute == null) {
            return null;
        }
        return (A) AnnotationProxyMaker.generateAnnotation(attribute, cls);
    }

    @Override // javax.lang.model.AnnotatedConstruct
    public abstract List<? extends Attribute.Compound> getAnnotationMirrors();

    /* JADX WARN: Code duplicated, block: B:52:0x00be A[LOOP:1: B:50:0x00ba->B:52:0x00be, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:64:0x00c9 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.lang.model.AnnotatedConstruct
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> cls) {
        int i;
        Attribute.Compound compound = null;
        if (!cls.isAnnotation()) {
            aca.a("Not an annotation type: ", cls);
            return null;
        }
        Class<? extends Annotation> container = getContainer(cls);
        int i2 = 1;
        int i3 = 0;
        if (container == null) {
            Annotation annotation = getAnnotation(cls);
            A[] aArr = (A[]) ((Annotation[]) Array.newInstance((Class<?>) cls, annotation == null ? 0 : 1));
            if (annotation != null) {
                aArr[0] = annotation;
            }
            return aArr;
        }
        String name = cls.getName();
        String name2 = container.getName();
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        Attribute.Compound compound2 = null;
        for (Attribute.Compound compound3 : getAnnotationMirrors()) {
            i4++;
            if (compound3.type.tsym.flatName().contentEquals(name)) {
                i5 = i4;
                compound = compound3;
            } else if (compound3.type.tsym.flatName().contentEquals(name2)) {
                i6 = i4;
                compound2 = compound3;
            }
        }
        if (compound == null && compound2 == null && cls.isAnnotationPresent(Inherited.class)) {
            return (A[]) getInheritedAnnotations(cls);
        }
        Attribute.Compound[] compoundArrUnpackContained = unpackContained(compound2);
        if (compound == null && compoundArrUnpackContained.length == 0 && cls.isAnnotationPresent(Inherited.class)) {
            return (A[]) getInheritedAnnotations(cls);
        }
        A[] aArr2 = (A[]) ((Annotation[]) Array.newInstance((Class<?>) cls, (compound == null ? 0 : 1) + compoundArrUnpackContained.length));
        int length = aArr2.length;
        if (i5 >= 0 && i6 >= 0) {
            if (i5 < i6) {
                aArr2[0] = AnnotationProxyMaker.generateAnnotation(compound, cls);
            } else {
                aArr2[aArr2.length - 1] = AnnotationProxyMaker.generateAnnotation(compound, cls);
                length--;
            }
            while (true) {
                i = i3 + i2;
                if (i < length) {
                    return aArr2;
                }
                aArr2[i] = AnnotationProxyMaker.generateAnnotation(compoundArrUnpackContained[i3], cls);
                i3++;
            }
        } else if (i5 >= 0) {
            aArr2[0] = AnnotationProxyMaker.generateAnnotation(compound, cls);
            return aArr2;
        }
        i2 = 0;
        while (true) {
            i = i3 + i2;
            if (i < length) {
                return aArr2;
            }
            aArr2[i] = AnnotationProxyMaker.generateAnnotation(compoundArrUnpackContained[i3], cls);
            i3++;
        }
    }

    public <A extends Annotation> Attribute.Compound getAttribute(Class<A> cls) {
        String name = cls.getName();
        for (Attribute.Compound compound : getAnnotationMirrors()) {
            if (name.equals(compound.type.tsym.flatName().toString())) {
                return compound;
            }
        }
        return null;
    }

    public <A extends Annotation> A[] getInheritedAnnotations(Class<A> cls) {
        return (A[]) ((Annotation[]) Array.newInstance((Class<?>) cls, 0));
    }
}
