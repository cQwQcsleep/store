package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus;", Argument.Delimiters.none, "<init>", "()V", "NotComputed", "Computing", "Computed", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus$Computed;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus$Computing;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus$NotComputed;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ImplicitBodyResolveComputationStatus {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus$Computed;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus;", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "transformedDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "getResolvedTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "getTransformedDeclaration", "()Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Computed extends ImplicitBodyResolveComputationStatus {
        private final FirResolvedTypeRef resolvedTypeRef;
        private final FirCallableDeclaration transformedDeclaration;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Computed(FirResolvedTypeRef firResolvedTypeRef, FirCallableDeclaration firCallableDeclaration) {
            super(null);
            firResolvedTypeRef.getClass();
            firCallableDeclaration.getClass();
            this.resolvedTypeRef = firResolvedTypeRef;
            this.transformedDeclaration = firCallableDeclaration;
        }

        public final FirResolvedTypeRef getResolvedTypeRef() {
            return this.resolvedTypeRef;
        }

        public final FirCallableDeclaration getTransformedDeclaration() {
            return this.transformedDeclaration;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus$Computing;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Computing extends ImplicitBodyResolveComputationStatus {
        public static final Computing INSTANCE = new Computing();

        private Computing() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus$NotComputed;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationStatus;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NotComputed extends ImplicitBodyResolveComputationStatus {
        public static final NotComputed INSTANCE = new NotComputed();

        private NotComputed() {
            super(null);
        }
    }

    public /* synthetic */ ImplicitBodyResolveComputationStatus(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ImplicitBodyResolveComputationStatus() {
    }
}
