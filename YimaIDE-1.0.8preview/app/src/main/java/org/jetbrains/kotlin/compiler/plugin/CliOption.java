package org.jetbrains.kotlin.compiler.plugin;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/compiler/plugin/CliOption;", "Lorg/jetbrains/kotlin/compiler/plugin/AbstractCliOption;", "optionName", Argument.Delimiters.none, "valueDescription", "description", "required", Argument.Delimiters.none, "allowMultipleOccurrences", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getOptionName", "()Ljava/lang/String;", "getValueDescription", "getDescription", "getRequired", "()Z", "getAllowMultipleOccurrences", "org.jetbrains.kotlin:plugin-api"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliOption implements AbstractCliOption {
    private final boolean allowMultipleOccurrences;
    private final String description;
    private final String optionName;
    private final boolean required;
    private final String valueDescription;

    public CliOption(String str, String str2, String str3, boolean z, boolean z2) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.optionName = str;
        this.valueDescription = str2;
        this.description = str3;
        this.required = z;
        this.allowMultipleOccurrences = z2;
    }

    @Override // org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
    public boolean getAllowMultipleOccurrences() {
        return this.allowMultipleOccurrences;
    }

    @Override // org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
    public String getDescription() {
        return this.description;
    }

    @Override // org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
    public String getOptionName() {
        return this.optionName;
    }

    @Override // org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
    public boolean getRequired() {
        return this.required;
    }

    @Override // org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
    public String getValueDescription() {
        return this.valueDescription;
    }

    public /* synthetic */ CliOption(String str, String str2, String str3, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? true : z, (i & 16) != 0 ? false : z2);
    }
}
