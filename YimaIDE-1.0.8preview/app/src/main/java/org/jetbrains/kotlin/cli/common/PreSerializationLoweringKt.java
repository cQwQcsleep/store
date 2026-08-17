package org.jetbrains.kotlin.cli.common;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.PreSerializationLoweringContext;
import org.jetbrains.kotlin.backend.common.RunPreSerializationLoweringPhasesKt;
import org.jetbrains.kotlin.backend.common.phaser.PhaseEngine;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.NamedCompilerPhase;
import org.jetbrains.kotlin.fir.pipeline.Fir2IrActualizedResult;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001aB\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u001e\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b0\u0007¨\u0006\n"}, d2 = {"runPreSerializationLoweringPhases", "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "T", "Lorg/jetbrains/kotlin/backend/common/PreSerializationLoweringContext;", "Lorg/jetbrains/kotlin/backend/common/phaser/PhaseEngine;", "fir2IrActualizedResult", "lowerings", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PreSerializationLoweringKt {
    public static final <T extends PreSerializationLoweringContext> Fir2IrActualizedResult runPreSerializationLoweringPhases(PhaseEngine<T> phaseEngine, Fir2IrActualizedResult fir2IrActualizedResult, List<? extends NamedCompilerPhase<? super T, IrModuleFragment, IrModuleFragment>> list) {
        phaseEngine.getClass();
        fir2IrActualizedResult.getClass();
        list.getClass();
        return Fir2IrActualizedResult.copy$default(fir2IrActualizedResult, RunPreSerializationLoweringPhasesKt.runPreSerializationLoweringPhases(phaseEngine, list, fir2IrActualizedResult.getIrModuleFragment()), null, null, null, null, null, 62, null);
    }
}
