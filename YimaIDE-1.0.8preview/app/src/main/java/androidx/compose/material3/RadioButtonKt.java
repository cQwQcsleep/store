package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.RadioButtonKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.RadioButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aO\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0002\u0010\r\"\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010\"\u0010\u0010\u0011\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010\"\u0010\u0010\u0012\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0013"}, d2 = {"RadioButton", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/RadioButtonColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/RadioButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "RadioButtonPadding", "Landroidx/compose/ui/unit/Dp;", "F", "RadioButtonDotSize", "RadioStrokeWidth", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RadioButtonKt {
    private static final float RadioButtonPadding = Dp.m6022constructorimpl(2.0f);
    private static final float RadioButtonDotSize = Dp.m6022constructorimpl(12.0f);
    private static final float RadioStrokeWidth = Dp.m6022constructorimpl(2.0f);

    /* JADX WARN: Code duplicated, block: B:100:0x011a  */
    /* JADX WARN: Code duplicated, block: B:103:0x014a  */
    /* JADX WARN: Code duplicated, block: B:104:0x017b  */
    /* JADX WARN: Code duplicated, block: B:106:0x0182  */
    /* JADX WARN: Code duplicated, block: B:107:0x0189  */
    /* JADX WARN: Code duplicated, block: B:110:0x01be  */
    /* JADX WARN: Code duplicated, block: B:112:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:115:0x01da  */
    /* JADX WARN: Code duplicated, block: B:117:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:120:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:122:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x005c  */
    /* JADX WARN: Code duplicated, block: B:38:0x0061  */
    /* JADX WARN: Code duplicated, block: B:40:0x0065  */
    /* JADX WARN: Code duplicated, block: B:42:0x006d  */
    /* JADX WARN: Code duplicated, block: B:43:0x0070  */
    /* JADX WARN: Code duplicated, block: B:47:0x0077  */
    /* JADX WARN: Code duplicated, block: B:49:0x007b  */
    /* JADX WARN: Code duplicated, block: B:51:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x0086  */
    /* JADX WARN: Code duplicated, block: B:55:0x008c  */
    /* JADX WARN: Code duplicated, block: B:58:0x0094  */
    /* JADX WARN: Code duplicated, block: B:60:0x0098  */
    /* JADX WARN: Code duplicated, block: B:62:0x009b  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:65:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e0 A[PHI: r3 r4 r5 r6
      0x00e0: PHI (r3v33 boolean) = (r3v22 boolean), (r3v35 boolean) binds: [B:92:0x00fc, B:81:0x00dc] A[DONT_GENERATE, DONT_INLINE]
      0x00e0: PHI (r4v16 androidx.compose.ui.Modifier) = (r4v10 androidx.compose.ui.Modifier), (r4v18 androidx.compose.ui.Modifier) binds: [B:92:0x00fc, B:81:0x00dc] A[DONT_GENERATE, DONT_INLINE]
      0x00e0: PHI (r5v13 androidx.compose.material3.RadioButtonColors) = (r5v6 androidx.compose.material3.RadioButtonColors), (r5v14 androidx.compose.material3.RadioButtonColors) binds: [B:92:0x00fc, B:81:0x00dc] A[DONT_GENERATE, DONT_INLINE]
      0x00e0: PHI (r6v16 int) = (r6v7 int), (r6v17 int) binds: [B:92:0x00fc, B:81:0x00dc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:83:0x00e2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:84:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:90:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:93:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:96:0x0108  */
    /* JADX WARN: Code duplicated, block: B:99:0x0112  */
    public static final void RadioButton(final boolean z, final Function0<Unit> function0, Modifier modifier, boolean z2, RadioButtonColors radioButtonColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z3;
        int i5;
        RadioButtonColors radioButtonColorsColors;
        int i6;
        MutableInteractionSource mutableInteractionSource2;
        int i7;
        boolean z4;
        final Modifier modifier3;
        final boolean z5;
        final RadioButtonColors radioButtonColors2;
        final MutableInteractionSource mutableInteractionSource3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int i8;
        boolean z6;
        RadioButtonColors radioButtonColors3;
        MutableInteractionSource mutableInteractionSource4;
        float fM6022constructorimpl;
        final State state;
        final State<Color> stateRadioColor$material3;
        Modifier modifier5;
        Modifier modifierMinimumInteractiveComponentSize;
        boolean zChanged;
        Object objRememberedValue;
        Composer composerStartRestartGroup = composer.startRestartGroup(408580840);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        int i9 = i2 & 4;
        if (i9 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z3 = z2;
                    if (composerStartRestartGroup.changed(z3)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        radioButtonColorsColors = radioButtonColors;
                        int i10 = composerStartRestartGroup.changed(radioButtonColorsColors) ? 16384 : 8192;
                        i3 |= i10;
                    } else {
                        radioButtonColorsColors = radioButtonColors;
                    }
                    i3 |= i10;
                } else {
                    radioButtonColorsColors = radioButtonColors;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((74899 & i3) != 74898) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i9 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i8 = i3;
                            z6 = z3;
                            radioButtonColors3 = radioButtonColorsColors;
                            if (i6 != 0) {
                                mutableInteractionSource4 = null;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:80)");
                            }
                            if (z) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(RadioButtonDotSize / 2.0f);
                            } else {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            }
                            state = AnimateAsStateKt.animateDpAsState-AjpBEmI(fM6022constructorimpl, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
                            stateRadioColor$material3 = radioButtonColors3.radioColor$material3(z6, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                            if (function0 != null) {
                                z5 = z6;
                                modifier5 = SelectableKt.selectable-O2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, Dp.m6022constructorimpl(RadioButtonTokens.INSTANCE.m2084getStateLayerSizeD9Ej5fM() / 2.0f), 0L, 4, null), z5, Role.m5238boximpl(Role.INSTANCE.m5250getRadioButtono7Vup1c()), function0);
                            } else {
                                z5 = z6;
                                modifier5 = Modifier.INSTANCE;
                            }
                            if (function0 != null) {
                                modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                            } else {
                                modifierMinimumInteractiveComponentSize = Modifier.INSTANCE;
                            }
                            Modifier modifier6 = SizeKt.requiredSize-3ABfNKs(PaddingKt.padding-3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(modifierMinimumInteractiveComponentSize).then(modifier5), Alignment.INSTANCE.getCenter(), false, 2, (Object) null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m2083getIconSizeD9Ej5fM());
                            zChanged = composerStartRestartGroup.changed(stateRadioColor$material3) | composerStartRestartGroup.changed(state);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: y5c
                                    public final Object invoke(Object obj) {
                                        return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            CanvasKt.Canvas(modifier6, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            mutableInteractionSource3 = mutableInteractionSource4;
                            radioButtonColors2 = radioButtonColors3;
                            modifier3 = modifier4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            i8 = i3;
                            modifier4 = modifier2;
                            z6 = z3;
                            radioButtonColors3 = radioButtonColorsColors;
                        }
                        mutableInteractionSource4 = mutableInteractionSource2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:80)");
                        }
                        if (z) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(RadioButtonDotSize / 2.0f);
                        } else {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        state = AnimateAsStateKt.animateDpAsState-AjpBEmI(fM6022constructorimpl, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
                        stateRadioColor$material3 = radioButtonColors3.radioColor$material3(z6, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                        if (function0 != null) {
                            z5 = z6;
                            modifier5 = SelectableKt.selectable-O2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, Dp.m6022constructorimpl(RadioButtonTokens.INSTANCE.m2084getStateLayerSizeD9Ej5fM() / 2.0f), 0L, 4, null), z5, Role.m5238boximpl(Role.INSTANCE.m5250getRadioButtono7Vup1c()), function0);
                        } else {
                            z5 = z6;
                            modifier5 = Modifier.INSTANCE;
                        }
                        if (function0 != null) {
                            modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                        } else {
                            modifierMinimumInteractiveComponentSize = Modifier.INSTANCE;
                        }
                        Modifier modifier7 = SizeKt.requiredSize-3ABfNKs(PaddingKt.padding-3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(modifierMinimumInteractiveComponentSize).then(modifier5), Alignment.INSTANCE.getCenter(), false, 2, (Object) null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m2083getIconSizeD9Ej5fM());
                        zChanged = composerStartRestartGroup.changed(stateRadioColor$material3) | composerStartRestartGroup.changed(state);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new Function1() { // from class: y5c
                                public final Object invoke(Object obj) {
                                    return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new Function1() { // from class: y5c
                                public final Object invoke(Object obj) {
                                    return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        CanvasKt.Canvas(modifier7, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource3 = mutableInteractionSource4;
                        radioButtonColors2 = radioButtonColors3;
                        modifier3 = modifier4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z5 = z3;
                        radioButtonColors2 = radioButtonColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z5c
                            public final Object invoke(Object obj, Object obj2) {
                                return RadioButtonKt.b(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((74899 & i3) != 74898) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z6 = z3;
                        radioButtonColors3 = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z6 = z3;
                        radioButtonColors3 = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:80)");
                    }
                    if (z) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(RadioButtonDotSize / 2.0f);
                    } else {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    state = AnimateAsStateKt.animateDpAsState-AjpBEmI(fM6022constructorimpl, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
                    stateRadioColor$material3 = radioButtonColors3.radioColor$material3(z6, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                    if (function0 != null) {
                        z5 = z6;
                        modifier5 = SelectableKt.selectable-O2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, Dp.m6022constructorimpl(RadioButtonTokens.INSTANCE.m2084getStateLayerSizeD9Ej5fM() / 2.0f), 0L, 4, null), z5, Role.m5238boximpl(Role.INSTANCE.m5250getRadioButtono7Vup1c()), function0);
                    } else {
                        z5 = z6;
                        modifier5 = Modifier.INSTANCE;
                    }
                    if (function0 != null) {
                        modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        modifierMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    Modifier modifier8 = SizeKt.requiredSize-3ABfNKs(PaddingKt.padding-3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(modifierMinimumInteractiveComponentSize).then(modifier5), Alignment.INSTANCE.getCenter(), false, 2, (Object) null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m2083getIconSizeD9Ej5fM());
                    zChanged = composerStartRestartGroup.changed(stateRadioColor$material3) | composerStartRestartGroup.changed(state);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new Function1() { // from class: y5c
                            public final Object invoke(Object obj) {
                                return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: y5c
                            public final Object invoke(Object obj) {
                                return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    CanvasKt.Canvas(modifier8, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource3 = mutableInteractionSource4;
                    radioButtonColors2 = radioButtonColors3;
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z5 = z3;
                    radioButtonColors2 = radioButtonColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z5c
                        public final Object invoke(Object obj, Object obj2) {
                            return RadioButtonKt.b(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z3 = z2;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    radioButtonColorsColors = radioButtonColors;
                    if (composerStartRestartGroup.changed(radioButtonColorsColors)) {
                    }
                    i3 |= i10;
                } else {
                    radioButtonColorsColors = radioButtonColors;
                }
                i3 |= i10;
            } else {
                radioButtonColorsColors = radioButtonColors;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((74899 & i3) != 74898) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z6 = z3;
                        radioButtonColors3 = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z6 = z3;
                        radioButtonColors3 = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:80)");
                    }
                    if (z) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(RadioButtonDotSize / 2.0f);
                    } else {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    state = AnimateAsStateKt.animateDpAsState-AjpBEmI(fM6022constructorimpl, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
                    stateRadioColor$material3 = radioButtonColors3.radioColor$material3(z6, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                    if (function0 != null) {
                        z5 = z6;
                        modifier5 = SelectableKt.selectable-O2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, Dp.m6022constructorimpl(RadioButtonTokens.INSTANCE.m2084getStateLayerSizeD9Ej5fM() / 2.0f), 0L, 4, null), z5, Role.m5238boximpl(Role.INSTANCE.m5250getRadioButtono7Vup1c()), function0);
                    } else {
                        z5 = z6;
                        modifier5 = Modifier.INSTANCE;
                    }
                    if (function0 != null) {
                        modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        modifierMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    Modifier modifier9 = SizeKt.requiredSize-3ABfNKs(PaddingKt.padding-3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(modifierMinimumInteractiveComponentSize).then(modifier5), Alignment.INSTANCE.getCenter(), false, 2, (Object) null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m2083getIconSizeD9Ej5fM());
                    zChanged = composerStartRestartGroup.changed(stateRadioColor$material3) | composerStartRestartGroup.changed(state);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new Function1() { // from class: y5c
                            public final Object invoke(Object obj) {
                                return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: y5c
                            public final Object invoke(Object obj) {
                                return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    CanvasKt.Canvas(modifier9, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource3 = mutableInteractionSource4;
                    radioButtonColors2 = radioButtonColors3;
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z5 = z3;
                    radioButtonColors2 = radioButtonColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z5c
                        public final Object invoke(Object obj, Object obj2) {
                            return RadioButtonKt.b(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((74899 & i3) != 74898) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z6 = z3;
                    radioButtonColors3 = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z6 = z3;
                    radioButtonColors3 = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:80)");
                }
                if (z) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(RadioButtonDotSize / 2.0f);
                } else {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                }
                state = AnimateAsStateKt.animateDpAsState-AjpBEmI(fM6022constructorimpl, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
                stateRadioColor$material3 = radioButtonColors3.radioColor$material3(z6, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                if (function0 != null) {
                    z5 = z6;
                    modifier5 = SelectableKt.selectable-O2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, Dp.m6022constructorimpl(RadioButtonTokens.INSTANCE.m2084getStateLayerSizeD9Ej5fM() / 2.0f), 0L, 4, null), z5, Role.m5238boximpl(Role.INSTANCE.m5250getRadioButtono7Vup1c()), function0);
                } else {
                    z5 = z6;
                    modifier5 = Modifier.INSTANCE;
                }
                if (function0 != null) {
                    modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    modifierMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifier10 = SizeKt.requiredSize-3ABfNKs(PaddingKt.padding-3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(modifierMinimumInteractiveComponentSize).then(modifier5), Alignment.INSTANCE.getCenter(), false, 2, (Object) null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m2083getIconSizeD9Ej5fM());
                zChanged = composerStartRestartGroup.changed(stateRadioColor$material3) | composerStartRestartGroup.changed(state);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue = new Function1() { // from class: y5c
                        public final Object invoke(Object obj) {
                            return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function1() { // from class: y5c
                        public final Object invoke(Object obj) {
                            return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CanvasKt.Canvas(modifier10, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource3 = mutableInteractionSource4;
                radioButtonColors2 = radioButtonColors3;
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z5 = z3;
                radioButtonColors2 = radioButtonColorsColors;
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z5c
                    public final Object invoke(Object obj, Object obj2) {
                        return RadioButtonKt.b(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z3 = z2;
                if (composerStartRestartGroup.changed(z3)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    radioButtonColorsColors = radioButtonColors;
                    if (composerStartRestartGroup.changed(radioButtonColorsColors)) {
                    }
                    i3 |= i10;
                } else {
                    radioButtonColorsColors = radioButtonColors;
                }
                i3 |= i10;
            } else {
                radioButtonColorsColors = radioButtonColors;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((74899 & i3) != 74898) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z6 = z3;
                        radioButtonColors3 = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z6 = z3;
                        radioButtonColors3 = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:80)");
                    }
                    if (z) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(RadioButtonDotSize / 2.0f);
                    } else {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    state = AnimateAsStateKt.animateDpAsState-AjpBEmI(fM6022constructorimpl, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
                    stateRadioColor$material3 = radioButtonColors3.radioColor$material3(z6, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                    if (function0 != null) {
                        z5 = z6;
                        modifier5 = SelectableKt.selectable-O2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, Dp.m6022constructorimpl(RadioButtonTokens.INSTANCE.m2084getStateLayerSizeD9Ej5fM() / 2.0f), 0L, 4, null), z5, Role.m5238boximpl(Role.INSTANCE.m5250getRadioButtono7Vup1c()), function0);
                    } else {
                        z5 = z6;
                        modifier5 = Modifier.INSTANCE;
                    }
                    if (function0 != null) {
                        modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        modifierMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    Modifier modifier11 = SizeKt.requiredSize-3ABfNKs(PaddingKt.padding-3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(modifierMinimumInteractiveComponentSize).then(modifier5), Alignment.INSTANCE.getCenter(), false, 2, (Object) null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m2083getIconSizeD9Ej5fM());
                    zChanged = composerStartRestartGroup.changed(stateRadioColor$material3) | composerStartRestartGroup.changed(state);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new Function1() { // from class: y5c
                            public final Object invoke(Object obj) {
                                return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: y5c
                            public final Object invoke(Object obj) {
                                return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    CanvasKt.Canvas(modifier11, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource3 = mutableInteractionSource4;
                    radioButtonColors2 = radioButtonColors3;
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z5 = z3;
                    radioButtonColors2 = radioButtonColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z5c
                        public final Object invoke(Object obj, Object obj2) {
                            return RadioButtonKt.b(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((74899 & i3) != 74898) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z6 = z3;
                    radioButtonColors3 = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z6 = z3;
                    radioButtonColors3 = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:80)");
                }
                if (z) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(RadioButtonDotSize / 2.0f);
                } else {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                }
                state = AnimateAsStateKt.animateDpAsState-AjpBEmI(fM6022constructorimpl, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
                stateRadioColor$material3 = radioButtonColors3.radioColor$material3(z6, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                if (function0 != null) {
                    z5 = z6;
                    modifier5 = SelectableKt.selectable-O2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, Dp.m6022constructorimpl(RadioButtonTokens.INSTANCE.m2084getStateLayerSizeD9Ej5fM() / 2.0f), 0L, 4, null), z5, Role.m5238boximpl(Role.INSTANCE.m5250getRadioButtono7Vup1c()), function0);
                } else {
                    z5 = z6;
                    modifier5 = Modifier.INSTANCE;
                }
                if (function0 != null) {
                    modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    modifierMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifier12 = SizeKt.requiredSize-3ABfNKs(PaddingKt.padding-3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(modifierMinimumInteractiveComponentSize).then(modifier5), Alignment.INSTANCE.getCenter(), false, 2, (Object) null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m2083getIconSizeD9Ej5fM());
                zChanged = composerStartRestartGroup.changed(stateRadioColor$material3) | composerStartRestartGroup.changed(state);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue = new Function1() { // from class: y5c
                        public final Object invoke(Object obj) {
                            return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function1() { // from class: y5c
                        public final Object invoke(Object obj) {
                            return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CanvasKt.Canvas(modifier12, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource3 = mutableInteractionSource4;
                radioButtonColors2 = radioButtonColors3;
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z5 = z3;
                radioButtonColors2 = radioButtonColorsColors;
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z5c
                    public final Object invoke(Object obj, Object obj2) {
                        return RadioButtonKt.b(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z3 = z2;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                radioButtonColorsColors = radioButtonColors;
                if (composerStartRestartGroup.changed(radioButtonColorsColors)) {
                }
                i3 |= i10;
            } else {
                radioButtonColorsColors = radioButtonColors;
            }
            i3 |= i10;
        } else {
            radioButtonColorsColors = radioButtonColors;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((74899 & i3) != 74898) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z6 = z3;
                    radioButtonColors3 = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z6 = z3;
                    radioButtonColors3 = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:80)");
                }
                if (z) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(RadioButtonDotSize / 2.0f);
                } else {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                }
                state = AnimateAsStateKt.animateDpAsState-AjpBEmI(fM6022constructorimpl, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
                stateRadioColor$material3 = radioButtonColors3.radioColor$material3(z6, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                if (function0 != null) {
                    z5 = z6;
                    modifier5 = SelectableKt.selectable-O2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, Dp.m6022constructorimpl(RadioButtonTokens.INSTANCE.m2084getStateLayerSizeD9Ej5fM() / 2.0f), 0L, 4, null), z5, Role.m5238boximpl(Role.INSTANCE.m5250getRadioButtono7Vup1c()), function0);
                } else {
                    z5 = z6;
                    modifier5 = Modifier.INSTANCE;
                }
                if (function0 != null) {
                    modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    modifierMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifier13 = SizeKt.requiredSize-3ABfNKs(PaddingKt.padding-3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(modifierMinimumInteractiveComponentSize).then(modifier5), Alignment.INSTANCE.getCenter(), false, 2, (Object) null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m2083getIconSizeD9Ej5fM());
                zChanged = composerStartRestartGroup.changed(stateRadioColor$material3) | composerStartRestartGroup.changed(state);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue = new Function1() { // from class: y5c
                        public final Object invoke(Object obj) {
                            return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function1() { // from class: y5c
                        public final Object invoke(Object obj) {
                            return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CanvasKt.Canvas(modifier13, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource3 = mutableInteractionSource4;
                radioButtonColors2 = radioButtonColors3;
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z5 = z3;
                radioButtonColors2 = radioButtonColorsColors;
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z5c
                    public final Object invoke(Object obj, Object obj2) {
                        return RadioButtonKt.b(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((74899 & i3) != 74898) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    z3 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                i8 = i3;
                z6 = z3;
                radioButtonColors3 = radioButtonColorsColors;
                if (i6 != 0) {
                    mutableInteractionSource4 = null;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource2;
                }
            } else {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    z3 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                i8 = i3;
                z6 = z3;
                radioButtonColors3 = radioButtonColorsColors;
                if (i6 != 0) {
                    mutableInteractionSource4 = null;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:80)");
            }
            if (z) {
                fM6022constructorimpl = Dp.m6022constructorimpl(RadioButtonDotSize / 2.0f);
            } else {
                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
            }
            state = AnimateAsStateKt.animateDpAsState-AjpBEmI(fM6022constructorimpl, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
            stateRadioColor$material3 = radioButtonColors3.radioColor$material3(z6, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
            if (function0 != null) {
                z5 = z6;
                modifier5 = SelectableKt.selectable-O2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, Dp.m6022constructorimpl(RadioButtonTokens.INSTANCE.m2084getStateLayerSizeD9Ej5fM() / 2.0f), 0L, 4, null), z5, Role.m5238boximpl(Role.INSTANCE.m5250getRadioButtono7Vup1c()), function0);
            } else {
                z5 = z6;
                modifier5 = Modifier.INSTANCE;
            }
            if (function0 != null) {
                modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
            } else {
                modifierMinimumInteractiveComponentSize = Modifier.INSTANCE;
            }
            Modifier modifier14 = SizeKt.requiredSize-3ABfNKs(PaddingKt.padding-3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(modifierMinimumInteractiveComponentSize).then(modifier5), Alignment.INSTANCE.getCenter(), false, 2, (Object) null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m2083getIconSizeD9Ej5fM());
            zChanged = composerStartRestartGroup.changed(stateRadioColor$material3) | composerStartRestartGroup.changed(state);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue = new Function1() { // from class: y5c
                    public final Object invoke(Object obj) {
                        return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function1() { // from class: y5c
                    public final Object invoke(Object obj) {
                        return RadioButtonKt.a(stateRadioColor$material3, state, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifier14, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            mutableInteractionSource3 = mutableInteractionSource4;
            radioButtonColors2 = radioButtonColors3;
            modifier3 = modifier4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            z5 = z3;
            radioButtonColors2 = radioButtonColorsColors;
            mutableInteractionSource3 = mutableInteractionSource2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z5c
                public final Object invoke(Object obj, Object obj2) {
                    return RadioButtonKt.b(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(State state, State state2, DrawScope drawScope) {
        float fMo4557toPx0680j_4 = drawScope.mo4557toPx0680j_4(RadioStrokeWidth);
        float f = fMo4557toPx0680j_4 / 2.0f;
        DrawScope.m3689drawCircleVaOC9Bg$default(drawScope, ((Color) state.getValue()).m3144unboximpl(), drawScope.mo4557toPx0680j_4(Dp.m6022constructorimpl(RadioButtonTokens.INSTANCE.m2083getIconSizeD9Ej5fM() / 2.0f)) - f, 0L, 0.0f, new Stroke(fMo4557toPx0680j_4, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
        if (Dp.m6021compareTo0680j_4(((Dp) state2.getValue()).m6036unboximpl(), Dp.m6022constructorimpl(0.0f)) > 0) {
            DrawScope.m3689drawCircleVaOC9Bg$default(drawScope, ((Color) state.getValue()).m3144unboximpl(), drawScope.mo4557toPx0680j_4(((Dp) state2.getValue()).m6036unboximpl()) - f, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
        }
        return Unit.INSTANCE;
    }

    public static Unit b(boolean z, Function0 function0, Modifier modifier, boolean z2, RadioButtonColors radioButtonColors, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        RadioButton(z, function0, modifier, z2, radioButtonColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }
}
