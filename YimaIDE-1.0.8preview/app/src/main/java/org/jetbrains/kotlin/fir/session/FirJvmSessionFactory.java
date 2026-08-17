package org.jetbrains.kotlin.fir.session;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeysKt;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.checkers.CheckersContainersKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.deserialization.SingleModuleDataProvider;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProvider;
import org.jetbrains.kotlin.fir.java.FirJvmTargetProvider;
import org.jetbrains.kotlin.fir.java.JavaSymbolProvider;
import org.jetbrains.kotlin.fir.java.deserialization.FirJvmBuiltinsSymbolProvider;
import org.jetbrains.kotlin.fir.java.deserialization.FirJvmClasspathBuiltinSymbolProvider;
import org.jetbrains.kotlin.fir.java.deserialization.JvmClassFileBasedSymbolProvider;
import org.jetbrains.kotlin.fir.java.deserialization.OptionalAnnotationClassesProvider;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirCloneableSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirFallbackBuiltinSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.scopes.JvmMappedScopesKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.session.FirJvmSessionFactory;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001=B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J,\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002J.\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\n2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0002H\u0014J4\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002J\b\u0010\u001a\u001a\u00020\u0015H\u0014J\u0014\u0010\u001b\u001a\u00020\u001c*\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0002H\u0016Js\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 2\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010#0\"2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001c0\"¢\u0006\u0002\b+J\u0018\u0010,\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\rH\u0014J\f\u0010-\u001a\u00020\u001c*\u00020*H\u0016J\f\u0010.\u001a\u00020\u001c*\u00020*H\u0016J\u0014\u0010/\u001a\u00020\u001c*\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0002H\u0016J\"\u00104\u001a\u0004\u0018\u00010\u00102\u0006\u00105\u001a\u0002062\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u0015H\u0002J(\u00108\u001a\u0002092\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u00152\u0006\u0010;\u001a\u00020<H\u0002R\u0014\u00100\u001a\u00020'8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u00020'8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b3\u00102¨\u0006>"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirJvmSessionFactory;", "Lorg/jetbrains/kotlin/fir/session/FirAbstractSessionFactory;", "Lorg/jetbrains/kotlin/fir/session/FirJvmSessionFactory$Context;", "<init>", "()V", "createSharedLibrarySession", "Lorg/jetbrains/kotlin/fir/FirSession;", "mainModuleName", "Lorg/jetbrains/kotlin/name/Name;", "extensionRegistrars", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "context", "createPlatformSpecificSharedProviders", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "createLibrarySession", "sharedLibrarySession", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "createKotlinScopeProviderForLibrarySession", "registerLibrarySessionComponents", Argument.Delimiters.none, "c", "createSourceSession", "javaSourcesScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "createIncrementalCompilationSymbolProviders", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/session/FirJvmIncrementalCompilationSymbolProviders;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "needRegisterJavaElementFinder", Argument.Delimiters.none, "isForLeafHmppModule", "init", "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", "Lkotlin/ExtensionFunctionType;", "createKotlinScopeProviderForSourceSession", "registerPlatformCheckers", "registerExtraPlatformCheckers", "registerSourceSessionComponents", "requiresSpecialSetupOfSourceProvidersInHmppCompilation", "getRequiresSpecialSetupOfSourceProvidersInHmppCompilation", "()Z", "isFactoryForMetadataCompilation", "initializeForStdlibIfNeeded", "projectEnvironment", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;", "kotlinScopeProvider", "initializeBuiltinsProvider", "Lorg/jetbrains/kotlin/fir/java/deserialization/FirJvmBuiltinsSymbolProvider;", "builtinsModuleData", "kotlinClassFinder", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;", "Context", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmSessionFactory extends FirAbstractSessionFactory<Context> {
    public static final FirJvmSessionFactory INSTANCE = new FirJvmSessionFactory();

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.session.FirJvmSessionFactory$createKotlinScopeProviderForLibrarySession$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends AdaptedFunctionReference implements Function5<FirClass, FirContainingNamesAwareScope, FirSession, ScopeSession, FirResolvePhase, FirContainingNamesAwareScope> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(5, JvmMappedScopesKt.class, "wrapScopeWithJvmMapped", "wrapScopeWithJvmMapped(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;Z)Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", 1);
        }

        public final FirContainingNamesAwareScope invoke(FirClass firClass, FirContainingNamesAwareScope firContainingNamesAwareScope, FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase) {
            firClass.getClass();
            firContainingNamesAwareScope.getClass();
            firSession.getClass();
            scopeSession.getClass();
            return JvmMappedScopesKt.wrapScopeWithJvmMapped$default(firClass, firContainingNamesAwareScope, firSession, scopeSession, firResolvePhase, false, 32, null);
        }
    }

    private FirJvmSessionFactory() {
    }

    public static InputStream d(KotlinClassFinder kotlinClassFinder, FqName fqName) {
        fqName.getClass();
        return kotlinClassFinder.findBuiltInsData(fqName);
    }

    public static FirContainingNamesAwareScope f(LanguageVersionSettings languageVersionSettings, FirClass firClass, FirContainingNamesAwareScope firContainingNamesAwareScope, FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase) {
        firClass.getClass();
        firContainingNamesAwareScope.getClass();
        firSession.getClass();
        scopeSession.getClass();
        return JvmMappedScopesKt.wrapScopeWithJvmMapped(firClass, firContainingNamesAwareScope, firSession, scopeSession, firResolvePhase, !((Boolean) languageVersionSettings.getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue());
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0031  */
    public static List g(Context context, ModuleDataProvider moduleDataProvider, LanguageVersionSettings languageVersionSettings, FirSession firSession, FirKotlinScopeProvider firKotlinScopeProvider) {
        AbstractProjectFileSearchScope librariesScope;
        firSession.getClass();
        firKotlinScopeProvider.getClass();
        AbstractProjectEnvironment projectEnvironment = context.getProjectEnvironment();
        FirModuleData firModuleData = (FirModuleData) CollectionsKt.last(moduleDataProvider.getAllModuleData());
        Set<Path> moduleDataPaths = moduleDataProvider.getModuleDataPaths(firModuleData);
        if (moduleDataPaths == null || (librariesScope = projectEnvironment.getSearchScopeByClassPath(moduleDataPaths)) == null) {
            librariesScope = context.getLibrariesScope();
        } else {
            if (librariesScope.isEmpty()) {
                librariesScope = null;
            }
            if (librariesScope == null) {
                librariesScope = context.getLibrariesScope();
            }
        }
        KotlinClassFinder kotlinClassFinder = projectEnvironment.getKotlinClassFinder(librariesScope);
        return CollectionsKt.listOfNotNull(new FirSymbolProvider[]{new JvmClassFileBasedSymbolProvider(firSession, moduleDataProvider, firKotlinScopeProvider, context.getPackagePartProviderForLibraries(), kotlinClassFinder, projectEnvironment.getFirJavaFacade(firSession, firModuleData, context.getLibrariesScope()), null, 64, null), ((Boolean) languageVersionSettings.getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue() ? null : INSTANCE.initializeBuiltinsProvider(firSession, firModuleData, firKotlinScopeProvider, kotlinClassFinder)});
    }

    public static FirAbstractSessionFactory.SourceProviders h(AbstractProjectEnvironment abstractProjectEnvironment, FirModuleData firModuleData, AbstractProjectFileSearchScope abstractProjectFileSearchScope, Function1 function1, FirSession firSession, FirKotlinScopeProvider firKotlinScopeProvider, FirSymbolProvider firSymbolProvider, FirSwitchableExtensionDeclarationsSymbolProvider firSwitchableExtensionDeclarationsSymbolProvider) {
        FirSymbolProvider[] firSymbolProviderArr;
        Collection<FirSymbolProvider> previousFirSessionsSymbolProviders;
        firSession.getClass();
        firKotlinScopeProvider.getClass();
        firSymbolProvider.getClass();
        JavaSymbolProvider javaSymbolProvider = new JavaSymbolProvider(firSession, abstractProjectEnvironment.getFirJavaFacade(firSession, firModuleData, abstractProjectFileSearchScope));
        firSession.register(Reflection.getOrCreateKotlinClass(JavaSymbolProvider.class), javaSymbolProvider);
        FirJvmIncrementalCompilationSymbolProviders firJvmIncrementalCompilationSymbolProviders = (FirJvmIncrementalCompilationSymbolProviders) function1.invoke(firSession);
        SpreadBuilder spreadBuilder = new SpreadBuilder(6);
        spreadBuilder.add(firSymbolProvider);
        spreadBuilder.add(firSwitchableExtensionDeclarationsSymbolProvider);
        if (firJvmIncrementalCompilationSymbolProviders == null || (previousFirSessionsSymbolProviders = firJvmIncrementalCompilationSymbolProviders.getPreviousFirSessionsSymbolProviders()) == null || (firSymbolProviderArr = (FirSymbolProvider[]) previousFirSessionsSymbolProviders.toArray(new FirSymbolProvider[0])) == null) {
            firSymbolProviderArr = new FirSymbolProvider[0];
        }
        spreadBuilder.addSpread(firSymbolProviderArr);
        spreadBuilder.add(firJvmIncrementalCompilationSymbolProviders != null ? firJvmIncrementalCompilationSymbolProviders.getSymbolProviderForBinariesFromIncrementalCompilation() : null);
        spreadBuilder.add(javaSymbolProvider);
        spreadBuilder.add(INSTANCE.initializeForStdlibIfNeeded(abstractProjectEnvironment, firSession, firKotlinScopeProvider));
        return new FirAbstractSessionFactory.SourceProviders(CollectionsKt.listOfNotNull(spreadBuilder.toArray(new FirSymbolProvider[spreadBuilder.size()])), firJvmIncrementalCompilationSymbolProviders != null ? firJvmIncrementalCompilationSymbolProviders.getOptionalAnnotationClassesProviderForBinariesFromIncrementalCompilation() : null);
    }

    private final FirJvmBuiltinsSymbolProvider initializeBuiltinsProvider(FirSession session, FirModuleData builtinsModuleData, FirKotlinScopeProvider kotlinScopeProvider, final KotlinClassFinder kotlinClassFinder) {
        return new FirJvmBuiltinsSymbolProvider(session, new FirFallbackBuiltinSymbolProvider(session, builtinsModuleData, kotlinScopeProvider), new Function1() { // from class: aa5
            public final Object invoke(Object obj) {
                return FirJvmSessionFactory.d(kotlinClassFinder, (FqName) obj);
            }
        });
    }

    private final FirSymbolProvider initializeForStdlibIfNeeded(AbstractProjectEnvironment projectEnvironment, FirSession session, FirKotlinScopeProvider kotlinScopeProvider) {
        if (!(((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(session).getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue() && !FirModuleDataKt.getModuleData(session).getIsCommon() && FirModuleDataKt.getModuleData(session).getDependsOnDependencies().isEmpty())) {
            return null;
        }
        final KotlinClassFinder kotlinClassFinder = projectEnvironment.getKotlinClassFinder(projectEnvironment.getSearchScopeForProjectLibraries());
        return new FirJvmClasspathBuiltinSymbolProvider(session, FirModuleDataKt.getModuleData(session), kotlinScopeProvider, new Function1() { // from class: ba5
            public final Object invoke(Object obj) {
                return FirJvmSessionFactory.initializeForStdlibIfNeeded$lambda$0$0(kotlinClassFinder, (FqName) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final InputStream initializeForStdlibIfNeeded$lambda$0$0(KotlinClassFinder kotlinClassFinder, FqName fqName) {
        fqName.getClass();
        return kotlinClassFinder.findBuiltInsData(fqName);
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public FirKotlinScopeProvider createKotlinScopeProviderForLibrarySession() {
        return new FirKotlinScopeProvider(AnonymousClass1.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public FirKotlinScopeProvider createKotlinScopeProviderForSourceSession(FirModuleData moduleData, final LanguageVersionSettings languageVersionSettings) {
        moduleData.getClass();
        languageVersionSettings.getClass();
        return (((Boolean) languageVersionSettings.getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue() && moduleData.getIsCommon()) ? new FirKotlinScopeProvider(null, 1, null) : new FirKotlinScopeProvider(new Function5() { // from class: ea5
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                return FirJvmSessionFactory.f(languageVersionSettings, (FirClass) obj, (FirContainingNamesAwareScope) obj2, (FirSession) obj3, (ScopeSession) obj4, (FirResolvePhase) obj5);
            }
        });
    }

    public final FirSession createLibrarySession(FirSession sharedLibrarySession, final ModuleDataProvider moduleDataProvider, List<? extends FirExtensionRegistrar> extensionRegistrars, final LanguageVersionSettings languageVersionSettings, final Context context) {
        sharedLibrarySession.getClass();
        moduleDataProvider.getClass();
        extensionRegistrars.getClass();
        languageVersionSettings.getClass();
        context.getClass();
        return createLibrarySession(context, sharedLibrarySession, moduleDataProvider, languageVersionSettings, extensionRegistrars, true, new Function2() { // from class: da5
            public final Object invoke(Object obj, Object obj2) {
                return FirJvmSessionFactory.g(context, moduleDataProvider, languageVersionSettings, (FirSession) obj, (FirKotlinScopeProvider) obj2);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public List<FirSymbolProvider> createPlatformSpecificSharedProviders(FirSession session, FirModuleData moduleData, FirKotlinScopeProvider scopeProvider, Context context) {
        session.getClass();
        moduleData.getClass();
        scopeProvider.getClass();
        context.getClass();
        return CollectionsKt.listOf(new FirSymbolProvider[]{new FirCloneableSymbolProvider(session, moduleData, scopeProvider), new OptionalAnnotationClassesProvider(session, new SingleModuleDataProvider(moduleData), scopeProvider, context.getPackagePartProviderForLibraries(), null, 16, null)});
    }

    public final FirSession createSharedLibrarySession(Name mainModuleName, List<? extends FirExtensionRegistrar> extensionRegistrars, LanguageVersionSettings languageVersionSettings, Context context) {
        mainModuleName.getClass();
        extensionRegistrars.getClass();
        languageVersionSettings.getClass();
        context.getClass();
        return createSharedLibrarySession(mainModuleName, context, languageVersionSettings, extensionRegistrars);
    }

    public final FirSession createSourceSession(final FirModuleData moduleData, final AbstractProjectFileSearchScope javaSourcesScope, final Function1<? super FirSession, FirJvmIncrementalCompilationSymbolProviders> createIncrementalCompilationSymbolProviders, List<? extends FirExtensionRegistrar> extensionRegistrars, CompilerConfiguration configuration, Context context, boolean needRegisterJavaElementFinder, boolean isForLeafHmppModule, Function1<? super FirSessionConfigurator, Unit> init) {
        moduleData.getClass();
        javaSourcesScope.getClass();
        createIncrementalCompilationSymbolProviders.getClass();
        extensionRegistrars.getClass();
        configuration.getClass();
        context.getClass();
        init.getClass();
        final AbstractProjectEnvironment projectEnvironment = context.getProjectEnvironment();
        FirSession firSessionCreateSourceSession = createSourceSession(moduleData, context, extensionRegistrars, configuration, isForLeafHmppModule, init, new Function4() { // from class: ca5
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return FirJvmSessionFactory.h(projectEnvironment, moduleData, javaSourcesScope, createIncrementalCompilationSymbolProviders, (FirSession) obj, (FirKotlinScopeProvider) obj2, (FirSymbolProvider) obj3, (FirSwitchableExtensionDeclarationsSymbolProvider) obj4);
            }
        });
        if (needRegisterJavaElementFinder) {
            projectEnvironment.registerAsJavaElementFinder(firSessionCreateSourceSession);
        }
        return firSessionCreateSourceSession;
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public boolean getRequiresSpecialSetupOfSourceProvidersInHmppCompilation() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public boolean isFactoryForMetadataCompilation() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerExtraPlatformCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerLibrarySessionComponents(FirSession firSession, Context context) {
        firSession.getClass();
        context.getClass();
        ComponentsContainersKt.registerJavaComponents(firSession, context.getProjectEnvironment().getJavaModuleResolver(), context.getPredefinedJavaComponents(), context.getRegisterJvmDeserializationExtension(), context.getInlineConstTracker());
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerPlatformCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        CheckersContainersKt.registerJvmCheckers(firSessionConfigurator);
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerSourceSessionComponents(FirSession firSession, Context context) {
        firSession.getClass();
        context.getClass();
        registerLibrarySessionComponents(firSession, context);
        firSession.register(Reflection.getOrCreateKotlinClass(FirJvmTargetProvider.class), new FirJvmTargetProvider(context.getJvmTarget()));
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rB+\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirJvmSessionFactory$Context;", Argument.Delimiters.none, "jvmTarget", "Lorg/jetbrains/kotlin/config/JvmTarget;", "projectEnvironment", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;", "librariesScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "registerJvmDeserializationExtension", Argument.Delimiters.none, "inlineConstTracker", "Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "<init>", "(Lorg/jetbrains/kotlin/config/JvmTarget;Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;ZLorg/jetbrains/kotlin/incremental/components/InlineConstTracker;)V", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;Z)V", "getJvmTarget", "()Lorg/jetbrains/kotlin/config/JvmTarget;", "getProjectEnvironment", "()Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;", "getLibrariesScope", "()Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "getRegisterJvmDeserializationExtension", "()Z", "getInlineConstTracker", "()Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "packagePartProviderForLibraries", "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "getPackagePartProviderForLibraries", "()Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "predefinedJavaComponents", "Lorg/jetbrains/kotlin/fir/session/FirSharableJavaComponents;", "getPredefinedJavaComponents", "()Lorg/jetbrains/kotlin/fir/session/FirSharableJavaComponents;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Context {
        private final InlineConstTracker inlineConstTracker;
        private final JvmTarget jvmTarget;
        private final AbstractProjectFileSearchScope librariesScope;
        private final PackagePartProvider packagePartProviderForLibraries;
        private final FirSharableJavaComponents predefinedJavaComponents;
        private final AbstractProjectEnvironment projectEnvironment;
        private final boolean registerJvmDeserializationExtension;

        public Context(JvmTarget jvmTarget, AbstractProjectEnvironment abstractProjectEnvironment, AbstractProjectFileSearchScope abstractProjectFileSearchScope, boolean z, InlineConstTracker inlineConstTracker) {
            jvmTarget.getClass();
            abstractProjectEnvironment.getClass();
            abstractProjectFileSearchScope.getClass();
            this.jvmTarget = jvmTarget;
            this.projectEnvironment = abstractProjectEnvironment;
            this.librariesScope = abstractProjectFileSearchScope;
            this.registerJvmDeserializationExtension = z;
            this.inlineConstTracker = inlineConstTracker;
            this.packagePartProviderForLibraries = abstractProjectEnvironment.getPackagePartProvider(abstractProjectFileSearchScope);
            this.predefinedJavaComponents = new FirSharableJavaComponents(ComponentsContainersKt.getFirCachesFactoryForCliMode());
        }

        public final InlineConstTracker getInlineConstTracker() {
            return this.inlineConstTracker;
        }

        public final JvmTarget getJvmTarget() {
            return this.jvmTarget;
        }

        public final AbstractProjectFileSearchScope getLibrariesScope() {
            return this.librariesScope;
        }

        public final PackagePartProvider getPackagePartProviderForLibraries() {
            return this.packagePartProviderForLibraries;
        }

        public final FirSharableJavaComponents getPredefinedJavaComponents() {
            return this.predefinedJavaComponents;
        }

        public final AbstractProjectEnvironment getProjectEnvironment() {
            return this.projectEnvironment;
        }

        public final boolean getRegisterJvmDeserializationExtension() {
            return this.registerJvmDeserializationExtension;
        }

        public /* synthetic */ Context(CompilerConfiguration compilerConfiguration, AbstractProjectEnvironment abstractProjectEnvironment, AbstractProjectFileSearchScope abstractProjectFileSearchScope, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(compilerConfiguration, abstractProjectEnvironment, abstractProjectFileSearchScope, (i & 8) != 0 ? true : z);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public Context(CompilerConfiguration compilerConfiguration, AbstractProjectEnvironment abstractProjectEnvironment, AbstractProjectFileSearchScope abstractProjectFileSearchScope, boolean z) {
            compilerConfiguration.getClass();
            abstractProjectEnvironment.getClass();
            abstractProjectFileSearchScope.getClass();
            JvmTarget jvmTarget = JVMConfigurationKeysKt.getJvmTarget(compilerConfiguration);
            this(jvmTarget == null ? JvmTarget.DEFAULT : jvmTarget, abstractProjectEnvironment, abstractProjectFileSearchScope, z, CommonConfigurationKeysKt.getInlineConstTracker(compilerConfiguration));
        }
    }
}
