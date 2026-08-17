package org.jetbrains.kotlin.config.phaser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LoggingContext;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.ActionState;
import org.jetbrains.kotlin.config.phaser.CompilerPhaseKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001aO\u0010\b\u001a\u0002H\t\"\b\b\u0000\u0010\n*\u00020\u000b\"\u0004\b\u0001\u0010\f\"\u0004\b\u0002\u0010\t*\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\t0\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u0002H\n2\u0006\u0010\u0011\u001a\u0002H\f¢\u0006\u0002\u0010\u0012\u001a\u0099\u0001\u0010\u001c\u001a*\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00160\u001aj\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n`\u001d\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\n**\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00160\u001aj\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n`\u001d2.\u0010\u001e\u001a*\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00160\u001aj\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n`\u001dH\u0086\u0006*(\u0010\u0013\u001a\u0004\b\u0000\u0010\u0014\"\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00160\u00152\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00160\u0015*\"\u0010\u0017\"\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00182\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0018*F\u0010\u0019\u001a\u0004\b\u0000\u0010\u0014\u001a\u0004\b\u0001\u0010\n\"\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00160\u001a2\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00160\u001a\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001f"}, d2 = {"downlevel", "R", "Lorg/jetbrains/kotlin/config/phaser/PhaserState;", "nlevels", Argument.Delimiters.none, "block", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/config/phaser/PhaserState;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "invokeToplevel", "Output", "Context", "Lorg/jetbrains/kotlin/config/LoggingContext;", "Input", "Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "phaseConfig", "Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "context", "input", "(Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;Lorg/jetbrains/kotlin/config/LoggingContext;Ljava/lang/Object;)Ljava/lang/Object;", "Checker", "Data", "Lkotlin/Function1;", Argument.Delimiters.none, "AnyNamedPhase", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Action", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/config/phaser/ActionState;", "plus", "Lorg/jetbrains/kotlin/config/phaser/Action;", "other", "org.jetbrains.kotlin:config"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompilerPhaseKt {
    public static Unit a(Function3 function3, Function3 function4, ActionState actionState, Object obj, Object obj2) {
        actionState.getClass();
        function3.invoke(actionState, obj, obj2);
        function4.invoke(actionState, obj, obj2);
        return Unit.INSTANCE;
    }

    public static final <R> R downlevel(PhaserState phaserState, int i, Function0<? extends R> function0) {
        phaserState.getClass();
        function0.getClass();
        phaserState.setDepth(phaserState.getDepth() + i);
        R r = (R) function0.invoke();
        phaserState.setDepth(phaserState.getDepth() - i);
        return r;
    }

    public static final <Context extends LoggingContext, Input, Output> Output invokeToplevel(CompilerPhase<? super Context, Input, Output> compilerPhase, PhaseConfig phaseConfig, Context context, Input input) {
        compilerPhase.getClass();
        phaseConfig.getClass();
        context.getClass();
        return compilerPhase.invoke(phaseConfig, new PhaserState(null, 0, 0, 7, null), context, input);
    }

    public static final <Data, Context> Function3<ActionState, Data, Context, Unit> plus(final Function3<? super ActionState, ? super Data, ? super Context, Unit> function3, final Function3<? super ActionState, ? super Data, ? super Context, Unit> function4) {
        function3.getClass();
        function4.getClass();
        return new Function3() { // from class: v92
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CompilerPhaseKt.a(function3, function4, (ActionState) obj, obj2, obj3);
            }
        };
    }
}
