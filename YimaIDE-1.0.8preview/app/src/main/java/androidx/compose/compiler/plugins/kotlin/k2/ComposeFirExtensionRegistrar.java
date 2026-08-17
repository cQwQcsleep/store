package androidx.compose.compiler.plugins.kotlin.k2;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u0005*\u00060\u0006R\u00020\u0001H\u0014¨\u0006\u0007"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposeFirExtensionRegistrar;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;", "<init>", "()V", "configurePlugin", "", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar$ExtensionRegistrarContext;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeFirExtensionRegistrar extends FirExtensionRegistrar {

    /* JADX INFO: renamed from: androidx.compose.compiler.plugins.kotlin.k2.ComposeFirExtensionRegistrar$configurePlugin$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<FirSession, ComposableFunctionTypeKindExtension> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1, ComposableFunctionTypeKindExtension.class, "<init>", "<init>(Lorg/jetbrains/kotlin/fir/FirSession;)V", 0);
        }

        public final ComposableFunctionTypeKindExtension invoke(FirSession firSession) {
            firSession.getClass();
            return new ComposableFunctionTypeKindExtension(firSession);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.compiler.plugins.kotlin.k2.ComposeFirExtensionRegistrar$configurePlugin$2, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<FirSession, ComposeFirCheckersExtension> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1, ComposeFirCheckersExtension.class, "<init>", "<init>(Lorg/jetbrains/kotlin/fir/FirSession;)V", 0);
        }

        public final ComposeFirCheckersExtension invoke(FirSession firSession) {
            firSession.getClass();
            return new ComposeFirCheckersExtension(firSession);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.compiler.plugins.kotlin.k2.ComposeFirExtensionRegistrar$configurePlugin$3, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function1<FirSession, ComposableTargetSessionStorage> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1, ComposableTargetSessionStorage.class, "<init>", "<init>(Lorg/jetbrains/kotlin/fir/FirSession;)V", 0);
        }

        public final ComposableTargetSessionStorage invoke(FirSession firSession) {
            firSession.getClass();
            return new ComposableTargetSessionStorage(firSession);
        }
    }

    public void configurePlugin(FirExtensionRegistrar.ExtensionRegistrarContext extensionRegistrarContext) {
        extensionRegistrarContext.getClass();
        extensionRegistrarContext.plusFunctionTypeKindExtension(AnonymousClass1.INSTANCE);
        extensionRegistrarContext.plusAdditionalCheckersExtension(AnonymousClass2.INSTANCE);
        extensionRegistrarContext.plusExtensionSessionComponent(AnonymousClass3.INSTANCE);
        extensionRegistrarContext.registerDiagnosticContainers(new KtDiagnosticsContainer[]{ComposeErrors.INSTANCE});
    }
}
