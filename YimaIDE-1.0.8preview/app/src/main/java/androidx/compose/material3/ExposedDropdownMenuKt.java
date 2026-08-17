package androidx.compose.material3;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.material3.ExposedDropdownMenuKt;
import androidx.compose.material3.internal.BackHandler_androidKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSizeKt;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aQ\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\f\u001a\u001b\u0010\r\u001a\u00020\u0003*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001aa\u0010\u001b\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002¢\u0006\u0004\b&\u0010'\u001a\"\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u0001032\u0006\u00104\u001a\u00020/H\u0002\u001a\u000e\u00105\u001a\u000203*\u0004\u0018\u000106H\u0002\"\u0018\u0010(\u001a\u00020\u0003*\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+\"\u0018\u0010,\u001a\u00020\u0003*\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b-\u0010+\"\u0010\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0004\n\u0002\u00109*8\b\u0007\u0010\u0012\"\u00020\u000e2\u00020\u000eB*\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u001c\b\u0016\u0012\u0018\b\u000bB\u0014\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0006\b\u001a\u0012\u0002\b\f¨\u0006:²\u0006\f\u0010;\u001a\u0004\u0018\u000106X\u008a\u008e\u0002²\u0006\n\u0010<\u001a\u00020/X\u008a\u008e\u0002²\u0006\n\u0010=\u001a\u00020/X\u008a\u008e\u0002"}, d2 = {"ExposedDropdownMenuBox", "", "expanded", "", "onExpandedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Landroidx/compose/material3/ExposedDropdownMenuBoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "hasGreaterOrEqualPriorityThan", "Landroidx/compose/material3/ExposedDropdownMenuAnchorType;", "that", "hasGreaterOrEqualPriorityThan-vVDBVkM", "(Ljava/lang/String;Ljava/lang/String;)Z", "MenuAnchorType", "Lkotlin/Deprecated;", "message", "Renamed to ExposedDropdownMenuAnchorType", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "ExposedDropdownMenuAnchorType", "imports", "expandable", "Lkotlin/Function0;", "anchorType", "alwaysFocusable", "Landroidx/compose/runtime/MutableState;", "expandedDescription", "", "collapsedDescription", "toggleDescription", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "expandable-3-2CpT8", "(Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/MutableState;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/platform/SoftwareKeyboardController;)Landroidx/compose/ui/Modifier;", "isClick", "Landroidx/compose/ui/input/key/KeyEvent;", "isClick-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "isEnterMinusSpacebar", "isEnterMinusSpacebar-ZmokQxo", "calculateMaxHeight", "", "windowBounds", "Landroidx/compose/ui/unit/IntRect;", "anchorBounds", "Landroidx/compose/ui/geometry/Rect;", "verticalMargin", "getAnchorBounds", "Landroidx/compose/ui/layout/LayoutCoordinates;", "ExposedDropdownMenuItemHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "material3", "anchorCoordinates", "anchorWidth", "menuMaxHeight"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ExposedDropdownMenuKt {
    private static final float ExposedDropdownMenuItemHorizontalPadding = Dp.m6022constructorimpl(16.0f);

    /* JADX WARN: Code duplicated, block: B:100:0x022d  */
    /* JADX WARN: Code duplicated, block: B:103:0x0239  */
    /* JADX WARN: Code duplicated, block: B:104:0x023d  */
    /* JADX WARN: Code duplicated, block: B:109:0x026c  */
    /* JADX WARN: Code duplicated, block: B:112:0x0295  */
    /* JADX WARN: Code duplicated, block: B:116:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:119:0x02c5  */
    /* JADX WARN: Code duplicated, block: B:121:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:122:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:127:0x02e1  */
    /* JADX WARN: Code duplicated, block: B:130:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:131:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:136:0x0305  */
    /* JADX WARN: Code duplicated, block: B:139:0x0319  */
    /* JADX WARN: Code duplicated, block: B:141:0x031e  */
    /* JADX WARN: Code duplicated, block: B:144:0x0329  */
    /* JADX WARN: Code duplicated, block: B:146:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x005f  */
    /* JADX WARN: Code duplicated, block: B:37:0x0062  */
    /* JADX WARN: Code duplicated, block: B:39:0x0066  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:46:0x007b  */
    /* JADX WARN: Code duplicated, block: B:47:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0087 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:51:0x0089  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:55:0x0093  */
    /* JADX WARN: Code duplicated, block: B:58:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:61:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:67:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:70:0x0139  */
    /* JADX WARN: Code duplicated, block: B:71:0x014d  */
    /* JADX WARN: Code duplicated, block: B:74:0x015b  */
    /* JADX WARN: Code duplicated, block: B:75:0x0169  */
    /* JADX WARN: Code duplicated, block: B:78:0x0172  */
    /* JADX WARN: Code duplicated, block: B:79:0x0175  */
    /* JADX WARN: Code duplicated, block: B:82:0x017e  */
    /* JADX WARN: Code duplicated, block: B:83:0x0181  */
    /* JADX WARN: Code duplicated, block: B:88:0x019c  */
    /* JADX WARN: Code duplicated, block: B:97:0x01f0  */
    public static final void ExposedDropdownMenuBox(boolean z, final Function1<? super Boolean, Unit> function1, Modifier modifier, Function3<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z2;
        final Function3<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        WindowBoundsCalculator windowBoundsCalculatorPlatformWindowBoundsCalculator;
        final int iMo4551roundToPx0680j_4;
        Object objRememberedValue;
        Composer.Companion companion;
        final MutableState mutableState;
        Object objRememberedValue2;
        MutableIntState mutableIntState;
        Object objRememberedValue3;
        MutableIntState mutableIntState2;
        Object objRememberedValue4;
        final FocusRequester focusRequester;
        SoftwareKeyboardController softwareKeyboardController;
        String strM1471getString2EP1pXo;
        String strM1471getString2EP1pXo2;
        String strM1471getString2EP1pXo3;
        Object objRememberedValue5;
        MutableState mutableState2;
        Object objRememberedValue6;
        MutableState mutableState3;
        int i5;
        boolean z3;
        int i6;
        boolean z4;
        boolean zChanged;
        Object exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1;
        MutableIntState mutableIntState3;
        final MutableIntState mutableIntState4;
        final WindowBoundsCalculator windowBoundsCalculator;
        boolean zChangedInstance;
        Object objRememberedValue7;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM2388constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        boolean z5;
        Object objRememberedValue8;
        boolean z6;
        Object objRememberedValue9;
        boolean zChangedInstance2;
        Object objRememberedValue10;
        final boolean z7 = z;
        Composer composerStartRestartGroup = composer.startRestartGroup(1597265892);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z7) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i7 = i2 & 4;
        if (i7 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            if ((i2 & 8) != 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i4 = 2048;
                } else {
                    i4 = 1024;
                }
                i3 |= i4;
            }
            if ((i3 & 1171) != 1170) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                if (i7 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1597265892, i3, -1, "androidx.compose.material3.ExposedDropdownMenuBox (ExposedDropdownMenu.kt:141)");
                }
                windowBoundsCalculatorPlatformWindowBoundsCalculator = ExposedDropdownMenu_androidKt.platformWindowBoundsCalculator(composerStartRestartGroup, 0);
                Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                iMo4551roundToPx0680j_4 = density.mo4551roundToPx0680j_4(MenuKt.getMenuVerticalMargin());
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = SnapshotIntStateKt.mutableIntStateOf(0);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableIntState = (MutableIntState) objRememberedValue2;
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == companion.getEmpty()) {
                    objRememberedValue3 = SnapshotIntStateKt.mutableIntStateOf(0);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                mutableIntState2 = (MutableIntState) objRememberedValue3;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == companion.getEmpty()) {
                    objRememberedValue4 = new FocusRequester();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                focusRequester = (FocusRequester) objRememberedValue4;
                softwareKeyboardController = (SoftwareKeyboardController) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalSoftwareKeyboardController());
                Strings.Companion companion2 = Strings.INSTANCE;
                strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_dropdown_menu_expanded), composerStartRestartGroup, 0);
                strM1471getString2EP1pXo2 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_dropdown_menu_collapsed), composerStartRestartGroup, 0);
                strM1471getString2EP1pXo3 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_dropdown_menu_toggle), composerStartRestartGroup, 0);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue5 == companion.getEmpty()) {
                    objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ExposedDropdownMenuAnchorType.m433boximpl(ExposedDropdownMenuAnchorType.INSTANCE.m441getPrimaryNotEditableoYjWRB4()), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                mutableState2 = (MutableState) objRememberedValue5;
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue6 == companion.getEmpty()) {
                    objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                mutableState3 = (MutableState) objRememberedValue6;
                i5 = i3 & 14;
                if (i5 == 4) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                i6 = i3 & 112;
                if (i6 == 32) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                zChanged = composerStartRestartGroup.changed(density) | z3 | z4 | composerStartRestartGroup.changed(windowBoundsCalculatorPlatformWindowBoundsCalculator);
                Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
                if (!zChanged || objRememberedValue11 == companion.getEmpty()) {
                    mutableIntState3 = mutableIntState;
                    mutableIntState4 = mutableIntState2;
                    windowBoundsCalculator = windowBoundsCalculatorPlatformWindowBoundsCalculator;
                    exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 = new ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1(focusRequester, z, mutableState3, strM1471getString2EP1pXo, strM1471getString2EP1pXo2, strM1471getString2EP1pXo3, softwareKeyboardController, mutableState2, function1, mutableIntState3, mutableIntState4);
                    focusRequester = focusRequester;
                    z7 = z;
                    composerStartRestartGroup.updateRememberedValue(exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1);
                } else {
                    z7 = z;
                    mutableIntState3 = mutableIntState;
                    windowBoundsCalculator = windowBoundsCalculatorPlatformWindowBoundsCalculator;
                    exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 = objRememberedValue11;
                    mutableIntState4 = mutableIntState2;
                }
                ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$2 = (ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1) exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1;
                zChangedInstance = composerStartRestartGroup.changedInstance(windowBoundsCalculator) | composerStartRestartGroup.changed(iMo4551roundToPx0680j_4);
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue7 == companion.getEmpty()) {
                    final MutableIntState mutableIntState5 = mutableIntState3;
                    final MutableIntState mutableIntState6 = mutableIntState4;
                    final WindowBoundsCalculator windowBoundsCalculator2 = windowBoundsCalculator;
                    objRememberedValue7 = new Function1() { // from class: qg4
                        public final Object invoke(Object obj) {
                            return ExposedDropdownMenuKt.a(windowBoundsCalculator2, iMo4551roundToPx0680j_4, mutableState, mutableIntState5, mutableIntState6, (LayoutCoordinates) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(modifier4, (Function1) objRememberedValue7);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
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
                function4 = function3;
                function4.invoke(exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$2, composerStartRestartGroup, Integer.valueOf((i3 >> 6) & 112));
                composerStartRestartGroup.endNode();
                if (z7) {
                    composerStartRestartGroup.startReplaceGroup(209894723);
                    zChangedInstance2 = composerStartRestartGroup.changedInstance(windowBoundsCalculator) | composerStartRestartGroup.changed(iMo4551roundToPx0680j_4);
                    objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance2 || objRememberedValue10 == companion.getEmpty()) {
                        objRememberedValue10 = new Function0() { // from class: rg4
                            public final Object invoke() {
                                return ExposedDropdownMenuKt.d(windowBoundsCalculator, iMo4551roundToPx0680j_4, mutableState, mutableIntState4);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
                    }
                    ExposedDropdownMenu_androidKt.OnPlatformWindowBoundsChange((Function0) objRememberedValue10, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(210228190);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (i5 == 4) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (z5 || objRememberedValue8 == companion.getEmpty()) {
                    objRememberedValue8 = new Function0() { // from class: sg4
                        public final Object invoke() {
                            return ExposedDropdownMenuKt.b(z7, focusRequester);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                }
                EffectsKt.SideEffect((Function0) objRememberedValue8, composerStartRestartGroup, 0);
                if (i6 == 32) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                if (z6 || objRememberedValue9 == companion.getEmpty()) {
                    objRememberedValue9 = new Function0() { // from class: tg4
                        public final Object invoke() {
                            return ExposedDropdownMenuKt.f(function1);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
                }
                BackHandler_androidKt.BackHandler(z7, (Function0) objRememberedValue9, composerStartRestartGroup, i5, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
            } else {
                function4 = function3;
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ug4
                    public final Object invoke(Object obj, Object obj2) {
                        return ExposedDropdownMenuKt.c(z7, function1, modifier3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i4 = 2048;
            } else {
                i4 = 1024;
            }
            i3 |= i4;
        }
        if ((i3 & 1171) != 1170) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
            if (i7 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1597265892, i3, -1, "androidx.compose.material3.ExposedDropdownMenuBox (ExposedDropdownMenu.kt:141)");
            }
            windowBoundsCalculatorPlatformWindowBoundsCalculator = ExposedDropdownMenu_androidKt.platformWindowBoundsCalculator(composerStartRestartGroup, 0);
            Density density2 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            iMo4551roundToPx0680j_4 = density2.mo4551roundToPx0680j_4(MenuKt.getMenuVerticalMargin());
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            mutableIntState = (MutableIntState) objRememberedValue2;
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            mutableIntState2 = (MutableIntState) objRememberedValue3;
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            focusRequester = (FocusRequester) objRememberedValue4;
            softwareKeyboardController = (SoftwareKeyboardController) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalSoftwareKeyboardController());
            Strings.Companion companion4 = Strings.INSTANCE;
            strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_dropdown_menu_expanded), composerStartRestartGroup, 0);
            strM1471getString2EP1pXo2 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_dropdown_menu_collapsed), composerStartRestartGroup, 0);
            strM1471getString2EP1pXo3 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_dropdown_menu_toggle), composerStartRestartGroup, 0);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ExposedDropdownMenuAnchorType.m433boximpl(ExposedDropdownMenuAnchorType.INSTANCE.m441getPrimaryNotEditableoYjWRB4()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            mutableState2 = (MutableState) objRememberedValue5;
            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == companion.getEmpty()) {
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            mutableState3 = (MutableState) objRememberedValue6;
            i5 = i3 & 14;
            if (i5 == 4) {
                z3 = true;
            } else {
                z3 = false;
            }
            i6 = i3 & 112;
            if (i6 == 32) {
                z4 = true;
            } else {
                z4 = false;
            }
            zChanged = composerStartRestartGroup.changed(density2) | z3 | z4 | composerStartRestartGroup.changed(windowBoundsCalculatorPlatformWindowBoundsCalculator);
            Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                mutableIntState3 = mutableIntState;
                mutableIntState4 = mutableIntState2;
                windowBoundsCalculator = windowBoundsCalculatorPlatformWindowBoundsCalculator;
                exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 = new ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1(focusRequester, z, mutableState3, strM1471getString2EP1pXo, strM1471getString2EP1pXo2, strM1471getString2EP1pXo3, softwareKeyboardController, mutableState2, function1, mutableIntState3, mutableIntState4);
                focusRequester = focusRequester;
                z7 = z;
                composerStartRestartGroup.updateRememberedValue(exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1);
            } else {
                mutableIntState3 = mutableIntState;
                mutableIntState4 = mutableIntState2;
                windowBoundsCalculator = windowBoundsCalculatorPlatformWindowBoundsCalculator;
                exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 = new ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1(focusRequester, z, mutableState3, strM1471getString2EP1pXo, strM1471getString2EP1pXo2, strM1471getString2EP1pXo3, softwareKeyboardController, mutableState2, function1, mutableIntState3, mutableIntState4);
                focusRequester = focusRequester;
                z7 = z;
                composerStartRestartGroup.updateRememberedValue(exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1);
            }
            ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$3 = (ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1) exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1;
            zChangedInstance = composerStartRestartGroup.changedInstance(windowBoundsCalculator) | composerStartRestartGroup.changed(iMo4551roundToPx0680j_4);
            objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                final MutableIntState mutableIntState7 = mutableIntState3;
                final MutableIntState mutableIntState8 = mutableIntState4;
                final WindowBoundsCalculator windowBoundsCalculator3 = windowBoundsCalculator;
                objRememberedValue7 = new Function1() { // from class: qg4
                    public final Object invoke(Object obj) {
                        return ExposedDropdownMenuKt.a(windowBoundsCalculator3, iMo4551roundToPx0680j_4, mutableState, mutableIntState7, mutableIntState8, (LayoutCoordinates) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            } else {
                final MutableIntState mutableIntState9 = mutableIntState3;
                final MutableIntState mutableIntState10 = mutableIntState4;
                final WindowBoundsCalculator windowBoundsCalculator4 = windowBoundsCalculator;
                objRememberedValue7 = new Function1() { // from class: qg4
                    public final Object invoke(Object obj) {
                        return ExposedDropdownMenuKt.a(windowBoundsCalculator4, iMo4551roundToPx0680j_4, mutableState, mutableIntState9, mutableIntState10, (LayoutCoordinates) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            Modifier modifierOnGloballyPositioned2 = OnGloballyPositionedModifierKt.onGloballyPositioned(modifier4, (Function1) objRememberedValue7);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
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
            function4 = function3;
            function4.invoke(exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$3, composerStartRestartGroup, Integer.valueOf((i3 >> 6) & 112));
            composerStartRestartGroup.endNode();
            if (z7) {
                composerStartRestartGroup.startReplaceGroup(209894723);
                zChangedInstance2 = composerStartRestartGroup.changedInstance(windowBoundsCalculator) | composerStartRestartGroup.changed(iMo4551roundToPx0680j_4);
                objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance2) {
                    objRememberedValue10 = new Function0() { // from class: rg4
                        public final Object invoke() {
                            return ExposedDropdownMenuKt.d(windowBoundsCalculator, iMo4551roundToPx0680j_4, mutableState, mutableIntState4);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
                } else {
                    objRememberedValue10 = new Function0() { // from class: rg4
                        public final Object invoke() {
                            return ExposedDropdownMenuKt.d(windowBoundsCalculator, iMo4551roundToPx0680j_4, mutableState, mutableIntState4);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
                }
                ExposedDropdownMenu_androidKt.OnPlatformWindowBoundsChange((Function0) objRememberedValue10, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(210228190);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (i5 == 4) {
                z5 = true;
            } else {
                z5 = false;
            }
            objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (z5) {
                objRememberedValue8 = new Function0() { // from class: sg4
                    public final Object invoke() {
                        return ExposedDropdownMenuKt.b(z7, focusRequester);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            } else {
                objRememberedValue8 = new Function0() { // from class: sg4
                    public final Object invoke() {
                        return ExposedDropdownMenuKt.b(z7, focusRequester);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            EffectsKt.SideEffect((Function0) objRememberedValue8, composerStartRestartGroup, 0);
            if (i6 == 32) {
                z6 = true;
            } else {
                z6 = false;
            }
            objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (z6) {
                objRememberedValue9 = new Function0() { // from class: tg4
                    public final Object invoke() {
                        return ExposedDropdownMenuKt.f(function1);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            } else {
                objRememberedValue9 = new Function0() { // from class: tg4
                    public final Object invoke() {
                        return ExposedDropdownMenuKt.f(function1);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            BackHandler_androidKt.BackHandler(z7, (Function0) objRememberedValue9, composerStartRestartGroup, i5, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        } else {
            function4 = function3;
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ug4
                public final Object invoke(Object obj, Object obj2) {
                    return ExposedDropdownMenuKt.c(z7, function1, modifier3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final LayoutCoordinates ExposedDropdownMenuBox$lambda$2(MutableState<LayoutCoordinates> mutableState) {
        return mutableState.getValue();
    }

    @Deprecated(message = "Renamed to ExposedDropdownMenuAnchorType", replaceWith = @ReplaceWith(expression = "ExposedDropdownMenuAnchorType", imports = {}))
    public static /* synthetic */ void MenuAnchorType$annotations() {
    }

    public static Unit a(WindowBoundsCalculator windowBoundsCalculator, int i, MutableState mutableState, MutableIntState mutableIntState, MutableIntState mutableIntState2, LayoutCoordinates layoutCoordinates) {
        mutableState.setValue(layoutCoordinates);
        mutableIntState.setIntValue((int) (layoutCoordinates.mo4613getSizeYbymL2g() >> 32));
        mutableIntState2.setIntValue(calculateMaxHeight(windowBoundsCalculator.getVisibleWindowBounds(), getAnchorBounds(ExposedDropdownMenuBox$lambda$2(mutableState)), i));
        return Unit.INSTANCE;
    }

    public static Unit b(boolean z, FocusRequester focusRequester) {
        if (z) {
            FocusRequester.m2792requestFocus3ESFkO8$default(focusRequester, 0, 1, null);
        }
        return Unit.INSTANCE;
    }

    public static Unit c(boolean z, Function1 function1, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        ExposedDropdownMenuBox(z, function1, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private static final int calculateMaxHeight(IntRect intRect, Rect rect, int i) {
        if (rect == null) {
            return 0;
        }
        int top = intRect.getTop() + i;
        int bottom = intRect.getBottom() - i;
        return Math.max((rect.getTop() > ((float) intRect.getBottom()) || rect.getBottom() < ((float) intRect.getTop())) ? bottom - top : MathKt.roundToInt(Math.max(rect.getTop() - top, bottom - rect.getBottom())), 0);
    }

    public static Unit d(WindowBoundsCalculator windowBoundsCalculator, int i, MutableState mutableState, MutableIntState mutableIntState) {
        mutableIntState.setIntValue(calculateMaxHeight(windowBoundsCalculator.getVisibleWindowBounds(), getAnchorBounds(ExposedDropdownMenuBox$lambda$2(mutableState)), i));
        return Unit.INSTANCE;
    }

    public static Unit e(final String str, boolean z, String str2, String str3, String str4, final Function0 function0, final SoftwareKeyboardController softwareKeyboardController, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (ExposedDropdownMenuAnchorType.m436equalsimpl0(str, ExposedDropdownMenuAnchorType.INSTANCE.m442getSecondaryEditableoYjWRB4())) {
            SemanticsPropertiesKt.m5264setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5245getButtono7Vup1c());
            if (!z) {
                str2 = str3;
            }
            SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, str2);
            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str4);
        } else {
            SemanticsPropertiesKt.m5264setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5248getDropdownListo7Vup1c());
        }
        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0() { // from class: pg4
            public final Object invoke() {
                return Boolean.valueOf(ExposedDropdownMenuKt.g(function0, str, softwareKeyboardController));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: expandable-3-2CpT8, reason: not valid java name */
    public static final Modifier m458expandable32CpT8(Modifier modifier, final boolean z, final Function0<Unit> function0, final String str, final MutableState<Boolean> mutableState, final String str2, final String str3, final String str4, final SoftwareKeyboardController softwareKeyboardController) {
        return SemanticsModifierKt.semantics$default(KeyInputModifierKt.onPreviewKeyEvent(SuspendingPointerInputFilterKt.pointerInput(modifier, function0, new PointerInputEventHandler() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$expandable$1

            /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenuKt$expandable$1$1, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
            @DebugMetadata(c = "androidx.compose.material3.ExposedDropdownMenuKt$expandable$1$1", f = "ExposedDropdownMenu.kt", i = {0}, l = {1426, 1430}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
            public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $anchorType;
                final /* synthetic */ Function0<Unit> $onExpandedChange;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(String str, Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$anchorType = str;
                    this.$onExpandedChange = function0;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$anchorType, this.$onExpandedChange, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
                
                    if (r11 == r0) goto L19;
                 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    AnonymousClass1 anonymousClass1;
                    AwaitPointerEventScope awaitPointerEventScope;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                        PointerEventPass pointerEventPass = PointerEventPass.Initial;
                        this.L$0 = awaitPointerEventScope2;
                        this.label = 1;
                        anonymousClass1 = this;
                        obj = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope2, false, pointerEventPass, anonymousClass1, 1, (Object) null);
                        if (obj != coroutine_suspended) {
                            awaitPointerEventScope = awaitPointerEventScope2;
                        }
                        return coroutine_suspended;
                    }
                    if (i == 1) {
                        awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        anonymousClass1 = this;
                    } else {
                        if (i != 2) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                        anonymousClass1 = this;
                    }
                    if (((PointerInputChange) obj) != null) {
                        anonymousClass1.$onExpandedChange.invoke();
                    }
                    return Unit.INSTANCE;
                    PointerInputChange pointerInputChange = (PointerInputChange) obj;
                    if (ExposedDropdownMenuAnchorType.m436equalsimpl0(anonymousClass1.$anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m442getSecondaryEditableoYjWRB4())) {
                        pointerInputChange.consume();
                    }
                    PointerEventPass pointerEventPass2 = PointerEventPass.Initial;
                    anonymousClass1.L$0 = null;
                    anonymousClass1.label = 2;
                    obj = TapGestureDetectorKt.waitForUpOrCancellation(awaitPointerEventScope, pointerEventPass2, anonymousClass1);
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass1(str, function0, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        }), new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$expandable$2
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m462invokeZmokQxo(((KeyEvent) obj).m4284unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m462invokeZmokQxo(android.view.KeyEvent keyEvent) {
                if (ExposedDropdownMenuKt.m460isClickZmokQxo(keyEvent)) {
                    if (!ExposedDropdownMenuAnchorType.m436equalsimpl0(str, ExposedDropdownMenuAnchorType.INSTANCE.m440getPrimaryEditableoYjWRB4())) {
                        function0.invoke();
                    } else if (ExposedDropdownMenuKt.m461isEnterMinusSpacebarZmokQxo(keyEvent)) {
                        function0.invoke();
                        return Boolean.TRUE;
                    }
                }
                if (ExposedDropdownMenuAnchorType.m436equalsimpl0(str, ExposedDropdownMenuAnchorType.INSTANCE.m440getPrimaryEditableoYjWRB4()) && z) {
                    long jM4295getKeyZmokQxo = KeyEvent_androidKt.m4295getKeyZmokQxo(keyEvent);
                    Key.Companion companion = Key.INSTANCE;
                    if (Key.m3987equalsimpl0(jM4295getKeyZmokQxo, companion.m4223getTabEK5gGoQ()) || Key.m3987equalsimpl0(KeyEvent_androidKt.m4295getKeyZmokQxo(keyEvent), companion.m4060getDirectionDownEK5gGoQ()) || Key.m3987equalsimpl0(KeyEvent_androidKt.m4295getKeyZmokQxo(keyEvent), companion.m4065getDirectionUpEK5gGoQ())) {
                        MutableState<Boolean> mutableState2 = mutableState;
                        Boolean bool = Boolean.TRUE;
                        mutableState2.setValue(bool);
                        return bool;
                    }
                }
                MutableState<Boolean> mutableState3 = mutableState;
                Boolean bool2 = Boolean.FALSE;
                mutableState3.setValue(bool2);
                return bool2;
            }
        }), false, new Function1() { // from class: vg4
            public final Object invoke(Object obj) {
                return ExposedDropdownMenuKt.e(str, z, str2, str3, str4, function0, softwareKeyboardController, (SemanticsPropertyReceiver) obj);
            }
        }, 1, null);
    }

    public static Unit f(Function1 function1) {
        function1.invoke(Boolean.FALSE);
        return Unit.INSTANCE;
    }

    public static boolean g(Function0 function0, String str, SoftwareKeyboardController softwareKeyboardController) {
        function0.invoke();
        if (!ExposedDropdownMenuAnchorType.m436equalsimpl0(str, ExposedDropdownMenuAnchorType.INSTANCE.m440getPrimaryEditableoYjWRB4()) || softwareKeyboardController == null) {
            return true;
        }
        softwareKeyboardController.show();
        return true;
    }

    private static final Rect getAnchorBounds(LayoutCoordinates layoutCoordinates) {
        return (layoutCoordinates == null || !layoutCoordinates.isAttached()) ? Rect.INSTANCE.getZero() : RectKt.m2929Recttz77jQw(LayoutCoordinatesKt.positionInWindow(layoutCoordinates), IntSizeKt.m6205toSizeozmzZPI(layoutCoordinates.mo4613getSizeYbymL2g()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hasGreaterOrEqualPriorityThan-vVDBVkM, reason: not valid java name */
    public static final boolean m459hasGreaterOrEqualPriorityThanvVDBVkM(String str, String str2) {
        ExposedDropdownMenuAnchorType.Companion companion = ExposedDropdownMenuAnchorType.INSTANCE;
        if (ExposedDropdownMenuAnchorType.m436equalsimpl0(str, companion.m441getPrimaryNotEditableoYjWRB4()) || ExposedDropdownMenuAnchorType.m436equalsimpl0(str, companion.m440getPrimaryEditableoYjWRB4())) {
            return true;
        }
        if (ExposedDropdownMenuAnchorType.m436equalsimpl0(str, companion.m442getSecondaryEditableoYjWRB4())) {
            return ExposedDropdownMenuAnchorType.m436equalsimpl0(str2, companion.m442getSecondaryEditableoYjWRB4());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isClick-ZmokQxo, reason: not valid java name */
    public static final boolean m460isClickZmokQxo(android.view.KeyEvent keyEvent) {
        if (KeyEventType.m4288equalsimpl0(KeyEvent_androidKt.m4296getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4293getKeyUpCS__XNY())) {
            return m461isEnterMinusSpacebarZmokQxo(keyEvent) || Key.m3987equalsimpl0(KeyEvent_androidKt.m4295getKeyZmokQxo(keyEvent), Key.INSTANCE.m4211getSpacebarEK5gGoQ());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isEnterMinusSpacebar-ZmokQxo, reason: not valid java name */
    public static final boolean m461isEnterMinusSpacebarZmokQxo(android.view.KeyEvent keyEvent) {
        long jM4295getKeyZmokQxo = KeyEvent_androidKt.m4295getKeyZmokQxo(keyEvent);
        Key.Companion companion = Key.INSTANCE;
        return Key.m3987equalsimpl0(jM4295getKeyZmokQxo, companion.m4059getDirectionCenterEK5gGoQ()) || Key.m3987equalsimpl0(jM4295getKeyZmokQxo, companion.m4073getEnterEK5gGoQ()) || Key.m3987equalsimpl0(jM4295getKeyZmokQxo, companion.m4165getNumPadEnterEK5gGoQ());
    }
}
