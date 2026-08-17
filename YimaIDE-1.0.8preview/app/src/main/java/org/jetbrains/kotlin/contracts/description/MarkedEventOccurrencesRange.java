package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0002:\u0006\f\r\u000e\u000f\u0010\u0011B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u0004\u0018\u00018\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0006\u0012\u0013\u0014\u0015\u0016\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", "D", Argument.Delimiters.none, "<init>", "()V", "location", "getLocation", "()Ljava/lang/Object;", "withoutMarker", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "getWithoutMarker", "()Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "Zero", "AtMostOnce", "ExactlyOnce", "AtLeastOnce", "MoreThanOnce", "Unknown", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$AtLeastOnce;", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$AtMostOnce;", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$ExactlyOnce;", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$MoreThanOnce;", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$Unknown;", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$Zero;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class MarkedEventOccurrencesRange<D> {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0083\u0004J\n\u0010\t\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$AtLeastOnce;", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", Argument.Delimiters.none, "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class AtLeastOnce extends MarkedEventOccurrencesRange {
        public static final AtLeastOnce INSTANCE = new AtLeastOnce();

        private AtLeastOnce() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof AtLeastOnce);
        }

        public int hashCode() {
            return 502740392;
        }

        public String toString() {
            return "AtLeastOnce";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\n\b\u0001\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\n\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\bJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0016\u0010\u0004\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$AtMostOnce;", "D", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", "location", "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "getLocation", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$AtMostOnce;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class AtMostOnce<D> extends MarkedEventOccurrencesRange<D> {
        private final D location;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AtMostOnce(D d) {
            super(null);
            d.getClass();
            this.location = d;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AtMostOnce copy$default(AtMostOnce atMostOnce, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = atMostOnce.location;
            }
            return atMostOnce.copy(obj);
        }

        public final D component1() {
            return this.location;
        }

        public final AtMostOnce<D> copy(D location) {
            location.getClass();
            return new AtMostOnce<>(location);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AtMostOnce) && Intrinsics.areEqual(this.location, ((AtMostOnce) other).location);
        }

        @Override // org.jetbrains.kotlin.contracts.description.MarkedEventOccurrencesRange
        public D getLocation() {
            return this.location;
        }

        public int hashCode() {
            return this.location.hashCode();
        }

        public String toString() {
            return "AtMostOnce(location=" + this.location + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\n\b\u0001\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\n\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\bJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0016\u0010\u0004\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$ExactlyOnce;", "D", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", "location", "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "getLocation", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$ExactlyOnce;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ExactlyOnce<D> extends MarkedEventOccurrencesRange<D> {
        private final D location;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ExactlyOnce(D d) {
            super(null);
            d.getClass();
            this.location = d;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ExactlyOnce copy$default(ExactlyOnce exactlyOnce, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = exactlyOnce.location;
            }
            return exactlyOnce.copy(obj);
        }

        public final D component1() {
            return this.location;
        }

        public final ExactlyOnce<D> copy(D location) {
            location.getClass();
            return new ExactlyOnce<>(location);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ExactlyOnce) && Intrinsics.areEqual(this.location, ((ExactlyOnce) other).location);
        }

        @Override // org.jetbrains.kotlin.contracts.description.MarkedEventOccurrencesRange
        public D getLocation() {
            return this.location;
        }

        public int hashCode() {
            return this.location.hashCode();
        }

        public String toString() {
            return "ExactlyOnce(location=" + this.location + ')';
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0083\u0004J\n\u0010\t\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$MoreThanOnce;", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", Argument.Delimiters.none, "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class MoreThanOnce extends MarkedEventOccurrencesRange {
        public static final MoreThanOnce INSTANCE = new MoreThanOnce();

        private MoreThanOnce() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof MoreThanOnce);
        }

        public int hashCode() {
            return -1399222202;
        }

        public String toString() {
            return "MoreThanOnce";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0083\u0004J\n\u0010\t\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$Unknown;", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", Argument.Delimiters.none, "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Unknown extends MarkedEventOccurrencesRange {
        public static final Unknown INSTANCE = new Unknown();

        private Unknown() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Unknown);
        }

        public int hashCode() {
            return -282398501;
        }

        public String toString() {
            return "Unknown";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0083\u0004J\n\u0010\t\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange$Zero;", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", Argument.Delimiters.none, "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Zero extends MarkedEventOccurrencesRange {
        public static final Zero INSTANCE = new Zero();

        private Zero() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Zero);
        }

        public int hashCode() {
            return 182938551;
        }

        public String toString() {
            return "Zero";
        }
    }

    public /* synthetic */ MarkedEventOccurrencesRange(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public D getLocation() {
        return null;
    }

    public final EventOccurrencesRange getWithoutMarker() {
        if (Intrinsics.areEqual(this, Zero.INSTANCE)) {
            return EventOccurrencesRange.ZERO;
        }
        if (this instanceof AtMostOnce) {
            return EventOccurrencesRange.AT_MOST_ONCE;
        }
        if (this instanceof ExactlyOnce) {
            return EventOccurrencesRange.EXACTLY_ONCE;
        }
        if (Intrinsics.areEqual(this, AtLeastOnce.INSTANCE)) {
            return EventOccurrencesRange.AT_LEAST_ONCE;
        }
        if (Intrinsics.areEqual(this, MoreThanOnce.INSTANCE)) {
            return EventOccurrencesRange.MORE_THAN_ONCE;
        }
        if (Intrinsics.areEqual(this, Unknown.INSTANCE)) {
            return EventOccurrencesRange.UNKNOWN;
        }
        bu8.a();
        return null;
    }

    private MarkedEventOccurrencesRange() {
    }
}
