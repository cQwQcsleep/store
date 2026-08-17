package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import java.util.LinkedList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassQueue {

    @Deprecated
    protected LinkedList<JavaClass> vec = new LinkedList<>();

    public JavaClass dequeue() {
        return this.vec.removeFirst();
    }

    public boolean empty() {
        return this.vec.isEmpty();
    }

    public void enqueue(JavaClass javaClass) {
        this.vec.addLast(javaClass);
    }

    public String toString() {
        return this.vec.toString();
    }
}
