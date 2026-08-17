package org.jetbrains.kotlin.cli.jvm;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.FilteringMessageCollector;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.modules.ModuleBuilder;
import org.jetbrains.kotlin.cli.common.modules.ModuleChunk;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.K2JVMCompilerKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CliCompilerUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CompileEnvironmentUtil;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.modules.Module;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0000\u001a\u0019\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"configureModuleChunk", "Lorg/jetbrains/kotlin/cli/common/modules/ModuleChunk;", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "buildFile", "Ljava/io/File;", "targetDescription", Argument.Delimiters.none, "main", Argument.Delimiters.none, "args", Argument.Delimiters.none, "([Ljava/lang/String;)V", "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JVMCompilerKt {
    public static final ModuleChunk configureModuleChunk(CompilerConfiguration compilerConfiguration, K2JVMCompilerArguments k2JVMCompilerArguments, File file) {
        String path;
        compilerConfiguration.getClass();
        k2JVMCompilerArguments.getClass();
        String destination = k2JVMCompilerArguments.getDestination();
        File file2 = destination != null ? new File(destination) : null;
        if (file != null) {
            MessageCollector messageCollector = (MessageCollector) compilerConfiguration.getNotNull(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY);
            if (file2 != null) {
                configureModuleChunk$strongWarning(compilerConfiguration, "The '-d' option with a directory destination is ignored because '-Xbuild-file' is specified");
            }
            if (!(k2JVMCompilerArguments.getJavaSourceRoots().length == 0)) {
                configureModuleChunk$strongWarning(compilerConfiguration, "The '-Xjava-source-roots' option is ignored because '-Xbuild-file' is specified");
            }
            if (k2JVMCompilerArguments.getJavaPackagePrefix() != null) {
                configureModuleChunk$strongWarning(compilerConfiguration, "The '-Xjava-package-prefix' option is ignored because '-Xbuild-file' is specified");
            }
            JvmArgumentsKt.configureContentRootsFromClassPath(compilerConfiguration, k2JVMCompilerArguments);
            final EnumSet<CompilerMessageSeverity> enumSet = CompilerMessageSeverity.VERBOSE;
            FilteringMessageCollector filteringMessageCollector = new FilteringMessageCollector(messageCollector, new Predicate() { // from class: i38
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return K2JVMCompilerKt.configureModuleChunk$contains(enumSet, (CompilerMessageSeverity) obj);
                }
            });
            compilerConfiguration.put(JVMConfigurationKeys.MODULE_XML_FILE, file);
            ModuleChunk moduleChunkLoadModuleChunk = CompileEnvironmentUtil.loadModuleChunk(file, filteringMessageCollector);
            moduleChunkLoadModuleChunk.getClass();
            return moduleChunkLoadModuleChunk;
        }
        if (file2 != null) {
            String path2 = file2.getPath();
            path2.getClass();
            if (StringsKt.endsWith$default(path2, ".jar", false, 2, (Object) null)) {
                compilerConfiguration.put(JVMConfigurationKeys.OUTPUT_JAR, file2);
            } else {
                compilerConfiguration.put(JVMConfigurationKeys.OUTPUT_DIRECTORY, file2);
            }
        }
        String str = (String) compilerConfiguration.get(CommonConfigurationKeys.MODULE_NAME);
        if (str == null) {
            str = "main";
        }
        if (file2 == null || (path = file2.getPath()) == null) {
            path = ".";
        }
        ModuleBuilder moduleBuilder = new ModuleBuilder(str, path, ModuleXmlParser.TYPE_PRODUCTION);
        CliCompilerUtilsKt.configureFromArgs(moduleBuilder, k2JVMCompilerArguments);
        return new ModuleChunk(CollectionsKt.listOf(moduleBuilder));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean configureModuleChunk$contains(EnumSet enumSet, CompilerMessageSeverity compilerMessageSeverity) {
        enumSet.getClass();
        return enumSet.contains(compilerMessageSeverity);
    }

    private static final void configureModuleChunk$strongWarning(CompilerConfiguration compilerConfiguration, String str) {
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), str, null, 4, null);
    }

    public static final void main(String[] strArr) {
        strArr.getClass();
        K2JVMCompiler.INSTANCE.main(strArr);
    }

    public static final String targetDescription(ModuleChunk moduleChunk) {
        moduleChunk.getClass();
        List<Module> modules = moduleChunk.getModules();
        modules.getClass();
        List<Module> list = modules;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Module module : list) {
            arrayList.add(module.getName() + '-' + module.getType());
        }
        String str = (String) CollectionsKt.singleOrNull(arrayList);
        return str == null ? CollectionsKt.joinToString$default(arrayList, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null) : str;
    }
}
