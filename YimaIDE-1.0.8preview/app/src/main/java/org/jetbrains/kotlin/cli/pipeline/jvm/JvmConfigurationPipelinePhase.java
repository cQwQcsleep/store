package org.jetbrains.kotlin.cli.pipeline.jvm;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.common.modules.ModuleChunk;
import org.jetbrains.kotlin.cli.pipeline.AbstractConfigurationPhase;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationPipelineArtifact;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.modules.Module;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0002H\u0014¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmConfigurationPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/AbstractConfigurationPhase;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "<init>", "()V", "executePhase", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;", "input", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "createMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "versionArray", Argument.Delimiters.none, "provideCustomScriptingPluginOptions", Argument.Delimiters.none, Argument.Delimiters.none, "arguments", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmConfigurationPipelinePhase extends AbstractConfigurationPhase<K2JVMCompilerArguments> {
    public static final JvmConfigurationPipelinePhase INSTANCE = new JvmConfigurationPipelinePhase();

    private JvmConfigurationPipelinePhase() {
        super("JvmConfigurationPipelinePhase", null, SetsKt.setOf(CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE), CollectionsKt.listOf(JvmConfigurationUpdater.INSTANCE), 2, null);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractConfigurationPhase
    public BinaryVersion createMetadataVersion(int[] versionArray) {
        versionArray.getClass();
        return new MetadataVersion(Arrays.copyOf(versionArray, versionArray.length));
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractConfigurationPhase, org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public ConfigurationPipelineArtifact executePhase(ArgumentsPipelineArtifact<K2JVMCompilerArguments> input) {
        input.getClass();
        ConfigurationPipelineArtifact configurationPipelineArtifactExecutePhase = super.executePhase((ArgumentsPipelineArtifact) input);
        CompilerConfiguration configuration = configurationPipelineArtifactExecutePhase != null ? configurationPipelineArtifactExecutePhase.getConfiguration() : null;
        String str = configuration != null ? (String) configuration.get(CommonConfigurationKeys.DUMP_MODEL) : null;
        if (str != null) {
            JvmFrontendPipelinePhase jvmFrontendPipelinePhase = JvmFrontendPipelinePhase.INSTANCE;
            ModuleChunk moduleChunk = CLIConfigurationKeysKt.getModuleChunk(configuration);
            moduleChunk.getClass();
            List<Module> modules = moduleChunk.getModules();
            modules.getClass();
            jvmFrontendPipelinePhase.dumpModel(str, modules, configuration, input.getArguments());
        }
        return configurationPipelineArtifactExecutePhase;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractConfigurationPhase
    public List<String> provideCustomScriptingPluginOptions(K2JVMCompilerArguments arguments) {
        arguments.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        String[] scriptTemplates = arguments.getScriptTemplates();
        if (scriptTemplates != null) {
            if (!(scriptTemplates.length == 0)) {
                StringBuilder sb = new StringBuilder("plugin:kotlin.scripting:script-templates=");
                String[] scriptTemplates2 = arguments.getScriptTemplates();
                scriptTemplates2.getClass();
                sb.append(ArraysKt.joinToString$default(scriptTemplates2, Argument.Delimiters.default, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                listCreateListBuilder.add(sb.toString());
            }
        }
        String[] scriptResolverEnvironment = arguments.getScriptResolverEnvironment();
        if (scriptResolverEnvironment != null) {
            if (!(scriptResolverEnvironment.length == 0)) {
                StringBuilder sb2 = new StringBuilder("plugin:kotlin.scripting:script-resolver-environment=");
                String[] scriptResolverEnvironment2 = arguments.getScriptResolverEnvironment();
                scriptResolverEnvironment2.getClass();
                sb2.append(ArraysKt.joinToString$default(scriptResolverEnvironment2, Argument.Delimiters.default, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                listCreateListBuilder.add(sb2.toString());
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }
}
