package androidx.compose.material.icons.outlined;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_adminPanelSettings", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AdminPanelSettings", "Landroidx/compose/material/icons/Icons$Outlined;", "getAdminPanelSettings", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AdminPanelSettingsKt {
    private static ImageVector _adminPanelSettings;

    public static final ImageVector getAdminPanelSettings(Icons.Outlined outlined) {
        ImageVector imageVector = _adminPanelSettings;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AdminPanelSettings", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        PathFillType.Companion companion = PathFillType.Companion;
        int i = companion.getEvenOdd-Rg-k1Os();
        Color.Companion companion2 = Color.Companion;
        SolidColor solidColor = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion3 = StrokeCap.Companion;
        int i2 = companion3.getButt-KaPHkGw();
        StrokeJoin.Companion companion4 = StrokeJoin.Companion;
        int i3 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(17.0f, 15.5f);
        pathBuilder.moveToRelative(-1.12f, 0.0f);
        pathBuilder.arcToRelative(1.12f, 1.12f, 0.0f, true, true, 2.24f, 0.0f);
        pathBuilder.arcToRelative(1.12f, 1.12f, 0.0f, true, true, -2.24f, 0.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), i, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i2, i3, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i4 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor2 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion3.getButt-KaPHkGw();
        int i6 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(17.0f, 17.5f);
        pathBuilder2.curveToRelative(-0.73f, 0.0f, -2.19f, 0.36f, -2.24f, 1.08f);
        pathBuilder2.curveToRelative(0.5f, 0.71f, 1.32f, 1.17f, 2.24f, 1.17f);
        pathBuilder2.reflectiveCurveToRelative(1.74f, -0.46f, 2.24f, -1.17f);
        pathBuilder2.curveTo(19.19f, 17.86f, 17.73f, 17.5f, 17.0f, 17.5f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), i4, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i7 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor3 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i8 = companion3.getButt-KaPHkGw();
        int i9 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(18.0f, 11.09f);
        pathBuilder3.verticalLineTo(6.27f);
        pathBuilder3.lineTo(10.5f, 3.0f);
        pathBuilder3.lineTo(3.0f, 6.27f);
        pathBuilder3.verticalLineToRelative(4.91f);
        pathBuilder3.curveToRelative(0.0f, 4.54f, 3.2f, 8.79f, 7.5f, 9.82f);
        pathBuilder3.curveToRelative(0.55f, -0.13f, 1.08f, -0.32f, 1.6f, -0.55f);
        pathBuilder3.curveTo(13.18f, 21.99f, 14.97f, 23.0f, 17.0f, 23.0f);
        pathBuilder3.curveToRelative(3.31f, 0.0f, 6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilder3.curveTo(23.0f, 14.03f, 20.84f, 11.57f, 18.0f, 11.09f);
        pathBuilder3.close();
        pathBuilder3.moveTo(11.0f, 17.0f);
        pathBuilder3.curveToRelative(0.0f, 0.56f, 0.08f, 1.11f, 0.23f, 1.62f);
        pathBuilder3.curveToRelative(-0.24f, 0.11f, -0.48f, 0.22f, -0.73f, 0.3f);
        pathBuilder3.curveToRelative(-3.17f, -1.0f, -5.5f, -4.24f, -5.5f, -7.74f);
        pathBuilder3.verticalLineToRelative(-3.6f);
        pathBuilder3.lineToRelative(5.5f, -2.4f);
        pathBuilder3.lineToRelative(5.5f, 2.4f);
        pathBuilder3.verticalLineToRelative(3.51f);
        pathBuilder3.curveTo(13.16f, 11.57f, 11.0f, 14.03f, 11.0f, 17.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(17.0f, 21.0f);
        pathBuilder3.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilder3.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilder3.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilder3.curveTo(21.0f, 19.21f, 19.21f, 21.0f, 17.0f, 21.0f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), i7, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i8, i9, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _adminPanelSettings = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
