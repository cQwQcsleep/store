package org.jetbrains.kotlin.cli;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtRegisteredDiagnosticFactoriesStorage;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.diagnostics.impl.DiagnosticsCollectorImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0007\u001a\f\u0010\t\u001a\u00020\b*\u00020\u0001H\u0002¨\u0006\n"}, d2 = {CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "Lorg/jetbrains/kotlin/config/CompilerConfiguration$Companion;", "diagnosticsCollector", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "registerExtensionStorage", Argument.Delimiters.none, "initializeDiagnosticFactoriesStorageForCli", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompilerConfigurationCreationKt {
    public static final CompilerConfiguration create(CompilerConfiguration.Companion companion, BaseDiagnosticsCollector baseDiagnosticsCollector, MessageCollector messageCollector) {
        companion.getClass();
        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        registerExtensionStorage(compilerConfiguration);
        initializeDiagnosticFactoriesStorageForCli(compilerConfiguration);
        if (baseDiagnosticsCollector == null) {
            baseDiagnosticsCollector = new DiagnosticsCollectorImpl();
        }
        CLIConfigurationKeysKt.setDiagnosticsCollector(compilerConfiguration, baseDiagnosticsCollector);
        if (messageCollector != null) {
            CommonConfigurationKeysKt.setMessageCollector(compilerConfiguration, messageCollector);
        }
        return compilerConfiguration;
    }

    public static /* synthetic */ CompilerConfiguration create$default(CompilerConfiguration.Companion companion, BaseDiagnosticsCollector baseDiagnosticsCollector, MessageCollector messageCollector, int i, Object obj) {
        if ((i & 1) != 0) {
            baseDiagnosticsCollector = null;
        }
        if ((i & 2) != 0) {
            messageCollector = null;
        }
        return create(companion, baseDiagnosticsCollector, messageCollector);
    }

    private static final void initializeDiagnosticFactoriesStorageForCli(CompilerConfiguration compilerConfiguration) {
        KtRegisteredDiagnosticFactoriesStorage ktRegisteredDiagnosticFactoriesStorage = new KtRegisteredDiagnosticFactoriesStorage();
        ktRegisteredDiagnosticFactoriesStorage.registerDiagnosticContainers(CliDiagnostics.INSTANCE);
        FrontendConfigurationKeysKt.setDiagnosticFactoriesStorage(compilerConfiguration, ktRegisteredDiagnosticFactoriesStorage);
    }

    @CompilerConfiguration.Internals(message = "Consider using `CompilerConfiguration.Companion.create()` which registers default services")
    public static final void registerExtensionStorage(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        FrontendConfigurationKeysKt.setExtensionsStorage(compilerConfiguration, new CompilerPluginRegistrar.ExtensionStorage());
    }

    public static final CompilerConfiguration create(CompilerConfiguration.Companion companion, BaseDiagnosticsCollector baseDiagnosticsCollector) {
        companion.getClass();
        return create$default(companion, baseDiagnosticsCollector, null, 2, null);
    }

    public static final CompilerConfiguration create(CompilerConfiguration.Companion companion) {
        companion.getClass();
        return create$default(companion, null, null, 3, null);
    }
}
