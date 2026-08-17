package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SnackbarHostKt$FadeInFadeOutWithScale$1$1 implements Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit> {
    final /* synthetic */ String $a11yPaneTitle;
    final /* synthetic */ SnackbarData $current;
    final /* synthetic */ SnackbarData $key;
    final /* synthetic */ FadeInFadeOutState<SnackbarData> $state;

    public SnackbarHostKt$FadeInFadeOutWithScale$1$1(SnackbarData snackbarData, SnackbarData snackbarData2, FadeInFadeOutState<SnackbarData> fadeInFadeOutState, String str) {
        this.$key = snackbarData;
        this.$current = snackbarData2;
        this.$state = fadeInFadeOutState;
        this.$a11yPaneTitle = str;
    }

    public static Unit a(boolean z, String str, final SnackbarData snackbarData, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (z) {
            SemanticsPropertiesKt.m5263setLiveRegionhR3wRGc(semanticsPropertyReceiver, LiveRegionMode.INSTANCE.m5237getPolite0phEisY());
        }
        SemanticsPropertiesKt.dismiss$default(semanticsPropertyReceiver, null, new Function0() { // from class: androidx.compose.material3.a4
            public final Object invoke() {
                return Boolean.valueOf(SnackbarHostKt$FadeInFadeOutWithScale$1$1.b(snackbarData));
            }
        }, 1, null);
        SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, str);
        return Unit.INSTANCE;
    }

    public static boolean b(SnackbarData snackbarData) {
        snackbarData.dismiss();
        return true;
    }

    public static boolean c(SnackbarData snackbarData, FadeInFadeOutAnimationItem fadeInFadeOutAnimationItem) {
        return Intrinsics.areEqual(fadeInFadeOutAnimationItem.getKey(), snackbarData);
    }

    public static Unit d(final SnackbarData snackbarData, FadeInFadeOutState fadeInFadeOutState) {
        if (!Intrinsics.areEqual(snackbarData, fadeInFadeOutState.getCurrent())) {
            CollectionsKt.removeAll(fadeInFadeOutState.getItems(), new Function1() { // from class: androidx.compose.material3.b4
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(SnackbarHostKt$FadeInFadeOutWithScale$1$1.c(snackbarData, (FadeInFadeOutAnimationItem) obj));
                }
            });
            RecomposeScope scope = fadeInFadeOutState.getScope();
            if (scope != null) {
                scope.invalidate();
            }
        }
        return Unit.INSTANCE;
    }

    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, int i) {
        int i2;
        if ((i & 6) == 0) {
            i2 = i | (composer.changedInstance(function2) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (!composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1952400805, i2, -1, "androidx.compose.material3.FadeInFadeOutWithScale.<anonymous>.<anonymous> (SnackbarHost.kt:338)");
        }
        final boolean zAreEqual = Intrinsics.areEqual(this.$key, this.$current);
        FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, composer, 6);
        boolean zChanged = composer.changed(this.$key) | composer.changedInstance(this.$state);
        final SnackbarData snackbarData = this.$key;
        final FadeInFadeOutState<SnackbarData> fadeInFadeOutState = this.$state;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: androidx.compose.material3.c4
                public final Object invoke() {
                    return SnackbarHostKt$FadeInFadeOutWithScale$1$1.d(snackbarData, fadeInFadeOutState);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        State stateAnimatedOpacity = SnackbarHostKt.animatedOpacity(finiteAnimationSpecValue, zAreEqual, (Function0) objRememberedValue, composer, 0, 0);
        State stateAnimatedScale = SnackbarHostKt.animatedScale(MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composer, 6), zAreEqual, composer, 0);
        Modifier modifierM3296graphicsLayerAp8cVGQ = GraphicsLayerModifierKt.m3296graphicsLayerAp8cVGQ(Modifier.INSTANCE, (124895 & 1) != 0 ? 1.0f : ((Number) stateAnimatedScale.getValue()).floatValue(), (124895 & 2) != 0 ? 1.0f : ((Number) stateAnimatedScale.getValue()).floatValue(), (124895 & 4) == 0 ? ((Number) stateAnimatedOpacity.getValue()).floatValue() : 1.0f, (124895 & 8) != 0 ? 0.0f : 0.0f, (124895 & 16) != 0 ? 0.0f : 0.0f, (124895 & 32) != 0 ? 0.0f : 0.0f, (124895 & 64) != 0 ? 0.0f : 0.0f, (124895 & 128) != 0 ? 0.0f : 0.0f, (124895 & 256) == 0 ? 0.0f : 0.0f, (124895 & 512) != 0 ? 8.0f : 0.0f, (124895 & 1024) != 0 ? TransformOrigin.INSTANCE.m3547getCenterSzJe1aQ() : 0L, (124895 & 2048) != 0 ? RectangleShapeKt.getRectangleShape() : null, (124895 & 4096) != 0 ? false : false, (124895 & 8192) != 0 ? null : null, (124895 & 16384) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L, (32768 & 124895) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L, (124895 & 65536) != 0 ? CompositingStrategy.INSTANCE.m3223getAutoNrFUSI() : 0);
        boolean zChanged2 = composer.changed(zAreEqual) | composer.changed(this.$key) | composer.changed(this.$a11yPaneTitle);
        final String str = this.$a11yPaneTitle;
        final SnackbarData snackbarData2 = this.$key;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.d4
                public final Object invoke(Object obj) {
                    return SnackbarHostKt$FadeInFadeOutWithScale$1$1.a(zAreEqual, str, snackbarData2, (SemanticsPropertyReceiver) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierM3296graphicsLayerAp8cVGQ, false, (Function1) objRememberedValue2, 1, null);
        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierSemantics$default);
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
        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
        }
        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        function2.invoke(composer, Integer.valueOf(i2 & 14));
        composer.endNode();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
        invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
        return Unit.INSTANCE;
    }
}
