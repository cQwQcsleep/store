package org.jetbrains.kotlin.fir;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&J:\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH&¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirNameConflictsTracker;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "getClassifierRedeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirNameConflictsTracker$ClassifierRedeclaration;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "registerClassifierRedeclaration", Argument.Delimiters.none, "newSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "newSymbolFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "prevSymbol", "prevSymbolFile", "ClassifierRedeclaration", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirNameConflictsTracker implements FirSessionComponent {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirNameConflictsTracker$ClassifierRedeclaration;", Argument.Delimiters.none, "<init>", "()V", "classifierSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getClassifierSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "containingFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getContainingFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ClassifierRedeclaration {
        public abstract FirClassLikeSymbol<?> getClassifierSymbol();

        public abstract FirFile getContainingFile();
    }

    public abstract Collection<ClassifierRedeclaration> getClassifierRedeclarations(ClassId classId);

    public abstract void registerClassifierRedeclaration(ClassId classId, FirClassLikeSymbol<?> newSymbol, FirFile newSymbolFile, FirClassLikeSymbol<?> prevSymbol, FirFile prevSymbolFile);
}
