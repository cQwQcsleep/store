package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"copyCommonToolArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "from", "to", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonToolArgumentsCopyGeneratedKt {
    public static final CommonToolArguments copyCommonToolArguments(CommonToolArguments commonToolArguments, CommonToolArguments commonToolArguments2) {
        commonToolArguments.getClass();
        commonToolArguments2.getClass();
        commonToolArguments2.setAllWarningsAsErrors(commonToolArguments.getAllWarningsAsErrors());
        commonToolArguments2.setExtraHelp(commonToolArguments.getExtraHelp());
        commonToolArguments2.setExtraWarnings(commonToolArguments.getExtraWarnings());
        commonToolArguments2.setFreeArgs(commonToolArguments.getFreeArgs());
        commonToolArguments2.setHelp(commonToolArguments.getHelp());
        commonToolArguments2.setInternalArguments(commonToolArguments.getInternalArguments());
        commonToolArguments2.setSuppressWarnings(commonToolArguments.getSuppressWarnings());
        commonToolArguments2.setVerbose(commonToolArguments.getVerbose());
        commonToolArguments2.setVersion(commonToolArguments.getVersion());
        return commonToolArguments2;
    }
}
