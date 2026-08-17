package org.jetbrains.kotlin.fir.analysis.checkers.type;

import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentInternal;
import org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\b&\u0018\u0000 +2\u00020\u0001:\u0001+B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR5\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00188@X\u0081\u0084\u0002r\u0002\b\u001e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u0012\u0004\b\u0019\u0010\u0003\u001a\u0004\b\u001a\u0010\u001bR5\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u00188@X\u0081\u0084\u0002r\u0002\b\u001e¢\u0006\u0012\n\u0004\b\"\u0010\u001d\u0012\u0004\b \u0010\u0003\u001a\u0004\b!\u0010\u001bR5\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u00188@X\u0081\u0084\u0002r\u0002\b\u001e¢\u0006\u0012\n\u0004\b&\u0010\u001d\u0012\u0004\b$\u0010\u0003\u001a\u0004\b%\u0010\u001bR5\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u00188@X\u0081\u0084\u0002r\u0002\b\u001e¢\u0006\u0012\n\u0004\b*\u0010\u001d\u0012\u0004\b(\u0010\u0003\u001a\u0004\b)\u0010\u001b¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", Argument.Delimiters.none, "<init>", "()V", "typeRefCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeRefChecker;", "getTypeRefCheckers", "()Ljava/util/Set;", "resolvedTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "getResolvedTypeRefCheckers", "functionTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirFunctionTypeRefChecker;", "getFunctionTypeRefCheckers", "intersectionTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirIntersectionTypeRefChecker;", "getIntersectionTypeRefCheckers", "allTypeRefCheckers", Argument.Delimiters.none, "getAllTypeRefCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllTypeRefCheckers$org_jetbrains_kotlin_checkers", "()[Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "allTypeRefCheckers$delegate", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/fir/analysis/CheckersComponentInternal;", "allResolvedTypeRefCheckers", "getAllResolvedTypeRefCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllResolvedTypeRefCheckers$org_jetbrains_kotlin_checkers", "allResolvedTypeRefCheckers$delegate", "allFunctionTypeRefCheckers", "getAllFunctionTypeRefCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllFunctionTypeRefCheckers$org_jetbrains_kotlin_checkers", "allFunctionTypeRefCheckers$delegate", "allIntersectionTypeRefCheckers", "getAllIntersectionTypeRefCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllIntersectionTypeRefCheckers$org_jetbrains_kotlin_checkers", "allIntersectionTypeRefCheckers$delegate", "Companion", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class TypeCheckers {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TypeCheckers EMPTY = new TypeCheckers() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers$Companion$EMPTY$1
    };
    private final Set<FirTypeChecker<FirTypeRef>> typeRefCheckers = SetsKt.emptySet();
    private final Set<FirTypeChecker<FirResolvedTypeRef>> resolvedTypeRefCheckers = SetsKt.emptySet();
    private final Set<FirTypeChecker<FirFunctionTypeRef>> functionTypeRefCheckers = SetsKt.emptySet();
    private final Set<FirTypeChecker<FirIntersectionTypeRef>> intersectionTypeRefCheckers = SetsKt.emptySet();

    /* JADX INFO: renamed from: allTypeRefCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allTypeRefCheckers = LazyKt.lazy(new Function0() { // from class: fte
        public final Object invoke() {
            return TypeCheckers.a(this.b);
        }
    });

    /* JADX INFO: renamed from: allResolvedTypeRefCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allResolvedTypeRefCheckers = LazyKt.lazy(new Function0() { // from class: gte
        public final Object invoke() {
            return TypeCheckers.c(this.b);
        }
    });

    /* JADX INFO: renamed from: allFunctionTypeRefCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allFunctionTypeRefCheckers = LazyKt.lazy(new Function0() { // from class: hte
        public final Object invoke() {
            return TypeCheckers.d(this.b);
        }
    });

    /* JADX INFO: renamed from: allIntersectionTypeRefCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allIntersectionTypeRefCheckers = LazyKt.lazy(new Function0() { // from class: ite
        public final Object invoke() {
            return TypeCheckers.b(this.b);
        }
    });

    public static FirTypeChecker[] a(TypeCheckers typeCheckers) {
        return (FirTypeChecker[]) typeCheckers.getTypeRefCheckers().toArray(new FirTypeChecker[0]);
    }

    public static FirTypeChecker[] b(TypeCheckers typeCheckers) {
        return (FirTypeChecker[]) SetsKt.plus(typeCheckers.getIntersectionTypeRefCheckers(), typeCheckers.getTypeRefCheckers()).toArray(new FirTypeChecker[0]);
    }

    public static FirTypeChecker[] c(TypeCheckers typeCheckers) {
        return (FirTypeChecker[]) SetsKt.plus(typeCheckers.getResolvedTypeRefCheckers(), typeCheckers.getTypeRefCheckers()).toArray(new FirTypeChecker[0]);
    }

    public static FirTypeChecker[] d(TypeCheckers typeCheckers) {
        return (FirTypeChecker[]) SetsKt.plus(typeCheckers.getFunctionTypeRefCheckers(), typeCheckers.getTypeRefCheckers()).toArray(new FirTypeChecker[0]);
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllFunctionTypeRefCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllIntersectionTypeRefCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllResolvedTypeRefCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllTypeRefCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    public final FirTypeChecker<FirFunctionTypeRef>[] getAllFunctionTypeRefCheckers$org_jetbrains_kotlin_checkers() {
        return (FirTypeChecker[]) this.allFunctionTypeRefCheckers.getValue();
    }

    public final FirTypeChecker<FirIntersectionTypeRef>[] getAllIntersectionTypeRefCheckers$org_jetbrains_kotlin_checkers() {
        return (FirTypeChecker[]) this.allIntersectionTypeRefCheckers.getValue();
    }

    public final FirTypeChecker<FirResolvedTypeRef>[] getAllResolvedTypeRefCheckers$org_jetbrains_kotlin_checkers() {
        return (FirTypeChecker[]) this.allResolvedTypeRefCheckers.getValue();
    }

    public final FirTypeChecker<FirTypeRef>[] getAllTypeRefCheckers$org_jetbrains_kotlin_checkers() {
        return (FirTypeChecker[]) this.allTypeRefCheckers.getValue();
    }

    public Set<FirTypeChecker<FirFunctionTypeRef>> getFunctionTypeRefCheckers() {
        return this.functionTypeRefCheckers;
    }

    public Set<FirTypeChecker<FirIntersectionTypeRef>> getIntersectionTypeRefCheckers() {
        return this.intersectionTypeRefCheckers;
    }

    public Set<FirTypeChecker<FirResolvedTypeRef>> getResolvedTypeRefCheckers() {
        return this.resolvedTypeRefCheckers;
    }

    public Set<FirTypeChecker<FirTypeRef>> getTypeRefCheckers() {
        return this.typeRefCheckers;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers$Companion;", Argument.Delimiters.none, "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "getEMPTY", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TypeCheckers getEMPTY() {
            return TypeCheckers.EMPTY;
        }

        private Companion() {
        }
    }
}
