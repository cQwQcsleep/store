package androidx.core.graphics;

import android.graphics.Point;
import android.graphics.PointF;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\n\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0086\n\u001a\r\u0010\u0000\u001a\u00020\u0004*\u00020\u0005H\u0086\n\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0004H\u0086\n\u001a\r\u0010\n\u001a\u00020\u0002*\u00020\u0002H\u0086\n\u001a\r\u0010\n\u001a\u00020\u0005*\u00020\u0005H\u0086\n\u001a\u0015\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\u000b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\f\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\r\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\r\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\f\u001a\u00020\u0004H\u0086\n\u001a\r\u0010\u000e\u001a\u00020\u0005*\u00020\u0002H\u0086\b\u001a\r\u0010\u000f\u001a\u00020\u0002*\u00020\u0005H\u0086\b¨\u0006\u0010"}, d2 = {"component1", "", "Landroid/graphics/Point;", "component2", "", "Landroid/graphics/PointF;", "plus", "p", "xy", "minus", "unaryMinus", "times", "scalar", "div", "toPointF", "toPoint", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PointKt {
    public static final int component1(Point point) {
        point.getClass();
        return point.x;
    }

    public static final int component2(Point point) {
        point.getClass();
        return point.y;
    }

    public static final Point div(Point point, float f) {
        point.getClass();
        return new Point(Math.round(point.x / f), Math.round(point.y / f));
    }

    public static final Point minus(Point point, Point point2) {
        point.getClass();
        point2.getClass();
        Point point3 = new Point(point.x, point.y);
        point3.offset(-point2.x, -point2.y);
        return point3;
    }

    public static final Point plus(Point point, Point point2) {
        point.getClass();
        point2.getClass();
        Point point3 = new Point(point.x, point.y);
        point3.offset(point2.x, point2.y);
        return point3;
    }

    public static final Point times(Point point, float f) {
        point.getClass();
        return new Point(Math.round(point.x * f), Math.round(point.y * f));
    }

    public static final Point toPoint(PointF pointF) {
        pointF.getClass();
        return new Point((int) pointF.x, (int) pointF.y);
    }

    public static final PointF toPointF(Point point) {
        point.getClass();
        return new PointF(point);
    }

    public static final Point unaryMinus(Point point) {
        point.getClass();
        return new Point(-point.x, -point.y);
    }

    public static final float component1(PointF pointF) {
        pointF.getClass();
        return pointF.x;
    }

    public static final float component2(PointF pointF) {
        pointF.getClass();
        return pointF.y;
    }

    public static final PointF unaryMinus(PointF pointF) {
        pointF.getClass();
        return new PointF(-pointF.x, -pointF.y);
    }

    public static final PointF plus(PointF pointF, PointF pointF2) {
        pointF.getClass();
        pointF2.getClass();
        PointF pointF3 = new PointF(pointF.x, pointF.y);
        pointF3.offset(pointF2.x, pointF2.y);
        return pointF3;
    }

    public static final Point plus(Point point, int i) {
        point.getClass();
        Point point2 = new Point(point.x, point.y);
        point2.offset(i, i);
        return point2;
    }

    public static final PointF div(PointF pointF, float f) {
        pointF.getClass();
        return new PointF(pointF.x / f, pointF.y / f);
    }

    public static final PointF minus(PointF pointF, PointF pointF2) {
        pointF.getClass();
        pointF2.getClass();
        PointF pointF3 = new PointF(pointF.x, pointF.y);
        pointF3.offset(-pointF2.x, -pointF2.y);
        return pointF3;
    }

    public static final PointF plus(PointF pointF, float f) {
        pointF.getClass();
        PointF pointF2 = new PointF(pointF.x, pointF.y);
        pointF2.offset(f, f);
        return pointF2;
    }

    public static final PointF times(PointF pointF, float f) {
        pointF.getClass();
        return new PointF(pointF.x * f, pointF.y * f);
    }

    public static final Point minus(Point point, int i) {
        point.getClass();
        Point point2 = new Point(point.x, point.y);
        int i2 = -i;
        point2.offset(i2, i2);
        return point2;
    }

    public static final PointF minus(PointF pointF, float f) {
        pointF.getClass();
        PointF pointF2 = new PointF(pointF.x, pointF.y);
        float f2 = -f;
        pointF2.offset(f2, f2);
        return pointF2;
    }
}
