package androidx.compose.material3;

import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\u001a>\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b\u001aH\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\nH\u0001¢\u0006\u0002\u0010\u000e\u001aP\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\nH\u0001¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0017\"\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u000e\u0010\u0018\u001a\u00020\u0019X\u0080T¢\u0006\u0002\n\u0000\"\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\u0011X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"MaterialTheme", "", "colorScheme", "Landroidx/compose/material3/ColorScheme;", "shapes", "Landroidx/compose/material3/Shapes;", "typography", "Landroidx/compose/material3/Typography;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/material3/Shapes;Landroidx/compose/material3/Typography;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "motionScheme", "Landroidx/compose/material3/MotionScheme;", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/material3/MotionScheme;Landroidx/compose/material3/Shapes;Landroidx/compose/material3/Typography;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "MaterialExpressiveTheme", "LocalUsingExpressiveTheme", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "getLocalUsingExpressiveTheme", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "rememberTextSelectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "TextSelectionBackgroundOpacity", "", "_localMotionScheme", "get_localMotionScheme$annotations", "()V", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class MaterialThemeKt {
    public static final float TextSelectionBackgroundOpacity = 0.4f;
    private static final ProvidableCompositionLocal<Boolean> LocalUsingExpressiveTheme = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: mv9
        public final Object invoke() {
            return Boolean.valueOf(MaterialThemeKt.e());
        }
    });
    private static final ProvidableCompositionLocal<MotionScheme> _localMotionScheme = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: nv9
        public final Object invoke() {
            return MaterialThemeKt.c();
        }
    });

    /* JADX WARN: Code duplicated, block: B:100:0x019b  */
    /* JADX WARN: Code duplicated, block: B:103:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:105:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0048  */
    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:32:0x0059  */
    /* JADX WARN: Code duplicated, block: B:33:0x005c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0063  */
    /* JADX WARN: Code duplicated, block: B:39:0x0068  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:43:0x0074  */
    /* JADX WARN: Code duplicated, block: B:44:0x0077  */
    /* JADX WARN: Code duplicated, block: B:48:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x0087  */
    /* JADX WARN: Code duplicated, block: B:54:0x008f  */
    /* JADX WARN: Code duplicated, block: B:55:0x0092  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:60:0x009e  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:68:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:72:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:75:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:78:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:87:0x0110  */
    /* JADX WARN: Code duplicated, block: B:89:0x011c  */
    /* JADX WARN: Code duplicated, block: B:90:0x012d  */
    /* JADX WARN: Code duplicated, block: B:92:0x013a  */
    /* JADX WARN: Code duplicated, block: B:93:0x014b  */
    /* JADX WARN: Code duplicated, block: B:95:0x0164  */
    /* JADX WARN: Code duplicated, block: B:98:0x0191  */
    public static final void MaterialExpressiveTheme(ColorScheme colorScheme, MotionScheme motionScheme, Shapes shapes, Typography typography, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        ColorScheme colorScheme2;
        int i3;
        MotionScheme motionScheme2;
        int i4;
        Shapes shapes2;
        int i5;
        int i6;
        Typography typography2;
        int i7;
        Function2<? super Composer, ? super Integer, Unit> function3;
        int i8;
        boolean z;
        final ColorScheme colorScheme3;
        final MotionScheme motionScheme3;
        final Shapes shapes3;
        final Typography typography3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final MotionScheme motionScheme4;
        final Shapes shapes4;
        final Typography typography4;
        ProvidableCompositionLocal<Boolean> providableCompositionLocal;
        final ColorScheme colorScheme4;
        ColorScheme colorScheme5;
        MotionScheme motionScheme5;
        Typography typography5;
        Shapes shapes5;
        Composer composerStartRestartGroup = composer.startRestartGroup(1317329884);
        int i9 = i2 & 1;
        if (i9 != 0) {
            i3 = i | 6;
            colorScheme2 = colorScheme;
        } else if ((i & 6) == 0) {
            colorScheme2 = colorScheme;
            i3 = (composerStartRestartGroup.changed(colorScheme2) ? 4 : 2) | i;
        } else {
            colorScheme2 = colorScheme;
            i3 = i;
        }
        int i10 = i2 & 2;
        if (i10 == 0) {
            if ((i & 48) == 0) {
                motionScheme2 = motionScheme;
                i3 |= composerStartRestartGroup.changed(motionScheme2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    shapes2 = shapes;
                    if (composerStartRestartGroup.changed(shapes2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        typography2 = typography;
                        if (composerStartRestartGroup.changed(typography2)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i2 & 16) != 0) {
                        if ((i & 24576) == 0) {
                            function3 = function2;
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i8 = 16384;
                            } else {
                                i8 = 8192;
                            }
                            i3 |= i8;
                        }
                        if ((i3 & 9363) != 9362) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                            if (i9 != 0) {
                                colorScheme2 = null;
                            }
                            if (i10 != 0) {
                                motionScheme4 = null;
                            } else {
                                motionScheme4 = motionScheme2;
                            }
                            if (i4 != 0) {
                                shapes4 = null;
                            } else {
                                shapes4 = shapes2;
                            }
                            if (i6 != 0) {
                                typography4 = null;
                            } else {
                                typography4 = typography2;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                            }
                            providableCompositionLocal = LocalUsingExpressiveTheme;
                            if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                                composerStartRestartGroup.startReplaceGroup(1458674654);
                                if (colorScheme2 == null) {
                                    composerStartRestartGroup.startReplaceGroup(-1061322393);
                                    ColorScheme colorScheme6 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                                    composerStartRestartGroup.endReplaceGroup();
                                    colorScheme5 = colorScheme6;
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(-1061323292);
                                    composerStartRestartGroup.endReplaceGroup();
                                    colorScheme5 = colorScheme2;
                                }
                                if (motionScheme4 == null) {
                                    composerStartRestartGroup.startReplaceGroup(-1061320152);
                                    MotionScheme motionScheme6 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                                    composerStartRestartGroup.endReplaceGroup();
                                    motionScheme5 = motionScheme6;
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(-1061321082);
                                    composerStartRestartGroup.endReplaceGroup();
                                    motionScheme5 = motionScheme4;
                                }
                                if (typography4 == null) {
                                    composerStartRestartGroup.startReplaceGroup(-1061318010);
                                    Typography typography6 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                                    composerStartRestartGroup.endReplaceGroup();
                                    typography5 = typography6;
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(-1061318878);
                                    composerStartRestartGroup.endReplaceGroup();
                                    typography5 = typography4;
                                }
                                if (shapes4 == null) {
                                    composerStartRestartGroup.startReplaceGroup(-1061316190);
                                    Shapes shapes6 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                                    composerStartRestartGroup.endReplaceGroup();
                                    shapes5 = shapes6;
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(-1061316934);
                                    composerStartRestartGroup.endReplaceGroup();
                                    shapes5 = shapes4;
                                }
                                MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                                composerStartRestartGroup.endReplaceGroup();
                                colorScheme4 = colorScheme2;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1459011221);
                                colorScheme4 = colorScheme2;
                                CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                                    public final void invoke(Composer composer2, int i11) {
                                        Typography typography7;
                                        if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                            composer2.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                        }
                                        ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                        if (colorSchemeExpressiveLightColorScheme == null) {
                                            colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                        }
                                        ColorScheme colorScheme7 = colorSchemeExpressiveLightColorScheme;
                                        MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                        if (motionSchemeExpressive$material3 == null) {
                                            motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                        }
                                        MotionScheme motionScheme7 = motionSchemeExpressive$material3;
                                        Shapes shapes7 = shapes4;
                                        Shapes shapes8 = shapes7 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes7;
                                        Typography typography8 = typography4;
                                        if (typography8 == null) {
                                            typography7 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                        } else {
                                            typography7 = typography8;
                                        }
                                        MaterialThemeKt.MaterialTheme(colorScheme7, motionScheme7, shapes8, typography7, function2, composer2, 0, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            colorScheme3 = colorScheme4;
                            motionScheme3 = motionScheme4;
                            shapes3 = shapes4;
                            typography3 = typography4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            colorScheme3 = colorScheme2;
                            motionScheme3 = motionScheme2;
                            shapes3 = shapes2;
                            typography3 = typography2;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                                public final Object invoke(Object obj, Object obj2) {
                                    return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 24576;
                    function3 = function2;
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i9 != 0) {
                            colorScheme2 = null;
                        }
                        if (i10 != 0) {
                            motionScheme4 = null;
                        } else {
                            motionScheme4 = motionScheme2;
                        }
                        if (i4 != 0) {
                            shapes4 = null;
                        } else {
                            shapes4 = shapes2;
                        }
                        if (i6 != 0) {
                            typography4 = null;
                        } else {
                            typography4 = typography2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                        }
                        providableCompositionLocal = LocalUsingExpressiveTheme;
                        if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                            composerStartRestartGroup.startReplaceGroup(1458674654);
                            if (colorScheme2 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061322393);
                                ColorScheme colorScheme7 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                colorScheme5 = colorScheme7;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061323292);
                                composerStartRestartGroup.endReplaceGroup();
                                colorScheme5 = colorScheme2;
                            }
                            if (motionScheme4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061320152);
                                MotionScheme motionScheme7 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                motionScheme5 = motionScheme7;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061321082);
                                composerStartRestartGroup.endReplaceGroup();
                                motionScheme5 = motionScheme4;
                            }
                            if (typography4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061318010);
                                Typography typography7 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                typography5 = typography7;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061318878);
                                composerStartRestartGroup.endReplaceGroup();
                                typography5 = typography4;
                            }
                            if (shapes4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061316190);
                                Shapes shapes7 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                shapes5 = shapes7;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061316934);
                                composerStartRestartGroup.endReplaceGroup();
                                shapes5 = shapes4;
                            }
                            MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme4 = colorScheme2;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1459011221);
                            colorScheme4 = colorScheme2;
                            CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                                public final void invoke(Composer composer2, int i11) {
                                    Typography typography8;
                                    if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                    }
                                    ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                    if (colorSchemeExpressiveLightColorScheme == null) {
                                        colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                    }
                                    ColorScheme colorScheme8 = colorSchemeExpressiveLightColorScheme;
                                    MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                    if (motionSchemeExpressive$material3 == null) {
                                        motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                    }
                                    MotionScheme motionScheme8 = motionSchemeExpressive$material3;
                                    Shapes shapes8 = shapes4;
                                    Shapes shapes9 = shapes8 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes8;
                                    Typography typography9 = typography4;
                                    if (typography9 == null) {
                                        typography8 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                    } else {
                                        typography8 = typography9;
                                    }
                                    MaterialThemeKt.MaterialTheme(colorScheme8, motionScheme8, shapes9, typography8, function2, composer2, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        colorScheme3 = colorScheme4;
                        motionScheme3 = motionScheme4;
                        shapes3 = shapes4;
                        typography3 = typography4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        colorScheme3 = colorScheme2;
                        motionScheme3 = motionScheme2;
                        shapes3 = shapes2;
                        typography3 = typography2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                            public final Object invoke(Object obj, Object obj2) {
                                return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 3072;
                typography2 = typography;
                if ((i2 & 16) != 0) {
                    if ((i & 24576) == 0) {
                        function3 = function2;
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i9 != 0) {
                            colorScheme2 = null;
                        }
                        if (i10 != 0) {
                            motionScheme4 = null;
                        } else {
                            motionScheme4 = motionScheme2;
                        }
                        if (i4 != 0) {
                            shapes4 = null;
                        } else {
                            shapes4 = shapes2;
                        }
                        if (i6 != 0) {
                            typography4 = null;
                        } else {
                            typography4 = typography2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                        }
                        providableCompositionLocal = LocalUsingExpressiveTheme;
                        if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                            composerStartRestartGroup.startReplaceGroup(1458674654);
                            if (colorScheme2 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061322393);
                                ColorScheme colorScheme8 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                colorScheme5 = colorScheme8;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061323292);
                                composerStartRestartGroup.endReplaceGroup();
                                colorScheme5 = colorScheme2;
                            }
                            if (motionScheme4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061320152);
                                MotionScheme motionScheme8 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                motionScheme5 = motionScheme8;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061321082);
                                composerStartRestartGroup.endReplaceGroup();
                                motionScheme5 = motionScheme4;
                            }
                            if (typography4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061318010);
                                Typography typography8 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                typography5 = typography8;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061318878);
                                composerStartRestartGroup.endReplaceGroup();
                                typography5 = typography4;
                            }
                            if (shapes4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061316190);
                                Shapes shapes8 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                shapes5 = shapes8;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061316934);
                                composerStartRestartGroup.endReplaceGroup();
                                shapes5 = shapes4;
                            }
                            MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme4 = colorScheme2;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1459011221);
                            colorScheme4 = colorScheme2;
                            CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                                public final void invoke(Composer composer2, int i11) {
                                    Typography typography9;
                                    if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                    }
                                    ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                    if (colorSchemeExpressiveLightColorScheme == null) {
                                        colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                    }
                                    ColorScheme colorScheme9 = colorSchemeExpressiveLightColorScheme;
                                    MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                    if (motionSchemeExpressive$material3 == null) {
                                        motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                    }
                                    MotionScheme motionScheme9 = motionSchemeExpressive$material3;
                                    Shapes shapes9 = shapes4;
                                    Shapes shapes10 = shapes9 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes9;
                                    Typography typography10 = typography4;
                                    if (typography10 == null) {
                                        typography9 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                    } else {
                                        typography9 = typography10;
                                    }
                                    MaterialThemeKt.MaterialTheme(colorScheme9, motionScheme9, shapes10, typography9, function2, composer2, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        colorScheme3 = colorScheme4;
                        motionScheme3 = motionScheme4;
                        shapes3 = shapes4;
                        typography3 = typography4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        colorScheme3 = colorScheme2;
                        motionScheme3 = motionScheme2;
                        shapes3 = shapes2;
                        typography3 = typography2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                            public final Object invoke(Object obj, Object obj2) {
                                return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                function3 = function2;
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i9 != 0) {
                        colorScheme2 = null;
                    }
                    if (i10 != 0) {
                        motionScheme4 = null;
                    } else {
                        motionScheme4 = motionScheme2;
                    }
                    if (i4 != 0) {
                        shapes4 = null;
                    } else {
                        shapes4 = shapes2;
                    }
                    if (i6 != 0) {
                        typography4 = null;
                    } else {
                        typography4 = typography2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                    }
                    providableCompositionLocal = LocalUsingExpressiveTheme;
                    if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                        composerStartRestartGroup.startReplaceGroup(1458674654);
                        if (colorScheme2 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061322393);
                            ColorScheme colorScheme9 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme9;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061323292);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme2;
                        }
                        if (motionScheme4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061320152);
                            MotionScheme motionScheme9 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme9;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061321082);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme4;
                        }
                        if (typography4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061318010);
                            Typography typography9 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography9;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061318878);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography4;
                        }
                        if (shapes4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061316190);
                            Shapes shapes9 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes9;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061316934);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes4;
                        }
                        MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme4 = colorScheme2;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1459011221);
                        colorScheme4 = colorScheme2;
                        CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                            public final void invoke(Composer composer2, int i11) {
                                Typography typography10;
                                if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                }
                                ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                if (colorSchemeExpressiveLightColorScheme == null) {
                                    colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                }
                                ColorScheme colorScheme10 = colorSchemeExpressiveLightColorScheme;
                                MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                if (motionSchemeExpressive$material3 == null) {
                                    motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                }
                                MotionScheme motionScheme10 = motionSchemeExpressive$material3;
                                Shapes shapes10 = shapes4;
                                Shapes shapes11 = shapes10 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes10;
                                Typography typography11 = typography4;
                                if (typography11 == null) {
                                    typography10 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                } else {
                                    typography10 = typography11;
                                }
                                MaterialThemeKt.MaterialTheme(colorScheme10, motionScheme10, shapes11, typography10, function2, composer2, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorScheme3 = colorScheme4;
                    motionScheme3 = motionScheme4;
                    shapes3 = shapes4;
                    typography3 = typography4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    colorScheme3 = colorScheme2;
                    motionScheme3 = motionScheme2;
                    shapes3 = shapes2;
                    typography3 = typography2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                        public final Object invoke(Object obj, Object obj2) {
                            return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            shapes2 = shapes;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    typography2 = typography;
                    if (composerStartRestartGroup.changed(typography2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i2 & 16) != 0) {
                    if ((i & 24576) == 0) {
                        function3 = function2;
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i9 != 0) {
                            colorScheme2 = null;
                        }
                        if (i10 != 0) {
                            motionScheme4 = null;
                        } else {
                            motionScheme4 = motionScheme2;
                        }
                        if (i4 != 0) {
                            shapes4 = null;
                        } else {
                            shapes4 = shapes2;
                        }
                        if (i6 != 0) {
                            typography4 = null;
                        } else {
                            typography4 = typography2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                        }
                        providableCompositionLocal = LocalUsingExpressiveTheme;
                        if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                            composerStartRestartGroup.startReplaceGroup(1458674654);
                            if (colorScheme2 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061322393);
                                ColorScheme colorScheme10 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                colorScheme5 = colorScheme10;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061323292);
                                composerStartRestartGroup.endReplaceGroup();
                                colorScheme5 = colorScheme2;
                            }
                            if (motionScheme4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061320152);
                                MotionScheme motionScheme10 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                motionScheme5 = motionScheme10;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061321082);
                                composerStartRestartGroup.endReplaceGroup();
                                motionScheme5 = motionScheme4;
                            }
                            if (typography4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061318010);
                                Typography typography10 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                typography5 = typography10;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061318878);
                                composerStartRestartGroup.endReplaceGroup();
                                typography5 = typography4;
                            }
                            if (shapes4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061316190);
                                Shapes shapes10 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                shapes5 = shapes10;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061316934);
                                composerStartRestartGroup.endReplaceGroup();
                                shapes5 = shapes4;
                            }
                            MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme4 = colorScheme2;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1459011221);
                            colorScheme4 = colorScheme2;
                            CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                                public final void invoke(Composer composer2, int i11) {
                                    Typography typography11;
                                    if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                    }
                                    ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                    if (colorSchemeExpressiveLightColorScheme == null) {
                                        colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                    }
                                    ColorScheme colorScheme11 = colorSchemeExpressiveLightColorScheme;
                                    MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                    if (motionSchemeExpressive$material3 == null) {
                                        motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                    }
                                    MotionScheme motionScheme11 = motionSchemeExpressive$material3;
                                    Shapes shapes11 = shapes4;
                                    Shapes shapes12 = shapes11 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes11;
                                    Typography typography12 = typography4;
                                    if (typography12 == null) {
                                        typography11 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                    } else {
                                        typography11 = typography12;
                                    }
                                    MaterialThemeKt.MaterialTheme(colorScheme11, motionScheme11, shapes12, typography11, function2, composer2, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        colorScheme3 = colorScheme4;
                        motionScheme3 = motionScheme4;
                        shapes3 = shapes4;
                        typography3 = typography4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        colorScheme3 = colorScheme2;
                        motionScheme3 = motionScheme2;
                        shapes3 = shapes2;
                        typography3 = typography2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                            public final Object invoke(Object obj, Object obj2) {
                                return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                function3 = function2;
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i9 != 0) {
                        colorScheme2 = null;
                    }
                    if (i10 != 0) {
                        motionScheme4 = null;
                    } else {
                        motionScheme4 = motionScheme2;
                    }
                    if (i4 != 0) {
                        shapes4 = null;
                    } else {
                        shapes4 = shapes2;
                    }
                    if (i6 != 0) {
                        typography4 = null;
                    } else {
                        typography4 = typography2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                    }
                    providableCompositionLocal = LocalUsingExpressiveTheme;
                    if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                        composerStartRestartGroup.startReplaceGroup(1458674654);
                        if (colorScheme2 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061322393);
                            ColorScheme colorScheme11 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme11;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061323292);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme2;
                        }
                        if (motionScheme4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061320152);
                            MotionScheme motionScheme11 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme11;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061321082);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme4;
                        }
                        if (typography4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061318010);
                            Typography typography11 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography11;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061318878);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography4;
                        }
                        if (shapes4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061316190);
                            Shapes shapes11 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes11;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061316934);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes4;
                        }
                        MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme4 = colorScheme2;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1459011221);
                        colorScheme4 = colorScheme2;
                        CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                            public final void invoke(Composer composer2, int i11) {
                                Typography typography12;
                                if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                }
                                ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                if (colorSchemeExpressiveLightColorScheme == null) {
                                    colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                }
                                ColorScheme colorScheme12 = colorSchemeExpressiveLightColorScheme;
                                MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                if (motionSchemeExpressive$material3 == null) {
                                    motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                }
                                MotionScheme motionScheme12 = motionSchemeExpressive$material3;
                                Shapes shapes12 = shapes4;
                                Shapes shapes13 = shapes12 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes12;
                                Typography typography13 = typography4;
                                if (typography13 == null) {
                                    typography12 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                } else {
                                    typography12 = typography13;
                                }
                                MaterialThemeKt.MaterialTheme(colorScheme12, motionScheme12, shapes13, typography12, function2, composer2, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorScheme3 = colorScheme4;
                    motionScheme3 = motionScheme4;
                    shapes3 = shapes4;
                    typography3 = typography4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    colorScheme3 = colorScheme2;
                    motionScheme3 = motionScheme2;
                    shapes3 = shapes2;
                    typography3 = typography2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                        public final Object invoke(Object obj, Object obj2) {
                            return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            typography2 = typography;
            if ((i2 & 16) != 0) {
                if ((i & 24576) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i9 != 0) {
                        colorScheme2 = null;
                    }
                    if (i10 != 0) {
                        motionScheme4 = null;
                    } else {
                        motionScheme4 = motionScheme2;
                    }
                    if (i4 != 0) {
                        shapes4 = null;
                    } else {
                        shapes4 = shapes2;
                    }
                    if (i6 != 0) {
                        typography4 = null;
                    } else {
                        typography4 = typography2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                    }
                    providableCompositionLocal = LocalUsingExpressiveTheme;
                    if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                        composerStartRestartGroup.startReplaceGroup(1458674654);
                        if (colorScheme2 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061322393);
                            ColorScheme colorScheme12 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme12;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061323292);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme2;
                        }
                        if (motionScheme4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061320152);
                            MotionScheme motionScheme12 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme12;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061321082);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme4;
                        }
                        if (typography4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061318010);
                            Typography typography12 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography12;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061318878);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography4;
                        }
                        if (shapes4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061316190);
                            Shapes shapes12 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes12;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061316934);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes4;
                        }
                        MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme4 = colorScheme2;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1459011221);
                        colorScheme4 = colorScheme2;
                        CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                            public final void invoke(Composer composer2, int i11) {
                                Typography typography13;
                                if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                }
                                ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                if (colorSchemeExpressiveLightColorScheme == null) {
                                    colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                }
                                ColorScheme colorScheme13 = colorSchemeExpressiveLightColorScheme;
                                MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                if (motionSchemeExpressive$material3 == null) {
                                    motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                }
                                MotionScheme motionScheme13 = motionSchemeExpressive$material3;
                                Shapes shapes13 = shapes4;
                                Shapes shapes14 = shapes13 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes13;
                                Typography typography14 = typography4;
                                if (typography14 == null) {
                                    typography13 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                } else {
                                    typography13 = typography14;
                                }
                                MaterialThemeKt.MaterialTheme(colorScheme13, motionScheme13, shapes14, typography13, function2, composer2, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorScheme3 = colorScheme4;
                    motionScheme3 = motionScheme4;
                    shapes3 = shapes4;
                    typography3 = typography4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    colorScheme3 = colorScheme2;
                    motionScheme3 = motionScheme2;
                    shapes3 = shapes2;
                    typography3 = typography2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                        public final Object invoke(Object obj, Object obj2) {
                            return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function3 = function2;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i9 != 0) {
                    colorScheme2 = null;
                }
                if (i10 != 0) {
                    motionScheme4 = null;
                } else {
                    motionScheme4 = motionScheme2;
                }
                if (i4 != 0) {
                    shapes4 = null;
                } else {
                    shapes4 = shapes2;
                }
                if (i6 != 0) {
                    typography4 = null;
                } else {
                    typography4 = typography2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                }
                providableCompositionLocal = LocalUsingExpressiveTheme;
                if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                    composerStartRestartGroup.startReplaceGroup(1458674654);
                    if (colorScheme2 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061322393);
                        ColorScheme colorScheme13 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme5 = colorScheme13;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061323292);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme5 = colorScheme2;
                    }
                    if (motionScheme4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061320152);
                        MotionScheme motionScheme13 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        motionScheme5 = motionScheme13;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061321082);
                        composerStartRestartGroup.endReplaceGroup();
                        motionScheme5 = motionScheme4;
                    }
                    if (typography4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061318010);
                        Typography typography13 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        typography5 = typography13;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061318878);
                        composerStartRestartGroup.endReplaceGroup();
                        typography5 = typography4;
                    }
                    if (shapes4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061316190);
                        Shapes shapes13 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        shapes5 = shapes13;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061316934);
                        composerStartRestartGroup.endReplaceGroup();
                        shapes5 = shapes4;
                    }
                    MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                    composerStartRestartGroup.endReplaceGroup();
                    colorScheme4 = colorScheme2;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1459011221);
                    colorScheme4 = colorScheme2;
                    CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                        public final void invoke(Composer composer2, int i11) {
                            Typography typography14;
                            if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                            }
                            ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                            if (colorSchemeExpressiveLightColorScheme == null) {
                                colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                            }
                            ColorScheme colorScheme14 = colorSchemeExpressiveLightColorScheme;
                            MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                            if (motionSchemeExpressive$material3 == null) {
                                motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                            }
                            MotionScheme motionScheme14 = motionSchemeExpressive$material3;
                            Shapes shapes14 = shapes4;
                            Shapes shapes15 = shapes14 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes14;
                            Typography typography15 = typography4;
                            if (typography15 == null) {
                                typography14 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                            } else {
                                typography14 = typography15;
                            }
                            MaterialThemeKt.MaterialTheme(colorScheme14, motionScheme14, shapes15, typography14, function2, composer2, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                colorScheme3 = colorScheme4;
                motionScheme3 = motionScheme4;
                shapes3 = shapes4;
                typography3 = typography4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                colorScheme3 = colorScheme2;
                motionScheme3 = motionScheme2;
                shapes3 = shapes2;
                typography3 = typography2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                    public final Object invoke(Object obj, Object obj2) {
                        return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        motionScheme2 = motionScheme;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                shapes2 = shapes;
                if (composerStartRestartGroup.changed(shapes2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    typography2 = typography;
                    if (composerStartRestartGroup.changed(typography2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i2 & 16) != 0) {
                    if ((i & 24576) == 0) {
                        function3 = function2;
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i9 != 0) {
                            colorScheme2 = null;
                        }
                        if (i10 != 0) {
                            motionScheme4 = null;
                        } else {
                            motionScheme4 = motionScheme2;
                        }
                        if (i4 != 0) {
                            shapes4 = null;
                        } else {
                            shapes4 = shapes2;
                        }
                        if (i6 != 0) {
                            typography4 = null;
                        } else {
                            typography4 = typography2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                        }
                        providableCompositionLocal = LocalUsingExpressiveTheme;
                        if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                            composerStartRestartGroup.startReplaceGroup(1458674654);
                            if (colorScheme2 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061322393);
                                ColorScheme colorScheme14 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                colorScheme5 = colorScheme14;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061323292);
                                composerStartRestartGroup.endReplaceGroup();
                                colorScheme5 = colorScheme2;
                            }
                            if (motionScheme4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061320152);
                                MotionScheme motionScheme14 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                motionScheme5 = motionScheme14;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061321082);
                                composerStartRestartGroup.endReplaceGroup();
                                motionScheme5 = motionScheme4;
                            }
                            if (typography4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061318010);
                                Typography typography14 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                typography5 = typography14;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061318878);
                                composerStartRestartGroup.endReplaceGroup();
                                typography5 = typography4;
                            }
                            if (shapes4 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1061316190);
                                Shapes shapes14 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                                shapes5 = shapes14;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1061316934);
                                composerStartRestartGroup.endReplaceGroup();
                                shapes5 = shapes4;
                            }
                            MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme4 = colorScheme2;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1459011221);
                            colorScheme4 = colorScheme2;
                            CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                                public final void invoke(Composer composer2, int i11) {
                                    Typography typography15;
                                    if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                    }
                                    ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                    if (colorSchemeExpressiveLightColorScheme == null) {
                                        colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                    }
                                    ColorScheme colorScheme15 = colorSchemeExpressiveLightColorScheme;
                                    MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                    if (motionSchemeExpressive$material3 == null) {
                                        motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                    }
                                    MotionScheme motionScheme15 = motionSchemeExpressive$material3;
                                    Shapes shapes15 = shapes4;
                                    Shapes shapes16 = shapes15 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes15;
                                    Typography typography16 = typography4;
                                    if (typography16 == null) {
                                        typography15 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                    } else {
                                        typography15 = typography16;
                                    }
                                    MaterialThemeKt.MaterialTheme(colorScheme15, motionScheme15, shapes16, typography15, function2, composer2, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        colorScheme3 = colorScheme4;
                        motionScheme3 = motionScheme4;
                        shapes3 = shapes4;
                        typography3 = typography4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        colorScheme3 = colorScheme2;
                        motionScheme3 = motionScheme2;
                        shapes3 = shapes2;
                        typography3 = typography2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                            public final Object invoke(Object obj, Object obj2) {
                                return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                function3 = function2;
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i9 != 0) {
                        colorScheme2 = null;
                    }
                    if (i10 != 0) {
                        motionScheme4 = null;
                    } else {
                        motionScheme4 = motionScheme2;
                    }
                    if (i4 != 0) {
                        shapes4 = null;
                    } else {
                        shapes4 = shapes2;
                    }
                    if (i6 != 0) {
                        typography4 = null;
                    } else {
                        typography4 = typography2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                    }
                    providableCompositionLocal = LocalUsingExpressiveTheme;
                    if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                        composerStartRestartGroup.startReplaceGroup(1458674654);
                        if (colorScheme2 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061322393);
                            ColorScheme colorScheme15 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme15;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061323292);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme2;
                        }
                        if (motionScheme4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061320152);
                            MotionScheme motionScheme15 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme15;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061321082);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme4;
                        }
                        if (typography4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061318010);
                            Typography typography15 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography15;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061318878);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography4;
                        }
                        if (shapes4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061316190);
                            Shapes shapes15 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes15;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061316934);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes4;
                        }
                        MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme4 = colorScheme2;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1459011221);
                        colorScheme4 = colorScheme2;
                        CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                            public final void invoke(Composer composer2, int i11) {
                                Typography typography16;
                                if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                }
                                ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                if (colorSchemeExpressiveLightColorScheme == null) {
                                    colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                }
                                ColorScheme colorScheme16 = colorSchemeExpressiveLightColorScheme;
                                MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                if (motionSchemeExpressive$material3 == null) {
                                    motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                }
                                MotionScheme motionScheme16 = motionSchemeExpressive$material3;
                                Shapes shapes16 = shapes4;
                                Shapes shapes17 = shapes16 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes16;
                                Typography typography17 = typography4;
                                if (typography17 == null) {
                                    typography16 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                } else {
                                    typography16 = typography17;
                                }
                                MaterialThemeKt.MaterialTheme(colorScheme16, motionScheme16, shapes17, typography16, function2, composer2, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorScheme3 = colorScheme4;
                    motionScheme3 = motionScheme4;
                    shapes3 = shapes4;
                    typography3 = typography4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    colorScheme3 = colorScheme2;
                    motionScheme3 = motionScheme2;
                    shapes3 = shapes2;
                    typography3 = typography2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                        public final Object invoke(Object obj, Object obj2) {
                            return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            typography2 = typography;
            if ((i2 & 16) != 0) {
                if ((i & 24576) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i9 != 0) {
                        colorScheme2 = null;
                    }
                    if (i10 != 0) {
                        motionScheme4 = null;
                    } else {
                        motionScheme4 = motionScheme2;
                    }
                    if (i4 != 0) {
                        shapes4 = null;
                    } else {
                        shapes4 = shapes2;
                    }
                    if (i6 != 0) {
                        typography4 = null;
                    } else {
                        typography4 = typography2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                    }
                    providableCompositionLocal = LocalUsingExpressiveTheme;
                    if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                        composerStartRestartGroup.startReplaceGroup(1458674654);
                        if (colorScheme2 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061322393);
                            ColorScheme colorScheme16 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme16;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061323292);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme2;
                        }
                        if (motionScheme4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061320152);
                            MotionScheme motionScheme16 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme16;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061321082);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme4;
                        }
                        if (typography4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061318010);
                            Typography typography16 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography16;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061318878);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography4;
                        }
                        if (shapes4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061316190);
                            Shapes shapes16 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes16;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061316934);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes4;
                        }
                        MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme4 = colorScheme2;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1459011221);
                        colorScheme4 = colorScheme2;
                        CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                            public final void invoke(Composer composer2, int i11) {
                                Typography typography17;
                                if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                }
                                ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                if (colorSchemeExpressiveLightColorScheme == null) {
                                    colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                }
                                ColorScheme colorScheme17 = colorSchemeExpressiveLightColorScheme;
                                MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                if (motionSchemeExpressive$material3 == null) {
                                    motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                }
                                MotionScheme motionScheme17 = motionSchemeExpressive$material3;
                                Shapes shapes17 = shapes4;
                                Shapes shapes18 = shapes17 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes17;
                                Typography typography18 = typography4;
                                if (typography18 == null) {
                                    typography17 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                } else {
                                    typography17 = typography18;
                                }
                                MaterialThemeKt.MaterialTheme(colorScheme17, motionScheme17, shapes18, typography17, function2, composer2, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorScheme3 = colorScheme4;
                    motionScheme3 = motionScheme4;
                    shapes3 = shapes4;
                    typography3 = typography4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    colorScheme3 = colorScheme2;
                    motionScheme3 = motionScheme2;
                    shapes3 = shapes2;
                    typography3 = typography2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                        public final Object invoke(Object obj, Object obj2) {
                            return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function3 = function2;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i9 != 0) {
                    colorScheme2 = null;
                }
                if (i10 != 0) {
                    motionScheme4 = null;
                } else {
                    motionScheme4 = motionScheme2;
                }
                if (i4 != 0) {
                    shapes4 = null;
                } else {
                    shapes4 = shapes2;
                }
                if (i6 != 0) {
                    typography4 = null;
                } else {
                    typography4 = typography2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                }
                providableCompositionLocal = LocalUsingExpressiveTheme;
                if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                    composerStartRestartGroup.startReplaceGroup(1458674654);
                    if (colorScheme2 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061322393);
                        ColorScheme colorScheme17 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme5 = colorScheme17;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061323292);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme5 = colorScheme2;
                    }
                    if (motionScheme4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061320152);
                        MotionScheme motionScheme17 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        motionScheme5 = motionScheme17;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061321082);
                        composerStartRestartGroup.endReplaceGroup();
                        motionScheme5 = motionScheme4;
                    }
                    if (typography4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061318010);
                        Typography typography17 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        typography5 = typography17;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061318878);
                        composerStartRestartGroup.endReplaceGroup();
                        typography5 = typography4;
                    }
                    if (shapes4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061316190);
                        Shapes shapes17 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        shapes5 = shapes17;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061316934);
                        composerStartRestartGroup.endReplaceGroup();
                        shapes5 = shapes4;
                    }
                    MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                    composerStartRestartGroup.endReplaceGroup();
                    colorScheme4 = colorScheme2;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1459011221);
                    colorScheme4 = colorScheme2;
                    CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                        public final void invoke(Composer composer2, int i11) {
                            Typography typography18;
                            if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                            }
                            ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                            if (colorSchemeExpressiveLightColorScheme == null) {
                                colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                            }
                            ColorScheme colorScheme18 = colorSchemeExpressiveLightColorScheme;
                            MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                            if (motionSchemeExpressive$material3 == null) {
                                motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                            }
                            MotionScheme motionScheme18 = motionSchemeExpressive$material3;
                            Shapes shapes18 = shapes4;
                            Shapes shapes19 = shapes18 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes18;
                            Typography typography19 = typography4;
                            if (typography19 == null) {
                                typography18 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                            } else {
                                typography18 = typography19;
                            }
                            MaterialThemeKt.MaterialTheme(colorScheme18, motionScheme18, shapes19, typography18, function2, composer2, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                colorScheme3 = colorScheme4;
                motionScheme3 = motionScheme4;
                shapes3 = shapes4;
                typography3 = typography4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                colorScheme3 = colorScheme2;
                motionScheme3 = motionScheme2;
                shapes3 = shapes2;
                typography3 = typography2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                    public final Object invoke(Object obj, Object obj2) {
                        return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        shapes2 = shapes;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                typography2 = typography;
                if (composerStartRestartGroup.changed(typography2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i2 & 16) != 0) {
                if ((i & 24576) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i9 != 0) {
                        colorScheme2 = null;
                    }
                    if (i10 != 0) {
                        motionScheme4 = null;
                    } else {
                        motionScheme4 = motionScheme2;
                    }
                    if (i4 != 0) {
                        shapes4 = null;
                    } else {
                        shapes4 = shapes2;
                    }
                    if (i6 != 0) {
                        typography4 = null;
                    } else {
                        typography4 = typography2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                    }
                    providableCompositionLocal = LocalUsingExpressiveTheme;
                    if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                        composerStartRestartGroup.startReplaceGroup(1458674654);
                        if (colorScheme2 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061322393);
                            ColorScheme colorScheme18 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme18;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061323292);
                            composerStartRestartGroup.endReplaceGroup();
                            colorScheme5 = colorScheme2;
                        }
                        if (motionScheme4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061320152);
                            MotionScheme motionScheme18 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme18;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061321082);
                            composerStartRestartGroup.endReplaceGroup();
                            motionScheme5 = motionScheme4;
                        }
                        if (typography4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061318010);
                            Typography typography18 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography18;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061318878);
                            composerStartRestartGroup.endReplaceGroup();
                            typography5 = typography4;
                        }
                        if (shapes4 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1061316190);
                            Shapes shapes18 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes18;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1061316934);
                            composerStartRestartGroup.endReplaceGroup();
                            shapes5 = shapes4;
                        }
                        MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme4 = colorScheme2;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1459011221);
                        colorScheme4 = colorScheme2;
                        CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                            public final void invoke(Composer composer2, int i11) {
                                Typography typography19;
                                if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                                }
                                ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                                if (colorSchemeExpressiveLightColorScheme == null) {
                                    colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                                }
                                ColorScheme colorScheme19 = colorSchemeExpressiveLightColorScheme;
                                MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                                if (motionSchemeExpressive$material3 == null) {
                                    motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                                }
                                MotionScheme motionScheme19 = motionSchemeExpressive$material3;
                                Shapes shapes19 = shapes4;
                                Shapes shapes110 = shapes19 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes19;
                                Typography typography110 = typography4;
                                if (typography110 == null) {
                                    typography19 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                                } else {
                                    typography19 = typography110;
                                }
                                MaterialThemeKt.MaterialTheme(colorScheme19, motionScheme19, shapes110, typography19, function2, composer2, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorScheme3 = colorScheme4;
                    motionScheme3 = motionScheme4;
                    shapes3 = shapes4;
                    typography3 = typography4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    colorScheme3 = colorScheme2;
                    motionScheme3 = motionScheme2;
                    shapes3 = shapes2;
                    typography3 = typography2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                        public final Object invoke(Object obj, Object obj2) {
                            return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function3 = function2;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i9 != 0) {
                    colorScheme2 = null;
                }
                if (i10 != 0) {
                    motionScheme4 = null;
                } else {
                    motionScheme4 = motionScheme2;
                }
                if (i4 != 0) {
                    shapes4 = null;
                } else {
                    shapes4 = shapes2;
                }
                if (i6 != 0) {
                    typography4 = null;
                } else {
                    typography4 = typography2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                }
                providableCompositionLocal = LocalUsingExpressiveTheme;
                if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                    composerStartRestartGroup.startReplaceGroup(1458674654);
                    if (colorScheme2 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061322393);
                        ColorScheme colorScheme19 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme5 = colorScheme19;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061323292);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme5 = colorScheme2;
                    }
                    if (motionScheme4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061320152);
                        MotionScheme motionScheme19 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        motionScheme5 = motionScheme19;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061321082);
                        composerStartRestartGroup.endReplaceGroup();
                        motionScheme5 = motionScheme4;
                    }
                    if (typography4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061318010);
                        Typography typography19 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        typography5 = typography19;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061318878);
                        composerStartRestartGroup.endReplaceGroup();
                        typography5 = typography4;
                    }
                    if (shapes4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061316190);
                        Shapes shapes19 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        shapes5 = shapes19;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061316934);
                        composerStartRestartGroup.endReplaceGroup();
                        shapes5 = shapes4;
                    }
                    MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                    composerStartRestartGroup.endReplaceGroup();
                    colorScheme4 = colorScheme2;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1459011221);
                    colorScheme4 = colorScheme2;
                    CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                        public final void invoke(Composer composer2, int i11) {
                            Typography typography110;
                            if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                            }
                            ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                            if (colorSchemeExpressiveLightColorScheme == null) {
                                colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                            }
                            ColorScheme colorScheme110 = colorSchemeExpressiveLightColorScheme;
                            MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                            if (motionSchemeExpressive$material3 == null) {
                                motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                            }
                            MotionScheme motionScheme110 = motionSchemeExpressive$material3;
                            Shapes shapes110 = shapes4;
                            Shapes shapes111 = shapes110 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes110;
                            Typography typography111 = typography4;
                            if (typography111 == null) {
                                typography110 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                            } else {
                                typography110 = typography111;
                            }
                            MaterialThemeKt.MaterialTheme(colorScheme110, motionScheme110, shapes111, typography110, function2, composer2, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                colorScheme3 = colorScheme4;
                motionScheme3 = motionScheme4;
                shapes3 = shapes4;
                typography3 = typography4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                colorScheme3 = colorScheme2;
                motionScheme3 = motionScheme2;
                shapes3 = shapes2;
                typography3 = typography2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                    public final Object invoke(Object obj, Object obj2) {
                        return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        typography2 = typography;
        if ((i2 & 16) != 0) {
            if ((i & 24576) == 0) {
                function3 = function2;
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i9 != 0) {
                    colorScheme2 = null;
                }
                if (i10 != 0) {
                    motionScheme4 = null;
                } else {
                    motionScheme4 = motionScheme2;
                }
                if (i4 != 0) {
                    shapes4 = null;
                } else {
                    shapes4 = shapes2;
                }
                if (i6 != 0) {
                    typography4 = null;
                } else {
                    typography4 = typography2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
                }
                providableCompositionLocal = LocalUsingExpressiveTheme;
                if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                    composerStartRestartGroup.startReplaceGroup(1458674654);
                    if (colorScheme2 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061322393);
                        ColorScheme colorScheme110 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme5 = colorScheme110;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061323292);
                        composerStartRestartGroup.endReplaceGroup();
                        colorScheme5 = colorScheme2;
                    }
                    if (motionScheme4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061320152);
                        MotionScheme motionScheme110 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        motionScheme5 = motionScheme110;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061321082);
                        composerStartRestartGroup.endReplaceGroup();
                        motionScheme5 = motionScheme4;
                    }
                    if (typography4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061318010);
                        Typography typography110 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        typography5 = typography110;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061318878);
                        composerStartRestartGroup.endReplaceGroup();
                        typography5 = typography4;
                    }
                    if (shapes4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1061316190);
                        Shapes shapes110 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                        shapes5 = shapes110;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1061316934);
                        composerStartRestartGroup.endReplaceGroup();
                        shapes5 = shapes4;
                    }
                    MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                    composerStartRestartGroup.endReplaceGroup();
                    colorScheme4 = colorScheme2;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1459011221);
                    colorScheme4 = colorScheme2;
                    CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                        public final void invoke(Composer composer2, int i11) {
                            Typography typography111;
                            if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                            }
                            ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                            if (colorSchemeExpressiveLightColorScheme == null) {
                                colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                            }
                            ColorScheme colorScheme111 = colorSchemeExpressiveLightColorScheme;
                            MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                            if (motionSchemeExpressive$material3 == null) {
                                motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                            }
                            MotionScheme motionScheme111 = motionSchemeExpressive$material3;
                            Shapes shapes111 = shapes4;
                            Shapes shapes112 = shapes111 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes111;
                            Typography typography112 = typography4;
                            if (typography112 == null) {
                                typography111 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                            } else {
                                typography111 = typography112;
                            }
                            MaterialThemeKt.MaterialTheme(colorScheme111, motionScheme111, shapes112, typography111, function2, composer2, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                colorScheme3 = colorScheme4;
                motionScheme3 = motionScheme4;
                shapes3 = shapes4;
                typography3 = typography4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                colorScheme3 = colorScheme2;
                motionScheme3 = motionScheme2;
                shapes3 = shapes2;
                typography3 = typography2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                    public final Object invoke(Object obj, Object obj2) {
                        return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        function3 = function2;
        if ((i3 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i9 != 0) {
                colorScheme2 = null;
            }
            if (i10 != 0) {
                motionScheme4 = null;
            } else {
                motionScheme4 = motionScheme2;
            }
            if (i4 != 0) {
                shapes4 = null;
            } else {
                shapes4 = shapes2;
            }
            if (i6 != 0) {
                typography4 = null;
            } else {
                typography4 = typography2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1317329884, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:190)");
            }
            providableCompositionLocal = LocalUsingExpressiveTheme;
            if (((Boolean) composerStartRestartGroup.consume(providableCompositionLocal)).booleanValue()) {
                composerStartRestartGroup.startReplaceGroup(1458674654);
                if (colorScheme2 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1061322393);
                    ColorScheme colorScheme111 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                    colorScheme5 = colorScheme111;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1061323292);
                    composerStartRestartGroup.endReplaceGroup();
                    colorScheme5 = colorScheme2;
                }
                if (motionScheme4 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1061320152);
                    MotionScheme motionScheme111 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                    motionScheme5 = motionScheme111;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1061321082);
                    composerStartRestartGroup.endReplaceGroup();
                    motionScheme5 = motionScheme4;
                }
                if (typography4 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1061318010);
                    Typography typography111 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                    typography5 = typography111;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1061318878);
                    composerStartRestartGroup.endReplaceGroup();
                    typography5 = typography4;
                }
                if (shapes4 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1061316190);
                    Shapes shapes111 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                    shapes5 = shapes111;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1061316934);
                    composerStartRestartGroup.endReplaceGroup();
                    shapes5 = shapes4;
                }
                MaterialTheme(colorScheme5, motionScheme5, shapes5, typography5, function3, composerStartRestartGroup, i3 & 57344, 0);
                composerStartRestartGroup.endReplaceGroup();
                colorScheme4 = colorScheme2;
            } else {
                composerStartRestartGroup.startReplaceGroup(1459011221);
                colorScheme4 = colorScheme2;
                CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(Boolean.TRUE), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1535649272, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                    public final void invoke(Composer composer2, int i11) {
                        Typography typography112;
                        if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1535649272, i11, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:201)");
                        }
                        ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme4;
                        if (colorSchemeExpressiveLightColorScheme == null) {
                            colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                        }
                        ColorScheme colorScheme112 = colorSchemeExpressiveLightColorScheme;
                        MotionScheme motionSchemeExpressive$material3 = motionScheme4;
                        if (motionSchemeExpressive$material3 == null) {
                            motionSchemeExpressive$material3 = MotionScheme.INSTANCE.expressive$material3();
                        }
                        MotionScheme motionScheme112 = motionSchemeExpressive$material3;
                        Shapes shapes112 = shapes4;
                        Shapes shapes113 = shapes112 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes112;
                        Typography typography113 = typography4;
                        if (typography113 == null) {
                            typography112 = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                        } else {
                            typography112 = typography113;
                        }
                        MaterialThemeKt.MaterialTheme(colorScheme112, motionScheme112, shapes113, typography112, function2, composer2, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colorScheme3 = colorScheme4;
            motionScheme3 = motionScheme4;
            shapes3 = shapes4;
            typography3 = typography4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            colorScheme3 = colorScheme2;
            motionScheme3 = motionScheme2;
            shapes3 = shapes2;
            typography3 = typography2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ov9
                public final Object invoke(Object obj, Object obj2) {
                    return MaterialThemeKt.b(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void MaterialTheme(ColorScheme colorScheme, MotionScheme motionScheme, Shapes shapes, Typography typography, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        ColorScheme colorScheme2;
        int i3;
        MotionScheme motionScheme2;
        Shapes shapes2;
        final Typography typography2;
        Composer composerStartRestartGroup = composer.startRestartGroup(904511636);
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                colorScheme2 = colorScheme;
                int i4 = composerStartRestartGroup.changed(colorScheme2) ? 4 : 2;
                i3 = i4 | i;
            } else {
                colorScheme2 = colorScheme;
            }
            i3 = i4 | i;
        } else {
            colorScheme2 = colorScheme;
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                motionScheme2 = motionScheme;
                int i5 = composerStartRestartGroup.changed(motionScheme2) ? 32 : 16;
                i3 |= i5;
            } else {
                motionScheme2 = motionScheme;
            }
            i3 |= i5;
        } else {
            motionScheme2 = motionScheme;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                shapes2 = shapes;
                int i6 = composerStartRestartGroup.changed(shapes2) ? 256 : 128;
                i3 |= i6;
            } else {
                shapes2 = shapes;
            }
            i3 |= i6;
        } else {
            shapes2 = shapes;
        }
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                typography2 = typography;
                int i7 = composerStartRestartGroup.changed(typography2) ? 2048 : 1024;
                i3 |= i7;
            } else {
                typography2 = typography;
            }
            i3 |= i7;
        } else {
            typography2 = typography;
        }
        if ((i2 & 16) != 0) {
            i3 |= 24576;
        } else if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 9363) != 9362, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 1) != 0) {
                    colorScheme2 = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                    i3 &= -15;
                }
                if ((i2 & 2) != 0) {
                    motionScheme2 = MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    shapes2 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
                if ((i2 & 8) != 0) {
                    typography2 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                    i3 &= -7169;
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                if ((i2 & 2) != 0) {
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(904511636, i3, -1, "androidx.compose.material3.MaterialTheme (MaterialTheme.kt:95)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ColorSchemeKt.getLocalColorScheme().provides(colorScheme2), _localMotionScheme.provides(motionScheme2), IndicationKt.getLocalIndication().provides(RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), ShapesKt.getLocalShapes().provides(shapes2), TextSelectionColorsKt.getLocalTextSelectionColors().provides(rememberTextSelectionColors(colorScheme2, composerStartRestartGroup, i3 & 14)), TypographyKt.getLocalTypography().provides(typography2)}, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1750539308, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialTheme.2
                public final void invoke(Composer composer2, int i8) {
                    if (!composer2.shouldExecute((i8 & 3) != 2, i8 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1750539308, i8, -1, "androidx.compose.material3.MaterialTheme.<anonymous> (MaterialTheme.kt:106)");
                    }
                    TextKt.ProvideTextStyle(typography2.getBodyLarge(), function2, composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        final Shapes shapes3 = shapes2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final ColorScheme colorScheme3 = colorScheme2;
            final MotionScheme motionScheme3 = motionScheme2;
            final Typography typography3 = typography2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kv9
                public final Object invoke(Object obj, Object obj2) {
                    return MaterialThemeKt.a(colorScheme3, motionScheme3, shapes3, typography3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(ColorScheme colorScheme, MotionScheme motionScheme, Shapes shapes, Typography typography, Function2 function2, int i, int i2, Composer composer, int i3) {
        MaterialTheme(colorScheme, motionScheme, shapes, typography, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(ColorScheme colorScheme, MotionScheme motionScheme, Shapes shapes, Typography typography, Function2 function2, int i, int i2, Composer composer, int i3) {
        MaterialExpressiveTheme(colorScheme, motionScheme, shapes, typography, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static MotionScheme c() {
        return MotionScheme.INSTANCE.standard$material3();
    }

    public static Unit d(ColorScheme colorScheme, Shapes shapes, Typography typography, Function2 function2, int i, int i2, Composer composer, int i3) {
        MaterialTheme(colorScheme, shapes, typography, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static boolean e() {
        return false;
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalUsingExpressiveTheme() {
        return LocalUsingExpressiveTheme;
    }

    private static /* synthetic */ void get_localMotionScheme$annotations() {
    }

    public static final TextSelectionColors rememberTextSelectionColors(ColorScheme colorScheme, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1866455512, i, -1, "androidx.compose.material3.rememberTextSelectionColors (MaterialTheme.kt:217)");
        }
        long primary = colorScheme.getPrimary();
        boolean zChanged = composer.changed(primary);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            TextSelectionColors textSelectionColors = new TextSelectionColors(primary, Color.m3133copywmQWz5c$default(primary, 0.4f, 0.0f, 0.0f, 0.0f, 14, null), (DefaultConstructorMarker) null);
            composer.updateRememberedValue(textSelectionColors);
            objRememberedValue = textSelectionColors;
        }
        TextSelectionColors textSelectionColors2 = (TextSelectionColors) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return textSelectionColors2;
    }

    public static final void MaterialTheme(ColorScheme colorScheme, Shapes shapes, Typography typography, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        final Function2<? super Composer, ? super Integer, Unit> function3;
        final Typography typography2;
        final Shapes shapes2;
        final ColorScheme colorScheme2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-449719819);
        if ((i & 6) == 0) {
            i3 = (((i2 & 1) == 0 && composerStartRestartGroup.changed(colorScheme)) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= ((i2 & 2) == 0 && composerStartRestartGroup.changed(shapes)) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= ((i2 & 4) == 0 && composerStartRestartGroup.changed(typography)) ? 256 : 128;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 1) != 0) {
                    colorScheme = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6);
                    i3 &= -15;
                }
                if ((i2 & 2) != 0) {
                    shapes = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    typography = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                if ((i2 & 2) != 0) {
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
            }
            ColorScheme colorScheme3 = colorScheme;
            Shapes shapes3 = shapes;
            Typography typography3 = typography;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-449719819, i3, -1, "androidx.compose.material3.MaterialTheme (MaterialTheme.kt:59)");
            }
            int i4 = i3 << 3;
            MaterialTheme(colorScheme3, MaterialTheme.INSTANCE.getMotionScheme(composerStartRestartGroup, 6), shapes3, typography3, function2, composerStartRestartGroup, (i3 & 14) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i4 & 57344), 0);
            function3 = function2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colorScheme2 = colorScheme3;
            shapes2 = shapes3;
            typography2 = typography3;
        } else {
            function3 = function2;
            composerStartRestartGroup.skipToGroupEnd();
            typography2 = typography;
            shapes2 = shapes;
            colorScheme2 = colorScheme;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lv9
                public final Object invoke(Object obj, Object obj2) {
                    return MaterialThemeKt.d(colorScheme2, shapes2, typography2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
