package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsHandball", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsHandball", "Landroidx/compose/material/icons/Icons$Rounded;", "getSportsHandball", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsHandballKt {
    private static ImageVector _sportsHandball;

    public static final ImageVector getSportsHandball(Icons.Rounded rounded) {
        ImageVector imageVector = _sportsHandball;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SportsHandball", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(14.27f, 6.0f);
        pathBuilder.curveTo(13.72f, 6.95f, 14.05f, 8.18f, 15.0f, 8.73f);
        pathBuilder.curveToRelative(0.95f, 0.55f, 2.18f, 0.22f, 2.73f, -0.73f);
        pathBuilder.curveToRelative(0.55f, -0.95f, 0.22f, -2.18f, -0.73f, -2.73f);
        pathBuilder.curveTo(16.05f, 4.72f, 14.82f, 5.05f, 14.27f, 6.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(15.84f, 10.41f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, -1.63f, -0.94f, -2.6f, -1.5f);
        pathBuilder2.curveToRelative(-2.13f, -1.24f, -3.01f, -3.83f, -2.18f, -6.07f);
        pathBuilder2.curveToRelative(0.17f, -0.46f, -0.01f, -0.97f, -0.43f, -1.21f);
        pathBuilder2.curveTo(10.1f, 1.33f, 9.41f, 1.56f, 9.2f, 2.13f);
        pathBuilder2.curveTo(8.25f, 4.64f, 8.85f, 7.48f, 10.66f, 9.4f);
        pathBuilder2.lineToRelative(-4.65f, 8.05f);
        pathBuilder2.curveToRelative(-0.28f, 0.48f, -0.11f, 1.09f, 0.37f, 1.37f);
        pathBuilder2.curveToRelative(0.48f, 0.28f, 1.09f, 0.11f, 1.37f, -0.37f);
        pathBuilder2.lineToRelative(1.0f, -1.73f);
        pathBuilder2.lineToRelative(1.73f, 1.0f);
        pathBuilder2.lineToRelative(-2.5f, 4.33f);
        pathBuilder2.curveToRelative(-0.28f, 0.48f, -0.11f, 1.09f, 0.37f, 1.37f);
        pathBuilder2.curveToRelative(0.48f, 0.28f, 1.09f, 0.11f, 1.37f, -0.37f);
        pathBuilder2.lineToRelative(5.79f, -10.02f);
        pathBuilder2.curveToRelative(0.98f, 1.34f, 1.26f, 3.12f, 0.66f, 4.72f);
        pathBuilder2.curveToRelative(-0.17f, 0.45f, 0.02f, 0.96f, 0.43f, 1.2f);
        pathBuilder2.curveToRelative(0.53f, 0.31f, 1.22f, 0.08f, 1.44f, -0.5f);
        pathBuilder2.curveTo(19.01f, 15.83f, 18.45f, 12.61f, 15.84f, 10.41f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(12.75f, 3.8f);
        pathBuilder3.curveToRelative(0.72f, 0.41f, 1.63f, 0.17f, 2.05f, -0.55f);
        pathBuilder3.curveToRelative(0.41f, -0.72f, 0.17f, -1.63f, -0.55f, -2.05f);
        pathBuilder3.curveToRelative(-0.72f, -0.41f, -1.63f, -0.17f, -2.05f, 0.55f);
        pathBuilder3.curveTo(11.79f, 2.47f, 12.03f, 3.39f, 12.75f, 3.8f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _sportsHandball = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
