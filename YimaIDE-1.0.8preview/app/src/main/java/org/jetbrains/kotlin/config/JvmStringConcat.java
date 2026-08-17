package org.jetbrains.kotlin.config;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rj\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/config/JvmStringConcat;", Argument.Delimiters.none, "description", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "INLINE", "INDY_WITH_CONSTANTS", "INDY", "isDynamic", Argument.Delimiters.none, "()Z", "Companion", "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum JvmStringConcat {
    INLINE("inline"),
    INDY_WITH_CONSTANTS("indy-with-constants"),
    INDY("indy");

    private final String description;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    JvmStringConcat(String str) {
        this.description = str;
    }

    @JvmStatic
    public static final JvmStringConcat fromString(String str) {
        return INSTANCE.fromString(str);
    }

    public static EnumEntries<JvmStringConcat> getEntries() {
        return $ENTRIES;
    }

    public final String getDescription() {
        return this.description;
    }

    public final boolean isDynamic() {
        return this != INLINE;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/config/JvmStringConcat$Companion;", Argument.Delimiters.none, "<init>", "()V", "fromString", "Lorg/jetbrains/kotlin/config/JvmStringConcat;", "string", Argument.Delimiters.none, "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final JvmStringConcat fromString(String string) {
            Object next;
            string.getClass();
            Iterator it = JvmStringConcat.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (Intrinsics.areEqual(((JvmStringConcat) next).getDescription(), string)) {
                    return (JvmStringConcat) next;
                }
            }
            next = null;
            return (JvmStringConcat) next;
        }

        private Companion() {
        }
    }
}
