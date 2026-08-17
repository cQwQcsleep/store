package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\t\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/resolve/ForbiddenNamedArgumentsTarget;", Argument.Delimiters.none, "description", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "NON_KOTLIN_FUNCTION", "INVOKE_ON_FUNCTION_TYPE", "EXPECTED_CLASS_MEMBER", "toString", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum ForbiddenNamedArgumentsTarget {
    NON_KOTLIN_FUNCTION("non-Kotlin functions"),
    INVOKE_ON_FUNCTION_TYPE("function types"),
    EXPECTED_CLASS_MEMBER("members of expected classes");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String description;

    ForbiddenNamedArgumentsTarget(String str) {
        this.description = str;
    }

    public static EnumEntries<ForbiddenNamedArgumentsTarget> getEntries() {
        return $ENTRIES;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.description;
    }
}
