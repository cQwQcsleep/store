package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirNameConflictsTracker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirNameConflictsTrackerImpl;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u0006H\u0016J:\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00062\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0012H\u0016R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNameConflictsTrackerImpl;", "Lorg/jetbrains/kotlin/fir/FirNameConflictsTracker;", "<init>", "()V", "redeclaredClassifiers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNameConflictsTrackerImpl$ClassifierRedeclarationImpl;", "getClassifierRedeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirNameConflictsTracker$ClassifierRedeclaration;", "classId", "registerClassifierRedeclaration", Argument.Delimiters.none, "newSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "newSymbolFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "prevSymbol", "prevSymbolFile", "ClassifierRedeclarationImpl", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNameConflictsTrackerImpl extends FirNameConflictsTracker {
    private final Map<ClassId, Set<ClassifierRedeclarationImpl>> redeclaredClassifiers = new HashMap();

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNameConflictsTrackerImpl$ClassifierRedeclarationImpl;", "Lorg/jetbrains/kotlin/fir/FirNameConflictsTracker$ClassifierRedeclaration;", "classifierSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "containingFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "getClassifierSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getContainingFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ClassifierRedeclarationImpl extends FirNameConflictsTracker.ClassifierRedeclaration {
        private final FirClassLikeSymbol<?> classifierSymbol;
        private final FirFile containingFile;

        public ClassifierRedeclarationImpl(FirClassLikeSymbol<?> firClassLikeSymbol, FirFile firFile) {
            firClassLikeSymbol.getClass();
            this.classifierSymbol = firClassLikeSymbol;
            this.containingFile = firFile;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ClassifierRedeclarationImpl copy$default(ClassifierRedeclarationImpl classifierRedeclarationImpl, FirClassLikeSymbol firClassLikeSymbol, FirFile firFile, int i, Object obj) {
            if ((i & 1) != 0) {
                firClassLikeSymbol = classifierRedeclarationImpl.classifierSymbol;
            }
            if ((i & 2) != 0) {
                firFile = classifierRedeclarationImpl.containingFile;
            }
            return classifierRedeclarationImpl.copy(firClassLikeSymbol, firFile);
        }

        public final FirClassLikeSymbol<?> component1() {
            return this.classifierSymbol;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final FirFile getContainingFile() {
            return this.containingFile;
        }

        public final ClassifierRedeclarationImpl copy(FirClassLikeSymbol<?> classifierSymbol, FirFile containingFile) {
            classifierSymbol.getClass();
            return new ClassifierRedeclarationImpl(classifierSymbol, containingFile);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ClassifierRedeclarationImpl)) {
                return false;
            }
            ClassifierRedeclarationImpl classifierRedeclarationImpl = (ClassifierRedeclarationImpl) other;
            return Intrinsics.areEqual(this.classifierSymbol, classifierRedeclarationImpl.classifierSymbol) && Intrinsics.areEqual(this.containingFile, classifierRedeclarationImpl.containingFile);
        }

        @Override // org.jetbrains.kotlin.fir.FirNameConflictsTracker.ClassifierRedeclaration
        public FirClassLikeSymbol<?> getClassifierSymbol() {
            return this.classifierSymbol;
        }

        @Override // org.jetbrains.kotlin.fir.FirNameConflictsTracker.ClassifierRedeclaration
        public FirFile getContainingFile() {
            return this.containingFile;
        }

        public int hashCode() {
            int iHashCode = this.classifierSymbol.hashCode() * 31;
            FirFile firFile = this.containingFile;
            return iHashCode + (firFile == null ? 0 : firFile.hashCode());
        }

        public String toString() {
            return "ClassifierRedeclarationImpl(classifierSymbol=" + this.classifierSymbol + ", containingFile=" + this.containingFile + ')';
        }
    }

    public static Set a(Function2 function2, Object obj, Object obj2) {
        return (Set) function2.invoke(obj, obj2);
    }

    public static Set b(Set set, Set set2) {
        set.getClass();
        set2.getClass();
        return SetsKt.plus(set, set2);
    }

    @Override // org.jetbrains.kotlin.fir.FirNameConflictsTracker
    public Collection<FirNameConflictsTracker.ClassifierRedeclaration> getClassifierRedeclarations(ClassId classId) {
        classId.getClass();
        Set<ClassifierRedeclarationImpl> setEmptySet = this.redeclaredClassifiers.get(classId);
        if (setEmptySet == null) {
            setEmptySet = SetsKt.emptySet();
        }
        return setEmptySet;
    }

    @Override // org.jetbrains.kotlin.fir.FirNameConflictsTracker
    public void registerClassifierRedeclaration(ClassId classId, FirClassLikeSymbol<?> newSymbol, FirFile newSymbolFile, FirClassLikeSymbol<?> prevSymbol, FirFile prevSymbolFile) {
        classId.getClass();
        newSymbol.getClass();
        newSymbolFile.getClass();
        prevSymbol.getClass();
        Map<ClassId, Set<ClassifierRedeclarationImpl>> map = this.redeclaredClassifiers;
        LinkedHashSet linkedHashSetLinkedSetOf = SetsKt.linkedSetOf(new ClassifierRedeclarationImpl[]{new ClassifierRedeclarationImpl(newSymbol, newSymbolFile), new ClassifierRedeclarationImpl(prevSymbol, prevSymbolFile)});
        final Function2 function2 = new Function2() { // from class: ua5
            public final Object invoke(Object obj, Object obj2) {
                return FirNameConflictsTrackerImpl.b((Set) obj, (Set) obj2);
            }
        };
        map.merge(classId, linkedHashSetLinkedSetOf, new BiFunction() { // from class: va5
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return FirNameConflictsTrackerImpl.a(function2, obj, obj2);
            }
        });
    }
}
