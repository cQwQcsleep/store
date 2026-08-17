package com.sun.org.apache.bcel.internal;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.util.SyntheticRepository;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Repository {
    private static com.sun.org.apache.bcel.internal.util.Repository repository = SyntheticRepository.getInstance();

    public static JavaClass addClass(JavaClass javaClass) {
        JavaClass javaClassFindClass = repository.findClass(javaClass.getClassName());
        repository.storeClass(javaClass);
        return javaClassFindClass;
    }

    public static void clearCache() {
        repository.clear();
    }

    public static JavaClass[] getInterfaces(String str) throws ClassNotFoundException {
        return getInterfaces(lookupClass(str));
    }

    public static com.sun.org.apache.bcel.internal.util.Repository getRepository() {
        return repository;
    }

    public static JavaClass[] getSuperClasses(String str) throws ClassNotFoundException {
        return getSuperClasses(lookupClass(str));
    }

    public static boolean implementationOf(String str, String str2) throws ClassNotFoundException {
        return implementationOf(lookupClass(str), lookupClass(str2));
    }

    public static boolean instanceOf(String str, String str2) throws ClassNotFoundException {
        return instanceOf(lookupClass(str), lookupClass(str2));
    }

    public static JavaClass lookupClass(Class<?> cls) throws ClassNotFoundException {
        return repository.loadClass(cls);
    }

    public static void removeClass(String str) {
        com.sun.org.apache.bcel.internal.util.Repository repository2 = repository;
        repository2.removeClass(repository2.findClass(str));
    }

    public static void setRepository(com.sun.org.apache.bcel.internal.util.Repository repository2) {
        repository = repository2;
    }

    public static JavaClass lookupClass(String str) throws ClassNotFoundException {
        return repository.loadClass(str);
    }

    public static JavaClass[] getInterfaces(JavaClass javaClass) throws ClassNotFoundException {
        return javaClass.getAllInterfaces();
    }

    public static JavaClass[] getSuperClasses(JavaClass javaClass) throws ClassNotFoundException {
        return javaClass.getSuperClasses();
    }

    public static void removeClass(JavaClass javaClass) {
        repository.removeClass(javaClass);
    }

    public static boolean implementationOf(JavaClass javaClass, String str) throws ClassNotFoundException {
        return implementationOf(javaClass, lookupClass(str));
    }

    public static boolean instanceOf(JavaClass javaClass, String str) throws ClassNotFoundException {
        return instanceOf(javaClass, lookupClass(str));
    }

    public static boolean implementationOf(String str, JavaClass javaClass) throws ClassNotFoundException {
        return implementationOf(lookupClass(str), javaClass);
    }

    public static boolean instanceOf(String str, JavaClass javaClass) throws ClassNotFoundException {
        return instanceOf(lookupClass(str), javaClass);
    }

    public static boolean implementationOf(JavaClass javaClass, JavaClass javaClass2) throws ClassNotFoundException {
        return javaClass.implementationOf(javaClass2);
    }

    public static boolean instanceOf(JavaClass javaClass, JavaClass javaClass2) throws ClassNotFoundException {
        return javaClass.instanceOf(javaClass2);
    }
}
