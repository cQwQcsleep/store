package androidx.compose.compiler.plugins.kotlin;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption;
import org.jetbrains.kotlin.compiler.plugin.CliOption;
import org.jetbrains.kotlin.compiler.plugin.CliOptionProcessingException;
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeCommandLineProcessor;", "Lorg/jetbrains/kotlin/compiler/plugin/CommandLineProcessor;", "<init>", "()V", "pluginId", "", "getPluginId", "()Ljava/lang/String;", "pluginOptions", "", "Lorg/jetbrains/kotlin/compiler/plugin/CliOption;", "getPluginOptions", "()Ljava/util/List;", "processOption", "", "option", "Lorg/jetbrains/kotlin/compiler/plugin/AbstractCliOption;", "value", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "Companion", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeCommandLineProcessor implements CommandLineProcessor {
    private static final CliOption EXPERIMENTAL_STRONG_SKIPPING_OPTION;
    private static final CliOption SKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_OPTION;
    private static final CliOption STABLE_CONFIG_PATH_OPTION;
    private static final CliOption STRONG_SKIPPING_OPTION;
    private static final CliOption TARGET_RUNTIME_VERSION_OPTION;
    private static final CliOption TRACE_MARKERS_OPTION;
    private final String pluginId = PLUGIN_ID;
    private final List<CliOption> pluginOptions = CollectionsKt.listOf(new CliOption[]{LIVE_LITERALS_ENABLED_OPTION, LIVE_LITERALS_V2_ENABLED_OPTION, GENERATE_FUNCTION_KEY_META_CLASSES_OPTION, GENERATE_FUNCTION_KEY_META_ANNOTATION_OPTION, SOURCE_INFORMATION_ENABLED_OPTION, METRICS_DESTINATION_OPTION, REPORTS_DESTINATION_OPTION, INTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_OPTION, NON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_OPTION, SUPPRESS_KOTLIN_VERSION_CHECK_ENABLED_OPTION, DECOYS_ENABLED_OPTION, EXPERIMENTAL_STRONG_SKIPPING_OPTION, STRONG_SKIPPING_OPTION, STABLE_CONFIG_PATH_OPTION, TRACE_MARKERS_OPTION, FEATURE_FLAG_OPTION, SKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_OPTION, TARGET_RUNTIME_VERSION_OPTION});

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PLUGIN_ID = ComposePluginKt.COMPOSE_PLUGIN_ID;
    private static final CliOption LIVE_LITERALS_ENABLED_OPTION = new CliOption("liveLiterals", "<true|false>", "Enable Live Literals code generation", false, false);
    private static final CliOption LIVE_LITERALS_V2_ENABLED_OPTION = new CliOption("liveLiteralsEnabled", "<true|false>", "Enable Live Literals code generation (with per-file enabled flags)", false, false);
    private static final CliOption GENERATE_FUNCTION_KEY_META_CLASSES_OPTION = new CliOption("generateFunctionKeyMetaClasses", "<true|false>", "Generate function key meta classes with annotations indicating the functions and their group keys. Generally used for tooling. Deprecated. Use 'generateFunctionKeyMetaAnnotation' instead.", false, false);
    private static final CliOption GENERATE_FUNCTION_KEY_META_ANNOTATION_OPTION = new CliOption("generateFunctionKeyMetaAnnotations", "<true|false>", "Generate function key meta annotations indicating the functions and their group keys. Generally used for tooling.", false, true);
    private static final CliOption SOURCE_INFORMATION_ENABLED_OPTION = new CliOption("sourceInformation", "<true|false>", "Include source information in generated code", false, false);
    private static final CliOption METRICS_DESTINATION_OPTION = new CliOption("metricsDestination", "<path>", "Save compose build metrics to this folder", false, false);
    private static final CliOption REPORTS_DESTINATION_OPTION = new CliOption("reportsDestination", "<path>", "Save compose build reports to this folder", false, false);
    private static final CliOption FEATURE_FLAG_OPTION = new CliOption("featureFlag", "<feature name>", "The name of the feature to enable", false, true);
    private static final CliOption INTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_OPTION = new CliOption("intrinsicRemember", "<true|false>", "Include source information in generated code. Deprecated. Use " + ComposePluginKt.useFeatureFlagInsteadMessage(FeatureFlag.IntrinsicRemember), false, false);
    private static final CliOption NON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_OPTION = new CliOption("nonSkippingGroupOptimization", "<true|false>", "Remove groups around non-skipping composable functions. Deprecated. " + ComposePluginKt.useFeatureFlagInsteadMessage(FeatureFlag.OptimizeNonSkippingGroups), false, false);
    private static final CliOption SUPPRESS_KOTLIN_VERSION_CHECK_ENABLED_OPTION = new CliOption("suppressKotlinVersionCompatibilityCheck", "<kotlin_version>", "Deprecated. Suppress Kotlin version compatibility check", false, false);
    private static final CliOption DECOYS_ENABLED_OPTION = new CliOption("generateDecoys", "<true|false>", "Generate decoy methods in IR transform", false, false);

    static {
        StringBuilder sb = new StringBuilder("Enable strong skipping mode. Deprecated. ");
        FeatureFlag featureFlag = FeatureFlag.StrongSkipping;
        sb.append(ComposePluginKt.useFeatureFlagInsteadMessage(featureFlag));
        STRONG_SKIPPING_OPTION = new CliOption("strongSkipping", "<true|false>", sb.toString(), false, false);
        EXPERIMENTAL_STRONG_SKIPPING_OPTION = new CliOption("experimentalStrongSkipping", "<true|false>", "Deprecated. " + ComposePluginKt.useFeatureFlagInsteadMessage(featureFlag), false, false);
        STABLE_CONFIG_PATH_OPTION = new CliOption("stabilityConfigurationPath", "<path>", "Path to stability configuration file", false, true);
        TRACE_MARKERS_OPTION = new CliOption("traceMarkersEnabled", "<true|false>", "Include composition trace markers in generate code", false, false);
        SKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_OPTION = new CliOption("skipIrLoweringIfRuntimeNotFound", "<true|false>", "Skip IR lowering transformation when finding Compose runtime fails", false, false);
        TARGET_RUNTIME_VERSION_OPTION = new CliOption("targetRuntimeVersion", "<version>", "Override target Compose runtime version for the compiler. Normally, this is determined through the Compose runtime dependency at compile time.", false, false);
    }

    public String getPluginId() {
        return this.pluginId;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.compiler.plugin.CliOptionProcessingException */
    public void processOption(AbstractCliOption option, String value, CompilerConfiguration configuration) throws CliOptionProcessingException {
        option.getClass();
        value.getClass();
        configuration.getClass();
        if (Intrinsics.areEqual(option, LIVE_LITERALS_ENABLED_OPTION)) {
            configuration.put(ComposeConfiguration.INSTANCE.getLIVE_LITERALS_ENABLED_KEY(), Boolean.valueOf(Intrinsics.areEqual(value, "true")));
            return;
        }
        if (Intrinsics.areEqual(option, LIVE_LITERALS_V2_ENABLED_OPTION)) {
            configuration.put(ComposeConfiguration.INSTANCE.getLIVE_LITERALS_V2_ENABLED_KEY(), Boolean.valueOf(Intrinsics.areEqual(value, "true")));
            return;
        }
        CliOption cliOption = GENERATE_FUNCTION_KEY_META_CLASSES_OPTION;
        boolean z = true;
        if (Intrinsics.areEqual(option, cliOption)) {
            MessageCollector.report$default(CommonConfigurationKeysKt.getMessageCollector(configuration), CompilerMessageSeverity.WARNING, cliOption.getOptionName() + " is deprecated. It was replaced by emitting annotations on functions instead. Use " + GENERATE_FUNCTION_KEY_META_ANNOTATION_OPTION.getOptionName() + " instead.", (CompilerMessageSourceLocation) null, 4, (Object) null);
            ComposeConfiguration composeConfiguration = ComposeConfiguration.INSTANCE;
            CompilerConfigurationKey<Boolean> generate_function_key_meta_annotation_key = composeConfiguration.getGENERATE_FUNCTION_KEY_META_ANNOTATION_KEY();
            if (!Intrinsics.areEqual(configuration.get(composeConfiguration.getGENERATE_FUNCTION_KEY_META_ANNOTATION_KEY()), Boolean.TRUE) && !Intrinsics.areEqual(value, "true")) {
                z = false;
            }
            configuration.put(generate_function_key_meta_annotation_key, Boolean.valueOf(z));
            return;
        }
        if (Intrinsics.areEqual(option, GENERATE_FUNCTION_KEY_META_ANNOTATION_OPTION)) {
            ComposeConfiguration composeConfiguration2 = ComposeConfiguration.INSTANCE;
            CompilerConfigurationKey<Boolean> generate_function_key_meta_annotation_key2 = composeConfiguration2.getGENERATE_FUNCTION_KEY_META_ANNOTATION_KEY();
            if (!Intrinsics.areEqual(configuration.get(composeConfiguration2.getGENERATE_FUNCTION_KEY_META_ANNOTATION_KEY()), Boolean.TRUE) && !Intrinsics.areEqual(value, "true")) {
                z = false;
            }
            configuration.put(generate_function_key_meta_annotation_key2, Boolean.valueOf(z));
            return;
        }
        if (Intrinsics.areEqual(option, SOURCE_INFORMATION_ENABLED_OPTION)) {
            configuration.put(ComposeConfiguration.INSTANCE.getSOURCE_INFORMATION_ENABLED_KEY(), Boolean.valueOf(Intrinsics.areEqual(value, "true")));
            return;
        }
        if (Intrinsics.areEqual(option, METRICS_DESTINATION_OPTION)) {
            configuration.put(ComposeConfiguration.INSTANCE.getMETRICS_DESTINATION_KEY(), value);
            return;
        }
        if (Intrinsics.areEqual(option, REPORTS_DESTINATION_OPTION)) {
            configuration.put(ComposeConfiguration.INSTANCE.getREPORTS_DESTINATION_KEY(), value);
            return;
        }
        CliOption cliOption2 = INTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_OPTION;
        if (Intrinsics.areEqual(option, cliOption2)) {
            ComposePluginKt.oldOptionDeprecationWarning(configuration, cliOption2, FeatureFlag.IntrinsicRemember);
            configuration.put(ComposeConfiguration.INSTANCE.getINTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_KEY(), Boolean.valueOf(Intrinsics.areEqual(value, "true")));
            return;
        }
        CliOption cliOption3 = NON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_OPTION;
        if (Intrinsics.areEqual(option, cliOption3)) {
            ComposePluginKt.oldOptionDeprecationWarning(configuration, cliOption3, FeatureFlag.OptimizeNonSkippingGroups);
            configuration.put(ComposeConfiguration.INSTANCE.getNON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_KEY(), Boolean.valueOf(Intrinsics.areEqual(value, "true")));
            return;
        }
        if (Intrinsics.areEqual(option, SUPPRESS_KOTLIN_VERSION_CHECK_ENABLED_OPTION)) {
            configuration.put(ComposeConfiguration.INSTANCE.getSUPPRESS_KOTLIN_VERSION_COMPATIBILITY_CHECK(), value);
            return;
        }
        if (Intrinsics.areEqual(option, DECOYS_ENABLED_OPTION)) {
            configuration.put(ComposeConfiguration.INSTANCE.getDECOYS_ENABLED_KEY(), Boolean.valueOf(Intrinsics.areEqual(value, "true")));
            return;
        }
        CliOption cliOption4 = EXPERIMENTAL_STRONG_SKIPPING_OPTION;
        if (Intrinsics.areEqual(option, cliOption4)) {
            ComposePluginKt.oldOptionDeprecationWarning(configuration, cliOption4, FeatureFlag.StrongSkipping);
            configuration.put(ComposeConfiguration.INSTANCE.getSTRONG_SKIPPING_ENABLED_KEY(), Boolean.valueOf(Intrinsics.areEqual(value, "true")));
            return;
        }
        if (Intrinsics.areEqual(option, STRONG_SKIPPING_OPTION)) {
            ComposePluginKt.oldOptionDeprecationWarning(configuration, cliOption4, FeatureFlag.StrongSkipping);
            configuration.put(ComposeConfiguration.INSTANCE.getSTRONG_SKIPPING_ENABLED_KEY(), Boolean.valueOf(Intrinsics.areEqual(value, "true")));
            return;
        }
        if (Intrinsics.areEqual(option, STABLE_CONFIG_PATH_OPTION)) {
            appendList(configuration, ComposeConfiguration.INSTANCE.getSTABILITY_CONFIG_PATH_KEY(), value);
            return;
        }
        if (Intrinsics.areEqual(option, TRACE_MARKERS_OPTION)) {
            configuration.put(ComposeConfiguration.INSTANCE.getTRACE_MARKERS_ENABLED_KEY(), Boolean.valueOf(Intrinsics.areEqual(value, "true")));
            return;
        }
        if (Intrinsics.areEqual(option, FEATURE_FLAG_OPTION)) {
            ComposePluginKt.validateFeatureFlag(configuration, value);
            appendList(configuration, ComposeConfiguration.INSTANCE.getFEATURE_FLAGS(), value);
        } else if (Intrinsics.areEqual(option, SKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_OPTION)) {
            configuration.put(ComposeConfiguration.INSTANCE.getSKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_KEY(), Boolean.valueOf(Intrinsics.areEqual(value, "true")));
        } else {
            if (Intrinsics.areEqual(option, TARGET_RUNTIME_VERSION_OPTION)) {
                configuration.put(ComposeConfiguration.INSTANCE.getTARGET_RUNTIME_VERSION_KEY(), value);
                return;
            }
            throw new CliOptionProcessingException("Unknown option: " + option.getOptionName(), (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b%\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u0011\u0010\u0016\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0011\u0010\u0018\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000bR\u0011\u0010\u001a\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000bR\u0011\u0010\u001c\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000bR\u0011\u0010\u001e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000bR\u0011\u0010 \u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u000bR\u0011\u0010\"\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u000bR\u0011\u0010$\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u000bR\u0011\u0010&\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u000bR\u0011\u0010(\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u000bR\u0011\u0010*\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u000bR\u0011\u0010,\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u000b¨\u0006."}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeCommandLineProcessor$Companion;", "", "<init>", "()V", "PLUGIN_ID", "", "getPLUGIN_ID", "()Ljava/lang/String;", "LIVE_LITERALS_ENABLED_OPTION", "Lorg/jetbrains/kotlin/compiler/plugin/CliOption;", "getLIVE_LITERALS_ENABLED_OPTION", "()Lorg/jetbrains/kotlin/compiler/plugin/CliOption;", "LIVE_LITERALS_V2_ENABLED_OPTION", "getLIVE_LITERALS_V2_ENABLED_OPTION", "GENERATE_FUNCTION_KEY_META_CLASSES_OPTION", "getGENERATE_FUNCTION_KEY_META_CLASSES_OPTION", "GENERATE_FUNCTION_KEY_META_ANNOTATION_OPTION", "getGENERATE_FUNCTION_KEY_META_ANNOTATION_OPTION", "SOURCE_INFORMATION_ENABLED_OPTION", "getSOURCE_INFORMATION_ENABLED_OPTION", "METRICS_DESTINATION_OPTION", "getMETRICS_DESTINATION_OPTION", "REPORTS_DESTINATION_OPTION", "getREPORTS_DESTINATION_OPTION", "FEATURE_FLAG_OPTION", "getFEATURE_FLAG_OPTION", "INTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_OPTION", "getINTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_OPTION", "NON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_OPTION", "getNON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_OPTION", "SUPPRESS_KOTLIN_VERSION_CHECK_ENABLED_OPTION", "getSUPPRESS_KOTLIN_VERSION_CHECK_ENABLED_OPTION", "DECOYS_ENABLED_OPTION", "getDECOYS_ENABLED_OPTION", "STRONG_SKIPPING_OPTION", "getSTRONG_SKIPPING_OPTION", "EXPERIMENTAL_STRONG_SKIPPING_OPTION", "getEXPERIMENTAL_STRONG_SKIPPING_OPTION", "STABLE_CONFIG_PATH_OPTION", "getSTABLE_CONFIG_PATH_OPTION", "TRACE_MARKERS_OPTION", "getTRACE_MARKERS_OPTION", "SKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_OPTION", "getSKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_OPTION", "TARGET_RUNTIME_VERSION_OPTION", "getTARGET_RUNTIME_VERSION_OPTION", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CliOption getDECOYS_ENABLED_OPTION() {
            return ComposeCommandLineProcessor.DECOYS_ENABLED_OPTION;
        }

        public final CliOption getEXPERIMENTAL_STRONG_SKIPPING_OPTION() {
            return ComposeCommandLineProcessor.EXPERIMENTAL_STRONG_SKIPPING_OPTION;
        }

        public final CliOption getFEATURE_FLAG_OPTION() {
            return ComposeCommandLineProcessor.FEATURE_FLAG_OPTION;
        }

        public final CliOption getGENERATE_FUNCTION_KEY_META_ANNOTATION_OPTION() {
            return ComposeCommandLineProcessor.GENERATE_FUNCTION_KEY_META_ANNOTATION_OPTION;
        }

        public final CliOption getGENERATE_FUNCTION_KEY_META_CLASSES_OPTION() {
            return ComposeCommandLineProcessor.GENERATE_FUNCTION_KEY_META_CLASSES_OPTION;
        }

        public final CliOption getINTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_OPTION() {
            return ComposeCommandLineProcessor.INTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_OPTION;
        }

        public final CliOption getLIVE_LITERALS_ENABLED_OPTION() {
            return ComposeCommandLineProcessor.LIVE_LITERALS_ENABLED_OPTION;
        }

        public final CliOption getLIVE_LITERALS_V2_ENABLED_OPTION() {
            return ComposeCommandLineProcessor.LIVE_LITERALS_V2_ENABLED_OPTION;
        }

        public final CliOption getMETRICS_DESTINATION_OPTION() {
            return ComposeCommandLineProcessor.METRICS_DESTINATION_OPTION;
        }

        public final CliOption getNON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_OPTION() {
            return ComposeCommandLineProcessor.NON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_OPTION;
        }

        public final String getPLUGIN_ID() {
            return ComposeCommandLineProcessor.PLUGIN_ID;
        }

        public final CliOption getREPORTS_DESTINATION_OPTION() {
            return ComposeCommandLineProcessor.REPORTS_DESTINATION_OPTION;
        }

        public final CliOption getSKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_OPTION() {
            return ComposeCommandLineProcessor.SKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_OPTION;
        }

        public final CliOption getSOURCE_INFORMATION_ENABLED_OPTION() {
            return ComposeCommandLineProcessor.SOURCE_INFORMATION_ENABLED_OPTION;
        }

        public final CliOption getSTABLE_CONFIG_PATH_OPTION() {
            return ComposeCommandLineProcessor.STABLE_CONFIG_PATH_OPTION;
        }

        public final CliOption getSTRONG_SKIPPING_OPTION() {
            return ComposeCommandLineProcessor.STRONG_SKIPPING_OPTION;
        }

        public final CliOption getSUPPRESS_KOTLIN_VERSION_CHECK_ENABLED_OPTION() {
            return ComposeCommandLineProcessor.SUPPRESS_KOTLIN_VERSION_CHECK_ENABLED_OPTION;
        }

        public final CliOption getTARGET_RUNTIME_VERSION_OPTION() {
            return ComposeCommandLineProcessor.TARGET_RUNTIME_VERSION_OPTION;
        }

        public final CliOption getTRACE_MARKERS_OPTION() {
            return ComposeCommandLineProcessor.TRACE_MARKERS_OPTION;
        }

        private Companion() {
        }
    }

    public List<CliOption> getPluginOptions() {
        return this.pluginOptions;
    }
}
