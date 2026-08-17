package org.jetbrains.kotlin.backend.wasm.dwarf;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b1\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/dwarf/DwForm;", "", "opcode", "Lkotlin/UInt;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;II)V", "getOpcode-pVg5ArA", "()I", "I", "ADDR", "BLOCK2", "BLOCK4", "DATA2", "DATA4", "DATA8", "STRING", "BLOCK", "BLOCK1", "DATA1", "FLAG", "SDATA", "STRP", "UDATA", "REF_ADDR", "REF1", "REF2", "REF4", "REF8", "REF_UDATA", "INDIRECT", "SEC_OFFSET", "EXPRLOC", "FLAG_PRESENT", "STRX", "ADDRX", "REF_SUP4", "STRP_SUP", "DATA16", "LINE_STRP", "REF_SIG8", "IMPLICIT_CONST", "LOCLISTX", "RNGLISTX", "REF_SUP8", "STRX1", "STRX2", "STRX3", "STRX4", "ADDRX1", "ADDRX2", "ADDRX3", "ADDRX4", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum DwForm {
    ADDR(1),
    BLOCK2(3),
    BLOCK4(4),
    DATA2(5),
    DATA4(6),
    DATA8(7),
    STRING(8),
    BLOCK(9),
    BLOCK1(10),
    DATA1(11),
    FLAG(12),
    SDATA(13),
    STRP(14),
    UDATA(15),
    REF_ADDR(16),
    REF1(17),
    REF2(18),
    REF4(19),
    REF8(20),
    REF_UDATA(21),
    INDIRECT(22),
    SEC_OFFSET(23),
    EXPRLOC(24),
    FLAG_PRESENT(25),
    STRX(26),
    ADDRX(27),
    REF_SUP4(28),
    STRP_SUP(29),
    DATA16(30),
    LINE_STRP(31),
    REF_SIG8(32),
    IMPLICIT_CONST(33),
    LOCLISTX(34),
    RNGLISTX(35),
    REF_SUP8(36),
    STRX1(37),
    STRX2(38),
    STRX3(39),
    STRX4(40),
    ADDRX1(41),
    ADDRX2(42),
    ADDRX3(43),
    ADDRX4(44);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int opcode;

    DwForm(int i) {
        this.opcode = i;
    }

    public static EnumEntries<DwForm> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: getOpcode-pVg5ArA, reason: not valid java name and from getter */
    public final int getOpcode() {
        return this.opcode;
    }
}
