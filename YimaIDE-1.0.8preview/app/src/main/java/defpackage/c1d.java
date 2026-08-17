package defpackage;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonElevation;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import io.github.rosemoe.sora.widget.CodeEditor;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class c1d {
    public static Unit a(boolean z, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1425397345, i, -1, "com.yimaide.app.ui.workbench.SearchReplacePanel.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SearchReplacePanel.kt:97)");
            }
            TextKt.Text-Nvy7gAk("Aa", (Modifier) null, ColorKt.Color(z ? 4281908728L : 4287931320L), (TextAutoSize) null, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24582, 0, 262122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(String str, RowScope rowScope, Composer composer, int i) {
        rowScope.getClass();
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-297796079, i, -1, "com.yimaide.app.ui.workbench.SearchReplaceActionButton.<anonymous> (SearchReplacePanel.kt:207)");
            }
            TextKt.Text-Nvy7gAk(str, (Modifier) null, 0L, (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.Companion.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composer, 1597440, 24960, 241582);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(String str, Function1 function1, Function0 function0, String str2, Function1 function2, boolean z, Function0 function3, String str3, Function0 function4, Function0 function5, Function0 function6, Function0 function7, Function0 function8, Modifier modifier, int i, int i2, int i3, Composer composer, int i4) {
        h(str, function1, function0, str2, function2, z, function3, str3, function4, function5, function6, function7, function8, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    public static Unit d(Function0 function0, KeyboardActionScope keyboardActionScope) {
        keyboardActionScope.getClass();
        function0.invoke();
        return Unit.INSTANCE;
    }

    public static Unit e(String str, Function0 function0, boolean z, boolean z2, Modifier modifier, int i, int i2, Composer composer, int i3) {
        g(str, function0, z, z2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit f(String str, RowScope rowScope, Composer composer, int i) {
        rowScope.getClass();
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1101088444, i, -1, "com.yimaide.app.ui.workbench.SearchReplaceActionButton.<anonymous> (SearchReplacePanel.kt:184)");
            }
            TextKt.Text-Nvy7gAk(str, (Modifier) null, 0L, (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.Companion.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composer, 1597440, 24960, 241582);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x007b  */
    /* JADX WARN: Code duplicated, block: B:45:0x007d  */
    /* JADX WARN: Code duplicated, block: B:48:0x0086 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:49:0x0088  */
    /* JADX WARN: Code duplicated, block: B:50:0x008b  */
    /* JADX WARN: Code duplicated, block: B:53:0x0092  */
    /* JADX WARN: Code duplicated, block: B:56:0x00be  */
    /* JADX WARN: Code duplicated, block: B:57:0x0150  */
    /* JADX WARN: Code duplicated, block: B:59:0x016c  */
    /* JADX WARN: Code duplicated, block: B:61:0x0176  */
    /* JADX WARN: Code duplicated, block: B:65:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:67:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:70:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:72:? A[RETURN, SYNTHETIC] */
    public static final void g(final String str, final Function0 function0, final boolean z, final boolean z2, Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        boolean z3;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        RoundedCornerShape roundedCornerShape;
        PaddingValues paddingValues;
        long j;
        str.getClass();
        function0.getClass();
        Composer composerStartRestartGroup = composer.startRestartGroup(-2014335769);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i & KyberEngine.KyberPolyBytes) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 256 : CodeEditor.FLAG_DRAW_SOFT_WRAP;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changed(z2) ? NewHope.SENDB_BYTES : NewHope.POLY_SIZE;
        }
        int i4 = i2 & 16;
        if (i4 == 0) {
            if ((i & 24576) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 16384 : 8192;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i4 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2014335769, i3, -1, "com.yimaide.app.ui.workbench.SearchReplaceActionButton (SearchReplacePanel.kt:165)");
                }
                roundedCornerShape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10.0f));
                paddingValues = PaddingKt.PaddingValues-YgX7TsA(Dp.constructor-impl(14.0f), Dp.constructor-impl(0.0f));
                if (z2) {
                    composerStartRestartGroup.startReplaceGroup(206508698);
                    Modifier modifier5 = SizeKt.height-3ABfNKs(modifier4, Dp.constructor-impl(36.0f));
                    ButtonDefaults buttonDefaults = ButtonDefaults.INSTANCE;
                    long jColor = ColorKt.Color(4279150057L);
                    long jColor2 = ColorKt.Color(4294507260L);
                    long jColor3 = ColorKt.Color(4280236883L);
                    long jColor4 = ColorKt.Color(4284773515L);
                    int i5 = ButtonDefaults.$stable;
                    ButtonColors buttonColors = buttonDefaults.buttonColors-ro_MJ88(jColor, jColor2, jColor3, jColor4, composerStartRestartGroup, (i5 << 12) | 3510, 0);
                    ButtonElevation buttonElevation = buttonDefaults.buttonElevation-R_JCAzs(Dp.constructor-impl(0.0f), Dp.constructor-impl(0.0f), 0.0f, 0.0f, Dp.constructor-impl(0.0f), composerStartRestartGroup, (i5 << 15) | 24630, 12);
                    composerStartRestartGroup = composerStartRestartGroup;
                    ButtonKt.Button(function0, modifier5, z, roundedCornerShape, buttonColors, buttonElevation, (BorderStroke) null, paddingValues, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(1101088444, true, new Function3() { // from class: z0d
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return c1d.f(str, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 >> 3) & 14) | 817889280 | (i3 & 896), DilithiumEngine.DilithiumPolyT1PackedBytes);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(207372544);
                    Modifier modifier6 = SizeKt.height-3ABfNKs(modifier4, Dp.constructor-impl(36.0f));
                    float f = Dp.constructor-impl(1.0f);
                    if (z) {
                        j = 4281549141L;
                    } else {
                        j = 4280562244L;
                    }
                    BorderStroke borderStroke = BorderStrokeKt.BorderStroke-cXLIe8U(f, ColorKt.Color(j));
                    int i6 = i3;
                    ButtonKt.OutlinedButton(function0, modifier6, z, roundedCornerShape, ButtonDefaults.INSTANCE.outlinedButtonColors-ro_MJ88(ColorKt.Color(4279179050L), ColorKt.Color(4293060848L), ColorKt.Color(4279179050L), ColorKt.Color(4284773515L), composerStartRestartGroup, (ButtonDefaults.$stable << 12) | 3510, 0), (ButtonElevation) null, borderStroke, paddingValues, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(-297796079, true, new Function3() { // from class: a1d
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return c1d.b(str, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i6 >> 3) & 14) | 817889280 | (i6 & 896), 288);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: b1d
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return c1d.e(str, function0, z, z2, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        modifier2 = modifier;
        if ((i3 & 9363) != 9362) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            if (i4 != 0) {
                modifier4 = Modifier.Companion;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2014335769, i3, -1, "com.yimaide.app.ui.workbench.SearchReplaceActionButton (SearchReplacePanel.kt:165)");
            }
            roundedCornerShape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10.0f));
            paddingValues = PaddingKt.PaddingValues-YgX7TsA(Dp.constructor-impl(14.0f), Dp.constructor-impl(0.0f));
            if (z2) {
                composerStartRestartGroup.startReplaceGroup(206508698);
                Modifier modifier7 = SizeKt.height-3ABfNKs(modifier4, Dp.constructor-impl(36.0f));
                ButtonDefaults buttonDefaults2 = ButtonDefaults.INSTANCE;
                long jColor5 = ColorKt.Color(4279150057L);
                long jColor6 = ColorKt.Color(4294507260L);
                long jColor7 = ColorKt.Color(4280236883L);
                long jColor8 = ColorKt.Color(4284773515L);
                int i7 = ButtonDefaults.$stable;
                ButtonColors buttonColors2 = buttonDefaults2.buttonColors-ro_MJ88(jColor5, jColor6, jColor7, jColor8, composerStartRestartGroup, (i7 << 12) | 3510, 0);
                ButtonElevation buttonElevation2 = buttonDefaults2.buttonElevation-R_JCAzs(Dp.constructor-impl(0.0f), Dp.constructor-impl(0.0f), 0.0f, 0.0f, Dp.constructor-impl(0.0f), composerStartRestartGroup, (i7 << 15) | 24630, 12);
                composerStartRestartGroup = composerStartRestartGroup;
                ButtonKt.Button(function0, modifier7, z, roundedCornerShape, buttonColors2, buttonElevation2, (BorderStroke) null, paddingValues, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(1101088444, true, new Function3() { // from class: z0d
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return c1d.f(str, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 >> 3) & 14) | 817889280 | (i3 & 896), DilithiumEngine.DilithiumPolyT1PackedBytes);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(207372544);
                Modifier modifier8 = SizeKt.height-3ABfNKs(modifier4, Dp.constructor-impl(36.0f));
                float f2 = Dp.constructor-impl(1.0f);
                if (z) {
                    j = 4281549141L;
                } else {
                    j = 4280562244L;
                }
                BorderStroke borderStroke2 = BorderStrokeKt.BorderStroke-cXLIe8U(f2, ColorKt.Color(j));
                int i8 = i3;
                ButtonKt.OutlinedButton(function0, modifier8, z, roundedCornerShape, ButtonDefaults.INSTANCE.outlinedButtonColors-ro_MJ88(ColorKt.Color(4279179050L), ColorKt.Color(4293060848L), ColorKt.Color(4279179050L), ColorKt.Color(4284773515L), composerStartRestartGroup, (ButtonDefaults.$stable << 12) | 3510, 0), (ButtonElevation) null, borderStroke2, paddingValues, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(-297796079, true, new Function3() { // from class: a1d
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return c1d.b(str, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i8 >> 3) & 14) | 817889280 | (i8 & 896), 288);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: b1d
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return c1d.e(str, function0, z, z2, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void h(final String str, final Function1 function1, final Function0 function0, final String str2, final Function1 function2, final boolean z, final Function0 function3, final String str3, final Function0 function4, final Function0 function5, final Function0 function6, final Function0 function7, final Function0 function8, Modifier modifier, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        int i5;
        int i6;
        final Modifier modifier2;
        str.getClass();
        function1.getClass();
        function0.getClass();
        str2.getClass();
        function2.getClass();
        function3.getClass();
        str3.getClass();
        function4.getClass();
        function5.getClass();
        function6.getClass();
        function7.getClass();
        function8.getClass();
        Composer composerStartRestartGroup = composer.startRestartGroup(-156682429);
        if ((i & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        if ((i & 48) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & KyberEngine.KyberPolyBytes) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function0) ? 256 : CodeEditor.FLAG_DRAW_SOFT_WRAP;
        }
        int i7 = i & 3072;
        int i8 = NewHope.POLY_SIZE;
        if (i7 == 0) {
            i4 |= composerStartRestartGroup.changed(str2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((i & 196608) == 0) {
            i4 |= composerStartRestartGroup.changed(z) ? 131072 : 65536;
        }
        if ((i & 1572864) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function3) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            i4 |= composerStartRestartGroup.changed(str3) ? 8388608 : 4194304;
        }
        if ((i & 100663296) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function4) ? 67108864 : 33554432;
        }
        if ((i & 805306368) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function5) ? 536870912 : 268435456;
        }
        if ((i2 & 6) == 0) {
            i5 = i2 | (composerStartRestartGroup.changedInstance(function6) ? 4 : 2);
        } else {
            i5 = i2;
        }
        if ((i2 & 48) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function7) ? 32 : 16;
        }
        if ((i2 & KyberEngine.KyberPolyBytes) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function8) ? 256 : CodeEditor.FLAG_DRAW_SOFT_WRAP;
        }
        int i9 = i5;
        int i10 = i3 & 8192;
        if (i10 != 0) {
            i6 = i9 | 3072;
        } else if ((i2 & 3072) == 0) {
            if (composerStartRestartGroup.changed(modifier)) {
                i8 = 2048;
            }
            i6 = i9 | i8;
        } else {
            i6 = i9;
        }
        if (composerStartRestartGroup.shouldExecute(((i4 & 306783379) == 306783378 && (i6 & 1171) == 1170) ? false : true, i4 & 1)) {
            Modifier modifier3 = i10 != 0 ? Modifier.Companion : modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-156682429, i4, i6, "com.yimaide.app.ui.workbench.SearchReplacePanel (SearchReplacePanel.kt:51)");
            }
            Modifier modifier4 = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), ColorKt.Color(4280166715L), (Shape) null, 2, (Object) null), Dp.constructor-impl(8.0f), Dp.constructor-impl(6.0f));
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.Vertical top = arrangement.getTop();
            Alignment.Companion companion = Alignment.Companion;
            Modifier modifier5 = modifier3;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(top, companion.getStart(), composerStartRestartGroup, 0);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier4);
            ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
            Function0 constructor = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer2, measurePolicyColumnMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, modifierMaterializeModifier, companion2.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            float f = Dp.constructor-impl(168.0f);
            float f2 = Dp.constructor-impl(8.0f);
            float f3 = Dp.constructor-impl(40.0f);
            float f4 = Dp.constructor-impl(80.0f);
            Modifier.Companion companion3 = Modifier.Companion;
            int i11 = i6;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion3, 0.0f, 1, (Object) null);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.getStart(), companion.getCenterVertically(), composerStartRestartGroup, 48);
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default);
            Function0 constructor2 = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer3 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer3, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(iHashCode2), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, modifierMaterializeModifier2, companion2.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, companion3, 1.0f, false, 2, (Object) null);
            TextStyle textStyle = new TextStyle(ColorKt.Color(4293060848L), TextUnitKt.getSp(13), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777212, (DefaultConstructorMarker) null);
            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
            TextFieldColors textFieldColors = outlinedTextFieldDefaults.colors-0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4281908728L), 0L, (TextSelectionColors) null, ColorKt.Color(4281908728L), ColorKt.Color(4281549141L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 100663296, 432, 0, 0, 3072, 2147477247, 4095);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.Companion.getSearch-eUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
            int i12 = i4;
            boolean z2 = (i4 & 896) == 256;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: w0d
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return c1d.d(function0, (KeyboardActionScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            KeyboardActions keyboardActions = new KeyboardActions((Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) objRememberedValue, (Function1) null, 47, (DefaultConstructorMarker) null);
            jj2 jj2Var = jj2.a;
            OutlinedTextFieldKt.OutlinedTextField(str, function1, modifierWeight$default, false, false, textStyle, (Function2) null, jj2Var.h(), (Function2) null, (Function2) null, (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, keyboardOptions, keyboardActions, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColors, composerStartRestartGroup, (i12 & 14) | 12779520 | (i12 & 112), 12779520, 0, 3964760);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion3, f2), composerStartRestartGroup, 6);
            Modifier modifier6 = SizeKt.width-3ABfNKs(companion3, f);
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(arrangement.getEnd(), companion.getCenterVertically(), composerStartRestartGroup, 54);
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier6);
            Function0 constructor3 = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer4 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer4, measurePolicyRowMeasurePolicy2, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer4, currentCompositionLocalMap3, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer4, Integer.valueOf(iHashCode3), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer4, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer4, modifierMaterializeModifier3, companion2.getSetModifier());
            TextKt.Text-Nvy7gAk(str3, SizeKt.width-3ABfNKs(companion3, f3), ColorKt.Color(4287931320L), (TextAutoSize) null, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.box-impl(TextAlign.Companion.getEnd-e0LSkKk()), 0L, TextOverflow.Companion.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, ((i12 >> 21) & 14) | 25008, 24960, 240616);
            IconButtonKt.IconButton(function3, SizeKt.size-3ABfNKs(companion3, Dp.constructor-impl(32.0f)), false, (IconButtonColors) null, (MutableInteractionSource) null, (Shape) null, ComposableLambdaKt.rememberComposableLambda(-1425397345, true, new Function2() { // from class: x0d
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return c1d.a(z, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 >> 18) & 14) | 1572912, 60);
            IconButtonKt.IconButton(function4, SizeKt.size-3ABfNKs(companion3, Dp.constructor-impl(32.0f)), false, (IconButtonColors) null, (MutableInteractionSource) null, (Shape) null, jj2Var.f(), composerStartRestartGroup, ((i12 >> 24) & 14) | 1572912, 60);
            IconButtonKt.IconButton(function5, SizeKt.size-3ABfNKs(companion3, Dp.constructor-impl(32.0f)), false, (IconButtonColors) null, (MutableInteractionSource) null, (Shape) null, jj2Var.g(), composerStartRestartGroup, ((i12 >> 27) & 14) | 1572912, 60);
            IconButtonKt.IconButton(function6, SizeKt.size-3ABfNKs(companion3, Dp.constructor-impl(32.0f)), false, (IconButtonColors) null, (MutableInteractionSource) null, (Shape) null, jj2Var.i(), composerStartRestartGroup, (i11 & 14) | 1572912, 60);
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endNode();
            Modifier modifier7 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(companion3, 0.0f, 1, (Object) null), 0.0f, Dp.constructor-impl(4.0f), 0.0f, 0.0f, 13, (Object) null);
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(arrangement.getStart(), companion.getCenterVertically(), composerStartRestartGroup, 48);
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier7);
            Function0 constructor4 = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor4);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer5 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer5, measurePolicyRowMeasurePolicy3, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer5, currentCompositionLocalMap4, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer5, Integer.valueOf(iHashCode4), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer5, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer5, modifierMaterializeModifier4, companion2.getSetModifier());
            boolean z3 = !StringsKt.isBlank(str);
            int i13 = i12 >> 9;
            OutlinedTextFieldKt.OutlinedTextField(str2, function2, RowScope.weight$default(rowScopeInstance, companion3, 1.0f, false, 2, (Object) null), false, false, new TextStyle(ColorKt.Color(4293060848L), TextUnitKt.getSp(13), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777212, (DefaultConstructorMarker) null), (Function2) null, jj2Var.j(), (Function2) null, (Function2) null, (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, outlinedTextFieldDefaults.colors-0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4281908728L), 0L, (TextSelectionColors) null, ColorKt.Color(4281908728L), ColorKt.Color(4281549141L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 100663296, 432, 0, 0, 3072, 2147477247, 4095), composerStartRestartGroup, (i13 & 14) | 12779520 | (i13 & 112), 12582912, 0, 4063064);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion3, f2), composerStartRestartGroup, 6);
            Modifier modifier8 = SizeKt.width-3ABfNKs(companion3, f);
            MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(arrangement.getEnd(), companion.getCenterVertically(), composerStartRestartGroup, 54);
            int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier8);
            Function0 constructor5 = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor5);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer6 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer6, measurePolicyRowMeasurePolicy4, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer6, currentCompositionLocalMap5, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer6, Integer.valueOf(iHashCode5), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer6, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer6, modifierMaterializeModifier5, companion2.getSetModifier());
            g("替换", function7, z3, false, SizeKt.width-3ABfNKs(companion3, f4), composerStartRestartGroup, (i11 & 112) | 27654, 0);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion3, f2), composerStartRestartGroup, 6);
            g("全部替换", function8, z3, true, SizeKt.width-3ABfNKs(companion3, f4), composerStartRestartGroup, ((i11 >> 3) & 112) | 27654, 0);
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier5;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: y0d
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return c1d.c(str, function1, function0, str2, function2, z, function3, str3, function4, function5, function6, function7, function8, modifier2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
