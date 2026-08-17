package androidx.compose.foundation;

import androidx.compose.foundation.BorderModifierNode;
import androidx.compose.ui.draw.CacheDrawModifierNode;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.PathOperation;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ,\u0010%\u001a\u00020&*\u00020'2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020,H\u0002JC\u0010-\u001a\u00020&*\u00020'2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010(\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020,H\u0002¢\u0006\u0004\b3\u00104J\f\u00105\u001a\u000206*\u000207H\u0016R\u0014\u0010\u000b\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Landroidx/compose/foundation/BorderModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "widthParameter", "Landroidx/compose/ui/unit/Dp;", "brushParameter", "Landroidx/compose/ui/graphics/Brush;", "shapeParameter", "Landroidx/compose/ui/graphics/Shape;", "<init>", "(FLandroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Shape;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "shouldAutoInvalidate", "", "getShouldAutoInvalidate", "()Z", "isImportantForBounds", "borderCache", "Landroidx/compose/foundation/BorderCache;", "value", "width", "getWidth-D9Ej5fM", "()F", "setWidth-0680j_4", "(F)V", "F", "brush", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "setBrush", "(Landroidx/compose/ui/graphics/Brush;)V", "shape", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/graphics/Shape;)V", "drawWithCacheModifierNode", "Landroidx/compose/ui/draw/CacheDrawModifierNode;", "drawGenericBorder", "Landroidx/compose/ui/draw/DrawResult;", "Landroidx/compose/ui/draw/CacheDrawScope;", "outline", "Landroidx/compose/ui/graphics/Outline$Generic;", "fillArea", "strokeWidth", "", "drawRoundRectBorder", "Landroidx/compose/ui/graphics/Outline$Rounded;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "borderSize", "Landroidx/compose/ui/geometry/Size;", "drawRoundRectBorder-JqoCqck", "(Landroidx/compose/ui/draw/CacheDrawScope;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Outline$Rounded;JJZF)Landroidx/compose/ui/draw/DrawResult;", "applySemantics", "", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BorderModifierNode extends DelegatingNode implements SemanticsModifierNode {
    public static final int $stable = 8;
    private BorderCache borderCache;
    private Brush brush;
    private final CacheDrawModifierNode drawWithCacheModifierNode;
    private final boolean isImportantForBounds;
    private Shape shape;
    private final boolean shouldAutoInvalidate;
    private float width;

    private BorderModifierNode(float f, Brush brush, Shape shape) {
        this.width = f;
        this.brush = brush;
        this.shape = shape;
        this.drawWithCacheModifierNode = delegate(DrawModifierKt.CacheDrawModifierNode(new Function1() { // from class: xy0
            public final Object invoke(Object obj) {
                return BorderModifierNode.b(this.b, (CacheDrawScope) obj);
            }
        }));
    }

    public static Unit a(Path path, Brush brush, ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        DrawScope.drawPath-GBMwjPU$default(contentDrawScope, path, brush, 0.0f, (DrawStyle) null, (ColorFilter) null, 0, 60, (Object) null);
        return Unit.INSTANCE;
    }

    public static DrawResult b(BorderModifierNode borderModifierNode, CacheDrawScope cacheDrawScope) {
        if (cacheDrawScope.toPx-0680j_4(borderModifierNode.width) < 0.0f || Size.getMinDimension-impl(cacheDrawScope.getSize-NH-jbRc()) <= 0.0f) {
            return BorderKt.drawContentWithoutBorder(cacheDrawScope);
        }
        float fMin = Math.min(Dp.equals-impl0(borderModifierNode.width, Dp.Companion.getHairline-D9Ej5fM()) ? 1.0f : (float) Math.ceil(cacheDrawScope.toPx-0680j_4(borderModifierNode.width)), (float) Math.ceil(Size.getMinDimension-impl(cacheDrawScope.getSize-NH-jbRc()) / 2.0f));
        float f = fMin / 2.0f;
        long j = Offset.constructor-impl((((long) Float.floatToRawIntBits(f)) & 4294967295L) | (((long) Float.floatToRawIntBits(f)) << 32));
        long j2 = Size.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (cacheDrawScope.getSize-NH-jbRc() & 4294967295L)) - fMin)) & 4294967295L) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (cacheDrawScope.getSize-NH-jbRc() >> 32)) - fMin)) << 32));
        boolean z = 2.0f * fMin > Size.getMinDimension-impl(cacheDrawScope.getSize-NH-jbRc());
        Outline outline = borderModifierNode.shape.createOutline-Pq9zytI(cacheDrawScope.getSize-NH-jbRc(), cacheDrawScope.getLayoutDirection(), cacheDrawScope);
        if (outline instanceof Outline.Generic) {
            return borderModifierNode.drawGenericBorder(cacheDrawScope, borderModifierNode.brush, (Outline.Generic) outline, z, fMin);
        }
        if (outline instanceof Outline.Rounded) {
            return borderModifierNode.m339drawRoundRectBorderJqoCqck(cacheDrawScope, borderModifierNode.brush, (Outline.Rounded) outline, j, j2, z, fMin);
        }
        if (outline instanceof Outline.Rectangle) {
            return BorderKt.m337drawRectBorderNsqcLGU(cacheDrawScope, borderModifierNode.brush, j, j2, z, fMin);
        }
        bu8.a();
        return null;
    }

    public static Unit c(Rect rect, Ref.ObjectRef objectRef, long j, ColorFilter colorFilter, ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        float left = rect.getLeft();
        float top = rect.getTop();
        contentDrawScope.getDrawContext().getTransform().translate(left, top);
        try {
            DrawScope.drawImage-AZ2fEMs$default(contentDrawScope, (ImageBitmap) objectRef.element, 0L, j, 0L, 0L, 0.0f, (DrawStyle) null, colorFilter, 0, 0, 890, (Object) null);
            return Unit.INSTANCE;
        } finally {
            contentDrawScope.getDrawContext().getTransform().translate(-left, -top);
        }
    }

    public static Unit d(boolean z, Brush brush, long j, float f, float f2, long j2, long j3, Stroke stroke, ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        if (z) {
            DrawScope.drawRoundRect-ZuiqVtQ$default(contentDrawScope, brush, 0L, 0L, j, 0.0f, (DrawStyle) null, (ColorFilter) null, 0, 246, (Object) null);
        } else if (Float.intBitsToFloat((int) (j >> 32)) < f) {
            float fIntBitsToFloat = Float.intBitsToFloat((int) (contentDrawScope.getSize-NH-jbRc() >> 32)) - f2;
            float fIntBitsToFloat2 = Float.intBitsToFloat((int) (contentDrawScope.getSize-NH-jbRc() & 4294967295L)) - f2;
            int i = ClipOp.Companion.getDifference-rtfAjoo();
            DrawContext drawContext = contentDrawScope.getDrawContext();
            long j4 = drawContext.getSize-NH-jbRc();
            drawContext.getCanvas().save();
            try {
                drawContext.getTransform().clipRect-N_I0leg(f2, f2, fIntBitsToFloat, fIntBitsToFloat2, i);
                DrawScope.drawRoundRect-ZuiqVtQ$default(contentDrawScope, brush, 0L, 0L, j, 0.0f, (DrawStyle) null, (ColorFilter) null, 0, 246, (Object) null);
            } finally {
                drawContext.getCanvas().restore();
                drawContext.setSize-uvyYCjk(j4);
            }
        } else {
            DrawScope.drawRoundRect-ZuiqVtQ$default(contentDrawScope, brush, j2, j3, BorderKt.m338shrinkKibmq7A(j, f), 0.0f, stroke, (ColorFilter) null, 0, 208, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00f9  */
    private final DrawResult drawGenericBorder(CacheDrawScope cacheDrawScope, final Brush brush, final Outline.Generic generic, boolean z, float f) throws Throwable {
        int i;
        ColorFilter colorFilter;
        boolean z2;
        float f2;
        float f3;
        DrawContext drawContext;
        long j;
        if (z) {
            return cacheDrawScope.onDrawWithContent(new Function1() { // from class: vy0
                public final Object invoke(Object obj) {
                    return BorderModifierNode.e(generic, brush, (ContentDrawScope) obj);
                }
            });
        }
        if (brush instanceof SolidColor) {
            i = ImageBitmapConfig.Companion.getAlpha8-_sVssgQ();
            colorFilter = ColorFilter.Companion.tint-xETnrds$default(ColorFilter.Companion, Color.copy-wmQWz5c$default(((SolidColor) brush).getValue-0d7_KjU(), 1.0f, 0.0f, 0.0f, 0.0f, 14, (Object) null), 0, 2, (Object) null);
        } else {
            i = ImageBitmapConfig.Companion.getArgb8888-_sVssgQ();
            colorFilter = null;
        }
        int i2 = i;
        final Rect bounds = generic.getPath().getBounds();
        if (this.borderCache == null) {
            this.borderCache = new BorderCache(null, null, null, null, 15, null);
        }
        BorderCache borderCache = this.borderCache;
        borderCache.getClass();
        Path pathObtainPath = borderCache.obtainPath();
        pathObtainPath.reset();
        Path.addRect$default(pathObtainPath, bounds, (Path.Direction) null, 2, (Object) null);
        pathObtainPath.op-N5in7k0(pathObtainPath, generic.getPath(), PathOperation.Companion.getDifference-b3I0S0c());
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final long j2 = IntSize.constructor-impl((((long) ((int) Math.ceil(bounds.getBottom() - bounds.getTop()))) & 4294967295L) | (((long) ((int) Math.ceil(bounds.getRight() - bounds.getLeft()))) << 32));
        BorderCache borderCache2 = this.borderCache;
        borderCache2.getClass();
        ImageBitmap imageBitmap = borderCache2.imageBitmap;
        Canvas Canvas = borderCache2.canvas;
        ImageBitmapConfig imageBitmapConfig = imageBitmap != null ? ImageBitmapConfig.box-impl(imageBitmap.getConfig-_sVssgQ()) : null;
        if (!(imageBitmapConfig == null ? false : ImageBitmapConfig.equals-impl0(imageBitmapConfig.unbox-impl(), ImageBitmapConfig.Companion.getArgb8888-_sVssgQ()))) {
            z2 = ImageBitmapConfig.equals-impl(i2, imageBitmap != null ? ImageBitmapConfig.box-impl(imageBitmap.getConfig-_sVssgQ()) : null);
        }
        if (imageBitmap == null || Canvas == null || Float.intBitsToFloat((int) (cacheDrawScope.getSize-NH-jbRc() >> 32)) > imageBitmap.getWidth() || Float.intBitsToFloat((int) (cacheDrawScope.getSize-NH-jbRc() & 4294967295L)) > imageBitmap.getHeight() || !z2) {
            imageBitmap = ImageBitmapKt.ImageBitmap-x__-hDU$default((int) (j2 >> 32), (int) (j2 & 4294967295L), i2, false, (ColorSpace) null, 24, (Object) null);
            borderCache2.imageBitmap = imageBitmap;
            Canvas = androidx.compose.ui.graphics.CanvasKt.Canvas(imageBitmap);
            borderCache2.canvas = Canvas;
        }
        ImageBitmap imageBitmap2 = imageBitmap;
        Canvas canvas = Canvas;
        CanvasDrawScope canvasDrawScope = borderCache2.canvasDrawScope;
        if (canvasDrawScope == null) {
            canvasDrawScope = new CanvasDrawScope();
            borderCache2.canvasDrawScope = canvasDrawScope;
        }
        CanvasDrawScope canvasDrawScope2 = canvasDrawScope;
        long j3 = IntSizeKt.toSize-ozmzZPI(j2);
        LayoutDirection layoutDirection = cacheDrawScope.getLayoutDirection();
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope2.getDrawParams();
        Density densityComponent1 = drawParams.component1();
        LayoutDirection layoutDirectionComponent2 = drawParams.component2();
        Canvas canvasComponent3 = drawParams.component3();
        long j4 = drawParams.component4-NH-jbRc();
        CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope2.getDrawParams();
        drawParams2.setDensity(cacheDrawScope);
        drawParams2.setLayoutDirection(layoutDirection);
        drawParams2.setCanvas(canvas);
        drawParams2.setSize-uvyYCjk(j3);
        canvas.save();
        long j5 = Color.Companion.getBlack-0d7_KjU();
        BlendMode.Companion companion = BlendMode.Companion;
        DrawScope.drawRect-n-J9OG0$default(canvasDrawScope2, j5, 0L, j3, 0.0f, (DrawStyle) null, (ColorFilter) null, companion.getClear-0nO6VwU(), 58, (Object) null);
        float f4 = -bounds.getLeft();
        float f5 = -bounds.getTop();
        canvasDrawScope2.getDrawContext().getTransform().translate(f4, f5);
        try {
            f3 = f5;
            f2 = f4;
            final ColorFilter colorFilter2 = colorFilter;
            try {
                DrawScope.drawPath-GBMwjPU$default(canvasDrawScope2, generic.getPath(), brush, 0.0f, new Stroke(2.0f * f, 0.0f, 0, 0, (PathEffect) null, 30, (DefaultConstructorMarker) null), (ColorFilter) null, 0, 52, (Object) null);
                float fIntBitsToFloat = (Float.intBitsToFloat((int) (canvasDrawScope2.getSize-NH-jbRc() >> 32)) + 1.0f) / Float.intBitsToFloat((int) (canvasDrawScope2.getSize-NH-jbRc() >> 32));
                float fIntBitsToFloat2 = (Float.intBitsToFloat((int) (canvasDrawScope2.getSize-NH-jbRc() & 4294967295L)) + 1.0f) / Float.intBitsToFloat((int) (canvasDrawScope2.getSize-NH-jbRc() & 4294967295L));
                long j6 = canvasDrawScope2.getCenter-F1C5BW0();
                DrawContext drawContext2 = canvasDrawScope2.getDrawContext();
                long j7 = drawContext2.getSize-NH-jbRc();
                drawContext2.getCanvas().save();
                try {
                    drawContext2.getTransform().scale-0AR0LA0(fIntBitsToFloat, fIntBitsToFloat2, j6);
                    j = j7;
                    try {
                        DrawScope.drawPath-GBMwjPU$default(canvasDrawScope2, pathObtainPath, brush, 0.0f, (DrawStyle) null, (ColorFilter) null, companion.getClear-0nO6VwU(), 28, (Object) null);
                        drawContext2.getCanvas().restore();
                        drawContext2.setSize-uvyYCjk(j);
                        canvasDrawScope2.getDrawContext().getTransform().translate(-f2, -f3);
                        canvas.restore();
                        CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope2.getDrawParams();
                        drawParams3.setDensity(densityComponent1);
                        drawParams3.setLayoutDirection(layoutDirectionComponent2);
                        drawParams3.setCanvas(canvasComponent3);
                        drawParams3.setSize-uvyYCjk(j4);
                        imageBitmap2.prepareToDraw();
                        objectRef.element = imageBitmap2;
                        return cacheDrawScope.onDrawWithContent(new Function1() { // from class: wy0
                            public final Object invoke(Object obj) {
                                return BorderModifierNode.c(bounds, objectRef, j2, colorFilter2, (ContentDrawScope) obj);
                            }
                        });
                    } catch (Throwable th) {
                        th = th;
                        drawContext = drawContext2;
                        drawContext.getCanvas().restore();
                        drawContext.setSize-uvyYCjk(j);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    drawContext = drawContext2;
                    j = j7;
                }
            } catch (Throwable th3) {
                th = th3;
                canvasDrawScope2.getDrawContext().getTransform().translate(-f2, -f3);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            f2 = f4;
            f3 = f5;
        }
    }

    /* JADX INFO: renamed from: drawRoundRectBorder-JqoCqck, reason: not valid java name */
    private final DrawResult m339drawRoundRectBorderJqoCqck(CacheDrawScope cacheDrawScope, final Brush brush, Outline.Rounded rounded, final long j, final long j2, final boolean z, final float f) {
        if (RoundRectKt.isSimple(rounded.getRoundRect())) {
            final long j3 = rounded.getRoundRect().getTopLeftCornerRadius-kKHJgLs();
            final float f2 = f / 2.0f;
            final Stroke stroke = new Stroke(f, 0.0f, 0, 0, (PathEffect) null, 30, (DefaultConstructorMarker) null);
            return cacheDrawScope.onDrawWithContent(new Function1() { // from class: ty0
                public final Object invoke(Object obj) {
                    return BorderModifierNode.d(z, brush, j3, f2, f, j, j2, stroke, (ContentDrawScope) obj);
                }
            });
        }
        if (this.borderCache == null) {
            this.borderCache = new BorderCache(null, null, null, null, 15, null);
        }
        BorderCache borderCache = this.borderCache;
        borderCache.getClass();
        final Path pathCreateRoundRectPath = BorderKt.createRoundRectPath(borderCache.obtainPath(), rounded.getRoundRect(), f, z);
        return cacheDrawScope.onDrawWithContent(new Function1() { // from class: uy0
            public final Object invoke(Object obj) {
                return BorderModifierNode.a(pathCreateRoundRectPath, brush, (ContentDrawScope) obj);
            }
        });
    }

    public static Unit e(Outline.Generic generic, Brush brush, ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        DrawScope.drawPath-GBMwjPU$default(contentDrawScope, generic.getPath(), brush, 0.0f, (DrawStyle) null, (ColorFilter) null, 0, 60, (Object) null);
        return Unit.INSTANCE;
    }

    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setShape(semanticsPropertyReceiver, this.shape);
    }

    public final Brush getBrush() {
        return this.brush;
    }

    public final Shape getShape() {
        return this.shape;
    }

    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    /* JADX INFO: renamed from: getWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: isImportantForBounds, reason: from getter */
    public boolean getIsImportantForBounds() {
        return this.isImportantForBounds;
    }

    public final void setBrush(Brush brush) {
        if (Intrinsics.areEqual(this.brush, brush)) {
            return;
        }
        this.brush = brush;
        this.drawWithCacheModifierNode.invalidateDrawCache();
    }

    public final void setShape(Shape shape) {
        if (Intrinsics.areEqual(this.shape, shape)) {
            return;
        }
        this.shape = shape;
        this.drawWithCacheModifierNode.invalidateDrawCache();
        SemanticsModifierNodeKt.invalidateSemantics(this);
    }

    /* JADX INFO: renamed from: setWidth-0680j_4, reason: not valid java name */
    public final void m341setWidth0680j_4(float f) {
        if (Dp.equals-impl0(this.width, f)) {
            return;
        }
        this.width = f;
        this.drawWithCacheModifierNode.invalidateDrawCache();
    }

    public /* synthetic */ BorderModifierNode(float f, Brush brush, Shape shape, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, brush, shape);
    }
}
