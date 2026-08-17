package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aK\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000e\u001aK\u0010\u000f\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\n2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\u0012\u0010\u0013\u001a9\u0010\u0014\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0010\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a-\u0010\u0017\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\f¢\u0006\u0004\b\u001c\u0010\u001d\u001a1\u0010\u001e\u001a\u00060\u0001j\u0002`\u00022\n\u0010\u001f\u001a\u00060\u0001j\u0002`\u00022\n\u0010 \u001a\u00060\u0001j\u0002`\u00022\u0006\u0010!\u001a\u00020\"¢\u0006\u0004\b#\u0010$¨\u0006%"}, d2 = {"LinearGradientShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "from", "Landroidx/compose/ui/geometry/Offset;", "to", "colors", "", "Landroidx/compose/ui/graphics/Color;", "colorStops", "", "tileMode", "Landroidx/compose/ui/graphics/TileMode;", "LinearGradientShader-VjE6UOU", "(JJLjava/util/List;Ljava/util/List;I)Landroid/graphics/Shader;", "RadialGradientShader", "center", "radius", "RadialGradientShader-8uybcMk", "(JFLjava/util/List;Ljava/util/List;I)Landroid/graphics/Shader;", "SweepGradientShader", "SweepGradientShader-9KIMszo", "(JLjava/util/List;Ljava/util/List;)Landroid/graphics/Shader;", "ImageShader", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "tileModeX", "tileModeY", "ImageShader-F49vj9s", "(Landroidx/compose/ui/graphics/ImageBitmap;II)Landroid/graphics/Shader;", "CompositeShader", "dst", "src", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "CompositeShader-7EN7VTw", "(Landroid/graphics/Shader;Landroid/graphics/Shader;I)Landroid/graphics/Shader;", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ShaderKt {
    /* JADX INFO: renamed from: CompositeShader-7EN7VTw, reason: not valid java name */
    public static final Shader m3465CompositeShader7EN7VTw(Shader shader, Shader shader2, int i) {
        return AndroidShader_androidKt.m3031ActualCompositeShader7EN7VTw(shader, shader2, i);
    }

    /* JADX INFO: renamed from: ImageShader-F49vj9s, reason: not valid java name */
    public static final Shader m3466ImageShaderF49vj9s(ImageBitmap imageBitmap, int i, int i2) {
        return AndroidShader_androidKt.m3032ActualImageShaderF49vj9s(imageBitmap, i, i2);
    }

    /* JADX INFO: renamed from: ImageShader-F49vj9s$default, reason: not valid java name */
    public static /* synthetic */ Shader m3467ImageShaderF49vj9s$default(ImageBitmap imageBitmap, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = TileMode.INSTANCE.m3529getClamp3opZhB0();
        }
        if ((i3 & 4) != 0) {
            i2 = TileMode.INSTANCE.m3529getClamp3opZhB0();
        }
        return m3466ImageShaderF49vj9s(imageBitmap, i, i2);
    }

    /* JADX INFO: renamed from: LinearGradientShader-VjE6UOU, reason: not valid java name */
    public static final Shader m3468LinearGradientShaderVjE6UOU(long j, long j2, List<Color> list, List<Float> list2, int i) {
        return AndroidShader_androidKt.m3033ActualLinearGradientShaderVjE6UOU(j, j2, list, list2, i);
    }

    /* JADX INFO: renamed from: LinearGradientShader-VjE6UOU$default, reason: not valid java name */
    public static /* synthetic */ Shader m3469LinearGradientShaderVjE6UOU$default(long j, long j2, List list, List list2, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            list2 = null;
        }
        List list3 = list2;
        if ((i2 & 16) != 0) {
            i = TileMode.INSTANCE.m3529getClamp3opZhB0();
        }
        return m3468LinearGradientShaderVjE6UOU(j, j2, list, list3, i);
    }

    /* JADX INFO: renamed from: RadialGradientShader-8uybcMk, reason: not valid java name */
    public static final Shader m3470RadialGradientShader8uybcMk(long j, float f, List<Color> list, List<Float> list2, int i) {
        return AndroidShader_androidKt.m3034ActualRadialGradientShader8uybcMk(j, f, list, list2, i);
    }

    /* JADX INFO: renamed from: RadialGradientShader-8uybcMk$default, reason: not valid java name */
    public static /* synthetic */ Shader m3471RadialGradientShader8uybcMk$default(long j, float f, List list, List list2, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            list2 = null;
        }
        List list3 = list2;
        if ((i2 & 16) != 0) {
            i = TileMode.INSTANCE.m3529getClamp3opZhB0();
        }
        return m3470RadialGradientShader8uybcMk(j, f, list, list3, i);
    }

    /* JADX INFO: renamed from: SweepGradientShader-9KIMszo, reason: not valid java name */
    public static final Shader m3472SweepGradientShader9KIMszo(long j, List<Color> list, List<Float> list2) {
        return AndroidShader_androidKt.m3035ActualSweepGradientShader9KIMszo(j, list, list2);
    }

    /* JADX INFO: renamed from: SweepGradientShader-9KIMszo$default, reason: not valid java name */
    public static /* synthetic */ Shader m3473SweepGradientShader9KIMszo$default(long j, List list, List list2, int i, Object obj) {
        if ((i & 4) != 0) {
            list2 = null;
        }
        return m3472SweepGradientShader9KIMszo(j, list, list2);
    }
}
