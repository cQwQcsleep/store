package org.jetbrains.kotlin.fir.session;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirBinaryDependenciesModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificCastChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.PlatformConflictDeclarationsDiagnosticDispatcher;
import org.jetbrains.kotlin.fir.analysis.p001native.checkers.FirNativeCastChecker;
import org.jetbrains.kotlin.fir.analysis.p001native.checkers.NativeConflictDeclarationsDiagnosticDispatcher;
import org.jetbrains.kotlin.fir.backend.p002native.FirNativeClassMapper;
import org.jetbrains.kotlin.fir.backend.p002native.FirNativeOverrideChecker;
import org.jetbrains.kotlin.fir.checkers.CheckersContainersKt;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.FirDefaultImportsProviderHolder;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper;
import org.jetbrains.kotlin.fir.scopes.impl.FirEnumEntriesSupport;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.metadata.impl.KlibResolvedModuleDescriptorsFactoryImpl;
import org.jetbrains.kotlin.resolve.konan.platform.NativeDefaultImportsProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 \u001d2\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0002\u001d\u001eB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0014J4\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\tH\u0016J\u0016\u0010\u0013\u001a\u00020\u0014*\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016J\f\u0010\u0016\u001a\u00020\u0014*\u00020\u0017H\u0016J\f\u0010\u0018\u001a\u00020\u0014*\u00020\u0017H\u0016J\u0016\u0010\u0019\u001a\u00020\u0014*\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0014J\f\u0010\u001b\u001a\u00020\u0014*\u00020\fH\u0002J\n\u0010\u001c\u001a\u00020\u0014*\u00020\f¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirNativeSessionFactory;", "Lorg/jetbrains/kotlin/fir/session/AbstractFirKlibSessionFactory;", Argument.Delimiters.none, "<init>", "()V", "createLibraryContext", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "createAdditionalDependencyProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "resolvedLibraries", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "registerLibrarySessionComponents", Argument.Delimiters.none, "c", "registerPlatformCheckers", "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", "registerExtraPlatformCheckers", "registerSourceSessionComponents", "createSourceContext", "registerComponents", "registerNativeComponents", "Companion", "ForMetadata", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirNativeSessionFactory extends AbstractFirKlibSessionFactory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirNativeSessionFactory$ForMetadata;", "Lorg/jetbrains/kotlin/fir/session/FirNativeSessionFactory;", "<init>", "()V", "requiresSpecialSetupOfSourceProvidersInHmppCompilation", Argument.Delimiters.none, "getRequiresSpecialSetupOfSourceProvidersInHmppCompilation", "()Z", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForMetadata extends FirNativeSessionFactory {
        public static final ForMetadata INSTANCE = new ForMetadata();

        private ForMetadata() {
        }

        @Override // org.jetbrains.kotlin.fir.session.AbstractFirKlibSessionFactory, org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
        public boolean getRequiresSpecialSetupOfSourceProvidersInHmppCompilation() {
            return false;
        }
    }

    private final void registerComponents(FirSession firSession) {
        registerNativeComponents(firSession);
    }

    @Override // org.jetbrains.kotlin.fir.session.AbstractFirKlibSessionFactory
    public List<FirSymbolProvider> createAdditionalDependencyProviders(FirSession session, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider kotlinScopeProvider, List<? extends KotlinLibrary> resolvedLibraries) {
        session.getClass();
        moduleDataProvider.getClass();
        kotlinScopeProvider.getClass();
        resolvedLibraries.getClass();
        FirBinaryDependenciesModuleData firBinaryDependenciesModuleData = new FirBinaryDependenciesModuleData(KlibResolvedModuleDescriptorsFactoryImpl.Companion.getFORWARD_DECLARATIONS_MODULE_NAME(), null, 2, null);
        firBinaryDependenciesModuleData.bindSession(session);
        return CollectionsKt.listOf(new NativeForwardDeclarationsSymbolProvider(session, firBinaryDependenciesModuleData, kotlinScopeProvider, resolvedLibraries));
    }

    @Override // org.jetbrains.kotlin.fir.session.AbstractFirKlibSessionFactory
    public Void createLibraryContext(CompilerConfiguration configuration) {
        configuration.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.session.AbstractFirKlibSessionFactory
    public Void createSourceContext(CompilerConfiguration configuration) {
        configuration.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerExtraPlatformCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        CheckersContainersKt.registerExtraNativeCheckers(firSessionConfigurator);
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerLibrarySessionComponents(FirSession firSession, Void r2) {
        firSession.getClass();
        registerComponents(firSession);
    }

    public final void registerNativeComponents(FirSession firSession) {
        firSession.getClass();
        firSession.register((KClass<? extends FirEnumEntriesSupport>) Reflection.getOrCreateKotlinClass(FirEnumEntriesSupport.class), new FirEnumEntriesSupport(firSession));
        firSession.register((KClass<? extends FirNativeClassMapper>) Reflection.getOrCreateKotlinClass(FirPlatformClassMapper.class), new FirNativeClassMapper());
        firSession.register(Reflection.getOrCreateKotlinClass(FirPlatformSpecificCastChecker.class), FirNativeCastChecker.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(PlatformConflictDeclarationsDiagnosticDispatcher.class), NativeConflictDeclarationsDiagnosticDispatcher.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(FirOverrideChecker.class), new FirNativeOverrideChecker(firSession));
        firSession.register((KClass<? extends FirDefaultImportsProviderHolder>) Reflection.getOrCreateKotlinClass(FirDefaultImportsProviderHolder.class), FirDefaultImportsProviderHolder.INSTANCE.of(NativeDefaultImportsProvider.INSTANCE));
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerPlatformCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        CheckersContainersKt.registerNativeCheckers(firSessionConfigurator);
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerSourceSessionComponents(FirSession firSession, Void r2) {
        firSession.getClass();
        registerComponents(firSession);
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirNativeSessionFactory$Companion;", "Lorg/jetbrains/kotlin/fir/session/FirNativeSessionFactory;", "<init>", "()V", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion extends FirNativeSessionFactory {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
