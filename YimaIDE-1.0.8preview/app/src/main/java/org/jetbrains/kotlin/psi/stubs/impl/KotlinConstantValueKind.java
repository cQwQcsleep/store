package org.jetbrains.kotlin.psi.stubs.impl;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0015\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinConstantValueKind;", "", "<init>", "(Ljava/lang/String;I)V", "NULL", "BOOLEAN", "CHAR", "BYTE", "SHORT", "INT", "LONG", "DOUBLE", "FLOAT", "ENUM", "KCLASS", "STRING", "ARRAY", "UBYTE", "USHORT", "UINT", "ULONG", "ANNO", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum KotlinConstantValueKind {
    NULL,
    BOOLEAN,
    CHAR,
    BYTE,
    SHORT,
    INT,
    LONG,
    DOUBLE,
    FLOAT,
    ENUM,
    KCLASS,
    STRING,
    ARRAY,
    UBYTE,
    USHORT,
    UINT,
    ULONG,
    ANNO;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<KotlinConstantValueKind> getEntries() {
        return $ENTRIES;
    }
}
