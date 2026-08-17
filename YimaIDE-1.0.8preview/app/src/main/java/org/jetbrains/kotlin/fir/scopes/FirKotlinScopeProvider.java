package org.jetbrains.kotlin.fir.scopes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.impl.FirPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSessionKey;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassAnySynthesizedMemberScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassUseSiteMemberScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMemberScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirNameAwareOnlyCallablesScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirStaticScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirTrivialEnumEntryScope;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScope;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002:\u0001!B\u0082\u0001\u0012y\b\u0002\u0010\u0003\u001as\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\u0004\b\u0011\u0010\u0012J*\u0010\u0015\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\"\u0010\u001b\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\"\u0010\u001c\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016J*\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\"\u0010 \u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016R\u0082\u0001\u0010\u0003\u001as\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "declaredMemberScopeDecorator", "Lkotlin/Function5;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "klass", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "declaredMemberScope", "Lorg/jetbrains/kotlin/fir/FirSession;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "memberRequiredPhase", "<init>", "(Lkotlin/jvm/functions/Function5;)V", "getDeclaredMemberScopeDecorator", "()Lkotlin/jvm/functions/Function5;", "getUseSiteMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "getTypealiasConstructorScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "getStaticCallableMemberScope", "getStaticCallableMemberScopeForBackend", "getStaticCallableMemberScopeImpl", "forBackend", Argument.Delimiters.none, "getNestedClassifierScope", "PlatformDependentFilteringScope", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirKotlinScopeProvider extends FirScopeProvider implements FirSessionComponent {
    private final Function5<FirClass, FirContainingNamesAwareScope, FirSession, ScopeSession, FirResolvePhase, FirContainingNamesAwareScope> declaredMemberScopeDecorator;

    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0016\u0010\u0012\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020\u00100\u0013H\u0016J.\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u001c\u0010\u0012\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u0016H\u0016J\u001c\u0010\u0019\u001a\u00020\u00102\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00100\u0013H\u0016J$\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00100\u0013H\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u001c\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020'H\u0017b\u0002\b(R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider$PlatformDependentFilteringScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "declaredMemberScope", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;Lorg/jetbrains/kotlin/fir/FirSession;)V", "getDeclaredMemberScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getCallableNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "getClassifierNames", "processPropertiesByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processClassifiersByNameWithSubstitution", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "processFunctionsByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "mayContainName", Argument.Delimiters.none, "scopeOwnerLookupNames", Argument.Delimiters.none, Argument.Delimiters.none, "getScopeOwnerLookupNames", "()Ljava/util/List;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PlatformDependentFilteringScope extends FirContainingNamesAwareScope {
        private final FirContainingNamesAwareScope declaredMemberScope;
        private final FirSession session;

        public PlatformDependentFilteringScope(FirContainingNamesAwareScope firContainingNamesAwareScope, FirSession firSession) {
            firContainingNamesAwareScope.getClass();
            firSession.getClass();
            this.declaredMemberScope = firContainingNamesAwareScope;
            this.session = firSession;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Unit b(PlatformDependentFilteringScope platformDependentFilteringScope, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
            firNamedFunctionSymbol.getClass();
            if (FirPlatformDeclarationFilter.INSTANCE.isNotPlatformDependent((FirNamedFunction) firNamedFunctionSymbol.getFir(), platformDependentFilteringScope.session)) {
                function1.invoke(firNamedFunctionSymbol);
            }
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
        public Set<Name> getCallableNames() {
            return this.declaredMemberScope.getCallableNames();
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
        public Set<Name> getClassifierNames() {
            return this.declaredMemberScope.getClassifierNames();
        }

        public final FirContainingNamesAwareScope getDeclaredMemberScope() {
            return this.declaredMemberScope;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirScope
        public List<String> getScopeOwnerLookupNames() {
            return this.declaredMemberScope.getScopeOwnerLookupNames();
        }

        public final FirSession getSession() {
            return this.session;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirScope
        public boolean mayContainName(Name name) {
            name.getClass();
            return this.declaredMemberScope.mayContainName(name);
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirScope
        public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
            name.getClass();
            processor.getClass();
            this.declaredMemberScope.processClassifiersByNameWithSubstitution(name, processor);
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirScope
        public void processDeclaredConstructors(Function1<? super FirConstructorSymbol, Unit> processor) {
            processor.getClass();
            this.declaredMemberScope.processDeclaredConstructors(processor);
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirScope
        public void processFunctionsByName(Name name, final Function1<? super FirNamedFunctionSymbol, Unit> processor) {
            name.getClass();
            processor.getClass();
            this.declaredMemberScope.processFunctionsByName(name, new Function1() { // from class: ha5
                public final Object invoke(Object obj) {
                    return FirKotlinScopeProvider.PlatformDependentFilteringScope.b(this.b, processor, (FirNamedFunctionSymbol) obj);
                }
            });
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirScope
        public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
            name.getClass();
            processor.getClass();
            this.declaredMemberScope.processPropertiesByName(name, processor);
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
        @DelicateScopeAPI
        public PlatformDependentFilteringScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
            newSession.getClass();
            newScopeSession.getClass();
            FirContainingNamesAwareScope firContainingNamesAwareScopeWithReplacedSessionOrNull = this.declaredMemberScope.withReplacedSessionOrNull(newSession, newScopeSession);
            if (firContainingNamesAwareScopeWithReplacedSessionOrNull == null) {
                firContainingNamesAwareScopeWithReplacedSessionOrNull = this.declaredMemberScope;
            }
            return new PlatformDependentFilteringScope(firContainingNamesAwareScopeWithReplacedSessionOrNull, newSession);
        }
    }

    public /* synthetic */ FirKotlinScopeProvider(Function5 function5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function5() { // from class: ga5
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                return FirKotlinScopeProvider.a((FirClass) obj, (FirContainingNamesAwareScope) obj2, (FirSession) obj3, (ScopeSession) obj4, (FirResolvePhase) obj5);
            }
        } : function5);
    }

    public static FirContainingNamesAwareScope a(FirClass firClass, FirContainingNamesAwareScope firContainingNamesAwareScope, FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase) {
        firClass.getClass();
        firContainingNamesAwareScope.getClass();
        firSession.getClass();
        scopeSession.getClass();
        return new PlatformDependentFilteringScope(firContainingNamesAwareScope, firSession);
    }

    private final FirContainingNamesAwareScope getStaticCallableMemberScopeImpl(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession, boolean forBackend) {
        FirRegularClassSymbol regularClassSymbol;
        FirContainingNamesAwareScope firContainingNamesAwareScopeStaticScopeForBackend;
        FirContainingNamesAwareScope firContainingNamesAwareScopeDeclaredMemberScope = FirDeclaredMemberScopeProviderKt.declaredMemberScope(useSiteSession, klass, (FirResolvePhase) null);
        FirNameAwareOnlyCallablesScope firNameAwareOnlyCallablesScope = firContainingNamesAwareScopeDeclaredMemberScope.getHasDefinitelyNoStaticMembers() ? null : new FirNameAwareOnlyCallablesScope(new FirStaticScope(firContainingNamesAwareScopeDeclaredMemberScope));
        if (forBackend) {
            Iterator<T> it = FirDeclarationUtilKt.getSuperConeTypes(klass).iterator();
            do {
                if (!it.hasNext()) {
                    regularClassSymbol = null;
                    break;
                }
                regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default((ConeClassLikeType) it.next(), useSiteSession, (Function1) null, 2, (Object) null), useSiteSession);
                if (regularClassSymbol == null || regularClassSymbol.getClassKind() != ClassKind.CLASS) {
                    regularClassSymbol = null;
                }
            } while (regularClassSymbol == null);
            FirRegularClass firRegularClass = regularClassSymbol != null ? (FirRegularClass) regularClassSymbol.getFir() : null;
            if (firRegularClass != null && (firContainingNamesAwareScopeStaticScopeForBackend = FirScopeProviderKt.staticScopeForBackend(firRegularClass, useSiteSession, scopeSession)) != null) {
                return firNameAwareOnlyCallablesScope != null ? new FirNameAwareCompositeScope(CollectionsKt.listOf(new FirContainingNamesAwareScope[]{firNameAwareOnlyCallablesScope, firContainingNamesAwareScopeStaticScopeForBackend})) : firContainingNamesAwareScopeStaticScopeForBackend;
            }
        }
        return firNameAwareOnlyCallablesScope;
    }

    public final Function5<FirClass, FirContainingNamesAwareScope, FirSession, ScopeSession, FirResolvePhase, FirContainingNamesAwareScope> getDeclaredMemberScopeDecorator() {
        return this.declaredMemberScopeDecorator;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScopeProvider
    public FirContainingNamesAwareScope getNestedClassifierScope(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession) {
        klass.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        return FirDeclaredMemberScopeProviderKt.nestedClassifierScope(useSiteSession, klass);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScopeProvider
    public FirContainingNamesAwareScope getStaticCallableMemberScope(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession) {
        klass.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        return getStaticCallableMemberScopeImpl(klass, useSiteSession, scopeSession, false);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScopeProvider
    public FirContainingNamesAwareScope getStaticCallableMemberScopeForBackend(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession) {
        klass.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        return getStaticCallableMemberScopeImpl(klass, useSiteSession, scopeSession, true);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScopeProvider
    public FirScope getTypealiasConstructorScope(FirTypeAlias typeAlias, FirSession useSiteSession, ScopeSession scopeSession) {
        typeAlias.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        Pair pair = TuplesKt.to(useSiteSession, typeAlias.getSymbol());
        ScopeSessionKey<?, ?> scopeSessionKey = FirKotlinScopeProviderKt.TYPEALIAS_CONSTRUCTOR;
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(pair);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(pair, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        Object objInitialize = map2.get(scopeSessionKey);
        if (objInitialize == null) {
            objInitialize = TypeAliasConstructorsSubstitutingScope.INSTANCE.initialize(typeAlias.getSymbol(), useSiteSession, scopeSession);
            map2.put(scopeSessionKey, objInitialize);
        }
        if (objInitialize != null) {
            return (FirScope) objInitialize;
        }
        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.scopes.FirScope");
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00d4  */
    @Override // org.jetbrains.kotlin.fir.scopes.FirScopeProvider
    public FirTypeScope getUseSiteMemberScope(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession, FirResolvePhase memberRequiredPhase) {
        ScopeSession scopeSession2;
        FirContainingNamesAwareScope firClassAnySynthesizedMemberScope;
        ConeClassLikeType coneClassLikeType;
        FirTypeScope firTypeScopeScopeForSupertype;
        klass.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        if (memberRequiredPhase != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhaseWithCallableMembers(klass, memberRequiredPhase);
        }
        Pair pair = TuplesKt.to(useSiteSession, klass.getSymbol());
        ScopeSessionKey<?, ?> use_site = SupertypeUtilsKt.getUSE_SITE();
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(pair);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(pair, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        Object firClassUseSiteMemberScope = map2.get(use_site);
        if (firClassUseSiteMemberScope == null) {
            if (klass.getClassKind() != ClassKind.ENUM_ENTRY || !(CollectionsKt.singleOrNull(klass.getDeclarations()) instanceof FirPrimaryConstructor) || (coneClassLikeType = (ConeClassLikeType) CollectionsKt.singleOrNull(FirDeclarationUtilKt.getSuperConeTypes(klass))) == null || (firTypeScopeScopeForSupertype = FirKotlinScopeProviderKt.scopeForSupertype(coneClassLikeType, useSiteSession, scopeSession, klass, memberRequiredPhase)) == null) {
                FirContainingNamesAwareScope firDelegatedMemberScope = (FirContainingNamesAwareScope) this.declaredMemberScopeDecorator.invoke(klass, FirDeclaredMemberScopeProviderKt.declaredMemberScope(useSiteSession, klass, memberRequiredPhase), useSiteSession, scopeSession, memberRequiredPhase);
                List<FirField> delegateFields = FirDeclarationUtilKt.getDelegateFields(klass);
                if (delegateFields.isEmpty()) {
                    scopeSession2 = scopeSession;
                } else {
                    scopeSession2 = scopeSession;
                    firDelegatedMemberScope = new FirDelegatedMemberScope(useSiteSession, scopeSession, klass, firDelegatedMemberScope, delegateFields);
                }
                if (!(klass instanceof FirRegularClass) || klass.getStatus().isExpect()) {
                    firClassAnySynthesizedMemberScope = firDelegatedMemberScope;
                } else {
                    FirRegularClass firRegularClass = (FirRegularClass) klass;
                    if ((firRegularClass.getStatus().isData() || klass.getStatus().isInline() || klass.getStatus().isValue()) && !Intrinsics.areEqual(firRegularClass.getOrigin(), FirDeclarationOrigin.Library.INSTANCE)) {
                        firClassAnySynthesizedMemberScope = new FirClassAnySynthesizedMemberScope(useSiteSession, firDelegatedMemberScope, firRegularClass, scopeSession2);
                    } else {
                        firClassAnySynthesizedMemberScope = firDelegatedMemberScope;
                    }
                }
                List listLookupSuperTypes$default = SupertypeUtilsKt.lookupSuperTypes$default(klass, true, false, useSiteSession, true, null, 32, null);
                ArrayList arrayList = new ArrayList();
                Iterator it = listLookupSuperTypes$default.iterator();
                while (it.hasNext()) {
                    FirTypeScope firTypeScopeScopeForSupertype2 = FirKotlinScopeProviderKt.scopeForSupertype((ConeClassLikeType) it.next(), useSiteSession, scopeSession2, klass, memberRequiredPhase);
                    if (firTypeScopeScopeForSupertype2 != null) {
                        arrayList.add(firTypeScopeScopeForSupertype2);
                    }
                }
                firClassUseSiteMemberScope = new FirClassUseSiteMemberScope(klass, useSiteSession, arrayList, firClassAnySynthesizedMemberScope);
            } else {
                firClassUseSiteMemberScope = new FirTrivialEnumEntryScope(klass, firTypeScopeScopeForSupertype);
            }
            map2.put(use_site, firClassUseSiteMemberScope);
        }
        return (FirTypeScope) firClassUseSiteMemberScope;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FirKotlinScopeProvider(Function5<? super FirClass, ? super FirContainingNamesAwareScope, ? super FirSession, ? super ScopeSession, ? super FirResolvePhase, ? extends FirContainingNamesAwareScope> function5) {
        function5.getClass();
        this.declaredMemberScopeDecorator = function5;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FirKotlinScopeProvider() {
        Function5 function5 = null;
        this(function5, 1, function5);
    }
}
