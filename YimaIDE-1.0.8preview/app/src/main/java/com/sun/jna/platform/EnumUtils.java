package com.sun.jna.platform;

import com.sun.jna.platform.win32.FlagEnum;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnumUtils {
    public static final int UNINITIALIZED = -1;

    public static <E extends Enum<E>> E fromInteger(int i, Class<E> cls) {
        if (i == -1) {
            return null;
        }
        return cls.getEnumConstants()[i];
    }

    public static <T extends FlagEnum> Set<T> setFromInteger(int i, Class<T> cls) {
        T[] enumConstants = cls.getEnumConstants();
        HashSet hashSet = new HashSet();
        for (T t : enumConstants) {
            if ((t.getFlag() & i) != 0) {
                hashSet.add(t);
            }
        }
        return hashSet;
    }

    public static <T extends FlagEnum> int setToInteger(Set<T> set) {
        Iterator<T> it = set.iterator();
        int flag = 0;
        while (it.hasNext()) {
            flag |= it.next().getFlag();
        }
        return flag;
    }

    public static <E extends Enum<E>> int toInteger(E e) {
        Enum[] enumArr = (Enum[]) e.getClass().getEnumConstants();
        for (int i = 0; i < enumArr.length; i++) {
            if (enumArr[i] == e) {
                return i;
            }
        }
        j2d.a();
        return 0;
    }
}
