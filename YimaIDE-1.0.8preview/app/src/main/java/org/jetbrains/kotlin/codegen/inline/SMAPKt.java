package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u0015\u0010\u0003\u001a\u00020\u0004*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"KOTLIN_STRATA_NAME", Argument.Delimiters.none, "KOTLIN_DEBUG_STRATA_NAME", "toRange", "Lkotlin/ranges/IntRange;", "Lorg/jetbrains/kotlin/codegen/inline/RangeMapping;", "getToRange", "(Lorg/jetbrains/kotlin/codegen/inline/RangeMapping;)Lkotlin/ranges/IntRange;", "org.jetbrains.kotlin:backend.common.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SMAPKt {
    public static final String KOTLIN_DEBUG_STRATA_NAME = "KotlinDebug";
    public static final String KOTLIN_STRATA_NAME = "Kotlin";

    public static final IntRange getToRange(RangeMapping rangeMapping) {
        rangeMapping.getClass();
        return RangesKt.until(rangeMapping.getDest(), rangeMapping.getDest() + rangeMapping.getRange());
    }
}
