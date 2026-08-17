package com.intellij.util.containers;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class HashSetInterner<T> extends Interner<T> {
    private final ObjectOpenHashSet<T> set = new ObjectOpenHashSet<>();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 3) ? 2 : 3];
        if (i == 1 || i == 3) {
            objArr[0] = "com/intellij/util/containers/HashSetInterner";
        } else {
            objArr[0] = "name";
        }
        if (i == 1) {
            objArr[1] = "intern";
        } else if (i != 3) {
            objArr[1] = "com/intellij/util/containers/HashSetInterner";
        } else {
            objArr[1] = "getValues";
        }
        if (i != 1) {
            if (i == 2) {
                objArr[2] = "get";
            } else if (i != 3) {
                objArr[2] = "intern";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // com.intellij.util.containers.Interner
    public void clear() {
        this.set.clear();
    }

    @Override // com.intellij.util.containers.Interner
    public Set<T> getValues() {
        ObjectOpenHashSet<T> objectOpenHashSet = this.set;
        if (objectOpenHashSet == null) {
            $$$reportNull$$$0(3);
        }
        return objectOpenHashSet;
    }

    @Override // com.intellij.util.containers.Interner
    public T intern(T t) {
        if (t == null) {
            $$$reportNull$$$0(0);
        }
        T t2 = (T) this.set.addOrGet(t);
        if (t2 == null) {
            $$$reportNull$$$0(1);
        }
        return t2;
    }
}
