package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.ParagraphIntrinsicsKt;
import androidx.compose.ui.text.ParagraphKt;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010A\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020CH\u0002¢\u0006\u0004\bD\u00100J\u001d\u0010E\u001a\u00020\u000b2\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u000206¢\u0006\u0004\bH\u0010IJ)\u0010J\u001a\u0002082\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u0002062\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0002¢\u0006\u0004\bK\u0010LJ\u0016\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\r2\u0006\u0010G\u001a\u000206JE\u0010O\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u0002042\u0006\u0010G\u001a\u000206H\u0002J\u001f\u0010S\u001a\u00020!2\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u000206H\u0000¢\u0006\u0004\bT\u0010UJ\u001f\u0010V\u001a\u00020\u000b2\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u000206H\u0002¢\u0006\u0004\bW\u0010IJ\b\u0010X\u001a\u00020\u001dH\u0002J\u0010\u0010Y\u001a\u0004\u0018\u00010Z2\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010[\u001a\u00020\r2\u0006\u0010G\u001a\u000206J\u000e\u0010\\\u001a\u00020\r2\u0006\u0010G\u001a\u000206J\b\u0010]\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R(\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001d8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u00020,X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u000208X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u00109\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010;\u001a\u00020<8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b=\u0010>\u001a\u0004\b?\u0010.\"\u0004\b@\u00100¨\u0006^"}, d2 = {"Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "<init>", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "lastDensity", "Landroidx/compose/foundation/text/modifiers/InlineDensity;", "J", "value", "Landroidx/compose/ui/unit/Density;", "density", "getDensity$foundation", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation", "(Landroidx/compose/ui/unit/Density;)V", "observeFontChanges", "", "getObserveFontChanges$foundation", "()Lkotlin/Unit;", "paragraph", "Landroidx/compose/ui/text/Paragraph;", "getParagraph$foundation", "()Landroidx/compose/ui/text/Paragraph;", "setParagraph$foundation", "(Landroidx/compose/ui/text/Paragraph;)V", "didOverflow", "getDidOverflow$foundation", "()Z", "setDidOverflow$foundation", "(Z)V", "layoutSize", "Landroidx/compose/ui/unit/IntSize;", "getLayoutSize-YbymL2g$foundation", "()J", "setLayoutSize-ozmzZPI$foundation", "(J)V", "mMinLinesConstrainer", "Landroidx/compose/foundation/text/modifiers/MinLinesConstrainer;", "paragraphIntrinsics", "Landroidx/compose/ui/text/ParagraphIntrinsics;", "intrinsicsLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "prevConstraints", "Landroidx/compose/ui/unit/Constraints;", "cachedIntrinsicHeightInputWidth", "cachedIntrinsicHeight", "historyFlag", "", "getHistoryFlag$foundation$annotations", "()V", "getHistoryFlag$foundation", "setHistoryFlag$foundation", "recordHistory", "op", "Landroidx/compose/foundation/text/modifiers/LayoutCacheOperation;", "recordHistory-4ETZmGE", "layoutWithConstraints", "constraints", "layoutDirection", "layoutWithConstraints-K40F9xA", "(JLandroidx/compose/ui/unit/LayoutDirection;)Z", "useMinLinesConstrainer", "useMinLinesConstrainer-euUD3Qg", "(JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/TextStyle;)J", "intrinsicHeight", "width", "update", "update-L6sJoHM", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZII)V", "setLayoutDirection", "layoutText", "layoutText-K40F9xA$foundation", "(JLandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/text/Paragraph;", "newLayoutWillBeDifferent", "newLayoutWillBeDifferent-K40F9xA", "markDirty", "slowCreateTextLayoutResultOrNull", "Landroidx/compose/ui/text/TextLayoutResult;", "minIntrinsicWidth", "maxIntrinsicWidth", "toString", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ParagraphLayoutCache {
    public static final int $stable = 8;
    private int cachedIntrinsicHeight;
    private int cachedIntrinsicHeightInputWidth;
    private Density density;
    private boolean didOverflow;
    private FontFamily.Resolver fontFamilyResolver;
    private long historyFlag;
    private LayoutDirection intrinsicsLayoutDirection;
    private long lastDensity;
    private long layoutSize;
    private MinLinesConstrainer mMinLinesConstrainer;
    private int maxLines;
    private int minLines;
    private int overflow;
    private Paragraph paragraph;
    private ParagraphIntrinsics paragraphIntrinsics;
    private long prevConstraints;
    private boolean softWrap;
    private TextStyle style;
    private String text;

    private ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3) {
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.lastDensity = InlineDensity.INSTANCE.m1686getUnspecifiedL26CHvs();
        this.layoutSize = IntSize.constructor-impl(0L);
        this.prevConstraints = Constraints.Companion.fixed-JhjzzOo(0, 0);
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
    }

    public static /* synthetic */ void getHistoryFlag$foundation$annotations() {
    }

    private final void markDirty() {
        this.paragraph = null;
        this.paragraphIntrinsics = null;
        this.intrinsicsLayoutDirection = null;
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
        this.prevConstraints = Constraints.Companion.fixed-JhjzzOo(0, 0);
        this.layoutSize = IntSize.constructor-impl(0L);
        this.didOverflow = false;
    }

    /* JADX INFO: renamed from: newLayoutWillBeDifferent-K40F9xA, reason: not valid java name */
    private final boolean m1716newLayoutWillBeDifferentK40F9xA(long constraints, LayoutDirection layoutDirection) {
        ParagraphIntrinsics paragraphIntrinsics;
        Paragraph paragraph = this.paragraph;
        if (paragraph == null || (paragraphIntrinsics = this.paragraphIntrinsics) == null || paragraphIntrinsics.getHasStaleResolvedFonts() || layoutDirection != this.intrinsicsLayoutDirection) {
            return true;
        }
        if (Constraints.equals-impl0(constraints, this.prevConstraints)) {
            return false;
        }
        return Constraints.getMaxWidth-impl(constraints) != Constraints.getMaxWidth-impl(this.prevConstraints) || Constraints.getMinWidth-impl(constraints) != Constraints.getMinWidth-impl(this.prevConstraints) || ((float) Constraints.getMaxHeight-impl(constraints)) < paragraph.getHeight() || paragraph.getDidExceedMaxLines();
    }

    /* JADX INFO: renamed from: recordHistory-4ETZmGE, reason: not valid java name */
    private final void m1717recordHistory4ETZmGE(long op) {
        this.historyFlag = op | (this.historyFlag << 2);
    }

    private final ParagraphIntrinsics setLayoutDirection(LayoutDirection layoutDirection) {
        ParagraphIntrinsics ParagraphIntrinsics = this.paragraphIntrinsics;
        if (ParagraphIntrinsics == null || layoutDirection != this.intrinsicsLayoutDirection || ParagraphIntrinsics.getHasStaleResolvedFonts()) {
            this.intrinsicsLayoutDirection = layoutDirection;
            String str = this.text;
            TextStyle textStyleResolveDefaults = TextStyleKt.resolveDefaults(this.style, layoutDirection);
            List listEmptyList = CollectionsKt.emptyList();
            Density density = this.density;
            density.getClass();
            ParagraphIntrinsics = ParagraphIntrinsicsKt.ParagraphIntrinsics(str, textStyleResolveDefaults, listEmptyList, density, this.fontFamilyResolver, CollectionsKt.emptyList());
        }
        this.paragraphIntrinsics = ParagraphIntrinsics;
        return ParagraphIntrinsics;
    }

    /* JADX INFO: renamed from: useMinLinesConstrainer-euUD3Qg, reason: not valid java name */
    private final long m1718useMinLinesConstrainereuUD3Qg(long constraints, LayoutDirection layoutDirection, TextStyle style) {
        MinLinesConstrainer.Companion companion = MinLinesConstrainer.INSTANCE;
        MinLinesConstrainer minLinesConstrainer = this.mMinLinesConstrainer;
        Density density = this.density;
        density.getClass();
        MinLinesConstrainer minLinesConstrainerFrom = companion.from(minLinesConstrainer, layoutDirection, style, density, this.fontFamilyResolver);
        this.mMinLinesConstrainer = minLinesConstrainerFrom;
        return minLinesConstrainerFrom.m1702coerceMinLinesOh53vG4$foundation(constraints, this.minLines);
    }

    /* JADX INFO: renamed from: useMinLinesConstrainer-euUD3Qg$default, reason: not valid java name */
    public static /* synthetic */ long m1719useMinLinesConstrainereuUD3Qg$default(ParagraphLayoutCache paragraphLayoutCache, long j, LayoutDirection layoutDirection, TextStyle textStyle, int i, Object obj) {
        if ((i & 4) != 0) {
            textStyle = paragraphLayoutCache.style;
        }
        return paragraphLayoutCache.m1718useMinLinesConstrainereuUD3Qg(j, layoutDirection, textStyle);
    }

    /* JADX INFO: renamed from: getDensity$foundation, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    /* JADX INFO: renamed from: getDidOverflow$foundation, reason: from getter */
    public final boolean getDidOverflow() {
        return this.didOverflow;
    }

    /* JADX INFO: renamed from: getHistoryFlag$foundation, reason: from getter */
    public final long getHistoryFlag() {
        return this.historyFlag;
    }

    /* JADX INFO: renamed from: getLayoutSize-YbymL2g$foundation, reason: not valid java name and from getter */
    public final long getLayoutSize() {
        return this.layoutSize;
    }

    public final Unit getObserveFontChanges$foundation() {
        ParagraphIntrinsics paragraphIntrinsics = this.paragraphIntrinsics;
        if (paragraphIntrinsics != null) {
            paragraphIntrinsics.getHasStaleResolvedFonts();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: getParagraph$foundation, reason: from getter */
    public final Paragraph getParagraph() {
        return this.paragraph;
    }

    public final int intrinsicHeight(int width, LayoutDirection layoutDirection) {
        ParagraphLayoutCache paragraphLayoutCache;
        LayoutDirection layoutDirection2;
        int i = this.cachedIntrinsicHeightInputWidth;
        int i2 = this.cachedIntrinsicHeight;
        if (width == i && i != -1) {
            return i2;
        }
        long jConstraints = ConstraintsKt.Constraints(0, width, 0, Integer.MAX_VALUE);
        if (this.minLines > 1) {
            paragraphLayoutCache = this;
            layoutDirection2 = layoutDirection;
            jConstraints = m1719useMinLinesConstrainereuUD3Qg$default(paragraphLayoutCache, jConstraints, layoutDirection2, null, 4, null);
        } else {
            paragraphLayoutCache = this;
            layoutDirection2 = layoutDirection;
        }
        int iCoerceAtLeast = RangesKt.coerceAtLeast(TextDelegateKt.ceilToIntPx(paragraphLayoutCache.m1721layoutTextK40F9xA$foundation(jConstraints, layoutDirection2).getHeight()), Constraints.getMinHeight-impl(jConstraints));
        paragraphLayoutCache.cachedIntrinsicHeightInputWidth = width;
        paragraphLayoutCache.cachedIntrinsicHeight = iCoerceAtLeast;
        return iCoerceAtLeast;
    }

    /* JADX INFO: renamed from: layoutText-K40F9xA$foundation, reason: not valid java name */
    public final Paragraph m1721layoutTextK40F9xA$foundation(long constraints, LayoutDirection layoutDirection) {
        ParagraphIntrinsics layoutDirection2 = setLayoutDirection(layoutDirection);
        return ParagraphKt.Paragraph-czeN-Hc(layoutDirection2, LayoutUtilsKt.m1698finalConstraintstfFHcEY(constraints, this.softWrap, this.overflow, layoutDirection2.getMaxIntrinsicWidth()), LayoutUtilsKt.m1699finalMaxLinesxdlQI24(this.softWrap, this.overflow, this.maxLines), this.overflow);
    }

    /* JADX INFO: renamed from: layoutWithConstraints-K40F9xA, reason: not valid java name */
    public final boolean m1722layoutWithConstraintsK40F9xA(long constraints, LayoutDirection layoutDirection) {
        ParagraphLayoutCache paragraphLayoutCache;
        LayoutDirection layoutDirection2;
        m1717recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1694getLayoutWithConstraintsDEKiAbY());
        boolean z = true;
        if (this.minLines > 1) {
            paragraphLayoutCache = this;
            layoutDirection2 = layoutDirection;
            constraints = m1719useMinLinesConstrainereuUD3Qg$default(paragraphLayoutCache, constraints, layoutDirection2, null, 4, null);
        } else {
            paragraphLayoutCache = this;
            layoutDirection2 = layoutDirection;
        }
        boolean z2 = false;
        if (paragraphLayoutCache.m1716newLayoutWillBeDifferentK40F9xA(constraints, layoutDirection2)) {
            Paragraph paragraphM1721layoutTextK40F9xA$foundation = paragraphLayoutCache.m1721layoutTextK40F9xA$foundation(constraints, layoutDirection2);
            paragraphLayoutCache.prevConstraints = constraints;
            long j = ConstraintsKt.constrain-4WqzIAM(constraints, IntSize.constructor-impl((((long) TextDelegateKt.ceilToIntPx(paragraphM1721layoutTextK40F9xA$foundation.getHeight())) & 4294967295L) | (((long) TextDelegateKt.ceilToIntPx(paragraphM1721layoutTextK40F9xA$foundation.getWidth())) << 32)));
            paragraphLayoutCache.layoutSize = j;
            if (!TextOverflow.equals-impl0(paragraphLayoutCache.overflow, TextOverflow.Companion.getVisible-gIe3tQ8()) && (((int) (j >> 32)) < paragraphM1721layoutTextK40F9xA$foundation.getWidth() || ((int) (j & 4294967295L)) < paragraphM1721layoutTextK40F9xA$foundation.getHeight())) {
                z2 = true;
            }
            paragraphLayoutCache.didOverflow = z2;
            paragraphLayoutCache.paragraph = paragraphM1721layoutTextK40F9xA$foundation;
            return true;
        }
        if (!Constraints.equals-impl0(constraints, paragraphLayoutCache.prevConstraints)) {
            Paragraph paragraph = paragraphLayoutCache.paragraph;
            paragraph.getClass();
            long j2 = ConstraintsKt.constrain-4WqzIAM(constraints, IntSize.constructor-impl((((long) TextDelegateKt.ceilToIntPx(paragraph.getHeight())) & 4294967295L) | (((long) TextDelegateKt.ceilToIntPx(Math.min(paragraph.getMaxIntrinsicWidth(), paragraph.getWidth()))) << 32)));
            paragraphLayoutCache.layoutSize = j2;
            if (TextOverflow.equals-impl0(paragraphLayoutCache.overflow, TextOverflow.Companion.getVisible-gIe3tQ8()) || (((int) (j2 >> 32)) >= paragraph.getWidth() && ((int) (4294967295L & j2)) >= paragraph.getHeight())) {
                z = false;
            }
            paragraphLayoutCache.didOverflow = z;
            paragraphLayoutCache.prevConstraints = constraints;
        }
        return false;
    }

    public final int maxIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMaxIntrinsicWidth());
    }

    public final int minIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMinIntrinsicWidth());
    }

    public final void setDensity$foundation(Density density) {
        Density density2 = this.density;
        long jM1678constructorimpl = density != null ? InlineDensity.m1678constructorimpl(density) : InlineDensity.INSTANCE.m1686getUnspecifiedL26CHvs();
        if (density2 == null) {
            this.density = density;
            this.lastDensity = jM1678constructorimpl;
        } else if (density == null || !InlineDensity.m1680equalsimpl0(this.lastDensity, jM1678constructorimpl)) {
            this.density = density;
            this.lastDensity = jM1678constructorimpl;
            m1717recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1695getMarkDirtyDensityDEKiAbY());
            markDirty();
        }
    }

    public final void setDidOverflow$foundation(boolean z) {
        this.didOverflow = z;
    }

    public final void setHistoryFlag$foundation(long j) {
        this.historyFlag = j;
    }

    /* JADX INFO: renamed from: setLayoutSize-ozmzZPI$foundation, reason: not valid java name */
    public final void m1723setLayoutSizeozmzZPI$foundation(long j) {
        this.layoutSize = j;
    }

    public final void setParagraph$foundation(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    public final TextLayoutResult slowCreateTextLayoutResultOrNull(TextStyle style) {
        Density density;
        LayoutDirection layoutDirection = this.intrinsicsLayoutDirection;
        if (layoutDirection == null || (density = this.density) == null) {
            return null;
        }
        AnnotatedString annotatedString = new AnnotatedString(this.text, (List) null, 2, (DefaultConstructorMarker) null);
        if (this.paragraph == null || this.paragraphIntrinsics == null) {
            return null;
        }
        long j = Constraints.constructor-impl(this.prevConstraints & (-8589934589L));
        return new TextLayoutResult(new TextLayoutInput(annotatedString, style, CollectionsKt.emptyList(), this.maxLines, this.softWrap, this.overflow, density, layoutDirection, this.fontFamilyResolver, j, (DefaultConstructorMarker) null), new MultiParagraph(new MultiParagraphIntrinsics(annotatedString, style, CollectionsKt.emptyList(), density, this.fontFamilyResolver), j, this.maxLines, this.overflow, (DefaultConstructorMarker) null), this.layoutSize, (DefaultConstructorMarker) null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ParagraphLayoutCache(paragraph=");
        sb.append(this.paragraph != null ? "<paragraph>" : "null");
        sb.append(", lastDensity=");
        sb.append((Object) InlineDensity.m1684toStringimpl(this.lastDensity));
        sb.append(", history=");
        sb.append(this.historyFlag);
        sb.append(", constraints=$)");
        return sb.toString();
    }

    /* JADX INFO: renamed from: update-L6sJoHM, reason: not valid java name */
    public final void m1724updateL6sJoHM(String text, TextStyle style, FontFamily.Resolver fontFamilyResolver, int overflow, boolean softWrap, int maxLines, int minLines) {
        this.text = text;
        this.style = style;
        this.fontFamilyResolver = fontFamilyResolver;
        this.overflow = overflow;
        this.softWrap = softWrap;
        this.maxLines = maxLines;
        this.minLines = minLines;
        m1717recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1696getMarkDirtyNodeDEKiAbY());
        markDirty();
    }

    public /* synthetic */ ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, i, z, i2, i3);
    }

    public /* synthetic */ ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, (i4 & 8) != 0 ? TextOverflow.Companion.getClip-gIe3tQ8() : i, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? Integer.MAX_VALUE : i2, (i4 & 64) != 0 ? 1 : i3, null);
    }
}
