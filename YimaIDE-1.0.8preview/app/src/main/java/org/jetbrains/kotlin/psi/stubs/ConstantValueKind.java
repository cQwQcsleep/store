package org.jetbrains.kotlin.psi.stubs;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/ConstantValueKind;", "", "<init>", "(Ljava/lang/String;I)V", "NULL", "BOOLEAN_CONSTANT", "FLOAT_CONSTANT", "CHARACTER_CONSTANT", "INTEGER_CONSTANT", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum ConstantValueKind {
    NULL,
    BOOLEAN_CONSTANT,
    FLOAT_CONSTANT,
    CHARACTER_CONSTANT,
    INTEGER_CONSTANT;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<ConstantValueKind> getEntries() {
        return $ENTRIES;
    }
}
