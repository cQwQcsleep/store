package org.jetbrains.kotlin.cli.common.repl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\u000f\u001a\u001a\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0006H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0011H\u0016J\u0018\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0006H\u0016J$\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u00020\u001c\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001e0\u001d0\u00102\u0006\u0010\u001f\u001a\u00020 H\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/HistoryActionsForRepeatAny;", "Lorg/jetbrains/kotlin/cli/common/repl/HistoryActionsForNoRepeat;", "state", "Lorg/jetbrains/kotlin/cli/common/repl/GenericReplEvaluatorState;", "matchingLine", "Lorg/jetbrains/kotlin/cli/common/repl/ReplHistoryRecord;", "Lorg/jetbrains/kotlin/cli/common/repl/EvalClassWithInstanceAndLoader;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/repl/GenericReplEvaluatorState;Lorg/jetbrains/kotlin/cli/common/repl/ReplHistoryRecord;)V", "getMatchingLine", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplHistoryRecord;", "effectiveHistory", Argument.Delimiters.none, "getEffectiveHistory", "()Ljava/util/List;", "firstMismatch", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/cli/common/repl/ILineId;", "other", "Lkotlin/sequences/Sequence;", "addPlaceholder", Argument.Delimiters.none, "lineId", "value", "removePlaceholder", Argument.Delimiters.none, "addFinal", "processClasses", "Ljava/lang/ClassLoader;", "Ljava/lang/Class;", Argument.Delimiters.none, "compileResult", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
class HistoryActionsForRepeatAny extends HistoryActionsForNoRepeat {
    private final ReplHistoryRecord<EvalClassWithInstanceAndLoader> matchingLine;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HistoryActionsForRepeatAny(GenericReplEvaluatorState genericReplEvaluatorState, ReplHistoryRecord<EvalClassWithInstanceAndLoader> replHistoryRecord) {
        super(genericReplEvaluatorState);
        genericReplEvaluatorState.getClass();
        replHistoryRecord.getClass();
        this.matchingLine = replHistoryRecord;
    }

    public static boolean a(HistoryActionsForRepeatAny historyActionsForRepeatAny, ReplHistoryRecord replHistoryRecord) {
        replHistoryRecord.getClass();
        return !Intrinsics.areEqual(replHistoryRecord.getId(), historyActionsForRepeatAny.matchingLine.getId());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public void addFinal(ILineId lineId, EvalClassWithInstanceAndLoader value) {
        List<ReplHistoryRecord> list;
        lineId.getClass();
        value.getClass();
        IReplStageHistory<EvalClassWithInstanceAndLoader> history = getState().getHistory();
        if (!history.isEmpty()) {
            ListIterator<ReplHistoryRecord<? extends T>> listIterator = history.listIterator(history.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    list = CollectionsKt.toList(history);
                    break;
                }
                if (!Intrinsics.areEqual(((ReplHistoryRecord) listIterator.previous()).getId(), this.matchingLine.getId())) {
                    listIterator.next();
                    int size = history.size() - listIterator.nextIndex();
                    if (size != 0) {
                        ArrayList arrayList = new ArrayList(size);
                        while (listIterator.hasNext()) {
                            arrayList.add(listIterator.next());
                        }
                        list = arrayList;
                        break;
                    }
                    list = CollectionsKt.emptyList();
                    break;
                }
            }
        } else {
            list = CollectionsKt.emptyList();
        }
        getState().getHistory().resetTo(lineId);
        getState().getHistory().pop();
        getState().getHistory().push(lineId, value);
        for (ReplHistoryRecord replHistoryRecord : list) {
            getState().getHistory().push(replHistoryRecord.getId(), replHistoryRecord.getItem());
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public void addPlaceholder(ILineId lineId, EvalClassWithInstanceAndLoader value) {
        lineId.getClass();
        value.getClass();
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public Pair<ReplHistoryRecord<EvalClassWithInstanceAndLoader>, ILineId> firstMismatch(Sequence<? extends ILineId> other) {
        other.getClass();
        return ReplStateKt.firstMismatchWhile(getState().getHistory(), other, new Function1() { // from class: org.jetbrains.kotlin.cli.common.repl.a
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HistoryActionsForRepeatAny.a(this.b, (ReplHistoryRecord) obj));
            }
        });
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public List<EvalClassWithInstanceAndLoader> getEffectiveHistory() {
        IReplStageHistory<EvalClassWithInstanceAndLoader> history = getState().getHistory();
        ArrayList arrayList = new ArrayList();
        for (Object obj : history) {
            if (Intrinsics.areEqual(((ReplHistoryRecord) obj).getId(), this.matchingLine.getId())) {
                break;
            }
            arrayList.add(obj);
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((EvalClassWithInstanceAndLoader) ((ReplHistoryRecord) it.next()).getItem());
        }
        return arrayList2;
    }

    public final ReplHistoryRecord<EvalClassWithInstanceAndLoader> getMatchingLine() {
        return this.matchingLine;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public Pair<ClassLoader, Class<? extends Object>> processClasses(ReplCompileResult.CompiledClasses compileResult) {
        compileResult.getClass();
        return TuplesKt.to(this.matchingLine.getItem().getClassLoader(), JvmClassMappingKt.getJavaClass(this.matchingLine.getItem().getKlass()));
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat
    public boolean removePlaceholder(ILineId lineId) {
        lineId.getClass();
        return true;
    }
}
