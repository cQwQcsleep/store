package androidx.compose.ui.node;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.FrameRateCategory;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.MutableRectKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.MatrixKt;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.input.pointer.MatrixPositionCalculator;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.spatial.RectManager;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000À\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u001c\b!\u0018\u0000 Ç\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0004Æ\u0002Ç\u0002B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u00103\u001a\u0004\u0018\u00010\u00152\u0006\u00104\u001a\u00020\fH\u0002J-\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00104\u001a\u00020\f2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u0002060:H\u0086\bJ:\u00105\u001a\u000206\"\u0006\b\u0000\u0010;\u0018\u00012\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H;0=2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u0002H;\u0012\u0004\u0012\u0002060:H\u0086\b¢\u0006\u0004\b>\u0010?J\u001b\u0010@\u001a\u00020\f2\n\u0010<\u001a\u0006\u0012\u0002\b\u00030=H\u0002¢\u0006\u0004\bA\u0010BJ\u001b\u0010C\u001a\u0004\u0018\u00010\u00152\n\u0010<\u001a\u0006\u0012\u0002\b\u00030=¢\u0006\u0004\bD\u0010EJ\u0006\u0010U\u001a\u00020\fJ\r\u0010\\\u001a\u000206H\u0010¢\u0006\u0002\b]J\b\u0010q\u001a\u000206H&J\u0018\u0010v\u001a\u0002062\u0006\u0010w\u001a\u0002082\u0006\u0010x\u001a\u000208H\u0014J\u000f\u0010\u0087\u0001\u001a\u000206H\u0000¢\u0006\u0003\b\u0088\u0001J0\u0010§\u0001\u001a\u00030¨\u00012\b\u0010©\u0001\u001a\u00030\u0099\u00012\u0010\b\u0004\u00109\u001a\n\u0012\u0005\u0012\u00030¨\u00010ª\u0001H\u0084\b¢\u0006\u0006\b«\u0001\u0010¬\u0001J\u0007\u0010\u00ad\u0001\u001a\u000206J\u0007\u0010®\u0001\u001a\u000206J=\u0010¯\u0001\u001a\u0002062\u0006\u0010z\u001a\u00020y2\u0006\u0010\u007f\u001a\u00020%2\u0019\u0010N\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bMH\u0014¢\u0006\u0006\b°\u0001\u0010±\u0001J,\u0010¯\u0001\u001a\u0002062\u0006\u0010z\u001a\u00020y2\u0006\u0010\u007f\u001a\u00020%2\b\u0010²\u0001\u001a\u00030³\u0001H\u0014¢\u0006\u0006\b°\u0001\u0010´\u0001JI\u0010µ\u0001\u001a\u0002062\u0006\u0010z\u001a\u00020y2\u0006\u0010\u007f\u001a\u00020%2\u0019\u0010N\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bM2\n\u0010¶\u0001\u001a\u0005\u0018\u00010³\u0001H\u0002¢\u0006\u0006\b·\u0001\u0010¸\u0001J\u0007\u0010¹\u0001\u001a\u000206JG\u0010º\u0001\u001a\u0002062\u0006\u0010z\u001a\u00020y2\u0006\u0010\u007f\u001a\u00020%2\u0019\u0010N\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bM2\n\u0010²\u0001\u001a\u0005\u0018\u00010³\u0001¢\u0006\u0006\b»\u0001\u0010¸\u0001J\u001d\u0010¼\u0001\u001a\u0002062\b\u0010½\u0001\u001a\u00030¾\u00012\n\u0010¿\u0001\u001a\u0005\u0018\u00010³\u0001J\u001f\u0010À\u0001\u001a\u0002062\b\u0010½\u0001\u001a\u00030¾\u00012\n\u0010¿\u0001\u001a\u0005\u0018\u00010³\u0001H\u0002J\u001f\u0010Á\u0001\u001a\u0002062\b\u0010½\u0001\u001a\u00030¾\u00012\n\u0010¿\u0001\u001a\u0005\u0018\u00010³\u0001H\u0016J\u0007\u0010Â\u0001\u001a\u000206J-\u0010Ê\u0001\u001a\u0002062\u0019\u0010N\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bM2\t\b\u0002\u0010Ë\u0001\u001a\u00020\fJ\u0014\u0010Ì\u0001\u001a\u0002062\t\b\u0002\u0010Í\u0001\u001a\u00020\fH\u0002JA\u0010Ø\u0001\u001a\u0002062\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\f¢\u0006\u0006\bâ\u0001\u0010ã\u0001JI\u0010ä\u0001\u001a\u000206*\u0004\u0018\u00010\u00152\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\fH\u0002¢\u0006\u0006\bå\u0001\u0010æ\u0001J[\u0010ç\u0001\u001a\u000206*\u0004\u0018\u00010\u00152\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\f2\u0007\u0010è\u0001\u001a\u00020%2\u0007\u0010é\u0001\u001a\u00020\fH\u0002¢\u0006\u0006\bê\u0001\u0010ë\u0001JR\u0010ì\u0001\u001a\u000206*\u0004\u0018\u00010\u00152\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\f2\u0007\u0010è\u0001\u001a\u00020%H\u0002¢\u0006\u0006\bí\u0001\u0010î\u0001JR\u0010ï\u0001\u001a\u000206*\u0004\u0018\u00010\u00152\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\f2\u0007\u0010è\u0001\u001a\u00020%H\u0002¢\u0006\u0006\bð\u0001\u0010î\u0001J,\u0010ñ\u0001\u001a\u00020\f*\u0004\u0018\u00010\u00152\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010ß\u0001\u001a\u00030à\u0001H\u0002¢\u0006\u0006\bò\u0001\u0010ó\u0001JC\u0010ô\u0001\u001a\u0002062\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\fH\u0016¢\u0006\u0006\bõ\u0001\u0010ã\u0001J\b\u0010ö\u0001\u001a\u00030÷\u0001J\u001d\u0010ø\u0001\u001a\u00030Ü\u00012\b\u0010ù\u0001\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\bú\u0001\u0010û\u0001J\u001d\u0010ü\u0001\u001a\u00030Ü\u00012\b\u0010ý\u0001\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\bþ\u0001\u0010û\u0001J\u001d\u0010ÿ\u0001\u001a\u00030Ü\u00012\b\u0010\u0080\u0002\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\b\u0081\u0002\u0010û\u0001J\u001d\u0010\u0082\u0002\u001a\u00030Ü\u00012\b\u0010ý\u0001\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\b\u0083\u0002\u0010û\u0001J\r\u0010\u0084\u0002\u001a\u00020\u0000*\u00020\u0003H\u0002J&\u0010\u0085\u0002\u001a\u00030Ü\u00012\u0007\u0010\u0086\u0002\u001a\u00020\u00032\b\u0010\u0087\u0002\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\b\u0088\u0002\u0010\u0089\u0002J/\u0010\u0085\u0002\u001a\u00030Ü\u00012\u0007\u0010\u0086\u0002\u001a\u00020\u00032\b\u0010\u0087\u0002\u001a\u00030Ü\u00012\u0007\u0010\u008a\u0002\u001a\u00020\fH\u0016¢\u0006\u0006\b\u008b\u0002\u0010\u008c\u0002J%\u0010\u008d\u0002\u001a\u0002062\u0007\u0010\u0086\u0002\u001a\u00020\u00032\b\u0010\u008e\u0002\u001a\u00030\u008f\u0002H\u0016¢\u0006\u0006\b\u0090\u0002\u0010\u0091\u0002J\u001c\u0010\u0092\u0002\u001a\u0002062\b\u0010\u008e\u0002\u001a\u00030\u008f\u0002H\u0016¢\u0006\u0006\b\u0093\u0002\u0010\u0094\u0002J%\u0010\u0095\u0002\u001a\u0002062\u0007\u0010\u0096\u0002\u001a\u00020\u00002\b\u0010\u008e\u0002\u001a\u00030\u008f\u0002H\u0002¢\u0006\u0006\b\u0097\u0002\u0010\u0098\u0002J%\u0010\u0099\u0002\u001a\u0002062\u0007\u0010\u0096\u0002\u001a\u00020\u00002\b\u0010\u008e\u0002\u001a\u00030\u008f\u0002H\u0002¢\u0006\u0006\b\u009a\u0002\u0010\u0098\u0002J\u001c\u0010\u009b\u0002\u001a\u00030÷\u00012\u0007\u0010\u0086\u0002\u001a\u00020\u00032\u0007\u0010\u009c\u0002\u001a\u00020\fH\u0016J/\u0010\u009d\u0002\u001a\u00030Ü\u00012\u0007\u0010\u0096\u0002\u001a\u00020\u00002\b\u0010\u009e\u0002\u001a\u00030Ü\u00012\u0007\u0010\u008a\u0002\u001a\u00020\fH\u0002¢\u0006\u0006\b\u009f\u0002\u0010 \u0002J%\u0010\u009d\u0002\u001a\u0002062\u0007\u0010\u0096\u0002\u001a\u00020\u00002\b\u0010¡\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u009c\u0002\u001a\u00020\fH\u0002J\u001d\u0010¢\u0002\u001a\u00030Ü\u00012\b\u0010ý\u0001\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\b£\u0002\u0010û\u0001J)\u0010¤\u0002\u001a\u0002062\b\u0010½\u0001\u001a\u00030¾\u00012\u0013\u00109\u001a\u000f\u0012\u0005\u0012\u00030¾\u0001\u0012\u0004\u0012\u0002060:H\u0084\bJ'\u0010¥\u0002\u001a\u00030Ü\u00012\u0007\u0010z\u001a\u00030Ü\u00012\t\b\u0002\u0010\u008a\u0002\u001a\u00020\fH\u0016¢\u0006\u0006\b¦\u0002\u0010§\u0002J'\u0010¨\u0002\u001a\u00030Ü\u00012\u0007\u0010z\u001a\u00030Ü\u00012\t\b\u0002\u0010\u008a\u0002\u001a\u00020\fH\u0016¢\u0006\u0006\b©\u0002\u0010§\u0002J\u001d\u0010ª\u0002\u001a\u0002062\b\u0010½\u0001\u001a\u00030¾\u00012\b\u0010«\u0002\u001a\u00030¬\u0002H\u0004J\u0007\u0010\u00ad\u0002\u001a\u000206J\u0007\u0010®\u0002\u001a\u000206J-\u0010¯\u0002\u001a\u0002062\b\u0010°\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u009c\u0002\u001a\u00020\f2\t\b\u0002\u0010±\u0002\u001a\u00020\fH\u0000¢\u0006\u0003\b²\u0002J\u001c\u0010³\u0002\u001a\u0002062\b\u0010°\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u009c\u0002\u001a\u00020\fH\u0002J\u001c\u0010´\u0002\u001a\u00020\f2\b\u0010Û\u0001\u001a\u00030Ü\u0001H\u0004¢\u0006\u0006\bµ\u0002\u0010¶\u0002J\u001c\u0010·\u0002\u001a\u00020\f2\b\u0010Û\u0001\u001a\u00030Ü\u0001H\u0004¢\u0006\u0006\b¸\u0002\u0010¶\u0002J\t\u0010¹\u0002\u001a\u000206H\u0016J\t\u0010º\u0002\u001a\u000206H\u0016J\u0018\u0010»\u0002\u001a\u00020\u00002\u0007\u0010¼\u0002\u001a\u00020\u0000H\u0000¢\u0006\u0003\b½\u0002J\u0007\u0010¾\u0002\u001a\u00020\fJ\u001d\u0010¿\u0002\u001a\u00030Ü\u00012\b\u0010Û\u0001\u001a\u00030Ü\u0001H\u0002¢\u0006\u0006\bÀ\u0002\u0010û\u0001J\u001d\u0010Á\u0002\u001a\u00030Ö\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0004¢\u0006\u0006\bÂ\u0002\u0010û\u0001J&\u0010Ã\u0002\u001a\u00020%2\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0004¢\u0006\u0006\bÄ\u0002\u0010Å\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u0012\u0010\u0014\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0000X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0000X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u0014\u0010 \u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010'R\u0016\u0010*\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u0010\u000eR\u000e\u00102\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010F\u001a\u00020G8F¢\u0006\u0006\u001a\u0004\bH\u0010IR\u000e\u0010J\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000RD\u0010N\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bM2\u0019\u0010K\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bM@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\bO\u0010PR\u000e\u0010Q\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010V\u001a\u00020W8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bX\u0010YR\u0016\u0010Z\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b[\u0010,R\u0014\u0010^\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b_\u0010\u000eR\u0014\u0010`\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010\u000eR\u0010\u0010a\u001a\u0004\u0018\u00010bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010c\u001a\u00020b2\u0006\u0010K\u001a\u00020b8P@PX\u0090\u000e¢\u0006\f\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR&\u0010i\u001a\u0004\u0018\u00010h2\b\u0010K\u001a\u0004\u0018\u00010h@dX¦\u000e¢\u0006\f\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\u0016\u0010n\u001a\n\u0012\u0004\u0012\u00020p\u0018\u00010oX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010r\u001a\b\u0012\u0004\u0012\u00020p0s8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bt\u0010uR&\u0010z\u001a\u00020y2\u0006\u0010K\u001a\u00020y@TX\u0096\u000e¢\u0006\u0010\n\u0002\u0010~\u001a\u0004\b{\u0010I\"\u0004\b|\u0010}R'\u0010\u007f\u001a\u00020%2\u0006\u0010K\u001a\u00020%@DX\u0086\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b\u0080\u0001\u0010'\"\u0006\b\u0081\u0001\u0010\u0082\u0001R\u001a\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0015\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0007\u001a\u0005\b\u008a\u0001\u0010/R\u0015\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0007\u001a\u0005\b\u008c\u0001\u0010/R\u0012\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u008f\u0001\u001a\u00030\u008e\u00018DX\u0084\u0004¢\u0006\b\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0018\u0010\u0092\u0001\u001a\u00030\u0093\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0012\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0098\u0001\u001a\u00030\u0099\u00018@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u009a\u0001\u0010IR \u0010\u009b\u0001\u001a\u00030\u009c\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001\"\u0006\b\u009f\u0001\u0010 \u0001R\u001d\u0010¡\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¢\u0001\u0010\u000e\"\u0005\b£\u0001\u0010\u0010R\u001d\u0010¤\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¥\u0001\u0010\u000e\"\u0005\b¦\u0001\u0010\u0010R\u0012\u0010Ã\u0001\u001a\u0005\u0018\u00010³\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010Ä\u0001\u001a\u0005\u0018\u00010¾\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010Å\u0001\u001a\u001b\u0012\u0005\u0012\u00030¾\u0001\u0012\u0007\u0012\u0005\u0018\u00010³\u0001\u0012\u0004\u0012\u000206\u0018\u00010Æ\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010Ç\u0001\u001a\u0019\u0012\u0005\u0012\u00030¾\u0001\u0012\u0007\u0012\u0005\u0018\u00010³\u0001\u0012\u0004\u0012\u0002060Æ\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\bÈ\u0001\u0010É\u0001R\u0016\u0010Î\u0001\u001a\t\u0012\u0004\u0012\u0002060ª\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010Ï\u0001\u001a\u00020\f2\u0006\u0010K\u001a\u00020\f@BX\u0080\u000e¢\u0006\t\n\u0000\u001a\u0005\bÐ\u0001\u0010\u000eR'\u0010²\u0001\u001a\u0005\u0018\u00010Ñ\u00012\t\u0010K\u001a\u0005\u0018\u00010Ñ\u0001@BX\u0086\u000e¢\u0006\n\n\u0000\u001a\u0006\bÒ\u0001\u0010Ó\u0001R\u0012\u0010¶\u0001\u001a\u0005\u0018\u00010³\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010Ô\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÔ\u0001\u0010\u000eR\u0014\u0010Õ\u0001\u001a\u00030Ö\u00018F¢\u0006\u0007\u001a\u0005\b×\u0001\u0010I¨\u0006È\u0002"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator;", "Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/node/OwnerScope;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "forcePlaceWithLookaheadOffset", "", "getForcePlaceWithLookaheadOffset$ui", "()Z", "setForcePlaceWithLookaheadOffset$ui", "(Z)V", "forceMeasureWithLookaheadConstraints", "getForceMeasureWithLookaheadConstraints$ui", "setForceMeasureWithLookaheadConstraints$ui", "tail", "Landroidx/compose/ui/Modifier$Node;", "getTail", "()Landroidx/compose/ui/Modifier$Node;", "wrapped", "getWrapped$ui", "()Landroidx/compose/ui/node/NodeCoordinator;", "setWrapped$ui", "(Landroidx/compose/ui/node/NodeCoordinator;)V", "wrappedBy", "getWrappedBy$ui", "setWrappedBy$ui", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "density", "", "getDensity", "()F", "fontScale", "getFontScale", "parent", "getParent", "()Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "coordinates", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "introducesMotionFrameOfReference", "getIntroducesMotionFrameOfReference", "released", "headNode", "includeTail", "visitNodes", "", "mask", "", "block", "Lkotlin/Function1;", "T", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/node/NodeKind;", "visitNodes-aLcG6gQ", "(ILkotlin/jvm/functions/Function1;)V", "hasNode", "hasNode-H91voCI", "(I)Z", "head", "head-H91voCI", "(I)Landroidx/compose/ui/Modifier$Node;", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", "isClipping", "value", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "layerBlock", "getLayerBlock", "()Lkotlin/jvm/functions/Function1;", "layerDensity", "Landroidx/compose/ui/unit/Density;", "layerLayoutDirection", "lastLayerAlpha", "isTransparent", "alignmentLinesOwner", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "getAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "child", "getChild", "replace", "replace$ui", "hasMeasureResult", "getHasMeasureResult", "isAttached", "_measureResult", "Landroidx/compose/ui/layout/MeasureResult;", "measureResult", "getMeasureResult$ui", "()Landroidx/compose/ui/layout/MeasureResult;", "setMeasureResult$ui", "(Landroidx/compose/ui/layout/MeasureResult;)V", "Landroidx/compose/ui/node/LookaheadDelegate;", "lookaheadDelegate", "getLookaheadDelegate", "()Landroidx/compose/ui/node/LookaheadDelegate;", "setLookaheadDelegate", "(Landroidx/compose/ui/node/LookaheadDelegate;)V", "oldAlignmentLines", "Landroidx/collection/MutableObjectIntMap;", "Landroidx/compose/ui/layout/AlignmentLine;", "ensureLookaheadDelegateCreated", "providedAlignmentLines", "", "getProvidedAlignmentLines", "()Ljava/util/Set;", "onMeasureResultChanged", "width", "height", "Landroidx/compose/ui/unit/IntOffset;", "position", "getPosition-nOcc-ac", "setPosition--gyyYBs", "(J)V", "J", "zIndex", "getZIndex", "setZIndex", "(F)V", "parentData", "", "getParentData", "()Ljava/lang/Object;", "onCoordinatesUsed", "onCoordinatesUsed$ui", "parentLayoutCoordinates", "getParentLayoutCoordinates", "parentCoordinates", "getParentCoordinates", "_rectCache", "Landroidx/compose/ui/geometry/MutableRect;", "rectCache", "getRectCache", "()Landroidx/compose/ui/geometry/MutableRect;", "snapshotObserver", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "layerPositionalProperties", "Landroidx/compose/ui/node/LayerPositionalProperties;", "lastMeasurementConstraints", "Landroidx/compose/ui/unit/Constraints;", "getLastMeasurementConstraints-msEJaDk$ui", "lastShape", "Landroidx/compose/ui/graphics/Shape;", "getLastShape$ui", "()Landroidx/compose/ui/graphics/Shape;", "setLastShape$ui", "(Landroidx/compose/ui/graphics/Shape;)V", "lastClip", "getLastClip$ui", "setLastClip$ui", "wasLayerBlockInvoked", "getWasLayerBlockInvoked$ui", "setWasLayerBlockInvoked$ui", "performingMeasure", "Landroidx/compose/ui/layout/Placeable;", "constraints", "Lkotlin/Function0;", "performingMeasure-K40F9xA", "(JLkotlin/jvm/functions/Function0;)Landroidx/compose/ui/layout/Placeable;", "onMeasured", "onUnplaced", "placeAt", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "(JFLandroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "placeSelf", "explicitLayer", "placeSelf-MLgxB_4", "(JFLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "releaseLayer", "placeSelfApparentToRealOffset", "placeSelfApparentToRealOffset-MLgxB_4", "draw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "graphicsLayer", "drawContainedDrawModifiers", "performDraw", "onPlaced", "drawBlockParentLayer", "drawBlockCanvas", "_drawBlock", "Lkotlin/Function2;", "drawBlock", "getDrawBlock", "()Lkotlin/jvm/functions/Function2;", "updateLayerBlock", "forceUpdateLayerParameters", "updateLayerParameters", "invokeOnLayoutChange", "invalidateParentLayer", "lastLayerDrawingWasSkipped", "getLastLayerDrawingWasSkipped$ui", "Landroidx/compose/ui/node/OwnedLayer;", "getLayer", "()Landroidx/compose/ui/node/OwnedLayer;", "isValidOwnerScope", "minimumTouchTargetSize", "Landroidx/compose/ui/geometry/Size;", "getMinimumTouchTargetSize-NH-jbRc", "hitTest", "hitTestSource", "Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "hitTestResult", "Landroidx/compose/ui/node/HitTestResult;", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "isInLayer", "hitTest-qzLsGqo", "(Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;IZ)V", "hit", "hit-5ShdDok", "(Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;IZ)V", "outOfBoundsHit", "distanceFromEdge", "isHitInMinimumTouchTargetBetter", "outOfBoundsHit-8NAm7pk", "(Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;IZFZ)V", "hitNear", "hitNear-Fh5PU_I", "(Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;IZF)V", "speculativeHit", "speculativeHit-Fh5PU_I", "isInExpandedTouchBounds", "isInExpandedTouchBounds-ThD-n1k", "(Landroidx/compose/ui/Modifier$Node;JI)Z", "hitTestChild", "hitTestChild-qzLsGqo", "touchBoundsInRoot", "Landroidx/compose/ui/geometry/Rect;", "screenToLocal", "relativeToScreen", "screenToLocal-MK-Hz9U", "(J)J", "localToScreen", "relativeToLocal", "localToScreen-MK-Hz9U", "windowToLocal", "relativeToWindow", "windowToLocal-MK-Hz9U", "localToWindow", "localToWindow-MK-Hz9U", "toCoordinator", "localPositionOf", "sourceCoordinates", "relativeToSource", "localPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "includeMotionFrameOfReference", "localPositionOf-S_NoaFU", "(Landroidx/compose/ui/layout/LayoutCoordinates;JZ)J", "transformFrom", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "transformFrom-EL8BTi8", "(Landroidx/compose/ui/layout/LayoutCoordinates;[F)V", "transformToScreen", "transformToScreen-58bKbWc", "([F)V", "transformToAncestor", "ancestor", "transformToAncestor-EL8BTi8", "(Landroidx/compose/ui/node/NodeCoordinator;[F)V", "transformFromAncestor", "transformFromAncestor-EL8BTi8", "localBoundingBoxOf", "clipBounds", "ancestorToLocal", "offset", "ancestorToLocal-S_NoaFU", "(Landroidx/compose/ui/node/NodeCoordinator;JZ)J", "rect", "localToRoot", "localToRoot-MK-Hz9U", "withPositionTranslation", "toParentPosition", "toParentPosition-8S9VItk", "(JZ)J", "fromParentPosition", "fromParentPosition-8S9VItk", "drawBorder", "paint", "Landroidx/compose/ui/graphics/Paint;", "onLayoutNodeDetach", "onRelease", "rectInParent", "bounds", "clipToMinimumTouchTargetSize", "rectInParent$ui", "fromParentRect", "withinLayerBounds", "withinLayerBounds-k-4lQ0M", "(J)Z", "isPointerInBounds", "isPointerInBounds-k-4lQ0M", "invalidateLayer", "onLayoutModifierNodeChanged", "findCommonAncestor", "other", "findCommonAncestor$ui", "shouldSharePointerInputWithSiblings", "offsetFromEdge", "offsetFromEdge-MK-Hz9U", "calculateMinimumTouchTargetPadding", "calculateMinimumTouchTargetPadding-E7KxVPU", "distanceInMinimumTouchTarget", "distanceInMinimumTouchTarget-tz77jQw", "(JJ)F", "HitTestSource", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public abstract class NodeCoordinator extends LookaheadCapablePlaceable implements Measurable, LayoutCoordinates, OwnerScope {
    public static final int $stable = 0;
    public static final String ExpectAttachedLayoutCoordinates = "LayoutCoordinate operations are only valid when isAttached is true";
    public static final String UnmeasuredError = "Asking for measurement result of unmeasured layout modifier";
    private Function2<? super Canvas, ? super GraphicsLayer, Unit> _drawBlock;
    private MeasureResult _measureResult;
    private MutableRect _rectCache;
    private Canvas drawBlockCanvas;
    private GraphicsLayer drawBlockParentLayer;
    private GraphicsLayer explicitLayer;
    private boolean forceMeasureWithLookaheadConstraints;
    private boolean forcePlaceWithLookaheadOffset;
    private boolean isClipping;
    private boolean lastClip;
    private boolean lastLayerDrawingWasSkipped;
    private OwnedLayer layer;
    private Function1<? super GraphicsLayerScope, Unit> layerBlock;
    private LayerPositionalProperties layerPositionalProperties;
    private final LayoutNode layoutNode;
    private MutableObjectIntMap<AlignmentLine> oldAlignmentLines;
    private boolean released;
    private boolean wasLayerBlockInvoked;
    private NodeCoordinator wrapped;
    private NodeCoordinator wrappedBy;
    private float zIndex;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Function1<NodeCoordinator, Unit> onCommitAffectingLayerParams = new Function1<NodeCoordinator, Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$onCommitAffectingLayerParams$1
        public final void invoke(NodeCoordinator nodeCoordinator) throws Throwable {
            LayoutNode layoutNode = nodeCoordinator.getLayoutNode();
            try {
                if (nodeCoordinator.isValidOwnerScope()) {
                    NodeCoordinator.updateLayerParameters$default(nodeCoordinator, false, 1, null);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                layoutNode.rethrowWithComposeStackTrace(th);
                wq6.a();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws Throwable {
            invoke((NodeCoordinator) obj);
            return Unit.INSTANCE;
        }
    };
    private static final Function1<NodeCoordinator, Unit> onCommitAffectingLayer = new Function1<NodeCoordinator, Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$onCommitAffectingLayer$1
        public final void invoke(NodeCoordinator nodeCoordinator) {
            OwnedLayer layer = nodeCoordinator.getLayer();
            if (layer != null) {
                layer.invalidate();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((NodeCoordinator) obj);
            return Unit.INSTANCE;
        }
    };
    private static final ReusableGraphicsLayerScope graphicsLayerScope = new ReusableGraphicsLayerScope();
    private static final LayerPositionalProperties tmpLayerPositionalProperties = new LayerPositionalProperties();
    private static final float[] tmpMatrix = Matrix.m3378constructorimpl$default(null, 1, null);
    private static final HitTestSource PointerInputSource = new HitTestSource() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$PointerInputSource$1
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: childHitTest-qzLsGqo, reason: not valid java name */
        public void mo4939childHitTestqzLsGqo(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer) {
            layoutNode.m4844hitTest6fMxITs$ui(pointerPosition, hitTestResult, pointerType, isInLayer);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: entityType-OLwlOKw, reason: not valid java name */
        public int mo4940entityTypeOLwlOKw() {
            return NodeKind.m4949constructorimpl(16);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r9v7 */
        /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
            java.lang.NullPointerException
            */
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean interceptOutOfBoundsChildEvents(androidx.compose.ui.Modifier.Node r9) {
            /*
                r8 = this;
                r8 = 16
                int r0 = androidx.compose.ui.node.NodeKind.m4949constructorimpl(r8)
                r1 = 0
                r2 = r1
            L8:
                r3 = 0
                if (r9 == 0) goto L5a
                boolean r4 = r9 instanceof androidx.compose.ui.node.PointerInputModifierNode
                r5 = 1
                if (r4 == 0) goto L19
                androidx.compose.ui.node.PointerInputModifierNode r9 = (androidx.compose.ui.node.PointerInputModifierNode) r9
                boolean r9 = r9.interceptOutOfBoundsChildEvents()
                if (r9 == 0) goto L55
                return r5
            L19:
                int r4 = r9.getKindSet()
                r4 = r4 & r0
                if (r4 == 0) goto L55
                boolean r4 = r9 instanceof androidx.compose.ui.node.DelegatingNode
                if (r4 == 0) goto L55
                r4 = r9
                androidx.compose.ui.node.DelegatingNode r4 = (androidx.compose.ui.node.DelegatingNode) r4
                androidx.compose.ui.Modifier$Node r4 = r4.getDelegate()
                r6 = r3
            L2c:
                if (r4 == 0) goto L52
                int r7 = r4.getKindSet()
                r7 = r7 & r0
                if (r7 == 0) goto L4d
                int r6 = r6 + 1
                if (r6 != r5) goto L3b
                r9 = r4
                goto L4d
            L3b:
                if (r2 != 0) goto L44
                androidx.compose.runtime.collection.MutableVector r2 = new androidx.compose.runtime.collection.MutableVector
                androidx.compose.ui.Modifier$Node[] r7 = new androidx.compose.ui.Modifier.Node[r8]
                r2.<init>(r7, r3)
            L44:
                if (r9 == 0) goto L4a
                r2.add(r9)
                r9 = r1
            L4a:
                r2.add(r4)
            L4d:
                androidx.compose.ui.Modifier$Node r4 = r4.getChild()
                goto L2c
            L52:
                if (r6 != r5) goto L55
                goto L8
            L55:
                androidx.compose.ui.Modifier$Node r9 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r2)
                goto L8
            L5a:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeCoordinator$Companion$PointerInputSource$1.interceptOutOfBoundsChildEvents(androidx.compose.ui.Modifier$Node):boolean");
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shouldHitTestChildren(LayoutNode parentLayoutNode) {
            return true;
        }
    };
    private static final HitTestSource SemanticsSource = new HitTestSource() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$SemanticsSource$1
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: childHitTest-qzLsGqo */
        public void mo4939childHitTestqzLsGqo(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer) {
            layoutNode.m4845hitTestSemantics6fMxITs$ui(pointerPosition, hitTestResult, pointerType, isInLayer);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: entityType-OLwlOKw */
        public int mo4940entityTypeOLwlOKw() {
            return NodeKind.m4949constructorimpl(8);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean interceptOutOfBoundsChildEvents(Modifier.Node node) {
            return false;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shouldHitTestChildren(LayoutNode parentLayoutNode) {
            SemanticsConfiguration semanticsConfiguration = parentLayoutNode.getSemanticsConfiguration();
            boolean z = false;
            if (semanticsConfiguration != null && semanticsConfiguration.getIsClearingSemantics()) {
                z = true;
            }
            return !z;
        }
    };
    private Density layerDensity = getLayoutNode().getDensity();
    private LayoutDirection layerLayoutDirection = getLayoutNode().getLayoutDirection();
    private float lastLayerAlpha = 0.8f;
    private long position = IntOffset.INSTANCE.m6161getZeronOccac();
    private Shape lastShape = RectangleShapeKt.getRectangleShape();
    private final Function0<Unit> invalidateParentLayer = new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$invalidateParentLayer$1
        {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4942invoke() {
            NodeCoordinator wrappedBy = this.this$0.getWrappedBy();
            if (wrappedBy != null) {
                wrappedBy.invalidateLayer();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            m4942invoke();
            return Unit.INSTANCE;
        }
    };

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u0013\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H&¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH&J7\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007H&¢\u0006\u0004\b\u0017\u0010\u0018ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "", "entityType", "Landroidx/compose/ui/node/NodeKind;", "entityType-OLwlOKw", "()I", "interceptOutOfBoundsChildEvents", "", "node", "Landroidx/compose/ui/Modifier$Node;", "shouldHitTestChildren", "parentLayoutNode", "Landroidx/compose/ui/node/LayoutNode;", "childHitTest", "", "layoutNode", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "hitTestResult", "Landroidx/compose/ui/node/HitTestResult;", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "isInLayer", "childHitTest-qzLsGqo", "(Landroidx/compose/ui/node/LayoutNode;JLandroidx/compose/ui/node/HitTestResult;IZ)V", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public interface HitTestSource {
        /* JADX INFO: renamed from: childHitTest-qzLsGqo */
        void mo4939childHitTestqzLsGqo(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer);

        /* JADX INFO: renamed from: entityType-OLwlOKw */
        int mo4940entityTypeOLwlOKw();

        boolean interceptOutOfBoundsChildEvents(Modifier.Node node);

        boolean shouldHitTestChildren(LayoutNode parentLayoutNode);
    }

    public NodeCoordinator(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
    }

    private final void ancestorToLocal(NodeCoordinator ancestor, MutableRect rect, boolean clipBounds) {
        if (ancestor == this) {
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.ancestorToLocal(ancestor, rect, clipBounds);
        }
        fromParentRect(rect, clipBounds);
    }

    /* JADX INFO: renamed from: ancestorToLocal-S_NoaFU, reason: not valid java name */
    private final long m4912ancestorToLocalS_NoaFU(NodeCoordinator ancestor, long offset, boolean includeMotionFrameOfReference) {
        if (ancestor == this) {
            return offset;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        return (nodeCoordinator == null || Intrinsics.areEqual(ancestor, nodeCoordinator)) ? m4927fromParentPosition8S9VItk(offset, includeMotionFrameOfReference) : m4927fromParentPosition8S9VItk(nodeCoordinator.m4912ancestorToLocalS_NoaFU(ancestor, offset, includeMotionFrameOfReference), includeMotionFrameOfReference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void drawContainedDrawModifiers(Canvas canvas, GraphicsLayer graphicsLayer) {
        Modifier.Node nodeM4930headH91voCI = m4930headH91voCI(NodeKind.m4949constructorimpl(4));
        if (nodeM4930headH91voCI == null) {
            performDraw(canvas, graphicsLayer);
        } else {
            getLayoutNode().getMDrawScope$ui().m4860draweZhPAX0$ui(canvas, IntSizeKt.m6205toSizeozmzZPI(mo4613getSizeYbymL2g()), this, nodeM4930headH91voCI, graphicsLayer);
        }
    }

    /* JADX INFO: renamed from: fromParentPosition-8S9VItk$default, reason: not valid java name */
    public static /* synthetic */ long m4913fromParentPosition8S9VItk$default(NodeCoordinator nodeCoordinator, long j, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: fromParentPosition-8S9VItk");
            return 0L;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return nodeCoordinator.m4927fromParentPosition8S9VItk(j, z);
    }

    private final void fromParentRect(MutableRect bounds, boolean clipBounds) {
        float fM6150getXimpl = IntOffset.m6150getXimpl(getPosition());
        bounds.setLeft(bounds.getLeft() - fM6150getXimpl);
        bounds.setRight(bounds.getRight() - fM6150getXimpl);
        float fM6151getYimpl = IntOffset.m6151getYimpl(getPosition());
        bounds.setTop(bounds.getTop() - fM6151getYimpl);
        bounds.setBottom(bounds.getBottom() - fM6151getYimpl);
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mapBounds(bounds, true);
            if (this.isClipping && clipBounds) {
                bounds.intersect(0.0f, 0.0f, (int) (mo4613getSizeYbymL2g() >> 32), (int) (mo4613getSizeYbymL2g() & 4294967295L));
                bounds.isEmpty();
            }
        }
    }

    private final Function2<Canvas, GraphicsLayer, Unit> getDrawBlock() {
        Function2 function2 = this._drawBlock;
        if (function2 != null) {
            return function2;
        }
        final Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$drawBlock$drawBlockCallToDrawModifiers$1
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m4941invoke() {
                NodeCoordinator nodeCoordinator = this.this$0;
                Canvas canvas = nodeCoordinator.drawBlockCanvas;
                canvas.getClass();
                nodeCoordinator.drawContainedDrawModifiers(canvas, this.this$0.drawBlockParentLayer);
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m4941invoke();
                return Unit.INSTANCE;
            }
        };
        Function2<Canvas, GraphicsLayer, Unit> function3 = new Function2<Canvas, GraphicsLayer, Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$drawBlock$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            public final void invoke(Canvas canvas, GraphicsLayer graphicsLayer) {
                boolean zIsPlaced = this.this$0.getLayoutNode().isPlaced();
                NodeCoordinator nodeCoordinator = this.this$0;
                if (!zIsPlaced) {
                    nodeCoordinator.lastLayerDrawingWasSkipped = true;
                    return;
                }
                nodeCoordinator.drawBlockCanvas = canvas;
                this.this$0.drawBlockParentLayer = graphicsLayer;
                OwnerSnapshotObserver snapshotObserver = this.this$0.getSnapshotObserver();
                snapshotObserver.observer.observeReads(this.this$0, NodeCoordinator.onCommitAffectingLayer, function0);
                this.this$0.lastLayerDrawingWasSkipped = false;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Canvas) obj, (GraphicsLayer) obj2);
                return Unit.INSTANCE;
            }
        };
        this._drawBlock = function3;
        return function3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OwnerSnapshotObserver getSnapshotObserver() {
        return LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver();
    }

    /* JADX INFO: renamed from: hasNode-H91voCI, reason: not valid java name */
    private final boolean m4914hasNodeH91voCI(int type) {
        Modifier.Node nodeHeadNode = headNode(NodeKindKt.m4958getIncludeSelfInTraversalH91voCI(type));
        return nodeHeadNode != null && DelegatableNodeKt.m4785has64DMado(nodeHeadNode, type);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modifier.Node headNode(boolean includeTail) {
        Modifier.Node tail;
        if (getLayoutNode().getOuterCoordinator$ui() == this) {
            return getLayoutNode().getNodes().getHead();
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (!includeTail) {
            if (nodeCoordinator != null) {
                return nodeCoordinator.getTail();
            }
            return null;
        }
        if (nodeCoordinator == null || (tail = nodeCoordinator.getTail()) == null) {
            return null;
        }
        return tail.getChild();
    }

    /* JADX INFO: renamed from: hit-5ShdDok, reason: not valid java name */
    private final void m4915hit5ShdDok(Modifier.Node node, HitTestSource hitTestSource, long j, HitTestResult hitTestResult, int i, boolean z) {
        if (node == null) {
            mo4833hitTestChildqzLsGqo(hitTestSource, j, hitTestResult, i, z);
            return;
        }
        int i2 = hitTestResult.hitDepth;
        hitTestResult.removeNodesInRange(hitTestResult.hitDepth + 1, hitTestResult.size());
        hitTestResult.hitDepth++;
        hitTestResult.values.add(node);
        hitTestResult.distanceFromEdgeAndFlags.add(HitTestResultKt.DistanceAndFlags(-1.0f, z, false));
        m4915hit5ShdDok(NodeCoordinatorKt.m4947nextUntilhw7D004(node, hitTestSource.mo4940entityTypeOLwlOKw(), NodeKind.m4949constructorimpl(2)), hitTestSource, j, hitTestResult, i, z);
        hitTestResult.hitDepth = i2;
    }

    /* JADX INFO: renamed from: hitNear-Fh5PU_I, reason: not valid java name */
    private final void m4916hitNearFh5PU_I(Modifier.Node node, HitTestSource hitTestSource, long j, HitTestResult hitTestResult, int i, boolean z, float f) {
        if (node == null) {
            mo4833hitTestChildqzLsGqo(hitTestSource, j, hitTestResult, i, z);
            return;
        }
        int i2 = hitTestResult.hitDepth;
        hitTestResult.removeNodesInRange(hitTestResult.hitDepth + 1, hitTestResult.size());
        hitTestResult.hitDepth++;
        hitTestResult.values.add(node);
        hitTestResult.distanceFromEdgeAndFlags.add(HitTestResultKt.DistanceAndFlags(f, z, false));
        m4919outOfBoundsHit8NAm7pk(NodeCoordinatorKt.m4947nextUntilhw7D004(node, hitTestSource.mo4940entityTypeOLwlOKw(), NodeKind.m4949constructorimpl(2)), hitTestSource, j, hitTestResult, i, z, f, true);
        hitTestResult.hitDepth = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v13 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "result" is null
        	at jadx.core.dex.visitors.PrepareForCodeGen.removeInstructions(PrepareForCodeGen.java:118)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:85)
        */
    /* JADX INFO: renamed from: isInExpandedTouchBounds-ThD-n1k, reason: not valid java name */
    private final boolean m4917isInExpandedTouchBoundsThDn1k(androidx.compose.ui.Modifier.Node r9, long r10, int r12) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeCoordinator.m4917isInExpandedTouchBoundsThDn1k(androidx.compose.ui.Modifier$Node, long, int):boolean");
    }

    /* JADX INFO: renamed from: offsetFromEdge-MK-Hz9U, reason: not valid java name */
    private final long m4918offsetFromEdgeMKHz9U(long pointerPosition) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (pointerPosition >> 32));
        float fMax = Math.max(0.0f, fIntBitsToFloat < 0.0f ? -fIntBitsToFloat : fIntBitsToFloat - getMeasuredWidth());
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (pointerPosition & 4294967295L));
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fMax)) << 32) | (((long) Float.floatToRawIntBits(Math.max(0.0f, fIntBitsToFloat2 < 0.0f ? -fIntBitsToFloat2 : fIntBitsToFloat2 - getMeasuredHeight()))) & 4294967295L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: outOfBoundsHit-8NAm7pk, reason: not valid java name */
    public final void m4919outOfBoundsHit8NAm7pk(final Modifier.Node node, final HitTestSource hitTestSource, final long j, final HitTestResult hitTestResult, final int i, final boolean z, final float f, final boolean z2) {
        if (node == null) {
            mo4833hitTestChildqzLsGqo(hitTestSource, j, hitTestResult, i, z);
            return;
        }
        if (m4917isInExpandedTouchBoundsThDn1k(node, j, i)) {
            hitTestResult.hitExpandedTouchBounds(node, z, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$outOfBoundsHit$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m4943invoke() {
                    this.this$0.m4919outOfBoundsHit8NAm7pk(NodeCoordinatorKt.m4947nextUntilhw7D004(node, hitTestSource.mo4940entityTypeOLwlOKw(), NodeKind.m4949constructorimpl(2)), hitTestSource, j, hitTestResult, i, z, f, z2);
                }

                public /* bridge */ /* synthetic */ Object invoke() {
                    m4943invoke();
                    return Unit.INSTANCE;
                }
            });
        } else if (z2) {
            m4916hitNearFh5PU_I(node, hitTestSource, j, hitTestResult, i, z, f);
        } else {
            m4921speculativeHitFh5PU_I(node, hitTestSource, j, hitTestResult, i, z, f);
        }
    }

    /* JADX INFO: renamed from: placeSelf-MLgxB_4, reason: not valid java name */
    private final void m4920placeSelfMLgxB_4(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock, GraphicsLayer explicitLayer) {
        if (explicitLayer != null) {
            if (!(layerBlock == null)) {
                InlineClassHelperKt.throwIllegalArgumentException("both ways to create layers shouldn't be used together");
            }
            if (this.explicitLayer != explicitLayer) {
                this.explicitLayer = null;
                updateLayerBlock$default(this, null, false, 2, null);
                this.explicitLayer = explicitLayer;
            }
            if (this.layer == null) {
                OwnedLayer ownedLayerCreateLayer = LayoutNodeKt.requireOwner(getLayoutNode()).createLayer(getDrawBlock(), this.invalidateParentLayer, explicitLayer);
                ownedLayerCreateLayer.mo5011resizeozmzZPI(getMeasuredSize());
                ownedLayerCreateLayer.mo5010movegyyYBs(position);
                this.layer = ownedLayerCreateLayer;
                getLayoutNode().setInnerLayerCoordinatorIsDirty$ui(true);
                this.invalidateParentLayer.invoke();
            }
        } else {
            if (this.explicitLayer != null) {
                this.explicitLayer = null;
                updateLayerBlock$default(this, null, false, 2, null);
            }
            updateLayerBlock$default(this, layerBlock, false, 2, null);
        }
        if (!IntOffset.m6149equalsimpl0(getPosition(), position)) {
            LayoutNodeKt.requireOwner(getLayoutNode()).voteFrameRate(FrameRateCategory.INSTANCE.m2606getHighNSsRyOo());
            m4935setPositiongyyYBs(position);
            getLayoutNode().getLayoutDelegate().getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.mo5010movegyyYBs(position);
            } else {
                NodeCoordinator nodeCoordinator = this.wrappedBy;
                if (nodeCoordinator != null) {
                    nodeCoordinator.invalidateLayer();
                }
            }
            getLayoutNode().onCoordinatorPositionChanged$ui();
            invalidateAlignmentLinesFromPositionChange(this);
            Owner owner = getLayoutNode().getOwner();
            if (owner != null) {
                owner.onLayoutChange(getLayoutNode());
            }
        }
        this.zIndex = zIndex;
        if (this == getLayoutNode().getOuterCoordinator$ui()) {
            RectManager.onLayoutPositionChanged$default(LayoutNodeKt.requireOwner(getLayoutNode()).getRectManager(), getLayoutNode(), false, 2, null);
        }
        if (getIsPlacingForAlignment()) {
            return;
        }
        captureRulersIfNeeded$ui(getMeasureResult$ui());
    }

    public static /* synthetic */ void rectInParent$ui$default(NodeCoordinator nodeCoordinator, MutableRect mutableRect, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: rectInParent");
            return;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        nodeCoordinator.rectInParent$ui(mutableRect, z, z2);
    }

    /* JADX INFO: renamed from: speculativeHit-Fh5PU_I, reason: not valid java name */
    private final void m4921speculativeHitFh5PU_I(final Modifier.Node node, final HitTestSource hitTestSource, final long j, final HitTestResult hitTestResult, final int i, final boolean z, final float f) {
        if (node == null) {
            mo4833hitTestChildqzLsGqo(hitTestSource, j, hitTestResult, i, z);
        } else if (hitTestSource.interceptOutOfBoundsChildEvents(node)) {
            hitTestResult.speculativeHit(node, f, z, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$speculativeHit$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m4944invoke() {
                    this.this$0.m4919outOfBoundsHit8NAm7pk(NodeCoordinatorKt.m4947nextUntilhw7D004(node, hitTestSource.mo4940entityTypeOLwlOKw(), NodeKind.m4949constructorimpl(2)), hitTestSource, j, hitTestResult, i, z, f, false);
                }

                public /* bridge */ /* synthetic */ Object invoke() {
                    m4944invoke();
                    return Unit.INSTANCE;
                }
            });
        } else {
            m4919outOfBoundsHit8NAm7pk(NodeCoordinatorKt.m4947nextUntilhw7D004(node, hitTestSource.mo4940entityTypeOLwlOKw(), NodeKind.m4949constructorimpl(2)), hitTestSource, j, hitTestResult, i, z, f, false);
        }
    }

    private final NodeCoordinator toCoordinator(LayoutCoordinates layoutCoordinates) {
        NodeCoordinator coordinator;
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates = layoutCoordinates instanceof LookaheadLayoutCoordinates ? (LookaheadLayoutCoordinates) layoutCoordinates : null;
        if (lookaheadLayoutCoordinates != null && (coordinator = lookaheadLayoutCoordinates.getCoordinator()) != null) {
            return coordinator;
        }
        layoutCoordinates.getClass();
        return (NodeCoordinator) layoutCoordinates;
    }

    /* JADX INFO: renamed from: toParentPosition-8S9VItk$default, reason: not valid java name */
    public static /* synthetic */ long m4922toParentPosition8S9VItk$default(NodeCoordinator nodeCoordinator, long j, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: toParentPosition-8S9VItk");
            return 0L;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return nodeCoordinator.m4936toParentPosition8S9VItk(j, z);
    }

    /* JADX INFO: renamed from: transformFromAncestor-EL8BTi8, reason: not valid java name */
    private final void m4923transformFromAncestorEL8BTi8(NodeCoordinator ancestor, float[] matrix) {
        if (Intrinsics.areEqual(ancestor, this)) {
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        nodeCoordinator.getClass();
        nodeCoordinator.m4923transformFromAncestorEL8BTi8(ancestor, matrix);
        if (!IntOffset.m6149equalsimpl0(getPosition(), IntOffset.INSTANCE.m6161getZeronOccac())) {
            float[] fArr = tmpMatrix;
            Matrix.m3387resetimpl(fArr);
            Matrix.m3400translateimpl$default(fArr, -IntOffset.m6150getXimpl(getPosition()), -IntOffset.m6151getYimpl(getPosition()), 0.0f, 4, null);
            Matrix.m3397timesAssign58bKbWc(matrix, fArr);
        }
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mo5007inverseTransform58bKbWc(matrix);
        }
    }

    /* JADX INFO: renamed from: transformToAncestor-EL8BTi8, reason: not valid java name */
    private final void m4924transformToAncestorEL8BTi8(NodeCoordinator ancestor, float[] matrix) {
        while (!Intrinsics.areEqual(this, ancestor)) {
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.mo5012transform58bKbWc(matrix);
            }
            long position = this.getPosition();
            if (!IntOffset.m6149equalsimpl0(position, IntOffset.INSTANCE.m6161getZeronOccac())) {
                float[] fArr = tmpMatrix;
                Matrix.m3387resetimpl(fArr);
                Matrix.m3400translateimpl$default(fArr, IntOffset.m6150getXimpl(position), IntOffset.m6151getYimpl(position), 0.0f, 4, null);
                Matrix.m3397timesAssign58bKbWc(matrix, fArr);
            }
            this = this.wrappedBy;
            this.getClass();
        }
    }

    public static /* synthetic */ void updateLayerBlock$default(NodeCoordinator nodeCoordinator, Function1 function1, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: updateLayerBlock");
            return;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        nodeCoordinator.updateLayerBlock(function1, z);
    }

    private final void updateLayerParameters(boolean invokeOnLayoutChange) {
        Owner owner;
        if (this.explicitLayer != null) {
            return;
        }
        OwnedLayer ownedLayer = this.layer;
        final Function1<? super GraphicsLayerScope, Unit> function1 = this.layerBlock;
        if (ownedLayer == null) {
            if (function1 == null) {
                return;
            }
            InlineClassHelperKt.throwIllegalStateException("null layer with a non-null layerBlock");
            return;
        }
        if (function1 == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("updateLayerParameters requires a non-null layerBlock");
            wq6.a();
            return;
        }
        ReusableGraphicsLayerScope reusableGraphicsLayerScope = graphicsLayerScope;
        reusableGraphicsLayerScope.reset();
        reusableGraphicsLayerScope.setGraphicsDensity$ui(getLayoutNode().getDensity());
        reusableGraphicsLayerScope.setLayoutDirection$ui(getLayoutNode().getLayoutDirection());
        reusableGraphicsLayerScope.m3462setSizeuvyYCjk(IntSizeKt.m6205toSizeozmzZPI(mo4613getSizeYbymL2g()));
        getSnapshotObserver().observer.observeReads(this, onCommitAffectingLayerParams, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator.updateLayerParameters.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m4945invoke() {
                function1.invoke(NodeCoordinator.graphicsLayerScope);
                boolean z = this.getLastShape() != NodeCoordinator.graphicsLayerScope.getShape();
                boolean z2 = this.getLastClip() != NodeCoordinator.graphicsLayerScope.getClip();
                if (z || z2) {
                    this.setLastShape$ui(NodeCoordinator.graphicsLayerScope.getShape());
                    this.setLastClip$ui(NodeCoordinator.graphicsLayerScope.getClip());
                    if (this.getWasLayerBlockInvoked() && (z2 || (this.getLastClip() && z))) {
                        this.getLayoutNode().invalidateSemantics$ui();
                    }
                }
                this.setWasLayerBlockInvoked$ui(true);
                NodeCoordinator.graphicsLayerScope.updateOutline$ui();
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m4945invoke();
                return Unit.INSTANCE;
            }
        });
        LayerPositionalProperties layerPositionalProperties = this.layerPositionalProperties;
        if (layerPositionalProperties == null) {
            layerPositionalProperties = new LayerPositionalProperties();
            this.layerPositionalProperties = layerPositionalProperties;
        }
        LayerPositionalProperties layerPositionalProperties2 = tmpLayerPositionalProperties;
        layerPositionalProperties2.copyFrom(layerPositionalProperties);
        layerPositionalProperties.copyFrom(reusableGraphicsLayerScope);
        ownedLayer.updateLayerProperties(reusableGraphicsLayerScope);
        boolean z = this.isClipping;
        this.isClipping = reusableGraphicsLayerScope.getClip();
        this.lastLayerAlpha = reusableGraphicsLayerScope.getAlpha();
        boolean zHasSameValuesAs = layerPositionalProperties2.hasSameValuesAs(layerPositionalProperties);
        if (invokeOnLayoutChange && ((!zHasSameValuesAs || z != this.isClipping) && (owner = getLayoutNode().getOwner()) != null)) {
            owner.onLayoutChange(getLayoutNode());
        }
        if (zHasSameValuesAs) {
            return;
        }
        LayoutNode layoutNode = getLayoutNode();
        LayoutNodeLayoutDelegate layoutDelegate = layoutNode.getLayoutDelegate();
        if (layoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() > 0) {
            if (layoutDelegate.getCoordinatesAccessedDuringModifierPlacement() || layoutDelegate.getCoordinatesAccessedDuringPlacement()) {
                LayoutNode.requestRelayout$ui$default(layoutNode, false, 1, null);
            }
            layoutDelegate.getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
        }
        layoutNode.onCoordinatorPositionChanged$ui();
        Owner ownerRequireOwner = LayoutNodeKt.requireOwner(layoutNode);
        RectManager rectManager = ownerRequireOwner.getRectManager();
        if (this == layoutNode.getOuterCoordinator$ui()) {
            RectManager.onLayoutPositionChanged$default(rectManager, layoutNode, false, 2, null);
        } else {
            rectManager.onLayoutLayerPositionalPropertiesChanged(layoutNode);
        }
        if (layoutNode.getGloballyPositionedObservers() > 0) {
            ownerRequireOwner.requestOnPositionedCallback(layoutNode);
        }
    }

    public static /* synthetic */ void updateLayerParameters$default(NodeCoordinator nodeCoordinator, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: updateLayerParameters");
            return;
        }
        if ((i & 1) != 0) {
            z = true;
        }
        nodeCoordinator.updateLayerParameters(z);
    }

    /* JADX INFO: renamed from: calculateMinimumTouchTargetPadding-E7KxVPU, reason: not valid java name */
    public final long m4925calculateMinimumTouchTargetPaddingE7KxVPU(long minimumTouchTargetSize) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (minimumTouchTargetSize >> 32)) - getMeasuredWidth();
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (minimumTouchTargetSize & 4294967295L)) - getMeasuredHeight();
        return Size.m2949constructorimpl((((long) Float.floatToRawIntBits(Math.max(0.0f, fIntBitsToFloat / 2.0f))) << 32) | (((long) Float.floatToRawIntBits(Math.max(0.0f, fIntBitsToFloat2 / 2.0f))) & 4294967295L));
    }

    /* JADX INFO: renamed from: distanceInMinimumTouchTarget-tz77jQw, reason: not valid java name */
    public final float m4926distanceInMinimumTouchTargettz77jQw(long pointerPosition, long minimumTouchTargetSize) {
        if (getMeasuredWidth() >= Float.intBitsToFloat((int) (minimumTouchTargetSize >> 32)) && getMeasuredHeight() >= Float.intBitsToFloat((int) (minimumTouchTargetSize & 4294967295L))) {
            return Float.POSITIVE_INFINITY;
        }
        long jM4925calculateMinimumTouchTargetPaddingE7KxVPU = m4925calculateMinimumTouchTargetPaddingE7KxVPU(minimumTouchTargetSize);
        float fIntBitsToFloat = Float.intBitsToFloat((int) (jM4925calculateMinimumTouchTargetPaddingE7KxVPU >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (jM4925calculateMinimumTouchTargetPaddingE7KxVPU & 4294967295L));
        long jM4918offsetFromEdgeMKHz9U = m4918offsetFromEdgeMKHz9U(pointerPosition);
        if ((fIntBitsToFloat > 0.0f || fIntBitsToFloat2 > 0.0f) && Float.intBitsToFloat((int) (jM4918offsetFromEdgeMKHz9U >> 32)) <= fIntBitsToFloat && Float.intBitsToFloat((int) (jM4918offsetFromEdgeMKHz9U & 4294967295L)) <= fIntBitsToFloat2) {
            return Offset.m2888getDistanceSquaredimpl(jM4918offsetFromEdgeMKHz9U);
        }
        return Float.POSITIVE_INFINITY;
    }

    public final void draw(Canvas canvas, GraphicsLayer graphicsLayer) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.drawLayer(canvas, graphicsLayer);
            return;
        }
        float fM6150getXimpl = IntOffset.m6150getXimpl(getPosition());
        float fM6151getYimpl = IntOffset.m6151getYimpl(getPosition());
        canvas.translate(fM6150getXimpl, fM6151getYimpl);
        drawContainedDrawModifiers(canvas, graphicsLayer);
        canvas.translate(-fM6150getXimpl, -fM6151getYimpl);
    }

    public final void drawBorder(Canvas canvas, Paint paint) {
        canvas.drawRect(0.5f, 0.5f, ((int) (getMeasuredSize() >> 32)) - 0.5f, ((int) (getMeasuredSize() & 4294967295L)) - 0.5f, paint);
    }

    public abstract void ensureLookaheadDelegateCreated();

    public final NodeCoordinator findCommonAncestor$ui(NodeCoordinator other) {
        LayoutNode layoutNode = other.getLayoutNode();
        LayoutNode layoutNode2 = getLayoutNode();
        if (layoutNode == layoutNode2) {
            Modifier.Node tail = other.getTail();
            Modifier.Node tail2 = getTail();
            int iM4949constructorimpl = NodeKind.m4949constructorimpl(2);
            if (!tail2.getNode().getIsAttached()) {
                InlineClassHelperKt.throwIllegalStateException("visitLocalAncestors called on an unattached node");
            }
            for (Modifier.Node parent = tail2.getNode().getParent(); parent != null; parent = parent.getParent()) {
                if ((parent.getKindSet() & iM4949constructorimpl) != 0 && parent == tail) {
                    return other;
                }
            }
            return this;
        }
        while (layoutNode.getDepth() > layoutNode2.getDepth()) {
            layoutNode = layoutNode.getParent$ui();
            layoutNode.getClass();
        }
        while (layoutNode2.getDepth() > layoutNode.getDepth()) {
            layoutNode2 = layoutNode2.getParent$ui();
            layoutNode2.getClass();
        }
        while (layoutNode != layoutNode2) {
            layoutNode = layoutNode.getParent$ui();
            layoutNode2 = layoutNode2.getParent$ui();
            if (layoutNode == null || layoutNode2 == null) {
                w01.a("layouts are not part of the same hierarchy");
                return null;
            }
        }
        if (layoutNode2 != getLayoutNode()) {
            if (layoutNode != other.getLayoutNode()) {
                return layoutNode.getInnerCoordinator$ui();
            }
            return other;
        }
        return this;
    }

    /* JADX INFO: renamed from: fromParentPosition-8S9VItk, reason: not valid java name */
    public long m4927fromParentPosition8S9VItk(long position, boolean includeMotionFrameOfReference) {
        if (includeMotionFrameOfReference || !getIsPlacedUnderMotionFrameOfReference()) {
            position = IntOffsetKt.m6163minusNvtHpc(position, getPosition());
        }
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer != null ? ownedLayer.mo5009mapOffset8S9VItk(position, true) : position;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public AlignmentLinesOwner getAlignmentLinesOwner() {
        return getLayoutNode().getLayoutDelegate().getAlignmentLinesOwner$ui();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getChild() {
        return this.wrapped;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LayoutCoordinates getCoordinates() {
        return this;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return getLayoutNode().getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return getLayoutNode().getDensity().getFontScale();
    }

    /* JADX INFO: renamed from: getForceMeasureWithLookaheadConstraints$ui, reason: from getter */
    public final boolean getForceMeasureWithLookaheadConstraints() {
        return this.forceMeasureWithLookaheadConstraints;
    }

    /* JADX INFO: renamed from: getForcePlaceWithLookaheadOffset$ui, reason: from getter */
    public final boolean getForcePlaceWithLookaheadOffset() {
        return this.forcePlaceWithLookaheadOffset;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean getIntroducesMotionFrameOfReference() {
        return getIsPlacedUnderMotionFrameOfReference();
    }

    /* JADX INFO: renamed from: getLastClip$ui, reason: from getter */
    public final boolean getLastClip() {
        return this.lastClip;
    }

    /* JADX INFO: renamed from: getLastLayerDrawingWasSkipped$ui, reason: from getter */
    public final boolean getLastLayerDrawingWasSkipped() {
        return this.lastLayerDrawingWasSkipped;
    }

    /* JADX INFO: renamed from: getLastMeasurementConstraints-msEJaDk$ui, reason: not valid java name */
    public final long m4928getLastMeasurementConstraintsmsEJaDk$ui() {
        return getMeasurementConstraints();
    }

    /* JADX INFO: renamed from: getLastShape$ui, reason: from getter */
    public final Shape getLastShape() {
        return this.lastShape;
    }

    public final OwnedLayer getLayer() {
        return this.layer;
    }

    public final Function1<GraphicsLayerScope, Unit> getLayerBlock() {
        return this.layerBlock;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return getLayoutNode().getLayoutDirection();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.compose.ui.node.MeasureScopeWithLayoutNode
    public LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public abstract LookaheadDelegate getLookaheadDelegate();

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public MeasureResult getMeasureResult$ui() {
        MeasureResult measureResult = this._measureResult;
        if (measureResult != null) {
            return measureResult;
        }
        k2d.a(UnmeasuredError);
        return null;
    }

    /* JADX INFO: renamed from: getMinimumTouchTargetSize-NH-jbRc, reason: not valid java name */
    public final long m4929getMinimumTouchTargetSizeNHjbRc() {
        return this.layerDensity.mo4558toSizeXkaWNTQ(getLayoutNode().getViewConfiguration().mo4852getMinimumTouchTargetSizeMYxV2XQ());
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getParent() {
        return this.wrappedBy;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentCoordinates() {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        onCoordinatesUsed$ui();
        return this.wrappedBy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v8 */
    @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        if (!getLayoutNode().getNodes().m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(64))) {
            return null;
        }
        getTail();
        kotlin.jvm.internal.Ref.ObjectRef objectRef = new kotlin.jvm.internal.Ref.ObjectRef();
        for (Modifier.Node tail = getLayoutNode().getNodes().getTail(); tail != null; tail = tail.getParent()) {
            if ((NodeKind.m4949constructorimpl(64) & tail.getKindSet()) != 0) {
                int iM4949constructorimpl = NodeKind.m4949constructorimpl(64);
                MutableVector mutableVector = null;
                Modifier.Node nodePop = tail;
                while (nodePop != 0) {
                    if (nodePop instanceof ParentDataModifierNode) {
                        objectRef.element = ((ParentDataModifierNode) nodePop).modifyParentData(getLayoutNode().getDensity(), objectRef.element);
                    } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                        Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                        int i = 0;
                        nodePop = nodePop;
                        while (delegate$ui != null) {
                            if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                i++;
                                if (i == 1) {
                                    nodePop = delegate$ui;
                                } else {
                                    if (mutableVector == null) {
                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    if (nodePop != 0) {
                                        mutableVector.add(nodePop);
                                        nodePop = 0;
                                    }
                                    mutableVector.add(delegate$ui);
                                }
                            }
                            delegate$ui = delegate$ui.getChild();
                            nodePop = nodePop;
                        }
                        if (i == 1) {
                        }
                    }
                    nodePop = DelegatableNodeKt.pop(mutableVector);
                }
            }
        }
        return objectRef.element;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentLayoutCoordinates() {
        if (!isAttached()) {
            StringBuilder sb = new StringBuilder(ExpectAttachedLayoutCoordinates);
            for (LayoutNode layoutNode = getLayoutNode(); layoutNode != null; layoutNode = layoutNode.getParent$ui()) {
                sb.append("\n|");
                sb.append(layoutNode);
                sb.append(" isAttached=");
                sb.append(layoutNode.isAttached());
                sb.append(" modifier=");
                sb.append(layoutNode.get_modifier());
                sb.append(" tail=");
                sb.append(getTail());
            }
            InlineClassHelperKt.throwIllegalStateException(sb.toString());
        }
        onCoordinatesUsed$ui();
        return getLayoutNode().getOuterCoordinator$ui().wrappedBy;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* JADX INFO: renamed from: getPosition-nOcc-ac, reason: from getter */
    public long getPosition() {
        return this.position;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Set<AlignmentLine> getProvidedAlignmentLines() {
        LinkedHashSet linkedHashSet = null;
        while (this != null) {
            MeasureResult measureResult = this._measureResult;
            Map<AlignmentLine, Integer> alignmentLines = measureResult != null ? measureResult.getAlignmentLines() : null;
            if (alignmentLines != null && (!alignmentLines.isEmpty())) {
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.addAll(alignmentLines.keySet());
            }
            this = this.wrapped;
        }
        return linkedHashSet == null ? SetsKt.emptySet() : linkedHashSet;
    }

    public final MutableRect getRectCache() {
        MutableRect mutableRect = this._rectCache;
        if (mutableRect != null) {
            return mutableRect;
        }
        MutableRect mutableRect2 = new MutableRect(0.0f, 0.0f, 0.0f, 0.0f);
        this._rectCache = mutableRect2;
        return mutableRect2;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: getSize-YbymL2g */
    public final long mo4613getSizeYbymL2g() {
        return getMeasuredSize();
    }

    public abstract Modifier.Node getTail();

    /* JADX INFO: renamed from: getWasLayerBlockInvoked$ui, reason: from getter */
    public final boolean getWasLayerBlockInvoked() {
        return this.wasLayerBlockInvoked;
    }

    /* JADX INFO: renamed from: getWrapped$ui, reason: from getter */
    public final NodeCoordinator getWrapped() {
        return this.wrapped;
    }

    /* JADX INFO: renamed from: getWrappedBy$ui, reason: from getter */
    public final NodeCoordinator getWrappedBy() {
        return this.wrappedBy;
    }

    public final float getZIndex() {
        return this.zIndex;
    }

    /* JADX INFO: renamed from: head-H91voCI, reason: not valid java name */
    public final Modifier.Node m4930headH91voCI(int type) {
        boolean zM4958getIncludeSelfInTraversalH91voCI = NodeKindKt.m4958getIncludeSelfInTraversalH91voCI(type);
        Modifier.Node tail = getTail();
        if (!zM4958getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return null;
        }
        for (Modifier.Node nodeHeadNode = headNode(zM4958getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & type) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & type) != 0) {
                return nodeHeadNode;
            }
            if (nodeHeadNode == tail) {
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: hitTest-qzLsGqo, reason: not valid java name */
    public final void m4931hitTestqzLsGqo(HitTestSource hitTestSource, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer) {
        boolean z;
        Modifier.Node nodeM4930headH91voCI = m4930headH91voCI(hitTestSource.mo4940entityTypeOLwlOKw());
        boolean z2 = false;
        if (!m4938withinLayerBoundsk4lQ0M(pointerPosition)) {
            if (PointerType.m4529equalsimpl0(pointerType, PointerType.INSTANCE.m4536getTouchT8wyACA())) {
                float fM4926distanceInMinimumTouchTargettz77jQw = m4926distanceInMinimumTouchTargettz77jQw(pointerPosition, m4929getMinimumTouchTargetSizeNHjbRc());
                if ((Float.floatToRawIntBits(fM4926distanceInMinimumTouchTargettz77jQw) & Integer.MAX_VALUE) >= 2139095040 || !hitTestResult.isHitInMinimumTouchTargetBetter(fM4926distanceInMinimumTouchTargettz77jQw, false)) {
                    return;
                }
                m4916hitNearFh5PU_I(nodeM4930headH91voCI, hitTestSource, pointerPosition, hitTestResult, pointerType, false, fM4926distanceInMinimumTouchTargettz77jQw);
                return;
            }
            return;
        }
        if (nodeM4930headH91voCI == null) {
            mo4833hitTestChildqzLsGqo(hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer);
            return;
        }
        if (m4932isPointerInBoundsk4lQ0M(pointerPosition)) {
            m4915hit5ShdDok(nodeM4930headH91voCI, hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer);
            return;
        }
        float fM4926distanceInMinimumTouchTargettz77jQw2 = !PointerType.m4529equalsimpl0(pointerType, PointerType.INSTANCE.m4536getTouchT8wyACA()) ? Float.POSITIVE_INFINITY : m4926distanceInMinimumTouchTargettz77jQw(pointerPosition, m4929getMinimumTouchTargetSizeNHjbRc());
        if ((Float.floatToRawIntBits(fM4926distanceInMinimumTouchTargettz77jQw2) & Integer.MAX_VALUE) < 2139095040) {
            z = isInLayer;
            if (hitTestResult.isHitInMinimumTouchTargetBetter(fM4926distanceInMinimumTouchTargettz77jQw2, z)) {
                z2 = true;
            }
        } else {
            z = isInLayer;
        }
        m4919outOfBoundsHit8NAm7pk(nodeM4930headH91voCI, hitTestSource, pointerPosition, hitTestResult, pointerType, z, fM4926distanceInMinimumTouchTargettz77jQw2, z2);
    }

    /* JADX INFO: renamed from: hitTestChild-qzLsGqo */
    public void mo4833hitTestChildqzLsGqo(HitTestSource hitTestSource, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer) {
        NodeCoordinator nodeCoordinator = this.wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.m4931hitTestqzLsGqo(hitTestSource, m4913fromParentPosition8S9VItk$default(nodeCoordinator, pointerPosition, false, 2, null), hitTestResult, pointerType, isInLayer);
        }
    }

    public void invalidateLayer() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.invalidateLayer();
        }
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean isAttached() {
        return getTail().getIsAttached();
    }

    /* JADX INFO: renamed from: isPointerInBounds-k-4lQ0M, reason: not valid java name */
    public final boolean m4932isPointerInBoundsk4lQ0M(long pointerPosition) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (pointerPosition >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (pointerPosition & 4294967295L));
        return fIntBitsToFloat >= 0.0f && fIntBitsToFloat2 >= 0.0f && fIntBitsToFloat < ((float) getMeasuredWidth()) && fIntBitsToFloat2 < ((float) getMeasuredHeight());
    }

    public final boolean isTransparent() {
        if (this.layer != null && this.lastLayerAlpha <= 0.0f) {
            return true;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            return nodeCoordinator.isTransparent();
        }
        return false;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValidOwnerScope() {
        return (this.layer == null || this.released || !getLayoutNode().isAttached()) ? false : true;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Rect localBoundingBoxOf(LayoutCoordinates sourceCoordinates, boolean clipBounds) {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        if (!sourceCoordinates.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinates " + sourceCoordinates + " is not attached!");
        }
        NodeCoordinator coordinator = toCoordinator(sourceCoordinates);
        coordinator.onCoordinatesUsed$ui();
        NodeCoordinator nodeCoordinatorFindCommonAncestor$ui = findCommonAncestor$ui(coordinator);
        MutableRect rectCache = getRectCache();
        rectCache.setLeft(0.0f);
        rectCache.setTop(0.0f);
        rectCache.setRight((int) (sourceCoordinates.mo4613getSizeYbymL2g() >> 32));
        rectCache.setBottom((int) (sourceCoordinates.mo4613getSizeYbymL2g() & 4294967295L));
        NodeCoordinator nodeCoordinator = coordinator;
        while (nodeCoordinator != nodeCoordinatorFindCommonAncestor$ui) {
            boolean z = clipBounds;
            rectInParent$ui$default(nodeCoordinator, rectCache, z, false, 4, null);
            if (rectCache.isEmpty()) {
                return Rect.INSTANCE.getZero();
            }
            nodeCoordinator = nodeCoordinator.wrappedBy;
            nodeCoordinator.getClass();
            clipBounds = z;
        }
        ancestorToLocal(nodeCoordinatorFindCommonAncestor$ui, rectCache, clipBounds);
        return MutableRectKt.toRect(rectCache);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-R5De75A */
    public long mo4614localPositionOfR5De75A(LayoutCoordinates sourceCoordinates, long relativeToSource) {
        return mo4615localPositionOfS_NoaFU(sourceCoordinates, relativeToSource, true);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-S_NoaFU */
    public long mo4615localPositionOfS_NoaFU(LayoutCoordinates sourceCoordinates, long relativeToSource, boolean includeMotionFrameOfReference) {
        if (sourceCoordinates instanceof LookaheadLayoutCoordinates) {
            LookaheadLayoutCoordinates lookaheadLayoutCoordinates = (LookaheadLayoutCoordinates) sourceCoordinates;
            lookaheadLayoutCoordinates.getCoordinator().onCoordinatesUsed$ui();
            return Offset.m2881constructorimpl(lookaheadLayoutCoordinates.mo4615localPositionOfS_NoaFU(this, Offset.m2881constructorimpl(relativeToSource ^ (-9223372034707292160L)), includeMotionFrameOfReference) ^ (-9223372034707292160L));
        }
        NodeCoordinator coordinator = toCoordinator(sourceCoordinates);
        coordinator.onCoordinatesUsed$ui();
        NodeCoordinator nodeCoordinatorFindCommonAncestor$ui = findCommonAncestor$ui(coordinator);
        while (coordinator != nodeCoordinatorFindCommonAncestor$ui) {
            relativeToSource = coordinator.m4936toParentPosition8S9VItk(relativeToSource, includeMotionFrameOfReference);
            coordinator = coordinator.wrappedBy;
            coordinator.getClass();
        }
        return m4912ancestorToLocalS_NoaFU(nodeCoordinatorFindCommonAncestor$ui, relativeToSource, includeMotionFrameOfReference);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToRoot-MK-Hz9U */
    public long mo4616localToRootMKHz9U(long relativeToLocal) {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        onCoordinatesUsed$ui();
        long jM4922toParentPosition8S9VItk$default = relativeToLocal;
        for (NodeCoordinator nodeCoordinator = this; nodeCoordinator != null; nodeCoordinator = nodeCoordinator.wrappedBy) {
            if (ComposeUiFlags.isRectManagerOffsetUsageFromLayoutCoordinatesEnabled) {
                LayoutNode layoutNode = nodeCoordinator.getLayoutNode();
                if (nodeCoordinator == layoutNode.getOuterCoordinator$ui() && !layoutNode.getHasPositionalLayerTransformationsInOffsetFromRoot()) {
                    long jM5271getOffsetFromRectListForBjo55l4 = LayoutNodeKt.requireOwner(layoutNode).getRectManager().m5271getOffsetFromRectListForBjo55l4(layoutNode);
                    if (!IntOffset.m6149equalsimpl0(jM5271getOffsetFromRectListForBjo55l4, IntOffset.INSTANCE.m6160getMaxnOccac())) {
                        return IntOffsetKt.m6165plusNvtHpc(jM4922toParentPosition8S9VItk$default, jM5271getOffsetFromRectListForBjo55l4);
                    }
                }
            }
            jM4922toParentPosition8S9VItk$default = m4922toParentPosition8S9VItk$default(nodeCoordinator, jM4922toParentPosition8S9VItk$default, false, 2, null);
        }
        return jM4922toParentPosition8S9VItk$default;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U */
    public long mo4617localToScreenMKHz9U(long relativeToLocal) {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        return LayoutNodeKt.requireOwner(getLayoutNode()).mo4538localToScreenMKHz9U(mo4616localToRootMKHz9U(relativeToLocal));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToWindow-MK-Hz9U */
    public long mo4618localToWindowMKHz9U(long relativeToLocal) {
        return LayoutNodeKt.requireOwner(getLayoutNode()).mo5014calculatePositionInWindowMKHz9U(mo4616localToRootMKHz9U(relativeToLocal));
    }

    public final void onCoordinatesUsed$ui() {
        getLayoutNode().getLayoutDelegate().onCoordinatesUsed();
    }

    public void onLayoutModifierNodeChanged() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
        }
    }

    public final void onLayoutNodeDetach() {
        releaseLayer();
        if (getLayoutNode().isPlaced()) {
            onUnplaced();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v8 */
    public void onMeasureResultChanged(int width, int height) {
        NodeCoordinator nodeCoordinator;
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mo5011resizeozmzZPI(IntSize.m6188constructorimpl((((long) width) << 32) | (((long) height) & 4294967295L)));
        } else if (getLayoutNode().isPlaced() && (nodeCoordinator = this.wrappedBy) != null) {
            nodeCoordinator.invalidateLayer();
        }
        m4674setMeasuredSizeozmzZPI(IntSize.m6188constructorimpl((((long) height) & 4294967295L) | (((long) width) << 32)));
        if (this.layerBlock != null) {
            updateLayerParameters(false);
        }
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(4);
        boolean zM4958getIncludeSelfInTraversalH91voCI = NodeKindKt.m4958getIncludeSelfInTraversalH91voCI(iM4949constructorimpl);
        Modifier.Node tail = getTail();
        if (zM4958getIncludeSelfInTraversalH91voCI || (tail = tail.getParent()) != null) {
            for (Modifier.Node nodeHeadNode = headNode(zM4958getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM4949constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
                if ((nodeHeadNode.getKindSet() & iM4949constructorimpl) != 0) {
                    Modifier.Node nodePop = nodeHeadNode;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof DrawModifierNode) {
                            ((DrawModifierNode) nodePop).onMeasureResultChanged();
                        } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                            Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                            int i = 0;
                            nodePop = nodePop;
                            while (delegate$ui != null) {
                                if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                    i++;
                                    if (i == 1) {
                                        nodePop = delegate$ui;
                                    } else {
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (nodePop != 0) {
                                            mutableVector.add(nodePop);
                                            nodePop = 0;
                                        }
                                        mutableVector.add(delegate$ui);
                                    }
                                }
                                delegate$ui = delegate$ui.getChild();
                                nodePop = nodePop;
                            }
                            if (i == 1) {
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector);
                    }
                }
                if (nodeHeadNode == tail) {
                    break;
                }
            }
        }
        Owner owner = getLayoutNode().getOwner();
        if (owner != null) {
            owner.onLayoutChange(getLayoutNode());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v10 */
    public final void onMeasured() {
        Modifier.Node parent;
        if (m4914hasNodeH91voCI(NodeKind.m4949constructorimpl(128))) {
            Snapshot.Companion companion = Snapshot.INSTANCE;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                int iM4949constructorimpl = NodeKind.m4949constructorimpl(128);
                boolean zM4958getIncludeSelfInTraversalH91voCI = NodeKindKt.m4958getIncludeSelfInTraversalH91voCI(iM4949constructorimpl);
                if (!zM4958getIncludeSelfInTraversalH91voCI) {
                    parent = getTail().getParent();
                    if (parent == null) {
                    }
                    Unit unit = Unit.INSTANCE;
                }
                parent = getTail();
                for (Modifier.Node nodeHeadNode = headNode(zM4958getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM4949constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
                    if ((nodeHeadNode.getKindSet() & iM4949constructorimpl) != 0) {
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = nodeHeadNode;
                        while (nodePop != 0) {
                            if (nodePop instanceof LayoutAwareModifierNode) {
                                ((LayoutAwareModifierNode) nodePop).mo217onRemeasuredozmzZPI(getMeasuredSize());
                            } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate$ui != null) {
                                    if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate$ui;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                mutableVector.add(nodePop);
                                                nodePop = 0;
                                            }
                                            mutableVector.add(delegate$ui);
                                        }
                                    }
                                    delegate$ui = delegate$ui.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    if (nodeHeadNode == parent) {
                        break;
                    }
                }
                Unit unit2 = Unit.INSTANCE;
            } finally {
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6 */
    public final void onPlaced() {
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(4194304);
        boolean zM4958getIncludeSelfInTraversalH91voCI = NodeKindKt.m4958getIncludeSelfInTraversalH91voCI(iM4949constructorimpl);
        Modifier.Node tail = getTail();
        if (!zM4958getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = headNode(zM4958getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM4949constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & iM4949constructorimpl) != 0) {
                Modifier.Node nodePop = nodeHeadNode;
                MutableVector mutableVector = null;
                while (nodePop != 0) {
                    if (nodePop instanceof LayoutAwareModifierNode) {
                        ((LayoutAwareModifierNode) nodePop).onPlaced(this);
                    } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                        Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                        int i = 0;
                        nodePop = nodePop;
                        while (delegate$ui != null) {
                            if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                i++;
                                if (i == 1) {
                                    nodePop = delegate$ui;
                                } else {
                                    if (mutableVector == null) {
                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    if (nodePop != 0) {
                                        mutableVector.add(nodePop);
                                        nodePop = 0;
                                    }
                                    mutableVector.add(delegate$ui);
                                }
                            }
                            delegate$ui = delegate$ui.getChild();
                            nodePop = nodePop;
                        }
                        if (i == 1) {
                        }
                    }
                    nodePop = DelegatableNodeKt.pop(mutableVector);
                }
            }
            if (nodeHeadNode == tail) {
                return;
            }
        }
    }

    public final void onRelease() {
        this.released = true;
        this.invalidateParentLayer.invoke();
        releaseLayer();
        if (IntOffset.m6149equalsimpl0(getPosition(), IntOffset.INSTANCE.m6161getZeronOccac())) {
            return;
        }
        getLayoutNode().onCoordinatorPositionChanged$ui();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6 */
    public final void onUnplaced() {
        if (m4914hasNodeH91voCI(NodeKind.m4949constructorimpl(1048576))) {
            int iM4949constructorimpl = NodeKind.m4949constructorimpl(1048576);
            boolean zM4958getIncludeSelfInTraversalH91voCI = NodeKindKt.m4958getIncludeSelfInTraversalH91voCI(iM4949constructorimpl);
            Modifier.Node tail = getTail();
            if (!zM4958getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
                return;
            }
            for (Modifier.Node nodeHeadNode = headNode(zM4958getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM4949constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
                if ((nodeHeadNode.getKindSet() & iM4949constructorimpl) != 0) {
                    Modifier.Node nodePop = nodeHeadNode;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof UnplacedAwareModifierNode) {
                            ((UnplacedAwareModifierNode) nodePop).onUnplaced();
                        } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                            Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                            int i = 0;
                            nodePop = nodePop;
                            while (delegate$ui != null) {
                                if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                    i++;
                                    if (i == 1) {
                                        nodePop = delegate$ui;
                                    } else {
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (nodePop != 0) {
                                            mutableVector.add(nodePop);
                                            nodePop = 0;
                                        }
                                        mutableVector.add(delegate$ui);
                                    }
                                }
                                delegate$ui = delegate$ui.getChild();
                                nodePop = nodePop;
                            }
                            if (i == 1) {
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector);
                    }
                }
                if (nodeHeadNode == tail) {
                    return;
                }
            }
        }
    }

    public void performDraw(Canvas canvas, GraphicsLayer graphicsLayer) {
        NodeCoordinator nodeCoordinator = this.wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.draw(canvas, graphicsLayer);
        }
    }

    /* JADX INFO: renamed from: performingMeasure-K40F9xA, reason: not valid java name */
    public final Placeable m4933performingMeasureK40F9xA(long constraints, Function0<? extends Placeable> block) {
        m4675setMeasurementConstraintsBRTryo0(constraints);
        return (Placeable) block.invoke();
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo4673placeAtf8xVGno(long position, float zIndex, GraphicsLayer layer) {
        if (!this.forcePlaceWithLookaheadOffset) {
            m4920placeSelfMLgxB_4(position, zIndex, null, layer);
            return;
        }
        LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
        lookaheadDelegate.getClass();
        m4920placeSelfMLgxB_4(lookaheadDelegate.getPosition(), zIndex, null, layer);
    }

    /* JADX INFO: renamed from: placeSelfApparentToRealOffset-MLgxB_4, reason: not valid java name */
    public final void m4934placeSelfApparentToRealOffsetMLgxB_4(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock, GraphicsLayer layer) {
        m4920placeSelfMLgxB_4(IntOffset.m6154plusqkQi6aY(position, getApparentToRealOffset()), zIndex, layerBlock, layer);
    }

    public final void rectInParent$ui(MutableRect bounds, boolean clipBounds, boolean clipToMinimumTouchTargetSize) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            if (this.isClipping) {
                if (clipToMinimumTouchTargetSize) {
                    long jM4929getMinimumTouchTargetSizeNHjbRc = m4929getMinimumTouchTargetSizeNHjbRc();
                    float fIntBitsToFloat = Float.intBitsToFloat((int) (jM4929getMinimumTouchTargetSizeNHjbRc >> 32)) / 2.0f;
                    float fIntBitsToFloat2 = Float.intBitsToFloat((int) (jM4929getMinimumTouchTargetSizeNHjbRc & 4294967295L)) / 2.0f;
                    bounds.intersect(-fIntBitsToFloat, -fIntBitsToFloat2, ((int) (mo4613getSizeYbymL2g() >> 32)) + fIntBitsToFloat, ((int) (4294967295L & mo4613getSizeYbymL2g())) + fIntBitsToFloat2);
                } else if (clipBounds) {
                    bounds.intersect(0.0f, 0.0f, (int) (mo4613getSizeYbymL2g() >> 32), (int) (4294967295L & mo4613getSizeYbymL2g()));
                }
                if (bounds.isEmpty()) {
                    return;
                }
            }
            ownedLayer.mapBounds(bounds, false);
        }
        float fM6150getXimpl = IntOffset.m6150getXimpl(getPosition());
        bounds.setLeft(bounds.getLeft() + fM6150getXimpl);
        bounds.setRight(bounds.getRight() + fM6150getXimpl);
        float fM6151getYimpl = IntOffset.m6151getYimpl(getPosition());
        bounds.setTop(bounds.getTop() + fM6151getYimpl);
        bounds.setBottom(bounds.getBottom() + fM6151getYimpl);
    }

    public final void releaseLayer() {
        if (this.layer != null) {
            if (this.explicitLayer != null) {
                this.explicitLayer = null;
            }
            updateLayerBlock$default(this, null, false, 2, null);
            LayoutNode.requestRelayout$ui$default(getLayoutNode(), false, 1, null);
        }
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public void replace$ui() {
        GraphicsLayer graphicsLayer = this.explicitLayer;
        if (graphicsLayer != null) {
            mo4673placeAtf8xVGno(getPosition(), this.zIndex, graphicsLayer);
        } else {
            mo4606placeAtf8xVGno(getPosition(), this.zIndex, this.layerBlock);
        }
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U */
    public long mo4619screenToLocalMKHz9U(long relativeToScreen) {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        return mo4614localPositionOfR5De75A(LayoutCoordinatesKt.findRootCoordinates(this), LayoutNodeKt.requireOwner(getLayoutNode()).mo4539screenToLocalMKHz9U(relativeToScreen));
    }

    public final void setForceMeasureWithLookaheadConstraints$ui(boolean z) {
        this.forceMeasureWithLookaheadConstraints = z;
    }

    public final void setForcePlaceWithLookaheadOffset$ui(boolean z) {
        this.forcePlaceWithLookaheadOffset = z;
    }

    public final void setLastClip$ui(boolean z) {
        this.lastClip = z;
    }

    public final void setLastShape$ui(Shape shape) {
        this.lastShape = shape;
    }

    public abstract void setLookaheadDelegate(LookaheadDelegate lookaheadDelegate);

    /* JADX WARN: Code duplicated, block: B:15:0x0034  */
    /* JADX WARN: Code duplicated, block: B:28:? A[RETURN, SYNTHETIC] */
    public void setMeasureResult$ui(MeasureResult measureResult) {
        MeasureResult measureResult2 = this._measureResult;
        if (measureResult != measureResult2) {
            this._measureResult = measureResult;
            if (measureResult2 == null || measureResult.get$w() != measureResult2.get$w() || measureResult.get$h() != measureResult2.get$h()) {
                onMeasureResultChanged(measureResult.get$w(), measureResult.get$h());
            }
            MutableObjectIntMap<AlignmentLine> mutableObjectIntMap = this.oldAlignmentLines;
            if (mutableObjectIntMap != null) {
                mutableObjectIntMap.getClass();
                if (!mutableObjectIntMap.isNotEmpty()) {
                    if (measureResult.getAlignmentLines().isEmpty()) {
                        return;
                    }
                }
            } else if (measureResult.getAlignmentLines().isEmpty()) {
                return;
            }
            if (NodeCoordinatorKt.compareEquals(this.oldAlignmentLines, measureResult.getAlignmentLines())) {
                return;
            }
            getAlignmentLinesOwner().getAlignmentLines().onAlignmentsChanged();
            MutableObjectIntMap<AlignmentLine> mutableObjectIntMapMutableObjectIntMapOf = this.oldAlignmentLines;
            if (mutableObjectIntMapMutableObjectIntMapOf == null) {
                mutableObjectIntMapMutableObjectIntMapOf = ObjectIntMapKt.mutableObjectIntMapOf();
                this.oldAlignmentLines = mutableObjectIntMapMutableObjectIntMapOf;
            }
            mutableObjectIntMapMutableObjectIntMapOf.clear();
            for (Map.Entry<AlignmentLine, Integer> entry : measureResult.getAlignmentLines().entrySet()) {
                mutableObjectIntMapMutableObjectIntMapOf.set(entry.getKey(), entry.getValue().intValue());
            }
        }
    }

    /* JADX INFO: renamed from: setPosition--gyyYBs, reason: not valid java name */
    public void m4935setPositiongyyYBs(long j) {
        this.position = j;
    }

    public final void setWasLayerBlockInvoked$ui(boolean z) {
        this.wasLayerBlockInvoked = z;
    }

    public final void setWrapped$ui(NodeCoordinator nodeCoordinator) {
        this.wrapped = nodeCoordinator;
    }

    public final void setWrappedBy$ui(NodeCoordinator nodeCoordinator) {
        this.wrappedBy = nodeCoordinator;
    }

    public final void setZIndex(float f) {
        this.zIndex = f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v7 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "result" is null
        	at jadx.core.dex.visitors.PrepareForCodeGen.removeInstructions(PrepareForCodeGen.java:118)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:85)
        */
    public final boolean shouldSharePointerInputWithSiblings() {
        /*
            r10 = this;
            r0 = 16
            int r1 = androidx.compose.ui.node.NodeKind.m4949constructorimpl(r0)
            boolean r1 = androidx.compose.ui.node.NodeKindKt.m4958getIncludeSelfInTraversalH91voCI(r1)
            androidx.compose.ui.Modifier$Node r10 = r10.headNode(r1)
            r1 = 0
            if (r10 != 0) goto L12
            return r1
        L12:
            boolean r2 = r10.getIsAttached()
            if (r2 == 0) goto L99
            int r2 = androidx.compose.ui.node.NodeKind.m4949constructorimpl(r0)
            androidx.compose.ui.Modifier$Node r3 = r10.getNode()
            boolean r3 = r3.getIsAttached()
            if (r3 != 0) goto L2c
            java.lang.String r3 = "visitLocalDescendants called on an unattached node"
            androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateException(r3)
        L2c:
            androidx.compose.ui.Modifier$Node r10 = r10.getNode()
            int r3 = r10.getAggregateChildKindSet()
            r3 = r3 & r2
            if (r3 == 0) goto L99
        L37:
            if (r10 == 0) goto L99
            int r3 = r10.getKindSet()
            r3 = r3 & r2
            if (r3 == 0) goto L94
            r3 = 0
            r4 = r10
            r5 = r3
        L43:
            if (r4 == 0) goto L94
            boolean r6 = r4 instanceof androidx.compose.ui.node.PointerInputModifierNode
            r7 = 1
            if (r6 == 0) goto L53
            androidx.compose.ui.node.PointerInputModifierNode r4 = (androidx.compose.ui.node.PointerInputModifierNode) r4
            boolean r4 = r4.sharePointerInputWithSiblings()
            if (r4 == 0) goto L8f
            return r7
        L53:
            int r6 = r4.getKindSet()
            r6 = r6 & r2
            if (r6 == 0) goto L8f
            boolean r6 = r4 instanceof androidx.compose.ui.node.DelegatingNode
            if (r6 == 0) goto L8f
            r6 = r4
            androidx.compose.ui.node.DelegatingNode r6 = (androidx.compose.ui.node.DelegatingNode) r6
            androidx.compose.ui.Modifier$Node r6 = r6.getDelegate()
            r8 = r1
        L66:
            if (r6 == 0) goto L8c
            int r9 = r6.getKindSet()
            r9 = r9 & r2
            if (r9 == 0) goto L87
            int r8 = r8 + 1
            if (r8 != r7) goto L75
            r4 = r6
            goto L87
        L75:
            if (r5 != 0) goto L7e
            androidx.compose.runtime.collection.MutableVector r5 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r9 = new androidx.compose.ui.Modifier.Node[r0]
            r5.<init>(r9, r1)
        L7e:
            if (r4 == 0) goto L84
            r5.add(r4)
            r4 = r3
        L84:
            r5.add(r6)
        L87:
            androidx.compose.ui.Modifier$Node r6 = r6.getChild()
            goto L66
        L8c:
            if (r8 != r7) goto L8f
            goto L43
        L8f:
            androidx.compose.ui.Modifier$Node r4 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r5)
            goto L43
        L94:
            androidx.compose.ui.Modifier$Node r10 = r10.getChild()
            goto L37
        L99:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeCoordinator.shouldSharePointerInputWithSiblings():boolean");
    }

    /* JADX INFO: renamed from: toParentPosition-8S9VItk, reason: not valid java name */
    public long m4936toParentPosition8S9VItk(long position, boolean includeMotionFrameOfReference) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            position = ownedLayer.mo5009mapOffset8S9VItk(position, false);
        }
        return (includeMotionFrameOfReference || !getIsPlacedUnderMotionFrameOfReference()) ? IntOffsetKt.m6165plusNvtHpc(position, getPosition()) : position;
    }

    public final Rect touchBoundsInRoot() {
        if (!isAttached()) {
            return Rect.INSTANCE.getZero();
        }
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(this);
        MutableRect rectCache = getRectCache();
        long jM4925calculateMinimumTouchTargetPaddingE7KxVPU = m4925calculateMinimumTouchTargetPaddingE7KxVPU(m4929getMinimumTouchTargetSizeNHjbRc());
        int i = (int) (jM4925calculateMinimumTouchTargetPaddingE7KxVPU >> 32);
        rectCache.setLeft(-Float.intBitsToFloat(i));
        int i2 = (int) (jM4925calculateMinimumTouchTargetPaddingE7KxVPU & 4294967295L);
        rectCache.setTop(-Float.intBitsToFloat(i2));
        rectCache.setRight(getMeasuredWidth() + Float.intBitsToFloat(i));
        rectCache.setBottom(getMeasuredHeight() + Float.intBitsToFloat(i2));
        while (this != layoutCoordinatesFindRootCoordinates) {
            this.rectInParent$ui(rectCache, false, true);
            if (rectCache.isEmpty()) {
                return Rect.INSTANCE.getZero();
            }
            this = this.wrappedBy;
            this.getClass();
        }
        return MutableRectKt.toRect(rectCache);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformFrom-EL8BTi8 */
    public void mo4620transformFromEL8BTi8(LayoutCoordinates sourceCoordinates, float[] matrix) {
        NodeCoordinator coordinator = toCoordinator(sourceCoordinates);
        coordinator.onCoordinatesUsed$ui();
        NodeCoordinator nodeCoordinatorFindCommonAncestor$ui = findCommonAncestor$ui(coordinator);
        Matrix.m3387resetimpl(matrix);
        coordinator.m4924transformToAncestorEL8BTi8(nodeCoordinatorFindCommonAncestor$ui, matrix);
        m4923transformFromAncestorEL8BTi8(nodeCoordinatorFindCommonAncestor$ui, matrix);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformToScreen-58bKbWc */
    public void mo4621transformToScreen58bKbWc(float[] matrix) {
        Owner ownerRequireOwner = LayoutNodeKt.requireOwner(getLayoutNode());
        NodeCoordinator coordinator = toCoordinator(LayoutCoordinatesKt.findRootCoordinates(this));
        m4924transformToAncestorEL8BTi8(coordinator, matrix);
        if (ownerRequireOwner instanceof MatrixPositionCalculator) {
            ((MatrixPositionCalculator) ownerRequireOwner).mo4374localToScreen58bKbWc(matrix);
            return;
        }
        long jPositionOnScreen = LayoutCoordinatesKt.positionOnScreen(coordinator);
        if ((androidx.compose.ui.geometry.InlineClassHelperKt.DualUnsignedFloatMask & jPositionOnScreen) != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            Matrix.m3399translateimpl(matrix, Float.intBitsToFloat((int) (jPositionOnScreen >> 32)), Float.intBitsToFloat((int) (jPositionOnScreen & 4294967295L)), 0.0f);
        }
    }

    public final void updateLayerBlock(Function1<? super GraphicsLayerScope, Unit> layerBlock, boolean forceUpdateLayerParameters) {
        Owner owner;
        if (!(layerBlock == null || this.explicitLayer == null)) {
            InlineClassHelperKt.throwIllegalArgumentException("layerBlock can't be provided when explicitLayer is provided");
        }
        LayoutNode layoutNode = getLayoutNode();
        boolean z = (!forceUpdateLayerParameters && this.layerBlock == layerBlock && Intrinsics.areEqual(this.layerDensity, layoutNode.getDensity()) && this.layerLayoutDirection == layoutNode.getLayoutDirection()) ? false : true;
        this.layerDensity = layoutNode.getDensity();
        this.layerLayoutDirection = layoutNode.getLayoutDirection();
        if (layoutNode.isAttached() && layerBlock != null) {
            this.layerBlock = layerBlock;
            if (this.layer != null) {
                if (z) {
                    updateLayerParameters$default(this, false, 1, null);
                    return;
                }
                return;
            }
            OwnedLayer ownedLayerCreateLayer$default = Owner.createLayer$default(LayoutNodeKt.requireOwner(layoutNode), getDrawBlock(), this.invalidateParentLayer, null, 4, null);
            ownedLayerCreateLayer$default.mo5011resizeozmzZPI(getMeasuredSize());
            ownedLayerCreateLayer$default.mo5010movegyyYBs(getPosition());
            this.layer = ownedLayerCreateLayer$default;
            updateLayerParameters$default(this, false, 1, null);
            layoutNode.setInnerLayerCoordinatorIsDirty$ui(true);
            this.invalidateParentLayer.invoke();
            return;
        }
        this.layerBlock = null;
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            if (!MatrixKt.m3403isIdentity58bKbWc(ownedLayer.mo5006getUnderlyingMatrixsQKQjiQ())) {
                layoutNode.onCoordinatorPositionChanged$ui();
            }
            ownedLayer.destroy();
            layoutNode.setInnerLayerCoordinatorIsDirty$ui(true);
            this.invalidateParentLayer.invoke();
            if (isAttached() && layoutNode.isPlaced() && (owner = layoutNode.getOwner()) != null) {
                owner.onLayoutChange(layoutNode);
            }
        }
        this.layer = null;
        this.lastLayerDrawingWasSkipped = false;
    }

    public final void visitNodes(int mask, boolean includeTail, Function1<? super Modifier.Node, Unit> block) {
        Modifier.Node tail = getTail();
        if (!includeTail && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = headNode(includeTail); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & mask) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & mask) != 0) {
                block.invoke(nodeHeadNode);
            }
            if (nodeHeadNode == tail) {
                return;
            }
        }
    }

    /* JADX INFO: renamed from: visitNodes-aLcG6gQ, reason: not valid java name */
    public final /* synthetic */ <T> void m4937visitNodesaLcG6gQ(int type, Function1<? super T, Unit> block) {
        boolean zM4958getIncludeSelfInTraversalH91voCI = NodeKindKt.m4958getIncludeSelfInTraversalH91voCI(type);
        Modifier.Node tail = getTail();
        if (!zM4958getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = headNode(zM4958getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & type) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & type) != 0) {
                for (Modifier.Node nodePop = nodeHeadNode; nodePop != null; nodePop = DelegatableNodeKt.pop(null)) {
                    Intrinsics.reifiedOperationMarker(3, "T");
                    block.invoke(nodePop);
                }
            }
            if (nodeHeadNode == tail) {
                return;
            }
        }
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: windowToLocal-MK-Hz9U */
    public long mo4622windowToLocalMKHz9U(long relativeToWindow) {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(this);
        return mo4614localPositionOfR5De75A(layoutCoordinatesFindRootCoordinates, Offset.m2893minusMKHz9U(LayoutNodeKt.requireOwner(getLayoutNode()).mo5013calculateLocalPositionMKHz9U(relativeToWindow), LayoutCoordinatesKt.positionInRoot(layoutCoordinatesFindRootCoordinates)));
    }

    public final void withPositionTranslation(Canvas canvas, Function1<? super Canvas, Unit> block) {
        float fM6150getXimpl = IntOffset.m6150getXimpl(getPosition());
        float fM6151getYimpl = IntOffset.m6151getYimpl(getPosition());
        canvas.translate(fM6150getXimpl, fM6151getYimpl);
        block.invoke(canvas);
        canvas.translate(-fM6150getXimpl, -fM6151getYimpl);
    }

    /* JADX INFO: renamed from: withinLayerBounds-k-4lQ0M, reason: not valid java name */
    public final boolean m4938withinLayerBoundsk4lQ0M(long pointerPosition) {
        if ((((androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase ^ (pointerPosition & androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase)) - androidx.compose.ui.geometry.InlineClassHelperKt.Uint64Low32) & (-9223372034707292160L)) != 0) {
            return false;
        }
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer == null || !this.isClipping || ownedLayer.mo5008isInLayerk4lQ0M(pointerPosition);
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator$Companion;", "", "<init>", "()V", "ExpectAttachedLayoutCoordinates", "", "UnmeasuredError", "onCommitAffectingLayerParams", "Lkotlin/Function1;", "Landroidx/compose/ui/node/NodeCoordinator;", "", "onCommitAffectingLayer", "graphicsLayerScope", "Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "tmpLayerPositionalProperties", "Landroidx/compose/ui/node/LayerPositionalProperties;", "tmpMatrix", "Landroidx/compose/ui/graphics/Matrix;", "[F", "PointerInputSource", "Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "getPointerInputSource", "()Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "SemanticsSource", "getSemanticsSource", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HitTestSource getPointerInputSource() {
            return NodeCoordinator.PointerInputSource;
        }

        public final HitTestSource getSemanticsSource() {
            return NodeCoordinator.SemanticsSource;
        }

        private Companion() {
        }
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo4606placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
        if (this.forcePlaceWithLookaheadOffset) {
            LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
            lookaheadDelegate.getClass();
            m4920placeSelfMLgxB_4(lookaheadDelegate.getPosition(), zIndex, layerBlock, null);
            return;
        }
        m4920placeSelfMLgxB_4(position, zIndex, layerBlock, null);
    }
}
