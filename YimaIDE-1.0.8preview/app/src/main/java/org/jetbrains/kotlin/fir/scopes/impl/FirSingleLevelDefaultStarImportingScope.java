package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.declarations.builder.FirImportBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirResolvedImportBuilder;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirDefaultImportsProviderHolderKt;
import org.jetbrains.kotlin.fir.scopes.FirLookupDefaultStarImportsInSourcesSettingHolderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.ImportPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ$\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00160\u001aH\u0016J(\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0016\u0010\u0019\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d\u0012\u0004\u0012\u00020\u00160\u001aH\u0016J\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0017b\u0002\b J\u001c\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0006H\u0017b\u0002\b$R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirSingleLevelDefaultStarImportingScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractStarImportingScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultStarImportingScopeMarker;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "priority", "Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultImportPriority;", "additionalExcludedImportNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultImportPriority;Ljava/util/Set;)V", "getPriority", "()Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultImportPriority;", "starImports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "getStarImports", "()Ljava/util/List;", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "findEnumEntryWithoutResolution", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSingleLevelDefaultStarImportingScope extends FirAbstractStarImportingScope implements DefaultStarImportingScopeMarker {
    private final Set<FqName> additionalExcludedImportNames;
    private final DefaultImportPriority priority;
    private final List<FirResolvedImport> starImports;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirSingleLevelDefaultStarImportingScope(FirSession firSession, ScopeSession scopeSession, DefaultImportPriority defaultImportPriority, Set<FqName> set) {
        List<FirResolvedImport> listEmptyList;
        super(firSession, scopeSession, FirLookupDefaultStarImportsInSourcesSettingHolderKt.getLookupDefaultStarImportsInSources(firSession), SetsKt.plus(set, FirDefaultImportsProviderHolderKt.getDefaultImportsProvider(firSession).getExcludedImports()));
        firSession.getClass();
        scopeSession.getClass();
        defaultImportPriority.getClass();
        set.getClass();
        this.priority = defaultImportPriority;
        this.additionalExcludedImportNames = set;
        List<ImportPath> allDefaultImports = defaultImportPriority.getAllDefaultImports(FirDefaultImportsProviderHolderKt.getDefaultImportsProvider(firSession), LanguageVersionSettingsImpl.DEFAULT);
        if (allDefaultImports != null) {
            ArrayList<ImportPath> arrayList = new ArrayList();
            for (Object obj : allDefaultImports) {
                if (((ImportPath) obj).isAllUnder()) {
                    arrayList.add(obj);
                }
            }
            listEmptyList = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            for (ImportPath importPath : arrayList) {
                FirResolvedImportBuilder firResolvedImportBuilder = new FirResolvedImportBuilder();
                FirImportBuilder firImportBuilder = new FirImportBuilder();
                firImportBuilder.setImportedFqName(importPath.getFqName());
                firImportBuilder.setAllUnder(true);
                firResolvedImportBuilder.setDelegate(firImportBuilder.build());
                firResolvedImportBuilder.setPackageFqName(importPath.getFqName());
                listEmptyList.add(firResolvedImportBuilder.build());
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        this.starImports = listEmptyList;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractStarImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope
    @FirImplementationDetail
    public FirEnumEntrySymbol findEnumEntryWithoutResolution(Name name) {
        name.getClass();
        return null;
    }

    public final DefaultImportPriority getPriority() {
        return this.priority;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractStarImportingScope
    public List<FirResolvedImport> getStarImports() {
        return this.starImports;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractStarImportingScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (!name.isSpecial()) {
            String identifier = name.getIdentifier();
            identifier.getClass();
            if (identifier.length() <= 0) {
                return;
            }
        }
        for (FirResolvedImport firResolvedImport : getStarImports()) {
            if (!isExcluded(firResolvedImport, name)) {
                Iterator<FirNamedFunctionSymbol> it = getProvider().getTopLevelFunctionSymbols(firResolvedImport.getPackageFqName(), name).iterator();
                while (it.hasNext()) {
                    processor.invoke(it.next());
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractStarImportingScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (!name.isSpecial()) {
            String identifier = name.getIdentifier();
            identifier.getClass();
            if (identifier.length() <= 0) {
                return;
            }
        }
        for (FirResolvedImport firResolvedImport : getStarImports()) {
            if (!isExcluded(firResolvedImport, name)) {
                Iterator<FirPropertySymbol> it = getProvider().getTopLevelPropertySymbols(firResolvedImport.getPackageFqName(), name).iterator();
                while (it.hasNext()) {
                    processor.invoke(it.next());
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractStarImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractProviderBasedScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirSingleLevelDefaultStarImportingScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirSingleLevelDefaultStarImportingScope(newSession, newScopeSession, this.priority, this.additionalExcludedImportNames);
    }
}
