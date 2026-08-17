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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cloudDownload", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CloudDownload", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCloudDownload", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CloudDownloadKt {
    private static ImageVector _cloudDownload;

    public static final ImageVector getCloudDownload(Icons.TwoTone twoTone) {
        ImageVector imageVector = _cloudDownload;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CloudDownload", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(19.21f, 12.04f);
        pathBuilder.lineToRelative(-1.53f, -0.11f);
        pathBuilder.lineToRelative(-0.3f, -1.5f);
        pathBuilder.curveTo(16.88f, 7.86f, 14.62f, 6.0f, 12.0f, 6.0f);
        pathBuilder.curveTo(9.94f, 6.0f, 8.08f, 7.14f, 7.12f, 8.96f);
        pathBuilder.lineToRelative(-0.5f, 0.95f);
        pathBuilder.lineToRelative(-1.07f, 0.11f);
        pathBuilder.curveTo(3.53f, 10.24f, 2.0f, 11.95f, 2.0f, 14.0f);
        pathBuilder.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilder.horizontalLineToRelative(13.0f);
        pathBuilder.curveToRelative(1.65f, 0.0f, 3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilder.curveToRelative(0.0f, -1.55f, -1.22f, -2.86f, -2.79f, -2.96f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 17.0f);
        pathBuilder.lineToRelative(-4.0f, -4.0f);
        pathBuilder.horizontalLineToRelative(2.55f);
        pathBuilder.verticalLineToRelative(-3.0f);
        pathBuilder.horizontalLineToRelative(2.91f);
        pathBuilder.verticalLineToRelative(3.0f);
        pathBuilder.horizontalLineTo(16.0f);
        pathBuilder.lineToRelative(-4.0f, 4.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(19.35f, 10.04f);
        pathBuilder2.curveTo(18.67f, 6.59f, 15.64f, 4.0f, 12.0f, 4.0f);
        pathBuilder2.curveTo(9.11f, 4.0f, 6.6f, 5.64f, 5.35f, 8.04f);
        pathBuilder2.curveTo(2.34f, 8.36f, 0.0f, 10.91f, 0.0f, 14.0f);
        pathBuilder2.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilder2.horizontalLineToRelative(13.0f);
        pathBuilder2.curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilder2.curveToRelative(0.0f, -2.64f, -2.05f, -4.78f, -4.65f, -4.96f);
        pathBuilder2.close();
        pathBuilder2.moveTo(19.0f, 18.0f);
        pathBuilder2.lineTo(6.0f, 18.0f);
        pathBuilder2.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilder2.curveToRelative(0.0f, -2.05f, 1.53f, -3.76f, 3.56f, -3.97f);
        pathBuilder2.lineToRelative(1.07f, -0.11f);
        pathBuilder2.lineToRelative(0.5f, -0.95f);
        pathBuilder2.curveTo(8.08f, 7.14f, 9.94f, 6.0f, 12.0f, 6.0f);
        pathBuilder2.curveToRelative(2.62f, 0.0f, 4.88f, 1.86f, 5.39f, 4.43f);
        pathBuilder2.lineToRelative(0.3f, 1.5f);
        pathBuilder2.lineToRelative(1.53f, 0.11f);
        pathBuilder2.curveToRelative(1.56f, 0.1f, 2.78f, 1.41f, 2.78f, 2.96f);
        pathBuilder2.curveToRelative(0.0f, 1.65f, -1.35f, 3.0f, -3.0f, 3.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(13.45f, 10.0f);
        pathBuilder2.horizontalLineToRelative(-2.9f);
        pathBuilder2.verticalLineToRelative(3.0f);
        pathBuilder2.lineTo(8.0f, 13.0f);
        pathBuilder2.lineToRelative(4.0f, 4.0f);
        pathBuilder2.lineToRelative(4.0f, -4.0f);
        pathBuilder2.horizontalLineToRelative(-2.55f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _cloudDownload = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
