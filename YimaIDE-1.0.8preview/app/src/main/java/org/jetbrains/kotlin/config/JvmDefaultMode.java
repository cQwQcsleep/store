package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/config/JvmDefaultMode;", Argument.Delimiters.none, "description", Argument.Delimiters.none, "oldDescription", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getOldDescription", "DISABLE", "ENABLE", "NO_COMPATIBILITY", "isEnabled", Argument.Delimiters.none, "()Z", "Companion", "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum JvmDefaultMode {
    DISABLE("disable", "disable"),
    ENABLE("enable", "all-compatibility"),
    NO_COMPATIBILITY("no-compatibility", "all");

    private final String description;
    private final String oldDescription;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    JvmDefaultMode(String str, String str2) {
        this.description = str;
        this.oldDescription = str2;
    }

    @JvmStatic
    public static final JvmDefaultMode fromStringOrNull(String str) {
        return INSTANCE.fromStringOrNull(str);
    }

    @JvmStatic
    public static final JvmDefaultMode fromStringOrNullOld(String str) {
        return INSTANCE.fromStringOrNullOld(str);
    }

    public static EnumEntries<JvmDefaultMode> getEntries() {
        return $ENTRIES;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getOldDescription() {
        return this.oldDescription;
    }

    public final boolean isEnabled() {
        return this != DISABLE;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/config/JvmDefaultMode$Companion;", Argument.Delimiters.none, "<init>", "()V", "fromStringOrNull", "Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "string", Argument.Delimiters.none, "fromStringOrNullOld", "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final JvmDefaultMode fromStringOrNull(String string) {
            JvmDefaultMode jvmDefaultMode = JvmDefaultMode.DISABLE;
            if (Intrinsics.areEqual(string, jvmDefaultMode.getDescription())) {
                return jvmDefaultMode;
            }
            JvmDefaultMode jvmDefaultMode2 = JvmDefaultMode.ENABLE;
            if (Intrinsics.areEqual(string, jvmDefaultMode2.getDescription())) {
                return jvmDefaultMode2;
            }
            JvmDefaultMode jvmDefaultMode3 = JvmDefaultMode.NO_COMPATIBILITY;
            if (Intrinsics.areEqual(string, jvmDefaultMode3.getDescription())) {
                return jvmDefaultMode3;
            }
            return null;
        }

        @JvmStatic
        public final JvmDefaultMode fromStringOrNullOld(String string) {
            JvmDefaultMode jvmDefaultMode = JvmDefaultMode.DISABLE;
            if (Intrinsics.areEqual(string, jvmDefaultMode.getOldDescription())) {
                return jvmDefaultMode;
            }
            JvmDefaultMode jvmDefaultMode2 = JvmDefaultMode.ENABLE;
            if (Intrinsics.areEqual(string, jvmDefaultMode2.getOldDescription())) {
                return jvmDefaultMode2;
            }
            JvmDefaultMode jvmDefaultMode3 = JvmDefaultMode.NO_COMPATIBILITY;
            if (Intrinsics.areEqual(string, jvmDefaultMode3.getOldDescription())) {
                return jvmDefaultMode3;
            }
            return null;
        }

        private Companion() {
        }
    }
}
