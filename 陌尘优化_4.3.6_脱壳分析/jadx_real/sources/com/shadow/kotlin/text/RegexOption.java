package com.shadow.kotlin.text;

import com.shadow.kotlin.enums.EnumEntries;
import com.shadow.kotlin.enums.EnumEntriesKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class RegexOption implements kotlin.text.FlagEnum {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ kotlin.text.RegexOption[] $VALUES;
    public static final RegexOption CANON_EQ;
    public static final RegexOption COMMENTS;
    public static final RegexOption DOT_MATCHES_ALL;
    public static final RegexOption IGNORE_CASE;
    public static final RegexOption LITERAL;
    public static final RegexOption MULTILINE;
    public static final RegexOption UNIX_LINES;
    private final int mask;
    private final int value;

    static {
        kotlin.text.RegexOption regexOption = new RegexOption("IGNORE_CASE", 0, 2, 0, 2, null);
        IGNORE_CASE = regexOption;
        kotlin.text.RegexOption regexOption2 = new RegexOption("MULTILINE", 1, 8, 0, 2, null);
        MULTILINE = regexOption2;
        kotlin.text.RegexOption regexOption3 = new RegexOption("LITERAL", 2, 16, 0, 2, null);
        LITERAL = regexOption3;
        kotlin.text.RegexOption regexOption4 = new RegexOption("UNIX_LINES", 3, 1, 0, 2, null);
        UNIX_LINES = regexOption4;
        kotlin.text.RegexOption regexOption5 = new RegexOption("COMMENTS", 4, 4, 0, 2, null);
        COMMENTS = regexOption5;
        kotlin.text.RegexOption regexOption6 = new RegexOption("DOT_MATCHES_ALL", 5, 32, 0, 2, null);
        DOT_MATCHES_ALL = regexOption6;
        kotlin.text.RegexOption regexOption7 = new RegexOption("CANON_EQ", 6, 128, 0, 2, null);
        CANON_EQ = regexOption7;
        kotlin.text.RegexOption[] regexOptionArr = {regexOption, regexOption2, regexOption3, regexOption4, regexOption5, regexOption6, regexOption7};
        $VALUES = regexOptionArr;
        $ENTRIES = EnumEntriesKt.enumEntries(regexOptionArr);
    }

    public RegexOption(String str, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        i3 = (i4 & 2) != 0 ? i2 : i3;
        this.value = i2;
        this.mask = i3;
    }

    public static kotlin.enums.EnumEntries<kotlin.text.RegexOption> getEntries() {
        return $ENTRIES;
    }

    public static RegexOption valueOf(String str) {
        return (RegexOption) Enum.valueOf(RegexOption.class, str);
    }

    public static kotlin.text.RegexOption[] values() {
        return (kotlin.text.RegexOption[]) $VALUES.clone();
    }

    public int getMask() {
        return this.mask;
    }

    public int getValue() {
        return this.value;
    }
}
