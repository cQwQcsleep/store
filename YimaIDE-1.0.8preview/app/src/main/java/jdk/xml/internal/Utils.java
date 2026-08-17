package jdk.xml.internal;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Utils {
    public static Class<?>[] arraysAppend(Class<?>[] clsArr, Class<?>... clsArr2) {
        if (clsArr == null && clsArr2 == null) {
            return null;
        }
        if (clsArr2 == null) {
            return (Class[]) Arrays.copyOf(clsArr, clsArr.length);
        }
        if (clsArr == null) {
            return (Class[]) Arrays.copyOf(clsArr2, clsArr2.length);
        }
        Class<?>[] clsArr3 = (Class[]) Arrays.copyOf(clsArr, clsArr.length + clsArr2.length);
        System.arraycopy(clsArr2, 0, clsArr3, clsArr.length, clsArr2.length);
        return clsArr3;
    }
}
