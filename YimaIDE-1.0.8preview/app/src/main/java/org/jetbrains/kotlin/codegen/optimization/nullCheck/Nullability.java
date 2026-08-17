package org.jetbrains.kotlin.codegen.optimization.nullCheck;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/Nullability;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "NULL", "NOT_NULL", "NULLABLE", "isNull", Argument.Delimiters.none, "isNotNull", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum Nullability {
    NULL,
    NOT_NULL,
    NULLABLE;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<Nullability> getEntries() {
        return $ENTRIES;
    }

    public final boolean isNotNull() {
        return this == NOT_NULL;
    }

    public final boolean isNull() {
        return this == NULL;
    }
}
