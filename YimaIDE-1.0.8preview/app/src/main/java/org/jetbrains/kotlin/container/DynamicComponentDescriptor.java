package org.jetbrains.kotlin.container;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\n\u0010\u0006\u001a\u00020\u0007H\u0096\u0080\u0004¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/container/DynamicComponentDescriptor;", "Lorg/jetbrains/kotlin/container/ValueDescriptor;", "<init>", "()V", "getValue", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DynamicComponentDescriptor implements ValueDescriptor {
    public static final DynamicComponentDescriptor INSTANCE = new DynamicComponentDescriptor();

    private DynamicComponentDescriptor() {
    }

    @Override // org.jetbrains.kotlin.container.ValueDescriptor
    public Object getValue() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return "Dynamic";
    }
}
