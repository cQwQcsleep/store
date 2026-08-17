package org.jetbrains.kotlin.fir.extensions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension;
import org.jetbrains.kotlin.fir.backend.Fir2IrReplSnippetConfiguratorExtension;
import org.jetbrains.kotlin.fir.backend.Fir2IrScriptConfiguratorExtension;
import org.jetbrains.kotlin.fir.builder.FirReplSnippetConfiguratorExtension;
import org.jetbrains.kotlin.fir.builder.FirScriptConfiguratorExtension;
import org.jetbrains.kotlin.fir.extensions.FirExtension;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.resolve.FirSamConversionTransformerExtension;
import org.jetbrains.kotlin.fir.serialization.FirMetadataSerializerPlugin;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\b&\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u0005*\u00060\u0006R\u00020\u0000H$J\u0006\u0010\u0007\u001a\u00020\bJ0\u0010\u0017\u001a\u00020\u0005\"\b\b\u0000\u0010\u0018*\u00020\f2\u000e\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00180\u000b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u000eH\u0002R.\u0010\t\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000e0\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000R;\u0010\u0011\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000e0\u00120\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrarAdapter;", "<init>", "()V", "configurePlugin", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar$ExtensionRegistrarContext;", "configure", "Lorg/jetbrains/kotlin/fir/extensions/BunchOfRegisteredExtensions;", "extensionFactories", Argument.Delimiters.none, "Lkotlin/reflect/KClass;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExtension$Factory;", "diagnosticsContainers", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "configuredExtensionFactories", Argument.Delimiters.none, "getConfiguredExtensionFactories", "()Ljava/util/Map;", "configuredExtensionFactories$delegate", "Lkotlin/Lazy;", "registerExtension", "P", "kClass", "factory", "Companion", "ExtensionRegistrarContext", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirExtensionRegistrar extends FirExtensionRegistrarAdapter {

    /* JADX INFO: renamed from: configuredExtensionFactories$delegate, reason: from kotlin metadata */
    private final Lazy configuredExtensionFactories;
    private final List<KtDiagnosticsContainer> diagnosticsContainers;
    private final Map<KClass<? extends FirExtension>, List<FirExtension.Factory<FirExtension>>> extensionFactories;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<KClass<? extends FirExtension>> AVAILABLE_EXTENSIONS = CollectionsKt.listOf(new KClass[]{Reflection.getOrCreateKotlinClass(FirStatusTransformerExtension.class), Reflection.getOrCreateKotlinClass(FirDeclarationGenerationExtension.class), Reflection.getOrCreateKotlinClass(FirAdditionalCheckersExtension.class), Reflection.getOrCreateKotlinClass(FirSupertypeGenerationExtension.class), Reflection.getOrCreateKotlinClass(FirTypeAttributeExtension.class), Reflection.getOrCreateKotlinClass(FirExpressionResolutionExtension.class), Reflection.getOrCreateKotlinClass(FirExtensionSessionComponent.class), Reflection.getOrCreateKotlinClass(FirSamConversionTransformerExtension.class), Reflection.getOrCreateKotlinClass(FirAssignExpressionAltererExtension.class), Reflection.getOrCreateKotlinClass(FirScriptConfiguratorExtension.class), Reflection.getOrCreateKotlinClass(FirScriptResolutionConfigurationExtension.class), Reflection.getOrCreateKotlinClass(Fir2IrScriptConfiguratorExtension.class), Reflection.getOrCreateKotlinClass(Fir2IrReplSnippetConfiguratorExtension.class), Reflection.getOrCreateKotlinClass(FirReplSnippetConfiguratorExtension.class), Reflection.getOrCreateKotlinClass(FirFunctionTypeKindExtension.class), Reflection.getOrCreateKotlinClass(FirMetadataSerializerPlugin.class), Reflection.getOrCreateKotlinClass(FirFunctionCallRefinementExtension.class)});
    private static final List<KClass<? extends FirExtension>> ALLOWED_EXTENSIONS_FOR_LIBRARY_SESSION = CollectionsKt.listOf(new KClass[]{Reflection.getOrCreateKotlinClass(FirTypeAttributeExtension.class), Reflection.getOrCreateKotlinClass(FirFunctionTypeKindExtension.class)});

    public FirExtensionRegistrar() {
        List<KClass<? extends FirExtension>> list = AVAILABLE_EXTENSIONS;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(obj, new ArrayList());
        }
        this.extensionFactories = linkedHashMap;
        this.diagnosticsContainers = new ArrayList();
        this.configuredExtensionFactories = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new Function0() { // from class: c75
            public final Object invoke() {
                return FirExtensionRegistrar.a(this.b);
            }
        });
    }

    public static Map a(FirExtensionRegistrar firExtensionRegistrar) {
        firExtensionRegistrar.configurePlugin(firExtensionRegistrar.new ExtensionRegistrarContext());
        return firExtensionRegistrar.extensionFactories;
    }

    private final Map<KClass<? extends FirExtension>, List<FirExtension.Factory<FirExtension>>> getConfiguredExtensionFactories() {
        return (Map) this.configuredExtensionFactories.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <P extends FirExtension> void registerExtension(KClass<? extends P> kClass, FirExtension.Factory<? extends P> factory) {
        ((List) MapsKt.getValue(this.extensionFactories, kClass)).add(factory);
    }

    public final BunchOfRegisteredExtensions configure() {
        return new BunchOfRegisteredExtensions(getConfiguredExtensionFactories(), this.diagnosticsContainers);
    }

    public abstract void configurePlugin(ExtensionRegistrarContext extensionRegistrarContext);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\"\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar$Companion;", Argument.Delimiters.none, "<init>", "()V", "AVAILABLE_EXTENSIONS", Argument.Delimiters.none, "Lkotlin/reflect/KClass;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", "getAVAILABLE_EXTENSIONS$org_jetbrains_kotlin_entrypoint", "()Ljava/util/List;", "ALLOWED_EXTENSIONS_FOR_LIBRARY_SESSION", "getALLOWED_EXTENSIONS_FOR_LIBRARY_SESSION$org_jetbrains_kotlin_entrypoint", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<KClass<? extends FirExtension>> getALLOWED_EXTENSIONS_FOR_LIBRARY_SESSION$org_jetbrains_kotlin_entrypoint() {
            return FirExtensionRegistrar.ALLOWED_EXTENSIONS_FOR_LIBRARY_SESSION;
        }

        public final List<KClass<? extends FirExtension>> getAVAILABLE_EXTENSIONS$org_jetbrains_kotlin_entrypoint() {
            return FirExtensionRegistrar.AVAILABLE_EXTENSIONS;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0084\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\u0007J \u0010\u0004\u001a\u00020\u0005*\u00020\nH\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b¢\u0006\u0002\b\u000bJ \u0010\u0004\u001a\u00020\u0005*\u00020\fH\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r¢\u0006\u0002\b\rJ \u0010\u0004\u001a\u00020\u0005*\u00020\u000eH\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000f¢\u0006\u0002\b\u000fJ \u0010\u0004\u001a\u00020\u0005*\u00020\u0010H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0011¢\u0006\u0002\b\u0011J \u0010\u0004\u001a\u00020\u0005*\u00020\u0012H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0013¢\u0006\u0002\b\u0013J \u0010\u0004\u001a\u00020\u0005*\u00020\u0014H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0015¢\u0006\u0002\b\u0015J \u0010\u0004\u001a\u00020\u0005*\u00020\u0016H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0017¢\u0006\u0002\b\u0017J \u0010\u0004\u001a\u00020\u0005*\u00020\u0018H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0019¢\u0006\u0002\b\u0019J \u0010\u0004\u001a\u00020\u0005*\u00020\u001aH\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u001b¢\u0006\u0002\b\u001bJ \u0010\u0004\u001a\u00020\u0005*\u00020\u001cH\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u001d¢\u0006\u0002\b\u001dJ \u0010\u0004\u001a\u00020\u0005*\u00020\u001eH\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u001f¢\u0006\u0002\b\u001fJ \u0010\u0004\u001a\u00020\u0005*\u00020 H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(!¢\u0006\u0002\b!J \u0010\u0004\u001a\u00020\u0005*\u00020\"H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(#¢\u0006\u0002\b#J \u0010\u0004\u001a\u00020\u0005*\u00020$H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(%¢\u0006\u0002\b%J$\u0010\u0004\u001a\u00020\u0005*\u00020&H\u0087\u0002b\u0002\b(b\f\b\b\u0012\b\b\t\u0012\u0004\b\b('¢\u0006\u0002\b'J$\u0010\u0004\u001a\u00020\u0005*\u00020)H\u0087\u0002b\u0002\b(b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(*¢\u0006\u0002\b*J,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\u0007J,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020.0+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b¢\u0006\u0002\b\u000bJ,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020/0+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r¢\u0006\u0002\b\rJ,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002000+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000f¢\u0006\u0002\b\u000fJ,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002010+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0011¢\u0006\u0002\b\u0011J,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002020+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0013¢\u0006\u0002\b\u0013J,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002030+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0015¢\u0006\u0002\b\u0015J,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002040+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0017¢\u0006\u0002\b\u0017J,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002050+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0019¢\u0006\u0002\b\u0019J,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002060+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u001b¢\u0006\u0002\b\u001bJ,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002070+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u001d¢\u0006\u0002\b\u001dJ,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002080+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u001f¢\u0006\u0002\b\u001fJ,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002090+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(!¢\u0006\u0002\b!J,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020:0+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(#¢\u0006\u0002\b#J,\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020;0+H\u0087\u0002b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(%¢\u0006\u0002\b%J0\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020<0+H\u0087\u0002b\u0002\b(b\f\b\b\u0012\b\b\t\u0012\u0004\b\b('¢\u0006\u0002\b'J0\u0010\u0004\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020=0+H\u0087\u0002b\u0002\b(b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(*¢\u0006\u0002\b*J\u001f\u0010>\u001a\u00020\u00052\u0012\u0010?\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0@\"\u00020A¢\u0006\u0002\u0010BJS\u0010C\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002HD0+\"\u0004\b\u0000\u0010E\"\u0004\b\u0001\u0010D*\u0014\u0012\u0004\u0012\u0002HE\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002HD0F2\u0006\u0010G\u001a\u0002HEH\u0007b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(H¢\u0006\u0004\bH\u0010IJS\u0010C\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002HD0+\"\u0004\b\u0000\u0010E\"\u0004\b\u0001\u0010D*\u0014\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u0002HE\u0012\u0004\u0012\u0002HD0F2\u0006\u0010G\u001a\u0002HEH\u0007b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(J¢\u0006\u0004\bJ\u0010I¨\u0006K"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar$ExtensionRegistrarContext;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;)V", "unaryPlus", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirStatusTransformerExtension$Factory;", "plusStatusTransformerExtension", "Lkotlin/jvm/JvmName;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension$Factory;", "plusClassGenerationExtension", "Lorg/jetbrains/kotlin/fir/analysis/extensions/FirAdditionalCheckersExtension$Factory;", "plusAdditionalCheckersExtension", "Lorg/jetbrains/kotlin/fir/extensions/FirSupertypeGenerationExtension$Factory;", "plusSupertypeGenerationExtension", "Lorg/jetbrains/kotlin/fir/extensions/FirTypeAttributeExtension$Factory;", "plusTypeAttributeExtension", "Lorg/jetbrains/kotlin/fir/extensions/FirExpressionResolutionExtension$Factory;", "plusExpressionResolutionExtension", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionSessionComponent$Factory;", "plusExtensionSessionComponent", "Lorg/jetbrains/kotlin/fir/resolve/FirSamConversionTransformerExtension$Factory;", "plusSamConversionTransformerExtension", "Lorg/jetbrains/kotlin/fir/extensions/FirAssignExpressionAltererExtension$Factory;", "plusAssignExpressionAltererExtension", "Lorg/jetbrains/kotlin/fir/builder/FirScriptConfiguratorExtension$Factory;", "plusScriptConfiguratorExtension", "Lorg/jetbrains/kotlin/fir/extensions/FirScriptResolutionConfigurationExtension$Factory;", "plusFirScriptResolutionConfigurationExtension", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrScriptConfiguratorExtension$Factory;", "plusFir2IrScriptConfiguratorExtension", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrReplSnippetConfiguratorExtension$Factory;", "plusFir2IrReplStateDeclarationsProviderExtension", "Lorg/jetbrains/kotlin/fir/builder/FirReplSnippetConfiguratorExtension$Factory;", "plusReplSnippetConfiguratorExtension", "Lorg/jetbrains/kotlin/fir/extensions/FirFunctionTypeKindExtension$Factory;", "plusFunctionTypeKindExtension", "Lorg/jetbrains/kotlin/fir/serialization/FirMetadataSerializerPlugin$Factory;", "plusMetadataSerializerPlugin", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionApiInternals;", "Lorg/jetbrains/kotlin/fir/extensions/FirFunctionCallRefinementExtension$Factory;", "plusFunctionCallRefinementExtension", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/extensions/FirStatusTransformerExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension;", "Lorg/jetbrains/kotlin/fir/analysis/extensions/FirAdditionalCheckersExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirSupertypeGenerationExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirTypeAttributeExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExpressionResolutionExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionSessionComponent;", "Lorg/jetbrains/kotlin/fir/resolve/FirSamConversionTransformerExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirAssignExpressionAltererExtension;", "Lorg/jetbrains/kotlin/fir/builder/FirScriptConfiguratorExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirScriptResolutionConfigurationExtension;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrScriptConfiguratorExtension;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrReplSnippetConfiguratorExtension;", "Lorg/jetbrains/kotlin/fir/builder/FirReplSnippetConfiguratorExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirFunctionTypeKindExtension;", "Lorg/jetbrains/kotlin/fir/serialization/FirMetadataSerializerPlugin;", "Lorg/jetbrains/kotlin/fir/extensions/FirFunctionCallRefinementExtension;", "registerDiagnosticContainers", "diagnosticContainers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "([Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;)V", "bind", "R", "T", "Lkotlin/Function2;", "value", "bindLeft", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "bindRight", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class ExtensionRegistrarContext {
        public ExtensionRegistrarContext() {
        }

        public static FirScriptResolutionConfigurationExtension a(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirScriptResolutionConfigurationExtension) function1.invoke(firSession);
        }

        public static Fir2IrReplSnippetConfiguratorExtension b(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (Fir2IrReplSnippetConfiguratorExtension) function1.invoke(firSession);
        }

        public static FirExpressionResolutionExtension c(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirExpressionResolutionExtension) function1.invoke(firSession);
        }

        public static FirMetadataSerializerPlugin d(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirMetadataSerializerPlugin) function1.invoke(firSession);
        }

        public static FirFunctionCallRefinementExtension e(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirFunctionCallRefinementExtension) function1.invoke(firSession);
        }

        public static FirReplSnippetConfiguratorExtension f(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirReplSnippetConfiguratorExtension) function1.invoke(firSession);
        }

        public static FirSamConversionTransformerExtension g(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirSamConversionTransformerExtension) function1.invoke(firSession);
        }

        public static FirStatusTransformerExtension h(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirStatusTransformerExtension) function1.invoke(firSession);
        }

        public static FirExtensionSessionComponent i(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirExtensionSessionComponent) function1.invoke(firSession);
        }

        public static FirFunctionTypeKindExtension j(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirFunctionTypeKindExtension) function1.invoke(firSession);
        }

        public static Object k(Function2 function2, Object obj, FirSession firSession) {
            firSession.getClass();
            return function2.invoke(firSession, obj);
        }

        public static FirScriptConfiguratorExtension l(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirScriptConfiguratorExtension) function1.invoke(firSession);
        }

        public static FirSupertypeGenerationExtension m(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirSupertypeGenerationExtension) function1.invoke(firSession);
        }

        public static FirAssignExpressionAltererExtension n(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirAssignExpressionAltererExtension) function1.invoke(firSession);
        }

        public static FirAdditionalCheckersExtension o(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirAdditionalCheckersExtension) function1.invoke(firSession);
        }

        public static Object p(Function2 function2, Object obj, FirSession firSession) {
            firSession.getClass();
            return function2.invoke(obj, firSession);
        }

        public static FirDeclarationGenerationExtension q(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirDeclarationGenerationExtension) function1.invoke(firSession);
        }

        public static Fir2IrScriptConfiguratorExtension r(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (Fir2IrScriptConfiguratorExtension) function1.invoke(firSession);
        }

        public static FirTypeAttributeExtension s(Function1 function1, FirSession firSession) {
            firSession.getClass();
            return (FirTypeAttributeExtension) function1.invoke(firSession);
        }

        public final <T, R> Function1<FirSession, R> bindLeft(final Function2<? super T, ? super FirSession, ? extends R> function2, final T t) {
            function2.getClass();
            return new Function1() { // from class: n75
                public final Object invoke(Object obj) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.p(function2, t, (FirSession) obj);
                }
            };
        }

        public final <T, R> Function1<FirSession, R> bindRight(final Function2<? super FirSession, ? super T, ? extends R> function2, final T t) {
            function2.getClass();
            return new Function1() { // from class: i75
                public final Object invoke(Object obj) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.k(function2, t, (FirSession) obj);
                }
            };
        }

        public final void plusAdditionalCheckersExtension(FirAdditionalCheckersExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirAdditionalCheckersExtension.class), factory);
        }

        public final void plusAssignExpressionAltererExtension(FirAssignExpressionAltererExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirAssignExpressionAltererExtension.class), factory);
        }

        public final void plusClassGenerationExtension(FirDeclarationGenerationExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirDeclarationGenerationExtension.class), factory);
        }

        public final void plusExpressionResolutionExtension(FirExpressionResolutionExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirExpressionResolutionExtension.class), factory);
        }

        public final void plusExtensionSessionComponent(FirExtensionSessionComponent.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirExtensionSessionComponent.class), factory);
        }

        public final void plusFir2IrReplStateDeclarationsProviderExtension(Fir2IrReplSnippetConfiguratorExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(Fir2IrReplSnippetConfiguratorExtension.class), factory);
        }

        public final void plusFir2IrScriptConfiguratorExtension(Fir2IrScriptConfiguratorExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(Fir2IrScriptConfiguratorExtension.class), factory);
        }

        public final void plusFirScriptResolutionConfigurationExtension(FirScriptResolutionConfigurationExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirScriptResolutionConfigurationExtension.class), factory);
        }

        @FirExtensionApiInternals
        public final void plusFunctionCallRefinementExtension(FirFunctionCallRefinementExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirFunctionCallRefinementExtension.class), factory);
        }

        public final void plusFunctionTypeKindExtension(FirFunctionTypeKindExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirFunctionTypeKindExtension.class), factory);
        }

        @FirExtensionApiInternals
        public final void plusMetadataSerializerPlugin(FirMetadataSerializerPlugin.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirMetadataSerializerPlugin.class), factory);
        }

        public final void plusReplSnippetConfiguratorExtension(FirReplSnippetConfiguratorExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirReplSnippetConfiguratorExtension.class), factory);
        }

        public final void plusSamConversionTransformerExtension(FirSamConversionTransformerExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirSamConversionTransformerExtension.class), factory);
        }

        public final void plusScriptConfiguratorExtension(FirScriptConfiguratorExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirScriptConfiguratorExtension.class), factory);
        }

        public final void plusStatusTransformerExtension(FirStatusTransformerExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirStatusTransformerExtension.class), factory);
        }

        public final void plusSupertypeGenerationExtension(FirSupertypeGenerationExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirSupertypeGenerationExtension.class), factory);
        }

        public final void plusTypeAttributeExtension(FirTypeAttributeExtension.Factory factory) {
            factory.getClass();
            FirExtensionRegistrar.this.registerExtension(Reflection.getOrCreateKotlinClass(FirTypeAttributeExtension.class), factory);
        }

        public final void registerDiagnosticContainers(KtDiagnosticsContainer... diagnosticContainers) {
            diagnosticContainers.getClass();
            CollectionsKt.addAll(FirExtensionRegistrar.this.diagnosticsContainers, diagnosticContainers);
        }

        public final void plusAdditionalCheckersExtension(final Function1<? super FirSession, ? extends FirAdditionalCheckersExtension> function1) {
            function1.getClass();
            plusAdditionalCheckersExtension(new FirAdditionalCheckersExtension.Factory() { // from class: k75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.o(function1, firSession);
                }
            });
        }

        public final void plusAssignExpressionAltererExtension(final Function1<? super FirSession, ? extends FirAssignExpressionAltererExtension> function1) {
            function1.getClass();
            plusAssignExpressionAltererExtension(new FirAssignExpressionAltererExtension.Factory() { // from class: l75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.n(function1, firSession);
                }
            });
        }

        public final void plusClassGenerationExtension(final Function1<? super FirSession, ? extends FirDeclarationGenerationExtension> function1) {
            function1.getClass();
            plusClassGenerationExtension(new FirDeclarationGenerationExtension.Factory() { // from class: v75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.q(function1, firSession);
                }
            });
        }

        public final void plusExpressionResolutionExtension(final Function1<? super FirSession, ? extends FirExpressionResolutionExtension> function1) {
            function1.getClass();
            plusExpressionResolutionExtension(new FirExpressionResolutionExtension.Factory() { // from class: m75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.c(function1, firSession);
                }
            });
        }

        public final void plusExtensionSessionComponent(final Function1<? super FirSession, ? extends FirExtensionSessionComponent> function1) {
            function1.getClass();
            plusExtensionSessionComponent(new FirExtensionSessionComponent.Factory() { // from class: g75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.i(function1, firSession);
                }
            });
        }

        public final void plusFir2IrReplStateDeclarationsProviderExtension(final Function1<? super FirSession, ? extends Fir2IrReplSnippetConfiguratorExtension> function1) {
            function1.getClass();
            plusFir2IrReplStateDeclarationsProviderExtension(new Fir2IrReplSnippetConfiguratorExtension.Factory() { // from class: q75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.b(function1, firSession);
                }
            });
        }

        public final void plusFir2IrScriptConfiguratorExtension(final Function1<? super FirSession, ? extends Fir2IrScriptConfiguratorExtension> function1) {
            function1.getClass();
            plusFir2IrScriptConfiguratorExtension(new Fir2IrScriptConfiguratorExtension.Factory() { // from class: d75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.r(function1, firSession);
                }
            });
        }

        public final void plusFirScriptResolutionConfigurationExtension(final Function1<? super FirSession, ? extends FirScriptResolutionConfigurationExtension> function1) {
            function1.getClass();
            plusFirScriptResolutionConfigurationExtension(new FirScriptResolutionConfigurationExtension.Factory() { // from class: o75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.a(function1, firSession);
                }
            });
        }

        @FirExtensionApiInternals
        public final void plusFunctionCallRefinementExtension(final Function1<? super FirSession, ? extends FirFunctionCallRefinementExtension> function1) {
            function1.getClass();
            plusFunctionCallRefinementExtension(new FirFunctionCallRefinementExtension.Factory() { // from class: h75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.e(function1, firSession);
                }
            });
        }

        public final void plusFunctionTypeKindExtension(final Function1<? super FirSession, ? extends FirFunctionTypeKindExtension> function1) {
            function1.getClass();
            plusFunctionTypeKindExtension(new FirFunctionTypeKindExtension.Factory() { // from class: p75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.j(function1, firSession);
                }
            });
        }

        @FirExtensionApiInternals
        public final void plusMetadataSerializerPlugin(final Function1<? super FirSession, ? extends FirMetadataSerializerPlugin> function1) {
            function1.getClass();
            plusMetadataSerializerPlugin(new FirMetadataSerializerPlugin.Factory() { // from class: f75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.d(function1, firSession);
                }
            });
        }

        public final void plusReplSnippetConfiguratorExtension(final Function1<? super FirSession, ? extends FirReplSnippetConfiguratorExtension> function1) {
            function1.getClass();
            plusReplSnippetConfiguratorExtension(new FirReplSnippetConfiguratorExtension.Factory() { // from class: u75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.f(function1, firSession);
                }
            });
        }

        public final void plusSamConversionTransformerExtension(final Function1<? super FirSession, ? extends FirSamConversionTransformerExtension> function1) {
            function1.getClass();
            plusSamConversionTransformerExtension(new FirSamConversionTransformerExtension.Factory() { // from class: s75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.g(function1, firSession);
                }
            });
        }

        public final void plusScriptConfiguratorExtension(final Function1<? super FirSession, ? extends FirScriptConfiguratorExtension> function1) {
            function1.getClass();
            plusScriptConfiguratorExtension(new FirScriptConfiguratorExtension.Factory() { // from class: r75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.l(function1, firSession);
                }
            });
        }

        public final void plusStatusTransformerExtension(final Function1<? super FirSession, ? extends FirStatusTransformerExtension> function1) {
            function1.getClass();
            plusStatusTransformerExtension(new FirStatusTransformerExtension.Factory() { // from class: t75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.h(function1, firSession);
                }
            });
        }

        public final void plusSupertypeGenerationExtension(final Function1<? super FirSession, ? extends FirSupertypeGenerationExtension> function1) {
            function1.getClass();
            plusSupertypeGenerationExtension(new FirSupertypeGenerationExtension.Factory() { // from class: j75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.m(function1, firSession);
                }
            });
        }

        public final void plusTypeAttributeExtension(final Function1<? super FirSession, ? extends FirTypeAttributeExtension> function1) {
            function1.getClass();
            plusTypeAttributeExtension(new FirTypeAttributeExtension.Factory() { // from class: e75
                @Override // org.jetbrains.kotlin.fir.extensions.FirExtension.Factory
                public final FirExtension create(FirSession firSession) {
                    return FirExtensionRegistrar.ExtensionRegistrarContext.s(function1, firSession);
                }
            });
        }
    }
}
