package androidx.compose.ui.platform;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.os.Trace;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScrollCaptureTarget;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.collection.ScatterMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.retain.ForgetfulRetainedValuesStore;
import androidx.compose.runtime.retain.RetainedValuesStore;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.R;
import androidx.compose.ui.SessionMutex;
import androidx.compose.ui.autofill.AndroidAutofill;
import androidx.compose.ui.autofill.AndroidAutofillManager;
import androidx.compose.ui.autofill.AndroidAutofill_androidKt;
import androidx.compose.ui.autofill.Autofill;
import androidx.compose.ui.autofill.AutofillCallback;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.autofill.PlatformAutofillManagerImpl;
import androidx.compose.ui.contentcapture.AndroidContentCaptureManager;
import androidx.compose.ui.draganddrop.AndroidDragAndDropManager;
import androidx.compose.ui.draganddrop.ComposeDragShadowBuilder;
import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusInteropUtils_androidKt;
import androidx.compose.ui.focus.FocusListener;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusOwnerImplKt;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusTraversalKt;
import androidx.compose.ui.focus.PlatformFocusOwner;
import androidx.compose.ui.focus.TwoDimensionalFocusSearchKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidGraphicsContext_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.PlatformHapticFeedback;
import androidx.compose.ui.input.InputMode;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.InputModeManagerImpl;
import androidx.compose.ui.input.indirect.AndroidIndirectPointerEvent;
import androidx.compose.ui.input.indirect.AndroidIndirectPointerEvent_androidKt;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerEventPrimaryDirectionalMotionAxis;
import androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.MatrixPositionCalculator;
import androidx.compose.ui.input.pointer.MotionEventAdapter;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.input.pointer.PointerInputEvent;
import androidx.compose.ui.input.pointer.PointerInputEventData;
import androidx.compose.ui.input.pointer.PointerInputEventProcessor;
import androidx.compose.ui.input.pointer.PointerInputEventProcessorKt;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.InsetsListener;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.layout.RectRulers;
import androidx.compose.ui.layout.RootMeasurePolicy;
import androidx.compose.ui.layout.RulerScope;
import androidx.compose.ui.layout.WindowInsetsRulerProvider;
import androidx.compose.ui.layout.WindowInsetsRulers_androidKt;
import androidx.compose.ui.layout.WindowWindowInsetsAnimationValues;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.MeasureAndLayoutDelegate;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.OutOfFrameExecutor;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.AndroidComposeView.RootModifierNode;
import androidx.compose.ui.relocation.BringIntoViewModifierNode;
import androidx.compose.ui.scrollcapture.ScrollCapture;
import androidx.compose.ui.semantics.EmptySemanticsModifier;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.spatial.RectManager;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontFamilyResolver_androidKt;
import androidx.compose.ui.text.input.PlatformTextInputService;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputServiceAndroid;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.AndroidDensity_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.core.app.NotificationCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000¢\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d*\u0002Ù\u0002\b\u0001\u0018\u0000 ÿ\u00042\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000b:\u0006ÿ\u0004\u0080\u0005\u0081\u0005B\u0017\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010M\u001a\u00020NH\u0016J\u0017\u0010^\u001a\u00020\u00162\u0006\u0010_\u001a\u00020`H\u0002¢\u0006\u0004\ba\u0010bJ\u0017\u0010c\u001a\u00020\u00162\u0006\u0010_\u001a\u00020`H\u0002¢\u0006\u0004\bd\u0010bJ\u0017\u0010e\u001a\u00020\u00162\u0006\u0010_\u001a\u00020`H\u0002¢\u0006\u0004\bf\u0010bJ\u0017\u0010g\u001a\u00020\u00162\u0006\u0010_\u001a\u00020`H\u0016¢\u0006\u0004\bh\u0010bJ\u0019\u0010i\u001a\u0004\u0018\u00010$2\u0006\u0010_\u001a\u00020`H\u0002¢\u0006\u0004\bj\u0010kJ\n\u0010l\u001a\u0004\u0018\u00010mH\u0016J\b\u0010n\u001a\u000207H\u0016J\u0012\u0010o\u001a\u0004\u0018\u00010$2\u0006\u0010p\u001a\u00020NH\u0002JA\u0010\u009e\u0002\u001a\u00030\u009f\u00022.\u0010 \u0002\u001a)\b\u0001\u0012\u0005\u0012\u00030¢\u0002\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u009f\u00020£\u0002\u0012\u0007\u0012\u0005\u0018\u00010¤\u00020¡\u0002¢\u0006\u0003\b¥\u0002H\u0096@¢\u0006\u0003\u0010¦\u0002J\u0013\u0010ã\u0002\u001a\u0002072\b\u0010ä\u0002\u001a\u00030å\u0002H\u0016J=\u0010æ\u0002\u001a\u0002072!\u0010ç\u0002\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010$\u0018\u00010è\u0002j\r\u0012\u0006\u0012\u0004\u0018\u00010$\u0018\u0001`é\u00022\u0006\u0010p\u001a\u00020N2\u0007\u0010ê\u0002\u001a\u00020NH\u0016J\u0013\u0010ë\u0002\u001a\u0002072\b\u0010ì\u0002\u001a\u00030í\u0002H\u0016J.\u0010ò\u0002\u001a\u0002072\b\u0010ó\u0002\u001a\u00030å\u00022\b\u0010ô\u0002\u001a\u00030õ\u00022\u000f\u0010ö\u0002\u001a\n\u0012\u0005\u0012\u00030ø\u00020÷\u0002H\u0016J\u0013\u0010ù\u0002\u001a\u0002072\b\u0010ú\u0002\u001a\u00030û\u0002H\u0016J\u0013\u0010ü\u0002\u001a\u0002072\b\u0010ú\u0002\u001a\u00030û\u0002H\u0016J\u001e\u0010ý\u0002\u001a\u0004\u0018\u00010$2\t\u0010þ\u0002\u001a\u0004\u0018\u00010$2\u0006\u0010p\u001a\u00020NH\u0016J\u001b\u0010ÿ\u0002\u001a\u00020\u00162\u0006\u0010p\u001a\u00020N2\n\u0010\u0080\u0003\u001a\u0005\u0018\u00010å\u0002J\u001b\u0010\u0081\u0003\u001a\u00020\u00162\u0006\u0010p\u001a\u00020N2\n\u0010\u0080\u0003\u001a\u0005\u0018\u00010å\u0002J\u001b\u0010\u0082\u0003\u001a\u00020\u00162\u0006\u0010p\u001a\u00020N2\n\u0010\u0080\u0003\u001a\u0005\u0018\u00010å\u0002J\u001d\u0010\u0083\u0003\u001a\u00020\u00162\u0006\u0010p\u001a\u00020N2\n\u0010\u0080\u0003\u001a\u0005\u0018\u00010å\u0002H\u0016J$\u0010\u0084\u0003\u001a\u00020\u00162\b\u0010_\u001a\u0004\u0018\u00010`2\t\u0010\u0080\u0003\u001a\u0004\u0018\u00010mH\u0016¢\u0006\u0003\b\u0085\u0003J\t\u0010\u0086\u0003\u001a\u000207H\u0016J&\u0010\u0087\u0003\u001a\u0002072\u0007\u0010\u0088\u0003\u001a\u00020\u00162\u0006\u0010p\u001a\u00020N2\n\u0010\u0080\u0003\u001a\u0005\u0018\u00010å\u0002H\u0014J!\u0010\u0087\u0003\u001a\u0002072\n\u0010\u0089\u0003\u001a\u0005\u0018\u00010\u008a\u00032\n\u0010\u008b\u0003\u001a\u0005\u0018\u00010\u008a\u0003H\u0016J\u0012\u0010\u008c\u0003\u001a\u0002072\u0007\u0010\u008d\u0003\u001a\u00020\u0016H\u0016J\u001c\u0010\u008e\u0003\u001a\u00020\u00162\b\u0010\u008f\u0003\u001a\u00030\u0090\u0003H\u0016¢\u0006\u0006\b\u0091\u0003\u0010\u0092\u0003J\u0013\u0010\u0093\u0003\u001a\u00020\u00162\b\u0010\u0094\u0003\u001a\u00030\u0095\u0003H\u0017J\u0013\u0010\u0096\u0003\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030\u0098\u0003H\u0016J\u0013\u0010\u0099\u0003\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030\u0098\u0003H\u0016J\u0012\u0010\u009a\u0003\u001a\u0002072\u0007\u0010\u009b\u0003\u001a\u00020\u0016H\u0016J\u0013\u0010\u009c\u0003\u001a\u0002072\b\u0010\u009d\u0003\u001a\u00030è\u0001H\u0016J\u0012\u0010\u009e\u0003\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0016J\u0012\u0010 \u0003\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0016J\u0012\u0010¡\u0003\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0016J\u0012\u0010¢\u0003\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0016J\u0007\u0010£\u0003\u001a\u000207J\t\u0010¤\u0003\u001a\u000207H\u0016J\u0018\u0010¥\u0003\u001a\u0002072\r\u0010¦\u0003\u001a\b\u0012\u0004\u0012\u00020706H\u0016JC\u0010§\u0003\u001a\u00020\u00162\b\u0010¨\u0003\u001a\u00030©\u00032\b\u0010ª\u0003\u001a\u00030«\u00032\u001b\u0010¬\u0003\u001a\u0016\u0012\u0005\u0012\u00030\u00ad\u0003\u0012\u0004\u0012\u0002070\u008a\u0002¢\u0006\u0003\b¥\u0002H\u0002¢\u0006\u0006\b®\u0003\u0010¯\u0003J\u0012\u0010°\u0003\u001a\u0002072\u0007\u0010±\u0003\u001a\u00020\u0001H\u0002J&\u0010²\u0003\u001a\u0002072\u0007\u0010³\u0003\u001a\u00020N2\b\u0010´\u0003\u001a\u00030µ\u00032\b\u0010¶\u0003\u001a\u00030·\u0003H\u0002J\u0014\u0010¸\u0003\u001a\u0002072\t\u0010¹\u0003\u001a\u0004\u0018\u00010$H\u0016J\u001d\u0010¸\u0003\u001a\u0002072\t\u0010¹\u0003\u001a\u0004\u0018\u00010$2\u0007\u0010º\u0003\u001a\u00020NH\u0016J&\u0010¸\u0003\u001a\u0002072\t\u0010¹\u0003\u001a\u0004\u0018\u00010$2\u0007\u0010»\u0003\u001a\u00020N2\u0007\u0010¼\u0003\u001a\u00020NH\u0016J \u0010¸\u0003\u001a\u0002072\t\u0010¹\u0003\u001a\u0004\u0018\u00010$2\n\u0010½\u0003\u001a\u0005\u0018\u00010¾\u0003H\u0016J)\u0010¸\u0003\u001a\u0002072\t\u0010¹\u0003\u001a\u0004\u0018\u00010$2\u0007\u0010º\u0003\u001a\u00020N2\n\u0010½\u0003\u001a\u0005\u0018\u00010¾\u0003H\u0016J\u0019\u0010¿\u0003\u001a\u0002072\u0007\u0010#\u001a\u00030À\u00032\u0007\u0010Á\u0003\u001a\u00020|J\u0010\u0010Â\u0003\u001a\u0002072\u0007\u0010#\u001a\u00030À\u0003J\u001a\u0010Ã\u0003\u001a\u0002072\u0007\u0010#\u001a\u00030À\u00032\b\u0010Ä\u0003\u001a\u00030Å\u0003J\u0016\u0010Æ\u0003\u001a\u0002072\u000b\b\u0002\u0010Ç\u0003\u001a\u0004\u0018\u00010|H\u0002J\r\u0010È\u0003\u001a\u00020\u0016*\u00020|H\u0002J\u0012\u0010É\u0003\u001a\u0002072\u0007\u0010Ê\u0003\u001a\u00020\u0016H\u0016J%\u0010É\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\b\u0010Ë\u0003\u001a\u00030ã\u0001H\u0016¢\u0006\u0006\bÌ\u0003\u0010Í\u0003J\t\u0010Î\u0003\u001a\u000207H\u0002J\u001b\u0010Ï\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\u0007\u0010Ð\u0003\u001a\u00020\u0016H\u0016J-\u0010Ñ\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\u0007\u0010Ð\u0003\u001a\u00020\u00162\u0007\u0010Ò\u0003\u001a\u00020\u00162\u0007\u0010Æ\u0003\u001a\u00020\u0016H\u0016J$\u0010Ó\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\u0007\u0010Ð\u0003\u001a\u00020\u00162\u0007\u0010Ò\u0003\u001a\u00020\u0016H\u0016J\u0012\u0010Ô\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|H\u0016J\t\u0010Õ\u0003\u001a\u000207H\u0016J\u0015\u0010Ö\u0003\u001a\u0002072\n\u0010×\u0003\u001a\u0005\u0018\u00010\u008d\u0001H\u0016J\u001b\u0010Ø\u0003\u001a\u0002072\u0007\u0010Ù\u0003\u001a\u00020N2\u0007\u0010Ú\u0003\u001a\u00020NH\u0014J\u0018\u0010Û\u0003\u001a\u00020N*\u00030Ü\u0003H\u0082\n¢\u0006\u0006\bÝ\u0003\u0010Þ\u0003J\u0018\u0010ß\u0003\u001a\u00020N*\u00030Ü\u0003H\u0082\n¢\u0006\u0006\bà\u0003\u0010Þ\u0003J%\u0010á\u0003\u001a\u00030Ü\u00032\u0007\u0010â\u0003\u001a\u00020N2\u0007\u0010ã\u0003\u001a\u00020NH\u0002¢\u0006\u0006\bä\u0003\u0010å\u0003J\u001c\u0010æ\u0003\u001a\u00030Ü\u00032\u0007\u0010ç\u0003\u001a\u00020NH\u0002¢\u0006\u0006\bè\u0003\u0010é\u0003J6\u0010ê\u0003\u001a\u0002072\u0007\u0010ë\u0003\u001a\u00020\u00162\u0007\u0010ì\u0003\u001a\u00020N2\u0007\u0010í\u0003\u001a\u00020N2\u0007\u0010î\u0003\u001a\u00020N2\u0007\u0010ã\u0003\u001a\u00020NH\u0014J\t\u0010ð\u0003\u001a\u000207H\u0002J\u0013\u0010ñ\u0003\u001a\u0002072\b\u0010Ä\u0003\u001a\u00030Å\u0003H\u0014Ji\u0010ò\u0003\u001a\u00030¬\u00012B\u0010ó\u0003\u001a=\u0012\u0017\u0012\u00150ô\u0003¢\u0006\u000f\bõ\u0003\u0012\n\bö\u0003\u0012\u0005\b\b(Ä\u0003\u0012\u0019\u0012\u0017\u0018\u00010÷\u0003¢\u0006\u000f\bõ\u0003\u0012\n\bö\u0003\u0012\u0005\b\b(ø\u0003\u0012\u0004\u0012\u0002070¡\u00022\r\u0010ù\u0003\u001a\b\u0012\u0004\u0012\u000207062\n\u0010ú\u0003\u001a\u0005\u0018\u00010÷\u0003H\u0016J\u0019\u0010û\u0003\u001a\u00020\u00162\b\u0010ü\u0003\u001a\u00030¬\u0001H\u0000¢\u0006\u0003\bý\u0003J\t\u0010þ\u0003\u001a\u000207H\u0016J\u0012\u0010ÿ\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|H\u0016J\u0012\u0010\u0080\u0004\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|H\u0016J\u001b\u0010\u0081\u0004\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\u0007\u0010\u0082\u0004\u001a\u00020NH\u0016J\u001b\u0010\u0083\u0004\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\u0007\u0010\u0082\u0004\u001a\u00020NH\u0016J\u0016\u0010\u0084\u0004\u001a\u0002072\u000b\u0010#\u001a\u00070$j\u0003`\u0085\u0004H\u0016J\u0013\u0010\u0086\u0004\u001a\u0002072\b\u0010¦\u0003\u001a\u00030\u0087\u0004H\u0016J\u0013\u0010\u0088\u0004\u001a\u0002072\b\u0010Ä\u0003\u001a\u00030Å\u0003H\u0014J\"\u0010\u0089\u0004\u001a\u0002072\b\u0010ü\u0003\u001a\u00030¬\u00012\u0007\u0010\u008a\u0004\u001a\u00020\u0016H\u0000¢\u0006\u0003\b\u008b\u0004J\u001e\u0010\u008c\u0004\u001a\u0002072\u0015\u0010\u008d\u0004\u001a\u0010\u0012\u0005\u0012\u00030þ\u0001\u0012\u0004\u0012\u0002070\u008a\u0002J\u0010\u0010\u008e\u0004\u001a\u000207H\u0086@¢\u0006\u0003\u0010\u008f\u0004J\u0010\u0010\u0090\u0004\u001a\u000207H\u0086@¢\u0006\u0003\u0010\u008f\u0004J\u0012\u0010\u0091\u0004\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0002J\u0012\u0010\u0092\u0004\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0002J\t\u0010\u0093\u0004\u001a\u000207H\u0016J\t\u0010\u0094\u0004\u001a\u000207H\u0014J#\u0010\u0095\u0004\u001a\u0004\u0018\u0001002\n\u0010\u0096\u0004\u001a\u0005\u0018\u00010û\u00022\n\u0010\u0097\u0004\u001a\u0005\u0018\u00010\u0098\u0004H\u0002J\t\u0010\u0099\u0004\u001a\u000207H\u0014J\u001e\u0010\u009a\u0004\u001a\u0002072\n\u0010ì\u0002\u001a\u0005\u0018\u00010í\u00022\u0007\u0010\u009b\u0004\u001a\u00020NH\u0016J\u001a\u0010Á\u0001\u001a\u0002072\u000f\u0010\u009c\u0004\u001a\n\u0012\u0005\u0012\u00030\u009e\u00040\u009d\u0004H\u0016J0\u0010\u009f\u0004\u001a\u0002072\b\u0010 \u0004\u001a\u00030¡\u00042\b\u0010¢\u0004\u001a\u00030ð\u00012\u0011\u0010£\u0004\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010¤\u00040÷\u0002H\u0017J\u001c\u0010¥\u0004\u001a\u0002072\u0011\u0010¦\u0004\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010¨\u00040§\u0004H\u0017J\u0013\u0010©\u0004\u001a\u00020\u00162\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0016J\u0013\u0010«\u0004\u001a\u00020\u00162\b\u0010\u0094\u0003\u001a\u00030\u0095\u0003H\u0002J\u0013\u0010¬\u0004\u001a\u00020\u00162\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0016J\u0013\u0010\u00ad\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u0002H\u0002J\u001d\u0010®\u0004\u001a\u00030¯\u00042\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0002¢\u0006\u0006\b°\u0004\u0010±\u0004J\u001d\u0010²\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u00022\b\u0010³\u0004\u001a\u00030Ð\u0002H\u0002J\u0013\u0010´\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u0002H\u0002J\u001d\u0010µ\u0004\u001a\u00030¯\u00042\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0002¢\u0006\u0006\b¶\u0004\u0010±\u0004J1\u0010·\u0004\u001a\u0002072\b\u0010ª\u0004\u001a\u00030Ð\u00022\u0007\u0010¸\u0004\u001a\u00020N2\b\u0010¹\u0004\u001a\u00030è\u00012\t\b\u0002\u0010º\u0004\u001a\u00020\u0016H\u0002J\u0011\u0010»\u0004\u001a\u00020\u00162\u0006\u0010p\u001a\u00020NH\u0016J\u0011\u0010¼\u0004\u001a\u00020\u00162\u0006\u0010p\u001a\u00020NH\u0016J\u0013\u0010½\u0004\u001a\u00020\u00162\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0002J\u001b\u0010¾\u0004\u001a\u00020\u00132\u0007\u0010¿\u0004\u001a\u00020\u0013H\u0016¢\u0006\u0006\bÀ\u0004\u0010Á\u0004J\u001c\u0010¾\u0004\u001a\u0002072\b\u0010Â\u0004\u001a\u00030ò\u0001H\u0016¢\u0006\u0006\bÃ\u0004\u0010Ä\u0004J\u001b\u0010Å\u0004\u001a\u00020\u00132\u0007\u0010Æ\u0004\u001a\u00020\u0013H\u0016¢\u0006\u0006\bÇ\u0004\u0010Á\u0004J\t\u0010È\u0004\u001a\u000207H\u0002J\u0013\u0010È\u0004\u001a\u0002072\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0002J\t\u0010É\u0004\u001a\u000207H\u0002J\t\u0010Ê\u0004\u001a\u000207H\u0002J\t\u0010Ë\u0004\u001a\u00020\u0016H\u0016J\u0016\u0010Ì\u0004\u001a\u0005\u0018\u00010Í\u00042\b\u0010Î\u0004\u001a\u00030Ï\u0004H\u0016J\u001b\u0010Ð\u0004\u001a\u00020\u00132\u0007\u0010Ñ\u0004\u001a\u00020\u0013H\u0016¢\u0006\u0006\bÒ\u0004\u0010Á\u0004J\u001b\u0010Ó\u0004\u001a\u00020\u00132\u0007\u0010¿\u0004\u001a\u00020\u0013H\u0016¢\u0006\u0006\bÔ\u0004\u0010Á\u0004J\u0013\u0010Õ\u0004\u001a\u0002072\b\u0010Ö\u0004\u001a\u00030´\u0001H\u0014J\t\u0010×\u0004\u001a\u000207H\u0002J\u0013\u0010Ø\u0004\u001a\u0002072\b\u0010Ö\u0004\u001a\u00030´\u0001H\u0002J\u0012\u0010Ù\u0004\u001a\u0002072\u0007\u0010·\u0002\u001a\u00020NH\u0016J\t\u0010Ú\u0004\u001a\u00020\u0016H\u0002J\u0013\u0010Û\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u0002H\u0016J\u0013\u0010Ü\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u0002H\u0002J\u0013\u0010Ý\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u0002H\u0002J\u001d\u0010Þ\u0004\u001a\u0004\u0018\u00010$2\u0007\u0010ß\u0004\u001a\u00020N2\u0007\u0010à\u0004\u001a\u00020$H\u0002J\u001d\u0010á\u0004\u001a\u00030â\u00042\b\u0010\u0097\u0003\u001a\u00030Ð\u00022\u0007\u0010ã\u0004\u001a\u00020NH\u0017J\u0012\u0010è\u0004\u001a\u0004\u0018\u00010$2\u0007\u0010ß\u0004\u001a\u00020NJ\t\u0010ê\u0004\u001a\u00020\u0016H\u0016J\t\u0010ì\u0004\u001a\u000207H\u0016J\t\u0010í\u0004\u001a\u000207H\u0016J\t\u0010ï\u0004\u001a\u000207H\u0016J\t\u0010ð\u0004\u001a\u000207H\u0016J\u0018\u0010ô\u0004\u001a\u0002072\r\u0010õ\u0004\u001a\b\u0012\u0004\u0012\u00020706H\u0016J\u0013\u0010ö\u0004\u001a\u0002072\b\u0010÷\u0004\u001a\u00030Ö\u0002H\u0017J\u001b\u0010ø\u0004\u001a\u0002072\u0007\u0010ù\u0004\u001a\u00020\u0013H\u0016¢\u0006\u0006\bú\u0004\u0010ú\u0001J\t\u0010û\u0004\u001a\u000207H\u0016J\t\u0010ü\u0004\u001a\u000207H\u0016J\u0012\u0010ý\u0004\u001a\u0002072\u0007\u0010þ\u0004\u001a\u00020\u0016H\u0016R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0017\u001a\u0004\u0018\u00010\u00188\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00101\u001a\u0002002\u0006\u0010/\u001a\u000200@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001a\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002070605X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010<\u001a\u00020;2\u0006\u0010:\u001a\u00020;8V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010B\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u000e\u0010C\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010D\u001a\u00020\u0016X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u000e\u0010G\u001a\u00020HX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\u00020JX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u000f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0014\u0010S\u001a\u00020TX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010VR\u000e\u0010W\u001a\u00020XX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010Y\u001a\u00020Z8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\\R\u000e\u0010]\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010s\u001a\u00020tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bu\u0010vR\u0011\u0010w\u001a\u00020x¢\u0006\b\n\u0000\u001a\u0004\by\u0010zR\u001a\u0010{\u001a\u00020|X\u0096\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b}\u0010\u001a\u001a\u0004\b~\u0010\u007fR\u001e\u0010\u0080\u0001\u001a\t\u0012\u0004\u0012\u00020|0\u0081\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0018\u0010\u0084\u0001\u001a\u00030\u0085\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0018\u0010\u0088\u0001\u001a\u00030\u0089\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R\"\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001\"\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0018\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0010\u0010\u0096\u0001\u001a\u00030\u0097\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0098\u0001\u001a\u00030\u0099\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001\"\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0018\u0010\u009e\u0001\u001a\u00030\u009f\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b \u0001\u0010¡\u0001R\u0018\u0010¢\u0001\u001a\u00030£\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b¤\u0001\u0010¥\u0001R\u0018\u0010¦\u0001\u001a\u00030§\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b¨\u0001\u0010©\u0001R\u0017\u0010ª\u0001\u001a\n\u0012\u0005\u0012\u00030¬\u00010«\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u00ad\u0001\u001a\f\u0012\u0005\u0012\u00030¬\u0001\u0018\u00010«\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010®\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010¯\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010°\u0001\u001a\u00030±\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010²\u0001\u001a\u00030³\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010µ\u0001\u001a\u00030´\u00012\u0007\u0010:\u001a\u00030´\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\bº\u0001\u0010B\u001a\u0006\b¶\u0001\u0010·\u0001\"\u0006\b¸\u0001\u0010¹\u0001R\u0012\u0010»\u0001\u001a\u0005\u0018\u00010¼\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010½\u0001\u001a\u0005\u0018\u00010¾\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b¿\u0001\u0010À\u0001R\u001a\u0010Á\u0001\u001a\u0005\u0018\u00010Â\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÃ\u0001\u0010Ä\u0001R\u001a\u0010Å\u0001\u001a\u0005\u0018\u00010Æ\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÇ\u0001\u0010È\u0001R\u000f\u0010É\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010Ê\u0001\u001a\u00030Ë\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÌ\u0001\u0010Í\u0001R\u0018\u0010Î\u0001\u001a\u00030Ï\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÐ\u0001\u0010Ñ\u0001R\u0018\u0010Ò\u0001\u001a\u00030Ó\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÔ\u0001\u0010Õ\u0001R'\u0010Ö\u0001\u001a\u00020\u00168VX\u0096\u000e¢\u0006\u0018\n\u0000\u0012\u0005\b×\u0001\u0010\u001a\u001a\u0005\bØ\u0001\u0010F\"\u0006\bÙ\u0001\u0010Ú\u0001R\u0012\u0010Û\u0001\u001a\u0005\u0018\u00010Ü\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010Ý\u0001\u001a\u00030Ü\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bÞ\u0001\u0010ß\u0001R\u0012\u0010à\u0001\u001a\u0005\u0018\u00010á\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010â\u0001\u001a\u0005\u0018\u00010ã\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010ä\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010å\u0001\u001a\u00030æ\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010ç\u0001\u001a\u00030è\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bé\u0001\u0010ê\u0001R\u0016\u0010ë\u0001\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bì\u0001\u0010FR\u0012\u0010í\u0001\u001a\u00030î\u0001X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010ï\u0001\u001a\u00030ð\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010ñ\u0001\u001a\u00030ò\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010ó\u0001R\u0013\u0010ô\u0001\u001a\u00030ò\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010ó\u0001R\u0013\u0010õ\u0001\u001a\u00030ò\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010ó\u0001R+\u0010ö\u0001\u001a\u00030è\u00018\u0000@\u0000X\u0081\u000e¢\u0006\u0019\n\u0000\u0012\u0005\b÷\u0001\u0010\u001a\u001a\u0006\bø\u0001\u0010ê\u0001\"\u0006\bù\u0001\u0010ú\u0001R\u000f\u0010û\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010ü\u0001\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000f\u0010ý\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010ÿ\u0001\u001a\u0005\u0018\u00010þ\u00012\t\u0010:\u001a\u0005\u0018\u00010þ\u00018B@BX\u0082\u008e\u0002¢\u0006\u0017\n\u0005\b\u0084\u0002\u0010B\u001a\u0006\b\u0080\u0002\u0010\u0081\u0002\"\u0006\b\u0082\u0002\u0010\u0083\u0002R#\u0010\u0085\u0002\u001a\u0005\u0018\u00010þ\u00018FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\b\u0087\u0002\u0010\u0088\u0002\u001a\u0006\b\u0086\u0002\u0010\u0081\u0002R\u001f\u0010\u0089\u0002\u001a\u0012\u0012\u0005\u0012\u00030þ\u0001\u0012\u0004\u0012\u000207\u0018\u00010\u008a\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u008b\u0002\u001a\u00030\u008c\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u008d\u0002\u001a\u00030\u008e\u00028\u0016X\u0097\u0004¢\u0006\u0011\n\u0000\u0012\u0005\b\u008f\u0002\u0010\u001a\u001a\u0006\b\u0090\u0002\u0010\u0091\u0002R\u001a\u0010\u0092\u0002\u001a\n\u0012\u0005\u0012\u00030\u0094\u00020\u0093\u0002X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0095\u0002R\u0018\u0010\u0096\u0002\u001a\u00030\u0097\u0002X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0098\u0002\u0010\u0099\u0002R\u0018\u0010\u009a\u0002\u001a\u00030\u009b\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009c\u0002\u0010\u009d\u0002R!\u0010§\u0002\u001a\u00030¨\u00028\u0016X\u0097\u0004¢\u0006\u0011\n\u0000\u0012\u0005\b©\u0002\u0010\u001a\u001a\u0006\bª\u0002\u0010«\u0002R3\u0010\u00ad\u0002\u001a\u00030¬\u00022\u0007\u0010:\u001a\u00030¬\u00028V@RX\u0096\u008e\u0002¢\u0006\u0017\n\u0005\b²\u0002\u0010B\u001a\u0006\b®\u0002\u0010¯\u0002\"\u0006\b°\u0002\u0010±\u0002R\u001c\u0010³\u0002\u001a\u00020N*\u00030´\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b´\u0002\u0010µ\u0002R3\u0010·\u0002\u001a\u00030¶\u00022\u0007\u0010:\u001a\u00030¶\u00028V@RX\u0096\u008e\u0002¢\u0006\u0017\n\u0005\b¼\u0002\u0010B\u001a\u0006\b¸\u0002\u0010¹\u0002\"\u0006\bº\u0002\u0010»\u0002R\u0018\u0010½\u0002\u001a\u00030¾\u0002X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b¿\u0002\u0010À\u0002R\u0010\u0010Á\u0002\u001a\u00030Â\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010Ã\u0002\u001a\u00030Ä\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\bÅ\u0002\u0010Æ\u0002R\u0018\u0010Ç\u0002\u001a\u00030È\u0002X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÉ\u0002\u0010Ê\u0002R\u0018\u0010Ë\u0002\u001a\u00030Ì\u0002X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÍ\u0002\u0010Î\u0002R\u0012\u0010Ï\u0002\u001a\u0005\u0018\u00010Ð\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Ñ\u0002\u001a\u00030è\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010Ò\u0002\u001a\n\u0012\u0005\u0012\u00030¬\u00010Ó\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010Ô\u0002\u001a\u0011\u0012\f\u0012\n\u0012\u0004\u0012\u000207\u0018\u0001060«\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010Õ\u0002\u001a\u00030Ö\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010×\u0002\u001a\u00030Ö\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010Ø\u0002\u001a\u00030Ù\u0002X\u0082\u0004¢\u0006\u0005\n\u0003\u0010Ú\u0002R\u000f\u0010Û\u0002\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010Ü\u0002\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Ý\u0002\u001a\u00030Þ\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010ß\u0002\u001a\b\u0012\u0004\u0012\u00020706X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010à\u0002\u001a\u00030á\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010â\u0002\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010î\u0002\u001a\u0005\u0018\u00010ï\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010ð\u0002\u001a\u00020\u00168@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bñ\u0002\u0010FR\u0011\u0010ï\u0003\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010ä\u0004\u001a\u00030å\u0004X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bæ\u0004\u0010ç\u0004R\u0016\u0010é\u0004\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bé\u0004\u0010FR\u000f\u0010ë\u0004\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010î\u0004\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010ñ\u0004\u001a\u0004\u0018\u00010\u00008VX\u0096\u0004¢\u0006\b\u001a\u0006\bò\u0004\u0010ó\u0004¨\u0006\u0082\u0005"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView;", "Landroid/view/ViewGroup;", "Landroidx/compose/ui/node/Owner;", "Landroidx/compose/ui/focus/PlatformFocusOwner;", "Landroidx/compose/ui/platform/ViewRootForTest;", "Landroidx/compose/ui/input/pointer/MatrixPositionCalculator;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroidx/compose/ui/node/OutOfFrameExecutor;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "Landroid/view/ViewTreeObserver$OnScrollChangedListener;", "Landroid/view/ViewTreeObserver$OnTouchModeChangeListener;", "Landroidx/compose/ui/focus/FocusListener;", "context", "Landroid/content/Context;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Landroid/content/Context;Lkotlin/coroutines/CoroutineContext;)V", "lastDownPointerPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "superclassInitComplete", "", "primaryDirectionalMotionAxisOverride", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "getPrimaryDirectionalMotionAxisOverride-dqNNBbU$ui$annotations", "()V", "getPrimaryDirectionalMotionAxisOverride-dqNNBbU$ui", "()Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "setPrimaryDirectionalMotionAxisOverride-r2epLt8$ui", "(Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;)V", "sharedDrawScope", "Landroidx/compose/ui/node/LayoutNodeDrawScope;", "getSharedDrawScope", "()Landroidx/compose/ui/node/LayoutNodeDrawScope;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "frameEndScheduler", "Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$FrameEndScheduler;", "getFrameEndScheduler$ui", "()Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$FrameEndScheduler;", "setFrameEndScheduler$ui", "(Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$FrameEndScheduler;)V", "lifecycleRetainedValuesStoreOwnerEntry", "Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$RetainedValuesStoreEntry;", "value", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "retainedValuesStore", "getRetainedValuesStore", "()Landroidx/compose/runtime/retain/RetainedValuesStore;", "outOfFrameQueue", "Lkotlin/collections/ArrayDeque;", "Lkotlin/Function0;", "", "outOfFrameRunnable", "Ljava/lang/Runnable;", "<set-?>", "Landroidx/compose/ui/unit/Density;", "density", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "density$delegate", "Landroidx/compose/runtime/MutableState;", "frameRateCategoryView", "isArrEnabled", "isArrEnabled$ui", "()Z", "rootSemanticsNode", "Landroidx/compose/ui/semantics/EmptySemanticsModifier;", "focusOwner", "Landroidx/compose/ui/focus/FocusOwner;", "getFocusOwner", "()Landroidx/compose/ui/focus/FocusOwner;", "getImportantForAutofill", "", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "setCoroutineContext", "(Lkotlin/coroutines/CoroutineContext;)V", "dragAndDropManager", "Landroidx/compose/ui/draganddrop/AndroidDragAndDropManager;", "getDragAndDropManager", "()Landroidx/compose/ui/draganddrop/AndroidDragAndDropManager;", "_windowInfo", "Landroidx/compose/ui/platform/LazyWindowInfo;", "windowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "getWindowInfo", "()Landroidx/compose/ui/platform/WindowInfo;", "processingRequestFocusForNextNonChildView", "moveFocusInChildrenCurrent", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "moveFocusInChildrenCurrent-3ESFkO8", "(I)Z", "moveFocusInChildrenViewFocusFix", "moveFocusInChildrenViewFocusFix-3ESFkO8", "moveFocusInChildrenBypassUnfocusableComposeView", "moveFocusInChildrenBypassUnfocusableComposeView-3ESFkO8", "moveFocusInChildren", "moveFocusInChildren-3ESFkO8", "findNextViewInEmbeddedView", "findNextViewInEmbeddedView-3ESFkO8", "(I)Landroid/view/View;", "getEmbeddedViewFocusRect", "Landroidx/compose/ui/geometry/Rect;", "focusTargetAvailable", "findNextNonChildView", "direction", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "insetsListener", "Landroidx/compose/ui/layout/InsetsListener;", "getInsetsListener", "()Landroidx/compose/ui/layout/InsetsListener;", "root", "Landroidx/compose/ui/node/LayoutNode;", "getRoot$annotations", "getRoot", "()Landroidx/compose/ui/node/LayoutNode;", "layoutNodes", "Landroidx/collection/MutableIntObjectMap;", "getLayoutNodes", "()Landroidx/collection/MutableIntObjectMap;", "rectManager", "Landroidx/compose/ui/spatial/RectManager;", "getRectManager", "()Landroidx/compose/ui/spatial/RectManager;", "rootForTest", "Landroidx/compose/ui/node/RootForTest;", "getRootForTest", "()Landroidx/compose/ui/node/RootForTest;", "uncaughtExceptionHandler", "Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;", "getUncaughtExceptionHandler$ui", "()Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;", "setUncaughtExceptionHandler$ui", "(Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;)V", "semanticsOwner", "Landroidx/compose/ui/semantics/SemanticsOwner;", "getSemanticsOwner", "()Landroidx/compose/ui/semantics/SemanticsOwner;", "composeAccessibilityDelegate", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "contentCaptureManager", "Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;", "getContentCaptureManager$ui", "()Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;", "setContentCaptureManager$ui", "(Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;)V", "accessibilityManager", "Landroidx/compose/ui/platform/AndroidAccessibilityManager;", "getAccessibilityManager", "()Landroidx/compose/ui/platform/AndroidAccessibilityManager;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "getGraphicsContext", "()Landroidx/compose/ui/graphics/GraphicsContext;", "autofillTree", "Landroidx/compose/ui/autofill/AutofillTree;", "getAutofillTree", "()Landroidx/compose/ui/autofill/AutofillTree;", "dirtyLayers", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/node/OwnedLayer;", "postponedDirtyLayers", "isDrawingContent", "isPendingInteropViewLayoutChangeDispatch", "motionEventAdapter", "Landroidx/compose/ui/input/pointer/MotionEventAdapter;", "pointerInputEventProcessor", "Landroidx/compose/ui/input/pointer/PointerInputEventProcessor;", "Landroid/content/res/Configuration;", "configuration", "getConfiguration", "()Landroid/content/res/Configuration;", "setConfiguration", "(Landroid/content/res/Configuration;)V", "configuration$delegate", "_autofill", "Landroidx/compose/ui/autofill/AndroidAutofill;", "_autofillManager", "Landroidx/compose/ui/autofill/AndroidAutofillManager;", "get_autofillManager$ui", "()Landroidx/compose/ui/autofill/AndroidAutofillManager;", "autofill", "Landroidx/compose/ui/autofill/Autofill;", "getAutofill", "()Landroidx/compose/ui/autofill/Autofill;", "autofillManager", "Landroidx/compose/ui/autofill/AutofillManager;", "getAutofillManager", "()Landroidx/compose/ui/autofill/AutofillManager;", "observationClearRequested", "clipboardManager", "Landroidx/compose/ui/platform/AndroidClipboardManager;", "getClipboardManager", "()Landroidx/compose/ui/platform/AndroidClipboardManager;", "clipboard", "Landroidx/compose/ui/platform/AndroidClipboard;", "getClipboard", "()Landroidx/compose/ui/platform/AndroidClipboard;", "snapshotObserver", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "showLayoutBounds", "getShowLayoutBounds$annotations", "getShowLayoutBounds", "setShowLayoutBounds", "(Z)V", "_androidViewsHandler", "Landroidx/compose/ui/platform/AndroidViewsHandler;", "androidViewsHandler", "getAndroidViewsHandler$ui", "()Landroidx/compose/ui/platform/AndroidViewsHandler;", "viewLayersContainer", "Landroidx/compose/ui/platform/DrawChildContainer;", "onMeasureConstraints", "Landroidx/compose/ui/unit/Constraints;", "wasMeasuredWithMultipleConstraints", "measureAndLayoutDelegate", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate;", "measureIteration", "", "getMeasureIteration", "()J", "hasPendingMeasureOrLayout", "getHasPendingMeasureOrLayout", "globalPosition", "Landroidx/compose/ui/unit/IntOffset;", "tmpPositionArray", "", "tmpMatrix", "Landroidx/compose/ui/graphics/Matrix;", "[F", "viewToWindowMatrix", "windowToViewMatrix", "lastMatrixRecalculationAnimationTime", "getLastMatrixRecalculationAnimationTime$ui$annotations", "getLastMatrixRecalculationAnimationTime$ui", "setLastMatrixRecalculationAnimationTime$ui", "(J)V", "forceUseMatrixCache", "windowPosition", "isRenderNodeCompatible", "Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "_viewTreeOwners", "get_viewTreeOwners", "()Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "set_viewTreeOwners", "(Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;)V", "_viewTreeOwners$delegate", "viewTreeOwners", "getViewTreeOwners", "viewTreeOwners$delegate", "Landroidx/compose/runtime/State;", "onViewTreeOwnersAvailable", "Lkotlin/Function1;", "legacyTextInputServiceAndroid", "Landroidx/compose/ui/text/input/TextInputServiceAndroid;", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "getTextInputService$annotations", "getTextInputService", "()Landroidx/compose/ui/text/input/TextInputService;", "textInputSessionMutex", "Landroidx/compose/ui/SessionMutex;", "Landroidx/compose/ui/platform/AndroidPlatformTextInputSession;", "Ljava/util/concurrent/atomic/AtomicReference;", "softwareKeyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "getSoftwareKeyboardController", "()Landroidx/compose/ui/platform/SoftwareKeyboardController;", "placementScope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getPlacementScope", "()Landroidx/compose/ui/layout/Placeable$PlacementScope;", "textInputSession", "", "session", "Lkotlin/Function2;", "Landroidx/compose/ui/platform/PlatformTextInputSessionScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fontLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "getFontLoader$annotations", "getFontLoader", "()Landroidx/compose/ui/text/font/Font$ResourceLoader;", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver", "getFontFamilyResolver", "()Landroidx/compose/ui/text/font/FontFamily$Resolver;", "setFontFamilyResolver", "(Landroidx/compose/ui/text/font/FontFamily$Resolver;)V", "fontFamilyResolver$delegate", "fontWeightAdjustmentCompat", "getFontWeightAdjustmentCompat", "(Landroid/content/res/Configuration;)I", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "layoutDirection$delegate", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "_inputModeManager", "Landroidx/compose/ui/input/InputModeManagerImpl;", "inputModeManager", "Landroidx/compose/ui/input/InputModeManager;", "getInputModeManager", "()Landroidx/compose/ui/input/InputModeManager;", "modifierLocalManager", "Landroidx/compose/ui/modifier/ModifierLocalManager;", "getModifierLocalManager", "()Landroidx/compose/ui/modifier/ModifierLocalManager;", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "previousMotionEvent", "Landroid/view/MotionEvent;", "relayoutTime", "layerCache", "Landroidx/compose/ui/platform/WeakCache;", "endApplyChangesListeners", "currentFrameRate", "", "currentFrameRateCategory", "resendMotionEventRunnable", "androidx/compose/ui/platform/AndroidComposeView$resendMotionEventRunnable$1", "Landroidx/compose/ui/platform/AndroidComposeView$resendMotionEventRunnable$1;", "sendHoverExitEvent", "hoverExitReceived", "indirectPointerNavigationGestureDetector", "Landroidx/compose/ui/platform/IndirectPointerNavigationGestureDetector;", "resendMotionEventOnLayout", "matrixToWindow", "Landroidx/compose/ui/platform/CalculateMatrixToWindow;", "keyboardModifiersRequireUpdate", "getFocusedRect", "rect", "Landroid/graphics/Rect;", "addFocusables", "views", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "focusableMode", "dispatchProvideStructure", "structure", "Landroid/view/ViewStructure;", "scrollCapture", "Landroidx/compose/ui/scrollcapture/ScrollCapture;", "scrollCaptureInProgress", "getScrollCaptureInProgress$ui", "onScrollCaptureSearch", "localVisibleRect", "windowOffset", "Landroid/graphics/Point;", "targets", "Ljava/util/function/Consumer;", "Landroid/view/ScrollCaptureTarget;", "onResume", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "focusSearch", "focused", "requestFocusCurrent", "previouslyFocusedRect", "requestFocusViewFocusFix", "requestFocusBypassUnfocusableComposeView", "requestFocus", "requestOwnerFocus", "requestOwnerFocus-7o62pno", "clearOwnerFocus", "onFocusChanged", "gainFocus", "previous", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "current", "onWindowFocusChanged", "hasWindowFocus", "sendKeyEvent", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "sendKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "sendIndirectPointerEvent", "indirectPointerEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "dispatchKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "dispatchKeyEventPreIme", "forceAccessibilityForTesting", "enable", "setAccessibilityEventBatchIntervalMillis", "intervalMillis", "onPreAttach", "node", "onPostAttach", "onDetach", "requestAutofill", "requestClearInvalidObservations", "onEndApplyChanges", "registerOnEndApplyChangesListener", "listener", "startDrag", "transferData", "Landroidx/compose/ui/draganddrop/DragAndDropTransferData;", "decorationSize", "Landroidx/compose/ui/geometry/Size;", "drawDragDecoration", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "startDrag-12SF9DM", "(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;JLkotlin/jvm/functions/Function1;)Z", "clearChildInvalidObservations", "viewGroup", "addExtraDataToAccessibilityNodeInfoHelper", "virtualViewId", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "extraDataKey", "", "addView", "child", "index", "width", "height", "params", "Landroid/view/ViewGroup$LayoutParams;", "addAndroidView", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "layoutNode", "removeAndroidView", "drawAndroidView", "canvas", "Landroid/graphics/Canvas;", "scheduleMeasureAndLayout", "nodeToRemeasure", "childSizeCanAffectParentSize", "measureAndLayout", "sendPointerUpdate", "constraints", "measureAndLayout-0kLqBqw", "(Landroidx/compose/ui/node/LayoutNode;J)V", "dispatchPendingInteropLayoutCallbacks", "forceMeasureTheSubtree", "affectsLookahead", "onRequestMeasure", "forceRequest", "onRequestRelayout", "requestOnPositionedCallback", "measureAndLayoutForTest", "setUncaughtExceptionHandler", "handler", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "component1", "Lkotlin/ULong;", "component1-VKZWuLQ", "(J)I", "component2", "component2-VKZWuLQ", "pack", "a", "b", "pack-ZIaKswc", "(II)J", "convertMeasureSpec", "measureSpec", "convertMeasureSpec-I7RO_PI", "(I)J", "onLayout", "changed", "l", "t", "r", "_rootView", "updatePositionCacheAndDispatch", "onDraw", "createLayer", "drawBlock", "Landroidx/compose/ui/graphics/Canvas;", "Lkotlin/ParameterName;", "name", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "parentLayer", "invalidateParentLayer", "explicitLayer", "recycle", "layer", "recycle$ui", "onSemanticsChange", "onLayoutChange", "onLayoutNodeDeactivated", "onPreLayoutNodeReused", "oldSemanticsId", "onPostLayoutNodeReused", "onInteropViewLayoutChange", "Landroidx/compose/ui/viewinterop/InteropView;", "registerOnLayoutCompletedListener", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "dispatchDraw", "notifyLayerIsDirty", "isDirty", "notifyLayerIsDirty$ui", "setOnViewTreeOwnersAvailable", "callback", "boundsUpdatesContentCaptureEventLoop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "boundsUpdatesAccessibilityEventLoop", "invalidateLayoutNodeMeasurement", "invalidateLayers", "invalidateDescendants", "onAttachedToWindow", "installLocalRetainedValuesStore", "lifecycleOwner", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "onDetachedFromWindow", "onProvideAutofillVirtualStructure", "flags", "values", "Landroid/util/SparseArray;", "Landroid/view/autofill/AutofillValue;", "onCreateVirtualViewTranslationRequests", "virtualIds", "", "supportedFormats", "requestsCollector", "Landroid/view/translation/ViewTranslationRequest;", "onVirtualViewTranslationResponses", "response", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "dispatchGenericMotionEvent", "motionEvent", "handleIndirectPointerEvent", "dispatchTouchEvent", "handleRotaryEvent", "handleMotionEvent", "Landroidx/compose/ui/input/pointer/ProcessResult;", "handleMotionEvent-8iAsVTc", "(Landroid/view/MotionEvent;)I", "hasChangedDevices", "lastEvent", "isDevicePressEvent", "sendMotionEvent", "sendMotionEvent-8iAsVTc", "sendSimulatedEvent", "action", "eventTime", "forceHover", "canScrollHorizontally", "canScrollVertically", "isInBounds", "localToScreen", "localPosition", "localToScreen-MK-Hz9U", "(J)J", "localTransform", "localToScreen-58bKbWc", "([F)V", "screenToLocal", "positionOnScreen", "screenToLocal-MK-Hz9U", "recalculateWindowPosition", "recalculateWindowViewTransforms", "updateWindowMetrics", "onCheckIsTextEditor", "onCreateInputConnection", "Landroid/view/inputmethod/InputConnection;", "outAttrs", "Landroid/view/inputmethod/EditorInfo;", "calculateLocalPosition", "positionInWindow", "calculateLocalPosition-MK-Hz9U", "calculatePositionInWindow", "calculatePositionInWindow-MK-Hz9U", "onConfigurationChanged", "newConfig", "dispatchConfigurationChangeIfNeeded", "updateConfiguration", "onRtlPropertiesChanged", "autofillSupported", "dispatchHoverEvent", "isBadMotionEvent", "isPositionChanged", "findViewByAccessibilityIdRootedAtCurrentView", "accessibilityId", "currentView", "onResolvePointerIcon", "Landroid/view/PointerIcon;", "pointerIndex", "pointerIconService", "Landroidx/compose/ui/input/pointer/PointerIconService;", "getPointerIconService", "()Landroidx/compose/ui/input/pointer/PointerIconService;", "findViewByAccessibilityIdTraversal", "isLifecycleInResumedState", "shouldDelayChildPressedState", "sensitiveComponentCount", "incrementSensitiveComponentCount", "decrementSensitiveComponentCount", "keepScreenOnCount", "incrementKeepScreenOnCount", "decrementKeepScreenOnCount", "outOfFrameExecutor", "getOutOfFrameExecutor", "()Landroidx/compose/ui/platform/AndroidComposeView;", "schedule", "block", "voteFrameRate", "frameRate", "dispatchOnScrollChanged", "delta", "dispatchOnScrollChanged-k-4lQ0M", "onGlobalLayout", "onScrollChanged", "onTouchModeChanged", "isInTouchMode", "Companion", "ViewTreeOwners", "RootModifierNode", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidComposeView extends ViewGroup implements Owner, PlatformFocusOwner, ViewRootForTest, MatrixPositionCalculator, DefaultLifecycleObserver, OutOfFrameExecutor, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, ViewTreeObserver.OnTouchModeChangeListener, FocusListener {
    private static Method addChangeCallbackMethod;
    private static Method dispatchOnScrollChangedMethod;
    private static Method getBooleanMethod;
    private static Runnable systemPropertiesChangedRunnable;
    private static Class<?> systemPropertiesClass;
    private AndroidViewsHandler _androidViewsHandler;
    private final AndroidAutofill _autofill;
    private final AndroidAutofillManager _autofillManager;
    private final InputModeManagerImpl _inputModeManager;
    private View _rootView;

    /* JADX INFO: renamed from: _viewTreeOwners$delegate, reason: from kotlin metadata */
    private final MutableState _viewTreeOwners;
    private final LazyWindowInfo _windowInfo;
    private final AndroidAccessibilityManager accessibilityManager;
    private final AutofillTree autofillTree;
    private final CanvasHolder canvasHolder;
    private final AndroidClipboard clipboard;
    private final AndroidClipboardManager clipboardManager;
    private final AndroidComposeViewAccessibilityDelegateCompat composeAccessibilityDelegate;

    /* JADX INFO: renamed from: configuration$delegate, reason: from kotlin metadata */
    private final MutableState configuration;
    private AndroidContentCaptureManager contentCaptureManager;
    private CoroutineContext coroutineContext;
    private float currentFrameRate;
    private float currentFrameRateCategory;

    /* JADX INFO: renamed from: density$delegate, reason: from kotlin metadata */
    private final MutableState density;
    private final MutableObjectList<OwnedLayer> dirtyLayers;
    private final AndroidDragAndDropManager dragAndDropManager;
    private final MutableObjectList<Function0<Unit>> endApplyChangesListeners;
    private final FocusOwner focusOwner;

    /* JADX INFO: renamed from: fontFamilyResolver$delegate, reason: from kotlin metadata */
    private final MutableState fontFamilyResolver;
    private final Font.ResourceLoader fontLoader;
    private boolean forceUseMatrixCache;
    private LifecycleRetainedValuesStoreOwner.FrameEndScheduler frameEndScheduler;
    private View frameRateCategoryView;
    private long globalPosition;
    private final GraphicsContext graphicsContext;
    private final HapticFeedback hapticFeedBack;
    private boolean hoverExitReceived;
    private final IndirectPointerNavigationGestureDetector indirectPointerNavigationGestureDetector;
    private final InsetsListener insetsListener;
    private final boolean isArrEnabled;
    private boolean isDrawingContent;
    private boolean isPendingInteropViewLayoutChangeDispatch;
    private boolean isRenderNodeCompatible;
    private int keepScreenOnCount;
    private boolean keyboardModifiersRequireUpdate;
    private long lastDownPointerPosition;
    private long lastMatrixRecalculationAnimationTime;
    private final WeakCache<OwnedLayer> layerCache;

    /* JADX INFO: renamed from: layoutDirection$delegate, reason: from kotlin metadata */
    private final MutableState layoutDirection;
    private final MutableIntObjectMap<LayoutNode> layoutNodes;
    private final TextInputServiceAndroid legacyTextInputServiceAndroid;
    private LifecycleRetainedValuesStoreOwner.RetainedValuesStoreEntry lifecycleRetainedValuesStoreOwnerEntry;
    private final CalculateMatrixToWindow matrixToWindow;
    private final MeasureAndLayoutDelegate measureAndLayoutDelegate;
    private final ModifierLocalManager modifierLocalManager;
    private final MotionEventAdapter motionEventAdapter;
    private boolean observationClearRequested;
    private Constraints onMeasureConstraints;
    private Function1<? super ViewTreeOwners, Unit> onViewTreeOwnersAvailable;
    private final ArrayDeque<Function0<Unit>> outOfFrameQueue;
    private final Runnable outOfFrameRunnable;
    private final PointerIconService pointerIconService;
    private final PointerInputEventProcessor pointerInputEventProcessor;
    private MutableObjectList<OwnedLayer> postponedDirtyLayers;
    private MotionEvent previousMotionEvent;
    private IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxisOverride;
    private boolean processingRequestFocusForNextNonChildView;
    private final RectManager rectManager;
    private long relayoutTime;
    private final Function0<Unit> resendMotionEventOnLayout;
    private final AndroidComposeView$resendMotionEventRunnable$1 resendMotionEventRunnable;
    private RetainedValuesStore retainedValuesStore;
    private final LayoutNode root;
    private final RootForTest rootForTest;
    private final EmptySemanticsModifier rootSemanticsNode;
    private final ScrollCapture scrollCapture;
    private final SemanticsOwner semanticsOwner;
    private final Runnable sendHoverExitEvent;
    private int sensitiveComponentCount;
    private final LayoutNodeDrawScope sharedDrawScope;
    private boolean showLayoutBounds;
    private final OwnerSnapshotObserver snapshotObserver;
    private final SoftwareKeyboardController softwareKeyboardController;
    private boolean superclassInitComplete;
    private final TextInputService textInputService;
    private final AtomicReference<SessionMutex.Session<AndroidPlatformTextInputSession>> textInputSessionMutex;
    private final TextToolbar textToolbar;
    private final float[] tmpMatrix;
    private final int[] tmpPositionArray;
    private RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler;
    private final ViewConfiguration viewConfiguration;
    private DrawChildContainer viewLayersContainer;
    private final float[] viewToWindowMatrix;

    /* JADX INFO: renamed from: viewTreeOwners$delegate, reason: from kotlin metadata */
    private final State viewTreeOwners;
    private boolean wasMeasuredWithMultipleConstraints;
    private long windowPosition;
    private final float[] windowToViewMatrix;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final MutableObjectList<AndroidComposeView> composeViews = new MutableObjectList<>(0, 1, (DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\bB\u0007¢\u0006\u0004\b\t\u0010\nJ#\u00103\u001a\u000204*\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0016¢\u0006\u0004\b:\u0010;J\f\u0010?\u001a\u00020-*\u00020@H\u0016J&\u0010A\u001a\u00020-2\u0006\u0010B\u001a\u00020C2\u000e\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010F0EH\u0096@¢\u0006\u0002\u0010GJ\u0010\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020KH\u0016J\u0010\u0010L\u001a\u00020I2\u0006\u0010J\u001a\u00020KH\u0016J\u0017\u0010M\u001a\u00020I2\u0006\u0010J\u001a\u00020NH\u0016¢\u0006\u0004\bO\u0010PJ\u0017\u0010Q\u001a\u00020I2\u0006\u0010J\u001a\u00020NH\u0016¢\u0006\u0004\bR\u0010PR \u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R(\u0010*\u001a\u0013\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0+¢\u0006\u0002\b.¢\u0006\u000e\n\u0000\u0012\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0014\u0010<\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>¨\u0006S"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$RootModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/relocation/BringIntoViewModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/input/rotary/RotaryInputModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/layout/WindowInsetsRulerProvider;", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;)V", "insetsValues", "Landroidx/collection/ScatterMap;", "", "Landroidx/compose/ui/layout/WindowWindowInsetsAnimationValues;", "getInsetsValues", "()Landroidx/collection/ScatterMap;", "generation", "Landroidx/compose/runtime/MutableIntState;", "getGeneration", "()Landroidx/compose/runtime/MutableIntState;", "previousGeneration", "", "getPreviousGeneration", "()I", "setPreviousGeneration", "(I)V", "cutoutRects", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/runtime/MutableState;", "Landroid/graphics/Rect;", "getCutoutRects", "()Landroidx/collection/MutableObjectList;", "cutoutRulers", "", "Landroidx/compose/ui/layout/RectRulers;", "getCutoutRulers", "()Ljava/util/List;", "insetsListener", "Landroidx/compose/ui/layout/InsetsListener;", "getInsetsListener", "()Landroidx/compose/ui/layout/InsetsListener;", "rulerLambda", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/RulerScope;", "", "Lkotlin/ExtensionFunctionType;", "getRulerLambda$annotations", "()V", "getRulerLambda", "()Lkotlin/jvm/functions/Function1;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "traverseKey", "getTraverseKey", "()Ljava/lang/Object;", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "bringIntoView", "childCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "boundsProvider", "Lkotlin/Function0;", "Landroidx/compose/ui/geometry/Rect;", "(Landroidx/compose/ui/layout/LayoutCoordinates;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onRotaryScrollEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/rotary/RotaryScrollEvent;", "onPreRotaryScrollEvent", "onPreKeyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onPreKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onKeyEvent", "onKeyEvent-ZmokQxo", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public final class RootModifierNode extends Modifier.Node implements BringIntoViewModifierNode, SemanticsModifierNode, RotaryInputModifierNode, KeyInputModifierNode, LayoutModifierNode, TraversableNode, WindowInsetsRulerProvider {
        private int previousGeneration = -1;
        private final Function1<RulerScope, Unit> rulerLambda = new Function1<RulerScope, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$RootModifierNode$rulerLambda$1
            {
                super(1);
            }

            public final void invoke(RulerScope rulerScope) {
                AndroidComposeView.RootModifierNode rootModifierNode = this.this$0;
                rootModifierNode.setPreviousGeneration(rootModifierNode.getGeneration().getIntValue());
                if (this.this$0.getPreviousGeneration() <= 0 || !ComposeUiFlags.areWindowInsetsRulersEnabled) {
                    return;
                }
                WindowInsetsRulers_androidKt.provideWindowInsetsRulers(rulerScope, this.this$0);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((RulerScope) obj);
                return Unit.INSTANCE;
            }
        };

        public RootModifierNode() {
        }

        public static /* synthetic */ void getRulerLambda$annotations() {
        }

        @Override // androidx.compose.ui.node.SemanticsModifierNode
        public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        }

        @Override // androidx.compose.ui.relocation.BringIntoViewModifierNode
        public Object bringIntoView(LayoutCoordinates layoutCoordinates, Function0<Rect> function0, Continuation<? super Unit> continuation) {
            long jPositionInRoot = LayoutCoordinatesKt.positionInRoot(layoutCoordinates);
            Rect rect = (Rect) function0.invoke();
            Rect rectM2926translatek4lQ0M = rect != null ? rect.m2926translatek4lQ0M(jPositionInRoot) : null;
            if (rectM2926translatek4lQ0M != null) {
                AndroidComposeView.this.requestRectangleOnScreen(RectHelper_androidKt.toAndroidRect(rectM2926translatek4lQ0M), false);
            }
            return Unit.INSTANCE;
        }

        @Override // androidx.compose.ui.layout.WindowInsetsRulerProvider
        public MutableObjectList<MutableState<android.graphics.Rect>> getCutoutRects() {
            return getInsetsListener().getDisplayCutouts();
        }

        @Override // androidx.compose.ui.layout.WindowInsetsRulerProvider
        public List<RectRulers> getCutoutRulers() {
            return getInsetsListener().getDisplayCutoutRulers();
        }

        public final MutableIntState getGeneration() {
            return getInsetsListener().getGeneration();
        }

        @Override // androidx.compose.ui.layout.WindowInsetsRulerProvider
        public InsetsListener getInsetsListener() {
            return AndroidComposeView.this.getInsetsListener();
        }

        @Override // androidx.compose.ui.layout.WindowInsetsRulerProvider
        public ScatterMap<Object, WindowWindowInsetsAnimationValues> getInsetsValues() {
            return getInsetsListener().getInsetsValues();
        }

        public final int getPreviousGeneration() {
            return this.previousGeneration;
        }

        public final Function1<RulerScope, Unit> getRulerLambda() {
            return this.rulerLambda;
        }

        @Override // androidx.compose.ui.node.TraversableNode
        public Object getTraverseKey() {
            return WindowInsetsRulers_androidKt.RulerKey;
        }

        @Override // androidx.compose.ui.node.LayoutModifierNode
        /* JADX INFO: renamed from: measure-3p2s80s */
        public MeasureResult mo628measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
            final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(j);
            return MeasureScope.layout$default(measureScope, placeableMo4605measureBRTryo0.getWidth(), placeableMo4605measureBRTryo0.getHeight(), null, this.rulerLambda, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$RootModifierNode$measure$1
                {
                    super(1);
                }

                public final void invoke(Placeable.PlacementScope placementScope) {
                    Placeable.PlacementScope.place$default(placementScope, placeableMo4605measureBRTryo0, 0, 0, 0.0f, 4, null);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Placeable.PlacementScope) obj);
                    return Unit.INSTANCE;
                }
            }, 4, null);
        }

        @Override // androidx.compose.ui.input.key.KeyInputModifierNode
        /* JADX INFO: renamed from: onKeyEvent-ZmokQxo */
        public boolean mo4302onKeyEventZmokQxo(KeyEvent event) {
            Boolean boolMo2779focusSearchULY8qGw;
            final FocusDirection focusDirectionM2772toFocusDirectionZmokQxo = FocusInteropUtils_androidKt.m2772toFocusDirectionZmokQxo(event);
            if (focusDirectionM2772toFocusDirectionZmokQxo == null || !KeyEventType.m4288equalsimpl0(KeyEvent_androidKt.m4296getTypeZmokQxo(event), KeyEventType.INSTANCE.m4292getKeyDownCS__XNY())) {
                return false;
            }
            if (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
                FocusTargetNode activeFocusTargetNode = AndroidComposeView.this.getFocusOwner().getActiveFocusTargetNode();
                if (activeFocusTargetNode != null && activeFocusTargetNode.getIsInteropViewHost() && AndroidComposeView.this.mo2830moveFocusInChildren3ESFkO8(focusDirectionM2772toFocusDirectionZmokQxo.getValue())) {
                    return true;
                }
                Boolean boolMo2779focusSearchULY8qGw2 = AndroidComposeView.this.getFocusOwner().mo2779focusSearchULY8qGw(focusDirectionM2772toFocusDirectionZmokQxo.getValue(), AndroidComposeView.this.getEmbeddedViewFocusRect(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$RootModifierNode$onKeyEvent$focusWasMovedOrCancelled$1
                    {
                        super(1);
                    }

                    public final Boolean invoke(FocusTargetNode focusTargetNode) {
                        return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(focusDirectionM2772toFocusDirectionZmokQxo.getValue()));
                    }
                });
                if (boolMo2779focusSearchULY8qGw2 != null ? boolMo2779focusSearchULY8qGw2.booleanValue() : true) {
                    return true;
                }
                if (FocusOwnerImplKt.m2789is1dFocusSearch3ESFkO8(focusDirectionM2772toFocusDirectionZmokQxo.getValue())) {
                    Integer numM2771toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m2771toAndroidFocusDirection3ESFkO8(focusDirectionM2772toFocusDirectionZmokQxo.getValue());
                    int iIntValue = numM2771toAndroidFocusDirection3ESFkO8 != null ? numM2771toAndroidFocusDirection3ESFkO8.intValue() : 2;
                    FocusFinder focusFinder = FocusFinder.getInstance();
                    View rootView = AndroidComposeView.this.getRootView();
                    rootView.getClass();
                    View viewFindNextFocus = focusFinder.findNextFocus((ViewGroup) rootView, AndroidComposeView.this.getView(), iIntValue);
                    if (viewFindNextFocus == null || Intrinsics.areEqual(viewFindNextFocus, AndroidComposeView.this)) {
                        return AndroidComposeView.this.getFocusOwner().mo2782resetFocus3ESFkO8(focusDirectionM2772toFocusDirectionZmokQxo.getValue());
                    }
                }
                return false;
            }
            Integer numM2771toAndroidFocusDirection3ESFkO9 = FocusInteropUtils_androidKt.m2771toAndroidFocusDirection3ESFkO8(focusDirectionM2772toFocusDirectionZmokQxo.getValue());
            if (ComposeUiFlags.isViewFocusFixEnabled && AndroidComposeView.this.hasFocus() && numM2771toAndroidFocusDirection3ESFkO9 != null && AndroidComposeView.this.mo2830moveFocusInChildren3ESFkO8(focusDirectionM2772toFocusDirectionZmokQxo.getValue())) {
                return true;
            }
            Rect embeddedViewFocusRect = AndroidComposeView.this.getEmbeddedViewFocusRect();
            Boolean boolMo2779focusSearchULY8qGw3 = AndroidComposeView.this.getFocusOwner().mo2779focusSearchULY8qGw(focusDirectionM2772toFocusDirectionZmokQxo.getValue(), embeddedViewFocusRect, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$RootModifierNode$onKeyEvent$focusWasMovedOrCancelled$2
                {
                    super(1);
                }

                public final Boolean invoke(FocusTargetNode focusTargetNode) {
                    return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(focusDirectionM2772toFocusDirectionZmokQxo.getValue()));
                }
            });
            if (boolMo2779focusSearchULY8qGw3 != null ? boolMo2779focusSearchULY8qGw3.booleanValue() : true) {
                return true;
            }
            if (!FocusOwnerImplKt.m2789is1dFocusSearch3ESFkO8(focusDirectionM2772toFocusDirectionZmokQxo.getValue())) {
                return false;
            }
            if (numM2771toAndroidFocusDirection3ESFkO9 != null) {
                View viewFindNextNonChildView = AndroidComposeView.this.findNextNonChildView(numM2771toAndroidFocusDirection3ESFkO9.intValue());
                if (Intrinsics.areEqual(viewFindNextNonChildView, AndroidComposeView.this)) {
                    viewFindNextNonChildView = null;
                }
                if (viewFindNextNonChildView != null) {
                    android.graphics.Rect androidRect = embeddedViewFocusRect != null ? RectHelper_androidKt.toAndroidRect(embeddedViewFocusRect) : null;
                    if (androidRect == null) {
                        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid rect");
                        wq6.a();
                        return false;
                    }
                    View rootView2 = AndroidComposeView.this.getRootView();
                    rootView2.getClass();
                    ViewGroup viewGroup = (ViewGroup) rootView2;
                    viewGroup.offsetDescendantRectToMyCoords(AndroidComposeView.this.getView(), androidRect);
                    viewGroup.offsetRectIntoDescendantCoords(viewFindNextNonChildView, androidRect);
                    if (FocusInteropUtils_androidKt.requestInteropFocus(viewFindNextNonChildView, numM2771toAndroidFocusDirection3ESFkO9, androidRect)) {
                        return true;
                    }
                }
            }
            if (AndroidComposeView.this.getFocusOwner().mo2776clearFocusI7lrPNg(false, true, false, focusDirectionM2772toFocusDirectionZmokQxo.getValue()) && (boolMo2779focusSearchULY8qGw = AndroidComposeView.this.getFocusOwner().mo2779focusSearchULY8qGw(focusDirectionM2772toFocusDirectionZmokQxo.getValue(), null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$RootModifierNode$onKeyEvent$1
                {
                    super(1);
                }

                public final Boolean invoke(FocusTargetNode focusTargetNode) {
                    return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(focusDirectionM2772toFocusDirectionZmokQxo.getValue()));
                }
            })) != null) {
                return boolMo2779focusSearchULY8qGw.booleanValue();
            }
            return true;
        }

        @Override // androidx.compose.ui.input.key.KeyInputModifierNode
        /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo */
        public boolean mo4303onPreKeyEventZmokQxo(KeyEvent event) {
            return false;
        }

        @Override // androidx.compose.ui.input.rotary.RotaryInputModifierNode
        public boolean onPreRotaryScrollEvent(RotaryScrollEvent event) {
            return false;
        }

        @Override // androidx.compose.ui.input.rotary.RotaryInputModifierNode
        public boolean onRotaryScrollEvent(RotaryScrollEvent event) {
            return false;
        }

        public final void setPreviousGeneration(int i) {
            this.previousGeneration = i;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "savedStateRegistryOwner", "Landroidx/savedstate/SavedStateRegistryOwner;", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/savedstate/SavedStateRegistryOwner;Landroidx/lifecycle/ViewModelStoreOwner;)V", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "getSavedStateRegistryOwner", "()Landroidx/savedstate/SavedStateRegistryOwner;", "getViewModelStoreOwner", "()Landroidx/lifecycle/ViewModelStoreOwner;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class ViewTreeOwners {
        public static final int $stable = 8;
        private final LifecycleOwner lifecycleOwner;
        private final SavedStateRegistryOwner savedStateRegistryOwner;
        private final ViewModelStoreOwner viewModelStoreOwner;

        public ViewTreeOwners(LifecycleOwner lifecycleOwner, SavedStateRegistryOwner savedStateRegistryOwner, ViewModelStoreOwner viewModelStoreOwner) {
            this.lifecycleOwner = lifecycleOwner;
            this.savedStateRegistryOwner = savedStateRegistryOwner;
            this.viewModelStoreOwner = viewModelStoreOwner;
        }

        public final LifecycleOwner getLifecycleOwner() {
            return this.lifecycleOwner;
        }

        public final SavedStateRegistryOwner getSavedStateRegistryOwner() {
            return this.savedStateRegistryOwner;
        }

        public final ViewModelStoreOwner getViewModelStoreOwner() {
            return this.viewModelStoreOwner;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeView$textInputSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.ui.platform.AndroidComposeView", f = "AndroidComposeView.android.kt", i = {}, l = {776}, m = "textInputSession", n = {}, s = {}, v = 1)
    public static final class C01071 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C01071(Continuation<? super C01071> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidComposeView.this.textInputSession(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v32, types: [androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1] */
    public AndroidComposeView(Context context, CoroutineContext coroutineContext) {
        final AndroidComposeView androidComposeView;
        AndroidAutofillManager androidAutofillManager;
        super(context);
        Offset.Companion companion = Offset.INSTANCE;
        this.lastDownPointerPosition = companion.m2904getUnspecifiedF1C5BW0();
        this.superclassInitComplete = true;
        CanvasDrawScope canvasDrawScope = null;
        this.sharedDrawScope = new LayoutNodeDrawScope(canvasDrawScope, 1, canvasDrawScope);
        this.retainedValuesStore = ForgetfulRetainedValuesStore.INSTANCE;
        this.outOfFrameQueue = new ArrayDeque<>();
        this.outOfFrameRunnable = new Runnable() { // from class: e50
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeView.a(this.b);
            }
        };
        this.density = SnapshotStateKt.mutableStateOf(AndroidDensity_androidKt.Density(context), SnapshotStateKt.referentialEqualityPolicy());
        boolean z = false;
        Object[] objArr = 0;
        boolean z2 = ComposeUiFlags.isAdaptiveRefreshRateEnabled && Build.VERSION.SDK_INT >= 35;
        this.isArrEnabled = z2;
        EmptySemanticsModifier emptySemanticsModifier = new EmptySemanticsModifier();
        this.rootSemanticsNode = emptySemanticsModifier;
        this.focusOwner = new FocusOwnerImpl(this, this);
        this.coroutineContext = coroutineContext;
        this.dragAndDropManager = new AndroidDragAndDropManager(new AndroidComposeView$dragAndDropManager$1(this));
        this._windowInfo = new LazyWindowInfo();
        this.canvasHolder = new CanvasHolder();
        this.viewConfiguration = new AndroidViewConfiguration(android.view.ViewConfiguration.get(context));
        this.insetsListener = new InsetsListener(this);
        LayoutNode layoutNode = new LayoutNode(z, objArr == true ? 1 : 0, 3, canvasDrawScope);
        layoutNode.setMeasurePolicy(RootMeasurePolicy.INSTANCE);
        layoutNode.setDensity(getDensity());
        layoutNode.setViewConfiguration(getViewConfiguration());
        layoutNode.setModifier(new ModifierNodeElement<RootModifierNode>() { // from class: androidx.compose.ui.platform.AndroidComposeView$root$1$1
            @Override // androidx.compose.ui.node.ModifierNodeElement
            /* JADX INFO: renamed from: create */
            public AndroidComposeView.RootModifierNode getNode() {
                return this.this$0.new RootModifierNode();
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public boolean equals(Object other) {
                return other == this;
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public int hashCode() {
                return this.this$0.hashCode();
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public void inspectableProperties(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("rootModifier");
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public void update(AndroidComposeView.RootModifierNode node) {
            }
        }.then(getFocusOwner().getModifier()).then(getDragAndDropManager().getModifier()));
        this.root = layoutNode;
        this.layoutNodes = IntObjectMapKt.mutableIntObjectMapOf();
        this.rectManager = new RectManager(getLayoutNodes());
        this.rootForTest = this;
        this.semanticsOwner = new SemanticsOwner(getRoot(), emptySemanticsModifier, getLayoutNodes());
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = new AndroidComposeViewAccessibilityDelegateCompat(this);
        this.composeAccessibilityDelegate = androidComposeViewAccessibilityDelegateCompat;
        this.contentCaptureManager = new AndroidContentCaptureManager(this, new AndroidComposeView$contentCaptureManager$1(this));
        this.accessibilityManager = new AndroidAccessibilityManager(context);
        this.graphicsContext = AndroidGraphicsContext_androidKt.GraphicsContext(this);
        this.autofillTree = new AutofillTree();
        this.dirtyLayers = new MutableObjectList<>(0, 1, (DefaultConstructorMarker) null);
        this.motionEventAdapter = new MotionEventAdapter();
        this.pointerInputEventProcessor = new PointerInputEventProcessor(getRoot());
        this.configuration = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new Configuration(context.getResources().getConfiguration()), null, 2, null);
        this._autofill = autofillSupported() ? new AndroidAutofill(this, getAutofillTree()) : null;
        if (autofillSupported()) {
            AutofillManager autofillManager = (AutofillManager) context.getSystemService(AutofillManager.class);
            if (autofillManager == null) {
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Autofill service could not be located.");
                wq6.a();
                throw null;
            }
            androidComposeView = this;
            androidAutofillManager = new AndroidAutofillManager(new PlatformAutofillManagerImpl(autofillManager), getSemanticsOwner(), androidComposeView, getRectManager(), context.getPackageName());
        } else {
            androidComposeView = this;
            androidAutofillManager = null;
        }
        androidComposeView._autofillManager = androidAutofillManager;
        androidComposeView.clipboardManager = new AndroidClipboardManager(context);
        androidComposeView.clipboard = new AndroidClipboard(androidComposeView.getClipboardManager());
        androidComposeView.snapshotObserver = new OwnerSnapshotObserver(new AndroidComposeView$snapshotObserver$1(androidComposeView));
        androidComposeView.measureAndLayoutDelegate = new MeasureAndLayoutDelegate(androidComposeView.getRoot());
        androidComposeView.globalPosition = IntOffset.m6144constructorimpl(androidx.compose.ui.geometry.InlineClassHelperKt.DualUnsignedFloatMask);
        androidComposeView.tmpPositionArray = new int[]{0, 0};
        androidComposeView.tmpMatrix = Matrix.m3378constructorimpl$default(null, 1, null);
        androidComposeView.viewToWindowMatrix = Matrix.m3378constructorimpl$default(null, 1, null);
        androidComposeView.windowToViewMatrix = Matrix.m3378constructorimpl$default(null, 1, null);
        androidComposeView.lastMatrixRecalculationAnimationTime = -1L;
        androidComposeView.windowPosition = companion.m2903getInfiniteF1C5BW0();
        androidComposeView.isRenderNodeCompatible = true;
        androidComposeView._viewTreeOwners = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        androidComposeView.viewTreeOwners = SnapshotStateKt.derivedStateOf(new Function0<ViewTreeOwners>() { // from class: androidx.compose.ui.platform.AndroidComposeView$viewTreeOwners$2
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final AndroidComposeView.ViewTreeOwners m5081invoke() {
                return this.this$0.get_viewTreeOwners();
            }
        });
        TextInputServiceAndroid textInputServiceAndroid = new TextInputServiceAndroid(androidComposeView.getView(), androidComposeView);
        androidComposeView.legacyTextInputServiceAndroid = textInputServiceAndroid;
        androidComposeView.textInputService = new TextInputService((PlatformTextInputService) AndroidComposeView_androidKt.getPlatformTextInputServiceInterceptor().invoke(textInputServiceAndroid));
        androidComposeView.textInputSessionMutex = SessionMutex.m2611constructorimpl();
        androidComposeView.softwareKeyboardController = new DelegatingSoftwareKeyboardController(androidComposeView.getTextInputService());
        androidComposeView.fontLoader = new AndroidFontResourceLoader(context);
        androidComposeView.fontFamilyResolver = SnapshotStateKt.mutableStateOf(FontFamilyResolver_androidKt.createFontFamilyResolver(context), SnapshotStateKt.referentialEqualityPolicy());
        LayoutDirection layoutDirection = FocusInteropUtils_androidKt.toLayoutDirection(context.getResources().getConfiguration().getLayoutDirection());
        androidComposeView.layoutDirection = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(layoutDirection == null ? LayoutDirection.Ltr : layoutDirection, null, 2, null);
        androidComposeView.hapticFeedBack = new PlatformHapticFeedback(androidComposeView);
        androidComposeView._inputModeManager = new InputModeManagerImpl(androidComposeView.isInTouchMode() ? InputMode.INSTANCE.m3950getTouchaOaMEAU() : InputMode.INSTANCE.m3949getKeyboardaOaMEAU(), new Function1<InputMode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$_inputModeManager$1
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m5072invokeiuPiT84(((InputMode) obj).getValue());
            }

            /* JADX INFO: renamed from: invoke-iuPiT84, reason: not valid java name */
            public final Boolean m5072invokeiuPiT84(int i) {
                boolean zRequestFocusFromTouch;
                InputMode.Companion companion2 = InputMode.INSTANCE;
                if (InputMode.m3945equalsimpl0(i, companion2.m3950getTouchaOaMEAU())) {
                    zRequestFocusFromTouch = this.this$0.isInTouchMode();
                } else if (InputMode.m3945equalsimpl0(i, companion2.m3949getKeyboardaOaMEAU())) {
                    zRequestFocusFromTouch = this.this$0.isInTouchMode() ? this.this$0.requestFocusFromTouch() : true;
                } else {
                    zRequestFocusFromTouch = false;
                }
                return Boolean.valueOf(zRequestFocusFromTouch);
            }
        }, canvasDrawScope);
        androidComposeView.modifierLocalManager = new ModifierLocalManager(androidComposeView);
        androidComposeView.textToolbar = new AndroidTextToolbar(androidComposeView);
        androidComposeView.layerCache = new WeakCache<>();
        androidComposeView.endApplyChangesListeners = new MutableObjectList<>(0, 1, (DefaultConstructorMarker) null);
        androidComposeView.resendMotionEventRunnable = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                this.this$0.removeCallbacks(this);
                MotionEvent motionEvent = this.this$0.previousMotionEvent;
                if (motionEvent != null) {
                    boolean z3 = motionEvent.getToolType(0) == 3;
                    int actionMasked = motionEvent.getActionMasked();
                    if (z3) {
                        if (actionMasked == 10 || actionMasked == 1) {
                            return;
                        }
                    } else if (actionMasked == 1) {
                        return;
                    }
                    int i = 7;
                    if (actionMasked != 7 && actionMasked != 9) {
                        i = 2;
                    }
                    AndroidComposeView androidComposeView2 = this.this$0;
                    androidComposeView2.sendSimulatedEvent(motionEvent, i, androidComposeView2.relayoutTime, false);
                }
            }
        };
        androidComposeView.sendHoverExitEvent = new Runnable() { // from class: f50
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeView.b(this.b);
            }
        };
        androidComposeView.indirectPointerNavigationGestureDetector = new IndirectPointerNavigationGestureDetector(context, new Function1<FocusDirection, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$indirectPointerNavigationGestureDetector$1
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m5077invoke3ESFkO8(((FocusDirection) obj).getValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-3ESFkO8, reason: not valid java name */
            public final void m5077invoke3ESFkO8(int i) {
                this.this$0.getFocusOwner().mo2780moveFocusaToIllA(i, false);
            }
        });
        androidComposeView.resendMotionEventOnLayout = new Function0<Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventOnLayout$1
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m5079invoke() {
                MotionEvent motionEvent = this.this$0.previousMotionEvent;
                if (motionEvent != null) {
                    int actionMasked = motionEvent.getActionMasked();
                    if (actionMasked == 7 || actionMasked == 9) {
                        this.this$0.relayoutTime = SystemClock.uptimeMillis();
                        AndroidComposeView androidComposeView2 = this.this$0;
                        androidComposeView2.post(androidComposeView2.resendMotionEventRunnable);
                    }
                }
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m5079invoke();
                return Unit.INSTANCE;
            }
        };
        androidComposeView.matrixToWindow = new CalculateMatrixToWindowApi29();
        androidComposeView.addOnAttachStateChangeListener(androidComposeView.contentCaptureManager);
        androidComposeView.setWillNotDraw(false);
        androidComposeView.setFocusable(true);
        AndroidComposeViewVerificationHelperMethodsO.INSTANCE.focusable(androidComposeView, 1, false);
        androidComposeView.setFocusableInTouchMode(true);
        androidComposeView.setClipChildren(false);
        ViewCompat.setAccessibilityDelegate(androidComposeView, androidComposeViewAccessibilityDelegateCompat);
        Function1<ViewRootForTest, Unit> onViewCreatedCallback = ViewRootForTest.INSTANCE.getOnViewCreatedCallback();
        if (onViewCreatedCallback != null) {
            onViewCreatedCallback.invoke(androidComposeView);
        }
        androidComposeView.setOnDragListener(androidComposeView.getDragAndDropManager());
        androidComposeView.getRoot().attach$ui(androidComposeView);
        AndroidComposeViewForceDarkModeQ.INSTANCE.disallowForceDark(androidComposeView);
        if (z2) {
            View view = new View(context);
            view.setLayoutParams(new ViewGroup.LayoutParams(1, 1));
            view.setTag(R.id.hide_in_inspector_tag, Boolean.TRUE);
            androidComposeView.frameRateCategoryView = view;
            androidComposeView.addView(view);
        }
        androidComposeView.scrollCapture = new ScrollCapture();
        androidComposeView.pointerIconService = new PointerIconService() { // from class: androidx.compose.ui.platform.AndroidComposeView$pointerIconService$1
            private PointerIcon currentMouseCursorIcon = PointerIcon.INSTANCE.getDefault();
            private PointerIcon currentStylusHoverIcon;

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            /* JADX INFO: renamed from: getIcon, reason: from getter */
            public PointerIcon getCurrentMouseCursorIcon() {
                return this.currentMouseCursorIcon;
            }

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            /* JADX INFO: renamed from: getStylusHoverIcon, reason: from getter */
            public PointerIcon getCurrentStylusHoverIcon() {
                return this.currentStylusHoverIcon;
            }

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            public void setIcon(PointerIcon value) {
                if (value == null) {
                    value = PointerIcon.INSTANCE.getDefault();
                }
                this.currentMouseCursorIcon = value;
                AndroidComposeViewVerificationHelperMethodsN.INSTANCE.setPointerIcon(this.this$0, value);
            }

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            public void setStylusHoverIcon(PointerIcon value) {
                this.currentStylusHoverIcon = value;
            }
        };
    }

    public static void a(AndroidComposeView androidComposeView) {
        Trace.beginSection("AndroidOwner:outOfFrameExecutor");
        while (!androidComposeView.outOfFrameQueue.isEmpty()) {
            try {
                ((Function0) androidComposeView.outOfFrameQueue.removeLast()).invoke();
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        Unit unit = Unit.INSTANCE;
        Trace.endSection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addExtraDataToAccessibilityNodeInfoHelper(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey) {
        int orDefault;
        boolean zAreEqual = Intrinsics.areEqual(extraDataKey, this.composeAccessibilityDelegate.getExtraDataTestTraversalBeforeVal());
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = this.composeAccessibilityDelegate;
        if (zAreEqual) {
            int orDefault2 = androidComposeViewAccessibilityDelegateCompat.getIdToBeforeMap().getOrDefault(virtualViewId, -1);
            if (orDefault2 != -1) {
                info.getExtras().putInt(extraDataKey, orDefault2);
                return;
            }
            return;
        }
        if (!Intrinsics.areEqual(extraDataKey, androidComposeViewAccessibilityDelegateCompat.getExtraDataTestTraversalAfterVal()) || (orDefault = this.composeAccessibilityDelegate.getIdToAfterMap().getOrDefault(virtualViewId, -1)) == -1) {
            return;
        }
        info.getExtras().putInt(extraDataKey, orDefault);
    }

    private final boolean autofillSupported() {
        return true;
    }

    public static void b(AndroidComposeView androidComposeView) {
        androidComposeView.hoverExitReceived = false;
        MotionEvent motionEvent = androidComposeView.previousMotionEvent;
        motionEvent.getClass();
        if (motionEvent.getActionMasked() == 10) {
            androidComposeView.m5067sendMotionEvent8iAsVTc(motionEvent);
        } else {
            k2d.a("The ACTION_HOVER_EXIT event was not cleared.");
        }
    }

    private final boolean childSizeCanAffectParentSize(LayoutNode layoutNode) {
        if (this.wasMeasuredWithMultipleConstraints) {
            return true;
        }
        LayoutNode parent$ui = layoutNode.getParent$ui();
        return (parent$ui == null || parent$ui.getHasFixedInnerContentConstraints$ui()) ? false : true;
    }

    private final void clearChildInvalidObservations(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof AndroidComposeView) {
                ((AndroidComposeView) childAt).onEndApplyChanges();
            } else if (childAt instanceof ViewGroup) {
                clearChildInvalidObservations((ViewGroup) childAt);
            }
        }
    }

    /* JADX INFO: renamed from: component1-VKZWuLQ, reason: not valid java name */
    private final int m5057component1VKZWuLQ(long j) {
        return (int) ULong.constructor-impl(j >>> 32);
    }

    /* JADX INFO: renamed from: component2-VKZWuLQ, reason: not valid java name */
    private final int m5058component2VKZWuLQ(long j) {
        return (int) ULong.constructor-impl(j & 4294967295L);
    }

    /* JADX INFO: renamed from: convertMeasureSpec-I7RO_PI, reason: not valid java name */
    private final long m5059convertMeasureSpecI7RO_PI(int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        if (mode == Integer.MIN_VALUE) {
            return m5066packZIaKswc(0, size);
        }
        if (mode == 0) {
            return m5066packZIaKswc(0, Integer.MAX_VALUE);
        }
        if (mode == 1073741824) {
            return m5066packZIaKswc(size, size);
        }
        g33.a();
        return 0L;
    }

    private final void dispatchConfigurationChangeIfNeeded() {
        if (Build.VERSION.SDK_INT < 34) {
            updateConfiguration(getResources().getConfiguration());
        }
    }

    private final void dispatchPendingInteropLayoutCallbacks() {
        if (this.isPendingInteropViewLayoutChangeDispatch) {
            getViewTreeObserver().dispatchOnGlobalLayout();
            this.isPendingInteropViewLayoutChangeDispatch = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final View findNextNonChildView(int direction) {
        FocusFinderCompat companion = FocusFinderCompat.INSTANCE.getInstance();
        View viewFindNextFocus = this;
        while (viewFindNextFocus != null) {
            View rootView = getRootView();
            rootView.getClass();
            viewFindNextFocus = companion.findNextFocus((ViewGroup) rootView, viewFindNextFocus, direction);
            if (viewFindNextFocus != null && !AndroidComposeView_androidKt.containsDescendant(this, viewFindNextFocus)) {
                return viewFindNextFocus;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: findNextViewInEmbeddedView-3ESFkO8, reason: not valid java name */
    private final View m5060findNextViewInEmbeddedView3ESFkO8(int focusDirection) {
        FocusTargetNode activeFocusTargetNode = getFocusOwner().getActiveFocusTargetNode();
        if (activeFocusTargetNode == null) {
            k2d.a("findNextViewInEmbeddedView called when owner does not have anything focused.");
            return null;
        }
        Integer numM2771toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m2771toAndroidFocusDirection3ESFkO8(focusDirection);
        if (numM2771toAndroidFocusDirection3ESFkO8 == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid focus direction");
            wq6.a();
            return null;
        }
        int iIntValue = numM2771toAndroidFocusDirection3ESFkO8.intValue();
        View interopView = DelegatableNodeKt.requireLayoutNode(activeFocusTargetNode).getInteropView();
        View viewFindFocus = findFocus();
        FocusFinder focusFinder = FocusFinder.getInstance();
        View rootView = getRootView();
        rootView.getClass();
        View viewFindNextFocus = focusFinder.findNextFocus((ViewGroup) rootView, viewFindFocus, iIntValue);
        if (viewFindNextFocus == null || interopView == null || !AndroidComposeView_androidKt.containsDescendant(interopView, viewFindNextFocus)) {
            return null;
        }
        return viewFindNextFocus;
    }

    private final View findViewByAccessibilityIdRootedAtCurrentView(int accessibilityId, View currentView) {
        return null;
    }

    @Deprecated(message = "fontLoader is deprecated, use fontFamilyResolver", replaceWith = @ReplaceWith(expression = "fontFamilyResolver", imports = {}))
    public static /* synthetic */ void getFontLoader$annotations() {
    }

    private final int getFontWeightAdjustmentCompat(Configuration configuration) {
        return configuration.fontWeightAdjustment;
    }

    public static /* synthetic */ void getLastMatrixRecalculationAnimationTime$ui$annotations() {
    }

    /* JADX INFO: renamed from: getPrimaryDirectionalMotionAxisOverride-dqNNBbU$ui$annotations, reason: not valid java name */
    public static /* synthetic */ void m5061getPrimaryDirectionalMotionAxisOverridedqNNBbU$ui$annotations() {
    }

    public static /* synthetic */ void getRoot$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    @Deprecated(message = "Use PlatformTextInputModifierNode instead.")
    public static /* synthetic */ void getTextInputService$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final ViewTreeOwners get_viewTreeOwners() {
        return (ViewTreeOwners) this._viewTreeOwners.getValue();
    }

    private final boolean handleIndirectPointerEvent(IndirectPointerEvent indirectPointerEvent) {
        boolean zDispatchIndirectPointerEvent = getFocusOwner().dispatchIndirectPointerEvent(indirectPointerEvent);
        if (!ComposeUiFlags.isIndirectPointerNavigationGestureDetectorEnabled) {
            return zDispatchIndirectPointerEvent;
        }
        this.indirectPointerNavigationGestureDetector.onIndirectPointerEvent(indirectPointerEvent, zDispatchIndirectPointerEvent);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0043  */
    /* JADX INFO: renamed from: handleMotionEvent-8iAsVTc, reason: not valid java name */
    private final int m5062handleMotionEvent8iAsVTc(MotionEvent motionEvent) {
        AndroidComposeView androidComposeView;
        int i;
        removeCallbacks(this.resendMotionEventRunnable);
        try {
            recalculateWindowPosition(motionEvent);
            boolean z = true;
            this.forceUseMatrixCache = true;
            measureAndLayout(false);
            Trace.beginSection("AndroidOwner:onTouch");
            try {
                int actionMasked = motionEvent.getActionMasked();
                MotionEvent motionEvent2 = this.previousMotionEvent;
                boolean z2 = motionEvent2 != null && motionEvent2.getToolType(0) == 3;
                if (motionEvent2 != null) {
                    try {
                        if (hasChangedDevices(motionEvent, motionEvent2)) {
                            if (isDevicePressEvent(motionEvent2)) {
                                this.pointerInputEventProcessor.processCancel();
                            } else if (motionEvent2.getActionMasked() != 10 && z2) {
                                sendSimulatedEvent$default(this, motionEvent2, 10, motionEvent2.getEventTime(), false, 8, null);
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        Trace.endSection();
                        throw th;
                    }
                }
                boolean z3 = motionEvent.getToolType(0) == 3;
                if (z2 || !z3 || actionMasked == 3 || actionMasked == 9 || !isInBounds(motionEvent)) {
                    androidComposeView = this;
                    i = 9;
                } else {
                    i = 9;
                    androidComposeView = this;
                    sendSimulatedEvent$default(androidComposeView, motionEvent, 9, motionEvent.getEventTime(), false, 8, null);
                }
                if (motionEvent2 != 0) {
                    motionEvent2.recycle();
                }
                MotionEvent motionEvent3 = androidComposeView.previousMotionEvent;
                if (motionEvent3 != null && motionEvent3.getAction() == 10) {
                    MotionEvent motionEvent4 = androidComposeView.previousMotionEvent;
                    int pointerId = motionEvent4 != null ? motionEvent4.getPointerId(0) : -1;
                    if (motionEvent.getAction() == i && motionEvent.getHistorySize() == 0) {
                        if (pointerId >= 0) {
                            androidComposeView.motionEventAdapter.endStream(pointerId);
                        }
                    } else if (motionEvent.getAction() == 0 && motionEvent.getHistorySize() == 0) {
                        MotionEvent motionEvent5 = androidComposeView.previousMotionEvent;
                        float x = motionEvent5 != null ? motionEvent5.getX() : Float.NaN;
                        MotionEvent motionEvent6 = androidComposeView.previousMotionEvent;
                        boolean z4 = (x == motionEvent.getX() && (motionEvent6 != null ? motionEvent6.getY() : Float.NaN) == motionEvent.getY()) ? false : true;
                        MotionEvent motionEvent7 = androidComposeView.previousMotionEvent;
                        if ((motionEvent7 != null ? motionEvent7.getEventTime() : -1L) == motionEvent.getEventTime()) {
                            z = false;
                        }
                        if (z4 || z) {
                            if (pointerId >= 0) {
                                androidComposeView.motionEventAdapter.endStream(pointerId);
                            }
                            androidComposeView.pointerInputEventProcessor.clearPreviouslyHitModifierNodes();
                        }
                    }
                }
                androidComposeView.previousMotionEvent = MotionEvent.obtainNoHistory(motionEvent);
                int iM5067sendMotionEvent8iAsVTc = m5067sendMotionEvent8iAsVTc(motionEvent);
                Trace.endSection();
                androidComposeView.forceUseMatrixCache = false;
                return iM5067sendMotionEvent8iAsVTc;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            this.forceUseMatrixCache = false;
            throw th3;
        }
    }

    private final boolean handleRotaryEvent(final MotionEvent event) {
        android.view.ViewConfiguration viewConfiguration = android.view.ViewConfiguration.get(getContext());
        float f = -event.getAxisValue(26);
        return getFocusOwner().dispatchRotaryEvent(new RotaryScrollEvent(ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, getContext()) * f, f * ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, getContext()), event.getEventTime(), event.getDeviceId()), new Function0<Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView.handleRotaryEvent.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m5076invoke() {
                return Boolean.valueOf(AndroidComposeView.super.dispatchGenericMotionEvent(event));
            }
        });
    }

    private final boolean hasChangedDevices(MotionEvent event, MotionEvent lastEvent) {
        return (lastEvent.getSource() == event.getSource() && lastEvent.getToolType(0) == event.getToolType(0)) ? false : true;
    }

    private final RetainedValuesStore installLocalRetainedValuesStore(LifecycleOwner lifecycleOwner, ViewModelStoreOwner viewModelStoreOwner) {
        LifecycleRetainedValuesStoreOwner.FrameEndScheduler frameEndScheduler = this.frameEndScheduler;
        if (lifecycleOwner == null || viewModelStoreOwner == null || frameEndScheduler == null) {
            return null;
        }
        LifecycleRetainedValuesStoreOwner lifecycleRetainedValuesStoreOwner = (LifecycleRetainedValuesStoreOwner) ViewModelProvider.Companion.create$default(ViewModelProvider.INSTANCE, viewModelStoreOwner.getViewModelStore(), new ViewModelProvider.NewInstanceFactory(), (CreationExtras) null, 4, (Object) null).get(Reflection.getOrCreateKotlinClass(LifecycleRetainedValuesStoreOwner.class));
        Object parent = getParent();
        parent.getClass();
        LifecycleRetainedValuesStoreOwner.RetainedValuesStoreEntry orCreateRetainedValuesStoreEntry = lifecycleRetainedValuesStoreOwner.getOrCreateRetainedValuesStoreEntry(((View) parent).getId());
        this.lifecycleRetainedValuesStoreOwnerEntry = orCreateRetainedValuesStoreEntry;
        return orCreateRetainedValuesStoreEntry.getRetainedValuesStore();
    }

    private final void invalidateLayers(LayoutNode node) {
        node.invalidateLayers$ui();
        MutableVector<LayoutNode> mutableVector = node.get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            invalidateLayers(layoutNodeArr[i]);
        }
    }

    private final void invalidateLayoutNodeMeasurement(LayoutNode node) {
        MeasureAndLayoutDelegate.requestRemeasure$default(this.measureAndLayoutDelegate, node, false, 2, null);
        MutableVector<LayoutNode> mutableVector = node.get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            invalidateLayoutNodeMeasurement(layoutNodeArr[i]);
        }
    }

    private final boolean isBadMotionEvent(MotionEvent event) {
        boolean z = (Float.floatToRawIntBits(event.getX()) & Integer.MAX_VALUE) >= 2139095040 || (Float.floatToRawIntBits(event.getY()) & Integer.MAX_VALUE) >= 2139095040 || (Float.floatToRawIntBits(event.getRawX()) & Integer.MAX_VALUE) >= 2139095040 || (Float.floatToRawIntBits(event.getRawY()) & Integer.MAX_VALUE) >= 2139095040;
        if (!z) {
            int pointerCount = event.getPointerCount();
            for (int i = 1; i < pointerCount; i++) {
                z = (Float.floatToRawIntBits(event.getX(i)) & Integer.MAX_VALUE) >= 2139095040 || (Float.floatToRawIntBits(event.getY(i)) & Integer.MAX_VALUE) >= 2139095040 || !MotionEventVerifierApi29.INSTANCE.isValidMotionEvent(event, i);
                if (z) {
                    break;
                }
            }
        }
        return z;
    }

    private final boolean isDevicePressEvent(MotionEvent event) {
        int actionMasked;
        return event.getButtonState() != 0 || (actionMasked = event.getActionMasked()) == 0 || actionMasked == 2 || actionMasked == 6;
    }

    private final boolean isInBounds(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return 0.0f <= x && x <= ((float) getWidth()) && 0.0f <= y && y <= ((float) getHeight());
    }

    private final boolean isPositionChanged(MotionEvent event) {
        MotionEvent motionEvent;
        return (event.getPointerCount() == 1 && (motionEvent = this.previousMotionEvent) != null && motionEvent.getPointerCount() == event.getPointerCount() && event.getRawX() == motionEvent.getRawX() && event.getRawY() == motionEvent.getRawY()) ? false : true;
    }

    /* JADX INFO: renamed from: moveFocusInChildrenBypassUnfocusableComposeView-3ESFkO8, reason: not valid java name */
    private final boolean m5063moveFocusInChildrenBypassUnfocusableComposeView3ESFkO8(int focusDirection) {
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (!FocusDirection.m2759equalsimpl0(focusDirection, companion.m2764getEnterdhqQ8s()) && !FocusDirection.m2759equalsimpl0(focusDirection, companion.m2765getExitdhqQ8s())) {
            Integer numM2771toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m2771toAndroidFocusDirection3ESFkO8(focusDirection);
            if (numM2771toAndroidFocusDirection3ESFkO8 != null) {
                int iIntValue = numM2771toAndroidFocusDirection3ESFkO8.intValue();
                View viewM5060findNextViewInEmbeddedView3ESFkO8 = m5060findNextViewInEmbeddedView3ESFkO8(focusDirection);
                if (viewM5060findNextViewInEmbeddedView3ESFkO8 != null) {
                    return FocusInteropUtils_androidKt.requestInteropFocus(viewM5060findNextViewInEmbeddedView3ESFkO8, Integer.valueOf(iIntValue), null);
                }
                return false;
            }
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid focus direction");
            wq6.a();
        }
        return false;
    }

    /* JADX INFO: renamed from: moveFocusInChildrenCurrent-3ESFkO8, reason: not valid java name */
    private final boolean m5064moveFocusInChildrenCurrent3ESFkO8(int focusDirection) {
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (!FocusDirection.m2759equalsimpl0(focusDirection, companion.m2764getEnterdhqQ8s()) && !FocusDirection.m2759equalsimpl0(focusDirection, companion.m2765getExitdhqQ8s())) {
            Integer numM2771toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m2771toAndroidFocusDirection3ESFkO8(focusDirection);
            if (numM2771toAndroidFocusDirection3ESFkO8 != null) {
                int iIntValue = numM2771toAndroidFocusDirection3ESFkO8.intValue();
                Rect embeddedViewFocusRect = getEmbeddedViewFocusRect();
                android.graphics.Rect androidRect = embeddedViewFocusRect != null ? RectHelper_androidKt.toAndroidRect(embeddedViewFocusRect) : null;
                FocusFinderCompat companion2 = FocusFinderCompat.INSTANCE.getInstance();
                View viewFindNextFocus = androidRect == null ? companion2.findNextFocus(this, findFocus(), iIntValue) : companion2.findNextFocusFromRect(this, androidRect, iIntValue);
                if (viewFindNextFocus != null) {
                    return FocusInteropUtils_androidKt.requestInteropFocus(viewFindNextFocus, Integer.valueOf(iIntValue), androidRect);
                }
                return false;
            }
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid focus direction");
            wq6.a();
        }
        return false;
    }

    /* JADX INFO: renamed from: moveFocusInChildrenViewFocusFix-3ESFkO8, reason: not valid java name */
    private final boolean m5065moveFocusInChildrenViewFocusFix3ESFkO8(int focusDirection) {
        AndroidViewsHandler androidViewsHandler;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m2759equalsimpl0(focusDirection, companion.m2764getEnterdhqQ8s()) || FocusDirection.m2759equalsimpl0(focusDirection, companion.m2765getExitdhqQ8s()) || !hasFocus() || (androidViewsHandler = this._androidViewsHandler) == null) {
            return false;
        }
        Integer numM2771toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m2771toAndroidFocusDirection3ESFkO8(focusDirection);
        if (numM2771toAndroidFocusDirection3ESFkO8 == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid focus direction");
            wq6.a();
            return false;
        }
        int iIntValue = numM2771toAndroidFocusDirection3ESFkO8.intValue();
        View rootView = getRootView();
        rootView.getClass();
        ViewGroup viewGroup = (ViewGroup) rootView;
        View viewFindFocus = viewGroup.findFocus();
        if (viewFindFocus == null) {
            k2d.a("view hasFocus but root can't find it");
            return false;
        }
        View viewFindNextFocus = FocusFinderCompat.INSTANCE.getInstance().findNextFocus(viewGroup, viewFindFocus, iIntValue);
        android.graphics.Rect androidRect = null;
        if (!FocusOwnerImplKt.m2789is1dFocusSearch3ESFkO8(focusDirection) || !androidViewsHandler.hasFocus()) {
            Rect embeddedViewFocusRect = getEmbeddedViewFocusRect();
            androidRect = embeddedViewFocusRect != null ? RectHelper_androidKt.toAndroidRect(embeddedViewFocusRect) : null;
            if (viewFindNextFocus != null && androidRect != null) {
                viewGroup.offsetDescendantRectToMyCoords(this, androidRect);
                viewGroup.offsetRectIntoDescendantCoords(viewFindNextFocus, androidRect);
            }
        }
        if (viewFindNextFocus == null || viewFindNextFocus == viewFindFocus) {
            return false;
        }
        View focusedChild = androidViewsHandler.getFocusedChild();
        ViewParent parent = viewFindNextFocus.getParent();
        while (parent != null && parent != focusedChild) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return false;
        }
        return FocusInteropUtils_androidKt.requestInteropFocus(viewFindNextFocus, Integer.valueOf(iIntValue), androidRect);
    }

    /* JADX INFO: renamed from: pack-ZIaKswc, reason: not valid java name */
    private final long m5066packZIaKswc(int a, int b) {
        return ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(a) << 32) | ULong.constructor-impl(b));
    }

    private final void recalculateWindowPosition() {
        if (this.forceUseMatrixCache) {
            return;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (jCurrentAnimationTimeMillis != this.lastMatrixRecalculationAnimationTime) {
            this.lastMatrixRecalculationAnimationTime = jCurrentAnimationTimeMillis;
            recalculateWindowViewTransforms();
            ViewParent parent = getParent();
            View view = this;
            while (parent instanceof ViewGroup) {
                view = (View) parent;
                parent = ((ViewGroup) view).getParent();
            }
            view.getLocationOnScreen(this.tmpPositionArray);
            int[] iArr = this.tmpPositionArray;
            float f = iArr[0];
            float f2 = iArr[1];
            view.getLocationInWindow(iArr);
            int[] iArr2 = this.tmpPositionArray;
            this.windowPosition = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(f - iArr2[0])) << 32) | (((long) Float.floatToRawIntBits(f2 - iArr2[1])) & 4294967295L));
        }
    }

    private final void recalculateWindowViewTransforms() {
        this.matrixToWindow.mo5109calculateMatrixToWindowEL8BTi8(this, this.viewToWindowMatrix);
        InvertMatrixKt.m5174invertToJiSxe2E(this.viewToWindowMatrix, this.windowToViewMatrix);
    }

    private final void scheduleMeasureAndLayout(LayoutNode nodeToRemeasure) {
        if (isLayoutRequested() || !isAttachedToWindow()) {
            return;
        }
        if (nodeToRemeasure != null) {
            while (nodeToRemeasure != null && nodeToRemeasure.getMeasuredByParent$ui() == LayoutNode.UsageByParent.InMeasureBlock && childSizeCanAffectParentSize(nodeToRemeasure)) {
                nodeToRemeasure = nodeToRemeasure.getParent$ui();
            }
            if (nodeToRemeasure == getRoot()) {
                requestLayout();
                return;
            }
        }
        if (getWidth() == 0 || getHeight() == 0) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    public static /* synthetic */ void scheduleMeasureAndLayout$default(AndroidComposeView androidComposeView, LayoutNode layoutNode, int i, Object obj) {
        if ((i & 1) != 0) {
            layoutNode = null;
        }
        androidComposeView.scheduleMeasureAndLayout(layoutNode);
    }

    /* JADX INFO: renamed from: sendMotionEvent-8iAsVTc, reason: not valid java name */
    private final int m5067sendMotionEvent8iAsVTc(MotionEvent motionEvent) {
        PointerInputEventData pointerInputEventData;
        if (this.keyboardModifiersRequireUpdate) {
            this.keyboardModifiersRequireUpdate = false;
            this._windowInfo.m5183setKeyboardModifiers5xRPYO0(PointerKeyboardModifiers.m4520constructorimpl(motionEvent.getMetaState()));
        }
        PointerInputEvent pointerInputEventConvertToPointerInputEvent$ui = this.motionEventAdapter.convertToPointerInputEvent$ui(motionEvent, this);
        int actionMasked = motionEvent.getActionMasked();
        if (pointerInputEventConvertToPointerInputEvent$ui == null) {
            this.pointerInputEventProcessor.processCancel();
            return PointerInputEventProcessorKt.ProcessResult(false, false, false);
        }
        List<PointerInputEventData> pointers = pointerInputEventConvertToPointerInputEvent$ui.getPointers();
        int size = pointers.size() - 1;
        if (size < 0) {
            pointerInputEventData = null;
            break;
        }
        while (true) {
            int i = size - 1;
            pointerInputEventData = pointers.get(size);
            if (pointerInputEventData.getDown() && (actionMasked == 0 || actionMasked == 5 || !ComposeUiFlags.isCanScrollUsingLastDownEventFixEnabled)) {
                break;
            }
            if (i < 0) {
                pointerInputEventData = null;
                break;
            }
            size = i;
        }
        PointerInputEventData pointerInputEventData2 = pointerInputEventData;
        if (pointerInputEventData2 != null) {
            this.lastDownPointerPosition = pointerInputEventData2.m4472getPositionF1C5BW0();
        }
        int iM4477processBIzXfog = this.pointerInputEventProcessor.m4477processBIzXfog(pointerInputEventConvertToPointerInputEvent$ui, this, isInBounds(motionEvent));
        pointerInputEventConvertToPointerInputEvent$ui.setMotionEvent(null);
        if ((actionMasked != 0 && actionMasked != 5) || (iM4477processBIzXfog & 1) != 0) {
            return iM4477processBIzXfog;
        }
        this.motionEventAdapter.endStream(motionEvent.getPointerId(motionEvent.getActionIndex()));
        return iM4477processBIzXfog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSimulatedEvent(MotionEvent motionEvent, int action, long eventTime, boolean forceHover) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = -1;
        if (actionMasked != 1) {
            if (actionMasked == 6) {
                actionIndex = motionEvent.getActionIndex();
            }
        } else if (action != 9 && action != 10) {
            actionIndex = 0;
        }
        int pointerCount = motionEvent.getPointerCount() - (actionIndex >= 0 ? 1 : 0);
        if (pointerCount == 0) {
            return;
        }
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            pointerPropertiesArr[i] = new MotionEvent.PointerProperties();
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i2 = 0; i2 < pointerCount; i2++) {
            pointerCoordsArr[i2] = new MotionEvent.PointerCoords();
        }
        int i3 = 0;
        while (i3 < pointerCount) {
            int i4 = ((actionIndex < 0 || i3 < actionIndex) ? 0 : 1) + i3;
            motionEvent.getPointerProperties(i4, pointerPropertiesArr[i3]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i3];
            motionEvent.getPointerCoords(i4, pointerCoords);
            float f = pointerCoords.x;
            long jMo4538localToScreenMKHz9U = mo4538localToScreenMKHz9U(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(pointerCoords.y)) & 4294967295L) | (((long) Float.floatToRawIntBits(f)) << 32)));
            pointerCoords.x = Float.intBitsToFloat((int) (jMo4538localToScreenMKHz9U >> 32));
            pointerCoords.y = Float.intBitsToFloat((int) (jMo4538localToScreenMKHz9U & 4294967295L));
            i3++;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent.getDownTime() == motionEvent.getEventTime() ? eventTime : motionEvent.getDownTime(), eventTime, action, pointerCount, pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), forceHover ? 0 : motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        PointerInputEvent pointerInputEventConvertToPointerInputEvent$ui = this.motionEventAdapter.convertToPointerInputEvent$ui(motionEventObtain, this);
        pointerInputEventConvertToPointerInputEvent$ui.getClass();
        this.pointerInputEventProcessor.m4477processBIzXfog(pointerInputEventConvertToPointerInputEvent$ui, this, true);
        motionEventObtain.recycle();
    }

    public static /* synthetic */ void sendSimulatedEvent$default(AndroidComposeView androidComposeView, MotionEvent motionEvent, int i, long j, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = true;
        }
        androidComposeView.sendSimulatedEvent(motionEvent, i, j, z);
    }

    private void setDensity(Density density) {
        this.density.setValue(density);
    }

    private void setFontFamilyResolver(FontFamily.Resolver resolver) {
        this.fontFamilyResolver.setValue(resolver);
    }

    private void setLayoutDirection(LayoutDirection layoutDirection) {
        this.layoutDirection.setValue(layoutDirection);
    }

    private final void set_viewTreeOwners(ViewTreeOwners viewTreeOwners) {
        this._viewTreeOwners.setValue(viewTreeOwners);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startDrag-12SF9DM, reason: not valid java name */
    public final boolean m5068startDrag12SF9DM(DragAndDropTransferData transferData, long decorationSize, Function1<? super DrawScope, Unit> drawDragDecoration) {
        Resources resources = getContext().getResources();
        return AndroidComposeViewStartDragAndDropN.INSTANCE.startDragAndDrop(this, transferData, new ComposeDragShadowBuilder(DensityKt.Density(resources.getDisplayMetrics().density, resources.getConfiguration().fontScale), decorationSize, drawDragDecoration, null));
    }

    private final void updateConfiguration(Configuration newConfig) {
        Configuration configuration = getConfiguration();
        if (Intrinsics.areEqual(configuration, newConfig)) {
            return;
        }
        setConfiguration(new Configuration(newConfig));
        if (configuration.fontScale != newConfig.fontScale || configuration.densityDpi != newConfig.densityDpi) {
            setDensity(AndroidDensity_androidKt.Density(getContext()));
        }
        if (AndroidComposeView_androidKt.diffForWindowMetricsChanged(configuration, newConfig)) {
            updateWindowMetrics();
        }
        if (getFontWeightAdjustmentCompat(configuration) != getFontWeightAdjustmentCompat(newConfig)) {
            setFontFamilyResolver(FontFamilyResolver_androidKt.createFontFamilyResolver(getContext()));
        }
    }

    private final void updatePositionCacheAndDispatch() {
        getLocationOnScreen(this.tmpPositionArray);
        long j = this.globalPosition;
        int iM6150getXimpl = IntOffset.m6150getXimpl(j);
        int iM6151getYimpl = IntOffset.m6151getYimpl(j);
        int[] iArr = this.tmpPositionArray;
        boolean z = false;
        int i = iArr[0];
        if (iM6150getXimpl != i || iM6151getYimpl != iArr[1] || this.lastMatrixRecalculationAnimationTime < 0) {
            this.globalPosition = IntOffset.m6144constructorimpl((((long) i) << 32) | (((long) iArr[1]) & 4294967295L));
            if (iM6150getXimpl != Integer.MAX_VALUE && iM6151getYimpl != Integer.MAX_VALUE) {
                getRoot().getLayoutDelegate().getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
                z = true;
            }
        }
        recalculateWindowPosition();
        View rootView = this._rootView;
        if (rootView == null) {
            rootView = getRootView();
            this._rootView = rootView;
        }
        getRectManager().m5272updateOffsetsgTq6Wqs(this.globalPosition, IntOffsetKt.m6167roundk4lQ0M(this.windowPosition), this.viewToWindowMatrix, rootView.getWidth(), rootView.getHeight());
        this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
        getRectManager().dispatchCallbacks();
    }

    private final void updateWindowMetrics() {
        MutableState mutableState = this._windowInfo._containerSize;
        if (mutableState != null) {
            mutableState.setValue(AndroidWindowInfo_androidKt.calculateWindowSize(this));
        }
    }

    public final void addAndroidView(AndroidViewHolder view, final LayoutNode layoutNode) {
        getAndroidViewsHandler$ui().getHolderToLayoutNode().put(view, layoutNode);
        getAndroidViewsHandler$ui().addView(view);
        getAndroidViewsHandler$ui().getLayoutNodeToHolder().put(layoutNode, view);
        view.setImportantForAccessibility(1);
        ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: androidx.compose.ui.platform.AndroidComposeView.addAndroidView.1
            /* JADX WARN: Code duplicated, block: B:19:0x0054  */
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                if (AndroidComposeView.this.composeAccessibilityDelegate.isEnabled$ui()) {
                    info.setVisibleToUser(false);
                }
                LayoutNode parent$ui = layoutNode.getParent$ui();
                while (true) {
                    if (parent$ui == null) {
                        parent$ui = null;
                        break;
                    } else if (parent$ui.getNodes().m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(8))) {
                        break;
                    } else {
                        parent$ui = parent$ui.getParent$ui();
                    }
                }
                Integer numValueOf = parent$ui != null ? Integer.valueOf(parent$ui.getSemanticsId()) : null;
                if (numValueOf != null) {
                    if (numValueOf.intValue() == AndroidComposeView.this.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
                        numValueOf = -1;
                    }
                } else {
                    numValueOf = -1;
                }
                info.setParent(this, numValueOf.intValue());
                int semanticsId = layoutNode.getSemanticsId();
                int orDefault = AndroidComposeView.this.composeAccessibilityDelegate.getIdToBeforeMap().getOrDefault(semanticsId, -1);
                if (orDefault != -1) {
                    View viewSemanticsIdToView = SemanticsUtils_androidKt.semanticsIdToView(AndroidComposeView.this.getAndroidViewsHandler$ui(), orDefault);
                    if (viewSemanticsIdToView != null) {
                        info.setTraversalBefore(viewSemanticsIdToView);
                    } else {
                        info.setTraversalBefore(this, orDefault);
                    }
                    AndroidComposeView.this.addExtraDataToAccessibilityNodeInfoHelper(semanticsId, info.unwrap(), AndroidComposeView.this.composeAccessibilityDelegate.getExtraDataTestTraversalBeforeVal());
                }
                int orDefault2 = AndroidComposeView.this.composeAccessibilityDelegate.getIdToAfterMap().getOrDefault(semanticsId, -1);
                if (orDefault2 != -1) {
                    View viewSemanticsIdToView2 = SemanticsUtils_androidKt.semanticsIdToView(AndroidComposeView.this.getAndroidViewsHandler$ui(), orDefault2);
                    if (viewSemanticsIdToView2 != null) {
                        info.setTraversalAfter(viewSemanticsIdToView2);
                    } else {
                        info.setTraversalAfter(this, orDefault2);
                    }
                    AndroidComposeView.this.addExtraDataToAccessibilityNodeInfoHelper(semanticsId, info.unwrap(), AndroidComposeView.this.composeAccessibilityDelegate.getExtraDataTestTraversalAfterVal());
                }
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        if (!ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
            super.addFocusables(views, direction, focusableMode);
            return;
        }
        if (getFocusOwner().hasFocusableContent()) {
            super.addFocusables(views, direction, focusableMode);
            if (getFocusOwner().hasNonInteropFocusableContent() || views == null) {
                return;
            }
            views.remove(this);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index) {
        child.getClass();
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        addView(child, index, layoutParams);
    }

    @Override // android.view.View
    public void autofill(SparseArray<AutofillValue> values) {
        AndroidAutofillManager androidAutofillManager;
        if (autofillSupported()) {
            if (ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
                androidAutofillManager.performAutofill(values);
            }
            AndroidAutofill androidAutofill = this._autofill;
            if (androidAutofill != null) {
                AndroidAutofill_androidKt.performAutofill(androidAutofill, values);
            }
        }
    }

    public final Object boundsUpdatesAccessibilityEventLoop(Continuation<? super Unit> continuation) {
        Object objBoundsUpdatesEventLoop$ui = this.composeAccessibilityDelegate.boundsUpdatesEventLoop$ui(continuation);
        return objBoundsUpdatesEventLoop$ui == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBoundsUpdatesEventLoop$ui : Unit.INSTANCE;
    }

    public final Object boundsUpdatesContentCaptureEventLoop(Continuation<? super Unit> continuation) {
        Object objBoundsUpdatesEventLoop$ui = this.contentCaptureManager.boundsUpdatesEventLoop$ui(continuation);
        return objBoundsUpdatesEventLoop$ui == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBoundsUpdatesEventLoop$ui : Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: calculateLocalPosition-MK-Hz9U */
    public long mo5013calculateLocalPositionMKHz9U(long positionInWindow) {
        recalculateWindowPosition();
        return Matrix.m3384mapMKHz9U(this.windowToViewMatrix, positionInWindow);
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: calculatePositionInWindow-MK-Hz9U */
    public long mo5014calculatePositionInWindowMKHz9U(long localPosition) {
        recalculateWindowPosition();
        return Matrix.m3384mapMKHz9U(this.viewToWindowMatrix, localPosition);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int direction) {
        return this.composeAccessibilityDelegate.m5084canScroll0AR0LA0$ui(false, direction, this.lastDownPointerPosition);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int direction) {
        return this.composeAccessibilityDelegate.m5084canScroll0AR0LA0$ui(true, direction, this.lastDownPointerPosition);
    }

    @Override // androidx.compose.ui.focus.PlatformFocusOwner
    public void clearOwnerFocus() {
        if (isFocused() || (!ComposeUiFlags.isViewFocusFixEnabled && hasFocus())) {
            super.clearFocus();
        } else if (hasFocus()) {
            View viewFindFocus = findFocus();
            if (viewFindFocus != null) {
                viewFindFocus.clearFocus();
            }
            super.clearFocus();
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public OwnedLayer createLayer(Function2<? super Canvas, ? super GraphicsLayer, Unit> drawBlock, Function0<Unit> invalidateParentLayer, GraphicsLayer explicitLayer) {
        if (explicitLayer != null) {
            return new GraphicsLayerOwnerLayer(explicitLayer, null, this, drawBlock, invalidateParentLayer);
        }
        OwnedLayer ownedLayer = (OwnedLayer) this.layerCache.pop();
        if (ownedLayer == null) {
            return new GraphicsLayerOwnerLayer(getGraphicsContext().createGraphicsLayer(), getGraphicsContext(), this, drawBlock, invalidateParentLayer);
        }
        ownedLayer.reuseLayer(drawBlock, invalidateParentLayer);
        return ownedLayer;
    }

    @Override // androidx.compose.ui.node.Owner
    public void decrementKeepScreenOnCount() {
        this.keepScreenOnCount--;
        getView().setKeepScreenOn(this.keepScreenOnCount > 0);
    }

    @Override // androidx.compose.ui.node.Owner
    public void decrementSensitiveComponentCount() {
        if (Build.VERSION.SDK_INT >= 35) {
            if (this.sensitiveComponentCount == 1) {
                AndroidComposeViewSensitiveContent35.INSTANCE.setContentSensitivity(getView(), false);
            }
            this.sensitiveComponentCount--;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(android.graphics.Canvas canvas) {
        if (!isAttachedToWindow()) {
            invalidateLayers(getRoot());
        }
        View view = null;
        Owner.measureAndLayout$default(this, false, 1, null);
        Snapshot.INSTANCE.notifyObjectsInitialized();
        this.isDrawingContent = true;
        try {
            CanvasHolder canvasHolder = this.canvasHolder;
            android.graphics.Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
            canvasHolder.getAndroidCanvas().setInternalCanvas(canvas);
            getRoot().draw$ui(canvasHolder.getAndroidCanvas(), null);
            canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
            if (this.dirtyLayers.isNotEmpty()) {
                int size = this.dirtyLayers.getSize();
                for (int i = 0; i < size; i++) {
                    ((OwnedLayer) this.dirtyLayers.get(i)).updateDisplayList();
                }
            }
            if (ViewLayer.INSTANCE.getShouldUseDispatchDraw()) {
                int iSave = canvas.save();
                canvas.clipRect(0.0f, 0.0f, 0.0f, 0.0f);
                super.dispatchDraw(canvas);
                canvas.restoreToCount(iSave);
            }
            this.dirtyLayers.clear();
            this.isDrawingContent = false;
        } catch (Throwable th) {
            RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
            if (uncaughtExceptionHandler == null) {
                throw th;
            }
            uncaughtExceptionHandler.onUncaughtException(th);
        }
        MutableObjectList<OwnedLayer> mutableObjectList = this.postponedDirtyLayers;
        if (mutableObjectList != null) {
            mutableObjectList.getClass();
            this.dirtyLayers.addAll(mutableObjectList);
            mutableObjectList.clear();
        }
        if (this.isArrEnabled) {
            Api35Impl.setRequestedFrameRate(this, this.currentFrameRate);
            View view2 = this.frameRateCategoryView;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("frameRateCategoryView");
                view2 = null;
            }
            Api35Impl.setRequestedFrameRate(view2, this.currentFrameRateCategory);
            if (!Float.isNaN(this.currentFrameRateCategory)) {
                View view3 = this.frameRateCategoryView;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("frameRateCategoryView");
                    view3 = null;
                }
                view3.invalidate();
                View view4 = this.frameRateCategoryView;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("frameRateCategoryView");
                } else {
                    view = view4;
                }
                drawChild(canvas, view, getDrawingTime());
            }
            this.currentFrameRate = Float.NaN;
            this.currentFrameRateCategory = Float.NaN;
        }
        getRectManager().dispatchCallbacks();
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            if (motionEvent.getActionMasked() == 8) {
                this.hoverExitReceived = false;
            } else {
                this.sendHoverExitEvent.run();
            }
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        if (motionEvent.getActionMasked() == 8) {
            if (motionEvent.isFromSource(4194304)) {
                return handleRotaryEvent(motionEvent);
            }
            return (m5062handleMotionEvent8iAsVTc(motionEvent) & 1) != 0;
        }
        if (motionEvent.isFromSource(2097152)) {
            AndroidIndirectPointerEvent androidIndirectPointerEventM4377convertToIndirectPointerEventk92h6UU$ui = this.motionEventAdapter.m4377convertToIndirectPointerEventk92h6UU$ui(motionEvent, this.primaryDirectionalMotionAxisOverride);
            if (androidIndirectPointerEventM4377convertToIndirectPointerEventk92h6UU$ui == null) {
                getFocusOwner().dispatchIndirectPointerCancel();
                this.indirectPointerNavigationGestureDetector.cancelCurrentEventStream();
                return true;
            }
            if (handleIndirectPointerEvent(androidIndirectPointerEventM4377convertToIndirectPointerEventk92h6UU$ui)) {
                return true;
            }
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchHoverEvent(MotionEvent event) {
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            this.sendHoverExitEvent.run();
        }
        if (!isBadMotionEvent(event) && isAttachedToWindow()) {
            this.composeAccessibilityDelegate.dispatchHoverEvent$ui(event);
            int actionMasked = event.getActionMasked();
            if (actionMasked != 7) {
                if (actionMasked == 10 && isInBounds(event)) {
                    if (event.getToolType(0) == 3 && event.getButtonState() != 0) {
                        return false;
                    }
                    MotionEvent motionEvent = this.previousMotionEvent;
                    if (motionEvent != null) {
                        motionEvent.recycle();
                    }
                    this.previousMotionEvent = MotionEvent.obtainNoHistory(event);
                    this.hoverExitReceived = true;
                    postDelayed(this.sendHoverExitEvent, 8L);
                    return false;
                }
            } else if (!isPositionChanged(event)) {
                return false;
            }
            if ((m5062handleMotionEvent8iAsVTc(event) & 1) != 0) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(final KeyEvent event) {
        if (!isFocused()) {
            return getFocusOwner().mo2778dispatchKeyEventYhN2O0w(androidx.compose.ui.input.key.KeyEvent.m4279constructorimpl(event), new Function0<Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView.dispatchKeyEvent.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final Boolean m5074invoke() {
                    return Boolean.valueOf(AndroidComposeView.super.dispatchKeyEvent(event));
                }
            });
        }
        this._windowInfo.m5183setKeyboardModifiers5xRPYO0(PointerKeyboardModifiers.m4520constructorimpl(event.getMetaState()));
        return FocusOwner.m2775dispatchKeyEventYhN2O0w$default(getFocusOwner(), androidx.compose.ui.input.key.KeyEvent.m4279constructorimpl(event), null, 2, null) || super.dispatchKeyEvent(event);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        return (isFocused() && getFocusOwner().mo2777dispatchInterceptedSoftKeyboardEventZmokQxo(androidx.compose.ui.input.key.KeyEvent.m4279constructorimpl(event))) || super.dispatchKeyEventPreIme(event);
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: dispatchOnScrollChanged-k-4lQ0M */
    public void mo5015dispatchOnScrollChangedk4lQ0M(long delta) {
        INSTANCE.dispatchOnScrollChanged(getViewTreeObserver());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideStructure(ViewStructure structure) {
        super.dispatchProvideStructure(structure);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Object objM5099boximpl;
        FocusTargetNode activeFocusTargetNode;
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            MotionEvent motionEvent2 = this.previousMotionEvent;
            motionEvent2.getClass();
            if (motionEvent.getActionMasked() != 0 || hasChangedDevices(motionEvent, motionEvent2)) {
                this.sendHoverExitEvent.run();
            } else {
                this.hoverExitReceived = false;
            }
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow() || (motionEvent.getActionMasked() == 2 && !isPositionChanged(motionEvent))) {
            return false;
        }
        int iM5062handleMotionEvent8iAsVTc = m5062handleMotionEvent8iAsVTc(motionEvent);
        if ((iM5062handleMotionEvent8iAsVTc & 2) != 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        boolean z = motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 5;
        boolean z2 = motionEvent.isFromSource(InputDeviceCompat.SOURCE_MOUSE) || motionEvent.isFromSource(InputDeviceCompat.SOURCE_TOUCHPAD);
        if (z && z2) {
            Object parent = getParent();
            View view = parent instanceof View ? (View) parent : null;
            if (view == null || (objM5099boximpl = view.getTag(R.id.auto_clear_focus_behavior_tag)) == null) {
                objM5099boximpl = AutoClearFocusBehavior.m5099boximpl(AutoClearFocusBehavior.INSTANCE.m5107getDefault4UtRPd4());
            }
            if (Intrinsics.areEqual(objM5099boximpl, AutoClearFocusBehavior.m5099boximpl(AutoClearFocusBehavior.INSTANCE.m5106getCursorBased4UtRPd4())) && (activeFocusTargetNode = getFocusOwner().getActiveFocusTargetNode()) != null) {
                if (!LayoutCoordinatesKt.boundsInRoot(DelegatableNodeKt.requireLayoutCoordinates(activeFocusTargetNode)).m2915containsk4lQ0M(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(motionEvent.getX())) << 32) | (((long) Float.floatToRawIntBits(motionEvent.getY())) & 4294967295L)))) {
                    FocusManager.clearFocus$default(getFocusOwner(), false, 1, null);
                }
            }
        }
        return (iM5062handleMotionEvent8iAsVTc & 1) != 0;
    }

    public final void drawAndroidView(AndroidViewHolder view, android.graphics.Canvas canvas) {
        getAndroidViewsHandler$ui().drawView(view, canvas);
    }

    public final View findViewByAccessibilityIdTraversal(int accessibilityId) throws IllegalAccessException, InvocationTargetException {
        try {
            Method declaredMethod = View.class.getDeclaredMethod("findViewByAccessibilityIdTraversal", Integer.TYPE);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(this, Integer.valueOf(accessibilityId));
            if (objInvoke instanceof View) {
                return (View) objInvoke;
            }
        } catch (NoSuchMethodException unused) {
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View focused, int direction) {
        Rect rectCalculateFocusRectRelativeTo;
        if (focused == null || this.measureAndLayoutDelegate.getDuringMeasureLayout()) {
            return super.focusSearch(focused, direction);
        }
        View rootView = getRootView();
        rootView.getClass();
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup) rootView, focused, direction);
        if (viewFindNextFocus == null || !AndroidComposeView_androidKt.containsDescendant(this, viewFindNextFocus)) {
            viewFindNextFocus = null;
        }
        if (focused != this || (rectCalculateFocusRectRelativeTo = getFocusOwner().getFocusRect()) == null) {
            rectCalculateFocusRectRelativeTo = FocusInteropUtils_androidKt.calculateFocusRectRelativeTo(focused, this);
        }
        FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(direction);
        int value = focusDirection != null ? focusDirection.getValue() : FocusDirection.INSTANCE.m2763getDowndhqQ8s();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (getFocusOwner().mo2779focusSearchULY8qGw(value, rectCalculateFocusRectRelativeTo, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$focusSearch$searchResult$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                objectRef.element = focusTargetNode;
                return Boolean.TRUE;
            }
        }) == null) {
            return focused;
        }
        if (objectRef.element != null) {
            if (viewFindNextFocus != null) {
                if (FocusOwnerImplKt.m2789is1dFocusSearch3ESFkO8(value)) {
                    if (!ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
                        return super.focusSearch(focused, direction);
                    }
                } else if (TwoDimensionalFocusSearchKt.m2837isBetterCandidateI7lrPNg(FocusTraversalKt.focusRect((FocusTargetNode) objectRef.element), FocusInteropUtils_androidKt.calculateFocusRectRelativeTo(viewFindNextFocus, this), rectCalculateFocusRectRelativeTo, value)) {
                }
            }
            return this;
        }
        if (viewFindNextFocus == null) {
            return super.focusSearch(focused, direction);
        }
        return viewFindNextFocus;
    }

    @Override // androidx.compose.ui.focus.PlatformFocusOwner
    public void focusTargetAvailable() {
        if (getFocusOwner().getRootState().getHasFocus()) {
            return;
        }
        focusableViewAvailable(this);
    }

    @Override // androidx.compose.ui.node.RootForTest
    public void forceAccessibilityForTesting(boolean enable) {
        this.composeAccessibilityDelegate.setAccessibilityForceEnabledForTesting$ui(enable);
    }

    @Override // androidx.compose.ui.node.Owner
    public void forceMeasureTheSubtree(LayoutNode layoutNode, boolean affectsLookahead) {
        this.measureAndLayoutDelegate.forceMeasureTheSubtree(layoutNode, affectsLookahead);
    }

    public final AndroidViewsHandler getAndroidViewsHandler$ui() {
        if (this._androidViewsHandler == null) {
            AndroidViewsHandler androidViewsHandler = new AndroidViewsHandler(getContext());
            this._androidViewsHandler = androidViewsHandler;
            addView(androidViewsHandler);
            requestLayout();
        }
        AndroidViewsHandler androidViewsHandler2 = this._androidViewsHandler;
        androidViewsHandler2.getClass();
        return androidViewsHandler2;
    }

    @Override // androidx.compose.ui.node.Owner
    public Autofill getAutofill() {
        return this._autofill;
    }

    @Override // androidx.compose.ui.node.Owner
    public androidx.compose.ui.autofill.AutofillManager getAutofillManager() {
        return this._autofillManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public AutofillTree getAutofillTree() {
        return this.autofillTree;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Configuration getConfiguration() {
        return (Configuration) this.configuration.getValue();
    }

    /* JADX INFO: renamed from: getContentCaptureManager$ui, reason: from getter */
    public final AndroidContentCaptureManager getContentCaptureManager() {
        return this.contentCaptureManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // androidx.compose.ui.node.Owner, androidx.compose.ui.node.RootForTest
    public Density getDensity() {
        return (Density) this.density.getValue();
    }

    @Override // androidx.compose.ui.focus.PlatformFocusOwner
    public Rect getEmbeddedViewFocusRect() {
        if (isFocused()) {
            return getFocusOwner().getFocusRect();
        }
        View viewFindFocus = findFocus();
        if (viewFindFocus != null) {
            return FocusInteropUtils_androidKt.calculateFocusRectRelativeTo(viewFindFocus, this);
        }
        return null;
    }

    @Override // androidx.compose.ui.node.Owner
    public FocusOwner getFocusOwner() {
        return this.focusOwner;
    }

    @Override // android.view.View
    public void getFocusedRect(android.graphics.Rect rect) {
        Rect embeddedViewFocusRect = getEmbeddedViewFocusRect();
        if (embeddedViewFocusRect != null) {
            rect.left = Math.round(embeddedViewFocusRect.getLeft());
            rect.top = Math.round(embeddedViewFocusRect.getTop());
            rect.right = Math.round(embeddedViewFocusRect.getRight());
            rect.bottom = Math.round(embeddedViewFocusRect.getBottom());
            return;
        }
        if (Intrinsics.areEqual(getFocusOwner().mo2779focusSearchULY8qGw(FocusDirection.INSTANCE.m2763getDowndhqQ8s(), null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView.getFocusedRect.1
            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                return Boolean.TRUE;
            }
        }), Boolean.TRUE)) {
            super.getFocusedRect(rect);
        } else {
            rect.set(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public FontFamily.Resolver getFontFamilyResolver() {
        return (FontFamily.Resolver) this.fontFamilyResolver.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public Font.ResourceLoader getFontLoader() {
        return this.fontLoader;
    }

    /* JADX INFO: renamed from: getFrameEndScheduler$ui, reason: from getter */
    public final LifecycleRetainedValuesStoreOwner.FrameEndScheduler getFrameEndScheduler() {
        return this.frameEndScheduler;
    }

    @Override // androidx.compose.ui.node.Owner
    public GraphicsContext getGraphicsContext() {
        return this.graphicsContext;
    }

    @Override // androidx.compose.ui.node.Owner
    public HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public boolean getHasPendingMeasureOrLayout() {
        return this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout() || !this.outOfFrameQueue.isEmpty();
    }

    @Override // android.view.View
    public int getImportantForAutofill() {
        return 1;
    }

    @Override // androidx.compose.ui.node.Owner
    public InputModeManager getInputModeManager() {
        return this._inputModeManager;
    }

    public final InsetsListener getInsetsListener() {
        return this.insetsListener;
    }

    /* JADX INFO: renamed from: getLastMatrixRecalculationAnimationTime$ui, reason: from getter */
    public final long getLastMatrixRecalculationAnimationTime() {
        return this.lastMatrixRecalculationAnimationTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View, android.view.ViewParent, androidx.compose.ui.node.Owner
    public LayoutDirection getLayoutDirection() {
        return (LayoutDirection) this.layoutDirection.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public long getMeasureIteration() {
        return this.measureAndLayoutDelegate.getMeasureIteration();
    }

    @Override // androidx.compose.ui.node.Owner
    public ModifierLocalManager getModifierLocalManager() {
        return this.modifierLocalManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidComposeView getOutOfFrameExecutor() {
        if (isAttachedToWindow()) {
            return this;
        }
        return null;
    }

    @Override // androidx.compose.ui.node.Owner
    public Placeable.PlacementScope getPlacementScope() {
        return PlaceableKt.PlacementScope(this);
    }

    @Override // androidx.compose.ui.node.Owner
    public PointerIconService getPointerIconService() {
        return this.pointerIconService;
    }

    /* JADX INFO: renamed from: getPrimaryDirectionalMotionAxisOverride-dqNNBbU$ui, reason: not valid java name and from getter */
    public final IndirectPointerEventPrimaryDirectionalMotionAxis getPrimaryDirectionalMotionAxisOverride() {
        return this.primaryDirectionalMotionAxisOverride;
    }

    @Override // androidx.compose.ui.node.Owner
    public RectManager getRectManager() {
        return this.rectManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public RetainedValuesStore getRetainedValuesStore() {
        return this.retainedValuesStore;
    }

    @Override // androidx.compose.ui.node.Owner
    public LayoutNode getRoot() {
        return this.root;
    }

    @Override // androidx.compose.ui.node.Owner
    public RootForTest getRootForTest() {
        return this.rootForTest;
    }

    public final boolean getScrollCaptureInProgress$ui() {
        ScrollCapture scrollCapture = this.scrollCapture;
        if (scrollCapture != null) {
            return scrollCapture.getScrollCaptureInProgress();
        }
        return false;
    }

    @Override // androidx.compose.ui.node.Owner, androidx.compose.ui.node.RootForTest
    public SemanticsOwner getSemanticsOwner() {
        return this.semanticsOwner;
    }

    @Override // androidx.compose.ui.node.Owner
    public LayoutNodeDrawScope getSharedDrawScope() {
        return this.sharedDrawScope;
    }

    @Override // androidx.compose.ui.node.Owner
    public boolean getShowLayoutBounds() {
        return Api30Impl.INSTANCE.isShowingLayoutBounds(this);
    }

    @Override // androidx.compose.ui.node.Owner
    public OwnerSnapshotObserver getSnapshotObserver() {
        return this.snapshotObserver;
    }

    @Override // androidx.compose.ui.node.Owner
    public SoftwareKeyboardController getSoftwareKeyboardController() {
        return this.softwareKeyboardController;
    }

    @Override // androidx.compose.ui.node.Owner, androidx.compose.ui.node.RootForTest
    public TextInputService getTextInputService() {
        return this.textInputService;
    }

    @Override // androidx.compose.ui.node.Owner
    public TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    /* JADX INFO: renamed from: getUncaughtExceptionHandler$ui, reason: from getter */
    public final RootForTest.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.uncaughtExceptionHandler;
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public View getView() {
        return this;
    }

    @Override // androidx.compose.ui.node.Owner
    public ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    public final ViewTreeOwners getViewTreeOwners() {
        return (ViewTreeOwners) this.viewTreeOwners.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public WindowInfo getWindowInfo() {
        return this._windowInfo;
    }

    /* JADX INFO: renamed from: get_autofillManager$ui, reason: from getter */
    public final AndroidAutofillManager get_autofillManager() {
        return this._autofillManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public void incrementKeepScreenOnCount() {
        this.keepScreenOnCount++;
        getView().setKeepScreenOn(this.keepScreenOnCount > 0);
    }

    @Override // androidx.compose.ui.node.Owner
    public void incrementSensitiveComponentCount() {
        if (Build.VERSION.SDK_INT >= 35) {
            if (this.sensitiveComponentCount == 0) {
                AndroidComposeViewSensitiveContent35.INSTANCE.setContentSensitivity(getView(), true);
            }
            this.sensitiveComponentCount++;
        }
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public void invalidateDescendants() {
        invalidateLayers(getRoot());
    }

    /* JADX INFO: renamed from: isArrEnabled$ui, reason: from getter */
    public final boolean getIsArrEnabled() {
        return this.isArrEnabled;
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public boolean isLifecycleInResumedState() {
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycle;
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        return ((viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null || (lifecycle = lifecycleOwner.getLifecycle()) == null) ? null : lifecycle.getState()) == Lifecycle.State.RESUMED;
    }

    @Override // androidx.compose.ui.input.pointer.MatrixPositionCalculator
    /* JADX INFO: renamed from: localToScreen-58bKbWc */
    public void mo4374localToScreen58bKbWc(float[] localTransform) {
        recalculateWindowPosition();
        Matrix.m3397timesAssign58bKbWc(localTransform, this.viewToWindowMatrix);
        AndroidComposeView_androidKt.m5090preTranslatecG2Xzmc(localTransform, Float.intBitsToFloat((int) (this.windowPosition >> 32)), Float.intBitsToFloat((int) (this.windowPosition & 4294967295L)), this.tmpMatrix);
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U */
    public long mo4538localToScreenMKHz9U(long localPosition) {
        recalculateWindowPosition();
        long jM3384mapMKHz9U = Matrix.m3384mapMKHz9U(this.viewToWindowMatrix, localPosition);
        float fIntBitsToFloat = Float.intBitsToFloat((int) (jM3384mapMKHz9U >> 32)) + Float.intBitsToFloat((int) (this.windowPosition >> 32));
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (jM3384mapMKHz9U & 4294967295L)) + Float.intBitsToFloat((int) (this.windowPosition & 4294967295L)))) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32));
    }

    @Override // androidx.compose.ui.node.Owner
    public void measureAndLayout(boolean sendPointerUpdate) {
        Function0<Unit> function0;
        if (this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout() || this.measureAndLayoutDelegate.getHasPendingOnPositionedCallbacks()) {
            Trace.beginSection("AndroidOwner:measureAndLayout");
            if (sendPointerUpdate) {
                try {
                    function0 = this.resendMotionEventOnLayout;
                } finally {
                    Trace.endSection();
                }
            } else {
                function0 = null;
            }
            if (this.measureAndLayoutDelegate.measureAndLayout(function0)) {
                requestLayout();
            }
            MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks$default(this.measureAndLayoutDelegate, false, 1, null);
            dispatchPendingInteropLayoutCallbacks();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: measureAndLayout-0kLqBqw */
    public void mo5016measureAndLayout0kLqBqw(LayoutNode layoutNode, long constraints) {
        Trace.beginSection("AndroidOwner:measureAndLayout");
        try {
            this.measureAndLayoutDelegate.m4890measureAndLayout0kLqBqw(layoutNode, constraints);
            if (!this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout()) {
                MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks$default(this.measureAndLayoutDelegate, false, 1, null);
                dispatchPendingInteropLayoutCallbacks();
            }
            getRectManager().dispatchCallbacks();
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.compose.ui.node.RootForTest
    public void measureAndLayoutForTest() {
        Owner.measureAndLayout$default(this, false, 1, null);
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.outOfFrameRunnable);
        }
        this.outOfFrameRunnable.run();
    }

    @Override // androidx.compose.ui.focus.PlatformFocusOwner
    /* JADX INFO: renamed from: moveFocusInChildren-3ESFkO8 */
    public boolean mo2830moveFocusInChildren3ESFkO8(int focusDirection) {
        if (ComposeUiFlags.isViewFocusFixEnabled) {
            return m5065moveFocusInChildrenViewFocusFix3ESFkO8(focusDirection);
        }
        return ComposeUiFlags.isBypassUnfocusableComposeViewEnabled ? m5063moveFocusInChildrenBypassUnfocusableComposeView3ESFkO8(focusDirection) : m5064moveFocusInChildrenCurrent3ESFkO8(focusDirection);
    }

    public final void notifyLayerIsDirty$ui(OwnedLayer layer, boolean isDirty) {
        boolean z = this.isDrawingContent;
        if (!isDirty) {
            if (z) {
                return;
            }
            this.dirtyLayers.remove(layer);
            MutableObjectList<OwnedLayer> mutableObjectList = this.postponedDirtyLayers;
            if (mutableObjectList != null) {
                mutableObjectList.remove(layer);
                return;
            }
            return;
        }
        if (!z) {
            this.dirtyLayers.add(layer);
            return;
        }
        MutableObjectList<OwnedLayer> mutableObjectList2 = this.postponedDirtyLayers;
        if (mutableObjectList2 == null) {
            mutableObjectList2 = new MutableObjectList<>(0, 1, (DefaultConstructorMarker) null);
            this.postponedDirtyLayers = mutableObjectList2;
        }
        mutableObjectList2.add(layer);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycle;
        LifecycleOwner lifecycleOwner2;
        AndroidAutofill androidAutofill;
        super.onAttachedToWindow();
        if (ComposeUiFlags.areWindowInsetsRulersEnabled) {
            this.insetsListener.onViewAttachedToWindow(this);
        }
        INSTANCE.addNotificationForSysPropsChange(this);
        this._windowInfo.setWindowFocused(hasWindowFocus());
        this._windowInfo.setOnInitializeContainerSize(new Function0<DerivedSize>() { // from class: androidx.compose.ui.platform.AndroidComposeView.onAttachedToWindow.1
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final DerivedSize m5078invoke() {
                return AndroidWindowInfo_androidKt.calculateWindowSize(AndroidComposeView.this);
            }
        });
        updateWindowMetrics();
        invalidateLayoutNodeMeasurement(getRoot());
        invalidateLayers(getRoot());
        getSnapshotObserver().startObserving$ui();
        if (autofillSupported() && (androidAutofill = this._autofill) != null) {
            AutofillCallback.INSTANCE.register(androidAutofill);
        }
        LifecycleOwner lifecycleOwner3 = ViewTreeLifecycleOwner.get(this);
        SavedStateRegistryOwner savedStateRegistryOwner = ViewTreeSavedStateRegistryOwner.get(this);
        ViewModelStoreOwner viewModelStoreOwner = ViewTreeViewModelStoreOwner.get(this);
        RetainedValuesStore retainedValuesStoreInstallLocalRetainedValuesStore = installLocalRetainedValuesStore(lifecycleOwner3, viewModelStoreOwner);
        if (retainedValuesStoreInstallLocalRetainedValuesStore == null) {
            retainedValuesStoreInstallLocalRetainedValuesStore = ForgetfulRetainedValuesStore.INSTANCE;
        }
        this.retainedValuesStore = retainedValuesStoreInstallLocalRetainedValuesStore;
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        Lifecycle lifecycle2 = null;
        if (viewTreeOwners == null || (lifecycleOwner3 != null && savedStateRegistryOwner != null && (lifecycleOwner3 != viewTreeOwners.getLifecycleOwner() || savedStateRegistryOwner != viewTreeOwners.getSavedStateRegistryOwner() || viewModelStoreOwner != viewTreeOwners.getViewModelStoreOwner()))) {
            if (lifecycleOwner3 == null) {
                k2d.a("Composed into the View which doesn't propagate ViewTreeLifecycleOwner!");
                return;
            }
            if (savedStateRegistryOwner == null) {
                k2d.a("Composed into the View which doesn't propagateViewTreeSavedStateRegistryOwner!");
                return;
            }
            if (viewTreeOwners != null && (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
                lifecycle.removeObserver(this);
            }
            lifecycleOwner3.getLifecycle().addObserver(this);
            ViewTreeOwners viewTreeOwners2 = new ViewTreeOwners(lifecycleOwner3, savedStateRegistryOwner, viewModelStoreOwner);
            set_viewTreeOwners(viewTreeOwners2);
            Function1<? super ViewTreeOwners, Unit> function1 = this.onViewTreeOwnersAvailable;
            if (function1 != null) {
                function1.invoke(viewTreeOwners2);
            }
            this.onViewTreeOwnersAvailable = null;
        }
        this._inputModeManager.m3953setInputModeiuPiT84(isInTouchMode() ? InputMode.INSTANCE.m3950getTouchaOaMEAU() : InputMode.INSTANCE.m3949getKeyboardaOaMEAU());
        ViewTreeOwners viewTreeOwners3 = getViewTreeOwners();
        if (viewTreeOwners3 != null && (lifecycleOwner2 = viewTreeOwners3.getLifecycleOwner()) != null) {
            lifecycle2 = lifecycleOwner2.getLifecycle();
        }
        if (lifecycle2 == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("No lifecycle owner exists");
            wq6.a();
            return;
        }
        lifecycle2.addObserver(this);
        lifecycle2.addObserver(this.contentCaptureManager);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
        getViewTreeObserver().addOnScrollChangedListener(this);
        getViewTreeObserver().addOnTouchModeChangeListener(this);
        AndroidComposeViewTranslationCallbackS.INSTANCE.setViewTranslationCallback(this);
        AndroidAutofillManager androidAutofillManager = this._autofillManager;
        if (androidAutofillManager != null) {
            getFocusOwner().getListeners().add(androidAutofillManager);
            getSemanticsOwner().getListeners$ui().add(androidAutofillManager);
        }
        getFocusOwner().getListeners().add(this);
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        AndroidPlatformTextInputSession androidPlatformTextInputSession = (AndroidPlatformTextInputSession) SessionMutex.m2615getCurrentSessionimpl(this.textInputSessionMutex);
        return androidPlatformTextInputSession == null ? this.legacyTextInputServiceAndroid.getEditorHasFocus() : androidPlatformTextInputSession.isReadyForConnection();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateConfiguration(newConfig);
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        AndroidPlatformTextInputSession androidPlatformTextInputSession = (AndroidPlatformTextInputSession) SessionMutex.m2615getCurrentSessionimpl(this.textInputSessionMutex);
        return androidPlatformTextInputSession == null ? this.legacyTextInputServiceAndroid.createInputConnection(outAttrs) : androidPlatformTextInputSession.createInputConnection(outAttrs);
    }

    @Override // android.view.View
    public void onCreateVirtualViewTranslationRequests(long[] virtualIds, int[] supportedFormats, Consumer<ViewTranslationRequest> requestsCollector) {
        this.contentCaptureManager.onCreateVirtualViewTranslationRequests$ui(virtualIds, supportedFormats, requestsCollector);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onDetach(LayoutNode node) {
        AndroidAutofillManager androidAutofillManager;
        getLayoutNodes().remove(node.getSemanticsId());
        this.measureAndLayoutDelegate.onNodeDetached(node);
        requestClearInvalidObservations();
        if (autofillSupported() && ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
            androidAutofillManager.onDetach$ui(node);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AndroidAutofill androidAutofill;
        LifecycleOwner lifecycleOwner;
        super.onDetachedFromWindow();
        if (ComposeUiFlags.areWindowInsetsRulersEnabled) {
            this.insetsListener.onViewDetachedFromWindow(this);
        }
        if (this.isArrEnabled) {
            View view = this.frameRateCategoryView;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("frameRateCategoryView");
                view = null;
            }
            removeView(view);
        }
        INSTANCE.removeNotificationForSysPropsChange(this);
        getSnapshotObserver().stopObserving$ui();
        this._windowInfo.setOnInitializeContainerSize(null);
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        Lifecycle lifecycle = (viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null) ? null : lifecycleOwner.getLifecycle();
        if (lifecycle == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("No lifecycle owner exists");
            wq6.a();
            return;
        }
        lifecycle.removeObserver(this.contentCaptureManager);
        lifecycle.removeObserver(this);
        if (autofillSupported() && (androidAutofill = this._autofill) != null) {
            AutofillCallback.INSTANCE.unregister(androidAutofill);
        }
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
        getViewTreeObserver().removeOnScrollChangedListener(this);
        getViewTreeObserver().removeOnTouchModeChangeListener(this);
        LifecycleRetainedValuesStoreOwner.RetainedValuesStoreEntry retainedValuesStoreEntry = this.lifecycleRetainedValuesStoreOwnerEntry;
        if (retainedValuesStoreEntry != null) {
            retainedValuesStoreEntry.release();
        }
        this.lifecycleRetainedValuesStoreOwnerEntry = null;
        AndroidComposeViewTranslationCallbackS.INSTANCE.clearViewTranslationCallback(this);
        AndroidAutofillManager androidAutofillManager = this._autofillManager;
        if (androidAutofillManager != null) {
            getSemanticsOwner().getListeners$ui().remove(androidAutofillManager);
            getFocusOwner().getListeners().remove(androidAutofillManager);
        }
        getRectManager().removeScheduledCallback();
        getFocusOwner().getListeners().remove(this);
    }

    @Override // android.view.View
    public void onDraw(android.graphics.Canvas canvas) {
    }

    @Override // androidx.compose.ui.node.Owner
    public void onEndApplyChanges() {
        MutableObjectList<Function0<Unit>> mutableObjectList;
        AndroidAutofillManager androidAutofillManager;
        if (this.observationClearRequested) {
            getSnapshotObserver().clearInvalidObservations$ui();
            this.observationClearRequested = false;
        }
        AndroidViewsHandler androidViewsHandler = this._androidViewsHandler;
        if (androidViewsHandler != null) {
            clearChildInvalidObservations(androidViewsHandler);
        }
        if (autofillSupported() && ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
            androidAutofillManager.onEndApplyChanges$ui();
        }
        while (this.endApplyChangesListeners.isNotEmpty() && this.endApplyChangesListeners.get(0) != null) {
            int size = this.endApplyChangesListeners.getSize();
            int i = 0;
            while (true) {
                mutableObjectList = this.endApplyChangesListeners;
                if (i < size) {
                    Function0 function0 = (Function0) mutableObjectList.get(i);
                    this.endApplyChangesListeners.set(i, (Object) null);
                    if (function0 != null) {
                        function0.invoke();
                    }
                    i++;
                }
            }
            mutableObjectList.removeRange(0, size);
        }
    }

    @Override // androidx.compose.ui.focus.FocusListener
    public void onFocusChanged(FocusTargetModifierNode previous, FocusTargetModifierNode current) {
        NodeChain nodes;
        NodeChain nodes2;
        if (!ComposeUiFlags.isOptimizedFocusEventDispatchEnabled || previous == null) {
            return;
        }
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(2097152);
        if (!previous.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = previous.getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(previous);
        LinkedHashSet linkedHashSet = null;
        ArrayList arrayList = null;
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & iM4949constructorimpl) != 0) {
                        Modifier.Node nodePop = node;
                        MutableVector mutableVector = null;
                        while (nodePop != null) {
                            if (nodePop instanceof IndirectPointerInputModifierNode) {
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                arrayList.add(nodePop);
                            } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i = 0;
                                for (Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate(); delegate$ui != null; delegate$ui = delegate$ui.getChild()) {
                                    if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate$ui;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector.add(delegate$ui);
                                        }
                                    }
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node = (layoutNodeRequireLayoutNode == null || (nodes2 = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes2.getTail();
        }
        if (arrayList == null) {
            return;
        }
        if (current != null) {
            int iM4949constructorimpl2 = NodeKind.m4949constructorimpl(2097152);
            if (!current.getNode().getIsAttached()) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node2 = current.getNode();
            LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(current);
            LinkedHashSet linkedHashSet2 = null;
            while (layoutNodeRequireLayoutNode2 != null) {
                if ((layoutNodeRequireLayoutNode2.getNodes().getHead().getAggregateChildKindSet() & iM4949constructorimpl2) != 0) {
                    while (node2 != null) {
                        if ((node2.getKindSet() & iM4949constructorimpl2) != 0) {
                            Modifier.Node nodePop2 = node2;
                            MutableVector mutableVector2 = null;
                            while (nodePop2 != null) {
                                if (nodePop2 instanceof IndirectPointerInputModifierNode) {
                                    if (linkedHashSet2 == null) {
                                        linkedHashSet2 = new LinkedHashSet();
                                    }
                                    linkedHashSet2.add(nodePop2);
                                } else if ((nodePop2.getKindSet() & iM4949constructorimpl2) != 0 && (nodePop2 instanceof DelegatingNode)) {
                                    int i2 = 0;
                                    for (Modifier.Node delegate$ui2 = ((DelegatingNode) nodePop2).getDelegate(); delegate$ui2 != null; delegate$ui2 = delegate$ui2.getChild()) {
                                        if ((delegate$ui2.getKindSet() & iM4949constructorimpl2) != 0) {
                                            i2++;
                                            if (i2 == 1) {
                                                nodePop2 = delegate$ui2;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop2 != null) {
                                                    mutableVector2.add(nodePop2);
                                                    nodePop2 = null;
                                                }
                                                mutableVector2.add(delegate$ui2);
                                            }
                                        }
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                            }
                        }
                        node2 = node2.getParent();
                    }
                }
                layoutNodeRequireLayoutNode2 = layoutNodeRequireLayoutNode2.getParent$ui();
                node2 = (layoutNodeRequireLayoutNode2 == null || (nodes = layoutNodeRequireLayoutNode2.getNodes()) == null) ? null : nodes.getTail();
            }
            linkedHashSet = linkedHashSet2;
        }
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            IndirectPointerInputModifierNode indirectPointerInputModifierNode = (IndirectPointerInputModifierNode) arrayList.get(i3);
            if (!(linkedHashSet != null ? linkedHashSet.contains(indirectPointerInputModifierNode) : false)) {
                indirectPointerInputModifierNode.onCancelIndirectPointerInput();
            }
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.lastMatrixRecalculationAnimationTime = 0L;
        updatePositionCacheAndDispatch();
        dispatchConfigurationChangeIfNeeded();
    }

    @Override // androidx.compose.ui.node.Owner
    public void onInteropViewLayoutChange(View view) {
        this.isPendingInteropViewLayoutChangeDispatch = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        this.lastMatrixRecalculationAnimationTime = 0L;
        this.measureAndLayoutDelegate.measureAndLayout(this.resendMotionEventOnLayout);
        this.onMeasureConstraints = null;
        updatePositionCacheAndDispatch();
        if (this._androidViewsHandler != null) {
            getAndroidViewsHandler$ui().layout(0, 0, r - l, b - t);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onLayoutChange(LayoutNode layoutNode) {
        this.composeAccessibilityDelegate.onLayoutChange$ui(layoutNode);
        this.contentCaptureManager.onLayoutChange$ui();
    }

    @Override // androidx.compose.ui.node.Owner
    public void onLayoutNodeDeactivated(LayoutNode layoutNode) {
        AndroidAutofillManager androidAutofillManager;
        if (autofillSupported() && ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
            androidAutofillManager.onLayoutNodeDeactivated$ui(layoutNode);
        }
    }

    @Override // android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Trace.beginSection("AndroidOwner:onMeasure");
        try {
            if (!isAttachedToWindow()) {
                invalidateLayoutNodeMeasurement(getRoot());
            }
            long jM5059convertMeasureSpecI7RO_PI = m5059convertMeasureSpecI7RO_PI(widthMeasureSpec);
            int i = (int) ULong.constructor-impl(jM5059convertMeasureSpecI7RO_PI >>> 32);
            int i2 = (int) ULong.constructor-impl(jM5059convertMeasureSpecI7RO_PI & 4294967295L);
            long jM5059convertMeasureSpecI7RO_PI2 = m5059convertMeasureSpecI7RO_PI(heightMeasureSpec);
            long jM5983fitPrioritizingHeightZbe2FdA = Constraints.INSTANCE.m5983fitPrioritizingHeightZbe2FdA(i, i2, (int) ULong.constructor-impl(jM5059convertMeasureSpecI7RO_PI2 >>> 32), (int) ULong.constructor-impl(4294967295L & jM5059convertMeasureSpecI7RO_PI2));
            Constraints constraints = this.onMeasureConstraints;
            boolean zM5968equalsimpl0 = false;
            if (constraints == null) {
                this.onMeasureConstraints = Constraints.m5962boximpl(jM5983fitPrioritizingHeightZbe2FdA);
                this.wasMeasuredWithMultipleConstraints = false;
            } else {
                if (constraints != null) {
                    zM5968equalsimpl0 = Constraints.m5968equalsimpl0(constraints.getValue(), jM5983fitPrioritizingHeightZbe2FdA);
                }
                if (!zM5968equalsimpl0) {
                    this.wasMeasuredWithMultipleConstraints = true;
                }
            }
            this.measureAndLayoutDelegate.m4891updateRootConstraintsBRTryo0(jM5983fitPrioritizingHeightZbe2FdA);
            this.measureAndLayoutDelegate.measureOnly();
            setMeasuredDimension(getRoot().getWidth(), getRoot().getHeight());
            if (this._androidViewsHandler != null) {
                getAndroidViewsHandler$ui().measure(View.MeasureSpec.makeMeasureSpec(getRoot().getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getRoot().getHeight(), 1073741824));
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onPostAttach(LayoutNode node) {
        AndroidAutofillManager androidAutofillManager;
        if (autofillSupported() && ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
            androidAutofillManager.onPostAttach$ui(node);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onPostLayoutNodeReused(LayoutNode layoutNode, int oldSemanticsId) {
        AndroidAutofillManager androidAutofillManager;
        if (autofillSupported() && ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
            androidAutofillManager.onPostLayoutNodeReused$ui(layoutNode, oldSemanticsId);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onPreAttach(LayoutNode node) {
        getLayoutNodes().set(node.getSemanticsId(), node);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onPreLayoutNodeReused(LayoutNode layoutNode, int oldSemanticsId) {
        getLayoutNodes().remove(oldSemanticsId);
        getLayoutNodes().set(layoutNode.getSemanticsId(), layoutNode);
    }

    @Override // android.view.View
    public void onProvideAutofillVirtualStructure(ViewStructure structure, int flags) {
        AndroidAutofillManager androidAutofillManager;
        if (!autofillSupported() || structure == null) {
            return;
        }
        if (ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
            androidAutofillManager.populateViewStructure(structure);
        }
        AndroidAutofill androidAutofill = this._autofill;
        if (androidAutofill != null) {
            AndroidAutofill_androidKt.populateViewStructure(androidAutofill, structure);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onRequestMeasure(LayoutNode layoutNode, boolean affectsLookahead, boolean forceRequest, boolean scheduleMeasureAndLayout) {
        MeasureAndLayoutDelegate measureAndLayoutDelegate = this.measureAndLayoutDelegate;
        if (affectsLookahead) {
            if (measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, forceRequest) && scheduleMeasureAndLayout) {
                scheduleMeasureAndLayout(layoutNode);
                return;
            }
            return;
        }
        if (measureAndLayoutDelegate.requestRemeasure(layoutNode, forceRequest) && scheduleMeasureAndLayout) {
            scheduleMeasureAndLayout(layoutNode);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onRequestRelayout(LayoutNode layoutNode, boolean affectsLookahead, boolean forceRequest) {
        MeasureAndLayoutDelegate measureAndLayoutDelegate = this.measureAndLayoutDelegate;
        if (affectsLookahead) {
            if (measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, forceRequest)) {
                scheduleMeasureAndLayout$default(this, null, 1, null);
            }
        } else if (measureAndLayoutDelegate.requestRelayout(layoutNode, forceRequest)) {
            scheduleMeasureAndLayout$default(this, null, 1, null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public android.view.PointerIcon onResolvePointerIcon(MotionEvent event, int pointerIndex) {
        PointerIcon stylusHoverIcon;
        int toolType = event.getToolType(pointerIndex);
        return (event.isFromSource(InputDeviceCompat.SOURCE_MOUSE) || !event.isFromSource(InputDeviceCompat.SOURCE_STYLUS) || !(toolType == 2 || toolType == 4) || (stylusHoverIcon = getPointerIconService().getCurrentStylusHoverIcon()) == null) ? super.onResolvePointerIcon(event, pointerIndex) : AndroidComposeViewVerificationHelperMethodsN.INSTANCE.toAndroidPointerIcon(getContext(), stylusHoverIcon);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(LifecycleOwner owner) {
        LifecycleRetainedValuesStoreOwner.RetainedValuesStoreEntry retainedValuesStoreEntry = this.lifecycleRetainedValuesStoreOwnerEntry;
        if (retainedValuesStoreEntry != null) {
            LifecycleRetainedValuesStoreOwner.FrameEndScheduler frameEndScheduler = this.frameEndScheduler;
            frameEndScheduler.getClass();
            retainedValuesStoreEntry.stopRetainingExitedValues(frameEndScheduler);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        if (this.superclassInitComplete) {
            LayoutDirection layoutDirection2 = FocusInteropUtils_androidKt.toLayoutDirection(layoutDirection);
            if (layoutDirection2 == null) {
                layoutDirection2 = LayoutDirection.Ltr;
            }
            setLayoutDirection(layoutDirection2);
        }
    }

    @Override // android.view.View
    public void onScrollCaptureSearch(android.graphics.Rect localVisibleRect, Point windowOffset, Consumer<ScrollCaptureTarget> targets) {
        ScrollCapture scrollCapture = this.scrollCapture;
        if (scrollCapture != null) {
            scrollCapture.onScrollCaptureSearch(this, getSemanticsOwner(), getCoroutineContext(), targets);
        }
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        updatePositionCacheAndDispatch();
    }

    @Override // androidx.compose.ui.node.Owner
    public void onSemanticsChange() {
        this.composeAccessibilityDelegate.onSemanticsChange$ui();
        this.contentCaptureManager.onSemanticsChange$ui();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        LifecycleRetainedValuesStoreOwner.RetainedValuesStoreEntry retainedValuesStoreEntry = this.lifecycleRetainedValuesStoreOwnerEntry;
        if (retainedValuesStoreEntry != null) {
            retainedValuesStoreEntry.startRetainingExitedValues();
        }
    }

    @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
    public void onTouchModeChanged(boolean isInTouchMode) {
        this._inputModeManager.m3953setInputModeiuPiT84(isInTouchMode ? InputMode.INSTANCE.m3950getTouchaOaMEAU() : InputMode.INSTANCE.m3949getKeyboardaOaMEAU());
    }

    @Override // android.view.View
    public void onVirtualViewTranslationResponses(LongSparseArray<ViewTranslationResponse> response) {
        AndroidContentCaptureManager androidContentCaptureManager = this.contentCaptureManager;
        androidContentCaptureManager.onVirtualViewTranslationResponses$ui(androidContentCaptureManager, response);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        this._windowInfo.setWindowFocused(hasWindowFocus);
        this.keyboardModifiersRequireUpdate = true;
        super.onWindowFocusChanged(hasWindowFocus);
    }

    public final boolean recycle$ui(OwnedLayer layer) {
        if (this.viewLayersContainer != null) {
            ViewLayer.INSTANCE.getShouldUseDispatchDraw();
        }
        this.layerCache.push(layer);
        this.dirtyLayers.remove(layer);
        return true;
    }

    @Override // androidx.compose.ui.node.Owner
    public void registerOnEndApplyChangesListener(Function0<Unit> listener) {
        if (this.endApplyChangesListeners.contains(listener)) {
            return;
        }
        this.endApplyChangesListeners.add(listener);
    }

    @Override // androidx.compose.ui.node.Owner
    public void registerOnLayoutCompletedListener(Owner.OnLayoutCompletedListener listener) {
        this.measureAndLayoutDelegate.registerOnLayoutCompletedListener(listener);
        scheduleMeasureAndLayout$default(this, null, 1, null);
    }

    public final void removeAndroidView(AndroidViewHolder view) {
        getAndroidViewsHandler$ui().removeViewInLayout(view);
        HashMap<LayoutNode, AndroidViewHolder> layoutNodeToHolder = getAndroidViewsHandler$ui().getLayoutNodeToHolder();
        TypeIntrinsics.asMutableMap(layoutNodeToHolder).remove(getAndroidViewsHandler$ui().getHolderToLayoutNode().remove(view));
        view.setImportantForAccessibility(0);
    }

    @Override // androidx.compose.ui.node.Owner
    public void requestAutofill(LayoutNode node) {
        AndroidAutofillManager androidAutofillManager;
        if (autofillSupported() && ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
            androidAutofillManager.requestAutofill$ui(node);
        }
    }

    public final void requestClearInvalidObservations() {
        this.observationClearRequested = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int direction, android.graphics.Rect previouslyFocusedRect) {
        if (ComposeUiFlags.isViewFocusFixEnabled) {
            return requestFocusViewFocusFix(direction, previouslyFocusedRect);
        }
        return ComposeUiFlags.isBypassUnfocusableComposeViewEnabled ? requestFocusBypassUnfocusableComposeView(direction, previouslyFocusedRect) : requestFocusCurrent(direction, previouslyFocusedRect);
    }

    public final boolean requestFocusBypassUnfocusableComposeView(int direction, android.graphics.Rect previouslyFocusedRect) {
        if (isFocused()) {
            return true;
        }
        FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(direction);
        final int value = focusDirection != null ? focusDirection.getValue() : FocusDirection.INSTANCE.m2764getEnterdhqQ8s();
        Boolean boolMo2779focusSearchULY8qGw = getFocusOwner().mo2779focusSearchULY8qGw(value, previouslyFocusedRect != null ? RectHelper_androidKt.toComposeRect(previouslyFocusedRect) : null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$requestFocusBypassUnfocusableComposeView$requestFocusWithPrevRect$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(value));
            }
        });
        Boolean bool = Boolean.TRUE;
        if (Intrinsics.areEqual(boolMo2779focusSearchULY8qGw, bool)) {
            return true;
        }
        if (ComposeUiFlags.isIgnoreInvalidPrevFocusRectEnabled && Intrinsics.areEqual(getFocusOwner().mo2779focusSearchULY8qGw(value, null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$requestFocusBypassUnfocusableComposeView$requestFocusWithoutPrevRect$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(value));
            }
        }), bool)) {
            return true;
        }
        if (hasFocus() && FocusOwnerImplKt.m2789is1dFocusSearch3ESFkO8(value)) {
            return getFocusOwner().mo2782resetFocus3ESFkO8(value);
        }
        return false;
    }

    public final boolean requestFocusCurrent(int direction, android.graphics.Rect previouslyFocusedRect) {
        if (isFocused()) {
            return true;
        }
        if (getFocusOwner().getRootState().getHasFocus()) {
            return super.requestFocus(direction, previouslyFocusedRect);
        }
        FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(direction);
        final int value = focusDirection != null ? focusDirection.getValue() : FocusDirection.INSTANCE.m2764getEnterdhqQ8s();
        return Intrinsics.areEqual(getFocusOwner().mo2779focusSearchULY8qGw(value, previouslyFocusedRect != null ? RectHelper_androidKt.toComposeRect(previouslyFocusedRect) : null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView.requestFocusCurrent.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(value));
            }
        }), Boolean.TRUE);
    }

    public final boolean requestFocusViewFocusFix(int direction, android.graphics.Rect previouslyFocusedRect) {
        View viewFindNextNonChildView;
        if (isFocused()) {
            return true;
        }
        if (this.processingRequestFocusForNextNonChildView) {
            return false;
        }
        FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(direction);
        final int value = focusDirection != null ? focusDirection.getValue() : FocusDirection.INSTANCE.m2764getEnterdhqQ8s();
        if (hasFocus() && mo2830moveFocusInChildren3ESFkO8(value)) {
            return true;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Boolean boolMo2779focusSearchULY8qGw = getFocusOwner().mo2779focusSearchULY8qGw(value, previouslyFocusedRect != null ? RectHelper_androidKt.toComposeRect(previouslyFocusedRect) : null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$requestFocusViewFocusFix$focusSearchResult$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                booleanRef.element = true;
                return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(value));
            }
        });
        if (boolMo2779focusSearchULY8qGw == null) {
            return false;
        }
        if (boolMo2779focusSearchULY8qGw.booleanValue()) {
            return true;
        }
        if (booleanRef.element) {
            return false;
        }
        if ((previouslyFocusedRect != null && !hasFocus() && Intrinsics.areEqual(getFocusOwner().mo2779focusSearchULY8qGw(value, null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$requestFocusViewFocusFix$altFocus$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(value));
            }
        }), Boolean.TRUE)) || (viewFindNextNonChildView = findNextNonChildView(direction)) == null || viewFindNextNonChildView == this) {
            return true;
        }
        this.processingRequestFocusForNextNonChildView = true;
        boolean zRequestFocus = viewFindNextNonChildView.requestFocus(direction);
        this.processingRequestFocusForNextNonChildView = false;
        return zRequestFocus;
    }

    @Override // androidx.compose.ui.node.Owner
    public void requestOnPositionedCallback(LayoutNode layoutNode) {
        this.measureAndLayoutDelegate.requestOnPositionedCallback(layoutNode);
        scheduleMeasureAndLayout$default(this, null, 1, null);
    }

    @Override // androidx.compose.ui.focus.PlatformFocusOwner
    /* JADX INFO: renamed from: requestOwnerFocus-7o62pno */
    public boolean mo2831requestOwnerFocus7o62pno(FocusDirection focusDirection, Rect previouslyFocusedRect) {
        Integer numM2771toAndroidFocusDirection3ESFkO8;
        if (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
            if (isFocused()) {
                return true;
            }
        } else if (isFocused() || hasFocus()) {
            return true;
        }
        return super.requestFocus((focusDirection == null || (numM2771toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m2771toAndroidFocusDirection3ESFkO8(focusDirection.getValue())) == null) ? 130 : numM2771toAndroidFocusDirection3ESFkO8.intValue(), previouslyFocusedRect != null ? RectHelper_androidKt.toAndroidRect(previouslyFocusedRect) : null);
    }

    @Override // androidx.compose.ui.node.OutOfFrameExecutor
    public void schedule(Function0<Unit> block) {
        boolean zIsEmpty = this.outOfFrameQueue.isEmpty();
        this.outOfFrameQueue.addLast(block);
        if (zIsEmpty) {
            Handler handler = getHandler();
            if (handler != null) {
                handler.postAtFrontOfQueue(this.outOfFrameRunnable);
            } else {
                w01.a("schedule is called when outOfFrameExecutor is not available (view is detached)");
            }
        }
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U */
    public long mo4539screenToLocalMKHz9U(long positionOnScreen) {
        recalculateWindowPosition();
        float fIntBitsToFloat = Float.intBitsToFloat((int) (positionOnScreen >> 32)) - Float.intBitsToFloat((int) (this.windowPosition >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (positionOnScreen & 4294967295L)) - Float.intBitsToFloat((int) (this.windowPosition & 4294967295L));
        return Matrix.m3384mapMKHz9U(this.windowToViewMatrix, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32)));
    }

    @Override // androidx.compose.ui.node.RootForTest
    public boolean sendIndirectPointerEvent(IndirectPointerEvent indirectPointerEvent) {
        if (AndroidIndirectPointerEvent_androidKt.getNativeEvent(indirectPointerEvent).getActionMasked() != 3) {
            return handleIndirectPointerEvent(indirectPointerEvent);
        }
        getFocusOwner().dispatchIndirectPointerCancel();
        return true;
    }

    @Override // androidx.compose.ui.node.RootForTest
    /* JADX INFO: renamed from: sendKeyEvent-ZmokQxo */
    public boolean mo5018sendKeyEventZmokQxo(KeyEvent keyEvent) {
        return getFocusOwner().mo2777dispatchInterceptedSoftKeyboardEventZmokQxo(keyEvent) || FocusOwner.m2775dispatchKeyEventYhN2O0w$default(getFocusOwner(), keyEvent, null, 2, null);
    }

    @Override // androidx.compose.ui.node.RootForTest
    public void setAccessibilityEventBatchIntervalMillis(long intervalMillis) {
        this.composeAccessibilityDelegate.setSendRecurringAccessibilityEventsIntervalMillis$ui(intervalMillis);
    }

    public final void setConfiguration(Configuration configuration) {
        this.configuration.setValue(configuration);
    }

    public final void setContentCaptureManager$ui(AndroidContentCaptureManager androidContentCaptureManager) {
        this.contentCaptureManager = androidContentCaptureManager;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v7 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "result" is null
        	at jadx.core.dex.visitors.PrepareForCodeGen.removeInstructions(PrepareForCodeGen.java:118)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:85)
        */
    public void setCoroutineContext(kotlin.coroutines.CoroutineContext r12) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.setCoroutineContext(kotlin.coroutines.CoroutineContext):void");
    }

    public final void setFrameEndScheduler$ui(LifecycleRetainedValuesStoreOwner.FrameEndScheduler frameEndScheduler) {
        this.frameEndScheduler = frameEndScheduler;
    }

    public final void setLastMatrixRecalculationAnimationTime$ui(long j) {
        this.lastMatrixRecalculationAnimationTime = j;
    }

    public final void setOnViewTreeOwnersAvailable(Function1<? super ViewTreeOwners, Unit> callback) {
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null) {
            callback.invoke(viewTreeOwners);
        }
        if (isAttachedToWindow()) {
            return;
        }
        this.onViewTreeOwnersAvailable = callback;
    }

    /* JADX INFO: renamed from: setPrimaryDirectionalMotionAxisOverride-r2epLt8$ui, reason: not valid java name */
    public final void m5070setPrimaryDirectionalMotionAxisOverrider2epLt8$ui(IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis) {
        this.primaryDirectionalMotionAxisOverride = indirectPointerEventPrimaryDirectionalMotionAxis;
    }

    @Override // androidx.compose.ui.node.Owner
    public void setShowLayoutBounds(boolean z) {
        this.showLayoutBounds = z;
    }

    @Override // androidx.compose.ui.node.RootForTest
    public void setUncaughtExceptionHandler(RootForTest.UncaughtExceptionHandler handler) {
        this.uncaughtExceptionHandler = handler;
        this.measureAndLayoutDelegate.setUncaughtExceptionHandler$ui(handler);
    }

    public final void setUncaughtExceptionHandler$ui(RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // androidx.compose.ui.node.Owner
    public Object textInputSession(Function2<? super PlatformTextInputSessionScope, ? super Continuation<?>, ? extends Object> function2, Continuation<?> continuation) {
        C01071 c01071;
        if (continuation instanceof C01071) {
            c01071 = (C01071) continuation;
            int i = c01071.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01071.label = i - Integer.MIN_VALUE;
            } else {
                c01071 = new C01071(continuation);
            }
        } else {
            c01071 = new C01071(continuation);
        }
        Object obj = c01071.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01071.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            AtomicReference<SessionMutex.Session<AndroidPlatformTextInputSession>> atomicReference = this.textInputSessionMutex;
            Function1<CoroutineScope, AndroidPlatformTextInputSession> function1 = new Function1<CoroutineScope, AndroidPlatformTextInputSession>() { // from class: androidx.compose.ui.platform.AndroidComposeView.textInputSession.2
                {
                    super(1);
                }

                public final AndroidPlatformTextInputSession invoke(CoroutineScope coroutineScope) {
                    AndroidComposeView androidComposeView = AndroidComposeView.this;
                    return new AndroidPlatformTextInputSession(androidComposeView, androidComposeView.getTextInputService(), coroutineScope);
                }
            };
            c01071.label = 1;
            if (SessionMutex.m2618withSessionCancellingPreviousimpl(atomicReference, function1, function2, c01071) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        wq6.a();
        return null;
    }

    @Override // androidx.compose.ui.node.Owner
    public void voteFrameRate(float frameRate) {
        if (this.isArrEnabled) {
            if (frameRate > 0.0f) {
                if (Float.isNaN(this.currentFrameRate) || frameRate > this.currentFrameRate) {
                    this.currentFrameRate = frameRate;
                    return;
                }
                return;
            }
            if (frameRate < 0.0f) {
                if (Float.isNaN(this.currentFrameRateCategory) || frameRate < this.currentFrameRateCategory) {
                    this.currentFrameRateCategory = frameRate;
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0007R\u0014\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$Companion;", "", "<init>", "()V", "systemPropertiesClass", "Ljava/lang/Class;", "getBooleanMethod", "Ljava/lang/reflect/Method;", "addChangeCallbackMethod", "composeViews", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/platform/AndroidComposeView;", "systemPropertiesChangedRunnable", "Ljava/lang/Runnable;", "dispatchOnScrollChangedMethod", "getIsShowingLayoutBounds", "", "addNotificationForSysPropsChange", "", "composeView", "removeNotificationForSysPropsChange", "dispatchOnScrollChanged", "viewTreeObserver", "Landroid/view/ViewTreeObserver;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static void a() {
            synchronized (AndroidComposeView.composeViews) {
                try {
                    MutableObjectList mutableObjectList = AndroidComposeView.composeViews;
                    Object[] objArr = ((ObjectList) mutableObjectList).content;
                    int i = ((ObjectList) mutableObjectList)._size;
                    for (int i2 = 0; i2 < i; i2++) {
                        ((AndroidComposeView) objArr[i2]).invalidateDescendants();
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void addNotificationForSysPropsChange(AndroidComposeView composeView) {
            if (AndroidComposeView.systemPropertiesChangedRunnable == null) {
                Runnable runnable = new Runnable() { // from class: g50
                    @Override // java.lang.Runnable
                    public final void run() {
                        AndroidComposeView.Companion.a();
                    }
                };
                AndroidComposeView.systemPropertiesChangedRunnable = runnable;
                StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
                try {
                    if (AndroidComposeView.systemPropertiesClass == null) {
                        AndroidComposeView.systemPropertiesClass = Class.forName("android.os.SystemProperties");
                    }
                    if (AndroidComposeView.addChangeCallbackMethod == null) {
                        StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);
                        Class cls = AndroidComposeView.systemPropertiesClass;
                        AndroidComposeView.addChangeCallbackMethod = cls != null ? cls.getDeclaredMethod("addChangeCallback", Runnable.class) : null;
                    }
                    Method method = AndroidComposeView.addChangeCallbackMethod;
                    if (method != null) {
                        method.invoke(null, runnable);
                    }
                } catch (Throwable unused) {
                }
                StrictMode.setVmPolicy(vmPolicy);
            }
            synchronized (AndroidComposeView.composeViews) {
                AndroidComposeView.composeViews.add(composeView);
                Unit unit = Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean getIsShowingLayoutBounds() {
            try {
                if (AndroidComposeView.systemPropertiesClass == null) {
                    AndroidComposeView.systemPropertiesClass = Class.forName("android.os.SystemProperties");
                }
                if (AndroidComposeView.getBooleanMethod == null) {
                    Class cls = AndroidComposeView.systemPropertiesClass;
                    AndroidComposeView.getBooleanMethod = cls != null ? cls.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE) : null;
                }
                Method method = AndroidComposeView.getBooleanMethod;
                Object objInvoke = method != null ? method.invoke(null, "debug.layout", Boolean.FALSE) : null;
                return Intrinsics.areEqual(objInvoke instanceof Boolean ? (Boolean) objInvoke : null, Boolean.TRUE);
            } catch (Exception unused) {
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void removeNotificationForSysPropsChange(AndroidComposeView composeView) {
            synchronized (AndroidComposeView.composeViews) {
                AndroidComposeView.composeViews.remove(composeView);
                Unit unit = Unit.INSTANCE;
            }
        }

        public final void dispatchOnScrollChanged(ViewTreeObserver viewTreeObserver) {
            try {
                if (AndroidComposeView.dispatchOnScrollChangedMethod == null) {
                    Method declaredMethod = viewTreeObserver.getClass().getDeclaredMethod("dispatchOnScrollChanged", null);
                    declaredMethod.setAccessible(true);
                    AndroidComposeView.dispatchOnScrollChangedMethod = declaredMethod;
                }
                Method method = AndroidComposeView.dispatchOnScrollChangedMethod;
                if (method != null) {
                    method.invoke(viewTreeObserver, null);
                }
            } catch (Exception unused) {
            }
        }

        private Companion() {
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidAccessibilityManager getAccessibilityManager() {
        return this.accessibilityManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidClipboard getClipboard() {
        return this.clipboard;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidDragAndDropManager getDragAndDropManager() {
        return this.dragAndDropManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public MutableIntObjectMap<LayoutNode> getLayoutNodes() {
        return this.layoutNodes;
    }

    @Override // android.view.ViewGroup
    public void addView(View child) {
        addView(child, -1);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int width, int height) {
        ViewGroup.LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        layoutParamsGenerateDefaultLayoutParams.width = width;
        layoutParamsGenerateDefaultLayoutParams.height = height;
        Unit unit = Unit.INSTANCE;
        addView(child, -1, layoutParamsGenerateDefaultLayoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View child, ViewGroup.LayoutParams params) {
        addView(child, -1, params);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        addViewInLayout(child, index, params, true);
    }

    private final void recalculateWindowPosition(MotionEvent motionEvent) {
        this.lastMatrixRecalculationAnimationTime = AnimationUtils.currentAnimationTimeMillis();
        recalculateWindowViewTransforms();
        float[] fArr = this.viewToWindowMatrix;
        float x = motionEvent.getX();
        long jM3384mapMKHz9U = Matrix.m3384mapMKHz9U(fArr, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(motionEvent.getY())) & 4294967295L) | (Float.floatToRawIntBits(x) << 32)));
        this.windowPosition = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(motionEvent.getRawX() - Float.intBitsToFloat((int) (jM3384mapMKHz9U >> 32)))) << 32) | (((long) Float.floatToRawIntBits(motionEvent.getRawY() - Float.intBitsToFloat((int) (jM3384mapMKHz9U & 4294967295L)))) & 4294967295L));
    }

    @Override // android.view.View
    public void onFocusChanged(boolean gainFocus, int direction, android.graphics.Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus || hasFocus()) {
            return;
        }
        getFocusOwner().releaseFocus();
    }
}
