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
import org.jetbrains.kotlin.config.ReturnValueCheckerMode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/config/ReturnValueCheckerMode;", Argument.Delimiters.none, "state", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getState", "()Ljava/lang/String;", "DISABLED", "CHECKER", "FULL", "Companion", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum ReturnValueCheckerMode {
    DISABLED("disable"),
    CHECKER("check"),
    FULL("full");

    private final String state;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    ReturnValueCheckerMode(String str) {
        this.state = str;
    }

    public static EnumEntries<ReturnValueCheckerMode> getEntries() {
        return $ENTRIES;
    }

    public final String getState() {
        return this.state;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/config/ReturnValueCheckerMode$Companion;", Argument.Delimiters.none, "<init>", "()V", "fromString", "Lorg/jetbrains/kotlin/config/ReturnValueCheckerMode;", "string", Argument.Delimiters.none, "availableValues", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static CharSequence a(ReturnValueCheckerMode returnValueCheckerMode) {
            returnValueCheckerMode.getClass();
            return returnValueCheckerMode.getState();
        }

        public final String availableValues() {
            return CollectionsKt.joinToString$default(ReturnValueCheckerMode.getEntries(), (CharSequence) null, "{", "}", 0, (CharSequence) null, new Function1() { // from class: mjc
                public final Object invoke(Object obj) {
                    return ReturnValueCheckerMode.Companion.a((ReturnValueCheckerMode) obj);
                }
            }, 25, (Object) null);
        }

        public final ReturnValueCheckerMode fromString(String string) {
            Object next;
            string.getClass();
            Iterator it = ReturnValueCheckerMode.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (Intrinsics.areEqual(((ReturnValueCheckerMode) next).getState(), string)) {
                    return (ReturnValueCheckerMode) next;
                }
            }
            next = null;
            return (ReturnValueCheckerMode) next;
        }

        private Companion() {
        }
    }
}
