package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0004J\b\u0010\b\u001a\u00020\u0000H\u0014J\r\u0010\t\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\nJ\u0015\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\rJ\b\u0010\u0006\u001a\u00020\u0000H\u0007J\b\u0010\u000e\u001a\u00020\u0000H\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", Argument.Delimiters.none, "<init>", "()V", "checkFrozen", Argument.Delimiters.none, "frozen", Argument.Delimiters.none, "copyOf", "copyOfInternal", "copyOfInternal$org_jetbrains_kotlin_cli_base", "getInstanceWithFreezeStatus", "value", "getInstanceWithFreezeStatus$org_jetbrains_kotlin_cli_base", "unfrozen", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class Freezable {
    private boolean frozen;

    public final void checkFrozen() {
        if (this.frozen) {
            zia.a("Instance of ", Reflection.getOrCreateKotlinClass(getClass()), " is frozen");
        }
    }

    public Freezable copyOf() {
        return (Freezable) ArgumentUtilsKt.copyBean(this);
    }

    public final Freezable copyOfInternal$org_jetbrains_kotlin_cli_base() {
        return copyOf();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use type safe extension functions")
    public final /* synthetic */ Freezable frozen() {
        return getInstanceWithFreezeStatus$org_jetbrains_kotlin_cli_base(true);
    }

    public final Freezable getInstanceWithFreezeStatus$org_jetbrains_kotlin_cli_base(boolean value) {
        if (value == this.frozen) {
            return this;
        }
        Freezable freezableCopyOf = copyOf();
        freezableCopyOf.frozen = value;
        return freezableCopyOf;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use type safe extension functions")
    public final /* synthetic */ Freezable unfrozen() {
        return getInstanceWithFreezeStatus$org_jetbrains_kotlin_cli_base(false);
    }
}
