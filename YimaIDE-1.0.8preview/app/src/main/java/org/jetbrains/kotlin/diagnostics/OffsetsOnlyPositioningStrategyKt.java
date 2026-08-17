package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u0016\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\b"}, d2 = {"markElement", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "startOffset", Argument.Delimiters.none, "endOffset", "markRange", "markSingleElement", "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OffsetsOnlyPositioningStrategyKt {
    public static final List<TextRange> markElement(int i, int i2) {
        return markRange(i, i2);
    }

    public static final List<TextRange> markRange(int i, int i2) {
        return CollectionsKt.listOf(markSingleElement(i, i2));
    }

    public static final TextRange markSingleElement(int i, int i2) {
        return new TextRange(i, i2);
    }
}
