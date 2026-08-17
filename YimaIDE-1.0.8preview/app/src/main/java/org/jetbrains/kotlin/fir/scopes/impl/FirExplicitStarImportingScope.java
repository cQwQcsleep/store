package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.impl.FirExplicitStarImportingScope;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B5\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rB5\b\u0016\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\u0010J\u001c\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0005H\u0017b\u0002\b\u001bR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0015\u0010\u0012¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirExplicitStarImportingScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractStarImportingScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "starImports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "excludedImportNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Ljava/util/List;Ljava/util/Set;)V", "imports", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Ljava/util/Set;)V", "getStarImports", "()Ljava/util/List;", "scopeOwnerLookupNames", Argument.Delimiters.none, "getScopeOwnerLookupNames", "scopeOwnerLookupNames$delegate", "Lkotlin/Lazy;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirExplicitStarImportingScope extends FirAbstractStarImportingScope {

    /* JADX INFO: renamed from: scopeOwnerLookupNames$delegate, reason: from kotlin metadata */
    private final Lazy scopeOwnerLookupNames;
    private final List<FirResolvedImport> starImports;

    public FirExplicitStarImportingScope(List<? extends FirImport> list, FirSession firSession, ScopeSession scopeSession, Set<FqName> set) {
        list.getClass();
        firSession.getClass();
        scopeSession.getClass();
        set.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof FirResolvedImport) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (((FirResolvedImport) obj2).getIsAllUnder()) {
                arrayList2.add(obj2);
            }
        }
        this(firSession, scopeSession, arrayList2, set);
    }

    public static List c(FirExplicitStarImportingScope firExplicitStarImportingScope) {
        List<FirResolvedImport> starImports = firExplicitStarImportingScope.getStarImports();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = starImports.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((FirResolvedImport) it.next()).getPackageFqName().asString());
        }
        return CollectionsKt.toList(linkedHashSet);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public List<String> getScopeOwnerLookupNames() {
        return (List) this.scopeOwnerLookupNames.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractStarImportingScope
    public List<FirResolvedImport> getStarImports() {
        return this.starImports;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractStarImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractProviderBasedScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirExplicitStarImportingScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirExplicitStarImportingScope(newSession, newScopeSession, getStarImports(), getExcludedImportNames());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FirExplicitStarImportingScope(FirSession firSession, ScopeSession scopeSession, List<? extends FirResolvedImport> list, Set<FqName> set) {
        super(firSession, scopeSession, true, set);
        this.starImports = list;
        this.scopeOwnerLookupNames = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: l65
            public final Object invoke() {
                return FirExplicitStarImportingScope.c(this.b);
            }
        });
    }
}
