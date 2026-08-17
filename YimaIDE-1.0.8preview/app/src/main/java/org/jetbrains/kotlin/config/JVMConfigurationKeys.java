package org.jetbrains.kotlin.config;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCompilationComponents;
import org.jetbrains.kotlin.modules.Module;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00100\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00101\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00102\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00103\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00104\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00105\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00106\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00107\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00108\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00109\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010:\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010;\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010<\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lorg/jetbrains/kotlin/config/JVMConfigurationKeys;", Argument.Delimiters.none, "<init>", "()V", "OUTPUT_DIRECTORY", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "Ljava/io/File;", "OUTPUT_JAR", "INCLUDE_RUNTIME", Argument.Delimiters.none, "JDK_HOME", "NO_JDK", "DISABLE_STANDARD_SCRIPT_DEFINITION", "DISABLE_CALL_ASSERTIONS", "DISABLE_RECEIVER_ASSERTIONS", "DISABLE_PARAM_ASSERTIONS", "ASSERTIONS_MODE", "Lorg/jetbrains/kotlin/config/JVMAssertionsMode;", "DISABLE_OPTIMIZATION", "USE_TYPE_TABLE", "JVM_TARGET", "Lorg/jetbrains/kotlin/config/JvmTarget;", "PARAMETERS_METADATA", "INCREMENTAL_COMPILATION_COMPONENTS", "Lorg/jetbrains/kotlin/load/kotlin/incremental/components/IncrementalCompilationComponents;", "MODULE_XML_FILE", "MODULES", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/modules/Module;", "FRIEND_PATHS", Argument.Delimiters.none, "USE_PSI_CLASS_FILES_READING", "USE_FAST_JAR_FILE_SYSTEM", "USE_JAVAC", "COMPILE_JAVA", "ADDITIONAL_JAVA_MODULES", "EMIT_JVM_TYPE_ANNOTATIONS", "STRING_CONCAT", "Lorg/jetbrains/kotlin/config/JvmStringConcat;", "JDK_RELEASE", Argument.Delimiters.none, "SAM_CONVERSIONS", "Lorg/jetbrains/kotlin/config/JvmClosureGenerationScheme;", "LAMBDAS", "KLIB_PATHS", "ABI_STABILITY", "Lorg/jetbrains/kotlin/config/JvmAbiStability;", "DO_NOT_CLEAR_BINDING_CONTEXT", "NO_RESET_JAR_TIMESTAMPS", "NO_UNIFIED_NULL_CHECKS", "NO_SOURCE_DEBUG_EXTENSION", "USE_OLD_INLINE_CLASSES_MANGLING_SCHEME", "ENABLE_JVM_PREVIEW", "NO_REFLECT", "VALIDATE_BYTECODE", "LINK_VIA_SIGNATURES", "ENABLE_DEBUG_MODE", "ENHANCED_COROUTINES_DEBUGGING", "NO_NEW_JAVA_ANNOTATION_TARGETS", "USE_INLINE_SCOPES_NUMBERS", "SKIP_BODIES", "EXPRESSION_TO_EVALUATE", "WHEN_GENERATION_SCHEME", "Lorg/jetbrains/kotlin/config/JvmWhenGenerationScheme;", "IGNORED_ANNOTATIONS_FOR_BRIDGES", "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JVMConfigurationKeys {
    public static final CompilerConfigurationKey<JvmAbiStability> ABI_STABILITY;
    public static final CompilerConfigurationKey<List<String>> ADDITIONAL_JAVA_MODULES;
    public static final CompilerConfigurationKey<JVMAssertionsMode> ASSERTIONS_MODE;
    public static final CompilerConfigurationKey<Boolean> COMPILE_JAVA;
    public static final CompilerConfigurationKey<Boolean> DISABLE_CALL_ASSERTIONS;
    public static final CompilerConfigurationKey<Boolean> DISABLE_OPTIMIZATION;
    public static final CompilerConfigurationKey<Boolean> DISABLE_PARAM_ASSERTIONS;
    public static final CompilerConfigurationKey<Boolean> DISABLE_RECEIVER_ASSERTIONS;
    public static final CompilerConfigurationKey<Boolean> DISABLE_STANDARD_SCRIPT_DEFINITION;
    public static final CompilerConfigurationKey<Boolean> DO_NOT_CLEAR_BINDING_CONTEXT;
    public static final CompilerConfigurationKey<Boolean> EMIT_JVM_TYPE_ANNOTATIONS;
    public static final CompilerConfigurationKey<Boolean> ENABLE_DEBUG_MODE;
    public static final CompilerConfigurationKey<Boolean> ENABLE_JVM_PREVIEW;
    public static final CompilerConfigurationKey<Boolean> ENHANCED_COROUTINES_DEBUGGING;
    public static final CompilerConfigurationKey<String> EXPRESSION_TO_EVALUATE;
    public static final CompilerConfigurationKey<List<String>> FRIEND_PATHS;
    public static final CompilerConfigurationKey<List<String>> IGNORED_ANNOTATIONS_FOR_BRIDGES;
    public static final CompilerConfigurationKey<Boolean> INCLUDE_RUNTIME;
    public static final CompilerConfigurationKey<IncrementalCompilationComponents> INCREMENTAL_COMPILATION_COMPONENTS;
    public static final JVMConfigurationKeys INSTANCE = new JVMConfigurationKeys();
    public static final CompilerConfigurationKey<File> JDK_HOME;
    public static final CompilerConfigurationKey<Integer> JDK_RELEASE;
    public static final CompilerConfigurationKey<JvmTarget> JVM_TARGET;
    public static final CompilerConfigurationKey<List<String>> KLIB_PATHS;
    public static final CompilerConfigurationKey<JvmClosureGenerationScheme> LAMBDAS;
    public static final CompilerConfigurationKey<Boolean> LINK_VIA_SIGNATURES;
    public static final CompilerConfigurationKey<List<Module>> MODULES;
    public static final CompilerConfigurationKey<File> MODULE_XML_FILE;
    public static final CompilerConfigurationKey<Boolean> NO_JDK;
    public static final CompilerConfigurationKey<Boolean> NO_NEW_JAVA_ANNOTATION_TARGETS;
    public static final CompilerConfigurationKey<Boolean> NO_REFLECT;
    public static final CompilerConfigurationKey<Boolean> NO_RESET_JAR_TIMESTAMPS;
    public static final CompilerConfigurationKey<Boolean> NO_SOURCE_DEBUG_EXTENSION;
    public static final CompilerConfigurationKey<Boolean> NO_UNIFIED_NULL_CHECKS;
    public static final CompilerConfigurationKey<File> OUTPUT_DIRECTORY;
    public static final CompilerConfigurationKey<File> OUTPUT_JAR;
    public static final CompilerConfigurationKey<Boolean> PARAMETERS_METADATA;
    public static final CompilerConfigurationKey<JvmClosureGenerationScheme> SAM_CONVERSIONS;
    public static final CompilerConfigurationKey<Boolean> SKIP_BODIES;
    public static final CompilerConfigurationKey<JvmStringConcat> STRING_CONCAT;
    public static final CompilerConfigurationKey<Boolean> USE_FAST_JAR_FILE_SYSTEM;
    public static final CompilerConfigurationKey<Boolean> USE_INLINE_SCOPES_NUMBERS;
    public static final CompilerConfigurationKey<Boolean> USE_JAVAC;
    public static final CompilerConfigurationKey<Boolean> USE_OLD_INLINE_CLASSES_MANGLING_SCHEME;
    public static final CompilerConfigurationKey<Boolean> USE_PSI_CLASS_FILES_READING;
    public static final CompilerConfigurationKey<Boolean> USE_TYPE_TABLE;
    public static final CompilerConfigurationKey<Boolean> VALIDATE_BYTECODE;
    public static final CompilerConfigurationKey<JvmWhenGenerationScheme> WHEN_GENERATION_SCHEME;

    static {
        CompilerConfigurationKey.Companion companion = CompilerConfigurationKey.INSTANCE;
        OUTPUT_DIRECTORY = companion.create("OUTPUT_DIRECTORY");
        OUTPUT_JAR = companion.create("OUTPUT_JAR");
        INCLUDE_RUNTIME = companion.create("INCLUDE_RUNTIME");
        JDK_HOME = companion.create("JDK_HOME");
        NO_JDK = companion.create("NO_JDK");
        DISABLE_STANDARD_SCRIPT_DEFINITION = companion.create("DISABLE_STANDARD_SCRIPT_DEFINITION");
        DISABLE_CALL_ASSERTIONS = companion.create("DISABLE_CALL_ASSERTIONS");
        DISABLE_RECEIVER_ASSERTIONS = companion.create("DISABLE_RECEIVER_ASSERTIONS");
        DISABLE_PARAM_ASSERTIONS = companion.create("DISABLE_PARAM_ASSERTIONS");
        ASSERTIONS_MODE = companion.create("ASSERTIONS_MODE");
        DISABLE_OPTIMIZATION = companion.create("DISABLE_OPTIMIZATION");
        USE_TYPE_TABLE = companion.create("USE_TYPE_TABLE");
        JVM_TARGET = companion.create("JVM_TARGET");
        PARAMETERS_METADATA = companion.create("PARAMETERS_METADATA");
        INCREMENTAL_COMPILATION_COMPONENTS = companion.create("INCREMENTAL_COMPILATION_COMPONENTS");
        MODULE_XML_FILE = companion.create("MODULE_XML_FILE");
        MODULES = companion.create("MODULES");
        FRIEND_PATHS = companion.create("FRIEND_PATHS");
        USE_PSI_CLASS_FILES_READING = companion.create("USE_PSI_CLASS_FILES_READING");
        USE_FAST_JAR_FILE_SYSTEM = companion.create("USE_FAST_JAR_FILE_SYSTEM");
        USE_JAVAC = companion.create("USE_JAVAC");
        COMPILE_JAVA = companion.create("COMPILE_JAVA");
        ADDITIONAL_JAVA_MODULES = companion.create("ADDITIONAL_JAVA_MODULES");
        EMIT_JVM_TYPE_ANNOTATIONS = companion.create("EMIT_JVM_TYPE_ANNOTATIONS");
        STRING_CONCAT = companion.create("STRING_CONCAT");
        JDK_RELEASE = companion.create("JDK_RELEASE");
        SAM_CONVERSIONS = companion.create("SAM_CONVERSIONS");
        LAMBDAS = companion.create("LAMBDAS");
        KLIB_PATHS = companion.create("KLIB_PATHS");
        ABI_STABILITY = companion.create("ABI_STABILITY");
        DO_NOT_CLEAR_BINDING_CONTEXT = companion.create("DO_NOT_CLEAR_BINDING_CONTEXT");
        NO_RESET_JAR_TIMESTAMPS = companion.create("NO_RESET_JAR_TIMESTAMPS");
        NO_UNIFIED_NULL_CHECKS = companion.create("NO_UNIFIED_NULL_CHECKS");
        NO_SOURCE_DEBUG_EXTENSION = companion.create("NO_SOURCE_DEBUG_EXTENSION");
        USE_OLD_INLINE_CLASSES_MANGLING_SCHEME = companion.create("USE_OLD_INLINE_CLASSES_MANGLING_SCHEME");
        ENABLE_JVM_PREVIEW = companion.create("ENABLE_JVM_PREVIEW");
        NO_REFLECT = companion.create("NO_REFLECT");
        VALIDATE_BYTECODE = companion.create("VALIDATE_BYTECODE");
        LINK_VIA_SIGNATURES = companion.create("LINK_VIA_SIGNATURES");
        ENABLE_DEBUG_MODE = companion.create("ENABLE_DEBUG_MODE");
        ENHANCED_COROUTINES_DEBUGGING = companion.create("ENHANCED_COROUTINES_DEBUGGING");
        NO_NEW_JAVA_ANNOTATION_TARGETS = companion.create("NO_NEW_JAVA_ANNOTATION_TARGETS");
        USE_INLINE_SCOPES_NUMBERS = companion.create("USE_INLINE_SCOPES_NUMBERS");
        SKIP_BODIES = companion.create("SKIP_BODIES");
        EXPRESSION_TO_EVALUATE = companion.create("EXPRESSION_TO_EVALUATE");
        WHEN_GENERATION_SCHEME = companion.create("WHEN_GENERATION_SCHEME");
        IGNORED_ANNOTATIONS_FOR_BRIDGES = companion.create("IGNORED_ANNOTATIONS_FOR_BRIDGES");
    }

    private JVMConfigurationKeys() {
    }
}
