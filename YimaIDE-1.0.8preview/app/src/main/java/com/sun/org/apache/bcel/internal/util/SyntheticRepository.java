package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.classfile.ClassParser;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import defpackage.aca;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SyntheticRepository implements Repository {
    private final Map<String, SoftReference<JavaClass>> loadedClasses = new HashMap();

    private SyntheticRepository() {
    }

    public static SyntheticRepository getInstance() {
        return new SyntheticRepository();
    }

    @Override // com.sun.org.apache.bcel.internal.util.Repository
    public void clear() {
        this.loadedClasses.clear();
    }

    @Override // com.sun.org.apache.bcel.internal.util.Repository
    public JavaClass findClass(String str) {
        SoftReference<JavaClass> softReference = this.loadedClasses.get(str);
        if (softReference == null) {
            return null;
        }
        return softReference.get();
    }

    @Override // com.sun.org.apache.bcel.internal.util.Repository
    public JavaClass loadClass(String str) throws ClassNotFoundException {
        if (str == null || str.isEmpty()) {
            aca.a("Invalid class name ", str);
            return null;
        }
        String strPathToPackage = Utility.pathToPackage(str);
        JavaClass javaClassFindClass = findClass(strPathToPackage);
        if (javaClassFindClass != null) {
            return javaClassFindClass;
        }
        IOException iOException = new IOException("Couldn't find: " + strPathToPackage + JavaClass.EXTENSION);
        throw new ClassNotFoundException("Exception while looking for class " + strPathToPackage + ": " + iOException, iOException);
    }

    @Override // com.sun.org.apache.bcel.internal.util.Repository
    public void removeClass(JavaClass javaClass) {
        this.loadedClasses.remove(javaClass.getClassName());
    }

    @Override // com.sun.org.apache.bcel.internal.util.Repository
    public void storeClass(JavaClass javaClass) {
        this.loadedClasses.put(javaClass.getClassName(), new SoftReference<>(javaClass));
        javaClass.setRepository(this);
    }

    private JavaClass loadClass(InputStream inputStream, String str) throws ClassNotFoundException {
        if (inputStream != null) {
            try {
                JavaClass javaClass = new ClassParser(inputStream, str).parse();
                storeClass(javaClass);
                return javaClass;
            } catch (IOException e) {
                throw new ClassNotFoundException("Exception while looking for class " + str + ": " + e, e);
            }
        }
        throw new ClassNotFoundException("ClassRepository could not load " + str);
    }

    @Override // com.sun.org.apache.bcel.internal.util.Repository
    public JavaClass loadClass(Class<?> cls) throws ClassNotFoundException {
        String name = cls.getName();
        JavaClass javaClassFindClass = findClass(name);
        if (javaClassFindClass != null) {
            return javaClassFindClass;
        }
        int iLastIndexOf = name.lastIndexOf(46);
        try {
            InputStream resourceAsStream = cls.getResourceAsStream((iLastIndexOf > 0 ? name.substring(iLastIndexOf + 1) : name).concat(JavaClass.EXTENSION));
            try {
                JavaClass javaClassLoadClass = loadClass(resourceAsStream, name);
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
                return javaClassLoadClass;
            } catch (Throwable th) {
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
