package org.jetbrains.kotlin.cli.common;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.analyzer.CompilationErrorException;
import org.jetbrains.kotlin.cli.CompilerConfigurationCreationKt;
import org.jetbrains.kotlin.cli.common.CLICompiler;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.CommonToolArguments;
import org.jetbrains.kotlin.cli.common.arguments.ParseCommandLineArgumentsKt;
import org.jetbrains.kotlin.cli.common.environment.UtilKt;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.FilteringMessageCollector;
import org.jetbrains.kotlin.cli.common.messages.GroupingMessageCollector;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.messages.MessageCollectorUtil;
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer;
import org.jetbrains.kotlin.cli.common.messages.PlainTextMessageRenderer;
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector;
import org.jetbrains.kotlin.cli.jvm.compiler.CompatKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CompileEnvironmentException;
import org.jetbrains.kotlin.cli.jvm.plugins.PluginCliParser;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.plugins.PluginClasspathAndOptions;
import org.jetbrains.kotlin.cli.plugins.PluginsOptionsParserKt;
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor;
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar;
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.KotlinCompilerVersion;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.progress.CompilationCanceledException;
import org.jetbrains.kotlin.progress.CompilationCanceledStatus;
import org.jetbrains.kotlin.progress.ProgressIndicatorAndCompilationCanceledStatus;
import org.jetbrains.kotlin.util.CompilerType;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PerformanceManagerImpl;
import org.jetbrains.kotlin.util.UnitStatsKt;
import org.jetbrains.kotlin.utils.KotlinPaths;
import org.jetbrains.kotlin.utils.PathUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 W*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001WB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u0018H\u0014¢\u0006\u0002\u0010\u0019J/\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020 0\u001f\"\u00020 ¢\u0006\u0002\u0010!J!\u0010\"\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f¢\u0006\u0002\u0010#J\u0015\u0010$\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010%J%\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010)J\u001d\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0016\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H$J%\u00103\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u0018H$¢\u0006\u0002\u00104J'\u00105\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00106\u001a\u00020(H\u0014¢\u0006\u0002\u00107J/\u00108\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00028\u00002\u0006\u0010,\u001a\u00020-2\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<H$¢\u0006\u0002\u0010=J\u001f\u0010>\u001a\u00020+*\b\u0012\u0004\u0012\u00020 0?2\u0006\u0010\u0016\u001a\u00028\u0000H$¢\u0006\u0002\u0010@J/\u0010A\u001a\u00020\u001b2\b\u0010;\u001a\u0004\u0018\u00010<2\u0006\u0010\u0016\u001a\u00028\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010B\u001a\u00020:H\u0004¢\u0006\u0002\u0010CJ&\u0010D\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020 0F2\u0006\u0010G\u001a\u00020\u0011H\u0002J\u001e\u0010H\u001a\u00020+2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020 0F2\u0006\u0010,\u001a\u00020-H\u0002J'\u0010I\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020 0\u001f\"\u00020 ¢\u0006\u0002\u0010#J/\u0010I\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010J\u001a\u00020K2\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020 0\u001f\"\u00020 ¢\u0006\u0002\u0010LJ5\u0010I\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010J\u001a\u00020K2\u000e\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020 0\u001fH\u0004¢\u0006\u0002\u0010MJ#\u0010I\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00028\u0000¢\u0006\u0002\u0010)J\b\u0010N\u001a\u00020+H\u0002J\r\u0010O\u001a\u00028\u0000H&¢\u0006\u0002\u0010PJ#\u0010Q\u001a\u00020+2\u000e\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020 0\u001f2\u0006\u0010\u0016\u001a\u00028\u0000¢\u0006\u0002\u0010RJ'\u0010S\u001a\u00020+\"\b\b\u0001\u0010\u0001*\u00020T2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u0016\u001a\u0002H\u0001H\u0002¢\u0006\u0002\u0010UJ\b\u0010V\u001a\u00020 H&R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006X"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/CLICompiler;", "A", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", Argument.Delimiters.none, "<init>", "()V", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "defaultPerformanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "getDefaultPerformanceManager", "()Lorg/jetbrains/kotlin/util/PerformanceManager;", "defaultPerformanceManager$delegate", "Lkotlin/Lazy;", "isReadingSettingsFromEnvironmentAllowed", Argument.Delimiters.none, "()Z", "setReadingSettingsFromEnvironmentAllowed", "(Z)V", "createPerformanceManager", "arguments", "services", "Lorg/jetbrains/kotlin/config/Services;", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lorg/jetbrains/kotlin/config/Services;)Lorg/jetbrains/kotlin/util/PerformanceManager;", "execAndOutputXml", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "errStream", "Ljava/io/PrintStream;", "args", Argument.Delimiters.none, Argument.Delimiters.none, "(Ljava/io/PrintStream;Lorg/jetbrains/kotlin/config/Services;[Ljava/lang/String;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "execFullPathsInMessages", "(Ljava/io/PrintStream;[Ljava/lang/String;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "shouldRunK2", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;)Z", "execImpl", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "(Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;Lorg/jetbrains/kotlin/config/Services;Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "setupCommonArguments", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;)V", "createMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "versionArray", Argument.Delimiters.none, "setupPlatformSpecificArgumentsAndServices", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lorg/jetbrains/kotlin/config/Services;)V", "doExecutePhased", "basicMessageCollector", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lorg/jetbrains/kotlin/config/Services;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "doExecute", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "paths", "Lorg/jetbrains/kotlin/utils/KotlinPaths;", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lcom/intellij/openapi/Disposable;Lorg/jetbrains/kotlin/utils/KotlinPaths;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "addPlatformOptions", Argument.Delimiters.none, "(Ljava/util/List;Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;)V", "loadPlugins", "parentDisposable", "(Lorg/jetbrains/kotlin/utils/KotlinPaths;Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lcom/intellij/openapi/Disposable;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "tryLoadScriptingPluginFromCurrentClassLoader", "pluginOptions", Argument.Delimiters.none, "useK2", "processScriptPluginCliOptions", "exec", "messageRenderer", "Lorg/jetbrains/kotlin/cli/common/messages/MessageRenderer;", "(Ljava/io/PrintStream;Lorg/jetbrains/kotlin/cli/common/messages/MessageRenderer;[Ljava/lang/String;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "(Ljava/io/PrintStream;Lorg/jetbrains/kotlin/config/Services;Lorg/jetbrains/kotlin/cli/common/messages/MessageRenderer;[Ljava/lang/String;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "disableURLConnectionCaches", "createArguments", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "parseArguments", "([Ljava/lang/String;Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;)V", "printVersionIfNeeded", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "(Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;)V", "executableScriptFileName", "Companion", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CLICompiler<A extends CommonCompilerArguments> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String SCRIPT_PLUGIN_COMMANDLINE_PROCESSOR_NAME = "org.jetbrains.kotlin.scripting.compiler.plugin.ScriptingCommandLineProcessor";
    public static final String SCRIPT_PLUGIN_K2_REGISTRAR_NAME = "org.jetbrains.kotlin.scripting.compiler.plugin.ScriptingK2CompilerPluginRegistrar";
    public static final String SCRIPT_PLUGIN_REGISTRAR_NAME = "org.jetbrains.kotlin.scripting.compiler.plugin.ScriptingCompilerConfigurationComponentRegistrar";

    /* JADX INFO: renamed from: defaultPerformanceManager$delegate, reason: from kotlin metadata */
    private final Lazy defaultPerformanceManager = LazyKt.lazy(new Function0() { // from class: q71
        public final Object invoke() {
            return CLICompiler.a(this.b);
        }
    });
    private boolean isReadingSettingsFromEnvironmentAllowed;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.cli.common.CLICompiler$setupCommonArguments$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00031 extends FunctionReferenceImpl implements Function1<int[], BinaryVersion> {
        public C00031(Object obj) {
            super(1, obj, CLICompiler.class, "createMetadataVersion", "createMetadataVersion([I)Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", 0);
        }

        public final BinaryVersion invoke(int[] iArr) {
            iArr.getClass();
            return ((CLICompiler) ((CallableReference) this).receiver).createMetadataVersion(iArr);
        }
    }

    public CLICompiler() {
        this.isReadingSettingsFromEnvironmentAllowed = getClass().getClassLoader().getResource("META-INF/allow-configuring-from-environment") != null;
    }

    public static PerformanceManagerImpl a(CLICompiler cLICompiler) {
        return UtilsKt.createPerformanceManagerFor(cLICompiler.getPlatform());
    }

    public static Unit b(GroupingMessageCollector groupingMessageCollector, String str) {
        str.getClass();
        groupingMessageCollector.report(CompilerMessageSeverity.LOGGING, "PERF: ".concat(str), null);
        return Unit.INSTANCE;
    }

    private final void disableURLConnectionCaches() {
        new URLConnection(new URL("file:.")) { // from class: org.jetbrains.kotlin.cli.common.CLICompiler.disableURLConnectionCaches.1
            @Override // java.net.URLConnection
            public Void connect() {
                throw new UnsupportedOperationException();
            }
        }.setDefaultUseCaches(false);
    }

    @JvmStatic
    public static final void doMain(CLICompiler<?> cLICompiler, String[] strArr) {
        INSTANCE.doMain(cLICompiler, strArr);
    }

    @JvmStatic
    public static final ExitCode doMainNoExit(CLICompiler<?> cLICompiler, String[] strArr) {
        return INSTANCE.doMainNoExit(cLICompiler, strArr);
    }

    private final ExitCode execImpl(MessageCollector messageCollector, Services services, A arguments) {
        ExitCode exitCodeDoExecutePhased;
        boolean zShouldRunK2 = shouldRunK2(arguments);
        if (zShouldRunK2 && (exitCodeDoExecutePhased = doExecutePhased(arguments, services, messageCollector)) != null) {
            return exitCodeDoExecutePhased;
        }
        PerformanceManager performanceManagerCreatePerformanceManager = createPerformanceManager(arguments, services);
        performanceManagerCreatePerformanceManager.setCompilerType(zShouldRunK2 ? CompilerType.K2 : CompilerType.K1);
        if (arguments.getReportPerf() || arguments.getDumpPerf() != null) {
            performanceManagerCreatePerformanceManager.enableExtendedStats();
        }
        CompilerConfiguration compilerConfigurationCreate$default = CompilerConfigurationCreationKt.create$default(CompilerConfiguration.INSTANCE, null, null, 3, null);
        compilerConfigurationCreate$default.put(CLIConfigurationKeys.ORIGINAL_MESSAGE_COLLECTOR_KEY, messageCollector);
        CLIConfigurationKeysKt.setTreatWarningsAsErrors(compilerConfigurationCreate$default, arguments.getAllWarningsAsErrors());
        final GroupingMessageCollector groupingMessageCollector = new GroupingMessageCollector(messageCollector, arguments.getAllWarningsAsErrors(), arguments.getReportAllWarnings());
        CommonConfigurationKeysKt.setMessageCollector(compilerConfigurationCreate$default, groupingMessageCollector);
        CommonConfigurationKeysKt.setPerfManager(compilerConfigurationCreate$default, performanceManagerCreatePerformanceManager);
        try {
            try {
                setupCommonArguments(compilerConfigurationCreate$default, arguments);
                setupPlatformSpecificArgumentsAndServices(compilerConfigurationCreate$default, arguments, services);
                KotlinPaths kotlinPathsComputeKotlinPaths = ArgumentsKt.computeKotlinPaths(compilerConfigurationCreate$default, arguments);
                CheckCompilationErrors.CheckDiagnosticCollector checkDiagnosticCollector = CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE;
                if (checkDiagnosticCollector.checkHasErrorsAndReportToMessageCollector(compilerConfigurationCreate$default)) {
                    ExitCode exitCode = ExitCode.COMPILATION_ERROR;
                    groupingMessageCollector.flush();
                    return exitCode;
                }
                ProgressIndicatorAndCompilationCanceledStatus.setCompilationCanceledStatus((CompilationCanceledStatus) services.get(CompilationCanceledStatus.class));
                Disposable disposableNewDisposable = Disposer.newDisposable("Disposable for " + Reflection.getOrCreateKotlinClass(CLICompiler.class).getSimpleName() + ".execImpl");
                disposableNewDisposable.getClass();
                try {
                    try {
                        UtilKt.setIdeaIoUseFallback();
                        ExitCode exitCodeDoExecute = doExecute(arguments, compilerConfigurationCreate$default, disposableNewDisposable, kotlinPathsComputeKotlinPaths);
                        performanceManagerCreatePerformanceManager.notifyCompilationFinished();
                        if (arguments.getReportPerf()) {
                            MessageCollector.report$default(groupingMessageCollector, CompilerMessageSeverity.LOGGING, "PERF: " + performanceManagerCreatePerformanceManager.getTargetInfo(), null, 4, null);
                            UnitStatsKt.forEachStringMeasurement(performanceManagerCreatePerformanceManager, new Function1() { // from class: p71
                                public final Object invoke(Object obj) {
                                    return CLICompiler.b(groupingMessageCollector, (String) obj);
                                }
                            });
                        }
                        if (arguments.getDumpPerf() != null) {
                            String dumpPerf = arguments.getDumpPerf();
                            dumpPerf.getClass();
                            performanceManagerCreatePerformanceManager.dumpPerformanceReport(dumpPerf);
                        }
                        if (checkDiagnosticCollector.checkHasErrorsAndReportToMessageCollector(compilerConfigurationCreate$default)) {
                            exitCodeDoExecute = ExitCode.COMPILATION_ERROR;
                        }
                        UtilsKt.disposeRootInWriteAction(disposableNewDisposable);
                        groupingMessageCollector.flush();
                        return exitCodeDoExecute;
                    } catch (RuntimeException e) {
                        Object cause = e.getCause();
                        if (!(cause instanceof CompilationCanceledException)) {
                            throw e;
                        }
                        CLICompilerKt.reportCompilationCancelled(groupingMessageCollector, (CompilationCanceledException) cause);
                        ExitCode exitCode2 = ExitCode.OK;
                        UtilsKt.disposeRootInWriteAction(disposableNewDisposable);
                        groupingMessageCollector.flush();
                        return exitCode2;
                    } catch (CompilationCanceledException e2) {
                        CLICompilerKt.reportCompilationCancelled(groupingMessageCollector, e2);
                        ExitCode exitCode3 = ExitCode.OK;
                        UtilsKt.disposeRootInWriteAction(disposableNewDisposable);
                        groupingMessageCollector.flush();
                        return exitCode3;
                    }
                } catch (Throwable th) {
                    UtilsKt.disposeRootInWriteAction(disposableNewDisposable);
                    throw th;
                }
            } catch (Throwable th2) {
                groupingMessageCollector.flush();
                throw th2;
            }
        } catch (CompilationErrorException unused) {
            ExitCode exitCode4 = ExitCode.COMPILATION_ERROR;
            groupingMessageCollector.flush();
            return exitCode4;
        } catch (Throwable th3) {
            MessageCollectorUtil.reportException(groupingMessageCollector, th3);
            ExitCode exitCode5 = ((th3 instanceof OutOfMemoryError) || CLICompilerKt.hasOOMCause(th3)) ? ExitCode.OOM_ERROR : ExitCode.INTERNAL_ERROR;
            groupingMessageCollector.flush();
            return exitCode5;
        }
    }

    private final <A extends CommonToolArguments> void printVersionIfNeeded(MessageCollector messageCollector, A arguments) {
        if (arguments.getVersion()) {
            String property = System.getProperty("java.runtime.version");
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.INFO, executableScriptFileName() + ' ' + KotlinCompilerVersion.VERSION + " (JRE " + property + ')', null, 4, null);
        }
    }

    private final void processScriptPluginCliOptions(List<String> pluginOptions, CompilerConfiguration configuration) throws ClassNotFoundException {
        Class<?> clsLoadClass;
        Constructor<?> declaredConstructor;
        if (pluginOptions.isEmpty()) {
            clsLoadClass = null;
        } else {
            clsLoadClass = PluginCliParser.class.getClassLoader().loadClass(SCRIPT_PLUGIN_COMMANDLINE_PROCESSOR_NAME);
            clsLoadClass.getClass();
        }
        Object objNewInstance = (clsLoadClass == null || (declaredConstructor = clsLoadClass.getDeclaredConstructor(null)) == null) ? null : declaredConstructor.newInstance(null);
        CommandLineProcessor commandLineProcessor = objNewInstance instanceof CommandLineProcessor ? (CommandLineProcessor) objNewInstance : null;
        if (commandLineProcessor != null) {
            PluginsOptionsParserKt.processCompilerPluginsOptions(configuration, pluginOptions, CollectionsKt.listOf(commandLineProcessor));
        }
    }

    private final void setupCommonArguments(CompilerConfiguration configuration, A arguments) {
        ArgumentsKt.setupCommonArguments(configuration, arguments, new C00031(this));
    }

    private final boolean shouldRunK2(A arguments) {
        LanguageVersion languageVersionFromVersionString;
        String languageVersion = arguments.getLanguageVersion();
        if (languageVersion == null || (languageVersionFromVersionString = LanguageVersion.INSTANCE.fromVersionString(languageVersion)) == null) {
            languageVersionFromVersionString = LanguageVersion.LATEST_STABLE;
        }
        return languageVersionFromVersionString.getUsesK2();
    }

    private final boolean tryLoadScriptingPluginFromCurrentClassLoader(CompilerConfiguration configuration, List<String> pluginOptions, boolean useK2) {
        try {
            CompilerPluginRegistrar compilerPluginRegistrar = null;
            Object objNewInstance = PluginCliParser.class.getClassLoader().loadClass(SCRIPT_PLUGIN_REGISTRAR_NAME).getDeclaredConstructor(null).newInstance(null);
            ComponentRegistrar componentRegistrar = objNewInstance instanceof ComponentRegistrar ? (ComponentRegistrar) objNewInstance : null;
            if (componentRegistrar != null) {
                configuration.add(ComponentRegistrar.INSTANCE.getPLUGIN_COMPONENT_REGISTRARS(), componentRegistrar);
            } else {
                componentRegistrar = null;
            }
            if (useK2) {
                Object objNewInstance2 = PluginCliParser.class.getClassLoader().loadClass(SCRIPT_PLUGIN_K2_REGISTRAR_NAME).getDeclaredConstructor(null).newInstance(null);
                CompilerPluginRegistrar compilerPluginRegistrar2 = objNewInstance2 instanceof CompilerPluginRegistrar ? (CompilerPluginRegistrar) objNewInstance2 : null;
                if (compilerPluginRegistrar2 != null) {
                    configuration.add(CompilerPluginRegistrar.INSTANCE.getCOMPILER_PLUGIN_REGISTRARS(), compilerPluginRegistrar2);
                    compilerPluginRegistrar = compilerPluginRegistrar2;
                }
            }
            if (componentRegistrar == null && compilerPluginRegistrar == null) {
                return false;
            }
            processScriptPluginCliOptions(pluginOptions, configuration);
            return true;
        } catch (Throwable th) {
            MessageCollector.report$default((MessageCollector) configuration.getNotNull(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY), CompilerMessageSeverity.LOGGING, "Exception on loading scripting plugin: " + th, null, 4, null);
            return false;
        }
    }

    public abstract void addPlatformOptions(List<String> list, A a);

    public abstract A createArguments();

    public abstract BinaryVersion createMetadataVersion(int[] versionArray);

    public PerformanceManager createPerformanceManager(A arguments, Services services) {
        arguments.getClass();
        services.getClass();
        PerformanceManager defaultPerformanceManager = getDefaultPerformanceManager();
        defaultPerformanceManager.setDetailedPerf(arguments.getDetailedPerf());
        return defaultPerformanceManager;
    }

    public abstract ExitCode doExecute(A arguments, CompilerConfiguration configuration, Disposable rootDisposable, KotlinPaths paths);

    public ExitCode doExecutePhased(A arguments, Services services, MessageCollector basicMessageCollector) {
        arguments.getClass();
        services.getClass();
        basicMessageCollector.getClass();
        return null;
    }

    public final ExitCode exec(PrintStream errStream, Services services, MessageRenderer messageRenderer, String[] args) throws IllegalAccessException, InvocationTargetException {
        ExitCode exitCodeExec;
        boolean z;
        errStream.getClass();
        services.getClass();
        messageRenderer.getClass();
        args.getClass();
        CommonCompilerArguments commonCompilerArgumentsCreateArguments = createArguments();
        ParseCommandLineArgumentsKt.parseCommandLineArguments$default(ArraysKt.asList(args), commonCompilerArgumentsCreateArguments, false, 4, null);
        if (this.isReadingSettingsFromEnvironmentAllowed) {
            ParseCommandLineArgumentsKt.parseCommandLineArgumentsFromEnvironment(commonCompilerArgumentsCreateArguments);
        }
        PrintingMessageCollector printingMessageCollector = new PrintingMessageCollector(errStream, messageRenderer, commonCompilerArgumentsCreateArguments.getVerbose());
        try {
            if (messageRenderer instanceof PlainTextMessageRenderer) {
                ((PlainTextMessageRenderer) messageRenderer).enableColorsIfNeeded();
            }
            errStream.print(messageRenderer.renderPreamble());
            List<String> listValidateArgumentsAllErrors = ParseCommandLineArgumentsKt.validateArgumentsAllErrors(commonCompilerArgumentsCreateArguments.getErrors());
            if (!listValidateArgumentsAllErrors.isEmpty()) {
                Iterator<T> it = listValidateArgumentsAllErrors.iterator();
                while (it.hasNext()) {
                    printingMessageCollector.report(CompilerMessageSeverity.ERROR, (String) it.next(), null);
                }
                printingMessageCollector.report(CompilerMessageSeverity.INFO, "Use -help for more information", null);
                exitCodeExec = ExitCode.COMPILATION_ERROR;
                return exitCodeExec;
            }
            if (commonCompilerArgumentsCreateArguments.getHelp() || commonCompilerArgumentsCreateArguments.getExtraHelp()) {
                errStream.print(messageRenderer.renderUsage(Usage.render(this, commonCompilerArgumentsCreateArguments)));
                exitCodeExec = ExitCode.OK;
                if (!z) {
                    return exitCodeExec;
                }
            } else {
                exitCodeExec = exec(printingMessageCollector, services, commonCompilerArgumentsCreateArguments);
                if (!z) {
                    return exitCodeExec;
                }
            }
            return exitCodeExec;
        } finally {
            errStream.print(messageRenderer.renderConclusion());
            if (messageRenderer instanceof PlainTextMessageRenderer) {
                ((PlainTextMessageRenderer) messageRenderer).disableColorsIfNeeded();
            }
        }
    }

    public final ExitCode execAndOutputXml(PrintStream errStream, Services services, String... args) {
        errStream.getClass();
        services.getClass();
        args.getClass();
        MessageRenderer messageRenderer = MessageRenderer.XML;
        messageRenderer.getClass();
        return exec(errStream, services, messageRenderer, args);
    }

    public final ExitCode execFullPathsInMessages(PrintStream errStream, String[] args) {
        errStream.getClass();
        args.getClass();
        Services services = Services.EMPTY;
        MessageRenderer messageRenderer = MessageRenderer.PLAIN_FULL_PATHS;
        messageRenderer.getClass();
        return exec(errStream, services, messageRenderer, args);
    }

    public abstract String executableScriptFileName();

    public PerformanceManager getDefaultPerformanceManager() {
        return (PerformanceManager) this.defaultPerformanceManager.getValue();
    }

    public abstract TargetPlatform getPlatform();

    /* JADX INFO: renamed from: isReadingSettingsFromEnvironmentAllowed, reason: from getter */
    public final boolean getIsReadingSettingsFromEnvironmentAllowed() {
        return this.isReadingSettingsFromEnvironmentAllowed;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x01ec A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:102:? A[LOOP:3: B:53:0x00f1->B:102:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:52:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:55:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:61:0x011a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x011c  */
    /* JADX WARN: Code duplicated, block: B:63:0x0121  */
    /* JADX WARN: Code duplicated, block: B:66:0x012d  */
    /* JADX WARN: Code duplicated, block: B:70:0x0136  */
    /* JADX WARN: Code duplicated, block: B:73:0x014d A[LOOP:0: B:72:0x014b->B:73:0x014d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:77:0x016e  */
    /* JADX WARN: Code duplicated, block: B:83:0x019a  */
    /* JADX WARN: Code duplicated, block: B:86:0x01b1 A[LOOP:2: B:84:0x01ab->B:86:0x01b1, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:88:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:94:0x017f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:0x017b A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:88:0x01c4, please report this as an issue */
    public final ExitCode loadPlugins(KotlinPaths paths, A arguments, CompilerConfiguration configuration, Disposable parentDisposable) {
        List list;
        Iterator it;
        String name;
        KotlinPaths kotlinPathsForCompiler;
        File libPath;
        ArrayList arrayList;
        int i;
        ArrayList arrayList2;
        ArrayList arrayList3;
        List list2;
        List list3;
        ArrayList arrayList4;
        Iterator it2;
        arguments.getClass();
        configuration.getClass();
        parentDisposable.getClass();
        String[] pluginClasspaths = arguments.getPluginClasspaths();
        if (pluginClasspaths == null) {
            pluginClasspaths = new String[0];
        }
        List mutableList = ArraysKt.toMutableList(pluginClasspaths);
        String[] pluginOptions = arguments.getPluginOptions();
        if (pluginOptions == null) {
            pluginOptions = new String[0];
        }
        List<String> mutableList2 = ArraysKt.toMutableList(pluginOptions);
        String[] pluginConfigurations = arguments.getPluginConfigurations();
        File file = null;
        List listAsList = pluginConfigurations != null ? ArraysKt.asList(pluginConfigurations) : null;
        if (listAsList == null) {
            listAsList = CollectionsKt.emptyList();
        }
        String[] pluginOrderConstraints = arguments.getPluginOrderConstraints();
        List listAsList2 = pluginOrderConstraints != null ? ArraysKt.asList(pluginOrderConstraints) : null;
        if (listAsList2 == null) {
            listAsList2 = CollectionsKt.emptyList();
        }
        boolean zAreEqual = Intrinsics.areEqual(configuration.get(CommonConfigurationKeys.USE_FIR), Boolean.TRUE);
        if (!CLICompilerKt.checkPluginsArguments(configuration, zAreEqual, mutableList, mutableList2, listAsList)) {
            return ExitCode.INTERNAL_ERROR;
        }
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        if (arguments.getDisableDefaultScriptingPlugin()) {
            arrayList6.add("plugin:kotlin.scripting:disable=true");
        } else {
            addPlatformOptions(arrayList6, arguments);
            List<PluginClasspathAndOptions> listExtractPluginClasspathAndOptions = PluginsOptionsParserKt.extractPluginClasspathAndOptions(listAsList);
            if ((listExtractPluginClasspathAndOptions instanceof Collection) && listExtractPluginClasspathAndOptions.isEmpty()) {
                list = mutableList;
                if (list instanceof Collection) {
                    it = list.iterator();
                    while (it.hasNext()) {
                        name = new File((String) it.next()).getName();
                        name.getClass();
                        if (StringsKt.startsWith$default(name, "kotlin-scripting-compiler", false, 2, (Object) null)) {
                        }
                    }
                    if (!tryLoadScriptingPluginFromCurrentClassLoader(configuration, mutableList2, zAreEqual)) {
                        if (paths == null) {
                            kotlinPathsForCompiler = PathUtil.getKotlinPathsForCompiler();
                        } else {
                            kotlinPathsForCompiler = paths;
                        }
                        libPath = kotlinPathsForCompiler.getLibPath();
                        if (libPath.exists()) {
                            file = libPath;
                        }
                        if (file == null) {
                            file = new File(".");
                        }
                        String[] kotlin_scripting_plugin_classpath_jars = PathUtil.INSTANCE.getKOTLIN_SCRIPTING_PLUGIN_CLASSPATH_JARS();
                        arrayList = new ArrayList(kotlin_scripting_plugin_classpath_jars.length);
                        for (String str : kotlin_scripting_plugin_classpath_jars) {
                            arrayList.add(new File(file, str));
                        }
                        arrayList2 = new ArrayList();
                        arrayList3 = new ArrayList();
                        for (Object obj : arrayList) {
                            if (((File) obj).exists()) {
                                arrayList2.add(obj);
                            } else {
                                arrayList3.add(obj);
                            }
                        }
                        Pair pair = new Pair(arrayList2, arrayList3);
                        list2 = (List) pair.component1();
                        list3 = (List) pair.component2();
                        if (list3.isEmpty()) {
                            List list4 = list2;
                            arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
                            it2 = list4.iterator();
                            while (it2.hasNext()) {
                                arrayList4.add(((File) it2.next()).getCanonicalPath());
                            }
                            arrayList5.addAll(0, arrayList4);
                        } else {
                            MessageCollector.report$default(CommonConfigurationKeysKt.getMessageCollector(configuration), CompilerMessageSeverity.LOGGING, "Scripting plugin will not be loaded: not all required jars are present in the classpath (missing files: " + list3 + ')', null, 4, null);
                        }
                    }
                } else {
                    it = list.iterator();
                    while (it.hasNext()) {
                        name = new File((String) it.next()).getName();
                        name.getClass();
                        if (StringsKt.startsWith$default(name, "kotlin-scripting-compiler", false, 2, (Object) null)) {
                        }
                    }
                    if (!tryLoadScriptingPluginFromCurrentClassLoader(configuration, mutableList2, zAreEqual)) {
                        if (paths == null) {
                            kotlinPathsForCompiler = PathUtil.getKotlinPathsForCompiler();
                        } else {
                            kotlinPathsForCompiler = paths;
                        }
                        libPath = kotlinPathsForCompiler.getLibPath();
                        if (libPath.exists()) {
                            file = libPath;
                        }
                        if (file == null) {
                            file = new File(".");
                        }
                        String[] kotlin_scripting_plugin_classpath_jars2 = PathUtil.INSTANCE.getKOTLIN_SCRIPTING_PLUGIN_CLASSPATH_JARS();
                        arrayList = new ArrayList(kotlin_scripting_plugin_classpath_jars2.length);
                        while (i < r8) {
                            arrayList.add(new File(file, str));
                        }
                        arrayList2 = new ArrayList();
                        arrayList3 = new ArrayList();
                        while (r2.hasNext()) {
                            if (((File) obj).exists()) {
                                arrayList2.add(obj);
                            } else {
                                arrayList3.add(obj);
                            }
                        }
                        Pair pair2 = new Pair(arrayList2, arrayList3);
                        list2 = (List) pair2.component1();
                        list3 = (List) pair2.component2();
                        if (list3.isEmpty()) {
                            List list5 = list2;
                            arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, 10));
                            it2 = list5.iterator();
                            while (it2.hasNext()) {
                                arrayList4.add(((File) it2.next()).getCanonicalPath());
                            }
                            arrayList5.addAll(0, arrayList4);
                        } else {
                            MessageCollector.report$default(CommonConfigurationKeysKt.getMessageCollector(configuration), CompilerMessageSeverity.LOGGING, "Scripting plugin will not be loaded: not all required jars are present in the classpath (missing files: " + list3 + ')', null, 4, null);
                        }
                    }
                }
            } else {
                Iterator<T> it3 = listExtractPluginClasspathAndOptions.iterator();
                while (it3.hasNext()) {
                    List<String> listComponent2 = ((PluginClasspathAndOptions) it3.next()).component2();
                    if (!(listComponent2 instanceof Collection) || !listComponent2.isEmpty()) {
                        Iterator<T> it4 = listComponent2.iterator();
                        while (it4.hasNext()) {
                            String name2 = new File((String) it4.next()).getName();
                            name2.getClass();
                            if (StringsKt.startsWith$default(name2, "kotlin-scripting-compiler", false, 2, (Object) null)) {
                            }
                        }
                    }
                }
                list = mutableList;
                if ((list instanceof Collection) || !list.isEmpty()) {
                    it = list.iterator();
                    while (it.hasNext()) {
                        name = new File((String) it.next()).getName();
                        name.getClass();
                        if (StringsKt.startsWith$default(name, "kotlin-scripting-compiler", false, 2, (Object) null)) {
                        }
                    }
                    if (!tryLoadScriptingPluginFromCurrentClassLoader(configuration, mutableList2, zAreEqual)) {
                        if (paths == null) {
                            kotlinPathsForCompiler = PathUtil.getKotlinPathsForCompiler();
                        } else {
                            kotlinPathsForCompiler = paths;
                        }
                        libPath = kotlinPathsForCompiler.getLibPath();
                        if (libPath.exists() && libPath.isDirectory()) {
                            file = libPath;
                        }
                        if (file == null) {
                            file = new File(".");
                        }
                        String[] kotlin_scripting_plugin_classpath_jars3 = PathUtil.INSTANCE.getKOTLIN_SCRIPTING_PLUGIN_CLASSPATH_JARS();
                        arrayList = new ArrayList(kotlin_scripting_plugin_classpath_jars3.length);
                        while (i < r8) {
                            arrayList.add(new File(file, str));
                        }
                        arrayList2 = new ArrayList();
                        arrayList3 = new ArrayList();
                        while (r2.hasNext()) {
                            if (((File) obj).exists()) {
                                arrayList2.add(obj);
                            } else {
                                arrayList3.add(obj);
                            }
                        }
                        Pair pair3 = new Pair(arrayList2, arrayList3);
                        list2 = (List) pair3.component1();
                        list3 = (List) pair3.component2();
                        if (list3.isEmpty()) {
                            List list6 = list2;
                            arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list6, 10));
                            it2 = list6.iterator();
                            while (it2.hasNext()) {
                                arrayList4.add(((File) it2.next()).getCanonicalPath());
                            }
                            arrayList5.addAll(0, arrayList4);
                        } else {
                            MessageCollector.report$default(CommonConfigurationKeysKt.getMessageCollector(configuration), CompilerMessageSeverity.LOGGING, "Scripting plugin will not be loaded: not all required jars are present in the classpath (missing files: " + list3 + ')', null, 4, null);
                        }
                    }
                } else if (!tryLoadScriptingPluginFromCurrentClassLoader(configuration, mutableList2, zAreEqual)) {
                    if (paths == null) {
                        kotlinPathsForCompiler = PathUtil.getKotlinPathsForCompiler();
                    } else {
                        kotlinPathsForCompiler = paths;
                    }
                    libPath = kotlinPathsForCompiler.getLibPath();
                    if (libPath.exists()) {
                        file = libPath;
                    }
                    if (file == null) {
                        file = new File(".");
                    }
                    String[] kotlin_scripting_plugin_classpath_jars4 = PathUtil.INSTANCE.getKOTLIN_SCRIPTING_PLUGIN_CLASSPATH_JARS();
                    arrayList = new ArrayList(kotlin_scripting_plugin_classpath_jars4.length);
                    while (i < r8) {
                        arrayList.add(new File(file, str));
                    }
                    arrayList2 = new ArrayList();
                    arrayList3 = new ArrayList();
                    while (r2.hasNext()) {
                        if (((File) obj).exists()) {
                            arrayList2.add(obj);
                        } else {
                            arrayList3.add(obj);
                        }
                    }
                    Pair pair4 = new Pair(arrayList2, arrayList3);
                    list2 = (List) pair4.component1();
                    list3 = (List) pair4.component2();
                    if (list3.isEmpty()) {
                        List list7 = list2;
                        arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list7, 10));
                        it2 = list7.iterator();
                        while (it2.hasNext()) {
                            arrayList4.add(((File) it2.next()).getCanonicalPath());
                        }
                        arrayList5.addAll(0, arrayList4);
                    } else {
                        MessageCollector.report$default(CommonConfigurationKeysKt.getMessageCollector(configuration), CompilerMessageSeverity.LOGGING, "Scripting plugin will not be loaded: not all required jars are present in the classpath (missing files: " + list3 + ')', null, 4, null);
                    }
                }
            }
        }
        mutableList.addAll(arrayList5);
        mutableList2.addAll(arrayList6);
        return PluginCliParser.loadPluginsSafe(mutableList, mutableList2, listAsList, listAsList2, configuration, parentDisposable);
    }

    public final void parseArguments(String[] args, A arguments) throws IllegalAccessException, InvocationTargetException {
        args.getClass();
        arguments.getClass();
        ParseCommandLineArgumentsKt.parseCommandLineArguments$default(ArraysKt.asList(args), arguments, false, 4, null);
        String strValidateArguments = ParseCommandLineArgumentsKt.validateArguments(arguments.getErrors());
        if (strValidateArguments == null) {
            return;
        }
        w01.a(strValidateArguments);
    }

    public final void setReadingSettingsFromEnvironmentAllowed(boolean z) {
        this.isReadingSettingsFromEnvironmentAllowed = z;
    }

    public abstract void setupPlatformSpecificArgumentsAndServices(CompilerConfiguration configuration, A arguments, Services services);

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0002J'\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0007¢\u0006\u0002\u0010\u0010J1\u0010\u0011\u001a\u00020\u00122\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/CLICompiler$Companion;", Argument.Delimiters.none, "<init>", "()V", "SCRIPT_PLUGIN_REGISTRAR_NAME", Argument.Delimiters.none, "SCRIPT_PLUGIN_COMMANDLINE_PROCESSOR_NAME", "SCRIPT_PLUGIN_K2_REGISTRAR_NAME", "defaultMessageRenderer", "Lorg/jetbrains/kotlin/cli/common/messages/MessageRenderer;", "doMain", Argument.Delimiters.none, "compiler", "Lorg/jetbrains/kotlin/cli/common/CLICompiler;", "args", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/cli/common/CLICompiler;[Ljava/lang/String;)V", "doMainNoExit", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "messageRenderer", "(Lorg/jetbrains/kotlin/cli/common/CLICompiler;[Ljava/lang/String;Lorg/jetbrains/kotlin/cli/common/messages/MessageRenderer;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final MessageRenderer defaultMessageRenderer() {
            String property = System.getProperty(MessageRenderer.PROPERTY_KEY);
            MessageRenderer messageRenderer = MessageRenderer.XML;
            if (Intrinsics.areEqual(property, messageRenderer.getName())) {
                messageRenderer.getClass();
                return messageRenderer;
            }
            MessageRenderer messageRenderer2 = MessageRenderer.GRADLE_STYLE;
            if (Intrinsics.areEqual(property, messageRenderer2.getName())) {
                messageRenderer2.getClass();
                return messageRenderer2;
            }
            MessageRenderer messageRenderer3 = MessageRenderer.XCODE_STYLE;
            if (Intrinsics.areEqual(property, messageRenderer3.getName())) {
                messageRenderer3.getClass();
                return messageRenderer3;
            }
            MessageRenderer messageRenderer4 = MessageRenderer.WITHOUT_PATHS;
            if (Intrinsics.areEqual(property, messageRenderer4.getName())) {
                messageRenderer4.getClass();
                return messageRenderer4;
            }
            MessageRenderer messageRenderer5 = MessageRenderer.PLAIN_FULL_PATHS;
            if (Intrinsics.areEqual(property, messageRenderer5.getName())) {
                messageRenderer5.getClass();
                return messageRenderer5;
            }
            MessageRenderer messageRenderer6 = MessageRenderer.PLAIN_RELATIVE_PATHS;
            messageRenderer6.getClass();
            return messageRenderer6;
        }

        public static /* synthetic */ ExitCode doMainNoExit$default(Companion companion, CLICompiler cLICompiler, String[] strArr, MessageRenderer messageRenderer, int i, Object obj) {
            if ((i & 4) != 0) {
                messageRenderer = companion.defaultMessageRenderer();
            }
            return companion.doMainNoExit(cLICompiler, strArr, messageRenderer);
        }

        @JvmStatic
        public final void doMain(CLICompiler<?> compiler, String[] args) {
            compiler.getClass();
            args.getClass();
            if (System.getProperty("java.awt.headless") == null) {
                System.setProperty("java.awt.headless", "true");
            }
            CompilerSystemProperties compilerSystemProperties = CompilerSystemProperties.KOTLIN_COLORS_ENABLED_PROPERTY;
            if (compilerSystemProperties.getValue() == null) {
                compilerSystemProperties.setValue("true");
            }
            CompatKt.setupIdeaStandaloneExecution();
            ExitCode exitCodeDoMainNoExit$default = doMainNoExit$default(this, compiler, args, null, 4, null);
            if (exitCodeDoMainNoExit$default == ExitCode.OK) {
                return;
            }
            System.exit(exitCodeDoMainNoExit$default.getCode());
            f63.a("System.exit returned normally, while it was supposed to halt JVM.");
        }

        @JvmStatic
        public final ExitCode doMainNoExit(CLICompiler<?> compiler, String[] args, MessageRenderer messageRenderer) {
            compiler.getClass();
            args.getClass();
            messageRenderer.getClass();
            try {
                PrintStream printStream = System.err;
                printStream.getClass();
                return compiler.exec(printStream, messageRenderer, (String[]) Arrays.copyOf(args, args.length));
            } catch (CompileEnvironmentException e) {
                System.err.println(e.getMessage());
                return ExitCode.INTERNAL_ERROR;
            }
        }

        private Companion() {
        }

        @JvmStatic
        public final ExitCode doMainNoExit(CLICompiler<?> cLICompiler, String[] strArr) {
            cLICompiler.getClass();
            strArr.getClass();
            return doMainNoExit$default(this, cLICompiler, strArr, null, 4, null);
        }
    }

    @JvmStatic
    public static final ExitCode doMainNoExit(CLICompiler<?> cLICompiler, String[] strArr, MessageRenderer messageRenderer) {
        return INSTANCE.doMainNoExit(cLICompiler, strArr, messageRenderer);
    }

    public final ExitCode exec(PrintStream errStream, MessageRenderer messageRenderer, String... args) {
        errStream.getClass();
        messageRenderer.getClass();
        args.getClass();
        return exec(errStream, Services.EMPTY, messageRenderer, args);
    }

    public final ExitCode exec(PrintStream errStream, String... args) {
        errStream.getClass();
        args.getClass();
        return exec(errStream, Services.EMPTY, INSTANCE.defaultMessageRenderer(), args);
    }

    public final ExitCode exec(MessageCollector messageCollector, Services services, A arguments) {
        messageCollector.getClass();
        services.getClass();
        arguments.getClass();
        disableURLConnectionCaches();
        printVersionIfNeeded(messageCollector, arguments);
        if (arguments.getSuppressWarnings() && !arguments.getAllWarningsAsErrors()) {
            messageCollector = new FilteringMessageCollector(messageCollector, Predicate.isEqual(CompilerMessageSeverity.WARNING));
        }
        ArgumentsKt.reportArgumentParseProblems(messageCollector, arguments);
        return execImpl(messageCollector, services, arguments);
    }
}
