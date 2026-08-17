package org.jetbrains.kotlin.analysis.decompiler.psi.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/psi/text/TypeReferencePosition;", "", "<init>", "(Ljava/lang/String;I)V", "REGULAR", "DECLARATION_RECEIVER", "FUNCTION_TYPE_RECEIVER", "CONTEXT_RECEIVER", "org.jetbrains.kotlin:decompiler-to-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
enum TypeReferencePosition {
    REGULAR,
    DECLARATION_RECEIVER,
    FUNCTION_TYPE_RECEIVER,
    CONTEXT_RECEIVER;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<TypeReferencePosition> getEntries() {
        return $ENTRIES;
    }
}
