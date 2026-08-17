package org.jetbrains.kotlin.cli.plugins;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/plugins/PluginOrderConstraint;", Argument.Delimiters.none, "before", Argument.Delimiters.none, "after", "rawArgument", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBefore", "()Ljava/lang/String;", "getAfter", "getRawArgument", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class PluginOrderConstraint {
    private final String after;
    private final String before;
    private final String rawArgument;

    public PluginOrderConstraint(String str, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.before = str;
        this.after = str2;
        this.rawArgument = str3;
    }

    public static /* synthetic */ PluginOrderConstraint copy$default(PluginOrderConstraint pluginOrderConstraint, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pluginOrderConstraint.before;
        }
        if ((i & 2) != 0) {
            str2 = pluginOrderConstraint.after;
        }
        if ((i & 4) != 0) {
            str3 = pluginOrderConstraint.rawArgument;
        }
        return pluginOrderConstraint.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getBefore() {
        return this.before;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAfter() {
        return this.after;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRawArgument() {
        return this.rawArgument;
    }

    public final PluginOrderConstraint copy(String before, String after, String rawArgument) {
        before.getClass();
        after.getClass();
        rawArgument.getClass();
        return new PluginOrderConstraint(before, after, rawArgument);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PluginOrderConstraint)) {
            return false;
        }
        PluginOrderConstraint pluginOrderConstraint = (PluginOrderConstraint) other;
        return Intrinsics.areEqual(this.before, pluginOrderConstraint.before) && Intrinsics.areEqual(this.after, pluginOrderConstraint.after) && Intrinsics.areEqual(this.rawArgument, pluginOrderConstraint.rawArgument);
    }

    public final String getAfter() {
        return this.after;
    }

    public final String getBefore() {
        return this.before;
    }

    public final String getRawArgument() {
        return this.rawArgument;
    }

    public int hashCode() {
        return (((this.before.hashCode() * 31) + this.after.hashCode()) * 31) + this.rawArgument.hashCode();
    }

    public String toString() {
        return "PluginOrderConstraint(before=" + this.before + ", after=" + this.after + ", rawArgument=" + this.rawArgument + ')';
    }
}
