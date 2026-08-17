package org.jetbrains.kotlin.incremental.util;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/incremental/util/ExceptionLocation;", "", "readableName", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getReadableName", "()Ljava/lang/String;", "INCREMENTAL_COMPILATION", "DAEMON", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum ExceptionLocation {
    INCREMENTAL_COMPILATION("Incremental compilation"),
    DAEMON("Daemon compilation");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String readableName;

    ExceptionLocation(String str) {
        this.readableName = str;
    }

    public static EnumEntries<ExceptionLocation> getEntries() {
        return $ENTRIES;
    }

    public final String getReadableName() {
        return this.readableName;
    }
}
