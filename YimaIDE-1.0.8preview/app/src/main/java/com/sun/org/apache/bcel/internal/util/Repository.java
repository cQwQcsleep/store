package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Repository {
    void clear();

    JavaClass findClass(String str);

    JavaClass loadClass(Class<?> cls) throws ClassNotFoundException;

    JavaClass loadClass(String str) throws ClassNotFoundException;

    void removeClass(JavaClass javaClass);

    void storeClass(JavaClass javaClass);
}
