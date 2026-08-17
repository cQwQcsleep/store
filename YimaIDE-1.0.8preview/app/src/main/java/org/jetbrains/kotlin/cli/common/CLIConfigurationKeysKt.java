package org.jetbrains.kotlin.cli.common;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.config.ContentRoot;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.modules.ModuleChunk;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.utils.KotlinPaths;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\"4\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00042\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\",\u0010\n\u001a\u0004\u0018\u00010\t*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u00010\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\"(\u0010\u0010\u001a\u00020\u000f*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u000f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\"(\u0010\u0016\u001a\u00020\u0015*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\"(\u0010\u001b\u001a\u00020\u0015*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001a\"(\u0010\u001e\u001a\u00020\u0015*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001a\",\u0010\"\u001a\u0004\u0018\u00010!*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u00010!8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\",\u0010(\u001a\u0004\u0018\u00010'*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u00010'8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,\",\u0010-\u001a\u0004\u0018\u00010'*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u00010'8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b.\u0010*\"\u0004\b/\u0010,\"(\u00100\u001a\u00020\u0015*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b1\u0010\u0018\"\u0004\b2\u0010\u001a\"(\u00103\u001a\u00020\u0015*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b4\u0010\u0018\"\u0004\b5\u0010\u001a\"(\u00106\u001a\u00020\u0015*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b7\u0010\u0018\"\u0004\b8\u0010\u001a\",\u0010:\u001a\u0004\u0018\u000109*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u0001098F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>\"(\u0010?\u001a\u00020\u0015*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b@\u0010\u0018\"\u0004\bA\u0010\u001a\",\u0010C\u001a\u0004\u0018\u00010B*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u00010B8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bD\u0010E\"\u0004\bF\u0010G\",\u0010H\u001a\u0004\u0018\u00010'*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u00010'8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bI\u0010*\"\u0004\bJ\u0010,\"4\u0010K\u001a\b\u0012\u0004\u0012\u00020!0\u0001*\u00020\u00042\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020!0\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bL\u0010\u0006\"\u0004\bM\u0010\b\",\u0010N\u001a\u0004\u0018\u00010!*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u00010!8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bO\u0010$\"\u0004\bP\u0010&\"(\u0010Q\u001a\u00020\u0015*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bR\u0010\u0018\"\u0004\bS\u0010\u001a¨\u0006T"}, d2 = {"value", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/config/ContentRoot;", "contentRoots", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getContentRoots", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/util/List;", "setContentRoots", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;)V", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "originalMessageCollectorKey", "getOriginalMessageCollectorKey", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "setOriginalMessageCollectorKey", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;)V", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "diagnosticsCollector", "getDiagnosticsCollector", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "setDiagnosticsCollector", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;)V", Argument.Delimiters.none, "renderDiagnosticInternalName", "getRenderDiagnosticInternalName", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Z", "setRenderDiagnosticInternalName", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Z)V", "treatWarningsAsErrors", "getTreatWarningsAsErrors", "setTreatWarningsAsErrors", "allowKotlinPackage", "getAllowKotlinPackage", "setAllowKotlinPackage", Argument.Delimiters.none, "intellijPluginRoot", "getIntellijPluginRoot", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/String;", "setIntellijPluginRoot", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/String;)V", "Ljava/io/File;", "metadataDestinationDirectory", "getMetadataDestinationDirectory", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/io/File;", "setMetadataDestinationDirectory", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/io/File;)V", "pathToKotlinCompilerJar", "getPathToKotlinCompilerJar", "setPathToKotlinCompilerJar", "printVersion", "getPrintVersion", "setPrintVersion", "scriptMode", "getScriptMode", "setScriptMode", "replMode", "getReplMode", "setReplMode", "Lorg/jetbrains/kotlin/utils/KotlinPaths;", "kotlinPaths", "getKotlinPaths", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/utils/KotlinPaths;", "setKotlinPaths", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/utils/KotlinPaths;)V", "allowNoSourceFiles", "getAllowNoSourceFiles", "setAllowNoSourceFiles", "Lorg/jetbrains/kotlin/cli/common/modules/ModuleChunk;", "moduleChunk", "getModuleChunk", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/cli/common/modules/ModuleChunk;", "setModuleChunk", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/common/modules/ModuleChunk;)V", "buildFile", "getBuildFile", "setBuildFile", "freeArgsForScript", "getFreeArgsForScript", "setFreeArgsForScript", "defaultExtensionForScripts", "getDefaultExtensionForScripts", "setDefaultExtensionForScripts", "testEnvironment", "getTestEnvironment", "setTestEnvironment", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CLIConfigurationKeysKt {
    public static BaseDiagnosticsCollector a() {
        throw new IllegalStateException("diagnostic collector is not initialized");
    }

    public static final boolean getAllowKotlinPackage(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CLIConfigurationKeys.ALLOW_KOTLIN_PACKAGE);
    }

    public static final boolean getAllowNoSourceFiles(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CLIConfigurationKeys.ALLOW_NO_SOURCE_FILES);
    }

    public static final File getBuildFile(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (File) compilerConfiguration.get(CLIConfigurationKeys.BUILD_FILE);
    }

    public static final List<ContentRoot> getContentRoots(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(CLIConfigurationKeys.CONTENT_ROOTS);
    }

    public static final String getDefaultExtensionForScripts(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(CLIConfigurationKeys.DEFAULT_EXTENSION_FOR_SCRIPTS);
    }

    public static final BaseDiagnosticsCollector getDiagnosticsCollector(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (BaseDiagnosticsCollector) compilerConfiguration.getOrDefault(CLIConfigurationKeys.DIAGNOSTICS_COLLECTOR, new Function0() { // from class: r71
            public final Object invoke() {
                return CLIConfigurationKeysKt.a();
            }
        });
    }

    public static final List<String> getFreeArgsForScript(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(CLIConfigurationKeys.FREE_ARGS_FOR_SCRIPT);
    }

    public static final String getIntellijPluginRoot(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (String) compilerConfiguration.get(CLIConfigurationKeys.INTELLIJ_PLUGIN_ROOT);
    }

    public static final KotlinPaths getKotlinPaths(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (KotlinPaths) compilerConfiguration.get(CLIConfigurationKeys.KOTLIN_PATHS);
    }

    public static final File getMetadataDestinationDirectory(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (File) compilerConfiguration.get(CLIConfigurationKeys.METADATA_DESTINATION_DIRECTORY);
    }

    public static final ModuleChunk getModuleChunk(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (ModuleChunk) compilerConfiguration.get(CLIConfigurationKeys.MODULE_CHUNK);
    }

    public static final MessageCollector getOriginalMessageCollectorKey(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (MessageCollector) compilerConfiguration.get(CLIConfigurationKeys.ORIGINAL_MESSAGE_COLLECTOR_KEY);
    }

    public static final File getPathToKotlinCompilerJar(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (File) compilerConfiguration.get(CLIConfigurationKeys.PATH_TO_KOTLIN_COMPILER_JAR);
    }

    public static final boolean getPrintVersion(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CLIConfigurationKeys.PRINT_VERSION);
    }

    public static final boolean getRenderDiagnosticInternalName(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CLIConfigurationKeys.RENDER_DIAGNOSTIC_INTERNAL_NAME);
    }

    public static final boolean getReplMode(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CLIConfigurationKeys.REPL_MODE);
    }

    public static final boolean getScriptMode(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CLIConfigurationKeys.SCRIPT_MODE);
    }

    public static final boolean getTestEnvironment(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CLIConfigurationKeys.TEST_ENVIRONMENT);
    }

    public static final boolean getTreatWarningsAsErrors(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(CLIConfigurationKeys.TREAT_WARNINGS_AS_ERRORS);
    }

    public static final void setAllowKotlinPackage(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.ALLOW_KOTLIN_PACKAGE, Boolean.valueOf(z));
    }

    public static final void setAllowNoSourceFiles(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.ALLOW_NO_SOURCE_FILES, Boolean.valueOf(z));
    }

    public static final void setBuildFile(CompilerConfiguration compilerConfiguration, File file) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<File> compilerConfigurationKey = CLIConfigurationKeys.BUILD_FILE;
        if (file != null) {
            compilerConfiguration.put(compilerConfigurationKey, file);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setContentRoots(CompilerConfiguration compilerConfiguration, List<? extends ContentRoot> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.CONTENT_ROOTS, list);
    }

    public static final void setDefaultExtensionForScripts(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(CLIConfigurationKeys.DEFAULT_EXTENSION_FOR_SCRIPTS, str);
    }

    public static final void setDiagnosticsCollector(CompilerConfiguration compilerConfiguration, BaseDiagnosticsCollector baseDiagnosticsCollector) {
        compilerConfiguration.getClass();
        baseDiagnosticsCollector.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.DIAGNOSTICS_COLLECTOR, baseDiagnosticsCollector);
    }

    public static final void setFreeArgsForScript(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.FREE_ARGS_FOR_SCRIPT, list);
    }

    public static final void setIntellijPluginRoot(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<String> compilerConfigurationKey = CLIConfigurationKeys.INTELLIJ_PLUGIN_ROOT;
        if (str != null) {
            compilerConfiguration.put(compilerConfigurationKey, str);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setKotlinPaths(CompilerConfiguration compilerConfiguration, KotlinPaths kotlinPaths) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<KotlinPaths> compilerConfigurationKey = CLIConfigurationKeys.KOTLIN_PATHS;
        if (kotlinPaths != null) {
            compilerConfiguration.put(compilerConfigurationKey, kotlinPaths);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setMetadataDestinationDirectory(CompilerConfiguration compilerConfiguration, File file) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<File> compilerConfigurationKey = CLIConfigurationKeys.METADATA_DESTINATION_DIRECTORY;
        if (file != null) {
            compilerConfiguration.put(compilerConfigurationKey, file);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setModuleChunk(CompilerConfiguration compilerConfiguration, ModuleChunk moduleChunk) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<ModuleChunk> compilerConfigurationKey = CLIConfigurationKeys.MODULE_CHUNK;
        if (moduleChunk != null) {
            compilerConfiguration.put(compilerConfigurationKey, moduleChunk);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setOriginalMessageCollectorKey(CompilerConfiguration compilerConfiguration, MessageCollector messageCollector) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<MessageCollector> compilerConfigurationKey = CLIConfigurationKeys.ORIGINAL_MESSAGE_COLLECTOR_KEY;
        if (messageCollector != null) {
            compilerConfiguration.put(compilerConfigurationKey, messageCollector);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setPathToKotlinCompilerJar(CompilerConfiguration compilerConfiguration, File file) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<File> compilerConfigurationKey = CLIConfigurationKeys.PATH_TO_KOTLIN_COMPILER_JAR;
        if (file != null) {
            compilerConfiguration.put(compilerConfigurationKey, file);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setPrintVersion(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.PRINT_VERSION, Boolean.valueOf(z));
    }

    public static final void setRenderDiagnosticInternalName(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.RENDER_DIAGNOSTIC_INTERNAL_NAME, Boolean.valueOf(z));
    }

    public static final void setReplMode(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.REPL_MODE, Boolean.valueOf(z));
    }

    public static final void setScriptMode(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.SCRIPT_MODE, Boolean.valueOf(z));
    }

    public static final void setTestEnvironment(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.TEST_ENVIRONMENT, Boolean.valueOf(z));
    }

    public static final void setTreatWarningsAsErrors(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(CLIConfigurationKeys.TREAT_WARNINGS_AS_ERRORS, Boolean.valueOf(z));
    }
}
