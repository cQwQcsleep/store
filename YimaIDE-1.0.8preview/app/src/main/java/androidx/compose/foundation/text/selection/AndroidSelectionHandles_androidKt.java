package androidx.compose.foundation.text.selection;

import androidx.collection.SieveCacheKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.AbsoluteAlignment;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.compose.ui.window.SecureFlagPolicy;
import com.intellij.util.io.IOUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aI\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a+\u0010\u0011\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\u0015\u001a\"\u0010\u0016\u001a\u00020\u000e*\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0000\u001a\u0014\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0000\u001a0\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0011\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b H\u0001¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"SelectionHandle", "", "offsetProvider", "Landroidx/compose/foundation/text/selection/OffsetProvider;", "isStartHandle", "", "direction", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "handlesCrossed", "minTouchTargetSize", "Landroidx/compose/ui/unit/DpSize;", "lineHeight", "", "modifier", "Landroidx/compose/ui/Modifier;", "SelectionHandle-wLIcFTc", "(Landroidx/compose/foundation/text/selection/OffsetProvider;ZLandroidx/compose/ui/text/style/ResolvedTextDirection;ZJFLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "SelectionHandleIcon", "iconVisible", "Lkotlin/Function0;", "isLeft", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "drawSelectionHandle", "createHandleImage", "Landroidx/compose/ui/graphics/ImageBitmap;", "Landroidx/compose/ui/draw/CacheDrawScope;", "radius", "HandlePopup", "positionProvider", "handleReferencePoint", "Landroidx/compose/ui/Alignment;", "content", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/text/selection/OffsetProvider;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidSelectionHandles_androidKt {
    public static final void HandlePopup(final OffsetProvider offsetProvider, final Alignment alignment, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1090171650);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(offsetProvider) : composerStartRestartGroup.changedInstance(offsetProvider) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(alignment) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        boolean z = false;
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1090171650, i2, -1, "androidx.compose.foundation.text.selection.HandlePopup (AndroidSelectionHandles.android.kt:219)");
            }
            boolean z2 = (i2 & 112) == 32;
            if ((i2 & 14) == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changed(offsetProvider))) {
                z = true;
            }
            boolean z3 = z2 | z;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z3 || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new HandlePositionProvider(alignment, offsetProvider);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            AndroidPopup_androidKt.Popup((HandlePositionProvider) objRememberedValue, (Function0) null, new PopupProperties(false, false, false, (SecureFlagPolicy) null, true, false, 15, (DefaultConstructorMarker) null), function2, composerStartRestartGroup, ((i2 << 3) & 7168) | 384, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: q60
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidSelectionHandles_androidKt.c(offsetProvider, alignment, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: SelectionHandle-wLIcFTc, reason: not valid java name */
    public static final void m1735SelectionHandlewLIcFTc(final OffsetProvider offsetProvider, final boolean z, final ResolvedTextDirection resolvedTextDirection, final boolean z2, long j, final float f, final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        long j2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-466280168);
        if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(offsetProvider) : composerStartRestartGroup.changedInstance(offsetProvider) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(resolvedTextDirection.ordinal()) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changed(z2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            j2 = j;
            i3 |= ((i2 & 16) == 0 && composerStartRestartGroup.changed(j2)) ? 16384 : 8192;
        } else {
            j2 = j;
        }
        if ((1572864 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? IOUtil.MiB : 524288;
        }
        if (composerStartRestartGroup.shouldExecute((533651 & i3) != 533650, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                }
            } else if ((i2 & 16) != 0) {
                j2 = DpSize.Companion.getUnspecified-MYxV2XQ();
                i3 &= -57345;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-466280168, i3, -1, "androidx.compose.foundation.text.selection.SelectionHandle (AndroidSelectionHandles.android.kt:65)");
            }
            final boolean zIsLeftSelectionHandle = SelectionHandlesKt.isLeftSelectionHandle(z, resolvedTextDirection, z2);
            AbsoluteAlignment absoluteAlignment = AbsoluteAlignment.INSTANCE;
            Alignment topRight = zIsLeftSelectionHandle ? absoluteAlignment.getTopRight() : absoluteAlignment.getTopLeft();
            int i4 = i3 & 14;
            boolean zChanged = ((i3 & 112) == 32) | (i4 == 4 || ((i3 & 8) != 0 && composerStartRestartGroup.changedInstance(offsetProvider))) | composerStartRestartGroup.changed(zIsLeftSelectionHandle);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: s60
                    public final Object invoke(Object obj) {
                        return AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$0$0(offsetProvider, z, zIsLeftSelectionHandle, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, (Function1) objRememberedValue, 1, (Object) null);
            final ViewConfiguration viewConfiguration = (ViewConfiguration) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalViewConfiguration());
            final long j3 = j2;
            HandlePopup(offsetProvider, topRight, ComposableLambdaKt.rememberComposableLambda(1365123137, true, new Function2() { // from class: t60
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidSelectionHandles_androidKt.j(viewConfiguration, j3, zIsLeftSelectionHandle, modifierSemantics$default, offsetProvider, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, i4 | 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j2 = j3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final long j4 = j2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: u60
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidSelectionHandles_androidKt.g(offsetProvider, z, resolvedTextDirection, z2, j4, f, modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void SelectionHandleIcon(final Modifier modifier, final Function0<Boolean> function0, final boolean z, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2111672474);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2111672474, i2, -1, "androidx.compose.foundation.text.selection.SelectionHandleIcon (AndroidSelectionHandles.android.kt:123)");
            }
            SpacerKt.Spacer(drawSelectionHandle(SizeKt.m985sizeVpY3zN4(modifier, SelectionHandlesKt.getHandleWidth(), SelectionHandlesKt.getHandleHeight()), function0, z), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: w60
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidSelectionHandles_androidKt.d(modifier, function0, z, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionHandle_wLIcFTc$lambda$0$0(OffsetProvider offsetProvider, boolean z, boolean z2, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        long jMo1306provideF1C5BW0 = offsetProvider.mo1306provideF1C5BW0();
        semanticsPropertyReceiver.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo(z ? Handle.SelectionStart : Handle.SelectionEnd, jMo1306provideF1C5BW0, z2 ? SelectionHandleAnchor.Left : SelectionHandleAnchor.Right, (SieveCacheKt.InvalidMapping & jMo1306provideF1C5BW0) != 9205357640488583168L, null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionHandle_wLIcFTc$lambda$1$0(long j, boolean z, Modifier modifier, final OffsetProvider offsetProvider, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1260045569, i, -1, "androidx.compose.foundation.text.selection.SelectionHandle.<anonymous>.<anonymous> (AndroidSelectionHandles.android.kt:86)");
            }
            if (j != 9205357640488583168L) {
                composer.startReplaceGroup(3458246);
                Arrangement.Horizontal right = z ? Arrangement.Absolute.INSTANCE.getRight() : Arrangement.Absolute.INSTANCE.getLeft();
                Modifier modifierM979requiredSizeInqDBjuR0$default = SizeKt.m979requiredSizeInqDBjuR0$default(modifier, DpSize.getWidth-D9Ej5fM(j), DpSize.getHeight-D9Ej5fM(j), 0.0f, 0.0f, 12, null);
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(right, Alignment.Companion.getTop(), composer, 0);
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM979requiredSizeInqDBjuR0$default);
                ComposeUiNode.Companion companion = ComposeUiNode.Companion;
                Function0 constructor = companion.getConstructor();
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
                Updater.set-impl(composer2, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                Updater.set-impl(composer2, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer2, companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer2, modifierMaterializeModifier, companion.getSetModifier());
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                Modifier.Companion companion2 = Modifier.Companion;
                boolean zChangedInstance = composer.changedInstance(offsetProvider);
                Object objRememberedValue = composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: a70
                        public final Object invoke() {
                            return Boolean.valueOf(AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$1$0$0$0$0(offsetProvider));
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                SelectionHandleIcon(companion2, (Function0) objRememberedValue, z, composer, 6);
                composer.endNode();
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(4389176);
                boolean zChangedInstance2 = composer.changedInstance(offsetProvider);
                Object objRememberedValue2 = composer.rememberedValue();
                if (zChangedInstance2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: r60
                        public final Object invoke() {
                            return Boolean.valueOf(AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$1$0$1$0(offsetProvider));
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue2);
                }
                SelectionHandleIcon(modifier, (Function0) objRememberedValue2, z, composer, 0);
                composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SelectionHandle_wLIcFTc$lambda$1$0$0$0$0(OffsetProvider offsetProvider) {
        return (offsetProvider.mo1306provideF1C5BW0() & SieveCacheKt.InvalidMapping) != 9205357640488583168L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SelectionHandle_wLIcFTc$lambda$1$0$1$0(OffsetProvider offsetProvider) {
        return (offsetProvider.mo1306provideF1C5BW0() & SieveCacheKt.InvalidMapping) != 9205357640488583168L;
    }

    public static Unit c(OffsetProvider offsetProvider, Alignment alignment, Function2 function2, int i, Composer composer, int i2) {
        HandlePopup(offsetProvider, alignment, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final ImageBitmap createHandleImage(CacheDrawScope cacheDrawScope, float f) {
        int iCeil = ((int) Math.ceil(f)) * 2;
        HandleImageCache handleImageCache = HandleImageCache.INSTANCE;
        ImageBitmap imageBitmap = handleImageCache.getImageBitmap();
        Canvas canvas = handleImageCache.getCanvas();
        CanvasDrawScope canvasDrawScope = handleImageCache.getCanvasDrawScope();
        if (imageBitmap == null || canvas == null || iCeil > imageBitmap.getWidth() || iCeil > imageBitmap.getHeight()) {
            imageBitmap = ImageBitmapKt.ImageBitmap-x__-hDU$default(iCeil, iCeil, ImageBitmapConfig.Companion.getAlpha8-_sVssgQ(), false, (ColorSpace) null, 24, (Object) null);
            handleImageCache.setImageBitmap(imageBitmap);
            canvas = CanvasKt.Canvas(imageBitmap);
            handleImageCache.setCanvas(canvas);
        }
        ImageBitmap imageBitmap2 = imageBitmap;
        Canvas canvas2 = canvas;
        if (canvasDrawScope == null) {
            canvasDrawScope = new CanvasDrawScope();
            handleImageCache.setCanvasDrawScope(canvasDrawScope);
        }
        CanvasDrawScope canvasDrawScope2 = canvasDrawScope;
        LayoutDirection layoutDirection = cacheDrawScope.getLayoutDirection();
        float width = imageBitmap2.getWidth();
        long j = Size.constructor-impl((((long) Float.floatToRawIntBits(imageBitmap2.getHeight())) & 4294967295L) | (Float.floatToRawIntBits(width) << 32));
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope2.getDrawParams();
        Density densityComponent1 = drawParams.component1();
        LayoutDirection layoutDirectionComponent2 = drawParams.component2();
        Canvas canvasComponent3 = drawParams.component3();
        long j2 = drawParams.component4-NH-jbRc();
        CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope2.getDrawParams();
        drawParams2.setDensity(cacheDrawScope);
        drawParams2.setLayoutDirection(layoutDirection);
        drawParams2.setCanvas(canvas2);
        drawParams2.setSize-uvyYCjk(j);
        canvas2.save();
        DrawScope.drawRect-n-J9OG0$default(canvasDrawScope2, Color.Companion.getBlack-0d7_KjU(), 0L, canvasDrawScope2.getSize-NH-jbRc(), 0.0f, (DrawStyle) null, (ColorFilter) null, BlendMode.Companion.getClear-0nO6VwU(), 58, (Object) null);
        DrawScope.drawRect-n-J9OG0$default(canvasDrawScope2, ColorKt.Color(4278190080L), Offset.Companion.getZero-F1C5BW0(), Size.constructor-impl((((long) Float.floatToRawIntBits(f)) << 32) | (((long) Float.floatToRawIntBits(f)) & 4294967295L)), 0.0f, (DrawStyle) null, (ColorFilter) null, 0, 120, (Object) null);
        DrawScope.drawCircle-VaOC9Bg$default(canvasDrawScope2, ColorKt.Color(4278190080L), f, Offset.constructor-impl((((long) Float.floatToRawIntBits(f)) & 4294967295L) | (Float.floatToRawIntBits(f) << 32)), 0.0f, (DrawStyle) null, (ColorFilter) null, 0, 120, (Object) null);
        canvas2.restore();
        CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope2.getDrawParams();
        drawParams3.setDensity(densityComponent1);
        drawParams3.setLayoutDirection(layoutDirectionComponent2);
        drawParams3.setCanvas(canvasComponent3);
        drawParams3.setSize-uvyYCjk(j2);
        return imageBitmap2;
    }

    public static Unit d(Modifier modifier, Function0 function0, boolean z, int i, Composer composer, int i2) {
        SelectionHandleIcon(modifier, function0, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final Modifier drawSelectionHandle(Modifier modifier, final Function0<Boolean> function0, final boolean z) {
        return ComposedModifierKt.composed$default(modifier, (Function1) null, new Function3() { // from class: x60
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AndroidSelectionHandles_androidKt.f(function0, z, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DrawResult drawSelectionHandle$lambda$0$0$0(long j, final Function0 function0, final boolean z, CacheDrawScope cacheDrawScope) {
        final ImageBitmap imageBitmapCreateHandleImage = createHandleImage(cacheDrawScope, Float.intBitsToFloat((int) (cacheDrawScope.getSize-NH-jbRc() >> 32)) / 2.0f);
        final ColorFilter colorFilter = ColorFilter.Companion.tint-xETnrds$default(ColorFilter.Companion, j, 0, 2, (Object) null);
        return cacheDrawScope.onDrawWithContent(new Function1() { // from class: z60
            public final Object invoke(Object obj) {
                return AndroidSelectionHandles_androidKt.drawSelectionHandle$lambda$0$0$0$0(function0, z, imageBitmapCreateHandleImage, colorFilter, (ContentDrawScope) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit drawSelectionHandle$lambda$0$0$0$0(Function0 function0, boolean z, ImageBitmap imageBitmap, ColorFilter colorFilter, ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        if (!((Boolean) function0.invoke()).booleanValue()) {
            return Unit.INSTANCE;
        }
        if (z) {
            long j = contentDrawScope.getCenter-F1C5BW0();
            DrawContext drawContext = contentDrawScope.getDrawContext();
            long j2 = drawContext.getSize-NH-jbRc();
            drawContext.getCanvas().save();
            try {
                drawContext.getTransform().scale-0AR0LA0(-1.0f, 1.0f, j);
                DrawScope.drawImage-gbVJVH8$default(contentDrawScope, imageBitmap, 0L, 0.0f, (DrawStyle) null, colorFilter, 0, 46, (Object) null);
            } finally {
                drawContext.getCanvas().restore();
                drawContext.setSize-uvyYCjk(j2);
            }
        } else {
            DrawScope.drawImage-gbVJVH8$default(contentDrawScope, imageBitmap, 0L, 0.0f, (DrawStyle) null, colorFilter, 0, 46, (Object) null);
        }
        return Unit.INSTANCE;
    }

    public static Modifier f(final Function0 function0, final boolean z, Modifier modifier, Composer composer, int i) {
        composer.startReplaceGroup(-196777734);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-196777734, i, -1, "androidx.compose.foundation.text.selection.drawSelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:129)");
        }
        final long handleColor = ((TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors())).getHandleColor();
        boolean zChanged = composer.changed(handleColor) | composer.changed(function0) | composer.changed(z);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Function1() { // from class: y60
                public final Object invoke(Object obj) {
                    return AndroidSelectionHandles_androidKt.drawSelectionHandle$lambda$0$0$0(handleColor, function0, z, (CacheDrawScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierDrawWithCache = DrawModifierKt.drawWithCache(modifier, (Function1) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return modifierDrawWithCache;
    }

    public static Unit g(OffsetProvider offsetProvider, boolean z, ResolvedTextDirection resolvedTextDirection, boolean z2, long j, float f, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m1735SelectionHandlewLIcFTc(offsetProvider, z, resolvedTextDirection, z2, j, f, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit j(ViewConfiguration viewConfiguration, final long j, final boolean z, final Modifier modifier, final OffsetProvider offsetProvider, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1365123137, i, -1, "androidx.compose.foundation.text.selection.SelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:85)");
            }
            CompositionLocalKt.CompositionLocalProvider(CompositionLocalsKt.getLocalViewConfiguration().provides(viewConfiguration), ComposableLambdaKt.rememberComposableLambda(1260045569, true, new Function2() { // from class: v60
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$1$0(j, z, modifier, offsetProvider, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
