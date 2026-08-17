package org.jetbrains.kotlin.config;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\f\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u000b\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/config/DuplicatedUniqueNameStrategy;", Argument.Delimiters.none, "alias", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getAlias", "()Ljava/lang/String;", "DENY", "ALLOW_ALL_WITH_WARNING", "ALLOW_FIRST_WITH_WARNING", "toString", "Aliases", "Companion", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum DuplicatedUniqueNameStrategy {
    DENY(Aliases.DENY),
    ALLOW_ALL_WITH_WARNING(Aliases.ALLOW_ALL_WITH_WARNING),
    ALLOW_FIRST_WITH_WARNING(Aliases.ALLOW_FIRST_WITH_WARNING);

    private final String alias;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/config/DuplicatedUniqueNameStrategy$Aliases;", Argument.Delimiters.none, "<init>", "()V", "DENY", Argument.Delimiters.none, "ALLOW_ALL_WITH_WARNING", "ALLOW_FIRST_WITH_WARNING", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Aliases {
        public static final String ALLOW_ALL_WITH_WARNING = "allow-all-with-warning";
        public static final String ALLOW_FIRST_WITH_WARNING = "allow-first-with-warning";
        public static final String DENY = "deny";
        public static final Aliases INSTANCE = new Aliases();

        private Aliases() {
        }
    }

    DuplicatedUniqueNameStrategy(String str) {
        this.alias = str;
    }

    public static EnumEntries<DuplicatedUniqueNameStrategy> getEntries() {
        return $ENTRIES;
    }

    public final String getAlias() {
        return this.alias;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.alias;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/config/DuplicatedUniqueNameStrategy$Companion;", Argument.Delimiters.none, "<init>", "()V", "parseOrDefault", "Lorg/jetbrains/kotlin/config/DuplicatedUniqueNameStrategy;", "flagValue", Argument.Delimiters.none, "default", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DuplicatedUniqueNameStrategy parseOrDefault(String flagValue, DuplicatedUniqueNameStrategy duplicatedUniqueNameStrategy) {
            duplicatedUniqueNameStrategy.getClass();
            Iterator it = DuplicatedUniqueNameStrategy.getEntries().iterator();
            Object obj = null;
            boolean z = false;
            Object obj2 = null;
            while (true) {
                if (!it.hasNext()) {
                    if (!z) {
                        break;
                    }
                    obj = obj2;
                    break;
                }
                Object next = it.next();
                if (Intrinsics.areEqual(((DuplicatedUniqueNameStrategy) next).getAlias(), flagValue)) {
                    if (z) {
                        break;
                    }
                    z = true;
                    obj2 = next;
                }
            }
            DuplicatedUniqueNameStrategy duplicatedUniqueNameStrategy2 = (DuplicatedUniqueNameStrategy) obj;
            return duplicatedUniqueNameStrategy2 == null ? duplicatedUniqueNameStrategy : duplicatedUniqueNameStrategy2;
        }

        private Companion() {
        }
    }
}
