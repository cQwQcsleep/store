package androidx.compose.ui.node;

import android.view.View;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.tooling.CompositionErrorContext;
import androidx.compose.runtime.tooling.CompositionErrorContextKt;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.layout.LayoutNodeSubcompositionsState;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.ModifierInfo;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.platform.JvmActuals_jvmKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.spatial.RectManager;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import java.util.Comparator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u008c\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0001\u0018\u0000 ¦\u00032\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b:\b¥\u0003¦\u0003§\u0003¨\u0003B\u001b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010C\u001a\u00020DH\u0002J\b\u0010K\u001a\u00020DH\u0002J\r\u0010O\u001a\u00020DH\u0000¢\u0006\u0002\bPJ\u001d\u0010Q\u001a\u00020D2\u0012\u0010R\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020D0SH\u0086\bJ#\u0010T\u001a\u00020D2\u0018\u0010R\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020D0UH\u0086\bJ\u0015\u0010h\u001a\n\u0018\u00010ij\u0004\u0018\u0001`jH\u0017¢\u0006\u0002\u0010kJ\u001e\u0010}\u001a\u00020D2\u0006\u0010~\u001a\u00020\f2\u0006\u0010\u007f\u001a\u00020\u0000H\u0000¢\u0006\u0003\b\u0080\u0001J\u0012\u0010\u0081\u0001\u001a\u00030\u0082\u00012\u0006\u0010\u007f\u001a\u00020\u0000H\u0002J\u000f\u0010\u0083\u0001\u001a\u00020DH\u0000¢\u0006\u0003\b\u0084\u0001J \u0010\u0085\u0001\u001a\u00020D2\u0006\u0010~\u001a\u00020\f2\u0007\u0010\u0086\u0001\u001a\u00020\fH\u0000¢\u0006\u0003\b\u0087\u0001J\u000f\u0010\u0088\u0001\u001a\u00020DH\u0000¢\u0006\u0003\b\u0089\u0001J\u0012\u0010\u008a\u0001\u001a\u00020D2\u0007\u0010\u008b\u0001\u001a\u00020\u0000H\u0002J*\u0010\u008c\u0001\u001a\u00020D2\u0007\u0010\u008d\u0001\u001a\u00020\f2\u0007\u0010\u008e\u0001\u001a\u00020\f2\u0007\u0010\u0086\u0001\u001a\u00020\fH\u0000¢\u0006\u0003\b\u008f\u0001J\t\u0010\u0090\u0001\u001a\u00020\nH\u0016J\u000f\u0010\u0094\u0001\u001a\u00020DH\u0000¢\u0006\u0003\b\u0095\u0001J\u000f\u0010\u0096\u0001\u001a\u00020DH\u0000¢\u0006\u0003\b\u0097\u0001J\n\u0010\u009e\u0001\u001a\u00030\u0099\u0001H\u0002J\u0017\u0010\u009f\u0001\u001a\u00020D2\u0006\u0010]\u001a\u00020\\H\u0000¢\u0006\u0003\b \u0001J\u000f\u0010¡\u0001\u001a\u00020DH\u0000¢\u0006\u0003\b¢\u0001J\n\u0010ª\u0001\u001a\u00030\u0082\u0001H\u0016J\u0014\u0010\u00ad\u0001\u001a\u00030\u0082\u00012\b\b\u0002\u0010m\u001a\u00020\fH\u0002J\n\u0010¶\u0001\u001a\u00030µ\u0001H\u0002J\u0010\u0010·\u0001\u001a\u00020\f2\u0007\u0010¸\u0001\u001a\u00020\fJ\u0010\u0010¹\u0001\u001a\u00020\f2\u0007\u0010º\u0001\u001a\u00020\fJ\u0010\u0010»\u0001\u001a\u00020\f2\u0007\u0010¸\u0001\u001a\u00020\fJ\u0010\u0010¼\u0001\u001a\u00020\f2\u0007\u0010º\u0001\u001a\u00020\fJ\u0010\u0010½\u0001\u001a\u00020\f2\u0007\u0010¸\u0001\u001a\u00020\fJ\u0010\u0010¾\u0001\u001a\u00020\f2\u0007\u0010º\u0001\u001a\u00020\fJ\u0010\u0010¿\u0001\u001a\u00020\f2\u0007\u0010¸\u0001\u001a\u00020\fJ\u0010\u0010À\u0001\u001a\u00020\f2\u0007\u0010º\u0001\u001a\u00020\fJ\u0012\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u0001J\t\u0010á\u0001\u001a\u00020DH\u0002J\u000f\u0010\u009b\u0002\u001a\u00020DH\u0000¢\u0006\u0003\b\u009c\u0002J\u0013\u0010§\u0002\u001a\u00020D2\b\u0010¢\u0002\u001a\u00030\u009e\u0002H\u0002J\t\u0010¨\u0002\u001a\u00020DH\u0002J\u000f\u0010©\u0002\u001a\u00020DH\u0000¢\u0006\u0003\bª\u0002J!\u0010½\u0002\u001a\u00020D2\u0007\u0010¾\u0002\u001a\u00020\f2\u0007\u0010¿\u0002\u001a\u00020\fH\u0000¢\u0006\u0003\bÀ\u0002J\u000f\u0010Á\u0002\u001a\u00020DH\u0000¢\u0006\u0003\bÂ\u0002J\u000f\u0010Ã\u0002\u001a\u00020DH\u0000¢\u0006\u0003\bÄ\u0002J%\u0010Å\u0002\u001a\u00020D2\b\u0010Æ\u0002\u001a\u00030Ç\u00022\n\u0010È\u0002\u001a\u0005\u0018\u00010É\u0002H\u0000¢\u0006\u0003\bÊ\u0002J=\u0010Ë\u0002\u001a\u00020D2\b\u0010Ì\u0002\u001a\u00030Í\u00022\b\u0010Î\u0002\u001a\u00030Ï\u00022\n\b\u0002\u0010Ð\u0002\u001a\u00030Ñ\u00022\t\b\u0002\u0010Ò\u0002\u001a\u00020\nH\u0000¢\u0006\u0006\bÓ\u0002\u0010Ô\u0002J=\u0010Õ\u0002\u001a\u00020D2\b\u0010Ì\u0002\u001a\u00030Í\u00022\b\u0010Ö\u0002\u001a\u00030Ï\u00022\n\b\u0002\u0010Ð\u0002\u001a\u00030Ñ\u00022\t\b\u0002\u0010Ò\u0002\u001a\u00020\nH\u0000¢\u0006\u0006\b×\u0002\u0010Ô\u0002J\u0018\u0010Ø\u0002\u001a\u00020D2\u0007\u0010Ù\u0002\u001a\u00020\u0000H\u0000¢\u0006\u0003\bÚ\u0002J0\u0010Û\u0002\u001a\u00020D2\t\b\u0002\u0010Ü\u0002\u001a\u00020\n2\t\b\u0002\u0010Ý\u0002\u001a\u00020\n2\t\b\u0002\u0010Þ\u0002\u001a\u00020\nH\u0000¢\u0006\u0003\bß\u0002J0\u0010à\u0002\u001a\u00020D2\t\b\u0002\u0010Ü\u0002\u001a\u00020\n2\t\b\u0002\u0010Ý\u0002\u001a\u00020\n2\t\b\u0002\u0010Þ\u0002\u001a\u00020\nH\u0000¢\u0006\u0003\bá\u0002J\u000f\u0010â\u0002\u001a\u00020DH\u0000¢\u0006\u0003\bã\u0002J\u000f\u0010ä\u0002\u001a\u00020DH\u0000¢\u0006\u0003\bå\u0002J\u000f\u0010æ\u0002\u001a\u00020DH\u0000¢\u0006\u0003\bç\u0002J*\u0010|\u001a\u0003Hè\u0002\"\u0005\b\u0000\u0010è\u00022\u000e\u0010R\u001a\n\u0012\u0005\u0012\u0003Hè\u00020é\u0002H\u0080\b¢\u0006\u0006\bê\u0002\u0010ë\u0002J\u001a\u0010ì\u0002\u001a\u00020D2\t\b\u0002\u0010Ü\u0002\u001a\u00020\nH\u0000¢\u0006\u0003\bí\u0002J\u001a\u0010î\u0002\u001a\u00020D2\t\b\u0002\u0010Ü\u0002\u001a\u00020\nH\u0000¢\u0006\u0003\bï\u0002J\u000f\u0010ð\u0002\u001a\u00020DH\u0000¢\u0006\u0003\bñ\u0002J\u0010\u0010ò\u0002\u001a\t\u0012\u0005\u0012\u00030ó\u00020>H\u0016J\u000f\u0010ô\u0002\u001a\u00020DH\u0000¢\u0006\u0003\bõ\u0002J\u001d\u0010ö\u0002\u001a\u00020\n2\f\b\u0002\u0010÷\u0002\u001a\u0005\u0018\u00010ø\u0002H\u0000¢\u0006\u0003\bù\u0002J\u001d\u0010ú\u0002\u001a\u00020\n2\f\b\u0002\u0010÷\u0002\u001a\u0005\u0018\u00010ø\u0002H\u0000¢\u0006\u0003\bû\u0002J\u000f\u0010\u0084\u0003\u001a\u00020DH\u0000¢\u0006\u0003\b\u0085\u0003J\u000f\u0010\u0086\u0003\u001a\u00020DH\u0000¢\u0006\u0003\b\u0087\u0003J\u000f\u0010\u0088\u0003\u001a\u00020DH\u0000¢\u0006\u0003\b\u0089\u0003J\u0012\u0010\u008a\u0003\u001a\u00020D2\t\b\u0002\u0010\u008b\u0003\u001a\u00020\nJ\u0007\u0010\u008c\u0003\u001a\u00020DJ\u0012\u0010\u008d\u0003\u001a\u00020D2\t\b\u0002\u0010\u008b\u0003\u001a\u00020\nJ\u000f\u0010\u008e\u0003\u001a\u00020DH\u0000¢\u0006\u0003\b\u008f\u0003J\t\u0010\u0090\u0003\u001a\u00020DH\u0016J\t\u0010\u0091\u0003\u001a\u00020DH\u0016J%\u0010\u0092\u0003\u001a\u00020D2\u0013\u0010R\u001a\u000f\u0012\u0005\u0012\u00030\u0093\u0003\u0012\u0004\u0012\u00020D0SH\u0080\b¢\u0006\u0003\b\u0094\u0003J%\u0010\u0095\u0003\u001a\u00020D2\u0013\u0010R\u001a\u000f\u0012\u0005\u0012\u00030\u0082\u0002\u0012\u0004\u0012\u00020D0SH\u0080\b¢\u0006\u0003\b\u0096\u0003J\u000f\u0010\u0097\u0003\u001a\u00020DH\u0000¢\u0006\u0003\b\u0098\u0003J\t\u0010\u0099\u0003\u001a\u00020DH\u0002J\u000f\u0010\u009a\u0003\u001a\u00020DH\u0000¢\u0006\u0003\b\u009b\u0003J\t\u0010¢\u0003\u001a\u00020DH\u0016J\t\u0010£\u0003\u001a\u00020DH\u0016J\t\u0010¤\u0003\u001a\u00020DH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u00020\u0019X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001f\u001a\u00020 X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b!\u0010\u001b\"\u0004\b\"\u0010\u001dR\u001c\u0010#\u001a\u00020\u0019X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b$\u0010\u001b\"\u0004\b%\u0010\u001dR\u001a\u0010&\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0015\"\u0004\b(\u0010\u0017R\u001a\u0010)\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0015\"\u0004\b+\u0010\u0017R\u001a\u0010,\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0010\"\u0004\b.\u0010\u0012R\u001a\u0010/\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0015\"\u0004\b1\u0010\u0017R(\u00103\u001a\u0004\u0018\u00010\u00002\b\u00102\u001a\u0004\u0018\u00010\u0000@BX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0013\u00108\u001a\u0004\u0018\u00010\n8F¢\u0006\u0006\u001a\u0004\b8\u00109R\u000e\u0010:\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00000<X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00000>8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@R\u0016\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010BX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020F0>8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010@R\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020F0>8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bI\u0010@R\u000e\u0010J\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00000B8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bM\u0010NR\u001a\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00000>8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bW\u0010@R\u0010\u0010X\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010Y\u001a\u0004\u0018\u00010\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bZ\u00105R\"\u0010]\u001a\u0004\u0018\u00010\\2\b\u0010[\u001a\u0004\u0018\u00010\\@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b^\u0010_R$\u0010`\u001a\n\u0018\u00010aj\u0004\u0018\u0001`bX\u0080\u000e¢\u0006\u0010\n\u0002\u0010g\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u0014\u0010l\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bl\u0010\u0015R\u001a\u0010m\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010\u0010\"\u0004\bo\u0010\u0012R\u0014\u0010p\u001a\u00020q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\br\u0010sR\u0016\u0010t\u001a\u0004\u0018\u00010u8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bv\u0010wR\u0014\u0010x\u001a\u00020y8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bz\u0010{R\u000e\u0010|\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0091\u0001\u001a\u00020\nX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0092\u0001\u0010\u0015\"\u0005\b\u0093\u0001\u0010\u0017R\u0012\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0099\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u0099\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R\u000f\u0010\u009d\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010£\u0001\u001a\b\u0012\u0004\u0012\u00020\u00000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010¤\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010¥\u0001\u001a\b\u0012\u0004\u0012\u00020\u00000B8@X\u0081\u0004¢\u0006\u000f\u0012\u0006\b¦\u0001\u0010§\u0001\u001a\u0005\b¨\u0001\u0010NR\u0016\u0010©\u0001\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b©\u0001\u0010\u0015R\u0016\u0010«\u0001\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b¬\u0001\u0010\u0015R+\u0010¯\u0001\u001a\u00030®\u00012\u0007\u0010[\u001a\u00030®\u0001@VX\u0096\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b°\u0001\u0010±\u0001\"\u0006\b²\u0001\u0010³\u0001R\u0012\u0010´\u0001\u001a\u0005\u0018\u00010µ\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010Â\u0001\u001a\u00030Á\u00012\u0007\u0010[\u001a\u00030Á\u0001@VX\u0096\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÃ\u0001\u0010Ä\u0001\"\u0006\bÅ\u0001\u0010Æ\u0001R+\u0010È\u0001\u001a\u00030Ç\u00012\u0007\u0010[\u001a\u00030Ç\u0001@VX\u0096\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÉ\u0001\u0010Ê\u0001\"\u0006\bË\u0001\u0010Ì\u0001R+\u0010Î\u0001\u001a\u00030Í\u00012\u0007\u0010[\u001a\u00030Í\u0001@VX\u0096\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÏ\u0001\u0010Ð\u0001\"\u0006\bÑ\u0001\u0010Ò\u0001R+\u0010Ô\u0001\u001a\u00030Ó\u00012\u0007\u0010[\u001a\u00030Ó\u0001@VX\u0096\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÕ\u0001\u0010Ö\u0001\"\u0006\b×\u0001\u0010Ø\u0001R\u001a\u0010Ù\u0001\u001a\u0005\u0018\u00010Ú\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\bÛ\u0001\u0010Ü\u0001R\u0016\u0010º\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bâ\u0001\u0010\u0010R\u0016\u0010¸\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bã\u0001\u0010\u0010R\u0016\u0010ä\u0001\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bå\u0001\u0010\u0015R\u0018\u0010æ\u0001\u001a\u00030ç\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bè\u0001\u0010é\u0001R\u0016\u0010ê\u0001\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bê\u0001\u0010\u0015R\u0013\u0010ë\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bë\u0001\u0010\u0015R\u0016\u0010ì\u0001\u001a\u00020\f8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bí\u0001\u0010\u0010R\u0018\u0010î\u0001\u001a\u00030ï\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bð\u0001\u0010ñ\u0001R\u0018\u0010ò\u0001\u001a\u00030ï\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bó\u0001\u0010ñ\u0001R \u0010ô\u0001\u001a\u00030ï\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bõ\u0001\u0010ñ\u0001\"\u0006\bö\u0001\u0010÷\u0001R\u0010\u0010ø\u0001\u001a\u00030ï\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010ù\u0001\u001a\u00020\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0000\u0012\u0006\bú\u0001\u0010§\u0001\u001a\u0005\bû\u0001\u0010\u0015\"\u0005\bü\u0001\u0010\u0017R\u0018\u0010ý\u0001\u001a\u00030þ\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\bÿ\u0001\u0010\u0080\u0002R\u0018\u0010\u0081\u0002\u001a\u00030\u0082\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0083\u0002\u0010\u0084\u0002R\u0018\u0010\u0085\u0002\u001a\u00030\u0086\u0002X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0087\u0002\u0010\u0088\u0002R\u0018\u0010\u0089\u0002\u001a\u00030\u0082\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u008a\u0002\u0010\u0084\u0002R\u0018\u0010\u008b\u0002\u001a\u00030\u008c\u00028BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008d\u0002\u0010\u008e\u0002R\"\u0010\u008f\u0002\u001a\u0005\u0018\u00010\u0090\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0091\u0002\u0010\u0092\u0002\"\u0006\b\u0093\u0002\u0010\u0094\u0002R\u0012\u0010\u0095\u0002\u001a\u0005\u0018\u00010\u0082\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0096\u0002\u001a\u00020\nX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0002\u0010\u0015\"\u0005\b\u0098\u0002\u0010\u0017R\u001a\u0010\u0099\u0002\u001a\u0005\u0018\u00010\u0082\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u009a\u0002\u0010\u0084\u0002R\u0010\u0010\u009d\u0002\u001a\u00030\u009e\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u009f\u0002\u001a\u0005\u0018\u00010\u009e\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010 \u0002\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b¡\u0002\u0010\u0015R+\u0010¢\u0002\u001a\u00030\u009e\u00022\u0007\u0010[\u001a\u00030\u009e\u00028V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\b£\u0002\u0010¤\u0002\"\u0006\b¥\u0002\u0010¦\u0002R\u0018\u0010«\u0002\u001a\u00030¬\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u00ad\u0002\u0010®\u0002R-\u0010¯\u0002\u001a\u0010\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020D\u0018\u00010SX\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b°\u0002\u0010±\u0002\"\u0006\b²\u0002\u0010³\u0002R-\u0010´\u0002\u001a\u0010\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020D\u0018\u00010SX\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bµ\u0002\u0010±\u0002\"\u0006\b¶\u0002\u0010³\u0002R\u001d\u0010·\u0002\u001a\u00020\nX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¸\u0002\u0010\u0015\"\u0005\b¹\u0002\u0010\u0017R'\u0010º\u0002\u001a\u00020\f2\u0006\u0010[\u001a\u00020\f@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b»\u0002\u0010\u0010\"\u0005\b¼\u0002\u0010\u0012R\u0016\u0010ü\u0002\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bý\u0002\u0010\u0015R\u0016\u0010þ\u0002\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bÿ\u0002\u0010\u0015R\u0016\u0010\u0080\u0003\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0081\u0003\u0010\u0015R\u0016\u0010\u0082\u0003\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0083\u0003\u0010\u0015R\u0019\u0010\u009c\u0003\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009d\u0003\u0010\u009e\u0003R\u001c\u0010\u009f\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050>8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b \u0003\u0010@R \u0010¡\u0003\u001a\u00020\n2\u0006\u0010[\u001a\u00020\n@RX\u0096\u000e¢\u0006\t\n\u0000\u001a\u0005\b¡\u0003\u0010\u0015¨\u0006©\u0003"}, d2 = {"Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "Landroidx/compose/ui/layout/Remeasurement;", "Landroidx/compose/ui/node/OwnerScope;", "Landroidx/compose/ui/layout/LayoutInfo;", "Landroidx/compose/ui/semantics/SemanticsInfo;", "Landroidx/compose/ui/node/ComposeUiNode;", "Landroidx/compose/ui/node/InteroperableComposeUiNode;", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "isVirtual", "", "semanticsId", "", "<init>", "(ZI)V", "getSemanticsId", "()I", "setSemanticsId", "(I)V", "hasPositionalLayerTransformationsInOffsetFromRoot", "getHasPositionalLayerTransformationsInOffsetFromRoot$ui", "()Z", "setHasPositionalLayerTransformationsInOffsetFromRoot$ui", "(Z)V", "lastOffsetFromParent", "Landroidx/compose/ui/unit/IntOffset;", "getLastOffsetFromParent-nOcc-ac$ui", "()J", "setLastOffsetFromParent--gyyYBs$ui", "(J)V", "J", "lastSize", "Landroidx/compose/ui/unit/IntSize;", "getLastSize-YbymL2g$ui", "setLastSize-ozmzZPI$ui", "outerToInnerOffset", "getOuterToInnerOffset-nOcc-ac$ui", "setOuterToInnerOffset--gyyYBs$ui", "outerToInnerOffsetDirty", "getOuterToInnerOffsetDirty$ui", "setOuterToInnerOffsetDirty$ui", "addedToRectList", "getAddedToRectList$ui", "setAddedToRectList$ui", "compositeKeyHash", "getCompositeKeyHash", "setCompositeKeyHash", "isVirtualLookaheadRoot", "isVirtualLookaheadRoot$ui", "setVirtualLookaheadRoot$ui", "newRoot", "lookaheadRoot", "getLookaheadRoot$ui", "()Landroidx/compose/ui/node/LayoutNode;", "setLookaheadRoot", "(Landroidx/compose/ui/node/LayoutNode;)V", "isPlacedInLookahead", "()Ljava/lang/Boolean;", "virtualChildrenCount", "_foldedChildren", "Landroidx/compose/ui/node/MutableVectorWithMutationTracking;", "foldedChildren", "", "getFoldedChildren$ui", "()Ljava/util/List;", "_unfoldedChildren", "Landroidx/compose/runtime/collection/MutableVector;", "recreateUnfoldedChildrenIfDirty", "", "childMeasurables", "Landroidx/compose/ui/layout/Measurable;", "getChildMeasurables$ui", "childLookaheadMeasurables", "getChildLookaheadMeasurables$ui", "unfoldedVirtualChildrenListDirty", "invalidateUnfoldedVirtualChildren", "_children", "get_children$ui", "()Landroidx/compose/runtime/collection/MutableVector;", "updateChildrenIfDirty", "updateChildrenIfDirty$ui", "forEachChild", "block", "Lkotlin/Function1;", "forEachChildIndexed", "Lkotlin/Function2;", "children", "getChildren$ui", "_foldedParent", "parent", "getParent$ui", "value", "Landroidx/compose/ui/node/Owner;", "owner", "getOwner$ui", "()Landroidx/compose/ui/node/Owner;", "interopViewFactoryHolder", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "Landroidx/compose/ui/viewinterop/InteropViewFactoryHolder;", "getInteropViewFactoryHolder$ui", "()Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "setInteropViewFactoryHolder$ui", "(Landroidx/compose/ui/viewinterop/AndroidViewHolder;)V", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "getInteropView", "Landroid/view/View;", "Landroidx/compose/ui/viewinterop/InteropView;", "()Landroid/view/View;", "isAttached", "depth", "getDepth$ui", "setDepth$ui", "layoutState", "Landroidx/compose/ui/node/LayoutNode$LayoutState;", "getLayoutState$ui", "()Landroidx/compose/ui/node/LayoutNode$LayoutState;", "lookaheadPassDelegate", "Landroidx/compose/ui/node/LookaheadPassDelegate;", "getLookaheadPassDelegate$ui", "()Landroidx/compose/ui/node/LookaheadPassDelegate;", "measurePassDelegate", "Landroidx/compose/ui/node/MeasurePassDelegate;", "getMeasurePassDelegate$ui", "()Landroidx/compose/ui/node/MeasurePassDelegate;", "ignoreRemeasureRequests", "insertAt", "index", "instance", "insertAt$ui", "exceptionMessageForParentingOrOwnership", "", "onZSortedChildrenInvalidated", "onZSortedChildrenInvalidated$ui", "removeAt", "count", "removeAt$ui", "removeAll", "removeAll$ui", "onChildRemoved", "child", "move", "from", "to", "move$ui", "isTransparent", "isSemanticsInvalidated", "isSemanticsInvalidated$ui", "setSemanticsInvalidated$ui", "requestAutofill", "requestAutofill$ui", "invalidateSemantics", "invalidateSemantics$ui", "_semanticsConfiguration", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "semanticsConfiguration", "getSemanticsConfiguration", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", "isCurrentlyCalculatingSemanticsConfiguration", "calculateSemanticsConfiguration", "attach", "attach$ui", "detach", "detach$ui", "_zSortedChildren", "zSortedChildrenInvalidated", "zSortedChildren", "getZSortedChildren$annotations", "()V", "getZSortedChildren", "isValidOwnerScope", "toString", "hasFixedInnerContentConstraints", "getHasFixedInnerContentConstraints$ui", "debugTreeToString", "Landroidx/compose/ui/layout/MeasurePolicy;", "measurePolicy", "getMeasurePolicy", "()Landroidx/compose/ui/layout/MeasurePolicy;", "setMeasurePolicy", "(Landroidx/compose/ui/layout/MeasurePolicy;)V", "intrinsicsPolicy", "Landroidx/compose/ui/node/IntrinsicsPolicy;", "getOrCreateIntrinsicsPolicy", "minLookaheadIntrinsicWidth", "height", "minLookaheadIntrinsicHeight", "width", "maxLookaheadIntrinsicWidth", "maxLookaheadIntrinsicHeight", "minIntrinsicWidth", "minIntrinsicHeight", "maxIntrinsicWidth", "maxIntrinsicHeight", "Landroidx/compose/ui/unit/Density;", "density", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroidx/compose/ui/platform/ViewConfiguration;", "viewConfiguration", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "setViewConfiguration", "(Landroidx/compose/ui/platform/ViewConfiguration;)V", "Landroidx/compose/runtime/CompositionLocalMap;", "compositionLocalMap", "getCompositionLocalMap", "()Landroidx/compose/runtime/CompositionLocalMap;", "setCompositionLocalMap", "(Landroidx/compose/runtime/CompositionLocalMap;)V", "traceContext", "Landroidx/compose/runtime/tooling/CompositionErrorContext;", "getTraceContext", "()Landroidx/compose/runtime/tooling/CompositionErrorContext;", "rethrowWithComposeStackTrace", "", "e", "", "onDensityOrLayoutDirectionChanged", "getWidth", "getHeight", "alignmentLinesRequired", "getAlignmentLinesRequired$ui", "mDrawScope", "Landroidx/compose/ui/node/LayoutNodeDrawScope;", "getMDrawScope$ui", "()Landroidx/compose/ui/node/LayoutNodeDrawScope;", "isPlaced", "isPlacedByParent", "placeOrder", "getPlaceOrder$ui", "measuredByParent", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "getMeasuredByParent$ui", "()Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "measuredByParentInLookahead", "getMeasuredByParentInLookahead$ui", "intrinsicsUsageByParent", "getIntrinsicsUsageByParent$ui", "setIntrinsicsUsageByParent$ui", "(Landroidx/compose/ui/node/LayoutNode$UsageByParent;)V", "previousIntrinsicsUsageByParent", "canMultiMeasure", "getCanMultiMeasure$ui$annotations", "getCanMultiMeasure$ui", "setCanMultiMeasure$ui", "nodes", "Landroidx/compose/ui/node/NodeChain;", "getNodes$ui", "()Landroidx/compose/ui/node/NodeChain;", "innerCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "getInnerCoordinator$ui", "()Landroidx/compose/ui/node/NodeCoordinator;", "layoutDelegate", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "getLayoutDelegate$ui", "()Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "outerCoordinator", "getOuterCoordinator$ui", "zIndex", "", "getZIndex", "()F", "subcompositionsState", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "getSubcompositionsState$ui", "()Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "setSubcompositionsState$ui", "(Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;)V", "_innerLayerCoordinator", "innerLayerCoordinatorIsDirty", "getInnerLayerCoordinatorIsDirty$ui", "setInnerLayerCoordinatorIsDirty$ui", "innerLayerCoordinator", "getInnerLayerCoordinator$ui", "invalidateLayer", "invalidateLayer$ui", "_modifier", "Landroidx/compose/ui/Modifier;", "pendingModifier", "applyingModifierOnAttach", "getApplyingModifierOnAttach$ui", "modifier", "getModifier", "()Landroidx/compose/ui/Modifier;", "setModifier", "(Landroidx/compose/ui/Modifier;)V", "applyModifier", "resetModifierState", "invalidateParentData", "invalidateParentData$ui", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "onAttach", "getOnAttach$ui", "()Lkotlin/jvm/functions/Function1;", "setOnAttach$ui", "(Lkotlin/jvm/functions/Function1;)V", "onDetach", "getOnDetach$ui", "setOnDetach$ui", "needsOnGloballyPositionedDispatch", "getNeedsOnGloballyPositionedDispatch$ui", "setNeedsOnGloballyPositionedDispatch$ui", "globallyPositionedObservers", "getGloballyPositionedObservers", "setGloballyPositionedObservers", "place", "x", "y", "place$ui", "replace", "replace$ui", "lookaheadReplace", "lookaheadReplace$ui", "draw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "graphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "draw$ui", "hitTest", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "hitTestResult", "Landroidx/compose/ui/node/HitTestResult;", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "isInLayer", "hitTest-6fMxITs$ui", "(JLandroidx/compose/ui/node/HitTestResult;IZ)V", "hitTestSemantics", "hitSemanticsEntities", "hitTestSemantics-6fMxITs$ui", "rescheduleRemeasureOrRelayout", "it", "rescheduleRemeasureOrRelayout$ui", "requestRemeasure", "forceRequest", "scheduleMeasureAndLayout", "invalidateIntrinsics", "requestRemeasure$ui", "requestLookaheadRemeasure", "requestLookaheadRemeasure$ui", "invalidateMeasurements", "invalidateMeasurements$ui", "invalidateOnPositioned", "invalidateOnPositioned$ui", "onCoordinatorPositionChanged", "onCoordinatorPositionChanged$ui", "T", "Lkotlin/Function0;", "ignoreRemeasureRequests$ui", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "requestRelayout", "requestRelayout$ui", "requestLookaheadRelayout", "requestLookaheadRelayout$ui", "dispatchOnPositionedCallbacks", "dispatchOnPositionedCallbacks$ui", "getModifierInfo", "Landroidx/compose/ui/layout/ModifierInfo;", "invalidateLayers", "invalidateLayers$ui", "lookaheadRemeasure", "constraints", "Landroidx/compose/ui/unit/Constraints;", "lookaheadRemeasure-_Sx5XlM$ui", "remeasure", "remeasure-_Sx5XlM$ui", "measurePending", "getMeasurePending$ui", "layoutPending", "getLayoutPending$ui", "lookaheadMeasurePending", "getLookaheadMeasurePending$ui", "lookaheadLayoutPending", "getLookaheadLayoutPending$ui", "markLayoutPending", "markLayoutPending$ui", "markMeasurePending", "markMeasurePending$ui", "markLookaheadLayoutPending", "markLookaheadLayoutPending$ui", "invalidateSubtree", "isRootOfInvalidation", "invalidateMeasurementForSubtree", "invalidateDrawForSubtree", "markLookaheadMeasurePending", "markLookaheadMeasurePending$ui", "forceRemeasure", "onLayoutComplete", "forEachCoordinator", "Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;", "forEachCoordinator$ui", "forEachCoordinatorIncludingInner", "forEachCoordinatorIncludingInner$ui", "clearSubtreeIntrinsicsUsage", "clearSubtreeIntrinsicsUsage$ui", "clearSubtreePlacementIntrinsicsUsage", "resetSubtreeIntrinsicsUsage", "resetSubtreeIntrinsicsUsage$ui", "parentInfo", "getParentInfo", "()Landroidx/compose/ui/semantics/SemanticsInfo;", "childrenInfo", "getChildrenInfo", "isDeactivated", "onReuse", "onDeactivate", "onRelease", "NoIntrinsicsMeasurePolicy", "Companion", "LayoutState", "UsageByParent", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LayoutNode implements ComposeNodeLifecycleCallback, Remeasurement, OwnerScope, LayoutInfo, SemanticsInfo, ComposeUiNode, InteroperableComposeUiNode, Owner.OnLayoutCompletedListener {
    public static final int NotPlacedPlaceOrder = Integer.MAX_VALUE;
    private final MutableVectorWithMutationTracking<LayoutNode> _foldedChildren;
    private LayoutNode _foldedParent;
    private NodeCoordinator _innerLayerCoordinator;
    private Modifier _modifier;
    private SemanticsConfiguration _semanticsConfiguration;
    private MutableVector<LayoutNode> _unfoldedChildren;
    private final MutableVector<LayoutNode> _zSortedChildren;
    private boolean addedToRectList;
    private boolean canMultiMeasure;
    private int compositeKeyHash;
    private CompositionLocalMap compositionLocalMap;
    private Density density;
    private int depth;
    private int globallyPositionedObservers;
    private boolean hasPositionalLayerTransformationsInOffsetFromRoot;
    private boolean ignoreRemeasureRequests;
    private boolean innerLayerCoordinatorIsDirty;
    private AndroidViewHolder interopViewFactoryHolder;
    private IntrinsicsPolicy intrinsicsPolicy;
    private UsageByParent intrinsicsUsageByParent;
    private boolean isCurrentlyCalculatingSemanticsConfiguration;
    private boolean isDeactivated;
    private boolean isSemanticsInvalidated;
    private final boolean isVirtual;
    private boolean isVirtualLookaheadRoot;
    private long lastOffsetFromParent;
    private long lastSize;
    private final LayoutNodeLayoutDelegate layoutDelegate;
    private LayoutDirection layoutDirection;
    private LayoutNode lookaheadRoot;
    private MeasurePolicy measurePolicy;
    private boolean needsOnGloballyPositionedDispatch;
    private final NodeChain nodes;
    private Function1<? super Owner, Unit> onAttach;
    private Function1<? super Owner, Unit> onDetach;
    private long outerToInnerOffset;
    private boolean outerToInnerOffsetDirty;
    private Owner owner;
    private Modifier pendingModifier;
    private UsageByParent previousIntrinsicsUsageByParent;
    private int semanticsId;
    private LayoutNodeSubcompositionsState subcompositionsState;
    private boolean unfoldedVirtualChildrenListDirty;
    private ViewConfiguration viewConfiguration;
    private int virtualChildrenCount;
    private boolean zSortedChildrenInvalidated;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final NoIntrinsicsMeasurePolicy ErrorMeasurePolicy = new NoIntrinsicsMeasurePolicy() { // from class: androidx.compose.ui.node.LayoutNode$Companion$ErrorMeasurePolicy$1
        /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
        public Void m4853measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
            throw new IllegalStateException("Undefined measure and it is required");
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        /* JADX INFO: renamed from: measure-3p2s80s */
        public /* bridge */ /* synthetic */ MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List list, long j) {
            return (MeasureResult) m4853measure3p2s80s(measureScope, (List<? extends Measurable>) list, j);
        }
    };
    private static final Function0<LayoutNode> Constructor = new Function0<LayoutNode>() { // from class: androidx.compose.ui.node.LayoutNode$Companion$Constructor$1
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final LayoutNode m4851invoke() {
            return new LayoutNode(false, 0 == true ? 1 : 0, 3, null);
        }
    };
    private static final ViewConfiguration DummyViewConfiguration = new ViewConfiguration() { // from class: androidx.compose.ui.node.LayoutNode$Companion$DummyViewConfiguration$1
        @Override // androidx.compose.ui.platform.ViewConfiguration
        public long getDoubleTapMinTimeMillis() {
            return 40L;
        }

        @Override // androidx.compose.ui.platform.ViewConfiguration
        public long getDoubleTapTimeoutMillis() {
            return 300L;
        }

        @Override // androidx.compose.ui.platform.ViewConfiguration
        public long getLongPressTimeoutMillis() {
            return 400L;
        }

        @Override // androidx.compose.ui.platform.ViewConfiguration
        /* JADX INFO: renamed from: getMinimumTouchTargetSize-MYxV2XQ, reason: not valid java name */
        public long mo4852getMinimumTouchTargetSizeMYxV2XQ() {
            return DpSize.INSTANCE.m6130getZeroMYxV2XQ();
        }

        @Override // androidx.compose.ui.platform.ViewConfiguration
        public float getTouchSlop() {
            return 16.0f;
        }
    };
    private static final Comparator<LayoutNode> ZComparator = new Comparator() { // from class: xo8
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return LayoutNode.a((LayoutNode) obj, (LayoutNode) obj2);
        }
    };

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$LayoutState;", "", "<init>", "(Ljava/lang/String;I)V", "Measuring", "LookaheadMeasuring", "LayingOut", "LookaheadLayingOut", "Idle", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public enum LayoutState {
        Measuring,
        LookaheadMeasuring,
        LayingOut,
        LookaheadLayingOut,
        Idle;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<LayoutState> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "", "<init>", "(Ljava/lang/String;I)V", "InMeasureBlock", "InLayoutBlock", "NotUsed", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public enum UsageByParent {
        InMeasureBlock,
        InLayoutBlock,
        NotUsed;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<UsageByParent> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutState.values().length];
            try {
                iArr[LayoutState.Idle.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LayoutNode(boolean z, int i) {
        this.isVirtual = z;
        this.semanticsId = i;
        IntOffset.Companion companion = IntOffset.INSTANCE;
        this.lastOffsetFromParent = companion.m6160getMaxnOccac();
        this.lastSize = IntSize.INSTANCE.m6198getZeroYbymL2g();
        this.outerToInnerOffset = companion.m6160getMaxnOccac();
        this.outerToInnerOffsetDirty = true;
        this._foldedChildren = new MutableVectorWithMutationTracking<>(new MutableVector(new LayoutNode[16], 0), new Function0<Unit>() { // from class: androidx.compose.ui.node.LayoutNode$_foldedChildren$1
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m4858invoke() {
                this.this$0.getLayoutDelegate().markChildrenDirty();
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m4858invoke();
                return Unit.INSTANCE;
            }
        });
        this._zSortedChildren = new MutableVector<>(new LayoutNode[16], 0);
        this.zSortedChildrenInvalidated = true;
        this.measurePolicy = ErrorMeasurePolicy;
        this.density = LayoutNodeKt.DefaultDensity;
        this.layoutDirection = LayoutDirection.Ltr;
        this.viewConfiguration = DummyViewConfiguration;
        this.compositionLocalMap = CompositionLocalMap.INSTANCE.getEmpty();
        UsageByParent usageByParent = UsageByParent.NotUsed;
        this.intrinsicsUsageByParent = usageByParent;
        this.previousIntrinsicsUsageByParent = usageByParent;
        this.nodes = new NodeChain(this);
        this.layoutDelegate = new LayoutNodeLayoutDelegate(this);
        this.innerLayerCoordinatorIsDirty = true;
        this._modifier = Modifier.INSTANCE;
    }

    public static int a(LayoutNode layoutNode, LayoutNode layoutNode2) {
        return layoutNode.getZIndex() == layoutNode2.getZIndex() ? Intrinsics.compare(layoutNode.getPlaceOrder$ui(), layoutNode2.getPlaceOrder$ui()) : Float.compare(layoutNode.getZIndex(), layoutNode2.getZIndex());
    }

    private final void applyModifier(Modifier modifier) {
        boolean zM4905hasH91voCI$ui = this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(16));
        boolean zM4905hasH91voCI$ui2 = this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(1024));
        this._modifier = modifier;
        this.nodes.updateFrom$ui(modifier);
        boolean zM4905hasH91voCI$ui3 = this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(16));
        boolean zM4905hasH91voCI$ui4 = this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(1024));
        this.layoutDelegate.updateParentData();
        if (this.lookaheadRoot == null && this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(512))) {
            setLookaheadRoot(this);
        }
        if (zM4905hasH91voCI$ui == zM4905hasH91voCI$ui3 && zM4905hasH91voCI$ui2 == zM4905hasH91voCI$ui4) {
            return;
        }
        LayoutNodeKt.requireOwner(this).getRectManager().updateFlagsFor(this, zM4905hasH91voCI$ui4, zM4905hasH91voCI$ui3);
    }

    private final SemanticsConfiguration calculateSemanticsConfiguration() {
        this.isCurrentlyCalculatingSemanticsConfiguration = true;
        final kotlin.jvm.internal.Ref.ObjectRef objectRef = new kotlin.jvm.internal.Ref.ObjectRef();
        objectRef.element = new SemanticsConfiguration();
        OwnerSnapshotObserver snapshotObserver = LayoutNodeKt.requireOwner(this).getSnapshotObserver();
        Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.ui.node.LayoutNode.calculateSemanticsConfiguration.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r3v6 */
            /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "result" is null
                	at jadx.core.dex.visitors.PrepareForCodeGen.removeInstructions(PrepareForCodeGen.java:118)
                	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:85)
                */
            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m4859invoke() {
                /*
                    r10 = this;
                    androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.LayoutNode.this
                    androidx.compose.ui.node.NodeChain r0 = r0.getNodes()
                    r1 = 8
                    int r1 = androidx.compose.ui.node.NodeKind.m4949constructorimpl(r1)
                    kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.ui.semantics.SemanticsConfiguration> r10 = r2
                    int r2 = androidx.compose.ui.node.NodeChain.access$getAggregateChildKindSet(r0)
                    r2 = r2 & r1
                    if (r2 == 0) goto L9d
                    androidx.compose.ui.Modifier$Node r0 = r0.getTail()
                L19:
                    if (r0 == 0) goto L9d
                    int r2 = r0.getKindSet()
                    r2 = r2 & r1
                    if (r2 == 0) goto L97
                    r2 = 0
                    r3 = r0
                    r4 = r2
                L25:
                    if (r3 == 0) goto L97
                    boolean r5 = r3 instanceof androidx.compose.ui.node.SemanticsModifierNode
                    r6 = 1
                    if (r5 == 0) goto L53
                    androidx.compose.ui.node.SemanticsModifierNode r3 = (androidx.compose.ui.node.SemanticsModifierNode) r3
                    boolean r5 = r3.getIsClearingSemantics()
                    if (r5 == 0) goto L3e
                    androidx.compose.ui.semantics.SemanticsConfiguration r5 = new androidx.compose.ui.semantics.SemanticsConfiguration
                    r5.<init>()
                    r10.element = r5
                    r5.setClearingSemantics(r6)
                L3e:
                    boolean r5 = r3.getShouldMergeDescendantSemantics()
                    if (r5 == 0) goto L4b
                    java.lang.Object r5 = r10.element
                    androidx.compose.ui.semantics.SemanticsConfiguration r5 = (androidx.compose.ui.semantics.SemanticsConfiguration) r5
                    r5.setMergingSemanticsOfDescendants(r6)
                L4b:
                    java.lang.Object r5 = r10.element
                    androidx.compose.ui.semantics.SemanticsPropertyReceiver r5 = (androidx.compose.ui.semantics.SemanticsPropertyReceiver) r5
                    r3.applySemantics(r5)
                    goto L92
                L53:
                    int r5 = r3.getKindSet()
                    r5 = r5 & r1
                    if (r5 == 0) goto L92
                    boolean r5 = r3 instanceof androidx.compose.ui.node.DelegatingNode
                    if (r5 == 0) goto L92
                    r5 = r3
                    androidx.compose.ui.node.DelegatingNode r5 = (androidx.compose.ui.node.DelegatingNode) r5
                    androidx.compose.ui.Modifier$Node r5 = r5.getDelegate()
                    r7 = 0
                    r8 = r7
                L67:
                    if (r5 == 0) goto L8f
                    int r9 = r5.getKindSet()
                    r9 = r9 & r1
                    if (r9 == 0) goto L8a
                    int r8 = r8 + 1
                    if (r8 != r6) goto L76
                    r3 = r5
                    goto L8a
                L76:
                    if (r4 != 0) goto L81
                    androidx.compose.runtime.collection.MutableVector r4 = new androidx.compose.runtime.collection.MutableVector
                    r9 = 16
                    androidx.compose.ui.Modifier$Node[] r9 = new androidx.compose.ui.Modifier.Node[r9]
                    r4.<init>(r9, r7)
                L81:
                    if (r3 == 0) goto L87
                    r4.add(r3)
                    r3 = r2
                L87:
                    r4.add(r5)
                L8a:
                    androidx.compose.ui.Modifier$Node r5 = r5.getChild()
                    goto L67
                L8f:
                    if (r8 != r6) goto L92
                    goto L25
                L92:
                    androidx.compose.ui.Modifier$Node r3 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r4)
                    goto L25
                L97:
                    androidx.compose.ui.Modifier$Node r0 = r0.getParent()
                    goto L19
                L9d:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutNode.AnonymousClass1.m4859invoke():void");
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m4859invoke();
                return Unit.INSTANCE;
            }
        };
        snapshotObserver.observer.observeReads(this, snapshotObserver.onCommitAffectingSemantics, function0);
        this.isCurrentlyCalculatingSemanticsConfiguration = false;
        return (SemanticsConfiguration) objectRef.element;
    }

    private final void clearSubtreePlacementIntrinsicsUsage() {
        this.previousIntrinsicsUsageByParent = this.intrinsicsUsageByParent;
        this.intrinsicsUsageByParent = UsageByParent.NotUsed;
        MutableVector<LayoutNode> mutableVector = get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode = layoutNodeArr[i];
            if (layoutNode.intrinsicsUsageByParent == UsageByParent.InLayoutBlock) {
                layoutNode.clearSubtreePlacementIntrinsicsUsage();
            }
        }
    }

    private final String debugTreeToString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        sb.append("|-");
        sb.append(toString());
        sb.append('\n');
        MutableVector<LayoutNode> mutableVector = get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(layoutNodeArr[i2].debugTreeToString(depth + 1));
        }
        String string = sb.toString();
        return depth == 0 ? string.substring(0, string.length() - 1) : string;
    }

    public static /* synthetic */ String debugTreeToString$default(LayoutNode layoutNode, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return layoutNode.debugTreeToString(i);
    }

    private final String exceptionMessageForParentingOrOwnership(LayoutNode instance) {
        StringBuilder sb = new StringBuilder("Cannot insert ");
        sb.append(instance);
        sb.append(" because it already has a parent or an owner. This tree: ");
        sb.append(debugTreeToString$default(this, 0, 1, null));
        sb.append(" Other tree: ");
        LayoutNode layoutNode = instance._foldedParent;
        sb.append(layoutNode != null ? debugTreeToString$default(layoutNode, 0, 1, null) : null);
        return sb.toString();
    }

    @Deprecated(message = "Temporary API to support ConstraintLayout prototyping.")
    public static /* synthetic */ void getCanMultiMeasure$ui$annotations() {
    }

    private final IntrinsicsPolicy getOrCreateIntrinsicsPolicy() {
        IntrinsicsPolicy intrinsicsPolicy = this.intrinsicsPolicy;
        if (intrinsicsPolicy != null) {
            return intrinsicsPolicy;
        }
        IntrinsicsPolicy intrinsicsPolicy2 = new IntrinsicsPolicy(this, getMeasurePolicy());
        this.intrinsicsPolicy = intrinsicsPolicy2;
        return intrinsicsPolicy2;
    }

    private final CompositionErrorContext getTraceContext() {
        return (CompositionErrorContext) getCompositionLocalMap().get(CompositionErrorContextKt.getLocalCompositionErrorContext());
    }

    private final float getZIndex() {
        return getMeasurePassDelegate$ui().getZIndex$ui();
    }

    public static /* synthetic */ void getZSortedChildren$annotations() {
    }

    /* JADX INFO: renamed from: hitTest-6fMxITs$ui$default, reason: not valid java name */
    public static /* synthetic */ void m4837hitTest6fMxITs$ui$default(LayoutNode layoutNode, long j, HitTestResult hitTestResult, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = PointerType.INSTANCE.m4537getUnknownT8wyACA();
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            z = true;
        }
        layoutNode.m4844hitTest6fMxITs$ui(j, hitTestResult, i3, z);
    }

    /* JADX INFO: renamed from: hitTestSemantics-6fMxITs$ui$default, reason: not valid java name */
    public static /* synthetic */ void m4838hitTestSemantics6fMxITs$ui$default(LayoutNode layoutNode, long j, HitTestResult hitTestResult, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = PointerType.INSTANCE.m4536getTouchT8wyACA();
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            z = true;
        }
        layoutNode.m4845hitTestSemantics6fMxITs$ui(j, hitTestResult, i3, z);
    }

    public static /* synthetic */ void invalidateDrawForSubtree$default(LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        layoutNode.invalidateDrawForSubtree(z);
    }

    public static /* synthetic */ void invalidateSubtree$default(LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        layoutNode.invalidateSubtree(z);
    }

    private final void invalidateUnfoldedVirtualChildren() {
        LayoutNode layoutNode;
        if (this.virtualChildrenCount > 0) {
            this.unfoldedVirtualChildrenListDirty = true;
        }
        if (!this.isVirtual || (layoutNode = this._foldedParent) == null) {
            return;
        }
        layoutNode.invalidateUnfoldedVirtualChildren();
    }

    /* JADX INFO: renamed from: lookaheadRemeasure-_Sx5XlM$ui$default, reason: not valid java name */
    public static /* synthetic */ boolean m4839lookaheadRemeasure_Sx5XlM$ui$default(LayoutNode layoutNode, Constraints constraints, int i, Object obj) {
        if ((i & 1) != 0) {
            constraints = layoutNode.layoutDelegate.m4863getLastLookaheadConstraintsDWUhwKw();
        }
        return layoutNode.m4846lookaheadRemeasure_Sx5XlM$ui(constraints);
    }

    private final void onChildRemoved(LayoutNode child) {
        if (child.layoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() > 0) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
            layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() - 1);
        }
        if (this.owner != null) {
            child.detach$ui();
        }
        child._foldedParent = null;
        if (child.globallyPositionedObservers > 0) {
            setGloballyPositionedObservers(this.globallyPositionedObservers - 1);
        }
        child.getOuterCoordinator$ui().setWrappedBy$ui(null);
        if (child.isVirtual) {
            this.virtualChildrenCount--;
            MutableVector<LayoutNode> vector = child._foldedChildren.getVector();
            LayoutNode[] layoutNodeArr = vector.content;
            int size = vector.getSize();
            for (int i = 0; i < size; i++) {
                layoutNodeArr[i].getOuterCoordinator$ui().setWrappedBy$ui(null);
            }
        }
        invalidateUnfoldedVirtualChildren();
        onZSortedChildrenInvalidated$ui();
    }

    private final void onDensityOrLayoutDirectionChanged() {
        invalidateMeasurements$ui();
        LayoutNode parent$ui = getParent$ui();
        if (parent$ui != null) {
            parent$ui.invalidateLayer$ui();
        }
        invalidateLayers$ui();
    }

    private final void recreateUnfoldedChildrenIfDirty() {
        if (this.unfoldedVirtualChildrenListDirty) {
            this.unfoldedVirtualChildrenListDirty = false;
            MutableVector<LayoutNode> mutableVector = this._unfoldedChildren;
            if (mutableVector == null) {
                mutableVector = new MutableVector<>(new LayoutNode[16], 0);
                this._unfoldedChildren = mutableVector;
            }
            mutableVector.clear();
            MutableVector<LayoutNode> vector = this._foldedChildren.getVector();
            LayoutNode[] layoutNodeArr = vector.content;
            int size = vector.getSize();
            for (int i = 0; i < size; i++) {
                LayoutNode layoutNode = layoutNodeArr[i];
                if (layoutNode.isVirtual) {
                    mutableVector.addAll(mutableVector.getSize(), layoutNode.get_children$ui());
                } else {
                    mutableVector.add(layoutNode);
                }
            }
            this.layoutDelegate.markChildrenDirty();
        }
    }

    /* JADX INFO: renamed from: remeasure-_Sx5XlM$ui$default, reason: not valid java name */
    public static /* synthetic */ boolean m4840remeasure_Sx5XlM$ui$default(LayoutNode layoutNode, Constraints constraints, int i, Object obj) {
        if ((i & 1) != 0) {
            constraints = layoutNode.layoutDelegate.m4862getLastConstraintsDWUhwKw();
        }
        return layoutNode.m4847remeasure_Sx5XlM$ui(constraints);
    }

    public static /* synthetic */ void requestLookaheadRelayout$ui$default(LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        layoutNode.requestLookaheadRelayout$ui(z);
    }

    public static /* synthetic */ void requestLookaheadRemeasure$ui$default(LayoutNode layoutNode, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            z3 = true;
        }
        layoutNode.requestLookaheadRemeasure$ui(z, z2, z3);
    }

    public static /* synthetic */ void requestRelayout$ui$default(LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        layoutNode.requestRelayout$ui(z);
    }

    public static /* synthetic */ void requestRemeasure$ui$default(LayoutNode layoutNode, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            z3 = true;
        }
        layoutNode.requestRemeasure$ui(z, z2, z3);
    }

    private final void resetModifierState() {
        this.nodes.resetState$ui();
    }

    private final void setLookaheadRoot(LayoutNode layoutNode) {
        if (Intrinsics.areEqual(layoutNode, this.lookaheadRoot)) {
            return;
        }
        this.lookaheadRoot = layoutNode;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
        if (layoutNode != null) {
            layoutNodeLayoutDelegate.ensureLookaheadDelegateCreated$ui();
            NodeCoordinator wrapped = getInnerCoordinator$ui().getWrapped();
            for (NodeCoordinator outerCoordinator$ui = getOuterCoordinator$ui(); !Intrinsics.areEqual(outerCoordinator$ui, wrapped) && outerCoordinator$ui != null; outerCoordinator$ui = outerCoordinator$ui.getWrapped()) {
                outerCoordinator$ui.ensureLookaheadDelegateCreated();
            }
        } else {
            layoutNodeLayoutDelegate.onRemovedFromLookaheadScope();
        }
        invalidateMeasurements$ui();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x003c  */
    public final void attach$ui(Owner owner) {
        boolean z;
        LayoutNode layoutNode;
        if (!(this.owner == null)) {
            InlineClassHelperKt.throwIllegalStateException("Cannot attach " + this + " as it already is attached.  Tree: " + debugTreeToString$default(this, 0, 1, null));
        }
        LayoutNode layoutNode2 = this._foldedParent;
        if (layoutNode2 == null) {
            z = true;
        } else if (Intrinsics.areEqual(layoutNode2 != null ? layoutNode2.owner : null, owner)) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            StringBuilder sb = new StringBuilder("Attaching to a different owner(");
            sb.append(owner);
            sb.append(") than the parent's owner(");
            LayoutNode parent$ui = getParent$ui();
            sb.append(parent$ui != null ? parent$ui.owner : null);
            sb.append("). This tree: ");
            sb.append(debugTreeToString$default(this, 0, 1, null));
            sb.append(" Parent tree: ");
            LayoutNode layoutNode3 = this._foldedParent;
            sb.append(layoutNode3 != null ? debugTreeToString$default(layoutNode3, 0, 1, null) : null);
            InlineClassHelperKt.throwIllegalStateException(sb.toString());
        }
        LayoutNode parent$ui2 = getParent$ui();
        if (parent$ui2 == null) {
            getMeasurePassDelegate$ui().setPlaced$ui(true);
            RectManager.onLayoutPositionChanged$default(owner.getRectManager(), this, false, 2, null);
            LookaheadPassDelegate lookaheadPassDelegate$ui = getLookaheadPassDelegate$ui();
            if (lookaheadPassDelegate$ui != null) {
                lookaheadPassDelegate$ui.onAttachedToNullParent();
            }
        }
        getOuterCoordinator$ui().setWrappedBy$ui(parent$ui2 != null ? parent$ui2.getInnerCoordinator$ui() : null);
        this.owner = owner;
        this.depth = (parent$ui2 != null ? parent$ui2.depth : -1) + 1;
        Modifier modifier = this.pendingModifier;
        if (modifier != null) {
            applyModifier(modifier);
        }
        this.pendingModifier = null;
        if (!ComposeUiFlags.isSemanticAutofillEnabled && this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(8))) {
            invalidateSemantics$ui();
        }
        owner.onPreAttach(this);
        if (this.isVirtualLookaheadRoot) {
            setLookaheadRoot(this);
        } else {
            LayoutNode layoutNode4 = this._foldedParent;
            if (layoutNode4 == null || (layoutNode = layoutNode4.lookaheadRoot) == null) {
                layoutNode = this.lookaheadRoot;
            }
            setLookaheadRoot(layoutNode);
            if (this.lookaheadRoot == null && this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(512))) {
                setLookaheadRoot(this);
            }
        }
        if (!getIsDeactivated()) {
            this.nodes.markAsAttached();
        }
        MutableVector<LayoutNode> vector = this._foldedChildren.getVector();
        LayoutNode[] layoutNodeArr = vector.content;
        int size = vector.getSize();
        for (int i = 0; i < size; i++) {
            layoutNodeArr[i].attach$ui(owner);
        }
        if (!getIsDeactivated()) {
            this.nodes.runAttachLifecycle();
        }
        invalidateMeasurements$ui();
        if (parent$ui2 != null) {
            parent$ui2.invalidateMeasurements$ui();
        }
        Function1<? super Owner, Unit> function1 = this.onAttach;
        if (function1 != null) {
            function1.invoke(owner);
        }
        this.layoutDelegate.updateParentData();
        if (ComposeUiFlags.isSemanticAutofillEnabled && !getIsDeactivated() && this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(8))) {
            invalidateSemantics$ui();
        }
        owner.onPostAttach(this);
    }

    public final void clearSubtreeIntrinsicsUsage$ui() {
        this.previousIntrinsicsUsageByParent = this.intrinsicsUsageByParent;
        this.intrinsicsUsageByParent = UsageByParent.NotUsed;
        MutableVector<LayoutNode> mutableVector = get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode = layoutNodeArr[i];
            if (layoutNode.intrinsicsUsageByParent != UsageByParent.NotUsed) {
                layoutNode.clearSubtreeIntrinsicsUsage$ui();
            }
        }
    }

    public final void detach$ui() {
        Owner owner = this.owner;
        if (owner == null) {
            StringBuilder sb = new StringBuilder("Cannot detach node that is already detached!  Tree: ");
            LayoutNode parent$ui = getParent$ui();
            sb.append(parent$ui != null ? debugTreeToString$default(parent$ui, 0, 1, null) : null);
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck(sb.toString());
            wq6.a();
            return;
        }
        LayoutNode parent$ui2 = getParent$ui();
        if (parent$ui2 != null) {
            parent$ui2.invalidateLayer$ui();
            parent$ui2.invalidateMeasurements$ui();
            MeasurePassDelegate measurePassDelegate$ui = getMeasurePassDelegate$ui();
            UsageByParent usageByParent = UsageByParent.NotUsed;
            measurePassDelegate$ui.setMeasuredByParent$ui(usageByParent);
            LookaheadPassDelegate lookaheadPassDelegate$ui = getLookaheadPassDelegate$ui();
            if (lookaheadPassDelegate$ui != null) {
                lookaheadPassDelegate$ui.setMeasuredByParent$ui(usageByParent);
            }
        }
        this.layoutDelegate.resetAlignmentLines();
        NodeCoordinator wrapped = getInnerCoordinator$ui().getWrapped();
        for (NodeCoordinator outerCoordinator$ui = getOuterCoordinator$ui(); !Intrinsics.areEqual(outerCoordinator$ui, wrapped) && outerCoordinator$ui != null; outerCoordinator$ui = outerCoordinator$ui.getWrapped()) {
            outerCoordinator$ui.onLayoutNodeDetach();
        }
        Function1<? super Owner, Unit> function1 = this.onDetach;
        if (function1 != null) {
            function1.invoke(owner);
        }
        if (!ComposeUiFlags.isSemanticAutofillEnabled && this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(8))) {
            invalidateSemantics$ui();
        }
        this.nodes.runDetachLifecycle$ui();
        this.ignoreRemeasureRequests = true;
        MutableVector<LayoutNode> vector = this._foldedChildren.getVector();
        LayoutNode[] layoutNodeArr = vector.content;
        int size = vector.getSize();
        for (int i = 0; i < size; i++) {
            layoutNodeArr[i].detach$ui();
        }
        Unit unit = Unit.INSTANCE;
        this.ignoreRemeasureRequests = false;
        this.nodes.markAsDetached$ui();
        owner.onDetach(this);
        owner.getRectManager().remove(this);
        this.owner = null;
        setLookaheadRoot(null);
        this.depth = 0;
        getMeasurePassDelegate$ui().onNodeDetached();
        LookaheadPassDelegate lookaheadPassDelegate$ui2 = getLookaheadPassDelegate$ui();
        if (lookaheadPassDelegate$ui2 != null) {
            lookaheadPassDelegate$ui2.onNodeDetached();
        }
        if (ComposeUiFlags.isSemanticAutofillEnabled && this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(8))) {
            SemanticsConfiguration semanticsConfiguration = this._semanticsConfiguration;
            this._semanticsConfiguration = null;
            this.isSemanticsInvalidated = false;
            owner.getSemanticsOwner().notifySemanticsChange$ui(this, semanticsConfiguration);
            owner.onSemanticsChange();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6 */
    public final void dispatchOnPositionedCallbacks$ui() {
        if (getLayoutState$ui() != LayoutState.Idle || getLayoutPending$ui() || getMeasurePending$ui() || getIsDeactivated() || !isPlaced()) {
            return;
        }
        NodeChain nodeChain = this.nodes;
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(256);
        if ((nodeChain.getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
            for (Modifier.Node head = nodeChain.getHead(); head != null; head = head.getChild()) {
                if ((head.getKindSet() & iM4949constructorimpl) != 0) {
                    Modifier.Node nodePop = head;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof GlobalPositionAwareModifierNode) {
                            GlobalPositionAwareModifierNode globalPositionAwareModifierNode = (GlobalPositionAwareModifierNode) nodePop;
                            globalPositionAwareModifierNode.onGloballyPositioned(DelegatableNodeKt.m4787requireCoordinator64DMado(globalPositionAwareModifierNode, NodeKind.m4949constructorimpl(256)));
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
                if ((head.getAggregateChildKindSet() & iM4949constructorimpl) == 0) {
                    return;
                }
            }
        }
    }

    public final void draw$ui(Canvas canvas, GraphicsLayer graphicsLayer) throws Throwable {
        try {
            getOuterCoordinator$ui().draw(canvas, graphicsLayer);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            rethrowWithComposeStackTrace(th);
            wq6.a();
        }
    }

    public final void forEachChild(Function1<? super LayoutNode, Unit> block) {
        MutableVector<LayoutNode> mutableVector = get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            block.invoke(layoutNodeArr[i]);
        }
    }

    public final void forEachChildIndexed(Function2<? super Integer, ? super LayoutNode, Unit> block) {
        MutableVector<LayoutNode> mutableVector = get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            block.invoke(Integer.valueOf(i), layoutNodeArr[i]);
        }
    }

    public final void forEachCoordinator$ui(Function1<? super LayoutModifierNodeCoordinator, Unit> block) {
        NodeCoordinator outerCoordinator$ui = getOuterCoordinator$ui();
        NodeCoordinator innerCoordinator$ui = getInnerCoordinator$ui();
        while (outerCoordinator$ui != innerCoordinator$ui) {
            outerCoordinator$ui.getClass();
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = (LayoutModifierNodeCoordinator) outerCoordinator$ui;
            block.invoke(layoutModifierNodeCoordinator);
            outerCoordinator$ui = layoutModifierNodeCoordinator.getWrapped();
        }
    }

    public final void forEachCoordinatorIncludingInner$ui(Function1<? super NodeCoordinator, Unit> block) {
        NodeCoordinator wrapped = getInnerCoordinator$ui().getWrapped();
        for (NodeCoordinator outerCoordinator$ui = getOuterCoordinator$ui(); !Intrinsics.areEqual(outerCoordinator$ui, wrapped) && outerCoordinator$ui != null; outerCoordinator$ui = outerCoordinator$ui.getWrapped()) {
            block.invoke(outerCoordinator$ui);
        }
    }

    public void forceRemeasure() {
        LayoutNode layoutNode;
        if (this.lookaheadRoot != null) {
            layoutNode = this;
            requestLookaheadRemeasure$ui$default(layoutNode, false, false, false, 5, null);
        } else {
            layoutNode = this;
            requestRemeasure$ui$default(layoutNode, false, false, false, 5, null);
        }
        Constraints constraintsM4862getLastConstraintsDWUhwKw = layoutNode.layoutDelegate.m4862getLastConstraintsDWUhwKw();
        Owner owner = layoutNode.owner;
        if (constraintsM4862getLastConstraintsDWUhwKw != null) {
            if (owner != null) {
                owner.mo5016measureAndLayout0kLqBqw(layoutNode, constraintsM4862getLastConstraintsDWUhwKw.getValue());
            }
        } else if (owner != null) {
            Owner.measureAndLayout$default(owner, false, 1, null);
        }
    }

    /* JADX INFO: renamed from: getAddedToRectList$ui, reason: from getter */
    public final boolean getAddedToRectList() {
        return this.addedToRectList;
    }

    public final boolean getAlignmentLinesRequired$ui() {
        AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui;
        AlignmentLines alignmentLines;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
        return layoutNodeLayoutDelegate.getAlignmentLinesOwner$ui().getAlignmentLines().getRequired$ui() || !((lookaheadAlignmentLinesOwner$ui = layoutNodeLayoutDelegate.getLookaheadAlignmentLinesOwner$ui()) == null || (alignmentLines = lookaheadAlignmentLinesOwner$ui.getAlignmentLines()) == null || !alignmentLines.getRequired$ui());
    }

    public final boolean getApplyingModifierOnAttach$ui() {
        return this.pendingModifier != null;
    }

    /* JADX INFO: renamed from: getCanMultiMeasure$ui, reason: from getter */
    public final boolean getCanMultiMeasure() {
        return this.canMultiMeasure;
    }

    public final List<Measurable> getChildLookaheadMeasurables$ui() {
        LookaheadPassDelegate lookaheadPassDelegate$ui = getLookaheadPassDelegate$ui();
        lookaheadPassDelegate$ui.getClass();
        return lookaheadPassDelegate$ui.getChildDelegates$ui();
    }

    public final List<Measurable> getChildMeasurables$ui() {
        return getMeasurePassDelegate$ui().getChildDelegates$ui();
    }

    public final List<LayoutNode> getChildren$ui() {
        return get_children$ui().asMutableList();
    }

    @Override // androidx.compose.ui.semantics.SemanticsInfo
    public List<SemanticsInfo> getChildrenInfo() {
        return getChildren$ui();
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public int getCompositeKeyHash() {
        return this.compositeKeyHash;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public CompositionLocalMap getCompositionLocalMap() {
        return this.compositionLocalMap;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public LayoutCoordinates getCoordinates() {
        return getInnerCoordinator$ui();
    }

    @Override // androidx.compose.ui.layout.LayoutInfo, androidx.compose.ui.node.ComposeUiNode
    public Density getDensity() {
        return this.density;
    }

    /* JADX INFO: renamed from: getDepth$ui, reason: from getter */
    public final int getDepth() {
        return this.depth;
    }

    public final List<LayoutNode> getFoldedChildren$ui() {
        return this._foldedChildren.getVector().asMutableList();
    }

    public final int getGloballyPositionedObservers() {
        return this.globallyPositionedObservers;
    }

    public final boolean getHasFixedInnerContentConstraints$ui() {
        long jM4928getLastMeasurementConstraintsmsEJaDk$ui = getInnerCoordinator$ui().m4928getLastMeasurementConstraintsmsEJaDk$ui();
        return Constraints.m5973getHasFixedWidthimpl(jM4928getLastMeasurementConstraintsmsEJaDk$ui) && Constraints.m5972getHasFixedHeightimpl(jM4928getLastMeasurementConstraintsmsEJaDk$ui);
    }

    /* JADX INFO: renamed from: getHasPositionalLayerTransformationsInOffsetFromRoot$ui, reason: from getter */
    public final boolean getHasPositionalLayerTransformationsInOffsetFromRoot() {
        return this.hasPositionalLayerTransformationsInOffsetFromRoot;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public int getHeight() {
        return this.layoutDelegate.getHeight$ui();
    }

    public final NodeCoordinator getInnerCoordinator$ui() {
        return this.nodes.getInnerCoordinator();
    }

    public final NodeCoordinator getInnerLayerCoordinator$ui() {
        if (this.innerLayerCoordinatorIsDirty) {
            NodeCoordinator innerCoordinator$ui = getInnerCoordinator$ui();
            NodeCoordinator wrappedBy = getOuterCoordinator$ui().getWrappedBy();
            this._innerLayerCoordinator = null;
            while (!Intrinsics.areEqual(innerCoordinator$ui, wrappedBy)) {
                if ((innerCoordinator$ui != null ? innerCoordinator$ui.getLayer() : null) != null) {
                    this._innerLayerCoordinator = innerCoordinator$ui;
                    break;
                }
                innerCoordinator$ui = innerCoordinator$ui != null ? innerCoordinator$ui.getWrappedBy() : null;
            }
        }
        NodeCoordinator nodeCoordinator = this._innerLayerCoordinator;
        if (nodeCoordinator == null || nodeCoordinator.getLayer() != null) {
            return nodeCoordinator;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("layer was not set");
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getInnerLayerCoordinatorIsDirty$ui, reason: from getter */
    public final boolean getInnerLayerCoordinatorIsDirty() {
        return this.innerLayerCoordinatorIsDirty;
    }

    @Override // androidx.compose.ui.node.InteroperableComposeUiNode
    public View getInteropView() {
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            return androidViewHolder.getView();
        }
        return null;
    }

    /* JADX INFO: renamed from: getInteropViewFactoryHolder$ui, reason: from getter */
    public final AndroidViewHolder getInteropViewFactoryHolder() {
        return this.interopViewFactoryHolder;
    }

    /* JADX INFO: renamed from: getIntrinsicsUsageByParent$ui, reason: from getter */
    public final UsageByParent getIntrinsicsUsageByParent() {
        return this.intrinsicsUsageByParent;
    }

    /* JADX INFO: renamed from: getLastOffsetFromParent-nOcc-ac$ui, reason: not valid java name and from getter */
    public final long getLastOffsetFromParent() {
        return this.lastOffsetFromParent;
    }

    /* JADX INFO: renamed from: getLastSize-YbymL2g$ui, reason: not valid java name and from getter */
    public final long getLastSize() {
        return this.lastSize;
    }

    /* JADX INFO: renamed from: getLayoutDelegate$ui, reason: from getter */
    public final LayoutNodeLayoutDelegate getLayoutDelegate() {
        return this.layoutDelegate;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo, androidx.compose.ui.node.ComposeUiNode
    public LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    public final boolean getLayoutPending$ui() {
        return this.layoutDelegate.getLayoutPending$ui();
    }

    public final LayoutState getLayoutState$ui() {
        return this.layoutDelegate.getLayoutState();
    }

    public final boolean getLookaheadLayoutPending$ui() {
        return this.layoutDelegate.getLookaheadLayoutPending();
    }

    public final boolean getLookaheadMeasurePending$ui() {
        return this.layoutDelegate.getLookaheadMeasurePending();
    }

    public final LookaheadPassDelegate getLookaheadPassDelegate$ui() {
        return this.layoutDelegate.getLookaheadPassDelegate();
    }

    /* JADX INFO: renamed from: getLookaheadRoot$ui, reason: from getter */
    public final LayoutNode getLookaheadRoot() {
        return this.lookaheadRoot;
    }

    public final LayoutNodeDrawScope getMDrawScope$ui() {
        return LayoutNodeKt.requireOwner(this).getSharedDrawScope();
    }

    public final MeasurePassDelegate getMeasurePassDelegate$ui() {
        return this.layoutDelegate.getMeasurePassDelegate();
    }

    public final boolean getMeasurePending$ui() {
        return this.layoutDelegate.getMeasurePending$ui();
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public MeasurePolicy getMeasurePolicy() {
        return this.measurePolicy;
    }

    public final UsageByParent getMeasuredByParent$ui() {
        return getMeasurePassDelegate$ui().getMeasuredByParent$ui();
    }

    public final UsageByParent getMeasuredByParentInLookahead$ui() {
        UsageByParent measuredByParent$ui;
        LookaheadPassDelegate lookaheadPassDelegate$ui = getLookaheadPassDelegate$ui();
        return (lookaheadPassDelegate$ui == null || (measuredByParent$ui = lookaheadPassDelegate$ui.getMeasuredByParent$ui()) == null) ? UsageByParent.NotUsed : measuredByParent$ui;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    /* JADX INFO: renamed from: getModifier, reason: from getter */
    public Modifier get_modifier() {
        return this._modifier;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public List<ModifierInfo> getModifierInfo() {
        return this.nodes.getModifierInfo();
    }

    /* JADX INFO: renamed from: getNeedsOnGloballyPositionedDispatch$ui, reason: from getter */
    public final boolean getNeedsOnGloballyPositionedDispatch() {
        return this.needsOnGloballyPositionedDispatch;
    }

    /* JADX INFO: renamed from: getNodes$ui, reason: from getter */
    public final NodeChain getNodes() {
        return this.nodes;
    }

    public final Function1<Owner, Unit> getOnAttach$ui() {
        return this.onAttach;
    }

    public final Function1<Owner, Unit> getOnDetach$ui() {
        return this.onDetach;
    }

    public final NodeCoordinator getOuterCoordinator$ui() {
        return this.nodes.getOuterCoordinator();
    }

    /* JADX INFO: renamed from: getOuterToInnerOffset-nOcc-ac$ui, reason: not valid java name and from getter */
    public final long getOuterToInnerOffset() {
        return this.outerToInnerOffset;
    }

    /* JADX INFO: renamed from: getOuterToInnerOffsetDirty$ui, reason: from getter */
    public final boolean getOuterToInnerOffsetDirty() {
        return this.outerToInnerOffsetDirty;
    }

    /* JADX INFO: renamed from: getOwner$ui, reason: from getter */
    public final Owner getOwner() {
        return this.owner;
    }

    public final LayoutNode getParent$ui() {
        LayoutNode layoutNode = this._foldedParent;
        while (layoutNode != null && layoutNode.isVirtual) {
            layoutNode = layoutNode._foldedParent;
        }
        return layoutNode;
    }

    public final int getPlaceOrder$ui() {
        return getMeasurePassDelegate$ui().getPlaceOrder();
    }

    @Override // androidx.compose.ui.semantics.SemanticsInfo
    public SemanticsConfiguration getSemanticsConfiguration() {
        if (!isAttached() || getIsDeactivated() || !this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(8))) {
            return null;
        }
        if (!ComposeUiFlags.isSemanticAutofillEnabled && this._semanticsConfiguration == null) {
            this._semanticsConfiguration = calculateSemanticsConfiguration();
        }
        return this._semanticsConfiguration;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public int getSemanticsId() {
        return this.semanticsId;
    }

    /* JADX INFO: renamed from: getSubcompositionsState$ui, reason: from getter */
    public final LayoutNodeSubcompositionsState getSubcompositionsState() {
        return this.subcompositionsState;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo, androidx.compose.ui.node.ComposeUiNode
    public ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public int getWidth() {
        return this.layoutDelegate.getWidth$ui();
    }

    public final MutableVector<LayoutNode> getZSortedChildren() {
        if (this.zSortedChildrenInvalidated) {
            this._zSortedChildren.clear();
            MutableVector<LayoutNode> mutableVector = this._zSortedChildren;
            mutableVector.addAll(mutableVector.getSize(), get_children$ui());
            this._zSortedChildren.sortWith(ZComparator);
            this.zSortedChildrenInvalidated = false;
        }
        return this._zSortedChildren;
    }

    public final MutableVector<LayoutNode> get_children$ui() {
        updateChildrenIfDirty$ui();
        if (this.virtualChildrenCount == 0) {
            return this._foldedChildren.getVector();
        }
        MutableVector<LayoutNode> mutableVector = this._unfoldedChildren;
        mutableVector.getClass();
        return mutableVector;
    }

    /* JADX INFO: renamed from: hitTest-6fMxITs$ui, reason: not valid java name */
    public final void m4844hitTest6fMxITs$ui(long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer) {
        getOuterCoordinator$ui().m4931hitTestqzLsGqo(NodeCoordinator.INSTANCE.getPointerInputSource(), NodeCoordinator.m4913fromParentPosition8S9VItk$default(getOuterCoordinator$ui(), pointerPosition, false, 2, null), hitTestResult, pointerType, isInLayer);
    }

    /* JADX INFO: renamed from: hitTestSemantics-6fMxITs$ui, reason: not valid java name */
    public final void m4845hitTestSemantics6fMxITs$ui(long pointerPosition, HitTestResult hitSemanticsEntities, int pointerType, boolean isInLayer) {
        getOuterCoordinator$ui().m4931hitTestqzLsGqo(NodeCoordinator.INSTANCE.getSemanticsSource(), NodeCoordinator.m4913fromParentPosition8S9VItk$default(getOuterCoordinator$ui(), pointerPosition, false, 2, null), hitSemanticsEntities, PointerType.INSTANCE.m4536getTouchT8wyACA(), isInLayer);
    }

    public final <T> T ignoreRemeasureRequests$ui(Function0<? extends T> block) {
        this.ignoreRemeasureRequests = true;
        T t = (T) block.invoke();
        this.ignoreRemeasureRequests = false;
        return t;
    }

    public final void insertAt$ui(int index, LayoutNode instance) {
        if (!(instance._foldedParent == null || instance.owner == null)) {
            InlineClassHelperKt.throwIllegalStateException(exceptionMessageForParentingOrOwnership(instance));
        }
        instance._foldedParent = this;
        this._foldedChildren.add(index, instance);
        onZSortedChildrenInvalidated$ui();
        if (instance.isVirtual) {
            this.virtualChildrenCount++;
        }
        invalidateUnfoldedVirtualChildren();
        Owner owner = this.owner;
        if (owner != null) {
            instance.attach$ui(owner);
        }
        if (instance.layoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() > 0) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
            layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() + 1);
        }
        if (instance.globallyPositionedObservers > 0) {
            setGloballyPositionedObservers(this.globallyPositionedObservers + 1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v8 */
    public final void invalidateDrawForSubtree(boolean isRootOfInvalidation) {
        LayoutNode parent$ui;
        if (isRootOfInvalidation && (parent$ui = getParent$ui()) != null) {
            parent$ui.invalidateLayer$ui();
        }
        NodeChain nodeChain = this.nodes;
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(2);
        if ((nodeChain.getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
            for (Modifier.Node head = nodeChain.getHead(); head != null; head = head.getChild()) {
                if ((head.getKindSet() & iM4949constructorimpl) != 0) {
                    Modifier.Node nodePop = head;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof LayoutModifierNode) {
                            OwnedLayer layer = DelegatableNodeKt.m4787requireCoordinator64DMado((LayoutModifierNode) nodePop, NodeKind.m4949constructorimpl(2)).getLayer();
                            if (layer != null) {
                                layer.invalidate();
                            }
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
                if ((head.getAggregateChildKindSet() & iM4949constructorimpl) == 0) {
                    break;
                }
            }
        }
        MutableVector<LayoutNode> mutableVector2 = get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector2.content;
        int size = mutableVector2.getSize();
        for (int i2 = 0; i2 < size; i2++) {
            layoutNodeArr[i2].invalidateDrawForSubtree(false);
        }
    }

    public final void invalidateLayer$ui() {
        NodeCoordinator innerLayerCoordinator$ui = getInnerLayerCoordinator$ui();
        if (innerLayerCoordinator$ui != null) {
            innerLayerCoordinator$ui.invalidateLayer();
            return;
        }
        LayoutNode parent$ui = getParent$ui();
        if (parent$ui != null) {
            parent$ui.invalidateLayer$ui();
        }
    }

    public final void invalidateLayers$ui() {
        NodeCoordinator outerCoordinator$ui = getOuterCoordinator$ui();
        NodeCoordinator innerCoordinator$ui = getInnerCoordinator$ui();
        while (outerCoordinator$ui != innerCoordinator$ui) {
            outerCoordinator$ui.getClass();
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = (LayoutModifierNodeCoordinator) outerCoordinator$ui;
            OwnedLayer layer = layoutModifierNodeCoordinator.getLayer();
            if (layer != null) {
                layer.invalidate();
            }
            outerCoordinator$ui = layoutModifierNodeCoordinator.getWrapped();
        }
        OwnedLayer layer2 = getInnerCoordinator$ui().getLayer();
        if (layer2 != null) {
            layer2.invalidate();
        }
    }

    public final void invalidateMeasurementForSubtree() {
        requestRemeasure$ui$default(this, false, false, false, 7, null);
        MutableVector<LayoutNode> mutableVector = get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            layoutNodeArr[i].invalidateMeasurementForSubtree();
        }
    }

    public final void invalidateMeasurements$ui() {
        if (this.isVirtual) {
            LayoutNode parent$ui = getParent$ui();
            if (parent$ui != null) {
                parent$ui.invalidateMeasurements$ui();
                return;
            }
            return;
        }
        if (this.lookaheadRoot != null) {
            requestLookaheadRemeasure$ui$default(this, false, false, false, 7, null);
        } else {
            requestRemeasure$ui$default(this, false, false, false, 7, null);
        }
    }

    public final void invalidateOnPositioned$ui() {
        if (this.globallyPositionedObservers == 0 || getLayoutPending$ui() || getMeasurePending$ui() || this.needsOnGloballyPositionedDispatch) {
            return;
        }
        LayoutNodeKt.requireOwner(this).requestOnPositionedCallback(this);
    }

    public final void invalidateParentData$ui() {
        this.layoutDelegate.invalidateParentData();
    }

    public final void invalidateSemantics$ui() {
        if (this.isCurrentlyCalculatingSemanticsConfiguration) {
            return;
        }
        if (!ComposeUiFlags.isSemanticAutofillEnabled) {
            this._semanticsConfiguration = null;
            LayoutNodeKt.requireOwner(this).onSemanticsChange();
        } else {
            if (this.nodes.isUpdating$ui() || getApplyingModifierOnAttach$ui()) {
                this.isSemanticsInvalidated = true;
                return;
            }
            SemanticsConfiguration semanticsConfiguration = this._semanticsConfiguration;
            this._semanticsConfiguration = calculateSemanticsConfiguration();
            this.isSemanticsInvalidated = false;
            Owner ownerRequireOwner = LayoutNodeKt.requireOwner(this);
            ownerRequireOwner.getSemanticsOwner().notifySemanticsChange$ui(this, semanticsConfiguration);
            ownerRequireOwner.onSemanticsChange();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v9 */
    public final void invalidateSubtree(boolean isRootOfInvalidation) {
        LayoutNode parent$ui;
        if (isRootOfInvalidation && (parent$ui = getParent$ui()) != null) {
            parent$ui.invalidateLayer$ui();
        }
        invalidateSemantics$ui();
        requestRemeasure$ui$default(this, false, false, false, 7, null);
        NodeChain nodeChain = this.nodes;
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(2);
        if ((nodeChain.getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
            for (Modifier.Node head = nodeChain.getHead(); head != null; head = head.getChild()) {
                if ((head.getKindSet() & iM4949constructorimpl) != 0) {
                    Modifier.Node nodePop = head;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof LayoutModifierNode) {
                            OwnedLayer layer = DelegatableNodeKt.m4787requireCoordinator64DMado((LayoutModifierNode) nodePop, NodeKind.m4949constructorimpl(2)).getLayer();
                            if (layer != null) {
                                layer.invalidate();
                            }
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
                if ((head.getAggregateChildKindSet() & iM4949constructorimpl) == 0) {
                    break;
                }
            }
        }
        MutableVector<LayoutNode> mutableVector2 = get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector2.content;
        int size = mutableVector2.getSize();
        for (int i2 = 0; i2 < size; i2++) {
            layoutNodeArr[i2].invalidateSubtree(false);
        }
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public boolean isAttached() {
        return this.owner != null;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    /* JADX INFO: renamed from: isDeactivated, reason: from getter */
    public boolean getIsDeactivated() {
        return this.isDeactivated;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public boolean isPlaced() {
        return getMeasurePassDelegate$ui().isPlaced$ui();
    }

    public final boolean isPlacedByParent() {
        return getMeasurePassDelegate$ui().isPlacedByParent();
    }

    public final Boolean isPlacedInLookahead() {
        LookaheadPassDelegate lookaheadPassDelegate$ui = getLookaheadPassDelegate$ui();
        if (lookaheadPassDelegate$ui != null) {
            return Boolean.valueOf(lookaheadPassDelegate$ui.isPlaced$ui());
        }
        return null;
    }

    /* JADX INFO: renamed from: isSemanticsInvalidated$ui, reason: from getter */
    public final boolean getIsSemanticsInvalidated() {
        return this.isSemanticsInvalidated;
    }

    @Override // androidx.compose.ui.semantics.SemanticsInfo
    public boolean isTransparent() {
        return getOuterCoordinator$ui().isTransparent();
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValidOwnerScope() {
        return isAttached();
    }

    /* JADX INFO: renamed from: isVirtualLookaheadRoot$ui, reason: from getter */
    public final boolean getIsVirtualLookaheadRoot() {
        return this.isVirtualLookaheadRoot;
    }

    /* JADX INFO: renamed from: lookaheadRemeasure-_Sx5XlM$ui, reason: not valid java name */
    public final boolean m4846lookaheadRemeasure_Sx5XlM$ui(Constraints constraints) {
        if (constraints == null || this.lookaheadRoot == null) {
            return false;
        }
        LookaheadPassDelegate lookaheadPassDelegate$ui = getLookaheadPassDelegate$ui();
        lookaheadPassDelegate$ui.getClass();
        return lookaheadPassDelegate$ui.m4884remeasureBRTryo0(constraints.getValue());
    }

    public final void lookaheadReplace$ui() {
        if (this.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            clearSubtreePlacementIntrinsicsUsage();
        }
        LookaheadPassDelegate lookaheadPassDelegate$ui = getLookaheadPassDelegate$ui();
        lookaheadPassDelegate$ui.getClass();
        lookaheadPassDelegate$ui.replace();
    }

    public final void markLayoutPending$ui() {
        this.layoutDelegate.markLayoutPending$ui();
    }

    public final void markLookaheadLayoutPending$ui() {
        this.layoutDelegate.markLookaheadLayoutPending$ui();
    }

    public final void markLookaheadMeasurePending$ui() {
        this.layoutDelegate.markLookaheadMeasurePending$ui();
    }

    public final void markMeasurePending$ui() {
        this.layoutDelegate.markMeasurePending$ui();
    }

    public final int maxIntrinsicHeight(int width) {
        return getOrCreateIntrinsicsPolicy().maxIntrinsicHeight(width);
    }

    public final int maxIntrinsicWidth(int height) {
        return getOrCreateIntrinsicsPolicy().maxIntrinsicWidth(height);
    }

    public final int maxLookaheadIntrinsicHeight(int width) {
        return getOrCreateIntrinsicsPolicy().maxLookaheadIntrinsicHeight(width);
    }

    public final int maxLookaheadIntrinsicWidth(int height) {
        return getOrCreateIntrinsicsPolicy().maxLookaheadIntrinsicWidth(height);
    }

    public final int minIntrinsicHeight(int width) {
        return getOrCreateIntrinsicsPolicy().minIntrinsicHeight(width);
    }

    public final int minIntrinsicWidth(int height) {
        return getOrCreateIntrinsicsPolicy().minIntrinsicWidth(height);
    }

    public final int minLookaheadIntrinsicHeight(int width) {
        return getOrCreateIntrinsicsPolicy().minLookaheadIntrinsicHeight(width);
    }

    public final int minLookaheadIntrinsicWidth(int height) {
        return getOrCreateIntrinsicsPolicy().minLookaheadIntrinsicWidth(height);
    }

    public final void move$ui(int from, int to, int count) {
        if (from == to) {
            return;
        }
        for (int i = 0; i < count; i++) {
            this._foldedChildren.add(from > to ? to + i : (to + count) - 2, this._foldedChildren.removeAt(from > to ? from + i : from));
        }
        onZSortedChildrenInvalidated$ui();
        invalidateUnfoldedVirtualChildren();
        invalidateMeasurements$ui();
    }

    public final void onCoordinatorPositionChanged$ui() {
        RectManager rectManager;
        this.outerToInnerOffsetDirty = true;
        Owner owner = this.owner;
        if (owner == null || (rectManager = owner.getRectManager()) == null) {
            return;
        }
        rectManager.invalidateCallbacksFor(this);
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onDeactivate() {
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            androidViewHolder.onDeactivate();
        }
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this.subcompositionsState;
        if (layoutNodeSubcompositionsState != null) {
            layoutNodeSubcompositionsState.onDeactivate();
        }
        this.isDeactivated = true;
        resetModifierState();
        if (isAttached()) {
            if (ComposeUiFlags.isSemanticAutofillEnabled) {
                this._semanticsConfiguration = null;
                this.isSemanticsInvalidated = false;
            } else {
                invalidateSemantics$ui();
            }
        }
        Owner owner = this.owner;
        if (owner != null) {
            owner.onLayoutNodeDeactivated(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6 */
    @Override // androidx.compose.ui.node.Owner.OnLayoutCompletedListener
    public void onLayoutComplete() {
        NodeCoordinator innerCoordinator$ui = getInnerCoordinator$ui();
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(4194304);
        boolean zM4958getIncludeSelfInTraversalH91voCI = NodeKindKt.m4958getIncludeSelfInTraversalH91voCI(iM4949constructorimpl);
        Modifier.Node tail = innerCoordinator$ui.getTail();
        if (!zM4958getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = innerCoordinator$ui.headNode(zM4958getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM4949constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & iM4949constructorimpl) != 0) {
                Modifier.Node nodePop = nodeHeadNode;
                MutableVector mutableVector = null;
                while (nodePop != 0) {
                    if (nodePop instanceof LayoutAwareModifierNode) {
                        ((LayoutAwareModifierNode) nodePop).onPlaced(getInnerCoordinator$ui());
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

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onRelease() {
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            androidViewHolder.onRelease();
        }
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this.subcompositionsState;
        if (layoutNodeSubcompositionsState != null) {
            layoutNodeSubcompositionsState.onRelease();
        }
        NodeCoordinator wrapped = getInnerCoordinator$ui().getWrapped();
        for (NodeCoordinator outerCoordinator$ui = getOuterCoordinator$ui(); !Intrinsics.areEqual(outerCoordinator$ui, wrapped) && outerCoordinator$ui != null; outerCoordinator$ui = outerCoordinator$ui.getWrapped()) {
            outerCoordinator$ui.onRelease();
        }
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onReuse() {
        RectManager rectManager;
        RectManager rectManager2;
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalArgumentException("onReuse is only expected on attached node");
        }
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            androidViewHolder.onReuse();
        }
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this.subcompositionsState;
        if (layoutNodeSubcompositionsState != null) {
            layoutNodeSubcompositionsState.onReuse();
        }
        this.isCurrentlyCalculatingSemanticsConfiguration = false;
        if (getIsDeactivated()) {
            this.isDeactivated = false;
            if (!ComposeUiFlags.isSemanticAutofillEnabled) {
                invalidateSemantics$ui();
            }
        } else {
            resetModifierState();
        }
        int semanticsId = getSemanticsId();
        Owner owner = this.owner;
        if (owner != null && (rectManager2 = owner.getRectManager()) != null) {
            rectManager2.remove(this);
        }
        setSemanticsId(SemanticsModifierKt.generateSemanticsId());
        Owner owner2 = this.owner;
        if (owner2 != null) {
            owner2.onPreLayoutNodeReused(this, semanticsId);
        }
        this.nodes.markAsAttached();
        this.nodes.runAttachLifecycle();
        if (ComposeUiFlags.isSemanticAutofillEnabled && this.nodes.m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(8))) {
            invalidateSemantics$ui();
        }
        rescheduleRemeasureOrRelayout$ui(this);
        Owner owner3 = this.owner;
        if (owner3 != null) {
            owner3.onPostLayoutNodeReused(this, semanticsId);
        }
        Owner owner4 = this.owner;
        if (owner4 == null || (rectManager = owner4.getRectManager()) == null) {
            return;
        }
        rectManager.onLayoutPositionChanged(this, true);
    }

    public final void onZSortedChildrenInvalidated$ui() {
        if (!this.isVirtual) {
            this.zSortedChildrenInvalidated = true;
            return;
        }
        LayoutNode parent$ui = getParent$ui();
        if (parent$ui != null) {
            parent$ui.onZSortedChildrenInvalidated$ui();
        }
    }

    public final void place$ui(int x, int y) {
        Placeable.PlacementScope placementScope;
        NodeCoordinator innerCoordinator$ui;
        if (this.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            clearSubtreePlacementIntrinsicsUsage();
        }
        LayoutNode parent$ui = getParent$ui();
        if (parent$ui == null || (innerCoordinator$ui = parent$ui.getInnerCoordinator$ui()) == null || (placementScope = innerCoordinator$ui.getPlacementScope()) == null) {
            placementScope = LayoutNodeKt.requireOwner(this).getPlacementScope();
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, getMeasurePassDelegate$ui(), x, y, 0.0f, 4, null);
    }

    /* JADX INFO: renamed from: remeasure-_Sx5XlM$ui, reason: not valid java name */
    public final boolean m4847remeasure_Sx5XlM$ui(Constraints constraints) {
        if (constraints == null) {
            return false;
        }
        if (this.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            clearSubtreeIntrinsicsUsage$ui();
        }
        return getMeasurePassDelegate$ui().m4897remeasureBRTryo0(constraints.getValue());
    }

    public final void removeAll$ui() {
        int size = this._foldedChildren.getVector().getSize();
        while (true) {
            size--;
            MutableVectorWithMutationTracking<LayoutNode> mutableVectorWithMutationTracking = this._foldedChildren;
            if (-1 >= size) {
                mutableVectorWithMutationTracking.clear();
                return;
            }
            onChildRemoved(mutableVectorWithMutationTracking.getVector().content[size]);
        }
    }

    public final void removeAt$ui(int index, int count) {
        if (!(count >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("count (" + count + ") must be greater than 0");
        }
        int i = (count + index) - 1;
        if (index > i) {
            return;
        }
        while (true) {
            onChildRemoved(this._foldedChildren.getVector().content[i]);
            this._foldedChildren.removeAt(i);
            if (i == index) {
                return;
            } else {
                i--;
            }
        }
    }

    public final void replace$ui() {
        if (this.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            clearSubtreePlacementIntrinsicsUsage();
        }
        getMeasurePassDelegate$ui().replace();
    }

    public final void requestAutofill$ui() {
        if (this.isCurrentlyCalculatingSemanticsConfiguration) {
            return;
        }
        LayoutNodeKt.requireOwner(this).requestAutofill(this);
    }

    public final void requestLookaheadRelayout$ui(boolean forceRequest) {
        Owner owner;
        if (this.isVirtual || (owner = this.owner) == null) {
            return;
        }
        owner.onRequestRelayout(this, true, forceRequest);
    }

    public final void requestLookaheadRemeasure$ui(boolean forceRequest, boolean scheduleMeasureAndLayout, boolean invalidateIntrinsics) {
        if (!(this.lookaheadRoot != null)) {
            InlineClassHelperKt.throwIllegalStateException("Lookahead measure cannot be requested on a node that is not a part of the LookaheadScope");
        }
        Owner owner = this.owner;
        if (owner == null || this.ignoreRemeasureRequests || this.isVirtual) {
            return;
        }
        owner.onRequestMeasure(this, true, forceRequest, scheduleMeasureAndLayout);
        if (invalidateIntrinsics) {
            LookaheadPassDelegate lookaheadPassDelegate$ui = getLookaheadPassDelegate$ui();
            lookaheadPassDelegate$ui.getClass();
            lookaheadPassDelegate$ui.invalidateIntrinsicsParent(forceRequest);
        }
    }

    public final void requestRelayout$ui(boolean forceRequest) {
        Owner owner;
        if (this.isVirtual || (owner = this.owner) == null) {
            return;
        }
        Owner.onRequestRelayout$default(owner, this, false, forceRequest, 2, null);
    }

    public final void requestRemeasure$ui(boolean forceRequest, boolean scheduleMeasureAndLayout, boolean invalidateIntrinsics) {
        Owner owner;
        if (this.ignoreRemeasureRequests || this.isVirtual || (owner = this.owner) == null) {
            return;
        }
        Owner.onRequestMeasure$default(owner, this, false, forceRequest, scheduleMeasureAndLayout, 2, null);
        if (invalidateIntrinsics) {
            getMeasurePassDelegate$ui().invalidateIntrinsicsParent(forceRequest);
        }
    }

    public final void rescheduleRemeasureOrRelayout$ui(LayoutNode it) {
        if (WhenMappings.$EnumSwitchMapping$0[it.getLayoutState$ui().ordinal()] != 1) {
            sle.a("Unexpected state ", it.getLayoutState$ui());
            return;
        }
        if (it.getLookaheadMeasurePending$ui()) {
            requestLookaheadRemeasure$ui$default(it, true, false, false, 6, null);
            return;
        }
        if (it.getLookaheadLayoutPending$ui()) {
            it.requestLookaheadRelayout$ui(true);
        }
        if (it.getMeasurePending$ui()) {
            requestRemeasure$ui$default(it, true, false, false, 6, null);
        } else if (it.getLayoutPending$ui()) {
            it.requestRelayout$ui(true);
        }
    }

    public final void resetSubtreeIntrinsicsUsage$ui() {
        MutableVector<LayoutNode> mutableVector = get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode = layoutNodeArr[i];
            UsageByParent usageByParent = layoutNode.previousIntrinsicsUsageByParent;
            layoutNode.intrinsicsUsageByParent = usageByParent;
            if (usageByParent != UsageByParent.NotUsed) {
                layoutNode.resetSubtreeIntrinsicsUsage$ui();
            }
        }
    }

    public final Void rethrowWithComposeStackTrace(Throwable e) throws Throwable {
        CompositionErrorContext traceContext = getTraceContext();
        if (traceContext == null) {
            throw e;
        }
        traceContext.attachComposeStackTrace(e, this);
        throw e;
    }

    public final void setAddedToRectList$ui(boolean z) {
        this.addedToRectList = z;
    }

    public final void setCanMultiMeasure$ui(boolean z) {
        this.canMultiMeasure = z;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setCompositeKeyHash(int i) {
        this.compositeKeyHash = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "result" is null
        	at jadx.core.dex.visitors.PrepareForCodeGen.removeInstructions(PrepareForCodeGen.java:118)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:85)
        */
    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setCompositionLocalMap(androidx.compose.runtime.CompositionLocalMap r9) {
        /*
            r8 = this;
            r8.compositionLocalMap = r9
            androidx.compose.runtime.ProvidableCompositionLocal r0 = androidx.compose.ui.platform.CompositionLocalsKt.getLocalDensity()
            java.lang.Object r0 = r9.get(r0)
            androidx.compose.ui.unit.Density r0 = (androidx.compose.ui.unit.Density) r0
            r8.setDensity(r0)
            androidx.compose.runtime.ProvidableCompositionLocal r0 = androidx.compose.ui.platform.CompositionLocalsKt.getLocalLayoutDirection()
            java.lang.Object r0 = r9.get(r0)
            androidx.compose.ui.unit.LayoutDirection r0 = (androidx.compose.ui.unit.LayoutDirection) r0
            r8.setLayoutDirection(r0)
            androidx.compose.runtime.ProvidableCompositionLocal r0 = androidx.compose.ui.platform.CompositionLocalsKt.getLocalViewConfiguration()
            java.lang.Object r9 = r9.get(r0)
            androidx.compose.ui.platform.ViewConfiguration r9 = (androidx.compose.ui.platform.ViewConfiguration) r9
            r8.setViewConfiguration(r9)
            androidx.compose.ui.node.NodeChain r8 = r8.nodes
            r9 = 32768(0x8000, float:4.5918E-41)
            int r9 = androidx.compose.ui.node.NodeKind.m4949constructorimpl(r9)
            int r0 = androidx.compose.ui.node.NodeChain.access$getAggregateChildKindSet(r8)
            r0 = r0 & r9
            if (r0 == 0) goto Lb4
            androidx.compose.ui.Modifier$Node r8 = r8.getHead()
        L3d:
            if (r8 == 0) goto Lb4
            int r0 = r8.getKindSet()
            r0 = r0 & r9
            if (r0 == 0) goto La8
            r0 = 0
            r1 = r8
            r2 = r0
        L49:
            if (r1 == 0) goto La8
            boolean r3 = r1 instanceof androidx.compose.ui.node.CompositionLocalConsumerModifierNode
            r4 = 1
            if (r3 == 0) goto L64
            androidx.compose.ui.node.CompositionLocalConsumerModifierNode r1 = (androidx.compose.ui.node.CompositionLocalConsumerModifierNode) r1
            androidx.compose.ui.Modifier$Node r1 = r1.getNode()
            boolean r3 = r1.getIsAttached()
            if (r3 == 0) goto L60
            androidx.compose.ui.node.NodeKindKt.autoInvalidateUpdatedNode(r1)
            goto La3
        L60:
            r1.setUpdatedNodeAwaitingAttachForInvalidation$ui(r4)
            goto La3
        L64:
            int r3 = r1.getKindSet()
            r3 = r3 & r9
            if (r3 == 0) goto La3
            boolean r3 = r1 instanceof androidx.compose.ui.node.DelegatingNode
            if (r3 == 0) goto La3
            r3 = r1
            androidx.compose.ui.node.DelegatingNode r3 = (androidx.compose.ui.node.DelegatingNode) r3
            androidx.compose.ui.Modifier$Node r3 = r3.getDelegate()
            r5 = 0
            r6 = r5
        L78:
            if (r3 == 0) goto La0
            int r7 = r3.getKindSet()
            r7 = r7 & r9
            if (r7 == 0) goto L9b
            int r6 = r6 + 1
            if (r6 != r4) goto L87
            r1 = r3
            goto L9b
        L87:
            if (r2 != 0) goto L92
            androidx.compose.runtime.collection.MutableVector r2 = new androidx.compose.runtime.collection.MutableVector
            r7 = 16
            androidx.compose.ui.Modifier$Node[] r7 = new androidx.compose.ui.Modifier.Node[r7]
            r2.<init>(r7, r5)
        L92:
            if (r1 == 0) goto L98
            r2.add(r1)
            r1 = r0
        L98:
            r2.add(r3)
        L9b:
            androidx.compose.ui.Modifier$Node r3 = r3.getChild()
            goto L78
        La0:
            if (r6 != r4) goto La3
            goto L49
        La3:
            androidx.compose.ui.Modifier$Node r1 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r2)
            goto L49
        La8:
            int r0 = r8.getAggregateChildKindSet()
            r0 = r0 & r9
            if (r0 == 0) goto Lb4
            androidx.compose.ui.Modifier$Node r8 = r8.getChild()
            goto L3d
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutNode.setCompositionLocalMap(androidx.compose.runtime.CompositionLocalMap):void");
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setDensity(Density density) {
        if (Intrinsics.areEqual(this.density, density)) {
            return;
        }
        this.density = density;
        onDensityOrLayoutDirectionChanged();
        for (Modifier.Node head = this.nodes.getHead(); head != null; head = head.getChild()) {
            head.onDensityChange();
        }
    }

    public final void setDepth$ui(int i) {
        this.depth = i;
    }

    public final void setGloballyPositionedObservers(int i) {
        LayoutNode parent$ui;
        LayoutNode parent$ui2;
        int i2 = this.globallyPositionedObservers;
        if (i2 != i) {
            if (i > 0 && i2 == 0 && (parent$ui2 = getParent$ui()) != null) {
                parent$ui2.setGloballyPositionedObservers(parent$ui2.globallyPositionedObservers + 1);
            }
            if (i == 0 && this.globallyPositionedObservers > 0 && (parent$ui = getParent$ui()) != null) {
                parent$ui.setGloballyPositionedObservers(parent$ui.globallyPositionedObservers - 1);
            }
            this.globallyPositionedObservers = i;
        }
    }

    public final void setHasPositionalLayerTransformationsInOffsetFromRoot$ui(boolean z) {
        this.hasPositionalLayerTransformationsInOffsetFromRoot = z;
    }

    public final void setInnerLayerCoordinatorIsDirty$ui(boolean z) {
        this.innerLayerCoordinatorIsDirty = z;
    }

    public final void setInteropViewFactoryHolder$ui(AndroidViewHolder androidViewHolder) {
        this.interopViewFactoryHolder = androidViewHolder;
    }

    public final void setIntrinsicsUsageByParent$ui(UsageByParent usageByParent) {
        this.intrinsicsUsageByParent = usageByParent;
    }

    /* JADX INFO: renamed from: setLastOffsetFromParent--gyyYBs$ui, reason: not valid java name */
    public final void m4848setLastOffsetFromParentgyyYBs$ui(long j) {
        this.lastOffsetFromParent = j;
    }

    /* JADX INFO: renamed from: setLastSize-ozmzZPI$ui, reason: not valid java name */
    public final void m4849setLastSizeozmzZPI$ui(long j) {
        this.lastSize = j;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setLayoutDirection(LayoutDirection layoutDirection) {
        if (this.layoutDirection != layoutDirection) {
            this.layoutDirection = layoutDirection;
            onDensityOrLayoutDirectionChanged();
            for (Modifier.Node head = this.nodes.getHead(); head != null; head = head.getChild()) {
                head.onLayoutDirectionChange();
            }
        }
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setMeasurePolicy(MeasurePolicy measurePolicy) {
        if (Intrinsics.areEqual(this.measurePolicy, measurePolicy)) {
            return;
        }
        this.measurePolicy = measurePolicy;
        IntrinsicsPolicy intrinsicsPolicy = this.intrinsicsPolicy;
        if (intrinsicsPolicy != null) {
            intrinsicsPolicy.updateFrom(getMeasurePolicy());
        }
        invalidateMeasurements$ui();
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setModifier(Modifier modifier) {
        if (!(!this.isVirtual || get_modifier() == Modifier.INSTANCE)) {
            InlineClassHelperKt.throwIllegalArgumentException("Modifiers are not supported on virtual LayoutNodes");
        }
        if (getIsDeactivated()) {
            InlineClassHelperKt.throwIllegalArgumentException("modifier is updated when deactivated");
        }
        if (!isAttached()) {
            this.pendingModifier = modifier;
            return;
        }
        applyModifier(modifier);
        if (this.isSemanticsInvalidated) {
            invalidateSemantics$ui();
        }
    }

    public final void setNeedsOnGloballyPositionedDispatch$ui(boolean z) {
        this.needsOnGloballyPositionedDispatch = z;
    }

    public final void setOnAttach$ui(Function1<? super Owner, Unit> function1) {
        this.onAttach = function1;
    }

    public final void setOnDetach$ui(Function1<? super Owner, Unit> function1) {
        this.onDetach = function1;
    }

    /* JADX INFO: renamed from: setOuterToInnerOffset--gyyYBs$ui, reason: not valid java name */
    public final void m4850setOuterToInnerOffsetgyyYBs$ui(long j) {
        this.outerToInnerOffset = j;
    }

    public final void setOuterToInnerOffsetDirty$ui(boolean z) {
        this.outerToInnerOffsetDirty = z;
    }

    public void setSemanticsId(int i) {
        this.semanticsId = i;
    }

    public final void setSemanticsInvalidated$ui(boolean z) {
        this.isSemanticsInvalidated = z;
    }

    public final void setSubcompositionsState$ui(LayoutNodeSubcompositionsState layoutNodeSubcompositionsState) {
        this.subcompositionsState = layoutNodeSubcompositionsState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6 */
    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setViewConfiguration(ViewConfiguration viewConfiguration) {
        if (Intrinsics.areEqual(this.viewConfiguration, viewConfiguration)) {
            return;
        }
        this.viewConfiguration = viewConfiguration;
        NodeChain nodeChain = this.nodes;
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(16);
        if ((nodeChain.getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
            for (Modifier.Node head = nodeChain.getHead(); head != null; head = head.getChild()) {
                if ((head.getKindSet() & iM4949constructorimpl) != 0) {
                    Modifier.Node nodePop = head;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof PointerInputModifierNode) {
                            ((PointerInputModifierNode) nodePop).onViewConfigurationChange();
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
                if ((head.getAggregateChildKindSet() & iM4949constructorimpl) == 0) {
                    return;
                }
            }
        }
    }

    public final void setVirtualLookaheadRoot$ui(boolean z) {
        this.isVirtualLookaheadRoot = z;
    }

    public String toString() {
        return JvmActuals_jvmKt.simpleIdentityToString(this, null) + " children: " + getChildren$ui().size() + " measurePolicy: " + getMeasurePolicy() + " deactivated: " + getIsDeactivated();
    }

    public final void updateChildrenIfDirty$ui() {
        if (this.virtualChildrenCount > 0) {
            recreateUnfoldedChildrenIfDirty();
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0013j\b\u0012\u0004\u0012\u00020\u000b`\u0014X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$Companion;", "", "<init>", "()V", "ErrorMeasurePolicy", "Landroidx/compose/ui/node/LayoutNode$NoIntrinsicsMeasurePolicy;", "NotPlacedPlaceOrder", "", "getNotPlacedPlaceOrder$ui$annotations", "Constructor", "Lkotlin/Function0;", "Landroidx/compose/ui/node/LayoutNode;", "getConstructor$ui", "()Lkotlin/jvm/functions/Function0;", "DummyViewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getDummyViewConfiguration$ui", "()Landroidx/compose/ui/platform/ViewConfiguration;", "ZComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "getZComparator$ui", "()Ljava/util/Comparator;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getNotPlacedPlaceOrder$ui$annotations() {
        }

        public final Function0<LayoutNode> getConstructor$ui() {
            return LayoutNode.Constructor;
        }

        public final ViewConfiguration getDummyViewConfiguration$ui() {
            return LayoutNode.DummyViewConfiguration;
        }

        public final Comparator<LayoutNode> getZComparator$ui() {
            return LayoutNode.ZComparator;
        }

        private Companion() {
        }
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public SemanticsInfo getParentInfo() {
        return getParent$ui();
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b!\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\u0006\u001a\u00020\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u000e\u001a\u00020\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000f\u001a\u00020\rH\u0016J\"\u0010\u0010\u001a\u00020\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u0011\u001a\u00020\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$NoIntrinsicsMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "error", "", "<init>", "(Ljava/lang/String;)V", "minIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static abstract class NoIntrinsicsMeasurePolicy implements MeasurePolicy {
        public static final int $stable = 0;
        private final String error;

        public NoIntrinsicsMeasurePolicy(String str) {
            this.error = str;
        }

        /* JADX INFO: renamed from: maxIntrinsicHeight, reason: collision with other method in class */
        public Void m4854maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
            throw new IllegalStateException(this.error.toString());
        }

        /* JADX INFO: renamed from: maxIntrinsicWidth, reason: collision with other method in class */
        public Void m4855maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
            throw new IllegalStateException(this.error.toString());
        }

        /* JADX INFO: renamed from: minIntrinsicHeight, reason: collision with other method in class */
        public Void m4856minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
            throw new IllegalStateException(this.error.toString());
        }

        /* JADX INFO: renamed from: minIntrinsicWidth, reason: collision with other method in class */
        public Void m4857minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
            throw new IllegalStateException(this.error.toString());
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            return ((Number) m4854maxIntrinsicHeight(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i)).intValue();
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            return ((Number) m4855maxIntrinsicWidth(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i)).intValue();
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            return ((Number) m4856minIntrinsicHeight(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i)).intValue();
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            return ((Number) m4857minIntrinsicWidth(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i)).intValue();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LayoutNode() {
        this(false, 0 == true ? 1 : 0, 3, null);
    }

    public /* synthetic */ LayoutNode(boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? SemanticsModifierKt.generateSemanticsId() : i);
    }
}
