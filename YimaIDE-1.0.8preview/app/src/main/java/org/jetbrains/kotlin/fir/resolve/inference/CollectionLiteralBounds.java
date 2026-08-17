package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.Set;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ConeCollectionLiteralAtom;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0005\u000f\u0010\u0011\u0012\u0013B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0000H\u0096\u0082\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0004\u0014\u0015\u0016\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", Argument.Delimiters.none, "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;)V", "getAtom", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "readiness", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", "getReadiness", "()Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", "compareTo", Argument.Delimiters.none, "other", "Ambiguity", "FallbackOnly", "SingleBound", "NonTvExpected", "CollectionLiteralReadiness", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$Ambiguity;", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$FallbackOnly;", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$NonTvExpected;", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$SingleBound;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CollectionLiteralBounds implements Comparable<CollectionLiteralBounds> {
    private final ConeCollectionLiteralAtom atom;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$Ambiguity;", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "bounds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;Ljava/util/Set;)V", "getBounds", "()Ljava/util/Set;", "readiness", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", "getReadiness", "()Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Ambiguity extends CollectionLiteralBounds {
        private final Set<FirRegularClassSymbol> bounds;
        private final CollectionLiteralReadiness readiness;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Ambiguity(ConeCollectionLiteralAtom coneCollectionLiteralAtom, Set<FirRegularClassSymbol> set) {
            super(coneCollectionLiteralAtom, null);
            coneCollectionLiteralAtom.getClass();
            set.getClass();
            this.bounds = set;
            this.readiness = CollectionLiteralReadiness.AMBIGUITY;
        }

        public final Set<FirRegularClassSymbol> getBounds() {
            return this.bounds;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.inference.CollectionLiteralBounds
        public CollectionLiteralReadiness getReadiness() {
            return this.readiness;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0084\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "AMBIGUITY", "FALLBACK_ONLY", "SINGLE_BOUND", "NON_TV_EXPECTED", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum CollectionLiteralReadiness {
        AMBIGUITY,
        FALLBACK_ONLY,
        SINGLE_BOUND,
        NON_TV_EXPECTED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<CollectionLiteralReadiness> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$FallbackOnly;", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;)V", "readiness", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", "getReadiness", "()Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FallbackOnly extends CollectionLiteralBounds {
        private final CollectionLiteralReadiness readiness;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FallbackOnly(ConeCollectionLiteralAtom coneCollectionLiteralAtom) {
            super(coneCollectionLiteralAtom, null);
            coneCollectionLiteralAtom.getClass();
            this.readiness = CollectionLiteralReadiness.FALLBACK_ONLY;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.inference.CollectionLiteralBounds
        public CollectionLiteralReadiness getReadiness() {
            return this.readiness;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$NonTvExpected;", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "bound", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "getBound", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "readiness", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", "getReadiness", "()Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NonTvExpected extends CollectionLiteralBounds {
        private final FirRegularClassSymbol bound;
        private final CollectionLiteralReadiness readiness;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NonTvExpected(ConeCollectionLiteralAtom coneCollectionLiteralAtom, FirRegularClassSymbol firRegularClassSymbol) {
            super(coneCollectionLiteralAtom, null);
            coneCollectionLiteralAtom.getClass();
            this.bound = firRegularClassSymbol;
            this.readiness = CollectionLiteralReadiness.NON_TV_EXPECTED;
        }

        public final FirRegularClassSymbol getBound() {
            return this.bound;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.inference.CollectionLiteralBounds
        public CollectionLiteralReadiness getReadiness() {
            return this.readiness;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$SingleBound;", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "bound", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "getBound", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "readiness", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", "getReadiness", "()Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds$CollectionLiteralReadiness;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SingleBound extends CollectionLiteralBounds {
        private final FirRegularClassSymbol bound;
        private final CollectionLiteralReadiness readiness;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SingleBound(ConeCollectionLiteralAtom coneCollectionLiteralAtom, FirRegularClassSymbol firRegularClassSymbol) {
            super(coneCollectionLiteralAtom, null);
            coneCollectionLiteralAtom.getClass();
            firRegularClassSymbol.getClass();
            this.bound = firRegularClassSymbol;
            this.readiness = CollectionLiteralReadiness.SINGLE_BOUND;
        }

        public final FirRegularClassSymbol getBound() {
            return this.bound;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.inference.CollectionLiteralBounds
        public CollectionLiteralReadiness getReadiness() {
            return this.readiness;
        }
    }

    private CollectionLiteralBounds(ConeCollectionLiteralAtom coneCollectionLiteralAtom) {
        this.atom = coneCollectionLiteralAtom;
    }

    @Override // java.lang.Comparable
    public int compareTo(CollectionLiteralBounds other) {
        other.getClass();
        return getReadiness().compareTo(other.getReadiness());
    }

    public final ConeCollectionLiteralAtom getAtom() {
        return this.atom;
    }

    public abstract CollectionLiteralReadiness getReadiness();

    public /* synthetic */ CollectionLiteralBounds(ConeCollectionLiteralAtom coneCollectionLiteralAtom, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneCollectionLiteralAtom);
    }
}
