package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.ParagraphInfo;
import androidx.compose.ui.text.font.DelegatingFontLoaderForDeprecatedUsage_androidKt;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.text.platform.AndroidMultiParagraphDraw_androidKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0007\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB-\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\n\u0010\u000eB-\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\n\u0010\u0011B[\b\u0017\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0004\b\n\u0010\u001eB[\b\u0017\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\n\u0010!B[\b\u0017\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\n\u0010\"B[\b\u0016\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010#J9\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\b\b\u0002\u0010F\u001a\u00020G2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010KH\u0007¢\u0006\u0004\bL\u0010MJM\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\b\b\u0002\u0010F\u001a\u00020G2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010K2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010O2\b\b\u0002\u0010P\u001a\u00020Q¢\u0006\u0004\bR\u0010SJU\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010T\u001a\u00020U2\b\b\u0002\u0010V\u001a\u00020\u00102\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010K2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010O2\b\b\u0002\u0010P\u001a\u00020Q¢\u0006\u0004\bW\u0010XJ\u0016\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00072\u0006\u0010\\\u001a\u00020\u0007J\u000e\u0010]\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u0010J\u0015\u0010_\u001a\u00020\u00072\u0006\u0010`\u001a\u00020a¢\u0006\u0004\bb\u0010cJ%\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020<2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020j¢\u0006\u0004\bk\u0010lJ\u000e\u0010m\u001a\u00020<2\u0006\u0010n\u001a\u00020\u0007J'\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020e2\u0006\u0010r\u001a\u00020p2\b\b\u0001\u0010s\u001a\u00020\u0007¢\u0006\u0004\bt\u0010uJ\u0016\u0010v\u001a\u00020\u00102\u0006\u0010n\u001a\u00020\u00072\u0006\u0010w\u001a\u00020\rJ\u000e\u0010x\u001a\u00020y2\u0006\u0010n\u001a\u00020\u0007J\u000e\u0010z\u001a\u00020y2\u0006\u0010n\u001a\u00020\u0007J\u0015\u0010{\u001a\u00020e2\u0006\u0010n\u001a\u00020\u0007¢\u0006\u0004\b|\u0010}J\u000e\u0010~\u001a\u00020<2\u0006\u0010n\u001a\u00020\u0007J\u000e\u0010\u007f\u001a\u00020\u00072\u0006\u0010n\u001a\u00020\u0007J\u0010\u0010\u0080\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0082\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0083\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0084\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0085\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0086\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0087\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0088\u0001\u001a\u00020\u00072\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u001b\u0010\u0089\u0001\u001a\u00020\u00072\u0007\u0010\u0081\u0001\u001a\u00020\u00072\t\b\u0002\u0010\u008a\u0001\u001a\u00020\rJ\u0010\u0010\u008b\u0001\u001a\u00020\r2\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0011\u0010\u008c\u0001\u001a\u00020C2\u0006\u0010n\u001a\u00020\u0007H\u0002J\u0011\u0010\u008d\u0001\u001a\u00020C2\u0006\u0010n\u001a\u00020\u0007H\u0002J\u0012\u0010\u008e\u0001\u001a\u00020C2\u0007\u0010\u0081\u0001\u001a\u00020\u0007H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0011\u0010-\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b.\u0010,R\u0011\u0010/\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b2\u0010,R\u0011\u00103\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b4\u0010,R\u0011\u00105\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b6\u0010,R\u0011\u00107\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b8\u0010,R\u0011\u00109\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b:\u0010'R\u0019\u0010;\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010<0\u0017¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010>¨\u0006\u008f\u0001"}, d2 = {"Landroidx/compose/ui/text/MultiParagraph;", "", "intrinsics", "Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "maxLines", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "<init>", "(Landroidx/compose/ui/text/MultiParagraphIntrinsics;JIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "ellipsis", "", "(Landroidx/compose/ui/text/MultiParagraphIntrinsics;JIZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "width", "", "(Landroidx/compose/ui/text/MultiParagraphIntrinsics;IZF)V", "annotatedString", "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "density", "Landroidx/compose/ui/unit/Density;", "resourceLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;IZFLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/Font$ResourceLoader;)V", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;FLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;IZ)V", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;JLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;IZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;JLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getIntrinsics", "()Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "getMaxLines", "()I", "getAnnotatedString", "()Landroidx/compose/ui/text/AnnotatedString;", "minIntrinsicWidth", "getMinIntrinsicWidth", "()F", "maxIntrinsicWidth", "getMaxIntrinsicWidth", "didExceedMaxLines", "getDidExceedMaxLines", "()Z", "getWidth", "height", "getHeight", "firstBaseline", "getFirstBaseline", "lastBaseline", "getLastBaseline", "lineCount", "getLineCount", "placeholderRects", "Landroidx/compose/ui/geometry/Rect;", "getPlaceholderRects", "()Ljava/util/List;", "paragraphInfoList", "Landroidx/compose/ui/text/ParagraphInfo;", "getParagraphInfoList$ui_text", "paint", "", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "color", "Landroidx/compose/ui/graphics/Color;", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "decoration", "Landroidx/compose/ui/text/style/TextDecoration;", "paint-RPmYEkk", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;)V", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "paint-LG529CI", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "paint-hn5TExg", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "getPathForRange", "Landroidx/compose/ui/graphics/Path;", "start", "end", "getLineForVerticalPosition", "vertical", "getOffsetForPosition", "position", "Landroidx/compose/ui/geometry/Offset;", "getOffsetForPosition-k-4lQ0M", "(J)I", "getRangeForRect", "Landroidx/compose/ui/text/TextRange;", "rect", "granularity", "Landroidx/compose/ui/text/TextGranularity;", "inclusionStrategy", "Landroidx/compose/ui/text/TextInclusionStrategy;", "getRangeForRect-8-6BmAI", "(Landroidx/compose/ui/geometry/Rect;ILandroidx/compose/ui/text/TextInclusionStrategy;)J", "getBoundingBox", "offset", "fillBoundingBoxes", "", "range", "array", "arrayStart", "fillBoundingBoxes-8ffj60Q", "(J[FI)[F", "getHorizontalPosition", "usePrimaryDirection", "getParagraphDirection", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getBidiRunDirection", "getWordBoundary", "getWordBoundary--jx7JFs", "(I)J", "getCursorRect", "getLineForOffset", "getLineLeft", "lineIndex", "getLineRight", "getLineTop", "getLineBaseline", "getLineBottom", "getLineHeight", "getLineWidth", "getLineStart", "getLineEnd", "visibleEnd", "isLineEllipsized", "requireIndexInRange", "requireIndexInRangeInclusiveEnd", "requireLineIndexInRange", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class MultiParagraph {
    public static final int $stable = 8;
    private final boolean didExceedMaxLines;
    private final float height;
    private final MultiParagraphIntrinsics intrinsics;
    private final int lineCount;
    private final int maxLines;
    private final List<ParagraphInfo> paragraphInfoList;
    private final List<Rect> placeholderRects;
    private final float width;

    private MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, int i2) {
        this.intrinsics = multiParagraphIntrinsics;
        this.maxLines = i;
        boolean z = true;
        if (!(Constraints.m5977getMinWidthimpl(j) == 0 && Constraints.m5976getMinHeightimpl(j) == 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
        }
        ArrayList arrayList = new ArrayList();
        List<ParagraphIntrinsicInfo> infoList$ui_text = multiParagraphIntrinsics.getInfoList$ui_text();
        int size = infoList$ui_text.size();
        int i3 = 0;
        float f = 0.0f;
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                z = false;
                break;
            }
            ParagraphIntrinsicInfo paragraphIntrinsicInfo = infoList$ui_text.get(i4);
            Paragraph paragraphM5362ParagraphczeNHc = ParagraphKt.m5362ParagraphczeNHc(paragraphIntrinsicInfo.getIntrinsics(), ConstraintsKt.Constraints$default(0, Constraints.m5975getMaxWidthimpl(j), 0, Constraints.m5970getHasBoundedHeightimpl(j) ? RangesKt.coerceAtLeast(Constraints.m5974getMaxHeightimpl(j) - ParagraphKt.ceilToInt(f), 0) : Constraints.m5974getMaxHeightimpl(j), 5, null), this.maxLines - i3, i2);
            float height = f + paragraphM5362ParagraphczeNHc.getHeight();
            int lineCount = i3 + paragraphM5362ParagraphczeNHc.getLineCount();
            arrayList.add(new ParagraphInfo(paragraphM5362ParagraphczeNHc, paragraphIntrinsicInfo.getStartIndex(), paragraphIntrinsicInfo.getEndIndex(), i3, lineCount, f, height));
            if (paragraphM5362ParagraphczeNHc.getDidExceedMaxLines() || (lineCount == this.maxLines && i4 != CollectionsKt.getLastIndex(this.intrinsics.getInfoList$ui_text()))) {
                i3 = lineCount;
                f = height;
                break;
            } else {
                i4++;
                i3 = lineCount;
                f = height;
            }
        }
        this.height = f;
        this.lineCount = i3;
        this.didExceedMaxLines = z;
        this.paragraphInfoList = arrayList;
        this.width = Constraints.m5975getMaxWidthimpl(j);
        List<Rect> arrayList2 = new ArrayList<>(arrayList.size());
        int size2 = arrayList.size();
        for (int i5 = 0; i5 < size2; i5++) {
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i5);
            List<Rect> placeholderRects = paragraphInfo.getParagraph().getPlaceholderRects();
            ArrayList arrayList3 = new ArrayList(placeholderRects.size());
            int size3 = placeholderRects.size();
            for (int i6 = 0; i6 < size3; i6++) {
                Rect rect = placeholderRects.get(i6);
                arrayList3.add(rect != null ? paragraphInfo.toGlobal(rect) : null);
            }
            CollectionsKt.addAll(arrayList2, arrayList3);
        }
        if (arrayList2.size() < this.intrinsics.getPlaceholders().size()) {
            int size4 = this.intrinsics.getPlaceholders().size() - arrayList2.size();
            ArrayList arrayList4 = new ArrayList(size4);
            for (int i7 = 0; i7 < size4; i7++) {
                arrayList4.add(null);
            }
            arrayList2 = CollectionsKt.plus(arrayList2, arrayList4);
        }
        this.placeholderRects = arrayList2;
    }

    public static Unit a(Path path, int i, int i2, ParagraphInfo paragraphInfo) {
        Path.m3418addPathUv8p0NA$default(path, paragraphInfo.toGlobal(paragraphInfo.getParagraph().getPathForRange(paragraphInfo.toLocalIndex(i), paragraphInfo.toLocalIndex(i2))), 0L, 2, null);
        return Unit.INSTANCE;
    }

    public static Unit b(long j, float[] fArr, Ref.IntRef intRef, Ref.FloatRef floatRef, ParagraphInfo paragraphInfo) {
        long jTextRange = TextRangeKt.TextRange(paragraphInfo.toLocalIndex(paragraphInfo.getStartIndex() > TextRange.m5477getMinimpl(j) ? paragraphInfo.getStartIndex() : TextRange.m5477getMinimpl(j)), paragraphInfo.toLocalIndex(paragraphInfo.getEndIndex() < TextRange.m5476getMaximpl(j) ? paragraphInfo.getEndIndex() : TextRange.m5476getMaximpl(j)));
        paragraphInfo.getParagraph().mo5293fillBoundingBoxes8ffj60Q(jTextRange, fArr, intRef.element);
        int iM5475getLengthimpl = intRef.element + (TextRange.m5475getLengthimpl(jTextRange) * 4);
        for (int i = intRef.element; i < iM5475getLengthimpl; i += 4) {
            int i2 = i + 1;
            float f = fArr[i2];
            float f2 = floatRef.element;
            fArr[i2] = f + f2;
            int i3 = i + 3;
            fArr[i3] = fArr[i3] + f2;
        }
        intRef.element = iM5475getLengthimpl;
        floatRef.element += paragraphInfo.getParagraph().getHeight();
        return Unit.INSTANCE;
    }

    private final AnnotatedString getAnnotatedString() {
        return this.intrinsics.getAnnotatedString();
    }

    public static /* synthetic */ int getLineEnd$default(MultiParagraph multiParagraph, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return multiParagraph.getLineEnd(i, z);
    }

    /* JADX INFO: renamed from: paint-RPmYEkk$default, reason: not valid java name */
    public static /* synthetic */ void m5337paintRPmYEkk$default(MultiParagraph multiParagraph, Canvas canvas, long j, Shadow shadow, TextDecoration textDecoration, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        multiParagraph.m5344paintRPmYEkk(canvas, j, (i & 4) != 0 ? null : shadow, (i & 8) != 0 ? null : textDecoration);
    }

    /* JADX INFO: renamed from: paint-hn5TExg$default, reason: not valid java name */
    public static /* synthetic */ void m5338painthn5TExg$default(MultiParagraph multiParagraph, Canvas canvas, Brush brush, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            f = Float.NaN;
        }
        multiParagraph.m5345painthn5TExg(canvas, brush, f, (i2 & 8) != 0 ? null : shadow, (i2 & 16) != 0 ? null : textDecoration, (i2 & 32) != 0 ? null : drawStyle, (i2 & 64) != 0 ? DrawScope.INSTANCE.m3710getDefaultBlendMode0nO6VwU() : i);
    }

    private final void requireIndexInRange(int offset) {
        boolean z = false;
        if (offset >= 0 && offset < getAnnotatedString().getText().length()) {
            z = true;
        }
        if (z) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("offset(" + offset + ") is out of bounds [0, " + getAnnotatedString().length() + ')');
    }

    private final void requireIndexInRangeInclusiveEnd(int offset) {
        boolean z = false;
        if (offset >= 0 && offset <= getAnnotatedString().getText().length()) {
            z = true;
        }
        if (z) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("offset(" + offset + ") is out of bounds [0, " + getAnnotatedString().length() + ']');
    }

    private final void requireLineIndexInRange(int lineIndex) {
        boolean z = false;
        if (lineIndex >= 0 && lineIndex < this.lineCount) {
            z = true;
        }
        if (z) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("lineIndex(" + lineIndex + ") is out of bounds [0, " + this.lineCount + ')');
    }

    /* JADX INFO: renamed from: fillBoundingBoxes-8ffj60Q, reason: not valid java name */
    public final float[] m5339fillBoundingBoxes8ffj60Q(final long range, final float[] array, int arrayStart) {
        requireIndexInRange(TextRange.m5477getMinimpl(range));
        requireIndexInRangeInclusiveEnd(TextRange.m5476getMaximpl(range));
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = arrayStart;
        final Ref.FloatRef floatRef = new Ref.FloatRef();
        MultiParagraphKt.m5346findParagraphsByRangeSbBc2M(this.paragraphInfoList, range, new Function1() { // from class: g8a
            public final Object invoke(Object obj) {
                return MultiParagraph.b(range, array, intRef, floatRef, (ParagraphInfo) obj);
            }
        });
        return array;
    }

    public final ResolvedTextDirection getBidiRunDirection(int offset) {
        requireIndexInRangeInclusiveEnd(offset);
        int length = getAnnotatedString().length();
        List<ParagraphInfo> list = this.paragraphInfoList;
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset == length ? CollectionsKt.getLastIndex(list) : MultiParagraphKt.findParagraphByIndex(list, offset));
        return paragraphInfo.getParagraph().getBidiRunDirection(paragraphInfo.toLocalIndex(offset));
    }

    public final Rect getBoundingBox(int offset) {
        requireIndexInRange(offset);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset));
        return paragraphInfo.toGlobal(paragraphInfo.getParagraph().getBoundingBox(paragraphInfo.toLocalIndex(offset)));
    }

    public final Rect getCursorRect(int offset) {
        requireIndexInRangeInclusiveEnd(offset);
        int length = getAnnotatedString().length();
        List<ParagraphInfo> list = this.paragraphInfoList;
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset == length ? CollectionsKt.getLastIndex(list) : MultiParagraphKt.findParagraphByIndex(list, offset));
        return paragraphInfo.toGlobal(paragraphInfo.getParagraph().getCursorRect(paragraphInfo.toLocalIndex(offset)));
    }

    public final boolean getDidExceedMaxLines() {
        return this.didExceedMaxLines;
    }

    public final float getFirstBaseline() {
        if (this.paragraphInfoList.isEmpty()) {
            return 0.0f;
        }
        return this.paragraphInfoList.get(0).getParagraph().getFirstBaseline();
    }

    public final float getHeight() {
        return this.height;
    }

    public final float getHorizontalPosition(int offset, boolean usePrimaryDirection) {
        requireIndexInRangeInclusiveEnd(offset);
        int length = getAnnotatedString().length();
        List<ParagraphInfo> list = this.paragraphInfoList;
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset == length ? CollectionsKt.getLastIndex(list) : MultiParagraphKt.findParagraphByIndex(list, offset));
        return paragraphInfo.getParagraph().getHorizontalPosition(paragraphInfo.toLocalIndex(offset), usePrimaryDirection);
    }

    public final MultiParagraphIntrinsics getIntrinsics() {
        return this.intrinsics;
    }

    public final float getLastBaseline() {
        if (this.paragraphInfoList.isEmpty()) {
            return 0.0f;
        }
        ParagraphInfo paragraphInfo = (ParagraphInfo) CollectionsKt.last(this.paragraphInfoList);
        return paragraphInfo.toGlobalYPosition(paragraphInfo.getParagraph().getLastBaseline());
    }

    public final float getLineBaseline(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.toGlobalYPosition(paragraphInfo.getParagraph().getLineBaseline(paragraphInfo.toLocalLineIndex(lineIndex)));
    }

    public final float getLineBottom(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.toGlobalYPosition(paragraphInfo.getParagraph().getLineBottom(paragraphInfo.toLocalLineIndex(lineIndex)));
    }

    public final int getLineCount() {
        return this.lineCount;
    }

    public final int getLineEnd(int lineIndex, boolean visibleEnd) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.toGlobalIndex(paragraphInfo.getParagraph().getLineEnd(paragraphInfo.toLocalLineIndex(lineIndex), visibleEnd));
    }

    public final int getLineForOffset(int offset) {
        int iFindParagraphByIndex;
        if (offset >= getAnnotatedString().length()) {
            iFindParagraphByIndex = CollectionsKt.getLastIndex(this.paragraphInfoList);
        } else {
            iFindParagraphByIndex = offset < 0 ? 0 : MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset);
        }
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(iFindParagraphByIndex);
        return paragraphInfo.toGlobalLineIndex(paragraphInfo.getParagraph().getLineForOffset(paragraphInfo.toLocalIndex(offset)));
    }

    public final int getLineForVerticalPosition(float vertical) {
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByY(this.paragraphInfoList, vertical));
        return paragraphInfo.getLength() == 0 ? paragraphInfo.getStartLineIndex() : paragraphInfo.toGlobalLineIndex(paragraphInfo.getParagraph().getLineForVerticalPosition(paragraphInfo.toLocalYPosition(vertical)));
    }

    public final float getLineHeight(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.getParagraph().getLineHeight(paragraphInfo.toLocalLineIndex(lineIndex));
    }

    public final float getLineLeft(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.getParagraph().getLineLeft(paragraphInfo.toLocalLineIndex(lineIndex));
    }

    public final float getLineRight(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.getParagraph().getLineRight(paragraphInfo.toLocalLineIndex(lineIndex));
    }

    public final int getLineStart(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.toGlobalIndex(paragraphInfo.getParagraph().getLineStart(paragraphInfo.toLocalLineIndex(lineIndex)));
    }

    public final float getLineTop(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.toGlobalYPosition(paragraphInfo.getParagraph().getLineTop(paragraphInfo.toLocalLineIndex(lineIndex)));
    }

    public final float getLineWidth(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.getParagraph().getLineWidth(paragraphInfo.toLocalLineIndex(lineIndex));
    }

    public final float getMaxIntrinsicWidth() {
        return this.intrinsics.getMaxIntrinsicWidth();
    }

    public final int getMaxLines() {
        return this.maxLines;
    }

    public final float getMinIntrinsicWidth() {
        return this.intrinsics.getMinIntrinsicWidth();
    }

    /* JADX INFO: renamed from: getOffsetForPosition-k-4lQ0M, reason: not valid java name */
    public final int m5340getOffsetForPositionk4lQ0M(long position) {
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByY(this.paragraphInfoList, Float.intBitsToFloat((int) (4294967295L & position))));
        return paragraphInfo.getLength() == 0 ? paragraphInfo.getStartIndex() : paragraphInfo.toGlobalIndex(paragraphInfo.getParagraph().mo5295getOffsetForPositionk4lQ0M(paragraphInfo.m5355toLocalMKHz9U(position)));
    }

    public final ResolvedTextDirection getParagraphDirection(int offset) {
        requireIndexInRangeInclusiveEnd(offset);
        int length = getAnnotatedString().length();
        List<ParagraphInfo> list = this.paragraphInfoList;
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset == length ? CollectionsKt.getLastIndex(list) : MultiParagraphKt.findParagraphByIndex(list, offset));
        return paragraphInfo.getParagraph().getParagraphDirection(paragraphInfo.toLocalIndex(offset));
    }

    public final List<ParagraphInfo> getParagraphInfoList$ui_text() {
        return this.paragraphInfoList;
    }

    public final Path getPathForRange(final int start, final int end) {
        if (!(start >= 0 && start <= end && end <= getAnnotatedString().getText().length())) {
            InlineClassHelperKt.throwIllegalArgumentException("Start(" + start + ") or End(" + end + ") is out of range [0.." + getAnnotatedString().getText().length() + "), or start > end!");
        }
        if (start == end) {
            return AndroidPath_androidKt.Path();
        }
        final Path Path = AndroidPath_androidKt.Path();
        MultiParagraphKt.m5346findParagraphsByRangeSbBc2M(this.paragraphInfoList, TextRangeKt.TextRange(start, end), new Function1() { // from class: h8a
            public final Object invoke(Object obj) {
                return MultiParagraph.a(Path, start, end, (ParagraphInfo) obj);
            }
        });
        return Path;
    }

    public final List<Rect> getPlaceholderRects() {
        return this.placeholderRects;
    }

    /* JADX INFO: renamed from: getRangeForRect-8-6BmAI, reason: not valid java name */
    public final long m5341getRangeForRect86BmAI(Rect rect, int granularity, TextInclusionStrategy inclusionStrategy) {
        TextRange.Companion companion;
        TextRange.Companion companion2;
        int iFindParagraphByY = MultiParagraphKt.findParagraphByY(this.paragraphInfoList, rect.getTop());
        if (this.paragraphInfoList.get(iFindParagraphByY).getBottom() >= rect.getBottom() || iFindParagraphByY == CollectionsKt.getLastIndex(this.paragraphInfoList)) {
            ParagraphInfo paragraphInfo = this.paragraphInfoList.get(iFindParagraphByY);
            return ParagraphInfo.m5353toGlobalxdX6G0$default(paragraphInfo, paragraphInfo.getParagraph().mo5297getRangeForRect86BmAI(paragraphInfo.toLocal(rect), granularity, inclusionStrategy), false, 1, null);
        }
        int iFindParagraphByY2 = MultiParagraphKt.findParagraphByY(this.paragraphInfoList, rect.getBottom());
        long jM5484getZerod9O1mEE = TextRange.INSTANCE.m5484getZerod9O1mEE();
        while (true) {
            companion = TextRange.INSTANCE;
            if (!TextRange.m5472equalsimpl0(jM5484getZerod9O1mEE, companion.m5484getZerod9O1mEE()) || iFindParagraphByY > iFindParagraphByY2) {
                break;
            }
            ParagraphInfo paragraphInfo2 = this.paragraphInfoList.get(iFindParagraphByY);
            jM5484getZerod9O1mEE = ParagraphInfo.m5353toGlobalxdX6G0$default(paragraphInfo2, paragraphInfo2.getParagraph().mo5297getRangeForRect86BmAI(paragraphInfo2.toLocal(rect), granularity, inclusionStrategy), false, 1, null);
            iFindParagraphByY++;
        }
        if (TextRange.m5472equalsimpl0(jM5484getZerod9O1mEE, companion.m5484getZerod9O1mEE())) {
            return companion.m5484getZerod9O1mEE();
        }
        long jM5484getZerod9O1mEE2 = companion.m5484getZerod9O1mEE();
        while (true) {
            companion2 = TextRange.INSTANCE;
            if (!TextRange.m5472equalsimpl0(jM5484getZerod9O1mEE2, companion2.m5484getZerod9O1mEE()) || iFindParagraphByY > iFindParagraphByY2) {
                break;
            }
            ParagraphInfo paragraphInfo3 = this.paragraphInfoList.get(iFindParagraphByY2);
            jM5484getZerod9O1mEE2 = ParagraphInfo.m5353toGlobalxdX6G0$default(paragraphInfo3, paragraphInfo3.getParagraph().mo5297getRangeForRect86BmAI(paragraphInfo3.toLocal(rect), granularity, inclusionStrategy), false, 1, null);
            iFindParagraphByY2--;
        }
        return TextRange.m5472equalsimpl0(jM5484getZerod9O1mEE2, companion2.m5484getZerod9O1mEE()) ? jM5484getZerod9O1mEE : TextRangeKt.TextRange(TextRange.m5479getStartimpl(jM5484getZerod9O1mEE), TextRange.m5474getEndimpl(jM5484getZerod9O1mEE2));
    }

    public final float getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: getWordBoundary--jx7JFs, reason: not valid java name */
    public final long m5342getWordBoundaryjx7JFs(int offset) {
        requireIndexInRangeInclusiveEnd(offset);
        int length = getAnnotatedString().length();
        List<ParagraphInfo> list = this.paragraphInfoList;
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset == length ? CollectionsKt.getLastIndex(list) : MultiParagraphKt.findParagraphByIndex(list, offset));
        return paragraphInfo.m5354toGlobalxdX6G0(paragraphInfo.getParagraph().mo5298getWordBoundaryjx7JFs(paragraphInfo.toLocalIndex(offset)), false);
    }

    public final boolean isLineEllipsized(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        return this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex)).getParagraph().isLineEllipsized(lineIndex);
    }

    /* JADX INFO: renamed from: paint-LG529CI, reason: not valid java name */
    public final void m5343paintLG529CI(Canvas canvas, long color, Shadow shadow, TextDecoration decoration, DrawStyle drawStyle, int blendMode) {
        canvas.save();
        List<ParagraphInfo> list = this.paragraphInfoList;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ParagraphInfo paragraphInfo = list.get(i);
            paragraphInfo.getParagraph().mo5299paintLG529CI(canvas, color, shadow, decoration, drawStyle, blendMode);
            canvas.translate(0.0f, paragraphInfo.getParagraph().getHeight());
        }
        canvas.restore();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the new paint function that takes canvas as the only required parameter.")
    /* JADX INFO: renamed from: paint-RPmYEkk, reason: not valid java name */
    public final /* synthetic */ void m5344paintRPmYEkk(Canvas canvas, long color, Shadow shadow, TextDecoration decoration) {
        canvas.save();
        List<ParagraphInfo> list = this.paragraphInfoList;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ParagraphInfo paragraphInfo = list.get(i);
            Paragraph.m5347paintLG529CI$default(paragraphInfo.getParagraph(), canvas, color, shadow, decoration, null, 0, 48, null);
            canvas.translate(0.0f, paragraphInfo.getParagraph().getHeight());
        }
        canvas.restore();
    }

    /* JADX INFO: renamed from: paint-hn5TExg, reason: not valid java name */
    public final void m5345painthn5TExg(Canvas canvas, Brush brush, float alpha, Shadow shadow, TextDecoration decoration, DrawStyle drawStyle, int blendMode) {
        AndroidMultiParagraphDraw_androidKt.m5726drawMultiParagraph7AXcY_I(this, canvas, brush, alpha, shadow, decoration, drawStyle, blendMode);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Constructor with `ellipsis: Boolean` is deprecated, pass TextOverflow instead")
    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List list, int i, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, j, density, resolver, (List<AnnotatedString.Range<Placeholder>>) list, i, z);
    }

    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(multiParagraphIntrinsics, j, i, i2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Constructor with `ellipsis: Boolean` is deprecated, pass TextOverflow instead")
    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(multiParagraphIntrinsics, j, i, z);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List list, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, j, density, resolver, (List<AnnotatedString.Range<Placeholder>>) list, i, i2);
    }

    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(multiParagraphIntrinsics, j, (i3 & 4) != 0 ? Integer.MAX_VALUE : i, (i3 & 8) != 0 ? TextOverflow.INSTANCE.m5957getClipgIe3tQ8() : i2, (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(multiParagraphIntrinsics, j, (i2 & 4) != 0 ? Integer.MAX_VALUE : i, (i2 & 8) != 0 ? false : z, (DefaultConstructorMarker) null);
    }

    private MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, boolean z) {
        this(multiParagraphIntrinsics, j, i, z ? TextOverflow.INSTANCE.m5958getEllipsisgIe3tQ8() : TextOverflow.INSTANCE.m5957getClipgIe3tQ8(), (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, int i, boolean z, float f, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(multiParagraphIntrinsics, (i2 & 2) != 0 ? Integer.MAX_VALUE : i, (i2 & 4) != 0 ? false : z, f);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated(message = "MultiParagraph that takes maximum allowed width is deprecated, pass constraints instead.", replaceWith = @ReplaceWith(expression = "MultiParagraph(intrinsics, Constraints(maxWidth = ceil(width).toInt()), maxLines, ellipsis)", imports = {"kotlin.math.ceil", "androidx.compose.ui.unit.Constraints"}))
    public MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, int i, boolean z, float f) {
        long jConstraints$default = ConstraintsKt.Constraints$default(0, ParagraphKt.ceilToInt(f), 0, 0, 13, null);
        TextOverflow.Companion companion = TextOverflow.INSTANCE;
        this(multiParagraphIntrinsics, jConstraints$default, i, z ? companion.m5958getEllipsisgIe3tQ8() : companion.m5957getClipgIe3tQ8(), (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, List list, int i, boolean z, float f, Density density, Font.ResourceLoader resourceLoader, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, (List<AnnotatedString.Range<Placeholder>>) ((i2 & 4) != 0 ? CollectionsKt.emptyList() : list), (i2 & 8) != 0 ? Integer.MAX_VALUE : i, (i2 & 16) != 0 ? false : z, f, density, resourceLoader);
    }

    @Deprecated(message = "Font.ResourceLoader is deprecated, use fontFamilyResolver instead", replaceWith = @ReplaceWith(expression = "MultiParagraph(annotatedString, style, placeholders, maxLines, ellipsis, width, density, fontFamilyResolver)", imports = {}))
    public MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, List<AnnotatedString.Range<Placeholder>> list, int i, boolean z, float f, Density density, Font.ResourceLoader resourceLoader) {
        MultiParagraphIntrinsics multiParagraphIntrinsics = new MultiParagraphIntrinsics(annotatedString, textStyle, list, density, DelegatingFontLoaderForDeprecatedUsage_androidKt.createFontFamilyResolver(resourceLoader));
        TextOverflow.Companion companion = TextOverflow.INSTANCE;
        this(multiParagraphIntrinsics, ConstraintsKt.Constraints$default(0, ParagraphKt.ceilToInt(f), 0, 0, 13, null), i, z ? companion.m5958getEllipsisgIe3tQ8() : companion.m5957getClipgIe3tQ8(), (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, float f, Density density, FontFamily.Resolver resolver, List list, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, f, density, resolver, (List<AnnotatedString.Range<Placeholder>>) ((i2 & 32) != 0 ? CollectionsKt.emptyList() : list), (i2 & 64) != 0 ? Integer.MAX_VALUE : i, (i2 & 128) != 0 ? false : z);
    }

    @Deprecated(message = "MultiParagraph that takes maximum allowed width is deprecated, pass constraints instead.", replaceWith = @ReplaceWith(expression = "MultiParagraph(annotatedString, style, Constraints(maxWidth = ceil(width).toInt()), density, fontFamilyResolver, placeholders, maxLines, ellipsis)", imports = {"kotlin.math.ceil", "androidx.compose.ui.unit.Constraints"}))
    public MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, float f, Density density, FontFamily.Resolver resolver, List<AnnotatedString.Range<Placeholder>> list, int i, boolean z) {
        MultiParagraphIntrinsics multiParagraphIntrinsics = new MultiParagraphIntrinsics(annotatedString, textStyle, list, density, resolver);
        TextOverflow.Companion companion = TextOverflow.INSTANCE;
        this(multiParagraphIntrinsics, ConstraintsKt.Constraints$default(0, ParagraphKt.ceilToInt(f), 0, 0, 13, null), i, z ? companion.m5958getEllipsisgIe3tQ8() : companion.m5957getClipgIe3tQ8(), (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List list, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, j, density, resolver, (i2 & 32) != 0 ? CollectionsKt.emptyList() : list, (i2 & 64) != 0 ? Integer.MAX_VALUE : i, (i2 & 128) != 0 ? false : z, (DefaultConstructorMarker) null);
    }

    private MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List<AnnotatedString.Range<Placeholder>> list, int i, boolean z) {
        MultiParagraphIntrinsics multiParagraphIntrinsics = new MultiParagraphIntrinsics(annotatedString, textStyle, list, density, resolver);
        TextOverflow.Companion companion = TextOverflow.INSTANCE;
        this(multiParagraphIntrinsics, j, i, z ? companion.m5958getEllipsisgIe3tQ8() : companion.m5957getClipgIe3tQ8(), (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List list, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, j, density, resolver, (i3 & 32) != 0 ? CollectionsKt.emptyList() : list, (i3 & 64) != 0 ? Integer.MAX_VALUE : i, (i3 & 128) != 0 ? TextOverflow.INSTANCE.m5957getClipgIe3tQ8() : i2, (DefaultConstructorMarker) null);
    }

    private MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List<AnnotatedString.Range<Placeholder>> list, int i, int i2) {
        this(new MultiParagraphIntrinsics(annotatedString, textStyle, list, density, resolver), j, i, i2, (DefaultConstructorMarker) null);
    }
}
