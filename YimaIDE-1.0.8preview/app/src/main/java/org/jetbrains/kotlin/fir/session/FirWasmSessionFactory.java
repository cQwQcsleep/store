package org.jetbrains.kotlin.fir.session;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificCastChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificEqualityChecker;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.FirWasmJsCastChecker;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.FirWasmJsEqualityChecker;
import org.jetbrains.kotlin.fir.checkers.CheckersContainersKt;
import org.jetbrains.kotlin.fir.scopes.FirDefaultImportsProviderHolder;
import org.jetbrains.kotlin.fir.scopes.impl.FirEnumEntriesSupport;
import org.jetbrains.kotlin.platform.wasm.WasmTarget;
import org.jetbrains.kotlin.resolve.DefaultImportsProvider;
import org.jetbrains.kotlin.wasm.resolve.WasmJsDefaultImportsProvider;
import org.jetbrains.kotlin.wasm.resolve.WasmWasiDefaultImportsProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00192\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0003\u0017\u0018\u0019B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0016\u0010\f\u001a\u00020\r*\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0014J\f\u0010\u0011\u001a\u00020\r*\u00020\u0012H&J\f\u0010\u0013\u001a\u00020\r*\u00020\u0012H\u0016J\u0016\u0010\u0014\u001a\u00020\r*\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\f\u0010\u0015\u001a\u00020\r*\u00020\u000eH&J\f\u0010\u0016\u001a\u00020\r*\u00020\u000eH\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0002\u001a\u001b¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory;", "Lorg/jetbrains/kotlin/fir/session/AbstractFirKlibSessionFactory;", Argument.Delimiters.none, "<init>", "()V", "defaultImportsProvider", "Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "getDefaultImportsProvider", "()Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "createLibraryContext", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "registerLibrarySessionComponents", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirSession;", "c", "createSourceContext", "registerPlatformCheckers", "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", "registerExtraPlatformCheckers", "registerSourceSessionComponents", "registerWasmComponents", "registerCommonWasmComponents", "WasmJs", "WasmWasi", "Companion", "Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory$WasmJs;", "Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory$WasmWasi;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirWasmSessionFactory extends AbstractFirKlibSessionFactory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\b\u001a\u00020\t*\u00020\nH\u0016J\f\u0010\u000b\u001a\u00020\t*\u00020\fH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory$WasmJs;", "Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory;", "<init>", "()V", "defaultImportsProvider", "Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "getDefaultImportsProvider", "()Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "registerWasmComponents", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirSession;", "registerPlatformCheckers", "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class WasmJs extends FirWasmSessionFactory {
        public static final WasmJs INSTANCE = new WasmJs();

        private WasmJs() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.fir.session.FirWasmSessionFactory
        public DefaultImportsProvider getDefaultImportsProvider() {
            return WasmJsDefaultImportsProvider.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.session.FirWasmSessionFactory, org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
        public void registerPlatformCheckers(FirSessionConfigurator firSessionConfigurator) {
            firSessionConfigurator.getClass();
            CheckersContainersKt.registerWasmJsCheckers(firSessionConfigurator);
        }

        @Override // org.jetbrains.kotlin.fir.session.FirWasmSessionFactory
        public void registerWasmComponents(FirSession firSession) {
            firSession.getClass();
            registerCommonWasmComponents(firSession);
            firSession.register(Reflection.getOrCreateKotlinClass(FirPlatformSpecificCastChecker.class), FirWasmJsCastChecker.INSTANCE);
            firSession.register(Reflection.getOrCreateKotlinClass(FirPlatformSpecificEqualityChecker.class), FirWasmJsEqualityChecker.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\b\u001a\u00020\t*\u00020\nH\u0016J\f\u0010\u000b\u001a\u00020\t*\u00020\fH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory$WasmWasi;", "Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory;", "<init>", "()V", "defaultImportsProvider", "Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "getDefaultImportsProvider", "()Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "registerWasmComponents", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirSession;", "registerPlatformCheckers", "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class WasmWasi extends FirWasmSessionFactory {
        public static final WasmWasi INSTANCE = new WasmWasi();

        private WasmWasi() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.fir.session.FirWasmSessionFactory
        public DefaultImportsProvider getDefaultImportsProvider() {
            return WasmWasiDefaultImportsProvider.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.session.FirWasmSessionFactory, org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
        public void registerPlatformCheckers(FirSessionConfigurator firSessionConfigurator) {
            firSessionConfigurator.getClass();
            CheckersContainersKt.registerWasmWasiCheckers(firSessionConfigurator);
        }

        @Override // org.jetbrains.kotlin.fir.session.FirWasmSessionFactory
        public void registerWasmComponents(FirSession firSession) {
            firSession.getClass();
            registerCommonWasmComponents(firSession);
        }
    }

    public /* synthetic */ FirWasmSessionFactory(DefaultConstructorMarker defaultConstructorMarker) {
        this();
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

    public abstract DefaultImportsProvider getDefaultImportsProvider();

    public final void registerCommonWasmComponents(FirSession firSession) {
        firSession.getClass();
        firSession.register((KClass<? extends FirEnumEntriesSupport>) Reflection.getOrCreateKotlinClass(FirEnumEntriesSupport.class), new FirEnumEntriesSupport(firSession));
        firSession.register((KClass<? extends FirDefaultImportsProviderHolder>) Reflection.getOrCreateKotlinClass(FirDefaultImportsProviderHolder.class), FirDefaultImportsProviderHolder.INSTANCE.of(getDefaultImportsProvider()));
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerExtraPlatformCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerLibrarySessionComponents(FirSession firSession, Void r2) {
        firSession.getClass();
        registerWasmComponents(firSession);
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public abstract void registerPlatformCheckers(FirSessionConfigurator firSessionConfigurator);

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerSourceSessionComponents(FirSession firSession, Void r2) {
        firSession.getClass();
        registerWasmComponents(firSession);
    }

    public abstract void registerWasmComponents(FirSession firSession);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory$Companion;", Argument.Delimiters.none, "<init>", "()V", "of", "Lorg/jetbrains/kotlin/fir/session/FirWasmSessionFactory;", "wasmTarget", "Lorg/jetbrains/kotlin/platform/wasm/WasmTarget;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
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

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirWasmSessionFactory of(WasmTarget wasmTarget) {
            wasmTarget.getClass();
            int i = WhenMappings.$EnumSwitchMapping$0[wasmTarget.ordinal()];
            if (i == 1) {
                return WasmJs.INSTANCE;
            }
            if (i == 2) {
                return WasmWasi.INSTANCE;
            }
            bu8.a();
            return null;
        }

        private Companion() {
        }
    }

    private FirWasmSessionFactory() {
    }
}
