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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_savedSearch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SavedSearch", "Landroidx/compose/material/icons/Icons$Rounded;", "getSavedSearch", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SavedSearchKt {
    private static ImageVector _savedSearch;

    public static final ImageVector getSavedSearch(Icons.Rounded rounded) {
        ImageVector imageVector = _savedSearch;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SavedSearch", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(14.73f, 13.31f);
        pathBuilder.curveToRelative(1.13f, -1.55f, 1.63f, -3.58f, 0.98f, -5.74f);
        pathBuilder.curveToRelative(-0.68f, -2.23f, -2.57f, -3.98f, -4.85f, -4.44f);
        pathBuilder.curveTo(6.21f, 2.2f, 2.2f, 6.22f, 3.14f, 10.86f);
        pathBuilder.curveToRelative(0.46f, 2.29f, 2.21f, 4.18f, 4.44f, 4.85f);
        pathBuilder.curveToRelative(2.16f, 0.65f, 4.19f, 0.15f, 5.74f, -0.98f);
        pathBuilder.lineToRelative(5.56f, 5.56f);
        pathBuilder.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilder.lineTo(14.73f, 13.31f);
        pathBuilder.close();
        pathBuilder.moveTo(9.5f, 14.0f);
        pathBuilder.curveTo(7.01f, 14.0f, 5.0f, 11.99f, 5.0f, 9.5f);
        pathBuilder.reflectiveCurveTo(7.01f, 5.0f, 9.5f, 5.0f);
        pathBuilder.reflectiveCurveTo(14.0f, 7.01f, 14.0f, 9.5f);
        pathBuilder.reflectiveCurveTo(11.99f, 14.0f, 9.5f, 14.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(10.29f, 8.44f);
        pathBuilder2.lineToRelative(-0.79f, -2.44f);
        pathBuilder2.lineToRelative(-0.79f, 2.44f);
        pathBuilder2.lineToRelative(-2.46f, 0.0f);
        pathBuilder2.lineToRelative(2.01f, 1.59f);
        pathBuilder2.lineToRelative(-0.77f, 2.47f);
        pathBuilder2.lineToRelative(2.01f, -1.53f);
        pathBuilder2.lineToRelative(2.01f, 1.53f);
        pathBuilder2.lineToRelative(-0.77f, -2.47f);
        pathBuilder2.lineToRelative(2.01f, -1.59f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _savedSearch = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
