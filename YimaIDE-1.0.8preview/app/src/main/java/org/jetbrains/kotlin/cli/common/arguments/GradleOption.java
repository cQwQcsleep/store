package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/GradleOption;", Argument.Delimiters.none, "value", "Lorg/jetbrains/kotlin/cli/common/arguments/DefaultValue;", "gradleInputType", "Lorg/jetbrains/kotlin/cli/common/arguments/GradleInputTypes;", "shouldGenerateDeprecatedKotlinOptions", Argument.Delimiters.none, "gradleName", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/cli/common/arguments/DefaultValue;Lorg/jetbrains/kotlin/cli/common/arguments/GradleInputTypes;ZLjava/lang/String;)V", "getValue", "()Lorg/jetbrains/kotlin/cli/common/arguments/DefaultValue;", "getGradleInputType", "()Lorg/jetbrains/kotlin/cli/common/arguments/GradleInputTypes;", "getShouldGenerateDeprecatedKotlinOptions", "()Z", "getGradleName", "()Ljava/lang/String;", "org.jetbrains.kotlin:arguments.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class GradleOption {
    private final GradleInputTypes gradleInputType;
    private final String gradleName;
    private final boolean shouldGenerateDeprecatedKotlinOptions;
    private final DefaultValue value;

    public GradleOption(DefaultValue defaultValue, GradleInputTypes gradleInputTypes, boolean z, String str) {
        defaultValue.getClass();
        gradleInputTypes.getClass();
        str.getClass();
        this.value = defaultValue;
        this.gradleInputType = gradleInputTypes;
        this.shouldGenerateDeprecatedKotlinOptions = z;
        this.gradleName = str;
    }

    public final GradleInputTypes getGradleInputType() {
        return this.gradleInputType;
    }

    public final String getGradleName() {
        return this.gradleName;
    }

    public final boolean getShouldGenerateDeprecatedKotlinOptions() {
        return this.shouldGenerateDeprecatedKotlinOptions;
    }

    public final DefaultValue getValue() {
        return this.value;
    }

    public /* synthetic */ GradleOption(DefaultValue defaultValue, GradleInputTypes gradleInputTypes, boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(defaultValue, gradleInputTypes, (i & 4) != 0 ? false : z, (i & 8) != 0 ? Argument.Delimiters.none : str);
    }
}
