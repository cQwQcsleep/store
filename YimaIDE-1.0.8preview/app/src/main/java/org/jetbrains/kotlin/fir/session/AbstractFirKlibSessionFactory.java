package org.jetbrains.kotlin.fir.session;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.SpreadBuilder;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.deserialization.SingleModuleDataProvider;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.session.AbstractFirKlibSessionFactory;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ3\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0016J:\u0010\u0017\u001a\u00020\u00062\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u001d\u001a\u00020\nJ\u0015\u0010\u001e\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\nH$¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020!2\u0006\u0010\u0010\u001a\u00020\u0006H\u0014J4\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\fH\u0016J\b\u0010$\u001a\u00020\u0014H\u0004JQ\u0010%\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010&\u001a\u00020'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\u0017\u0010*\u001a\u0013\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0+¢\u0006\u0002\b.J\u0015\u0010/\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\nH$¢\u0006\u0002\u0010\u001fJ\u0018\u00100\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u00101\u001a\u000202H\u0004R\u0014\u00103\u001a\u00020'8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R\u0014\u00106\u001a\u00020'8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b6\u00105¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/AbstractFirKlibSessionFactory;", "CONTEXT", "Lorg/jetbrains/kotlin/fir/session/FirAbstractSessionFactory;", "<init>", "()V", "createSharedLibrarySession", "Lorg/jetbrains/kotlin/fir/FirSession;", "mainModuleName", "Lorg/jetbrains/kotlin/name/Name;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "extensionRegistrars", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;", "createPlatformSpecificSharedProviders", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "context", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Ljava/lang/Object;)Ljava/util/List;", "createLibrarySession", "resolvedLibraries", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "sharedLibrarySession", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "compilerConfiguration", "createLibraryContext", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/Object;", "createFlexibleTypeFactory", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "createAdditionalDependencyProviders", "kotlinScopeProvider", "createKotlinScopeProviderForLibrarySession", "createSourceSession", "isForLeafHmppModule", Argument.Delimiters.none, "icData", "Lorg/jetbrains/kotlin/fir/session/KlibIcData;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "createSourceContext", "createKotlinScopeProviderForSourceSession", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "requiresSpecialSetupOfSourceProvidersInHmppCompilation", "getRequiresSpecialSetupOfSourceProvidersInHmppCompilation", "()Z", "isFactoryForMetadataCompilation", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFirKlibSessionFactory<CONTEXT> extends FirAbstractSessionFactory<CONTEXT> {
    public static /* synthetic */ FirSession createSourceSession$default(AbstractFirKlibSessionFactory abstractFirKlibSessionFactory, FirModuleData firModuleData, List list, CompilerConfiguration compilerConfiguration, boolean z, KlibIcData klibIcData, Function1 function1, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: createSourceSession");
            return null;
        }
        if ((i & 16) != 0) {
            klibIcData = null;
        }
        return abstractFirKlibSessionFactory.createSourceSession(firModuleData, list, compilerConfiguration, z, klibIcData, function1);
    }

    public static FirAbstractSessionFactory.SourceProviders d(KlibIcData klibIcData, FirModuleData firModuleData, AbstractFirKlibSessionFactory abstractFirKlibSessionFactory, FirSession firSession, FirKotlinScopeProvider firKotlinScopeProvider, FirSymbolProvider firSymbolProvider, FirSwitchableExtensionDeclarationsSymbolProvider firSwitchableExtensionDeclarationsSymbolProvider) {
        firSession.getClass();
        firKotlinScopeProvider.getClass();
        firSymbolProvider.getClass();
        FirSymbolProvider firSymbolProvider2 = null;
        return new FirAbstractSessionFactory.SourceProviders(CollectionsKt.listOfNotNull(new FirSymbolProvider[]{firSymbolProvider, firSwitchableExtensionDeclarationsSymbolProvider, klibIcData != null ? new KlibIcCacheBasedSymbolProvider(firSession, new SingleModuleDataProvider(firModuleData), firKotlinScopeProvider, klibIcData, null, abstractFirKlibSessionFactory.createFlexibleTypeFactory(firSession), 16, null) : null}), firSymbolProvider2, 2, firSymbolProvider2);
    }

    public static List e(ModuleDataProvider moduleDataProvider, List list, AbstractFirKlibSessionFactory abstractFirKlibSessionFactory, FirSession firSession, FirKotlinScopeProvider firKotlinScopeProvider) {
        firSession.getClass();
        firKotlinScopeProvider.getClass();
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(new KlibBasedSymbolProvider(firSession, moduleDataProvider, firKotlinScopeProvider, list, null, abstractFirKlibSessionFactory.createFlexibleTypeFactory(firSession), 16, null));
        spreadBuilder.addSpread(abstractFirKlibSessionFactory.createAdditionalDependencyProviders(firSession, moduleDataProvider, firKotlinScopeProvider, list).toArray(new FirSymbolProvider[0]));
        return CollectionsKt.listOfNotNull(spreadBuilder.toArray(new FirSymbolProvider[spreadBuilder.size()]));
    }

    public List<FirSymbolProvider> createAdditionalDependencyProviders(FirSession session, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider kotlinScopeProvider, List<? extends KotlinLibrary> resolvedLibraries) {
        session.getClass();
        moduleDataProvider.getClass();
        kotlinScopeProvider.getClass();
        resolvedLibraries.getClass();
        return CollectionsKt.emptyList();
    }

    public FirTypeDeserializer.FlexibleTypeFactory createFlexibleTypeFactory(FirSession session) {
        session.getClass();
        return FirTypeDeserializer.FlexibleTypeFactory.Default.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public final FirKotlinScopeProvider createKotlinScopeProviderForLibrarySession() {
        return new FirKotlinScopeProvider(null, 1, null);
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public final FirKotlinScopeProvider createKotlinScopeProviderForSourceSession(FirModuleData moduleData, LanguageVersionSettings languageVersionSettings) {
        moduleData.getClass();
        languageVersionSettings.getClass();
        return new FirKotlinScopeProvider(null, 1, null);
    }

    public abstract CONTEXT createLibraryContext(CompilerConfiguration configuration);

    public final FirSession createLibrarySession(final List<? extends KotlinLibrary> resolvedLibraries, FirSession sharedLibrarySession, final ModuleDataProvider moduleDataProvider, List<? extends FirExtensionRegistrar> extensionRegistrars, CompilerConfiguration compilerConfiguration) {
        resolvedLibraries.getClass();
        sharedLibrarySession.getClass();
        moduleDataProvider.getClass();
        extensionRegistrars.getClass();
        compilerConfiguration.getClass();
        return createLibrarySession(createLibraryContext(compilerConfiguration), sharedLibrarySession, moduleDataProvider, CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration), extensionRegistrars, true, new Function2() { // from class: rm
            public final Object invoke(Object obj, Object obj2) {
                return AbstractFirKlibSessionFactory.e(moduleDataProvider, resolvedLibraries, this, (FirSession) obj, (FirKotlinScopeProvider) obj2);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public List<FirSymbolProvider> createPlatformSpecificSharedProviders(FirSession session, FirModuleData moduleData, FirKotlinScopeProvider scopeProvider, CONTEXT context) {
        session.getClass();
        moduleData.getClass();
        scopeProvider.getClass();
        return CollectionsKt.emptyList();
    }

    public final FirSession createSharedLibrarySession(Name mainModuleName, CompilerConfiguration configuration, List<? extends FirExtensionRegistrar> extensionRegistrars) {
        mainModuleName.getClass();
        configuration.getClass();
        extensionRegistrars.getClass();
        return createSharedLibrarySession(mainModuleName, createLibraryContext(configuration), CommonConfigurationKeysKt.getLanguageVersionSettings(configuration), extensionRegistrars);
    }

    public abstract CONTEXT createSourceContext(CompilerConfiguration configuration);

    public final FirSession createSourceSession(final FirModuleData moduleData, List<? extends FirExtensionRegistrar> extensionRegistrars, CompilerConfiguration configuration, boolean isForLeafHmppModule, final KlibIcData icData, Function1<? super FirSessionConfigurator, Unit> init) {
        moduleData.getClass();
        extensionRegistrars.getClass();
        configuration.getClass();
        init.getClass();
        return createSourceSession(moduleData, createSourceContext(configuration), extensionRegistrars, configuration, isForLeafHmppModule, init, new Function4() { // from class: sm
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AbstractFirKlibSessionFactory.d(icData, moduleData, this, (FirSession) obj, (FirKotlinScopeProvider) obj2, (FirSymbolProvider) obj3, (FirSwitchableExtensionDeclarationsSymbolProvider) obj4);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public boolean getRequiresSpecialSetupOfSourceProvidersInHmppCompilation() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public boolean isFactoryForMetadataCompilation() {
        return false;
    }
}
