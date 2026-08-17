package org.jetbrains.kotlin.container;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u00020\u0007H\u0096\u0080\u0004¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/container/DefaultInstanceComponentDescriptor;", "Lorg/jetbrains/kotlin/container/InstanceComponentDescriptor;", "instance", Argument.Delimiters.none, "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DefaultInstanceComponentDescriptor extends InstanceComponentDescriptor {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultInstanceComponentDescriptor(Object obj) {
        super(obj);
        obj.getClass();
    }

    @Override // org.jetbrains.kotlin.container.InstanceComponentDescriptor
    public String toString() {
        return "Default instance: ".concat(getInstance().getClass().getSimpleName());
    }
}
