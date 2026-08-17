package androidx.compose.foundation.text;

import androidx.compose.foundation.text.TextFieldCursorKt;
import androidx.compose.foundation.text.input.internal.CursorAnimationState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000¨\u0006\f"}, d2 = {"cursor", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "enabled", "", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldCursorKt {
    public static Modifier b(final Brush brush, final LegacyTextFieldState legacyTextFieldState, final TextFieldValue textFieldValue, final OffsetMapping offsetMapping, Modifier modifier, Composer composer, int i) {
        Modifier modifierDrawWithContent;
        composer.startReplaceGroup(-84507373);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-84507373, i, -1, "androidx.compose.foundation.text.cursor.<anonymous> (TextFieldCursor.kt:46)");
        }
        boolean zBooleanValue = ((Boolean) composer.consume(CompositionLocalsKt.getLocalCursorBlinkEnabled())).booleanValue();
        boolean zChanged = composer.changed(zBooleanValue);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new CursorAnimationState(zBooleanValue);
            composer.updateRememberedValue(objRememberedValue);
        }
        final CursorAnimationState cursorAnimationState = (CursorAnimationState) objRememberedValue;
        boolean z = ((brush instanceof SolidColor) && ((SolidColor) brush).getValue-0d7_KjU() == 16) ? false : true;
        if (((WindowInfo) composer.consume(CompositionLocalsKt.getLocalWindowInfo())).isWindowFocused() && legacyTextFieldState.getHasFocus() && TextRange.getCollapsed-impl(textFieldValue.getSelection-d9O1mEE()) && z) {
            composer.startReplaceGroup(-707487962);
            AnnotatedString annotatedString = textFieldValue.getAnnotatedString();
            TextRange textRange = TextRange.box-impl(textFieldValue.getSelection-d9O1mEE());
            boolean zChangedInstance = composer.changedInstance(cursorAnimationState);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.Companion.getEmpty()) {
                objRememberedValue2 = new TextFieldCursorKt$cursor$1$1$1(cursorAnimationState, null);
                composer.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(annotatedString, textRange, (Function2) objRememberedValue2, composer, 0);
            boolean zChangedInstance2 = composer.changedInstance(cursorAnimationState) | composer.changedInstance(offsetMapping) | composer.changed(textFieldValue) | composer.changedInstance(legacyTextFieldState) | composer.changed(brush);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChangedInstance2 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                Function1 function1 = new Function1() { // from class: m5e
                    public final Object invoke(Object obj) {
                        return TextFieldCursorKt.cursor$lambda$0$2$0(cursorAnimationState, offsetMapping, textFieldValue, legacyTextFieldState, brush, (ContentDrawScope) obj);
                    }
                };
                composer.updateRememberedValue(function1);
                objRememberedValue3 = function1;
            }
            modifierDrawWithContent = DrawModifierKt.drawWithContent(modifier, (Function1) objRememberedValue3);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-705473241);
            composer.endReplaceGroup();
            modifierDrawWithContent = Modifier.Companion;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return modifierDrawWithContent;
    }

    public static final Modifier cursor(Modifier modifier, final LegacyTextFieldState legacyTextFieldState, final TextFieldValue textFieldValue, final OffsetMapping offsetMapping, final Brush brush, boolean z) {
        return z ? ComposedModifierKt.composed$default(modifier, (Function1) null, new Function3() { // from class: n5e
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextFieldCursorKt.b(brush, legacyTextFieldState, textFieldValue, offsetMapping, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, (Object) null) : modifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cursor$lambda$0$2$0(CursorAnimationState cursorAnimationState, OffsetMapping offsetMapping, TextFieldValue textFieldValue, LegacyTextFieldState legacyTextFieldState, Brush brush, ContentDrawScope contentDrawScope) {
        Rect rect;
        TextLayoutResult value;
        contentDrawScope.drawContent();
        float cursorAlpha = cursorAnimationState.getCursorAlpha();
        if (cursorAlpha != 0.0f) {
            int iOriginalToTransformed = offsetMapping.originalToTransformed(TextRange.getStart-impl(textFieldValue.getSelection-d9O1mEE()));
            TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
            if (layoutResult == null || (value = layoutResult.getValue()) == null || (rect = value.getCursorRect(iOriginalToTransformed)) == null) {
                rect = new Rect(0.0f, 0.0f, 0.0f, 0.0f);
            }
            float fCoerceAtLeast = RangesKt.coerceAtLeast((float) Math.floor(contentDrawScope.toPx-0680j_4(TextFieldCursor_androidKt.getDefaultCursorThickness())), 1.0f);
            float f = fCoerceAtLeast / 2.0f;
            float fCoerceAtLeast2 = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(rect.getLeft() + f, Float.intBitsToFloat((int) (contentDrawScope.getSize-NH-jbRc() >> 32)) - f), f);
            float fFloor = ((int) fCoerceAtLeast) % 2 == 1 ? ((float) Math.floor(fCoerceAtLeast2)) + 0.5f : (float) Math.rint(fCoerceAtLeast2);
            DrawScope.drawLine-1RTmtNc$default(contentDrawScope, brush, Offset.constructor-impl((((long) Float.floatToRawIntBits(fFloor)) << 32) | (((long) Float.floatToRawIntBits(rect.getTop())) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(rect.getBottom())) & 4294967295L) | (((long) Float.floatToRawIntBits(fFloor)) << 32)), fCoerceAtLeast, 0, (PathEffect) null, cursorAlpha, (ColorFilter) null, 0, 432, (Object) null);
        }
        return Unit.INSTANCE;
    }
}
