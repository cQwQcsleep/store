package org.jetbrains.kotlin.fir.resolve.substitution;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\t\u0010\u0013\u001a\u00020\u0001HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0001HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001HÂ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/substitution/ChainedSubstitutor;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "first", "second", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;)V", "getFirst", "()Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "getSecond", "substituteOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "substituteArgument", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "projection", "index", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ChainedSubstitutor extends ConeSubstitutor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ConeSubstitutor first;
    private final ConeSubstitutor second;

    private ChainedSubstitutor(ConeSubstitutor coneSubstitutor, ConeSubstitutor coneSubstitutor2) {
        this.first = coneSubstitutor;
        this.second = coneSubstitutor2;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ConeSubstitutor getFirst() {
        return this.first;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ConeSubstitutor getSecond() {
        return this.second;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChainedSubstitutor)) {
            return false;
        }
        ChainedSubstitutor chainedSubstitutor = (ChainedSubstitutor) other;
        return Intrinsics.areEqual(this.first, chainedSubstitutor.first) && Intrinsics.areEqual(this.second, chainedSubstitutor.second);
    }

    public final ConeSubstitutor getFirst() {
        return this.first;
    }

    public final ConeSubstitutor getSecond() {
        return this.second;
    }

    public int hashCode() {
        return (this.first.hashCode() * 31) + this.second.hashCode();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
    public ConeTypeProjection substituteArgument(ConeTypeProjection projection, int index) {
        projection.getClass();
        ConeTypeProjection coneTypeProjectionSubstituteArgument = this.first.substituteArgument(projection, index);
        ConeSubstitutor coneSubstitutor = this.second;
        if (coneTypeProjectionSubstituteArgument != null) {
            projection = coneTypeProjectionSubstituteArgument;
        }
        ConeTypeProjection coneTypeProjectionSubstituteArgument2 = coneSubstitutor.substituteArgument(projection, index);
        return coneTypeProjectionSubstituteArgument2 == null ? coneTypeProjectionSubstituteArgument : coneTypeProjectionSubstituteArgument2;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
    public ConeKotlinType substituteOrNull(ConeKotlinType type) {
        type.getClass();
        ConeKotlinType coneKotlinTypeSubstituteOrNull = this.first.substituteOrNull(type);
        ConeSubstitutor coneSubstitutor = this.second;
        return coneKotlinTypeSubstituteOrNull != null ? coneSubstitutor.substituteOrSelf(coneKotlinTypeSubstituteOrNull) : coneSubstitutor.substituteOrNull(type);
    }

    public String toString() {
        return this.first + " then " + this.second;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0086\u0002¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/substitution/ChainedSubstitutor$Companion;", Argument.Delimiters.none, "<init>", "()V", "invoke", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "first", "second", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConeSubstitutor invoke(ConeSubstitutor first, ConeSubstitutor second) {
            first.getClass();
            second.getClass();
            ConeSubstitutor.Empty empty = ConeSubstitutor.Empty.INSTANCE;
            if (Intrinsics.areEqual(first, empty)) {
                return second;
            }
            return Intrinsics.areEqual(second, empty) ? first : new ChainedSubstitutor(first, second, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ ChainedSubstitutor(ConeSubstitutor coneSubstitutor, ConeSubstitutor coneSubstitutor2, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneSubstitutor, coneSubstitutor2);
    }
}
