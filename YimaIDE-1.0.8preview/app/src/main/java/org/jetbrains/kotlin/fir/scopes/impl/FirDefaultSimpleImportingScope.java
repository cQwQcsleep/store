package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.declarations.builder.FirImportBuilder;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.FirImportResolveTransformer;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirDefaultImportsProviderHolderKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.DefaultImportsProvider;
import org.jetbrains.kotlin.resolve.ImportPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001c\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0005H\u0017b\u0002\b\u001dR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00170\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirDefaultSimpleImportingScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractSimpleImportingScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "priority", "Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultImportPriority;", "excludedImportNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultImportPriority;Ljava/util/Set;)V", "getPriority", "()Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultImportPriority;", "resolve", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "importResolveTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirImportResolveTransformer;", "simpleImports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "getSimpleImports", "()Ljava/util/Map;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDefaultSimpleImportingScope extends FirAbstractSimpleImportingScope {
    private final Set<FqName> excludedImportNames;
    private final DefaultImportPriority priority;
    private final Map<Name, List<FirResolvedImport>> simpleImports;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDefaultSimpleImportingScope(FirSession firSession, ScopeSession scopeSession, DefaultImportPriority defaultImportPriority, Set<FqName> set) {
        Map mapEmptyMap;
        super(firSession, scopeSession);
        firSession.getClass();
        scopeSession.getClass();
        defaultImportPriority.getClass();
        set.getClass();
        this.priority = defaultImportPriority;
        this.excludedImportNames = set;
        FirImportResolveTransformer firImportResolveTransformer = new FirImportResolveTransformer(firSession);
        DefaultImportsProvider defaultImportsProvider = FirDefaultImportsProviderHolderKt.getDefaultImportsProvider(firSession);
        List<ImportPath> allDefaultImports = defaultImportPriority.getAllDefaultImports(defaultImportsProvider, LanguageVersionSettingsImpl.DEFAULT);
        if (allDefaultImports != null) {
            ArrayList<ImportPath> arrayList = new ArrayList();
            for (Object obj : allDefaultImports) {
                ImportPath importPath = (ImportPath) obj;
                if (!importPath.isAllUnder() && !this.excludedImportNames.contains(importPath.getFqName()) && !defaultImportsProvider.getExcludedImports().contains(importPath.getFqName())) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (ImportPath importPath2 : arrayList) {
                FirImportBuilder firImportBuilder = new FirImportBuilder();
                firImportBuilder.setImportedFqName(importPath2.getFqName());
                firImportBuilder.setAllUnder(false);
                FirResolvedImport firResolvedImportResolve = resolve(firImportBuilder.build(), firImportResolveTransformer);
                if (firResolvedImportResolve != null) {
                    arrayList2.add(firResolvedImportResolve);
                }
            }
            mapEmptyMap = new LinkedHashMap();
            for (Object obj2 : arrayList2) {
                Name importedName = ((FirResolvedImport) obj2).getImportedName();
                importedName.getClass();
                Object arrayList3 = mapEmptyMap.get(importedName);
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                    mapEmptyMap.put(importedName, arrayList3);
                }
                ((List) arrayList3).add(obj2);
            }
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        this.simpleImports = mapEmptyMap;
    }

    private final FirResolvedImport resolve(FirImport firImport, FirImportResolveTransformer firImportResolveTransformer) {
        FirImport firImportTransformImport = firImportResolveTransformer.transformImport(firImport, null);
        if (firImportTransformImport instanceof FirResolvedImport) {
            return (FirResolvedImport) firImportTransformImport;
        }
        return null;
    }

    public final DefaultImportPriority getPriority() {
        return this.priority;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractSimpleImportingScope
    public Map<Name, List<FirResolvedImport>> getSimpleImports() {
        return this.simpleImports;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractSimpleImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractProviderBasedScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirDefaultSimpleImportingScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirDefaultSimpleImportingScope(newSession, newScopeSession, this.priority, this.excludedImportNames);
    }
}
