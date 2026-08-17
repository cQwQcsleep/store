package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.internal.AccessibilityUtilKt;
import androidx.compose.material3.tokens.CircularProgressIndicatorTokens;
import androidx.compose.material3.tokens.LinearProgressIndicatorTokens;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.app.NotificationCompat;
import androidx.profileinstaller.ProfileVerifier;
import androidx.window.core.layout.WindowSizeClass;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\u001aE\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\r\u001aj\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a7\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001aA\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001a?\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\u001a\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a-\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001a;\u0010\u001f\u001a\u00020\u0001*\u00020\u00122\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0004\b#\u0010$\u001aO\u0010%\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b&\u0010'\u001aY\u0010%\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b(\u0010)\u001aA\u0010%\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b*\u0010+\u001aK\u0010%\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b,\u0010-\u001aI\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b&\u0010.\u001a5\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000fH\u0007¢\u0006\u0004\b/\u00100\u001a-\u0010%\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000fH\u0007¢\u0006\u0004\b1\u00102\u001a3\u00103\u001a\u00020\u0001*\u00020\u00122\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b8\u00109\u001a#\u0010:\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b;\u0010<\u001a3\u0010=\u001a\u00020\u0001*\u00020\u00122\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b>\u00109\u001a;\u0010?\u001a\u00020\u0001*\u00020\u00122\u0006\u00104\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b@\u0010A\"\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bD\u0010E\"\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010E\"\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bI\u0010E\"\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bK\u0010E\"\u001a\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bM\u0010E\"\u001a\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bO\u0010E\"\u001a\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010E\"\u0016\u0010R\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bS\u0010T\"\u0016\u0010V\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bW\u0010T\"\u0016\u0010X\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bY\u0010T\"\u0016\u0010Z\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\b[\u0010T\"\u000e\u0010\\\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010^\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010_\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010`\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010a\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010b\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010c\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010d\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010e\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u0014\u0010f\u001a\u00020gX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010i\"\u0014\u0010j\u001a\u00020gX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bk\u0010i\"\u000e\u0010l\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010m\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010n\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010o\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010p\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010q\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006s"}, d2 = {"LinearProgressIndicator", "", NotificationCompat.CATEGORY_PROGRESS, "Lkotlin/Function0;", "", "modifier", "Landroidx/compose/ui/Modifier;", "color", "Landroidx/compose/ui/graphics/Color;", "trackColor", "strokeCap", "Landroidx/compose/ui/graphics/StrokeCap;", "LinearProgressIndicator-_5eSR-E", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "gapSize", "Landroidx/compose/ui/unit/Dp;", "drawStopIndicator", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "LinearProgressIndicator-GJbTh5U", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JJIFLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-2cYBFYY", "(Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-rIrjwxo", "(Landroidx/compose/ui/Modifier;JJIFLandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-eaDK9VM", "(FLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-RIQooxk", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "drawLinearIndicator", "startFraction", "endFraction", "strokeWidth", "drawLinearIndicator-qYKTg0g", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJFI)V", "CircularProgressIndicator", "CircularProgressIndicator-DUhRLBM", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-IyT6zlY", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JFJIFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-LxG7B9w", "(Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-4lLiAd8", "(Landroidx/compose/ui/Modifier;JFJIFLandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-MBs18nI", "(FLandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-aM-cp0Q", "(Landroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "drawCircularIndicator", "startAngle", "sweep", "stroke", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "drawCircularIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawCircularIndicatorTrack", "drawCircularIndicatorTrack-bw27NRU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawDeterminateCircularIndicator", "drawDeterminateCircularIndicator-42QJj7c", "drawIndeterminateCircularIndicator", "drawIndeterminateCircularIndicator-hrjfTZI", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "circularIndeterminateGlobalRotationAnimationSpec", "Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "getCircularIndeterminateGlobalRotationAnimationSpec", "()Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "circularIndeterminateRotationAnimationSpec", "getCircularIndeterminateRotationAnimationSpec", "circularIndeterminateProgressAnimationSpec", "getCircularIndeterminateProgressAnimationSpec", "linearIndeterminateFirstLineHeadAnimationSpec", "getLinearIndeterminateFirstLineHeadAnimationSpec", "linearIndeterminateFirstLineTailAnimationSpec", "getLinearIndeterminateFirstLineTailAnimationSpec", "linearIndeterminateSecondLineHeadAnimationSpec", "getLinearIndeterminateSecondLineHeadAnimationSpec", "linearIndeterminateSecondLineTailAnimationSpec", "getLinearIndeterminateSecondLineTailAnimationSpec", "LinearIndicatorWidth", "getLinearIndicatorWidth", "()F", "F", "LinearIndicatorHeight", "getLinearIndicatorHeight", "StopIndicatorTrailingSpace", "getStopIndicatorTrailingSpace", "CircularIndicatorDiameter", "getCircularIndicatorDiameter", "LinearAnimationDuration", "", "FirstLineHeadDuration", "FirstLineTailDuration", "SecondLineHeadDuration", "SecondLineTailDuration", "FirstLineHeadDelay", "FirstLineTailDelay", "SecondLineHeadDelay", "SecondLineTailDelay", "LinearIndeterminateProgressEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "getLinearIndeterminateProgressEasing", "()Landroidx/compose/animation/core/CubicBezierEasing;", "CircularProgressEasing", "getCircularProgressEasing", "CircularIndeterminateMinProgress", "CircularIndeterminateMaxProgress", "CircularAnimationProgressDuration", "CircularAnimationAdditionalRotationDelay", "CircularAnimationAdditionalRotationDuration", "CircularAdditionalRotationDegreesTarget", "CircularGlobalRotationDegreesTarget", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ProgressIndicatorKt {
    public static final float CircularAdditionalRotationDegreesTarget = 360.0f;
    public static final int CircularAnimationAdditionalRotationDelay = 1500;
    public static final int CircularAnimationAdditionalRotationDuration = 300;
    public static final int CircularAnimationProgressDuration = 6000;
    public static final float CircularGlobalRotationDegreesTarget = 1080.0f;
    public static final float CircularIndeterminateMaxProgress = 0.87f;
    public static final float CircularIndeterminateMinProgress = 0.1f;
    private static final CubicBezierEasing CircularProgressEasing;
    public static final int FirstLineHeadDelay = 0;
    public static final int FirstLineHeadDuration = 1000;
    public static final int FirstLineTailDelay = 250;
    public static final int FirstLineTailDuration = 1000;
    public static final int LinearAnimationDuration = 1750;
    private static final CubicBezierEasing LinearIndeterminateProgressEasing;
    public static final int SecondLineHeadDelay = 650;
    public static final int SecondLineHeadDuration = 850;
    public static final int SecondLineTailDelay = 900;
    public static final int SecondLineTailDuration = 850;
    private static final float LinearIndicatorWidth = Dp.m6022constructorimpl(240.0f);
    private static final float LinearIndicatorHeight = LinearProgressIndicatorTokens.INSTANCE.m1879getHeightD9Ej5fM();
    private static final float StopIndicatorTrailingSpace = Dp.m6022constructorimpl(6.0f);
    private static final float CircularIndicatorDiameter = CircularProgressIndicatorTokens.INSTANCE.m1588getSizeD9Ej5fM();

    static {
        MotionTokens motionTokens = MotionTokens.INSTANCE;
        LinearIndeterminateProgressEasing = motionTokens.getEasingEmphasizedAccelerateCubicBezier();
        CircularProgressEasing = motionTokens.getEasingStandardCubicBezier();
    }

    public static Unit A(Modifier modifier, long j, long j2, int i, int i2, Composer composer, int i3) {
        m756LinearProgressIndicatorRIQooxk(modifier, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit B(Function0 function0, Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m758LinearProgressIndicator_5eSRE(function0, modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit C(KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig) {
        keyframesSpecConfig.setDurationMillis(LinearAnimationDuration);
        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), SecondLineHeadDelay), LinearIndeterminateProgressEasing);
        keyframesSpecConfig.at(Float.valueOf(1.0f), CircularAnimationAdditionalRotationDelay);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x012d  */
    /* JADX WARN: Code duplicated, block: B:103:0x0139  */
    /* JADX WARN: Code duplicated, block: B:106:0x0200  */
    /* JADX WARN: Code duplicated, block: B:107:0x0202  */
    /* JADX WARN: Code duplicated, block: B:110:0x020b  */
    /* JADX WARN: Code duplicated, block: B:111:0x020d  */
    /* JADX WARN: Code duplicated, block: B:114:0x0215  */
    /* JADX WARN: Code duplicated, block: B:115:0x0217  */
    /* JADX WARN: Code duplicated, block: B:118:0x022b  */
    /* JADX WARN: Code duplicated, block: B:120:0x0231  */
    /* JADX WARN: Code duplicated, block: B:126:0x0246  */
    /* JADX WARN: Code duplicated, block: B:128:0x024c  */
    /* JADX WARN: Code duplicated, block: B:134:0x025d  */
    /* JADX WARN: Code duplicated, block: B:138:0x026d  */
    /* JADX WARN: Code duplicated, block: B:141:0x0291  */
    /* JADX WARN: Code duplicated, block: B:143:0x02a1  */
    /* JADX WARN: Code duplicated, block: B:146:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:148:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0061  */
    /* JADX WARN: Code duplicated, block: B:41:0x0070  */
    /* JADX WARN: Code duplicated, block: B:43:0x0074  */
    /* JADX WARN: Code duplicated, block: B:46:0x007a  */
    /* JADX WARN: Code duplicated, block: B:48:0x007f  */
    /* JADX WARN: Code duplicated, block: B:50:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x008b  */
    /* JADX WARN: Code duplicated, block: B:53:0x008e  */
    /* JADX WARN: Code duplicated, block: B:57:0x0098  */
    /* JADX WARN: Code duplicated, block: B:58:0x009d  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:67:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:68:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:73:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:83:0x00ec A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:84:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:90:0x0101  */
    /* JADX WARN: Code duplicated, block: B:93:0x010c  */
    /* JADX WARN: Code duplicated, block: B:94:0x0116  */
    /* JADX WARN: Code duplicated, block: B:96:0x0119  */
    /* JADX WARN: Code duplicated, block: B:98:0x0122  */
    /* JADX INFO: renamed from: CircularProgressIndicator-4lLiAd8, reason: not valid java name */
    public static final void m747CircularProgressIndicator4lLiAd8(Modifier modifier, long j, float f, long j2, int i, float f2, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long circularColor;
        float fM743getCircularStrokeWidthD9Ej5fM;
        long j3;
        int i5;
        int iM741getCircularIndeterminateStrokeCapKaPHkGw;
        int i6;
        int i7;
        float f3;
        int i8;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final long j4;
        final float f4;
        final float f5;
        final int i9;
        final long j5;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        long circularIndeterminateTrackColor;
        final float fM742getCircularIndicatorTrackGapSizeD9Ej5fM;
        final int i10;
        final Stroke stroke;
        final State stateAnimateFloat;
        final State stateAnimateFloat2;
        final State stateAnimateFloat3;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean zChanged;
        Object objRememberedValue;
        final long j6;
        final long j7;
        final float f6;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(333154241);
        int i12 = i3 & 1;
        if (i12 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            circularColor = j;
            i4 |= ((i3 & 2) == 0 && composerStartRestartGroup.changed(circularColor)) ? 32 : 16;
        } else {
            circularColor = j;
        }
        int i13 = i3 & 4;
        if (i13 == 0) {
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                fM743getCircularStrokeWidthD9Ej5fM = f;
                i4 |= composerStartRestartGroup.changed(fM743getCircularStrokeWidthD9Ej5fM) ? 256 : 128;
            }
            if ((i2 & 3072) == 0) {
                j3 = j2;
                if ((i3 & 8) == 0 || !composerStartRestartGroup.changed(j3)) {
                    i11 = 1024;
                } else {
                    i11 = 2048;
                }
                i4 |= i11;
            } else {
                j3 = j2;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    iM741getCircularIndeterminateStrokeCapKaPHkGw = i;
                    if (composerStartRestartGroup.changed(iM741getCircularIndeterminateStrokeCapKaPHkGw)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i4 |= i6;
                }
                i7 = i3 & 32;
                if (i7 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    f3 = f2;
                } else {
                    f3 = f2;
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i8 = 131072;
                        } else {
                            i8 = 65536;
                        }
                        i4 |= i8;
                    }
                }
                if ((i4 & 74899) != 74898) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i12 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 2) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -113;
                        }
                        if (i13 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 8) != 0) {
                            circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                            i4 &= -7169;
                        } else {
                            circularIndeterminateTrackColor = j3;
                        }
                        if (i5 != 0) {
                            iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                        }
                        if (i7 != 0) {
                            fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                        } else {
                            fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                        }
                        i10 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 2) != 0) {
                            i4 &= -113;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                        modifier4 = modifier2;
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                        i10 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                        circularIndeterminateTrackColor = j3;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(333154241, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:630)");
                    }
                    stroke = new Stroke(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4557toPx0680j_4(fM743getCircularStrokeWidthD9Ej5fM), 0.0f, i10, 0, null, 26, null);
                    InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition((String) null, composerStartRestartGroup, 0, 1);
                    InfiniteRepeatableSpec<Float> circularIndeterminateGlobalRotationAnimationSpec = getCircularIndeterminateGlobalRotationAnimationSpec();
                    int i14 = InfiniteTransition.$stable;
                    int i15 = InfiniteRepeatableSpec.$stable;
                    stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1080.0f, circularIndeterminateGlobalRotationAnimationSpec, (String) null, composerStartRestartGroup, i14 | 432 | (i15 << 9), 8);
                    stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 360.0f, getCircularIndeterminateRotationAnimationSpec(), (String) null, composerStartRestartGroup, i14 | 432 | (i15 << 9), 8);
                    stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.1f, 0.87f, getCircularIndeterminateProgressAnimationSpec(), (String) null, composerStartRestartGroup, i14 | 432 | (i15 << 9), 8);
                    composer2 = composerStartRestartGroup;
                    Modifier modifier5 = SizeKt.size-3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier4), CircularIndicatorDiameter);
                    boolean zChanged2 = composer2.changed(stateAnimateFloat3);
                    Modifier modifier6 = modifier4;
                    if ((57344 & i4) == 16384) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z5 = z2 | zChanged2;
                    if ((458752 & i4) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z6 = z5 | z3;
                    if ((i4 & 896) == 256) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    zChanged = z6 | z4 | composer2.changed(stateAnimateFloat) | composer2.changed(stateAnimateFloat2) | ((((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composer2.changed(circularIndeterminateTrackColor)) || (i4 & 3072) == 2048) | composer2.changedInstance(stroke) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(circularColor)) || (i4 & 48) == 32);
                    objRememberedValue = composer2.rememberedValue();
                    if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        j6 = circularIndeterminateTrackColor;
                        j7 = circularColor;
                        f6 = fM743getCircularStrokeWidthD9Ej5fM;
                        objRememberedValue = new Function1() { // from class: tdb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.m(stateAnimateFloat3, i10, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f6, stateAnimateFloat, stateAnimateFloat2, j6, stroke, j7, (DrawScope) obj);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue);
                    } else {
                        j6 = circularIndeterminateTrackColor;
                        j7 = circularColor;
                        f6 = fM743getCircularStrokeWidthD9Ej5fM;
                    }
                    CanvasKt.Canvas(modifier5, (Function1) objRememberedValue, composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier6;
                    i9 = i10;
                    f4 = fM742getCircularIndicatorTrackGapSizeD9Ej5fM;
                    f5 = f6;
                    j5 = j6;
                    j4 = j7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j4 = circularColor;
                    f4 = f3;
                    f5 = fM743getCircularStrokeWidthD9Ej5fM;
                    i9 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                    j5 = j3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: udb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.x(modifier3, j4, f5, j5, i9, f4, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            iM741getCircularIndeterminateStrokeCapKaPHkGw = i;
            i7 = i3 & 32;
            if (i7 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                f3 = f2;
            } else {
                f3 = f2;
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i4 |= i8;
                }
            }
            if ((i4 & 74899) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i13 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    } else {
                        circularIndeterminateTrackColor = j3;
                    }
                    if (i5 != 0) {
                        iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                    }
                    if (i7 != 0) {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                    i10 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                } else {
                    if (i12 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i13 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    } else {
                        circularIndeterminateTrackColor = j3;
                    }
                    if (i5 != 0) {
                        iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                    }
                    if (i7 != 0) {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                    i10 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(333154241, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:630)");
                }
                stroke = new Stroke(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4557toPx0680j_4(fM743getCircularStrokeWidthD9Ej5fM), 0.0f, i10, 0, null, 26, null);
                InfiniteTransition infiniteTransitionRememberInfiniteTransition2 = InfiniteTransitionKt.rememberInfiniteTransition((String) null, composerStartRestartGroup, 0, 1);
                InfiniteRepeatableSpec<Float> circularIndeterminateGlobalRotationAnimationSpec2 = getCircularIndeterminateGlobalRotationAnimationSpec();
                int i16 = InfiniteTransition.$stable;
                int i17 = InfiniteRepeatableSpec.$stable;
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, 1080.0f, circularIndeterminateGlobalRotationAnimationSpec2, (String) null, composerStartRestartGroup, i16 | 432 | (i17 << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, 360.0f, getCircularIndeterminateRotationAnimationSpec(), (String) null, composerStartRestartGroup, i16 | 432 | (i17 << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.1f, 0.87f, getCircularIndeterminateProgressAnimationSpec(), (String) null, composerStartRestartGroup, i16 | 432 | (i17 << 9), 8);
                composer2 = composerStartRestartGroup;
                Modifier modifier7 = SizeKt.size-3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier4), CircularIndicatorDiameter);
                boolean zChanged3 = composer2.changed(stateAnimateFloat3);
                Modifier modifier8 = modifier4;
                if ((57344 & i4) == 16384) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z7 = z2 | zChanged3;
                if ((458752 & i4) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z8 = z7 | z3;
                if ((i4 & 896) == 256) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                zChanged = z8 | z4 | composer2.changed(stateAnimateFloat) | composer2.changed(stateAnimateFloat2) | ((((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composer2.changed(circularIndeterminateTrackColor)) || (i4 & 3072) == 2048) | composer2.changedInstance(stroke) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(circularColor)) || (i4 & 48) == 32);
                objRememberedValue = composer2.rememberedValue();
                if (zChanged) {
                    j6 = circularIndeterminateTrackColor;
                    j7 = circularColor;
                    f6 = fM743getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue = new Function1() { // from class: tdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.m(stateAnimateFloat3, i10, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f6, stateAnimateFloat, stateAnimateFloat2, j6, stroke, j7, (DrawScope) obj);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                } else {
                    j6 = circularIndeterminateTrackColor;
                    j7 = circularColor;
                    f6 = fM743getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue = new Function1() { // from class: tdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.m(stateAnimateFloat3, i10, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f6, stateAnimateFloat, stateAnimateFloat2, j6, stroke, j7, (DrawScope) obj);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                CanvasKt.Canvas(modifier7, (Function1) objRememberedValue, composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier8;
                i9 = i10;
                f4 = fM742getCircularIndicatorTrackGapSizeD9Ej5fM;
                f5 = f6;
                j5 = j6;
                j4 = j7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j4 = circularColor;
                f4 = f3;
                f5 = fM743getCircularStrokeWidthD9Ej5fM;
                i9 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                j5 = j3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: udb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.x(modifier3, j4, f5, j5, i9, f4, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        fM743getCircularStrokeWidthD9Ej5fM = f;
        if ((i2 & 3072) == 0) {
            j3 = j2;
            if ((i3 & 8) == 0) {
                i11 = 1024;
            } else {
                i11 = 1024;
            }
            i4 |= i11;
        } else {
            j3 = j2;
        }
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                iM741getCircularIndeterminateStrokeCapKaPHkGw = i;
                if (composerStartRestartGroup.changed(iM741getCircularIndeterminateStrokeCapKaPHkGw)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i4 |= i6;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                f3 = f2;
            } else {
                f3 = f2;
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i4 |= i8;
                }
            }
            if ((i4 & 74899) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i13 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    } else {
                        circularIndeterminateTrackColor = j3;
                    }
                    if (i5 != 0) {
                        iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                    }
                    if (i7 != 0) {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                    i10 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                } else {
                    if (i12 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i13 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    } else {
                        circularIndeterminateTrackColor = j3;
                    }
                    if (i5 != 0) {
                        iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                    }
                    if (i7 != 0) {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                    i10 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(333154241, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:630)");
                }
                stroke = new Stroke(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4557toPx0680j_4(fM743getCircularStrokeWidthD9Ej5fM), 0.0f, i10, 0, null, 26, null);
                InfiniteTransition infiniteTransitionRememberInfiniteTransition3 = InfiniteTransitionKt.rememberInfiniteTransition((String) null, composerStartRestartGroup, 0, 1);
                InfiniteRepeatableSpec<Float> circularIndeterminateGlobalRotationAnimationSpec3 = getCircularIndeterminateGlobalRotationAnimationSpec();
                int i18 = InfiniteTransition.$stable;
                int i19 = InfiniteRepeatableSpec.$stable;
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, 1080.0f, circularIndeterminateGlobalRotationAnimationSpec3, (String) null, composerStartRestartGroup, i18 | 432 | (i19 << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, 360.0f, getCircularIndeterminateRotationAnimationSpec(), (String) null, composerStartRestartGroup, i18 | 432 | (i19 << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.1f, 0.87f, getCircularIndeterminateProgressAnimationSpec(), (String) null, composerStartRestartGroup, i18 | 432 | (i19 << 9), 8);
                composer2 = composerStartRestartGroup;
                Modifier modifier9 = SizeKt.size-3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier4), CircularIndicatorDiameter);
                boolean zChanged4 = composer2.changed(stateAnimateFloat3);
                Modifier modifier10 = modifier4;
                if ((57344 & i4) == 16384) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z9 = z2 | zChanged4;
                if ((458752 & i4) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z10 = z9 | z3;
                if ((i4 & 896) == 256) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                zChanged = z10 | z4 | composer2.changed(stateAnimateFloat) | composer2.changed(stateAnimateFloat2) | ((((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composer2.changed(circularIndeterminateTrackColor)) || (i4 & 3072) == 2048) | composer2.changedInstance(stroke) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(circularColor)) || (i4 & 48) == 32);
                objRememberedValue = composer2.rememberedValue();
                if (zChanged) {
                    j6 = circularIndeterminateTrackColor;
                    j7 = circularColor;
                    f6 = fM743getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue = new Function1() { // from class: tdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.m(stateAnimateFloat3, i10, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f6, stateAnimateFloat, stateAnimateFloat2, j6, stroke, j7, (DrawScope) obj);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                } else {
                    j6 = circularIndeterminateTrackColor;
                    j7 = circularColor;
                    f6 = fM743getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue = new Function1() { // from class: tdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.m(stateAnimateFloat3, i10, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f6, stateAnimateFloat, stateAnimateFloat2, j6, stroke, j7, (DrawScope) obj);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                CanvasKt.Canvas(modifier9, (Function1) objRememberedValue, composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier10;
                i9 = i10;
                f4 = fM742getCircularIndicatorTrackGapSizeD9Ej5fM;
                f5 = f6;
                j5 = j6;
                j4 = j7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j4 = circularColor;
                f4 = f3;
                f5 = fM743getCircularStrokeWidthD9Ej5fM;
                i9 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                j5 = j3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: udb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.x(modifier3, j4, f5, j5, i9, f4, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        iM741getCircularIndeterminateStrokeCapKaPHkGw = i;
        i7 = i3 & 32;
        if (i7 != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f3 = f2;
        } else {
            f3 = f2;
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i8 = 131072;
                } else {
                    i8 = 65536;
                }
                i4 |= i8;
            }
        }
        if ((i4 & 74899) != 74898) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i12 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if (i13 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 8) != 0) {
                    circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                } else {
                    circularIndeterminateTrackColor = j3;
                }
                if (i5 != 0) {
                    iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                }
                if (i7 != 0) {
                    fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                }
                i10 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
            } else {
                if (i12 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if (i13 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 8) != 0) {
                    circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                } else {
                    circularIndeterminateTrackColor = j3;
                }
                if (i5 != 0) {
                    iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                }
                if (i7 != 0) {
                    fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                }
                i10 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(333154241, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:630)");
            }
            stroke = new Stroke(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4557toPx0680j_4(fM743getCircularStrokeWidthD9Ej5fM), 0.0f, i10, 0, null, 26, null);
            InfiniteTransition infiniteTransitionRememberInfiniteTransition4 = InfiniteTransitionKt.rememberInfiniteTransition((String) null, composerStartRestartGroup, 0, 1);
            InfiniteRepeatableSpec<Float> circularIndeterminateGlobalRotationAnimationSpec4 = getCircularIndeterminateGlobalRotationAnimationSpec();
            int i110 = InfiniteTransition.$stable;
            int i111 = InfiniteRepeatableSpec.$stable;
            stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, 1080.0f, circularIndeterminateGlobalRotationAnimationSpec4, (String) null, composerStartRestartGroup, i110 | 432 | (i111 << 9), 8);
            stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, 360.0f, getCircularIndeterminateRotationAnimationSpec(), (String) null, composerStartRestartGroup, i110 | 432 | (i111 << 9), 8);
            stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.1f, 0.87f, getCircularIndeterminateProgressAnimationSpec(), (String) null, composerStartRestartGroup, i110 | 432 | (i111 << 9), 8);
            composer2 = composerStartRestartGroup;
            Modifier modifier11 = SizeKt.size-3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier4), CircularIndicatorDiameter);
            boolean zChanged5 = composer2.changed(stateAnimateFloat3);
            Modifier modifier12 = modifier4;
            if ((57344 & i4) == 16384) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z11 = z2 | zChanged5;
            if ((458752 & i4) == 131072) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z12 = z11 | z3;
            if ((i4 & 896) == 256) {
                z4 = true;
            } else {
                z4 = false;
            }
            zChanged = z12 | z4 | composer2.changed(stateAnimateFloat) | composer2.changed(stateAnimateFloat2) | ((((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composer2.changed(circularIndeterminateTrackColor)) || (i4 & 3072) == 2048) | composer2.changedInstance(stroke) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(circularColor)) || (i4 & 48) == 32);
            objRememberedValue = composer2.rememberedValue();
            if (zChanged) {
                j6 = circularIndeterminateTrackColor;
                j7 = circularColor;
                f6 = fM743getCircularStrokeWidthD9Ej5fM;
                objRememberedValue = new Function1() { // from class: tdb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.m(stateAnimateFloat3, i10, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f6, stateAnimateFloat, stateAnimateFloat2, j6, stroke, j7, (DrawScope) obj);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            } else {
                j6 = circularIndeterminateTrackColor;
                j7 = circularColor;
                f6 = fM743getCircularStrokeWidthD9Ej5fM;
                objRememberedValue = new Function1() { // from class: tdb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.m(stateAnimateFloat3, i10, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f6, stateAnimateFloat, stateAnimateFloat2, j6, stroke, j7, (DrawScope) obj);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifier11, (Function1) objRememberedValue, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier12;
            i9 = i10;
            f4 = fM742getCircularIndicatorTrackGapSizeD9Ej5fM;
            f5 = f6;
            j5 = j6;
            j4 = j7;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j4 = circularColor;
            f4 = f3;
            f5 = fM743getCircularStrokeWidthD9Ej5fM;
            i9 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
            j5 = j3;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: udb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.x(modifier3, j4, f5, j5, i9, f4, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0137  */
    /* JADX WARN: Code duplicated, block: B:104:0x0142  */
    /* JADX WARN: Code duplicated, block: B:107:0x014a  */
    /* JADX WARN: Code duplicated, block: B:109:0x0152  */
    /* JADX WARN: Code duplicated, block: B:112:0x0171  */
    /* JADX WARN: Code duplicated, block: B:114:0x017b  */
    /* JADX WARN: Code duplicated, block: B:117:0x018b  */
    /* JADX WARN: Code duplicated, block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0043  */
    /* JADX WARN: Code duplicated, block: B:28:0x0047  */
    /* JADX WARN: Code duplicated, block: B:30:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0052  */
    /* JADX WARN: Code duplicated, block: B:34:0x0058  */
    /* JADX WARN: Code duplicated, block: B:37:0x005e  */
    /* JADX WARN: Code duplicated, block: B:39:0x0063  */
    /* JADX WARN: Code duplicated, block: B:41:0x0067  */
    /* JADX WARN: Code duplicated, block: B:43:0x006f  */
    /* JADX WARN: Code duplicated, block: B:44:0x0072  */
    /* JADX WARN: Code duplicated, block: B:48:0x0079  */
    /* JADX WARN: Code duplicated, block: B:50:0x007d  */
    /* JADX WARN: Code duplicated, block: B:52:0x0085  */
    /* JADX WARN: Code duplicated, block: B:53:0x0088  */
    /* JADX WARN: Code duplicated, block: B:56:0x008e  */
    /* JADX WARN: Code duplicated, block: B:59:0x0096  */
    /* JADX WARN: Code duplicated, block: B:61:0x009a  */
    /* JADX WARN: Code duplicated, block: B:63:0x009d  */
    /* JADX WARN: Code duplicated, block: B:65:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:66:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:70:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e6 A[PHI: r3 r6 r7 r11 r12
      0x00e6: PHI (r3v25 int) = (r3v20 int), (r3v26 int), (r3v27 int) binds: [B:97:0x011a, B:83:0x00e3, B:84:0x00e5] A[DONT_GENERATE, DONT_INLINE]
      0x00e6: PHI (r6v9 androidx.compose.ui.Modifier) = (r6v5 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier) binds: [B:97:0x011a, B:83:0x00e3, B:84:0x00e5] A[DONT_GENERATE, DONT_INLINE]
      0x00e6: PHI (r7v10 long) = (r7v7 long), (r7v6 long), (r7v6 long) binds: [B:97:0x011a, B:83:0x00e3, B:84:0x00e5] A[DONT_GENERATE, DONT_INLINE]
      0x00e6: PHI (r11v8 float) = (r11v4 float), (r11v2 float), (r11v2 float) binds: [B:97:0x011a, B:83:0x00e3, B:84:0x00e5] A[DONT_GENERATE, DONT_INLINE]
      0x00e6: PHI (r12v10 long) = (r12v7 long), (r12v6 long), (r12v6 long) binds: [B:97:0x011a, B:83:0x00e3, B:84:0x00e5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:87:0x00f2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:91:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:93:0x0106  */
    /* JADX WARN: Code duplicated, block: B:96:0x0111  */
    /* JADX WARN: Code duplicated, block: B:98:0x011c  */
    @Deprecated(message = "Use the overload that takes `progress` as a lambda", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(\nprogress = { progress },\nmodifier = modifier,\ncolor = color,\nstrokeWidth = strokeWidth,\ntrackColor = trackColor,\nstrokeCap = strokeCap,\n)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    public static final void m748CircularProgressIndicatorDUhRLBM(final float f, Modifier modifier, long j, float f2, long j2, int i, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long circularColor;
        int i5;
        float fM743getCircularStrokeWidthD9Ej5fM;
        int i6;
        long circularTrackColor;
        int i7;
        int i8;
        int i9;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final long j3;
        final float f3;
        final long j4;
        final int i10;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int iM740getCircularDeterminateStrokeCapKaPHkGw;
        boolean z2;
        float f4;
        long j5;
        int i11;
        Modifier modifier4;
        Object objRememberedValue;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1472321743);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(f) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i12 = i3 & 2;
        if (i12 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i3 & 4) == 0) {
                    circularColor = j;
                    int i13 = composerStartRestartGroup.changed(circularColor) ? 256 : 128;
                    i4 |= i13;
                } else {
                    circularColor = j;
                }
                i4 |= i13;
            } else {
                circularColor = j;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i2 & 3072) == 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = f2;
                    if (composerStartRestartGroup.changed(fM743getCircularStrokeWidthD9Ej5fM)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                if ((i2 & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        circularTrackColor = j2;
                        int i14 = composerStartRestartGroup.changed(circularTrackColor) ? 16384 : 8192;
                        i4 |= i14;
                    } else {
                        circularTrackColor = j2;
                    }
                    i4 |= i14;
                } else {
                    circularTrackColor = j2;
                }
                i7 = i3 & 32;
                if (i7 != 0) {
                    if ((196608 & i2) == 0) {
                        i8 = i;
                        if (composerStartRestartGroup.changed(i8)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i4 |= i9;
                    }
                    if ((i4 & 74899) != 74898) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i12 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                                i4 &= -897;
                            }
                            if (i5 != 0) {
                                fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                            }
                            if ((i3 & 16) != 0) {
                                circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if (i7 != 0) {
                                iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                                z2 = false;
                                f4 = fM743getCircularStrokeWidthD9Ej5fM;
                                j5 = circularTrackColor;
                                i11 = -1472321743;
                                modifier4 = modifier2;
                            }
                            long j6 = circularColor;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
                            }
                            if ((i4 & 14) == 4) {
                                z2 = true;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function0() { // from class: mdb
                                    public final Object invoke() {
                                        return Float.valueOf(ProgressIndicatorKt.E(f));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composer2 = composerStartRestartGroup;
                            m750CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier4, j6, f4, j5, iM740getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            j3 = j6;
                            f3 = f4;
                            j4 = j5;
                            i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                            }
                            if ((i3 & 16) != 0) {
                                i4 &= -57345;
                            }
                        }
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        z2 = false;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        j5 = circularTrackColor;
                        long j7 = circularColor;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
                        }
                        if ((i4 & 14) == 4) {
                            z2 = true;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new Function0() { // from class: mdb
                                public final Object invoke() {
                                    return Float.valueOf(ProgressIndicatorKt.E(f));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new Function0() { // from class: mdb
                                public final Object invoke() {
                                    return Float.valueOf(ProgressIndicatorKt.E(f));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        m750CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier4, j7, f4, j5, iM740getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        j3 = j7;
                        f3 = f4;
                        j4 = j5;
                        i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        j3 = circularColor;
                        f3 = fM743getCircularStrokeWidthD9Ej5fM;
                        j4 = circularTrackColor;
                        i10 = i8;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: odb
                            public final Object invoke(Object obj, Object obj2) {
                                return ProgressIndicatorKt.r(f, modifier3, j3, f3, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i8 = i;
                if ((i4 & 74899) != 74898) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                            z2 = false;
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            j5 = circularTrackColor;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                        } else {
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            z2 = false;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                            j5 = circularTrackColor;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                            z2 = false;
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            j5 = circularTrackColor;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                        } else {
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            z2 = false;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                            j5 = circularTrackColor;
                        }
                    }
                    long j8 = circularColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
                    }
                    if ((i4 & 14) == 4) {
                        z2 = true;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new Function0() { // from class: mdb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.E(f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function0() { // from class: mdb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.E(f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    m750CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier4, j8, f4, j5, iM740getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    j3 = j8;
                    f3 = f4;
                    j4 = j5;
                    i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = circularColor;
                    f3 = fM743getCircularStrokeWidthD9Ej5fM;
                    j4 = circularTrackColor;
                    i10 = i8;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: odb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.r(f, modifier3, j3, f3, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            fM743getCircularStrokeWidthD9Ej5fM = f2;
            if ((i2 & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    circularTrackColor = j2;
                    if (composerStartRestartGroup.changed(circularTrackColor)) {
                    }
                    i4 |= i14;
                } else {
                    circularTrackColor = j2;
                }
                i4 |= i14;
            } else {
                circularTrackColor = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                if ((196608 & i2) == 0) {
                    i8 = i;
                    if (composerStartRestartGroup.changed(i8)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((i4 & 74899) != 74898) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                            z2 = false;
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            j5 = circularTrackColor;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                        } else {
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            z2 = false;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                            j5 = circularTrackColor;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                            z2 = false;
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            j5 = circularTrackColor;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                        } else {
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            z2 = false;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                            j5 = circularTrackColor;
                        }
                    }
                    long j9 = circularColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
                    }
                    if ((i4 & 14) == 4) {
                        z2 = true;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new Function0() { // from class: mdb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.E(f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function0() { // from class: mdb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.E(f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    m750CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier4, j9, f4, j5, iM740getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    j3 = j9;
                    f3 = f4;
                    j4 = j5;
                    i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = circularColor;
                    f3 = fM743getCircularStrokeWidthD9Ej5fM;
                    j4 = circularTrackColor;
                    i10 = i8;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: odb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.r(f, modifier3, j3, f3, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i8 = i;
            if ((i4 & 74899) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        z2 = false;
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        j5 = circularTrackColor;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                    } else {
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        z2 = false;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        j5 = circularTrackColor;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        z2 = false;
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        j5 = circularTrackColor;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                    } else {
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        z2 = false;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        j5 = circularTrackColor;
                    }
                }
                long j10 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
                }
                if ((i4 & 14) == 4) {
                    z2 = true;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new Function0() { // from class: mdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.E(f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: mdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.E(f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                m750CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier4, j10, f4, j5, iM740getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                j3 = j10;
                f3 = f4;
                j4 = j5;
                i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = circularColor;
                f3 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularTrackColor;
                i10 = i8;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: odb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.r(f, modifier3, j3, f3, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i3 & 4) == 0) {
                circularColor = j;
                if (composerStartRestartGroup.changed(circularColor)) {
                }
                i4 |= i13;
            } else {
                circularColor = j;
            }
            i4 |= i13;
        } else {
            circularColor = j;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i2 & 3072) == 0) {
                fM743getCircularStrokeWidthD9Ej5fM = f2;
                if (composerStartRestartGroup.changed(fM743getCircularStrokeWidthD9Ej5fM)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            if ((i2 & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    circularTrackColor = j2;
                    if (composerStartRestartGroup.changed(circularTrackColor)) {
                    }
                    i4 |= i14;
                } else {
                    circularTrackColor = j2;
                }
                i4 |= i14;
            } else {
                circularTrackColor = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                if ((196608 & i2) == 0) {
                    i8 = i;
                    if (composerStartRestartGroup.changed(i8)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((i4 & 74899) != 74898) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                            z2 = false;
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            j5 = circularTrackColor;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                        } else {
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            z2 = false;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                            j5 = circularTrackColor;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                            z2 = false;
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            j5 = circularTrackColor;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                        } else {
                            f4 = fM743getCircularStrokeWidthD9Ej5fM;
                            z2 = false;
                            i11 = -1472321743;
                            modifier4 = modifier2;
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                            j5 = circularTrackColor;
                        }
                    }
                    long j11 = circularColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
                    }
                    if ((i4 & 14) == 4) {
                        z2 = true;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new Function0() { // from class: mdb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.E(f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function0() { // from class: mdb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.E(f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    m750CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier4, j11, f4, j5, iM740getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    j3 = j11;
                    f3 = f4;
                    j4 = j5;
                    i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = circularColor;
                    f3 = fM743getCircularStrokeWidthD9Ej5fM;
                    j4 = circularTrackColor;
                    i10 = i8;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: odb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.r(f, modifier3, j3, f3, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i8 = i;
            if ((i4 & 74899) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        z2 = false;
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        j5 = circularTrackColor;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                    } else {
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        z2 = false;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        j5 = circularTrackColor;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        z2 = false;
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        j5 = circularTrackColor;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                    } else {
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        z2 = false;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        j5 = circularTrackColor;
                    }
                }
                long j12 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
                }
                if ((i4 & 14) == 4) {
                    z2 = true;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new Function0() { // from class: mdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.E(f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: mdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.E(f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                m750CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier4, j12, f4, j5, iM740getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                j3 = j12;
                f3 = f4;
                j4 = j5;
                i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = circularColor;
                f3 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularTrackColor;
                i10 = i8;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: odb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.r(f, modifier3, j3, f3, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        fM743getCircularStrokeWidthD9Ej5fM = f2;
        if ((i2 & 24576) == 0) {
            if ((i3 & 16) == 0) {
                circularTrackColor = j2;
                if (composerStartRestartGroup.changed(circularTrackColor)) {
                }
                i4 |= i14;
            } else {
                circularTrackColor = j2;
            }
            i4 |= i14;
        } else {
            circularTrackColor = j2;
        }
        i7 = i3 & 32;
        if (i7 != 0) {
            if ((196608 & i2) == 0) {
                i8 = i;
                if (composerStartRestartGroup.changed(i8)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            if ((i4 & 74899) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        z2 = false;
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        j5 = circularTrackColor;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                    } else {
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        z2 = false;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        j5 = circularTrackColor;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        z2 = false;
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        j5 = circularTrackColor;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                    } else {
                        f4 = fM743getCircularStrokeWidthD9Ej5fM;
                        z2 = false;
                        i11 = -1472321743;
                        modifier4 = modifier2;
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        j5 = circularTrackColor;
                    }
                }
                long j13 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
                }
                if ((i4 & 14) == 4) {
                    z2 = true;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new Function0() { // from class: mdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.E(f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: mdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.E(f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                m750CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier4, j13, f4, j5, iM740getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                j3 = j13;
                f3 = f4;
                j4 = j5;
                i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = circularColor;
                f3 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularTrackColor;
                i10 = i8;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: odb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.r(f, modifier3, j3, f3, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        i8 = i;
        if ((i4 & 74899) != 74898) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                }
                if (i7 != 0) {
                    iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    z2 = false;
                    f4 = fM743getCircularStrokeWidthD9Ej5fM;
                    j5 = circularTrackColor;
                    i11 = -1472321743;
                    modifier4 = modifier2;
                } else {
                    f4 = fM743getCircularStrokeWidthD9Ej5fM;
                    z2 = false;
                    i11 = -1472321743;
                    modifier4 = modifier2;
                    iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                    j5 = circularTrackColor;
                }
            } else {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                }
                if (i7 != 0) {
                    iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    z2 = false;
                    f4 = fM743getCircularStrokeWidthD9Ej5fM;
                    j5 = circularTrackColor;
                    i11 = -1472321743;
                    modifier4 = modifier2;
                } else {
                    f4 = fM743getCircularStrokeWidthD9Ej5fM;
                    z2 = false;
                    i11 = -1472321743;
                    modifier4 = modifier2;
                    iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                    j5 = circularTrackColor;
                }
            }
            long j14 = circularColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
            }
            if ((i4 & 14) == 4) {
                z2 = true;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2) {
                objRememberedValue = new Function0() { // from class: mdb
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.E(f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function0() { // from class: mdb
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.E(f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composer2 = composerStartRestartGroup;
            m750CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier4, j14, f4, j5, iM740getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            j3 = j14;
            f3 = f4;
            j4 = j5;
            i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = circularColor;
            f3 = fM743getCircularStrokeWidthD9Ej5fM;
            j4 = circularTrackColor;
            i10 = i8;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: odb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.r(f, modifier3, j3, f3, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x011c  */
    /* JADX WARN: Code duplicated, block: B:104:0x0127  */
    /* JADX WARN: Code duplicated, block: B:105:0x0131  */
    /* JADX WARN: Code duplicated, block: B:107:0x0134  */
    /* JADX WARN: Code duplicated, block: B:109:0x013d  */
    /* JADX WARN: Code duplicated, block: B:112:0x014f  */
    /* JADX WARN: Code duplicated, block: B:115:0x015d  */
    /* JADX WARN: Code duplicated, block: B:116:0x015f  */
    /* JADX WARN: Code duplicated, block: B:119:0x0166  */
    /* JADX WARN: Code duplicated, block: B:121:0x016e  */
    /* JADX WARN: Code duplicated, block: B:124:0x01af  */
    /* JADX WARN: Code duplicated, block: B:126:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:129:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:130:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:133:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:134:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:137:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:138:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:141:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:143:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:149:0x0214  */
    /* JADX WARN: Code duplicated, block: B:151:0x021a  */
    /* JADX WARN: Code duplicated, block: B:157:0x022b  */
    /* JADX WARN: Code duplicated, block: B:161:0x023b  */
    /* JADX WARN: Code duplicated, block: B:164:0x025b  */
    /* JADX WARN: Code duplicated, block: B:166:0x0265  */
    /* JADX WARN: Code duplicated, block: B:169:0x0274  */
    /* JADX WARN: Code duplicated, block: B:171:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0043  */
    /* JADX WARN: Code duplicated, block: B:31:0x0052  */
    /* JADX WARN: Code duplicated, block: B:33:0x0056  */
    /* JADX WARN: Code duplicated, block: B:36:0x005c  */
    /* JADX WARN: Code duplicated, block: B:38:0x0061  */
    /* JADX WARN: Code duplicated, block: B:40:0x0065  */
    /* JADX WARN: Code duplicated, block: B:42:0x006d  */
    /* JADX WARN: Code duplicated, block: B:43:0x0070  */
    /* JADX WARN: Code duplicated, block: B:47:0x0077  */
    /* JADX WARN: Code duplicated, block: B:49:0x007d  */
    /* JADX WARN: Code duplicated, block: B:52:0x0086  */
    /* JADX WARN: Code duplicated, block: B:54:0x008b  */
    /* JADX WARN: Code duplicated, block: B:57:0x0093  */
    /* JADX WARN: Code duplicated, block: B:58:0x0098  */
    /* JADX WARN: Code duplicated, block: B:60:0x009e  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:83:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:93:0x0101 A[PHI: r3 r6 r7 r11 r13 r15
      0x0101: PHI (r3v31 int) = (r3v22 int), (r3v33 int) binds: [B:108:0x013b, B:92:0x0100] A[DONT_GENERATE, DONT_INLINE]
      0x0101: PHI (r6v8 androidx.compose.ui.Modifier) = (r6v5 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier) binds: [B:108:0x013b, B:92:0x0100] A[DONT_GENERATE, DONT_INLINE]
      0x0101: PHI (r7v10 long) = (r7v7 long), (r7v11 long) binds: [B:108:0x013b, B:92:0x0100] A[DONT_GENERATE, DONT_INLINE]
      0x0101: PHI (r11v6 long) = (r11v3 long), (r11v1 long) binds: [B:108:0x013b, B:92:0x0100] A[DONT_GENERATE, DONT_INLINE]
      0x0101: PHI (r13v6 float) = (r13v3 float), (r13v2 float) binds: [B:108:0x013b, B:92:0x0100] A[DONT_GENERATE, DONT_INLINE]
      0x0101: PHI (r15v12 int) = (r15v2 int), (r15v1 int) binds: [B:108:0x013b, B:92:0x0100] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:95:0x0106 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:96:0x0108  */
    /* JADX WARN: Code duplicated, block: B:99:0x0112  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: CircularProgressIndicator-IyT6zlY, reason: not valid java name */
    public static final void m750CircularProgressIndicatorIyT6zlY(final Function0<Float> function0, Modifier modifier, long j, float f, long j2, int i, float f2, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long circularColor;
        int i5;
        float fM743getCircularStrokeWidthD9Ej5fM;
        int i6;
        long j3;
        int i7;
        int iM740getCircularDeterminateStrokeCapKaPHkGw;
        int i8;
        int i9;
        float f3;
        int i10;
        boolean z;
        final long j4;
        final float f4;
        final float fM742getCircularIndicatorTrackGapSizeD9Ej5fM;
        final int i11;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        long circularDeterminateTrackColor;
        boolean z2;
        Object objRememberedValue;
        final Function0 function1;
        final Stroke stroke;
        boolean zChanged;
        Object objRememberedValue2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean zChangedInstance;
        Object objRememberedValue3;
        final long j5;
        final long j6;
        final float f5;
        int i12;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1798883595);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i14 = i3 & 2;
        if (i14 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                circularColor = j;
                if ((i3 & 4) == 0 || !composerStartRestartGroup.changed(circularColor)) {
                    i13 = 128;
                } else {
                    i13 = 256;
                }
                i4 |= i13;
            } else {
                circularColor = j;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i2 & 3072) == 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = f;
                    if (composerStartRestartGroup.changed(fM743getCircularStrokeWidthD9Ej5fM)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                if ((i2 & 24576) == 0) {
                    j3 = j2;
                    if ((i3 & 16) == 0 || !composerStartRestartGroup.changed(j3)) {
                        i12 = 8192;
                    } else {
                        i12 = 16384;
                    }
                    i4 |= i12;
                } else {
                    j3 = j2;
                }
                i7 = i3 & 32;
                if (i7 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    iM740getCircularDeterminateStrokeCapKaPHkGw = i;
                } else {
                    iM740getCircularDeterminateStrokeCapKaPHkGw = i;
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(iM740getCircularDeterminateStrokeCapKaPHkGw)) {
                            i8 = 131072;
                        } else {
                            i8 = 65536;
                        }
                        i4 |= i8;
                    }
                }
                i9 = i3 & 64;
                if (i9 != 0) {
                    i4 |= 1572864;
                    f3 = f2;
                } else {
                    f3 = f2;
                    if ((i2 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i4 |= i10;
                    }
                }
                if ((i4 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            circularDeterminateTrackColor = j3;
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        }
                        if (i9 != 0) {
                            fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                        }
                        i11 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:526)");
                        }
                        if ((i4 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function0() { // from class: pdb
                                public final Object invoke() {
                                    return Float.valueOf(ProgressIndicatorKt.a(function0));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function1 = (Function0) objRememberedValue;
                        stroke = new Stroke(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4557toPx0680j_4(fM743getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
                        zChanged = composerStartRestartGroup.changed(function1);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: qdb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.z(function1, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifier3 = SizeKt.size-3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
                        boolean zChanged2 = composerStartRestartGroup.changed(function1);
                        if ((458752 & i4) == 131072) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z6 = z3 | zChanged2;
                        if ((3670016 & i4) == 1048576) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z7 = z6 | z4;
                        if ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        zChangedInstance = z7 | z5 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            j5 = circularDeterminateTrackColor;
                            j6 = circularColor;
                            f5 = fM743getCircularStrokeWidthD9Ej5fM;
                            objRememberedValue3 = new Function1() { // from class: rdb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.v(function1, i11, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f5, j5, stroke, j6, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            j5 = circularDeterminateTrackColor;
                            j6 = circularColor;
                            f5 = fM743getCircularStrokeWidthD9Ej5fM;
                        }
                        CanvasKt.Canvas(modifier3, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f4 = f5;
                        j3 = j5;
                        j4 = j6;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                        }
                        circularDeterminateTrackColor = j3;
                    }
                    fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    i11 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:526)");
                    }
                    if ((i4 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new Function0() { // from class: pdb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.a(function0));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function0() { // from class: pdb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.a(function0));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function1 = (Function0) objRememberedValue;
                    stroke = new Stroke(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4557toPx0680j_4(fM743getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
                    zChanged = composerStartRestartGroup.changed(function1);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: qdb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.z(function1, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: qdb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.z(function1, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifier4 = SizeKt.size-3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
                    boolean zChanged3 = composerStartRestartGroup.changed(function1);
                    if ((458752 & i4) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z8 = z3 | zChanged3;
                    if ((3670016 & i4) == 1048576) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z9 = z8 | z4;
                    if ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    zChangedInstance = z9 | z5 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance) {
                        j5 = circularDeterminateTrackColor;
                        j6 = circularColor;
                        f5 = fM743getCircularStrokeWidthD9Ej5fM;
                        objRememberedValue3 = new Function1() { // from class: rdb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.v(function1, i11, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f5, j5, stroke, j6, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        j5 = circularDeterminateTrackColor;
                        j6 = circularColor;
                        f5 = fM743getCircularStrokeWidthD9Ej5fM;
                        objRememberedValue3 = new Function1() { // from class: rdb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.v(function1, i11, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f5, j5, stroke, j6, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CanvasKt.Canvas(modifier4, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f4 = f5;
                    j3 = j5;
                    j4 = j6;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    j4 = circularColor;
                    f4 = fM743getCircularStrokeWidthD9Ej5fM;
                    fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    i11 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = modifier2;
                    final long j7 = j3;
                    final int i15 = i11;
                    final float f6 = fM742getCircularIndicatorTrackGapSizeD9Ej5fM;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sdb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.u(function0, modifier5, j4, f4, j7, i15, f6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            fM743getCircularStrokeWidthD9Ej5fM = f;
            if ((i2 & 24576) == 0) {
                j3 = j2;
                if ((i3 & 16) == 0) {
                    i12 = 8192;
                } else {
                    i12 = 8192;
                }
                i4 |= i12;
            } else {
                j3 = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                iM740getCircularDeterminateStrokeCapKaPHkGw = i;
            } else {
                iM740getCircularDeterminateStrokeCapKaPHkGw = i;
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(iM740getCircularDeterminateStrokeCapKaPHkGw)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i4 |= i8;
                }
            }
            i9 = i3 & 64;
            if (i9 != 0) {
                i4 |= 1572864;
                f3 = f2;
            } else {
                f3 = f2;
                if ((i2 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i4 |= i10;
                }
            }
            if ((i4 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                }
                i11 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:526)");
                }
                if ((i4 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new Function0() { // from class: pdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.a(function0));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: pdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.a(function0));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                function1 = (Function0) objRememberedValue;
                stroke = new Stroke(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4557toPx0680j_4(fM743getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
                zChanged = composerStartRestartGroup.changed(function1);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue2 = new Function1() { // from class: qdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.z(function1, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: qdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.z(function1, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifier6 = SizeKt.size-3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
                boolean zChanged4 = composerStartRestartGroup.changed(function1);
                if ((458752 & i4) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z10 = z3 | zChanged4;
                if ((3670016 & i4) == 1048576) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z11 = z10 | z4;
                if ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                zChangedInstance = z11 | z5 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j5 = circularDeterminateTrackColor;
                    j6 = circularColor;
                    f5 = fM743getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = new Function1() { // from class: rdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.v(function1, i11, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f5, j5, stroke, j6, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    j5 = circularDeterminateTrackColor;
                    j6 = circularColor;
                    f5 = fM743getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = new Function1() { // from class: rdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.v(function1, i11, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f5, j5, stroke, j6, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                CanvasKt.Canvas(modifier6, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f4 = f5;
                j3 = j5;
                j4 = j6;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                j4 = circularColor;
                f4 = fM743getCircularStrokeWidthD9Ej5fM;
                fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                i11 = iM740getCircularDeterminateStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier7 = modifier2;
                final long j8 = j3;
                final int i16 = i11;
                final float f7 = fM742getCircularIndicatorTrackGapSizeD9Ej5fM;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sdb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.u(function0, modifier7, j4, f4, j8, i16, f7, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            circularColor = j;
            if ((i3 & 4) == 0) {
                i13 = 128;
            } else {
                i13 = 128;
            }
            i4 |= i13;
        } else {
            circularColor = j;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i2 & 3072) == 0) {
                fM743getCircularStrokeWidthD9Ej5fM = f;
                if (composerStartRestartGroup.changed(fM743getCircularStrokeWidthD9Ej5fM)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            if ((i2 & 24576) == 0) {
                j3 = j2;
                if ((i3 & 16) == 0) {
                    i12 = 8192;
                } else {
                    i12 = 8192;
                }
                i4 |= i12;
            } else {
                j3 = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                iM740getCircularDeterminateStrokeCapKaPHkGw = i;
            } else {
                iM740getCircularDeterminateStrokeCapKaPHkGw = i;
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(iM740getCircularDeterminateStrokeCapKaPHkGw)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i4 |= i8;
                }
            }
            i9 = i3 & 64;
            if (i9 != 0) {
                i4 |= 1572864;
                f3 = f2;
            } else {
                f3 = f2;
                if ((i2 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i4 |= i10;
                }
            }
            if ((i4 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                }
                i11 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:526)");
                }
                if ((i4 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new Function0() { // from class: pdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.a(function0));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: pdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.a(function0));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                function1 = (Function0) objRememberedValue;
                stroke = new Stroke(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4557toPx0680j_4(fM743getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
                zChanged = composerStartRestartGroup.changed(function1);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue2 = new Function1() { // from class: qdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.z(function1, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: qdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.z(function1, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifier8 = SizeKt.size-3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
                boolean zChanged5 = composerStartRestartGroup.changed(function1);
                if ((458752 & i4) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z12 = z3 | zChanged5;
                if ((3670016 & i4) == 1048576) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z13 = z12 | z4;
                if ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                zChangedInstance = z13 | z5 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j5 = circularDeterminateTrackColor;
                    j6 = circularColor;
                    f5 = fM743getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = new Function1() { // from class: rdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.v(function1, i11, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f5, j5, stroke, j6, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    j5 = circularDeterminateTrackColor;
                    j6 = circularColor;
                    f5 = fM743getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = new Function1() { // from class: rdb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.v(function1, i11, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f5, j5, stroke, j6, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                CanvasKt.Canvas(modifier8, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f4 = f5;
                j3 = j5;
                j4 = j6;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                j4 = circularColor;
                f4 = fM743getCircularStrokeWidthD9Ej5fM;
                fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                i11 = iM740getCircularDeterminateStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier9 = modifier2;
                final long j9 = j3;
                final int i17 = i11;
                final float f8 = fM742getCircularIndicatorTrackGapSizeD9Ej5fM;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sdb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.u(function0, modifier9, j4, f4, j9, i17, f8, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        fM743getCircularStrokeWidthD9Ej5fM = f;
        if ((i2 & 24576) == 0) {
            j3 = j2;
            if ((i3 & 16) == 0) {
                i12 = 8192;
            } else {
                i12 = 8192;
            }
            i4 |= i12;
        } else {
            j3 = j2;
        }
        i7 = i3 & 32;
        if (i7 != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            iM740getCircularDeterminateStrokeCapKaPHkGw = i;
        } else {
            iM740getCircularDeterminateStrokeCapKaPHkGw = i;
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(iM740getCircularDeterminateStrokeCapKaPHkGw)) {
                    i8 = 131072;
                } else {
                    i8 = 65536;
                }
                i4 |= i8;
            }
        }
        i9 = i3 & 64;
        if (i9 != 0) {
            i4 |= 1572864;
            f3 = f2;
        } else {
            f3 = f2;
            if ((i2 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i10 = 1048576;
                } else {
                    i10 = 524288;
                }
                i4 |= i10;
            }
        }
        if ((i4 & 599187) != 599186) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                } else {
                    circularDeterminateTrackColor = j3;
                }
                if (i7 != 0) {
                    iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                }
                if (i9 != 0) {
                    fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                }
            } else {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                } else {
                    circularDeterminateTrackColor = j3;
                }
                if (i7 != 0) {
                    iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                }
                if (i9 != 0) {
                    fM742getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                }
            }
            i11 = iM740getCircularDeterminateStrokeCapKaPHkGw;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:526)");
            }
            if ((i4 & 14) == 4) {
                z2 = true;
            } else {
                z2 = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2) {
                objRememberedValue = new Function0() { // from class: pdb
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.a(function0));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function0() { // from class: pdb
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.a(function0));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            function1 = (Function0) objRememberedValue;
            stroke = new Stroke(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4557toPx0680j_4(fM743getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
            zChanged = composerStartRestartGroup.changed(function1);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue2 = new Function1() { // from class: qdb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.z(function1, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new Function1() { // from class: qdb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.z(function1, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifier10 = SizeKt.size-3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
            boolean zChanged6 = composerStartRestartGroup.changed(function1);
            if ((458752 & i4) == 131072) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z14 = z3 | zChanged6;
            if ((3670016 & i4) == 1048576) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean z15 = z14 | z4;
            if ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                z5 = true;
            } else {
                z5 = false;
            }
            zChangedInstance = z15 | z5 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                j5 = circularDeterminateTrackColor;
                j6 = circularColor;
                f5 = fM743getCircularStrokeWidthD9Ej5fM;
                objRememberedValue3 = new Function1() { // from class: rdb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.v(function1, i11, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f5, j5, stroke, j6, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                j5 = circularDeterminateTrackColor;
                j6 = circularColor;
                f5 = fM743getCircularStrokeWidthD9Ej5fM;
                objRememberedValue3 = new Function1() { // from class: rdb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.v(function1, i11, fM742getCircularIndicatorTrackGapSizeD9Ej5fM, f5, j5, stroke, j6, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            CanvasKt.Canvas(modifier10, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f4 = f5;
            j3 = j5;
            j4 = j6;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            j4 = circularColor;
            f4 = fM743getCircularStrokeWidthD9Ej5fM;
            fM742getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
            i11 = iM740getCircularDeterminateStrokeCapKaPHkGw;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier11 = modifier2;
            final long j10 = j3;
            final int i18 = i11;
            final float f9 = fM742getCircularIndicatorTrackGapSizeD9Ej5fM;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sdb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.u(function0, modifier11, j4, f4, j10, i18, f9, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0152  */
    /* JADX WARN: Code duplicated, block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x0063  */
    /* JADX WARN: Code duplicated, block: B:39:0x0067  */
    /* JADX WARN: Code duplicated, block: B:41:0x006f  */
    /* JADX WARN: Code duplicated, block: B:42:0x0072  */
    /* JADX WARN: Code duplicated, block: B:45:0x0078  */
    /* JADX WARN: Code duplicated, block: B:48:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x0087  */
    /* JADX WARN: Code duplicated, block: B:54:0x008f  */
    /* JADX WARN: Code duplicated, block: B:55:0x0092  */
    /* JADX WARN: Code duplicated, block: B:59:0x009b  */
    /* JADX WARN: Code duplicated, block: B:60:0x009d  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:77:0x00c9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:78:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:79:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:82:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:84:0x00de  */
    /* JADX WARN: Code duplicated, block: B:87:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:90:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:93:0x010c  */
    /* JADX WARN: Code duplicated, block: B:96:0x0139  */
    /* JADX WARN: Code duplicated, block: B:98:0x0142  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(modifier, color, strokeWidth, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-LxG7B9w, reason: not valid java name */
    public static final /* synthetic */ void m751CircularProgressIndicatorLxG7B9w(Modifier modifier, long j, float f, long j2, int i, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long circularColor;
        float fM743getCircularStrokeWidthD9Ej5fM;
        long circularIndeterminateTrackColor;
        int i5;
        int iM741getCircularIndeterminateStrokeCapKaPHkGw;
        int i6;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final long j3;
        final float f2;
        final long j4;
        final int i7;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        float f3;
        Modifier modifier5;
        long j5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-115871647);
        int i8 = i3 & 1;
        if (i8 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            if ((i3 & 2) == 0) {
                circularColor = j;
                int i9 = composerStartRestartGroup.changed(circularColor) ? 32 : 16;
                i4 |= i9;
            } else {
                circularColor = j;
            }
            i4 |= i9;
        } else {
            circularColor = j;
        }
        int i10 = i3 & 4;
        if (i10 == 0) {
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                fM743getCircularStrokeWidthD9Ej5fM = f;
                i4 |= composerStartRestartGroup.changed(fM743getCircularStrokeWidthD9Ej5fM) ? 256 : 128;
            }
            if ((i2 & 3072) == 0) {
                if ((i3 & 8) == 0) {
                    circularIndeterminateTrackColor = j2;
                    int i11 = composerStartRestartGroup.changed(circularIndeterminateTrackColor) ? 2048 : 1024;
                    i4 |= i11;
                } else {
                    circularIndeterminateTrackColor = j2;
                }
                i4 |= i11;
            } else {
                circularIndeterminateTrackColor = j2;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    iM741getCircularIndeterminateStrokeCapKaPHkGw = i;
                    if (composerStartRestartGroup.changed(iM741getCircularIndeterminateStrokeCapKaPHkGw)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i4 |= i6;
                }
                if ((i4 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 2) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -113;
                        }
                        if (i10 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 8) != 0) {
                            circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                            i4 &= -7169;
                        }
                        if (i5 != 0) {
                            iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                            f3 = fM743getCircularStrokeWidthD9Ej5fM;
                            j5 = circularIndeterminateTrackColor;
                            modifier5 = modifier4;
                        } else {
                            f3 = fM743getCircularStrokeWidthD9Ej5fM;
                            modifier5 = modifier4;
                        }
                        long j6 = circularColor;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-115871647, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:594)");
                        }
                        composer2 = composerStartRestartGroup;
                        m747CircularProgressIndicator4lLiAd8(modifier5, j6, f3, j5, iM741getCircularIndeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier5;
                        j3 = j6;
                        f2 = f3;
                        j4 = j5;
                        i7 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 2) != 0) {
                            i4 &= -113;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                        f3 = fM743getCircularStrokeWidthD9Ej5fM;
                        modifier5 = modifier2;
                    }
                    j5 = circularIndeterminateTrackColor;
                    long j7 = circularColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-115871647, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:594)");
                    }
                    composer2 = composerStartRestartGroup;
                    m747CircularProgressIndicator4lLiAd8(modifier5, j7, f3, j5, iM741getCircularIndeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    j3 = j7;
                    f2 = f3;
                    j4 = j5;
                    i7 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = circularColor;
                    f2 = fM743getCircularStrokeWidthD9Ej5fM;
                    j4 = circularIndeterminateTrackColor;
                    i7 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vdb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.d(modifier3, j3, f2, j4, i7, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            iM741getCircularIndeterminateStrokeCapKaPHkGw = i;
            if ((i4 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i10 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                        f3 = fM743getCircularStrokeWidthD9Ej5fM;
                        j5 = circularIndeterminateTrackColor;
                        modifier5 = modifier4;
                    } else {
                        f3 = fM743getCircularStrokeWidthD9Ej5fM;
                        modifier5 = modifier4;
                        j5 = circularIndeterminateTrackColor;
                    }
                } else {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i10 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                        f3 = fM743getCircularStrokeWidthD9Ej5fM;
                        j5 = circularIndeterminateTrackColor;
                        modifier5 = modifier4;
                    } else {
                        f3 = fM743getCircularStrokeWidthD9Ej5fM;
                        modifier5 = modifier4;
                        j5 = circularIndeterminateTrackColor;
                    }
                }
                long j8 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-115871647, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:594)");
                }
                composer2 = composerStartRestartGroup;
                m747CircularProgressIndicator4lLiAd8(modifier5, j8, f3, j5, iM741getCircularIndeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j3 = j8;
                f2 = f3;
                j4 = j5;
                i7 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = circularColor;
                f2 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularIndeterminateTrackColor;
                i7 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vdb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.d(modifier3, j3, f2, j4, i7, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        fM743getCircularStrokeWidthD9Ej5fM = f;
        if ((i2 & 3072) == 0) {
            if ((i3 & 8) == 0) {
                circularIndeterminateTrackColor = j2;
                if (composerStartRestartGroup.changed(circularIndeterminateTrackColor)) {
                }
                i4 |= i11;
            } else {
                circularIndeterminateTrackColor = j2;
            }
            i4 |= i11;
        } else {
            circularIndeterminateTrackColor = j2;
        }
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                iM741getCircularIndeterminateStrokeCapKaPHkGw = i;
                if (composerStartRestartGroup.changed(iM741getCircularIndeterminateStrokeCapKaPHkGw)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i4 |= i6;
            }
            if ((i4 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i10 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                        f3 = fM743getCircularStrokeWidthD9Ej5fM;
                        j5 = circularIndeterminateTrackColor;
                        modifier5 = modifier4;
                    } else {
                        f3 = fM743getCircularStrokeWidthD9Ej5fM;
                        modifier5 = modifier4;
                        j5 = circularIndeterminateTrackColor;
                    }
                } else {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i10 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                        f3 = fM743getCircularStrokeWidthD9Ej5fM;
                        j5 = circularIndeterminateTrackColor;
                        modifier5 = modifier4;
                    } else {
                        f3 = fM743getCircularStrokeWidthD9Ej5fM;
                        modifier5 = modifier4;
                        j5 = circularIndeterminateTrackColor;
                    }
                }
                long j9 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-115871647, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:594)");
                }
                composer2 = composerStartRestartGroup;
                m747CircularProgressIndicator4lLiAd8(modifier5, j9, f3, j5, iM741getCircularIndeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j3 = j9;
                f2 = f3;
                j4 = j5;
                i7 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = circularColor;
                f2 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularIndeterminateTrackColor;
                i7 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vdb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.d(modifier3, j3, f2, j4, i7, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        iM741getCircularIndeterminateStrokeCapKaPHkGw = i;
        if ((i4 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i8 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if (i10 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 8) != 0) {
                    circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                    f3 = fM743getCircularStrokeWidthD9Ej5fM;
                    j5 = circularIndeterminateTrackColor;
                    modifier5 = modifier4;
                } else {
                    f3 = fM743getCircularStrokeWidthD9Ej5fM;
                    modifier5 = modifier4;
                    j5 = circularIndeterminateTrackColor;
                }
            } else {
                if (i8 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if (i10 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 8) != 0) {
                    circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM741getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m741getCircularIndeterminateStrokeCapKaPHkGw();
                    f3 = fM743getCircularStrokeWidthD9Ej5fM;
                    j5 = circularIndeterminateTrackColor;
                    modifier5 = modifier4;
                } else {
                    f3 = fM743getCircularStrokeWidthD9Ej5fM;
                    modifier5 = modifier4;
                    j5 = circularIndeterminateTrackColor;
                }
            }
            long j10 = circularColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-115871647, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:594)");
            }
            composer2 = composerStartRestartGroup;
            m747CircularProgressIndicator4lLiAd8(modifier5, j10, f3, j5, iM741getCircularIndeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j3 = j10;
            f2 = f3;
            j4 = j5;
            i7 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = circularColor;
            f2 = fM743getCircularStrokeWidthD9Ej5fM;
            j4 = circularIndeterminateTrackColor;
            i7 = iM741getCircularIndeterminateStrokeCapKaPHkGw;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vdb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.d(modifier3, j3, f2, j4, i7, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0048  */
    /* JADX WARN: Code duplicated, block: B:31:0x0057  */
    /* JADX WARN: Code duplicated, block: B:33:0x005b  */
    /* JADX WARN: Code duplicated, block: B:36:0x0061  */
    /* JADX WARN: Code duplicated, block: B:38:0x0066  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:42:0x0072  */
    /* JADX WARN: Code duplicated, block: B:43:0x0075  */
    /* JADX WARN: Code duplicated, block: B:47:0x007e  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:51:0x0089  */
    /* JADX WARN: Code duplicated, block: B:53:0x0091  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:61:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:67:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:72:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:75:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:77:0x0103  */
    /* JADX WARN: Code duplicated, block: B:80:0x010f  */
    /* JADX WARN: Code duplicated, block: B:82:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: CircularProgressIndicator-MBs18nI, reason: not valid java name */
    public static final /* synthetic */ void m752CircularProgressIndicatorMBs18nI(final float f, Modifier modifier, long j, float f2, Composer composer, final int i, final int i2) {
        float f3;
        int i3;
        Modifier modifier2;
        long circularColor;
        int i4;
        float f4;
        int i5;
        boolean z;
        final Modifier modifier3;
        final long j2;
        final float f5;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        float fM743getCircularStrokeWidthD9Ej5fM;
        long j3;
        Modifier modifier5;
        int i6;
        Composer composerStartRestartGroup = composer.startRestartGroup(402841196);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            f3 = f;
        } else if ((i & 6) == 0) {
            f3 = f;
            i3 = (composerStartRestartGroup.changed(f3) ? 4 : 2) | i;
        } else {
            f3 = f;
            i3 = i;
        }
        int i7 = i2 & 2;
        if (i7 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                circularColor = j;
                if ((i2 & 4) == 0 || !composerStartRestartGroup.changed(circularColor)) {
                    i6 = 128;
                } else {
                    i6 = 256;
                }
                i3 |= i6;
            } else {
                circularColor = j;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    f4 = f2;
                    if (composerStartRestartGroup.changed(f4)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i3 & 1171) != 1170) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i7 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i3 &= -897;
                        }
                        if (i4 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        } else {
                            fM743getCircularStrokeWidthD9Ej5fM = f4;
                        }
                        j3 = circularColor;
                        modifier5 = modifier4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        modifier5 = modifier2;
                        fM743getCircularStrokeWidthD9Ej5fM = f4;
                        j3 = circularColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:723)");
                    }
                    ProgressIndicatorDefaults progressIndicatorDefaults = ProgressIndicatorDefaults.INSTANCE;
                    float f6 = fM743getCircularStrokeWidthD9Ej5fM;
                    m748CircularProgressIndicatorDUhRLBM(f3, modifier5, j3, f6, progressIndicatorDefaults.getCircularTrackColor(composerStartRestartGroup, 6), progressIndicatorDefaults.m740getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    j2 = j3;
                    f5 = f6;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j2 = circularColor;
                    f5 = f4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fdb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.b(f, modifier3, j2, f5, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            f4 = f2;
            if ((i3 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM743getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j3 = circularColor;
                    modifier5 = modifier4;
                } else {
                    if (i7 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM743getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j3 = circularColor;
                    modifier5 = modifier4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:723)");
                }
                ProgressIndicatorDefaults progressIndicatorDefaults2 = ProgressIndicatorDefaults.INSTANCE;
                float f7 = fM743getCircularStrokeWidthD9Ej5fM;
                m748CircularProgressIndicatorDUhRLBM(f3, modifier5, j3, f7, progressIndicatorDefaults2.getCircularTrackColor(composerStartRestartGroup, 6), progressIndicatorDefaults2.m740getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j2 = j3;
                f5 = f7;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j2 = circularColor;
                f5 = f4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fdb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.b(f, modifier3, j2, f5, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            circularColor = j;
            if ((i2 & 4) == 0) {
                i6 = 128;
            } else {
                i6 = 128;
            }
            i3 |= i6;
        } else {
            circularColor = j;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                f4 = f2;
                if (composerStartRestartGroup.changed(f4)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i3 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM743getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j3 = circularColor;
                    modifier5 = modifier4;
                } else {
                    if (i7 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM743getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j3 = circularColor;
                    modifier5 = modifier4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:723)");
                }
                ProgressIndicatorDefaults progressIndicatorDefaults3 = ProgressIndicatorDefaults.INSTANCE;
                float f8 = fM743getCircularStrokeWidthD9Ej5fM;
                m748CircularProgressIndicatorDUhRLBM(f3, modifier5, j3, f8, progressIndicatorDefaults3.getCircularTrackColor(composerStartRestartGroup, 6), progressIndicatorDefaults3.m740getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j2 = j3;
                f5 = f8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j2 = circularColor;
                f5 = f4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fdb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.b(f, modifier3, j2, f5, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        f4 = f2;
        if ((i3 & 1171) != 1170) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i7 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
                if (i4 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM743getCircularStrokeWidthD9Ej5fM = f4;
                }
                j3 = circularColor;
                modifier5 = modifier4;
            } else {
                if (i7 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
                if (i4 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM743getCircularStrokeWidthD9Ej5fM = f4;
                }
                j3 = circularColor;
                modifier5 = modifier4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:723)");
            }
            ProgressIndicatorDefaults progressIndicatorDefaults4 = ProgressIndicatorDefaults.INSTANCE;
            float f9 = fM743getCircularStrokeWidthD9Ej5fM;
            m748CircularProgressIndicatorDUhRLBM(f3, modifier5, j3, f9, progressIndicatorDefaults4.getCircularTrackColor(composerStartRestartGroup, 6), progressIndicatorDefaults4.m740getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j2 = j3;
            f5 = f9;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j2 = circularColor;
            f5 = f4;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fdb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.b(f, modifier3, j2, f5, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0063  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:40:0x006e  */
    /* JADX WARN: Code duplicated, block: B:49:0x008a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x008c  */
    /* JADX WARN: Code duplicated, block: B:51:0x008f  */
    /* JADX WARN: Code duplicated, block: B:54:0x0094  */
    /* JADX WARN: Code duplicated, block: B:56:0x009e  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:61:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:64:0x00da  */
    /* JADX WARN: Code duplicated, block: B:66:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:71:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: CircularProgressIndicator-aM-cp0Q, reason: not valid java name */
    public static final /* synthetic */ void m753CircularProgressIndicatoraMcp0Q(Modifier modifier, long j, float f, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long circularColor;
        float f2;
        boolean z;
        final Modifier modifier3;
        final long j2;
        final float f3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        float fM743getCircularStrokeWidthD9Ej5fM;
        long j3;
        Modifier modifier5;
        Composer composerStartRestartGroup = composer.startRestartGroup(947193756);
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
        if ((i & 48) == 0) {
            circularColor = j;
            i3 |= ((i2 & 2) == 0 && composerStartRestartGroup.changed(circularColor)) ? 32 : 16;
        } else {
            circularColor = j;
        }
        int i5 = i2 & 4;
        if (i5 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                f2 = f;
                i3 |= composerStartRestartGroup.changed(f2) ? 256 : 128;
            }
            if ((i3 & 147) != 146) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -113;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM743getCircularStrokeWidthD9Ej5fM = f2;
                    }
                    j3 = circularColor;
                    modifier5 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 2) != 0) {
                        i3 &= -113;
                    }
                    fM743getCircularStrokeWidthD9Ej5fM = f2;
                    j3 = circularColor;
                    modifier5 = modifier2;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(947193756, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:740)");
                }
                ProgressIndicatorDefaults progressIndicatorDefaults = ProgressIndicatorDefaults.INSTANCE;
                m747CircularProgressIndicator4lLiAd8(modifier5, j3, fM743getCircularStrokeWidthD9Ej5fM, progressIndicatorDefaults.getCircularTrackColor(composerStartRestartGroup, 6), progressIndicatorDefaults.m741getCircularIndeterminateStrokeCapKaPHkGw(), 0.0f, composerStartRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896), 32);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j2 = j3;
                f3 = fM743getCircularStrokeWidthD9Ej5fM;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j2 = circularColor;
                f3 = f2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ldb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.t(modifier3, j2, f3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        f2 = f;
        if ((i3 & 147) != 146) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if (i5 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM743getCircularStrokeWidthD9Ej5fM = f2;
                }
                j3 = circularColor;
                modifier5 = modifier4;
            } else {
                if (i4 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if (i5 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM743getCircularStrokeWidthD9Ej5fM = f2;
                }
                j3 = circularColor;
                modifier5 = modifier4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(947193756, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:740)");
            }
            ProgressIndicatorDefaults progressIndicatorDefaults2 = ProgressIndicatorDefaults.INSTANCE;
            m747CircularProgressIndicator4lLiAd8(modifier5, j3, fM743getCircularStrokeWidthD9Ej5fM, progressIndicatorDefaults2.getCircularTrackColor(composerStartRestartGroup, 6), progressIndicatorDefaults2.m741getCircularIndeterminateStrokeCapKaPHkGw(), 0.0f, composerStartRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896), 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j2 = j3;
            f3 = fM743getCircularStrokeWidthD9Ej5fM;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j2 = circularColor;
            f3 = f2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ldb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.t(modifier3, j2, f3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit D(KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig) {
        keyframesSpecConfig.setDurationMillis(CircularAnimationProgressDuration);
        Float fValueOf = Float.valueOf(90.0f);
        keyframesSpecConfig.using(keyframesSpecConfig.at(fValueOf, CircularAnimationAdditionalRotationDuration), MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier());
        keyframesSpecConfig.at(fValueOf, CircularAnimationAdditionalRotationDelay);
        Float fValueOf2 = Float.valueOf(180.0f);
        keyframesSpecConfig.at(fValueOf2, 1800);
        keyframesSpecConfig.at(fValueOf2, 3000);
        Float fValueOf3 = Float.valueOf(270.0f);
        keyframesSpecConfig.at(fValueOf3, 3300);
        keyframesSpecConfig.at(fValueOf3, 4500);
        Float fValueOf4 = Float.valueOf(360.0f);
        keyframesSpecConfig.at(fValueOf4, 4800);
        keyframesSpecConfig.at(fValueOf4, CircularAnimationProgressDuration);
        return Unit.INSTANCE;
    }

    public static float E(float f) {
        return f;
    }

    /* JADX WARN: Code duplicated, block: B:47:0x007e  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:51:0x0089  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ab A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:65:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:82:0x0108  */
    /* JADX WARN: Code duplicated, block: B:84:0x0110  */
    /* JADX WARN: Code duplicated, block: B:87:0x011d  */
    /* JADX WARN: Code duplicated, block: B:89:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`, see `LegacyIndeterminateLinearProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(modifier, color, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-2cYBFYY, reason: not valid java name */
    public static final /* synthetic */ void m754LinearProgressIndicator2cYBFYY(Modifier modifier, long j, long j2, int i, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long linearColor;
        long linearTrackColor;
        int i5;
        boolean z;
        final Modifier modifier3;
        final long j3;
        final long j4;
        final int i6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int iM745getLinearStrokeCapKaPHkGw;
        Modifier modifier5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-476865359);
        int i7 = i3 & 1;
        if (i7 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            if ((i3 & 2) == 0) {
                linearColor = j;
                int i8 = composerStartRestartGroup.changed(linearColor) ? 32 : 16;
                i4 |= i8;
            } else {
                linearColor = j;
            }
            i4 |= i8;
        } else {
            linearColor = j;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            linearTrackColor = j2;
            i4 |= ((i3 & 4) == 0 && composerStartRestartGroup.changed(linearTrackColor)) ? 256 : 128;
        } else {
            linearTrackColor = j2;
        }
        int i9 = i3 & 8;
        if (i9 == 0) {
            if ((i2 & 3072) == 0) {
                i5 = i;
                i4 |= composerStartRestartGroup.changed(i5) ? 2048 : 1024;
            }
            if ((i4 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i7 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i9 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i5;
                    }
                    modifier5 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i3 & 2) != 0) {
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                    }
                    linearTrackColor = linearTrackColor;
                    iM745getLinearStrokeCapKaPHkGw = i5;
                    modifier5 = modifier2;
                }
                long j5 = linearColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-476865359, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:219)");
                }
                m760LinearProgressIndicatorrIrjwxo(modifier5, j5, linearTrackColor, iM745getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM(), composerStartRestartGroup, (i4 & 14) | 24576 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j3 = j5;
                j4 = linearTrackColor;
                i6 = iM745getLinearStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = linearColor;
                j4 = linearTrackColor;
                i6 = i5;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xcb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.y(modifier3, j3, j4, i6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        i5 = i;
        if ((i4 & 1171) != 1170) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i7 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i9 != 0) {
                    iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                } else {
                    iM745getLinearStrokeCapKaPHkGw = i5;
                }
                modifier5 = modifier4;
            } else {
                if (i7 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i9 != 0) {
                    iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                } else {
                    iM745getLinearStrokeCapKaPHkGw = i5;
                }
                modifier5 = modifier4;
            }
            long j6 = linearColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-476865359, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:219)");
            }
            m760LinearProgressIndicatorrIrjwxo(modifier5, j6, linearTrackColor, iM745getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM(), composerStartRestartGroup, (i4 & 14) | 24576 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j3 = j6;
            j4 = linearTrackColor;
            i6 = iM745getLinearStrokeCapKaPHkGw;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = linearColor;
            j4 = linearTrackColor;
            i6 = i5;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xcb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.y(modifier3, j3, j4, i6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x011a  */
    /* JADX WARN: Code duplicated, block: B:104:0x0126  */
    /* JADX WARN: Code duplicated, block: B:106:0x0130  */
    /* JADX WARN: Code duplicated, block: B:107:0x0137  */
    /* JADX WARN: Code duplicated, block: B:109:0x013a  */
    /* JADX WARN: Code duplicated, block: B:112:0x0144  */
    /* JADX WARN: Code duplicated, block: B:114:0x014c  */
    /* JADX WARN: Code duplicated, block: B:116:0x0152  */
    /* JADX WARN: Code duplicated, block: B:122:0x015f  */
    /* JADX WARN: Code duplicated, block: B:123:0x0161  */
    /* JADX WARN: Code duplicated, block: B:126:0x0169  */
    /* JADX WARN: Code duplicated, block: B:128:0x0171  */
    /* JADX WARN: Code duplicated, block: B:131:0x0183  */
    /* JADX WARN: Code duplicated, block: B:134:0x018f  */
    /* JADX WARN: Code duplicated, block: B:137:0x019d  */
    /* JADX WARN: Code duplicated, block: B:138:0x019f  */
    /* JADX WARN: Code duplicated, block: B:141:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:143:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:146:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:148:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:151:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:152:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:155:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:156:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:159:0x0209  */
    /* JADX WARN: Code duplicated, block: B:161:0x020f  */
    /* JADX WARN: Code duplicated, block: B:167:0x021f  */
    /* JADX WARN: Code duplicated, block: B:169:0x0225  */
    /* JADX WARN: Code duplicated, block: B:175:0x0236  */
    /* JADX WARN: Code duplicated, block: B:177:0x023c  */
    /* JADX WARN: Code duplicated, block: B:183:0x0249  */
    /* JADX WARN: Code duplicated, block: B:187:0x0259  */
    /* JADX WARN: Code duplicated, block: B:190:0x0277  */
    /* JADX WARN: Code duplicated, block: B:192:0x0283  */
    /* JADX WARN: Code duplicated, block: B:195:0x0294  */
    /* JADX WARN: Code duplicated, block: B:197:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0043  */
    /* JADX WARN: Code duplicated, block: B:31:0x0052  */
    /* JADX WARN: Code duplicated, block: B:33:0x0056  */
    /* JADX WARN: Code duplicated, block: B:36:0x005c  */
    /* JADX WARN: Code duplicated, block: B:41:0x006b  */
    /* JADX WARN: Code duplicated, block: B:43:0x006f  */
    /* JADX WARN: Code duplicated, block: B:46:0x0075  */
    /* JADX WARN: Code duplicated, block: B:48:0x007a  */
    /* JADX WARN: Code duplicated, block: B:50:0x007e  */
    /* JADX WARN: Code duplicated, block: B:52:0x0086  */
    /* JADX WARN: Code duplicated, block: B:53:0x0089  */
    /* JADX WARN: Code duplicated, block: B:57:0x0093  */
    /* JADX WARN: Code duplicated, block: B:58:0x0098  */
    /* JADX WARN: Code duplicated, block: B:60:0x009e  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:83:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:96:0x010f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:97:0x0111  */
    /* JADX WARN: Code duplicated, block: B:98:0x0114  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: LinearProgressIndicator-GJbTh5U, reason: not valid java name */
    public static final void m755LinearProgressIndicatorGJbTh5U(final Function0<Float> function0, Modifier modifier, long j, long j2, int i, float f, Function1<? super DrawScope, Unit> function1, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        final long linearColor;
        long linearTrackColor;
        int i5;
        int i6;
        int i7;
        int i8;
        float fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
        int i9;
        boolean z;
        boolean z2;
        final float f2;
        Modifier modifier3;
        final int i10;
        final long j3;
        final long j4;
        final Function1<? super DrawScope, Unit> function2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final int iM745getLinearStrokeCapKaPHkGw;
        Function1<? super DrawScope, Unit> function3;
        final float f3;
        final int i11;
        boolean z3;
        boolean z4;
        Object objRememberedValue;
        boolean z5;
        Object objRememberedValue2;
        final Function0 function4;
        boolean zChanged;
        Object objRememberedValue3;
        boolean z6;
        boolean z7;
        boolean z8;
        Object objRememberedValue4;
        final Function1<? super DrawScope, Unit> function5;
        final long j5;
        int i12;
        int i13;
        int i14;
        Composer composerStartRestartGroup = composer.startRestartGroup(-339970038);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i15 = i3 & 2;
        if (i15 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                linearColor = j;
                if ((i3 & 4) == 0 || !composerStartRestartGroup.changed(linearColor)) {
                    i14 = 128;
                } else {
                    i14 = 256;
                }
                i4 |= i14;
            } else {
                linearColor = j;
            }
            if ((i2 & 3072) == 0) {
                linearTrackColor = j2;
                if ((i3 & 8) == 0 || !composerStartRestartGroup.changed(linearTrackColor)) {
                    i13 = 1024;
                } else {
                    i13 = 2048;
                }
                i4 |= i13;
            } else {
                linearTrackColor = j2;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    i6 = i;
                    if (composerStartRestartGroup.changed(i6)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i4 |= i7;
                }
                i8 = i3 & 32;
                if (i8 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f;
                } else {
                    fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f;
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(fM744getLinearIndicatorTrackGapSizeD9Ej5fM)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i4 |= i9;
                    }
                }
                if ((i2 & 1572864) != 0) {
                    if ((i3 & 64) == 0 || !composerStartRestartGroup.changedInstance(function1)) {
                        i12 = 524288;
                    } else {
                        i12 = 1048576;
                    }
                    i4 |= i12;
                }
                z = true;
                if ((i4 & 599187) != 599186) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i15 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                            i4 &= -7169;
                        }
                        if (i5 != 0) {
                            iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                        } else {
                            iM745getLinearStrokeCapKaPHkGw = i6;
                        }
                        if (i8 != 0) {
                            fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                        }
                        if ((i3 & 64) != 0) {
                            boolean z9 = (((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256;
                            if ((i4 & 57344) == 16384) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            z4 = z9 | z3;
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z4 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: scb
                                    public final Object invoke(Object obj) {
                                        return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function3 = (Function1) objRememberedValue;
                            i4 &= -3670017;
                        } else {
                            function3 = function1;
                        }
                        f3 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                        i11 = iM745getLinearStrokeCapKaPHkGw;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                        }
                        function3 = function1;
                        f3 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                        modifier3 = modifier2;
                        i11 = i6;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:153)");
                    }
                    if ((i4 & 14) == 4) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: tcb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.c(function0));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function4 = (Function0) objRememberedValue2;
                    Modifier modifierThen = modifier3.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds());
                    zChanged = composerStartRestartGroup.changed(function4);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function1() { // from class: ucb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.l(function4, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    Modifier modifier4 = SizeKt.size-VpY3zN4(SemanticsModifierKt.semantics(modifierThen, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
                    if ((i4 & 57344) == 16384) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if ((458752 & i4) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean zChanged2 = z6 | z7 | composerStartRestartGroup.changed(function4) | ((((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
                    if ((((3670016 & i4) ^ 1572864) > 1048576 || !composerStartRestartGroup.changed(function3)) && (i4 & 1572864) != 1048576) {
                    }
                    z8 = z | zChanged2;
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!z8 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        function5 = function3;
                        j5 = linearColor;
                        j4 = linearTrackColor;
                        objRememberedValue4 = new Function1() { // from class: vcb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.s(i11, f3, function4, j4, j5, function5, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        function5 = function3;
                        j5 = linearColor;
                        j4 = linearTrackColor;
                    }
                    CanvasKt.Canvas(modifier4, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    i10 = i11;
                    f2 = f3;
                    j3 = j5;
                    function2 = function5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    f2 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                    modifier3 = modifier2;
                    i10 = i6;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    function2 = function1;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = modifier3;
                    final long j6 = j4;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: wcb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.w(function0, modifier5, j3, j6, i10, f2, function2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            i6 = i;
            i8 = i3 & 32;
            if (i8 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f;
            } else {
                fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f;
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(fM744getLinearIndicatorTrackGapSizeD9Ej5fM)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
            }
            if ((i2 & 1572864) != 0) {
                if ((i3 & 64) == 0) {
                    i12 = 524288;
                } else {
                    i12 = 524288;
                }
                i4 |= i12;
            }
            z = true;
            if ((i4 & 599187) != 599186) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i15 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        if (((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256) {
                        }
                        if ((i4 & 57344) == 16384) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z9 | z3;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z4) {
                            objRememberedValue = new Function1() { // from class: scb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new Function1() { // from class: scb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f3 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i11 = iM745getLinearStrokeCapKaPHkGw;
                } else {
                    if (i15 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        if (((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256) {
                        }
                        if ((i4 & 57344) == 16384) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z9 | z3;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z4) {
                            objRememberedValue = new Function1() { // from class: scb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new Function1() { // from class: scb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f3 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i11 = iM745getLinearStrokeCapKaPHkGw;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:153)");
                }
                if ((i4 & 14) == 4) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new Function0() { // from class: tcb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.c(function0));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function0() { // from class: tcb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.c(function0));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                function4 = (Function0) objRememberedValue2;
                Modifier modifierThen2 = modifier3.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds());
                zChanged = composerStartRestartGroup.changed(function4);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = new Function1() { // from class: ucb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.l(function4, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function1() { // from class: ucb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.l(function4, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                Modifier modifier6 = SizeKt.size-VpY3zN4(SemanticsModifierKt.semantics(modifierThen2, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
                if ((i4 & 57344) == 16384) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if ((458752 & i4) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean zChanged3 = z6 | z7 | composerStartRestartGroup.changed(function4) | ((((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
                z = ((3670016 & i4) ^ 1572864) > 1048576 ? false : false;
                z8 = z | zChanged3;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    function5 = function3;
                    j5 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = new Function1() { // from class: vcb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.s(i11, f3, function4, j4, j5, function5, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    function5 = function3;
                    j5 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = new Function1() { // from class: vcb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.s(i11, f3, function4, j4, j5, function5, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                CanvasKt.Canvas(modifier6, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                i10 = i11;
                f2 = f3;
                j3 = j5;
                function2 = function5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                f2 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                modifier3 = modifier2;
                i10 = i6;
                j3 = linearColor;
                j4 = linearTrackColor;
                function2 = function1;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier7 = modifier3;
                final long j7 = j4;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: wcb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.w(function0, modifier7, j3, j7, i10, f2, function2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            linearColor = j;
            if ((i3 & 4) == 0) {
                i14 = 128;
            } else {
                i14 = 128;
            }
            i4 |= i14;
        } else {
            linearColor = j;
        }
        if ((i2 & 3072) == 0) {
            linearTrackColor = j2;
            if ((i3 & 8) == 0) {
                i13 = 1024;
            } else {
                i13 = 1024;
            }
            i4 |= i13;
        } else {
            linearTrackColor = j2;
        }
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                i6 = i;
                if (composerStartRestartGroup.changed(i6)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i4 |= i7;
            }
            i8 = i3 & 32;
            if (i8 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f;
            } else {
                fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f;
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(fM744getLinearIndicatorTrackGapSizeD9Ej5fM)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
            }
            if ((i2 & 1572864) != 0) {
                if ((i3 & 64) == 0) {
                    i12 = 524288;
                } else {
                    i12 = 524288;
                }
                i4 |= i12;
            }
            z = true;
            if ((i4 & 599187) != 599186) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i15 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        if (((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256) {
                        }
                        if ((i4 & 57344) == 16384) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z9 | z3;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z4) {
                            objRememberedValue = new Function1() { // from class: scb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new Function1() { // from class: scb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f3 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i11 = iM745getLinearStrokeCapKaPHkGw;
                } else {
                    if (i15 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        if (((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256) {
                        }
                        if ((i4 & 57344) == 16384) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z9 | z3;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z4) {
                            objRememberedValue = new Function1() { // from class: scb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new Function1() { // from class: scb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f3 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i11 = iM745getLinearStrokeCapKaPHkGw;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:153)");
                }
                if ((i4 & 14) == 4) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new Function0() { // from class: tcb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.c(function0));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function0() { // from class: tcb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.c(function0));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                function4 = (Function0) objRememberedValue2;
                Modifier modifierThen3 = modifier3.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds());
                zChanged = composerStartRestartGroup.changed(function4);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = new Function1() { // from class: ucb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.l(function4, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function1() { // from class: ucb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.l(function4, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                Modifier modifier8 = SizeKt.size-VpY3zN4(SemanticsModifierKt.semantics(modifierThen3, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
                if ((i4 & 57344) == 16384) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if ((458752 & i4) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean zChanged4 = z6 | z7 | composerStartRestartGroup.changed(function4) | ((((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
                if (((3670016 & i4) ^ 1572864) > 1048576) {
                }
                z8 = z | zChanged4;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    function5 = function3;
                    j5 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = new Function1() { // from class: vcb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.s(i11, f3, function4, j4, j5, function5, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    function5 = function3;
                    j5 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = new Function1() { // from class: vcb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.s(i11, f3, function4, j4, j5, function5, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                CanvasKt.Canvas(modifier8, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                i10 = i11;
                f2 = f3;
                j3 = j5;
                function2 = function5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                f2 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                modifier3 = modifier2;
                i10 = i6;
                j3 = linearColor;
                j4 = linearTrackColor;
                function2 = function1;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier9 = modifier3;
                final long j8 = j4;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: wcb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.w(function0, modifier9, j3, j8, i10, f2, function2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        i6 = i;
        i8 = i3 & 32;
        if (i8 != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f;
        } else {
            fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f;
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(fM744getLinearIndicatorTrackGapSizeD9Ej5fM)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
        }
        if ((i2 & 1572864) != 0) {
            if ((i3 & 64) == 0) {
                i12 = 524288;
            } else {
                i12 = 524288;
            }
            i4 |= i12;
        }
        z = true;
        if ((i4 & 599187) != 599186) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i15 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                } else {
                    iM745getLinearStrokeCapKaPHkGw = i6;
                }
                if (i8 != 0) {
                    fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                }
                if ((i3 & 64) != 0) {
                    if (((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256) {
                    }
                    if ((i4 & 57344) == 16384) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z9 | z3;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z4) {
                        objRememberedValue = new Function1() { // from class: scb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: scb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function3 = (Function1) objRememberedValue;
                    i4 &= -3670017;
                } else {
                    function3 = function1;
                }
                f3 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                i11 = iM745getLinearStrokeCapKaPHkGw;
            } else {
                if (i15 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                } else {
                    iM745getLinearStrokeCapKaPHkGw = i6;
                }
                if (i8 != 0) {
                    fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                }
                if ((i3 & 64) != 0) {
                    if (((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256) {
                    }
                    if ((i4 & 57344) == 16384) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z9 | z3;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z4) {
                        objRememberedValue = new Function1() { // from class: scb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: scb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.e(linearColor, iM745getLinearStrokeCapKaPHkGw, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function3 = (Function1) objRememberedValue;
                    i4 &= -3670017;
                } else {
                    function3 = function1;
                }
                f3 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                i11 = iM745getLinearStrokeCapKaPHkGw;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:153)");
            }
            if ((i4 & 14) == 4) {
                z5 = true;
            } else {
                z5 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z5) {
                objRememberedValue2 = new Function0() { // from class: tcb
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.c(function0));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new Function0() { // from class: tcb
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.c(function0));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            function4 = (Function0) objRememberedValue2;
            Modifier modifierThen4 = modifier3.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds());
            zChanged = composerStartRestartGroup.changed(function4);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue3 = new Function1() { // from class: ucb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.l(function4, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = new Function1() { // from class: ucb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.l(function4, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifier10 = SizeKt.size-VpY3zN4(SemanticsModifierKt.semantics(modifierThen4, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
            if ((i4 & 57344) == 16384) {
                z6 = true;
            } else {
                z6 = false;
            }
            if ((458752 & i4) == 131072) {
                z7 = true;
            } else {
                z7 = false;
            }
            boolean zChanged5 = z6 | z7 | composerStartRestartGroup.changed(function4) | ((((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
            if (((3670016 & i4) ^ 1572864) > 1048576) {
            }
            z8 = z | zChanged5;
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z8) {
                function5 = function3;
                j5 = linearColor;
                j4 = linearTrackColor;
                objRememberedValue4 = new Function1() { // from class: vcb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.s(i11, f3, function4, j4, j5, function5, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                function5 = function3;
                j5 = linearColor;
                j4 = linearTrackColor;
                objRememberedValue4 = new Function1() { // from class: vcb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.s(i11, f3, function4, j4, j5, function5, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            CanvasKt.Canvas(modifier10, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            i10 = i11;
            f2 = f3;
            j3 = j5;
            function2 = function5;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            f2 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
            modifier3 = modifier2;
            i10 = i6;
            j3 = linearColor;
            j4 = linearTrackColor;
            function2 = function1;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier11 = modifier3;
            final long j9 = j4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: wcb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.w(function0, modifier11, j3, j9, i10, f2, function2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: LinearProgressIndicator-RIQooxk, reason: not valid java name */
    public static final /* synthetic */ void m756LinearProgressIndicatorRIQooxk(Modifier modifier, long j, long j2, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long linearColor;
        long linearTrackColor;
        final Modifier modifier3;
        final long j3;
        final long j4;
        long j5;
        Modifier modifier4;
        Composer composerStartRestartGroup = composer.startRestartGroup(585576195);
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
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                linearColor = j;
                int i5 = composerStartRestartGroup.changed(linearColor) ? 32 : 16;
                i3 |= i5;
            } else {
                linearColor = j;
            }
            i3 |= i5;
        } else {
            linearColor = j;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                linearTrackColor = j2;
                int i6 = composerStartRestartGroup.changed(linearTrackColor) ? 256 : 128;
                i3 |= i6;
            } else {
                linearTrackColor = j2;
            }
            i3 |= i6;
        } else {
            linearTrackColor = j2;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                Modifier modifier5 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i2 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
                j5 = linearTrackColor;
                modifier4 = modifier5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 2) != 0) {
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
                j5 = linearTrackColor;
                modifier4 = modifier2;
            }
            long j6 = linearColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(585576195, i3, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:391)");
            }
            m760LinearProgressIndicatorrIrjwxo(modifier4, j6, j5, ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw(), 0.0f, composerStartRestartGroup, (i3 & 14) | 3072 | (i3 & 112) | (i3 & 896), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            j3 = j6;
            j4 = j5;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = linearColor;
            j4 = linearTrackColor;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hdb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.A(modifier3, j3, j4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0149  */
    /* JADX WARN: Code duplicated, block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0044  */
    /* JADX WARN: Code duplicated, block: B:31:0x0053  */
    /* JADX WARN: Code duplicated, block: B:33:0x0057  */
    /* JADX WARN: Code duplicated, block: B:36:0x005d  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:43:0x0070  */
    /* JADX WARN: Code duplicated, block: B:46:0x0076  */
    /* JADX WARN: Code duplicated, block: B:48:0x007b  */
    /* JADX WARN: Code duplicated, block: B:50:0x007f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0087  */
    /* JADX WARN: Code duplicated, block: B:53:0x008a  */
    /* JADX WARN: Code duplicated, block: B:57:0x0097  */
    /* JADX WARN: Code duplicated, block: B:58:0x009a  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c1 A[PHI: r3 r6 r9 r11
      0x00c1: PHI (r3v23 int) = (r3v18 int), (r3v24 int), (r3v25 int) binds: [B:82:0x00e4, B:70:0x00bd, B:71:0x00bf] A[DONT_GENERATE, DONT_INLINE]
      0x00c1: PHI (r6v7 androidx.compose.ui.Modifier) = (r6v3 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier) binds: [B:82:0x00e4, B:70:0x00bd, B:71:0x00bf] A[DONT_GENERATE, DONT_INLINE]
      0x00c1: PHI (r9v8 long) = (r9v3 long), (r9v1 long), (r9v1 long) binds: [B:82:0x00e4, B:70:0x00bd, B:71:0x00bf] A[DONT_GENERATE, DONT_INLINE]
      0x00c1: PHI (r11v6 long) = (r11v2 long), (r11v1 long), (r11v1 long) binds: [B:82:0x00e4, B:70:0x00bd, B:71:0x00bf] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:74:0x00c6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:75:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:89:0x0101  */
    /* JADX WARN: Code duplicated, block: B:92:0x0109  */
    /* JADX WARN: Code duplicated, block: B:94:0x0111  */
    /* JADX WARN: Code duplicated, block: B:97:0x0132  */
    /* JADX WARN: Code duplicated, block: B:99:0x013a  */
    @Deprecated(message = "Use the overload that takes `progress` as a lambda", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(\nprogress = { progress },\nmodifier = modifier,\ncolor = color,\ntrackColor = trackColor,\nstrokeCap = strokeCap,\n)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    public static final void m757LinearProgressIndicator_5eSRE(final float f, Modifier modifier, long j, long j2, int i, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long linearColor;
        long linearTrackColor;
        int i5;
        int i6;
        int i7;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final long j3;
        final long j4;
        final int i8;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int iM745getLinearStrokeCapKaPHkGw;
        boolean z2;
        Object objRememberedValue;
        int i9;
        int i10;
        Composer composerStartRestartGroup = composer.startRestartGroup(905419617);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(f) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i11 = i3 & 2;
        if (i11 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                linearColor = j;
                if ((i3 & 4) == 0 || !composerStartRestartGroup.changed(linearColor)) {
                    i10 = 128;
                } else {
                    i10 = 256;
                }
                i4 |= i10;
            } else {
                linearColor = j;
            }
            if ((i2 & 3072) == 0) {
                linearTrackColor = j2;
                if ((i3 & 8) == 0 || !composerStartRestartGroup.changed(linearTrackColor)) {
                    i9 = 1024;
                } else {
                    i9 = 2048;
                }
                i4 |= i9;
            } else {
                linearTrackColor = j2;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    i6 = i;
                    if (composerStartRestartGroup.changed(i6)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i4 |= i7;
                }
                if ((i4 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                            i4 &= -7169;
                        }
                        if (i5 != 0) {
                            iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                        }
                        long j5 = linearTrackColor;
                        long j6 = linearColor;
                        Modifier modifier4 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:359)");
                        }
                        z2 = (i4 & 14) == 4;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function0() { // from class: bdb
                                public final Object invoke() {
                                    return Float.valueOf(ProgressIndicatorKt.o(f));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        m755LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier4, j6, j5, iM745getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        j3 = j6;
                        j4 = j5;
                        i8 = iM745getLinearStrokeCapKaPHkGw;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                    }
                    iM745getLinearStrokeCapKaPHkGw = i6;
                    long j7 = linearTrackColor;
                    long j8 = linearColor;
                    Modifier modifier5 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:359)");
                    }
                    if ((i4 & 14) == 4) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new Function0() { // from class: bdb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.o(f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function0() { // from class: bdb
                            public final Object invoke() {
                                return Float.valueOf(ProgressIndicatorKt.o(f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    m755LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier5, j8, j7, iM745getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    j3 = j8;
                    j4 = j7;
                    i8 = iM745getLinearStrokeCapKaPHkGw;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    i8 = i6;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ddb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.i(f, modifier3, j3, j4, i8, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            i6 = i;
            if ((i4 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                }
                long j9 = linearTrackColor;
                long j10 = linearColor;
                Modifier modifier6 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:359)");
                }
                if ((i4 & 14) == 4) {
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new Function0() { // from class: bdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.o(f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: bdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.o(f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                m755LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier6, j10, j9, iM745getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier6;
                j3 = j10;
                j4 = j9;
                i8 = iM745getLinearStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = linearColor;
                j4 = linearTrackColor;
                i8 = i6;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ddb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.i(f, modifier3, j3, j4, i8, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            linearColor = j;
            if ((i3 & 4) == 0) {
                i10 = 128;
            } else {
                i10 = 128;
            }
            i4 |= i10;
        } else {
            linearColor = j;
        }
        if ((i2 & 3072) == 0) {
            linearTrackColor = j2;
            if ((i3 & 8) == 0) {
                i9 = 1024;
            } else {
                i9 = 1024;
            }
            i4 |= i9;
        } else {
            linearTrackColor = j2;
        }
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                i6 = i;
                if (composerStartRestartGroup.changed(i6)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i4 |= i7;
            }
            if ((i4 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                }
                long j11 = linearTrackColor;
                long j12 = linearColor;
                Modifier modifier7 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:359)");
                }
                if ((i4 & 14) == 4) {
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new Function0() { // from class: bdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.o(f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: bdb
                        public final Object invoke() {
                            return Float.valueOf(ProgressIndicatorKt.o(f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                m755LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier7, j12, j11, iM745getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier7;
                j3 = j12;
                j4 = j11;
                i8 = iM745getLinearStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = linearColor;
                j4 = linearTrackColor;
                i8 = i6;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ddb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.i(f, modifier3, j3, j4, i8, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        i6 = i;
        if ((i4 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                } else {
                    iM745getLinearStrokeCapKaPHkGw = i6;
                }
            } else {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                } else {
                    iM745getLinearStrokeCapKaPHkGw = i6;
                }
            }
            long j13 = linearTrackColor;
            long j14 = linearColor;
            Modifier modifier8 = modifier2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:359)");
            }
            if ((i4 & 14) == 4) {
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2) {
                objRememberedValue = new Function0() { // from class: bdb
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.o(f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function0() { // from class: bdb
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.o(f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composer2 = composerStartRestartGroup;
            m755LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier8, j14, j13, iM745getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier8;
            j3 = j14;
            j4 = j13;
            i8 = iM745getLinearStrokeCapKaPHkGw;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = linearColor;
            j4 = linearTrackColor;
            i8 = i6;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ddb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.i(f, modifier3, j3, j4, i8, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x0049  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:31:0x0054  */
    /* JADX WARN: Code duplicated, block: B:34:0x005a  */
    /* JADX WARN: Code duplicated, block: B:37:0x0060  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:44:0x0073  */
    /* JADX WARN: Code duplicated, block: B:47:0x007b  */
    /* JADX WARN: Code duplicated, block: B:48:0x007d  */
    /* JADX WARN: Code duplicated, block: B:51:0x0086  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:73:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:81:0x0104  */
    /* JADX WARN: Code duplicated, block: B:84:0x0110  */
    /* JADX WARN: Code duplicated, block: B:86:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: LinearProgressIndicator-eaDK9VM, reason: not valid java name */
    public static final /* synthetic */ void m759LinearProgressIndicatoreaDK9VM(final float f, Modifier modifier, long j, long j2, Composer composer, final int i, final int i2) {
        float f2;
        int i3;
        Modifier modifier2;
        long j3;
        long j4;
        boolean z;
        final Modifier modifier3;
        final long j5;
        final long j6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        long linearColor;
        long linearTrackColor;
        Modifier modifier5;
        long j7;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-372717133);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            f2 = f;
        } else {
            f2 = f;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changed(f2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i5 = i2 & 2;
        if (i5 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) == 0) {
                    j3 = j;
                    int i6 = composerStartRestartGroup.changed(j3) ? 256 : 128;
                    i3 |= i6;
                } else {
                    j3 = j;
                }
                i3 |= i6;
            } else {
                j3 = j;
            }
            if ((i & 3072) == 0) {
                j4 = j2;
                if ((i2 & 8) == 0 || !composerStartRestartGroup.changed(j4)) {
                    i4 = 1024;
                } else {
                    i4 = 2048;
                }
                i3 |= i4;
            } else {
                j4 = j2;
            }
            if ((i3 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        modifier5 = modifier4;
                        j7 = linearColor;
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    } else {
                        linearTrackColor = j4;
                        modifier5 = modifier4;
                        j7 = linearColor;
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                    }
                    linearTrackColor = j4;
                    modifier5 = modifier2;
                    j7 = j3;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-372717133, i3, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:376)");
                }
                m757LinearProgressIndicator_5eSRE(f2, modifier5, j7, linearTrackColor, ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j5 = j7;
                j6 = linearTrackColor;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j5 = j3;
                j6 = j4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jdb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.k(f, modifier3, j5, j6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                j3 = j;
                if (composerStartRestartGroup.changed(j3)) {
                }
                i3 |= i6;
            } else {
                j3 = j;
            }
            i3 |= i6;
        } else {
            j3 = j;
        }
        if ((i & 3072) == 0) {
            j4 = j2;
            if ((i2 & 8) == 0) {
                i4 = 1024;
            } else {
                i4 = 1024;
            }
            i3 |= i4;
        } else {
            j4 = j2;
        }
        if ((i3 & 1171) != 1170) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    modifier5 = modifier4;
                    j7 = linearColor;
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                } else {
                    linearTrackColor = j4;
                    modifier5 = modifier4;
                    j7 = linearColor;
                }
            } else {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    modifier5 = modifier4;
                    j7 = linearColor;
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                } else {
                    linearTrackColor = j4;
                    modifier5 = modifier4;
                    j7 = linearColor;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-372717133, i3, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:376)");
            }
            m757LinearProgressIndicator_5eSRE(f2, modifier5, j7, linearTrackColor, ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j5 = j7;
            j6 = linearTrackColor;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j5 = j3;
            j6 = j4;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jdb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.k(f, modifier3, j5, j6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:103:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:109:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:111:0x0204  */
    /* JADX WARN: Code duplicated, block: B:117:0x021c  */
    /* JADX WARN: Code duplicated, block: B:121:0x022a  */
    /* JADX WARN: Code duplicated, block: B:124:0x024c  */
    /* JADX WARN: Code duplicated, block: B:126:0x0259  */
    /* JADX WARN: Code duplicated, block: B:129:0x0267  */
    /* JADX WARN: Code duplicated, block: B:131:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x007a  */
    /* JADX WARN: Code duplicated, block: B:48:0x007f  */
    /* JADX WARN: Code duplicated, block: B:50:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x008b  */
    /* JADX WARN: Code duplicated, block: B:53:0x008e  */
    /* JADX WARN: Code duplicated, block: B:57:0x0099  */
    /* JADX WARN: Code duplicated, block: B:58:0x009b  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c2 A[PHI: r2 r4 r9 r11 r14
      0x00c2: PHI (r2v8 androidx.compose.ui.Modifier) = (r2v4 androidx.compose.ui.Modifier), (r2v11 androidx.compose.ui.Modifier) binds: [B:86:0x00f0, B:72:0x00c1] A[DONT_GENERATE, DONT_INLINE]
      0x00c2: PHI (r4v31 int) = (r4v16 int), (r4v33 int) binds: [B:86:0x00f0, B:72:0x00c1] A[DONT_GENERATE, DONT_INLINE]
      0x00c2: PHI (r9v6 long) = (r9v3 long), (r9v1 long) binds: [B:86:0x00f0, B:72:0x00c1] A[DONT_GENERATE, DONT_INLINE]
      0x00c2: PHI (r11v5 long) = (r11v2 long), (r11v1 long) binds: [B:86:0x00f0, B:72:0x00c1] A[DONT_GENERATE, DONT_INLINE]
      0x00c2: PHI (r14v29 int) = (r14v3 int), (r14v2 int) binds: [B:86:0x00f0, B:72:0x00c1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:75:0x00c7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:76:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:77:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:83:0x00de  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:90:0x0104  */
    /* JADX WARN: Code duplicated, block: B:93:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:94:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:97:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:98:0x01d4  */
    /* JADX INFO: renamed from: LinearProgressIndicator-rIrjwxo, reason: not valid java name */
    public static final void m760LinearProgressIndicatorrIrjwxo(Modifier modifier, long j, long j2, int i, float f, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long linearColor;
        long linearTrackColor;
        int iM745getLinearStrokeCapKaPHkGw;
        int i5;
        final float f2;
        int i6;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final long j3;
        final long j4;
        final int i7;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        final float fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
        final int i8;
        final State stateAnimateFloat;
        final State stateAnimateFloat2;
        final State stateAnimateFloat3;
        final State stateAnimateFloat4;
        boolean z2;
        boolean z3;
        boolean zChanged;
        Object objRememberedValue;
        final long j5;
        final long j6;
        Composer composerStartRestartGroup = composer.startRestartGroup(567589233);
        int i9 = i3 & 1;
        if (i9 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            linearColor = j;
            i4 |= ((i3 & 2) == 0 && composerStartRestartGroup.changed(linearColor)) ? 32 : 16;
        } else {
            linearColor = j;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            linearTrackColor = j2;
            i4 |= ((i3 & 4) == 0 && composerStartRestartGroup.changed(linearTrackColor)) ? 256 : 128;
        } else {
            linearTrackColor = j2;
        }
        int i10 = i3 & 8;
        if (i10 == 0) {
            if ((i2 & 3072) == 0) {
                iM745getLinearStrokeCapKaPHkGw = i;
                i4 |= composerStartRestartGroup.changed(iM745getLinearStrokeCapKaPHkGw) ? 2048 : 1024;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    f2 = f;
                    if (composerStartRestartGroup.changed(f2)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i4 |= i6;
                }
                if ((i4 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i9 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 2) != 0) {
                            linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                            i4 &= -113;
                        }
                        if ((i3 & 4) != 0) {
                            linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i10 != 0) {
                            iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                        }
                        if (i5 != 0) {
                            fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                        }
                        i8 = iM745getLinearStrokeCapKaPHkGw;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:254)");
                        }
                        InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition((String) null, composerStartRestartGroup, 0, 1);
                        InfiniteRepeatableSpec<Float> linearIndeterminateFirstLineHeadAnimationSpec = getLinearIndeterminateFirstLineHeadAnimationSpec();
                        int i11 = InfiniteTransition.$stable;
                        int i12 = InfiniteRepeatableSpec.$stable;
                        stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, linearIndeterminateFirstLineHeadAnimationSpec, (String) null, composerStartRestartGroup, i11 | 432 | (i12 << 9), 8);
                        stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, getLinearIndeterminateFirstLineTailAnimationSpec(), (String) null, composerStartRestartGroup, i11 | 432 | (i12 << 9), 8);
                        stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, getLinearIndeterminateSecondLineHeadAnimationSpec(), (String) null, composerStartRestartGroup, i11 | 432 | (i12 << 9), 8);
                        stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, getLinearIndeterminateSecondLineTailAnimationSpec(), (String) null, composerStartRestartGroup, i11 | 432 | (i12 << 9), 8);
                        composer2 = composerStartRestartGroup;
                        Modifier modifier5 = SizeKt.size-VpY3zN4(ProgressSemanticsKt.progressSemantics(modifier4.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds())), LinearIndicatorWidth, LinearIndicatorHeight);
                        if ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((57344 & i4) == 16384) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        zChanged = z3 | z2 | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composer2.changed(linearTrackColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
                        objRememberedValue = composer2.rememberedValue();
                        if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            j5 = linearColor;
                            j6 = linearTrackColor;
                            objRememberedValue = new Function1() { // from class: ycb
                                public final Object invoke(Object obj) {
                                    return ProgressIndicatorKt.n(i8, fM744getLinearIndicatorTrackGapSizeD9Ej5fM, stateAnimateFloat, j6, stateAnimateFloat2, j5, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue);
                        } else {
                            j5 = linearColor;
                            j6 = linearTrackColor;
                        }
                        CanvasKt.Canvas(modifier5, (Function1) objRememberedValue, composer2, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        i7 = i8;
                        f2 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                        j4 = j6;
                        j3 = j5;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 2) != 0) {
                            i4 &= -113;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        modifier4 = modifier2;
                    }
                    fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    i8 = iM745getLinearStrokeCapKaPHkGw;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:254)");
                    }
                    InfiniteTransition infiniteTransitionRememberInfiniteTransition2 = InfiniteTransitionKt.rememberInfiniteTransition((String) null, composerStartRestartGroup, 0, 1);
                    InfiniteRepeatableSpec<Float> linearIndeterminateFirstLineHeadAnimationSpec2 = getLinearIndeterminateFirstLineHeadAnimationSpec();
                    int i13 = InfiniteTransition.$stable;
                    int i14 = InfiniteRepeatableSpec.$stable;
                    stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, 1.0f, linearIndeterminateFirstLineHeadAnimationSpec2, (String) null, composerStartRestartGroup, i13 | 432 | (i14 << 9), 8);
                    stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, 1.0f, getLinearIndeterminateFirstLineTailAnimationSpec(), (String) null, composerStartRestartGroup, i13 | 432 | (i14 << 9), 8);
                    stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, 1.0f, getLinearIndeterminateSecondLineHeadAnimationSpec(), (String) null, composerStartRestartGroup, i13 | 432 | (i14 << 9), 8);
                    stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, 1.0f, getLinearIndeterminateSecondLineTailAnimationSpec(), (String) null, composerStartRestartGroup, i13 | 432 | (i14 << 9), 8);
                    composer2 = composerStartRestartGroup;
                    Modifier modifier6 = SizeKt.size-VpY3zN4(ProgressSemanticsKt.progressSemantics(modifier4.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds())), LinearIndicatorWidth, LinearIndicatorHeight);
                    if ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((57344 & i4) == 16384) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    zChanged = z3 | z2 | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composer2.changed(linearTrackColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
                    objRememberedValue = composer2.rememberedValue();
                    if (zChanged) {
                        j5 = linearColor;
                        j6 = linearTrackColor;
                        objRememberedValue = new Function1() { // from class: ycb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.n(i8, fM744getLinearIndicatorTrackGapSizeD9Ej5fM, stateAnimateFloat, j6, stateAnimateFloat2, j5, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue);
                    } else {
                        j5 = linearColor;
                        j6 = linearTrackColor;
                        objRememberedValue = new Function1() { // from class: ycb
                            public final Object invoke(Object obj) {
                                return ProgressIndicatorKt.n(i8, fM744getLinearIndicatorTrackGapSizeD9Ej5fM, stateAnimateFloat, j6, stateAnimateFloat2, j5, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    CanvasKt.Canvas(modifier6, (Function1) objRememberedValue, composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    i7 = i8;
                    f2 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                    j4 = j6;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    i7 = iM745getLinearStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zcb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.g(modifier3, j3, j4, i7, f2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            f2 = f;
            if ((i4 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                }
                i8 = iM745getLinearStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:254)");
                }
                InfiniteTransition infiniteTransitionRememberInfiniteTransition3 = InfiniteTransitionKt.rememberInfiniteTransition((String) null, composerStartRestartGroup, 0, 1);
                InfiniteRepeatableSpec<Float> linearIndeterminateFirstLineHeadAnimationSpec3 = getLinearIndeterminateFirstLineHeadAnimationSpec();
                int i15 = InfiniteTransition.$stable;
                int i16 = InfiniteRepeatableSpec.$stable;
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, 1.0f, linearIndeterminateFirstLineHeadAnimationSpec3, (String) null, composerStartRestartGroup, i15 | 432 | (i16 << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, 1.0f, getLinearIndeterminateFirstLineTailAnimationSpec(), (String) null, composerStartRestartGroup, i15 | 432 | (i16 << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, 1.0f, getLinearIndeterminateSecondLineHeadAnimationSpec(), (String) null, composerStartRestartGroup, i15 | 432 | (i16 << 9), 8);
                stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, 1.0f, getLinearIndeterminateSecondLineTailAnimationSpec(), (String) null, composerStartRestartGroup, i15 | 432 | (i16 << 9), 8);
                composer2 = composerStartRestartGroup;
                Modifier modifier7 = SizeKt.size-VpY3zN4(ProgressSemanticsKt.progressSemantics(modifier4.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds())), LinearIndicatorWidth, LinearIndicatorHeight);
                if ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((57344 & i4) == 16384) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                zChanged = z3 | z2 | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composer2.changed(linearTrackColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
                objRememberedValue = composer2.rememberedValue();
                if (zChanged) {
                    j5 = linearColor;
                    j6 = linearTrackColor;
                    objRememberedValue = new Function1() { // from class: ycb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.n(i8, fM744getLinearIndicatorTrackGapSizeD9Ej5fM, stateAnimateFloat, j6, stateAnimateFloat2, j5, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                } else {
                    j5 = linearColor;
                    j6 = linearTrackColor;
                    objRememberedValue = new Function1() { // from class: ycb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.n(i8, fM744getLinearIndicatorTrackGapSizeD9Ej5fM, stateAnimateFloat, j6, stateAnimateFloat2, j5, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                CanvasKt.Canvas(modifier7, (Function1) objRememberedValue, composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                i7 = i8;
                f2 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                j4 = j6;
                j3 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = linearColor;
                j4 = linearTrackColor;
                i7 = iM745getLinearStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zcb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.g(modifier3, j3, j4, i7, f2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        iM745getLinearStrokeCapKaPHkGw = i;
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                f2 = f;
                if (composerStartRestartGroup.changed(f2)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i4 |= i6;
            }
            if ((i4 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                }
                i8 = iM745getLinearStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:254)");
                }
                InfiniteTransition infiniteTransitionRememberInfiniteTransition4 = InfiniteTransitionKt.rememberInfiniteTransition((String) null, composerStartRestartGroup, 0, 1);
                InfiniteRepeatableSpec<Float> linearIndeterminateFirstLineHeadAnimationSpec4 = getLinearIndeterminateFirstLineHeadAnimationSpec();
                int i17 = InfiniteTransition.$stable;
                int i18 = InfiniteRepeatableSpec.$stable;
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, 1.0f, linearIndeterminateFirstLineHeadAnimationSpec4, (String) null, composerStartRestartGroup, i17 | 432 | (i18 << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, 1.0f, getLinearIndeterminateFirstLineTailAnimationSpec(), (String) null, composerStartRestartGroup, i17 | 432 | (i18 << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, 1.0f, getLinearIndeterminateSecondLineHeadAnimationSpec(), (String) null, composerStartRestartGroup, i17 | 432 | (i18 << 9), 8);
                stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, 1.0f, getLinearIndeterminateSecondLineTailAnimationSpec(), (String) null, composerStartRestartGroup, i17 | 432 | (i18 << 9), 8);
                composer2 = composerStartRestartGroup;
                Modifier modifier8 = SizeKt.size-VpY3zN4(ProgressSemanticsKt.progressSemantics(modifier4.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds())), LinearIndicatorWidth, LinearIndicatorHeight);
                if ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((57344 & i4) == 16384) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                zChanged = z3 | z2 | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composer2.changed(linearTrackColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
                objRememberedValue = composer2.rememberedValue();
                if (zChanged) {
                    j5 = linearColor;
                    j6 = linearTrackColor;
                    objRememberedValue = new Function1() { // from class: ycb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.n(i8, fM744getLinearIndicatorTrackGapSizeD9Ej5fM, stateAnimateFloat, j6, stateAnimateFloat2, j5, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                } else {
                    j5 = linearColor;
                    j6 = linearTrackColor;
                    objRememberedValue = new Function1() { // from class: ycb
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.n(i8, fM744getLinearIndicatorTrackGapSizeD9Ej5fM, stateAnimateFloat, j6, stateAnimateFloat2, j5, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                CanvasKt.Canvas(modifier8, (Function1) objRememberedValue, composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                i7 = i8;
                f2 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
                j4 = j6;
                j3 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = linearColor;
                j4 = linearTrackColor;
                i7 = iM745getLinearStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zcb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.g(modifier3, j3, j4, i7, f2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        f2 = f;
        if ((i4 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i10 != 0) {
                    iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                }
                if (i5 != 0) {
                    fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                }
            } else {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i10 != 0) {
                    iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                }
                if (i5 != 0) {
                    fM744getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM744getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                }
            }
            i8 = iM745getLinearStrokeCapKaPHkGw;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:254)");
            }
            InfiniteTransition infiniteTransitionRememberInfiniteTransition5 = InfiniteTransitionKt.rememberInfiniteTransition((String) null, composerStartRestartGroup, 0, 1);
            InfiniteRepeatableSpec<Float> linearIndeterminateFirstLineHeadAnimationSpec5 = getLinearIndeterminateFirstLineHeadAnimationSpec();
            int i19 = InfiniteTransition.$stable;
            int i110 = InfiniteRepeatableSpec.$stable;
            stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, 1.0f, linearIndeterminateFirstLineHeadAnimationSpec5, (String) null, composerStartRestartGroup, i19 | 432 | (i110 << 9), 8);
            stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, 1.0f, getLinearIndeterminateFirstLineTailAnimationSpec(), (String) null, composerStartRestartGroup, i19 | 432 | (i110 << 9), 8);
            stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, 1.0f, getLinearIndeterminateSecondLineHeadAnimationSpec(), (String) null, composerStartRestartGroup, i19 | 432 | (i110 << 9), 8);
            stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, 1.0f, getLinearIndeterminateSecondLineTailAnimationSpec(), (String) null, composerStartRestartGroup, i19 | 432 | (i110 << 9), 8);
            composer2 = composerStartRestartGroup;
            Modifier modifier9 = SizeKt.size-VpY3zN4(ProgressSemanticsKt.progressSemantics(modifier4.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds())), LinearIndicatorWidth, LinearIndicatorHeight);
            if ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((57344 & i4) == 16384) {
                z3 = true;
            } else {
                z3 = false;
            }
            zChanged = z3 | z2 | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 && composer2.changed(linearTrackColor)) || (i4 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
            objRememberedValue = composer2.rememberedValue();
            if (zChanged) {
                j5 = linearColor;
                j6 = linearTrackColor;
                objRememberedValue = new Function1() { // from class: ycb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.n(i8, fM744getLinearIndicatorTrackGapSizeD9Ej5fM, stateAnimateFloat, j6, stateAnimateFloat2, j5, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            } else {
                j5 = linearColor;
                j6 = linearTrackColor;
                objRememberedValue = new Function1() { // from class: ycb
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.n(i8, fM744getLinearIndicatorTrackGapSizeD9Ej5fM, stateAnimateFloat, j6, stateAnimateFloat2, j5, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifier9, (Function1) objRememberedValue, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            i7 = i8;
            f2 = fM744getLinearIndicatorTrackGapSizeD9Ej5fM;
            j4 = j6;
            j3 = j5;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = linearColor;
            j4 = linearTrackColor;
            i7 = iM745getLinearStrokeCapKaPHkGw;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zcb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.g(modifier3, j3, j4, i7, f2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static float a(Function0 function0) {
        float fFloatValue = ((Number) function0.invoke()).floatValue();
        if (fFloatValue < 0.0f) {
            fFloatValue = 0.0f;
        }
        if (fFloatValue > 1.0f) {
            return 1.0f;
        }
        return fFloatValue;
    }

    public static Unit b(float f, Modifier modifier, long j, float f2, int i, int i2, Composer composer, int i3) {
        m752CircularProgressIndicatorMBs18nI(f, modifier, j, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static float c(Function0 function0) {
        float fFloatValue = ((Number) function0.invoke()).floatValue();
        if (fFloatValue < 0.0f) {
            fFloatValue = 0.0f;
        }
        if (fFloatValue > 1.0f) {
            return 1.0f;
        }
        return fFloatValue;
    }

    public static Unit d(Modifier modifier, long j, float f, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m751CircularProgressIndicatorLxG7B9w(modifier, j, f, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m761drawCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        float width = stroke.getWidth() / 2.0f;
        float fIntBitsToFloat = Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)) - (2.0f * width);
        DrawScope.m3687drawArcyD3GUKo$default(drawScope, j, f, f2, false, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(width)) & 4294967295L) | (Float.floatToRawIntBits(width) << 32)), Size.m2949constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat)) & 4294967295L)), 0.0f, stroke, null, 0, 832, null);
    }

    /* JADX INFO: renamed from: drawCircularIndicatorTrack-bw27NRU, reason: not valid java name */
    private static final void m762drawCircularIndicatorTrackbw27NRU(DrawScope drawScope, long j, Stroke stroke) {
        m761drawCircularIndicator42QJj7c(drawScope, 0.0f, 360.0f, j, stroke);
    }

    /* JADX INFO: renamed from: drawDeterminateCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m763drawDeterminateCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        m761drawCircularIndicator42QJj7c(drawScope, f, f2, j, stroke);
    }

    /* JADX INFO: renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    private static final void m764drawIndeterminateCircularIndicatorhrjfTZI(DrawScope drawScope, float f, float f2, float f3, long j, Stroke stroke) {
        m761drawCircularIndicator42QJj7c(drawScope, f + (StrokeCap.m3504equalsimpl0(stroke.getCap(), StrokeCap.INSTANCE.m3508getButtKaPHkGw()) ? 0.0f : ((f2 / Dp.m6022constructorimpl(CircularIndicatorDiameter / 2.0f)) * 57.29578f) / 2.0f), Math.max(f3, 0.1f), j, stroke);
    }

    /* JADX INFO: renamed from: drawLinearIndicator-qYKTg0g, reason: not valid java name */
    private static final void m765drawLinearIndicatorqYKTg0g(DrawScope drawScope, float f, float f2, long j, float f3, int i) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L));
        float f4 = fIntBitsToFloat2 / 2.0f;
        boolean z = drawScope.getLayoutDirection() == LayoutDirection.Ltr;
        float f5 = (z ? f : 1.0f - f2) * fIntBitsToFloat;
        float f6 = (z ? f2 : 1.0f - f) * fIntBitsToFloat;
        if (StrokeCap.m3504equalsimpl0(i, StrokeCap.INSTANCE.m3508getButtKaPHkGw()) || fIntBitsToFloat2 > fIntBitsToFloat) {
            DrawScope.m3694drawLineNGM6Ib0$default(drawScope, j, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(f5)) << 32) | (((long) Float.floatToRawIntBits(f4)) & 4294967295L)), Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(f6)) << 32) | (((long) Float.floatToRawIntBits(f4)) & 4294967295L)), f3, 0, null, 0.0f, null, 0, 496, null);
            return;
        }
        float f7 = f3 / 2.0f;
        float f8 = fIntBitsToFloat - f7;
        if (f5 < f7) {
            f5 = f7;
        }
        if (f5 > f8) {
            f5 = f8;
        }
        if (f6 < f7) {
            f6 = f7;
        }
        if (f6 <= f8) {
            f8 = f6;
        }
        if (Math.abs(f2 - f) > 0.0f) {
            DrawScope.m3694drawLineNGM6Ib0$default(drawScope, j, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(f5)) << 32) | (((long) Float.floatToRawIntBits(f4)) & 4294967295L)), Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(f8)) << 32) | (((long) Float.floatToRawIntBits(f4)) & 4294967295L)), f3, i, null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
        }
    }

    public static Unit e(long j, int i, DrawScope drawScope) {
        ProgressIndicatorDefaults progressIndicatorDefaults = ProgressIndicatorDefaults.INSTANCE;
        progressIndicatorDefaults.m739drawStopIndicatorEgI2THU(drawScope, progressIndicatorDefaults.m746getLinearTrackStopIndicatorSizeD9Ej5fM(), j, i);
        return Unit.INSTANCE;
    }

    public static Unit f(KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig) {
        keyframesSpecConfig.setDurationMillis(LinearAnimationDuration);
        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), LinearIndeterminateProgressEasing);
        keyframesSpecConfig.at(Float.valueOf(1.0f), 1000);
        return Unit.INSTANCE;
    }

    public static Unit g(Modifier modifier, long j, long j2, int i, float f, int i2, int i3, Composer composer, int i4) {
        m760LinearProgressIndicatorrIrjwxo(modifier, j, j2, i, f, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getCircularIndeterminateGlobalRotationAnimationSpec() {
        return AnimationSpecKt.infiniteRepeatable-9IiC70o$default(AnimationSpecKt.tween$default(CircularAnimationProgressDuration, 0, EasingKt.getLinearEasing(), 2, (Object) null), (RepeatMode) null, 0L, 6, (Object) null);
    }

    public static final InfiniteRepeatableSpec<Float> getCircularIndeterminateProgressAnimationSpec() {
        return AnimationSpecKt.infiniteRepeatable-9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: edb
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt.p((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), (RepeatMode) null, 0L, 6, (Object) null);
    }

    public static final InfiniteRepeatableSpec<Float> getCircularIndeterminateRotationAnimationSpec() {
        return AnimationSpecKt.infiniteRepeatable-9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: gdb
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt.D((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), (RepeatMode) null, 0L, 6, (Object) null);
    }

    public static final float getCircularIndicatorDiameter() {
        return CircularIndicatorDiameter;
    }

    public static final CubicBezierEasing getCircularProgressEasing() {
        return CircularProgressEasing;
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateFirstLineHeadAnimationSpec() {
        return AnimationSpecKt.infiniteRepeatable-9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: rcb
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt.f((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), (RepeatMode) null, 0L, 6, (Object) null);
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateFirstLineTailAnimationSpec() {
        return AnimationSpecKt.infiniteRepeatable-9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: adb
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt.j((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), (RepeatMode) null, 0L, 6, (Object) null);
    }

    public static final CubicBezierEasing getLinearIndeterminateProgressEasing() {
        return LinearIndeterminateProgressEasing;
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateSecondLineHeadAnimationSpec() {
        return AnimationSpecKt.infiniteRepeatable-9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: idb
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt.C((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), (RepeatMode) null, 0L, 6, (Object) null);
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateSecondLineTailAnimationSpec() {
        return AnimationSpecKt.infiniteRepeatable-9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: kdb
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt.q((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), (RepeatMode) null, 0L, 6, (Object) null);
    }

    public static final float getLinearIndicatorHeight() {
        return LinearIndicatorHeight;
    }

    public static final float getLinearIndicatorWidth() {
        return LinearIndicatorWidth;
    }

    public static final float getStopIndicatorTrailingSpace() {
        return StopIndicatorTrailingSpace;
    }

    public static Unit h(Function0 function0, Modifier modifier, long j, float f, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m749CircularProgressIndicatorDUhRLBM(function0, modifier, j, f, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit i(float f, Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m757LinearProgressIndicator_5eSRE(f, modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit j(KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig) {
        keyframesSpecConfig.setDurationMillis(LinearAnimationDuration);
        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), FirstLineTailDelay), LinearIndeterminateProgressEasing);
        keyframesSpecConfig.at(Float.valueOf(1.0f), 1250);
        return Unit.INSTANCE;
    }

    public static Unit k(float f, Modifier modifier, long j, long j2, int i, int i2, Composer composer, int i3) {
        m759LinearProgressIndicatoreaDK9VM(f, modifier, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit l(Function0 function0, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Object objInvoke = function0.invoke();
        if (Float.isNaN(((Number) objInvoke).floatValue())) {
            objInvoke = null;
        }
        Float f = (Float) objInvoke;
        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(f != null ? f.floatValue() : 0.0f, RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
        return Unit.INSTANCE;
    }

    public static Unit m(State state, int i, float f, float f2, State state2, State state3, long j, Stroke stroke, long j2, DrawScope drawScope) {
        float fFloatValue = ((Number) state.getValue()).floatValue() * 360.0f;
        if (!StrokeCap.m3504equalsimpl0(i, StrokeCap.INSTANCE.m3508getButtKaPHkGw()) && Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L)) <= Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32))) {
            f = Dp.m6022constructorimpl(f + f2);
        }
        float fMo4553toDpu2uoSUM = (f / ((float) (((double) drawScope.mo4553toDpu2uoSUM(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)))) * 3.141592653589793d))) * 360.0f;
        float fFloatValue2 = ((Number) state2.getValue()).floatValue() + ((Number) state3.getValue()).floatValue();
        long jMo3707getCenterF1C5BW0 = drawScope.mo3707getCenterF1C5BW0();
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3629getSizeNHjbRc = drawContext.mo3629getSizeNHjbRc();
        drawContext.getCanvas().save();
        try {
            drawContext.getTransform().mo3635rotateUv8p0NA(fFloatValue2, jMo3707getCenterF1C5BW0);
            m761drawCircularIndicator42QJj7c(drawScope, fFloatValue + Math.min(fFloatValue, fMo4553toDpu2uoSUM), (360.0f - fFloatValue) - (Math.min(fFloatValue, fMo4553toDpu2uoSUM) * 2.0f), j, stroke);
            m763drawDeterminateCircularIndicator42QJj7c(drawScope, 0.0f, fFloatValue, j2, stroke);
            return Unit.INSTANCE;
        } finally {
            drawContext.getCanvas().restore();
            drawContext.mo3630setSizeuvyYCjk(jMo3629getSizeNHjbRc);
        }
    }

    public static Unit n(int i, float f, State state, long j, State state2, long j2, State state3, State state4, DrawScope drawScope) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L));
        if (!StrokeCap.m3504equalsimpl0(i, StrokeCap.INSTANCE.m3508getButtKaPHkGw()) && Float.intBitsToFloat((int) (4294967295L & drawScope.mo3708getSizeNHjbRc())) <= Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32))) {
            f = Dp.m6022constructorimpl(f + drawScope.mo4553toDpu2uoSUM(fIntBitsToFloat));
        }
        float fMo4553toDpu2uoSUM = f / drawScope.mo4553toDpu2uoSUM(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)));
        if (((Number) state.getValue()).floatValue() < 1.0f - fMo4553toDpu2uoSUM) {
            m765drawLinearIndicatorqYKTg0g(drawScope, ((Number) state.getValue()).floatValue() > 0.0f ? ((Number) state.getValue()).floatValue() + fMo4553toDpu2uoSUM : 0.0f, 1.0f, j, fIntBitsToFloat, i);
        }
        if (((Number) state.getValue()).floatValue() - ((Number) state2.getValue()).floatValue() > 0.0f) {
            m765drawLinearIndicatorqYKTg0g(drawScope, ((Number) state.getValue()).floatValue(), ((Number) state2.getValue()).floatValue(), j2, fIntBitsToFloat, i);
        }
        if (((Number) state2.getValue()).floatValue() > fMo4553toDpu2uoSUM) {
            m765drawLinearIndicatorqYKTg0g(drawScope, ((Number) state3.getValue()).floatValue() > 0.0f ? ((Number) state3.getValue()).floatValue() + fMo4553toDpu2uoSUM : 0.0f, ((Number) state2.getValue()).floatValue() < 1.0f ? ((Number) state2.getValue()).floatValue() - fMo4553toDpu2uoSUM : 1.0f, j, fIntBitsToFloat, i);
        }
        if (((Number) state3.getValue()).floatValue() - ((Number) state4.getValue()).floatValue() > 0.0f) {
            m765drawLinearIndicatorqYKTg0g(drawScope, ((Number) state3.getValue()).floatValue(), ((Number) state4.getValue()).floatValue(), j2, fIntBitsToFloat, i);
        }
        if (((Number) state4.getValue()).floatValue() > fMo4553toDpu2uoSUM) {
            m765drawLinearIndicatorqYKTg0g(drawScope, 0.0f, ((Number) state4.getValue()).floatValue() < 1.0f ? ((Number) state4.getValue()).floatValue() - fMo4553toDpu2uoSUM : 1.0f, j, fIntBitsToFloat, i);
        }
        return Unit.INSTANCE;
    }

    public static float o(float f) {
        return f;
    }

    public static Unit p(KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig) {
        keyframesSpecConfig.setDurationMillis(CircularAnimationProgressDuration);
        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.87f), 3000), CircularProgressEasing);
        keyframesSpecConfig.at(Float.valueOf(0.1f), CircularAnimationProgressDuration);
        return Unit.INSTANCE;
    }

    public static Unit q(KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig) {
        keyframesSpecConfig.setDurationMillis(LinearAnimationDuration);
        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 900), LinearIndeterminateProgressEasing);
        keyframesSpecConfig.at(Float.valueOf(1.0f), LinearAnimationDuration);
        return Unit.INSTANCE;
    }

    public static Unit r(float f, Modifier modifier, long j, float f2, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m748CircularProgressIndicatorDUhRLBM(f, modifier, j, f2, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit s(int i, float f, Function0 function0, long j, long j2, Function1 function1, DrawScope drawScope) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L));
        if (!StrokeCap.m3504equalsimpl0(i, StrokeCap.INSTANCE.m3508getButtKaPHkGw()) && Float.intBitsToFloat((int) (4294967295L & drawScope.mo3708getSizeNHjbRc())) <= Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32))) {
            f = Dp.m6022constructorimpl(f + drawScope.mo4553toDpu2uoSUM(fIntBitsToFloat));
        }
        float fMo4553toDpu2uoSUM = f / drawScope.mo4553toDpu2uoSUM(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)));
        float fFloatValue = ((Number) function0.invoke()).floatValue();
        float fMin = fFloatValue + Math.min(fFloatValue, fMo4553toDpu2uoSUM);
        if (fMin <= 1.0f) {
            m765drawLinearIndicatorqYKTg0g(drawScope, fMin, 1.0f, j, fIntBitsToFloat, i);
        }
        m765drawLinearIndicatorqYKTg0g(drawScope, 0.0f, fFloatValue, j2, fIntBitsToFloat, i);
        function1.invoke(drawScope);
        return Unit.INSTANCE;
    }

    public static Unit t(Modifier modifier, long j, float f, int i, int i2, Composer composer, int i3) {
        m753CircularProgressIndicatoraMcp0Q(modifier, j, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit u(Function0 function0, Modifier modifier, long j, float f, long j2, int i, float f2, int i2, int i3, Composer composer, int i4) {
        m750CircularProgressIndicatorIyT6zlY(function0, modifier, j, f, j2, i, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit v(Function0 function0, int i, float f, float f2, long j, Stroke stroke, long j2, DrawScope drawScope) {
        float fFloatValue = ((Number) function0.invoke()).floatValue() * 360.0f;
        if (!StrokeCap.m3504equalsimpl0(i, StrokeCap.INSTANCE.m3508getButtKaPHkGw()) && Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L)) <= Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32))) {
            f = Dp.m6022constructorimpl(f + f2);
        }
        float fMo4553toDpu2uoSUM = (f / ((float) (((double) drawScope.mo4553toDpu2uoSUM(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)))) * 3.141592653589793d))) * 360.0f;
        m761drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, fMo4553toDpu2uoSUM), (360.0f - fFloatValue) - (Math.min(fFloatValue, fMo4553toDpu2uoSUM) * 2.0f), j, stroke);
        m763drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j2, stroke);
        return Unit.INSTANCE;
    }

    public static Unit w(Function0 function0, Modifier modifier, long j, long j2, int i, float f, Function1 function1, int i2, int i3, Composer composer, int i4) {
        m755LinearProgressIndicatorGJbTh5U(function0, modifier, j, j2, i, f, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit x(Modifier modifier, long j, float f, long j2, int i, float f2, int i2, int i3, Composer composer, int i4) {
        m747CircularProgressIndicator4lLiAd8(modifier, j, f, j2, i, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit y(Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m754LinearProgressIndicator2cYBFYY(modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit z(Function0 function0, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Object objInvoke = function0.invoke();
        if (Float.isNaN(((Number) objInvoke).floatValue())) {
            objInvoke = null;
        }
        Float f = (Float) objInvoke;
        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(f != null ? f.floatValue() : 0.0f, RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x0049  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:31:0x0054  */
    /* JADX WARN: Code duplicated, block: B:34:0x005a  */
    /* JADX WARN: Code duplicated, block: B:37:0x0060  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:44:0x0073  */
    /* JADX WARN: Code duplicated, block: B:47:0x0079  */
    /* JADX WARN: Code duplicated, block: B:49:0x007e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0082  */
    /* JADX WARN: Code duplicated, block: B:53:0x008a  */
    /* JADX WARN: Code duplicated, block: B:54:0x008d  */
    /* JADX WARN: Code duplicated, block: B:58:0x0096  */
    /* JADX WARN: Code duplicated, block: B:59:0x0098  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:75:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:76:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:83:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:93:0x012c  */
    /* JADX WARN: Code duplicated, block: B:95:0x0134  */
    /* JADX WARN: Code duplicated, block: B:98:0x0143  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize` and `drawStopIndicator`, see `LegacyLinearProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(progress, modifier, color, trackColor, strokeCap, gapSize, drawStopIndicator)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    public static final /* synthetic */ void m758LinearProgressIndicator_5eSRE(final Function0 function0, Modifier modifier, long j, long j2, int i, Composer composer, final int i2, final int i3) {
        Function0 function1;
        int i4;
        Modifier modifier2;
        long j3;
        long linearTrackColor;
        int i5;
        int i6;
        int i7;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final long j4;
        final long j5;
        final int i8;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        long linearColor;
        int iM745getLinearStrokeCapKaPHkGw;
        Modifier modifier5;
        long j6;
        int i9;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1796992155);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i2 & 6) == 0) {
                i4 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
        }
        int i10 = i3 & 2;
        if (i10 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i3 & 4) == 0) {
                    j3 = j;
                    int i11 = composerStartRestartGroup.changed(j3) ? 256 : 128;
                    i4 |= i11;
                } else {
                    j3 = j;
                }
                i4 |= i11;
            } else {
                j3 = j;
            }
            if ((i2 & 3072) == 0) {
                linearTrackColor = j2;
                if ((i3 & 8) == 0 || !composerStartRestartGroup.changed(linearTrackColor)) {
                    i9 = 1024;
                } else {
                    i9 = 2048;
                }
                i4 |= i9;
            } else {
                linearTrackColor = j2;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    i6 = i;
                    if (composerStartRestartGroup.changed(i6)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i4 |= i7;
                }
                if ((i4 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        } else {
                            linearColor = j3;
                        }
                        if ((i3 & 8) != 0) {
                            linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                            i4 &= -7169;
                        }
                        if (i5 != 0) {
                            iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                        } else {
                            iM745getLinearStrokeCapKaPHkGw = i6;
                        }
                        modifier5 = modifier4;
                        j6 = linearColor;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                        linearTrackColor = linearTrackColor;
                        iM745getLinearStrokeCapKaPHkGw = i6;
                        modifier5 = modifier2;
                        j6 = j3;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:100)");
                    }
                    composer2 = composerStartRestartGroup;
                    m755LinearProgressIndicatorGJbTh5U(function1, modifier5, j6, linearTrackColor, iM745getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i4 & 57344), 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    j4 = j6;
                    j5 = linearTrackColor;
                    i8 = iM745getLinearStrokeCapKaPHkGw;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j4 = j3;
                    j5 = linearTrackColor;
                    i8 = i6;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ndb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.B(function0, modifier3, j4, j5, i8, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            i6 = i;
            if ((i4 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier5 = modifier4;
                    j6 = linearColor;
                } else {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier5 = modifier4;
                    j6 = linearColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:100)");
                }
                composer2 = composerStartRestartGroup;
                m755LinearProgressIndicatorGJbTh5U(function1, modifier5, j6, linearTrackColor, iM745getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i4 & 57344), 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j4 = j6;
                j5 = linearTrackColor;
                i8 = iM745getLinearStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j4 = j3;
                j5 = linearTrackColor;
                i8 = i6;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ndb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.B(function0, modifier3, j4, j5, i8, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i3 & 4) == 0) {
                j3 = j;
                if (composerStartRestartGroup.changed(j3)) {
                }
                i4 |= i11;
            } else {
                j3 = j;
            }
            i4 |= i11;
        } else {
            j3 = j;
        }
        if ((i2 & 3072) == 0) {
            linearTrackColor = j2;
            if ((i3 & 8) == 0) {
                i9 = 1024;
            } else {
                i9 = 1024;
            }
            i4 |= i9;
        } else {
            linearTrackColor = j2;
        }
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                i6 = i;
                if (composerStartRestartGroup.changed(i6)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i4 |= i7;
            }
            if ((i4 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier5 = modifier4;
                    j6 = linearColor;
                } else {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                    } else {
                        iM745getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier5 = modifier4;
                    j6 = linearColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:100)");
                }
                composer2 = composerStartRestartGroup;
                m755LinearProgressIndicatorGJbTh5U(function1, modifier5, j6, linearTrackColor, iM745getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i4 & 57344), 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j4 = j6;
                j5 = linearTrackColor;
                i8 = iM745getLinearStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j4 = j3;
                j5 = linearTrackColor;
                i8 = i6;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ndb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.B(function0, modifier3, j4, j5, i8, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        i6 = i;
        if ((i4 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i10 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                } else {
                    iM745getLinearStrokeCapKaPHkGw = i6;
                }
                modifier5 = modifier4;
                j6 = linearColor;
            } else {
                if (i10 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM745getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m745getLinearStrokeCapKaPHkGw();
                } else {
                    iM745getLinearStrokeCapKaPHkGw = i6;
                }
                modifier5 = modifier4;
                j6 = linearColor;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:100)");
            }
            composer2 = composerStartRestartGroup;
            m755LinearProgressIndicatorGJbTh5U(function1, modifier5, j6, linearTrackColor, iM745getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m744getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i4 & 57344), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j4 = j6;
            j5 = linearTrackColor;
            i8 = iM745getLinearStrokeCapKaPHkGw;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j4 = j3;
            j5 = linearTrackColor;
            i8 = i6;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ndb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.B(function0, modifier3, j4, j5, i8, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0124  */
    /* JADX WARN: Code duplicated, block: B:103:0x0134  */
    /* JADX WARN: Code duplicated, block: B:106:0x0165  */
    /* JADX WARN: Code duplicated, block: B:108:0x016f  */
    /* JADX WARN: Code duplicated, block: B:111:0x017f  */
    /* JADX WARN: Code duplicated, block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x0049  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:31:0x0054  */
    /* JADX WARN: Code duplicated, block: B:34:0x005a  */
    /* JADX WARN: Code duplicated, block: B:37:0x0060  */
    /* JADX WARN: Code duplicated, block: B:39:0x0065  */
    /* JADX WARN: Code duplicated, block: B:41:0x0069  */
    /* JADX WARN: Code duplicated, block: B:43:0x0071  */
    /* JADX WARN: Code duplicated, block: B:44:0x0074  */
    /* JADX WARN: Code duplicated, block: B:48:0x007b  */
    /* JADX WARN: Code duplicated, block: B:50:0x007f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0087  */
    /* JADX WARN: Code duplicated, block: B:53:0x008a  */
    /* JADX WARN: Code duplicated, block: B:56:0x0090  */
    /* JADX WARN: Code duplicated, block: B:59:0x0098  */
    /* JADX WARN: Code duplicated, block: B:61:0x009c  */
    /* JADX WARN: Code duplicated, block: B:63:0x009f  */
    /* JADX WARN: Code duplicated, block: B:65:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:66:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:76:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ea A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:87:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:93:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:96:0x010a  */
    /* JADX WARN: Code duplicated, block: B:98:0x0114  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`, see `LegacyCircularProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(progress, modifier, color, strokeWidth, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    public static final /* synthetic */ void m749CircularProgressIndicatorDUhRLBM(final Function0 function0, Modifier modifier, long j, float f, long j2, int i, Composer composer, final int i2, final int i3) {
        Function0 function1;
        int i4;
        Modifier modifier2;
        long circularColor;
        int i5;
        float fM743getCircularStrokeWidthD9Ej5fM;
        int i6;
        long circularDeterminateTrackColor;
        int i7;
        int i8;
        int i9;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final long j3;
        final float f2;
        final long j4;
        final int i10;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int iM740getCircularDeterminateStrokeCapKaPHkGw;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-761680467);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i2 & 6) == 0) {
                i4 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
        }
        int i12 = i3 & 2;
        if (i12 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i3 & 4) == 0) {
                    circularColor = j;
                    int i13 = composerStartRestartGroup.changed(circularColor) ? 256 : 128;
                    i4 |= i13;
                } else {
                    circularColor = j;
                }
                i4 |= i13;
            } else {
                circularColor = j;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i2 & 3072) == 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = f;
                    if (composerStartRestartGroup.changed(fM743getCircularStrokeWidthD9Ej5fM)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                if ((i2 & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        circularDeterminateTrackColor = j2;
                        int i14 = composerStartRestartGroup.changed(circularDeterminateTrackColor) ? 16384 : 8192;
                        i4 |= i14;
                    } else {
                        circularDeterminateTrackColor = j2;
                    }
                    i4 |= i14;
                } else {
                    circularDeterminateTrackColor = j2;
                }
                i7 = i3 & 32;
                if (i7 != 0) {
                    if ((196608 & i2) == 0) {
                        i8 = i;
                        if (composerStartRestartGroup.changed(i8)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i4 |= i9;
                    }
                    if ((74899 & i4) != 74898) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i12 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if ((i3 & 4) != 0) {
                                circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                                i4 &= -897;
                            }
                            if (i5 != 0) {
                                fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                            }
                            if ((i3 & 16) != 0) {
                                i4 &= -57345;
                                circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                            }
                            if (i7 != 0) {
                                iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                            } else {
                                iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                            }
                            i11 = -761680467;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                            }
                            if ((i3 & 16) != 0) {
                                i4 &= -57345;
                            }
                            circularDeterminateTrackColor = circularDeterminateTrackColor;
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i11 = -761680467;
                            modifier4 = modifier2;
                            circularColor = circularColor;
                            fM743getCircularStrokeWidthD9Ej5fM = fM743getCircularStrokeWidthD9Ej5fM;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:480)");
                        }
                        composer2 = composerStartRestartGroup;
                        m750CircularProgressIndicatorIyT6zlY(function1, modifier4, circularColor, fM743getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM740getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4) | (i4 & 458752), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        j3 = circularColor;
                        f2 = fM743getCircularStrokeWidthD9Ej5fM;
                        j4 = circularDeterminateTrackColor;
                        i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        j3 = circularColor;
                        f2 = fM743getCircularStrokeWidthD9Ej5fM;
                        j4 = circularDeterminateTrackColor;
                        i10 = i8;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cdb
                            public final Object invoke(Object obj, Object obj2) {
                                return ProgressIndicatorKt.h(function0, modifier3, j3, f2, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i8 = i;
                if ((74899 & i4) != 74898) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i11 = -761680467;
                    } else {
                        if (i12 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i11 = -761680467;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:480)");
                    }
                    composer2 = composerStartRestartGroup;
                    m750CircularProgressIndicatorIyT6zlY(function1, modifier4, circularColor, fM743getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM740getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4) | (i4 & 458752), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    j3 = circularColor;
                    f2 = fM743getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = circularColor;
                    f2 = fM743getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i10 = i8;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cdb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.h(function0, modifier3, j3, f2, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            fM743getCircularStrokeWidthD9Ej5fM = f;
            if ((i2 & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    circularDeterminateTrackColor = j2;
                    if (composerStartRestartGroup.changed(circularDeterminateTrackColor)) {
                    }
                    i4 |= i14;
                } else {
                    circularDeterminateTrackColor = j2;
                }
                i4 |= i14;
            } else {
                circularDeterminateTrackColor = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                if ((196608 & i2) == 0) {
                    i8 = i;
                    if (composerStartRestartGroup.changed(i8)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((74899 & i4) != 74898) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i11 = -761680467;
                    } else {
                        if (i12 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i11 = -761680467;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:480)");
                    }
                    composer2 = composerStartRestartGroup;
                    m750CircularProgressIndicatorIyT6zlY(function1, modifier4, circularColor, fM743getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM740getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4) | (i4 & 458752), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    j3 = circularColor;
                    f2 = fM743getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = circularColor;
                    f2 = fM743getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i10 = i8;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cdb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.h(function0, modifier3, j3, f2, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i8 = i;
            if ((74899 & i4) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i11 = -761680467;
                } else {
                    if (i12 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i11 = -761680467;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:480)");
                }
                composer2 = composerStartRestartGroup;
                m750CircularProgressIndicatorIyT6zlY(function1, modifier4, circularColor, fM743getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM740getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4) | (i4 & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                j3 = circularColor;
                f2 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = circularColor;
                f2 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i10 = i8;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cdb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.h(function0, modifier3, j3, f2, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i3 & 4) == 0) {
                circularColor = j;
                if (composerStartRestartGroup.changed(circularColor)) {
                }
                i4 |= i13;
            } else {
                circularColor = j;
            }
            i4 |= i13;
        } else {
            circularColor = j;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i2 & 3072) == 0) {
                fM743getCircularStrokeWidthD9Ej5fM = f;
                if (composerStartRestartGroup.changed(fM743getCircularStrokeWidthD9Ej5fM)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            if ((i2 & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    circularDeterminateTrackColor = j2;
                    if (composerStartRestartGroup.changed(circularDeterminateTrackColor)) {
                    }
                    i4 |= i14;
                } else {
                    circularDeterminateTrackColor = j2;
                }
                i4 |= i14;
            } else {
                circularDeterminateTrackColor = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                if ((196608 & i2) == 0) {
                    i8 = i;
                    if (composerStartRestartGroup.changed(i8)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((74899 & i4) != 74898) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i11 = -761680467;
                    } else {
                        if (i12 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i11 = -761680467;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:480)");
                    }
                    composer2 = composerStartRestartGroup;
                    m750CircularProgressIndicatorIyT6zlY(function1, modifier4, circularColor, fM743getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM740getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4) | (i4 & 458752), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    j3 = circularColor;
                    f2 = fM743getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = circularColor;
                    f2 = fM743getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i10 = i8;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cdb
                        public final Object invoke(Object obj, Object obj2) {
                            return ProgressIndicatorKt.h(function0, modifier3, j3, f2, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i8 = i;
            if ((74899 & i4) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i11 = -761680467;
                } else {
                    if (i12 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i11 = -761680467;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:480)");
                }
                composer2 = composerStartRestartGroup;
                m750CircularProgressIndicatorIyT6zlY(function1, modifier4, circularColor, fM743getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM740getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4) | (i4 & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                j3 = circularColor;
                f2 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = circularColor;
                f2 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i10 = i8;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cdb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.h(function0, modifier3, j3, f2, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        fM743getCircularStrokeWidthD9Ej5fM = f;
        if ((i2 & 24576) == 0) {
            if ((i3 & 16) == 0) {
                circularDeterminateTrackColor = j2;
                if (composerStartRestartGroup.changed(circularDeterminateTrackColor)) {
                }
                i4 |= i14;
            } else {
                circularDeterminateTrackColor = j2;
            }
            i4 |= i14;
        } else {
            circularDeterminateTrackColor = j2;
        }
        i7 = i3 & 32;
        if (i7 != 0) {
            if ((196608 & i2) == 0) {
                i8 = i;
                if (composerStartRestartGroup.changed(i8)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            if ((74899 & i4) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i11 = -761680467;
                } else {
                    if (i12 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i11 = -761680467;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:480)");
                }
                composer2 = composerStartRestartGroup;
                m750CircularProgressIndicatorIyT6zlY(function1, modifier4, circularColor, fM743getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM740getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4) | (i4 & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                j3 = circularColor;
                f2 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = circularColor;
                f2 = fM743getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i10 = i8;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cdb
                    public final Object invoke(Object obj, Object obj2) {
                        return ProgressIndicatorKt.h(function0, modifier3, j3, f2, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        i8 = i;
        if ((74899 & i4) != 74898) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i12 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    i4 &= -57345;
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                }
                if (i7 != 0) {
                    iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                } else {
                    iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                }
                i11 = -761680467;
            } else {
                if (i12 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM743getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m743getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    i4 &= -57345;
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                }
                if (i7 != 0) {
                    iM740getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m740getCircularDeterminateStrokeCapKaPHkGw();
                } else {
                    iM740getCircularDeterminateStrokeCapKaPHkGw = i8;
                }
                i11 = -761680467;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i11, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:480)");
            }
            composer2 = composerStartRestartGroup;
            m750CircularProgressIndicatorIyT6zlY(function1, modifier4, circularColor, fM743getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM740getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m742getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4) | (i4 & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            j3 = circularColor;
            f2 = fM743getCircularStrokeWidthD9Ej5fM;
            j4 = circularDeterminateTrackColor;
            i10 = iM740getCircularDeterminateStrokeCapKaPHkGw;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = circularColor;
            f2 = fM743getCircularStrokeWidthD9Ej5fM;
            j4 = circularDeterminateTrackColor;
            i10 = i8;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cdb
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.h(function0, modifier3, j3, f2, j4, i10, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
