package org.jetbrains.kotlin.compiler.plugin;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.FrontendConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.extensions.ExtensionPointDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006¨\u0006\u0007"}, d2 = {"getCompilerExtensions", Argument.Delimiters.none, "T", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "descriptor", "Lorg/jetbrains/kotlin/extensions/ExtensionPointDescriptor;", "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExtensionPointUtilsKt {
    public static final <T> List<T> getCompilerExtensions(CompilerConfiguration compilerConfiguration, ExtensionPointDescriptor<T> extensionPointDescriptor) {
        compilerConfiguration.getClass();
        extensionPointDescriptor.getClass();
        CompilerPluginRegistrar.ExtensionStorage extensionsStorage = FrontendConfigurationKeysKt.getExtensionsStorage(compilerConfiguration);
        if (extensionsStorage != null) {
            return extensionsStorage.get(extensionPointDescriptor);
        }
        k2d.a("Extensions storage is not registered");
        return null;
    }
}
