package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.TimePickerDialogDefaults;
import androidx.compose.material3.internal.Icons;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.DialogTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u00020\r¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Landroidx/compose/material3/TimePickerDialogDefaults;", "", "<init>", "()V", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "MinHeightForTimePicker", "Landroidx/compose/ui/unit/Dp;", "getMinHeightForTimePicker-D9Ej5fM", "()F", "F", "DisplayModeToggle", "", "onDisplayModeChange", "Lkotlin/Function0;", "displayMode", "Landroidx/compose/material3/TimePickerDisplayMode;", "modifier", "Landroidx/compose/ui/Modifier;", "DisplayModeToggle-S7Bxtbk", "(Lkotlin/jvm/functions/Function0;ILandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Title", "Title-pK_nZyw", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TimePickerDialogDefaults {
    public static final int $stable = 0;
    public static final TimePickerDialogDefaults INSTANCE = new TimePickerDialogDefaults();
    private static final float MinHeightForTimePicker = Dp.m6022constructorimpl(300.0f);

    private TimePickerDialogDefaults() {
    }

    public static Unit a(TimePickerDialogDefaults timePickerDialogDefaults, int i, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        timePickerDialogDefaults.m1124TitlepK_nZyw(i, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit b(TimePickerDialogDefaults timePickerDialogDefaults, Function0 function0, int i, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        timePickerDialogDefaults.m1123DisplayModeToggleS7Bxtbk(function0, i, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0061  */
    /* JADX WARN: Code duplicated, block: B:37:0x0063  */
    /* JADX WARN: Code duplicated, block: B:40:0x006c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:42:0x0072  */
    /* JADX WARN: Code duplicated, block: B:45:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:50:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:53:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:55:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: DisplayModeToggle-S7Bxtbk, reason: not valid java name */
    public final void m1123DisplayModeToggleS7Bxtbk(final Function0<Unit> function0, final int i, Modifier modifier, Composer composer, final int i2, final int i3) {
        Function0<Unit> function1;
        int i4;
        Modifier modifier2;
        boolean z;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1283607635);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i2 & 6) == 0) {
                i4 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 32 : 16;
        }
        int i5 = i3 & 4;
        if (i5 == 0) {
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            if ((i4 & 147) != 146) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1283607635, i4, -1, "androidx.compose.material3.TimePickerDialogDefaults.DisplayModeToggle (TimePickerDialog.kt:264)");
                }
                IconButtonKt.IconButton(function1, modifier4, false, null, null, null, ComposableLambdaKt.rememberComposableLambda(-698026161, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogDefaults$DisplayModeToggle$1
                    public final void invoke(Composer composer2, int i6) {
                        int iM1392constructorimpl;
                        if (!composer2.shouldExecute((i6 & 3) != 2, i6 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-698026161, i6, -1, "androidx.compose.material3.TimePickerDialogDefaults.DisplayModeToggle.<anonymous> (TimePickerDialog.kt:266)");
                        }
                        int i7 = i;
                        TimePickerDisplayMode.Companion companion = TimePickerDisplayMode.INSTANCE;
                        ImageVector keyboard = TimePickerDisplayMode.m1131equalsimpl0(i7, companion.m1136getPickerONbchU()) ? Icons.Outlined.INSTANCE.getKeyboard() : Icons.Outlined.INSTANCE.getSchedule();
                        if (TimePickerDisplayMode.m1131equalsimpl0(i, companion.m1136getPickerONbchU())) {
                            Strings.Companion companion2 = Strings.INSTANCE;
                            iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_toggle_touch);
                        } else {
                            Strings.Companion companion3 = Strings.INSTANCE;
                            iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_toggle_keyboard);
                        }
                        IconKt.m539Iconww6aTOc(keyboard, Strings_androidKt.m1471getString2EP1pXo(iM1392constructorimpl, composer2, 0), (Modifier) null, 0L, composer2, 0, 12);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i4 & 14) | 1572864 | ((i4 >> 3) & 112), 60);
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
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: eee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogDefaults.b(this.b, function0, i, modifier3, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        if ((i4 & 147) != 146) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            if (i5 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1283607635, i4, -1, "androidx.compose.material3.TimePickerDialogDefaults.DisplayModeToggle (TimePickerDialog.kt:264)");
            }
            IconButtonKt.IconButton(function1, modifier4, false, null, null, null, ComposableLambdaKt.rememberComposableLambda(-698026161, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogDefaults$DisplayModeToggle$1
                public final void invoke(Composer composer2, int i6) {
                    int iM1392constructorimpl;
                    if (!composer2.shouldExecute((i6 & 3) != 2, i6 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-698026161, i6, -1, "androidx.compose.material3.TimePickerDialogDefaults.DisplayModeToggle.<anonymous> (TimePickerDialog.kt:266)");
                    }
                    int i7 = i;
                    TimePickerDisplayMode.Companion companion = TimePickerDisplayMode.INSTANCE;
                    ImageVector keyboard = TimePickerDisplayMode.m1131equalsimpl0(i7, companion.m1136getPickerONbchU()) ? Icons.Outlined.INSTANCE.getKeyboard() : Icons.Outlined.INSTANCE.getSchedule();
                    if (TimePickerDisplayMode.m1131equalsimpl0(i, companion.m1136getPickerONbchU())) {
                        Strings.Companion companion2 = Strings.INSTANCE;
                        iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_toggle_touch);
                    } else {
                        Strings.Companion companion3 = Strings.INSTANCE;
                        iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_toggle_keyboard);
                    }
                    IconKt.m539Iconww6aTOc(keyboard, Strings_androidKt.m1471getString2EP1pXo(iM1392constructorimpl, composer2, 0), (Modifier) null, 0L, composer2, 0, 12);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i4 & 14) | 1572864 | ((i4 >> 3) & 112), 60);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: eee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerDialogDefaults.b(this.b, function0, i, modifier3, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0046  */
    /* JADX WARN: Code duplicated, block: B:27:0x0048  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:31:0x0053  */
    /* JADX WARN: Code duplicated, block: B:32:0x0057  */
    /* JADX WARN: Code duplicated, block: B:35:0x005e  */
    /* JADX WARN: Code duplicated, block: B:38:0x008b  */
    /* JADX WARN: Code duplicated, block: B:39:0x0094  */
    /* JADX WARN: Code duplicated, block: B:42:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:44:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:47:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:49:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: Title-pK_nZyw, reason: not valid java name */
    public final void m1124TitlepK_nZyw(final int i, Modifier modifier, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int iM1392constructorimpl;
        Composer composerStartRestartGroup = composer.startRestartGroup(1546564986);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = i2 | (composerStartRestartGroup.changed(i) ? 4 : 2);
        } else {
            i4 = i2;
        }
        int i5 = i3 & 2;
        if (i5 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i4 & 19) != 18) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1546564986, i4, -1, "androidx.compose.material3.TimePickerDialogDefaults.Title (TimePickerDialog.kt:294)");
                }
                Modifier modifier5 = PaddingKt.padding-qDBjuR0$default(modifier4, 0.0f, 0.0f, 0.0f, Dp.m6022constructorimpl(20.0f), 7, (Object) null);
                Modifier modifier6 = modifier4;
                TextStyle labelMedium = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6).getLabelMedium();
                if (TimePickerDisplayMode.m1131equalsimpl0(i, TimePickerDisplayMode.INSTANCE.m1136getPickerONbchU())) {
                    Strings.Companion companion = Strings.INSTANCE;
                    iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_dialog_title);
                } else {
                    Strings.Companion companion2 = Strings.INSTANCE;
                    iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_input_dialog_title);
                }
                composer2 = composerStartRestartGroup;
                TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(iM1392constructorimpl, composerStartRestartGroup, 0), modifier5, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, labelMedium, composer2, 0, 0, 131068);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier6;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogDefaults.a(this.b, i, modifier3, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i4 & 19) != 18) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            if (i5 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1546564986, i4, -1, "androidx.compose.material3.TimePickerDialogDefaults.Title (TimePickerDialog.kt:294)");
            }
            Modifier modifier7 = PaddingKt.padding-qDBjuR0$default(modifier4, 0.0f, 0.0f, 0.0f, Dp.m6022constructorimpl(20.0f), 7, (Object) null);
            Modifier modifier8 = modifier4;
            TextStyle labelMedium2 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6).getLabelMedium();
            if (TimePickerDisplayMode.m1131equalsimpl0(i, TimePickerDisplayMode.INSTANCE.m1136getPickerONbchU())) {
                Strings.Companion companion3 = Strings.INSTANCE;
                iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_dialog_title);
            } else {
                Strings.Companion companion4 = Strings.INSTANCE;
                iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_input_dialog_title);
            }
            composer2 = composerStartRestartGroup;
            TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(iM1392constructorimpl, composerStartRestartGroup, 0), modifier7, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, labelMedium2, composer2, 0, 0, 131068);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier8;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerDialogDefaults.a(this.b, i, modifier3, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final long getContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-443775449, i, -1, "androidx.compose.material3.TimePickerDialogDefaults.<get-containerColor> (TimePickerDialog.kt:242)");
        }
        long value = ColorSchemeKt.getValue(DialogTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: getMinHeightForTimePicker-D9Ej5fM, reason: not valid java name */
    public final float m1125getMinHeightForTimePickerD9Ej5fM() {
        return MinHeightForTimePicker;
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1241096723, i, -1, "androidx.compose.material3.TimePickerDialogDefaults.<get-shape> (TimePickerDialog.kt:246)");
        }
        Shape value = ShapesKt.getValue(DialogTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }
}
