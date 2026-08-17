package androidx.compose.material3;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.ExitTransition;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.material3.SegmentedButtonDefaults;
import androidx.compose.material3.internal.Icons;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.OutlinedSegmentedButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J\u0087\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007¢\u0006\u0002\u0010(J\r\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u00101JA\u00102\u001a\u0002002\u0006\u00103\u001a\u0002042\u0013\b\u0002\u00105\u001a\r\u0012\u0004\u0012\u00020006¢\u0006\u0002\b72\u0015\b\u0002\u00108\u001a\u000f\u0012\u0004\u0012\u000200\u0018\u000106¢\u0006\u0002\b7H\u0007¢\u0006\u0002\u00109J\u001f\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\b2\b\b\u0002\u0010=\u001a\u00020\u001f¢\u0006\u0004\b>\u0010?R\u0018\u0010\u0016\u001a\u00020\u0005*\u00020\u00178@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b8G¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u001e\u001a\u00020\u001f¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0013\u0010)\u001a\u00020\u001f¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b*\u0010!R\u0011\u0010+\u001a\u00020,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u0006@"}, d2 = {"Landroidx/compose/material3/SegmentedButtonDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/SegmentedButtonColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SegmentedButtonColors;", "activeContainerColor", "Landroidx/compose/ui/graphics/Color;", "activeContentColor", "activeBorderColor", "inactiveContainerColor", "inactiveContentColor", "inactiveBorderColor", "disabledActiveContainerColor", "disabledActiveContentColor", "disabledActiveBorderColor", "disabledInactiveContainerColor", "disabledInactiveContentColor", "disabledInactiveBorderColor", "colors-XqyqHi0", "(JJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SegmentedButtonColors;", "defaultSegmentedButtonColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultSegmentedButtonColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SegmentedButtonColors;", "baseShape", "Landroidx/compose/foundation/shape/CornerBasedShape;", "getBaseShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/shape/CornerBasedShape;", "BorderWidth", "Landroidx/compose/ui/unit/Dp;", "getBorderWidth-D9Ej5fM", "()F", "F", "itemShape", "Landroidx/compose/ui/graphics/Shape;", "index", "", "count", "(IILandroidx/compose/foundation/shape/CornerBasedShape;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/Shape;", "IconSize", "getIconSize-D9Ej5fM", "ContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "ActiveIcon", "", "(Landroidx/compose/runtime/Composer;I)V", "Icon", "active", "", "activeContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "inactiveContent", "(ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "borderStroke", "Landroidx/compose/foundation/BorderStroke;", "color", "width", "borderStroke-l07J4OM", "(JF)Landroidx/compose/foundation/BorderStroke;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SegmentedButtonDefaults {
    public static final int $stable = 0;
    private static final float BorderWidth;
    private static final PaddingValues ContentPadding;
    public static final SegmentedButtonDefaults INSTANCE = new SegmentedButtonDefaults();
    private static final float IconSize;

    static {
        OutlinedSegmentedButtonTokens outlinedSegmentedButtonTokens = OutlinedSegmentedButtonTokens.INSTANCE;
        BorderWidth = outlinedSegmentedButtonTokens.m1979getOutlineWidthD9Ej5fM();
        IconSize = outlinedSegmentedButtonTokens.m1978getIconSizeD9Ej5fM();
        ContentPadding = ButtonDefaults.INSTANCE.getTextButtonContentPadding();
    }

    private SegmentedButtonDefaults() {
    }

    public static Unit a(SegmentedButtonDefaults segmentedButtonDefaults, boolean z, Function2 function2, Function2 function3, int i, int i2, Composer composer, int i3) {
        segmentedButtonDefaults.Icon(z, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(SegmentedButtonDefaults segmentedButtonDefaults, int i, Composer composer, int i2) {
        segmentedButtonDefaults.ActiveIcon(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: borderStroke-l07J4OM$default, reason: not valid java name */
    public static /* synthetic */ BorderStroke m841borderStrokel07J4OM$default(SegmentedButtonDefaults segmentedButtonDefaults, long j, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = BorderWidth;
        }
        return segmentedButtonDefaults.m842borderStrokel07J4OM(j, f);
    }

    public final void ActiveIcon(Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1273041460);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1273041460, i2, -1, "androidx.compose.material3.SegmentedButtonDefaults.ActiveIcon (SegmentedButton.kt:614)");
            }
            IconKt.m539Iconww6aTOc(Icons.Filled.INSTANCE.getCheck$material3(), (String) null, SizeKt.size-3ABfNKs(Modifier.INSTANCE, IconSize), 0L, composerStartRestartGroup, 48, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n2d
                public final Object invoke(Object obj, Object obj2) {
                    return SegmentedButtonDefaults.b(this.b, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0046  */
    /* JADX WARN: Code duplicated, block: B:28:0x004b  */
    /* JADX WARN: Code duplicated, block: B:30:0x004f  */
    /* JADX WARN: Code duplicated, block: B:32:0x0057  */
    /* JADX WARN: Code duplicated, block: B:33:0x005a  */
    /* JADX WARN: Code duplicated, block: B:37:0x0064  */
    /* JADX WARN: Code duplicated, block: B:38:0x0066  */
    /* JADX WARN: Code duplicated, block: B:41:0x006f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:42:0x0071  */
    /* JADX WARN: Code duplicated, block: B:43:0x0078  */
    /* JADX WARN: Code duplicated, block: B:46:0x007c  */
    /* JADX WARN: Code duplicated, block: B:47:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0085  */
    /* JADX WARN: Code duplicated, block: B:53:0x0090  */
    /* JADX WARN: Code duplicated, block: B:54:0x00db  */
    /* JADX WARN: Code duplicated, block: B:57:0x010b  */
    /* JADX WARN: Code duplicated, block: B:59:0x0110  */
    /* JADX WARN: Code duplicated, block: B:62:0x011b  */
    /* JADX WARN: Code duplicated, block: B:64:? A[RETURN, SYNTHETIC] */
    public final void Icon(final boolean z, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        boolean z2;
        int i3;
        Function2<? super Composer, ? super Integer, Unit> function4;
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        int i5;
        boolean z3;
        final Function2<? super Composer, ? super Integer, Unit> lambda$1863131183$material3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        Composer composerStartRestartGroup = composer.startRestartGroup(-657462570);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            z2 = z;
        } else {
            z2 = z;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changed(z2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i6 = i2 & 2;
        if (i6 == 0) {
            if ((i & 48) == 0) {
                function4 = function2;
                i3 |= composerStartRestartGroup.changedInstance(function4) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i3 & 147) != 146) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    if (i6 != 0) {
                        lambda$1863131183$material3 = ComposableSingletons$SegmentedButtonKt.INSTANCE.getLambda$1863131183$material3();
                    } else {
                        lambda$1863131183$material3 = function4;
                    }
                    if (i4 != 0) {
                        function7 = null;
                    } else {
                        function7 = function5;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-657462570, i3, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon (SegmentedButton.kt:635)");
                    }
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1416314439);
                        AnimatedVisibilityKt.AnimatedVisibility(z2, (Modifier) null, EnterExitTransitionKt.fadeIn$default(MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), 0.0f, 2, (Object) null).plus(EnterExitTransitionKt.scaleIn-L8ZKh-E(MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, TransformOriginKt.TransformOrigin(0.0f, 1.0f))), ExitTransition.Companion.getNone(), (String) null, ComposableLambdaKt.rememberComposableLambda(2059591811, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonDefaults.Icon.1
                            public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer2, int i7) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(2059591811, i7, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon.<anonymous> (SegmentedButton.kt:649)");
                                }
                                lambda$1863131183$material3.invoke(composer2, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer2, Integer num) {
                                invoke(animatedVisibilityScope, composer2, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                        composerStartRestartGroup = composerStartRestartGroup;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1415695803);
                        CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), (String) null, ComposableLambdaKt.rememberComposableLambda(-1785818285, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonDefaults.Icon.2
                            public final void invoke(boolean z4, Composer composer2, int i7) {
                                Function2<Composer, Integer, Unit> function8;
                                if ((i7 & 6) == 0) {
                                    i7 |= composer2.changed(z4) ? 4 : 2;
                                }
                                if (!composer2.shouldExecute((i7 & 19) != 18, i7 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1785818285, i7, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon.<anonymous> (SegmentedButton.kt:657)");
                                }
                                if (z4) {
                                    composer2.startReplaceGroup(1815547362);
                                    function8 = lambda$1863131183$material3;
                                } else {
                                    composer2.startReplaceGroup(1815548036);
                                    function8 = function7;
                                }
                                function8.invoke(composer2, 0);
                                composer2.endReplaceGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer2, Integer num) {
                                invoke(bool.booleanValue(), composer2, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 24576, 10);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function6 = function7;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    lambda$1863131183$material3 = function4;
                    function6 = function5;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: o2d
                        public final Object invoke(Object obj, Object obj2) {
                            return SegmentedButtonDefaults.a(this.b, z, lambda$1863131183$material3, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            function5 = function3;
            if ((i3 & 147) != 146) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i6 != 0) {
                    lambda$1863131183$material3 = ComposableSingletons$SegmentedButtonKt.INSTANCE.getLambda$1863131183$material3();
                } else {
                    lambda$1863131183$material3 = function4;
                }
                if (i4 != 0) {
                    function7 = null;
                } else {
                    function7 = function5;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-657462570, i3, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon (SegmentedButton.kt:635)");
                }
                if (function7 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1416314439);
                    AnimatedVisibilityKt.AnimatedVisibility(z2, (Modifier) null, EnterExitTransitionKt.fadeIn$default(MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), 0.0f, 2, (Object) null).plus(EnterExitTransitionKt.scaleIn-L8ZKh-E(MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, TransformOriginKt.TransformOrigin(0.0f, 1.0f))), ExitTransition.Companion.getNone(), (String) null, ComposableLambdaKt.rememberComposableLambda(2059591811, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonDefaults.Icon.1
                        public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer2, int i7) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2059591811, i7, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon.<anonymous> (SegmentedButton.kt:649)");
                            }
                            lambda$1863131183$material3.invoke(composer2, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer2, Integer num) {
                            invoke(animatedVisibilityScope, composer2, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1415695803);
                    CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), (String) null, ComposableLambdaKt.rememberComposableLambda(-1785818285, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonDefaults.Icon.2
                        public final void invoke(boolean z4, Composer composer2, int i7) {
                            Function2<Composer, Integer, Unit> function8;
                            if ((i7 & 6) == 0) {
                                i7 |= composer2.changed(z4) ? 4 : 2;
                            }
                            if (!composer2.shouldExecute((i7 & 19) != 18, i7 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1785818285, i7, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon.<anonymous> (SegmentedButton.kt:657)");
                            }
                            if (z4) {
                                composer2.startReplaceGroup(1815547362);
                                function8 = lambda$1863131183$material3;
                            } else {
                                composer2.startReplaceGroup(1815548036);
                                function8 = function7;
                            }
                            function8.invoke(composer2, 0);
                            composer2.endReplaceGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer2, Integer num) {
                            invoke(bool.booleanValue(), composer2, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 24576, 10);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function6 = function7;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                lambda$1863131183$material3 = function4;
                function6 = function5;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: o2d
                    public final Object invoke(Object obj, Object obj2) {
                        return SegmentedButtonDefaults.a(this.b, z, lambda$1863131183$material3, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        function4 = function2;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                function5 = function3;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i3 & 147) != 146) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i6 != 0) {
                    lambda$1863131183$material3 = ComposableSingletons$SegmentedButtonKt.INSTANCE.getLambda$1863131183$material3();
                } else {
                    lambda$1863131183$material3 = function4;
                }
                if (i4 != 0) {
                    function7 = null;
                } else {
                    function7 = function5;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-657462570, i3, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon (SegmentedButton.kt:635)");
                }
                if (function7 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1416314439);
                    AnimatedVisibilityKt.AnimatedVisibility(z2, (Modifier) null, EnterExitTransitionKt.fadeIn$default(MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), 0.0f, 2, (Object) null).plus(EnterExitTransitionKt.scaleIn-L8ZKh-E(MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, TransformOriginKt.TransformOrigin(0.0f, 1.0f))), ExitTransition.Companion.getNone(), (String) null, ComposableLambdaKt.rememberComposableLambda(2059591811, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonDefaults.Icon.1
                        public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer2, int i7) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2059591811, i7, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon.<anonymous> (SegmentedButton.kt:649)");
                            }
                            lambda$1863131183$material3.invoke(composer2, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer2, Integer num) {
                            invoke(animatedVisibilityScope, composer2, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1415695803);
                    CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), (String) null, ComposableLambdaKt.rememberComposableLambda(-1785818285, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonDefaults.Icon.2
                        public final void invoke(boolean z4, Composer composer2, int i7) {
                            Function2<Composer, Integer, Unit> function8;
                            if ((i7 & 6) == 0) {
                                i7 |= composer2.changed(z4) ? 4 : 2;
                            }
                            if (!composer2.shouldExecute((i7 & 19) != 18, i7 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1785818285, i7, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon.<anonymous> (SegmentedButton.kt:657)");
                            }
                            if (z4) {
                                composer2.startReplaceGroup(1815547362);
                                function8 = lambda$1863131183$material3;
                            } else {
                                composer2.startReplaceGroup(1815548036);
                                function8 = function7;
                            }
                            function8.invoke(composer2, 0);
                            composer2.endReplaceGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer2, Integer num) {
                            invoke(bool.booleanValue(), composer2, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 24576, 10);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function6 = function7;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                lambda$1863131183$material3 = function4;
                function6 = function5;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: o2d
                    public final Object invoke(Object obj, Object obj2) {
                        return SegmentedButtonDefaults.a(this.b, z, lambda$1863131183$material3, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        function5 = function3;
        if ((i3 & 147) != 146) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            if (i6 != 0) {
                lambda$1863131183$material3 = ComposableSingletons$SegmentedButtonKt.INSTANCE.getLambda$1863131183$material3();
            } else {
                lambda$1863131183$material3 = function4;
            }
            if (i4 != 0) {
                function7 = null;
            } else {
                function7 = function5;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-657462570, i3, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon (SegmentedButton.kt:635)");
            }
            if (function7 == null) {
                composerStartRestartGroup.startReplaceGroup(-1416314439);
                AnimatedVisibilityKt.AnimatedVisibility(z2, (Modifier) null, EnterExitTransitionKt.fadeIn$default(MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), 0.0f, 2, (Object) null).plus(EnterExitTransitionKt.scaleIn-L8ZKh-E(MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, TransformOriginKt.TransformOrigin(0.0f, 1.0f))), ExitTransition.Companion.getNone(), (String) null, ComposableLambdaKt.rememberComposableLambda(2059591811, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonDefaults.Icon.1
                    public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer2, int i7) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2059591811, i7, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon.<anonymous> (SegmentedButton.kt:649)");
                        }
                        lambda$1863131183$material3.invoke(composer2, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer2, Integer num) {
                        invoke(animatedVisibilityScope, composer2, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1415695803);
                CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), (String) null, ComposableLambdaKt.rememberComposableLambda(-1785818285, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonDefaults.Icon.2
                    public final void invoke(boolean z4, Composer composer2, int i7) {
                        Function2<Composer, Integer, Unit> function8;
                        if ((i7 & 6) == 0) {
                            i7 |= composer2.changed(z4) ? 4 : 2;
                        }
                        if (!composer2.shouldExecute((i7 & 19) != 18, i7 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1785818285, i7, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon.<anonymous> (SegmentedButton.kt:657)");
                        }
                        if (z4) {
                            composer2.startReplaceGroup(1815547362);
                            function8 = lambda$1863131183$material3;
                        } else {
                            composer2.startReplaceGroup(1815548036);
                            function8 = function7;
                        }
                        function8.invoke(composer2, 0);
                        composer2.endReplaceGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer2, Integer num) {
                        invoke(bool.booleanValue(), composer2, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 24576, 10);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function6 = function7;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            lambda$1863131183$material3 = function4;
            function6 = function5;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: o2d
                public final Object invoke(Object obj, Object obj2) {
                    return SegmentedButtonDefaults.a(this.b, z, lambda$1863131183$material3, function6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: borderStroke-l07J4OM, reason: not valid java name */
    public final BorderStroke m842borderStrokel07J4OM(long color, float width) {
        return BorderStrokeKt.m12BorderStrokecXLIe8U(width, color);
    }

    public final SegmentedButtonColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(679457321, i, -1, "androidx.compose.material3.SegmentedButtonDefaults.colors (SegmentedButton.kt:494)");
        }
        SegmentedButtonColors defaultSegmentedButtonColors$material3 = getDefaultSegmentedButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultSegmentedButtonColors$material3;
    }

    /* JADX INFO: renamed from: colors-XqyqHi0, reason: not valid java name */
    public final SegmentedButtonColors m843colorsXqyqHi0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, Composer composer, int i, int i2, int i3) {
        long jM3170getUnspecified0d7_KjU = (i3 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i3 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i3 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i3 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i3 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU6 = (i3 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        long jM3170getUnspecified0d7_KjU7 = (i3 & 64) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j7;
        long jM3170getUnspecified0d7_KjU8 = (i3 & 128) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j8;
        long j13 = jM3170getUnspecified0d7_KjU;
        long jM3170getUnspecified0d7_KjU9 = (i3 & 256) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j9;
        long jM3170getUnspecified0d7_KjU10 = (i3 & 512) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j10;
        long jM3170getUnspecified0d7_KjU11 = (i3 & 1024) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j11;
        long jM3170getUnspecified0d7_KjU12 = (i3 & 2048) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j12;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(132526205, i, i2, "androidx.compose.material3.SegmentedButtonDefaults.colors (SegmentedButton.kt:530)");
        }
        SegmentedButtonColors segmentedButtonColorsM828copy2qZNXz8 = getDefaultSegmentedButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m828copy2qZNXz8(j13, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU6, jM3170getUnspecified0d7_KjU7, jM3170getUnspecified0d7_KjU8, jM3170getUnspecified0d7_KjU9, jM3170getUnspecified0d7_KjU10, jM3170getUnspecified0d7_KjU11, jM3170getUnspecified0d7_KjU12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return segmentedButtonColorsM828copy2qZNXz8;
    }

    public final CornerBasedShape getBaseShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1264240381, i, -1, "androidx.compose.material3.SegmentedButtonDefaults.<get-baseShape> (SegmentedButton.kt:578)");
        }
        CornerBasedShape value = ShapesKt.getValue(OutlinedSegmentedButtonTokens.INSTANCE.getShape(), composer, 6);
        value.getClass();
        CornerBasedShape cornerBasedShape = value;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cornerBasedShape;
    }

    /* JADX INFO: renamed from: getBorderWidth-D9Ej5fM, reason: not valid java name */
    public final float m844getBorderWidthD9Ej5fM() {
        return BorderWidth;
    }

    public final PaddingValues getContentPadding() {
        return ContentPadding;
    }

    public final SegmentedButtonColors getDefaultSegmentedButtonColors$material3(ColorScheme colorScheme) {
        SegmentedButtonColors defaultSegmentedButtonColorsCached$material3 = colorScheme.getDefaultSegmentedButtonColorsCached();
        if (defaultSegmentedButtonColorsCached$material3 != null) {
            return defaultSegmentedButtonColorsCached$material3;
        }
        OutlinedSegmentedButtonTokens outlinedSegmentedButtonTokens = OutlinedSegmentedButtonTokens.INSTANCE;
        long jFromToken = ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getSelectedContainerColor());
        long jFromToken2 = ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getSelectedLabelTextColor());
        long jFromToken3 = ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getOutlineColor());
        Color.Companion companion = Color.INSTANCE;
        SegmentedButtonColors segmentedButtonColors = new SegmentedButtonColors(jFromToken, jFromToken2, jFromToken3, companion.m3169getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getUnselectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getOutlineColor()), ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getSelectedContainerColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getDisabledLabelTextColor()), outlinedSegmentedButtonTokens.getDisabledLabelTextOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getOutlineColor()), outlinedSegmentedButtonTokens.getDisabledOutlineOpacity(), 0.0f, 0.0f, 0.0f, 14, null), companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getDisabledLabelTextColor()), outlinedSegmentedButtonTokens.getDisabledLabelTextOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getOutlineColor()), outlinedSegmentedButtonTokens.getDisabledOutlineOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultSegmentedButtonColorsCached$material3(segmentedButtonColors);
        return segmentedButtonColors;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m845getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final Shape itemShape(int i, int i2, CornerBasedShape cornerBasedShape, Composer composer, int i3, int i4) {
        CornerBasedShape cornerBasedShapeEnd$default;
        if ((i4 & 4) != 0) {
            cornerBasedShape = getBaseShape(composer, (i3 >> 9) & 14);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-942072063, i3, -1, "androidx.compose.material3.SegmentedButtonDefaults.itemShape (SegmentedButton.kt:594)");
        }
        if (i2 == 1) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return cornerBasedShape;
        }
        if (i == 0) {
            cornerBasedShapeEnd$default = ShapesKt.start$default(cornerBasedShape, null, 1, null);
        } else {
            cornerBasedShapeEnd$default = i == i2 - 1 ? ShapesKt.end$default(cornerBasedShape, null, 1, null) : RectangleShapeKt.getRectangleShape();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cornerBasedShapeEnd$default;
    }
}
