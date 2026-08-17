package androidx.compose.foundation;

import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ=\u0010\f\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013¢\u0006\u0002\b\u0016H\u0086\b¢\u0006\u0004\b\u0017\u0010\u0018J\u0006\u0010\u0019\u001a\u00020\tJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÂ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÂ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\tHÂ\u0003J9\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/compose/foundation/BorderCache;", "", "imageBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "canvasDrawScope", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "borderPath", "Landroidx/compose/ui/graphics/Path;", "<init>", "(Landroidx/compose/ui/graphics/ImageBitmap;Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;Landroidx/compose/ui/graphics/Path;)V", "drawBorderCache", "Landroidx/compose/ui/draw/CacheDrawScope;", "borderSize", "Landroidx/compose/ui/unit/IntSize;", "config", "Landroidx/compose/ui/graphics/ImageBitmapConfig;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "drawBorderCache-EMwLDEs", "(Landroidx/compose/ui/draw/CacheDrawScope;JILkotlin/jvm/functions/Function1;)Landroidx/compose/ui/graphics/ImageBitmap;", "obtainPath", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class BorderCache {
    private Path borderPath;
    private Canvas canvas;
    private CanvasDrawScope canvasDrawScope;
    private ImageBitmap imageBitmap;

    public /* synthetic */ BorderCache(ImageBitmap imageBitmap, Canvas canvas, CanvasDrawScope canvasDrawScope, Path path, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : imageBitmap, (i & 2) != 0 ? null : canvas, (i & 4) != 0 ? null : canvasDrawScope, (i & 8) != 0 ? null : path);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final ImageBitmap getImageBitmap() {
        return this.imageBitmap;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final Canvas getCanvas() {
        return this.canvas;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    private final CanvasDrawScope getCanvasDrawScope() {
        return this.canvasDrawScope;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    private final Path getBorderPath() {
        return this.borderPath;
    }

    public static /* synthetic */ BorderCache copy$default(BorderCache borderCache, ImageBitmap imageBitmap, Canvas canvas, CanvasDrawScope canvasDrawScope, Path path, int i, Object obj) {
        if ((i & 1) != 0) {
            imageBitmap = borderCache.imageBitmap;
        }
        if ((i & 2) != 0) {
            canvas = borderCache.canvas;
        }
        if ((i & 4) != 0) {
            canvasDrawScope = borderCache.canvasDrawScope;
        }
        if ((i & 8) != 0) {
            path = borderCache.borderPath;
        }
        return borderCache.copy(imageBitmap, canvas, canvasDrawScope, path);
    }

    public final BorderCache copy(ImageBitmap imageBitmap, Canvas canvas, CanvasDrawScope canvasDrawScope, Path borderPath) {
        return new BorderCache(imageBitmap, canvas, canvasDrawScope, borderPath);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0075  */
    /* JADX WARN: Code duplicated, block: B:29:0x0094  */
    /* JADX INFO: renamed from: drawBorderCache-EMwLDEs, reason: not valid java name */
    public final ImageBitmap m331drawBorderCacheEMwLDEs(CacheDrawScope cacheDrawScope, long j, int i, Function1<? super DrawScope, Unit> function1) {
        int i2;
        CanvasDrawScope canvasDrawScope;
        ImageBitmap imageBitmap = this.imageBitmap;
        Canvas Canvas = this.canvas;
        ImageBitmapConfig imageBitmapConfig = imageBitmap != null ? ImageBitmapConfig.box-impl(imageBitmap.getConfig-_sVssgQ()) : null;
        boolean z = false;
        if (!(imageBitmapConfig == null ? false : ImageBitmapConfig.equals-impl0(imageBitmapConfig.unbox-impl(), ImageBitmapConfig.Companion.getArgb8888-_sVssgQ()))) {
            i2 = i;
            if (ImageBitmapConfig.equals-impl(i2, imageBitmap != null ? ImageBitmapConfig.box-impl(imageBitmap.getConfig-_sVssgQ()) : null)) {
            }
            if (imageBitmap != null || Canvas == null || Float.intBitsToFloat((int) (cacheDrawScope.getSize-NH-jbRc() >> 32)) > imageBitmap.getWidth() || Float.intBitsToFloat((int) (cacheDrawScope.getSize-NH-jbRc() & 4294967295L)) > imageBitmap.getHeight() || !z) {
                imageBitmap = ImageBitmapKt.ImageBitmap-x__-hDU$default((int) (j >> 32), (int) (j & 4294967295L), i2, false, (ColorSpace) null, 24, (Object) null);
                this.imageBitmap = imageBitmap;
                Canvas = androidx.compose.ui.graphics.CanvasKt.Canvas(imageBitmap);
                this.canvas = Canvas;
            }
            canvasDrawScope = this.canvasDrawScope;
            if (canvasDrawScope == null) {
                canvasDrawScope = new CanvasDrawScope();
                this.canvasDrawScope = canvasDrawScope;
            }
            CanvasDrawScope canvasDrawScope2 = canvasDrawScope;
            long j2 = IntSizeKt.toSize-ozmzZPI(j);
            LayoutDirection layoutDirection = cacheDrawScope.getLayoutDirection();
            CanvasDrawScope.DrawParams drawParams = canvasDrawScope2.getDrawParams();
            Density densityComponent1 = drawParams.component1();
            LayoutDirection layoutDirectionComponent2 = drawParams.component2();
            Canvas canvasComponent3 = drawParams.component3();
            long j3 = drawParams.component4-NH-jbRc();
            CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope2.getDrawParams();
            drawParams2.setDensity(cacheDrawScope);
            drawParams2.setLayoutDirection(layoutDirection);
            drawParams2.setCanvas(Canvas);
            drawParams2.setSize-uvyYCjk(j2);
            Canvas.save();
            ImageBitmap imageBitmap2 = imageBitmap;
            DrawScope.drawRect-n-J9OG0$default(canvasDrawScope2, Color.Companion.getBlack-0d7_KjU(), 0L, j2, 0.0f, (DrawStyle) null, (ColorFilter) null, BlendMode.Companion.getClear-0nO6VwU(), 58, (Object) null);
            function1.invoke(canvasDrawScope2);
            Canvas.restore();
            CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope2.getDrawParams();
            drawParams3.setDensity(densityComponent1);
            drawParams3.setLayoutDirection(layoutDirectionComponent2);
            drawParams3.setCanvas(canvasComponent3);
            drawParams3.setSize-uvyYCjk(j3);
            imageBitmap2.prepareToDraw();
            return imageBitmap2;
        }
        i2 = i;
        z = true;
        if (imageBitmap != null) {
            imageBitmap = ImageBitmapKt.ImageBitmap-x__-hDU$default((int) (j >> 32), (int) (j & 4294967295L), i2, false, (ColorSpace) null, 24, (Object) null);
            this.imageBitmap = imageBitmap;
            Canvas = androidx.compose.ui.graphics.CanvasKt.Canvas(imageBitmap);
            this.canvas = Canvas;
        } else {
            imageBitmap = ImageBitmapKt.ImageBitmap-x__-hDU$default((int) (j >> 32), (int) (j & 4294967295L), i2, false, (ColorSpace) null, 24, (Object) null);
            this.imageBitmap = imageBitmap;
            Canvas = androidx.compose.ui.graphics.CanvasKt.Canvas(imageBitmap);
            this.canvas = Canvas;
        }
        canvasDrawScope = this.canvasDrawScope;
        if (canvasDrawScope == null) {
            canvasDrawScope = new CanvasDrawScope();
            this.canvasDrawScope = canvasDrawScope;
        }
        CanvasDrawScope canvasDrawScope3 = canvasDrawScope;
        long j4 = IntSizeKt.toSize-ozmzZPI(j);
        LayoutDirection layoutDirection2 = cacheDrawScope.getLayoutDirection();
        CanvasDrawScope.DrawParams drawParams4 = canvasDrawScope3.getDrawParams();
        Density densityComponent2 = drawParams4.component1();
        LayoutDirection layoutDirectionComponent3 = drawParams4.component2();
        Canvas canvasComponent4 = drawParams4.component3();
        long j5 = drawParams4.component4-NH-jbRc();
        CanvasDrawScope.DrawParams drawParams5 = canvasDrawScope3.getDrawParams();
        drawParams5.setDensity(cacheDrawScope);
        drawParams5.setLayoutDirection(layoutDirection2);
        drawParams5.setCanvas(Canvas);
        drawParams5.setSize-uvyYCjk(j4);
        Canvas.save();
        ImageBitmap imageBitmap3 = imageBitmap;
        DrawScope.drawRect-n-J9OG0$default(canvasDrawScope3, Color.Companion.getBlack-0d7_KjU(), 0L, j4, 0.0f, (DrawStyle) null, (ColorFilter) null, BlendMode.Companion.getClear-0nO6VwU(), 58, (Object) null);
        function1.invoke(canvasDrawScope3);
        Canvas.restore();
        CanvasDrawScope.DrawParams drawParams6 = canvasDrawScope3.getDrawParams();
        drawParams6.setDensity(densityComponent2);
        drawParams6.setLayoutDirection(layoutDirectionComponent3);
        drawParams6.setCanvas(canvasComponent4);
        drawParams6.setSize-uvyYCjk(j5);
        imageBitmap3.prepareToDraw();
        return imageBitmap3;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BorderCache)) {
            return false;
        }
        BorderCache borderCache = (BorderCache) other;
        return Intrinsics.areEqual(this.imageBitmap, borderCache.imageBitmap) && Intrinsics.areEqual(this.canvas, borderCache.canvas) && Intrinsics.areEqual(this.canvasDrawScope, borderCache.canvasDrawScope) && Intrinsics.areEqual(this.borderPath, borderCache.borderPath);
    }

    public int hashCode() {
        ImageBitmap imageBitmap = this.imageBitmap;
        int iHashCode = (imageBitmap == null ? 0 : imageBitmap.hashCode()) * 31;
        Canvas canvas = this.canvas;
        int iHashCode2 = (iHashCode + (canvas == null ? 0 : canvas.hashCode())) * 31;
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        int iHashCode3 = (iHashCode2 + (canvasDrawScope == null ? 0 : canvasDrawScope.hashCode())) * 31;
        Path path = this.borderPath;
        return iHashCode3 + (path != null ? path.hashCode() : 0);
    }

    public final Path obtainPath() {
        Path path = this.borderPath;
        if (path != null) {
            return path;
        }
        Path Path = AndroidPath_androidKt.Path();
        this.borderPath = Path;
        return Path;
    }

    public String toString() {
        return "BorderCache(imageBitmap=" + this.imageBitmap + ", canvas=" + this.canvas + ", canvasDrawScope=" + this.canvasDrawScope + ", borderPath=" + this.borderPath + ')';
    }

    public BorderCache(ImageBitmap imageBitmap, Canvas canvas, CanvasDrawScope canvasDrawScope, Path path) {
        this.imageBitmap = imageBitmap;
        this.canvas = canvas;
        this.canvasDrawScope = canvasDrawScope;
        this.borderPath = path;
    }

    public BorderCache() {
        this(null, null, null, null, 15, null);
    }
}
