package org.jetbrains.kotlin.fir.extensions;

import defpackage.v65;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirGeneratedDeclarationsUtilsKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.caches.FirLazyValue;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.GeneratedDeclarationValidationKt;
import org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 ?2\u00020\u00012\u00020\u0002:\u0001?B'\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJm\u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0\u001c0\u001b\"\u0004\b\u0000\u0010!\"\u0004\b\u0001\u0010 2\u001d\u0010\"\u001a\u0019\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0$0#¢\u0006\u0002\b%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u00020\u00170#2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H 0#H\u0002J\u0016\u0010*\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000f2\u0006\u0010+\u001a\u00020\u000eH\u0002J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00130\b2\u0006\u0010-\u001a\u00020\u0012H\u0002J\u0016\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00150\b2\u0006\u0010-\u001a\u00020\u0012H\u0002J\u0016\u00103\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000f2\u0006\u0010+\u001a\u00020\u000eH\u0016J.\u00104\u001a\u0002052\u0010\u00106\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u000308072\u0006\u0010&\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u001dH\u0017b\u0002\b:J*\u0010;\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u0013072\u0006\u0010&\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u001dH\u0017b\u0002\b:J*\u0010<\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u0015072\u0006\u0010&\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u001dH\u0017b\u0002\b:J\u0010\u0010=\u001a\u00020\u00182\u0006\u0010>\u001a\u00020\u0017H\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\f\u001a\u001c\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0011\u001a\u001c\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\b\u0012\u0006\u0012\u0004\u0018\u00010\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0014\u001a\u001c\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\b\u0012\u0006\u0012\u0004\u0018\u00010\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0019\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u001e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010(\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010)\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\u000200X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102¨\u0006@"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirExtensionDeclarationsSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "extensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;Ljava/util/List;)V", "classCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", Argument.Delimiters.none, "functionCache", "Lorg/jetbrains/kotlin/name/CallableId;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "propertyCache", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "packageCache", "Lorg/jetbrains/kotlin/name/FqName;", Argument.Delimiters.none, "callableNamesInPackageCache", "Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "classNamesInPackageCache", "computeNamesGroupedByPackage", "N", "I", "ids", "Lkotlin/Function1;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "packageFqName", "shortName", "extensionsByTopLevelClassId", "extensionsByTopLevelCallableId", "generateClassLikeDeclaration", "classId", "generateTopLevelFunctions", "callableId", "generateTopLevelProperties", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getClassLikeSymbolByClassId", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbolsTo", "getTopLevelPropertySymbolsTo", "hasPackage", "fqName", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExtensionDeclarationsSymbolProvider extends FirSymbolProvider implements FirSessionComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FirLazyValue<Map<FqName, Set<Name>>> callableNamesInPackageCache;
    private final FirCache classCache;
    private final FirLazyValue<Map<FqName, Set<Name>>> classNamesInPackageCache;
    private final List<FirDeclarationGenerationExtension> extensions;
    private final FirLazyValue<Map<CallableId, List<FirDeclarationGenerationExtension>>> extensionsByTopLevelCallableId;
    private final FirLazyValue<Map<ClassId, List<FirDeclarationGenerationExtension>>> extensionsByTopLevelClassId;
    private final FirCache functionCache;
    private final FirCache packageCache;
    private final FirCache propertyCache;
    private final FirSymbolNamesProvider symbolNamesProvider;

    /* JADX WARN: Multi-variable type inference failed */
    private FirExtensionDeclarationsSymbolProvider(FirSession firSession, FirCachesFactory firCachesFactory, List<? extends FirDeclarationGenerationExtension> list) {
        super(firSession);
        this.extensions = list;
        this.classCache = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider$classCache$1
            public final FirClassLikeSymbol<?> invoke(ClassId classId, Void r2) {
                classId.getClass();
                return this.this$0.generateClassLikeDeclaration(classId);
            }
        });
        this.functionCache = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider$functionCache$1
            public final List<FirNamedFunctionSymbol> invoke(CallableId callableId, Void r2) {
                callableId.getClass();
                return this.this$0.generateTopLevelFunctions(callableId);
            }
        });
        this.propertyCache = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider$propertyCache$1
            public final List<FirPropertySymbol> invoke(CallableId callableId, Void r2) {
                callableId.getClass();
                return this.this$0.generateTopLevelProperties(callableId);
            }
        });
        this.packageCache = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider$packageCache$1
            public final Boolean invoke(FqName fqName, Void r3) {
                fqName.getClass();
                List list2 = this.this$0.extensions;
                boolean z = false;
                if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        if (((FirDeclarationGenerationExtension) it.next()).hasPackage(fqName)) {
                            z = true;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        });
        this.callableNamesInPackageCache = firCachesFactory.createLazyValue(new Function0() { // from class: w65
            public final Object invoke() {
                return FirExtensionDeclarationsSymbolProvider.f(this.b);
            }
        });
        this.classNamesInPackageCache = firCachesFactory.createLazyValue(new Function0() { // from class: x65
            public final Object invoke() {
                return FirExtensionDeclarationsSymbolProvider.b(this.b);
            }
        });
        this.extensionsByTopLevelClassId = FirCachesFactoryKt.getFirCachesFactory(firSession).createLazyValue(new Function0() { // from class: y65
            public final Object invoke() {
                return FirExtensionDeclarationsSymbolProvider.e(this.b);
            }
        });
        this.extensionsByTopLevelCallableId = FirCachesFactoryKt.getFirCachesFactory(firSession).createLazyValue(new Function0() { // from class: z65
            public final Object invoke() {
                return FirExtensionDeclarationsSymbolProvider.c(this.b);
            }
        });
        this.symbolNamesProvider = new FirSymbolNamesProvider() { // from class: org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider$symbolNamesProvider$1
            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificCallablePackageNamesComputation() {
                return true;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificClassifierPackageNamesComputation() {
                return true;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNames() {
                return SetsKt.plus(getPackageNamesWithTopLevelClassifiers(), getPackageNamesWithTopLevelCallables());
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNamesWithTopLevelCallables() {
                FirExtensionDeclarationsSymbolProvider firExtensionDeclarationsSymbolProvider = this.this$0;
                Set setCreateSetBuilder = SetsKt.createSetBuilder();
                Iterator it = firExtensionDeclarationsSymbolProvider.extensions.iterator();
                while (it.hasNext()) {
                    Set set = setCreateSetBuilder;
                    Iterator<T> it2 = ((FirDeclarationGenerationExtension) it.next()).getTopLevelCallableIdsCache().getValue().iterator();
                    while (it2.hasNext()) {
                        set.add(((CallableId) it2.next()).getPackageName().asString());
                    }
                }
                return SetsKt.build(setCreateSetBuilder);
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNamesWithTopLevelClassifiers() {
                FirExtensionDeclarationsSymbolProvider firExtensionDeclarationsSymbolProvider = this.this$0;
                Set setCreateSetBuilder = SetsKt.createSetBuilder();
                Iterator it = firExtensionDeclarationsSymbolProvider.extensions.iterator();
                while (it.hasNext()) {
                    Set set = setCreateSetBuilder;
                    Iterator<T> it2 = ((FirDeclarationGenerationExtension) it.next()).getTopLevelClassIdsCache().getValue().iterator();
                    while (it2.hasNext()) {
                        set.add(((ClassId) it2.next()).getPackageFqName().asString());
                    }
                }
                return SetsKt.build(setCreateSetBuilder);
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelCallableNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                Set<Name> set = (Set) ((Map) this.this$0.callableNamesInPackageCache.getValue()).get(packageFqName);
                return set == null ? SetsKt.emptySet() : set;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                Set<Name> set = (Set) ((Map) this.this$0.classNamesInPackageCache.getValue()).get(packageFqName);
                return set == null ? SetsKt.emptySet() : set;
            }
        };
    }

    public static Unit a(Ref.ObjectRef objectRef, FirClassifierSymbol firClassifierSymbol) {
        firClassifierSymbol.getClass();
        if (firClassifierSymbol instanceof FirClassLikeSymbol) {
            objectRef.element = firClassifierSymbol;
        }
        return Unit.INSTANCE;
    }

    public static Map b(FirExtensionDeclarationsSymbolProvider firExtensionDeclarationsSymbolProvider) {
        return firExtensionDeclarationsSymbolProvider.computeNamesGroupedByPackage(FirExtensionDeclarationsSymbolProvider$classNamesInPackageCache$1$1.INSTANCE, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider$classNamesInPackageCache$1$2
            public Object get(Object obj) {
                return ((ClassId) obj).getPackageFqName();
            }
        }, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider$classNamesInPackageCache$1$3
            public Object get(Object obj) {
                return ((ClassId) obj).getShortClassName();
            }
        });
    }

    public static Map c(FirExtensionDeclarationsSymbolProvider firExtensionDeclarationsSymbolProvider) {
        List<FirDeclarationGenerationExtension> list = firExtensionDeclarationsSymbolProvider.extensions;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FirDeclarationGenerationExtension firDeclarationGenerationExtension : list) {
            for (CallableId callableId : firDeclarationGenerationExtension.getTopLevelCallableIdsCache().getValue()) {
                Collection arrayList = (List) linkedHashMap.get(callableId);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(callableId, arrayList);
                }
                arrayList.add(firDeclarationGenerationExtension);
            }
        }
        return linkedHashMap;
    }

    private final <I, N> Map<FqName, Set<N>> computeNamesGroupedByPackage(Function1<? super FirDeclarationGenerationExtension, ? extends Collection<? extends I>> ids, Function1<? super I, FqName> packageFqName, Function1<? super I, ? extends N> shortName) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        Iterator<FirDeclarationGenerationExtension> it = this.extensions.iterator();
        while (it.hasNext()) {
            for (Object obj : (Collection) ids.invoke(it.next())) {
                Object objInvoke = packageFqName.invoke(obj);
                Object linkedHashSet = mapCreateMapBuilder.get(objInvoke);
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                    mapCreateMapBuilder.put(objInvoke, linkedHashSet);
                }
                ((Set) linkedHashSet).add(shortName.invoke(obj));
            }
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public static CharSequence d(FirClassLikeSymbol firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return UtilsKt.render(firClassLikeSymbol.getFir());
    }

    public static Map e(FirExtensionDeclarationsSymbolProvider firExtensionDeclarationsSymbolProvider) {
        List<FirDeclarationGenerationExtension> list = firExtensionDeclarationsSymbolProvider.extensions;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FirDeclarationGenerationExtension firDeclarationGenerationExtension : list) {
            for (ClassId classId : firDeclarationGenerationExtension.getTopLevelClassIdsCache().getValue()) {
                Collection arrayList = (List) linkedHashMap.get(classId);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(classId, arrayList);
                }
                arrayList.add(firDeclarationGenerationExtension);
            }
        }
        return linkedHashMap;
    }

    public static Map f(FirExtensionDeclarationsSymbolProvider firExtensionDeclarationsSymbolProvider) {
        return firExtensionDeclarationsSymbolProvider.computeNamesGroupedByPackage(FirExtensionDeclarationsSymbolProvider$callableNamesInPackageCache$1$1.INSTANCE, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider$callableNamesInPackageCache$1$2
            public Object get(Object obj) {
                return ((CallableId) obj).getPackageName();
            }
        }, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider$callableNamesInPackageCache$1$3
            public Object get(Object obj) {
                return ((CallableId) obj).getCallableName();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v9, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public final FirClassLikeSymbol<?> generateClassLikeDeclaration(ClassId classId) {
        FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope;
        if (classId.isLocal()) {
            return null;
        }
        if (classId.isNestedClass()) {
            FirSymbolProvider symbolProvider = FirSymbolProviderKt.getSymbolProvider(getSession());
            ClassId outerClassId = classId.getOuterClassId();
            outerClassId.getClass();
            FirClassLikeSymbol<?> classLikeSymbolByClassId = symbolProvider.getClassLikeSymbolByClassId(outerClassId);
            FirClassSymbol firClassSymbol = classLikeSymbolByClassId instanceof FirClassSymbol ? (FirClassSymbol) classLikeSymbolByClassId : null;
            if (firClassSymbol == null || (firNestedClassifierScopeNestedClassifierScope = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(getSession(), (FirClass) firClassSymbol.getFir())) == null) {
                return null;
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Name shortClassName = classId.getShortClassName();
            final Function1 function1 = new Function1() { // from class: a75
                public final Object invoke(Object obj) {
                    return FirExtensionDeclarationsSymbolProvider.a(objectRef, (FirClassifierSymbol) obj);
                }
            };
            firNestedClassifierScopeNestedClassifierScope.processClassifiersByNameWithSubstitution(shortClassName, new Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit>() { // from class: org.jetbrains.kotlin.fir.extensions.FirExtensionDeclarationsSymbolProvider$generateClassLikeDeclaration$$inlined$processClassifiersByName$1
                public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
                    firClassifierSymbol.getClass();
                    coneSubstitutor.getClass();
                    function1.invoke(firClassifierSymbol);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
                    return Unit.INSTANCE;
                }
            });
            FirClassLikeSymbol<?> firClassLikeSymbol = (FirClassLikeSymbol) objectRef.element;
            if (firClassLikeSymbol == null) {
                return null;
            }
            if (firClassLikeSymbol.getOrigin().getGenerated() || Intrinsics.areEqual(firClassLikeSymbol.getOrigin(), FirDeclarationOrigin.Java.Source.INSTANCE)) {
                return firClassLikeSymbol;
            }
            return null;
        }
        List<FirDeclarationGenerationExtension> list = this.extensionsByTopLevelClassId.getValue().get(classId);
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (FirDeclarationGenerationExtension firDeclarationGenerationExtension : list) {
            FirClassLikeSymbol<?> firClassLikeSymbolGenerateTopLevelClassLikeDeclaration = firDeclarationGenerationExtension.generateTopLevelClassLikeDeclaration(classId);
            if (firClassLikeSymbolGenerateTopLevelClassLikeDeclaration != null) {
                FirGeneratedDeclarationsUtilsKt.setOwnerGenerator((FirClassLikeDeclaration) firClassLikeSymbolGenerateTopLevelClassLikeDeclaration.getFir(), firDeclarationGenerationExtension);
            } else {
                firClassLikeSymbolGenerateTopLevelClassLikeDeclaration = null;
            }
            if (firClassLikeSymbolGenerateTopLevelClassLikeDeclaration != null) {
                arrayList.add(firClassLikeSymbolGenerateTopLevelClassLikeDeclaration);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            GeneratedDeclarationValidationKt.validate(((FirClassLikeSymbol) it.next()).getFir());
        }
        int size = arrayList.size();
        if (size != 0) {
            if (size == 1) {
                return (FirClassLikeSymbol) CollectionsKt.first(arrayList);
            }
            StringBuilder sb = new StringBuilder("Multiple plugins generated classes with same classId ");
            sb.append(classId);
            v65.a(sb, 10, CollectionsKt.joinToString$default(arrayList, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: b75
                public final Object invoke(Object obj) {
                    return FirExtensionDeclarationsSymbolProvider.d((FirClassLikeSymbol) obj);
                }
            }, 30, (Object) null));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r4v4, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public final List<FirNamedFunctionSymbol> generateTopLevelFunctions(CallableId callableId) {
        List<FirDeclarationGenerationExtension> listEmptyList = this.extensionsByTopLevelCallableId.getValue().get(callableId);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listEmptyList.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((FirDeclarationGenerationExtension) it.next()).generateFunctions(callableId, null));
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            GeneratedDeclarationValidationKt.validate(((FirNamedFunctionSymbol) it2.next()).getFir());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r4v4, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public final List<FirPropertySymbol> generateTopLevelProperties(CallableId callableId) {
        List<FirDeclarationGenerationExtension> listEmptyList = this.extensionsByTopLevelCallableId.getValue().get(callableId);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listEmptyList.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((FirDeclarationGenerationExtension) it.next()).generateProperties(callableId, null));
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            GeneratedDeclarationValidationKt.validate(((FirPropertySymbol) it2.next()).getFir());
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirClassLikeSymbol<?> getClassLikeSymbolByClassId(ClassId classId) {
        classId.getClass();
        if (classId.isNestedClass() || getSymbolNamesProvider().mayHaveTopLevelClassifier(classId)) {
            return (FirClassLikeSymbol) this.classCache.getValue(classId, null);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirSymbolNamesProvider getSymbolNamesProvider() {
        return this.symbolNamesProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        if (getSymbolNamesProvider().mayHaveTopLevelCallable(packageFqName, name)) {
            CallableId callableId = new CallableId(packageFqName, name);
            List<FirCallableSymbol<?>> list = destination;
            CollectionsKt.addAll(list, (Iterable) this.functionCache.getValue(callableId, null));
            CollectionsKt.addAll(list, (Iterable) this.propertyCache.getValue(callableId, null));
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        if (getSymbolNamesProvider().mayHaveTopLevelCallable(packageFqName, name)) {
            CollectionsKt.addAll(destination, (Iterable) this.functionCache.getValue(new CallableId(packageFqName, name), null));
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        if (getSymbolNamesProvider().mayHaveTopLevelCallable(packageFqName, name)) {
            CollectionsKt.addAll(destination, (Iterable) this.propertyCache.getValue(new CallableId(packageFqName, name), null));
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return ((Boolean) this.packageCache.getValue(fqName, null)).booleanValue();
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirExtensionDeclarationsSymbolProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", "createIfNeeded", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionDeclarationsSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirExtensionDeclarationsSymbolProvider createIfNeeded(FirSession session) {
            session.getClass();
            List<FirDeclarationGenerationExtension> declarationGenerators = FirDeclarationGenerationExtensionKt.getDeclarationGenerators(FirExtensionServiceKt.getExtensionService(session));
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (declarationGenerators.isEmpty()) {
                return null;
            }
            return new FirExtensionDeclarationsSymbolProvider(session, FirCachesFactoryKt.getFirCachesFactory(session), declarationGenerators, defaultConstructorMarker);
        }

        private Companion() {
        }
    }

    public /* synthetic */ FirExtensionDeclarationsSymbolProvider(FirSession firSession, FirCachesFactory firCachesFactory, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firCachesFactory, list);
    }
}
