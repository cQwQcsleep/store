package org.jetbrains.kotlin.fir.analysis.checkers.type;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u000f0\u0005j\u0002`\u00100\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0005j\u0002`\u00150\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0005j\u0002`\u00190\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0005j\u0002`\u001d0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FilteredTypeCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "delegate", "predicate", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;Lkotlin/jvm/functions/Function1;)V", "getDelegate", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "getPredicate", "()Lkotlin/jvm/functions/Function1;", "typeRefCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeRefChecker;", "getTypeRefCheckers", "()Ljava/util/Set;", "resolvedTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "getResolvedTypeRefCheckers", "functionTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirFunctionTypeRefChecker;", "getFunctionTypeRefCheckers", "intersectionTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirIntersectionTypeRefChecker;", "getIntersectionTypeRefCheckers", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FilteredTypeCheckers extends TypeCheckers {
    private final TypeCheckers delegate;
    private final Set<FirTypeChecker<FirFunctionTypeRef>> functionTypeRefCheckers;
    private final Set<FirTypeChecker<FirIntersectionTypeRef>> intersectionTypeRefCheckers;
    private final Function1<FirTypeChecker<?>, Boolean> predicate;
    private final Set<FirTypeChecker<FirResolvedTypeRef>> resolvedTypeRefCheckers;
    private final Set<FirTypeChecker<FirTypeRef>> typeRefCheckers;

    /* JADX WARN: Multi-variable type inference failed */
    public FilteredTypeCheckers(TypeCheckers typeCheckers, Function1<? super FirTypeChecker<?>, Boolean> function1) {
        typeCheckers.getClass();
        function1.getClass();
        this.delegate = typeCheckers;
        this.predicate = function1;
        Set<FirTypeChecker<FirTypeRef>> typeRefCheckers = typeCheckers.getTypeRefCheckers();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : typeRefCheckers) {
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                linkedHashSet.add(obj);
            }
        }
        this.typeRefCheckers = linkedHashSet;
        Set<FirTypeChecker<FirResolvedTypeRef>> resolvedTypeRefCheckers = this.delegate.getResolvedTypeRefCheckers();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        Function1<FirTypeChecker<?>, Boolean> function2 = this.predicate;
        for (Object obj2 : resolvedTypeRefCheckers) {
            if (((Boolean) function2.invoke(obj2)).booleanValue()) {
                linkedHashSet2.add(obj2);
            }
        }
        this.resolvedTypeRefCheckers = linkedHashSet2;
        Set<FirTypeChecker<FirFunctionTypeRef>> functionTypeRefCheckers = this.delegate.getFunctionTypeRefCheckers();
        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        Function1<FirTypeChecker<?>, Boolean> function3 = this.predicate;
        for (Object obj3 : functionTypeRefCheckers) {
            if (((Boolean) function3.invoke(obj3)).booleanValue()) {
                linkedHashSet3.add(obj3);
            }
        }
        this.functionTypeRefCheckers = linkedHashSet3;
        Set<FirTypeChecker<FirIntersectionTypeRef>> intersectionTypeRefCheckers = this.delegate.getIntersectionTypeRefCheckers();
        LinkedHashSet linkedHashSet4 = new LinkedHashSet();
        Function1<FirTypeChecker<?>, Boolean> function4 = this.predicate;
        for (Object obj4 : intersectionTypeRefCheckers) {
            if (((Boolean) function4.invoke(obj4)).booleanValue()) {
                linkedHashSet4.add(obj4);
            }
        }
        this.intersectionTypeRefCheckers = linkedHashSet4;
    }

    public final TypeCheckers getDelegate() {
        return this.delegate;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirFunctionTypeRef>> getFunctionTypeRefCheckers() {
        return this.functionTypeRefCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirIntersectionTypeRef>> getIntersectionTypeRefCheckers() {
        return this.intersectionTypeRefCheckers;
    }

    public final Function1<FirTypeChecker<?>, Boolean> getPredicate() {
        return this.predicate;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirResolvedTypeRef>> getResolvedTypeRefCheckers() {
        return this.resolvedTypeRefCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirTypeRef>> getTypeRefCheckers() {
        return this.typeRefCheckers;
    }
}
