package androidx.compose.material3.internal;

import android.view.View;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.DialogProperties;
import androidx.compose.ui.window.SecureFlagPolicy;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\\\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000eH\u0001¢\u0006\u0002\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\t*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0000¨\u0006\u0013²\u0006\u001b\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000eX\u008a\u0084\u0002²\u0006\u0010\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u008a\u0084\u0002²\u0006\n\u0010\u0016\u001a\u00020\tX\u008a\u0084\u0002"}, d2 = {"BasicEdgeToEdgeDialog", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "lightStatusBars", "", "lightNavigationBars", "content", "Lkotlin/Function1;", "Landroidx/compose/material3/internal/PredictiveBackState;", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/window/DialogProperties;ZZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "shouldApplySecureFlag", "Landroidx/compose/ui/window/SecureFlagPolicy;", "isSecureFlagSetOnParent", "material3", "currentContent", "currentOnDismissRequest", "currentDismissOnBackPress"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BasicEdgeToEdgeDialog_androidKt {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SecureFlagPolicy.values().length];
            try {
                iArr[SecureFlagPolicy.SecureOff.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SecureFlagPolicy.SecureOn.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SecureFlagPolicy.Inherit.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0127  */
    /* JADX WARN: Code duplicated, block: B:102:0x013d  */
    /* JADX WARN: Code duplicated, block: B:103:0x013f  */
    /* JADX WARN: Code duplicated, block: B:106:0x0147  */
    /* JADX WARN: Code duplicated, block: B:109:0x0153  */
    /* JADX WARN: Code duplicated, block: B:112:0x018c  */
    /* JADX WARN: Code duplicated, block: B:115:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:119:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:122:0x0205  */
    /* JADX WARN: Code duplicated, block: B:124:0x020b  */
    /* JADX WARN: Code duplicated, block: B:127:0x0220  */
    /* JADX WARN: Code duplicated, block: B:128:0x0222  */
    /* JADX WARN: Code duplicated, block: B:131:0x022c  */
    /* JADX WARN: Code duplicated, block: B:132:0x022e  */
    /* JADX WARN: Code duplicated, block: B:135:0x0241  */
    /* JADX WARN: Code duplicated, block: B:137:0x0247  */
    /* JADX WARN: Code duplicated, block: B:143:0x0259  */
    /* JADX WARN: Code duplicated, block: B:145:0x025f  */
    /* JADX WARN: Code duplicated, block: B:151:0x026d  */
    /* JADX WARN: Code duplicated, block: B:153:0x0273  */
    /* JADX WARN: Code duplicated, block: B:156:0x028e  */
    /* JADX WARN: Code duplicated, block: B:159:0x0296  */
    /* JADX WARN: Code duplicated, block: B:162:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:164:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x004a  */
    /* JADX WARN: Code duplicated, block: B:30:0x004e  */
    /* JADX WARN: Code duplicated, block: B:32:0x0056  */
    /* JADX WARN: Code duplicated, block: B:33:0x0059  */
    /* JADX WARN: Code duplicated, block: B:37:0x0060  */
    /* JADX WARN: Code duplicated, block: B:39:0x0064  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:45:0x0075  */
    /* JADX WARN: Code duplicated, block: B:48:0x007b  */
    /* JADX WARN: Code duplicated, block: B:50:0x007f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0087  */
    /* JADX WARN: Code duplicated, block: B:53:0x008a  */
    /* JADX WARN: Code duplicated, block: B:56:0x0091  */
    /* JADX WARN: Code duplicated, block: B:59:0x0099  */
    /* JADX WARN: Code duplicated, block: B:60:0x009c  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:65:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:70:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:86:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:89:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:90:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:93:0x0107  */
    /* JADX WARN: Code duplicated, block: B:95:0x011d  */
    /* JADX WARN: Code duplicated, block: B:96:0x011f  */
    public static final void BasicEdgeToEdgeDialog(final Function0<Unit> function0, Modifier modifier, DialogProperties dialogProperties, boolean z, boolean z2, final Function3<? super PredictiveBackState, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        DialogProperties dialogProperties2;
        int i5;
        boolean z3;
        boolean z4;
        int i6;
        boolean z5;
        final Modifier modifier3;
        final DialogProperties dialogProperties3;
        final boolean z6;
        final boolean z7;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        DialogProperties dialogProperties4;
        int i7;
        DialogProperties dialogProperties5;
        boolean z8;
        boolean z9;
        View view;
        Density density;
        final LayoutDirection layoutDirection;
        CompositionContext compositionContextRememberCompositionContext;
        Object objRememberedValue;
        Composer.Companion companion;
        UUID uuid;
        State stateRememberUpdatedState;
        int i8;
        State stateRememberUpdatedState2;
        State stateRememberUpdatedState3;
        boolean zChanged;
        boolean z10;
        Object obj;
        final DialogWrapper dialogWrapper;
        boolean zChangedInstance;
        Object objRememberedValue2;
        boolean z11;
        int i9;
        boolean z12;
        boolean zChanged2;
        Object objRememberedValue3;
        Composer composerStartRestartGroup = composer.startRestartGroup(814581409);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i10 = i2 & 2;
        if (i10 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    dialogProperties2 = dialogProperties;
                    if (composerStartRestartGroup.changed(dialogProperties2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        z3 = z;
                        int i11 = composerStartRestartGroup.changed(z3) ? 2048 : 1024;
                        i3 |= i11;
                    } else {
                        z3 = z;
                    }
                    i3 |= i11;
                } else {
                    z3 = z;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        z4 = z2;
                        int i12 = composerStartRestartGroup.changed(z4) ? 16384 : 8192;
                        i3 |= i12;
                    } else {
                        z4 = z2;
                    }
                    i3 |= i12;
                } else {
                    z4 = z2;
                }
                if ((i2 & 32) != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i6 = 131072;
                    } else {
                        i6 = 65536;
                    }
                    i3 |= i6;
                }
                if ((i3 & 74899) != 74898) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if ((i2 & 8) != 0) {
                            if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                                z9 = true;
                            } else {
                                z9 = false;
                            }
                            i3 &= -7169;
                            z3 = z9;
                        }
                        if ((i2 & 16) != 0) {
                            if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                                z8 = true;
                            } else {
                                z8 = false;
                            }
                            i7 = i3 & (-57345);
                            z4 = z8;
                        } else {
                            i7 = i3;
                        }
                        dialogProperties5 = dialogProperties4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                        }
                        i7 = i3;
                        modifier4 = modifier2;
                        dialogProperties5 = dialogProperties2;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(814581409, i7, -1, "androidx.compose.material3.internal.BasicEdgeToEdgeDialog (BasicEdgeToEdgeDialog.android.kt:90)");
                    }
                    view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
                    density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                    compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
                    Object[] objArr = new Object[0];
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: mo0
                            public final Object invoke() {
                                return BasicEdgeToEdgeDialog_androidKt.a();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue, composerStartRestartGroup, 48);
                    stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function3, composerStartRestartGroup, (i7 >> 15) & 14);
                    i8 = i7 & 14;
                    stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function0, composerStartRestartGroup, i8);
                    stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(dialogProperties5.getDismissOnBackPress()), composerStartRestartGroup, 0);
                    zChanged = composerStartRestartGroup.changed(view) | composerStartRestartGroup.changed(density);
                    Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged || objRememberedValue4 == companion.getEmpty()) {
                        DialogWrapper dialogWrapper2 = new DialogWrapper(function0, dialogProperties5, view, layoutDirection, density, uuid, z3, z4);
                        z10 = true;
                        dialogWrapper2.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-635938462, true, new BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1(modifier4, stateRememberUpdatedState3, stateRememberUpdatedState2, stateRememberUpdatedState)));
                        composerStartRestartGroup.updateRememberedValue(dialogWrapper2);
                        obj = dialogWrapper2;
                    } else {
                        z10 = true;
                        obj = objRememberedValue4;
                    }
                    dialogWrapper = (DialogWrapper) obj;
                    zChangedInstance = composerStartRestartGroup.changedInstance(dialogWrapper);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.internal.f
                            public final Object invoke(Object obj2) {
                                return BasicEdgeToEdgeDialog_androidKt.b(dialogWrapper, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.DisposableEffect(dialogWrapper, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, 0);
                    boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(dialogWrapper);
                    if (i8 == 4) {
                        z11 = z10;
                    } else {
                        z11 = false;
                    }
                    boolean z13 = zChangedInstance2 | z11;
                    i9 = i7;
                    if ((i9 & 896) == 256) {
                        z12 = z10;
                    } else {
                        z12 = false;
                    }
                    zChanged2 = z13 | z12 | composerStartRestartGroup.changed(layoutDirection.ordinal()) | (((((i9 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) > 2048 || !composerStartRestartGroup.changed(z3)) && (i9 & 3072) != 2048) ? false : z10) | (((((57344 & i9) ^ 24576) > 16384 || !composerStartRestartGroup.changed(z4)) && (i9 & 24576) != 16384) ? false : z10);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2 || objRememberedValue3 == companion.getEmpty()) {
                        final DialogProperties dialogProperties6 = dialogProperties5;
                        final boolean z14 = z3;
                        final boolean z15 = z4;
                        Function0 function1 = new Function0() { // from class: androidx.compose.material3.internal.g
                            public final Object invoke() {
                                return BasicEdgeToEdgeDialog_androidKt.d(dialogWrapper, function0, dialogProperties6, layoutDirection, z14, z15);
                            }
                        };
                        dialogProperties5 = dialogProperties6;
                        composerStartRestartGroup.updateRememberedValue(function1);
                        objRememberedValue3 = function1;
                    }
                    EffectsKt.SideEffect((Function0) objRememberedValue3, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties5;
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                }
                z6 = z3;
                z7 = z4;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: no0
                        public final Object invoke(Object obj2, Object obj3) {
                            return BasicEdgeToEdgeDialog_androidKt.c(function0, modifier3, dialogProperties3, z6, z7, function3, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            dialogProperties2 = dialogProperties;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    z3 = z;
                    if (composerStartRestartGroup.changed(z3)) {
                    }
                    i3 |= i11;
                } else {
                    z3 = z;
                }
                i3 |= i11;
            } else {
                z3 = z;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                    }
                    i3 |= i12;
                } else {
                    z4 = z2;
                }
                i3 |= i12;
            } else {
                z4 = z2;
            }
            if ((i2 & 32) != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i6 = 131072;
                } else {
                    i6 = 65536;
                }
                i3 |= i6;
            }
            if ((i3 & 74899) != 74898) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if ((i2 & 8) != 0) {
                        if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        i3 &= -7169;
                        z3 = z9;
                    }
                    if ((i2 & 16) != 0) {
                        if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        i7 = i3 & (-57345);
                        z4 = z8;
                    } else {
                        i7 = i3;
                    }
                    dialogProperties5 = dialogProperties4;
                } else {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if ((i2 & 8) != 0) {
                        if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        i3 &= -7169;
                        z3 = z9;
                    }
                    if ((i2 & 16) != 0) {
                        if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        i7 = i3 & (-57345);
                        z4 = z8;
                    } else {
                        i7 = i3;
                    }
                    dialogProperties5 = dialogProperties4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(814581409, i7, -1, "androidx.compose.material3.internal.BasicEdgeToEdgeDialog (BasicEdgeToEdgeDialog.android.kt:90)");
                }
                view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
                density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
                Object[] objArr2 = new Object[0];
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: mo0
                        public final Object invoke() {
                            return BasicEdgeToEdgeDialog_androidKt.a();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr2, (Function0) objRememberedValue, composerStartRestartGroup, 48);
                stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function3, composerStartRestartGroup, (i7 >> 15) & 14);
                i8 = i7 & 14;
                stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function0, composerStartRestartGroup, i8);
                stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(dialogProperties5.getDismissOnBackPress()), composerStartRestartGroup, 0);
                zChanged = composerStartRestartGroup.changed(view) | composerStartRestartGroup.changed(density);
                Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    DialogWrapper dialogWrapper3 = new DialogWrapper(function0, dialogProperties5, view, layoutDirection, density, uuid, z3, z4);
                    z10 = true;
                    dialogWrapper3.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-635938462, true, new BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1(modifier4, stateRememberUpdatedState3, stateRememberUpdatedState2, stateRememberUpdatedState)));
                    composerStartRestartGroup.updateRememberedValue(dialogWrapper3);
                    obj = dialogWrapper3;
                } else {
                    DialogWrapper dialogWrapper4 = new DialogWrapper(function0, dialogProperties5, view, layoutDirection, density, uuid, z3, z4);
                    z10 = true;
                    dialogWrapper4.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-635938462, true, new BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1(modifier4, stateRememberUpdatedState3, stateRememberUpdatedState2, stateRememberUpdatedState)));
                    composerStartRestartGroup.updateRememberedValue(dialogWrapper4);
                    obj = dialogWrapper4;
                }
                dialogWrapper = (DialogWrapper) obj;
                zChangedInstance = composerStartRestartGroup.changedInstance(dialogWrapper);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.internal.f
                        public final Object invoke(Object obj2) {
                            return BasicEdgeToEdgeDialog_androidKt.b(dialogWrapper, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.internal.f
                        public final Object invoke(Object obj2) {
                            return BasicEdgeToEdgeDialog_androidKt.b(dialogWrapper, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.DisposableEffect(dialogWrapper, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, 0);
                boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(dialogWrapper);
                if (i8 == 4) {
                    z11 = z10;
                } else {
                    z11 = false;
                }
                boolean z16 = zChangedInstance3 | z11;
                i9 = i7;
                if ((i9 & 896) == 256) {
                    z12 = z10;
                } else {
                    z12 = false;
                }
                zChanged2 = z16 | z12 | composerStartRestartGroup.changed(layoutDirection.ordinal()) | (((((i9 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) > 2048 || !composerStartRestartGroup.changed(z3)) && (i9 & 3072) != 2048) ? false : z10) | (((((57344 & i9) ^ 24576) > 16384 || !composerStartRestartGroup.changed(z4)) && (i9 & 24576) != 16384) ? false : z10);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    final DialogProperties dialogProperties7 = dialogProperties5;
                    final boolean z17 = z3;
                    final boolean z18 = z4;
                    Function0 function2 = new Function0() { // from class: androidx.compose.material3.internal.g
                        public final Object invoke() {
                            return BasicEdgeToEdgeDialog_androidKt.d(dialogWrapper, function0, dialogProperties7, layoutDirection, z17, z18);
                        }
                    };
                    dialogProperties5 = dialogProperties7;
                    composerStartRestartGroup.updateRememberedValue(function2);
                    objRememberedValue3 = function2;
                } else {
                    final DialogProperties dialogProperties8 = dialogProperties5;
                    final boolean z19 = z3;
                    final boolean z110 = z4;
                    Function0 function4 = new Function0() { // from class: androidx.compose.material3.internal.g
                        public final Object invoke() {
                            return BasicEdgeToEdgeDialog_androidKt.d(dialogWrapper, function0, dialogProperties8, layoutDirection, z19, z110);
                        }
                    };
                    dialogProperties5 = dialogProperties8;
                    composerStartRestartGroup.updateRememberedValue(function4);
                    objRememberedValue3 = function4;
                }
                EffectsKt.SideEffect((Function0) objRememberedValue3, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                dialogProperties3 = dialogProperties5;
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                dialogProperties3 = dialogProperties2;
            }
            z6 = z3;
            z7 = z4;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: no0
                    public final Object invoke(Object obj2, Object obj3) {
                        return BasicEdgeToEdgeDialog_androidKt.c(function0, modifier3, dialogProperties3, z6, z7, function3, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                dialogProperties2 = dialogProperties;
                if (composerStartRestartGroup.changed(dialogProperties2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    z3 = z;
                    if (composerStartRestartGroup.changed(z3)) {
                    }
                    i3 |= i11;
                } else {
                    z3 = z;
                }
                i3 |= i11;
            } else {
                z3 = z;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                    }
                    i3 |= i12;
                } else {
                    z4 = z2;
                }
                i3 |= i12;
            } else {
                z4 = z2;
            }
            if ((i2 & 32) != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i6 = 131072;
                } else {
                    i6 = 65536;
                }
                i3 |= i6;
            }
            if ((i3 & 74899) != 74898) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if ((i2 & 8) != 0) {
                        if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        i3 &= -7169;
                        z3 = z9;
                    }
                    if ((i2 & 16) != 0) {
                        if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        i7 = i3 & (-57345);
                        z4 = z8;
                    } else {
                        i7 = i3;
                    }
                    dialogProperties5 = dialogProperties4;
                } else {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if ((i2 & 8) != 0) {
                        if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        i3 &= -7169;
                        z3 = z9;
                    }
                    if ((i2 & 16) != 0) {
                        if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        i7 = i3 & (-57345);
                        z4 = z8;
                    } else {
                        i7 = i3;
                    }
                    dialogProperties5 = dialogProperties4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(814581409, i7, -1, "androidx.compose.material3.internal.BasicEdgeToEdgeDialog (BasicEdgeToEdgeDialog.android.kt:90)");
                }
                view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
                density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
                Object[] objArr3 = new Object[0];
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: mo0
                        public final Object invoke() {
                            return BasicEdgeToEdgeDialog_androidKt.a();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr3, (Function0) objRememberedValue, composerStartRestartGroup, 48);
                stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function3, composerStartRestartGroup, (i7 >> 15) & 14);
                i8 = i7 & 14;
                stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function0, composerStartRestartGroup, i8);
                stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(dialogProperties5.getDismissOnBackPress()), composerStartRestartGroup, 0);
                zChanged = composerStartRestartGroup.changed(view) | composerStartRestartGroup.changed(density);
                Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    DialogWrapper dialogWrapper5 = new DialogWrapper(function0, dialogProperties5, view, layoutDirection, density, uuid, z3, z4);
                    z10 = true;
                    dialogWrapper5.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-635938462, true, new BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1(modifier4, stateRememberUpdatedState3, stateRememberUpdatedState2, stateRememberUpdatedState)));
                    composerStartRestartGroup.updateRememberedValue(dialogWrapper5);
                    obj = dialogWrapper5;
                } else {
                    DialogWrapper dialogWrapper6 = new DialogWrapper(function0, dialogProperties5, view, layoutDirection, density, uuid, z3, z4);
                    z10 = true;
                    dialogWrapper6.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-635938462, true, new BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1(modifier4, stateRememberUpdatedState3, stateRememberUpdatedState2, stateRememberUpdatedState)));
                    composerStartRestartGroup.updateRememberedValue(dialogWrapper6);
                    obj = dialogWrapper6;
                }
                dialogWrapper = (DialogWrapper) obj;
                zChangedInstance = composerStartRestartGroup.changedInstance(dialogWrapper);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.internal.f
                        public final Object invoke(Object obj2) {
                            return BasicEdgeToEdgeDialog_androidKt.b(dialogWrapper, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.internal.f
                        public final Object invoke(Object obj2) {
                            return BasicEdgeToEdgeDialog_androidKt.b(dialogWrapper, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.DisposableEffect(dialogWrapper, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, 0);
                boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(dialogWrapper);
                if (i8 == 4) {
                    z11 = z10;
                } else {
                    z11 = false;
                }
                boolean z111 = zChangedInstance4 | z11;
                i9 = i7;
                if ((i9 & 896) == 256) {
                    z12 = z10;
                } else {
                    z12 = false;
                }
                zChanged2 = z111 | z12 | composerStartRestartGroup.changed(layoutDirection.ordinal()) | (((((i9 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) > 2048 || !composerStartRestartGroup.changed(z3)) && (i9 & 3072) != 2048) ? false : z10) | (((((57344 & i9) ^ 24576) > 16384 || !composerStartRestartGroup.changed(z4)) && (i9 & 24576) != 16384) ? false : z10);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    final DialogProperties dialogProperties9 = dialogProperties5;
                    final boolean z112 = z3;
                    final boolean z113 = z4;
                    Function0 function5 = new Function0() { // from class: androidx.compose.material3.internal.g
                        public final Object invoke() {
                            return BasicEdgeToEdgeDialog_androidKt.d(dialogWrapper, function0, dialogProperties9, layoutDirection, z112, z113);
                        }
                    };
                    dialogProperties5 = dialogProperties9;
                    composerStartRestartGroup.updateRememberedValue(function5);
                    objRememberedValue3 = function5;
                } else {
                    final DialogProperties dialogProperties10 = dialogProperties5;
                    final boolean z114 = z3;
                    final boolean z115 = z4;
                    Function0 function6 = new Function0() { // from class: androidx.compose.material3.internal.g
                        public final Object invoke() {
                            return BasicEdgeToEdgeDialog_androidKt.d(dialogWrapper, function0, dialogProperties10, layoutDirection, z114, z115);
                        }
                    };
                    dialogProperties5 = dialogProperties10;
                    composerStartRestartGroup.updateRememberedValue(function6);
                    objRememberedValue3 = function6;
                }
                EffectsKt.SideEffect((Function0) objRememberedValue3, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                dialogProperties3 = dialogProperties5;
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                dialogProperties3 = dialogProperties2;
            }
            z6 = z3;
            z7 = z4;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: no0
                    public final Object invoke(Object obj2, Object obj3) {
                        return BasicEdgeToEdgeDialog_androidKt.c(function0, modifier3, dialogProperties3, z6, z7, function3, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        dialogProperties2 = dialogProperties;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                z3 = z;
                if (composerStartRestartGroup.changed(z3)) {
                }
                i3 |= i11;
            } else {
                z3 = z;
            }
            i3 |= i11;
        } else {
            z3 = z;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                }
                i3 |= i12;
            } else {
                z4 = z2;
            }
            i3 |= i12;
        } else {
            z4 = z2;
        }
        if ((i2 & 32) != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i6 = 131072;
            } else {
                i6 = 65536;
            }
            i3 |= i6;
        }
        if ((i3 & 74899) != 74898) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i10 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    dialogProperties4 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                } else {
                    dialogProperties4 = dialogProperties2;
                }
                if ((i2 & 8) != 0) {
                    if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    i3 &= -7169;
                    z3 = z9;
                }
                if ((i2 & 16) != 0) {
                    if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    i7 = i3 & (-57345);
                    z4 = z8;
                } else {
                    i7 = i3;
                }
                dialogProperties5 = dialogProperties4;
            } else {
                if (i10 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    dialogProperties4 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                } else {
                    dialogProperties4 = dialogProperties2;
                }
                if ((i2 & 8) != 0) {
                    if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    i3 &= -7169;
                    z3 = z9;
                }
                if ((i2 & 16) != 0) {
                    if (ColorKt.m3186luminance8_81llA(((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()) < 0.5f) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    i7 = i3 & (-57345);
                    z4 = z8;
                } else {
                    i7 = i3;
                }
                dialogProperties5 = dialogProperties4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(814581409, i7, -1, "androidx.compose.material3.internal.BasicEdgeToEdgeDialog (BasicEdgeToEdgeDialog.android.kt:90)");
            }
            view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
            density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
            Object[] objArr4 = new Object[0];
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: mo0
                    public final Object invoke() {
                        return BasicEdgeToEdgeDialog_androidKt.a();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr4, (Function0) objRememberedValue, composerStartRestartGroup, 48);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function3, composerStartRestartGroup, (i7 >> 15) & 14);
            i8 = i7 & 14;
            stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function0, composerStartRestartGroup, i8);
            stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(dialogProperties5.getDismissOnBackPress()), composerStartRestartGroup, 0);
            zChanged = composerStartRestartGroup.changed(view) | composerStartRestartGroup.changed(density);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                DialogWrapper dialogWrapper7 = new DialogWrapper(function0, dialogProperties5, view, layoutDirection, density, uuid, z3, z4);
                z10 = true;
                dialogWrapper7.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-635938462, true, new BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1(modifier4, stateRememberUpdatedState3, stateRememberUpdatedState2, stateRememberUpdatedState)));
                composerStartRestartGroup.updateRememberedValue(dialogWrapper7);
                obj = dialogWrapper7;
            } else {
                DialogWrapper dialogWrapper8 = new DialogWrapper(function0, dialogProperties5, view, layoutDirection, density, uuid, z3, z4);
                z10 = true;
                dialogWrapper8.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-635938462, true, new BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1(modifier4, stateRememberUpdatedState3, stateRememberUpdatedState2, stateRememberUpdatedState)));
                composerStartRestartGroup.updateRememberedValue(dialogWrapper8);
                obj = dialogWrapper8;
            }
            dialogWrapper = (DialogWrapper) obj;
            zChangedInstance = composerStartRestartGroup.changedInstance(dialogWrapper);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.internal.f
                    public final Object invoke(Object obj2) {
                        return BasicEdgeToEdgeDialog_androidKt.b(dialogWrapper, (DisposableEffectScope) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.internal.f
                    public final Object invoke(Object obj2) {
                        return BasicEdgeToEdgeDialog_androidKt.b(dialogWrapper, (DisposableEffectScope) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.DisposableEffect(dialogWrapper, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, 0);
            boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(dialogWrapper);
            if (i8 == 4) {
                z11 = z10;
            } else {
                z11 = false;
            }
            boolean z116 = zChangedInstance5 | z11;
            i9 = i7;
            if ((i9 & 896) == 256) {
                z12 = z10;
            } else {
                z12 = false;
            }
            zChanged2 = z116 | z12 | composerStartRestartGroup.changed(layoutDirection.ordinal()) | (((((i9 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) > 2048 || !composerStartRestartGroup.changed(z3)) && (i9 & 3072) != 2048) ? false : z10) | (((((57344 & i9) ^ 24576) > 16384 || !composerStartRestartGroup.changed(z4)) && (i9 & 24576) != 16384) ? false : z10);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2) {
                final DialogProperties dialogProperties11 = dialogProperties5;
                final boolean z117 = z3;
                final boolean z118 = z4;
                Function0 function7 = new Function0() { // from class: androidx.compose.material3.internal.g
                    public final Object invoke() {
                        return BasicEdgeToEdgeDialog_androidKt.d(dialogWrapper, function0, dialogProperties11, layoutDirection, z117, z118);
                    }
                };
                dialogProperties5 = dialogProperties11;
                composerStartRestartGroup.updateRememberedValue(function7);
                objRememberedValue3 = function7;
            } else {
                final DialogProperties dialogProperties12 = dialogProperties5;
                final boolean z119 = z3;
                final boolean z1110 = z4;
                Function0 function8 = new Function0() { // from class: androidx.compose.material3.internal.g
                    public final Object invoke() {
                        return BasicEdgeToEdgeDialog_androidKt.d(dialogWrapper, function0, dialogProperties12, layoutDirection, z119, z1110);
                    }
                };
                dialogProperties5 = dialogProperties12;
                composerStartRestartGroup.updateRememberedValue(function8);
                objRememberedValue3 = function8;
            }
            EffectsKt.SideEffect((Function0) objRememberedValue3, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            dialogProperties3 = dialogProperties5;
            modifier3 = modifier4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            dialogProperties3 = dialogProperties2;
        }
        z6 = z3;
        z7 = z4;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: no0
                public final Object invoke(Object obj2, Object obj3) {
                    return BasicEdgeToEdgeDialog_androidKt.c(function0, modifier3, dialogProperties3, z6, z7, function3, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function3<PredictiveBackState, Composer, Integer, Unit> BasicEdgeToEdgeDialog$lambda$2(State<? extends Function3<? super PredictiveBackState, ? super Composer, ? super Integer, Unit>> state) {
        return (Function3) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function0<Unit> BasicEdgeToEdgeDialog$lambda$3(State<? extends Function0<Unit>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean BasicEdgeToEdgeDialog$lambda$4(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    public static UUID a() {
        return UUID.randomUUID();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static DisposableEffectResult b(final DialogWrapper dialogWrapper, DisposableEffectScope disposableEffectScope) {
        dialogWrapper.show();
        return new DisposableEffectResult() { // from class: androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$lambda$9$lambda$8$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                dialogWrapper.dismiss();
                dialogWrapper.disposeComposition();
            }
        };
    }

    public static Unit c(Function0 function0, Modifier modifier, DialogProperties dialogProperties, boolean z, boolean z2, Function3 function3, int i, int i2, Composer composer, int i3) {
        BasicEdgeToEdgeDialog(function0, modifier, dialogProperties, z, z2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit d(DialogWrapper dialogWrapper, Function0 function0, DialogProperties dialogProperties, LayoutDirection layoutDirection, boolean z, boolean z2) {
        dialogWrapper.updateParameters(function0, dialogProperties, layoutDirection, z, z2);
        return Unit.INSTANCE;
    }

    public static final boolean shouldApplySecureFlag(SecureFlagPolicy secureFlagPolicy, boolean z) {
        int i = WhenMappings.$EnumSwitchMapping$0[secureFlagPolicy.ordinal()];
        if (i == 1) {
            return false;
        }
        if (i == 2) {
            return true;
        }
        if (i == 3) {
            return z;
        }
        bu8.a();
        return false;
    }
}
