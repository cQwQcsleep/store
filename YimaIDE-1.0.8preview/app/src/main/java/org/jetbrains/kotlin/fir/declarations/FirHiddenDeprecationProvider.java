package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirHiddenDeprecationProvider;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "isDeprecationLevelHidden", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirHiddenDeprecationProvider implements FirSessionComponent {
    private final FirSession session;

    public FirHiddenDeprecationProvider(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public boolean isDeprecationLevelHidden(FirBasedSymbol<?> symbol) {
        FirDeprecationInfo all;
        FirDeprecationInfo all2;
        symbol.getClass();
        DeprecationLevelValue deprecationLevel = null;
        if (symbol instanceof FirCallableSymbol) {
            DeprecationsPerUseSite deprecation = ((FirCallableSymbol) symbol).getDeprecation(FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session));
            if (deprecation != null && (all2 = deprecation.getAll()) != null) {
                deprecationLevel = all2.getDeprecationLevel();
            }
            return deprecationLevel == DeprecationLevelValue.HIDDEN;
        }
        if (symbol instanceof FirClassLikeSymbol) {
            DeprecationsPerUseSite ownDeprecation = ((FirClassLikeSymbol) symbol).getOwnDeprecation(FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session));
            if (ownDeprecation != null && (all = ownDeprecation.getAll()) != null) {
                deprecationLevel = all.getDeprecationLevel();
            }
            if (deprecationLevel == DeprecationLevelValue.HIDDEN) {
                return true;
            }
        }
        return false;
    }
}
