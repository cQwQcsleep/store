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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contactEmergency", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContactEmergency", "Landroidx/compose/material/icons/Icons$Outlined;", "getContactEmergency", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactEmergencyKt {
    private static ImageVector _contactEmergency;

    public static final ImageVector getContactEmergency(Icons.Outlined outlined) {
        ImageVector imageVector = _contactEmergency;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ContactEmergency", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.0f, 14.0f);
        pathBuilder.curveToRelative(1.65f, 0.0f, 3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilder.reflectiveCurveToRelative(-1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilder.reflectiveCurveToRelative(-3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilder.reflectiveCurveTo(7.35f, 14.0f, 9.0f, 14.0f);
        pathBuilder.close();
        pathBuilder.moveTo(9.0f, 10.0f);
        pathBuilder.curveToRelative(0.54f, 0.0f, 1.0f, 0.46f, 1.0f, 1.0f);
        pathBuilder.reflectiveCurveToRelative(-0.46f, 1.0f, -1.0f, 1.0f);
        pathBuilder.reflectiveCurveToRelative(-1.0f, -0.46f, -1.0f, -1.0f);
        pathBuilder.reflectiveCurveTo(8.46f, 10.0f, 9.0f, 10.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(22.0f, 3.0f);
        pathBuilder2.horizontalLineTo(2.0f);
        pathBuilder2.curveTo(0.9f, 3.0f, 0.0f, 3.9f, 0.0f, 5.0f);
        pathBuilder2.verticalLineToRelative(14.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(20.0f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 1.99f, -0.9f, 1.99f, -2.0f);
        pathBuilder2.lineTo(24.0f, 5.0f);
        pathBuilder2.curveTo(24.0f, 3.9f, 23.1f, 3.0f, 22.0f, 3.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(4.54f, 19.0f);
        pathBuilder2.curveToRelative(1.1f, -1.22f, 2.69f, -2.0f, 4.46f, -2.0f);
        pathBuilder2.reflectiveCurveToRelative(3.36f, 0.78f, 4.46f, 2.0f);
        pathBuilder2.horizontalLineTo(4.54f);
        pathBuilder2.close();
        pathBuilder2.moveTo(22.0f, 19.0f);
        pathBuilder2.horizontalLineToRelative(-6.08f);
        pathBuilder2.curveToRelative(-1.38f, -2.39f, -3.96f, -4.0f, -6.92f, -4.0f);
        pathBuilder2.reflectiveCurveToRelative(-5.54f, 1.61f, -6.92f, 4.0f);
        pathBuilder2.horizontalLineTo(2.0f);
        pathBuilder2.verticalLineTo(5.0f);
        pathBuilder2.horizontalLineToRelative(20.0f);
        pathBuilder2.verticalLineTo(19.0f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(15.78f, 11.15f);
        pathBuilder3.lineToRelative(1.47f, -0.85f);
        pathBuilder3.lineToRelative(0.0f, 1.7f);
        pathBuilder3.lineToRelative(1.5f, 0.0f);
        pathBuilder3.lineToRelative(0.0f, -1.7f);
        pathBuilder3.lineToRelative(1.47f, 0.85f);
        pathBuilder3.lineToRelative(0.75f, -1.3f);
        pathBuilder3.lineToRelative(-1.47f, -0.85f);
        pathBuilder3.lineToRelative(1.47f, -0.85f);
        pathBuilder3.lineToRelative(-0.75f, -1.3f);
        pathBuilder3.lineToRelative(-1.47f, 0.85f);
        pathBuilder3.lineToRelative(0.0f, -1.7f);
        pathBuilder3.lineToRelative(-1.5f, 0.0f);
        pathBuilder3.lineToRelative(0.0f, 1.7f);
        pathBuilder3.lineToRelative(-1.47f, -0.85f);
        pathBuilder3.lineToRelative(-0.75f, 1.3f);
        pathBuilder3.lineToRelative(1.47f, 0.85f);
        pathBuilder3.lineToRelative(-1.47f, 0.85f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _contactEmergency = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
