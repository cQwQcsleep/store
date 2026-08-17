package androidx.compose.ui.window;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aR\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0004\b\f\u0010\r\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\u0010\u001a \u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0002\u001a(\u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u001b2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0001¢\u0006\u0002\u0010\"\u001a+\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020%2\u0013\b\b\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0083\b¢\u0006\u0002\u0010&\u001a\f\u0010'\u001a\u00020\u0015*\u00020(H\u0000\u001a\u0014\u0010)\u001a\u00020\u0012*\u00020\t2\u0006\u0010*\u001a\u00020\u0015H\u0002\u001a\f\u0010+\u001a\u00020,*\u00020-H\u0002\u001a\u001c\u0010.\u001a\u00020\u00152\u0006\u0010/\u001a\u00020(2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u001bH\u0007\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00150\u001aX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001d¨\u00061²\u0006\u0015\u00102\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bX\u008a\u0084\u0002"}, d2 = {"Popup", "", "alignment", "Landroidx/compose/ui/Alignment;", "offset", "Landroidx/compose/ui/unit/IntOffset;", "onDismissRequest", "Lkotlin/Function0;", "properties", "Landroidx/compose/ui/window/PopupProperties;", "content", "Landroidx/compose/runtime/Composable;", "Popup-K5zGePQ", "(Landroidx/compose/ui/Alignment;JLkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "popupPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "PopupPropertiesBaseFlags", "", "createFlags", "focusable", "", "securePolicy", "Landroidx/compose/ui/window/SecureFlagPolicy;", "clippingEnabled", "LocalPopupTestTag", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "getLocalPopupTestTag", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalIsInPopupLayout", "getLocalIsInPopupLayout", "PopupTestTag", "tag", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SimpleStack", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "isFlagSecureEnabled", "Landroid/view/View;", "flagsWithSecureFlagInherited", "isParentFlagSecureEnabled", "toIntBounds", "Landroidx/compose/ui/unit/IntRect;", "Landroid/graphics/Rect;", "isPopupLayout", "view", "testTag", "ui", "currentContent"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidPopup_androidKt {
    private static final int PopupPropertiesBaseFlags = 262144;
    private static final ProvidableCompositionLocal<String> LocalPopupTestTag = CompositionLocalKt.compositionLocalOf$default(null, new Function0<String>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$LocalPopupTestTag$1
        public final String invoke() {
            return "DEFAULT_TEST_TAG";
        }
    }, 1, null);
    private static final ProvidableCompositionLocal<Boolean> LocalIsInPopupLayout = CompositionLocalKt.compositionLocalOf$default(null, new Function0<Boolean>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$LocalIsInPopupLayout$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Boolean m6288invoke() {
            return Boolean.FALSE;
        }
    }, 1, null);

    /* JADX WARN: Code duplicated, block: B:101:0x0206  */
    /* JADX WARN: Code duplicated, block: B:103:0x020c  */
    /* JADX WARN: Code duplicated, block: B:106:0x0227  */
    /* JADX WARN: Code duplicated, block: B:108:0x022d  */
    /* JADX WARN: Code duplicated, block: B:111:0x024e  */
    /* JADX WARN: Code duplicated, block: B:113:0x0254  */
    /* JADX WARN: Code duplicated, block: B:116:0x027b  */
    /* JADX WARN: Code duplicated, block: B:119:0x0287  */
    /* JADX WARN: Code duplicated, block: B:120:0x028b  */
    /* JADX WARN: Code duplicated, block: B:123:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:125:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:128:0x02d5  */
    /* JADX WARN: Code duplicated, block: B:130:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x003e  */
    /* JADX WARN: Code duplicated, block: B:25:0x0043  */
    /* JADX WARN: Code duplicated, block: B:27:0x0047  */
    /* JADX WARN: Code duplicated, block: B:29:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0052  */
    /* JADX WARN: Code duplicated, block: B:34:0x0059  */
    /* JADX WARN: Code duplicated, block: B:36:0x005f  */
    /* JADX WARN: Code duplicated, block: B:37:0x0062  */
    /* JADX WARN: Code duplicated, block: B:41:0x006d  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:45:0x0078  */
    /* JADX WARN: Code duplicated, block: B:47:0x007b  */
    /* JADX WARN: Code duplicated, block: B:48:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0082  */
    /* JADX WARN: Code duplicated, block: B:51:0x0094  */
    /* JADX WARN: Code duplicated, block: B:54:0x009c  */
    /* JADX WARN: Code duplicated, block: B:57:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:60:0x010c  */
    /* JADX WARN: Code duplicated, block: B:61:0x0141  */
    /* JADX WARN: Code duplicated, block: B:64:0x0154  */
    /* JADX WARN: Code duplicated, block: B:65:0x0156  */
    /* JADX WARN: Code duplicated, block: B:68:0x015e  */
    /* JADX WARN: Code duplicated, block: B:69:0x0160  */
    /* JADX WARN: Code duplicated, block: B:72:0x0176  */
    /* JADX WARN: Code duplicated, block: B:74:0x017c  */
    /* JADX WARN: Code duplicated, block: B:77:0x0196  */
    /* JADX WARN: Code duplicated, block: B:78:0x0198  */
    /* JADX WARN: Code duplicated, block: B:81:0x019e  */
    /* JADX WARN: Code duplicated, block: B:82:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:85:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:89:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:92:0x01df  */
    /* JADX WARN: Code duplicated, block: B:93:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:96:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:98:0x01ef  */
    public static final void Popup(PopupPositionProvider popupPositionProvider, Function0<Unit> function0, PopupProperties popupProperties, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        Function0<Unit> function1;
        int i4;
        PopupProperties popupProperties2;
        int i5;
        boolean z;
        final Function0<Unit> function3;
        final PopupProperties popupProperties3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final Function0<Unit> function4;
        final PopupProperties popupProperties4;
        View view;
        Density density;
        String str;
        final LayoutDirection layoutDirection;
        CompositionContext compositionContextRememberCompositionContext;
        final State stateRememberUpdatedState;
        Object objRememberedValue;
        Composer.Companion companion;
        UUID uuid;
        boolean zBooleanValue;
        Object objRememberedValue2;
        boolean z2;
        String str2;
        int i6;
        final PopupLayout popupLayout;
        int i7;
        boolean z3;
        int i8;
        boolean z4;
        boolean zChanged;
        Object objRememberedValue3;
        boolean z5;
        boolean z6;
        boolean zChanged2;
        Object objRememberedValue4;
        int i9;
        boolean z7;
        boolean z8;
        Object objRememberedValue5;
        boolean zChangedInstance;
        Object objRememberedValue6;
        boolean zChangedInstance2;
        Object objRememberedValue7;
        boolean zChangedInstance3;
        Object objRememberedValue8;
        Function0<ComposeUiNode> constructor;
        int i10;
        final PopupPositionProvider popupPositionProvider2 = popupPositionProvider;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1772091631);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(popupPositionProvider2) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i11 = i2 & 2;
        if (i11 == 0) {
            if ((i & 48) == 0) {
                function1 = function0;
                i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    popupProperties2 = popupProperties;
                    if (composerStartRestartGroup.changed(popupProperties2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i3 |= i10;
                }
                if ((i3 & 1171) != 1170) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i11 != 0) {
                        function4 = null;
                    } else {
                        function4 = function1;
                    }
                    if (i4 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1772091631, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:298)");
                    }
                    view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
                    density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    str = (String) composerStartRestartGroup.consume(LocalPopupTestTag);
                    layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                    compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
                    stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i3 >> 9) & 14);
                    Object[] objArr = new Object[0];
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = new Function0<UUID>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupId$1$1
                            public final UUID invoke() {
                                return UUID.randomUUID();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue, composerStartRestartGroup, 48);
                    zBooleanValue = ((Boolean) composerStartRestartGroup.consume(LocalIsInPopupLayout)).booleanValue();
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        str2 = str;
                        i6 = 32;
                        final PopupLayout popupLayout2 = new PopupLayout(function4, popupProperties4, str2, view, density, popupPositionProvider2, uuid, zBooleanValue, null, 256, null);
                        popupPositionProvider2 = popupPositionProvider2;
                        z2 = true;
                        popupLayout2.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-297523940, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            public final void invoke(Composer composer2, int i12) {
                                if (!composer2.shouldExecute((i12 & 3) != 2, i12 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-297523940, i12, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:321)");
                                }
                                ProvidedValue<Boolean> providedValueProvides = AndroidPopup_androidKt.getLocalIsInPopupLayout().provides(Boolean.TRUE);
                                final PopupLayout popupLayout3 = popupLayout2;
                                final State<Function2<Composer, Integer, Unit>> state = stateRememberUpdatedState;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1022273628, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    public final void invoke(Composer composer3, int i13) {
                                        if (!composer3.shouldExecute((i13 & 3) != 2, i13 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1022273628, i13, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:322)");
                                        }
                                        Modifier.Companion companion2 = Modifier.INSTANCE;
                                        Object objRememberedValue9 = composer3.rememberedValue();
                                        Composer.Companion companion3 = Composer.INSTANCE;
                                        if (objRememberedValue9 == companion3.getEmpty()) {
                                            objRememberedValue9 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1$1
                                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                                    invoke((SemanticsPropertyReceiver) obj);
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                    SemanticsPropertiesKt.popup(semanticsPropertyReceiver);
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue9);
                                        }
                                        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion2, false, (Function1) objRememberedValue9, 1, null);
                                        boolean zChangedInstance4 = composer3.changedInstance(popupLayout3);
                                        final PopupLayout popupLayout4 = popupLayout3;
                                        Object objRememberedValue10 = composer3.rememberedValue();
                                        if (zChangedInstance4 || objRememberedValue10 == companion3.getEmpty()) {
                                            objRememberedValue10 = new Function1<IntSize, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$2$1
                                                {
                                                    super(1);
                                                }

                                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                                    m6292invokeozmzZPI(((IntSize) obj).m6197unboximpl());
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                                public final void m6292invokeozmzZPI(long j) {
                                                    popupLayout4.m6295setPopupContentSizefhxjrPA(IntSize.m6185boximpl(j));
                                                    popupLayout4.updatePosition();
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue10);
                                        }
                                        Modifier modifierAlpha = AlphaKt.alpha(OnRemeasuredModifierKt.onSizeChanged(modifierSemantics$default, (Function1) objRememberedValue10), popupLayout3.getCanCalculatePosition() ? 1.0f : 0.0f);
                                        Function2 function2Popup$lambda$0 = AndroidPopup_androidKt.Popup$lambda$0(state);
                                        Object objRememberedValue11 = composer3.rememberedValue();
                                        if (objRememberedValue11 == companion3.getEmpty()) {
                                            objRememberedValue11 = AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
                                            composer3.updateRememberedValue(objRememberedValue11);
                                        }
                                        MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue11;
                                        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierAlpha);
                                        ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor2 = companion4.getConstructor();
                                        if (composer3.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor2);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion4.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion4.getSetResolvedCompositionLocals());
                                        Updater.m2392initimpl(composerM2388constructorimpl, Integer.valueOf(iHashCode), companion4.getSetCompositeKeyHash());
                                        Updater.m2394reconcileimpl(composerM2388constructorimpl, companion4.getApplyOnDeactivatedNodeAssertion());
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion4.getSetModifier());
                                        function2Popup$lambda$0.invoke(composer3, 0);
                                        composer3.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer2, 54), composer2, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }));
                        composerStartRestartGroup.updateRememberedValue(popupLayout2);
                        objRememberedValue2 = popupLayout2;
                    } else {
                        z2 = true;
                        str2 = str;
                        i6 = 32;
                    }
                    popupLayout = (PopupLayout) objRememberedValue2;
                    boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(popupLayout);
                    int i12 = i3;
                    i7 = i12 & 112;
                    if (i7 == i6) {
                        z3 = z2;
                    } else {
                        z3 = false;
                    }
                    boolean z9 = zChangedInstance4 | z3;
                    i8 = i12 & 896;
                    if (i8 == 256) {
                        z4 = z2;
                    } else {
                        z4 = false;
                    }
                    zChanged = z9 | z4 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection.ordinal());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                        final String str3 = str2;
                        objRememberedValue3 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                                popupLayout.show();
                                popupLayout.updateParameters(function4, popupProperties4, str3, layoutDirection);
                                final PopupLayout popupLayout3 = popupLayout;
                                return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                                    @Override // androidx.compose.runtime.DisposableEffectResult
                                    public void dispose() {
                                        popupLayout3.disposeComposition();
                                        popupLayout3.dismiss();
                                    }
                                };
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    EffectsKt.DisposableEffect(popupLayout, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, 0);
                    boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(popupLayout);
                    if (i7 == i6) {
                        z5 = z2;
                    } else {
                        z5 = false;
                    }
                    boolean z10 = zChangedInstance5 | z5;
                    if (i8 == 256) {
                        z6 = z2;
                    } else {
                        z6 = false;
                    }
                    zChanged2 = z10 | z6 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection.ordinal());
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2 || objRememberedValue4 == companion.getEmpty()) {
                        final String str4 = str2;
                        objRememberedValue4 = new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                            public final void m6290invoke() {
                                popupLayout.updateParameters(function4, popupProperties4, str4, layoutDirection);
                            }

                            public /* bridge */ /* synthetic */ Object invoke() {
                                m6290invoke();
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    EffectsKt.SideEffect((Function0) objRememberedValue4, composerStartRestartGroup, 0);
                    boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(popupLayout);
                    i9 = i12 & 14;
                    if (i9 == 4) {
                        z7 = z2;
                    } else {
                        z7 = false;
                    }
                    z8 = zChangedInstance6 | z7;
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (z8 || objRememberedValue5 == companion.getEmpty()) {
                        objRememberedValue5 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                                popupLayout.setPositionProvider(popupPositionProvider2);
                                popupLayout.updatePosition();
                                return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                                    @Override // androidx.compose.runtime.DisposableEffectResult
                                    public void dispose() {
                                    }
                                };
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    EffectsKt.DisposableEffect(popupPositionProvider2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue5, composerStartRestartGroup, i9);
                    zChangedInstance = composerStartRestartGroup.changedInstance(popupLayout);
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance || objRememberedValue6 == companion.getEmpty()) {
                        objRememberedValue6 = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    EffectsKt.LaunchedEffect(popupLayout, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue6, composerStartRestartGroup, 0);
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    zChangedInstance2 = composerStartRestartGroup.changedInstance(popupLayout);
                    objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance2 || objRememberedValue7 == companion.getEmpty()) {
                        objRememberedValue7 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                            {
                                super(1);
                            }

                            public final void invoke(LayoutCoordinates layoutCoordinates) {
                                LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                                parentLayoutCoordinates.getClass();
                                popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((LayoutCoordinates) obj);
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                    }
                    Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(companion2, (Function1) objRememberedValue7);
                    zChangedInstance3 = composerStartRestartGroup.changedInstance(popupLayout) | composerStartRestartGroup.changed(layoutDirection.ordinal());
                    objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance3 || objRememberedValue8 == companion.getEmpty()) {
                        objRememberedValue8 = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                            @Override // androidx.compose.ui.layout.MeasurePolicy
                            /* JADX INFO: renamed from: measure-3p2s80s */
                            public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                                popupLayout.setParentLayoutDirection(layoutDirection);
                                return MeasureScope.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((Placeable.PlacementScope) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Placeable.PlacementScope placementScope) {
                                    }
                                }, 4, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                    }
                    MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue8;
                    int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned);
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
                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion3.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                    Updater.m2392initimpl(composerM2388constructorimpl, Integer.valueOf(iHashCode), companion3.getSetCompositeKeyHash());
                    Updater.m2394reconcileimpl(composerM2388constructorimpl, companion3.getApplyOnDeactivatedNodeAssertion());
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = function4;
                    popupProperties3 = popupProperties4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function3 = function1;
                    popupProperties3 = popupProperties2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.9
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i13) {
                            AndroidPopup_androidKt.Popup(popupPositionProvider2, function3, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            popupProperties2 = popupProperties;
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i10 = 2048;
                } else {
                    i10 = 1024;
                }
                i3 |= i10;
            }
            if ((i3 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i11 != 0) {
                    function4 = null;
                } else {
                    function4 = function1;
                }
                if (i4 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1772091631, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:298)");
                }
                view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
                density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                str = (String) composerStartRestartGroup.consume(LocalPopupTestTag);
                layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
                stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i3 >> 9) & 14);
                Object[] objArr2 = new Object[0];
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = new Function0<UUID>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupId$1$1
                        public final UUID invoke() {
                            return UUID.randomUUID();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr2, (Function0) objRememberedValue, composerStartRestartGroup, 48);
                zBooleanValue = ((Boolean) composerStartRestartGroup.consume(LocalIsInPopupLayout)).booleanValue();
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    str2 = str;
                    i6 = 32;
                    final PopupLayout popupLayout3 = new PopupLayout(function4, popupProperties4, str2, view, density, popupPositionProvider2, uuid, zBooleanValue, null, 256, null);
                    popupPositionProvider2 = popupPositionProvider2;
                    z2 = true;
                    popupLayout3.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-297523940, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i13) {
                            if (!composer2.shouldExecute((i13 & 3) != 2, i13 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-297523940, i13, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:321)");
                            }
                            ProvidedValue<Boolean> providedValueProvides = AndroidPopup_androidKt.getLocalIsInPopupLayout().provides(Boolean.TRUE);
                            final PopupLayout popupLayout4 = popupLayout3;
                            final State<? extends Function2<? super Composer, ? super Integer, Unit>> state = stateRememberUpdatedState;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1022273628, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1022273628, i14, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:322)");
                                    }
                                    Modifier.Companion companion4 = Modifier.INSTANCE;
                                    Object objRememberedValue9 = composer3.rememberedValue();
                                    Composer.Companion companion5 = Composer.INSTANCE;
                                    if (objRememberedValue9 == companion5.getEmpty()) {
                                        objRememberedValue9 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1$1
                                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                                invoke((SemanticsPropertyReceiver) obj);
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                SemanticsPropertiesKt.popup(semanticsPropertyReceiver);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue9);
                                    }
                                    Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion4, false, (Function1) objRememberedValue9, 1, null);
                                    boolean zChangedInstance7 = composer3.changedInstance(popupLayout4);
                                    final PopupLayout popupLayout5 = popupLayout4;
                                    Object objRememberedValue10 = composer3.rememberedValue();
                                    if (zChangedInstance7 || objRememberedValue10 == companion5.getEmpty()) {
                                        objRememberedValue10 = new Function1<IntSize, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$2$1
                                            {
                                                super(1);
                                            }

                                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                                m6292invokeozmzZPI(((IntSize) obj).m6197unboximpl());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                            public final void m6292invokeozmzZPI(long j) {
                                                popupLayout5.m6295setPopupContentSizefhxjrPA(IntSize.m6185boximpl(j));
                                                popupLayout5.updatePosition();
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue10);
                                    }
                                    Modifier modifierAlpha = AlphaKt.alpha(OnRemeasuredModifierKt.onSizeChanged(modifierSemantics$default, (Function1) objRememberedValue10), popupLayout4.getCanCalculatePosition() ? 1.0f : 0.0f);
                                    Function2 function2Popup$lambda$0 = AndroidPopup_androidKt.Popup$lambda$0(state);
                                    Object objRememberedValue11 = composer3.rememberedValue();
                                    if (objRememberedValue11 == companion5.getEmpty()) {
                                        objRememberedValue11 = AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
                                        composer3.updateRememberedValue(objRememberedValue11);
                                    }
                                    MeasurePolicy measurePolicy2 = (MeasurePolicy) objRememberedValue11;
                                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierAlpha);
                                    ComposeUiNode.Companion companion6 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor2 = companion6.getConstructor();
                                    if (composer3.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor2);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composer3);
                                    Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicy2, companion6.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion6.getSetResolvedCompositionLocals());
                                    Updater.m2392initimpl(composerM2388constructorimpl2, Integer.valueOf(iHashCode2), companion6.getSetCompositeKeyHash());
                                    Updater.m2394reconcileimpl(composerM2388constructorimpl2, companion6.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion6.getSetModifier());
                                    function2Popup$lambda$0.invoke(composer3, 0);
                                    composer3.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer2, 54), composer2, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }));
                    composerStartRestartGroup.updateRememberedValue(popupLayout3);
                    objRememberedValue2 = popupLayout3;
                } else {
                    z2 = true;
                    str2 = str;
                    i6 = 32;
                }
                popupLayout = (PopupLayout) objRememberedValue2;
                boolean zChangedInstance7 = composerStartRestartGroup.changedInstance(popupLayout);
                int i13 = i3;
                i7 = i13 & 112;
                if (i7 == i6) {
                    z3 = z2;
                } else {
                    z3 = false;
                }
                boolean z11 = zChangedInstance7 | z3;
                i8 = i13 & 896;
                if (i8 == 256) {
                    z4 = z2;
                } else {
                    z4 = false;
                }
                zChanged = z11 | z4 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection.ordinal());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    final String str5 = str2;
                    objRememberedValue3 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.show();
                            popupLayout.updateParameters(function4, popupProperties4, str5, layoutDirection);
                            final PopupLayout popupLayout4 = popupLayout;
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    popupLayout4.disposeComposition();
                                    popupLayout4.dismiss();
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    final String str6 = str2;
                    objRememberedValue3 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.show();
                            popupLayout.updateParameters(function4, popupProperties4, str6, layoutDirection);
                            final PopupLayout popupLayout4 = popupLayout;
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    popupLayout4.disposeComposition();
                                    popupLayout4.dismiss();
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                EffectsKt.DisposableEffect(popupLayout, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, 0);
                boolean zChangedInstance8 = composerStartRestartGroup.changedInstance(popupLayout);
                if (i7 == i6) {
                    z5 = z2;
                } else {
                    z5 = false;
                }
                boolean z12 = zChangedInstance8 | z5;
                if (i8 == 256) {
                    z6 = z2;
                } else {
                    z6 = false;
                }
                zChanged2 = z12 | z6 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection.ordinal());
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    final String str7 = str2;
                    objRememberedValue4 = new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                        public final void m6290invoke() {
                            popupLayout.updateParameters(function4, popupProperties4, str7, layoutDirection);
                        }

                        public /* bridge */ /* synthetic */ Object invoke() {
                            m6290invoke();
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    final String str8 = str2;
                    objRememberedValue4 = new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                        public final void m6290invoke() {
                            popupLayout.updateParameters(function4, popupProperties4, str8, layoutDirection);
                        }

                        public /* bridge */ /* synthetic */ Object invoke() {
                            m6290invoke();
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                EffectsKt.SideEffect((Function0) objRememberedValue4, composerStartRestartGroup, 0);
                boolean zChangedInstance9 = composerStartRestartGroup.changedInstance(popupLayout);
                i9 = i13 & 14;
                if (i9 == 4) {
                    z7 = z2;
                } else {
                    z7 = false;
                }
                z8 = zChangedInstance9 | z7;
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    objRememberedValue5 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.setPositionProvider(popupPositionProvider2);
                            popupLayout.updatePosition();
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.setPositionProvider(popupPositionProvider2);
                            popupLayout.updatePosition();
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                EffectsKt.DisposableEffect(popupPositionProvider2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue5, composerStartRestartGroup, i9);
                zChangedInstance = composerStartRestartGroup.changedInstance(popupLayout);
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    objRememberedValue6 = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                } else {
                    objRememberedValue6 = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                EffectsKt.LaunchedEffect(popupLayout, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue6, composerStartRestartGroup, 0);
                Modifier.Companion companion4 = Modifier.INSTANCE;
                zChangedInstance2 = composerStartRestartGroup.changedInstance(popupLayout);
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance2) {
                    objRememberedValue7 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                        {
                            super(1);
                        }

                        public final void invoke(LayoutCoordinates layoutCoordinates) {
                            LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                            parentLayoutCoordinates.getClass();
                            popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((LayoutCoordinates) obj);
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                } else {
                    objRememberedValue7 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                        {
                            super(1);
                        }

                        public final void invoke(LayoutCoordinates layoutCoordinates) {
                            LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                            parentLayoutCoordinates.getClass();
                            popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((LayoutCoordinates) obj);
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                Modifier modifierOnGloballyPositioned2 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion4, (Function1) objRememberedValue7);
                zChangedInstance3 = composerStartRestartGroup.changedInstance(popupLayout) | composerStartRestartGroup.changed(layoutDirection.ordinal());
                objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance3) {
                    objRememberedValue8 = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        /* JADX INFO: renamed from: measure-3p2s80s */
                        public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                            popupLayout.setParentLayoutDirection(layoutDirection);
                            return MeasureScope.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                    invoke((Placeable.PlacementScope) obj);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Placeable.PlacementScope placementScope) {
                                }
                            }, 4, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                } else {
                    objRememberedValue8 = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        /* JADX INFO: renamed from: measure-3p2s80s */
                        public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                            popupLayout.setParentLayoutDirection(layoutDirection);
                            return MeasureScope.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                    invoke((Placeable.PlacementScope) obj);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Placeable.PlacementScope placementScope) {
                                }
                            }, 4, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                }
                MeasurePolicy measurePolicy2 = (MeasurePolicy) objRememberedValue8;
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned2);
                ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
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
                Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicy2, companion5.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion5.getSetResolvedCompositionLocals());
                Updater.m2392initimpl(composerM2388constructorimpl2, Integer.valueOf(iHashCode2), companion5.getSetCompositeKeyHash());
                Updater.m2394reconcileimpl(composerM2388constructorimpl2, companion5.getApplyOnDeactivatedNodeAssertion());
                Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion5.getSetModifier());
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function4;
                popupProperties3 = popupProperties4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function3 = function1;
                popupProperties3 = popupProperties2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.9
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i14) {
                        AndroidPopup_androidKt.Popup(popupPositionProvider2, function3, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= 48;
        function1 = function0;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                popupProperties2 = popupProperties;
                if (composerStartRestartGroup.changed(popupProperties2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i10 = 2048;
                } else {
                    i10 = 1024;
                }
                i3 |= i10;
            }
            if ((i3 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i11 != 0) {
                    function4 = null;
                } else {
                    function4 = function1;
                }
                if (i4 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1772091631, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:298)");
                }
                view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
                density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                str = (String) composerStartRestartGroup.consume(LocalPopupTestTag);
                layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
                stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i3 >> 9) & 14);
                Object[] objArr3 = new Object[0];
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = new Function0<UUID>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupId$1$1
                        public final UUID invoke() {
                            return UUID.randomUUID();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr3, (Function0) objRememberedValue, composerStartRestartGroup, 48);
                zBooleanValue = ((Boolean) composerStartRestartGroup.consume(LocalIsInPopupLayout)).booleanValue();
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    str2 = str;
                    i6 = 32;
                    final PopupLayout popupLayout4 = new PopupLayout(function4, popupProperties4, str2, view, density, popupPositionProvider2, uuid, zBooleanValue, null, 256, null);
                    popupPositionProvider2 = popupPositionProvider2;
                    z2 = true;
                    popupLayout4.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-297523940, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i14) {
                            if (!composer2.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-297523940, i14, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:321)");
                            }
                            ProvidedValue<Boolean> providedValueProvides = AndroidPopup_androidKt.getLocalIsInPopupLayout().provides(Boolean.TRUE);
                            final PopupLayout popupLayout5 = popupLayout4;
                            final State<? extends Function2<? super Composer, ? super Integer, Unit>> state = stateRememberUpdatedState;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1022273628, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                public final void invoke(Composer composer3, int i15) {
                                    if (!composer3.shouldExecute((i15 & 3) != 2, i15 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1022273628, i15, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:322)");
                                    }
                                    Modifier.Companion companion6 = Modifier.INSTANCE;
                                    Object objRememberedValue9 = composer3.rememberedValue();
                                    Composer.Companion companion7 = Composer.INSTANCE;
                                    if (objRememberedValue9 == companion7.getEmpty()) {
                                        objRememberedValue9 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1$1
                                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                                invoke((SemanticsPropertyReceiver) obj);
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                SemanticsPropertiesKt.popup(semanticsPropertyReceiver);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue9);
                                    }
                                    Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion6, false, (Function1) objRememberedValue9, 1, null);
                                    boolean zChangedInstance10 = composer3.changedInstance(popupLayout5);
                                    final PopupLayout popupLayout6 = popupLayout5;
                                    Object objRememberedValue10 = composer3.rememberedValue();
                                    if (zChangedInstance10 || objRememberedValue10 == companion7.getEmpty()) {
                                        objRememberedValue10 = new Function1<IntSize, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$2$1
                                            {
                                                super(1);
                                            }

                                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                                m6292invokeozmzZPI(((IntSize) obj).m6197unboximpl());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                            public final void m6292invokeozmzZPI(long j) {
                                                popupLayout6.m6295setPopupContentSizefhxjrPA(IntSize.m6185boximpl(j));
                                                popupLayout6.updatePosition();
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue10);
                                    }
                                    Modifier modifierAlpha = AlphaKt.alpha(OnRemeasuredModifierKt.onSizeChanged(modifierSemantics$default, (Function1) objRememberedValue10), popupLayout5.getCanCalculatePosition() ? 1.0f : 0.0f);
                                    Function2 function2Popup$lambda$0 = AndroidPopup_androidKt.Popup$lambda$0(state);
                                    Object objRememberedValue11 = composer3.rememberedValue();
                                    if (objRememberedValue11 == companion7.getEmpty()) {
                                        objRememberedValue11 = AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
                                        composer3.updateRememberedValue(objRememberedValue11);
                                    }
                                    MeasurePolicy measurePolicy3 = (MeasurePolicy) objRememberedValue11;
                                    int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                                    CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer3, modifierAlpha);
                                    ComposeUiNode.Companion companion8 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor2 = companion8.getConstructor();
                                    if (composer3.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor2);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composer3);
                                    Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicy3, companion8.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion8.getSetResolvedCompositionLocals());
                                    Updater.m2392initimpl(composerM2388constructorimpl3, Integer.valueOf(iHashCode3), companion8.getSetCompositeKeyHash());
                                    Updater.m2394reconcileimpl(composerM2388constructorimpl3, companion8.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion8.getSetModifier());
                                    function2Popup$lambda$0.invoke(composer3, 0);
                                    composer3.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer2, 54), composer2, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }));
                    composerStartRestartGroup.updateRememberedValue(popupLayout4);
                    objRememberedValue2 = popupLayout4;
                } else {
                    z2 = true;
                    str2 = str;
                    i6 = 32;
                }
                popupLayout = (PopupLayout) objRememberedValue2;
                boolean zChangedInstance10 = composerStartRestartGroup.changedInstance(popupLayout);
                int i14 = i3;
                i7 = i14 & 112;
                if (i7 == i6) {
                    z3 = z2;
                } else {
                    z3 = false;
                }
                boolean z13 = zChangedInstance10 | z3;
                i8 = i14 & 896;
                if (i8 == 256) {
                    z4 = z2;
                } else {
                    z4 = false;
                }
                zChanged = z13 | z4 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection.ordinal());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    final String str9 = str2;
                    objRememberedValue3 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.show();
                            popupLayout.updateParameters(function4, popupProperties4, str9, layoutDirection);
                            final PopupLayout popupLayout5 = popupLayout;
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    popupLayout5.disposeComposition();
                                    popupLayout5.dismiss();
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    final String str10 = str2;
                    objRememberedValue3 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.show();
                            popupLayout.updateParameters(function4, popupProperties4, str10, layoutDirection);
                            final PopupLayout popupLayout5 = popupLayout;
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    popupLayout5.disposeComposition();
                                    popupLayout5.dismiss();
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                EffectsKt.DisposableEffect(popupLayout, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, 0);
                boolean zChangedInstance11 = composerStartRestartGroup.changedInstance(popupLayout);
                if (i7 == i6) {
                    z5 = z2;
                } else {
                    z5 = false;
                }
                boolean z14 = zChangedInstance11 | z5;
                if (i8 == 256) {
                    z6 = z2;
                } else {
                    z6 = false;
                }
                zChanged2 = z14 | z6 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection.ordinal());
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    final String str11 = str2;
                    objRememberedValue4 = new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                        public final void m6290invoke() {
                            popupLayout.updateParameters(function4, popupProperties4, str11, layoutDirection);
                        }

                        public /* bridge */ /* synthetic */ Object invoke() {
                            m6290invoke();
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    final String str12 = str2;
                    objRememberedValue4 = new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                        public final void m6290invoke() {
                            popupLayout.updateParameters(function4, popupProperties4, str12, layoutDirection);
                        }

                        public /* bridge */ /* synthetic */ Object invoke() {
                            m6290invoke();
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                EffectsKt.SideEffect((Function0) objRememberedValue4, composerStartRestartGroup, 0);
                boolean zChangedInstance12 = composerStartRestartGroup.changedInstance(popupLayout);
                i9 = i14 & 14;
                if (i9 == 4) {
                    z7 = z2;
                } else {
                    z7 = false;
                }
                z8 = zChangedInstance12 | z7;
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    objRememberedValue5 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.setPositionProvider(popupPositionProvider2);
                            popupLayout.updatePosition();
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.setPositionProvider(popupPositionProvider2);
                            popupLayout.updatePosition();
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                EffectsKt.DisposableEffect(popupPositionProvider2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue5, composerStartRestartGroup, i9);
                zChangedInstance = composerStartRestartGroup.changedInstance(popupLayout);
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    objRememberedValue6 = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                } else {
                    objRememberedValue6 = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                EffectsKt.LaunchedEffect(popupLayout, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue6, composerStartRestartGroup, 0);
                Modifier.Companion companion6 = Modifier.INSTANCE;
                zChangedInstance2 = composerStartRestartGroup.changedInstance(popupLayout);
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance2) {
                    objRememberedValue7 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                        {
                            super(1);
                        }

                        public final void invoke(LayoutCoordinates layoutCoordinates) {
                            LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                            parentLayoutCoordinates.getClass();
                            popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((LayoutCoordinates) obj);
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                } else {
                    objRememberedValue7 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                        {
                            super(1);
                        }

                        public final void invoke(LayoutCoordinates layoutCoordinates) {
                            LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                            parentLayoutCoordinates.getClass();
                            popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((LayoutCoordinates) obj);
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                Modifier modifierOnGloballyPositioned3 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion6, (Function1) objRememberedValue7);
                zChangedInstance3 = composerStartRestartGroup.changedInstance(popupLayout) | composerStartRestartGroup.changed(layoutDirection.ordinal());
                objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance3) {
                    objRememberedValue8 = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        /* JADX INFO: renamed from: measure-3p2s80s */
                        public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                            popupLayout.setParentLayoutDirection(layoutDirection);
                            return MeasureScope.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                    invoke((Placeable.PlacementScope) obj);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Placeable.PlacementScope placementScope) {
                                }
                            }, 4, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                } else {
                    objRememberedValue8 = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        /* JADX INFO: renamed from: measure-3p2s80s */
                        public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                            popupLayout.setParentLayoutDirection(layoutDirection);
                            return MeasureScope.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                    invoke((Placeable.PlacementScope) obj);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Placeable.PlacementScope placementScope) {
                                }
                            }, 4, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                }
                MeasurePolicy measurePolicy3 = (MeasurePolicy) objRememberedValue8;
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned3);
                ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
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
                Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicy3, companion7.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion7.getSetResolvedCompositionLocals());
                Updater.m2392initimpl(composerM2388constructorimpl3, Integer.valueOf(iHashCode3), companion7.getSetCompositeKeyHash());
                Updater.m2394reconcileimpl(composerM2388constructorimpl3, companion7.getApplyOnDeactivatedNodeAssertion());
                Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion7.getSetModifier());
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function4;
                popupProperties3 = popupProperties4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function3 = function1;
                popupProperties3 = popupProperties2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.9
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i15) {
                        AndroidPopup_androidKt.Popup(popupPositionProvider2, function3, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        popupProperties2 = popupProperties;
        if ((i & 3072) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i10 = 2048;
            } else {
                i10 = 1024;
            }
            i3 |= i10;
        }
        if ((i3 & 1171) != 1170) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i11 != 0) {
                function4 = null;
            } else {
                function4 = function1;
            }
            if (i4 != 0) {
                popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
            } else {
                popupProperties4 = popupProperties2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1772091631, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:298)");
            }
            view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
            density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            str = (String) composerStartRestartGroup.consume(LocalPopupTestTag);
            layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i3 >> 9) & 14);
            Object[] objArr4 = new Object[0];
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function0<UUID>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupId$1$1
                    public final UUID invoke() {
                        return UUID.randomUUID();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr4, (Function0) objRememberedValue, composerStartRestartGroup, 48);
            zBooleanValue = ((Boolean) composerStartRestartGroup.consume(LocalIsInPopupLayout)).booleanValue();
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                str2 = str;
                i6 = 32;
                final PopupLayout popupLayout5 = new PopupLayout(function4, popupProperties4, str2, view, density, popupPositionProvider2, uuid, zBooleanValue, null, 256, null);
                popupPositionProvider2 = popupPositionProvider2;
                z2 = true;
                popupLayout5.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-297523940, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i15) {
                        if (!composer2.shouldExecute((i15 & 3) != 2, i15 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-297523940, i15, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:321)");
                        }
                        ProvidedValue<Boolean> providedValueProvides = AndroidPopup_androidKt.getLocalIsInPopupLayout().provides(Boolean.TRUE);
                        final PopupLayout popupLayout6 = popupLayout5;
                        final State<? extends Function2<? super Composer, ? super Integer, Unit>> state = stateRememberUpdatedState;
                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1022273628, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1022273628, i16, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:322)");
                                }
                                Modifier.Companion companion8 = Modifier.INSTANCE;
                                Object objRememberedValue9 = composer3.rememberedValue();
                                Composer.Companion companion9 = Composer.INSTANCE;
                                if (objRememberedValue9 == companion9.getEmpty()) {
                                    objRememberedValue9 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1$1
                                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                            invoke((SemanticsPropertyReceiver) obj);
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            SemanticsPropertiesKt.popup(semanticsPropertyReceiver);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue9);
                                }
                                Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion8, false, (Function1) objRememberedValue9, 1, null);
                                boolean zChangedInstance13 = composer3.changedInstance(popupLayout6);
                                final PopupLayout popupLayout7 = popupLayout6;
                                Object objRememberedValue10 = composer3.rememberedValue();
                                if (zChangedInstance13 || objRememberedValue10 == companion9.getEmpty()) {
                                    objRememberedValue10 = new Function1<IntSize, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$2$1
                                        {
                                            super(1);
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                            m6292invokeozmzZPI(((IntSize) obj).m6197unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                        public final void m6292invokeozmzZPI(long j) {
                                            popupLayout7.m6295setPopupContentSizefhxjrPA(IntSize.m6185boximpl(j));
                                            popupLayout7.updatePosition();
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue10);
                                }
                                Modifier modifierAlpha = AlphaKt.alpha(OnRemeasuredModifierKt.onSizeChanged(modifierSemantics$default, (Function1) objRememberedValue10), popupLayout6.getCanCalculatePosition() ? 1.0f : 0.0f);
                                Function2 function2Popup$lambda$0 = AndroidPopup_androidKt.Popup$lambda$0(state);
                                Object objRememberedValue11 = composer3.rememberedValue();
                                if (objRememberedValue11 == companion9.getEmpty()) {
                                    objRememberedValue11 = AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
                                    composer3.updateRememberedValue(objRememberedValue11);
                                }
                                MeasurePolicy measurePolicy4 = (MeasurePolicy) objRememberedValue11;
                                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                                CompositionLocalMap currentCompositionLocalMap4 = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer3, modifierAlpha);
                                ComposeUiNode.Companion companion10 = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor2 = companion10.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor2);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl4 = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl4, measurePolicy4, companion10.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl4, currentCompositionLocalMap4, companion10.getSetResolvedCompositionLocals());
                                Updater.m2392initimpl(composerM2388constructorimpl4, Integer.valueOf(iHashCode4), companion10.getSetCompositeKeyHash());
                                Updater.m2394reconcileimpl(composerM2388constructorimpl4, companion10.getApplyOnDeactivatedNodeAssertion());
                                Updater.m2396setimpl(composerM2388constructorimpl4, modifierMaterializeModifier4, companion10.getSetModifier());
                                function2Popup$lambda$0.invoke(composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer2, 54), composer2, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }));
                composerStartRestartGroup.updateRememberedValue(popupLayout5);
                objRememberedValue2 = popupLayout5;
            } else {
                z2 = true;
                str2 = str;
                i6 = 32;
            }
            popupLayout = (PopupLayout) objRememberedValue2;
            boolean zChangedInstance13 = composerStartRestartGroup.changedInstance(popupLayout);
            int i15 = i3;
            i7 = i15 & 112;
            if (i7 == i6) {
                z3 = z2;
            } else {
                z3 = false;
            }
            boolean z15 = zChangedInstance13 | z3;
            i8 = i15 & 896;
            if (i8 == 256) {
                z4 = z2;
            } else {
                z4 = false;
            }
            zChanged = z15 | z4 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection.ordinal());
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                final String str13 = str2;
                objRememberedValue3 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        popupLayout.show();
                        popupLayout.updateParameters(function4, popupProperties4, str13, layoutDirection);
                        final PopupLayout popupLayout6 = popupLayout;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                popupLayout6.disposeComposition();
                                popupLayout6.dismiss();
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                final String str14 = str2;
                objRememberedValue3 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        popupLayout.show();
                        popupLayout.updateParameters(function4, popupProperties4, str14, layoutDirection);
                        final PopupLayout popupLayout6 = popupLayout;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                popupLayout6.disposeComposition();
                                popupLayout6.dismiss();
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.DisposableEffect(popupLayout, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, 0);
            boolean zChangedInstance14 = composerStartRestartGroup.changedInstance(popupLayout);
            if (i7 == i6) {
                z5 = z2;
            } else {
                z5 = false;
            }
            boolean z16 = zChangedInstance14 | z5;
            if (i8 == 256) {
                z6 = z2;
            } else {
                z6 = false;
            }
            zChanged2 = z16 | z6 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection.ordinal());
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged2) {
                final String str15 = str2;
                objRememberedValue4 = new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m6290invoke() {
                        popupLayout.updateParameters(function4, popupProperties4, str15, layoutDirection);
                    }

                    public /* bridge */ /* synthetic */ Object invoke() {
                        m6290invoke();
                        return Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                final String str16 = str2;
                objRememberedValue4 = new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m6290invoke() {
                        popupLayout.updateParameters(function4, popupProperties4, str16, layoutDirection);
                    }

                    public /* bridge */ /* synthetic */ Object invoke() {
                        m6290invoke();
                        return Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            EffectsKt.SideEffect((Function0) objRememberedValue4, composerStartRestartGroup, 0);
            boolean zChangedInstance15 = composerStartRestartGroup.changedInstance(popupLayout);
            i9 = i15 & 14;
            if (i9 == 4) {
                z7 = z2;
            } else {
                z7 = false;
            }
            z8 = zChangedInstance15 | z7;
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (z8) {
                objRememberedValue5 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        popupLayout.setPositionProvider(popupPositionProvider2);
                        popupLayout.updatePosition();
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            } else {
                objRememberedValue5 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        popupLayout.setPositionProvider(popupPositionProvider2);
                        popupLayout.updatePosition();
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            EffectsKt.DisposableEffect(popupPositionProvider2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue5, composerStartRestartGroup, i9);
            zChangedInstance = composerStartRestartGroup.changedInstance(popupLayout);
            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                objRememberedValue6 = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            } else {
                objRememberedValue6 = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            EffectsKt.LaunchedEffect(popupLayout, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue6, composerStartRestartGroup, 0);
            Modifier.Companion companion8 = Modifier.INSTANCE;
            zChangedInstance2 = composerStartRestartGroup.changedInstance(popupLayout);
            objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2) {
                objRememberedValue7 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                    {
                        super(1);
                    }

                    public final void invoke(LayoutCoordinates layoutCoordinates) {
                        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                        parentLayoutCoordinates.getClass();
                        popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((LayoutCoordinates) obj);
                        return Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            } else {
                objRememberedValue7 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                    {
                        super(1);
                    }

                    public final void invoke(LayoutCoordinates layoutCoordinates) {
                        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                        parentLayoutCoordinates.getClass();
                        popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((LayoutCoordinates) obj);
                        return Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            Modifier modifierOnGloballyPositioned4 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion8, (Function1) objRememberedValue7);
            zChangedInstance3 = composerStartRestartGroup.changedInstance(popupLayout) | composerStartRestartGroup.changed(layoutDirection.ordinal());
            objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3) {
                objRememberedValue8 = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        popupLayout.setParentLayoutDirection(layoutDirection);
                        return MeasureScope.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((Placeable.PlacementScope) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Placeable.PlacementScope placementScope) {
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            } else {
                objRememberedValue8 = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        popupLayout.setParentLayoutDirection(layoutDirection);
                        return MeasureScope.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((Placeable.PlacementScope) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Placeable.PlacementScope placementScope) {
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            MeasurePolicy measurePolicy4 = (MeasurePolicy) objRememberedValue8;
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned4);
            ComposeUiNode.Companion companion9 = ComposeUiNode.INSTANCE;
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
            Composer composerM2388constructorimpl4 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl4, measurePolicy4, companion9.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl4, currentCompositionLocalMap4, companion9.getSetResolvedCompositionLocals());
            Updater.m2392initimpl(composerM2388constructorimpl4, Integer.valueOf(iHashCode4), companion9.getSetCompositeKeyHash());
            Updater.m2394reconcileimpl(composerM2388constructorimpl4, companion9.getApplyOnDeactivatedNodeAssertion());
            Updater.m2396setimpl(composerM2388constructorimpl4, modifierMaterializeModifier4, companion9.getSetModifier());
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function3 = function4;
            popupProperties3 = popupProperties4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            function3 = function1;
            popupProperties3 = popupProperties2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.9
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                public final void invoke(Composer composer2, int i16) {
                    AndroidPopup_androidKt.Popup(popupPositionProvider2, function3, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Function2<Composer, Integer, Unit> Popup$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        return state.getValue();
    }

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
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:51:0x0089  */
    /* JADX WARN: Code duplicated, block: B:53:0x008e  */
    /* JADX WARN: Code duplicated, block: B:56:0x009a  */
    /* JADX WARN: Code duplicated, block: B:57:0x009d  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:61:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:68:0x00be  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:72:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00de  */
    /* JADX WARN: Code duplicated, block: B:78:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:79:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:82:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:85:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:87:0x0106  */
    /* JADX WARN: Code duplicated, block: B:90:0x0120  */
    /* JADX WARN: Code duplicated, block: B:92:0x0126  */
    /* JADX WARN: Code duplicated, block: B:95:0x0133  */
    /* JADX WARN: Code duplicated, block: B:97:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: Popup-K5zGePQ, reason: not valid java name */
    public static final void m6287PopupK5zGePQ(Alignment alignment, long j, Function0<Unit> function0, PopupProperties popupProperties, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        Alignment alignment2;
        int i3;
        long j2;
        int i4;
        Function0<Unit> function1;
        int i5;
        int i6;
        PopupProperties popupProperties2;
        int i7;
        Function2<? super Composer, ? super Integer, Unit> function3;
        boolean z;
        final Alignment topStart;
        final long jM6144constructorimpl;
        final Function0<Unit> function4;
        final PopupProperties popupProperties3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        DefaultConstructorMarker defaultConstructorMarker;
        Function0<Unit> function5;
        PopupProperties popupProperties4;
        boolean z2;
        boolean z3;
        Object objRememberedValue;
        int i8;
        Composer composerStartRestartGroup = composer.startRestartGroup(71005054);
        int i9 = i2 & 1;
        if (i9 != 0) {
            i3 = i | 6;
            alignment2 = alignment;
        } else if ((i & 6) == 0) {
            alignment2 = alignment;
            i3 = (composerStartRestartGroup.changed(alignment2) ? 4 : 2) | i;
        } else {
            alignment2 = alignment;
            i3 = i;
        }
        int i10 = i2 & 2;
        if (i10 == 0) {
            if ((i & 48) == 0) {
                j2 = j;
                i3 |= composerStartRestartGroup.changed(j2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    function1 = function0;
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        popupProperties2 = popupProperties;
                        if (composerStartRestartGroup.changed(popupProperties2)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i & 24576) == 0) {
                        function3 = function2;
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    } else {
                        function3 = function2;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i9 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i10 != 0) {
                            jM6144constructorimpl = IntOffset.m6144constructorimpl(0L);
                        } else {
                            jM6144constructorimpl = j2;
                        }
                        defaultConstructorMarker = null;
                        if (i4 != 0) {
                            function5 = null;
                        } else {
                            function5 = function1;
                        }
                        if (i6 != 0) {
                            popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                        } else {
                            popupProperties4 = popupProperties2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:269)");
                        }
                        if ((i3 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = z2 | ((i3 & 112) == 32);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function4 = function5;
                        popupProperties3 = popupProperties4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        topStart = alignment2;
                        jM6144constructorimpl = j2;
                        function4 = function1;
                        popupProperties3 = popupProperties2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            public final void invoke(Composer composer2, int i11) {
                                AndroidPopup_androidKt.m6287PopupK5zGePQ(topStart, jM6144constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }
                i3 |= 3072;
                popupProperties2 = popupProperties;
                if ((i & 24576) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                } else {
                    function3 = function2;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i9 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i10 != 0) {
                        jM6144constructorimpl = IntOffset.m6144constructorimpl(0L);
                    } else {
                        jM6144constructorimpl = j2;
                    }
                    defaultConstructorMarker = null;
                    if (i4 != 0) {
                        function5 = null;
                    } else {
                        function5 = function1;
                    }
                    if (i6 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:269)");
                    }
                    if ((i3 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = z2 | ((i3 & 112) == 32);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function4 = function5;
                    popupProperties3 = popupProperties4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    topStart = alignment2;
                    jM6144constructorimpl = j2;
                    function4 = function1;
                    popupProperties3 = popupProperties2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            AndroidPopup_androidKt.m6287PopupK5zGePQ(topStart, jM6144constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            function1 = function0;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    popupProperties2 = popupProperties;
                    if (composerStartRestartGroup.changed(popupProperties2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                } else {
                    function3 = function2;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i9 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i10 != 0) {
                        jM6144constructorimpl = IntOffset.m6144constructorimpl(0L);
                    } else {
                        jM6144constructorimpl = j2;
                    }
                    defaultConstructorMarker = null;
                    if (i4 != 0) {
                        function5 = null;
                    } else {
                        function5 = function1;
                    }
                    if (i6 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:269)");
                    }
                    if ((i3 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = z2 | ((i3 & 112) == 32);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function4 = function5;
                    popupProperties3 = popupProperties4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    topStart = alignment2;
                    jM6144constructorimpl = j2;
                    function4 = function1;
                    popupProperties3 = popupProperties2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            AndroidPopup_androidKt.m6287PopupK5zGePQ(topStart, jM6144constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= 3072;
            popupProperties2 = popupProperties;
            if ((i & 24576) == 0) {
                function3 = function2;
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            } else {
                function3 = function2;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i9 != 0) {
                    topStart = Alignment.INSTANCE.getTopStart();
                } else {
                    topStart = alignment2;
                }
                if (i10 != 0) {
                    jM6144constructorimpl = IntOffset.m6144constructorimpl(0L);
                } else {
                    jM6144constructorimpl = j2;
                }
                defaultConstructorMarker = null;
                if (i4 != 0) {
                    function5 = null;
                } else {
                    function5 = function1;
                }
                if (i6 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:269)");
                }
                if ((i3 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2 | ((i3 & 112) == 32);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function4 = function5;
                popupProperties3 = popupProperties4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                topStart = alignment2;
                jM6144constructorimpl = j2;
                function4 = function1;
                popupProperties3 = popupProperties2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i11) {
                        AndroidPopup_androidKt.m6287PopupK5zGePQ(topStart, jM6144constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= 48;
        j2 = j;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                function1 = function0;
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    popupProperties2 = popupProperties;
                    if (composerStartRestartGroup.changed(popupProperties2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                } else {
                    function3 = function2;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i9 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i10 != 0) {
                        jM6144constructorimpl = IntOffset.m6144constructorimpl(0L);
                    } else {
                        jM6144constructorimpl = j2;
                    }
                    defaultConstructorMarker = null;
                    if (i4 != 0) {
                        function5 = null;
                    } else {
                        function5 = function1;
                    }
                    if (i6 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:269)");
                    }
                    if ((i3 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = z2 | ((i3 & 112) == 32);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function4 = function5;
                    popupProperties3 = popupProperties4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    topStart = alignment2;
                    jM6144constructorimpl = j2;
                    function4 = function1;
                    popupProperties3 = popupProperties2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            AndroidPopup_androidKt.m6287PopupK5zGePQ(topStart, jM6144constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= 3072;
            popupProperties2 = popupProperties;
            if ((i & 24576) == 0) {
                function3 = function2;
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            } else {
                function3 = function2;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i9 != 0) {
                    topStart = Alignment.INSTANCE.getTopStart();
                } else {
                    topStart = alignment2;
                }
                if (i10 != 0) {
                    jM6144constructorimpl = IntOffset.m6144constructorimpl(0L);
                } else {
                    jM6144constructorimpl = j2;
                }
                defaultConstructorMarker = null;
                if (i4 != 0) {
                    function5 = null;
                } else {
                    function5 = function1;
                }
                if (i6 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:269)");
                }
                if ((i3 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2 | ((i3 & 112) == 32);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function4 = function5;
                popupProperties3 = popupProperties4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                topStart = alignment2;
                jM6144constructorimpl = j2;
                function4 = function1;
                popupProperties3 = popupProperties2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i11) {
                        AndroidPopup_androidKt.m6287PopupK5zGePQ(topStart, jM6144constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        function1 = function0;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                popupProperties2 = popupProperties;
                if (composerStartRestartGroup.changed(popupProperties2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i & 24576) == 0) {
                function3 = function2;
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            } else {
                function3 = function2;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i9 != 0) {
                    topStart = Alignment.INSTANCE.getTopStart();
                } else {
                    topStart = alignment2;
                }
                if (i10 != 0) {
                    jM6144constructorimpl = IntOffset.m6144constructorimpl(0L);
                } else {
                    jM6144constructorimpl = j2;
                }
                defaultConstructorMarker = null;
                if (i4 != 0) {
                    function5 = null;
                } else {
                    function5 = function1;
                }
                if (i6 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:269)");
                }
                if ((i3 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2 | ((i3 & 112) == 32);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function4 = function5;
                popupProperties3 = popupProperties4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                topStart = alignment2;
                jM6144constructorimpl = j2;
                function4 = function1;
                popupProperties3 = popupProperties2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i11) {
                        AndroidPopup_androidKt.m6287PopupK5zGePQ(topStart, jM6144constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= 3072;
        popupProperties2 = popupProperties;
        if ((i & 24576) == 0) {
            function3 = function2;
            if (composerStartRestartGroup.changedInstance(function3)) {
                i8 = 16384;
            } else {
                i8 = 8192;
            }
            i3 |= i8;
        } else {
            function3 = function2;
        }
        if ((i3 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i9 != 0) {
                topStart = Alignment.INSTANCE.getTopStart();
            } else {
                topStart = alignment2;
            }
            if (i10 != 0) {
                jM6144constructorimpl = IntOffset.m6144constructorimpl(0L);
            } else {
                jM6144constructorimpl = j2;
            }
            defaultConstructorMarker = null;
            if (i4 != 0) {
                function5 = null;
            } else {
                function5 = function1;
            }
            if (i6 != 0) {
                popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
            } else {
                popupProperties4 = popupProperties2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:269)");
            }
            if ((i3 & 14) == 4) {
                z2 = true;
            } else {
                z2 = false;
            }
            z3 = z2 | ((i3 & 112) == 32);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z3) {
                objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM6144constructorimpl, defaultConstructorMarker);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function4 = function5;
            popupProperties3 = popupProperties4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            topStart = alignment2;
            jM6144constructorimpl = j2;
            function4 = function1;
            popupProperties3 = popupProperties2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                public final void invoke(Composer composer2, int i11) {
                    AndroidPopup_androidKt.m6287PopupK5zGePQ(topStart, jM6144constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }

    public static final void PopupTestTag(final String str, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1357513789);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1357513789, i2, -1, "androidx.compose.ui.window.PopupTestTag (AndroidPopup.android.kt:440)");
            }
            CompositionLocalKt.CompositionLocalProvider(LocalPopupTestTag.provides(str), function2, composerStartRestartGroup, (i2 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.PopupTestTag.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                public final void invoke(Composer composer2, int i3) {
                    AndroidPopup_androidKt.PopupTestTag(str, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }

    private static final void SimpleStack(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, int i) {
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
            composer.updateRememberedValue(objRememberedValue);
        }
        MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
        int i2 = ((i << 3) & 112) | ((i >> 3) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        Function0<ComposeUiNode> constructor = companion.getConstructor();
        int i3 = ((i2 << 6) & 896) | 6;
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
        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion.getSetMeasurePolicy());
        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
        Updater.m2392initimpl(composerM2388constructorimpl, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
        Updater.m2394reconcileimpl(composerM2388constructorimpl, companion.getApplyOnDeactivatedNodeAssertion());
        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
        function2.invoke(composer, Integer.valueOf((i3 >> 6) & 14));
        composer.endNode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int createFlags(boolean z, SecureFlagPolicy secureFlagPolicy, boolean z2) {
        int i = !z ? 262152 : 262144;
        if (secureFlagPolicy == SecureFlagPolicy.SecureOn) {
            i |= 8192;
        }
        return !z2 ? i | 512 : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int flagsWithSecureFlagInherited(PopupProperties popupProperties, boolean z) {
        if (popupProperties.getInheritSecurePolicy() && z) {
            return popupProperties.getFlags() | 8192;
        }
        return (!popupProperties.getInheritSecurePolicy() || z) ? popupProperties.getFlags() : popupProperties.getFlags() & (-8193);
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalIsInPopupLayout() {
        return LocalIsInPopupLayout;
    }

    public static final ProvidableCompositionLocal<String> getLocalPopupTestTag() {
        return LocalPopupTestTag;
    }

    public static final boolean isFlagSecureEnabled(View view) {
        ViewGroup.LayoutParams layoutParams = view.getRootView().getLayoutParams();
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (layoutParams2 == null || (layoutParams2.flags & 8192) == 0) ? false : true;
    }

    public static final boolean isPopupLayout(View view, String str) {
        if (view instanceof PopupLayout) {
            return str == null || Intrinsics.areEqual(str, ((PopupLayout) view).getTestTag());
        }
        return false;
    }

    public static /* synthetic */ boolean isPopupLayout$default(View view, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return isPopupLayout(view, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRect toIntBounds(Rect rect) {
        return new IntRect(rect.left, rect.top, rect.right, rect.bottom);
    }
}
