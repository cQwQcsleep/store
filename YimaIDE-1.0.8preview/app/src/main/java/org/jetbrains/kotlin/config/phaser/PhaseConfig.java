package org.jetbrains.kotlin.config.phaser;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001Bo\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u001e\u0010\u001b\u001a\u00020\r2\u0016\u0010\u001c\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001dj\u0002`\u001eJ\u001e\u0010\u001f\u001a\u00020\r2\u0016\u0010\u001c\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001dj\u0002`\u001eJ\u001e\u0010 \u001a\u00020\r2\u0016\u0010\u001c\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001dj\u0002`\u001eJ\u001e\u0010!\u001a\u00020\r2\u0016\u0010\u001c\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001dj\u0002`\u001eJ\u001e\u0010\"\u001a\u00020\r2\u0016\u0010\u001c\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001dj\u0002`\u001eJ\u001e\u0010#\u001a\u00020\r2\u0016\u0010\u001c\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001dj\u0002`\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", Argument.Delimiters.none, "disabled", "Lorg/jetbrains/kotlin/config/phaser/PhaseSet;", "verbose", "toDumpStateBefore", "toDumpStateAfter", "toValidateStateBefore", "toValidateStateAfter", "dumpToDirectory", Argument.Delimiters.none, "dumpOnlyFqName", "needProfiling", Argument.Delimiters.none, "checkConditions", "<init>", "(Lorg/jetbrains/kotlin/config/phaser/PhaseSet;Lorg/jetbrains/kotlin/config/phaser/PhaseSet;Lorg/jetbrains/kotlin/config/phaser/PhaseSet;Lorg/jetbrains/kotlin/config/phaser/PhaseSet;Lorg/jetbrains/kotlin/config/phaser/PhaseSet;Lorg/jetbrains/kotlin/config/phaser/PhaseSet;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getVerbose", "()Lorg/jetbrains/kotlin/config/phaser/PhaseSet;", "getToDumpStateBefore", "getToDumpStateAfter", "getDumpToDirectory", "()Ljava/lang/String;", "getDumpOnlyFqName", "getNeedProfiling", "()Z", "getCheckConditions", "isEnabled", "phase", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/config/phaser/AnyNamedPhase;", "isVerbose", "shouldDumpStateBefore", "shouldDumpStateAfter", "shouldValidateStateBefore", "shouldValidateStateAfter", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PhaseConfig {
    private final boolean checkConditions;
    private final PhaseSet disabled;
    private final String dumpOnlyFqName;
    private final String dumpToDirectory;
    private final boolean needProfiling;
    private final PhaseSet toDumpStateAfter;
    private final PhaseSet toDumpStateBefore;
    private final PhaseSet toValidateStateAfter;
    private final PhaseSet toValidateStateBefore;
    private final PhaseSet verbose;

    public /* synthetic */ PhaseConfig(PhaseSet phaseSet, PhaseSet phaseSet2, PhaseSet phaseSet3, PhaseSet phaseSet4, PhaseSet phaseSet5, PhaseSet phaseSet6, String str, String str2, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? PhaseSet.Empty.INSTANCE : phaseSet, (i & 2) != 0 ? PhaseSet.Empty.INSTANCE : phaseSet2, (i & 4) != 0 ? PhaseSet.Empty.INSTANCE : phaseSet3, (i & 8) != 0 ? PhaseSet.Empty.INSTANCE : phaseSet4, (i & 16) != 0 ? PhaseSet.Empty.INSTANCE : phaseSet5, (i & 32) != 0 ? PhaseSet.Empty.INSTANCE : phaseSet6, (i & 64) != 0 ? null : str, (i & 128) != 0 ? null : str2, (i & 256) != 0 ? false : z, (i & 512) != 0 ? false : z2);
    }

    public final boolean getCheckConditions() {
        return this.checkConditions;
    }

    public final String getDumpOnlyFqName() {
        return this.dumpOnlyFqName;
    }

    public final String getDumpToDirectory() {
        return this.dumpToDirectory;
    }

    public final boolean getNeedProfiling() {
        return this.needProfiling;
    }

    public final PhaseSet getToDumpStateAfter() {
        return this.toDumpStateAfter;
    }

    public final PhaseSet getToDumpStateBefore() {
        return this.toDumpStateBefore;
    }

    public final PhaseSet getVerbose() {
        return this.verbose;
    }

    public final boolean isEnabled(NamedCompilerPhase<?, ?, ?> phase) {
        phase.getClass();
        return !this.disabled.contains(phase);
    }

    public final boolean isVerbose(NamedCompilerPhase<?, ?, ?> phase) {
        phase.getClass();
        return this.verbose.contains(phase);
    }

    public final boolean shouldDumpStateAfter(NamedCompilerPhase<?, ?, ?> phase) {
        phase.getClass();
        return this.toDumpStateAfter.contains(phase);
    }

    public final boolean shouldDumpStateBefore(NamedCompilerPhase<?, ?, ?> phase) {
        phase.getClass();
        return this.toDumpStateBefore.contains(phase);
    }

    public final boolean shouldValidateStateAfter(NamedCompilerPhase<?, ?, ?> phase) {
        phase.getClass();
        return this.toValidateStateAfter.contains(phase);
    }

    public final boolean shouldValidateStateBefore(NamedCompilerPhase<?, ?, ?> phase) {
        phase.getClass();
        return this.toValidateStateBefore.contains(phase);
    }

    public PhaseConfig(PhaseSet phaseSet, PhaseSet phaseSet2, PhaseSet phaseSet3, PhaseSet phaseSet4, PhaseSet phaseSet5, PhaseSet phaseSet6, String str, String str2, boolean z, boolean z2) {
        phaseSet.getClass();
        phaseSet2.getClass();
        phaseSet3.getClass();
        phaseSet4.getClass();
        phaseSet5.getClass();
        phaseSet6.getClass();
        this.disabled = phaseSet;
        this.verbose = phaseSet2;
        this.toDumpStateBefore = phaseSet3;
        this.toDumpStateAfter = phaseSet4;
        this.toValidateStateBefore = phaseSet5;
        this.toValidateStateAfter = phaseSet6;
        this.dumpToDirectory = str;
        this.dumpOnlyFqName = str2;
        this.needProfiling = z;
        this.checkConditions = z2;
    }

    public PhaseConfig() {
        this(null, null, null, null, null, null, null, null, false, false, 1023, null);
    }
}
