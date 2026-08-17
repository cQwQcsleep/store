package org.jetbrains.kotlin.fir.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.deserialization.SingleModuleDataProvider;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.scopes.impl.FirEnumEntriesSupport;
import org.jetbrains.kotlin.fir.session.AbstractFirMetadataSessionFactory;
import org.jetbrains.kotlin.fir.session.FirJsSessionFactory;
import org.jetbrains.kotlin.fir.session.FirJvmSessionFactory;
import org.jetbrains.kotlin.fir.session.FirNativeSessionFactory;
import org.jetbrains.kotlin.fir.session.FirSessionConfigurator;
import org.jetbrains.kotlin.fir.session.FirWasmSessionFactory;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.PackageAndMetadataPartProvider;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.JsPlatform;
import org.jetbrains.kotlin.platform.NativePlatform;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.TargetPlatformKt;
import org.jetbrains.kotlin.platform.WasmPlatform;
import org.jetbrains.kotlin.platform.jvm.JvmPlatform;
import org.jetbrains.kotlin.platform.wasm.WasmPlatforms;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002LMB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J,\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0002J\u0088\u0001\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00022:\b\u0002\u0010\u001f\u001a4\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u0010\u0018\u00010 j\u0004\u0018\u0001`#J\b\u0010$\u001a\u00020!H\u0014J\u0014\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\u0002H\u0016Ja\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010/\u001a\u0002002\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u00101\u001a\u00020\u00142\u0019\b\u0002\u00102\u001a\u0013\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020&03¢\u0006\u0002\b5J\u0018\u00106\u001a\u00020!2\u0006\u0010)\u001a\u00020*2\u0006\u0010\r\u001a\u00020\u000eH\u0014J\f\u00107\u001a\u00020&*\u000204H\u0016J\f\u00108\u001a\u00020&*\u000204H\u0016J\u0014\u00109\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\u0002H\u0016J\u0099\u0001\u0010=\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00022\u001d\u0010>\u001a\u0019\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020&0?¢\u0006\u0002\b52\u001d\u0010B\u001a\u0019\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020&0?¢\u0006\u0002\b52\u0017\u0010E\u001a\u0013\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020&03¢\u0006\u0002\b52\u0017\u0010G\u001a\u0013\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020&03¢\u0006\u0002\b52\u0017\u0010I\u001a\u0013\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020&03¢\u0006\u0002\b5H\u0002J\u0085\u0001\u0010K\u001a\u00020&2\u0017\u0010>\u001a\u0013\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020&03¢\u0006\u0002\b52\u0017\u0010B\u001a\u0013\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020&03¢\u0006\u0002\b52\u0017\u0010E\u001a\u0013\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020&03¢\u0006\u0002\b52\u0017\u0010G\u001a\u0013\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020&03¢\u0006\u0002\b52\u0017\u0010I\u001a\u0013\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020&03¢\u0006\u0002\b5H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\u0013\u001a\u00020\u0014X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010:\u001a\u00020\u00148TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b;\u0010\u0016R\u0014\u0010<\u001a\u00020\u00148TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\u0016¨\u0006N"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/AbstractFirMetadataSessionFactory;", "Lorg/jetbrains/kotlin/fir/session/FirAbstractSessionFactory;", "Lorg/jetbrains/kotlin/fir/session/AbstractFirMetadataSessionFactory$Context;", "targetPlatform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "<init>", "(Lorg/jetbrains/kotlin/platform/TargetPlatform;)V", "getTargetPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "createSharedLibrarySession", "Lorg/jetbrains/kotlin/fir/FirSession;", "mainModuleName", "Lorg/jetbrains/kotlin/name/Name;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "extensionRegistrars", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;", "context", "createSeparateSharedProvidersInHmppCompilation", Argument.Delimiters.none, "getCreateSeparateSharedProvidersInHmppCompilation", "()Z", "createLibrarySession", "sharedLibrarySession", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "jarMetadataProviderComponents", "Lorg/jetbrains/kotlin/fir/session/AbstractFirMetadataSessionFactory$JarMetadataProviderComponents;", "resolvedKLibs", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "additionalProviders", "Lkotlin/Function4;", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "Lorg/jetbrains/kotlin/fir/session/AdditionalProvidersSupplier;", "createKotlinScopeProviderForLibrarySession", "registerLibrarySessionComponents", Argument.Delimiters.none, "c", "createSourceSession", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "projectEnvironment", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;", "incrementalCompilationContext", "Lorg/jetbrains/kotlin/fir/session/IncrementalCompilationContext;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "isForLeafHmppModule", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", "Lkotlin/ExtensionFunctionType;", "createKotlinScopeProviderForSourceSession", "registerPlatformCheckers", "registerExtraPlatformCheckers", "registerSourceSessionComponents", "requiresSpecialSetupOfSourceProvidersInHmppCompilation", "getRequiresSpecialSetupOfSourceProvidersInHmppCompilation", "isFactoryForMetadataCompilation", "processPlatformsWithContext", "onJvmPlatform", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/session/FirJvmSessionFactory;", "Lorg/jetbrains/kotlin/fir/session/FirJvmSessionFactory$Context;", "onJsPlatform", "Lorg/jetbrains/kotlin/fir/session/FirJsSessionFactory;", "Lorg/jetbrains/kotlin/fir/session/FirJsSessionFactory$Context;", "onWasmJsPlatform", "Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory$WasmJs;", "onWasmWasiPlatform", "Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory$WasmWasi;", "onNativePlatform", "Lorg/jetbrains/kotlin/fir/session/FirNativeSessionFactory$ForMetadata;", "processPlatforms", "Context", "JarMetadataProviderComponents", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFirMetadataSessionFactory extends FirAbstractSessionFactory<Context> {
    private final TargetPlatform targetPlatform;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/AbstractFirMetadataSessionFactory$Context;", Argument.Delimiters.none, "createJvmContext", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/fir/session/FirJvmSessionFactory$Context;", "createJsContext", "Lorg/jetbrains/kotlin/fir/session/FirJsSessionFactory$Context;", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "jvmContext", "getJvmContext", "()Lorg/jetbrains/kotlin/fir/session/FirJvmSessionFactory$Context;", "jvmContext$delegate", "Lkotlin/Lazy;", "jsContext", "getJsContext", "()Lorg/jetbrains/kotlin/fir/session/FirJsSessionFactory$Context;", "jsContext$delegate", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Context {

        /* JADX INFO: renamed from: jsContext$delegate, reason: from kotlin metadata */
        private final Lazy jsContext;

        /* JADX INFO: renamed from: jvmContext$delegate, reason: from kotlin metadata */
        private final Lazy jvmContext;

        public Context(final Function0<FirJvmSessionFactory.Context> function0, final Function0<FirJsSessionFactory.Context> function1) {
            function0.getClass();
            function1.getClass();
            LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
            this.jvmContext = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: vn
                public final Object invoke() {
                    return AbstractFirMetadataSessionFactory.Context.b(function0);
                }
            });
            this.jsContext = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: wn
                public final Object invoke() {
                    return AbstractFirMetadataSessionFactory.Context.a(function1);
                }
            });
        }

        public static FirJsSessionFactory.Context a(Function0 function0) {
            return (FirJsSessionFactory.Context) function0.invoke();
        }

        public static FirJvmSessionFactory.Context b(Function0 function0) {
            return (FirJvmSessionFactory.Context) function0.invoke();
        }

        public final FirJsSessionFactory.Context getJsContext() {
            return (FirJsSessionFactory.Context) this.jsContext.getValue();
        }

        public final FirJvmSessionFactory.Context getJvmContext() {
            return (FirJvmSessionFactory.Context) this.jvmContext.getValue();
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/AbstractFirMetadataSessionFactory$JarMetadataProviderComponents;", Argument.Delimiters.none, "packageAndMetadataPartProvider", "Lorg/jetbrains/kotlin/load/kotlin/PackageAndMetadataPartProvider;", "librariesScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "projectEnvironment", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;", "<init>", "(Lorg/jetbrains/kotlin/load/kotlin/PackageAndMetadataPartProvider;Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;)V", "getPackageAndMetadataPartProvider", "()Lorg/jetbrains/kotlin/load/kotlin/PackageAndMetadataPartProvider;", "getLibrariesScope", "()Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "getProjectEnvironment", "()Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class JarMetadataProviderComponents {
        private final AbstractProjectFileSearchScope librariesScope;
        private final PackageAndMetadataPartProvider packageAndMetadataPartProvider;
        private final AbstractProjectEnvironment projectEnvironment;

        public JarMetadataProviderComponents(PackageAndMetadataPartProvider packageAndMetadataPartProvider, AbstractProjectFileSearchScope abstractProjectFileSearchScope, AbstractProjectEnvironment abstractProjectEnvironment) {
            packageAndMetadataPartProvider.getClass();
            abstractProjectFileSearchScope.getClass();
            abstractProjectEnvironment.getClass();
            this.packageAndMetadataPartProvider = packageAndMetadataPartProvider;
            this.librariesScope = abstractProjectFileSearchScope;
            this.projectEnvironment = abstractProjectEnvironment;
        }

        public static /* synthetic */ JarMetadataProviderComponents copy$default(JarMetadataProviderComponents jarMetadataProviderComponents, PackageAndMetadataPartProvider packageAndMetadataPartProvider, AbstractProjectFileSearchScope abstractProjectFileSearchScope, AbstractProjectEnvironment abstractProjectEnvironment, int i, Object obj) {
            if ((i & 1) != 0) {
                packageAndMetadataPartProvider = jarMetadataProviderComponents.packageAndMetadataPartProvider;
            }
            if ((i & 2) != 0) {
                abstractProjectFileSearchScope = jarMetadataProviderComponents.librariesScope;
            }
            if ((i & 4) != 0) {
                abstractProjectEnvironment = jarMetadataProviderComponents.projectEnvironment;
            }
            return jarMetadataProviderComponents.copy(packageAndMetadataPartProvider, abstractProjectFileSearchScope, abstractProjectEnvironment);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final PackageAndMetadataPartProvider getPackageAndMetadataPartProvider() {
            return this.packageAndMetadataPartProvider;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final AbstractProjectFileSearchScope getLibrariesScope() {
            return this.librariesScope;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final AbstractProjectEnvironment getProjectEnvironment() {
            return this.projectEnvironment;
        }

        public final JarMetadataProviderComponents copy(PackageAndMetadataPartProvider packageAndMetadataPartProvider, AbstractProjectFileSearchScope librariesScope, AbstractProjectEnvironment projectEnvironment) {
            packageAndMetadataPartProvider.getClass();
            librariesScope.getClass();
            projectEnvironment.getClass();
            return new JarMetadataProviderComponents(packageAndMetadataPartProvider, librariesScope, projectEnvironment);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof JarMetadataProviderComponents)) {
                return false;
            }
            JarMetadataProviderComponents jarMetadataProviderComponents = (JarMetadataProviderComponents) other;
            return Intrinsics.areEqual(this.packageAndMetadataPartProvider, jarMetadataProviderComponents.packageAndMetadataPartProvider) && Intrinsics.areEqual(this.librariesScope, jarMetadataProviderComponents.librariesScope) && Intrinsics.areEqual(this.projectEnvironment, jarMetadataProviderComponents.projectEnvironment);
        }

        public final AbstractProjectFileSearchScope getLibrariesScope() {
            return this.librariesScope;
        }

        public final PackageAndMetadataPartProvider getPackageAndMetadataPartProvider() {
            return this.packageAndMetadataPartProvider;
        }

        public final AbstractProjectEnvironment getProjectEnvironment() {
            return this.projectEnvironment;
        }

        public int hashCode() {
            return (((this.packageAndMetadataPartProvider.hashCode() * 31) + this.librariesScope.hashCode()) * 31) + this.projectEnvironment.hashCode();
        }

        public String toString() {
            return "JarMetadataProviderComponents(packageAndMetadataPartProvider=" + this.packageAndMetadataPartProvider + ", librariesScope=" + this.librariesScope + ", projectEnvironment=" + this.projectEnvironment + ')';
        }
    }

    public AbstractFirMetadataSessionFactory(TargetPlatform targetPlatform) {
        targetPlatform.getClass();
        this.targetPlatform = targetPlatform;
    }

    public static Unit A(Function2 function2, Context context, FirJsSessionFactory firJsSessionFactory) {
        firJsSessionFactory.getClass();
        function2.invoke(firJsSessionFactory, context.getJsContext());
        return Unit.INSTANCE;
    }

    public static Unit B(FirSession firSession, FirJsSessionFactory firJsSessionFactory, FirJsSessionFactory.Context context) {
        firJsSessionFactory.getClass();
        context.getClass();
        firJsSessionFactory.registerLibrarySessionComponents(firSession, context);
        return Unit.INSTANCE;
    }

    public static FirContainingNamesAwareScope C(FirClass firClass, FirContainingNamesAwareScope firContainingNamesAwareScope, FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase) {
        firClass.getClass();
        firContainingNamesAwareScope.getClass();
        firSession.getClass();
        scopeSession.getClass();
        return firContainingNamesAwareScope;
    }

    public static Unit D(AbstractFirMetadataSessionFactory abstractFirMetadataSessionFactory, final FirSessionConfigurator firSessionConfigurator) {
        abstractFirMetadataSessionFactory.processPlatforms(new Function1() { // from class: wm
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.registerExtraPlatformCheckers$lambda$0$0(firSessionConfigurator, (FirJvmSessionFactory) obj);
            }
        }, new Function1() { // from class: xm
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.registerExtraPlatformCheckers$lambda$0$1(firSessionConfigurator, (FirJsSessionFactory) obj);
            }
        }, new Function1() { // from class: ym
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.registerExtraPlatformCheckers$lambda$0$2(firSessionConfigurator, (FirWasmSessionFactory.WasmJs) obj);
            }
        }, new Function1() { // from class: zm
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.registerExtraPlatformCheckers$lambda$0$3(firSessionConfigurator, (FirWasmSessionFactory.WasmWasi) obj);
            }
        }, new Function1() { // from class: an
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.registerExtraPlatformCheckers$lambda$0$4(firSessionConfigurator, (FirNativeSessionFactory.ForMetadata) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static Unit E(FirSession firSession, FirNativeSessionFactory.ForMetadata forMetadata) {
        forMetadata.getClass();
        forMetadata.registerSourceSessionComponents(firSession, (Void) null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirSession createLibrarySession$default(AbstractFirMetadataSessionFactory abstractFirMetadataSessionFactory, FirSession firSession, ModuleDataProvider moduleDataProvider, List list, JarMetadataProviderComponents jarMetadataProviderComponents, List list2, LanguageVersionSettings languageVersionSettings, Context context, Function4 function4, int i, Object obj) {
        if (obj == null) {
            return abstractFirMetadataSessionFactory.createLibrarySession(firSession, moduleDataProvider, list, jarMetadataProviderComponents, list2, languageVersionSettings, context, (i & 128) != 0 ? null : function4);
        }
        c41.a("Super calls with default arguments not supported in this target, function: createLibrarySession");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirSession createSourceSession$default(AbstractFirMetadataSessionFactory abstractFirMetadataSessionFactory, FirModuleData firModuleData, AbstractProjectEnvironment abstractProjectEnvironment, IncrementalCompilationContext incrementalCompilationContext, List list, CompilerConfiguration compilerConfiguration, Context context, boolean z, Function1 function1, int i, Object obj) {
        if (obj == null) {
            return abstractFirMetadataSessionFactory.createSourceSession(firModuleData, abstractProjectEnvironment, incrementalCompilationContext, list, compilerConfiguration, context, z, (i & 128) != 0 ? new Function1() { // from class: rn
                public final Object invoke(Object obj2) {
                    return AbstractFirMetadataSessionFactory.r((FirSessionConfigurator) obj2);
                }
            } : function1);
        }
        c41.a("Super calls with default arguments not supported in this target, function: createSourceSession");
        return null;
    }

    public static Unit e(FirSession firSession, FirWasmSessionFactory.WasmWasi wasmWasi) {
        wasmWasi.getClass();
        wasmWasi.registerLibrarySessionComponents(firSession, (Void) null);
        return Unit.INSTANCE;
    }

    public static Unit f(Function2 function2, Context context, FirJvmSessionFactory firJvmSessionFactory) {
        firJvmSessionFactory.getClass();
        function2.invoke(firJvmSessionFactory, context.getJvmContext());
        return Unit.INSTANCE;
    }

    public static Unit g(FirSession firSession, FirWasmSessionFactory.WasmJs wasmJs) {
        wasmJs.getClass();
        wasmJs.registerSourceSessionComponents(firSession, (Void) null);
        return Unit.INSTANCE;
    }

    public static Unit h(FirSession firSession, FirJvmSessionFactory firJvmSessionFactory, FirJvmSessionFactory.Context context) {
        firJvmSessionFactory.getClass();
        context.getClass();
        firJvmSessionFactory.registerSourceSessionComponents(firSession, context);
        return Unit.INSTANCE;
    }

    public static List i(JarMetadataProviderComponents jarMetadataProviderComponents, List list, Function4 function4, ModuleDataProvider moduleDataProvider, FirSession firSession, FirKotlinScopeProvider firKotlinScopeProvider) {
        List list2;
        firSession.getClass();
        firKotlinScopeProvider.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        if (jarMetadataProviderComponents != null) {
            listCreateListBuilder.add(new MetadataSymbolProvider(firSession, moduleDataProvider, firKotlinScopeProvider, jarMetadataProviderComponents.getPackageAndMetadataPartProvider(), jarMetadataProviderComponents.getProjectEnvironment().getKotlinClassFinder(jarMetadataProviderComponents.getLibrariesScope()), null, 32, null));
        }
        List list3 = list;
        if (!list3.isEmpty()) {
            listCreateListBuilder.add(new KlibBasedSymbolProvider(firSession, moduleDataProvider, firKotlinScopeProvider, list3, null, null, 48, null));
        }
        if (function4 != null && (list2 = (List) function4.invoke(firSession, moduleDataProvider, firKotlinScopeProvider, list)) != null) {
            CollectionsKt.addAll(listCreateListBuilder, list2);
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public static Unit j(FirSession firSession, FirNativeSessionFactory.ForMetadata forMetadata) {
        forMetadata.getClass();
        forMetadata.registerLibrarySessionComponents(firSession, (Void) null);
        return Unit.INSTANCE;
    }

    public static FirAbstractSessionFactory.SourceProviders l(IncrementalCompilationContext incrementalCompilationContext, FirModuleData firModuleData, AbstractProjectEnvironment abstractProjectEnvironment, FirSession firSession, FirKotlinScopeProvider firKotlinScopeProvider, FirSymbolProvider firSymbolProvider, FirSwitchableExtensionDeclarationsSymbolProvider firSwitchableExtensionDeclarationsSymbolProvider) {
        MetadataSymbolProvider metadataSymbolProvider;
        FirSymbolProvider[] firSymbolProviderArr;
        Collection<FirSymbolProvider> previousFirSessionsSymbolProviders;
        PackagePartProvider precompiledBinariesPackagePartProvider;
        firSession.getClass();
        firKotlinScopeProvider.getClass();
        firSymbolProvider.getClass();
        FirSymbolProvider firSymbolProvider2 = null;
        if (incrementalCompilationContext == null || (precompiledBinariesPackagePartProvider = incrementalCompilationContext.getPrecompiledBinariesPackagePartProvider()) == null || incrementalCompilationContext.getPrecompiledBinariesFileScope() == null) {
            metadataSymbolProvider = null;
        } else {
            KotlinClassFinder kotlinClassFinder = abstractProjectEnvironment.getKotlinClassFinder(incrementalCompilationContext.getPrecompiledBinariesFileScope());
            kotlinClassFinder.getClass();
            metadataSymbolProvider = new MetadataSymbolProvider(firSession, new SingleModuleDataProvider(firModuleData), firKotlinScopeProvider, (PackageAndMetadataPartProvider) precompiledBinariesPackagePartProvider, kotlinClassFinder, FirDeclarationOrigin.Precompiled.INSTANCE);
        }
        SpreadBuilder spreadBuilder = new SpreadBuilder(4);
        spreadBuilder.add(firSymbolProvider);
        if (incrementalCompilationContext == null || (previousFirSessionsSymbolProviders = incrementalCompilationContext.getPreviousFirSessionsSymbolProviders()) == null || (firSymbolProviderArr = (FirSymbolProvider[]) previousFirSessionsSymbolProviders.toArray(new FirSymbolProvider[0])) == null) {
            firSymbolProviderArr = new FirSymbolProvider[0];
        }
        spreadBuilder.addSpread(firSymbolProviderArr);
        spreadBuilder.add(metadataSymbolProvider);
        spreadBuilder.add(firSwitchableExtensionDeclarationsSymbolProvider);
        return new FirAbstractSessionFactory.SourceProviders(CollectionsKt.listOfNotNull(spreadBuilder.toArray(new FirSymbolProvider[spreadBuilder.size()])), firSymbolProvider2, 2, firSymbolProvider2);
    }

    public static Unit n(AbstractFirMetadataSessionFactory abstractFirMetadataSessionFactory, final FirSessionConfigurator firSessionConfigurator) {
        abstractFirMetadataSessionFactory.processPlatforms(new Function1() { // from class: sn
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.registerPlatformCheckers$lambda$0$0(firSessionConfigurator, (FirJvmSessionFactory) obj);
            }
        }, new Function1() { // from class: tn
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.registerPlatformCheckers$lambda$0$1(firSessionConfigurator, (FirJsSessionFactory) obj);
            }
        }, new Function1() { // from class: un
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.registerPlatformCheckers$lambda$0$2(firSessionConfigurator, (FirWasmSessionFactory.WasmJs) obj);
            }
        }, new Function1() { // from class: um
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.registerPlatformCheckers$lambda$0$3(firSessionConfigurator, (FirWasmSessionFactory.WasmWasi) obj);
            }
        }, new Function1() { // from class: vm
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.registerPlatformCheckers$lambda$0$4(firSessionConfigurator, (FirNativeSessionFactory.ForMetadata) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static Unit p(FirSession firSession, FirWasmSessionFactory.WasmJs wasmJs) {
        wasmJs.getClass();
        wasmJs.registerLibrarySessionComponents(firSession, (Void) null);
        return Unit.INSTANCE;
    }

    private final void processPlatforms(Function1<? super FirJvmSessionFactory, Unit> onJvmPlatform, Function1<? super FirJsSessionFactory, Unit> onJsPlatform, Function1<? super FirWasmSessionFactory.WasmJs, Unit> onWasmJsPlatform, Function1<? super FirWasmSessionFactory.WasmWasi, Unit> onWasmWasiPlatform, Function1<? super FirNativeSessionFactory.ForMetadata, Unit> onNativePlatform) {
        TargetPlatform targetPlatform = this.targetPlatform;
        if (targetPlatform != null) {
            Set componentPlatforms = targetPlatform.getComponentPlatforms();
            ArrayList arrayList = new ArrayList();
            for (Object obj : componentPlatforms) {
                if (obj instanceof JvmPlatform) {
                    arrayList.add(obj);
                }
            }
            if (!arrayList.isEmpty()) {
                onJvmPlatform.invoke(FirJvmSessionFactory.INSTANCE);
            }
        }
        if (targetPlatform != null) {
            Set componentPlatforms2 = targetPlatform.getComponentPlatforms();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : componentPlatforms2) {
                if (obj2 instanceof JsPlatform) {
                    arrayList2.add(obj2);
                }
            }
            if (!arrayList2.isEmpty()) {
                onJsPlatform.invoke(FirJsSessionFactory.INSTANCE);
            }
        }
        if (targetPlatform != null) {
            Set componentPlatforms3 = targetPlatform.getComponentPlatforms();
            ArrayList arrayList3 = new ArrayList();
            for (Object obj3 : componentPlatforms3) {
                if (obj3 instanceof WasmPlatform) {
                    arrayList3.add(obj3);
                }
            }
            if (!arrayList3.isEmpty()) {
                Set componentPlatforms4 = targetPlatform.getComponentPlatforms();
                ArrayList arrayList4 = new ArrayList();
                for (Object obj4 : componentPlatforms4) {
                    if (obj4 instanceof WasmPlatform) {
                        arrayList4.add(obj4);
                    }
                }
                ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
                Iterator it = arrayList4.iterator();
                while (it.hasNext()) {
                    arrayList5.add(TargetPlatformKt.toTargetPlatform((WasmPlatform) it.next()));
                }
                WasmPlatforms wasmPlatforms = WasmPlatforms.INSTANCE;
                if (arrayList5.contains(wasmPlatforms.getUnspecifiedWasmPlatform()) || arrayList5.contains(wasmPlatforms.getWasmJs())) {
                    onWasmJsPlatform.invoke(FirWasmSessionFactory.WasmJs.INSTANCE);
                }
                if (arrayList5.contains(wasmPlatforms.getWasmWasi())) {
                    onWasmWasiPlatform.invoke(FirWasmSessionFactory.WasmWasi.INSTANCE);
                }
            }
        }
        if (targetPlatform != null) {
            Set componentPlatforms5 = targetPlatform.getComponentPlatforms();
            ArrayList arrayList6 = new ArrayList();
            for (Object obj5 : componentPlatforms5) {
                if (obj5 instanceof NativePlatform) {
                    arrayList6.add(obj5);
                }
            }
            if (arrayList6.isEmpty()) {
                return;
            }
            onNativePlatform.invoke(FirNativeSessionFactory.ForMetadata.INSTANCE);
        }
    }

    private final void processPlatformsWithContext(final Context c, final Function2<? super FirJvmSessionFactory, ? super FirJvmSessionFactory.Context, Unit> onJvmPlatform, final Function2<? super FirJsSessionFactory, ? super FirJsSessionFactory.Context, Unit> onJsPlatform, Function1<? super FirWasmSessionFactory.WasmJs, Unit> onWasmJsPlatform, Function1<? super FirWasmSessionFactory.WasmWasi, Unit> onWasmWasiPlatform, Function1<? super FirNativeSessionFactory.ForMetadata, Unit> onNativePlatform) {
        processPlatforms(new Function1() { // from class: bn
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.f(onJvmPlatform, c, (FirJvmSessionFactory) obj);
            }
        }, new Function1() { // from class: cn
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.A(onJsPlatform, c, (FirJsSessionFactory) obj);
            }
        }, onWasmJsPlatform, onWasmWasiPlatform, onNativePlatform);
    }

    public static Unit r(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerExtraPlatformCheckers$lambda$0$0(FirSessionConfigurator firSessionConfigurator, FirJvmSessionFactory firJvmSessionFactory) {
        firJvmSessionFactory.getClass();
        firJvmSessionFactory.registerExtraPlatformCheckers(firSessionConfigurator);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerExtraPlatformCheckers$lambda$0$1(FirSessionConfigurator firSessionConfigurator, FirJsSessionFactory firJsSessionFactory) {
        firJsSessionFactory.getClass();
        firJsSessionFactory.registerExtraPlatformCheckers(firSessionConfigurator);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerExtraPlatformCheckers$lambda$0$2(FirSessionConfigurator firSessionConfigurator, FirWasmSessionFactory.WasmJs wasmJs) {
        wasmJs.getClass();
        wasmJs.registerExtraPlatformCheckers(firSessionConfigurator);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerExtraPlatformCheckers$lambda$0$3(FirSessionConfigurator firSessionConfigurator, FirWasmSessionFactory.WasmWasi wasmWasi) {
        wasmWasi.getClass();
        wasmWasi.registerExtraPlatformCheckers(firSessionConfigurator);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerExtraPlatformCheckers$lambda$0$4(FirSessionConfigurator firSessionConfigurator, FirNativeSessionFactory.ForMetadata forMetadata) {
        forMetadata.getClass();
        forMetadata.registerExtraPlatformCheckers(firSessionConfigurator);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerPlatformCheckers$lambda$0$0(FirSessionConfigurator firSessionConfigurator, FirJvmSessionFactory firJvmSessionFactory) {
        firJvmSessionFactory.getClass();
        firJvmSessionFactory.registerPlatformCheckers(firSessionConfigurator);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerPlatformCheckers$lambda$0$1(FirSessionConfigurator firSessionConfigurator, FirJsSessionFactory firJsSessionFactory) {
        firJsSessionFactory.getClass();
        firJsSessionFactory.registerPlatformCheckers(firSessionConfigurator);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerPlatformCheckers$lambda$0$2(FirSessionConfigurator firSessionConfigurator, FirWasmSessionFactory.WasmJs wasmJs) {
        wasmJs.getClass();
        wasmJs.registerPlatformCheckers(firSessionConfigurator);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerPlatformCheckers$lambda$0$3(FirSessionConfigurator firSessionConfigurator, FirWasmSessionFactory.WasmWasi wasmWasi) {
        wasmWasi.getClass();
        wasmWasi.registerPlatformCheckers(firSessionConfigurator);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerPlatformCheckers$lambda$0$4(FirSessionConfigurator firSessionConfigurator, FirNativeSessionFactory.ForMetadata forMetadata) {
        forMetadata.getClass();
        forMetadata.registerPlatformCheckers(firSessionConfigurator);
        return Unit.INSTANCE;
    }

    public static Unit t(FirSession firSession, FirWasmSessionFactory.WasmWasi wasmWasi) {
        wasmWasi.getClass();
        wasmWasi.registerSourceSessionComponents(firSession, (Void) null);
        return Unit.INSTANCE;
    }

    public static Unit u(FirSession firSession, FirJsSessionFactory firJsSessionFactory, FirJsSessionFactory.Context context) {
        firJsSessionFactory.getClass();
        context.getClass();
        firJsSessionFactory.registerSourceSessionComponents(firSession, context);
        return Unit.INSTANCE;
    }

    public static Unit x(FirSession firSession, FirJvmSessionFactory firJvmSessionFactory, FirJvmSessionFactory.Context context) {
        firJvmSessionFactory.getClass();
        context.getClass();
        firJvmSessionFactory.registerLibrarySessionComponents(firSession, context);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public FirKotlinScopeProvider createKotlinScopeProviderForLibrarySession() {
        return new FirKotlinScopeProvider(null, 1, null);
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public FirKotlinScopeProvider createKotlinScopeProviderForSourceSession(FirModuleData moduleData, LanguageVersionSettings languageVersionSettings) {
        moduleData.getClass();
        languageVersionSettings.getClass();
        return ((Boolean) languageVersionSettings.getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue() ? new FirKotlinScopeProvider(new Function5() { // from class: mn
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                return AbstractFirMetadataSessionFactory.C((FirClass) obj, (FirContainingNamesAwareScope) obj2, (FirSession) obj3, (ScopeSession) obj4, (FirResolvePhase) obj5);
            }
        }) : new FirKotlinScopeProvider(null, 1, null);
    }

    public final FirSession createLibrarySession(FirSession sharedLibrarySession, final ModuleDataProvider moduleDataProvider, List<? extends FirExtensionRegistrar> extensionRegistrars, final JarMetadataProviderComponents jarMetadataProviderComponents, final List<? extends KotlinLibrary> resolvedKLibs, LanguageVersionSettings languageVersionSettings, Context context, final Function4<? super FirSession, ? super ModuleDataProvider, ? super FirKotlinScopeProvider, ? super List<? extends KotlinLibrary>, ? extends List<? extends FirSymbolProvider>> additionalProviders) {
        sharedLibrarySession.getClass();
        moduleDataProvider.getClass();
        extensionRegistrars.getClass();
        resolvedKLibs.getClass();
        languageVersionSettings.getClass();
        context.getClass();
        return createLibrarySession(context, sharedLibrarySession, moduleDataProvider, languageVersionSettings, extensionRegistrars, getCreateSeparateSharedProvidersInHmppCompilation(), new Function2() { // from class: ln
            public final Object invoke(Object obj, Object obj2) {
                return AbstractFirMetadataSessionFactory.i(jarMetadataProviderComponents, resolvedKLibs, additionalProviders, moduleDataProvider, (FirSession) obj, (FirKotlinScopeProvider) obj2);
            }
        });
    }

    public final FirSession createSharedLibrarySession(Name mainModuleName, LanguageVersionSettings languageVersionSettings, List<? extends FirExtensionRegistrar> extensionRegistrars, Context context) {
        mainModuleName.getClass();
        languageVersionSettings.getClass();
        extensionRegistrars.getClass();
        context.getClass();
        return createSharedLibrarySession(mainModuleName, context, languageVersionSettings, extensionRegistrars);
    }

    public final FirSession createSourceSession(final FirModuleData moduleData, final AbstractProjectEnvironment projectEnvironment, final IncrementalCompilationContext incrementalCompilationContext, List<? extends FirExtensionRegistrar> extensionRegistrars, CompilerConfiguration configuration, Context context, boolean isForLeafHmppModule, Function1<? super FirSessionConfigurator, Unit> init) {
        moduleData.getClass();
        projectEnvironment.getClass();
        extensionRegistrars.getClass();
        configuration.getClass();
        context.getClass();
        init.getClass();
        return createSourceSession(moduleData, context, extensionRegistrars, configuration, isForLeafHmppModule, init, new Function4() { // from class: fn
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AbstractFirMetadataSessionFactory.l(incrementalCompilationContext, moduleData, projectEnvironment, (FirSession) obj, (FirKotlinScopeProvider) obj2, (FirSymbolProvider) obj3, (FirSwitchableExtensionDeclarationsSymbolProvider) obj4);
            }
        });
    }

    public abstract boolean getCreateSeparateSharedProvidersInHmppCompilation();

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public boolean getRequiresSpecialSetupOfSourceProvidersInHmppCompilation() {
        return false;
    }

    public final TargetPlatform getTargetPlatform() {
        return this.targetPlatform;
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public boolean isFactoryForMetadataCompilation() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerExtraPlatformCheckers(final FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.withOnlyPlatformSpecificCheckersEnabledInMetadataCompilation(new Function0() { // from class: tm
            public final Object invoke() {
                return AbstractFirMetadataSessionFactory.D(this.b, firSessionConfigurator);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerLibrarySessionComponents(final FirSession firSession, Context context) {
        firSession.getClass();
        context.getClass();
        firSession.register((KClass<? extends FirEnumEntriesSupport>) Reflection.getOrCreateKotlinClass(FirEnumEntriesSupport.class), new FirEnumEntriesSupport(firSession));
        processPlatformsWithContext(context, new Function2() { // from class: en
            public final Object invoke(Object obj, Object obj2) {
                return AbstractFirMetadataSessionFactory.x(firSession, (FirJvmSessionFactory) obj, (FirJvmSessionFactory.Context) obj2);
            }
        }, new Function2() { // from class: nn
            public final Object invoke(Object obj, Object obj2) {
                return AbstractFirMetadataSessionFactory.B(firSession, (FirJsSessionFactory) obj, (FirJsSessionFactory.Context) obj2);
            }
        }, new Function1() { // from class: on
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.p(firSession, (FirWasmSessionFactory.WasmJs) obj);
            }
        }, new Function1() { // from class: pn
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.e(firSession, (FirWasmSessionFactory.WasmWasi) obj);
            }
        }, new Function1() { // from class: qn
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.j(firSession, (FirNativeSessionFactory.ForMetadata) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerPlatformCheckers(final FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.withOnlyPlatformSpecificCheckersEnabledInMetadataCompilation(new Function0() { // from class: dn
            public final Object invoke() {
                return AbstractFirMetadataSessionFactory.n(this.b, firSessionConfigurator);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerSourceSessionComponents(final FirSession firSession, Context context) {
        firSession.getClass();
        context.getClass();
        processPlatformsWithContext(context, new Function2() { // from class: gn
            public final Object invoke(Object obj, Object obj2) {
                return AbstractFirMetadataSessionFactory.h(firSession, (FirJvmSessionFactory) obj, (FirJvmSessionFactory.Context) obj2);
            }
        }, new Function2() { // from class: hn
            public final Object invoke(Object obj, Object obj2) {
                return AbstractFirMetadataSessionFactory.u(firSession, (FirJsSessionFactory) obj, (FirJsSessionFactory.Context) obj2);
            }
        }, new Function1() { // from class: in
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.g(firSession, (FirWasmSessionFactory.WasmJs) obj);
            }
        }, new Function1() { // from class: jn
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.t(firSession, (FirWasmSessionFactory.WasmWasi) obj);
            }
        }, new Function1() { // from class: kn
            public final Object invoke(Object obj) {
                return AbstractFirMetadataSessionFactory.E(firSession, (FirNativeSessionFactory.ForMetadata) obj);
            }
        });
    }
}
