package androidx.compose.foundation.text;

import androidx.compose.foundation.text.TextFieldSizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004²\u0006\n\u0010\u0005\u001a\u00020\u0006X\u008a\u0084\u0002"}, d2 = {"textFieldMinSize", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "foundation", "typeface", ""}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldSizeKt {
    public static Modifier a(TextStyle textStyle, Modifier modifier, Composer composer, int i) {
        composer.startReplaceGroup(1582736677);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1582736677, i, -1, "androidx.compose.foundation.text.textFieldMinSize.<anonymous> (TextFieldSize.kt:37)");
        }
        Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        FontFamily.Resolver resolver = (FontFamily.Resolver) composer.consume(CompositionLocalsKt.getLocalFontFamilyResolver());
        LayoutDirection layoutDirection = (LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection());
        boolean zChanged = composer.changed(textStyle) | composer.changed(layoutDirection.ordinal());
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = TextStyleKt.resolveDefaults(textStyle, layoutDirection);
            composer.updateRememberedValue(objRememberedValue);
        }
        TextStyle textStyle2 = (TextStyle) objRememberedValue;
        boolean zChanged2 = composer.changed(resolver) | composer.changed(textStyle2);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            FontFamily fontFamily = textStyle2.getFontFamily();
            FontWeight fontWeight = textStyle2.getFontWeight();
            if (fontWeight == null) {
                fontWeight = FontWeight.Companion.getNormal();
            }
            FontStyle fontStyle = textStyle2.getFontStyle-4Lr2A7w();
            int i2 = fontStyle != null ? fontStyle.unbox-impl() : FontStyle.Companion.getNormal-_-LCdwA();
            FontSynthesis fontSynthesis = textStyle2.getFontSynthesis-ZQGJjVo();
            objRememberedValue2 = resolver.resolve-DPcqOEQ(fontFamily, fontWeight, i2, fontSynthesis != null ? fontSynthesis.unbox-impl() : FontSynthesis.Companion.getAll-GVVA2EU());
            composer.updateRememberedValue(objRememberedValue2);
        }
        State state = (State) objRememberedValue2;
        Object objRememberedValue3 = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new TextFieldSize(layoutDirection, density, resolver, textStyle, state.getValue());
            composer.updateRememberedValue(objRememberedValue3);
        }
        final TextFieldSize textFieldSize = (TextFieldSize) objRememberedValue3;
        textFieldSize.update(layoutDirection, density, resolver, textStyle2, state.getValue());
        Modifier.Companion companion2 = Modifier.Companion;
        boolean zChangedInstance = composer.changedInstance(textFieldSize);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new Function3() { // from class: androidx.compose.foundation.text.k
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TextFieldSizeKt.textFieldMinSize$lambda$0$4$0(textFieldSize, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        Modifier modifierLayout = LayoutModifierKt.layout(companion2, (Function3) objRememberedValue4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return modifierLayout;
    }

    public static final Modifier textFieldMinSize(Modifier modifier, final TextStyle textStyle) {
        return ComposedModifierKt.composed$default(modifier, (Function1) null, new Function3() { // from class: bae
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextFieldSizeKt.a(textStyle, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult textFieldMinSize$lambda$0$4$0(TextFieldSize textFieldSize, MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        long minSize = textFieldSize.getMinSize();
        final Placeable placeable = measurable.measure-BRTryo0(Constraints.copy-Zbe2FdA$default(constraints.unbox-impl(), RangesKt.coerceIn((int) (minSize >> 32), Constraints.getMinWidth-impl(constraints.unbox-impl()), Constraints.getMaxWidth-impl(constraints.unbox-impl())), 0, RangesKt.coerceIn((int) (minSize & 4294967295L), Constraints.getMinHeight-impl(constraints.unbox-impl()), Constraints.getMaxHeight-impl(constraints.unbox-impl())), 0, 10, (Object) null));
        return MeasureScope.layout$default(measureScope, placeable.getWidth(), placeable.getHeight(), (Map) null, new Function1() { // from class: cae
            public final Object invoke(Object obj) {
                return TextFieldSizeKt.textFieldMinSize$lambda$0$4$0$0(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit textFieldMinSize$lambda$0$4$0$0(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }
}
