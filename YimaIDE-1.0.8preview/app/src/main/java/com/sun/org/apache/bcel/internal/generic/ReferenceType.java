package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.Repository;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ReferenceType extends Type {
    public ReferenceType() {
        super((byte) 14, "<null object>");
    }

    private ReferenceType getFirstCommonSuperclassInternal(ReferenceType referenceType) throws ClassNotFoundException {
        if (((this instanceof ObjectType) && ((ObjectType) this).referencesInterfaceExact()) || ((referenceType instanceof ObjectType) && ((ObjectType) referenceType).referencesInterfaceExact())) {
            return Type.OBJECT;
        }
        ObjectType objectType = (ObjectType) this;
        ObjectType objectType2 = (ObjectType) referenceType;
        JavaClass[] superClasses = Repository.getSuperClasses(objectType.getClassName());
        JavaClass[] superClasses2 = Repository.getSuperClasses(objectType2.getClassName());
        if (superClasses != null && superClasses2 != null) {
            int length = superClasses.length + 1;
            JavaClass[] javaClassArr = new JavaClass[length];
            int length2 = superClasses2.length + 1;
            JavaClass[] javaClassArr2 = new JavaClass[length2];
            System.arraycopy(superClasses, 0, javaClassArr, 1, superClasses.length);
            System.arraycopy(superClasses2, 0, javaClassArr2, 1, superClasses2.length);
            javaClassArr[0] = Repository.lookupClass(objectType.getClassName());
            javaClassArr2[0] = Repository.lookupClass(objectType2.getClassName());
            for (int i = 0; i < length2; i++) {
                JavaClass javaClass = javaClassArr2[i];
                for (int i2 = 0; i2 < length; i2++) {
                    JavaClass javaClass2 = javaClassArr[i2];
                    if (javaClass2.equals(javaClass)) {
                        return ObjectType.getInstance(javaClass2.getClassName());
                    }
                }
            }
        }
        return null;
    }

    @Deprecated
    public ReferenceType firstCommonSuperclass(ReferenceType referenceType) throws ClassNotFoundException {
        ReferenceType referenceType2 = Type.NULL;
        if (equals(referenceType2)) {
            return referenceType;
        }
        if (referenceType.equals(referenceType2) || equals(referenceType)) {
            return this;
        }
        return ((this instanceof ArrayType) || (referenceType instanceof ArrayType)) ? Type.OBJECT : getFirstCommonSuperclassInternal(referenceType);
    }

    public ReferenceType getFirstCommonSuperclass(ReferenceType referenceType) throws ClassNotFoundException {
        ReferenceType referenceType2 = Type.NULL;
        if (equals(referenceType2)) {
            return referenceType;
        }
        if (referenceType.equals(referenceType2) || equals(referenceType)) {
            return this;
        }
        boolean z = this instanceof ArrayType;
        if (z && (referenceType instanceof ArrayType)) {
            ArrayType arrayType = (ArrayType) this;
            ArrayType arrayType2 = (ArrayType) referenceType;
            if (arrayType.getDimensions() == arrayType2.getDimensions() && (arrayType.getBasicType() instanceof ObjectType) && (arrayType2.getBasicType() instanceof ObjectType)) {
                return new ArrayType(((ObjectType) arrayType.getBasicType()).getFirstCommonSuperclass((ObjectType) arrayType2.getBasicType()), arrayType.getDimensions());
            }
        }
        return (z || (referenceType instanceof ArrayType)) ? Type.OBJECT : getFirstCommonSuperclassInternal(referenceType);
    }

    public boolean isAssignmentCompatibleWith(Type type) throws ClassNotFoundException {
        if (!(type instanceof ReferenceType)) {
            return false;
        }
        ReferenceType referenceType = (ReferenceType) type;
        if (equals(Type.NULL)) {
            return true;
        }
        boolean z = this instanceof ObjectType;
        if (z) {
            ObjectType objectType = (ObjectType) this;
            if (objectType.referencesClassExact()) {
                boolean z2 = referenceType instanceof ObjectType;
                if (z2) {
                    ObjectType objectType2 = (ObjectType) referenceType;
                    if (objectType2.referencesClassExact() && (equals(referenceType) || Repository.instanceOf(objectType.getClassName(), objectType2.getClassName()))) {
                        return true;
                    }
                }
                if (z2) {
                    ObjectType objectType3 = (ObjectType) referenceType;
                    if (objectType3.referencesInterfaceExact() && Repository.implementationOf(objectType.getClassName(), objectType3.getClassName())) {
                        return true;
                    }
                }
            }
        }
        if (z) {
            ObjectType objectType4 = (ObjectType) this;
            if (objectType4.referencesInterfaceExact()) {
                boolean z3 = referenceType instanceof ObjectType;
                if (z3 && ((ObjectType) referenceType).referencesClassExact() && referenceType.equals(Type.OBJECT)) {
                    return true;
                }
                if (z3) {
                    ObjectType objectType5 = (ObjectType) referenceType;
                    if (objectType5.referencesInterfaceExact() && (equals(referenceType) || Repository.implementationOf(objectType4.getClassName(), objectType5.getClassName()))) {
                        return true;
                    }
                }
            }
        }
        if (this instanceof ArrayType) {
            boolean z4 = referenceType instanceof ObjectType;
            if (z4 && ((ObjectType) referenceType).referencesClassExact() && referenceType.equals(Type.OBJECT)) {
                return true;
            }
            if (referenceType instanceof ArrayType) {
                Type elementType = ((ArrayType) this).getElementType();
                Type elementType2 = ((ArrayType) referenceType).getElementType();
                if ((elementType instanceof BasicType) && (elementType2 instanceof BasicType) && elementType.equals(elementType2)) {
                    return true;
                }
                if ((elementType2 instanceof ReferenceType) && (elementType instanceof ReferenceType) && ((ReferenceType) elementType).isAssignmentCompatibleWith(elementType2)) {
                    return true;
                }
            }
            if (z4 && ((ObjectType) referenceType).referencesInterfaceExact()) {
                Iterator<String> it = Const.getInterfacesImplementedByArrays().iterator();
                while (it.hasNext()) {
                    if (referenceType.equals(ObjectType.getInstance(it.next()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCastableTo(Type type) throws ClassNotFoundException {
        return equals(Type.NULL) ? type instanceof ReferenceType : isAssignmentCompatibleWith(type);
    }

    public ReferenceType(byte b, String str) {
        super(b, str);
    }
}
