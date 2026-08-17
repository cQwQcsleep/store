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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_integrationInstructions", "Landroidx/compose/ui/graphics/vector/ImageVector;", "IntegrationInstructions", "Landroidx/compose/material/icons/Icons$Outlined;", "getIntegrationInstructions", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IntegrationInstructionsKt {
    private static ImageVector _integrationInstructions;

    public static final ImageVector getIntegrationInstructions(Icons.Outlined outlined) {
        ImageVector imageVector = _integrationInstructions;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.IntegrationInstructions", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(11.0f, 14.17f);
        pathBuilder.lineToRelative(-2.17f, -2.17f);
        pathBuilder.lineToRelative(2.17f, -2.17f);
        pathBuilder.lineToRelative(-1.41f, -1.42f);
        pathBuilder.lineToRelative(-3.59f, 3.59f);
        pathBuilder.lineToRelative(3.59f, 3.59f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(14.41f, 15.59f);
        pathBuilder2.lineToRelative(3.59f, -3.59f);
        pathBuilder2.lineToRelative(-3.59f, -3.59f);
        pathBuilder2.lineToRelative(-1.41f, 1.42f);
        pathBuilder2.lineToRelative(2.17f, 2.17f);
        pathBuilder2.lineToRelative(-2.17f, 2.17f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(19.0f, 3.0f);
        pathBuilder3.horizontalLineToRelative(-4.18f);
        pathBuilder3.curveTo(14.4f, 1.84f, 13.3f, 1.0f, 12.0f, 1.0f);
        pathBuilder3.reflectiveCurveTo(9.6f, 1.84f, 9.18f, 3.0f);
        pathBuilder3.horizontalLineTo(5.0f);
        pathBuilder3.curveTo(4.86f, 3.0f, 4.73f, 3.01f, 4.6f, 3.04f);
        pathBuilder3.curveTo(4.21f, 3.12f, 3.86f, 3.32f, 3.59f, 3.59f);
        pathBuilder3.curveToRelative(-0.18f, 0.18f, -0.33f, 0.4f, -0.43f, 0.64f);
        pathBuilder3.curveTo(3.06f, 4.46f, 3.0f, 4.72f, 3.0f, 5.0f);
        pathBuilder3.verticalLineToRelative(10.0f);
        pathBuilder3.verticalLineToRelative(1.0f);
        pathBuilder3.verticalLineToRelative(3.0f);
        pathBuilder3.curveToRelative(0.0f, 0.27f, 0.06f, 0.54f, 0.16f, 0.78f);
        pathBuilder3.curveToRelative(0.1f, 0.24f, 0.25f, 0.45f, 0.43f, 0.64f);
        pathBuilder3.curveToRelative(0.27f, 0.27f, 0.62f, 0.47f, 1.01f, 0.55f);
        pathBuilder3.curveTo(4.73f, 20.99f, 4.86f, 21.0f, 5.0f, 21.0f);
        pathBuilder3.horizontalLineToRelative(14.0f);
        pathBuilder3.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder3.verticalLineToRelative(-3.0f);
        pathBuilder3.verticalLineToRelative(-1.0f);
        pathBuilder3.verticalLineTo(5.0f);
        pathBuilder3.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(12.0f, 2.75f);
        pathBuilder3.curveToRelative(0.41f, 0.0f, 0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilder3.reflectiveCurveTo(12.41f, 4.25f, 12.0f, 4.25f);
        pathBuilder3.reflectiveCurveToRelative(-0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilder3.reflectiveCurveTo(11.59f, 2.75f, 12.0f, 2.75f);
        pathBuilder3.close();
        pathBuilder3.moveTo(19.0f, 15.0f);
        pathBuilder3.verticalLineToRelative(1.0f);
        pathBuilder3.verticalLineToRelative(3.0f);
        pathBuilder3.horizontalLineTo(5.0f);
        pathBuilder3.verticalLineToRelative(-3.0f);
        pathBuilder3.verticalLineToRelative(-1.0f);
        pathBuilder3.verticalLineTo(5.0f);
        pathBuilder3.horizontalLineToRelative(14.0f);
        pathBuilder3.verticalLineTo(15.0f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _integrationInstructions = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
