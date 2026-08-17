package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u0004\u0005\u0006\u0007\b\t\nB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", Argument.Delimiters.none, "<init>", "()V", "Evaluated", "NotEvaluated", "DuringEvaluation", "CompileTimeException", "DivisionByZero", "TrimMarginBlankPrefix", "RecursionInInitializer", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$CompileTimeException;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$DuringEvaluation;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$Evaluated;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$NotEvaluated;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirEvaluatorResult {

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$DivisionByZero;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$CompileTimeException;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class DivisionByZero extends CompileTimeException {
        public static final DivisionByZero INSTANCE = new DivisionByZero();

        private DivisionByZero() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof DivisionByZero);
        }

        public int hashCode() {
            return -1023700109;
        }

        public String toString() {
            return "DivisionByZero";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$DuringEvaluation;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class DuringEvaluation extends FirEvaluatorResult {
        public static final DuringEvaluation INSTANCE = new DuringEvaluation();

        private DuringEvaluation() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof DuringEvaluation);
        }

        public int hashCode() {
            return -243636380;
        }

        public String toString() {
            return "DuringEvaluation";
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$Evaluated;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/FirElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirElement;)V", "getResult", "()Lorg/jetbrains/kotlin/fir/FirElement;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Evaluated extends FirEvaluatorResult {
        private final FirElement result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Evaluated(FirElement firElement) {
            super(null);
            firElement.getClass();
            this.result = firElement;
        }

        public final FirElement getResult() {
            return this.result;
        }

        public String toString() {
            return UtilsKt.render(this.result);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$NotEvaluated;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class NotEvaluated extends FirEvaluatorResult {
        public static final NotEvaluated INSTANCE = new NotEvaluated();

        private NotEvaluated() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof NotEvaluated);
        }

        public int hashCode() {
            return -1824174113;
        }

        public String toString() {
            return "NotEvaluated";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$RecursionInInitializer;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$CompileTimeException;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class RecursionInInitializer extends CompileTimeException {
        public static final RecursionInInitializer INSTANCE = new RecursionInInitializer();

        private RecursionInInitializer() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof RecursionInInitializer);
        }

        public int hashCode() {
            return 480779778;
        }

        public String toString() {
            return "RecursionInInitializer";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$TrimMarginBlankPrefix;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$CompileTimeException;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class TrimMarginBlankPrefix extends CompileTimeException {
        public static final TrimMarginBlankPrefix INSTANCE = new TrimMarginBlankPrefix();

        private TrimMarginBlankPrefix() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof TrimMarginBlankPrefix);
        }

        public int hashCode() {
            return -2116448305;
        }

        public String toString() {
            return "TrimMarginBlankPrefix";
        }
    }

    public /* synthetic */ FirEvaluatorResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0004\u0005\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$CompileTimeException;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", "<init>", "()V", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$DivisionByZero;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$RecursionInInitializer;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$TrimMarginBlankPrefix;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class CompileTimeException extends FirEvaluatorResult {
        private CompileTimeException() {
            super(null);
        }

        public /* synthetic */ CompileTimeException(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private FirEvaluatorResult() {
    }
}
