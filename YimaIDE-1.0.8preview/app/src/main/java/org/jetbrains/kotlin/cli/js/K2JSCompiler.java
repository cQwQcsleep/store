package org.jetbrains.kotlin.cli.js;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.CLICompiler;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.pipeline.web.CommonJsConfigurationUpdater;
import org.jetbrains.kotlin.cli.pipeline.web.JsCliPipeline;
import org.jetbrains.kotlin.cli.pipeline.web.WasmCliPipeline;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.js.JsPlatforms;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0002H\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/cli/js/K2JSCompiler;", "Lorg/jetbrains/kotlin/cli/js/KotlinWebCompilerBase;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArguments;", "<init>", "()V", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "executableScriptFileName", Argument.Delimiters.none, "createArguments", "doExecutePhased", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "arguments", "services", "Lorg/jetbrains/kotlin/config/Services;", "basicMessageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "setupPlatformSpecificArgumentsAndServices", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "Companion", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JSCompiler extends KotlinWebCompilerBase<K2JSCompilerArguments> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final TargetPlatform platform = JsPlatforms.INSTANCE.getDefaultJsPlatform();

    @JvmStatic
    public static final void main(String[] strArr) {
        INSTANCE.main(strArr);
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public K2JSCompilerArguments createArguments() {
        return new K2JSCompilerArguments();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public ExitCode doExecutePhased(K2JSCompilerArguments arguments, Services services, MessageCollector basicMessageCollector) {
        arguments.getClass();
        services.getClass();
        basicMessageCollector.getClass();
        return arguments.getWasm() ? new WasmCliPipeline(getDefaultPerformanceManager()).execute(K2JSCompilerKt.toWasmArguments(arguments, basicMessageCollector), services, basicMessageCollector) : new JsCliPipeline(getDefaultPerformanceManager()).execute(arguments, services, basicMessageCollector);
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public String executableScriptFileName() {
        return "kotlinc-js";
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public TargetPlatform getPlatform() {
        return this.platform;
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public void setupPlatformSpecificArgumentsAndServices(CompilerConfiguration configuration, K2JSCompilerArguments arguments, Services services) {
        configuration.getClass();
        arguments.getClass();
        services.getClass();
        CommonJsConfigurationUpdater.INSTANCE.setupPlatformSpecificArgumentsAndServices$org_jetbrains_kotlin_cli_js(configuration, arguments, services);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007b\u0002\b\n¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/cli/js/K2JSCompiler$Companion;", Argument.Delimiters.none, "<init>", "()V", "main", Argument.Delimiters.none, "args", Argument.Delimiters.none, Argument.Delimiters.none, "([Ljava/lang/String;)V", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void main(String[] args) {
            args.getClass();
            CLICompiler.INSTANCE.doMain(new K2JSCompiler(), args);
        }

        private Companion() {
        }
    }
}
