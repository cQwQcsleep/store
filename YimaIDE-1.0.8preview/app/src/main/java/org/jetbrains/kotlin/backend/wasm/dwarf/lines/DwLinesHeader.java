package org.jetbrains.kotlin.backend.wasm.dwarf.lines;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/dwarf/lines/DwLinesHeader;", "", "opcode", "Lkotlin/UInt;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;II)V", "getOpcode-pVg5ArA", "()I", "I", "PATH", "DIRECTORY_INDEX", "TIMESTAMP", "SIZE", "MD5", "LO_USER", "HI_USER", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum DwLinesHeader {
    PATH(1),
    DIRECTORY_INDEX(2),
    TIMESTAMP(3),
    SIZE(4),
    MD5(5),
    LO_USER(8192),
    HI_USER(16383);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int opcode;

    DwLinesHeader(int i) {
        this.opcode = i;
    }

    public static EnumEntries<DwLinesHeader> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: getOpcode-pVg5ArA, reason: not valid java name and from getter */
    public final int getOpcode() {
        return this.opcode;
    }
}
