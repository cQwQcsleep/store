package org.jetbrains.kotlin.backend.wasm.dwarf.entries;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.eclipse.jdt.internal.compiler.codegen.Opcodes;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/dwarf/entries/UnitHeader;", "", "opcode", "Lkotlin/UByte;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;IB)V", "getOpcode-w2LRezQ", "()B", "B", "COMPILE", "TYPE", "PARTIAL", "SKELETON", "SPLIT_COMPILE", "SPLIT_TYPE", "LO_USER", "HI_USER", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum UnitHeader {
    COMPILE((byte) 1),
    TYPE((byte) 2),
    PARTIAL((byte) 3),
    SKELETON((byte) 4),
    SPLIT_COMPILE((byte) 5),
    SPLIT_TYPE((byte) 6),
    LO_USER(Opcodes.OPC_ior),
    HI_USER((byte) -1);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final byte opcode;

    UnitHeader(byte b) {
        this.opcode = b;
    }

    public static EnumEntries<UnitHeader> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: getOpcode-w2LRezQ, reason: not valid java name and from getter */
    public final byte getOpcode() {
        return this.opcode;
    }
}
