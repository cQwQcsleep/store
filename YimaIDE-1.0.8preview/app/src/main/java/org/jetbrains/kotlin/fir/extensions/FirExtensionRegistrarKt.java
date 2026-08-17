package org.jetbrains.kotlin.fir.extensions;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirComposedDiagnosticRendererFactoryKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007b\u0002\b\u0005¨\u0006\u0006"}, d2 = {"registerExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;", "registeredExtensions", "Lorg/jetbrains/kotlin/fir/extensions/BunchOfRegisteredExtensions;", "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "org.jetbrains.kotlin:entrypoint"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExtensionRegistrarKt {
    @SessionConfiguration
    public static final void registerExtensions(FirExtensionService firExtensionService, BunchOfRegisteredExtensions bunchOfRegisteredExtensions) {
        firExtensionService.getClass();
        bunchOfRegisteredExtensions.getClass();
        for (Map.Entry<KClass<? extends FirExtension>, List<FirExtension.Factory<FirExtension>>> entry : bunchOfRegisteredExtensions.getExtensions().entrySet()) {
            firExtensionService.registerExtensions(entry.getKey(), entry.getValue());
        }
        for (FirExtensionSessionComponent firExtensionSessionComponent : FirExtensionSessionComponentKt.getExtensionSessionComponents(firExtensionService)) {
            firExtensionService.getSession().register(firExtensionSessionComponent.getComponentClass(), firExtensionSessionComponent);
        }
        FirRegisteredPluginAnnotationsKt.getRegisteredPluginAnnotations(firExtensionService.getSession()).initialize();
        if (firExtensionService.getSession().getKind() == FirSession.Kind.Source) {
            FirComposedDiagnosticRendererFactoryKt.getRegisteredDiagnosticFactoriesStorage(firExtensionService.getSession()).registerDiagnosticContainers(bunchOfRegisteredExtensions.getDiagnosticsContainers());
        }
    }
}
