package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import java.util.Stack;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassStack {
    private final Stack<JavaClass> stack = new Stack<>();

    public boolean empty() {
        return this.stack.empty();
    }

    public JavaClass pop() {
        return this.stack.pop();
    }

    public void push(JavaClass javaClass) {
        this.stack.push(javaClass);
    }

    public JavaClass top() {
        return this.stack.peek();
    }
}
