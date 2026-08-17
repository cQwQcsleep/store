package org.jetbrains.kotlin.cli.common.repl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003H\u0002¨\u0006\u0004"}, d2 = {"adjustHistories", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/repl/ILineId;", "Lorg/jetbrains/kotlin/cli/common/repl/AggregatedReplStageState;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class GenericReplCompilingEvaluatorKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Iterable<ILineId> adjustHistories(AggregatedReplStageState<?, ?> aggregatedReplStageState) {
        Iterable<ILineId> iterableResetTo;
        ReplHistoryRecord<?> replHistoryRecordPeek = aggregatedReplStageState.getState2().getHistory().peek();
        return (replHistoryRecordPeek == null || (iterableResetTo = aggregatedReplStageState.getState1().getHistory().resetTo(replHistoryRecordPeek.getId())) == null) ? aggregatedReplStageState.getState1().getHistory().reset() : iterableResetTo;
    }
}
