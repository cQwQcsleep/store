package org.jetbrains.kotlin.cli.pipeline;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhaseKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.ActionState;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0086\u0001\u0010\u0000\u001aB\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\u0012\u0004\u0012\u0002H\u0006`\b\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0004\b\u0002\u0010\u0006**\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006`\bH\u0002¨\u0006\t"}, d2 = {"toPostAction", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/config/phaser/ActionState;", "Lkotlin/Pair;", "Input", "Output", "Context", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/phaser/Action;", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PipelinePhaseKt {
    public static Unit a(Function3 function3, ActionState actionState, Pair pair, Object obj) {
        actionState.getClass();
        pair.getClass();
        function3.invoke(actionState, pair.getSecond(), obj);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <Input, Output, Context> Function3<ActionState, Pair<? extends Input, ? extends Output>, Context, Unit> toPostAction(final Function3<? super ActionState, ? super Output, ? super Context, Unit> function3) {
        return new Function3() { // from class: y1b
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return PipelinePhaseKt.a(function3, (ActionState) obj, (Pair) obj2, obj3);
            }
        };
    }
}
