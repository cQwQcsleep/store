package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_volunteerActivism", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VolunteerActivism", "Landroidx/compose/material/icons/Icons$Outlined;", "getVolunteerActivism", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VolunteerActivismKt {
    private static ImageVector _volunteerActivism;

    public static final ImageVector getVolunteerActivism(Icons.Outlined outlined) {
        ImageVector imageVector = _volunteerActivism;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.VolunteerActivism", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(16.0f, 13.0f);
        pathBuilder.curveToRelative(3.09f, -2.81f, 6.0f, -5.44f, 6.0f, -7.7f);
        pathBuilder.curveTo(22.0f, 3.45f, 20.55f, 2.0f, 18.7f, 2.0f);
        pathBuilder.curveToRelative(-1.04f, 0.0f, -2.05f, 0.49f, -2.7f, 1.25f);
        pathBuilder.curveTo(15.34f, 2.49f, 14.34f, 2.0f, 13.3f, 2.0f);
        pathBuilder.curveTo(11.45f, 2.0f, 10.0f, 3.45f, 10.0f, 5.3f);
        pathBuilder.curveTo(10.0f, 7.56f, 12.91f, 10.19f, 16.0f, 13.0f);
        pathBuilder.close();
        pathBuilder.moveTo(13.3f, 4.0f);
        pathBuilder.curveToRelative(0.44f, 0.0f, 0.89f, 0.21f, 1.18f, 0.55f);
        pathBuilder.lineTo(16.0f, 6.34f);
        pathBuilder.lineToRelative(1.52f, -1.79f);
        pathBuilder.curveTo(17.81f, 4.21f, 18.26f, 4.0f, 18.7f, 4.0f);
        pathBuilder.curveTo(19.44f, 4.0f, 20.0f, 4.56f, 20.0f, 5.3f);
        pathBuilder.curveToRelative(0.0f, 1.12f, -2.04f, 3.17f, -4.0f, 4.99f);
        pathBuilder.curveToRelative(-1.96f, -1.82f, -4.0f, -3.88f, -4.0f, -4.99f);
        pathBuilder.curveTo(12.0f, 4.56f, 12.56f, 4.0f, 13.3f, 4.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(19.0f, 16.0f);
        pathBuilder2.horizontalLineToRelative(-2.0f);
        pathBuilder2.curveToRelative(0.0f, -1.2f, -0.75f, -2.28f, -1.87f, -2.7f);
        pathBuilder2.lineTo(8.97f, 11.0f);
        pathBuilder2.horizontalLineTo(1.0f);
        pathBuilder2.verticalLineToRelative(11.0f);
        pathBuilder2.horizontalLineToRelative(6.0f);
        pathBuilder2.verticalLineToRelative(-1.44f);
        pathBuilder2.lineToRelative(7.0f, 1.94f);
        pathBuilder2.lineToRelative(8.0f, -2.5f);
        pathBuilder2.verticalLineToRelative(-1.0f);
        pathBuilder2.curveTo(22.0f, 17.34f, 20.66f, 16.0f, 19.0f, 16.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(3.0f, 20.0f);
        pathBuilder2.verticalLineToRelative(-7.0f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.verticalLineToRelative(7.0f);
        pathBuilder2.horizontalLineTo(3.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(13.97f, 20.41f);
        pathBuilder2.lineTo(7.0f, 18.48f);
        pathBuilder2.verticalLineTo(13.0f);
        pathBuilder2.horizontalLineToRelative(1.61f);
        pathBuilder2.lineToRelative(5.82f, 2.17f);
        pathBuilder2.curveTo(14.77f, 15.3f, 15.0f, 15.63f, 15.0f, 16.0f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, -1.99f, -0.05f, -2.3f, -0.15f);
        pathBuilder2.lineToRelative(-2.38f, -0.79f);
        pathBuilder2.lineToRelative(-0.63f, 1.9f);
        pathBuilder2.lineToRelative(2.38f, 0.79f);
        pathBuilder2.curveToRelative(0.51f, 0.17f, 1.04f, 0.26f, 1.58f, 0.26f);
        pathBuilder2.horizontalLineTo(19.0f);
        pathBuilder2.curveToRelative(0.39f, 0.0f, 0.74f, 0.23f, 0.9f, 0.56f);
        pathBuilder2.lineTo(13.97f, 20.41f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _volunteerActivism = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
