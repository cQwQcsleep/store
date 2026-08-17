package org.jetbrains.kotlin.modules;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"TargetId", "Lorg/jetbrains/kotlin/modules/TargetId;", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/modules/Module;", "org.jetbrains.kotlin:util"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TargetIdKt {
    public static final TargetId TargetId(Module module) {
        module.getClass();
        return new TargetId(module.getModuleName(), module.getModuleType());
    }
}
