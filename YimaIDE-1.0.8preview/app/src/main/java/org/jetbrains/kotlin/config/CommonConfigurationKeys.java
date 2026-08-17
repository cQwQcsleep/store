package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.config.phaser.PhaseConfig;
import org.jetbrains.kotlin.incremental.components.EnumWhenTracker;
import org.jetbrains.kotlin.incremental.components.ExpectActualTracker;
import org.jetbrains.kotlin.incremental.components.ICFileMappingTracker;
import org.jetbrains.kotlin.incremental.components.ImportTracker;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020\n0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00101\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00102\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00103\u001a\b\u0012\u0004\u0012\u00020\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00104\u001a\b\u0012\u0004\u0012\u0002050\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00106\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00107\u001a\b\u0012\u0004\u0012\u0002080\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lorg/jetbrains/kotlin/config/CommonConfigurationKeys;", Argument.Delimiters.none, "<init>", "()V", "LANGUAGE_VERSION_SETTINGS", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "DISABLE_INLINE", Argument.Delimiters.none, "MODULE_NAME", Argument.Delimiters.none, "REPORT_OUTPUT_FILES", "LOOKUP_TRACKER", "Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "EXPECT_ACTUAL_TRACKER", "Lorg/jetbrains/kotlin/incremental/components/ExpectActualTracker;", "INLINE_CONST_TRACKER", "Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "FILE_MAPPING_TRACKER", "Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;", "ENUM_WHEN_TRACKER", "Lorg/jetbrains/kotlin/incremental/components/EnumWhenTracker;", "IMPORT_TRACKER", "Lorg/jetbrains/kotlin/incremental/components/ImportTracker;", "METADATA_VERSION", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "USE_FIR", "USE_LIGHT_TREE", "HMPP_MODULE_STRUCTURE", "Lorg/jetbrains/kotlin/config/HmppCliModuleStructure;", "METADATA_KLIB", "USE_FIR_EXTRA_CHECKERS", "USE_FIR_EXPERIMENTAL_CHECKERS", "DUMP_INFERENCE_LOGS", "PARALLEL_BACKEND_THREADS", Argument.Delimiters.none, "DUMP_MODEL", "INCREMENTAL_COMPILATION", "ALLOW_ANY_SCRIPTS_IN_SOURCE_ROOTS", "IGNORE_CONST_OPTIMIZATION_ERRORS", "MESSAGE_COLLECTOR_KEY", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "VERIFY_IR", "Lorg/jetbrains/kotlin/config/IrVerificationMode;", "ENABLE_IR_VISIBILITY_CHECKS", "ENABLE_IR_VARARG_TYPES_CHECKS", "ENABLE_IR_NESTED_OFFSETS_CHECKS", "PHASE_CONFIG", "Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "DONT_CREATE_SEPARATE_SESSION_FOR_SCRIPTS", "DONT_SORT_SOURCE_FILES", "SCRIPTING_HOST_CONFIGURATION", "PERF_MANAGER", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "DETAILED_PERF", "TARGET_PLATFORM", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonConfigurationKeys {
    public static final CompilerConfigurationKey<Boolean> ALLOW_ANY_SCRIPTS_IN_SOURCE_ROOTS;
    public static final CompilerConfigurationKey<Boolean> DETAILED_PERF;
    public static final CompilerConfigurationKey<Boolean> DISABLE_INLINE;
    public static final CompilerConfigurationKey<Boolean> DONT_CREATE_SEPARATE_SESSION_FOR_SCRIPTS;
    public static final CompilerConfigurationKey<Boolean> DONT_SORT_SOURCE_FILES;
    public static final CompilerConfigurationKey<Boolean> DUMP_INFERENCE_LOGS;
    public static final CompilerConfigurationKey<String> DUMP_MODEL;
    public static final CompilerConfigurationKey<Boolean> ENABLE_IR_NESTED_OFFSETS_CHECKS;
    public static final CompilerConfigurationKey<Boolean> ENABLE_IR_VARARG_TYPES_CHECKS;
    public static final CompilerConfigurationKey<Boolean> ENABLE_IR_VISIBILITY_CHECKS;
    public static final CompilerConfigurationKey<EnumWhenTracker> ENUM_WHEN_TRACKER;
    public static final CompilerConfigurationKey<ExpectActualTracker> EXPECT_ACTUAL_TRACKER;
    public static final CompilerConfigurationKey<ICFileMappingTracker> FILE_MAPPING_TRACKER;
    public static final CompilerConfigurationKey<HmppCliModuleStructure> HMPP_MODULE_STRUCTURE;
    public static final CompilerConfigurationKey<Boolean> IGNORE_CONST_OPTIMIZATION_ERRORS;
    public static final CompilerConfigurationKey<ImportTracker> IMPORT_TRACKER;
    public static final CompilerConfigurationKey<Boolean> INCREMENTAL_COMPILATION;
    public static final CompilerConfigurationKey<InlineConstTracker> INLINE_CONST_TRACKER;
    public static final CommonConfigurationKeys INSTANCE = new CommonConfigurationKeys();
    public static final CompilerConfigurationKey<LanguageVersionSettings> LANGUAGE_VERSION_SETTINGS;
    public static final CompilerConfigurationKey<LookupTracker> LOOKUP_TRACKER;
    public static final CompilerConfigurationKey<MessageCollector> MESSAGE_COLLECTOR_KEY;
    public static final CompilerConfigurationKey<Boolean> METADATA_KLIB;
    public static final CompilerConfigurationKey<BinaryVersion> METADATA_VERSION;
    public static final CompilerConfigurationKey<String> MODULE_NAME;
    public static final CompilerConfigurationKey<Integer> PARALLEL_BACKEND_THREADS;
    public static final CompilerConfigurationKey<PerformanceManager> PERF_MANAGER;
    public static final CompilerConfigurationKey<PhaseConfig> PHASE_CONFIG;
    public static final CompilerConfigurationKey<Boolean> REPORT_OUTPUT_FILES;
    public static final CompilerConfigurationKey<Object> SCRIPTING_HOST_CONFIGURATION;
    public static final CompilerConfigurationKey<TargetPlatform> TARGET_PLATFORM;
    public static final CompilerConfigurationKey<Boolean> USE_FIR;
    public static final CompilerConfigurationKey<Boolean> USE_FIR_EXPERIMENTAL_CHECKERS;
    public static final CompilerConfigurationKey<Boolean> USE_FIR_EXTRA_CHECKERS;
    public static final CompilerConfigurationKey<Boolean> USE_LIGHT_TREE;
    public static final CompilerConfigurationKey<IrVerificationMode> VERIFY_IR;

    static {
        CompilerConfigurationKey.Companion companion = CompilerConfigurationKey.INSTANCE;
        LANGUAGE_VERSION_SETTINGS = companion.create("LANGUAGE_VERSION_SETTINGS");
        DISABLE_INLINE = companion.create("DISABLE_INLINE");
        MODULE_NAME = companion.create("MODULE_NAME");
        REPORT_OUTPUT_FILES = companion.create("REPORT_OUTPUT_FILES");
        LOOKUP_TRACKER = companion.create("LOOKUP_TRACKER");
        EXPECT_ACTUAL_TRACKER = companion.create("EXPECT_ACTUAL_TRACKER");
        INLINE_CONST_TRACKER = companion.create("INLINE_CONST_TRACKER");
        FILE_MAPPING_TRACKER = companion.create("FILE_MAPPING_TRACKER");
        ENUM_WHEN_TRACKER = companion.create("ENUM_WHEN_TRACKER");
        IMPORT_TRACKER = companion.create("IMPORT_TRACKER");
        METADATA_VERSION = companion.create("METADATA_VERSION");
        USE_FIR = companion.create("USE_FIR");
        USE_LIGHT_TREE = companion.create("USE_LIGHT_TREE");
        HMPP_MODULE_STRUCTURE = companion.create("HMPP_MODULE_STRUCTURE");
        METADATA_KLIB = companion.create("METADATA_KLIB");
        USE_FIR_EXTRA_CHECKERS = companion.create("USE_FIR_EXTRA_CHECKERS");
        USE_FIR_EXPERIMENTAL_CHECKERS = companion.create("USE_FIR_EXPERIMENTAL_CHECKERS");
        DUMP_INFERENCE_LOGS = companion.create("DUMP_INFERENCE_LOGS");
        PARALLEL_BACKEND_THREADS = companion.create("PARALLEL_BACKEND_THREADS");
        DUMP_MODEL = companion.create("DUMP_MODEL");
        INCREMENTAL_COMPILATION = companion.create("INCREMENTAL_COMPILATION");
        ALLOW_ANY_SCRIPTS_IN_SOURCE_ROOTS = companion.create("ALLOW_ANY_SCRIPTS_IN_SOURCE_ROOTS");
        IGNORE_CONST_OPTIMIZATION_ERRORS = companion.create("IGNORE_CONST_OPTIMIZATION_ERRORS");
        MESSAGE_COLLECTOR_KEY = companion.create("MESSAGE_COLLECTOR_KEY");
        VERIFY_IR = companion.create("VERIFY_IR");
        ENABLE_IR_VISIBILITY_CHECKS = companion.create("ENABLE_IR_VISIBILITY_CHECKS");
        ENABLE_IR_VARARG_TYPES_CHECKS = companion.create("ENABLE_IR_VARARG_TYPES_CHECKS");
        ENABLE_IR_NESTED_OFFSETS_CHECKS = companion.create("ENABLE_IR_NESTED_OFFSETS_CHECKS");
        PHASE_CONFIG = companion.create("PHASE_CONFIG");
        DONT_CREATE_SEPARATE_SESSION_FOR_SCRIPTS = companion.create("DONT_CREATE_SEPARATE_SESSION_FOR_SCRIPTS");
        DONT_SORT_SOURCE_FILES = companion.create("DONT_SORT_SOURCE_FILES");
        SCRIPTING_HOST_CONFIGURATION = companion.create("SCRIPTING_HOST_CONFIGURATION");
        PERF_MANAGER = companion.create("PERF_MANAGER");
        DETAILED_PERF = companion.create("DETAILED_PERF");
        TARGET_PLATFORM = companion.create("TARGET_PLATFORM");
    }

    private CommonConfigurationKeys() {
    }
}
