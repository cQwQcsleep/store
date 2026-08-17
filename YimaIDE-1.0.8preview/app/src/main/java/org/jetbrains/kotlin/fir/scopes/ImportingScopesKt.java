package org.jetbrains.kotlin.fir.scopes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtension;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtensionKt;
import org.jetbrains.kotlin.fir.extensions.FirScriptResolutionConfigurationExtension;
import org.jetbrains.kotlin.fir.extensions.FirScriptResolutionConfigurationExtensionKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSessionKey;
import org.jetbrains.kotlin.fir.resolve.transformers.FirImportResolveTransformer;
import org.jetbrains.kotlin.fir.scopes.impl.DefaultImportPriority;
import org.jetbrains.kotlin.fir.scopes.impl.FirDefaultSimpleImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDefaultStarImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirExplicitSimpleImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirExplicitStarImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirPackageMemberScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirPackageMemberScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirSingleLevelDefaultStarImportingScope;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u001a:\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u0017H\u0000\u001a2\u0010\u001b\u001a\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u000f\u0018\u00010\u001c2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0002H\u0002\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\r0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"ALL_IMPORTS", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/scopes/ListStorageFirScope;", "DEFAULT_STAR_IMPORT", "Lorg/jetbrains/kotlin/fir/scopes/DefaultStarImportKey;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirSingleLevelDefaultStarImportingScope;", "DEFAULT_SIMPLE_IMPORT", "Lorg/jetbrains/kotlin/fir/scopes/DefaultSimpleImportKey;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDefaultSimpleImportingScope;", "DEFAULT_SCRIPT_STAR_IMPORT", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirExplicitStarImportingScope;", "DEFAULT_SCRIPT_SIMPLE_IMPORT", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirExplicitSimpleImportingScope;", "createImportingScopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "file", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "useCaching", Argument.Delimiters.none, "computeImportingScopes", "includeDefaultImports", "includePackageImport", "getDefaultImportsForScripting", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImportingScopesKt {
    private static final ScopeSessionKey<FirFile, ListStorageFirScope> ALL_IMPORTS = new ScopeSessionKey<FirFile, ListStorageFirScope>() { // from class: org.jetbrains.kotlin.fir.scopes.ImportingScopesKt$special$$inlined$scopeSessionKey$1
    };
    private static final ScopeSessionKey<DefaultStarImportKey, FirSingleLevelDefaultStarImportingScope> DEFAULT_STAR_IMPORT = new ScopeSessionKey<DefaultStarImportKey, FirSingleLevelDefaultStarImportingScope>() { // from class: org.jetbrains.kotlin.fir.scopes.ImportingScopesKt$special$$inlined$scopeSessionKey$2
    };
    private static final ScopeSessionKey<DefaultSimpleImportKey, FirDefaultSimpleImportingScope> DEFAULT_SIMPLE_IMPORT = new ScopeSessionKey<DefaultSimpleImportKey, FirDefaultSimpleImportingScope>() { // from class: org.jetbrains.kotlin.fir.scopes.ImportingScopesKt$special$$inlined$scopeSessionKey$3
    };
    private static final ScopeSessionKey<FirFile, FirExplicitStarImportingScope> DEFAULT_SCRIPT_STAR_IMPORT = new ScopeSessionKey<FirFile, FirExplicitStarImportingScope>() { // from class: org.jetbrains.kotlin.fir.scopes.ImportingScopesKt$special$$inlined$scopeSessionKey$4
    };
    private static final ScopeSessionKey<FirFile, FirExplicitSimpleImportingScope> DEFAULT_SCRIPT_SIMPLE_IMPORT = new ScopeSessionKey<FirFile, FirExplicitSimpleImportingScope>() { // from class: org.jetbrains.kotlin.fir.scopes.ImportingScopesKt$special$$inlined$scopeSessionKey$5
    };

    public static final List<FirScope> computeImportingScopes(FirFile firFile, FirSession firSession, ScopeSession scopeSession, boolean z, boolean z2) {
        FirSession firSession2;
        FirPackageMemberScope firPackageMemberScope;
        firFile.getClass();
        firSession.getClass();
        scopeSession.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firFile, FirResolvePhase.IMPORTS);
        List<FirImport> imports = firFile.getImports();
        ArrayList arrayList = new ArrayList();
        for (Object obj : imports) {
            if (((FirImport) obj).getAliasName() != null) {
                arrayList.add(obj);
            }
        }
        Set hashSet = new HashSet();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            FqName importedFqName = ((FirImport) it.next()).getImportedFqName();
            if (importedFqName != null) {
                hashSet.add(importedFqName);
            }
        }
        if (hashSet.isEmpty()) {
            hashSet = SetsKt.emptySet();
        }
        Set<FqName> set = hashSet;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (FqName fqName : set) {
            Name nameShortName = Intrinsics.areEqual(fqName.parent(), UtilsKt.getPackageFqName(firFile)) ? fqName.shortName() : null;
            if (nameShortName != null) {
                linkedHashSet.add(nameShortName);
            }
        }
        Pair<List<FirImport>, List<FirImport>> defaultImportsForScripting = getDefaultImportsForScripting(firSession, firFile);
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        if (z) {
            List list = listCreateListBuilder;
            DefaultImportPriority defaultImportPriority = DefaultImportPriority.HIGH;
            DefaultStarImportKey defaultStarImportKey = new DefaultStarImportKey(defaultImportPriority, set);
            ScopeSessionKey<DefaultStarImportKey, FirSingleLevelDefaultStarImportingScope> scopeSessionKey = DEFAULT_STAR_IMPORT;
            HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
            HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(defaultStarImportKey);
            if (map == null) {
                map = new HashMap<>();
                mapScopes.put(defaultStarImportKey, map);
            }
            HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
            Object firSingleLevelDefaultStarImportingScope = map2.get(scopeSessionKey);
            if (firSingleLevelDefaultStarImportingScope == null) {
                firSingleLevelDefaultStarImportingScope = new FirSingleLevelDefaultStarImportingScope(firSession, scopeSession, defaultImportPriority, set);
                map2.put(scopeSessionKey, firSingleLevelDefaultStarImportingScope);
            }
            FirSingleLevelDefaultStarImportingScope firSingleLevelDefaultStarImportingScope2 = (FirSingleLevelDefaultStarImportingScope) firSingleLevelDefaultStarImportingScope;
            DefaultImportPriority defaultImportPriority2 = DefaultImportPriority.LOW;
            DefaultStarImportKey defaultStarImportKey2 = new DefaultStarImportKey(defaultImportPriority2, set);
            HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes2 = scopeSession.scopes();
            HashMap<ScopeSessionKey<?, ?>, Object> map3 = mapScopes2.get(defaultStarImportKey2);
            if (map3 == null) {
                map3 = new HashMap<>();
                mapScopes2.put(defaultStarImportKey2, map3);
            }
            HashMap<ScopeSessionKey<?, ?>, Object> map4 = map3;
            Object firSingleLevelDefaultStarImportingScope3 = map4.get(scopeSessionKey);
            if (firSingleLevelDefaultStarImportingScope3 == null) {
                firSingleLevelDefaultStarImportingScope3 = new FirSingleLevelDefaultStarImportingScope(firSession, scopeSession, defaultImportPriority2, set);
                map4.put(scopeSessionKey, firSingleLevelDefaultStarImportingScope3);
            }
            list.add(new FirDefaultStarImportingScope(firSingleLevelDefaultStarImportingScope2, (FirSingleLevelDefaultStarImportingScope) firSingleLevelDefaultStarImportingScope3));
            if (defaultImportsForScripting != null) {
                ScopeSessionKey<FirFile, FirExplicitStarImportingScope> scopeSessionKey2 = DEFAULT_SCRIPT_STAR_IMPORT;
                HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes3 = scopeSession.scopes();
                HashMap<ScopeSessionKey<?, ?>, Object> map5 = mapScopes3.get(firFile);
                if (map5 == null) {
                    map5 = new HashMap<>();
                    mapScopes3.put(firFile, map5);
                }
                HashMap<ScopeSessionKey<?, ?>, Object> map6 = map5;
                Object firExplicitStarImportingScope = map6.get(scopeSessionKey2);
                if (firExplicitStarImportingScope == null) {
                    firExplicitStarImportingScope = new FirExplicitStarImportingScope((List<? extends FirImport>) defaultImportsForScripting.getFirst(), firSession, scopeSession, (Set<FqName>) set);
                    map6.put(scopeSessionKey2, firExplicitStarImportingScope);
                }
                list.add((FirExplicitStarImportingScope) firExplicitStarImportingScope);
            }
        }
        List list2 = listCreateListBuilder;
        list2.add(new FirExplicitStarImportingScope(firFile.getImports(), firSession, scopeSession, (Set<FqName>) set));
        if (z) {
            DefaultImportPriority defaultImportPriority3 = DefaultImportPriority.LOW;
            DefaultSimpleImportKey defaultSimpleImportKey = new DefaultSimpleImportKey(defaultImportPriority3, set);
            ScopeSessionKey<DefaultSimpleImportKey, FirDefaultSimpleImportingScope> scopeSessionKey3 = DEFAULT_SIMPLE_IMPORT;
            HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes4 = scopeSession.scopes();
            HashMap<ScopeSessionKey<?, ?>, Object> map7 = mapScopes4.get(defaultSimpleImportKey);
            if (map7 == null) {
                map7 = new HashMap<>();
                mapScopes4.put(defaultSimpleImportKey, map7);
            }
            HashMap<ScopeSessionKey<?, ?>, Object> map8 = map7;
            Object firDefaultSimpleImportingScope = map8.get(scopeSessionKey3);
            if (firDefaultSimpleImportingScope == null) {
                firDefaultSimpleImportingScope = new FirDefaultSimpleImportingScope(firSession, scopeSession, defaultImportPriority3, set);
                map8.put(scopeSessionKey3, firDefaultSimpleImportingScope);
            }
            list2.add((FirDefaultSimpleImportingScope) firDefaultSimpleImportingScope);
            DefaultImportPriority defaultImportPriority4 = DefaultImportPriority.HIGH;
            DefaultSimpleImportKey defaultSimpleImportKey2 = new DefaultSimpleImportKey(defaultImportPriority4, set);
            HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes5 = scopeSession.scopes();
            HashMap<ScopeSessionKey<?, ?>, Object> map9 = mapScopes5.get(defaultSimpleImportKey2);
            if (map9 == null) {
                map9 = new HashMap<>();
                mapScopes5.put(defaultSimpleImportKey2, map9);
            }
            HashMap<ScopeSessionKey<?, ?>, Object> map10 = map9;
            Object firDefaultSimpleImportingScope2 = map10.get(scopeSessionKey3);
            if (firDefaultSimpleImportingScope2 == null) {
                firDefaultSimpleImportingScope2 = new FirDefaultSimpleImportingScope(firSession, scopeSession, defaultImportPriority4, set);
                map10.put(scopeSessionKey3, firDefaultSimpleImportingScope2);
            }
            list2.add((FirDefaultSimpleImportingScope) firDefaultSimpleImportingScope2);
            if (defaultImportsForScripting != null) {
                ScopeSessionKey<FirFile, FirExplicitSimpleImportingScope> scopeSessionKey4 = DEFAULT_SCRIPT_SIMPLE_IMPORT;
                HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes6 = scopeSession.scopes();
                HashMap<ScopeSessionKey<?, ?>, Object> map11 = mapScopes6.get(firFile);
                if (map11 == null) {
                    map11 = new HashMap<>();
                    mapScopes6.put(firFile, map11);
                }
                HashMap<ScopeSessionKey<?, ?>, Object> map12 = map11;
                Object firExplicitSimpleImportingScope = map12.get(scopeSessionKey4);
                if (firExplicitSimpleImportingScope == null) {
                    firExplicitSimpleImportingScope = new FirExplicitSimpleImportingScope((List<? extends FirImport>) defaultImportsForScripting.getSecond(), firSession, scopeSession);
                    map12.put(scopeSessionKey4, firExplicitSimpleImportingScope);
                }
                list2.add((FirExplicitSimpleImportingScope) firExplicitSimpleImportingScope);
            }
        }
        if (z2) {
            if (linkedHashSet.isEmpty()) {
                Pair pair = TuplesKt.to(UtilsKt.getPackageFqName(firFile), firSession);
                ScopeSessionKey<?, ?> package_member = FirPackageMemberScopeKt.getPACKAGE_MEMBER();
                HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes7 = scopeSession.scopes();
                HashMap<ScopeSessionKey<?, ?>, Object> map13 = mapScopes7.get(pair);
                if (map13 == null) {
                    map13 = new HashMap<>();
                    mapScopes7.put(pair, map13);
                }
                HashMap<ScopeSessionKey<?, ?>, Object> map14 = map13;
                Object obj2 = map14.get(package_member);
                if (obj2 == null) {
                    firSession2 = firSession;
                    FirPackageMemberScope firPackageMemberScope2 = new FirPackageMemberScope(UtilsKt.getPackageFqName(firFile), firSession2, null, SetsKt.emptySet(), 4, null);
                    map14.put(package_member, firPackageMemberScope2);
                    obj2 = firPackageMemberScope2;
                } else {
                    firSession2 = firSession;
                }
                firPackageMemberScope = (FirPackageMemberScope) obj2;
            } else {
                firSession2 = firSession;
                firPackageMemberScope = new FirPackageMemberScope(UtilsKt.getPackageFqName(firFile), firSession2, null, linkedHashSet, 4, null);
            }
            list2.add(firPackageMemberScope);
        } else {
            firSession2 = firSession;
        }
        list2.add(new FirExplicitSimpleImportingScope(firFile.getImports(), firSession2, scopeSession));
        return CollectionsKt.build(listCreateListBuilder);
    }

    public static /* synthetic */ List computeImportingScopes$default(FirFile firFile, FirSession firSession, ScopeSession scopeSession, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        if ((i & 16) != 0) {
            z2 = true;
        }
        return computeImportingScopes(firFile, firSession, scopeSession, z, z2);
    }

    public static final List<FirScope> createImportingScopes(FirFile firFile, FirSession firSession, ScopeSession scopeSession, boolean z) {
        firFile.getClass();
        firSession.getClass();
        scopeSession.getClass();
        if (!z) {
            return computeImportingScopes$default(firFile, firSession, scopeSession, false, false, 24, null);
        }
        ScopeSessionKey<FirFile, ListStorageFirScope> scopeSessionKey = ALL_IMPORTS;
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(firFile);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(firFile, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        Object listStorageFirScope = map2.get(scopeSessionKey);
        if (listStorageFirScope == null) {
            listStorageFirScope = new ListStorageFirScope(computeImportingScopes$default(firFile, firSession, scopeSession, false, false, 24, null));
            map2.put(scopeSessionKey, listStorageFirScope);
        }
        return ((ListStorageFirScope) listStorageFirScope).getResult();
    }

    public static /* synthetic */ List createImportingScopes$default(FirFile firFile, FirSession firSession, ScopeSession scopeSession, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return createImportingScopes(firFile, firSession, scopeSession, z);
    }

    private static final Pair<List<FirImport>, List<FirImport>> getDefaultImportsForScripting(FirSession firSession, FirFile firFile) {
        FirDeclaration firDeclaration = (FirDeclaration) CollectionsKt.firstOrNull(firFile.getDeclarations());
        if (firDeclaration instanceof FirScript) {
            List<FirScriptResolutionConfigurationExtension> firScriptResolutionConfigurators = FirScriptResolutionConfigurationExtensionKt.getFirScriptResolutionConfigurators(FirExtensionServiceKt.getExtensionService(firSession));
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = firScriptResolutionConfigurators.iterator();
            while (it.hasNext()) {
                List<FirImport> scriptDefaultImports = ((FirScriptResolutionConfigurationExtension) it.next()).getScriptDefaultImports((FirScript) firDeclaration);
                if (scriptDefaultImports == null) {
                    scriptDefaultImports = CollectionsKt.emptyList();
                }
                CollectionsKt.addAll(arrayList, scriptDefaultImports);
            }
            return getDefaultImportsForScripting$transformImports(arrayList, firSession);
        }
        List<FirImport> listEmptyList = null;
        if (!(firDeclaration instanceof FirReplSnippet)) {
            return null;
        }
        FirReplSnippetResolveExtension replSnippetResolveExtension = FirReplSnippetResolveExtensionKt.getReplSnippetResolveExtension(firSession);
        if (replSnippetResolveExtension != null) {
            KtSourceFile sourceFile = firFile.getSourceFile();
            sourceFile.getClass();
            listEmptyList = replSnippetResolveExtension.getSnippetDefaultImports(sourceFile, (FirReplSnippet) firDeclaration);
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        return getDefaultImportsForScripting$transformImports(listEmptyList, firSession);
    }

    private static final Pair<List<FirImport>, List<FirImport>> getDefaultImportsForScripting$transformImports(List<? extends FirImport> list, FirSession firSession) {
        FirImportResolveTransformer firImportResolveTransformer = new FirImportResolveTransformer(firSession);
        List<? extends FirImport> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (FirImport firImport : list2) {
            FirImport firImportTransformImport = firImportResolveTransformer.transformImport(firImport, null);
            FirResolvedImport firResolvedImport = firImportTransformImport instanceof FirResolvedImport ? (FirResolvedImport) firImportTransformImport : null;
            if (firResolvedImport != null) {
                firImport = firResolvedImport;
            }
            arrayList.add(firImport);
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList) {
            if (((FirImport) obj).getIsAllUnder()) {
                arrayList2.add(obj);
            } else {
                arrayList3.add(obj);
            }
        }
        return new Pair<>(arrayList2, arrayList3);
    }
}
