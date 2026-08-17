package org.jetbrains.kotlin.config;

import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.PartialLinkageLogLevel;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 org.jetbrains.kotlin.config.PartialLinkageLogLevel, still in use, count: 1, list:
  (r0v0 org.jetbrains.kotlin.config.PartialLinkageLogLevel) from 0x003c: SPUT (r0v0 org.jetbrains.kotlin.config.PartialLinkageLogLevel) org.jetbrains.kotlin.config.PartialLinkageLogLevel.DEFAULT org.jetbrains.kotlin.config.PartialLinkageLogLevel
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "SILENT", "INFO", "WARNING", "ERROR", "Companion", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PartialLinkageLogLevel {
    SILENT,
    INFO,
    WARNING,
    ERROR;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PartialLinkageLogLevel DEFAULT = new PartialLinkageLogLevel();

    static {
    }

    private PartialLinkageLogLevel() {
        super(str, i);
    }

    public static EnumEntries<PartialLinkageLogLevel> getEntries() {
        return $ENTRIES;
    }

    public static PartialLinkageLogLevel valueOf(String str) {
        return (PartialLinkageLogLevel) Enum.valueOf(PartialLinkageLogLevel.class, str);
    }

    public static PartialLinkageLogLevel[] values() {
        return (PartialLinkageLogLevel[]) $VALUES.clone();
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel$Companion;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel;", "getDEFAULT", "()Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel;", "resolveLogLevel", "key", Argument.Delimiters.none, "availableValues", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static CharSequence a(PartialLinkageLogLevel partialLinkageLogLevel) {
            partialLinkageLogLevel.getClass();
            String lowerCase = partialLinkageLogLevel.name().toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            return lowerCase;
        }

        public final String availableValues() {
            return CollectionsKt.joinToString$default(PartialLinkageLogLevel.getEntries(), (CharSequence) null, "{", "}", 0, (CharSequence) null, new Function1() { // from class: gza
                public final Object invoke(Object obj) {
                    return PartialLinkageLogLevel.Companion.a((PartialLinkageLogLevel) obj);
                }
            }, 25, (Object) null);
        }

        public final PartialLinkageLogLevel getDEFAULT() {
            return PartialLinkageLogLevel.DEFAULT;
        }

        public final PartialLinkageLogLevel resolveLogLevel(String key) {
            Object next;
            key.getClass();
            Iterator it = PartialLinkageLogLevel.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (StringsKt.equals(((PartialLinkageLogLevel) next).name(), key, true)) {
                    return (PartialLinkageLogLevel) next;
                }
            }
            next = null;
            return (PartialLinkageLogLevel) next;
        }

        private Companion() {
        }
    }
}
