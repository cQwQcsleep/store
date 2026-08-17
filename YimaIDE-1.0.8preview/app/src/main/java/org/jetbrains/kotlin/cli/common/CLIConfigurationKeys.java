package org.jetbrains.kotlin.cli.common;

import java.io.File;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.config.ContentRoot;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.modules.ModuleChunk;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.utils.KotlinPaths;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00150\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/CLIConfigurationKeys;", Argument.Delimiters.none, "<init>", "()V", "CONTENT_ROOTS", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/config/ContentRoot;", "MESSAGE_COLLECTOR_KEY", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "getMESSAGE_COLLECTOR_KEY$annotations", "ORIGINAL_MESSAGE_COLLECTOR_KEY", "DIAGNOSTICS_COLLECTOR", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "RENDER_DIAGNOSTIC_INTERNAL_NAME", Argument.Delimiters.none, "TREAT_WARNINGS_AS_ERRORS", "ALLOW_KOTLIN_PACKAGE", "INTELLIJ_PLUGIN_ROOT", Argument.Delimiters.none, "METADATA_DESTINATION_DIRECTORY", "Ljava/io/File;", "PATH_TO_KOTLIN_COMPILER_JAR", "PRINT_VERSION", "SCRIPT_MODE", "REPL_MODE", "KOTLIN_PATHS", "Lorg/jetbrains/kotlin/utils/KotlinPaths;", "ALLOW_NO_SOURCE_FILES", "MODULE_CHUNK", "Lorg/jetbrains/kotlin/cli/common/modules/ModuleChunk;", "BUILD_FILE", "FREE_ARGS_FOR_SCRIPT", "DEFAULT_EXTENSION_FOR_SCRIPTS", "TEST_ENVIRONMENT", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CLIConfigurationKeys {
    public static final CompilerConfigurationKey<Boolean> ALLOW_KOTLIN_PACKAGE;
    public static final CompilerConfigurationKey<Boolean> ALLOW_NO_SOURCE_FILES;
    public static final CompilerConfigurationKey<File> BUILD_FILE;
    public static final CompilerConfigurationKey<List<ContentRoot>> CONTENT_ROOTS;
    public static final CompilerConfigurationKey<String> DEFAULT_EXTENSION_FOR_SCRIPTS;
    public static final CompilerConfigurationKey<BaseDiagnosticsCollector> DIAGNOSTICS_COLLECTOR;
    public static final CompilerConfigurationKey<List<String>> FREE_ARGS_FOR_SCRIPT;
    public static final CLIConfigurationKeys INSTANCE = new CLIConfigurationKeys();
    public static final CompilerConfigurationKey<String> INTELLIJ_PLUGIN_ROOT;
    public static final CompilerConfigurationKey<KotlinPaths> KOTLIN_PATHS;
    public static final CompilerConfigurationKey<MessageCollector> MESSAGE_COLLECTOR_KEY;
    public static final CompilerConfigurationKey<File> METADATA_DESTINATION_DIRECTORY;
    public static final CompilerConfigurationKey<ModuleChunk> MODULE_CHUNK;
    public static final CompilerConfigurationKey<MessageCollector> ORIGINAL_MESSAGE_COLLECTOR_KEY;
    public static final CompilerConfigurationKey<File> PATH_TO_KOTLIN_COMPILER_JAR;
    public static final CompilerConfigurationKey<Boolean> PRINT_VERSION;
    public static final CompilerConfigurationKey<Boolean> RENDER_DIAGNOSTIC_INTERNAL_NAME;
    public static final CompilerConfigurationKey<Boolean> REPL_MODE;
    public static final CompilerConfigurationKey<Boolean> SCRIPT_MODE;
    public static final CompilerConfigurationKey<Boolean> TEST_ENVIRONMENT;
    public static final CompilerConfigurationKey<Boolean> TREAT_WARNINGS_AS_ERRORS;

    static {
        CompilerConfigurationKey.Companion companion = CompilerConfigurationKey.INSTANCE;
        CONTENT_ROOTS = companion.create("CONTENT_ROOTS");
        MESSAGE_COLLECTOR_KEY = CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY;
        ORIGINAL_MESSAGE_COLLECTOR_KEY = companion.create("ORIGINAL_MESSAGE_COLLECTOR_KEY");
        DIAGNOSTICS_COLLECTOR = companion.create("DIAGNOSTICS_COLLECTOR");
        RENDER_DIAGNOSTIC_INTERNAL_NAME = companion.create("RENDER_DIAGNOSTIC_INTERNAL_NAME");
        TREAT_WARNINGS_AS_ERRORS = companion.create("TREAT_WARNINGS_AS_ERRORS");
        ALLOW_KOTLIN_PACKAGE = companion.create("ALLOW_KOTLIN_PACKAGE");
        INTELLIJ_PLUGIN_ROOT = companion.create("INTELLIJ_PLUGIN_ROOT");
        METADATA_DESTINATION_DIRECTORY = companion.create("METADATA_DESTINATION_DIRECTORY");
        PATH_TO_KOTLIN_COMPILER_JAR = companion.create("PATH_TO_KOTLIN_COMPILER_JAR");
        PRINT_VERSION = companion.create("PRINT_VERSION");
        SCRIPT_MODE = companion.create("SCRIPT_MODE");
        REPL_MODE = companion.create("REPL_MODE");
        KOTLIN_PATHS = companion.create("KOTLIN_PATHS");
        ALLOW_NO_SOURCE_FILES = companion.create("ALLOW_NO_SOURCE_FILES");
        MODULE_CHUNK = companion.create("MODULE_CHUNK");
        BUILD_FILE = companion.create("BUILD_FILE");
        FREE_ARGS_FOR_SCRIPT = companion.create("FREE_ARGS_FOR_SCRIPT");
        DEFAULT_EXTENSION_FOR_SCRIPTS = companion.create("DEFAULT_EXTENSION_FOR_SCRIPTS");
        TEST_ENVIRONMENT = companion.create("TEST_ENVIRONMENT");
    }

    private CLIConfigurationKeys() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Please use CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY instead", replaceWith = @ReplaceWith(expression = "CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY", imports = {"org.jetbrains.kotlin.config.CommonConfigurationKeys"}))
    public static /* synthetic */ void getMESSAGE_COLLECTOR_KEY$annotations() {
    }
}
