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
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016J\u0010\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016J\u0018\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0014\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0014\u0010\u0018\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000e¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/FirCompositeSymbolNamesProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "providers", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getProviders", "()Ljava/util/List;", "getPackageNames", Argument.Delimiters.none, Argument.Delimiters.none, "hasSpecificClassifierPackageNamesComputation", Argument.Delimiters.none, "getHasSpecificClassifierPackageNamesComputation", "()Z", "getPackageNamesWithTopLevelClassifiers", "getTopLevelClassifierNamesInPackage", "Lorg/jetbrains/kotlin/name/Name;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "hasSpecificCallablePackageNamesComputation", "getHasSpecificCallablePackageNamesComputation", "getPackageNamesWithTopLevelCallables", "getTopLevelCallableNamesInPackage", "mayHaveSyntheticFunctionTypes", "getMayHaveSyntheticFunctionTypes", "mayHaveSyntheticFunctionType", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirCompositeSymbolNamesProvider extends FirSymbolNamesProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean hasSpecificCallablePackageNamesComputation;
    private final boolean hasSpecificClassifierPackageNamesComputation;
    private final boolean mayHaveSyntheticFunctionTypes;
    private final List<FirSymbolNamesProvider> providers;

    /* JADX WARN: Multi-variable type inference failed */
    public FirCompositeSymbolNamesProvider(List<? extends FirSymbolNamesProvider> list) {
        boolean z;
        boolean z2;
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

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public Set<String> getPackageNames() {
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

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public Set<String> getPackageNamesWithTopLevelCallables() {
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

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public Set<String> getPackageNamesWithTopLevelClassifiers() {
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

    public final List<FirSymbolNamesProvider> getProviders() {
        return this.providers;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public Set<Name> getTopLevelCallableNamesInPackage(FqName packageFqName) {
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

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
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
    public boolean mayHaveSyntheticFunctionType(ClassId classId) {
        classId.getClass();
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

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007J\u0014\u0010\b\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\t0\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/FirCompositeSymbolNamesProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "providers", Argument.Delimiters.none, "fromSymbolProviders", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirSymbolNamesProvider create(List<? extends FirSymbolNamesProvider> providers) {
            providers.getClass();
            int size = providers.size();
            if (size != 0) {
                return size != 1 ? new FirCompositeSymbolNamesProvider(providers) : (FirSymbolNamesProvider) CollectionsKt.single(providers);
            }
            return FirEmptySymbolNamesProvider.INSTANCE;
        }

        public final FirSymbolNamesProvider fromSymbolProviders(List<? extends FirSymbolProvider> providers) {
            providers.getClass();
            List<? extends FirSymbolProvider> list = providers;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirSymbolProvider) it.next()).getSymbolNamesProvider());
            }
            return create(arrayList);
        }

        private Companion() {
        }
    }
}
