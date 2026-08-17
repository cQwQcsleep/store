package org.jetbrains.kotlin.backend.wasm.dwarf.entries;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.eclipse.jdt.internal.compiler.lookup.ExtraCompilerModifiers;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\bL\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bN¨\u0006O"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/dwarf/entries/DwTag;", "", "value", "Lkotlin/UInt;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;II)V", "getValue-pVg5ArA", "()I", "I", "ARRAY_TYPE", "CLASS_TYPE", "ENTRY_POINT", "ENUMERATION_TYPE", "FORMAL_PARAMETER", "IMPORTED_DECLARATION", "LABEL", "LEXICAL_BLOCK", "MEMBER", "POINTER_TYPE", "REFERENCE_TYPE", "COMPILE_UNIT", "STRING_TYPE", "STRUCTURE_TYPE", "SUBROUTINE_TYPE", "TYPEDEF", "UNION_TYPE", "UNSPECIFIED_PARAMETERS", "VARIANT", "COMMON_BLOCK", "COMMON_INCLUSION", "INHERITANCE", "INLINED_SUBROUTINE", "MODULE", "PTR_TO_MEMBER_TYPE", "SET_TYPE", "SUBRANGE_TYPE", "WITH_STMT", "ACCESS_DECLARATION", "BASE_TYPE", "CATCH_BLOCK", "CONST_TYPE", "CONSTANT", "ENUMERATOR", "FILE_TYPE", "FRIEND", "NAMELIST", "NAMELIST_ITEM", "PACKED_TYPE", "SUBPROGRAM", "TEMPLATE_TYPE_PARAMETER", "TEMPLATE_VALUE_PARAMETER", "THROWN_TYPE", "TRY_BLOCK", "VARIANT_PART", "VARIABLE", "VOLATILE_TYPE", "DWARF_PROCEDURE", "RESTRICT_TYPE", "INTERFACE_TYPE", "NAMESPACE", "IMPORTED_MODULE", "UNSPECIFIED_TYPE", "PARTIAL_UNIT", "IMPORTED_UNIT", "CONDITION", "SHARED_TYPE", "TYPE_UNIT", "RVALUE_REFERENCE_TYPE", "TEMPLATE_ALIAS", "COARRAY_TYPE", "GENERIC_SUBRANGE", "DYNAMIC_TYPE", "ATOMIC_TYPE", "CALL_SITE", "CALL_SITE_PARAMETER", "SKELETON_UNIT", "IMMUTABLE_TYPE", "LO_USER", "HI_USER", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum DwTag {
    ARRAY_TYPE(1),
    CLASS_TYPE(2),
    ENTRY_POINT(3),
    ENUMERATION_TYPE(4),
    FORMAL_PARAMETER(5),
    IMPORTED_DECLARATION(8),
    LABEL(10),
    LEXICAL_BLOCK(11),
    MEMBER(13),
    POINTER_TYPE(15),
    REFERENCE_TYPE(16),
    COMPILE_UNIT(17),
    STRING_TYPE(18),
    STRUCTURE_TYPE(19),
    SUBROUTINE_TYPE(21),
    TYPEDEF(22),
    UNION_TYPE(23),
    UNSPECIFIED_PARAMETERS(24),
    VARIANT(25),
    COMMON_BLOCK(26),
    COMMON_INCLUSION(27),
    INHERITANCE(28),
    INLINED_SUBROUTINE(29),
    MODULE(30),
    PTR_TO_MEMBER_TYPE(31),
    SET_TYPE(32),
    SUBRANGE_TYPE(33),
    WITH_STMT(34),
    ACCESS_DECLARATION(35),
    BASE_TYPE(36),
    CATCH_BLOCK(37),
    CONST_TYPE(38),
    CONSTANT(39),
    ENUMERATOR(40),
    FILE_TYPE(41),
    FRIEND(42),
    NAMELIST(43),
    NAMELIST_ITEM(44),
    PACKED_TYPE(45),
    SUBPROGRAM(46),
    TEMPLATE_TYPE_PARAMETER(47),
    TEMPLATE_VALUE_PARAMETER(48),
    THROWN_TYPE(49),
    TRY_BLOCK(50),
    VARIANT_PART(51),
    VARIABLE(52),
    VOLATILE_TYPE(53),
    DWARF_PROCEDURE(54),
    RESTRICT_TYPE(55),
    INTERFACE_TYPE(56),
    NAMESPACE(57),
    IMPORTED_MODULE(58),
    UNSPECIFIED_TYPE(59),
    PARTIAL_UNIT(60),
    IMPORTED_UNIT(61),
    CONDITION(63),
    SHARED_TYPE(64),
    TYPE_UNIT(65),
    RVALUE_REFERENCE_TYPE(66),
    TEMPLATE_ALIAS(67),
    COARRAY_TYPE(68),
    GENERIC_SUBRANGE(69),
    DYNAMIC_TYPE(70),
    ATOMIC_TYPE(71),
    CALL_SITE(72),
    CALL_SITE_PARAMETER(73),
    SKELETON_UNIT(74),
    IMMUTABLE_TYPE(75),
    LO_USER(16512),
    HI_USER(ExtraCompilerModifiers.AccJustFlag);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int value;

    DwTag(int i) {
        this.value = i;
    }

    public static EnumEntries<DwTag> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: getValue-pVg5ArA, reason: not valid java name and from getter */
    public final int getValue() {
        return this.value;
    }
}
