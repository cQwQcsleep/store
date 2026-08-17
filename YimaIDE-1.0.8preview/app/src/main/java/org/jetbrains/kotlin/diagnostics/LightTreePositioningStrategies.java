package org.jetbrains.kotlin.diagnostics;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies;
import org.jetbrains.kotlin.lexer.KtKeywordToken;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtSingleValueToken;
import org.jetbrains.kotlin.lexer.KtToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.stubs.elements.KtTokenSets;
import org.jetbrains.kotlin.resolve.source.SourceElementUtilsKt;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\bP\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\b\u0003\n\u0002\bG*\u0004\u008d\u0001\u0090\u0001\bÆ\u0002\u0018\u00002\u00020\u0001:\bÒ\u0001Ó\u0001Ô\u0001Õ\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0002J\u001c\u0010u\u001a\u0004\u0018\u00010\u0015*\b\u0012\u0004\u0012\u00020\u00150\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0007R\u0011\u0010\u001b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0007R\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0007R\u0011\u0010\u001f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0007R\u0011\u0010!\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0007R\u0011\u0010#\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0007R\u0011\u0010%\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0007R\u0011\u0010'\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0007R\u0011\u0010)\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0007R\u0018\u0010+\u001a\u00020,*\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010-R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R\u0011\u00100\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0007R\u0011\u00102\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0007R\u0011\u00104\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0007R\u0011\u00106\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0007R\u0011\u00108\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0007R\u0011\u0010:\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0007R\u0011\u0010<\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0007R\u0011\u0010>\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0007R\u0011\u0010@\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0007R\u0011\u0010B\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0007R\u000e\u0010D\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010E\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0007R\u0011\u0010G\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0007R\u0011\u0010I\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0007R\u0011\u0010K\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\u0007R\u0011\u0010M\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0007R\u0011\u0010O\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0007R\u0011\u0010Q\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0007R\u0011\u0010S\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bT\u0010\u0007R\u0011\u0010U\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\u0007R\u0011\u0010W\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\u0007R\u0011\u0010Y\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0007R\u0011\u0010[\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\u0007R\u0011\u0010]\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0007R\u0011\u0010_\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u0007R\u0011\u0010a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0007R\u0011\u0010c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0007R\u0011\u0010e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0007R\u0011\u0010g\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\u0007R\u0011\u0010i\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0007R\u0011\u0010k\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\u0007R\u0011\u0010m\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0007R\u0011\u0010o\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bp\u0010\u0007R\u0011\u0010q\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\br\u0010\u0007R\u0011\u0010s\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bt\u0010\u0007R\u0011\u0010v\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bw\u0010\u0007R\u0011\u0010x\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\by\u0010\u0007R\u0011\u0010z\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b{\u0010\u0007R\u001c\u0010|\u001a\u0010\u0012\f\u0012\n \u007f*\u0004\u0018\u00010~0~0}X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0080\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0081\u0001\u0010\u0007R\u0013\u0010\u0082\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0083\u0001\u0010\u0007R\u0013\u0010\u0084\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0085\u0001\u0010\u0007R\u0013\u0010\u0086\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u0010\u0007R\u0013\u0010\u0088\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0089\u0001\u0010\u0007R\u0013\u0010\u008a\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u008b\u0001\u0010\u0007R\u0013\u0010\u008c\u0001\u001a\u00030\u008d\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008e\u0001R\u0013\u0010\u008f\u0001\u001a\u00030\u0090\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0091\u0001R\u0013\u0010\u0092\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0093\u0001\u0010\u0007R\u0013\u0010\u0094\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0095\u0001\u0010\u0007R\u0013\u0010\u0096\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u0007R\u0013\u0010\u0098\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0099\u0001\u0010\u0007R\u0013\u0010\u009a\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009b\u0001\u0010\u0007R\u0013\u0010\u009c\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009d\u0001\u0010\u0007R\u0013\u0010\u009e\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009f\u0001\u0010\u0007R\u0013\u0010 \u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¡\u0001\u0010\u0007R\u0013\u0010¢\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b£\u0001\u0010\u0007R\u0013\u0010¤\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¥\u0001\u0010\u0007R\u0013\u0010¦\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b§\u0001\u0010\u0007R\u0013\u0010¨\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b©\u0001\u0010\u0007R\u0013\u0010ª\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b«\u0001\u0010\u0007R\u0013\u0010¬\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u00ad\u0001\u0010\u0007R\u0013\u0010®\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¯\u0001\u0010\u0007R\u0013\u0010°\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b±\u0001\u0010\u0007R\u0013\u0010²\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b³\u0001\u0010\u0007R\u0013\u0010´\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bµ\u0001\u0010\u0007R\u0013\u0010¶\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b·\u0001\u0010\u0007R\u0013\u0010¸\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¹\u0001\u0010\u0007R\u0013\u0010º\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b»\u0001\u0010\u0007R\u0013\u0010¼\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b½\u0001\u0010\u0007R\u0013\u0010¾\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¿\u0001\u0010\u0007R\u0013\u0010À\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÁ\u0001\u0010\u0007R\u0013\u0010Â\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÃ\u0001\u0010\u0007R\u0013\u0010Ä\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÅ\u0001\u0010\u0007R\u0013\u0010Æ\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÇ\u0001\u0010\u0007R\u0013\u0010È\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÉ\u0001\u0010\u0007R\u0013\u0010Ê\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bË\u0001\u0010\u0007R\u0013\u0010Ì\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÍ\u0001\u0010\u0007R\u0013\u0010Î\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÏ\u0001\u0010\u0007R\u0013\u0010Ð\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÑ\u0001\u0010\u0007¨\u0006Ö\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategies;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;", "getDEFAULT", "()Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;", "SUPERTYPES_LIST", "getSUPERTYPES_LIST", "VAL_OR_VAR_NODE", "getVAL_OR_VAR_NODE", "COMPANION_OBJECT", "getCOMPANION_OBJECT", "SECONDARY_CONSTRUCTOR_DELEGATION_CALL", "getSECONDARY_CONSTRUCTOR_DELEGATION_CALL", "DECLARATION_RETURN_TYPE", "getDECLARATION_RETURN_TYPE", "DECLARATION_START_TO_NAME", "getDECLARATION_START_TO_NAME", "findStartingASTNodeForDeclarationName", "Lcom/intellij/lang/LighterASTNode;", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "node", "DECLARATION_NAME", "getDECLARATION_NAME", "DECLARATION_NAME_ONLY", "getDECLARATION_NAME_ONLY", "ACTUAL_DECLARATION_NAME", "getACTUAL_DECLARATION_NAME", "CONTEXT_KEYWORD", "getCONTEXT_KEYWORD", "DECLARATION_SIGNATURE", "getDECLARATION_SIGNATURE", "CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS", "getCALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS", "DECLARATION_SIGNATURE_OR_DEFAULT", "getDECLARATION_SIGNATURE_OR_DEFAULT", "VARIABLE_INITIALIZER", "getVARIABLE_INITIALIZER", "LAST_CHILD", "getLAST_CHILD", "isDeclaration", Argument.Delimiters.none, "(Lcom/intellij/lang/LighterASTNode;)Z", "VISIBILITY_MODIFIER", "getVISIBILITY_MODIFIER", "MODALITY_MODIFIER", "getMODALITY_MODIFIER", "ABSTRACT_MODIFIER", "getABSTRACT_MODIFIER", "OPEN_MODIFIER", "getOPEN_MODIFIER", "OVERRIDE_MODIFIER", "getOVERRIDE_MODIFIER", "PRIVATE_MODIFIER", "getPRIVATE_MODIFIER", "LATEINIT_MODIFIER", "getLATEINIT_MODIFIER", "VARIANCE_MODIFIER", "getVARIANCE_MODIFIER", "CONST_MODIFIER", "getCONST_MODIFIER", "FUN_MODIFIER", "getFUN_MODIFIER", "SUSPEND_MODIFIER", "getSUSPEND_MODIFIER", "SUSPEND_OR_FUN_MODIFIER", "INLINE_OR_VALUE_MODIFIER", "getINLINE_OR_VALUE_MODIFIER", "INNER_MODIFIER", "getINNER_MODIFIER", "DATA_MODIFIER", "getDATA_MODIFIER", "OPERATOR_MODIFIER", "getOPERATOR_MODIFIER", "INFIX_MODIFIER", "getINFIX_MODIFIER", "ENUM_MODIFIER", "getENUM_MODIFIER", "TAILREC_MODIFIER", "getTAILREC_MODIFIER", "EXTERNAL_MODIFIER", "getEXTERNAL_MODIFIER", "EXPECT_ACTUAL_MODIFIER", "getEXPECT_ACTUAL_MODIFIER", "OBJECT_KEYWORD", "getOBJECT_KEYWORD", "FIELD_KEYWORD", "getFIELD_KEYWORD", "PROPERTY_DELEGATE", "getPROPERTY_DELEGATE", "PROPERTY_DELEGATE_BY_KEYWORD", "getPROPERTY_DELEGATE_BY_KEYWORD", "INLINE_PARAMETER_MODIFIER", "getINLINE_PARAMETER_MODIFIER", "INLINE_FUN_MODIFIER", "getINLINE_FUN_MODIFIER", "OPERATOR", "getOPERATOR", "PARAMETER_DEFAULT_VALUE", "getPARAMETER_DEFAULT_VALUE", "PARAMETERS_WITH_DEFAULT_VALUE", "getPARAMETERS_WITH_DEFAULT_VALUE", "PARAMETER_VARARG_MODIFIER", "getPARAMETER_VARARG_MODIFIER", "NAME_OF_NAMED_ARGUMENT", "getNAME_OF_NAMED_ARGUMENT", "VALUE_ARGUMENTS_LIST", "getVALUE_ARGUMENTS_LIST", "VALUE_ARGUMENTS", "getVALUE_ARGUMENTS", "DOT_BY_QUALIFIED", "getDOT_BY_QUALIFIED", "SELECTOR_BY_QUALIFIED", "getSELECTOR_BY_QUALIFIED", "referencedTypeExpression", "FUN_INTERFACE", "getFUN_INTERFACE", "REFERENCE_BY_QUALIFIED", "getREFERENCE_BY_QUALIFIED", "REFERENCED_NAME_BY_QUALIFIED", "getREFERENCED_NAME_BY_QUALIFIED", "nodeTypesWithOperation", Argument.Delimiters.none, "Lcom/intellij/psi/tree/IElementType;", "kotlin.jvm.PlatformType", "WHEN_EXPRESSION", "getWHEN_EXPRESSION", "WHEN_GUARD", "getWHEN_GUARD", "IF_EXPRESSION", "getIF_EXPRESSION", "ELSE_ENTRY", "getELSE_ENTRY", "ARRAY_ACCESS", "getARRAY_ACCESS", "SAFE_ACCESS", "getSAFE_ACCESS", "OPERATION_TO_END", "org/jetbrains/kotlin/diagnostics/LightTreePositioningStrategies$OPERATION_TO_END$1", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategies$OPERATION_TO_END$1;", "START_TO_OPERATOR", "org/jetbrains/kotlin/diagnostics/LightTreePositioningStrategies$START_TO_OPERATOR$1", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategies$START_TO_OPERATOR$1;", "AS_TYPE", "getAS_TYPE", "USELESS_ELVIS", "getUSELESS_ELVIS", "USELESS_ELVIS_LEFT", "getUSELESS_ELVIS_LEFT", "RETURN_WITH_LABEL", "getRETURN_WITH_LABEL", "WHOLE_ELEMENT", "getWHOLE_ELEMENT", "LONG_LITERAL_SUFFIX", "getLONG_LITERAL_SUFFIX", "REIFIED_MODIFIER", "getREIFIED_MODIFIER", "TYPE_PARAMETERS_LIST", "getTYPE_PARAMETERS_LIST", "FUNCTION_TYPE_RECEIVER", "getFUNCTION_TYPE_RECEIVER", "NAME_IDENTIFIER", "getNAME_IDENTIFIER", "REDUNDANT_NULLABLE", "getREDUNDANT_NULLABLE", "QUESTION_MARK_BY_TYPE", "getQUESTION_MARK_BY_TYPE", "ANNOTATION_USE_SITE", "getANNOTATION_USE_SITE", "IMPORT_LAST_NAME", "getIMPORT_LAST_NAME", "IMPORT_LAST_BUT_ONE_NAME", "getIMPORT_LAST_BUT_ONE_NAME", "IMPORT_ALIAS", "getIMPORT_ALIAS", "SPREAD_OPERATOR", "getSPREAD_OPERATOR", "DECLARATION_WITH_BODY", "getDECLARATION_WITH_BODY", "UNREACHABLE_CODE", "getUNREACHABLE_CODE", "NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT", "getNOT_SUPPORTED_IN_INLINE_MOST_RELEVANT", "LABEL", "getLABEL", "COMMAS", "getCOMMAS", "NON_FINAL_MODIFIER_OR_NAME", "getNON_FINAL_MODIFIER_OR_NAME", "DELEGATED_SUPERTYPE_BY_KEYWORD", "getDELEGATED_SUPERTYPE_BY_KEYWORD", "CALL_ELEMENT_WITH_DOT", "getCALL_ELEMENT_WITH_DOT", "TYPEALIAS_TYPE_REFERENCE", "getTYPEALIAS_TYPE_REFERENCE", "SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC", "getSUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC", "TYPE_ARGUMENT_LIST_OR_SELF", "getTYPE_ARGUMENT_LIST_OR_SELF", "TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER", "getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER", "PACKAGE_DIRECTIVE_NAME_EXPRESSION", "getPACKAGE_DIRECTIVE_NAME_EXPRESSION", "OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS", "getOUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS", "DEPRECATION", "getDEPRECATION", "BaseDeclarationNameStrategy", "ModifierSetBasedLightTreePositioningStrategy", "InlineFunLightTreePositioningStrategy", "FindReferencePositioningStrategy", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LightTreePositioningStrategies {
    private static final LightTreePositioningStrategy ABSTRACT_MODIFIER;
    private static final LightTreePositioningStrategy ANNOTATION_USE_SITE;
    private static final LightTreePositioningStrategy ARRAY_ACCESS;
    private static final LightTreePositioningStrategy AS_TYPE;
    private static final LightTreePositioningStrategy CALL_ELEMENT_WITH_DOT;
    private static final LightTreePositioningStrategy COMMAS;
    private static final LightTreePositioningStrategy CONST_MODIFIER;
    private static final LightTreePositioningStrategy DATA_MODIFIER;
    private static final LightTreePositioningStrategy DECLARATION_WITH_BODY;
    private static final LightTreePositioningStrategy DELEGATED_SUPERTYPE_BY_KEYWORD;
    private static final LightTreePositioningStrategy DEPRECATION;
    private static final LightTreePositioningStrategy DOT_BY_QUALIFIED;
    private static final LightTreePositioningStrategy ELSE_ENTRY;
    private static final LightTreePositioningStrategy ENUM_MODIFIER;
    private static final LightTreePositioningStrategy EXPECT_ACTUAL_MODIFIER;
    private static final LightTreePositioningStrategy EXTERNAL_MODIFIER;
    private static final LightTreePositioningStrategy FIELD_KEYWORD;
    private static final LightTreePositioningStrategy FUNCTION_TYPE_RECEIVER;
    private static final LightTreePositioningStrategy FUN_INTERFACE;
    private static final LightTreePositioningStrategy FUN_MODIFIER;
    private static final LightTreePositioningStrategy IF_EXPRESSION;
    private static final LightTreePositioningStrategy IMPORT_ALIAS;
    private static final LightTreePositioningStrategy IMPORT_LAST_BUT_ONE_NAME;
    private static final LightTreePositioningStrategy IMPORT_LAST_NAME;
    private static final LightTreePositioningStrategy INFIX_MODIFIER;
    private static final LightTreePositioningStrategy INLINE_FUN_MODIFIER;
    private static final LightTreePositioningStrategy INLINE_OR_VALUE_MODIFIER;
    private static final LightTreePositioningStrategy INLINE_PARAMETER_MODIFIER;
    private static final LightTreePositioningStrategy INNER_MODIFIER;
    private static final LightTreePositioningStrategy LABEL;
    private static final LightTreePositioningStrategy LATEINIT_MODIFIER;
    private static final LightTreePositioningStrategy LONG_LITERAL_SUFFIX;
    private static final LightTreePositioningStrategy MODALITY_MODIFIER;
    private static final LightTreePositioningStrategy NAME_IDENTIFIER;
    private static final LightTreePositioningStrategy NAME_OF_NAMED_ARGUMENT;
    private static final LightTreePositioningStrategy NON_FINAL_MODIFIER_OR_NAME;
    private static final LightTreePositioningStrategy NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT;
    private static final LightTreePositioningStrategy OBJECT_KEYWORD;
    private static final LightTreePositioningStrategy OPEN_MODIFIER;
    private static final LightTreePositioningStrategies$OPERATION_TO_END$1 OPERATION_TO_END;
    private static final LightTreePositioningStrategy OPERATOR;
    private static final LightTreePositioningStrategy OPERATOR_MODIFIER;
    private static final LightTreePositioningStrategy OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS;
    private static final LightTreePositioningStrategy OVERRIDE_MODIFIER;
    private static final LightTreePositioningStrategy PACKAGE_DIRECTIVE_NAME_EXPRESSION;
    private static final LightTreePositioningStrategy PARAMETERS_WITH_DEFAULT_VALUE;
    private static final LightTreePositioningStrategy PARAMETER_DEFAULT_VALUE;
    private static final LightTreePositioningStrategy PARAMETER_VARARG_MODIFIER;
    private static final LightTreePositioningStrategy PRIVATE_MODIFIER;
    private static final LightTreePositioningStrategy PROPERTY_DELEGATE;
    private static final LightTreePositioningStrategy PROPERTY_DELEGATE_BY_KEYWORD;
    private static final LightTreePositioningStrategy QUESTION_MARK_BY_TYPE;
    private static final LightTreePositioningStrategy REDUNDANT_NULLABLE;
    private static final LightTreePositioningStrategy REFERENCED_NAME_BY_QUALIFIED;
    private static final LightTreePositioningStrategy REFERENCE_BY_QUALIFIED;
    private static final LightTreePositioningStrategy REIFIED_MODIFIER;
    private static final LightTreePositioningStrategy RETURN_WITH_LABEL;
    private static final LightTreePositioningStrategy SAFE_ACCESS;
    private static final LightTreePositioningStrategy SELECTOR_BY_QUALIFIED;
    private static final LightTreePositioningStrategy SPREAD_OPERATOR;
    private static final LightTreePositioningStrategies$START_TO_OPERATOR$1 START_TO_OPERATOR;
    private static final LightTreePositioningStrategy SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC;
    private static final LightTreePositioningStrategy SUSPEND_MODIFIER;
    private static final LightTreePositioningStrategy SUSPEND_OR_FUN_MODIFIER;
    private static final LightTreePositioningStrategy TAILREC_MODIFIER;
    private static final LightTreePositioningStrategy TYPEALIAS_TYPE_REFERENCE;
    private static final LightTreePositioningStrategy TYPE_ARGUMENT_LIST_OR_SELF;
    private static final LightTreePositioningStrategy TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER;
    private static final LightTreePositioningStrategy TYPE_PARAMETERS_LIST;
    private static final LightTreePositioningStrategy UNREACHABLE_CODE;
    private static final LightTreePositioningStrategy USELESS_ELVIS;
    private static final LightTreePositioningStrategy USELESS_ELVIS_LEFT;
    private static final LightTreePositioningStrategy VALUE_ARGUMENTS;
    private static final LightTreePositioningStrategy VALUE_ARGUMENTS_LIST;
    private static final LightTreePositioningStrategy VARIANCE_MODIFIER;
    private static final LightTreePositioningStrategy VISIBILITY_MODIFIER;
    private static final LightTreePositioningStrategy WHEN_EXPRESSION;
    private static final LightTreePositioningStrategy WHEN_GUARD;
    private static final LightTreePositioningStrategy WHOLE_ELEMENT;
    private static final Set<IElementType> nodeTypesWithOperation;
    public static final LightTreePositioningStrategies INSTANCE = new LightTreePositioningStrategies();
    private static final LightTreePositioningStrategy DEFAULT = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$DEFAULT$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            IElementType tokenType = node.getTokenType();
            if (!Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_LITERAL)) {
                if (!Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_DECLARATION)) {
                    return Intrinsics.areEqual(tokenType, KtNodeTypes.CONSTRUCTOR_DELEGATION_CALL) ? LightTreePositioningStrategies.INSTANCE.getSECONDARY_CONSTRUCTOR_DELEGATION_CALL().mark(node, startOffset, endOffset, tree) : super.mark(node, startOffset, endOffset, tree);
                }
                LighterASTNode lighterASTNodeObjectKeyword = LightTreePositioningStrategiesKt.objectKeyword(tree, node);
                lighterASTNodeObjectKeyword.getClass();
                LighterASTNode lighterASTNodeNameIdentifier = LightTreePositioningStrategiesKt.nameIdentifier(tree, node);
                return LightTreePositioningStrategyKt.markRange(lighterASTNodeObjectKeyword, lighterASTNodeNameIdentifier == null ? lighterASTNodeObjectKeyword : lighterASTNodeNameIdentifier, startOffset, endOffset, tree, node);
            }
            IElementType iElementType = KtNodeTypes.OBJECT_DECLARATION;
            iElementType.getClass();
            LighterASTNode lighterASTNodeFindDescendantByType$default = LightTreePositioningStrategiesKt.findDescendantByType$default(tree, node, iElementType, false, 4, null);
            lighterASTNodeFindDescendantByType$default.getClass();
            LighterASTNode lighterASTNodeObjectKeyword2 = LightTreePositioningStrategiesKt.objectKeyword(tree, lighterASTNodeFindDescendantByType$default);
            lighterASTNodeObjectKeyword2.getClass();
            LighterASTNode lighterASTNodeSupertypesList = LightTreePositioningStrategiesKt.supertypesList(tree, lighterASTNodeFindDescendantByType$default);
            return LightTreePositioningStrategyKt.markRange(lighterASTNodeObjectKeyword2, lighterASTNodeSupertypesList == null ? lighterASTNodeObjectKeyword2 : lighterASTNodeSupertypesList, startOffset, endOffset, tree, node);
        }
    };
    private static final LightTreePositioningStrategy SUPERTYPES_LIST = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$SUPERTYPES_LIST$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            LighterASTNode lighterASTNodeSupertypesList = LightTreePositioningStrategiesKt.supertypesList(tree, node);
            if (lighterASTNodeSupertypesList == null) {
                lighterASTNodeSupertypesList = node;
            }
            return LightTreePositioningStrategyKt.markElement(lighterASTNodeSupertypesList, startOffset, endOffset, tree, node);
        }
    };
    private static final LightTreePositioningStrategy VAL_OR_VAR_NODE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$VAL_OR_VAR_NODE$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            LighterASTNode lighterASTNodeValOrVarKeyword = LightTreePositioningStrategiesKt.valOrVarKeyword(tree, node);
            if (lighterASTNodeValOrVarKeyword == null) {
                lighterASTNodeValOrVarKeyword = node;
            }
            return LightTreePositioningStrategyKt.markElement(lighterASTNodeValOrVarKeyword, startOffset, endOffset, tree, node);
        }
    };
    private static final LightTreePositioningStrategy COMPANION_OBJECT = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$COMPANION_OBJECT$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            LighterASTNode lighterASTNodeCompanionKeyword = LightTreePositioningStrategiesKt.companionKeyword(tree, node);
            if (lighterASTNodeCompanionKeyword == null) {
                lighterASTNodeCompanionKeyword = node;
            }
            return LightTreePositioningStrategyKt.markElement(lighterASTNodeCompanionKeyword, startOffset, endOffset, tree, node);
        }
    };
    private static final LightTreePositioningStrategy SECONDARY_CONSTRUCTOR_DELEGATION_CALL = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$SECONDARY_CONSTRUCTOR_DELEGATION_CALL$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            IElementType tokenType = node.getTokenType();
            IElementType iElementType = KtNodeTypes.SECONDARY_CONSTRUCTOR;
            if (Intrinsics.areEqual(tokenType, iElementType)) {
                LighterASTNode lighterASTNodeValueParameterList = LightTreePositioningStrategiesKt.valueParameterList(tree, node);
                if (lighterASTNodeValueParameterList == null) {
                    return LightTreePositioningStrategyKt.markElement$default(node, startOffset, endOffset, tree, null, 16, null);
                }
                LighterASTNode lighterASTNodeConstructorKeyword = LightTreePositioningStrategiesKt.constructorKeyword(tree, node);
                lighterASTNodeConstructorKeyword.getClass();
                LighterASTNode lighterASTNodeLastChild = LightTreePositioningStrategiesKt.lastChild(tree, lighterASTNodeValueParameterList);
                return LightTreePositioningStrategyKt.markRange(lighterASTNodeConstructorKeyword, lighterASTNodeLastChild == null ? lighterASTNodeValueParameterList : lighterASTNodeLastChild, startOffset, endOffset, tree, node);
            }
            if (!Intrinsics.areEqual(tokenType, KtNodeTypes.CONSTRUCTOR_DELEGATION_CALL)) {
                w04.a("unexpected element ", node);
                return null;
            }
            IElementType iElementType2 = KtNodeTypes.CONSTRUCTOR_DELEGATION_REFERENCE;
            iElementType2.getClass();
            LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType2);
            if (lighterASTNodeFindChildByType == null || LightTreePositioningStrategiesKt.firstChild(tree, lighterASTNodeFindChildByType) != null) {
                if (lighterASTNodeFindChildByType == null) {
                    lighterASTNodeFindChildByType = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
            }
            iElementType.getClass();
            LighterASTNode lighterASTNodeFindParentOfType$default = LightTreePositioningStrategiesKt.findParentOfType$default(tree, node, iElementType, false, 4, null);
            lighterASTNodeFindParentOfType$default.getClass();
            LighterASTNode lighterASTNodeValueParameterList2 = LightTreePositioningStrategiesKt.valueParameterList(tree, lighterASTNodeFindParentOfType$default);
            if (lighterASTNodeValueParameterList2 == null) {
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindParentOfType$default, startOffset, endOffset, tree, node);
            }
            LighterASTNode lighterASTNodeConstructorKeyword2 = LightTreePositioningStrategiesKt.constructorKeyword(tree, lighterASTNodeFindParentOfType$default);
            lighterASTNodeConstructorKeyword2.getClass();
            LighterASTNode lighterASTNodeLastChild2 = LightTreePositioningStrategiesKt.lastChild(tree, lighterASTNodeValueParameterList2);
            return LightTreePositioningStrategyKt.markRange(lighterASTNodeConstructorKeyword2, lighterASTNodeLastChild2 == null ? lighterASTNodeValueParameterList2 : lighterASTNodeLastChild2, startOffset, endOffset, tree, node);
        }
    };
    private static final LightTreePositioningStrategy DECLARATION_RETURN_TYPE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$DECLARATION_RETURN_TYPE$1
        private final LighterASTNode getElementToMark(LighterASTNode node, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            Pair pair;
            if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.PROPERTY_ACCESSOR)) {
                pair = TuplesKt.to(LightTreePositioningStrategiesKt.typeReference(tree, node), LightTreePositioningStrategiesKt.accessorNamePlaceholder(tree, node));
            } else {
                pair = (LightTreePositioningStrategies.INSTANCE.isDeclaration(node) || Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.VALUE_PARAMETER)) ? TuplesKt.to(LightTreePositioningStrategiesKt.typeReference(tree, node), LightTreePositioningStrategiesKt.nameIdentifier(tree, node)) : TuplesKt.to(null, null);
            }
            LighterASTNode lighterASTNode = (LighterASTNode) pair.component1();
            LighterASTNode lighterASTNode2 = (LighterASTNode) pair.component2();
            if (lighterASTNode == null) {
                return lighterASTNode2 == null ? node : lighterASTNode2;
            }
            return lighterASTNode;
        }

        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public boolean isValid(LighterASTNode node, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            return super.isValid(getElementToMark(node, tree), tree);
        }

        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            return LightTreePositioningStrategyKt.markElement(getElementToMark(node, tree), startOffset, endOffset, tree, node);
        }
    };
    private static final LightTreePositioningStrategy DECLARATION_START_TO_NAME = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$DECLARATION_START_TO_NAME$1
        private final LighterASTNode firstNonCommentNonAnnotationLeaf(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
            LighterASTNode lighterASTNode2;
            IElementType tokenType;
            LighterASTNode lighterASTNodeFirstNonCommentNonAnnotationLeaf;
            List listFilterNotNull = ArraysKt.filterNotNull(LightTreePositioningStrategiesKt.getChildrenArray(flyweightCapableTreeStructure, lighterASTNode));
            if (listFilterNotNull.isEmpty()) {
                return lighterASTNode;
            }
            Iterator it = listFilterNotNull.iterator();
            while (it.hasNext() && (tokenType = (lighterASTNode2 = (LighterASTNode) it.next()).getTokenType()) != null) {
                if (!KtTokens.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(tokenType) && !Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION_ENTRY) && (lighterASTNodeFirstNonCommentNonAnnotationLeaf = firstNonCommentNonAnnotationLeaf(flyweightCapableTreeStructure, lighterASTNode2)) != null) {
                    return lighterASTNodeFirstNonCommentNonAnnotationLeaf;
                }
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            LighterASTNode lighterASTNodeConstructorKeyword;
            node.getClass();
            tree.getClass();
            LighterASTNode lighterASTNodeFirstNonCommentNonAnnotationLeaf = firstNonCommentNonAnnotationLeaf(tree, node);
            LighterASTNode lighterASTNode = lighterASTNodeFirstNonCommentNonAnnotationLeaf == null ? node : lighterASTNodeFirstNonCommentNonAnnotationLeaf;
            LighterASTNode lighterASTNodeNameIdentifier = LightTreePositioningStrategiesKt.nameIdentifier(tree, node);
            if (lighterASTNodeNameIdentifier != null) {
                return LightTreePositioningStrategyKt.markRange(lighterASTNode, lighterASTNodeNameIdentifier, startOffset, endOffset, tree, node);
            }
            IElementType tokenType = node.getTokenType();
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.PRIMARY_CONSTRUCTOR) || Intrinsics.areEqual(tokenType, KtNodeTypes.SECONDARY_CONSTRUCTOR)) {
                lighterASTNodeConstructorKeyword = LightTreePositioningStrategiesKt.constructorKeyword(tree, node);
            } else {
                if (!Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_DECLARATION)) {
                    return LightTreePositioningStrategies.INSTANCE.getDEFAULT().mark(node, startOffset, endOffset, tree);
                }
                lighterASTNodeConstructorKeyword = LightTreePositioningStrategiesKt.objectKeyword(tree, node);
            }
            return LightTreePositioningStrategyKt.markRange(lighterASTNode, lighterASTNodeConstructorKeyword == null ? node : lighterASTNodeConstructorKeyword, startOffset, endOffset, tree, node);
        }
    };
    private static final LightTreePositioningStrategy DECLARATION_NAME = new BaseDeclarationNameStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$DECLARATION_NAME$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies.BaseDeclarationNameStrategy, org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            return Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.PROPERTY_ACCESSOR) ? LightTreePositioningStrategyKt.markElement(LightTreePositioningStrategiesKt.accessorNamePlaceholder(tree, node), startOffset, endOffset, tree, node) : super.mark(node, startOffset, endOffset, tree);
        }

        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies.BaseDeclarationNameStrategy
        public List<TextRange> markNameIdentifier(LighterASTNode nameIdentifier, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree, LighterASTNode node) {
            nameIdentifier.getClass();
            tree.getClass();
            node.getClass();
            return (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.CLASS) || Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.OBJECT_DECLARATION)) ? LightTreePositioningStrategyKt.markRange(LightTreePositioningStrategies.INSTANCE.findStartingASTNodeForDeclarationName(tree, node), nameIdentifier, startOffset, endOffset, tree, node) : LightTreePositioningStrategyKt.markElement(nameIdentifier, startOffset, endOffset, tree, node);
        }
    };
    private static final LightTreePositioningStrategy DECLARATION_NAME_ONLY = new BaseDeclarationNameStrategy();
    private static final LightTreePositioningStrategy ACTUAL_DECLARATION_NAME = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$ACTUAL_DECLARATION_NAME$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            LighterASTNode lighterASTNodeNameIdentifier = LightTreePositioningStrategiesKt.nameIdentifier(tree, node);
            return lighterASTNodeNameIdentifier != null ? LightTreePositioningStrategyKt.markElement(lighterASTNodeNameIdentifier, startOffset, endOffset, tree, node) : LightTreePositioningStrategies.INSTANCE.getDEFAULT().mark(node, startOffset, endOffset, tree);
        }
    };
    private static final LightTreePositioningStrategy CONTEXT_KEYWORD = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$CONTEXT_KEYWORD$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            List children;
            LighterASTNode lighterASTNode;
            List<TextRange> listMarkElement;
            node.getClass();
            tree.getClass();
            IElementType iElementType = KtNodeTypes.CONTEXT_PARAMETER_LIST;
            iElementType.getClass();
            LighterASTNode lighterASTNodeFindDescendantByType$default = LightTreePositioningStrategiesKt.findDescendantByType$default(tree, node, iElementType, false, 4, null);
            return (lighterASTNodeFindDescendantByType$default == null || (children = LightTreeUtilsKt.getChildren(lighterASTNodeFindDescendantByType$default, tree)) == null || (lighterASTNode = (LighterASTNode) CollectionsKt.firstOrNull(children)) == null || (listMarkElement = LightTreePositioningStrategyKt.markElement(lighterASTNode, startOffset, endOffset, tree, node)) == null) ? super.mark(node, startOffset, endOffset, tree) : listMarkElement;
        }
    };
    private static final LightTreePositioningStrategy DECLARATION_SIGNATURE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$DECLARATION_SIGNATURE$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            LighterASTNode lighterASTNodeReceiverTypeReference;
            LighterASTNode lighterASTNodeValueParameterList;
            node.getClass();
            tree.getClass();
            IElementType tokenType = node.getTokenType();
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.PRIMARY_CONSTRUCTOR) || Intrinsics.areEqual(tokenType, KtNodeTypes.SECONDARY_CONSTRUCTOR)) {
                LighterASTNode lighterASTNodeConstructorKeyword = LightTreePositioningStrategiesKt.constructorKeyword(tree, node);
                if (lighterASTNodeConstructorKeyword == null && (lighterASTNodeConstructorKeyword = LightTreePositioningStrategiesKt.valueParameterList(tree, node)) == null) {
                    return LightTreePositioningStrategyKt.markElement$default(node, startOffset, endOffset, tree, null, 16, null);
                }
                LighterASTNode lighterASTNodeValueParameterList2 = LightTreePositioningStrategiesKt.valueParameterList(tree, node);
                return (lighterASTNodeValueParameterList2 == null && (lighterASTNodeValueParameterList2 = LightTreePositioningStrategiesKt.constructorKeyword(tree, node)) == null) ? LightTreePositioningStrategyKt.markElement$default(node, startOffset, endOffset, tree, null, 16, null) : LightTreePositioningStrategyKt.markRange(lighterASTNodeConstructorKeyword, lighterASTNodeValueParameterList2, startOffset, endOffset, tree, node);
            }
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUN) || Intrinsics.areEqual(tokenType, KtNodeTypes.FUNCTION_LITERAL)) {
                LighterASTNode lighterASTNodeTypeReference = LightTreePositioningStrategiesKt.typeReference(tree, node);
                return LightTreePositioningStrategyKt.markRange((!Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.FUNCTION_LITERAL) || ((lighterASTNodeReceiverTypeReference = LightTreePositioningStrategiesKt.receiverTypeReference(tree, node)) == null && (lighterASTNodeReceiverTypeReference = LightTreePositioningStrategiesKt.valueParameterList(tree, node)) == null)) ? node : lighterASTNodeReceiverTypeReference, (lighterASTNodeTypeReference == null && (lighterASTNodeTypeReference = LightTreePositioningStrategiesKt.valueParameterList(tree, node)) == null && (lighterASTNodeTypeReference = LightTreePositioningStrategiesKt.nameIdentifier(tree, node)) == null) ? node : lighterASTNodeTypeReference, startOffset, endOffset, tree, node);
            }
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY)) {
                LighterASTNode lighterASTNodeTypeReference2 = LightTreePositioningStrategiesKt.typeReference(tree, node);
                return LightTreePositioningStrategyKt.markRange(node, (lighterASTNodeTypeReference2 == null && (lighterASTNodeTypeReference2 = LightTreePositioningStrategiesKt.nameIdentifier(tree, node)) == null) ? node : lighterASTNodeTypeReference2, startOffset, endOffset, tree, node);
            }
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY_ACCESSOR)) {
                LighterASTNode lighterASTNodeTypeReference3 = LightTreePositioningStrategiesKt.typeReference(tree, node);
                if (lighterASTNodeTypeReference3 == null && (lighterASTNodeTypeReference3 = LightTreePositioningStrategiesKt.valueParameterList(tree, node)) == null) {
                    lighterASTNodeTypeReference3 = LightTreePositioningStrategiesKt.accessorNamePlaceholder(tree, node);
                }
                return LightTreePositioningStrategyKt.markRange(node, lighterASTNodeTypeReference3, startOffset, endOffset, tree, node);
            }
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS)) {
                LighterASTNode lighterASTNodeNameIdentifier = LightTreePositioningStrategiesKt.nameIdentifier(tree, node);
                if (lighterASTNodeNameIdentifier == null) {
                    return LightTreePositioningStrategyKt.markElement$default(node, startOffset, endOffset, tree, null, 16, null);
                }
                LighterASTNode lighterASTNodePrimaryConstructor = LightTreePositioningStrategiesKt.primaryConstructor(tree, node);
                return (lighterASTNodePrimaryConstructor == null || (lighterASTNodeValueParameterList = LightTreePositioningStrategiesKt.valueParameterList(tree, lighterASTNodePrimaryConstructor)) == null) ? LightTreePositioningStrategyKt.markElement(lighterASTNodeNameIdentifier, startOffset, endOffset, tree, node) : LightTreePositioningStrategyKt.markRange(lighterASTNodeNameIdentifier, lighterASTNodeValueParameterList, startOffset, endOffset, tree, node);
            }
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_DECLARATION)) {
                return LightTreePositioningStrategies.INSTANCE.getDECLARATION_NAME().mark(node, startOffset, endOffset, tree);
            }
            if (!Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS_INITIALIZER)) {
                return super.mark(node, startOffset, endOffset, tree);
            }
            LighterASTNode lighterASTNodeInitKeyword = LightTreePositioningStrategiesKt.initKeyword(tree, node);
            lighterASTNodeInitKeyword.getClass();
            return LightTreePositioningStrategyKt.markElement(lighterASTNodeInitKeyword, startOffset, endOffset, tree, node);
        }
    };
    private static final LightTreePositioningStrategy CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            LighterASTNode lighterASTNode;
            LighterASTNode lighterASTNode2;
            node.getClass();
            tree.getClass();
            IElementType tokenType = node.getTokenType();
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUN)) {
                KtModifierKeywordToken ktModifierKeywordToken = KtTokens.FUN_KEYWORD;
                ktModifierKeywordToken.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, (IElementType) ktModifierKeywordToken);
                LighterASTNode lighterASTNode3 = lighterASTNodeFindChildByType == null ? node : lighterASTNodeFindChildByType;
                LighterASTNode lighterASTNodeTypeReference = LightTreePositioningStrategiesKt.typeReference(tree, node);
                if (lighterASTNodeTypeReference == null && (lighterASTNodeTypeReference = LightTreePositioningStrategiesKt.valueParameterList(tree, node)) == null && (lighterASTNodeTypeReference = LightTreePositioningStrategiesKt.nameIdentifier(tree, node)) == null) {
                    lighterASTNode = node;
                    lighterASTNode2 = lighterASTNode;
                } else {
                    lighterASTNode = lighterASTNodeTypeReference;
                    lighterASTNode2 = node;
                }
                return LightTreePositioningStrategyKt.markRange(lighterASTNode3, lighterASTNode, startOffset, endOffset, tree, lighterASTNode2);
            }
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY)) {
                TokenSet tokenSet = KtTokens.VAL_VAR;
                tokenSet.getClass();
                LighterASTNode lighterASTNodeFindChildByType2 = LightTreePositioningStrategiesKt.findChildByType(tree, node, tokenSet);
                LighterASTNode lighterASTNode4 = lighterASTNodeFindChildByType2 == null ? node : lighterASTNodeFindChildByType2;
                LighterASTNode lighterASTNodeTypeReference2 = LightTreePositioningStrategiesKt.typeReference(tree, node);
                return LightTreePositioningStrategyKt.markRange(lighterASTNode4, (lighterASTNodeTypeReference2 == null && (lighterASTNodeTypeReference2 = LightTreePositioningStrategiesKt.nameIdentifier(tree, node)) == null) ? node : lighterASTNodeTypeReference2, startOffset, endOffset, tree, node);
            }
            if (!Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY_ACCESSOR)) {
                return LightTreePositioningStrategies.INSTANCE.getDECLARATION_SIGNATURE().mark(node, startOffset, endOffset, tree);
            }
            LighterASTNode lighterASTNodeTypeReference3 = LightTreePositioningStrategiesKt.typeReference(tree, node);
            if (lighterASTNodeTypeReference3 == null && (lighterASTNodeTypeReference3 = LightTreePositioningStrategiesKt.valueParameterList(tree, node)) == null) {
                lighterASTNodeTypeReference3 = LightTreePositioningStrategiesKt.accessorNamePlaceholder(tree, node);
            }
            return LightTreePositioningStrategyKt.markRange(LightTreePositioningStrategiesKt.accessorNamePlaceholder(tree, node), lighterASTNodeTypeReference3, startOffset, endOffset, tree, node);
        }
    };
    private static final LightTreePositioningStrategy DECLARATION_SIGNATURE_OR_DEFAULT = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$DECLARATION_SIGNATURE_OR_DEFAULT$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            LightTreePositioningStrategies lightTreePositioningStrategies = LightTreePositioningStrategies.INSTANCE;
            return lightTreePositioningStrategies.isDeclaration(node) ? lightTreePositioningStrategies.getDECLARATION_SIGNATURE().mark(node, startOffset, endOffset, tree) : lightTreePositioningStrategies.getDEFAULT().mark(node, startOffset, endOffset, tree);
        }
    };
    private static final LightTreePositioningStrategy VARIABLE_INITIALIZER = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$VARIABLE_INITIALIZER$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            KtSingleValueToken ktSingleValueToken = KtTokens.EQ;
            ktSingleValueToken.getClass();
            LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, (IElementType) ktSingleValueToken);
            return lighterASTNodeFindChildByType != null ? LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node) : LightTreePositioningStrategies.INSTANCE.getLAST_CHILD().mark(node, startOffset, endOffset, tree);
        }
    };
    private static final LightTreePositioningStrategy LAST_CHILD = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$LAST_CHILD$1
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            return LightTreePositioningStrategyKt.markElement(LightTreePositioningStrategyKt.nonFillerLastChildOrSelf(node, tree), startOffset, endOffset, tree, node);
        }
    };

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0012\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J4\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0016J<\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r2\u0006\u0010\u0007\u001a\u00020\bH\u0014¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategies$BaseDeclarationNameStrategy;", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;", "<init>", "()V", "mark", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "node", "Lcom/intellij/lang/LighterASTNode;", "startOffset", Argument.Delimiters.none, "endOffset", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "markNameIdentifier", "nameIdentifier", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static class BaseDeclarationNameStrategy extends LightTreePositioningStrategy {
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            LighterASTNode lighterASTNodeNameIdentifier = LightTreePositioningStrategiesKt.nameIdentifier(tree, node);
            if (lighterASTNodeNameIdentifier != null) {
                return markNameIdentifier(lighterASTNodeNameIdentifier, startOffset, endOffset, tree, node);
            }
            return Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.FUN) ? LightTreePositioningStrategies.INSTANCE.getDECLARATION_SIGNATURE().mark(node, startOffset, endOffset, tree) : LightTreePositioningStrategies.INSTANCE.getDEFAULT().mark(node, startOffset, endOffset, tree);
        }

        public List<TextRange> markNameIdentifier(LighterASTNode nameIdentifier, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree, LighterASTNode node) {
            nameIdentifier.getClass();
            tree.getClass();
            node.getClass();
            return LightTreePositioningStrategyKt.markElement(nameIdentifier, startOffset, endOffset, tree, node);
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J4\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategies$FindReferencePositioningStrategy;", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;", "locateReferencedName", Argument.Delimiters.none, "<init>", "(Z)V", "getLocateReferencedName", "()Z", "mark", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "node", "Lcom/intellij/lang/LighterASTNode;", "startOffset", Argument.Delimiters.none, "endOffset", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FindReferencePositioningStrategy extends LightTreePositioningStrategy {
        private final boolean locateReferencedName;

        public FindReferencePositioningStrategy(boolean z) {
            this.locateReferencedName = z;
        }

        public final boolean getLocateReferencedName() {
            return this.locateReferencedName;
        }

        /* JADX WARN: Code duplicated, block: B:111:0x01d7  */
        /* JADX WARN: Code duplicated, block: B:17:0x0049  */
        /* JADX WARN: Code duplicated, block: B:48:0x00da  */
        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            LighterASTNode lighterASTNodeReferenceExpression;
            LighterASTNode lighterASTNodeFindChildByType;
            node.getClass();
            tree.getClass();
            IElementType tokenType = node.getTokenType();
            IElementType iElementType = KtNodeTypes.BINARY_EXPRESSION;
            if (Intrinsics.areEqual(tokenType, iElementType)) {
                TokenSet tokenSet = KtTokens.AUGMENTED_ASSIGNMENTS;
                tokenSet.getClass();
                LighterASTNode lighterASTNodeFindDescendantByTypes = LightTreePositioningStrategiesKt.findDescendantByTypes(tree, node, tokenSet);
                if (lighterASTNodeFindDescendantByTypes != null) {
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindDescendantByTypes, startOffset, endOffset, tree, node);
                }
            }
            if (!Intrinsics.areEqual(node.getTokenType(), iElementType)) {
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.CALL_EXPRESSION)) {
                }
                lighterASTNodeReferenceExpression = LightTreePositioningStrategiesKt.referenceExpression(tree, node, this.locateReferencedName);
                if (lighterASTNodeReferenceExpression == null) {
                    lighterASTNodeReferenceExpression = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeReferenceExpression, startOffset, endOffset, tree, node);
            }
            KtSingleValueToken ktSingleValueToken = KtTokens.EQ;
            ktSingleValueToken.getClass();
            if (LightTreePositioningStrategiesKt.findDescendantByType(tree, node, ktSingleValueToken, false) != null) {
                LighterASTNode lighterASTNodeFindExpressionDeep = LightTreePositioningStrategiesKt.findExpressionDeep(tree, node);
                if (lighterASTNodeFindExpressionDeep != null) {
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindExpressionDeep, startOffset, endOffset, tree, node);
                }
            } else {
                if (!Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.CALL_EXPRESSION) || Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.CONSTRUCTOR_DELEGATION_CALL)) {
                    lighterASTNodeReferenceExpression = LightTreePositioningStrategiesKt.referenceExpression(tree, node, this.locateReferencedName);
                    if (lighterASTNodeReferenceExpression == null) {
                        lighterASTNodeReferenceExpression = node;
                    }
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeReferenceExpression, startOffset, endOffset, tree, node);
                }
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.PROPERTY_DELEGATE)) {
                    LighterASTNode lighterASTNodeFindExpressionDeep2 = LightTreePositioningStrategiesKt.findExpressionDeep(tree, node);
                    if (lighterASTNodeFindExpressionDeep2 == null) {
                        lighterASTNodeFindExpressionDeep2 = node;
                    }
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindExpressionDeep2, startOffset, endOffset, tree, node);
                }
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.ANNOTATION_ENTRY) || Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.SUPER_TYPE_CALL_ENTRY)) {
                    IElementType iElementType2 = KtNodeTypes.CONSTRUCTOR_CALLEE;
                    iElementType2.getClass();
                    LighterASTNode lighterASTNodeFindDescendantByType$default = LightTreePositioningStrategiesKt.findDescendantByType$default(tree, node, iElementType2, false, 4, null);
                    if (lighterASTNodeFindDescendantByType$default == null) {
                        lighterASTNodeFindDescendantByType$default = node;
                    }
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindDescendantByType$default, startOffset, endOffset, tree, node);
                }
                if (LightTreePositioningStrategies.nodeTypesWithOperation.contains(node.getTokenType())) {
                    LighterASTNode lighterASTNodeOperationReference = LightTreePositioningStrategiesKt.operationReference(tree, node);
                    if (lighterASTNodeOperationReference == null) {
                        lighterASTNodeOperationReference = node;
                    }
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeOperationReference, startOffset, endOffset, tree, node);
                }
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.TYPE_REFERENCE)) {
                    IElementType iElementType3 = KtNodeTypes.NULLABLE_TYPE;
                    iElementType3.getClass();
                    LighterASTNode lighterASTNodeFindChildByType2 = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType3);
                    if (lighterASTNodeFindChildByType2 != null) {
                        IElementType iElementType4 = KtNodeTypes.USER_TYPE;
                        iElementType4.getClass();
                        lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, lighterASTNodeFindChildByType2, iElementType4);
                        if (lighterASTNodeFindChildByType == null) {
                            lighterASTNodeFindChildByType = node;
                        }
                    } else {
                        lighterASTNodeFindChildByType = node;
                    }
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
                }
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.IMPORT_DIRECTIVE)) {
                    TokenSet tokenSet2 = KtTokenSets.INSIDE_DIRECTIVE_EXPRESSIONS;
                    tokenSet2.getClass();
                    LighterASTNode lighterASTNodeFindChildByType3 = LightTreePositioningStrategiesKt.findChildByType(tree, node, tokenSet2);
                    if (lighterASTNodeFindChildByType3 == null) {
                        lighterASTNodeFindChildByType3 = node;
                    }
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType3, startOffset, endOffset, tree, node);
                }
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.CLASS) || Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.OBJECT_DECLARATION) || Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.ENUM_ENTRY)) {
                    LighterASTNode lighterASTNodeNameIdentifier = LightTreePositioningStrategiesKt.nameIdentifier(tree, node);
                    if (lighterASTNodeNameIdentifier != null) {
                        return LightTreePositioningStrategyKt.markRange(LightTreePositioningStrategies.INSTANCE.findStartingASTNodeForDeclarationName(tree, node), lighterASTNodeNameIdentifier, startOffset, endOffset, tree, node);
                    }
                } else {
                    if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.DESTRUCTURING_DECLARATION_ENTRY)) {
                        LighterASTNode lighterASTNodeNameIdentifier2 = LightTreePositioningStrategiesKt.nameIdentifier(tree, node);
                        if (lighterASTNodeNameIdentifier2 == null) {
                            lighterASTNodeNameIdentifier2 = node;
                        }
                        return LightTreePositioningStrategyKt.markElement(lighterASTNodeNameIdentifier2, startOffset, endOffset, tree, node);
                    }
                    if (!Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.DOT_QUALIFIED_EXPRESSION) && !Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.SAFE_ACCESS_EXPRESSION) && !Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.CALLABLE_REFERENCE_EXPRESSION)) {
                        return super.mark(node, startOffset, endOffset, tree);
                    }
                }
            }
            LighterASTNode lighterASTNodeSelector = LightTreePositioningStrategiesKt.selector(tree, node);
            if (lighterASTNodeSelector != null) {
                IElementType tokenType2 = lighterASTNodeSelector.getTokenType();
                if (Intrinsics.areEqual(tokenType2, KtNodeTypes.REFERENCE_EXPRESSION)) {
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeSelector, startOffset, endOffset, tree, node);
                }
                if (Intrinsics.areEqual(tokenType2, KtNodeTypes.CALL_EXPRESSION) || Intrinsics.areEqual(tokenType2, KtNodeTypes.CONSTRUCTOR_DELEGATION_CALL) || Intrinsics.areEqual(tokenType2, KtNodeTypes.SUPER_TYPE_CALL_ENTRY)) {
                    LighterASTNode lighterASTNodeReferenceExpression2 = LightTreePositioningStrategiesKt.referenceExpression(tree, lighterASTNodeSelector, this.locateReferencedName);
                    if (lighterASTNodeReferenceExpression2 != null) {
                        lighterASTNodeSelector = lighterASTNodeReferenceExpression2;
                    }
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeSelector, startOffset, endOffset, tree, node);
                }
            }
            return super.mark(node, startOffset, endOffset, tree);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J4\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategies$InlineFunLightTreePositioningStrategy;", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategies$ModifierSetBasedLightTreePositioningStrategy;", "<init>", "()V", "mark", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "node", "Lcom/intellij/lang/LighterASTNode;", "startOffset", Argument.Delimiters.none, "endOffset", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InlineFunLightTreePositioningStrategy extends ModifierSetBasedLightTreePositioningStrategy {
        /* JADX WARN: Illegal instructions before constructor call */
        public InlineFunLightTreePositioningStrategy() {
            IElementType iElementType = KtTokens.INLINE_KEYWORD;
            iElementType.getClass();
            super(iElementType);
        }

        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies.ModifierSetBasedLightTreePositioningStrategy, org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            if (!Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.PROPERTY)) {
                return super.mark(node, startOffset, endOffset, tree);
            }
            List<TextRange> listMarkModifier = markModifier(LightTreePositioningStrategiesKt.getter(tree, node), startOffset, endOffset, tree, node);
            if (listMarkModifier != null) {
                return listMarkModifier;
            }
            List<TextRange> listMarkModifier2 = markModifier(LightTreePositioningStrategiesKt.setter(tree, node), startOffset, endOffset, tree, node);
            return listMarkModifier2 == null ? super.mark(node, startOffset, endOffset, tree) : listMarkModifier2;
        }
    }

    /* JADX WARN: Type inference failed for: r0v66, types: [org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$OPERATION_TO_END$1, org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy] */
    /* JADX WARN: Type inference failed for: r5v17, types: [org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$START_TO_OPERATOR$1, org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy] */
    static {
        TokenSet tokenSet = KtTokens.VISIBILITY_MODIFIERS;
        tokenSet.getClass();
        VISIBILITY_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(tokenSet);
        TokenSet tokenSet2 = KtTokens.MODALITY_MODIFIERS;
        tokenSet2.getClass();
        MODALITY_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(tokenSet2);
        IElementType iElementType = KtTokens.ABSTRACT_KEYWORD;
        iElementType.getClass();
        ABSTRACT_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType);
        IElementType iElementType2 = KtTokens.OPEN_KEYWORD;
        iElementType2.getClass();
        OPEN_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType2);
        IElementType iElementType3 = KtTokens.OVERRIDE_KEYWORD;
        iElementType3.getClass();
        OVERRIDE_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType3);
        IElementType iElementType4 = KtTokens.PRIVATE_KEYWORD;
        iElementType4.getClass();
        PRIVATE_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType4);
        IElementType iElementType5 = KtTokens.LATEINIT_KEYWORD;
        iElementType5.getClass();
        LATEINIT_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType5);
        IElementType iElementType6 = KtTokens.IN_KEYWORD;
        iElementType6.getClass();
        IElementType iElementType7 = KtTokens.OUT_KEYWORD;
        iElementType7.getClass();
        VARIANCE_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType6, iElementType7);
        IElementType iElementType8 = KtTokens.CONST_KEYWORD;
        iElementType8.getClass();
        CONST_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType8);
        IElementType iElementType9 = KtTokens.FUN_KEYWORD;
        iElementType9.getClass();
        FUN_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType9);
        IElementType iElementType10 = KtTokens.SUSPEND_KEYWORD;
        iElementType10.getClass();
        SUSPEND_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType10);
        iElementType10.getClass();
        iElementType9.getClass();
        SUSPEND_OR_FUN_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType10, iElementType9);
        IElementType iElementType11 = KtTokens.INLINE_KEYWORD;
        iElementType11.getClass();
        IElementType iElementType12 = KtTokens.VALUE_KEYWORD;
        iElementType12.getClass();
        INLINE_OR_VALUE_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType11, iElementType12);
        IElementType iElementType13 = KtTokens.INNER_KEYWORD;
        iElementType13.getClass();
        INNER_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType13);
        IElementType iElementType14 = KtTokens.DATA_KEYWORD;
        iElementType14.getClass();
        DATA_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType14);
        IElementType iElementType15 = KtTokens.OPERATOR_KEYWORD;
        iElementType15.getClass();
        OPERATOR_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType15);
        IElementType iElementType16 = KtTokens.INFIX_KEYWORD;
        iElementType16.getClass();
        INFIX_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType16);
        IElementType iElementType17 = KtTokens.ENUM_KEYWORD;
        iElementType17.getClass();
        ENUM_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType17);
        IElementType iElementType18 = KtTokens.TAILREC_KEYWORD;
        iElementType18.getClass();
        TAILREC_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType18);
        IElementType iElementType19 = KtTokens.EXTERNAL_KEYWORD;
        iElementType19.getClass();
        EXTERNAL_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType19);
        IElementType iElementType20 = KtTokens.EXPECT_KEYWORD;
        iElementType20.getClass();
        IElementType iElementType21 = KtTokens.ACTUAL_KEYWORD;
        iElementType21.getClass();
        EXPECT_ACTUAL_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType20, iElementType21);
        OBJECT_KEYWORD = LightTreePositioningStrategiesKt.keywordStrategy(new Function2() { // from class: a19
            public final Object invoke(Object obj, Object obj2) {
                return LightTreePositioningStrategies.a((FlyweightCapableTreeStructure) obj, (LighterASTNode) obj2);
            }
        });
        FIELD_KEYWORD = LightTreePositioningStrategiesKt.keywordStrategy(new Function2() { // from class: b19
            public final Object invoke(Object obj, Object obj2) {
                return LightTreePositioningStrategies.b((FlyweightCapableTreeStructure) obj, (LighterASTNode) obj2);
            }
        });
        PROPERTY_DELEGATE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$PROPERTY_DELEGATE$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType iElementType22 = KtNodeTypes.PROPERTY_DELEGATE;
                iElementType22.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType22);
                if (lighterASTNodeFindChildByType == null) {
                    lighterASTNodeFindChildByType = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
            }
        };
        PROPERTY_DELEGATE_BY_KEYWORD = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$PROPERTY_DELEGATE_BY_KEYWORD$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNode = (LighterASTNode) tree.getParent(node);
                LighterASTNode lighterASTNodeByKeyword = lighterASTNode != null ? LightTreePositioningStrategiesKt.byKeyword(tree, lighterASTNode) : null;
                if (lighterASTNodeByKeyword == null) {
                    lighterASTNodeByKeyword = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeByKeyword, startOffset, endOffset, tree, node);
            }
        };
        IElementType iElementType22 = KtTokens.NOINLINE_KEYWORD;
        iElementType22.getClass();
        IElementType iElementType23 = KtTokens.CROSSINLINE_KEYWORD;
        iElementType23.getClass();
        INLINE_PARAMETER_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType22, iElementType23);
        INLINE_FUN_MODIFIER = new InlineFunLightTreePositioningStrategy();
        OPERATOR = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$OPERATOR$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeOperationReference = LightTreePositioningStrategiesKt.operationReference(tree, node);
                if (lighterASTNodeOperationReference == null) {
                    lighterASTNodeOperationReference = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeOperationReference, startOffset, endOffset, tree, node);
            }
        };
        PARAMETER_DEFAULT_VALUE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$PARAMETER_DEFAULT_VALUE$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeDefaultValue = LightTreePositioningStrategiesKt.defaultValue(tree, node);
                if (lighterASTNodeDefaultValue == null) {
                    lighterASTNodeDefaultValue = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeDefaultValue, startOffset, endOffset, tree, node);
            }
        };
        PARAMETERS_WITH_DEFAULT_VALUE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$PARAMETERS_WITH_DEFAULT_VALUE$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                List listValueParameters = LightTreePositioningStrategiesKt.valueParameters(tree, node);
                List arrayList = new ArrayList();
                for (Object obj : listValueParameters) {
                    if (LightTreePositioningStrategiesKt.defaultValue(tree, (LighterASTNode) obj) != null) {
                        arrayList.add(obj);
                    }
                }
                if (arrayList.isEmpty()) {
                    arrayList = null;
                }
                if (arrayList == null) {
                    LighterASTNode lighterASTNodeValueParameterList = LightTreePositioningStrategiesKt.valueParameterList(tree, node);
                    arrayList = lighterASTNodeValueParameterList != null ? CollectionsKt.listOf(lighterASTNodeValueParameterList) : null;
                    if (arrayList == null) {
                        LighterASTNode lighterASTNodeNameIdentifier = LightTreePositioningStrategiesKt.nameIdentifier(tree, node);
                        List listListOf = lighterASTNodeNameIdentifier != null ? CollectionsKt.listOf(lighterASTNodeNameIdentifier) : null;
                        arrayList = listListOf == null ? CollectionsKt.listOf(node) : listListOf;
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    CollectionsKt.addAll(arrayList2, LightTreePositioningStrategyKt.markElement((LighterASTNode) it.next(), startOffset, endOffset, tree, node));
                }
                return arrayList2;
            }
        };
        PARAMETER_VARARG_MODIFIER = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$PARAMETER_VARARG_MODIFIER$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNodeFindChildByType;
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeModifierList = LightTreePositioningStrategiesKt.modifierList(tree, node);
                if (lighterASTNodeModifierList != null) {
                    KtModifierKeywordToken ktModifierKeywordToken = KtTokens.VARARG_KEYWORD;
                    ktModifierKeywordToken.getClass();
                    lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, lighterASTNodeModifierList, (IElementType) ktModifierKeywordToken);
                } else {
                    lighterASTNodeFindChildByType = null;
                }
                if (lighterASTNodeFindChildByType == null) {
                    lighterASTNodeFindChildByType = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
            }
        };
        NAME_OF_NAMED_ARGUMENT = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$NAME_OF_NAMED_ARGUMENT$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                List<TextRange> listMarkElement;
                node.getClass();
                tree.getClass();
                IElementType iElementType24 = KtNodeTypes.VALUE_ARGUMENT_NAME;
                iElementType24.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType24);
                return (lighterASTNodeFindChildByType == null || (listMarkElement = LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node)) == null) ? LightTreePositioningStrategyKt.markElement(node, startOffset, endOffset, tree, node) : listMarkElement;
            }
        };
        VALUE_ARGUMENTS_LIST = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$VALUE_ARGUMENTS_LIST$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType iElementType24 = KtNodeTypes.VALUE_ARGUMENT_LIST;
                iElementType24.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType24);
                if (lighterASTNodeFindChildByType == null) {
                    lighterASTNodeFindChildByType = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
            }
        };
        VALUE_ARGUMENTS = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$VALUE_ARGUMENTS$1
            /* JADX WARN: Code duplicated, block: B:19:0x0057  */
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNodeFindLastChildByType;
                LighterASTNode lighterASTNodeFirstChildExpression;
                node.getClass();
                tree.getClass();
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.BINARY_EXPRESSION)) {
                    TokenSet tokenSet3 = KtTokens.ALL_ASSIGNMENTS;
                    tokenSet3.getClass();
                    if (LightTreePositioningStrategiesKt.findDescendantByTypes(tree, node, tokenSet3) != null && (lighterASTNodeFirstChildExpression = LightTreePositioningStrategiesKt.firstChildExpression(tree, node)) != null) {
                        return LightTreePositioningStrategyKt.markElement(LightTreePositioningStrategiesKt.unwrapParenthesesLabelsAndAnnotations(tree, lighterASTNodeFirstChildExpression), startOffset, endOffset, tree, node);
                    }
                }
                IElementType tokenType = node.getTokenType();
                if (KtTokens.QUALIFIED_ACCESS.contains(tokenType)) {
                    IElementType iElementType24 = KtNodeTypes.CALL_EXPRESSION;
                    iElementType24.getClass();
                    lighterASTNodeFindLastChildByType = LightTreePositioningStrategiesKt.findLastChildByType(tree, node, iElementType24);
                    if (lighterASTNodeFindLastChildByType == null) {
                        lighterASTNodeFindLastChildByType = node;
                    }
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS)) {
                    IElementType iElementType25 = KtNodeTypes.SUPER_TYPE_LIST;
                    iElementType25.getClass();
                    lighterASTNodeFindLastChildByType = LightTreePositioningStrategiesKt.findLastChildByType(tree, node, iElementType25);
                    if (lighterASTNodeFindLastChildByType == null) {
                        lighterASTNodeFindLastChildByType = node;
                    }
                } else {
                    lighterASTNodeFindLastChildByType = node;
                }
                IElementType tokenType2 = lighterASTNodeFindLastChildByType.getTokenType();
                IElementType iElementType26 = KtNodeTypes.VALUE_ARGUMENT_LIST;
                LighterASTNode lighterASTNodeFindChildByType = Intrinsics.areEqual(tokenType2, iElementType26) ? lighterASTNodeFindLastChildByType : null;
                if (lighterASTNodeFindChildByType == null) {
                    iElementType26.getClass();
                    lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, lighterASTNodeFindLastChildByType, iElementType26);
                }
                if (lighterASTNodeFindChildByType == null) {
                    if (!Intrinsics.areEqual(lighterASTNodeFindLastChildByType.getTokenType(), KtNodeTypes.CALL_EXPRESSION)) {
                        return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindLastChildByType, startOffset, endOffset, tree, node);
                    }
                    IElementType iElementType27 = KtNodeTypes.REFERENCE_EXPRESSION;
                    iElementType27.getClass();
                    LighterASTNode lighterASTNodeFindChildByType2 = LightTreePositioningStrategiesKt.findChildByType(tree, lighterASTNodeFindLastChildByType, iElementType27);
                    if (lighterASTNodeFindChildByType2 != null) {
                        lighterASTNodeFindLastChildByType = lighterASTNodeFindChildByType2;
                    }
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindLastChildByType, startOffset, endOffset, tree, node);
                }
                KtSingleValueToken ktSingleValueToken = KtTokens.RPAR;
                ktSingleValueToken.getClass();
                LighterASTNode lighterASTNodeFindLastChildByType2 = LightTreePositioningStrategiesKt.findLastChildByType(tree, lighterASTNodeFindChildByType, ktSingleValueToken);
                if (lighterASTNodeFindLastChildByType2 == null) {
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindLastChildByType, startOffset, endOffset, tree, node);
                }
                IElementType iElementType28 = KtNodeTypes.VALUE_ARGUMENT;
                iElementType28.getClass();
                LighterASTNode lighterASTNodeFindLastChildByType3 = LightTreePositioningStrategiesKt.findLastChildByType(tree, lighterASTNodeFindChildByType, iElementType28);
                if (lighterASTNodeFindLastChildByType3 != null) {
                    return LightTreePositioningStrategyKt.markRange(lighterASTNodeFindLastChildByType3, lighterASTNodeFindLastChildByType2, startOffset, endOffset, tree, node);
                }
                KtSingleValueToken ktSingleValueToken2 = KtTokens.LPAR;
                ktSingleValueToken2.getClass();
                LighterASTNode lighterASTNodeFindLastChildByType4 = LightTreePositioningStrategiesKt.findLastChildByType(tree, lighterASTNodeFindChildByType, ktSingleValueToken2);
                return LightTreePositioningStrategyKt.markRange(lighterASTNodeFindLastChildByType4 == null ? lighterASTNodeFindLastChildByType : lighterASTNodeFindLastChildByType4, lighterASTNodeFindLastChildByType2, startOffset, endOffset, tree, node);
            }
        };
        DOT_BY_QUALIFIED = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$DOT_BY_QUALIFIED$1
            /* JADX WARN: Code duplicated, block: B:14:0x003a  */
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNode;
                FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure;
                node.getClass();
                tree.getClass();
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.BINARY_EXPRESSION)) {
                    TokenSet tokenSet3 = KtTokens.ALL_ASSIGNMENTS;
                    tokenSet3.getClass();
                    if (LightTreePositioningStrategiesKt.findDescendantByTypes(tree, node, tokenSet3) != null) {
                        IElementType iElementType24 = KtNodeTypes.DOT_QUALIFIED_EXPRESSION;
                        iElementType24.getClass();
                        lighterASTNode = node;
                        flyweightCapableTreeStructure = tree;
                        LighterASTNode lighterASTNodeFindDescendantByType$default = LightTreePositioningStrategiesKt.findDescendantByType$default(flyweightCapableTreeStructure, lighterASTNode, iElementType24, false, 4, null);
                        if (lighterASTNodeFindDescendantByType$default != null) {
                            LighterASTNode lighterASTNodeDotOperator = LightTreePositioningStrategiesKt.dotOperator(flyweightCapableTreeStructure, lighterASTNodeFindDescendantByType$default);
                            if (lighterASTNodeDotOperator != null) {
                                lighterASTNodeFindDescendantByType$default = lighterASTNodeDotOperator;
                            }
                            return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindDescendantByType$default, startOffset, endOffset, flyweightCapableTreeStructure, lighterASTNode);
                        }
                    } else {
                        lighterASTNode = node;
                        flyweightCapableTreeStructure = tree;
                    }
                } else {
                    lighterASTNode = node;
                    flyweightCapableTreeStructure = tree;
                }
                if (!Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.DOT_QUALIFIED_EXPRESSION)) {
                    return LightTreePositioningStrategies.INSTANCE.getREFERENCE_BY_QUALIFIED().mark(lighterASTNode, startOffset, endOffset, flyweightCapableTreeStructure);
                }
                LighterASTNode lighterASTNodeDotOperator2 = LightTreePositioningStrategiesKt.dotOperator(flyweightCapableTreeStructure, lighterASTNode);
                if (lighterASTNodeDotOperator2 == null) {
                    lighterASTNodeDotOperator2 = lighterASTNode;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeDotOperator2, startOffset, endOffset, flyweightCapableTreeStructure, lighterASTNode);
            }
        };
        SELECTOR_BY_QUALIFIED = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$SELECTOR_BY_QUALIFIED$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNode;
                FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure;
                LighterASTNode lighterASTNodeReferencedTypeExpression;
                LighterASTNode lighterASTNodeFindExpressionDeep;
                node.getClass();
                tree.getClass();
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.BINARY_EXPRESSION)) {
                    TokenSet tokenSet3 = KtTokens.ALL_ASSIGNMENTS;
                    tokenSet3.getClass();
                    if (LightTreePositioningStrategiesKt.findDescendantByTypes(tree, node, tokenSet3) != null && (lighterASTNodeFindExpressionDeep = LightTreePositioningStrategiesKt.findExpressionDeep(tree, node)) != null) {
                        return mark(lighterASTNodeFindExpressionDeep, lighterASTNodeFindExpressionDeep.getStartOffset(), lighterASTNodeFindExpressionDeep.getEndOffset(), tree);
                    }
                }
                if (KtTokens.QUALIFIED_ACCESS.contains(node.getTokenType())) {
                    LighterASTNode lighterASTNodeSelector = LightTreePositioningStrategiesKt.selector(tree, node);
                    return lighterASTNodeSelector != null ? LightTreePositioningStrategyKt.markElement(lighterASTNodeSelector, startOffset, endOffset, tree, node) : super.mark(node, startOffset, endOffset, tree);
                }
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.IMPORT_DIRECTIVE)) {
                    IElementType iElementType24 = KtNodeTypes.REFERENCE_EXPRESSION;
                    iElementType24.getClass();
                    lighterASTNode = node;
                    flyweightCapableTreeStructure = tree;
                    LighterASTNode lighterASTNode2 = (LighterASTNode) CollectionsKt.lastOrNull(LightTreePositioningStrategiesKt.collectDescendantsOfType$default(flyweightCapableTreeStructure, lighterASTNode, iElementType24, null, 4, null));
                    if (lighterASTNode2 != null) {
                        return mark(lighterASTNode2, lighterASTNode2.getStartOffset(), lighterASTNode2.getEndOffset(), flyweightCapableTreeStructure);
                    }
                } else {
                    lighterASTNode = node;
                    flyweightCapableTreeStructure = tree;
                }
                if (Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.TYPE_REFERENCE)) {
                    TokenSet tokenSet4 = KtTokenSets.TYPE_ELEMENT_TYPES;
                    tokenSet4.getClass();
                    LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(flyweightCapableTreeStructure, lighterASTNode, tokenSet4);
                    if (lighterASTNodeFindChildByType != null && (lighterASTNodeReferencedTypeExpression = LightTreePositioningStrategies.INSTANCE.referencedTypeExpression(flyweightCapableTreeStructure, lighterASTNodeFindChildByType)) != null) {
                        return LightTreePositioningStrategyKt.markElement(lighterASTNodeReferencedTypeExpression, startOffset, endOffset, flyweightCapableTreeStructure, lighterASTNode);
                    }
                }
                return super.mark(lighterASTNode, startOffset, endOffset, flyweightCapableTreeStructure);
            }
        };
        FUN_INTERFACE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$FUN_INTERFACE$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType tokenType = node.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS)) {
                    return LightTreePositioningStrategies.INSTANCE.getFUN_MODIFIER().mark(node, startOffset, endOffset, tree);
                }
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY)) {
                    return LightTreePositioningStrategies.INSTANCE.getVAL_OR_VAR_NODE().mark(node, startOffset, endOffset, tree);
                }
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUN)) {
                    return LightTreePositioningStrategiesKt.typeParametersList(tree, node) != null ? LightTreePositioningStrategies.INSTANCE.getTYPE_PARAMETERS_LIST().mark(node, startOffset, endOffset, tree) : LightTreePositioningStrategies.SUSPEND_OR_FUN_MODIFIER.mark(node, startOffset, endOffset, tree);
                }
                return LightTreePositioningStrategies.INSTANCE.getDEFAULT().mark(node, startOffset, endOffset, tree);
            }
        };
        REFERENCE_BY_QUALIFIED = new FindReferencePositioningStrategy(false);
        REFERENCED_NAME_BY_QUALIFIED = new FindReferencePositioningStrategy(true);
        IElementType iElementType24 = KtNodeTypes.IS_EXPRESSION;
        IElementType iElementType25 = KtNodeTypes.BINARY_WITH_TYPE;
        IElementType iElementType26 = KtNodeTypes.BINARY_EXPRESSION;
        nodeTypesWithOperation = SetsKt.setOf(new IElementType[]{iElementType24, iElementType25, iElementType26, KtNodeTypes.POSTFIX_EXPRESSION, KtNodeTypes.PREFIX_EXPRESSION, iElementType26, KtNodeTypes.WHEN_CONDITION_IN_RANGE});
        WHEN_EXPRESSION = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$WHEN_EXPRESSION$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeWhenKeyword = LightTreePositioningStrategiesKt.whenKeyword(tree, node);
                if (lighterASTNodeWhenKeyword == null) {
                    lighterASTNodeWhenKeyword = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeWhenKeyword, startOffset, endOffset, tree, node);
            }
        };
        WHEN_GUARD = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$WHEN_GUARD$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType iElementType27 = KtNodeTypes.WHEN_ENTRY_GUARD;
                iElementType27.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType27);
                if (lighterASTNodeFindChildByType == null) {
                    lighterASTNodeFindChildByType = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
            }
        };
        IF_EXPRESSION = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$IF_EXPRESSION$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeIfKeyword = LightTreePositioningStrategiesKt.ifKeyword(tree, node);
                if (lighterASTNodeIfKeyword == null) {
                    lighterASTNodeIfKeyword = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeIfKeyword, startOffset, endOffset, tree, node);
            }
        };
        ELSE_ENTRY = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$ELSE_ENTRY$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeElseKeyword = LightTreePositioningStrategiesKt.elseKeyword(tree, node);
                if (lighterASTNodeElseKeyword == null) {
                    lighterASTNodeElseKeyword = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeElseKeyword, startOffset, endOffset, tree, node);
            }
        };
        ARRAY_ACCESS = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$ARRAY_ACCESS$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType iElementType27 = KtNodeTypes.INDICES;
                iElementType27.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType27);
                lighterASTNodeFindChildByType.getClass();
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
            }
        };
        SAFE_ACCESS = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$SAFE_ACCESS$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeSafeAccess = LightTreePositioningStrategiesKt.safeAccess(tree, node);
                if (lighterASTNodeSafeAccess == null) {
                    lighterASTNodeSafeAccess = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeSafeAccess, startOffset, endOffset, tree, node);
            }
        };
        ?? r0 = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$OPERATION_TO_END$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNode;
                LighterASTNode lighterASTNode2;
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeOperationReference = LightTreePositioningStrategiesKt.operationReference(tree, node);
                LighterASTNode lighterASTNode3 = lighterASTNodeOperationReference == null ? node : lighterASTNodeOperationReference;
                LighterASTNode lighterASTNodeLastChild = LightTreePositioningStrategiesKt.lastChild(tree, node);
                if (lighterASTNodeLastChild == null) {
                    lighterASTNode = node;
                    lighterASTNode2 = lighterASTNode;
                } else {
                    lighterASTNode = lighterASTNodeLastChild;
                    lighterASTNode2 = node;
                }
                return LightTreePositioningStrategyKt.markRange(lighterASTNode3, lighterASTNode, startOffset, endOffset, tree, lighterASTNode2);
            }
        };
        OPERATION_TO_END = r0;
        ?? r5 = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$START_TO_OPERATOR$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNode;
                LighterASTNode lighterASTNode2;
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeFirstChild = LightTreePositioningStrategiesKt.firstChild(tree, node);
                LighterASTNode lighterASTNode3 = lighterASTNodeFirstChild == null ? node : lighterASTNodeFirstChild;
                LighterASTNode lighterASTNodeOperationReference = LightTreePositioningStrategiesKt.operationReference(tree, node);
                if (lighterASTNodeOperationReference == null) {
                    lighterASTNode = node;
                    lighterASTNode2 = lighterASTNode;
                } else {
                    lighterASTNode = lighterASTNodeOperationReference;
                    lighterASTNode2 = node;
                }
                return LightTreePositioningStrategyKt.markRange(lighterASTNode3, lighterASTNode, startOffset, endOffset, tree, lighterASTNode2);
            }
        };
        START_TO_OPERATOR = r5;
        AS_TYPE = r0;
        USELESS_ELVIS = r0;
        USELESS_ELVIS_LEFT = r5;
        RETURN_WITH_LABEL = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$RETURN_WITH_LABEL$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType iElementType27 = KtNodeTypes.LABEL_QUALIFIER;
                iElementType27.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType27);
                if (lighterASTNodeFindChildByType != null) {
                    return LightTreePositioningStrategyKt.markRange(node, lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
                }
                LighterASTNode lighterASTNodeReturnKeyword = LightTreePositioningStrategiesKt.returnKeyword(tree, node);
                if (lighterASTNodeReturnKeyword == null) {
                    lighterASTNodeReturnKeyword = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeReturnKeyword, startOffset, endOffset, tree, node);
            }
        };
        WHOLE_ELEMENT = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$WHOLE_ELEMENT$1
        };
        LONG_LITERAL_SUFFIX = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$LONG_LITERAL_SUFFIX$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                return Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.INTEGER_CONSTANT) ? CollectionsKt.listOf(TextRange.create(endOffset - 1, endOffset)) : super.mark(node, startOffset, endOffset, tree);
            }
        };
        IElementType iElementType27 = KtTokens.REIFIED_KEYWORD;
        iElementType27.getClass();
        REIFIED_MODIFIER = new ModifierSetBasedLightTreePositioningStrategy(iElementType27);
        TYPE_PARAMETERS_LIST = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$TYPE_PARAMETERS_LIST$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeTypeParametersList = LightTreePositioningStrategiesKt.typeParametersList(tree, node);
                if (lighterASTNodeTypeParametersList == null) {
                    lighterASTNodeTypeParametersList = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeTypeParametersList, startOffset, endOffset, tree, node);
            }
        };
        FUNCTION_TYPE_RECEIVER = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$FUNCTION_TYPE_RECEIVER$1
            /* JADX WARN: Code duplicated, block: B:38:0x008c  */
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNode;
                List children;
                Object next;
                List children2;
                LighterASTNode lighterASTNode2;
                List children3;
                node.getClass();
                tree.getClass();
                Object obj = null;
                LighterASTNode lighterASTNode3 = Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.VALUE_PARAMETER) ? node : null;
                if (lighterASTNode3 == null || (children = LightTreeUtilsKt.getChildren(lighterASTNode3, tree)) == null) {
                    lighterASTNode = node;
                } else {
                    Iterator it = children.iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!Intrinsics.areEqual(((LighterASTNode) next).getTokenType(), KtNodeTypes.TYPE_REFERENCE));
                    LighterASTNode lighterASTNode4 = (LighterASTNode) next;
                    if (lighterASTNode4 == null || (children2 = LightTreeUtilsKt.getChildren(lighterASTNode4, tree)) == null || (lighterASTNode2 = (LighterASTNode) CollectionsKt.firstOrNull(children2)) == null) {
                        lighterASTNode = node;
                    } else {
                        if (!Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.FUNCTION_TYPE)) {
                            lighterASTNode2 = null;
                        }
                        if (lighterASTNode2 == null || (children3 = LightTreeUtilsKt.getChildren(lighterASTNode2, tree)) == null) {
                            lighterASTNode = node;
                        } else {
                            for (Object obj2 : children3) {
                                if (Intrinsics.areEqual(((LighterASTNode) obj2).getTokenType(), KtNodeTypes.FUNCTION_TYPE_RECEIVER)) {
                                    obj = obj2;
                                    break;
                                }
                            }
                            lighterASTNode = (LighterASTNode) obj;
                            if (lighterASTNode == null) {
                                lighterASTNode = node;
                            }
                        }
                    }
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNode, startOffset, endOffset, tree, node);
            }
        };
        NAME_IDENTIFIER = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$NAME_IDENTIFIER$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNode;
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeNameIdentifier = LightTreePositioningStrategiesKt.nameIdentifier(tree, node);
                if (lighterASTNodeNameIdentifier != null) {
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeNameIdentifier, startOffset, endOffset, tree, node);
                }
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.LABEL_QUALIFIER)) {
                    return super.mark(node, startOffset, endOffset - 1, tree);
                }
                return (!Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.PACKAGE_DIRECTIVE) || (lighterASTNode = (LighterASTNode) CollectionsKt.lastOrNull(LightTreeUtilsKt.getChildren(node, tree))) == null) ? LightTreePositioningStrategies.INSTANCE.getDEFAULT().mark(node, startOffset, endOffset, tree) : LightTreePositioningStrategyKt.markElement(lighterASTNode, startOffset, endOffset, tree, node);
            }
        };
        REDUNDANT_NULLABLE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$REDUNDANT_NULLABLE$1
            private final LighterASTNode getNullableChild(FlyweightCapableTreeStructure<LighterASTNode> tree, LighterASTNode node, Ref<LighterASTNode[]> ref) {
                tree.getChildren(node, ref);
                Object obj = ref.get();
                obj.getClass();
                LighterASTNode lighterASTNode = (LighterASTNode) ArraysKt.firstOrNull((Object[]) obj);
                if (lighterASTNode != null && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.NULLABLE_TYPE)) {
                    return lighterASTNode;
                }
                return null;
            }

            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNode;
                LighterASTNode lighterASTNode2;
                LighterASTNode lighterASTNode3;
                node.getClass();
                tree.getClass();
                Ref<LighterASTNode[]> ref = new Ref<>();
                LighterASTNode lighterASTNode4 = null;
                LighterASTNode nullableChild = node;
                LighterASTNode lighterASTNode5 = null;
                LighterASTNode lighterASTNode6 = null;
                while (nullableChild != null) {
                    nullableChild = getNullableChild(tree, nullableChild, ref);
                    Object obj = ref.get();
                    obj.getClass();
                    LighterASTNode lighterASTNode7 = (LighterASTNode) ArraysKt.getOrNull((Object[]) obj, 1);
                    if (lighterASTNode6 == null) {
                        lighterASTNode6 = lighterASTNode7;
                        lighterASTNode4 = lighterASTNode5;
                        lighterASTNode5 = lighterASTNode6;
                    } else {
                        LighterASTNode lighterASTNode8 = lighterASTNode5;
                        lighterASTNode5 = lighterASTNode7;
                        lighterASTNode4 = lighterASTNode8;
                    }
                }
                if (lighterASTNode4 == null) {
                    lighterASTNode = lighterASTNode6 == null ? node : lighterASTNode6;
                } else {
                    lighterASTNode = lighterASTNode4;
                }
                if (lighterASTNode6 == null) {
                    lighterASTNode3 = node;
                    lighterASTNode2 = lighterASTNode3;
                } else {
                    lighterASTNode2 = node;
                    lighterASTNode3 = lighterASTNode6;
                }
                return LightTreePositioningStrategyKt.markRange(lighterASTNode, lighterASTNode3, startOffset, endOffset, tree, lighterASTNode2);
            }
        };
        QUESTION_MARK_BY_TYPE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$QUESTION_MARK_BY_TYPE$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.TYPE_REFERENCE)) {
                    IElementType iElementType28 = KtNodeTypes.NULLABLE_TYPE;
                    iElementType28.getClass();
                    LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType28);
                    if (lighterASTNodeFindChildByType != null) {
                        KtSingleValueToken ktSingleValueToken = KtTokens.QUEST;
                        ktSingleValueToken.getClass();
                        LighterASTNode lighterASTNodeFindChildByType2 = LightTreePositioningStrategiesKt.findChildByType(tree, lighterASTNodeFindChildByType, (IElementType) ktSingleValueToken);
                        if (lighterASTNodeFindChildByType2 != null) {
                            return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType2, startOffset, endOffset, tree, node);
                        }
                    }
                }
                return super.mark(node, startOffset, endOffset, tree);
            }
        };
        ANNOTATION_USE_SITE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$ANNOTATION_USE_SITE$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType iElementType28 = KtNodeTypes.ANNOTATION_TARGET;
                iElementType28.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType28);
                return lighterASTNodeFindChildByType != null ? LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node) : super.mark(node, startOffset, endOffset, tree);
            }
        };
        IMPORT_LAST_NAME = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$IMPORT_LAST_NAME$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNode;
                FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure;
                LighterASTNode lighterASTNode2;
                node.getClass();
                tree.getClass();
                if (Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.IMPORT_DIRECTIVE)) {
                    IElementType iElementType28 = KtNodeTypes.REFERENCE_EXPRESSION;
                    iElementType28.getClass();
                    lighterASTNode = node;
                    flyweightCapableTreeStructure = tree;
                    lighterASTNode2 = (LighterASTNode) CollectionsKt.lastOrNull(LightTreePositioningStrategiesKt.collectDescendantsOfType$default(flyweightCapableTreeStructure, lighterASTNode, iElementType28, null, 4, null));
                    if (lighterASTNode2 == null) {
                    }
                    return LightTreePositioningStrategyKt.markElement(lighterASTNode2, startOffset, endOffset, flyweightCapableTreeStructure, lighterASTNode);
                }
                lighterASTNode = node;
                flyweightCapableTreeStructure = tree;
                lighterASTNode2 = lighterASTNode;
                return LightTreePositioningStrategyKt.markElement(lighterASTNode2, startOffset, endOffset, flyweightCapableTreeStructure, lighterASTNode);
            }
        };
        IMPORT_LAST_BUT_ONE_NAME = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$IMPORT_LAST_BUT_ONE_NAME$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType iElementType28 = KtNodeTypes.REFERENCE_EXPRESSION;
                iElementType28.getClass();
                List listCollectDescendantsOfType$default = LightTreePositioningStrategiesKt.collectDescendantsOfType$default(tree, node, iElementType28, null, 4, null);
                LighterASTNode lighterASTNode = (LighterASTNode) CollectionsKt.getOrNull(listCollectDescendantsOfType$default, listCollectDescendantsOfType$default.size() - 2);
                if (lighterASTNode == null) {
                    lighterASTNode = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNode, startOffset, endOffset, tree, node);
            }
        };
        IMPORT_ALIAS = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$IMPORT_ALIAS$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType iElementType28 = KtNodeTypes.IMPORT_ALIAS;
                iElementType28.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType28);
                if (lighterASTNodeFindChildByType != null) {
                    KtToken ktToken = KtTokens.IDENTIFIER;
                    ktToken.getClass();
                    LighterASTNode lighterASTNodeFindChildByType2 = LightTreePositioningStrategiesKt.findChildByType(tree, lighterASTNodeFindChildByType, (IElementType) ktToken);
                    if (lighterASTNodeFindChildByType2 != null) {
                        return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType2, startOffset, endOffset, tree, node);
                    }
                }
                return LightTreePositioningStrategies.INSTANCE.getIMPORT_LAST_NAME().mark(node, startOffset, endOffset, tree);
            }
        };
        SPREAD_OPERATOR = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$SPREAD_OPERATOR$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                List<TextRange> listMarkElement;
                node.getClass();
                tree.getClass();
                KtSingleValueToken ktSingleValueToken = KtTokens.MUL;
                ktSingleValueToken.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, (IElementType) ktSingleValueToken);
                return (lighterASTNodeFindChildByType == null || (listMarkElement = LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node)) == null) ? super.mark(node, startOffset, endOffset, tree) : listMarkElement;
            }
        };
        DECLARATION_WITH_BODY = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$DECLARATION_WITH_BODY$1
            private final TextRange lastSymbol(TextRange range) {
                if (range.isEmpty()) {
                    return range;
                }
                TextRange textRangeCreate = TextRange.create(range.getEndOffset() - 1, range.getEndOffset());
                textRangeCreate.getClass();
                return textRangeCreate;
            }

            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNodeFindChildByType;
                node.getClass();
                tree.getClass();
                IElementType tokenType = node.getTokenType();
                IElementType iElementType28 = KtNodeTypes.BLOCK;
                if (Intrinsics.areEqual(tokenType, iElementType28)) {
                    lighterASTNodeFindChildByType = node;
                } else {
                    iElementType28.getClass();
                    lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType28);
                }
                LighterASTNode lighterASTNode = lighterASTNodeFindChildByType == null ? node : lighterASTNodeFindChildByType;
                KtSingleValueToken ktSingleValueToken = KtTokens.RBRACE;
                ktSingleValueToken.getClass();
                LighterASTNode lighterASTNodeFindLastChildByType = LightTreePositioningStrategiesKt.findLastChildByType(tree, lighterASTNode, ktSingleValueToken);
                if (lighterASTNodeFindLastChildByType != null) {
                    return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindLastChildByType, startOffset, endOffset, tree, node);
                }
                if (lighterASTNodeFindChildByType == null) {
                    return super.mark(node, startOffset, endOffset, tree);
                }
                List<TextRange> listMarkElement = LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listMarkElement, 10));
                Iterator<T> it = listMarkElement.iterator();
                while (it.hasNext()) {
                    arrayList.add(lastSymbol((TextRange) it.next()));
                }
                return arrayList;
            }
        };
        UNREACHABLE_CODE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$UNREACHABLE_CODE$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> markKtDiagnostic(KtSourceElement element, KtDiagnostic diagnostic) {
                element.getClass();
                diagnostic.getClass();
                KtDiagnosticWithParameters2 ktDiagnosticWithParameters2 = (KtDiagnosticWithParameters2) diagnostic;
                UnreachableCodeLightTreeHelper unreachableCodeLightTreeHelper = new UnreachableCodeLightTreeHelper(element.getTreeStructure());
                Iterable iterable = (Iterable) ktDiagnosticWithParameters2.getA();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(((KtSourceElement) it.next()).getLighterASTNode());
                }
                Set<? extends LighterASTNode> set = CollectionsKt.toSet(arrayList);
                Iterable iterable2 = (Iterable) ktDiagnosticWithParameters2.getB();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                Iterator it2 = iterable2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((KtSourceElement) it2.next()).getLighterASTNode());
                }
                Set<? extends LighterASTNode> set2 = CollectionsKt.toSet(arrayList2);
                if (!unreachableCodeLightTreeHelper.hasChildrenInSet(element.getLighterASTNode(), set)) {
                    return super.markKtDiagnostic(element, diagnostic);
                }
                List<LighterASTNode> listRemoveReachableElementsWithMeaninglessSiblings = unreachableCodeLightTreeHelper.removeReachableElementsWithMeaninglessSiblings(unreachableCodeLightTreeHelper.getLeavesOrReachableChildren(element.getLighterASTNode(), set, set2), set);
                if (listRemoveReachableElementsWithMeaninglessSiblings.isEmpty()) {
                    return super.markKtDiagnostic(element, diagnostic);
                }
                ArrayList arrayList3 = new ArrayList();
                Iterator<T> it3 = listRemoveReachableElementsWithMeaninglessSiblings.iterator();
                while (it3.hasNext()) {
                    CollectionsKt.addAll(arrayList3, LightTreePositioningStrategyKt.markElement((LighterASTNode) it3.next(), element.getStartOffset(), element.getEndOffset(), element.getTreeStructure(), element.getLighterASTNode()));
                }
                return unreachableCodeLightTreeHelper.mergeAdjacentTextRanges(arrayList3);
            }
        };
        NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNodeInlineModifier;
                node.getClass();
                tree.getClass();
                IElementType tokenType = node.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS)) {
                    KtKeywordToken ktKeywordToken = KtTokens.CLASS_KEYWORD;
                    ktKeywordToken.getClass();
                    lighterASTNodeInlineModifier = LightTreePositioningStrategiesKt.findChildByType(tree, node, (IElementType) ktKeywordToken);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_DECLARATION)) {
                    KtKeywordToken ktKeywordToken2 = KtTokens.OBJECT_KEYWORD;
                    ktKeywordToken2.getClass();
                    lighterASTNodeInlineModifier = LightTreePositioningStrategiesKt.findChildByType(tree, node, (IElementType) ktKeywordToken2);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUN)) {
                    lighterASTNodeInlineModifier = LightTreePositioningStrategiesKt.inlineModifier(tree, node);
                    if (lighterASTNodeInlineModifier == null) {
                        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.FUN_KEYWORD;
                        ktModifierKeywordToken.getClass();
                        lighterASTNodeInlineModifier = LightTreePositioningStrategiesKt.findChildByType(tree, node, (IElementType) ktModifierKeywordToken);
                    }
                } else {
                    lighterASTNodeInlineModifier = node;
                }
                if (lighterASTNodeInlineModifier == null) {
                    lighterASTNodeInlineModifier = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeInlineModifier, startOffset, endOffset, tree, node);
            }
        };
        LABEL = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$LABEL$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType iElementType28 = KtNodeTypes.LABEL_QUALIFIER;
                iElementType28.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType28);
                if (lighterASTNodeFindChildByType == null) {
                    lighterASTNodeFindChildByType = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
            }
        };
        COMMAS = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$COMMAS$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public boolean isValid(LighterASTNode node, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                return true;
            }

            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNode;
                int i;
                int i2;
                FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure;
                node.getClass();
                tree.getClass();
                List listCreateListBuilder = CollectionsKt.createListBuilder();
                Ref ref = new Ref();
                tree.getChildren(node, ref);
                Object obj = ref.get();
                obj.getClass();
                LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) obj;
                int length = lighterASTNodeArr.length;
                int i3 = 0;
                while (i3 < length) {
                    LighterASTNode lighterASTNode2 = lighterASTNodeArr[i3];
                    if (lighterASTNode2 == null || !Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtTokens.COMMA)) {
                        lighterASTNode = node;
                        i = startOffset;
                        i2 = endOffset;
                        flyweightCapableTreeStructure = tree;
                    } else {
                        lighterASTNode = node;
                        i = startOffset;
                        i2 = endOffset;
                        flyweightCapableTreeStructure = tree;
                        listCreateListBuilder.add(LightTreePositioningStrategyKt.markSingleElement(lighterASTNode2, lighterASTNode2, i, i2, flyweightCapableTreeStructure, lighterASTNode));
                    }
                    i3++;
                    startOffset = i;
                    endOffset = i2;
                    tree = flyweightCapableTreeStructure;
                    node = lighterASTNode;
                }
                return CollectionsKt.build(listCreateListBuilder);
            }
        };
        TokenSet tokenSetCreate = TokenSet.create(new IElementType[]{iElementType, iElementType2, KtTokens.SEALED_KEYWORD});
        tokenSetCreate.getClass();
        NON_FINAL_MODIFIER_OR_NAME = new ModifierSetBasedLightTreePositioningStrategy(tokenSetCreate);
        DELEGATED_SUPERTYPE_BY_KEYWORD = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$DELEGATED_SUPERTYPE_BY_KEYWORD$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                Object next;
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNode = (LighterASTNode) tree.getParent(node);
                if (lighterASTNode == null || !Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.DELEGATED_SUPER_TYPE_ENTRY)) {
                    return super.mark(node, startOffset, endOffset, tree);
                }
                Iterator it = LightTreeUtilsKt.getChildren(lighterASTNode, tree).iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(((LighterASTNode) next).getTokenType(), KtTokens.BY_KEYWORD));
                LighterASTNode lighterASTNode2 = (LighterASTNode) next;
                if (lighterASTNode2 == null) {
                    lighterASTNode2 = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNode2, startOffset, endOffset, tree, node);
            }
        };
        CALL_ELEMENT_WITH_DOT = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$CALL_ELEMENT_WITH_DOT$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LightTreePositioningStrategies lightTreePositioningStrategies = LightTreePositioningStrategies.INSTANCE;
                List<TextRange> listMark = lightTreePositioningStrategies.getSELECTOR_BY_QUALIFIED().mark(node, startOffset, endOffset, tree);
                if (listMark.size() != 1) {
                    return listMark;
                }
                TextRange textRange = (TextRange) CollectionsKt.first(listMark);
                List<TextRange> listMark2 = lightTreePositioningStrategies.getSAFE_ACCESS().mark(node, startOffset, endOffset, tree);
                return listMark2.size() == 1 ? CollectionsKt.listOf(new TextRange(((TextRange) CollectionsKt.first(listMark2)).getStartOffset(), textRange.getEndOffset())) : listMark2;
            }
        };
        TYPEALIAS_TYPE_REFERENCE = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$TYPEALIAS_TYPE_REFERENCE$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                IElementType iElementType28 = KtNodeTypes.TYPE_REFERENCE;
                iElementType28.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType28);
                if (lighterASTNodeFindChildByType == null) {
                    lighterASTNodeFindChildByType = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
            }
        };
        SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC$1
            /* JADX WARN: Code duplicated, block: B:14:0x0046  */
            /* JADX WARN: Code duplicated, block: B:16:0x0049  */
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                LighterASTNode lighterASTNodeFindChildByType;
                node.getClass();
                tree.getClass();
                IElementType tokenType = node.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.ENUM_ENTRY)) {
                    IElementType iElementType28 = KtNodeTypes.INITIALIZER_LIST;
                    iElementType28.getClass();
                    lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType28);
                    if (lighterASTNodeFindChildByType == null) {
                        lighterASTNodeFindChildByType = node;
                    }
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    IElementType iElementType29 = KtNodeTypes.CONSTRUCTOR_CALLEE;
                    iElementType29.getClass();
                    LighterASTNode parentIfTypeIs = LightTreePositioningStrategiesKt.getParentIfTypeIs(node, iElementType29, tree);
                    if (parentIfTypeIs != null) {
                        IElementType iElementType30 = KtNodeTypes.SUPER_TYPE_CALL_ENTRY;
                        iElementType30.getClass();
                        LighterASTNode parentIfTypeIs2 = LightTreePositioningStrategiesKt.getParentIfTypeIs(parentIfTypeIs, iElementType30, tree);
                        if (parentIfTypeIs2 != null) {
                            IElementType iElementType31 = KtNodeTypes.VALUE_ARGUMENT_LIST;
                            iElementType31.getClass();
                            lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, parentIfTypeIs2, iElementType31);
                        } else {
                            lighterASTNodeFindChildByType = null;
                        }
                    } else {
                        lighterASTNodeFindChildByType = null;
                    }
                    if (lighterASTNodeFindChildByType == null) {
                        lighterASTNodeFindChildByType = node;
                    }
                } else {
                    lighterASTNodeFindChildByType = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
            }
        };
        TYPE_ARGUMENT_LIST_OR_SELF = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$TYPE_ARGUMENT_LIST_OR_SELF$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeSelector = LightTreePositioningStrategiesKt.selector(tree, node);
                if (lighterASTNodeSelector == null) {
                    lighterASTNodeSelector = node;
                }
                IElementType iElementType28 = KtNodeTypes.TYPE_ARGUMENT_LIST;
                iElementType28.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, lighterASTNodeSelector, iElementType28);
                if (lighterASTNodeFindChildByType == null) {
                    lighterASTNodeFindChildByType = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
            }
        };
        TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNodeSelector = LightTreePositioningStrategiesKt.selector(tree, node);
                LighterASTNode lighterASTNode = lighterASTNodeSelector == null ? node : lighterASTNodeSelector;
                IElementType iElementType28 = KtNodeTypes.TYPE_ARGUMENT_LIST;
                iElementType28.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, lighterASTNode, iElementType28);
                if (lighterASTNodeFindChildByType != null) {
                    lighterASTNodeSelector = lighterASTNodeFindChildByType;
                } else if (lighterASTNodeSelector == null) {
                    lighterASTNodeSelector = node;
                }
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeSelector, startOffset, endOffset, tree, node);
            }
        };
        PACKAGE_DIRECTIVE_NAME_EXPRESSION = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$PACKAGE_DIRECTIVE_NAME_EXPRESSION$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                if (!Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.PACKAGE_DIRECTIVE)) {
                    return super.mark(node, startOffset, endOffset, tree);
                }
                IElementType iElementType28 = KtNodeTypes.DOT_QUALIFIED_EXPRESSION;
                iElementType28.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, iElementType28);
                if (lighterASTNodeFindChildByType != null) {
                    node = lighterASTNodeFindChildByType;
                }
                return super.mark(node, node.getStartOffset(), node.getEndOffset(), tree);
            }
        };
        OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode assignmentLhsIfUnwrappable = SourceElementUtilsKt.getAssignmentLhsIfUnwrappable(node, tree);
                return assignmentLhsIfUnwrappable == null ? super.mark(node, startOffset, endOffset, tree) : super.mark(assignmentLhsIfUnwrappable, assignmentLhsIfUnwrappable.getStartOffset(), assignmentLhsIfUnwrappable.getEndOffset(), tree);
            }
        };
        DEPRECATION = new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies$DEPRECATION$1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                return Intrinsics.areEqual(node.getTokenType(), KtNodeTypes.TYPE_REFERENCE) ? LightTreePositioningStrategies.INSTANCE.getSELECTOR_BY_QUALIFIED().mark(node, startOffset, endOffset, tree) : LightTreePositioningStrategies.INSTANCE.getREFERENCED_NAME_BY_QUALIFIED().mark(node, startOffset, endOffset, tree);
            }
        };
    }

    private LightTreePositioningStrategies() {
    }

    public static LighterASTNode a(FlyweightCapableTreeStructure flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        return LightTreePositioningStrategiesKt.objectKeyword(flyweightCapableTreeStructure, lighterASTNode);
    }

    public static LighterASTNode b(FlyweightCapableTreeStructure flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        return LightTreePositioningStrategiesKt.fieldKeyword(flyweightCapableTreeStructure, lighterASTNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LighterASTNode findStartingASTNodeForDeclarationName(FlyweightCapableTreeStructure<LighterASTNode> tree, LighterASTNode node) {
        LighterASTNode lighterASTNodeModifierList = LightTreePositioningStrategiesKt.modifierList(tree, node);
        if (lighterASTNodeModifierList != null) {
            KtModifierKeywordToken ktModifierKeywordToken = KtTokens.ENUM_KEYWORD;
            ktModifierKeywordToken.getClass();
            LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, lighterASTNodeModifierList, (IElementType) ktModifierKeywordToken);
            if (lighterASTNodeFindChildByType != null) {
                return lighterASTNodeFindChildByType;
            }
        }
        TokenSet tokenSetCreate = TokenSet.create(new IElementType[]{KtTokens.CLASS_KEYWORD, KtTokens.OBJECT_KEYWORD});
        tokenSetCreate.getClass();
        LighterASTNode lighterASTNodeFindChildByType2 = LightTreePositioningStrategiesKt.findChildByType(tree, node, tokenSetCreate);
        return lighterASTNodeFindChildByType2 == null ? node : lighterASTNodeFindChildByType2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDeclaration(LighterASTNode lighterASTNode) {
        IElementType tokenType = lighterASTNode.getTokenType();
        return Intrinsics.areEqual(tokenType, KtNodeTypes.PRIMARY_CONSTRUCTOR) || Intrinsics.areEqual(tokenType, KtNodeTypes.SECONDARY_CONSTRUCTOR) || Intrinsics.areEqual(tokenType, KtNodeTypes.FUN) || Intrinsics.areEqual(tokenType, KtNodeTypes.FUNCTION_LITERAL) || Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY) || Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY_ACCESSOR) || Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS) || Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_DECLARATION) || Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS_INITIALIZER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LighterASTNode referencedTypeExpression(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        IElementType tokenType = lighterASTNode.getTokenType();
        if (!Intrinsics.areEqual(tokenType, KtNodeTypes.USER_TYPE)) {
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.NULLABLE_TYPE)) {
                TokenSet tokenSet = KtTokenSets.TYPE_ELEMENT_TYPES;
                tokenSet.getClass();
                LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(flyweightCapableTreeStructure, lighterASTNode, tokenSet);
                if (lighterASTNodeFindChildByType != null) {
                    return INSTANCE.referencedTypeExpression(flyweightCapableTreeStructure, lighterASTNodeFindChildByType);
                }
            }
            return null;
        }
        IElementType iElementType = KtNodeTypes.REFERENCE_EXPRESSION;
        iElementType.getClass();
        LighterASTNode lighterASTNodeFindChildByType2 = LightTreePositioningStrategiesKt.findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType);
        if (lighterASTNodeFindChildByType2 != null) {
            return lighterASTNodeFindChildByType2;
        }
        IElementType iElementType2 = KtNodeTypes.ENUM_ENTRY_SUPERCLASS_REFERENCE_EXPRESSION;
        iElementType2.getClass();
        return LightTreePositioningStrategiesKt.findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType2);
    }

    public final LightTreePositioningStrategy getABSTRACT_MODIFIER() {
        return ABSTRACT_MODIFIER;
    }

    public final LightTreePositioningStrategy getACTUAL_DECLARATION_NAME() {
        return ACTUAL_DECLARATION_NAME;
    }

    public final LightTreePositioningStrategy getANNOTATION_USE_SITE() {
        return ANNOTATION_USE_SITE;
    }

    public final LightTreePositioningStrategy getARRAY_ACCESS() {
        return ARRAY_ACCESS;
    }

    public final LightTreePositioningStrategy getAS_TYPE() {
        return AS_TYPE;
    }

    public final LightTreePositioningStrategy getCALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS() {
        return CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS;
    }

    public final LightTreePositioningStrategy getCALL_ELEMENT_WITH_DOT() {
        return CALL_ELEMENT_WITH_DOT;
    }

    public final LightTreePositioningStrategy getCOMMAS() {
        return COMMAS;
    }

    public final LightTreePositioningStrategy getCOMPANION_OBJECT() {
        return COMPANION_OBJECT;
    }

    public final LightTreePositioningStrategy getCONST_MODIFIER() {
        return CONST_MODIFIER;
    }

    public final LightTreePositioningStrategy getCONTEXT_KEYWORD() {
        return CONTEXT_KEYWORD;
    }

    public final LightTreePositioningStrategy getDATA_MODIFIER() {
        return DATA_MODIFIER;
    }

    public final LightTreePositioningStrategy getDECLARATION_NAME() {
        return DECLARATION_NAME;
    }

    public final LightTreePositioningStrategy getDECLARATION_NAME_ONLY() {
        return DECLARATION_NAME_ONLY;
    }

    public final LightTreePositioningStrategy getDECLARATION_RETURN_TYPE() {
        return DECLARATION_RETURN_TYPE;
    }

    public final LightTreePositioningStrategy getDECLARATION_SIGNATURE() {
        return DECLARATION_SIGNATURE;
    }

    public final LightTreePositioningStrategy getDECLARATION_SIGNATURE_OR_DEFAULT() {
        return DECLARATION_SIGNATURE_OR_DEFAULT;
    }

    public final LightTreePositioningStrategy getDECLARATION_START_TO_NAME() {
        return DECLARATION_START_TO_NAME;
    }

    public final LightTreePositioningStrategy getDECLARATION_WITH_BODY() {
        return DECLARATION_WITH_BODY;
    }

    public final LightTreePositioningStrategy getDEFAULT() {
        return DEFAULT;
    }

    public final LightTreePositioningStrategy getDELEGATED_SUPERTYPE_BY_KEYWORD() {
        return DELEGATED_SUPERTYPE_BY_KEYWORD;
    }

    public final LightTreePositioningStrategy getDEPRECATION() {
        return DEPRECATION;
    }

    public final LightTreePositioningStrategy getDOT_BY_QUALIFIED() {
        return DOT_BY_QUALIFIED;
    }

    public final LightTreePositioningStrategy getELSE_ENTRY() {
        return ELSE_ENTRY;
    }

    public final LightTreePositioningStrategy getENUM_MODIFIER() {
        return ENUM_MODIFIER;
    }

    public final LightTreePositioningStrategy getEXPECT_ACTUAL_MODIFIER() {
        return EXPECT_ACTUAL_MODIFIER;
    }

    public final LightTreePositioningStrategy getEXTERNAL_MODIFIER() {
        return EXTERNAL_MODIFIER;
    }

    public final LightTreePositioningStrategy getFIELD_KEYWORD() {
        return FIELD_KEYWORD;
    }

    public final LightTreePositioningStrategy getFUNCTION_TYPE_RECEIVER() {
        return FUNCTION_TYPE_RECEIVER;
    }

    public final LightTreePositioningStrategy getFUN_INTERFACE() {
        return FUN_INTERFACE;
    }

    public final LightTreePositioningStrategy getFUN_MODIFIER() {
        return FUN_MODIFIER;
    }

    public final LightTreePositioningStrategy getIF_EXPRESSION() {
        return IF_EXPRESSION;
    }

    public final LightTreePositioningStrategy getIMPORT_ALIAS() {
        return IMPORT_ALIAS;
    }

    public final LightTreePositioningStrategy getIMPORT_LAST_BUT_ONE_NAME() {
        return IMPORT_LAST_BUT_ONE_NAME;
    }

    public final LightTreePositioningStrategy getIMPORT_LAST_NAME() {
        return IMPORT_LAST_NAME;
    }

    public final LightTreePositioningStrategy getINFIX_MODIFIER() {
        return INFIX_MODIFIER;
    }

    public final LightTreePositioningStrategy getINLINE_FUN_MODIFIER() {
        return INLINE_FUN_MODIFIER;
    }

    public final LightTreePositioningStrategy getINLINE_OR_VALUE_MODIFIER() {
        return INLINE_OR_VALUE_MODIFIER;
    }

    public final LightTreePositioningStrategy getINLINE_PARAMETER_MODIFIER() {
        return INLINE_PARAMETER_MODIFIER;
    }

    public final LightTreePositioningStrategy getINNER_MODIFIER() {
        return INNER_MODIFIER;
    }

    public final LightTreePositioningStrategy getLABEL() {
        return LABEL;
    }

    public final LightTreePositioningStrategy getLAST_CHILD() {
        return LAST_CHILD;
    }

    public final LightTreePositioningStrategy getLATEINIT_MODIFIER() {
        return LATEINIT_MODIFIER;
    }

    public final LightTreePositioningStrategy getLONG_LITERAL_SUFFIX() {
        return LONG_LITERAL_SUFFIX;
    }

    public final LightTreePositioningStrategy getMODALITY_MODIFIER() {
        return MODALITY_MODIFIER;
    }

    public final LightTreePositioningStrategy getNAME_IDENTIFIER() {
        return NAME_IDENTIFIER;
    }

    public final LightTreePositioningStrategy getNAME_OF_NAMED_ARGUMENT() {
        return NAME_OF_NAMED_ARGUMENT;
    }

    public final LightTreePositioningStrategy getNON_FINAL_MODIFIER_OR_NAME() {
        return NON_FINAL_MODIFIER_OR_NAME;
    }

    public final LightTreePositioningStrategy getNOT_SUPPORTED_IN_INLINE_MOST_RELEVANT() {
        return NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT;
    }

    public final LightTreePositioningStrategy getOBJECT_KEYWORD() {
        return OBJECT_KEYWORD;
    }

    public final LightTreePositioningStrategy getOPEN_MODIFIER() {
        return OPEN_MODIFIER;
    }

    public final LightTreePositioningStrategy getOPERATOR() {
        return OPERATOR;
    }

    public final LightTreePositioningStrategy getOPERATOR_MODIFIER() {
        return OPERATOR_MODIFIER;
    }

    public final LightTreePositioningStrategy getOUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS() {
        return OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS;
    }

    public final LightTreePositioningStrategy getOVERRIDE_MODIFIER() {
        return OVERRIDE_MODIFIER;
    }

    public final LightTreePositioningStrategy getPACKAGE_DIRECTIVE_NAME_EXPRESSION() {
        return PACKAGE_DIRECTIVE_NAME_EXPRESSION;
    }

    public final LightTreePositioningStrategy getPARAMETERS_WITH_DEFAULT_VALUE() {
        return PARAMETERS_WITH_DEFAULT_VALUE;
    }

    public final LightTreePositioningStrategy getPARAMETER_DEFAULT_VALUE() {
        return PARAMETER_DEFAULT_VALUE;
    }

    public final LightTreePositioningStrategy getPARAMETER_VARARG_MODIFIER() {
        return PARAMETER_VARARG_MODIFIER;
    }

    public final LightTreePositioningStrategy getPRIVATE_MODIFIER() {
        return PRIVATE_MODIFIER;
    }

    public final LightTreePositioningStrategy getPROPERTY_DELEGATE() {
        return PROPERTY_DELEGATE;
    }

    public final LightTreePositioningStrategy getPROPERTY_DELEGATE_BY_KEYWORD() {
        return PROPERTY_DELEGATE_BY_KEYWORD;
    }

    public final LightTreePositioningStrategy getQUESTION_MARK_BY_TYPE() {
        return QUESTION_MARK_BY_TYPE;
    }

    public final LightTreePositioningStrategy getREDUNDANT_NULLABLE() {
        return REDUNDANT_NULLABLE;
    }

    public final LightTreePositioningStrategy getREFERENCED_NAME_BY_QUALIFIED() {
        return REFERENCED_NAME_BY_QUALIFIED;
    }

    public final LightTreePositioningStrategy getREFERENCE_BY_QUALIFIED() {
        return REFERENCE_BY_QUALIFIED;
    }

    public final LightTreePositioningStrategy getREIFIED_MODIFIER() {
        return REIFIED_MODIFIER;
    }

    public final LightTreePositioningStrategy getRETURN_WITH_LABEL() {
        return RETURN_WITH_LABEL;
    }

    public final LightTreePositioningStrategy getSAFE_ACCESS() {
        return SAFE_ACCESS;
    }

    public final LightTreePositioningStrategy getSECONDARY_CONSTRUCTOR_DELEGATION_CALL() {
        return SECONDARY_CONSTRUCTOR_DELEGATION_CALL;
    }

    public final LightTreePositioningStrategy getSELECTOR_BY_QUALIFIED() {
        return SELECTOR_BY_QUALIFIED;
    }

    public final LightTreePositioningStrategy getSPREAD_OPERATOR() {
        return SPREAD_OPERATOR;
    }

    public final LightTreePositioningStrategy getSUPERTYPES_LIST() {
        return SUPERTYPES_LIST;
    }

    public final LightTreePositioningStrategy getSUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC() {
        return SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC;
    }

    public final LightTreePositioningStrategy getSUSPEND_MODIFIER() {
        return SUSPEND_MODIFIER;
    }

    public final LightTreePositioningStrategy getTAILREC_MODIFIER() {
        return TAILREC_MODIFIER;
    }

    public final LightTreePositioningStrategy getTYPEALIAS_TYPE_REFERENCE() {
        return TYPEALIAS_TYPE_REFERENCE;
    }

    public final LightTreePositioningStrategy getTYPE_ARGUMENT_LIST_OR_SELF() {
        return TYPE_ARGUMENT_LIST_OR_SELF;
    }

    public final LightTreePositioningStrategy getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER() {
        return TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER;
    }

    public final LightTreePositioningStrategy getTYPE_PARAMETERS_LIST() {
        return TYPE_PARAMETERS_LIST;
    }

    public final LightTreePositioningStrategy getUNREACHABLE_CODE() {
        return UNREACHABLE_CODE;
    }

    public final LightTreePositioningStrategy getUSELESS_ELVIS() {
        return USELESS_ELVIS;
    }

    public final LightTreePositioningStrategy getUSELESS_ELVIS_LEFT() {
        return USELESS_ELVIS_LEFT;
    }

    public final LightTreePositioningStrategy getVALUE_ARGUMENTS() {
        return VALUE_ARGUMENTS;
    }

    public final LightTreePositioningStrategy getVALUE_ARGUMENTS_LIST() {
        return VALUE_ARGUMENTS_LIST;
    }

    public final LightTreePositioningStrategy getVAL_OR_VAR_NODE() {
        return VAL_OR_VAR_NODE;
    }

    public final LightTreePositioningStrategy getVARIABLE_INITIALIZER() {
        return VARIABLE_INITIALIZER;
    }

    public final LightTreePositioningStrategy getVARIANCE_MODIFIER() {
        return VARIANCE_MODIFIER;
    }

    public final LightTreePositioningStrategy getVISIBILITY_MODIFIER() {
        return VISIBILITY_MODIFIER;
    }

    public final LightTreePositioningStrategy getWHEN_EXPRESSION() {
        return WHEN_EXPRESSION;
    }

    public final LightTreePositioningStrategy getWHEN_GUARD() {
        return WHEN_GUARD;
    }

    public final LightTreePositioningStrategy getWHOLE_ELEMENT() {
        return WHOLE_ELEMENT;
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0012\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001d\b\u0016\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0004\b\u0004\u0010\tJ@\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0004J4\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategies$ModifierSetBasedLightTreePositioningStrategy;", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;", "modifierSet", "Lcom/intellij/psi/tree/TokenSet;", "<init>", "(Lcom/intellij/psi/tree/TokenSet;)V", "tokens", Argument.Delimiters.none, "Lcom/intellij/psi/tree/IElementType;", "([Lcom/intellij/psi/tree/IElementType;)V", "markModifier", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "node", "Lcom/intellij/lang/LighterASTNode;", "startOffset", Argument.Delimiters.none, "endOffset", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "baseNode", "mark", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static class ModifierSetBasedLightTreePositioningStrategy extends LightTreePositioningStrategy {
        private final TokenSet modifierSet;

        /* JADX WARN: Illegal instructions before constructor call */
        public ModifierSetBasedLightTreePositioningStrategy(IElementType... iElementTypeArr) {
            iElementTypeArr.getClass();
            TokenSet tokenSetCreate = TokenSet.create((IElementType[]) Arrays.copyOf(iElementTypeArr, iElementTypeArr.length));
            tokenSetCreate.getClass();
            this(tokenSetCreate);
        }

        @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
        public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
            node.getClass();
            tree.getClass();
            List<TextRange> listMarkModifier = markModifier(node, startOffset, endOffset, tree, node);
            if (listMarkModifier != null) {
                return listMarkModifier;
            }
            LighterASTNode lighterASTNodeNameIdentifier = LightTreePositioningStrategiesKt.nameIdentifier(tree, node);
            if (lighterASTNodeNameIdentifier != null) {
                return LightTreePositioningStrategyKt.markElement(lighterASTNodeNameIdentifier, startOffset, endOffset, tree, node);
            }
            IElementType tokenType = node.getTokenType();
            if (!Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_DECLARATION)) {
                return Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY_ACCESSOR) ? LightTreePositioningStrategyKt.markElement(LightTreePositioningStrategiesKt.accessorNamePlaceholder(tree, node), startOffset, endOffset, tree, node) : LightTreePositioningStrategyKt.markElement$default(node, startOffset, endOffset, tree, null, 16, null);
            }
            LighterASTNode lighterASTNodeObjectKeyword = LightTreePositioningStrategiesKt.objectKeyword(tree, node);
            lighterASTNodeObjectKeyword.getClass();
            return LightTreePositioningStrategyKt.markElement(lighterASTNodeObjectKeyword, startOffset, endOffset, tree, node);
        }

        public final List<TextRange> markModifier(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree, LighterASTNode baseNode) {
            LighterASTNode lighterASTNodeModifierList;
            LighterASTNode lighterASTNodeFindChildByType;
            tree.getClass();
            baseNode.getClass();
            if (node == null || (lighterASTNodeModifierList = LightTreePositioningStrategiesKt.modifierList(tree, node)) == null || (lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, lighterASTNodeModifierList, this.modifierSet)) == null) {
                return null;
            }
            return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, baseNode);
        }

        public ModifierSetBasedLightTreePositioningStrategy(TokenSet tokenSet) {
            tokenSet.getClass();
            this.modifierSet = tokenSet;
        }
    }
}
