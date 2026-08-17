package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirTowerDataMode;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "REGULAR", "NESTED_CLASS", "COMPANION_BLOCK", "COMPANION_OBJECT", "CONSTRUCTOR_HEADER", "ENUM_ENTRY", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum FirTowerDataMode {
    REGULAR,
    NESTED_CLASS,
    COMPANION_BLOCK,
    COMPANION_OBJECT,
    CONSTRUCTOR_HEADER,
    ENUM_ENTRY;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<FirTowerDataMode> getEntries() {
        return $ENTRIES;
    }
}
