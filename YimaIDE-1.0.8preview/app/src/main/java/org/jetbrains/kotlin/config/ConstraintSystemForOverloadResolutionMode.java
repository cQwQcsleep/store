package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007j\u0002\b\u0004j\u0002\b\u0005¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/config/ConstraintSystemForOverloadResolutionMode;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "CONSTRAINT_SYSTEM_FOR_OLD_INFERENCE", "CONSTRAINT_SYSTEM_FOR_NEW_INFERENCE", "forNewInference", Argument.Delimiters.none, "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum ConstraintSystemForOverloadResolutionMode {
    CONSTRAINT_SYSTEM_FOR_OLD_INFERENCE,
    CONSTRAINT_SYSTEM_FOR_NEW_INFERENCE;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<ConstraintSystemForOverloadResolutionMode> getEntries() {
        return $ENTRIES;
    }

    public final boolean forNewInference() {
        return this == CONSTRAINT_SYSTEM_FOR_NEW_INFERENCE;
    }
}
