package com.shadow.kotlin.coroutines.intrinsics;

import com.shadow.kotlin.enums.EnumEntries;
import com.shadow.kotlin.enums.EnumEntriesKt;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class CoroutineSingletons {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ kotlin.coroutines.intrinsics.CoroutineSingletons[] $VALUES;
    public static final CoroutineSingletons COROUTINE_SUSPENDED;
    public static final CoroutineSingletons RESUMED;
    public static final CoroutineSingletons UNDECIDED;

    static {
        kotlin.coroutines.intrinsics.CoroutineSingletons coroutineSingletons = new CoroutineSingletons("COROUTINE_SUSPENDED", 0);
        COROUTINE_SUSPENDED = coroutineSingletons;
        kotlin.coroutines.intrinsics.CoroutineSingletons coroutineSingletons2 = new CoroutineSingletons("UNDECIDED", 1);
        UNDECIDED = coroutineSingletons2;
        kotlin.coroutines.intrinsics.CoroutineSingletons coroutineSingletons3 = new CoroutineSingletons("RESUMED", 2);
        RESUMED = coroutineSingletons3;
        kotlin.coroutines.intrinsics.CoroutineSingletons[] coroutineSingletonsArr = {coroutineSingletons, coroutineSingletons2, coroutineSingletons3};
        $VALUES = coroutineSingletonsArr;
        $ENTRIES = EnumEntriesKt.enumEntries(coroutineSingletonsArr);
    }

    public static kotlin.enums.EnumEntries<kotlin.coroutines.intrinsics.CoroutineSingletons> getEntries() {
        return $ENTRIES;
    }

    public static CoroutineSingletons valueOf(String str) {
        return (CoroutineSingletons) Enum.valueOf(CoroutineSingletons.class, str);
    }

    public static kotlin.coroutines.intrinsics.CoroutineSingletons[] values() {
        return (kotlin.coroutines.intrinsics.CoroutineSingletons[]) $VALUES.clone();
    }
}
