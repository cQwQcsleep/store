package org.jetbrains.kotlin.ir.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.BuiltInOperatorNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u0000 \u00062\u00020\u0001:\u0002\u0006\u0007R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "", "debugName", "", "getDebugName", "()Ljava/lang/String;", "Companion", "COMPONENT_N", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrStatementOrigin {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0003\bÚ\u0001\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000e\u0010\u0007R\u001b\u0010\u0010\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0011\u0010\u0007R\u001b\u0010\u0013\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0014\u0010\u0007R\u001b\u0010\u0016\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0017\u0010\u0007R\u001b\u0010\u0019\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\t\u001a\u0004\b\u001a\u0010\u0007R\u001b\u0010\u001c\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\t\u001a\u0004\b\u001d\u0010\u0007R\u001b\u0010\u001f\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\t\u001a\u0004\b \u0010\u0007R\u001b\u0010\"\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\t\u001a\u0004\b#\u0010\u0007R\u001b\u0010%\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\t\u001a\u0004\b&\u0010\u0007R\u001b\u0010(\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\t\u001a\u0004\b)\u0010\u0007R\u001b\u0010+\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\t\u001a\u0004\b,\u0010\u0007R\u001b\u0010.\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\t\u001a\u0004\b/\u0010\u0007R\u001b\u00101\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\t\u001a\u0004\b2\u0010\u0007R\u001b\u00104\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\t\u001a\u0004\b5\u0010\u0007R\u001b\u00107\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\t\u001a\u0004\b8\u0010\u0007R\u001b\u0010:\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\t\u001a\u0004\b;\u0010\u0007R\u001b\u0010=\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b?\u0010\t\u001a\u0004\b>\u0010\u0007R\u001b\u0010@\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bB\u0010\t\u001a\u0004\bA\u0010\u0007R\u001b\u0010C\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\t\u001a\u0004\bD\u0010\u0007R\u001b\u0010F\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bH\u0010\t\u001a\u0004\bG\u0010\u0007R\u001b\u0010I\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bK\u0010\t\u001a\u0004\bJ\u0010\u0007R\u001b\u0010L\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bN\u0010\t\u001a\u0004\bM\u0010\u0007R\u001b\u0010O\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010\t\u001a\u0004\bP\u0010\u0007R\u001b\u0010R\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bT\u0010\t\u001a\u0004\bS\u0010\u0007R\u001b\u0010U\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bW\u0010\t\u001a\u0004\bV\u0010\u0007R\u001b\u0010X\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bZ\u0010\t\u001a\u0004\bY\u0010\u0007R\u001b\u0010[\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b]\u0010\t\u001a\u0004\b\\\u0010\u0007R\u001b\u0010^\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b`\u0010\t\u001a\u0004\b_\u0010\u0007R\u001b\u0010a\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bc\u0010\t\u001a\u0004\bb\u0010\u0007R\u001b\u0010d\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bf\u0010\t\u001a\u0004\be\u0010\u0007R\u001b\u0010g\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bi\u0010\t\u001a\u0004\bh\u0010\u0007R\u001b\u0010j\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bl\u0010\t\u001a\u0004\bk\u0010\u0007R\u001b\u0010m\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bo\u0010\t\u001a\u0004\bn\u0010\u0007R\u001b\u0010p\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\br\u0010\t\u001a\u0004\bq\u0010\u0007R\u001b\u0010s\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bu\u0010\t\u001a\u0004\bt\u0010\u0007R\u001b\u0010v\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bx\u0010\t\u001a\u0004\bw\u0010\u0007R\u001b\u0010y\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b{\u0010\t\u001a\u0004\bz\u0010\u0007R\u001b\u0010|\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b~\u0010\t\u001a\u0004\b}\u0010\u0007R\u001d\u0010\u007f\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0081\u0001\u0010\t\u001a\u0005\b\u0080\u0001\u0010\u0007R\u001e\u0010\u0082\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0084\u0001\u0010\t\u001a\u0005\b\u0083\u0001\u0010\u0007R\u001e\u0010\u0085\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0087\u0001\u0010\t\u001a\u0005\b\u0086\u0001\u0010\u0007R\u001e\u0010\u0088\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u008a\u0001\u0010\t\u001a\u0005\b\u0089\u0001\u0010\u0007R\u001e\u0010\u008b\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u008d\u0001\u0010\t\u001a\u0005\b\u008c\u0001\u0010\u0007R\u001e\u0010\u008e\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0090\u0001\u0010\t\u001a\u0005\b\u008f\u0001\u0010\u0007R\u001e\u0010\u0091\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0093\u0001\u0010\t\u001a\u0005\b\u0092\u0001\u0010\u0007R\u001e\u0010\u0094\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0096\u0001\u0010\t\u001a\u0005\b\u0095\u0001\u0010\u0007R\u001e\u0010\u0097\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0099\u0001\u0010\t\u001a\u0005\b\u0098\u0001\u0010\u0007R\u001e\u0010\u009a\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u009c\u0001\u0010\t\u001a\u0005\b\u009b\u0001\u0010\u0007R\u001e\u0010\u009d\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u009f\u0001\u0010\t\u001a\u0005\b\u009e\u0001\u0010\u0007R\u001e\u0010 \u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¢\u0001\u0010\t\u001a\u0005\b¡\u0001\u0010\u0007R\u001e\u0010£\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¥\u0001\u0010\t\u001a\u0005\b¤\u0001\u0010\u0007R\u001e\u0010¦\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¨\u0001\u0010\t\u001a\u0005\b§\u0001\u0010\u0007R\u001e\u0010©\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b«\u0001\u0010\t\u001a\u0005\bª\u0001\u0010\u0007R\u001e\u0010¬\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b®\u0001\u0010\t\u001a\u0005\b\u00ad\u0001\u0010\u0007R\u001e\u0010¯\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b±\u0001\u0010\t\u001a\u0005\b°\u0001\u0010\u0007R\u001e\u0010²\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b´\u0001\u0010\t\u001a\u0005\b³\u0001\u0010\u0007R\u001e\u0010µ\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b·\u0001\u0010\t\u001a\u0005\b¶\u0001\u0010\u0007R\u001e\u0010¸\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bº\u0001\u0010\t\u001a\u0005\b¹\u0001\u0010\u0007R\u001e\u0010»\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b½\u0001\u0010\t\u001a\u0005\b¼\u0001\u0010\u0007R\u001e\u0010¾\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÀ\u0001\u0010\t\u001a\u0005\b¿\u0001\u0010\u0007R\u001e\u0010Á\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÃ\u0001\u0010\t\u001a\u0005\bÂ\u0001\u0010\u0007R\u001e\u0010Ä\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÆ\u0001\u0010\t\u001a\u0005\bÅ\u0001\u0010\u0007R\u001e\u0010Ç\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÉ\u0001\u0010\t\u001a\u0005\bÈ\u0001\u0010\u0007R\u001e\u0010Ê\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÌ\u0001\u0010\t\u001a\u0005\bË\u0001\u0010\u0007R\u001e\u0010Í\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÏ\u0001\u0010\t\u001a\u0005\bÎ\u0001\u0010\u0007R\u001e\u0010Ð\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÒ\u0001\u0010\t\u001a\u0005\bÑ\u0001\u0010\u0007R\u001c\u0010Ó\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0003\bÕ\u0001\u001a\u0005\bÔ\u0001\u0010\u0007R\u001e\u0010Ö\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bØ\u0001\u0010\t\u001a\u0005\b×\u0001\u0010\u0007R\u001e\u0010Ù\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÛ\u0001\u0010\t\u001a\u0005\bÚ\u0001\u0010\u0007R\u001e\u0010Ü\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÞ\u0001\u0010\t\u001a\u0005\bÝ\u0001\u0010\u0007¨\u0006ß\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin$Companion;", "", "<init>", "()V", "SAFE_CALL", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "getSAFE_CALL", "()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "SAFE_CALL$delegate", "Lkotlin/properties/ReadOnlyProperty;", "UMINUS", "getUMINUS", "UMINUS$delegate", "UPLUS", "getUPLUS", "UPLUS$delegate", "EXCL", "getEXCL", "EXCL$delegate", "EXCLEXCL", "getEXCLEXCL", "EXCLEXCL$delegate", "ELVIS", "getELVIS", "ELVIS$delegate", "LT", "getLT", "LT$delegate", "GT", "getGT", "GT$delegate", "LTEQ", "getLTEQ", "LTEQ$delegate", "GTEQ", "getGTEQ", "GTEQ$delegate", BuiltInOperatorNames.EQEQ, "getEQEQ", "EQEQ$delegate", BuiltInOperatorNames.EQEQEQ, "getEQEQEQ", "EQEQEQ$delegate", "EXCLEQ", "getEXCLEQ", "EXCLEQ$delegate", "EXCLEQEQ", "getEXCLEQEQ", "EXCLEQEQ$delegate", "IN", "getIN", "IN$delegate", "NOT_IN", "getNOT_IN", "NOT_IN$delegate", BuiltInOperatorNames.ANDAND, "getANDAND", "ANDAND$delegate", BuiltInOperatorNames.OROR, "getOROR", "OROR$delegate", "PLUS", "getPLUS", "PLUS$delegate", "MINUS", "getMINUS", "MINUS$delegate", "MUL", "getMUL", "MUL$delegate", "DIV", "getDIV", "DIV$delegate", "PERC", "getPERC", "PERC$delegate", "RANGE", "getRANGE", "RANGE$delegate", "RANGE_UNTIL", "getRANGE_UNTIL", "RANGE_UNTIL$delegate", "INVOKE", "getINVOKE", "INVOKE$delegate", "VARIABLE_AS_FUNCTION", "getVARIABLE_AS_FUNCTION", "VARIABLE_AS_FUNCTION$delegate", "GET_ARRAY_ELEMENT", "getGET_ARRAY_ELEMENT", "GET_ARRAY_ELEMENT$delegate", "PREFIX_INCR", "getPREFIX_INCR", "PREFIX_INCR$delegate", "PREFIX_DECR", "getPREFIX_DECR", "PREFIX_DECR$delegate", "POSTFIX_INCR", "getPOSTFIX_INCR", "POSTFIX_INCR$delegate", "POSTFIX_DECR", "getPOSTFIX_DECR", "POSTFIX_DECR$delegate", "EQ", "getEQ", "EQ$delegate", "PLUSEQ", "getPLUSEQ", "PLUSEQ$delegate", "MINUSEQ", "getMINUSEQ", "MINUSEQ$delegate", "MULTEQ", "getMULTEQ", "MULTEQ$delegate", "DIVEQ", "getDIVEQ", "DIVEQ$delegate", "PERCEQ", "getPERCEQ", "PERCEQ$delegate", "ARGUMENTS_REORDERING_FOR_CALL", "getARGUMENTS_REORDERING_FOR_CALL", "ARGUMENTS_REORDERING_FOR_CALL$delegate", "IMPLICIT_ARGUMENT", "getIMPLICIT_ARGUMENT", "IMPLICIT_ARGUMENT$delegate", "DESTRUCTURING_DECLARATION", "getDESTRUCTURING_DECLARATION", "DESTRUCTURING_DECLARATION$delegate", "GET_PROPERTY", "getGET_PROPERTY", "GET_PROPERTY$delegate", "GET_LOCAL_PROPERTY", "getGET_LOCAL_PROPERTY", "GET_LOCAL_PROPERTY$delegate", "IF", "getIF", "IF$delegate", "WHEN", "getWHEN", "WHEN$delegate", "WHEN_COMMA", "getWHEN_COMMA", "WHEN_COMMA$delegate", "WHILE_LOOP", "getWHILE_LOOP", "WHILE_LOOP$delegate", "DO_WHILE_LOOP", "getDO_WHILE_LOOP", "DO_WHILE_LOOP$delegate", "FOR_LOOP", "getFOR_LOOP", "FOR_LOOP$delegate", "FOR_LOOP_ITERATOR", "getFOR_LOOP_ITERATOR", "FOR_LOOP_ITERATOR$delegate", "FOR_LOOP_INNER_WHILE", "getFOR_LOOP_INNER_WHILE", "FOR_LOOP_INNER_WHILE$delegate", "FOR_LOOP_HAS_NEXT", "getFOR_LOOP_HAS_NEXT", "FOR_LOOP_HAS_NEXT$delegate", "FOR_LOOP_NEXT", "getFOR_LOOP_NEXT", "FOR_LOOP_NEXT$delegate", "LAMBDA", "getLAMBDA", "LAMBDA$delegate", "DEFAULT_VALUE", "getDEFAULT_VALUE", "DEFAULT_VALUE$delegate", "ANONYMOUS_FUNCTION", "getANONYMOUS_FUNCTION", "ANONYMOUS_FUNCTION$delegate", "OBJECT_LITERAL", "getOBJECT_LITERAL", "OBJECT_LITERAL$delegate", "ADAPTED_FUNCTION_REFERENCE", "getADAPTED_FUNCTION_REFERENCE", "ADAPTED_FUNCTION_REFERENCE$delegate", "SUSPEND_CONVERSION", "getSUSPEND_CONVERSION", "SUSPEND_CONVERSION$delegate", "FUN_INTERFACE_CONSTRUCTOR_REFERENCE", "getFUN_INTERFACE_CONSTRUCTOR_REFERENCE", "FUN_INTERFACE_CONSTRUCTOR_REFERENCE$delegate", "INITIALIZE_PROPERTY_FROM_PARAMETER", "getINITIALIZE_PROPERTY_FROM_PARAMETER", "INITIALIZE_PROPERTY_FROM_PARAMETER$delegate", "INITIALIZE_FIELD", "getINITIALIZE_FIELD", "INITIALIZE_FIELD$delegate", "PROPERTY_REFERENCE_FOR_DELEGATE", "getPROPERTY_REFERENCE_FOR_DELEGATE", "PROPERTY_REFERENCE_FOR_DELEGATE$delegate", "BRIDGE_DELEGATION", "getBRIDGE_DELEGATION", "BRIDGE_DELEGATION$delegate", "SYNTHETIC_NOT_AUTOBOXED_CHECK", "getSYNTHETIC_NOT_AUTOBOXED_CHECK", "SYNTHETIC_NOT_AUTOBOXED_CHECK$delegate", "PARTIAL_LINKAGE_RUNTIME_ERROR", "getPARTIAL_LINKAGE_RUNTIME_ERROR", "PARTIAL_LINKAGE_RUNTIME_ERROR$delegate", "SYNTHESIZED_INIT_BLOCK", "getSYNTHESIZED_INIT_BLOCK", "SYNTHESIZED_INIT_BLOCK$delegate", "DEFAULT_DISPATCH_CALL", "getDEFAULT_DISPATCH_CALL", "DEFAULT_DISPATCH_CALL$delegate", "STATEMENT_ORIGIN_INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE", "getSTATEMENT_ORIGIN_INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE", "STATEMENT_ORIGIN_INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE$delegate", "INLINED_FUNCTION_REFERENCE", "getINLINED_FUNCTION_REFERENCE", "INLINED_FUNCTION_REFERENCE$delegate", "INLINE_LAMBDA", "getINLINE_LAMBDA", "INLINE_LAMBDA$delegate", "INLINE_ARGS_CONTAINER", "getINLINE_ARGS_CONTAINER", "INLINE_ARGS_CONTAINER$delegate", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE;
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;

        /* JADX INFO: renamed from: ADAPTED_FUNCTION_REFERENCE$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> ADAPTED_FUNCTION_REFERENCE;

        /* JADX INFO: renamed from: ANDAND$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> ANDAND;

        /* JADX INFO: renamed from: ANONYMOUS_FUNCTION$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> ANONYMOUS_FUNCTION;

        /* JADX INFO: renamed from: ARGUMENTS_REORDERING_FOR_CALL$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> ARGUMENTS_REORDERING_FOR_CALL;

        /* JADX INFO: renamed from: BRIDGE_DELEGATION$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> BRIDGE_DELEGATION;

        /* JADX INFO: renamed from: DEFAULT_DISPATCH_CALL$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> DEFAULT_DISPATCH_CALL;

        /* JADX INFO: renamed from: DEFAULT_VALUE$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> DEFAULT_VALUE;

        /* JADX INFO: renamed from: DESTRUCTURING_DECLARATION$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> DESTRUCTURING_DECLARATION;

        /* JADX INFO: renamed from: DIV$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> DIV;

        /* JADX INFO: renamed from: DIVEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> DIVEQ;

        /* JADX INFO: renamed from: DO_WHILE_LOOP$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> DO_WHILE_LOOP;

        /* JADX INFO: renamed from: ELVIS$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> ELVIS;

        /* JADX INFO: renamed from: EQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> EQ;

        /* JADX INFO: renamed from: EQEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> EQEQ;

        /* JADX INFO: renamed from: EQEQEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> EQEQEQ;

        /* JADX INFO: renamed from: EXCL$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> EXCL;

        /* JADX INFO: renamed from: EXCLEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> EXCLEQ;

        /* JADX INFO: renamed from: EXCLEQEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> EXCLEQEQ;

        /* JADX INFO: renamed from: EXCLEXCL$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> EXCLEXCL;

        /* JADX INFO: renamed from: FOR_LOOP$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> FOR_LOOP;

        /* JADX INFO: renamed from: FOR_LOOP_HAS_NEXT$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> FOR_LOOP_HAS_NEXT;

        /* JADX INFO: renamed from: FOR_LOOP_INNER_WHILE$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> FOR_LOOP_INNER_WHILE;

        /* JADX INFO: renamed from: FOR_LOOP_ITERATOR$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> FOR_LOOP_ITERATOR;

        /* JADX INFO: renamed from: FOR_LOOP_NEXT$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> FOR_LOOP_NEXT;

        /* JADX INFO: renamed from: FUN_INTERFACE_CONSTRUCTOR_REFERENCE$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> FUN_INTERFACE_CONSTRUCTOR_REFERENCE;

        /* JADX INFO: renamed from: GET_ARRAY_ELEMENT$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> GET_ARRAY_ELEMENT;

        /* JADX INFO: renamed from: GET_LOCAL_PROPERTY$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> GET_LOCAL_PROPERTY;

        /* JADX INFO: renamed from: GET_PROPERTY$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> GET_PROPERTY;

        /* JADX INFO: renamed from: GT$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> GT;

        /* JADX INFO: renamed from: GTEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> GTEQ;

        /* JADX INFO: renamed from: IF$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> IF;

        /* JADX INFO: renamed from: IMPLICIT_ARGUMENT$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> IMPLICIT_ARGUMENT;

        /* JADX INFO: renamed from: IN$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> IN;

        /* JADX INFO: renamed from: INITIALIZE_FIELD$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> INITIALIZE_FIELD;

        /* JADX INFO: renamed from: INITIALIZE_PROPERTY_FROM_PARAMETER$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> INITIALIZE_PROPERTY_FROM_PARAMETER;

        /* JADX INFO: renamed from: INLINED_FUNCTION_REFERENCE$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> INLINED_FUNCTION_REFERENCE;

        /* JADX INFO: renamed from: INLINE_ARGS_CONTAINER$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> INLINE_ARGS_CONTAINER;

        /* JADX INFO: renamed from: INLINE_LAMBDA$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> INLINE_LAMBDA;

        /* JADX INFO: renamed from: INVOKE$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> INVOKE;

        /* JADX INFO: renamed from: LAMBDA$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> LAMBDA;

        /* JADX INFO: renamed from: LT$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> LT;

        /* JADX INFO: renamed from: LTEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> LTEQ;

        /* JADX INFO: renamed from: MINUS$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> MINUS;

        /* JADX INFO: renamed from: MINUSEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> MINUSEQ;

        /* JADX INFO: renamed from: MUL$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> MUL;

        /* JADX INFO: renamed from: MULTEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> MULTEQ;

        /* JADX INFO: renamed from: NOT_IN$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> NOT_IN;

        /* JADX INFO: renamed from: OBJECT_LITERAL$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> OBJECT_LITERAL;

        /* JADX INFO: renamed from: OROR$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> OROR;

        /* JADX INFO: renamed from: PARTIAL_LINKAGE_RUNTIME_ERROR$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> PARTIAL_LINKAGE_RUNTIME_ERROR;

        /* JADX INFO: renamed from: PERC$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> PERC;

        /* JADX INFO: renamed from: PERCEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> PERCEQ;

        /* JADX INFO: renamed from: PLUS$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> PLUS;

        /* JADX INFO: renamed from: PLUSEQ$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> PLUSEQ;

        /* JADX INFO: renamed from: POSTFIX_DECR$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> POSTFIX_DECR;

        /* JADX INFO: renamed from: POSTFIX_INCR$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> POSTFIX_INCR;

        /* JADX INFO: renamed from: PREFIX_DECR$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> PREFIX_DECR;

        /* JADX INFO: renamed from: PREFIX_INCR$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> PREFIX_INCR;

        /* JADX INFO: renamed from: PROPERTY_REFERENCE_FOR_DELEGATE$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> PROPERTY_REFERENCE_FOR_DELEGATE;

        /* JADX INFO: renamed from: RANGE$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> RANGE;

        /* JADX INFO: renamed from: RANGE_UNTIL$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> RANGE_UNTIL;

        /* JADX INFO: renamed from: SAFE_CALL$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> SAFE_CALL;

        /* JADX INFO: renamed from: STATEMENT_ORIGIN_INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE$delegate, reason: from kotlin metadata */
        private static final IrStatementOriginImpl STATEMENT_ORIGIN_INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE;

        /* JADX INFO: renamed from: SUSPEND_CONVERSION$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> SUSPEND_CONVERSION;

        /* JADX INFO: renamed from: SYNTHESIZED_INIT_BLOCK$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> SYNTHESIZED_INIT_BLOCK;

        /* JADX INFO: renamed from: SYNTHETIC_NOT_AUTOBOXED_CHECK$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> SYNTHETIC_NOT_AUTOBOXED_CHECK;

        /* JADX INFO: renamed from: UMINUS$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> UMINUS;

        /* JADX INFO: renamed from: UPLUS$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> UPLUS;

        /* JADX INFO: renamed from: VARIABLE_AS_FUNCTION$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> VARIABLE_AS_FUNCTION;

        /* JADX INFO: renamed from: WHEN$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> WHEN;

        /* JADX INFO: renamed from: WHEN_COMMA$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> WHEN_COMMA;

        /* JADX INFO: renamed from: WHILE_LOOP$delegate, reason: from kotlin metadata */
        private static final ReadOnlyProperty<Object, IrStatementOriginImpl> WHILE_LOOP;

        static {
            KProperty<?>[] kPropertyArr = {new PropertyReference1Impl<>(Companion.class, "SAFE_CALL", "getSAFE_CALL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "UMINUS", "getUMINUS()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "UPLUS", "getUPLUS()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "EXCL", "getEXCL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "EXCLEXCL", "getEXCLEXCL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "ELVIS", "getELVIS()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "LT", "getLT()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "GT", "getGT()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "LTEQ", "getLTEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "GTEQ", "getGTEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, BuiltInOperatorNames.EQEQ, "getEQEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, BuiltInOperatorNames.EQEQEQ, "getEQEQEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "EXCLEQ", "getEXCLEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "EXCLEQEQ", "getEXCLEQEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "IN", "getIN()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "NOT_IN", "getNOT_IN()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, BuiltInOperatorNames.ANDAND, "getANDAND()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, BuiltInOperatorNames.OROR, "getOROR()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "PLUS", "getPLUS()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "MINUS", "getMINUS()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "MUL", "getMUL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "DIV", "getDIV()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "PERC", "getPERC()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "RANGE", "getRANGE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "RANGE_UNTIL", "getRANGE_UNTIL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "INVOKE", "getINVOKE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "VARIABLE_AS_FUNCTION", "getVARIABLE_AS_FUNCTION()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "GET_ARRAY_ELEMENT", "getGET_ARRAY_ELEMENT()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "PREFIX_INCR", "getPREFIX_INCR()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "PREFIX_DECR", "getPREFIX_DECR()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "POSTFIX_INCR", "getPOSTFIX_INCR()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "POSTFIX_DECR", "getPOSTFIX_DECR()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "EQ", "getEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "PLUSEQ", "getPLUSEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "MINUSEQ", "getMINUSEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "MULTEQ", "getMULTEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "DIVEQ", "getDIVEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "PERCEQ", "getPERCEQ()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "ARGUMENTS_REORDERING_FOR_CALL", "getARGUMENTS_REORDERING_FOR_CALL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "IMPLICIT_ARGUMENT", "getIMPLICIT_ARGUMENT()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "DESTRUCTURING_DECLARATION", "getDESTRUCTURING_DECLARATION()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "GET_PROPERTY", "getGET_PROPERTY()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "GET_LOCAL_PROPERTY", "getGET_LOCAL_PROPERTY()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "IF", "getIF()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "WHEN", "getWHEN()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "WHEN_COMMA", "getWHEN_COMMA()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "WHILE_LOOP", "getWHILE_LOOP()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "DO_WHILE_LOOP", "getDO_WHILE_LOOP()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "FOR_LOOP", "getFOR_LOOP()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "FOR_LOOP_ITERATOR", "getFOR_LOOP_ITERATOR()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "FOR_LOOP_INNER_WHILE", "getFOR_LOOP_INNER_WHILE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "FOR_LOOP_HAS_NEXT", "getFOR_LOOP_HAS_NEXT()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "FOR_LOOP_NEXT", "getFOR_LOOP_NEXT()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "LAMBDA", "getLAMBDA()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "DEFAULT_VALUE", "getDEFAULT_VALUE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "ANONYMOUS_FUNCTION", "getANONYMOUS_FUNCTION()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "OBJECT_LITERAL", "getOBJECT_LITERAL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "ADAPTED_FUNCTION_REFERENCE", "getADAPTED_FUNCTION_REFERENCE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "SUSPEND_CONVERSION", "getSUSPEND_CONVERSION()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "FUN_INTERFACE_CONSTRUCTOR_REFERENCE", "getFUN_INTERFACE_CONSTRUCTOR_REFERENCE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "INITIALIZE_PROPERTY_FROM_PARAMETER", "getINITIALIZE_PROPERTY_FROM_PARAMETER()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "INITIALIZE_FIELD", "getINITIALIZE_FIELD()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "PROPERTY_REFERENCE_FOR_DELEGATE", "getPROPERTY_REFERENCE_FOR_DELEGATE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "BRIDGE_DELEGATION", "getBRIDGE_DELEGATION()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "SYNTHETIC_NOT_AUTOBOXED_CHECK", "getSYNTHETIC_NOT_AUTOBOXED_CHECK()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "PARTIAL_LINKAGE_RUNTIME_ERROR", "getPARTIAL_LINKAGE_RUNTIME_ERROR()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "SYNTHESIZED_INIT_BLOCK", "getSYNTHESIZED_INIT_BLOCK()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "DEFAULT_DISPATCH_CALL", "getDEFAULT_DISPATCH_CALL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "STATEMENT_ORIGIN_INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE", "getSTATEMENT_ORIGIN_INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "INLINED_FUNCTION_REFERENCE", "getINLINED_FUNCTION_REFERENCE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "INLINE_LAMBDA", "getINLINE_LAMBDA()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(Companion.class, "INLINE_ARGS_CONTAINER", "getINLINE_ARGS_CONTAINER()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0)};
            $$delegatedProperties = kPropertyArr;
            Companion companion = new Companion();
            $$INSTANCE = companion;
            IrStatementOriginImpl.Companion companion2 = IrStatementOriginImpl.INSTANCE;
            SAFE_CALL = companion2.m389provideDelegate((Object) companion, kPropertyArr[0]);
            UMINUS = companion2.m389provideDelegate((Object) companion, kPropertyArr[1]);
            UPLUS = companion2.m389provideDelegate((Object) companion, kPropertyArr[2]);
            EXCL = companion2.m389provideDelegate((Object) companion, kPropertyArr[3]);
            EXCLEXCL = companion2.m389provideDelegate((Object) companion, kPropertyArr[4]);
            ELVIS = companion2.m389provideDelegate((Object) companion, kPropertyArr[5]);
            LT = companion2.m389provideDelegate((Object) companion, kPropertyArr[6]);
            GT = companion2.m389provideDelegate((Object) companion, kPropertyArr[7]);
            LTEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[8]);
            GTEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[9]);
            EQEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[10]);
            EQEQEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[11]);
            EXCLEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[12]);
            EXCLEQEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[13]);
            IN = companion2.m389provideDelegate((Object) companion, kPropertyArr[14]);
            NOT_IN = companion2.m389provideDelegate((Object) companion, kPropertyArr[15]);
            ANDAND = companion2.m389provideDelegate((Object) companion, kPropertyArr[16]);
            OROR = companion2.m389provideDelegate((Object) companion, kPropertyArr[17]);
            PLUS = companion2.m389provideDelegate((Object) companion, kPropertyArr[18]);
            MINUS = companion2.m389provideDelegate((Object) companion, kPropertyArr[19]);
            MUL = companion2.m389provideDelegate((Object) companion, kPropertyArr[20]);
            DIV = companion2.m389provideDelegate((Object) companion, kPropertyArr[21]);
            PERC = companion2.m389provideDelegate((Object) companion, kPropertyArr[22]);
            RANGE = companion2.m389provideDelegate((Object) companion, kPropertyArr[23]);
            RANGE_UNTIL = companion2.m389provideDelegate((Object) companion, kPropertyArr[24]);
            INVOKE = companion2.m389provideDelegate((Object) companion, kPropertyArr[25]);
            VARIABLE_AS_FUNCTION = companion2.m389provideDelegate((Object) companion, kPropertyArr[26]);
            GET_ARRAY_ELEMENT = companion2.m389provideDelegate((Object) companion, kPropertyArr[27]);
            PREFIX_INCR = companion2.m389provideDelegate((Object) companion, kPropertyArr[28]);
            PREFIX_DECR = companion2.m389provideDelegate((Object) companion, kPropertyArr[29]);
            POSTFIX_INCR = companion2.m389provideDelegate((Object) companion, kPropertyArr[30]);
            POSTFIX_DECR = companion2.m389provideDelegate((Object) companion, kPropertyArr[31]);
            EQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[32]);
            PLUSEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[33]);
            MINUSEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[34]);
            MULTEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[35]);
            DIVEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[36]);
            PERCEQ = companion2.m389provideDelegate((Object) companion, kPropertyArr[37]);
            ARGUMENTS_REORDERING_FOR_CALL = companion2.m389provideDelegate((Object) companion, kPropertyArr[38]);
            IMPLICIT_ARGUMENT = companion2.m389provideDelegate((Object) companion, kPropertyArr[39]);
            DESTRUCTURING_DECLARATION = companion2.m389provideDelegate((Object) companion, kPropertyArr[40]);
            GET_PROPERTY = companion2.m389provideDelegate((Object) companion, kPropertyArr[41]);
            GET_LOCAL_PROPERTY = companion2.m389provideDelegate((Object) companion, kPropertyArr[42]);
            IF = companion2.m389provideDelegate((Object) companion, kPropertyArr[43]);
            WHEN = companion2.m389provideDelegate((Object) companion, kPropertyArr[44]);
            WHEN_COMMA = companion2.m389provideDelegate((Object) companion, kPropertyArr[45]);
            WHILE_LOOP = companion2.m389provideDelegate((Object) companion, kPropertyArr[46]);
            DO_WHILE_LOOP = companion2.m389provideDelegate((Object) companion, kPropertyArr[47]);
            FOR_LOOP = companion2.m389provideDelegate((Object) companion, kPropertyArr[48]);
            FOR_LOOP_ITERATOR = companion2.m389provideDelegate((Object) companion, kPropertyArr[49]);
            FOR_LOOP_INNER_WHILE = companion2.m389provideDelegate((Object) companion, kPropertyArr[50]);
            FOR_LOOP_HAS_NEXT = companion2.m389provideDelegate((Object) companion, kPropertyArr[51]);
            FOR_LOOP_NEXT = companion2.m389provideDelegate((Object) companion, kPropertyArr[52]);
            LAMBDA = companion2.m389provideDelegate((Object) companion, kPropertyArr[53]);
            DEFAULT_VALUE = companion2.m389provideDelegate((Object) companion, kPropertyArr[54]);
            ANONYMOUS_FUNCTION = companion2.m389provideDelegate((Object) companion, kPropertyArr[55]);
            OBJECT_LITERAL = companion2.m389provideDelegate((Object) companion, kPropertyArr[56]);
            ADAPTED_FUNCTION_REFERENCE = companion2.m389provideDelegate((Object) companion, kPropertyArr[57]);
            SUSPEND_CONVERSION = companion2.m389provideDelegate((Object) companion, kPropertyArr[58]);
            FUN_INTERFACE_CONSTRUCTOR_REFERENCE = companion2.m389provideDelegate((Object) companion, kPropertyArr[59]);
            INITIALIZE_PROPERTY_FROM_PARAMETER = companion2.m389provideDelegate((Object) companion, kPropertyArr[60]);
            INITIALIZE_FIELD = companion2.m389provideDelegate((Object) companion, kPropertyArr[61]);
            PROPERTY_REFERENCE_FOR_DELEGATE = companion2.m389provideDelegate((Object) companion, kPropertyArr[62]);
            BRIDGE_DELEGATION = companion2.m389provideDelegate((Object) companion, kPropertyArr[63]);
            SYNTHETIC_NOT_AUTOBOXED_CHECK = companion2.m389provideDelegate((Object) companion, kPropertyArr[64]);
            PARTIAL_LINKAGE_RUNTIME_ERROR = companion2.m389provideDelegate((Object) companion, kPropertyArr[65]);
            SYNTHESIZED_INIT_BLOCK = companion2.m389provideDelegate((Object) companion, kPropertyArr[66]);
            DEFAULT_DISPATCH_CALL = companion2.m389provideDelegate((Object) companion, kPropertyArr[67]);
            STATEMENT_ORIGIN_INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE = new IrStatementOriginImpl("INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE");
            INLINED_FUNCTION_REFERENCE = companion2.m389provideDelegate((Object) companion, kPropertyArr[69]);
            INLINE_LAMBDA = companion2.m389provideDelegate((Object) companion, kPropertyArr[70]);
            INLINE_ARGS_CONTAINER = companion2.m389provideDelegate((Object) companion, kPropertyArr[71]);
        }

        private Companion() {
        }

        public final IrStatementOriginImpl getADAPTED_FUNCTION_REFERENCE() {
            return (IrStatementOriginImpl) ADAPTED_FUNCTION_REFERENCE.getValue(this, $$delegatedProperties[57]);
        }

        public final IrStatementOriginImpl getANDAND() {
            return (IrStatementOriginImpl) ANDAND.getValue(this, $$delegatedProperties[16]);
        }

        public final IrStatementOriginImpl getANONYMOUS_FUNCTION() {
            return (IrStatementOriginImpl) ANONYMOUS_FUNCTION.getValue(this, $$delegatedProperties[55]);
        }

        public final IrStatementOriginImpl getARGUMENTS_REORDERING_FOR_CALL() {
            return (IrStatementOriginImpl) ARGUMENTS_REORDERING_FOR_CALL.getValue(this, $$delegatedProperties[38]);
        }

        public final IrStatementOriginImpl getBRIDGE_DELEGATION() {
            return (IrStatementOriginImpl) BRIDGE_DELEGATION.getValue(this, $$delegatedProperties[63]);
        }

        public final IrStatementOriginImpl getDEFAULT_DISPATCH_CALL() {
            return (IrStatementOriginImpl) DEFAULT_DISPATCH_CALL.getValue(this, $$delegatedProperties[67]);
        }

        public final IrStatementOriginImpl getDEFAULT_VALUE() {
            return (IrStatementOriginImpl) DEFAULT_VALUE.getValue(this, $$delegatedProperties[54]);
        }

        public final IrStatementOriginImpl getDESTRUCTURING_DECLARATION() {
            return (IrStatementOriginImpl) DESTRUCTURING_DECLARATION.getValue(this, $$delegatedProperties[40]);
        }

        public final IrStatementOriginImpl getDIV() {
            return (IrStatementOriginImpl) DIV.getValue(this, $$delegatedProperties[21]);
        }

        public final IrStatementOriginImpl getDIVEQ() {
            return (IrStatementOriginImpl) DIVEQ.getValue(this, $$delegatedProperties[36]);
        }

        public final IrStatementOriginImpl getDO_WHILE_LOOP() {
            return (IrStatementOriginImpl) DO_WHILE_LOOP.getValue(this, $$delegatedProperties[47]);
        }

        public final IrStatementOriginImpl getELVIS() {
            return (IrStatementOriginImpl) ELVIS.getValue(this, $$delegatedProperties[5]);
        }

        public final IrStatementOriginImpl getEQ() {
            return (IrStatementOriginImpl) EQ.getValue(this, $$delegatedProperties[32]);
        }

        public final IrStatementOriginImpl getEQEQ() {
            return (IrStatementOriginImpl) EQEQ.getValue(this, $$delegatedProperties[10]);
        }

        public final IrStatementOriginImpl getEQEQEQ() {
            return (IrStatementOriginImpl) EQEQEQ.getValue(this, $$delegatedProperties[11]);
        }

        public final IrStatementOriginImpl getEXCL() {
            return (IrStatementOriginImpl) EXCL.getValue(this, $$delegatedProperties[3]);
        }

        public final IrStatementOriginImpl getEXCLEQ() {
            return (IrStatementOriginImpl) EXCLEQ.getValue(this, $$delegatedProperties[12]);
        }

        public final IrStatementOriginImpl getEXCLEQEQ() {
            return (IrStatementOriginImpl) EXCLEQEQ.getValue(this, $$delegatedProperties[13]);
        }

        public final IrStatementOriginImpl getEXCLEXCL() {
            return (IrStatementOriginImpl) EXCLEXCL.getValue(this, $$delegatedProperties[4]);
        }

        public final IrStatementOriginImpl getFOR_LOOP() {
            return (IrStatementOriginImpl) FOR_LOOP.getValue(this, $$delegatedProperties[48]);
        }

        public final IrStatementOriginImpl getFOR_LOOP_HAS_NEXT() {
            return (IrStatementOriginImpl) FOR_LOOP_HAS_NEXT.getValue(this, $$delegatedProperties[51]);
        }

        public final IrStatementOriginImpl getFOR_LOOP_INNER_WHILE() {
            return (IrStatementOriginImpl) FOR_LOOP_INNER_WHILE.getValue(this, $$delegatedProperties[50]);
        }

        public final IrStatementOriginImpl getFOR_LOOP_ITERATOR() {
            return (IrStatementOriginImpl) FOR_LOOP_ITERATOR.getValue(this, $$delegatedProperties[49]);
        }

        public final IrStatementOriginImpl getFOR_LOOP_NEXT() {
            return (IrStatementOriginImpl) FOR_LOOP_NEXT.getValue(this, $$delegatedProperties[52]);
        }

        public final IrStatementOriginImpl getFUN_INTERFACE_CONSTRUCTOR_REFERENCE() {
            return (IrStatementOriginImpl) FUN_INTERFACE_CONSTRUCTOR_REFERENCE.getValue(this, $$delegatedProperties[59]);
        }

        public final IrStatementOriginImpl getGET_ARRAY_ELEMENT() {
            return (IrStatementOriginImpl) GET_ARRAY_ELEMENT.getValue(this, $$delegatedProperties[27]);
        }

        public final IrStatementOriginImpl getGET_LOCAL_PROPERTY() {
            return (IrStatementOriginImpl) GET_LOCAL_PROPERTY.getValue(this, $$delegatedProperties[42]);
        }

        public final IrStatementOriginImpl getGET_PROPERTY() {
            return (IrStatementOriginImpl) GET_PROPERTY.getValue(this, $$delegatedProperties[41]);
        }

        public final IrStatementOriginImpl getGT() {
            return (IrStatementOriginImpl) GT.getValue(this, $$delegatedProperties[7]);
        }

        public final IrStatementOriginImpl getGTEQ() {
            return (IrStatementOriginImpl) GTEQ.getValue(this, $$delegatedProperties[9]);
        }

        public final IrStatementOriginImpl getIF() {
            return (IrStatementOriginImpl) IF.getValue(this, $$delegatedProperties[43]);
        }

        public final IrStatementOriginImpl getIMPLICIT_ARGUMENT() {
            return (IrStatementOriginImpl) IMPLICIT_ARGUMENT.getValue(this, $$delegatedProperties[39]);
        }

        public final IrStatementOriginImpl getIN() {
            return (IrStatementOriginImpl) IN.getValue(this, $$delegatedProperties[14]);
        }

        public final IrStatementOriginImpl getINITIALIZE_FIELD() {
            return (IrStatementOriginImpl) INITIALIZE_FIELD.getValue(this, $$delegatedProperties[61]);
        }

        public final IrStatementOriginImpl getINITIALIZE_PROPERTY_FROM_PARAMETER() {
            return (IrStatementOriginImpl) INITIALIZE_PROPERTY_FROM_PARAMETER.getValue(this, $$delegatedProperties[60]);
        }

        public final IrStatementOriginImpl getINLINED_FUNCTION_REFERENCE() {
            return (IrStatementOriginImpl) INLINED_FUNCTION_REFERENCE.getValue(this, $$delegatedProperties[69]);
        }

        public final IrStatementOriginImpl getINLINE_ARGS_CONTAINER() {
            return (IrStatementOriginImpl) INLINE_ARGS_CONTAINER.getValue(this, $$delegatedProperties[71]);
        }

        public final IrStatementOriginImpl getINLINE_LAMBDA() {
            return (IrStatementOriginImpl) INLINE_LAMBDA.getValue(this, $$delegatedProperties[70]);
        }

        public final IrStatementOriginImpl getINVOKE() {
            return (IrStatementOriginImpl) INVOKE.getValue(this, $$delegatedProperties[25]);
        }

        public final IrStatementOriginImpl getLAMBDA() {
            return (IrStatementOriginImpl) LAMBDA.getValue(this, $$delegatedProperties[53]);
        }

        public final IrStatementOriginImpl getLT() {
            return (IrStatementOriginImpl) LT.getValue(this, $$delegatedProperties[6]);
        }

        public final IrStatementOriginImpl getLTEQ() {
            return (IrStatementOriginImpl) LTEQ.getValue(this, $$delegatedProperties[8]);
        }

        public final IrStatementOriginImpl getMINUS() {
            return (IrStatementOriginImpl) MINUS.getValue(this, $$delegatedProperties[19]);
        }

        public final IrStatementOriginImpl getMINUSEQ() {
            return (IrStatementOriginImpl) MINUSEQ.getValue(this, $$delegatedProperties[34]);
        }

        public final IrStatementOriginImpl getMUL() {
            return (IrStatementOriginImpl) MUL.getValue(this, $$delegatedProperties[20]);
        }

        public final IrStatementOriginImpl getMULTEQ() {
            return (IrStatementOriginImpl) MULTEQ.getValue(this, $$delegatedProperties[35]);
        }

        public final IrStatementOriginImpl getNOT_IN() {
            return (IrStatementOriginImpl) NOT_IN.getValue(this, $$delegatedProperties[15]);
        }

        public final IrStatementOriginImpl getOBJECT_LITERAL() {
            return (IrStatementOriginImpl) OBJECT_LITERAL.getValue(this, $$delegatedProperties[56]);
        }

        public final IrStatementOriginImpl getOROR() {
            return (IrStatementOriginImpl) OROR.getValue(this, $$delegatedProperties[17]);
        }

        public final IrStatementOriginImpl getPARTIAL_LINKAGE_RUNTIME_ERROR() {
            return (IrStatementOriginImpl) PARTIAL_LINKAGE_RUNTIME_ERROR.getValue(this, $$delegatedProperties[65]);
        }

        public final IrStatementOriginImpl getPERC() {
            return (IrStatementOriginImpl) PERC.getValue(this, $$delegatedProperties[22]);
        }

        public final IrStatementOriginImpl getPERCEQ() {
            return (IrStatementOriginImpl) PERCEQ.getValue(this, $$delegatedProperties[37]);
        }

        public final IrStatementOriginImpl getPLUS() {
            return (IrStatementOriginImpl) PLUS.getValue(this, $$delegatedProperties[18]);
        }

        public final IrStatementOriginImpl getPLUSEQ() {
            return (IrStatementOriginImpl) PLUSEQ.getValue(this, $$delegatedProperties[33]);
        }

        public final IrStatementOriginImpl getPOSTFIX_DECR() {
            return (IrStatementOriginImpl) POSTFIX_DECR.getValue(this, $$delegatedProperties[31]);
        }

        public final IrStatementOriginImpl getPOSTFIX_INCR() {
            return (IrStatementOriginImpl) POSTFIX_INCR.getValue(this, $$delegatedProperties[30]);
        }

        public final IrStatementOriginImpl getPREFIX_DECR() {
            return (IrStatementOriginImpl) PREFIX_DECR.getValue(this, $$delegatedProperties[29]);
        }

        public final IrStatementOriginImpl getPREFIX_INCR() {
            return (IrStatementOriginImpl) PREFIX_INCR.getValue(this, $$delegatedProperties[28]);
        }

        public final IrStatementOriginImpl getPROPERTY_REFERENCE_FOR_DELEGATE() {
            return (IrStatementOriginImpl) PROPERTY_REFERENCE_FOR_DELEGATE.getValue(this, $$delegatedProperties[62]);
        }

        public final IrStatementOriginImpl getRANGE() {
            return (IrStatementOriginImpl) RANGE.getValue(this, $$delegatedProperties[23]);
        }

        public final IrStatementOriginImpl getRANGE_UNTIL() {
            return (IrStatementOriginImpl) RANGE_UNTIL.getValue(this, $$delegatedProperties[24]);
        }

        public final IrStatementOriginImpl getSAFE_CALL() {
            return (IrStatementOriginImpl) SAFE_CALL.getValue(this, $$delegatedProperties[0]);
        }

        public final IrStatementOriginImpl getSTATEMENT_ORIGIN_INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE() {
            return STATEMENT_ORIGIN_INITIALIZER_OF_FIELD_FOR_CAPTURED_VALUE.m388getValue((Object) this, $$delegatedProperties[68]);
        }

        public final IrStatementOriginImpl getSUSPEND_CONVERSION() {
            return (IrStatementOriginImpl) SUSPEND_CONVERSION.getValue(this, $$delegatedProperties[58]);
        }

        public final IrStatementOriginImpl getSYNTHESIZED_INIT_BLOCK() {
            return (IrStatementOriginImpl) SYNTHESIZED_INIT_BLOCK.getValue(this, $$delegatedProperties[66]);
        }

        public final IrStatementOriginImpl getSYNTHETIC_NOT_AUTOBOXED_CHECK() {
            return (IrStatementOriginImpl) SYNTHETIC_NOT_AUTOBOXED_CHECK.getValue(this, $$delegatedProperties[64]);
        }

        public final IrStatementOriginImpl getUMINUS() {
            return (IrStatementOriginImpl) UMINUS.getValue(this, $$delegatedProperties[1]);
        }

        public final IrStatementOriginImpl getUPLUS() {
            return (IrStatementOriginImpl) UPLUS.getValue(this, $$delegatedProperties[2]);
        }

        public final IrStatementOriginImpl getVARIABLE_AS_FUNCTION() {
            return (IrStatementOriginImpl) VARIABLE_AS_FUNCTION.getValue(this, $$delegatedProperties[26]);
        }

        public final IrStatementOriginImpl getWHEN() {
            return (IrStatementOriginImpl) WHEN.getValue(this, $$delegatedProperties[44]);
        }

        public final IrStatementOriginImpl getWHEN_COMMA() {
            return (IrStatementOriginImpl) WHEN_COMMA.getValue(this, $$delegatedProperties[45]);
        }

        public final IrStatementOriginImpl getWHILE_LOOP() {
            return (IrStatementOriginImpl) WHILE_LOOP.getValue(this, $$delegatedProperties[46]);
        }
    }

    String getDebugName();

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÂ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\tHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin$COMPONENT_N;", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "index", "", "<init>", "(I)V", "getIndex", "()I", "debugName", "", "getDebugName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "Companion", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class COMPONENT_N implements IrStatementOrigin {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final COMPONENT_N[] precreatedComponents;
        private final String debugName;
        private final int index;

        static {
            COMPONENT_N[] component_nArr = new COMPONENT_N[32];
            int i = 0;
            while (i < 32) {
                int i2 = i + 1;
                component_nArr[i] = new COMPONENT_N(i2);
                i = i2;
            }
            precreatedComponents = component_nArr;
        }

        private COMPONENT_N(int i) {
            this.index = i;
            this.debugName = "COMPONENT_" + i;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getIndex() {
            return this.index;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof COMPONENT_N) && this.index == ((COMPONENT_N) other).index;
        }

        @Override // org.jetbrains.kotlin.ir.expressions.IrStatementOrigin
        public String getDebugName() {
            return this.debugName;
        }

        public final int getIndex() {
            return this.index;
        }

        public int hashCode() {
            return Integer.hashCode(this.index);
        }

        public String toString() {
            return "COMPONENT_N(index=" + this.index + ')';
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nR\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin$COMPONENT_N$Companion;", "", "<init>", "()V", "precreatedComponents", "", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin$COMPONENT_N;", "[Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin$COMPONENT_N;", "withIndex", "index", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final COMPONENT_N withIndex(int index) {
                return index < COMPONENT_N.precreatedComponents.length ? COMPONENT_N.precreatedComponents[index - 1] : new COMPONENT_N(index, null);
            }

            private Companion() {
            }
        }

        public /* synthetic */ COMPONENT_N(int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(i);
        }
    }
}
