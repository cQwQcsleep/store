package org.jetbrains.kotlin.cli;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtRegisteredDiagnosticFactoriesStorage;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/cli/FrontendConfigurationKeys;", Argument.Delimiters.none, "<init>", "()V", "DIAGNOSTIC_FACTORIES_STORAGE", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "Lorg/jetbrains/kotlin/diagnostics/KtRegisteredDiagnosticFactoriesStorage;", "Lkotlin/jvm/JvmField;", "EXTENSIONS_STORAGE", "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;", "getEXTENSIONS_STORAGE$annotations", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FrontendConfigurationKeys {
    public static final CompilerConfigurationKey<KtRegisteredDiagnosticFactoriesStorage> DIAGNOSTIC_FACTORIES_STORAGE;
    public static final CompilerConfigurationKey<CompilerPluginRegistrar.ExtensionStorage> EXTENSIONS_STORAGE;
    public static final FrontendConfigurationKeys INSTANCE = new FrontendConfigurationKeys();

    static {
        CompilerConfigurationKey.Companion companion = CompilerConfigurationKey.INSTANCE;
        DIAGNOSTIC_FACTORIES_STORAGE = companion.create("DIAGNOSTIC_FACTORIES_STORAGE");
        EXTENSIONS_STORAGE = companion.create("EXTENSIONS_STORAGE");
    }

    private FrontendConfigurationKeys() {
    }

    public static /* synthetic */ void getEXTENSIONS_STORAGE$annotations() {
    }
}
