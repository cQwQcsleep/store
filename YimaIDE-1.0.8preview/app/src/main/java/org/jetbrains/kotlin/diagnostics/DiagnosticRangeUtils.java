package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticRangeUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0007b\u0002\b\u000bR\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticRangeUtils;", Argument.Delimiters.none, "<init>", "()V", "TEXT_RANGE_COMPARATOR", "Ljava/util/Comparator;", "Lcom/intellij/openapi/util/TextRange;", "Lkotlin/jvm/JvmField;", "firstRange", "ranges", Argument.Delimiters.none, "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticRangeUtils {
    public static final DiagnosticRangeUtils INSTANCE = new DiagnosticRangeUtils();
    public static final Comparator<TextRange> TEXT_RANGE_COMPARATOR = new Comparator() { // from class: bt3
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DiagnosticRangeUtils.a((TextRange) obj, (TextRange) obj2);
        }
    };

    private DiagnosticRangeUtils() {
    }

    public static int a(TextRange textRange, TextRange textRange2) {
        int endOffset;
        int endOffset2;
        textRange.getClass();
        textRange2.getClass();
        if (textRange.getStartOffset() != textRange2.getStartOffset()) {
            endOffset = textRange.getStartOffset();
            endOffset2 = textRange2.getStartOffset();
        } else {
            endOffset = textRange.getEndOffset();
            endOffset2 = textRange2.getEndOffset();
        }
        return endOffset - endOffset2;
    }

    @JvmStatic
    public static final TextRange firstRange(List<? extends TextRange> ranges) {
        ranges.getClass();
        return (TextRange) CollectionsKt.minWithOrThrow(ranges, TEXT_RANGE_COMPARATOR);
    }
}
