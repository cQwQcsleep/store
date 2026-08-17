package org.jetbrains.kotlin.cli.metadata;

import java.io.File;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.analyzer.AnalysisResult;
import org.jetbrains.kotlin.analyzer.AnalysisResult$RetryWithAdditionalRoots;
import org.jetbrains.kotlin.analyzer.ModuleContent;
import org.jetbrains.kotlin.analyzer.common.CommonDependenciesContainer;
import org.jetbrains.kotlin.analyzer.common.CommonResolverForModuleFactory;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.metadata.CommonAnalysisKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.context.ProjectContext;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.CommonPlatforms;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.CompilerEnvironment;
import org.jetbrains.kotlin.serialization.deserialization.MetadataPartProvider;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0000\u001a\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0002\"\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0010"}, d2 = {"destDir", "Ljava/io/File;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", "getDestDir", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;)Ljava/io/File;", "runCommonAnalysisForSerialization", "Lorg/jetbrains/kotlin/cli/metadata/CommonAnalysisResult;", "environment", "dependOnBuiltins", Argument.Delimiters.none, "dependencyContainerFactory", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/analyzer/common/CommonDependenciesContainer;", "runCommonAnalysisIteration", "Lorg/jetbrains/kotlin/cli/metadata/AnalysisResultWithHasErrors;", "dependencyContainer", "org.jetbrains.kotlin:cli-metadata"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonAnalysisKt {
    public static AnalysisResult a(List list, Name name, boolean z, CompilerConfiguration compilerConfiguration, CommonDependenciesContainer commonDependenciesContainer, final KotlinCoreEnvironment kotlinCoreEnvironment) {
        return CommonResolverForModuleFactory.Companion.analyzeFiles$default(CommonResolverForModuleFactory.Companion, list, name, z, CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration), CommonPlatforms.INSTANCE.getDefaultCommonPlatform(), CompilerEnvironment.INSTANCE, (Map) null, commonDependenciesContainer, (ProjectContext) null, new Function1() { // from class: n72
            public final Object invoke(Object obj) {
                return CommonAnalysisKt.runCommonAnalysisIteration$lambda$0$0(kotlinCoreEnvironment, (ModuleContent) obj);
            }
        }, 320, (Object) null);
    }

    public static final File getDestDir(KotlinCoreEnvironment kotlinCoreEnvironment) {
        kotlinCoreEnvironment.getClass();
        return (File) kotlinCoreEnvironment.getConfiguration().get(CLIConfigurationKeys.METADATA_DESTINATION_DIRECTORY);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static final CommonAnalysisResult runCommonAnalysisForSerialization(KotlinCoreEnvironment kotlinCoreEnvironment, boolean z, Function0<? extends CommonDependenciesContainer> function0) throws UninitializedPropertyAccessException {
        AnalysisResultWithHasErrors analysisResultWithHasErrors;
        AnalysisResult result;
        AnalysisResultWithHasErrors analysisResultWithHasErrorsRunCommonAnalysisIteration;
        AnalysisResultWithHasErrors analysisResultWithHasErrors2;
        kotlinCoreEnvironment.getClass();
        function0.getClass();
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(kotlinCoreEnvironment.getConfiguration());
        if (perfManager != null) {
            perfManager.notifyCurrentPhaseFinishedIfNeeded();
        }
        do {
            PhaseType phaseType = PhaseType.Analysis;
            if (perfManager == null) {
                analysisResultWithHasErrorsRunCommonAnalysisIteration = runCommonAnalysisIteration(kotlinCoreEnvironment, z, (CommonDependenciesContainer) function0.invoke());
                if (analysisResultWithHasErrorsRunCommonAnalysisIteration == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("analysisResultWithHasErrors");
                    analysisResultWithHasErrors2 = null;
                } else {
                    analysisResultWithHasErrors2 = analysisResultWithHasErrorsRunCommonAnalysisIteration;
                }
                result = analysisResultWithHasErrors2.getResult();
                if (result instanceof AnalysisResult$RetryWithAdditionalRoots) {
                    kotlinCoreEnvironment.addKotlinSourceRoots(((AnalysisResult$RetryWithAdditionalRoots) result).getAdditionalKotlinRoots());
                }
            } else {
                try {
                    perfManager.notifyPhaseStarted(phaseType);
                    AnalysisResultWithHasErrors analysisResultWithHasErrorsRunCommonAnalysisIteration2 = runCommonAnalysisIteration(kotlinCoreEnvironment, z, (CommonDependenciesContainer) function0.invoke());
                    if (analysisResultWithHasErrorsRunCommonAnalysisIteration2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("analysisResultWithHasErrors");
                        analysisResultWithHasErrors = null;
                    } else {
                        analysisResultWithHasErrors = analysisResultWithHasErrorsRunCommonAnalysisIteration2;
                    }
                    result = analysisResultWithHasErrors.getResult();
                    if (result instanceof AnalysisResult$RetryWithAdditionalRoots) {
                        kotlinCoreEnvironment.addKotlinSourceRoots(((AnalysisResult$RetryWithAdditionalRoots) result).getAdditionalKotlinRoots());
                    }
                    perfManager.notifyPhaseFinished(phaseType);
                    analysisResultWithHasErrorsRunCommonAnalysisIteration = analysisResultWithHasErrorsRunCommonAnalysisIteration2;
                } catch (Throwable th) {
                    perfManager.notifyPhaseFinished(phaseType);
                    throw th;
                }
            }
        } while (result instanceof AnalysisResult$RetryWithAdditionalRoots);
        AnalysisResult result2 = analysisResultWithHasErrorsRunCommonAnalysisIteration.getResult();
        if (!result2.getShouldGenerateCode() || analysisResultWithHasErrorsRunCommonAnalysisIteration.getHasErrors()) {
            return null;
        }
        return new CommonAnalysisResult(result2.getModuleDescriptor(), result2.getBindingContext());
    }

    private static final AnalysisResultWithHasErrors runCommonAnalysisIteration(final KotlinCoreEnvironment kotlinCoreEnvironment, final boolean z, final CommonDependenciesContainer commonDependenciesContainer) {
        final CompilerConfiguration configuration = kotlinCoreEnvironment.getConfiguration();
        final List<KtFile> sourceFiles = kotlinCoreEnvironment.getSourceFiles();
        final Name nameSpecial = Name.special("<" + ((String) configuration.getNotNull(CommonConfigurationKeys.MODULE_NAME)) + '>');
        nameSpecial.getClass();
        AnalyzerWithCompilerReport analyzerWithCompilerReport = new AnalyzerWithCompilerReport(configuration);
        analyzerWithCompilerReport.analyzeAndReport(sourceFiles, new Function0() { // from class: m72
            public final Object invoke() {
                return CommonAnalysisKt.a(sourceFiles, nameSpecial, z, configuration, commonDependenciesContainer, kotlinCoreEnvironment);
            }
        });
        return new AnalysisResultWithHasErrors(analyzerWithCompilerReport.getAnalysisResult(), analyzerWithCompilerReport.hasErrors());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MetadataPartProvider runCommonAnalysisIteration$lambda$0$0(KotlinCoreEnvironment kotlinCoreEnvironment, ModuleContent moduleContent) {
        moduleContent.getClass();
        return kotlinCoreEnvironment.createPackagePartProvider(moduleContent.getModuleContentScope());
    }
}
