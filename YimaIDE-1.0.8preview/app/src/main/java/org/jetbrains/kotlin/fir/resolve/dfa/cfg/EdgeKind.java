package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0086\u0081\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B1\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u0016\u001a\u00020\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000bj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeKind;", Argument.Delimiters.none, "usedInDfa", Argument.Delimiters.none, "usedInDeadDfa", "usedInCfa", "isBack", "isDead", "<init>", "(Ljava/lang/String;IZZZZZ)V", "getUsedInDfa", "()Z", "getUsedInDeadDfa", "getUsedInCfa", "Forward", "DfgForward", "CfgForward", "DeadForward", "DeadDfgForward", "DeadCfgForward", "CfgBackward", "DeadCfgBackward", "toDead", "Companion", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum EdgeKind {
    Forward(true, true, true, false, false),
    DfgForward(true, true, false, false, false),
    CfgForward(false, false, true, false, false),
    DeadForward(false, true, true, false, true),
    DeadDfgForward(false, true, false, false, true),
    DeadCfgForward(false, false, true, false, true),
    CfgBackward(false, false, true, true, false),
    DeadCfgBackward(false, false, true, true, true);

    private final boolean isBack;
    private final boolean isDead;
    private final boolean usedInCfa;
    private final boolean usedInDeadDfa;
    private final boolean usedInDfa;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EdgeKind.values().length];
            try {
                iArr[EdgeKind.Forward.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EdgeKind.DfgForward.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EdgeKind.CfgForward.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[EdgeKind.DeadForward.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[EdgeKind.DeadDfgForward.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[EdgeKind.DeadCfgForward.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[EdgeKind.CfgBackward.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[EdgeKind.DeadCfgBackward.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    EdgeKind(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.usedInDfa = z;
        this.usedInDeadDfa = z2;
        this.usedInCfa = z3;
        this.isBack = z4;
        this.isDead = z5;
    }

    public static EnumEntries<EdgeKind> getEntries() {
        return $ENTRIES;
    }

    public final boolean getUsedInCfa() {
        return this.usedInCfa;
    }

    public final boolean getUsedInDeadDfa() {
        return this.usedInDeadDfa;
    }

    public final boolean getUsedInDfa() {
        return this.usedInDfa;
    }

    /* JADX INFO: renamed from: isBack, reason: from getter */
    public final boolean getIsBack() {
        return this.isBack;
    }

    /* JADX INFO: renamed from: isDead, reason: from getter */
    public final boolean getIsDead() {
        return this.isDead;
    }

    public final EdgeKind toDead() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return DeadForward;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return DeadDfgForward;
            case 3:
                return DeadCfgForward;
            case 4:
                return DeadForward;
            case 5:
                return DeadDfgForward;
            case 6:
                return DeadCfgForward;
            case 7:
                return DeadCfgBackward;
            case 8:
                return DeadCfgBackward;
            default:
                bu8.a();
                return null;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeKind$Companion;", Argument.Delimiters.none, "<init>", "()V", "forward", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeKind;", "usedInCfa", Argument.Delimiters.none, "usedInDfa", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ EdgeKind forward$default(Companion companion, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            if ((i & 2) != 0) {
                z2 = false;
            }
            return companion.forward(z, z2);
        }

        public final EdgeKind forward(boolean usedInCfa, boolean usedInDfa) {
            if (usedInCfa && usedInDfa) {
                return EdgeKind.Forward;
            }
            if (usedInCfa) {
                return EdgeKind.CfgForward;
            }
            if (usedInDfa) {
                return EdgeKind.DfgForward;
            }
            return null;
        }

        private Companion() {
        }
    }
}
