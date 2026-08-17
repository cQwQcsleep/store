package org.jetbrains.kotlin.incremental;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.build.report.metrics.BuildAttribute;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangesEither;", "", "<init>", "()V", "Known", "Unknown", "Lorg/jetbrains/kotlin/incremental/ChangesEither$Known;", "Lorg/jetbrains/kotlin/incremental/ChangesEither$Unknown;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ChangesEither {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangesEither$Unknown;", "Lorg/jetbrains/kotlin/incremental/ChangesEither;", "reason", "Lorg/jetbrains/kotlin/build/report/metrics/BuildAttribute;", "<init>", "(Lorg/jetbrains/kotlin/build/report/metrics/BuildAttribute;)V", "getReason", "()Lorg/jetbrains/kotlin/build/report/metrics/BuildAttribute;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Unknown extends ChangesEither {
        private final BuildAttribute reason;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Unknown(BuildAttribute buildAttribute) {
            super(null);
            buildAttribute.getClass();
            this.reason = buildAttribute;
        }

        public final BuildAttribute getReason() {
            return this.reason;
        }
    }

    public /* synthetic */ ChangesEither(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ChangesEither() {
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangesEither$Known;", "Lorg/jetbrains/kotlin/incremental/ChangesEither;", "lookupSymbols", "", "Lorg/jetbrains/kotlin/incremental/LookupSymbol;", "fqNames", "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Ljava/util/Collection;Ljava/util/Collection;)V", "getLookupSymbols", "()Ljava/util/Collection;", "getFqNames", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Known extends ChangesEither {
        private final Collection<FqName> fqNames;
        private final Collection<LookupSymbol> lookupSymbols;

        public /* synthetic */ Known(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2);
        }

        public final Collection<FqName> getFqNames() {
            return this.fqNames;
        }

        public final Collection<LookupSymbol> getLookupSymbols() {
            return this.lookupSymbols;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Known(Collection<LookupSymbol> collection, Collection<FqName> collection2) {
            super(null);
            collection.getClass();
            collection2.getClass();
            this.lookupSymbols = collection;
            this.fqNames = collection2;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public Known() {
            Collection collection = null;
            this(collection, collection, 3, collection);
        }
    }
}
