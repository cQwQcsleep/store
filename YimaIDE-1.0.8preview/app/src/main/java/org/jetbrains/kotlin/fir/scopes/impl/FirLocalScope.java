package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.util.PersistentMultimap;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BU\b\u0002\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eB\u0011\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u0010J\u0016\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\fJ\u0016\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000f\u001a\u00020\fJ\u0016\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\fJ\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u000f\u001a\u00020\fJ$\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00042\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020%0(H\u0016J(\u0010)\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00042\u0016\u0010'\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020%0(H\u0016J.\u0010*\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00042\u001c\u0010'\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030,\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020%0+H\u0016J\u0010\u0010.\u001a\u00020/2\u0006\u0010&\u001a\u00020\u0004H\u0016J\u000e\u00100\u001a\b\u0012\u0004\u0012\u00020\u000401H\u0016J\u000e\u00102\u001a\b\u0012\u0004\u0012\u00020\u000401H\u0016J\u001e\u00103\u001a\u0004\u0018\u00010\u00002\u0006\u00104\u001a\u00020\f2\u0006\u00105\u001a\u000206H\u0017b\u0002\b7R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u00068"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "properties", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "functions", "Lorg/jetbrains/kotlin/fir/util/PersistentMultimap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "classLikeSymbols", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;Lorg/jetbrains/kotlin/fir/util/PersistentMultimap;Lkotlinx/collections/immutable/PersistentMap;Lorg/jetbrains/kotlin/fir/FirSession;)V", "session", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getProperties", "()Lkotlinx/collections/immutable/PersistentMap;", "getFunctions", "()Lorg/jetbrains/kotlin/fir/util/PersistentMultimap;", "getClassLikeSymbols", "getUseSiteSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "storeClassOrTypeAlias", "classLikeDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "storeFunction", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "storeVariable", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "storeBackingField", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function1;", "processPropertiesByName", "processClassifiersByNameWithSubstitution", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "mayContainName", Argument.Delimiters.none, "getCallableNames", Argument.Delimiters.none, "getClassifierNames", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLocalScope extends FirContainingNamesAwareScope {
    private final PersistentMap<Name, FirClassLikeSymbol<?>> classLikeSymbols;
    private final PersistentMultimap<Name, FirNamedFunctionSymbol> functions;
    private final PersistentMap<Name, FirVariableSymbol<?>> properties;
    private final FirSession useSiteSession;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirLocalScope(FirSession firSession) {
        this(ExtensionsKt.persistentMapOf(), new PersistentMultimap(), ExtensionsKt.persistentMapOf(), firSession);
        firSession.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return SetsKt.plus(this.properties.keySet(), this.functions.getKeys());
    }

    public final PersistentMap<Name, FirClassLikeSymbol<?>> getClassLikeSymbols() {
        return this.classLikeSymbols;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return this.classLikeSymbols.keySet();
    }

    public final PersistentMultimap<Name, FirNamedFunctionSymbol> getFunctions() {
        return this.functions;
    }

    public final PersistentMap<Name, FirVariableSymbol<?>> getProperties() {
        return this.properties;
    }

    public final FirSession getUseSiteSession() {
        return this.useSiteSession;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public boolean mayContainName(Name name) {
        name.getClass();
        return this.properties.containsKey(name) || !this.functions.get(name).isEmpty() || this.classLikeSymbols.containsKey(name);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) this.classLikeSymbols.get(name);
        if (firClassLikeSymbol != null) {
            List<FirTypeParameterSymbol> typeParameterSymbols = firClassLikeSymbol.getTypeParameterSymbols();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(typeParameterSymbols, 10)), 16));
            for (Object obj : typeParameterSymbols) {
                linkedHashMap.put(obj, FirNestedClassifierScopeKt.toConeType((FirTypeParameterSymbol) obj));
            }
            processor.invoke(firClassLikeSymbol, ConeSubstitutorByMapKt.substitutorByMap(linkedHashMap, this.useSiteSession, true));
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        Iterator<FirNamedFunctionSymbol> it = this.functions.get(name).iterator();
        while (it.hasNext()) {
            processor.invoke(it.next());
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        FirVariableSymbol firVariableSymbol = (FirVariableSymbol) this.properties.get(name);
        if (firVariableSymbol != null) {
            processor.invoke(firVariableSymbol);
        }
    }

    public final FirLocalScope storeBackingField(FirProperty property, FirSession session) {
        FirBackingFieldSymbol symbol;
        property.getClass();
        session.getClass();
        FirBackingField backingField = property.getBackingField();
        PersistentMap<Name, FirVariableSymbol<?>> persistentMapPut = (backingField == null || (symbol = backingField.getSymbol()) == null) ? null : this.properties.put(StandardNames.BACKING_FIELD, symbol);
        if (persistentMapPut == null) {
            persistentMapPut = this.properties;
        }
        return new FirLocalScope(persistentMapPut, this.functions, this.classLikeSymbols, session);
    }

    public final FirLocalScope storeClassOrTypeAlias(FirClassLikeDeclaration classLikeDeclaration, FirSession session) {
        Name name;
        classLikeDeclaration.getClass();
        session.getClass();
        if (classLikeDeclaration instanceof FirRegularClass) {
            name = ((FirRegularClass) classLikeDeclaration).getName();
        } else {
            if (!(classLikeDeclaration instanceof FirTypeAlias)) {
                AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                wq6.a();
                return null;
            }
            name = ((FirTypeAlias) classLikeDeclaration).getName();
        }
        return new FirLocalScope(this.properties, this.functions, this.classLikeSymbols.put(name, classLikeDeclaration.getSymbol()), session);
    }

    public final FirLocalScope storeFunction(FirNamedFunction function, FirSession session) {
        function.getClass();
        session.getClass();
        return new FirLocalScope(this.properties, this.functions.put(function.getName(), function.getSymbol()), this.classLikeSymbols, session);
    }

    public final FirLocalScope storeVariable(FirVariable variable, FirSession session) {
        variable.getClass();
        session.getClass();
        return new FirLocalScope(this.properties.put(variable.getName(), variable.getSymbol()), this.functions, this.classLikeSymbols, session);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirLocalScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FirLocalScope(PersistentMap<Name, ? extends FirVariableSymbol<?>> persistentMap, PersistentMultimap<Name, FirNamedFunctionSymbol> persistentMultimap, PersistentMap<Name, ? extends FirClassLikeSymbol<?>> persistentMap2, FirSession firSession) {
        this.properties = persistentMap;
        this.functions = persistentMultimap;
        this.classLikeSymbols = persistentMap2;
        this.useSiteSession = firSession;
    }
}
