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
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\"(\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"(\u0010\t\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\",\u0010\u000f\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\"(\u0010\u0014\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\r\",\u0010\u0018\u001a\u0004\u0018\u00010\u0017*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00178F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\",\u0010\u001e\u001a\u0004\u0018\u00010\u001d*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u001d8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"\",\u0010$\u001a\u0004\u0018\u00010#*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010#8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(\",\u0010*\u001a\u0004\u0018\u00010)*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010)8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\",\u00100\u001a\u0004\u0018\u00010/*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010/8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b1\u00102\"\u0004\b3\u00104\",\u00106\u001a\u0004\u0018\u000105*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u0001058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:\",\u0010<\u001a\u0004\u0018\u00010;*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010;8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@\"(\u0010A\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bB\u0010\u000b\"\u0004\bC\u0010\r\"(\u0010D\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bE\u0010\u000b\"\u0004\bF\u0010\r\",\u0010H\u001a\u0004\u0018\u00010G*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010G8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bI\u0010J\"\u0004\bK\u0010L\"(\u0010M\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bN\u0010\u000b\"\u0004\bO\u0010\r\"(\u0010P\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bQ\u0010\u000b\"\u0004\bR\u0010\r\"(\u0010S\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bT\u0010\u000b\"\u0004\bU\u0010\r\"(\u0010V\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bW\u0010\u000b\"\u0004\bX\u0010\r\",\u0010Z\u001a\u0004\u0018\u00010Y*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010Y8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^\",\u0010_\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b`\u0010\u0011\"\u0004\ba\u0010\u0013\"(\u0010b\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bc\u0010\u000b\"\u0004\bd\u0010\r\"(\u0010e\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bf\u0010\u000b\"\u0004\bg\u0010\r\"(\u0010h\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bi\u0010\u000b\"\u0004\bj\u0010\r\"(\u0010l\u001a\u00020k*\u00020\u00032\u0006\u0010\u0000\u001a\u00020k8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bm\u0010n\"\u0004\bo\u0010p\",\u0010r\u001a\u0004\u0018\u00010q*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010q8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bs\u0010t\"\u0004\bu\u0010v\"(\u0010w\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bx\u0010\u000b\"\u0004\by\u0010\r\"(\u0010z\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b{\u0010\u000b\"\u0004\b|\u0010\r\"(\u0010}\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b~\u0010\u000b\"\u0004\b\u007f\u0010\r\"3\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0080\u0001*\u00020\u00032\t\u0010\u0000\u001a\u0005\u0018\u00010\u0080\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001\"+\u0010\u0086\u0001\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0087\u0001\u0010\u000b\"\u0005\b\u0088\u0001\u0010\r\"+\u0010\u0089\u0001\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u008a\u0001\u0010\u000b\"\u0005\b\u008b\u0001\u0010\r\"3\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008c\u0001*\u00020\u00032\t\u0010\u0000\u001a\u0005\u0018\u00010\u008c\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001\"\u0006\b\u0090\u0001\u0010\u0091\u0001\"3\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0092\u0001*\u00020\u00032\t\u0010\u0000\u001a\u0005\u0018\u00010\u0092\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001\"\u0006\b\u0096\u0001\u0010\u0097\u0001\"+\u0010\u0098\u0001\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0099\u0001\u0010\u000b\"\u0005\b\u009a\u0001\u0010\r\"3\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009b\u0001*\u00020\u00032\t\u0010\u0000\u001a\u0005\u0018\u00010\u009b\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001\"\u0006\b\u009f\u0001\u0010 \u0001¨\u0006¡\u0001"}, d2 = {"value", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getLanguageVersionSettings", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "setLanguageVersionSettings", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)V", Argument.Delimiters.none, "disableInline", "getDisableInline", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Z", "setDisableInline", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Z)V", Argument.Delimiters.none, "moduleName", "getModuleName", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/String;", "setModuleName", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/String;)V", "reportOutputFiles", "getReportOutputFiles", "setReportOutputFiles", "Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "lookupTracker", "getLookupTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "setLookupTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/incremental/components/LookupTracker;)V", "Lorg/jetbrains/kotlin/incremental/components/ExpectActualTracker;", "expectActualTracker", "getExpectActualTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/incremental/components/ExpectActualTracker;", "setExpectActualTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/incremental/components/ExpectActualTracker;)V", "Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "inlineConstTracker", "getInlineConstTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "setInlineConstTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;)V", "Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;", "fileMappingTracker", "getFileMappingTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;", "setFileMappingTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;)V", "Lorg/jetbrains/kotlin/incremental/components/EnumWhenTracker;", "enumWhenTracker", "getEnumWhenTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/incremental/components/EnumWhenTracker;", "setEnumWhenTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/incremental/components/EnumWhenTracker;)V", "Lorg/jetbrains/kotlin/incremental/components/ImportTracker;", "importTracker", "getImportTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/incremental/components/ImportTracker;", "setImportTracker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/incremental/components/ImportTracker;)V", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "metadataVersion", "getMetadataVersion", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "setMetadataVersion", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;)V", "useFir", "getUseFir", "setUseFir", "useLightTree", "getUseLightTree", "setUseLightTree", "Lorg/jetbrains/kotlin/config/HmppCliModuleStructure;", "hmppModuleStructure", "getHmppModuleStructure", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/HmppCliModuleStructure;", "setHmppModuleStructure", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/HmppCliModuleStructure;)V", "metadataKlib", "getMetadataKlib", "setMetadataKlib", "useFirExtraCheckers", "getUseFirExtraCheckers", "setUseFirExtraCheckers", "useFirExperimentalCheckers", "getUseFirExperimentalCheckers", "setUseFirExperimentalCheckers", "dumpInferenceLogs", "getDumpInferenceLogs", "setDumpInferenceLogs", Argument.Delimiters.none, "parallelBackendThreads", "getParallelBackendThreads", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/Integer;", "setParallelBackendThreads", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/Integer;)V", "dumpModel", "getDumpModel", "setDumpModel", "incrementalCompilation", "getIncrementalCompilation", "setIncrementalCompilation", "allowAnyScriptsInSourceRoots", "getAllowAnyScriptsInSourceRoots", "setAllowAnyScriptsInSourceRoots", "ignoreConstOptimizationErrors", "getIgnoreConstOptimizationErrors", "setIgnoreConstOptimizationErrors", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "messageCollector", "getMessageCollector", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "setMessageCollector", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;)V", "Lorg/jetbrains/kotlin/config/IrVerificationMode;", "verifyIr", "getVerifyIr", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/IrVerificationMode;", "setVerifyIr", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/IrVerificationMode;)V", "enableIrVisibilityChecks", "getEnableIrVisibilityChecks", "setEnableIrVisibilityChecks", "enableIrVarargTypesChecks", "getEnableIrVarargTypesChecks", "setEnableIrVarargTypesChecks", "enableIrNestedOffsetsChecks", "getEnableIrNestedOffsetsChecks", "setEnableIrNestedOffsetsChecks", "Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "phaseConfig", "getPhaseConfig", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "setPhaseConfig", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;)V", "dontCreateSeparateSessionForScripts", "getDontCreateSeparateSessionForScripts", "setDontCreateSeparateSessionForScripts", "dontSortSourceFiles", "getDontSortSourceFiles", "setDontSortSourceFiles", Argument.Delimiters.none, "scriptingHostConfiguration", "getScriptingHostConfiguration", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/Object;", "setScriptingHostConfiguration", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/Object;)V", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "perfManager", "getPerfManager", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/util/PerformanceManager;", "setPerfManager", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/util/PerformanceManager;)V", "detailedPerf", "getDetailedPerf", "setDetailedPerf", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "targetPlatform", "getTargetPlatform", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/platform/TargetPlatform;", "setTargetPlatform", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/platform/TargetPlatform;)V", "org.jetbrains.kotlin:config"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonConfigurationKeysKt {
    public static final boolean getAllowAnyScriptsInSourceRoots(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.ALLOW_ANY_SCRIPTS_IN_SOURCE_ROOTS);
    }

    public static final boolean getDetailedPerf(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.DETAILED_PERF);
    }

    public static final boolean getDisableInline(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.DISABLE_INLINE);
    }

    public static final boolean getDontCreateSeparateSessionForScripts(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.DONT_CREATE_SEPARATE_SESSION_FOR_SCRIPTS);
    }

    public static final boolean getDontSortSourceFiles(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.DONT_SORT_SOURCE_FILES);
    }

    public static final boolean getDumpInferenceLogs(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.DUMP_INFERENCE_LOGS);
    }

    public static final String getDumpModel(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(CommonConfigurationKeys.DUMP_MODEL);
    }

    public static final boolean getEnableIrNestedOffsetsChecks(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.ENABLE_IR_NESTED_OFFSETS_CHECKS);
    }

    public static final boolean getEnableIrVarargTypesChecks(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.ENABLE_IR_VARARG_TYPES_CHECKS);
    }

    public static final boolean getEnableIrVisibilityChecks(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.ENABLE_IR_VISIBILITY_CHECKS);
    }

    public static final EnumWhenTracker getEnumWhenTracker(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (EnumWhenTracker) compilerConfiguration.get(CommonConfigurationKeys.ENUM_WHEN_TRACKER);
    }

    public static final ExpectActualTracker getExpectActualTracker(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (ExpectActualTracker) compilerConfiguration.get(CommonConfigurationKeys.EXPECT_ACTUAL_TRACKER);
    }

    public static final ICFileMappingTracker getFileMappingTracker(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (ICFileMappingTracker) compilerConfiguration.get(CommonConfigurationKeys.FILE_MAPPING_TRACKER);
    }

    public static final HmppCliModuleStructure getHmppModuleStructure(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (HmppCliModuleStructure) compilerConfiguration.get(CommonConfigurationKeys.HMPP_MODULE_STRUCTURE);
    }

    public static final boolean getIgnoreConstOptimizationErrors(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.IGNORE_CONST_OPTIMIZATION_ERRORS);
    }

    public static final ImportTracker getImportTracker(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (ImportTracker) compilerConfiguration.get(CommonConfigurationKeys.IMPORT_TRACKER);
    }

    public static final boolean getIncrementalCompilation(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.INCREMENTAL_COMPILATION);
    }

    public static final InlineConstTracker getInlineConstTracker(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (InlineConstTracker) compilerConfiguration.get(CommonConfigurationKeys.INLINE_CONST_TRACKER);
    }

    public static final LanguageVersionSettings getLanguageVersionSettings(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (LanguageVersionSettings) compilerConfiguration.get(CommonConfigurationKeys.LANGUAGE_VERSION_SETTINGS, LanguageVersionSettingsImpl.DEFAULT);
    }

    public static final LookupTracker getLookupTracker(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (LookupTracker) compilerConfiguration.get(CommonConfigurationKeys.LOOKUP_TRACKER);
    }

    public static final MessageCollector getMessageCollector(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (MessageCollector) compilerConfiguration.get(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY, MessageCollector.INSTANCE.getNONE());
    }

    public static final boolean getMetadataKlib(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.METADATA_KLIB);
    }

    public static final BinaryVersion getMetadataVersion(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (BinaryVersion) compilerConfiguration.get(CommonConfigurationKeys.METADATA_VERSION);
    }

    public static final String getModuleName(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(CommonConfigurationKeys.MODULE_NAME);
    }

    public static final Integer getParallelBackendThreads(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (Integer) compilerConfiguration.get(CommonConfigurationKeys.PARALLEL_BACKEND_THREADS);
    }

    public static final PerformanceManager getPerfManager(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (PerformanceManager) compilerConfiguration.get(CommonConfigurationKeys.PERF_MANAGER);
    }

    public static final PhaseConfig getPhaseConfig(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (PhaseConfig) compilerConfiguration.get(CommonConfigurationKeys.PHASE_CONFIG);
    }

    public static final boolean getReportOutputFiles(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.REPORT_OUTPUT_FILES);
    }

    public static final Object getScriptingHostConfiguration(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.get(CommonConfigurationKeys.SCRIPTING_HOST_CONFIGURATION);
    }

    public static final TargetPlatform getTargetPlatform(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (TargetPlatform) compilerConfiguration.get(CommonConfigurationKeys.TARGET_PLATFORM);
    }

    public static final boolean getUseFir(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.USE_FIR);
    }

    public static final boolean getUseFirExperimentalCheckers(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.USE_FIR_EXPERIMENTAL_CHECKERS);
    }

    public static final boolean getUseFirExtraCheckers(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.USE_FIR_EXTRA_CHECKERS);
    }

    public static final boolean getUseLightTree(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CommonConfigurationKeys.USE_LIGHT_TREE);
    }

    public static final IrVerificationMode getVerifyIr(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (IrVerificationMode) compilerConfiguration.get(CommonConfigurationKeys.VERIFY_IR);
    }

    public static final void setAllowAnyScriptsInSourceRoots(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.ALLOW_ANY_SCRIPTS_IN_SOURCE_ROOTS, Boolean.valueOf(z));
    }

    public static final void setDetailedPerf(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.DETAILED_PERF, Boolean.valueOf(z));
    }

    public static final void setDisableInline(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.DISABLE_INLINE, Boolean.valueOf(z));
    }

    public static final void setDontCreateSeparateSessionForScripts(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.DONT_CREATE_SEPARATE_SESSION_FOR_SCRIPTS, Boolean.valueOf(z));
    }

    public static final void setDontSortSourceFiles(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.DONT_SORT_SOURCE_FILES, Boolean.valueOf(z));
    }

    public static final void setDumpInferenceLogs(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.DUMP_INFERENCE_LOGS, Boolean.valueOf(z));
    }

    public static final void setDumpModel(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<String> compilerConfigurationKey = CommonConfigurationKeys.DUMP_MODEL;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setEnableIrNestedOffsetsChecks(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.ENABLE_IR_NESTED_OFFSETS_CHECKS, Boolean.valueOf(z));
    }

    public static final void setEnableIrVarargTypesChecks(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.ENABLE_IR_VARARG_TYPES_CHECKS, Boolean.valueOf(z));
    }

    public static final void setEnableIrVisibilityChecks(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.ENABLE_IR_VISIBILITY_CHECKS, Boolean.valueOf(z));
    }

    public static final void setEnumWhenTracker(CompilerConfiguration compilerConfiguration, EnumWhenTracker enumWhenTracker) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(CommonConfigurationKeys.ENUM_WHEN_TRACKER, enumWhenTracker);
    }

    public static final void setExpectActualTracker(CompilerConfiguration compilerConfiguration, ExpectActualTracker expectActualTracker) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(CommonConfigurationKeys.EXPECT_ACTUAL_TRACKER, expectActualTracker);
    }

    public static final void setFileMappingTracker(CompilerConfiguration compilerConfiguration, ICFileMappingTracker iCFileMappingTracker) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(CommonConfigurationKeys.FILE_MAPPING_TRACKER, iCFileMappingTracker);
    }

    public static final void setHmppModuleStructure(CompilerConfiguration compilerConfiguration, HmppCliModuleStructure hmppCliModuleStructure) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<HmppCliModuleStructure> compilerConfigurationKey = CommonConfigurationKeys.HMPP_MODULE_STRUCTURE;
        if (hmppCliModuleStructure != null) {
            compilerConfiguration.put(compilerConfigurationKey, hmppCliModuleStructure);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setIgnoreConstOptimizationErrors(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.IGNORE_CONST_OPTIMIZATION_ERRORS, Boolean.valueOf(z));
    }

    public static final void setImportTracker(CompilerConfiguration compilerConfiguration, ImportTracker importTracker) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(CommonConfigurationKeys.IMPORT_TRACKER, importTracker);
    }

    public static final void setIncrementalCompilation(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.INCREMENTAL_COMPILATION, Boolean.valueOf(z));
    }

    public static final void setInlineConstTracker(CompilerConfiguration compilerConfiguration, InlineConstTracker inlineConstTracker) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(CommonConfigurationKeys.INLINE_CONST_TRACKER, inlineConstTracker);
    }

    public static final void setLanguageVersionSettings(CompilerConfiguration compilerConfiguration, LanguageVersionSettings languageVersionSettings) {
        compilerConfiguration.getClass();
        languageVersionSettings.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.LANGUAGE_VERSION_SETTINGS, languageVersionSettings);
    }

    public static final void setLookupTracker(CompilerConfiguration compilerConfiguration, LookupTracker lookupTracker) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(CommonConfigurationKeys.LOOKUP_TRACKER, lookupTracker);
    }

    public static final void setMessageCollector(CompilerConfiguration compilerConfiguration, MessageCollector messageCollector) {
        compilerConfiguration.getClass();
        messageCollector.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY, messageCollector);
    }

    public static final void setMetadataKlib(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.METADATA_KLIB, Boolean.valueOf(z));
    }

    public static final void setMetadataVersion(CompilerConfiguration compilerConfiguration, BinaryVersion binaryVersion) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<BinaryVersion> compilerConfigurationKey = CommonConfigurationKeys.METADATA_VERSION;
        if (binaryVersion != null) {
            compilerConfiguration.put(compilerConfigurationKey, binaryVersion);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setModuleName(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<String> compilerConfigurationKey = CommonConfigurationKeys.MODULE_NAME;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setParallelBackendThreads(CompilerConfiguration compilerConfiguration, Integer num) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<Integer> compilerConfigurationKey = CommonConfigurationKeys.PARALLEL_BACKEND_THREADS;
        if (num != null) {
            compilerConfiguration.put(compilerConfigurationKey, num);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setPerfManager(CompilerConfiguration compilerConfiguration, PerformanceManager performanceManager) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<PerformanceManager> compilerConfigurationKey = CommonConfigurationKeys.PERF_MANAGER;
        if (performanceManager != null) {
            compilerConfiguration.put(compilerConfigurationKey, performanceManager);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setPhaseConfig(CompilerConfiguration compilerConfiguration, PhaseConfig phaseConfig) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<PhaseConfig> compilerConfigurationKey = CommonConfigurationKeys.PHASE_CONFIG;
        if (phaseConfig != null) {
            compilerConfiguration.put(compilerConfigurationKey, phaseConfig);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setReportOutputFiles(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.REPORT_OUTPUT_FILES, Boolean.valueOf(z));
    }

    public static final void setScriptingHostConfiguration(CompilerConfiguration compilerConfiguration, Object obj) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<Object> compilerConfigurationKey = CommonConfigurationKeys.SCRIPTING_HOST_CONFIGURATION;
        if (obj != null) {
            compilerConfiguration.put(compilerConfigurationKey, obj);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setTargetPlatform(CompilerConfiguration compilerConfiguration, TargetPlatform targetPlatform) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<TargetPlatform> compilerConfigurationKey = CommonConfigurationKeys.TARGET_PLATFORM;
        if (targetPlatform != null) {
            compilerConfiguration.put(compilerConfigurationKey, targetPlatform);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setUseFir(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.USE_FIR, Boolean.valueOf(z));
    }

    public static final void setUseFirExperimentalCheckers(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.USE_FIR_EXPERIMENTAL_CHECKERS, Boolean.valueOf(z));
    }

    public static final void setUseFirExtraCheckers(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.USE_FIR_EXTRA_CHECKERS, Boolean.valueOf(z));
    }

    public static final void setUseLightTree(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CommonConfigurationKeys.USE_LIGHT_TREE, Boolean.valueOf(z));
    }

    public static final void setVerifyIr(CompilerConfiguration compilerConfiguration, IrVerificationMode irVerificationMode) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<IrVerificationMode> compilerConfigurationKey = CommonConfigurationKeys.VERIFY_IR;
        if (irVerificationMode != null) {
            compilerConfiguration.put(compilerConfigurationKey, irVerificationMode);
        } else {
            w01.a("nullable values are not allowed");
        }
    }
}
