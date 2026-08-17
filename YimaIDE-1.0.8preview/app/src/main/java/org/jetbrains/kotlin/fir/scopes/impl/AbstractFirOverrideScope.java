package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0000\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J(\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u000e2\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\u0013H\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR*\u0010\f\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u000e0\rX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/AbstractFirOverrideScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "overrideChecker", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getOverrideChecker", "()Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "overrideByBase", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getOverrideByBase", "()Ljava/util/Map;", "getOverridden", "overrideCandidates", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFirOverrideScope extends FirTypeScope {
    private final Map<FirCallableSymbol<?>, FirCallableSymbol<?>> overrideByBase;
    private final FirOverrideChecker overrideChecker;
    private final FirSession session;

    public AbstractFirOverrideScope(FirSession firSession, FirOverrideChecker firOverrideChecker) {
        firSession.getClass();
        firOverrideChecker.getClass();
        this.session = firSession;
        this.overrideChecker = firOverrideChecker;
        this.overrideByBase = new HashMap();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public FirCallableSymbol<?> getOverridden(FirCallableSymbol<?> firCallableSymbol, Set<? extends FirCallableSymbol<?>> set) throws KotlinIllegalArgumentExceptionWithAttachments {
        firCallableSymbol.getClass();
        set.getClass();
        FirCallableSymbol<?> firCallableSymbol2 = this.overrideByBase.get(firCallableSymbol);
        if (firCallableSymbol2 != null) {
            return firCallableSymbol2;
        }
        Object fir = firCallableSymbol.getFir();
        fir.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) fir;
        for (Object obj : set) {
            FirCallableSymbol firCallableSymbol3 = (FirCallableSymbol) obj;
            firCallableSymbol3.getClass();
            D fir2 = firCallableSymbol3.getFir();
            fir2.getClass();
            if (AbstractFirOverrideScopeKt.similarFunctionsOrBothProperties(this.overrideChecker, (FirCallableDeclaration) fir2, firCallableDeclaration)) {
                FirCallableSymbol<?> firCallableSymbol4 = (FirCallableSymbol) obj;
                this.overrideByBase.put(firCallableSymbol, firCallableSymbol4);
                return firCallableSymbol4;
            }
        }
        obj = null;
        FirCallableSymbol<?> firCallableSymbol5 = (FirCallableSymbol) obj;
        this.overrideByBase.put(firCallableSymbol, firCallableSymbol5);
        return firCallableSymbol5;
    }

    public final Map<FirCallableSymbol<?>, FirCallableSymbol<?>> getOverrideByBase() {
        return this.overrideByBase;
    }

    public final FirOverrideChecker getOverrideChecker() {
        return this.overrideChecker;
    }

    public final FirSession getSession() {
        return this.session;
    }
}
