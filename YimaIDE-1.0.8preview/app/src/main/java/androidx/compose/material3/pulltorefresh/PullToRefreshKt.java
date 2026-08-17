package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.MotionSchemeKt;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.pulltorefresh.PullToRefreshKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.core.app.NotificationCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u007f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001e\b\u0002\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0012\u001aC\u0010\u0013\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00162\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0017\u0010\u0018\u001a\r\u0010\u0019\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u001a\u001a\b\u0010\u001b\u001a\u00020\tH\u0007\u001a\u001f\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0003¢\u0006\u0004\b!\u0010\"\u001a;\u0010#\u001a\u00020\u0001*\u00020$2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0016H\u0002¢\u0006\u0004\b,\u0010-\u001a\u0010\u0010.\u001a\u00020(2\u0006\u0010\u001d\u001a\u00020&H\u0002\u001aC\u0010/\u001a\u00020\u0001*\u00020$2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010+\u001a\u00020\u0016H\u0002¢\u0006\u0004\b3\u00104\"\u000e\u00105\u001a\u00020&X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u00106\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00107\"\u0010\u00108\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00107\"\u0016\u00109\u001a\u00020\u0016X\u0080\u0004¢\u0006\n\n\u0002\u00107\u001a\u0004\b:\u0010;\"\u0016\u0010<\u001a\u00020\u0016X\u0080\u0004¢\u0006\n\n\u0002\u00107\u001a\u0004\b=\u0010;\"\u0010\u0010>\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00107\"\u0010\u0010?\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00107\"\u000e\u0010@\u001a\u00020&X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010A\u001a\u00020&X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010B\u001a\u00020&X\u0082T¢\u0006\u0002\n\u0000¨\u0006C²\u0006\n\u0010D\u001a\u00020&X\u008a\u0084\u0002"}, d2 = {"PullToRefreshBox", "", "isRefreshing", "", "onRefresh", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "indicator", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "content", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/pulltorefresh/PullToRefreshState;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "pullToRefresh", "enabled", "threshold", "Landroidx/compose/ui/unit/Dp;", "pullToRefresh-Z4HSEVQ", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/pulltorefresh/PullToRefreshState;ZFLkotlin/jvm/functions/Function0;)Landroidx/compose/ui/Modifier;", "rememberPullToRefreshState", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "PullToRefreshState", "CircularArrowProgressIndicator", NotificationCompat.CATEGORY_PROGRESS, "Landroidx/compose/material3/internal/FloatProducer;", "color", "Landroidx/compose/ui/graphics/Color;", "CircularArrowProgressIndicator-RPmYEkk", "(Landroidx/compose/material3/internal/FloatProducer;JLandroidx/compose/runtime/Composer;I)V", "drawCircularIndicator", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "alpha", "", "values", "Landroidx/compose/material3/pulltorefresh/ArrowValues;", "arcBounds", "Landroidx/compose/ui/geometry/Rect;", "strokeWidth", "drawCircularIndicator-KzyDr3Q", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;Landroidx/compose/ui/geometry/Rect;F)V", "ArrowValues", "drawArrow", "arrow", "Landroidx/compose/ui/graphics/Path;", "bounds", "drawArrow-uDrxG_w", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/geometry/Rect;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;F)V", "MaxProgressArc", "StrokeWidth", "F", "ArcRadius", "SpinnerSize", "getSpinnerSize", "()F", "SpinnerContainerSize", "getSpinnerContainerSize", "ArrowWidth", "ArrowHeight", "MinAlpha", "MaxAlpha", "DragMultiplier", "material3", "targetAlpha"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PullToRefreshKt {
    private static final float DragMultiplier = 0.5f;
    private static final float MaxAlpha = 1.0f;
    private static final float MaxProgressArc = 0.8f;
    private static final float MinAlpha = 0.3f;
    private static final float StrokeWidth = Dp.m6022constructorimpl(2.5f);
    private static final float ArcRadius = Dp.m6022constructorimpl(5.5f);
    private static final float SpinnerSize = Dp.m6022constructorimpl(16.0f);
    private static final float SpinnerContainerSize = Dp.m6022constructorimpl(40.0f);
    private static final float ArrowWidth = Dp.m6022constructorimpl(10.0f);
    private static final float ArrowHeight = Dp.m6022constructorimpl(5.0f);

    private static final ArrowValues ArrowValues(float f) {
        float fMax = (Math.max(Math.min(1.0f, f) - 0.4f, 0.0f) * 5.0f) / 3.0f;
        float fCoerceIn = RangesKt.coerceIn(Math.abs(f) - 1.0f, 0.0f, 2.0f);
        float fPow = (((0.4f * fMax) - 0.25f) + (fCoerceIn - (((float) Math.pow(fCoerceIn, 2.0d)) / 4.0f))) * 0.5f;
        return new ArrowValues(fPow, fPow * 360.0f, ((0.8f * fMax) + fPow) * 360.0f, Math.min(1.0f, fMax));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: CircularArrowProgressIndicator-RPmYEkk, reason: not valid java name */
    public static final void m1486CircularArrowProgressIndicatorRPmYEkk(final FloatProducer floatProducer, final long j, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1353562852);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(floatProducer) : composerStartRestartGroup.changedInstance(floatProducer) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1353562852, i2, -1, "androidx.compose.material3.pulltorefresh.CircularArrowProgressIndicator (PullToRefresh.kt:631)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            Object obj = objRememberedValue;
            if (objRememberedValue == companion.getEmpty()) {
                Path Path = AndroidPath_androidKt.Path();
                Path.mo3024setFillTypeoQ8Xj4U(PathFillType.INSTANCE.m3430getEvenOddRgk1Os());
                composerStartRestartGroup.updateRememberedValue(Path);
                obj = Path;
            }
            final Path path = (Path) obj;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: hvb
                    public final Object invoke() {
                        return Float.valueOf(PullToRefreshKt.c(floatProducer));
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final State stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CircularArrowProgressIndicator_RPmYEkk$lambda$8((State) objRememberedValue2), MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            int i3 = i2 & 14;
            boolean z = i3 == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(floatProducer));
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: ivb
                    public final Object invoke(Object obj2) {
                        return PullToRefreshKt.e(floatProducer, (SemanticsPropertyReceiver) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifier = SizeKt.size-3ABfNKs(SemanticsModifierKt.clearAndSetSemantics(companion2, (Function1) objRememberedValue3), SpinnerSize);
            boolean zChanged = composerStartRestartGroup.changed(stateAnimateFloatAsState) | (i3 == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(floatProducer))) | ((i2 & 112) == 32) | composerStartRestartGroup.changedInstance(path);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue4 == companion.getEmpty()) {
                Function1 function1 = new Function1() { // from class: jvb
                    public final Object invoke(Object obj2) {
                        return PullToRefreshKt.f(floatProducer, stateAnimateFloatAsState, j, path, (DrawScope) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function1);
                objRememberedValue4 = function1;
            }
            CanvasKt.Canvas(modifier, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kvb
                public final Object invoke(Object obj2, Object obj3) {
                    return PullToRefreshKt.d(floatProducer, j, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final float CircularArrowProgressIndicator_RPmYEkk$lambda$8(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0112  */
    /* JADX WARN: Code duplicated, block: B:105:0x012e  */
    /* JADX WARN: Code duplicated, block: B:108:0x015e  */
    /* JADX WARN: Code duplicated, block: B:111:0x016a  */
    /* JADX WARN: Code duplicated, block: B:112:0x016e  */
    /* JADX WARN: Code duplicated, block: B:115:0x018d  */
    /* JADX WARN: Code duplicated, block: B:117:0x019b  */
    /* JADX WARN: Code duplicated, block: B:120:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:123:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:126:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0060  */
    /* JADX WARN: Code duplicated, block: B:38:0x0064  */
    /* JADX WARN: Code duplicated, block: B:40:0x006c  */
    /* JADX WARN: Code duplicated, block: B:41:0x006f  */
    /* JADX WARN: Code duplicated, block: B:44:0x0075  */
    /* JADX WARN: Code duplicated, block: B:47:0x007b  */
    /* JADX WARN: Code duplicated, block: B:49:0x0080  */
    /* JADX WARN: Code duplicated, block: B:51:0x0084  */
    /* JADX WARN: Code duplicated, block: B:53:0x008c  */
    /* JADX WARN: Code duplicated, block: B:54:0x008f  */
    /* JADX WARN: Code duplicated, block: B:58:0x0098  */
    /* JADX WARN: Code duplicated, block: B:60:0x009c  */
    /* JADX WARN: Code duplicated, block: B:62:0x009f  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:72:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:74:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:83:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:96:0x0100  */
    /* JADX WARN: Code duplicated, block: B:98:0x0108  */
    /* JADX WARN: Code duplicated, block: B:99:0x010f  */
    public static final void PullToRefreshBox(final boolean z, final Function0<Unit> function0, Modifier modifier, PullToRefreshState pullToRefreshState, Alignment alignment, Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i, final int i2) {
        int i3;
        Function0<Unit> function1;
        Modifier modifier2;
        final PullToRefreshState pullToRefreshStateRememberPullToRefreshState;
        int i4;
        Alignment alignment2;
        int i5;
        int i6;
        Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3RememberComposableLambda;
        int i7;
        int i8;
        boolean z2;
        final Modifier modifier3;
        final PullToRefreshState pullToRefreshState2;
        final Alignment alignment3;
        final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function5;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Alignment topStart;
        int i9;
        Modifier modifier4;
        PullToRefreshState pullToRefreshState3;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM2388constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        Composer composerStartRestartGroup = composer.startRestartGroup(-532332839);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
            }
        }
        int i10 = i2 & 4;
        if (i10 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    pullToRefreshStateRememberPullToRefreshState = pullToRefreshState;
                    int i11 = composerStartRestartGroup.changed(pullToRefreshStateRememberPullToRefreshState) ? 2048 : 1024;
                    i3 |= i11;
                } else {
                    pullToRefreshStateRememberPullToRefreshState = pullToRefreshState;
                }
                i3 |= i11;
            } else {
                pullToRefreshStateRememberPullToRefreshState = pullToRefreshState;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((i & 24576) == 0) {
                    alignment2 = alignment;
                    if (composerStartRestartGroup.changed(alignment2)) {
                        i5 = 16384;
                    } else {
                        i5 = 8192;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        function3RememberComposableLambda = function3;
                        if (composerStartRestartGroup.changedInstance(function3RememberComposableLambda)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((i2 & 64) != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i8 = 1048576;
                        } else {
                            i8 = 524288;
                        }
                        i3 |= i8;
                    }
                    if ((599187 & i3) != 599186) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i10 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 8) != 0) {
                                pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                                i3 &= -7169;
                            }
                            if (i4 != 0) {
                                topStart = Alignment.INSTANCE.getTopStart();
                            } else {
                                topStart = alignment2;
                            }
                            if (i6 != 0) {
                                function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                                    public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                        int i13;
                                        if ((i12 & 6) == 0) {
                                            i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                        } else {
                                            i13 = i12;
                                        }
                                        if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                            composer2.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                        }
                                        PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                        invoke(boxScope, composer2, num.intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                            }
                            i9 = i3;
                            modifier4 = modifier2;
                            pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                            alignment2 = topStart;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                            i9 = i3;
                            modifier4 = modifier2;
                            pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-532332839, i9, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox (PullToRefresh.kt:133)");
                        }
                        Modifier modifierM1491pullToRefreshZ4HSEVQ$default = m1491pullToRefreshZ4HSEVQ$default(modifier4, z, pullToRefreshState3, false, 0.0f, function1, 12, null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(alignment2, false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1491pullToRefreshZ4HSEVQ$default);
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
                        function4.invoke(boxScopeInstance, composerStartRestartGroup, Integer.valueOf(((i9 >> 15) & 112) | 6));
                        function3RememberComposableLambda.invoke(boxScopeInstance, composerStartRestartGroup, Integer.valueOf(((i9 >> 12) & 112) | 6));
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        pullToRefreshState2 = pullToRefreshState3;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        pullToRefreshState2 = pullToRefreshStateRememberPullToRefreshState;
                    }
                    alignment3 = alignment2;
                    function5 = function3RememberComposableLambda;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvb
                            public final Object invoke(Object obj, Object obj2) {
                                return PullToRefreshKt.a(z, function0, modifier3, pullToRefreshState2, alignment3, function5, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function3RememberComposableLambda = function3;
                if ((i2 & 64) != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 1048576;
                    } else {
                        i8 = 524288;
                    }
                    i3 |= i8;
                }
                if ((599187 & i3) != 599186) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                            i3 &= -7169;
                        }
                        if (i4 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i6 != 0) {
                            function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                                public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                    int i13;
                                    if ((i12 & 6) == 0) {
                                        i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                    } else {
                                        i13 = i12;
                                    }
                                    if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                    }
                                    PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                    invoke(boxScope, composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        i9 = i3;
                        modifier4 = modifier2;
                        pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                        alignment2 = topStart;
                    } else {
                        if (i10 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                            i3 &= -7169;
                        }
                        if (i4 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i6 != 0) {
                            function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                                public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                    int i13;
                                    if ((i12 & 6) == 0) {
                                        i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                    } else {
                                        i13 = i12;
                                    }
                                    if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                    }
                                    PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                    invoke(boxScope, composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        i9 = i3;
                        modifier4 = modifier2;
                        pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                        alignment2 = topStart;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-532332839, i9, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox (PullToRefresh.kt:133)");
                    }
                    Modifier modifierM1491pullToRefreshZ4HSEVQ$default2 = m1491pullToRefreshZ4HSEVQ$default(modifier4, z, pullToRefreshState3, false, 0.0f, function1, 12, null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(alignment2, false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1491pullToRefreshZ4HSEVQ$default2);
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
                    function4.invoke(boxScopeInstance2, composerStartRestartGroup, Integer.valueOf(((i9 >> 15) & 112) | 6));
                    function3RememberComposableLambda.invoke(boxScopeInstance2, composerStartRestartGroup, Integer.valueOf(((i9 >> 12) & 112) | 6));
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    pullToRefreshState2 = pullToRefreshState3;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    pullToRefreshState2 = pullToRefreshStateRememberPullToRefreshState;
                }
                alignment3 = alignment2;
                function5 = function3RememberComposableLambda;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvb
                        public final Object invoke(Object obj, Object obj2) {
                            return PullToRefreshKt.a(z, function0, modifier3, pullToRefreshState2, alignment3, function5, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            alignment2 = alignment;
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    function3RememberComposableLambda = function3;
                    if (composerStartRestartGroup.changedInstance(function3RememberComposableLambda)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((i2 & 64) != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 1048576;
                    } else {
                        i8 = 524288;
                    }
                    i3 |= i8;
                }
                if ((599187 & i3) != 599186) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                            i3 &= -7169;
                        }
                        if (i4 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i6 != 0) {
                            function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                                public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                    int i13;
                                    if ((i12 & 6) == 0) {
                                        i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                    } else {
                                        i13 = i12;
                                    }
                                    if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                    }
                                    PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                    invoke(boxScope, composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        i9 = i3;
                        modifier4 = modifier2;
                        pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                        alignment2 = topStart;
                    } else {
                        if (i10 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                            i3 &= -7169;
                        }
                        if (i4 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i6 != 0) {
                            function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                                public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                    int i13;
                                    if ((i12 & 6) == 0) {
                                        i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                    } else {
                                        i13 = i12;
                                    }
                                    if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                    }
                                    PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                    invoke(boxScope, composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        i9 = i3;
                        modifier4 = modifier2;
                        pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                        alignment2 = topStart;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-532332839, i9, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox (PullToRefresh.kt:133)");
                    }
                    Modifier modifierM1491pullToRefreshZ4HSEVQ$default3 = m1491pullToRefreshZ4HSEVQ$default(modifier4, z, pullToRefreshState3, false, 0.0f, function1, 12, null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(alignment2, false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1491pullToRefreshZ4HSEVQ$default3);
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
                    function4.invoke(boxScopeInstance3, composerStartRestartGroup, Integer.valueOf(((i9 >> 15) & 112) | 6));
                    function3RememberComposableLambda.invoke(boxScopeInstance3, composerStartRestartGroup, Integer.valueOf(((i9 >> 12) & 112) | 6));
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    pullToRefreshState2 = pullToRefreshState3;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    pullToRefreshState2 = pullToRefreshStateRememberPullToRefreshState;
                }
                alignment3 = alignment2;
                function5 = function3RememberComposableLambda;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvb
                        public final Object invoke(Object obj, Object obj2) {
                            return PullToRefreshKt.a(z, function0, modifier3, pullToRefreshState2, alignment3, function5, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function3RememberComposableLambda = function3;
            if ((i2 & 64) != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i8 = 1048576;
                } else {
                    i8 = 524288;
                }
                i3 |= i8;
            }
            if ((599187 & i3) != 599186) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                        i3 &= -7169;
                    }
                    if (i4 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i6 != 0) {
                        function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                            public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                int i13;
                                if ((i12 & 6) == 0) {
                                    i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                } else {
                                    i13 = i12;
                                }
                                if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                }
                                PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                invoke(boxScope, composer2, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    i9 = i3;
                    modifier4 = modifier2;
                    pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                    alignment2 = topStart;
                } else {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                        i3 &= -7169;
                    }
                    if (i4 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i6 != 0) {
                        function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                            public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                int i13;
                                if ((i12 & 6) == 0) {
                                    i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                } else {
                                    i13 = i12;
                                }
                                if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                }
                                PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                invoke(boxScope, composer2, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    i9 = i3;
                    modifier4 = modifier2;
                    pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                    alignment2 = topStart;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-532332839, i9, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox (PullToRefresh.kt:133)");
                }
                Modifier modifierM1491pullToRefreshZ4HSEVQ$default4 = m1491pullToRefreshZ4HSEVQ$default(modifier4, z, pullToRefreshState3, false, 0.0f, function1, 12, null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(alignment2, false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1491pullToRefreshZ4HSEVQ$default4);
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
                function4.invoke(boxScopeInstance4, composerStartRestartGroup, Integer.valueOf(((i9 >> 15) & 112) | 6));
                function3RememberComposableLambda.invoke(boxScopeInstance4, composerStartRestartGroup, Integer.valueOf(((i9 >> 12) & 112) | 6));
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                pullToRefreshState2 = pullToRefreshState3;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                pullToRefreshState2 = pullToRefreshStateRememberPullToRefreshState;
            }
            alignment3 = alignment2;
            function5 = function3RememberComposableLambda;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvb
                    public final Object invoke(Object obj, Object obj2) {
                        return PullToRefreshKt.a(z, function0, modifier3, pullToRefreshState2, alignment3, function5, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                pullToRefreshStateRememberPullToRefreshState = pullToRefreshState;
                if (composerStartRestartGroup.changed(pullToRefreshStateRememberPullToRefreshState)) {
                }
                i3 |= i11;
            } else {
                pullToRefreshStateRememberPullToRefreshState = pullToRefreshState;
            }
            i3 |= i11;
        } else {
            pullToRefreshStateRememberPullToRefreshState = pullToRefreshState;
        }
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((i & 24576) == 0) {
                alignment2 = alignment;
                if (composerStartRestartGroup.changed(alignment2)) {
                    i5 = 16384;
                } else {
                    i5 = 8192;
                }
                i3 |= i5;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    function3RememberComposableLambda = function3;
                    if (composerStartRestartGroup.changedInstance(function3RememberComposableLambda)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((i2 & 64) != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 1048576;
                    } else {
                        i8 = 524288;
                    }
                    i3 |= i8;
                }
                if ((599187 & i3) != 599186) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                            i3 &= -7169;
                        }
                        if (i4 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i6 != 0) {
                            function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                                public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                    int i13;
                                    if ((i12 & 6) == 0) {
                                        i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                    } else {
                                        i13 = i12;
                                    }
                                    if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                    }
                                    PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                    invoke(boxScope, composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        i9 = i3;
                        modifier4 = modifier2;
                        pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                        alignment2 = topStart;
                    } else {
                        if (i10 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                            i3 &= -7169;
                        }
                        if (i4 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i6 != 0) {
                            function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                                public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                    int i13;
                                    if ((i12 & 6) == 0) {
                                        i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                    } else {
                                        i13 = i12;
                                    }
                                    if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                    }
                                    PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                    invoke(boxScope, composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        i9 = i3;
                        modifier4 = modifier2;
                        pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                        alignment2 = topStart;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-532332839, i9, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox (PullToRefresh.kt:133)");
                    }
                    Modifier modifierM1491pullToRefreshZ4HSEVQ$default5 = m1491pullToRefreshZ4HSEVQ$default(modifier4, z, pullToRefreshState3, false, 0.0f, function1, 12, null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(alignment2, false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1491pullToRefreshZ4HSEVQ$default5);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy5, companion5.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap5, companion5.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion5.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier5, companion5.getSetModifier());
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    function4.invoke(boxScopeInstance5, composerStartRestartGroup, Integer.valueOf(((i9 >> 15) & 112) | 6));
                    function3RememberComposableLambda.invoke(boxScopeInstance5, composerStartRestartGroup, Integer.valueOf(((i9 >> 12) & 112) | 6));
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    pullToRefreshState2 = pullToRefreshState3;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    pullToRefreshState2 = pullToRefreshStateRememberPullToRefreshState;
                }
                alignment3 = alignment2;
                function5 = function3RememberComposableLambda;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvb
                        public final Object invoke(Object obj, Object obj2) {
                            return PullToRefreshKt.a(z, function0, modifier3, pullToRefreshState2, alignment3, function5, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function3RememberComposableLambda = function3;
            if ((i2 & 64) != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i8 = 1048576;
                } else {
                    i8 = 524288;
                }
                i3 |= i8;
            }
            if ((599187 & i3) != 599186) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                        i3 &= -7169;
                    }
                    if (i4 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i6 != 0) {
                        function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                            public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                int i13;
                                if ((i12 & 6) == 0) {
                                    i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                } else {
                                    i13 = i12;
                                }
                                if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                }
                                PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                invoke(boxScope, composer2, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    i9 = i3;
                    modifier4 = modifier2;
                    pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                    alignment2 = topStart;
                } else {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                        i3 &= -7169;
                    }
                    if (i4 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i6 != 0) {
                        function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                            public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                int i13;
                                if ((i12 & 6) == 0) {
                                    i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                } else {
                                    i13 = i12;
                                }
                                if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                }
                                PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                invoke(boxScope, composer2, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    i9 = i3;
                    modifier4 = modifier2;
                    pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                    alignment2 = topStart;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-532332839, i9, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox (PullToRefresh.kt:133)");
                }
                Modifier modifierM1491pullToRefreshZ4HSEVQ$default6 = m1491pullToRefreshZ4HSEVQ$default(modifier4, z, pullToRefreshState3, false, 0.0f, function1, 12, null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(alignment2, false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1491pullToRefreshZ4HSEVQ$default6);
                ComposeUiNode.Companion companion6 = ComposeUiNode.INSTANCE;
                constructor = companion6.getConstructor();
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
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy6, companion6.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap6, companion6.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion6.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier6, companion6.getSetModifier());
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                function4.invoke(boxScopeInstance6, composerStartRestartGroup, Integer.valueOf(((i9 >> 15) & 112) | 6));
                function3RememberComposableLambda.invoke(boxScopeInstance6, composerStartRestartGroup, Integer.valueOf(((i9 >> 12) & 112) | 6));
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                pullToRefreshState2 = pullToRefreshState3;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                pullToRefreshState2 = pullToRefreshStateRememberPullToRefreshState;
            }
            alignment3 = alignment2;
            function5 = function3RememberComposableLambda;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvb
                    public final Object invoke(Object obj, Object obj2) {
                        return PullToRefreshKt.a(z, function0, modifier3, pullToRefreshState2, alignment3, function5, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        alignment2 = alignment;
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                function3RememberComposableLambda = function3;
                if (composerStartRestartGroup.changedInstance(function3RememberComposableLambda)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((i2 & 64) != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i8 = 1048576;
                } else {
                    i8 = 524288;
                }
                i3 |= i8;
            }
            if ((599187 & i3) != 599186) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                        i3 &= -7169;
                    }
                    if (i4 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i6 != 0) {
                        function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                            public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                int i13;
                                if ((i12 & 6) == 0) {
                                    i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                } else {
                                    i13 = i12;
                                }
                                if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                }
                                PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                invoke(boxScope, composer2, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    i9 = i3;
                    modifier4 = modifier2;
                    pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                    alignment2 = topStart;
                } else {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                        i3 &= -7169;
                    }
                    if (i4 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i6 != 0) {
                        function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                            public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                                int i13;
                                if ((i12 & 6) == 0) {
                                    i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                                } else {
                                    i13 = i12;
                                }
                                if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                                }
                                PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                                invoke(boxScope, composer2, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    i9 = i3;
                    modifier4 = modifier2;
                    pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                    alignment2 = topStart;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-532332839, i9, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox (PullToRefresh.kt:133)");
                }
                Modifier modifierM1491pullToRefreshZ4HSEVQ$default7 = m1491pullToRefreshZ4HSEVQ$default(modifier4, z, pullToRefreshState3, false, 0.0f, function1, 12, null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(alignment2, false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1491pullToRefreshZ4HSEVQ$default7);
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
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy7, companion7.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap7, companion7.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion7.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier7, companion7.getSetModifier());
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                function4.invoke(boxScopeInstance7, composerStartRestartGroup, Integer.valueOf(((i9 >> 15) & 112) | 6));
                function3RememberComposableLambda.invoke(boxScopeInstance7, composerStartRestartGroup, Integer.valueOf(((i9 >> 12) & 112) | 6));
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                pullToRefreshState2 = pullToRefreshState3;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                pullToRefreshState2 = pullToRefreshStateRememberPullToRefreshState;
            }
            alignment3 = alignment2;
            function5 = function3RememberComposableLambda;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvb
                    public final Object invoke(Object obj, Object obj2) {
                        return PullToRefreshKt.a(z, function0, modifier3, pullToRefreshState2, alignment3, function5, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        function3RememberComposableLambda = function3;
        if ((i2 & 64) != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function4)) {
                i8 = 1048576;
            } else {
                i8 = 524288;
            }
            i3 |= i8;
        }
        if ((599187 & i3) != 599186) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i10 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 8) != 0) {
                    pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                    i3 &= -7169;
                }
                if (i4 != 0) {
                    topStart = Alignment.INSTANCE.getTopStart();
                } else {
                    topStart = alignment2;
                }
                if (i6 != 0) {
                    function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                        public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                            int i13;
                            if ((i12 & 6) == 0) {
                                i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                            } else {
                                i13 = i12;
                            }
                            if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                            }
                            PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                            invoke(boxScope, composer2, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                }
                i9 = i3;
                modifier4 = modifier2;
                pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                alignment2 = topStart;
            } else {
                if (i10 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 8) != 0) {
                    pullToRefreshStateRememberPullToRefreshState = rememberPullToRefreshState(composerStartRestartGroup, 0);
                    i3 &= -7169;
                }
                if (i4 != 0) {
                    topStart = Alignment.INSTANCE.getTopStart();
                } else {
                    topStart = alignment2;
                }
                if (i6 != 0) {
                    function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                        public final void invoke(BoxScope boxScope, Composer composer2, int i12) {
                            int i13;
                            if ((i12 & 6) == 0) {
                                i13 = (composer2.changed(boxScope) ? 4 : 2) | i12;
                            } else {
                                i13 = i12;
                            }
                            if (!composer2.shouldExecute((i13 & 19) != 18, i13 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1028036671, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                            }
                            PullToRefreshDefaults.INSTANCE.m1479Indicator2poqoh4(pullToRefreshStateRememberPullToRefreshState, z, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, composer2, 1572864, 56);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                            invoke(boxScope, composer2, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                }
                i9 = i3;
                modifier4 = modifier2;
                pullToRefreshState3 = pullToRefreshStateRememberPullToRefreshState;
                alignment2 = topStart;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-532332839, i9, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox (PullToRefresh.kt:133)");
            }
            Modifier modifierM1491pullToRefreshZ4HSEVQ$default8 = m1491pullToRefreshZ4HSEVQ$default(modifier4, z, pullToRefreshState3, false, 0.0f, function1, 12, null);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(alignment2, false);
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1491pullToRefreshZ4HSEVQ$default8);
            ComposeUiNode.Companion companion8 = ComposeUiNode.INSTANCE;
            constructor = companion8.getConstructor();
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy8, companion8.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap8, companion8.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion8.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting()) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier8, companion8.getSetModifier());
            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
            function4.invoke(boxScopeInstance8, composerStartRestartGroup, Integer.valueOf(((i9 >> 15) & 112) | 6));
            function3RememberComposableLambda.invoke(boxScopeInstance8, composerStartRestartGroup, Integer.valueOf(((i9 >> 12) & 112) | 6));
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            pullToRefreshState2 = pullToRefreshState3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            pullToRefreshState2 = pullToRefreshStateRememberPullToRefreshState;
        }
        alignment3 = alignment2;
        function5 = function3RememberComposableLambda;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvb
                public final Object invoke(Object obj, Object obj2) {
                    return PullToRefreshKt.a(z, function0, modifier3, pullToRefreshState2, alignment3, function5, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final PullToRefreshState PullToRefreshState() {
        return new PullToRefreshStateImpl();
    }

    public static Unit a(boolean z, Function0 function0, Modifier modifier, PullToRefreshState pullToRefreshState, Alignment alignment, Function3 function3, Function3 function4, int i, int i2, Composer composer, int i3) {
        PullToRefreshBox(z, function0, modifier, pullToRefreshState, alignment, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static PullToRefreshStateImpl b() {
        return new PullToRefreshStateImpl();
    }

    public static float c(FloatProducer floatProducer) {
        if (floatProducer.invoke() >= 1.0f) {
            return 1.0f;
        }
        return MinAlpha;
    }

    public static Unit d(FloatProducer floatProducer, long j, int i, Composer composer, int i2) {
        m1486CircularArrowProgressIndicatorRPmYEkk(floatProducer, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: drawArrow-uDrxG_w, reason: not valid java name */
    private static final void m1488drawArrowuDrxG_w(DrawScope drawScope, Path path, Rect rect, long j, float f, ArrowValues arrowValues, float f2) {
        path.reset();
        path.moveTo(0.0f, 0.0f);
        float f3 = ArrowWidth;
        path.lineTo((drawScope.mo4557toPx0680j_4(f3) * arrowValues.getScale()) / 2.0f, drawScope.mo4557toPx0680j_4(ArrowHeight) * arrowValues.getScale());
        path.lineTo(drawScope.mo4557toPx0680j_4(f3) * arrowValues.getScale(), 0.0f);
        float fMin = ((Math.min(rect.getRight() - rect.getLeft(), rect.getBottom() - rect.getTop()) / 2.0f) + Float.intBitsToFloat((int) (rect.m2919getCenterF1C5BW0() >> 32))) - ((drawScope.mo4557toPx0680j_4(f3) * arrowValues.getScale()) / 2.0f);
        path.mo3026translatek4lQ0M(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (rect.m2919getCenterF1C5BW0() & 4294967295L)) - drawScope.mo4557toPx0680j_4(f2))) & 4294967295L) | (Float.floatToRawIntBits(fMin) << 32)));
        float endAngle = arrowValues.getEndAngle() - drawScope.mo4557toPx0680j_4(f2);
        long jMo3707getCenterF1C5BW0 = drawScope.mo3707getCenterF1C5BW0();
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3629getSizeNHjbRc = drawContext.mo3629getSizeNHjbRc();
        drawContext.getCanvas().save();
        try {
            drawContext.getTransform().mo3635rotateUv8p0NA(endAngle, jMo3707getCenterF1C5BW0);
            DrawScope.m3698drawPathLG529CI$default(drawScope, path, j, f, new Stroke(drawScope.mo4557toPx0680j_4(f2), 0.0f, 0, 0, null, 30, null), null, 0, 48, null);
        } finally {
            drawContext.getCanvas().restore();
            drawContext.mo3630setSizeuvyYCjk(jMo3629getSizeNHjbRc);
        }
    }

    /* JADX INFO: renamed from: drawCircularIndicator-KzyDr3Q, reason: not valid java name */
    private static final void m1489drawCircularIndicatorKzyDr3Q(DrawScope drawScope, long j, float f, ArrowValues arrowValues, Rect rect, float f2) {
        DrawScope.m3687drawArcyD3GUKo$default(drawScope, j, arrowValues.getStartAngle(), arrowValues.getEndAngle() - arrowValues.getStartAngle(), false, rect.m2924getTopLeftF1C5BW0(), rect.m2922getSizeNHjbRc(), f, new Stroke(drawScope.mo4557toPx0680j_4(f2), 0.0f, StrokeCap.INSTANCE.m3508getButtKaPHkGw(), 0, null, 26, null), null, 0, 768, null);
    }

    public static Unit e(FloatProducer floatProducer, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (floatProducer.invoke() > 0.0f) {
            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(floatProducer.invoke(), RangesKt.rangeTo(0.0f, 1.0f), 0));
        }
        return Unit.INSTANCE;
    }

    public static Unit f(FloatProducer floatProducer, State state, long j, Path path, DrawScope drawScope) {
        ArrowValues ArrowValues = ArrowValues(floatProducer.invoke());
        float fFloatValue = ((Number) state.getValue()).floatValue();
        float rotation = ArrowValues.getRotation();
        long jMo3707getCenterF1C5BW0 = drawScope.mo3707getCenterF1C5BW0();
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3629getSizeNHjbRc = drawContext.mo3629getSizeNHjbRc();
        drawContext.getCanvas().save();
        try {
            drawContext.getTransform().mo3635rotateUv8p0NA(rotation, jMo3707getCenterF1C5BW0);
            float fMo4557toPx0680j_4 = drawScope.mo4557toPx0680j_4(ArcRadius);
            float f = StrokeWidth;
            Rect rectM2928Rect3MmeM6k = RectKt.m2928Rect3MmeM6k(androidx.compose.ui.geometry.SizeKt.m2968getCenteruvyYCjk(drawScope.mo3708getSizeNHjbRc()), fMo4557toPx0680j_4 + (drawScope.mo4557toPx0680j_4(f) / 2.0f));
            m1489drawCircularIndicatorKzyDr3Q(drawScope, j, fFloatValue, ArrowValues, rectM2928Rect3MmeM6k, f);
            m1488drawArrowuDrxG_w(drawScope, path, rectM2928Rect3MmeM6k, j, fFloatValue, ArrowValues, f);
            return Unit.INSTANCE;
        } finally {
            drawContext.getCanvas().restore();
            drawContext.mo3630setSizeuvyYCjk(jMo3629getSizeNHjbRc);
        }
    }

    public static final float getSpinnerContainerSize() {
        return SpinnerContainerSize;
    }

    public static final float getSpinnerSize() {
        return SpinnerSize;
    }

    /* JADX INFO: renamed from: pullToRefresh-Z4HSEVQ, reason: not valid java name */
    public static final Modifier m1490pullToRefreshZ4HSEVQ(Modifier modifier, boolean z, PullToRefreshState pullToRefreshState, boolean z2, float f, Function0<Unit> function0) {
        return modifier.then(new PullToRefreshElement(z, function0, z2, pullToRefreshState, f, null));
    }

    /* JADX INFO: renamed from: pullToRefresh-Z4HSEVQ$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1491pullToRefreshZ4HSEVQ$default(Modifier modifier, boolean z, PullToRefreshState pullToRefreshState, boolean z2, float f, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        boolean z3 = z2;
        if ((i & 8) != 0) {
            f = PullToRefreshDefaults.INSTANCE.m1484getPositionalThresholdD9Ej5fM();
        }
        return m1490pullToRefreshZ4HSEVQ(modifier, z, pullToRefreshState, z3, f, function0);
    }

    public static final PullToRefreshState rememberPullToRefreshState(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(318623070, i, -1, "androidx.compose.material3.pulltorefresh.rememberPullToRefreshState (PullToRefresh.kt:585)");
        }
        Object[] objArr = new Object[0];
        Saver<PullToRefreshStateImpl, Float> saver = PullToRefreshStateImpl.INSTANCE.getSaver();
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: mvb
                public final Object invoke() {
                    return PullToRefreshKt.b();
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        PullToRefreshStateImpl pullToRefreshStateImpl = (PullToRefreshStateImpl) RememberSaveableKt.m2564rememberSaveable(objArr, (Saver) saver, (Function0) objRememberedValue, composer, AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return pullToRefreshStateImpl;
    }
}
