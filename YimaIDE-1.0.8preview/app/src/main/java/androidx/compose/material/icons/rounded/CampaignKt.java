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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_campaign", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Campaign", "Landroidx/compose/material/icons/Icons$Rounded;", "getCampaign", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CampaignKt {
    private static ImageVector _campaign;

    public static final ImageVector getCampaign(Icons.Rounded rounded) {
        ImageVector imageVector = _campaign;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Campaign", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.0f, 12.0f);
        pathBuilder.lineTo(18.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder.verticalLineToRelative(0.0f);
        pathBuilder.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.curveTo(18.45f, 11.0f, 18.0f, 11.45f, 18.0f, 12.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(16.59f, 16.82f);
        pathBuilder2.curveToRelative(-0.33f, 0.44f, -0.24f, 1.05f, 0.2f, 1.37f);
        pathBuilder2.curveToRelative(0.53f, 0.39f, 1.09f, 0.81f, 1.62f, 1.21f);
        pathBuilder2.curveToRelative(0.44f, 0.33f, 1.06f, 0.24f, 1.38f, -0.2f);
        pathBuilder2.curveToRelative(0.0f, -0.01f, 0.01f, -0.01f, 0.01f, -0.02f);
        pathBuilder2.curveToRelative(0.33f, -0.44f, 0.24f, -1.06f, -0.2f, -1.38f);
        pathBuilder2.curveToRelative(-0.53f, -0.4f, -1.09f, -0.82f, -1.61f, -1.21f);
        pathBuilder2.curveToRelative(-0.44f, -0.33f, -1.06f, -0.23f, -1.39f, 0.21f);
        pathBuilder2.curveTo(16.6f, 16.81f, 16.59f, 16.82f, 16.59f, 16.82f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(19.81f, 4.81f);
        pathBuilder3.curveToRelative(0.0f, -0.01f, -0.01f, -0.01f, -0.01f, -0.02f);
        pathBuilder3.curveToRelative(-0.33f, -0.44f, -0.95f, -0.53f, -1.38f, -0.2f);
        pathBuilder3.curveToRelative(-0.53f, 0.4f, -1.1f, 0.82f, -1.62f, 1.22f);
        pathBuilder3.curveToRelative(-0.44f, 0.33f, -0.52f, 0.95f, -0.19f, 1.38f);
        pathBuilder3.curveToRelative(0.0f, 0.01f, 0.01f, 0.01f, 0.01f, 0.02f);
        pathBuilder3.curveToRelative(0.33f, 0.44f, 0.94f, 0.53f, 1.38f, 0.2f);
        pathBuilder3.curveToRelative(0.53f, -0.39f, 1.09f, -0.82f, 1.62f, -1.22f);
        pathBuilder3.curveTo(20.05f, 5.87f, 20.13f, 5.25f, 19.81f, 4.81f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(8.0f, 9.0f);
        pathBuilder4.horizontalLineTo(4.0f);
        pathBuilder4.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder4.verticalLineToRelative(2.0f);
        pathBuilder4.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder4.horizontalLineToRelative(1.0f);
        pathBuilder4.verticalLineToRelative(3.0f);
        pathBuilder4.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder4.horizontalLineToRelative(0.0f);
        pathBuilder4.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder4.verticalLineToRelative(-3.0f);
        pathBuilder4.horizontalLineToRelative(1.0f);
        pathBuilder4.lineToRelative(5.0f, 3.0f);
        pathBuilder4.verticalLineTo(6.0f);
        pathBuilder4.lineTo(8.0f, 9.0f);
        pathBuilder4.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i9 = companion2.getButt-KaPHkGw();
        int i10 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder5 = new PathBuilder();
        pathBuilder5.moveTo(15.5f, 12.0f);
        pathBuilder5.curveToRelative(0.0f, -1.33f, -0.58f, -2.53f, -1.5f, -3.35f);
        pathBuilder5.verticalLineToRelative(6.69f);
        pathBuilder5.curveTo(14.92f, 14.53f, 15.5f, 13.33f, 15.5f, 12.0f);
        pathBuilder5.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder5.getNodes(), defaultFillType5, "", solidColor5, 1.0f, (Brush) null, 1.0f, 1.0f, i9, i10, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _campaign = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
