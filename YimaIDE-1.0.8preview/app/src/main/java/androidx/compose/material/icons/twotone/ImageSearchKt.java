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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_imageSearch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ImageSearch", "Landroidx/compose/material/icons/Icons$TwoTone;", "getImageSearch", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ImageSearchKt {
    private static ImageVector _imageSearch;

    public static final ImageVector getImageSearch(Icons.TwoTone twoTone) {
        ImageVector imageVector = _imageSearch;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ImageSearch", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(17.7f, 11.53f);
        pathBuilder.curveToRelative(-0.7f, 0.31f, -1.45f, 0.47f, -2.21f, 0.47f);
        pathBuilder.curveTo(12.46f, 12.0f, 10.0f, 9.53f, 10.0f, 6.5f);
        pathBuilder.curveToRelative(0.0f, -0.17f, 0.01f, -0.34f, 0.03f, -0.5f);
        pathBuilder.horizontalLineTo(4.0f);
        pathBuilder.verticalLineToRelative(14.0f);
        pathBuilder.horizontalLineToRelative(14.0f);
        pathBuilder.verticalLineToRelative(-8.17f);
        pathBuilder.lineToRelative(-0.3f, -0.3f);
        pathBuilder.close();
        pathBuilder.moveTo(5.5f, 18.0f);
        pathBuilder.lineToRelative(2.75f, -3.53f);
        pathBuilder.lineToRelative(1.96f, 2.36f);
        pathBuilder.lineToRelative(2.75f, -3.54f);
        pathBuilder.lineTo(16.5f, 18.0f);
        pathBuilder.horizontalLineToRelative(-11.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(10.21f, 16.83f);
        pathBuilder2.lineToRelative(-1.96f, -2.36f);
        pathBuilder2.lineTo(5.5f, 18.0f);
        pathBuilder2.horizontalLineToRelative(11.0f);
        pathBuilder2.lineToRelative(-3.54f, -4.71f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.0f, 6.5f);
        pathBuilder2.curveTo(20.0f, 4.01f, 17.99f, 2.0f, 15.5f, 2.0f);
        pathBuilder2.reflectiveCurveTo(11.0f, 4.01f, 11.0f, 6.5f);
        pathBuilder2.reflectiveCurveToRelative(2.01f, 4.5f, 4.49f, 4.5f);
        pathBuilder2.curveToRelative(0.88f, 0.0f, 1.7f, -0.26f, 2.39f, -0.7f);
        pathBuilder2.lineTo(21.0f, 13.42f);
        pathBuilder2.lineTo(22.42f, 12.0f);
        pathBuilder2.lineTo(19.3f, 8.89f);
        pathBuilder2.curveToRelative(0.44f, -0.7f, 0.7f, -1.51f, 0.7f, -2.39f);
        pathBuilder2.close();
        pathBuilder2.moveTo(15.5f, 9.0f);
        pathBuilder2.curveTo(14.12f, 9.0f, 13.0f, 7.88f, 13.0f, 6.5f);
        pathBuilder2.reflectiveCurveTo(14.12f, 4.0f, 15.5f, 4.0f);
        pathBuilder2.reflectiveCurveTo(18.0f, 5.12f, 18.0f, 6.5f);
        pathBuilder2.reflectiveCurveTo(16.88f, 9.0f, 15.5f, 9.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(18.0f, 20.0f);
        pathBuilder2.horizontalLineTo(4.0f);
        pathBuilder2.verticalLineTo(6.0f);
        pathBuilder2.horizontalLineToRelative(6.03f);
        pathBuilder2.curveToRelative(0.06f, -0.72f, 0.27f, -1.39f, 0.58f, -2.0f);
        pathBuilder2.horizontalLineTo(4.0f);
        pathBuilder2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder2.verticalLineToRelative(14.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(14.0f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder2.verticalLineToRelative(-6.17f);
        pathBuilder2.lineToRelative(-2.0f, -2.0f);
        pathBuilder2.verticalLineTo(20.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _imageSearch = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
