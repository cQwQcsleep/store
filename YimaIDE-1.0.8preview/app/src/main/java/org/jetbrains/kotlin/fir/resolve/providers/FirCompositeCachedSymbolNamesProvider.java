package org.jetbrains.kotlin.fir.resolve.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirSyntheticFunctionInterfaceProviderBase;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000  2\u00020\u0001:\u0001 B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0016J\u0010\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0016J\u0018\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0016J\u0018\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0017\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0014\u0010\u001b\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/FirCompositeCachedSymbolNamesProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirCachedSymbolNamesProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "providers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/List;)V", "getProviders", "()Ljava/util/List;", "computePackageNames", Argument.Delimiters.none, Argument.Delimiters.none, "hasSpecificClassifierPackageNamesComputation", Argument.Delimiters.none, "getHasSpecificClassifierPackageNamesComputation", "()Z", "computePackageNamesWithTopLevelClassifiers", "computeTopLevelClassifierNames", "Lorg/jetbrains/kotlin/name/Name;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "hasSpecificCallablePackageNamesComputation", "getHasSpecificCallablePackageNamesComputation", "computePackageNamesWithTopLevelCallables", "computeTopLevelCallableNames", "mayHaveSyntheticFunctionTypes", "getMayHaveSyntheticFunctionTypes", "mayHaveSyntheticFunctionType", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirCompositeCachedSymbolNamesProvider extends FirCachedSymbolNamesProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean hasSpecificCallablePackageNamesComputation;
    private final boolean hasSpecificClassifierPackageNamesComputation;
    private final boolean mayHaveSyntheticFunctionTypes;
    private final List<FirSymbolNamesProvider> providers;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FirCompositeCachedSymbolNamesProvider(FirSession firSession, List<? extends FirSymbolNamesProvider> list) {
        boolean z;
        boolean z2;
        super(firSession);
        firSession.getClass();
        list.getClass();
        this.providers = list;
        List<? extends FirSymbolNamesProvider> list2 = list;
        boolean z3 = true;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (((FirSymbolNamesProvider) it.next()).getHasSpecificClassifierPackageNamesComputation()) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        this.hasSpecificClassifierPackageNamesComputation = z;
        List<FirSymbolNamesProvider> list3 = this.providers;
        if (!(list3 instanceof Collection) || !list3.isEmpty()) {
            Iterator<T> it2 = list3.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                } else if (((FirSymbolNamesProvider) it2.next()).getHasSpecificCallablePackageNamesComputation()) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
            break;
        }
        this.hasSpecificCallablePackageNamesComputation = z2;
        List<FirSymbolNamesProvider> list4 = this.providers;
        if ((list4 instanceof Collection) && list4.isEmpty()) {
            z3 = false;
        } else {
            Iterator<T> it3 = list4.iterator();
            while (it3.hasNext()) {
                if (((FirSymbolNamesProvider) it3.next()).getMayHaveSyntheticFunctionTypes()) {
                }
            }
            z3 = false;
        }
        this.mayHaveSyntheticFunctionTypes = z3;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
    public Set<String> computePackageNames() {
        List<FirSymbolNamesProvider> list = this.providers;
        Collection linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Set<String> packageNames = ((FirSymbolNamesProvider) it.next()).getPackageNames();
            if (packageNames == null) {
                return null;
            }
            CollectionsKt.addAll(linkedHashSet, packageNames);
        }
        if (linkedHashSet.isEmpty()) {
            linkedHashSet = SetsKt.emptySet();
        }
        return (Set) linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
    public Set<String> computePackageNamesWithTopLevelCallables() {
        List<FirSymbolNamesProvider> list = this.providers;
        Collection linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Set<String> packageNamesWithTopLevelCallables = ((FirSymbolNamesProvider) it.next()).getPackageNamesWithTopLevelCallables();
            if (packageNamesWithTopLevelCallables == null) {
                return null;
            }
            CollectionsKt.addAll(linkedHashSet, packageNamesWithTopLevelCallables);
        }
        if (linkedHashSet.isEmpty()) {
            linkedHashSet = SetsKt.emptySet();
        }
        return (Set) linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
    public Set<String> computePackageNamesWithTopLevelClassifiers() {
        List<FirSymbolNamesProvider> list = this.providers;
        Collection linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Set<String> packageNamesWithTopLevelClassifiers = ((FirSymbolNamesProvider) it.next()).getPackageNamesWithTopLevelClassifiers();
            if (packageNamesWithTopLevelClassifiers == null) {
                return null;
            }
            CollectionsKt.addAll(linkedHashSet, packageNamesWithTopLevelClassifiers);
        }
        if (linkedHashSet.isEmpty()) {
            linkedHashSet = SetsKt.emptySet();
        }
        return (Set) linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
    public Set<Name> computeTopLevelCallableNames(FqName packageFqName) {
        packageFqName.getClass();
        List<FirSymbolNamesProvider> list = this.providers;
        Collection linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Set<Name> topLevelCallableNamesInPackage = ((FirSymbolNamesProvider) it.next()).getTopLevelCallableNamesInPackage(packageFqName);
            if (topLevelCallableNamesInPackage == null) {
                return null;
            }
            CollectionsKt.addAll(linkedHashSet, topLevelCallableNamesInPackage);
        }
        if (linkedHashSet.isEmpty()) {
            linkedHashSet = SetsKt.emptySet();
        }
        return (Set) linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
    public Set<Name> computeTopLevelClassifierNames(FqName packageFqName) {
        packageFqName.getClass();
        List<FirSymbolNamesProvider> list = this.providers;
        Collection linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Set<Name> topLevelClassifierNamesInPackage = ((FirSymbolNamesProvider) it.next()).getTopLevelClassifierNamesInPackage(packageFqName);
            if (topLevelClassifierNamesInPackage == null) {
                return null;
            }
            CollectionsKt.addAll(linkedHashSet, topLevelClassifierNamesInPackage);
        }
        if (linkedHashSet.isEmpty()) {
            linkedHashSet = SetsKt.emptySet();
        }
        return (Set) linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public boolean getHasSpecificCallablePackageNamesComputation() {
        return this.hasSpecificCallablePackageNamesComputation;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public boolean getHasSpecificClassifierPackageNamesComputation() {
        return this.hasSpecificClassifierPackageNamesComputation;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public boolean getMayHaveSyntheticFunctionTypes() {
        return this.mayHaveSyntheticFunctionTypes;
    }

    public final List<FirSymbolNamesProvider> getProviders() {
        return this.providers;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public boolean mayHaveSyntheticFunctionType(ClassId classId) {
        classId.getClass();
        if (!FirSyntheticFunctionInterfaceProviderBase.INSTANCE.mayBeSyntheticFunctionClassName(classId)) {
            return false;
        }
        List<FirSymbolNamesProvider> list = this.providers;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((FirSymbolNamesProvider) it.next()).mayHaveSyntheticFunctionType(classId)) {
                return true;
            }
        }
        return false;
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tJ\u001c\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u000b0\t¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/FirCompositeCachedSymbolNamesProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "providers", Argument.Delimiters.none, "fromSymbolProviders", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirSymbolNamesProvider create(FirSession session, List<? extends FirSymbolNamesProvider> providers) {
            session.getClass();
            providers.getClass();
            int size = providers.size();
            if (size == 0) {
                return FirEmptySymbolNamesProvider.INSTANCE;
            }
            if (size != 1) {
                return new FirCompositeCachedSymbolNamesProvider(session, providers);
            }
            FirSymbolNamesProvider firSymbolNamesProvider = (FirSymbolNamesProvider) CollectionsKt.single(providers);
            return firSymbolNamesProvider instanceof FirCachedSymbolNamesProvider ? (FirCachedSymbolNamesProvider) firSymbolNamesProvider : new FirDelegatingCachedSymbolNamesProvider(session, firSymbolNamesProvider);
        }

        public final FirSymbolNamesProvider fromSymbolProviders(FirSession session, List<? extends FirSymbolProvider> providers) {
            session.getClass();
            providers.getClass();
            List<? extends FirSymbolProvider> list = providers;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirSymbolProvider) it.next()).getSymbolNamesProvider());
            }
            return create(session, arrayList);
        }

        private Companion() {
        }
    }
}
