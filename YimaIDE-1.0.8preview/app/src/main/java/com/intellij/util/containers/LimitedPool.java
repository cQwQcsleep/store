package com.intellij.util.containers;

import com.intellij.util.ArrayUtil;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.MathUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class LimitedPool<T> {
    private final ObjectFactory<T> myFactory;
    private int myIndex;
    private final int myMaxCapacity;
    private Object[] myStorage;

    @FunctionalInterface
    public interface ObjectFactory<T> {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "t", "com/intellij/util/containers/LimitedPool$ObjectFactory", "cleanup"));
        }

        default void cleanup(T t) {
            if (t == null) {
                $$$reportNull$$$0(0);
            }
        }

        T create();
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2) ? 2 : 3];
        if (i == 1 || i == 2) {
            objArr[0] = "com/intellij/util/containers/LimitedPool";
        } else if (i != 3) {
            objArr[0] = "factory";
        } else {
            objArr[0] = "t";
        }
        if (i == 1 || i == 2) {
            objArr[1] = "alloc";
        } else {
            objArr[1] = "com/intellij/util/containers/LimitedPool";
        }
        if (i != 1 && i != 2) {
            if (i != 3) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "recycle";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public LimitedPool(int i, ObjectFactory<T> objectFactory) {
        if (objectFactory == null) {
            $$$reportNull$$$0(0);
        }
        this.myStorage = ArrayUtilRt.EMPTY_OBJECT_ARRAY;
        this.myMaxCapacity = i;
        this.myFactory = objectFactory;
    }

    private void ensureCapacity() {
        Object[] objArr = this.myStorage;
        if (objArr.length <= this.myIndex) {
            this.myStorage = ArrayUtil.realloc(this.myStorage, MathUtil.clamp((objArr.length * 3) / 2, 10, this.myMaxCapacity), ArrayUtil.OBJECT_ARRAY_FACTORY);
        }
    }

    public T alloc() {
        int i = this.myIndex;
        if (i == 0) {
            T tCreate = this.myFactory.create();
            if (tCreate == null) {
                $$$reportNull$$$0(1);
            }
            return tCreate;
        }
        int i2 = i - 1;
        this.myIndex = i2;
        Object[] objArr = this.myStorage;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        if (t == null) {
            $$$reportNull$$$0(2);
        }
        return t;
    }

    public void recycle(T t) {
        if (t == null) {
            $$$reportNull$$$0(3);
        }
        this.myFactory.cleanup(t);
        if (this.myIndex >= this.myMaxCapacity) {
            return;
        }
        ensureCapacity();
        Object[] objArr = this.myStorage;
        int i = this.myIndex;
        this.myIndex = i + 1;
        objArr[i] = t;
    }
}
