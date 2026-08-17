package org.jetbrains.kotlin.backend.common.phaser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.kotlin.backend.common.DisposableContext;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.config.LoggingContext;
import org.jetbrains.kotlin.config.phaser.NamedCompilerPhase;
import org.jetbrains.kotlin.config.phaser.PhaseConfig;
import org.jetbrains.kotlin.config.phaser.PhaserState;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 **\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001*B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\t\u0010\nJG\u0010\u0012\u001a\u0002H\u0013\"\f\b\u0001\u0010\u0014*\u00020\u0015*\u00020\u0002\"\u0004\b\u0002\u0010\u00132\u0006\u0010\u0016\u001a\u0002H\u00142\u0018\u0010\u0017\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u0000\u0012\u0004\u0012\u0002H\u00130\u0018H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0019JO\u0010\u0012\u001a\u0002H\u0013\"\f\b\u0001\u0010\u0014*\u00020\u0015*\u00020\u0002\"\u0004\b\u0002\u0010\u00132\u0006\u0010\u0016\u001a\u0002H\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0018\u0010\u0017\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u0000\u0012\u0004\u0012\u0002H\u00130\u0018H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001cJC\u0010\u001d\u001a\u0002H\u0013\"\b\b\u0001\u0010\u0014*\u00020\u0002\"\u0004\b\u0002\u0010\u00132\u0006\u0010\u0016\u001a\u0002H\u00142\u0018\u0010\u0017\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u0000\u0012\u0004\u0012\u0002H\u00130\u0018H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001eJM\u0010\u001f\u001a\u0002H \"\u0004\b\u0001\u0010!\"\u0004\b\u0002\u0010 \"\u001a\b\u0003\u0010\"*\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H 0#2\u0006\u0010$\u001a\u0002H\"2\u0006\u0010%\u001a\u0002H!2\b\b\u0002\u0010&\u001a\u00020\u001b¢\u0006\u0002\u0010'J5\u0010\u001f\u001a\u0002H \"\u0004\b\u0001\u0010 \"\u001a\b\u0002\u0010\"*\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u0002H 0#2\u0006\u0010$\u001a\u0002H\"¢\u0006\u0002\u0010)R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/phaser/PhaseEngine;", "Context", "Lorg/jetbrains/kotlin/config/LoggingContext;", "", "phaseConfig", "Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "phaserState", "Lorg/jetbrains/kotlin/config/phaser/PhaserState;", "context", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;Lorg/jetbrains/kotlin/config/phaser/PhaserState;Lorg/jetbrains/kotlin/config/LoggingContext;)V", "getPhaseConfig", "()Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "getPhaserState", "()Lorg/jetbrains/kotlin/config/phaser/PhaserState;", "getContext", "()Lorg/jetbrains/kotlin/config/LoggingContext;", "Lorg/jetbrains/kotlin/config/LoggingContext;", "useContext", "R", "NewContext", "Lorg/jetbrains/kotlin/backend/common/DisposableContext;", "newContext", "action", "Lkotlin/Function1;", "(Lorg/jetbrains/kotlin/backend/common/DisposableContext;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "copyState", "", "(Lorg/jetbrains/kotlin/backend/common/DisposableContext;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "newEngine", "(Lorg/jetbrains/kotlin/config/LoggingContext;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "runPhase", "Output", "Input", "P", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "phase", "input", "disable", "(Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;Ljava/lang/Object;Z)Ljava/lang/Object;", "", "(Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;)Ljava/lang/Object;", "Companion", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PhaseEngine<Context extends LoggingContext> {
    private final Context context;
    private final PhaseConfig phaseConfig;
    private final PhaserState phaserState;

    public PhaseEngine(PhaseConfig phaseConfig, PhaserState phaserState, Context context) {
        phaseConfig.getClass();
        phaserState.getClass();
        context.getClass();
        this.phaseConfig = phaseConfig;
        this.phaserState = phaserState;
        this.context = context;
    }

    public static /* synthetic */ Object runPhase$default(PhaseEngine phaseEngine, NamedCompilerPhase namedCompilerPhase, Object obj, boolean z, int i, Object obj2) {
        if ((i & 4) != 0) {
            z = false;
        }
        return phaseEngine.runPhase(namedCompilerPhase, obj, z);
    }

    public final Context getContext() {
        return this.context;
    }

    public final PhaseConfig getPhaseConfig() {
        return this.phaseConfig;
    }

    public final PhaserState getPhaserState() {
        return this.phaserState;
    }

    public final <NewContext extends LoggingContext, R> R newEngine(NewContext newContext, Function1<? super PhaseEngine<NewContext>, ? extends R> action) {
        newContext.getClass();
        action.getClass();
        return (R) action.invoke(new PhaseEngine(getPhaseConfig(), getPhaserState(), newContext));
    }

    public final <Input, Output, P extends NamedCompilerPhase<? super Context, Input, Output>> Output runPhase(P phase, Input input, boolean disable) {
        phase.getClass();
        PhaseConfig phaseConfig = this.phaseConfig;
        return disable ? (Output) phase.outputIfNotEnabled(phaseConfig, this.phaserState, this.context, input) : (Output) phase.invoke(phaseConfig, this.phaserState, this.context, input);
    }

    public final <NewContext extends DisposableContext & LoggingContext, R> R useContext(NewContext newContext, boolean copyState, Function1<? super PhaseEngine<NewContext>, ? extends R> action) {
        newContext.getClass();
        action.getClass();
        PhaseEngine phaseEngine = new PhaseEngine(getPhaseConfig(), copyState ? getPhaserState().copyOf() : getPhaserState(), newContext);
        try {
            return (R) action.invoke(phaseEngine);
        } finally {
            InlineMarker.finallyStart(1);
            newContext.dispose();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <Output, P extends NamedCompilerPhase<? super Context, Unit, Output>> Output runPhase(P phase) {
        phase.getClass();
        return (Output) runPhase$default(this, phase, Unit.INSTANCE, false, 4, null);
    }

    public final <NewContext extends DisposableContext & LoggingContext, R> R useContext(NewContext newContext, Function1<? super PhaseEngine<NewContext>, ? extends R> action) {
        newContext.getClass();
        action.getClass();
        PhaseEngine phaseEngine = new PhaseEngine(getPhaseConfig(), getPhaserState(), newContext);
        try {
            return (R) action.invoke(phaseEngine);
        } finally {
            InlineMarker.finallyStart(1);
            newContext.dispose();
            InlineMarker.finallyEnd(1);
        }
    }
}
