package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u000e\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"COMPOSE_PLUGIN_ID", "", "featureFlagName", "useFeatureFlagInsteadMessage", "feature", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlag;", "oldOptionDeprecationWarning", "", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "oldOption", "Lorg/jetbrains/kotlin/compiler/plugin/AbstractCliOption;", "validateFeatureFlag", "value", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ComposePluginKt {
    public static final String COMPOSE_PLUGIN_ID = "androidx.compose.compiler.plugins.kotlin";

    public static final String featureFlagName() {
        return "plugin:androidx.compose.compiler.plugins.kotlin:" + ComposeCommandLineProcessor.INSTANCE.getFEATURE_FLAG_OPTION().getOptionName();
    }

    public static final void oldOptionDeprecationWarning(CompilerConfiguration compilerConfiguration, AbstractCliOption abstractCliOption, FeatureFlag featureFlag) {
        compilerConfiguration.getClass();
        abstractCliOption.getClass();
        featureFlag.getClass();
        MessageCollector.report$default(CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration), CompilerMessageSeverity.WARNING, abstractCliOption.getOptionName() + " is deprecated. " + useFeatureFlagInsteadMessage(featureFlag), (CompilerMessageSourceLocation) null, 4, (Object) null);
    }

    public static final String useFeatureFlagInsteadMessage(FeatureFlag featureFlag) {
        featureFlag.getClass();
        return "Use " + featureFlagName() + '=' + featureFlag.getFeatureName() + " instead";
    }

    public static final void validateFeatureFlag(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        str.getClass();
        if (((FeatureFlag) FeatureFlag.INSTANCE.fromString(str).component1()) == null) {
            MessageCollector.report$default(CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration), CompilerMessageSeverity.WARNING, featureFlagName() + " contains an unrecognized feature name: " + str + '.', (CompilerMessageSourceLocation) null, 4, (Object) null);
        }
    }
}
