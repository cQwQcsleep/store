package androidx.compose.compiler.plugins.kotlin;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\bR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\bR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\bR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\bR\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\bR\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\"0\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\bR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\bR\u001d\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\bR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\bR\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\b¨\u0006,"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeConfiguration;", "", "<init>", "()V", "LIVE_LITERALS_ENABLED_KEY", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "", "getLIVE_LITERALS_ENABLED_KEY", "()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "LIVE_LITERALS_V2_ENABLED_KEY", "getLIVE_LITERALS_V2_ENABLED_KEY", "GENERATE_FUNCTION_KEY_META_ANNOTATION_KEY", "getGENERATE_FUNCTION_KEY_META_ANNOTATION_KEY", "SOURCE_INFORMATION_ENABLED_KEY", "getSOURCE_INFORMATION_ENABLED_KEY", "METRICS_DESTINATION_KEY", "", "getMETRICS_DESTINATION_KEY", "REPORTS_DESTINATION_KEY", "getREPORTS_DESTINATION_KEY", "INTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_KEY", "getINTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_KEY", "NON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_KEY", "getNON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_KEY", "SUPPRESS_KOTLIN_VERSION_COMPATIBILITY_CHECK", "getSUPPRESS_KOTLIN_VERSION_COMPATIBILITY_CHECK", "DECOYS_ENABLED_KEY", "getDECOYS_ENABLED_KEY", "STRONG_SKIPPING_ENABLED_KEY", "getSTRONG_SKIPPING_ENABLED_KEY", "STABILITY_CONFIG_PATH_KEY", "", "getSTABILITY_CONFIG_PATH_KEY", "TEST_STABILITY_CONFIG_KEY", "", "getTEST_STABILITY_CONFIG_KEY", "TRACE_MARKERS_ENABLED_KEY", "getTRACE_MARKERS_ENABLED_KEY", "FEATURE_FLAGS", "getFEATURE_FLAGS", "SKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_KEY", "getSKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_KEY", "TARGET_RUNTIME_VERSION_KEY", "getTARGET_RUNTIME_VERSION_KEY", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeConfiguration {
    public static final ComposeConfiguration INSTANCE = new ComposeConfiguration();
    private static final CompilerConfigurationKey<Boolean> LIVE_LITERALS_ENABLED_KEY = new CompilerConfigurationKey<>("Enable Live Literals code generation");
    private static final CompilerConfigurationKey<Boolean> LIVE_LITERALS_V2_ENABLED_KEY = new CompilerConfigurationKey<>("Enable Live Literals code generation (with per-file enabled flags)");
    private static final CompilerConfigurationKey<Boolean> GENERATE_FUNCTION_KEY_META_ANNOTATION_KEY = new CompilerConfigurationKey<>("Generate function key meta classes");
    private static final CompilerConfigurationKey<Boolean> SOURCE_INFORMATION_ENABLED_KEY = new CompilerConfigurationKey<>("Include source information in generated code");
    private static final CompilerConfigurationKey<String> METRICS_DESTINATION_KEY = new CompilerConfigurationKey<>("Directory to save compose build metrics");
    private static final CompilerConfigurationKey<String> REPORTS_DESTINATION_KEY = new CompilerConfigurationKey<>("Directory to save compose build reports");
    private static final CompilerConfigurationKey<Boolean> INTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_KEY = new CompilerConfigurationKey<>("Enable optimization to treat remember as an intrinsic");
    private static final CompilerConfigurationKey<Boolean> NON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_KEY = new CompilerConfigurationKey<>("Enabled optimization to remove groups around non-skipping functions");
    private static final CompilerConfigurationKey<String> SUPPRESS_KOTLIN_VERSION_COMPATIBILITY_CHECK = new CompilerConfigurationKey<>("Deprecated. Version of Kotlin for which version compatibility check should be suppressed");
    private static final CompilerConfigurationKey<Boolean> DECOYS_ENABLED_KEY = new CompilerConfigurationKey<>("Generate decoy methods in IR transform");
    private static final CompilerConfigurationKey<Boolean> STRONG_SKIPPING_ENABLED_KEY = new CompilerConfigurationKey<>("Enable strong skipping mode");
    private static final CompilerConfigurationKey<List<String>> STABILITY_CONFIG_PATH_KEY = new CompilerConfigurationKey<>("Path to stability configuration file");
    private static final CompilerConfigurationKey<Set<String>> TEST_STABILITY_CONFIG_KEY = new CompilerConfigurationKey<>("Set of stable classes to be merged with configuration file, used for testing.");
    private static final CompilerConfigurationKey<Boolean> TRACE_MARKERS_ENABLED_KEY = new CompilerConfigurationKey<>("Include composition trace markers in generated code");
    private static final CompilerConfigurationKey<List<String>> FEATURE_FLAGS = new CompilerConfigurationKey<>("A list of features to enable.");
    private static final CompilerConfigurationKey<Boolean> SKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_KEY = new CompilerConfigurationKey<>("Skip IR lowering transformation when finding Compose runtime fails");
    private static final CompilerConfigurationKey<String> TARGET_RUNTIME_VERSION_KEY = new CompilerConfigurationKey<>("Target Compose runtime version for the compiler.");

    private ComposeConfiguration() {
    }

    public final CompilerConfigurationKey<Boolean> getDECOYS_ENABLED_KEY() {
        return DECOYS_ENABLED_KEY;
    }

    public final CompilerConfigurationKey<List<String>> getFEATURE_FLAGS() {
        return FEATURE_FLAGS;
    }

    public final CompilerConfigurationKey<Boolean> getGENERATE_FUNCTION_KEY_META_ANNOTATION_KEY() {
        return GENERATE_FUNCTION_KEY_META_ANNOTATION_KEY;
    }

    public final CompilerConfigurationKey<Boolean> getINTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_KEY() {
        return INTRINSIC_REMEMBER_OPTIMIZATION_ENABLED_KEY;
    }

    public final CompilerConfigurationKey<Boolean> getLIVE_LITERALS_ENABLED_KEY() {
        return LIVE_LITERALS_ENABLED_KEY;
    }

    public final CompilerConfigurationKey<Boolean> getLIVE_LITERALS_V2_ENABLED_KEY() {
        return LIVE_LITERALS_V2_ENABLED_KEY;
    }

    public final CompilerConfigurationKey<String> getMETRICS_DESTINATION_KEY() {
        return METRICS_DESTINATION_KEY;
    }

    public final CompilerConfigurationKey<Boolean> getNON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_KEY() {
        return NON_SKIPPING_GROUP_OPTIMIZATION_ENABLED_KEY;
    }

    public final CompilerConfigurationKey<String> getREPORTS_DESTINATION_KEY() {
        return REPORTS_DESTINATION_KEY;
    }

    public final CompilerConfigurationKey<Boolean> getSKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_KEY() {
        return SKIP_IR_LOWERING_IF_RUNTIME_NOT_FOUND_KEY;
    }

    public final CompilerConfigurationKey<Boolean> getSOURCE_INFORMATION_ENABLED_KEY() {
        return SOURCE_INFORMATION_ENABLED_KEY;
    }

    public final CompilerConfigurationKey<List<String>> getSTABILITY_CONFIG_PATH_KEY() {
        return STABILITY_CONFIG_PATH_KEY;
    }

    public final CompilerConfigurationKey<Boolean> getSTRONG_SKIPPING_ENABLED_KEY() {
        return STRONG_SKIPPING_ENABLED_KEY;
    }

    public final CompilerConfigurationKey<String> getSUPPRESS_KOTLIN_VERSION_COMPATIBILITY_CHECK() {
        return SUPPRESS_KOTLIN_VERSION_COMPATIBILITY_CHECK;
    }

    public final CompilerConfigurationKey<String> getTARGET_RUNTIME_VERSION_KEY() {
        return TARGET_RUNTIME_VERSION_KEY;
    }

    public final CompilerConfigurationKey<Set<String>> getTEST_STABILITY_CONFIG_KEY() {
        return TEST_STABILITY_CONFIG_KEY;
    }

    public final CompilerConfigurationKey<Boolean> getTRACE_MARKERS_ENABLED_KEY() {
        return TRACE_MARKERS_ENABLED_KEY;
    }
}
