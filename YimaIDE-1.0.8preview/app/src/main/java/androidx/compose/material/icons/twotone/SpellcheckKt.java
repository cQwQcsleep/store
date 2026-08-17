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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_spellcheck", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Spellcheck", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSpellcheck", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpellcheckKt {
    private static ImageVector _spellcheck;

    public static final ImageVector getSpellcheck(Icons.TwoTone twoTone) {
        ImageVector imageVector = _spellcheck;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Spellcheck", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.45f, 16.0f);
        pathBuilder.horizontalLineToRelative(2.09f);
        pathBuilder.lineTo(9.43f, 3.0f);
        pathBuilder.lineTo(7.57f, 3.0f);
        pathBuilder.lineTo(2.46f, 16.0f);
        pathBuilder.horizontalLineToRelative(2.09f);
        pathBuilder.lineToRelative(1.12f, -3.0f);
        pathBuilder.horizontalLineToRelative(5.64f);
        pathBuilder.lineToRelative(1.14f, 3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(6.43f, 11.0f);
        pathBuilder.lineTo(8.5f, 5.48f);
        pathBuilder.lineTo(10.57f, 11.0f);
        pathBuilder.lineTo(6.43f, 11.0f);
        pathBuilder.close();
        pathBuilder.moveTo(21.59f, 11.59f);
        pathBuilder.lineToRelative(-8.09f, 8.09f);
        pathBuilder.lineTo(9.83f, 16.0f);
        pathBuilder.lineToRelative(-1.41f, 1.41f);
        pathBuilder.lineToRelative(5.09f, 5.09f);
        pathBuilder.lineTo(23.0f, 13.0f);
        pathBuilder.lineToRelative(-1.41f, -1.41f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _spellcheck = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
