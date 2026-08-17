package androidx.compose.material3;

import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.material3.LabelKt;
import androidx.compose.material3.internal.BasicTooltipKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.window.PopupPositionProvider;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a^\u0010\u0000\u001a\u00020\u00012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0002\u0010\u000f\u001a%\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\nH\u0003¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {TextFieldImplKt.LabelId, "", "label", "Lkotlin/Function1;", "Landroidx/compose/material3/TooltipScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "isPersistent", "", "content", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "HandleInteractions", "enabled", "state", "Landroidx/compose/material3/TooltipState;", "(ZLandroidx/compose/material3/TooltipState;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;I)V", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LabelKt {
    private static final void HandleInteractions(final boolean z, final TooltipState tooltipState, final MutableInteractionSource mutableInteractionSource, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-627258109);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composerStartRestartGroup.changed(tooltipState) : composerStartRestartGroup.changedInstance(tooltipState) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 256 : 128;
        }
        boolean z2 = false;
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-627258109, i2, -1, "androidx.compose.material3.HandleInteractions (Label.kt:108)");
            }
            if (z) {
                composerStartRestartGroup.startReplaceGroup(756598818);
                boolean z3 = (i2 & 896) == 256;
                if ((i2 & 112) == 32 || ((i2 & 64) != 0 && composerStartRestartGroup.changedInstance(tooltipState))) {
                    z2 = true;
                }
                boolean z4 = z3 | z2;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z4 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new LabelKt$HandleInteractions$1$1(mutableInteractionSource, tooltipState, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue, composerStartRestartGroup, (i2 >> 6) & 14);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(757210975);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: wm8
                public final Object invoke(Object obj, Object obj2) {
                    return LabelKt.c(z, tooltipState, mutableInteractionSource, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x004a  */
    /* JADX WARN: Code duplicated, block: B:30:0x004e  */
    /* JADX WARN: Code duplicated, block: B:32:0x0056  */
    /* JADX WARN: Code duplicated, block: B:33:0x0059  */
    /* JADX WARN: Code duplicated, block: B:37:0x0060  */
    /* JADX WARN: Code duplicated, block: B:39:0x0065  */
    /* JADX WARN: Code duplicated, block: B:41:0x0069  */
    /* JADX WARN: Code duplicated, block: B:43:0x0071  */
    /* JADX WARN: Code duplicated, block: B:44:0x0074  */
    /* JADX WARN: Code duplicated, block: B:48:0x007b  */
    /* JADX WARN: Code duplicated, block: B:49:0x007e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0082  */
    /* JADX WARN: Code duplicated, block: B:53:0x0088  */
    /* JADX WARN: Code duplicated, block: B:54:0x008b  */
    /* JADX WARN: Code duplicated, block: B:58:0x0096  */
    /* JADX WARN: Code duplicated, block: B:59:0x0098  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:63:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:67:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:68:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:74:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:76:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:83:0x0100  */
    /* JADX WARN: Code duplicated, block: B:85:0x0112  */
    /* JADX WARN: Code duplicated, block: B:88:0x0122  */
    /* JADX WARN: Code duplicated, block: B:91:0x014a  */
    /* JADX WARN: Code duplicated, block: B:94:0x0160  */
    /* JADX WARN: Code duplicated, block: B:97:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:99:0x01b7  */
    public static final void Label(final Function3<? super TooltipScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, MutableInteractionSource mutableInteractionSource, boolean z, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        MutableInteractionSource mutableInteractionSource2;
        int i5;
        int i6;
        boolean z2;
        int i7;
        int i8;
        boolean z3;
        final Modifier modifier3;
        final MutableInteractionSource mutableInteractionSource3;
        final boolean z4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        MutableInteractionSource mutableInteractionSource4;
        boolean z5;
        MutableInteractionSource mutableInteractionSource5;
        PopupPositionProvider popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss;
        TooltipState tooltipStateRememberBasicTooltipState;
        final Ref.ObjectRef objectRef;
        Object objRememberedValue;
        Composer.Companion companion;
        Object objRememberedValue2;
        Object objRememberedValue3;
        Object objRememberedValue4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-458575864);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function3) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i9 = i2 & 2;
        if (i9 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i2 & 16) != 0) {
                        i3 |= 24576;
                    } else if ((i & 24576) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 9363) != 9362) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        if (i9 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-458575864, i3, -1, "androidx.compose.material3.Label (Label.kt:69)");
                        }
                        if (mutableInteractionSource4 == null) {
                            composerStartRestartGroup.startReplaceGroup(857748595);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue4;
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1690236644);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource5 = mutableInteractionSource4;
                        }
                        popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss = TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2);
                        if (z5) {
                            composerStartRestartGroup.startReplaceGroup(857995293);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new LabelStateImpl(false, false, 3, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            tooltipStateRememberBasicTooltipState = (LabelStateImpl) objRememberedValue3;
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1690246656);
                            tooltipStateRememberBasicTooltipState = BasicTooltipKt.rememberBasicTooltipState(false, false, new MutatorMutex(), composerStartRestartGroup, 0, 3);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        TooltipState tooltipState = tooltipStateRememberBasicTooltipState;
                        objectRef = new Ref.ObjectRef();
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        objectRef.element = (MutableState) objRememberedValue;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new TooltipScopeImpl(new Function0() { // from class: xm8
                                public final Object invoke() {
                                    return LabelKt.b(objectRef);
                                }
                            }, popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final TooltipScopeImpl tooltipScopeImpl = (TooltipScopeImpl) objRememberedValue2;
                        BasicTooltipKt.BasicTooltipBox(popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss, ComposableLambdaKt.rememberComposableLambda(-1572484206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt.Label.1
                            public final void invoke(Composer composer2, int i10) {
                                if (!composer2.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1572484206, i10, -1, "androidx.compose.material3.Label.<anonymous> (Label.kt:88)");
                                }
                                function3.invoke(tooltipScopeImpl, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), tooltipState, modifier4, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-44123786, true, new LabelKt$Label$wrappedContent$1(objectRef, function2), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 102432816, 144);
                        composerStartRestartGroup = composerStartRestartGroup;
                        HandleInteractions(!z5, tooltipState, mutableInteractionSource5, composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        mutableInteractionSource3 = mutableInteractionSource4;
                        z4 = z5;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        z4 = z2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ym8
                            public final Object invoke(Object obj, Object obj2) {
                                return LabelKt.a(function3, modifier3, mutableInteractionSource3, z4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 3072;
                z2 = z;
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-458575864, i3, -1, "androidx.compose.material3.Label (Label.kt:69)");
                    }
                    if (mutableInteractionSource4 == null) {
                        composerStartRestartGroup.startReplaceGroup(857748595);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue4;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1690236644);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = mutableInteractionSource4;
                    }
                    popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss = TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2);
                    if (z5) {
                        composerStartRestartGroup.startReplaceGroup(857995293);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new LabelStateImpl(false, false, 3, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        tooltipStateRememberBasicTooltipState = (LabelStateImpl) objRememberedValue3;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1690246656);
                        tooltipStateRememberBasicTooltipState = BasicTooltipKt.rememberBasicTooltipState(false, false, new MutatorMutex(), composerStartRestartGroup, 0, 3);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    TooltipState tooltipState2 = tooltipStateRememberBasicTooltipState;
                    objectRef = new Ref.ObjectRef();
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    objectRef.element = (MutableState) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new TooltipScopeImpl(new Function0() { // from class: xm8
                            public final Object invoke() {
                                return LabelKt.b(objectRef);
                            }
                        }, popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final TooltipScopeImpl tooltipScopeImpl2 = (TooltipScopeImpl) objRememberedValue2;
                    BasicTooltipKt.BasicTooltipBox(popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss, ComposableLambdaKt.rememberComposableLambda(-1572484206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt.Label.1
                        public final void invoke(Composer composer2, int i10) {
                            if (!composer2.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1572484206, i10, -1, "androidx.compose.material3.Label.<anonymous> (Label.kt:88)");
                            }
                            function3.invoke(tooltipScopeImpl2, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), tooltipState2, modifier4, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-44123786, true, new LabelKt$Label$wrappedContent$1(objectRef, function2), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 102432816, 144);
                    composerStartRestartGroup = composerStartRestartGroup;
                    HandleInteractions(!z5, tooltipState2, mutableInteractionSource5, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    mutableInteractionSource3 = mutableInteractionSource4;
                    z4 = z5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    mutableInteractionSource3 = mutableInteractionSource2;
                    z4 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ym8
                        public final Object invoke(Object obj, Object obj2) {
                            return LabelKt.a(function3, modifier3, mutableInteractionSource3, z4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            mutableInteractionSource2 = mutableInteractionSource;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-458575864, i3, -1, "androidx.compose.material3.Label (Label.kt:69)");
                    }
                    if (mutableInteractionSource4 == null) {
                        composerStartRestartGroup.startReplaceGroup(857748595);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue4;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1690236644);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = mutableInteractionSource4;
                    }
                    popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss = TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2);
                    if (z5) {
                        composerStartRestartGroup.startReplaceGroup(857995293);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new LabelStateImpl(false, false, 3, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        tooltipStateRememberBasicTooltipState = (LabelStateImpl) objRememberedValue3;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1690246656);
                        tooltipStateRememberBasicTooltipState = BasicTooltipKt.rememberBasicTooltipState(false, false, new MutatorMutex(), composerStartRestartGroup, 0, 3);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    TooltipState tooltipState3 = tooltipStateRememberBasicTooltipState;
                    objectRef = new Ref.ObjectRef();
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    objectRef.element = (MutableState) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new TooltipScopeImpl(new Function0() { // from class: xm8
                            public final Object invoke() {
                                return LabelKt.b(objectRef);
                            }
                        }, popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final TooltipScopeImpl tooltipScopeImpl3 = (TooltipScopeImpl) objRememberedValue2;
                    BasicTooltipKt.BasicTooltipBox(popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss, ComposableLambdaKt.rememberComposableLambda(-1572484206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt.Label.1
                        public final void invoke(Composer composer2, int i10) {
                            if (!composer2.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1572484206, i10, -1, "androidx.compose.material3.Label.<anonymous> (Label.kt:88)");
                            }
                            function3.invoke(tooltipScopeImpl3, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), tooltipState3, modifier4, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-44123786, true, new LabelKt$Label$wrappedContent$1(objectRef, function2), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 102432816, 144);
                    composerStartRestartGroup = composerStartRestartGroup;
                    HandleInteractions(!z5, tooltipState3, mutableInteractionSource5, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    mutableInteractionSource3 = mutableInteractionSource4;
                    z4 = z5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    mutableInteractionSource3 = mutableInteractionSource2;
                    z4 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ym8
                        public final Object invoke(Object obj, Object obj2) {
                            return LabelKt.a(function3, modifier3, mutableInteractionSource3, z4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i2 & 16) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    mutableInteractionSource4 = null;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource2;
                }
                if (i6 != 0) {
                    z5 = false;
                } else {
                    z5 = z2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-458575864, i3, -1, "androidx.compose.material3.Label (Label.kt:69)");
                }
                if (mutableInteractionSource4 == null) {
                    composerStartRestartGroup.startReplaceGroup(857748595);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue4;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1690236644);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = mutableInteractionSource4;
                }
                popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss = TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2);
                if (z5) {
                    composerStartRestartGroup.startReplaceGroup(857995293);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new LabelStateImpl(false, false, 3, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    tooltipStateRememberBasicTooltipState = (LabelStateImpl) objRememberedValue3;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1690246656);
                    tooltipStateRememberBasicTooltipState = BasicTooltipKt.rememberBasicTooltipState(false, false, new MutatorMutex(), composerStartRestartGroup, 0, 3);
                    composerStartRestartGroup.endReplaceGroup();
                }
                TooltipState tooltipState4 = tooltipStateRememberBasicTooltipState;
                objectRef = new Ref.ObjectRef();
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                objectRef.element = (MutableState) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new TooltipScopeImpl(new Function0() { // from class: xm8
                        public final Object invoke() {
                            return LabelKt.b(objectRef);
                        }
                    }, popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final TooltipScopeImpl tooltipScopeImpl4 = (TooltipScopeImpl) objRememberedValue2;
                BasicTooltipKt.BasicTooltipBox(popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss, ComposableLambdaKt.rememberComposableLambda(-1572484206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt.Label.1
                    public final void invoke(Composer composer2, int i10) {
                        if (!composer2.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1572484206, i10, -1, "androidx.compose.material3.Label.<anonymous> (Label.kt:88)");
                        }
                        function3.invoke(tooltipScopeImpl4, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), tooltipState4, modifier4, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-44123786, true, new LabelKt$Label$wrappedContent$1(objectRef, function2), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 102432816, 144);
                composerStartRestartGroup = composerStartRestartGroup;
                HandleInteractions(!z5, tooltipState4, mutableInteractionSource5, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                mutableInteractionSource3 = mutableInteractionSource4;
                z4 = z5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                mutableInteractionSource3 = mutableInteractionSource2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ym8
                    public final Object invoke(Object obj, Object obj2) {
                        return LabelKt.a(function3, modifier3, mutableInteractionSource3, z4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-458575864, i3, -1, "androidx.compose.material3.Label (Label.kt:69)");
                    }
                    if (mutableInteractionSource4 == null) {
                        composerStartRestartGroup.startReplaceGroup(857748595);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue4;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1690236644);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = mutableInteractionSource4;
                    }
                    popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss = TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2);
                    if (z5) {
                        composerStartRestartGroup.startReplaceGroup(857995293);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new LabelStateImpl(false, false, 3, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        tooltipStateRememberBasicTooltipState = (LabelStateImpl) objRememberedValue3;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1690246656);
                        tooltipStateRememberBasicTooltipState = BasicTooltipKt.rememberBasicTooltipState(false, false, new MutatorMutex(), composerStartRestartGroup, 0, 3);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    TooltipState tooltipState5 = tooltipStateRememberBasicTooltipState;
                    objectRef = new Ref.ObjectRef();
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    objectRef.element = (MutableState) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new TooltipScopeImpl(new Function0() { // from class: xm8
                            public final Object invoke() {
                                return LabelKt.b(objectRef);
                            }
                        }, popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final TooltipScopeImpl tooltipScopeImpl5 = (TooltipScopeImpl) objRememberedValue2;
                    BasicTooltipKt.BasicTooltipBox(popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss, ComposableLambdaKt.rememberComposableLambda(-1572484206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt.Label.1
                        public final void invoke(Composer composer2, int i10) {
                            if (!composer2.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1572484206, i10, -1, "androidx.compose.material3.Label.<anonymous> (Label.kt:88)");
                            }
                            function3.invoke(tooltipScopeImpl5, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), tooltipState5, modifier4, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-44123786, true, new LabelKt$Label$wrappedContent$1(objectRef, function2), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 102432816, 144);
                    composerStartRestartGroup = composerStartRestartGroup;
                    HandleInteractions(!z5, tooltipState5, mutableInteractionSource5, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    mutableInteractionSource3 = mutableInteractionSource4;
                    z4 = z5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    mutableInteractionSource3 = mutableInteractionSource2;
                    z4 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ym8
                        public final Object invoke(Object obj, Object obj2) {
                            return LabelKt.a(function3, modifier3, mutableInteractionSource3, z4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i2 & 16) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    mutableInteractionSource4 = null;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource2;
                }
                if (i6 != 0) {
                    z5 = false;
                } else {
                    z5 = z2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-458575864, i3, -1, "androidx.compose.material3.Label (Label.kt:69)");
                }
                if (mutableInteractionSource4 == null) {
                    composerStartRestartGroup.startReplaceGroup(857748595);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue4;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1690236644);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = mutableInteractionSource4;
                }
                popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss = TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2);
                if (z5) {
                    composerStartRestartGroup.startReplaceGroup(857995293);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new LabelStateImpl(false, false, 3, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    tooltipStateRememberBasicTooltipState = (LabelStateImpl) objRememberedValue3;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1690246656);
                    tooltipStateRememberBasicTooltipState = BasicTooltipKt.rememberBasicTooltipState(false, false, new MutatorMutex(), composerStartRestartGroup, 0, 3);
                    composerStartRestartGroup.endReplaceGroup();
                }
                TooltipState tooltipState6 = tooltipStateRememberBasicTooltipState;
                objectRef = new Ref.ObjectRef();
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                objectRef.element = (MutableState) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new TooltipScopeImpl(new Function0() { // from class: xm8
                        public final Object invoke() {
                            return LabelKt.b(objectRef);
                        }
                    }, popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final TooltipScopeImpl tooltipScopeImpl6 = (TooltipScopeImpl) objRememberedValue2;
                BasicTooltipKt.BasicTooltipBox(popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss, ComposableLambdaKt.rememberComposableLambda(-1572484206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt.Label.1
                    public final void invoke(Composer composer2, int i10) {
                        if (!composer2.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1572484206, i10, -1, "androidx.compose.material3.Label.<anonymous> (Label.kt:88)");
                        }
                        function3.invoke(tooltipScopeImpl6, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), tooltipState6, modifier4, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-44123786, true, new LabelKt$Label$wrappedContent$1(objectRef, function2), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 102432816, 144);
                composerStartRestartGroup = composerStartRestartGroup;
                HandleInteractions(!z5, tooltipState6, mutableInteractionSource5, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                mutableInteractionSource3 = mutableInteractionSource4;
                z4 = z5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                mutableInteractionSource3 = mutableInteractionSource2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ym8
                    public final Object invoke(Object obj, Object obj2) {
                        return LabelKt.a(function3, modifier3, mutableInteractionSource3, z4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        mutableInteractionSource2 = mutableInteractionSource;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i2 & 16) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    mutableInteractionSource4 = null;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource2;
                }
                if (i6 != 0) {
                    z5 = false;
                } else {
                    z5 = z2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-458575864, i3, -1, "androidx.compose.material3.Label (Label.kt:69)");
                }
                if (mutableInteractionSource4 == null) {
                    composerStartRestartGroup.startReplaceGroup(857748595);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue4;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1690236644);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = mutableInteractionSource4;
                }
                popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss = TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2);
                if (z5) {
                    composerStartRestartGroup.startReplaceGroup(857995293);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new LabelStateImpl(false, false, 3, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    tooltipStateRememberBasicTooltipState = (LabelStateImpl) objRememberedValue3;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1690246656);
                    tooltipStateRememberBasicTooltipState = BasicTooltipKt.rememberBasicTooltipState(false, false, new MutatorMutex(), composerStartRestartGroup, 0, 3);
                    composerStartRestartGroup.endReplaceGroup();
                }
                TooltipState tooltipState7 = tooltipStateRememberBasicTooltipState;
                objectRef = new Ref.ObjectRef();
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                objectRef.element = (MutableState) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new TooltipScopeImpl(new Function0() { // from class: xm8
                        public final Object invoke() {
                            return LabelKt.b(objectRef);
                        }
                    }, popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final TooltipScopeImpl tooltipScopeImpl7 = (TooltipScopeImpl) objRememberedValue2;
                BasicTooltipKt.BasicTooltipBox(popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss, ComposableLambdaKt.rememberComposableLambda(-1572484206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt.Label.1
                    public final void invoke(Composer composer2, int i10) {
                        if (!composer2.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1572484206, i10, -1, "androidx.compose.material3.Label.<anonymous> (Label.kt:88)");
                        }
                        function3.invoke(tooltipScopeImpl7, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), tooltipState7, modifier4, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-44123786, true, new LabelKt$Label$wrappedContent$1(objectRef, function2), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 102432816, 144);
                composerStartRestartGroup = composerStartRestartGroup;
                HandleInteractions(!z5, tooltipState7, mutableInteractionSource5, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                mutableInteractionSource3 = mutableInteractionSource4;
                z4 = z5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                mutableInteractionSource3 = mutableInteractionSource2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ym8
                    public final Object invoke(Object obj, Object obj2) {
                        return LabelKt.a(function3, modifier3, mutableInteractionSource3, z4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i2 & 16) != 0) {
            i3 |= 24576;
        } else if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i8 = 16384;
            } else {
                i8 = 8192;
            }
            i3 |= i8;
        }
        if ((i3 & 9363) != 9362) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            if (i9 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i4 != 0) {
                mutableInteractionSource4 = null;
            } else {
                mutableInteractionSource4 = mutableInteractionSource2;
            }
            if (i6 != 0) {
                z5 = false;
            } else {
                z5 = z2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-458575864, i3, -1, "androidx.compose.material3.Label (Label.kt:69)");
            }
            if (mutableInteractionSource4 == null) {
                composerStartRestartGroup.startReplaceGroup(857748595);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue4;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1690236644);
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource5 = mutableInteractionSource4;
            }
            popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss = TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2);
            if (z5) {
                composerStartRestartGroup.startReplaceGroup(857995293);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new LabelStateImpl(false, false, 3, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                tooltipStateRememberBasicTooltipState = (LabelStateImpl) objRememberedValue3;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1690246656);
                tooltipStateRememberBasicTooltipState = BasicTooltipKt.rememberBasicTooltipState(false, false, new MutatorMutex(), composerStartRestartGroup, 0, 3);
                composerStartRestartGroup.endReplaceGroup();
            }
            TooltipState tooltipState8 = tooltipStateRememberBasicTooltipState;
            objectRef = new Ref.ObjectRef();
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            objectRef.element = (MutableState) objRememberedValue;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new TooltipScopeImpl(new Function0() { // from class: xm8
                    public final Object invoke() {
                        return LabelKt.b(objectRef);
                    }
                }, popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final TooltipScopeImpl tooltipScopeImpl8 = (TooltipScopeImpl) objRememberedValue2;
            BasicTooltipKt.BasicTooltipBox(popupPositionProviderM1276rememberTooltipPositionProviderHu5FAss, ComposableLambdaKt.rememberComposableLambda(-1572484206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt.Label.1
                public final void invoke(Composer composer2, int i10) {
                    if (!composer2.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1572484206, i10, -1, "androidx.compose.material3.Label.<anonymous> (Label.kt:88)");
                    }
                    function3.invoke(tooltipScopeImpl8, composer2, 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), tooltipState8, modifier4, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-44123786, true, new LabelKt$Label$wrappedContent$1(objectRef, function2), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 102432816, 144);
            composerStartRestartGroup = composerStartRestartGroup;
            HandleInteractions(!z5, tooltipState8, mutableInteractionSource5, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            mutableInteractionSource3 = mutableInteractionSource4;
            z4 = z5;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            mutableInteractionSource3 = mutableInteractionSource2;
            z4 = z2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ym8
                public final Object invoke(Object obj, Object obj2) {
                    return LabelKt.a(function3, modifier3, mutableInteractionSource3, z4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Function3 function3, Modifier modifier, MutableInteractionSource mutableInteractionSource, boolean z, Function2 function2, int i, int i2, Composer composer, int i3) {
        Label(function3, modifier, mutableInteractionSource, z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static LayoutCoordinates b(Ref.ObjectRef objectRef) {
        return (LayoutCoordinates) ((MutableState) objectRef.element).getValue();
    }

    public static Unit c(boolean z, TooltipState tooltipState, MutableInteractionSource mutableInteractionSource, int i, Composer composer, int i2) {
        HandleInteractions(z, tooltipState, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }
}
