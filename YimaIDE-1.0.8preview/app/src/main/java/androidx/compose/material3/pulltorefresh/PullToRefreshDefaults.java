package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.CrossfadeKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.MotionSchemeKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
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
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jo\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020\u00152\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010(\u001a\u00020\u00152\u001c\u0010)\u001a\u0018\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020 0*¢\u0006\u0002\b,¢\u0006\u0002\b-H\u0007¢\u0006\u0004\b.\u0010/JG\u00100\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u00101\u001a\u00020\f2\b\b\u0002\u0010'\u001a\u00020\u0015H\u0007¢\u0006\u0004\b2\u00103R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\u00020\f8GX\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0013\u0010\u0014\u001a\u00020\u0015¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0019\u001a\u00020\u0015¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u001a\u0010\u0017R\u0013\u0010\u001b\u001a\u00020\u0015¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u001c\u0010\u0017R\u0013\u0010\u001d\u001a\u00020\u0015¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u001e\u0010\u0017¨\u00064"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshDefaults;", "", "<init>", "()V", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape$annotations", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "indicatorShape", "getIndicatorShape", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor$annotations", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "indicatorContainerColor", "getIndicatorContainerColor", "indicatorColor", "getIndicatorColor", "PositionalThreshold", "Landroidx/compose/ui/unit/Dp;", "getPositionalThreshold-D9Ej5fM", "()F", "F", "IndicatorMaxDistance", "getIndicatorMaxDistance-D9Ej5fM", "Elevation", "getElevation-D9Ej5fM", "LoadingIndicatorElevation", "getLoadingIndicatorElevation-D9Ej5fM", "IndicatorBox", "", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "isRefreshing", "", "modifier", "Landroidx/compose/ui/Modifier;", "maxDistance", "elevation", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "IndicatorBox-1CPYgEU", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;ZLandroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;JFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Indicator", "color", "Indicator-2poqoh4", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;ZLandroidx/compose/ui/Modifier;JJFLandroidx/compose/runtime/Composer;II)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PullToRefreshDefaults {
    public static final int $stable = 0;
    private static final float Elevation;
    private static final float IndicatorMaxDistance;
    private static final float LoadingIndicatorElevation;
    private static final float PositionalThreshold;
    public static final PullToRefreshDefaults INSTANCE = new PullToRefreshDefaults();
    private static final Shape shape = RoundedCornerShapeKt.getCircleShape();
    private static final Shape indicatorShape = RoundedCornerShapeKt.getCircleShape();

    static {
        float fM6022constructorimpl = Dp.m6022constructorimpl(80.0f);
        PositionalThreshold = fM6022constructorimpl;
        IndicatorMaxDistance = fM6022constructorimpl;
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        Elevation = elevationTokens.m1744getLevel2D9Ej5fM();
        LoadingIndicatorElevation = elevationTokens.m1742getLevel0D9Ej5fM();
    }

    private PullToRefreshDefaults() {
    }

    public static Unit a(PullToRefreshDefaults pullToRefreshDefaults, PullToRefreshState pullToRefreshState, boolean z, Modifier modifier, float f, Shape shape2, long j, float f2, Function3 function3, int i, int i2, Composer composer, int i3) {
        pullToRefreshDefaults.m1480IndicatorBox1CPYgEU(pullToRefreshState, z, modifier, f, shape2, j, f2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static MeasureResult b(final PullToRefreshState pullToRefreshState, final boolean z, final float f, final float f2, final Shape shape2, MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(constraints.getValue());
        return MeasureScope.layout$default(measureScope, placeableMo4605measureBRTryo0.getWidth(), placeableMo4605measureBRTryo0.getHeight(), null, new Function1() { // from class: gvb
            public final Object invoke(Object obj) {
                return PullToRefreshDefaults.d(placeableMo4605measureBRTryo0, pullToRefreshState, z, f, f2, shape2, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public static Unit c(ContentDrawScope contentDrawScope) {
        int iM3123getIntersectrtfAjoo = ClipOp.INSTANCE.m3123getIntersectrtfAjoo();
        DrawContext drawContext = contentDrawScope.getDrawContext();
        long jMo3629getSizeNHjbRc = drawContext.mo3629getSizeNHjbRc();
        drawContext.getCanvas().save();
        try {
            drawContext.getTransform().mo3632clipRectN_I0leg(-3.4028235E38f, 0.0f, Float.MAX_VALUE, Float.MAX_VALUE, iM3123getIntersectrtfAjoo);
            contentDrawScope.drawContent();
            return Unit.INSTANCE;
        } finally {
            drawContext.getCanvas().restore();
            drawContext.mo3630setSizeuvyYCjk(jMo3629getSizeNHjbRc);
        }
    }

    public static Unit d(Placeable placeable, final PullToRefreshState pullToRefreshState, final boolean z, final float f, final float f2, final Shape shape2, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeWithLayer$default(placementScope, placeable, 0, 0, 0.0f, new Function1() { // from class: cvb
            public final Object invoke(Object obj) {
                return PullToRefreshDefaults.e(pullToRefreshState, z, f, f2, shape2, (GraphicsLayerScope) obj);
            }
        }, 4, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit e(PullToRefreshState pullToRefreshState, boolean z, float f, float f2, Shape shape2, GraphicsLayerScope graphicsLayerScope) {
        boolean z2 = pullToRefreshState.getDistanceFraction() > 0.0f || z;
        graphicsLayerScope.setTranslationY((pullToRefreshState.getDistanceFraction() * graphicsLayerScope.mo4551roundToPx0680j_4(f)) - Float.intBitsToFloat((int) (graphicsLayerScope.getSize() & 4294967295L)));
        graphicsLayerScope.setShadowElevation(z2 ? graphicsLayerScope.mo4557toPx0680j_4(f2) : 0.0f);
        graphicsLayerScope.setShape(shape2);
        graphicsLayerScope.setClip(true);
        return Unit.INSTANCE;
    }

    public static Unit f(PullToRefreshDefaults pullToRefreshDefaults, PullToRefreshState pullToRefreshState, boolean z, Modifier modifier, long j, long j2, float f, int i, int i2, Composer composer, int i3) {
        pullToRefreshDefaults.m1479Indicator2poqoh4(pullToRefreshState, z, modifier, j, j2, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(message = "Use indicatorContainerColor instead", replaceWith = @ReplaceWith(expression = "indicatorContainerColor", imports = {}))
    public static /* synthetic */ void getContainerColor$annotations() {
    }

    @Deprecated(message = "Use indicatorShape instead", replaceWith = @ReplaceWith(expression = "indicatorShape", imports = {}))
    public static /* synthetic */ void getShape$annotations() {
    }

    /* JADX WARN: Code duplicated, block: B:102:0x010f  */
    /* JADX WARN: Code duplicated, block: B:103:0x011a  */
    /* JADX WARN: Code duplicated, block: B:106:0x011f  */
    /* JADX WARN: Code duplicated, block: B:109:0x012d  */
    /* JADX WARN: Code duplicated, block: B:111:0x0135  */
    /* JADX WARN: Code duplicated, block: B:114:0x0141  */
    /* JADX WARN: Code duplicated, block: B:117:0x0181  */
    /* JADX WARN: Code duplicated, block: B:119:0x0189  */
    /* JADX WARN: Code duplicated, block: B:122:0x0195  */
    /* JADX WARN: Code duplicated, block: B:124:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x005e  */
    /* JADX WARN: Code duplicated, block: B:38:0x0062  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006d  */
    /* JADX WARN: Code duplicated, block: B:44:0x0073  */
    /* JADX WARN: Code duplicated, block: B:47:0x0079  */
    /* JADX WARN: Code duplicated, block: B:52:0x0088  */
    /* JADX WARN: Code duplicated, block: B:54:0x008c  */
    /* JADX WARN: Code duplicated, block: B:57:0x0093  */
    /* JADX WARN: Code duplicated, block: B:59:0x0097  */
    /* JADX WARN: Code duplicated, block: B:61:0x009f  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:65:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:73:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:74:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:78:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:79:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:82:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:97:0x0105 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:98:0x0107  */
    /* JADX WARN: Code duplicated, block: B:99:0x010a  */
    /* JADX INFO: renamed from: Indicator-2poqoh4, reason: not valid java name */
    public final void m1479Indicator2poqoh4(final PullToRefreshState pullToRefreshState, final boolean z, Modifier modifier, long j, long j2, float f, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        long j3;
        long indicatorColor;
        final float f2;
        int i4;
        boolean z2;
        final Modifier modifier3;
        final long j4;
        final long j5;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        long indicatorContainerColor;
        int i5;
        float f3;
        final long j6;
        int i6;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1076870256);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(pullToRefreshState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        int i7 = i2 & 4;
        if (i7 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    j3 = j;
                    int i8 = composerStartRestartGroup.changed(j3) ? 2048 : 1024;
                    i3 |= i8;
                } else {
                    j3 = j;
                }
                i3 |= i8;
            } else {
                j3 = j;
            }
            if ((i & 24576) == 0) {
                indicatorColor = j2;
                if ((i2 & 16) == 0 || !composerStartRestartGroup.changed(indicatorColor)) {
                    i6 = 8192;
                } else {
                    i6 = 16384;
                }
                i3 |= i6;
            } else {
                indicatorColor = j2;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    f2 = f;
                    int i9 = composerStartRestartGroup.changed(f2) ? 131072 : 65536;
                    i3 |= i9;
                } else {
                    f2 = f;
                }
                i3 |= i9;
            } else {
                f2 = f;
            }
            if ((i2 & 64) != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i4 = 1048576;
                } else {
                    i4 = 524288;
                }
                i3 |= i4;
            }
            if ((599187 & i3) != 599186) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i7 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        indicatorContainerColor = getIndicatorContainerColor(composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -7169;
                    } else {
                        indicatorContainerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = getIndicatorColor(composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -57345;
                    }
                    if ((i2 & 32) != 0) {
                        i5 = i3 & (-458753);
                        f3 = IndicatorMaxDistance;
                    } else {
                        i5 = i3;
                        f3 = f2;
                    }
                    j6 = indicatorColor;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                    }
                    modifier4 = modifier2;
                    indicatorContainerColor = j3;
                    j6 = indicatorColor;
                    i5 = i3;
                    f3 = f2;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1076870256, i5, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:515)");
                }
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(298232649, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1
                    public final void invoke(BoxScope boxScope, Composer composer2, int i10) {
                        if (!composer2.shouldExecute((i10 & 17) != 16, i10 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(298232649, i10, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous> (PullToRefresh.kt:524)");
                        }
                        CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer2, 6), (String) null, ComposableLambdaKt.rememberComposableLambda(-2064098104, true, new AnonymousClass1(j6, pullToRefreshState), composer2, 54), composer2, 24576, 10);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                        invoke(boxScope, composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
                    public static final class AnonymousClass1 implements Function3<Boolean, Composer, Integer, Unit> {
                        final /* synthetic */ long $color;
                        final /* synthetic */ PullToRefreshState $state;

                        public AnonymousClass1(long j, PullToRefreshState pullToRefreshState) {
                            this.$color = j;
                            this.$state = pullToRefreshState;
                        }

                        public static float a(PullToRefreshState pullToRefreshState) {
                            return pullToRefreshState.getDistanceFraction();
                        }

                        public final void invoke(boolean z, Composer composer, int i) {
                            if ((i & 6) == 0) {
                                i |= composer.changed(z) ? 4 : 2;
                            }
                            if (!composer.shouldExecute((i & 19) != 18, i & 1)) {
                                composer.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2064098104, i, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:528)");
                            }
                            if (z) {
                                composer.startReplaceGroup(-499784343);
                                ProgressIndicatorKt.m747CircularProgressIndicator4lLiAd8(SizeKt.size-3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), this.$color, PullToRefreshKt.StrokeWidth, 0L, 0, 0.0f, composer, 390, 56);
                                composer.endReplaceGroup();
                            } else {
                                composer.startReplaceGroup(-499540745);
                                boolean zChanged = composer.changed(this.$state);
                                final PullToRefreshState pullToRefreshState = this.$state;
                                Object objRememberedValue = composer.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = 
                                    /*  JADX ERROR: Method code generation error
                                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0076: CONSTRUCTOR (r14v3 'objRememberedValue' java.lang.Object) = (r13v1 'pullToRefreshState' androidx.compose.material3.pulltorefresh.PullToRefreshState A[DONT_INLINE]) A[MD:(androidx.compose.material3.pulltorefresh.PullToRefreshState):void (m)] call: androidx.compose.material3.pulltorefresh.a.<init>(androidx.compose.material3.pulltorefresh.PullToRefreshState):void type: CONSTRUCTOR in method: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1.1.invoke(boolean, androidx.compose.runtime.Composer, int):void, file: /workspace/dex_all/classes4.dex
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                                        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                                        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
                                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
                                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:268)
                                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:160)
                                        	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:320)
                                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:297)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                                        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                                        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
                                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
                                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                                        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                                        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
                                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
                                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:268)
                                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:160)
                                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:104)
                                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
                                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
                                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
                                        	at jadx.core.ProcessClass.process(ProcessClass.java:89)
                                        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:127)
                                        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
                                        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
                                        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
                                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.material3.pulltorefresh.a, state: NOT_LOADED
                                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                        	... 116 more
                                        */
                                    /*
                                        this = this;
                                        r0 = r14 & 6
                                        if (r0 != 0) goto Le
                                        boolean r0 = r13.changed(r12)
                                        if (r0 == 0) goto Lc
                                        r0 = 4
                                        goto Ld
                                    Lc:
                                        r0 = 2
                                    Ld:
                                        r14 = r14 | r0
                                    Le:
                                        r0 = r14 & 19
                                        r1 = 18
                                        r2 = 0
                                        if (r0 == r1) goto L17
                                        r0 = 1
                                        goto L18
                                    L17:
                                        r0 = r2
                                    L18:
                                        r1 = r14 & 1
                                        boolean r0 = r13.shouldExecute(r0, r1)
                                        if (r0 == 0) goto L90
                                        boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                        if (r0 == 0) goto L2f
                                        r0 = -1
                                        java.lang.String r1 = "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:528)"
                                        r3 = -2064098104(0xffffffff84f85cc8, float:-5.8389726E-36)
                                        androidx.compose.runtime.ComposerKt.traceEventStart(r3, r14, r0, r1)
                                    L2f:
                                        if (r12 == 0) goto L57
                                        r12 = -499784343(0xffffffffe235e569, float:-8.3884786E20)
                                        r13.startReplaceGroup(r12)
                                        float r3 = androidx.compose.material3.pulltorefresh.PullToRefreshKt.access$getStrokeWidth$p()
                                        androidx.compose.ui.Modifier$Companion r12 = androidx.compose.ui.Modifier.INSTANCE
                                        float r14 = androidx.compose.material3.pulltorefresh.PullToRefreshKt.getSpinnerSize()
                                        androidx.compose.ui.Modifier r0 = androidx.compose.foundation.layout.SizeKt.size-3ABfNKs(r12, r14)
                                        long r1 = r11.$color
                                        r9 = 390(0x186, float:5.47E-43)
                                        r10 = 56
                                        r4 = 0
                                        r6 = 0
                                        r7 = 0
                                        r8 = r13
                                        androidx.compose.material3.ProgressIndicatorKt.m747CircularProgressIndicator4lLiAd8(r0, r1, r3, r4, r6, r7, r8, r9, r10)
                                        r8.endReplaceGroup()
                                        goto L86
                                    L57:
                                        r8 = r13
                                        r12 = -499540745(0xffffffffe2399cf7, float:-8.5598954E20)
                                        r8.startReplaceGroup(r12)
                                        androidx.compose.material3.pulltorefresh.PullToRefreshState r12 = r11.$state
                                        boolean r12 = r8.changed(r12)
                                        androidx.compose.material3.pulltorefresh.PullToRefreshState r13 = r11.$state
                                        java.lang.Object r14 = r8.rememberedValue()
                                        if (r12 != 0) goto L74
                                        androidx.compose.runtime.Composer$Companion r12 = androidx.compose.runtime.Composer.INSTANCE
                                        java.lang.Object r12 = r12.getEmpty()
                                        if (r14 != r12) goto L7c
                                    L74:
                                        androidx.compose.material3.pulltorefresh.a r14 = new androidx.compose.material3.pulltorefresh.a
                                        r14.<init>(r13)
                                        r8.updateRememberedValue(r14)
                                    L7c:
                                        androidx.compose.material3.internal.FloatProducer r14 = (androidx.compose.material3.internal.FloatProducer) r14
                                        long r11 = r11.$color
                                        androidx.compose.material3.pulltorefresh.PullToRefreshKt.m1487access$CircularArrowProgressIndicatorRPmYEkk(r14, r11, r8, r2)
                                        r8.endReplaceGroup()
                                    L86:
                                        boolean r11 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                        if (r11 == 0) goto L8f
                                        androidx.compose.runtime.ComposerKt.traceEventEnd()
                                    L8f:
                                        return
                                    L90:
                                        r8 = r13
                                        r8.skipToGroupEnd()
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1.AnonymousClass1.invoke(boolean, androidx.compose.runtime.Composer, int):void");
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer, Integer num) {
                                    invoke(bool.booleanValue(), composer, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }
                        }, composerStartRestartGroup, 54);
                        int i10 = (i5 & 14) | 12582912 | (i5 & 112) | (i5 & 896) | ((i5 >> 6) & V4Signature.MAX_SIGNING_INFOS_SIZE);
                        int i11 = i5 << 6;
                        int i12 = i10 | (458752 & i11) | (i11 & 234881024);
                        Modifier modifier5 = modifier4;
                        m1480IndicatorBox1CPYgEU(pullToRefreshState, z, modifier5, f3, null, indicatorContainerColor, 0.0f, composableLambdaRememberComposableLambda, composerStartRestartGroup, i12, 80);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f2 = f3;
                        j4 = indicatorContainerColor;
                        j5 = j6;
                        modifier3 = modifier5;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        j4 = j3;
                        j5 = indicatorColor;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bvb
                            public final Object invoke(Object obj, Object obj2) {
                                return PullToRefreshDefaults.f(this.b, pullToRefreshState, z, modifier3, j4, j5, f2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                modifier2 = modifier;
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        j3 = j;
                        if (composerStartRestartGroup.changed(j3)) {
                        }
                        i3 |= i8;
                    } else {
                        j3 = j;
                    }
                    i3 |= i8;
                } else {
                    j3 = j;
                }
                if ((i & 24576) == 0) {
                    indicatorColor = j2;
                    if ((i2 & 16) == 0) {
                        i6 = 8192;
                    } else {
                        i6 = 8192;
                    }
                    i3 |= i6;
                } else {
                    indicatorColor = j2;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        f2 = f;
                        if (composerStartRestartGroup.changed(f2)) {
                        }
                        i3 |= i9;
                    } else {
                        f2 = f;
                    }
                    i3 |= i9;
                } else {
                    f2 = f;
                }
                if ((i2 & 64) != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i4 = 1048576;
                    } else {
                        i4 = 524288;
                    }
                    i3 |= i4;
                }
                if ((599187 & i3) != 599186) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i7 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            indicatorContainerColor = getIndicatorContainerColor(composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -7169;
                        } else {
                            indicatorContainerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = getIndicatorColor(composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -57345;
                        }
                        if ((i2 & 32) != 0) {
                            i5 = i3 & (-458753);
                            f3 = IndicatorMaxDistance;
                        } else {
                            i5 = i3;
                            f3 = f2;
                        }
                        j6 = indicatorColor;
                    } else {
                        if (i7 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            indicatorContainerColor = getIndicatorContainerColor(composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -7169;
                        } else {
                            indicatorContainerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = getIndicatorColor(composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -57345;
                        }
                        if ((i2 & 32) != 0) {
                            i5 = i3 & (-458753);
                            f3 = IndicatorMaxDistance;
                        } else {
                            i5 = i3;
                            f3 = f2;
                        }
                        j6 = indicatorColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1076870256, i5, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:515)");
                    }
                    ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(298232649, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1
                        public final void invoke(BoxScope boxScope, Composer composer2, int i13) {
                            if (!composer2.shouldExecute((i13 & 17) != 16, i13 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(298232649, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous> (PullToRefresh.kt:524)");
                            }
                            CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer2, 6), (String) null, ComposableLambdaKt.rememberComposableLambda(-2064098104, true, new AnonymousClass1(j6, pullToRefreshState), composer2, 54), composer2, 24576, 10);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer2, Integer num) {
                            invoke(boxScope, composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1, reason: invalid class name */
                        @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
                        public static final class AnonymousClass1 implements Function3<Boolean, Composer, Integer, Unit> {
                            final /* synthetic */ long $color;
                            final /* synthetic */ PullToRefreshState $state;

                            public AnonymousClass1(long j, PullToRefreshState pullToRefreshState) {
                                this.$color = j;
                                this.$state = pullToRefreshState;
                            }

                            public static float a(PullToRefreshState pullToRefreshState) {
                                return pullToRefreshState.getDistanceFraction();
                            }

                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0076: CONSTRUCTOR (r14v3 'objRememberedValue' java.lang.Object) = (r13v1 'pullToRefreshState' androidx.compose.material3.pulltorefresh.PullToRefreshState A[DONT_INLINE]) A[MD:(androidx.compose.material3.pulltorefresh.PullToRefreshState):void (m)] call: androidx.compose.material3.pulltorefresh.a.<init>(androidx.compose.material3.pulltorefresh.PullToRefreshState):void type: CONSTRUCTOR in method: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1.1.invoke(boolean, androidx.compose.runtime.Composer, int):void, file: /workspace/dex_all/classes4.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:268)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:160)
                                	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:320)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:297)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:268)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:160)
                                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:104)
                                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
                                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
                                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
                                	at jadx.core.ProcessClass.process(ProcessClass.java:89)
                                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:127)
                                	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
                                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.material3.pulltorefresh.a, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                	... 115 more
                                */
                            public final void invoke(boolean r12, androidx.compose.runtime.Composer r13, int r14) {
                                /*
                                    r11 = this;
                                    r0 = r14 & 6
                                    if (r0 != 0) goto Le
                                    boolean r0 = r13.changed(r12)
                                    if (r0 == 0) goto Lc
                                    r0 = 4
                                    goto Ld
                                Lc:
                                    r0 = 2
                                Ld:
                                    r14 = r14 | r0
                                Le:
                                    r0 = r14 & 19
                                    r1 = 18
                                    r2 = 0
                                    if (r0 == r1) goto L17
                                    r0 = 1
                                    goto L18
                                L17:
                                    r0 = r2
                                L18:
                                    r1 = r14 & 1
                                    boolean r0 = r13.shouldExecute(r0, r1)
                                    if (r0 == 0) goto L90
                                    boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                    if (r0 == 0) goto L2f
                                    r0 = -1
                                    java.lang.String r1 = "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:528)"
                                    r3 = -2064098104(0xffffffff84f85cc8, float:-5.8389726E-36)
                                    androidx.compose.runtime.ComposerKt.traceEventStart(r3, r14, r0, r1)
                                L2f:
                                    if (r12 == 0) goto L57
                                    r12 = -499784343(0xffffffffe235e569, float:-8.3884786E20)
                                    r13.startReplaceGroup(r12)
                                    float r3 = androidx.compose.material3.pulltorefresh.PullToRefreshKt.access$getStrokeWidth$p()
                                    androidx.compose.ui.Modifier$Companion r12 = androidx.compose.ui.Modifier.INSTANCE
                                    float r14 = androidx.compose.material3.pulltorefresh.PullToRefreshKt.getSpinnerSize()
                                    androidx.compose.ui.Modifier r0 = androidx.compose.foundation.layout.SizeKt.size-3ABfNKs(r12, r14)
                                    long r1 = r11.$color
                                    r9 = 390(0x186, float:5.47E-43)
                                    r10 = 56
                                    r4 = 0
                                    r6 = 0
                                    r7 = 0
                                    r8 = r13
                                    androidx.compose.material3.ProgressIndicatorKt.m747CircularProgressIndicator4lLiAd8(r0, r1, r3, r4, r6, r7, r8, r9, r10)
                                    r8.endReplaceGroup()
                                    goto L86
                                L57:
                                    r8 = r13
                                    r12 = -499540745(0xffffffffe2399cf7, float:-8.5598954E20)
                                    r8.startReplaceGroup(r12)
                                    androidx.compose.material3.pulltorefresh.PullToRefreshState r12 = r11.$state
                                    boolean r12 = r8.changed(r12)
                                    androidx.compose.material3.pulltorefresh.PullToRefreshState r13 = r11.$state
                                    java.lang.Object r14 = r8.rememberedValue()
                                    if (r12 != 0) goto L74
                                    androidx.compose.runtime.Composer$Companion r12 = androidx.compose.runtime.Composer.INSTANCE
                                    java.lang.Object r12 = r12.getEmpty()
                                    if (r14 != r12) goto L7c
                                L74:
                                    androidx.compose.material3.pulltorefresh.a r14 = new androidx.compose.material3.pulltorefresh.a
                                    r14.<init>(r13)
                                    r8.updateRememberedValue(r14)
                                L7c:
                                    androidx.compose.material3.internal.FloatProducer r14 = (androidx.compose.material3.internal.FloatProducer) r14
                                    long r11 = r11.$color
                                    androidx.compose.material3.pulltorefresh.PullToRefreshKt.m1487access$CircularArrowProgressIndicatorRPmYEkk(r14, r11, r8, r2)
                                    r8.endReplaceGroup()
                                L86:
                                    boolean r11 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                    if (r11 == 0) goto L8f
                                    androidx.compose.runtime.ComposerKt.traceEventEnd()
                                L8f:
                                    return
                                L90:
                                    r8 = r13
                                    r8.skipToGroupEnd()
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1.AnonymousClass1.invoke(boolean, androidx.compose.runtime.Composer, int):void");
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer, Integer num) {
                                invoke(bool.booleanValue(), composer, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }
                    }, composerStartRestartGroup, 54);
                    int i13 = (i5 & 14) | 12582912 | (i5 & 112) | (i5 & 896) | ((i5 >> 6) & V4Signature.MAX_SIGNING_INFOS_SIZE);
                    int i14 = i5 << 6;
                    int i15 = i13 | (458752 & i14) | (i14 & 234881024);
                    Modifier modifier6 = modifier4;
                    m1480IndicatorBox1CPYgEU(pullToRefreshState, z, modifier6, f3, null, indicatorContainerColor, 0.0f, composableLambdaRememberComposableLambda2, composerStartRestartGroup, i15, 80);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f2 = f3;
                    j4 = indicatorContainerColor;
                    j5 = j6;
                    modifier3 = modifier6;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j4 = j3;
                    j5 = indicatorColor;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bvb
                        public final Object invoke(Object obj, Object obj2) {
                            return PullToRefreshDefaults.f(this.b, pullToRefreshState, z, modifier3, j4, j5, f2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }

            /* JADX WARN: Code duplicated, block: B:100:0x0117  */
            /* JADX WARN: Code duplicated, block: B:101:0x011a  */
            /* JADX WARN: Code duplicated, block: B:104:0x0123  */
            /* JADX WARN: Code duplicated, block: B:106:0x0130  */
            /* JADX WARN: Code duplicated, block: B:119:0x014e A[DONT_INVERT] */
            /* JADX WARN: Code duplicated, block: B:120:0x0150  */
            /* JADX WARN: Code duplicated, block: B:123:0x0157  */
            /* JADX WARN: Code duplicated, block: B:126:0x0160  */
            /* JADX WARN: Code duplicated, block: B:127:0x0165  */
            /* JADX WARN: Code duplicated, block: B:129:0x0169  */
            /* JADX WARN: Code duplicated, block: B:132:0x0173  */
            /* JADX WARN: Code duplicated, block: B:135:0x0180  */
            /* JADX WARN: Code duplicated, block: B:138:0x019d  */
            /* JADX WARN: Code duplicated, block: B:141:0x01b0  */
            /* JADX WARN: Code duplicated, block: B:142:0x01b3  */
            /* JADX WARN: Code duplicated, block: B:145:0x01bc  */
            /* JADX WARN: Code duplicated, block: B:146:0x01bf  */
            /* JADX WARN: Code duplicated, block: B:149:0x01cc  */
            /* JADX WARN: Code duplicated, block: B:151:0x01d2  */
            /* JADX WARN: Code duplicated, block: B:157:0x01e7  */
            /* JADX WARN: Code duplicated, block: B:159:0x01ed  */
            /* JADX WARN: Code duplicated, block: B:165:0x0203  */
            /* JADX WARN: Code duplicated, block: B:167:0x0209  */
            /* JADX WARN: Code duplicated, block: B:173:0x0218  */
            /* JADX WARN: Code duplicated, block: B:175:0x021e  */
            /* JADX WARN: Code duplicated, block: B:178:0x0269  */
            /* JADX WARN: Code duplicated, block: B:181:0x0275  */
            /* JADX WARN: Code duplicated, block: B:182:0x0279  */
            /* JADX WARN: Code duplicated, block: B:185:0x029a  */
            /* JADX WARN: Code duplicated, block: B:187:0x02a8  */
            /* JADX WARN: Code duplicated, block: B:190:0x02d5  */
            /* JADX WARN: Code duplicated, block: B:193:0x02df  */
            /* JADX WARN: Code duplicated, block: B:196:0x02eb  */
            /* JADX WARN: Code duplicated, block: B:198:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:26:0x004c  */
            /* JADX WARN: Code duplicated, block: B:28:0x0051  */
            /* JADX WARN: Code duplicated, block: B:30:0x0055  */
            /* JADX WARN: Code duplicated, block: B:32:0x005d  */
            /* JADX WARN: Code duplicated, block: B:33:0x0060  */
            /* JADX WARN: Code duplicated, block: B:37:0x0067  */
            /* JADX WARN: Code duplicated, block: B:39:0x006b  */
            /* JADX WARN: Code duplicated, block: B:41:0x0073  */
            /* JADX WARN: Code duplicated, block: B:42:0x0076  */
            /* JADX WARN: Code duplicated, block: B:45:0x007c  */
            /* JADX WARN: Code duplicated, block: B:48:0x0082  */
            /* JADX WARN: Code duplicated, block: B:50:0x0086  */
            /* JADX WARN: Code duplicated, block: B:53:0x0091 A[ADDED_TO_REGION] */
            /* JADX WARN: Code duplicated, block: B:56:0x0098  */
            /* JADX WARN: Code duplicated, block: B:59:0x00a0  */
            /* JADX WARN: Code duplicated, block: B:60:0x00a5  */
            /* JADX WARN: Code duplicated, block: B:62:0x00ab  */
            /* JADX WARN: Code duplicated, block: B:64:0x00b1  */
            /* JADX WARN: Code duplicated, block: B:65:0x00b4  */
            /* JADX WARN: Code duplicated, block: B:69:0x00be  */
            /* JADX WARN: Code duplicated, block: B:71:0x00c4  */
            /* JADX WARN: Code duplicated, block: B:74:0x00cd  */
            /* JADX WARN: Code duplicated, block: B:76:0x00d2  */
            /* JADX WARN: Code duplicated, block: B:79:0x00da  */
            /* JADX WARN: Code duplicated, block: B:80:0x00dd  */
            /* JADX WARN: Code duplicated, block: B:82:0x00e1  */
            /* JADX WARN: Code duplicated, block: B:84:0x00e7  */
            /* JADX WARN: Code duplicated, block: B:85:0x00ea  */
            /* JADX WARN: Code duplicated, block: B:89:0x00f3  */
            /* JADX WARN: Code duplicated, block: B:91:0x00f8  */
            /* JADX WARN: Code duplicated, block: B:93:0x00fc  */
            /* JADX WARN: Code duplicated, block: B:95:0x0104  */
            /* JADX WARN: Code duplicated, block: B:96:0x0107  */
            /* JADX INFO: renamed from: IndicatorBox-1CPYgEU, reason: not valid java name */
            public final void m1480IndicatorBox1CPYgEU(final PullToRefreshState pullToRefreshState, final boolean z, Modifier modifier, float f, Shape shape2, long j, float f2, final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
                int i3;
                int i4;
                Modifier modifier2;
                int i5;
                float f3;
                int i6;
                long jM3170getUnspecified0d7_KjU;
                int i7;
                float f4;
                int i8;
                int i9;
                boolean z2;
                boolean z3;
                final Shape shape3;
                final Modifier modifier3;
                final float f5;
                final long j2;
                final float f6;
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
                Shape shape4;
                Object objRememberedValue;
                Composer.Companion companion;
                boolean z4;
                boolean z5;
                boolean z6;
                Object objRememberedValue2;
                int currentCompositeKeyHash;
                Function0<ComposeUiNode> constructor;
                Composer composerM2388constructorimpl;
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
                int i10;
                Composer composerStartRestartGroup = composer.startRestartGroup(-1341144489);
                if ((i2 & 1) != 0) {
                    i3 = i | 6;
                } else if ((i & 6) == 0) {
                    i3 = (composerStartRestartGroup.changed(pullToRefreshState) ? 4 : 2) | i;
                } else {
                    i3 = i;
                }
                if ((i2 & 2) == 0) {
                    if ((i & 48) == 0) {
                        i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
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
                        if ((i & 3072) == 0) {
                            if ((i2 & 8) == 0) {
                                f3 = f;
                                int i11 = composerStartRestartGroup.changed(f3) ? 2048 : 1024;
                                i3 |= i11;
                            } else {
                                f3 = f;
                            }
                            i3 |= i11;
                        } else {
                            f3 = f;
                        }
                        if ((i & 24576) != 0) {
                            i3 |= ((i2 & 16) == 0 || !composerStartRestartGroup.changed(shape2)) ? 8192 : 16384;
                        }
                        i6 = i2 & 32;
                        if (i6 != 0) {
                            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            jM3170getUnspecified0d7_KjU = j;
                        } else {
                            jM3170getUnspecified0d7_KjU = j;
                            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                if (composerStartRestartGroup.changed(jM3170getUnspecified0d7_KjU)) {
                                    i7 = 131072;
                                } else {
                                    i7 = 65536;
                                }
                                i3 |= i7;
                            }
                        }
                        if ((i & 1572864) == 0) {
                            f4 = f2;
                            if ((i2 & 64) == 0 || !composerStartRestartGroup.changed(f4)) {
                                i10 = 524288;
                            } else {
                                i10 = 1048576;
                            }
                            i3 |= i10;
                        } else {
                            f4 = f2;
                        }
                        if ((i2 & 128) != 0) {
                            i3 |= 12582912;
                        } else if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i8 = 8388608;
                            } else {
                                i8 = 4194304;
                            }
                            i3 |= i8;
                        }
                        if ((i2 & 256) != 0) {
                            if ((i & 100663296) == 0) {
                                if (composerStartRestartGroup.changed(this)) {
                                    i9 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                                } else {
                                    i9 = 33554432;
                                }
                                i3 |= i9;
                            }
                            z2 = true;
                            if ((i3 & 38347923) != 38347922) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i4 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if ((i2 & 8) != 0) {
                                        i3 &= -7169;
                                        f3 = IndicatorMaxDistance;
                                    }
                                    if ((i2 & 16) != 0) {
                                        shape4 = indicatorShape;
                                        i3 &= -57345;
                                    } else {
                                        shape4 = shape2;
                                    }
                                    if (i6 != 0) {
                                        jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                                    }
                                    if ((i2 & 64) != 0) {
                                        i3 &= -3670017;
                                        f4 = Elevation;
                                    }
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    if ((i2 & 8) != 0) {
                                        i3 &= -7169;
                                    }
                                    if ((i2 & 16) != 0) {
                                        i3 &= -57345;
                                    }
                                    if ((i2 & 64) != 0) {
                                        i3 &= -3670017;
                                    }
                                    shape4 = shape2;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1341144489, i3, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.IndicatorBox (PullToRefresh.kt:456)");
                                }
                                Modifier modifier4 = SizeKt.size-3ABfNKs(modifier2, PullToRefreshKt.getSpinnerContainerSize());
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                companion = Composer.INSTANCE;
                                if (objRememberedValue == companion.getEmpty()) {
                                    objRememberedValue = new Function1() { // from class: dvb
                                        public final Object invoke(Object obj) {
                                            return PullToRefreshDefaults.c((ContentDrawScope) obj);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                Modifier modifierDrawWithContent = DrawModifierKt.drawWithContent(modifier4, (Function1) objRememberedValue);
                                if ((i3 & 14) == 4) {
                                    z4 = true;
                                } else {
                                    z4 = false;
                                }
                                boolean z7 = z4;
                                if ((i3 & 112) == 32) {
                                    z5 = true;
                                } else {
                                    z5 = false;
                                }
                                boolean z8 = z7 | z5 | ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(f3)) || (i3 & 3072) == 2048) | ((((3670016 & i3) ^ 1572864) <= 1048576 && composerStartRestartGroup.changed(f4)) || (i3 & 1572864) == 1048576);
                                if ((((57344 & i3) ^ 24576) > 16384 || !composerStartRestartGroup.changed(shape4)) && (i3 & 24576) != 16384) {
                                }
                                z6 = z8 | z2;
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z6 || objRememberedValue2 == companion.getEmpty()) {
                                    final Shape shape5 = shape4;
                                    final float f7 = f3;
                                    final float f8 = f4;
                                    objRememberedValue2 = new Function3() { // from class: evb
                                        @Override // kotlin.jvm.functions.Function3
                                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                                            return PullToRefreshDefaults.b(pullToRefreshState, z, f7, f8, shape5, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                Modifier modifier5 = BackgroundKt.background-bw27NRU(LayoutModifierKt.layout(modifierDrawWithContent, (Function3) objRememberedValue2), jM3170getUnspecified0d7_KjU, shape4);
                                Alignment center = Alignment.INSTANCE.getCenter();
                                int i12 = ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 48;
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier5);
                                ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                Shape shape6 = shape4;
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
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                function3.invoke(BoxScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i12 >> 6) & 112) | 6));
                                composerStartRestartGroup.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                shape3 = shape6;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                shape3 = shape2;
                            }
                            modifier3 = modifier2;
                            f5 = f3;
                            j2 = jM3170getUnspecified0d7_KjU;
                            f6 = f4;
                            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fvb
                                    public final Object invoke(Object obj, Object obj2) {
                                        return PullToRefreshDefaults.a(this.b, pullToRefreshState, z, modifier3, f5, shape3, j2, f6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i3 |= 100663296;
                        z2 = true;
                        if ((i3 & 38347923) != 38347922) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                    f3 = IndicatorMaxDistance;
                                }
                                if ((i2 & 16) != 0) {
                                    shape4 = indicatorShape;
                                    i3 &= -57345;
                                } else {
                                    shape4 = shape2;
                                }
                                if (i6 != 0) {
                                    jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                    f4 = Elevation;
                                }
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                    f3 = IndicatorMaxDistance;
                                }
                                if ((i2 & 16) != 0) {
                                    shape4 = indicatorShape;
                                    i3 &= -57345;
                                } else {
                                    shape4 = shape2;
                                }
                                if (i6 != 0) {
                                    jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                    f4 = Elevation;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1341144489, i3, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.IndicatorBox (PullToRefresh.kt:456)");
                            }
                            Modifier modifier6 = SizeKt.size-3ABfNKs(modifier2, PullToRefreshKt.getSpinnerContainerSize());
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: dvb
                                    public final Object invoke(Object obj) {
                                        return PullToRefreshDefaults.c((ContentDrawScope) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            Modifier modifierDrawWithContent2 = DrawModifierKt.drawWithContent(modifier6, (Function1) objRememberedValue);
                            if ((i3 & 14) == 4) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            boolean z9 = z4;
                            if ((i3 & 112) == 32) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            boolean z10 = z9 | z5 | ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(f3)) || (i3 & 3072) == 2048) | ((((3670016 & i3) ^ 1572864) <= 1048576 && composerStartRestartGroup.changed(f4)) || (i3 & 1572864) == 1048576);
                            z2 = ((57344 & i3) ^ 24576) > 16384 ? false : false;
                            z6 = z10 | z2;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                final Shape shape7 = shape4;
                                final float f9 = f3;
                                final float f10 = f4;
                                objRememberedValue2 = new Function3() { // from class: evb
                                    @Override // kotlin.jvm.functions.Function3
                                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                                        return PullToRefreshDefaults.b(pullToRefreshState, z, f9, f10, shape7, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                final Shape shape8 = shape4;
                                final float f11 = f3;
                                final float f12 = f4;
                                objRememberedValue2 = new Function3() { // from class: evb
                                    @Override // kotlin.jvm.functions.Function3
                                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                                        return PullToRefreshDefaults.b(pullToRefreshState, z, f11, f12, shape8, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Modifier modifier7 = BackgroundKt.background-bw27NRU(LayoutModifierKt.layout(modifierDrawWithContent2, (Function3) objRememberedValue2), jM3170getUnspecified0d7_KjU, shape4);
                            Alignment center2 = Alignment.INSTANCE.getCenter();
                            int i13 = ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 48;
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier7);
                            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                            Shape shape9 = shape4;
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
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, companion3.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier2, companion3.getSetModifier());
                            function3.invoke(BoxScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i13 >> 6) & 112) | 6));
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            shape3 = shape9;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            shape3 = shape2;
                        }
                        modifier3 = modifier2;
                        f5 = f3;
                        j2 = jM3170getUnspecified0d7_KjU;
                        f6 = f4;
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fvb
                                public final Object invoke(Object obj, Object obj2) {
                                    return PullToRefreshDefaults.a(this.b, pullToRefreshState, z, modifier3, f5, shape3, j2, f6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                    modifier2 = modifier;
                    if ((i & 3072) == 0) {
                        if ((i2 & 8) == 0) {
                            f3 = f;
                            if (composerStartRestartGroup.changed(f3)) {
                            }
                            i3 |= i11;
                        } else {
                            f3 = f;
                        }
                        i3 |= i11;
                    } else {
                        f3 = f;
                    }
                    if ((i & 24576) != 0) {
                        i3 |= ((i2 & 16) == 0 || !composerStartRestartGroup.changed(shape2)) ? 8192 : 16384;
                    }
                    i6 = i2 & 32;
                    if (i6 != 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        jM3170getUnspecified0d7_KjU = j;
                    } else {
                        jM3170getUnspecified0d7_KjU = j;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(jM3170getUnspecified0d7_KjU)) {
                                i7 = 131072;
                            } else {
                                i7 = 65536;
                            }
                            i3 |= i7;
                        }
                    }
                    if ((i & 1572864) == 0) {
                        f4 = f2;
                        if ((i2 & 64) == 0) {
                            i10 = 524288;
                        } else {
                            i10 = 524288;
                        }
                        i3 |= i10;
                    } else {
                        f4 = f2;
                    }
                    if ((i2 & 128) != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 8388608;
                        } else {
                            i8 = 4194304;
                        }
                        i3 |= i8;
                    }
                    if ((i2 & 256) != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(this)) {
                                i9 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i9 = 33554432;
                            }
                            i3 |= i9;
                        }
                        z2 = true;
                        if ((i3 & 38347923) != 38347922) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                    f3 = IndicatorMaxDistance;
                                }
                                if ((i2 & 16) != 0) {
                                    shape4 = indicatorShape;
                                    i3 &= -57345;
                                } else {
                                    shape4 = shape2;
                                }
                                if (i6 != 0) {
                                    jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                    f4 = Elevation;
                                }
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                    f3 = IndicatorMaxDistance;
                                }
                                if ((i2 & 16) != 0) {
                                    shape4 = indicatorShape;
                                    i3 &= -57345;
                                } else {
                                    shape4 = shape2;
                                }
                                if (i6 != 0) {
                                    jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                    f4 = Elevation;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1341144489, i3, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.IndicatorBox (PullToRefresh.kt:456)");
                            }
                            Modifier modifier8 = SizeKt.size-3ABfNKs(modifier2, PullToRefreshKt.getSpinnerContainerSize());
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: dvb
                                    public final Object invoke(Object obj) {
                                        return PullToRefreshDefaults.c((ContentDrawScope) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            Modifier modifierDrawWithContent3 = DrawModifierKt.drawWithContent(modifier8, (Function1) objRememberedValue);
                            if ((i3 & 14) == 4) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            boolean z11 = z4;
                            if ((i3 & 112) == 32) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            boolean z12 = z11 | z5 | ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(f3)) || (i3 & 3072) == 2048) | ((((3670016 & i3) ^ 1572864) <= 1048576 && composerStartRestartGroup.changed(f4)) || (i3 & 1572864) == 1048576);
                            if (((57344 & i3) ^ 24576) > 16384) {
                            }
                            z6 = z12 | z2;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                final Shape shape10 = shape4;
                                final float f13 = f3;
                                final float f14 = f4;
                                objRememberedValue2 = new Function3() { // from class: evb
                                    @Override // kotlin.jvm.functions.Function3
                                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                                        return PullToRefreshDefaults.b(pullToRefreshState, z, f13, f14, shape10, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                final Shape shape11 = shape4;
                                final float f15 = f3;
                                final float f16 = f4;
                                objRememberedValue2 = new Function3() { // from class: evb
                                    @Override // kotlin.jvm.functions.Function3
                                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                                        return PullToRefreshDefaults.b(pullToRefreshState, z, f15, f16, shape11, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Modifier modifier9 = BackgroundKt.background-bw27NRU(LayoutModifierKt.layout(modifierDrawWithContent3, (Function3) objRememberedValue2), jM3170getUnspecified0d7_KjU, shape4);
                            Alignment center3 = Alignment.INSTANCE.getCenter();
                            int i14 = ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 48;
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(center3, false);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier9);
                            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
                            Shape shape12 = shape4;
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
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, companion4.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap3, companion4.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion4.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier3, companion4.getSetModifier());
                            function3.invoke(BoxScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i14 >> 6) & 112) | 6));
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            shape3 = shape12;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            shape3 = shape2;
                        }
                        modifier3 = modifier2;
                        f5 = f3;
                        j2 = jM3170getUnspecified0d7_KjU;
                        f6 = f4;
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fvb
                                public final Object invoke(Object obj, Object obj2) {
                                    return PullToRefreshDefaults.a(this.b, pullToRefreshState, z, modifier3, f5, shape3, j2, f6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    z2 = true;
                    if ((i3 & 38347923) != 38347922) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                f3 = IndicatorMaxDistance;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = indicatorShape;
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if (i6 != 0) {
                                jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                                f4 = Elevation;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                f3 = IndicatorMaxDistance;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = indicatorShape;
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if (i6 != 0) {
                                jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                                f4 = Elevation;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1341144489, i3, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.IndicatorBox (PullToRefresh.kt:456)");
                        }
                        Modifier modifier10 = SizeKt.size-3ABfNKs(modifier2, PullToRefreshKt.getSpinnerContainerSize());
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: dvb
                                public final Object invoke(Object obj) {
                                    return PullToRefreshDefaults.c((ContentDrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        Modifier modifierDrawWithContent4 = DrawModifierKt.drawWithContent(modifier10, (Function1) objRememberedValue);
                        if ((i3 & 14) == 4) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z13 = z4;
                        if ((i3 & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        boolean z14 = z13 | z5 | ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(f3)) || (i3 & 3072) == 2048) | ((((3670016 & i3) ^ 1572864) <= 1048576 && composerStartRestartGroup.changed(f4)) || (i3 & 1572864) == 1048576);
                        if (((57344 & i3) ^ 24576) > 16384) {
                        }
                        z6 = z14 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            final Shape shape13 = shape4;
                            final float f17 = f3;
                            final float f18 = f4;
                            objRememberedValue2 = new Function3() { // from class: evb
                                @Override // kotlin.jvm.functions.Function3
                                public final Object invoke(Object obj, Object obj2, Object obj3) {
                                    return PullToRefreshDefaults.b(pullToRefreshState, z, f17, f18, shape13, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            final Shape shape14 = shape4;
                            final float f19 = f3;
                            final float f110 = f4;
                            objRememberedValue2 = new Function3() { // from class: evb
                                @Override // kotlin.jvm.functions.Function3
                                public final Object invoke(Object obj, Object obj2, Object obj3) {
                                    return PullToRefreshDefaults.b(pullToRefreshState, z, f19, f110, shape14, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifier11 = BackgroundKt.background-bw27NRU(LayoutModifierKt.layout(modifierDrawWithContent4, (Function3) objRememberedValue2), jM3170getUnspecified0d7_KjU, shape4);
                        Alignment center4 = Alignment.INSTANCE.getCenter();
                        int i15 = ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 48;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(center4, false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11);
                        ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
                        Shape shape15 = shape4;
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy4, companion5.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap4, companion5.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion5.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier4, companion5.getSetModifier());
                        function3.invoke(BoxScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i15 >> 6) & 112) | 6));
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        shape3 = shape15;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape3 = shape2;
                    }
                    modifier3 = modifier2;
                    f5 = f3;
                    j2 = jM3170getUnspecified0d7_KjU;
                    f6 = f4;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fvb
                            public final Object invoke(Object obj, Object obj2) {
                                return PullToRefreshDefaults.a(this.b, pullToRefreshState, z, modifier3, f5, shape3, j2, f6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 48;
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
                    if ((i & 3072) == 0) {
                        if ((i2 & 8) == 0) {
                            f3 = f;
                            if (composerStartRestartGroup.changed(f3)) {
                            }
                            i3 |= i11;
                        } else {
                            f3 = f;
                        }
                        i3 |= i11;
                    } else {
                        f3 = f;
                    }
                    if ((i & 24576) != 0) {
                        i3 |= ((i2 & 16) == 0 || !composerStartRestartGroup.changed(shape2)) ? 8192 : 16384;
                    }
                    i6 = i2 & 32;
                    if (i6 != 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        jM3170getUnspecified0d7_KjU = j;
                    } else {
                        jM3170getUnspecified0d7_KjU = j;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(jM3170getUnspecified0d7_KjU)) {
                                i7 = 131072;
                            } else {
                                i7 = 65536;
                            }
                            i3 |= i7;
                        }
                    }
                    if ((i & 1572864) == 0) {
                        f4 = f2;
                        if ((i2 & 64) == 0) {
                            i10 = 524288;
                        } else {
                            i10 = 524288;
                        }
                        i3 |= i10;
                    } else {
                        f4 = f2;
                    }
                    if ((i2 & 128) != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 8388608;
                        } else {
                            i8 = 4194304;
                        }
                        i3 |= i8;
                    }
                    if ((i2 & 256) != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(this)) {
                                i9 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i9 = 33554432;
                            }
                            i3 |= i9;
                        }
                        z2 = true;
                        if ((i3 & 38347923) != 38347922) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                    f3 = IndicatorMaxDistance;
                                }
                                if ((i2 & 16) != 0) {
                                    shape4 = indicatorShape;
                                    i3 &= -57345;
                                } else {
                                    shape4 = shape2;
                                }
                                if (i6 != 0) {
                                    jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                    f4 = Elevation;
                                }
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                    f3 = IndicatorMaxDistance;
                                }
                                if ((i2 & 16) != 0) {
                                    shape4 = indicatorShape;
                                    i3 &= -57345;
                                } else {
                                    shape4 = shape2;
                                }
                                if (i6 != 0) {
                                    jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                    f4 = Elevation;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1341144489, i3, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.IndicatorBox (PullToRefresh.kt:456)");
                            }
                            Modifier modifier12 = SizeKt.size-3ABfNKs(modifier2, PullToRefreshKt.getSpinnerContainerSize());
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: dvb
                                    public final Object invoke(Object obj) {
                                        return PullToRefreshDefaults.c((ContentDrawScope) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            Modifier modifierDrawWithContent5 = DrawModifierKt.drawWithContent(modifier12, (Function1) objRememberedValue);
                            if ((i3 & 14) == 4) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            boolean z15 = z4;
                            if ((i3 & 112) == 32) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            boolean z16 = z15 | z5 | ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(f3)) || (i3 & 3072) == 2048) | ((((3670016 & i3) ^ 1572864) <= 1048576 && composerStartRestartGroup.changed(f4)) || (i3 & 1572864) == 1048576);
                            if (((57344 & i3) ^ 24576) > 16384) {
                            }
                            z6 = z16 | z2;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                final Shape shape16 = shape4;
                                final float f111 = f3;
                                final float f112 = f4;
                                objRememberedValue2 = new Function3() { // from class: evb
                                    @Override // kotlin.jvm.functions.Function3
                                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                                        return PullToRefreshDefaults.b(pullToRefreshState, z, f111, f112, shape16, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                final Shape shape17 = shape4;
                                final float f113 = f3;
                                final float f114 = f4;
                                objRememberedValue2 = new Function3() { // from class: evb
                                    @Override // kotlin.jvm.functions.Function3
                                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                                        return PullToRefreshDefaults.b(pullToRefreshState, z, f113, f114, shape17, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Modifier modifier13 = BackgroundKt.background-bw27NRU(LayoutModifierKt.layout(modifierDrawWithContent5, (Function3) objRememberedValue2), jM3170getUnspecified0d7_KjU, shape4);
                            Alignment center5 = Alignment.INSTANCE.getCenter();
                            int i16 = ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 48;
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(center5, false);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier13);
                            ComposeUiNode.Companion companion6 = ComposeUiNode.INSTANCE;
                            Shape shape18 = shape4;
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
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy5, companion6.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap5, companion6.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion6.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier5, companion6.getSetModifier());
                            function3.invoke(BoxScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i16 >> 6) & 112) | 6));
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            shape3 = shape18;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            shape3 = shape2;
                        }
                        modifier3 = modifier2;
                        f5 = f3;
                        j2 = jM3170getUnspecified0d7_KjU;
                        f6 = f4;
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fvb
                                public final Object invoke(Object obj, Object obj2) {
                                    return PullToRefreshDefaults.a(this.b, pullToRefreshState, z, modifier3, f5, shape3, j2, f6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    z2 = true;
                    if ((i3 & 38347923) != 38347922) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                f3 = IndicatorMaxDistance;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = indicatorShape;
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if (i6 != 0) {
                                jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                                f4 = Elevation;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                f3 = IndicatorMaxDistance;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = indicatorShape;
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if (i6 != 0) {
                                jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                                f4 = Elevation;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1341144489, i3, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.IndicatorBox (PullToRefresh.kt:456)");
                        }
                        Modifier modifier14 = SizeKt.size-3ABfNKs(modifier2, PullToRefreshKt.getSpinnerContainerSize());
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: dvb
                                public final Object invoke(Object obj) {
                                    return PullToRefreshDefaults.c((ContentDrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        Modifier modifierDrawWithContent6 = DrawModifierKt.drawWithContent(modifier14, (Function1) objRememberedValue);
                        if ((i3 & 14) == 4) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z17 = z4;
                        if ((i3 & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        boolean z18 = z17 | z5 | ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(f3)) || (i3 & 3072) == 2048) | ((((3670016 & i3) ^ 1572864) <= 1048576 && composerStartRestartGroup.changed(f4)) || (i3 & 1572864) == 1048576);
                        if (((57344 & i3) ^ 24576) > 16384) {
                        }
                        z6 = z18 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            final Shape shape19 = shape4;
                            final float f115 = f3;
                            final float f116 = f4;
                            objRememberedValue2 = new Function3() { // from class: evb
                                @Override // kotlin.jvm.functions.Function3
                                public final Object invoke(Object obj, Object obj2, Object obj3) {
                                    return PullToRefreshDefaults.b(pullToRefreshState, z, f115, f116, shape19, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            final Shape shape110 = shape4;
                            final float f117 = f3;
                            final float f118 = f4;
                            objRememberedValue2 = new Function3() { // from class: evb
                                @Override // kotlin.jvm.functions.Function3
                                public final Object invoke(Object obj, Object obj2, Object obj3) {
                                    return PullToRefreshDefaults.b(pullToRefreshState, z, f117, f118, shape110, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifier15 = BackgroundKt.background-bw27NRU(LayoutModifierKt.layout(modifierDrawWithContent6, (Function3) objRememberedValue2), jM3170getUnspecified0d7_KjU, shape4);
                        Alignment center6 = Alignment.INSTANCE.getCenter();
                        int i17 = ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 48;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(center6, false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier15);
                        ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
                        Shape shape111 = shape4;
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy6, companion7.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap6, companion7.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion7.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier6, companion7.getSetModifier());
                        function3.invoke(BoxScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i17 >> 6) & 112) | 6));
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        shape3 = shape111;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape3 = shape2;
                    }
                    modifier3 = modifier2;
                    f5 = f3;
                    j2 = jM3170getUnspecified0d7_KjU;
                    f6 = f4;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fvb
                            public final Object invoke(Object obj, Object obj2) {
                                return PullToRefreshDefaults.a(this.b, pullToRefreshState, z, modifier3, f5, shape3, j2, f6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                modifier2 = modifier;
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        f3 = f;
                        if (composerStartRestartGroup.changed(f3)) {
                        }
                        i3 |= i11;
                    } else {
                        f3 = f;
                    }
                    i3 |= i11;
                } else {
                    f3 = f;
                }
                if ((i & 24576) != 0) {
                    i3 |= ((i2 & 16) == 0 || !composerStartRestartGroup.changed(shape2)) ? 8192 : 16384;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    jM3170getUnspecified0d7_KjU = j;
                } else {
                    jM3170getUnspecified0d7_KjU = j;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(jM3170getUnspecified0d7_KjU)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                }
                if ((i & 1572864) == 0) {
                    f4 = f2;
                    if ((i2 & 64) == 0) {
                        i10 = 524288;
                    } else {
                        i10 = 524288;
                    }
                    i3 |= i10;
                } else {
                    f4 = f2;
                }
                if ((i2 & 128) != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i3 |= i8;
                }
                if ((i2 & 256) != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i9 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i9 = 33554432;
                        }
                        i3 |= i9;
                    }
                    z2 = true;
                    if ((i3 & 38347923) != 38347922) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                f3 = IndicatorMaxDistance;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = indicatorShape;
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if (i6 != 0) {
                                jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                                f4 = Elevation;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                f3 = IndicatorMaxDistance;
                            }
                            if ((i2 & 16) != 0) {
                                shape4 = indicatorShape;
                                i3 &= -57345;
                            } else {
                                shape4 = shape2;
                            }
                            if (i6 != 0) {
                                jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                                f4 = Elevation;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1341144489, i3, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.IndicatorBox (PullToRefresh.kt:456)");
                        }
                        Modifier modifier16 = SizeKt.size-3ABfNKs(modifier2, PullToRefreshKt.getSpinnerContainerSize());
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: dvb
                                public final Object invoke(Object obj) {
                                    return PullToRefreshDefaults.c((ContentDrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        Modifier modifierDrawWithContent7 = DrawModifierKt.drawWithContent(modifier16, (Function1) objRememberedValue);
                        if ((i3 & 14) == 4) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z19 = z4;
                        if ((i3 & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        boolean z110 = z19 | z5 | ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(f3)) || (i3 & 3072) == 2048) | ((((3670016 & i3) ^ 1572864) <= 1048576 && composerStartRestartGroup.changed(f4)) || (i3 & 1572864) == 1048576);
                        if (((57344 & i3) ^ 24576) > 16384) {
                        }
                        z6 = z110 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            final Shape shape112 = shape4;
                            final float f119 = f3;
                            final float f1110 = f4;
                            objRememberedValue2 = new Function3() { // from class: evb
                                @Override // kotlin.jvm.functions.Function3
                                public final Object invoke(Object obj, Object obj2, Object obj3) {
                                    return PullToRefreshDefaults.b(pullToRefreshState, z, f119, f1110, shape112, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            final Shape shape113 = shape4;
                            final float f1111 = f3;
                            final float f1112 = f4;
                            objRememberedValue2 = new Function3() { // from class: evb
                                @Override // kotlin.jvm.functions.Function3
                                public final Object invoke(Object obj, Object obj2, Object obj3) {
                                    return PullToRefreshDefaults.b(pullToRefreshState, z, f1111, f1112, shape113, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifier17 = BackgroundKt.background-bw27NRU(LayoutModifierKt.layout(modifierDrawWithContent7, (Function3) objRememberedValue2), jM3170getUnspecified0d7_KjU, shape4);
                        Alignment center7 = Alignment.INSTANCE.getCenter();
                        int i18 = ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 48;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(center7, false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier17);
                        ComposeUiNode.Companion companion8 = ComposeUiNode.INSTANCE;
                        Shape shape114 = shape4;
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy7, companion8.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap7, companion8.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion8.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier7, companion8.getSetModifier());
                        function3.invoke(BoxScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i18 >> 6) & 112) | 6));
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        shape3 = shape114;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape3 = shape2;
                    }
                    modifier3 = modifier2;
                    f5 = f3;
                    j2 = jM3170getUnspecified0d7_KjU;
                    f6 = f4;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fvb
                            public final Object invoke(Object obj, Object obj2) {
                                return PullToRefreshDefaults.a(this.b, pullToRefreshState, z, modifier3, f5, shape3, j2, f6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                z2 = true;
                if ((i3 & 38347923) != 38347922) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            f3 = IndicatorMaxDistance;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = indicatorShape;
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if (i6 != 0) {
                            jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            f4 = Elevation;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            f3 = IndicatorMaxDistance;
                        }
                        if ((i2 & 16) != 0) {
                            shape4 = indicatorShape;
                            i3 &= -57345;
                        } else {
                            shape4 = shape2;
                        }
                        if (i6 != 0) {
                            jM3170getUnspecified0d7_KjU = Color.INSTANCE.m3170getUnspecified0d7_KjU();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            f4 = Elevation;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1341144489, i3, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.IndicatorBox (PullToRefresh.kt:456)");
                    }
                    Modifier modifier18 = SizeKt.size-3ABfNKs(modifier2, PullToRefreshKt.getSpinnerContainerSize());
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: dvb
                            public final Object invoke(Object obj) {
                                return PullToRefreshDefaults.c((ContentDrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Modifier modifierDrawWithContent8 = DrawModifierKt.drawWithContent(modifier18, (Function1) objRememberedValue);
                    if ((i3 & 14) == 4) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z111 = z4;
                    if ((i3 & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    boolean z112 = z111 | z5 | ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(f3)) || (i3 & 3072) == 2048) | ((((3670016 & i3) ^ 1572864) <= 1048576 && composerStartRestartGroup.changed(f4)) || (i3 & 1572864) == 1048576);
                    if (((57344 & i3) ^ 24576) > 16384) {
                    }
                    z6 = z112 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        final Shape shape115 = shape4;
                        final float f1113 = f3;
                        final float f1114 = f4;
                        objRememberedValue2 = new Function3() { // from class: evb
                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj, Object obj2, Object obj3) {
                                return PullToRefreshDefaults.b(pullToRefreshState, z, f1113, f1114, shape115, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        final Shape shape116 = shape4;
                        final float f1115 = f3;
                        final float f1116 = f4;
                        objRememberedValue2 = new Function3() { // from class: evb
                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj, Object obj2, Object obj3) {
                                return PullToRefreshDefaults.b(pullToRefreshState, z, f1115, f1116, shape116, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifier19 = BackgroundKt.background-bw27NRU(LayoutModifierKt.layout(modifierDrawWithContent8, (Function3) objRememberedValue2), jM3170getUnspecified0d7_KjU, shape4);
                    Alignment center8 = Alignment.INSTANCE.getCenter();
                    int i19 = ((i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 48;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(center8, false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier19);
                    ComposeUiNode.Companion companion9 = ComposeUiNode.INSTANCE;
                    Shape shape117 = shape4;
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
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy8, companion9.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap8, companion9.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion9.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier8, companion9.getSetModifier());
                    function3.invoke(BoxScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i19 >> 6) & 112) | 6));
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    shape3 = shape117;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape3 = shape2;
                }
                modifier3 = modifier2;
                f5 = f3;
                j2 = jM3170getUnspecified0d7_KjU;
                f6 = f4;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fvb
                        public final Object invoke(Object obj, Object obj2) {
                            return PullToRefreshDefaults.a(this.b, pullToRefreshState, z, modifier3, f5, shape3, j2, f6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }

            public final long getContainerColor(Composer composer, int i) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1066257972, i, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.<get-containerColor> (PullToRefresh.kt:405)");
                }
                long surfaceContainerHigh = MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurfaceContainerHigh();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return surfaceContainerHigh;
            }

            /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name */
            public final float m1481getElevationD9Ej5fM() {
                return Elevation;
            }

            public final long getIndicatorColor(Composer composer, int i) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1441334156, i, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.<get-indicatorColor> (PullToRefresh.kt:413)");
                }
                long onSurfaceVariant = MaterialTheme.INSTANCE.getColorScheme(composer, 6).getOnSurfaceVariant();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return onSurfaceVariant;
            }

            public final long getIndicatorContainerColor(Composer composer, int i) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-80510850, i, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.<get-indicatorContainerColor> (PullToRefresh.kt:409)");
                }
                long surfaceContainerHigh = MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurfaceContainerHigh();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return surfaceContainerHigh;
            }

            /* JADX INFO: renamed from: getIndicatorMaxDistance-D9Ej5fM, reason: not valid java name */
            public final float m1482getIndicatorMaxDistanceD9Ej5fM() {
                return IndicatorMaxDistance;
            }

            public final Shape getIndicatorShape() {
                return indicatorShape;
            }

            /* JADX INFO: renamed from: getLoadingIndicatorElevation-D9Ej5fM, reason: not valid java name */
            public final float m1483getLoadingIndicatorElevationD9Ej5fM() {
                return LoadingIndicatorElevation;
            }

            /* JADX INFO: renamed from: getPositionalThreshold-D9Ej5fM, reason: not valid java name */
            public final float m1484getPositionalThresholdD9Ej5fM() {
                return PositionalThreshold;
            }

            public final Shape getShape() {
                return shape;
            }
        }
