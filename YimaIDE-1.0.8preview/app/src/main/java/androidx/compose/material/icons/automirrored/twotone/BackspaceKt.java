package androidx.compose.material.icons.automirrored.twotone;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_backspace", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Backspace", "Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;", "getBackspace", "(Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BackspaceKt {
    private static ImageVector _backspace;

    public static final ImageVector getBackspace(Icons.AutoMirrored.TwoTone twoTone) {
        ImageVector imageVector = _backspace;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.TwoTone.Backspace", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(7.06f, 5.0f);
        pathBuilder.lineTo(2.4f, 12.0f);
        pathBuilder.lineToRelative(4.67f, 7.0f);
        pathBuilder.horizontalLineTo(22.0f);
        pathBuilder.verticalLineTo(5.0f);
        pathBuilder.horizontalLineTo(7.06f);
        pathBuilder.curveToRelative(0.01f, 0.0f, 0.01f, 0.0f, 0.0f, 0.0f);
        pathBuilder.close();
        pathBuilder.moveTo(9.0f, 8.41f);
        pathBuilder.lineTo(10.41f, 7.0f);
        pathBuilder.lineTo(14.0f, 10.59f);
        pathBuilder.lineTo(17.59f, 7.0f);
        pathBuilder.lineTo(19.0f, 8.41f);
        pathBuilder.lineTo(15.41f, 12.0f);
        pathBuilder.lineTo(19.0f, 15.59f);
        pathBuilder.lineTo(17.59f, 17.0f);
        pathBuilder.lineTo(14.0f, 13.41f);
        pathBuilder.lineTo(10.41f, 17.0f);
        pathBuilder.lineTo(9.0f, 15.59f);
        pathBuilder.lineTo(12.59f, 12.0f);
        pathBuilder.lineTo(9.0f, 8.41f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(22.0f, 3.0f);
        pathBuilder2.lineTo(7.0f, 3.0f);
        pathBuilder2.curveToRelative(-0.69f, 0.0f, -1.23f, 0.35f, -1.59f, 0.88f);
        pathBuilder2.lineTo(0.0f, 12.0f);
        pathBuilder2.lineToRelative(5.41f, 8.11f);
        pathBuilder2.curveToRelative(0.36f, 0.53f, 0.9f, 0.89f, 1.59f, 0.89f);
        pathBuilder2.horizontalLineToRelative(15.0f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder2.lineTo(24.0f, 5.0f);
        pathBuilder2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(22.0f, 19.0f);
        pathBuilder2.lineTo(7.07f, 19.0f);
        pathBuilder2.lineTo(2.4f, 12.0f);
        pathBuilder2.lineToRelative(4.66f, -7.0f);
        pathBuilder2.lineTo(22.0f, 5.0f);
        pathBuilder2.verticalLineToRelative(14.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(10.41f, 17.0f);
        pathBuilder2.lineTo(14.0f, 13.41f);
        pathBuilder2.lineTo(17.59f, 17.0f);
        pathBuilder2.lineTo(19.0f, 15.59f);
        pathBuilder2.lineTo(15.41f, 12.0f);
        pathBuilder2.lineTo(19.0f, 8.41f);
        pathBuilder2.lineTo(17.59f, 7.0f);
        pathBuilder2.lineTo(14.0f, 10.59f);
        pathBuilder2.lineTo(10.41f, 7.0f);
        pathBuilder2.lineTo(9.0f, 8.41f);
        pathBuilder2.lineTo(12.59f, 12.0f);
        pathBuilder2.lineTo(9.0f, 15.59f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _backspace = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
