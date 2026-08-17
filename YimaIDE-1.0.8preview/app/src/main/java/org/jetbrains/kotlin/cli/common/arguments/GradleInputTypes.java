package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/GradleInputTypes;", Argument.Delimiters.none, "gradleType", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getGradleType", "()Ljava/lang/String;", "INPUT", "INTERNAL", "org.jetbrains.kotlin:arguments.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum GradleInputTypes {
    INPUT("org.gradle.api.tasks.Input"),
    INTERNAL("org.gradle.api.tasks.Internal");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String gradleType;

    GradleInputTypes(String str) {
        this.gradleType = str;
    }

    public static EnumEntries<GradleInputTypes> getEntries() {
        return $ENTRIES;
    }

    public final String getGradleType() {
        return this.gradleType;
    }
}
