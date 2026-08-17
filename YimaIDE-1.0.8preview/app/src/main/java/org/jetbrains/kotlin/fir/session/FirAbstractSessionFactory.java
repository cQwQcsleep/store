package org.jetbrains.kotlin.fir.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.FrontendConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtRegisteredDiagnosticFactoriesStorage;
import org.jetbrains.kotlin.fir.FirBinaryDependenciesModuleData;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.checkers.CheckersContainersKt;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProvider;
import org.jetbrains.kotlin.fir.java.FirCliSession;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceLogger;
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirBuiltinSyntheticFunctionInterfaceProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirCachingCompositeSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirCommonDeclarationsMappingSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirEmptySymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirExtensionSyntheticFunctionInterfaceProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirExtensionSyntheticFunctionInterfaceProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirFallbackBuiltinSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirLibrarySessionProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirProviderImpl;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001BB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J3\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0004¢\u0006\u0002\u0010\u000fJ3\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\r2\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\t\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0017J3\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\r2\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\t\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0017Jc\u0010\u0019\u001a\u00020\u00062\u0006\u0010\t\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u001d\u001a\u00020\u001e2\u001e\u0010\u001f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\r0 H\u0004¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u0016H$J\u0019\u0010#\u001a\u00020$*\u00020\u00062\u0006\u0010%\u001a\u00028\u0000H&¢\u0006\u0002\u0010&J|\u0010'\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00028\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001e2\u0017\u0010+\u001a\u0013\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020$0,¢\u0006\u0002\b.2&\u0010\u001f\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u000100\u0012\u0004\u0012\u0002010/H\u0004¢\u0006\u0002\u00102J\u0018\u00103\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000bH$J\f\u00104\u001a\u00020$*\u00020-H&J\f\u00105\u001a\u00020$*\u00020-H&J\u0019\u00106\u001a\u00020$*\u00020\u00062\u0006\u0010%\u001a\u00028\u0000H&¢\u0006\u0002\u0010&J(\u0010;\u001a\u00020<2\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u001eH\u0002J\u0010\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00110\r*\u00020\u0011J\u0012\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00110\r*\u00020\u0011H\u0002J&\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00110\r*\u00020\u00112\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001e0,H\u0002J\u001e\u0010@\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00062\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00110\rH\u0002R\u0012\u00107\u001a\u00020\u001eX¤\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0012\u0010:\u001a\u00020\u001eX¤\u0004¢\u0006\u0006\u001a\u0004\b:\u00109¨\u0006C"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirAbstractSessionFactory;", "CONTEXT", Argument.Delimiters.none, "<init>", "()V", "createSharedLibrarySession", "Lorg/jetbrains/kotlin/fir/FirSession;", "mainModuleName", "Lorg/jetbrains/kotlin/name/Name;", "context", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "extensionRegistrars", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;", "(Lorg/jetbrains/kotlin/name/Name;Ljava/lang/Object;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Ljava/util/List;)Lorg/jetbrains/kotlin/fir/FirSession;", "createSharedProviders", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Ljava/lang/Object;)Ljava/util/List;", "createPlatformSpecificSharedProviders", "createLibrarySession", "sharedLibrarySession", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "createSeparateSharedProvidersInHmppCompilation", Argument.Delimiters.none, "createProviders", "Lkotlin/Function2;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Ljava/util/List;ZLkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/FirSession;", "createKotlinScopeProviderForLibrarySession", "registerLibrarySessionComponents", Argument.Delimiters.none, "c", "(Lorg/jetbrains/kotlin/fir/FirSession;Ljava/lang/Object;)V", "createSourceSession", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "isForLeafHmppModule", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", "Lkotlin/ExtensionFunctionType;", "Lkotlin/Function4;", "Lorg/jetbrains/kotlin/fir/extensions/FirSwitchableExtensionDeclarationsSymbolProvider;", "Lorg/jetbrains/kotlin/fir/session/FirAbstractSessionFactory$SourceProviders;", "(Lorg/jetbrains/kotlin/fir/FirModuleData;Ljava/lang/Object;Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)Lorg/jetbrains/kotlin/fir/FirSession;", "createKotlinScopeProviderForSourceSession", "registerPlatformCheckers", "registerExtraPlatformCheckers", "registerSourceSessionComponents", "requiresSpecialSetupOfSourceProvidersInHmppCompilation", "getRequiresSpecialSetupOfSourceProvidersInHmppCompilation", "()Z", "isFactoryForMetadataCompilation", "computeDependencyProviderList", "Lorg/jetbrains/kotlin/fir/session/StructuredProviders;", "flattenAndFilterOwnProviders", "flatten", "predicate", "createCachingCompositeProviderIfNeeded", "providers", "SourceProviders", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractSessionFactory<CONTEXT> {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirSession.Kind.values().length];
            try {
                iArr[FirSession.Kind.Library.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirSession.Kind.Source.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static boolean a(FirSession firSession, FirSymbolProvider firSymbolProvider) {
        firSymbolProvider.getClass();
        if (firSession != null && firSymbolProvider.getSession().getKind() == FirSession.Kind.Source && Intrinsics.areEqual(firSymbolProvider.getSession(), firSession)) {
            return true;
        }
        return firSession == null && firSymbolProvider.getSession().getKind() == FirSession.Kind.Library;
    }

    public static boolean b(FirSymbolProvider firSymbolProvider) {
        firSymbolProvider.getClass();
        return true;
    }

    public static boolean c(FirSymbolProvider firSymbolProvider) {
        firSymbolProvider.getClass();
        return firSymbolProvider instanceof FirFallbackBuiltinSymbolProvider;
    }

    private final StructuredProviders computeDependencyProviderList(FirSession session, FirModuleData moduleData, LanguageVersionSettings languageVersionSettings, boolean isForLeafHmppModule) {
        List arrayList;
        FirSymbolProvider sharedProvider;
        List<FirSymbolProvider> sourceProviders;
        List listPlus = CollectionsKt.plus(CollectionsKt.plus(moduleData.getDependencies(), moduleData.getFriendDependencies()), moduleData.getAllDependsOnDependencies());
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listPlus) {
            if (hashSet.add(((FirModuleData) obj).getSession())) {
                arrayList2.add(obj);
            }
        }
        List<FirModuleData> listSortedWith = CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory$computeDependencyProviderList$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((FirModuleData) t).getSession().getKind(), ((FirModuleData) t2).getSession().getKind());
            }
        });
        ArrayList<Pair> arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSortedWith, 10));
        for (FirModuleData firModuleData : listSortedWith) {
            arrayList3.add(TuplesKt.to(firModuleData, StructuredProvidersKt.getStructuredProviders(firModuleData.getSession())));
        }
        if (!((Boolean) languageVersionSettings.getFlag(AnalysisFlags.INSTANCE.getHierarchicalMultiplatformCompilation())).booleanValue() || !getRequiresSpecialSetupOfSourceProvidersInHmppCompilation()) {
            arrayList = new ArrayList();
            for (Pair pair : arrayList3) {
                FirModuleData firModuleData2 = (FirModuleData) pair.component1();
                StructuredProviders structuredProviders = (StructuredProviders) pair.component2();
                int i = WhenMappings.$EnumSwitchMapping$0[firModuleData2.getSession().getKind().ordinal()];
                if (i == 1) {
                    List<FirSymbolProvider> dependencyProviders = structuredProviders.getDependencyProviders();
                    if (!structuredProviders.getSourceProviders().isEmpty()) {
                        k2d.a("Check failed.");
                        return null;
                    }
                    sourceProviders = dependencyProviders;
                } else {
                    if (i != 2) {
                        bu8.a();
                        return null;
                    }
                    sourceProviders = structuredProviders.getSourceProviders();
                }
                CollectionsKt.addAll(arrayList, sourceProviders);
            }
            for (Pair pair2 : arrayList3) {
                if (((FirModuleData) pair2.component1()).getSession().getKind() == FirSession.Kind.Library) {
                    sharedProvider = ((StructuredProviders) pair2.getSecond()).getSharedProvider();
                }
            }
            hb9.a("Collection contains no element matching the predicate.");
            return null;
        }
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        for (Pair pair3 : arrayList3) {
            FirModuleData firModuleData3 = (FirModuleData) pair3.component1();
            StructuredProviders structuredProviders2 = (StructuredProviders) pair3.component2();
            int i2 = WhenMappings.$EnumSwitchMapping$0[firModuleData3.getSession().getKind().ordinal()];
            if (i2 == 1) {
                List<FirSymbolProvider> dependencyProviders2 = structuredProviders2.getDependencyProviders();
                if (!structuredProviders2.getSourceProviders().isEmpty()) {
                    k2d.a("Check failed.");
                    return null;
                }
                CollectionsKt.addAll(arrayList5, dependencyProviders2);
            } else {
                if (i2 != 2) {
                    bu8.a();
                    return null;
                }
                CollectionsKt.addAll(arrayList6, structuredProviders2.getSourceProviders());
                List<FirSymbolProvider> dependencyProviders3 = structuredProviders2.getDependencyProviders();
                ArrayList arrayList7 = new ArrayList();
                for (FirSymbolProvider firSymbolProvider : dependencyProviders3) {
                    CollectionsKt.addAll(arrayList7, firSymbolProvider.getSession().getKind() == FirSession.Kind.Library ? CollectionsKt.listOf(firSymbolProvider) : firSymbolProvider instanceof FirCommonDeclarationsMappingSymbolProvider ? flatten(((FirCommonDeclarationsMappingSymbolProvider) firSymbolProvider).getPlatformSymbolProvider()) : CollectionsKt.emptyList());
                }
                CollectionsKt.addAll(arrayList4, arrayList7);
            }
        }
        if (!arrayList4.isEmpty()) {
            Iterator it = arrayList4.iterator();
            while (it.hasNext()) {
                if (((FirSymbolProvider) it.next()) instanceof FirFallbackBuiltinSymbolProvider) {
                    if (!isForLeafHmppModule) {
                        CollectionsKt.removeAll(arrayList5, new Function1() { // from class: vx4
                            public final Object invoke(Object obj2) {
                                return Boolean.valueOf(FirAbstractSessionFactory.c((FirSymbolProvider) obj2));
                            }
                        });
                        break;
                    }
                    break;
                }
            }
        }
        FirCommonDeclarationsMappingSymbolProvider firCommonDeclarationsMappingSymbolProvider = new FirCommonDeclarationsMappingSymbolProvider(session, createCachingCompositeProviderIfNeeded(session, CollectionsKt.distinct(arrayList4)), createCachingCompositeProviderIfNeeded(session, arrayList5));
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.addAll(arrayList6);
        listCreateListBuilder.add(firCommonDeclarationsMappingSymbolProvider);
        arrayList = CollectionsKt.build(listCreateListBuilder);
        sharedProvider = new FirEmptySymbolProvider(session);
        return new StructuredProviders(CollectionsKt.emptyList(), arrayList, sharedProvider);
    }

    private final FirSymbolProvider createCachingCompositeProviderIfNeeded(FirSession session, List<? extends FirSymbolProvider> providers) {
        int size = providers.size();
        if (size != 0) {
            return size != 1 ? new FirCachingCompositeSymbolProvider(session, providers, false, 4, null) : (FirSymbolProvider) CollectionsKt.single(providers);
        }
        return new FirEmptySymbolProvider(session);
    }

    private final List<FirSymbolProvider> createSharedProviders(FirSession session, FirModuleData moduleData, FirKotlinScopeProvider scopeProvider, CONTEXT context) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(new FirBuiltinSyntheticFunctionInterfaceProvider(session, moduleData, scopeProvider, false, 8, null));
        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(listCreateListBuilder, FirExtensionSyntheticFunctionInterfaceProvider.INSTANCE.createIfNeeded(session, moduleData, scopeProvider));
        listCreateListBuilder.addAll(createPlatformSpecificSharedProviders(session, moduleData, scopeProvider, context));
        return CollectionsKt.build(listCreateListBuilder);
    }

    private final List<FirSymbolProvider> flatten(FirSymbolProvider firSymbolProvider) {
        return flatten(firSymbolProvider, new Function1() { // from class: ux4
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirAbstractSessionFactory.b((FirSymbolProvider) obj));
            }
        });
    }

    private static final void flatten$collectProviders(FirSymbolProvider firSymbolProvider, Function1<? super FirSymbolProvider, Boolean> function1, List<FirSymbolProvider> list) {
        if (firSymbolProvider instanceof FirCachingCompositeSymbolProvider) {
            Iterator<FirSymbolProvider> it = ((FirCachingCompositeSymbolProvider) firSymbolProvider).getProviders().iterator();
            while (it.hasNext()) {
                flatten$collectProviders(it.next(), function1, list);
            }
        } else if (((Boolean) function1.invoke(firSymbolProvider)).booleanValue()) {
            list.add(firSymbolProvider);
        }
    }

    public abstract FirKotlinScopeProvider createKotlinScopeProviderForLibrarySession();

    public abstract FirKotlinScopeProvider createKotlinScopeProviderForSourceSession(FirModuleData moduleData, LanguageVersionSettings languageVersionSettings);

    public final FirSession createLibrarySession(CONTEXT context, FirSession sharedLibrarySession, ModuleDataProvider moduleDataProvider, LanguageVersionSettings languageVersionSettings, List<? extends FirExtensionRegistrar> extensionRegistrars, boolean createSeparateSharedProvidersInHmppCompilation, Function2<? super FirSession, ? super FirKotlinScopeProvider, ? extends List<? extends FirSymbolProvider>> createProviders) {
        sharedLibrarySession.getClass();
        moduleDataProvider.getClass();
        languageVersionSettings.getClass();
        extensionRegistrars.getClass();
        createProviders.getClass();
        FirSession firCliSession = new FirCliSession(FirSession.Kind.Library);
        Iterator<T> it = moduleDataProvider.getAllModuleData().iterator();
        while (it.hasNext()) {
            ((FirModuleData) it.next()).bindSession(firCliSession);
        }
        ComponentsContainersKt.registerCliCompilerAndCommonComponents(firCliSession, languageVersionSettings, isFactoryForMetadataCompilation());
        registerLibrarySessionComponents(firCliSession, context);
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirBuiltinSyntheticFunctionInterfaceProvider.class), FirExtensionSyntheticFunctionInterfaceProviderKt.getSyntheticFunctionInterfacesSymbolProvider(sharedLibrarySession));
        FirKotlinScopeProvider firKotlinScopeProviderCreateKotlinScopeProviderForLibrarySession = createKotlinScopeProviderForLibrarySession();
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirKotlinScopeProvider.class), firKotlinScopeProviderCreateKotlinScopeProviderForLibrarySession);
        FirSessionConfigurator firSessionConfigurator = new FirSessionConfigurator(firCliSession);
        Iterator<? extends FirExtensionRegistrar> it2 = extensionRegistrars.iterator();
        while (it2.hasNext()) {
            firSessionConfigurator.registerExtensions(it2.next().configure());
        }
        firSessionConfigurator.configure();
        ComponentsContainersKt.registerCommonComponentsAfterExtensionsAreConfigured(firCliSession);
        boolean z = ((Boolean) languageVersionSettings.getFlag(AnalysisFlags.INSTANCE.getHierarchicalMultiplatformCompilation())).booleanValue() && createSeparateSharedProvidersInHmppCompilation;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.addAll((Collection) createProviders.invoke(firCliSession, firKotlinScopeProviderCreateKotlinScopeProviderForLibrarySession));
        if (z) {
            listCreateListBuilder.addAll(createSharedProviders(firCliSession, moduleDataProvider.getRegularDependenciesModuleData(), firKotlinScopeProviderCreateKotlinScopeProviderForLibrarySession, context));
        }
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        firCliSession.register(Reflection.getOrCreateKotlinClass(StructuredProviders.class), new StructuredProviders(CollectionsKt.emptyList(), listBuild, z ? new FirEmptySymbolProvider(firCliSession) : FirSymbolProviderKt.getSymbolProvider(sharedLibrarySession)));
        if (!z) {
            listBuild = CollectionsKt.plus(listBuild, flattenAndFilterOwnProviders(FirSymbolProviderKt.getSymbolProvider(sharedLibrarySession)));
        }
        FirCachingCompositeSymbolProvider firCachingCompositeSymbolProvider = new FirCachingCompositeSymbolProvider(firCliSession, listBuild, false, 4, null);
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirSymbolProvider.class), firCachingCompositeSymbolProvider);
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirProvider.class), new FirLibrarySessionProvider(firCachingCompositeSymbolProvider));
        return firCliSession;
    }

    public abstract List<FirSymbolProvider> createPlatformSpecificSharedProviders(FirSession session, FirModuleData moduleData, FirKotlinScopeProvider scopeProvider, CONTEXT context);

    public final FirSession createSharedLibrarySession(Name mainModuleName, CONTEXT context, LanguageVersionSettings languageVersionSettings, List<? extends FirExtensionRegistrar> extensionRegistrars) {
        mainModuleName.getClass();
        languageVersionSettings.getClass();
        extensionRegistrars.getClass();
        FirSession firCliSession = new FirCliSession(FirSession.Kind.Library);
        ComponentsContainersKt.registerCliCompilerAndCommonComponents(firCliSession, languageVersionSettings, isFactoryForMetadataCompilation());
        registerLibrarySessionComponents(firCliSession, context);
        FirKotlinScopeProvider firKotlinScopeProviderCreateKotlinScopeProviderForLibrarySession = createKotlinScopeProviderForLibrarySession();
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirKotlinScopeProvider.class), firKotlinScopeProviderCreateKotlinScopeProviderForLibrarySession);
        Name nameSpecial = Name.special("<shared dependencies of " + mainModuleName.asString() + '>');
        nameSpecial.getClass();
        FirBinaryDependenciesModuleData firBinaryDependenciesModuleData = new FirBinaryDependenciesModuleData(nameSpecial, null, 2, null);
        firBinaryDependenciesModuleData.bindSession(firCliSession);
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirModuleData.class), firBinaryDependenciesModuleData);
        FirSessionConfigurator firSessionConfigurator = new FirSessionConfigurator(firCliSession);
        Iterator<? extends FirExtensionRegistrar> it = extensionRegistrars.iterator();
        while (it.hasNext()) {
            firSessionConfigurator.registerExtensions(it.next().configure());
        }
        firSessionConfigurator.configure();
        ComponentsContainersKt.registerCommonComponentsAfterExtensionsAreConfigured(firCliSession);
        FirCachingCompositeSymbolProvider firCachingCompositeSymbolProvider = new FirCachingCompositeSymbolProvider(firCliSession, createSharedProviders(firCliSession, firBinaryDependenciesModuleData, firKotlinScopeProviderCreateKotlinScopeProviderForLibrarySession, context), false, 4, null);
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirSymbolProvider.class), firCachingCompositeSymbolProvider);
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirProvider.class), new FirLibrarySessionProvider(firCachingCompositeSymbolProvider));
        return firCliSession;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final FirSession createSourceSession(FirModuleData moduleData, CONTEXT context, List<? extends FirExtensionRegistrar> extensionRegistrars, CompilerConfiguration configuration, boolean isForLeafHmppModule, Function1<? super FirSessionConfigurator, Unit> init, Function4<? super FirSession, ? super FirKotlinScopeProvider, ? super FirSymbolProvider, ? super FirSwitchableExtensionDeclarationsSymbolProvider, SourceProviders> createProviders) {
        moduleData.getClass();
        extensionRegistrars.getClass();
        configuration.getClass();
        init.getClass();
        createProviders.getClass();
        LanguageVersionSettings languageVersionSettings = CommonConfigurationKeysKt.getLanguageVersionSettings(configuration);
        FirSession firCliSession = new FirCliSession(FirSession.Kind.Source);
        moduleData.bindSession(firCliSession);
        ComponentsContainersKt.registerModuleData(firCliSession, moduleData);
        if (CommonConfigurationKeysKt.getDumpInferenceLogs(configuration)) {
            firCliSession.register(Reflection.getOrCreateKotlinClass(FirInferenceLogger.class), new FirInferenceLogger());
        }
        ComponentsContainersKt.registerCliCompilerAndCommonComponents(firCliSession, languageVersionSettings, isFactoryForMetadataCompilation());
        KtRegisteredDiagnosticFactoriesStorage diagnosticFactoriesStorage = FrontendConfigurationKeysKt.getDiagnosticFactoriesStorage(configuration);
        if (diagnosticFactoriesStorage == null) {
            k2d.a("diagnosticFactoriesStorage is not registered in the configuration");
            return null;
        }
        ComponentsContainersKt.registerResolveComponents(firCliSession, diagnosticFactoriesStorage, CommonConfigurationKeysKt.getLookupTracker(configuration), CommonConfigurationKeysKt.getEnumWhenTracker(configuration), CommonConfigurationKeysKt.getImportTracker(configuration), CommonConfigurationKeysKt.getFileMappingTracker(configuration));
        ComponentsContainersKt.registerCliCompilerOnlyResolveComponents(firCliSession);
        registerSourceSessionComponents(firCliSession, context);
        FirKotlinScopeProvider firKotlinScopeProviderCreateKotlinScopeProviderForSourceSession = createKotlinScopeProviderForSourceSession(moduleData, languageVersionSettings);
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirKotlinScopeProvider.class), firKotlinScopeProviderCreateKotlinScopeProviderForSourceSession);
        FirProviderImpl firProviderImpl = new FirProviderImpl(firCliSession, firKotlinScopeProviderCreateKotlinScopeProviderForSourceSession);
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirProvider.class), firProviderImpl);
        FirSessionConfigurator firSessionConfigurator = new FirSessionConfigurator(firCliSession);
        CheckersContainersKt.registerCommonCheckers(firSessionConfigurator);
        registerPlatformCheckers(firSessionConfigurator);
        if (CommonConfigurationKeysKt.getUseFirExtraCheckers(configuration)) {
            registerExtraPlatformCheckers(firSessionConfigurator);
        }
        Iterator<? extends FirExtensionRegistrar> it = extensionRegistrars.iterator();
        while (it.hasNext()) {
            firSessionConfigurator.registerExtensions(it.next().configure());
        }
        init.invoke(firSessionConfigurator);
        firSessionConfigurator.configure();
        ComponentsContainersKt.registerCommonComponentsAfterExtensionsAreConfigured(firCliSession);
        StructuredProviders structuredProvidersComputeDependencyProviderList = computeDependencyProviderList(firCliSession, moduleData, languageVersionSettings, isForLeafHmppModule);
        FirSessionComponent firSessionComponentCreateIfNeeded = FirSwitchableExtensionDeclarationsSymbolProvider.INSTANCE.createIfNeeded(firCliSession);
        SourceProviders sourceProviders = (SourceProviders) createProviders.invoke(firCliSession, firKotlinScopeProviderCreateKotlinScopeProviderForSourceSession, firProviderImpl.getSymbolProvider(), firSessionComponentCreateIfNeeded);
        List<FirSymbolProvider> listComponent1 = sourceProviders.component1();
        FirSymbolProvider additionalOptionalAnnotationsProvider = sourceProviders.getAdditionalOptionalAnnotationsProvider();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.addAll(structuredProvidersComputeDependencyProviderList.getDependencyProviders());
        if (!isForLeafHmppModule) {
            List list = listCreateListBuilder;
            Iterator<T> it2 = moduleData.getDependsOnDependencies().iterator();
            while (it2.hasNext()) {
                CollectionsKt.addAll(list, StructuredProvidersKt.getStructuredProviders(((FirModuleData) it2.next()).getSession()).getDependencyProviders());
            }
        }
        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(listCreateListBuilder, additionalOptionalAnnotationsProvider);
        StructuredProviders structuredProviders = new StructuredProviders(listComponent1, CollectionsKt.build(listCreateListBuilder), structuredProvidersComputeDependencyProviderList.getSharedProvider());
        firCliSession.register(Reflection.getOrCreateKotlinClass(StructuredProviders.class), structuredProviders);
        List listCreateListBuilder2 = CollectionsKt.createListBuilder();
        List list2 = listCreateListBuilder2;
        Iterator<T> it3 = structuredProviders.getDependencyProviders().iterator();
        while (it3.hasNext()) {
            CollectionsKt.addAll(list2, flattenAndFilterOwnProviders((FirSymbolProvider) it3.next()));
        }
        listCreateListBuilder2.addAll(flattenAndFilterOwnProviders(structuredProviders.getSharedProvider()));
        List listDistinct = CollectionsKt.distinct(CollectionsKt.build(listCreateListBuilder2));
        firCliSession.register(Reflection.getOrCreateKotlinClass(FirSymbolProvider.class), new FirCachingCompositeSymbolProvider(firCliSession, CollectionsKt.plus(structuredProviders.getSourceProviders(), listDistinct), firSessionComponentCreateIfNeeded != null));
        if (firSessionComponentCreateIfNeeded != null) {
            firCliSession.register(Reflection.getOrCreateKotlinClass(FirSwitchableExtensionDeclarationsSymbolProvider.class), firSessionComponentCreateIfNeeded);
        }
        firCliSession.register(FirSymbolProviderKt.DEPENDENCIES_SYMBOL_PROVIDER_QUALIFIED_KEY, new FirCachingCompositeSymbolProvider(firCliSession, listDistinct, false, 4, null));
        return firCliSession;
    }

    public final List<FirSymbolProvider> flattenAndFilterOwnProviders(FirSymbolProvider firSymbolProvider) {
        firSymbolProvider.getClass();
        final FirSession session = firSymbolProvider.getSession();
        if (session.getKind() != FirSession.Kind.Source) {
            session = null;
        }
        return flatten(firSymbolProvider, new Function1() { // from class: wx4
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirAbstractSessionFactory.a(session, (FirSymbolProvider) obj));
            }
        });
    }

    public abstract boolean getRequiresSpecialSetupOfSourceProvidersInHmppCompilation();

    public abstract boolean isFactoryForMetadataCompilation();

    public abstract void registerExtraPlatformCheckers(FirSessionConfigurator firSessionConfigurator);

    public abstract void registerLibrarySessionComponents(FirSession firSession, CONTEXT context);

    public abstract void registerPlatformCheckers(FirSessionConfigurator firSessionConfigurator);

    public abstract void registerSourceSessionComponents(FirSession firSession, CONTEXT context);

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0084\b\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0004HÆ\u0003J%\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirAbstractSessionFactory$SourceProviders;", Argument.Delimiters.none, "sourceProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "additionalOptionalAnnotationsProvider", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;)V", "getSourceProviders", "()Ljava/util/List;", "getAdditionalOptionalAnnotationsProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class SourceProviders {
        private final FirSymbolProvider additionalOptionalAnnotationsProvider;
        private final List<FirSymbolProvider> sourceProviders;

        /* JADX WARN: Multi-variable type inference failed */
        public SourceProviders(List<? extends FirSymbolProvider> list, FirSymbolProvider firSymbolProvider) {
            list.getClass();
            this.sourceProviders = list;
            this.additionalOptionalAnnotationsProvider = firSymbolProvider;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SourceProviders copy$default(SourceProviders sourceProviders, List list, FirSymbolProvider firSymbolProvider, int i, Object obj) {
            if ((i & 1) != 0) {
                list = sourceProviders.sourceProviders;
            }
            if ((i & 2) != 0) {
                firSymbolProvider = sourceProviders.additionalOptionalAnnotationsProvider;
            }
            return sourceProviders.copy(list, firSymbolProvider);
        }

        public final List<FirSymbolProvider> component1() {
            return this.sourceProviders;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final FirSymbolProvider getAdditionalOptionalAnnotationsProvider() {
            return this.additionalOptionalAnnotationsProvider;
        }

        public final SourceProviders copy(List<? extends FirSymbolProvider> sourceProviders, FirSymbolProvider additionalOptionalAnnotationsProvider) {
            sourceProviders.getClass();
            return new SourceProviders(sourceProviders, additionalOptionalAnnotationsProvider);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SourceProviders)) {
                return false;
            }
            SourceProviders sourceProviders = (SourceProviders) other;
            return Intrinsics.areEqual(this.sourceProviders, sourceProviders.sourceProviders) && Intrinsics.areEqual(this.additionalOptionalAnnotationsProvider, sourceProviders.additionalOptionalAnnotationsProvider);
        }

        public final FirSymbolProvider getAdditionalOptionalAnnotationsProvider() {
            return this.additionalOptionalAnnotationsProvider;
        }

        public final List<FirSymbolProvider> getSourceProviders() {
            return this.sourceProviders;
        }

        public int hashCode() {
            int iHashCode = this.sourceProviders.hashCode() * 31;
            FirSymbolProvider firSymbolProvider = this.additionalOptionalAnnotationsProvider;
            return iHashCode + (firSymbolProvider == null ? 0 : firSymbolProvider.hashCode());
        }

        public String toString() {
            return "SourceProviders(sourceProviders=" + this.sourceProviders + ", additionalOptionalAnnotationsProvider=" + this.additionalOptionalAnnotationsProvider + ')';
        }

        public /* synthetic */ SourceProviders(List list, FirSymbolProvider firSymbolProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(list, (i & 2) != 0 ? null : firSymbolProvider);
        }
    }

    private final List<FirSymbolProvider> flatten(FirSymbolProvider firSymbolProvider, Function1<? super FirSymbolProvider, Boolean> function1) {
        ArrayList arrayList = new ArrayList();
        flatten$collectProviders(firSymbolProvider, function1, arrayList);
        return arrayList;
    }
}
