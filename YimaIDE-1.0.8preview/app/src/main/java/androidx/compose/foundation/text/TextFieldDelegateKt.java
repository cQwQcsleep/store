package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.ParagraphKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a9\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010\u0010\u001a.\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0019H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u001a"}, d2 = {"DefaultWidthCharCount", "", "EmptyTextReplacement", "", "getEmptyTextReplacement", "()Ljava/lang/String;", "computeSizeForDefaultText", "Landroidx/compose/ui/unit/IntSize;", "style", "Landroidx/compose/ui/text/TextStyle;", "density", "Landroidx/compose/ui/unit/Density;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "text", "maxLines", "(Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/lang/String;I)J", "focusedRectInRoot", "Landroidx/compose/ui/geometry/Rect;", "layoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "layoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "focusOffset", "sizeForDefaultText", "Lkotlin/Function0;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldDelegateKt {
    public static final int DefaultWidthCharCount = 10;
    private static final String EmptyTextReplacement = StringsKt.repeat("H", 10);

    public static final long computeSizeForDefaultText(TextStyle textStyle, Density density, FontFamily.Resolver resolver, String str, int i) {
        Paragraph paragraph = ParagraphKt.Paragraph-Ul8oQg4$default(str, textStyle, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, (Object) null), density, resolver, CollectionsKt.emptyList(), (List) null, i, TextOverflow.Companion.getClip-gIe3tQ8(), 64, (Object) null);
        return IntSize.constructor-impl((((long) TextDelegateKt.ceilToIntPx(paragraph.getMinIntrinsicWidth())) << 32) | (((long) TextDelegateKt.ceilToIntPx(paragraph.getHeight())) & 4294967295L));
    }

    public static /* synthetic */ long computeSizeForDefaultText$default(TextStyle textStyle, Density density, FontFamily.Resolver resolver, String str, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            str = EmptyTextReplacement;
        }
        if ((i2 & 16) != 0) {
            i = 1;
        }
        return computeSizeForDefaultText(textStyle, density, resolver, str, i);
    }

    public static final Rect focusedRectInRoot(TextLayoutResult textLayoutResult, LayoutCoordinates layoutCoordinates, int i, Function0<IntSize> function0) {
        Rect boundingBox;
        if (i < textLayoutResult.getLayoutInput().getText().length()) {
            boundingBox = textLayoutResult.getBoundingBox(i);
        } else {
            boundingBox = i != 0 ? textLayoutResult.getBoundingBox(i - 1) : new Rect(0.0f, 0.0f, 1.0f, (int) (((IntSize) function0.invoke()).unbox-impl() & 4294967295L));
        }
        long j = layoutCoordinates.localToRoot-MK-Hz9U(Offset.constructor-impl((((long) Float.floatToRawIntBits(boundingBox.getTop())) & 4294967295L) | (((long) Float.floatToRawIntBits(boundingBox.getLeft())) << 32)));
        return RectKt.Rect-tz77jQw(Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j >> 32)))) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j & 4294967295L)))) & 4294967295L)), Size.constructor-impl((((long) Float.floatToRawIntBits(boundingBox.getBottom() - boundingBox.getTop())) & 4294967295L) | (((long) Float.floatToRawIntBits(boundingBox.getRight() - boundingBox.getLeft())) << 32)));
    }

    public static final String getEmptyTextReplacement() {
        return EmptyTextReplacement;
    }
}
