package com.intellij.util.lang;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class ClassLoadingLocks {
    private final ConcurrentHashMap<String, WeakLockReference> map;
    private final ReferenceQueue<Object> queue;

    public static final class WeakLockReference extends WeakReference<Object> {
        final String className;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "lock";
            } else if (i != 2) {
                objArr[0] = "className";
            } else {
                objArr[0] = "q";
            }
            objArr[1] = "com/intellij/util/lang/ClassLoadingLocks$WeakLockReference";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private WeakLockReference(String str, Object obj, ReferenceQueue<Object> referenceQueue) {
            super(obj, referenceQueue);
            if (str == null) {
                $$$reportNull$$$0(0);
            }
            if (obj == null) {
                $$$reportNull$$$0(1);
            }
            if (referenceQueue == null) {
                $$$reportNull$$$0(2);
            }
            this.className = str;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3 || i == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3 || i == 4) ? 2 : 3];
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            objArr[0] = "com/intellij/util/lang/ClassLoadingLocks";
        } else {
            objArr[0] = "className";
        }
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            objArr[1] = "getOrCreateLock";
        } else {
            objArr[1] = "com/intellij/util/lang/ClassLoadingLocks";
        }
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            objArr[2] = "getOrCreateLock";
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static /* synthetic */ WeakLockReference a(ClassLoadingLocks classLoadingLocks, String str) {
        classLoadingLocks.getClass();
        return new WeakLockReference(str, new Object(), classLoadingLocks.queue);
    }

    private void removeExpired() {
        while (true) {
            WeakLockReference weakLockReference = (WeakLockReference) this.queue.poll();
            if (weakLockReference == null) {
                return;
            } else {
                this.map.remove(weakLockReference.className, weakLockReference);
            }
        }
    }

    public Object getOrCreateLock(String str) {
        WeakLockReference weakLockReferencePutIfAbsent;
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        Object obj = this.map.computeIfAbsent(str, new Function() { // from class: com.intellij.util.lang.a
            @Override // java.util.function.Function
            public final Object apply(Object obj2) {
                return ClassLoadingLocks.a(this.b, (String) obj2);
            }
        }).get();
        if (obj != null) {
            return obj;
        }
        Object obj2 = new Object();
        WeakLockReference weakLockReference = new WeakLockReference(str, obj2, this.queue);
        do {
            removeExpired();
            weakLockReferencePutIfAbsent = this.map.putIfAbsent(str, weakLockReference);
            if (weakLockReferencePutIfAbsent == null) {
                break;
            }
            Object obj3 = weakLockReferencePutIfAbsent.get();
            if (obj3 != null) {
                return obj3;
            }
        } while (!this.map.replace(str, weakLockReferencePutIfAbsent, weakLockReference));
        return obj2;
    }
}
