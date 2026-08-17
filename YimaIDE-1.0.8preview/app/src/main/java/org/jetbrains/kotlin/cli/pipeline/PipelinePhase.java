package org.jetbrains.kotlin.cli.pipeline;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.ActionState;
import org.jetbrains.kotlin.config.phaser.NamedCompilerPhase;
import org.jetbrains.kotlin.config.phaser.PhaseConfig;
import org.jetbrains.kotlin.config.phaser.PhaserState;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B\u007f\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00126\b\u0002\u0010\b\u001a0\u0012,\u0012*\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\nj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005`\r0\t\u00126\b\u0002\u0010\u000e\u001a0\u0012,\u0012*\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\nj\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u0005`\r0\t¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0011\u001a\u00028\u00012\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00028\u0000¢\u0006\u0002\u0010\u0014J\u0017\u0010\u0015\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0013\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0016J-\u0010\u0017\u001a\u00028\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "I", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "O", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", ModuleXmlParser.NAME, Argument.Delimiters.none, "preActions", Argument.Delimiters.none, "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/config/phaser/ActionState;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/phaser/Action;", "postActions", "<init>", "(Ljava/lang/String;Ljava/util/Set;Ljava/util/Set;)V", "phaseBody", "context", "input", "(Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;)Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "executePhase", "(Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;)Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "outputIfNotEnabled", "phaseConfig", "Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "phaserState", "Lorg/jetbrains/kotlin/config/phaser/PhaserState;", "(Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;Lorg/jetbrains/kotlin/config/phaser/PhaserState;Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;)Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PipelinePhase<I extends PipelineArtifact, O extends PipelineArtifact> extends NamedCompilerPhase<PipelineContext, I, O> {
    public PipelinePhase(String str, Set<? extends Function3<? super ActionState, ? super I, ? super PipelineContext, Unit>> set, Set<? extends Function3<? super ActionState, ? super O, ? super PipelineContext, Unit>> set2) {
        str.getClass();
        set.getClass();
        set2.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = set2.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(PipelinePhaseKt.toPostAction((Function3) it.next()));
        }
        super(str, null, null, null, set, linkedHashSet, 0, 78, null);
    }

    public abstract O executePhase(I input);

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.config.phaser.NamedCompilerPhase
    public O outputIfNotEnabled(PhaseConfig phaseConfig, PhaserState phaserState, PipelineContext context, I input) throws KotlinNothingValueException {
        phaseConfig.getClass();
        phaserState.getClass();
        context.getClass();
        input.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.config.phaser.NamedCompilerPhase
    public final O phaseBody(PipelineContext context, I input) {
        context.getClass();
        input.getClass();
        O o = (O) executePhase(input);
        if (o != null) {
            return o;
        }
        throw new PipelineStepException(false, 1, null);
    }

    public /* synthetic */ PipelinePhase(String str, Set set, Set set2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? SetsKt.emptySet() : set, (i & 4) != 0 ? SetsKt.emptySet() : set2);
    }
}
