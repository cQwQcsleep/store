package androidx.compose.material3;

import androidx.collection.IntList;
import androidx.collection.IntListKt;
import androidx.collection.MutableIntList;
import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.TimePickerKt;
import androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.TimeInputTokens;
import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.PointerIconCompat;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a)\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\r\u001a+\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001a \u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\u001a3\u0010\u001b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0002¢\u0006\u0004\b\"\u0010#\u001aJ\u0010$\u001a\u00020\u0001*\u00020%2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001d0(H\u0082@¢\u0006\u0004\b)\u0010*\u001a1\u0010/\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020%2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0013H\u0001¢\u0006\u0002\u00100\u001a1\u00101\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020%2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0013H\u0001¢\u0006\u0002\u00100\u001a%\u00102\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u00103\u001a\u001d\u00104\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00105\u001a\u001d\u00106\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00105\u001a\u001d\u00107\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00105\u001a%\u00108\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00109\u001a%\u0010:\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00109\u001a=\u0010;\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?H\u0003¢\u0006\u0002\u0010A\u001aQ\u0010B\u001a\u00020\u00012\u0006\u0010C\u001a\u00020\u00132\u0006\u0010D\u001a\u00020?2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00010F2\u0006\u0010\u0006\u001a\u00020\u00072\u001c\u0010G\u001a\u0018\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020\u00010H¢\u0006\u0002\bJ¢\u0006\u0002\bKH\u0003¢\u0006\u0002\u0010L\u001a\u0015\u0010M\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010N\u001a7\u0010O\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0004\bS\u0010T\u001a-\u0010U\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020%2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0013H\u0001¢\u0006\u0002\u0010V\u001a\u001c\u0010W\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020%2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a-\u0010X\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020%2\u0006\u0010P\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010Y\u001ah\u0010Z\u001a\u00020\u00012\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010P\u001a\u00020[2\u0006\u0010\\\u001a\u00020[2\u0006\u0010]\u001a\u00020\u00102\f\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00130_2!\u0010`\u001a\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\ba\u0012\b\bb\u0012\u0004\b\b(P\u0012\u0004\u0012\u00020\u00010HH\u0002¢\u0006\u0004\bc\u0010d\u001a_\u0010e\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010P\u001a\u00020[2\u0012\u0010f\u001a\u000e\u0012\u0004\u0012\u00020[\u0012\u0004\u0012\u00020\u00010H2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020R2\b\b\u0002\u0010g\u001a\u00020h2\b\b\u0002\u0010i\u001a\u00020j2\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0004\bk\u0010l\u001a4\u0010m\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010n\u001a\u00020\u001d2\u0011\u0010G\u001a\r\u0012\u0004\u0012\u00020\u00010F¢\u0006\u0002\bJH\u0003¢\u0006\u0002\u0010o\u001a'\u0010p\u001a\u00020q2\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010r\u001a\u00020\u0010H\u0001¢\u0006\u0004\bs\u0010t\u001a(\u0010u\u001a\u00020\u001d2\u0006\u0010v\u001a\u00020\u001d2\u0006\u0010w\u001a\u00020\u001d2\u0006\u0010x\u001a\u00020\u00102\u0006\u0010y\u001a\u00020\u0010H\u0002\u001a\u0018\u0010z\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002\u001a\u0016\u0010\u009d\u0001\u001a\u00020\u0005*\u00020\u00052\u0007\u0010\u009d\u0001\u001a\u00020\u0013H\u0003\"\u0015\u0010\u0015\u001a\u00020\u0013*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\"\u0018\u0010\u0018\u001a\u00020\u0010*\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\"\u0018\u0010+\u001a\u00020,*\u00020%8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.\"\u001a\u0010{\u001a\u00020\t8AX\u0080\u0004¢\u0006\f\u0012\u0004\b|\u0010}\u001a\u0004\b~\u0010\u007f\"\u000f\u0010\u0080\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0081\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0082\u0001\u001a\u00030\u0083\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0084\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0085\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0086\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0087\u0001\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000f\u0010\u0088\u0001\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008c\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008d\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008e\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008f\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0090\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0091\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0010\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0094\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0095\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0096\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0097\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0098\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0099\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u001b\u0010\u009a\u0001\u001a\u00030\u008a\u0001X\u0080\u0004¢\u0006\r\n\u0003\u0010\u008b\u0001\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001¨\u0006\u009e\u0001²\u0006\u000b\u0010\u009f\u0001\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\u000b\u0010 \u0001\u001a\u00020[X\u008a\u008e\u0002²\u0006\u000b\u0010¡\u0001\u001a\u00020[X\u008a\u008e\u0002²\u0006\u000b\u0010 \u001a\u00030¢\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010£\u0001\u001a\u00020!X\u008a\u008e\u0002²\u0006\f\u0010¤\u0001\u001a\u00030¥\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010¦\u0001\u001a\u00020\u0013X\u008a\u0084\u0002"}, d2 = {"TimePicker", "", "state", "Landroidx/compose/material3/TimePickerState;", "modifier", "Landroidx/compose/ui/Modifier;", "colors", "Landroidx/compose/material3/TimePickerColors;", "layoutType", "Landroidx/compose/material3/TimePickerLayoutType;", "TimePicker-mT9BvqQ", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ILandroidx/compose/runtime/Composer;II)V", "TimeInput", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "rememberTimePickerState", "initialHour", "", "initialMinute", "is24Hour", "", "(IIZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TimePickerState;", "isPm", "(Landroidx/compose/material3/TimePickerState;)Z", "TimePickerState", "hourForDisplay", "getHourForDisplay", "(Landroidx/compose/material3/TimePickerState;)I", "moveSelector", "x", "", "y", "maxDist", "center", "Landroidx/compose/ui/unit/IntOffset;", "moveSelector-d3b8Pxo", "(Landroidx/compose/material3/TimePickerState;FFFJ)V", "onTap", "Landroidx/compose/material3/AnalogTimePickerState;", "autoSwitchToMinute", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "onTap-uYHVD98", "(Landroidx/compose/material3/AnalogTimePickerState;FFFZJLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectorPos", "Landroidx/compose/ui/unit/DpOffset;", "getSelectorPos", "(Landroidx/compose/material3/AnalogTimePickerState;)J", "VerticalTimePicker", "(Landroidx/compose/material3/AnalogTimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;II)V", "HorizontalTimePicker", "TimeInputImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/material3/TimePickerState;Landroidx/compose/runtime/Composer;I)V", "HorizontalClockDisplay", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "VerticalClockDisplay", "ClockDisplayNumbers", "HorizontalPeriodToggle", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "VerticalPeriodToggle", "PeriodToggleImpl", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "startShape", "Landroidx/compose/ui/graphics/Shape;", "endShape", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "ToggleItem", "checked", "shape", "onClick", "Lkotlin/Function0;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/TimePickerColors;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "DisplaySeparator", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "TimeSelector", "value", "selection", "Landroidx/compose/material3/TimePickerSelectionMode;", "TimeSelector-SAnMeKU", "(Landroidx/compose/ui/Modifier;ILandroidx/compose/material3/TimePickerState;ILandroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "ClockFace", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/AnalogTimePickerState;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;I)V", "drawSelector", "ClockText", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/AnalogTimePickerState;IZLandroidx/compose/runtime/Composer;I)V", "timeInputOnChange", "Landroidx/compose/ui/text/input/TextFieldValue;", "prevValue", "max", "userOverride", "Landroidx/compose/ui/node/Ref;", "onNewValue", "Lkotlin/ParameterName;", "name", "timeInputOnChange-_K77t-0", "(ILandroidx/compose/material3/TimePickerState;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/TextFieldValue;ILandroidx/compose/ui/node/Ref;Lkotlin/jvm/functions/Function1;)V", "TimePickerTextField", "onValueChange", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "TimePickerTextField-1vLObsk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/TimePickerState;ILandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "CircularLayout", "radiusToSizeRatio", "(Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "numberContentDescription", "", "number", "numberContentDescription-dSwYdS4", "(IZILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "dist", "x1", "y1", "x2", "y2", "atan", "defaultTimePickerLayoutType", "getDefaultTimePickerLayoutType$annotations", "()V", "getDefaultTimePickerLayoutType", "(Landroidx/compose/runtime/Composer;I)I", "FullCircle", "HalfCircle", "QuarterCircle", "", "RadiansPerMinute", "RadiansPerHour", "SeparatorZIndex", "OuterCircleToSizeRatio", "InnerCircleToSizeRatio", "ClockDisplayBottomMargin", "Landroidx/compose/ui/unit/Dp;", "F", "ClockFaceBottomMargin", "DisplaySeparatorWidth", "SupportLabelTop", "TimeInputBottomPadding", "MaxDistance", "MinimumInteractiveSize", "Minutes", "Landroidx/collection/IntList;", "Hours", "ExtraHours", "PeriodToggleMargin", "TimePickerMaxHeight", "TimePickerMidHeight", "ClockDialMidContainerSize", "ClockDialMinContainerSize", "getClockDialMinContainerSize", "()F", "visible", "material3", "a11yServicesEnabled", "hourValue", "minuteValue", "Landroidx/compose/ui/geometry/Offset;", "parentCenter", "boundsInParent", "Landroidx/compose/ui/geometry/Rect;", "selected"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TimePickerKt {
    private static final float ClockDialMidContainerSize;
    private static final float ClockDialMinContainerSize;
    private static final float ClockDisplayBottomMargin;
    private static final float ClockFaceBottomMargin;
    private static final float DisplaySeparatorWidth;
    private static final IntList ExtraHours;
    private static final float FullCircle = 6.2831855f;
    private static final float HalfCircle = 3.1415927f;
    private static final IntList Hours;
    private static final float InnerCircleToSizeRatio;
    private static final float MaxDistance;
    private static final float MinimumInteractiveSize;
    private static final IntList Minutes;
    private static final float OuterCircleToSizeRatio;
    private static final float PeriodToggleMargin;
    private static final double QuarterCircle = 1.5707963267948966d;
    private static final float RadiansPerHour = 0.5235988f;
    private static final float RadiansPerMinute = 0.10471976f;
    private static final float SeparatorZIndex = 2.0f;
    private static final float SupportLabelTop;
    private static final float TimeInputBottomPadding;
    private static final float TimePickerMaxHeight;
    private static final float TimePickerMidHeight;

    static {
        float fM6022constructorimpl = Dp.m6022constructorimpl(101.0f);
        TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
        OuterCircleToSizeRatio = fM6022constructorimpl / timePickerTokens.m2195getClockDialContainerSizeD9Ej5fM();
        InnerCircleToSizeRatio = Dp.m6022constructorimpl(69.0f) / timePickerTokens.m2195getClockDialContainerSizeD9Ej5fM();
        ClockDisplayBottomMargin = Dp.m6022constructorimpl(36.0f);
        ClockFaceBottomMargin = Dp.m6022constructorimpl(24.0f);
        DisplaySeparatorWidth = Dp.m6022constructorimpl(24.0f);
        SupportLabelTop = Dp.m6022constructorimpl(7.0f);
        TimeInputBottomPadding = Dp.m6022constructorimpl(24.0f);
        MaxDistance = Dp.m6022constructorimpl(74.0f);
        MinimumInteractiveSize = Dp.m6022constructorimpl(48.0f);
        Minutes = IntListKt.intListOf(new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55});
        IntList intListIntListOf = IntListKt.intListOf(new int[]{12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        Hours = intListIntListOf;
        MutableIntList mutableIntList = new MutableIntList(intListIntListOf._size);
        int[] iArr = intListIntListOf.content;
        int i = intListIntListOf._size;
        for (int i2 = 0; i2 < i; i2++) {
            mutableIntList.add((iArr[i2] % 12) + 12);
        }
        ExtraHours = mutableIntList;
        PeriodToggleMargin = Dp.m6022constructorimpl(12.0f);
        TimePickerMaxHeight = Dp.m6022constructorimpl(384.0f);
        TimePickerMidHeight = Dp.m6022constructorimpl(330.0f);
        ClockDialMidContainerSize = Dp.m6022constructorimpl(238.0f);
        ClockDialMinContainerSize = Dp.m6022constructorimpl(200.0f);
    }

    public static Unit A(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.m5264setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5250getRadioButtono7Vup1c());
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
        return Unit.INSTANCE;
    }

    public static Unit B(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
        return Unit.INSTANCE;
    }

    public static Unit C(Modifier modifier, TextFieldValue textFieldValue, Function1 function1, TimePickerState timePickerState, int i, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, TimePickerColors timePickerColors, int i2, int i3, Composer composer, int i4) {
        m1138TimePickerTextField1vLObsk(modifier, textFieldValue, function1, timePickerState, i, keyboardOptions, keyboardActions, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CircularLayout(Modifier modifier, final float f, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1041042571);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        } else if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1041042571, i3, -1, "androidx.compose.material3.CircularLayout (TimePicker.kt:1978)");
            }
            boolean z = (i3 & 112) == 32;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new TimePickerKt$CircularLayout$1$1(f);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            int i5 = ((i3 >> 6) & 14) | ((i3 << 3) & 112);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion.getConstructor();
            int i6 = ((i5 << 6) & 896) | 6;
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            function2.invoke(composerStartRestartGroup, Integer.valueOf((i6 >> 6) & 14));
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        final Modifier modifier2 = modifier;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ife
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.w(modifier2, f, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void ClockDisplayNumbers(final TimePickerState timePickerState, final TimePickerColors timePickerColors, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-934561141);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(timePickerState) : composerStartRestartGroup.changedInstance(timePickerState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(timePickerColors) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-934561141, i2, -1, "androidx.compose.material3.ClockDisplayNumbers (TimePicker.kt:1173)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(TypographyKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorLabelTextFont(), composerStartRestartGroup, 6)), CompositionLocalsKt.getLocalLayoutDirection().provides(LayoutDirection.Ltr)}, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-477913269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockDisplayNumbers.1
                public final void invoke(Composer composer2, int i3) {
                    if (!composer2.shouldExecute((i3 & 3) != 2, i3 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-477913269, i3, -1, "androidx.compose.material3.ClockDisplayNumbers.<anonymous> (TimePicker.kt:1179)");
                    }
                    TimePickerState timePickerState2 = timePickerState;
                    TimePickerColors timePickerColors2 = timePickerColors;
                    Modifier.Companion companion = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer2, 0);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion);
                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                    if (composer2.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
                    Modifier modifier = SizeKt.size-VpY3zN4(companion, timePickerTokens.m2207getTimeSelectorContainerWidthD9Ej5fM(), timePickerTokens.m2206getTimeSelectorContainerHeightD9Ej5fM());
                    int hourForDisplay = TimePickerKt.getHourForDisplay(timePickerState2);
                    TimePickerSelectionMode.Companion companion3 = TimePickerSelectionMode.INSTANCE;
                    TimePickerKt.m1139TimeSelectorSAnMeKU(modifier, hourForDisplay, timePickerState2, companion3.m1167getHouryecRtBI(), timePickerColors2, composer2, 3078);
                    TimePickerKt.DisplaySeparator(SizeKt.size-VpY3zN4(companion, TimePickerKt.DisplaySeparatorWidth, timePickerTokens.m2203getPeriodSelectorVerticalContainerHeightD9Ej5fM()), composer2, 6);
                    TimePickerKt.m1139TimeSelectorSAnMeKU(SizeKt.size-VpY3zN4(companion, timePickerTokens.m2207getTimeSelectorContainerWidthD9Ej5fM(), timePickerTokens.m2206getTimeSelectorContainerHeightD9Ej5fM()), timePickerState2.getMinute(), timePickerState2, companion3.m1168getMinuteyecRtBI(), timePickerColors2, composer2, 3078);
                    composer2.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ofe
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.o(timePickerState, timePickerColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ClockFace(final Modifier modifier, AnalogTimePickerState analogTimePickerState, final TimePickerColors timePickerColors, final boolean z, Composer composer, final int i) {
        int i2;
        final AnalogTimePickerState analogTimePickerState2 = analogTimePickerState;
        Composer composerStartRestartGroup = composer.startRestartGroup(-478841003);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(analogTimePickerState2) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(timePickerColors) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-478841003, i2, -1, "androidx.compose.material3.ClockFace (TimePicker.kt:1591)");
            }
            analogTimePickerState2 = analogTimePickerState;
            CrossfadeKt.Crossfade(analogTimePickerState2.getClockFaceValues(), drawSelector(BackgroundKt.background-bw27NRU(modifier, timePickerColors.getClockDialColor(), RoundedCornerShapeKt.getCircleShape()).then(new ClockDialModifier(analogTimePickerState, z, analogTimePickerState.mo74getSelectionyecRtBI(), MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, composerStartRestartGroup, 6), null)), analogTimePickerState2, timePickerColors), MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), (String) null, ComposableLambdaKt.rememberComposableLambda(747010833, true, new C00641(timePickerColors, analogTimePickerState2, z), composerStartRestartGroup, 54), composerStartRestartGroup, 24576, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: iee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.q(modifier, analogTimePickerState2, timePickerColors, z, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText(final Modifier modifier, final AnalogTimePickerState analogTimePickerState, final int i, final boolean z, Composer composer, final int i2) {
        int i3;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-206784607);
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(analogTimePickerState) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= composerStartRestartGroup.changed(i) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        int i4 = i3;
        if (composerStartRestartGroup.shouldExecute((i4 & 1171) != 1170, i4 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-206784607, i4, -1, "androidx.compose.material3.ClockText (TimePicker.kt:1727)");
            }
            TextStyle value = TypographyKt.getValue(TimePickerTokens.INSTANCE.getClockDialLabelTextFont(), composerStartRestartGroup, 6);
            final Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            final float fMo4557toPx0680j_4 = density.mo4557toPx0680j_4(MaxDistance);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m2878boximpl(Offset.INSTANCE.m2905getZeroF1C5BW0()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntOffset.m6141boximpl(IntOffset.INSTANCE.m6161getZeronOccac()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Rect.INSTANCE.getZero(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue4;
            final String strM1146numberContentDescriptiondSwYdS4 = m1146numberContentDescriptiondSwYdS4(analogTimePickerState.mo74getSelectionyecRtBI(), analogTimePickerState.getIs24hour(), i, composerStartRestartGroup, i4 & 896);
            String localString$default = CalendarLocale_jvmKt.toLocalString$default(i, 0, 0, false, null, 15, null);
            boolean zChanged = composerStartRestartGroup.changed(analogTimePickerState);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: lee
                    public final Object invoke() {
                        return Boolean.valueOf(TimePickerKt.d(analogTimePickerState, density, mutableState3));
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final State state = (State) objRememberedValue5;
            Alignment center = Alignment.INSTANCE.getCenter();
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == companion.getEmpty()) {
                objRememberedValue6 = new Function1() { // from class: mee
                    public final Object invoke(Object obj) {
                        return TimePickerKt.x(mutableState2, mutableState3, mutableState, (LayoutCoordinates) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            Modifier modifierFocusable$default = FocusableKt.focusable$default(SizeKt.size-3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(OnGloballyPositionedModifierKt.onGloballyPositioned(modifier, (Function1) objRememberedValue6)), MinimumInteractiveSize), false, (MutableInteractionSource) null, 3, (Object) null);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changedInstance(analogTimePickerState) | composerStartRestartGroup.changed(fMo4557toPx0680j_4) | ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) | composerStartRestartGroup.changed(state);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue7 == companion.getEmpty()) {
                Function1 function1 = new Function1() { // from class: nee
                    public final Object invoke(Object obj) {
                        return TimePickerKt.D(coroutineScope, analogTimePickerState, fMo4557toPx0680j_4, z, mutableState, mutableState2, state, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function1);
                objRememberedValue7 = function1;
            }
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifierFocusable$default, true, (Function1) objRememberedValue7);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierSemantics);
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier.Companion companion3 = Modifier.INSTANCE;
            boolean zChanged2 = composerStartRestartGroup.changed(strM1146numberContentDescriptiondSwYdS4);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue8 == companion.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: oee
                    public final Object invoke(Object obj) {
                        return TimePickerKt.s(strM1146numberContentDescriptiondSwYdS4, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            composer2 = composerStartRestartGroup;
            TextKt.m1097TextNvy7gAk(localString$default, SemanticsModifierKt.clearAndSetSemantics(companion3, (Function1) objRememberedValue8), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, value, composer2, 0, 0, 131068);
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.m(modifier, analogTimePickerState, i, z, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ClockText$lambda$64(MutableState<Offset> mutableState) {
        return mutableState.getValue().m2899unboximpl();
    }

    private static final void ClockText$lambda$65(MutableState<Offset> mutableState, long j) {
        mutableState.setValue(Offset.m2878boximpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ClockText$lambda$67(MutableState<IntOffset> mutableState) {
        return mutableState.getValue().m6159unboximpl();
    }

    private static final void ClockText$lambda$68(MutableState<IntOffset> mutableState, long j) {
        mutableState.setValue(IntOffset.m6141boximpl(j));
    }

    private static final Rect ClockText$lambda$70(MutableState<Rect> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean ClockText$lambda$75(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    public static Unit D(final CoroutineScope coroutineScope, final AnalogTimePickerState analogTimePickerState, final float f, final boolean z, final MutableState mutableState, final MutableState mutableState2, State state, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0() { // from class: kfe
            public final Object invoke() {
                return Boolean.valueOf(TimePickerKt.E(coroutineScope, analogTimePickerState, f, z, mutableState, mutableState2));
            }
        }, 1, null);
        SemanticsPropertiesKt.setSelected(semanticsPropertyReceiver, ClockText$lambda$75(state));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DisplaySeparator(final Modifier modifier, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2100674302);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2100674302, i2, -1, "androidx.compose.material3.DisplaySeparator (TimePicker.kt:1379)");
            }
            TextStyle textStyleM5492copyp1EtxEg$default = TextStyle.m5492copyp1EtxEg$default((TextStyle) composerStartRestartGroup.consume(TextKt.getLocalTextStyle()), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, new LineHeightStyle(LineHeightStyle.Alignment.INSTANCE.m5867getCenterPIaL0Z0(), LineHeightStyle.Trim.INSTANCE.m5889getBothEVpEnUU(), (DefaultConstructorMarker) null), 0, 0, null, 15695871, null);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: ree
                    public final Object invoke(Object obj) {
                        return TimePickerKt.a((SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(modifier, (Function1) objRememberedValue);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierClearAndSetSemantics);
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m1097TextNvy7gAk(":", null, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSeparatorColor(), composerStartRestartGroup, 6), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, textStyleM5492copyp1EtxEg$default, composer2, 6, 0, 131066);
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: see
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.k(modifier, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static boolean E(CoroutineScope coroutineScope, AnalogTimePickerState analogTimePickerState, float f, boolean z, MutableState mutableState, MutableState mutableState2) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new TimePickerKt$ClockText$2$1$1$1(analogTimePickerState, f, z, mutableState, mutableState2, null), 3, (Object) null);
        return true;
    }

    public static Unit F(TimePickerState timePickerState) {
        if (!isPm(timePickerState)) {
            timePickerState.setHour(timePickerState.getHour() + 12);
        }
        return Unit.INSTANCE;
    }

    public static Unit G(AnalogTimePickerState analogTimePickerState, TimePickerColors timePickerColors, ContentDrawScope contentDrawScope) {
        float fMo4557toPx0680j_4 = contentDrawScope.mo4557toPx0680j_4(DpOffset.m6083getXD9Ej5fM(getSelectorPos(analogTimePickerState)));
        long jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(contentDrawScope.mo4557toPx0680j_4(DpOffset.m6085getYD9Ej5fM(getSelectorPos(analogTimePickerState))))) & 4294967295L) | (Float.floatToRawIntBits(fMo4557toPx0680j_4) << 32));
        TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
        float fMo4557toPx0680j_5 = ((contentDrawScope.mo4557toPx0680j_4(timePickerTokens.m2197getClockDialSelectorHandleContainerSizeD9Ej5fM()) / SeparatorZIndex) * contentDrawScope.mo4551roundToPx0680j_4(analogTimePickerState.m73getCurrentDiameterD9Ej5fM())) / contentDrawScope.mo4551roundToPx0680j_4(timePickerTokens.m2195getClockDialContainerSizeD9Ej5fM());
        long selectorColor = timePickerColors.getSelectorColor();
        long jM3160getBlack0d7_KjU = Color.INSTANCE.m3160getBlack0d7_KjU();
        BlendMode.Companion companion = BlendMode.INSTANCE;
        DrawScope.m3689drawCircleVaOC9Bg$default(contentDrawScope, jM3160getBlack0d7_KjU, fMo4557toPx0680j_5, jM2881constructorimpl, 0.0f, null, null, companion.m3047getClear0nO6VwU(), 56, null);
        contentDrawScope.drawContent();
        DrawScope.m3689drawCircleVaOC9Bg$default(contentDrawScope, selectorColor, fMo4557toPx0680j_5, jM2881constructorimpl, 0.0f, null, null, companion.m3075getXor0nO6VwU(), 56, null);
        float fMo4557toPx0680j_6 = contentDrawScope.mo4557toPx0680j_4(timePickerTokens.m2198getClockDialSelectorTrackContainerWidthD9Ej5fM());
        float fCos = ((float) Math.cos(analogTimePickerState.getCurrentAngle())) * fMo4557toPx0680j_5;
        DrawScope.m3694drawLineNGM6Ib0$default(contentDrawScope, selectorColor, androidx.compose.ui.geometry.SizeKt.m2968getCenteruvyYCjk(contentDrawScope.mo3708getSizeNHjbRc()), Offset.m2893minusMKHz9U(jM2881constructorimpl, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(((float) Math.sin(analogTimePickerState.getCurrentAngle())) * fMo4557toPx0680j_5)) & 4294967295L) | (Float.floatToRawIntBits(fCos) << 32))), fMo4557toPx0680j_6, 0, null, 0.0f, null, companion.m3074getSrcOver0nO6VwU(), 240, null);
        DrawScope.m3689drawCircleVaOC9Bg$default(contentDrawScope, selectorColor, contentDrawScope.mo4557toPx0680j_4(timePickerTokens.m2196getClockDialSelectorCenterContainerSizeD9Ej5fM()) / SeparatorZIndex, androidx.compose.ui.geometry.SizeKt.m2968getCenteruvyYCjk(contentDrawScope.mo3708getSizeNHjbRc()), 0.0f, null, null, 0, 120, null);
        DrawScope.m3689drawCircleVaOC9Bg$default(contentDrawScope, timePickerColors.m1101clockDialContentColorvNxB06k$material3(true), fMo4557toPx0680j_5, jM2881constructorimpl, 0.0f, null, null, companion.m3057getDstOver0nO6VwU(), 56, null);
        return Unit.INSTANCE;
    }

    public static Unit H(TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        HorizontalClockDisplay(timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final void HorizontalClockDisplay(final TimePickerState timePickerState, final TimePickerColors timePickerColors, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(755539561);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(timePickerState) : composerStartRestartGroup.changedInstance(timePickerState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(timePickerColors) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(755539561, i2, -1, "androidx.compose.material3.HorizontalClockDisplay (TimePicker.kt:1133)");
            }
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            Modifier.Companion companion = Modifier.INSTANCE;
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, companion2.getStart(), composerStartRestartGroup, 6);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion3.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ClockDisplayNumbers(timePickerState, timePickerColors, composerStartRestartGroup, i2 & 126);
            if (timePickerState.getIs24hour()) {
                composerStartRestartGroup.startReplaceGroup(999020143);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(998576161);
                Modifier modifier = PaddingKt.padding-qDBjuR0$default(companion, 0.0f, PeriodToggleMargin, 0.0f, 0.0f, 13, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion2.getTopStart(), false);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
                Function0<ComposeUiNode> constructor2 = companion3.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
                if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
                int i3 = i2 << 3;
                HorizontalPeriodToggle(SizeKt.size-VpY3zN4(companion, timePickerTokens.m2201getPeriodSelectorHorizontalContainerWidthD9Ej5fM(), timePickerTokens.m2200getPeriodSelectorHorizontalContainerHeightD9Ej5fM()), timePickerState, timePickerColors, composerStartRestartGroup, 6 | (i3 & 112) | (i3 & 896));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfe
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.H(timePickerState, timePickerColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void HorizontalPeriodToggle(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, Composer composer, final int i) {
        int i2;
        final Modifier modifier2;
        final TimePickerState timePickerState2;
        final TimePickerColors timePickerColors2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1261215927);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composerStartRestartGroup.changed(timePickerState) : composerStartRestartGroup.changedInstance(timePickerState) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(timePickerColors) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1261215927, i2, -1, "androidx.compose.material3.HorizontalPeriodToggle (TimePicker.kt:1206)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1.INSTANCE;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            CornerBasedShape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), composerStartRestartGroup, 6);
            value.getClass();
            CornerBasedShape cornerBasedShape = value;
            modifier2 = modifier;
            timePickerState2 = timePickerState;
            timePickerColors2 = timePickerColors;
            PeriodToggleImpl(modifier2, timePickerState2, timePickerColors2, measurePolicy, ShapesKt.start$default(cornerBasedShape, null, 1, null), ShapesKt.end$default(cornerBasedShape, null, 1, null), composerStartRestartGroup, (i2 & 14) | 3072 | (i2 & 112) | (i2 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            modifier2 = modifier;
            timePickerState2 = timePickerState;
            timePickerColors2 = timePickerColors;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sfe
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.e(modifier2, timePickerState2, timePickerColors2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0043  */
    /* JADX WARN: Code duplicated, block: B:28:0x0047  */
    /* JADX WARN: Code duplicated, block: B:30:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0052  */
    /* JADX WARN: Code duplicated, block: B:34:0x0058  */
    /* JADX WARN: Code duplicated, block: B:37:0x005e  */
    /* JADX WARN: Code duplicated, block: B:39:0x0063  */
    /* JADX WARN: Code duplicated, block: B:41:0x0067  */
    /* JADX WARN: Code duplicated, block: B:43:0x006f  */
    /* JADX WARN: Code duplicated, block: B:44:0x0072  */
    /* JADX WARN: Code duplicated, block: B:48:0x007d  */
    /* JADX WARN: Code duplicated, block: B:49:0x007f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0088  */
    /* JADX WARN: Code duplicated, block: B:54:0x0090  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:76:0x0112  */
    /* JADX WARN: Code duplicated, block: B:79:0x011e  */
    /* JADX WARN: Code duplicated, block: B:80:0x0122  */
    /* JADX WARN: Code duplicated, block: B:83:0x0141  */
    /* JADX WARN: Code duplicated, block: B:85:0x014f  */
    /* JADX WARN: Code duplicated, block: B:88:0x019b  */
    /* JADX WARN: Code duplicated, block: B:90:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:93:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:95:? A[RETURN, SYNTHETIC] */
    public static final void HorizontalTimePicker(final AnalogTimePickerState analogTimePickerState, Modifier modifier, TimePickerColors timePickerColors, final boolean z, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        TimePickerColors timePickerColors2;
        boolean z2;
        int i4;
        boolean z3;
        final Modifier modifier3;
        final TimePickerColors timePickerColors3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int i5;
        TimePickerColors timePickerColorsColors;
        Modifier modifier5;
        Object objRememberedValue;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM2388constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        Composer composerStartRestartGroup = composer.startRestartGroup(1432307537);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(analogTimePickerState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i6 = i2 & 2;
        if (i6 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) == 0) {
                    timePickerColors2 = timePickerColors;
                    int i7 = composerStartRestartGroup.changed(timePickerColors2) ? 256 : 128;
                    i3 |= i7;
                } else {
                    timePickerColors2 = timePickerColors;
                }
                i3 |= i7;
            } else {
                timePickerColors2 = timePickerColors;
            }
            if ((i2 & 8) != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i4 = 2048;
                    } else {
                        i4 = 1024;
                    }
                    i3 |= i4;
                }
                if ((i3 & 1171) != 1170) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i6 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 4) != 0) {
                            modifier5 = modifier4;
                            i5 = i3 & (-897);
                            timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            Modifier modifier6 = modifier4;
                            i5 = i3;
                            timePickerColorsColors = timePickerColors2;
                            modifier5 = modifier6;
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        i5 = i3;
                        timePickerColorsColors = timePickerColors2;
                        modifier5 = modifier2;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1432307537, i5, -1, "androidx.compose.material3.HorizontalTimePicker (TimePicker.kt:980)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: lfe
                            public final Object invoke(Object obj) {
                                return TimePickerKt.l((SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null);
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 48);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierSemantics$default);
                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                    constructor = companion.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    HorizontalClockDisplay(analogTimePickerState, timePickerColorsColors, composerStartRestartGroup, (i5 & 14) | ((i5 >> 3) & 112));
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion2, ClockDisplayBottomMargin), composerStartRestartGroup, 6);
                    ClockFace(companion2.then(new ClockFaceSizeModifier()), analogTimePickerState, timePickerColorsColors, z2, composerStartRestartGroup, ((i5 << 3) & 112) | (i5 & 896) | (i5 & V4Signature.MAX_SIGNING_INFOS_SIZE));
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    timePickerColors3 = timePickerColorsColors;
                    modifier3 = modifier5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    timePickerColors3 = timePickerColors2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: mfe
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerKt.I(analogTimePickerState, modifier3, timePickerColors3, z, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i3 & 1171) != 1170) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        modifier5 = modifier4;
                        i5 = i3 & (-897);
                        timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        Modifier modifier7 = modifier4;
                        i5 = i3;
                        timePickerColorsColors = timePickerColors2;
                        modifier5 = modifier7;
                    }
                } else {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        modifier5 = modifier4;
                        i5 = i3 & (-897);
                        timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        Modifier modifier8 = modifier4;
                        i5 = i3;
                        timePickerColorsColors = timePickerColors2;
                        modifier5 = modifier8;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1432307537, i5, -1, "androidx.compose.material3.HorizontalTimePicker (TimePicker.kt:980)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: lfe
                        public final Object invoke(Object obj) {
                            return TimePickerKt.l((SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null);
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 48);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierSemantics$default2);
                ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                constructor = companion3.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy2, companion3.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier2, companion3.getSetModifier());
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                HorizontalClockDisplay(analogTimePickerState, timePickerColorsColors, composerStartRestartGroup, (i5 & 14) | ((i5 >> 3) & 112));
                Modifier.Companion companion4 = Modifier.INSTANCE;
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion4, ClockDisplayBottomMargin), composerStartRestartGroup, 6);
                ClockFace(companion4.then(new ClockFaceSizeModifier()), analogTimePickerState, timePickerColorsColors, z2, composerStartRestartGroup, ((i5 << 3) & 112) | (i5 & 896) | (i5 & V4Signature.MAX_SIGNING_INFOS_SIZE));
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                timePickerColors3 = timePickerColorsColors;
                modifier3 = modifier5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                timePickerColors3 = timePickerColors2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: mfe
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerKt.I(analogTimePickerState, modifier3, timePickerColors3, z, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                timePickerColors2 = timePickerColors;
                if (composerStartRestartGroup.changed(timePickerColors2)) {
                }
                i3 |= i7;
            } else {
                timePickerColors2 = timePickerColors;
            }
            i3 |= i7;
        } else {
            timePickerColors2 = timePickerColors;
        }
        if ((i2 & 8) != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i4 = 2048;
                } else {
                    i4 = 1024;
                }
                i3 |= i4;
            }
            if ((i3 & 1171) != 1170) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        modifier5 = modifier4;
                        i5 = i3 & (-897);
                        timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        Modifier modifier9 = modifier4;
                        i5 = i3;
                        timePickerColorsColors = timePickerColors2;
                        modifier5 = modifier9;
                    }
                } else {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        modifier5 = modifier4;
                        i5 = i3 & (-897);
                        timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        Modifier modifier10 = modifier4;
                        i5 = i3;
                        timePickerColorsColors = timePickerColors2;
                        modifier5 = modifier10;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1432307537, i5, -1, "androidx.compose.material3.HorizontalTimePicker (TimePicker.kt:980)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: lfe
                        public final Object invoke(Object obj) {
                            return TimePickerKt.l((SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifierSemantics$default3 = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null);
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 48);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierSemantics$default3);
                ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
                constructor = companion5.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy3, companion5.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap3, companion5.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion5.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier3, companion5.getSetModifier());
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                HorizontalClockDisplay(analogTimePickerState, timePickerColorsColors, composerStartRestartGroup, (i5 & 14) | ((i5 >> 3) & 112));
                Modifier.Companion companion6 = Modifier.INSTANCE;
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion6, ClockDisplayBottomMargin), composerStartRestartGroup, 6);
                ClockFace(companion6.then(new ClockFaceSizeModifier()), analogTimePickerState, timePickerColorsColors, z2, composerStartRestartGroup, ((i5 << 3) & 112) | (i5 & 896) | (i5 & V4Signature.MAX_SIGNING_INFOS_SIZE));
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                timePickerColors3 = timePickerColorsColors;
                modifier3 = modifier5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                timePickerColors3 = timePickerColors2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: mfe
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerKt.I(analogTimePickerState, modifier3, timePickerColors3, z, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i3 & 1171) != 1170) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i6 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    modifier5 = modifier4;
                    i5 = i3 & (-897);
                    timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                } else {
                    Modifier modifier11 = modifier4;
                    i5 = i3;
                    timePickerColorsColors = timePickerColors2;
                    modifier5 = modifier11;
                }
            } else {
                if (i6 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    modifier5 = modifier4;
                    i5 = i3 & (-897);
                    timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                } else {
                    Modifier modifier12 = modifier4;
                    i5 = i3;
                    timePickerColorsColors = timePickerColors2;
                    modifier5 = modifier12;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1432307537, i5, -1, "androidx.compose.material3.HorizontalTimePicker (TimePicker.kt:980)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: lfe
                    public final Object invoke(Object obj) {
                        return TimePickerKt.l((SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics$default4 = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null);
            MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 48);
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierSemantics$default4);
            ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
            constructor = companion7.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy4, companion7.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap4, companion7.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion7.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting()) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier4, companion7.getSetModifier());
            RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
            HorizontalClockDisplay(analogTimePickerState, timePickerColorsColors, composerStartRestartGroup, (i5 & 14) | ((i5 >> 3) & 112));
            Modifier.Companion companion8 = Modifier.INSTANCE;
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion8, ClockDisplayBottomMargin), composerStartRestartGroup, 6);
            ClockFace(companion8.then(new ClockFaceSizeModifier()), analogTimePickerState, timePickerColorsColors, z2, composerStartRestartGroup, ((i5 << 3) & 112) | (i5 & 896) | (i5 & V4Signature.MAX_SIGNING_INFOS_SIZE));
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            timePickerColors3 = timePickerColorsColors;
            modifier3 = modifier5;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            timePickerColors3 = timePickerColors2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: mfe
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.I(analogTimePickerState, modifier3, timePickerColors3, z, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit I(AnalogTimePickerState analogTimePickerState, Modifier modifier, TimePickerColors timePickerColors, boolean z, int i, int i2, Composer composer, int i3) {
        HorizontalTimePicker(analogTimePickerState, modifier, timePickerColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit J(AnalogTimePickerState analogTimePickerState, Modifier modifier, TimePickerColors timePickerColors, boolean z, int i, int i2, Composer composer, int i3) {
        VerticalTimePicker(analogTimePickerState, modifier, timePickerColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit K(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        VerticalPeriodToggle(modifier, timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit L(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return Unit.INSTANCE;
    }

    private static final void PeriodToggleImpl(final Modifier modifier, final TimePickerState timePickerState, final TimePickerColors timePickerColors, final MeasurePolicy measurePolicy, final Shape shape, final Shape shape2, Composer composer, final int i) {
        int i2;
        Shape shape3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1374241901);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composerStartRestartGroup.changed(timePickerState) : composerStartRestartGroup.changedInstance(timePickerState) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(timePickerColors) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(measurePolicy) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(shape) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            shape3 = shape2;
            i2 |= composerStartRestartGroup.changed(shape3) ? 131072 : 65536;
        } else {
            shape3 = shape2;
        }
        if (composerStartRestartGroup.shouldExecute((74899 & i2) != 74898, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1374241901, i2, -1, "androidx.compose.material3.PeriodToggleImpl (TimePicker.kt:1301)");
            }
            TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
            BorderStroke borderStrokeM12BorderStrokecXLIe8U = BorderStrokeKt.m12BorderStrokecXLIe8U(timePickerTokens.m2202getPeriodSelectorOutlineWidthD9Ej5fM(), timePickerColors.getPeriodSelectorBorderColor());
            CornerBasedShape value = ShapesKt.getValue(timePickerTokens.getPeriodSelectorContainerShape(), composerStartRestartGroup, 6);
            value.getClass();
            CornerBasedShape cornerBasedShape = value;
            Strings.Companion companion = Strings.INSTANCE;
            final String strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_time_picker_period_toggle_description), composerStartRestartGroup, 0);
            boolean zChanged = composerStartRestartGroup.changed(strM1471getString2EP1pXo);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: cfe
                    public final Object invoke(Object obj) {
                        return TimePickerKt.B(strM1471getString2EP1pXo, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierBorder = BorderKt.border(SelectableGroupKt.selectableGroup(SemanticsModifierKt.semantics$default(modifier, false, (Function1) objRememberedValue, 1, null)), borderStrokeM12BorderStrokecXLIe8U, cornerBasedShape);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierBorder);
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            boolean z = !isPm(timePickerState);
            int i3 = i2 & 112;
            boolean z2 = i3 == 32 || ((i2 & 64) != 0 && composerStartRestartGroup.changedInstance(timePickerState));
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: dfe
                    public final Object invoke() {
                        return TimePickerKt.b(timePickerState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposableSingletons$TimePickerKt composableSingletons$TimePickerKt = ComposableSingletons$TimePickerKt.INSTANCE;
            Function3<RowScope, Composer, Integer, Unit> lambda$1425358052$material3 = composableSingletons$TimePickerKt.getLambda$1425358052$material3();
            int i4 = (i2 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE;
            ToggleItem(z, shape, (Function0) objRememberedValue2, timePickerColors, lambda$1425358052$material3, composerStartRestartGroup, ((i2 >> 9) & 112) | 24576 | i4);
            SpacerKt.Spacer(BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(LayoutIdKt.layoutId(Modifier.INSTANCE, "Spacer"), SeparatorZIndex), 0.0f, 1, (Object) null), timePickerColors.getPeriodSelectorBorderColor(), (Shape) null, 2, (Object) null), composerStartRestartGroup, 0);
            boolean z3 = false;
            boolean zIsPm = isPm(timePickerState);
            if (i3 == 32 || ((i2 & 64) != 0 && composerStartRestartGroup.changedInstance(timePickerState))) {
                z3 = true;
            }
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: ffe
                    public final Object invoke() {
                        return TimePickerKt.F(timePickerState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ToggleItem(zIsPm, shape3, (Function0) objRememberedValue3, timePickerColors, composableSingletons$TimePickerKt.m328getLambda$1179219109$material3(), composerStartRestartGroup, ((i2 >> 12) & 112) | 24576 | i4);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfe
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.h(modifier, timePickerState, timePickerColors, measurePolicy, shape, shape2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void TimeInput(final TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-760850373);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(timePickerState) : composerStartRestartGroup.changedInstance(timePickerState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= ((i2 & 4) == 0 && composerStartRestartGroup.changed(timePickerColors)) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    timePickerColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-760850373, i3, -1, "androidx.compose.material3.TimeInput (TimePicker.kt:274)");
            }
            TimeInputImpl(modifier, timePickerColors, timePickerState, composerStartRestartGroup, ((i3 >> 3) & 126) | ((i3 << 6) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        final Modifier modifier2 = modifier;
        final TimePickerColors timePickerColors2 = timePickerColors;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: afe
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.r(timePickerState, modifier2, timePickerColors2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void TimeInputImpl(Modifier modifier, TimePickerColors timePickerColors, TimePickerState timePickerState, Composer composer, final int i) {
        int i2;
        final Modifier modifier2;
        final TimePickerColors timePickerColors2;
        Ref ref;
        MutableState mutableState;
        MutableState mutableState2;
        final TimePickerState timePickerState2 = timePickerState;
        Composer composerStartRestartGroup = composer.startRestartGroup(-475657989);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(timePickerColors) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= (i & 512) == 0 ? composerStartRestartGroup.changed(timePickerState2) : composerStartRestartGroup.changedInstance(timePickerState2) ? 256 : 128;
        }
        int i3 = i2;
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-475657989, i3, -1, "androidx.compose.material3.TimeInputImpl (TimePicker.kt:997)");
            }
            Object[] objArr = new Object[0];
            TextFieldValue.Companion companion = TextFieldValue.INSTANCE;
            Saver<TextFieldValue, Object> saver = companion.getSaver();
            int i4 = i3 & 896;
            boolean z = i4 == 256 || ((i3 & 512) != 0 && composerStartRestartGroup.changedInstance(timePickerState2));
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: tee
                    public final Object invoke() {
                        return TimePickerKt.f(timePickerState2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableStateRememberSaveable = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (Function0) objRememberedValue, composerStartRestartGroup, 0);
            Object[] objArr2 = new Object[0];
            Saver<TextFieldValue, Object> saver2 = companion.getSaver();
            boolean z2 = i4 == 256 || ((i3 & 512) != 0 && composerStartRestartGroup.changedInstance(timePickerState2));
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: efe
                    public final Object invoke() {
                        return TimePickerKt.j(timePickerState2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableState mutableStateRememberSaveable2 = RememberSaveableKt.rememberSaveable(objArr2, (Saver) saver2, (Function0) objRememberedValue2, composerStartRestartGroup, 0);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue3 == companion2.getEmpty()) {
                objRememberedValue3 = new Ref();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Ref ref2 = (Ref) objRememberedValue3;
            Integer numValueOf = Integer.valueOf(timePickerState2.getHour());
            Integer numValueOf2 = Integer.valueOf(timePickerState2.getMinute());
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(ref2) | composerStartRestartGroup.changed(mutableStateRememberSaveable) | (i4 == 256 || ((i3 & 512) != 0 && composerStartRestartGroup.changedInstance(timePickerState2))) | composerStartRestartGroup.changed(mutableStateRememberSaveable2);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue4 == companion2.getEmpty()) {
                TimePickerKt$TimeInputImpl$1$1 timePickerKt$TimeInputImpl$1$1 = new TimePickerKt$TimeInputImpl$1$1(ref2, timePickerState, mutableStateRememberSaveable, mutableStateRememberSaveable2, null);
                ref = ref2;
                mutableState = mutableStateRememberSaveable;
                mutableState2 = mutableStateRememberSaveable2;
                composerStartRestartGroup.updateRememberedValue(timePickerKt$TimeInputImpl$1$1);
                objRememberedValue4 = timePickerKt$TimeInputImpl$1$1;
            } else {
                mutableState = mutableStateRememberSaveable;
                mutableState2 = mutableStateRememberSaveable2;
                ref = ref2;
            }
            EffectsKt.LaunchedEffect(numValueOf, numValueOf2, (Function2) objRememberedValue4, composerStartRestartGroup, 0);
            Modifier modifier3 = PaddingKt.padding-qDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, TimeInputBottomPadding, 7, (Object) null);
            modifier2 = modifier;
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), companion3.getTop(), composerStartRestartGroup, 48);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion4.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion4.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion4.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion4.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion4.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            TimeInputTokens timeInputTokens = TimeInputTokens.INSTANCE;
            timePickerColors2 = timePickerColors;
            timePickerState2 = timePickerState;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(timeInputTokens.getTimeFieldLabelTextFont(), composerStartRestartGroup, 6), timePickerColors.m1120timeSelectorContentColorvNxB06k$material3(true), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744446, null)), CompositionLocalsKt.getLocalLayoutDirection().provides(LayoutDirection.Ltr)}, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1306700887, true, new TimePickerKt$TimeInputImpl$2$1(mutableState, timePickerState2, ref, timePickerColors2, mutableState2), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
            if (timePickerState2.getIs24hour()) {
                composerStartRestartGroup.startReplaceGroup(-1381607893);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1381942321);
                Modifier.Companion companion5 = Modifier.INSTANCE;
                Modifier modifier4 = PaddingKt.padding-qDBjuR0$default(companion5, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier4);
                Function0<ComposeUiNode> constructor2 = companion4.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion4.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion4.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion4.getSetCompositeKeyHash();
                if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion4.getSetModifier());
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                VerticalPeriodToggle(SizeKt.size-VpY3zN4(companion5, timeInputTokens.m2190getPeriodSelectorContainerWidthD9Ej5fM(), timeInputTokens.m2189getPeriodSelectorContainerHeightD9Ej5fM()), timePickerState2, timePickerColors2, composerStartRestartGroup, ((i3 >> 3) & 112) | 6 | ((i3 << 3) & 896));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            modifier2 = modifier;
            timePickerColors2 = timePickerColors;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: nfe
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.i(modifier2, timePickerColors2, timePickerState2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$hourTextValue(TimePickerState timePickerState) {
        return new TextFieldValue(CalendarLocale_jvmKt.toLocalString$default(getHourForDisplay(timePickerState), 2, 0, false, null, 14, null), 0L, (TextRange) null, 6, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$18(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$22(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$minuteTextValue(TimePickerState timePickerState) {
        return new TextFieldValue(CalendarLocale_jvmKt.toLocalString$default(timePickerState.getMinute(), 2, 0, false, null, 14, null), 0L, (TextRange) null, 6, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0150  */
    /* JADX WARN: Code duplicated, block: B:107:0x015d  */
    /* JADX WARN: Code duplicated, block: B:110:0x0167  */
    /* JADX WARN: Code duplicated, block: B:112:0x016d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0187  */
    /* JADX WARN: Code duplicated, block: B:116:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:119:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:121:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:124:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:126:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x004d  */
    /* JADX WARN: Code duplicated, block: B:31:0x0051  */
    /* JADX WARN: Code duplicated, block: B:33:0x0059  */
    /* JADX WARN: Code duplicated, block: B:34:0x005c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0062  */
    /* JADX WARN: Code duplicated, block: B:40:0x0068  */
    /* JADX WARN: Code duplicated, block: B:42:0x006c  */
    /* JADX WARN: Code duplicated, block: B:44:0x0074  */
    /* JADX WARN: Code duplicated, block: B:45:0x0077  */
    /* JADX WARN: Code duplicated, block: B:48:0x007d  */
    /* JADX WARN: Code duplicated, block: B:51:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x0088  */
    /* JADX WARN: Code duplicated, block: B:55:0x0091  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:69:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:73:0x00be  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:77:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:84:0x0100  */
    /* JADX WARN: Code duplicated, block: B:87:0x010e  */
    /* JADX WARN: Code duplicated, block: B:93:0x011b  */
    /* JADX WARN: Code duplicated, block: B:96:0x0122  */
    /* JADX WARN: Code duplicated, block: B:98:0x0128  */
    /* JADX INFO: renamed from: TimePicker-mT9BvqQ, reason: not valid java name */
    public static final void m1137TimePickermT9BvqQ(final TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, int i, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        TimePickerColors timePickerColors2;
        int i5;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final TimePickerColors timePickerColors3;
        final int i6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        TimePickerColors timePickerColorsColors;
        int i7;
        TimePickerColors timePickerColors4;
        int iM1122layoutTypesDNSZnc;
        State<Boolean> stateRememberAccessibilityServiceState;
        Object objRememberedValue;
        Composer.Companion companion;
        Ref ref;
        int i8;
        boolean z2;
        Object objRememberedValue2;
        AnalogTimePickerState analogTimePickerState;
        boolean z3;
        boolean z4;
        Object objRememberedValue3;
        TimePickerColors timePickerColors5;
        Modifier modifier5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-619286452);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = ((i2 & 8) == 0 ? composerStartRestartGroup.changed(timePickerState) : composerStartRestartGroup.changedInstance(timePickerState) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i9 = i3 & 2;
        if (i9 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i3 & 4) == 0) {
                    timePickerColors2 = timePickerColors;
                    int i10 = composerStartRestartGroup.changed(timePickerColors2) ? 256 : 128;
                    i4 |= i10;
                } else {
                    timePickerColors2 = timePickerColors;
                }
                i4 |= i10;
            } else {
                timePickerColors2 = timePickerColors;
            }
            if ((i2 & 3072) == 0) {
                if ((i3 & 8) == 0) {
                    i5 = i;
                    int i11 = composerStartRestartGroup.changed(i5) ? 2048 : 1024;
                    i4 |= i11;
                } else {
                    i5 = i;
                }
                i4 |= i11;
            } else {
                i5 = i;
            }
            if ((i4 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i9 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        timePickerColorsColors = timePickerColors2;
                    }
                    if ((i3 & 8) != 0) {
                        i7 = i4 & (-7169);
                        timePickerColors4 = timePickerColorsColors;
                        iM1122layoutTypesDNSZnc = TimePickerDefaults.INSTANCE.m1122layoutTypesDNSZnc(composerStartRestartGroup, 6);
                    } else {
                        i7 = i4;
                        timePickerColors4 = timePickerColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-619286452, i7, -1, "androidx.compose.material3.TimePicker (TimePicker.kt:224)");
                    }
                    stateRememberAccessibilityServiceState = AccessibilityServiceStateProvider_androidKt.rememberAccessibilityServiceState(false, false, false, composerStartRestartGroup, 0, 7);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = new Ref();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ref = (Ref) objRememberedValue;
                    i8 = i7 & 14;
                    if (i8 != 4 || ((i7 & 8) != 0 && composerStartRestartGroup.changed(timePickerState))) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z2 || objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new AnalogTimePickerState(timePickerState, ref);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    analogTimePickerState = (AnalogTimePickerState) objRememberedValue2;
                    Integer numValueOf = Integer.valueOf(timePickerState.getHour());
                    Integer numValueOf2 = Integer.valueOf(timePickerState.getMinute());
                    boolean zChangedInstance = composerStartRestartGroup.changedInstance(ref) | composerStartRestartGroup.changedInstance(analogTimePickerState);
                    if (i8 != 4 || ((i7 & 8) != 0 && composerStartRestartGroup.changedInstance(timePickerState))) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = zChangedInstance | z3;
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z4 || objRememberedValue3 == companion.getEmpty()) {
                        objRememberedValue3 = new TimePickerKt$TimePicker$1$1(ref, analogTimePickerState, timePickerState, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    EffectsKt.LaunchedEffect(numValueOf, numValueOf2, (Function2) objRememberedValue3, composerStartRestartGroup, 0);
                    if (TimePickerLayoutType.m1154equalsimpl0(iM1122layoutTypesDNSZnc, TimePickerLayoutType.INSTANCE.m1159getVerticalQJTpgSE())) {
                        composerStartRestartGroup.startReplaceGroup(2017551219);
                        timePickerColors5 = timePickerColors4;
                        modifier5 = modifier4;
                        VerticalTimePicker(analogTimePickerState, modifier5, timePickerColors5, !TimePicker_mT9BvqQ$lambda$0(stateRememberAccessibilityServiceState), composerStartRestartGroup, i7 & PointerIconCompat.TYPE_TEXT, 0);
                        composer2 = composerStartRestartGroup;
                        composer2.endReplaceGroup();
                    } else {
                        timePickerColors5 = timePickerColors4;
                        modifier5 = modifier4;
                        composerStartRestartGroup.startReplaceGroup(2017750673);
                        HorizontalTimePicker(analogTimePickerState, modifier5, timePickerColors5, !TimePicker_mT9BvqQ$lambda$0(stateRememberAccessibilityServiceState), composerStartRestartGroup, i7 & PointerIconCompat.TYPE_TEXT, 0);
                        composer2 = composerStartRestartGroup;
                        composer2.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    timePickerColors3 = timePickerColors5;
                    i6 = iM1122layoutTypesDNSZnc;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                    }
                    i7 = i4;
                    modifier4 = modifier2;
                    timePickerColors4 = timePickerColors2;
                }
                iM1122layoutTypesDNSZnc = i5;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-619286452, i7, -1, "androidx.compose.material3.TimePicker (TimePicker.kt:224)");
                }
                stateRememberAccessibilityServiceState = AccessibilityServiceStateProvider_androidKt.rememberAccessibilityServiceState(false, false, false, composerStartRestartGroup, 0, 7);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = new Ref();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ref = (Ref) objRememberedValue;
                i8 = i7 & 14;
                if (i8 != 4) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue2 = new AnalogTimePickerState(timePickerState, ref);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new AnalogTimePickerState(timePickerState, ref);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                analogTimePickerState = (AnalogTimePickerState) objRememberedValue2;
                Integer numValueOf3 = Integer.valueOf(timePickerState.getHour());
                Integer numValueOf4 = Integer.valueOf(timePickerState.getMinute());
                boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(ref) | composerStartRestartGroup.changedInstance(analogTimePickerState);
                if (i8 != 4) {
                    z3 = true;
                } else {
                    z3 = true;
                }
                z4 = zChangedInstance2 | z3;
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z4) {
                    objRememberedValue3 = new TimePickerKt$TimePicker$1$1(ref, analogTimePickerState, timePickerState, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new TimePickerKt$TimePicker$1$1(ref, analogTimePickerState, timePickerState, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                EffectsKt.LaunchedEffect(numValueOf3, numValueOf4, (Function2) objRememberedValue3, composerStartRestartGroup, 0);
                if (TimePickerLayoutType.m1154equalsimpl0(iM1122layoutTypesDNSZnc, TimePickerLayoutType.INSTANCE.m1159getVerticalQJTpgSE())) {
                    composerStartRestartGroup.startReplaceGroup(2017551219);
                    timePickerColors5 = timePickerColors4;
                    modifier5 = modifier4;
                    VerticalTimePicker(analogTimePickerState, modifier5, timePickerColors5, !TimePicker_mT9BvqQ$lambda$0(stateRememberAccessibilityServiceState), composerStartRestartGroup, i7 & PointerIconCompat.TYPE_TEXT, 0);
                    composer2 = composerStartRestartGroup;
                    composer2.endReplaceGroup();
                } else {
                    timePickerColors5 = timePickerColors4;
                    modifier5 = modifier4;
                    composerStartRestartGroup.startReplaceGroup(2017750673);
                    HorizontalTimePicker(analogTimePickerState, modifier5, timePickerColors5, !TimePicker_mT9BvqQ$lambda$0(stateRememberAccessibilityServiceState), composerStartRestartGroup, i7 & PointerIconCompat.TYPE_TEXT, 0);
                    composer2 = composerStartRestartGroup;
                    composer2.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                timePickerColors3 = timePickerColors5;
                i6 = iM1122layoutTypesDNSZnc;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                timePickerColors3 = timePickerColors2;
                i6 = i5;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerKt.p(timePickerState, modifier3, timePickerColors3, i6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i3 & 4) == 0) {
                timePickerColors2 = timePickerColors;
                if (composerStartRestartGroup.changed(timePickerColors2)) {
                }
                i4 |= i10;
            } else {
                timePickerColors2 = timePickerColors;
            }
            i4 |= i10;
        } else {
            timePickerColors2 = timePickerColors;
        }
        if ((i2 & 3072) == 0) {
            if ((i3 & 8) == 0) {
                i5 = i;
                if (composerStartRestartGroup.changed(i5)) {
                }
                i4 |= i11;
            } else {
                i5 = i;
            }
            i4 |= i11;
        } else {
            i5 = i;
        }
        if ((i4 & 1171) != 1170) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i4 &= -897;
                } else {
                    timePickerColorsColors = timePickerColors2;
                }
                if ((i3 & 8) != 0) {
                    i7 = i4 & (-7169);
                    timePickerColors4 = timePickerColorsColors;
                    iM1122layoutTypesDNSZnc = TimePickerDefaults.INSTANCE.m1122layoutTypesDNSZnc(composerStartRestartGroup, 6);
                } else {
                    i7 = i4;
                    timePickerColors4 = timePickerColorsColors;
                    iM1122layoutTypesDNSZnc = i5;
                }
            } else {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i4 &= -897;
                } else {
                    timePickerColorsColors = timePickerColors2;
                }
                if ((i3 & 8) != 0) {
                    i7 = i4 & (-7169);
                    timePickerColors4 = timePickerColorsColors;
                    iM1122layoutTypesDNSZnc = TimePickerDefaults.INSTANCE.m1122layoutTypesDNSZnc(composerStartRestartGroup, 6);
                } else {
                    i7 = i4;
                    timePickerColors4 = timePickerColorsColors;
                    iM1122layoutTypesDNSZnc = i5;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-619286452, i7, -1, "androidx.compose.material3.TimePicker (TimePicker.kt:224)");
            }
            stateRememberAccessibilityServiceState = AccessibilityServiceStateProvider_androidKt.rememberAccessibilityServiceState(false, false, false, composerStartRestartGroup, 0, 7);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Ref();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ref = (Ref) objRememberedValue;
            i8 = i7 & 14;
            if (i8 != 4) {
                z2 = true;
            } else {
                z2 = true;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z2) {
                objRememberedValue2 = new AnalogTimePickerState(timePickerState, ref);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new AnalogTimePickerState(timePickerState, ref);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            analogTimePickerState = (AnalogTimePickerState) objRememberedValue2;
            Integer numValueOf5 = Integer.valueOf(timePickerState.getHour());
            Integer numValueOf6 = Integer.valueOf(timePickerState.getMinute());
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(ref) | composerStartRestartGroup.changedInstance(analogTimePickerState);
            if (i8 != 4) {
                z3 = true;
            } else {
                z3 = true;
            }
            z4 = zChangedInstance3 | z3;
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z4) {
                objRememberedValue3 = new TimePickerKt$TimePicker$1$1(ref, analogTimePickerState, timePickerState, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = new TimePickerKt$TimePicker$1$1(ref, analogTimePickerState, timePickerState, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.LaunchedEffect(numValueOf5, numValueOf6, (Function2) objRememberedValue3, composerStartRestartGroup, 0);
            if (TimePickerLayoutType.m1154equalsimpl0(iM1122layoutTypesDNSZnc, TimePickerLayoutType.INSTANCE.m1159getVerticalQJTpgSE())) {
                composerStartRestartGroup.startReplaceGroup(2017551219);
                timePickerColors5 = timePickerColors4;
                modifier5 = modifier4;
                VerticalTimePicker(analogTimePickerState, modifier5, timePickerColors5, !TimePicker_mT9BvqQ$lambda$0(stateRememberAccessibilityServiceState), composerStartRestartGroup, i7 & PointerIconCompat.TYPE_TEXT, 0);
                composer2 = composerStartRestartGroup;
                composer2.endReplaceGroup();
            } else {
                timePickerColors5 = timePickerColors4;
                modifier5 = modifier4;
                composerStartRestartGroup.startReplaceGroup(2017750673);
                HorizontalTimePicker(analogTimePickerState, modifier5, timePickerColors5, !TimePicker_mT9BvqQ$lambda$0(stateRememberAccessibilityServiceState), composerStartRestartGroup, i7 & PointerIconCompat.TYPE_TEXT, 0);
                composer2 = composerStartRestartGroup;
                composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            timePickerColors3 = timePickerColors5;
            i6 = iM1122layoutTypesDNSZnc;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            timePickerColors3 = timePickerColors2;
            i6 = i5;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.p(timePickerState, modifier3, timePickerColors3, i6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final TimePickerState TimePickerState(int i, int i2, boolean z) {
        return new TimePickerStateImpl(i, i2, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r118v1 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.visitors.ModVisitor.anonymousCallArgMod(ModVisitor.java:535)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.dex.visitors.ModVisitor.processAnonymousConstructor(ModVisitor.java:528)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:111)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    /* JADX INFO: renamed from: TimePickerTextField-1vLObsk, reason: not valid java name */
    public static final void m1138TimePickerTextField1vLObsk(androidx.compose.ui.Modifier r113, final androidx.compose.ui.text.input.TextFieldValue r114, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r115, androidx.compose.material3.TimePickerState r116, int r117, androidx.compose.foundation.text.KeyboardOptions r118, androidx.compose.foundation.text.KeyboardActions r119, androidx.compose.material3.TimePickerColors r120, androidx.compose.runtime.Composer r121, int r122, int r123) {
        /*
            Method dump skipped, instruction units count: 1556
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.m1138TimePickerTextField1vLObsk(androidx.compose.ui.Modifier, androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.material3.TimePickerState, int, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, androidx.compose.material3.TimePickerColors, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean TimePicker_mT9BvqQ$lambda$0(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: TimeSelector-SAnMeKU, reason: not valid java name */
    public static final void m1139TimeSelectorSAnMeKU(final Modifier modifier, final int i, final TimePickerState timePickerState, final int i2, final TimePickerColors timePickerColors, Composer composer, final int i3) {
        int i4;
        Composer composer2;
        int iM1392constructorimpl;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1148055889);
        if ((i3 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i3;
        } else {
            i4 = i3;
        }
        if ((i3 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 32 : 16;
        }
        if ((i3 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= (i3 & 512) == 0 ? composerStartRestartGroup.changed(timePickerState) : composerStartRestartGroup.changedInstance(timePickerState) ? 256 : 128;
        }
        if ((i3 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changed(i2) ? 2048 : 1024;
        }
        if ((i3 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changed(timePickerColors) ? 16384 : 8192;
        }
        if (composerStartRestartGroup.shouldExecute((i4 & 9363) != 9362, i4 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1148055889, i4, -1, "androidx.compose.material3.TimeSelector (TimePicker.kt:1403)");
            }
            boolean zM1163equalsimpl0 = TimePickerSelectionMode.m1163equalsimpl0(timePickerState.mo74getSelectionyecRtBI(), i2);
            if (TimePickerSelectionMode.m1163equalsimpl0(i2, TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI())) {
                Strings.Companion companion = Strings.INSTANCE;
                iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_hour_selection);
            } else {
                Strings.Companion companion2 = Strings.INSTANCE;
                iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_minute_selection);
            }
            final String strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(iM1392constructorimpl, composerStartRestartGroup, 0);
            long jM1119timeSelectorContainerColorvNxB06k$material3 = timePickerColors.m1119timeSelectorContainerColorvNxB06k$material3(zM1163equalsimpl0);
            long jM1120timeSelectorContentColorvNxB06k$material3 = timePickerColors.m1120timeSelectorContentColorvNxB06k$material3(zM1163equalsimpl0);
            boolean zChanged = composerStartRestartGroup.changed(strM1471getString2EP1pXo);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: tfe
                    public final Object invoke(Object obj) {
                        return TimePickerKt.A(strM1471getString2EP1pXo, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (Function1) objRememberedValue);
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorContainerShape(), composerStartRestartGroup, 6);
            boolean z = ((i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) | ((i4 & 896) == 256 || ((i4 & 512) != 0 && composerStartRestartGroup.changedInstance(timePickerState)));
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: jee
                    public final Object invoke() {
                        return TimePickerKt.g(i2, timePickerState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composer2 = composerStartRestartGroup;
            SurfaceKt.m955Surfaced85dljk(zM1163equalsimpl0, (Function0<Unit>) objRememberedValue2, modifierSemantics, false, value, jM1119timeSelectorContainerColorvNxB06k$material3, 0L, 0.0f, 0.0f, (BorderStroke) null, (MutableInteractionSource) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1477282471, true, new TimePickerKt$TimeSelector$3(i2, timePickerState, i, jM1120timeSelectorContentColorvNxB06k$material3), composerStartRestartGroup, 54), composer2, 0, 48, 1992);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.v(modifier, i, timePickerState, i2, timePickerColors, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void ToggleItem(final boolean z, final Shape shape, final Function0<Unit> function0, final TimePickerColors timePickerColors, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1523811083);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(shape) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(timePickerColors) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 16384 : 8192;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 9363) != 9362, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1523811083, i2, -1, "androidx.compose.material3.ToggleItem (TimePicker.kt:1359)");
            }
            long jM1118periodSelectorContentColorvNxB06k$material3 = timePickerColors.m1118periodSelectorContentColorvNxB06k$material3(z);
            long jM1117periodSelectorContainerColorvNxB06k$material3 = timePickerColors.m1117periodSelectorContainerColorvNxB06k$material3(z);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(Modifier.INSTANCE, z ? 0.0f : 1.0f), 0.0f, 1, (Object) null);
            boolean z2 = (i2 & 14) == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: yee
                    public final Object invoke(Object obj) {
                        return TimePickerKt.z(z, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ButtonKt.TextButton(function0, SemanticsModifierKt.semantics$default(modifierFillMaxSize$default, false, (Function1) objRememberedValue, 1, null), false, shape, ButtonDefaults.INSTANCE.m147textButtonColorsro_MJ88(jM1117periodSelectorContainerColorvNxB06k$material3, jM1118periodSelectorContentColorvNxB06k$material3, 0L, 0L, composerStartRestartGroup, 24576, 12), null, null, PaddingKt.PaddingValues-0680j_4(Dp.m6022constructorimpl(0.0f)), null, function3, composerStartRestartGroup, ((i2 >> 6) & 14) | 12582912 | ((i2 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i2 << 15) & 1879048192), 356);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.u(z, shape, function0, timePickerColors, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void VerticalClockDisplay(final TimePickerState timePickerState, final TimePickerColors timePickerColors, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2054675515);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(timePickerState) : composerStartRestartGroup.changedInstance(timePickerState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(timePickerColors) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2054675515, i2, -1, "androidx.compose.material3.VerticalClockDisplay (TimePicker.kt:1153)");
            }
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            Modifier.Companion companion = Modifier.INSTANCE;
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, companion2.getTop(), composerStartRestartGroup, 6);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion3.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ClockDisplayNumbers(timePickerState, timePickerColors, composerStartRestartGroup, i2 & 126);
            if (timePickerState.getIs24hour()) {
                composerStartRestartGroup.startReplaceGroup(1364727499);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1364287361);
                Modifier modifier = PaddingKt.padding-qDBjuR0$default(companion, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion2.getTopStart(), false);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
                Function0<ComposeUiNode> constructor2 = companion3.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
                if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
                int i3 = i2 << 3;
                VerticalPeriodToggle(SizeKt.size-VpY3zN4(companion, timePickerTokens.m2204getPeriodSelectorVerticalContainerWidthD9Ej5fM(), timePickerTokens.m2203getPeriodSelectorVerticalContainerHeightD9Ej5fM()), timePickerState, timePickerColors, composerStartRestartGroup, 6 | (i3 & 112) | (i3 & 896));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.t(timePickerState, timePickerColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void VerticalPeriodToggle(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, Composer composer, final int i) {
        int i2;
        final Modifier modifier2;
        final TimePickerState timePickerState2;
        final TimePickerColors timePickerColors2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1898918107);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composerStartRestartGroup.changed(timePickerState) : composerStartRestartGroup.changedInstance(timePickerState) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(timePickerColors) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1898918107, i2, -1, "androidx.compose.material3.VerticalPeriodToggle (TimePicker.kt:1252)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1.INSTANCE;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            CornerBasedShape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), composerStartRestartGroup, 6);
            value.getClass();
            CornerBasedShape cornerBasedShape = value;
            modifier2 = modifier;
            timePickerState2 = timePickerState;
            timePickerColors2 = timePickerColors;
            PeriodToggleImpl(modifier2, timePickerState2, timePickerColors2, measurePolicy, ShapesKt.top$default(cornerBasedShape, null, 1, null), ShapesKt.bottom$default(cornerBasedShape, null, 1, null), composerStartRestartGroup, (i2 & 14) | 3072 | (i2 & 112) | (i2 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            modifier2 = modifier;
            timePickerState2 = timePickerState;
            timePickerColors2 = timePickerColors;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jfe
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.K(modifier2, timePickerState2, timePickerColors2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0043  */
    /* JADX WARN: Code duplicated, block: B:28:0x0047  */
    /* JADX WARN: Code duplicated, block: B:30:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0052  */
    /* JADX WARN: Code duplicated, block: B:34:0x0058  */
    /* JADX WARN: Code duplicated, block: B:37:0x005e  */
    /* JADX WARN: Code duplicated, block: B:39:0x0063  */
    /* JADX WARN: Code duplicated, block: B:41:0x0067  */
    /* JADX WARN: Code duplicated, block: B:43:0x006f  */
    /* JADX WARN: Code duplicated, block: B:44:0x0072  */
    /* JADX WARN: Code duplicated, block: B:48:0x007d  */
    /* JADX WARN: Code duplicated, block: B:49:0x007f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0088  */
    /* JADX WARN: Code duplicated, block: B:54:0x0090  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:76:0x0112  */
    /* JADX WARN: Code duplicated, block: B:79:0x011e  */
    /* JADX WARN: Code duplicated, block: B:80:0x0122  */
    /* JADX WARN: Code duplicated, block: B:83:0x0141  */
    /* JADX WARN: Code duplicated, block: B:85:0x014f  */
    /* JADX WARN: Code duplicated, block: B:88:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:90:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:93:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:95:? A[RETURN, SYNTHETIC] */
    public static final void VerticalTimePicker(final AnalogTimePickerState analogTimePickerState, Modifier modifier, TimePickerColors timePickerColors, final boolean z, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        TimePickerColors timePickerColors2;
        boolean z2;
        int i4;
        boolean z3;
        final Modifier modifier3;
        final TimePickerColors timePickerColors3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int i5;
        TimePickerColors timePickerColorsColors;
        Modifier modifier5;
        Object objRememberedValue;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM2388constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        Composer composerStartRestartGroup = composer.startRestartGroup(1249591487);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(analogTimePickerState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i6 = i2 & 2;
        if (i6 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) == 0) {
                    timePickerColors2 = timePickerColors;
                    int i7 = composerStartRestartGroup.changed(timePickerColors2) ? 256 : 128;
                    i3 |= i7;
                } else {
                    timePickerColors2 = timePickerColors;
                }
                i3 |= i7;
            } else {
                timePickerColors2 = timePickerColors;
            }
            if ((i2 & 8) != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i4 = 2048;
                    } else {
                        i4 = 1024;
                    }
                    i3 |= i4;
                }
                if ((i3 & 1171) != 1170) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i6 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 4) != 0) {
                            modifier5 = modifier4;
                            i5 = i3 & (-897);
                            timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            Modifier modifier6 = modifier4;
                            i5 = i3;
                            timePickerColorsColors = timePickerColors2;
                            modifier5 = modifier6;
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        i5 = i3;
                        timePickerColorsColors = timePickerColors2;
                        modifier5 = modifier2;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1249591487, i5, -1, "androidx.compose.material3.VerticalTimePicker (TimePicker.kt:957)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: vee
                            public final Object invoke(Object obj) {
                                return TimePickerKt.y((SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null);
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getCenterHorizontally(), composerStartRestartGroup, 48);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierSemantics$default);
                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                    constructor = companion.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    VerticalClockDisplay(analogTimePickerState, timePickerColorsColors, composerStartRestartGroup, (i5 & 14) | ((i5 >> 3) & 112));
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(companion2, ClockDisplayBottomMargin), composerStartRestartGroup, 6);
                    ClockFace(SizeKt.size-3ABfNKs(companion2, TimePickerTokens.INSTANCE.m2195getClockDialContainerSizeD9Ej5fM()), analogTimePickerState, timePickerColorsColors, z2, composerStartRestartGroup, ((i5 << 3) & 112) | 6 | (i5 & 896) | (i5 & V4Signature.MAX_SIGNING_INFOS_SIZE));
                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(companion2, ClockFaceBottomMargin), composerStartRestartGroup, 6);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    timePickerColors3 = timePickerColorsColors;
                    modifier3 = modifier5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    timePickerColors3 = timePickerColors2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: wee
                        public final Object invoke(Object obj, Object obj2) {
                            return TimePickerKt.J(analogTimePickerState, modifier3, timePickerColors3, z, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i3 & 1171) != 1170) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        modifier5 = modifier4;
                        i5 = i3 & (-897);
                        timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        Modifier modifier7 = modifier4;
                        i5 = i3;
                        timePickerColorsColors = timePickerColors2;
                        modifier5 = modifier7;
                    }
                } else {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        modifier5 = modifier4;
                        i5 = i3 & (-897);
                        timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        Modifier modifier8 = modifier4;
                        i5 = i3;
                        timePickerColorsColors = timePickerColors2;
                        modifier5 = modifier8;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1249591487, i5, -1, "androidx.compose.material3.VerticalTimePicker (TimePicker.kt:957)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: vee
                        public final Object invoke(Object obj) {
                            return TimePickerKt.y((SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null);
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getCenterHorizontally(), composerStartRestartGroup, 48);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierSemantics$default2);
                ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                constructor = companion3.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy2, companion3.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier2, companion3.getSetModifier());
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                VerticalClockDisplay(analogTimePickerState, timePickerColorsColors, composerStartRestartGroup, (i5 & 14) | ((i5 >> 3) & 112));
                Modifier.Companion companion4 = Modifier.INSTANCE;
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(companion4, ClockDisplayBottomMargin), composerStartRestartGroup, 6);
                ClockFace(SizeKt.size-3ABfNKs(companion4, TimePickerTokens.INSTANCE.m2195getClockDialContainerSizeD9Ej5fM()), analogTimePickerState, timePickerColorsColors, z2, composerStartRestartGroup, ((i5 << 3) & 112) | 6 | (i5 & 896) | (i5 & V4Signature.MAX_SIGNING_INFOS_SIZE));
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(companion4, ClockFaceBottomMargin), composerStartRestartGroup, 6);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                timePickerColors3 = timePickerColorsColors;
                modifier3 = modifier5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                timePickerColors3 = timePickerColors2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: wee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerKt.J(analogTimePickerState, modifier3, timePickerColors3, z, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                timePickerColors2 = timePickerColors;
                if (composerStartRestartGroup.changed(timePickerColors2)) {
                }
                i3 |= i7;
            } else {
                timePickerColors2 = timePickerColors;
            }
            i3 |= i7;
        } else {
            timePickerColors2 = timePickerColors;
        }
        if ((i2 & 8) != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i4 = 2048;
                } else {
                    i4 = 1024;
                }
                i3 |= i4;
            }
            if ((i3 & 1171) != 1170) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        modifier5 = modifier4;
                        i5 = i3 & (-897);
                        timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        Modifier modifier9 = modifier4;
                        i5 = i3;
                        timePickerColorsColors = timePickerColors2;
                        modifier5 = modifier9;
                    }
                } else {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        modifier5 = modifier4;
                        i5 = i3 & (-897);
                        timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        Modifier modifier10 = modifier4;
                        i5 = i3;
                        timePickerColorsColors = timePickerColors2;
                        modifier5 = modifier10;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1249591487, i5, -1, "androidx.compose.material3.VerticalTimePicker (TimePicker.kt:957)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: vee
                        public final Object invoke(Object obj) {
                            return TimePickerKt.y((SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifierSemantics$default3 = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null);
                MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getCenterHorizontally(), composerStartRestartGroup, 48);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierSemantics$default3);
                ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
                constructor = companion5.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy3, companion5.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap3, companion5.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion5.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier3, companion5.getSetModifier());
                ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                VerticalClockDisplay(analogTimePickerState, timePickerColorsColors, composerStartRestartGroup, (i5 & 14) | ((i5 >> 3) & 112));
                Modifier.Companion companion6 = Modifier.INSTANCE;
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(companion6, ClockDisplayBottomMargin), composerStartRestartGroup, 6);
                ClockFace(SizeKt.size-3ABfNKs(companion6, TimePickerTokens.INSTANCE.m2195getClockDialContainerSizeD9Ej5fM()), analogTimePickerState, timePickerColorsColors, z2, composerStartRestartGroup, ((i5 << 3) & 112) | 6 | (i5 & 896) | (i5 & V4Signature.MAX_SIGNING_INFOS_SIZE));
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(companion6, ClockFaceBottomMargin), composerStartRestartGroup, 6);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                timePickerColors3 = timePickerColorsColors;
                modifier3 = modifier5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                timePickerColors3 = timePickerColors2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: wee
                    public final Object invoke(Object obj, Object obj2) {
                        return TimePickerKt.J(analogTimePickerState, modifier3, timePickerColors3, z, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i3 & 1171) != 1170) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i6 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    modifier5 = modifier4;
                    i5 = i3 & (-897);
                    timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                } else {
                    Modifier modifier11 = modifier4;
                    i5 = i3;
                    timePickerColorsColors = timePickerColors2;
                    modifier5 = modifier11;
                }
            } else {
                if (i6 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    modifier5 = modifier4;
                    i5 = i3 & (-897);
                    timePickerColorsColors = TimePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                } else {
                    Modifier modifier12 = modifier4;
                    i5 = i3;
                    timePickerColorsColors = timePickerColors2;
                    modifier5 = modifier12;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1249591487, i5, -1, "androidx.compose.material3.VerticalTimePicker (TimePicker.kt:957)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: vee
                    public final Object invoke(Object obj) {
                        return TimePickerKt.y((SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics$default4 = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null);
            MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getCenterHorizontally(), composerStartRestartGroup, 48);
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierSemantics$default4);
            ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
            constructor = companion7.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy4, companion7.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap4, companion7.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion7.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting()) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier4, companion7.getSetModifier());
            ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
            VerticalClockDisplay(analogTimePickerState, timePickerColorsColors, composerStartRestartGroup, (i5 & 14) | ((i5 >> 3) & 112));
            Modifier.Companion companion8 = Modifier.INSTANCE;
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(companion8, ClockDisplayBottomMargin), composerStartRestartGroup, 6);
            ClockFace(SizeKt.size-3ABfNKs(companion8, TimePickerTokens.INSTANCE.m2195getClockDialContainerSizeD9Ej5fM()), analogTimePickerState, timePickerColorsColors, z2, composerStartRestartGroup, ((i5 << 3) & 112) | 6 | (i5 & 896) | (i5 & V4Signature.MAX_SIGNING_INFOS_SIZE));
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(companion8, ClockFaceBottomMargin), composerStartRestartGroup, 6);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            timePickerColors3 = timePickerColorsColors;
            modifier3 = modifier5;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            timePickerColors3 = timePickerColors2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: wee
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.J(analogTimePickerState, modifier3, timePickerColors3, z, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float atan(float f, float f2) {
        float fAtan2 = ((float) Math.atan2(f, f2)) - 1.5707964f;
        return fAtan2 < 0.0f ? fAtan2 + FullCircle : fAtan2;
    }

    public static Unit b(TimePickerState timePickerState) {
        if (isPm(timePickerState)) {
            timePickerState.setHour(timePickerState.getHour() - 12);
        }
        return Unit.INSTANCE;
    }

    public static Unit c(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
        return Unit.INSTANCE;
    }

    public static boolean d(AnalogTimePickerState analogTimePickerState, Density density, MutableState mutableState) {
        long selectorPos = getSelectorPos(analogTimePickerState);
        float fMo4557toPx0680j_4 = density.mo4557toPx0680j_4(DpOffset.m6083getXD9Ej5fM(selectorPos));
        return ClockText$lambda$70(mutableState).m2915containsk4lQ0M(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(density.mo4557toPx0680j_4(DpOffset.m6085getYD9Ej5fM(selectorPos)))) & 4294967295L) | (Float.floatToRawIntBits(fMo4557toPx0680j_4) << 32)));
    }

    private static final float dist(float f, float f2, int i, int i2) {
        return (float) Math.hypot(i - f, i2 - f2);
    }

    private static final Modifier drawSelector(Modifier modifier, final AnalogTimePickerState analogTimePickerState, final TimePickerColors timePickerColors) {
        return DrawModifierKt.drawWithContent(modifier, new Function1() { // from class: xee
            public final Object invoke(Object obj) {
                return TimePickerKt.G(analogTimePickerState, timePickerColors, (ContentDrawScope) obj);
            }
        });
    }

    public static Unit e(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        HorizontalPeriodToggle(modifier, timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static MutableState f(TimePickerState timePickerState) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TimeInputImpl$hourTextValue(timePickerState), null, 2, null);
    }

    public static Unit g(int i, TimePickerState timePickerState) {
        if (!TimePickerSelectionMode.m1163equalsimpl0(i, timePickerState.mo74getSelectionyecRtBI())) {
            timePickerState.mo76setSelection6_8s6DQ(i);
        }
        return Unit.INSTANCE;
    }

    public static final float getClockDialMinContainerSize() {
        return ClockDialMinContainerSize;
    }

    public static final int getDefaultTimePickerLayoutType(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(435687004, i, -1, "androidx.compose.material3.<get-defaultTimePickerLayoutType> (TimePicker.kt:2051)");
        }
        int iDefaultTimePickerLayoutType = TimePicker_androidKt.defaultTimePickerLayoutType(composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iDefaultTimePickerLayoutType;
    }

    public static /* synthetic */ void getDefaultTimePickerLayoutType$annotations() {
    }

    public static final int getHourForDisplay(TimePickerState timePickerState) {
        if (timePickerState.getIs24hour()) {
            return timePickerState.getHour() % 24;
        }
        if (timePickerState.getHour() % 12 == 0) {
            return 12;
        }
        return isPm(timePickerState) ? timePickerState.getHour() - 12 : timePickerState.getHour();
    }

    public static final long getSelectorPos(AnalogTimePickerState analogTimePickerState) {
        float fM73getCurrentDiameterD9Ej5fM = analogTimePickerState.m73getCurrentDiameterD9Ej5fM();
        TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
        float fM6022constructorimpl = Dp.m6022constructorimpl(Dp.m6022constructorimpl(timePickerTokens.m2197getClockDialSelectorHandleContainerSizeD9Ej5fM() / SeparatorZIndex) * (fM73getCurrentDiameterD9Ej5fM / timePickerTokens.m2195getClockDialContainerSizeD9Ej5fM()));
        float fM6022constructorimpl2 = Dp.m6022constructorimpl(((Dp) RangesKt.coerceAtLeast(Dp.m6020boximpl(Dp.m6022constructorimpl(((analogTimePickerState.getIs24hour() && isPm(analogTimePickerState) && TimePickerSelectionMode.m1163equalsimpl0(analogTimePickerState.mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI())) ? Dp.m6022constructorimpl(analogTimePickerState.m73getCurrentDiameterD9Ej5fM() * InnerCircleToSizeRatio) : Dp.m6022constructorimpl(analogTimePickerState.m73getCurrentDiameterD9Ej5fM() * OuterCircleToSizeRatio)) - fM6022constructorimpl)), Dp.m6020boximpl(Dp.m6022constructorimpl(0.0f)))).m6036unboximpl() + fM6022constructorimpl);
        return DpOffset.m6078constructorimpl((((long) Float.floatToRawIntBits(Dp.m6022constructorimpl(Dp.m6022constructorimpl(((float) Math.cos(analogTimePickerState.getCurrentAngle())) * fM6022constructorimpl2) + Dp.m6022constructorimpl(analogTimePickerState.m73getCurrentDiameterD9Ej5fM() / SeparatorZIndex)))) << 32) | (((long) Float.floatToRawIntBits(Dp.m6022constructorimpl(Dp.m6022constructorimpl(fM6022constructorimpl2 * ((float) Math.sin(analogTimePickerState.getCurrentAngle()))) + Dp.m6022constructorimpl(analogTimePickerState.m73getCurrentDiameterD9Ej5fM() / SeparatorZIndex)))) & 4294967295L));
    }

    public static Unit h(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, MeasurePolicy measurePolicy, Shape shape, Shape shape2, int i, Composer composer, int i2) {
        PeriodToggleImpl(modifier, timePickerState, timePickerColors, measurePolicy, shape, shape2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit i(Modifier modifier, TimePickerColors timePickerColors, TimePickerState timePickerState, int i, Composer composer, int i2) {
        TimeInputImpl(modifier, timePickerColors, timePickerState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final boolean isPm(TimePickerState timePickerState) {
        return timePickerState.getHour() >= 12;
    }

    public static MutableState j(TimePickerState timePickerState) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TimeInputImpl$minuteTextValue(timePickerState), null, 2, null);
    }

    public static Unit k(Modifier modifier, int i, Composer composer, int i2) {
        DisplaySeparator(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit l(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        return Unit.INSTANCE;
    }

    public static Unit m(Modifier modifier, AnalogTimePickerState analogTimePickerState, int i, boolean z, int i2, Composer composer, int i3) {
        ClockText(modifier, analogTimePickerState, i, z, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: moveSelector-d3b8Pxo, reason: not valid java name */
    public static final void m1145moveSelectord3b8Pxo(TimePickerState timePickerState, float f, float f2, float f3, long j) {
        if (TimePickerSelectionMode.m1163equalsimpl0(timePickerState.mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI()) && timePickerState.getIs24hour()) {
            float fDist = dist(f, f2, IntOffset.m6150getXimpl(j), IntOffset.m6151getYimpl(j));
            if (isPm(timePickerState)) {
                timePickerState.setHour(timePickerState.getHour() - (fDist >= f3 ? 12 : 0));
            } else {
                timePickerState.setHour(timePickerState.getHour() + (fDist < f3 ? 12 : 0));
            }
        }
    }

    public static TimePickerStateImpl n(int i, int i2, boolean z) {
        return new TimePickerStateImpl(i, i2, z);
    }

    /* JADX INFO: renamed from: numberContentDescription-dSwYdS4, reason: not valid java name */
    public static final String m1146numberContentDescriptiondSwYdS4(int i, boolean z, int i2, Composer composer, int i3) {
        int iM1392constructorimpl;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(194237364, i3, -1, "androidx.compose.material3.numberContentDescription (TimePicker.kt:2019)");
        }
        if (TimePickerSelectionMode.m1163equalsimpl0(i, TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI())) {
            Strings.Companion companion = Strings.INSTANCE;
            iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_minute_suffix);
        } else if (z) {
            Strings.Companion companion2 = Strings.INSTANCE;
            iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_hour_24h_suffix);
        } else {
            Strings.Companion companion3 = Strings.INSTANCE;
            iM1392constructorimpl = Strings.m1392constructorimpl(R.string.m3c_time_picker_hour_suffix);
        }
        String strM1472getStringqBjtwXw = Strings_androidKt.m1472getStringqBjtwXw(iM1392constructorimpl, new Object[]{Integer.valueOf(i2)}, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strM1472getStringqBjtwXw;
    }

    public static Unit o(TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        ClockDisplayNumbers(timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:35:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:8:0x0016  */
    /* JADX INFO: renamed from: onTap-uYHVD98, reason: not valid java name */
    public static final Object m1147onTapuYHVD98(AnalogTimePickerState analogTimePickerState, float f, float f2, float f3, boolean z, long j, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        TimePickerKt$onTap$1 timePickerKt$onTap$1;
        float f4;
        float fRint;
        AnalogTimePickerState analogTimePickerState2;
        boolean z2;
        AnalogTimePickerState analogTimePickerState3;
        if (continuation instanceof TimePickerKt$onTap$1) {
            timePickerKt$onTap$1 = (TimePickerKt$onTap$1) continuation;
            int i = timePickerKt$onTap$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                timePickerKt$onTap$1.label = i - Integer.MIN_VALUE;
            } else {
                timePickerKt$onTap$1 = new TimePickerKt$onTap$1(continuation);
            }
        } else {
            timePickerKt$onTap$1 = new TimePickerKt$onTap$1(continuation);
        }
        TimePickerKt$onTap$1 timePickerKt$onTap$2 = timePickerKt$onTap$1;
        Object obj = timePickerKt$onTap$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = timePickerKt$onTap$2.label;
        if (i2 != 0) {
            if (i2 == 1) {
                z2 = timePickerKt$onTap$2.Z$0;
                AnalogTimePickerState analogTimePickerState4 = (AnalogTimePickerState) timePickerKt$onTap$2.L$0;
                ResultKt.throwOnFailure(obj);
                analogTimePickerState2 = analogTimePickerState4;
            } else {
                if (i2 != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                z2 = timePickerKt$onTap$2.Z$0;
                analogTimePickerState3 = (AnalogTimePickerState) timePickerKt$onTap$2.L$0;
                ResultKt.throwOnFailure(obj);
            }
            analogTimePickerState2 = analogTimePickerState3;
            if (z2) {
                analogTimePickerState2.mo76setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI());
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        float fAtan = atan(f2 - IntOffset.m6151getYimpl(j), f - IntOffset.m6150getXimpl(j));
        if (TimePickerSelectionMode.m1163equalsimpl0(analogTimePickerState.mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI())) {
            f4 = RadiansPerMinute;
            fRint = ((float) Math.rint((fAtan / RadiansPerMinute) / 5.0f)) * 5.0f;
        } else {
            f4 = RadiansPerHour;
            fRint = (float) Math.rint(fAtan / RadiansPerHour);
        }
        float f5 = fRint * f4;
        m1145moveSelectord3b8Pxo(analogTimePickerState, f, f2, f3, j);
        timePickerKt$onTap$2.L$0 = analogTimePickerState;
        timePickerKt$onTap$2.Z$0 = z;
        timePickerKt$onTap$2.label = 1;
        if (analogTimePickerState.rotateTo(f5, animationSpec, true, timePickerKt$onTap$2) != coroutine_suspended) {
            analogTimePickerState2 = analogTimePickerState;
            z2 = z;
        }
        return coroutine_suspended;
        if (TimePickerSelectionMode.m1163equalsimpl0(analogTimePickerState2.mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI()) && z2) {
            timePickerKt$onTap$2.L$0 = analogTimePickerState2;
            timePickerKt$onTap$2.Z$0 = z2;
            timePickerKt$onTap$2.label = 2;
            if (DelayKt.delay(100L, timePickerKt$onTap$2) != coroutine_suspended) {
                analogTimePickerState3 = analogTimePickerState2;
                analogTimePickerState2 = analogTimePickerState3;
            }
            return coroutine_suspended;
        }
        if (z2) {
            analogTimePickerState2.mo76setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI());
        }
        return Unit.INSTANCE;
    }

    public static Unit p(TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, int i, int i2, int i3, Composer composer, int i4) {
        m1137TimePickermT9BvqQ(timePickerState, modifier, timePickerColors, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit q(Modifier modifier, AnalogTimePickerState analogTimePickerState, TimePickerColors timePickerColors, boolean z, int i, Composer composer, int i2) {
        ClockFace(modifier, analogTimePickerState, timePickerColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit r(TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, int i, int i2, Composer composer, int i3) {
        TimeInput(timePickerState, modifier, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final TimePickerState rememberTimePickerState(final int i, final int i2, final boolean z, Composer composer, int i3, int i4) {
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            z = TimeFormat_androidKt.is24HourFormat(composer, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1237715277, i3, -1, "androidx.compose.material3.rememberTimePickerState (TimePicker.kt:587)");
        }
        Object[] objArr = new Object[0];
        Saver<TimePickerStateImpl, ?> Saver = TimePickerStateImpl.INSTANCE.Saver();
        boolean z2 = true;
        boolean z3 = ((((i3 & 14) ^ 6) > 4 && composer.changed(i)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(i2)) || (i3 & 48) == 32);
        if ((((i3 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 || !composer.changed(z)) && (i3 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 256) {
            z2 = false;
        }
        boolean z4 = z3 | z2;
        Object objRememberedValue = composer.rememberedValue();
        if (z4 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: hfe
                public final Object invoke() {
                    return TimePickerKt.n(i, i2, z);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        TimePickerStateImpl timePickerStateImpl = (TimePickerStateImpl) RememberSaveableKt.m2564rememberSaveable(objArr, (Saver) Saver, (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return timePickerStateImpl;
    }

    public static Unit s(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
        return Unit.INSTANCE;
    }

    public static Unit t(TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        VerticalClockDisplay(timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: timeInputOnChange-_K77t-0, reason: not valid java name */
    public static final void m1148timeInputOnChange_K77t0(int i, TimePickerState timePickerState, TextFieldValue textFieldValue, TextFieldValue textFieldValue2, int i2, Ref<Boolean> ref, Function1<? super TextFieldValue, Unit> function1) {
        ref.setValue(Boolean.FALSE);
        if (Intrinsics.areEqual(textFieldValue.getText(), textFieldValue2.getText())) {
            function1.invoke(textFieldValue);
            return;
        }
        int i3 = 12;
        if (textFieldValue.getText().length() == 0) {
            if (TimePickerSelectionMode.m1163equalsimpl0(i, TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI())) {
                timePickerState.setHour((!isPm(timePickerState) || timePickerState.getIs24hour()) ? 0 : 12);
            } else {
                timePickerState.setMinute(0);
            }
            function1.invoke(TextFieldValue.m5715copy3r_uNRQ$default(textFieldValue, "", 0L, (TextRange) null, 6, (Object) null));
            return;
        }
        try {
            int iDigitToInt = (textFieldValue.getText().length() == 3 && TextRange.m5479getStartimpl(textFieldValue.getSelection()) == 1) ? CharsKt.digitToInt(textFieldValue.getText().charAt(0)) : Integer.parseInt(textFieldValue.getText());
            if (iDigitToInt <= i2) {
                TimePickerSelectionMode.Companion companion = TimePickerSelectionMode.INSTANCE;
                if (TimePickerSelectionMode.m1163equalsimpl0(i, companion.m1167getHouryecRtBI())) {
                    if (iDigitToInt != 12 || !isPm(timePickerState)) {
                        if (iDigitToInt != 12 || isPm(timePickerState) || timePickerState.getIs24hour()) {
                            if (!isPm(timePickerState) || timePickerState.getIs24hour()) {
                                i3 = 0;
                            }
                            i3 += iDigitToInt;
                        } else {
                            i3 = 0;
                        }
                    }
                    timePickerState.setHour(i3);
                    if (iDigitToInt > 1 && !timePickerState.getIs24hour()) {
                        timePickerState.mo76setSelection6_8s6DQ(companion.m1168getMinuteyecRtBI());
                    }
                } else {
                    timePickerState.setMinute(iDigitToInt);
                }
                function1.invoke(textFieldValue.getText().length() <= 2 ? textFieldValue : TextFieldValue.m5715copy3r_uNRQ$default(textFieldValue, String.valueOf(textFieldValue.getText().charAt(0)), 0L, (TextRange) null, 6, (Object) null));
            }
        } catch (NumberFormatException | IllegalArgumentException unused) {
        }
    }

    public static Unit u(boolean z, Shape shape, Function0 function0, TimePickerColors timePickerColors, Function3 function3, int i, Composer composer, int i2) {
        ToggleItem(z, shape, function0, timePickerColors, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit v(Modifier modifier, int i, TimePickerState timePickerState, int i2, TimePickerColors timePickerColors, int i3, Composer composer, int i4) {
        m1139TimeSelectorSAnMeKU(modifier, i, timePickerState, i2, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1));
        return Unit.INSTANCE;
    }

    private static final Modifier visible(Modifier modifier, final boolean z) {
        return modifier.then(new VisibleModifier(z, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TimePickerKt$visible$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("visible");
                inspectorInfo.getProperties().set("visible", Boolean.valueOf(z));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }

    public static Unit w(Modifier modifier, float f, Function2 function2, int i, int i2, Composer composer, int i3) {
        CircularLayout(modifier, f, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit x(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates parentCoordinates = layoutCoordinates.getParentCoordinates();
        ClockText$lambda$68(mutableState, parentCoordinates != null ? IntSizeKt.m6199getCenterozmzZPI(parentCoordinates.mo4613getSizeYbymL2g()) : IntOffset.INSTANCE.m6161getZeronOccac());
        mutableState2.setValue(LayoutCoordinatesKt.boundsInParent(layoutCoordinates));
        ClockText$lambda$65(mutableState3, ClockText$lambda$70(mutableState2).m2919getCenterF1C5BW0());
        return Unit.INSTANCE;
    }

    public static Unit y(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        return Unit.INSTANCE;
    }

    public static Unit z(boolean z, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setSelected(semanticsPropertyReceiver, z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class C00641 implements Function3<IntList, Composer, Integer, Unit> {
        final /* synthetic */ boolean $autoSwitchToMinute;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ AnalogTimePickerState $state;

        public C00641(TimePickerColors timePickerColors, AnalogTimePickerState analogTimePickerState, boolean z) {
            this.$colors = timePickerColors;
            this.$state = analogTimePickerState;
            this.$autoSwitchToMinute = z;
        }

        public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.selectableGroup(semanticsPropertyReceiver);
            return Unit.INSTANCE;
        }

        public final void invoke(final IntList intList, Composer composer, int i) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(747010833, i, -1, "androidx.compose.material3.ClockFace.<anonymous> (TimePicker.kt:1609)");
            }
            Modifier modifier = SizeKt.size-3ABfNKs(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m2195getClockDialContainerSizeD9Ej5fM());
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.z4
                    public final Object invoke(Object obj) {
                        return TimePickerKt.C00641.a((SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, (Function1) objRememberedValue, 1, null);
            float f = TimePickerKt.OuterCircleToSizeRatio;
            final TimePickerColors timePickerColors = this.$colors;
            final AnalogTimePickerState analogTimePickerState = this.$state;
            final boolean z = this.$autoSwitchToMinute;
            TimePickerKt.CircularLayout(modifierSemantics$default, f, ComposableLambdaKt.rememberComposableLambda(-99063847, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1.2
                public final void invoke(Composer composer2, int i2) {
                    if (!composer2.shouldExecute((i2 & 3) != 2, i2 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-99063847, i2, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous> (TimePicker.kt:1613)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(timePickerColors.m1101clockDialContentColorvNxB06k$material3(false))), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-596940007, true, new C00111(intList, analogTimePickerState, z), composer2, 54), composer2, ProvidedValue.$stable | 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1, reason: invalid class name and collision with other inner class name */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
                public static final class C00111 implements Function2<Composer, Integer, Unit> {
                    final /* synthetic */ boolean $autoSwitchToMinute;
                    final /* synthetic */ IntList $screen;
                    final /* synthetic */ AnalogTimePickerState $state;

                    public C00111(IntList intList, AnalogTimePickerState analogTimePickerState, boolean z) {
                        this.$screen = intList;
                        this.$state = analogTimePickerState;
                        this.$autoSwitchToMinute = z;
                    }

                    public static Unit a(int i, SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setTraversalIndex(semanticsPropertyReceiver, i + 1.0f);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer, int i) {
                        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                            composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-596940007, i, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1616)");
                        }
                        composer.startReplaceGroup(1866272144);
                        IntList intList = this.$screen;
                        int i2 = intList._size;
                        AnalogTimePickerState analogTimePickerState = this.$state;
                        boolean z = this.$autoSwitchToMinute;
                        for (final int i3 = 0; i3 < i2; i3++) {
                            int i4 = (!analogTimePickerState.getIs24hour() || TimePickerSelectionMode.m1163equalsimpl0(analogTimePickerState.mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI())) ? intList.get(i3) : intList.get(i3) % 12;
                            Modifier.Companion companion = Modifier.INSTANCE;
                            boolean zChanged = composer.changed(i3);
                            Object objRememberedValue = composer.rememberedValue();
                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: androidx.compose.material3.a5
                                    public final Object invoke(Object obj) {
                                        return TimePickerKt.C00641.AnonymousClass2.C00111.a(i3, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                composer.updateRememberedValue(objRememberedValue);
                            }
                            TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion, false, (Function1) objRememberedValue, 1, null), analogTimePickerState, i4, z, composer, 0);
                        }
                        composer.endReplaceGroup();
                        if (TimePickerSelectionMode.m1163equalsimpl0(this.$state.mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI()) && this.$state.getIs24hour()) {
                            composer.startReplaceGroup(2020585964);
                            TimePickerKt.CircularLayout(BackgroundKt.background-bw27NRU(SizeKt.size-3ABfNKs(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutId.InnerCircle), TimePickerTokens.INSTANCE.m2195getClockDialContainerSizeD9Ej5fM()), Color.INSTANCE.m3169getTransparent0d7_KjU(), RoundedCornerShapeKt.getCircleShape()), TimePickerKt.InnerCircleToSizeRatio, ComposableLambdaKt.rememberComposableLambda(-1385767514, true, new C00122(this.$state, this.$autoSwitchToMinute), composer, 54), composer, 432, 0);
                            composer.endReplaceGroup();
                        } else {
                            composer.startReplaceGroup(2021505641);
                            composer.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$2, reason: invalid class name and collision with other inner class name */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
                    public static final class C00122 implements Function2<Composer, Integer, Unit> {
                        final /* synthetic */ boolean $autoSwitchToMinute;
                        final /* synthetic */ AnalogTimePickerState $state;

                        public C00122(AnalogTimePickerState analogTimePickerState, boolean z) {
                            this.$state = analogTimePickerState;
                            this.$autoSwitchToMinute = z;
                        }

                        public static Unit a(int i, SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setTraversalIndex(semanticsPropertyReceiver, 12.0f + i);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer, int i) {
                            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                                composer.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1385767514, i, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1639)");
                            }
                            int i2 = TimePickerKt.ExtraHours._size;
                            AnalogTimePickerState analogTimePickerState = this.$state;
                            boolean z = this.$autoSwitchToMinute;
                            for (final int i3 = 0; i3 < i2; i3++) {
                                int i4 = TimePickerKt.ExtraHours.get(i3);
                                Modifier.Companion companion = Modifier.INSTANCE;
                                boolean zChanged = composer.changed(i3);
                                Object objRememberedValue = composer.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function1() { // from class: androidx.compose.material3.b5
                                        public final Object invoke(Object obj) {
                                            return TimePickerKt.C00641.AnonymousClass2.C00111.C00122.a(i3, (SemanticsPropertyReceiver) obj);
                                        }
                                    };
                                    composer.updateRememberedValue(objRememberedValue);
                                }
                                TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion, false, (Function1) objRememberedValue, 1, null), analogTimePickerState, i4, z, composer, 0);
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }
            }, composer, 54), composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(IntList intList, Composer composer, Integer num) {
            invoke(intList, composer, num.intValue());
            return Unit.INSTANCE;
        }
    }
}
