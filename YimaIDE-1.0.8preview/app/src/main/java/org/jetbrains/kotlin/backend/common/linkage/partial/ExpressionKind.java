package org.jetbrains.kotlin.backend.common.linkage.partial;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/ExpressionKind;", "", "prefix", "", "postfix", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getPrefix", "()Ljava/lang/String;", "getPostfix", "REFERENCE", "CALLING", "CALLING_INSTANCE_INITIALIZER", "READING", "WRITING", "GETTING_INSTANCE", "TYPE_OPERATOR", "SAM_CONVERSION", "ANONYMOUS_OBJECT_LITERAL", "OTHER_EXPRESSION", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
enum ExpressionKind {
    REFERENCE("Reference to", "can not be evaluated"),
    CALLING(null, "can not be called"),
    CALLING_INSTANCE_INITIALIZER("Instance initializer of", "can not be called"),
    READING("Can not read value from", null),
    WRITING("Can not write value to", null),
    GETTING_INSTANCE("Can not get instance of", null),
    TYPE_OPERATOR("Type operator expression", "can not be evaluated"),
    SAM_CONVERSION("Single abstract method (SAM) conversion expression", "can not be evaluated"),
    ANONYMOUS_OBJECT_LITERAL("Anonymous object literal", "can not be evaluated"),
    OTHER_EXPRESSION("Expression", "can not be evaluated");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String postfix;
    private final String prefix;

    ExpressionKind(String str, String str2) {
        this.prefix = str;
        this.postfix = str2;
    }

    public static EnumEntries<ExpressionKind> getEntries() {
        return $ENTRIES;
    }

    public final String getPostfix() {
        return this.postfix;
    }

    public final String getPrefix() {
        return this.prefix;
    }
}
