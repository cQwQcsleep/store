package org.jetbrains.kotlin.fir.resolve.calls.tower;

import defpackage.uhe;
import java.util.Arrays;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.tower.TowerGroup;
import org.jetbrains.kotlin.fir.resolve.calls.tower.TowerGroupKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 &2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001&B5\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0000H\u0002J\u0012\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0000H\u0096\u0082\u0004J\u0014\u0010 \u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\"H\u0096\u0082\u0004J\n\u0010#\u001a\u00020$H\u0096\u0080\u0004J\n\u0010%\u001a\u00020\u0016H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", Argument.Delimiters.none, "code", Argument.Delimiters.none, "debugKinds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "invokeResolvePriority", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/InvokeResolvePriority;", "receiverGroup", "<init>", "(J[Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/InvokeResolvePriority;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;)V", "[Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "kindOf", "kind", "Member", "getMember", "()Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "InvokeExtensionWithImplicitReceiver", "getInvokeExtensionWithImplicitReceiver", "Local", "depth", Argument.Delimiters.none, "Implicit", "NonLocal", "ContextReceiverGroup", "TopPrioritized", "InvokeReceiver", "InvokeResolvePriority", "debugCompareTo", "other", "compareTo", "equals", Argument.Delimiters.none, Argument.Delimiters.none, "toString", Argument.Delimiters.none, "hashCode", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TowerGroup implements Comparable<TowerGroup> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final Comparator<TowerGroupKind[]> DEBUG_KINDS_COMPARATOR;
    private static final int DEPTH_SIZE_BITS;
    private static final TowerGroupKind[] EMPTY_KIND_ARRAY;
    private static final TowerGroup EmptyRoot;
    private static final TowerGroup EmptyRootForInvokeReceiver;
    private static final int KIND_SIZE_BITS;
    private static final TowerGroup Last;
    private static final TowerGroup Member;
    private static final TowerGroup QualifierOrClassifier;
    private static final TowerGroup QualifierValue;
    private static final TowerGroup Start;
    private static final int USABLE_BITS;
    private final long code;
    private final TowerGroupKind[] debugKinds;
    private final InvokeResolvePriority invokeResolvePriority;
    private final TowerGroup receiverGroup;

    static {
        final Companion companion = new Companion(null);
        INSTANCE = companion;
        KIND_SIZE_BITS = Integer.bitCount(15);
        DEPTH_SIZE_BITS = Integer.bitCount(CodegenUtilKt.STRING_UTF8_ENCODING_BYTE_LIMIT);
        USABLE_BITS = Long.numberOfLeadingZeros(63L);
        TowerGroupKind[] towerGroupKindArr = new TowerGroupKind[0];
        EMPTY_KIND_ARRAY = towerGroupKindArr;
        DEBUG_KINDS_COMPARATOR = new Comparator() { // from class: the
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return TowerGroup.DEBUG_KINDS_COMPARATOR$compareDebugKinds(companion, (TowerGroupKind[]) obj, (TowerGroupKind[]) obj2);
            }
        };
        DefaultConstructorMarker defaultConstructorMarker = null;
        long j = 0;
        TowerGroup towerGroup = null;
        EmptyRoot = new TowerGroup(j, towerGroupKindArr, null, towerGroup, 12, defaultConstructorMarker);
        EmptyRootForInvokeReceiver = new TowerGroup(j, towerGroupKindArr, InvokeResolvePriority.INVOKE_RECEIVER, towerGroup, 8, defaultConstructorMarker);
        Start = companion.kindOf(TowerGroupKind.Start.INSTANCE);
        QualifierOrClassifier = companion.kindOf(TowerGroupKind.QualifierOrClassifier.INSTANCE);
        QualifierValue = companion.kindOf(TowerGroupKind.QualifierValue.INSTANCE);
        Member = companion.kindOf(TowerGroupKind.Member.INSTANCE);
        Last = companion.kindOf(TowerGroupKind.Last.INSTANCE);
    }

    public /* synthetic */ TowerGroup(long j, TowerGroupKind[] towerGroupKindArr, InvokeResolvePriority invokeResolvePriority, TowerGroup towerGroup, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, towerGroupKindArr, (i & 4) != 0 ? InvokeResolvePriority.NONE : invokeResolvePriority, (i & 8) != 0 ? null : towerGroup);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int DEBUG_KINDS_COMPARATOR$compareDebugKinds(Companion companion, TowerGroupKind[] towerGroupKindArr, TowerGroupKind[] towerGroupKindArr2) {
        companion.getClass();
        towerGroupKindArr.getClass();
        towerGroupKindArr2.getClass();
        return companion.compareDebugKinds(towerGroupKindArr, towerGroupKindArr2);
    }

    private final TowerGroup kindOf(TowerGroupKind kind) {
        Companion companion = INSTANCE;
        return new TowerGroup(companion.subscript(this.code, kind), companion.appendDebugKind(this.debugKinds, kind), null, null, 12, null);
    }

    public final TowerGroup ContextReceiverGroup(int depth) {
        return kindOf(new TowerGroupKind.ContextReceiverGroup(depth));
    }

    public final TowerGroup Implicit(int depth) {
        return kindOf(TowerGroupKind.INSTANCE.Implicit(depth));
    }

    public final TowerGroup InvokeReceiver(TowerGroup receiverGroup, InvokeResolvePriority invokeResolvePriority) {
        receiverGroup.getClass();
        invokeResolvePriority.getClass();
        InvokeResolvePriority invokeResolvePriority2 = receiverGroup.invokeResolvePriority;
        InvokeResolvePriority invokeResolvePriority3 = InvokeResolvePriority.INVOKE_RECEIVER;
        if (invokeResolvePriority2 != invokeResolvePriority3) {
            wec.a("Receivers for invoke should be resolved with INVOKE_RECEIVER, but ", receiverGroup.invokeResolvePriority, " found");
            return null;
        }
        if (invokeResolvePriority == InvokeResolvePriority.NONE || invokeResolvePriority == invokeResolvePriority3) {
            w01.a("invokeResolvePriority should be non-trivial when receiverGroup is specified");
            return null;
        }
        if (receiverGroup.receiverGroup == null) {
            return new TowerGroup(this.code, this.debugKinds, invokeResolvePriority, receiverGroup);
        }
        wec.a("receiverGroup should be trivial, but ", receiverGroup.receiverGroup, " was found");
        return null;
    }

    public final TowerGroup InvokeResolvePriority(InvokeResolvePriority invokeResolvePriority) {
        invokeResolvePriority.getClass();
        if (invokeResolvePriority == InvokeResolvePriority.NONE) {
            return this;
        }
        return new TowerGroup(this.code, this.debugKinds, invokeResolvePriority, null, 8, null);
    }

    public final TowerGroup Local(int depth) {
        return kindOf(new TowerGroupKind.Local(depth));
    }

    public final TowerGroup NonLocal(int depth) {
        return kindOf(TowerGroupKind.INSTANCE.NonLocal(depth));
    }

    public final TowerGroup TopPrioritized(int depth) {
        return kindOf(new TowerGroupKind.TopPrioritized(depth));
    }

    @Override // java.lang.Comparable
    public int compareTo(TowerGroup other) {
        other.getClass();
        TowerGroup towerGroup = this.receiverGroup;
        if (towerGroup == null && other.receiverGroup == null) {
            InvokeResolvePriority invokeResolvePriority = this.invokeResolvePriority;
            InvokeResolvePriority invokeResolvePriority2 = InvokeResolvePriority.NONE;
            if (invokeResolvePriority == invokeResolvePriority2 && other.invokeResolvePriority == invokeResolvePriority2) {
                return Long.compareUnsigned(this.code, other.code);
            }
        }
        long j = towerGroup != null ? towerGroup.code : 0L;
        TowerGroup towerGroup2 = other.receiverGroup;
        long j2 = towerGroup2 != null ? towerGroup2.code : 0L;
        long j3 = Long.compareUnsigned(this.code, j) >= 0 ? this.code : j;
        int iCompareUnsigned = Long.compareUnsigned(j3, Long.compareUnsigned(other.code, j2) >= 0 ? other.code : j2);
        if (iCompareUnsigned != 0) {
            return iCompareUnsigned;
        }
        int iCompareTo = this.invokeResolvePriority.compareTo(other.invokeResolvePriority);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        return (Long.compareUnsigned(j, j3) == 0 && Long.compareUnsigned(j2, j3) == 0) ? Long.compareUnsigned(this.code, other.code) : Long.compareUnsigned(j, j2);
    }

    public boolean equals(Object other) {
        TowerGroup towerGroup;
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(TowerGroup.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        TowerGroup towerGroup2 = (TowerGroup) other;
        if (this.code != towerGroup2.code || this.invokeResolvePriority != towerGroup2.invokeResolvePriority) {
            return false;
        }
        TowerGroup towerGroup3 = this.receiverGroup;
        return towerGroup3 == null || (towerGroup = towerGroup2.receiverGroup) == null || Intrinsics.areEqual(towerGroup3, towerGroup);
    }

    public final TowerGroup getInvokeExtensionWithImplicitReceiver() {
        return kindOf(TowerGroupKind.InvokeExtensionWithImplicitReceiver.INSTANCE);
    }

    public final TowerGroup getMember() {
        return kindOf(TowerGroupKind.Member.INSTANCE);
    }

    public int hashCode() {
        return (Long.hashCode(this.code) * 31) + this.invokeResolvePriority.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TowerGroup(code=");
        sb.append(Long.toBinaryString(this.code));
        sb.append(", debugKinds=");
        String string = Arrays.toString(this.debugKinds);
        string.getClass();
        sb.append(string);
        sb.append(", invokeResolvePriority=");
        sb.append(this.invokeResolvePriority);
        sb.append(')');
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0015\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0016J\u001b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0015\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u000fH\u0002J)\u0010\u001b\u001a\u00020\u00052\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0002\u0010\u001eJ\u0010\u0010$\u001a\u00020%2\u0006\u0010\u0015\u001a\u00020\u000fH\u0002J\u000e\u00103\u001a\u00020%2\u0006\u00104\u001a\u00020\u0005J\u000e\u00105\u001a\u00020%2\u0006\u00104\u001a\u00020\u0005J\u000e\u00106\u001a\u00020%2\u0006\u00104\u001a\u00020\u0005J\u000e\u00107\u001a\u00020%2\u0006\u00104\u001a\u00020\u0005J\u000e\u00108\u001a\u00020%2\u0006\u00104\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000R-\u0010\u001f\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0 j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e`!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010&\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u0011\u0010+\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b,\u0010(R\u0011\u0010-\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b.\u0010(R\u0011\u0010/\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b0\u0010(R\u0011\u00101\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b2\u0010(R\u0011\u00109\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b:\u0010(¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup$Companion;", Argument.Delimiters.none, "<init>", "()V", "KIND_MASK", Argument.Delimiters.none, "KIND_SIZE_BITS", "DEPTH_MASK", "DEPTH_SIZE_BITS", "USED_BITS_MASK", Argument.Delimiters.none, "TOTAL_BITS", "USABLE_BITS", "EMPTY_KIND_ARRAY", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "[Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "DEBUG", Argument.Delimiters.none, "appendDebugKind", "kinds", "kind", "([Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;)[Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "debugKindArrayOf", "(Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;)[Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;", "subscript", "code", "compareDebugKinds", "aDebugKinds", "bDebugKinds", "([Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;[Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroupKind;)I", "DEBUG_KINDS_COMPARATOR", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "getDEBUG_KINDS_COMPARATOR", "()Ljava/util/Comparator;", "kindOf", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "EmptyRoot", "getEmptyRoot", "()Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "EmptyRootForInvokeReceiver", "getEmptyRootForInvokeReceiver", "Start", "getStart", "QualifierOrClassifier", "getQualifierOrClassifier", "QualifierValue", "getQualifierValue", "Member", "getMember", "Local", "depth", "Implicit", "NonLocal", "ContextReceiverGroup", "TopPrioritized", "Last", "getLast", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final TowerGroupKind[] appendDebugKind(TowerGroupKind[] kinds, TowerGroupKind kind) {
            return TowerGroup.EMPTY_KIND_ARRAY;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int compareDebugKinds(TowerGroupKind[] aDebugKinds, TowerGroupKind[] bDebugKinds) {
            int i = 0;
            while (i < aDebugKinds.length) {
                if (i >= bDebugKinds.length) {
                    return 1;
                }
                if (aDebugKinds[i].compareTo(bDebugKinds[i]) < 0) {
                    return -1;
                }
                if (aDebugKinds[i].compareTo(bDebugKinds[i]) > 0) {
                    return 1;
                }
                i++;
            }
            return i < bDebugKinds.length ? -1 : 0;
        }

        private final TowerGroupKind[] debugKindArrayOf(TowerGroupKind kind) {
            return TowerGroup.EMPTY_KIND_ARRAY;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final TowerGroup kindOf(TowerGroupKind kind) {
            return new TowerGroup(subscript(0L, kind), debugKindArrayOf(kind), null, null, 12, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long subscript(long code, TowerGroupKind kind) {
            long index;
            long j;
            int i = (int) (63 & code);
            if (kind instanceof TowerGroupKind.WithDepth) {
                int i2 = TowerGroup.KIND_SIZE_BITS + i;
                int i3 = i2 + TowerGroup.DEPTH_SIZE_BITS;
                TowerGroupKind.WithDepth withDepth = (TowerGroupKind.WithDepth) kind;
                if (withDepth.getDepth() > 65535) {
                    z2d.a("Depth overflow: requested: ", withDepth.getDepth(), ", allowed: 65535");
                    return 0L;
                }
                if (i3 > TowerGroup.USABLE_BITS) {
                    uhe.a("BitGroup overflow: newUsedBits: ", i3, ", original: ", Long.toBinaryString(code), ", usedBits: ", i);
                    return 0L;
                }
                index = code | (((long) kind.getIndex()) << (64 - i2)) | (((long) withDepth.getDepth()) << (64 - i3));
                j = i3;
            } else {
                int i4 = i + TowerGroup.KIND_SIZE_BITS;
                if (i4 > TowerGroup.USABLE_BITS) {
                    w01.a("Failed requirement.");
                    return 0L;
                }
                index = code | (((long) kind.getIndex()) << (64 - i4));
                j = i4;
            }
            return index | j;
        }

        public final TowerGroup ContextReceiverGroup(int depth) {
            return kindOf(new TowerGroupKind.ContextReceiverGroup(depth));
        }

        public final TowerGroup Implicit(int depth) {
            return kindOf(TowerGroupKind.INSTANCE.Implicit(depth));
        }

        public final TowerGroup Local(int depth) {
            return kindOf(new TowerGroupKind.Local(depth));
        }

        public final TowerGroup NonLocal(int depth) {
            return kindOf(TowerGroupKind.INSTANCE.NonLocal(depth));
        }

        public final TowerGroup TopPrioritized(int depth) {
            return kindOf(new TowerGroupKind.TopPrioritized(depth));
        }

        public final Comparator<TowerGroupKind[]> getDEBUG_KINDS_COMPARATOR() {
            return TowerGroup.DEBUG_KINDS_COMPARATOR;
        }

        public final TowerGroup getEmptyRoot() {
            return TowerGroup.EmptyRoot;
        }

        public final TowerGroup getEmptyRootForInvokeReceiver() {
            return TowerGroup.EmptyRootForInvokeReceiver;
        }

        public final TowerGroup getLast() {
            return TowerGroup.Last;
        }

        public final TowerGroup getMember() {
            return TowerGroup.Member;
        }

        public final TowerGroup getQualifierOrClassifier() {
            return TowerGroup.QualifierOrClassifier;
        }

        public final TowerGroup getQualifierValue() {
            return TowerGroup.QualifierValue;
        }

        public final TowerGroup getStart() {
            return TowerGroup.Start;
        }

        private Companion() {
        }
    }

    private TowerGroup(long j, TowerGroupKind[] towerGroupKindArr, InvokeResolvePriority invokeResolvePriority, TowerGroup towerGroup) {
        this.code = j;
        this.debugKinds = towerGroupKindArr;
        this.invokeResolvePriority = invokeResolvePriority;
        this.receiverGroup = towerGroup;
    }
}
