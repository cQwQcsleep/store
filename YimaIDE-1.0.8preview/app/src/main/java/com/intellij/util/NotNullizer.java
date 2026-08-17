package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class NotNullizer {
    private final Object myNull;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2) ? 2 : 3];
        if (i == 1 || i == 2) {
            objArr[0] = "com/intellij/util/NotNullizer";
        } else if (i != 3) {
            objArr[0] = "name";
        } else {
            objArr[0] = "value";
        }
        if (i == 1) {
            objArr[1] = "fakeNull";
        } else if (i != 2) {
            objArr[1] = "com/intellij/util/NotNullizer";
        } else {
            objArr[1] = "notNullize";
        }
        if (i != 1 && i != 2) {
            if (i != 3) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "nullize";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public NotNullizer(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        this.myNull = ObjectUtils.sentinel(str);
    }

    private <T> T fakeNull() {
        T t = (T) this.myNull;
        if (t == null) {
            $$$reportNull$$$0(1);
        }
        return t;
    }

    public <T> T notNullize(T t) {
        return t == null ? (T) fakeNull() : t;
    }

    public <T> T nullize(T t) {
        if (t == null) {
            $$$reportNull$$$0(3);
        }
        if (t == this.myNull) {
            return null;
        }
        return t;
    }
}
