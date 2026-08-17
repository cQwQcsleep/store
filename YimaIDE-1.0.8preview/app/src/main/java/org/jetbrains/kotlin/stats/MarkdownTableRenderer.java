package org.jetbrains.kotlin.stats;

import java.util.Arrays;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.stats.MarkdownTableRenderer;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u0014\u001a\u00020\u0015*\u00060\u0016j\u0002`\u00172\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u000f\"\u00020\r¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u0015*\u00060\u0016j\u0002`\u0017J'\u0010\u001b\u001a\u00020\u0015*\u00060\u0016j\u0002`\u00172\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u000f\"\u00020\r¢\u0006\u0002\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/stats/MarkdownTableRenderer;", "", "columnsCount", "", "nameColumnWidth", "valueColumnWidth", "<init>", "(III)V", "getColumnsCount", "()I", "getNameColumnWidth", "getValueColumnWidth", "columnsFormat", "", "emptyColumns", "", "getEmptyColumns", "()[Ljava/lang/String;", "emptyColumns$delegate", "Lkotlin/Lazy;", "renderHeader", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "columns", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)V", "renderBreak", "renderLine", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class MarkdownTableRenderer {
    private final int columnsCount;
    private final String columnsFormat;

    /* JADX INFO: renamed from: emptyColumns$delegate, reason: from kotlin metadata */
    private final Lazy emptyColumns;
    private final int nameColumnWidth;
    private final int valueColumnWidth;

    public MarkdownTableRenderer(int i, int i2, int i3) {
        StringBuilder sb;
        String str;
        this.columnsCount = i;
        this.nameColumnWidth = i2;
        this.valueColumnWidth = i3;
        StringBuilder sb2 = new StringBuilder();
        for (int i4 = 0; i4 < i; i4++) {
            if (i4 == 0) {
                sb = new StringBuilder("| %-");
                sb.append(this.nameColumnWidth + 1);
                str = "s|";
            } else {
                sb = new StringBuilder("%");
                sb.append(this.valueColumnWidth + 1);
                str = "s |";
            }
            sb.append(str);
            sb2.append(sb.toString());
        }
        this.columnsFormat = sb2.toString();
        this.emptyColumns = LazyKt.lazy(new Function0() { // from class: xu9
            public final Object invoke() {
                return MarkdownTableRenderer.a(this.b);
            }
        });
    }

    public static String[] a(MarkdownTableRenderer markdownTableRenderer) {
        int i = markdownTableRenderer.columnsCount;
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = "";
        }
        return strArr;
    }

    private final String[] getEmptyColumns() {
        return (String[]) this.emptyColumns.getValue();
    }

    public final int getColumnsCount() {
        return this.columnsCount;
    }

    public final int getNameColumnWidth() {
        return this.nameColumnWidth;
    }

    public final int getValueColumnWidth() {
        return this.valueColumnWidth;
    }

    public final void renderBreak(StringBuilder sb) {
        sb.getClass();
        String[] emptyColumns = getEmptyColumns();
        renderLine(sb, (String[]) Arrays.copyOf(emptyColumns, emptyColumns.length));
    }

    public final void renderHeader(StringBuilder sb, String... strArr) {
        sb.getClass();
        strArr.getClass();
        if (strArr.length != this.columnsCount) {
            v1f.a("Columns count must be ", this.columnsCount);
            return;
        }
        renderLine(sb, (String[]) Arrays.copyOf(strArr, strArr.length));
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        String str = StringsKt.repeat("-", this.valueColumnWidth - 1) + ':';
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            listCreateListBuilder.add(i == 0 ? StringsKt.repeat("-", this.nameColumnWidth) : str);
            i++;
        }
        String[] strArr2 = (String[]) CollectionsKt.build(listCreateListBuilder).toArray(new String[0]);
        renderLine(sb, (String[]) Arrays.copyOf(strArr2, strArr2.length));
    }

    public final void renderLine(StringBuilder sb, String... strArr) {
        sb.getClass();
        strArr.getClass();
        if (strArr.length != this.columnsCount) {
            v1f.a("Columns count must be ", this.columnsCount);
            return;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = this.columnsFormat;
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length);
        sb.append(String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length)));
        sb.append('\n');
    }
}
