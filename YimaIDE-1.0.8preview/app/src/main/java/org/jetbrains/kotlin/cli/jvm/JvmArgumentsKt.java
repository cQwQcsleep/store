package org.jetbrains.kotlin.cli.jvm;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.jvm.JvmArgumentsKt;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmModulePathRoot;
import org.jetbrains.kotlin.cli.jvm.modules.CoreJrtFileSystem;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.JVMAssertionsMode;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.JvmAbiStability;
import org.jetbrains.kotlin.config.JvmClosureGenerationScheme;
import org.jetbrains.kotlin.config.JvmStringConcat;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.config.JvmWhenGenerationScheme;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleKt;
import org.jetbrains.kotlin.utils.KotlinPaths;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002\u001a,\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002\u001a\u0012\u0010\u0010\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0011\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0003\u001a\u00020\u0004\u001ag\u0010\u0014\u001a\u00020\u0001\"\b\b\u0000\u0010\u0017*\u00020\u0018*\u00020\u00022\b\u0010\u0015\u001a\u0004\u0018\u0001H\u00172\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u00020\u001b0\u001a2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u00020\u001b0\u001a2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u001e\u001a\n\u0010\u001f\u001a\u00020\u0006*\u00020\u0002\u001a$\u0010 \u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010\u001b\u001a\u0012\u0010#\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010$\u001a\u00020%*\u00020\u00022\u0006\u0010&\u001a\u00020\bH\u0002\u001a\u0012\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\b\u0010(\u001a\u00020%H\u0002¨\u0006)"}, d2 = {"setupJvmSpecificArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "isCompatibleJvmTargetAndRelease", Argument.Delimiters.none, "jvmTarget", Argument.Delimiters.none, "release", "handleClosureGenerationSchemeArgument", "flag", "value", "key", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "Lorg/jetbrains/kotlin/config/JvmClosureGenerationScheme;", "configureJdkHome", "configureJdkHomeFromSystemProperty", "configureJavaModulesContentRoots", "configureContentRootsFromClassPath", "configureStandardLibs", "paths", "Lorg/jetbrains/kotlin/utils/KotlinPaths;", "PathProvider", Argument.Delimiters.none, "stdlibPath", "Lkotlin/Function1;", "Ljava/io/File;", "scriptRuntimePath", "reflectPath", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;)V", "isModularJava", "addModularRootIfNotNull", "moduleName", "file", "configureAdvancedJvmOptions", "parseBackendThreads", Argument.Delimiters.none, "stringValue", "configureKlibPaths", "getJavaVersion", "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmArgumentsKt {
    public static CharSequence a(JvmClosureGenerationScheme jvmClosureGenerationScheme) {
        jvmClosureGenerationScheme.getClass();
        return jvmClosureGenerationScheme.getDescription();
    }

    public static final void addModularRootIfNotNull(CompilerConfiguration compilerConfiguration, boolean z, String str, File file) {
        compilerConfiguration.getClass();
        str.getClass();
        if (file != null) {
            if (!z) {
                compilerConfiguration.add(CLIConfigurationKeys.CONTENT_ROOTS, new JvmClasspathRoot(file));
            } else {
                compilerConfiguration.add(CLIConfigurationKeys.CONTENT_ROOTS, new JvmModulePathRoot(file));
                compilerConfiguration.add(JVMConfigurationKeys.ADDITIONAL_JAVA_MODULES, str);
            }
        }
    }

    public static CharSequence b(JvmTarget jvmTarget) {
        jvmTarget.getClass();
        return jvmTarget.getDescription();
    }

    public static CharSequence c(JvmWhenGenerationScheme jvmWhenGenerationScheme) {
        jvmWhenGenerationScheme.getClass();
        return jvmWhenGenerationScheme.getDescription();
    }

    public static final void configureAdvancedJvmOptions(CompilerConfiguration compilerConfiguration, K2JVMCompilerArguments k2JVMCompilerArguments) {
        compilerConfiguration.getClass();
        k2JVMCompilerArguments.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.PARAMETERS_METADATA, Boolean.valueOf(k2JVMCompilerArguments.getJavaParameters()));
        JvmAbiStability jvmAbiStabilityFromStringOrNull = JvmAbiStability.INSTANCE.fromStringOrNull(k2JVMCompilerArguments.getAbiStability());
        if (k2JVMCompilerArguments.getAbiStability() != null) {
            if (jvmAbiStabilityFromStringOrNull == null) {
                KtSourcelessDiagnosticFactory compiler_arguments_error = CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR();
                StringBuilder sb = new StringBuilder("Unknown ABI stability mode: ");
                sb.append(k2JVMCompilerArguments.getAbiStability());
                sb.append(", supported modes: ");
                EnumEntries<JvmAbiStability> entries = JvmAbiStability.getEntries();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
                Iterator it = entries.iterator();
                while (it.hasNext()) {
                    arrayList.add(((JvmAbiStability) it.next()).getDescription());
                }
                sb.append(arrayList);
                CliDiagnosticReportingKt.report$default(compilerConfiguration, compiler_arguments_error, sb.toString(), null, 4, null);
            } else {
                compilerConfiguration.put(JVMConfigurationKeys.ABI_STABILITY, jvmAbiStabilityFromStringOrNull);
            }
        }
        compilerConfiguration.put(JVMConfigurationKeys.DO_NOT_CLEAR_BINDING_CONTEXT, Boolean.valueOf(k2JVMCompilerArguments.getDoNotClearBindingContext()));
        compilerConfiguration.put(JVMConfigurationKeys.DISABLE_CALL_ASSERTIONS, Boolean.valueOf(k2JVMCompilerArguments.getNoCallAssertions()));
        compilerConfiguration.put(JVMConfigurationKeys.DISABLE_RECEIVER_ASSERTIONS, Boolean.valueOf(k2JVMCompilerArguments.getNoReceiverAssertions()));
        compilerConfiguration.put(JVMConfigurationKeys.DISABLE_PARAM_ASSERTIONS, Boolean.valueOf(k2JVMCompilerArguments.getNoParamAssertions()));
        compilerConfiguration.put(JVMConfigurationKeys.DISABLE_OPTIMIZATION, Boolean.valueOf(k2JVMCompilerArguments.getNoOptimize()));
        compilerConfiguration.put(JVMConfigurationKeys.EMIT_JVM_TYPE_ANNOTATIONS, Boolean.valueOf(k2JVMCompilerArguments.getEmitJvmTypeAnnotations()));
        compilerConfiguration.put(JVMConfigurationKeys.NO_RESET_JAR_TIMESTAMPS, Boolean.valueOf(k2JVMCompilerArguments.getNoResetJarTimestamps()));
        compilerConfiguration.put(JVMConfigurationKeys.NO_UNIFIED_NULL_CHECKS, Boolean.valueOf(k2JVMCompilerArguments.getNoUnifiedNullChecks()));
        compilerConfiguration.put(JVMConfigurationKeys.NO_SOURCE_DEBUG_EXTENSION, Boolean.valueOf(k2JVMCompilerArguments.getNoSourceDebugExtension()));
        compilerConfiguration.put(JVMConfigurationKeys.VALIDATE_BYTECODE, Boolean.valueOf(k2JVMCompilerArguments.getValidateBytecode()));
        compilerConfiguration.put(JVMConfigurationKeys.LINK_VIA_SIGNATURES, Boolean.valueOf(k2JVMCompilerArguments.getLinkViaSignatures()));
        compilerConfiguration.put(JVMConfigurationKeys.ENABLE_DEBUG_MODE, Boolean.valueOf(k2JVMCompilerArguments.getEnableDebugMode()));
        compilerConfiguration.put(JVMConfigurationKeys.ENHANCED_COROUTINES_DEBUGGING, Boolean.valueOf(k2JVMCompilerArguments.getEnhancedCoroutinesDebugging()));
        compilerConfiguration.put(JVMConfigurationKeys.NO_NEW_JAVA_ANNOTATION_TARGETS, Boolean.valueOf(k2JVMCompilerArguments.getNoNewJavaAnnotationTargets()));
        compilerConfiguration.put(JVMConfigurationKeys.USE_INLINE_SCOPES_NUMBERS, Boolean.valueOf(k2JVMCompilerArguments.getUseInlineScopesNumbers()));
        JVMAssertionsMode jVMAssertionsModeFromStringOrNull = JVMAssertionsMode.INSTANCE.fromStringOrNull(k2JVMCompilerArguments.getAssertionsMode());
        if (jVMAssertionsModeFromStringOrNull == null) {
            KtSourcelessDiagnosticFactory compiler_arguments_error2 = CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR();
            StringBuilder sb2 = new StringBuilder("Unknown assertions mode: ");
            sb2.append(k2JVMCompilerArguments.getAssertionsMode());
            sb2.append(", supported modes: ");
            EnumEntries<JVMAssertionsMode> entries2 = JVMAssertionsMode.getEntries();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries2, 10));
            Iterator it2 = entries2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((JVMAssertionsMode) it2.next()).getDescription());
            }
            sb2.append(arrayList2);
            CliDiagnosticReportingKt.report$default(compilerConfiguration, compiler_arguments_error2, sb2.toString(), null, 4, null);
        }
        CompilerConfigurationKey<JVMAssertionsMode> compilerConfigurationKey = JVMConfigurationKeys.ASSERTIONS_MODE;
        if (jVMAssertionsModeFromStringOrNull == null) {
            jVMAssertionsModeFromStringOrNull = JVMAssertionsMode.DEFAULT;
        }
        compilerConfiguration.put(compilerConfigurationKey, jVMAssertionsModeFromStringOrNull);
        compilerConfiguration.put(JVMConfigurationKeys.USE_TYPE_TABLE, Boolean.valueOf(k2JVMCompilerArguments.getUseTypeTable()));
        compilerConfiguration.put(JVMConfigurationKeys.USE_PSI_CLASS_FILES_READING, Boolean.valueOf(k2JVMCompilerArguments.getUseOldClassFilesReading()));
        Boolean useFastJarFileSystem = k2JVMCompilerArguments.getUseFastJarFileSystem();
        if (useFastJarFileSystem != null) {
            compilerConfiguration.put(JVMConfigurationKeys.USE_FAST_JAR_FILE_SYSTEM, useFastJarFileSystem);
        }
        if (k2JVMCompilerArguments.getUseOldClassFilesReading()) {
            CliDiagnosticReportingKt.reportInfo$default(compilerConfiguration, "Using the old java class files reading implementation", null, 2, null);
        }
        compilerConfiguration.put(CLIConfigurationKeys.ALLOW_KOTLIN_PACKAGE, Boolean.valueOf(k2JVMCompilerArguments.getAllowKotlinPackage()));
        compilerConfiguration.put(JVMConfigurationKeys.USE_OLD_INLINE_CLASSES_MANGLING_SCHEME, Boolean.valueOf(k2JVMCompilerArguments.getUseOldInlineClassesManglingScheme()));
        compilerConfiguration.put(JVMConfigurationKeys.ENABLE_JVM_PREVIEW, Boolean.valueOf(k2JVMCompilerArguments.getEnableJvmPreview()));
        if (k2JVMCompilerArguments.getEnableJvmPreview()) {
            CliDiagnosticReportingKt.reportInfo$default(compilerConfiguration, "Using preview Java language features", null, 2, null);
        }
        int backendThreads = parseBackendThreads(compilerConfiguration, k2JVMCompilerArguments.getBackendThreads());
        if (backendThreads == 0) {
            backendThreads = Runtime.getRuntime().availableProcessors();
        }
        if (backendThreads > 1) {
            CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, "Running backend in parallel with " + backendThreads + " threads", null, 2, null);
        }
        compilerConfiguration.put(CommonConfigurationKeys.PARALLEL_BACKEND_THREADS, Integer.valueOf(backendThreads));
    }

    public static final void configureContentRootsFromClassPath(CompilerConfiguration compilerConfiguration, K2JVMCompilerArguments k2JVMCompilerArguments) {
        compilerConfiguration.getClass();
        k2JVMCompilerArguments.getClass();
        String classpath = k2JVMCompilerArguments.getClasspath();
        List listSplit$default = classpath != null ? StringsKt.split$default(classpath, new char[]{File.pathSeparatorChar}, false, 0, 6, (Object) null) : null;
        if (listSplit$default == null) {
            listSplit$default = CollectionsKt.emptyList();
        }
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            compilerConfiguration.add(CLIConfigurationKeys.CONTENT_ROOTS, new JvmClasspathRoot(new File((String) it.next())));
        }
    }

    public static final void configureJavaModulesContentRoots(CompilerConfiguration compilerConfiguration, K2JVMCompilerArguments k2JVMCompilerArguments) {
        compilerConfiguration.getClass();
        k2JVMCompilerArguments.getClass();
        String javaModulePath = k2JVMCompilerArguments.getJavaModulePath();
        List listSplit$default = javaModulePath != null ? StringsKt.split$default(javaModulePath, new char[]{File.pathSeparatorChar}, false, 0, 6, (Object) null) : null;
        if (listSplit$default == null) {
            listSplit$default = CollectionsKt.emptyList();
        }
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            compilerConfiguration.add(CLIConfigurationKeys.CONTENT_ROOTS, new JvmModulePathRoot(new File((String) it.next())));
        }
    }

    public static final boolean configureJdkHome(CompilerConfiguration compilerConfiguration, K2JVMCompilerArguments k2JVMCompilerArguments) {
        compilerConfiguration.getClass();
        k2JVMCompilerArguments.getClass();
        if (k2JVMCompilerArguments.getNoJdk()) {
            compilerConfiguration.put(JVMConfigurationKeys.NO_JDK, Boolean.TRUE);
            if (k2JVMCompilerArguments.getJdkHome() != null) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), "The '-jdk-home' option is ignored because '-no-jdk' is specified", null, 4, null);
            }
            return true;
        }
        if (k2JVMCompilerArguments.getJdkHome() != null) {
            String jdkHome = k2JVMCompilerArguments.getJdkHome();
            jdkHome.getClass();
            File file = new File(jdkHome);
            if (!file.exists()) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "JDK home directory does not exist: " + file, null, 4, null);
                return false;
            }
            CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, "Using JDK home directory " + file, null, 2, null);
            compilerConfiguration.put(JVMConfigurationKeys.JDK_HOME, file);
        } else {
            configureJdkHomeFromSystemProperty(compilerConfiguration);
        }
        return true;
    }

    public static final void configureJdkHomeFromSystemProperty(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        File file = new File(System.getProperty("java.home"));
        CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, "Using JDK home inferred from java.home: " + file, null, 2, null);
        compilerConfiguration.put(JVMConfigurationKeys.JDK_HOME, file);
    }

    public static final void configureKlibPaths(CompilerConfiguration compilerConfiguration, K2JVMCompilerArguments k2JVMCompilerArguments) {
        compilerConfiguration.getClass();
        k2JVMCompilerArguments.getClass();
        String klibLibraries = k2JVMCompilerArguments.getKlibLibraries();
        if (klibLibraries == null) {
            return;
        }
        CompilerConfigurationKey<List<String>> compilerConfigurationKey = JVMConfigurationKeys.KLIB_PATHS;
        String str = File.pathSeparator;
        str.getClass();
        List listSplit = new Regex(str).split(klibLibraries, 0);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit) {
            if (((String) obj).length() != 0) {
                arrayList.add(obj);
            }
        }
        compilerConfiguration.put(compilerConfigurationKey, arrayList);
    }

    public static final <PathProvider> void configureStandardLibs(CompilerConfiguration compilerConfiguration, PathProvider pathprovider, Function1<? super PathProvider, ? extends File> function1, Function1<? super PathProvider, ? extends File> function2, Function1<? super PathProvider, ? extends File> function3, K2JVMCompilerArguments k2JVMCompilerArguments) {
        CompilerConfiguration compilerConfiguration2;
        PathProvider pathprovider2;
        compilerConfiguration.getClass();
        function1.getClass();
        function2.getClass();
        function3.getClass();
        k2JVMCompilerArguments.getClass();
        Integer num = (Integer) compilerConfiguration.get(JVMConfigurationKeys.JDK_RELEASE);
        boolean z = isModularJava(compilerConfiguration) && (num == null || num.intValue() >= 9);
        if (k2JVMCompilerArguments.getNoStdlib()) {
            compilerConfiguration2 = compilerConfiguration;
            pathprovider2 = pathprovider;
        } else {
            compilerConfiguration2 = compilerConfiguration;
            pathprovider2 = pathprovider;
            configureStandardLibs$addRoot(compilerConfiguration2, z, pathprovider2, JavaModuleKt.KOTLIN_STDLIB_MODULE_NAME, "kotlin-stdlib.jar", function1, "'-no-stdlib'");
            configureStandardLibs$addRoot(compilerConfiguration2, z, pathprovider2, "kotlin.script.runtime", "kotlin-script-runtime.jar", function2, "'-no-stdlib'");
        }
        if (k2JVMCompilerArguments.getNoReflect() || k2JVMCompilerArguments.getNoStdlib()) {
            return;
        }
        configureStandardLibs$addRoot(compilerConfiguration2, z, pathprovider2, "kotlin.reflect", "kotlin-reflect.jar", function3, "'-no-reflect' or '-no-stdlib'");
    }

    private static final <PathProvider> void configureStandardLibs$addRoot(CompilerConfiguration compilerConfiguration, boolean z, PathProvider pathprovider, String str, String str2, Function1<? super PathProvider, ? extends File> function1, String str3) {
        addModularRootIfNotNull(compilerConfiguration, z, str, UtilsKt.getLibraryFromHome(pathprovider, function1, str2, compilerConfiguration, str3));
    }

    public static CharSequence d(JvmStringConcat jvmStringConcat) {
        jvmStringConcat.getClass();
        return jvmStringConcat.getDescription();
    }

    private static final int getJavaVersion() {
        String strSubstringAfter$default;
        Integer intOrNull;
        String property = System.getProperty("java.specification.version");
        if (property == null || (strSubstringAfter$default = StringsKt.substringAfter$default(property, '.', (String) null, 2, (Object) null)) == null || (intOrNull = StringsKt.toIntOrNull(strSubstringAfter$default)) == null) {
            return 6;
        }
        return intOrNull.intValue();
    }

    private static final void handleClosureGenerationSchemeArgument(CompilerConfiguration compilerConfiguration, String str, String str2, CompilerConfigurationKey<? extends JvmClosureGenerationScheme> compilerConfigurationKey) {
        if (str2 == null) {
            return;
        }
        JvmClosureGenerationScheme jvmClosureGenerationSchemeFromString = JvmClosureGenerationScheme.INSTANCE.fromString(str2);
        if (jvmClosureGenerationSchemeFromString != null) {
            compilerConfiguration.put(compilerConfigurationKey, jvmClosureGenerationSchemeFromString);
            return;
        }
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Unknown `" + str + "` argument: " + str2 + "\n.Supported arguments: " + CollectionsKt.joinToString$default(JvmClosureGenerationScheme.getEntries(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: av7
            public final Object invoke(Object obj) {
                return JvmArgumentsKt.a((JvmClosureGenerationScheme) obj);
            }
        }, 31, (Object) null), null, 4, null);
    }

    private static final boolean isCompatibleJvmTargetAndRelease(String str, String str2) {
        return Intrinsics.areEqual(str, "1.8") ? CollectionsKt.listOf(new String[]{"6", "1.6", "7", "1.7", "8", "1.8"}).contains(str2) : Intrinsics.areEqual(str, str2);
    }

    public static final boolean isModularJava(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        File file = (File) compilerConfiguration.get(JVMConfigurationKeys.JDK_HOME);
        if (file != null) {
            return CoreJrtFileSystem.INSTANCE.isModularJdk(file);
        }
        return false;
    }

    private static final int parseBackendThreads(CompilerConfiguration compilerConfiguration, String str) {
        Integer intOrNull = StringsKt.toIntOrNull(str);
        if (intOrNull != null) {
            if (intOrNull.intValue() >= 0) {
                return intOrNull.intValue();
            }
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "-Xbackend-threads value cannot be negative", null, 4, null);
            return 1;
        }
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Cannot parse -Xbackend-threads value: \"" + str + "\". Please use an integer number", null, 4, null);
        return 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:21:0x0070  */
    /* JADX WARN: Code duplicated, block: B:61:0x0125 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x0127  */
    /* JADX WARN: Code duplicated, block: B:63:0x0147  */
    /* JADX WARN: Instruction removed from duplicated block: B:62:0x0127, please report this as an issue */
    public static final void setupJvmSpecificArguments(CompilerConfiguration compilerConfiguration, K2JVMCompilerArguments k2JVMCompilerArguments) {
        CompilerConfiguration compilerConfiguration2;
        CompilerConfiguration compilerConfiguration3;
        Integer intOrNull;
        compilerConfiguration.getClass();
        k2JVMCompilerArguments.getClass();
        compilerConfiguration.put(JVMConfigurationKeys.INCLUDE_RUNTIME, Boolean.valueOf(k2JVMCompilerArguments.getIncludeRuntime()));
        compilerConfiguration.put(JVMConfigurationKeys.NO_REFLECT, Boolean.valueOf(k2JVMCompilerArguments.getNoReflect()));
        CompilerConfigurationKey<List<String>> compilerConfigurationKey = JVMConfigurationKeys.FRIEND_PATHS;
        String[] friendPaths = k2JVMCompilerArguments.getFriendPaths();
        compilerConfiguration.putIfNotNull(compilerConfigurationKey, friendPaths != null ? ArraysKt.asList(friendPaths) : null);
        String jdkRelease = k2JVMCompilerArguments.getJdkRelease();
        String jvmTarget = k2JVMCompilerArguments.getJvmTarget();
        if (jdkRelease != null) {
            switch (jdkRelease) {
                case "1.6":
                    intOrNull = 6;
                    break;
                case "1.7":
                    intOrNull = 7;
                    break;
                case "1.8":
                    intOrNull = 8;
                    break;
                default:
                    intOrNull = StringsKt.toIntOrNull(jdkRelease);
                    break;
            }
            if (intOrNull == null || intOrNull.intValue() < 6) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Unknown JDK release version: ".concat(jdkRelease), null, 4, null);
            } else {
                if (intOrNull.intValue() != getJavaVersion() || k2JVMCompilerArguments.getJdkHome() != null) {
                    compilerConfiguration.put(JVMConfigurationKeys.JDK_RELEASE, intOrNull);
                }
                if (jvmTarget != null && !isCompatibleJvmTargetAndRelease(jvmTarget, jdkRelease)) {
                    String str = intOrNull.intValue() < 8 ? "Please change the value of the 'jvm-target' option to 1.8" : "Please remove the '-jvm-target' option";
                    CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "'-Xjdk-release=" + jdkRelease + "' option conflicts with '-jvm-target " + jvmTarget + "'. " + str, null, 4, null);
                }
            }
        }
        if (jdkRelease != null) {
            switch (jdkRelease) {
                case "6":
                case "7":
                    if (jvmTarget == null) {
                        compilerConfiguration2 = compilerConfiguration;
                        CliDiagnosticReportingKt.report$default(compilerConfiguration2, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "'-Xjdk-release=" + jdkRelease + "' option requires JVM target explicitly set to 1.8. Please specify the '-jvm-target' option", null, 4, null);
                    } else {
                        compilerConfiguration2 = compilerConfiguration;
                    }
                case "8":
                    compilerConfiguration2 = compilerConfiguration;
                    jdkRelease = "1.8";
                    break;
                case "1.6":
                case "1.7":
                    if (jvmTarget == null) {
                        compilerConfiguration2 = compilerConfiguration;
                        CliDiagnosticReportingKt.report$default(compilerConfiguration2, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "'-Xjdk-release=" + jdkRelease + "' option requires JVM target explicitly set to 1.8. Please specify the '-jvm-target' option", null, 4, null);
                    } else {
                        compilerConfiguration2 = compilerConfiguration;
                    }
                default:
                    compilerConfiguration2 = compilerConfiguration;
                    break;
            }
        } else {
            compilerConfiguration2 = compilerConfiguration;
        }
        if (jdkRelease != null) {
            JvmTarget.Companion companion = JvmTarget.INSTANCE;
            JvmTarget jvmTargetFromString = companion.fromString(jdkRelease);
            if (jvmTargetFromString != null) {
                compilerConfiguration2.put(JVMConfigurationKeys.JVM_TARGET, jvmTargetFromString);
                if (jvmTargetFromString == JvmTarget.JVM_1_6) {
                    CliDiagnosticReportingKt.report$default(compilerConfiguration2, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "JVM target 1.6 is no longer supported. Please migrate to JVM target 1.8 or above", null, 4, null);
                }
                compilerConfiguration2 = compilerConfiguration;
            } else {
                compilerConfiguration2 = compilerConfiguration;
                CliDiagnosticReportingKt.report$default(compilerConfiguration2, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Unknown JVM target version: " + jdkRelease + "\nSupported versions: " + CollectionsKt.joinToString$default(companion.supportedValues(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: bv7
                    public final Object invoke(Object obj) {
                        return JvmArgumentsKt.b((JvmTarget) obj);
                    }
                }, 31, (Object) null), null, 4, null);
            }
        }
        JvmTarget jvmTarget2 = (JvmTarget) compilerConfiguration2.get(JVMConfigurationKeys.JVM_TARGET);
        if (jvmTarget2 == null) {
            jvmTarget2 = JvmTarget.DEFAULT;
        }
        JvmTarget jvmTarget3 = jvmTarget2;
        String stringConcat = k2JVMCompilerArguments.getStringConcat();
        if (stringConcat != null) {
            JvmStringConcat jvmStringConcatFromString = JvmStringConcat.INSTANCE.fromString(stringConcat);
            if (jvmStringConcatFromString != null) {
                compilerConfiguration2.put(JVMConfigurationKeys.STRING_CONCAT, jvmStringConcatFromString);
                if (jvmTarget3.getMajorVersion() < JvmTarget.JVM_9.getMajorVersion() && jvmStringConcatFromString != JvmStringConcat.INLINE) {
                    CliDiagnosticReportingKt.report$default(compilerConfiguration2, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), "`-Xstring-concat=" + stringConcat + "` does nothing with JVM target `" + jvmTarget3.getDescription() + "`.", null, 4, null);
                }
                compilerConfiguration2 = compilerConfiguration;
            } else {
                compilerConfiguration2 = compilerConfiguration;
                CliDiagnosticReportingKt.report$default(compilerConfiguration2, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Unknown `-Xstring-concat` mode: " + stringConcat + "\nSupported modes: " + CollectionsKt.joinToString$default(JvmStringConcat.getEntries(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: cv7
                    public final Object invoke(Object obj) {
                        return JvmArgumentsKt.d((JvmStringConcat) obj);
                    }
                }, 31, (Object) null), null, 4, null);
            }
        }
        String whenExpressionsGeneration = k2JVMCompilerArguments.getWhenExpressionsGeneration();
        if (whenExpressionsGeneration != null) {
            JvmWhenGenerationScheme jvmWhenGenerationSchemeFromString = JvmWhenGenerationScheme.INSTANCE.fromString(whenExpressionsGeneration);
            if (jvmWhenGenerationSchemeFromString != null) {
                compilerConfiguration2.put(JVMConfigurationKeys.WHEN_GENERATION_SCHEME, jvmWhenGenerationSchemeFromString);
                if (jvmTarget3.getMajorVersion() < JvmTarget.JVM_21.getMajorVersion() && jvmWhenGenerationSchemeFromString != JvmWhenGenerationScheme.INLINE) {
                    CliDiagnosticReportingKt.report$default(compilerConfiguration2, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), "`-Xwhen-expressions=" + whenExpressionsGeneration + "` does nothing with JVM target `" + jvmTarget3.getDescription() + "`.", null, 4, null);
                }
            } else {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Unknown `-Xwhen-expressions` mode: " + whenExpressionsGeneration + "\nSupported modes: " + CollectionsKt.joinToString$default(JvmWhenGenerationScheme.getEntries(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: dv7
                    public final Object invoke(Object obj) {
                        return JvmArgumentsKt.c((JvmWhenGenerationScheme) obj);
                    }
                }, 31, (Object) null), null, 4, null);
            }
        }
        if (k2JVMCompilerArguments.getValueClasses()) {
            compilerConfiguration3 = compilerConfiguration;
            CliDiagnosticReportingKt.report$default(compilerConfiguration3, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "This flag is deprecated, use -XXLanguage:+JvmInlineMultiFieldValueClasses instead", null, 4, null);
        } else {
            compilerConfiguration3 = compilerConfiguration;
        }
        handleClosureGenerationSchemeArgument(compilerConfiguration3, "-Xsam-conversions", k2JVMCompilerArguments.getSamConversions(), JVMConfigurationKeys.SAM_CONVERSIONS);
        handleClosureGenerationSchemeArgument(compilerConfiguration3, "-Xlambdas", k2JVMCompilerArguments.getLambdas(), JVMConfigurationKeys.LAMBDAS);
        CompilerConfigurationKey<List<String>> compilerConfigurationKey2 = JVMConfigurationKeys.ADDITIONAL_JAVA_MODULES;
        String[] additionalJavaModules = k2JVMCompilerArguments.getAdditionalJavaModules();
        compilerConfiguration3.addAll(compilerConfigurationKey2, additionalJavaModules != null ? ArraysKt.asList(additionalJavaModules) : null);
        CompilerConfigurationKey<List<String>> compilerConfigurationKey3 = JVMConfigurationKeys.IGNORED_ANNOTATIONS_FOR_BRIDGES;
        String[] ignoredAnnotationsForBridges = k2JVMCompilerArguments.getIgnoredAnnotationsForBridges();
        List list = ignoredAnnotationsForBridges != null ? ArraysKt.toList(ignoredAnnotationsForBridges) : null;
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        compilerConfiguration3.put(compilerConfigurationKey3, list);
    }

    public static final void configureStandardLibs(CompilerConfiguration compilerConfiguration, KotlinPaths kotlinPaths, K2JVMCompilerArguments k2JVMCompilerArguments) {
        compilerConfiguration.getClass();
        k2JVMCompilerArguments.getClass();
        configureStandardLibs(compilerConfiguration, kotlinPaths, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.jvm.JvmArgumentsKt.configureStandardLibs.1
            public Object get(Object obj) {
                return ((KotlinPaths) obj).getStdlibPath();
            }
        }, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.jvm.JvmArgumentsKt.configureStandardLibs.2
            public Object get(Object obj) {
                return ((KotlinPaths) obj).getScriptRuntimePath();
            }
        }, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.jvm.JvmArgumentsKt.configureStandardLibs.3
            public Object get(Object obj) {
                return ((KotlinPaths) obj).getReflectPath();
            }
        }, k2JVMCompilerArguments);
    }
}
