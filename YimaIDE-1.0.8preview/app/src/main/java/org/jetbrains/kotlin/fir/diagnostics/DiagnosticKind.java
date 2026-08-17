package org.jetbrains.kotlin.fir.diagnostics;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b.\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "ExpressionExpected", "NotLoopLabel", "JumpOutsideLoop", "VariableExpected", "ReturnNotAllowed", "UnresolvedLabel", "AmbiguousLabel", "LabelNameClash", "NotAFunctionLabel", "NoThis", "IllegalConstExpression", "IllegalSelector", "NoReceiverAllowed", "IllegalUnderscore", "DeserializationError", "InferenceError", "RecursionInImplicitTypes", "Java", "SuperNotAllowed", "ValueParameterWithNoTypeAnnotation", "IllegalProjectionUsage", "MissingStdlibClass", "NotASupertype", "SuperNotAvailable", "AnnotationInWhereClause", "MultipleAnnotationWithAllTarget", "LoopInSupertype", "RecursiveTypealiasExpansion", "UnresolvedSupertype", "UnresolvedExpandedType", "IncorrectCharacterLiteral", "EmptyCharacterLiteral", "TooManyCharactersInCharacterLiteral", "IllegalEscape", "IntLiteralOutOfRange", "IntLiteralWithLeadingZeros", "FloatLiteralOutOfRange", "WrongLongSuffix", "UnsignedNumbersAreNotPresent", "IsEnumEntry", "EnumEntryAsType", "UnderscoreWithoutRenamingInDestructuring", "Other", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum DiagnosticKind {
    ExpressionExpected,
    NotLoopLabel,
    JumpOutsideLoop,
    VariableExpected,
    ReturnNotAllowed,
    UnresolvedLabel,
    AmbiguousLabel,
    LabelNameClash,
    NotAFunctionLabel,
    NoThis,
    IllegalConstExpression,
    IllegalSelector,
    NoReceiverAllowed,
    IllegalUnderscore,
    DeserializationError,
    InferenceError,
    RecursionInImplicitTypes,
    Java,
    SuperNotAllowed,
    ValueParameterWithNoTypeAnnotation,
    IllegalProjectionUsage,
    MissingStdlibClass,
    NotASupertype,
    SuperNotAvailable,
    AnnotationInWhereClause,
    MultipleAnnotationWithAllTarget,
    LoopInSupertype,
    RecursiveTypealiasExpansion,
    UnresolvedSupertype,
    UnresolvedExpandedType,
    IncorrectCharacterLiteral,
    EmptyCharacterLiteral,
    TooManyCharactersInCharacterLiteral,
    IllegalEscape,
    IntLiteralOutOfRange,
    IntLiteralWithLeadingZeros,
    FloatLiteralOutOfRange,
    WrongLongSuffix,
    UnsignedNumbersAreNotPresent,
    IsEnumEntry,
    EnumEntryAsType,
    UnderscoreWithoutRenamingInDestructuring,
    Other;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<DiagnosticKind> getEntries() {
        return $ENTRIES;
    }
}
