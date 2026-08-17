package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\f0\u000eH\u0086\bø\u0001\u0000j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/ConstantArgumentKind;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "VALID_CONST", "RESOLUTION_ERROR", "NOT_CONST", "ENUM_NOT_CONST", "NOT_KCLASS_LITERAL", "NOT_CONST_VAL_IN_CONST_EXPRESSION", "KCLASS_LITERAL_OF_TYPE_PARAMETER_ERROR", "ifNotValidConst", Argument.Delimiters.none, "action", "Lkotlin/Function1;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum ConstantArgumentKind {
    VALID_CONST,
    RESOLUTION_ERROR,
    NOT_CONST,
    ENUM_NOT_CONST,
    NOT_KCLASS_LITERAL,
    NOT_CONST_VAL_IN_CONST_EXPRESSION,
    KCLASS_LITERAL_OF_TYPE_PARAMETER_ERROR;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<ConstantArgumentKind> getEntries() {
        return $ENTRIES;
    }

    public final void ifNotValidConst(Function1<? super ConstantArgumentKind, Unit> action) {
        action.getClass();
        if (this != VALID_CONST) {
            action.invoke(this);
        }
    }
}
