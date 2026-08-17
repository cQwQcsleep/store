package org.jetbrains.kotlin.fir;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.mpp.RegularClassSymbolMarker;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.calls.mpp.ExpectActualMatchingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001J(\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH&J\u001e\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H&J\u001e\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H&R\u0012\u0010\u000b\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirExpectActualMatchingContext;", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualMatchingContext;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "expectScopeSession", "getExpectScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getCallablesForExpectClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/mpp/RegularClassSymbolMarker;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getStaticCallablesForExpectClass", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirExpectActualMatchingContext extends ExpectActualMatchingContext<FirBasedSymbol<?>> {
    static /* synthetic */ Collection getConstructors$default(FirExpectActualMatchingContext firExpectActualMatchingContext, FirClassSymbol firClassSymbol, ScopeSession scopeSession, FirSession firSession, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getConstructors");
            return null;
        }
        if ((i & 2) != 0) {
            firSession = firClassSymbol.getModuleData().getSession();
        }
        return firExpectActualMatchingContext.getConstructors(firClassSymbol, scopeSession, firSession);
    }

    List<FirCallableSymbol<?>> getCallablesForExpectClass(RegularClassSymbolMarker regularClassSymbolMarker, Name name);

    Collection<FirConstructorSymbol> getConstructors(FirClassSymbol<?> firClassSymbol, ScopeSession scopeSession, FirSession firSession);

    ScopeSession getExpectScopeSession();

    List<FirCallableSymbol<?>> getStaticCallablesForExpectClass(RegularClassSymbolMarker regularClassSymbolMarker, Name name);
}
