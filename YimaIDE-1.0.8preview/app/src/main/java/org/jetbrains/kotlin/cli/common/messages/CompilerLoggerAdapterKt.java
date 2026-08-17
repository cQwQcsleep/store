package org.jetbrains.kotlin.cli.common.messages;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"getLogger", "Lorg/jetbrains/kotlin/util/Logger;", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "treatWarningsAsErrors", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompilerLoggerAdapterKt {
    public static final Logger getLogger(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        return new CompilerLoggerAdapter(CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration), z);
    }

    public static /* synthetic */ Logger getLogger$default(CompilerConfiguration compilerConfiguration, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return getLogger(compilerConfiguration, z);
    }
}
