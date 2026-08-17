package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0003\b¹\u0001\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0007R\u0011\u0010*\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0007R\u0011\u0010,\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0007R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R\u0011\u00100\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0007R\u0011\u00102\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0007R\u0011\u00104\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0007R\u0011\u00106\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0007R\u0011\u00108\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0007R\u0011\u0010:\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0007R\u0011\u0010<\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0007R\u0011\u0010>\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0007R\u0011\u0010@\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0007R\u0011\u0010B\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0007R\u0011\u0010D\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u0007R\u0011\u0010F\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0007R\u0011\u0010H\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u0007R\u0011\u0010J\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bK\u0010\u0007R\u0011\u0010L\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bM\u0010\u0007R\u0011\u0010N\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bO\u0010\u0007R\u0011\u0010P\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010\u0007R\u0011\u0010R\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bS\u0010\u0007R\u0011\u0010T\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bU\u0010\u0007R\u0011\u0010V\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bW\u0010\u0007R\u0011\u0010X\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bY\u0010\u0007R\u0011\u0010Z\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\u0007R\u0011\u0010\\\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b]\u0010\u0007R\u0011\u0010^\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b_\u0010\u0007R\u0011\u0010`\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\ba\u0010\u0007R\u0011\u0010b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bc\u0010\u0007R\u0011\u0010d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\be\u0010\u0007R\u0011\u0010f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bg\u0010\u0007R\u0011\u0010h\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bi\u0010\u0007R\u0011\u0010j\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bk\u0010\u0007R\u0011\u0010l\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bm\u0010\u0007R\u0011\u0010n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bo\u0010\u0007R\u0011\u0010p\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bq\u0010\u0007R\u0011\u0010r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bs\u0010\u0007R\u0011\u0010t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bu\u0010\u0007R\u0011\u0010v\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bw\u0010\u0007R\u0011\u0010x\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\by\u0010\u0007R\u0011\u0010z\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b{\u0010\u0007R\u0011\u0010|\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b}\u0010\u0007R\u0011\u0010~\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u007f\u0010\u0007R\u0013\u0010\u0080\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0081\u0001\u0010\u0007R\u0013\u0010\u0082\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0083\u0001\u0010\u0007R\u0013\u0010\u0084\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0085\u0001\u0010\u0007R\u0013\u0010\u0086\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u0010\u0007R\u0013\u0010\u0088\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0089\u0001\u0010\u0007R\u0013\u0010\u008a\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u008b\u0001\u0010\u0007R\u0013\u0010\u008c\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u008d\u0001\u0010\u0007R\u0013\u0010\u008e\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u008f\u0001\u0010\u0007R\u0013\u0010\u0090\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0091\u0001\u0010\u0007R\u0013\u0010\u0092\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0093\u0001\u0010\u0007R\u0013\u0010\u0094\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0095\u0001\u0010\u0007R\u0013\u0010\u0096\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u0007R\u0013\u0010\u0098\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0099\u0001\u0010\u0007R\u0013\u0010\u009a\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009b\u0001\u0010\u0007R\u0013\u0010\u009c\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009d\u0001\u0010\u0007R\u0013\u0010\u009e\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009f\u0001\u0010\u0007R\u0013\u0010 \u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¡\u0001\u0010\u0007R\u0013\u0010¢\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b£\u0001\u0010\u0007R\u0013\u0010¤\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¥\u0001\u0010\u0007R\u0013\u0010¦\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b§\u0001\u0010\u0007R\u0013\u0010¨\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b©\u0001\u0010\u0007R\u0013\u0010ª\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b«\u0001\u0010\u0007R\u0013\u0010¬\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u00ad\u0001\u0010\u0007R\u0013\u0010®\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¯\u0001\u0010\u0007R\u0013\u0010°\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b±\u0001\u0010\u0007R\u0013\u0010²\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b³\u0001\u0010\u0007R\u0013\u0010´\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bµ\u0001\u0010\u0007R\u0013\u0010¶\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b·\u0001\u0010\u0007R\u0013\u0010¸\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¹\u0001\u0010\u0007R\u0013\u0010º\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b»\u0001\u0010\u0007R\u0013\u0010¼\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b½\u0001\u0010\u0007¨\u0006¾\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/SourceElementPositioningStrategies;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/diagnostics/SourceElementPositioningStrategy;", "getDEFAULT", "()Lorg/jetbrains/kotlin/diagnostics/SourceElementPositioningStrategy;", "SYNTAX_ERROR", "getSYNTAX_ERROR", "VAL_OR_VAR_NODE", "getVAL_OR_VAR_NODE", "FUN_INTERFACE", "getFUN_INTERFACE", "COMPANION_OBJECT", "getCOMPANION_OBJECT", "SECONDARY_CONSTRUCTOR_DELEGATION_CALL", "getSECONDARY_CONSTRUCTOR_DELEGATION_CALL", "DECLARATION_RETURN_TYPE", "getDECLARATION_RETURN_TYPE", "CONTEXT_KEYWORD", "getCONTEXT_KEYWORD", "DECLARATION_NAME", "getDECLARATION_NAME", "DECLARATION_NAME_ONLY", "getDECLARATION_NAME_ONLY", "DECLARATION_SIGNATURE", "getDECLARATION_SIGNATURE", "CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS", "getCALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS", "DECLARATION_SIGNATURE_OR_DEFAULT", "getDECLARATION_SIGNATURE_OR_DEFAULT", "VISIBILITY_MODIFIER", "getVISIBILITY_MODIFIER", "MODALITY_MODIFIER", "getMODALITY_MODIFIER", "ABSTRACT_MODIFIER", "getABSTRACT_MODIFIER", "OPEN_MODIFIER", "getOPEN_MODIFIER", "OVERRIDE_MODIFIER", "getOVERRIDE_MODIFIER", "PRIVATE_MODIFIER", "getPRIVATE_MODIFIER", "LATEINIT_MODIFIER", "getLATEINIT_MODIFIER", "VARIANCE_MODIFIER", "getVARIANCE_MODIFIER", "CONST_MODIFIER", "getCONST_MODIFIER", "INLINE_OR_VALUE_MODIFIER", "getINLINE_OR_VALUE_MODIFIER", "INNER_MODIFIER", "getINNER_MODIFIER", "FUN_MODIFIER", "getFUN_MODIFIER", "SUSPEND_MODIFIER", "getSUSPEND_MODIFIER", "DATA_MODIFIER", "getDATA_MODIFIER", "EXPECT_ACTUAL_MODIFIER", "getEXPECT_ACTUAL_MODIFIER", "OBJECT_KEYWORD", "getOBJECT_KEYWORD", "OPERATOR", "getOPERATOR", "PARAMETER_DEFAULT_VALUE", "getPARAMETER_DEFAULT_VALUE", "PARAMETERS_WITH_DEFAULT_VALUE", "getPARAMETERS_WITH_DEFAULT_VALUE", "PARAMETER_VARARG_MODIFIER", "getPARAMETER_VARARG_MODIFIER", "NAME_OF_NAMED_ARGUMENT", "getNAME_OF_NAMED_ARGUMENT", "VALUE_ARGUMENTS", "getVALUE_ARGUMENTS", "VALUE_ARGUMENTS_LIST", "getVALUE_ARGUMENTS_LIST", "SUPERTYPES_LIST", "getSUPERTYPES_LIST", "DOT_BY_QUALIFIED", "getDOT_BY_QUALIFIED", "SELECTOR_BY_QUALIFIED", "getSELECTOR_BY_QUALIFIED", "REFERENCE_BY_QUALIFIED", "getREFERENCE_BY_QUALIFIED", "REFERENCED_NAME_BY_QUALIFIED", "getREFERENCED_NAME_BY_QUALIFIED", "DEPRECATION", "getDEPRECATION", "WHEN_EXPRESSION", "getWHEN_EXPRESSION", "WHEN_GUARD", "getWHEN_GUARD", "IF_EXPRESSION", "getIF_EXPRESSION", "ELSE_ENTRY", "getELSE_ENTRY", "ARRAY_ACCESS", "getARRAY_ACCESS", "SAFE_ACCESS", "getSAFE_ACCESS", "AS_TYPE", "getAS_TYPE", "USELESS_ELVIS", "getUSELESS_ELVIS", "USELESS_ELVIS_LEFT", "getUSELESS_ELVIS_LEFT", "RETURN_WITH_LABEL", "getRETURN_WITH_LABEL", "VARIABLE_INITIALIZER", "getVARIABLE_INITIALIZER", "WHOLE_ELEMENT", "getWHOLE_ELEMENT", "LONG_LITERAL_SUFFIX", "getLONG_LITERAL_SUFFIX", "REIFIED_MODIFIER", "getREIFIED_MODIFIER", "TYPE_PARAMETERS_LIST", "getTYPE_PARAMETERS_LIST", "FUNCTION_TYPE_RECEIVER", "getFUNCTION_TYPE_RECEIVER", "NAME_IDENTIFIER", "getNAME_IDENTIFIER", "REDUNDANT_NULLABLE", "getREDUNDANT_NULLABLE", "QUESTION_MARK_BY_TYPE", "getQUESTION_MARK_BY_TYPE", "ANNOTATION_USE_SITE", "getANNOTATION_USE_SITE", "IMPORT_LAST_NAME", "getIMPORT_LAST_NAME", "IMPORT_LAST_BUT_ONE_NAME", "getIMPORT_LAST_BUT_ONE_NAME", "SPREAD_OPERATOR", "getSPREAD_OPERATOR", "DECLARATION_WITH_BODY", "getDECLARATION_WITH_BODY", "COMMAS", "getCOMMAS", "UNREACHABLE_CODE", "getUNREACHABLE_CODE", "ACTUAL_DECLARATION_NAME", "getACTUAL_DECLARATION_NAME", "LABEL", "getLABEL", "NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT", "getNOT_SUPPORTED_IN_INLINE_MOST_RELEVANT", "INLINE_PARAMETER_MODIFIER", "getINLINE_PARAMETER_MODIFIER", "INLINE_FUN_MODIFIER", "getINLINE_FUN_MODIFIER", "OPERATOR_MODIFIER", "getOPERATOR_MODIFIER", "INFIX_MODIFIER", "getINFIX_MODIFIER", "NON_FINAL_MODIFIER_OR_NAME", "getNON_FINAL_MODIFIER_OR_NAME", "ENUM_MODIFIER", "getENUM_MODIFIER", "FIELD_KEYWORD", "getFIELD_KEYWORD", "TAILREC_MODIFIER", "getTAILREC_MODIFIER", "EXTERNAL_MODIFIER", "getEXTERNAL_MODIFIER", "PROPERTY_DELEGATE", "getPROPERTY_DELEGATE", "IMPORT_ALIAS", "getIMPORT_ALIAS", "DECLARATION_START_TO_NAME", "getDECLARATION_START_TO_NAME", "DELEGATED_SUPERTYPE_BY_KEYWORD", "getDELEGATED_SUPERTYPE_BY_KEYWORD", "PROPERTY_DELEGATE_BY_KEYWORD", "getPROPERTY_DELEGATE_BY_KEYWORD", "CALL_ELEMENT_WITH_DOT", "getCALL_ELEMENT_WITH_DOT", "TYPEALIAS_TYPE_REFERENCE", "getTYPEALIAS_TYPE_REFERENCE", "SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC", "getSUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC", "TYPE_ARGUMENT_LIST_OR_SELF", "getTYPE_ARGUMENT_LIST_OR_SELF", "TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER", "getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER", "PACKAGE_DIRECTIVE_NAME_EXPRESSION", "getPACKAGE_DIRECTIVE_NAME_EXPRESSION", "OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS", "getOUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourceElementPositioningStrategies {
    private static final SourceElementPositioningStrategy ABSTRACT_MODIFIER;
    private static final SourceElementPositioningStrategy ACTUAL_DECLARATION_NAME;
    private static final SourceElementPositioningStrategy ANNOTATION_USE_SITE;
    private static final SourceElementPositioningStrategy ARRAY_ACCESS;
    private static final SourceElementPositioningStrategy AS_TYPE;
    private static final SourceElementPositioningStrategy CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS;
    private static final SourceElementPositioningStrategy CALL_ELEMENT_WITH_DOT;
    private static final SourceElementPositioningStrategy COMMAS;
    private static final SourceElementPositioningStrategy COMPANION_OBJECT;
    private static final SourceElementPositioningStrategy CONST_MODIFIER;
    private static final SourceElementPositioningStrategy CONTEXT_KEYWORD;
    private static final SourceElementPositioningStrategy DATA_MODIFIER;
    private static final SourceElementPositioningStrategy DECLARATION_NAME;
    private static final SourceElementPositioningStrategy DECLARATION_NAME_ONLY;
    private static final SourceElementPositioningStrategy DECLARATION_RETURN_TYPE;
    private static final SourceElementPositioningStrategy DECLARATION_SIGNATURE;
    private static final SourceElementPositioningStrategy DECLARATION_SIGNATURE_OR_DEFAULT;
    private static final SourceElementPositioningStrategy DECLARATION_START_TO_NAME;
    private static final SourceElementPositioningStrategy DECLARATION_WITH_BODY;
    private static final SourceElementPositioningStrategy DEFAULT;
    private static final SourceElementPositioningStrategy DELEGATED_SUPERTYPE_BY_KEYWORD;
    private static final SourceElementPositioningStrategy DEPRECATION;
    private static final SourceElementPositioningStrategy DOT_BY_QUALIFIED;
    private static final SourceElementPositioningStrategy ELSE_ENTRY;
    private static final SourceElementPositioningStrategy ENUM_MODIFIER;
    private static final SourceElementPositioningStrategy EXPECT_ACTUAL_MODIFIER;
    private static final SourceElementPositioningStrategy EXTERNAL_MODIFIER;
    private static final SourceElementPositioningStrategy FIELD_KEYWORD;
    private static final SourceElementPositioningStrategy FUNCTION_TYPE_RECEIVER;
    private static final SourceElementPositioningStrategy FUN_INTERFACE;
    private static final SourceElementPositioningStrategy FUN_MODIFIER;
    private static final SourceElementPositioningStrategy IF_EXPRESSION;
    private static final SourceElementPositioningStrategy IMPORT_ALIAS;
    private static final SourceElementPositioningStrategy IMPORT_LAST_BUT_ONE_NAME;
    private static final SourceElementPositioningStrategy IMPORT_LAST_NAME;
    private static final SourceElementPositioningStrategy INFIX_MODIFIER;
    private static final SourceElementPositioningStrategy INLINE_FUN_MODIFIER;
    private static final SourceElementPositioningStrategy INLINE_OR_VALUE_MODIFIER;
    private static final SourceElementPositioningStrategy INLINE_PARAMETER_MODIFIER;
    private static final SourceElementPositioningStrategy INNER_MODIFIER;
    public static final SourceElementPositioningStrategies INSTANCE = new SourceElementPositioningStrategies();
    private static final SourceElementPositioningStrategy LABEL;
    private static final SourceElementPositioningStrategy LATEINIT_MODIFIER;
    private static final SourceElementPositioningStrategy LONG_LITERAL_SUFFIX;
    private static final SourceElementPositioningStrategy MODALITY_MODIFIER;
    private static final SourceElementPositioningStrategy NAME_IDENTIFIER;
    private static final SourceElementPositioningStrategy NAME_OF_NAMED_ARGUMENT;
    private static final SourceElementPositioningStrategy NON_FINAL_MODIFIER_OR_NAME;
    private static final SourceElementPositioningStrategy NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT;
    private static final SourceElementPositioningStrategy OBJECT_KEYWORD;
    private static final SourceElementPositioningStrategy OPEN_MODIFIER;
    private static final SourceElementPositioningStrategy OPERATOR;
    private static final SourceElementPositioningStrategy OPERATOR_MODIFIER;
    private static final SourceElementPositioningStrategy OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS;
    private static final SourceElementPositioningStrategy OVERRIDE_MODIFIER;
    private static final SourceElementPositioningStrategy PACKAGE_DIRECTIVE_NAME_EXPRESSION;
    private static final SourceElementPositioningStrategy PARAMETERS_WITH_DEFAULT_VALUE;
    private static final SourceElementPositioningStrategy PARAMETER_DEFAULT_VALUE;
    private static final SourceElementPositioningStrategy PARAMETER_VARARG_MODIFIER;
    private static final SourceElementPositioningStrategy PRIVATE_MODIFIER;
    private static final SourceElementPositioningStrategy PROPERTY_DELEGATE;
    private static final SourceElementPositioningStrategy PROPERTY_DELEGATE_BY_KEYWORD;
    private static final SourceElementPositioningStrategy QUESTION_MARK_BY_TYPE;
    private static final SourceElementPositioningStrategy REDUNDANT_NULLABLE;
    private static final SourceElementPositioningStrategy REFERENCED_NAME_BY_QUALIFIED;
    private static final SourceElementPositioningStrategy REFERENCE_BY_QUALIFIED;
    private static final SourceElementPositioningStrategy REIFIED_MODIFIER;
    private static final SourceElementPositioningStrategy RETURN_WITH_LABEL;
    private static final SourceElementPositioningStrategy SAFE_ACCESS;
    private static final SourceElementPositioningStrategy SECONDARY_CONSTRUCTOR_DELEGATION_CALL;
    private static final SourceElementPositioningStrategy SELECTOR_BY_QUALIFIED;
    private static final SourceElementPositioningStrategy SPREAD_OPERATOR;
    private static final SourceElementPositioningStrategy SUPERTYPES_LIST;
    private static final SourceElementPositioningStrategy SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC;
    private static final SourceElementPositioningStrategy SUSPEND_MODIFIER;
    private static final SourceElementPositioningStrategy SYNTAX_ERROR;
    private static final SourceElementPositioningStrategy TAILREC_MODIFIER;
    private static final SourceElementPositioningStrategy TYPEALIAS_TYPE_REFERENCE;
    private static final SourceElementPositioningStrategy TYPE_ARGUMENT_LIST_OR_SELF;
    private static final SourceElementPositioningStrategy TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER;
    private static final SourceElementPositioningStrategy TYPE_PARAMETERS_LIST;
    private static final SourceElementPositioningStrategy UNREACHABLE_CODE;
    private static final SourceElementPositioningStrategy USELESS_ELVIS;
    private static final SourceElementPositioningStrategy USELESS_ELVIS_LEFT;
    private static final SourceElementPositioningStrategy VALUE_ARGUMENTS;
    private static final SourceElementPositioningStrategy VALUE_ARGUMENTS_LIST;
    private static final SourceElementPositioningStrategy VAL_OR_VAR_NODE;
    private static final SourceElementPositioningStrategy VARIABLE_INITIALIZER;
    private static final SourceElementPositioningStrategy VARIANCE_MODIFIER;
    private static final SourceElementPositioningStrategy VISIBILITY_MODIFIER;
    private static final SourceElementPositioningStrategy WHEN_EXPRESSION;
    private static final SourceElementPositioningStrategy WHEN_GUARD;
    private static final SourceElementPositioningStrategy WHOLE_ELEMENT;

    static {
        LightTreePositioningStrategies lightTreePositioningStrategies = LightTreePositioningStrategies.INSTANCE;
        DEFAULT = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDEFAULT(), PositioningStrategies.DEFAULT, null, 4, null);
        LightTreePositioningStrategy lightTreePositioningStrategy = lightTreePositioningStrategies.getDEFAULT();
        PositioningStrategies positioningStrategies = PositioningStrategies.INSTANCE;
        SYNTAX_ERROR = new SourceElementPositioningStrategy(lightTreePositioningStrategy, positioningStrategies.getSYNTAX_ERROR(), null, 4, null);
        VAL_OR_VAR_NODE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getVAL_OR_VAR_NODE(), PositioningStrategies.VAL_OR_VAR_NODE, null, 4, null);
        FUN_INTERFACE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getFUN_INTERFACE(), PositioningStrategies.FUN_INTERFACE, null, 4, null);
        COMPANION_OBJECT = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getCOMPANION_OBJECT(), PositioningStrategies.COMPANION_OBJECT, null, 4, null);
        SECONDARY_CONSTRUCTOR_DELEGATION_CALL = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getSECONDARY_CONSTRUCTOR_DELEGATION_CALL(), PositioningStrategies.SECONDARY_CONSTRUCTOR_DELEGATION_CALL, null, 4, null);
        DECLARATION_RETURN_TYPE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDECLARATION_RETURN_TYPE(), PositioningStrategies.DECLARATION_RETURN_TYPE, null, 4, null);
        CONTEXT_KEYWORD = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getCONTEXT_KEYWORD(), PositioningStrategies.CONTEXT_KEYWORD, null, 4, null);
        DECLARATION_NAME = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDECLARATION_NAME(), positioningStrategies.getDECLARATION_NAME(), null, 4, null);
        DECLARATION_NAME_ONLY = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDECLARATION_NAME_ONLY(), PositioningStrategies.DECLARATION_NAME_ONLY, null, 4, null);
        DECLARATION_SIGNATURE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDECLARATION_SIGNATURE(), positioningStrategies.getDECLARATION_SIGNATURE(), null, 4, null);
        CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getCALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS(), PositioningStrategies.CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS, null, 4, null);
        DECLARATION_SIGNATURE_OR_DEFAULT = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), positioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), null, 4, null);
        VISIBILITY_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getVISIBILITY_MODIFIER(), PositioningStrategies.VISIBILITY_MODIFIER, null, 4, null);
        MODALITY_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getMODALITY_MODIFIER(), PositioningStrategies.MODALITY_MODIFIER, null, 4, null);
        ABSTRACT_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getABSTRACT_MODIFIER(), PositioningStrategies.ABSTRACT_MODIFIER, null, 4, null);
        OPEN_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getOPEN_MODIFIER(), PositioningStrategies.OPEN_MODIFIER, null, 4, null);
        OVERRIDE_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getOVERRIDE_MODIFIER(), PositioningStrategies.OVERRIDE_MODIFIER, null, 4, null);
        PRIVATE_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getPRIVATE_MODIFIER(), PositioningStrategies.PRIVATE_MODIFIER, null, 4, null);
        LATEINIT_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getLATEINIT_MODIFIER(), PositioningStrategies.LATEINIT_MODIFIER, null, 4, null);
        VARIANCE_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getVARIANCE_MODIFIER(), PositioningStrategies.VARIANCE_MODIFIER, null, 4, null);
        CONST_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getCONST_MODIFIER(), PositioningStrategies.CONST_MODIFIER, null, 4, null);
        INLINE_OR_VALUE_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getINLINE_OR_VALUE_MODIFIER(), PositioningStrategies.INLINE_OR_VALUE_MODIFIER, null, 4, null);
        INNER_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getINNER_MODIFIER(), PositioningStrategies.INNER_MODIFIER, null, 4, null);
        FUN_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getFUN_MODIFIER(), PositioningStrategies.FUN_MODIFIER, null, 4, null);
        SUSPEND_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getSUSPEND_MODIFIER(), PositioningStrategies.SUSPEND_MODIFIER, null, 4, null);
        DATA_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDATA_MODIFIER(), PositioningStrategies.DATA_MODIFIER, null, 4, null);
        EXPECT_ACTUAL_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getEXPECT_ACTUAL_MODIFIER(), PositioningStrategies.EXPECT_ACTUAL_MODIFIER, null, 4, null);
        OBJECT_KEYWORD = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getOBJECT_KEYWORD(), PositioningStrategies.OBJECT_KEYWORD, null, 4, null);
        OPERATOR = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getOPERATOR(), positioningStrategies.getOPERATOR(), null, 4, null);
        PARAMETER_DEFAULT_VALUE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getPARAMETER_DEFAULT_VALUE(), PositioningStrategies.PARAMETER_DEFAULT_VALUE, null, 4, null);
        PARAMETERS_WITH_DEFAULT_VALUE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getPARAMETERS_WITH_DEFAULT_VALUE(), PositioningStrategies.PARAMETERS_WITH_DEFAULT_VALUE, null, 4, null);
        PARAMETER_VARARG_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getPARAMETER_VARARG_MODIFIER(), PositioningStrategies.PARAMETER_VARARG_MODIFIER, null, 4, null);
        NAME_OF_NAMED_ARGUMENT = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getNAME_OF_NAMED_ARGUMENT(), PositioningStrategies.NAME_OF_NAMED_ARGUMENT, null, 4, null);
        VALUE_ARGUMENTS = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getVALUE_ARGUMENTS(), PositioningStrategies.VALUE_ARGUMENTS, null, 4, null);
        VALUE_ARGUMENTS_LIST = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getVALUE_ARGUMENTS_LIST(), PositioningStrategies.VALUE_ARGUMENTS_LIST, null, 4, null);
        SUPERTYPES_LIST = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getSUPERTYPES_LIST(), PositioningStrategies.SUPERTYPES_LIST, null, 4, null);
        DOT_BY_QUALIFIED = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDOT_BY_QUALIFIED(), positioningStrategies.getDOT_BY_QUALIFIED(), null, 4, null);
        SELECTOR_BY_QUALIFIED = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getSELECTOR_BY_QUALIFIED(), positioningStrategies.getSELECTOR_BY_QUALIFIED(), null, 4, null);
        REFERENCE_BY_QUALIFIED = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getREFERENCE_BY_QUALIFIED(), positioningStrategies.getREFERENCE_BY_QUALIFIED(), null, 4, null);
        REFERENCED_NAME_BY_QUALIFIED = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getREFERENCED_NAME_BY_QUALIFIED(), positioningStrategies.getREFERENCED_NAME_BY_QUALIFIED(), null, 4, null);
        DEPRECATION = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDEPRECATION(), positioningStrategies.getDEPRECATION(), null, 4, null);
        WHEN_EXPRESSION = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getWHEN_EXPRESSION(), PositioningStrategies.WHEN_EXPRESSION, null, 4, null);
        WHEN_GUARD = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getWHEN_GUARD(), PositioningStrategies.WHEN_GUARD, null, 4, null);
        IF_EXPRESSION = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getIF_EXPRESSION(), PositioningStrategies.IF_EXPRESSION, null, 4, null);
        ELSE_ENTRY = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getELSE_ENTRY(), PositioningStrategies.ELSE_ENTRY, null, 4, null);
        ARRAY_ACCESS = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getARRAY_ACCESS(), PositioningStrategies.ARRAY_ACCESS, null, 4, null);
        SAFE_ACCESS = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getSAFE_ACCESS(), PositioningStrategies.SAFE_ACCESS, null, 4, null);
        AS_TYPE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getAS_TYPE(), PositioningStrategies.AS_TYPE, null, 4, null);
        USELESS_ELVIS = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getUSELESS_ELVIS(), PositioningStrategies.USELESS_ELVIS, null, 4, null);
        USELESS_ELVIS_LEFT = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getUSELESS_ELVIS_LEFT(), PositioningStrategies.USELESS_ELVIS_LEFT, null, 4, null);
        RETURN_WITH_LABEL = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getRETURN_WITH_LABEL(), PositioningStrategies.RETURN_WITH_LABEL, null, 4, null);
        VARIABLE_INITIALIZER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getVARIABLE_INITIALIZER(), positioningStrategies.getVARIABLE_INITIALIZER(), null, 4, null);
        WHOLE_ELEMENT = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getWHOLE_ELEMENT(), positioningStrategies.getWHOLE_ELEMENT(), null, 4, null);
        LONG_LITERAL_SUFFIX = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getLONG_LITERAL_SUFFIX(), PositioningStrategies.LONG_LITERAL_SUFFIX, null, 4, null);
        REIFIED_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getREIFIED_MODIFIER(), positioningStrategies.getREIFIED_MODIFIER(), null, 4, null);
        TYPE_PARAMETERS_LIST = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getTYPE_PARAMETERS_LIST(), positioningStrategies.getTYPE_PARAMETERS_LIST(), null, 4, null);
        FUNCTION_TYPE_RECEIVER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getFUNCTION_TYPE_RECEIVER(), positioningStrategies.getFUNCTION_TYPE_RECEIVER(), null, 4, null);
        NAME_IDENTIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getNAME_IDENTIFIER(), positioningStrategies.getNAME_IDENTIFIER(), null, 4, null);
        REDUNDANT_NULLABLE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getREDUNDANT_NULLABLE(), PositioningStrategies.REDUNDANT_NULLABLE, null, 4, null);
        QUESTION_MARK_BY_TYPE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getQUESTION_MARK_BY_TYPE(), PositioningStrategies.QUESTION_MARK_BY_TYPE, null, 4, null);
        ANNOTATION_USE_SITE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getANNOTATION_USE_SITE(), positioningStrategies.getANNOTATION_USE_SITE(), null, 4, null);
        IMPORT_LAST_NAME = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getIMPORT_LAST_NAME(), positioningStrategies.getIMPORT_LAST_NAME(), null, 4, null);
        IMPORT_LAST_BUT_ONE_NAME = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getIMPORT_LAST_BUT_ONE_NAME(), positioningStrategies.getIMPORT_LAST_BUT_ONE_NAME(), null, 4, null);
        SPREAD_OPERATOR = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getSPREAD_OPERATOR(), PositioningStrategies.SPREAD_OPERATOR, null, 4, null);
        DECLARATION_WITH_BODY = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDECLARATION_WITH_BODY(), PositioningStrategies.DECLARATION_WITH_BODY, null, 4, null);
        COMMAS = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getCOMMAS(), positioningStrategies.getCOMMAS(), null, 4, null);
        LightTreePositioningStrategy unreachable_code = lightTreePositioningStrategies.getUNREACHABLE_CODE();
        PsiPositioningStrategies psiPositioningStrategies = PsiPositioningStrategies.INSTANCE;
        UNREACHABLE_CODE = new SourceElementPositioningStrategy(unreachable_code, psiPositioningStrategies.getUNREACHABLE_CODE(), null, 4, null);
        ACTUAL_DECLARATION_NAME = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getACTUAL_DECLARATION_NAME(), psiPositioningStrategies.getACTUAL_DECLARATION_NAME(), null, 4, null);
        LABEL = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getLABEL(), positioningStrategies.getLABEL(), null, 4, null);
        NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getNOT_SUPPORTED_IN_INLINE_MOST_RELEVANT(), PositioningStrategies.NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT, null, 4, null);
        INLINE_PARAMETER_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getINLINE_PARAMETER_MODIFIER(), PositioningStrategies.INLINE_PARAMETER_MODIFIER, null, 4, null);
        INLINE_FUN_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getINLINE_FUN_MODIFIER(), PositioningStrategies.INLINE_FUN_MODIFIER, null, 4, null);
        OPERATOR_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getOPERATOR_MODIFIER(), PositioningStrategies.OPERATOR_MODIFIER, null, 4, null);
        INFIX_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getINFIX_MODIFIER(), PositioningStrategies.INFIX_MODIFIER, null, 4, null);
        NON_FINAL_MODIFIER_OR_NAME = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getNON_FINAL_MODIFIER_OR_NAME(), positioningStrategies.getNON_FINAL_MODIFIER_OR_NAME(), null, 4, null);
        ENUM_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getENUM_MODIFIER(), PositioningStrategies.ENUM_MODIFIER, null, 4, null);
        FIELD_KEYWORD = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getFIELD_KEYWORD(), PositioningStrategies.FIELD_KEYWORD, null, 4, null);
        TAILREC_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getTAILREC_MODIFIER(), PositioningStrategies.TAILREC_MODIFIER, null, 4, null);
        EXTERNAL_MODIFIER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getEXTERNAL_MODIFIER(), PositioningStrategies.EXTERNAL_MODIFIER, null, 4, null);
        PROPERTY_DELEGATE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getPROPERTY_DELEGATE(), PositioningStrategies.PROPERTY_DELEGATE, null, 4, null);
        IMPORT_ALIAS = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getIMPORT_ALIAS(), PositioningStrategies.IMPORT_ALIAS, null, 4, null);
        DECLARATION_START_TO_NAME = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDECLARATION_START_TO_NAME(), PositioningStrategies.DECLARATION_START_TO_NAME, null, 4, null);
        DELEGATED_SUPERTYPE_BY_KEYWORD = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getDELEGATED_SUPERTYPE_BY_KEYWORD(), positioningStrategies.getDELEGATED_SUPERTYPE_BY_KEYWORD(), null, 4, null);
        PROPERTY_DELEGATE_BY_KEYWORD = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getPROPERTY_DELEGATE_BY_KEYWORD(), positioningStrategies.getPROPERTY_DELEGATE_BY_KEYWORD(), null, 4, null);
        CALL_ELEMENT_WITH_DOT = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getCALL_ELEMENT_WITH_DOT(), PositioningStrategies.CALL_ELEMENT_WITH_DOT, null, 4, null);
        TYPEALIAS_TYPE_REFERENCE = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getTYPEALIAS_TYPE_REFERENCE(), PositioningStrategies.TYPEALIAS_TYPE_REFERENCE, null, 4, null);
        SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getSUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC(), PositioningStrategies.SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC, null, 4, null);
        TYPE_ARGUMENT_LIST_OR_SELF = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getTYPE_ARGUMENT_LIST_OR_SELF(), positioningStrategies.getTYPE_ARGUMENT_LIST_OR_SELF(), null, 4, null);
        TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER(), positioningStrategies.getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER(), null, 4, null);
        PACKAGE_DIRECTIVE_NAME_EXPRESSION = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getPACKAGE_DIRECTIVE_NAME_EXPRESSION(), positioningStrategies.getPACKAGE_DIRECTIVE_NAME_EXPRESSION(), null, 4, null);
        OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS = new SourceElementPositioningStrategy(lightTreePositioningStrategies.getOUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS(), positioningStrategies.getOUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS(), null, 4, null);
    }

    private SourceElementPositioningStrategies() {
    }

    public final SourceElementPositioningStrategy getABSTRACT_MODIFIER() {
        return ABSTRACT_MODIFIER;
    }

    public final SourceElementPositioningStrategy getACTUAL_DECLARATION_NAME() {
        return ACTUAL_DECLARATION_NAME;
    }

    public final SourceElementPositioningStrategy getANNOTATION_USE_SITE() {
        return ANNOTATION_USE_SITE;
    }

    public final SourceElementPositioningStrategy getARRAY_ACCESS() {
        return ARRAY_ACCESS;
    }

    public final SourceElementPositioningStrategy getAS_TYPE() {
        return AS_TYPE;
    }

    public final SourceElementPositioningStrategy getCALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS() {
        return CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS;
    }

    public final SourceElementPositioningStrategy getCALL_ELEMENT_WITH_DOT() {
        return CALL_ELEMENT_WITH_DOT;
    }

    public final SourceElementPositioningStrategy getCOMMAS() {
        return COMMAS;
    }

    public final SourceElementPositioningStrategy getCOMPANION_OBJECT() {
        return COMPANION_OBJECT;
    }

    public final SourceElementPositioningStrategy getCONST_MODIFIER() {
        return CONST_MODIFIER;
    }

    public final SourceElementPositioningStrategy getCONTEXT_KEYWORD() {
        return CONTEXT_KEYWORD;
    }

    public final SourceElementPositioningStrategy getDATA_MODIFIER() {
        return DATA_MODIFIER;
    }

    public final SourceElementPositioningStrategy getDECLARATION_NAME() {
        return DECLARATION_NAME;
    }

    public final SourceElementPositioningStrategy getDECLARATION_NAME_ONLY() {
        return DECLARATION_NAME_ONLY;
    }

    public final SourceElementPositioningStrategy getDECLARATION_RETURN_TYPE() {
        return DECLARATION_RETURN_TYPE;
    }

    public final SourceElementPositioningStrategy getDECLARATION_SIGNATURE() {
        return DECLARATION_SIGNATURE;
    }

    public final SourceElementPositioningStrategy getDECLARATION_SIGNATURE_OR_DEFAULT() {
        return DECLARATION_SIGNATURE_OR_DEFAULT;
    }

    public final SourceElementPositioningStrategy getDECLARATION_START_TO_NAME() {
        return DECLARATION_START_TO_NAME;
    }

    public final SourceElementPositioningStrategy getDECLARATION_WITH_BODY() {
        return DECLARATION_WITH_BODY;
    }

    public final SourceElementPositioningStrategy getDEFAULT() {
        return DEFAULT;
    }

    public final SourceElementPositioningStrategy getDELEGATED_SUPERTYPE_BY_KEYWORD() {
        return DELEGATED_SUPERTYPE_BY_KEYWORD;
    }

    public final SourceElementPositioningStrategy getDEPRECATION() {
        return DEPRECATION;
    }

    public final SourceElementPositioningStrategy getDOT_BY_QUALIFIED() {
        return DOT_BY_QUALIFIED;
    }

    public final SourceElementPositioningStrategy getELSE_ENTRY() {
        return ELSE_ENTRY;
    }

    public final SourceElementPositioningStrategy getENUM_MODIFIER() {
        return ENUM_MODIFIER;
    }

    public final SourceElementPositioningStrategy getEXPECT_ACTUAL_MODIFIER() {
        return EXPECT_ACTUAL_MODIFIER;
    }

    public final SourceElementPositioningStrategy getEXTERNAL_MODIFIER() {
        return EXTERNAL_MODIFIER;
    }

    public final SourceElementPositioningStrategy getFIELD_KEYWORD() {
        return FIELD_KEYWORD;
    }

    public final SourceElementPositioningStrategy getFUNCTION_TYPE_RECEIVER() {
        return FUNCTION_TYPE_RECEIVER;
    }

    public final SourceElementPositioningStrategy getFUN_INTERFACE() {
        return FUN_INTERFACE;
    }

    public final SourceElementPositioningStrategy getFUN_MODIFIER() {
        return FUN_MODIFIER;
    }

    public final SourceElementPositioningStrategy getIF_EXPRESSION() {
        return IF_EXPRESSION;
    }

    public final SourceElementPositioningStrategy getIMPORT_ALIAS() {
        return IMPORT_ALIAS;
    }

    public final SourceElementPositioningStrategy getIMPORT_LAST_BUT_ONE_NAME() {
        return IMPORT_LAST_BUT_ONE_NAME;
    }

    public final SourceElementPositioningStrategy getIMPORT_LAST_NAME() {
        return IMPORT_LAST_NAME;
    }

    public final SourceElementPositioningStrategy getINFIX_MODIFIER() {
        return INFIX_MODIFIER;
    }

    public final SourceElementPositioningStrategy getINLINE_FUN_MODIFIER() {
        return INLINE_FUN_MODIFIER;
    }

    public final SourceElementPositioningStrategy getINLINE_OR_VALUE_MODIFIER() {
        return INLINE_OR_VALUE_MODIFIER;
    }

    public final SourceElementPositioningStrategy getINLINE_PARAMETER_MODIFIER() {
        return INLINE_PARAMETER_MODIFIER;
    }

    public final SourceElementPositioningStrategy getINNER_MODIFIER() {
        return INNER_MODIFIER;
    }

    public final SourceElementPositioningStrategy getLABEL() {
        return LABEL;
    }

    public final SourceElementPositioningStrategy getLATEINIT_MODIFIER() {
        return LATEINIT_MODIFIER;
    }

    public final SourceElementPositioningStrategy getLONG_LITERAL_SUFFIX() {
        return LONG_LITERAL_SUFFIX;
    }

    public final SourceElementPositioningStrategy getMODALITY_MODIFIER() {
        return MODALITY_MODIFIER;
    }

    public final SourceElementPositioningStrategy getNAME_IDENTIFIER() {
        return NAME_IDENTIFIER;
    }

    public final SourceElementPositioningStrategy getNAME_OF_NAMED_ARGUMENT() {
        return NAME_OF_NAMED_ARGUMENT;
    }

    public final SourceElementPositioningStrategy getNON_FINAL_MODIFIER_OR_NAME() {
        return NON_FINAL_MODIFIER_OR_NAME;
    }

    public final SourceElementPositioningStrategy getNOT_SUPPORTED_IN_INLINE_MOST_RELEVANT() {
        return NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT;
    }

    public final SourceElementPositioningStrategy getOBJECT_KEYWORD() {
        return OBJECT_KEYWORD;
    }

    public final SourceElementPositioningStrategy getOPEN_MODIFIER() {
        return OPEN_MODIFIER;
    }

    public final SourceElementPositioningStrategy getOPERATOR() {
        return OPERATOR;
    }

    public final SourceElementPositioningStrategy getOPERATOR_MODIFIER() {
        return OPERATOR_MODIFIER;
    }

    public final SourceElementPositioningStrategy getOUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS() {
        return OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS;
    }

    public final SourceElementPositioningStrategy getOVERRIDE_MODIFIER() {
        return OVERRIDE_MODIFIER;
    }

    public final SourceElementPositioningStrategy getPACKAGE_DIRECTIVE_NAME_EXPRESSION() {
        return PACKAGE_DIRECTIVE_NAME_EXPRESSION;
    }

    public final SourceElementPositioningStrategy getPARAMETERS_WITH_DEFAULT_VALUE() {
        return PARAMETERS_WITH_DEFAULT_VALUE;
    }

    public final SourceElementPositioningStrategy getPARAMETER_DEFAULT_VALUE() {
        return PARAMETER_DEFAULT_VALUE;
    }

    public final SourceElementPositioningStrategy getPARAMETER_VARARG_MODIFIER() {
        return PARAMETER_VARARG_MODIFIER;
    }

    public final SourceElementPositioningStrategy getPRIVATE_MODIFIER() {
        return PRIVATE_MODIFIER;
    }

    public final SourceElementPositioningStrategy getPROPERTY_DELEGATE() {
        return PROPERTY_DELEGATE;
    }

    public final SourceElementPositioningStrategy getPROPERTY_DELEGATE_BY_KEYWORD() {
        return PROPERTY_DELEGATE_BY_KEYWORD;
    }

    public final SourceElementPositioningStrategy getQUESTION_MARK_BY_TYPE() {
        return QUESTION_MARK_BY_TYPE;
    }

    public final SourceElementPositioningStrategy getREDUNDANT_NULLABLE() {
        return REDUNDANT_NULLABLE;
    }

    public final SourceElementPositioningStrategy getREFERENCED_NAME_BY_QUALIFIED() {
        return REFERENCED_NAME_BY_QUALIFIED;
    }

    public final SourceElementPositioningStrategy getREFERENCE_BY_QUALIFIED() {
        return REFERENCE_BY_QUALIFIED;
    }

    public final SourceElementPositioningStrategy getREIFIED_MODIFIER() {
        return REIFIED_MODIFIER;
    }

    public final SourceElementPositioningStrategy getRETURN_WITH_LABEL() {
        return RETURN_WITH_LABEL;
    }

    public final SourceElementPositioningStrategy getSAFE_ACCESS() {
        return SAFE_ACCESS;
    }

    public final SourceElementPositioningStrategy getSECONDARY_CONSTRUCTOR_DELEGATION_CALL() {
        return SECONDARY_CONSTRUCTOR_DELEGATION_CALL;
    }

    public final SourceElementPositioningStrategy getSELECTOR_BY_QUALIFIED() {
        return SELECTOR_BY_QUALIFIED;
    }

    public final SourceElementPositioningStrategy getSPREAD_OPERATOR() {
        return SPREAD_OPERATOR;
    }

    public final SourceElementPositioningStrategy getSUPERTYPES_LIST() {
        return SUPERTYPES_LIST;
    }

    public final SourceElementPositioningStrategy getSUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC() {
        return SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC;
    }

    public final SourceElementPositioningStrategy getSUSPEND_MODIFIER() {
        return SUSPEND_MODIFIER;
    }

    public final SourceElementPositioningStrategy getSYNTAX_ERROR() {
        return SYNTAX_ERROR;
    }

    public final SourceElementPositioningStrategy getTAILREC_MODIFIER() {
        return TAILREC_MODIFIER;
    }

    public final SourceElementPositioningStrategy getTYPEALIAS_TYPE_REFERENCE() {
        return TYPEALIAS_TYPE_REFERENCE;
    }

    public final SourceElementPositioningStrategy getTYPE_ARGUMENT_LIST_OR_SELF() {
        return TYPE_ARGUMENT_LIST_OR_SELF;
    }

    public final SourceElementPositioningStrategy getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER() {
        return TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER;
    }

    public final SourceElementPositioningStrategy getTYPE_PARAMETERS_LIST() {
        return TYPE_PARAMETERS_LIST;
    }

    public final SourceElementPositioningStrategy getUNREACHABLE_CODE() {
        return UNREACHABLE_CODE;
    }

    public final SourceElementPositioningStrategy getUSELESS_ELVIS() {
        return USELESS_ELVIS;
    }

    public final SourceElementPositioningStrategy getUSELESS_ELVIS_LEFT() {
        return USELESS_ELVIS_LEFT;
    }

    public final SourceElementPositioningStrategy getVALUE_ARGUMENTS() {
        return VALUE_ARGUMENTS;
    }

    public final SourceElementPositioningStrategy getVALUE_ARGUMENTS_LIST() {
        return VALUE_ARGUMENTS_LIST;
    }

    public final SourceElementPositioningStrategy getVAL_OR_VAR_NODE() {
        return VAL_OR_VAR_NODE;
    }

    public final SourceElementPositioningStrategy getVARIABLE_INITIALIZER() {
        return VARIABLE_INITIALIZER;
    }

    public final SourceElementPositioningStrategy getVARIANCE_MODIFIER() {
        return VARIANCE_MODIFIER;
    }

    public final SourceElementPositioningStrategy getVISIBILITY_MODIFIER() {
        return VISIBILITY_MODIFIER;
    }

    public final SourceElementPositioningStrategy getWHEN_EXPRESSION() {
        return WHEN_EXPRESSION;
    }

    public final SourceElementPositioningStrategy getWHEN_GUARD() {
        return WHEN_GUARD;
    }

    public final SourceElementPositioningStrategy getWHOLE_ELEMENT() {
        return WHOLE_ELEMENT;
    }
}
