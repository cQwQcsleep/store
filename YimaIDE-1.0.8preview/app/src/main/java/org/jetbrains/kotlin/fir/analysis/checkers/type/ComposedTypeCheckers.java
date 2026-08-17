package org.jetbrains.kotlin.fir.analysis.checkers.type;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentInternal;
import org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.type.ComposedTypeCheckers;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\u0014\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0001H\u0007b\u0002\b(R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u000fj\u0002`\u00110\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00150\u000fj\u0002`\u00160\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R$\u0010\u0018\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00190\u000fj\u0002`\u001a0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R$\u0010\u001c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001d0\u000fj\u0002`\u001e0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R\u001e\u0010 \u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u000fj\u0002`\u00110!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00150\u000fj\u0002`\u00160!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00190\u000fj\u0002`\u001a0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001d0\u000fj\u0002`\u001e0!X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/ComposedTypeCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "predicate", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirCheckerWithMppKind;", Argument.Delimiters.none, "<init>", "(Lkotlin/jvm/functions/Function1;)V", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "getPredicate", "()Lkotlin/jvm/functions/Function1;", "typeRefCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeRefChecker;", "getTypeRefCheckers", "()Ljava/util/Set;", "resolvedTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "getResolvedTypeRefCheckers", "functionTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirFunctionTypeRefChecker;", "getFunctionTypeRefCheckers", "intersectionTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirIntersectionTypeRefChecker;", "getIntersectionTypeRefCheckers", "_typeRefCheckers", Argument.Delimiters.none, "_resolvedTypeRefCheckers", "_functionTypeRefCheckers", "_intersectionTypeRefCheckers", "register", Argument.Delimiters.none, "checkers", "Lorg/jetbrains/kotlin/fir/analysis/CheckersComponentInternal;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComposedTypeCheckers extends TypeCheckers {
    private final Set<FirTypeChecker<FirFunctionTypeRef>> _functionTypeRefCheckers;
    private final Set<FirTypeChecker<FirIntersectionTypeRef>> _intersectionTypeRefCheckers;
    private final Set<FirTypeChecker<FirResolvedTypeRef>> _resolvedTypeRefCheckers;
    private final Set<FirTypeChecker<FirTypeRef>> _typeRefCheckers;
    private final Function1<FirCheckerWithMppKind, Boolean> predicate;

    /* JADX WARN: Multi-variable type inference failed */
    public ComposedTypeCheckers(Function1<? super FirCheckerWithMppKind, Boolean> function1) {
        function1.getClass();
        this.predicate = function1;
        this._typeRefCheckers = new LinkedHashSet();
        this._resolvedTypeRefCheckers = new LinkedHashSet();
        this._functionTypeRefCheckers = new LinkedHashSet();
        this._intersectionTypeRefCheckers = new LinkedHashSet();
    }

    public static boolean e(MppCheckerKind mppCheckerKind, FirCheckerWithMppKind firCheckerWithMppKind) {
        firCheckerWithMppKind.getClass();
        return firCheckerWithMppKind.getMppKind() == mppCheckerKind;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirFunctionTypeRef>> getFunctionTypeRefCheckers() {
        return this._functionTypeRefCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirIntersectionTypeRef>> getIntersectionTypeRefCheckers() {
        return this._intersectionTypeRefCheckers;
    }

    public final Function1<FirCheckerWithMppKind, Boolean> getPredicate() {
        return this.predicate;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirResolvedTypeRef>> getResolvedTypeRefCheckers() {
        return this._resolvedTypeRefCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirTypeRef>> getTypeRefCheckers() {
        return this._typeRefCheckers;
    }

    @CheckersComponentInternal
    public final void register(TypeCheckers checkers) {
        checkers.getClass();
        Set<FirTypeChecker<FirTypeRef>> typeRefCheckers = checkers.getTypeRefCheckers();
        Collection collection = this._typeRefCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function1 = this.predicate;
        for (Object obj : typeRefCheckers) {
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                collection.add(obj);
            }
        }
        Set<FirTypeChecker<FirResolvedTypeRef>> resolvedTypeRefCheckers = checkers.getResolvedTypeRefCheckers();
        Collection collection2 = this._resolvedTypeRefCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function2 = this.predicate;
        for (Object obj2 : resolvedTypeRefCheckers) {
            if (((Boolean) function2.invoke(obj2)).booleanValue()) {
                collection2.add(obj2);
            }
        }
        Set<FirTypeChecker<FirFunctionTypeRef>> functionTypeRefCheckers = checkers.getFunctionTypeRefCheckers();
        Collection collection3 = this._functionTypeRefCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function3 = this.predicate;
        for (Object obj3 : functionTypeRefCheckers) {
            if (((Boolean) function3.invoke(obj3)).booleanValue()) {
                collection3.add(obj3);
            }
        }
        Set<FirTypeChecker<FirIntersectionTypeRef>> intersectionTypeRefCheckers = checkers.getIntersectionTypeRefCheckers();
        Collection collection4 = this._intersectionTypeRefCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function4 = this.predicate;
        for (Object obj4 : intersectionTypeRefCheckers) {
            if (((Boolean) function4.invoke(obj4)).booleanValue()) {
                collection4.add(obj4);
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ComposedTypeCheckers(final MppCheckerKind mppCheckerKind) {
        this((Function1<? super FirCheckerWithMppKind, Boolean>) new Function1() { // from class: rm2
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ComposedTypeCheckers.e(mppCheckerKind, (FirCheckerWithMppKind) obj));
            }
        });
        mppCheckerKind.getClass();
    }
}
