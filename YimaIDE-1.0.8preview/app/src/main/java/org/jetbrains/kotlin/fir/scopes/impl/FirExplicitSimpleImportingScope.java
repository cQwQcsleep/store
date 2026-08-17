package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B3\b\u0002\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fB'\b\u0016\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\u000fJ\u001c\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\nH\u0017b\u0002\b\u0015R&\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirExplicitSimpleImportingScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractSimpleImportingScope;", "simpleImports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Ljava/util/Map;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "imports", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "getSimpleImports", "()Ljava/util/Map;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExplicitSimpleImportingScope extends FirAbstractSimpleImportingScope {
    private final Map<Name, List<FirResolvedImport>> simpleImports;

    public FirExplicitSimpleImportingScope(List<? extends FirImport> list, FirSession firSession, ScopeSession scopeSession) {
        list.getClass();
        firSession.getClass();
        scopeSession.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof FirResolvedImport) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            FirResolvedImport firResolvedImport = (FirResolvedImport) obj2;
            if (!firResolvedImport.getIsAllUnder() && firResolvedImport.getImportedName() != null) {
                arrayList2.add(obj2);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj3 : arrayList2) {
            FirResolvedImport firResolvedImport2 = (FirResolvedImport) obj3;
            Name aliasName = firResolvedImport2.getAliasName();
            if (aliasName == null) {
                aliasName = firResolvedImport2.getImportedName();
                aliasName.getClass();
            }
            Object arrayList3 = linkedHashMap.get(aliasName);
            if (arrayList3 == null) {
                arrayList3 = new ArrayList();
                linkedHashMap.put(aliasName, arrayList3);
            }
            ((List) arrayList3).add(obj3);
        }
        this(linkedHashMap, firSession, scopeSession);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractSimpleImportingScope
    public Map<Name, List<FirResolvedImport>> getSimpleImports() {
        return this.simpleImports;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractSimpleImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractProviderBasedScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirExplicitSimpleImportingScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirExplicitSimpleImportingScope(getSimpleImports(), newSession, newScopeSession);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FirExplicitSimpleImportingScope(Map<Name, ? extends List<? extends FirResolvedImport>> map, FirSession firSession, ScopeSession scopeSession) {
        super(firSession, scopeSession);
        this.simpleImports = map;
    }
}
