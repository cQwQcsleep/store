package org.jetbrains.kotlin.ir.backend.js.utils.serialization;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/utils/serialization/ExpressionIds;", "", "<init>", "()V", "THIS_REF", "", "NULL", "TRUE_LITERAL", "FALSE_LITERAL", "STRING_LITERAL", "REG_EXP", "INT_LITERAL", "DOUBLE_LITERAL", "ARRAY_LITERAL", "OBJECT_LITERAL", "FUNCTION", "DOC_COMMENT", "BINARY_OPERATION", "PREFIX_OPERATION", "POSTFIX_OPERATION", "CONDITIONAL", "ARRAY_ACCESS", "NAME_REFERENCE", "SIMPLE_NAME_REFERENCE", "PROPERTY_REFERENCE", "INVOCATION", "NEW", "CLASS", "SUPER_REF", "YIELD", "BIGINT_LITERAL", "YIELD_STAR", "TEMPLATE_STRING_LITERAL", "TEMPLATE_ELEMENT_STRING", "TEMPLATE_ELEMENT_INTERPOLATION", "SPREAD", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ExpressionIds {
    public static final int ARRAY_ACCESS = 16;
    public static final int ARRAY_LITERAL = 8;
    public static final int BIGINT_LITERAL = 25;
    public static final int BINARY_OPERATION = 12;
    public static final int CLASS = 22;
    public static final int CONDITIONAL = 15;
    public static final int DOC_COMMENT = 11;
    public static final int DOUBLE_LITERAL = 7;
    public static final int FALSE_LITERAL = 3;
    public static final int FUNCTION = 10;
    public static final ExpressionIds INSTANCE = new ExpressionIds();
    public static final int INT_LITERAL = 6;
    public static final int INVOCATION = 20;
    public static final int NAME_REFERENCE = 17;
    public static final int NEW = 21;
    public static final int NULL = 1;
    public static final int OBJECT_LITERAL = 9;
    public static final int POSTFIX_OPERATION = 14;
    public static final int PREFIX_OPERATION = 13;
    public static final int PROPERTY_REFERENCE = 19;
    public static final int REG_EXP = 5;
    public static final int SIMPLE_NAME_REFERENCE = 18;
    public static final int SPREAD = 30;
    public static final int STRING_LITERAL = 4;
    public static final int SUPER_REF = 23;
    public static final int TEMPLATE_ELEMENT_INTERPOLATION = 29;
    public static final int TEMPLATE_ELEMENT_STRING = 28;
    public static final int TEMPLATE_STRING_LITERAL = 27;
    public static final int THIS_REF = 0;
    public static final int TRUE_LITERAL = 2;
    public static final int YIELD = 24;
    public static final int YIELD_STAR = 26;

    private ExpressionIds() {
    }
}
