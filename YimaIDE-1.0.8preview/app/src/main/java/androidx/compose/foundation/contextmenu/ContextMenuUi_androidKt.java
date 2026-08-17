package androidx.compose.foundation.contextmenu;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010\u0002\u001a!\u0010\u0000\u001a\u00020\u00012\b\b\u0001\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0007\u001a\u00020\b*\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u00042\b\b\u0001\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a\"\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u00042\b\b\u0001\u0010\u000b\u001a\u00020\u0004H\u0002\u001a\u001d\u0010\u0011\u001a\u00020\b*\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001d\u0010\u0014\u001a\u00020\b*\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0015\u0010\u0013¨\u0006\u0016"}, d2 = {"computeContextMenuColors", "Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "backgroundStyleId", "", "foregroundStyleId", "(IILandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "resolveColor", "Landroidx/compose/ui/graphics/Color;", "Landroid/content/Context;", "resId", "attrId", "defaultColor", "resolveColor-g2O1Hgs", "(Landroid/content/Context;IIJ)J", "resolveColorStateList", "Landroid/content/res/ColorStateList;", "enabledColor", "enabledColor-4WTKRHQ", "(Landroid/content/res/ColorStateList;J)J", "disabledColor", "disabledColor-4WTKRHQ", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ContextMenuUi_androidKt {
    public static final ContextMenuColors computeContextMenuColors(int i, int i2, Composer composer, int i3) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1689505294, i3, -1, "androidx.compose.foundation.contextmenu.computeContextMenuColors (ContextMenuUi.android.kt:41)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        Configuration configuration = (Configuration) composer.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration());
        boolean zChanged = composer.changed(configuration) | composer.changed(context);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            long jM470resolveColorg2O1Hgs = m470resolveColorg2O1Hgs(context, i, R.attr.colorBackground, ContextMenuUiKt.getDefaultContextMenuColors().getBackgroundColor());
            ColorStateList colorStateListResolveColorStateList = resolveColorStateList(context, i2, R.attr.textColorPrimary);
            long jM469enabledColor4WTKRHQ = m469enabledColor4WTKRHQ(colorStateListResolveColorStateList, ContextMenuUiKt.getDefaultContextMenuColors().getTextColor());
            long jM468disabledColor4WTKRHQ = m468disabledColor4WTKRHQ(colorStateListResolveColorStateList, ContextMenuUiKt.getDefaultContextMenuColors().getDisabledTextColor());
            ContextMenuColors contextMenuColors = new ContextMenuColors(jM470resolveColorg2O1Hgs, jM469enabledColor4WTKRHQ, jM469enabledColor4WTKRHQ, jM468disabledColor4WTKRHQ, jM468disabledColor4WTKRHQ, null);
            composer.updateRememberedValue(contextMenuColors);
            objRememberedValue = contextMenuColors;
        }
        ContextMenuColors contextMenuColors2 = (ContextMenuColors) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return contextMenuColors2;
    }

    /* JADX INFO: renamed from: disabledColor-4WTKRHQ, reason: not valid java name */
    private static final long m468disabledColor4WTKRHQ(ColorStateList colorStateList, long j) {
        int i = ColorKt.toArgb-8_81llA(j);
        Integer numValueOf = colorStateList != null ? Integer.valueOf(colorStateList.getColorForState(new int[]{-16842910}, i)) : null;
        return (numValueOf == null || numValueOf.intValue() == i) ? j : ColorKt.Color(numValueOf.intValue());
    }

    /* JADX INFO: renamed from: enabledColor-4WTKRHQ, reason: not valid java name */
    private static final long m469enabledColor4WTKRHQ(ColorStateList colorStateList, long j) {
        int i = ColorKt.toArgb-8_81llA(j);
        Integer numValueOf = colorStateList != null ? Integer.valueOf(colorStateList.getColorForState(new int[]{R.attr.state_enabled}, i)) : null;
        return (numValueOf == null || numValueOf.intValue() == i) ? j : ColorKt.Color(numValueOf.intValue());
    }

    /* JADX INFO: renamed from: resolveColor-g2O1Hgs, reason: not valid java name */
    private static final long m470resolveColorg2O1Hgs(Context context, int i, int i2, long j) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, new int[]{i2});
        int i3 = ColorKt.toArgb-8_81llA(j);
        int color = typedArrayObtainStyledAttributes.getColor(0, i3);
        typedArrayObtainStyledAttributes.recycle();
        return color == i3 ? j : ColorKt.Color(color);
    }

    private static final ColorStateList resolveColorStateList(Context context, int i, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, new int[]{i2});
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(0);
        typedArrayObtainStyledAttributes.recycle();
        return colorStateList;
    }

    public static final ContextMenuColors computeContextMenuColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1428061410, i, -1, "androidx.compose.foundation.contextmenu.computeContextMenuColors (ContextMenuUi.android.kt:32)");
        }
        ContextMenuColors contextMenuColorsComputeContextMenuColors = computeContextMenuColors(R.style.Widget.PopupMenu, R.style.TextAppearance.Widget.PopupMenu.Large, composer, 54);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return contextMenuColorsComputeContextMenuColors;
    }
}
