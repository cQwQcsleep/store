package org.jetbrains.kotlin.cli;

import kotlin.Metadata;
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtRegisteredDiagnosticFactoriesStorage;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\",\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"2\u0010\t\u001a\u0004\u0018\u00010\b*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\u0012\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"value", "Lorg/jetbrains/kotlin/diagnostics/KtRegisteredDiagnosticFactoriesStorage;", "diagnosticFactoriesStorage", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getDiagnosticFactoriesStorage", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/diagnostics/KtRegisteredDiagnosticFactoriesStorage;", "setDiagnosticFactoriesStorage", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/diagnostics/KtRegisteredDiagnosticFactoriesStorage;)V", "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;", "extensionsStorage", "getExtensionsStorage$annotations", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getExtensionsStorage", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;", "setExtensionsStorage", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;)V", "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FrontendConfigurationKeysKt {
    public static final KtRegisteredDiagnosticFactoriesStorage getDiagnosticFactoriesStorage(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (KtRegisteredDiagnosticFactoriesStorage) compilerConfiguration.get(FrontendConfigurationKeys.DIAGNOSTIC_FACTORIES_STORAGE);
    }

    public static final CompilerPluginRegistrar.ExtensionStorage getExtensionsStorage(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (CompilerPluginRegistrar.ExtensionStorage) compilerConfiguration.get(FrontendConfigurationKeys.EXTENSIONS_STORAGE);
    }

    public static /* synthetic */ void getExtensionsStorage$annotations(CompilerConfiguration compilerConfiguration) {
    }

    public static final void setDiagnosticFactoriesStorage(CompilerConfiguration compilerConfiguration, KtRegisteredDiagnosticFactoriesStorage ktRegisteredDiagnosticFactoriesStorage) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<KtRegisteredDiagnosticFactoriesStorage> compilerConfigurationKey = FrontendConfigurationKeys.DIAGNOSTIC_FACTORIES_STORAGE;
        if (ktRegisteredDiagnosticFactoriesStorage != null) {
            compilerConfiguration.put(compilerConfigurationKey, ktRegisteredDiagnosticFactoriesStorage);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setExtensionsStorage(CompilerConfiguration compilerConfiguration, CompilerPluginRegistrar.ExtensionStorage extensionStorage) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<CompilerPluginRegistrar.ExtensionStorage> compilerConfigurationKey = FrontendConfigurationKeys.EXTENSIONS_STORAGE;
        if (extensionStorage != null) {
            compilerConfiguration.put(compilerConfigurationKey, extensionStorage);
        } else {
            w01.a("nullable values are not allowed");
        }
    }
}
