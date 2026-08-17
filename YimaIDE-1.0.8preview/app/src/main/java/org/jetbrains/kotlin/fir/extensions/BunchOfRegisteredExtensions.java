package org.jetbrains.kotlin.fir.extensions;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013BC\b\u0007\u0012&\u0010\u0002\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070\u00060\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u001a\u0002\b\f¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000H\u0086\u0002R1\u0010\u0002\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/BunchOfRegisteredExtensions;", Argument.Delimiters.none, "extensions", Argument.Delimiters.none, "Lkotlin/reflect/KClass;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExtension$Factory;", "diagnosticsContainers", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "(Ljava/util/Map;Ljava/util/List;)V", "Lorg/jetbrains/kotlin/fir/extensions/PluginServicesInitialization;", "getExtensions", "()Ljava/util/Map;", "getDiagnosticsContainers", "()Ljava/util/List;", "plus", "other", "Companion", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BunchOfRegisteredExtensions {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<KtDiagnosticsContainer> diagnosticsContainers;
    private final Map<KClass<? extends FirExtension>, List<FirExtension.Factory<FirExtension>>> extensions;

    /* JADX WARN: Multi-variable type inference failed */
    @PluginServicesInitialization
    public BunchOfRegisteredExtensions(Map<KClass<? extends FirExtension>, ? extends List<? extends FirExtension.Factory<? extends FirExtension>>> map, List<? extends KtDiagnosticsContainer> list) {
        map.getClass();
        list.getClass();
        this.extensions = map;
        this.diagnosticsContainers = list;
    }

    public final List<KtDiagnosticsContainer> getDiagnosticsContainers() {
        return this.diagnosticsContainers;
    }

    public final Map<KClass<? extends FirExtension>, List<FirExtension.Factory<FirExtension>>> getExtensions() {
        return this.extensions;
    }

    public final BunchOfRegisteredExtensions plus(BunchOfRegisteredExtensions other) {
        other.getClass();
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (KClass<? extends FirExtension> kClass : FirExtensionRegistrar.INSTANCE.getAVAILABLE_EXTENSIONS$org_jetbrains_kotlin_entrypoint()) {
            mapCreateMapBuilder.put(kClass, CollectionsKt.plus((Collection) MapsKt.getValue(this.extensions, kClass), (Iterable) MapsKt.getValue(other.extensions, kClass)));
        }
        return new BunchOfRegisteredExtensions(MapsKt.build(mapCreateMapBuilder), CollectionsKt.plus(this.diagnosticsContainers, other.diagnosticsContainers));
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/BunchOfRegisteredExtensions$Companion;", Argument.Delimiters.none, "<init>", "()V", "empty", "Lorg/jetbrains/kotlin/fir/extensions/BunchOfRegisteredExtensions;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BunchOfRegisteredExtensions empty() {
            List<KClass<? extends FirExtension>> aVAILABLE_EXTENSIONS$org_jetbrains_kotlin_entrypoint = FirExtensionRegistrar.INSTANCE.getAVAILABLE_EXTENSIONS$org_jetbrains_kotlin_entrypoint();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(aVAILABLE_EXTENSIONS$org_jetbrains_kotlin_entrypoint, 10)), 16));
            for (Object obj : aVAILABLE_EXTENSIONS$org_jetbrains_kotlin_entrypoint) {
                linkedHashMap.put(obj, CollectionsKt.emptyList());
            }
            return new BunchOfRegisteredExtensions(linkedHashMap, CollectionsKt.emptyList());
        }

        private Companion() {
        }
    }
}
