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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fontDownloadOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FontDownloadOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFontDownloadOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FontDownloadOffKt {
    private static ImageVector _fontDownloadOff;

    public static final ImageVector getFontDownloadOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _fontDownloadOff;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FontDownloadOff", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(10.35f, 7.52f);
        pathBuilder.lineTo(10.92f, 6.0f);
        pathBuilder.horizontalLineToRelative(2.14f);
        pathBuilder.lineToRelative(2.55f, 6.79f);
        pathBuilder.lineTo(20.0f, 17.17f);
        pathBuilder.verticalLineTo(4.0f);
        pathBuilder.horizontalLineTo(6.83f);
        pathBuilder.lineTo(10.35f, 7.52f);
        pathBuilder.close();
        pathBuilder.moveTo(12.58f, 9.75f);
        pathBuilder.lineToRelative(-0.54f, -1.52f);
        pathBuilder.horizontalLineToRelative(-0.1f);
        pathBuilder.lineToRelative(-0.23f, 0.66f);
        pathBuilder.lineTo(12.58f, 9.75f);
        pathBuilder.close();
        pathBuilder.moveTo(17.17f, 20.0f);
        pathBuilder.lineToRelative(-5.07f, -5.07f);
        pathBuilder.horizontalLineTo(9.58f);
        pathBuilder.lineTo(8.49f, 18.0f);
        pathBuilder.horizontalLineTo(6.41f);
        pathBuilder.lineToRelative(2.39f, -6.37f);
        pathBuilder.lineTo(4.0f, 6.83f);
        pathBuilder.verticalLineTo(20.0f);
        pathBuilder.horizontalLineTo(17.17f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(4.83f, 2.0f);
        pathBuilder2.horizontalLineTo(20.0f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilder2.verticalLineToRelative(15.17f);
        pathBuilder2.lineToRelative(-2.0f, -2.0f);
        pathBuilder2.verticalLineTo(4.0f);
        pathBuilder2.horizontalLineTo(6.83f);
        pathBuilder2.lineTo(4.83f, 2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(10.92f, 6.0f);
        pathBuilder2.lineToRelative(-0.57f, 1.52f);
        pathBuilder2.lineToRelative(1.36f, 1.36f);
        pathBuilder2.lineToRelative(0.23f, -0.66f);
        pathBuilder2.horizontalLineToRelative(0.1f);
        pathBuilder2.lineToRelative(0.54f, 1.52f);
        pathBuilder2.lineToRelative(3.04f, 3.04f);
        pathBuilder2.lineTo(13.07f, 6.0f);
        pathBuilder2.horizontalLineTo(10.92f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.49f, 23.31f);
        pathBuilder2.lineTo(19.17f, 22.0f);
        pathBuilder2.horizontalLineTo(4.0f);
        pathBuilder2.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder2.verticalLineTo(4.83f);
        pathBuilder2.lineTo(0.69f, 3.51f);
        pathBuilder2.lineTo(2.1f, 2.1f);
        pathBuilder2.lineToRelative(19.8f, 19.8f);
        pathBuilder2.lineTo(20.49f, 23.31f);
        pathBuilder2.close();
        pathBuilder2.moveTo(17.17f, 20.0f);
        pathBuilder2.lineToRelative(-5.07f, -5.07f);
        pathBuilder2.horizontalLineTo(9.58f);
        pathBuilder2.lineTo(8.49f, 18.0f);
        pathBuilder2.horizontalLineTo(6.41f);
        pathBuilder2.lineToRelative(2.39f, -6.37f);
        pathBuilder2.lineTo(4.0f, 6.83f);
        pathBuilder2.verticalLineTo(20.0f);
        pathBuilder2.horizontalLineTo(17.17f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _fontDownloadOff = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
