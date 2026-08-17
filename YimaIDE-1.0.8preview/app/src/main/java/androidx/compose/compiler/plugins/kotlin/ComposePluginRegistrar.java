package androidx.compose.compiler.plugins.kotlin;

import androidx.compose.compiler.plugins.kotlin.analysis.FqNameMatcher;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityConfigParser;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.k1.ComposableAnnotationChecker;
import androidx.compose.compiler.plugins.kotlin.k1.ComposableCallChecker;
import androidx.compose.compiler.plugins.kotlin.k1.ComposableDeclarationChecker;
import androidx.compose.compiler.plugins.kotlin.k1.ComposableTargetChecker;
import androidx.compose.compiler.plugins.kotlin.k1.ComposeDescriptorSerializerContext;
import androidx.compose.compiler.plugins.kotlin.k1.ComposeDiagnosticSuppressor;
import androidx.compose.compiler.plugins.kotlin.k1.ComposeTypeResolutionInterceptorExtension;
import androidx.compose.compiler.plugins.kotlin.k2.ComposeFirExtensionRegistrar;
import androidx.compose.compiler.plugins.kotlin.lower.ClassStabilityFieldSerializationPlugin;
import androidx.compose.compiler.plugins.kotlin.lower.hiddenfromobjc.AddHiddenFromObjCSerializationPlugin;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar;
import org.jetbrains.kotlin.compiler.plugin.FirExtensionRegistrarConfigurationUtilKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor;
import org.jetbrains.kotlin.extensions.internal.TypeResolutionInterceptor;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.resolve.diagnostics.DiagnosticSuppressor;
import org.jetbrains.kotlin.serialization.DescriptorSerializerPlugin;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposePluginRegistrar;", "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar;", "<init>", "()V", "pluginId", "", "getPluginId", "()Ljava/lang/String;", "supportsK2", "", "getSupportsK2", "()Z", "registerExtensions", "", "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "Companion", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposePluginRegistrar extends CompilerPluginRegistrar {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public String getPluginId() {
        return ComposePluginKt.COMPOSE_PLUGIN_ID;
    }

    public boolean getSupportsK2() {
        return true;
    }

    public void registerExtensions(CompilerPluginRegistrar.ExtensionStorage extensionStorage, CompilerConfiguration compilerConfiguration) {
        extensionStorage.getClass();
        compilerConfiguration.getClass();
        Companion companion = INSTANCE;
        if (companion.checkCompilerConfiguration(compilerConfiguration)) {
            boolean usesK2 = CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration).getLanguageVersion().getUsesK2();
            ComposeDescriptorSerializerContext composeDescriptorSerializerContext = usesK2 ? null : new ComposeDescriptorSerializerContext(null, null, 3, null);
            companion.setupJvmConfiguration(compilerConfiguration);
            companion.registerCommonExtensions(extensionStorage, composeDescriptorSerializerContext);
            extensionStorage.registerExtension(IrGenerationExtension.Companion, Companion.createComposeIrExtension$default(companion, compilerConfiguration, composeDescriptorSerializerContext, null, 4, null));
            if (usesK2) {
                return;
            }
            composeDescriptorSerializerContext.getClass();
            companion.registerNativeExtensions(extensionStorage, composeDescriptorSerializerContext);
        }
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\n\u001a\u00020\t*\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rJ\u0012\u0010\u000e\u001a\u00020\t*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ8\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r2\u001c\b\u0002\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0013¨\u0006\u0017"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposePluginRegistrar$Companion;", "", "<init>", "()V", "checkCompilerConfiguration", "", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "setupJvmConfiguration", "", "registerCommonExtensions", "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;", "composeDescriptorSerializerContext", "Landroidx/compose/compiler/plugins/kotlin/k1/ComposeDescriptorSerializerContext;", "registerNativeExtensions", "createComposeIrExtension", "Landroidx/compose/compiler/plugins/kotlin/ComposeIrGenerationExtension;", "descriptorSerializerContext", "moduleMetricsFactory", "Lkotlin/Function2;", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ComposeIrGenerationExtension createComposeIrExtension$default(Companion companion, CompilerConfiguration compilerConfiguration, ComposeDescriptorSerializerContext composeDescriptorSerializerContext, Function2 function2, int i, Object obj) {
            if ((i & 2) != 0) {
                composeDescriptorSerializerContext = null;
            }
            if ((i & 4) != 0) {
                function2 = null;
            }
            return companion.createComposeIrExtension(compilerConfiguration, composeDescriptorSerializerContext, function2);
        }

        public static /* synthetic */ void registerCommonExtensions$default(Companion companion, CompilerPluginRegistrar.ExtensionStorage extensionStorage, ComposeDescriptorSerializerContext composeDescriptorSerializerContext, int i, Object obj) {
            if ((i & 1) != 0) {
                composeDescriptorSerializerContext = null;
            }
            companion.registerCommonExtensions(extensionStorage, composeDescriptorSerializerContext);
        }

        public final boolean checkCompilerConfiguration(CompilerConfiguration configuration) {
            configuration.getClass();
            MessageCollector messageCollector = CommonConfigurationKeysKt.getMessageCollector(configuration);
            ComposeConfiguration composeConfiguration = ComposeConfiguration.INSTANCE;
            if (((String) configuration.get(composeConfiguration.getSUPPRESS_KOTLIN_VERSION_COMPATIBILITY_CHECK())) != null) {
                MessageCollector.report$default(messageCollector, CompilerMessageSeverity.WARNING, "suppressKotlinVersionCompatibilityCheck flag is deprecated for Compose compiler bundled with Kotlin releases.", (CompilerMessageSourceLocation) null, 4, (Object) null);
            }
            if (!((Boolean) configuration.get(composeConfiguration.getDECOYS_ENABLED_KEY(), Boolean.FALSE)).booleanValue()) {
                return true;
            }
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.ERROR, "Decoys generation is no longer supported by the Compose compiler.", (CompilerMessageSourceLocation) null, 4, (Object) null);
            return false;
        }

        public final ComposeIrGenerationExtension createComposeIrExtension(CompilerConfiguration configuration, ComposeDescriptorSerializerContext descriptorSerializerContext, Function2<? super StabilityInferencer, ? super FeatureFlags, ? extends ModuleMetrics> moduleMetricsFactory) throws Throwable {
            Collection collectionEmptySet;
            Set<FqNameMatcher> setEmptySet;
            configuration.getClass();
            ComposeConfiguration composeConfiguration = ComposeConfiguration.INSTANCE;
            boolean z = configuration.getBoolean(composeConfiguration.getLIVE_LITERALS_ENABLED_KEY());
            boolean z2 = configuration.getBoolean(composeConfiguration.getLIVE_LITERALS_V2_ENABLED_KEY());
            Boolean bool = (Boolean) configuration.get(composeConfiguration.getGENERATE_FUNCTION_KEY_META_ANNOTATION_KEY());
            boolean z3 = configuration.getBoolean(composeConfiguration.getSOURCE_INFORMATION_ENABLED_KEY());
            CompilerConfigurationKey<Boolean> intrinsic_remember_optimization_enabled_key = composeConfiguration.getINTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_KEY();
            FeatureFlag featureFlag = FeatureFlag.IntrinsicRemember;
            boolean zBooleanValue = ((Boolean) configuration.get(intrinsic_remember_optimization_enabled_key, Boolean.valueOf(featureFlag.getDefault()))).booleanValue();
            CompilerConfigurationKey<Boolean> non_skipping_group_optimization_enabled_key = composeConfiguration.getNON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_KEY();
            FeatureFlag featureFlag2 = FeatureFlag.OptimizeNonSkippingGroups;
            boolean zBooleanValue2 = ((Boolean) configuration.get(non_skipping_group_optimization_enabled_key, Boolean.valueOf(featureFlag2.getDefault()))).booleanValue();
            CharSequence charSequence = (CharSequence) configuration.get(composeConfiguration.getMETRICS_DESTINATION_KEY(), "");
            if (StringsKt.isBlank(charSequence)) {
                charSequence = null;
            }
            String str = (String) charSequence;
            CharSequence charSequence2 = (CharSequence) configuration.get(composeConfiguration.getREPORTS_DESTINATION_KEY(), "");
            if (StringsKt.isBlank(charSequence2)) {
                charSequence2 = null;
            }
            String str2 = (String) charSequence2;
            boolean usesK2 = CommonConfigurationKeysKt.getLanguageVersionSettings(configuration).getLanguageVersion().getUsesK2();
            CompilerConfigurationKey<Boolean> strong_skipping_enabled_key = composeConfiguration.getSTRONG_SKIPPING_ENABLED_KEY();
            FeatureFlag featureFlag3 = FeatureFlag.StrongSkipping;
            boolean zBooleanValue3 = ((Boolean) configuration.get(strong_skipping_enabled_key, Boolean.valueOf(featureFlag3.getDefault()))).booleanValue();
            List list = configuration.getList(composeConfiguration.getSTABILITY_CONFIG_PATH_KEY());
            boolean zBooleanValue4 = ((Boolean) configuration.get(composeConfiguration.getTRACE_MARKERS_ENABLED_KEY(), Boolean.TRUE)).booleanValue();
            boolean z4 = configuration.getBoolean(composeConfiguration.getSKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_KEY());
            FeatureFlags featureFlags = new FeatureFlags((List) configuration.get(composeConfiguration.getFEATURE_FLAGS(), CollectionsKt.emptyList()));
            featureFlags.validateFeatureFlags(configuration);
            String str3 = (String) configuration.get(composeConfiguration.getTARGET_RUNTIME_VERSION_KEY());
            ComposeRuntimeVersion composeRuntimeVersionFromString = str3 != null ? ComposeRuntimeVersion.INSTANCE.fromString(str3) : null;
            featureFlags.setFeature(featureFlag, zBooleanValue);
            featureFlags.setFeature(featureFlag3, zBooleanValue3);
            featureFlags.setFeature(featureFlag2, zBooleanValue2);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String str4 = (String) list.get(i);
                try {
                    setEmptySet = StabilityConfigParser.INSTANCE.fromFile(str4).getStableTypeMatchers();
                } catch (FileNotFoundException unused) {
                    MessageCollector.report$default(CommonConfigurationKeysKt.getMessageCollector(configuration), CompilerMessageSeverity.WARNING, "Stability configuration file not found at " + str4, (CompilerMessageSourceLocation) null, 4, (Object) null);
                    setEmptySet = SetsKt.emptySet();
                } catch (Exception e) {
                    PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                    MessageCollector messageCollector = CommonConfigurationKeysKt.getMessageCollector(configuration);
                    CompilerMessageSeverity compilerMessageSeverity = CompilerMessageSeverity.ERROR;
                    String message = e.getMessage();
                    if (message == null) {
                        message = "Error parsing stability configuration at " + str4;
                    }
                    MessageCollector.report$default(messageCollector, compilerMessageSeverity, message, (CompilerMessageSourceLocation) null, 4, (Object) null);
                    setEmptySet = SetsKt.emptySet();
                }
                linkedHashSet.addAll(setEmptySet);
            }
            Set set = (Set) configuration.get(ComposeConfiguration.INSTANCE.getTEST_STABILITY_CONFIG_KEY());
            if (set != null) {
                Set set2 = set;
                collectionEmptySet = new ArrayList(CollectionsKt.collectionSizeOrDefault(set2, 10));
                Iterator it = set2.iterator();
                while (it.hasNext()) {
                    collectionEmptySet.add(new FqNameMatcher((String) it.next()));
                }
            } else {
                collectionEmptySet = SetsKt.emptySet();
            }
            linkedHashSet.addAll(collectionEmptySet);
            return new ComposeIrGenerationExtension(z, z2, bool, z3, zBooleanValue4, str, str2, usesK2, linkedHashSet, moduleMetricsFactory, descriptorSerializerContext, featureFlags, z4, composeRuntimeVersionFromString, CommonConfigurationKeysKt.getMessageCollector(configuration));
        }

        public final void registerCommonExtensions(CompilerPluginRegistrar.ExtensionStorage extensionStorage, ComposeDescriptorSerializerContext composeDescriptorSerializerContext) {
            extensionStorage.getClass();
            StorageComponentContainerContributor.Companion companion = StorageComponentContainerContributor.Companion;
            extensionStorage.registerExtension(companion, new ComposableCallChecker());
            extensionStorage.registerExtension(companion, new ComposableDeclarationChecker());
            extensionStorage.registerExtension(companion, new ComposableTargetChecker());
            extensionStorage.registerExtension(companion, new ComposableAnnotationChecker());
            extensionStorage.registerExtension(DiagnosticSuppressor.Companion, new ComposeDiagnosticSuppressor());
            extensionStorage.registerExtension(TypeResolutionInterceptor.Companion, new ComposeTypeResolutionInterceptorExtension());
            extensionStorage.registerExtension(DescriptorSerializerPlugin.Companion, new ClassStabilityFieldSerializationPlugin(composeDescriptorSerializerContext != null ? composeDescriptorSerializerContext.getClassStabilityInferredCollection() : null));
            FirExtensionRegistrarConfigurationUtilKt.registerExtension(extensionStorage, FirExtensionRegistrar.Companion, new ComposeFirExtensionRegistrar());
        }

        public final void registerNativeExtensions(CompilerPluginRegistrar.ExtensionStorage extensionStorage, ComposeDescriptorSerializerContext composeDescriptorSerializerContext) {
            extensionStorage.getClass();
            composeDescriptorSerializerContext.getClass();
            extensionStorage.registerExtension(DescriptorSerializerPlugin.Companion, new AddHiddenFromObjCSerializationPlugin(composeDescriptorSerializerContext.getHideFromObjCDeclarationsSet()));
        }

        public final void setupJvmConfiguration(CompilerConfiguration configuration) {
            configuration.getClass();
            CompilerConfigurationKey compilerConfigurationKey = JVMConfigurationKeys.IGNORED_ANNOTATIONS_FOR_BRIDGES;
            ComposeClassIds composeClassIds = ComposeClassIds.INSTANCE;
            configuration.put(compilerConfigurationKey, CollectionsKt.listOf(new String[]{composeClassIds.getComposable().asFqNameString(), composeClassIds.getComposableInferredTarget().asFqNameString(), composeClassIds.getFunctionKeyMeta().asFqNameString()}));
        }

        private Companion() {
        }
    }
}
