package org.jetbrains.kotlin.fir.extensions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate;
import org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u001c\u0010\t\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u001c\u0010\u0010\u001a\u00020\r2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\u0010\u001a\u00020\r2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00112\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0006J \u0010\u0010\u001a\u00020\r2\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u00052\u0006\u0010\n\u001a\u00020\u000bJ$\u0010\u0010\u001a\u00020\r2\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u00052\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0006J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016H\u0017b\u0002\b\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirPredicateBasedProvider;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "getSymbolsByPredicate", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "predicate", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "getOwnersOfDeclaration", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "fileHasPluginAnnotations", Argument.Delimiters.none, "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "matches", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "predicates", "registerAnnotatedDeclaration", Argument.Delimiters.none, "owners", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionApiInternals;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirPredicateBasedProvider implements FirSessionComponent {
    public abstract boolean fileHasPluginAnnotations(FirFile file);

    public abstract List<FirBasedSymbol<?>> getOwnersOfDeclaration(FirDeclaration declaration);

    public abstract List<FirBasedSymbol<?>> getSymbolsByPredicate(LookupPredicate predicate);

    public final boolean matches(List<? extends AbstractPredicate<?>> predicates, FirDeclaration declaration) {
        predicates.getClass();
        declaration.getClass();
        List<? extends AbstractPredicate<?>> list = predicates;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (matches((AbstractPredicate<?>) it.next(), declaration)) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean matches(AbstractPredicate<?> predicate, FirDeclaration declaration);

    @FirExtensionApiInternals
    public void registerAnnotatedDeclaration(FirDeclaration declaration, PersistentList<? extends FirDeclaration> owners) {
        declaration.getClass();
        owners.getClass();
    }

    public final boolean matches(AbstractPredicate<?> predicate, FirBasedSymbol<?> declaration) {
        predicate.getClass();
        declaration.getClass();
        return matches(predicate, declaration.getFir());
    }

    public final boolean matches(List<? extends AbstractPredicate<?>> predicates, FirBasedSymbol<?> declaration) {
        predicates.getClass();
        declaration.getClass();
        return matches(predicates, declaration.getFir());
    }
}
