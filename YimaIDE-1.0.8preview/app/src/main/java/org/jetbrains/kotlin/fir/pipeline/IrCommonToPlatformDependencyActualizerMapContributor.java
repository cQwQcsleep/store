package org.jetbrains.kotlin.fir.pipeline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.actualizer.IrActualizerMapContributor;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.pipeline.IrCommonToPlatformDependencyActualizerMapContributor;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirCachingCompositeSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirCommonDeclarationsMappingSymbolProvider;
import org.jetbrains.kotlin.fir.session.NativeForwardDeclarationsSymbolProvider;
import org.jetbrains.kotlin.fir.session.StructuredProvidersKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeAliasSymbol;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 '2\u00020\u0001:\u0001'B;\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0015\u001a\u00020\u0010H\u0016J\u0014\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\tH\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\n*\u0006\u0012\u0002\b\u00030#H\u0002J\u0010\u0010$\u001a\u00020\u0017*\u0006\u0012\u0002\b\u00030%H\u0002J\u0010\u0010$\u001a\u00020\u0017*\u0006\u0012\u0002\b\u00030&H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R-\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\t8BX\u0082\u0084\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u0014\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/pipeline/IrCommonToPlatformDependencyActualizerMapContributor;", "Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizerMapContributor;", "platformSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "platformMappingProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirCommonDeclarationsMappingSymbolProvider;", "commonMappingProviders", Argument.Delimiters.none, "componentsPerSession", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirCommonDeclarationsMappingSymbolProvider;Ljava/util/List;Ljava/util/Map;)V", "dependencyToSourceSession", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "classesMap", "Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizerMapContributor$ActualClassInfo;", "getClassesMap", "()Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizerMapContributor$ActualClassInfo;", "classesMap$delegate", "Lkotlin/Lazy;", "collectClassesMap", "topLevelCallablesMap", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "getTopLevelCallablesMap$annotations", "()V", "getTopLevelCallablesMap", "()Ljava/util/Map;", "topLevelCallablesMap$delegate", "collectTopLevelCallablesMap", "actualizeClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "properComponents", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "toIrSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Companion", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IrCommonToPlatformDependencyActualizerMapContributor extends IrActualizerMapContributor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: classesMap$delegate, reason: from kotlin metadata */
    private final Lazy classesMap;
    private final List<FirCommonDeclarationsMappingSymbolProvider> commonMappingProviders;
    private final Map<FirSession, Fir2IrComponents> componentsPerSession;
    private final Map<FirModuleData, FirSession> dependencyToSourceSession;
    private final FirCommonDeclarationsMappingSymbolProvider platformMappingProvider;
    private final FirSession platformSession;

    /* JADX INFO: renamed from: topLevelCallablesMap$delegate, reason: from kotlin metadata */
    private final Lazy topLevelCallablesMap;

    /* JADX WARN: Multi-variable type inference failed */
    private IrCommonToPlatformDependencyActualizerMapContributor(FirSession firSession, FirCommonDeclarationsMappingSymbolProvider firCommonDeclarationsMappingSymbolProvider, List<FirCommonDeclarationsMappingSymbolProvider> list, Map<FirSession, ? extends Fir2IrComponents> map) {
        this.platformSession = firSession;
        this.platformMappingProvider = firCommonDeclarationsMappingSymbolProvider;
        this.commonMappingProviders = list;
        this.componentsPerSession = map;
        final Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (final FirSession firSession2 : map.keySet()) {
            FirModuleData moduleData = FirModuleDataKt.getModuleData(firSession2);
            Function1 function1 = new Function1() { // from class: dy6
                public final Object invoke(Object obj) {
                    return IrCommonToPlatformDependencyActualizerMapContributor.dependencyToSourceSession$lambda$0$0(mapCreateMapBuilder, firSession2, (FirModuleData) obj);
                }
            };
            Iterator<T> it = moduleData.getDependencies().iterator();
            while (it.hasNext()) {
                function1.invoke(it.next());
            }
            Iterator<T> it2 = moduleData.getFriendDependencies().iterator();
            while (it2.hasNext()) {
                function1.invoke(it2.next());
            }
        }
        FirSession firSession3 = (FirSession) CollectionsKt.first(this.componentsPerSession.keySet());
        mapCreateMapBuilder.put(FirModuleDataKt.getModuleData(StructuredProvidersKt.getStructuredProviders(firSession3).getSharedProvider().getSession()), firSession3);
        this.dependencyToSourceSession = MapsKt.build(mapCreateMapBuilder);
        this.classesMap = LazyKt.lazy(new Function0() { // from class: ey6
            public final Object invoke() {
                return IrCommonToPlatformDependencyActualizerMapContributor.a(this.b);
            }
        });
        this.topLevelCallablesMap = LazyKt.lazy(new Function0() { // from class: fy6
            public final Object invoke() {
                return IrCommonToPlatformDependencyActualizerMapContributor.c(this.b);
            }
        });
    }

    public static IrActualizerMapContributor.ActualClassInfo a(IrCommonToPlatformDependencyActualizerMapContributor irCommonToPlatformDependencyActualizerMapContributor) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        Iterator<FirCommonDeclarationsMappingSymbolProvider> it = irCommonToPlatformDependencyActualizerMapContributor.commonMappingProviders.iterator();
        while (it.hasNext()) {
            for (FirCommonDeclarationsMappingSymbolProvider.ClassPair classPair : CollectionsKt.toList(it.next().getClassMapping().values())) {
                FirClassLikeSymbol<?> platformClass = classPair.getPlatformClass();
                if (platformClass != null || (platformClass = classPair.getCommonClass()) != null) {
                    FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(irCommonToPlatformDependencyActualizerMapContributor.platformSession).getClassLikeSymbolByClassId(platformClass.getClassId());
                    classLikeSymbolByClassId.getClass();
                    classesMap_delegate$lambda$0$processPairOfClasses(irCommonToPlatformDependencyActualizerMapContributor, linkedHashMap2, linkedHashMap, platformClass, classLikeSymbolByClassId);
                }
            }
        }
        classesMap_delegate$lambda$0$handleCloneable(irCommonToPlatformDependencyActualizerMapContributor, linkedHashMap2, linkedHashMap);
        return new IrActualizerMapContributor.ActualClassInfo(linkedHashMap, linkedHashMap2);
    }

    public static Map c(IrCommonToPlatformDependencyActualizerMapContributor irCommonToPlatformDependencyActualizerMapContributor) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (Map.Entry<FirCallableSymbol<?>, FirCallableSymbol<?>> entry : irCommonToPlatformDependencyActualizerMapContributor.platformMappingProvider.getCommonCallableToPlatformCallableMap().entrySet()) {
            FirCallableSymbol<?> key = entry.getKey();
            FirCallableSymbol<?> value = entry.getValue();
            IrPropertySymbol irSymbol = irCommonToPlatformDependencyActualizerMapContributor.toIrSymbol(key);
            IrPropertySymbol irSymbol2 = irCommonToPlatformDependencyActualizerMapContributor.toIrSymbol(value);
            mapCreateMapBuilder.put(irSymbol, irSymbol2);
            if ((irSymbol instanceof IrPropertySymbol) && (irSymbol2 instanceof IrPropertySymbol)) {
                IrPropertySymbol irPropertySymbol = irSymbol;
                IrSimpleFunction getter = irPropertySymbol.getOwner().getGetter();
                IrSimpleFunctionSymbol symbol = getter != null ? getter.getSymbol() : null;
                IrPropertySymbol irPropertySymbol2 = irSymbol2;
                IrSimpleFunction getter2 = irPropertySymbol2.getOwner().getGetter();
                IrSimpleFunctionSymbol symbol2 = getter2 != null ? getter2.getSymbol() : null;
                if (symbol != null && symbol2 != null) {
                    mapCreateMapBuilder.put(symbol, symbol2);
                }
                IrSimpleFunction setter = irPropertySymbol.getOwner().getSetter();
                IrSimpleFunctionSymbol symbol3 = setter != null ? setter.getSymbol() : null;
                IrSimpleFunction setter2 = irPropertySymbol2.getOwner().getSetter();
                IrSimpleFunctionSymbol symbol4 = setter2 != null ? setter2.getSymbol() : null;
                if (symbol3 != null && symbol4 != null) {
                    mapCreateMapBuilder.put(symbol3, symbol4);
                }
            }
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    private static final void classesMap_delegate$lambda$0$handleCloneable(IrCommonToPlatformDependencyActualizerMapContributor irCommonToPlatformDependencyActualizerMapContributor, Map<ClassId, IrTypeAliasSymbol> map, Map<IrClassSymbol, IrClassSymbol> map2) {
        FirClassLikeSymbol<?> classLikeSymbolByClassId;
        ClassId cloneable = StandardClassIds.INSTANCE.getCloneable();
        FirClassLikeSymbol<?> classLikeSymbolByClassId2 = irCommonToPlatformDependencyActualizerMapContributor.platformMappingProvider.getPlatformSymbolProvider().getClassLikeSymbolByClassId(cloneable);
        if (classLikeSymbolByClassId2 == null || irCommonToPlatformDependencyActualizerMapContributor.platformMappingProvider.getCommonSymbolProvider().getClassLikeSymbolByClassId(cloneable) != null || (classLikeSymbolByClassId = StructuredProvidersKt.getStructuredProviders(irCommonToPlatformDependencyActualizerMapContributor.platformMappingProvider.getSession()).getSharedProvider().getClassLikeSymbolByClassId(cloneable)) == null) {
            return;
        }
        classesMap_delegate$lambda$0$processPairOfClasses(irCommonToPlatformDependencyActualizerMapContributor, map, map2, classLikeSymbolByClassId, classLikeSymbolByClassId2);
    }

    private static final void classesMap_delegate$lambda$0$processPairOfClasses(IrCommonToPlatformDependencyActualizerMapContributor irCommonToPlatformDependencyActualizerMapContributor, Map<ClassId, IrTypeAliasSymbol> map, Map<IrClassSymbol, IrClassSymbol> map2, FirClassLikeSymbol<?> firClassLikeSymbol, FirClassLikeSymbol<?> firClassLikeSymbol2) {
        IrClassSymbol classOrFail;
        IrSymbol irSymbol = irCommonToPlatformDependencyActualizerMapContributor.toIrSymbol(firClassLikeSymbol);
        IrClassSymbol irClassSymbol = irSymbol instanceof IrClassSymbol ? (IrClassSymbol) irSymbol : null;
        if (irClassSymbol == null) {
            return;
        }
        IrTypeAliasSymbol irSymbol2 = irCommonToPlatformDependencyActualizerMapContributor.toIrSymbol(firClassLikeSymbol2);
        if (irSymbol2 instanceof IrClassSymbol) {
            classOrFail = (IrClassSymbol) irSymbol2;
        } else if (!(irSymbol2 instanceof IrTypeAliasSymbol)) {
            w04.a("Unexpected symbol: ", irClassSymbol);
            return;
        } else {
            map.put(firClassLikeSymbol2.getClassId(), irSymbol2);
            classOrFail = IrTypesKt.getClassOrFail(irSymbol2.getOwner().getExpandedType().getType());
        }
        map2.put(irClassSymbol, classOrFail);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit dependencyToSourceSession$lambda$0$0(Map map, FirSession firSession, FirModuleData firModuleData) {
        firModuleData.getClass();
        if (firModuleData.getSession().getKind() == FirSession.Kind.Library) {
            map.put(firModuleData, firSession);
            List<FirSymbolProvider> dependencyProviders = StructuredProvidersKt.getStructuredProviders(firModuleData.getSession()).getDependencyProviders();
            ArrayList arrayList = new ArrayList();
            for (Object obj : dependencyProviders) {
                if (obj instanceof NativeForwardDeclarationsSymbolProvider) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                map.put(((NativeForwardDeclarationsSymbolProvider) it.next()).getForwardDeclarationsModuleData(), firSession);
            }
        }
        return Unit.INSTANCE;
    }

    private final IrActualizerMapContributor.ActualClassInfo getClassesMap() {
        return (IrActualizerMapContributor.ActualClassInfo) this.classesMap.getValue();
    }

    private final Map<IrSymbol, IrSymbol> getTopLevelCallablesMap() {
        return (Map) this.topLevelCallablesMap.getValue();
    }

    private final Fir2IrComponents properComponents(FirBasedSymbol<?> firBasedSymbol) {
        return (Fir2IrComponents) MapsKt.getValue(this.componentsPerSession, (FirSession) MapsKt.getValue(this.dependencyToSourceSession, firBasedSymbol.getModuleData()));
    }

    private final IrSymbol toIrSymbol(FirCallableSymbol<?> firCallableSymbol) {
        Fir2IrComponents fir2IrComponentsProperComponents = properComponents(firCallableSymbol);
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            return Fir2IrDeclarationStorage.getIrFunctionSymbol$default(fir2IrComponentsProperComponents.getDeclarationStorage(), (FirFunctionSymbol) firCallableSymbol, null, false, 6, null);
        }
        if (firCallableSymbol instanceof FirPropertySymbol) {
            return Fir2IrDeclarationStorage.getIrPropertySymbol$default(fir2IrComponentsProperComponents.getDeclarationStorage(), (FirPropertySymbol) firCallableSymbol, null, 2, null);
        }
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        wq6.a();
        return null;
    }

    public IrClassSymbol actualizeClass(ClassId classId) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        classId.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(this.platformSession).getClassLikeSymbolByClassId(classId);
        if (classLikeSymbolByClassId == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(classLikeSymbolByClassId, this.platformMappingProvider.getSession())) == null || !this.dependencyToSourceSession.containsKey(firRegularClassSymbolFullyExpandedClass.getModuleData())) {
            return null;
        }
        IrClassSymbol irSymbol = toIrSymbol(firRegularClassSymbolFullyExpandedClass);
        if (irSymbol instanceof IrClassSymbol) {
            return irSymbol;
        }
        return null;
    }

    public IrActualizerMapContributor.ActualClassInfo collectClassesMap() {
        return getClassesMap();
    }

    public Map<IrSymbol, IrSymbol> collectTopLevelCallablesMap() {
        return getTopLevelCallablesMap();
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/pipeline/IrCommonToPlatformDependencyActualizerMapContributor$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/pipeline/IrCommonToPlatformDependencyActualizerMapContributor;", "platformSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "componentsPerSession", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static final void create$process(List<FirCommonDeclarationsMappingSymbolProvider> list, FirSession firSession) {
            Object next;
            FirSymbolProvider symbolProvider = FirSymbolProviderKt.getSymbolProvider(firSession);
            symbolProvider.getClass();
            Iterator<T> it = ((FirCachingCompositeSymbolProvider) symbolProvider).getProviders().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!(next instanceof FirCommonDeclarationsMappingSymbolProvider));
            org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(list, (FirCommonDeclarationsMappingSymbolProvider) next);
            Iterator<FirModuleData> it2 = FirModuleDataKt.getModuleData(firSession).getDependsOnDependencies().iterator();
            while (it2.hasNext()) {
                create$process(list, it2.next().getSession());
            }
        }

        public final IrCommonToPlatformDependencyActualizerMapContributor create(FirSession platformSession, Map<FirSession, ? extends Fir2IrComponents> componentsPerSession) {
            platformSession.getClass();
            componentsPerSession.getClass();
            ArrayList arrayList = new ArrayList();
            create$process(arrayList, platformSession);
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : arrayList) {
                if (Intrinsics.areEqual(((FirCommonDeclarationsMappingSymbolProvider) obj).getSession(), platformSession)) {
                    arrayList2.add(obj);
                } else {
                    arrayList3.add(obj);
                }
            }
            Pair pair = new Pair(arrayList2, arrayList3);
            List list = (List) pair.component1();
            List list2 = (List) pair.component2();
            if (list.isEmpty()) {
                return null;
            }
            return new IrCommonToPlatformDependencyActualizerMapContributor(platformSession, (FirCommonDeclarationsMappingSymbolProvider) CollectionsKt.single(list), list2, componentsPerSession, null);
        }

        private Companion() {
        }
    }

    private final IrSymbol toIrSymbol(FirClassLikeSymbol<?> firClassLikeSymbol) {
        Fir2IrComponents fir2IrComponentsProperComponents = properComponents(firClassLikeSymbol);
        if (firClassLikeSymbol instanceof FirClassSymbol) {
            return fir2IrComponentsProperComponents.getClassifierStorage().getIrClassSymbol((FirClassSymbol<?>) firClassLikeSymbol);
        }
        if (firClassLikeSymbol instanceof FirTypeAliasSymbol) {
            return fir2IrComponentsProperComponents.getClassifierStorage().getIrTypeAliasSymbol((FirTypeAliasSymbol) firClassLikeSymbol);
        }
        bu8.a();
        return null;
    }

    public /* synthetic */ IrCommonToPlatformDependencyActualizerMapContributor(FirSession firSession, FirCommonDeclarationsMappingSymbolProvider firCommonDeclarationsMappingSymbolProvider, List list, Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firCommonDeclarationsMappingSymbolProvider, list, map);
    }
}
