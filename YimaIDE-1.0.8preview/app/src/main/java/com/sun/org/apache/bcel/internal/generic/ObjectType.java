package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Repository;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Utility;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ObjectType extends ReferenceType {
    private final String className;

    public ObjectType(String str) {
        super((byte) 14, "L" + Utility.packageToPath(str) + ";");
        this.className = Utility.pathToPackage(str);
    }

    public static ObjectType getInstance(String str) {
        return new ObjectType(str);
    }

    public boolean accessibleTo(ObjectType objectType) throws ClassNotFoundException {
        JavaClass javaClassLookupClass = Repository.lookupClass(this.className);
        if (javaClassLookupClass.isPublic()) {
            return true;
        }
        return Repository.lookupClass(objectType.className).getPackageName().equals(javaClassLookupClass.getPackageName());
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Type
    public boolean equals(Object obj) {
        return (obj instanceof ObjectType) && ((ObjectType) obj).className.equals(this.className);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Type
    public String getClassName() {
        return this.className;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Type
    public int hashCode() {
        return this.className.hashCode();
    }

    @Deprecated
    public boolean referencesClass() {
        try {
            return Repository.lookupClass(this.className).isClass();
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public boolean referencesClassExact() throws ClassNotFoundException {
        return Repository.lookupClass(this.className).isClass();
    }

    @Deprecated
    public boolean referencesInterface() {
        try {
            return !Repository.lookupClass(this.className).isClass();
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public boolean referencesInterfaceExact() throws ClassNotFoundException {
        return !Repository.lookupClass(this.className).isClass();
    }

    public boolean subclassOf(ObjectType objectType) throws ClassNotFoundException {
        if (referencesInterfaceExact() || objectType.referencesInterfaceExact()) {
            return false;
        }
        return Repository.instanceOf(this.className, objectType.className);
    }
}
