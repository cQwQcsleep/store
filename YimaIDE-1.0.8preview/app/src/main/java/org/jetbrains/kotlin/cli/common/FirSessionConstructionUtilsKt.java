package org.jetbrains.kotlin.cli.common;

import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.FirSessionConstructionUtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DependencyListForCliModule;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.session.AbstractFirKlibSessionFactory;
import org.jetbrains.kotlin.fir.session.AbstractFirMetadataSessionFactory;
import org.jetbrains.kotlin.fir.session.FirJsSessionFactory;
import org.jetbrains.kotlin.fir.session.FirJvmSessionFactory;
import org.jetbrains.kotlin.fir.session.FirMetadataSessionFactory;
import org.jetbrains.kotlin.fir.session.FirNativeSessionFactory;
import org.jetbrains.kotlin.fir.session.FirWasmSessionFactory;
import org.jetbrains.kotlin.fir.session.IncrementalCompilationContext;
import org.jetbrains.kotlin.fir.session.KlibIcData;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.load.kotlin.PackageAndMetadataPartProvider;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.CommonPlatforms;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.js.JsPlatforms;
import org.jetbrains.kotlin.platform.konan.NativePlatforms;
import org.jetbrains.kotlin.platform.wasm.WasmPlatforms;
import org.jetbrains.kotlin.platform.wasm.WasmTarget;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.multiplatform.IsCommonSourceKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeys;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0092\u0001\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0012\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00122\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00030\u00012\u0018\u0010!\u001a\u0014\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00062\b\u0010\"\u001a\u0004\u0018\u00010#\u001a\u0090\u0001\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0012\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00122\u0006\u0010%\u001a\u00020\u00032\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00030\u00012\u0018\u0010!\u001a\u0014\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0006\u001a\u0092\u0001\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0012\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00122\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00030\u00012\u0018\u0010!\u001a\u0014\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00062\b\u0010\"\u001a\u0004\u0018\u00010#\u001a°\u0001\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0012\"\u0004\b\u0000\u0010\u00142\n\u0010(\u001a\u0006\u0012\u0002\b\u00030)2\u0006\u0010*\u001a\u00020+2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00122\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00030\u00012\u0018\u0010!\u001a\u0014\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00062\u0006\u0010%\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002\u001a´\u0001\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0012\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010-\u001a\u00020.2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00122\u0006\u0010/\u001a\u0002002\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00030\u00012\u0018\u0010!\u001a\u0014\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00062\u001a\u00101\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u0012\u0012\u0006\u0012\u0004\u0018\u0001020\u0001\"\u001d\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0000\u0010\u0004\"#\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"!\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\f8F¢\u0006\u0006\u001a\u0004\b\n\u0010\r\"'\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0006*\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u00063"}, d2 = {"isCommonSourceForPsi", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/psi/KtFile;", Argument.Delimiters.none, "()Lkotlin/jvm/functions/Function1;", "fileBelongsToModuleForPsi", "Lkotlin/Function2;", Argument.Delimiters.none, "getFileBelongsToModuleForPsi", "()Lkotlin/jvm/functions/Function2;", "isCommonSourceForLt", "Lorg/jetbrains/kotlin/KtSourceFile;", "Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;", "(Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;)Lkotlin/jvm/functions/Function1;", "fileBelongsToModuleForLt", "getFileBelongsToModuleForLt", "(Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;)Lkotlin/jvm/functions/Function2;", "prepareJsSessions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/SessionWithSources;", "F", "files", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "rootModuleName", "Lorg/jetbrains/kotlin/name/Name;", "resolvedLibraries", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "libraryList", "Lorg/jetbrains/kotlin/fir/DependencyListForCliModule;", "extensionRegistrars", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;", "isCommonSource", "fileBelongsToModule", "icData", "Lorg/jetbrains/kotlin/fir/session/KlibIcData;", "prepareNativeSessions", "metadataCompilationMode", "prepareWasmSessions", "prepareKlibSessions", "sessionFactory", "Lorg/jetbrains/kotlin/fir/session/AbstractFirKlibSessionFactory;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "prepareMetadataSessions", "projectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "librariesScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "createProviderAndScopeForIncrementalCompilation", "Lorg/jetbrains/kotlin/fir/session/IncrementalCompilationContext;", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSessionConstructionUtilsKt {
    private static final Function1<KtFile, Boolean> isCommonSourceForPsi = new Function1() { // from class: ld5
        public final Object invoke(Object obj) {
            return Boolean.valueOf(FirSessionConstructionUtilsKt.r((KtFile) obj));
        }
    };
    private static final Function2<KtFile, String, Boolean> fileBelongsToModuleForPsi = new Function2() { // from class: md5
        public final Object invoke(Object obj, Object obj2) {
            return Boolean.valueOf(FirSessionConstructionUtilsKt.i((KtFile) obj, (String) obj2));
        }
    };

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WasmTarget.values().length];
            try {
                iArr[WasmTarget.JS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WasmTarget.WASI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static boolean a(Object obj) {
        return false;
    }

    public static FirSession b(FirMetadataSessionFactory firMetadataSessionFactory, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, Function1 function1, List list, CompilerConfiguration compilerConfiguration, AbstractFirMetadataSessionFactory.Context context, List list2, FirModuleData firModuleData, boolean z, Function1 function2) {
        list2.getClass();
        firModuleData.getClass();
        function2.getClass();
        return firMetadataSessionFactory.createSourceSession(firModuleData, vfsBasedProjectEnvironment, (IncrementalCompilationContext) function1.invoke(list2), list, compilerConfiguration, context, z, function2);
    }

    public static boolean c(GroupedKtSources groupedKtSources, KtSourceFile ktSourceFile) {
        ktSourceFile.getClass();
        return groupedKtSources.getCommonSources().contains(ktSourceFile);
    }

    public static boolean d(GroupedKtSources groupedKtSources, KtSourceFile ktSourceFile, String str) {
        ktSourceFile.getClass();
        str.getClass();
        Set<KtSourceFile> setEmptySet = groupedKtSources.getSourcesByModuleName().get(str);
        if (setEmptySet == null) {
            setEmptySet = SetsKt.emptySet();
        }
        return setEmptySet.contains(ktSourceFile);
    }

    public static FirJsSessionFactory.Context e(CompilerConfiguration compilerConfiguration) {
        return new FirJsSessionFactory.Context(compilerConfiguration);
    }

    public static FirSession f(FirMetadataSessionFactory firMetadataSessionFactory, DependencyListForCliModule dependencyListForCliModule, List list, PackageAndMetadataPartProvider packageAndMetadataPartProvider, AbstractProjectFileSearchScope abstractProjectFileSearchScope, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, List list2, LanguageVersionSettings languageVersionSettings, AbstractFirMetadataSessionFactory.Context context, FirSession firSession) {
        firSession.getClass();
        return AbstractFirMetadataSessionFactory.createLibrarySession$default(firMetadataSessionFactory, firSession, dependencyListForCliModule.getModuleDataProvider(), list, new AbstractFirMetadataSessionFactory.JarMetadataProviderComponents(packageAndMetadataPartProvider, abstractProjectFileSearchScope, vfsBasedProjectEnvironment), list2, languageVersionSettings, context, null, 128, null);
    }

    public static AbstractFirMetadataSessionFactory.Context g(final CompilerConfiguration compilerConfiguration) {
        return new AbstractFirMetadataSessionFactory.Context(new Function0() { // from class: nd5
            public final Object invoke() {
                return FirSessionConstructionUtilsKt.prepareKlibSessions$lambda$1$0();
            }
        }, new Function0() { // from class: od5
            public final Object invoke() {
                return FirSessionConstructionUtilsKt.prepareKlibSessions$lambda$1$1(compilerConfiguration);
            }
        });
    }

    public static final Function2<KtSourceFile, String, Boolean> getFileBelongsToModuleForLt(final GroupedKtSources groupedKtSources) {
        groupedKtSources.getClass();
        return new Function2() { // from class: pd5
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(FirSessionConstructionUtilsKt.d(groupedKtSources, (KtSourceFile) obj, (String) obj2));
            }
        };
    }

    public static final Function2<KtFile, String, Boolean> getFileBelongsToModuleForPsi() {
        return fileBelongsToModuleForPsi;
    }

    public static FirJvmSessionFactory.Context h(CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, AbstractProjectFileSearchScope abstractProjectFileSearchScope) {
        return new FirJvmSessionFactory.Context(compilerConfiguration, vfsBasedProjectEnvironment, abstractProjectFileSearchScope, false);
    }

    public static boolean i(KtFile ktFile, String str) {
        ktFile.getClass();
        str.getClass();
        return Intrinsics.areEqual(IsCommonSourceKt.getHmppModuleName(ktFile), str);
    }

    public static final Function1<KtSourceFile, Boolean> isCommonSourceForLt(final GroupedKtSources groupedKtSources) {
        groupedKtSources.getClass();
        return new Function1() { // from class: wd5
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirSessionConstructionUtilsKt.c(groupedKtSources, (KtSourceFile) obj));
            }
        };
    }

    public static final Function1<KtFile, Boolean> isCommonSourceForPsi() {
        return isCommonSourceForPsi;
    }

    public static FirSession j(FirMetadataSessionFactory firMetadataSessionFactory, Name name, LanguageVersionSettings languageVersionSettings, List list, AbstractFirMetadataSessionFactory.Context context) {
        return firMetadataSessionFactory.createSharedLibrarySession(name, languageVersionSettings, (List<? extends FirExtensionRegistrar>) list, context);
    }

    public static AbstractFirMetadataSessionFactory.Context k(AbstractFirMetadataSessionFactory.Context context) {
        return context;
    }

    public static boolean l(Object obj) {
        return false;
    }

    public static FirSession n(AbstractFirKlibSessionFactory abstractFirKlibSessionFactory, List list, DependencyListForCliModule dependencyListForCliModule, List list2, CompilerConfiguration compilerConfiguration, FirSession firSession) {
        firSession.getClass();
        return abstractFirKlibSessionFactory.createLibrarySession(list, firSession, dependencyListForCliModule.getModuleDataProvider(), list2, compilerConfiguration);
    }

    public static FirSession o(AbstractFirKlibSessionFactory abstractFirKlibSessionFactory, List list, CompilerConfiguration compilerConfiguration, KlibIcData klibIcData, List list2, FirModuleData firModuleData, boolean z, Function1 function1) {
        list2.getClass();
        firModuleData.getClass();
        function1.getClass();
        return abstractFirKlibSessionFactory.createSourceSession(firModuleData, list, compilerConfiguration, z, klibIcData, function1);
    }

    public static final <F> List<SessionWithSources<F>> prepareJsSessions(List<? extends F> list, CompilerConfiguration compilerConfiguration, Name name, List<? extends KotlinLibrary> list2, DependencyListForCliModule dependencyListForCliModule, List<? extends FirExtensionRegistrar> list3, Function1<? super F, Boolean> function1, Function2<? super F, ? super String, Boolean> function2, KlibIcData klibIcData) {
        list.getClass();
        compilerConfiguration.getClass();
        name.getClass();
        list2.getClass();
        dependencyListForCliModule.getClass();
        list3.getClass();
        function1.getClass();
        function2.getClass();
        return prepareKlibSessions(FirJsSessionFactory.INSTANCE, JsPlatforms.INSTANCE.getDefaultJsPlatform(), list, compilerConfiguration, name, list2, dependencyListForCliModule, list3, function1, function2, false, klibIcData);
    }

    private static final <F> List<SessionWithSources<F>> prepareKlibSessions(final AbstractFirKlibSessionFactory<?> abstractFirKlibSessionFactory, TargetPlatform targetPlatform, List<? extends F> list, final CompilerConfiguration compilerConfiguration, final Name name, final List<? extends KotlinLibrary> list2, final DependencyListForCliModule dependencyListForCliModule, final List<? extends FirExtensionRegistrar> list3, Function1<? super F, Boolean> function1, Function2<? super F, ? super String, Boolean> function2, boolean z, final KlibIcData klibIcData) {
        return SessionConstructionUtils.INSTANCE.prepareSessions(list, compilerConfiguration, name, targetPlatform, z, dependencyListForCliModule, list3, function1, new Function1() { // from class: xd5
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirSessionConstructionUtilsKt.l(obj));
            }
        }, function2, new Function0() { // from class: yd5
            public final Object invoke() {
                return FirSessionConstructionUtilsKt.g(compilerConfiguration);
            }
        }, new Function0() { // from class: hd5
            public final Object invoke() {
                return FirSessionConstructionUtilsKt.q(abstractFirKlibSessionFactory, name, compilerConfiguration, list3);
            }
        }, new Function1() { // from class: id5
            public final Object invoke(Object obj) {
                return FirSessionConstructionUtilsKt.n(abstractFirKlibSessionFactory, list2, dependencyListForCliModule, list3, compilerConfiguration, (FirSession) obj);
            }
        }, new FirSessionProducer() { // from class: jd5
            @Override // org.jetbrains.kotlin.cli.common.FirSessionProducer
            public final FirSession createSession(List list4, FirModuleData firModuleData, boolean z2, Function1 function3) {
                return FirSessionConstructionUtilsKt.o(abstractFirKlibSessionFactory, list3, compilerConfiguration, klibIcData, list4, firModuleData, z2, function3);
            }
        }, new Function4() { // from class: kd5
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return FirSessionConstructionUtilsKt.s(abstractFirKlibSessionFactory, (FirSession) obj, (ModuleDataProvider) obj2, (FirKotlinScopeProvider) obj3, (List) obj4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public static final FirJvmSessionFactory.Context prepareKlibSessions$lambda$1$0() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirJsSessionFactory.Context prepareKlibSessions$lambda$1$1(CompilerConfiguration compilerConfiguration) {
        return new FirJsSessionFactory.Context(compilerConfiguration);
    }

    public static final <F> List<SessionWithSources<F>> prepareMetadataSessions(List<? extends F> list, final CompilerConfiguration compilerConfiguration, final VfsBasedProjectEnvironment vfsBasedProjectEnvironment, final Name name, final List<? extends FirExtensionRegistrar> list2, final AbstractProjectFileSearchScope abstractProjectFileSearchScope, final DependencyListForCliModule dependencyListForCliModule, final List<? extends KotlinLibrary> list3, Function1<? super F, Boolean> function1, Function2<? super F, ? super String, Boolean> function2, final Function1<? super List<? extends F>, IncrementalCompilationContext> function3) {
        list.getClass();
        compilerConfiguration.getClass();
        vfsBasedProjectEnvironment.getClass();
        name.getClass();
        list2.getClass();
        abstractProjectFileSearchScope.getClass();
        dependencyListForCliModule.getClass();
        list3.getClass();
        function1.getClass();
        function2.getClass();
        function3.getClass();
        PackagePartProvider packagePartProvider = vfsBasedProjectEnvironment.getPackagePartProvider(abstractProjectFileSearchScope);
        packagePartProvider.getClass();
        final PackageAndMetadataPartProvider packageAndMetadataPartProvider = (PackageAndMetadataPartProvider) packagePartProvider;
        final LanguageVersionSettings languageVersionSettings = CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration);
        TargetPlatform targetPlatform = CommonConfigurationKeysKt.getTargetPlatform(compilerConfiguration);
        if (targetPlatform == null) {
            targetPlatform = CommonPlatforms.INSTANCE.getDefaultCommonPlatform();
        }
        final FirMetadataSessionFactory firMetadataSessionFactory = new FirMetadataSessionFactory(targetPlatform);
        final AbstractFirMetadataSessionFactory.Context context = new AbstractFirMetadataSessionFactory.Context(new Function0() { // from class: gd5
            public final Object invoke() {
                return FirSessionConstructionUtilsKt.h(compilerConfiguration, vfsBasedProjectEnvironment, abstractProjectFileSearchScope);
            }
        }, new Function0() { // from class: qd5
            public final Object invoke() {
                return FirSessionConstructionUtilsKt.e(compilerConfiguration);
            }
        });
        return SessionConstructionUtils.INSTANCE.prepareSessions(list, compilerConfiguration, name, targetPlatform, true, dependencyListForCliModule, list2, function1, new Function1() { // from class: rd5
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirSessionConstructionUtilsKt.a(obj));
            }
        }, function2, new Function0() { // from class: sd5
            public final Object invoke() {
                return FirSessionConstructionUtilsKt.k(context);
            }
        }, new Function0() { // from class: td5
            public final Object invoke() {
                return FirSessionConstructionUtilsKt.j(firMetadataSessionFactory, name, languageVersionSettings, list2, context);
            }
        }, new Function1() { // from class: ud5
            public final Object invoke(Object obj) {
                return FirSessionConstructionUtilsKt.f(firMetadataSessionFactory, dependencyListForCliModule, list2, packageAndMetadataPartProvider, abstractProjectFileSearchScope, vfsBasedProjectEnvironment, list3, languageVersionSettings, context, (FirSession) obj);
            }
        }, new FirSessionProducer() { // from class: vd5
            @Override // org.jetbrains.kotlin.cli.common.FirSessionProducer
            public final FirSession createSession(List list4, FirModuleData firModuleData, boolean z, Function1 function4) {
                return FirSessionConstructionUtilsKt.b(firMetadataSessionFactory, vfsBasedProjectEnvironment, function3, list2, compilerConfiguration, context, list4, firModuleData, z, function4);
            }
        }, (16384 & 16384) != 0 ? null : null);
    }

    public static final <F> List<SessionWithSources<F>> prepareNativeSessions(List<? extends F> list, CompilerConfiguration compilerConfiguration, Name name, List<? extends KotlinLibrary> list2, DependencyListForCliModule dependencyListForCliModule, List<? extends FirExtensionRegistrar> list3, boolean z, Function1<? super F, Boolean> function1, Function2<? super F, ? super String, Boolean> function2) {
        list.getClass();
        compilerConfiguration.getClass();
        name.getClass();
        list2.getClass();
        dependencyListForCliModule.getClass();
        list3.getClass();
        function1.getClass();
        function2.getClass();
        return prepareKlibSessions(z ? FirNativeSessionFactory.ForMetadata.INSTANCE : FirNativeSessionFactory.INSTANCE, NativePlatforms.INSTANCE.getUnspecifiedNativePlatform(), list, compilerConfiguration, name, list2, dependencyListForCliModule, list3, function1, function2, z, null);
    }

    public static final <F> List<SessionWithSources<F>> prepareWasmSessions(List<? extends F> list, CompilerConfiguration compilerConfiguration, Name name, List<? extends KotlinLibrary> list2, DependencyListForCliModule dependencyListForCliModule, List<? extends FirExtensionRegistrar> list3, Function1<? super F, Boolean> function1, Function2<? super F, ? super String, Boolean> function2, KlibIcData klibIcData) {
        TargetPlatform wasmJs;
        list.getClass();
        compilerConfiguration.getClass();
        name.getClass();
        list2.getClass();
        dependencyListForCliModule.getClass();
        list3.getClass();
        function1.getClass();
        function2.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[((WasmTarget) compilerConfiguration.get(WasmConfigurationKeys.WASM_TARGET, WasmTarget.JS)).ordinal()];
        if (i == 1) {
            wasmJs = WasmPlatforms.INSTANCE.getWasmJs();
        } else {
            if (i != 2) {
                bu8.a();
                return null;
            }
            wasmJs = WasmPlatforms.INSTANCE.getWasmWasi();
        }
        return prepareKlibSessions(FirWasmSessionFactory.INSTANCE.of(WasmConfigurationKeysKt.getWasmTarget(compilerConfiguration)), wasmJs, list, compilerConfiguration, name, list2, dependencyListForCliModule, list3, function1, function2, false, klibIcData);
    }

    public static FirSession q(AbstractFirKlibSessionFactory abstractFirKlibSessionFactory, Name name, CompilerConfiguration compilerConfiguration, List list) {
        return abstractFirKlibSessionFactory.createSharedLibrarySession(name, compilerConfiguration, list);
    }

    public static boolean r(KtFile ktFile) {
        ktFile.getClass();
        return Intrinsics.areEqual(IsCommonSourceKt.isCommonSource(ktFile), Boolean.TRUE);
    }

    public static List s(AbstractFirKlibSessionFactory abstractFirKlibSessionFactory, FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, List list) {
        firSession.getClass();
        moduleDataProvider.getClass();
        firKotlinScopeProvider.getClass();
        list.getClass();
        return abstractFirKlibSessionFactory.createAdditionalDependencyProviders(firSession, moduleDataProvider, firKotlinScopeProvider, list);
    }
}
