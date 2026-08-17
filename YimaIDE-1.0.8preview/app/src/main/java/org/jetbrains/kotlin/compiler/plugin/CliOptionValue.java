package org.jetbrains.kotlin.compiler.plugin;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/compiler/plugin/CliOptionValue;", Argument.Delimiters.none, "pluginId", Argument.Delimiters.none, "optionName", "value", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPluginId", "()Ljava/lang/String;", "getOptionName", "getValue", "toString", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:plugin-api"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class CliOptionValue {
    private final String optionName;
    private final String pluginId;
    private final String value;

    public CliOptionValue(String str, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.pluginId = str;
        this.optionName = str2;
        this.value = str3;
    }

    public static /* synthetic */ CliOptionValue copy$default(CliOptionValue cliOptionValue, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cliOptionValue.pluginId;
        }
        if ((i & 2) != 0) {
            str2 = cliOptionValue.optionName;
        }
        if ((i & 4) != 0) {
            str3 = cliOptionValue.value;
        }
        return cliOptionValue.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPluginId() {
        return this.pluginId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getOptionName() {
        return this.optionName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    public final CliOptionValue copy(String pluginId, String optionName, String value) {
        pluginId.getClass();
        optionName.getClass();
        value.getClass();
        return new CliOptionValue(pluginId, optionName, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CliOptionValue)) {
            return false;
        }
        CliOptionValue cliOptionValue = (CliOptionValue) other;
        return Intrinsics.areEqual(this.pluginId, cliOptionValue.pluginId) && Intrinsics.areEqual(this.optionName, cliOptionValue.optionName) && Intrinsics.areEqual(this.value, cliOptionValue.value);
    }

    public final String getOptionName() {
        return this.optionName;
    }

    public final String getPluginId() {
        return this.pluginId;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return (((this.pluginId.hashCode() * 31) + this.optionName.hashCode()) * 31) + this.value.hashCode();
    }

    public String toString() {
        return this.pluginId + ':' + this.optionName + '=' + this.value;
    }
}
