package androidx.compose.ui.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.TypedValue;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorPainterKt;
import androidx.compose.ui.graphics.vector.compat.XmlVectorParser_androidKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.archive.PathTree;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0001\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a1\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bR\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010\f\u001a \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"painterResource", "Landroidx/compose/ui/graphics/painter/Painter;", "id", "", "(ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/painter/Painter;", "loadVectorResource", "Landroidx/compose/ui/graphics/vector/ImageVector;", "theme", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "res", "changingConfigurations", "(Landroid/content/res/Resources$Theme;Landroid/content/res/Resources;IILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/vector/ImageVector;", "loadImageBitmapResource", "Landroidx/compose/ui/graphics/ImageBitmap;", PathTree.NAME_path, "", "errorMessage", "", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PainterResources_androidKt {
    private static final String errorMessage = "Only VectorDrawables and rasterized asset types are supported ex. PNG, JPG, WEBP";

    private static final ImageBitmap loadImageBitmapResource(CharSequence charSequence, Resources resources, int i) {
        try {
            return ImageResources_androidKt.imageResource(ImageBitmap.INSTANCE, resources, i);
        } catch (Exception e) {
            throw new ResourceResolutionException("Error attempting to load resource: " + ((Object) charSequence), e);
        }
    }

    private static final ImageVector loadVectorResource(Resources.Theme theme, Resources resources, int i, int i2, Composer composer, int i3) throws XmlPullParserException, IOException {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(21855625, i3, -1, "androidx.compose.ui.res.loadVectorResource (PainterResources.android.kt:87)");
        }
        ImageVectorCache imageVectorCache = (ImageVectorCache) composer.consume(AndroidCompositionLocals_androidKt.getLocalImageVectorCache());
        ImageVectorCache.Key key = new ImageVectorCache.Key(theme, i);
        ImageVectorCache.ImageVectorEntry imageVectorEntryLoadVectorResourceInner = imageVectorCache.get(key);
        if (imageVectorEntryLoadVectorResourceInner == null) {
            XmlResourceParser xml = resources.getXml(i);
            if (!Intrinsics.areEqual(XmlVectorParser_androidKt.seekToStartTag(xml).getName(), "vector")) {
                w01.a(errorMessage);
                return null;
            }
            imageVectorEntryLoadVectorResourceInner = VectorResources_androidKt.loadVectorResourceInner(theme, resources, xml, i2);
            imageVectorCache.set(key, imageVectorEntryLoadVectorResourceInner);
        }
        ImageVector imageVector = imageVectorEntryLoadVectorResourceInner.getImageVector();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return imageVector;
    }

    public static final Painter painterResource(int i, Composer composer, int i2) {
        Painter painterRememberVectorPainter;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(473971343, i2, -1, "androidx.compose.ui.res.painterResource (PainterResources.android.kt:56)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        Resources resources = (Resources) composer.consume(AndroidCompositionLocals_androidKt.getLocalResources());
        TypedValue typedValueResolveResourcePath = ((ResourceIdCache) composer.consume(AndroidCompositionLocals_androidKt.getLocalResourceIdCache())).resolveResourcePath(resources, i);
        CharSequence charSequence = typedValueResolveResourcePath.string;
        boolean z = true;
        if (charSequence == null || !StringsKt.endsWith$default(charSequence, ".xml", false, 2, (Object) null)) {
            composer.startReplaceGroup(-1771643000);
            Object theme = context.getTheme();
            boolean zChanged = composer.changed(charSequence);
            if ((((i2 & 14) ^ 6) <= 4 || !composer.changed(i)) && (i2 & 6) != 4) {
                z = false;
            }
            boolean zChanged2 = composer.changed(theme) | zChanged | z;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = loadImageBitmapResource(charSequence, resources, i);
                composer.updateRememberedValue(objRememberedValue);
            }
            BitmapPainter bitmapPainter = new BitmapPainter((ImageBitmap) objRememberedValue, 0L, 0L, 6, null);
            composer.endReplaceGroup();
            painterRememberVectorPainter = bitmapPainter;
        } else {
            composer.startReplaceGroup(-1771798434);
            painterRememberVectorPainter = VectorPainterKt.rememberVectorPainter(loadVectorResource(context.getTheme(), resources, i, typedValueResolveResourcePath.changingConfigurations, composer, (i2 << 6) & 896), composer, 0);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return painterRememberVectorPainter;
    }
}
