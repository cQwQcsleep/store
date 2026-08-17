package org.jetbrains.kotlin.cli.pipeline.web;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.text.StringUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import org.jetbrains.kotlin.backend.common.linkage.partial.PartialLinkageConfigurationKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.CreatePhaseConfigKt;
import org.jetbrains.kotlin.cli.common.KlibArgumentsKt;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.ArgumentUtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.CommonJsAndWasmCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments;
import org.jetbrains.kotlin.cli.common.config.ContentRootsKt;
import org.jetbrains.kotlin.cli.js.HelpersKt;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.HmppCliModuleKt;
import org.jetbrains.kotlin.config.HmppCliModuleStructure;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.incremental.components.ExpectActualTracker;
import org.jetbrains.kotlin.incremental.components.ICFileMappingTracker;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.incremental.js.IncrementalDataProvider;
import org.jetbrains.kotlin.incremental.js.IncrementalNextRoundChecker;
import org.jetbrains.kotlin.incremental.js.IncrementalResultsConsumer;
import org.jetbrains.kotlin.js.config.JSConfigurationKeys;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.js.config.SourceMapNamesPolicy;
import org.jetbrains.kotlin.js.config.SourceMapSourceEmbedding;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J'\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0010¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0012\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/CommonWebConfigurationUpdater;", "T", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationUpdater;", "<init>", "()V", "fillConfiguration", Argument.Delimiters.none, "input", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "setupPlatformSpecificArgumentsAndServices", "arguments", "services", "Lorg/jetbrains/kotlin/config/Services;", "setupPlatformSpecificArgumentsAndServices$org_jetbrains_kotlin_cli_js", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;Lorg/jetbrains/kotlin/config/Services;)V", "initializeCommonConfiguration", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "initializeCommonConfiguration$org_jetbrains_kotlin_cli_js", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CommonWebConfigurationUpdater<T extends CommonJsAndWasmCompilerArguments> extends ConfigurationUpdater<T> {
    @Override // org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater
    public void fillConfiguration(ArgumentsPipelineArtifact<? extends T> input, CompilerConfiguration configuration) {
        CompilerConfiguration compilerConfiguration;
        input.getClass();
        configuration.getClass();
        CommonJsAndWasmCompilerArguments commonJsAndWasmCompilerArguments = (CommonJsAndWasmCompilerArguments) input.component1();
        Services services = input.getServices();
        Disposable rootDisposable = input.getRootDisposable();
        setupPlatformSpecificArgumentsAndServices$org_jetbrains_kotlin_cli_js(configuration, commonJsAndWasmCompilerArguments, services);
        initializeCommonConfiguration$org_jetbrains_kotlin_cli_js(configuration, commonJsAndWasmCompilerArguments, rootDisposable);
        JSConfigurationKeysKt.setJsIncrementalCompilationEnabled(configuration, UtilsKt.incrementalCompilationIsEnabledForJs(commonJsAndWasmCompilerArguments));
        String moduleName = commonJsAndWasmCompilerArguments.getModuleName();
        if (moduleName == null) {
            CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_ERROR(), "IR: Specify output name via " + ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.web.CommonWebConfigurationUpdater.fillConfiguration.1
                public Object get(Object obj) {
                    return ((K2JSCompilerArguments) obj).getModuleName();
                }

                public void set(Object obj, Object obj2) {
                    ((K2JSCompilerArguments) obj).setModuleName((String) obj2);
                }
            }), null, 4, null);
            compilerConfiguration = configuration;
        } else {
            compilerConfiguration = configuration;
            JSConfigurationKeysKt.setOutputName(compilerConfiguration, moduleName);
        }
        String outputDir = commonJsAndWasmCompilerArguments.getOutputDir();
        if (outputDir == null) {
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_ERROR(), "IR: Specify output dir via " + ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.web.CommonWebConfigurationUpdater.fillConfiguration.2
                public Object get(Object obj) {
                    return ((K2JSCompilerArguments) obj).getOutputDir();
                }

                public void set(Object obj, Object obj2) {
                    ((K2JSCompilerArguments) obj).setOutputDir((String) obj2);
                }
            }), null, 4, null);
        } else {
            try {
                JSConfigurationKeysKt.setOutputDir(compilerConfiguration, new File(outputDir).getCanonicalFile());
            } catch (IOException unused) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_ERROR(), "Could not resolve output directory", null, 4, null);
            }
        }
        JSConfigurationKeysKt.setProduceKlibFile(compilerConfiguration, commonJsAndWasmCompilerArguments.getIrProduceKlibFile());
        JSConfigurationKeysKt.setProduceKlibDir(compilerConfiguration, commonJsAndWasmCompilerArguments.getIrProduceKlibDir());
        String main = commonJsAndWasmCompilerArguments.getMain();
        if (main != null) {
            JSConfigurationKeysKt.setCallMainMode(compilerConfiguration, main);
        }
        JSConfigurationKeysKt.setDce(compilerConfiguration, commonJsAndWasmCompilerArguments.getIrDce());
        JSConfigurationKeysKt.setPerModuleOutputName(compilerConfiguration, commonJsAndWasmCompilerArguments.getIrPerModuleOutputName());
        JSConfigurationKeysKt.setIcCacheDirectory(compilerConfiguration, commonJsAndWasmCompilerArguments.getCacheDirectory());
        if (commonJsAndWasmCompilerArguments.getIncludes() == null) {
            CommonConfigurationKeysKt.setPhaseConfig(compilerConfiguration, CreatePhaseConfigKt.createPhaseConfig$default(commonJsAndWasmCompilerArguments, null, 2, null));
        }
        if (commonJsAndWasmCompilerArguments.getIncludes() == null && commonJsAndWasmCompilerArguments.getIrProduceJs()) {
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_ERROR(), "It is not possible to produce a KLIB ('" + ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.web.CommonWebConfigurationUpdater.fillConfiguration.5
                public Object get(Object obj) {
                    return ((K2JSCompilerArguments) obj).getIncludes();
                }

                public void set(Object obj, Object obj2) {
                    ((K2JSCompilerArguments) obj).setIncludes((String) obj2);
                }
            }) + "' is not passed) and compile the resulting JavaScript artifact ('" + ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.web.CommonWebConfigurationUpdater.fillConfiguration.6
                public Object get(Object obj) {
                    return Boolean.valueOf(((K2JSCompilerArguments) obj).getIrProduceJs());
                }

                public void set(Object obj, Object obj2) {
                    ((K2JSCompilerArguments) obj).setIrProduceJs(((Boolean) obj2).booleanValue());
                }
            }) + "' is passed) at the same time with the K2 compiler", null, 4, null);
        }
    }

    public final void initializeCommonConfiguration$org_jetbrains_kotlin_cli_js(CompilerConfiguration configuration, CommonJsAndWasmCompilerArguments arguments, Disposable rootDisposable) {
        configuration.getClass();
        arguments.getClass();
        rootDisposable.getClass();
        KlibArgumentsKt.setupCommonKlibArguments(configuration, arguments, false, rootDisposable);
        List listPlus = CollectionsKt.plus(HelpersKt.configureLibraries(arguments.getLibraries()), CollectionsKt.listOfNotNull(arguments.getIncludes()));
        List<String> listConfigureLibraries = HelpersKt.configureLibraries(arguments.getFriendModules());
        KlibArgumentsKt.checkForUnexpectedKlibLibraries(configuration, listConfigureLibraries, ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.web.CommonWebConfigurationUpdater$initializeCommonConfiguration$1
            public Object get(Object obj) {
                return ((K2JSCompilerArguments) obj).getFriendModules();
            }

            public void set(Object obj, Object obj2) {
                ((K2JSCompilerArguments) obj).setFriendModules((String) obj2);
            }
        }), listPlus, ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.web.CommonWebConfigurationUpdater$initializeCommonConfiguration$2
            public Object get(Object obj) {
                return ((K2JSCompilerArguments) obj).getLibraries();
            }

            public void set(Object obj, Object obj2) {
                ((K2JSCompilerArguments) obj).setLibraries((String) obj2);
            }
        }));
        JSConfigurationKeysKt.setLibraries(configuration, CollectionsKt.plus(JSConfigurationKeysKt.getLibraries(configuration), listPlus));
        JSConfigurationKeysKt.setFriendLibraries(configuration, CollectionsKt.plus(JSConfigurationKeysKt.getFriendLibraries(configuration), listConfigureLibraries));
        String includes = arguments.getIncludes();
        if (includes != null) {
            JSConfigurationKeysKt.setIncludes(configuration, includes);
            KlibArgumentsKt.prohibitExportKlibToOlderAbiVersionAtSecondStage(configuration);
        }
        Set set = ArraysKt.toSet(arguments.getCommonSources());
        HmppCliModuleStructure hmppModuleStructure = CommonConfigurationKeysKt.getHmppModuleStructure(configuration);
        for (String str : arguments.getFreeArgs()) {
            ContentRootsKt.addKotlinSourceRoot(configuration, str, set.contains(str), hmppModuleStructure != null ? HmppCliModuleKt.getModuleNameForSource(hmppModuleStructure, str) : null);
        }
        String irModuleName = arguments.getIrModuleName();
        if (irModuleName == null && (irModuleName = arguments.getModuleName()) == null) {
            CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_ERROR(), "Specify the module name via " + ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.web.CommonWebConfigurationUpdater$initializeCommonConfiguration$moduleName$1$message$1
                public Object get(Object obj) {
                    return ((K2JSCompilerArguments) obj).getIrModuleName();
                }

                public void set(Object obj, Object obj2) {
                    ((K2JSCompilerArguments) obj).setIrModuleName((String) obj2);
                }
            }) + " or " + ArgumentUtilsKt.getCliArgument(new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.web.CommonWebConfigurationUpdater$initializeCommonConfiguration$moduleName$1$message$2
                public Object get(Object obj) {
                    return ((K2JSCompilerArguments) obj).getModuleName();
                }

                public void set(Object obj, Object obj2) {
                    ((K2JSCompilerArguments) obj).setModuleName((String) obj2);
                }
            }), null, 4, null);
            return;
        }
        CommonConfigurationKeysKt.setModuleName(configuration, irModuleName);
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        if (perfManager != null) {
            perfManager.setTargetDescription(irModuleName);
        }
        CLIConfigurationKeysKt.setAllowKotlinPackage(configuration, arguments.getAllowKotlinPackage());
    }

    public void setupPlatformSpecificArgumentsAndServices$org_jetbrains_kotlin_cli_js(CompilerConfiguration configuration, T arguments, Services services) {
        configuration.getClass();
        arguments.getClass();
        services.getClass();
        if (arguments.getSourceMap()) {
            JSConfigurationKeysKt.setSourceMap(configuration, true);
            if (arguments.getSourceMapPrefix() != null) {
                String sourceMapPrefix = arguments.getSourceMapPrefix();
                sourceMapPrefix.getClass();
                JSConfigurationKeysKt.setSourceMapPrefix(configuration, sourceMapPrefix);
            }
            String sourceMapBaseDirs = arguments.getSourceMapBaseDirs();
            if (sourceMapBaseDirs == null && StringUtil.isNotEmpty(arguments.getSourceMapPrefix())) {
                sourceMapBaseDirs = HelpersKt.calculateSourceMapSourceRoot(configuration, arguments);
            }
            if (sourceMapBaseDirs != null) {
                configuration.put(JSConfigurationKeys.SOURCE_MAP_SOURCE_ROOTS, StringUtil.split(sourceMapBaseDirs, File.pathSeparator));
            }
        } else {
            if (arguments.getSourceMapPrefix() != null) {
                CliDiagnosticReportingKt.report(configuration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_WARNING(), "source-map-prefix argument has no effect without source map", null);
            }
            if (arguments.getSourceMapBaseDirs() != null) {
                CliDiagnosticReportingKt.report(configuration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_WARNING(), "source-map-source-root argument has no effect without source map", null);
            }
        }
        JSConfigurationKeysKt.setFriendPathsDisabled(configuration, arguments.getFriendModulesDisabled());
        JSConfigurationKeysKt.setGenerateDts(configuration, arguments.getGenerateDts());
        JSConfigurationKeysKt.setGenerateStrictImplicitExport(configuration, arguments.getStrictImplicitExportType());
        JSConfigurationKeysKt.setIncrementalDataProvider(configuration, (IncrementalDataProvider) services.get(IncrementalDataProvider.class));
        JSConfigurationKeysKt.setIncrementalResultsConsumer(configuration, (IncrementalResultsConsumer) services.get(IncrementalResultsConsumer.class));
        JSConfigurationKeysKt.setIncrementalNextRoundChecker(configuration, (IncrementalNextRoundChecker) services.get(IncrementalNextRoundChecker.class));
        CommonConfigurationKeysKt.setLookupTracker(configuration, (LookupTracker) services.get(LookupTracker.class));
        CommonConfigurationKeysKt.setExpectActualTracker(configuration, (ExpectActualTracker) services.get(ExpectActualTracker.class));
        CommonConfigurationKeysKt.setFileMappingTracker(configuration, (ICFileMappingTracker) services.get(ICFileMappingTracker.class));
        String sourceMapEmbedSources = arguments.getSourceMapEmbedSources();
        SourceMapSourceEmbedding sourceMapSourceEmbedding = sourceMapEmbedSources != null ? HelpersKt.getSourceMapContentEmbeddingMap().get(sourceMapEmbedSources) : SourceMapSourceEmbedding.INLINING;
        if (sourceMapSourceEmbedding == null) {
            CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_ERROR(), "Unknown source map source embedding mode: " + sourceMapEmbedSources + ". Valid values are: " + CollectionsKt.joinToString$default(HelpersKt.getSourceMapContentEmbeddingMap().keySet(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null), null, 4, null);
            sourceMapSourceEmbedding = SourceMapSourceEmbedding.INLINING;
        }
        JSConfigurationKeysKt.setSourceMapEmbedSources(configuration, sourceMapSourceEmbedding);
        if (!arguments.getSourceMap() && sourceMapEmbedSources != null) {
            CliDiagnosticReportingKt.report(configuration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_WARNING(), "source-map-embed-sources argument has no effect without source map", null);
        }
        String sourceMapNamesPolicy = arguments.getSourceMapNamesPolicy();
        SourceMapNamesPolicy sourceMapNamesPolicy2 = sourceMapNamesPolicy != null ? HelpersKt.getSourceMapNamesPolicyMap().get(sourceMapNamesPolicy) : SourceMapNamesPolicy.SIMPLE_NAMES;
        if (sourceMapNamesPolicy2 == null) {
            CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_ERROR(), "Unknown source map names policy: " + sourceMapNamesPolicy + ". Valid values are: " + CollectionsKt.joinToString$default(HelpersKt.getSourceMapNamesPolicyMap().keySet(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null), null, 4, null);
            sourceMapNamesPolicy2 = SourceMapNamesPolicy.SIMPLE_NAMES;
        }
        JSConfigurationKeysKt.setSourcemapNamesPolicy(configuration, sourceMapNamesPolicy2);
        JSConfigurationKeysKt.setPrintReachabilityInfo(configuration, arguments.getIrDcePrintReachabilityInfo());
        JSConfigurationKeysKt.setFakeOverrideValidator(configuration, arguments.getFakeOverrideValidator());
        String irDceRuntimeDiagnostic = arguments.getIrDceRuntimeDiagnostic();
        if (irDceRuntimeDiagnostic != null) {
            JSConfigurationKeysKt.setDceRuntimeDiagnostic(configuration, irDceRuntimeDiagnostic);
        }
        CliDiagnostics cliDiagnostics = CliDiagnostics.INSTANCE;
        PartialLinkageConfigurationKt.setupPartialLinkageConfig(configuration, arguments, cliDiagnostics.getWEB_ARGUMENT_WARNING(), cliDiagnostics.getWEB_ARGUMENT_ERROR());
    }
}
