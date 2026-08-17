package org.jetbrains.kotlin.config.phaser;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LoggingContext;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\b\u0003\b&\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\u0004\b\u0001\u0010\u0003*\u0004\b\u0002\u0010\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0005B\u0089\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u001a\b\u0002\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00000\t\u0012$\b\u0002\u0010\n\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00028\u0001`\r0\t\u0012$\b\u0002\u0010\u000e\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00028\u0002`\r0\t\u00126\b\u0002\u0010\u000f\u001a0\u0012,\u0012*\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0010j\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000`\u00120\t\u0012N\b\u0002\u0010\u0013\u001aH\u0012D\u0012B\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0010j\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0014\u0012\u0004\u0012\u00028\u0000`\u00120\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018J-\u0010!\u001a\u00028\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010(J\u001d\u0010)\u001a\u00028\u00022\u0006\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00028\u0001H&¢\u0006\u0002\u0010*J-\u0010+\u001a\u00028\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00028\u0001H&¢\u0006\u0002\u0010(J-\u0010,\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010-J5\u0010.\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00028\u00012\u0006\u0010/\u001a\u00028\u0002H\u0002¢\u0006\u0002\u00100J-\u00101\u001a\u00028\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u00002\u0006\u00102\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010(J0\u00103\u001a\"\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00000\u0014042\u0006\u00105\u001a\u00020\u0016H\u0016J\n\u00106\u001a\u00020\u0007H\u0096\u0080\u0004R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR#\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00000\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR-\u0010\n\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00028\u0001`\r0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR-\u0010\u000e\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00028\u0002`\r0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR<\u0010\u000f\u001a0\u0012,\u0012*\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0010j\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000`\u00120\tX\u0082\u0004¢\u0006\u0002\n\u0000RT\u0010\u0013\u001aH\u0012D\u0012B\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0010j\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0014\u0012\u0004\u0012\u00028\u0000`\u00120\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u0016X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Context", "Lorg/jetbrains/kotlin/config/LoggingContext;", "Input", "Output", "Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", ModuleXmlParser.NAME, Argument.Delimiters.none, "prerequisite", Argument.Delimiters.none, "preconditions", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/phaser/Checker;", "postconditions", "preactions", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/config/phaser/ActionState;", "Lorg/jetbrains/kotlin/config/phaser/Action;", "postactions", "Lkotlin/Pair;", "nlevels", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;I)V", "getName", "()Ljava/lang/String;", "getPrerequisite", "()Ljava/util/Set;", "getPreconditions", "getPostconditions", "getNlevels", "()I", "invoke", "phaseConfig", "Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "phaserState", "Lorg/jetbrains/kotlin/config/phaser/PhaserState;", "context", "input", "(Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;Lorg/jetbrains/kotlin/config/phaser/PhaserState;Lorg/jetbrains/kotlin/config/LoggingContext;Ljava/lang/Object;)Ljava/lang/Object;", "phaseBody", "(Lorg/jetbrains/kotlin/config/LoggingContext;Ljava/lang/Object;)Ljava/lang/Object;", "outputIfNotEnabled", "runBefore", "(Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;Lorg/jetbrains/kotlin/config/phaser/PhaserState;Lorg/jetbrains/kotlin/config/LoggingContext;Ljava/lang/Object;)V", "runAfter", "output", "(Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;Lorg/jetbrains/kotlin/config/phaser/PhaserState;Lorg/jetbrains/kotlin/config/LoggingContext;Ljava/lang/Object;Ljava/lang/Object;)V", "runAndProfile", "source", "getNamedSubphases", Argument.Delimiters.none, "startDepth", "toString", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class NamedCompilerPhase<Context extends LoggingContext, Input, Output> implements CompilerPhase<Context, Input, Output> {
    private final String name;
    private final int nlevels;
    private final Set<Function3<ActionState, Pair<? extends Input, ? extends Output>, Context, Unit>> postactions;
    private final Set<Function1<Output, Unit>> postconditions;
    private final Set<Function3<ActionState, Input, Context, Unit>> preactions;
    private final Set<Function1<Input, Unit>> preconditions;
    private final Set<NamedCompilerPhase<?, ?, ?>> prerequisite;

    public /* synthetic */ NamedCompilerPhase(String str, Set set, Set set2, Set set3, Set set4, Set set5, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? SetsKt.emptySet() : set, (i2 & 4) != 0 ? SetsKt.emptySet() : set2, (i2 & 8) != 0 ? SetsKt.emptySet() : set3, (i2 & 16) != 0 ? SetsKt.emptySet() : set4, (i2 & 32) != 0 ? SetsKt.emptySet() : set5, (i2 & 64) != 0 ? 0 : i);
    }

    private final void runAfter(PhaseConfig phaseConfig, PhaserState phaserState, Context context, Input input, Output output) {
        ActionState actionState = new ActionState(phaseConfig, this, phaserState.getPhaseCount(), BeforeOrAfter.AFTER);
        Iterator<Function3<ActionState, Pair<? extends Input, ? extends Output>, Context, Unit>> it = this.postactions.iterator();
        while (it.hasNext()) {
            it.next().invoke(actionState, TuplesKt.to(input, output), context);
        }
        if (phaseConfig.getCheckConditions()) {
            Iterator<Function1<Output, Unit>> it2 = this.postconditions.iterator();
            while (it2.hasNext()) {
                it2.next().invoke(output);
            }
        }
    }

    private final Output runAndProfile(PhaseConfig phaseConfig, PhaserState phaserState, Context context, Input source) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i = this.nlevels;
        phaserState.setDepth(phaserState.getDepth() + i);
        Output outputPhaseBody = phaseBody(context, source);
        phaserState.setDepth(phaserState.getDepth() - i);
        objectRef.element = outputPhaseBody;
        System.out.println((Object) (StringsKt.repeat("\t", phaserState.getDepth()) + this.name + ": " + (System.currentTimeMillis() - jCurrentTimeMillis) + " msec"));
        return (Output) objectRef.element;
    }

    private final void runBefore(PhaseConfig phaseConfig, PhaserState phaserState, Context context, Input input) {
        ActionState actionState = new ActionState(phaseConfig, this, phaserState.getPhaseCount(), BeforeOrAfter.BEFORE);
        Iterator<Function3<ActionState, Input, Context, Unit>> it = this.preactions.iterator();
        while (it.hasNext()) {
            it.next().invoke(actionState, input, context);
        }
        if (phaseConfig.getCheckConditions()) {
            Iterator<Function1<Input, Unit>> it2 = this.preconditions.iterator();
            while (it2.hasNext()) {
                it2.next().invoke(input);
            }
        }
    }

    public final String getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.config.phaser.CompilerPhase
    public List<Pair<Integer, NamedCompilerPhase<Context, ?, ?>>> getNamedSubphases(int startDepth) {
        return CollectionsKt.listOf(TuplesKt.to(Integer.valueOf(startDepth), this));
    }

    public final int getNlevels() {
        return this.nlevels;
    }

    public final Set<Function1<Output, Unit>> getPostconditions() {
        return this.postconditions;
    }

    public final Set<Function1<Input, Unit>> getPreconditions() {
        return this.preconditions;
    }

    public final Set<NamedCompilerPhase<?, ?, ?>> getPrerequisite() {
        return this.prerequisite;
    }

    @Override // org.jetbrains.kotlin.config.phaser.CompilerPhase
    public Output invoke(PhaseConfig phaseConfig, PhaserState phaserState, Context context, Input input) {
        Output outputRunAndProfile;
        phaseConfig.getClass();
        phaserState.getClass();
        context.getClass();
        if (!phaseConfig.isEnabled(this)) {
            return outputIfNotEnabled(phaseConfig, phaserState, context, input);
        }
        phaserState.getAlreadyDone().containsAll(this.prerequisite);
        context.setInVerbosePhase(phaseConfig.isVerbose(this));
        runBefore(phaseConfig, phaserState, context, input);
        if (phaseConfig.getNeedProfiling()) {
            outputRunAndProfile = runAndProfile(phaseConfig, phaserState, context, input);
        } else {
            int i = this.nlevels;
            phaserState.setDepth(phaserState.getDepth() + i);
            Output outputPhaseBody = phaseBody(context, input);
            phaserState.setDepth(phaserState.getDepth() - i);
            outputRunAndProfile = outputPhaseBody;
        }
        runAfter(phaseConfig, phaserState, context, input, outputRunAndProfile);
        Output output = outputRunAndProfile;
        context.setInVerbosePhase(false);
        phaserState.getAlreadyDone().add(this);
        phaserState.setPhaseCount(phaserState.getPhaseCount() + 1);
        return output;
    }

    public abstract Output outputIfNotEnabled(PhaseConfig phaseConfig, PhaserState phaserState, Context context, Input input);

    public abstract Output phaseBody(Context context, Input input);

    public String toString() {
        return "Compiler Phase @" + this.name;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NamedCompilerPhase(String str, Set<? extends NamedCompilerPhase<?, ?, ?>> set, Set<? extends Function1<? super Input, Unit>> set2, Set<? extends Function1<? super Output, Unit>> set3, Set<? extends Function3<? super ActionState, ? super Input, ? super Context, Unit>> set4, Set<? extends Function3<? super ActionState, ? super Pair<? extends Input, ? extends Output>, ? super Context, Unit>> set5, int i) {
        str.getClass();
        set.getClass();
        set2.getClass();
        set3.getClass();
        set4.getClass();
        set5.getClass();
        this.name = str;
        this.prerequisite = set;
        this.preconditions = set2;
        this.postconditions = set3;
        this.preactions = set4;
        this.postactions = set5;
        this.nlevels = i;
    }
}
