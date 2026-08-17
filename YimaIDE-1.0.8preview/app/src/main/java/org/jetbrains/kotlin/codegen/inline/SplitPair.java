package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.Interval;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SplitPair;", "T", "Lorg/jetbrains/kotlin/codegen/inline/Interval;", Argument.Delimiters.none, "patchedPart", "newPart", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/Interval;Lorg/jetbrains/kotlin/codegen/inline/Interval;)V", "getPatchedPart", "()Lorg/jetbrains/kotlin/codegen/inline/Interval;", "Lorg/jetbrains/kotlin/codegen/inline/Interval;", "getNewPart", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SplitPair<T extends Interval> {
    private final T newPart;
    private final T patchedPart;

    public SplitPair(T t, T t2) {
        t.getClass();
        t2.getClass();
        this.patchedPart = t;
        this.newPart = t2;
    }

    public final T getNewPart() {
        return this.newPart;
    }

    public final T getPatchedPart() {
        return this.patchedPart;
    }
}
