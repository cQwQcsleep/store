package com.shadow.kotlin.time;

import com.shadow.kotlin.enums.EnumEntries;
import com.shadow.kotlin.enums.EnumEntriesKt;
import java.util.concurrent.TimeUnit;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class DurationUnit {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ kotlin.time.DurationUnit[] $VALUES;
    public static final DurationUnit DAYS;
    public static final DurationUnit HOURS;
    public static final DurationUnit MICROSECONDS;
    public static final DurationUnit MILLISECONDS;
    public static final DurationUnit MINUTES;
    public static final DurationUnit NANOSECONDS;
    public static final DurationUnit SECONDS;
    private final TimeUnit timeUnit;

    static {
        kotlin.time.DurationUnit durationUnit = new DurationUnit("NANOSECONDS", 0, TimeUnit.NANOSECONDS);
        NANOSECONDS = durationUnit;
        kotlin.time.DurationUnit durationUnit2 = new DurationUnit("MICROSECONDS", 1, TimeUnit.MICROSECONDS);
        MICROSECONDS = durationUnit2;
        kotlin.time.DurationUnit durationUnit3 = new DurationUnit("MILLISECONDS", 2, TimeUnit.MILLISECONDS);
        MILLISECONDS = durationUnit3;
        kotlin.time.DurationUnit durationUnit4 = new DurationUnit("SECONDS", 3, TimeUnit.SECONDS);
        SECONDS = durationUnit4;
        kotlin.time.DurationUnit durationUnit5 = new DurationUnit("MINUTES", 4, TimeUnit.MINUTES);
        MINUTES = durationUnit5;
        kotlin.time.DurationUnit durationUnit6 = new DurationUnit("HOURS", 5, TimeUnit.HOURS);
        HOURS = durationUnit6;
        kotlin.time.DurationUnit durationUnit7 = new DurationUnit("DAYS", 6, TimeUnit.DAYS);
        DAYS = durationUnit7;
        kotlin.time.DurationUnit[] durationUnitArr = {durationUnit, durationUnit2, durationUnit3, durationUnit4, durationUnit5, durationUnit6, durationUnit7};
        $VALUES = durationUnitArr;
        $ENTRIES = EnumEntriesKt.enumEntries(durationUnitArr);
    }

    private DurationUnit(String str, int i, TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public static kotlin.enums.EnumEntries<kotlin.time.DurationUnit> getEntries() {
        return $ENTRIES;
    }

    public static DurationUnit valueOf(String str) {
        return (DurationUnit) Enum.valueOf(DurationUnit.class, str);
    }

    public static kotlin.time.DurationUnit[] values() {
        return (kotlin.time.DurationUnit[]) $VALUES.clone();
    }

    public final TimeUnit getTimeUnit$kotlin_stdlib() {
        return this.timeUnit;
    }
}
