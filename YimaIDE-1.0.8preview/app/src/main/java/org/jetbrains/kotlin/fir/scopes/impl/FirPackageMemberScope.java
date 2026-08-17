package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ.\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\n2\u001c\u0010\u001c\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001a0\u001dH\u0016J$\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\n2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u001a0 H\u0016J(\u0010!\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\n2\u0016\u0010\u001c\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"\u0012\u0004\u0012\u00020\u001a0 H\u0016J\u001c\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020*H\u0017b\u0002\b+R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00150\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirPackageMemberScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "excludedNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;Ljava/util/Set;)V", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "classifierCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "functionCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "propertyCache", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processFunctionsByName", "Lkotlin/Function1;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "scopeOwnerLookupNames", Argument.Delimiters.none, "getScopeOwnerLookupNames", "()Ljava/util/List;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPackageMemberScope extends FirScope {
    private final Map<Name, FirClassifierSymbol<?>> classifierCache;
    private final Set<Name> excludedNames;
    private final FqName fqName;
    private final Map<Name, List<FirNamedFunctionSymbol>> functionCache;
    private final Map<Name, List<FirPropertySymbol>> propertyCache;
    private final List<String> scopeOwnerLookupNames;
    private final FirSession session;
    private final FirSymbolProvider symbolProvider;

    public FirPackageMemberScope(FqName fqName, FirSession firSession, FirSymbolProvider firSymbolProvider, Set<Name> set) {
        fqName.getClass();
        firSession.getClass();
        firSymbolProvider.getClass();
        set.getClass();
        this.fqName = fqName;
        this.session = firSession;
        this.symbolProvider = firSymbolProvider;
        this.excludedNames = set;
        this.classifierCache = new HashMap();
        this.functionCache = new HashMap();
        this.propertyCache = new HashMap();
        this.scopeOwnerLookupNames = new SmartList(fqName.asString());
    }

    public final FqName getFqName() {
        return this.fqName;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public List<String> getScopeOwnerLookupNames() {
        return this.scopeOwnerLookupNames;
    }

    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        String strAsString = name.asString();
        strAsString.getClass();
        if (strAsString.length() == 0 || this.excludedNames.contains(name)) {
            return;
        }
        Map<Name, FirClassifierSymbol<?>> map = this.classifierCache;
        FirClassifierSymbol<?> classLikeSymbolByClassId = map.get(name);
        if (classLikeSymbolByClassId == null) {
            classLikeSymbolByClassId = this.symbolProvider.getClassLikeSymbolByClassId(new ClassId(this.fqName, name));
            map.put(name, classLikeSymbolByClassId);
        }
        FirClassifierSymbol<?> firClassifierSymbol = classLikeSymbolByClassId;
        if (firClassifierSymbol != null) {
            processor.invoke(firClassifierSymbol, ConeSubstitutor.Empty.INSTANCE);
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (this.excludedNames.contains(name)) {
            return;
        }
        Map<Name, List<FirNamedFunctionSymbol>> map = this.functionCache;
        List<FirNamedFunctionSymbol> topLevelFunctionSymbols = map.get(name);
        if (topLevelFunctionSymbols == null) {
            topLevelFunctionSymbols = this.symbolProvider.getTopLevelFunctionSymbols(this.fqName, name);
            map.put(name, topLevelFunctionSymbols);
        }
        Iterator<FirNamedFunctionSymbol> it = topLevelFunctionSymbols.iterator();
        while (it.hasNext()) {
            processor.invoke(it.next());
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (this.excludedNames.contains(name)) {
            return;
        }
        Map<Name, List<FirPropertySymbol>> map = this.propertyCache;
        List<FirPropertySymbol> topLevelPropertySymbols = map.get(name);
        if (topLevelPropertySymbols == null) {
            topLevelPropertySymbols = this.symbolProvider.getTopLevelPropertySymbols(this.fqName, name);
            map.put(name, topLevelPropertySymbols);
        }
        Iterator<FirPropertySymbol> it = topLevelPropertySymbols.iterator();
        while (it.hasNext()) {
            processor.invoke(it.next());
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirPackageMemberScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirPackageMemberScope(this.fqName, newSession, null, this.excludedNames, 4, null);
    }

    public /* synthetic */ FirPackageMemberScope(FqName fqName, FirSession firSession, FirSymbolProvider firSymbolProvider, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fqName, firSession, (i & 4) != 0 ? FirSymbolProviderKt.getSymbolProvider(firSession) : firSymbolProvider, (i & 8) != 0 ? SetsKt.emptySet() : set);
    }
}
