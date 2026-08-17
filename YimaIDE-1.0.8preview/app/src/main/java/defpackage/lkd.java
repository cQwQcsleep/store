package defpackage;

import android.content.Context;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingFunctionsKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.animation.core.StartOffset;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import java.util.Map;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class lkd {
    public static final long a = ColorKt.Color(4280640491L);
    public static final long b = ColorKt.Color(4292602622L);
    public static final long c = ColorKt.Color(4279310375L);
    public static final long d = ColorKt.Color(4288455599L);
    public static final long e = ColorKt.Color(4291086804L);
    public static final float f;
    public static final float g;

    static {
        float f2 = Dp.constructor-impl(288.0f);
        f = f2;
        g = Dp.constructor-impl(Dp.constructor-impl(f2 * 0.35f) / 2.0f);
    }

    public static Unit a(String str, BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, int i) {
        int i2;
        boxWithConstraintsScope.getClass();
        if ((i & 6) == 0) {
            i2 = i | (composer.changed(boxWithConstraintsScope) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(253306389, i2, -1, "com.yimaide.app.ui.SplashScreen.<anonymous> (SplashScreen.kt:79)");
            }
            float f2 = Dp.constructor-impl(boxWithConstraintsScope.getMaxHeight-D9Ej5fM() / 2.0f);
            Painter painterPainterResource = PainterResources_androidKt.painterResource(b2c.f, composer, 0);
            ContentScale fit = ContentScale.Companion.getFit();
            Modifier.Companion companion = Modifier.Companion;
            Alignment.Companion companion2 = Alignment.Companion;
            ImageKt.Image(painterPainterResource, (String) null, SizeKt.size-3ABfNKs(boxWithConstraintsScope.align(companion, companion2.getCenter()), f), (Alignment) null, fit, 0.0f, (ColorFilter) null, composer, Painter.$stable | 24624, 104);
            Alignment.Horizontal centerHorizontally = companion2.getCenterHorizontally();
            Modifier modifier = PaddingKt.padding-qDBjuR0$default(boxWithConstraintsScope.align(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null), companion2.getTopCenter()), 0.0f, Dp.constructor-impl(Dp.constructor-impl(f2 + g) + Dp.constructor-impl(26.0f)), 0.0f, 0.0f, 13, (Object) null);
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, composer, 48);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
            ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
            Function0 constructor = companion3.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composer);
            Updater.set-impl(composer2, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion3.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, companion3.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, modifierMaterializeModifier, companion3.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            AnnotatedString annotatedStringG = g();
            long sp = TextUnitKt.getSp(25);
            FontWeight.Companion companion4 = FontWeight.Companion;
            TextKt.Text-Z58ophY(annotatedStringG, (Modifier) null, c, (TextAutoSize) null, sp, (FontStyle) null, companion4.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Map) null, (Function1) null, (TextStyle) null, composer, 1597824, 0, 524202);
            TextKt.Text-Nvy7gAk("手机端 Android 开发", PaddingKt.padding-qDBjuR0$default(companion, 0.0f, Dp.constructor-impl(10.0f), 0.0f, 0.0f, 13, (Object) null), d, (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, companion4.getMedium(), (FontFamily) null, TextUnitKt.getSp(1.5d), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 102261174, 0, 261800);
            Composer composer3 = composer;
            composer3.endNode();
            d(PaddingKt.padding-qDBjuR0$default(boxWithConstraintsScope.align(companion, companion2.getBottomCenter()), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(64.0f), 7, (Object) null), composer3, 0, 0);
            if (StringsKt.isBlank(str)) {
                composer3.startReplaceGroup(-9747443);
            } else {
                composer3.startReplaceGroup(-5629682);
                TextKt.Text-Nvy7gAk("v" + str, PaddingKt.padding-qDBjuR0$default(boxWithConstraintsScope.align(companion, companion2.getBottomCenter()), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(26.0f), 7, (Object) null), e, (TextAutoSize) null, TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24960, 0, 262120);
                composer3 = composer;
            }
            composer3.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(Modifier modifier, int i, int i2, Composer composer, int i3) {
        d(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit c(Modifier modifier, int i, int i2, Composer composer, int i3) {
        f(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void d(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-758601534);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-758601534, i3, -1, "com.yimaide.app.ui.LoadingDots (SplashScreen.kt:133)");
            }
            InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition("loading_dots", composerStartRestartGroup, 6, 0);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(7.0f)), Alignment.Companion.getTop(), composerStartRestartGroup, 6);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion = ComposeUiNode.Companion;
            Function0 constructor = companion.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer2, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, modifierMaterializeModifier, companion.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(1582189514);
            for (int i5 = 0; i5 < 3; i5++) {
                State stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.infiniteRepeatable-9IiC70o(AnimationSpecKt.tween$default(600, 0, EasingFunctionsKt.getEaseInOut(), 2, (Object) null), RepeatMode.Reverse, StartOffset.constructor-impl$default(i5 * 200, 0, 2, (DefaultConstructorMarker) null)), "dot_progress_" + i5, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 0);
                BoxKt.Box(BackgroundKt.background-bw27NRU(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(((e(stateAnimateFloat) * 0.35f) + 0.8f) * 7.0f)), ColorKt.lerp-jxsXWHM(b, a, e(stateAnimateFloat)), RoundedCornerShapeKt.getCircleShape()), composerStartRestartGroup, 0);
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kkd
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return lkd.b(modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final float e(State state) {
        return ((Number) state.getValue()).floatValue();
    }

    public static final void f(Modifier modifier, Composer composer, final int i, final int i2) {
        final Modifier modifier2;
        int i3;
        Object objM38constructorimpl;
        Composer composerStartRestartGroup = composer.startRestartGroup(-935233877);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            Modifier modifier3 = i4 != 0 ? Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-935233877, i3, -1, "com.yimaide.app.ui.SplashScreen (SplashScreen.kt:66)");
            }
            Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            boolean zChanged = composerStartRestartGroup.changed(context);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM38constructorimpl = Result.m38constructorimpl(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM38constructorimpl = Result.m38constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m44isFailureimpl(objM38constructorimpl)) {
                    objM38constructorimpl = null;
                }
                String str = (String) objM38constructorimpl;
                if (str == null) {
                    str = "";
                }
                objRememberedValue = str;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final String str2 = (String) objRememberedValue;
            BoxWithConstraintsKt.BoxWithConstraints(BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, (Object) null), Color.Companion.getWhite-0d7_KjU(), (Shape) null, 2, (Object) null), (Alignment) null, false, ComposableLambdaKt.rememberComposableLambda(253306389, true, new Function3() { // from class: ikd
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return lkd.a(str2, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jkd
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return lkd.c(modifier2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final AnnotatedString g() {
        AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, (DefaultConstructorMarker) null);
        builder.append("Yima ");
        int iPushStyle = builder.pushStyle(new SpanStyle(a, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65534, (DefaultConstructorMarker) null));
        try {
            builder.append("IDE");
            Unit unit = Unit.INSTANCE;
            return builder.toAnnotatedString();
        } finally {
            builder.pop(iPushStyle);
        }
    }
}
