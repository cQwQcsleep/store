package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ModalBottomSheetKt$ModalBottomSheetContent$7 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Function0<Unit> $animateToDismiss;
    final /* synthetic */ Function3<ColumnScope, Composer, Integer, Unit> $content;
    final /* synthetic */ Function2<Composer, Integer, WindowInsets> $contentWindowInsets;
    final /* synthetic */ Function2<Composer, Integer, Unit> $dragHandle;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ boolean $sheetGesturesEnabled;
    final /* synthetic */ SheetState $sheetState;

    /* JADX WARN: Multi-variable type inference failed */
    public ModalBottomSheetKt$ModalBottomSheetContent$7(Function2<? super Composer, ? super Integer, ? extends WindowInsets> function2, Animatable<Float, AnimationVector1D> animatable, SheetState sheetState, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4, Function0<Unit> function0, CoroutineScope coroutineScope, boolean z) {
        this.$contentWindowInsets = function2;
        this.$predictiveBackProgress = animatable;
        this.$sheetState = sheetState;
        this.$dragHandle = function3;
        this.$content = function4;
        this.$animateToDismiss = function0;
        this.$scope = coroutineScope;
        this.$sheetGesturesEnabled = z;
    }

    public static Unit a(Animatable animatable, GraphicsLayerScope graphicsLayerScope) {
        float fFloatValue = ((Number) animatable.getValue()).floatValue();
        float fCalculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, fFloatValue);
        float fCalculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, fFloatValue);
        graphicsLayerScope.setScaleY(fCalculatePredictiveBackScaleY == 0.0f ? 1.0f : fCalculatePredictiveBackScaleX / fCalculatePredictiveBackScaleY);
        graphicsLayerScope.mo3335setTransformOrigin__ExYCQ(ModalBottomSheetKt.PredictiveBackChildTransformOrigin);
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(728743275, i, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:359)");
        }
        Modifier modifierWindowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, (Object) null), (WindowInsets) this.$contentWindowInsets.invoke(composer, 0));
        boolean zChangedInstance = composer.changedInstance(this.$predictiveBackProgress);
        final Animatable<Float, AnimationVector1D> animatable = this.$predictiveBackProgress;
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function1() { // from class: androidx.compose.material3.e2
                public final Object invoke(Object obj) {
                    return ModalBottomSheetKt$ModalBottomSheetContent$7.a(animatable, (GraphicsLayerScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierVerticalScaleDown = BottomSheetScaffoldKt.verticalScaleDown(GraphicsLayerModifierKt.graphicsLayer(modifierWindowInsetsPadding, (Function1) objRememberedValue), this.$sheetState);
        Function2<Composer, Integer, Unit> function2 = this.$dragHandle;
        Function3<ColumnScope, Composer, Integer, Unit> function3 = this.$content;
        SheetState sheetState = this.$sheetState;
        Function0<Unit> function0 = this.$animateToDismiss;
        CoroutineScope coroutineScope = this.$scope;
        boolean z = this.$sheetGesturesEnabled;
        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierVerticalScaleDown);
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        Function0<ComposeUiNode> constructor = companion.getConstructor();
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
        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
        }
        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        if (function2 != null) {
            composer.startReplaceGroup(1352934765);
            Strings.Companion companion2 = Strings.INSTANCE;
            SheetDefaultsKt.DragHandleWithTooltip(columnScopeInstance, ComposableLambdaKt.rememberComposableLambda(2000500644, true, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1(sheetState, function0, coroutineScope, z, Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer, 0), Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer, 0), Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer, 0), function2), composer, 54), composer, 54);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(1356009965);
            composer.endReplaceGroup();
        }
        function3.invoke(columnScopeInstance, composer, 6);
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
