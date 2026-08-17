package com.intellij.util;

import com.intellij.util.CommonProcessors;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class CommonProcessors {
    private static final Processor<Object> FALSE = new Processor() { // from class: a82
        @Override // com.intellij.util.Processor
        public final boolean process(Object obj) {
            return CommonProcessors.a(obj);
        }
    };
    private static final Processor<Object> TRUE = new Processor() { // from class: b82
        @Override // com.intellij.util.Processor
        public final boolean process(Object obj) {
            return CommonProcessors.b(obj);
        }
    };

    public static class FindFirstProcessor<T> extends FindProcessor<T> {
        @Override // com.intellij.util.CommonProcessors.FindProcessor
        public boolean accept(T t) {
            return true;
        }
    }

    public static abstract class FindProcessor<T> implements Processor<T> {
        private T myValue;

        public abstract boolean accept(T t);

        public T getFoundValue() {
            return this.myValue;
        }

        public boolean isFound() {
            return this.myValue != null;
        }

        @Override // com.intellij.util.Processor
        public boolean process(T t) {
            if (!accept(t)) {
                return true;
            }
            this.myValue = t;
            return false;
        }

        public T reset() {
            T t = this.myValue;
            this.myValue = null;
            return t;
        }
    }

    public static /* synthetic */ boolean a(Object obj) {
        return false;
    }

    public static /* synthetic */ boolean b(Object obj) {
        return true;
    }

    public static class CollectProcessor<T> implements Processor<T> {
        private final Collection<T> myCollection;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[(i == 2 || i == 3) ? 2 : 3];
            if (i == 1) {
                objArr[0] = "a";
            } else if (i == 2 || i == 3) {
                objArr[0] = "com/intellij/util/CommonProcessors$CollectProcessor";
            } else {
                objArr[0] = "collection";
            }
            if (i == 2) {
                objArr[1] = "toArray";
            } else if (i != 3) {
                objArr[1] = "com/intellij/util/CommonProcessors$CollectProcessor";
            } else {
                objArr[1] = "getResults";
            }
            if (i == 1) {
                objArr[2] = "toArray";
            } else if (i != 2 && i != 3) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i != 2 && i != 3) {
                throw new IllegalArgumentException(str2);
            }
            throw new IllegalStateException(str2);
        }

        public CollectProcessor(Collection<T> collection) {
            if (collection == null) {
                $$$reportNull$$$0(0);
            }
            this.myCollection = collection;
        }

        public boolean accept(T t) {
            return true;
        }

        @Override // com.intellij.util.Processor
        public boolean process(T t) {
            if (!accept(t)) {
                return true;
            }
            this.myCollection.add(t);
            return true;
        }

        public CollectProcessor() {
            this.myCollection = new ArrayList();
        }
    }
}
