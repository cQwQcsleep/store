package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t0\u000bH\u0016J\u001e\u0010\r\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0017b\u0002\b\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirTrivialEnumEntryScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirDelegatingTypeScope;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "delegate", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)V", "processDeclaredConstructors", Argument.Delimiters.none, "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "withReplacedSessionOrNull", "newSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTrivialEnumEntryScope extends FirDelegatingTypeScope {
    private final FirTypeScope delegate;
    private final FirClass klass;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirTrivialEnumEntryScope(FirClass firClass, FirTypeScope firTypeScope) {
        super(firTypeScope);
        firClass.getClass();
        firTypeScope.getClass();
        this.klass = firClass;
        this.delegate = firTypeScope;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        for (FirDeclaration firDeclaration : this.klass.getDeclarations()) {
            if (firDeclaration instanceof FirConstructor) {
                processor.invoke(((FirConstructor) firDeclaration).getSymbol());
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirDelegatingTypeScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirTypeScope firTypeScopeWithReplacedSessionOrNull = this.delegate.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firTypeScopeWithReplacedSessionOrNull != null) {
            return new FirTrivialEnumEntryScope(this.klass, firTypeScopeWithReplacedSessionOrNull);
        }
        return null;
    }
}
