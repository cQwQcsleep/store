package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassSet {
    private final Map<String, JavaClass> map = new HashMap();

    public boolean add(JavaClass javaClass) {
        return this.map.putIfAbsent(javaClass.getClassName(), javaClass) != null;
    }

    public boolean empty() {
        return this.map.isEmpty();
    }

    public String[] getClassNames() {
        return (String[]) this.map.keySet().toArray(Const.EMPTY_STRING_ARRAY);
    }

    public void remove(JavaClass javaClass) {
        this.map.remove(javaClass.getClassName());
    }

    public JavaClass[] toArray() {
        return (JavaClass[]) this.map.values().toArray(JavaClass.EMPTY_ARRAY);
    }
}
