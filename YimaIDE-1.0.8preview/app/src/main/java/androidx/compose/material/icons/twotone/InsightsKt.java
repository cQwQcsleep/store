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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_insights", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Insights", "Landroidx/compose/material/icons/Icons$TwoTone;", "getInsights", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InsightsKt {
    private static ImageVector _insights;

    public static final ImageVector getInsights(Icons.TwoTone twoTone) {
        ImageVector imageVector = _insights;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Insights", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(21.0f, 8.0f);
        pathBuilder.curveToRelative(-1.45f, 0.0f, -2.26f, 1.44f, -1.93f, 2.51f);
        pathBuilder.lineToRelative(-3.55f, 3.56f);
        pathBuilder.curveToRelative(-0.3f, -0.09f, -0.74f, -0.09f, -1.04f, 0.0f);
        pathBuilder.lineToRelative(-2.55f, -2.55f);
        pathBuilder.curveTo(12.27f, 10.45f, 11.46f, 9.0f, 10.0f, 9.0f);
        pathBuilder.curveToRelative(-1.45f, 0.0f, -2.27f, 1.44f, -1.93f, 2.52f);
        pathBuilder.lineToRelative(-4.56f, 4.55f);
        pathBuilder.curveTo(2.44f, 15.74f, 1.0f, 16.55f, 1.0f, 18.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.curveToRelative(1.45f, 0.0f, 2.26f, -1.44f, 1.93f, -2.51f);
        pathBuilder.lineToRelative(4.55f, -4.56f);
        pathBuilder.curveToRelative(0.3f, 0.09f, 0.74f, 0.09f, 1.04f, 0.0f);
        pathBuilder.lineToRelative(2.55f, 2.55f);
        pathBuilder.curveTo(12.73f, 16.55f, 13.54f, 18.0f, 15.0f, 18.0f);
        pathBuilder.curveToRelative(1.45f, 0.0f, 2.27f, -1.44f, 1.93f, -2.52f);
        pathBuilder.lineToRelative(3.56f, -3.55f);
        pathBuilder.curveTo(21.56f, 12.26f, 23.0f, 11.45f, 23.0f, 10.0f);
        pathBuilder.curveTo(23.0f, 8.9f, 22.1f, 8.0f, 21.0f, 8.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(15.0f, 9.0f);
        pathBuilder2.lineToRelative(0.94f, -2.07f);
        pathBuilder2.lineToRelative(2.06f, -0.93f);
        pathBuilder2.lineToRelative(-2.06f, -0.93f);
        pathBuilder2.lineToRelative(-0.94f, -2.07f);
        pathBuilder2.lineToRelative(-0.92f, 2.07f);
        pathBuilder2.lineToRelative(-2.08f, 0.93f);
        pathBuilder2.lineToRelative(2.08f, 0.93f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(3.5f, 11.0f);
        pathBuilder3.lineToRelative(0.5f, -2.0f);
        pathBuilder3.lineToRelative(2.0f, -0.5f);
        pathBuilder3.lineToRelative(-2.0f, -0.5f);
        pathBuilder3.lineToRelative(-0.5f, -2.0f);
        pathBuilder3.lineToRelative(-0.5f, 2.0f);
        pathBuilder3.lineToRelative(-2.0f, 0.5f);
        pathBuilder3.lineToRelative(2.0f, 0.5f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _insights = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
