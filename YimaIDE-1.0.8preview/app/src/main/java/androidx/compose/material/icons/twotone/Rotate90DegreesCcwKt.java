package androidx.compose.material.icons.twotone;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rotate90DegreesCcw", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rotate90DegreesCcw", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRotate90DegreesCcw", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Rotate90DegreesCcwKt {
    private static ImageVector _rotate90DegreesCcw;

    public static final ImageVector getRotate90DegreesCcw(Icons.TwoTone twoTone) {
        ImageVector imageVector = _rotate90DegreesCcw;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Rotate90DegreesCcw", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(7.35f, 9.24f);
        pathBuilder.lineTo(3.69f, 12.9f);
        pathBuilder.lineToRelative(3.65f, 3.66f);
        pathBuilder.lineTo(11.0f, 12.9f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(7.34f, 6.41f);
        pathBuilder2.lineTo(0.86f, 12.9f);
        pathBuilder2.lineToRelative(6.49f, 6.48f);
        pathBuilder2.lineToRelative(6.49f, -6.48f);
        pathBuilder2.lineToRelative(-6.5f, -6.49f);
        pathBuilder2.close();
        pathBuilder2.moveTo(7.34f, 16.56f);
        pathBuilder2.lineTo(3.69f, 12.9f);
        pathBuilder2.lineToRelative(3.66f, -3.66f);
        pathBuilder2.lineTo(11.0f, 12.9f);
        pathBuilder2.lineToRelative(-3.66f, 3.66f);
        pathBuilder2.close();
        pathBuilder2.moveTo(19.36f, 6.64f);
        pathBuilder2.curveTo(17.61f, 4.88f, 15.3f, 4.0f, 13.0f, 4.0f);
        pathBuilder2.lineTo(13.0f, 0.76f);
        pathBuilder2.lineTo(8.76f, 5.0f);
        pathBuilder2.lineTo(13.0f, 9.24f);
        pathBuilder2.lineTo(13.0f, 6.0f);
        pathBuilder2.curveToRelative(1.79f, 0.0f, 3.58f, 0.68f, 4.95f, 2.05f);
        pathBuilder2.curveToRelative(2.73f, 2.73f, 2.73f, 7.17f, 0.0f, 9.9f);
        pathBuilder2.curveTo(16.58f, 19.32f, 14.79f, 20.0f, 13.0f, 20.0f);
        pathBuilder2.curveToRelative(-0.97f, 0.0f, -1.94f, -0.21f, -2.84f, -0.61f);
        pathBuilder2.lineToRelative(-1.49f, 1.49f);
        pathBuilder2.curveTo(10.02f, 21.62f, 11.51f, 22.0f, 13.0f, 22.0f);
        pathBuilder2.curveToRelative(2.3f, 0.0f, 4.61f, -0.88f, 6.36f, -2.64f);
        pathBuilder2.curveToRelative(3.52f, -3.51f, 3.52f, -9.21f, 0.0f, -12.72f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _rotate90DegreesCcw = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
