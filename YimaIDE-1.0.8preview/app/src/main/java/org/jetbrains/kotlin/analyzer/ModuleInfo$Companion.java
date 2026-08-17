package org.jetbrains.kotlin.analyzer;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleCapability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/ModuleInfo$Companion;", Argument.Delimiters.none, "<init>", "()V", "Capability", "Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "getCapability", "()Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ModuleInfo$Companion {
    static final /* synthetic */ ModuleInfo$Companion $$INSTANCE = new ModuleInfo$Companion();
    private static final ModuleCapability<ModuleInfo> Capability = new ModuleCapability<>("ModuleInfo");

    private ModuleInfo$Companion() {
    }

    public final ModuleCapability<ModuleInfo> getCapability() {
        return Capability;
    }
}
