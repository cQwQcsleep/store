package org.jetbrains.kotlin.fir.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate;
import org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001c\u0010\t\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\r2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirEmptyPredicateBasedProvider;", "Lorg/jetbrains/kotlin/fir/extensions/FirPredicateBasedProvider;", "<init>", "()V", "getSymbolsByPredicate", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "predicate", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "getOwnersOfDeclaration", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "fileHasPluginAnnotations", Argument.Delimiters.none, "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "matches", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEmptyPredicateBasedProvider extends FirPredicateBasedProvider {
    public static final FirEmptyPredicateBasedProvider INSTANCE = new FirEmptyPredicateBasedProvider();

    private FirEmptyPredicateBasedProvider() {
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider
    public boolean fileHasPluginAnnotations(FirFile file) {
        file.getClass();
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider
    public List<FirBasedSymbol<?>> getOwnersOfDeclaration(FirDeclaration declaration) {
        declaration.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider
    public List<FirBasedSymbol<?>> getSymbolsByPredicate(LookupPredicate predicate) {
        predicate.getClass();
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider
    public boolean matches(AbstractPredicate<?> predicate, FirDeclaration declaration) {
        predicate.getClass();
        declaration.getClass();
        return false;
    }
}
