package org.jetbrains.kotlin.config.phaser;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LoggingContext;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\u0004\b\u0001\u0010\u0003*\u0004\b\u0002\u0010\u00042\u00020\u0005J-\u0010\u0006\u001a\u00028\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001H&¢\u0006\u0002\u0010\rJ2\u0010\u000e\u001a\"\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0011\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00120\u00100\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u0011H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "Context", "Lorg/jetbrains/kotlin/config/LoggingContext;", "Input", "Output", Argument.Delimiters.none, "invoke", "phaseConfig", "Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "phaserState", "Lorg/jetbrains/kotlin/config/phaser/PhaserState;", "context", "input", "(Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;Lorg/jetbrains/kotlin/config/phaser/PhaserState;Lorg/jetbrains/kotlin/config/LoggingContext;Ljava/lang/Object;)Ljava/lang/Object;", "getNamedSubphases", Argument.Delimiters.none, "Lkotlin/Pair;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "startDepth", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface CompilerPhase<Context extends LoggingContext, Input, Output> {
    static /* synthetic */ List getNamedSubphases$default(CompilerPhase compilerPhase, int i, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getNamedSubphases");
            return null;
        }
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return compilerPhase.getNamedSubphases(i);
    }

    default List<Pair<Integer, NamedCompilerPhase<Context, ?, ?>>> getNamedSubphases(int startDepth) {
        return CollectionsKt.emptyList();
    }

    Output invoke(PhaseConfig phaseConfig, PhaserState phaserState, Context context, Input input);
}
