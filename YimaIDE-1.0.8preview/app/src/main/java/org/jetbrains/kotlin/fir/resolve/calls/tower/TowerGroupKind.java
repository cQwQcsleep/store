package org.jetbrains.kotlin.fir.resolve.calls.tower;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00000\u0001:\f\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0000H\u0096\u0082\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0007\u0017\u0018\u0019\u001a\u001b\u001c\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", Argument.Delimiters.none, "index", Argument.Delimiters.none, "<init>", "(B)V", "getIndex", "()B", "compareTo", Argument.Delimiters.none, "other", "WithDepth", "Start", "QualifierOrClassifier", "TopPrioritized", "Member", "InvokeExtensionWithImplicitReceiver", "Local", "ImplicitOrNonLocal", "ContextReceiverGroup", "QualifierValue", "Last", "Companion", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$InvokeExtensionWithImplicitReceiver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$Last;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$Member;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$QualifierOrClassifier;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$QualifierValue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$Start;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$WithDepth;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class TowerGroupKind implements Comparable<TowerGroupKind> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final byte index;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$ContextReceiverGroup;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$WithDepth;", "depth", Argument.Delimiters.none, "<init>", "(I)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ContextReceiverGroup extends WithDepth {
        public ContextReceiverGroup(int i) {
            super((byte) 8, i);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0004\u001a\u00020\u0005¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$ImplicitOrNonLocal;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$WithDepth;", "depth", Argument.Delimiters.none, "kindForDebugSake", Argument.Delimiters.none, "<init>", "(ILjava/lang/String;)V", "getKindForDebugSake$annotations", "()V", "getKindForDebugSake", "()Ljava/lang/String;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ImplicitOrNonLocal extends WithDepth {
        private final String kindForDebugSake;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImplicitOrNonLocal(int i, String str) {
            super((byte) 7, i);
            str.getClass();
            this.kindForDebugSake = str;
        }

        public static /* synthetic */ void getKindForDebugSake$annotations() {
        }

        public final String getKindForDebugSake() {
            return this.kindForDebugSake;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$InvokeExtensionWithImplicitReceiver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class InvokeExtensionWithImplicitReceiver extends TowerGroupKind {
        public static final InvokeExtensionWithImplicitReceiver INSTANCE = new InvokeExtensionWithImplicitReceiver();

        private InvokeExtensionWithImplicitReceiver() {
            super((byte) 5, null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof InvokeExtensionWithImplicitReceiver);
        }

        public int hashCode() {
            return -1687627257;
        }

        public String toString() {
            return "InvokeExtensionWithImplicitReceiver";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$Last;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Last extends TowerGroupKind {
        public static final Last INSTANCE = new Last();

        private Last() {
            super((byte) 15, null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Last);
        }

        public int hashCode() {
            return -788437392;
        }

        public String toString() {
            return "Last";
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$Local;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$WithDepth;", "depth", Argument.Delimiters.none, "<init>", "(I)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Local extends WithDepth {
        public Local(int i) {
            super((byte) 6, i);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$Member;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Member extends TowerGroupKind {
        public static final Member INSTANCE = new Member();

        private Member() {
            super((byte) 4, null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Member);
        }

        public int hashCode() {
            return -1741959180;
        }

        public String toString() {
            return "Member";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$QualifierOrClassifier;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class QualifierOrClassifier extends TowerGroupKind {
        public static final QualifierOrClassifier INSTANCE = new QualifierOrClassifier();

        private QualifierOrClassifier() {
            super((byte) 1, null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof QualifierOrClassifier);
        }

        public int hashCode() {
            return 1165555092;
        }

        public String toString() {
            return "QualifierOrClassifier";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$QualifierValue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class QualifierValue extends TowerGroupKind {
        public static final QualifierValue INSTANCE = new QualifierValue();

        private QualifierValue() {
            super((byte) 9, null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof QualifierValue);
        }

        public int hashCode() {
            return -402860479;
        }

        public String toString() {
            return "QualifierValue";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$Start;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Start extends TowerGroupKind {
        public static final Start INSTANCE = new Start();

        private Start() {
            super((byte) 0, null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Start);
        }

        public int hashCode() {
            return 1335258056;
        }

        public String toString() {
            return "Start";
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$TopPrioritized;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$WithDepth;", "depth", Argument.Delimiters.none, "<init>", "(I)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TopPrioritized extends WithDepth {
        public TopPrioritized(int i) {
            super((byte) 3, i);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u0005H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$WithDepth;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "index", Argument.Delimiters.none, "depth", Argument.Delimiters.none, "<init>", "(BI)V", "getDepth", "()I", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class WithDepth extends TowerGroupKind {
        private final int depth;

        public WithDepth(byte b, int i) {
            super(b, null);
            this.depth = i;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
                return false;
            }
            other.getClass();
            WithDepth withDepth = (WithDepth) other;
            return getIndex() == withDepth.getIndex() && this.depth == withDepth.depth;
        }

        public final int getDepth() {
            return this.depth;
        }

        public int hashCode() {
            return (this.depth * 31) + getIndex();
        }
    }

    private TowerGroupKind(byte b) {
        this.index = b;
    }

    @Override // java.lang.Comparable
    public int compareTo(TowerGroupKind other) {
        other.getClass();
        int iCompare = Intrinsics.compare((int) this.index, (int) other.index);
        if (iCompare != 0) {
            return iCompare;
        }
        if ((this instanceof WithDepth) && (other instanceof WithDepth)) {
            return Intrinsics.compare(((WithDepth) this).getDepth(), ((WithDepth) other).getDepth());
        }
        return 0;
    }

    public final byte getIndex() {
        return this.index;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind$Companion;", Argument.Delimiters.none, "<init>", "()V", "Implicit", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "depth", Argument.Delimiters.none, "NonLocal", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TowerGroupKind Implicit(int depth) {
            return new ImplicitOrNonLocal(depth, "Implicit");
        }

        public final TowerGroupKind NonLocal(int depth) {
            return new ImplicitOrNonLocal(depth, "NonLocal");
        }

        private Companion() {
        }
    }

    public /* synthetic */ TowerGroupKind(byte b, DefaultConstructorMarker defaultConstructorMarker) {
        this(b);
    }
}
