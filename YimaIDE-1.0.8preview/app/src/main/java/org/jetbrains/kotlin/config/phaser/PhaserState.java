package org.jetbrains.kotlin.config.phaser;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B;\u0012\u001e\b\u0002\u0010\u0002\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004j\u0002`\u00050\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0013\u001a\u00020\u0000R'\u0010\u0002\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004j\u0002`\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/config/phaser/PhaserState;", Argument.Delimiters.none, "alreadyDone", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/config/phaser/AnyNamedPhase;", "depth", Argument.Delimiters.none, "phaseCount", "<init>", "(Ljava/util/Set;II)V", "getAlreadyDone", "()Ljava/util/Set;", "getDepth", "()I", "setDepth", "(I)V", "getPhaseCount", "setPhaseCount", "copyOf", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PhaserState {
    private final Set<NamedCompilerPhase<?, ?, ?>> alreadyDone;
    private int depth;
    private int phaseCount;

    public /* synthetic */ PhaserState(Set set, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? new LinkedHashSet() : set, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2);
    }

    public final PhaserState copyOf() {
        return new PhaserState(CollectionsKt.toMutableSet(this.alreadyDone), this.depth, this.phaseCount);
    }

    public final Set<NamedCompilerPhase<?, ?, ?>> getAlreadyDone() {
        return this.alreadyDone;
    }

    public final int getDepth() {
        return this.depth;
    }

    public final int getPhaseCount() {
        return this.phaseCount;
    }

    public final void setDepth(int i) {
        this.depth = i;
    }

    public final void setPhaseCount(int i) {
        this.phaseCount = i;
    }

    public PhaserState(Set<NamedCompilerPhase<?, ?, ?>> set, int i, int i2) {
        set.getClass();
        this.alreadyDone = set;
        this.depth = i;
        this.phaseCount = i2;
    }

    public PhaserState() {
        this(null, 0, 0, 7, null);
    }
}
