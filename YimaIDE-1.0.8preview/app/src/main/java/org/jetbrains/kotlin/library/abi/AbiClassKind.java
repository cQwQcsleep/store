package org.jetbrains.kotlin.library.abi;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\n\u0002\u0018\u0002\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bÊ\u0001\u0002\b\n¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/AbiClassKind;", "", "<init>", "(Ljava/lang/String;I)V", "CLASS", "INTERFACE", "OBJECT", "ENUM_CLASS", "ANNOTATION_CLASS", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@ExperimentalLibraryAbiReader
public enum AbiClassKind {
    CLASS,
    INTERFACE,
    OBJECT,
    ENUM_CLASS,
    ANNOTATION_CLASS;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<AbiClassKind> getEntries() {
        return $ENTRIES;
    }
}
