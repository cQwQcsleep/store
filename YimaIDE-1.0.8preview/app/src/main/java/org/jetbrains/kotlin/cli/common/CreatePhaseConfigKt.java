package org.jetbrains.kotlin.cli.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.CompilerPhase;
import org.jetbrains.kotlin.config.phaser.NamedCompilerPhase;
import org.jetbrains.kotlin.config.phaser.PhaseConfig;
import org.jetbrains.kotlin.config.phaser.PhaseSet;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirPlatformIncompatibilityDiagnosticRendererKt;
import org.jetbrains.kotlin.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032 \b\u0002\u0010\u0004\u001a\u001a\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006j\u0002`\u0007\u0018\u00010\u0005\u001a>\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0002\u001a\u00020\u00032 \b\u0002\u0010\u0004\u001a\u001a\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006j\u0002`\u0007\u0018\u00010\u0005H\u0002\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\u00012\u0012\u0010\r\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000e\u001a/\u0010\u000b\u001a\u00020\f*\u00020\u00012\u001c\u0010\u000f\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006j\u0002`\u00070\u0005H\u0007¢\u0006\u0002\b\u0010\u001a6\u0010\u000b\u001a\u00020\f*\u00020\u00012(\u0010\u000f\u001a$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006j\u0002`\u00070\t0\u0005H\u0002\u001a\u001d\u0010\u0012\u001a\u00020\n2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014H\u0002¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"createPhaseConfig", "Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "phasesToExecute", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/config/phaser/AnyNamedPhase;", "getCornerPhasesToDump", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/config/phaser/PhaseSet;", "list", Argument.Delimiters.none, "compoundPhase", "Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "phases", "listPhases", Argument.Delimiters.none, "createPhaseSetFromArguments", "names", Argument.Delimiters.none, Argument.Delimiters.none, "([Ljava/lang/String;)Lorg/jetbrains/kotlin/config/phaser/PhaseSet;", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CreatePhaseConfigKt {
    public static final PhaseConfig createPhaseConfig(CommonCompilerArguments commonCompilerArguments, List<? extends NamedCompilerPhase<?, ?, ?>> list) {
        commonCompilerArguments.getClass();
        PhaseSet phaseSetCreatePhaseSetFromArguments = createPhaseSetFromArguments(commonCompilerArguments.getPhasesToDump());
        PhaseSet phaseSetCreatePhaseSetFromArguments2 = createPhaseSetFromArguments(commonCompilerArguments.getPhasesToValidate());
        Pair<PhaseSet, PhaseSet> cornerPhasesToDump = getCornerPhasesToDump(commonCompilerArguments, list);
        return new PhaseConfig(createPhaseSetFromArguments(commonCompilerArguments.getDisablePhases()), createPhaseSetFromArguments(commonCompilerArguments.getVerbosePhases()), createPhaseSetFromArguments(commonCompilerArguments.getPhasesToDumpBefore()).plus(phaseSetCreatePhaseSetFromArguments).plus((PhaseSet) cornerPhasesToDump.component1()), createPhaseSetFromArguments(commonCompilerArguments.getPhasesToDumpAfter()).plus(phaseSetCreatePhaseSetFromArguments).plus((PhaseSet) cornerPhasesToDump.component2()), createPhaseSetFromArguments(commonCompilerArguments.getPhasesToValidateBefore()).plus(phaseSetCreatePhaseSetFromArguments2), createPhaseSetFromArguments(commonCompilerArguments.getPhasesToValidateAfter()).plus(phaseSetCreatePhaseSetFromArguments2), commonCompilerArguments.getDumpDirectory(), commonCompilerArguments.getDumpOnlyFqName(), commonCompilerArguments.getProfilePhases(), commonCompilerArguments.getCheckPhaseConditions());
    }

    public static /* synthetic */ PhaseConfig createPhaseConfig$default(CommonCompilerArguments commonCompilerArguments, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = null;
        }
        return createPhaseConfig(commonCompilerArguments, list);
    }

    private static final PhaseSet createPhaseSetFromArguments(String[] strArr) {
        if (strArr == null) {
            return PhaseSet.Empty.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(str));
        }
        return arrayList.contains("all") ? PhaseSet.All.INSTANCE : new PhaseSet.Enum(ArraysKt.toSet(strArr));
    }

    private static final Pair<PhaseSet, PhaseSet> getCornerPhasesToDump(CommonCompilerArguments commonCompilerArguments, List<? extends NamedCompilerPhase<?, ?, ?>> list) {
        String[] phasesToDump;
        if (list != null && (phasesToDump = commonCompilerArguments.getPhasesToDump()) != null && ArraysKt.contains(phasesToDump, "IrLowering")) {
            return TuplesKt.to(new PhaseSet.Enum(SetsKt.setOf(((NamedCompilerPhase) CollectionsKt.first(list)).getName())), new PhaseSet.Enum(SetsKt.setOf(((NamedCompilerPhase) CollectionsKt.last(list)).getName())));
        }
        PhaseSet.Empty empty = PhaseSet.Empty.INSTANCE;
        return TuplesKt.to(empty, empty);
    }

    private static final void list(PhaseConfig phaseConfig, List<? extends Pair<Integer, ? extends NamedCompilerPhase<?, ?, ?>>> list) {
        for (Pair<Integer, ? extends NamedCompilerPhase<?, ?, ?>> pair : list) {
            int iIntValue = ((Number) pair.component1()).intValue();
            NamedCompilerPhase<?, ?, ?> namedCompilerPhase = (NamedCompilerPhase) pair.component2();
            StringBuilder sb = new StringBuilder();
            sb.append(StringsKt.repeat(FirPlatformIncompatibilityDiagnosticRendererKt.INDENTATION_UNIT, iIntValue));
            sb.append(namedCompilerPhase.getName());
            if (!phaseConfig.isEnabled(namedCompilerPhase)) {
                sb.append(" (Disabled)");
            }
            if (phaseConfig.isVerbose(namedCompilerPhase)) {
                sb.append(" (Verbose)");
            }
            System.out.println((Object) sb.toString());
        }
    }

    public static final void listPhases(PhaseConfig phaseConfig, List<? extends NamedCompilerPhase<?, ?, ?>> list) {
        phaseConfig.getClass();
        list.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, CompilerPhase.getNamedSubphases$default((NamedCompilerPhase) it.next(), 0, 1, null));
        }
        list(phaseConfig, arrayList);
    }

    public static final void list(PhaseConfig phaseConfig, CompilerPhase<?, ?, ?> compilerPhase) {
        phaseConfig.getClass();
        compilerPhase.getClass();
        list(phaseConfig, (List<? extends Pair<Integer, ? extends NamedCompilerPhase<?, ?, ?>>>) CompilerPhase.getNamedSubphases$default(compilerPhase, 0, 1, null));
    }
}
