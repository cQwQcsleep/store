package org.jetbrains.kotlin.types;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/types/SmartcastStability;", "", "str", "", "description", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "STABLE_VALUE", "EXPECT_PROPERTY", "PROPERTY_WITH_GETTER", "ALIEN_PUBLIC_PROPERTY", "CAPTURED_VARIABLE", "MUTABLE_PROPERTY", "DELEGATED_PROPERTY", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public enum SmartcastStability {
    STABLE_VALUE("stable val", null, 2, null),
    EXPECT_PROPERTY("expect property", null, 2, null),
    PROPERTY_WITH_GETTER("custom getter", "property that has an open or custom getter"),
    ALIEN_PUBLIC_PROPERTY("alien public", "public API property declared in different module"),
    CAPTURED_VARIABLE("captured var", "local variable that is mutated in a capturing closure"),
    MUTABLE_PROPERTY("member", "mutable property that could be mutated concurrently"),
    DELEGATED_PROPERTY("delegate", "delegated property");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String description;
    private final String str;

    /* synthetic */ SmartcastStability(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? str : str2);
    }

    public static EnumEntries<SmartcastStability> getEntries() {
        return $ENTRIES;
    }

    public final String getDescription() {
        return this.description;
    }

    SmartcastStability(String str, String str2) {
        this.str = str;
        this.description = str2;
    }
}
