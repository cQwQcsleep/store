package org.jetbrains.kotlin.cli.jvm.plugins;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import defpackage.p2b;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.MessageCollectorUtil;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.plugins.PluginCliParser;
import org.jetbrains.kotlin.cli.plugins.PluginClasspathAndOptions;
import org.jetbrains.kotlin.cli.plugins.PluginOrderConstraint;
import org.jetbrains.kotlin.cli.plugins.PluginsOptionsParserKt;
import org.jetbrains.kotlin.compiler.plugin.CliOptionProcessingException;
import org.jetbrains.kotlin.compiler.plugin.CliOptionValue;
import org.jetbrains.kotlin.compiler.plugin.CliOptionsKt;
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor;
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar;
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar;
import org.jetbrains.kotlin.compiler.plugin.PluginCliOptionProcessingException;
import org.jetbrains.kotlin.compiler.plugin.PluginProcessingException;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.util.ServiceLoaderLite;
import org.jetbrains.kotlin.utils.SortUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\"#$B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JM\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0002\u0010\u000fJ]\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0002\u0010\u0011JB\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007JP\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J2\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0002J4\u0010\u001a\u001a\u00020\u001b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002JF\u0010\u001c\u001a\u00020\u001b2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00142\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00172\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0003J(\u0010\u001d\u001a\u00020\u001b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00172\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001e\u0010 \u001a\u00020\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u00172\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/plugins/PluginCliParser;", Argument.Delimiters.none, "<init>", "()V", "loadPluginsSafe", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "pluginClasspaths", Argument.Delimiters.none, Argument.Delimiters.none, "pluginOptions", "pluginConfigurations", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "parentDisposable", "Lcom/intellij/openapi/Disposable;", "([Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lcom/intellij/openapi/Disposable;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "pluginOrderConstraints", "([Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lcom/intellij/openapi/Disposable;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", Argument.Delimiters.none, "loadRegisteredPluginsInfo", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/plugins/PluginCliParser$RegisteredPluginInfo;", "rawPluginConfigurations", Argument.Delimiters.none, "orderConstraints", "Lorg/jetbrains/kotlin/cli/plugins/PluginOrderConstraint;", "loadPluginsModernStyle", Argument.Delimiters.none, "loadPluginsLegacyStyle", "processPluginOptions", "classLoader", "Ljava/net/URLClassLoader;", "createClassLoader", ModuleXmlParser.CLASSPATH, "RegisteredPluginInfo", "UrlClassLoaderDisposable", "PluginProcessingError", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PluginCliParser {
    public static final PluginCliParser INSTANCE = new PluginCliParser();

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/plugins/PluginCliParser$PluginProcessingError;", "Ljava/lang/Error;", "Lkotlin/Error;", "message", Argument.Delimiters.none, "cause", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PluginProcessingError extends Error {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PluginProcessingError(String str, Throwable th) {
            super(str, th);
            str.getClass();
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B3\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/plugins/PluginCliParser$RegisteredPluginInfo;", Argument.Delimiters.none, "componentRegistrar", "Lorg/jetbrains/kotlin/compiler/plugin/ComponentRegistrar;", "compilerPluginRegistrar", "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar;", "commandLineProcessor", "Lorg/jetbrains/kotlin/compiler/plugin/CommandLineProcessor;", "pluginOptions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/compiler/plugin/CliOptionValue;", "<init>", "(Lorg/jetbrains/kotlin/compiler/plugin/ComponentRegistrar;Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar;Lorg/jetbrains/kotlin/compiler/plugin/CommandLineProcessor;Ljava/util/List;)V", "getComponentRegistrar", "()Lorg/jetbrains/kotlin/compiler/plugin/ComponentRegistrar;", "getCompilerPluginRegistrar", "()Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar;", "getCommandLineProcessor", "()Lorg/jetbrains/kotlin/compiler/plugin/CommandLineProcessor;", "getPluginOptions", "()Ljava/util/List;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class RegisteredPluginInfo {
        private final CommandLineProcessor commandLineProcessor;
        private final CompilerPluginRegistrar compilerPluginRegistrar;
        private final ComponentRegistrar componentRegistrar;
        private final List<CliOptionValue> pluginOptions;

        public RegisteredPluginInfo(ComponentRegistrar componentRegistrar, CompilerPluginRegistrar compilerPluginRegistrar, CommandLineProcessor commandLineProcessor, List<CliOptionValue> list) {
            list.getClass();
            this.componentRegistrar = componentRegistrar;
            this.compilerPluginRegistrar = compilerPluginRegistrar;
            this.commandLineProcessor = commandLineProcessor;
            this.pluginOptions = list;
        }

        public final CommandLineProcessor getCommandLineProcessor() {
            return this.commandLineProcessor;
        }

        public final CompilerPluginRegistrar getCompilerPluginRegistrar() {
            return this.compilerPluginRegistrar;
        }

        public final ComponentRegistrar getComponentRegistrar() {
            return this.componentRegistrar;
        }

        public final List<CliOptionValue> getPluginOptions() {
            return this.pluginOptions;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/plugins/PluginCliParser$UrlClassLoaderDisposable;", "Lcom/intellij/openapi/Disposable;", "classLoader", "Ljava/net/URLClassLoader;", "<init>", "(Ljava/net/URLClassLoader;)V", "classLoaderRef", "Ljava/lang/ref/WeakReference;", "dispose", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UrlClassLoaderDisposable implements Disposable {
        private final WeakReference<URLClassLoader> classLoaderRef;

        public UrlClassLoaderDisposable(URLClassLoader uRLClassLoader) {
            uRLClassLoader.getClass();
            this.classLoaderRef = new WeakReference<>(uRLClassLoader);
        }

        public void dispose() throws IOException {
            URLClassLoader uRLClassLoader = this.classLoaderRef.get();
            if (uRLClassLoader != null) {
                uRLClassLoader.close();
                this.classLoaderRef.clear();
            }
        }
    }

    private PluginCliParser() {
    }

    public static Iterable a(Map map, CompilerPluginRegistrar compilerPluginRegistrar) {
        compilerPluginRegistrar.getClass();
        List listEmptyList = (List) map.get(compilerPluginRegistrar.getPluginId());
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        return listEmptyList;
    }

    public static Iterable b(Map map, RegisteredPluginInfo registeredPluginInfo) {
        registeredPluginInfo.getClass();
        CompilerPluginRegistrar compilerPluginRegistrar = registeredPluginInfo.getCompilerPluginRegistrar();
        List listEmptyList = (List) map.get(compilerPluginRegistrar != null ? compilerPluginRegistrar.getPluginId() : null);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        return listEmptyList;
    }

    private final URLClassLoader createClassLoader(Iterable<String> classpath, Disposable parentDisposable) {
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(classpath, 10));
        Iterator<String> it = classpath.iterator();
        while (it.hasNext()) {
            arrayList.add(new File(it.next()).toURI().toURL());
        }
        URLClassLoader uRLClassLoader = new URLClassLoader((URL[]) arrayList.toArray(new URL[0]), PluginCliParser.class.getClassLoader());
        Disposer.register(parentDisposable, new UrlClassLoaderDisposable(uRLClassLoader));
        return uRLClassLoader;
    }

    @JvmStatic
    private static final void loadPluginsLegacyStyle(Iterable<String> pluginClasspaths, final List<PluginOrderConstraint> orderConstraints, Iterable<String> pluginOptions, CompilerConfiguration configuration, Disposable parentDisposable) {
        PluginCliParser pluginCliParser = INSTANCE;
        if (pluginClasspaths == null) {
            pluginClasspaths = CollectionsKt.emptyList();
        }
        URLClassLoader uRLClassLoaderCreateClassLoader = pluginCliParser.createClassLoader(pluginClasspaths, parentDisposable);
        ServiceLoaderLite serviceLoaderLite = ServiceLoaderLite.INSTANCE;
        configuration.addAll(ComponentRegistrar.INSTANCE.getPLUGIN_COMPONENT_REGISTRARS(), serviceLoaderLite.loadImplementations(ComponentRegistrar.class, uRLClassLoaderCreateClassLoader));
        List listLoadImplementations = serviceLoaderLite.loadImplementations(CompilerPluginRegistrar.class, uRLClassLoaderCreateClassLoader);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listLoadImplementations) {
            CompilerPluginRegistrar compilerPluginRegistrar = (CompilerPluginRegistrar) obj;
            try {
                if (compilerPluginRegistrar.getPluginId().length() > 0) {
                    arrayList.add(obj);
                }
            } catch (LinkageError e) {
                throw new PluginProcessingError("Plugin " + Reflection.getOrCreateKotlinClass(compilerPluginRegistrar.getClass()).getQualifiedName() + " is incompatible with the current version of the compiler.", e);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        for (Object obj2 : arrayList) {
            linkedHashMap.put(((CompilerPluginRegistrar) obj2).getPluginId(), obj2);
        }
        ArrayList<PluginOrderConstraint> arrayList2 = new ArrayList();
        for (Object obj3 : orderConstraints) {
            PluginOrderConstraint pluginOrderConstraint = (PluginOrderConstraint) obj3;
            if (linkedHashMap.containsKey(pluginOrderConstraint.getBefore()) && linkedHashMap.containsKey(pluginOrderConstraint.getAfter())) {
                arrayList2.add(obj3);
            }
        }
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (PluginOrderConstraint pluginOrderConstraint2 : arrayList2) {
            String after = pluginOrderConstraint2.getAfter();
            Object arrayList3 = linkedHashMap2.get(after);
            if (arrayList3 == null) {
                arrayList3 = new ArrayList();
                linkedHashMap2.put(after, arrayList3);
            }
            ((List) arrayList3).add((CompilerPluginRegistrar) MapsKt.getValue(linkedHashMap, pluginOrderConstraint2.getBefore()));
        }
        configuration.addAll(CompilerPluginRegistrar.INSTANCE.getCOMPILER_PLUGIN_REGISTRARS(), CollectionsKt.asReversed(SortUtilsKt.topologicalSort(listLoadImplementations, new Function1() { // from class: org.jetbrains.kotlin.cli.jvm.plugins.PluginCliParser$loadPluginsLegacyStyle$topologicalSort$1
            public final Void invoke(CompilerPluginRegistrar compilerPluginRegistrar2) {
                compilerPluginRegistrar2.getClass();
                throw new PluginProcessingException("Compiler plugin '" + compilerPluginRegistrar2.getPluginId() + "' is part of an constraint cycle: " + CollectionsKt.joinToString$default(orderConstraints, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), null, 2, null);
            }
        }, new Function1() { // from class: q2b
            public final Object invoke(Object obj4) {
                return PluginCliParser.a(linkedHashMap2, (CompilerPluginRegistrar) obj4);
            }
        })));
        INSTANCE.processPluginOptions(pluginOptions, configuration, uRLClassLoaderCreateClassLoader);
    }

    private final void loadPluginsModernStyle(Iterable<String> rawPluginConfigurations, List<PluginOrderConstraint> orderConstraints, CompilerConfiguration configuration, Disposable parentDisposable) {
        for (RegisteredPluginInfo registeredPluginInfo : loadRegisteredPluginsInfo(rawPluginConfigurations, orderConstraints, parentDisposable)) {
            ComponentRegistrar componentRegistrar = registeredPluginInfo.getComponentRegistrar();
            if (componentRegistrar != null) {
                configuration.add(ComponentRegistrar.INSTANCE.getPLUGIN_COMPONENT_REGISTRARS(), componentRegistrar);
            }
            CompilerPluginRegistrar compilerPluginRegistrar = registeredPluginInfo.getCompilerPluginRegistrar();
            if (compilerPluginRegistrar != null) {
                configuration.add(CompilerPluginRegistrar.INSTANCE.getCOMPILER_PLUGIN_REGISTRARS(), compilerPluginRegistrar);
            }
            if (!registeredPluginInfo.getPluginOptions().isEmpty()) {
                CommandLineProcessor commandLineProcessor = registeredPluginInfo.getCommandLineProcessor();
                if (commandLineProcessor == null) {
                    p2b.a();
                    return;
                }
                PluginsOptionsParserKt.processCompilerPluginOptions(commandLineProcessor, registeredPluginInfo.getPluginOptions(), configuration);
            }
        }
    }

    @JvmStatic
    public static final ExitCode loadPluginsSafe(Collection<String> pluginClasspaths, Collection<String> pluginOptions, Collection<String> pluginConfigurations, Collection<String> pluginOrderConstraints, CompilerConfiguration configuration, Disposable parentDisposable) {
        PluginProcessingException pluginProcessingException;
        PluginCliOptionProcessingException pluginCliOptionProcessingException;
        pluginClasspaths.getClass();
        pluginOptions.getClass();
        pluginConfigurations.getClass();
        pluginOrderConstraints.getClass();
        configuration.getClass();
        parentDisposable.getClass();
        try {
            try {
                Collection<String> collection = pluginOrderConstraints;
                try {
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
                    for (String str : collection) {
                        PluginOrderConstraint pluginOrderConstraintExtractPluginOrderConstraint = PluginsOptionsParserKt.extractPluginOrderConstraint(str);
                        if (pluginOrderConstraintExtractPluginOrderConstraint == null) {
                            throw new PluginProcessingException("Could not parse plugin order constraint: " + str, null, 2, null);
                        }
                        arrayList.add(pluginOrderConstraintExtractPluginOrderConstraint);
                    }
                    loadPluginsLegacyStyle(pluginClasspaths, arrayList, pluginOptions, configuration, parentDisposable);
                    INSTANCE.loadPluginsModernStyle(pluginConfigurations, arrayList, configuration, parentDisposable);
                    return ExitCode.OK;
                } catch (PluginCliOptionProcessingException e) {
                    pluginCliOptionProcessingException = e;
                    CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), pluginCliOptionProcessingException.getMessage() + "\n\n" + CliOptionsKt.cliPluginUsageString(pluginCliOptionProcessingException.getPluginId(), pluginCliOptionProcessingException.getOptions()), null, 4, null);
                    return ExitCode.INTERNAL_ERROR;
                } catch (PluginProcessingException e2) {
                    pluginProcessingException = e2;
                    KtSourcelessDiagnosticFactory compiler_arguments_error = CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR();
                    String message = pluginProcessingException.getMessage();
                    message.getClass();
                    CliDiagnosticReportingKt.report$default(configuration, compiler_arguments_error, message, null, 4, null);
                    return ExitCode.INTERNAL_ERROR;
                }
            } catch (CliOptionProcessingException e3) {
                KtSourcelessDiagnosticFactory compiler_arguments_error2 = CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR();
                String message2 = e3.getMessage();
                message2.getClass();
                CliDiagnosticReportingKt.report$default(configuration, compiler_arguments_error2, message2, null, 4, null);
                return ExitCode.INTERNAL_ERROR;
            } catch (Throwable th) {
                MessageCollectorUtil.reportException(CommonConfigurationKeysKt.getMessageCollector(configuration), th);
                return ExitCode.INTERNAL_ERROR;
            }
        } catch (PluginCliOptionProcessingException e4) {
            pluginCliOptionProcessingException = e4;
        } catch (PluginProcessingException e5) {
            pluginProcessingException = e5;
        }
    }

    private final List<RegisteredPluginInfo> loadRegisteredPluginsInfo(Iterable<String> rawPluginConfigurations, final List<PluginOrderConstraint> orderConstraints, Disposable parentDisposable) {
        List<PluginClasspathAndOptions> listExtractPluginClasspathAndOptions = PluginsOptionsParserKt.extractPluginClasspathAndOptions(rawPluginConfigurations);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listExtractPluginClasspathAndOptions, 10));
        for (PluginClasspathAndOptions pluginClasspathAndOptions : listExtractPluginClasspathAndOptions) {
            URLClassLoader uRLClassLoaderCreateClassLoader = INSTANCE.createClassLoader(pluginClasspathAndOptions.getClasspath(), parentDisposable);
            ServiceLoaderLite serviceLoaderLite = ServiceLoaderLite.INSTANCE;
            List listLoadImplementations = serviceLoaderLite.loadImplementations(ComponentRegistrar.class, uRLClassLoaderCreateClassLoader);
            List listLoadImplementations2 = serviceLoaderLite.loadImplementations(CompilerPluginRegistrar.class, uRLClassLoaderCreateClassLoader);
            int size = listLoadImplementations.size() + listLoadImplementations2.size();
            if (size == 0) {
                throw new PluginProcessingException("No plugins found in given classpath: " + CollectionsKt.joinToString$default(pluginClasspathAndOptions.getClasspath(), Argument.Delimiters.default, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), null, 2, null);
            }
            if (size != 1) {
                throw new PluginProcessingException(loadRegisteredPluginsInfo$lambda$0$multiplePluginsErrorMessage(pluginClasspathAndOptions, CollectionsKt.plus(listLoadImplementations, listLoadImplementations2)), null, 2, null);
            }
            List listLoadImplementations3 = serviceLoaderLite.loadImplementations(CommandLineProcessor.class, uRLClassLoaderCreateClassLoader);
            if (listLoadImplementations3.size() > 1) {
                throw new PluginProcessingException(loadRegisteredPluginsInfo$lambda$0$multiplePluginsErrorMessage(pluginClasspathAndOptions, listLoadImplementations3), null, 2, null);
            }
            CommandLineProcessor commandLineProcessor = (CommandLineProcessor) CollectionsKt.firstOrNull(listLoadImplementations3);
            CompilerPluginRegistrar compilerPluginRegistrar = (CompilerPluginRegistrar) CollectionsKt.firstOrNull(listLoadImplementations2);
            if (commandLineProcessor != null) {
                String pluginId = compilerPluginRegistrar != null ? compilerPluginRegistrar.getPluginId() : null;
                if (pluginId != null && pluginId.length() != 0 && !Intrinsics.areEqual(commandLineProcessor.getPluginId(), compilerPluginRegistrar.getPluginId())) {
                    throw new PluginProcessingException("Mismatched 'pluginId's between registrar (" + compilerPluginRegistrar.getPluginId() + ") and processor (" + commandLineProcessor.getPluginId() + ")}", null, 2, null);
                }
            }
            arrayList.add(new RegisteredPluginInfo((ComponentRegistrar) CollectionsKt.firstOrNull(listLoadImplementations), compilerPluginRegistrar, commandLineProcessor, pluginClasspathAndOptions.getOptions()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            CompilerPluginRegistrar compilerPluginRegistrar2 = ((RegisteredPluginInfo) obj).getCompilerPluginRegistrar();
            String pluginId2 = compilerPluginRegistrar2 != null ? compilerPluginRegistrar2.getPluginId() : null;
            if (!(pluginId2 == null || pluginId2.length() == 0)) {
                arrayList2.add(obj);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
        for (Object obj2 : arrayList2) {
            CompilerPluginRegistrar compilerPluginRegistrar3 = ((RegisteredPluginInfo) obj2).getCompilerPluginRegistrar();
            compilerPluginRegistrar3.getClass();
            linkedHashMap.put(compilerPluginRegistrar3.getPluginId(), obj2);
        }
        ArrayList<PluginOrderConstraint> arrayList3 = new ArrayList();
        for (Object obj3 : orderConstraints) {
            PluginOrderConstraint pluginOrderConstraint = (PluginOrderConstraint) obj3;
            if (linkedHashMap.containsKey(pluginOrderConstraint.getBefore()) && linkedHashMap.containsKey(pluginOrderConstraint.getAfter())) {
                arrayList3.add(obj3);
            }
        }
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (PluginOrderConstraint pluginOrderConstraint2 : arrayList3) {
            String after = pluginOrderConstraint2.getAfter();
            Object arrayList4 = linkedHashMap2.get(after);
            if (arrayList4 == null) {
                arrayList4 = new ArrayList();
                linkedHashMap2.put(after, arrayList4);
            }
            ((List) arrayList4).add((RegisteredPluginInfo) MapsKt.getValue(linkedHashMap, pluginOrderConstraint2.getBefore()));
        }
        return CollectionsKt.asReversed(SortUtilsKt.topologicalSort(arrayList, new Function1() { // from class: org.jetbrains.kotlin.cli.jvm.plugins.PluginCliParser$loadRegisteredPluginsInfo$topologicalSort$1
            public final Void invoke(PluginCliParser.RegisteredPluginInfo registeredPluginInfo) {
                registeredPluginInfo.getClass();
                CompilerPluginRegistrar compilerPluginRegistrar4 = registeredPluginInfo.getCompilerPluginRegistrar();
                throw new PluginProcessingException("Compiler plugin '" + (compilerPluginRegistrar4 != null ? compilerPluginRegistrar4.getPluginId() : null) + "' is part of an constraint cycle: " + CollectionsKt.joinToString$default(orderConstraints, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), null, 2, null);
            }
        }, new Function1() { // from class: r2b
            public final Object invoke(Object obj4) {
                return PluginCliParser.b(linkedHashMap2, (PluginCliParser.RegisteredPluginInfo) obj4);
            }
        }));
    }

    private static final String loadRegisteredPluginsInfo$lambda$0$multiplePluginsErrorMessage(PluginClasspathAndOptions pluginClasspathAndOptions, List<? extends Object> list) {
        StringBuilder sb = new StringBuilder("Multiple plugins found in given classpath: ");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            String qualifiedName = Reflection.getOrCreateKotlinClass(it.next().getClass()).getQualifiedName();
            if (qualifiedName != null) {
                arrayList.add(qualifiedName);
            }
        }
        sb.append(CollectionsKt.joinToString$default(arrayList, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        sb.append('\n');
        sb.append("  Plugin configuration is: " + pluginClasspathAndOptions.getRawArgument());
        return sb.toString();
    }

    private final void processPluginOptions(Iterable<String> pluginOptions, CompilerConfiguration configuration, URLClassLoader classLoader) {
        PluginsOptionsParserKt.processCompilerPluginsOptions(configuration, pluginOptions, ServiceLoaderLite.INSTANCE.loadImplementations(CommandLineProcessor.class, classLoader));
    }

    @JvmStatic
    public static final ExitCode loadPluginsSafe(String[] pluginClasspaths, String[] pluginOptions, String[] pluginConfigurations, String[] pluginOrderConstraints, CompilerConfiguration configuration, Disposable parentDisposable) {
        configuration.getClass();
        parentDisposable.getClass();
        List listAsList = pluginClasspaths != null ? ArraysKt.asList(pluginClasspaths) : null;
        if (listAsList == null) {
            listAsList = CollectionsKt.emptyList();
        }
        List list = listAsList;
        List listAsList2 = pluginOptions != null ? ArraysKt.asList(pluginOptions) : null;
        if (listAsList2 == null) {
            listAsList2 = CollectionsKt.emptyList();
        }
        List list2 = listAsList2;
        List listAsList3 = pluginConfigurations != null ? ArraysKt.asList(pluginConfigurations) : null;
        if (listAsList3 == null) {
            listAsList3 = CollectionsKt.emptyList();
        }
        List list3 = listAsList3;
        List listAsList4 = pluginOrderConstraints != null ? ArraysKt.asList(pluginOrderConstraints) : null;
        if (listAsList4 == null) {
            listAsList4 = CollectionsKt.emptyList();
        }
        return loadPluginsSafe(list, list2, list3, listAsList4, configuration, parentDisposable);
    }

    @Deprecated(message = "Use loadPluginsSafe with order constraints instead", replaceWith = @ReplaceWith(expression = "loadPluginsSafe(pluginClasspaths, pluginOptions, pluginConfigurations, emptyList(), configuration, parentDisposable)", imports = {}))
    @JvmStatic
    public static final ExitCode loadPluginsSafe(Collection<String> pluginClasspaths, Collection<String> pluginOptions, Collection<String> pluginConfigurations, CompilerConfiguration configuration, Disposable parentDisposable) {
        pluginClasspaths.getClass();
        pluginOptions.getClass();
        pluginConfigurations.getClass();
        configuration.getClass();
        parentDisposable.getClass();
        return loadPluginsSafe(pluginClasspaths, pluginOptions, pluginConfigurations, CollectionsKt.emptyList(), configuration, parentDisposable);
    }

    @Deprecated(message = "Use loadPluginsSafe with order constraints instead", replaceWith = @ReplaceWith(expression = "loadPluginsSafe(pluginClasspaths, pluginOptions, pluginConfigurations, emptyList(), configuration, parentDisposable)", imports = {}))
    @JvmStatic
    public static final ExitCode loadPluginsSafe(String[] pluginClasspaths, String[] pluginOptions, String[] pluginConfigurations, CompilerConfiguration configuration, Disposable parentDisposable) {
        configuration.getClass();
        parentDisposable.getClass();
        List listAsList = pluginClasspaths != null ? ArraysKt.asList(pluginClasspaths) : null;
        if (listAsList == null) {
            listAsList = CollectionsKt.emptyList();
        }
        List list = listAsList;
        List listAsList2 = pluginOptions != null ? ArraysKt.asList(pluginOptions) : null;
        if (listAsList2 == null) {
            listAsList2 = CollectionsKt.emptyList();
        }
        List list2 = listAsList2;
        List listAsList3 = pluginConfigurations != null ? ArraysKt.asList(pluginConfigurations) : null;
        if (listAsList3 == null) {
            listAsList3 = CollectionsKt.emptyList();
        }
        return loadPluginsSafe(list, list2, listAsList3, CollectionsKt.emptyList(), configuration, parentDisposable);
    }
}
