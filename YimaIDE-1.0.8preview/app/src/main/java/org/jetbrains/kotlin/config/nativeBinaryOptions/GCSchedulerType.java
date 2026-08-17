package org.jetbrains.kotlin.config.nativeBinaryOptions;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0015\b\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0003\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/GCSchedulerType;", Argument.Delimiters.none, "deprecatedWithReplacement", "<init>", "(Ljava/lang/String;ILorg/jetbrains/kotlin/config/nativeBinaryOptions/GCSchedulerType;)V", "getDeprecatedWithReplacement", "()Lorg/jetbrains/kotlin/config/nativeBinaryOptions/GCSchedulerType;", "MANUAL", "ADAPTIVE", "AGGRESSIVE", "DISABLED", "WITH_TIMER", "ON_SAFE_POINTS", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum GCSchedulerType {
    MANUAL(null, 1, null),
    ADAPTIVE(null, 1, null),
    AGGRESSIVE(null, 1, null),
    DISABLED(MANUAL),
    WITH_TIMER(ADAPTIVE),
    ON_SAFE_POINTS(ADAPTIVE);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final GCSchedulerType deprecatedWithReplacement;

    /* synthetic */ GCSchedulerType(GCSchedulerType gCSchedulerType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : gCSchedulerType);
    }

    public static EnumEntries<GCSchedulerType> getEntries() {
        return $ENTRIES;
    }

    public final GCSchedulerType getDeprecatedWithReplacement() {
        return this.deprecatedWithReplacement;
    }

    GCSchedulerType(GCSchedulerType gCSchedulerType) {
        this.deprecatedWithReplacement = gCSchedulerType;
    }
}
