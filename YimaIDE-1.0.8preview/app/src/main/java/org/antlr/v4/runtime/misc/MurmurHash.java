package org.antlr.v4.runtime.misc;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class MurmurHash {
    private static final int DEFAULT_SEED = 0;

    private MurmurHash() {
    }

    public static int finish(int i, int i2) {
        int i3 = i ^ (i2 * 4);
        int i4 = (i3 ^ (i3 >>> 16)) * (-2048144789);
        int i5 = (i4 ^ (i4 >>> 13)) * (-1028477387);
        return i5 ^ (i5 >>> 16);
    }

    public static <T> int hashCode(T[] tArr, int i) {
        int iInitialize = initialize(i);
        for (T t : tArr) {
            iInitialize = update(iInitialize, t);
        }
        return finish(iInitialize, tArr.length);
    }

    public static int initialize() {
        return initialize(0);
    }

    public static int update(int i, int i2) {
        int i3 = i2 * (-862048943);
        int i4 = i ^ (((i3 >>> 17) | (i3 << 15)) * 461845907);
        return (((i4 >>> 19) | (i4 << 13)) * 5) - 430675100;
    }

    public static int initialize(int i) {
        return i;
    }

    public static int update(int i, Object obj) {
        return update(i, obj != null ? obj.hashCode() : 0);
    }
}
