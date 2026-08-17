package org.jetbrains.kotlin.fir.extensions.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar;
import org.jetbrains.kotlin.fir.extensions.FirExtensionSessionComponent;
import org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProviderKt;
import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\n\u001a\u00020\u000b*\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\f\u0010\u0014\u001a\u00020\u000e*\u00020\u0010H\u0002R\u0012\u0010\u0006\u001a\u00020\u0007X¤\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\"\u0010\u0011\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/utils/AbstractSimpleClassPredicateMatchingService;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "predicate", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate;", "getPredicate", "()Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate;", "registerPredicates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationPredicateRegistrar;", "isAnnotated", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "cache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", Argument.Delimiters.none, "annotated", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractSimpleClassPredicateMatchingService extends FirExtensionSessionComponent {
    private final FirCache cache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractSimpleClassPredicateMatchingService(FirSession firSession) {
        super(firSession);
        firSession.getClass();
        this.cache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.extensions.utils.AbstractSimpleClassPredicateMatchingService$cache$1
            public final Boolean invoke(FirRegularClassSymbol firRegularClassSymbol, Void r2) {
                firRegularClassSymbol.getClass();
                return Boolean.valueOf(this.this$0.annotated(firRegularClassSymbol));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean annotated(FirRegularClassSymbol firRegularClassSymbol) {
        if (FirPredicateBasedProviderKt.getPredicateBasedProvider(getSession()).matches(getPredicate(), firRegularClassSymbol)) {
            return true;
        }
        List<ConeKotlinType> resolvedSuperTypes = firRegularClassSymbol.getResolvedSuperTypes();
        if ((resolvedSuperTypes instanceof Collection) && resolvedSuperTypes.isEmpty()) {
            return false;
        }
        Iterator<T> it = resolvedSuperTypes.iterator();
        while (it.hasNext()) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((ConeKotlinType) it.next(), getSession());
            if (regularClassSymbol == null ? false : ((Boolean) this.cache.getValue(regularClassSymbol, null)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public abstract DeclarationPredicate getPredicate();

    public final boolean isAnnotated(FirRegularClassSymbol symbol) {
        symbol.getClass();
        return ((Boolean) this.cache.getValue(symbol, null)).booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirExtension
    public final void registerPredicates(FirDeclarationPredicateRegistrar firDeclarationPredicateRegistrar) {
        firDeclarationPredicateRegistrar.getClass();
        firDeclarationPredicateRegistrar.register(getPredicate());
    }
}
