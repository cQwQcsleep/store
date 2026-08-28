package com.shadow.kotlin.enums;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class EnumEntriesKt {
    public static final <E extends Enum<E>> kotlin.enums.EnumEntries<E> enumEntries(E[] eArr) {
        return new EnumEntriesList(eArr);
    }
}
