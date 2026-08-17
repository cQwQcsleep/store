package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.Interval;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0002J\u001e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0015\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\u0002H&¢\u0006\u0002\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SplittableInterval;", "T", "Lorg/jetbrains/kotlin/codegen/inline/Interval;", "split", "Lorg/jetbrains/kotlin/codegen/inline/SplitPair;", "splitBy", "keepStart", Argument.Delimiters.none, "copyWithNewBounds", "newBounds", "(Lorg/jetbrains/kotlin/codegen/inline/Interval;)Lorg/jetbrains/kotlin/codegen/inline/Interval;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface SplittableInterval<T extends Interval> extends Interval {
    T copyWithNewBounds(Interval newBounds);

    SplitPair<T> split(Interval splitBy, boolean keepStart);
}
