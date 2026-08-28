package com.shadow.kotlin.reflect;

import com.shadow.kotlin.enums.EnumEntries;
import com.shadow.kotlin.enums.EnumEntriesKt;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class KVisibility {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ kotlin.reflect.KVisibility[] $VALUES;
    public static final KVisibility INTERNAL;
    public static final KVisibility PRIVATE;
    public static final KVisibility PROTECTED;
    public static final KVisibility PUBLIC;

    static {
        kotlin.reflect.KVisibility kVisibility = new KVisibility("PUBLIC", 0);
        PUBLIC = kVisibility;
        kotlin.reflect.KVisibility kVisibility2 = new KVisibility("PROTECTED", 1);
        PROTECTED = kVisibility2;
        kotlin.reflect.KVisibility kVisibility3 = new KVisibility("INTERNAL", 2);
        INTERNAL = kVisibility3;
        kotlin.reflect.KVisibility kVisibility4 = new KVisibility("PRIVATE", 3);
        PRIVATE = kVisibility4;
        kotlin.reflect.KVisibility[] kVisibilityArr = {kVisibility, kVisibility2, kVisibility3, kVisibility4};
        $VALUES = kVisibilityArr;
        $ENTRIES = EnumEntriesKt.enumEntries(kVisibilityArr);
    }

    public static kotlin.enums.EnumEntries<kotlin.reflect.KVisibility> getEntries() {
        return $ENTRIES;
    }

    public static KVisibility valueOf(String str) {
        return (KVisibility) Enum.valueOf(KVisibility.class, str);
    }

    public static kotlin.reflect.KVisibility[] values() {
        return (kotlin.reflect.KVisibility[]) $VALUES.clone();
    }
}
