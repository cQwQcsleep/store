package org.jetbrains.kotlin.backend.wasm.dwarf.lines;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/dwarf/lines/DwLines;", "", "opcode", "Lkotlin/UInt;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;II)V", "getOpcode-pVg5ArA", "()I", "I", "COPY", "ADVANCE_PC", "ADVANCE_LINE", "SET_FILE", "SET_COLUMN", "NEGATE_STMT", "SET_BASIC_BLOCK", "CONST_ADD_PC", "FIXED_ADVANCE_PC", "SET_PROLOGUE_END", "SET_EPILOGUE_BEGIN", "SET_ISA", "END_SEQUENCE", "SET_ADDRESS", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum DwLines {
    COPY(1),
    ADVANCE_PC(2),
    ADVANCE_LINE(3),
    SET_FILE(4),
    SET_COLUMN(5),
    NEGATE_STMT(6),
    SET_BASIC_BLOCK(7),
    CONST_ADD_PC(8),
    FIXED_ADVANCE_PC(9),
    SET_PROLOGUE_END(10),
    SET_EPILOGUE_BEGIN(11),
    SET_ISA(12),
    END_SEQUENCE(1),
    SET_ADDRESS(2);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int opcode;

    DwLines(int i) {
        this.opcode = i;
    }

    public static EnumEntries<DwLines> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: getOpcode-pVg5ArA, reason: not valid java name and from getter */
    public final int getOpcode() {
        return this.opcode;
    }
}
