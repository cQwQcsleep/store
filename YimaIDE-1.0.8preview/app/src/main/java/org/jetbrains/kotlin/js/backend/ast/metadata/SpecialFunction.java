package org.jetbrains.kotlin.js.backend.ast.metadata;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/ast/metadata/SpecialFunction;", "", "suggestedName", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getSuggestedName", "()Ljava/lang/String;", "DEFINE_INLINE_FUNCTION", "WRAP_FUNCTION", "TO_BOXED_CHAR", "UNBOX_CHAR", "SUSPEND_CALL", "COROUTINE_RESULT", "COROUTINE_CONTROLLER", "COROUTINE_RECEIVER", "SET_COROUTINE_RESULT", "GET_KCLASS", "GET_REIFIED_TYPE_PARAMETER_KTYPE", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum SpecialFunction {
    DEFINE_INLINE_FUNCTION("defineInlineFunction"),
    WRAP_FUNCTION("wrapFunction"),
    TO_BOXED_CHAR("toBoxedChar"),
    UNBOX_CHAR("unboxChar"),
    SUSPEND_CALL("suspendCall"),
    COROUTINE_RESULT("coroutineResult"),
    COROUTINE_CONTROLLER("coroutineController"),
    COROUTINE_RECEIVER("coroutineReceiver"),
    SET_COROUTINE_RESULT("setCoroutineResult"),
    GET_KCLASS("getKClass"),
    GET_REIFIED_TYPE_PARAMETER_KTYPE("getReifiedTypeParameterKType");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String suggestedName;

    SpecialFunction(String str) {
        this.suggestedName = str;
    }

    public static EnumEntries<SpecialFunction> getEntries() {
        return $ENTRIES;
    }

    public final String getSuggestedName() {
        return this.suggestedName;
    }
}
