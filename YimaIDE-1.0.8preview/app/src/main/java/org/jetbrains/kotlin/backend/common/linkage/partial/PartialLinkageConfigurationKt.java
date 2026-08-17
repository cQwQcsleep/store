package org.jetbrains.kotlin.backend.common.linkage.partial;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.ArgumentUtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.CommonKlibBasedCompilerArguments;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.PartialLinkageConfig;
import org.jetbrains.kotlin.config.PartialLinkageLogLevel;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\"\u0010\t\u001a\u00020\n*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0002\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0002*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"PARTIAL_LINKAGE_CONFIGURATION", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "Lorg/jetbrains/kotlin/config/PartialLinkageConfig;", "getPARTIAL_LINKAGE_CONFIGURATION", "()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "partialLinkageConfig", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getPartialLinkageConfig", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/PartialLinkageConfig;", "setupPartialLinkageConfig", Argument.Delimiters.none, "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonKlibBasedCompilerArguments;", "warningDiagnosticFactory", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "errorDiagnosticFactory", "config", "org.jetbrains.kotlin:ir.serialization.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PartialLinkageConfigurationKt {
    private static final CompilerConfigurationKey<PartialLinkageConfig> PARTIAL_LINKAGE_CONFIGURATION = CompilerConfigurationKey.INSTANCE.create("PARTIAL_LINKAGE_CONFIGURATION");

    public static final CompilerConfigurationKey<PartialLinkageConfig> getPARTIAL_LINKAGE_CONFIGURATION() {
        return PARTIAL_LINKAGE_CONFIGURATION;
    }

    public static final PartialLinkageConfig getPartialLinkageConfig(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        PartialLinkageConfig partialLinkageConfig = (PartialLinkageConfig) compilerConfiguration.get(PARTIAL_LINKAGE_CONFIGURATION);
        return partialLinkageConfig == null ? PartialLinkageConfig.INSTANCE.getDEFAULT() : partialLinkageConfig;
    }

    public static final void setupPartialLinkageConfig(CompilerConfiguration compilerConfiguration, CommonKlibBasedCompilerArguments commonKlibBasedCompilerArguments, KtSourcelessDiagnosticFactory ktSourcelessDiagnosticFactory, KtSourcelessDiagnosticFactory ktSourcelessDiagnosticFactory2) {
        PartialLinkageLogLevel partialLinkageLogLevelResolveLogLevel;
        compilerConfiguration.getClass();
        commonKlibBasedCompilerArguments.getClass();
        ktSourcelessDiagnosticFactory.getClass();
        ktSourcelessDiagnosticFactory2.getClass();
        if (commonKlibBasedCompilerArguments.getPartialLinkageMode() != null) {
            CliDiagnosticReportingKt.report$default(compilerConfiguration, ktSourcelessDiagnosticFactory, "The " + ArgumentUtilsKt.getCliArgument(setupPartialLinkageConfig.1.INSTANCE) + " argument is deprecated. The partial linkage engine is always turned on.", null, 4, null);
        }
        String partialLinkageLogLevel = commonKlibBasedCompilerArguments.getPartialLinkageLogLevel();
        if (partialLinkageLogLevel != null) {
            PartialLinkageLogLevel.Companion companion = PartialLinkageLogLevel.INSTANCE;
            partialLinkageLogLevelResolveLogLevel = companion.resolveLogLevel(partialLinkageLogLevel);
            if (partialLinkageLogLevelResolveLogLevel == null) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, ktSourcelessDiagnosticFactory2, "Unknown value for parameter -Xpartial-linkage-loglevel: '" + partialLinkageLogLevel + "'. Value should be one of " + companion.availableValues(), null, 4, null);
                return;
            }
        } else {
            partialLinkageLogLevelResolveLogLevel = PartialLinkageLogLevel.INSTANCE.getDEFAULT();
        }
        setupPartialLinkageConfig(compilerConfiguration, new PartialLinkageConfig(partialLinkageLogLevelResolveLogLevel));
    }

    public static final void setupPartialLinkageConfig(CompilerConfiguration compilerConfiguration, PartialLinkageConfig partialLinkageConfig) {
        compilerConfiguration.getClass();
        partialLinkageConfig.getClass();
        compilerConfiguration.put(PARTIAL_LINKAGE_CONFIGURATION, partialLinkageConfig);
    }
}
