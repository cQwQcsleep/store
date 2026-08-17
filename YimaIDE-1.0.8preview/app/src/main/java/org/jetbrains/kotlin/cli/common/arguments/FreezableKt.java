package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0019\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u0001¢\u0006\u0002\u0010\u0003\u001a\u0019\u0010\u0004\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u0001¢\u0006\u0002\u0010\u0003\u001a\u0019\u0010\u0005\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u0001¢\u0006\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"copyOf", "T", "Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", "(Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;)Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", "frozen", "unfrozen", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FreezableKt {
    public static final <T extends Freezable> T copyOf(T t) {
        t.getClass();
        T t2 = (T) t.copyOfInternal$org_jetbrains_kotlin_cli_base();
        t2.getClass();
        return t2;
    }

    public static final <T extends Freezable> T frozen(T t) {
        t.getClass();
        T t2 = (T) t.getInstanceWithFreezeStatus$org_jetbrains_kotlin_cli_base(true);
        t2.getClass();
        return t2;
    }

    public static final <T extends Freezable> T unfrozen(T t) {
        t.getClass();
        T t2 = (T) t.getInstanceWithFreezeStatus$org_jetbrains_kotlin_cli_base(false);
        t2.getClass();
        return t2;
    }
}
