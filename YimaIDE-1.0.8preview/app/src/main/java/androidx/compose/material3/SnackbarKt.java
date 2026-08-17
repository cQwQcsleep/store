package androidx.compose.material3;

import androidx.compose.foundation.layout.AlignmentLineKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.SnackbarKt;
import androidx.compose.material3.tokens.SnackbarTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0096\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0015\b\u0002\u0010\u0004\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001ag\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\rH\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u001ab\u0010\u0019\u001a\u00020\u00012\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0013\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0003¢\u0006\u0004\b\u001d\u0010\u001e\u001ad\u0010\u001f\u001a\u00020\u00012\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0013\u0010\u0004\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\u0013\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\rH\u0003¢\u0006\u0004\b\"\u0010\u001e\"\u0010\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010&\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010'\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010(\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010)\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010*\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010+\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010,\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%¨\u0006-"}, d2 = {"Snackbar", "", "modifier", "Landroidx/compose/ui/Modifier;", "action", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "dismissAction", "actionOnNewLine", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "actionContentColor", "dismissActionContentColor", "content", "Snackbar-eQBnUkQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/graphics/Shape;JJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "snackbarData", "Landroidx/compose/material3/SnackbarData;", "actionColor", "Snackbar-sDKtq54", "(Landroidx/compose/material3/SnackbarData;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJJJJLandroidx/compose/runtime/Composer;II)V", "NewLineButtonSnackbar", "text", "actionTextStyle", "Landroidx/compose/ui/text/TextStyle;", "NewLineButtonSnackbar-kKq0p4A", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;JJLandroidx/compose/runtime/Composer;I)V", "OneRowSnackbar", "actionTextColor", "dismissActionColor", "OneRowSnackbar-kKq0p4A", "ContainerMaxWidth", "Landroidx/compose/ui/unit/Dp;", "F", "HeightToFirstLine", "HorizontalSpacing", "HorizontalSpacingButtonSide", "SeparateButtonExtraY", "SnackbarVerticalPadding", "TextEndExtraSpacing", "LongButtonVerticalOffset", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SnackbarKt {
    private static final float ContainerMaxWidth = Dp.m6022constructorimpl(600.0f);
    private static final float HeightToFirstLine = Dp.m6022constructorimpl(30.0f);
    private static final float HorizontalSpacing = Dp.m6022constructorimpl(16.0f);
    private static final float HorizontalSpacingButtonSide = Dp.m6022constructorimpl(8.0f);
    private static final float SeparateButtonExtraY = Dp.m6022constructorimpl(2.0f);
    private static final float SnackbarVerticalPadding = Dp.m6022constructorimpl(6.0f);
    private static final float TextEndExtraSpacing = Dp.m6022constructorimpl(8.0f);
    private static final float LongButtonVerticalOffset = Dp.m6022constructorimpl(12.0f);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: NewLineButtonSnackbar-kKq0p4A, reason: not valid java name */
    public static final void m937NewLineButtonSnackbarkKq0p4A(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final TextStyle textStyle, final long j, long j2, Composer composer, final int i) {
        int i2;
        long j3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-264666338);
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
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(textStyle) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            j3 = j2;
            i2 |= composerStartRestartGroup.changed(j3) ? 131072 : 65536;
        } else {
            j3 = j2;
        }
        if (composerStartRestartGroup.shouldExecute((74899 & i2) != 74898, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-264666338, i2, -1, "androidx.compose.material3.NewLineButtonSnackbar (Snackbar.kt:258)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifier = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(SizeKt.widthIn-VpY3zN4$default(companion, 0.0f, ContainerMaxWidth, 1, (Object) null), 0.0f, 1, (Object) null), HorizontalSpacing, 0.0f, 0.0f, SeparateButtonExtraY, 6, (Object) null);
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.Vertical top = arrangement.getTop();
            Alignment.Companion companion2 = Alignment.INSTANCE;
            int i3 = i2;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(top, companion2.getStart(), composerStartRestartGroup, 0);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion3.getConstructor();
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Modifier modifier2 = AlignmentLineKt.paddingFromBaseline-VpY3zN4(companion, HeightToFirstLine, LongButtonVerticalOffset);
            float fM6022constructorimpl = HorizontalSpacingButtonSide;
            Modifier modifier3 = PaddingKt.padding-qDBjuR0$default(modifier2, 0.0f, 0.0f, fM6022constructorimpl, 0.0f, 11, (Object) null);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion2.getTopStart(), false);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
            Function0<ComposeUiNode> constructor2 = companion3.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
            if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            function2.invoke(composerStartRestartGroup, Integer.valueOf(i3 & 14));
            composerStartRestartGroup.endNode();
            Modifier modifierAlign = columnScopeInstance.align(companion, companion2.getEnd());
            if (function4 != null) {
                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
            }
            Modifier modifier4 = PaddingKt.padding-qDBjuR0$default(modifierAlign, 0.0f, 0.0f, fM6022constructorimpl, 0.0f, 11, (Object) null);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion2.getTopStart(), false);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier4);
            Function0<ComposeUiNode> constructor3 = companion3.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, companion3.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = companion3.getSetCompositeKeyHash();
            if (composerM2388constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion3.getSetModifier());
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.getStart(), companion2.getTop(), composerStartRestartGroup, 0);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor4 = companion3.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor4);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl4 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl4, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl4, currentCompositionLocalMap4, companion3.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = companion3.getSetCompositeKeyHash();
            if (composerM2388constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM2388constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM2388constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m2396setimpl(composerM2388constructorimpl4, modifierMaterializeModifier4, companion3.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ProvidedValue[] providedValueArr = {ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(j)), TextKt.getLocalTextStyle().provides(textStyle)};
            int i4 = ProvidedValue.$stable;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, function3, composerStartRestartGroup, (i3 & 112) | i4);
            if (function4 != null) {
                composerStartRestartGroup.startReplaceGroup(916269829);
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(j3)), function4, composerStartRestartGroup, i4 | ((i3 >> 3) & 112));
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(916475483);
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final long j4 = j3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: thd
                public final Object invoke(Object obj, Object obj2) {
                    return SnackbarKt.b(function2, function3, function4, textStyle, j, j4, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: OneRowSnackbar-kKq0p4A, reason: not valid java name */
    public static final void m938OneRowSnackbarkKq0p4A(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final TextStyle textStyle, final long j, final long j2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-931325388);
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
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(textStyle) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(j2) ? 131072 : 65536;
        }
        if (composerStartRestartGroup.shouldExecute((74899 & i2) != 74898, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-931325388, i2, -1, "androidx.compose.material3.OneRowSnackbar (Snackbar.kt:303)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifier = PaddingKt.padding-qDBjuR0$default(companion, HorizontalSpacing, 0.0f, function4 == null ? HorizontalSpacingButtonSide : Dp.m6022constructorimpl(0.0f), 0.0f, 10, (Object) null);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            int i3 = i2;
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SnackbarKt$OneRowSnackbar$2$1("action", "dismissAction", "text");
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
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
            Modifier modifier2 = PaddingKt.padding-VpY3zN4$default(LayoutIdKt.layoutId(companion, "text"), 0.0f, SnackbarVerticalPadding, 1, (Object) null);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
            Function0<ComposeUiNode> constructor2 = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            function2.invoke(composerStartRestartGroup, Integer.valueOf(i3 & 14));
            composerStartRestartGroup.endNode();
            if (function3 != null) {
                composerStartRestartGroup.startReplaceGroup(-1014168049);
                Modifier modifierLayoutId = LayoutIdKt.layoutId(companion, "action");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId);
                Function0<ComposeUiNode> constructor3 = companion2.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion2.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = companion2.getSetCompositeKeyHash();
                if (composerM2388constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion2.getSetModifier());
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(j)), TextKt.getLocalTextStyle().provides(textStyle)}, function3, composerStartRestartGroup, ProvidedValue.$stable | (i3 & 112));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1013852841);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (function4 != null) {
                composerStartRestartGroup.startReplaceGroup(-1013804481);
                Modifier modifierLayoutId2 = LayoutIdKt.layoutId(companion, "dismissAction");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId2);
                Function0<ComposeUiNode> constructor4 = companion2.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor4);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl4 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy3, companion2.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl4, currentCompositionLocalMap4, companion2.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = companion2.getSetCompositeKeyHash();
                if (composerM2388constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    composerM2388constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM2388constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.m2396setimpl(composerM2388constructorimpl4, modifierMaterializeModifier4, companion2.getSetModifier());
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(j2)), function4, composerStartRestartGroup, ProvidedValue.$stable | ((i3 >> 3) & 112));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1013535401);
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rhd
                public final Object invoke(Object obj, Object obj2) {
                    return SnackbarKt.c(function2, function3, function4, textStyle, j, j2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x010f  */
    /* JADX WARN: Code duplicated, block: B:102:0x0114  */
    /* JADX WARN: Code duplicated, block: B:104:0x0118  */
    /* JADX WARN: Code duplicated, block: B:106:0x0120  */
    /* JADX WARN: Code duplicated, block: B:107:0x0123  */
    /* JADX WARN: Code duplicated, block: B:111:0x0131  */
    /* JADX WARN: Code duplicated, block: B:112:0x0133  */
    /* JADX WARN: Code duplicated, block: B:115:0x013c  */
    /* JADX WARN: Code duplicated, block: B:117:0x0152  */
    /* JADX WARN: Code duplicated, block: B:136:0x0189 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:137:0x018b  */
    /* JADX WARN: Code duplicated, block: B:138:0x018e  */
    /* JADX WARN: Code duplicated, block: B:141:0x0194  */
    /* JADX WARN: Code duplicated, block: B:144:0x0199  */
    /* JADX WARN: Code duplicated, block: B:146:0x019d  */
    /* JADX WARN: Code duplicated, block: B:147:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:150:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:151:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:154:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:155:0x01be  */
    /* JADX WARN: Code duplicated, block: B:158:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:159:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:162:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:163:0x01db  */
    /* JADX WARN: Code duplicated, block: B:166:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:167:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:170:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:173:0x025a  */
    /* JADX WARN: Code duplicated, block: B:175:0x0269  */
    /* JADX WARN: Code duplicated, block: B:178:0x0280  */
    /* JADX WARN: Code duplicated, block: B:180:? A[RETURN, SYNTHETIC] */
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
    /* JADX WARN: Code duplicated, block: B:50:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0095  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:70:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:80:0x00da  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:90:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:92:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:95:0x0103  */
    /* JADX WARN: Code duplicated, block: B:97:0x0107  */
    /* JADX INFO: renamed from: Snackbar-eQBnUkQ, reason: not valid java name */
    public static final void m939SnackbareQBnUkQ(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, boolean z, Shape shape, long j, long j2, long j3, long j4, final Function2<? super Composer, ? super Integer, Unit> function4, Composer composer, final int i, final int i2) {
        int i3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function6;
        int i5;
        int i6;
        boolean z2;
        int i7;
        Shape shape2;
        long j5;
        int i8;
        boolean z3;
        Composer composer2;
        final Modifier modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final boolean z4;
        final Shape shape3;
        final long j6;
        final long j7;
        final long j8;
        final long j9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function9;
        boolean z5;
        Shape shape4;
        long color;
        long contentColor;
        long actionContentColor;
        long dismissActionContentColor;
        int i9;
        int i10;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1218779924);
        int i12 = i2 & 1;
        if (i12 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i13 = i2 & 2;
        if (i13 == 0) {
            if ((i & 48) == 0) {
                function5 = function2;
                i3 |= composerStartRestartGroup.changedInstance(function5) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    function6 = function3;
                    if (composerStartRestartGroup.changedInstance(function6)) {
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
                    if ((i & 24576) == 0) {
                        if ((i2 & 16) == 0) {
                            shape2 = shape;
                            int i14 = composerStartRestartGroup.changed(shape2) ? 16384 : 8192;
                            i3 |= i14;
                        } else {
                            shape2 = shape;
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            j5 = j;
                            int i15 = composerStartRestartGroup.changed(j5) ? 131072 : 65536;
                            i3 |= i15;
                        } else {
                            j5 = j;
                        }
                        i3 |= i15;
                    } else {
                        j5 = j;
                    }
                    if ((i & 1572864) != 0) {
                        if ((i2 & 64) == 0 || !composerStartRestartGroup.changed(j2)) {
                            i11 = 524288;
                        } else {
                            i11 = 1048576;
                        }
                        i3 |= i11;
                    }
                    if ((i & 12582912) != 0) {
                        if ((i2 & 128) == 0 || !composerStartRestartGroup.changed(j3)) {
                            i10 = 4194304;
                        } else {
                            i10 = 8388608;
                        }
                        i3 |= i10;
                    }
                    if ((100663296 & i) != 0) {
                        if ((i2 & 256) == 0 || !composerStartRestartGroup.changed(j4)) {
                            i9 = 33554432;
                        } else {
                            i9 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        }
                        i3 |= i9;
                    }
                    if ((i2 & 512) != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i8 = 536870912;
                            } else {
                                i8 = 268435456;
                            }
                            i3 |= i8;
                        }
                        if ((i3 & 306783379) != 306783378) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i12 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i13 != 0) {
                                    function5 = null;
                                }
                                function9 = i4 == 0 ? function6 : null;
                                if (i6 != 0) {
                                    z5 = false;
                                } else {
                                    z5 = z2;
                                }
                                if ((i2 & 16) != 0) {
                                    shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i3 &= -57345;
                                } else {
                                    shape4 = shape2;
                                }
                                if ((i2 & 32) != 0) {
                                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                    i3 &= -458753;
                                } else {
                                    color = j5;
                                }
                                if ((i2 & 64) != 0) {
                                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                    i3 &= -3670017;
                                } else {
                                    contentColor = j2;
                                }
                                if ((i2 & 128) != 0) {
                                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    actionContentColor = j3;
                                }
                                if ((i2 & 256) != 0) {
                                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                    i3 &= -234881025;
                                } else {
                                    dismissActionContentColor = j4;
                                }
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i3 &= -29360129;
                                }
                                if ((i2 & 256) != 0) {
                                    i3 &= -234881025;
                                }
                                modifier3 = modifier;
                                dismissActionContentColor = j4;
                                function9 = function6;
                                z5 = z2;
                                shape4 = shape2;
                                color = j5;
                                contentColor = j2;
                                actionContentColor = j3;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function10 = function5;
                            final long j10 = actionContentColor;
                            final boolean z6 = z5;
                            final long j11 = dismissActionContentColor;
                            final Function2<? super Composer, ? super Integer, Unit> function11 = function9;
                            Modifier modifier4 = modifier3;
                            int i16 = i3 >> 9;
                            SurfaceKt.m954SurfaceT9BRK9s(modifier4, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                                public final void invoke(Composer composer3, int i17) {
                                    if (!composer3.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1343524879, i17, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                                    }
                                    SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                                    TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                                    final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                                    ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                    final boolean z7 = z6;
                                    final Function2<Composer, Integer, Unit> function12 = function10;
                                    final Function2<Composer, Integer, Unit> function13 = function4;
                                    final Function2<Composer, Integer, Unit> function14 = function11;
                                    final long j12 = j10;
                                    final long j13 = j11;
                                    CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                        public final void invoke(Composer composer4, int i18) {
                                            if (!composer4.shouldExecute((i18 & 3) != 2, i18 & 1)) {
                                                composer4.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(969655473, i18, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                            }
                                            if (!z7 || function12 == null) {
                                                composer4.startReplaceGroup(-168976609);
                                                SnackbarKt.m938OneRowSnackbarkKq0p4A(function13, function12, function14, value2, j12, j13, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-168990288);
                                                SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function13, function12, function14, value2, j12, j13, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i16 & 112) | (i16 & 896) | (i16 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                            modifier2 = modifier4;
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            shape3 = shape4;
                            function7 = function5;
                            j6 = color;
                            j7 = contentColor;
                            j8 = actionContentColor;
                            z4 = z5;
                            j9 = dismissActionContentColor;
                            function8 = function9;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            function7 = function5;
                            function8 = function6;
                            z4 = z2;
                            shape3 = shape2;
                            j6 = j5;
                            j7 = j2;
                            j8 = j3;
                            j9 = j4;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 805306368;
                    if ((i3 & 306783379) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z5 = false;
                            } else {
                                z5 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z5 = false;
                            } else {
                                z5 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function12 = function5;
                        final long j12 = actionContentColor;
                        final boolean z7 = z5;
                        final long j13 = dismissActionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function13 = function9;
                        Modifier modifier5 = modifier3;
                        int i17 = i3 >> 9;
                        SurfaceKt.m954SurfaceT9BRK9s(modifier5, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                            public final void invoke(Composer composer3, int i18) {
                                if (!composer3.shouldExecute((i18 & 3) != 2, i18 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1343524879, i18, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                                }
                                SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                                TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z8 = z7;
                                final Function2<? super Composer, ? super Integer, Unit> function14 = function12;
                                final Function2<? super Composer, ? super Integer, Unit> function15 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function16 = function13;
                                final long j14 = j12;
                                final long j15 = j13;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                    public final void invoke(Composer composer4, int i19) {
                                        if (!composer4.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(969655473, i19, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                        }
                                        if (!z8 || function14 == null) {
                                            composer4.startReplaceGroup(-168976609);
                                            SnackbarKt.m938OneRowSnackbarkKq0p4A(function15, function14, function16, value2, j14, j15, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-168990288);
                                            SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function15, function14, function16, value2, j14, j15, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i17 & 112) | (i17 & 896) | (i17 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                        modifier2 = modifier5;
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        shape3 = shape4;
                        function7 = function5;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        z4 = z5;
                        j9 = dismissActionContentColor;
                        function8 = function9;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        function7 = function5;
                        function8 = function6;
                        z4 = z2;
                        shape3 = shape2;
                        j6 = j5;
                        j7 = j2;
                        j8 = j3;
                        j9 = j4;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                            public final Object invoke(Object obj, Object obj2) {
                                return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 3072;
                z2 = z;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
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
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j5 = j;
                        if (composerStartRestartGroup.changed(j5)) {
                        }
                        i3 |= i15;
                    } else {
                        j5 = j;
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                if ((i & 1572864) != 0) {
                    if ((i2 & 64) == 0) {
                        i11 = 524288;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i10 = 4194304;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                if ((100663296 & i) != 0) {
                    if ((i2 & 256) == 0) {
                        i9 = 33554432;
                    } else {
                        i9 = 33554432;
                    }
                    i3 |= i9;
                }
                if ((i2 & 512) != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i8 = 536870912;
                        } else {
                            i8 = 268435456;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z5 = false;
                            } else {
                                z5 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z5 = false;
                            } else {
                                z5 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function14 = function5;
                        final long j14 = actionContentColor;
                        final boolean z8 = z5;
                        final long j15 = dismissActionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function15 = function9;
                        Modifier modifier6 = modifier3;
                        int i18 = i3 >> 9;
                        SurfaceKt.m954SurfaceT9BRK9s(modifier6, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                            public final void invoke(Composer composer3, int i19) {
                                if (!composer3.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1343524879, i19, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                                }
                                SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                                TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z9 = z8;
                                final Function2<? super Composer, ? super Integer, Unit> function16 = function14;
                                final Function2<? super Composer, ? super Integer, Unit> function17 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function18 = function15;
                                final long j16 = j14;
                                final long j17 = j15;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                    public final void invoke(Composer composer4, int i110) {
                                        if (!composer4.shouldExecute((i110 & 3) != 2, i110 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(969655473, i110, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                        }
                                        if (!z9 || function16 == null) {
                                            composer4.startReplaceGroup(-168976609);
                                            SnackbarKt.m938OneRowSnackbarkKq0p4A(function17, function16, function18, value2, j16, j17, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-168990288);
                                            SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function17, function16, function18, value2, j16, j17, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i18 & 112) | (i18 & 896) | (i18 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                        modifier2 = modifier6;
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        shape3 = shape4;
                        function7 = function5;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        z4 = z5;
                        j9 = dismissActionContentColor;
                        function8 = function9;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        function7 = function5;
                        function8 = function6;
                        z4 = z2;
                        shape3 = shape2;
                        j6 = j5;
                        j7 = j2;
                        j8 = j3;
                        j9 = j4;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                            public final Object invoke(Object obj, Object obj2) {
                                return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                if ((i3 & 306783379) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function16 = function5;
                    final long j16 = actionContentColor;
                    final boolean z9 = z5;
                    final long j17 = dismissActionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function17 = function9;
                    Modifier modifier7 = modifier3;
                    int i19 = i3 >> 9;
                    SurfaceKt.m954SurfaceT9BRK9s(modifier7, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                        public final void invoke(Composer composer3, int i110) {
                            if (!composer3.shouldExecute((i110 & 3) != 2, i110 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1343524879, i110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                            }
                            SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                            TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z10 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function18 = function16;
                            final Function2<? super Composer, ? super Integer, Unit> function19 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function110 = function17;
                            final long j18 = j16;
                            final long j19 = j17;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                public final void invoke(Composer composer4, int i111) {
                                    if (!composer4.shouldExecute((i111 & 3) != 2, i111 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(969655473, i111, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                    }
                                    if (!z10 || function18 == null) {
                                        composer4.startReplaceGroup(-168976609);
                                        SnackbarKt.m938OneRowSnackbarkKq0p4A(function19, function18, function110, value2, j18, j19, composer4, 0);
                                        composer4.endReplaceGroup();
                                    } else {
                                        composer4.startReplaceGroup(-168990288);
                                        SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function19, function18, function110, value2, j18, j19, composer4, 0);
                                        composer4.endReplaceGroup();
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i19 & 112) | (i19 & 896) | (i19 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                    modifier2 = modifier7;
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    shape3 = shape4;
                    function7 = function5;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    z4 = z5;
                    j9 = dismissActionContentColor;
                    function8 = function9;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    function7 = function5;
                    function8 = function6;
                    z4 = z2;
                    shape3 = shape2;
                    j6 = j5;
                    j7 = j2;
                    j8 = j3;
                    j9 = j4;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                        public final Object invoke(Object obj, Object obj2) {
                            return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            function6 = function3;
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
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
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
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j5 = j;
                        if (composerStartRestartGroup.changed(j5)) {
                        }
                        i3 |= i15;
                    } else {
                        j5 = j;
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                if ((i & 1572864) != 0) {
                    if ((i2 & 64) == 0) {
                        i11 = 524288;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i10 = 4194304;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                if ((100663296 & i) != 0) {
                    if ((i2 & 256) == 0) {
                        i9 = 33554432;
                    } else {
                        i9 = 33554432;
                    }
                    i3 |= i9;
                }
                if ((i2 & 512) != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i8 = 536870912;
                        } else {
                            i8 = 268435456;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z5 = false;
                            } else {
                                z5 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z5 = false;
                            } else {
                                z5 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function18 = function5;
                        final long j18 = actionContentColor;
                        final boolean z10 = z5;
                        final long j19 = dismissActionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function19 = function9;
                        Modifier modifier8 = modifier3;
                        int i110 = i3 >> 9;
                        SurfaceKt.m954SurfaceT9BRK9s(modifier8, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                            public final void invoke(Composer composer3, int i111) {
                                if (!composer3.shouldExecute((i111 & 3) != 2, i111 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1343524879, i111, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                                }
                                SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                                TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z11 = z10;
                                final Function2<? super Composer, ? super Integer, Unit> function110 = function18;
                                final Function2<? super Composer, ? super Integer, Unit> function111 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function112 = function19;
                                final long j110 = j18;
                                final long j111 = j19;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                    public final void invoke(Composer composer4, int i112) {
                                        if (!composer4.shouldExecute((i112 & 3) != 2, i112 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(969655473, i112, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                        }
                                        if (!z11 || function110 == null) {
                                            composer4.startReplaceGroup(-168976609);
                                            SnackbarKt.m938OneRowSnackbarkKq0p4A(function111, function110, function112, value2, j110, j111, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-168990288);
                                            SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function111, function110, function112, value2, j110, j111, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i110 & 112) | (i110 & 896) | (i110 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                        modifier2 = modifier8;
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        shape3 = shape4;
                        function7 = function5;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        z4 = z5;
                        j9 = dismissActionContentColor;
                        function8 = function9;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        function7 = function5;
                        function8 = function6;
                        z4 = z2;
                        shape3 = shape2;
                        j6 = j5;
                        j7 = j2;
                        j8 = j3;
                        j9 = j4;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                            public final Object invoke(Object obj, Object obj2) {
                                return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                if ((i3 & 306783379) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function110 = function5;
                    final long j110 = actionContentColor;
                    final boolean z11 = z5;
                    final long j111 = dismissActionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function111 = function9;
                    Modifier modifier9 = modifier3;
                    int i111 = i3 >> 9;
                    SurfaceKt.m954SurfaceT9BRK9s(modifier9, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                        public final void invoke(Composer composer3, int i112) {
                            if (!composer3.shouldExecute((i112 & 3) != 2, i112 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1343524879, i112, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                            }
                            SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                            TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z12 = z11;
                            final Function2<? super Composer, ? super Integer, Unit> function112 = function110;
                            final Function2<? super Composer, ? super Integer, Unit> function113 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function114 = function111;
                            final long j112 = j110;
                            final long j113 = j111;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                public final void invoke(Composer composer4, int i113) {
                                    if (!composer4.shouldExecute((i113 & 3) != 2, i113 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(969655473, i113, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                    }
                                    if (!z12 || function112 == null) {
                                        composer4.startReplaceGroup(-168976609);
                                        SnackbarKt.m938OneRowSnackbarkKq0p4A(function113, function112, function114, value2, j112, j113, composer4, 0);
                                        composer4.endReplaceGroup();
                                    } else {
                                        composer4.startReplaceGroup(-168990288);
                                        SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function113, function112, function114, value2, j112, j113, composer4, 0);
                                        composer4.endReplaceGroup();
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i111 & 112) | (i111 & 896) | (i111 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                    modifier2 = modifier9;
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    shape3 = shape4;
                    function7 = function5;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    z4 = z5;
                    j9 = dismissActionContentColor;
                    function8 = function9;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    function7 = function5;
                    function8 = function6;
                    z4 = z2;
                    shape3 = shape2;
                    j6 = j5;
                    j7 = j2;
                    j8 = j3;
                    j9 = j4;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                        public final Object invoke(Object obj, Object obj2) {
                            return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
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
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j5 = j;
                    if (composerStartRestartGroup.changed(j5)) {
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                i3 |= i15;
            } else {
                j5 = j;
            }
            if ((i & 1572864) != 0) {
                if ((i2 & 64) == 0) {
                    i11 = 524288;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i10 = 4194304;
                } else {
                    i10 = 4194304;
                }
                i3 |= i10;
            }
            if ((100663296 & i) != 0) {
                if ((i2 & 256) == 0) {
                    i9 = 33554432;
                } else {
                    i9 = 33554432;
                }
                i3 |= i9;
            }
            if ((i2 & 512) != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 536870912;
                    } else {
                        i8 = 268435456;
                    }
                    i3 |= i8;
                }
                if ((i3 & 306783379) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function112 = function5;
                    final long j112 = actionContentColor;
                    final boolean z12 = z5;
                    final long j113 = dismissActionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function113 = function9;
                    Modifier modifier10 = modifier3;
                    int i112 = i3 >> 9;
                    SurfaceKt.m954SurfaceT9BRK9s(modifier10, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                        public final void invoke(Composer composer3, int i113) {
                            if (!composer3.shouldExecute((i113 & 3) != 2, i113 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1343524879, i113, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                            }
                            SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                            TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z13 = z12;
                            final Function2<? super Composer, ? super Integer, Unit> function114 = function112;
                            final Function2<? super Composer, ? super Integer, Unit> function115 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function116 = function113;
                            final long j114 = j112;
                            final long j115 = j113;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                public final void invoke(Composer composer4, int i114) {
                                    if (!composer4.shouldExecute((i114 & 3) != 2, i114 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(969655473, i114, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                    }
                                    if (!z13 || function114 == null) {
                                        composer4.startReplaceGroup(-168976609);
                                        SnackbarKt.m938OneRowSnackbarkKq0p4A(function115, function114, function116, value2, j114, j115, composer4, 0);
                                        composer4.endReplaceGroup();
                                    } else {
                                        composer4.startReplaceGroup(-168990288);
                                        SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function115, function114, function116, value2, j114, j115, composer4, 0);
                                        composer4.endReplaceGroup();
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i112 & 112) | (i112 & 896) | (i112 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                    modifier2 = modifier10;
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    shape3 = shape4;
                    function7 = function5;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    z4 = z5;
                    j9 = dismissActionContentColor;
                    function8 = function9;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    function7 = function5;
                    function8 = function6;
                    z4 = z2;
                    shape3 = shape2;
                    j6 = j5;
                    j7 = j2;
                    j8 = j3;
                    j9 = j4;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                        public final Object invoke(Object obj, Object obj2) {
                            return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            if ((i3 & 306783379) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function114 = function5;
                final long j114 = actionContentColor;
                final boolean z13 = z5;
                final long j115 = dismissActionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function115 = function9;
                Modifier modifier11 = modifier3;
                int i113 = i3 >> 9;
                SurfaceKt.m954SurfaceT9BRK9s(modifier11, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                    public final void invoke(Composer composer3, int i114) {
                        if (!composer3.shouldExecute((i114 & 3) != 2, i114 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1343524879, i114, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                        }
                        SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                        TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                        final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                        ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                        final boolean z14 = z13;
                        final Function2<? super Composer, ? super Integer, Unit> function116 = function114;
                        final Function2<? super Composer, ? super Integer, Unit> function117 = function4;
                        final Function2<? super Composer, ? super Integer, Unit> function118 = function115;
                        final long j116 = j114;
                        final long j117 = j115;
                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                            public final void invoke(Composer composer4, int i115) {
                                if (!composer4.shouldExecute((i115 & 3) != 2, i115 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(969655473, i115, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                }
                                if (!z14 || function116 == null) {
                                    composer4.startReplaceGroup(-168976609);
                                    SnackbarKt.m938OneRowSnackbarkKq0p4A(function117, function116, function118, value2, j116, j117, composer4, 0);
                                    composer4.endReplaceGroup();
                                } else {
                                    composer4.startReplaceGroup(-168990288);
                                    SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function117, function116, function118, value2, j116, j117, composer4, 0);
                                    composer4.endReplaceGroup();
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i113 & 112) | (i113 & 896) | (i113 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                modifier2 = modifier11;
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                shape3 = shape4;
                function7 = function5;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                z4 = z5;
                j9 = dismissActionContentColor;
                function8 = function9;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                function7 = function5;
                function8 = function6;
                z4 = z2;
                shape3 = shape2;
                j6 = j5;
                j7 = j2;
                j8 = j3;
                j9 = j4;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                    public final Object invoke(Object obj, Object obj2) {
                        return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        function5 = function2;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                function6 = function3;
                if (composerStartRestartGroup.changedInstance(function6)) {
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
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
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
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j5 = j;
                        if (composerStartRestartGroup.changed(j5)) {
                        }
                        i3 |= i15;
                    } else {
                        j5 = j;
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                if ((i & 1572864) != 0) {
                    if ((i2 & 64) == 0) {
                        i11 = 524288;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i10 = 4194304;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                if ((100663296 & i) != 0) {
                    if ((i2 & 256) == 0) {
                        i9 = 33554432;
                    } else {
                        i9 = 33554432;
                    }
                    i3 |= i9;
                }
                if ((i2 & 512) != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i8 = 536870912;
                        } else {
                            i8 = 268435456;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z5 = false;
                            } else {
                                z5 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z5 = false;
                            } else {
                                z5 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function116 = function5;
                        final long j116 = actionContentColor;
                        final boolean z14 = z5;
                        final long j117 = dismissActionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function117 = function9;
                        Modifier modifier12 = modifier3;
                        int i114 = i3 >> 9;
                        SurfaceKt.m954SurfaceT9BRK9s(modifier12, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                            public final void invoke(Composer composer3, int i115) {
                                if (!composer3.shouldExecute((i115 & 3) != 2, i115 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1343524879, i115, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                                }
                                SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                                TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z15 = z14;
                                final Function2<? super Composer, ? super Integer, Unit> function118 = function116;
                                final Function2<? super Composer, ? super Integer, Unit> function119 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function1110 = function117;
                                final long j118 = j116;
                                final long j119 = j117;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                    public final void invoke(Composer composer4, int i116) {
                                        if (!composer4.shouldExecute((i116 & 3) != 2, i116 & 1)) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(969655473, i116, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                        }
                                        if (!z15 || function118 == null) {
                                            composer4.startReplaceGroup(-168976609);
                                            SnackbarKt.m938OneRowSnackbarkKq0p4A(function119, function118, function1110, value2, j118, j119, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-168990288);
                                            SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function119, function118, function1110, value2, j118, j119, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i114 & 112) | (i114 & 896) | (i114 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                        modifier2 = modifier12;
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        shape3 = shape4;
                        function7 = function5;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        z4 = z5;
                        j9 = dismissActionContentColor;
                        function8 = function9;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        function7 = function5;
                        function8 = function6;
                        z4 = z2;
                        shape3 = shape2;
                        j6 = j5;
                        j7 = j2;
                        j8 = j3;
                        j9 = j4;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                            public final Object invoke(Object obj, Object obj2) {
                                return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                if ((i3 & 306783379) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function118 = function5;
                    final long j118 = actionContentColor;
                    final boolean z15 = z5;
                    final long j119 = dismissActionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function119 = function9;
                    Modifier modifier13 = modifier3;
                    int i115 = i3 >> 9;
                    SurfaceKt.m954SurfaceT9BRK9s(modifier13, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                        public final void invoke(Composer composer3, int i116) {
                            if (!composer3.shouldExecute((i116 & 3) != 2, i116 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1343524879, i116, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                            }
                            SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                            TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z16 = z15;
                            final Function2<? super Composer, ? super Integer, Unit> function1110 = function118;
                            final Function2<? super Composer, ? super Integer, Unit> function1111 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function1112 = function119;
                            final long j1110 = j118;
                            final long j1111 = j119;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                public final void invoke(Composer composer4, int i117) {
                                    if (!composer4.shouldExecute((i117 & 3) != 2, i117 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(969655473, i117, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                    }
                                    if (!z16 || function1110 == null) {
                                        composer4.startReplaceGroup(-168976609);
                                        SnackbarKt.m938OneRowSnackbarkKq0p4A(function1111, function1110, function1112, value2, j1110, j1111, composer4, 0);
                                        composer4.endReplaceGroup();
                                    } else {
                                        composer4.startReplaceGroup(-168990288);
                                        SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function1111, function1110, function1112, value2, j1110, j1111, composer4, 0);
                                        composer4.endReplaceGroup();
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i115 & 112) | (i115 & 896) | (i115 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                    modifier2 = modifier13;
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    shape3 = shape4;
                    function7 = function5;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    z4 = z5;
                    j9 = dismissActionContentColor;
                    function8 = function9;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    function7 = function5;
                    function8 = function6;
                    z4 = z2;
                    shape3 = shape2;
                    j6 = j5;
                    j7 = j2;
                    j8 = j3;
                    j9 = j4;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                        public final Object invoke(Object obj, Object obj2) {
                            return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
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
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j5 = j;
                    if (composerStartRestartGroup.changed(j5)) {
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                i3 |= i15;
            } else {
                j5 = j;
            }
            if ((i & 1572864) != 0) {
                if ((i2 & 64) == 0) {
                    i11 = 524288;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i10 = 4194304;
                } else {
                    i10 = 4194304;
                }
                i3 |= i10;
            }
            if ((100663296 & i) != 0) {
                if ((i2 & 256) == 0) {
                    i9 = 33554432;
                } else {
                    i9 = 33554432;
                }
                i3 |= i9;
            }
            if ((i2 & 512) != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 536870912;
                    } else {
                        i8 = 268435456;
                    }
                    i3 |= i8;
                }
                if ((i3 & 306783379) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1110 = function5;
                    final long j1110 = actionContentColor;
                    final boolean z16 = z5;
                    final long j1111 = dismissActionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function1111 = function9;
                    Modifier modifier14 = modifier3;
                    int i116 = i3 >> 9;
                    SurfaceKt.m954SurfaceT9BRK9s(modifier14, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                        public final void invoke(Composer composer3, int i117) {
                            if (!composer3.shouldExecute((i117 & 3) != 2, i117 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1343524879, i117, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                            }
                            SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                            TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z17 = z16;
                            final Function2<? super Composer, ? super Integer, Unit> function1112 = function1110;
                            final Function2<? super Composer, ? super Integer, Unit> function1113 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function1114 = function1111;
                            final long j1112 = j1110;
                            final long j1113 = j1111;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                public final void invoke(Composer composer4, int i118) {
                                    if (!composer4.shouldExecute((i118 & 3) != 2, i118 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(969655473, i118, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                    }
                                    if (!z17 || function1112 == null) {
                                        composer4.startReplaceGroup(-168976609);
                                        SnackbarKt.m938OneRowSnackbarkKq0p4A(function1113, function1112, function1114, value2, j1112, j1113, composer4, 0);
                                        composer4.endReplaceGroup();
                                    } else {
                                        composer4.startReplaceGroup(-168990288);
                                        SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function1113, function1112, function1114, value2, j1112, j1113, composer4, 0);
                                        composer4.endReplaceGroup();
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i116 & 112) | (i116 & 896) | (i116 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                    modifier2 = modifier14;
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    shape3 = shape4;
                    function7 = function5;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    z4 = z5;
                    j9 = dismissActionContentColor;
                    function8 = function9;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    function7 = function5;
                    function8 = function6;
                    z4 = z2;
                    shape3 = shape2;
                    j6 = j5;
                    j7 = j2;
                    j8 = j3;
                    j9 = j4;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                        public final Object invoke(Object obj, Object obj2) {
                            return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            if ((i3 & 306783379) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function1112 = function5;
                final long j1112 = actionContentColor;
                final boolean z17 = z5;
                final long j1113 = dismissActionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function1113 = function9;
                Modifier modifier15 = modifier3;
                int i117 = i3 >> 9;
                SurfaceKt.m954SurfaceT9BRK9s(modifier15, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                    public final void invoke(Composer composer3, int i118) {
                        if (!composer3.shouldExecute((i118 & 3) != 2, i118 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1343524879, i118, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                        }
                        SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                        TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                        final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                        ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                        final boolean z18 = z17;
                        final Function2<? super Composer, ? super Integer, Unit> function1114 = function1112;
                        final Function2<? super Composer, ? super Integer, Unit> function1115 = function4;
                        final Function2<? super Composer, ? super Integer, Unit> function1116 = function1113;
                        final long j1114 = j1112;
                        final long j1115 = j1113;
                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                            public final void invoke(Composer composer4, int i119) {
                                if (!composer4.shouldExecute((i119 & 3) != 2, i119 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(969655473, i119, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                }
                                if (!z18 || function1114 == null) {
                                    composer4.startReplaceGroup(-168976609);
                                    SnackbarKt.m938OneRowSnackbarkKq0p4A(function1115, function1114, function1116, value2, j1114, j1115, composer4, 0);
                                    composer4.endReplaceGroup();
                                } else {
                                    composer4.startReplaceGroup(-168990288);
                                    SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function1115, function1114, function1116, value2, j1114, j1115, composer4, 0);
                                    composer4.endReplaceGroup();
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i117 & 112) | (i117 & 896) | (i117 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                modifier2 = modifier15;
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                shape3 = shape4;
                function7 = function5;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                z4 = z5;
                j9 = dismissActionContentColor;
                function8 = function9;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                function7 = function5;
                function8 = function6;
                z4 = z2;
                shape3 = shape2;
                j6 = j5;
                j7 = j2;
                j8 = j3;
                j9 = j4;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                    public final Object invoke(Object obj, Object obj2) {
                        return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        function6 = function3;
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
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
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
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j5 = j;
                    if (composerStartRestartGroup.changed(j5)) {
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                i3 |= i15;
            } else {
                j5 = j;
            }
            if ((i & 1572864) != 0) {
                if ((i2 & 64) == 0) {
                    i11 = 524288;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i10 = 4194304;
                } else {
                    i10 = 4194304;
                }
                i3 |= i10;
            }
            if ((100663296 & i) != 0) {
                if ((i2 & 256) == 0) {
                    i9 = 33554432;
                } else {
                    i9 = 33554432;
                }
                i3 |= i9;
            }
            if ((i2 & 512) != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 536870912;
                    } else {
                        i8 = 268435456;
                    }
                    i3 |= i8;
                }
                if ((i3 & 306783379) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1114 = function5;
                    final long j1114 = actionContentColor;
                    final boolean z18 = z5;
                    final long j1115 = dismissActionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function1115 = function9;
                    Modifier modifier16 = modifier3;
                    int i118 = i3 >> 9;
                    SurfaceKt.m954SurfaceT9BRK9s(modifier16, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                        public final void invoke(Composer composer3, int i119) {
                            if (!composer3.shouldExecute((i119 & 3) != 2, i119 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1343524879, i119, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                            }
                            SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                            TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z19 = z18;
                            final Function2<? super Composer, ? super Integer, Unit> function1116 = function1114;
                            final Function2<? super Composer, ? super Integer, Unit> function1117 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function1118 = function1115;
                            final long j1116 = j1114;
                            final long j1117 = j1115;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                                public final void invoke(Composer composer4, int i1110) {
                                    if (!composer4.shouldExecute((i1110 & 3) != 2, i1110 & 1)) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(969655473, i1110, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                    }
                                    if (!z19 || function1116 == null) {
                                        composer4.startReplaceGroup(-168976609);
                                        SnackbarKt.m938OneRowSnackbarkKq0p4A(function1117, function1116, function1118, value2, j1116, j1117, composer4, 0);
                                        composer4.endReplaceGroup();
                                    } else {
                                        composer4.startReplaceGroup(-168990288);
                                        SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function1117, function1116, function1118, value2, j1116, j1117, composer4, 0);
                                        composer4.endReplaceGroup();
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i118 & 112) | (i118 & 896) | (i118 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                    modifier2 = modifier16;
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    shape3 = shape4;
                    function7 = function5;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    z4 = z5;
                    j9 = dismissActionContentColor;
                    function8 = function9;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    function7 = function5;
                    function8 = function6;
                    z4 = z2;
                    shape3 = shape2;
                    j6 = j5;
                    j7 = j2;
                    j8 = j3;
                    j9 = j4;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                        public final Object invoke(Object obj, Object obj2) {
                            return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            if ((i3 & 306783379) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function1116 = function5;
                final long j1116 = actionContentColor;
                final boolean z19 = z5;
                final long j1117 = dismissActionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function1117 = function9;
                Modifier modifier17 = modifier3;
                int i119 = i3 >> 9;
                SurfaceKt.m954SurfaceT9BRK9s(modifier17, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                    public final void invoke(Composer composer3, int i1110) {
                        if (!composer3.shouldExecute((i1110 & 3) != 2, i1110 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1343524879, i1110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                        }
                        SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                        TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                        final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                        ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                        final boolean z110 = z19;
                        final Function2<? super Composer, ? super Integer, Unit> function1118 = function1116;
                        final Function2<? super Composer, ? super Integer, Unit> function1119 = function4;
                        final Function2<? super Composer, ? super Integer, Unit> function11110 = function1117;
                        final long j1118 = j1116;
                        final long j1119 = j1117;
                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                            public final void invoke(Composer composer4, int i1111) {
                                if (!composer4.shouldExecute((i1111 & 3) != 2, i1111 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(969655473, i1111, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                }
                                if (!z110 || function1118 == null) {
                                    composer4.startReplaceGroup(-168976609);
                                    SnackbarKt.m938OneRowSnackbarkKq0p4A(function1119, function1118, function11110, value2, j1118, j1119, composer4, 0);
                                    composer4.endReplaceGroup();
                                } else {
                                    composer4.startReplaceGroup(-168990288);
                                    SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function1119, function1118, function11110, value2, j1118, j1119, composer4, 0);
                                    composer4.endReplaceGroup();
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i119 & 112) | (i119 & 896) | (i119 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                modifier2 = modifier17;
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                shape3 = shape4;
                function7 = function5;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                z4 = z5;
                j9 = dismissActionContentColor;
                function8 = function9;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                function7 = function5;
                function8 = function6;
                z4 = z2;
                shape3 = shape2;
                j6 = j5;
                j7 = j2;
                j8 = j3;
                j9 = j4;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                    public final Object invoke(Object obj, Object obj2) {
                        return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
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
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                j5 = j;
                if (composerStartRestartGroup.changed(j5)) {
                }
                i3 |= i15;
            } else {
                j5 = j;
            }
            i3 |= i15;
        } else {
            j5 = j;
        }
        if ((i & 1572864) != 0) {
            if ((i2 & 64) == 0) {
                i11 = 524288;
            } else {
                i11 = 524288;
            }
            i3 |= i11;
        }
        if ((i & 12582912) != 0) {
            if ((i2 & 128) == 0) {
                i10 = 4194304;
            } else {
                i10 = 4194304;
            }
            i3 |= i10;
        }
        if ((100663296 & i) != 0) {
            if ((i2 & 256) == 0) {
                i9 = 33554432;
            } else {
                i9 = 33554432;
            }
            i3 |= i9;
        }
        if ((i2 & 512) != 0) {
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i8 = 536870912;
                } else {
                    i8 = 268435456;
                }
                i3 |= i8;
            }
            if ((i3 & 306783379) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
                }
                final Function2<? super Composer, ? super Integer, Unit> function1118 = function5;
                final long j1118 = actionContentColor;
                final boolean z110 = z5;
                final long j1119 = dismissActionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function1119 = function9;
                Modifier modifier18 = modifier3;
                int i1110 = i3 >> 9;
                SurfaceKt.m954SurfaceT9BRK9s(modifier18, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                    public final void invoke(Composer composer3, int i1111) {
                        if (!composer3.shouldExecute((i1111 & 3) != 2, i1111 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1343524879, i1111, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                        }
                        SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                        TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                        final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                        ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                        final boolean z111 = z110;
                        final Function2<? super Composer, ? super Integer, Unit> function11110 = function1118;
                        final Function2<? super Composer, ? super Integer, Unit> function11111 = function4;
                        final Function2<? super Composer, ? super Integer, Unit> function11112 = function1119;
                        final long j11110 = j1118;
                        final long j11111 = j1119;
                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                            public final void invoke(Composer composer4, int i1112) {
                                if (!composer4.shouldExecute((i1112 & 3) != 2, i1112 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(969655473, i1112, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                                }
                                if (!z111 || function11110 == null) {
                                    composer4.startReplaceGroup(-168976609);
                                    SnackbarKt.m938OneRowSnackbarkKq0p4A(function11111, function11110, function11112, value2, j11110, j11111, composer4, 0);
                                    composer4.endReplaceGroup();
                                } else {
                                    composer4.startReplaceGroup(-168990288);
                                    SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function11111, function11110, function11112, value2, j11110, j11111, composer4, 0);
                                    composer4.endReplaceGroup();
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i1110 & 112) | (i1110 & 896) | (i1110 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
                modifier2 = modifier18;
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                shape3 = shape4;
                function7 = function5;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                z4 = z5;
                j9 = dismissActionContentColor;
                function8 = function9;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                function7 = function5;
                function8 = function6;
                z4 = z2;
                shape3 = shape2;
                j6 = j5;
                j7 = j2;
                j8 = j3;
                j9 = j4;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                    public final Object invoke(Object obj, Object obj2) {
                        return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 805306368;
        if ((i3 & 306783379) != 306783378) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i13 != 0) {
                    function5 = null;
                }
                if (i4 == 0) {
                }
                if (i6 != 0) {
                    z5 = false;
                } else {
                    z5 = z2;
                }
                if ((i2 & 16) != 0) {
                    shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    shape4 = shape2;
                }
                if ((i2 & 32) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i3 &= -458753;
                } else {
                    color = j5;
                }
                if ((i2 & 64) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    contentColor = j2;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i3 &= -29360129;
                } else {
                    actionContentColor = j3;
                }
                if ((i2 & 256) != 0) {
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    i3 &= -234881025;
                } else {
                    dismissActionContentColor = j4;
                }
            } else {
                if (i12 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i13 != 0) {
                    function5 = null;
                }
                if (i4 == 0) {
                }
                if (i6 != 0) {
                    z5 = false;
                } else {
                    z5 = z2;
                }
                if ((i2 & 16) != 0) {
                    shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    shape4 = shape2;
                }
                if ((i2 & 32) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i3 &= -458753;
                } else {
                    color = j5;
                }
                if ((i2 & 64) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    contentColor = j2;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i3 &= -29360129;
                } else {
                    actionContentColor = j3;
                }
                if ((i2 & 256) != 0) {
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    i3 &= -234881025;
                } else {
                    dismissActionContentColor = j4;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1218779924, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:110)");
            }
            final Function2<? super Composer, ? super Integer, Unit> function11110 = function5;
            final long j11110 = actionContentColor;
            final boolean z111 = z5;
            final long j11111 = dismissActionContentColor;
            final Function2<? super Composer, ? super Integer, Unit> function11111 = function9;
            Modifier modifier19 = modifier3;
            int i1111 = i3 >> 9;
            SurfaceKt.m954SurfaceT9BRK9s(modifier19, shape4, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m2125getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1343524879, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
                public final void invoke(Composer composer3, int i1112) {
                    if (!composer3.shouldExecute((i1112 & 3) != 2, i1112 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1343524879, i1112, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:118)");
                    }
                    SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
                    TextStyle value = TypographyKt.getValue(snackbarTokens.getSupportingTextFont(), composer3, 6);
                    final TextStyle value2 = TypographyKt.getValue(snackbarTokens.getActionLabelTextFont(), composer3, 6);
                    ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                    final boolean z112 = z111;
                    final Function2<? super Composer, ? super Integer, Unit> function11112 = function11110;
                    final Function2<? super Composer, ? super Integer, Unit> function11113 = function4;
                    final Function2<? super Composer, ? super Integer, Unit> function11114 = function11111;
                    final long j11112 = j11110;
                    final long j11113 = j11111;
                    CompositionLocalKt.CompositionLocalProvider(providedValueProvides, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(969655473, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
                        public final void invoke(Composer composer4, int i1113) {
                            if (!composer4.shouldExecute((i1113 & 3) != 2, i1113 & 1)) {
                                composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(969655473, i1113, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:121)");
                            }
                            if (!z112 || function11112 == null) {
                                composer4.startReplaceGroup(-168976609);
                                SnackbarKt.m938OneRowSnackbarkKq0p4A(function11113, function11112, function11114, value2, j11112, j11113, composer4, 0);
                                composer4.endReplaceGroup();
                            } else {
                                composer4.startReplaceGroup(-168990288);
                                SnackbarKt.m937NewLineButtonSnackbarkKq0p4A(function11113, function11112, function11114, value2, j11112, j11113, composer4, 0);
                                composer4.endReplaceGroup();
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12779520 | (i1111 & 112) | (i1111 & 896) | (i1111 & V4Signature.MAX_SIGNING_INFOS_SIZE), 80);
            modifier2 = modifier19;
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape4;
            function7 = function5;
            j6 = color;
            j7 = contentColor;
            j8 = actionContentColor;
            z4 = z5;
            j9 = dismissActionContentColor;
            function8 = function9;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier2 = modifier;
            function7 = function5;
            function8 = function6;
            z4 = z2;
            shape3 = shape2;
            j6 = j5;
            j7 = j2;
            j8 = j3;
            j9 = j4;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uhd
                public final Object invoke(Object obj, Object obj2) {
                    return SnackbarKt.d(modifier2, function7, function8, z4, shape3, j6, j7, j8, j9, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0113  */
    /* JADX WARN: Code duplicated, block: B:102:0x0115  */
    /* JADX WARN: Code duplicated, block: B:105:0x011e  */
    /* JADX WARN: Code duplicated, block: B:107:0x0134  */
    /* JADX WARN: Code duplicated, block: B:129:0x0172 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:130:0x0174  */
    /* JADX WARN: Code duplicated, block: B:131:0x0177  */
    /* JADX WARN: Code duplicated, block: B:133:0x017a  */
    /* JADX WARN: Code duplicated, block: B:134:0x017d  */
    /* JADX WARN: Code duplicated, block: B:137:0x0184  */
    /* JADX WARN: Code duplicated, block: B:138:0x018d  */
    /* JADX WARN: Code duplicated, block: B:141:0x0192  */
    /* JADX WARN: Code duplicated, block: B:142:0x019b  */
    /* JADX WARN: Code duplicated, block: B:145:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:146:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:149:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:150:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:153:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:154:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:157:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:159:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:162:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:165:0x020e  */
    /* JADX WARN: Code duplicated, block: B:166:0x0226  */
    /* JADX WARN: Code duplicated, block: B:169:0x023b  */
    /* JADX WARN: Code duplicated, block: B:171:0x0253  */
    /* JADX WARN: Code duplicated, block: B:174:0x029d  */
    /* JADX WARN: Code duplicated, block: B:176:0x02ae  */
    /* JADX WARN: Code duplicated, block: B:179:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:181:? A[RETURN, SYNTHETIC] */
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
    /* JADX WARN: Code duplicated, block: B:56:0x0090  */
    /* JADX WARN: Code duplicated, block: B:59:0x0097  */
    /* JADX WARN: Code duplicated, block: B:61:0x009b  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:72:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:78:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:81:0x00da  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:86:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:93:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:96:0x0103  */
    /* JADX WARN: Code duplicated, block: B:98:0x0107  */
    /* JADX INFO: renamed from: Snackbar-sDKtq54, reason: not valid java name */
    public static final void m940SnackbarsDKtq54(final SnackbarData snackbarData, Modifier modifier, boolean z, Shape shape, long j, long j2, long j3, long j4, long j5, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z2;
        int i5;
        Shape shape2;
        long j6;
        long j7;
        int i6;
        int i7;
        boolean z3;
        Composer composer2;
        final Modifier modifier3;
        final boolean z4;
        final Shape shape3;
        final long j8;
        final long j9;
        final long j10;
        final long j11;
        final long j12;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        boolean z5;
        Shape shape4;
        long color;
        long contentColor;
        long actionColor;
        long actionContentColor;
        long dismissActionContentColor;
        boolean z6;
        String actionLabel;
        ComposableLambda composableLambdaRememberComposableLambda;
        Function2 function2;
        int i8;
        int i9;
        int i10;
        Composer composerStartRestartGroup = composer.startRestartGroup(274621471);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(snackbarData) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i11 = i2 & 2;
        if (i11 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        shape2 = shape;
                        int i12 = composerStartRestartGroup.changed(shape2) ? 2048 : 1024;
                        i3 |= i12;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i12;
                } else {
                    shape2 = shape;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        j6 = j;
                        int i13 = composerStartRestartGroup.changed(j6) ? 16384 : 8192;
                        i3 |= i13;
                    } else {
                        j6 = j;
                    }
                    i3 |= i13;
                } else {
                    j6 = j;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j7 = j2;
                        int i14 = composerStartRestartGroup.changed(j7) ? 131072 : 65536;
                        i3 |= i14;
                    } else {
                        j7 = j2;
                    }
                    i3 |= i14;
                } else {
                    j7 = j2;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        i10 = i3;
                        i7 = i11;
                        int i15 = composerStartRestartGroup.changed(j3) ? 1048576 : 524288;
                        i6 = i10 | i15;
                    } else {
                        i10 = i3;
                        i7 = i11;
                    }
                    i6 = i10 | i15;
                } else {
                    i6 = i3;
                    i7 = i11;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0 || !composerStartRestartGroup.changed(j4)) {
                        i9 = 4194304;
                    } else {
                        i9 = 8388608;
                    }
                    i6 |= i9;
                }
                if ((100663296 & i) != 0) {
                    if ((i2 & 256) == 0 || !composerStartRestartGroup.changed(j5)) {
                        i8 = 33554432;
                    } else {
                        i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    }
                    i6 |= i8;
                }
                if ((38347923 & i6) != 38347922) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i6 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i7 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            z5 = false;
                        } else {
                            z5 = z2;
                        }
                        if ((i2 & 8) != 0) {
                            shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i6 &= -7169;
                        } else {
                            shape4 = shape2;
                        }
                        if ((i2 & 16) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i6 &= -57345;
                        } else {
                            color = j6;
                        }
                        if ((i2 & 32) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i6 &= -458753;
                        } else {
                            contentColor = j7;
                        }
                        if ((i2 & 64) != 0) {
                            actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                            i6 &= -3670017;
                        } else {
                            actionColor = j3;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i6 &= -29360129;
                        } else {
                            actionContentColor = j4;
                        }
                        if ((i2 & 256) != 0) {
                            i6 &= -234881025;
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        } else {
                            dismissActionContentColor = j5;
                        }
                        z6 = z5;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 8) != 0) {
                            i6 &= -7169;
                        }
                        if ((i2 & 16) != 0) {
                            i6 &= -57345;
                        }
                        if ((i2 & 32) != 0) {
                            i6 &= -458753;
                        }
                        if ((i2 & 64) != 0) {
                            i6 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i6 &= -29360129;
                        }
                        if ((i2 & 256) != 0) {
                            i6 &= -234881025;
                        }
                        actionContentColor = j4;
                        dismissActionContentColor = j5;
                        modifier3 = modifier2;
                        z6 = z2;
                        shape4 = shape2;
                        color = j6;
                        contentColor = j7;
                        actionColor = j3;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:206)");
                    }
                    actionLabel = snackbarData.getVisuals().getActionLabel();
                    composableLambdaRememberComposableLambda = null;
                    if (actionLabel != null) {
                        composerStartRestartGroup.startReplaceGroup(-663815981);
                        Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new SnackbarKt$Snackbar$actionComposable$1(actionColor, snackbarData, actionLabel), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function2 = function2RememberComposableLambda;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-663517017);
                        composerStartRestartGroup.endReplaceGroup();
                        function2 = null;
                    }
                    if (snackbarData.getVisuals().getWithDismissAction()) {
                        composerStartRestartGroup.startReplaceGroup(-663364652);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1812633777, true, new SnackbarKt$Snackbar$dismissActionComposable$1(snackbarData), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-662974393);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    int i16 = i6 << 3;
                    composer2 = composerStartRestartGroup;
                    m939SnackbareQBnUkQ(PaddingKt.padding-3ABfNKs(modifier3, Dp.m6022constructorimpl(12.0f)), function2, composableLambdaRememberComposableLambda, z6, shape4, color, contentColor, actionContentColor, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                        public final void invoke(Composer composer3, int i17) {
                            if (!composer3.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1266389126, i17, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:246)");
                            }
                            TextKt.m1097TextNvy7gAk(snackbarData.getVisuals().getMessage(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i16 & 3670016) | (i16 & V4Signature.MAX_SIGNING_INFOS_SIZE) | 805306368 | (57344 & i16) | (458752 & i16) | (29360128 & i6) | (234881024 & i6), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j10 = actionColor;
                    z4 = z6;
                    shape3 = shape4;
                    j8 = color;
                    j9 = contentColor;
                    j11 = actionContentColor;
                    j12 = dismissActionContentColor;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z4 = z2;
                    shape3 = shape2;
                    j8 = j6;
                    j9 = j7;
                    j10 = j3;
                    j11 = j4;
                    j12 = j5;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: shd
                        public final Object invoke(Object obj, Object obj2) {
                            return SnackbarKt.a(snackbarData, modifier3, z4, shape3, j8, j9, j10, j11, j12, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            z2 = z;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i12;
                } else {
                    shape2 = shape;
                }
                i3 |= i12;
            } else {
                shape2 = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    j6 = j;
                    if (composerStartRestartGroup.changed(j6)) {
                    }
                    i3 |= i13;
                } else {
                    j6 = j;
                }
                i3 |= i13;
            } else {
                j6 = j;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j7 = j2;
                    if (composerStartRestartGroup.changed(j7)) {
                    }
                    i3 |= i14;
                } else {
                    j7 = j2;
                }
                i3 |= i14;
            } else {
                j7 = j2;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    i10 = i3;
                    i7 = i11;
                    if (composerStartRestartGroup.changed(j3)) {
                    }
                    i6 = i10 | i15;
                } else {
                    i10 = i3;
                    i7 = i11;
                }
                i6 = i10 | i15;
            } else {
                i6 = i3;
                i7 = i11;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i9 = 4194304;
                } else {
                    i9 = 4194304;
                }
                i6 |= i9;
            }
            if ((100663296 & i) != 0) {
                if ((i2 & 256) == 0) {
                    i8 = 33554432;
                } else {
                    i8 = 33554432;
                }
                i6 |= i8;
            }
            if ((38347923 & i6) != 38347922) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i6 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 &= -29360129;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    } else {
                        dismissActionContentColor = j5;
                    }
                    z6 = z5;
                } else {
                    if (i7 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 &= -29360129;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    } else {
                        dismissActionContentColor = j5;
                    }
                    z6 = z5;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:206)");
                }
                actionLabel = snackbarData.getVisuals().getActionLabel();
                composableLambdaRememberComposableLambda = null;
                if (actionLabel != null) {
                    composerStartRestartGroup.startReplaceGroup(-663815981);
                    Function2 function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new SnackbarKt$Snackbar$actionComposable$1(actionColor, snackbarData, actionLabel), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                    function2 = function2RememberComposableLambda2;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-663517017);
                    composerStartRestartGroup.endReplaceGroup();
                    function2 = null;
                }
                if (snackbarData.getVisuals().getWithDismissAction()) {
                    composerStartRestartGroup.startReplaceGroup(-663364652);
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1812633777, true, new SnackbarKt$Snackbar$dismissActionComposable$1(snackbarData), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-662974393);
                    composerStartRestartGroup.endReplaceGroup();
                }
                int i17 = i6 << 3;
                composer2 = composerStartRestartGroup;
                m939SnackbareQBnUkQ(PaddingKt.padding-3ABfNKs(modifier3, Dp.m6022constructorimpl(12.0f)), function2, composableLambdaRememberComposableLambda, z6, shape4, color, contentColor, actionContentColor, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                    public final void invoke(Composer composer3, int i18) {
                        if (!composer3.shouldExecute((i18 & 3) != 2, i18 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1266389126, i18, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:246)");
                        }
                        TextKt.m1097TextNvy7gAk(snackbarData.getVisuals().getMessage(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i17 & 3670016) | (i17 & V4Signature.MAX_SIGNING_INFOS_SIZE) | 805306368 | (57344 & i17) | (458752 & i17) | (29360128 & i6) | (234881024 & i6), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j10 = actionColor;
                z4 = z6;
                shape3 = shape4;
                j8 = color;
                j9 = contentColor;
                j11 = actionContentColor;
                j12 = dismissActionContentColor;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                z4 = z2;
                shape3 = shape2;
                j8 = j6;
                j9 = j7;
                j10 = j3;
                j11 = j4;
                j12 = j5;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: shd
                    public final Object invoke(Object obj, Object obj2) {
                        return SnackbarKt.a(snackbarData, modifier3, z4, shape3, j8, j9, j10, j11, j12, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i12;
                } else {
                    shape2 = shape;
                }
                i3 |= i12;
            } else {
                shape2 = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    j6 = j;
                    if (composerStartRestartGroup.changed(j6)) {
                    }
                    i3 |= i13;
                } else {
                    j6 = j;
                }
                i3 |= i13;
            } else {
                j6 = j;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j7 = j2;
                    if (composerStartRestartGroup.changed(j7)) {
                    }
                    i3 |= i14;
                } else {
                    j7 = j2;
                }
                i3 |= i14;
            } else {
                j7 = j2;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    i10 = i3;
                    i7 = i11;
                    if (composerStartRestartGroup.changed(j3)) {
                    }
                    i6 = i10 | i15;
                } else {
                    i10 = i3;
                    i7 = i11;
                }
                i6 = i10 | i15;
            } else {
                i6 = i3;
                i7 = i11;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i9 = 4194304;
                } else {
                    i9 = 4194304;
                }
                i6 |= i9;
            }
            if ((100663296 & i) != 0) {
                if ((i2 & 256) == 0) {
                    i8 = 33554432;
                } else {
                    i8 = 33554432;
                }
                i6 |= i8;
            }
            if ((38347923 & i6) != 38347922) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i6 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 &= -29360129;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    } else {
                        dismissActionContentColor = j5;
                    }
                    z6 = z5;
                } else {
                    if (i7 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        z5 = false;
                    } else {
                        z5 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape4 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 &= -29360129;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    } else {
                        dismissActionContentColor = j5;
                    }
                    z6 = z5;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:206)");
                }
                actionLabel = snackbarData.getVisuals().getActionLabel();
                composableLambdaRememberComposableLambda = null;
                if (actionLabel != null) {
                    composerStartRestartGroup.startReplaceGroup(-663815981);
                    Function2 function2RememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new SnackbarKt$Snackbar$actionComposable$1(actionColor, snackbarData, actionLabel), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                    function2 = function2RememberComposableLambda3;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-663517017);
                    composerStartRestartGroup.endReplaceGroup();
                    function2 = null;
                }
                if (snackbarData.getVisuals().getWithDismissAction()) {
                    composerStartRestartGroup.startReplaceGroup(-663364652);
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1812633777, true, new SnackbarKt$Snackbar$dismissActionComposable$1(snackbarData), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-662974393);
                    composerStartRestartGroup.endReplaceGroup();
                }
                int i18 = i6 << 3;
                composer2 = composerStartRestartGroup;
                m939SnackbareQBnUkQ(PaddingKt.padding-3ABfNKs(modifier3, Dp.m6022constructorimpl(12.0f)), function2, composableLambdaRememberComposableLambda, z6, shape4, color, contentColor, actionContentColor, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                    public final void invoke(Composer composer3, int i19) {
                        if (!composer3.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1266389126, i19, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:246)");
                        }
                        TextKt.m1097TextNvy7gAk(snackbarData.getVisuals().getMessage(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i18 & 3670016) | (i18 & V4Signature.MAX_SIGNING_INFOS_SIZE) | 805306368 | (57344 & i18) | (458752 & i18) | (29360128 & i6) | (234881024 & i6), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j10 = actionColor;
                z4 = z6;
                shape3 = shape4;
                j8 = color;
                j9 = contentColor;
                j11 = actionContentColor;
                j12 = dismissActionContentColor;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                z4 = z2;
                shape3 = shape2;
                j8 = j6;
                j9 = j7;
                j10 = j3;
                j11 = j4;
                j12 = j5;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: shd
                    public final Object invoke(Object obj, Object obj2) {
                        return SnackbarKt.a(snackbarData, modifier3, z4, shape3, j8, j9, j10, j11, j12, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        z2 = z;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i3 |= i12;
            } else {
                shape2 = shape;
            }
            i3 |= i12;
        } else {
            shape2 = shape;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                j6 = j;
                if (composerStartRestartGroup.changed(j6)) {
                }
                i3 |= i13;
            } else {
                j6 = j;
            }
            i3 |= i13;
        } else {
            j6 = j;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                j7 = j2;
                if (composerStartRestartGroup.changed(j7)) {
                }
                i3 |= i14;
            } else {
                j7 = j2;
            }
            i3 |= i14;
        } else {
            j7 = j2;
        }
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                i10 = i3;
                i7 = i11;
                if (composerStartRestartGroup.changed(j3)) {
                }
                i6 = i10 | i15;
            } else {
                i10 = i3;
                i7 = i11;
            }
            i6 = i10 | i15;
        } else {
            i6 = i3;
            i7 = i11;
        }
        if ((i & 12582912) != 0) {
            if ((i2 & 128) == 0) {
                i9 = 4194304;
            } else {
                i9 = 4194304;
            }
            i6 |= i9;
        }
        if ((100663296 & i) != 0) {
            if ((i2 & 256) == 0) {
                i8 = 33554432;
            } else {
                i8 = 33554432;
            }
            i6 |= i8;
        }
        if ((38347923 & i6) != 38347922) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i6 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i7 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    z5 = false;
                } else {
                    z5 = z2;
                }
                if ((i2 & 8) != 0) {
                    shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i6 &= -7169;
                } else {
                    shape4 = shape2;
                }
                if ((i2 & 16) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i6 &= -57345;
                } else {
                    color = j6;
                }
                if ((i2 & 32) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i6 &= -458753;
                } else {
                    contentColor = j7;
                }
                if ((i2 & 64) != 0) {
                    actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                    i6 &= -3670017;
                } else {
                    actionColor = j3;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i6 &= -29360129;
                } else {
                    actionContentColor = j4;
                }
                if ((i2 & 256) != 0) {
                    i6 &= -234881025;
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                } else {
                    dismissActionContentColor = j5;
                }
                z6 = z5;
            } else {
                if (i7 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    z5 = false;
                } else {
                    z5 = z2;
                }
                if ((i2 & 8) != 0) {
                    shape4 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i6 &= -7169;
                } else {
                    shape4 = shape2;
                }
                if ((i2 & 16) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i6 &= -57345;
                } else {
                    color = j6;
                }
                if ((i2 & 32) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i6 &= -458753;
                } else {
                    contentColor = j7;
                }
                if ((i2 & 64) != 0) {
                    actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                    i6 &= -3670017;
                } else {
                    actionColor = j3;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i6 &= -29360129;
                } else {
                    actionContentColor = j4;
                }
                if ((i2 & 256) != 0) {
                    i6 &= -234881025;
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                } else {
                    dismissActionContentColor = j5;
                }
                z6 = z5;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:206)");
            }
            actionLabel = snackbarData.getVisuals().getActionLabel();
            composableLambdaRememberComposableLambda = null;
            if (actionLabel != null) {
                composerStartRestartGroup.startReplaceGroup(-663815981);
                Function2 function2RememberComposableLambda4 = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new SnackbarKt$Snackbar$actionComposable$1(actionColor, snackbarData, actionLabel), composerStartRestartGroup, 54);
                composerStartRestartGroup.endReplaceGroup();
                function2 = function2RememberComposableLambda4;
            } else {
                composerStartRestartGroup.startReplaceGroup(-663517017);
                composerStartRestartGroup.endReplaceGroup();
                function2 = null;
            }
            if (snackbarData.getVisuals().getWithDismissAction()) {
                composerStartRestartGroup.startReplaceGroup(-663364652);
                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1812633777, true, new SnackbarKt$Snackbar$dismissActionComposable$1(snackbarData), composerStartRestartGroup, 54);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-662974393);
                composerStartRestartGroup.endReplaceGroup();
            }
            int i19 = i6 << 3;
            composer2 = composerStartRestartGroup;
            m939SnackbareQBnUkQ(PaddingKt.padding-3ABfNKs(modifier3, Dp.m6022constructorimpl(12.0f)), function2, composableLambdaRememberComposableLambda, z6, shape4, color, contentColor, actionContentColor, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                public final void invoke(Composer composer3, int i110) {
                    if (!composer3.shouldExecute((i110 & 3) != 2, i110 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1266389126, i110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:246)");
                    }
                    TextKt.m1097TextNvy7gAk(snackbarData.getVisuals().getMessage(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composer2, (i19 & 3670016) | (i19 & V4Signature.MAX_SIGNING_INFOS_SIZE) | 805306368 | (57344 & i19) | (458752 & i19) | (29360128 & i6) | (234881024 & i6), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j10 = actionColor;
            z4 = z6;
            shape3 = shape4;
            j8 = color;
            j9 = contentColor;
            j11 = actionContentColor;
            j12 = dismissActionContentColor;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            z4 = z2;
            shape3 = shape2;
            j8 = j6;
            j9 = j7;
            j10 = j3;
            j11 = j4;
            j12 = j5;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: shd
                public final Object invoke(Object obj, Object obj2) {
                    return SnackbarKt.a(snackbarData, modifier3, z4, shape3, j8, j9, j10, j11, j12, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(SnackbarData snackbarData, Modifier modifier, boolean z, Shape shape, long j, long j2, long j3, long j4, long j5, int i, int i2, Composer composer, int i3) {
        m940SnackbarsDKtq54(snackbarData, modifier, z, shape, j, j2, j3, j4, j5, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(Function2 function2, Function2 function3, Function2 function4, TextStyle textStyle, long j, long j2, int i, Composer composer, int i2) {
        m937NewLineButtonSnackbarkKq0p4A(function2, function3, function4, textStyle, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit c(Function2 function2, Function2 function3, Function2 function4, TextStyle textStyle, long j, long j2, int i, Composer composer, int i2) {
        m938OneRowSnackbarkKq0p4A(function2, function3, function4, textStyle, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit d(Modifier modifier, Function2 function2, Function2 function3, boolean z, Shape shape, long j, long j2, long j3, long j4, Function2 function4, int i, int i2, Composer composer, int i3) {
        m939SnackbareQBnUkQ(modifier, function2, function3, z, shape, j, j2, j3, j4, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }
}
