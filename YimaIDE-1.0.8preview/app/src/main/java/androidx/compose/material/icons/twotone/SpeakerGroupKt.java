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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_speakerGroup", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SpeakerGroup", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSpeakerGroup", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpeakerGroupKt {
    private static ImageVector _speakerGroup;

    public static final ImageVector getSpeakerGroup(Icons.TwoTone twoTone) {
        ImageVector imageVector = _speakerGroup;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SpeakerGroup", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(10.0f, 16.99f);
        pathBuilder.lineToRelative(8.0f, 0.01f);
        pathBuilder.lineTo(18.0f, 3.0f);
        pathBuilder.horizontalLineToRelative(-8.0f);
        pathBuilder.verticalLineToRelative(13.99f);
        pathBuilder.close();
        pathBuilder.moveTo(14.0f, 4.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, 0.89f, 2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, -0.89f, -2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilder.close();
        pathBuilder.moveTo(14.0f, 9.0f);
        pathBuilder.curveToRelative(1.93f, 0.0f, 3.5f, 1.57f, 3.5f, 3.5f);
        pathBuilder.reflectiveCurveTo(15.93f, 16.0f, 14.0f, 16.0f);
        pathBuilder.reflectiveCurveToRelative(-3.5f, -1.57f, -3.5f, -3.5f);
        pathBuilder.reflectiveCurveTo(12.07f, 9.0f, 14.0f, 9.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(18.2f, 1.0f);
        pathBuilder2.lineTo(9.8f, 1.0f);
        pathBuilder2.curveTo(8.81f, 1.0f, 8.0f, 1.81f, 8.0f, 2.8f);
        pathBuilder2.verticalLineToRelative(14.4f);
        pathBuilder2.curveToRelative(0.0f, 0.99f, 0.81f, 1.79f, 1.8f, 1.79f);
        pathBuilder2.lineToRelative(8.4f, 0.01f);
        pathBuilder2.curveToRelative(0.99f, 0.0f, 1.8f, -0.81f, 1.8f, -1.8f);
        pathBuilder2.lineTo(20.0f, 2.8f);
        pathBuilder2.curveToRelative(0.0f, -0.99f, -0.81f, -1.8f, -1.8f, -1.8f);
        pathBuilder2.close();
        pathBuilder2.moveTo(18.0f, 17.0f);
        pathBuilder2.lineToRelative(-8.0f, -0.01f);
        pathBuilder2.lineTo(10.0f, 3.0f);
        pathBuilder2.horizontalLineToRelative(8.0f);
        pathBuilder2.verticalLineToRelative(14.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(14.0f, 8.0f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilder2.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder2.reflectiveCurveToRelative(-2.0f, 0.89f, -2.0f, 2.0f);
        pathBuilder2.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(14.0f, 16.0f);
        pathBuilder2.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilder2.reflectiveCurveTo(15.93f, 9.0f, 14.0f, 9.0f);
        pathBuilder2.reflectiveCurveToRelative(-3.5f, 1.57f, -3.5f, 3.5f);
        pathBuilder2.reflectiveCurveTo(12.07f, 16.0f, 14.0f, 16.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(14.0f, 11.0f);
        pathBuilder2.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilder2.reflectiveCurveTo(14.83f, 14.0f, 14.0f, 14.0f);
        pathBuilder2.reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilder2.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilder2.close();
        pathBuilder2.moveTo(6.0f, 5.0f);
        pathBuilder2.lineTo(4.0f, 5.0f);
        pathBuilder2.verticalLineToRelative(16.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(10.0f);
        pathBuilder2.verticalLineToRelative(-2.0f);
        pathBuilder2.lineTo(6.0f, 21.0f);
        pathBuilder2.lineTo(6.0f, 5.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _speakerGroup = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
