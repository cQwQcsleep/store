package org.jetbrains.kotlin.cli.pipeline;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.common.ArgumentsKt;
import org.jetbrains.kotlin.cli.common.CLICompiler;
import org.jetbrains.kotlin.cli.common.CLICompilerKt;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.plugins.PluginCliParser;
import org.jetbrains.kotlin.cli.plugins.PluginClasspathAndOptions;
import org.jetbrains.kotlin.cli.plugins.PluginsOptionsParserKt;
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor;
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar;
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.ActionState;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.utils.KotlinPaths;
import org.jetbrains.kotlin.utils.PathUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003B\u009f\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012B\b\u0002\u0010\b\u001a<\u00128\u00126\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\nj\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0004\u0012\u00020\f`\u000e0\t\u00126\b\u0002\u0010\u000f\u001a0\u0012,\u0012*\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\nj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f`\u000e0\t\u0012\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00120\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH$J\u001b\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\u0006\u0010\u001e\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u001fJ\u001a\u0010 \u001a\u00020\r*\u00020!2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0002J(\u0010\"\u001a\u00020\r2\b\u0010#\u001a\u0004\u0018\u00010$2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010%\u001a\u00020!H\u0002J\u001e\u0010&\u001a\u00020'2\u0006\u0010%\u001a\u00020!2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H\u0002J\u001e\u0010)\u001a\u00020\r2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\u0006\u0010%\u001a\u00020!H\u0002R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/AbstractConfigurationPhase;", "A", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;", ModuleXmlParser.NAME, Argument.Delimiters.none, "preActions", Argument.Delimiters.none, "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/config/phaser/ActionState;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/phaser/Action;", "postActions", "configurationUpdaters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationUpdater;", "<init>", "(Ljava/lang/String;Ljava/util/Set;Ljava/util/Set;Ljava/util/List;)V", "getConfigurationUpdaters", "()Ljava/util/List;", "executePhase", "input", "createMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "versionArray", Argument.Delimiters.none, "provideCustomScriptingPluginOptions", "arguments", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;)Ljava/util/List;", "setupCommonConfiguration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "loadCompilerPlugins", "paths", "Lorg/jetbrains/kotlin/utils/KotlinPaths;", "configuration", "tryLoadScriptingPluginFromCurrentClassLoader", Argument.Delimiters.none, "pluginOptions", "processScriptPluginCliOptions", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractConfigurationPhase<A extends CommonCompilerArguments> extends PipelinePhase<ArgumentsPipelineArtifact<? extends A>, ConfigurationPipelineArtifact> {
    private final List<ConfigurationUpdater<A>> configurationUpdaters;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.cli.pipeline.AbstractConfigurationPhase$setupCommonConfiguration$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<int[], BinaryVersion> {
        public AnonymousClass1(Object obj) {
            super(1, obj, AbstractConfigurationPhase.class, "createMetadataVersion", "createMetadataVersion([I)Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", 0);
        }

        public final BinaryVersion invoke(int[] iArr) {
            iArr.getClass();
            return ((AbstractConfigurationPhase) ((CallableReference) this).receiver).createMetadataVersion(iArr);
        }
    }

    public /* synthetic */ AbstractConfigurationPhase(String str, Set set, Set set2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? SetsKt.emptySet() : set, (i & 4) != 0 ? SetsKt.emptySet() : set2, list);
    }

    /* JADX WARN: Code duplicated, block: B:100:? A[LOOP:3: B:50:0x00de->B:100:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x00da  */
    /* JADX WARN: Code duplicated, block: B:52:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:58:0x0106 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:59:0x0108  */
    /* JADX WARN: Code duplicated, block: B:60:0x010d  */
    /* JADX WARN: Code duplicated, block: B:63:0x0119  */
    /* JADX WARN: Code duplicated, block: B:66:0x0120  */
    /* JADX WARN: Code duplicated, block: B:68:0x0123  */
    /* JADX WARN: Code duplicated, block: B:71:0x013a A[LOOP:0: B:70:0x0138->B:71:0x013a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:75:0x015c  */
    /* JADX WARN: Code duplicated, block: B:81:0x0188  */
    /* JADX WARN: Code duplicated, block: B:84:0x019f A[LOOP:2: B:82:0x0199->B:84:0x019f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:86:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:92:0x016d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x0169 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:99:0x01ce A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:86:0x01b1, please report this as an issue */
    private final void loadCompilerPlugins(KotlinPaths paths, ArgumentsPipelineArtifact<? extends A> input, CompilerConfiguration configuration) {
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
        CommonCompilerArguments arguments = input.getArguments();
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
        List listAsList = pluginConfigurations != null ? ArraysKt.asList(pluginConfigurations) : null;
        if (listAsList == null) {
            listAsList = CollectionsKt.emptyList();
        }
        String[] pluginOrderConstraints = arguments.getPluginOrderConstraints();
        List listAsList2 = pluginOrderConstraints != null ? ArraysKt.asList(pluginOrderConstraints) : null;
        if (listAsList2 == null) {
            listAsList2 = CollectionsKt.emptyList();
        }
        if (CLICompilerKt.checkPluginsArguments(configuration, true, mutableList, mutableList2, listAsList)) {
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            if (arguments.getDisableDefaultScriptingPlugin()) {
                arrayList6.add("plugin:kotlin.scripting:disable=true");
            } else {
                CollectionsKt.addAll(arrayList6, provideCustomScriptingPluginOptions(arguments));
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
                        if (!tryLoadScriptingPluginFromCurrentClassLoader(configuration, mutableList2)) {
                            if (paths == null) {
                                kotlinPathsForCompiler = PathUtil.getKotlinPathsForCompiler();
                            } else {
                                kotlinPathsForCompiler = paths;
                            }
                            libPath = kotlinPathsForCompiler.getLibPath();
                            if (libPath.exists()) {
                                libPath = null;
                            } else {
                                libPath = null;
                            }
                            if (libPath == null) {
                                libPath = new File(".");
                            }
                            String[] kotlin_scripting_plugin_classpath_jars = PathUtil.INSTANCE.getKOTLIN_SCRIPTING_PLUGIN_CLASSPATH_JARS();
                            arrayList = new ArrayList(kotlin_scripting_plugin_classpath_jars.length);
                            for (String str : kotlin_scripting_plugin_classpath_jars) {
                                arrayList.add(new File(libPath, str));
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
                                CliDiagnosticReportingKt.reportLog$default(configuration, "Scripting plugin will not be loaded: not all required jars are present in the classpath (missing files: " + list3 + ')', null, 2, null);
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
                        if (!tryLoadScriptingPluginFromCurrentClassLoader(configuration, mutableList2)) {
                            if (paths == null) {
                                kotlinPathsForCompiler = PathUtil.getKotlinPathsForCompiler();
                            } else {
                                kotlinPathsForCompiler = paths;
                            }
                            libPath = kotlinPathsForCompiler.getLibPath();
                            if (libPath.exists()) {
                                libPath = null;
                            } else {
                                libPath = null;
                            }
                            if (libPath == null) {
                                libPath = new File(".");
                            }
                            String[] kotlin_scripting_plugin_classpath_jars2 = PathUtil.INSTANCE.getKOTLIN_SCRIPTING_PLUGIN_CLASSPATH_JARS();
                            arrayList = new ArrayList(kotlin_scripting_plugin_classpath_jars2.length);
                            while (i < r12) {
                                arrayList.add(new File(libPath, str));
                            }
                            arrayList2 = new ArrayList();
                            arrayList3 = new ArrayList();
                            while (r7.hasNext()) {
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
                                CliDiagnosticReportingKt.reportLog$default(configuration, "Scripting plugin will not be loaded: not all required jars are present in the classpath (missing files: " + list3 + ')', null, 2, null);
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
                        if (!tryLoadScriptingPluginFromCurrentClassLoader(configuration, mutableList2)) {
                            if (paths == null) {
                                kotlinPathsForCompiler = PathUtil.getKotlinPathsForCompiler();
                            } else {
                                kotlinPathsForCompiler = paths;
                            }
                            libPath = kotlinPathsForCompiler.getLibPath();
                            if (libPath.exists() || !libPath.isDirectory()) {
                                libPath = null;
                            }
                            if (libPath == null) {
                                libPath = new File(".");
                            }
                            String[] kotlin_scripting_plugin_classpath_jars3 = PathUtil.INSTANCE.getKOTLIN_SCRIPTING_PLUGIN_CLASSPATH_JARS();
                            arrayList = new ArrayList(kotlin_scripting_plugin_classpath_jars3.length);
                            while (i < r12) {
                                arrayList.add(new File(libPath, str));
                            }
                            arrayList2 = new ArrayList();
                            arrayList3 = new ArrayList();
                            while (r7.hasNext()) {
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
                                CliDiagnosticReportingKt.reportLog$default(configuration, "Scripting plugin will not be loaded: not all required jars are present in the classpath (missing files: " + list3 + ')', null, 2, null);
                            }
                        }
                    } else if (!tryLoadScriptingPluginFromCurrentClassLoader(configuration, mutableList2)) {
                        if (paths == null) {
                            kotlinPathsForCompiler = PathUtil.getKotlinPathsForCompiler();
                        } else {
                            kotlinPathsForCompiler = paths;
                        }
                        libPath = kotlinPathsForCompiler.getLibPath();
                        if (libPath.exists()) {
                            libPath = null;
                        } else {
                            libPath = null;
                        }
                        if (libPath == null) {
                            libPath = new File(".");
                        }
                        String[] kotlin_scripting_plugin_classpath_jars4 = PathUtil.INSTANCE.getKOTLIN_SCRIPTING_PLUGIN_CLASSPATH_JARS();
                        arrayList = new ArrayList(kotlin_scripting_plugin_classpath_jars4.length);
                        while (i < r12) {
                            arrayList.add(new File(libPath, str));
                        }
                        arrayList2 = new ArrayList();
                        arrayList3 = new ArrayList();
                        while (r7.hasNext()) {
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
                            CliDiagnosticReportingKt.reportLog$default(configuration, "Scripting plugin will not be loaded: not all required jars are present in the classpath (missing files: " + list3 + ')', null, 2, null);
                        }
                    }
                }
            }
            mutableList.addAll(arrayList5);
            mutableList2.addAll(arrayList6);
            PluginCliParser.loadPluginsSafe(mutableList, mutableList2, listAsList, listAsList2, configuration, input.getRootDisposable());
        }
    }

    private final void processScriptPluginCliOptions(List<String> pluginOptions, CompilerConfiguration configuration) throws ClassNotFoundException {
        Class<?> clsLoadClass;
        Constructor<?> declaredConstructor;
        if (pluginOptions.isEmpty()) {
            clsLoadClass = null;
        } else {
            clsLoadClass = PluginCliParser.class.getClassLoader().loadClass(CLICompiler.SCRIPT_PLUGIN_COMMANDLINE_PROCESSOR_NAME);
            clsLoadClass.getClass();
        }
        Object objNewInstance = (clsLoadClass == null || (declaredConstructor = clsLoadClass.getDeclaredConstructor(null)) == null) ? null : declaredConstructor.newInstance(null);
        CommandLineProcessor commandLineProcessor = objNewInstance instanceof CommandLineProcessor ? (CommandLineProcessor) objNewInstance : null;
        if (commandLineProcessor != null) {
            PluginsOptionsParserKt.processCompilerPluginsOptions(configuration, pluginOptions, CollectionsKt.listOf(commandLineProcessor));
        }
    }

    private final void setupCommonConfiguration(CompilerConfiguration compilerConfiguration, ArgumentsPipelineArtifact<? extends A> argumentsPipelineArtifact) {
        CommonCompilerArguments commonCompilerArgumentsComponent1 = argumentsPipelineArtifact.component1();
        CommonConfigurationKeysKt.setPerfManager(compilerConfiguration, argumentsPipelineArtifact.getPerformanceManager());
        CLIConfigurationKeysKt.setPrintVersion(compilerConfiguration, commonCompilerArgumentsComponent1.getVersion());
        CLIConfigurationKeysKt.setScriptMode(compilerConfiguration, commonCompilerArgumentsComponent1.getScript());
        CLIConfigurationKeysKt.setReplMode(compilerConfiguration, commonCompilerArgumentsComponent1.getRepl());
        ArgumentsKt.setupCommonArguments(compilerConfiguration, commonCompilerArgumentsComponent1, new AnonymousClass1(this));
        KotlinPaths kotlinPathsComputeKotlinPaths = ArgumentsKt.computeKotlinPaths(compilerConfiguration, commonCompilerArgumentsComponent1);
        if (kotlinPathsComputeKotlinPaths != null) {
            CLIConfigurationKeysKt.setKotlinPaths(compilerConfiguration, kotlinPathsComputeKotlinPaths);
        } else {
            kotlinPathsComputeKotlinPaths = null;
        }
        loadCompilerPlugins(kotlinPathsComputeKotlinPaths, argumentsPipelineArtifact, compilerConfiguration);
    }

    private final boolean tryLoadScriptingPluginFromCurrentClassLoader(CompilerConfiguration configuration, List<String> pluginOptions) {
        try {
            Object objNewInstance = PluginCliParser.class.getClassLoader().loadClass(CLICompiler.SCRIPT_PLUGIN_REGISTRAR_NAME).getDeclaredConstructor(null).newInstance(null);
            ComponentRegistrar componentRegistrar = objNewInstance instanceof ComponentRegistrar ? (ComponentRegistrar) objNewInstance : null;
            if (componentRegistrar != null) {
                configuration.add(ComponentRegistrar.INSTANCE.getPLUGIN_COMPONENT_REGISTRARS(), componentRegistrar);
            } else {
                componentRegistrar = null;
            }
            Object objNewInstance2 = PluginCliParser.class.getClassLoader().loadClass(CLICompiler.SCRIPT_PLUGIN_K2_REGISTRAR_NAME).getDeclaredConstructor(null).newInstance(null);
            CompilerPluginRegistrar compilerPluginRegistrar = objNewInstance2 instanceof CompilerPluginRegistrar ? (CompilerPluginRegistrar) objNewInstance2 : null;
            if (compilerPluginRegistrar != null) {
                configuration.add(CompilerPluginRegistrar.INSTANCE.getCOMPILER_PLUGIN_REGISTRARS(), compilerPluginRegistrar);
            } else {
                compilerPluginRegistrar = null;
            }
            if (componentRegistrar == null && compilerPluginRegistrar == null) {
                return false;
            }
            processScriptPluginCliOptions(pluginOptions, configuration);
            return true;
        } catch (Throwable th) {
            CliDiagnosticReportingKt.reportLog$default(configuration, "Exception on loading scripting plugin: " + th, null, 2, null);
            return false;
        }
    }

    public abstract BinaryVersion createMetadataVersion(int[] versionArray);

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public ConfigurationPipelineArtifact executePhase(ArgumentsPipelineArtifact<? extends A> input) {
        input.getClass();
        CompilerConfiguration configuration = input.getConfiguration();
        setupCommonConfiguration(configuration, input);
        Iterator<ConfigurationUpdater<A>> it = this.configurationUpdaters.iterator();
        while (it.hasNext()) {
            it.next().fillConfiguration(input, configuration);
        }
        if (input.getArguments().getPrintConfiguration() || input.getArguments().getVerbose()) {
            CliDiagnosticReportingKt.reportInfo$default(configuration, configuration.toString(), null, 2, null);
        }
        return new ConfigurationPipelineArtifact(configuration, input.getRootDisposable());
    }

    public final List<ConfigurationUpdater<A>> getConfigurationUpdaters() {
        return this.configurationUpdaters;
    }

    public List<String> provideCustomScriptingPluginOptions(A arguments) {
        arguments.getClass();
        return CollectionsKt.emptyList();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AbstractConfigurationPhase(String str, Set<? extends Function3<? super ActionState, ? super ArgumentsPipelineArtifact<? extends A>, ? super PipelineContext, Unit>> set, Set<? extends Function3<? super ActionState, ? super ConfigurationPipelineArtifact, ? super PipelineContext, Unit>> set2, List<? extends ConfigurationUpdater<? super A>> list) {
        super(str, set, set2);
        str.getClass();
        set.getClass();
        set2.getClass();
        list.getClass();
        this.configurationUpdaters = list;
    }
}
