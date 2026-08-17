package org.jetbrains.kotlin.backend.wasm.dwarf.entries;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u007f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bgj\u0002\bhj\u0002\bij\u0002\bjj\u0002\bkj\u0002\blj\u0002\bmj\u0002\bnj\u0002\boj\u0002\bpj\u0002\bqj\u0002\brj\u0002\bsj\u0002\btj\u0002\buj\u0002\bvj\u0002\bwj\u0002\bxj\u0002\byj\u0002\bzj\u0002\b{j\u0002\b|j\u0002\b}j\u0002\b~j\u0002\b\u007fj\u0003\b\u0080\u0001j\u0003\b\u0081\u0001¨\u0006\u0082\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/dwarf/entries/DwAttribute;", "", "opcode", "Lkotlin/UInt;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;II)V", "getOpcode-pVg5ArA", "()I", "I", "SIBLING", "LOCATION", "NAME", "ORDERING", "BYTE_SIZE", "BIT_SIZE", "STMT_LIST", "LOW_PC", "HIGH_PC", "LANGUAGE", "DISCR", "DISCR_VALUE", "VISIBILITY", "IMPORT", "STRING_LENGTH", "COMMON_REFERENCE", "COMP_DIR", "CONST_VALUE", "CONTAINING_TYPE", "DEFAULT_VALUE", "INLINE", "IS_OPTIONAL", "LOWER_BOUND", "PRODUCER", "PROTOTYPED", "RETURN_ADDR", "START_SCOPE", "BIT_STRIDE", "UPPER_BOUND", "ABSTRACT_ORIGIN", "ACCESSIBILITY", "ADDRESS_CLASS", "ARTIFICIAL", "BASE_TYPES", "CALLING_CONVENTION", "COUNT", "DATA_MEMBER_LOCATION", "DECL_COLUMN", "DECL_FILE", "DECL_LINE", "DECLARATION", "DISCR_LIST", "ENCODING", "EXTERNAL", "FRAME_BASE", "FRIEND", "IDENTIFIER_CASE", "NAMELIST_ITEM", "PRIORITY", "SEGMENT", "SPECIFICATION", "STATIC_LINK", "TYPE", "USE_LOCATION", "VARIABLE_PARAMETER", "VIRTUALITY", "VTABLE_ELEM_LOCATION", "ALLOCATED", "ASSOCIATED", "DATA_LOCATION", "BYTE_STRIDE", "ENTRY_PC", "USE_UTF8", "EXTENSION", "RANGES", "TRAMPOLINE", "CALL_COLUMN", "CALL_FILE", "CALL_LINE", "DESCRIPTION", "BINARY_SCALE", "DECIMAL_SCALE", "SMALL", "DECIMAL_SIGN", "DIGIT_COUNT", "PICTURE_STRING", "MUTABLE", "THREADS_SCALED", "EXPLICIT", "OBJECT_POINTER", "ENDIANITY", "ELEMENTAL", "PURE", "RECURSIVE", "SIGNATURE", "MAIN_SUBPROGRAM", "DATA_BIT_OFFSET", "CONST_EXPR", "ENUM_CLASS", "LINKAGE_NAME", "STRING_LENGTH_BIT_SIZE", "STRING_LENGTH_BYTE_SIZE", "RANK", "STR_OFFSETS_BASE", "ADDR_BASE", "RNGLISTS_BASE", "DWO_NAME", "REFERENCE", "RVALUE_REFERENCE", "MACROS", "CALL_ALL_CALLS", "CALL_ALL_SOURCE_CALLS", "CALL_ALL_TAIL_CALLS", "CALL_RETURN_PC", "CALL_VALUE", "CALL_ORIGIN", "CALL_PARAMETER", "CALL_PC", "CALL_TAIL_CALL", "CALL_TARGET", "CALL_TARGET_CLOBBERED", "CALL_DATA_LOCATION", "CALL_DATA_VALUE", "NORETURN", "ALIGNMENT", "EXPORT_SYMBOLS", "DELETED", "DEFAULTED", "LOCLISTS_BASE", "LO_USER", "HI_USER", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum DwAttribute {
    SIBLING(1),
    LOCATION(2),
    NAME(3),
    ORDERING(9),
    BYTE_SIZE(11),
    BIT_SIZE(13),
    STMT_LIST(16),
    LOW_PC(17),
    HIGH_PC(18),
    LANGUAGE(19),
    DISCR(21),
    DISCR_VALUE(22),
    VISIBILITY(23),
    IMPORT(24),
    STRING_LENGTH(25),
    COMMON_REFERENCE(26),
    COMP_DIR(27),
    CONST_VALUE(28),
    CONTAINING_TYPE(29),
    DEFAULT_VALUE(30),
    INLINE(32),
    IS_OPTIONAL(33),
    LOWER_BOUND(34),
    PRODUCER(37),
    PROTOTYPED(39),
    RETURN_ADDR(42),
    START_SCOPE(44),
    BIT_STRIDE(46),
    UPPER_BOUND(47),
    ABSTRACT_ORIGIN(49),
    ACCESSIBILITY(50),
    ADDRESS_CLASS(51),
    ARTIFICIAL(52),
    BASE_TYPES(53),
    CALLING_CONVENTION(54),
    COUNT(55),
    DATA_MEMBER_LOCATION(56),
    DECL_COLUMN(57),
    DECL_FILE(58),
    DECL_LINE(59),
    DECLARATION(60),
    DISCR_LIST(61),
    ENCODING(62),
    EXTERNAL(63),
    FRAME_BASE(64),
    FRIEND(65),
    IDENTIFIER_CASE(66),
    NAMELIST_ITEM(68),
    PRIORITY(69),
    SEGMENT(70),
    SPECIFICATION(71),
    STATIC_LINK(72),
    TYPE(73),
    USE_LOCATION(74),
    VARIABLE_PARAMETER(75),
    VIRTUALITY(76),
    VTABLE_ELEM_LOCATION(77),
    ALLOCATED(78),
    ASSOCIATED(79),
    DATA_LOCATION(80),
    BYTE_STRIDE(81),
    ENTRY_PC(82),
    USE_UTF8(83),
    EXTENSION(84),
    RANGES(85),
    TRAMPOLINE(86),
    CALL_COLUMN(87),
    CALL_FILE(88),
    CALL_LINE(89),
    DESCRIPTION(90),
    BINARY_SCALE(91),
    DECIMAL_SCALE(92),
    SMALL(93),
    DECIMAL_SIGN(94),
    DIGIT_COUNT(95),
    PICTURE_STRING(96),
    MUTABLE(97),
    THREADS_SCALED(98),
    EXPLICIT(99),
    OBJECT_POINTER(100),
    ENDIANITY(TerminalTokens.TokenNameREMAINDER_EQUAL),
    ELEMENTAL(TerminalTokens.TokenNameLEFT_SHIFT_EQUAL),
    PURE(TerminalTokens.TokenNameRIGHT_SHIFT_EQUAL),
    RECURSIVE(TerminalTokens.TokenNameUNSIGNED_RIGHT_SHIFT_EQUAL),
    SIGNATURE(TerminalTokens.TokenNameARROW),
    MAIN_SUBPROGRAM(TerminalTokens.TokenNameboolean),
    DATA_BIT_OFFSET(TerminalTokens.TokenNamebyte),
    CONST_EXPR(TerminalTokens.TokenNamecatch),
    ENUM_CLASS(TerminalTokens.TokenNamechar),
    LINKAGE_NAME(110),
    STRING_LENGTH_BIT_SIZE(TerminalTokens.TokenNamefloat),
    STRING_LENGTH_BYTE_SIZE(TerminalTokens.TokenNameimport),
    RANK(113),
    STR_OFFSETS_BASE(114),
    ADDR_BASE(115),
    RNGLISTS_BASE(116),
    DWO_NAME(TerminalTokens.TokenNamethrows),
    REFERENCE(119),
    RVALUE_REFERENCE(120),
    MACROS(121),
    CALL_ALL_CALLS(122),
    CALL_ALL_SOURCE_CALLS(TerminalTokens.TokenNameELLIPSIS),
    CALL_ALL_TAIL_CALLS(TerminalTokens.TokenNameelse),
    CALL_RETURN_PC(TerminalTokens.TokenNamerequires),
    CALL_VALUE(TerminalTokens.TokenNameexports),
    CALL_ORIGIN(TerminalTokens.TokenNameopens),
    CALL_PARAMETER(128),
    CALL_PC(129),
    CALL_TAIL_CALL(130),
    CALL_TARGET(131),
    CALL_TARGET_CLOBBERED(132),
    CALL_DATA_LOCATION(TerminalTokens.TokenNameBeginCaseElement),
    CALL_DATA_VALUE(TerminalTokens.TokenNameRestrictedIdentifierWhen),
    NORETURN(135),
    ALIGNMENT(136),
    EXPORT_SYMBOLS(137),
    DELETED(138),
    DEFAULTED(TerminalTokens.TokenNamegoto),
    LOCLISTS_BASE(140),
    LO_USER(8192),
    HI_USER(16383);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int opcode;

    DwAttribute(int i) {
        this.opcode = i;
    }

    public static EnumEntries<DwAttribute> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: getOpcode-pVg5ArA, reason: not valid java name and from getter */
    public final int getOpcode() {
        return this.opcode;
    }
}
