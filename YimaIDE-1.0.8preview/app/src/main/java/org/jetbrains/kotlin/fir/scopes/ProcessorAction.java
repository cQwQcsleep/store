package org.jetbrains.kotlin.fir.scopes;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\t\u0010\u0007\u001a\u00020\bH\u0086\u0002J\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\bJ\u0011\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0086\u0002j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "STOP", "NEXT", "NONE", "not", Argument.Delimiters.none, "stop", "next", "plus", "other", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum ProcessorAction {
    STOP,
    NEXT,
    NONE;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProcessorAction.values().length];
            try {
                iArr[ProcessorAction.STOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProcessorAction.NEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProcessorAction.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<ProcessorAction> getEntries() {
        return $ENTRIES;
    }

    public final boolean next() {
        return this != STOP;
    }

    public final boolean not() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 2 || i == 3) {
            return false;
        }
        bu8.a();
        return false;
    }

    public final ProcessorAction plus(ProcessorAction other) {
        other.getClass();
        ProcessorAction processorAction = NEXT;
        return (this == processorAction || other == processorAction) ? processorAction : this;
    }

    public final boolean stop() {
        return this == STOP;
    }
}
