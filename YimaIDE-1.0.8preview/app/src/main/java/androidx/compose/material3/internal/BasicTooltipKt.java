package androidx.compose.material3.internal;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.material3.TooltipState;
import androidx.compose.material3.internal.BasicTooltipKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.compose.ui.window.PopupProperties;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.bouncycastle.asn1.BERTags;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a}\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u0011\u001aP\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00142\u0006\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\t\u001a\u00020\n2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0002\u0010\u0015\u001a^\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00142\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u001a\u001a\u00020\n*\u00020\n2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a,\u0010\u001c\u001a\u00020\n*\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\u001a:\u0010\u001f\u001a\u00020\n*\u00020\n2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014H\u0002\u001a+\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\r2\b\b\u0002\u0010\"\u001a\u00020\r2\b\b\u0002\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\u0010%\u001a&\u0010&\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\r2\b\b\u0002\u0010\"\u001a\u00020\r2\b\b\u0002\u0010#\u001a\u00020$H\u0001\u001a\u0013\u0010'\u001a\b\u0012\u0004\u0012\u00020\r0(H\u0003¢\u0006\u0002\u0010)¨\u0006*"}, d2 = {"BasicTooltipBox", "", "positionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "tooltip", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "state", "Landroidx/compose/material3/TooltipState;", "modifier", "Landroidx/compose/ui/Modifier;", "onDismissRequest", "focusable", "", "enableUserInput", "hasAction", "content", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TooltipState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZZZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "WrappedAnchor", "forceKeyboardFocusable", "Landroidx/compose/runtime/MutableState;", "(ZLandroidx/compose/material3/TooltipState;Landroidx/compose/runtime/MutableState;ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TooltipPopup", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/compose/ui/window/PopupPositionProvider;Landroidx/compose/material3/TooltipState;Lkotlin/jvm/functions/Function0;Lkotlinx/coroutines/CoroutineScope;ZLandroidx/compose/runtime/MutableState;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "handleGestures", "enabled", "anchorSemantics", "label", "", "keyboardBehavior", "rememberBasicTooltipState", "initialIsVisible", "isPersistent", "mutatorMutex", "Landroidx/compose/foundation/MutatorMutex;", "(ZZLandroidx/compose/foundation/MutatorMutex;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TooltipState;", "BasicTooltipState", "rememberTouchExplorationOrSwitchAccessServiceState", "Landroidx/compose/runtime/State;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BasicTooltipKt {
    /* JADX WARN: Code duplicated, block: B:102:0x0126  */
    /* JADX WARN: Code duplicated, block: B:103:0x0129  */
    /* JADX WARN: Code duplicated, block: B:106:0x0132 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:107:0x0134  */
    /* JADX WARN: Code duplicated, block: B:108:0x0139  */
    /* JADX WARN: Code duplicated, block: B:111:0x013e  */
    /* JADX WARN: Code duplicated, block: B:112:0x0140  */
    /* JADX WARN: Code duplicated, block: B:114:0x0143  */
    /* JADX WARN: Code duplicated, block: B:116:0x0146  */
    /* JADX WARN: Code duplicated, block: B:117:0x0149  */
    /* JADX WARN: Code duplicated, block: B:119:0x014d  */
    /* JADX WARN: Code duplicated, block: B:120:0x014f  */
    /* JADX WARN: Code duplicated, block: B:123:0x0157  */
    /* JADX WARN: Code duplicated, block: B:126:0x016c  */
    /* JADX WARN: Code duplicated, block: B:129:0x0182  */
    /* JADX WARN: Code duplicated, block: B:132:0x0190  */
    /* JADX WARN: Code duplicated, block: B:137:0x01af  */
    /* JADX WARN: Code duplicated, block: B:140:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:143:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:144:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:147:0x0207  */
    /* JADX WARN: Code duplicated, block: B:149:0x0215  */
    /* JADX WARN: Code duplicated, block: B:152:0x0232  */
    /* JADX WARN: Code duplicated, block: B:154:0x023a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:157:0x0240  */
    /* JADX WARN: Code duplicated, block: B:159:0x026b  */
    /* JADX WARN: Code duplicated, block: B:162:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:170:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:172:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:175:0x02dd  */
    /* JADX WARN: Code duplicated, block: B:178:0x02e7  */
    /* JADX WARN: Code duplicated, block: B:181:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:183:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004c  */
    /* JADX WARN: Code duplicated, block: B:27:0x004f  */
    /* JADX WARN: Code duplicated, block: B:29:0x0053  */
    /* JADX WARN: Code duplicated, block: B:31:0x0057  */
    /* JADX WARN: Code duplicated, block: B:32:0x005c  */
    /* JADX WARN: Code duplicated, block: B:34:0x0062  */
    /* JADX WARN: Code duplicated, block: B:35:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006c  */
    /* JADX WARN: Code duplicated, block: B:41:0x0071  */
    /* JADX WARN: Code duplicated, block: B:43:0x0075  */
    /* JADX WARN: Code duplicated, block: B:45:0x007d  */
    /* JADX WARN: Code duplicated, block: B:46:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0087  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:54:0x0090  */
    /* JADX WARN: Code duplicated, block: B:56:0x0098  */
    /* JADX WARN: Code duplicated, block: B:57:0x009b  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:62:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:67:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:72:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:84:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:91:0x0101  */
    /* JADX WARN: Code duplicated, block: B:93:0x0106  */
    /* JADX WARN: Code duplicated, block: B:95:0x010a  */
    /* JADX WARN: Code duplicated, block: B:97:0x0112  */
    /* JADX WARN: Code duplicated, block: B:98:0x0115  */
    public static final void BasicTooltipBox(final PopupPositionProvider popupPositionProvider, final Function2<? super Composer, ? super Integer, Unit> function2, TooltipState tooltipState, Modifier modifier, Function0<Unit> function0, boolean z, boolean z2, boolean z3, final Function2<? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Function2<? super Composer, ? super Integer, Unit> function4;
        boolean zChangedInstance;
        int i4;
        int i5;
        Modifier modifier2;
        int i6;
        int i7;
        Function0<Unit> function1;
        int i8;
        int i9;
        int i10;
        boolean z4;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean z5;
        boolean z6;
        final TooltipState tooltipState2;
        final boolean z7;
        final Modifier modifier3;
        final Function0<Unit> function5;
        final boolean z8;
        final boolean z9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Function0<Unit> function6;
        boolean z10;
        boolean z11;
        Object objRememberedValue;
        Composer.Companion companion;
        CoroutineScope coroutineScope;
        Object objRememberedValue2;
        MutableState mutableState;
        boolean z12;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM2388constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        MutableState mutableState2;
        Object objRememberedValue3;
        boolean z13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1221877520);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(popupPositionProvider) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) == 0) {
            if ((i & 48) == 0) {
                function4 = function2;
                i3 |= composerStartRestartGroup.changedInstance(function4) ? 32 : 16;
            }
            if ((i2 & 4) != 0) {
                i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            } else if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i & 512) == 0) {
                    zChangedInstance = composerStartRestartGroup.changed(tooltipState);
                } else {
                    zChangedInstance = composerStartRestartGroup.changedInstance(tooltipState);
                }
                if (zChangedInstance) {
                    i4 = 256;
                } else {
                    i4 = 128;
                }
                i3 |= i4;
            }
            i5 = i2 & 8;
            if (i5 != 0) {
                if ((i & 3072) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i3 |= i6;
                }
                i7 = i2 & 16;
                if (i7 != 0) {
                    if ((i & 24576) == 0) {
                        function1 = function0;
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                    i9 = i2 & 32;
                    if (i9 != 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        i10 = 196608;
                        z4 = z;
                    } else {
                        i10 = 196608;
                        z4 = z;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(z4)) {
                                i11 = 131072;
                            } else {
                                i11 = 65536;
                            }
                            i3 |= i11;
                        }
                    }
                    i12 = i2 & 64;
                    if (i12 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i13 = 1048576;
                        } else {
                            i13 = 524288;
                        }
                        i3 |= i13;
                    }
                    i14 = i2 & 128;
                    if (i14 != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z3)) {
                            i15 = 8388608;
                        } else {
                            i15 = 4194304;
                        }
                        i3 |= i15;
                    }
                    if ((i2 & 256) != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i16 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i16 = 33554432;
                            }
                            i3 |= i16;
                        }
                        z5 = true;
                        if ((i3 & 38347923) != 38347922) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                            if (i5 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i7 != 0) {
                                function6 = null;
                            } else {
                                function6 = function1;
                            }
                            if (i9 != 0) {
                                z4 = false;
                            }
                            if (i12 != 0) {
                                z10 = true;
                            } else {
                                z10 = z2;
                            }
                            if (i14 != 0) {
                                z11 = false;
                            } else {
                                z11 = z3;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            coroutineScope = (CoroutineScope) objRememberedValue;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == companion.getEmpty()) {
                                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            mutableState = (MutableState) objRememberedValue2;
                            if (z11 || !(rememberTouchExplorationOrSwitchAccessServiceState(composerStartRestartGroup, 0).getValue().booleanValue() || ((Boolean) mutableState.getValue()).booleanValue())) {
                                z12 = false;
                            } else {
                                z12 = true;
                            }
                            Modifier.Companion companion2 = Modifier.INSTANCE;
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
                            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                            boolean z14 = z12;
                            constructor = companion3.getConstructor();
                            if (composerStartRestartGroup.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(constructor);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            if (tooltipState.getIsVisible()) {
                                composerStartRestartGroup.startReplaceGroup(-1891243071);
                                if (!z4 || z14) {
                                    z13 = true;
                                } else {
                                    z13 = false;
                                }
                                Function2<? super Composer, ? super Integer, Unit> function7 = function4;
                                mutableState2 = mutableState;
                                TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function7, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                                composerStartRestartGroup = composerStartRestartGroup;
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                mutableState2 = mutableState;
                                composerStartRestartGroup.startReplaceGroup(-1890863476);
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            int i17 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                            tooltipState2 = tooltipState;
                            boolean z15 = z10;
                            boolean z16 = z11;
                            modifier3 = modifier4;
                            WrappedAnchor(z15, tooltipState2, mutableState2, z16, modifier3, function3, composerStartRestartGroup, i17, 0);
                            composerStartRestartGroup.endNode();
                            if ((i3 & 896) != 256 && ((i3 & 512) == 0 || !composerStartRestartGroup.changedInstance(tooltipState2))) {
                                z5 = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (z5 || objRememberedValue3 == companion.getEmpty()) {
                                objRememberedValue3 = new Function1() { // from class: ts0
                                    public final Object invoke(Object obj) {
                                        return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z7 = z15;
                            z8 = z16;
                            function5 = function6;
                        } else {
                            tooltipState2 = tooltipState;
                            composerStartRestartGroup.skipToGroupEnd();
                            z7 = z2;
                            modifier3 = modifier2;
                            function5 = function1;
                            z8 = z3;
                        }
                        Composer composer2 = composerStartRestartGroup;
                        z9 = z4;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            final TooltipState tooltipState3 = tooltipState2;
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                                public final Object invoke(Object obj, Object obj2) {
                                    return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState3, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    z5 = true;
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        if (i5 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i7 != 0) {
                            function6 = null;
                        } else {
                            function6 = function1;
                        }
                        if (i9 != 0) {
                            z4 = false;
                        }
                        if (i12 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i14 != 0) {
                            z11 = false;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        coroutineScope = (CoroutineScope) objRememberedValue;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        mutableState = (MutableState) objRememberedValue2;
                        if (z11) {
                            z12 = false;
                        } else {
                            z12 = false;
                        }
                        Modifier.Companion companion4 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion4);
                        ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
                        boolean z17 = z12;
                        constructor = companion5.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, companion5.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap2, companion5.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion5.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier2, companion5.getSetModifier());
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        if (tooltipState.getIsVisible()) {
                            composerStartRestartGroup.startReplaceGroup(-1891243071);
                            if (z4) {
                                z13 = true;
                            } else {
                                z13 = true;
                            }
                            Function2<? super Composer, ? super Integer, Unit> function8 = function4;
                            mutableState2 = mutableState;
                            TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function8, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                            composerStartRestartGroup = composerStartRestartGroup;
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            mutableState2 = mutableState;
                            composerStartRestartGroup.startReplaceGroup(-1890863476);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        int i18 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                        tooltipState2 = tooltipState;
                        boolean z18 = z10;
                        boolean z19 = z11;
                        modifier3 = modifier4;
                        WrappedAnchor(z18, tooltipState2, mutableState2, z19, modifier3, function3, composerStartRestartGroup, i18, 0);
                        composerStartRestartGroup.endNode();
                        if ((i3 & 896) != 256) {
                            z5 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue3 = new Function1() { // from class: ts0
                                public final Object invoke(Object obj) {
                                    return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1() { // from class: ts0
                                public final Object invoke(Object obj) {
                                    return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z7 = z18;
                        z8 = z19;
                        function5 = function6;
                    } else {
                        tooltipState2 = tooltipState;
                        composerStartRestartGroup.skipToGroupEnd();
                        z7 = z2;
                        modifier3 = modifier2;
                        function5 = function1;
                        z8 = z3;
                    }
                    Composer composer3 = composerStartRestartGroup;
                    z9 = z4;
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final TooltipState tooltipState4 = tooltipState2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState4, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                function1 = function0;
                i9 = i2 & 32;
                if (i9 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    i10 = 196608;
                    z4 = z;
                } else {
                    i10 = 196608;
                    z4 = z;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(z4)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 64;
                if (i12 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i13 = 1048576;
                    } else {
                        i13 = 524288;
                    }
                    i3 |= i13;
                }
                i14 = i2 & 128;
                if (i14 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i15 = 8388608;
                    } else {
                        i15 = 4194304;
                    }
                    i3 |= i15;
                }
                if ((i2 & 256) != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i16 = 33554432;
                        }
                        i3 |= i16;
                    }
                    z5 = true;
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        if (i5 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i7 != 0) {
                            function6 = null;
                        } else {
                            function6 = function1;
                        }
                        if (i9 != 0) {
                            z4 = false;
                        }
                        if (i12 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i14 != 0) {
                            z11 = false;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        coroutineScope = (CoroutineScope) objRememberedValue;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        mutableState = (MutableState) objRememberedValue2;
                        if (z11) {
                            z12 = false;
                        } else {
                            z12 = false;
                        }
                        Modifier.Companion companion6 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion6);
                        ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
                        boolean z110 = z12;
                        constructor = companion7.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, companion7.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap3, companion7.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion7.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier3, companion7.getSetModifier());
                        BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                        if (tooltipState.getIsVisible()) {
                            composerStartRestartGroup.startReplaceGroup(-1891243071);
                            if (z4) {
                                z13 = true;
                            } else {
                                z13 = true;
                            }
                            Function2<? super Composer, ? super Integer, Unit> function9 = function4;
                            mutableState2 = mutableState;
                            TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function9, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                            composerStartRestartGroup = composerStartRestartGroup;
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            mutableState2 = mutableState;
                            composerStartRestartGroup.startReplaceGroup(-1890863476);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        int i19 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                        tooltipState2 = tooltipState;
                        boolean z111 = z10;
                        boolean z112 = z11;
                        modifier3 = modifier4;
                        WrappedAnchor(z111, tooltipState2, mutableState2, z112, modifier3, function3, composerStartRestartGroup, i19, 0);
                        composerStartRestartGroup.endNode();
                        if ((i3 & 896) != 256) {
                            z5 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue3 = new Function1() { // from class: ts0
                                public final Object invoke(Object obj) {
                                    return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1() { // from class: ts0
                                public final Object invoke(Object obj) {
                                    return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z7 = z111;
                        z8 = z112;
                        function5 = function6;
                    } else {
                        tooltipState2 = tooltipState;
                        composerStartRestartGroup.skipToGroupEnd();
                        z7 = z2;
                        modifier3 = modifier2;
                        function5 = function1;
                        z8 = z3;
                    }
                    Composer composer4 = composerStartRestartGroup;
                    z9 = z4;
                    scopeUpdateScopeEndRestartGroup = composer4.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final TooltipState tooltipState5 = tooltipState2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState5, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                z5 = true;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i7 != 0) {
                        function6 = null;
                    } else {
                        function6 = function1;
                    }
                    if (i9 != 0) {
                        z4 = false;
                    }
                    if (i12 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i14 != 0) {
                        z11 = false;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    coroutineScope = (CoroutineScope) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableState = (MutableState) objRememberedValue2;
                    if (z11) {
                        z12 = false;
                    } else {
                        z12 = false;
                    }
                    Modifier.Companion companion8 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion8);
                    ComposeUiNode.Companion companion9 = ComposeUiNode.INSTANCE;
                    boolean z113 = z12;
                    constructor = companion9.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy4, companion9.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap4, companion9.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion9.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier4, companion9.getSetModifier());
                    BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                    if (tooltipState.getIsVisible()) {
                        composerStartRestartGroup.startReplaceGroup(-1891243071);
                        if (z4) {
                            z13 = true;
                        } else {
                            z13 = true;
                        }
                        Function2<? super Composer, ? super Integer, Unit> function10 = function4;
                        mutableState2 = mutableState;
                        TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function10, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                        composerStartRestartGroup = composerStartRestartGroup;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        mutableState2 = mutableState;
                        composerStartRestartGroup.startReplaceGroup(-1890863476);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    int i110 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                    tooltipState2 = tooltipState;
                    boolean z114 = z10;
                    boolean z115 = z11;
                    modifier3 = modifier4;
                    WrappedAnchor(z114, tooltipState2, mutableState2, z115, modifier3, function3, composerStartRestartGroup, i110, 0);
                    composerStartRestartGroup.endNode();
                    if ((i3 & 896) != 256) {
                        z5 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z7 = z114;
                    z8 = z115;
                    function5 = function6;
                } else {
                    tooltipState2 = tooltipState;
                    composerStartRestartGroup.skipToGroupEnd();
                    z7 = z2;
                    modifier3 = modifier2;
                    function5 = function1;
                    z8 = z3;
                }
                Composer composer5 = composerStartRestartGroup;
                z9 = z4;
                scopeUpdateScopeEndRestartGroup = composer5.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final TooltipState tooltipState6 = tooltipState2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState6, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            modifier2 = modifier;
            i7 = i2 & 16;
            if (i7 != 0) {
                if ((i & 24576) == 0) {
                    function1 = function0;
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                i9 = i2 & 32;
                if (i9 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    i10 = 196608;
                    z4 = z;
                } else {
                    i10 = 196608;
                    z4 = z;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(z4)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 64;
                if (i12 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i13 = 1048576;
                    } else {
                        i13 = 524288;
                    }
                    i3 |= i13;
                }
                i14 = i2 & 128;
                if (i14 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i15 = 8388608;
                    } else {
                        i15 = 4194304;
                    }
                    i3 |= i15;
                }
                if ((i2 & 256) != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i16 = 33554432;
                        }
                        i3 |= i16;
                    }
                    z5 = true;
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        if (i5 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i7 != 0) {
                            function6 = null;
                        } else {
                            function6 = function1;
                        }
                        if (i9 != 0) {
                            z4 = false;
                        }
                        if (i12 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i14 != 0) {
                            z11 = false;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        coroutineScope = (CoroutineScope) objRememberedValue;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        mutableState = (MutableState) objRememberedValue2;
                        if (z11) {
                            z12 = false;
                        } else {
                            z12 = false;
                        }
                        Modifier.Companion companion10 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion10);
                        ComposeUiNode.Companion companion11 = ComposeUiNode.INSTANCE;
                        boolean z116 = z12;
                        constructor = companion11.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy5, companion11.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap5, companion11.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion11.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier5, companion11.getSetModifier());
                        BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                        if (tooltipState.getIsVisible()) {
                            composerStartRestartGroup.startReplaceGroup(-1891243071);
                            if (z4) {
                                z13 = true;
                            } else {
                                z13 = true;
                            }
                            Function2<? super Composer, ? super Integer, Unit> function11 = function4;
                            mutableState2 = mutableState;
                            TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function11, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                            composerStartRestartGroup = composerStartRestartGroup;
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            mutableState2 = mutableState;
                            composerStartRestartGroup.startReplaceGroup(-1890863476);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        int i111 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                        tooltipState2 = tooltipState;
                        boolean z117 = z10;
                        boolean z118 = z11;
                        modifier3 = modifier4;
                        WrappedAnchor(z117, tooltipState2, mutableState2, z118, modifier3, function3, composerStartRestartGroup, i111, 0);
                        composerStartRestartGroup.endNode();
                        if ((i3 & 896) != 256) {
                            z5 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue3 = new Function1() { // from class: ts0
                                public final Object invoke(Object obj) {
                                    return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1() { // from class: ts0
                                public final Object invoke(Object obj) {
                                    return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z7 = z117;
                        z8 = z118;
                        function5 = function6;
                    } else {
                        tooltipState2 = tooltipState;
                        composerStartRestartGroup.skipToGroupEnd();
                        z7 = z2;
                        modifier3 = modifier2;
                        function5 = function1;
                        z8 = z3;
                    }
                    Composer composer6 = composerStartRestartGroup;
                    z9 = z4;
                    scopeUpdateScopeEndRestartGroup = composer6.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final TooltipState tooltipState7 = tooltipState2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState7, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                z5 = true;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i7 != 0) {
                        function6 = null;
                    } else {
                        function6 = function1;
                    }
                    if (i9 != 0) {
                        z4 = false;
                    }
                    if (i12 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i14 != 0) {
                        z11 = false;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    coroutineScope = (CoroutineScope) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableState = (MutableState) objRememberedValue2;
                    if (z11) {
                        z12 = false;
                    } else {
                        z12 = false;
                    }
                    Modifier.Companion companion12 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion12);
                    ComposeUiNode.Companion companion13 = ComposeUiNode.INSTANCE;
                    boolean z119 = z12;
                    constructor = companion13.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy6, companion13.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap6, companion13.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion13.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier6, companion13.getSetModifier());
                    BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                    if (tooltipState.getIsVisible()) {
                        composerStartRestartGroup.startReplaceGroup(-1891243071);
                        if (z4) {
                            z13 = true;
                        } else {
                            z13 = true;
                        }
                        Function2<? super Composer, ? super Integer, Unit> function12 = function4;
                        mutableState2 = mutableState;
                        TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function12, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                        composerStartRestartGroup = composerStartRestartGroup;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        mutableState2 = mutableState;
                        composerStartRestartGroup.startReplaceGroup(-1890863476);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    int i112 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                    tooltipState2 = tooltipState;
                    boolean z1110 = z10;
                    boolean z1111 = z11;
                    modifier3 = modifier4;
                    WrappedAnchor(z1110, tooltipState2, mutableState2, z1111, modifier3, function3, composerStartRestartGroup, i112, 0);
                    composerStartRestartGroup.endNode();
                    if ((i3 & 896) != 256) {
                        z5 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z7 = z1110;
                    z8 = z1111;
                    function5 = function6;
                } else {
                    tooltipState2 = tooltipState;
                    composerStartRestartGroup.skipToGroupEnd();
                    z7 = z2;
                    modifier3 = modifier2;
                    function5 = function1;
                    z8 = z3;
                }
                Composer composer7 = composerStartRestartGroup;
                z9 = z4;
                scopeUpdateScopeEndRestartGroup = composer7.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final TooltipState tooltipState8 = tooltipState2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState8, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function1 = function0;
            i9 = i2 & 32;
            if (i9 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i10 = 196608;
                z4 = z;
            } else {
                i10 = 196608;
                z4 = z;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(z4)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 64;
            if (i12 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i13 = 1048576;
                } else {
                    i13 = 524288;
                }
                i3 |= i13;
            }
            i14 = i2 & 128;
            if (i14 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z3)) {
                    i15 = 8388608;
                } else {
                    i15 = 4194304;
                }
                i3 |= i15;
            }
            if ((i2 & 256) != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i16 = 33554432;
                    }
                    i3 |= i16;
                }
                z5 = true;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i7 != 0) {
                        function6 = null;
                    } else {
                        function6 = function1;
                    }
                    if (i9 != 0) {
                        z4 = false;
                    }
                    if (i12 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i14 != 0) {
                        z11 = false;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    coroutineScope = (CoroutineScope) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableState = (MutableState) objRememberedValue2;
                    if (z11) {
                        z12 = false;
                    } else {
                        z12 = false;
                    }
                    Modifier.Companion companion14 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion14);
                    ComposeUiNode.Companion companion15 = ComposeUiNode.INSTANCE;
                    boolean z1112 = z12;
                    constructor = companion15.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy7, companion15.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap7, companion15.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion15.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier7, companion15.getSetModifier());
                    BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                    if (tooltipState.getIsVisible()) {
                        composerStartRestartGroup.startReplaceGroup(-1891243071);
                        if (z4) {
                            z13 = true;
                        } else {
                            z13 = true;
                        }
                        Function2<? super Composer, ? super Integer, Unit> function13 = function4;
                        mutableState2 = mutableState;
                        TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function13, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                        composerStartRestartGroup = composerStartRestartGroup;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        mutableState2 = mutableState;
                        composerStartRestartGroup.startReplaceGroup(-1890863476);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    int i113 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                    tooltipState2 = tooltipState;
                    boolean z1113 = z10;
                    boolean z1114 = z11;
                    modifier3 = modifier4;
                    WrappedAnchor(z1113, tooltipState2, mutableState2, z1114, modifier3, function3, composerStartRestartGroup, i113, 0);
                    composerStartRestartGroup.endNode();
                    if ((i3 & 896) != 256) {
                        z5 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z7 = z1113;
                    z8 = z1114;
                    function5 = function6;
                } else {
                    tooltipState2 = tooltipState;
                    composerStartRestartGroup.skipToGroupEnd();
                    z7 = z2;
                    modifier3 = modifier2;
                    function5 = function1;
                    z8 = z3;
                }
                Composer composer8 = composerStartRestartGroup;
                z9 = z4;
                scopeUpdateScopeEndRestartGroup = composer8.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final TooltipState tooltipState9 = tooltipState2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState9, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            z5 = true;
            if ((i3 & 38347923) != 38347922) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i7 != 0) {
                    function6 = null;
                } else {
                    function6 = function1;
                }
                if (i9 != 0) {
                    z4 = false;
                }
                if (i12 != 0) {
                    z10 = true;
                } else {
                    z10 = z2;
                }
                if (i14 != 0) {
                    z11 = false;
                } else {
                    z11 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                coroutineScope = (CoroutineScope) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableState = (MutableState) objRememberedValue2;
                if (z11) {
                    z12 = false;
                } else {
                    z12 = false;
                }
                Modifier.Companion companion16 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion16);
                ComposeUiNode.Companion companion17 = ComposeUiNode.INSTANCE;
                boolean z1115 = z12;
                constructor = companion17.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy8, companion17.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap8, companion17.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion17.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier8, companion17.getSetModifier());
                BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
                if (tooltipState.getIsVisible()) {
                    composerStartRestartGroup.startReplaceGroup(-1891243071);
                    if (z4) {
                        z13 = true;
                    } else {
                        z13 = true;
                    }
                    Function2<? super Composer, ? super Integer, Unit> function14 = function4;
                    mutableState2 = mutableState;
                    TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function14, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    mutableState2 = mutableState;
                    composerStartRestartGroup.startReplaceGroup(-1890863476);
                    composerStartRestartGroup.endReplaceGroup();
                }
                int i114 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                tooltipState2 = tooltipState;
                boolean z1116 = z10;
                boolean z1117 = z11;
                modifier3 = modifier4;
                WrappedAnchor(z1116, tooltipState2, mutableState2, z1117, modifier3, function3, composerStartRestartGroup, i114, 0);
                composerStartRestartGroup.endNode();
                if ((i3 & 896) != 256) {
                    z5 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue3 = new Function1() { // from class: ts0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function1() { // from class: ts0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z7 = z1116;
                z8 = z1117;
                function5 = function6;
            } else {
                tooltipState2 = tooltipState;
                composerStartRestartGroup.skipToGroupEnd();
                z7 = z2;
                modifier3 = modifier2;
                function5 = function1;
                z8 = z3;
            }
            Composer composer9 = composerStartRestartGroup;
            z9 = z4;
            scopeUpdateScopeEndRestartGroup = composer9.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final TooltipState tooltipState10 = tooltipState2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                    public final Object invoke(Object obj, Object obj2) {
                        return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState10, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        function4 = function2;
        if ((i2 & 4) != 0) {
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        } else if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i & 512) == 0) {
                zChangedInstance = composerStartRestartGroup.changed(tooltipState);
            } else {
                zChangedInstance = composerStartRestartGroup.changedInstance(tooltipState);
            }
            if (zChangedInstance) {
                i4 = 256;
            } else {
                i4 = 128;
            }
            i3 |= i4;
        }
        i5 = i2 & 8;
        if (i5 != 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i3 |= i6;
            }
            i7 = i2 & 16;
            if (i7 != 0) {
                if ((i & 24576) == 0) {
                    function1 = function0;
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                i9 = i2 & 32;
                if (i9 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    i10 = 196608;
                    z4 = z;
                } else {
                    i10 = 196608;
                    z4 = z;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(z4)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 64;
                if (i12 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i13 = 1048576;
                    } else {
                        i13 = 524288;
                    }
                    i3 |= i13;
                }
                i14 = i2 & 128;
                if (i14 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i15 = 8388608;
                    } else {
                        i15 = 4194304;
                    }
                    i3 |= i15;
                }
                if ((i2 & 256) != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i16 = 33554432;
                        }
                        i3 |= i16;
                    }
                    z5 = true;
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        if (i5 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i7 != 0) {
                            function6 = null;
                        } else {
                            function6 = function1;
                        }
                        if (i9 != 0) {
                            z4 = false;
                        }
                        if (i12 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i14 != 0) {
                            z11 = false;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        coroutineScope = (CoroutineScope) objRememberedValue;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        mutableState = (MutableState) objRememberedValue2;
                        if (z11) {
                            z12 = false;
                        } else {
                            z12 = false;
                        }
                        Modifier.Companion companion18 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy9 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion18);
                        ComposeUiNode.Companion companion19 = ComposeUiNode.INSTANCE;
                        boolean z1118 = z12;
                        constructor = companion19.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy9, companion19.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap9, companion19.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion19.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier9, companion19.getSetModifier());
                        BoxScopeInstance boxScopeInstance9 = BoxScopeInstance.INSTANCE;
                        if (tooltipState.getIsVisible()) {
                            composerStartRestartGroup.startReplaceGroup(-1891243071);
                            if (z4) {
                                z13 = true;
                            } else {
                                z13 = true;
                            }
                            Function2<? super Composer, ? super Integer, Unit> function15 = function4;
                            mutableState2 = mutableState;
                            TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function15, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                            composerStartRestartGroup = composerStartRestartGroup;
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            mutableState2 = mutableState;
                            composerStartRestartGroup.startReplaceGroup(-1890863476);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        int i115 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                        tooltipState2 = tooltipState;
                        boolean z1119 = z10;
                        boolean z11110 = z11;
                        modifier3 = modifier4;
                        WrappedAnchor(z1119, tooltipState2, mutableState2, z11110, modifier3, function3, composerStartRestartGroup, i115, 0);
                        composerStartRestartGroup.endNode();
                        if ((i3 & 896) != 256) {
                            z5 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue3 = new Function1() { // from class: ts0
                                public final Object invoke(Object obj) {
                                    return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1() { // from class: ts0
                                public final Object invoke(Object obj) {
                                    return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z7 = z1119;
                        z8 = z11110;
                        function5 = function6;
                    } else {
                        tooltipState2 = tooltipState;
                        composerStartRestartGroup.skipToGroupEnd();
                        z7 = z2;
                        modifier3 = modifier2;
                        function5 = function1;
                        z8 = z3;
                    }
                    Composer composer10 = composerStartRestartGroup;
                    z9 = z4;
                    scopeUpdateScopeEndRestartGroup = composer10.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final TooltipState tooltipState11 = tooltipState2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState11, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                z5 = true;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i7 != 0) {
                        function6 = null;
                    } else {
                        function6 = function1;
                    }
                    if (i9 != 0) {
                        z4 = false;
                    }
                    if (i12 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i14 != 0) {
                        z11 = false;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    coroutineScope = (CoroutineScope) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableState = (MutableState) objRememberedValue2;
                    if (z11) {
                        z12 = false;
                    } else {
                        z12 = false;
                    }
                    Modifier.Companion companion110 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy10 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion110);
                    ComposeUiNode.Companion companion111 = ComposeUiNode.INSTANCE;
                    boolean z11111 = z12;
                    constructor = companion111.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy10, companion111.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap10, companion111.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion111.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier10, companion111.getSetModifier());
                    BoxScopeInstance boxScopeInstance10 = BoxScopeInstance.INSTANCE;
                    if (tooltipState.getIsVisible()) {
                        composerStartRestartGroup.startReplaceGroup(-1891243071);
                        if (z4) {
                            z13 = true;
                        } else {
                            z13 = true;
                        }
                        Function2<? super Composer, ? super Integer, Unit> function16 = function4;
                        mutableState2 = mutableState;
                        TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function16, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                        composerStartRestartGroup = composerStartRestartGroup;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        mutableState2 = mutableState;
                        composerStartRestartGroup.startReplaceGroup(-1890863476);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    int i116 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                    tooltipState2 = tooltipState;
                    boolean z11112 = z10;
                    boolean z11113 = z11;
                    modifier3 = modifier4;
                    WrappedAnchor(z11112, tooltipState2, mutableState2, z11113, modifier3, function3, composerStartRestartGroup, i116, 0);
                    composerStartRestartGroup.endNode();
                    if ((i3 & 896) != 256) {
                        z5 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z7 = z11112;
                    z8 = z11113;
                    function5 = function6;
                } else {
                    tooltipState2 = tooltipState;
                    composerStartRestartGroup.skipToGroupEnd();
                    z7 = z2;
                    modifier3 = modifier2;
                    function5 = function1;
                    z8 = z3;
                }
                Composer composer11 = composerStartRestartGroup;
                z9 = z4;
                scopeUpdateScopeEndRestartGroup = composer11.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final TooltipState tooltipState12 = tooltipState2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState12, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function1 = function0;
            i9 = i2 & 32;
            if (i9 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i10 = 196608;
                z4 = z;
            } else {
                i10 = 196608;
                z4 = z;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(z4)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 64;
            if (i12 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i13 = 1048576;
                } else {
                    i13 = 524288;
                }
                i3 |= i13;
            }
            i14 = i2 & 128;
            if (i14 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z3)) {
                    i15 = 8388608;
                } else {
                    i15 = 4194304;
                }
                i3 |= i15;
            }
            if ((i2 & 256) != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i16 = 33554432;
                    }
                    i3 |= i16;
                }
                z5 = true;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i7 != 0) {
                        function6 = null;
                    } else {
                        function6 = function1;
                    }
                    if (i9 != 0) {
                        z4 = false;
                    }
                    if (i12 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i14 != 0) {
                        z11 = false;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    coroutineScope = (CoroutineScope) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableState = (MutableState) objRememberedValue2;
                    if (z11) {
                        z12 = false;
                    } else {
                        z12 = false;
                    }
                    Modifier.Companion companion112 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy11 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion112);
                    ComposeUiNode.Companion companion113 = ComposeUiNode.INSTANCE;
                    boolean z11114 = z12;
                    constructor = companion113.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy11, companion113.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap11, companion113.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion113.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier11, companion113.getSetModifier());
                    BoxScopeInstance boxScopeInstance11 = BoxScopeInstance.INSTANCE;
                    if (tooltipState.getIsVisible()) {
                        composerStartRestartGroup.startReplaceGroup(-1891243071);
                        if (z4) {
                            z13 = true;
                        } else {
                            z13 = true;
                        }
                        Function2<? super Composer, ? super Integer, Unit> function17 = function4;
                        mutableState2 = mutableState;
                        TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function17, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                        composerStartRestartGroup = composerStartRestartGroup;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        mutableState2 = mutableState;
                        composerStartRestartGroup.startReplaceGroup(-1890863476);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    int i117 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                    tooltipState2 = tooltipState;
                    boolean z11115 = z10;
                    boolean z11116 = z11;
                    modifier3 = modifier4;
                    WrappedAnchor(z11115, tooltipState2, mutableState2, z11116, modifier3, function3, composerStartRestartGroup, i117, 0);
                    composerStartRestartGroup.endNode();
                    if ((i3 & 896) != 256) {
                        z5 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z7 = z11115;
                    z8 = z11116;
                    function5 = function6;
                } else {
                    tooltipState2 = tooltipState;
                    composerStartRestartGroup.skipToGroupEnd();
                    z7 = z2;
                    modifier3 = modifier2;
                    function5 = function1;
                    z8 = z3;
                }
                Composer composer12 = composerStartRestartGroup;
                z9 = z4;
                scopeUpdateScopeEndRestartGroup = composer12.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final TooltipState tooltipState13 = tooltipState2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState13, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            z5 = true;
            if ((i3 & 38347923) != 38347922) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i7 != 0) {
                    function6 = null;
                } else {
                    function6 = function1;
                }
                if (i9 != 0) {
                    z4 = false;
                }
                if (i12 != 0) {
                    z10 = true;
                } else {
                    z10 = z2;
                }
                if (i14 != 0) {
                    z11 = false;
                } else {
                    z11 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                coroutineScope = (CoroutineScope) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableState = (MutableState) objRememberedValue2;
                if (z11) {
                    z12 = false;
                } else {
                    z12 = false;
                }
                Modifier.Companion companion114 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy12 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion114);
                ComposeUiNode.Companion companion115 = ComposeUiNode.INSTANCE;
                boolean z11117 = z12;
                constructor = companion115.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy12, companion115.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap12, companion115.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion115.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier12, companion115.getSetModifier());
                BoxScopeInstance boxScopeInstance12 = BoxScopeInstance.INSTANCE;
                if (tooltipState.getIsVisible()) {
                    composerStartRestartGroup.startReplaceGroup(-1891243071);
                    if (z4) {
                        z13 = true;
                    } else {
                        z13 = true;
                    }
                    Function2<? super Composer, ? super Integer, Unit> function18 = function4;
                    mutableState2 = mutableState;
                    TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function18, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    mutableState2 = mutableState;
                    composerStartRestartGroup.startReplaceGroup(-1890863476);
                    composerStartRestartGroup.endReplaceGroup();
                }
                int i118 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                tooltipState2 = tooltipState;
                boolean z11118 = z10;
                boolean z11119 = z11;
                modifier3 = modifier4;
                WrappedAnchor(z11118, tooltipState2, mutableState2, z11119, modifier3, function3, composerStartRestartGroup, i118, 0);
                composerStartRestartGroup.endNode();
                if ((i3 & 896) != 256) {
                    z5 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue3 = new Function1() { // from class: ts0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function1() { // from class: ts0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z7 = z11118;
                z8 = z11119;
                function5 = function6;
            } else {
                tooltipState2 = tooltipState;
                composerStartRestartGroup.skipToGroupEnd();
                z7 = z2;
                modifier3 = modifier2;
                function5 = function1;
                z8 = z3;
            }
            Composer composer13 = composerStartRestartGroup;
            z9 = z4;
            scopeUpdateScopeEndRestartGroup = composer13.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final TooltipState tooltipState14 = tooltipState2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                    public final Object invoke(Object obj, Object obj2) {
                        return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState14, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        modifier2 = modifier;
        i7 = i2 & 16;
        if (i7 != 0) {
            if ((i & 24576) == 0) {
                function1 = function0;
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
            i9 = i2 & 32;
            if (i9 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i10 = 196608;
                z4 = z;
            } else {
                i10 = 196608;
                z4 = z;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(z4)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 64;
            if (i12 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i13 = 1048576;
                } else {
                    i13 = 524288;
                }
                i3 |= i13;
            }
            i14 = i2 & 128;
            if (i14 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z3)) {
                    i15 = 8388608;
                } else {
                    i15 = 4194304;
                }
                i3 |= i15;
            }
            if ((i2 & 256) != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i16 = 33554432;
                    }
                    i3 |= i16;
                }
                z5 = true;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i7 != 0) {
                        function6 = null;
                    } else {
                        function6 = function1;
                    }
                    if (i9 != 0) {
                        z4 = false;
                    }
                    if (i12 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i14 != 0) {
                        z11 = false;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    coroutineScope = (CoroutineScope) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableState = (MutableState) objRememberedValue2;
                    if (z11) {
                        z12 = false;
                    } else {
                        z12 = false;
                    }
                    Modifier.Companion companion116 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy13 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion116);
                    ComposeUiNode.Companion companion117 = ComposeUiNode.INSTANCE;
                    boolean z111110 = z12;
                    constructor = companion117.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy13, companion117.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap13, companion117.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion117.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier13, companion117.getSetModifier());
                    BoxScopeInstance boxScopeInstance13 = BoxScopeInstance.INSTANCE;
                    if (tooltipState.getIsVisible()) {
                        composerStartRestartGroup.startReplaceGroup(-1891243071);
                        if (z4) {
                            z13 = true;
                        } else {
                            z13 = true;
                        }
                        Function2<? super Composer, ? super Integer, Unit> function19 = function4;
                        mutableState2 = mutableState;
                        TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function19, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                        composerStartRestartGroup = composerStartRestartGroup;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        mutableState2 = mutableState;
                        composerStartRestartGroup.startReplaceGroup(-1890863476);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    int i119 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                    tooltipState2 = tooltipState;
                    boolean z111111 = z10;
                    boolean z111112 = z11;
                    modifier3 = modifier4;
                    WrappedAnchor(z111111, tooltipState2, mutableState2, z111112, modifier3, function3, composerStartRestartGroup, i119, 0);
                    composerStartRestartGroup.endNode();
                    if ((i3 & 896) != 256) {
                        z5 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: ts0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z7 = z111111;
                    z8 = z111112;
                    function5 = function6;
                } else {
                    tooltipState2 = tooltipState;
                    composerStartRestartGroup.skipToGroupEnd();
                    z7 = z2;
                    modifier3 = modifier2;
                    function5 = function1;
                    z8 = z3;
                }
                Composer composer14 = composerStartRestartGroup;
                z9 = z4;
                scopeUpdateScopeEndRestartGroup = composer14.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final TooltipState tooltipState15 = tooltipState2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState15, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            z5 = true;
            if ((i3 & 38347923) != 38347922) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i7 != 0) {
                    function6 = null;
                } else {
                    function6 = function1;
                }
                if (i9 != 0) {
                    z4 = false;
                }
                if (i12 != 0) {
                    z10 = true;
                } else {
                    z10 = z2;
                }
                if (i14 != 0) {
                    z11 = false;
                } else {
                    z11 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                coroutineScope = (CoroutineScope) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableState = (MutableState) objRememberedValue2;
                if (z11) {
                    z12 = false;
                } else {
                    z12 = false;
                }
                Modifier.Companion companion118 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy14 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion118);
                ComposeUiNode.Companion companion119 = ComposeUiNode.INSTANCE;
                boolean z111113 = z12;
                constructor = companion119.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy14, companion119.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap14, companion119.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion119.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier14, companion119.getSetModifier());
                BoxScopeInstance boxScopeInstance14 = BoxScopeInstance.INSTANCE;
                if (tooltipState.getIsVisible()) {
                    composerStartRestartGroup.startReplaceGroup(-1891243071);
                    if (z4) {
                        z13 = true;
                    } else {
                        z13 = true;
                    }
                    Function2<? super Composer, ? super Integer, Unit> function110 = function4;
                    mutableState2 = mutableState;
                    TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function110, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    mutableState2 = mutableState;
                    composerStartRestartGroup.startReplaceGroup(-1890863476);
                    composerStartRestartGroup.endReplaceGroup();
                }
                int i1110 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                tooltipState2 = tooltipState;
                boolean z111114 = z10;
                boolean z111115 = z11;
                modifier3 = modifier4;
                WrappedAnchor(z111114, tooltipState2, mutableState2, z111115, modifier3, function3, composerStartRestartGroup, i1110, 0);
                composerStartRestartGroup.endNode();
                if ((i3 & 896) != 256) {
                    z5 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue3 = new Function1() { // from class: ts0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function1() { // from class: ts0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z7 = z111114;
                z8 = z111115;
                function5 = function6;
            } else {
                tooltipState2 = tooltipState;
                composerStartRestartGroup.skipToGroupEnd();
                z7 = z2;
                modifier3 = modifier2;
                function5 = function1;
                z8 = z3;
            }
            Composer composer15 = composerStartRestartGroup;
            z9 = z4;
            scopeUpdateScopeEndRestartGroup = composer15.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final TooltipState tooltipState16 = tooltipState2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                    public final Object invoke(Object obj, Object obj2) {
                        return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState16, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        function1 = function0;
        i9 = i2 & 32;
        if (i9 != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i10 = 196608;
            z4 = z;
        } else {
            i10 = 196608;
            z4 = z;
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(z4)) {
                    i11 = 131072;
                } else {
                    i11 = 65536;
                }
                i3 |= i11;
            }
        }
        i12 = i2 & 64;
        if (i12 != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changed(z2)) {
                i13 = 1048576;
            } else {
                i13 = 524288;
            }
            i3 |= i13;
        }
        i14 = i2 & 128;
        if (i14 != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(z3)) {
                i15 = 8388608;
            } else {
                i15 = 4194304;
            }
            i3 |= i15;
        }
        if ((i2 & 256) != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i16 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i16 = 33554432;
                }
                i3 |= i16;
            }
            z5 = true;
            if ((i3 & 38347923) != 38347922) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i7 != 0) {
                    function6 = null;
                } else {
                    function6 = function1;
                }
                if (i9 != 0) {
                    z4 = false;
                }
                if (i12 != 0) {
                    z10 = true;
                } else {
                    z10 = z2;
                }
                if (i14 != 0) {
                    z11 = false;
                } else {
                    z11 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                coroutineScope = (CoroutineScope) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableState = (MutableState) objRememberedValue2;
                if (z11) {
                    z12 = false;
                } else {
                    z12 = false;
                }
                Modifier.Companion companion1110 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy15 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion1110);
                ComposeUiNode.Companion companion1111 = ComposeUiNode.INSTANCE;
                boolean z111116 = z12;
                constructor = companion1111.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy15, companion1111.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap15, companion1111.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion1111.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier15, companion1111.getSetModifier());
                BoxScopeInstance boxScopeInstance15 = BoxScopeInstance.INSTANCE;
                if (tooltipState.getIsVisible()) {
                    composerStartRestartGroup.startReplaceGroup(-1891243071);
                    if (z4) {
                        z13 = true;
                    } else {
                        z13 = true;
                    }
                    Function2<? super Composer, ? super Integer, Unit> function111 = function4;
                    mutableState2 = mutableState;
                    TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function111, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    mutableState2 = mutableState;
                    composerStartRestartGroup.startReplaceGroup(-1890863476);
                    composerStartRestartGroup.endReplaceGroup();
                }
                int i1111 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
                tooltipState2 = tooltipState;
                boolean z111117 = z10;
                boolean z111118 = z11;
                modifier3 = modifier4;
                WrappedAnchor(z111117, tooltipState2, mutableState2, z111118, modifier3, function3, composerStartRestartGroup, i1111, 0);
                composerStartRestartGroup.endNode();
                if ((i3 & 896) != 256) {
                    z5 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue3 = new Function1() { // from class: ts0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function1() { // from class: ts0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z7 = z111117;
                z8 = z111118;
                function5 = function6;
            } else {
                tooltipState2 = tooltipState;
                composerStartRestartGroup.skipToGroupEnd();
                z7 = z2;
                modifier3 = modifier2;
                function5 = function1;
                z8 = z3;
            }
            Composer composer16 = composerStartRestartGroup;
            z9 = z4;
            scopeUpdateScopeEndRestartGroup = composer16.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final TooltipState tooltipState17 = tooltipState2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                    public final Object invoke(Object obj, Object obj2) {
                        return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState17, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 100663296;
        z5 = true;
        if ((i3 & 38347923) != 38347922) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
            if (i5 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i7 != 0) {
                function6 = null;
            } else {
                function6 = function1;
            }
            if (i9 != 0) {
                z4 = false;
            }
            if (i12 != 0) {
                z10 = true;
            } else {
                z10 = z2;
            }
            if (i14 != 0) {
                z11 = false;
            } else {
                z11 = z3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1221877520, i3, -1, "androidx.compose.material3.internal.BasicTooltipBox (BasicTooltip.kt:103)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            coroutineScope = (CoroutineScope) objRememberedValue;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            mutableState = (MutableState) objRememberedValue2;
            if (z11) {
                z12 = false;
            } else {
                z12 = false;
            }
            Modifier.Companion companion1112 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy16 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion1112);
            ComposeUiNode.Companion companion1113 = ComposeUiNode.INSTANCE;
            boolean z111119 = z12;
            constructor = companion1113.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy16, companion1113.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap16, companion1113.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion1113.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting()) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier16, companion1113.getSetModifier());
            BoxScopeInstance boxScopeInstance16 = BoxScopeInstance.INSTANCE;
            if (tooltipState.getIsVisible()) {
                composerStartRestartGroup.startReplaceGroup(-1891243071);
                if (z4) {
                    z13 = true;
                } else {
                    z13 = true;
                }
                Function2<? super Composer, ? super Integer, Unit> function112 = function4;
                mutableState2 = mutableState;
                TooltipPopup(popupPositionProvider, tooltipState, function6, coroutineScope, z13, mutableState2, function112, composerStartRestartGroup, (i3 & 14) | i10 | ((i3 >> 3) & 112) | ((i3 >> 6) & 896) | ((i3 << 15) & 3670016));
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                mutableState2 = mutableState;
                composerStartRestartGroup.startReplaceGroup(-1890863476);
                composerStartRestartGroup.endReplaceGroup();
            }
            int i1112 = ((i3 >> 18) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i3 >> 3) & 112) | ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & (i3 << 3)) | ((i3 >> 9) & 458752);
            tooltipState2 = tooltipState;
            boolean z1111110 = z10;
            boolean z1111111 = z11;
            modifier3 = modifier4;
            WrappedAnchor(z1111110, tooltipState2, mutableState2, z1111111, modifier3, function3, composerStartRestartGroup, i1112, 0);
            composerStartRestartGroup.endNode();
            if ((i3 & 896) != 256) {
                z5 = false;
            }
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z5) {
                objRememberedValue3 = new Function1() { // from class: ts0
                    public final Object invoke(Object obj) {
                        return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = new Function1() { // from class: ts0
                    public final Object invoke(Object obj) {
                        return BasicTooltipKt.g(tooltipState2, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.DisposableEffect(tooltipState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, (i3 >> 6) & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z7 = z1111110;
            z8 = z1111111;
            function5 = function6;
        } else {
            tooltipState2 = tooltipState;
            composerStartRestartGroup.skipToGroupEnd();
            z7 = z2;
            modifier3 = modifier2;
            function5 = function1;
            z8 = z3;
        }
        Composer composer17 = composerStartRestartGroup;
        z9 = z4;
        scopeUpdateScopeEndRestartGroup = composer17.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final TooltipState tooltipState18 = tooltipState2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vs0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTooltipKt.f(popupPositionProvider, function2, tooltipState18, modifier3, function5, z9, z7, z8, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final TooltipState BasicTooltipState(boolean z, boolean z2, MutatorMutex mutatorMutex) {
        return new BasicTooltipStateImpl(z, z2, mutatorMutex);
    }

    public static /* synthetic */ TooltipState BasicTooltipState$default(boolean z, boolean z2, MutatorMutex mutatorMutex, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        return BasicTooltipState(z, z2, mutatorMutex);
    }

    private static final void TooltipPopup(final PopupPositionProvider popupPositionProvider, final TooltipState tooltipState, final Function0<Unit> function0, final CoroutineScope coroutineScope, final boolean z, final MutableState<Boolean> mutableState, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1413720282);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(popupPositionProvider) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composerStartRestartGroup.changed(tooltipState) : composerStartRestartGroup.changedInstance(tooltipState) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(coroutineScope) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableState) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 1048576 : 524288;
        }
        if (composerStartRestartGroup.shouldExecute((599187 & i2) != 599186, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1413720282, i2, -1, "androidx.compose.material3.internal.TooltipPopup (BasicTooltip.kt:169)");
            }
            String strDescription = BasicTooltipStrings.INSTANCE.description(composerStartRestartGroup, 6);
            boolean zChangedInstance = ((i2 & 896) == 256) | ((i2 & 112) == 32 || ((i2 & 64) != 0 && composerStartRestartGroup.changedInstance(tooltipState))) | composerStartRestartGroup.changedInstance(coroutineScope) | ((458752 & i2) == 131072);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: ps0
                    public final Object invoke() {
                        return BasicTooltipKt.a(function0, tooltipState, coroutineScope, mutableState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            AndroidPopup_androidKt.Popup(popupPositionProvider, (Function0) objRememberedValue, new PopupProperties(z, false, false, false, 14, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(-1287705660, true, new AnonymousClass2(strDescription, function2), composerStartRestartGroup, 54), composerStartRestartGroup, (i2 & 14) | 3072, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rs0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTooltipKt.b(popupPositionProvider, tooltipState, function0, coroutineScope, z, mutableState, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x019b  */
    /* JADX WARN: Code duplicated, block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x007e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0083  */
    /* JADX WARN: Code duplicated, block: B:53:0x0087  */
    /* JADX WARN: Code duplicated, block: B:55:0x008f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0092  */
    /* JADX WARN: Code duplicated, block: B:60:0x009b  */
    /* JADX WARN: Code duplicated, block: B:62:0x009e  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:66:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:76:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:79:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:85:0x0124  */
    /* JADX WARN: Code duplicated, block: B:88:0x0130  */
    /* JADX WARN: Code duplicated, block: B:89:0x0134  */
    /* JADX WARN: Code duplicated, block: B:92:0x0153  */
    /* JADX WARN: Code duplicated, block: B:94:0x0161  */
    /* JADX WARN: Code duplicated, block: B:97:0x018c  */
    /* JADX WARN: Code duplicated, block: B:99:0x0191  */
    private static final void WrappedAnchor(final boolean z, final TooltipState tooltipState, final MutableState<Boolean> mutableState, final boolean z2, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        MutableState<Boolean> mutableState2;
        boolean z3;
        int i4;
        Modifier modifier2;
        int i5;
        int i6;
        int i7;
        boolean z4;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Object objRememberedValue;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM2388constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        Composer composerStartRestartGroup = composer.startRestartGroup(1873232064);
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
            i3 |= (i & 64) == 0 ? composerStartRestartGroup.changed(tooltipState) : composerStartRestartGroup.changedInstance(tooltipState) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            mutableState2 = mutableState;
        } else {
            mutableState2 = mutableState;
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                i3 |= composerStartRestartGroup.changed(mutableState2) ? 256 : 128;
            }
        }
        if ((i2 & 8) == 0) {
            if ((i & 3072) == 0) {
                z3 = z2;
                i3 |= composerStartRestartGroup.changed(z3) ? 2048 : 1024;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((i & 24576) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i5 = 16384;
                    } else {
                        i5 = 8192;
                    }
                    i3 |= i5;
                }
                if ((i2 & 32) != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i6 = 131072;
                    } else {
                        i6 = 65536;
                    }
                    i3 |= i6;
                }
                i7 = i3;
                if ((74899 & i7) != 74898) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i7 & 1)) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1873232064, i7, -1, "androidx.compose.material3.internal.WrappedAnchor (BasicTooltip.kt:146)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue;
                    Modifier modifierKeyboardBehavior = keyboardBehavior(anchorSemantics(handleGestures(modifier2, z, tooltipState), BasicTooltipStrings.INSTANCE.label(composerStartRestartGroup, 6), z, tooltipState, coroutineScope), z, tooltipState, coroutineScope, z3, mutableState2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierKeyboardBehavior);
                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                    constructor = companion.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    function2.invoke(composerStartRestartGroup, Integer.valueOf((i7 >> 15) & 14));
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                }
                modifier3 = modifier2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dt0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTooltipKt.h(z, tooltipState, mutableState, z2, modifier3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            modifier2 = modifier;
            if ((i2 & 32) != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i6 = 131072;
                } else {
                    i6 = 65536;
                }
                i3 |= i6;
            }
            i7 = i3;
            if ((74899 & i7) != 74898) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i7 & 1)) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1873232064, i7, -1, "androidx.compose.material3.internal.WrappedAnchor (BasicTooltip.kt:146)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CoroutineScope coroutineScope2 = (CoroutineScope) objRememberedValue;
                Modifier modifierKeyboardBehavior2 = keyboardBehavior(anchorSemantics(handleGestures(modifier2, z, tooltipState), BasicTooltipStrings.INSTANCE.label(composerStartRestartGroup, 6), z, tooltipState, coroutineScope2), z, tooltipState, coroutineScope2, z3, mutableState2);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierKeyboardBehavior2);
                ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                constructor = companion2.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier2, companion2.getSetModifier());
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                function2.invoke(composerStartRestartGroup, Integer.valueOf((i7 >> 15) & 14));
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            modifier3 = modifier2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dt0
                    public final Object invoke(Object obj, Object obj2) {
                        return BasicTooltipKt.h(z, tooltipState, mutableState, z2, modifier3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z3 = z2;
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((i & 24576) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i5 = 16384;
                } else {
                    i5 = 8192;
                }
                i3 |= i5;
            }
            if ((i2 & 32) != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i6 = 131072;
                } else {
                    i6 = 65536;
                }
                i3 |= i6;
            }
            i7 = i3;
            if ((74899 & i7) != 74898) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i7 & 1)) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1873232064, i7, -1, "androidx.compose.material3.internal.WrappedAnchor (BasicTooltip.kt:146)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CoroutineScope coroutineScope3 = (CoroutineScope) objRememberedValue;
                Modifier modifierKeyboardBehavior3 = keyboardBehavior(anchorSemantics(handleGestures(modifier2, z, tooltipState), BasicTooltipStrings.INSTANCE.label(composerStartRestartGroup, 6), z, tooltipState, coroutineScope3), z, tooltipState, coroutineScope3, z3, mutableState2);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierKeyboardBehavior3);
                ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                constructor = companion3.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, companion3.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier3, companion3.getSetModifier());
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                function2.invoke(composerStartRestartGroup, Integer.valueOf((i7 >> 15) & 14));
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            modifier3 = modifier2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dt0
                    public final Object invoke(Object obj, Object obj2) {
                        return BasicTooltipKt.h(z, tooltipState, mutableState, z2, modifier3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        modifier2 = modifier;
        if ((i2 & 32) != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i6 = 131072;
            } else {
                i6 = 65536;
            }
            i3 |= i6;
        }
        i7 = i3;
        if ((74899 & i7) != 74898) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z4, i7 & 1)) {
            if (i4 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1873232064, i7, -1, "androidx.compose.material3.internal.WrappedAnchor (BasicTooltip.kt:146)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CoroutineScope coroutineScope4 = (CoroutineScope) objRememberedValue;
            Modifier modifierKeyboardBehavior4 = keyboardBehavior(anchorSemantics(handleGestures(modifier2, z, tooltipState), BasicTooltipStrings.INSTANCE.label(composerStartRestartGroup, 6), z, tooltipState, coroutineScope4), z, tooltipState, coroutineScope4, z3, mutableState2);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierKeyboardBehavior4);
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            constructor = companion4.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy4, companion4.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap4, companion4.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion4.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting()) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier4, companion4.getSetModifier());
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            function2.invoke(composerStartRestartGroup, Integer.valueOf((i7 >> 15) & 14));
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        modifier3 = modifier2;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dt0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTooltipKt.h(z, tooltipState, mutableState, z2, modifier3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Function0 function0, TooltipState tooltipState, CoroutineScope coroutineScope, MutableState mutableState) {
        if (function0 != null) {
            function0.invoke();
        } else if (tooltipState.getIsVisible()) {
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BasicTooltipKt$TooltipPopup$1$1$1(tooltipState, null), 3, (Object) null);
            mutableState.setValue(Boolean.FALSE);
        }
        return Unit.INSTANCE;
    }

    private static final Modifier anchorSemantics(Modifier modifier, final String str, boolean z, final TooltipState tooltipState, final CoroutineScope coroutineScope) {
        return z ? ChildParentSemanticsKt.parentSemantics(modifier, new Function1() { // from class: zs0
            public final Object invoke(Object obj) {
                return BasicTooltipKt.d(str, coroutineScope, tooltipState, (SemanticsPropertyReceiver) obj);
            }
        }) : modifier;
    }

    public static Unit b(PopupPositionProvider popupPositionProvider, TooltipState tooltipState, Function0 function0, CoroutineScope coroutineScope, boolean z, MutableState mutableState, Function2 function2, int i, Composer composer, int i2) {
        TooltipPopup(popupPositionProvider, tooltipState, function0, coroutineScope, z, mutableState, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static boolean c(CoroutineScope coroutineScope, TooltipState tooltipState) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BasicTooltipKt$anchorSemantics$1$1$1(tooltipState, null), 3, (Object) null);
        return true;
    }

    public static Unit d(String str, final CoroutineScope coroutineScope, final TooltipState tooltipState, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.onLongClick(semanticsPropertyReceiver, str, new Function0() { // from class: bt0
            public final Object invoke() {
                return Boolean.valueOf(BasicTooltipKt.c(coroutineScope, tooltipState));
            }
        });
        return Unit.INSTANCE;
    }

    public static Unit e(CoroutineScope coroutineScope, TooltipState tooltipState, FocusState focusState) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BasicTooltipKt$keyboardBehavior$1$1(focusState, tooltipState, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit f(PopupPositionProvider popupPositionProvider, Function2 function2, TooltipState tooltipState, Modifier modifier, Function0 function0, boolean z, boolean z2, boolean z3, Function2 function3, int i, int i2, Composer composer, int i3) {
        BasicTooltipBox(popupPositionProvider, function2, tooltipState, modifier, function0, z, z2, z3, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static DisposableEffectResult g(final TooltipState tooltipState, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.material3.internal.BasicTooltipKt$BasicTooltipBox$lambda$4$lambda$3$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                tooltipState.onDispose();
            }
        };
    }

    public static Unit h(boolean z, TooltipState tooltipState, MutableState mutableState, boolean z2, Modifier modifier, Function2 function2, int i, int i2, Composer composer, int i3) {
        WrappedAnchor(z, tooltipState, mutableState, z2, modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private static final Modifier handleGestures(Modifier modifier, boolean z, final TooltipState tooltipState) {
        return z ? SuspendingPointerInputFilterKt.pointerInput(SuspendingPointerInputFilterKt.pointerInput(modifier, tooltipState, new PointerInputEventHandler() { // from class: androidx.compose.material3.internal.BasicTooltipKt.handleGestures.1
            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C00181(pointerInputScope, tooltipState, null), continuation);
                return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipKt$handleGestures$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
            @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltipKt$handleGestures$1$1", f = "BasicTooltip.kt", i = {}, l = {ComposerKt.providerValuesKey}, m = "invokeSuspend", n = {}, s = {})
            public static final class C00181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ TooltipState $state;
                final /* synthetic */ PointerInputScope $this_pointerInput;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00181(PointerInputScope pointerInputScope, TooltipState tooltipState, Continuation<? super C00181> continuation) {
                    super(2, continuation);
                    this.$this_pointerInput = pointerInputScope;
                    this.$state = tooltipState;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00181 c00181 = new C00181(this.$this_pointerInput, this.$state, continuation);
                    c00181.L$0 = obj;
                    return c00181;
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        PointerInputScope pointerInputScope = this.$this_pointerInput;
                        C00191 c00191 = new C00191(coroutineScope, this.$state, null);
                        this.label = 1;
                        if (ForEachGestureKt.awaitEachGesture(pointerInputScope, c00191, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipKt$handleGestures$1$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
                @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltipKt$handleGestures$1$1$1", f = "BasicTooltip.kt", i = {0, 0, 0, 0, 1, 1, 1, 2}, l = {210, 216, 238}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "isLongPressedFlow", "pass", "longPressTimeout", "$this$awaitEachGesture", "isLongPressedFlow", "pass", "isLongPressedFlow"}, s = {"L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "L$0"})
                public static final class C00191 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ CoroutineScope $$this$coroutineScope;
                    final /* synthetic */ TooltipState $state;
                    long J$0;
                    private /* synthetic */ Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;

                    /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipKt$handleGestures$1$1$1$1, reason: invalid class name and collision with other inner class name */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
                    @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltipKt$handleGestures$1$1$1$1", f = "BasicTooltip.kt", i = {}, l = {217}, m = "invokeSuspend", n = {}, s = {})
                    public static final class C00201 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super PointerInputChange>, Object> {
                        final /* synthetic */ PointerEventPass $pass;
                        private /* synthetic */ Object L$0;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C00201(PointerEventPass pointerEventPass, Continuation<? super C00201> continuation) {
                            super(2, continuation);
                            this.$pass = pointerEventPass;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            C00201 c00201 = new C00201(this.$pass, continuation);
                            c00201.L$0 = obj;
                            return c00201;
                        }

                        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super PointerInputChange> continuation) {
                            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                        }

                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i != 0) {
                                if (i == 1) {
                                    ResultKt.throwOnFailure(obj);
                                    return obj;
                                }
                                k2d.a("call to 'resume' before 'invoke' with coroutine");
                                return null;
                            }
                            ResultKt.throwOnFailure(obj);
                            AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                            PointerEventPass pointerEventPass = this.$pass;
                            this.label = 1;
                            Object objWaitForUpOrCancellation = TapGestureDetectorKt.waitForUpOrCancellation(awaitPointerEventScope, pointerEventPass, this);
                            return objWaitForUpOrCancellation == coroutine_suspended ? coroutine_suspended : objWaitForUpOrCancellation;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00191(CoroutineScope coroutineScope, TooltipState tooltipState, Continuation<? super C00191> continuation) {
                        super(2, continuation);
                        this.$$this$coroutineScope = coroutineScope;
                        this.$state = tooltipState;
                    }

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00191 c00191 = new C00191(this.$$this$coroutineScope, this.$state, continuation);
                        c00191.L$0 = obj;
                        return c00191;
                    }

                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                        return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:35:0x00de, code lost:
                    
                        if (r0 == r6) goto L36;
                     */
                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Type inference failed for: r1v0 */
                    /* JADX WARN: Type inference failed for: r1v7 */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final Object invokeSuspend(Object obj) throws Throwable {
                        long longPressTimeoutMillis;
                        Object objAwaitFirstDown$default;
                        AwaitPointerEventScope awaitPointerEventScope;
                        PointerEventPass pointerEventPass;
                        MutableStateFlow mutableStateFlow;
                        MutableStateFlow mutableStateFlow2;
                        Object objWaitForUpOrCancellation;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        MutableSharedFlow mutableSharedFlow = 1;
                        try {
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                                MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boxing.boxBoolean(false));
                                longPressTimeoutMillis = awaitPointerEventScope2.getViewConfiguration().getLongPressTimeoutMillis();
                                PointerEventPass pointerEventPass2 = PointerEventPass.Initial;
                                this.L$0 = awaitPointerEventScope2;
                                this.L$1 = MutableStateFlow;
                                this.L$2 = pointerEventPass2;
                                this.J$0 = longPressTimeoutMillis;
                                this.label = 1;
                                objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope2, false, pointerEventPass2, this, 1, (Object) null);
                                if (objAwaitFirstDown$default != coroutine_suspended) {
                                    awaitPointerEventScope = awaitPointerEventScope2;
                                    pointerEventPass = pointerEventPass2;
                                    mutableStateFlow = MutableStateFlow;
                                }
                                return coroutine_suspended;
                            }
                            if (i == 1) {
                                long j = this.J$0;
                                PointerEventPass pointerEventPass3 = (PointerEventPass) this.L$2;
                                mutableStateFlow = (MutableStateFlow) this.L$1;
                                AwaitPointerEventScope awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                                ResultKt.throwOnFailure(obj);
                                longPressTimeoutMillis = j;
                                pointerEventPass = pointerEventPass3;
                                awaitPointerEventScope = awaitPointerEventScope3;
                                objAwaitFirstDown$default = obj;
                            } else if (i == 2) {
                                pointerEventPass = (PointerEventPass) this.L$2;
                                mutableStateFlow2 = (MutableStateFlow) this.L$1;
                                awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                                try {
                                    ResultKt.throwOnFailure(obj);
                                } catch (PointerEventTimeoutCancellationException unused) {
                                    BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new AnonymousClass3(mutableStateFlow2, this.$state, null), 1, (Object) null);
                                    this.L$0 = mutableStateFlow2;
                                    this.L$1 = null;
                                    this.L$2 = null;
                                    this.label = 3;
                                    objWaitForUpOrCancellation = TapGestureDetectorKt.waitForUpOrCancellation(awaitPointerEventScope, pointerEventPass, this);
                                }
                            } else {
                                if (i != 3) {
                                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                                    return null;
                                }
                                mutableStateFlow2 = (MutableStateFlow) this.L$0;
                                ResultKt.throwOnFailure(obj);
                                objWaitForUpOrCancellation = obj;
                                PointerInputChange pointerInputChange = (PointerInputChange) objWaitForUpOrCancellation;
                                if (pointerInputChange != null) {
                                    pointerInputChange.consume();
                                }
                            }
                            mutableStateFlow2.tryEmit(Boxing.boxBoolean(false));
                            return Unit.INSTANCE;
                            int type = ((PointerInputChange) objAwaitFirstDown$default).getType();
                            PointerType.Companion companion = PointerType.INSTANCE;
                            if (PointerType.m4529equalsimpl0(type, companion.m4536getTouchT8wyACA()) || PointerType.m4529equalsimpl0(type, companion.m4535getStylusT8wyACA())) {
                                try {
                                    C00201 c00201 = new C00201(pointerEventPass, null);
                                    this.L$0 = awaitPointerEventScope;
                                    this.L$1 = mutableStateFlow;
                                    this.L$2 = pointerEventPass;
                                    this.label = 2;
                                    if (awaitPointerEventScope.withTimeout(longPressTimeoutMillis, c00201, this) != coroutine_suspended) {
                                        mutableStateFlow2 = mutableStateFlow;
                                        mutableStateFlow2.tryEmit(Boxing.boxBoolean(false));
                                    }
                                } catch (PointerEventTimeoutCancellationException unused2) {
                                    mutableStateFlow2 = mutableStateFlow;
                                    BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new AnonymousClass3(mutableStateFlow2, this.$state, null), 1, (Object) null);
                                    this.L$0 = mutableStateFlow2;
                                    this.L$1 = null;
                                    this.L$2 = null;
                                    this.label = 3;
                                    objWaitForUpOrCancellation = TapGestureDetectorKt.waitForUpOrCancellation(awaitPointerEventScope, pointerEventPass, this);
                                } catch (Throwable th) {
                                    th = th;
                                    mutableSharedFlow = mutableStateFlow;
                                    mutableSharedFlow.tryEmit(Boxing.boxBoolean(false));
                                    throw th;
                                }
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipKt$handleGestures$1$1$1$3, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
                    @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltipKt$handleGestures$1$1$1$3", f = "BasicTooltip.kt", i = {}, l = {BERTags.FLAGS, 227, 227}, m = "invokeSuspend", n = {}, s = {})
                    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ MutableStateFlow<Boolean> $isLongPressedFlow;
                        final /* synthetic */ TooltipState $state;
                        Object L$0;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass3(MutableStateFlow<Boolean> mutableStateFlow, TooltipState tooltipState, Continuation<? super AnonymousClass3> continuation) {
                            super(2, continuation);
                            this.$isLongPressedFlow = mutableStateFlow;
                            this.$state = tooltipState;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass3(this.$isLongPressedFlow, this.$state, continuation);
                        }

                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Code restructure failed: missing block: B:22:0x005b, code lost:
                        
                            if (kotlinx.coroutines.flow.FlowKt.collectLatest(r7, r1, r6) == r0) goto L30;
                         */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public final Object invokeSuspend(Object obj) throws Throwable {
                            Throwable th;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            try {
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    this.$isLongPressedFlow.tryEmit(Boxing.boxBoolean(true));
                                    TooltipState tooltipState = this.$state;
                                    MutatePriority mutatePriority = MutatePriority.PreventUserInput;
                                    this.label = 1;
                                    if (tooltipState.show(mutatePriority, this) != coroutine_suspended) {
                                    }
                                    return coroutine_suspended;
                                }
                                if (i == 1) {
                                    ResultKt.throwOnFailure(obj);
                                } else {
                                    if (i != 2) {
                                        if (i != 3) {
                                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                                            return null;
                                        }
                                        th = (Throwable) this.L$0;
                                        ResultKt.throwOnFailure(obj);
                                        throw th;
                                    }
                                    ResultKt.throwOnFailure(obj);
                                }
                                return Unit.INSTANCE;
                                if (this.$state.getIsVisible()) {
                                    MutableStateFlow<Boolean> mutableStateFlow = this.$isLongPressedFlow;
                                    C00211 c00211 = new C00211(this.$state, null);
                                    this.label = 2;
                                }
                                return Unit.INSTANCE;
                            } catch (Throwable th2) {
                                if (!this.$state.getIsVisible()) {
                                    throw th2;
                                }
                                MutableStateFlow<Boolean> mutableStateFlow2 = this.$isLongPressedFlow;
                                C00211 c00212 = new C00211(this.$state, null);
                                this.L$0 = th2;
                                this.label = 3;
                                if (FlowKt.collectLatest(mutableStateFlow2, c00212, this) != coroutine_suspended) {
                                    th = th2;
                                }
                                return coroutine_suspended;
                            }
                        }

                        /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipKt$handleGestures$1$1$1$3$1, reason: invalid class name and collision with other inner class name */
                        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "isLongPressed", ""}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
                        @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltipKt$handleGestures$1$1$1$3$1", f = "BasicTooltip.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                        public static final class C00211 extends SuspendLambda implements Function2<Boolean, Continuation<? super Unit>, Object> {
                            final /* synthetic */ TooltipState $state;
                            /* synthetic */ boolean Z$0;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public C00211(TooltipState tooltipState, Continuation<? super C00211> continuation) {
                                super(2, continuation);
                                this.$state = tooltipState;
                            }

                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                C00211 c00211 = new C00211(this.$state, continuation);
                                c00211.Z$0 = ((Boolean) obj).booleanValue();
                                return c00211;
                            }

                            public final Object invoke(boolean z, Continuation<? super Unit> continuation) {
                                return create(Boolean.valueOf(z), continuation).invokeSuspend(Unit.INSTANCE);
                            }

                            public final Object invokeSuspend(Object obj) {
                                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                if (this.label != 0) {
                                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                                    return null;
                                }
                                ResultKt.throwOnFailure(obj);
                                if (!this.Z$0) {
                                    this.$state.dismiss();
                                }
                                return Unit.INSTANCE;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                return invoke(((Boolean) obj).booleanValue(), (Continuation<? super Unit>) obj2);
                            }
                        }
                    }
                }
            }
        }), tooltipState, new PointerInputEventHandler() { // from class: androidx.compose.material3.internal.BasicTooltipKt.handleGestures.2

            /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipKt$handleGestures$2$1, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
            @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltipKt$handleGestures$2$1", f = "BasicTooltip.kt", i = {}, l = {249}, m = "invokeSuspend", n = {}, s = {})
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ TooltipState $state;
                final /* synthetic */ PointerInputScope $this_pointerInput;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipKt$handleGestures$2$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
                @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltipKt$handleGestures$2$1$1", f = "BasicTooltip.kt", i = {0, 0}, l = {253}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "pass"}, s = {"L$0", "L$1"})
                public static final class C00221 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ CoroutineScope $$this$coroutineScope;
                    final /* synthetic */ TooltipState $state;
                    private /* synthetic */ Object L$0;
                    Object L$1;
                    int label;

                    /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipKt$handleGestures$2$1$1$1, reason: invalid class name and collision with other inner class name */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
                    @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltipKt$handleGestures$2$1$1$1", f = "BasicTooltip.kt", i = {}, l = {AndroidBinXmlParser.Chunk.RES_XML_TYPE_START_ELEMENT}, m = "invokeSuspend", n = {}, s = {})
                    public static final class C00231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ TooltipState $state;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C00231(TooltipState tooltipState, Continuation<? super C00231> continuation) {
                            super(2, continuation);
                            this.$state = tooltipState;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C00231(this.$state, continuation);
                        }

                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                        }

                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                TooltipState tooltipState = this.$state;
                                MutatePriority mutatePriority = MutatePriority.UserInput;
                                this.label = 1;
                                if (tooltipState.show(mutatePriority, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                                    return null;
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00221(CoroutineScope coroutineScope, TooltipState tooltipState, Continuation<? super C00221> continuation) {
                        super(2, continuation);
                        this.$$this$coroutineScope = coroutineScope;
                        this.$state = tooltipState;
                    }

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00221 c00221 = new C00221(this.$$this$coroutineScope, this.$state, continuation);
                        c00221.L$0 = obj;
                        return c00221;
                    }

                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                        return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Code duplicated, block: B:11:0x0034 A[RETURN] */
                    /* JADX WARN: Code duplicated, block: B:14:0x0052  */
                    /* JADX WARN: Code duplicated, block: B:16:0x0062  */
                    /* JADX WARN: Code duplicated, block: B:17:0x0073  */
                    /* JADX WARN: Code duplicated, block: B:19:0x007d  */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0032 -> B:12:0x0035). Please report as a decompilation issue!!! */
                    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:0:?
                        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                        */
                    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                        /*
                            r13 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r13.label
                            r2 = 0
                            r3 = 1
                            if (r1 == 0) goto L1e
                            if (r1 != r3) goto L18
                            java.lang.Object r1 = r13.L$1
                            androidx.compose.ui.input.pointer.PointerEventPass r1 = (androidx.compose.ui.input.pointer.PointerEventPass) r1
                            java.lang.Object r4 = r13.L$0
                            androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                            kotlin.ResultKt.throwOnFailure(r14)
                            goto L35
                        L18:
                            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                            k2d.a(r13)
                            return r2
                        L1e:
                            kotlin.ResultKt.throwOnFailure(r14)
                            java.lang.Object r14 = r13.L$0
                            androidx.compose.ui.input.pointer.AwaitPointerEventScope r14 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r14
                            androidx.compose.ui.input.pointer.PointerEventPass r1 = androidx.compose.ui.input.pointer.PointerEventPass.Main
                            r4 = r14
                        L28:
                            r13.L$0 = r4
                            r13.L$1 = r1
                            r13.label = r3
                            java.lang.Object r14 = r4.awaitPointerEvent(r1, r13)
                            if (r14 != r0) goto L35
                            return r0
                        L35:
                            androidx.compose.ui.input.pointer.PointerEvent r14 = (androidx.compose.ui.input.pointer.PointerEvent) r14
                            java.util.List r5 = r14.getChanges()
                            r6 = 0
                            java.lang.Object r5 = r5.get(r6)
                            androidx.compose.ui.input.pointer.PointerInputChange r5 = (androidx.compose.ui.input.pointer.PointerInputChange) r5
                            int r5 = r5.getType()
                            androidx.compose.ui.input.pointer.PointerType$Companion r6 = androidx.compose.ui.input.pointer.PointerType.INSTANCE
                            int r6 = r6.m4534getMouseT8wyACA()
                            boolean r5 = androidx.compose.ui.input.pointer.PointerType.m4529equalsimpl0(r5, r6)
                            if (r5 == 0) goto L28
                            int r14 = r14.getType()
                            androidx.compose.ui.input.pointer.PointerEventType$Companion r5 = androidx.compose.ui.input.pointer.PointerEventType.INSTANCE
                            int r6 = r5.m4409getEnter7fucELk()
                            boolean r6 = androidx.compose.ui.input.pointer.PointerEventType.m4405equalsimpl0(r14, r6)
                            if (r6 == 0) goto L73
                            kotlinx.coroutines.CoroutineScope r7 = r13.$$this$coroutineScope
                            androidx.compose.material3.internal.BasicTooltipKt$handleGestures$2$1$1$1 r10 = new androidx.compose.material3.internal.BasicTooltipKt$handleGestures$2$1$1$1
                            androidx.compose.material3.TooltipState r14 = r13.$state
                            r10.<init>(r14, r2)
                            r11 = 3
                            r12 = 0
                            r8 = 0
                            r9 = 0
                            kotlinx.coroutines.BuildersKt.launch$default(r7, r8, r9, r10, r11, r12)
                            goto L28
                        L73:
                            int r5 = r5.m4410getExit7fucELk()
                            boolean r14 = androidx.compose.ui.input.pointer.PointerEventType.m4405equalsimpl0(r14, r5)
                            if (r14 == 0) goto L28
                            androidx.compose.material3.TooltipState r14 = r13.$state
                            r14.dismiss()
                            goto L28
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.BasicTooltipKt.C00692.AnonymousClass1.C00221.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(PointerInputScope pointerInputScope, TooltipState tooltipState, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$this_pointerInput = pointerInputScope;
                    this.$state = tooltipState;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_pointerInput, this.$state, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        PointerInputScope pointerInputScope = this.$this_pointerInput;
                        C00221 c00221 = new C00221(coroutineScope, this.$state, null);
                        this.label = 1;
                        if (pointerInputScope.awaitPointerEventScope(c00221, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass1(pointerInputScope, tooltipState, null), continuation);
                return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
            }
        }) : modifier;
    }

    private static final Modifier keyboardBehavior(Modifier modifier, boolean z, final TooltipState tooltipState, final CoroutineScope coroutineScope, final boolean z2, final MutableState<Boolean> mutableState) {
        if (z) {
            return KeyInputModifierKt.onPreviewKeyEvent(FocusChangedModifierKt.onFocusChanged(modifier, new Function1() { // from class: xs0
                public final Object invoke(Object obj) {
                    return BasicTooltipKt.e(coroutineScope, tooltipState, (FocusState) obj);
                }
            }), new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.internal.BasicTooltipKt.keyboardBehavior.2
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m1379invokeZmokQxo(((KeyEvent) obj).m4284unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m1379invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    if (!tooltipState.getIsVisible()) {
                        mutableState.setValue(Boolean.FALSE);
                    }
                    if (!z2 || !KeyEventType.m4288equalsimpl0(KeyEvent_androidKt.m4296getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4292getKeyDownCS__XNY()) || !Key.m3987equalsimpl0(KeyEvent_androidKt.m4295getKeyZmokQxo(keyEvent), Key.INSTANCE.m4223getTabEK5gGoQ()) || !tooltipState.getIsVisible()) {
                        return Boolean.FALSE;
                    }
                    MutableState<Boolean> mutableState2 = mutableState;
                    Boolean bool = Boolean.TRUE;
                    mutableState2.setValue(bool);
                    return bool;
                }
            });
        }
        mutableState.setValue(Boolean.FALSE);
        return modifier;
    }

    public static final TooltipState rememberBasicTooltipState(boolean z, boolean z2, MutatorMutex mutatorMutex, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        if ((i2 & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1483057531, i, -1, "androidx.compose.material3.internal.rememberBasicTooltipState (BasicTooltip.kt:346)");
        }
        boolean z3 = ((((i & 112) ^ 48) > 32 && composer.changed(z2)) || (i & 48) == 32) | ((((i & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 && composer.changed(mutatorMutex)) || (i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new BasicTooltipStateImpl(z, z2, mutatorMutex);
            composer.updateRememberedValue(objRememberedValue);
        }
        BasicTooltipStateImpl basicTooltipStateImpl = (BasicTooltipStateImpl) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return basicTooltipStateImpl;
    }

    private static final State<Boolean> rememberTouchExplorationOrSwitchAccessServiceState(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1960751094, i, -1, "androidx.compose.material3.internal.rememberTouchExplorationOrSwitchAccessServiceState (BasicTooltip.kt:456)");
        }
        State<Boolean> stateRememberAccessibilityServiceState = AccessibilityServiceStateProvider_androidKt.rememberAccessibilityServiceState(true, true, false, composer, 438, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateRememberAccessibilityServiceState;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipKt$TooltipPopup$2, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ Function2<Composer, Integer, Unit> $content;
        final /* synthetic */ String $tooltipDescription;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(String str, Function2<? super Composer, ? super Integer, Unit> function2) {
            this.$tooltipDescription = str;
            this.$content = function2;
        }

        public static Unit a(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.m5263setLiveRegionhR3wRGc(semanticsPropertyReceiver, LiveRegionMode.INSTANCE.m5236getAssertive0phEisY());
            SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, str);
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1287705660, i, -1, "androidx.compose.material3.internal.TooltipPopup.<anonymous> (BasicTooltip.kt:186)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            boolean zChanged = composer.changed(this.$tooltipDescription);
            final String str = this.$tooltipDescription;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.internal.i
                    public final Object invoke(Object obj) {
                        return BasicTooltipKt.AnonymousClass2.a(str, (SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) objRememberedValue, 1, null);
            Function2<Composer, Integer, Unit> function2 = this.$content;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierSemantics$default);
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion2.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            function2.invoke(composer, 0);
            composer.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }
}
