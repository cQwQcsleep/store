package androidx.compose.ui.graphics.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.RectF;
import android.os.Build;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawContextKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import com.reandroid.archive.PathTree;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000â\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 »\u00012\u00020\u0001:\u0002»\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010w\u001a\u00020\u000f2\u0006\u00105\u001a\u0002042\u0006\u0010;\u001a\u00020:H\u0002¢\u0006\u0004\bx\u0010yJ>\u0010z\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020:2\u0017\u0010{\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010¢\u0006\u0004\b|\u0010}J\b\u0010~\u001a\u00020\u000fH\u0002J\f\u0010\u007f\u001a\u00020\u000f*\u00020\u000eH\u0002J\u0012\u0010\u0080\u0001\u001a\u00020\u000f2\u0007\u0010\u0081\u0001\u001a\u00020\u0000H\u0002J\u0013\u0010\u0082\u0001\u001a\u00020\u000f2\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0002J\u0019\u0010\u0085\u0001\u001a\u00020\u000f2\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0000¢\u0006\u0003\b\u0088\u0001J\t\u0010\u0089\u0001\u001a\u00020\u000fH\u0002J$\u0010\u008a\u0001\u001a\u00020\u000f2\b\u0010\u0086\u0001\u001a\u00030\u0087\u00012\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0003\b\u008c\u0001J\t\u0010\u008d\u0001\u001a\u00020\u000fH\u0002J\t\u0010\u008e\u0001\u001a\u00020\u000fH\u0002J\n\u0010\u0091\u0001\u001a\u00030\u0090\u0001H\u0002J\t\u0010\u0092\u0001\u001a\u00020\u000fH\u0002J4\u0010\u0093\u0001\u001a\u0003H\u0094\u0001\"\u0005\b\u0000\u0010\u0094\u00012\u001a\u0010{\u001a\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001a\u0012\u0005\u0012\u0003H\u0094\u00010\u0095\u0001H\u0082\b¢\u0006\u0003\u0010\u0096\u0001J\u0014\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u00132\u0007\u0010\u0098\u0001\u001a\u00020 H\u0002J\t\u0010\u0099\u0001\u001a\u00020\u0013H\u0002J\u000f\u0010\u009a\u0001\u001a\u00020\u000fH\u0000¢\u0006\u0003\b\u009b\u0001J\t\u0010\u009c\u0001\u001a\u00020\u000fH\u0002J\u000f\u0010\u009d\u0001\u001a\u00020\u000fH\u0000¢\u0006\u0003\b\u009e\u0001J\u000f\u0010\u009f\u0001\u001a\u00020\u000fH\u0001¢\u0006\u0003\b \u0001J\t\u0010©\u0001\u001a\u00020\u000fH\u0002J\u0010\u0010ª\u0001\u001a\u00020\u000f2\u0007\u0010\u0098\u0001\u001a\u00020 J/\u0010«\u0001\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u00172\b\b\u0002\u0010;\u001a\u00020\u001a2\t\b\u0002\u0010¬\u0001\u001a\u00020\u001c¢\u0006\u0006\b\u00ad\u0001\u0010®\u0001J#\u0010¯\u0001\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u00172\b\b\u0002\u0010;\u001a\u00020\u001a¢\u0006\u0005\b°\u0001\u0010yJ\u0011\u0010¸\u0001\u001a\u00030¹\u0001H\u0086@¢\u0006\u0003\u0010º\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010,\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R$\u0010/\u001a\u00020.2\u0006\u0010+\u001a\u00020.8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b0\u00101\"\u0004\b2\u00103R&\u00105\u001a\u0002042\u0006\u0010+\u001a\u000204@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b6\u00107\"\u0004\b8\u00109R&\u0010;\u001a\u00020:2\u0006\u0010+\u001a\u00020:@BX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b<\u00107\"\u0004\b=\u00109R$\u0010>\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR$\u0010D\u001a\u00020C2\u0006\u0010+\u001a\u00020C8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bE\u00101\"\u0004\bF\u00103R(\u0010H\u001a\u0004\u0018\u00010G2\b\u0010+\u001a\u0004\u0018\u00010G8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR&\u0010M\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u0017@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\bN\u00107\"\u0004\bO\u00109R$\u0010P\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bQ\u0010@\"\u0004\bR\u0010BR$\u0010S\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bT\u0010@\"\u0004\bU\u0010BR$\u0010V\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bW\u0010@\"\u0004\bX\u0010BR$\u0010Y\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bZ\u0010@\"\u0004\b[\u0010BR$\u0010\\\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b]\u0010@\"\u0004\b^\u0010BR$\u0010_\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b`\u0010@\"\u0004\ba\u0010BR$\u0010b\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bc\u0010@\"\u0004\bd\u0010BR$\u0010e\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bf\u0010@\"\u0004\bg\u0010BR$\u0010h\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bi\u0010@\"\u0004\bj\u0010BR*\u0010k\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0015@FX\u0086\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bl\u0010m\u001a\u0004\bn\u0010-\"\u0004\bo\u0010pR(\u0010r\u001a\u0004\u0018\u00010q2\b\u0010+\u001a\u0004\u0018\u00010q8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\u0012\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0090\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010¡\u0001\u001a\u00030¢\u00018F¢\u0006\u0007\u001a\u0005\b£\u0001\u00107R\u0014\u0010¤\u0001\u001a\u00030¢\u00018F¢\u0006\u0007\u001a\u0005\b¥\u0001\u00107R\u0014\u0010¦\u0001\u001a\u00020\u001e8F¢\u0006\b\u001a\u0006\b§\u0001\u0010¨\u0001R)\u0010²\u0001\u001a\u00030±\u00012\u0007\u0010+\u001a\u00030±\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b³\u0001\u00107\"\u0005\b´\u0001\u00109R)\u0010µ\u0001\u001a\u00030±\u00012\u0007\u0010+\u001a\u00030±\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b¶\u0001\u00107\"\u0005\b·\u0001\u00109¨\u0006¼\u0001"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "", "impl", "Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "<init>", "(Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;)V", "getImpl$ui_graphics", "()Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "drawBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "clipDrawBlock", "androidOutline", "Landroid/graphics/Outline;", "outlineDirty", "", "roundRectOutlineTopLeft", "Landroidx/compose/ui/geometry/Offset;", "J", "roundRectOutlineSize", "Landroidx/compose/ui/geometry/Size;", "roundRectCornerRadius", "", "internalOutline", "Landroidx/compose/ui/graphics/Outline;", "outlinePath", "Landroidx/compose/ui/graphics/Path;", "roundRectClipPath", "usePathForClip", "softwareDrawScope", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "softwareLayerPaint", "Landroidx/compose/ui/graphics/Paint;", "parentLayerUsages", "", "childDependenciesTracker", "Landroidx/compose/ui/graphics/layer/ChildLayerDependenciesTracker;", "value", "isReleased", "()Z", "Landroidx/compose/ui/graphics/layer/CompositingStrategy;", "compositingStrategy", "getCompositingStrategy-ke2Ky5w", "()I", "setCompositingStrategy-Wpw9cng", "(I)V", "Landroidx/compose/ui/unit/IntOffset;", "topLeft", "getTopLeft-nOcc-ac", "()J", "setTopLeft--gyyYBs", "(J)V", "Landroidx/compose/ui/unit/IntSize;", "size", "getSize-YbymL2g", "setSize-ozmzZPI", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "getBlendMode-0nO6VwU", "setBlendMode-s9anfk8", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "pivotOffset", "getPivotOffset-F1C5BW0", "setPivotOffset-k-4lQ0M", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "shadowElevation", "getShadowElevation", "setShadowElevation", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "cameraDistance", "getCameraDistance", "setCameraDistance", "clip", "getClip$annotations", "()V", "getClip", "setClip", "(Z)V", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "setPosition", "setPosition-VbeCjmY", "(JJ)V", "record", "block", "record-mL-hObY", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;JLkotlin/jvm/functions/Function1;)V", "recordInternal", "drawWithChildTracking", "addSubLayer", "graphicsLayer", "transformCanvas", "androidCanvas", "Landroid/graphics/Canvas;", "drawForPersistence", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "drawForPersistence$ui_graphics", "recreateDisplayListIfNeeded", "draw", "parentLayer", "draw$ui_graphics", "onAddedToParentLayer", "onRemovedFromParentLayer", "pathBounds", "Landroid/graphics/RectF;", "obtainPathBounds", "configureOutlineAndClip", "resolveOutlinePosition", "T", "Lkotlin/Function2;", "(Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "updatePathOutline", PathTree.NAME_path, "obtainAndroidOutline", "release", "release$ui_graphics", "discardContentIfReleasedAndHaveNoParentLayerUsages", "discardDisplayList", "discardDisplayList$ui_graphics", "emulateTrimMemory", "emulateTrimMemory$ui_graphics", "layerId", "", "getLayerId", "ownerViewId", "getOwnerViewId", "outline", "getOutline", "()Landroidx/compose/ui/graphics/Outline;", "resetOutlineParams", "setPathOutline", "setRoundRectOutline", "cornerRadius", "setRoundRectOutline-TNW_H78", "(JJF)V", "setRectOutline", "setRectOutline-tz77jQw", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "getAmbientShadowColor-0d7_KjU", "setAmbientShadowColor-8_81llA", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "toImageBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class GraphicsLayer {
    private static final LayerSnapshotImpl SnapshotImpl;
    private static final boolean isRobolectric;
    private Outline androidOutline;
    private final ChildLayerDependenciesTracker childDependenciesTracker;
    private boolean clip;
    private final GraphicsLayerImpl impl;
    private androidx.compose.ui.graphics.Outline internalOutline;
    private boolean isReleased;
    private Path outlinePath;
    private int parentLayerUsages;
    private RectF pathBounds;
    private long pivotOffset;
    private Path roundRectClipPath;
    private float roundRectCornerRadius;
    private long roundRectOutlineSize;
    private long roundRectOutlineTopLeft;
    private long size;
    private CanvasDrawScope softwareDrawScope;
    private Paint softwareLayerPaint;
    private long topLeft;
    private boolean usePathForClip;
    public static final int $stable = 8;
    private Density density = DrawContextKt.getDefaultDensity();
    private LayoutDirection layoutDirection = LayoutDirection.Ltr;
    private Function1<? super DrawScope, Unit> drawBlock = new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.layer.GraphicsLayer$drawBlock$1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DrawScope) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(DrawScope drawScope) {
        }
    };
    private final Function1<DrawScope, Unit> clipDrawBlock = new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.layer.GraphicsLayer$clipDrawBlock$1
        {
            super(1);
        }

        public final void invoke(DrawScope drawScope) {
            Path path = this.this$0.outlinePath;
            if (!this.this$0.usePathForClip || !this.this$0.getClip() || path == null) {
                this.this$0.drawWithChildTracking(drawScope);
                return;
            }
            GraphicsLayer graphicsLayer = this.this$0;
            int iM3123getIntersectrtfAjoo = ClipOp.INSTANCE.m3123getIntersectrtfAjoo();
            DrawContext drawContext = drawScope.getDrawContext();
            long jMo3629getSizeNHjbRc = drawContext.mo3629getSizeNHjbRc();
            drawContext.getCanvas().save();
            try {
                drawContext.getTransform().mo3631clipPathmtrdDE(path, iM3123getIntersectrtfAjoo);
                graphicsLayer.drawWithChildTracking(drawScope);
            } finally {
                drawContext.getCanvas().restore();
                drawContext.mo3630setSizeuvyYCjk(jMo3629getSizeNHjbRc);
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DrawScope) obj);
            return Unit.INSTANCE;
        }
    };
    private boolean outlineDirty = true;

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.ui.graphics.layer.GraphicsLayer", f = "AndroidGraphicsLayer.android.kt", i = {}, l = {869}, m = "toImageBitmap", n = {}, s = {}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GraphicsLayer.this.toImageBitmap(this);
        }
    }

    static {
        String lowerCase = Build.FINGERPRINT.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        boolean zAreEqual = Intrinsics.areEqual(lowerCase, "robolectric");
        isRobolectric = zAreEqual;
        SnapshotImpl = zAreEqual ? LayerSnapshotV21.INSTANCE : LayerSnapshotV28.INSTANCE;
    }

    public GraphicsLayer(GraphicsLayerImpl graphicsLayerImpl) {
        this.impl = graphicsLayerImpl;
        Offset.Companion companion = Offset.INSTANCE;
        this.roundRectOutlineTopLeft = companion.m2905getZeroF1C5BW0();
        this.roundRectOutlineSize = Size.INSTANCE.m2966getUnspecifiedNHjbRc();
        this.childDependenciesTracker = new ChildLayerDependenciesTracker();
        graphicsLayerImpl.setClip(false);
        this.topLeft = IntOffset.INSTANCE.m6161getZeronOccac();
        this.size = IntSize.INSTANCE.m6198getZeroYbymL2g();
        this.pivotOffset = companion.m2904getUnspecifiedF1C5BW0();
    }

    private final void addSubLayer(GraphicsLayer graphicsLayer) {
        if (this.childDependenciesTracker.onDependencyAdded(graphicsLayer)) {
            graphicsLayer.onAddedToParentLayer();
        }
    }

    private final void configureOutlineAndClip() {
        if (this.outlineDirty) {
            Outline outline = null;
            if (this.clip || getShadowElevation() > 0.0f) {
                Path path = this.outlinePath;
                if (path != null) {
                    RectF rectFObtainPathBounds = obtainPathBounds();
                    if (!(path instanceof AndroidPath)) {
                        c41.a("Unable to obtain android.graphics.Path");
                        return;
                    }
                    ((AndroidPath) path).getInternalPath().computeBounds(rectFObtainPathBounds, false);
                    Outline outlineUpdatePathOutline = updatePathOutline(path);
                    if (outlineUpdatePathOutline != null) {
                        outlineUpdatePathOutline.setAlpha(getAlpha());
                        outline = outlineUpdatePathOutline;
                    }
                    this.impl.mo3819setOutlineO0kMr_c(outline, IntSize.m6188constructorimpl((4294967295L & ((long) Math.round(rectFObtainPathBounds.height()))) | (((long) Math.round(rectFObtainPathBounds.width())) << 32)));
                    if (this.usePathForClip && this.clip) {
                        this.impl.setClip(false);
                        this.impl.discardDisplayList();
                    } else {
                        this.impl.setClip(this.clip);
                    }
                } else {
                    this.impl.setClip(this.clip);
                    Size.INSTANCE.m2967getZeroNHjbRc();
                    Outline outlineObtainAndroidOutline = obtainAndroidOutline();
                    long jM6205toSizeozmzZPI = IntSizeKt.m6205toSizeozmzZPI(this.size);
                    long j = this.roundRectOutlineTopLeft;
                    long j2 = this.roundRectOutlineSize;
                    long j3 = j2 == InlineClassHelperKt.UnspecifiedPackedFloats ? jM6205toSizeozmzZPI : j2;
                    int i = (int) (j >> 32);
                    int i2 = (int) (j & 4294967295L);
                    outlineObtainAndroidOutline.setRoundRect(Math.round(Float.intBitsToFloat(i)), Math.round(Float.intBitsToFloat(i2)), Math.round(Float.intBitsToFloat(i) + Float.intBitsToFloat((int) (j3 >> 32))), Math.round(Float.intBitsToFloat(i2) + Float.intBitsToFloat((int) (j3 & 4294967295L))), this.roundRectCornerRadius);
                    outlineObtainAndroidOutline.setAlpha(getAlpha());
                    this.impl.mo3819setOutlineO0kMr_c(outlineObtainAndroidOutline, IntSizeKt.m6201roundToIntSizeuvyYCjk(j3));
                }
            } else {
                this.impl.setClip(false);
                this.impl.mo3819setOutlineO0kMr_c(null, IntSize.INSTANCE.m6198getZeroYbymL2g());
            }
        }
        this.outlineDirty = false;
    }

    private final void discardContentIfReleasedAndHaveNoParentLayerUsages() {
        if (this.isReleased && this.parentLayerUsages == 0) {
            discardDisplayList$ui_graphics();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:29:0x0089 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x008b A[LOOP:0: B:20:0x0054->B:30:0x008b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:34:0x008e A[EDGE_INSN: B:34:0x008e->B:31:0x008e BREAK  A[LOOP:0: B:20:0x0054->B:30:0x008b], SYNTHETIC] */
    public final void drawWithChildTracking(DrawScope drawScope) {
        ChildLayerDependenciesTracker childLayerDependenciesTracker = this.childDependenciesTracker;
        childLayerDependenciesTracker.oldDependency = childLayerDependenciesTracker.dependency;
        MutableScatterSet mutableScatterSet = childLayerDependenciesTracker.dependenciesSet;
        if (mutableScatterSet != null && mutableScatterSet.isNotEmpty()) {
            MutableScatterSet mutableScatterSetMutableScatterSetOf = childLayerDependenciesTracker.oldDependenciesSet;
            if (mutableScatterSetMutableScatterSetOf == null) {
                mutableScatterSetMutableScatterSetOf = ScatterSetKt.mutableScatterSetOf();
                childLayerDependenciesTracker.oldDependenciesSet = mutableScatterSetMutableScatterSetOf;
            }
            mutableScatterSetMutableScatterSetOf.addAll(mutableScatterSet);
            mutableScatterSet.clear();
        }
        childLayerDependenciesTracker.trackingInProgress = true;
        this.drawBlock.invoke(drawScope);
        childLayerDependenciesTracker.trackingInProgress = false;
        GraphicsLayer graphicsLayer = childLayerDependenciesTracker.oldDependency;
        if (graphicsLayer != null) {
            graphicsLayer.onRemovedFromParentLayer();
        }
        MutableScatterSet mutableScatterSet2 = childLayerDependenciesTracker.oldDependenciesSet;
        if (mutableScatterSet2 == null || !mutableScatterSet2.isNotEmpty()) {
            return;
        }
        Object[] objArr = ((ScatterSet) mutableScatterSet2).elements;
        long[] jArr = ((ScatterSet) mutableScatterSet2).metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i != length) {
                        break;
                        break;
                    }
                    i++;
                } else {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            ((GraphicsLayer) objArr[(i << 3) + i3]).onRemovedFromParentLayer();
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    } else if (i != length) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        mutableScatterSet2.clear();
    }

    public static /* synthetic */ void getClip$annotations() {
    }

    private final Outline obtainAndroidOutline() {
        Outline outline = this.androidOutline;
        if (outline != null) {
            return outline;
        }
        Outline outline2 = new Outline();
        this.androidOutline = outline2;
        return outline2;
    }

    private final RectF obtainPathBounds() {
        RectF rectF = this.pathBounds;
        if (rectF != null) {
            return rectF;
        }
        RectF rectF2 = new RectF();
        this.pathBounds = rectF2;
        return rectF2;
    }

    private final void onAddedToParentLayer() {
        this.parentLayerUsages++;
    }

    private final void onRemovedFromParentLayer() {
        this.parentLayerUsages--;
        discardContentIfReleasedAndHaveNoParentLayerUsages();
    }

    private final void recordInternal() {
        this.impl.record(this.density, this.layoutDirection, this, this.clipDrawBlock);
    }

    private final void recreateDisplayListIfNeeded() {
        if (this.impl.getHasDisplayList()) {
            return;
        }
        try {
            recordInternal();
        } catch (Throwable unused) {
        }
    }

    private final void resetOutlineParams() {
        this.internalOutline = null;
        this.outlinePath = null;
        this.roundRectOutlineSize = Size.INSTANCE.m2966getUnspecifiedNHjbRc();
        this.roundRectOutlineTopLeft = Offset.INSTANCE.m2905getZeroF1C5BW0();
        this.roundRectCornerRadius = 0.0f;
        this.outlineDirty = true;
        this.usePathForClip = false;
    }

    private final <T> T resolveOutlinePosition(Function2<? super Offset, ? super Size, ? extends T> block) {
        long jM6205toSizeozmzZPI = IntSizeKt.m6205toSizeozmzZPI(this.size);
        long j = this.roundRectOutlineTopLeft;
        long j2 = this.roundRectOutlineSize;
        if (j2 != InlineClassHelperKt.UnspecifiedPackedFloats) {
            jM6205toSizeozmzZPI = j2;
        }
        return (T) block.invoke(Offset.m2878boximpl(j), Size.m2946boximpl(jM6205toSizeozmzZPI));
    }

    /* JADX INFO: renamed from: setPosition-VbeCjmY, reason: not valid java name */
    private final void m3791setPositionVbeCjmY(long topLeft, long size) {
        this.impl.mo3821setPositionH0pRuoY(IntOffset.m6150getXimpl(topLeft), IntOffset.m6151getYimpl(topLeft), size);
    }

    /* JADX INFO: renamed from: setRectOutline-tz77jQw$default, reason: not valid java name */
    public static /* synthetic */ void m3792setRectOutlinetz77jQw$default(GraphicsLayer graphicsLayer, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Offset.INSTANCE.m2905getZeroF1C5BW0();
        }
        if ((i & 2) != 0) {
            j2 = Size.INSTANCE.m2966getUnspecifiedNHjbRc();
        }
        graphicsLayer.m3807setRectOutlinetz77jQw(j, j2);
    }

    /* JADX INFO: renamed from: setRoundRectOutline-TNW_H78$default, reason: not valid java name */
    public static /* synthetic */ void m3793setRoundRectOutlineTNW_H78$default(GraphicsLayer graphicsLayer, long j, long j2, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Offset.INSTANCE.m2905getZeroF1C5BW0();
        }
        long j3 = j;
        if ((i & 2) != 0) {
            j2 = Size.INSTANCE.m2966getUnspecifiedNHjbRc();
        }
        long j4 = j2;
        if ((i & 4) != 0) {
            f = 0.0f;
        }
        graphicsLayer.m3808setRoundRectOutlineTNW_H78(j3, j4, f);
    }

    /* JADX INFO: renamed from: setSize-ozmzZPI, reason: not valid java name */
    private final void m3794setSizeozmzZPI(long j) {
        if (IntSize.m6191equalsimpl0(this.size, j)) {
            return;
        }
        this.size = j;
        m3791setPositionVbeCjmY(this.topLeft, j);
        if (this.roundRectOutlineSize == InlineClassHelperKt.UnspecifiedPackedFloats) {
            this.outlineDirty = true;
            configureOutlineAndClip();
        }
    }

    private final void transformCanvas(Canvas androidCanvas) {
        Canvas canvas;
        float fM6150getXimpl = IntOffset.m6150getXimpl(this.topLeft);
        float fM6151getYimpl = IntOffset.m6151getYimpl(this.topLeft);
        float fM6150getXimpl2 = IntOffset.m6150getXimpl(this.topLeft) + ((int) (this.size >> 32));
        float fM6151getYimpl2 = IntOffset.m6151getYimpl(this.topLeft) + ((int) (this.size & 4294967295L));
        float alpha = getAlpha();
        ColorFilter colorFilter = getColorFilter();
        int iM3796getBlendMode0nO6VwU = m3796getBlendMode0nO6VwU();
        if (alpha < 1.0f || !BlendMode.m3043equalsimpl0(iM3796getBlendMode0nO6VwU, BlendMode.INSTANCE.m3074getSrcOver0nO6VwU()) || colorFilter != null || CompositingStrategy.m3784equalsimpl0(m3797getCompositingStrategyke2Ky5w(), CompositingStrategy.INSTANCE.m3790getOffscreenke2Ky5w())) {
            Paint Paint = this.softwareLayerPaint;
            if (Paint == null) {
                Paint = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = Paint;
            }
            Paint.setAlpha(alpha);
            Paint.mo3009setBlendModes9anfk8(iM3796getBlendMode0nO6VwU);
            Paint.setColorFilter(colorFilter);
            canvas = androidCanvas;
            canvas.saveLayer(fM6150getXimpl, fM6151getYimpl, fM6150getXimpl2, fM6151getYimpl2, Paint.getInternalPaint());
        } else {
            androidCanvas.save();
            canvas = androidCanvas;
        }
        canvas.translate(fM6150getXimpl, fM6151getYimpl);
        canvas.concat(this.impl.calculateMatrix());
    }

    private final Outline updatePathOutline(Path path) {
        Outline outlineObtainAndroidOutline = obtainAndroidOutline();
        OutlineVerificationHelper.INSTANCE.setPath(outlineObtainAndroidOutline, path);
        this.usePathForClip = !outlineObtainAndroidOutline.canClip();
        this.outlinePath = path;
        return outlineObtainAndroidOutline;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0055 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:20:0x0057 A[LOOP:0: B:10:0x0020->B:20:0x0057, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:25:0x005a A[EDGE_INSN: B:25:0x005a->B:21:0x005a BREAK  A[LOOP:0: B:10:0x0020->B:20:0x0057], SYNTHETIC] */
    public final void discardDisplayList$ui_graphics() {
        ChildLayerDependenciesTracker childLayerDependenciesTracker = this.childDependenciesTracker;
        GraphicsLayer graphicsLayer = childLayerDependenciesTracker.dependency;
        if (graphicsLayer != null) {
            graphicsLayer.onRemovedFromParentLayer();
            childLayerDependenciesTracker.dependency = null;
        }
        MutableScatterSet mutableScatterSet = childLayerDependenciesTracker.dependenciesSet;
        if (mutableScatterSet != null) {
            Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
            long[] jArr = ((ScatterSet) mutableScatterSet).metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i != length) {
                            break;
                            break;
                        }
                        i++;
                    } else {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                ((GraphicsLayer) objArr[(i << 3) + i3]).onRemovedFromParentLayer();
                            }
                            j >>= 8;
                        }
                        if (i2 != 8) {
                            break;
                        } else if (i != length) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            mutableScatterSet.clear();
        }
        this.impl.discardDisplayList();
    }

    public final void draw$ui_graphics(androidx.compose.ui.graphics.Canvas canvas, GraphicsLayer parentLayer) {
        if (this.isReleased) {
            return;
        }
        configureOutlineAndClip();
        recreateDisplayListIfNeeded();
        boolean z = getShadowElevation() > 0.0f;
        if (z) {
            canvas.enableZ();
        }
        Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        boolean zIsHardwareAccelerated = nativeCanvas.isHardwareAccelerated();
        if (!zIsHardwareAccelerated) {
            transformCanvas(nativeCanvas);
        }
        boolean z2 = !zIsHardwareAccelerated && this.clip;
        if (z2) {
            canvas.save();
            androidx.compose.ui.graphics.Outline outline = getOutline();
            if (outline instanceof androidx.compose.ui.graphics.Outline.Rectangle) {
                androidx.compose.ui.graphics.Canvas.m3107clipRectmtrdDE$default(canvas, ((androidx.compose.ui.graphics.Outline.Rectangle) outline).getRect(), 0, 2, null);
            } else if (outline instanceof androidx.compose.ui.graphics.Outline.Rounded) {
                Path Path = this.roundRectClipPath;
                if (Path != null) {
                    Path.rewind();
                } else {
                    Path = AndroidPath_androidKt.Path();
                    this.roundRectClipPath = Path;
                }
                Path.addRoundRect$default(Path, ((androidx.compose.ui.graphics.Outline.Rounded) outline).getRoundRect(), null, 2, null);
                androidx.compose.ui.graphics.Canvas.m3105clipPathmtrdDE$default(canvas, Path, 0, 2, null);
            } else {
                if (!(outline instanceof androidx.compose.ui.graphics.Outline.Generic)) {
                    bu8.a();
                    return;
                }
                androidx.compose.ui.graphics.Canvas.m3105clipPathmtrdDE$default(canvas, ((androidx.compose.ui.graphics.Outline.Generic) outline).getPath(), 0, 2, null);
            }
        }
        if (parentLayer != null) {
            parentLayer.addSubLayer(this);
        }
        if (AndroidCanvas_androidKt.getNativeCanvas(canvas).isHardwareAccelerated() || this.impl.getSupportsSoftwareRendering()) {
            this.impl.draw(canvas);
        } else {
            CanvasDrawScope canvasDrawScope = this.softwareDrawScope;
            if (canvasDrawScope == null) {
                canvasDrawScope = new CanvasDrawScope();
                this.softwareDrawScope = canvasDrawScope;
            }
            Density density = this.density;
            LayoutDirection layoutDirection = this.layoutDirection;
            long jM6205toSizeozmzZPI = IntSizeKt.m6205toSizeozmzZPI(this.size);
            Density density2 = canvasDrawScope.getDrawContext().getDensity();
            LayoutDirection layoutDirection2 = canvasDrawScope.getDrawContext().getLayoutDirection();
            androidx.compose.ui.graphics.Canvas canvas2 = canvasDrawScope.getDrawContext().getCanvas();
            long jMo3629getSizeNHjbRc = canvasDrawScope.getDrawContext().mo3629getSizeNHjbRc();
            GraphicsLayer graphicsLayer = canvasDrawScope.getDrawContext().getGraphicsLayer();
            DrawContext drawContext = canvasDrawScope.getDrawContext();
            drawContext.setDensity(density);
            drawContext.setLayoutDirection(layoutDirection);
            drawContext.setCanvas(canvas);
            drawContext.mo3630setSizeuvyYCjk(jM6205toSizeozmzZPI);
            drawContext.setGraphicsLayer(this);
            canvas.save();
            try {
                drawWithChildTracking(canvasDrawScope);
                canvas.restore();
                DrawContext drawContext2 = canvasDrawScope.getDrawContext();
                drawContext2.setDensity(density2);
                drawContext2.setLayoutDirection(layoutDirection2);
                drawContext2.setCanvas(canvas2);
                drawContext2.mo3630setSizeuvyYCjk(jMo3629getSizeNHjbRc);
                drawContext2.setGraphicsLayer(graphicsLayer);
            } catch (Throwable th) {
                canvas.restore();
                DrawContext drawContext3 = canvasDrawScope.getDrawContext();
                drawContext3.setDensity(density2);
                drawContext3.setLayoutDirection(layoutDirection2);
                drawContext3.setCanvas(canvas2);
                drawContext3.mo3630setSizeuvyYCjk(jMo3629getSizeNHjbRc);
                drawContext3.setGraphicsLayer(graphicsLayer);
                throw th;
            }
        }
        if (z2) {
            canvas.restore();
        }
        if (z) {
            canvas.disableZ();
        }
        if (zIsHardwareAccelerated) {
            return;
        }
        nativeCanvas.restore();
    }

    public final void drawForPersistence$ui_graphics(androidx.compose.ui.graphics.Canvas canvas) {
        if (AndroidCanvas_androidKt.getNativeCanvas(canvas).isHardwareAccelerated() || this.impl.getSupportsSoftwareRendering()) {
            recreateDisplayListIfNeeded();
            this.impl.draw(canvas);
        }
    }

    public final void emulateTrimMemory$ui_graphics() {
        this.impl.discardDisplayList();
    }

    public final float getAlpha() {
        return this.impl.getAlpha();
    }

    /* JADX INFO: renamed from: getAmbientShadowColor-0d7_KjU, reason: not valid java name */
    public final long m3795getAmbientShadowColor0d7_KjU() {
        return this.impl.getAmbientShadowColor();
    }

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: not valid java name */
    public final int m3796getBlendMode0nO6VwU() {
        return this.impl.getBlendMode();
    }

    public final float getCameraDistance() {
        return this.impl.getCameraDistance();
    }

    public final boolean getClip() {
        return this.clip;
    }

    public final ColorFilter getColorFilter() {
        return this.impl.getColorFilter();
    }

    /* JADX INFO: renamed from: getCompositingStrategy-ke2Ky5w, reason: not valid java name */
    public final int m3797getCompositingStrategyke2Ky5w() {
        return this.impl.getCompositingStrategy();
    }

    /* JADX INFO: renamed from: getImpl$ui_graphics, reason: from getter */
    public final GraphicsLayerImpl getImpl() {
        return this.impl;
    }

    public final long getLayerId() {
        return this.impl.getLayerId();
    }

    public final androidx.compose.ui.graphics.Outline getOutline() {
        androidx.compose.ui.graphics.Outline rectangle;
        androidx.compose.ui.graphics.Outline outline = this.internalOutline;
        Path path = this.outlinePath;
        if (outline != null) {
            return outline;
        }
        if (path != null) {
            androidx.compose.ui.graphics.Outline.Generic generic = new androidx.compose.ui.graphics.Outline.Generic(path);
            this.internalOutline = generic;
            return generic;
        }
        long jM6205toSizeozmzZPI = IntSizeKt.m6205toSizeozmzZPI(this.size);
        long j = this.roundRectOutlineTopLeft;
        long j2 = this.roundRectOutlineSize;
        if (j2 != InlineClassHelperKt.UnspecifiedPackedFloats) {
            jM6205toSizeozmzZPI = j2;
        }
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
        float fIntBitsToFloat3 = Float.intBitsToFloat((int) (jM6205toSizeozmzZPI >> 32)) + fIntBitsToFloat;
        float fIntBitsToFloat4 = fIntBitsToFloat2 + Float.intBitsToFloat((int) (jM6205toSizeozmzZPI & 4294967295L));
        float f = this.roundRectCornerRadius;
        if (f > 0.0f) {
            rectangle = new androidx.compose.ui.graphics.Outline.Rounded(RoundRectKt.m2943RoundRectgG7oq9Y(fIntBitsToFloat, fIntBitsToFloat2, fIntBitsToFloat3, fIntBitsToFloat4, CornerRadius.m2843constructorimpl((((long) Float.floatToRawIntBits(f)) << 32) | (4294967295L & ((long) Float.floatToRawIntBits(f))))));
        } else {
            rectangle = new androidx.compose.ui.graphics.Outline.Rectangle(new Rect(fIntBitsToFloat, fIntBitsToFloat2, fIntBitsToFloat3, fIntBitsToFloat4));
        }
        this.internalOutline = rectangle;
        return rectangle;
    }

    public final long getOwnerViewId() {
        return this.impl.getOwnerId();
    }

    /* JADX INFO: renamed from: getPivotOffset-F1C5BW0, reason: not valid java name and from getter */
    public final long getPivotOffset() {
        return this.pivotOffset;
    }

    public final RenderEffect getRenderEffect() {
        return this.impl.getRenderEffect();
    }

    public final float getRotationX() {
        return this.impl.getRotationX();
    }

    public final float getRotationY() {
        return this.impl.getRotationY();
    }

    public final float getRotationZ() {
        return this.impl.getRotationZ();
    }

    public final float getScaleX() {
        return this.impl.getScaleX();
    }

    public final float getScaleY() {
        return this.impl.getScaleY();
    }

    public final float getShadowElevation() {
        return this.impl.getShadowElevation();
    }

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name and from getter */
    public final long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: getSpotShadowColor-0d7_KjU, reason: not valid java name */
    public final long m3800getSpotShadowColor0d7_KjU() {
        return this.impl.getSpotShadowColor();
    }

    /* JADX INFO: renamed from: getTopLeft-nOcc-ac, reason: not valid java name and from getter */
    public final long getTopLeft() {
        return this.topLeft;
    }

    public final float getTranslationX() {
        return this.impl.getTranslationX();
    }

    public final float getTranslationY() {
        return this.impl.getTranslationY();
    }

    /* JADX INFO: renamed from: isReleased, reason: from getter */
    public final boolean getIsReleased() {
        return this.isReleased;
    }

    /* JADX INFO: renamed from: record-mL-hObY, reason: not valid java name */
    public final void m3802recordmLhObY(Density density, LayoutDirection layoutDirection, long size, Function1<? super DrawScope, Unit> block) {
        m3794setSizeozmzZPI(size);
        this.density = density;
        this.layoutDirection = layoutDirection;
        this.drawBlock = block;
        this.impl.setInvalidated(true);
        recordInternal();
    }

    public final void release$ui_graphics() {
        if (this.isReleased) {
            return;
        }
        this.isReleased = true;
        discardContentIfReleasedAndHaveNoParentLayerUsages();
    }

    public final void setAlpha(float f) {
        if (this.impl.getAlpha() == f) {
            return;
        }
        this.impl.setAlpha(f);
    }

    /* JADX INFO: renamed from: setAmbientShadowColor-8_81llA, reason: not valid java name */
    public final void m3803setAmbientShadowColor8_81llA(long j) {
        if (Color.m3135equalsimpl0(j, this.impl.getAmbientShadowColor())) {
            return;
        }
        this.impl.mo3816setAmbientShadowColor8_81llA(j);
    }

    /* JADX INFO: renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public final void m3804setBlendModes9anfk8(int i) {
        if (BlendMode.m3043equalsimpl0(this.impl.getBlendMode(), i)) {
            return;
        }
        this.impl.mo3817setBlendModes9anfk8(i);
    }

    public final void setCameraDistance(float f) {
        if (this.impl.getCameraDistance() == f) {
            return;
        }
        this.impl.setCameraDistance(f);
    }

    public final void setClip(boolean z) {
        if (this.clip != z) {
            this.clip = z;
            this.outlineDirty = true;
            configureOutlineAndClip();
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        if (Intrinsics.areEqual(this.impl.getColorFilter(), colorFilter)) {
            return;
        }
        this.impl.setColorFilter(colorFilter);
    }

    /* JADX INFO: renamed from: setCompositingStrategy-Wpw9cng, reason: not valid java name */
    public final void m3805setCompositingStrategyWpw9cng(int i) {
        if (CompositingStrategy.m3784equalsimpl0(this.impl.getCompositingStrategy(), i)) {
            return;
        }
        this.impl.mo3818setCompositingStrategyWpw9cng(i);
    }

    public final void setPathOutline(Path path) {
        resetOutlineParams();
        this.outlinePath = path;
        configureOutlineAndClip();
    }

    /* JADX INFO: renamed from: setPivotOffset-k-4lQ0M, reason: not valid java name */
    public final void m3806setPivotOffsetk4lQ0M(long j) {
        if (Offset.m2886equalsimpl0(this.pivotOffset, j)) {
            return;
        }
        this.pivotOffset = j;
        this.impl.mo3820setPivotOffsetk4lQ0M(j);
    }

    /* JADX INFO: renamed from: setRectOutline-tz77jQw, reason: not valid java name */
    public final void m3807setRectOutlinetz77jQw(long topLeft, long size) {
        m3808setRoundRectOutlineTNW_H78(topLeft, size, 0.0f);
    }

    public final void setRenderEffect(RenderEffect renderEffect) {
        if (Intrinsics.areEqual(this.impl.getRenderEffect(), renderEffect)) {
            return;
        }
        this.impl.setRenderEffect(renderEffect);
    }

    public final void setRotationX(float f) {
        if (this.impl.getRotationX() == f) {
            return;
        }
        this.impl.setRotationX(f);
    }

    public final void setRotationY(float f) {
        if (this.impl.getRotationY() == f) {
            return;
        }
        this.impl.setRotationY(f);
    }

    public final void setRotationZ(float f) {
        if (this.impl.getRotationZ() == f) {
            return;
        }
        this.impl.setRotationZ(f);
    }

    /* JADX INFO: renamed from: setRoundRectOutline-TNW_H78, reason: not valid java name */
    public final void m3808setRoundRectOutlineTNW_H78(long topLeft, long size, float cornerRadius) {
        if (Offset.m2886equalsimpl0(this.roundRectOutlineTopLeft, topLeft) && Size.m2954equalsimpl0(this.roundRectOutlineSize, size) && this.roundRectCornerRadius == cornerRadius && this.outlinePath == null) {
            return;
        }
        resetOutlineParams();
        this.roundRectOutlineTopLeft = topLeft;
        this.roundRectOutlineSize = size;
        this.roundRectCornerRadius = cornerRadius;
        configureOutlineAndClip();
    }

    public final void setScaleX(float f) {
        if (this.impl.getScaleX() == f) {
            return;
        }
        this.impl.setScaleX(f);
    }

    public final void setScaleY(float f) {
        if (this.impl.getScaleY() == f) {
            return;
        }
        this.impl.setScaleY(f);
    }

    public final void setShadowElevation(float f) {
        if (this.impl.getShadowElevation() == f) {
            return;
        }
        this.impl.setShadowElevation(f);
        this.outlineDirty = true;
        configureOutlineAndClip();
    }

    /* JADX INFO: renamed from: setSpotShadowColor-8_81llA, reason: not valid java name */
    public final void m3809setSpotShadowColor8_81llA(long j) {
        if (Color.m3135equalsimpl0(j, this.impl.getSpotShadowColor())) {
            return;
        }
        this.impl.mo3822setSpotShadowColor8_81llA(j);
    }

    /* JADX INFO: renamed from: setTopLeft--gyyYBs, reason: not valid java name */
    public final void m3810setTopLeftgyyYBs(long j) {
        if (IntOffset.m6149equalsimpl0(this.topLeft, j)) {
            return;
        }
        this.topLeft = j;
        m3791setPositionVbeCjmY(j, this.size);
    }

    public final void setTranslationX(float f) {
        if (this.impl.getTranslationX() == f) {
            return;
        }
        this.impl.setTranslationX(f);
    }

    public final void setTranslationY(float f) {
        if (this.impl.getTranslationY() == f) {
            return;
        }
        this.impl.setTranslationY(f);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object toImageBitmap(Continuation<? super ImageBitmap> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object bitmap = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(bitmap);
            LayerSnapshotImpl layerSnapshotImpl = SnapshotImpl;
            anonymousClass1.label = 1;
            bitmap = layerSnapshotImpl.toBitmap(this, anonymousClass1);
            if (bitmap == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(bitmap);
        }
        return AndroidImageBitmap_androidKt.asImageBitmap((Bitmap) bitmap);
    }
}
