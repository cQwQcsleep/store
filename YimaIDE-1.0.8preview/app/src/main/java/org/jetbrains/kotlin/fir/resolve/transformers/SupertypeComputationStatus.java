package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus;", Argument.Delimiters.none, "<init>", "()V", "NotComputed", "Computing", "Computed", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus$Computed;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus$Computing;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus$NotComputed;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class SupertypeComputationStatus {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus$Computed;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus;", "supertypeRefs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "<init>", "(Ljava/util/List;)V", "getSupertypeRefs", "()Ljava/util/List;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Computed extends SupertypeComputationStatus {
        private final List<FirResolvedTypeRef> supertypeRefs;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Computed(List<? extends FirResolvedTypeRef> list) {
            super(null);
            list.getClass();
            this.supertypeRefs = list;
        }

        public final List<FirResolvedTypeRef> getSupertypeRefs() {
            return this.supertypeRefs;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus$Computing;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Computing extends SupertypeComputationStatus {
        public static final Computing INSTANCE = new Computing();

        private Computing() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus$NotComputed;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NotComputed extends SupertypeComputationStatus {
        public static final NotComputed INSTANCE = new NotComputed();

        private NotComputed() {
            super(null);
        }
    }

    public /* synthetic */ SupertypeComputationStatus(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private SupertypeComputationStatus() {
    }
}
