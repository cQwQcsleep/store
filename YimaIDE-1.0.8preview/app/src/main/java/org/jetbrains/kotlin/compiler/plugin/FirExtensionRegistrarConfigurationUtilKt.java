package org.jetbrains.kotlin.compiler.plugin;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a#\u0010\u0006\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"getCompilerExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "descriptor", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar$Companion;", "registerExtension", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;", "storage", "extension", "(Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar$Companion;Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;)V", "org.jetbrains.kotlin:entrypoint"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExtensionRegistrarConfigurationUtilKt {
    public static final List<FirExtensionRegistrar> getCompilerExtensions(CompilerConfiguration compilerConfiguration, FirExtensionRegistrar.Companion companion) {
        compilerConfiguration.getClass();
        companion.getClass();
        List<FirExtensionRegistrar> compilerExtensions = ExtensionPointUtilsKt.getCompilerExtensions(compilerConfiguration, FirExtensionRegistrarAdapter.INSTANCE);
        compilerExtensions.getClass();
        return compilerExtensions;
    }

    public static final void registerExtension(CompilerPluginRegistrar.ExtensionStorage extensionStorage, FirExtensionRegistrar.Companion companion, FirExtensionRegistrar firExtensionRegistrar) {
        extensionStorage.getClass();
        companion.getClass();
        firExtensionRegistrar.getClass();
        extensionStorage.registerExtension(FirExtensionRegistrarAdapter.INSTANCE, firExtensionRegistrar);
    }
}
