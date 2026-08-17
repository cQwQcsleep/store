package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeEquivalentCallConflictResolver;
import org.jetbrains.kotlin.fir.resolve.providers.FirCompositeSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001:B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u000fH\u0016J\"\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J.\u0010%\u001a\u00020&2\u0010\u0010'\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160(2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0017b\u0002\b)J*\u0010*\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020+0(2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0017b\u0002\b)J*\u0010,\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020-0(2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0017b\u0002\b)J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\"H\u0016JD\u00101\u001a\b\u0012\u0004\u0012\u0002H20\f\"\b\b\u0000\u00103*\u000204\"\u000e\b\u0001\u00102*\b\u0012\u0004\u0012\u0002H30\u00162\f\u00105\u001a\b\u0012\u0004\u0012\u0002H20\f2\f\u00106\u001a\b\u0012\u0004\u0012\u0002H20\fH\u0002J\u0018\u00107\u001a\u00020/2\u0006\u00108\u001a\u0002042\u0006\u00109\u001a\u000204H\u0002R\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\f0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\u0017\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0014\u0010\u0019\u001a\u00020\u001aX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirCommonDeclarationsMappingSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "commonSymbolProvider", "platformSymbolProvider", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;)V", "getCommonSymbolProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getPlatformSymbolProvider", "providers", Argument.Delimiters.none, "classMapping", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirCommonDeclarationsMappingSymbolProvider$ClassPair;", "getClassMapping", "()Ljava/util/Map;", "processedCallables", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/CallableId;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "commonCallableToPlatformCallableMap", "getCommonCallableToPlatformCallableMap", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getClassLikeSymbolByClassId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "classId", "getTopLevelCallableSymbols", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getTopLevelPropertySymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "hasPackage", Argument.Delimiters.none, "fqName", "preferPlatformDeclarations", "S", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "commonDeclarations", "platformDeclarations", "areEquivalentTopLevelCallables", "first", "second", "ClassPair", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCommonDeclarationsMappingSymbolProvider extends FirSymbolProvider {
    private final Map<ClassId, ClassPair> classMapping;
    private final Map<FirCallableSymbol<?>, FirCallableSymbol<?>> commonCallableToPlatformCallableMap;
    private final FirSymbolProvider commonSymbolProvider;
    private final FirSymbolProvider platformSymbolProvider;
    private final Map<CallableId, List<FirCallableSymbol<?>>> processedCallables;
    private final List<FirSymbolProvider> providers;
    private final FirSymbolNamesProvider symbolNamesProvider;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003HÆ\u0003J)\u0010\f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirCommonDeclarationsMappingSymbolProvider$ClassPair;", Argument.Delimiters.none, "commonClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "platformClass", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)V", "getCommonClass", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getPlatformClass", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ClassPair {
        private final FirClassLikeSymbol<?> commonClass;
        private final FirClassLikeSymbol<?> platformClass;

        public ClassPair(FirClassLikeSymbol<?> firClassLikeSymbol, FirClassLikeSymbol<?> firClassLikeSymbol2) {
            this.commonClass = firClassLikeSymbol;
            this.platformClass = firClassLikeSymbol2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ClassPair copy$default(ClassPair classPair, FirClassLikeSymbol firClassLikeSymbol, FirClassLikeSymbol firClassLikeSymbol2, int i, Object obj) {
            if ((i & 1) != 0) {
                firClassLikeSymbol = classPair.commonClass;
            }
            if ((i & 2) != 0) {
                firClassLikeSymbol2 = classPair.platformClass;
            }
            return classPair.copy(firClassLikeSymbol, firClassLikeSymbol2);
        }

        public final FirClassLikeSymbol<?> component1() {
            return this.commonClass;
        }

        public final FirClassLikeSymbol<?> component2() {
            return this.platformClass;
        }

        public final ClassPair copy(FirClassLikeSymbol<?> commonClass, FirClassLikeSymbol<?> platformClass) {
            return new ClassPair(commonClass, platformClass);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ClassPair)) {
                return false;
            }
            ClassPair classPair = (ClassPair) other;
            return Intrinsics.areEqual(this.commonClass, classPair.commonClass) && Intrinsics.areEqual(this.platformClass, classPair.platformClass);
        }

        public final FirClassLikeSymbol<?> getCommonClass() {
            return this.commonClass;
        }

        public final FirClassLikeSymbol<?> getPlatformClass() {
            return this.platformClass;
        }

        public int hashCode() {
            FirClassLikeSymbol<?> firClassLikeSymbol = this.commonClass;
            int iHashCode = (firClassLikeSymbol == null ? 0 : firClassLikeSymbol.hashCode()) * 31;
            FirClassLikeSymbol<?> firClassLikeSymbol2 = this.platformClass;
            return iHashCode + (firClassLikeSymbol2 != null ? firClassLikeSymbol2.hashCode() : 0);
        }

        public String toString() {
            return "ClassPair(commonClass=" + this.commonClass + ", platformClass=" + this.platformClass + ')';
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirCommonDeclarationsMappingSymbolProvider(FirSession firSession, FirSymbolProvider firSymbolProvider, FirSymbolProvider firSymbolProvider2) {
        super(firSession);
        firSession.getClass();
        firSymbolProvider.getClass();
        firSymbolProvider2.getClass();
        this.commonSymbolProvider = firSymbolProvider;
        this.platformSymbolProvider = firSymbolProvider2;
        List<FirSymbolProvider> listListOf = CollectionsKt.listOf(new FirSymbolProvider[]{firSymbolProvider, firSymbolProvider2});
        this.providers = listListOf;
        this.classMapping = new LinkedHashMap();
        this.processedCallables = new HashMap();
        this.commonCallableToPlatformCallableMap = new LinkedHashMap();
        this.symbolNamesProvider = FirCompositeSymbolNamesProvider.INSTANCE.fromSymbolProviders(listListOf);
    }

    private final boolean areEquivalentTopLevelCallables(FirCallableDeclaration first, FirCallableDeclaration second) {
        return ConeEquivalentCallConflictResolver.INSTANCE.areEquivalentTopLevelCallables(first, second, getSession(), null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <D extends FirCallableDeclaration, S extends FirCallableSymbol<? extends D>> List<S> preferPlatformDeclarations(List<? extends S> commonDeclarations, List<? extends S> platformDeclarations) {
        Object next;
        List<S> mutableList = CollectionsKt.toMutableList(platformDeclarations);
        for (S s : commonDeclarations) {
            Iterator<T> it = platformDeclarations.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!areEquivalentTopLevelCallables((FirCallableDeclaration) ((FirCallableSymbol) next).getFir(), (FirCallableDeclaration) s.getFir()));
            FirCallableSymbol<?> firCallableSymbol = (FirCallableSymbol) next;
            if (firCallableSymbol != null) {
                this.commonCallableToPlatformCallableMap.put(s, firCallableSymbol);
            } else {
                mutableList.add(s);
            }
        }
        return mutableList;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirClassLikeSymbol<?> getClassLikeSymbolByClassId(ClassId classId) {
        classId.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = this.commonSymbolProvider.getClassLikeSymbolByClassId(classId);
        FirClassLikeSymbol<?> classLikeSymbolByClassId2 = this.platformSymbolProvider.getClassLikeSymbolByClassId(classId);
        if (classLikeSymbolByClassId == null && classLikeSymbolByClassId2 == null) {
            return null;
        }
        this.classMapping.put(classId, new ClassPair(classLikeSymbolByClassId, classLikeSymbolByClassId2));
        return (classLikeSymbolByClassId != null && (classLikeSymbolByClassId2 == null || Intrinsics.areEqual(classLikeSymbolByClassId, classLikeSymbolByClassId2))) ? classLikeSymbolByClassId : classLikeSymbolByClassId2;
    }

    public final Map<ClassId, ClassPair> getClassMapping() {
        return this.classMapping;
    }

    public final Map<FirCallableSymbol<?>, FirCallableSymbol<?>> getCommonCallableToPlatformCallableMap() {
        return this.commonCallableToPlatformCallableMap;
    }

    public final FirSymbolProvider getCommonSymbolProvider() {
        return this.commonSymbolProvider;
    }

    public final FirSymbolProvider getPlatformSymbolProvider() {
        return this.platformSymbolProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirSymbolNamesProvider getSymbolNamesProvider() {
        return this.symbolNamesProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public List<FirCallableSymbol<?>> getTopLevelCallableSymbols(FqName packageFqName, Name name) {
        packageFqName.getClass();
        name.getClass();
        CallableId callableId = new CallableId(packageFqName, name);
        List<FirCallableSymbol<?>> list = this.processedCallables.get(callableId);
        if (list != null) {
            return list;
        }
        List<FirCallableSymbol<?>> listPreferPlatformDeclarations = preferPlatformDeclarations(this.commonSymbolProvider.getTopLevelCallableSymbols(packageFqName, name), this.platformSymbolProvider.getTopLevelCallableSymbols(packageFqName, name));
        this.processedCallables.put(callableId, listPreferPlatformDeclarations);
        return listPreferPlatformDeclarations;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        CollectionsKt.addAll(destination, getTopLevelCallableSymbols(packageFqName, name));
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        List<FirNamedFunctionSymbol> list = destination;
        for (Object obj : getTopLevelCallableSymbols(packageFqName, name)) {
            if (obj instanceof FirNamedFunctionSymbol) {
                list.add(obj);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        List<FirPropertySymbol> list = destination;
        for (Object obj : getTopLevelCallableSymbols(packageFqName, name)) {
            if (obj instanceof FirPropertySymbol) {
                list.add(obj);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        List<FirSymbolProvider> list = this.providers;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((FirSymbolProvider) it.next()).hasPackage(fqName)) {
                return true;
            }
        }
        return false;
    }
}
