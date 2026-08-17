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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_folderOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FolderOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFolderOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FolderOffKt {
    private static ImageVector _folderOff;

    public static final ImageVector getFolderOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _folderOff;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FolderOff", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(15.17f, 18.0f);
        pathBuilder.lineToRelative(-11.17f, -11.17f);
        pathBuilder.lineToRelative(0.0f, 11.17f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(11.17f, 8.0f);
        pathBuilder2.lineToRelative(8.83f, 9.17f);
        pathBuilder2.lineToRelative(0.0f, -9.17f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, (Brush) null, 0.3f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(0.69f, 3.51f);
        pathBuilder3.lineToRelative(1.56f, 1.56f);
        pathBuilder3.curveTo(2.1f, 5.35f, 2.01f, 5.66f, 2.01f, 6.0f);
        pathBuilder3.lineTo(2.0f, 18.0f);
        pathBuilder3.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder3.horizontalLineToRelative(13.17f);
        pathBuilder3.lineToRelative(3.31f, 3.31f);
        pathBuilder3.lineToRelative(1.41f, -1.41f);
        pathBuilder3.lineTo(2.1f, 2.1f);
        pathBuilder3.lineTo(0.69f, 3.51f);
        pathBuilder3.close();
        pathBuilder3.moveTo(15.17f, 18.0f);
        pathBuilder3.horizontalLineTo(4.0f);
        pathBuilder3.verticalLineTo(6.83f);
        pathBuilder3.lineTo(15.17f, 18.0f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(20.0f, 6.0f);
        pathBuilder4.horizontalLineToRelative(-8.0f);
        pathBuilder4.lineToRelative(-2.0f, -2.0f);
        pathBuilder4.horizontalLineTo(7.17f);
        pathBuilder4.lineToRelative(4.0f, 4.0f);
        pathBuilder4.horizontalLineTo(20.0f);
        pathBuilder4.verticalLineToRelative(9.17f);
        pathBuilder4.lineToRelative(1.76f, 1.76f);
        pathBuilder4.curveTo(21.91f, 18.65f, 22.0f, 18.34f, 22.0f, 18.0f);
        pathBuilder4.verticalLineTo(8.0f);
        pathBuilder4.curveTo(22.0f, 6.9f, 21.1f, 6.0f, 20.0f, 6.0f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _folderOff = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
