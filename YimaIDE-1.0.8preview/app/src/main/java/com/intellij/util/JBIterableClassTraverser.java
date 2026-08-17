package com.intellij.util;

import com.intellij.util.containers.JBIterable;
import com.intellij.util.containers.JBTreeTraverser;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class JBIterableClassTraverser {
    private static final JBTreeTraverser<Class<?>> CLASS_TRAVERSER = JBTreeTraverser.from(new Function() { // from class: ba7
        @Override // com.intellij.util.Function
        public final Object fun(Object obj) {
            Class cls = (Class) obj;
            return JBIterable.of(cls.getSuperclass()).append((Object[]) cls.getInterfaces());
        }
    });

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/JBIterableClassTraverser", "classTraverser"));
    }

    public static JBTreeTraverser<Class<?>> classTraverser(Class<?> cls) {
        JBTreeTraverser<Class<?>> jBTreeTraverser = (JBTreeTraverser) CLASS_TRAVERSER.unique().withRoot(cls);
        if (jBTreeTraverser == null) {
            $$$reportNull$$$0(0);
        }
        return jBTreeTraverser;
    }
}
