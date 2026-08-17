package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ClassSymbolAndProjections;", Argument.Delimiters.none, "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "projections", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Ljava/util/List;)V", "getClassSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getProjections", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final /* data */ class ClassSymbolAndProjections {
    private final FirClassSymbol<?> classSymbol;
    private final List<ConeTypeProjection> projections;

    public ClassSymbolAndProjections(FirClassSymbol<?> firClassSymbol, List<ConeTypeProjection> list) {
        firClassSymbol.getClass();
        list.getClass();
        this.classSymbol = firClassSymbol;
        this.projections = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ClassSymbolAndProjections copy$default(ClassSymbolAndProjections classSymbolAndProjections, FirClassSymbol firClassSymbol, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            firClassSymbol = classSymbolAndProjections.classSymbol;
        }
        if ((i & 2) != 0) {
            list = classSymbolAndProjections.projections;
        }
        return classSymbolAndProjections.copy(firClassSymbol, list);
    }

    public final FirClassSymbol<?> component1() {
        return this.classSymbol;
    }

    public final List<ConeTypeProjection> component2() {
        return this.projections;
    }

    public final ClassSymbolAndProjections copy(FirClassSymbol<?> classSymbol, List<ConeTypeProjection> projections) {
        classSymbol.getClass();
        projections.getClass();
        return new ClassSymbolAndProjections(classSymbol, projections);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassSymbolAndProjections)) {
            return false;
        }
        ClassSymbolAndProjections classSymbolAndProjections = (ClassSymbolAndProjections) other;
        return Intrinsics.areEqual(this.classSymbol, classSymbolAndProjections.classSymbol) && Intrinsics.areEqual(this.projections, classSymbolAndProjections.projections);
    }

    public final FirClassSymbol<?> getClassSymbol() {
        return this.classSymbol;
    }

    public final List<ConeTypeProjection> getProjections() {
        return this.projections;
    }

    public int hashCode() {
        return (this.classSymbol.hashCode() * 31) + this.projections.hashCode();
    }

    public String toString() {
        return "ClassSymbolAndProjections(classSymbol=" + this.classSymbol + ", projections=" + this.projections + ')';
    }
}
