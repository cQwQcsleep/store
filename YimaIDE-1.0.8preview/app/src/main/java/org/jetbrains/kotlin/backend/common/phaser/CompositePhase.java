package org.jetbrains.kotlin.backend.common.phaser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.config.LoggingContext;
import org.jetbrains.kotlin.config.phaser.CompilerPhase;
import org.jetbrains.kotlin.config.phaser.NamedCompilerPhase;
import org.jetbrains.kotlin.config.phaser.PhaseConfig;
import org.jetbrains.kotlin.config.phaser.PhaserState;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u0003*\u0004\b\u0002\u0010\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0005B+\u0012\"\u0010\u0006\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00050\u0007¢\u0006\u0004\b\t\u0010\nJ-\u0010\r\u001a\u00028\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0014J0\u0010\u0015\u001a\"\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0017\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00180\u00160\u00072\u0006\u0010\u0019\u001a\u00020\u0017H\u0016R-\u0010\u0006\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/phaser/CompositePhase;", "Context", "Lorg/jetbrains/kotlin/config/LoggingContext;", "Input", "Output", "Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "phases", "", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/List;)V", "getPhases", "()Ljava/util/List;", "invoke", "phaseConfig", "Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "phaserState", "Lorg/jetbrains/kotlin/config/phaser/PhaserState;", "context", "input", "(Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;Lorg/jetbrains/kotlin/config/phaser/PhaserState;Lorg/jetbrains/kotlin/config/LoggingContext;Ljava/lang/Object;)Ljava/lang/Object;", "getNamedSubphases", "Lkotlin/Pair;", "", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "startDepth", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class CompositePhase<Context extends LoggingContext, Input, Output> implements CompilerPhase<Context, Input, Output> {
    private final List<CompilerPhase<Context, Object, Object>> phases;

    public CompositePhase(List<? extends CompilerPhase<? super Context, Object, Object>> list) {
        list.getClass();
        this.phases = list;
    }

    public List<Pair<Integer, NamedCompilerPhase<Context, ?, ?>>> getNamedSubphases(int startDepth) {
        List<CompilerPhase<Context, Object, Object>> list = this.phases;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((CompilerPhase) it.next()).getNamedSubphases(startDepth));
        }
        return arrayList;
    }

    public final List<CompilerPhase<Context, Object, Object>> getPhases() {
        return this.phases;
    }

    public Output invoke(PhaseConfig phaseConfig, PhaserState phaserState, Context context, Input input) {
        phaseConfig.getClass();
        phaserState.getClass();
        context.getClass();
        Iterator<T> it = this.phases.iterator();
        while (it.hasNext()) {
            input = (Output) ((CompilerPhase) it.next()).invoke(phaseConfig, phaserState, context, input);
        }
        return (Output) input;
    }
}
