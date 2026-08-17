package org.jetbrains.kotlin.cli.common.repl;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\u000f\u001a\u001a\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\bH\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0011H\u0016J\u0018\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\bH\u0016J$\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u00020\u001c\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001e0\u001d0\u00102\u0006\u0010\u001f\u001a\u00020 H\u0016R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/HistoryActionsForRepeatRecentOnly;", "Lorg/jetbrains/kotlin/cli/common/repl/HistoryActionsForNoRepeat;", "state", "Lorg/jetbrains/kotlin/cli/common/repl/GenericReplEvaluatorState;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/repl/GenericReplEvaluatorState;)V", "currentLast", "Lorg/jetbrains/kotlin/cli/common/repl/ReplHistoryRecord;", "Lorg/jetbrains/kotlin/cli/common/repl/EvalClassWithInstanceAndLoader;", "getCurrentLast", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplHistoryRecord;", "effectiveHistory", Argument.Delimiters.none, "getEffectiveHistory", "()Ljava/util/List;", "firstMismatch", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/cli/common/repl/ILineId;", "other", "Lkotlin/sequences/Sequence;", "addPlaceholder", Argument.Delimiters.none, "lineId", "value", "removePlaceholder", Argument.Delimiters.none, "addFinal", "processClasses", "Ljava/lang/ClassLoader;", "Ljava/lang/Class;", Argument.Delimiters.none, "compileResult", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
class HistoryActionsForRepeatRecentOnly extends HistoryActionsForNoRepeat {
    private final ReplHistoryRecord<EvalClassWithInstanceAndLoader> currentLast;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HistoryActionsForRepeatRecentOnly(GenericReplEvaluatorState genericReplEvaluatorState) {
        super(genericReplEvaluatorState);
        genericReplEvaluatorState.getClass();
        ReplHistoryRecord<EvalClassWithInstanceAndLoader> replHistoryRecordPeek = genericReplEvaluatorState.getHistory().peek();
        replHistoryRecordPeek.getClass();
        this.currentLast = replHistoryRecordPeek;
    }

    public static boolean a(HistoryActionsForRepeatRecentOnly historyActionsForRepeatRecentOnly, ReplHistoryRecord replHistoryRecord) {
        replHistoryRecord.getClass();
        return !Intrinsics.areEqual(replHistoryRecord.getId(), historyActionsForRepeatRecentOnly.currentLast.getId());
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public void addFinal(ILineId lineId, EvalClassWithInstanceAndLoader value) {
        lineId.getClass();
        value.getClass();
        getState().getHistory().pop();
        getState().getHistory().push(lineId, value);
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public void addPlaceholder(ILineId lineId, EvalClassWithInstanceAndLoader value) {
        lineId.getClass();
        value.getClass();
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public Pair<ReplHistoryRecord<EvalClassWithInstanceAndLoader>, ILineId> firstMismatch(Sequence<? extends ILineId> other) {
        other.getClass();
        return ReplStateKt.firstMismatchFiltered(getState().getHistory(), other, new Function1() { // from class: org.jetbrains.kotlin.cli.common.repl.b
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HistoryActionsForRepeatRecentOnly.a(this.b, (ReplHistoryRecord) obj));
            }
        });
    }

    public final ReplHistoryRecord<EvalClassWithInstanceAndLoader> getCurrentLast() {
        return this.currentLast;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public List<EvalClassWithInstanceAndLoader> getEffectiveHistory() {
        return CollectionsKt.dropLast(super.getEffectiveHistory(), 1);
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public Pair<ClassLoader, Class<? extends Object>> processClasses(ReplCompileResult.CompiledClasses compileResult) {
        compileResult.getClass();
        return TuplesKt.to(this.currentLast.getItem().getClassLoader(), JvmClassMappingKt.getJavaClass(this.currentLast.getItem().getKlass()));
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public boolean removePlaceholder(ILineId lineId) {
        lineId.getClass();
        return true;
    }
}
