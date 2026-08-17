package org.jetbrains.kotlin.config;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.HeaderMode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/config/HeaderMode;", Argument.Delimiters.none, "state", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getState", "()Ljava/lang/String;", "ANY", "COMPILATION", "Companion", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum HeaderMode {
    ANY("any"),
    COMPILATION("compilation");

    private final String state;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    HeaderMode(String str) {
        this.state = str;
    }

    public static EnumEntries<HeaderMode> getEntries() {
        return $ENTRIES;
    }

    public final String getState() {
        return this.state;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/config/HeaderMode$Companion;", Argument.Delimiters.none, "<init>", "()V", "fromString", "Lorg/jetbrains/kotlin/config/HeaderMode;", "string", Argument.Delimiters.none, "availableValues", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static CharSequence a(HeaderMode headerMode) {
            headerMode.getClass();
            return headerMode.getState();
        }

        public final String availableValues() {
            return CollectionsKt.joinToString$default(HeaderMode.getEntries(), (CharSequence) null, "{", "}", 0, (CharSequence) null, new Function1() { // from class: r76
                public final Object invoke(Object obj) {
                    return HeaderMode.Companion.a((HeaderMode) obj);
                }
            }, 25, (Object) null);
        }

        public final HeaderMode fromString(String string) {
            Object next;
            string.getClass();
            Iterator it = HeaderMode.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (Intrinsics.areEqual(((HeaderMode) next).getState(), string)) {
                    return (HeaderMode) next;
                }
            }
            next = null;
            return (HeaderMode) next;
        }

        private Companion() {
        }
    }
}
