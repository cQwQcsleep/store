package com.intellij.util.containers;

import com.intellij.util.IncorrectOperationException;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class RefValueHashMapUtil {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "map", "com/intellij/util/containers/RefValueHashMapUtil", "expungeStaleEntries"));
    }

    public static void expungeStaleEntries(Map<?, ?> map) {
        if (map == null) {
            $$$reportNull$$$0(0);
        }
        if (map instanceof ReferenceQueueable) {
            ((ReferenceQueueable) map).processQueue();
        } else if (map instanceof java.util.WeakHashMap) {
            map.size();
        }
    }

    public static IncorrectOperationException pointlessContainsKey() {
        return new IncorrectOperationException("containsKey() makes no sense for weak/soft map because GC can clear the value any moment now");
    }

    public static IncorrectOperationException pointlessContainsValue() {
        return new IncorrectOperationException("containsValue() makes no sense for weak/soft map because GC can clear the key any moment now");
    }
}
