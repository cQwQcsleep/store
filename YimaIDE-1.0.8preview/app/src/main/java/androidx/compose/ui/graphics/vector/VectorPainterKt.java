package androidx.compose.ui.graphics.vector;

import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0093\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2@\u0010\u000f\u001a<\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0011\u0012\b\b\n\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0011\u0012\b\b\n\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u009d\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u00182@\u0010\u000f\u001a<\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0011\u0012\b\b\n\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0011\u0012\b\b\n\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0015\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001d\u001a&\u0010\u001e\u001a\u00020\u0012*\u00020\u001f2\u0017\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00120!¢\u0006\u0002\b\"H\u0082\b\u001a#\u0010#\u001a\u00020$*\u00020%2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b&\u0010'\u001a'\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b*\u0010+\u001a!\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0004\b.\u0010/\u001aA\u00100\u001a\u00020\u0003*\u00020\u00032\u0006\u0010)\u001a\u00020$2\u0006\u00101\u001a\u00020$2\b\b\u0002\u0010\n\u001a\u00020\u00012\b\u00102\u001a\u0004\u0018\u00010-2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0004\b3\u00104\u001a \u00105\u001a\u00020\u00032\u0006\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020\u001c2\u0006\u00108\u001a\u000209H\u0000\u001a\u0014\u0010:\u001a\u000209*\u0002092\u0006\u0010;\u001a\u00020<H\u0000\u001a+\u0010=\u001a\u00020\u00122\u0006\u0010>\u001a\u00020<2\u0014\b\u0002\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020A0@H\u0007¢\u0006\u0002\u0010B\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"RootGroupName", "", "rememberVectorPainter", "Landroidx/compose/ui/graphics/vector/VectorPainter;", "defaultWidth", "Landroidx/compose/ui/unit/Dp;", "defaultHeight", "viewportWidth", "", "viewportHeight", "name", "tintColor", "Landroidx/compose/ui/graphics/Color;", "tintBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "content", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/graphics/vector/VectorComposable;", "rememberVectorPainter-mlNsNFs", "(FFFFLjava/lang/String;JILkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/vector/VectorPainter;", "autoMirror", "", "rememberVectorPainter-vIP8VLU", "(FFFFLjava/lang/String;JIZLkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/vector/VectorPainter;", "image", "Landroidx/compose/ui/graphics/vector/ImageVector;", "(Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/vector/VectorPainter;", "mirror", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "obtainSizePx", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/Density;", "obtainSizePx-VpY3zN4", "(Landroidx/compose/ui/unit/Density;FF)J", "obtainViewportSize", "defaultSize", "obtainViewportSize-Pq9zytI", "(JFF)J", "createColorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "createColorFilter-xETnrds", "(JI)Landroidx/compose/ui/graphics/ColorFilter;", "configureVectorPainter", "viewportSize", "intrinsicColorFilter", "configureVectorPainter-T4PVSW8", "(Landroidx/compose/ui/graphics/vector/VectorPainter;JJLjava/lang/String;Landroidx/compose/ui/graphics/ColorFilter;Z)Landroidx/compose/ui/graphics/vector/VectorPainter;", "createVectorPainterFromImageVector", "density", "imageVector", "root", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "createGroupComponent", "currentGroup", "Landroidx/compose/ui/graphics/vector/VectorGroup;", "RenderVectorGroup", "group", "configs", "", "Landroidx/compose/ui/graphics/vector/VectorConfig;", "(Landroidx/compose/ui/graphics/vector/VectorGroup;Ljava/util/Map;Landroidx/compose/runtime/Composer;II)V", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class VectorPainterKt {
    public static final String RootGroupName = "VectorRootGroup";

    /* JADX WARN: Code duplicated, block: B:23:0x0040  */
    /* JADX WARN: Code duplicated, block: B:24:0x0042  */
    /* JADX WARN: Code duplicated, block: B:27:0x004b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:29:0x0052  */
    /* JADX WARN: Code duplicated, block: B:32:0x0059  */
    /* JADX WARN: Code duplicated, block: B:36:0x0069  */
    /* JADX WARN: Code duplicated, block: B:38:0x0073  */
    /* JADX WARN: Code duplicated, block: B:40:0x0087  */
    /* JADX WARN: Code duplicated, block: B:43:0x0174  */
    /* JADX WARN: Code duplicated, block: B:45:0x017c  */
    /* JADX WARN: Code duplicated, block: B:47:0x0191  */
    /* JADX WARN: Code duplicated, block: B:50:0x025d  */
    /* JADX WARN: Code duplicated, block: B:53:0x026b  */
    /* JADX WARN: Code duplicated, block: B:54:0x026f  */
    /* JADX WARN: Code duplicated, block: B:57:0x0279  */
    /* JADX WARN: Code duplicated, block: B:62:? A[RETURN, SYNTHETIC] */
    public static final void RenderVectorGroup(final VectorGroup vectorGroup, Map<String, ? extends VectorConfig> map, Composer composer, final int i, final int i2) {
        Map<String, ? extends VectorConfig> map2;
        boolean z;
        final Map<String, ? extends VectorConfig> map3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Map<String, ? extends VectorConfig> mapEmptyMap;
        Iterator<VectorNode> it;
        final VectorNode next;
        final Map<String, ? extends VectorConfig> map4;
        VectorConfig vectorConfig;
        VectorConfig vectorConfig2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-446179233);
        int i3 = (i & 6) == 0 ? (composerStartRestartGroup.changed(vectorGroup) ? 4 : 2) | i : i;
        int i4 = i2 & 2;
        if (i4 == 0) {
            if ((i & 48) == 0) {
                map2 = map;
                i3 |= composerStartRestartGroup.changedInstance(map2) ? 32 : 16;
            }
            if ((i3 & 19) != 18) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i4 != 0) {
                    mapEmptyMap = MapsKt.emptyMap();
                } else {
                    mapEmptyMap = map2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-446179233, i3, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup (VectorPainter.kt:423)");
                }
                for (it = vectorGroup.iterator(); it.hasNext(); it = it) {
                    next = it.next();
                    if (next instanceof VectorPath) {
                        composerStartRestartGroup.startReplaceGroup(798455915);
                        VectorPath vectorPath = (VectorPath) next;
                        vectorConfig2 = mapEmptyMap.get(vectorPath.getName());
                        if (vectorConfig2 == null) {
                            vectorConfig2 = new VectorConfig() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$config$1
                            };
                        }
                        Composer composer2 = composerStartRestartGroup;
                        map4 = mapEmptyMap;
                        VectorComposeKt.m3880Path9cdaXJ4((List) vectorConfig2.getOrDefault(VectorProperty.PathData.INSTANCE, vectorPath.getPathData()), vectorPath.getPathFillType(), vectorPath.getName(), (Brush) vectorConfig2.getOrDefault(VectorProperty.Fill.INSTANCE, vectorPath.getFill()), ((Number) vectorConfig2.getOrDefault(VectorProperty.FillAlpha.INSTANCE, Float.valueOf(vectorPath.getFillAlpha()))).floatValue(), (Brush) vectorConfig2.getOrDefault(VectorProperty.Stroke.INSTANCE, vectorPath.getStroke()), ((Number) vectorConfig2.getOrDefault(VectorProperty.StrokeAlpha.INSTANCE, Float.valueOf(vectorPath.getStrokeAlpha()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.StrokeLineWidth.INSTANCE, Float.valueOf(vectorPath.getStrokeLineWidth()))).floatValue(), vectorPath.getStrokeLineCap(), vectorPath.getStrokeLineJoin(), vectorPath.getStrokeLineMiter(), ((Number) vectorConfig2.getOrDefault(VectorProperty.TrimPathStart.INSTANCE, Float.valueOf(vectorPath.getTrimPathStart()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.TrimPathEnd.INSTANCE, Float.valueOf(vectorPath.getTrimPathEnd()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.TrimPathOffset.INSTANCE, Float.valueOf(vectorPath.getTrimPathOffset()))).floatValue(), composer2, 0, 0, 0);
                        composerStartRestartGroup = composer2;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        map4 = mapEmptyMap;
                        if (next instanceof VectorGroup) {
                            composerStartRestartGroup.startReplaceGroup(799870476);
                            VectorGroup vectorGroup2 = (VectorGroup) next;
                            vectorConfig = map4.get(vectorGroup2.getName());
                            if (vectorConfig == null) {
                                vectorConfig = new VectorConfig() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$config$2
                                };
                            }
                            VectorComposeKt.Group(vectorGroup2.getName(), ((Number) vectorConfig.getOrDefault(VectorProperty.Rotation.INSTANCE, Float.valueOf(vectorGroup2.getRotation()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.PivotX.INSTANCE, Float.valueOf(vectorGroup2.getPivotX()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.PivotY.INSTANCE, Float.valueOf(vectorGroup2.getPivotY()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.ScaleX.INSTANCE, Float.valueOf(vectorGroup2.getScaleX()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.ScaleY.INSTANCE, Float.valueOf(vectorGroup2.getScaleY()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.TranslateX.INSTANCE, Float.valueOf(vectorGroup2.getTranslationX()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.TranslateY.INSTANCE, Float.valueOf(vectorGroup2.getTranslationY()))).floatValue(), (List) vectorConfig.getOrDefault(VectorProperty.PathData.INSTANCE, vectorGroup2.getClipPathData()), ComposableLambdaKt.rememberComposableLambda(1450046638, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt.RenderVectorGroup.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                public final void invoke(Composer composer3, int i5) {
                                    if (!composer3.shouldExecute((i5 & 3) != 2, i5 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1450046638, i5, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup.<anonymous> (VectorPainter.kt:463)");
                                    }
                                    VectorPainterKt.RenderVectorGroup((VectorGroup) next, map4, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, 805306368, 0);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(782476035);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    mapEmptyMap = map4;
                }
                map3 = mapEmptyMap;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                map3 = map2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt.RenderVectorGroup.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer3, int i5) {
                        VectorPainterKt.RenderVectorGroup(vectorGroup, map3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= 48;
        map2 = map;
        if ((i3 & 19) != 18) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i4 != 0) {
                mapEmptyMap = MapsKt.emptyMap();
            } else {
                mapEmptyMap = map2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-446179233, i3, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup (VectorPainter.kt:423)");
            }
            while (it.hasNext()) {
                next = it.next();
                if (next instanceof VectorPath) {
                    composerStartRestartGroup.startReplaceGroup(798455915);
                    VectorPath vectorPath2 = (VectorPath) next;
                    vectorConfig2 = mapEmptyMap.get(vectorPath2.getName());
                    if (vectorConfig2 == null) {
                        vectorConfig2 = new VectorConfig() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$config$1
                        };
                    }
                    Composer composer3 = composerStartRestartGroup;
                    map4 = mapEmptyMap;
                    VectorComposeKt.m3880Path9cdaXJ4((List) vectorConfig2.getOrDefault(VectorProperty.PathData.INSTANCE, vectorPath2.getPathData()), vectorPath2.getPathFillType(), vectorPath2.getName(), (Brush) vectorConfig2.getOrDefault(VectorProperty.Fill.INSTANCE, vectorPath2.getFill()), ((Number) vectorConfig2.getOrDefault(VectorProperty.FillAlpha.INSTANCE, Float.valueOf(vectorPath2.getFillAlpha()))).floatValue(), (Brush) vectorConfig2.getOrDefault(VectorProperty.Stroke.INSTANCE, vectorPath2.getStroke()), ((Number) vectorConfig2.getOrDefault(VectorProperty.StrokeAlpha.INSTANCE, Float.valueOf(vectorPath2.getStrokeAlpha()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.StrokeLineWidth.INSTANCE, Float.valueOf(vectorPath2.getStrokeLineWidth()))).floatValue(), vectorPath2.getStrokeLineCap(), vectorPath2.getStrokeLineJoin(), vectorPath2.getStrokeLineMiter(), ((Number) vectorConfig2.getOrDefault(VectorProperty.TrimPathStart.INSTANCE, Float.valueOf(vectorPath2.getTrimPathStart()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.TrimPathEnd.INSTANCE, Float.valueOf(vectorPath2.getTrimPathEnd()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.TrimPathOffset.INSTANCE, Float.valueOf(vectorPath2.getTrimPathOffset()))).floatValue(), composer3, 0, 0, 0);
                    composerStartRestartGroup = composer3;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    map4 = mapEmptyMap;
                    if (next instanceof VectorGroup) {
                        composerStartRestartGroup.startReplaceGroup(799870476);
                        VectorGroup vectorGroup3 = (VectorGroup) next;
                        vectorConfig = map4.get(vectorGroup3.getName());
                        if (vectorConfig == null) {
                            vectorConfig = new VectorConfig() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$config$2
                            };
                        }
                        VectorComposeKt.Group(vectorGroup3.getName(), ((Number) vectorConfig.getOrDefault(VectorProperty.Rotation.INSTANCE, Float.valueOf(vectorGroup3.getRotation()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.PivotX.INSTANCE, Float.valueOf(vectorGroup3.getPivotX()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.PivotY.INSTANCE, Float.valueOf(vectorGroup3.getPivotY()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.ScaleX.INSTANCE, Float.valueOf(vectorGroup3.getScaleX()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.ScaleY.INSTANCE, Float.valueOf(vectorGroup3.getScaleY()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.TranslateX.INSTANCE, Float.valueOf(vectorGroup3.getTranslationX()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.TranslateY.INSTANCE, Float.valueOf(vectorGroup3.getTranslationY()))).floatValue(), (List) vectorConfig.getOrDefault(VectorProperty.PathData.INSTANCE, vectorGroup3.getClipPathData()), ComposableLambdaKt.rememberComposableLambda(1450046638, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt.RenderVectorGroup.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            public final void invoke(Composer composer4, int i5) {
                                if (!composer4.shouldExecute((i5 & 3) != 2, i5 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1450046638, i5, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup.<anonymous> (VectorPainter.kt:463)");
                                }
                                VectorPainterKt.RenderVectorGroup((VectorGroup) next, map4, composer4, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, 805306368, 0);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(782476035);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                }
                mapEmptyMap = map4;
            }
            map3 = mapEmptyMap;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            map3 = map2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt.RenderVectorGroup.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                public final void invoke(Composer composer4, int i5) {
                    VectorPainterKt.RenderVectorGroup(vectorGroup, map3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }

    /* JADX INFO: renamed from: configureVectorPainter-T4PVSW8, reason: not valid java name */
    public static final VectorPainter m3894configureVectorPainterT4PVSW8(VectorPainter vectorPainter, long j, long j2, String str, ColorFilter colorFilter, boolean z) {
        vectorPainter.m3891setSizeuvyYCjk$ui(j);
        vectorPainter.setAutoMirror$ui(z);
        vectorPainter.setIntrinsicColorFilter$ui(colorFilter);
        vectorPainter.m3892setViewportSizeuvyYCjk$ui(j2);
        vectorPainter.setName$ui(str);
        return vectorPainter;
    }

    /* JADX INFO: renamed from: configureVectorPainter-T4PVSW8$default, reason: not valid java name */
    public static /* synthetic */ VectorPainter m3895configureVectorPainterT4PVSW8$default(VectorPainter vectorPainter, long j, long j2, String str, ColorFilter colorFilter, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            str = RootGroupName;
        }
        return m3894configureVectorPainterT4PVSW8(vectorPainter, j, j2, str, colorFilter, (i & 16) != 0 ? false : z);
    }

    /* JADX INFO: renamed from: createColorFilter-xETnrds, reason: not valid java name */
    private static final ColorFilter m3896createColorFilterxETnrds(long j, int i) {
        if (j != 16) {
            return ColorFilter.INSTANCE.m3178tintxETnrds(j, i);
        }
        return null;
    }

    public static final GroupComponent createGroupComponent(GroupComponent groupComponent, VectorGroup vectorGroup) {
        int size = vectorGroup.getSize();
        for (int i = 0; i < size; i++) {
            VectorNode vectorNode = vectorGroup.get(i);
            if (vectorNode instanceof VectorPath) {
                PathComponent pathComponent = new PathComponent();
                VectorPath vectorPath = (VectorPath) vectorNode;
                pathComponent.setPathData(vectorPath.getPathData());
                pathComponent.m3872setPathFillTypeoQ8Xj4U(vectorPath.getPathFillType());
                pathComponent.setName(vectorPath.getName());
                pathComponent.setFill(vectorPath.getFill());
                pathComponent.setFillAlpha(vectorPath.getFillAlpha());
                pathComponent.setStroke(vectorPath.getStroke());
                pathComponent.setStrokeAlpha(vectorPath.getStrokeAlpha());
                pathComponent.setStrokeLineWidth(vectorPath.getStrokeLineWidth());
                pathComponent.m3873setStrokeLineCapBeK7IIE(vectorPath.getStrokeLineCap());
                pathComponent.m3874setStrokeLineJoinWw9F2mQ(vectorPath.getStrokeLineJoin());
                pathComponent.setStrokeLineMiter(vectorPath.getStrokeLineMiter());
                pathComponent.setTrimPathStart(vectorPath.getTrimPathStart());
                pathComponent.setTrimPathEnd(vectorPath.getTrimPathEnd());
                pathComponent.setTrimPathOffset(vectorPath.getTrimPathOffset());
                groupComponent.insertAt(i, pathComponent);
            } else if (vectorNode instanceof VectorGroup) {
                GroupComponent groupComponent2 = new GroupComponent();
                VectorGroup vectorGroup2 = (VectorGroup) vectorNode;
                groupComponent2.setName(vectorGroup2.getName());
                groupComponent2.setRotation(vectorGroup2.getRotation());
                groupComponent2.setScaleX(vectorGroup2.getScaleX());
                groupComponent2.setScaleY(vectorGroup2.getScaleY());
                groupComponent2.setTranslationX(vectorGroup2.getTranslationX());
                groupComponent2.setTranslationY(vectorGroup2.getTranslationY());
                groupComponent2.setPivotX(vectorGroup2.getPivotX());
                groupComponent2.setPivotY(vectorGroup2.getPivotY());
                groupComponent2.setClipPathData(vectorGroup2.getClipPathData());
                createGroupComponent(groupComponent2, vectorGroup2);
                groupComponent.insertAt(i, groupComponent2);
            }
        }
        return groupComponent;
    }

    public static final VectorPainter createVectorPainterFromImageVector(Density density, ImageVector imageVector, GroupComponent groupComponent) {
        long jM3897obtainSizePxVpY3zN4 = m3897obtainSizePxVpY3zN4(density, imageVector.getDefaultWidth(), imageVector.getDefaultHeight());
        return m3894configureVectorPainterT4PVSW8(new VectorPainter(groupComponent), jM3897obtainSizePxVpY3zN4, m3898obtainViewportSizePq9zytI(jM3897obtainSizePxVpY3zN4, imageVector.getViewportWidth(), imageVector.getViewportHeight()), imageVector.getName(), m3896createColorFilterxETnrds(imageVector.getTintColor(), imageVector.getTintBlendMode()), imageVector.getAutoMirror());
    }

    private static final void mirror(DrawScope drawScope, Function1<? super DrawScope, Unit> function1) {
        long jMo3707getCenterF1C5BW0 = drawScope.mo3707getCenterF1C5BW0();
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3629getSizeNHjbRc = drawContext.mo3629getSizeNHjbRc();
        drawContext.getCanvas().save();
        try {
            drawContext.getTransform().mo3636scale0AR0LA0(-1.0f, 1.0f, jMo3707getCenterF1C5BW0);
            function1.invoke(drawScope);
        } finally {
            InlineMarker.finallyStart(1);
            drawContext.getCanvas().restore();
            drawContext.mo3630setSizeuvyYCjk(jMo3629getSizeNHjbRc);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX INFO: renamed from: obtainSizePx-VpY3zN4, reason: not valid java name */
    private static final long m3897obtainSizePxVpY3zN4(Density density, float f, float f2) {
        float fMo4557toPx0680j_4 = density.mo4557toPx0680j_4(f);
        float fMo4557toPx0680j_5 = density.mo4557toPx0680j_4(f2);
        return Size.m2949constructorimpl((((long) Float.floatToRawIntBits(fMo4557toPx0680j_4)) << 32) | (((long) Float.floatToRawIntBits(fMo4557toPx0680j_5)) & 4294967295L));
    }

    /* JADX INFO: renamed from: obtainViewportSize-Pq9zytI, reason: not valid java name */
    private static final long m3898obtainViewportSizePq9zytI(long j, float f, float f2) {
        if (Float.isNaN(f)) {
            f = Float.intBitsToFloat((int) (j >> 32));
        }
        if (Float.isNaN(f2)) {
            f2 = Float.intBitsToFloat((int) (j & 4294967295L));
        }
        return Size.m2949constructorimpl((((long) Float.floatToRawIntBits(f)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L));
    }

    public static final VectorPainter rememberVectorPainter(ImageVector imageVector, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1413834416, i, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter (VectorPainter.kt:169)");
        }
        Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        float genId = imageVector.getGenId();
        boolean zChanged = composer.changed((((long) Float.floatToRawIntBits(density.getDensity())) & 4294967295L) | (Float.floatToRawIntBits(genId) << 32));
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            GroupComponent groupComponent = new GroupComponent();
            createGroupComponent(groupComponent, imageVector.getRoot());
            Unit unit = Unit.INSTANCE;
            objRememberedValue = createVectorPainterFromImageVector(density, imageVector, groupComponent);
            composer.updateRememberedValue(objRememberedValue);
        }
        VectorPainter vectorPainter = (VectorPainter) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return vectorPainter;
    }

    @Deprecated(message = "Replace rememberVectorPainter graphicsLayer that consumes the auto mirror flag", replaceWith = @ReplaceWith(expression = "rememberVectorPainter(defaultWidth, defaultHeight, viewportWidth, viewportHeight, name, tintColor, tintBlendMode, false, content)", imports = {"androidx.compose.ui.graphics.vector"}))
    /* JADX INFO: renamed from: rememberVectorPainter-mlNsNFs, reason: not valid java name */
    public static final VectorPainter m3899rememberVectorPaintermlNsNFs(float f, float f2, float f3, float f4, String str, long j, int i, Function4<? super Float, ? super Float, ? super Composer, ? super Integer, Unit> function4, Composer composer, int i2, int i3) {
        float f5 = (i3 & 4) != 0 ? Float.NaN : f3;
        float f6 = (i3 & 8) != 0 ? Float.NaN : f4;
        String str2 = (i3 & 16) != 0 ? RootGroupName : str;
        long jM3170getUnspecified0d7_KjU = (i3 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        int iM3072getSrcIn0nO6VwU = (i3 & 64) != 0 ? BlendMode.INSTANCE.m3072getSrcIn0nO6VwU() : i;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(411310745, i2, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter (VectorPainter.kt:85)");
        }
        VectorPainter vectorPainterM3900rememberVectorPaintervIP8VLU = m3900rememberVectorPaintervIP8VLU(f, f2, f5, f6, str2, jM3170getUnspecified0d7_KjU, iM3072getSrcIn0nO6VwU, false, function4, composer, (i2 & 14) | 12582912 | (i2 & 112) | (i2 & 896) | (i2 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i2) | (458752 & i2) | (3670016 & i2) | ((i2 << 3) & 234881024), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return vectorPainterM3900rememberVectorPaintervIP8VLU;
    }

    /* JADX INFO: renamed from: rememberVectorPainter-vIP8VLU, reason: not valid java name */
    public static final VectorPainter m3900rememberVectorPaintervIP8VLU(float f, float f2, float f3, float f4, String str, long j, int i, boolean z, final Function4<? super Float, ? super Float, ? super Composer, ? super Integer, Unit> function4, Composer composer, int i2, int i3) {
        Object obj;
        float f5 = (i3 & 4) != 0 ? Float.NaN : f3;
        float f6 = (i3 & 8) == 0 ? f4 : Float.NaN;
        String str2 = (i3 & 16) != 0 ? RootGroupName : str;
        long jM3170getUnspecified0d7_KjU = (i3 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        int iM3072getSrcIn0nO6VwU = (i3 & 64) != 0 ? BlendMode.INSTANCE.m3072getSrcIn0nO6VwU() : i;
        boolean z2 = (i3 & 128) != 0 ? false : z;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1685735925, i2, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter (VectorPainter.kt:129)");
        }
        long jM3897obtainSizePxVpY3zN4 = m3897obtainSizePxVpY3zN4((Density) composer.consume(CompositionLocalsKt.getLocalDensity()), f, f2);
        final long jM3898obtainViewportSizePq9zytI = m3898obtainViewportSizePq9zytI(jM3897obtainSizePxVpY3zN4, f5, f6);
        boolean z3 = z2;
        boolean z4 = ((((3670016 & i2) ^ 1572864) > 1048576 && composer.changed(iM3072getSrcIn0nO6VwU)) || (i2 & 1572864) == 1048576) | ((((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072 && composer.changed(jM3170getUnspecified0d7_KjU)) || (i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072);
        Object objRememberedValue = composer.rememberedValue();
        if (z4 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = m3896createColorFilterxETnrds(jM3170getUnspecified0d7_KjU, iM3072getSrcIn0nO6VwU);
            composer.updateRememberedValue(objRememberedValue);
        }
        ColorFilter colorFilter = (ColorFilter) objRememberedValue;
        composer.startReplaceGroup(789579543);
        Object objRememberedValue2 = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new VectorPainter(null, 1, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        VectorPainter vectorPainter = (VectorPainter) objRememberedValue2;
        m3894configureVectorPainterT4PVSW8(vectorPainter, jM3897obtainSizePxVpY3zN4, jM3898obtainViewportSizePq9zytI, str2, colorFilter, z3);
        CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composer, 0);
        boolean z5 = ((((234881024 & i2) ^ 100663296) > 67108864 && composer.changed(function4)) || (i2 & 100663296) == 67108864) | ((((i2 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 && composer.changed(f5)) || (i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256) | ((((i2 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) > 2048 && composer.changed(f6)) || (i2 & 3072) == 2048);
        Object objRememberedValue3 = composer.rememberedValue();
        if (z5 || objRememberedValue3 == companion.getEmpty()) {
            obj = objRememberedValue3;
            Composition composition = vectorPainter.getComposition();
            if (composition == null || composition.isDisposed()) {
                composition = CompositionKt.Composition(new VectorApplier(vectorPainter.getVector().getRoot()), compositionContextRememberCompositionContext);
            }
            Composition composition2 = composition;
            composition2.setContent(ComposableLambdaKt.composableLambdaInstance(1749374910, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$rememberVectorPainter$2$composition$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                public final void invoke(Composer composer2, int i4) {
                    if (!composer2.shouldExecute((i4 & 3) != 2, i4 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1749374910, i4, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter.<anonymous>.<anonymous>.<anonymous> (VectorPainter.kt:154)");
                    }
                    function4.invoke(Float.valueOf(Float.intBitsToFloat((int) (jM3898obtainViewportSizePq9zytI >> 32))), Float.valueOf(Float.intBitsToFloat((int) (jM3898obtainViewportSizePq9zytI & 4294967295L))), composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    invoke((Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }
            }));
            composer.updateRememberedValue(composition2);
            obj = composition2;
        }
        obj = objRememberedValue3;
        final Composition composition3 = (Composition) obj;
        vectorPainter.setComposition$ui(composition3);
        boolean zChangedInstance = composer.changedInstance(composition3);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$rememberVectorPainter$2$1$1
                {
                    super(1);
                }

                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                    final Composition composition4 = composition3;
                    return new DisposableEffectResult() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$rememberVectorPainter$2$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            composition4.dispose();
                        }
                    };
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        EffectsKt.DisposableEffect(vectorPainter, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue4, composer, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return vectorPainter;
    }
}
