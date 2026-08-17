package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassDeclaredMemberScopeImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00015B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ$\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001a0\u001dH\u0016J\u001c\u0010\u001f\u001a\u00020\u001a2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u001a0\u001dH\u0016J(\u0010!\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0016\u0010\u001c\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"\u0012\u0004\u0012\u00020\u001a0\u001dH\u0016J5\u0010#\u001a\u00020\u001a\"\u000e\b\u0000\u0010$\u0018\u0001*\u0006\u0012\u0002\b\u00030%2\u0006\u0010\u001b\u001a\u00020\b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u00020\u001a0\u001dH\u0082\bJ.\u0010&\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u001c\u0010\u001c\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030(\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u001a0'H\u0016J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\b0+H\u0016J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0+H\u0016J\u001c\u00100\u001a\u00020\u00002\u0006\u00101\u001a\u00020\u00032\u0006\u00102\u001a\u000203H\u0017b\u0002\b4R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0015\u001a\u00020\u0016*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010-\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassDeclaredMemberScopeImpl;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassDeclaredMemberScope;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "existingNamesForLazyNestedClassifierScope", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Ljava/util/List;)V", "getUseSiteSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "nestedClassifierScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "callablesIndex", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassDeclaredMemberScopeImpl$CallablesIndex;", "getCallablesIndex", "()Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassDeclaredMemberScopeImpl$CallablesIndex;", "callablesIndex$delegate", "Lkotlin/Lazy;", "supportsEnumEntries", Argument.Delimiters.none, "getSupportsEnumEntries", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Z", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processCallables", "D", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processClassifiersByNameWithSubstitution", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "getCallableNames", Argument.Delimiters.none, "getClassifierNames", "hasDefinitelyNoStaticMembers", "getHasDefinitelyNoStaticMembers", "()Z", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "CallablesIndex", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirClassDeclaredMemberScopeImpl extends FirClassDeclaredMemberScope {

    /* JADX INFO: renamed from: callablesIndex$delegate, reason: from kotlin metadata */
    private final Lazy callablesIndex;
    private final List<Name> existingNamesForLazyNestedClassifierScope;
    private final FirClass klass;
    private final FirContainingNamesAwareScope nestedClassifierScope;
    private final FirSession useSiteSession;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B-\u0012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00050\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR'\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassDeclaredMemberScopeImpl$CallablesIndex;", Argument.Delimiters.none, "callablesByName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "hasStaticMembers", Argument.Delimiters.none, "<init>", "(Ljava/util/Map;Z)V", "getCallablesByName", "()Ljava/util/Map;", "getHasStaticMembers", "()Z", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CallablesIndex {
        private final Map<Name, List<FirCallableSymbol<?>>> callablesByName;
        private final boolean hasStaticMembers;

        /* JADX WARN: Multi-variable type inference failed */
        public CallablesIndex(Map<Name, ? extends List<? extends FirCallableSymbol<?>>> map, boolean z) {
            map.getClass();
            this.callablesByName = map;
            this.hasStaticMembers = z;
        }

        public final Map<Name, List<FirCallableSymbol<?>>> getCallablesByName() {
            return this.callablesByName;
        }

        public final boolean getHasStaticMembers() {
            return this.hasStaticMembers;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirClassDeclaredMemberScopeImpl(FirSession firSession, FirClass firClass, List<Name> list) {
        super(FirDeclarationUtilKt.getClassId(firClass));
        firSession.getClass();
        firClass.getClass();
        this.useSiteSession = firSession;
        this.klass = firClass;
        this.existingNamesForLazyNestedClassifierScope = list;
        this.nestedClassifierScope = list != null ? FirDeclaredMemberScopeProviderKt.lazyNestedClassifierScope(firSession, firClass.getSymbol().getClassId(), list) : FirDeclaredMemberScopeProviderKt.nestedClassifierScope(firSession, firClass);
        this.callablesIndex = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: hz4
            public final Object invoke() {
                return FirClassDeclaredMemberScopeImpl.b(this.b);
            }
        });
    }

    public static CallablesIndex b(FirClassDeclaredMemberScopeImpl firClassDeclaredMemberScopeImpl) {
        Name name;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (true) {
            boolean z = false;
            for (FirDeclaration firDeclaration : firClassDeclaredMemberScopeImpl.klass.getDeclarations()) {
                if (firDeclaration instanceof FirCallableDeclaration) {
                    FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firDeclaration;
                    if (firCallableDeclaration instanceof FirConstructor) {
                        name = SpecialNames.INIT;
                    } else if (firCallableDeclaration instanceof FirVariable) {
                        if (firDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic) {
                            continue;
                        } else {
                            FirVariable firVariable = (FirVariable) firDeclaration;
                            if (!UtilsKt.isEnumEntries(firVariable, firClassDeclaredMemberScopeImpl.klass) || firClassDeclaredMemberScopeImpl.getSupportsEnumEntries(firClassDeclaredMemberScopeImpl.klass)) {
                                name = firVariable.getName();
                            }
                        }
                    } else if (firCallableDeclaration instanceof FirNamedFunction) {
                        name = ((FirNamedFunction) firDeclaration).getName();
                    } else {
                        continue;
                    }
                    Object arrayList = linkedHashMap.get(name);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        linkedHashMap.put(name, arrayList);
                    }
                    ((Collection) arrayList).add(firCallableDeclaration.getSymbol());
                    if (z || ((FirMemberDeclaration) firDeclaration).getStatus().isStatic()) {
                        z = true;
                    }
                }
            }
            return new CallablesIndex(linkedHashMap, z);
        }
    }

    private final CallablesIndex getCallablesIndex() {
        return (CallablesIndex) this.callablesIndex.getValue();
    }

    private final boolean getSupportsEnumEntries(FirClass firClass) {
        return FirEnumEntriesSupportKt.getEnumEntriesSupport(this.useSiteSession).canSynthesizeEnumEntriesFor(firClass);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return getCallablesIndex().getCallablesByName().keySet();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        FirContainingNamesAwareScope firContainingNamesAwareScope = this.nestedClassifierScope;
        Set<Name> classifierNames = firContainingNamesAwareScope != null ? firContainingNamesAwareScope.getClassifierNames() : null;
        return classifierNames == null ? SetsKt.emptySet() : classifierNames;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public boolean getHasDefinitelyNoStaticMembers() {
        return !getCallablesIndex().getHasStaticMembers();
    }

    public final FirSession getUseSiteSession() {
        return this.useSiteSession;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        FirContainingNamesAwareScope firContainingNamesAwareScope = this.nestedClassifierScope;
        if (firContainingNamesAwareScope != null) {
            firContainingNamesAwareScope.processClassifiersByNameWithSubstitution(name, processor);
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        List<FirCallableSymbol<?>> listEmptyList = getCallablesIndex().getCallablesByName().get(SpecialNames.INIT);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        for (FirCallableSymbol<?> firCallableSymbol : listEmptyList) {
            if (firCallableSymbol instanceof FirConstructorSymbol) {
                processor.invoke(firCallableSymbol);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (Intrinsics.areEqual(name, SpecialNames.INIT)) {
            return;
        }
        List<FirCallableSymbol<?>> listEmptyList = getCallablesIndex().getCallablesByName().get(name);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        for (FirCallableSymbol<?> firCallableSymbol : listEmptyList) {
            if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
                processor.invoke(firCallableSymbol);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        List<FirCallableSymbol<?>> listEmptyList = getCallablesIndex().getCallablesByName().get(name);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        for (FirCallableSymbol<?> firCallableSymbol : listEmptyList) {
            if (firCallableSymbol instanceof FirVariableSymbol) {
                processor.invoke(firCallableSymbol);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirClassDeclaredMemberScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirClassDeclaredMemberScopeImpl withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirClassDeclaredMemberScopeImpl(newSession, this.klass, this.existingNamesForLazyNestedClassifierScope);
    }
}
