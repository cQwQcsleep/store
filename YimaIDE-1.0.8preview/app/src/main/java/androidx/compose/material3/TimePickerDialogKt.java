package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.TimePickerDialogKt;
import androidx.compose.material3.tokens.DialogTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a·\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u009f\u0001\u0010\u0017\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0014H\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001aQ\u0010\u001a\u001a\u00020\u00012\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0014H\u0001¢\u0006\u0002\u0010\u001c¨\u0006\u001d"}, d2 = {"TimePickerDialog", "", "onDismissRequest", "Lkotlin/Function0;", "confirmButton", "Landroidx/compose/runtime/Composable;", "title", "modifier", "Landroidx/compose/ui/Modifier;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "modeToggleButton", "dismissButton", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "TimePickerDialog-FItCLgY", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "TimePickerDialogLayout", "TimePickerDialogLayout-3csKH6Y", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "TimePickerCustomLayout", "actions", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TimePickerDialogKt {
    public static final void TimePickerCustomLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-493479138);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function4) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-493479138, i2, -1, "androidx.compose.material3.TimePickerCustomLayout (TimePickerDialog.kt:130)");
            }
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(468305759, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerCustomLayout$content$1
                public final void invoke(Composer composer2, int i3) {
                    if (!composer2.shouldExecute((i3 & 3) != 2, i3 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(468305759, i3, -1, "androidx.compose.material3.TimePickerCustomLayout.<anonymous> (TimePickerDialog.kt:133)");
                    }
                    Modifier.Companion companion = Modifier.INSTANCE;
                    Modifier modifierLayoutId = LayoutIdKt.layoutId(companion, "title");
                    Function2<Composer, Integer, Unit> function5 = function2;
                    Alignment.Companion companion2 = Alignment.INSTANCE;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion2.getTopStart(), false);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierLayoutId);
                    ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor = companion3.getConstructor();
                    if (composer2.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    function5.invoke(composer2, 0);
                    composer2.endNode();
                    Modifier modifierLayoutId2 = LayoutIdKt.layoutId(companion, "actions");
                    Function2<Composer, Integer, Unit> function6 = function3;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion2.getTopStart(), false);
                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, modifierLayoutId2);
                    Function0<ComposeUiNode> constructor2 = companion3.getConstructor();
                    if (composer2.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor2);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composer2);
                    Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, companion3.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
                    function6.invoke(composer2, 0);
                    composer2.endNode();
                    Modifier modifierLayoutId3 = LayoutIdKt.layoutId(companion, "timePickerContent");
                    Function3<ColumnScope, Composer, Integer, Unit> function7 = function4;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), companion2.getStart(), composer2, 0);
                    int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifierLayoutId3);
                    Function0<ComposeUiNode> constructor3 = companion3.getConstructor();
                    if (composer2.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor3);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composer2);
                    Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = companion3.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                        composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion3.getSetModifier());
                    function7.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                    composer2.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1.INSTANCE;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            Modifier.Companion companion = Modifier.INSTANCE;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion2.getConstructor();
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            composableLambdaRememberComposableLambda.invoke(composerStartRestartGroup, 6);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerDialogKt.c(function2, function3, function4, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0116  */
    /* JADX WARN: Code duplicated, block: B:102:0x011d  */
    /* JADX WARN: Code duplicated, block: B:104:0x0121  */
    /* JADX WARN: Code duplicated, block: B:106:0x0129  */
    /* JADX WARN: Code duplicated, block: B:107:0x012c  */
    /* JADX WARN: Code duplicated, block: B:109:0x0131  */
    /* JADX WARN: Code duplicated, block: B:112:0x0140  */
    /* JADX WARN: Code duplicated, block: B:113:0x0142  */
    /* JADX WARN: Code duplicated, block: B:116:0x014b  */
    /* JADX WARN: Code duplicated, block: B:118:0x0158  */
    /* JADX WARN: Code duplicated, block: B:128:0x017b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:129:0x017d  */
    /* JADX WARN: Code duplicated, block: B:131:0x0182  */
    /* JADX WARN: Code duplicated, block: B:132:0x0192  */
    /* JADX WARN: Code duplicated, block: B:135:0x0197  */
    /* JADX WARN: Code duplicated, block: B:137:0x019a  */
    /* JADX WARN: Code duplicated, block: B:140:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:141:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:144:0x01af  */
    /* JADX WARN: Code duplicated, block: B:146:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:149:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:152:0x0210  */
    /* JADX WARN: Code duplicated, block: B:154:0x021f  */
    /* JADX WARN: Code duplicated, block: B:157:0x0231  */
    /* JADX WARN: Code duplicated, block: B:159:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0065  */
    /* JADX WARN: Code duplicated, block: B:38:0x006a  */
    /* JADX WARN: Code duplicated, block: B:40:0x006e  */
    /* JADX WARN: Code duplicated, block: B:42:0x0076  */
    /* JADX WARN: Code duplicated, block: B:43:0x0079  */
    /* JADX WARN: Code duplicated, block: B:47:0x0080  */
    /* JADX WARN: Code duplicated, block: B:49:0x0085  */
    /* JADX WARN: Code duplicated, block: B:51:0x0089  */
    /* JADX WARN: Code duplicated, block: B:53:0x0091  */
    /* JADX WARN: Code duplicated, block: B:54:0x0094  */
    /* JADX WARN: Code duplicated, block: B:58:0x009d  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:65:0x00af  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:73:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:76:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:82:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:88:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:93:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:96:0x0103  */
    /* JADX WARN: Code duplicated, block: B:98:0x0108  */
    /* JADX INFO: renamed from: TimePickerDialog-FItCLgY, reason: not valid java name */
    public static final void m1126TimePickerDialogFItCLgY(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Modifier modifier, DialogProperties dialogProperties, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Shape shape, long j, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function6, Composer composer, final int i, final int i2) {
        int i3;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        int i4;
        Modifier modifier2;
        int i5;
        int i6;
        DialogProperties dialogProperties2;
        int i7;
        int i8;
        Function2<? super Composer, ? super Integer, Unit> function9;
        int i9;
        int i10;
        Function2<? super Composer, ? super Integer, Unit> function10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final DialogProperties dialogProperties3;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final Shape shape2;
        final long j2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        DialogProperties dialogProperties4;
        final Shape shape3;
        final long containerColor;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        int i16;
        final Modifier modifier4;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        int i17;
        Composer composerStartRestartGroup = composer.startRestartGroup(951250327);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
            function7 = function2;
        } else {
            function7 = function2;
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function7) ? 32 : 16;
            }
        }
        if ((i2 & 4) == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                function8 = function3;
                i3 |= composerStartRestartGroup.changedInstance(function8) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        dialogProperties2 = dialogProperties;
                        if (composerStartRestartGroup.changed(dialogProperties2)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 32;
                    if (i8 != 0) {
                        if ((196608 & i) == 0) {
                            function9 = function4;
                            if (composerStartRestartGroup.changedInstance(function9)) {
                                i9 = 131072;
                            } else {
                                i9 = 65536;
                            }
                            i3 |= i9;
                        }
                        i10 = i2 & 64;
                        if (i10 != 0) {
                            if ((1572864 & i) == 0) {
                                function10 = function5;
                                if (composerStartRestartGroup.changedInstance(function10)) {
                                    i11 = 1048576;
                                } else {
                                    i11 = 524288;
                                }
                                i3 |= i11;
                            }
                            if ((i & 12582912) != 0) {
                                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                            }
                            if ((i & 100663296) == 0) {
                                int i18 = i3;
                                if ((i2 & 256) == 0 || !composerStartRestartGroup.changed(j)) {
                                    i17 = 33554432;
                                } else {
                                    i17 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                                }
                                i12 = i18 | i17;
                            } else {
                                i12 = i3;
                            }
                            i13 = i12;
                            if ((i2 & 512) != 0) {
                                i14 = i13 | 805306368;
                            } else if ((i & 805306368) == 0) {
                                if (composerStartRestartGroup.changedInstance(function6)) {
                                    i15 = 536870912;
                                } else {
                                    i15 = 268435456;
                                }
                                i14 = i13 | i15;
                            } else {
                                i14 = i13;
                            }
                            if ((i14 & 306783379) != 306783378) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i4 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i6 != 0) {
                                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties4 = dialogProperties2;
                                    }
                                    if (i8 != 0) {
                                        function9 = null;
                                    }
                                    if (i10 != 0) {
                                        function10 = null;
                                    }
                                    if ((i2 & 128) != 0) {
                                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                        i14 &= -29360129;
                                    } else {
                                        shape3 = shape;
                                    }
                                    if ((i2 & 256) != 0) {
                                        i14 &= -234881025;
                                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    } else {
                                        containerColor = j;
                                    }
                                    function13 = function9;
                                    i16 = i14;
                                    dialogProperties2 = dialogProperties4;
                                    modifier4 = modifier2;
                                    function14 = function10;
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    if ((i2 & 128) != 0) {
                                        i14 &= -29360129;
                                    }
                                    if ((i2 & 256) != 0) {
                                        i14 &= -234881025;
                                    }
                                    shape3 = shape;
                                    containerColor = j;
                                    function13 = function9;
                                    function14 = function10;
                                    i16 = i14;
                                    modifier4 = modifier2;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                                }
                                final Function2<? super Composer, ? super Integer, Unit> function15 = function8;
                                AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                                    public final void invoke(Composer composer3, int i19) {
                                        if (!composer3.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(296331566, i19, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                        }
                                        TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function15, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                                composer2 = composerStartRestartGroup;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                dialogProperties3 = dialogProperties2;
                                modifier3 = modifier4;
                                function11 = function13;
                                function12 = function14;
                                shape2 = shape3;
                                j2 = containerColor;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                modifier3 = modifier2;
                                dialogProperties3 = dialogProperties2;
                                function11 = function9;
                                function12 = function10;
                                shape2 = shape;
                                j2 = j;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                                    public final Object invoke(Object obj, Object obj2) {
                                        return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i3 |= 1572864;
                        function10 = function5;
                        if ((i & 12582912) != 0) {
                            i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                        }
                        if ((i & 100663296) == 0) {
                            int i19 = i3;
                            if ((i2 & 256) == 0) {
                                i17 = 33554432;
                            } else {
                                i17 = 33554432;
                            }
                            i12 = i19 | i17;
                        } else {
                            i12 = i3;
                        }
                        i13 = i12;
                        if ((i2 & 512) != 0) {
                            i14 = i13 | 805306368;
                        } else if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i15 = 536870912;
                            } else {
                                i15 = 268435456;
                            }
                            i14 = i13 | i15;
                        } else {
                            i14 = i13;
                        }
                        if ((i14 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties4 = dialogProperties2;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if (i10 != 0) {
                                    function10 = null;
                                }
                                if ((i2 & 128) != 0) {
                                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i14 &= -29360129;
                                } else {
                                    shape3 = shape;
                                }
                                if ((i2 & 256) != 0) {
                                    i14 &= -234881025;
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                } else {
                                    containerColor = j;
                                }
                                function13 = function9;
                                i16 = i14;
                                dialogProperties2 = dialogProperties4;
                                modifier4 = modifier2;
                                function14 = function10;
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties4 = dialogProperties2;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if (i10 != 0) {
                                    function10 = null;
                                }
                                if ((i2 & 128) != 0) {
                                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i14 &= -29360129;
                                } else {
                                    shape3 = shape;
                                }
                                if ((i2 & 256) != 0) {
                                    i14 &= -234881025;
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                } else {
                                    containerColor = j;
                                }
                                function13 = function9;
                                i16 = i14;
                                dialogProperties2 = dialogProperties4;
                                modifier4 = modifier2;
                                function14 = function10;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function16 = function8;
                            AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                                public final void invoke(Composer composer3, int i110) {
                                    if (!composer3.shouldExecute((i110 & 3) != 2, i110 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(296331566, i110, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                    }
                                    TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function16, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            dialogProperties3 = dialogProperties2;
                            modifier3 = modifier4;
                            function11 = function13;
                            function12 = function14;
                            shape2 = shape3;
                            j2 = containerColor;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            dialogProperties3 = dialogProperties2;
                            function11 = function9;
                            function12 = function10;
                            shape2 = shape;
                            j2 = j;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function9 = function4;
                    i10 = i2 & 64;
                    if (i10 != 0) {
                        if ((1572864 & i) == 0) {
                            function10 = function5;
                            if (composerStartRestartGroup.changedInstance(function10)) {
                                i11 = 1048576;
                            } else {
                                i11 = 524288;
                            }
                            i3 |= i11;
                        }
                        if ((i & 12582912) != 0) {
                            i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                        }
                        if ((i & 100663296) == 0) {
                            int i110 = i3;
                            if ((i2 & 256) == 0) {
                                i17 = 33554432;
                            } else {
                                i17 = 33554432;
                            }
                            i12 = i110 | i17;
                        } else {
                            i12 = i3;
                        }
                        i13 = i12;
                        if ((i2 & 512) != 0) {
                            i14 = i13 | 805306368;
                        } else if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i15 = 536870912;
                            } else {
                                i15 = 268435456;
                            }
                            i14 = i13 | i15;
                        } else {
                            i14 = i13;
                        }
                        if ((i14 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties4 = dialogProperties2;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if (i10 != 0) {
                                    function10 = null;
                                }
                                if ((i2 & 128) != 0) {
                                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i14 &= -29360129;
                                } else {
                                    shape3 = shape;
                                }
                                if ((i2 & 256) != 0) {
                                    i14 &= -234881025;
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                } else {
                                    containerColor = j;
                                }
                                function13 = function9;
                                i16 = i14;
                                dialogProperties2 = dialogProperties4;
                                modifier4 = modifier2;
                                function14 = function10;
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties4 = dialogProperties2;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if (i10 != 0) {
                                    function10 = null;
                                }
                                if ((i2 & 128) != 0) {
                                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i14 &= -29360129;
                                } else {
                                    shape3 = shape;
                                }
                                if ((i2 & 256) != 0) {
                                    i14 &= -234881025;
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                } else {
                                    containerColor = j;
                                }
                                function13 = function9;
                                i16 = i14;
                                dialogProperties2 = dialogProperties4;
                                modifier4 = modifier2;
                                function14 = function10;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function17 = function8;
                            AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                                public final void invoke(Composer composer3, int i111) {
                                    if (!composer3.shouldExecute((i111 & 3) != 2, i111 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(296331566, i111, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                    }
                                    TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function17, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            dialogProperties3 = dialogProperties2;
                            modifier3 = modifier4;
                            function11 = function13;
                            function12 = function14;
                            shape2 = shape3;
                            j2 = containerColor;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            dialogProperties3 = dialogProperties2;
                            function11 = function9;
                            function12 = function10;
                            shape2 = shape;
                            j2 = j;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    function10 = function5;
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                    }
                    if ((i & 100663296) == 0) {
                        int i111 = i3;
                        if ((i2 & 256) == 0) {
                            i17 = 33554432;
                        } else {
                            i17 = 33554432;
                        }
                        i12 = i111 | i17;
                    } else {
                        i12 = i3;
                    }
                    i13 = i12;
                    if ((i2 & 512) != 0) {
                        i14 = i13 | 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i15 = 536870912;
                        } else {
                            i15 = 268435456;
                        }
                        i14 = i13 | i15;
                    } else {
                        i14 = i13;
                    }
                    if ((i14 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function18 = function8;
                        AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                            public final void invoke(Composer composer3, int i112) {
                                if (!composer3.shouldExecute((i112 & 3) != 2, i112 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(296331566, i112, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                }
                                TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function18, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        dialogProperties3 = dialogProperties2;
                        modifier3 = modifier4;
                        function11 = function13;
                        function12 = function14;
                        shape2 = shape3;
                        j2 = containerColor;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        dialogProperties3 = dialogProperties2;
                        function11 = function9;
                        function12 = function10;
                        shape2 = shape;
                        j2 = j;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                dialogProperties2 = dialogProperties;
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        function9 = function4;
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 64;
                    if (i10 != 0) {
                        if ((1572864 & i) == 0) {
                            function10 = function5;
                            if (composerStartRestartGroup.changedInstance(function10)) {
                                i11 = 1048576;
                            } else {
                                i11 = 524288;
                            }
                            i3 |= i11;
                        }
                        if ((i & 12582912) != 0) {
                            i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                        }
                        if ((i & 100663296) == 0) {
                            int i112 = i3;
                            if ((i2 & 256) == 0) {
                                i17 = 33554432;
                            } else {
                                i17 = 33554432;
                            }
                            i12 = i112 | i17;
                        } else {
                            i12 = i3;
                        }
                        i13 = i12;
                        if ((i2 & 512) != 0) {
                            i14 = i13 | 805306368;
                        } else if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i15 = 536870912;
                            } else {
                                i15 = 268435456;
                            }
                            i14 = i13 | i15;
                        } else {
                            i14 = i13;
                        }
                        if ((i14 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties4 = dialogProperties2;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if (i10 != 0) {
                                    function10 = null;
                                }
                                if ((i2 & 128) != 0) {
                                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i14 &= -29360129;
                                } else {
                                    shape3 = shape;
                                }
                                if ((i2 & 256) != 0) {
                                    i14 &= -234881025;
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                } else {
                                    containerColor = j;
                                }
                                function13 = function9;
                                i16 = i14;
                                dialogProperties2 = dialogProperties4;
                                modifier4 = modifier2;
                                function14 = function10;
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties4 = dialogProperties2;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if (i10 != 0) {
                                    function10 = null;
                                }
                                if ((i2 & 128) != 0) {
                                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i14 &= -29360129;
                                } else {
                                    shape3 = shape;
                                }
                                if ((i2 & 256) != 0) {
                                    i14 &= -234881025;
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                } else {
                                    containerColor = j;
                                }
                                function13 = function9;
                                i16 = i14;
                                dialogProperties2 = dialogProperties4;
                                modifier4 = modifier2;
                                function14 = function10;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function19 = function8;
                            AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                                public final void invoke(Composer composer3, int i113) {
                                    if (!composer3.shouldExecute((i113 & 3) != 2, i113 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(296331566, i113, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                    }
                                    TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function19, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            dialogProperties3 = dialogProperties2;
                            modifier3 = modifier4;
                            function11 = function13;
                            function12 = function14;
                            shape2 = shape3;
                            j2 = containerColor;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            dialogProperties3 = dialogProperties2;
                            function11 = function9;
                            function12 = function10;
                            shape2 = shape;
                            j2 = j;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    function10 = function5;
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                    }
                    if ((i & 100663296) == 0) {
                        int i113 = i3;
                        if ((i2 & 256) == 0) {
                            i17 = 33554432;
                        } else {
                            i17 = 33554432;
                        }
                        i12 = i113 | i17;
                    } else {
                        i12 = i3;
                    }
                    i13 = i12;
                    if ((i2 & 512) != 0) {
                        i14 = i13 | 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i15 = 536870912;
                        } else {
                            i15 = 268435456;
                        }
                        i14 = i13 | i15;
                    } else {
                        i14 = i13;
                    }
                    if ((i14 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function110 = function8;
                        AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                            public final void invoke(Composer composer3, int i114) {
                                if (!composer3.shouldExecute((i114 & 3) != 2, i114 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(296331566, i114, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                }
                                TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function110, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        dialogProperties3 = dialogProperties2;
                        modifier3 = modifier4;
                        function11 = function13;
                        function12 = function14;
                        shape2 = shape3;
                        j2 = containerColor;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        dialogProperties3 = dialogProperties2;
                        function11 = function9;
                        function12 = function10;
                        shape2 = shape;
                        j2 = j;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function9 = function4;
                i10 = i2 & 64;
                if (i10 != 0) {
                    if ((1572864 & i) == 0) {
                        function10 = function5;
                        if (composerStartRestartGroup.changedInstance(function10)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                    }
                    if ((i & 100663296) == 0) {
                        int i114 = i3;
                        if ((i2 & 256) == 0) {
                            i17 = 33554432;
                        } else {
                            i17 = 33554432;
                        }
                        i12 = i114 | i17;
                    } else {
                        i12 = i3;
                    }
                    i13 = i12;
                    if ((i2 & 512) != 0) {
                        i14 = i13 | 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i15 = 536870912;
                        } else {
                            i15 = 268435456;
                        }
                        i14 = i13 | i15;
                    } else {
                        i14 = i13;
                    }
                    if ((i14 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function111 = function8;
                        AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                            public final void invoke(Composer composer3, int i115) {
                                if (!composer3.shouldExecute((i115 & 3) != 2, i115 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(296331566, i115, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                }
                                TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function111, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        dialogProperties3 = dialogProperties2;
                        modifier3 = modifier4;
                        function11 = function13;
                        function12 = function14;
                        shape2 = shape3;
                        j2 = containerColor;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        dialogProperties3 = dialogProperties2;
                        function11 = function9;
                        function12 = function10;
                        shape2 = shape;
                        j2 = j;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                function10 = function5;
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                }
                if ((i & 100663296) == 0) {
                    int i115 = i3;
                    if ((i2 & 256) == 0) {
                        i17 = 33554432;
                    } else {
                        i17 = 33554432;
                    }
                    i12 = i115 | i17;
                } else {
                    i12 = i3;
                }
                i13 = i12;
                if ((i2 & 512) != 0) {
                    i14 = i13 | 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i15 = 536870912;
                    } else {
                        i15 = 268435456;
                    }
                    i14 = i13 | i15;
                } else {
                    i14 = i13;
                }
                if ((i14 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function112 = function8;
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                        public final void invoke(Composer composer3, int i116) {
                            if (!composer3.shouldExecute((i116 & 3) != 2, i116 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(296331566, i116, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                            }
                            TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function112, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties2;
                    modifier3 = modifier4;
                    function11 = function13;
                    function12 = function14;
                    shape2 = shape3;
                    j2 = containerColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                    function11 = function9;
                    function12 = function10;
                    shape2 = shape;
                    j2 = j;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            modifier2 = modifier;
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    dialogProperties2 = dialogProperties;
                    if (composerStartRestartGroup.changed(dialogProperties2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        function9 = function4;
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 64;
                    if (i10 != 0) {
                        if ((1572864 & i) == 0) {
                            function10 = function5;
                            if (composerStartRestartGroup.changedInstance(function10)) {
                                i11 = 1048576;
                            } else {
                                i11 = 524288;
                            }
                            i3 |= i11;
                        }
                        if ((i & 12582912) != 0) {
                            i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                        }
                        if ((i & 100663296) == 0) {
                            int i116 = i3;
                            if ((i2 & 256) == 0) {
                                i17 = 33554432;
                            } else {
                                i17 = 33554432;
                            }
                            i12 = i116 | i17;
                        } else {
                            i12 = i3;
                        }
                        i13 = i12;
                        if ((i2 & 512) != 0) {
                            i14 = i13 | 805306368;
                        } else if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i15 = 536870912;
                            } else {
                                i15 = 268435456;
                            }
                            i14 = i13 | i15;
                        } else {
                            i14 = i13;
                        }
                        if ((i14 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties4 = dialogProperties2;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if (i10 != 0) {
                                    function10 = null;
                                }
                                if ((i2 & 128) != 0) {
                                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i14 &= -29360129;
                                } else {
                                    shape3 = shape;
                                }
                                if ((i2 & 256) != 0) {
                                    i14 &= -234881025;
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                } else {
                                    containerColor = j;
                                }
                                function13 = function9;
                                i16 = i14;
                                dialogProperties2 = dialogProperties4;
                                modifier4 = modifier2;
                                function14 = function10;
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties4 = dialogProperties2;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if (i10 != 0) {
                                    function10 = null;
                                }
                                if ((i2 & 128) != 0) {
                                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i14 &= -29360129;
                                } else {
                                    shape3 = shape;
                                }
                                if ((i2 & 256) != 0) {
                                    i14 &= -234881025;
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                } else {
                                    containerColor = j;
                                }
                                function13 = function9;
                                i16 = i14;
                                dialogProperties2 = dialogProperties4;
                                modifier4 = modifier2;
                                function14 = function10;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function113 = function8;
                            AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                                public final void invoke(Composer composer3, int i117) {
                                    if (!composer3.shouldExecute((i117 & 3) != 2, i117 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(296331566, i117, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                    }
                                    TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function113, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            dialogProperties3 = dialogProperties2;
                            modifier3 = modifier4;
                            function11 = function13;
                            function12 = function14;
                            shape2 = shape3;
                            j2 = containerColor;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            dialogProperties3 = dialogProperties2;
                            function11 = function9;
                            function12 = function10;
                            shape2 = shape;
                            j2 = j;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    function10 = function5;
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                    }
                    if ((i & 100663296) == 0) {
                        int i117 = i3;
                        if ((i2 & 256) == 0) {
                            i17 = 33554432;
                        } else {
                            i17 = 33554432;
                        }
                        i12 = i117 | i17;
                    } else {
                        i12 = i3;
                    }
                    i13 = i12;
                    if ((i2 & 512) != 0) {
                        i14 = i13 | 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i15 = 536870912;
                        } else {
                            i15 = 268435456;
                        }
                        i14 = i13 | i15;
                    } else {
                        i14 = i13;
                    }
                    if ((i14 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function114 = function8;
                        AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                            public final void invoke(Composer composer3, int i118) {
                                if (!composer3.shouldExecute((i118 & 3) != 2, i118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(296331566, i118, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                }
                                TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function114, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        dialogProperties3 = dialogProperties2;
                        modifier3 = modifier4;
                        function11 = function13;
                        function12 = function14;
                        shape2 = shape3;
                        j2 = containerColor;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        dialogProperties3 = dialogProperties2;
                        function11 = function9;
                        function12 = function10;
                        shape2 = shape;
                        j2 = j;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function9 = function4;
                i10 = i2 & 64;
                if (i10 != 0) {
                    if ((1572864 & i) == 0) {
                        function10 = function5;
                        if (composerStartRestartGroup.changedInstance(function10)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                    }
                    if ((i & 100663296) == 0) {
                        int i118 = i3;
                        if ((i2 & 256) == 0) {
                            i17 = 33554432;
                        } else {
                            i17 = 33554432;
                        }
                        i12 = i118 | i17;
                    } else {
                        i12 = i3;
                    }
                    i13 = i12;
                    if ((i2 & 512) != 0) {
                        i14 = i13 | 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i15 = 536870912;
                        } else {
                            i15 = 268435456;
                        }
                        i14 = i13 | i15;
                    } else {
                        i14 = i13;
                    }
                    if ((i14 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function115 = function8;
                        AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                            public final void invoke(Composer composer3, int i119) {
                                if (!composer3.shouldExecute((i119 & 3) != 2, i119 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(296331566, i119, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                }
                                TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function115, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        dialogProperties3 = dialogProperties2;
                        modifier3 = modifier4;
                        function11 = function13;
                        function12 = function14;
                        shape2 = shape3;
                        j2 = containerColor;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        dialogProperties3 = dialogProperties2;
                        function11 = function9;
                        function12 = function10;
                        shape2 = shape;
                        j2 = j;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                function10 = function5;
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                }
                if ((i & 100663296) == 0) {
                    int i119 = i3;
                    if ((i2 & 256) == 0) {
                        i17 = 33554432;
                    } else {
                        i17 = 33554432;
                    }
                    i12 = i119 | i17;
                } else {
                    i12 = i3;
                }
                i13 = i12;
                if ((i2 & 512) != 0) {
                    i14 = i13 | 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i15 = 536870912;
                    } else {
                        i15 = 268435456;
                    }
                    i14 = i13 | i15;
                } else {
                    i14 = i13;
                }
                if ((i14 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function116 = function8;
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                        public final void invoke(Composer composer3, int i1110) {
                            if (!composer3.shouldExecute((i1110 & 3) != 2, i1110 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(296331566, i1110, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                            }
                            TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function116, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties2;
                    modifier3 = modifier4;
                    function11 = function13;
                    function12 = function14;
                    shape2 = shape3;
                    j2 = containerColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                    function11 = function9;
                    function12 = function10;
                    shape2 = shape;
                    j2 = j;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            dialogProperties2 = dialogProperties;
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    function9 = function4;
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 64;
                if (i10 != 0) {
                    if ((1572864 & i) == 0) {
                        function10 = function5;
                        if (composerStartRestartGroup.changedInstance(function10)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                    }
                    if ((i & 100663296) == 0) {
                        int i1110 = i3;
                        if ((i2 & 256) == 0) {
                            i17 = 33554432;
                        } else {
                            i17 = 33554432;
                        }
                        i12 = i1110 | i17;
                    } else {
                        i12 = i3;
                    }
                    i13 = i12;
                    if ((i2 & 512) != 0) {
                        i14 = i13 | 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i15 = 536870912;
                        } else {
                            i15 = 268435456;
                        }
                        i14 = i13 | i15;
                    } else {
                        i14 = i13;
                    }
                    if ((i14 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function117 = function8;
                        AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                            public final void invoke(Composer composer3, int i1111) {
                                if (!composer3.shouldExecute((i1111 & 3) != 2, i1111 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(296331566, i1111, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                }
                                TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function117, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        dialogProperties3 = dialogProperties2;
                        modifier3 = modifier4;
                        function11 = function13;
                        function12 = function14;
                        shape2 = shape3;
                        j2 = containerColor;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        dialogProperties3 = dialogProperties2;
                        function11 = function9;
                        function12 = function10;
                        shape2 = shape;
                        j2 = j;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                function10 = function5;
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                }
                if ((i & 100663296) == 0) {
                    int i1111 = i3;
                    if ((i2 & 256) == 0) {
                        i17 = 33554432;
                    } else {
                        i17 = 33554432;
                    }
                    i12 = i1111 | i17;
                } else {
                    i12 = i3;
                }
                i13 = i12;
                if ((i2 & 512) != 0) {
                    i14 = i13 | 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i15 = 536870912;
                    } else {
                        i15 = 268435456;
                    }
                    i14 = i13 | i15;
                } else {
                    i14 = i13;
                }
                if ((i14 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function118 = function8;
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                        public final void invoke(Composer composer3, int i1112) {
                            if (!composer3.shouldExecute((i1112 & 3) != 2, i1112 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(296331566, i1112, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                            }
                            TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function118, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties2;
                    modifier3 = modifier4;
                    function11 = function13;
                    function12 = function14;
                    shape2 = shape3;
                    j2 = containerColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                    function11 = function9;
                    function12 = function10;
                    shape2 = shape;
                    j2 = j;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function9 = function4;
            i10 = i2 & 64;
            if (i10 != 0) {
                if ((1572864 & i) == 0) {
                    function10 = function5;
                    if (composerStartRestartGroup.changedInstance(function10)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                }
                if ((i & 100663296) == 0) {
                    int i1112 = i3;
                    if ((i2 & 256) == 0) {
                        i17 = 33554432;
                    } else {
                        i17 = 33554432;
                    }
                    i12 = i1112 | i17;
                } else {
                    i12 = i3;
                }
                i13 = i12;
                if ((i2 & 512) != 0) {
                    i14 = i13 | 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i15 = 536870912;
                    } else {
                        i15 = 268435456;
                    }
                    i14 = i13 | i15;
                } else {
                    i14 = i13;
                }
                if ((i14 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function119 = function8;
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                        public final void invoke(Composer composer3, int i1113) {
                            if (!composer3.shouldExecute((i1113 & 3) != 2, i1113 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(296331566, i1113, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                            }
                            TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function119, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties2;
                    modifier3 = modifier4;
                    function11 = function13;
                    function12 = function14;
                    shape2 = shape3;
                    j2 = containerColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                    function11 = function9;
                    function12 = function10;
                    shape2 = shape;
                    j2 = j;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            function10 = function5;
            if ((i & 12582912) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
            }
            if ((i & 100663296) == 0) {
                int i1113 = i3;
                if ((i2 & 256) == 0) {
                    i17 = 33554432;
                } else {
                    i17 = 33554432;
                }
                i12 = i1113 | i17;
            } else {
                i12 = i3;
            }
            i13 = i12;
            if ((i2 & 512) != 0) {
                i14 = i13 | 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i15 = 536870912;
                } else {
                    i15 = 268435456;
                }
                i14 = i13 | i15;
            } else {
                i14 = i13;
            }
            if ((i14 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if (i10 != 0) {
                        function10 = null;
                    }
                    if ((i2 & 128) != 0) {
                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i14 &= -29360129;
                    } else {
                        shape3 = shape;
                    }
                    if ((i2 & 256) != 0) {
                        i14 &= -234881025;
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        containerColor = j;
                    }
                    function13 = function9;
                    i16 = i14;
                    dialogProperties2 = dialogProperties4;
                    modifier4 = modifier2;
                    function14 = function10;
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if (i10 != 0) {
                        function10 = null;
                    }
                    if ((i2 & 128) != 0) {
                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i14 &= -29360129;
                    } else {
                        shape3 = shape;
                    }
                    if ((i2 & 256) != 0) {
                        i14 &= -234881025;
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        containerColor = j;
                    }
                    function13 = function9;
                    i16 = i14;
                    dialogProperties2 = dialogProperties4;
                    modifier4 = modifier2;
                    function14 = function10;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function1110 = function8;
                AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                    public final void invoke(Composer composer3, int i1114) {
                        if (!composer3.shouldExecute((i1114 & 3) != 2, i1114 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(296331566, i1114, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                        }
                        TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function1110, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                dialogProperties3 = dialogProperties2;
                modifier3 = modifier4;
                function11 = function13;
                function12 = function14;
                shape2 = shape3;
                j2 = containerColor;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                dialogProperties3 = dialogProperties2;
                function11 = function9;
                function12 = function10;
                shape2 = shape;
                j2 = j;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        function8 = function3;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    dialogProperties2 = dialogProperties;
                    if (composerStartRestartGroup.changed(dialogProperties2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        function9 = function4;
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 64;
                    if (i10 != 0) {
                        if ((1572864 & i) == 0) {
                            function10 = function5;
                            if (composerStartRestartGroup.changedInstance(function10)) {
                                i11 = 1048576;
                            } else {
                                i11 = 524288;
                            }
                            i3 |= i11;
                        }
                        if ((i & 12582912) != 0) {
                            i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                        }
                        if ((i & 100663296) == 0) {
                            int i1114 = i3;
                            if ((i2 & 256) == 0) {
                                i17 = 33554432;
                            } else {
                                i17 = 33554432;
                            }
                            i12 = i1114 | i17;
                        } else {
                            i12 = i3;
                        }
                        i13 = i12;
                        if ((i2 & 512) != 0) {
                            i14 = i13 | 805306368;
                        } else if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i15 = 536870912;
                            } else {
                                i15 = 268435456;
                            }
                            i14 = i13 | i15;
                        } else {
                            i14 = i13;
                        }
                        if ((i14 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties4 = dialogProperties2;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if (i10 != 0) {
                                    function10 = null;
                                }
                                if ((i2 & 128) != 0) {
                                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i14 &= -29360129;
                                } else {
                                    shape3 = shape;
                                }
                                if ((i2 & 256) != 0) {
                                    i14 &= -234881025;
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                } else {
                                    containerColor = j;
                                }
                                function13 = function9;
                                i16 = i14;
                                dialogProperties2 = dialogProperties4;
                                modifier4 = modifier2;
                                function14 = function10;
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties4 = dialogProperties2;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if (i10 != 0) {
                                    function10 = null;
                                }
                                if ((i2 & 128) != 0) {
                                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i14 &= -29360129;
                                } else {
                                    shape3 = shape;
                                }
                                if ((i2 & 256) != 0) {
                                    i14 &= -234881025;
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                } else {
                                    containerColor = j;
                                }
                                function13 = function9;
                                i16 = i14;
                                dialogProperties2 = dialogProperties4;
                                modifier4 = modifier2;
                                function14 = function10;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function1111 = function8;
                            AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                                public final void invoke(Composer composer3, int i1115) {
                                    if (!composer3.shouldExecute((i1115 & 3) != 2, i1115 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(296331566, i1115, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                    }
                                    TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function1111, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            dialogProperties3 = dialogProperties2;
                            modifier3 = modifier4;
                            function11 = function13;
                            function12 = function14;
                            shape2 = shape3;
                            j2 = containerColor;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            dialogProperties3 = dialogProperties2;
                            function11 = function9;
                            function12 = function10;
                            shape2 = shape;
                            j2 = j;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    function10 = function5;
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                    }
                    if ((i & 100663296) == 0) {
                        int i1115 = i3;
                        if ((i2 & 256) == 0) {
                            i17 = 33554432;
                        } else {
                            i17 = 33554432;
                        }
                        i12 = i1115 | i17;
                    } else {
                        i12 = i3;
                    }
                    i13 = i12;
                    if ((i2 & 512) != 0) {
                        i14 = i13 | 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i15 = 536870912;
                        } else {
                            i15 = 268435456;
                        }
                        i14 = i13 | i15;
                    } else {
                        i14 = i13;
                    }
                    if ((i14 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1112 = function8;
                        AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                            public final void invoke(Composer composer3, int i1116) {
                                if (!composer3.shouldExecute((i1116 & 3) != 2, i1116 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(296331566, i1116, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                }
                                TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function1112, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        dialogProperties3 = dialogProperties2;
                        modifier3 = modifier4;
                        function11 = function13;
                        function12 = function14;
                        shape2 = shape3;
                        j2 = containerColor;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        dialogProperties3 = dialogProperties2;
                        function11 = function9;
                        function12 = function10;
                        shape2 = shape;
                        j2 = j;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function9 = function4;
                i10 = i2 & 64;
                if (i10 != 0) {
                    if ((1572864 & i) == 0) {
                        function10 = function5;
                        if (composerStartRestartGroup.changedInstance(function10)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                    }
                    if ((i & 100663296) == 0) {
                        int i1116 = i3;
                        if ((i2 & 256) == 0) {
                            i17 = 33554432;
                        } else {
                            i17 = 33554432;
                        }
                        i12 = i1116 | i17;
                    } else {
                        i12 = i3;
                    }
                    i13 = i12;
                    if ((i2 & 512) != 0) {
                        i14 = i13 | 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i15 = 536870912;
                        } else {
                            i15 = 268435456;
                        }
                        i14 = i13 | i15;
                    } else {
                        i14 = i13;
                    }
                    if ((i14 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1113 = function8;
                        AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                            public final void invoke(Composer composer3, int i1117) {
                                if (!composer3.shouldExecute((i1117 & 3) != 2, i1117 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(296331566, i1117, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                }
                                TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function1113, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        dialogProperties3 = dialogProperties2;
                        modifier3 = modifier4;
                        function11 = function13;
                        function12 = function14;
                        shape2 = shape3;
                        j2 = containerColor;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        dialogProperties3 = dialogProperties2;
                        function11 = function9;
                        function12 = function10;
                        shape2 = shape;
                        j2 = j;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                function10 = function5;
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                }
                if ((i & 100663296) == 0) {
                    int i1117 = i3;
                    if ((i2 & 256) == 0) {
                        i17 = 33554432;
                    } else {
                        i17 = 33554432;
                    }
                    i12 = i1117 | i17;
                } else {
                    i12 = i3;
                }
                i13 = i12;
                if ((i2 & 512) != 0) {
                    i14 = i13 | 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i15 = 536870912;
                    } else {
                        i15 = 268435456;
                    }
                    i14 = i13 | i15;
                } else {
                    i14 = i13;
                }
                if ((i14 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1114 = function8;
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                        public final void invoke(Composer composer3, int i1118) {
                            if (!composer3.shouldExecute((i1118 & 3) != 2, i1118 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(296331566, i1118, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                            }
                            TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function1114, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties2;
                    modifier3 = modifier4;
                    function11 = function13;
                    function12 = function14;
                    shape2 = shape3;
                    j2 = containerColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                    function11 = function9;
                    function12 = function10;
                    shape2 = shape;
                    j2 = j;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            dialogProperties2 = dialogProperties;
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    function9 = function4;
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 64;
                if (i10 != 0) {
                    if ((1572864 & i) == 0) {
                        function10 = function5;
                        if (composerStartRestartGroup.changedInstance(function10)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                    }
                    if ((i & 100663296) == 0) {
                        int i1118 = i3;
                        if ((i2 & 256) == 0) {
                            i17 = 33554432;
                        } else {
                            i17 = 33554432;
                        }
                        i12 = i1118 | i17;
                    } else {
                        i12 = i3;
                    }
                    i13 = i12;
                    if ((i2 & 512) != 0) {
                        i14 = i13 | 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i15 = 536870912;
                        } else {
                            i15 = 268435456;
                        }
                        i14 = i13 | i15;
                    } else {
                        i14 = i13;
                    }
                    if ((i14 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1115 = function8;
                        AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                            public final void invoke(Composer composer3, int i1119) {
                                if (!composer3.shouldExecute((i1119 & 3) != 2, i1119 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(296331566, i1119, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                }
                                TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function1115, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        dialogProperties3 = dialogProperties2;
                        modifier3 = modifier4;
                        function11 = function13;
                        function12 = function14;
                        shape2 = shape3;
                        j2 = containerColor;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        dialogProperties3 = dialogProperties2;
                        function11 = function9;
                        function12 = function10;
                        shape2 = shape;
                        j2 = j;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                function10 = function5;
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                }
                if ((i & 100663296) == 0) {
                    int i1119 = i3;
                    if ((i2 & 256) == 0) {
                        i17 = 33554432;
                    } else {
                        i17 = 33554432;
                    }
                    i12 = i1119 | i17;
                } else {
                    i12 = i3;
                }
                i13 = i12;
                if ((i2 & 512) != 0) {
                    i14 = i13 | 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i15 = 536870912;
                    } else {
                        i15 = 268435456;
                    }
                    i14 = i13 | i15;
                } else {
                    i14 = i13;
                }
                if ((i14 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1116 = function8;
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                        public final void invoke(Composer composer3, int i11110) {
                            if (!composer3.shouldExecute((i11110 & 3) != 2, i11110 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(296331566, i11110, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                            }
                            TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function1116, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties2;
                    modifier3 = modifier4;
                    function11 = function13;
                    function12 = function14;
                    shape2 = shape3;
                    j2 = containerColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                    function11 = function9;
                    function12 = function10;
                    shape2 = shape;
                    j2 = j;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function9 = function4;
            i10 = i2 & 64;
            if (i10 != 0) {
                if ((1572864 & i) == 0) {
                    function10 = function5;
                    if (composerStartRestartGroup.changedInstance(function10)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                }
                if ((i & 100663296) == 0) {
                    int i11110 = i3;
                    if ((i2 & 256) == 0) {
                        i17 = 33554432;
                    } else {
                        i17 = 33554432;
                    }
                    i12 = i11110 | i17;
                } else {
                    i12 = i3;
                }
                i13 = i12;
                if ((i2 & 512) != 0) {
                    i14 = i13 | 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i15 = 536870912;
                    } else {
                        i15 = 268435456;
                    }
                    i14 = i13 | i15;
                } else {
                    i14 = i13;
                }
                if ((i14 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1117 = function8;
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                        public final void invoke(Composer composer3, int i11111) {
                            if (!composer3.shouldExecute((i11111 & 3) != 2, i11111 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(296331566, i11111, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                            }
                            TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function1117, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties2;
                    modifier3 = modifier4;
                    function11 = function13;
                    function12 = function14;
                    shape2 = shape3;
                    j2 = containerColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                    function11 = function9;
                    function12 = function10;
                    shape2 = shape;
                    j2 = j;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            function10 = function5;
            if ((i & 12582912) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
            }
            if ((i & 100663296) == 0) {
                int i11111 = i3;
                if ((i2 & 256) == 0) {
                    i17 = 33554432;
                } else {
                    i17 = 33554432;
                }
                i12 = i11111 | i17;
            } else {
                i12 = i3;
            }
            i13 = i12;
            if ((i2 & 512) != 0) {
                i14 = i13 | 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i15 = 536870912;
                } else {
                    i15 = 268435456;
                }
                i14 = i13 | i15;
            } else {
                i14 = i13;
            }
            if ((i14 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if (i10 != 0) {
                        function10 = null;
                    }
                    if ((i2 & 128) != 0) {
                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i14 &= -29360129;
                    } else {
                        shape3 = shape;
                    }
                    if ((i2 & 256) != 0) {
                        i14 &= -234881025;
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        containerColor = j;
                    }
                    function13 = function9;
                    i16 = i14;
                    dialogProperties2 = dialogProperties4;
                    modifier4 = modifier2;
                    function14 = function10;
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if (i10 != 0) {
                        function10 = null;
                    }
                    if ((i2 & 128) != 0) {
                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i14 &= -29360129;
                    } else {
                        shape3 = shape;
                    }
                    if ((i2 & 256) != 0) {
                        i14 &= -234881025;
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        containerColor = j;
                    }
                    function13 = function9;
                    i16 = i14;
                    dialogProperties2 = dialogProperties4;
                    modifier4 = modifier2;
                    function14 = function10;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function1118 = function8;
                AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                    public final void invoke(Composer composer3, int i11112) {
                        if (!composer3.shouldExecute((i11112 & 3) != 2, i11112 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(296331566, i11112, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                        }
                        TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function1118, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                dialogProperties3 = dialogProperties2;
                modifier3 = modifier4;
                function11 = function13;
                function12 = function14;
                shape2 = shape3;
                j2 = containerColor;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                dialogProperties3 = dialogProperties2;
                function11 = function9;
                function12 = function10;
                shape2 = shape;
                j2 = j;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        modifier2 = modifier;
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                dialogProperties2 = dialogProperties;
                if (composerStartRestartGroup.changed(dialogProperties2)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    function9 = function4;
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 64;
                if (i10 != 0) {
                    if ((1572864 & i) == 0) {
                        function10 = function5;
                        if (composerStartRestartGroup.changedInstance(function10)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                    }
                    if ((i & 100663296) == 0) {
                        int i11112 = i3;
                        if ((i2 & 256) == 0) {
                            i17 = 33554432;
                        } else {
                            i17 = 33554432;
                        }
                        i12 = i11112 | i17;
                    } else {
                        i12 = i3;
                    }
                    i13 = i12;
                    if ((i2 & 512) != 0) {
                        i14 = i13 | 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i15 = 536870912;
                        } else {
                            i15 = 268435456;
                        }
                        i14 = i13 | i15;
                    } else {
                        i14 = i13;
                    }
                    if ((i14 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties4 = dialogProperties2;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if (i10 != 0) {
                                function10 = null;
                            }
                            if ((i2 & 128) != 0) {
                                shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i14 &= -29360129;
                            } else {
                                shape3 = shape;
                            }
                            if ((i2 & 256) != 0) {
                                i14 &= -234881025;
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                containerColor = j;
                            }
                            function13 = function9;
                            i16 = i14;
                            dialogProperties2 = dialogProperties4;
                            modifier4 = modifier2;
                            function14 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1119 = function8;
                        AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                            public final void invoke(Composer composer3, int i11113) {
                                if (!composer3.shouldExecute((i11113 & 3) != 2, i11113 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(296331566, i11113, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                                }
                                TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function1119, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        dialogProperties3 = dialogProperties2;
                        modifier3 = modifier4;
                        function11 = function13;
                        function12 = function14;
                        shape2 = shape3;
                        j2 = containerColor;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        dialogProperties3 = dialogProperties2;
                        function11 = function9;
                        function12 = function10;
                        shape2 = shape;
                        j2 = j;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                function10 = function5;
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                }
                if ((i & 100663296) == 0) {
                    int i11113 = i3;
                    if ((i2 & 256) == 0) {
                        i17 = 33554432;
                    } else {
                        i17 = 33554432;
                    }
                    i12 = i11113 | i17;
                } else {
                    i12 = i3;
                }
                i13 = i12;
                if ((i2 & 512) != 0) {
                    i14 = i13 | 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i15 = 536870912;
                    } else {
                        i15 = 268435456;
                    }
                    i14 = i13 | i15;
                } else {
                    i14 = i13;
                }
                if ((i14 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function11110 = function8;
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                        public final void invoke(Composer composer3, int i11114) {
                            if (!composer3.shouldExecute((i11114 & 3) != 2, i11114 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(296331566, i11114, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                            }
                            TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function11110, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties2;
                    modifier3 = modifier4;
                    function11 = function13;
                    function12 = function14;
                    shape2 = shape3;
                    j2 = containerColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                    function11 = function9;
                    function12 = function10;
                    shape2 = shape;
                    j2 = j;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function9 = function4;
            i10 = i2 & 64;
            if (i10 != 0) {
                if ((1572864 & i) == 0) {
                    function10 = function5;
                    if (composerStartRestartGroup.changedInstance(function10)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                }
                if ((i & 100663296) == 0) {
                    int i11114 = i3;
                    if ((i2 & 256) == 0) {
                        i17 = 33554432;
                    } else {
                        i17 = 33554432;
                    }
                    i12 = i11114 | i17;
                } else {
                    i12 = i3;
                }
                i13 = i12;
                if ((i2 & 512) != 0) {
                    i14 = i13 | 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i15 = 536870912;
                    } else {
                        i15 = 268435456;
                    }
                    i14 = i13 | i15;
                } else {
                    i14 = i13;
                }
                if ((i14 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function11111 = function8;
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                        public final void invoke(Composer composer3, int i11115) {
                            if (!composer3.shouldExecute((i11115 & 3) != 2, i11115 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(296331566, i11115, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                            }
                            TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function11111, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties2;
                    modifier3 = modifier4;
                    function11 = function13;
                    function12 = function14;
                    shape2 = shape3;
                    j2 = containerColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                    function11 = function9;
                    function12 = function10;
                    shape2 = shape;
                    j2 = j;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            function10 = function5;
            if ((i & 12582912) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
            }
            if ((i & 100663296) == 0) {
                int i11115 = i3;
                if ((i2 & 256) == 0) {
                    i17 = 33554432;
                } else {
                    i17 = 33554432;
                }
                i12 = i11115 | i17;
            } else {
                i12 = i3;
            }
            i13 = i12;
            if ((i2 & 512) != 0) {
                i14 = i13 | 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i15 = 536870912;
                } else {
                    i15 = 268435456;
                }
                i14 = i13 | i15;
            } else {
                i14 = i13;
            }
            if ((i14 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if (i10 != 0) {
                        function10 = null;
                    }
                    if ((i2 & 128) != 0) {
                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i14 &= -29360129;
                    } else {
                        shape3 = shape;
                    }
                    if ((i2 & 256) != 0) {
                        i14 &= -234881025;
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        containerColor = j;
                    }
                    function13 = function9;
                    i16 = i14;
                    dialogProperties2 = dialogProperties4;
                    modifier4 = modifier2;
                    function14 = function10;
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if (i10 != 0) {
                        function10 = null;
                    }
                    if ((i2 & 128) != 0) {
                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i14 &= -29360129;
                    } else {
                        shape3 = shape;
                    }
                    if ((i2 & 256) != 0) {
                        i14 &= -234881025;
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        containerColor = j;
                    }
                    function13 = function9;
                    i16 = i14;
                    dialogProperties2 = dialogProperties4;
                    modifier4 = modifier2;
                    function14 = function10;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function11112 = function8;
                AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                    public final void invoke(Composer composer3, int i11116) {
                        if (!composer3.shouldExecute((i11116 & 3) != 2, i11116 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(296331566, i11116, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                        }
                        TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function11112, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                dialogProperties3 = dialogProperties2;
                modifier3 = modifier4;
                function11 = function13;
                function12 = function14;
                shape2 = shape3;
                j2 = containerColor;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                dialogProperties3 = dialogProperties2;
                function11 = function9;
                function12 = function10;
                shape2 = shape;
                j2 = j;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        dialogProperties2 = dialogProperties;
        i8 = i2 & 32;
        if (i8 != 0) {
            if ((196608 & i) == 0) {
                function9 = function4;
                if (composerStartRestartGroup.changedInstance(function9)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i3 |= i9;
            }
            i10 = i2 & 64;
            if (i10 != 0) {
                if ((1572864 & i) == 0) {
                    function10 = function5;
                    if (composerStartRestartGroup.changedInstance(function10)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
                }
                if ((i & 100663296) == 0) {
                    int i11116 = i3;
                    if ((i2 & 256) == 0) {
                        i17 = 33554432;
                    } else {
                        i17 = 33554432;
                    }
                    i12 = i11116 | i17;
                } else {
                    i12 = i3;
                }
                i13 = i12;
                if ((i2 & 512) != 0) {
                    i14 = i13 | 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i15 = 536870912;
                    } else {
                        i15 = 268435456;
                    }
                    i14 = i13 | i15;
                } else {
                    i14 = i13;
                }
                if ((i14 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties4 = dialogProperties2;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if (i10 != 0) {
                            function10 = null;
                        }
                        if ((i2 & 128) != 0) {
                            shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i14 &= -29360129;
                        } else {
                            shape3 = shape;
                        }
                        if ((i2 & 256) != 0) {
                            i14 &= -234881025;
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            containerColor = j;
                        }
                        function13 = function9;
                        i16 = i14;
                        dialogProperties2 = dialogProperties4;
                        modifier4 = modifier2;
                        function14 = function10;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function11113 = function8;
                    AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                        public final void invoke(Composer composer3, int i11117) {
                            if (!composer3.shouldExecute((i11117 & 3) != 2, i11117 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(296331566, i11117, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                            }
                            TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function11113, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    dialogProperties3 = dialogProperties2;
                    modifier3 = modifier4;
                    function11 = function13;
                    function12 = function14;
                    shape2 = shape3;
                    j2 = containerColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    dialogProperties3 = dialogProperties2;
                    function11 = function9;
                    function12 = function10;
                    shape2 = shape;
                    j2 = j;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            function10 = function5;
            if ((i & 12582912) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
            }
            if ((i & 100663296) == 0) {
                int i11117 = i3;
                if ((i2 & 256) == 0) {
                    i17 = 33554432;
                } else {
                    i17 = 33554432;
                }
                i12 = i11117 | i17;
            } else {
                i12 = i3;
            }
            i13 = i12;
            if ((i2 & 512) != 0) {
                i14 = i13 | 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i15 = 536870912;
                } else {
                    i15 = 268435456;
                }
                i14 = i13 | i15;
            } else {
                i14 = i13;
            }
            if ((i14 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if (i10 != 0) {
                        function10 = null;
                    }
                    if ((i2 & 128) != 0) {
                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i14 &= -29360129;
                    } else {
                        shape3 = shape;
                    }
                    if ((i2 & 256) != 0) {
                        i14 &= -234881025;
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        containerColor = j;
                    }
                    function13 = function9;
                    i16 = i14;
                    dialogProperties2 = dialogProperties4;
                    modifier4 = modifier2;
                    function14 = function10;
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if (i10 != 0) {
                        function10 = null;
                    }
                    if ((i2 & 128) != 0) {
                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i14 &= -29360129;
                    } else {
                        shape3 = shape;
                    }
                    if ((i2 & 256) != 0) {
                        i14 &= -234881025;
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        containerColor = j;
                    }
                    function13 = function9;
                    i16 = i14;
                    dialogProperties2 = dialogProperties4;
                    modifier4 = modifier2;
                    function14 = function10;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function11114 = function8;
                AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                    public final void invoke(Composer composer3, int i11118) {
                        if (!composer3.shouldExecute((i11118 & 3) != 2, i11118 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(296331566, i11118, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                        }
                        TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function11114, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                dialogProperties3 = dialogProperties2;
                modifier3 = modifier4;
                function11 = function13;
                function12 = function14;
                shape2 = shape3;
                j2 = containerColor;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                dialogProperties3 = dialogProperties2;
                function11 = function9;
                function12 = function10;
                shape2 = shape;
                j2 = j;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        function9 = function4;
        i10 = i2 & 64;
        if (i10 != 0) {
            if ((1572864 & i) == 0) {
                function10 = function5;
                if (composerStartRestartGroup.changedInstance(function10)) {
                    i11 = 1048576;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i & 12582912) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
            }
            if ((i & 100663296) == 0) {
                int i11118 = i3;
                if ((i2 & 256) == 0) {
                    i17 = 33554432;
                } else {
                    i17 = 33554432;
                }
                i12 = i11118 | i17;
            } else {
                i12 = i3;
            }
            i13 = i12;
            if ((i2 & 512) != 0) {
                i14 = i13 | 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i15 = 536870912;
                } else {
                    i15 = 268435456;
                }
                i14 = i13 | i15;
            } else {
                i14 = i13;
            }
            if ((i14 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if (i10 != 0) {
                        function10 = null;
                    }
                    if ((i2 & 128) != 0) {
                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i14 &= -29360129;
                    } else {
                        shape3 = shape;
                    }
                    if ((i2 & 256) != 0) {
                        i14 &= -234881025;
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        containerColor = j;
                    }
                    function13 = function9;
                    i16 = i14;
                    dialogProperties2 = dialogProperties4;
                    modifier4 = modifier2;
                    function14 = function10;
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties4 = dialogProperties2;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if (i10 != 0) {
                        function10 = null;
                    }
                    if ((i2 & 128) != 0) {
                        shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i14 &= -29360129;
                    } else {
                        shape3 = shape;
                    }
                    if ((i2 & 256) != 0) {
                        i14 &= -234881025;
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        containerColor = j;
                    }
                    function13 = function9;
                    i16 = i14;
                    dialogProperties2 = dialogProperties4;
                    modifier4 = modifier2;
                    function14 = function10;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function11115 = function8;
                AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                    public final void invoke(Composer composer3, int i11119) {
                        if (!composer3.shouldExecute((i11119 & 3) != 2, i11119 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(296331566, i11119, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                        }
                        TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function11115, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                dialogProperties3 = dialogProperties2;
                modifier3 = modifier4;
                function11 = function13;
                function12 = function14;
                shape2 = shape3;
                j2 = containerColor;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                dialogProperties3 = dialogProperties2;
                function11 = function9;
                function12 = function10;
                shape2 = shape;
                j2 = j;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 1572864;
        function10 = function5;
        if ((i & 12582912) != 0) {
            i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(shape)) ? 4194304 : 8388608;
        }
        if ((i & 100663296) == 0) {
            int i11119 = i3;
            if ((i2 & 256) == 0) {
                i17 = 33554432;
            } else {
                i17 = 33554432;
            }
            i12 = i11119 | i17;
        } else {
            i12 = i3;
        }
        i13 = i12;
        if ((i2 & 512) != 0) {
            i14 = i13 | 805306368;
        } else if ((i & 805306368) == 0) {
            if (composerStartRestartGroup.changedInstance(function6)) {
                i15 = 536870912;
            } else {
                i15 = 268435456;
            }
            i14 = i13 | i15;
        } else {
            i14 = i13;
        }
        if ((i14 & 306783379) != 306783378) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i14 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                } else {
                    dialogProperties4 = dialogProperties2;
                }
                if (i8 != 0) {
                    function9 = null;
                }
                if (i10 != 0) {
                    function10 = null;
                }
                if ((i2 & 128) != 0) {
                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i14 &= -29360129;
                } else {
                    shape3 = shape;
                }
                if ((i2 & 256) != 0) {
                    i14 &= -234881025;
                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    containerColor = j;
                }
                function13 = function9;
                i16 = i14;
                dialogProperties2 = dialogProperties4;
                modifier4 = modifier2;
                function14 = function10;
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    dialogProperties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                } else {
                    dialogProperties4 = dialogProperties2;
                }
                if (i8 != 0) {
                    function9 = null;
                }
                if (i10 != 0) {
                    function10 = null;
                }
                if ((i2 & 128) != 0) {
                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i14 &= -29360129;
                } else {
                    shape3 = shape;
                }
                if ((i2 & 256) != 0) {
                    i14 &= -234881025;
                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    containerColor = j;
                }
                function13 = function9;
                i16 = i14;
                dialogProperties2 = dialogProperties4;
                modifier4 = modifier2;
                function14 = function10;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(951250327, i16, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
            }
            final Function2<? super Composer, ? super Integer, Unit> function11116 = function8;
            AndroidDialog_androidKt.Dialog(function0, dialogProperties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                public final void invoke(Composer composer3, int i111110) {
                    if (!composer3.shouldExecute((i111110 & 3) != 2, i111110 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(296331566, i111110, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                    }
                    TimePickerDialogKt.m1127TimePickerDialogLayout3csKH6Y(function7, function11116, modifier4, function13, function14, shape3, containerColor, function6, composer3, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 9) & 112) | (i16 & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            dialogProperties3 = dialogProperties2;
            modifier3 = modifier4;
            function11 = function13;
            function12 = function14;
            shape2 = shape3;
            j2 = containerColor;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            dialogProperties3 = dialogProperties2;
            function11 = function9;
            function12 = function10;
            shape2 = shape;
            j2 = j;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerDialogKt.b(function0, function2, function3, modifier3, dialogProperties3, function11, function12, shape2, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:108:0x012c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:109:0x012e  */
    /* JADX WARN: Code duplicated, block: B:112:0x0134  */
    /* JADX WARN: Code duplicated, block: B:114:0x0137  */
    /* JADX WARN: Code duplicated, block: B:117:0x013d  */
    /* JADX WARN: Code duplicated, block: B:118:0x0147  */
    /* JADX WARN: Code duplicated, block: B:121:0x014d  */
    /* JADX WARN: Code duplicated, block: B:122:0x0156  */
    /* JADX WARN: Code duplicated, block: B:125:0x0160  */
    /* JADX WARN: Code duplicated, block: B:128:0x01af  */
    /* JADX WARN: Code duplicated, block: B:131:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:134:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:136:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004a  */
    /* JADX WARN: Code duplicated, block: B:28:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0053  */
    /* JADX WARN: Code duplicated, block: B:32:0x005b  */
    /* JADX WARN: Code duplicated, block: B:33:0x005e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0085  */
    /* JADX WARN: Code duplicated, block: B:52:0x0089  */
    /* JADX WARN: Code duplicated, block: B:54:0x0091  */
    /* JADX WARN: Code duplicated, block: B:55:0x0094  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:78:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:83:0x00db  */
    /* JADX WARN: Code duplicated, block: B:85:0x00df  */
    /* JADX WARN: Code duplicated, block: B:87:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:92:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:93:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:96:0x0106  */
    /* JADX WARN: Code duplicated, block: B:98:0x0113  */
    /* JADX INFO: renamed from: TimePickerDialogLayout-3csKH6Y, reason: not valid java name */
    public static final void m1127TimePickerDialogLayout3csKH6Y(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Shape shape, long j, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function6, Composer composer, final int i, final int i2) {
        int i3;
        Function2<? super Composer, ? super Integer, Unit> function7;
        int i4;
        Modifier modifier2;
        int i5;
        int i6;
        Function2<? super Composer, ? super Integer, Unit> function8;
        int i7;
        int i8;
        Function2<? super Composer, ? super Integer, Unit> function9;
        int i9;
        Shape shape2;
        long containerColor;
        int i10;
        int i11;
        boolean z;
        Composer composer2;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final long j2;
        final Modifier modifier3;
        final Shape shape3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i12;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-401873644);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) == 0) {
            if ((i & 48) == 0) {
                function7 = function3;
                i3 |= composerStartRestartGroup.changedInstance(function7) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        function8 = function4;
                        if (composerStartRestartGroup.changedInstance(function8)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 16;
                    if (i8 != 0) {
                        if ((i & 24576) == 0) {
                            function9 = function5;
                            if (composerStartRestartGroup.changedInstance(function9)) {
                                i9 = 16384;
                            } else {
                                i9 = 8192;
                            }
                            i3 |= i9;
                        }
                        if ((196608 & i) == 0) {
                            if ((i2 & 32) == 0) {
                                shape2 = shape;
                                int i14 = composerStartRestartGroup.changed(shape2) ? 131072 : 65536;
                                i3 |= i14;
                            } else {
                                shape2 = shape;
                            }
                            i3 |= i14;
                        } else {
                            shape2 = shape;
                        }
                        if ((1572864 & i) == 0) {
                            if ((i2 & 64) == 0) {
                                containerColor = j;
                                int i15 = composerStartRestartGroup.changed(containerColor) ? 1048576 : 524288;
                                i3 |= i15;
                            } else {
                                containerColor = j;
                            }
                            i3 |= i15;
                        } else {
                            containerColor = j;
                        }
                        if ((i2 & 128) != 0) {
                            if ((i & 12582912) == 0) {
                                if (composerStartRestartGroup.changedInstance(function6)) {
                                    i10 = 8388608;
                                } else {
                                    i10 = 4194304;
                                }
                                i3 |= i10;
                            }
                            i11 = i3;
                            if ((i3 & 4793491) != 4793490) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i4 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i6 != 0) {
                                        function8 = null;
                                    }
                                    if (i8 != 0) {
                                        function9 = null;
                                    }
                                    if ((i2 & 32) != 0) {
                                        i12 = i11 & (-458753);
                                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    } else {
                                        i12 = i11;
                                    }
                                    if ((i2 & 64) != 0) {
                                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i13 = i12 & (-3670017);
                                    } else {
                                        i13 = i12;
                                    }
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    i13 = (i2 & 32) != 0 ? i11 & (-458753) : i11;
                                    if ((i2 & 64) != 0) {
                                        i13 &= -3670017;
                                    }
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                                }
                                final Function2<? super Composer, ? super Integer, Unit> function12 = function9;
                                final Function2<? super Composer, ? super Integer, Unit> function13 = function7;
                                final Function2<? super Composer, ? super Integer, Unit> function14 = function8;
                                long j3 = containerColor;
                                composer2 = composerStartRestartGroup;
                                SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                                    public final void invoke(Composer composer3, int i16) {
                                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                        }
                                        Function2<Composer, Integer, Unit> function15 = function13;
                                        final Function2<Composer, Integer, Unit> function16 = function14;
                                        final Function2<Composer, Integer, Unit> function17 = function12;
                                        final Function2<Composer, Integer, Unit> function18 = function2;
                                        TimePickerDialogKt.TimePickerCustomLayout(function15, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                            public final void invoke(Composer composer4, int i17) {
                                                if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                    composer4.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                                }
                                                Modifier.Companion companion = Modifier.INSTANCE;
                                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                                Function2<Composer, Integer, Unit> function19 = function16;
                                                Function2<Composer, Integer, Unit> function20 = function17;
                                                Function2<Composer, Integer, Unit> function21 = function18;
                                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                                CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                                ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                                Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                                if (composer4.getApplier() == null) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                composer4.startReusableNode();
                                                if (composer4.getInserting()) {
                                                    composer4.createNode(constructor);
                                                } else {
                                                    composer4.useNode();
                                                }
                                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                                }
                                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                                if (function19 == null) {
                                                    composer4.startReplaceGroup(675833080);
                                                } else {
                                                    composer4.startReplaceGroup(2100011049);
                                                    function19.invoke(composer4, 0);
                                                }
                                                composer4.endReplaceGroup();
                                                SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                                if (function20 == null) {
                                                    composer4.startReplaceGroup(675935256);
                                                } else {
                                                    composer4.startReplaceGroup(2100014345);
                                                    function20.invoke(composer4, 0);
                                                }
                                                composer4.endReplaceGroup();
                                                function21.invoke(composer4, 0);
                                                composer4.endNode();
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }

                                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                                invoke((Composer) obj, ((Number) obj2).intValue());
                                                return Unit.INSTANCE;
                                            }
                                        }, composer3, 54), function6, composer3, 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                function10 = function8;
                                j2 = j3;
                                function11 = function12;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                function10 = function8;
                                function11 = function9;
                                j2 = containerColor;
                            }
                            modifier3 = modifier2;
                            shape3 = shape2;
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                                    public final Object invoke(Object obj, Object obj2) {
                                        return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i3 |= 12582912;
                        i11 = i3;
                        if ((i3 & 4793491) != 4793490) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    function8 = null;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 = i11 & (-458753);
                                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 64) != 0) {
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i13 = i12 & (-3670017);
                                } else {
                                    i13 = i12;
                                }
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    function8 = null;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 = i11 & (-458753);
                                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 64) != 0) {
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i13 = i12 & (-3670017);
                                } else {
                                    i13 = i12;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function15 = function9;
                            final Function2<? super Composer, ? super Integer, Unit> function16 = function7;
                            final Function2<? super Composer, ? super Integer, Unit> function17 = function8;
                            long j4 = containerColor;
                            composer2 = composerStartRestartGroup;
                            SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                    }
                                    Function2<Composer, Integer, Unit> function18 = function16;
                                    final Function2<? super Composer, ? super Integer, Unit> function19 = function17;
                                    final Function2<? super Composer, ? super Integer, Unit> function110 = function15;
                                    final Function2<? super Composer, ? super Integer, Unit> function111 = function2;
                                    TimePickerDialogKt.TimePickerCustomLayout(function18, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                        public final void invoke(Composer composer4, int i17) {
                                            if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                composer4.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                            }
                                            Modifier.Companion companion = Modifier.INSTANCE;
                                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                            Function2<Composer, Integer, Unit> function112 = function19;
                                            Function2<Composer, Integer, Unit> function20 = function110;
                                            Function2<Composer, Integer, Unit> function21 = function111;
                                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                            Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                            if (composer4.getApplier() == null) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer4.startReusableNode();
                                            if (composer4.getInserting()) {
                                                composer4.createNode(constructor);
                                            } else {
                                                composer4.useNode();
                                            }
                                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                            if (function112 == null) {
                                                composer4.startReplaceGroup(675833080);
                                            } else {
                                                composer4.startReplaceGroup(2100011049);
                                                function112.invoke(composer4, 0);
                                            }
                                            composer4.endReplaceGroup();
                                            SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                            if (function20 == null) {
                                                composer4.startReplaceGroup(675935256);
                                            } else {
                                                composer4.startReplaceGroup(2100014345);
                                                function20.invoke(composer4, 0);
                                            }
                                            composer4.endReplaceGroup();
                                            function21.invoke(composer4, 0);
                                            composer4.endNode();
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composer3, 54), function6, composer3, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function10 = function8;
                            j2 = j4;
                            function11 = function15;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function8;
                            function11 = function9;
                            j2 = containerColor;
                        }
                        modifier3 = modifier2;
                        shape3 = shape2;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 24576;
                    function9 = function5;
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            shape2 = shape;
                            if (composerStartRestartGroup.changed(shape2)) {
                            }
                            i3 |= i14;
                        } else {
                            shape2 = shape;
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            containerColor = j;
                            if (composerStartRestartGroup.changed(containerColor)) {
                            }
                            i3 |= i15;
                        } else {
                            containerColor = j;
                        }
                        i3 |= i15;
                    } else {
                        containerColor = j;
                    }
                    if ((i2 & 128) != 0) {
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i10 = 8388608;
                            } else {
                                i10 = 4194304;
                            }
                            i3 |= i10;
                        }
                        i11 = i3;
                        if ((i3 & 4793491) != 4793490) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    function8 = null;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 = i11 & (-458753);
                                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 64) != 0) {
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i13 = i12 & (-3670017);
                                } else {
                                    i13 = i12;
                                }
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    function8 = null;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 = i11 & (-458753);
                                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 64) != 0) {
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i13 = i12 & (-3670017);
                                } else {
                                    i13 = i12;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function18 = function9;
                            final Function2<? super Composer, ? super Integer, Unit> function19 = function7;
                            final Function2<? super Composer, ? super Integer, Unit> function110 = function8;
                            long j5 = containerColor;
                            composer2 = composerStartRestartGroup;
                            SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                    }
                                    Function2<Composer, Integer, Unit> function111 = function19;
                                    final Function2<? super Composer, ? super Integer, Unit> function112 = function110;
                                    final Function2<? super Composer, ? super Integer, Unit> function113 = function18;
                                    final Function2<? super Composer, ? super Integer, Unit> function114 = function2;
                                    TimePickerDialogKt.TimePickerCustomLayout(function111, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                        public final void invoke(Composer composer4, int i17) {
                                            if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                composer4.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                            }
                                            Modifier.Companion companion = Modifier.INSTANCE;
                                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                            Function2<Composer, Integer, Unit> function115 = function112;
                                            Function2<Composer, Integer, Unit> function20 = function113;
                                            Function2<Composer, Integer, Unit> function21 = function114;
                                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                            Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                            if (composer4.getApplier() == null) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer4.startReusableNode();
                                            if (composer4.getInserting()) {
                                                composer4.createNode(constructor);
                                            } else {
                                                composer4.useNode();
                                            }
                                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                            if (function115 == null) {
                                                composer4.startReplaceGroup(675833080);
                                            } else {
                                                composer4.startReplaceGroup(2100011049);
                                                function115.invoke(composer4, 0);
                                            }
                                            composer4.endReplaceGroup();
                                            SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                            if (function20 == null) {
                                                composer4.startReplaceGroup(675935256);
                                            } else {
                                                composer4.startReplaceGroup(2100014345);
                                                function20.invoke(composer4, 0);
                                            }
                                            composer4.endReplaceGroup();
                                            function21.invoke(composer4, 0);
                                            composer4.endNode();
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composer3, 54), function6, composer3, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function10 = function8;
                            j2 = j5;
                            function11 = function18;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function8;
                            function11 = function9;
                            j2 = containerColor;
                        }
                        modifier3 = modifier2;
                        shape3 = shape2;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function111 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function112 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function113 = function8;
                        long j6 = containerColor;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                }
                                Function2<Composer, Integer, Unit> function114 = function112;
                                final Function2<? super Composer, ? super Integer, Unit> function115 = function113;
                                final Function2<? super Composer, ? super Integer, Unit> function116 = function111;
                                final Function2<? super Composer, ? super Integer, Unit> function117 = function2;
                                TimePickerDialogKt.TimePickerCustomLayout(function114, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                    public final void invoke(Composer composer4, int i17) {
                                        if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                        Function2<Composer, Integer, Unit> function118 = function115;
                                        Function2<Composer, Integer, Unit> function20 = function116;
                                        Function2<Composer, Integer, Unit> function21 = function117;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                        if (composer4.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        if (function118 == null) {
                                            composer4.startReplaceGroup(675833080);
                                        } else {
                                            composer4.startReplaceGroup(2100011049);
                                            function118.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                        if (function20 == null) {
                                            composer4.startReplaceGroup(675935256);
                                        } else {
                                            composer4.startReplaceGroup(2100014345);
                                            function20.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        function21.invoke(composer4, 0);
                                        composer4.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), function6, composer3, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function10 = function8;
                        j2 = j6;
                        function11 = function111;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function8;
                        function11 = function9;
                        j2 = containerColor;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 3072;
                function8 = function4;
                i8 = i2 & 16;
                if (i8 != 0) {
                    if ((i & 24576) == 0) {
                        function9 = function5;
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i3 |= i9;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            shape2 = shape;
                            if (composerStartRestartGroup.changed(shape2)) {
                            }
                            i3 |= i14;
                        } else {
                            shape2 = shape;
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            containerColor = j;
                            if (composerStartRestartGroup.changed(containerColor)) {
                            }
                            i3 |= i15;
                        } else {
                            containerColor = j;
                        }
                        i3 |= i15;
                    } else {
                        containerColor = j;
                    }
                    if ((i2 & 128) != 0) {
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i10 = 8388608;
                            } else {
                                i10 = 4194304;
                            }
                            i3 |= i10;
                        }
                        i11 = i3;
                        if ((i3 & 4793491) != 4793490) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    function8 = null;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 = i11 & (-458753);
                                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 64) != 0) {
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i13 = i12 & (-3670017);
                                } else {
                                    i13 = i12;
                                }
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    function8 = null;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 = i11 & (-458753);
                                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 64) != 0) {
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i13 = i12 & (-3670017);
                                } else {
                                    i13 = i12;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function114 = function9;
                            final Function2<? super Composer, ? super Integer, Unit> function115 = function7;
                            final Function2<? super Composer, ? super Integer, Unit> function116 = function8;
                            long j7 = containerColor;
                            composer2 = composerStartRestartGroup;
                            SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                    }
                                    Function2<Composer, Integer, Unit> function117 = function115;
                                    final Function2<? super Composer, ? super Integer, Unit> function118 = function116;
                                    final Function2<? super Composer, ? super Integer, Unit> function119 = function114;
                                    final Function2<? super Composer, ? super Integer, Unit> function1110 = function2;
                                    TimePickerDialogKt.TimePickerCustomLayout(function117, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                        public final void invoke(Composer composer4, int i17) {
                                            if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                composer4.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                            }
                                            Modifier.Companion companion = Modifier.INSTANCE;
                                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                            Function2<Composer, Integer, Unit> function1111 = function118;
                                            Function2<Composer, Integer, Unit> function20 = function119;
                                            Function2<Composer, Integer, Unit> function21 = function1110;
                                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                            Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                            if (composer4.getApplier() == null) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer4.startReusableNode();
                                            if (composer4.getInserting()) {
                                                composer4.createNode(constructor);
                                            } else {
                                                composer4.useNode();
                                            }
                                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                            if (function1111 == null) {
                                                composer4.startReplaceGroup(675833080);
                                            } else {
                                                composer4.startReplaceGroup(2100011049);
                                                function1111.invoke(composer4, 0);
                                            }
                                            composer4.endReplaceGroup();
                                            SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                            if (function20 == null) {
                                                composer4.startReplaceGroup(675935256);
                                            } else {
                                                composer4.startReplaceGroup(2100014345);
                                                function20.invoke(composer4, 0);
                                            }
                                            composer4.endReplaceGroup();
                                            function21.invoke(composer4, 0);
                                            composer4.endNode();
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composer3, 54), function6, composer3, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function10 = function8;
                            j2 = j7;
                            function11 = function114;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function8;
                            function11 = function9;
                            j2 = containerColor;
                        }
                        modifier3 = modifier2;
                        shape3 = shape2;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function117 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function118 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function119 = function8;
                        long j8 = containerColor;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                }
                                Function2<Composer, Integer, Unit> function1110 = function118;
                                final Function2<? super Composer, ? super Integer, Unit> function1111 = function119;
                                final Function2<? super Composer, ? super Integer, Unit> function1112 = function117;
                                final Function2<? super Composer, ? super Integer, Unit> function1113 = function2;
                                TimePickerDialogKt.TimePickerCustomLayout(function1110, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                    public final void invoke(Composer composer4, int i17) {
                                        if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                        Function2<Composer, Integer, Unit> function1114 = function1111;
                                        Function2<Composer, Integer, Unit> function20 = function1112;
                                        Function2<Composer, Integer, Unit> function21 = function1113;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                        if (composer4.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        if (function1114 == null) {
                                            composer4.startReplaceGroup(675833080);
                                        } else {
                                            composer4.startReplaceGroup(2100011049);
                                            function1114.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                        if (function20 == null) {
                                            composer4.startReplaceGroup(675935256);
                                        } else {
                                            composer4.startReplaceGroup(2100014345);
                                            function20.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        function21.invoke(composer4, 0);
                                        composer4.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), function6, composer3, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function10 = function8;
                        j2 = j8;
                        function11 = function117;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function8;
                        function11 = function9;
                        j2 = containerColor;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                function9 = function5;
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        containerColor = j;
                        if (composerStartRestartGroup.changed(containerColor)) {
                        }
                        i3 |= i15;
                    } else {
                        containerColor = j;
                    }
                    i3 |= i15;
                } else {
                    containerColor = j;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i10 = 8388608;
                        } else {
                            i10 = 4194304;
                        }
                        i3 |= i10;
                    }
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1110 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function1111 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function1112 = function8;
                        long j9 = containerColor;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                }
                                Function2<Composer, Integer, Unit> function1113 = function1111;
                                final Function2<? super Composer, ? super Integer, Unit> function1114 = function1112;
                                final Function2<? super Composer, ? super Integer, Unit> function1115 = function1110;
                                final Function2<? super Composer, ? super Integer, Unit> function1116 = function2;
                                TimePickerDialogKt.TimePickerCustomLayout(function1113, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                    public final void invoke(Composer composer4, int i17) {
                                        if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                        Function2<Composer, Integer, Unit> function1117 = function1114;
                                        Function2<Composer, Integer, Unit> function20 = function1115;
                                        Function2<Composer, Integer, Unit> function21 = function1116;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                        if (composer4.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        if (function1117 == null) {
                                            composer4.startReplaceGroup(675833080);
                                        } else {
                                            composer4.startReplaceGroup(2100011049);
                                            function1117.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                        if (function20 == null) {
                                            composer4.startReplaceGroup(675935256);
                                        } else {
                                            composer4.startReplaceGroup(2100014345);
                                            function20.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        function21.invoke(composer4, 0);
                                        composer4.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), function6, composer3, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function10 = function8;
                        j2 = j9;
                        function11 = function1110;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function8;
                        function11 = function9;
                        j2 = containerColor;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1113 = function9;
                    final Function2<? super Composer, ? super Integer, Unit> function1114 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function1115 = function8;
                    long j10 = containerColor;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                            }
                            Function2<Composer, Integer, Unit> function1116 = function1114;
                            final Function2<? super Composer, ? super Integer, Unit> function1117 = function1115;
                            final Function2<? super Composer, ? super Integer, Unit> function1118 = function1113;
                            final Function2<? super Composer, ? super Integer, Unit> function1119 = function2;
                            TimePickerDialogKt.TimePickerCustomLayout(function1116, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                public final void invoke(Composer composer4, int i17) {
                                    if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                    Function2<Composer, Integer, Unit> function11110 = function1117;
                                    Function2<Composer, Integer, Unit> function20 = function1118;
                                    Function2<Composer, Integer, Unit> function21 = function1119;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                    if (composer4.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    if (function11110 == null) {
                                        composer4.startReplaceGroup(675833080);
                                    } else {
                                        composer4.startReplaceGroup(2100011049);
                                        function11110.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                    if (function20 == null) {
                                        composer4.startReplaceGroup(675935256);
                                    } else {
                                        composer4.startReplaceGroup(2100014345);
                                        function20.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    function21.invoke(composer4, 0);
                                    composer4.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), function6, composer3, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function10 = function8;
                    j2 = j10;
                    function11 = function1113;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function8;
                    function11 = function9;
                    j2 = containerColor;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            modifier2 = modifier;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    function8 = function4;
                    if (composerStartRestartGroup.changedInstance(function8)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 16;
                if (i8 != 0) {
                    if ((i & 24576) == 0) {
                        function9 = function5;
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i3 |= i9;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            shape2 = shape;
                            if (composerStartRestartGroup.changed(shape2)) {
                            }
                            i3 |= i14;
                        } else {
                            shape2 = shape;
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            containerColor = j;
                            if (composerStartRestartGroup.changed(containerColor)) {
                            }
                            i3 |= i15;
                        } else {
                            containerColor = j;
                        }
                        i3 |= i15;
                    } else {
                        containerColor = j;
                    }
                    if ((i2 & 128) != 0) {
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i10 = 8388608;
                            } else {
                                i10 = 4194304;
                            }
                            i3 |= i10;
                        }
                        i11 = i3;
                        if ((i3 & 4793491) != 4793490) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    function8 = null;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 = i11 & (-458753);
                                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 64) != 0) {
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i13 = i12 & (-3670017);
                                } else {
                                    i13 = i12;
                                }
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    function8 = null;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 = i11 & (-458753);
                                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 64) != 0) {
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i13 = i12 & (-3670017);
                                } else {
                                    i13 = i12;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function1116 = function9;
                            final Function2<? super Composer, ? super Integer, Unit> function1117 = function7;
                            final Function2<? super Composer, ? super Integer, Unit> function1118 = function8;
                            long j11 = containerColor;
                            composer2 = composerStartRestartGroup;
                            SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                    }
                                    Function2<Composer, Integer, Unit> function1119 = function1117;
                                    final Function2<? super Composer, ? super Integer, Unit> function11110 = function1118;
                                    final Function2<? super Composer, ? super Integer, Unit> function11111 = function1116;
                                    final Function2<? super Composer, ? super Integer, Unit> function11112 = function2;
                                    TimePickerDialogKt.TimePickerCustomLayout(function1119, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                        public final void invoke(Composer composer4, int i17) {
                                            if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                composer4.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                            }
                                            Modifier.Companion companion = Modifier.INSTANCE;
                                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                            Function2<Composer, Integer, Unit> function11113 = function11110;
                                            Function2<Composer, Integer, Unit> function20 = function11111;
                                            Function2<Composer, Integer, Unit> function21 = function11112;
                                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                            Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                            if (composer4.getApplier() == null) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer4.startReusableNode();
                                            if (composer4.getInserting()) {
                                                composer4.createNode(constructor);
                                            } else {
                                                composer4.useNode();
                                            }
                                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                            if (function11113 == null) {
                                                composer4.startReplaceGroup(675833080);
                                            } else {
                                                composer4.startReplaceGroup(2100011049);
                                                function11113.invoke(composer4, 0);
                                            }
                                            composer4.endReplaceGroup();
                                            SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                            if (function20 == null) {
                                                composer4.startReplaceGroup(675935256);
                                            } else {
                                                composer4.startReplaceGroup(2100014345);
                                                function20.invoke(composer4, 0);
                                            }
                                            composer4.endReplaceGroup();
                                            function21.invoke(composer4, 0);
                                            composer4.endNode();
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composer3, 54), function6, composer3, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function10 = function8;
                            j2 = j11;
                            function11 = function1116;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function8;
                            function11 = function9;
                            j2 = containerColor;
                        }
                        modifier3 = modifier2;
                        shape3 = shape2;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1119 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function11110 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function11111 = function8;
                        long j12 = containerColor;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                }
                                Function2<Composer, Integer, Unit> function11112 = function11110;
                                final Function2<? super Composer, ? super Integer, Unit> function11113 = function11111;
                                final Function2<? super Composer, ? super Integer, Unit> function11114 = function1119;
                                final Function2<? super Composer, ? super Integer, Unit> function11115 = function2;
                                TimePickerDialogKt.TimePickerCustomLayout(function11112, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                    public final void invoke(Composer composer4, int i17) {
                                        if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                        Function2<Composer, Integer, Unit> function11116 = function11113;
                                        Function2<Composer, Integer, Unit> function20 = function11114;
                                        Function2<Composer, Integer, Unit> function21 = function11115;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                        if (composer4.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        if (function11116 == null) {
                                            composer4.startReplaceGroup(675833080);
                                        } else {
                                            composer4.startReplaceGroup(2100011049);
                                            function11116.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                        if (function20 == null) {
                                            composer4.startReplaceGroup(675935256);
                                        } else {
                                            composer4.startReplaceGroup(2100014345);
                                            function20.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        function21.invoke(composer4, 0);
                                        composer4.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), function6, composer3, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function10 = function8;
                        j2 = j12;
                        function11 = function1119;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function8;
                        function11 = function9;
                        j2 = containerColor;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                function9 = function5;
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        containerColor = j;
                        if (composerStartRestartGroup.changed(containerColor)) {
                        }
                        i3 |= i15;
                    } else {
                        containerColor = j;
                    }
                    i3 |= i15;
                } else {
                    containerColor = j;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i10 = 8388608;
                        } else {
                            i10 = 4194304;
                        }
                        i3 |= i10;
                    }
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function11112 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function11113 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function11114 = function8;
                        long j13 = containerColor;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                }
                                Function2<Composer, Integer, Unit> function11115 = function11113;
                                final Function2<? super Composer, ? super Integer, Unit> function11116 = function11114;
                                final Function2<? super Composer, ? super Integer, Unit> function11117 = function11112;
                                final Function2<? super Composer, ? super Integer, Unit> function11118 = function2;
                                TimePickerDialogKt.TimePickerCustomLayout(function11115, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                    public final void invoke(Composer composer4, int i17) {
                                        if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                        Function2<Composer, Integer, Unit> function11119 = function11116;
                                        Function2<Composer, Integer, Unit> function20 = function11117;
                                        Function2<Composer, Integer, Unit> function21 = function11118;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                        if (composer4.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        if (function11119 == null) {
                                            composer4.startReplaceGroup(675833080);
                                        } else {
                                            composer4.startReplaceGroup(2100011049);
                                            function11119.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                        if (function20 == null) {
                                            composer4.startReplaceGroup(675935256);
                                        } else {
                                            composer4.startReplaceGroup(2100014345);
                                            function20.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        function21.invoke(composer4, 0);
                                        composer4.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), function6, composer3, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function10 = function8;
                        j2 = j13;
                        function11 = function11112;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function8;
                        function11 = function9;
                        j2 = containerColor;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function11115 = function9;
                    final Function2<? super Composer, ? super Integer, Unit> function11116 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function11117 = function8;
                    long j14 = containerColor;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                            }
                            Function2<Composer, Integer, Unit> function11118 = function11116;
                            final Function2<? super Composer, ? super Integer, Unit> function11119 = function11117;
                            final Function2<? super Composer, ? super Integer, Unit> function111110 = function11115;
                            final Function2<? super Composer, ? super Integer, Unit> function111111 = function2;
                            TimePickerDialogKt.TimePickerCustomLayout(function11118, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                public final void invoke(Composer composer4, int i17) {
                                    if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                    Function2<Composer, Integer, Unit> function111112 = function11119;
                                    Function2<Composer, Integer, Unit> function20 = function111110;
                                    Function2<Composer, Integer, Unit> function21 = function111111;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                    if (composer4.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    if (function111112 == null) {
                                        composer4.startReplaceGroup(675833080);
                                    } else {
                                        composer4.startReplaceGroup(2100011049);
                                        function111112.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                    if (function20 == null) {
                                        composer4.startReplaceGroup(675935256);
                                    } else {
                                        composer4.startReplaceGroup(2100014345);
                                        function20.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    function21.invoke(composer4, 0);
                                    composer4.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), function6, composer3, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function10 = function8;
                    j2 = j14;
                    function11 = function11115;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function8;
                    function11 = function9;
                    j2 = containerColor;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            function8 = function4;
            i8 = i2 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    function9 = function5;
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        containerColor = j;
                        if (composerStartRestartGroup.changed(containerColor)) {
                        }
                        i3 |= i15;
                    } else {
                        containerColor = j;
                    }
                    i3 |= i15;
                } else {
                    containerColor = j;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i10 = 8388608;
                        } else {
                            i10 = 4194304;
                        }
                        i3 |= i10;
                    }
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function11118 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function11119 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function111110 = function8;
                        long j15 = containerColor;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                }
                                Function2<Composer, Integer, Unit> function111111 = function11119;
                                final Function2<? super Composer, ? super Integer, Unit> function111112 = function111110;
                                final Function2<? super Composer, ? super Integer, Unit> function111113 = function11118;
                                final Function2<? super Composer, ? super Integer, Unit> function111114 = function2;
                                TimePickerDialogKt.TimePickerCustomLayout(function111111, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                    public final void invoke(Composer composer4, int i17) {
                                        if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                        Function2<Composer, Integer, Unit> function111115 = function111112;
                                        Function2<Composer, Integer, Unit> function20 = function111113;
                                        Function2<Composer, Integer, Unit> function21 = function111114;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                        if (composer4.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        if (function111115 == null) {
                                            composer4.startReplaceGroup(675833080);
                                        } else {
                                            composer4.startReplaceGroup(2100011049);
                                            function111115.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                        if (function20 == null) {
                                            composer4.startReplaceGroup(675935256);
                                        } else {
                                            composer4.startReplaceGroup(2100014345);
                                            function20.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        function21.invoke(composer4, 0);
                                        composer4.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), function6, composer3, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function10 = function8;
                        j2 = j15;
                        function11 = function11118;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function8;
                        function11 = function9;
                        j2 = containerColor;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function111111 = function9;
                    final Function2<? super Composer, ? super Integer, Unit> function111112 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function111113 = function8;
                    long j16 = containerColor;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                            }
                            Function2<Composer, Integer, Unit> function111114 = function111112;
                            final Function2<? super Composer, ? super Integer, Unit> function111115 = function111113;
                            final Function2<? super Composer, ? super Integer, Unit> function111116 = function111111;
                            final Function2<? super Composer, ? super Integer, Unit> function111117 = function2;
                            TimePickerDialogKt.TimePickerCustomLayout(function111114, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                public final void invoke(Composer composer4, int i17) {
                                    if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                    Function2<Composer, Integer, Unit> function111118 = function111115;
                                    Function2<Composer, Integer, Unit> function20 = function111116;
                                    Function2<Composer, Integer, Unit> function21 = function111117;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                    if (composer4.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    if (function111118 == null) {
                                        composer4.startReplaceGroup(675833080);
                                    } else {
                                        composer4.startReplaceGroup(2100011049);
                                        function111118.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                    if (function20 == null) {
                                        composer4.startReplaceGroup(675935256);
                                    } else {
                                        composer4.startReplaceGroup(2100014345);
                                        function20.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    function21.invoke(composer4, 0);
                                    composer4.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), function6, composer3, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function10 = function8;
                    j2 = j16;
                    function11 = function111111;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function8;
                    function11 = function9;
                    j2 = containerColor;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function9 = function5;
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                i3 |= i14;
            } else {
                shape2 = shape;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    containerColor = j;
                    if (composerStartRestartGroup.changed(containerColor)) {
                    }
                    i3 |= i15;
                } else {
                    containerColor = j;
                }
                i3 |= i15;
            } else {
                containerColor = j;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i10 = 8388608;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function111114 = function9;
                    final Function2<? super Composer, ? super Integer, Unit> function111115 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function111116 = function8;
                    long j17 = containerColor;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                            }
                            Function2<Composer, Integer, Unit> function111117 = function111115;
                            final Function2<? super Composer, ? super Integer, Unit> function111118 = function111116;
                            final Function2<? super Composer, ? super Integer, Unit> function111119 = function111114;
                            final Function2<? super Composer, ? super Integer, Unit> function1111110 = function2;
                            TimePickerDialogKt.TimePickerCustomLayout(function111117, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                public final void invoke(Composer composer4, int i17) {
                                    if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                    Function2<Composer, Integer, Unit> function1111111 = function111118;
                                    Function2<Composer, Integer, Unit> function20 = function111119;
                                    Function2<Composer, Integer, Unit> function21 = function1111110;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                    if (composer4.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    if (function1111111 == null) {
                                        composer4.startReplaceGroup(675833080);
                                    } else {
                                        composer4.startReplaceGroup(2100011049);
                                        function1111111.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                    if (function20 == null) {
                                        composer4.startReplaceGroup(675935256);
                                    } else {
                                        composer4.startReplaceGroup(2100014345);
                                        function20.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    function21.invoke(composer4, 0);
                                    composer4.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), function6, composer3, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function10 = function8;
                    j2 = j17;
                    function11 = function111114;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function8;
                    function11 = function9;
                    j2 = containerColor;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            i11 = i3;
            if ((i3 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        function8 = null;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if ((i2 & 32) != 0) {
                        i12 = i11 & (-458753);
                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i13 = i12 & (-3670017);
                    } else {
                        i13 = i12;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        function8 = null;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if ((i2 & 32) != 0) {
                        i12 = i11 & (-458753);
                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i13 = i12 & (-3670017);
                    } else {
                        i13 = i12;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function111117 = function9;
                final Function2<? super Composer, ? super Integer, Unit> function111118 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function111119 = function8;
                long j18 = containerColor;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                        }
                        Function2<Composer, Integer, Unit> function1111110 = function111118;
                        final Function2<? super Composer, ? super Integer, Unit> function1111111 = function111119;
                        final Function2<? super Composer, ? super Integer, Unit> function1111112 = function111117;
                        final Function2<? super Composer, ? super Integer, Unit> function1111113 = function2;
                        TimePickerDialogKt.TimePickerCustomLayout(function1111110, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                            public final void invoke(Composer composer4, int i17) {
                                if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                }
                                Modifier.Companion companion = Modifier.INSTANCE;
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                Function2<Composer, Integer, Unit> function1111114 = function1111111;
                                Function2<Composer, Integer, Unit> function20 = function1111112;
                                Function2<Composer, Integer, Unit> function21 = function1111113;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                if (composer4.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer4.startReusableNode();
                                if (composer4.getInserting()) {
                                    composer4.createNode(constructor);
                                } else {
                                    composer4.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                if (function1111114 == null) {
                                    composer4.startReplaceGroup(675833080);
                                } else {
                                    composer4.startReplaceGroup(2100011049);
                                    function1111114.invoke(composer4, 0);
                                }
                                composer4.endReplaceGroup();
                                SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                if (function20 == null) {
                                    composer4.startReplaceGroup(675935256);
                                } else {
                                    composer4.startReplaceGroup(2100014345);
                                    function20.invoke(composer4, 0);
                                }
                                composer4.endReplaceGroup();
                                function21.invoke(composer4, 0);
                                composer4.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer3, 54), function6, composer3, 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function10 = function8;
                j2 = j18;
                function11 = function111117;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function8;
                function11 = function9;
                j2 = containerColor;
            }
            modifier3 = modifier2;
            shape3 = shape2;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        function7 = function3;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    function8 = function4;
                    if (composerStartRestartGroup.changedInstance(function8)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 16;
                if (i8 != 0) {
                    if ((i & 24576) == 0) {
                        function9 = function5;
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i3 |= i9;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            shape2 = shape;
                            if (composerStartRestartGroup.changed(shape2)) {
                            }
                            i3 |= i14;
                        } else {
                            shape2 = shape;
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            containerColor = j;
                            if (composerStartRestartGroup.changed(containerColor)) {
                            }
                            i3 |= i15;
                        } else {
                            containerColor = j;
                        }
                        i3 |= i15;
                    } else {
                        containerColor = j;
                    }
                    if ((i2 & 128) != 0) {
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i10 = 8388608;
                            } else {
                                i10 = 4194304;
                            }
                            i3 |= i10;
                        }
                        i11 = i3;
                        if ((i3 & 4793491) != 4793490) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    function8 = null;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 = i11 & (-458753);
                                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 64) != 0) {
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i13 = i12 & (-3670017);
                                } else {
                                    i13 = i12;
                                }
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    function8 = null;
                                }
                                if (i8 != 0) {
                                    function9 = null;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 = i11 & (-458753);
                                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 64) != 0) {
                                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i13 = i12 & (-3670017);
                                } else {
                                    i13 = i12;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function1111110 = function9;
                            final Function2<? super Composer, ? super Integer, Unit> function1111111 = function7;
                            final Function2<? super Composer, ? super Integer, Unit> function1111112 = function8;
                            long j19 = containerColor;
                            composer2 = composerStartRestartGroup;
                            SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                    }
                                    Function2<Composer, Integer, Unit> function1111113 = function1111111;
                                    final Function2<? super Composer, ? super Integer, Unit> function1111114 = function1111112;
                                    final Function2<? super Composer, ? super Integer, Unit> function1111115 = function1111110;
                                    final Function2<? super Composer, ? super Integer, Unit> function1111116 = function2;
                                    TimePickerDialogKt.TimePickerCustomLayout(function1111113, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                        public final void invoke(Composer composer4, int i17) {
                                            if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                composer4.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                            }
                                            Modifier.Companion companion = Modifier.INSTANCE;
                                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                            Function2<Composer, Integer, Unit> function1111117 = function1111114;
                                            Function2<Composer, Integer, Unit> function20 = function1111115;
                                            Function2<Composer, Integer, Unit> function21 = function1111116;
                                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                            Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                            if (composer4.getApplier() == null) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer4.startReusableNode();
                                            if (composer4.getInserting()) {
                                                composer4.createNode(constructor);
                                            } else {
                                                composer4.useNode();
                                            }
                                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                            if (function1111117 == null) {
                                                composer4.startReplaceGroup(675833080);
                                            } else {
                                                composer4.startReplaceGroup(2100011049);
                                                function1111117.invoke(composer4, 0);
                                            }
                                            composer4.endReplaceGroup();
                                            SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                            if (function20 == null) {
                                                composer4.startReplaceGroup(675935256);
                                            } else {
                                                composer4.startReplaceGroup(2100014345);
                                                function20.invoke(composer4, 0);
                                            }
                                            composer4.endReplaceGroup();
                                            function21.invoke(composer4, 0);
                                            composer4.endNode();
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composer3, 54), function6, composer3, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function10 = function8;
                            j2 = j19;
                            function11 = function1111110;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function8;
                            function11 = function9;
                            j2 = containerColor;
                        }
                        modifier3 = modifier2;
                        shape3 = shape2;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1111113 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function1111114 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function1111115 = function8;
                        long j110 = containerColor;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                }
                                Function2<Composer, Integer, Unit> function1111116 = function1111114;
                                final Function2<? super Composer, ? super Integer, Unit> function1111117 = function1111115;
                                final Function2<? super Composer, ? super Integer, Unit> function1111118 = function1111113;
                                final Function2<? super Composer, ? super Integer, Unit> function1111119 = function2;
                                TimePickerDialogKt.TimePickerCustomLayout(function1111116, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                    public final void invoke(Composer composer4, int i17) {
                                        if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                        Function2<Composer, Integer, Unit> function11111110 = function1111117;
                                        Function2<Composer, Integer, Unit> function20 = function1111118;
                                        Function2<Composer, Integer, Unit> function21 = function1111119;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                        if (composer4.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        if (function11111110 == null) {
                                            composer4.startReplaceGroup(675833080);
                                        } else {
                                            composer4.startReplaceGroup(2100011049);
                                            function11111110.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                        if (function20 == null) {
                                            composer4.startReplaceGroup(675935256);
                                        } else {
                                            composer4.startReplaceGroup(2100014345);
                                            function20.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        function21.invoke(composer4, 0);
                                        composer4.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), function6, composer3, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function10 = function8;
                        j2 = j110;
                        function11 = function1111113;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function8;
                        function11 = function9;
                        j2 = containerColor;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                function9 = function5;
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        containerColor = j;
                        if (composerStartRestartGroup.changed(containerColor)) {
                        }
                        i3 |= i15;
                    } else {
                        containerColor = j;
                    }
                    i3 |= i15;
                } else {
                    containerColor = j;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i10 = 8388608;
                        } else {
                            i10 = 4194304;
                        }
                        i3 |= i10;
                    }
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1111116 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function1111117 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function1111118 = function8;
                        long j111 = containerColor;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                }
                                Function2<Composer, Integer, Unit> function1111119 = function1111117;
                                final Function2<? super Composer, ? super Integer, Unit> function11111110 = function1111118;
                                final Function2<? super Composer, ? super Integer, Unit> function11111111 = function1111116;
                                final Function2<? super Composer, ? super Integer, Unit> function11111112 = function2;
                                TimePickerDialogKt.TimePickerCustomLayout(function1111119, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                    public final void invoke(Composer composer4, int i17) {
                                        if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                        Function2<Composer, Integer, Unit> function11111113 = function11111110;
                                        Function2<Composer, Integer, Unit> function20 = function11111111;
                                        Function2<Composer, Integer, Unit> function21 = function11111112;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                        if (composer4.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        if (function11111113 == null) {
                                            composer4.startReplaceGroup(675833080);
                                        } else {
                                            composer4.startReplaceGroup(2100011049);
                                            function11111113.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                        if (function20 == null) {
                                            composer4.startReplaceGroup(675935256);
                                        } else {
                                            composer4.startReplaceGroup(2100014345);
                                            function20.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        function21.invoke(composer4, 0);
                                        composer4.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), function6, composer3, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function10 = function8;
                        j2 = j111;
                        function11 = function1111116;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function8;
                        function11 = function9;
                        j2 = containerColor;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1111119 = function9;
                    final Function2<? super Composer, ? super Integer, Unit> function11111110 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function11111111 = function8;
                    long j112 = containerColor;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                            }
                            Function2<Composer, Integer, Unit> function11111112 = function11111110;
                            final Function2<? super Composer, ? super Integer, Unit> function11111113 = function11111111;
                            final Function2<? super Composer, ? super Integer, Unit> function11111114 = function1111119;
                            final Function2<? super Composer, ? super Integer, Unit> function11111115 = function2;
                            TimePickerDialogKt.TimePickerCustomLayout(function11111112, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                public final void invoke(Composer composer4, int i17) {
                                    if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                    Function2<Composer, Integer, Unit> function11111116 = function11111113;
                                    Function2<Composer, Integer, Unit> function20 = function11111114;
                                    Function2<Composer, Integer, Unit> function21 = function11111115;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                    if (composer4.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    if (function11111116 == null) {
                                        composer4.startReplaceGroup(675833080);
                                    } else {
                                        composer4.startReplaceGroup(2100011049);
                                        function11111116.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                    if (function20 == null) {
                                        composer4.startReplaceGroup(675935256);
                                    } else {
                                        composer4.startReplaceGroup(2100014345);
                                        function20.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    function21.invoke(composer4, 0);
                                    composer4.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), function6, composer3, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function10 = function8;
                    j2 = j112;
                    function11 = function1111119;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function8;
                    function11 = function9;
                    j2 = containerColor;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            function8 = function4;
            i8 = i2 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    function9 = function5;
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        containerColor = j;
                        if (composerStartRestartGroup.changed(containerColor)) {
                        }
                        i3 |= i15;
                    } else {
                        containerColor = j;
                    }
                    i3 |= i15;
                } else {
                    containerColor = j;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i10 = 8388608;
                        } else {
                            i10 = 4194304;
                        }
                        i3 |= i10;
                    }
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function11111112 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function11111113 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function11111114 = function8;
                        long j113 = containerColor;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                }
                                Function2<Composer, Integer, Unit> function11111115 = function11111113;
                                final Function2<? super Composer, ? super Integer, Unit> function11111116 = function11111114;
                                final Function2<? super Composer, ? super Integer, Unit> function11111117 = function11111112;
                                final Function2<? super Composer, ? super Integer, Unit> function11111118 = function2;
                                TimePickerDialogKt.TimePickerCustomLayout(function11111115, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                    public final void invoke(Composer composer4, int i17) {
                                        if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                        Function2<Composer, Integer, Unit> function11111119 = function11111116;
                                        Function2<Composer, Integer, Unit> function20 = function11111117;
                                        Function2<Composer, Integer, Unit> function21 = function11111118;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                        if (composer4.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        if (function11111119 == null) {
                                            composer4.startReplaceGroup(675833080);
                                        } else {
                                            composer4.startReplaceGroup(2100011049);
                                            function11111119.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                        if (function20 == null) {
                                            composer4.startReplaceGroup(675935256);
                                        } else {
                                            composer4.startReplaceGroup(2100014345);
                                            function20.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        function21.invoke(composer4, 0);
                                        composer4.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), function6, composer3, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function10 = function8;
                        j2 = j113;
                        function11 = function11111112;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function8;
                        function11 = function9;
                        j2 = containerColor;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function11111115 = function9;
                    final Function2<? super Composer, ? super Integer, Unit> function11111116 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function11111117 = function8;
                    long j114 = containerColor;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                            }
                            Function2<Composer, Integer, Unit> function11111118 = function11111116;
                            final Function2<? super Composer, ? super Integer, Unit> function11111119 = function11111117;
                            final Function2<? super Composer, ? super Integer, Unit> function111111110 = function11111115;
                            final Function2<? super Composer, ? super Integer, Unit> function111111111 = function2;
                            TimePickerDialogKt.TimePickerCustomLayout(function11111118, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                public final void invoke(Composer composer4, int i17) {
                                    if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                    Function2<Composer, Integer, Unit> function111111112 = function11111119;
                                    Function2<Composer, Integer, Unit> function20 = function111111110;
                                    Function2<Composer, Integer, Unit> function21 = function111111111;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                    if (composer4.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    if (function111111112 == null) {
                                        composer4.startReplaceGroup(675833080);
                                    } else {
                                        composer4.startReplaceGroup(2100011049);
                                        function111111112.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                    if (function20 == null) {
                                        composer4.startReplaceGroup(675935256);
                                    } else {
                                        composer4.startReplaceGroup(2100014345);
                                        function20.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    function21.invoke(composer4, 0);
                                    composer4.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), function6, composer3, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function10 = function8;
                    j2 = j114;
                    function11 = function11111115;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function8;
                    function11 = function9;
                    j2 = containerColor;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function9 = function5;
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                i3 |= i14;
            } else {
                shape2 = shape;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    containerColor = j;
                    if (composerStartRestartGroup.changed(containerColor)) {
                    }
                    i3 |= i15;
                } else {
                    containerColor = j;
                }
                i3 |= i15;
            } else {
                containerColor = j;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i10 = 8388608;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function11111118 = function9;
                    final Function2<? super Composer, ? super Integer, Unit> function11111119 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function111111110 = function8;
                    long j115 = containerColor;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                            }
                            Function2<Composer, Integer, Unit> function111111111 = function11111119;
                            final Function2<? super Composer, ? super Integer, Unit> function111111112 = function111111110;
                            final Function2<? super Composer, ? super Integer, Unit> function111111113 = function11111118;
                            final Function2<? super Composer, ? super Integer, Unit> function111111114 = function2;
                            TimePickerDialogKt.TimePickerCustomLayout(function111111111, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                public final void invoke(Composer composer4, int i17) {
                                    if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                    Function2<Composer, Integer, Unit> function111111115 = function111111112;
                                    Function2<Composer, Integer, Unit> function20 = function111111113;
                                    Function2<Composer, Integer, Unit> function21 = function111111114;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                    if (composer4.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    if (function111111115 == null) {
                                        composer4.startReplaceGroup(675833080);
                                    } else {
                                        composer4.startReplaceGroup(2100011049);
                                        function111111115.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                    if (function20 == null) {
                                        composer4.startReplaceGroup(675935256);
                                    } else {
                                        composer4.startReplaceGroup(2100014345);
                                        function20.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    function21.invoke(composer4, 0);
                                    composer4.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), function6, composer3, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function10 = function8;
                    j2 = j115;
                    function11 = function11111118;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function8;
                    function11 = function9;
                    j2 = containerColor;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            i11 = i3;
            if ((i3 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        function8 = null;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if ((i2 & 32) != 0) {
                        i12 = i11 & (-458753);
                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i13 = i12 & (-3670017);
                    } else {
                        i13 = i12;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        function8 = null;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if ((i2 & 32) != 0) {
                        i12 = i11 & (-458753);
                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i13 = i12 & (-3670017);
                    } else {
                        i13 = i12;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function111111111 = function9;
                final Function2<? super Composer, ? super Integer, Unit> function111111112 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function111111113 = function8;
                long j116 = containerColor;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                        }
                        Function2<Composer, Integer, Unit> function111111114 = function111111112;
                        final Function2<? super Composer, ? super Integer, Unit> function111111115 = function111111113;
                        final Function2<? super Composer, ? super Integer, Unit> function111111116 = function111111111;
                        final Function2<? super Composer, ? super Integer, Unit> function111111117 = function2;
                        TimePickerDialogKt.TimePickerCustomLayout(function111111114, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                            public final void invoke(Composer composer4, int i17) {
                                if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                }
                                Modifier.Companion companion = Modifier.INSTANCE;
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                Function2<Composer, Integer, Unit> function111111118 = function111111115;
                                Function2<Composer, Integer, Unit> function20 = function111111116;
                                Function2<Composer, Integer, Unit> function21 = function111111117;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                if (composer4.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer4.startReusableNode();
                                if (composer4.getInserting()) {
                                    composer4.createNode(constructor);
                                } else {
                                    composer4.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                if (function111111118 == null) {
                                    composer4.startReplaceGroup(675833080);
                                } else {
                                    composer4.startReplaceGroup(2100011049);
                                    function111111118.invoke(composer4, 0);
                                }
                                composer4.endReplaceGroup();
                                SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                if (function20 == null) {
                                    composer4.startReplaceGroup(675935256);
                                } else {
                                    composer4.startReplaceGroup(2100014345);
                                    function20.invoke(composer4, 0);
                                }
                                composer4.endReplaceGroup();
                                function21.invoke(composer4, 0);
                                composer4.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer3, 54), function6, composer3, 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function10 = function8;
                j2 = j116;
                function11 = function111111111;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function8;
                function11 = function9;
                j2 = containerColor;
            }
            modifier3 = modifier2;
            shape3 = shape2;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                function8 = function4;
                if (composerStartRestartGroup.changedInstance(function8)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            i8 = i2 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    function9 = function5;
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        containerColor = j;
                        if (composerStartRestartGroup.changed(containerColor)) {
                        }
                        i3 |= i15;
                    } else {
                        containerColor = j;
                    }
                    i3 |= i15;
                } else {
                    containerColor = j;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i10 = 8388608;
                        } else {
                            i10 = 4194304;
                        }
                        i3 |= i10;
                    }
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                function8 = null;
                            }
                            if (i8 != 0) {
                                function9 = null;
                            }
                            if ((i2 & 32) != 0) {
                                i12 = i11 & (-458753);
                                shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i13 = i12 & (-3670017);
                            } else {
                                i13 = i12;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function111111114 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function111111115 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function111111116 = function8;
                        long j117 = containerColor;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                                }
                                Function2<Composer, Integer, Unit> function111111117 = function111111115;
                                final Function2<? super Composer, ? super Integer, Unit> function111111118 = function111111116;
                                final Function2<? super Composer, ? super Integer, Unit> function111111119 = function111111114;
                                final Function2<? super Composer, ? super Integer, Unit> function1111111110 = function2;
                                TimePickerDialogKt.TimePickerCustomLayout(function111111117, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                    public final void invoke(Composer composer4, int i17) {
                                        if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                        Function2<Composer, Integer, Unit> function1111111111 = function111111118;
                                        Function2<Composer, Integer, Unit> function20 = function111111119;
                                        Function2<Composer, Integer, Unit> function21 = function1111111110;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                        if (composer4.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        if (function1111111111 == null) {
                                            composer4.startReplaceGroup(675833080);
                                        } else {
                                            composer4.startReplaceGroup(2100011049);
                                            function1111111111.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                        if (function20 == null) {
                                            composer4.startReplaceGroup(675935256);
                                        } else {
                                            composer4.startReplaceGroup(2100014345);
                                            function20.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        function21.invoke(composer4, 0);
                                        composer4.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), function6, composer3, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function10 = function8;
                        j2 = j117;
                        function11 = function111111114;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function8;
                        function11 = function9;
                        j2 = containerColor;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                            public final Object invoke(Object obj, Object obj2) {
                                return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function111111117 = function9;
                    final Function2<? super Composer, ? super Integer, Unit> function111111118 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function111111119 = function8;
                    long j118 = containerColor;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                            }
                            Function2<Composer, Integer, Unit> function1111111110 = function111111118;
                            final Function2<? super Composer, ? super Integer, Unit> function1111111111 = function111111119;
                            final Function2<? super Composer, ? super Integer, Unit> function1111111112 = function111111117;
                            final Function2<? super Composer, ? super Integer, Unit> function1111111113 = function2;
                            TimePickerDialogKt.TimePickerCustomLayout(function1111111110, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                public final void invoke(Composer composer4, int i17) {
                                    if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                    Function2<Composer, Integer, Unit> function1111111114 = function1111111111;
                                    Function2<Composer, Integer, Unit> function20 = function1111111112;
                                    Function2<Composer, Integer, Unit> function21 = function1111111113;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                    if (composer4.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    if (function1111111114 == null) {
                                        composer4.startReplaceGroup(675833080);
                                    } else {
                                        composer4.startReplaceGroup(2100011049);
                                        function1111111114.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                    if (function20 == null) {
                                        composer4.startReplaceGroup(675935256);
                                    } else {
                                        composer4.startReplaceGroup(2100014345);
                                        function20.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    function21.invoke(composer4, 0);
                                    composer4.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), function6, composer3, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function10 = function8;
                    j2 = j118;
                    function11 = function111111117;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function8;
                    function11 = function9;
                    j2 = containerColor;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function9 = function5;
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                i3 |= i14;
            } else {
                shape2 = shape;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    containerColor = j;
                    if (composerStartRestartGroup.changed(containerColor)) {
                    }
                    i3 |= i15;
                } else {
                    containerColor = j;
                }
                i3 |= i15;
            } else {
                containerColor = j;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i10 = 8388608;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1111111110 = function9;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111111 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111112 = function8;
                    long j119 = containerColor;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                            }
                            Function2<Composer, Integer, Unit> function1111111113 = function1111111111;
                            final Function2<? super Composer, ? super Integer, Unit> function1111111114 = function1111111112;
                            final Function2<? super Composer, ? super Integer, Unit> function1111111115 = function1111111110;
                            final Function2<? super Composer, ? super Integer, Unit> function1111111116 = function2;
                            TimePickerDialogKt.TimePickerCustomLayout(function1111111113, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                public final void invoke(Composer composer4, int i17) {
                                    if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                    Function2<Composer, Integer, Unit> function1111111117 = function1111111114;
                                    Function2<Composer, Integer, Unit> function20 = function1111111115;
                                    Function2<Composer, Integer, Unit> function21 = function1111111116;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                    if (composer4.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    if (function1111111117 == null) {
                                        composer4.startReplaceGroup(675833080);
                                    } else {
                                        composer4.startReplaceGroup(2100011049);
                                        function1111111117.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                    if (function20 == null) {
                                        composer4.startReplaceGroup(675935256);
                                    } else {
                                        composer4.startReplaceGroup(2100014345);
                                        function20.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    function21.invoke(composer4, 0);
                                    composer4.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), function6, composer3, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function10 = function8;
                    j2 = j119;
                    function11 = function1111111110;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function8;
                    function11 = function9;
                    j2 = containerColor;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            i11 = i3;
            if ((i3 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        function8 = null;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if ((i2 & 32) != 0) {
                        i12 = i11 & (-458753);
                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i13 = i12 & (-3670017);
                    } else {
                        i13 = i12;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        function8 = null;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if ((i2 & 32) != 0) {
                        i12 = i11 & (-458753);
                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i13 = i12 & (-3670017);
                    } else {
                        i13 = i12;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function1111111113 = function9;
                final Function2<? super Composer, ? super Integer, Unit> function1111111114 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function1111111115 = function8;
                long j1110 = containerColor;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                        }
                        Function2<Composer, Integer, Unit> function1111111116 = function1111111114;
                        final Function2<? super Composer, ? super Integer, Unit> function1111111117 = function1111111115;
                        final Function2<? super Composer, ? super Integer, Unit> function1111111118 = function1111111113;
                        final Function2<? super Composer, ? super Integer, Unit> function1111111119 = function2;
                        TimePickerDialogKt.TimePickerCustomLayout(function1111111116, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                            public final void invoke(Composer composer4, int i17) {
                                if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                }
                                Modifier.Companion companion = Modifier.INSTANCE;
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                Function2<Composer, Integer, Unit> function11111111110 = function1111111117;
                                Function2<Composer, Integer, Unit> function20 = function1111111118;
                                Function2<Composer, Integer, Unit> function21 = function1111111119;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                if (composer4.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer4.startReusableNode();
                                if (composer4.getInserting()) {
                                    composer4.createNode(constructor);
                                } else {
                                    composer4.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                if (function11111111110 == null) {
                                    composer4.startReplaceGroup(675833080);
                                } else {
                                    composer4.startReplaceGroup(2100011049);
                                    function11111111110.invoke(composer4, 0);
                                }
                                composer4.endReplaceGroup();
                                SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                if (function20 == null) {
                                    composer4.startReplaceGroup(675935256);
                                } else {
                                    composer4.startReplaceGroup(2100014345);
                                    function20.invoke(composer4, 0);
                                }
                                composer4.endReplaceGroup();
                                function21.invoke(composer4, 0);
                                composer4.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer3, 54), function6, composer3, 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function10 = function8;
                j2 = j1110;
                function11 = function1111111113;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function8;
                function11 = function9;
                j2 = containerColor;
            }
            modifier3 = modifier2;
            shape3 = shape2;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        function8 = function4;
        i8 = i2 & 16;
        if (i8 != 0) {
            if ((i & 24576) == 0) {
                function9 = function5;
                if (composerStartRestartGroup.changedInstance(function9)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i3 |= i9;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                i3 |= i14;
            } else {
                shape2 = shape;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    containerColor = j;
                    if (composerStartRestartGroup.changed(containerColor)) {
                    }
                    i3 |= i15;
                } else {
                    containerColor = j;
                }
                i3 |= i15;
            } else {
                containerColor = j;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i10 = 8388608;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            function8 = null;
                        }
                        if (i8 != 0) {
                            function9 = null;
                        }
                        if ((i2 & 32) != 0) {
                            i12 = i11 & (-458753);
                            shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i13 = i12 & (-3670017);
                        } else {
                            i13 = i12;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1111111116 = function9;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111117 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111118 = function8;
                    long j1111 = containerColor;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                            }
                            Function2<Composer, Integer, Unit> function1111111119 = function1111111117;
                            final Function2<? super Composer, ? super Integer, Unit> function11111111110 = function1111111118;
                            final Function2<? super Composer, ? super Integer, Unit> function11111111111 = function1111111116;
                            final Function2<? super Composer, ? super Integer, Unit> function11111111112 = function2;
                            TimePickerDialogKt.TimePickerCustomLayout(function1111111119, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                                public final void invoke(Composer composer4, int i17) {
                                    if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                    Function2<Composer, Integer, Unit> function11111111113 = function11111111110;
                                    Function2<Composer, Integer, Unit> function20 = function11111111111;
                                    Function2<Composer, Integer, Unit> function21 = function11111111112;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                    if (composer4.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    if (function11111111113 == null) {
                                        composer4.startReplaceGroup(675833080);
                                    } else {
                                        composer4.startReplaceGroup(2100011049);
                                        function11111111113.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                    if (function20 == null) {
                                        composer4.startReplaceGroup(675935256);
                                    } else {
                                        composer4.startReplaceGroup(2100014345);
                                        function20.invoke(composer4, 0);
                                    }
                                    composer4.endReplaceGroup();
                                    function21.invoke(composer4, 0);
                                    composer4.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), function6, composer3, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function10 = function8;
                    j2 = j1111;
                    function11 = function1111111116;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function8;
                    function11 = function9;
                    j2 = containerColor;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            i11 = i3;
            if ((i3 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        function8 = null;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if ((i2 & 32) != 0) {
                        i12 = i11 & (-458753);
                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i13 = i12 & (-3670017);
                    } else {
                        i13 = i12;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        function8 = null;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if ((i2 & 32) != 0) {
                        i12 = i11 & (-458753);
                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i13 = i12 & (-3670017);
                    } else {
                        i13 = i12;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function1111111119 = function9;
                final Function2<? super Composer, ? super Integer, Unit> function11111111110 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function11111111111 = function8;
                long j1112 = containerColor;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                        }
                        Function2<Composer, Integer, Unit> function11111111112 = function11111111110;
                        final Function2<? super Composer, ? super Integer, Unit> function11111111113 = function11111111111;
                        final Function2<? super Composer, ? super Integer, Unit> function11111111114 = function1111111119;
                        final Function2<? super Composer, ? super Integer, Unit> function11111111115 = function2;
                        TimePickerDialogKt.TimePickerCustomLayout(function11111111112, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                            public final void invoke(Composer composer4, int i17) {
                                if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                }
                                Modifier.Companion companion = Modifier.INSTANCE;
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                Function2<Composer, Integer, Unit> function11111111116 = function11111111113;
                                Function2<Composer, Integer, Unit> function20 = function11111111114;
                                Function2<Composer, Integer, Unit> function21 = function11111111115;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                if (composer4.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer4.startReusableNode();
                                if (composer4.getInserting()) {
                                    composer4.createNode(constructor);
                                } else {
                                    composer4.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                if (function11111111116 == null) {
                                    composer4.startReplaceGroup(675833080);
                                } else {
                                    composer4.startReplaceGroup(2100011049);
                                    function11111111116.invoke(composer4, 0);
                                }
                                composer4.endReplaceGroup();
                                SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                if (function20 == null) {
                                    composer4.startReplaceGroup(675935256);
                                } else {
                                    composer4.startReplaceGroup(2100014345);
                                    function20.invoke(composer4, 0);
                                }
                                composer4.endReplaceGroup();
                                function21.invoke(composer4, 0);
                                composer4.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer3, 54), function6, composer3, 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function10 = function8;
                j2 = j1112;
                function11 = function1111111119;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function8;
                function11 = function9;
                j2 = containerColor;
            }
            modifier3 = modifier2;
            shape3 = shape2;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        function9 = function5;
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i3 |= i14;
            } else {
                shape2 = shape;
            }
            i3 |= i14;
        } else {
            shape2 = shape;
        }
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                containerColor = j;
                if (composerStartRestartGroup.changed(containerColor)) {
                }
                i3 |= i15;
            } else {
                containerColor = j;
            }
            i3 |= i15;
        } else {
            containerColor = j;
        }
        if ((i2 & 128) != 0) {
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i10 = 8388608;
                } else {
                    i10 = 4194304;
                }
                i3 |= i10;
            }
            i11 = i3;
            if ((i3 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        function8 = null;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if ((i2 & 32) != 0) {
                        i12 = i11 & (-458753);
                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i13 = i12 & (-3670017);
                    } else {
                        i13 = i12;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        function8 = null;
                    }
                    if (i8 != 0) {
                        function9 = null;
                    }
                    if ((i2 & 32) != 0) {
                        i12 = i11 & (-458753);
                        shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i13 = i12 & (-3670017);
                    } else {
                        i13 = i12;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function11111111112 = function9;
                final Function2<? super Composer, ? super Integer, Unit> function11111111113 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function11111111114 = function8;
                long j1113 = containerColor;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                        }
                        Function2<Composer, Integer, Unit> function11111111115 = function11111111113;
                        final Function2<? super Composer, ? super Integer, Unit> function11111111116 = function11111111114;
                        final Function2<? super Composer, ? super Integer, Unit> function11111111117 = function11111111112;
                        final Function2<? super Composer, ? super Integer, Unit> function11111111118 = function2;
                        TimePickerDialogKt.TimePickerCustomLayout(function11111111115, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                            public final void invoke(Composer composer4, int i17) {
                                if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                                }
                                Modifier.Companion companion = Modifier.INSTANCE;
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                                Function2<Composer, Integer, Unit> function11111111119 = function11111111116;
                                Function2<Composer, Integer, Unit> function20 = function11111111117;
                                Function2<Composer, Integer, Unit> function21 = function11111111118;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion2.getConstructor();
                                if (composer4.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer4.startReusableNode();
                                if (composer4.getInserting()) {
                                    composer4.createNode(constructor);
                                } else {
                                    composer4.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                if (function11111111119 == null) {
                                    composer4.startReplaceGroup(675833080);
                                } else {
                                    composer4.startReplaceGroup(2100011049);
                                    function11111111119.invoke(composer4, 0);
                                }
                                composer4.endReplaceGroup();
                                SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                                if (function20 == null) {
                                    composer4.startReplaceGroup(675935256);
                                } else {
                                    composer4.startReplaceGroup(2100014345);
                                    function20.invoke(composer4, 0);
                                }
                                composer4.endReplaceGroup();
                                function21.invoke(composer4, 0);
                                composer4.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer3, 54), function6, composer3, 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function10 = function8;
                j2 = j1113;
                function11 = function11111111112;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function8;
                function11 = function9;
                j2 = containerColor;
            }
            modifier3 = modifier2;
            shape3 = shape2;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 12582912;
        i11 = i3;
        if ((i3 & 4793491) != 4793490) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    function8 = null;
                }
                if (i8 != 0) {
                    function9 = null;
                }
                if ((i2 & 32) != 0) {
                    i12 = i11 & (-458753);
                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                } else {
                    i12 = i11;
                }
                if ((i2 & 64) != 0) {
                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i13 = i12 & (-3670017);
                } else {
                    i13 = i12;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    function8 = null;
                }
                if (i8 != 0) {
                    function9 = null;
                }
                if ((i2 & 32) != 0) {
                    i12 = i11 & (-458753);
                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                } else {
                    i12 = i11;
                }
                if ((i2 & 64) != 0) {
                    containerColor = TimePickerDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i13 = i12 & (-3670017);
                } else {
                    i13 = i12;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-401873644, i13, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
            }
            final Function2<? super Composer, ? super Integer, Unit> function11111111115 = function9;
            final Function2<? super Composer, ? super Integer, Unit> function11111111116 = function7;
            final Function2<? super Composer, ? super Integer, Unit> function11111111117 = function8;
            long j1114 = containerColor;
            composer2 = composerStartRestartGroup;
            SurfaceKt.m954SurfaceT9BRK9s(BackgroundKt.background-bw27NRU(modifier2, containerColor, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m1712getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                public final void invoke(Composer composer3, int i16) {
                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1522143641, i16, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                    }
                    Function2<Composer, Integer, Unit> function11111111118 = function11111111116;
                    final Function2<? super Composer, ? super Integer, Unit> function11111111119 = function11111111117;
                    final Function2<? super Composer, ? super Integer, Unit> function111111111110 = function11111111115;
                    final Function2<? super Composer, ? super Integer, Unit> function111111111111 = function2;
                    TimePickerDialogKt.TimePickerCustomLayout(function11111111118, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                        public final void invoke(Composer composer4, int i17) {
                            if (!composer4.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2122920701, i17, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                            }
                            Modifier.Companion companion = Modifier.INSTANCE;
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
                            Function2<Composer, Integer, Unit> function111111111112 = function11111111119;
                            Function2<Composer, Integer, Unit> function20 = function111111111110;
                            Function2<Composer, Integer, Unit> function21 = function111111111111;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer4, 0);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion2.getConstructor();
                            if (composer4.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer4.startReusableNode();
                            if (composer4.getInserting()) {
                                composer4.createNode(constructor);
                            } else {
                                composer4.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer4);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            if (function111111111112 == null) {
                                composer4.startReplaceGroup(675833080);
                            } else {
                                composer4.startReplaceGroup(2100011049);
                                function111111111112.invoke(composer4, 0);
                            }
                            composer4.endReplaceGroup();
                            SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, (Object) null), composer4, 0);
                            if (function20 == null) {
                                composer4.startReplaceGroup(675935256);
                            } else {
                                composer4.startReplaceGroup(2100014345);
                                function20.invoke(composer4, 0);
                            }
                            composer4.endReplaceGroup();
                            function21.invoke(composer4, 0);
                            composer4.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composer3, 54), function6, composer3, 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composer2, ((i13 >> 12) & 112) | 12607488, 108);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function10 = function8;
            j2 = j1114;
            function11 = function11111111115;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            function10 = function8;
            function11 = function9;
            j2 = containerColor;
        }
        modifier3 = modifier2;
        shape3 = shape2;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerDialogKt.a(function2, function3, modifier3, function10, function11, shape3, j2, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Function2 function2, Function2 function3, Modifier modifier, Function2 function4, Function2 function5, Shape shape, long j, Function3 function6, int i, int i2, Composer composer, int i3) {
        m1127TimePickerDialogLayout3csKH6Y(function2, function3, modifier, function4, function5, shape, j, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(Function0 function0, Function2 function2, Function2 function3, Modifier modifier, DialogProperties dialogProperties, Function2 function4, Function2 function5, Shape shape, long j, Function3 function6, int i, int i2, Composer composer, int i3) {
        m1126TimePickerDialogFItCLgY(function0, function2, function3, modifier, dialogProperties, function4, function5, shape, j, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit c(Function2 function2, Function2 function3, Function3 function4, int i, Composer composer, int i2) {
        TimePickerCustomLayout(function2, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }
}
