package org.jetbrains.kotlin.cli.pipeline.metadata;

import com.intellij.openapi.Disposable;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.KlibArgumentsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2MetadataCompilerArguments;
import org.jetbrains.kotlin.cli.common.config.ContentRootsKt;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootsKt;
import org.jetbrains.kotlin.cli.jvm.config.K2MetadataConfigurationKeys;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater;
import org.jetbrains.kotlin.cli.pipeline.metadata.MetadataConfigurationUpdater;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.HmppCliModuleStructure;
import org.jetbrains.kotlin.config.KlibConfigurationKeysKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.platform.CommonPlatforms;
import org.jetbrains.kotlin.platform.SimplePlatform;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.js.JsPlatforms;
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms;
import org.jetbrains.kotlin.platform.konan.NativePlatformUnspecifiedTarget;
import org.jetbrains.kotlin.platform.wasm.WasmPlatformWithTarget;
import org.jetbrains.kotlin.platform.wasm.WasmTarget;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001e\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011J>\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\u0006\u0010\u001a\u001a\u00020\u0013J\u001e\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00152\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataConfigurationUpdater;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationUpdater;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2MetadataCompilerArguments;", "<init>", "()V", "platformMap", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/platform/SimplePlatform;", "fillConfiguration", Argument.Delimiters.none, "input", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "arguments", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "computeTargetPlatform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "platformsFromArg", Argument.Delimiters.none, "onUnknownPlatform", "Lkotlin/Function1;", "onEmptyPlatforms", "Lkotlin/Function0;", "defaultPlatform", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MetadataConfigurationUpdater extends ConfigurationUpdater<K2MetadataCompilerArguments> {
    public static final MetadataConfigurationUpdater INSTANCE = new MetadataConfigurationUpdater();
    private static final Map<String, SimplePlatform> platformMap = MapsKt.mapOf(new Pair[]{TuplesKt.to("JVM", JvmPlatforms.INSTANCE.getUNSPECIFIED_SIMPLE_JVM_PLATFORM()), TuplesKt.to("JS", JsPlatforms.DefaultSimpleJsPlatform.INSTANCE), TuplesKt.to("WasmJs", new WasmPlatformWithTarget(WasmTarget.JS)), TuplesKt.to("WasmWasi", new WasmPlatformWithTarget(WasmTarget.WASI)), TuplesKt.to("Native", NativePlatformUnspecifiedTarget.INSTANCE)});

    private MetadataConfigurationUpdater() {
    }

    public static Unit a(CompilerConfiguration compilerConfiguration) {
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), "No target platform specified, using default", null, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit b(CompilerConfiguration compilerConfiguration, String str) {
        str.getClass();
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Unknown target platform: " + str + ". Possible values are: " + platformMap.keySet(), null, 4, null);
        return Unit.INSTANCE;
    }

    public final TargetPlatform computeTargetPlatform(List<String> platformsFromArg, Function1<? super String, Unit> onUnknownPlatform, Function0<Unit> onEmptyPlatforms, TargetPlatform defaultPlatform) {
        platformsFromArg.getClass();
        onUnknownPlatform.getClass();
        onEmptyPlatforms.getClass();
        defaultPlatform.getClass();
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        for (String str : platformsFromArg) {
            SimplePlatform simplePlatform = platformMap.get(str);
            if (simplePlatform == null) {
                onUnknownPlatform.invoke(str);
            } else {
                setCreateSetBuilder.add(simplePlatform);
            }
        }
        Set setBuild = SetsKt.build(setCreateSetBuilder);
        if (!setBuild.isEmpty()) {
            return new TargetPlatform(setBuild);
        }
        onEmptyPlatforms.invoke();
        return defaultPlatform;
    }

    public final void fillConfiguration(CompilerConfiguration configuration, K2MetadataCompilerArguments arguments, Disposable rootDisposable) {
        Set setEmptySet;
        configuration.getClass();
        arguments.getClass();
        rootDisposable.getClass();
        String[] commonSources = arguments.getCommonSources();
        if (commonSources == null || (setEmptySet = ArraysKt.toSet(commonSources)) == null) {
            setEmptySet = SetsKt.emptySet();
        }
        if (((HmppCliModuleStructure) configuration.get(CommonConfigurationKeys.HMPP_MODULE_STRUCTURE)) != null) {
            CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "HMPP module structure should not be passed during metadata compilation. Please remove `-Xfragments` and related flags", null, 4, null);
            return;
        }
        for (String str : arguments.getFreeArgs()) {
            ContentRootsKt.addKotlinSourceRoot(configuration, str, setEmptySet.contains(str), null);
        }
        if (arguments.getClasspath() != null) {
            String classpath = arguments.getClasspath();
            classpath.getClass();
            List listSplit$default = StringsKt.split$default(classpath, new char[]{File.pathSeparatorChar}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                arrayList.add(new File((String) it.next()));
            }
            JvmContentRootsKt.addJvmClasspathRoots(configuration, arrayList);
        }
        String moduleName = arguments.getModuleName();
        if (moduleName == null) {
            moduleName = "main";
        }
        configuration.put(CommonConfigurationKeys.MODULE_NAME, moduleName);
        configuration.put(CLIConfigurationKeys.ALLOW_KOTLIN_PACKAGE, Boolean.valueOf(arguments.getAllowKotlinPackage()));
        K2MetadataConfigurationKeys k2MetadataConfigurationKeys = K2MetadataConfigurationKeys.INSTANCE;
        CompilerConfigurationKey<List<String>> friend_paths = k2MetadataConfigurationKeys.getFRIEND_PATHS();
        String[] friendPaths = arguments.getFriendPaths();
        configuration.putIfNotNull(friend_paths, friendPaths != null ? ArraysKt.toList(friendPaths) : null);
        CompilerConfigurationKey<List<String>> refines_paths = k2MetadataConfigurationKeys.getREFINES_PATHS();
        String[] refinesPaths = arguments.getRefinesPaths();
        configuration.putIfNotNull(refines_paths, refinesPaths != null ? ArraysKt.toList(refinesPaths) : null);
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        perfManager.getClass();
        perfManager.setOutputKind(arguments.getMetadataKlib() ? "KLib" : "metadata");
        perfManager.setTargetDescription(moduleName);
        String[] targetPlatform = arguments.getTargetPlatform();
        if (targetPlatform == null) {
            targetPlatform = new String[0];
        }
        CommonConfigurationKeysKt.setTargetPlatform(configuration, computeTargetPlatform(ArraysKt.toList(targetPlatform), configuration));
        String destination = arguments.getDestination();
        if (destination != null) {
            if (StringsKt.endsWith$default(destination, ".jar", false, 2, (Object) null)) {
                CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), ".jar destination is not yet supported, results will be written to the directory with the given name", null, 4, null);
            }
            configuration.put(CLIConfigurationKeys.METADATA_DESTINATION_DIRECTORY, new File(destination));
        } else {
            CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Specify destination via -d", null, 4, null);
        }
        KlibConfigurationKeysKt.setZipFileSystemAccessor(configuration, KlibArgumentsKt.getZipFileSystemAccessor(arguments, new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.metadata.MetadataConfigurationUpdater.fillConfiguration.3
            public Object get(Object obj) {
                return ((K2MetadataCompilerArguments) obj).getKlibZipFileAccessorCacheLimit();
            }

            public void set(Object obj, Object obj2) {
                ((K2MetadataCompilerArguments) obj).setKlibZipFileAccessorCacheLimit((String) obj2);
            }
        }, configuration, rootDisposable));
    }

    private final TargetPlatform computeTargetPlatform(List<String> platformsFromArg, final CompilerConfiguration configuration) {
        return computeTargetPlatform(platformsFromArg, new Function1() { // from class: a0a
            public final Object invoke(Object obj) {
                return MetadataConfigurationUpdater.b(configuration, (String) obj);
            }
        }, new Function0() { // from class: b0a
            public final Object invoke() {
                return MetadataConfigurationUpdater.a(configuration);
            }
        }, CommonPlatforms.INSTANCE.getDefaultCommonPlatform());
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater
    public void fillConfiguration(ArgumentsPipelineArtifact<K2MetadataCompilerArguments> input, CompilerConfiguration configuration) {
        input.getClass();
        configuration.getClass();
        fillConfiguration(configuration, (K2MetadataCompilerArguments) input.getArguments(), input.getRootDisposable());
    }
}
