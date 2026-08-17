package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirDelegatingContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J.\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u000fH\u0016J\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0017b\u0002\b\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScopeWithSubstitution;", "Lorg/jetbrains/kotlin/fir/scopes/FirDelegatingContainingNamesAwareScope;", "originalScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;)V", "getOriginalScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "withReplacedSessionOrNull", "newSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNestedClassifierScopeWithSubstitution extends FirDelegatingContainingNamesAwareScope {
    private final FirContainingNamesAwareScope originalScope;
    private final ConeSubstitutor substitutor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirNestedClassifierScopeWithSubstitution(FirContainingNamesAwareScope firContainingNamesAwareScope, ConeSubstitutor coneSubstitutor) {
        super(firContainingNamesAwareScope);
        firContainingNamesAwareScope.getClass();
        coneSubstitutor.getClass();
        this.originalScope = firContainingNamesAwareScope;
        this.substitutor = coneSubstitutor;
    }

    public final FirContainingNamesAwareScope getOriginalScope() {
        return this.originalScope;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        FirClassifierSymbol<?> singleClassifier = FirScopeKt.getSingleClassifier(this.originalScope, name);
        FirClassLikeSymbol firClassLikeSymbol = singleClassifier instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) singleClassifier : null;
        if (firClassLikeSymbol == null) {
            return;
        }
        ConeSubstitutor coneSubstitutor = ((FirMemberDeclaration) firClassLikeSymbol.getFir()).getStatus().isInner() ? this.substitutor : null;
        if (coneSubstitutor == null) {
            coneSubstitutor = ConeSubstitutor.Empty.INSTANCE;
        }
        processor.invoke(firClassLikeSymbol, coneSubstitutor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirNestedClassifierScopeWithSubstitution withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirContainingNamesAwareScope firContainingNamesAwareScopeWithReplacedSessionOrNull = this.originalScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firContainingNamesAwareScopeWithReplacedSessionOrNull != null) {
            return new FirNestedClassifierScopeWithSubstitution(firContainingNamesAwareScopeWithReplacedSessionOrNull, this.substitutor);
        }
        return null;
    }
}
