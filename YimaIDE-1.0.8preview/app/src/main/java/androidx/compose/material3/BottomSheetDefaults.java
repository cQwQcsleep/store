package androidx.compose.material3;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.material3.BottomSheetDefaults;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.ScrimTokens;
import androidx.compose.material3.tokens.SheetBottomTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JA\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u000f2\b\b\u0002\u0010&\u001a\u00020\u000f2\b\b\u0002\u0010'\u001a\u00020\u00052\b\b\u0002\u0010(\u001a\u00020\u000bH\u0007¢\u0006\u0004\b)\u0010*R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u000b8G¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u00020\u000f¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u000b8G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\rR\u0013\u0010\u0015\u001a\u00020\u000f¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0016\u0010\u0011R\u0013\u0010\u0017\u001a\u00020\u000f¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u0019\u001a\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001e\u0010\u0011R\u0016\u0010\u001f\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b \u0010\u0011¨\u0006+"}, d2 = {"Landroidx/compose/material3/BottomSheetDefaults;", "", "<init>", "()V", "HiddenShape", "Landroidx/compose/ui/graphics/Shape;", "getHiddenShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "ExpandedShape", "getExpandedShape", "ContainerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "Elevation", "Landroidx/compose/ui/unit/Dp;", "getElevation-D9Ej5fM", "()F", "F", "ScrimColor", "getScrimColor", "SheetPeekHeight", "getSheetPeekHeight-D9Ej5fM", "SheetMaxWidth", "getSheetMaxWidth-D9Ej5fM", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "PositionalThreshold", "getPositionalThreshold-D9Ej5fM$material3", "VelocityThreshold", "getVelocityThreshold-D9Ej5fM$material3", "DragHandle", "", "modifier", "Landroidx/compose/ui/Modifier;", "width", "height", "shape", "color", "DragHandle-lgZ2HuY", "(Landroidx/compose/ui/Modifier;FFLandroidx/compose/ui/graphics/Shape;JLandroidx/compose/runtime/Composer;II)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BottomSheetDefaults {
    public static final int $stable = 0;
    public static final BottomSheetDefaults INSTANCE = new BottomSheetDefaults();
    private static final float Elevation = SheetBottomTokens.INSTANCE.m2098getDockedModalContainerElevationD9Ej5fM();
    private static final float SheetPeekHeight = Dp.m6022constructorimpl(56.0f);
    private static final float SheetMaxWidth = Dp.m6022constructorimpl(640.0f);
    private static final float PositionalThreshold = Dp.m6022constructorimpl(56.0f);
    private static final float VelocityThreshold = Dp.m6022constructorimpl(125.0f);

    private BottomSheetDefaults() {
    }

    public static Unit a(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
        return Unit.INSTANCE;
    }

    public static Unit b(BottomSheetDefaults bottomSheetDefaults, Modifier modifier, float f, float f2, Shape shape, long j, int i, int i2, Composer composer, int i3) {
        bottomSheetDefaults.m118DragHandlelgZ2HuY(modifier, f, f2, shape, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0181  */
    /* JADX WARN: Code duplicated, block: B:105:0x018f  */
    /* JADX WARN: Code duplicated, block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0048  */
    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:32:0x0059  */
    /* JADX WARN: Code duplicated, block: B:33:0x005c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0063  */
    /* JADX WARN: Code duplicated, block: B:39:0x0067  */
    /* JADX WARN: Code duplicated, block: B:41:0x006f  */
    /* JADX WARN: Code duplicated, block: B:42:0x0072  */
    /* JADX WARN: Code duplicated, block: B:45:0x0078  */
    /* JADX WARN: Code duplicated, block: B:48:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0082  */
    /* JADX WARN: Code duplicated, block: B:52:0x008a  */
    /* JADX WARN: Code duplicated, block: B:53:0x008d  */
    /* JADX WARN: Code duplicated, block: B:56:0x0093  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:60:0x009e  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:76:0x00cb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:77:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:82:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:91:0x010e  */
    /* JADX WARN: Code duplicated, block: B:94:0x0135  */
    /* JADX WARN: Code duplicated, block: B:96:0x013d  */
    /* JADX WARN: Code duplicated, block: B:99:0x0179  */
    /* JADX INFO: renamed from: DragHandle-lgZ2HuY, reason: not valid java name */
    public final void m118DragHandlelgZ2HuY(Modifier modifier, float f, float f2, Shape shape, long j, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        final float fM2097getDockedDragHandleWidthD9Ej5fM;
        int i4;
        float fM2096getDockedDragHandleHeightD9Ej5fM;
        int i5;
        Shape extraLarge;
        long value;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final float f3;
        final float f4;
        final Shape shape2;
        final long j2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final String strM1471getString2EP1pXo;
        boolean zChanged;
        Object objRememberedValue;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1364277227);
        int i6 = i2 & 1;
        if (i6 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        int i7 = i2 & 2;
        if (i7 == 0) {
            if ((i & 48) == 0) {
                fM2097getDockedDragHandleWidthD9Ej5fM = f;
                i3 |= composerStartRestartGroup.changed(fM2097getDockedDragHandleWidthD9Ej5fM) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    fM2096getDockedDragHandleHeightD9Ej5fM = f2;
                    if (composerStartRestartGroup.changed(fM2096getDockedDragHandleHeightD9Ej5fM)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        extraLarge = shape;
                        int i8 = composerStartRestartGroup.changed(extraLarge) ? 2048 : 1024;
                        i3 |= i8;
                    } else {
                        extraLarge = shape;
                    }
                    i3 |= i8;
                } else {
                    extraLarge = shape;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        value = j;
                        int i9 = composerStartRestartGroup.changed(value) ? 16384 : 8192;
                        i3 |= i9;
                    } else {
                        value = j;
                    }
                    i3 |= i9;
                } else {
                    value = j;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i6 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i7 != 0) {
                            fM2097getDockedDragHandleWidthD9Ej5fM = SheetBottomTokens.INSTANCE.m2097getDockedDragHandleWidthD9Ej5fM();
                        }
                        if (i4 != 0) {
                            fM2096getDockedDragHandleHeightD9Ej5fM = SheetBottomTokens.INSTANCE.m2096getDockedDragHandleHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            extraLarge = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6).getExtraLarge();
                        }
                        if ((i2 & 16) != 0) {
                            value = ColorSchemeKt.getValue(SheetBottomTokens.INSTANCE.getDockedDragHandleColor(), composerStartRestartGroup, 6);
                            i3 &= -57345;
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                        }
                        modifier3 = modifier2;
                    }
                    final float f5 = fM2096getDockedDragHandleHeightD9Ej5fM;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1364277227, i3, -1, "androidx.compose.material3.BottomSheetDefaults.DragHandle (SheetDefaults.kt:415)");
                    }
                    Strings.Companion companion = Strings.INSTANCE;
                    strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_drag_handle_description), composerStartRestartGroup, 0);
                    Modifier modifier4 = PaddingKt.padding-VpY3zN4$default(modifier3, 0.0f, SheetDefaultsKt.DragHandleVerticalPadding, 1, (Object) null);
                    zChanged = composerStartRestartGroup.changed(strM1471getString2EP1pXo);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: bz0
                            public final Object invoke(Object obj) {
                                return BottomSheetDefaults.a(strM1471getString2EP1pXo, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    int i10 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(SemanticsModifierKt.semantics$default(modifier4, false, (Function1) objRememberedValue, 1, null), extraLarge, value, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1039573072, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetDefaults$DragHandle$2
                        public final void invoke(Composer composer3, int i11) {
                            if (!composer3.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1039573072, i11, -1, "androidx.compose.material3.BottomSheetDefaults.DragHandle.<anonymous> (SheetDefaults.kt:425)");
                            }
                            BoxKt.Box(SizeKt.size-VpY3zN4(Modifier.INSTANCE, fM2097getDockedDragHandleWidthD9Ej5fM, f5), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 112) | 12582912 | (i10 & 896), 120);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f3 = f5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    f3 = fM2096getDockedDragHandleHeightD9Ej5fM;
                }
                f4 = fM2097getDockedDragHandleWidthD9Ej5fM;
                shape2 = extraLarge;
                j2 = value;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cz0
                        public final Object invoke(Object obj, Object obj2) {
                            return BottomSheetDefaults.b(this.b, modifier3, f4, f3, shape2, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            fM2096getDockedDragHandleHeightD9Ej5fM = f2;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    extraLarge = shape;
                    if (composerStartRestartGroup.changed(extraLarge)) {
                    }
                    i3 |= i8;
                } else {
                    extraLarge = shape;
                }
                i3 |= i8;
            } else {
                extraLarge = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    value = j;
                    if (composerStartRestartGroup.changed(value)) {
                    }
                    i3 |= i9;
                } else {
                    value = j;
                }
                i3 |= i9;
            } else {
                value = j;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i7 != 0) {
                        fM2097getDockedDragHandleWidthD9Ej5fM = SheetBottomTokens.INSTANCE.m2097getDockedDragHandleWidthD9Ej5fM();
                    }
                    if (i4 != 0) {
                        fM2096getDockedDragHandleHeightD9Ej5fM = SheetBottomTokens.INSTANCE.m2096getDockedDragHandleHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        extraLarge = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6).getExtraLarge();
                    }
                    if ((i2 & 16) != 0) {
                        value = ColorSchemeKt.getValue(SheetBottomTokens.INSTANCE.getDockedDragHandleColor(), composerStartRestartGroup, 6);
                        i3 &= -57345;
                    }
                } else {
                    if (i6 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i7 != 0) {
                        fM2097getDockedDragHandleWidthD9Ej5fM = SheetBottomTokens.INSTANCE.m2097getDockedDragHandleWidthD9Ej5fM();
                    }
                    if (i4 != 0) {
                        fM2096getDockedDragHandleHeightD9Ej5fM = SheetBottomTokens.INSTANCE.m2096getDockedDragHandleHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        extraLarge = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6).getExtraLarge();
                    }
                    if ((i2 & 16) != 0) {
                        value = ColorSchemeKt.getValue(SheetBottomTokens.INSTANCE.getDockedDragHandleColor(), composerStartRestartGroup, 6);
                        i3 &= -57345;
                    }
                }
                final float f6 = fM2096getDockedDragHandleHeightD9Ej5fM;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1364277227, i3, -1, "androidx.compose.material3.BottomSheetDefaults.DragHandle (SheetDefaults.kt:415)");
                }
                Strings.Companion companion2 = Strings.INSTANCE;
                strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_drag_handle_description), composerStartRestartGroup, 0);
                Modifier modifier5 = PaddingKt.padding-VpY3zN4$default(modifier3, 0.0f, SheetDefaultsKt.DragHandleVerticalPadding, 1, (Object) null);
                zChanged = composerStartRestartGroup.changed(strM1471getString2EP1pXo);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue = new Function1() { // from class: bz0
                        public final Object invoke(Object obj) {
                            return BottomSheetDefaults.a(strM1471getString2EP1pXo, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function1() { // from class: bz0
                        public final Object invoke(Object obj) {
                            return BottomSheetDefaults.a(strM1471getString2EP1pXo, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                int i11 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null), extraLarge, value, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1039573072, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetDefaults$DragHandle$2
                    public final void invoke(Composer composer3, int i12) {
                        if (!composer3.shouldExecute((i12 & 3) != 2, i12 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1039573072, i12, -1, "androidx.compose.material3.BottomSheetDefaults.DragHandle.<anonymous> (SheetDefaults.kt:425)");
                        }
                        BoxKt.Box(SizeKt.size-VpY3zN4(Modifier.INSTANCE, fM2097getDockedDragHandleWidthD9Ej5fM, f6), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i11 & 112) | 12582912 | (i11 & 896), 120);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f3 = f6;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                f3 = fM2096getDockedDragHandleHeightD9Ej5fM;
            }
            f4 = fM2097getDockedDragHandleWidthD9Ej5fM;
            shape2 = extraLarge;
            j2 = value;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cz0
                    public final Object invoke(Object obj, Object obj2) {
                        return BottomSheetDefaults.b(this.b, modifier3, f4, f3, shape2, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        fM2097getDockedDragHandleWidthD9Ej5fM = f;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                fM2096getDockedDragHandleHeightD9Ej5fM = f2;
                if (composerStartRestartGroup.changed(fM2096getDockedDragHandleHeightD9Ej5fM)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    extraLarge = shape;
                    if (composerStartRestartGroup.changed(extraLarge)) {
                    }
                    i3 |= i8;
                } else {
                    extraLarge = shape;
                }
                i3 |= i8;
            } else {
                extraLarge = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    value = j;
                    if (composerStartRestartGroup.changed(value)) {
                    }
                    i3 |= i9;
                } else {
                    value = j;
                }
                i3 |= i9;
            } else {
                value = j;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i7 != 0) {
                        fM2097getDockedDragHandleWidthD9Ej5fM = SheetBottomTokens.INSTANCE.m2097getDockedDragHandleWidthD9Ej5fM();
                    }
                    if (i4 != 0) {
                        fM2096getDockedDragHandleHeightD9Ej5fM = SheetBottomTokens.INSTANCE.m2096getDockedDragHandleHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        extraLarge = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6).getExtraLarge();
                    }
                    if ((i2 & 16) != 0) {
                        value = ColorSchemeKt.getValue(SheetBottomTokens.INSTANCE.getDockedDragHandleColor(), composerStartRestartGroup, 6);
                        i3 &= -57345;
                    }
                } else {
                    if (i6 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i7 != 0) {
                        fM2097getDockedDragHandleWidthD9Ej5fM = SheetBottomTokens.INSTANCE.m2097getDockedDragHandleWidthD9Ej5fM();
                    }
                    if (i4 != 0) {
                        fM2096getDockedDragHandleHeightD9Ej5fM = SheetBottomTokens.INSTANCE.m2096getDockedDragHandleHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        extraLarge = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6).getExtraLarge();
                    }
                    if ((i2 & 16) != 0) {
                        value = ColorSchemeKt.getValue(SheetBottomTokens.INSTANCE.getDockedDragHandleColor(), composerStartRestartGroup, 6);
                        i3 &= -57345;
                    }
                }
                final float f7 = fM2096getDockedDragHandleHeightD9Ej5fM;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1364277227, i3, -1, "androidx.compose.material3.BottomSheetDefaults.DragHandle (SheetDefaults.kt:415)");
                }
                Strings.Companion companion3 = Strings.INSTANCE;
                strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_drag_handle_description), composerStartRestartGroup, 0);
                Modifier modifier6 = PaddingKt.padding-VpY3zN4$default(modifier3, 0.0f, SheetDefaultsKt.DragHandleVerticalPadding, 1, (Object) null);
                zChanged = composerStartRestartGroup.changed(strM1471getString2EP1pXo);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue = new Function1() { // from class: bz0
                        public final Object invoke(Object obj) {
                            return BottomSheetDefaults.a(strM1471getString2EP1pXo, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function1() { // from class: bz0
                        public final Object invoke(Object obj) {
                            return BottomSheetDefaults.a(strM1471getString2EP1pXo, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                int i12 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(SemanticsModifierKt.semantics$default(modifier6, false, (Function1) objRememberedValue, 1, null), extraLarge, value, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1039573072, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetDefaults$DragHandle$2
                    public final void invoke(Composer composer3, int i13) {
                        if (!composer3.shouldExecute((i13 & 3) != 2, i13 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1039573072, i13, -1, "androidx.compose.material3.BottomSheetDefaults.DragHandle.<anonymous> (SheetDefaults.kt:425)");
                        }
                        BoxKt.Box(SizeKt.size-VpY3zN4(Modifier.INSTANCE, fM2097getDockedDragHandleWidthD9Ej5fM, f7), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i12 & 112) | 12582912 | (i12 & 896), 120);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f3 = f7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                f3 = fM2096getDockedDragHandleHeightD9Ej5fM;
            }
            f4 = fM2097getDockedDragHandleWidthD9Ej5fM;
            shape2 = extraLarge;
            j2 = value;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cz0
                    public final Object invoke(Object obj, Object obj2) {
                        return BottomSheetDefaults.b(this.b, modifier3, f4, f3, shape2, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        fM2096getDockedDragHandleHeightD9Ej5fM = f2;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                extraLarge = shape;
                if (composerStartRestartGroup.changed(extraLarge)) {
                }
                i3 |= i8;
            } else {
                extraLarge = shape;
            }
            i3 |= i8;
        } else {
            extraLarge = shape;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                value = j;
                if (composerStartRestartGroup.changed(value)) {
                }
                i3 |= i9;
            } else {
                value = j;
            }
            i3 |= i9;
        } else {
            value = j;
        }
        if ((i3 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i6 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i7 != 0) {
                    fM2097getDockedDragHandleWidthD9Ej5fM = SheetBottomTokens.INSTANCE.m2097getDockedDragHandleWidthD9Ej5fM();
                }
                if (i4 != 0) {
                    fM2096getDockedDragHandleHeightD9Ej5fM = SheetBottomTokens.INSTANCE.m2096getDockedDragHandleHeightD9Ej5fM();
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    extraLarge = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6).getExtraLarge();
                }
                if ((i2 & 16) != 0) {
                    value = ColorSchemeKt.getValue(SheetBottomTokens.INSTANCE.getDockedDragHandleColor(), composerStartRestartGroup, 6);
                    i3 &= -57345;
                }
            } else {
                if (i6 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i7 != 0) {
                    fM2097getDockedDragHandleWidthD9Ej5fM = SheetBottomTokens.INSTANCE.m2097getDockedDragHandleWidthD9Ej5fM();
                }
                if (i4 != 0) {
                    fM2096getDockedDragHandleHeightD9Ej5fM = SheetBottomTokens.INSTANCE.m2096getDockedDragHandleHeightD9Ej5fM();
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    extraLarge = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6).getExtraLarge();
                }
                if ((i2 & 16) != 0) {
                    value = ColorSchemeKt.getValue(SheetBottomTokens.INSTANCE.getDockedDragHandleColor(), composerStartRestartGroup, 6);
                    i3 &= -57345;
                }
            }
            final float f8 = fM2096getDockedDragHandleHeightD9Ej5fM;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1364277227, i3, -1, "androidx.compose.material3.BottomSheetDefaults.DragHandle (SheetDefaults.kt:415)");
            }
            Strings.Companion companion4 = Strings.INSTANCE;
            strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_drag_handle_description), composerStartRestartGroup, 0);
            Modifier modifier7 = PaddingKt.padding-VpY3zN4$default(modifier3, 0.0f, SheetDefaultsKt.DragHandleVerticalPadding, 1, (Object) null);
            zChanged = composerStartRestartGroup.changed(strM1471getString2EP1pXo);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue = new Function1() { // from class: bz0
                    public final Object invoke(Object obj) {
                        return BottomSheetDefaults.a(strM1471getString2EP1pXo, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function1() { // from class: bz0
                    public final Object invoke(Object obj) {
                        return BottomSheetDefaults.a(strM1471getString2EP1pXo, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            int i13 = i3 >> 6;
            composer2 = composerStartRestartGroup;
            SurfaceKt.m954SurfaceT9BRK9s(SemanticsModifierKt.semantics$default(modifier7, false, (Function1) objRememberedValue, 1, null), extraLarge, value, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1039573072, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetDefaults$DragHandle$2
                public final void invoke(Composer composer3, int i14) {
                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1039573072, i14, -1, "androidx.compose.material3.BottomSheetDefaults.DragHandle.<anonymous> (SheetDefaults.kt:425)");
                    }
                    BoxKt.Box(SizeKt.size-VpY3zN4(Modifier.INSTANCE, fM2097getDockedDragHandleWidthD9Ej5fM, f8), composer3, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composer2, (i13 & 112) | 12582912 | (i13 & 896), 120);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f3 = f8;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            f3 = fM2096getDockedDragHandleHeightD9Ej5fM;
        }
        f4 = fM2097getDockedDragHandleWidthD9Ej5fM;
        shape2 = extraLarge;
        j2 = value;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cz0
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetDefaults.b(this.b, modifier3, f4, f3, shape2, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final long getContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(433375448, i, -1, "androidx.compose.material3.BottomSheetDefaults.<get-ContainerColor> (SheetDefaults.kt:383)");
        }
        long value = ColorSchemeKt.getValue(SheetBottomTokens.INSTANCE.getDockedContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m119getElevationD9Ej5fM() {
        return Elevation;
    }

    public final Shape getExpandedShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1683783414, i, -1, "androidx.compose.material3.BottomSheetDefaults.<get-ExpandedShape> (SheetDefaults.kt:379)");
        }
        Shape value = ShapesKt.getValue(SheetBottomTokens.INSTANCE.getDockedContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final Shape getHiddenShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1971658024, i, -1, "androidx.compose.material3.BottomSheetDefaults.<get-HiddenShape> (SheetDefaults.kt:375)");
        }
        Shape value = ShapesKt.getValue(SheetBottomTokens.INSTANCE.getDockedMinimizedContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: getPositionalThreshold-D9Ej5fM$material3, reason: not valid java name */
    public final float m120getPositionalThresholdD9Ej5fM$material3() {
        return PositionalThreshold;
    }

    public final long getScrimColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2040719176, i, -1, "androidx.compose.material3.BottomSheetDefaults.<get-ScrimColor> (SheetDefaults.kt:390)");
        }
        long jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(ColorSchemeKt.getValue(ScrimTokens.INSTANCE.getContainerColor(), composer, 6), 0.32f, 0.0f, 0.0f, 0.0f, 14, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jM3133copywmQWz5c$default;
    }

    /* JADX INFO: renamed from: getSheetMaxWidth-D9Ej5fM, reason: not valid java name */
    public final float m121getSheetMaxWidthD9Ej5fM() {
        return SheetMaxWidth;
    }

    /* JADX INFO: renamed from: getSheetPeekHeight-D9Ej5fM, reason: not valid java name */
    public final float m122getSheetPeekHeightD9Ej5fM() {
        return SheetPeekHeight;
    }

    /* JADX INFO: renamed from: getVelocityThreshold-D9Ej5fM$material3, reason: not valid java name */
    public final float m123getVelocityThresholdD9Ej5fM$material3() {
        return VelocityThreshold;
    }

    public final WindowInsets getWindowInsets(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-511309409, i, -1, "androidx.compose.material3.BottomSheetDefaults.<get-windowInsets> (SheetDefaults.kt:401)");
        }
        WindowInsets safeDrawing = WindowInsets_androidKt.getSafeDrawing(WindowInsets.Companion, composer, 6);
        WindowInsetsSides.Companion companion = WindowInsetsSides.Companion;
        WindowInsets windowInsets = WindowInsetsKt.only-bOOhFvg(safeDrawing, WindowInsetsSides.plus-gK_yJZ4(companion.getBottom-JoeWqyM(), companion.getTop-JoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return windowInsets;
    }
}
